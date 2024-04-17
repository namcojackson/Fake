#!/usr/bin/ksh
# All Rights Reserved. Copyright  Canon U.S.A., Inc., 2009
#-------------------------------------------------------------------
# Project Name    : S21
# SubSystem Name  : NFC
# JOB NET         : S21SWD_SFCND4770
# JOB ID          : S21SWD_SFCJ4770010
# Date            : 05/04/2018
# Summary         : All procedures related to conversion 
# Type            : Scheduled
# Cycle           : One time
# Version         : 1
# InputParameter1 : 
# InputParameter2 : 
# InputParameter3 : 
# InputParameter4 : 
# InputParameter5 : 

#-------------------------------------------------
# Setup for Environment
#-------------------------------------------------
USER_COMPANY_CD='[[USER_COMPANY_CD]]'; export USER_COMPANY_CD
GLBL_CMPY_CD='[[GLBL_CMPY_CD]]'; export GLBL_CMPY_CD

JOBSCH_PROJECT='S21'; export JOBSCH_PROJECT
JOBSCH_JOBNET='S21SWD_SFCND4770'; export JOBSCH_JOBNET
JOBSCH_JOBID='S21SWD_SFCJ4770010'; export JOBSCH_JOBID
PROGRAM_ID='CanonCustomBillingConversionJob'; export PROGRAM_ID
SUBSYSTEM_CD='NFC'; export SUBSYSTEM_CD
unset DISPLAY
#-------------------------------------------------
# (optional)If any parameter for batch program needed.
#-------------------------------------------------


#-------------------------------------------------
# (optional)If memory tuning is needed.(If nothing set, default is MEM_XMN=384,MEM_XMX=1024,MEM_XMS=512)
#-------------------------------------------------
# Memory Tuning
MEM_XMN=''; export MEM_XMN
MEM_XMX=''; export MEM_XMX
MEM_XMS=''; export MEM_XMS

_IFS="${IFS}"
IFS='/'
set -- `pwd`
IFS="${_IFS}"
S_USR=$3

# Common Environments
COM_SHELL_HOME=/home/${S_USR}/dvlp/02shell/ZZZ
. ${COM_SHELL_HOME}/S21.system.common.evironment.ksh

#-------------------------------------------------
# (optional)Batch Contorol Parameters which can be used in batch program.
#-------------------------------------------------
MAX_RETRYCNT='1'
RETRY_TIME='1'
DEBUG_LEVEL='0'
COMMIT_TRANCNT='100'
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
preBatch
JobLogCallforSTART ${JOBSCH_JOBID}

#-------------------------------------------------
# Process Name
#-------------------------------------------------
PROGRAM_TYPE=EXT
#PROCESS1=com.canon.cusa.s21.batch.${PROGRAM_TYPE}.${PROGRAM_ID}
PROCESS1=oracle.apps.custombilling.server.${PROGRAM_ID}

#-------------------------------------------------
# EXECUTE PROCESS
#-------------------------------------------------
env
executeGeneralApp

#-------------------------------------------------
# Job end event
#-------------------------------------------------
JobLogCallforEnd ${JOBSCH_JOBID}

# Exit Code
exitFunction
postBatch

exit ${JRC}
