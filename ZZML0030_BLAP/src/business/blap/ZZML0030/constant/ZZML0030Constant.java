/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/06/2009   Fujitsu         T.Kawamura      Create          N/A
 * 03/03/2020   Fujitsu         K.takahama      Update          QC#56127
 *</pre>
 */
package business.blap.ZZML0030.constant;

import com.canon.cusa.s21.framework.mail.MailDefinition;

public interface ZZML0030Constant {

    String ML_STS_SENT    = "SENT";
    String ML_STS_PENDING = "PENDING";
    
    String[][] ML_SEND_STS = { 
            { MailDefinition.ML_STS_WAIT, MailDefinition.ML_STS_WAIT }, 
            { MailDefinition.ML_STS_SEND, ML_STS_SENT }, 
            { MailDefinition.ML_STS_ERROR, ML_STS_PENDING }
        };
    
    // 03/03/2020 Mod QC#56127 Start
    String ML_ADDR_TP_FROM = "FROM";
    String ML_ADDR_TP_TO    = "TO";
    String ML_ADDR_TP_CC = "CC";
    String ML_ADDR_TP_BCC = "BCC";
    
    String[][] ML_ADDR_TP = { 
            { ML_ADDR_TP_TO, ML_ADDR_TP_TO }, 
            { ML_ADDR_TP_CC, ML_ADDR_TP_CC }, 
            { ML_ADDR_TP_BCC, ML_ADDR_TP_BCC }
        };
    // 03/03/2020 Mod QC#56127 End

    int COL_STS_CD = 0;
    int COL_STS_NM = 1;

    String HH24MMSSFF3_ZERO = "000000000";
    String MMSSFF3_ZERO = "0000000";
    String MMSS_ZERO = "0000";

    String PROC_WARN = "W";

    
    // ----- ZZML0030Query -----
    String PM_EZDCMSG = "cMsg";
    
    String PM_UPD_DT_FROM = "updatedDateFrom";
    String PM_UPD_TM_FROM = "updatedTimeFrom";
    String PM_UPD_DT_TO = "updatedDateTo";
    String PM_UPD_TM_TO = "updatedTimeTo";
    
    String PM_ML_SUBJ_TXT = "mlSubjTxt";
    String PM_ML_USR_ADDR = "mlUsrAddr";
    // 03/03/2020 Mod QC#56127 Start
    String PM_ML_USR_ADDR_TO = "mlUsrAddrTo";
    // 03/03/2020 Mod QC#56127 End
    
    String QUERY_LIST = "getList";
    
    String PM_ML_SEND_PK = "mlSendPk";
    String PM_ML_STS_PK = "mlStsPk";
    
    String QUERY_MLUSR = "getMlUsr";
    String QUERY_MLATT = "getMlAtt";
    String QUERY_MLSEND = "getMlSend";
    String QUERY_MLSEND_BODY = "selMlBody";
    
    String DB_CHUNK_SIZE = "CHUNK_SIZE";
    String DB_ML_BODY_TXT = "ML_BODY_TXT";

}
