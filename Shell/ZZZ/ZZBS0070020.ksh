#!/usr/bin/ksh
# All Rights Reserved. Copyright  Canon U.S.A., Inc., 2009
#-------------------------------------------------------------------
# Project Name    : S21
# SubSystem Name  : ZZB
# JOB NET         : S21SWD_ZZBND0070
# JOB ID          : S21SWD_ZZBJ0070020
# Creator         : K.Matsuishi
# Date            : 2011/11/10
# Summary         : AutoSys Job Status Reset for Nightly Jobnet
# Type            : Scheduled
# Cycle           : Daily 4:00PM
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
JOBSCH_JOBNET='S21SWD_ZZBND0070'; export JOBSCH_JOBNET
JOBSCH_JOBID='S21SWD_ZZBJ0070020'; export JOBSCH_JOBID
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
INACTIVE="INACTIVE"
AUTOSYS_BIN=/opt/CA/UnicenterAutoSysJM/autosys/bin

JobLogOutput()
{
    LOGDATE=`date +'%Y/%m/%d %H:%M:%S.%N' | cut -c -23`
    echo "[${LOGDATE}] ${1}" >> ${LOGFILE}
}

JobLogOutput "AutoSys Job Status Reset for Nightly Jobnet"

for JOB in USEND0140 \
           USEND0130 \
           USEND0160 \
           USEND0170 \
           ZZBND0060 \
           AZZND5060 \
           ALAND0070 \
           ALAND0080 \
           ALAND0460 \
           AFCND0140 \
           AZZND5060 \
           ZZBND0040 \
           DWFND0020
do

    CHG_FLG=${NG}

    ${AUTOSYS_BIN}/sendevent -E CHANGE_STATUS -s ${INACTIVE} -J ${JOB}
    if [ $? -eq ${OK} ]; then

        # WAIT FOR CHANGE STATUS TIME LAG
        /bin/sleep 1
        for i in `/usr/bin/seq 1 9`; do
            # CHECK JOB STATUS
            if [ `${AUTOSYS_BIN}/autostatus -J ${JOB}` = ${INACTIVE} ]; then
                CHG_FLG=${OK}
                break
            else
                /bin/sleep 1
            fi
        done

    fi

    if [ ${CHG_FLG} -eq ${OK} ]; then
        JobLogOutput "${JOB} STATUS CHANGE SUCCESS"
    else
        JobLogOutput "${JOB} STATUS CHANGE ERROR"
        PRC=${NG}
    fi
done

#-------------------------------------------------
# Job end event
#-------------------------------------------------
JobLogCallforEnd ${JOBSCH_JOBID}

# Exit Code
exitFunction
postBatch

exit ${JRC}

