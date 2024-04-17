/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/12/2010   Fujitsu         S.Takami        Create          534(All2)
 * 02/04/2011	Fujitsu			S.Takami		Update			1313(Prod)
 * </pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.constant.cdtable.TOP_STOP_STS.ACTIVE;

import java.util.HashMap;
import java.util.Map;

//import business.db.TOP_STOP_LVLTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

public class NWXC001001TopStopMdseChk {

    private static final S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(NWXC001001TopStopMdseChk.class);

    /**
     * isTopStopMdse, check the mdse is target of TOP STOP or not.
     * @param glblCmpyCd Global Company Code
     * @param mdseCd merchandise code(target of checking)
     * @param slsDt sales date
     * @return true: the merchandise is target of TOP STOP false: the merchandise is not target of TOP STOP
     */
    @SuppressWarnings("unchecked")
    public static boolean isTopStopMdse(String glblCmpyCd, String mdseCd, String slsDt) {

//        boolean result = false;
//
//        if (!hasValue(glblCmpyCd) || !hasValue(mdseCd) || !hasValue(slsDt)) {
//            return result;
//        }
//
//        // 1.Search Top Stop Setup
//        TOP_STOP_LVLTMsg topStopresult = getStopStopLvl(glblCmpyCd, mdseCd, slsDt); 
//        if (topStopresult != null) {
//                result = true;
//        }
//        return result;
      return true;
    }
//    
//    @SuppressWarnings("unchecked")
//    private static TOP_STOP_LVLTMsg getStopStopLvl(String glblCmpyCd, String mdseCd, String slsDt) {
//        
//        final Map<String, Object> ssmParam = new HashMap<String, Object>();
//        String mdseCd8digit = null;
//        if (mdseCd.length() > 8) {
//        	mdseCd8digit = mdseCd.substring(0,8);
//        }
//        ssmParam.put("glblCmpyCd",		glblCmpyCd);
//        ssmParam.put("mdseCd",			mdseCd);
//        ssmParam.put("mdseCd8digit",	mdseCd8digit);
//        ssmParam.put("slsDt",			slsDt);
//        ssmParam.put("topStopStsCd", 	ACTIVE);
//
//        return (TOP_STOP_LVLTMsg)ssmClient.queryObject("getTopStopLvl", ssmParam);
//    }
}
