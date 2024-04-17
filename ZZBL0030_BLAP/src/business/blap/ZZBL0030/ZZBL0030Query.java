/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   Fujitsu         Yamaguchi       Create          N/A
 *</Pre>
 */
package business.blap.ZZBL0030;

import java.util.HashMap;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.common.EZDDebugOutput;
import business.blap.ZZBL0030.ZZBL0030CMsg;
import business.blap.ZZBL0030.ZZBL0030Query;
import business.blap.ZZBL0030.ZZBL0030SMsg;
import business.blap.ZZBL0030.ZZBL0030_ASMsgArray;
import business.blap.ZZBL0030.constant.ZZBL0030Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmEZDClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

public final class ZZBL0030Query implements ZZBL0030Constant{

    /**
     * Singleton instance.
     */
    private static final ZZBL0030Query myInstance = new ZZBL0030Query();

    /**
     * Constructor.
     */
    private ZZBL0030Query() {
        super();
    }
        
    /**
     * Singleton instance getter.
     * @return  SSML0002Query
     */
    public static ZZBL0030Query getInstance() {
        return myInstance;
    }    

    /**
     * Execute ZZBL0030Query SQL.
     * @param cMsg
     * @param sMsg
     * @return
     */
    public int exec(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        ZZBL0030CMsg bizMsg = (ZZBL0030CMsg) cMsg;
        ZZBL0030SMsg sesMsg = (ZZBL0030SMsg) sMsg;
        
        HashMap<String, Object> mapParam = new HashMap<String, Object>();

        if (!bizMsg.ezReqBusinessName.isClear()) {
            mapParam.put("ezReqBusinessName", bizMsg.ezReqBusinessName.getValue().toUpperCase());
        }
        
        if (!bizMsg.ezReqJobStatus.getValue().equals("5")){
            mapParam.put("ezReqJobStatus",bizMsg.ezReqJobStatus.getValue());
        }
        
        // Register Start Time
        if (!bizMsg.ezReqInputDate_ST.isClear()){
            mapParam.put("ezReqStartTime", bizMsg.ezReqInputDate_ST.getValue() + bizMsg.ezReqInputTime_ST.getValue());    
        }
        
        // Register End Time       
        if (!bizMsg.ezReqInputDate_EN.isClear()){
            mapParam.put("ezReqEndTime", bizMsg.ezReqInputDate_EN.getValue() + bizMsg.ezReqInputTime_EN.getValue());   
        }
        
        // Job Start Time
        if (!bizMsg.ezReqJobStartDate.isClear()){
            mapParam.put("ezReqJobStartDate", bizMsg.ezReqJobStartDate.getValue());
            mapParam.put("ezReqJobStartTime", bizMsg.ezReqJobStartTime.getValue()); 
        }
        
        // Job End Time
        if (!bizMsg.ezReqJobEndDate.isClear()){
            mapParam.put("ezReqJobEndDate", bizMsg.ezReqJobEndDate.getValue()); 
            mapParam.put("ezReqJobEndTime", bizMsg.ezReqJobEndTime.getValue());             
        }
        
        ZZBL0030_ASMsgArray resultObject = sesMsg.A;
        
        int hitCount =0; 
        try {
            S21SsmEZDClient ssmClient = S21SsmEZDClient.getClient(this.getClass());
            S21SsmEZDResult result = ssmClient.queryEZDMsgArray("getART10Records", mapParam, resultObject);
            if (result != null) {
                hitCount = resultObject.getValidCount();
                if (result.getQueryResultCount() > sesMsg.A.length()) {
                	 bizMsg.setMessageInfo("ZZZM9002W");
                } else if (hitCount == 0 ){
                	bizMsg.A.setValidCount(0);
                	bizMsg.setMessageInfo("ZZZM9005W");
                }
            }
        }catch (Exception e)
        {
            // bizMsg.setMessageInfo("ZZSM4101E", ZZSL1000Common.getErrorMessage(e.getMessage()));
        	EZDDebugOutput.println(1, e.getMessage(), null);
        }
        return hitCount;
    }
}
