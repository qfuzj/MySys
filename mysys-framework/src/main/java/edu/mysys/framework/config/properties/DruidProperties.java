package edu.mysys.framework.config.properties;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Druid 配置属性
 */
@Configuration
public class DruidProperties {
    @Value("${spring.datasource.druid.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.druid.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.druid.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.druid.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.druid.connectTimeout}")
    private int connectTimeout;

    @Value("${spring.datasource.druid.socketTimeout}")
    private int socketTimeout;

    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.druid.maxEvictableIdleTimeMillis}")
    private int maxEvictableIdleTimeMillis;

    @Value("${spring.datasource.druid.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.druid.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.druid.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.druid.testOnReturn}")
    private boolean testOnReturn;

    /**
     * 配置并返回 DruidDataSource 实例。
     * <p>
     * 根据当前属性设置数据源的连接池参数，包括初始化大小、最大活动连接数、最小空闲连接数、
     * 连接超时、空闲检测、连接有效性检测等。
     * </p>
     *
     * @param datasource 需要配置的 DruidDataSource 实例
     * @return 配置后的 DruidDataSource 实例
     */
    public DruidDataSource dataSource(DruidDataSource datasource) {
        // 配置连接池的初始化大小
        datasource.setInitialSize(initialSize);
        // 配置连接池的最大活动连接数
        datasource.setMaxActive(maxActive);
        // 配置连接池的最小空闲连接数
        datasource.setMinIdle(minIdle);

        // 配置获取连接等待超时的时间（毫秒）
        datasource.setMaxWait(maxWait);

        // 配置连接超时时间（毫秒）
        datasource.setConnectTimeout(connectTimeout);
        // 配置Socket超时时间（毫秒）
        datasource.setSocketTimeout(socketTimeout);
        // 再次配置获取连接等待超时的时间（毫秒），可去重
        datasource.setMaxWait(maxWait);
        // 配置多久进行一次空闲连接检测（毫秒）
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // 配置连接在池中最小生存时间（毫秒）
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 配置连接在池中最大生存时间（毫秒）
        datasource.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
        // 配置用于检测连接是否有效的SQL语句
        datasource.setValidationQuery(validationQuery);
        // 配置空闲时检测连接有效性
        datasource.setTestWhileIdle(testWhileIdle);
        // 配置借用连接时检测连接有效性
        datasource.setTestOnBorrow(testOnBorrow);
        // 配置归还连接时检测连接有效性
        datasource.setTestOnReturn(testOnReturn);
        return datasource;
    }
}
