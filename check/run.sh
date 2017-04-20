#!/bin/sh

# ----------------------------------------------------------------------
# -----------  Configurations  -----------------------------------------
# ----------------------------------------------------------------------

#JENNIFER AGENT HOME
AGENT_HOME=/Users/khalid/jennifer/agent.java

#Agent Configuration File Name
CONF_FILE=check.conf

# ICheck Home, use only if the card_check.jar and lib directory are in different location from this script
ICHECK_HOME="$PWD"
ICHECK_JAR=$ICHECK_HOME/card_checker.jar
LIB_JAR=$ICHECK_HOME/lib/check-1.1.jar

#ICheck Listen Port. Default is 1099 If you change this value make sure to configure payment connection settings as well.
LISTEN_PORT=1099

JAVA_OPTS="-javaagent:$AGENT_HOME/jennifer.jar -Djennifer.config=$AGENT_HOME/conf/$CONF_FILE"

java $JAVA_OPTS -cp $ICHECK_JAR:$LIB_JAR edu.jennifer.check.launcher.AppLauncher $LISTEN_PORT