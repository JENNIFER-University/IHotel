#!/bin/sh

# ACME Stress. Applying Stress to ACME Demo System
# To Modify setting use app.conf

#Setup JAVA_HOME
JAVA_HOME=/home/centos/jdk1.8.0_91

#Application Main Class. Do not Change
MAIN_CLASS=demo.jennifer.acme.stress.main.RunStress



grapProcess(){
  sleep 3
  pgrep -f "main.RunStress" > PID
  echo "Stress Tool Process ID" | cat PID
}


background(){
 nohup $JAVA_HOME/bin/java -cp .:lib/* $MAIN_CLASS &
 grapProcess
}

forground(){
 $JAVA_HOME/bin/java -cp .:lib/* $MAIN_CLASS 
}


cmd=$1
if [ $cmd = "start" ]
then
   background 
elif [ $cmd = "run" ]
then
   forground 
else
  echo "Invalid command, to start in background please pass [start] as first argument, and to start in forground please pass [run] as argument"
  echo "Running in background : ./start.sh start"
  echo "Running in forground : ./start.sh run "
fi
