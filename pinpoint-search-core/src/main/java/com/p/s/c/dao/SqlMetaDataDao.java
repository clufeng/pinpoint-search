package com.p.s.c.dao;

import com.p.s.c.model.SqlMetaDataBo;

import java.util.List;

public interface SqlMetaDataDao {

    List<SqlMetaDataBo> getSqlMetaData(String agentId, long time, int sqlId);

    List<List<SqlMetaDataBo>> getSqlMetaRowKeyDataOther(String agentId, long time);

    byte[] getRowKey(String agentId, long time, int sqlId);

    boolean delete(String agentId, long time, int sqlId);

    boolean delete(List<SqlMetaDataBo> metaDataBos);

}
