#!/usr/bin/ksh
# All Rights Reserved. Copyright  Canon U.S.A., Inc., 2009
#-------------------------------------------------------------------
# Project Name    : S21
# SubSystem Name  : ZZT
# JOB NET         : S21SWD_ZZTNR0000
# JOB ID          : S21SWD_ZZTJ0010030
# Creator         : Y.Suda
# Date            : 2015/10/13
# Summary         : BO Fax Validation
# Type            : Scheduled
# Cycle           : 
# Version         : 1
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
JOBSCH_JOBNET='S21SWD_ZZTNR0000'; export JOBSCH_JOBNET
JOBSCH_JOBID='S21SWD_ZZTJ0010030'; export JOBSCH_JOBID
PROGRAM_ID='ZZTB001003'; export PROGRAM_ID
SUBSYSTEM_CD='ZZT'; export SUBSYSTEM_CD

VAR_INPUT_PARAM1=$1; export VAR_INPUT_PARAM1
VAR_INPUT_PARAM2=$2; export VAR_INPUT_PARAM2
VAR_INPUT_PARAM3=$3; export VAR_INPUT_PARAM3
VAR_INPUT_PARAM4=$4; export VAR_INPUT_PARAM4
VAR_INPUT_PARAM5=$5; export VAR_INPUT_PARAM5

# User Variables
VAR_USER1='FAXOUT_FW'; export VAR_USER1
VAR_USER2='1234567'; export VAR_USER2
VAR_USER3=''; export VAR_USER3
VAR_USER4=''; export VAR_USER4
VAR_USER5=''; export VAR_USER5

# Interface ID
INTERFACE_ID=''; export INTERFACE_ID

# Report ID
REPORT_ID='ZZTF0400'; export REPORT_ID

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
MAX_RETRYCNT='1'
RETRY_TIME='1'
DEBUG_LEVEL='1'
COMMIT_TRANCNT='100'
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
PROGRAM_TYPE=ZZT
PROCESS1=com.canon.cusa.s21.batch.${PROGRAM_TYPE}.${PROGRAM_ID}.${PROGRAM_ID}

#-------------------------------------------------
# EXECUTE PROCESS
#-------------------------------------------------
if [ $PRC -eq 0 ];
then
	# EZ Request Extraction JOB
	if [ ${BATCH_TYPE_EZ} -eq 1 ];
	then
            executeRequestExtraction
	# EZ Request Trouble Job
	elif [ ${BATCH_TYPE_EZ} -eq 2 ];
	then
            executeRequestTrouble
	# General Batch Application
	else
            executeGeneralApp
	fi
fi

#-------------------------------------------------
# Job end event
#-------------------------------------------------
JobLogCallforEnd ${JOBSCH_JOBID}

# Exit Code
exitFunction
postBatch

exit ${JRC}

