/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6700;

import static business.blap.NMAL6700.constant.NMAL6700Constant.APP_FUNC_ID_HEADER;
import static business.blap.NMAL6700.constant.NMAL6700Constant.CHILDREN_MAX_LEVEL;
import static business.blap.NMAL6700.constant.NMAL6700Constant.MAX_DT;
import static business.blap.NMAL6700.constant.NMAL6700Constant.MAX_EFF_THRU_DT;
import static business.blap.NMAL6700.constant.NMAL6700Constant.PARENT_MAX_LEVEL;
import static business.blap.NMAL6700.constant.NMAL6700Constant.RGTN_STS_CD_ACTIVE;
import static business.blap.NMAL6700.constant.NMAL6700Constant.RGTN_STS_CD_INACTIVE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_EFF_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_RULE_RCPNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PMIT_LVL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/02/21   Fujitsu         N.Sugiura       Update          QC#15971
 * 2017/07/10   Hitachi         J.Kim           Update          QC#16966
 * 2017/08/08   Fujitsu         N.Sugiura       Update          QC#8598
 * 2017/08/09   Hitachi         J.Kim           Update          QC#20184
 * 2017/10/03   Fujitsu         T.Aoi           Update          QC#21198
 * 2017/11/16   Fujitsu         H.Sugawara      Update          QC#17322(Sol#174)
 * 2017/12/18   Fujitsu         Hd.Sugawara     Update          QC#22286
 * 2018/02/14   Fujitsu         M.Ohno          Update          QC#20297(Sol#379)
 * 2018/05/21   Fujitsu         M.Ohno          Update          QC#26042
 * 2018/08/30   Fujitsu         W.Honda         Update          QC#27869
 * 2018/09/12   Fujitsu         Mz.Takahashi    Update          QC#28165
 * 2018/10/09   Fujitsu         Hd.Sugawara     Update          QC#27598
 * 2019/05/23   Fujitsu         Hd.Sugawara     Update          QC#50101
 * 2019/08/07   Fujitsu         Hd.Sugawara     Update          QC#52385
 * 2019/12/13   Fujitsu         M.Nakamura      Update          QC#54882
 * 2020/10/02   CITS            J.Evangelista   Update          QC#57745
 * 2021/05/20   CITS            M.Furugoori     Update          QC#58743
 * 2022/05/31   CITS            K.Ogino         Update          QC#59957
 * 2022/07/28   CITS            A.Cullano       Update          QC#60173
 * 2023/01/13   Hitachi         H.Watanabe      Update          QC#60860
 * 2023/01/20   Hitachi         S.Fujita        Update          QC#61011
 * 2023/07/18   Hitachi         T.Doi           Update          QC#61421
 *</pre>
 */
public final class NMAL6700Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL6700Query INSTANCE = new NMAL6700Query();

    /**
     * Constructor.
     */
    private NMAL6700Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL6700Query
     */
    public static NMAL6700Query getInstance() {
        return INSTANCE;
    }

    /**
     * getDsAcctInfo
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param showInactiveFlg String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctInfo(String glblCmpyCd, String dsAcctNum, String showInactiveFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);

        return getSsmEZDClient().queryObjectList("getDsAcctInfo", ssmParam);
    }

    /**
     * getPrinCust
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrinCust(String glblCmpyCd, String dsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        // START 2020/10/02 J.Evangelista [QC#57745,ADD]
        ssmParam.put("rgtnStsCd", RGTN_STS.TERMINATED);
        // END   2020/10/02 J.Evangelista [QC#57745,ADD]

        return getSsmEZDClient().queryObjectList("getPrinCust", ssmParam);
    }

    /**
     * getPrinCustBefCreateAcctLoc
     * @param glblCmpyCd String
     * @param ptyLocPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrinCustBefCreateAcctLoc(String glblCmpyCd, BigDecimal ptyLocPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ptyLocPk", ptyLocPk);

        return getSsmEZDClient().queryObjectList("getPrinCustBefCreateAcctLoc", ssmParam);
    }

    /**
     * Once primary location flag is removed, the field PTY_LOC_PK in PRIN_CUST will also be clear.
     * Get PRIN_CUST record by CMPY_PK in DS_ACCT_CUST/DS_ACCT_PROS.
     * 
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrinCustAftDelPrimLocFlg(String glblCmpyCd, String dsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        return getSsmEZDClient().queryObjectList("getPrinCustAftDelPrimLocFlg", ssmParam);
    }

    /**
     * @param glblCmpyCd String
     * @param dsAcctNm String
     * @param excludeDsAcctNum String
     * @return S21SsmEZDResult
     */
    public String getDsAcctNumByAcctNm(String glblCmpyCd, String dsAcctNm, String excludeDsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNm", dsAcctNm);
        if (ZYPCommonFunc.hasValue(excludeDsAcctNum)) {
            ssmParam.put("excludeDsAcctNum", excludeDsAcctNum);
        }
        return (String) getSsmEZDClient().queryObject("getDsAcctNumByAcctNm", ssmParam).getResultObject();
    }

    /**
     * getProspectLocationList
     * @param glblCmpyCd String
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getProspectLocationList(String glblCmpyCd, NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", cMsg.dsAcctNum_H1);
        ssmParam.put("showInactiveFlg", cMsg.xxChkBox_AX.getValue());
        ssmParam.put("limitRownum", sMsg.A.length() + 1);
        ssmParam.put("maxEffThruDt", MAX_EFF_THRU_DT);
        String[] rgtnStsList = new String[] {RGTN_STS.PENDING_COMPLETION, RGTN_STS.READY_FOR_ORDER_TAKING};
        ssmParam.put("rgtnStsCdList", rgtnStsList);
        String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        ssmParam.put("salesDate", salesDate);
        // Filter Item

        ssmParam.put("locNum", cMsg.locNum_AD.getValue());

        return getSsmEZDClient().queryObjectList("getProspectLocationList", ssmParam);
    }

    /**
     * getLocationList
     * @param glblCmpyCd String
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocationList(String glblCmpyCd, NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        Map<String, Object> ssmParam = getSsmParam(glblCmpyCd, cMsg, sMsg, sMsg.A.length() + 1);
        return getSsmEZDClient().queryObjectList("getLocationList", ssmParam);
    }

    /**
     * @param glblCmpyCd String
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @param rownum int
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParam(String glblCmpyCd, NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int rownum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", cMsg.dsAcctNum_H1);
        ssmParam.put("showInactiveFlg", cMsg.xxChkBox_AX.getValue());
        ssmParam.put("limitRownum", rownum);
        ssmParam.put("maxEffThruDt", MAX_EFF_THRU_DT);
        String[] rgtnStsList = new String[] {RGTN_STS.PENDING_COMPLETION, RGTN_STS.READY_FOR_ORDER_TAKING};
        ssmParam.put("rgtnStsCdList", rgtnStsList);
        String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        ssmParam.put("salesDate", salesDate);
        // Filter Item

        ssmParam.put("locNum", cMsg.locNum_AD.getValue());

        return ssmParam;
    }

    /**
     * @param glblCmpyCd String
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @param rownum int
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParamCtac(String glblCmpyCd, NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int rownum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", cMsg.dsAcctNum_H1.getValue());
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_DX.getValue())) {
            ssmParam.put("ctacPsnActvFlg", ZYPConstant.FLG_ON_Y);
        }
        ssmParam.put("dsCtacPntTpCd_P", DS_CTAC_PNT_TP.PHONE_WORK);
        ssmParam.put("dsCtacPntTpCd_E", DS_CTAC_PNT_TP.EMAIL_WORK);
        if (ZYPCommonFunc.hasValue(cMsg.ctacPsnPk_DH)) {
            ssmParam.put("ctacPsnPk", cMsg.ctacPsnPk_DH.getValue());
        }
        // Add Start 2017/12/18 QC#22286
        ssmParam.put("ctacTpCd", CTAC_TP.DELIVERY_INSTALL);
        // Add End 2017/12/18 QC#22286
        // Add Start 2018/08/30 QC#27869
        ssmParam.put("active", ZYPConstant.FLG_ON_Y);
        ssmParam.put("inActive", ZYPConstant.FLG_OFF_N);
        ssmParam.put("maxDt", MAX_DT);
        ssmParam.put("salesDt", ZYPDateUtil.getSalesDate());
        // Add End 2018/08/30 QC#27869
        ssmParam.put("rowNum", rownum);
        return ssmParam;
    }

    /**
     * @param glblCmpyCd String
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRelnshipList(String glblCmpyCd, NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", cMsg.dsAcctNum_H1);
        ssmParam.put("showInactiveFlg", cMsg.xxChkBox_CX.getValue());
        // START 2017/07/06 J.Kim [QC#16966,ADD]
        ssmParam.put("dsAcctRelnTpCd_H", cMsg.dsAcctRelnTpCd_H.getValue());
        ssmParam.put("dsAcctNm_F", cMsg.dsAcctNm_F.getValue());
        ssmParam.put("dsAcctNum_F", cMsg.dsAcctNum_F.getValue());
        ssmParam.put("xxChkBox_FB", cMsg.xxChkBox_FB.getValue());
        ssmParam.put("xxChkBox_FS", cMsg.xxChkBox_FS.getValue());
        ssmParam.put("xxChkBox_FR", cMsg.xxChkBox_FR.getValue());
        ssmParam.put("effFromDt_F1", cMsg.effFromDt_F1.getValue());
        ssmParam.put("effFromDt_F2", cMsg.effFromDt_F2.getValue());
        ssmParam.put("effThruDt_F1", cMsg.effThruDt_F1.getValue());
        ssmParam.put("effThruDt_F2", cMsg.effThruDt_F2.getValue());
        ssmParam.put("highValDt", MAX_EFF_THRU_DT);
        // END 2017/07/06 J.Kim [QC#16966,ADD]
        ssmParam.put("rowNum", sMsg.C.length() + 1);

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_CX.getValue())) {
            String[] rgtnStsList = new String[] {RGTN_STS.PENDING_COMPLETION, RGTN_STS.READY_FOR_ORDER_TAKING, RGTN_STS.TERMINATED};
            ssmParam.put("rgtnStsCdList", rgtnStsList);
        } else {
            String[] rgtnStsList = new String[] {RGTN_STS.PENDING_COMPLETION, RGTN_STS.READY_FOR_ORDER_TAKING};
            ssmParam.put("rgtnStsCdList", rgtnStsList);
        }
        return getSsmEZDClient().queryObjectList("getRelnshipList", ssmParam);
    }

    /**
     * @param glblCmpyCd String
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAllRelnshipList(String glblCmpyCd, NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", cMsg.dsAcctNum_H1);
        ssmParam.put("showInactiveFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("rowNum", sMsg.C.length() + 1);
        String[] rgtnStsList = new String[] {RGTN_STS.PENDING_COMPLETION, RGTN_STS.READY_FOR_ORDER_TAKING, RGTN_STS.TERMINATED};
        ssmParam.put("rgtnStsCdList", rgtnStsList);
        return getSsmEZDClient().queryObjectList("getRelnshipList", ssmParam);
    }

    // START 2023/01/20 S.Fujita [QC#61011,ADD]
    /**
     * @param glblCmpyCd String
     * @param dsAcctRelnTpCd String
     * @return S21SsmEZDResult
     */
    public String getDsAcctRelnTpNm(String glblCmpyCd, String dsAcctRelnTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctRelnTpCd", dsAcctRelnTpCd);
        return (String) getSsmEZDClient().queryObject("getDsAcctRelnTpNm", ssmParam).getResultObject();
    }
    // END 2023/01/20 S.Fujita [QC#61011,ADD]
    /**
     * getDsAcctReln
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param relnDsAcctNum String
     * @param dsAcctRelnTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctReln(String glblCmpyCd, String dsAcctNum, String relnDsAcctNum, String dsAcctRelnTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("relnDsAcctNum", relnDsAcctNum);
        ssmParam.put("dsAcctRelnTpCd", dsAcctRelnTpCd);
        return getSsmEZDClient().queryObjectList("getDsAcctReln", ssmParam);
    }

    /**
     * getRepDsAcctReln
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param relnDsAcctNum String
     * @param dsAcctRelnTpCd String
     * @return S21SsmEZDResult
     */
     public S21SsmEZDResult getRepDsAcctReln(String glblCmpyCd, String dsAcctNum, String relnDsAcctNum, String dsAcctRelnTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("relnDsAcctNum", relnDsAcctNum);
        ssmParam.put("dsAcctRelnTpCd", dsAcctRelnTpCd);
        // START 2017/08/09 J.Kim [QC#20184,DEL]
        // ssmParam.put("effFromDt", effFromDt);
        // ssmParam.put("effThruDt", effThruDt);
        // END 2017/08/09 J.Kim [QC#20184,DEL]
        return getSsmEZDClient().queryObjectList("getRepDsAcctReln", ssmParam);
    }

    // START 2018/09/12 [QC#28165, ADD]
    /**
     * getRepDsAcctReln
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param relnDsAcctNum String
     * @param dsAcctRelnTpCd String
     * @param effFromDt String
     * @param effThruDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRepDsAcctReln(String glblCmpyCd, String dsAcctNum, String relnDsAcctNum, String dsAcctRelnTpCd, String effFromDt, String effThruDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("relnDsAcctNum", relnDsAcctNum);
        ssmParam.put("dsAcctRelnTpCd", dsAcctRelnTpCd);
        ssmParam.put("effFromDt", effFromDt);
        if (!ZYPCommonFunc.hasValue(effThruDt)) {
            effThruDt = MAX_EFF_THRU_DT;
        }
        ssmParam.put("effThruDt", effThruDt);
        return getSsmEZDClient().queryObjectList("getRepDsAcctRelnByEffDt", ssmParam);
    }
    // END 2018/09/12 [QC#28165, ADD]

    /**
     * In case of Display Account Hierarchy Get Parent Node from
     * target account#
     * @param glblCmpyCd String
     * @param targetDsAcctNum String
     * @param inActiveFlag boolean
     * @param showAllAddress boolean
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getParentNode(String glblCmpyCd, String targetDsAcctNum, boolean inActiveFlag, boolean showAllAddress) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("targetDsAcctNum", targetDsAcctNum);
        ssmParam.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        ssmParam.put("maxLevel", PARENT_MAX_LEVEL);
        if (!inActiveFlag) {
            ssmParam.put("readyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
        }
        if (!showAllAddress) {
            ssmParam.put("primaryOnly", ZYPConstant.FLG_ON_Y);
        }
        return getSsmEZDClient().queryObjectList("getParentNode", ssmParam);
    }

    // Del Start 2017/11/16 QC#17322(Sol#174)
    ///**
    // * getTreeListLeaseReln
    // * @param cMsg NMAL6710CMsg
    // * @param glblCmpyCd String
    // * @param parentDsAcctNum String
    // * @param targetDsAcctNum String
    // * @param inActiveFlag boolean
    // * @return S21SsmEZDResult
    // */
    //public S21SsmEZDResult getTreeListLeaseReln(NMAL6700CMsg cMsg, String glblCmpyCd, String parentDsAcctNum, String targetDsAcctNum, boolean inActiveFlag) {
    //
    //    Map<String, Object> ssmParam = new HashMap<String, Object>();
    //    ssmParam.put("glblCmpyCd", glblCmpyCd);
    //    ssmParam.put("parentDsAcctNum", parentDsAcctNum);
    //    ssmParam.put("targetDsAcctNum", targetDsAcctNum);
    //
    //    String rgtnStsCd = "";
    //    String dsAcctActvCustFlg = "";
    //    if (!inActiveFlag) {
    //        rgtnStsCd = RGTN_STS.READY_FOR_ORDER_TAKING;
    //        dsAcctActvCustFlg = ZYPConstant.FLG_ON_Y;
    //    }
    //    ssmParam.put("rgtnStsCd", rgtnStsCd);
    //    ssmParam.put("dsAcctActvCustFlg", dsAcctActvCustFlg);
    //
    //    // Lease  Accts Only, Lease  Bill To's Only
    //    String[] dsAcctRelnTpList = new String[] {DS_ACCT_RELN_TP.RELATED_ACCOUNT, DS_ACCT_RELN_TP.LEASE_ACCOUNT};
    //    ssmParam.put("dsAcctRelnTpList", dsAcctRelnTpList);
    //
    //    ssmParam.put("activeCd", RGTN_STS.READY_FOR_ORDER_TAKING);
    //    ssmParam.put("active", RGTN_STS_CD_ACTIVE);
    //    ssmParam.put("inActive", RGTN_STS_CD_INACTIVE);
    //
    //    return getSsmEZDClient().queryObjectList("getTreeListLeaseReln", ssmParam);
    //}
    // Del End 2017/11/16 QC#17322(Sol#174)

    /**
     * getTreeListLeaseReln
     * 
     * @param glblCmpyCd String
     * @param targetDsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getParentData(String glblCmpyCd, String targetDsAcctNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("targetDsAcctNum", targetDsAcctNum);

        return getSsmEZDClient().queryObjectList("getParentData", ssmParam);
    }

    /**
     * getTreeListLocation
     * 
     * @param cMsg NMAL6710CMsg
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param inActiveFlag boolean
     * @param showAllAddress boolean
     * 
     * @return Map<String, Object>
     */
    public S21SsmEZDResult getTreeListLocation(NMAL6700CMsg cMsg, String glblCmpyCd, String dsAcctNum, boolean inActiveFlag, boolean showAllAddress) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);

        String rgtnStsCd = "";
        String dsAcctActvCustFlg = "";

        if (!inActiveFlag) {
            rgtnStsCd = RGTN_STS.READY_FOR_ORDER_TAKING;
            dsAcctActvCustFlg = ZYPConstant.FLG_ON_Y;

        }
        if (!showAllAddress) {
            ssmParam.put("primaryOnly", ZYPConstant.FLG_ON_Y);
        }
        ssmParam.put("rgtnStsCd", rgtnStsCd);
        ssmParam.put("dsAcctActvCustFlg", dsAcctActvCustFlg);

        ssmParam.put("activeCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("active", RGTN_STS_CD_ACTIVE);
        ssmParam.put("inActive", RGTN_STS_CD_INACTIVE);

        return getSsmEZDClient().queryObjectList("getTreeListLocation", ssmParam);

    }

    /**
     * In case of Display Account Hierarchy Get Account Hierarchy from Root
     * 
     * @param glblCmpyCd String
     * @param parentDsAcctNum String
     * @param inActiveFlag boolean
     * @param showAllAddress boolean
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTreeList(String glblCmpyCd, String parentDsAcctNum, boolean inActiveFlag, boolean showAllAddress) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("parentDsAcctNum", parentDsAcctNum);
        ssmParam.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        ssmParam.put("readyForOrderTakingDisp", RGTN_STS.READY_FOR_ORDER_TAKING);
        if (!inActiveFlag) {
            ssmParam.put("readyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
            ssmParam.put("dsAcctActvCustFlg", ZYPConstant.FLG_ON_Y);
            ssmParam.put("inActiveFlag", ZYPConstant.FLG_ON_Y);
        }
        if (!showAllAddress) {
            ssmParam.put("primaryOnly", ZYPConstant.FLG_ON_Y);
        }
        ssmParam.put("active", RGTN_STS_CD_ACTIVE);
        ssmParam.put("inActive", RGTN_STS_CD_INACTIVE);
        ssmParam.put("childrenMaxLevel", CHILDREN_MAX_LEVEL);

        return getSsmEZDClient().queryObjectList("getTreeList", ssmParam);
    }

    /**
     * In case of Display Account Hierarchy Get Related Information
     * from Account# (RELATED, LEASE, ALTERNATE, SHIP TO ACCOUNT)
     * @param glblCmpyCd String
     * @param parentDsAcctNum String
     * @param targetDsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRelatedTreeList(String glblCmpyCd, String parentDsAcctNum, String targetDsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("parentDsAcctNum", parentDsAcctNum);
        ssmParam.put("targetDsAcctNum", targetDsAcctNum);
        List<String> dsAcctRelnTpCdList = new ArrayList<String>();
        ssmParam.put("dsAcctRelnTpCdList", dsAcctRelnTpCdList);

        return getSsmEZDClient().queryObjectList("getRelatedTreeList", ssmParam);

    }

    /**
     * In case of Display Account Hierarchy Get Location Information
     * from Account#
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLeefList(String glblCmpyCd, String dsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        return getSsmEZDClient().queryObjectList("getLeefList", ssmParam);

    }

    /**
     * In case of Display Account Hierarchy Get Location Information
     * from Account#
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLeefListInvisibleAddr(String glblCmpyCd, String dsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        return getSsmEZDClient().queryObjectList("getLeefListInvisibleAddr", ssmParam);

    }

    /**
     * @param glblCmpyCd String
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCtacPsnList(String glblCmpyCd, NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        Map<String, Object> ssmParam = getSsmParamCtac(glblCmpyCd, cMsg, sMsg, sMsg.D.length() + 1);
        return getSsmEZDClient().queryObjectList("getCtacPsnList", ssmParam);
    }

    /**
     * getDsAcctList
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctList(String glblCmpyCd, String dsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        return getSsmEZDClient().queryObjectList("getDsAcctList", ssmParam);
    }

    /**
     * getCertificationReqList
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCertificationReqList(String glblCmpyCd, String dsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("party", SVC_PMIT_LVL_TP.PARTY);
        ssmParam.put("dsAcctNum", dsAcctNum);
        return getSsmEZDClient().queryObjectList("getCertificationReqList", ssmParam);
    }

    /**
     * getDsAcctGrpAsgList
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param rownum int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctGrpAsgList(String glblCmpyCd, String dsAcctNum, int rownum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("rowNum", rownum);
        return getSsmEZDClient().queryObjectList("getDsAcctGrpAsgList", ssmParam);
    }

    /**
     * getDsCustTrxRuleList
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsCustTrxRuleList(String glblCmpyCd, String dsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);

        List<String> custEffLvlCdList = new ArrayList<String>();
        custEffLvlCdList.add(CUST_EFF_LVL.ACCOUNT_AND_CHILDREN);
        custEffLvlCdList.add(CUST_EFF_LVL.ACCOUNT_ONLY);
        ssmParam.put("custEffLvlCdList", custEffLvlCdList);

        return getSsmEZDClient().queryObjectList("getDsCustTrxRuleList", ssmParam);
    }

    /**
     * getDsCustSpclHdlgList
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsCustSpclHdlgList(String glblCmpyCd, String dsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);

        return getSsmEZDClient().queryObjectList("getDsCustSpclHdlgList", ssmParam);
    }

    /**
     * getDsAcctCrPrflInfo
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctCrPrflInfo(String glblCmpyCd, String dsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        return getSsmEZDClient().queryObjectList("getDsAcctCrPrflInfo", ssmParam);
    }

    /**
     * getDsCustInvRuleList
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsCustInvRuleList(String glblCmpyCd, String dsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        return getSsmEZDClient().queryObjectList("getDsCustInvRuleList", ssmParam);
    }

    /**
     * getDsAcctRefAttrbList
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctRefAttrbList(String glblCmpyCd, String dsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        return getSsmEZDClient().queryObjectList("getDsAcctRefAttrbList", ssmParam);
    }

    /**
     * getDsCustBankAcctList
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsCustBankAcctList(String glblCmpyCd, String dsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        return getSsmEZDClient().queryObjectList("getDsCustBankAcctList", ssmParam);
    }

    /**
     * getDsCustBankAcctList
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBankAcctPulldownList(String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("outboundCarrier", VND_TP.OUTBOUND_CARRIER);
        String salesDate = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());
        ssmParam.put("salesDate", salesDate);
        ssmParam.put("maxDate", MAX_EFF_THRU_DT);
        return getSsmEZDClient().queryObjectList("getBankAcctPulldownList", ssmParam);
    }

    // 2018/12/10 QC#29135 Del Start
//    /**
//     * getDsAcctCarrList
//     * @param glblCmpyCd String
//     * @param dsAcctNum String
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getDsAcctCarrList(String glblCmpyCd, String dsAcctNum) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("dsAcctNum", dsAcctNum);
//        return getSsmEZDClient().queryObjectList("getDsAcctCarrList", ssmParam);
//    }
    // 2018/12/10 QC#29135 Del End

    /**
     * getDsCustSpclMsgList
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param sMsg NMAL6700SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsCustSpclMsgList(String glblCmpyCd, String dsAcctNum, NMAL6700SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("maxEffThruDt", MAX_EFF_THRU_DT);
        ssmParam.put("rowNum", sMsg.J.length() + 1);
        return getSsmEZDClient().queryObjectList("getDsCustSpclMsgList", ssmParam);
    }

    /**
     * getDsSpclHdlgTpCdList
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsSpclHdlgTpCdList(String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getDsSpclHdlgTpCdList", ssmParam);
    }

    /**
     * getDsSpclHdlgTpValList
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsSpclHdlgTpValList(String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getDsSpclHdlgTpValList", ssmParam);
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTemplate(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", getGlobalCompanyCode());
        param.put("dsCustArTmplNm", cMsg.dsCustArTmplNm_U1.getValue());
        return getSsmEZDClient().queryEZDMsg("getTemplate", param, sMsg);
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTemplateForCredit(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", getGlobalCompanyCode());
        return getSsmEZDClient().queryEZDMsg("getTemplate", param, sMsg);
    }

    /**
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countLeaseCmpy(String dsAcctNum, String glblCmpyCd) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", glblCmpyCd);
        query.put("dsAcctNum", dsAcctNum);

        return getSsmEZDClient().queryObject("countLeaseCmpy", query);
    }

    /**
     * Count Collection Portfolio
     * @param cltPtfoCd String
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countCltPtfo(String cltPtfoCd, String glblCmpyCd) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", glblCmpyCd);
        query.put("cltPtfoCd", cltPtfoCd);

        return getSsmEZDClient().queryObject("countCltPtfo", query);
    }

    /**
     * @param glblCmpyCd String
     * @param cMsg NMAL6700CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSpclHdlgTpList(String glblCmpyCd, NMAL6700CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getSpclHdlgTpList", ssmParam);
    }

    /**
     * GetCltPtfoNm
     * @param cMsg NMAL6700CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCltPtfoNm(NMAL6700CMsg cMsg) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", getGlobalCompanyCode());
        query.put("cltPtfoCd", cMsg.cltPtfoCd_U1.getValue());

        return getSsmEZDClient().queryObject("GetCltPtfoNm", query);
    }

    /**
     * getReasonList
     * @param cMsg NMAL6700CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getReasonList(NMAL6700CMsg cMsg) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getReasonList", query);
    }

    /**
     * getPrimaryLocation
     * @param locNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrimaryLocation(String locNum) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", getGlobalCompanyCode());
        query.put("locNum", locNum);

        return getSsmEZDClient().queryObjectList("getPrimaryLocation", query);
    }

    /**
     * getLocation
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocation(String dsAcctNum) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", getGlobalCompanyCode());
        query.put("dsAcctNum", dsAcctNum);
        query.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getLocation", query);
    }

    /**
     * getProspectionLocation
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getProspectLocation(String dsAcctNum) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", getGlobalCompanyCode());
        query.put("dsAcctNum", dsAcctNum);
        query.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getProspectLocation", query);
    }

    /**
     * getBillToCust
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillToCust(String dsAcctNum) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", getGlobalCompanyCode());
        query.put("dsAcctNum", dsAcctNum);
        query.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getBillToCust", query);
    }

    /**
     * getAcctLoc
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAcctLoc(String dsAcctNum) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", getGlobalCompanyCode());
        query.put("dsAcctNum", dsAcctNum);

        return getSsmEZDClient().queryObjectList("getAcctLoc", query);
    }

    /**
     * getOtherAcctLoc
     * @param locNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOtherAcctLoc(String locNum) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", getGlobalCompanyCode());
        query.put("locNum", locNum);
        query.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);

        return getSsmEZDClient().queryObjectList("getOtherAcctLoc", query);
    }

    /**
     * getCountActiveAcctLoc
     * @param locNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCountActiveAcctLoc(String locNum) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", getGlobalCompanyCode());
        query.put("locNum", locNum);
        query.put("maxDate", MAX_EFF_THRU_DT);
        query.put("salesDate", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObject("getCountActiveAcctLoc", query);
    }

    /**
     * getBillToCustFromDsBillToCust
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillToCustNotLookRgtnStsCd(String dsAcctNum) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", getGlobalCompanyCode());
        query.put("dsAcctNum", dsAcctNum);

        return getSsmEZDClient().queryObjectList("getBillToCustNotLookRgtnStsCd", query);
    }

    /**
     * getShipToCust
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToCust(String dsAcctNum) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", getGlobalCompanyCode());
        query.put("dsAcctNum", dsAcctNum);

        return getSsmEZDClient().queryObjectList("getShipToCust", query);
    }

    /**
     * countContact
     * @param cMsg NMAL6700CMsg
     * @param gcMsg NMAL6700_GCMsg
     * @param ctacPsnPk String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countContact(NMAL6700CMsg cMsg, NMAL6700_GCMsg gcMsg, BigDecimal ctacPsnPk) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", getGlobalCompanyCode());
        query.put("dsAcctNum", cMsg.dsAcctNum_H1.getValue());
        query.put("ctacPsnPk", ctacPsnPk);
        // 2018/05/21 QC#26042 add start
        query.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        // 2018/05/21 QC#26042 add end

        return getSsmEZDClient().queryObject("countContact", query);
    }

    /**
     * countResource
     * @param gcMsg NMAL6700_GCMsg
     * @param glblCmpyCd String
     * @param mailCheckFlg String
     * @param psnCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countResource(NMAL6700_GCMsg gcMsg, String glblCmpyCd, String mailCheckFlg, String psnCd) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", glblCmpyCd);
        query.put("psnCd", psnCd);
        query.put("mailCheckFlg", mailCheckFlg);

        return getSsmEZDClient().queryObject("countResource", query);
    }

    /**
     * @param dsAcctNum String 
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNestRelationParent(String dsAcctNum, String glblCmpyCd) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", glblCmpyCd);
        query.put("dsAcctNum", dsAcctNum);
        query.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);

        return getSsmEZDClient().queryObjectList("getNestRelationParent", query);
    }

    /**
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNestRelationChild(String dsAcctNum, String glblCmpyCd) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", glblCmpyCd);
        query.put("dsAcctNum", dsAcctNum);
        query.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);

        return getSsmEZDClient().queryObjectList("getNestRelationChild", query);
    }

    /**
     * getPtyLocWrkList
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPtyLocWrkList(String dsAcctNum, String glblCmpyCd) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", glblCmpyCd);
        query.put("dsAcctNum", dsAcctNum);

        return getSsmEZDClient().queryObjectList("getPtyLocWrkList", query);
    }

    /**
     * getProsPtyLocWrkList
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getProsPtyLocWrkList(String dsAcctNum, String glblCmpyCd) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", glblCmpyCd);
        query.put("dsAcctNum", dsAcctNum);

        return getSsmEZDClient().queryObjectList("getProsPtyLocWrkList", query);
    }

    /**
     * getAltPayerList
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAltPayerList(String dsAcctNum, String glblCmpyCd) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", glblCmpyCd);
        query.put("dsAcctNum", dsAcctNum);

        return getSsmEZDClient().queryObjectList("getAltPayerList", query);
    }

    // 2019/12/13 QC#54882 Mod Start
//    /**
//     * countCpoOpenOrder
//     * 
//     * @param dsAcctNum String
//     * @param glblCmpyCd String 
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult countCpoOpenOrder(String dsAcctNum, String glblCmpyCd) {
//        Map<String, Object> query = new HashMap<String, Object>();
//        query.put("glblCmpyCd", glblCmpyCd);
//        // Mod Start 2018/10/09 QC#27598
//        //query.put("salesDate", ZYPDateUtil.getSalesDate(glblCmpyCd));
//        query.put("slsDtPast", getOneYearPast(ZYPDateUtil.getSalesDate(glblCmpyCd)));
//        // Mod End 2018/10/09 QC#27598
//        query.put("acctNum", dsAcctNum);
//        // Add Start 2018/10/09 QC#27598
//        query.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
//        query.put("flgOnY", ZYPConstant.FLG_ON_Y);
//        // Add End 2018/10/09 QC#27598
//        query.put("rowNum", 1);
//
//        return getSsmEZDClient().queryObject("cpoOpenOrder", query);
//    }
//
//    /**
//     * countCpoOpenOrder
//     * 
//     * @param dsAcctNum String
//     * @param glblCmpyCd String 
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult countOpenReturnOrder(String dsAcctNum, String glblCmpyCd) {
//        Map<String, Object> query = new HashMap<String, Object>();
//        query.put("glblCmpyCd", glblCmpyCd);
//        // Mod Start 2018/10/09 QC#27598
//        //query.put("salesDate", ZYPDateUtil.getSalesDate(glblCmpyCd));
//        query.put("slsDtPast", getOneYearPast(ZYPDateUtil.getSalesDate(glblCmpyCd)));
//        // Mod End 2018/10/09 QC#27598
//        query.put("acctNum", dsAcctNum);
//        // Add Start 2018/10/09 QC#27598
//        query.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
//        query.put("flgOnY", ZYPConstant.FLG_ON_Y);
//        // Add End 2018/10/09 QC#27598
//        query.put("rowNum", 1);
//
//        return getSsmEZDClient().queryObject("openReturnOrder", query);
//    }
//
//    /**
//     * countOpenMachineMasterCheck
//     * 
//     * @param dsAcctNum String
//     * @param glblCmpyCd String 
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult countOpenMachineMasterCheck(String dsAcctNum, String glblCmpyCd) {
//        Map<String, Object> query = new HashMap<String, Object>();
//        query.put("glblCmpyCd", glblCmpyCd);
//        // Del Start 2018/10/09 QC#27598
//        //query.put("salesDate", ZYPDateUtil.getSalesDate(glblCmpyCd));
//        // Del End 2018/10/09 QC#27598
//        query.put("acctNum", dsAcctNum);
//        // Add Start 2018/10/09 QC#27598
//        query.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
//        // Add End 2018/10/09 QC#27598
//        query.put("rowNum", 1);
//
//        query.put("w4i", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
//        query.put("instl", SVC_MACH_MSTR_STS.INSTALLED);
//        query.put("w4r", SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
//        query.put("dlrsv", SVC_MACH_MSTR_STS.DEALER_SERVICE);
//
//        return getSsmEZDClient().queryObject("openMachineMasterCheck", query);
//    }
//
//    /**
//     * countContractDsContrNum
//     * 
//     * @param dsAcctNum String
//     * @param glblCmpyCd String 
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult countContractDsContrNum(String dsAcctNum, String glblCmpyCd) {
//        Map<String, Object> query = new HashMap<String, Object>();
//        query.put("glblCmpyCd", glblCmpyCd);
//        // Del Start 2018/10/09 QC#27598
//        //query.put("salesDate", ZYPDateUtil.getSalesDate(glblCmpyCd));
//        // Del End 2018/10/09 QC#27598
//        query.put("acctNum", dsAcctNum);
//        query.put("ettlAvalFlg", ZYPConstant.FLG_ON_Y);
//        // Add Start 2018/10/09 QC#27598
//        query.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
//        // Add End 2018/10/09 QC#27598
//        query.put("rowNum", 1);
//
//        // Add Start 2018/10/09 QC#27598
//        List<String> dsContrCtrlSts = new ArrayList<String>();
//        dsContrCtrlSts.add(DS_CONTR_CTRL_STS.TERMINATED);
//        dsContrCtrlSts.add(DS_CONTR_CTRL_STS.TERMINATED_HOLD);
//        dsContrCtrlSts.add(DS_CONTR_CTRL_STS.EXPIRED);
//        dsContrCtrlSts.add(DS_CONTR_CTRL_STS.EXPIRED_HOLD);
//        dsContrCtrlSts.add(DS_CONTR_CTRL_STS.CANCELLED);
//        dsContrCtrlSts.add(DS_CONTR_CTRL_STS.DRAFT);
//        query.put("dsContrCtrlSts", dsContrCtrlSts);
//        // Add End 2018/10/09 QC#27598
//
//        return getSsmEZDClient().queryObject("contractDsContrNum", query);
//    }
//
//    /**
//     * countServiceCall
//     * 
//     * @param dsAcctNum String
//     * @param glblCmpyCd String 
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult countServiceCall(String dsAcctNum, String glblCmpyCd) {
//        Map<String, Object> query = new HashMap<String, Object>();
//        query.put("glblCmpyCd", glblCmpyCd);
//        // Mod Start 2018/10/09 QC#27598
//        //query.put("salesDate", ZYPDateUtil.getSalesDate(glblCmpyCd));
//        query.put("slsDtPast", getOneYearPast(ZYPDateUtil.getSalesDate(glblCmpyCd)));
//        // Mod End 2018/10/09 QC#27598
//        query.put("acctNum", dsAcctNum);
//        // Add Start 2018/10/09 QC#27598
//        query.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
//        // Add End 2018/10/09 QC#27598
//        query.put("rowNum", 1);
//
//        // Add Start 2018/10/09 QC#27598
//        List<String> svcTaskSts = new ArrayList<String>();
//        svcTaskSts.add(SVC_TASK_STS.CLOSED);
//        svcTaskSts.add(SVC_TASK_STS.CANCELLED);
//        query.put("svcTaskSts", svcTaskSts);
//        // Add End 2018/10/09 QC#27598
//
//        return getSsmEZDClient().queryObject("serviceCall", query);
//    }

    /**
     * countOpenTransaction
     * @param dsAcctNum String
     * @param glblCmpyCd String 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countOpenTransaction(String dsAcctNum, String glblCmpyCd) {
        Map<String, Object> query = new HashMap<String, Object>();

        query.put("glblCmpyCd", glblCmpyCd);
        query.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        query.put("acctNum", dsAcctNum);

        query.put("salesDate", ZYPDateUtil.getSalesDate(glblCmpyCd));
        query.put("flgY", ZYPConstant.FLG_ON_Y);
        query.put("flgN", ZYPConstant.FLG_OFF_N);
        query.put("svcMachTpMachine", SVC_MACH_TP.MACHINE);
        query.put("svcMachStsW4I", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        query.put("svcMachStsInstl", SVC_MACH_MSTR_STS.INSTALLED);
        query.put("svcMachStsW4R", SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        query.put("svcMachStsDlrSvc", SVC_MACH_MSTR_STS.DEALER_SERVICE);
        query.put("dsContrCtrlStsCancelled", DS_CONTR_CTRL_STS.CANCELLED);
        query.put("dsContrCtrlStsDraft", DS_CONTR_CTRL_STS.DRAFT);
        // 2021/05/20 QC#58743 Add Start
        query.put("dsContrCtrlStsTerminated", DS_CONTR_CTRL_STS.TERMINATED);
        // 2021/05/20 QC#58743 Add End
        query.put("svcTaskStsClosed", SVC_TASK_STS.CLOSED);
        query.put("svcTaskStsCancelled", SVC_TASK_STS.CANCELLED);
        query.put("maxThruDt", "99991231");
        query.put("locRoleTpShip", LOC_ROLE_TP.SHIP_TO);
        // START 2022/07/28 A.Cullano [QC#60173, ADD]
        query.put("skipRecovTpSkip", SKIP_RECOV_TP.SKIP);
        // END 2022/07/28 A.Cullano [QC#60173, ADD]

        query.put("rowNum", 1);
        
        return getSsmEZDClient().queryObject("countOpenTransaction", query);
    }
    // 2019/12/13 QC#54882 Mod End

    /**
     * getDefCoa
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefCoa(String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("appFuncId_AcctMaint", APP_FUNC_ID_HEADER);
        return getSsmEZDClient().queryObjectList("getDefCoa", ssmParam);
    }

    /**
     * @param dsCustInvRulePk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvoiceSourceListInternalReview(BigDecimal dsCustInvRulePk) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", getGlobalCompanyCode());
        query.put("dsCustInvRulePk", dsCustInvRulePk);
        query.put("invRuleRcpntTp_Internal", INV_RULE_RCPNT_TP.INTERNAL);

        return getSsmEZDClient().queryObjectList("getInvoiceSourceListInternalReview", query);
    }

    /**
     * @param dsCustInvRulePk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvoiceSourceListExternalContact(BigDecimal dsCustInvRulePk) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("glblCmpyCd", getGlobalCompanyCode());
        query.put("dsCustInvRulePk", dsCustInvRulePk);
        query.put("invRuleRcpntTp_External", INV_RULE_RCPNT_TP.EXTERNAL);

        return getSsmEZDClient().queryObjectList("getInvoiceSourceListExternalContact", query);
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param inActiveFlag boolean
     * @param showAllAddress boolean
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHierarchyList(NMAL6700CMsg cMsg, String glblCmpyCd, String dsAcctNum, boolean inActiveFlag, boolean showAllAddress) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("prntMaxLvl", PARENT_MAX_LEVEL);
        ssmParam.put("childMaxLvl", CHILDREN_MAX_LEVEL);
        ssmParam.put("maxRow", cMsg.B.length() + 1);

        ssmParam.put("dsAcctRelnTp_PrntAcct", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        ssmParam.put("rgtnSts_Ready", RGTN_STS.READY_FOR_ORDER_TAKING);

        if (!inActiveFlag) {
            ssmParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
        }
        if (!showAllAddress) {
            ssmParam.put("primOnlyFlg", ZYPConstant.FLG_ON_Y);
        }
        return getSsmEZDClient().queryObjectList("getHierarchyList", ssmParam);
    }

    /**
     * getLocationWithoutLocNum
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocationWithoutLocNum(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getLocationWithoutLocNum", queryParam);
    }

    // 2017/08/08 S21_NA#8598 Add Start
    public S21SsmEZDResult getPrimaryContactForDuplicateCheck(String dsAcctNum, BigDecimal ctacPsnPk, String ctacTpCd, String glblCmpyCd, String chkEffFromDt, String chkEffThruDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("ctacPsnPk", ctacPsnPk);
        ssmParam.put("ctacTpCd", ctacTpCd);
        ssmParam.put("chkEffFromDt", chkEffFromDt);
        ssmParam.put("chkEffThruDt", chkEffThruDt);
        ssmParam.put("maxEffThruDt", MAX_EFF_THRU_DT);

        return getSsmEZDClient().queryObjectList("getPrimaryContactForDuplicateCheck", ssmParam);
    }
    // 2017/08/08 S21_NA#8598 Add End

    // 2017/10/3 QC#21198 Add Start
    // Mod Start 2017/11/16 QC#17322(Sol#174)
    //public S21SsmEZDResult getRelnAcct(String glblCmpyCd, String dsAcctNum, String relnDsAcctNum) {
    public S21SsmEZDResult getRelnAcct(String glblCmpyCd, String dsAcctNum, String relnDsAcctNum, String dsAcctRelnTpCd, String effFromDt, String effThruDt) {
        // Mod End 2017/11/16 QC#17322(Sol#174)
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", relnDsAcctNum);
        ssmParam.put("relnDsAcctNum", dsAcctNum);
        // Mod Start 2017/11/16 QC#17322(Sol#174)
        //ssmParam.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.RELATED_ACCOUNT);
        ssmParam.put("dsAcctRelnTpCd", dsAcctRelnTpCd);
        // Mod End 2017/11/16 QC#17322(Sol#174)
        ssmParam.put("dsAcctRelnRecipFlg", ZYPConstant.FLG_ON_Y);

        // START 2018/09/28 [QC#28165, ADD]
        ssmParam.put("effFromDt", effFromDt);
        ssmParam.put("effThruDt", effThruDt);
        // END 2018/09/28 [QC#28165, ADD]

        return getSsmEZDClient().queryObject("getRelnAcct", ssmParam);
    }
    // 2017/10/3 QC#21198 Add End

    /**
     * getDsCustShpgDefList
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param sMsg NMAL6700SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsCustShpgDefList(String glblCmpyCd, String dsAcctNum, NMAL6700SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("maxEffThruDt", MAX_EFF_THRU_DT);
        ssmParam.put("rowNum", sMsg.M.length() + 1);
        return getSsmEZDClient().queryObjectList("getDsCustShpgDefList", ssmParam);
    }

    public S21SsmEZDResult getFrtCondSvcReln(String glblCmpyCd, String lineBizTpCd, String frtCondCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("lineBizTpCd", lineBizTpCd);
        if (ZYPCommonFunc.hasValue(frtCondCd)) {
            ssmParam.put("frtCondCd", frtCondCd);
        }
        return getSsmEZDClient().queryObjectList("getFrtCondSvcReln", ssmParam);
    }

    // 2019/12/13 QC#54882 Del Start
//    // Add Start 2018/10/09 QC#27598
//    private String getOneYearPast(String yyyyMMdd) {
//
//        Calendar cal = Calendar.getInstance();
//        DateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
//        try {
//            cal.setTime(dateformat.parse(yyyyMMdd));
//        } catch (ParseException e) {
//            return null;
//        }
//        cal.add(Calendar.YEAR, -1);
//        return dateformat.format(cal.getTime());
//    }
//    // Add End 2018/10/09 QC#27598
    // 2019/12/13 QC#54882 Del End

    // Add Start 2019/05/23 QC#50101
    public S21SsmEZDResult getCmpyPk(String glblCmpyCd, String locNum) {
        // Mod End 2017/11/16 QC#17322(Sol#174)
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("locNum", locNum);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getCmpyPk", ssmParam);
    }
    // Add End 2019/05/23 QC#50101

    // Add Start 2019/08/07 QC#52385
    /**
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param defBillToList List<String>
     * @param defShipToList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRelatedBillShipFromChildReln(String glblCmpyCd, String dsAcctNum, //
            List<String> defBillToList, List<String> defShipToList) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", glblCmpyCd);
        param.put("acctNum", dsAcctNum);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        param.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);

        if (defBillToList != null && defBillToList.size() > 0) {
            String[] strDefBillToList = defBillToList.toArray(new String[0]);
            param.put("defBillToList", strDefBillToList);
        }
        if (defShipToList != null && defShipToList.size() > 0) {
            String[] strDefShipToList = defShipToList.toArray(new String[0]);
            param.put("defShipToList", strDefShipToList);
        }

        return getSsmEZDClient().queryObjectList("getCustInfoForRelatedChildReln", param);
    }

    /**
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param defBillToList List<String>
     * @param defShipToList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRelatedBillShipFromCurrent(String glblCmpyCd, String dsAcctNum, //
            List<String> defBillToList, List<String> defShipToList) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", glblCmpyCd);
        param.put("acctNum", dsAcctNum);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        if (defBillToList != null && defBillToList.size() > 0) {
            String[] strDefBillToList = defBillToList.toArray(new String[0]);
            param.put("defBillToList", strDefBillToList);
        }
        if (defShipToList != null && defShipToList.size() > 0) {
            String[] strDefShipToList = defShipToList.toArray(new String[0]);
            param.put("defShipToList", strDefShipToList);
        }

        return getSsmEZDClient().queryObjectList("getCustInfoForRelatedCurrent", param);
    }

    /**
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param defBillToList List<String>
     * @param defShipToList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRelatedBillShipFromParentReln(String glblCmpyCd, String dsAcctNum, //
            List<String> defBillToList, List<String> defShipToList) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", glblCmpyCd);
        param.put("acctNum", dsAcctNum);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        param.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        param.put("dsAcctRelnTpCd_Exclusion", DS_ACCT_RELN_TP.MYCSA_ACCOUNT);

        if (defBillToList != null && defBillToList.size() > 0) {
            String[] strDefBillToList = defBillToList.toArray(new String[0]);
            param.put("defBillToList", strDefBillToList);
        }
        if (defShipToList != null && defShipToList.size() > 0) {
            String[] strDefShipToList = defShipToList.toArray(new String[0]);
            param.put("defShipToList", strDefShipToList);
        }

        return getSsmEZDClient().queryObjectList("getCustInfoForRelatedParentReln", param);
    }
    // Add End 2019/08/07 QC#52385

    /**
     * getExistPrinCust Add QC#59957
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getExistPrinCust(String glblCmpyCd, String locNum, BigDecimal prinCustPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("locNum", locNum);
        ssmParam.put("prinCustPk", prinCustPk);

        return getSsmEZDClient().queryObjectList("getExistsPrinCust", ssmParam);
    }
    // 2023/01/13 QC#60860 Add Start
    public String getVndCd(String glblCmpyCd, String vndNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("vndNm", vndNm);

        return (String) getSsmEZDClient().queryObject("getVndCd", ssmParam).getResultObject();
    }
    // 2023/01/13 QC#60860 Add End

    // add start 2023/07/18 QC#61421
    public S21SsmEZDResult getDsBizArea(String glblCmpyCd, String spclInstnFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(spclInstnFlg)) {
            ssmParam.put("spclInstnFlg", spclInstnFlg);
        }
        return getSsmEZDClient().queryObjectList("getDsBizArea", ssmParam);
    }
    // add end 2023/07/18 QC#61421
}
