##目录

- [springboot整合redis](#ralated-redis)
- [springboot配置文件](#ralated-aplication)
- [springboot-Restful与swagger2文档维护](#ralated-swagger2)
- [springboot-data-jpa](#ralated-data-jpa)
- [springboot-mutildatasource多数据源配置](#ralated-mutildatasource)
- [springboot-mongodb](#ralated-mongodb)
- [springboot-mybatis](#ralated-mybatis)
- [springboot-mybatis-annotation](#ralated-mybatis-annotation)
- [springboot-mybatis-druid](#ralated-mybatis-druid)
- [springboot-log](#ralated-log)
- [springboot-aop-log](#ralated-aop-log)
- [springboot-scheduled](#ralated-scheduled)
- [springboot-async](#ralated-async)
- [springboot-async-threadpool自定义线程池](#ralated-async-threadpool)
- [springboot-async-threadpool-close线程池正确关闭](#ralated-async-threadpool-close)
- [springboot-security安全控制](#ralated-security)
- [springboot-cache-ehcache缓存支持注解配置与EhCache使用](#ralated-cache-ehcache)
- [springboot-cache-redis集中缓存Redis](#ralated-cache-redis)
- [springboot-javaMailSender邮件发送](#ralated-mailsend)
- [springboot-rabbitmq-mytest个人测试RabbitMQ消息队列](#ralated-rabbitmq-mytest)
- [CAS](#ralated-cas)
- [java-rabbitmq-test1](#ralated-java-rabbitmq-test1)
- [springboot-rabbitmq-test2](#ralated-rabbitmq-test2)

<a name="ralated-redis"></a>
###1、springboot整合redis
- jar包更新(jedis)：

    ```text
    **原来的**
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    **更新为**
    <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-redis</artifactId>          
    </dependency>
    <dependency>
       <groupId>redis.clients</groupId>
       <artifactId>jedis</artifactId>
    </dependency>
    ```
<a name="ralated-aplication"></a>
###2、springboot配置文件
- 禁用命令行访问属性设置：

    ```text
    SpringApplication springApplication = new SpringApplication(SpringbootApplication1Application.class);
            springApplication.setAddCommandLineProperties(false);
            springApplication.run(args);
    ```
 - 命令行设置属性：
 
     ```text
     设置属性用--
     例如：java -jar xxx.jar --server.port=8888
     ```       
- 多环境配置：

  ```text
  Spring Boot中多环境配置文件名需要满足application-{profile}.properties的格式

    比如：
        application-dev.properties：开发环境
        application-test.properties：测试环境
        application-prod.properties：生产环境
    至于哪个具体的配置文件会被加载，需要在application.properties文件中通过spring.profiles.active属性来设置，其值对应{profile}值。
  ```      
- Springboot Configuration Annotation Processor not fund in classpath解决：

```text
加入依赖
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```  
    
<a name="ralated-swagger2"></a>
###3、springboot-Restful与swagger2文档维护
- jar包引入：

    ```text
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>2.2.2</version>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
        <version>2.2.2</version>
    </dependency>
    ``` 
- 注意点：

    ```text
    1、访问：http://localhost:8080/swagger-ui.html
    2、@RequestBody可以接收json数据,请求头设置为Content-Type:application/json
       @ModelAttribute接收请求头设置为Content-Type:application/x-www-form-urlencoded
    3、使用swagger2传入非String类型，需要在@ApiImplicitParam注解中添加paramType = "path"     
    ``` 
        
<a name="ralated-data-jpa"></a>
###4、springboot-data-jpa
- 配置：

    ```text
    1、spring.jpa.properties.hibernate.hbm2ddl.auto=
     
    create：每次加载hibernate时都会删除上一次的生成的表，然后根据你的model类再重新来生成新表，哪怕两次没有任何改变也要这样执行，这就是导致数据库表数据丢失的一个重要原因。
    create-drop：每次加载hibernate时根据model类生成表，但是sessionFactory一关闭,表就自动删除。
    update：最常用的属性，第一次加载hibernate时根据model类会自动建立起表的结构（前提是先建立好数据库），以后加载hibernate时根据model类自动更新表结构，即使表结构改变了但表中的行仍然存在不会删除以前的行。要注意的是当部署到服务器后，表结构是不会被马上建立起来的，是要等应用第一次运行起来后才会。
    validate：每次加载hibernate时，验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值。
     
    2、Spring-data-jpa的一大特性：通过解析方法名创建查询。无需任何类sql语句就可查询
     
    3、导入依赖：
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
      </dependency>
   ``` 
- @GeneratedValue四种参数
    - AUTO:主键由程序控制, 是默认选项
    - IDENTITY:主键由数据库生成, 采用数据库自增长, Oracle不支持这种方式
    - SEQUENCE:通过数据库的序列产生主键, MYSQL  不支持
    - Table:提供特定的数据库产生主键, 该方式更有利于数据库的移植
    
<a name="ralated-mutildatasource"></a>
###5、springboot-mutildatasource多数据源配置

jdbctemplate主数据源配置为spring.datasource.primary开头的配置，第二数据源配置为spring.datasource.secondary开头的配置
    
- application.properties：
```text
spring.datasource.primary.jdbc-url=jdbc:mysql://localhost:3306/springbootdb
spring.datasource.primary.username=root
spring.datasource.primary.password=
spring.datasource.primary.driver-class-name=com.mysql.jdbc.Driver
   
spring.datasource.secondary.jdbc-url=jdbc:mysql://localhost:3306/springbootdb2
spring.datasource.secondary.username=root
spring.datasource.secondary.password=
spring.datasource.secondary.driver-class-name=com.mysql.jdbc.Driver
```
- jdbc template 多数据源配置：
```text
1、引入依赖：
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
  </dependency>

2、配置文件:
  import org.springframework.beans.factory.annotation.Qualifier;
  import org.springframework.boot.context.properties.ConfigurationProperties;
  import org.springframework.boot.jdbc.DataSourceBuilder;
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.context.annotation.Primary;
  import org.springframework.jdbc.core.JdbcTemplate;
  
  import javax.sql.DataSource;
  
  /**
   * 多数据源配置
   */
  @Configuration
  public class DataSourceConfig {
  
      @Primary
      @Bean(name = "primaryDataSource")
      @ConfigurationProperties(prefix = "spring.datasource.primary")
      public DataSource primaryDataSource(){
          return DataSourceBuilder.create().build();
      }
  
  
      @Bean(name = "secondaryDataSource")
      @ConfigurationProperties(prefix = "spring.datasource.secondary")
      public DataSource secondaryDataSource() {
          return DataSourceBuilder.create().build();
      }
  
      /**
       * jdbc template多数据源配置
       * @param dataSource
       * @return
       */
      @Bean(name = "primaryJdbcTemplate")
      public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource){
          return new JdbcTemplate(dataSource);
      }
  
      /**
       * jdbc template多数据源配置
       * @param dataSource
       * @return
       */
      @Bean(name = "secondaryJdbcTemplate")
      public JdbcTemplate secondaryJdbcTemplate(
              @Qualifier("secondaryDataSource") DataSource dataSource) {
          return new JdbcTemplate(dataSource);
      }
  }
```     
- jdbc template 多数据源配置问题记录：
```text
jdbcUrl is required with driverClassName错误：
  springboot2.0.0之后：
      方法1：spring.datasource.url改为spring.datasource.jdbc-url
      方法2：数据源配置时候更改DataSource使用DataSourceProperties
        @Primary
        @Bean(name = "primaryDataSource")
        @ConfigurationProperties(prefix = "spring.datasource.primary")
        public DataSourceProperties primaryDataSource(){
            return new DataSourceProperties();
        }
    
    
        @Bean(name = "secondaryDataSource")
        @ConfigurationProperties(prefix = "spring.datasource.secondary")
        public DataSourceProperties secondaryDataSource() {
            return new DataSourceProperties();
        }
    
        /**
         * jdbc template多数据源配置
         * @param dataSource
         * @return
         */
        @Bean(name = "primaryJdbcTemplate")
        public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSourceProperties dataSource){
            return new JdbcTemplate(dataSource.initializeDataSourceBuilder().build());
        }
    
        /**
         * jdbc template多数据源配置
         * @param dataSource
         * @return
         */
        @Bean(name = "secondaryJdbcTemplate")
        public JdbcTemplate secondaryJdbcTemplate(
                @Qualifier("secondaryDataSource") DataSourceProperties dataSource) {
            return new JdbcTemplate(dataSource.initializeDataSourceBuilder().build());
        }
```
spring-data-jpa多数据源配置:
```text
    1、配置多数据源
      @Primary
      @Bean(name = "primaryDataSource")
      @Qualifier("primaryDataSource")
      @ConfigurationProperties(prefix = "spring.datasource.primary")
      public DataSource primaryDataSource(){
          return DataSourceBuilder.create().build();
      }
  
  
      @Bean(name = "secondaryDataSource")
      @Qualifier("secondaryDataSource")
      @ConfigurationProperties(prefix = "spring.datasource.secondary")
      public DataSource secondaryDataSource() {
          return DataSourceBuilder.create().build();
      }
    2、每个类型的Config单独配置:例如主数据源
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
        import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
        import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
        import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.context.annotation.Primary;
        import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
        import org.springframework.orm.jpa.JpaTransactionManager;
        import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
        import org.springframework.transaction.PlatformTransactionManager;
        import org.springframework.transaction.annotation.EnableTransactionManagement;
        
        import javax.persistence.EntityManager;
        import javax.sql.DataSource;
        import java.util.Map;
        
        @Configuration
        @EnableTransactionManagement
        @EnableJpaRepositories(
                entityManagerFactoryRef="entityManagerFactoryPrimary",
                transactionManagerRef="transactionManagerPrimary",
                basePackages= { "com.dx.springbootmutildatasourcedatajpa.domain.p" }) //设置Repository所在位置
        public class PrimaryConfig {
        
            @Autowired
            @Qualifier("primaryDataSource")
            private DataSource primaryDataSource;
        
            @Primary
            @Bean(name = "entityManagerPrimary")
            public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
                return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
            }
        
            @Primary
            @Bean(name = "entityManagerFactoryPrimary")
            public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary (EntityManagerFactoryBuilder builder) {
                return builder
                        .dataSource(primaryDataSource)
                        .properties(getVendorProperties())
                        .packages("com.dx.springbootmutildatasourcedatajpa.domain.p") //设置实体类所在位置
                        .persistenceUnit("primaryPersistenceUnit")
                        .build();
            }
        
            @Autowired
            private HibernateProperties hibernateProperties;
        
            @Autowired
            private JpaProperties jpaProperties;
        
            /*此方法与springBoot2.x以上版本舍弃
            private Map<String, String> getVendorProperties(DataSource dataSource) {
                return jpaProperties.getHibernateProperties(dataSource);
            }
            */
        
            private Map<String, Object> getVendorProperties() {
                Map<String, Object> stringObjectMap = hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
                return stringObjectMap;
            }
        
            @Primary
            @Bean(name = "transactionManagerPrimary")
            public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
                return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
            }
        
        }    
```    
**版本方法使用注意点：**

在springboot2.x以上版本，该方法不再使用

    private Map<String, String> getVendorProperties(DataSource dataSource) {
       return jpaProperties.getHibernateProperties(dataSource);
    }
    
改用

    @Autowired
    private HibernateProperties hibernateProperties;
    
    private Map<String, Object> getVendorProperties() {
        Map<String, Object> stringObjectMap = hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
        return stringObjectMap;
    }

注解：
```text
  @EnableJpaRepositories(
      entityManagerFactoryRef="entityManagerFactoryPrimary",  //实体管理工厂引用名称，对应到@Bean注解对应的方法
      transactionManagerRef="transactionManagerPrimary",  //事务管理工厂引用名称，对应到@Bean注解对应的方法
      basePackages= { "com.dx.springbootmutildatasourcedatajpa.domain.p" }) //设置Repository所在位置
```        

<a name="ralated-mongodb"></a>
###6、springboot-mongodb
       
mongodb创建用户：
```text
> db.createUser(
...   {
...     user: "dba",
...     pwd: "dba",
...     roles: [ { role: "userAdminAnyDatabase", db: "admin" } ]
...   }
... )
  
Built-In Roles（内置角色）：
1. 数据库用户角色：read、readWrite;
2. 数据库管理角色：dbAdmin、dbOwner、userAdmin；
3. 集群管理角色：clusterAdmin、clusterManager、clusterMonitor、hostManager；
4. 备份恢复角色：backup、restore；
5. 所有数据库角色：readAnyDatabase、readWriteAnyDatabase、userAdminAnyDatabase、dbAdminAnyDatabase
6. 超级用户角色：root  
// 这里还有几个角色间接或直接提供了系统超级用户的访问（dbOwner 、userAdmin、userAdminAnyDatabase）
7. 内部角色：__system  
 
Read：允许用户读取指定数据库
readWrite：允许用户读写指定数据库
dbAdmin：允许用户在指定数据库中执行管理函数，如索引创建、删除，查看统计或访问system.profile
userAdmin：允许用户向system.users集合写入，可以找指定数据库里创建、删除和管理用户
clusterAdmin：只在admin数据库中可用，赋予用户所有分片和复制集相关函数的管理权限。
readAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的读权限
readWriteAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的读写权限
userAdminAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的userAdmin权限
dbAdminAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的dbAdmin权限。
root：只在admin数据库中可用。超级账号，超级权限

```   
   
```text
1、导入依赖：
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
 
2、实现实体类的数据访问对象：XxxxRepository
public interface XxxxRepository extends MongoRepository<User, Long> {

    User findByUsername(String username);
    ...
}
   
3、本地连接无需配置application
  使用用户连接指定数据库:
    spring.data.mongodb.uri=mongodb://dx:123@localhost:27017/test
  若使用mongodb 2.x，也可以通过如下参数配置，该方式不支持mongodb 3.x。
  spring.data.mongodb.host=localhost spring.data.mongodb.port=27017
  
```
mongodb多数据源等配置：**https://www.cnblogs.com/ityouknow/p/6828919.html**

<a name="ralated-mybatis"></a>  
###7、springboot-mybatis
mybatis 配置相关详解如下：          
```text
   mybatis.config = mybatis 配置文件名称
   mybatis.mapperLocations = mapper xml 文件地址
   mybatis.typeAliasesPackage = 实体类包路径
   mybatis.typeHandlersPackage = type handlers 处理器包路径
   mybatis.check-config-location = 检查 mybatis 配置是否存在，一般命名为 mybatis-config.xml
   mybatis.executorType = 执行模式。默认是 SIMPLE
``` 
**项目启动类需要添加dao接口类扫描**：@MapperScan("xxx.xxx.xxx.dao")

<a name="ralated-mybatis-annotation"></a>  
    
###8、springboot-mybatis-annotation

**Mybatis注解配置，不用xml**

- 在dao层,接口类添加注解 @Mapper、@Select 和 @Results

```text
注意：
（#{cityName}而不是${cityName}） 
  
import com.dx.springbootmybatisannotation.domain.City;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper //标志为mybatis的mapper
public interface CityDao {

    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName 城市名
     */
    @Select("select * from city where city_name=#{cityName}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "provinceId", column = "province_id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "description", column = "description")
    })
    City findByName(@Param("cityName") String cityName);
}
```
- ${}和#{}的区别 
```text
 #{}: 表示一个占位符号,实现向PreparedStatement占位符中设置值(#{}表示一个占位符?),自动进行Java类型到JDBC类型的转换(因此#{}可以有效防止SQL注入),#{}可以接收简单类型或PO属性值,如果parameterType传输的是单个简单类型值,#{}花括号中可以是value或其它名称.
 ${}: 表示拼接SQL串,通过${}可将parameterType内容拼接在SQL中而不进行JDBC类型转换,${}可以接收简单类型或PO属性值,如果parameterType传输的是单个简单类型值,${}花括号中只能是value.
 虽然${}不能防止SQL注入,但有时${}会非常方便(如order by排序,需要将列名通过参数传入SQL,则用ORDER BY ${column},使用#{}则无法实现此功能.
```       
    
<a name="ralated-mybatis-druid"></a>    
###9、springboot-mybatis-druid
案列配置了xml和注解方式

**引入jar包：**
```text
<!-- Druid 数据连接池依赖 -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>${druid}</version>
</dependency>
<properties>
    <druid>1.0.18</druid>
</properties>
```
**application配置文件：**
```text
## master 数据源配置
master.datasource.url=jdbc:mysql://localhost:3306/springbootdb2?useUnicode=true&characterEncoding=utf8
master.datasource.username=root
master.datasource.password=
master.datasource.driverClassName=com.mysql.jdbc.Driver

## cluster 数据源配置
cluster.datasource.url=jdbc:mysql://localhost:3306/springbootdb?useUnicode=true&characterEncoding=utf8
cluster.datasource.username=root
cluster.datasource.password=
cluster.datasource.driverClassName=com.mysql.jdbc.Driver
```
**主数据源配置java文件：**
```java
package com.dx.springbootmybatisdruid.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 主数据源配置
 */
@Configuration
@MapperScan(basePackages = MasterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {

    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.dx.springbootmybatisdruid.dao.master";
//    static final String MAPPER_LOCATION = "classpath:mapper/master/*.xml"; //(采用注解)

    @Value("${master.datasource.url}")
    private String url;

    @Value("${master.datasource.username}")
    private String user;

    @Value("${master.datasource.password}")
    private String password;

    @Value("${master.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "masterDataSource")
    @Primary
    public DataSource masterDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Primary
    @Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager masterTransactionMannager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
//(采用注解)
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources(MasterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}

```
**从数据源配置java文件：**
```java
package com.dx.springbootmybatisdruid.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = ClusterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class ClusterDataSourceConfig {

    // 精确到 cluster 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.dx.springbootmybatisdruid.dao.cluster";
//    static final String MAPPER_LOCATION = "classpath:mapper/cluster/*.xml";

    @Value("${cluster.datasource.url}")
    private String url;

    @Value("${cluster.datasource.username}")
    private String user;

    @Value("${cluster.datasource.password}")
    private String password;

    @Value("${cluster.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "clusterDataSource")
    public DataSource clusterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "clusterTransactionManager")
    public DataSourceTransactionManager clusterTransactionManager() {
        return new DataSourceTransactionManager(clusterDataSource());
    }

    @Bean(name = "clusterSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource clusterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(clusterDataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources(ClusterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}

```
**主数据源数据获取接口：**
```java
package com.dx.springbootmybatisdruid.dao.cluster;

import com.dx.springbootmybatisdruid.domain.City;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CityDao {
      
    // 注意：#{}表示占位符
    @Select("select * from city where city_name=#{cityName}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "provinceId", column = "province_id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "description", column = "description")
    })
    City findByName(@Param("cityName") String cityName);
}

```
<a name="ralated-log"></a>  
###10、springboot-log

**springboot默认日志，logback**

Spring Boot在所有内部日志中使用Commons Logging，但是默认配置也提供了对常用日志的支持，如：Java Util Logging，Log4J, Log4J2和Logback。每种Logger都可以通过配置使用控制台或者文件输出日志内容。

默认的日志输出如下：

```text
2019-04-26 09:54:49.666  INFO 15776 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
```
输出内容元素具体如下：

- 时间日期： 精确到毫秒
- 日志级别： ERROR, WARN, INFO, DEBUG or TRACE
- 进程ID
- 分隔符： --- 标识实际日志的开始
- 线程名： 方括号括起来（可能会截断控制台输出）
- Logger名： 通常使用源代码的类名
- 日志内容

可以自定义日志级别：
- 1、在运行命令后加入--debug标志，如：$ java -jar myapp.jar --debug 
- 2、在application.properties中配置debug=true，该属性置为true的时候，核心Logger（包含嵌入式容器、hibernate、spring）会输出更多内容，但是你自己应用的日志并不会输出为DEBUG级别。 

多彩输出(如果你的终端支持ANSI，设置彩色输出会让日志更具可读性。通过在application.properties中设置spring.output.ansi.enabled参数来支持。)：
- 1、NEVER：禁用ANSI-colored输出（默认项）
- 2、DETECT：会检查终端是否支持ANSI，是的话就采用彩色输出（推荐项）
- 3、ALWAYS：总是使用ANSI-colored格式输出，若终端不支持的时候，会有很多干扰信息，不推荐使用

文件输出： 
 
SpringBoot默认配置只会输出到控制台，并不会记录到文件中，但是我们通常生产环境使用时都需要以文件方式记录。
若要增加文件输出，需要在application.properties中配置logging.file或logging.path属性。

- 1、logging.file，设置文件，可以是绝对路径，也可以是相对路径。如：logging.file=my.log
- 2、logging.path，设置目录，会在该目录下创建spring.log文件，并写入日志内容，如：logging.path=/var/log

**日志文件会在10Mb大小的时候被截断，产生新的日志文件，默认级别为：ERROR、WARN、INFO**

级别控制(配置格式：logging.level.*=LEVEL)： 

eg：
- logging.level.com.didispace=DEBUG：com.didispace包下所有class以DEBUG级别输出
- logging.level.root=WARN：root日志以WARN级别输出

自定义输出格式：
- 1、logging.pattern.console：定义输出到控制台的样式（不支持JDK Logger）
- 2、logging.pattern.file：定义输出到文件的样式（不支持JDK Logger）
    
**springboot使用log4j**

使用log4j(排除springboot默认的logback日志)：
    
- jar包引入：
```text
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <exclusions>
        <exclusion> 
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
 
springboot1.3以下版本
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j</artifactId>
</dependency>
 
springboot1.3以上版本
<dependency>
      <groupId>commons-logging</groupId>
      <artifactId>commons-logging</artifactId>
      <version>1.2</version>
</dependency>

<dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.17</version>
</dependency
  
或者
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```
- 加入配置文件log4j.properties

```text
可自定义输出位置
 
控制台输出
# LOG4J配置
log4j.rootCategory=INFO, stdout

# 控制台输出
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %5p %c{1}:%L - %m%n
 
文件输出
#
log4j.rootCategory=INFO, stdout, file

# root日志输出
log4j.appender.file=org.apache.log4j.DailyRollingFileAppender
log4j.appender.file.file=logs/all.log
log4j.appender.file.DatePattern='.'yyyy-MM-dd
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %5p %c{1}:%L - %m%n
 
分类输出
1、按照不同的package输出
# com.didispace包下的日志配置
log4j.category.com.didispace=DEBUG, didifile
  
# com.didispace下的日志输出
log4j.appender.didifile=org.apache.log4j.DailyRollingFileAppender
log4j.appender.didifile.file=logs/my.log
log4j.appender.didifile.DatePattern='.'yyyy-MM-dd
log4j.appender.didifile.layout=org.apache.log4j.PatternLayout
log4j.appender.didifile.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %5p %c{1}:%L ---- %m%n
  
2、按照不同级别进行分类
log4j.logger.error=errorfile
# error日志输出
log4j.appender.errorfile=org.apache.log4j.DailyRollingFileAppender
log4j.appender.errorfile.file=logs/error.log
log4j.appender.errorfile.DatePattern='.'yyyy-MM-dd
log4j.appender.errorfile.Threshold = ERROR
log4j.appender.errorfile.layout=org.apache.log4j.PatternLayout
log4j.appender.errorfile.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %5p %c{1}:%L - %m%n
```
<a name="ralated-aop-log"></a>     
###11、springboot-aop-log
    
**springboot使用aop统一处理web请求日志**

- 引入依赖：
```text
<!-- web依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
 
<!-- aop依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

**springboot默认开启spring.aop.auto,也就是说只要引入了AOP依赖后，默认已经增加了@EnableAspectJAutoProxy。**

**实现Web层的日志切面**

- 使用@Aspect注解将一个java类定义为切面类
- 使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等。
- 根据需要在切入点不同位置的切入内容
  - 使用@Before在切入点开始处切入内容
  - 使用@After在切入点结尾处切入内容
  - 使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
  - 使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
  - 使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑

**AOP切面优化，优先级测试**
 
- @Order(i)注解来标识切面的优先级。i的值越小，优先级越高
  - 在@Before中优先执行@Order(5)的内容，再执行@Order(10)的内容
  - 在@After和@AfterReturning中优先执行@Order(10)的内容，再执行@Order(5)的内容
    
<a name="ralated-scheduled"></a>   
###12、springboot-scheduled定时任务

- 启动类开启定时任务
```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
  
@SpringBootApplication
@EnableScheduling
public class SpringbootSchedulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSchedulApplication.class, args);
	}

}
```

- 对于@Scheduled的使用可以总结如下几种方式：
    - @Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
    - @Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
    - @Scheduled(initialDelay=1000, fixedRate=5000) ： 第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
    - @Scheduled(cron="*/5 * * * * *") ：通过cron表达式定义规则

<a name="ralated-async"></a>   
###13、springboot-async异步调用

在需要异步执行的方法上加上注解@Async，启动类上配置@EnableAsync开启异步
 
**注： @Async所修饰的函数不要定义为static类型，这样异步调用不会生效(编译不通过)**

异步执行无回调时，如果主程序执行完成则不会理会异步执行的函数，会导致结果不完整

**异步回调：**
使用<code>Future<V></code>来返回异步回调的结果

异步方法类：

```java
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

@Component
public class Task {

    public static Random random = new Random();

    @Async
    public Future<String> T1() throws InterruptedException {
        System.out.println("执行任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        System.out.println("任务一耗时：" + (end - start) + "ms");
        return new AsyncResult<>("任务一完成");
    }

    @Async
    public Future<String> T2() throws InterruptedException {
        System.out.println("执行任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        System.out.println("任务二耗时：" + (end - start) + "ms");
        return new AsyncResult<>("任务二完成");
    }

    @Async
    public Future<String> T3() throws InterruptedException {
        System.out.println("执行任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        System.out.println("任务三耗时：" + (end - start) + "ms");
        return new AsyncResult<>("任务三完成");
    }
}
```

测试类：
     
```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAsyncApplicationTests {

	@Autowired
	private Task task;

    //...
    
	@Test
	public void test2() throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
        Future<String> t1 = task.T1();
        Future<String> t2 = task.T2();
        Future<String> t3 = task.T3();

        while (true){
            if(t1.isDone() && t2.isDone() && t3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
	}
}
```

**java8之后可采用<code>CompletableFuture</code>替换<code>Future</code>**
- 参考使用详解：https://www.jianshu.com/p/6bac52527ca4

异步方法：
```java
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Component
public class Task2 {

    public static Random random = new Random();

    @Async
    public CompletableFuture<String> T1() throws InterruptedException {
        System.out.println("执行任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        System.out.println("任务一耗时：" + (end - start) + "ms");
        return CompletableFuture.supplyAsync(() -> "任务一完成");
    }

    @Async
    public CompletableFuture<String> T2() throws InterruptedException {
        System.out.println("执行任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        System.out.println("任务二耗时：" + (end - start) + "ms");
        return CompletableFuture.supplyAsync(() -> "任务二完成");
    }

    @Async
    public CompletableFuture<String> T3() throws InterruptedException {
        System.out.println("执行任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        System.out.println("任务三耗时：" + (end - start) + "ms");
        return CompletableFuture.supplyAsync(() -> "任务三完成");
    }
}
```
 
测试类：
```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Task2Test {

    @Autowired
    private Task2 task2;

    @Test
    public void t1() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();

        CompletableFuture<String> t1 = task2.T1();
        CompletableFuture<String> t2 = task2.T2();
        CompletableFuture<String> t3 = task2.T3();

        List<CompletableFuture<String>> list = new ArrayList<>();
        list.add(t1);
        list.add(t2);
        list.add(t3);

        for (CompletableFuture<String> future : list) {
            System.out.println(future.get());
        }

        long end = System.currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }
}
```
**CompletableFuture的一些使用:**

```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
  
import java.util.concurrent.CompletableFuture;
  
@SpringBootTest
@RunWith(SpringRunner.class)
public class Task3 {
    /**
     * 获取上一个阶段获取的值，返回转化后的值
     */
    @Test
    public void t1() {
        String result = CompletableFuture.supplyAsync(() -> "Hello").thenApplyAsync(v -> v + " world").join();
        System.out.println(result);
    }

    /**
     * 结合两个CompletionStage的结果，进行转化后返回
     */
    @Test
    public void t2() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "World";
        }), (s1, s2) -> s1 + " " + s2).join();

        System.out.println(result);
    }

    /**
     * 两个CompletionStage，谁计算的快，就用那个CompletionStage的结果进行下一步的处理
     */
    @Test
    public void t3() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hi Boy";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hi Girl";
        }), (s) -> s).join();
        System.out.println(result);
    }

    /**
     * 运行时出现了异常，可以通过exceptionally进行补偿
     */
    @Test
    public void t4() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                throw new RuntimeException("exception test!");
            }

            return "Hi Boy";
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "Hello World";
        }).join();
        System.out.println(result);
    }
}
```

<a name="ralated-async-threadpool"></a>   
###14、springboot-async-threadpool自定义线程池

启动类定义线程池：
```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
public class SpringbootAsyncThreadpoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAsyncThreadpoolApplication.class, args);
	}

	/**
	 * 自定义一个线程池
	 * @EnableAsync 开启异步
	 * @Configuration 自动扫描
	 */
	@EnableAsync
	@Configuration
	class TaskPoolConfig {

		@Bean("taskExecutor")
		public Executor taskExecutor() {
			ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
			executor.setCorePoolSize(10);
			executor.setMaxPoolSize(20);
			executor.setQueueCapacity(200);
			executor.setKeepAliveSeconds(60);
			executor.setThreadNamePrefix("myTaskExecutor-");
			executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
			return executor;
		}
	}
}
```
使用<code>ThreadPoolTaskExecutor</code>自定义线程池参数介绍：
- setCorePoolSize:核心线程数，初始化定义线程数
- setMaxPoolSize:最大线程数，当核心线程数满了，并且缓冲队列满了之后才会申请新的线程
- setQueueCapacity:缓冲队列，当核心线程满了则将任务添加到队列中等待
- setKeepAliveSeconds:空闲时间，超过核心线程数的线程在空闲时间到达之后则会被销毁
- setThreadNamePrefix:线程池名前缀
- setRejectedExecutionHandler:线程池对拒接任务的处理策略   
    - AbortPolicy:默认方式，处理程序遭到拒绝将抛出运行时RejectedExecutionException
    - CallerRunsPolicy:当线程池没有处理能力的时候，该策略会直接在execute方法的调用线程中运行被拒接的任务，如果执行程序已经关闭，则该任务丢弃
    - DiscardPolicy:不能执行的任务将被删除
    - DiscardOldestPolicy:如果执行程序尚未关闭，则位于工作队列头部的任务将被删除，然后重试执行程序（如果再次失败，则重复此过程）
    
使用<code>@Slf4j</code>注解实现日志输出：
- 导入依赖：
```text
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```   
省去<code>private final Logger logger = LoggerFactory.getLogger(XXXX.class);</code>，默认info级别。如果未找到log变量，需要idea下载一个插件lombok，File-Setting-Plugins-lombok

测试类：
```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAsyncThreadpoolApplicationTests {

	@Autowired
	private Task task;

	@Test
	public void contextLoads() throws InterruptedException {
		task.t1();
		task.t2();
		task.t3();

		Thread.currentThread().join();  // 主线程等待子线程
	}

}
```
<a name="ralated-async-threadpool-close"></a>   
###15、springboot-async-threadpool-close线程池正确关闭
结合redis测试：
- 引入依赖：
```text
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```
```java
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Task {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Async("taskExecutor")
    public void doTaskOne() throws Exception {
        log.info("开始做任务一");
        long start = System.currentTimeMillis();
        log.info(stringRedisTemplate.randomKey());
        long end = System.currentTimeMillis();
        log.info("完成任务一，耗时：" + (end - start) + "毫秒");
    }

    @Async("taskExecutor")
    public void doTaskTwo() throws Exception {
        log.info("开始做任务二");
        long start = System.currentTimeMillis();
        log.info(stringRedisTemplate.randomKey());
        long end = System.currentTimeMillis();
        log.info("完成任务二，耗时：" + (end - start) + "毫秒");
    }

    @Async("taskExecutor")
    public void doTaskThree() throws Exception {
        log.info("开始做任务三");
        long start = System.currentTimeMillis();
        log.info(stringRedisTemplate.randomKey());
        long end = System.currentTimeMillis();
        log.info("完成任务三，耗时：" + (end - start) + "毫秒");
    }

}
```
配置类：
```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;

@SpringBootApplication
public class SpringbootAsyncThreadpoolCloseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAsyncThreadpoolCloseApplication.class, args);
	}

	@EnableAsync
	@Configuration
	class TaskPoolConfig {
		@Bean("taskExecutor")
		public Executor taskExecutor() {
			ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
			executor.setPoolSize(20);
			executor.setThreadNamePrefix("myExecutor-");
			executor.setWaitForTasksToCompleteOnShutdown(true); //等待异步任务全部完成再销毁线程
			executor.setAwaitTerminationSeconds(60);    //配合setWaitForTasksToCompleteOnShutdown的使用，在这段时间内没有执行完的线程都将销毁
			return executor;
		}
	}
}
```
测试类：
```java
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAsyncThreadpoolCloseApplicationTests {

	@Autowired
	private Task task;

	@Test
	@SneakyThrows
	public void test() {

		for (int i = 0; i < 10000; i++) {
			task.doTaskOne();
			task.doTaskTwo();
			task.doTaskThree();
		}
	}

}
```
<a name="ralated-security"></a>   
###16、springboot-security安全控制
引入依赖：
  
```text
<!--thymeleaf模板依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
<!--security依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

配置文件：
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * spring security配置
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    /**
     * Spring security 5.0中新增了多种加密方式，也改变了密码的格式。会报错:There is no PasswordEncoder mapped for the id "null"
     * @return
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
//                .withUser("dx").password("123").roles("USER");
                .passwordEncoder(new BCryptPasswordEncoder())  // 设置加密方式
                .withUser("dx")
                .password(new BCryptPasswordEncoder().encode("123"))
                .roles("USER");
    }
}
```
注：spring security抛出异常There is no PasswordEncoder mapped for the id “null”，在Spring security5.0中新增了多种加密方式，也改变了密码格式
 现如今Spring Security中密码的存储格式是“{id}…………”。前面的id是加密方式，id可以是bcrypt、sha256等，后面跟着的是加密后的密码
官方推荐使用加密方式：**BCrypt**

<a name="ralated-cache-ehcache"></a>   
###17、springboot-cache-ehcache缓存支持注解配置与EhCache使用

导入依赖：
```text
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>${mysql-connector}</version>
    </dependency>

    <!-- Spring Boot Mybatis 依赖 -->
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>${mybatis-spring-boot}</version>
    </dependency>

    <!-- 引入cache依赖 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-cache</artifactId>
    </dependency>
</dependencies>

<properties>
    <mybatis-spring-boot>1.2.0</mybatis-spring-boot>
    <mysql-connector>5.1.39</mysql-connector>
</properties>
```
在启动类开启缓存功能：@EnableCaching
```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
```
在数据访问接口中增加缓存配置注解：
```java
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User, Long>{
    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

    @Cacheable
    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);
}
```

Cache注解详解：

引入依赖：
```text
<!-- 引入ehcache依赖 -->
<dependency>
    <groupId>net.sf.ehcache</groupId>
    <artifactId>ehcache</artifactId>
</dependency>
```
resource下添加配置ehcache.xml文件，也可以通过application.properties的属性来指定：
```text
spring.cache.ehcache.config=classpath:config/another-config.xml
```

```xml
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="ehcache.xsd">
   
    <!-- cache里的name和 UserRepository访问接口内cacheNames的名字相同才可以做缓存-->
    
    <cache name="my"
           maxEntriesLocalHeap="200"
           timeToLiveSeconds="600">
    </cache>

</ehcache>
```
注：
- cache里的name和 UserRepository访问接口内cacheNames的名字相同才可以做缓存
- jpa操作数据更新时，需要添加<code>@Modifying</code>注解，否则抛出InvalidDataAccessApiUsageException异常
- jpa操作数据更新时，需要添加事务<code>@Transactional</code>注解，否则抛出InvalidDataAccessApiUsageException异常

<code>@Cacheable</code>的使用：
    
- value属性是必须指定的，其表示当前方法的返回值是会被缓存在哪个Cache上的，对应Cache的名称。其可以是一个Cache也可以是多个Cache，当需要指定多个Cache时其是一个数组
- 自定义key策略。key="#..."，支持SpringEL表达式，使用方法参数时我们可以直接使用“#参数名”或者“#p参数index”   

<a name="ralated-cache-redis"></a>   
###18、springboot-cache-redis集中缓存Redis

**Redis5种存储类型总结：** 

redis的5中存储类型
- string：字符串
- list：列表
- map（hash）：哈希
- set：集合
- stored-set：有序集合

redis的string类型
- 可表示3种类型，字符串、整数、浮点数
- value内部以int、sds作为结构存储。int存放整型，sds存放字节、字符串、浮点型
- sds内部结构：
    - 数组存储字符串内容，长度大于存储内容，以“\0”（C标准库）结尾，预留几个空区（free区域），当添加的长度小于free区域，则sds不会重新申请内存，直接使用free区域
    - 扩容：当对字符串的操作完成后预期长度小于1M，扩容后的buf数组大小=预期长度*2+1；若大于1M，则buf总是会预留出1M的free空间
    - value对象通常具有两个内存部分：redisObject部分和redisObject的ptr指向的sds部分。创建value对象时，通常需要为redisObject和sds申请两次内存。
    单对于短小的字符串，可以把两者连续存放，所以可以一次性把两者的内存一起申请了。reedis3.0新增的embstr，如果字符串长度小于39字节就用embstr对象否则是传统的raw对象
    
redis的list类型
- list类型的value对象内部以linkedlist或ziplist承载。当list的元素个数和单个元素的长度较小时，redis会采用ziplist实现以减少内存占用，否则采用linkedlist结构
- linkedlist内部实现是双向链表。在list中定义了头尾元素指针和列表的长度，是的pop/push操作、llen操作的复杂度为O(1)。由于是链表，lindex类的操作复杂度仍然是O(N)
- ziplist的内部结构
    - 所有内容被放置在连续的内存中。其中zlbytes表示ziplist的总长度，zltail指向最末元素，zllen表示元素个数，entry表示元素自身内容，zlend作为ziplist定界符
    - rpush、rpop、llen，复杂度为O(1);lpush/pop操作由于涉及全列表元素的移动，复杂度为O(N)rpush、rpop、llen，复杂度为O(1);lpush/pop操作由于涉及全列表元素的移动，复杂度为O(N)
    
redis的map类型
- map又叫hash。map内部的key和value不能再嵌套map了，只能是string类型：整形、浮点型和字符串
- map主要由hashtable和ziplist两种承载方式实现，对于数据量较小的map，采用ziplist实现
- hashtable内部结构 
    - 主要分为三层，自底向上分别是dictEntry、dictht、dict
    - dictEntry：管理一个key-value对，同时保留同一个桶中相邻元素的指针，一次维护哈希桶的内部链
    - dictht：维护哈希表的所有桶链
    - dict：当dictht需要扩容/缩容时，用于管理dictht的迁移
    - 哈希表的核心结构是dictht，它的table字段维护着hash桶，它是一个数组，每个元素指向桶的第一个元素（dictEntry）
    - set值的流程：先通过MurmurHash算法求出key的hash值，再对桶的个数取模，得到key对应的桶，再进入桶中，
    遍历全部entry，判定是否已有相同的key，如果没有，则将新key对应的键值对插入到桶头，并且更新dictht的used数量，
    used表示hash表中已经存了多少元素。由于每次插入都要遍历hash桶中的全部entry，所以当桶中entry很多时，性能会线性下降
    - 扩容：通过负载因子判定是否需要增加桶数。负载因子=哈希表中已有元素/哈希桶数的比值。有两个阈值，小于1一定不扩容；
    大于5一定扩容。扩容时新的桶数目是现有桶的2n倍
    - 缩容：负载因子的阈值是0.1
    - 扩/缩容通过新建哈希表的方式实现。即扩容时，会并存两个哈希表，一个是源表，一个是目标表。通过将源表的桶逐步迁移到目标表，
    以数据迁移的方式实现扩容，迁移完成后目标表覆盖源表。迁移过程中，首先访问源表，如果发现key对应的源表桶已完成迁移，则重新访问目标表，否则在源表中操作
    - redis是单线程处理请求，迁移和访问的请求在相同线程内进行，所以不会存在并发性问题
- ziplist内部结构   
    - 和list的ziplist实现类似。不同的是，map对应的ziplist的entry个数总是2的整数倍，奇数存放key，偶数存放value
    - ziplist实现下，由哈希遍历变成了链表的顺序遍历，复杂度变成O(N)
 
redis的set类型
- set以intset或hashtable来存储。hashtable中的value永远为null，当set中只包含整数型的元素时，则采用intset
- intset的内部结构 
    - 核心元素是一个字节数组，从小到大有序存放着set的元素
    - 由于元素有序排列，所以set的获取操作采用二分查找方式实现，复杂度O(log(N))。进行插入时，首先通过二分查找得到本次插入的位置，再对元素进行扩容，
    再将预计插入位置之后的所有元素向右移动一个位置，最后插入元素，插入复杂度为O(N)。删除类似
 
- redis的sorted-set类型
    - 类似map是一个key-value对，但是有序的。value是一个浮点数，称为score，内部是按照score从小到大排序
    - 内部结构以ziplist或skiplist+hashtable来实现

**参考文档：<a href="https://blog.csdn.net/wuyangyang555/article/details/82152005">redis原理总结</a>**

<a name="ralated-mailsend"></a>   
###19、springboot-javaMailSender邮件发送

导入依赖：
```text
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```

配置文件配置:
```properties
spring.mail.host=smtp.qq.com
spring.mail.username=490468784@qq.com
#这里的密码需要邮件开启smtp、pop3协议返回的一个授权码
spring.mail.password=xxxxxxx
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
```

本地测试：
```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJavamailsendApplicationTests {

	@Autowired
	private JavaMailSender mailSender;

	/**
	 * 发送简单的邮件
	 */
	@Test
	public void sendMail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("490468784@qq.com");
		message.setTo("490468784@qq.com");
		message.setSubject("测试邮件");
		message.setText("你好，秀儿");

		mailSender.send(message);
	}

	/**
	 * 发送带附件的邮件
	 */
	@Test
	public void sendAttachmentsMail() throws MessagingException {
		File file = new File("C:\\Users\\wudongchun\\Desktop\\测试文本.txt");
		FileSystemResource systemResource = new FileSystemResource(file);

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("490468784@qq.com");
		helper.setTo("490468784@qq.com");
		helper.setSubject("测试邮件(带附件)");
		helper.setText("你好，秀儿");
//		helper.addAttachment("附件1.txt", file);
		helper.addAttachment("附件1.txt", systemResource);

		mailSender.send(mimeMessage);
	}

	/**
	 * 发送正文静态资源邮件
	 */
	@Test
	public void sendInLineMail() throws MessagingException {
		FileSystemResource systemResource = new FileSystemResource(new File("C:\\Users\\wudongchun\\Desktop\\测试.jpg"));

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("490468784@qq.com");
		helper.setTo("490468784@qq.com");
		helper.setSubject("测试邮件(静态资源)");
		helper.setText("hello，<html><body><img src=\"cid:pic\"></body></html>", true);
		helper.addInline("pic", systemResource);

		mailSender.send(mimeMessage);
	}

	
}
```

模板邮件使用：

导入依赖：
```text
<!-- 较新版本springboot不提供velocity集成，单独导入依赖，模板邮件依赖 -->
<dependency>
    <groupId>org.apache.velocity</groupId>
    <artifactId>velocity</artifactId>
    <version>1.7</version>
</dependency>
<dependency>
    <groupId>org.apache.velocity</groupId>
    <artifactId>velocity-tools</artifactId>
    <version>2.0</version>
</dependency>
```

使用Velocity模板邮件需要配置VelocityEngine：
```java
import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * velocity配置类
 */
@Configuration
public class VelocityEngineConfig {
   
    @Bean
    public VelocityEngine velocityEngine() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("input.encoding", "UTF-8");
        properties.setProperty("output.encoding", "UTF-8");
        //设置velocity资源加载方式为class
        properties.setProperty("resource.loader", "class");
        //设置velocity资源加载方式为class时的处理类
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        VelocityEngine velocityEngine = new VelocityEngine(properties);
        return velocityEngine;
    }
}
```

测试类：
```java
/**
 * 模板邮件
 */

@Autowired
private VelocityEngine velocityEngine;

@Test
public void snedTemplateMail() throws MessagingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
   
    VelocityContext contex = new VelocityContext();
    contex.put("username", "秀儿");
    StringWriter stringWriter = new StringWriter();
    // 需要注意第1个参数要全路径，否则会抛异常
    velocityEngine.mergeTemplate("/templates/template.vm", "UTF-8", contex, stringWriter);
    
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
    helper.setFrom("490468784@qq.com");
    helper.setTo("490468784@qq.com");
    helper.setSubject("模板邮件测试");
    helper.setText(stringWriter.toString(), true);
    
    mailSender.send(mimeMessage);
}
```

<a name="ralated-rabbitmq-mytest"></a>   
###20、springboot-rabbitmq-mytest个人测试RabbitMQ消息队列

交换机四种调度策略：
- Direct:
    - 处理路由键。需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。这是一个完整的匹配。如果一个队列绑定到该交换机上要求路由键 “test”，则只有被标记为“test”的消息才被转发，不会转发test.aaa，也不会转发dog.123，只会转发test。
- fanout:
    - 不处理路由，只要有消费发送到交换机，所有绑定了该交换机的队列都会接收到消息。Fanout交换机转发消息是最快的。
- topic:
    - 将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。因此“abc.#”能够匹配到“abc.def.ghi”，但是“abc.*” 只会匹配到“abc.def”
- headers:
    - 不处理路由键。而是根据发送的消息内容中的headers属性进行匹配。在绑定Queue与Exchange时指定一组键值对；当消息发送到RabbitMQ时会取到该消息的headers与Exchange绑定时指定的键值对进行匹配；如果完全匹配则消息会路由到该队列，否则不会路由到该队列。headers属性是一个键值对，可以是Hashtable，键值对的值可以是任何类型。而fanout，direct，topic 的路由键都需要要字符串形式的。

匹配规则x-match有下列两种类型：

x-match = all ：表示所有的键值对都匹配才能接受到消息

x-match = any ：表示只要有键值对匹配就能接受到消息
  
<a name="ralated-cas"></a>   
###21、CAS

```text
CAS机制:CAS是英文单词Compare and Swap的缩写。
        
CAS机制中使用了3个基本操作数：内存地址V，旧的预期值A，要修改的新值B。
  
CAS的缺点：

1） CPU开销过大

在并发量比较高的情况下，如果许多线程反复尝试更新某一个变量，却又一直更新不成功，循环往复，会给CPU带来很到的压力。

2） 不能保证代码块的原子性

CAS机制所保证的知识一个变量的原子性操作，而不能保证整个代码块的原子性。比如需要保证3个变量共同进行原子性的更新，就不得不使用synchronized了。

3） ABA问题

这是CAS机制最大的问题所在。
  
ABA问题可以添加版本号做两次操作的判断
    
在Java中，AtomicStampedReference类就实现了用版本号作比较额CAS机制。   
 
1. java语言CAS底层如何实现？

利用unsafe提供的原子性操作方法。

2.什么事ABA问题？怎么解决？

当一个值从A变成B，又更新回A，普通CAS机制会误判通过检测。

利用版本号比较可以有效解决ABA问题。
```

<a name="ralated-java-rabbitmq-test1"></a>   
###22、java-rabbitmq-test1

测试java对rabbitmq三种策略的支持
详见spring-rabbitmq-test项目

<a name="ralated-rabbitmq-test2"></a>   
###23、springboot-rabbitmq-test2

注意：
- 非guest用户。配置文件中需要设置用户访问的virtual-host。在可视化管理工具中为该用户设置权限
```properties
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=dx
spring.rabbitmq.password=123
#非guest用户需要设置权限
spring.rabbitmq.virtual-host=testhost
```
springboot-rabbitmq简单测试4种策略

