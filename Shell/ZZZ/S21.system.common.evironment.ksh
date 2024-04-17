#!/usr/bin/ksh
# All Rights Reserved. Copyright Canon USA Limited, 2012
#-------------------------------------------------------------------
# Project Name   : S21 Project (for WAS7/8.5)
# SubSystem Name : ZZZ
# JOB ID         : -
# JOB NAME       : -
# Creator        : Fujitsu USA) Ugaki
# Date           : 7/30/2013
# Summary        : Common Environment script for S21 Batch System.
# Type           : Common Shell
# Cycle          : -
# Parameters     : -
#-------------------------------------------------------------------
# Update Archives
# date / updator / content
# 
# 
#-------------------------------------------------------------------
# User specific environment and startup programs
export PATH="/WebSphere/AppServer/java/bin:${PATH}:${HOME}/bin"
export AUTOSYS_HOME="/opt/CA/UnicenterAutoSysJM/autosys"

# TEMPORARY...
export WAS_HOME="/WebSphere/AppServer"
export MQ_HOME="/opt/mqm"

# EZDeveloper Environment
export EZFENVID="dvlp"
export EZFDVLP="/home/${S_USR}/${EZFENVID}"

# The following are the Integration
export INTG_WAS1="${WAS_HOME}/lib/bootstrap.jar"
export INTG_WAS2="${WAS_HOME}/lib/j2ee.jar"
export INTG_WAS3="${WAS_HOME}/plugins/com.ibm.ws.runtime.jar"
export INTG_WAS4="${MQ_HOME}/java/lib/com.ibm.mqjms.jar"
export INTG_WAS5="${MQ_HOME}/java/lib/com.ibm.mq.jar"
export INTG_WAS6="${MQ_HOME}/java/lib/com.ibm.mq.soap.jar"
export INTG_WAS7="${MQ_HOME}/java/lib/dhbcore.jar"
export INTG_WAS8="${WAS_HOME}/plugins/com.ibm.ws.emf.jar"
export INTG_WAS9="${WAS_HOME}/runtimes/com.ibm.ws.webservices.thinclient_7.0.0.jar"
export INTG_WAS10="${WAS_HOME}/runtimes/com.ibm.ws.admin.client_7.0.0.jar"
export INTG_WAS11="${WAS_HOME}/plugins/com.ibm.ws.wlm.jar"
export INTG_WAS12="${WAS_HOME}/runtimes/com.ibm.ws.ejb.thinclient_7.0.0.jar"

# For JAX-WS Lib (WAS8.5 Java API for XML Web Services) - EIP & FAX
export JAX_WAS1="${WAS_HOME}/runtimes/com.ibm.jaxws.thinclient_8.5.0.jar"
export JAX_WAS2="${WAS_HOME}/endorsed_apis/jaxws-api.jar"

#ADD FOR WAS 8.5
export INTG_WAS13="${WAS_HOME}/runtimes/com.ibm.ws.webservices.thinclient_8.5.0.jar"
export INTG_WAS14="${WAS_HOME}/runtimes/com.ibm.ws.admin.client_8.5.0.jar"
export INTG_WAS15="${WAS_HOME}/plugins/javax.j2ee.management.jar"
export INTG_WAS16="${WAS_HOME}/plugins/com.ibm.tx.ltc.jar"

#ADD FOR Mobile
export MBL_WAS1="${WAS_HOME}/plugins/com.ibm.ws.webmsg.jar"


export AUTOSYS_SERVER_LOG4J="${AUTOSYS_HOME}/lib/log4j-1.2.6.jar"
export AUTOSYS_SERVER_LIB="${AUTOSYS_HOME}/lib/asapi.jar"
#. ${AUTOUSER}/autosys.ksh.localhost.cusa.canon.com

export ORCL_JDBC="/opt/oracle/product/12.1.0/client_1/jdbc/lib/ojdbc6.jar"
# the folowing is not changeable
export EZFHOME="${EZFDVLP}/99ezf"
export EZFRUNDIR="${EZFDVLP}/01run"
export EZRUN_LIBRARY="${EZFRUNDIR}/lib"
export LOG_HOME="${EZFDVLP}/70log"
export LOGDIR="${LOG_HOME}/joblog"
export WRKDIR="${EZFDVLP}/00temp"

REQUEST_PATH="${WRKDIR}/requestFile"
export REQUEST_DIR="${REQUEST_PATH}/${SUBSYSTEM_CD}"

# Application Messages
export MESSAGES="${EZFDVLP}/90common/msg"

# Configuration File
export CONFIG="${EZFDVLP}/80conf/setting"

# File Message Modules
export FILEMSGDIR="${EZFDVLP}/32filejava/bin"

# DB Message Modules
export DBMSGDIR="${EZFDVLP}/31dbjava/oracle/bin"
export DBXMLDIR="${EZFDVLP}/31dbjava/oracle/gen/xml"
export BUSINESS_MSGDIR="${EZFDVLP}/33bizjava/bin"

export EZ_LIBRARY="${EZFHOME}/lib"
export BATCH_PARTS="${EZFHOME}/parts/java/bin"
export BATCH_DBPARTS="${EZFHOME}/parts/dbjava/oracle/bin"
export BATCH_DBXMLPARTS="${EZFHOME}/parts/dbjava/oracle/gen/xml"

for jlib in ${EZ_LIBRARY}/*.jar; do export CLASSPATH=${CLASSPATH}:${jlib}; done
for jzip in ${EZ_LIBRARY}/*.zip; do export CLASSPATH=${CLASSPATH}:${jzip}; done
for aplib in ${EZRUN_LIBRARY}/*.jar; do export CLASSPATH=${CLASSPATH}:${aplib}; done
export CLASSPATH="${CLASSPATH}:${CONFIG}:${ORCL_JDBC}:${JAX_WAS1}:${JAX_WAS2}:${EZFRUNDIR}/classes:${DBXMLDIR}:${BUSINESS_MSGDIR}:${BATCH_DBXMLPARTS}:${BATCH_PARTS}:${BATCH_DBPARTS}:${DBMSGDIR}:${FILEMSGDIR}:${MESSAGES}:${INTG_WAS1}:${INTG_WAS2}:${INTG_WAS3}:${INTG_WAS4}:${INTG_WAS5}:${INTG_WAS6}:${INTG_WAS7}:${INTG_WAS8}:${INTG_WAS9}:${INTG_WAS10}:${INTG_WAS11}:${INTG_WAS12}:${INTG_WAS13}:${INTG_WAS14}:${INTG_WAS15}:${INTG_WAS16}:${MBL_WAS1}:${AUTOSYS_SERVER_LOG4J}:${AUTOSYS_SERVER_LIB}"

# Log Control
export LOGCALL="com.canon.cusa.s21.framework.log.S21CallMessageFunc"

# Set Global Company Code and User Company Code
EZDSYSTEMENV=$CONFIG/EZDSystem.properties
export GLBL_CMPY_CD=`awk -F"=" '$1=="S21.global_company_code"{print $2}' $EZDSYSTEMENV`
export USER_COMPANY_CD=$GLBL_CMPY_CD

JobLogCall()
{
    LOGDATE=`date +'%Y/%m/%d %H:%M:%S'`".000"
    if [ "${1}" = "ZZBM0015I" ]; then
        STR=`echo "${2}" | cut -c2-11`
        echo "[${LOGDATE}] Normal End of [${STR}]." >> ${LOGFILE}
    elif [ "${1}" = "ZZBM0016E" ]; then
        STR=`echo "${2}" | cut -c2-11`
        echo "[${LOGDATE}] Abnormal-End of [${STR}]." >> ${LOGFILE}
    else
        STR=`echo "${2}" | cut -c2-11`
        PRCX=`echo "${2}" | cut -c26-`
        echo "[${LOGDATE}] Process return code error of [${STR}] PRC[${PRCX}]." >> ${LOGFILE}
    fi
    return 0
}

JobLogCallforSTART()
{
    LOGDATE=`date +'%Y/%m/%d %H:%M:%S'`".000"
    echo "[${LOGDATE}] Job ID[${1}] : Start." >> ${LOGFILE}
    return 0
}

JobLogCallforEnd()
{
    LOGDATE=`date +'%Y/%m/%d %H:%M:%S'`".000"
    echo "[${LOGDATE}] Job ID[${1}] : End." >> ${LOGFILE}
    return 0
}

