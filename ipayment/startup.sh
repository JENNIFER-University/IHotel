#!/bin/sh

# ----------------------------------------------------------------------
# -----------  Configurations  -----------------------------------------
# ----------------------------------------------------------------------

#ICheck Listen Port. Default is 1099 If you change this value make sure to configure payment connection settings as well.
LISTEN_PORT=18080

# ----------------------------------------------------------------------
# -----------  JENNIFER Agent  -----------------------------------------
# ----------------------------------------------------------------------

#JENNIFER AGENT HOME
#AGENT_HOME=/Users/khalid/jennifer/agent.java

#Agent Configuration File Name
CONF_FILE=ipayment.conf

#JAVA_OPTS="-javaagent:$AGENT_HOME/jennifer.jar -Djennifer.config=$AGENT_HOME/conf/$CONF_FILE"

# ----------------------------------------------------------------------
# -----------  Do not Edit below this line -----------------------------
# ----------------------------------------------------------------------


APP_HOME="$PWD"
APP_JAR=$APP_HOME/ipayment.jar
APP_LIB=$APP_HOME/lib/*

java $JAVA_OPTS -cp $APP_JAR:$APP_LIB edu.jennifer.ipayment.APP
