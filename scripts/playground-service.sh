set -e
# PGREP=/usr/bin/pgrep
# JAVA=/usr/bin/java
# LOGDIR=/logs
# ZERO=0
# OKMSG=OK
# PLAYGROUNDPORT=8081

# ansible config
PLAYGROUNDJARFILE=playground-0.0.1-SNAPSHOT.jar
APPDIR=/lib

RED='\033[1;31m'
GREEN='\033[1;32m'
YELLOW='\033[1;33m'
BLUE='\033[1;34m'
NC='\033[0m' #No Color

start() {
    echo ""		
    echo "Starting playground service..."


    if $PGREP -f playground > /dev/null;
    then
        echo -e "The service is ${YELLOW}already${NC} running"
    else
        # $JAVA -jar "-Dfile.encoding=UTF-8" $APPDIR$PLAYGROUNDJARFILE q > /dev/null 2>&1 &
        TIMESTAMP=$(date +%Y-%m-%d_%H:%M:%S)
        LOGFILE="$APPDIR$LOGDIR/${TIMESTAMP}_playground.log"
        mkdir -p $APPDIR$LOGDIR
        $JAVA -jar "-Dfile.encoding=UTF-8" $APPDIR$PLAYGROUNDJARFILE --server.port=$PLAYGROUNDPORT > $LOGFILE 2>&1 &
        sleep 3
    
        if $PGREP -f playground > /dev/null;
        then
            echo -e "Service was ${GREEN}successfully${NC} started"
        else
            echo -e "${RED}Failed${NC} to start service"
        fi
    fi
    echo
}

stop() {
    echo ""		
    echo "Stopping playground service..."

    if $PGREP -f playground > /dev/null;
    then 
        PIDS=$($PGREP -f playground)
        if [ -n "$PIDS" ]; then
		    kill -9 $PIDS
		    sleep 3
        fi
		if $PGREP -f playground  > /dev/null;
		then
			echo -e "${RED}Failed${NC} to stop service"
		else
			echo -e "Service was ${GREEN}successfully${NC} stopped"
		fi        
    else
        echo -e "The service is ${YELLOW}already${NC} stopped"
    fi
    echo
}

status() {
    echo ""		
    echo "Checking status of playground service..." 
    if $PGREP -f playground > /dev/null;
    then
        PIDS=$($PGREP -f playground)
        echo -e "Service is ${GREEN}running${NC} with PID(s): $PIDS"
    else
        echo -e "Service is ${RED}stopped${NC}"
    fi
    echo
}

# Main logic from script
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
    restart|reload)
        stop
        start
        ;;
  *)
    echo -e "${RED}Invalid arguments${NC}"
    echo -e " Usages: $0 ${BLUE}{ start | stop | status | restart | reload }${NC}"
    exit 1
esac
exit 0