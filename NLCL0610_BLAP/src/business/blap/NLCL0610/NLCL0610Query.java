/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0610;

import static business.blap.NLCL0610.constant.NLCL0610Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NLCL0610.constant.NLCL0610Constant.DB_PARAM_LOC_TP_CD_LIST;
import static business.blap.NLCL0610.constant.NLCL0610Constant.DB_PARAM_MAX_ROWNUM;
import static business.blap.NLCL0610.constant.NLCL0610Constant.DB_PARAM_PHYS_INVTY_CTRL_NM;
import static business.blap.NLCL0610.constant.NLCL0610Constant.DB_PARAM_PHYS_INVTY_DT_FROM;
import static business.blap.NLCL0610.constant.NLCL0610Constant.DB_PARAM_PHYS_INVTY_DT_THRU;
import static business.blap.NLCL0610.constant.NLCL0610Constant.DB_PARAM_PHYS_INVTY_NUM;
import static business.blap.NLCL0610.constant.NLCL0610Constant.DB_PARAM_PHYS_INVTY_STS_CD_OPEN;
import static business.blap.NLCL0610.constant.NLCL0610Constant.DB_PARAM_PHYS_INVTY_STS_CD_SCHEDULED;
import static business.blap.NLCL0610.constant.NLCL0610Constant.DB_PARAM_RTL_WH_CD;
import static business.blap.NLCL0610.constant.NLCL0610Constant.DB_PARAM_TECH_PI_CANC_CNT_STS_CD;
import static business.blap.NLCL0610.constant.NLCL0610Constant.DB_PARAM_WH_OWNR_CD_LIST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * PI Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   CITS            T.Gotoda        Create          N/A
 * 2016/11/08   CITS            R.Shimamoto     Update          V0.2
 *</pre>
 */
public final class NLCL0610Query extends S21SsmEZDQuerySupport {
    /**
     * Singleton instance.
     */
    private static final NLCL0610Query INSTANCE = new NLCL0610Query();

    /**
     * Constructor.
     */
    private NLCL0610Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLCL0610Query
     */
    public static NLCL0610Query getInstance() {
        return INSTANCE;
    }

    /**
     * Search PI List
     * @param cMsg NLCL0610CMsg
     * @param sMsg NLCL0610SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchPiList(NLCL0610CMsg cMsg, NLCL0610SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_DT_FROM, cMsg.physInvtyDt_F);
        ssmParam.put(DB_PARAM_PHYS_INVTY_DT_THRU, cMsg.physInvtyDt_T);
        ssmParam.put(DB_PARAM_PHYS_INVTY_CTRL_NM, cMsg.physInvtyCtrlNm);

        ArrayList<String> whPilocTpCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(cMsg.varCharConstVal_LT)) {
            String[] whPilocTpCdArray = cMsg.varCharConstVal_LT.getValue().split(",");
            for (String code : whPilocTpCdArray) {
                whPilocTpCdList.add(code.trim());
            }
        }
        ssmParam.put(DB_PARAM_LOC_TP_CD_LIST, whPilocTpCdList);

        ArrayList<String>  whPiWhOwnrCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(cMsg.varCharConstVal_WO)) {
            String[] whPiWhOwnrCdArray = cMsg.varCharConstVal_WO.getValue().split(",");
            for (String code : whPiWhOwnrCdArray) {
                whPiWhOwnrCdList.add(code.trim());
            }
        }
        ssmParam.put(DB_PARAM_WH_OWNR_CD_LIST, whPiWhOwnrCdList);

        ssmParam.put(DB_PARAM_MAX_ROWNUM, sMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("searchPiList", ssmParam, sMsg.A);
    }

    /**
     * Count PI for Edit
     * @param glblCmpyCd String
     * @param physInvtyNum String
     * @return S21SsmEZDResult 
     */
    public S21SsmEZDResult countPiForEdit(String glblCmpyCd, String physInvtyNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, physInvtyNum);
        ssmParam.put(DB_PARAM_PHYS_INVTY_STS_CD_SCHEDULED, PHYS_INVTY_STS.SCHEDULED);

        return getSsmEZDClient().queryObject("countPiForEdit", ssmParam);
    }

    // 2016/11/08 V0.2 Add Start.
    /**
     * Count PI for Cancel
     * @param glblCmpyCd String
     * @param physInvtyNum String
     * @param techPiCancCntAtsCdList ArrayList<String>
     * @return S21SsmEZDResult 
     */
    public S21SsmEZDResult countPiForCancel(String glblCmpyCd, String physInvtyNum, ArrayList<String> techPiCancCntAtsCdList) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, physInvtyNum);
        ssmParam.put(DB_PARAM_PHYS_INVTY_STS_CD_SCHEDULED, PHYS_INVTY_STS.SCHEDULED);
        ssmParam.put(DB_PARAM_PHYS_INVTY_STS_CD_OPEN, PHYS_INVTY_STS.OPEN);
        ssmParam.put(DB_PARAM_TECH_PI_CANC_CNT_STS_CD, techPiCancCntAtsCdList);

        return getSsmEZDClient().queryObject("countPiForCancel", ssmParam);
    }
    // 2016/11/08 V0.2 Add End.

    /**
     * Count PI for Edit
     * @param cMsg NLCL0610CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWarehouseName(NLCL0610CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd);

        ArrayList<String> whPilocTpCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(cMsg.varCharConstVal_LT)) {
            String[] whPilocTpCdArray = cMsg.varCharConstVal_LT.getValue().split(",");
            for (String code : whPilocTpCdArray) {
                whPilocTpCdList.add(code.trim());
            }
        }
        ssmParam.put(DB_PARAM_LOC_TP_CD_LIST, whPilocTpCdList);

        ArrayList<String>  whPiWhOwnrCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(cMsg.varCharConstVal_WO)) {
            String[] whPiWhOwnrCdArray = cMsg.varCharConstVal_WO.getValue().split(",");
            for (String code : whPiWhOwnrCdArray) {
                whPiWhOwnrCdList.add(code.trim());
            }
        }
        ssmParam.put(DB_PARAM_WH_OWNR_CD_LIST, whPiWhOwnrCdList);

        return getSsmEZDClient().queryObjectList("getWarehouseName", ssmParam);
    }

}
