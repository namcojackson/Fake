/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1200;

import static business.blap.NSAL1200.constant.NSAL1200Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Counter Group Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   Hitachi         Y.Takeno        Create          N/A
 * 2017/08/03   Hitachi         T.Kanasaka      Update          QC#18193,18195
 * 2017/09/13   Hitachi         T.Kanasaka      Update          QC#21070
 *</pre>
 */
public final class NSAL1200Query extends S21SsmEZDQuerySupport {
    /** Singleton instance */
    private static final NSAL1200Query INSTANCE = new NSAL1200Query();

    /**
     * Private constructor
     */
    private NSAL1200Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL1200Query singleton instance
     */
    public static NSAL1200Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL1200CMsg
     * @param sMsg NSAL1200SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mtrGrpPk_H", cMsg.mtrGrpPk_H);
        params.put("limitNum", sMsg.A.length() + 1);
        // START 2017/08/03 T.Kanasaka [QC#18193,18195,ADD]
        params.put("bllgMtrMapLvlNum_1", BLLG_MTR_MAP_LVL_NUM_1);
        params.put("bllgMtrMapLvlNum_2", BLLG_MTR_MAP_LVL_NUM_2);
        params.put("bllgMtrMapLvlNum_3", BLLG_MTR_MAP_LVL_NUM_3);
        // END 2017/08/03 T.Kanasaka [QC#18193,18195,ADD]
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", params, sMsg.A);
    }

    /**
     * getBllgMtrMap
     * @param glblCmpyCd glblCmpyCd
     * @param slsDate slsDate
     * @param mdlMtrLbCd mdlMtrLbCd
     * @param bllgMtrLbCd bllgMtrLbCd
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBllgMtrMap(String glblCmpyCd, String slsDate, String mdlMtrLbCd, String bllgMtrLbCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("slsDate", slsDate);
        params.put("mdlMtrLbCd", mdlMtrLbCd);
        params.put("bllgMtrLbCd", bllgMtrLbCd);
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getBllgMtrMap", params);
    }

    /**
     * getBllgMtrMapByDescTxt
     * @param glblCmpyCd glblCmpyCd
     * @param slsDate slsDate
     * @param mdlMtrLbCd mdlMtrLbCd
     * @param mtrLbDescTxt mtrLbDescTxt
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBllgMtrMapByDescTxt(String glblCmpyCd, String slsDate, String mdlMtrLbCd, String mtrLbDescTxt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("slsDate", slsDate);
        // START 2017/08/03 T.Kanasaka [QC#18193,18195,DEL]
//        params.put("mdlMtrLbCd", mdlMtrLbCd);
        // END 2017/08/03 T.Kanasaka [QC#18193,18195,DEL]
        params.put("mtrLbDescTxt", mtrLbDescTxt);
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getBllgMtrMap", params);
    }

    /**
     * getMtrLbMap
     * @param glblCmpyCd glblCmpyCd
     * @param slsDate slsDate
     * @param mtrLbCd mtrLbCd
     * @param mtrLbTpCd mtrLbTpCd
     * @param mtrIdxCd mtrIdxCd
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMtrLbMap(String glblCmpyCd, String slsDate, String mtrLbCd, String mtrLbTpCd, String mtrIdxCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("slsDate", slsDate);
        params.put("mtrLbCd", mtrLbCd);
        params.put("mtrLbTpCd", mtrLbTpCd);
        params.put("mtrIdxCd", mtrIdxCd);
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getMtrLbMap", params);
    }

    /**
     * getMtrLbMapByDescTxt
     * @param glblCmpyCd glblCmpyCd
     * @param slsDate slsDate
     * @param mtrLbDescTxt mtrLbDescTxt
     * @param mtrLbTpCd mtrLbTpCd
     * @param mtrIdxCd mtrIdxCd
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMtrLbMapByDescTxt(String glblCmpyCd, String slsDate, String mtrLbDescTxt, String mtrLbTpCd, String mtrIdxCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("slsDate", slsDate);
        params.put("mtrLbDescTxt", mtrLbDescTxt);
        params.put("mtrLbTpCd", mtrLbTpCd);
        params.put("mtrIdxCd", mtrIdxCd);
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getMtrLbMap", params);
    }

    // START 2017/09/13 T.Kanasaka [QC#21070,ADD]
    /**
     * getBllgMtrLbCdGreaterThanLv3
     * @param glblCmpyCd glblCmpyCd
     * @param slsDate slsDate
     * @param mtrGrpPk mtrGrpPk
     * @param mdlMtrLbCd mdlMtrLbCd
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBllgMtrLbCdGreaterThanLv3(String glblCmpyCd, String slsDate, BigDecimal mtrGrpPk, String mdlMtrLbCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("slsDate", slsDate);
        params.put("mtrGrpPk", mtrGrpPk);
        params.put("mdlMtrLbCd", mdlMtrLbCd);
        params.put("bllgMtrMapLvlNum_3", BLLG_MTR_MAP_LVL_NUM_3);
        return getSsmEZDClient().queryObjectList("getBllgMtrLbCdGreaterThanLv3", params);
    }
    // END 2017/09/13 T.Kanasaka [QC#21070,ADD]
}
