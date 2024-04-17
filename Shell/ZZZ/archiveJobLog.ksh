#!/usr/bin/ksh
# All Rights Reserved. Copyright Canon USA Limited, 2010
#-------------------------------------------------------------------
# Project Name   : S21
# SubSystem Name : ZZZ
# JOB ID         : -
# JOB NAME       : -
# Creator        : Fsol) Oida
# Date           : 8/2/2010
# Summary        : Archiving the joblob file(.log or .err) for ".zip"format.
#                  It's called by the online program "ZZSL0060 AbendLogviewer".
# Type           : -
# Cycle          : -
# Parameters     : BAT_PROC_JOB_LOG_PATH_NM : Filepath to joblog on the batch server
                   TEMP_FILE_NM : Temporary file name
#-------------------------------------------------------------------
# Update Archives
# date / updator / content
# 
# 
#-------------------------------------------------------------------

BAT_PROC_JOB_LOG_PATH_NM=$1
TEMP_FILE_NM=$2

#Definition variables combined from input args
ERR_FILE="$BAT_PROC_JOB_LOG_PATH_NM"".err"

#Archive joblog and download
zip -9 $TEMP_FILE_NM $ERR_FILE
if [ $? -eq 0 ];
then
echo "Archive is success"
exit 0
else
echo "Archive isn't success"
exit 99
fi
