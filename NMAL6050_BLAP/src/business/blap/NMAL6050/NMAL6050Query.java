package business.blap.NMAL6050;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

public final class NMAL6050Query extends S21SsmEZDQuerySupport {
	
	/**
	 * Singleton instance.
	 */
	private static final NMAL6050Query myInstance = new NMAL6050Query();

	/**
	 * Constructor.
	 */
	private NMAL6050Query() {
	    super();
	}

	/**
	 * Singleton instance getter.
	 * @return	NMAL6050Query
	 */
	public static NMAL6050Query getInstance() {
	    return myInstance;
	}

	/**
	 * 「NMAL6050Query.xml」id="getNMAL6050_01"search
	 * @param	cMsg	NMAL6050CMsg
	 * @return	S21SsmEZDResult
	 */
	public S21SsmEZDResult getNMAL6050( NMAL6050CMsg cMsg, NMAL6050SMsg asMsg ) {
		
        S21UserProfileService profileAccessor = getUserProfileService();
        String globalCmpyCd = profileAccessor.getGlobalCompanyCode();
		
		Map<String, Object> ssmParam = new HashMap<String, Object>();
		ssmParam.put( "rowNum", asMsg.A.length() + 1 );
		ssmParam.put( "cMsg", cMsg );
		ssmParam.put( "globalCmpyCd", globalCmpyCd );
		// get column
		ssmParam.put( "xxTblCdColNm", cMsg.xxTblCdColNm.getValue() );
		ssmParam.put( "xxTblNmColNm", cMsg.xxTblNmColNm.getValue() );
		// table		
		ssmParam.put( "xxTblNm", cMsg.xxTblNm.getValue() );
		// sort column
        if (ZYPCommonFunc.hasValue(cMsg.xxTblSortColNm)) {
            ssmParam.put( "xxTblSortColNm", cMsg.xxTblSortColNm.getValue() );
        } else {
            ssmParam.put( "xxTblSortColNm", cMsg.xxTblCdColNm.getValue() );
        }
		// value
		ssmParam.put( "xxCondCd",  cMsg.xxCondCd.getValue() );
		ssmParam.put( "xxCondNm",  cMsg.xxCondNm.getValue() );
		
		return getSsmEZDClient().queryEZDMsgArray( "getNMAL6050", ssmParam, asMsg.A );
	}

}
