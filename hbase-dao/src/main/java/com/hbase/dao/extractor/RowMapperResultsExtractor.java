package com.hbase.dao.extractor;

import com.hbase.dao.mapper.RowMapper;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter encapsulating the RowMapper callback.
 * copy : https://github.com/spring-projects/spring-hadoop  1.0.2
 */
public class RowMapperResultsExtractor<T> implements ResultsExtractor<List<T>> {

    private final RowMapper<T> rowMapper;

    /**
     * Create a new RowMapperResultSetExtractor.
     *
     * @param rowMapper the RowMapper which creates an object for each row
     */
    public RowMapperResultsExtractor(RowMapper<T> rowMapper) {
        if(rowMapper == null) {
            throw new IllegalArgumentException("RowMapper is required");
        }
        this.rowMapper = rowMapper;
    }

    public List<T> extractData(ResultScanner results) throws Exception {
        List<T> rs = new ArrayList<>();
        int rowNum = 0;
        for (Result result : results) {
            rs.add(this.rowMapper.mapRow(result, rowNum++));
        }
        return rs;
    }
}
