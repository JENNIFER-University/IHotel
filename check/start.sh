#!/bin/sh

# ----------------------------------------------------------------------
# -----------  Configurations  -----------------------------------------
# ----------------------------------------------------------------------

# JAVA_HOME Settings. Uncomment the next line and set the path to JAVA_HOME

#JAVA_HOME=

#JENNIFER AGENT HOME
#AGENT_HOME=/Users/khalid/jennifer/agent.java

#Agent Configuration File Name
#CONF_FILE=check.conf

# JAVA_OPTS for Agent installation
#JAVA_OPTS="-javaagent:$AGENT_HOME/jennifer.jar -Djennifer.config=$AGENT_HOME/conf/$CONF_FILE"

#ICheck Listen Port. Default is 1099 If you change this value make sure to configure payment connection settings as well.
LISTEN_PORT=1099



#java $JAVA_OPTS -cp $ICHECK_JAR:$LIB_JAR edu.jennifer.check.launcher.AppLauncher $LISTEN_PORT

# ----------------------------------------------------------------------
# -----------  Do not Edit below this line -----------------------------
# ----------------------------------------------------------------------

# ICheck Home, use only if the card_check.jar and lib directory are in different location from this script
CHECK_HOME="$PWD"
LIB_JAR=$CHECK_HOME/lib


#Application Main Class. Do not Change
MAIN_CLASS=edu.jennifer.check.launcher.AppLauncher


grapProcess(){
  sleep 3
  pgrep -f $MAIN_CLASS > PID
  echo "Process ID" | cat PID
}

startup() {
  nohup $JAVA_HOME/bin/java -cp .:lib/* $JAVA_OPTS $MAIN_CLASS  $LISTEN_PORT &
  grapProcess

}

startup