Myrtle
======

Maven, Spring, Github, Tomcat deploys, Jenkins builds and deploys, all in one

Add the following content:
<?xml version='1.0' encoding='utf-8'?>
<Context>
	<Environment name="config" value="c:\Temp\props.properties" type="java.lang.String" override="false"/>
</Context>

to $CATALINA_HOME/conf/localhost/?.xml
