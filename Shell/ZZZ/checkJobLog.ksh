#!/usr/bin/ksh
# All Rights Reserved. Copyright Canon USA Limited, 2010
#-------------------------------------------------------------------
# Project Name   : S21
# SubSystem Name : ZZZ
# JOB ID         : -
# JOB NAME       : -
# Creator        : Fsol) Oida
# Date           : 8/2/2010
# Summary        : Checking the existence of joblob file(.log or .err).
#                  It's called by the online program "ZZSL0060 AbendLogviewer".
# Type           : -
# Cycle          : -
# Parameters     : BAT_PROC_JOB_LOG_PATH_NM : Filepath to joblog
#-------------------------------------------------------------------
# Update Archives
# date / updator / content
# 
# 
#-------------------------------------------------------------------

BAT_PROC_JOB_LOG_PATH_NM=$1
ERR_FILE="$BAT_PROC_JOB_LOG_PATH_NM"".err"
LOG_FILE="$BAT_PROC_JOB_LOG_PATH_NM"".log"
FNS_MSG="Joblog file is found"
NFT_MSG="No joblog file or the size is over 300MB size"
ERR_MSG="Unexpected error is occured"

#Ask the batch server to find joblog(.err).
find "$ERR_FILE" -size -300000k
if [ $? -eq 0 ];
then
echo "$FNS_MSG"
exit 0
else
echo "$NFT_MSG"
exit 0
fi
echo "$ERR_MSG"
exit 99
