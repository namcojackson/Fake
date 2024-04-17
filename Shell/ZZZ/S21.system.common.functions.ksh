#!/usr/bin/ksh
# All Rights Reserved. Copyright Canon USA, 2009
#-------------------------------------------------------------------
# Project Name   : S21
# SubSystem Name : -
# JOB ID         : -
# JOB NAME       : -
# Creator        : FAI) Akihiro Miyamoto
# Date           : 9/29/2008
# Summary        : Common Functions
# Type           : -
# Cycle          : -
# Parameters     : -
#-------------------------------------------------------------------
# Update Archives
# date / updator / content
# 
# 
#-------------------------------------------------------------------

preBatch(){
    DEBUG_LEVEL='0'
    if [ -z "${MEM_XMN}" ];
    then
      MEM_XMN=384
    fi
    if [ -z "${MEM_XMX}" ];
    then
      MEM_XMX=1024
    fi
    if [ -z "${MEM_XMS}" ];
    then
      MEM_XMS=512
    fi
    return 0
}

postBatch(){
    return 0
}

executeRequestExtraction(){
    if [ -f ${REQUEST_DIR}'/'${JOBSCH_JOBNET}'.ARW01' ] ; then rm -f ${REQUEST_DIR}'/'${JOBSCH_JOBNET}'.ARW01' ; fi
    if [ -f ${REQUEST_DIR}'/'${JOBSCH_JOBNET}'.ARW08' ] ; then rm -f ${REQUEST_DIR}'/'${JOBSCH_JOBNET}'.ARW08' ; fi

    REQUEST_DATAFILE=${REQUEST_DIR}'/'${JOBSCH_JOBNET}'.ARW01'; export REQUEST_DATAFILE
    REQUEST_NUMBERFILE=${REQUEST_DIR}'/'${JOBSCH_JOBNET}'.ARW08'; export REQUEST_NUMBERFILE

    PROCESS1=com.canon.cusa.s21.batch.ZZZ.${PROGRAM_ID}.${PROGRAM_ID}
    java -Djava.protocol.handler.pkgs=com.ibm.mq.soap.transport -Xquickstart -DDBUSE=Y -DDEBUG_LEVEL=${DEBUG_LEVEL} -DREQUEST_DATAFILE=${REQUEST_DATAFILE} -DEXT_NETID=${VAR_INPUT_PARAM1} -DREQUEST_NUMBERFILE=${REQUEST_NUMBERFILE} -DFILEBASE=${FILEBASE} -cp ".:${CLASSPATH}"  ${PROCESS1}  1>> ${LOGFILE} 2>&1
    PRC=$?
    if [ $PRC -ne 0 ] && [ $PRC -ne 10 ];
    then
        JOBMSG="Process number 10 (ZZBB01401) execution error PRC="$PRC; export JOBMSG
        java parts.batchcommon.EZDJobAbend ${JOBSCH_JOBID} $JOBMSG 1>> ${LOGFILE} 2>&1
        PRC=99
    fi
    if [ -f ${REQUEST_DATAFILE} ]; then
        chmod -R 777 ${REQUEST_DATAFILE}
    fi

    if [ -f ${REQUEST_NUMBERFILE} ]; then
        chmod -R 777 ${REQUEST_NUMBERFILE}
    fi
    JOBNO=`cat ${REQUEST_NUMBERFILE}` 1>> ${LOGFILE} 2>&1
    if [ ${JOBNO} ] ; then
        mv ${LOGFILE} ${LOGFILE}'.'${JOBNO}
        LOGFILE=${LOGFILE}'.'${JOBNO}
    fi
}

executeRequestTrouble(){
    REQUEST_DATAFILE=${REQUEST_DIR}'/'${JOBSCH_JOBNET}'.ARW01'; export REQUEST_DATAFILE
    REQUEST_NUMBERFILE=${REQUEST_DIR}'/'${JOBSCH_JOBNET}'.ARW08'; export REQUEST_NUMBERFILE

    if [  -f ${REQUEST_NUMBERFILE} ];
    then
        JOBNO=`cat  ${REQUEST_NUMBERFILE}` 1>> ${LOGFILE} 2>&1
        mv ${LOGFILE} ${LOGFILE}'.'${JOBNO}
        LOGFILE=${LOGFILE}'.'${JOBNO}
    fi

    PROCESS1=com.canon.cusa.s21.batch.ZZZ.${PROGRAM_ID}.${PROGRAM_ID}
    if [ ! -f ${REQUEST_DATAFILE} ];
    then
        JobLogCall "ZZBM0057E" "#${REQUEST_DATAFILE}"
        PRC=99
    fi
    if [ $PRC -eq 0 ];
    then
        REQUEST_DATAFILE=${REQUEST_DATAFILE}; export REQUEST_DATAFILE
        java -Xquickstart -DDEBUG_LEVEL=${DEBUG_LEVEL} -DDBUSE=Y -DJOBSCH_PROJECT=${JOBSCH_PROJECT} -DJOBSCH_JOBNET=${JOBSCH_JOBNET} -DREQUEST_DATAFILE=${REQUEST_DATAFILE} -DFILEBASE=${FILEBASE} -cp ".:${CLASSPATH}"  ${PROCESS1}  1>> ${LOGFILE} 2>&1
        PRC=$?
        if [ ${PRC} -ne 0 ] && [ ${PRC} -ne 10 ];
        then
            JOBMSG="Process number 10 (ZZBB001501) execution error PRC ="$PRC; export JOBMSG
            java parts.batchcommon.EZDJobAbend ${JOBSCH_JOBID} ${JOBMSG} 1>> $LOGFILE 2>&1
            PRC=99
        fi
    fi
}

executeGeneralApp(){
    # retry count
    CNTWK=1
    until [ ${MAX_RETRYCNT} -lt ${CNTWK} ];
    do

        if [  -f ${REQUEST_DIR}'/'${JOBSCH_JOBNET}'.ARW01' ];
        then
            REQUEST_FILE=${REQUEST_DIR}'/'${JOBSCH_JOBNET}'.ARW01'
            java -Djava.protocol.handler.pkgs=com.ibm.mq.soap.transport -Xmn${MEM_XMN}m -Xmx${MEM_XMX}m -Xms${MEM_XMS}m -Xquickstart -Djava.awt.headless=true -DDBUSE=Y -DCOMMIT_TRANCNT=${COMMIT_TRANCNT} -DMAX_RETRYCNT=${MAX_RETRYCNT} -DDEBUG_LEVEL=${DEBUG_LEVEL} -Di01=${REQUEST_FILE} -DPROGRAMID=${PROGRAM_ID} -DRECORDLOCK_RETRYCNT=${RECORDLOCK_RETRYCNT} -DRECORDLOCK_RETRYINTERVAL=${RECORDLOCK_RETRYINTERVAL} -cp "${CLASSPATH}"  ${PROCESS1}  1>> ${LOGFILE} 2>&1
        else
            java -Djava.protocol.handler.pkgs=com.ibm.mq.soap.transport -Xmn${MEM_XMN}m -Xmx${MEM_XMX}m -Xms${MEM_XMS}m -Xquickstart -Djava.awt.headless=true -XX:+UseCompilerSafepoints -XX:+UseOnStackReplacement -XX:+StringCache -XX:+UseFastAccessorMethods -DDBUSE=Y -DCOMMIT_TRANCNT=${COMMIT_TRANCNT} -DMAX_RETRYCNT=${MAX_RETRYCNT} -DDEBUG_LEVEL=${DEBUG_LEVEL} -DPROGRAMID=${PROGRAM_ID} -DRECORDLOCK_RETRYCNT=${RECORDLOCK_RETRYCNT} -DRECORDLOCK_RETRYINTERVAL=${RECORDLOCK_RETRYINTERVAL} -cp "${CLASSPATH}"  ${PROCESS1}  1>> ${LOGFILE} 2>&1
        fi

        PRC=$?
        if [ $PRC -ne 20 ];
        then
            break;
        fi
        sleep $RETRY_TIME
        CNTWK=`expr $CNTWK + 1`
    done
    if [ $PRC -eq 20 ];
    then
        JOBMSG="Occurrence a dead-lock.(Retry over) Retry Count="$MAX_RETRYCNT
        java parts.batchcommon.EZDJobAbend $JOBSCH_JOBID "${JOBMSG}" 1>> $LOGFILE 2>&1
        PRC=99
    fi
}

exitFunction(){
    if [ $PRC -eq 0 ] || [ $PRC -eq 10 ];
    then
        JobLogCall "ZZBM0015I" "#${JOBSCH_JOBID}#${JOBSCH_JOBID}"
        mv ${LOGFILE} ${LOGFILE}'.log'
        JRC=0
    elif [ $PRC -eq 99 ];
    then
        JobLogCall "ZZBM0016E" "#${JOBSCH_JOBID}#${JOBSCH_JOBID}"
        mv ${LOGFILE} ${LOGFILE}'.err'
        JRC=99
    else
        JobLogCall "ZZBM0017E" "#${JOBSCH_JOBID}#${JOBSCH_JOBID}#${PRC}"
        mv ${LOGFILE} ${LOGFILE}'.err'
        JRC=99
    fi
}
