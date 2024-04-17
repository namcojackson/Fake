/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1320;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.blap.NSAL1320.constant.NSAL1320Constant;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CRAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TRX_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NSAL1320Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 * 2017/06/14   Hitachi         K.Kitachi       Update          QC#18844
 * 2017/06/19   Hitachi         A.Kohinata      Update          QC#19036
 * 2017/06/20   Hitachi         K.Kojima        Update          QC#19053
 * 2017/06/28   Hitachi         Y.Takeno        Update          QC#19604
 * 2017/07/12   Hitachi         K.Kojima        Update          QC#19793
 * 2017/07/28   Hitachi         Y.Takeno        Update          QC#20088
 * 2017/08/09   Hitachi         T.Kanasaka      Update          QC#18193
 * 2017/09/26   Hitachi         Y.Takeno        Update          QC#21154
 * 2017/10/24   Hitachi         Y.Takeno        Update          QC#21656-1
 * 2017/10/24   Hitachi         Y.Takeno        Update          QC#21556
 * 2018/04/10   CITS            M.Naito         Update          QC#23378
 * 2018/04/19   Fujitsu         A.Kosai         Update          QC#21919
 * 2018/05/17   Fujitsu         A.Kosai         Update          QC#22482
 * 2018/08/17   Hitachi         A.Kohinata      Update          QC#23919
 * 2019/01/09   Hitachi         S.Kitamura      Update          QC#26928
 * 2019/01/18   Hitachi         K.Fujimoto      Update          QC#29782
 * 2019/05/23   Fujitsu         W.Honda         Update          QC#50157
 * 2019/10/10   Hitachi         K.Kitachi       Update          QC#54074
 * 2019/11/29   Hitachi         K.Kitachi       Update          QC#53682
 * 2019/11/29   CITS            T.Wada          Update          QC#55922
 * 2024/03/21   CITS            M.Kuroi         Update          QC#63638
 *</pre>
 */
public final class NSAL1320Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL1320Query MY_INSTANCE = new NSAL1320Query();

    /**
     * Private constructor
     */
    private NSAL1320Query() {
        super();
    }

    /**
     * Get the NSAL1320Query instance.
     * @return NSAL1320Query instance
     */
    public static NSAL1320Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param slsDt         slsDt
     * @param bizMsg        NSAL1320CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getBllgCycleList(String glblCmpyCd, String slsDt, NSAL1320CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("slsDt", slsDt);

        return getSsmEZDClient().queryObjectList("getBllgCycleList", params);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param mdseCd        mdseCd
     * @param mdseNm        mdseNm
     * @param likeSearchFlg String(Y/N)
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult searchShellItem(String glblCmpyCd, String mdseCd, String mdseNm, String likeSearchFlg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        params.put("mdseNm", mdseNm);
        params.put("itemTpCtxTpCd", "CPO_SVC_MDSE_QLFY_ITEM_TP");
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("likeSearchFlg", likeSearchFlg);

        String stmtId = "";
        // START 2017/06/14 K.Kitachi [QC#18844, MOD]
        if (ZYPConstant.FLG_OFF_N.equals(likeSearchFlg) //
                || (ZYPCommonFunc.hasValue(mdseCd) && mdseCd.indexOf("%") < 0 && mdseCd.indexOf("_") < 0)) {
        // END 2017/06/14 K.Kitachi [QC#18844, MOD]
            stmtId = "searchShellItem";
        } else {
            stmtId = "searchShellItemWithLike";
        }
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NSAL1320CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getCustIssPoNum(String glblCmpyCd, NSAL1320CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("acctCd", bizMsg.A.no(0).dsAcctNm);
        params.put("locCd", bizMsg.A.no(0).soldToCustLocCd);
        params.put("dsTrxRuleTpCd", DS_TRX_RULE_TP.SERVICE);
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObject("getCustIssPoNum", params);
    }

    /**
     * <pre>
     * @param bizMsg        NSAL1320CMsg
     * @param dsOrdPosnNum  dsOrdPosnNum
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getSvcConfigMstrPk(NSAL1320CMsg bizMsg, String dsOrdPosnNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("configCatgCd", CONFIG_CATG.OUTBOUND);
        params.put("dsOrdPosnNum", dsOrdPosnNum);

        return getSsmEZDClient().queryObjectList("getSvcConfigMstrPk", params);
    }

    // START 2017/07/12 K.Kojima [QC#19793,DEL]
    // /**
    //  * <pre>
    //  * @param glblCmpyCd    glblCmpyCd
    //  * @param cpoOrdNum     cpoOrdNum
    //  * @param dsOrdPosnNum  dsOrdPosnNum
    //  * @return  S21SsmEZDResult
    //  * </pre>
    //  */
    // public S21SsmEZDResult getAddlBaseItemLineList(String glblCmpyCd, String cpoOrdNum, String dsOrdPosnNum) {
    //     Map<String, Object> params = new HashMap<String, Object>();
    //     params.put("glblCmpyCd", glblCmpyCd);
    //     params.put("cpoOrdNum", cpoOrdNum);
    //     params.put("dsOrdPosnNum", dsOrdPosnNum);
    //     params.put("cpoSvcAddlBaseItems", "CPO_SVC_ADDL_BASE_ITEMS");
    // 
    //     return getSsmEZDClient().queryObjectList("getAddlBaseItemLineList", params);
    // }
    // END 2017/07/12 K.Kojima [QC#19793,DEL]

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param dsContrNum    dsContrNum
     * @param dsAcctNum     dsAcctNum
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult searchDsContrPk(String glblCmpyCd, String dsContrNum, String dsAcctNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrNum", dsContrNum);
        params.put("dsAcctNum", dsAcctNum);

        return getSsmEZDClient().queryObject("searchDsContrPk", params);
    }

    /**
     * get Bill To Customer Info List
     * @param bizMsg NSAL1320CMsg
     * @param custLocNum Customer location Number
     * @return Bill To Customer Info List
     */
    public S21SsmEZDResult getBillToCustInfoList(NSAL1320CMsg bizMsg, String custLocNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("custCd", custLocNum);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getBillToCustInfoList", params);
    }

    /**
     * get Bill To Customer Count
     * @param bizMsg NSAL1320CMsg
     * @param billToCustCd billToCustCd
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillToCustCount(NSAL1320CMsg bizMsg, String billToCustCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("billToCustCd", billToCustCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getBillToCustCount", params);
    }

    /**
     * <pre>
     * getDsAcctCustCount
     * @param bizMsg    NSAL1320CMsg
     * @param dsAcctNum dsAcctNum
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getDsAcctCustCount(NSAL1320CMsg bizMsg, String dsAcctNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsAcctNum", dsAcctNum);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getDsAcctCustCount", params);
    }

    // START 2017/07/12 K.Kojima [QC#19793,DEL]
    // /**
    //  * <pre>
    //  * @param cpoOrdNum cpoOrdNum
    //  * @return  S21SsmEZDResult
    //  * </pre>
    //  */
    // public S21SsmEZDResult getOrderBillToInfo(String cpoOrdNum) {
    //     Map<String, Object> params = new HashMap<String, Object>();
    //     params.put("glblCmpyCd", getGlobalCompanyCode());
    //     params.put("cpoOrdNum", cpoOrdNum);
    // 
    //     return getSsmEZDClient().queryObject("getOrderBillToInfo", params);
    // }
    // END 2017/07/12 K.Kojima [QC#19793,DEL]

    /**
     * <pre>
     * @param bizMsg    NSAL1320CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getBillByTpList(NSAL1320CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());

        return getSsmEZDClient().queryObjectList("getBillByTpList", params);
    }

    public S21SsmEZDResult getMntBillAsEquipInfo(NSAL1320_ACMsg aBizMsg, NSAL1320CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("billByTpCd", aBizMsg.billByTpCd.getValue());
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        String stmtId = "getMntBillAsEquipInfo";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    public S21SsmEZDResult getContractIndicatorList() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getContractIndicatorList", params);
    }

    public S21SsmEZDResult getContrInfo(NSAL1320_ACMsg aBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrPk", aBizMsg.dsContrPk_AD);
        params.put("dsContrNum", aBizMsg.dsContrNum_AD);
        params.put("serNum", aBizMsg.serNum_A);
        params.put("mdseCd", aBizMsg.mdseCd_A);
        
        return getSsmEZDClient().queryObjectList("getContrInfo", params);
    }

    public S21SsmEZDResult getHeaderInfoFromContrNum(NSAL1320_ACMsg aBizMsg, NSAL1320CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum);
        params.put("dsContrNum", aBizMsg.dsContrNum_AD);
        params.put("dsContrStsCd", DS_CONTR_STS.ORDER);
        // START 2017/10/24 [QC#21556, ADD]
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        // END   2017/10/24 [QC#21556, ADD]
        // 2018/05/17 QC#22482 Add Start
        params.put("svcMemoTpCd", SVC_MEMO_TP.OVERRIDE_SHELL_CONTRACT);
        params.put("svcMemoTrxNm", NSAL1320Constant.SVC_MEMO_TRX_NM_CPO_ORD_NUM);
        // 2018/05/17 QC#22482 Add End

        return getSsmEZDClient().queryObjectList("getHeaderInfoFromContrNum", params);
    }

    public S21SsmEZDResult getSerNumFromContract(NSAL1320_ACMsg aBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrPk", aBizMsg.dsContrPk_AD);
        params.put("dsContrDtlSts", DS_CONTR_DTL_STS.ORDER);

        return getSsmEZDClient().queryObjectList("getSerNumFromContract", params);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NSAL1320CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getInitDataFromCpo(String glblCmpyCd, NSAL1320CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("saved", ORD_HDR_STS.SAVED);
        params.put("validated", ORD_HDR_STS.VALIDATED);

        params.put("addToConfig", CONFIG_TP.ADD_TO_CONFIG);
        params.put("cpoSvcAddlBaseItems", MDSE_TP_CTX_TP.CPO_SERVICE_ADDITIONAL_BASE_ITEMS);

        return getSsmEZDClient().queryObjectList("getInitDataFromCpo", params);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NSAL1320CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getInitDataFromShellContr(String glblCmpyCd, NSAL1320CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("refCpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("dsContrStsCd", DS_CONTR_STS.ORDER);
        params.put("dsContrDtlTpCd01", new String[] { DS_CONTR_DTL_TP.BASE_AND_USAGE, DS_CONTR_DTL_TP.BASE_ONLY });
        params.put("addAsryFlg01", ZYPConstant.FLG_OFF_N);
        params.put("dsContrDtlTpCd02", new String[] { DS_CONTR_DTL_TP.ACCESSORIES });
        params.put("addAsryFlg02", ZYPConstant.FLG_ON_Y);
        // START 2017/07/28 [QC#20088, ADD]
        params.put("svcMemoTpCd", SVC_MEMO_TP.OVERRIDE_SHELL_CONTRACT);
        params.put("svcMemoTrxNm", NSAL1320Constant.SVC_MEMO_TRX_NM_CPO_ORD_NUM);
        // END   2017/07/28 [QC#20088, ADD]
        // START 2017/10/24 [QC#21656, ADD]
        params.put("dsContrDtlStsCd", DS_CONTR_DTL_STS.ORDER);
        // END   2017/10/24 [QC#21656, ADD]
        // add start 2018/08/17 QC#23919
        params.put("dsContrDtlTpCdFleet", DS_CONTR_DTL_TP.FLEET);
        // add end 2018/08/17 QC#23919
        // START 2019/01/09 S.Kitamura [QC#26928, ADD]
        params.put("lvlNum", "2");
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        // END 2019/01/09 S.Kitamura [QC#26928, ADD]
        // START 2019/01/18 K.Fujimoto [QC#29782, ADD]
        params.put("dsContrCratTp", DS_CONTR_CRAT_TP.SHELL);
        // END 2019/01/18 K.Fujimoto [QC#29782, ADD]
        String stmtId = "getInitDataFromShellContr";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param dsOrdPosnNum  dsOrdPosnNum
     * @param cpoOrdNum     cpoOrdNum
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult existShellContr(String glblCmpyCd, String dsOrdPosnNum, String cpoOrdNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("dsOrdPosnNum", dsOrdPosnNum);

        return getSsmEZDClient().queryObject("existShellContr", params);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NSAL1320CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getSvcConfigRef(String glblCmpyCd, NSAL1320CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        // START 2019/01/09 S.Kitamura [QC#26928, ADD]
        params.put("lvlNum", "2");
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        // END 2019/01/09 S.Kitamura [QC#26928, ADD]
        // START 2019/01/18 K.Fujimoto [QC#29782, ADD]
        params.put("dsContrCratTp", DS_CONTR_CRAT_TP.SHELL);
        // END 2019/01/18 K.Fujimoto [QC#29782, ADD]
        return getSsmEZDClient().queryObjectList("getSvcConfigRef", params);
    }

    /**
     * getRentalOrderCnt
     * 
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return
     */
    public Integer getRentalOrderCnt(String glblCmpyCd, String cpoOrdNum) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("ordCatgCtxTpCd", "RENTAL_SHELL_REQUIRED");

        return (Integer) getSsmEZDClient().queryObject("getRentalOrderCnt", ssmParam).getResultObject();
    }

    /**
     * getAmount
     * 
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NSAL1320CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getAmount(String glblCmpyCd, NSAL1320CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("dsContrDtlTpCdSv", new String[] { DS_CONTR_DTL_TP.BASE_AND_USAGE, DS_CONTR_DTL_TP.BASE_ONLY });
        // START 2018/04/10 M.Naito [QC#23378, ADD]
        params.put("isFleet" , DS_CONTR_CATG.FLEET.equals(bizMsg.A.no(0).dsContrCatgCd.getValue()) ? ZYPConstant.FLG_ON_Y : null);
        params.put("dsContrDtlTpCdFleet", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        // END 2018/04/10 M.Naito [QC#23378, ADD]
        // START 2017/06/28 [QC#19604, MOD]
        params.put("dsContrDtlTpCdEq1", new String[] { DS_CONTR_DTL_TP.ACCESSORIES });
        params.put("dsContrDtlTpCdEq2", new String[] { DS_CONTR_DTL_TP.BASE_ONLY, DS_CONTR_DTL_TP.BASE_AND_USAGE });
        // END   2017/06/28 [QC#19604, MOD]
        params.put("addlChrgCatgCdEq", ADDL_CHRG_CATG.RENTAL);
        params.put("dsContrDtlTpCdAd", new String[] { DS_CONTR_DTL_TP.BASE_AND_USAGE, DS_CONTR_DTL_TP.BASE_ONLY });
        params.put("addlChrgCatgCdAd", ADDL_CHRG_CATG.NORMAL);
        params.put("baseBllgCycleCd", BLLG_CYCLE.CONTRACT_PERIOD);
        // START 2019/01/18 K.Fujimoto [QC#29782, ADD]
        params.put("dsContrCratTpShell", DS_CONTR_CRAT_TP.SHELL);
        params.put("dsContrCratTpSupply", DS_CONTR_CRAT_TP.SUPPLY);
        params.put("dsContrCratTpOther", DS_CONTR_CRAT_TP.OTHER);
        // END 2019/01/18 K.Fujimoto [QC#29782, ADD]

        return getSsmEZDClient().queryObjectList("getAmount", params);
    }

    // add start 2017/06/19 QC#19036
    /**
     * getDsContrDtlOtherCpoCount
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrDtlOtherCpoCount(String glblCmpyCd, String cpoOrdNum, BigDecimal dsContrPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrPk", dsContrPk);
        params.put("cpoOrdNum", cpoOrdNum);
        String[] dsContrCtrlStsCdList = new String[] {DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.EXPIRED, DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.ORDER };
        params.put("dsContrCtrlStsCdList", dsContrCtrlStsCdList);
 
        return getSsmEZDClient().queryObject("getDsContrDtlOtherCpoCount", params);
    }
    // add end 2017/06/19 QC#19036

    // START 2017/06/20 K.Kojima [QC#19053,ADD]
    /**
     * getAmountForNewLine
     * @param glblCmpyCd glblCmpyCd
     * @param bizMsg NSAL1320CMsg
     * @param bizMsg BigDecimal
     * @return S21SsmEZDResult </pre>
     */
    public S21SsmEZDResult getAmountForNewLine(String glblCmpyCd, NSAL1320CMsg bizMsg, NSAL1320_ACMsg aBizMsg, NSAL1320_BCMsg bBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("dsContrPk", aBizMsg.dsContrPk_A.getValue());
        params.put("shellLineNum", aBizMsg.shellLineNum.getValue());
        params.put("mdlId", bBizMsg.mdlId.getValue());
        params.put("dsContrDtlTpShell", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        params.put("baseBllgCycleCd", BLLG_CYCLE.CONTRACT_PERIOD);

        return getSsmEZDClient().queryObject("getAmountForNewLine", params);
    }

    /**
     * @param bizMsg NSAL1320CMsg
     * @param abizMsg NSAL1320_ACMsg
     * @param bbizMsg NSAL1320_BCMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInitDataFromModelPricingHeader(String glblCmpyCd, NSAL1320CMsg bizMsg, NSAL1320_ACMsg aBizMsg, NSAL1320_BCMsg bBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", aBizMsg.dsContrPk_A.getValue());
        params.put("shellLineNum", bBizMsg.shellLineNum_B.getValue());
        params.put("mdlId", bBizMsg.mdlId.getValue());
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("dsContrStsCd", DS_CONTR_STS.ORDER);
        params.put("ordLineStsPartiallyShipped", ORD_LINE_STS.PARTIALLY_SHIPPED);
        params.put("shpgStsArrived", SHPG_STS.ARRIVED);
        params.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        params.put("dsContrDtlTpCdMac", new String[] {DS_CONTR_DTL_TP.BASE_ONLY, DS_CONTR_DTL_TP.USAGE_ONLY, DS_CONTR_DTL_TP.BASE_AND_USAGE });
        params.put("xsMtrFirstFlgN", ZYPConstant.FLG_OFF_N);
        params.put("isFleet" , DS_CONTR_CATG.FLEET.equals(aBizMsg.dsContrCatgCd.getValue()) ? ZYPConstant.FLG_ON_Y : null);
        // START 2019/10/09 K.Kitachi [QC#54074, ADD]
        params.put("ordLineStsCancelled", ORD_LINE_STS.CANCELLED);
        params.put("shpgStsCancelled", SHPG_STS.CANCELLED);
        // END 2019/10/09 K.Kitachi [QC#54074, ADD]

        String stmtId = "getInitDataFromModelPricingHeader";
        return getSsmEZDClient().queryObject(stmtId, params);
    }

    /**
     * @param bizMsg NSAL1320CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInitDataFromModelPricingDetail(String glblCmpyCd, NSAL1320CMsg bizMsg, NSAL1320_ACMsg aBizMsg, NSAL1320_BCMsg bBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", aBizMsg.dsContrPk_A.getValue());
        params.put("shellLineNum", bBizMsg.shellLineNum_B.getValue());
        params.put("mdlId", bBizMsg.mdlId.getValue());
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        params.put("dsContrStsCd", DS_CONTR_STS.ORDER);
        params.put("xsMtrFirstFlg", ZYPConstant.FLG_ON_Y);
        params.put("xsMtrFirstFlgN", ZYPConstant.FLG_OFF_N);
        // START 2017/08/09 T.Kanasaka [QC#18193,DEL]
//        params.put("isRegular" , DS_CONTR_CATG.REGULAR.equals(aBizMsg.dsContrCatgCd.getValue()) ? ZYPConstant.FLG_ON_Y : null);
        // END 2017/08/09 T.Kanasaka [QC#18193,DEL]
        params.put("isFleet" , DS_CONTR_CATG.FLEET.equals(aBizMsg.dsContrCatgCd.getValue()) ? ZYPConstant.FLG_ON_Y : null);
        // START 2017/08/09 T.Kanasaka [QC#18193,DEL]
//        params.put("isAggregate", DS_CONTR_CATG.AGGREGATE.equals(aBizMsg.dsContrCatgCd.getValue()) ? ZYPConstant.FLG_ON_Y : null);
        // END 2017/08/09 T.Kanasaka [QC#18193,DEL]
        params.put("flgNmP", NSAL1320Constant.XX_FLG_PARENT);
        params.put("flgNmH", NSAL1320Constant.XX_FLG_HARD);
        params.put("flgNmT", NSAL1320Constant.XX_FLG_TIER);

        String stmtId = "getInitDataFromModelPricingDetail";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * @param prcCatgCd String
     * @param mdlId BigDecimal
     * @param prcMtrPkgPk BigDecimal
     * @param prcSvcPlnTpCd String
     * @param prcSvcContrTpCd String
     * @param termMthNum BigDecimal
     * @return
     */
    public S21SsmEZDResult getPrcRateTpCd(String prcCatgCd, BigDecimal mdlId, BigDecimal prcMtrPkgPk, String prcSvcPlnTpCd, String prcSvcContrTpCd, BigDecimal termMthNum) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        mapParam.put("prcCatgCd", prcCatgCd);
        mapParam.put("activFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        mapParam.put("prcBaseDt", ZYPDateUtil.getSalesDate());
        mapParam.put("mdlId", mdlId);
        mapParam.put("prcMtrPkgPk", prcMtrPkgPk);
        mapParam.put("prcSvcPlnTpCd", prcSvcPlnTpCd);
        mapParam.put("prcSvcContrTpCd", prcSvcContrTpCd);
        mapParam.put("termMthNum", termMthNum);
        mapParam.put("rownum1", 1);

        return getSsmEZDClient().queryObject("getPrcRateTpCd", mapParam);
    }
    // END 2017/06/20 K.Kojima [QC#19053,ADD]

    // START 2017/09/26 [QC#21154, ADD]
    /**
     * @param aBizMsg NSAL1320_ACMsg
     * @param bizMsg NSAL1320CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSoldToCustInfo(NSAL1320_ACMsg aBizMsg, NSAL1320CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());

        String stmtId = "getSoldToCustInfo";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }
    // END   2017/09/26 [QC#21154, ADD]

    // START 2017/10/25 [QC#21556, ADD]
    public S21SsmEZDResult getContrTermInfo(NSAL1320CMsg bizMsg, NSAL1320_ACMsg aBizMsg, NSAL1320_BCMsg bBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrPk", aBizMsg.dsContrPk_AD.getValue());
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("cpoDtlLineNum", bBizMsg.cpoDtlLineNum.getValue());
        params.put("cpoDtlLineSubNum", bBizMsg.cpoDtlLineSubNum.getValue());
        params.put("dsOrdPosnNum", bBizMsg.dsOrdPosnNum.getValue());
        params.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.ACCESSORIES);
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getContrTermInfo", params);
    }
    // END   2017/10/25 [QC#21556, ADD]

    // START 2018/04/19 A.Kosai [QC#21919, ADD]
    public S21SsmEZDResult getIstlInfo(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("cpoDtlLineNum", cpoDtlLineNum);
        params.put("cpoDtlLineSubNum", cpoDtlLineSubNum);

        return getSsmEZDClient().queryObjectList("getIstlInfo", params);
    }

    public String convLineBizTpToSvcLineBiz(String glblCmpyCd, String lineBizTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("lineBizTpCd", lineBizTpCd);
        params.put("nullString", "NULL");

        return (String) getSsmEZDClient().queryObject("convLineBizTpToSvcLineBiz", params).getResultObject();
    }
    // END   2018/04/19 A.Kosai [QC#21919, ADD]

    // START 2019/05/23 W.Honda [QC#50157, ADD]
    public BigDecimal getExistCrCardPO(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrDtlPk", dsContrDtlPk);

        return (BigDecimal) getSsmEZDClient().queryObject("getExistCrCardPO", params).getResultObject();
    }
    // END   2019/05/23 W.Honda [QC#50157, ADD]

    // START 2019/11/29 K.Kitachi [QC#53682, ADD]
    /**
     * existAddToContrAccReln
     * @param bizMsg NSAL1320CMsg
     * @param aBizMsg NSAL1320_ACMsg
     * @param bBizMsg NSAL1320_BCMsg
     * @return boolean
     */
    public boolean existAddToContrAccReln(NSAL1320CMsg bizMsg, NSAL1320_ACMsg aBizMsg, NSAL1320_BCMsg bBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("cpoDtlLineNum", bBizMsg.cpoDtlLineNum.getValue());
        params.put("cpoDtlLineSubNum", bBizMsg.cpoDtlLineSubNum.getValue());
        params.put("dsContrPk", aBizMsg.dsContrPk_AD.getValue());
        params.put("svcMachTpCd", SVC_MACH_TP.MACHINE);

        BigDecimal count = (BigDecimal) getSsmEZDClient().queryObject("countAddToContrAccReln", params).getResultObject();
        if (BigDecimal.ZERO.compareTo(count) < 0) {
            return true;
        }
        return false;
    }
    // END 2019/11/29 K.Kitachi [QC#53682, ADD]

    // 2020/05/27 QC#55922 Add Start
    /**
     * countDsContrBllgMtr
     */
    public int countDsContrBllgMtr(BigDecimal dsContrPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrPk", dsContrPk);

        BigDecimal count = (BigDecimal) getSsmEZDClient().queryObject("countDsContrBllgMtr", params).getResultObject();
        return count.intValue();
    }
    // 2020/05/27 QC#55922 Add End

    // START 2024/03/21 M.Kuroi [QC#63638, ADD]
    /**
     * getLatestShippingStatus
     * @param bizMsg NSAL1320CMsg
     * @param bBizMsg NSAL1320_BCMsg
     * @return String
     */
    public String getLatestShpgSts(String refCpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("refCpoOrdNum", refCpoOrdNum);
        params.put("cpoDtlLineNum", cpoDtlLineNum);
        params.put("cpoDtlLineSubNum", cpoDtlLineSubNum);

        return (String) getSsmEZDClient().queryObject("getLatestShpgSts", params).getResultObject();
    }
    // END 2024/03/21 M.Kuroi [QC#63638, ADD]
}
