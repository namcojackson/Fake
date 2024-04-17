#!/usr/bin/ksh
# All Rights Reserved. Copyright  Canon U.S.A., Inc., 2009
#-------------------------------------------------------------------
# Project Name    : S21
# SubSystem Name  : ZZB
# JOB NET         : S21SWD_ZZBND0150
# JOB ID          : S21SWD_ZZBJ0150010
# Creator         : T.Tsuji
# Date            : 10/26/2017
# Summary         : BCV#3 Split(WSE Copy)
# Type            : Scheduled
# Cycle           : Daily
# Version         : 1
# InputParameter1 : BCV3 user@host for ssh specify
# InputParameter2 : BCV3 shell name on the host
# InputParameter3 : BCV3 parameter
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
JOBSCH_JOBNET='S21SWD_ZZBND0150'; export JOBSCH_JOBNET
JOBSCH_JOBID='S21SWD_ZZBJ0150010'; export JOBSCH_JOBID
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

# Common Environments
COM_SHELL_HOME=/home/${S_USR}/dvlp/02shell/ZZZ
. ${COM_SHELL_HOME}/S21.system.common.evironment.ksh

# Batch Type
BATCH_TYPE_EZ=0; export BATCH_TYPE_EZ

#-------------------------------------------------
# Batch Contorol Parameters
#-------------------------------------------------
MAX_RETRYCNT='1'
RETRY_TIME='1'
DEBUG_LEVEL='1'
COMMIT_TRANCNT='1'
RECORDLOCK_RETRYCNT='10'
RECORDLOCK_RETRYINTERVAL='1'

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
preBatch
JobLogCallforSTART ${JOBSCH_JOBID}

#-------------------------------------------------
# Process Name
#-------------------------------------------------
PROGRAM_TYPE=ZZB



#-------------------------------------------------
# EXECUTE PROCESS
#-------------------------------------------------
#ssh ${VAR_INPUT_PARAM1} ${VAR_INPUT_PARAM2} BCV3 >> ${LOGFILE}
echo "ssh -tt ${VAR_INPUT_PARAM1} \"sudo -n -u ${VAR_INPUT_PARAM3} ${VAR_INPUT_PARAM2}\"" >> ${LOGFILE}
ssh -tt ${VAR_INPUT_PARAM1} "sudo -n -u ${VAR_INPUT_PARAM3} ${VAR_INPUT_PARAM2}"

PRC=$?

if [[ $? -eq 0 ]]; then
  echo "EMC-BCV copy BCV3 is done successfully with ${VAR_INPUT_PARAM1}" >> ${LOGFILE}
fi


#if [ $PRC -eq 0 ];
#then
#	# EZ Request Extraction JOB
#	if [ ${BATCH_TYPE_EZ} -eq 1 ];
#	then
#            executeRequestExtraction
#	# EZ Request Trouble Job
#	elif [ ${BATCH_TYPE_EZ} -eq 2 ];
#	then
#            executeRequestTrouble
#	# General Batch Application
#	else
#            executeGeneralApp
#	fi
#fi

#-------------------------------------------------
# Job end event
#-------------------------------------------------
JobLogCallforEnd ${JOBSCH_JOBID}

# Exit Code
exitFunction
postBatch

exit ${JRC}

