/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0380;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NSAL0380.constant.NSAL0380Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CRAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/19   Fujitsu         T.Yoshida       Create          N/A
 * 2015/12/15   Hitachi         T.Tsuchida      Update          QC#1964
 * 2016/02/15   Hitachi         O.Okuma         Update          QC3029
 * 2016/03/04   Hitachi         K.Kasai         Update          QC3024
 * 2017/12/21   Hitachi         M.Kidokoro      Update          QC#22660
 * 2018/08/14   Hitachi         K.Kojima        Update          QC#27179
 * 2019/01/23   Fujitsu         R.Nakamura      Update          QC#29782
 *</pre>
 */
public final class NSAL0380Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0380Query INSTANCE = new NSAL0380Query();

    /**
     * Private constructor
     */
    private NSAL0380Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0380Query singleton instance
     */
    public static NSAL0380Query getInstance() {
        return INSTANCE;
    }

    /**
     * get Search Data
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     * @return Search Data
     */
    public S21SsmEZDResult getSearchData(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();

        BigDecimal[] dsContrPkList = new BigDecimal[cMsg.P.getValidCount()];
        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            dsContrPkList[i] = cMsg.P.no(i).dsContrPk_P1.getValue();
        }

        // mod start 2016/03/04 CSA Defect#3024
        int listCnt = 0;
        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(cMsg.P.no(i).dsContrDtlPk_P1)) {
                listCnt++;
            }
        }
        BigDecimal[] dsContrDtlPkList = new BigDecimal[listCnt];
        if (listCnt == 0) {
            dsContrDtlPkList = null;
        } else {
            int j = 0;
            for (int i = 0; i < cMsg.P.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(cMsg.P.no(i).dsContrDtlPk_P1)) {
                    dsContrDtlPkList[j] = cMsg.P.no(i).dsContrDtlPk_P1.getValue();
                    j++;
                }
            }
        }
        // mod end 2016/03/04 CSA Defect#3024

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("serNumFrom", cMsg.serNum_HF.getValue());
        params.put("serNumThru", cMsg.serNum_HT.getValue());
        params.put("svcMachMstrPkFrom", cMsg.svcMachMstrPk_HF.getValue());
        params.put("svcMachMstrPkThru", cMsg.svcMachMstrPk_HT.getValue());
        params.put("dsContrPkList", dsContrPkList);
        params.put("dsContrDtlPkList", dsContrDtlPkList);
        params.put("onY", ZYPConstant.CHKBOX_ON_Y);
        params.put("dsContrRnwEsclPk", NSAL0380Constant.DS_CONTR_RNW_ESCL_PK);
        params.put("machLvlNum1", NSAL0380Constant.MACH_LVL_NUM_1);
        params.put("machLvlNum2", NSAL0380Constant.MACH_LVL_NUM_2);
        params.put("machLvlNum3", NSAL0380Constant.MACH_LVL_NUM_3);
        params.put("machLvlNum4", NSAL0380Constant.MACH_LVL_NUM_4);
        params.put("machLvlNum5", NSAL0380Constant.MACH_LVL_NUM_5);
        params.put("machLvlNum6", NSAL0380Constant.MACH_LVL_NUM_6);
        params.put("machLvlNum7", NSAL0380Constant.MACH_LVL_NUM_7);
        params.put("dsContrBaseUsgNm_Base", NSAL0380Constant.BASE_NM);
        params.put("dsContrBaseUsgNm_Use", NSAL0380Constant.USG_NM);
        params.put("dsContrCatgCd_Flt", DS_CONTR_CATG.FLEET);
        params.put("dsContrCatgCd_Agg", DS_CONTR_CATG.AGGREGATE);
        params.put("dsContrDtlTpCd_Fleet", DS_CONTR_DTL_TP.FLEET);
        params.put("dsContrDtlTpCd_Agg", DS_CONTR_DTL_TP.AGGREGATE);
        params.put("dsContrDtlTpCd_Acc", DS_CONTR_DTL_TP.ACCESSORIES);
        // Add Start 2019/01/23 QC#29782
        params.put("dsContrCratTp", DS_CONTR_CRAT_TP.OTHER);
        // Add End 2019/01/23 QC#29782
        params.put("rowNum", sMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getSearchData", params, sMsg.A);
    }

    /**
     * check Exist First Data
     * @param cMsg NSAL0380CMsg
     * @param bsMsg NSAL0380_BSMsg
     * @return true: exist
     */
    public boolean isExistFstData(NSAL0380CMsg cMsg, NSAL0380_BSMsg bsMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bsMsg.dsContrPk_B.getValue());
        params.put("machLvlNum1", NSAL0380Constant.MACH_LVL_NUM_1);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistFstData", params);

        return 0 < (Integer) result.getResultObject();
    }

    /**
     * check Exist Second Data
     * @param cMsg NSAL0380CMsg
     * @param bsMsg NSAL0380_BSMsg
     * @return true: exist
     */
    public boolean isExistScdData(NSAL0380CMsg cMsg, NSAL0380_BSMsg bsMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bsMsg.dsContrPk_B.getValue());
        params.put("dsContrDtlPk", bsMsg.dsContrDtlPk_B.getValue());
        params.put("machLvlNum2", NSAL0380Constant.MACH_LVL_NUM_2);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistScdData", params);

        return 0 < (Integer) result.getResultObject();
    }

    /**
     * check Exist Third Data
     * @param cMsg NSAL0380CMsg
     * @param bsMsg NSAL0380_BSMsg
     * @param isBaseFlg Base Flag
     * @return true: exist
     */
    public boolean isExistTrdData(NSAL0380CMsg cMsg, NSAL0380_BSMsg bsMsg, boolean isBaseFlg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bsMsg.dsContrPk_B.getValue());
        params.put("dsContrDtlPk", bsMsg.dsContrDtlPk_B.getValue());
        params.put("machLvlNum3", NSAL0380Constant.MACH_LVL_NUM_3);

        if (isBaseFlg) {
            params.put("baseUsgNm", NSAL0380Constant.BASE_NM);
        } else {
            params.put("baseUsgNm", NSAL0380Constant.USG_NM);
        }

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistTrdData", params);

        return 0 < (Integer) result.getResultObject();
    }

    // START 2017/12/21 M.Kidokoro [QC#22660, ADD]
    /**
     * check Exist Different Contr Eff Thru Date
     * @param glblCmpyCd String
     * @param bsMsg NSAL0380_BSMsg
     * @return true: exist
     */
    public boolean isExistDiffThruDate(String glblCmpyCd, NSAL0380_BSMsg bsMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", bsMsg.dsContrPk_B.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistDiffThruDate", params);
        if ((Integer) result.getResultObject() != null) {
            return true;
        } else {
            return false;
        }
    }
    // END 2017/12/21 M.Kidokoro [QC#22660, ADD]

    // START 2018/08/14 K.Kojima [QC#27179,ADD]
    public List<String> getContrAutoRnwTpForAutoRnw(String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        S21SsmEZDResult result = getSsmEZDClient().queryObjectList("getContrAutoRnwTpForAutoRnw", params);
        if (result.isCodeNormal()) {
            return (List<String>) result.getResultObject();
        } else {
            return null;
        }
    }
    // END 2018/08/14 K.Kojima [QC#27179,ADD]
}
