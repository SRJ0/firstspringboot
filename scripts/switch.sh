#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy() {
  IDLE_PORT=$(find_idle_port)

  echo "> 전환할 Port: $IDLE_PORT"
  echo "> Port 전환"
  # 하나의 문장을 만들어 파이프라인으로 넘겨주기위해 echo 사용
  # nginx가 변경할 프록시ㅣ주소를 생성하고 .inc에 덮어쓴다
  echo "set \$service_url  http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc

  echo "> nginx reload"
  sudo service nginx reload
  # restart 와 달리 끊김이 없다. 중요한 설정은 반영되지 않지만 외부 설정 파일인 service-url을 다시 불러오는 것이므로 가능
}

