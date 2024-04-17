/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   Fujitsu         Yamaguchi       Create          N/A
 *</Pre>
 */
package business.blap.ZZBL0020;

import java.util.HashMap;
import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import business.blap.ZZBL0020.ZZBL0020CMsg;
import business.blap.ZZBL0020.ZZBL0020Query;
import business.blap.ZZBL0020.ZZBL0020SMsg;
import business.blap.ZZBL0020.ZZBL0020_ASMsgArray;
import business.servlet.ZZBL0020.constant.ZZBL0020Constant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmEZDClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

public final class ZZBL0020Query implements ZZBL0020Constant{

    /**
     * Singleton instance.
     */
    private static final ZZBL0020Query myInstance = new ZZBL0020Query();

    /**
     * Constructor.
     */
    private ZZBL0020Query() {
        super();
    }
        
    /**
     * Singleton instance getter.
     * @return  SSML0002Query
     */
    public static ZZBL0020Query getInstance() {
        return myInstance;
    }    

    /**
     * Execute ZZBL0020Query SQL.
     * @param cMsg
     * @param sMsg
     * @return
     */
    public int exec(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        ZZBL0020CMsg bizMsg = (ZZBL0020CMsg) cMsg;
        ZZBL0020SMsg sesMsg = (ZZBL0020SMsg) sMsg;
        
        HashMap<String, Object> mapParam = new HashMap<String, Object>();

        if (!bizMsg.ezReqBusinessID.isClear()) {
            mapParam.put("ezReqBusinessID", bizMsg.ezReqBusinessID.getValue());
        }
        
        if (!bizMsg.ezReqJobCtlNetID.isClear()){
            mapParam.put("ezReqJobCtlNetID", bizMsg.ezReqJobCtlNetID.getValue());
        }
        
        sesMsg.A.clear();
        ZZBL0020_ASMsgArray resultObject = sesMsg.A;
        
        int hitCount =0; 
        try {
            S21SsmEZDClient ssmClient = S21SsmEZDClient.getClient(this.getClass());
            S21SsmEZDResult result = ssmClient.queryEZDMsgArray("getReqMasterList", mapParam, resultObject);
            if (result != null) {
                hitCount = resultObject.getValidCount();
                if (result.getQueryResultCount() > MAX_ROW) {
                	 bizMsg.setMessageInfo("ZZZM9002W");
                } else if (hitCount == 0 ){
                	bizMsg.A.setValidCount(0);
                	bizMsg.setMessageInfo("ZZZM9005W");
                }
            }
        }
        catch (Exception e) {
            // bizMsg.setMessageInfo("ZZSM4101E", ZZSL1000Common.getErrorMessage(e.getMessage()));
        	EZDDebugOutput.println(1, e.getMessage(), null);
        }
        return hitCount;
    }
}
