MemberApiControllerTest.java 테스트 결과

08:14:40.495 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class [shane.blog.domain.member.MemberApiControllerTest]: MemberApiControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
08:14:40.730 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper -- Found @SpringBootConfiguration shane.blog.ReactSpringApplication for test class shane.blog.domain.member.MemberApiControllerTest
08:14:41,615 |-INFO in ch.qos.logback.core.model.processor.conditional.IfModelHandler - Condition [property("os.name").contains("Linux")] evaluated to false on line 10
08:14:41,619 |-INFO in ch.qos.logback.core.model.processor.AppenderModelHandler - Processing appender named [console]
08:14:41,620 |-INFO in ch.qos.logback.core.model.processor.AppenderModelHandler - About to instantiate appender of type [ch.qos.logback.core.ConsoleAppender]
08:14:41,628 |-INFO in ch.qos.logback.core.model.processor.ImplicitModelHandler - Assuming default type [ch.qos.logback.classic.encoder.PatternLayoutEncoder] for [encoder] property
08:14:41,662 |-WARN in ch.qos.logback.core.model.processor.AppenderModelHandler - Appender named [console-infolog] not referenced. Skipping further processing.
08:14:41,662 |-INFO in ch.qos.logback.core.model.processor.AppenderModelHandler - Processing appender named [file]
08:14:41,662 |-INFO in ch.qos.logback.core.model.processor.AppenderModelHandler - About to instantiate appender of type [ch.qos.logback.core.rolling.RollingFileAppender]
08:14:41,675 |-INFO in c.q.l.core.rolling.SizeAndTimeBasedRollingPolicy@488669101 - setting totalSizeCap to 10 GB
08:14:41,680 |-INFO in c.q.l.core.rolling.SizeAndTimeBasedRollingPolicy@488669101 - Archive files will be limited to [1 GB] each.
08:14:41,684 |-INFO in c.q.l.core.rolling.SizeAndTimeBasedRollingPolicy@488669101 - No compression will be used
08:14:41,686 |-INFO in c.q.l.core.rolling.SizeAndTimeBasedRollingPolicy@488669101 - Will use the pattern C:/logs/react-spring-%d{yyyy-MM-dd}.%i.log for the active file
08:14:41,689 |-INFO in ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP@512d4583 - The date pattern is 'yyyy-MM-dd' from file name pattern 'C:/logs/react-spring-%d{yyyy-MM-dd}.%i.log'.
08:14:41,689 |-INFO in ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP@512d4583 - Roll-over at midnight.
08:14:41,710 |-INFO in ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP@512d4583 - Setting initial period to 2024-05-09T08:05:24.942Z
08:14:41,714 |-INFO in ch.qos.logback.core.model.processor.ImplicitModelHandler - Assuming default type [ch.qos.logback.classic.encoder.PatternLayoutEncoder] for [encoder] property
08:14:41,717 |-INFO in ch.qos.logback.core.rolling.RollingFileAppender[file] - Active log file name: C:/logs/react-spring.log
08:14:41,718 |-INFO in ch.qos.logback.core.rolling.RollingFileAppender[file] - File property is set to [C:/logs/react-spring.log]
08:14:41,720 |-INFO in ch.qos.logback.classic.model.processor.LoggerModelHandler - Setting level of logger [org] to ERROR
08:14:41,720 |-INFO in ch.qos.logback.classic.jul.LevelChangePropagator@5e840abf - Propagating ERROR level on Logger[org] onto the JUL framework
08:14:41,724 |-INFO in ch.qos.logback.classic.model.processor.LoggerModelHandler - Setting additivity of logger [org] to false
08:14:41,724 |-INFO in ch.qos.logback.core.model.processor.AppenderRefModelHandler - Attaching appender named [console] to Logger[org]
08:14:41,724 |-INFO in ch.qos.logback.core.model.processor.AppenderRefModelHandler - Attaching appender named [file] to Logger[org]
08:14:41,724 |-INFO in ch.qos.logback.classic.model.processor.LoggerModelHandler - Setting level of logger [shane.blog] to DEBUG
08:14:41,724 |-INFO in ch.qos.logback.classic.jul.LevelChangePropagator@5e840abf - Propagating DEBUG level on Logger[shane.blog] onto the JUL framework
08:14:41,724 |-INFO in ch.qos.logback.classic.model.processor.LoggerModelHandler - Setting level of logger [jdbc.connection] to ERROR
08:14:41,724 |-INFO in ch.qos.logback.classic.jul.LevelChangePropagator@5e840abf - Propagating ERROR level on Logger[jdbc.connection] onto the JUL framework
08:14:41,725 |-INFO in ch.qos.logback.classic.model.processor.LoggerModelHandler - Setting level of logger [jdbc.sqltiming] to ERROR
08:14:41,725 |-INFO in ch.qos.logback.classic.jul.LevelChangePropagator@5e840abf - Propagating ERROR level on Logger[jdbc.sqltiming] onto the JUL framework
08:14:41,725 |-INFO in ch.qos.logback.classic.model.processor.LoggerModelHandler - Setting level of logger [jdbc.sqlonly] to DEBUG
08:14:41,725 |-INFO in ch.qos.logback.classic.jul.LevelChangePropagator@5e840abf - Propagating DEBUG level on Logger[jdbc.sqlonly] onto the JUL framework
08:14:41,725 |-INFO in ch.qos.logback.classic.model.processor.LoggerModelHandler - Setting level of logger [jdbc.audit] to ERROR
08:14:41,725 |-INFO in ch.qos.logback.classic.jul.LevelChangePropagator@5e840abf - Propagating ERROR level on Logger[jdbc.audit] onto the JUL framework
08:14:41,725 |-INFO in ch.qos.logback.classic.model.processor.LoggerModelHandler - Setting level of logger [jdbc.resultset] to ERROR
08:14:41,725 |-INFO in ch.qos.logback.classic.jul.LevelChangePropagator@5e840abf - Propagating ERROR level on Logger[jdbc.resultset] onto the JUL framework
08:14:41,725 |-INFO in ch.qos.logback.classic.model.processor.LoggerModelHandler - Setting level of logger [jdbc.resultsettable] to DEBUG
08:14:41,725 |-INFO in ch.qos.logback.classic.jul.LevelChangePropagator@5e840abf - Propagating DEBUG level on Logger[jdbc.resultsettable] onto the JUL framework
08:14:41,725 |-INFO in ch.qos.logback.classic.model.processor.RootLoggerModelHandler - Setting level of ROOT logger to DEBUG
08:14:41,726 |-INFO in ch.qos.logback.core.model.processor.AppenderRefModelHandler - Attaching appender named [console] to Logger[ROOT]
08:14:41,726 |-INFO in ch.qos.logback.core.model.processor.AppenderRefModelHandler - Attaching appender named [file] to Logger[ROOT]
08:14:41,726 |-INFO in ch.qos.logback.core.model.processor.DefaultProcessor@b558294 - End of configuration.
08:14:41,726 |-INFO in org.springframework.boot.logging.logback.SpringBootJoranConfigurator@bb095 - Registering current configuration as safe fallback point
08:14:41,751 |-INFO in ch.qos.logback.classic.jul.LevelChangePropagator@5e840abf - Propagating TRACE level on Logger[org.hibernate.type.descriptor.sql.BasicBinder] onto the JUL framework
08:14:41,752 |-INFO in ch.qos.logback.classic.jul.LevelChangePropagator@5e840abf - Propagating DEBUG level on Logger[org.hibernate.type.descriptor.sql] onto the JUL framework
08:14:41,752 |-INFO in ch.qos.logback.classic.jul.LevelChangePropagator@5e840abf - Propagating DEBUG level on Logger[org.hibernate.SQL] onto the JUL framework

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v3.1.11)

2024-05-10 08:14:41.842 [main] [INFO] s.b.d.m.MemberApiControllerTest - Starting MemberApiControllerTest using Java 17.0.6 with PID 23744 (started by Shane in C:\Users\DeltaX_20\Documents\Workspace\react-spring\backend)
08:14:41,843 |-INFO in c.q.l.co.rolling.helper.RenameUtil - Renaming file [C:\logs\react-spring.log] to [C:\logs\react-spring-2024-05-09.0.log]
08:14:41,847 |-INFO in c.q.l.core.rolling.helper.TimeBasedArchiveRemover - first clean up after appender initialization
08:14:41,848 |-INFO in c.q.l.core.rolling.helper.TimeBasedArchiveRemover - Multiple periods, i.e. 32 periods, seem to have elapsed. This is expected at application start.
2024-05-10 08:14:41.849 [main] [DEBUG] s.b.d.m.MemberApiControllerTest - Running with Spring Boot v3.1.11, Spring v6.0.19
08:14:41,849 |-INFO in c.q.l.core.rolling.helper.TimeBasedArchiveRemover - deleting C:\logs\react-spring-2024-04-29.0.log
2024-05-10 08:14:41.851 [main] [INFO] s.b.d.m.MemberApiControllerTest - No active profile set, falling back to 1 default profile: "default"
08:14:41,876 |-INFO in c.q.l.core.rolling.helper.TimeBasedArchiveRemover - Removed  0 Bytes of files
2024-05-10 08:14:43.702 [main] [DEBUG] c.z.h.HikariConfig - Driver class net.sf.log4jdbc.sql.jdbcapi.DriverSpy found in Thread context class loader jdk.internal.loader.ClassLoaders$AppClassLoader@659e0bfd
2024-05-10 08:14:43.722 [main] [DEBUG] l.debug - log4jdbc-logj2 properties initialization...
2024-05-10 08:14:43.722 [main] [DEBUG] l.debug - Using logger: net.sf.log4jdbc.log.slf4j.Slf4jSpyLogDelegator
2024-05-10 08:14:43.722 [main] [DEBUG] l.debug - x log4jdbc.debug.stack.prefix is not defined
2024-05-10 08:14:43.722 [main] [DEBUG] l.debug - x log4jdbc.sqltiming.warn.threshold is not defined
2024-05-10 08:14:43.722 [main] [DEBUG] l.debug - x log4jdbc.sqltiming.error.threshold is not defined
2024-05-10 08:14:43.722 [main] [DEBUG] l.debug - x log4jdbc.dump.booleanastruefalse is not defined (using default value false)
2024-05-10 08:14:43.722 [main] [DEBUG] l.debug -   log4jdbc.dump.sql.maxlinelength = 0
2024-05-10 08:14:43.722 [main] [DEBUG] l.debug - x log4jdbc.dump.fulldebugstacktrace is not defined (using default value false)
2024-05-10 08:14:43.722 [main] [DEBUG] l.debug - x log4jdbc.statement.warn is not defined (using default value false)
2024-05-10 08:14:43.722 [main] [DEBUG] l.debug - x log4jdbc.dump.sql.select is not defined (using default value true)
2024-05-10 08:14:43.722 [main] [DEBUG] l.debug - x log4jdbc.dump.sql.insert is not defined (using default value true)
2024-05-10 08:14:43.722 [main] [DEBUG] l.debug - x log4jdbc.dump.sql.update is not defined (using default value true)
2024-05-10 08:14:43.722 [main] [DEBUG] l.debug - x log4jdbc.dump.sql.delete is not defined (using default value true)
2024-05-10 08:14:43.722 [main] [DEBUG] l.debug - x log4jdbc.dump.sql.create is not defined (using default value true)
2024-05-10 08:14:43.722 [main] [DEBUG] l.debug - x log4jdbc.dump.sql.addsemicolon is not defined (using default value false)
2024-05-10 08:14:43.722 [main] [DEBUG] l.debug - x log4jdbc.auto.load.popular.drivers is not defined (using default value true)
2024-05-10 08:14:43.723 [main] [DEBUG] l.debug - x log4jdbc.drivers is not defined
2024-05-10 08:14:43.723 [main] [DEBUG] l.debug - x log4jdbc.trim.sql is not defined (using default value true)
2024-05-10 08:14:43.723 [main] [DEBUG] l.debug - x log4jdbc.trim.sql.extrablanklines is not defined (using default value true)
2024-05-10 08:14:43.723 [main] [DEBUG] l.debug - x log4jdbc.suppress.generated.keys.exception is not defined (using default value false)
2024-05-10 08:14:43.723 [main] [DEBUG] l.debug - log4jdbc-logj2 properties initialization done.
2024-05-10 08:14:43.723 [main] [DEBUG] l.debug - DriverSpy intialization...
2024-05-10 08:14:43.734 [main] [DEBUG] l.debug - WARNING!  log4jdbc couldn't find any underlying jdbc drivers.
2024-05-10 08:14:43.736 [main] [DEBUG] l.debug - DriverSpy intialization done.
2024-05-10 08:14:43.743 [main] [DEBUG] c.z.h.HikariConfig - HikariPool-1 - configuration:
2024-05-10 08:14:43.748 [main] [DEBUG] c.z.h.HikariConfig - allowPoolSuspension.............false
2024-05-10 08:14:43.749 [main] [DEBUG] c.z.h.HikariConfig - autoCommit......................true
2024-05-10 08:14:43.749 [main] [DEBUG] c.z.h.HikariConfig - catalog.........................none
2024-05-10 08:14:43.749 [main] [DEBUG] c.z.h.HikariConfig - connectionInitSql...............none
2024-05-10 08:14:43.749 [main] [DEBUG] c.z.h.HikariConfig - connectionTestQuery.............none
2024-05-10 08:14:43.749 [main] [DEBUG] c.z.h.HikariConfig - connectionTimeout...............30000
2024-05-10 08:14:43.750 [main] [DEBUG] c.z.h.HikariConfig - dataSource......................none
2024-05-10 08:14:43.750 [main] [DEBUG] c.z.h.HikariConfig - dataSourceClassName.............none
2024-05-10 08:14:43.750 [main] [DEBUG] c.z.h.HikariConfig - dataSourceJNDI..................none
2024-05-10 08:14:43.750 [main] [DEBUG] c.z.h.HikariConfig - dataSourceProperties............{password=<masked>}
2024-05-10 08:14:43.751 [main] [DEBUG] c.z.h.HikariConfig - driverClassName................."net.sf.log4jdbc.sql.jdbcapi.DriverSpy"
2024-05-10 08:14:43.751 [main] [DEBUG] c.z.h.HikariConfig - exceptionOverrideClassName......none
2024-05-10 08:14:43.751 [main] [DEBUG] c.z.h.HikariConfig - healthCheckProperties...........{}
2024-05-10 08:14:43.751 [main] [DEBUG] c.z.h.HikariConfig - healthCheckRegistry.............none
2024-05-10 08:14:43.751 [main] [DEBUG] c.z.h.HikariConfig - idleTimeout.....................600000
2024-05-10 08:14:43.751 [main] [DEBUG] c.z.h.HikariConfig - initializationFailTimeout.......1
2024-05-10 08:14:43.752 [main] [DEBUG] c.z.h.HikariConfig - isolateInternalQueries..........false
2024-05-10 08:14:43.753 [main] [DEBUG] c.z.h.HikariConfig - jdbcUrl.........................jdbc:log4jdbc:mariadb://localhost:3306/react-spring?serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true
2024-05-10 08:14:43.753 [main] [DEBUG] c.z.h.HikariConfig - keepaliveTime...................0
2024-05-10 08:14:43.753 [main] [DEBUG] c.z.h.HikariConfig - leakDetectionThreshold..........0
2024-05-10 08:14:43.754 [main] [DEBUG] c.z.h.HikariConfig - maxLifetime.....................1800000
2024-05-10 08:14:43.754 [main] [DEBUG] c.z.h.HikariConfig - maximumPoolSize.................10
2024-05-10 08:14:43.754 [main] [DEBUG] c.z.h.HikariConfig - metricRegistry..................none
2024-05-10 08:14:43.755 [main] [DEBUG] c.z.h.HikariConfig - metricsTrackerFactory...........none
2024-05-10 08:14:43.755 [main] [DEBUG] c.z.h.HikariConfig - minimumIdle.....................10
2024-05-10 08:14:43.755 [main] [DEBUG] c.z.h.HikariConfig - password........................<masked>
2024-05-10 08:14:43.755 [main] [DEBUG] c.z.h.HikariConfig - poolName........................"HikariPool-1"
2024-05-10 08:14:43.755 [main] [DEBUG] c.z.h.HikariConfig - readOnly........................false
2024-05-10 08:14:43.755 [main] [DEBUG] c.z.h.HikariConfig - registerMbeans..................false
2024-05-10 08:14:43.756 [main] [DEBUG] c.z.h.HikariConfig - scheduledExecutor...............none
2024-05-10 08:14:43.756 [main] [DEBUG] c.z.h.HikariConfig - schema..........................none
2024-05-10 08:14:43.756 [main] [DEBUG] c.z.h.HikariConfig - threadFactory...................internal
2024-05-10 08:14:43.756 [main] [DEBUG] c.z.h.HikariConfig - transactionIsolation............default
2024-05-10 08:14:43.756 [main] [DEBUG] c.z.h.HikariConfig - username........................"root"
2024-05-10 08:14:43.756 [main] [DEBUG] c.z.h.HikariConfig - validationTimeout...............5000
2024-05-10 08:14:43.758 [main] [INFO] c.z.h.HikariDataSource - HikariPool-1 - Starting...
2024-05-10 08:14:43.912 [main] [INFO] c.z.h.p.HikariPool - HikariPool-1 - Added connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@6806468e
2024-05-10 08:14:43.915 [main] [INFO] c.z.h.HikariDataSource - HikariPool-1 - Start completed.
2024-05-10 08:14:44.020 [HikariPool-1 housekeeper] [DEBUG] c.z.h.p.HikariPool - HikariPool-1 - Pool stats (total=1, active=0, idle=1, waiting=0)
2024-05-10 08:14:44.027 [HikariPool-1 connection adder] [DEBUG] c.z.h.p.HikariPool - HikariPool-1 - Added connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@29fa613a
2024-05-10 08:14:44.055 [HikariPool-1 connection adder] [DEBUG] c.z.h.p.HikariPool - HikariPool-1 - Added connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@7cc9dd57
2024-05-10 08:14:44.069 [HikariPool-1 connection adder] [DEBUG] c.z.h.p.HikariPool - HikariPool-1 - Added connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@2f2f0a62
2024-05-10 08:14:44.085 [HikariPool-1 connection adder] [DEBUG] c.z.h.p.HikariPool - HikariPool-1 - Added connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@2ff7306a
2024-05-10 08:14:44.102 [HikariPool-1 connection adder] [DEBUG] c.z.h.p.HikariPool - HikariPool-1 - Added connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@30afda5b
2024-05-10 08:14:44.117 [HikariPool-1 connection adder] [DEBUG] c.z.h.p.HikariPool - HikariPool-1 - Added connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@39d9c878
2024-05-10 08:14:44.133 [HikariPool-1 connection adder] [DEBUG] c.z.h.p.HikariPool - HikariPool-1 - Added connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@1b032d78
2024-05-10 08:14:44.149 [HikariPool-1 connection adder] [DEBUG] c.z.h.p.HikariPool - HikariPool-1 - Added connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@57cd902
2024-05-10 08:14:44.164 [HikariPool-1 connection adder] [DEBUG] c.z.h.p.HikariPool - HikariPool-1 - Added connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@76cce9e0
2024-05-10 08:14:44.179 [HikariPool-1 connection adder] [DEBUG] c.z.h.p.HikariPool - HikariPool-1 - After adding stats (total=10, active=0, idle=10, waiting=0)
2024-05-10 08:14:44.934 [main] [DEBUG] j.sqlonly -  com.zaxxer.hikari.pool.ProxyStatement.executeQuery(ProxyStatement.java:110)
1. SELECT @@character_set_database, @@sql_mode

2024-05-10 08:14:44.988 [main] [DEBUG] o.h.t.d.s.s.DdlTypeRegistry - addDescriptor(12, org.hibernate.type.descriptor.sql.internal.CapacityDependentDdlType@7db47323) replaced previous registration(org.hibernate.type.descriptor.sql.internal.CapacityDependentDdlType@5529522f)
2024-05-10 08:14:44.988 [main] [DEBUG] o.h.t.d.s.s.DdlTypeRegistry - addDescriptor(-9, org.hibernate.type.descriptor.sql.internal.CapacityDependentDdlType@175ac243) replaced previous registration(org.hibernate.type.descriptor.sql.internal.CapacityDependentDdlType@51a719e7)
2024-05-10 08:14:44.989 [main] [DEBUG] o.h.t.d.s.s.DdlTypeRegistry - addDescriptor(-3, org.hibernate.type.descriptor.sql.internal.CapacityDependentDdlType@fd4459b) replaced previous registration(org.hibernate.type.descriptor.sql.internal.CapacityDependentDdlType@1073c664)
2024-05-10 08:14:44.989 [main] [DEBUG] o.h.t.d.s.s.DdlTypeRegistry - addDescriptor(4003, org.hibernate.type.descriptor.sql.internal.DdlTypeImpl@517a46f3) replaced previous registration(org.hibernate.type.descriptor.sql.internal.DdlTypeImpl@38fb151a)
2024-05-10 08:14:44.989 [main] [DEBUG] o.h.t.d.s.s.DdlTypeRegistry - addDescriptor(4001, org.hibernate.type.descriptor.sql.internal.DdlTypeImpl@152e7703) replaced previous registration(org.hibernate.type.descriptor.sql.internal.DdlTypeImpl@1b60d324)
2024-05-10 08:14:44.989 [main] [DEBUG] o.h.t.d.s.s.DdlTypeRegistry - addDescriptor(4002, org.hibernate.type.descriptor.sql.internal.DdlTypeImpl@182e4365) replaced previous registration(org.hibernate.type.descriptor.sql.internal.DdlTypeImpl@789dd6bf)
2024-05-10 08:14:44.989 [main] [DEBUG] o.h.t.d.s.s.DdlTypeRegistry - addDescriptor(2004, org.hibernate.type.descriptor.sql.internal.CapacityDependentDdlType@1e6d30c0) replaced previous registration(org.hibernate.type.descriptor.sql.internal.DdlTypeImpl@38929da)
2024-05-10 08:14:44.989 [main] [DEBUG] o.h.t.d.s.s.DdlTypeRegistry - addDescriptor(2005, org.hibernate.type.descriptor.sql.internal.CapacityDependentDdlType@69d667a5) replaced previous registration(org.hibernate.type.descriptor.sql.internal.DdlTypeImpl@7fe87c0e)
2024-05-10 08:14:44.990 [main] [DEBUG] o.h.t.d.s.s.DdlTypeRegistry - addDescriptor(2011, org.hibernate.type.descriptor.sql.internal.CapacityDependentDdlType@8f4b803) replaced previous registration(org.hibernate.type.descriptor.sql.internal.DdlTypeImpl@42f9873e)
2024-05-10 08:14:48.224 [main] [DEBUG] _.s.w.s.H.Mappings -
	s.b.c.BoardController:
	{PATCH [/board/{boardId}/update]}: update(Long,BoardUpdateDto)
	{POST [/board/write]}: write(BoardWriteDto,Member)
	{DELETE [/board/{boardId}/delete]}: delete(Long)
	{GET [/board/search]}: search(Pageable,String,String,String)
	{GET [/board/list]}: boardList(Pageable)
	{GET [/board/{boardId}]}: detail(Long)
2024-05-10 08:14:48.229 [main] [DEBUG] _.s.w.s.H.Mappings -
	s.b.c.CommentController:
	{PATCH [/board/{boardId}/comment/update/{commentId}]}: update(Long,CommentDto)
	{POST [/board/{boardId}/comment/write]}: write(Member,Long,CommentDto)
	{DELETE [/board/{boardId}/comment/delete/{commentId}]}: delete(Long)
	{GET [/board/{boardId}/comment/list]}: commentList(Long,Pageable)
2024-05-10 08:14:48.230 [main] [DEBUG] _.s.w.s.H.Mappings -
	s.b.c.EmployeeController:
	{GET [/employees/{id}]}: one(Long)
	{GET [/employees]}: employeeList(Pageable)
	{PUT [/employees/{id}]}: replaceEmployee(Employee,Long)
	{POST [/employees]}: newEmployee(Employee)
	{DELETE [/employees/{id}]}: deleteEmployee(Long)
2024-05-10 08:14:48.231 [main] [DEBUG] _.s.w.s.H.Mappings -
	s.b.c.FileController:
	{DELETE [/board/{boardId}/file/delete]}: delete(Long)
	{POST [/board/{boardId}/file/upload]}: upload(Long,List)
	{GET [/board/{boardId}/file/download]}: download(Long)
2024-05-10 08:14:48.233 [main] [DEBUG] _.s.w.s.H.Mappings -
	s.b.c.MemberController:
	{PUT [/user/update]}: update(Member,MemberUpdateDto)
	{POST [/user/register]}: register(MemberRegisterDto)
	{POST [/user/checkPwd]}: check(Member,Map)
	{POST [/user/login]}: login(MemberLoginDto)
	{GET [/user/checkId]}: checkIdDuplicate(String)
2024-05-10 08:14:48.235 [main] [DEBUG] _.s.w.s.H.Mappings -
	s.b.d.c.CommentApiController:
	{GET [/posts/{postId}/comments]}: findAllComment(Long,CommentSearchDto)
	{PATCH [/posts/{postId}/comments/{id}]}: updateComment(Long,Long,CommentRequest)
	{POST [/posts/{postId}/comments]}: saveComment(Long,CommentRequest)
	{DELETE [/posts/{postId}/comments/{id}]}: deleteComment(Long,Long)
	{GET [/posts/{postId}/comments/{id}]}: findCommentById(Long,Long)
2024-05-10 08:14:48.236 [main] [DEBUG] _.s.w.s.H.Mappings -
	s.b.d.f.FileApiController:
	{GET [/posts/{postId}/files]}: findAllFileByPostId(Long)
	{GET [/posts/{postId}/files/{fileId}/download]}: downloadFile(Long,Long)
2024-05-10 08:14:48.240 [main] [DEBUG] _.s.w.s.H.Mappings -
	s.b.d.m.MemberApiController:
	{POST [/]}: root()
	{POST [/members]}: saveMember(MemberRequest)
	{GET [/member-count]}: countMemberByLoginId(String)
	{GET [/members/{loginId}]}: findMemberByLoginId(String)
	{DELETE [/members/{id}]}: deleteMemberById(Long)
	{POST [/login]}: login(HttpServletRequest)
	{PATCH [/members/{id}]}: updateMember(Long,MemberRequest)
	{POST [/logout]}: logout(HttpSession)
	{GET [/login.do]}: openLogin()
	{[GET, POST] [/member/list]}: memberList(SearchDto)
2024-05-10 08:14:48.242 [main] [DEBUG] _.s.w.s.H.Mappings -
	s.b.d.p.PostController:
	{POST [/post/save.do]}: savePost(PostRequest,Model)
	{POST [/post/delete.do]}: deletePost(Long,SearchDto,Model)
	{[GET, POST] [/post/list]}: postList(SearchDto)
	{GET [/post/write.do]}: postWrite(Long,Model)
	{GET [/post/view.do]}: postView(Long,Model)
	{POST [/post/update.do]}: updatePost(PostRequest,SearchDto,Model)
2024-05-10 08:14:48.247 [main] [DEBUG] _.s.w.s.H.Mappings -
	o.s.b.a.w.s.e.BasicErrorController:
	{ [/error]}: error(HttpServletRequest)
	{ [/error], produces [text/html]}: errorHtml(HttpServletRequest,HttpServletResponse)
2024-05-10 08:14:48.256 [main] [DEBUG] _.s.w.s.H.Mappings - 'beanNameHandlerMapping' {}
2024-05-10 08:14:48.397 [main] [DEBUG] _.s.w.s.H.Mappings - 'resourceHandlerMapping' {/webjars/**=ResourceHttpRequestHandler [classpath [META-INF/resources/webjars/]], /**=ResourceHttpRequestHandler [classpath [META-INF/resources/], classpath [resources/], classpath [static/], classpath [public/], classpath [mybatis/], ServletContext [/]]}
2024-05-10 08:14:48.407 [main] [DEBUG] s.b.s.SecurityConfig - SecurityConfig.filterChain() start
2024-05-10 08:14:48.911 [main] [INFO] s.b.d.m.MemberApiControllerTest - Started MemberApiControllerTest in 7.898 seconds (process running for 9.945)
Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
2024-05-10 08:14:49.821 [main] [DEBUG] s.b.d.m.M.save - ==>  Preparing: INSERT INTO tbl_member ( id , login_id , password , name , gender , birthday , delete_yn , created_date , modified_date ) VALUES ( ? , ? , ? , ? , ? , ? , 0 , NOW() , NULL )
2024-05-10 08:14:49.849 [main] [DEBUG] s.b.d.m.M.save - ==> Parameters: 13(Long), test(String), $2a$10$I/iXcBFjDsdMgy6X6EQ.1.MlnLihuBrORWFdUcSuOTzxy/0zMGroS(String), 테스트(String), 0(Integer), 2024-05-08(String)
2024-05-10 08:14:49.850 [main] [DEBUG] j.sqlonly -  com.zaxxer.hikari.pool.ProxyPreparedStatement.execute(ProxyPreparedStatement.java:44)
1. INSERT INTO tbl_member (

          id
        , login_id
        , password
        , name
        , gender
        , birthday
        , delete_yn
        , created_date
        , modified_date

        ) VALUES (
              13
            , 'test'
            , '$2a$10$I/iXcBFjDsdMgy6X6EQ.1.MlnLihuBrORWFdUcSuOTzxy/0zMGroS'
            , '테스트'
            , 0
            , '2024-05-08'
            , 0
            , NOW()
            , NULL
        )

2024-05-10 08:14:49.868 [main] [DEBUG] s.b.d.m.M.save - <==    Updates: 1
2024-05-10 08:14:49.872 [main] [INFO] j.resultsettable -
|----------|
|insert_id |
|----------|
|13        |
|----------|

2024-05-10 08:14:49.877 [main] [DEBUG] s.b.d.m.MemberApiControllerTest - insert id=13
2024-05-10 08:14:49.881 [main] [DEBUG] s.b.d.m.M.count - ==>  Preparing: SELECT /* id="count" 2024-05-07 by shanepark */ COUNT(*) FROM tbl_member
2024-05-10 08:14:49.882 [main] [DEBUG] s.b.d.m.M.count - ==> Parameters:
2024-05-10 08:14:49.882 [main] [DEBUG] j.sqlonly -  com.zaxxer.hikari.pool.ProxyPreparedStatement.execute(ProxyPreparedStatement.java:44)
1. SELECT  /* id="count" 2024-05-07 by shanepark */
            COUNT(*)
        FROM
            tbl_member

2024-05-10 08:14:49.899 [main] [INFO] j.resultsettable -
|---------|
|count(*) |
|---------|
|13       |
|---------|

2024-05-10 08:14:49.899 [main] [DEBUG] s.b.d.m.M.count - <==      Total: 1
2024-05-10 08:14:49.902 [main] [DEBUG] s.b.d.m.M.findList - ==>  Preparing: SELECT /* id="findList" 2024-05-07 by shanepark */ id , login_id , password , name , gender , birthday , delete_yn , created_date , modified_date FROM tbl_member WHERE true ORDER BY id DESC LIMIT ?, ?
2024-05-10 08:14:49.903 [main] [DEBUG] s.b.d.m.M.findList - ==> Parameters: 0(Integer), 10(Integer)
2024-05-10 08:14:49.903 [main] [DEBUG] j.sqlonly -  com.zaxxer.hikari.pool.ProxyPreparedStatement.execute(ProxyPreparedStatement.java:44)
1. SELECT  /* id="findList" 2024-05-07 by shanepark */

          id
        , login_id
        , password
        , name
        , gender
        , birthday
        , delete_yn
        , created_date
        , modified_date

        FROM
            tbl_member
        WHERE true
        ORDER BY id DESC
        LIMIT 0, 10

2024-05-10 08:14:49.912 [main] [INFO] j.resultsettable -
|---|---------|-------------------------------------------------------------|-----|-------|-----------|----------|--------------------|--------------------|
|id |login_id |password                                                     |name |gender |birthday   |delete_yn |created_date        |modified_date       |
|---|---------|-------------------------------------------------------------|-----|-------|-----------|----------|--------------------|--------------------|
|13 |test     |$2a$10$I/iXcBFjDsdMgy6X6EQ.1.MlnLihuBrORWFdUcSuOTzxy/0zMGroS |테스트  |0      |2024-05-08 |false     |2024-05-10 08:14:49 |null                |
|12 |user 12  |1                                                            |사용자  |1      |2024-05-07 |false     |2024-05-07 14:28:52 |2024-05-07 14:28:52 |
|11 |user 11  |1                                                            |사용자  |1      |2024-05-07 |false     |2024-05-07 14:28:52 |2024-05-07 14:28:52 |
|10 |user 10  |1                                                            |사용자  |1      |2024-05-07 |false     |2024-05-07 14:28:52 |2024-05-07 14:28:52 |
|9  |user 9   |1                                                            |사용자  |1      |2024-05-07 |false     |2024-05-07 14:28:52 |2024-05-07 14:28:52 |
|8  |user 8   |1                                                            |사용자  |1      |2024-05-07 |false     |2024-05-07 14:28:52 |2024-05-07 14:28:52 |
|7  |user 7   |1                                                            |사용자  |1      |2024-05-07 |false     |2024-05-07 14:28:52 |2024-05-07 14:28:52 |
|6  |user 6   |1                                                            |사용자  |1      |2024-05-07 |false     |2024-05-07 14:28:52 |2024-05-07 14:28:52 |
|5  |user 5   |1                                                            |사용자  |1      |2024-05-07 |false     |2024-05-07 14:28:52 |2024-05-07 14:28:52 |
|4  |user 4   |1                                                            |사용자  |1      |2024-05-07 |false     |2024-05-07 14:28:52 |2024-05-07 14:28:52 |
|---|---------|-------------------------------------------------------------|-----|-------|-----------|----------|--------------------|--------------------|

2024-05-10 08:14:49.913 [main] [DEBUG] s.b.d.m.M.findList - <==      Total: 10
2024-05-10 08:14:49.914 [main] [DEBUG] s.b.d.m.MemberApiControllerTest - loginId=test
2024-05-10 08:14:49.914 [main] [DEBUG] s.b.d.m.MemberApiControllerTest - loginId=user 12
2024-05-10 08:14:49.915 [main] [DEBUG] s.b.d.m.MemberApiControllerTest - loginId=user 11
2024-05-10 08:14:49.915 [main] [DEBUG] s.b.d.m.MemberApiControllerTest - loginId=user 10
2024-05-10 08:14:49.915 [main] [DEBUG] s.b.d.m.MemberApiControllerTest - loginId=user 9
2024-05-10 08:14:49.915 [main] [DEBUG] s.b.d.m.MemberApiControllerTest - loginId=user 8
2024-05-10 08:14:49.915 [main] [DEBUG] s.b.d.m.MemberApiControllerTest - loginId=user 7
2024-05-10 08:14:49.915 [main] [DEBUG] s.b.d.m.MemberApiControllerTest - loginId=user 6
2024-05-10 08:14:49.915 [main] [DEBUG] s.b.d.m.MemberApiControllerTest - loginId=user 5
2024-05-10 08:14:49.915 [main] [DEBUG] s.b.d.m.MemberApiControllerTest - loginId=user 4
테스트 성공!
2024-05-10 08:14:49.953 [SpringApplicationShutdownHook] [INFO] c.z.h.HikariDataSource - HikariPool-1 - Shutdown initiated...
2024-05-10 08:14:49.953 [SpringApplicationShutdownHook] [DEBUG] c.z.h.p.HikariPool - HikariPool-1 - Before shutdown stats (total=10, active=0, idle=10, waiting=0)
2024-05-10 08:14:49.957 [HikariPool-1 connection closer] [DEBUG] c.z.h.p.PoolBase - HikariPool-1 - Closing connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@6806468e: (connection evicted)
2024-05-10 08:14:49.961 [HikariPool-1 connection closer] [DEBUG] c.z.h.p.PoolBase - HikariPool-1 - Closing connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@29fa613a: (connection evicted)
2024-05-10 08:14:49.961 [HikariPool-1 connection closer] [DEBUG] c.z.h.p.PoolBase - HikariPool-1 - Closing connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@7cc9dd57: (connection evicted)
2024-05-10 08:14:49.962 [HikariPool-1 connection closer] [DEBUG] c.z.h.p.PoolBase - HikariPool-1 - Closing connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@2f2f0a62: (connection evicted)
2024-05-10 08:14:49.963 [HikariPool-1 connection closer] [DEBUG] c.z.h.p.PoolBase - HikariPool-1 - Closing connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@2ff7306a: (connection evicted)
2024-05-10 08:14:49.963 [HikariPool-1 connection closer] [DEBUG] c.z.h.p.PoolBase - HikariPool-1 - Closing connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@30afda5b: (connection evicted)
2024-05-10 08:14:49.965 [HikariPool-1 connection closer] [DEBUG] c.z.h.p.PoolBase - HikariPool-1 - Closing connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@39d9c878: (connection evicted)
2024-05-10 08:14:49.965 [HikariPool-1 connection closer] [DEBUG] c.z.h.p.PoolBase - HikariPool-1 - Closing connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@1b032d78: (connection evicted)
2024-05-10 08:14:49.966 [HikariPool-1 connection closer] [DEBUG] c.z.h.p.PoolBase - HikariPool-1 - Closing connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@57cd902: (connection evicted)
2024-05-10 08:14:49.967 [HikariPool-1 connection closer] [DEBUG] c.z.h.p.PoolBase - HikariPool-1 - Closing connection net.sf.log4jdbc.sql.jdbcapi.ConnectionSpy@76cce9e0: (connection evicted)
2024-05-10 08:14:49.968 [SpringApplicationShutdownHook] [DEBUG] c.z.h.p.HikariPool - HikariPool-1 - After shutdown stats (total=0, active=0, idle=0, waiting=0)
2024-05-10 08:14:49.969 [SpringApplicationShutdownHook] [INFO] c.z.h.HikariDataSource - HikariPool-1 - Shutdown completed.
