#!/usr/bin/ksh
# All Rights Reserved. Copyright Canon USA, 2009
#-------------------------------------------------------------------
# Project Name   : S21
# SubSystem Name : -
# JOB ID         : -
# JOB NAME       : -
# Creator        : Fujitsu America,Inc. Akihiro Miyamoto
# Date           : 9/29/2008
# Summary        : Common script for Logging.
# Type           : -
# Cycle          : -
# Parameters     : -
#-------------------------------------------------------------------
# Update Archives
# date / updator / content
# 
# 
#-------------------------------------------------------------------
FILEBASE=`echo $JOBSCH_PROJECT.$JOBSCH_JOBNET.$JOBSCH_JOBID`; export FILEBASE
LOGDATE=`date +%Y%m%d%H%M%S%Z`
LOGDAY=`date +%Y%m%d`

LOGDIR_PATH=${LOGDIR}'/'${LOGDAY}
LOGDIR=${LOGDIR}'/'${LOGDAY}'/'${SUBSYSTEM_CD}
if [ ! -d ${LOGDIR_PATH} ] ; then
    mkdir ${LOGDIR_PATH}
    chmod 775 ${LOGDIR_PATH}
fi
if [ ! -d ${LOGDIR} ] ; then
    mkdir ${LOGDIR}
    chmod 775 ${LOGDIR}
fi

if [  -f $WRKDIR'/'$JOBSCH_JOBNET'.ARW08' ];
then
    JOBID=`cat  $WRKDIR'/'$JOBSCH_JOBNET'.ARW08'`
    LOGFILE=${LOGDIR}'/'${LOGDATE}'.'${FILEBASE}'.'$JOBSCH_JOBID; export LOGFILE
else
    LOGFILE=${LOGDIR}'/'${LOGDATE}'.'${FILEBASE}; export LOGFILE
fi

MULTI_SUFFIX=0
TMP_LOGFILE=${LOGFILE}
while [ -e ${TMP_LOGFILE} ]
do
    MULTI_SUFFIX=`expr ${MULTI_SUFFIX} + 1`
    TMP_LOGFILE=${LOGFILE}'_'${MULTI_SUFFIX}
done
LOGFILE=${TMP_LOGFILE}
touch ${LOGFILE}
chmod 775 ${LOGFILE}
