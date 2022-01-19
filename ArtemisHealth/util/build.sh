#!/bin/bash
###############################################################################
# Name        : build.sh                                                      #
# Author      : Abel Gancsos                                                  #
# Version     : v. 1.0.0.0                                                    #
# Description : Helps build the project.                                      #
###############################################################################
BASE_PATH=$(dirname $0)/..
ROOT_PATH=$(dirname $0)/..
PROJECT_NAME=
NOMANIFEST=0
COPY_TO_ROOT=0
DIALECT=scala

while getopts "p:n:m:c:d:" option; do
    case $option in
        p) BASE_PATH=$OPTARG ;;
		n) PROJECT_NAME=$OPTARG ;;
		m) NOMANIFEST=$OPTARG ;;
		c) COPY_TO_ROOT=$OPTARG ;;
		d) DIALECT=$OPATARG ;;
        *) ;;
    esac
done

if [ "$(which scalac)" = "" ]; then
	echo "Scala RPL not installed..."
	exit -1
fi

if [ ! -d $BASE_PATH/bin ]; then
	mkdir $BASE_PATH/bin
fi
if [ ! -d $BASE_PATH/dist ]; then
	mkdir $BASE_PATH/dist
fi
rm -fr $BASE_PATH/bin/*
rm -fr $BASE_PATH/dist/*
find $BASE_PATH \( -name "project" -o -name "target" \) -a -type d -exec bash -c 'rm -fr $0 2>&1' {} \;

scalac -cp ".:$BASE_PATH/lib/*:" -d $BASE_PATH/bin $(find $BASE_PATH/src/main/$DIALECT -name *.scala)
if [ -d $BASE_PATH/conf ]; then
	cp -R $BASE_PATH/conf $BASE_PATH/bin
fi
if [ $NOMANIFEST = 0 ]; then
	jar cvfm "$BASE_PATH/dist/$PROJECT_NAME.jar" $BASE_PATH/Manifest.txt -C "$BASE_PATH/bin" .
else
	jar cvf "$BASE_PATH/dist/$PROJECT_NAME.jar" -C "$BASE_PATH/bin" .
fi

if [ $ROOT_PATH != $BASE_PATH ] && [ $COPY_TO_ROOT = 1 ]; then
	cp $BASE_PATH/dist/*.jar $ROOT_PATH/lib
fi

rm -fr $BASE_PATH/bin/*

