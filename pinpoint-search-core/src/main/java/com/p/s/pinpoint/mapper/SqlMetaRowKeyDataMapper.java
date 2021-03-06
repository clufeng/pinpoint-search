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

package com.p.s.pinpoint.mapper;

import com.hbase.dao.mapper.RowMapper;
import com.p.s.pinpoint.model.SqlMetaDataBo;
import com.sematext.hbase.wd.RowKeyDistributorByHashPrefix;
import org.apache.hadoop.hbase.client.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author emeroad
 * @author minwoo.jung
 */
@Component
public class SqlMetaRowKeyDataMapper implements RowMapper<SqlMetaDataBo> {

    @Autowired
    @Qualifier("metadataRowKeyDistributor")
    private RowKeyDistributorByHashPrefix rowKeyDistributorByHashPrefix;

    @Override
    public SqlMetaDataBo mapRow(Result result, int rowNum) throws Exception {
        if (result.isEmpty()) {
            return new SqlMetaDataBo();
        }
        final byte[] rowKey = getOriginalKey(result.getRow());
        SqlMetaDataBo sqlMetaDataBo = new SqlMetaDataBo();
        sqlMetaDataBo.readRowKey(rowKey);

        return sqlMetaDataBo;
    }

    private byte[] getOriginalKey(byte[] rowKey) {
        return rowKeyDistributorByHashPrefix.getOriginalKey(rowKey);
    }
    
    public void setRowKeyDistributorByHashPrefix(RowKeyDistributorByHashPrefix rowKeyDistributorByHashPrefix) {
        this.rowKeyDistributorByHashPrefix = rowKeyDistributorByHashPrefix;
    }
}
