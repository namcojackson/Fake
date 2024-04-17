#
# Usage example 
# ./build.sh -Duniq_ctxt=csawdsap1_ONLINE_DEV -Dcontext=s21extn
# ./build.sh -Duniq_ctxt=csawdsap1_BATCH_DEV -Dcontext=s21extn build_batch_dvlp
#

JAVA_HOME=/WebSphere/AppServer/java
ANT_HOME=${HOME}"/s21gridbuild/bin/ant"
PATH=$JAVA_HOME/bin:$PATH
$ANT_HOME/bin/ant "$@"
