#!/bin/bash
###############################################################################
# Name        : find_class.sh                                                 #
# Author      : Abel Gancsos                                                  #
# Version     : v. 1.0.0.0                                                    #
# Description : Helps find a class from the libs.                             #
###############################################################################
BASE_PATH=$(dirname $0)/..
ROOT_PATH=$(dirname $0)/..
CLASS_NAME=
VERBOSE=0
SHOW_RESULTS=0

while getopts "c:p:v:r:" option; do
    case $option in
        p) BASE_PATH=$OPTARG ;;
		c) CLASS_NAME=$OPTARG ;;
		v) VERBOSE=$OPTARG ;;
		r) SHOW_RESULTS=$OPTARG ;;
        *) ;;
    esac
done
printf "\e[32mWill search for: '$CLASS_NAME'\e[m\n"
ALL_CLASSES=$(find $BASE_PATH/lib -name *.jar)
for lib in ${ALL_CLASSES[@]}; do
	if [ $VERBOSE -gt 0 ]; then
		printf "\e[34m>>> Searching in lib: '$lib'\e[m\n"
	fi
	results=($(jar tf $lib | grep "$CLASS_NAME"))
	if [ ${#results[@]} -gt 0 ]; then
		printf "\e[36m* $lib\e[m\n"
		if [ $VERBOSE -gt 0 ] || [ $SHOW_RESULTS -gt 0 ]; then
			for result in ${results[@]}; do
				printf "\e[31m   !!! $result\e[m\n"
			done
		fi
	fi
done
