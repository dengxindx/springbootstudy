## 目录

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

<a name="ralated-redis"></a>
- ###1、[springboot整合redis]()
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
- ###2、[springboot配置文件]()
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
- ###3、[springboot-Restful与swagger2文档维护]()
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
- ###4、[springboot-data-jpa]()
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
- ###5、[springboot-mutildatasource多数据源配置]()

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
- ###6、[springboot-mongodb]()
       
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
- ###7、[springboot-mybatis]() 
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
    
- ###8、[springboot-mybatis-annotation]()

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
- ###9、[springboot-mybatis-druid]()  
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
- ###10、[springboot-log]()  

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
- ###10、[springboot-aop-log]() 
    
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
    
    
    