#!/bin/sh

# ACME Stress. Applying Stress to ACME Demo System

# ----------------------------------------------------------------------
# -----------  Configurations  -----------------------------------------
# ----------------------------------------------------------------------

#Setup JAVA_HOME
#JAVA_HOME=/home/centos/jdk1.8.0_91

# iHotel IP Address
ihotel_ip=127.0.0.1

# iHotel Port
 ihotel_port=8080

# ----------------------------------------------------------------------
# -----------  Do not Edit below this line -----------------------------
# ----------------------------------------------------------------------
#Application Main Class. Do not Change
MAIN_CLASS=edu.jennifer.stress.RunStress


grapProcess(){
  sleep 3
  pgrep -f "stress.RunStress" > PID
  echo "Stress Tool Process ID" | cat PID
}


applyStress(){
 nohup $JAVA_HOME/bin/java -cp .:lib/* $MAIN_CLASS &
 grapProcess
}

forground(){
 $JAVA_HOME/bin/java -cp .:lib/* $MAIN_CLASS 
}

echo "Applying Stress"
applyStress
