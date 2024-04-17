#!/usr/bin/ksh
# All Rights Reserved. Copyright  Canon U.S.A., Inc., 2009
#-------------------------------------------------------------------
# Project Name    : S21
# SubSystem Name  : ZZB
# JOB NET         : S21SWD_ZZBNM0630
# JOB ID          : S21SWD_ZZBJ0630010
# Creator         : T.Gotoda
# Date            : 7/29/2011
# Summary         : Temp Data File Purge
# Type            : Scheduled
# Cycle           : Monthly
# Version         : 1.0
# InputParameter1 : 
# InputParameter2 : 
# InputParameter3 : 
# InputParameter4 : 
# InputParameter5 : 

#-------------------------------------------------------------------
# Update Archives
# date/updator/content
# 
# 
#-------------------------------------------------------------------


#-------------------------------------------------
# Setup for Environment
#-------------------------------------------------
USER_COMPANY_CD='ADB'; export USER_COMPANY_CD
GLBL_CMPY_CD='ADB'; export GLBL_CMPY_CD

JOBSCH_PROJECT='S21'; export JOBSCH_PROJECT
JOBSCH_JOBNET='S21SWD_ZZBNM0630'; export JOBSCH_JOBNET
JOBSCH_JOBID='S21SWD_ZZBJ0630010'; export JOBSCH_JOBID
PROGRAM_ID=''; export PROGRAM_ID
SUBSYSTEM_CD='ZZB'; export SUBSYSTEM_CD

VAR_INPUT_PARAM1=$1; export VAR_INPUT_PARAM1
VAR_INPUT_PARAM2=$2; export VAR_INPUT_PARAM2
VAR_INPUT_PARAM3=$3; export VAR_INPUT_PARAM3
VAR_INPUT_PARAM4=$4; export VAR_INPUT_PARAM4
VAR_INPUT_PARAM5=$5; export VAR_INPUT_PARAM5

# User Variables
VAR_USER1=''; export VAR_USER1
VAR_USER2=''; export VAR_USER2
VAR_USER3=''; export VAR_USER3
VAR_USER4=''; export VAR_USER4
VAR_USER5=''; export VAR_USER5

# Interface ID
INTERFACE_ID=''; export INTERFACE_ID

# Report ID
REPORT_ID=''; export REPORT_ID

_IFS="${IFS}"
IFS='/'
set -- `pwd`
IFS="${_IFS}"
S_USR=$3

# Memory Tuning
MEM_XMN=''; export MEM_XMN
MEM_XMX=''; export MEM_XMX
MEM_XMS=''; export MEM_XMS

# Common Environments
COM_SHELL_HOME=/home/${S_USR}/dvlp/02shell/ZZZ
. ${COM_SHELL_HOME}/S21.system.common.evironment.ksh

# Batch Type
BATCH_TYPE_EZ=0; export BATCH_TYPE_EZ

#-------------------------------------------------
# Batch Contorol Parameters
#-------------------------------------------------
MAX_RETRYCNT='0'
RETRY_TIME='0'
DEBUG_LEVEL='1'
COMMIT_TRANCNT='0'
RECORDLOCK_RETRYCNT='0'
RECORDLOCK_RETRYINTERVAL='0'

#-------------------------------------------------
# Make up a Joblog file.
#-------------------------------------------------
. ${COM_SHELL_HOME}/S21.system.common.logging.ksh
. ${COM_SHELL_HOME}/S21.system.common.functions.ksh

#-------------------------------------------------
# clear PRC(Process Return Code) & JRC(Job Return Code)
#-------------------------------------------------
PRC=0
JRC=0

#-------------------------------------------------
# Start Job!
#-------------------------------------------------
#preBatch
JobLogCallforSTART ${JOBSCH_JOBID}

#-------------------------------------------------
# Process Name
#-------------------------------------------------
#PROGRAM_TYPE=ZZZ
#PROCESS1=com.canon.cusa.s21.batch.${PROGRAM_TYPE}.${PROGRAM_ID}.${PROGRAM_ID}

#-------------------------------------------------
# EXECUTE PROCESS
#-------------------------------------------------

OK=0
NG=1

# DATA TMP DIR
DATATMP_DIR1=`grep -e "S21.batch.temp.dir1" ${CONFIG}/EZDSystem.properties | cut -d "=" -f 2`
DATATMP_DIR2=`grep -e "S21.batch.temp.dir2" ${CONFIG}/EZDSystem.properties | cut -d "=" -f 2`

# DATA FILE RETENTION PERIOD
RP=${VAR_INPUT_PARAM1}

JobLogOutput()
{
    LOGDATE=`date +'%Y/%m/%d %H:%M:%S.%N' | cut -c -23`
    echo "[${LOGDATE}] ${1}" >> ${LOGFILE}
}

JobLogOutput "##### TEMP DATA FILE PURGE START! #####"

# If the tmp file(s) already past RP(Retention Period), It/They will be deleted.
for FILE in `find ${DATATMP_DIR1} ${DATATMP_DIR2} -type f -mtime +${RP}`
do

    FILE_INFO=`ls -l ${FILE}`
    JobLogOutput "DELETE TARGET FILE : ${FILE_INFO}"

    ERR=`rm -f ${FILE} 2>&1`
    if [ $? -ne ${OK} ]; then
        JobLogOutput "DELETE ERROR : ${ERR}"
        PRC=${NG}
    fi

done

JobLogOutput "##### TEMP DATA FILE PURGE COMPLETE! #####"

#-------------------------------------------------
# Job end event
#-------------------------------------------------
JobLogCallforEnd ${JOBSCH_JOBID}

# Exit Code
exitFunction
postBatch

exit ${JRC}

