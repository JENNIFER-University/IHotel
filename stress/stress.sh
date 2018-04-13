#!/bin/sh

# ----------------------------------------------------------------------
# -----------  Configurations  -----------------------------------------
# ----------------------------------------------------------------------

# iHotel IP Address
IHOTEL_IP=127.0.0.1

# iHotel Port
IHOTEL_PORT=8080



# ----------------------------------------------------------------------
# -----------  Do not Edit below this line -----------------------------
# ----------------------------------------------------------------------
APP_HOME="$PWD"
APP_LIB=$APP_HOME/lib/*
MAIN_CLASS=edu.jennifer.stress.Main
APP_NAME="stress"
LOG="$APP_HOME/$APP_NAME.log"

app_pid() {
    pgrep -f $MAIN_CLASS
}

start() {
    echo "Starting $APP_NAME. Please check the log file $LOG for more information"
    nohup java $JAVA_OPTS -cp .:$APP_LIB/*  $MAIN_CLASS -i $IHOTEL_IP -P IHOTEL_PORT >> $LOG 2>&1 &
    while app_pid > /dev/null ;  do
        sleep 1
    done

    app_pid > /dev/null
}

stop () {
    echo "Stopping $APP_NAME"
    pid=`app_pid`
    [ -n "$pid" ] && kill $pid
}

status() {
    pid=`app_pid`
    if [ -n "$pid" ]; then
        echo "$APP_NAME (Process Id $pid) is running"
        return 0
    fi

    echo "$APP_NAME is stopped"
    return 0;
}

case "$1" in
        start)
            start
            ;;
        stop)
            stop
            ;;
        status)
            status
            ;;
        *)
            echo "Usage $0 (start|stop|status)"
            exit 1
esac