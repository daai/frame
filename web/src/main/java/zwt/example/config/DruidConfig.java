package zwt.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.google.common.collect.Maps;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Map;

/**
 * @Author: zwt
 * @Date: Create in 15:25 2019-05-06
 */
@Configuration
@EnableTransactionManagement
@MapperScan("zwt.example.mapper*")
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /*@Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(this.druidDataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mybatis*//*.xml"));
        return sqlSessionFactoryBean.getObject();
    }*/

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(this.druidDataSource());
    }

    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = Maps.newHashMap();
        //　可配的属性都在 StatViewServlet 和其父类下
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "111111");
        servletRegistrationBean.setInitParameters(initParams);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidWebStatFilter() {
        FilterRegistrationBean reg = new FilterRegistrationBean();
        reg.setFilter(new WebStatFilter());
        reg.setUrlPatterns(Arrays.asList("/*"));
        reg.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        reg.addInitParameter("sessionStatMaxCount", "1000");
        reg.addInitParameter("sessionStatEnable", "true");
        reg.addInitParameter("principalSessionName", "druid.user");
        reg.addInitParameter("profileEnable", "true");
        return reg;
    }

    /**
     * Spring和Jdbc的关联监控。
     * DruidStatInterceptor:标准的Spring MethodInterceptor。可以灵活进行AOP配置
     * Advice
     * @return
     */
    @Bean
    public DruidStatInterceptor interceptorNames(){
        DruidStatInterceptor inc = new DruidStatInterceptor();
        return inc;
    }
}
