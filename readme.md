## 目录

- 1[springboot整合redis](#ralated-redis)
- 2[springboot配置文件](#ralated-aplication)
- 3[springboot-Restful与swagger2文档维护](#ralated-swagger2)
- 4[springboot-data-jpa](#ralated-data-jpa)
- 5[springboot-mutildatasource多数据源配置](#ralated-mutildatasource)

<a name="ralated-redis"></a>
- 1[springboot整合redis]()
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
- 2[springboot配置文件]()
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
- 3[springboot-Restful与swagger2文档维护]()
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
- 4[springboot-data-jpa]()
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
- 5[springboot-mutildatasource多数据源配置]()

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
    
       