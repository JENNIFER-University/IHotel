#!/bin/sh

# ----------------------------------------------------------------------
# -----------  Configurations  -----------------------------------------
# ----------------------------------------------------------------------

#JENNIFER AGENT HOME
AGENT_HOME=/Users/khalid/jennifer/agent.java

#Agent Configuration File Name
CONF_FILE=ipayment.conf

APP_HOME="$PWD"
APP_JAR=$APP_HOME/upayment.jar
APP_LIB=$APP_HOME/lib/*

#ICheck Listen Port. Default is 1099 If you change this value make sure to configure iPayment connection settings as well.
LISTEN_PORT=18080

JAVA_OPTS="-javaagent:$AGENT_HOME/jennifer.jar -Djennifer.config=$AGENT_HOME/conf/$CONF_FILE"

java $JAVA_OPTS -cp $APP_JAR:$APP_LIB edu.jennifer.ipayment.APP
