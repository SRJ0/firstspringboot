#!/usr/bin/env bash

# 쉬고있는 profile 찾기

function  find_idle_profile() {
    RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

    if [ ${RESPONSE_CODE} -ge 400] # http status가 400보다 크면
    then
      CURRENT_PROFILE=real2
    else
      CURRENT_PROFILE=$(curl -s http://localhost/profile)
    fi

    if [ ${CURRENT_PROFILE} == real1]
    then
      IDLE_PROFILE=real2
    else
      IDLE_PROFILE=real1
    fi
  # bash 스크립트는 값 반환이 안되므로 마지막 줄에 에코 후 클라이언트에서 찾아서 사용
    echo "${IDLE_PROFILE}"

}

function find_idle_port() {
  IDLE_PROFILE=$(find_idle_profile)

  if [ ${IDLE_PROFILE} == real1 ]
  then
    echo "8081"
  else
    echo "8082"
  fi

}