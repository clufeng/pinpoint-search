/*
 * Copyright 2014 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.p.s.pinpoint.dao;

import com.hbase.dao.HBaseOperations;
import com.hbase.dao.extractor.LimitRowMapperResultsExtractor;
import com.hbase.dao.mapper.RowMapper;
import com.p.s.pinpoint.hbase.HBaseTables;
import com.p.s.pinpoint.model.SqlMetaDataBo;
import com.p.s.pinpoint.utils.BytesUtils;
import com.p.s.pinpoint.utils.RowKeyUtils;
import com.p.s.pinpoint.utils.TimeUtils;
import com.sematext.hbase.wd.RowKeyDistributorByHashPrefix;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Repository
public class HbaseSqlMetaDataDao implements SqlMetaDataDao {

    @Autowired
    private HBaseOperations hbaseOperations2;

    @Autowired
    @Qualifier("sqlMetaDataMapper")
    private RowMapper<List<SqlMetaDataBo>> sqlMetaDataMapper;

    @Autowired
    @Qualifier("sqlMetaRowKeyDataMapper")
    private RowMapper<SqlMetaDataBo> sqlMetaRowKeyDataMapper;

    @Autowired
    @Qualifier("metadataRowKeyDistributor")
    private RowKeyDistributorByHashPrefix rowKeyDistributorByHashPrefix;

    @Override
    public List<SqlMetaDataBo> getSqlMetaData(String agentId, long time, int sqlId) {
        if (agentId == null) {
            throw new NullPointerException("agentId must not be null");
        }

        SqlMetaDataBo sqlMetaData = new SqlMetaDataBo(agentId, time, sqlId);
        byte[] rowKey = getDistributedKey(sqlMetaData.toRowKey());

        Get get = new Get(rowKey);
        get.addFamily(HBaseTables.SQL_METADATA_VER2_CF_SQL);

        return hbaseOperations2.get(HBaseTables.SQL_METADATA_VER2, get, sqlMetaDataMapper);
    }

    @Override
    public byte[] getRowKey(String agentId, long time, int sqlId) {
        if (agentId == null) {
            throw new NullPointerException("agentId must not be null");
        }

        SqlMetaDataBo sqlMetaData = new SqlMetaDataBo(agentId, time, sqlId);
        byte[] rowKey = getDistributedKey(sqlMetaData.toRowKey());

        return rowKey;
    }

    @Override
    public boolean delete(String agentId, long time, int sqlId) {

        byte[] rowKey =  getRowKey(agentId, time, sqlId);

        Delete delete = new Delete(rowKey);

        hbaseOperations2.delete(HBaseTables.SQL_METADATA_VER2, delete);

        return true;
    }

    @Override
    public boolean delete(List<SqlMetaDataBo> metaDataBos) {

        List<Delete> deletes = new ArrayList<>();

        metaDataBos.forEach(bo -> {
            byte[] rowKey =  getRowKey(bo.getAgentId(), bo.getStartTime(), bo.getSqlId());

            Delete delete = new Delete(rowKey);

            deletes.add(delete);
        });

        hbaseOperations2.delete(HBaseTables.SQL_METADATA_VER2, deletes);

        return true;
    }

    @Override
    public List<List<SqlMetaDataBo>> getSqlMetaRowKeyDataOther(String agentId, long time) {

        if (agentId == null) {
            throw new NullPointerException("agentId must not be null");
        }

        List<Scan> scans = createScan2(agentId, time);

        LimitRowMapperResultsExtractor<SqlMetaDataBo> lrmre = new LimitRowMapperResultsExtractor<>(sqlMetaRowKeyDataMapper, 10000);

        return this.hbaseOperations2.find(HBaseTables.SQL_METADATA_VER2, scans, lrmre);

    }

    private List<Scan> createScan2(String agentId, long currentTime) {

        List<Scan> scans = new ArrayList<>();

        for (byte i = 0; i < 32; i++) {

            scans.add(createHashScan(agentId, currentTime, i));
        }

        return scans;
    }

    private Scan createHashScan(String agentId, long currentTime, byte hash) {

        Scan scan = new Scan();
        byte[] preHash = new byte[1];
        preHash[0] = hash;

        byte[] agentIdBytes = Bytes.toBytes(agentId);

        byte[] preAgentIdBytes = Bytes.add(preHash, agentIdBytes);

        long startTime = TimeUtils.reverseTimeMillis(currentTime);

        byte[] startKeyBytes = RowKeyUtils.concatFixedByteAndLong(preAgentIdBytes, HBaseTables.AGENT_NAME_MAX_LEN + 1, startTime + 1);
        byte[] endKeyBytes = RowKeyUtils.concatFixedByteAndLong(preAgentIdBytes, HBaseTables.AGENT_NAME_MAX_LEN + 1, Long.MAX_VALUE);

        startKeyBytes = Bytes.padTail(startKeyBytes, BytesUtils.INT_BYTE_LENGTH);
        endKeyBytes = Bytes.padTail(endKeyBytes, BytesUtils.INT_BYTE_LENGTH);

        scan.setStartRow(startKeyBytes);
        scan.setStopRow(endKeyBytes);
        scan.addFamily(HBaseTables.SQL_METADATA_VER2_CF_SQL);
        scan.setCaching(256);
        scan.setMaxVersions(1);

        return scan;
    }


    private byte[] getDistributedKey(byte[] rowKey) {
        return rowKeyDistributorByHashPrefix.getDistributedKey(rowKey);
    }
    
    public void setSqlMetaDataMapper(RowMapper<List<SqlMetaDataBo>> sqlMetaDataMapper) {
        this.sqlMetaDataMapper = sqlMetaDataMapper;
    }
    
    public void setRowKeyDistributorByHashPrefix(RowKeyDistributorByHashPrefix rowKeyDistributorByHashPrefix) {
        this.rowKeyDistributorByHashPrefix = rowKeyDistributorByHashPrefix;
    }

}
