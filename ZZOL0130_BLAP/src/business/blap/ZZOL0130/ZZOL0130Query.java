package business.blap.ZZOL0130;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZOL0130Query extends S21SsmEZDQuerySupport {
	/**
	 * Singleton instance.
	 */
	private static final ZZOL0130Query myInstance = new ZZOL0130Query();

	/**
	 * Constructor.
	 */
	private ZZOL0130Query() {
	    super();
	}

	/**
	 * Singleton instance getter.
	 * @return	SSML0001Query
	 */
	public static ZZOL0130Query getInstance() {
	    return myInstance;
	}

	/**
	 * 
	 * @param cMsg
	 * @return
	 */
	public S21SsmEZDResult getDelTblList(ZZOL0130CMsg cMsg,ZZOL0130SMsg sMsg){
		
		Map<String,Object> ssmParam = new HashMap<String,Object>();
		ssmParam.put("rowNum", sMsg.A.length()+1);
		ssmParam.put("cMsg",cMsg);
        
		return getSsmEZDClient().queryEZDMsgArray( "getDelTblList", ssmParam, sMsg.A );
	}
    
    /**
     * 
     * @param cMsg
     * @return
     */
    public S21SsmEZDResult getDuplicateCount(ZZOL0130CMsg cMsg){
        
        return getSsmEZDClient().queryObject( "getDuplicateCount", cMsg);
    }
    
    /**
     * 
     * @param cMsg
     * @return
     */
    public S21SsmEZDResult isTableExist(ZZOL0130CMsg cMsg){
        
        return getSsmEZDClient().queryObject( "isTableExist", cMsg);
    }
}
