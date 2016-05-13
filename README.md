基于struts+spring+ibatis+freemarker脚手架

如何在老系统支持mvc
<filter>
    <filter-username>struts2</filter-username>
    <filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
   <!-- <filter-class>com.tom.filter.MyFilterDispatcher</filter-class>-->
</filter>
<filter-mapping>
    <filter-username>struts2</filter-username>
    <url-pattern>*.action</url-pattern>
    <dispatcher>REQUEST</dispatcher>
    <dispatcher>FORWARD</dispatcher>
    <dispatcher>INCLUDE</dispatcher>
</filter-mapping>
如何在老系统支持spring mvc,访问路径为/services/*
<!-- restful configuration -->
    <servlet>
        <servlet-username>rest</servlet-username>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-username>contextConfigLocation</param-username>
            <param-value>classpath:/com.tom.web.config/springmvc-servlet.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-username>rest</servlet-username>
        <url-pattern>/services/*</url-pattern>
    </servlet-mapping>