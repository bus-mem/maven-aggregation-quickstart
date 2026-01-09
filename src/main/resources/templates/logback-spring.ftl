<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="false" scanPeriod="60 seconds">
  <springProperty scop="context" name="LOG_PATH" source="logging.file.path"/>
  <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
      <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36}.%M - %msg%n</pattern>
    </encoder>
  </appender>
  <appender name="infoAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>${r'${LOG_PATH}'}/info.log</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>${r'${LOG_PATH}'}/info-%d{yyyy-MM-dd}.log</fileNamePattern>
      <MaxHistory>30</MaxHistory>
    </rollingPolicy>
    <encoder>
      <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36}.%M - %msg%n</pattern>
    </encoder>
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
      <level>INFO</level>
      <onMatch>ACCEPT</onMatch>
      <onMismatch>DENY</onMismatch>
    </filter>
  </appender>
  <appender name="debugAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>${r'${LOG_PATH}'}/debug.log</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>${r'${LOG_PATH}'}/debug-%d{yyyy-MM-dd}.log</fileNamePattern>
      <MaxHistory>30</MaxHistory>
    </rollingPolicy>
    <encoder>
      <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36}.%M - %msg%n</pattern>
    </encoder>
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
      <level>DEBUG</level>
      <onMatch>ACCEPT</onMatch>
      <onMismatch>DENY</onMismatch>
    </filter>
  </appender>
  <appender name="warnAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>${r'${LOG_PATH}'}/warn.log</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>${r'${LOG_PATH}'}/warn-%d{yyyy-MM-dd}.log</fileNamePattern>
      <MaxHistory>30</MaxHistory>
    </rollingPolicy>
    <encoder>
      <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36}.%M - %msg%n</pattern>
    </encoder>
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
      <level>WARN</level>
      <onMatch>ACCEPT</onMatch>
      <onMismatch>DENY</onMismatch>
    </filter>
  </appender>
  <appender name="errorAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>${r'${LOG_PATH}'}/error.log</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>${r'${LOG_PATH}'}/error-%d{yyyy-MM-dd}.log</fileNamePattern>
      <MaxHistory>30</MaxHistory>
    </rollingPolicy>
    <encoder>
      <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36}.%M - %msg%n</pattern>
    </encoder>
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
      <level>ERROR</level>
      <onMatch>ACCEPT</onMatch>
      <onMismatch>DENY</onMismatch>
    </filter>
  </appender>

  <logger name="org.springframework" additivity="false">
    <level value="ERROR"/>
    <appender-ref ref="STDOUT"/>
    <appender-ref ref="errorAppender"/>
  </logger>
  <logger name="org.apache.tomcat.util" additivity="false">
    <level value="ERROR"/>
    <appender-ref ref="STDOUT"/>
    <appender-ref ref="errorAppender"/>
  </logger>
  <logger name="org.hibernate.validator" additivity="false">
    <level value="ERROR"/>
    <appender-ref ref="STDOUT"/>
    <appender-ref ref="errorAppender"/>
  </logger>
  <include resource="org/springframework/boot/logging/logback/console-appender.xml"/>
  <logger name="jdbc.sqlonly" level="info">
  </logger>
  <logger name="jdbc.audit" level="OFF">
  </logger>
  <logger name="jdbc.resultset" level="OFF">
  </logger>
  <logger name="jdbc.connection" level="OFF">
  </logger>
  <logger name="jdbc.sqltiming" level="OFF">
    <appender-ref ref="CONSOLE"/>
  </logger>
  <logger name="org.springframework.kafka" level="error"/>
  <root level="INFO">
    <appender-ref ref="STDOUT"/>
    <appender-ref ref="infoAppender"/>
    <appender-ref ref="errorAppender"/>
  </root>
</configuration>