/*
 * <pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6040;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * <pre>
 * NMAL6040 P&L Product Structure Pop Up
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ----------------------------------------------------------------------
 * 09/05/2012   Fujitsu         H.Mizutani      Update          N/A 
 * 13/09/2013   Fujitsu         A.Shinohara     Update          R-MS001
 *</pre>
 */
public final class NMAL6040Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL6040Query myInstance = new NMAL6040Query();

    /**
     * Constructor.
     */
    private NMAL6040Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL6040Query
     */
    public static NMAL6040Query getInstance() {
        return myInstance;
    }

    /**
     * NMAL6040Query id=getCpoOrdTpCdList search
     * @param cMsg NMAL6040CMsg
     * @param sMsg NMAL6040SMsg
     * @return S21SsmEZDResult
     */
    // 20120905 Mizutani Delete
    //@SuppressWarnings("boxing")
    public S21SsmEZDResult getALL_MDSE_V(NMAL6040CMsg cMsg, NMAL6040SMsg sMsg) {

        S21UserProfileService profileAccessor = getUserProfileService();
        String globalCmpyCd = profileAccessor.getGlobalCompanyCode();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", sMsg.A.length() + 1);
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("globalCmpyCd", globalCmpyCd);
        ssmParam.put("zerothProdCtrlCd", cMsg.zerothProdCtrlCd.getValue());
        ssmParam.put("zerothProdCtrlNm", cMsg.zerothProdCtrlNm.getValue());
        ssmParam.put("firstProdCtrlCd", cMsg.firstProdCtrlCd.getValue());
        ssmParam.put("firstProdCtrlNm", cMsg.firstProdCtrlNm.getValue());
        ssmParam.put("scdProdCtrlCd", cMsg.scdProdCtrlCd.getValue());
        ssmParam.put("scdProdCtrlNm", cMsg.scdProdCtrlNm.getValue());
        ssmParam.put("thirdProdCtrlCd", cMsg.thirdProdCtrlCd.getValue());
        ssmParam.put("thirdProdCtrlNm", cMsg.thirdProdCtrlNm.getValue());
        ssmParam.put("frthProdCtrlCd", cMsg.frthProdCtrlCd.getValue());
        ssmParam.put("frthProdCtrlNm", cMsg.frthProdCtrlNm.getValue());
        ssmParam.put("fifthProdCtrlCd", cMsg.fifthProdCtrlCd.getValue());
        ssmParam.put("fifthProdCtrlNm", cMsg.fifthProdCtrlNm.getValue());
        ssmParam.put("mdseCd", cMsg.mdseCd.getValue());
        ssmParam.put("mdseNm", cMsg.mdseNm.getValue());
        ssmParam.put("upcCd", cMsg.upcCd.getValue());

        // 2013/05/13 Mod START R-MS001
        ssmParam.put("mdseCatgCd", cMsg.mdseCatgCd.getValue());
//        // 20120905 Deviation of Items in All Merchandise View - Mizutani 
//        ssmParam.put("oldMatNum", cMsg.oldMatNum.getValue());
//        ssmParam.put("lgcyOldMatCd", cMsg.lgcyOldMatCd.getValue());
//        ssmParam.put("dsMatGrpCd", cMsg.dsMatGrpCd.getValue());
//        ssmParam.put("xtrnlMatGrpCd", cMsg.xtrnlMatGrpCd.getValue());
//        ssmParam.put("mdseRgtnTpCd", cMsg.mdseRgtnTpCd.getValue());
//        // 20120905 Deviation of Items in All Merchandise View - Mizutani 
        // 2013/05/13 Mod E N D R-MS001

        return getSsmEZDClient().queryEZDMsgArray("getALL_MDSE_V", ssmParam, sMsg.A);
    }

    /**
     * 「NMAL6040Query.xml」id=getALL_MDSE_V_Structure search
     * @param cMsg NMAL6040CMsg
     * @param sMsg NMAL6040SMsg
     * @return S21SsmEZDResult
     */
    // 20120905 Mizutani Delete
    //@SuppressWarnings("boxing")
    public S21SsmEZDResult getALL_MDSE_V_Structure(NMAL6040CMsg cMsg, NMAL6040SMsg sMsg) {

        S21UserProfileService profileAccessor = getUserProfileService();
        String globalCmpyCd = profileAccessor.getGlobalCompanyCode();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", sMsg.A.length() + 1);
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("globalCmpyCd", globalCmpyCd);
        ssmParam.put("zerothProdCtrlCd", cMsg.zerothProdCtrlCd.getValue());
        ssmParam.put("zerothProdCtrlNm", cMsg.zerothProdCtrlNm.getValue());
        ssmParam.put("firstProdCtrlCd", cMsg.firstProdCtrlCd.getValue());
        ssmParam.put("firstProdCtrlNm", cMsg.firstProdCtrlNm.getValue());
        ssmParam.put("scdProdCtrlCd", cMsg.scdProdCtrlCd.getValue());
        ssmParam.put("scdProdCtrlNm", cMsg.scdProdCtrlNm.getValue());
        ssmParam.put("thirdProdCtrlCd", cMsg.thirdProdCtrlCd.getValue());
        ssmParam.put("thirdProdCtrlNm", cMsg.thirdProdCtrlNm.getValue());
        ssmParam.put("frthProdCtrlCd", cMsg.frthProdCtrlCd.getValue());
        ssmParam.put("frthProdCtrlNm", cMsg.frthProdCtrlNm.getValue());
        ssmParam.put("fifthProdCtrlCd", cMsg.fifthProdCtrlCd.getValue());
        ssmParam.put("fifthProdCtrlNm", cMsg.fifthProdCtrlNm.getValue());


        boolean hasMdseCondition = hasMdseCondition(cMsg);
        if (hasMdseCondition) {
            ssmParam.put("hasMdseCondition", "1");
        } else {
            ssmParam.put("hasMdseCondition", "0");
        }
        ssmParam.put("mdseCd", cMsg.mdseCd.getValue());
        ssmParam.put("mdseNm", cMsg.mdseNm.getValue());
        ssmParam.put("upcCd", cMsg.upcCd.getValue());

        // 2013/05/13 Mod START R-MS001
        ssmParam.put("mdseCatgCd", cMsg.mdseCatgCd.getValue());
//        // 20120905 Deviation of Items in All Merchandise View - Mizutani 
//        ssmParam.put("oldMatNum", cMsg.oldMatNum.getValue());
//        ssmParam.put("lgcyOldMatCd", cMsg.lgcyOldMatCd.getValue());
//        ssmParam.put("dsMatGrpCd", cMsg.dsMatGrpCd.getValue());
//        ssmParam.put("xtrnlMatGrpCd", cMsg.xtrnlMatGrpCd.getValue());
//        ssmParam.put("mdseRgtnTpCd", cMsg.mdseRgtnTpCd.getValue());
//        // 20120905 Deviation of Items in All Merchandise View - Mizutani 
        // 2013/05/13 Mod E N D R-MS001
        return getSsmEZDClient().queryEZDMsgArray("getALL_MDSE_V_Structure", ssmParam, sMsg.A);
    }

    private boolean hasMdseCondition(NMAL6040CMsg cMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.mdseCd)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.mdseNm)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.upcCd)) {
            return true;
        }
        // 2013/05/13 Mod START R-MS001
        if (ZYPCommonFunc.hasValue(cMsg.mdseCatgCd)) {
            return true;
        }
//        if (ZYPCommonFunc.hasValue(cMsg.oldMatNum)) {
//            return true;
//        }
//        if (ZYPCommonFunc.hasValue(cMsg.lgcyOldMatCd)) {
//            return true;
//        }
//        if (ZYPCommonFunc.hasValue(cMsg.dsMatGrpCd)) {
//            return true;
//        }
//        if (ZYPCommonFunc.hasValue(cMsg.xtrnlMatGrpCd)) {
//            return true;
//        }
//        if (ZYPCommonFunc.hasValue(cMsg.mdseRgtnTpCd)) {
//            return true;
//        }
        // 2013/05/13 Mod E N D R-MS001
        return false;
    }
    public S21SsmEZDResult getProductLevelName(NMAL6040CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getProductLevelName", ssmParam);
    }
}
