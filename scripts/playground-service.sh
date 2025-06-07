APPDIR=/opt/playground
PGREP=/usr/bin/pgrep
JAVA=/usr/bin/java
PLAYGROUNDJARFILE=/playground.jar
ZERO=0
OKMSG=OK
PLAYGROUNDPORT=8080

RED='\033[1;31m'
GREEN='\033[1;32m'
YELLOW='\033[1;33m'
BLUE='\033[1;34m'
NC='\033[0m' #No Color

start() {
    echo ""		
    echo "Starting playground service..."

    $PGREP -f PLAYGROUND > /dev/null
    VERIFIER=$?
    if [ $ZERO -eq $VERIFIER ]
    then
        echo -e "The service is ${YELLOW}already${NC} running"
    else
        $JAVA -jar "-Dfile.encoding=UTF-8" $APPDIR$PLAYGROUNDJARFILE q > /dev/null 2>&1 &
        sleep 3
        $PGREP -f PLAYGROUND > /dev/null
        VERIFIER=$?
        if [ $ZERO -eq $VERIFIER ]
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
    $PGREP -f PLAYGROUND > /dev/null
    VERIFIER=$?
    if [ $ZERO -eq $VERIFIER ]
    then        
		kill -9 $($PGREP -f PLAYGROUND)
		sleep 3
		$PGREP -f PLAYGROUND  > /dev/null
		VERIFIER=$?
		if [ $ZERO -eq $VERIFIER ]
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
    img
    $PGREP -f playground > /dev/null
    VERIFIER=$?
    if [ $ZERO -eq $VERIFIER ]
    then
        echo -e "Service is ${GREEN}running${NC}"        
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
    echo "${RED}Invalid arguments${NC}"
    echo " Usages: $0 ${BLUE}{ start | stop | status | restart | reload }${NC}"
    exit 1
esac
exit 0