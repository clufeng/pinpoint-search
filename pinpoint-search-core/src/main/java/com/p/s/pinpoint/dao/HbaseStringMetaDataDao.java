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
import com.hbase.dao.mapper.RowMapper;
import com.p.s.pinpoint.hbase.HBaseTables;
import com.p.s.pinpoint.model.StringMetaDataBo;
import com.sematext.hbase.wd.RowKeyDistributorByHashPrefix;
import org.apache.hadoop.hbase.client.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author emeroad
 */
@Repository
public class HbaseStringMetaDataDao implements StringMetaDataDao {

    @Autowired
    private HBaseOperations hbaseOperations2;

    @Autowired
    @Qualifier("stringMetaDataMapper")
    private RowMapper<List<StringMetaDataBo>> stringMetaDataMapper;

    @Autowired
    @Qualifier("metadataRowKeyDistributor")
    private RowKeyDistributorByHashPrefix rowKeyDistributorByHashPrefix;

    @Override
    public List<StringMetaDataBo> getStringMetaData(String agentId, long time, int stringId) {
        if (agentId == null) {
            throw new NullPointerException("agentId must not be null");
        }

        StringMetaDataBo stringMetaData = new StringMetaDataBo(agentId, time, stringId);
        byte[] rowKey = getDistributedKey(stringMetaData.toRowKey());

        Get get = new Get(rowKey);
        get.addFamily(HBaseTables.STRING_METADATA_CF_STR);

        return hbaseOperations2.get(HBaseTables.STRING_METADATA, get, stringMetaDataMapper);
    }

    private byte[] getDistributedKey(byte[] rowKey) {
        return rowKeyDistributorByHashPrefix.getDistributedKey(rowKey);
    }
}
