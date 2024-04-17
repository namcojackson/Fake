#!/usr/bin/ksh
# All Rights Reserved. Copyright  Canon U.S.A., Inc., 2009
#-------------------------------------------------------------------
# Project Name    : S21
# SubSystem Name  : ZZB
# JOB NET         : S21SWD_ZZBND0030
# JOB ID          : S21SWD_ZZBJ0040010
# Creator         : T.Gotoda
# Date            : 7/22/2009
# Summary         : Online Close
# Type            : Scheduled
# Cycle           : Daily
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
JOBSCH_JOBNET='S21SWD_ZZBND0030'; export JOBSCH_JOBNET
JOBSCH_JOBID='S21SWD_ZZBJ0040010'; export JOBSCH_JOBID
PROGRAM_ID='ZZBB004001'; export PROGRAM_ID
SUBSYSTEM_CD='ZZB'; export SUBSYSTEM_CD

VAR_INPUT_PARAM1=$1; export VAR_INPUT_PARAM1
VAR_INPUT_PARAM2=$2; export VAR_INPUT_PARAM2
VAR_INPUT_PARAM3=$3; export VAR_INPUT_PARAM3
VAR_INPUT_PARAM4=$4; export VAR_INPUT_PARAM4
VAR_INPUT_PARAM5=$5; export VAR_INPUT_PARAM5

# User Variables
VAR_USER1='NFAL0050,NFAL0060,NFAL0080,NFAL0120,NFAL0200,NFAL0210,NFAL0220,NFAL0230,NFAL0270,NFAL0280,NFBL0045,NFBL1110,NFBL1120,NFBL1130,NFBL2040,NFBL2060,NFBL2070,NFBL2080,NFCL0450,NFCL0510,NFCL0730,NFCL0740,NFCL0750,NFCL2320,NFCL2510,NFCL2520,NFCL2530,NFCL2540,NFCL2610,NFCL2620,NFCL2640,NFCL2650,NFCL2760,NFCL3000,NFCL3010,NFCL3020,NFCL3030,NFCL3040,NFCL3050,NFCL3070,NFCL3080,NFCL3120,NFCL3130,NFCL3140,NFCL3170,NFCL0770,NFDL0010,NFDL0030,NFDL0040,NFDL0080,NFDL0090,NFDL0130,NFDL0150,NFDL0160,NLAL0070,NLAL1100,NLAL2020,NLAL2030,NLAL2040,NLBL0040,NLBL0060,NLBL0110,NLBL2020,NLBL3060,NLBL3070,NLBL3080,NLBL3090,NLBL3120,NLBL3140,NLBL3150,NLCL0090,NLCL0180,NLCL0290,NLCL0300,NLCL0600,NLCL0610,NLCL0620,NLCL0630,NLCL1020,NLCL1030,NLCL1040,NLCL1050,NLEL0060,NLEL0070,NLEL0080,NLGL0030,NMAB3200,NMAB7010,NMAB7020,NMAL0100,NMAL0110,NMAL0150,NMAL0160,NMAL0170,NMAL0190,NMAL0200,NMAL0230,NMAL0240,NMAL2460,NMAL2500,NMAL2510,NMAL2520,NMAL2560,NMAL2600,NMAL2620,NMAL2700,NMAL2710,NMAL2720,NMAL2800,NMAL2830,NMAL2840,NMAL3000,NMAL3200,NMAL4500,NMAL6310,NMAL6320,NMAL6330,NMAL6340,NMAL6350,NMAL6370,NMAL6380,NMAL6410,NMAL6420,NMAL6450,NMAL6460,NMAL6550,NMAL6670,NMAL6700,NMAL6710,NMAL6720,NMAL6730,NMAL6740,NMAL6750,NMAL6820,NMAL6840,NMAL6850,NMAL6890,NMAL7000,NMAL7010,NMAL7020,NMAL7050,NMAL7060,NMAL7070,NMAL7080,NMAL7090,NMAL7100,NMAL7110,NMAL7120,NMAL7130,NMAL7150,NMAL7160,NMAL7180,NMAL7190,NMAL7210,NMAL7220,NMAL7230,NMAL7240,NMAL7250,NMAL7260,NMAL7270,NMAL7280,NMAL7290,NMAL7410,NPAL1070,NPAL1080,NPAL1150,NPAL1220,NPAL1230,NPAL1250,NPAL1280,NPAL1340,NPAL1350,NPAL1360,NPAL1400,NPAL1410,NPAL1430,NPAL1500,NPAL1520,NPAL1530,NPAL1540,NPBL0020,NSAL0010,NSAL0180,NSAL0230,NSAL0490,NSAL0860,NSAL0910,NSAL1080,NSAL1090,NSAL1100,NSAL1120,NSAL9910,NSBL0030,NSBL0280,NSBL0390,NSBL0400,NWAL1500,NWAL1540,NWAL1670,NWAL1700,NWAL1770,NWAL1780,NWAL1820,NWAL1840,NWAL1850,NWAL2010,NWAL2030,NWAL2040,NWAL2200,NWAL2220,NWAL2300,NWAL2320,NWAL2410,NWAL7020,NWCL0100,NWCL0110,NWCL0120,NWCL0130,NWCL0140,NWCL0150,NWDL0190,NWDL0260,NZZL0020,NZZL0030,NZZL0070,ZYP*,ZZP*,ZZS*,ZZW*'; export VAR_USER1
VAR_USER2='1'; export VAR_USER2
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
PROGRAM_TYPE=ZZZ
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

