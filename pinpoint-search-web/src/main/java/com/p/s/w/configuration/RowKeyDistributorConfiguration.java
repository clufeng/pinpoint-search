package com.p.s.w.configuration;


import com.p.s.c.utils.RangeOneByteSimpleHash;
import com.sematext.hbase.wd.RowKeyDistributorByHashPrefix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RowKeyDistributorConfiguration {

    @Bean(name="applicationTraceIndexDistributor")
    public RowKeyDistributorByHashPrefix getApplicationTraceIndexDistributor() {
        RowKeyDistributorByHashPrefix.OneByteSimpleHash oneByteSimpleHash = new RowKeyDistributorByHashPrefix.OneByteSimpleHash(32);

        return new RowKeyDistributorByHashPrefix(oneByteSimpleHash);
    }


    @Bean(name="traceV2Distributor")
    public RowKeyDistributorByHashPrefix getTraceV2Distributor() {
        RangeOneByteSimpleHash rangeOneByteSimpleHash = new RangeOneByteSimpleHash(32, 40, 256);
        return new RowKeyDistributorByHashPrefix(rangeOneByteSimpleHash);
    }



    @Bean(name="metadataRowKeyDistributor")
    public RowKeyDistributorByHashPrefix getMetadataRowKeyDistributor() {
        RangeOneByteSimpleHash rangeOneByteSimpleHash = new RangeOneByteSimpleHash(0, 36, 32);
        return new RowKeyDistributorByHashPrefix(rangeOneByteSimpleHash);
    }


}
