package business.blap.NFCL3120;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

import business.blap.NFCL3120.constant.NFCL3120Constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         T.Tanaka        Create          N/A
 *</pre>
 */
public class NFCL3120Query extends S21SsmEZDQuerySupport implements NFCL3120Constant {

    /**
    * Singleton instance.
    */
   private static final NFCL3120Query myInstance = new NFCL3120Query();

   /**
    * Constructor
    */
   public NFCL3120Query() {
       super();
   }

   /**
    * Singleton instance getter.
    * @return NFDL0010Query
    */
   public static NFCL3120Query getInstance() {
       return myInstance;
   }

   /**
    * 
    * @param globalMsg
    * @param ssmParam
    * @return
    */
   public S21SsmEZDResult searchBankAcct_Customer_Identified(NFCL3120SMsg globalMsg, Map<String, Object> ssmParam) {
       return getSsmEZDClient().queryEZDMsgArray("searchBankAcct_Customer_Identified", ssmParam, globalMsg.A);
   }

   /**
    * 
    * @param globalMsg
    * @param ssmParam
    * @return
    */
   public S21SsmEZDResult searchBankAcct_Customer_Unidentified(NFCL3120SMsg globalMsg, Map<String, Object> ssmParam) {
       return getSsmEZDClient().queryEZDMsgArray("searchBankAcct_Customer_Unidentified", ssmParam, globalMsg.A);
   }
}
