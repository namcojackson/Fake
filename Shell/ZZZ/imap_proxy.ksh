#!/usr/bin/ksh

# All Rights Reserved. Copyright Canon USA Limited, 2021
#-------------------------------------------------------------------
# Project Name   : S21 Project (for Java8)
# SubSystem Name : ZZZ
# JOB ID         : -
# JOB NAME       : -
# Creator        : Fujitsu Mz.Takahashi
# Date           : 11/11/2021
# Summary        : IMAP Proxy
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

LOG_PREFIX="KornShell:"

export IMAP_HOME=$EZFDVLP/99ezf/lib/imap
export JAVA_HOME=$IMAP_HOME/java/8
export PATH=$JAVA_HOME/bin

echo $LOG_PREFIX"IMAP_HOME=$IMAP_HOME" >&2
echo $LOG_PREFIX"JAVA_HOME=$JAVA_HOME" >&2
echo $LOG_PREFIX"PATH=$JAVA_HOME/bin" >&2

JARS=""

for jlib in ${IMAP_HOME}/*.jar
do
	if [ -z ${JARS} ] ; then
		JARS=${jlib}
	else
		JARS=${JARS}:${jlib}
	fi
done

if [[ $# -ge 2 ]]; then
	echo $LOG_PREFIX"execute :java -cp $JARS com.canon.cusa.s21.framework.mail.imap.server.S21MailImapProxyServer $1 $2" >&2
	java -cp  $JARS com.canon.cusa.s21.framework.mail.imap.server.S21MailImapProxyServer $1 $2
else
    echo $LOG_PREFIX"execute :java -cp $JARS com.canon.cusa.s21.framework.mail.imap.server.S21MailImapProxyServer $1" >&2
	java -cp  $JARS com.canon.cusa.s21.framework.mail.imap.server.S21MailImapProxyServer $1
fi
echo $LOG_PREFIX"IMAPProxyServer stopped [$?]" >&2
