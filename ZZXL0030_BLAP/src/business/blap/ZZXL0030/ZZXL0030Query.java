package business.blap.ZZXL0030;

import java.util.HashMap;
import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import business.blap.ZZXL0030.ZZXL0030CMsg;
import business.blap.ZZXL0030.ZZXL0030Query;
import business.blap.ZZXL0030.ZZXL0030SMsg;
import business.blap.ZZXL0030.ZZXL0030_DSMsgArray;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmEZDClient;

public class ZZXL0030Query {
	
    /**
     * Singleton instance.
     */
    private static final ZZXL0030Query myInstance = new ZZXL0030Query();

    /**
     * Constructor.
     */
    private ZZXL0030Query() {
        super();
    }
        
    /**
     * Singleton instance getter.
     * @return  SSML0002Query
     */
    public static ZZXL0030Query getInstance() {
        return myInstance;
    }    

    /**
     * This function retreives CAL table based on parameters.
     * @param cMsg
     * @param sMsg
     * @return
     */
    public int getCalendar(EZDCMsg cMsg, EZDSMsg sMsg){
        
        ZZXL0030CMsg bizMsg = (ZZXL0030CMsg) cMsg;
        ZZXL0030SMsg sesMsg = (ZZXL0030SMsg) sMsg;
        
        HashMap<String, Object> mapParam = new HashMap<String, Object>();

        // Calendar type
        if (!bizMsg.calTpCd.isClear()) {
            mapParam.put("calTpCd", bizMsg.calTpCd.getValue());
        }
        
        // Global company code. S21 only.
        if (!bizMsg.glblCmpyCd.isClear()) {
            mapParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        }
        
        // Search Start/End dates
        mapParam.put("calDtFrom", sesMsg.effFromDt.getValue());
        mapParam.put("calDtTo", sesMsg.effToDt.getValue());
        
        ZZXL0030_DSMsgArray resultObject = sesMsg.D;
        
        try {
            S21SsmEZDClient ssmClient = S21SsmEZDClient.getClient(this.getClass());
            ssmClient.queryEZDMsgArray("getCalendar", mapParam, resultObject);
            bizMsg.xxLastBtnNm.clear();
            sesMsg.D.setValidCount(resultObject.getValidCount());
        }catch (Exception e)
        {
            // bizMsg.setMessageInfo("ZZSM4101E", ZZSL1000Common.getErrorMessage(e.getMessage()));
        	EZDDebugOutput.println(1, e.getMessage(), null);
        }
        return resultObject.getValidCount();
    }
    
    /**
     * This function searches CAL_TP table. 
     * @param cMsg
     * @return
     */
    public int getCalendarType(EZDCMsg cMsg) {
        
        ZZXL0030CMsg bizMsg = (ZZXL0030CMsg) cMsg;
        
        HashMap<String, Object> mapParam = new HashMap<String, Object>();

        if (!bizMsg.glblCmpyCd.isClear()) {
            mapParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        }
        
        ZZXL0030_CCMsgArray resultObject = bizMsg.C;
        
        try {
            S21SsmEZDClient ssmClient = S21SsmEZDClient.getClient(this.getClass());
            ssmClient.queryEZDMsgArray("getCalendarType", mapParam, resultObject);
        }catch (Exception e)
        {
            // bizMsg.setMessageInfo("ZZSM4101E", ZZSL1000Common.getErrorMessage(e.getMessage()));
        	EZDDebugOutput.println(1, e.getMessage(), null);
        }
        return resultObject.getValidCount();
    }
}

