package com.p.s.w.configuration;

import com.hbase.dao.HBaseTemplate;
import com.hbase.dao.PooledHTableFactory;
import com.hbase.dao.TableFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * HBase配置类
 * @author clufeng
 * @version 1.0.0
 **/
@Configuration
@PropertySource("classpath:hbase.properties")
public class HBaseConfiguration {

    @Bean
    public org.apache.hadoop.conf.Configuration getHBaseConfigurationFactoryBean(@Value("${hbase.client.host}") String quorum,
                                                                                 @Value("${hbase.client.port}") String clientPort,
                                                                                 @Value("${hbase.ipc.client.tcpnodelay}") String tcpnodelay,
                                                                                 @Value("${hbase.rpc.timeout}") String rpcTimeout,
                                                                                 @Value("${hbase.client.operation.timeout}") String opTimeout,
                                                                                 @Value("${hbase.ipc.client.socket.timeout.read}") String readTimeout,
                                                                                 @Value("${hbase.ipc.client.socket.timeout.write}") String writeTimeout){

        org.apache.hadoop.conf.Configuration configuration = org.apache.hadoop.hbase.HBaseConfiguration.create();

        configuration.set("hbase.zookeeper.quorum", quorum);
        configuration.set("hbase.zookeeper.property.clientPort", clientPort);
        configuration.set("zookeeper.znode.parent", "/hbase");
        configuration.set("hbase.ipc.client.tcpnodelay", tcpnodelay);
        configuration.set("hbase.rpc.timeout", rpcTimeout);
        configuration.set("hbase.client.operation.timeout", opTimeout);
        configuration.set("hbase.ipc.client.socket.timeout.read", readTimeout);
        configuration.set("hbase.ipc.client.socket.timeout.write", writeTimeout);

        configuration.set("hbase.client.async.enable", "true");
        configuration.set("hbase.client.async.in.queuesize", "10000");
        configuration.set("hbase.tablemultiplexer.flush.period.ms", "100");
        configuration.set("hbase.client.max.retries.in.queue", "3");

        return configuration;

    }

    @Bean
    public TableFactory getHBaseTableFactory(@Autowired org.apache.hadoop.conf.Configuration configuration,
                                             @Value("${hbase.client.thread.max}") int threadNum,
                                             @Value("${hbase.client.threadPool.queueSize}") int queueSize,
                                             @Value("${hbase.client.threadPool.prestart}") boolean preStart) {

        return new PooledHTableFactory(configuration, null, threadNum, queueSize, preStart);

    }

    @Bean
    public HBaseTemplate getHBaseOperations(@Autowired org.apache.hadoop.conf.Configuration configuration,
                                            @Autowired TableFactory tableFactory) {
        HBaseTemplate template = new HBaseTemplate();
        template.setTableFactory(tableFactory);
        template.setConfiguration(configuration);
        return template;
    }

}
