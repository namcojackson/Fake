/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1330;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItem;
import business.blap.NSAL1330.constant.NSAL1330Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CRAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RATE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PRC_CATG;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NSAL1330Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/08   Hitachi         Y.Takeno        Create          N/A
 * 2017/06/14   Hitachi         Y.Takeno        Update          QC#19039
 * 2017/06/14   Hitachi         Y.Takeno        Update          QC#18934
 * 2017/06/20   Hitachi         T.Tomita        Update          QC#19325
 * 2017/06/21   Hitachi         A.Kohinata      Update          QC#19330
 * 2017/07/24   Hitachi         K.Kojima        Update          QC#20090
 * 2017/07/28   Hitachi         Y.Takeno        Update          QC#20088
 * 2017/08/09   Hitachi         T.Kanasaka      Update          QC#18193
 * 2017/10/25   Hitachi         K.Kojima        Update          QC#21690
 * 2017/10/26   Hitachi         Y.Takeno        Update          QC#21556
 * 2018/07/10   Hitachi         K.Kojima        Update          QC#27135
 * 2018/07/20   Fujitsu         R.Nakamura      Update          QC#27268
 * 2018/07/27   Fujitsu         R.Nakamura      Update          QC#27492
 * 2018/10/10   Hitachi         K.Kojima        Update          QC#28715
 * 2018/12/19   Fujitsu         M.Yamada        Update          QC#29248
 * 2019/01/09   Hitachi         S.Kitamura      Update          QC#26928
 * 2019/01/18   Hitachi         K.Fujimoto      Update          QC#29782
 * 2019/02/26   Hitachi         K.Fujimoto      Update          QC#30525
 * 2019/05/08   Fujitsu         K.Kato          Update          QC#50174
 * 2019/07/18   Fujitsu         K.Kato          Update          S21_NA#51327
 * 2019/10/10   Hitachi         K.Kitachi       Update          QC#54074
 *</pre>
 */
public final class NSAL1330Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL1330Query MY_INSTANCE = new NSAL1330Query();

    /**
     * Private constructor
     */
    private NSAL1330Query() {
        super();
    }

    /**
     * Get the NSAL1330Query instance.
     * @return NSAL1330Query instance
     */
    public static NSAL1330Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get modelCount
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeaderUpdate(NSAL1330CMsg bizMsg, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", bizMsg.dsContrPk.getValue());
        // START 2017/07/28 [QC#20088, ADD]
        params.put("svcMemoTpCd", SVC_MEMO_TP.OVERRIDE_SHELL_CONTRACT);
        params.put("svcMemoTrxNm", NSAL1330Constant.SVC_MEMO_TRX_NM_CPO_ORD_NUM);
        // END   2017/07/28 [QC#20088, ADD]

        return getSsmEZDClient().queryObject("getHeaderUpdate", params);
    }

    /**
     * getInitAcsryForRental
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd String
     * @param dplyLineNum String[]
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInitAcsryForRental(NSAL1330CMsg bizMsg, String glblCmpyCd, String checkFlg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);

        params.put("svcPrcCatgRentalEq", SVC_PRC_CATG.RENTAL_EQUIPMENT_BASE_CHARGE);
        params.put("svcPrcCatgAcsryChrg", SVC_PRC_CATG.ACC_UNIT_BASE_CHARGE);
        params.put("mdseTpCtxCpoSvcAddlBaseItems", MDSE_TP_CTX_TP.CPO_SERVICE_ADDITIONAL_BASE_ITEMS);

        params.put("cpoOrdNum", bizMsg.xxScrItem50Txt.getValue());
        params.put("mdseTpCtxTp" //
                , new String[] {MDSE_TP_CTX_TP.CPO_SERVICE_ADDITIONAL_BASE_ITEMS, MDSE_TP_CTX_TP.CPO_SERVICE_CONFIGURATION_ITEMS });
        params.put("ordCatgCtxTp", "RENTAL_ORDER");
        params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        params.put("shellLineNum", bizMsg.shellLineNum.getValue());
        params.put("checkFlg", checkFlg);
        // START 2017/07/24 K.Kojima [QC#20090,DEL]
        // params.put("closeFlg", "");
        // END 2017/07/24 K.Kojima [QC#20090,DEL]
        // START 2017/07/24 K.Kojima [QC#20090,ADD]
        params.put("prcQlfyTpItemCode", PRC_QLFY_TP.ITEM_CODE);
        // END 2017/07/24 K.Kojima [QC#20090,ADD]
        // 2019/05/08 QC#50174 Add Start
        params.put("ordLineStsCancel", ORD_LINE_STS.CANCELLED);
        // 2019/05/08 QC#50174 Add End

        return getSsmEZDClient().queryObjectList("getInitAcsryForRental", params);
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @param index int
     * @param glblCmpyCd String
     * @param target target area J/B
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUpdateAcsry(NSAL1330CMsg bizMsg, int index, String glblCmpyCd, String target) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("orderNum", bizMsg.xxScrItem50Txt.getValue());
        if ("B".equals(target)) {
            NSAL1330_BCMsg bBizMsg = bizMsg.B.no(index);
            params.put("lineNum", bBizMsg.cpoDtlLineNum_B.getValue());
            params.put("lineSubNum", bBizMsg.cpoDtlLineSubNum_B.getValue());
        } else {
            NSAL1330_JCMsg jBizMsg = bizMsg.J.no(index);
            params.put("lineNum", jBizMsg.cpoDtlLineNum_J.getValue());
            params.put("lineSubNum", jBizMsg.cpoDtlLineSubNum_J.getValue());
        }
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        params.put("prcQlfyTpItemCode", PRC_QLFY_TP.ITEM_CODE);
        params.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.ACCESSORIES);
        // START 2017/07/24 K.Kojima [QC#20090,ADD]
        params.put("prcQlfyTpItemCode", PRC_QLFY_TP.ITEM_CODE);
        // END 2017/07/24 K.Kojima [QC#20090,ADD]
        // START 2019/01/18 K.Fujimoto [QC#29782,ADD]
        params.put("dsContrCratTpShell", DS_CONTR_CRAT_TP.SHELL);
        params.put("dsContrCratTpSupply", DS_CONTR_CRAT_TP.SUPPLY);
        // END   2019/01/18 K.Fujimoto [QC#29782,ADD]

        String stmtId = "getUpdateAcsry";
        return getSsmEZDClient().queryObject(stmtId, params);
    }

    /**
     * @param prcCatgCd String
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListTp(String prcCatgCd, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", prcCatgCd);

        return getSsmEZDClient().queryObject("getPrcListTp", params);
    }

    /**
     * Get Update Additional Charge
     * @param bizMsg NSAL1330CMsg
     * @param index int
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUpdateAddChrg(NSAL1330CMsg bizMsg, int index, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("orderNum", bizMsg.xxScrItem50Txt.getValue());
        params.put("lineNum", bizMsg.C.no(index).cpoDtlLineNum_C.getValue());
        params.put("lineSubNum", bizMsg.C.no(index).cpoDtlLineSubNum_C.getValue());
        // START 2019/01/18 K.Fujimoto [QC#29782,ADD]
        params.put("dsContrCratTpShell", DS_CONTR_CRAT_TP.SHELL);
        // END   2019/01/18 K.Fujimoto [QC#29782,ADD]
        String stmtId = "getUpdateAddChrg";
        return getSsmEZDClient().queryObject(stmtId, params);
    }

    /**
     * Get SvcPkg21
     * @param bizMsg NSAL1330CMsg
     * @param index int
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkg21(NSAL1330CMsg bizMsg, int index, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", bizMsg.A.no(index).prcCatgCd_A.getValue());
        params.put("modelId", bizMsg.A.no(index).mdlId_A.getValue());
        params.put("modelNm", bizMsg.A.no(index).t_MdlNm_A.getValue());
        params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        return getSsmEZDClient().queryObjectList("getSvcPkg21", params);
    }

    /**
     * getSvcPkg21
     * @param bizMsg NSAL1330CMsg
     * @param rBizMsg NSAL1330_RCMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkg21(NSAL1330CMsg bizMsg, NSAL1330_RCMsg rBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());
        params.put("modelId", rBizMsg.mdlId_R.getValue());
        params.put("modelNm", rBizMsg.t_MdlNm_R.getValue());
        params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        return getSsmEZDClient().queryObjectList("getSvcPkg21", params);
    }

    /**
     * Get SvcPkg21 Fleet
     * @param bizMsg NSAL1330CMsg
     * @param mdlList List<BigDecimal>
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkg21FL(NSAL1330CMsg bizMsg, List<BigDecimal> mdlList, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", bizMsg.A.no(0).prcCatgCd_A.getValue());
        params.put("modelId", mdlList);
        params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        return getSsmEZDClient().queryObjectList("getSvcPkg21FL", params);
    }

    /**
     * Get SvcPkg07
     * @param bizMsg NSAL1330CMsg
     * @param index int
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkg07(NSAL1330CMsg bizMsg, int index, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", bizMsg.A.no(index).prcCatgCd_A.getValue());
        params.put("modelId", bizMsg.A.no(index).mdlId_A.getValue());
        params.put("modelNm", bizMsg.A.no(index).t_MdlNm_A.getValue());
        params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        return getSsmEZDClient().queryObjectList("getSvcPkg07", params);
    }

    /**
     * getSvcPkg07
     * @param bizMsg NSAL1330CMsg
     * @param rBizMsg NSAL1330_RCMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkg07(NSAL1330CMsg bizMsg, NSAL1330_RCMsg rBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());
        params.put("modelId", rBizMsg.mdlId_R.getValue());
        params.put("modelNm", rBizMsg.t_MdlNm_R.getValue());
        params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        return getSsmEZDClient().queryObjectList("getSvcPkg07", params);
    }

    /**
     * Get SvcPkg07 Fleet
     * @param bizMsg NSAL1330CMsg
     * @param mdlList List<BigDecimal>
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkg07FL(NSAL1330CMsg bizMsg, List<BigDecimal> mdlList, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", bizMsg.A.no(0).prcCatgCd_A.getValue());
        params.put("modelId", mdlList);
        params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        return getSsmEZDClient().queryObjectList("getSvcPkg07FL", params);
    }

    /**
     * Get SvcPkg
     * @param bizMsg NSAL1330CMsg
     * @param index int
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkg(NSAL1330CMsg bizMsg, int index, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", bizMsg.A.no(index).prcCatgCd_A.getValue());
        params.put("modelId", bizMsg.A.no(index).mdlId_A.getValue());
        params.put("modelNm", bizMsg.A.no(index).t_MdlNm_A.getValue());
        params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        return getSsmEZDClient().queryObjectList("getSvcPkg", params);
    }

    /**
     * getSvcPkg
     * @param bizMsg NSAL1330CMsg
     * @param rBizMsg NSAL1330_RCMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkg(NSAL1330CMsg bizMsg, NSAL1330_RCMsg rBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());
        params.put("modelId", rBizMsg.mdlId_R.getValue());
        params.put("modelNm", rBizMsg.t_MdlNm_R.getValue());
        params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        return getSsmEZDClient().queryObjectList("getSvcPkg", params);
    }

    /**
     * Get SvcPkg FLeet
     * @param bizMsg NSAL1330CMsg
     * @param mdlList List<BigDecimal>
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPkgFL(NSAL1330CMsg bizMsg, List<BigDecimal> mdlList, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", bizMsg.A.no(0).prcCatgCd_A.getValue());
        params.put("modelId", mdlList);
        params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        return getSsmEZDClient().queryObjectList("getSvcPkgFL", params);
    }

    /**
     * Get PrcListStru
     * @param bizMsg NSAL1330CMsg
     * @param index int
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListStruForAupplyAgreement(NSAL1330CMsg bizMsg, int index, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCatgCd", bizMsg.R.no(index).prcCatgCd_R.getValue());

        return getSsmEZDClient().queryObjectList("getPrcListStru", params);
    }

    /**
     * Get ApiData
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getApiDataB(NSAL1330CMsg bizMsg) {
        int index = bizMsg.xxNum_A.getValueInt();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("orderNum", bizMsg.xxScrItem50Txt.getValue());
        params.put("lineNum", bizMsg.B.no(index).cpoDtlLineNum_B.getValue());
        params.put("lineSubNum", bizMsg.B.no(index).cpoDtlLineSubNum_B.getValue());

        return getSsmEZDClient().queryObject("getApiData", params);
    }

    /**
     * Get ApiData
     * @param bizMsg NSAL1330CMsg
     * @param index int
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getApiDataBList(NSAL1330CMsg bizMsg, int index, String glblCmpyCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("orderNum", bizMsg.xxScrItem50Txt.getValue());
        params.put("lineNum", bizMsg.B.no(index).cpoDtlLineNum_B.getValue());
        params.put("lineSubNum", bizMsg.B.no(index).cpoDtlLineSubNum_B.getValue());

        return getSsmEZDClient().queryObject("getApiData", params);
    }

    /**
     * Get ApiData
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getApiDataC(NSAL1330CMsg bizMsg, String glblCmpyCd) {
        int index = bizMsg.xxNum_A.getValueInt();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("orderNum", bizMsg.xxScrItem50Txt.getValue());
        params.put("lineNum", bizMsg.C.no(index).cpoDtlLineNum_C.getValue());
        params.put("lineSubNum", bizMsg.C.no(index).cpoDtlLineSubNum_C.getValue());

        return getSsmEZDClient().queryObject("getApiData", params);
    }

    /**
     * Get LineBizTpCd
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineBizTpCd(NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("orderNum", bizMsg.xxScrItem50Txt.getValue());

        return getSsmEZDClient().queryObjectList("getLineBizTpCd", params);
    }

    /**
     * Get ItemCvrItem
     * @param bizMsg NSAL1330CMsg
     * @param mdseCd mdseCd
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemCvrItem(NSAL1330CMsg bizMsg, String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("itemCd", mdseCd);
        params.put("orderNum", bizMsg.xxScrItem50Txt.getValue());

        return getSsmEZDClient().queryObjectList("getItemCvrItem", params);
    }

    /**
     * Get ItemCvrUnit
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemCvrUnit(NSAL1330CMsg bizMsg, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("itemCd", bizMsg.C.no(bizMsg.xxNum_A.getValueInt()).mdseCd_CU.getValue());
        params.put("orderNum", bizMsg.xxScrItem50Txt.getValue());

        return getSsmEZDClient().queryObjectList("getItemCvrItem", params);
    }

    /**
     * Get ItemAddItem
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemAddItem(NSAL1330CMsg bizMsg, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("itemCd", bizMsg.C.no(bizMsg.xxNum_A.getValueInt()).mdseCd_CI.getValue());

        return getSsmEZDClient().queryObjectList("getItemAddItem", params);
    }

    /**
     * Get TieredPrcListBox
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTieredPrcListBox(NSAL1330CMsg bizMsg, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);

        return getSsmEZDClient().queryObjectList("getTieredPrcListBox", params);
    }

    /**
     * Get TieredPrcListBox
     * @param aBizMsg NSAL1330_ACMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTierdByCatg(NSAL1330_ACMsg aBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", aBizMsg.prcCatgCd_A.getValue());

        return getSsmEZDClient().queryObjectList("getTierdByCatg", params);
    }

    /**
     * Get HeaderInfo
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrRsnNm(NSAL1330CMsg bizMsg, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rsnCd", bizMsg.svcMemoRsnCd.getValue());

        return getSsmEZDClient().queryObjectList("getContrRsnNm", params);
    }

    /**
     * Get HeaderInfo
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrNum(NSAL1330CMsg bizMsg, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", bizMsg.dsContrPk_AD.getValue());

        return getSsmEZDClient().queryObjectList("getDsContrNum", params);
    }

    /**
     * Get SvcPricingInfo
     * @param bizMsg NSAL1330CMsg
     * @param index int
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPricingPkList(NSAL1330CMsg bizMsg, int index, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("pkgPk", bizMsg.A.no(index).prcMtrPkgPk_A.getValue());

        return getSsmEZDClient().queryObjectList("getSvcPricingPkList", params);
    }

    /**
     * Get SvcPricingInfo
     * @param prcMtrPkgPk pk
     * @param bizMsg NSAL1330CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPricingPkList(BigDecimal prcMtrPkgPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("pkgPk", prcMtrPkgPk);

        return getSsmEZDClient().queryObjectList("getSvcPricingPkList", params);
    }

    /**
     * Get BLLG_CYCLE_MTH_AOT
     * @param bizMsg NSAL1330CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getbllgCycle(NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("bllgCycleCd", bizMsg.baseBllgCycleCd.getValue());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("effFromDt", ZYPDateUtil.getSalesDate());
        params.put("effFromDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        return getSsmEZDClient().queryObject("getbllgCycle", params);
    }

    // QC#28398
//    /**
//     * Get SvcPricingInfo
//     * @param mdseCd String
//     * @param glblCmpyCd String
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getMdseNm(String mdseCd, String glblCmpyCd) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", glblCmpyCd);
//        params.put("mdseCd", mdseCd);
//
//        return getSsmEZDClient().queryObject("getMdseNm", params);
//    }
//
//    /**
//     * Get SvcPricingInfo
//     * @param prcCatgCd String
//     * @param glblCmpyCd String
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getPrcCatgNm(String prcCatgCd, String glblCmpyCd) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", glblCmpyCd);
//        params.put("prcCatgCd", prcCatgCd);
//
//        return getSsmEZDClient().queryObject("getPrcCatgNm", params);
//    }

    /**
     * Get SvcPricingInfo
     * @param bizMsg NSAL1330CMsg
     * @param index int
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdlNm(NSAL1330CMsg bizMsg, int index, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdlId", bizMsg.A.no(index).mdlId_A.getValue());

        return getSsmEZDClient().queryObjectList("getMdlNm", params);
    }

    // QC#28398
//    /**
//     * Get mtrLb
//     * @param mtrLbCd String
//     * @param glblCmpyCd String
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getMtrNm(String mtrLbCd, String glblCmpyCd) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", glblCmpyCd);
//        params.put("mtrLbCd", mtrLbCd);
//
//        return getSsmEZDClient().queryObjectList("getMtrNm", params);
//    }

    /**
     * Get SvcPricingInfo
     * @param bizMsg NSAL1330CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTierInfo_BandSearch(NSAL1330CMsg bizMsg) {
        NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(bizMsg.xxNum_Z.getValueInt());
        NSAL1330_ACMsg aBizMsg = bizMsg.A.no(bizMsg.xxNum_A.getValueInt());

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", zBizMsg.prcCatgCd_Z.getValue());
        params.put("modelId", zBizMsg.mdlId_Z.getValue().toString());
        params.put("modelNm", zBizMsg.t_MdlNm_Z.getValue());
        params.put("prcMtrPkgPk", aBizMsg.prcMtrPkgPk_A.getValue().toString());
        params.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        params.put("bllgMtrBLbCd", zBizMsg.bllgMtrLbCd_Z.getValue());
        params.put("prcListBandDescTxt", zBizMsg.prcListBandDescTxt_Z.getValue());
        params.put("delFlg", ZYPConstant.FLG_OFF_N);
        params.put("prcBaseDt", bizMsg.prcBaseDt.getValue());

        return getSsmEZDClient().queryObjectList("getTierInfo_BandSearch", params);
    }

    /**
     * Get SvcPricingInfo
     * @param bizMsg NSAL1330CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTierInfo_BandSearchConfig(NSAL1330CMsg bizMsg) {
        NSAL1330_UCMsg uBizMsg = bizMsg.U.no(bizMsg.xxNum_Z.getValueInt());
        NSAL1330_RCMsg rBizMsg = bizMsg.R.no(bizMsg.xxNum_A.getValueInt());

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", uBizMsg.prcCatgCd_U.getValue());
        params.put("modelId", uBizMsg.mdlId_U.getValue().toString());
        params.put("modelNm", uBizMsg.t_MdlNm_U.getValue());
        params.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R.getValue().toString());
        params.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
        params.put("bllgMtrBLbCd", uBizMsg.bllgMtrLbCd_U.getValue());
        params.put("prcListBandDescTxt", uBizMsg.prcListBandDescTxt_U.getValue());
        params.put("delFlg", ZYPConstant.FLG_OFF_N);
        params.put("prcBaseDt", bizMsg.prcBaseDt.getValue());

        return getSsmEZDClient().queryObjectList("getTierInfo_BandSearch", params);
    }

    /**
     * Get SvcPricingInfo
     * @param bizMsg NSAL1330CMsg
     * @param index int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcConfName(NSAL1330CMsg bizMsg, int index) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("coveredItem", bizMsg.B.no(index).mdseCd_B.getValue());
        params.put("prcList", bizMsg.B.no(index).addlBasePrcCatgCd_B.getValue());
        params.put("itemCd", PRC_QLFY_TP.ITEM_CODE);
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        return getSsmEZDClient().queryObjectList("getPrcConfName", params);
    }

    /**
     * <pre>
     * @param bizMsg        NSAL1330CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getMtrLb(NSAL1330CMsg bizMsg) {
        int index = bizMsg.xxNum_A.getValueInt();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcMtrPkgPk", bizMsg.A.no(index).prcMtrPkgPk_A.getValue().toString());
        params.put("mdlId", bizMsg.A.no(index).mdlId_A.getValue().toString());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        return getSsmEZDClient().queryObjectList("getMtrLb", params);
    }

    // START 2018/07/10 K.Kojima [QC#27135,MOD]
    // public S21SsmEZDResult getMtrLbConfig(NSAL1330_RCMsg rBizMsg) {
    public S21SsmEZDResult getMtrLbConfig(NSAL1330_RCMsg rBizMsg, NSAL1330CMsg bizMsg) {
    // END 2018/07/10 K.Kojima [QC#27135,MOD]
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R.getValue().toString());
        params.put("mdlId", rBizMsg.mdlId_R.getValue().toString());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        return getSsmEZDClient().queryObjectList("getMtrLb", params);
    }

    /**
     * <pre>
     * @param bizMsg       NSAL1330SMsg
     * @param mdlIdList     List<BigDecimal>
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult isInMtrDB(NSAL1330CMsg bizMsg, List<BigDecimal> mdlIdList) {
        int index = bizMsg.xxNum_A.getValueInt();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcMtrPkgPk", bizMsg.A.no(index).prcMtrPkgPk_A.getValue().toString());
        params.put("mdlId", mdlIdList);
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        return getSsmEZDClient().queryObjectList("isInMtrDB", params);
    }

    /**
     * <pre>
     * @param bizMsg        NSAL1330CMsg
     * @param mdlIdList     List<BigDecimal>
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getMtrLbFL(NSAL1330CMsg bizMsg, List<BigDecimal> mdlIdList) {
        int index = bizMsg.xxNum_A.getValueInt();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcMtrPkgPk", bizMsg.A.no(index).prcMtrPkgPk_A.getValue().toString());
        params.put("mdlId", mdlIdList);
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        return getSsmEZDClient().queryObjectList("getMtrLbFL", params);
    }

    /**
     * <pre>
     * @param bizMsg        NSAL1330CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult cntRentalOrder(NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("ordCatgCtxTpCd", "RENTAL_ORDER");
        params.put("xxScrItem50Txt", bizMsg.xxScrItem50Txt.getValue());

        String stmtId = "cntRentalOrder";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * <pre>
     * @param prcBookMdseCd prcBookMdseCd
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getBookItemNm(String prcBookMdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", prcBookMdseCd);
        params.put("itemTpCtxTpCd", "PRC_BOOK_MDSE_QLFY_ITEM_TP");
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getBookItemNm", params);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NSAL1330CMsg
     * @param aBizMsg       NSAL1330_ACMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getAnnualPrc(String glblCmpyCd, NSAL1330CMsg bizMsg, NSAL1330_ACMsg aBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("annual", PRC_RATE_TP.ANNUAL);
        params.put("prcCatgCd", aBizMsg.prcCatgCd_A.getValue());
        params.put("mdlId", aBizMsg.mdlId_A.getValue().toString());
        params.put("prcMtrPkgPk", aBizMsg.prcMtrPkgPk_A.getValue());
        params.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
        params.put("termMthNum", aBizMsg.termMthAot_A.getValue().toString());

        return getSsmEZDClient().queryObjectList("getAnnualPrc", params);
    }

    /**
     * <pre>
     * @param bizMsg        NSAL1330CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public S21SsmEZDResult getBllgCycleList(NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        return getSsmEZDClient().queryObjectList("getBllgCycleList", params);
    }

    // START 2017/10/25 K.Kojima [QC#21690,DEL]
    // /**
    //  * @param bizMsg NSAL1330CMsg
    //  * @return S21SsmEZDResult
    //  */
    // public S21SsmEZDResult getDefaultMdlSvcPrc(NSAL1330CMsg bizMsg) {
    //     Map<String, Object> params = new HashMap<String, Object>();
    //     params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
    //     params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
    //     params.put("slsDt", ZYPDateUtil.getSalesDate());
    // 
    //     return getSsmEZDClient().queryObjectList("getDefaultMdlSvcPrc", params);
    // }
    // END 2017/10/25 K.Kojima [QC#21690,DEL]

    /**
     * @param mdseCdItem EZDCStringItem
     * @param mdseTpCtxTp String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseInfoForOnBlurAddlChrg(EZDCStringItem mdseCdItem, String mdseTpCtxTp, boolean isLike) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", mdseCdItem.getValue());
        params.put("itemTpCtxTpCd", "CPO_SVC_ADDL_CHRG_QLFY_ITEM_TP");
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        String stmtId = "";
        if (isLike) {
            stmtId = "getMdseInfoForOnBlurAddlChrgWithLike";
        } else {
            stmtId = "getMdseInfoForOnBlurAddlChrg";
        }
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * @param mdseCdItem EZDCStringItem
     * @param cpoOrdNum String
     * @param mdseTpCtxTp String
     * @param shellLineNum BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseInfoForOnBlurCoveredUnit(EZDCStringItem mdseCdItem, String cpoOrdNum, BigDecimal shellLineNum, String mdseTpCtxTp) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("mdseCd", mdseCdItem.getValue());
        params.put("shellLineNum", shellLineNum);
        params.put("mdseTpCtxTp", mdseTpCtxTp);

        return getSsmEZDClient().queryObjectList("getMdseInfoForOnBlurCoveredUnit", params);
    }

    /**
     * @param mdseCdItem EZDCStringItem
     * @param cpoOrdNum String
     * @param shellLineNum BigDecimal
     * @param mdseTpCtxTp String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseInfoForOnBlurCoveredItem(//
            EZDCStringItem mdseCdItem, String cpoOrdNum, BigDecimal shellLineNum, String mdseTpCtxTp) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("shellLineNum", shellLineNum);
        params.put("mdseCd", mdseCdItem.getValue());
        params.put("mdseTpCtxTp", mdseTpCtxTp);

        return getSsmEZDClient().queryObjectList("getMdseInfoForOnBlurCoveredItem", params);
    }

    /**
     * Get LineOfBusinessCode
     * @param bizMsg NSAL1330CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineOfBusinessCd(NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());

        return getSsmEZDClient().queryObjectList("getLineOfBusinessCd", params);
    }

    /**
     * @param prcCatgCd String
     * @param prcCatgNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPriceCategoryNm(String prcCatgCd, String prcCatgNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", prcCatgCd);
        params.put("prcCatgNm", prcCatgNm);

        return getSsmEZDClient().queryObjectList("getPriceCategoryNm", params);
    }

    /**
     * @param prcCatgNm String
     * @return S21SsmEZDResult
     */
    // START 2018/07/10 K.Kojima [QC#27135,MOD]
    // public S21SsmEZDResult getPrcCatgInfoForSave(String prcCatgNm) {
    public S21SsmEZDResult getPrcCatgInfoForSave(String prcCatgNm, NSAL1330CMsg bizMsg) {
    // END 2018/07/10 K.Kojima [QC#27135,MOD]
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgNm", prcCatgNm);
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        return getSsmEZDClient().queryObjectList("getPrcCatgInfoForSave", params);
    }

    /**
     * getPrcRateTpCd
     * @param bizMsg NSAL1330CMsg
     * @param aBizMsg NSAL1330_ACMsg
     * @param isFleet boolean
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcRateTpCd(NSAL1330CMsg bizMsg, NSAL1330_ACMsg aBizMsg, boolean isFleet) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        mapParam.put("prcCatgCd", aBizMsg.prcCatgCd_A.getValue());
        mapParam.put("activFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // mapParam.put("prcBaseDt", ZYPDateUtil.getSalesDate());
        mapParam.put("prcBaseDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
        // QC#29248
//        mapParam.put("mdlId", aBizMsg.mdlId_A.getValue());
        List<BigDecimal> mdlIdList = new ArrayList<BigDecimal>(bizMsg.I.getValidCount());
        if (isFleet) {
            for (int i = 0; i < bizMsg.I.getValidCount(); i++) {
                mdlIdList.add(bizMsg.I.no(i).mdlId_I.getValue());
            }
        } else {
            mdlIdList.add(aBizMsg.mdlId_A.getValue());
        }
        mapParam.put("mdlIdList", mdlIdList);
        mapParam.put("prcMtrPkgPk", aBizMsg.prcMtrPkgPk_A.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("termMthNum", bizMsg.termMthAot.getValue());
        mapParam.put("rownum1", 1);

        return getSsmEZDClient().queryObject("getPrcRateTpCd", mapParam);
    }

    /**
     * getBasePrcForNonMeter
     * @param bizMsg NSAL1330CMsg
     * @param aBizMsg NSAL1330_ACMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBasePrcForNonMeter(NSAL1330CMsg bizMsg, NSAL1330_ACMsg aBizMsg) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        mapParam.put("prcCatgCd", aBizMsg.prcCatgCd_A.getValue());
        mapParam.put("activFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // mapParam.put("slsDt", ZYPDateUtil.getSalesDate());
        mapParam.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
        mapParam.put("mdlId", aBizMsg.mdlId_A.getValue());
        mapParam.put("prcMtrPkgPk", aBizMsg.prcMtrPkgPk_A.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("termMthNum", bizMsg.termMthAot.getValue());
        mapParam.put("rownum1", 1);

        return getSsmEZDClient().queryObject("getBasePrcForNonMeter", mapParam);
    }

    /**
     * getPeriodicServicePriceFromPriceList
     * @param bizMsg NSAL1330CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPeriodicServicePriceFromPriceList(NSAL1330CMsg bizMsg) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        int ixJ = bizMsg.xxNum_B.getValueInt();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        mapParam.put("prcCatgCd", bizMsg.J.no(ixJ).addlBasePrcCatgCd_J.getValue());
        mapParam.put("baseOnly", PRC_RATE_TP.BASE_ONLY);
        mapParam.put("prcListMdseCd", bizMsg.J.no(ixJ).mdseCd_J.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("termMthNum", bizMsg.termMthAot.getValue());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // mapParam.put("slsDt", ZYPDateUtil.getSalesDate());
        mapParam.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
        mapParam.put("rownum1", 1);

        return getSsmEZDClient().queryObject("getPeriodicServicePriceFromPriceList", mapParam);
    }

    public S21SsmEZDResult getPrcListBandCd(String prcListBandDescTxt) {
        Map<String, String> mapParam = new HashMap<String, String>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        mapParam.put("prcListBandDescTxt", prcListBandDescTxt);

        return getSsmEZDClient().queryObjectList("getPrcListBandCd", mapParam);
    }

    public S21SsmEZDResult getBandBasePrc(NSAL1330CMsg bizMsg, int ixZ) {
        NSAL1330_ACMsg aBizMsg = bizMsg.A.no(bizMsg.xxNum_A.getValueInt());
        NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(ixZ);

        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // mapParam.put("slsDt", ZYPDateUtil.getSalesDate());
        mapParam.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        mapParam.put("prcListStruTpService", PRC_LIST_STRU_TP.SERVICE);
        mapParam.put("prcListStruTpServiceTiers", PRC_LIST_STRU_TP.SERVICE_TIERS);
        mapParam.put("prcListStruTpSupplyProgram", PRC_LIST_STRU_TP.SUPPLY_PROGRAM);

        mapParam.put("mdlId", zBizMsg.mdlId_Z.getValue());
        mapParam.put("prcMtrPkgPk", aBizMsg.prcMtrPkgPk_A.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("bllgMtrLbCd", zBizMsg.bllgMtrLbCd_Z.getValue());
        mapParam.put("termMth", bizMsg.termMthAot.getValue());
        mapParam.put("prcListBandDescTxt", zBizMsg.prcListBandDescTxt_Z.getValue());

        mapParam.put("prcCatgCd", aBizMsg.prcCatgCd_A.getValue());

        return getSsmEZDClient().queryObject("getBandBasePrc", mapParam);
    }

    public S21SsmEZDResult getBandBasePrcConfig(NSAL1330CMsg bizMsg, int ixU) {
        NSAL1330_RCMsg rBizMsg = bizMsg.R.no(bizMsg.xxNum_A.getValueInt());
        NSAL1330_UCMsg zBizMsg = bizMsg.U.no(ixU);

        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // mapParam.put("slsDt", ZYPDateUtil.getSalesDate());
        mapParam.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        mapParam.put("prcListStruTpService", PRC_LIST_STRU_TP.SERVICE);
        mapParam.put("prcListStruTpServiceTiers", PRC_LIST_STRU_TP.SERVICE_TIERS);
        mapParam.put("prcListStruTpSupplyProgram", PRC_LIST_STRU_TP.SUPPLY_PROGRAM);

        mapParam.put("mdlId", zBizMsg.mdlId_U.getValue());
        mapParam.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("bllgMtrLbCd", zBizMsg.bllgMtrLbCd_U.getValue());
        mapParam.put("termMth", bizMsg.termMthAot.getValue());
        mapParam.put("prcListBandDescTxt", zBizMsg.prcListBandDescTxt_U.getValue());

        mapParam.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());

        return getSsmEZDClient().queryObject("getBandBasePrc", mapParam);
    }

    // START 2017/10/25 K.Kojima [QC#21690,DEL]
    // public S21SsmEZDResult getDefaultMdlSvcPrcByQlfy(NSAL1330CMsg bizMsg, NSAL1330_ACMsg aBizMsg) {
    //     Map<String, Object> params = new HashMap<String, Object>();
    //     params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
    //     params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
    //     params.put("slsDt", ZYPDateUtil.getSalesDate());
    // 
    //     params.put("mdlId", aBizMsg.mdlId_A.getValue());
    // 
    //     params.put("prcSvcPlnTp", bizMsg.prcSvcPlnTpCd.getValue());
    //     params.put("prcSvcContrTp", bizMsg.prcSvcContrTpCd.getValue());
    // 
    //     params.put("termMth", bizMsg.termMthAot.getValue());
    // 
    //     params.put("prcListStruTpService", PRC_LIST_STRU_TP.SERVICE);
    //     params.put("prcListStruTpServiceTiers", PRC_LIST_STRU_TP.SERVICE_TIERS);
    //     params.put("prcListStruTpSupplyProgram", PRC_LIST_STRU_TP.SUPPLY_PROGRAM);
    // 
    //     params.put("prcCtxTpServicePricing", PRC_CTX_TP.SERVICE_PRICING);
    // 
    //     return getSsmEZDClient().queryObjectList("getDefaultMdlSvcPrcByQlfy", params);
    // }
    // END 2017/10/25 K.Kojima [QC#21690,DEL]

    public S21SsmEZDResult getPrcListStruTp(String prcCatgCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", prcCatgCd);

        return getSsmEZDClient().queryObject("getPrcListStruTp", params);
    }

    /**
     * getDefaultBandForService
     * @param bizMsg NSAL1330CMsg
     * @param aBizMsg NSAL1330_ACMsg
     * @param zBizMsg NSAL1330_ZCMsg
     * @param prcListBandDescTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefaultBandForService(//
            NSAL1330CMsg bizMsg, NSAL1330_ACMsg aBizMsg, NSAL1330_ZCMsg zBizMsg, String prcListBandDescTxt) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // mapParam.put("slsDt", ZYPDateUtil.getSalesDate());
        mapParam.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        mapParam.put("prcCatgCd", aBizMsg.prcCatgCd_A.getValue());
        mapParam.put("mdlId", aBizMsg.mdlId_A.getValue());
        mapParam.put("prcMtrPkgPk", aBizMsg.prcMtrPkgPk_A.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("bllgMtrLbCd", zBizMsg.bllgMtrLbCd_Z.getValue());
        mapParam.put("termMth", bizMsg.termMthAot.getValue());

        mapParam.put("cpc", PRC_RATE_TP.CPC);
        mapParam.put("annual", PRC_RATE_TP.ANNUAL);

        mapParam.put("prcListBandDescTxt", prcListBandDescTxt);

        return getSsmEZDClient().queryObject("getDefaultBandForService", mapParam);
    }

    /**
     * getDefaultBandForService
     * @param bizMsg NSAL1330CMsg
     * @param rBizMsg NSAL1330_RCMsg
     * @param uBizMsg NSAL1330_UCMsg
     * @param prcListBandDescTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefaultBandForService(//
            NSAL1330CMsg bizMsg, NSAL1330_RCMsg rBizMsg, NSAL1330_UCMsg uBizMsg, String prcListBandDescTxt) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // mapParam.put("slsDt", ZYPDateUtil.getSalesDate());
        mapParam.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        mapParam.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());
        mapParam.put("mdlId", rBizMsg.mdlId_R.getValue());
        mapParam.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("bllgMtrLbCd", uBizMsg.bllgMtrLbCd_U.getValue());
        mapParam.put("termMth", bizMsg.termMthAot.getValue());

        mapParam.put("cpc", PRC_RATE_TP.CPC);
        mapParam.put("annual", PRC_RATE_TP.ANNUAL);

        mapParam.put("prcListBandDescTxt", prcListBandDescTxt);

        return getSsmEZDClient().queryObject("getDefaultBandForService", mapParam);
    }

    public S21SsmEZDResult getDefaultBandForServiceTiers(//
            NSAL1330CMsg bizMsg, NSAL1330_ACMsg aBizMsg, NSAL1330_ZCMsg zBizMsg, String prcListBandDescTxt) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // mapParam.put("slsDt", ZYPDateUtil.getSalesDate());
        mapParam.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        mapParam.put("prcCatgCd", aBizMsg.prcCatgCd_A.getValue());
        mapParam.put("mdlId", aBizMsg.mdlId_A.getValue());
        mapParam.put("prcMtrPkgPk", aBizMsg.prcMtrPkgPk_A.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("bllgMtrLbCd", zBizMsg.bllgMtrLbCd_Z.getValue());
        mapParam.put("termMth", bizMsg.termMthAot.getValue());

        mapParam.put("prcListBandDescTxt", prcListBandDescTxt);

        return getSsmEZDClient().queryObject("getDefaultBandForServiceTiers", mapParam);
    }

    public S21SsmEZDResult getDefaultBandForServiceTiers(//
            NSAL1330CMsg bizMsg, NSAL1330_RCMsg rBizMsg, NSAL1330_UCMsg uBizMsg, String prcListBandDescTxt) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // mapParam.put("slsDt", ZYPDateUtil.getSalesDate());
        mapParam.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        mapParam.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());
        mapParam.put("mdlId", rBizMsg.mdlId_R.getValue());
        mapParam.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("bllgMtrLbCd", uBizMsg.bllgMtrLbCd_U.getValue());
        mapParam.put("termMth", bizMsg.termMthAot.getValue());

        mapParam.put("prcListBandDescTxt", prcListBandDescTxt);

        return getSsmEZDClient().queryObject("getDefaultBandForServiceTiers", mapParam);
    }

    public S21SsmEZDResult getDefaultBandForSupplyProgram(//
            NSAL1330CMsg bizMsg, NSAL1330_ACMsg aBizMsg, NSAL1330_ZCMsg zBizMsg, String prcListBandDescTxt) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // mapParam.put("slsDt", ZYPDateUtil.getSalesDate());
        mapParam.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        mapParam.put("prcCatgCd", aBizMsg.prcCatgCd_A.getValue());
        mapParam.put("mdlId", aBizMsg.mdlId_A.getValue());
        mapParam.put("prcMtrPkgPk", aBizMsg.prcMtrPkgPk_A.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("bllgMtrLbCd", zBizMsg.bllgMtrLbCd_Z.getValue());
        mapParam.put("termMth", bizMsg.termMthAot.getValue());

        mapParam.put("prcListBandDescTxt", prcListBandDescTxt);

        return getSsmEZDClient().queryObject("getDefaultBandForSupplyProgram", mapParam);
    }

    public S21SsmEZDResult getDefaultBandForSupplyProgram(//
            NSAL1330CMsg bizMsg, NSAL1330_RCMsg rBizMsg, NSAL1330_UCMsg uBizMsg, String prcListBandDescTxt) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // mapParam.put("slsDt", ZYPDateUtil.getSalesDate());
        mapParam.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        mapParam.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());
        mapParam.put("mdlId", rBizMsg.mdlId_R.getValue());
        mapParam.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("bllgMtrLbCd", uBizMsg.bllgMtrLbCd_U.getValue());
        mapParam.put("termMth", bizMsg.termMthAot.getValue());

        mapParam.put("prcListBandDescTxt", prcListBandDescTxt);

        return getSsmEZDClient().queryObject("getDefaultBandForSupplyProgram", mapParam);
    }

    public S21SsmEZDResult getDefaultHeaderRentalEqPrcListFromCPO(NSAL1330CMsg bizMsg) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        mapParam.put("cpoOrdNum", bizMsg.xxScrItem50Txt.getValue());

        return getSsmEZDClient().queryObject("getDefaultHeaderRentalEqPrcListFromCPO", mapParam);
    }

    public S21SsmEZDResult getContractIndicatorList() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getContractIndicatorList", params);
    }

    public S21SsmEZDResult getCountPrcListBandFromDescTxt(String prcListBandDescTxt) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcListBandDescTxt", prcListBandDescTxt);

        return getSsmEZDClient().queryObject("getCountPrcListBandFromDescTxt", params);
    }

    public S21SsmEZDResult getCountValidItem(//
            String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum, String[] mdseTpCtxTp, String target) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("cpoDtlLineNum", cpoDtlLineNum);
        params.put("cpoDtlLineSubNum", cpoDtlLineSubNum);
        params.put("mdseTpCtxTp", mdseTpCtxTp);
        params.put("target", target);
        // Add Start 2018/07/20 QC#27268
        String targetFlg = null;
        // Mod Start 2018/07/27 QC#27492
//        if (S21StringUtil.isEquals(NSAL1330Constant.TARGET_ADDL_SVC_CHRG, target)) {
        if (!S21StringUtil.isEquals(NSAL1330Constant.TARGET_ADDL_SVC_CHRG, target)) {
            targetFlg = ZYPConstant.FLG_ON_Y;
        }
        // Mod End 2018/07/27 QC#27492
        params.put("targetFlg", targetFlg);
        // Add End 2018/07/20 QC#27268

        return getSsmEZDClient().queryObject("getCountValidItem", params);
    }

    public S21SsmEZDResult getSplyAgmtPlnNm(NSAL1330_ACMsg aBizMsg, NSAL1330_ZCMsg zBizMsg, NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdlId", aBizMsg.mdlId_A);
        params.put("prcMtrPkgPk", aBizMsg.prcMtrPkgPk_A);
        params.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd);
        params.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd);
        params.put("bllgMtrLbCd", zBizMsg.bllgMtrLbCd_Z);
        params.put("prcCatgCd", aBizMsg.prcCatgCd_A);
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
        params.put("termMth", bizMsg.termMthAot);
        params.put("prcListBandDescTxt", zBizMsg.prcListBandDescTxt_Z);

        return getSsmEZDClient().queryObjectList("getSplyAgmtPlnNm", params);
    }

    public S21SsmEZDResult getSplyAgmtPlnNm(//
            NSAL1330_RCMsg rBizMsg, NSAL1330_UCMsg uBizMsg, NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdlId", rBizMsg.mdlId_R);
        params.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R);
        params.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd);
        params.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd);
        params.put("bllgMtrLbCd", uBizMsg.bllgMtrLbCd_U);
        params.put("prcCatgCd", rBizMsg.prcCatgCd_R);
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
        params.put("termMth", bizMsg.termMthAot);
        params.put("prcListBandDescTxt", uBizMsg.prcListBandDescTxt_U);

        return getSsmEZDClient().queryObjectList("getSplyAgmtPlnNm", params);
    }

    public S21SsmEZDResult getAnnualPrc(String glblCmpyCd, NSAL1330CMsg bizMsg, NSAL1330_RCMsg rBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("annual", PRC_RATE_TP.ANNUAL);
        params.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());
        params.put("mdlId", rBizMsg.mdlId_R.getValue().toString());
        params.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R.getValue());
        params.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
        params.put("termMthNum", rBizMsg.termMthAot_R.getValue().toString());

        return getSsmEZDClient().queryObjectList("getAnnualPrc", params);
    }

    public S21SsmEZDResult getTierdByCatg(NSAL1330_RCMsg aBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", aBizMsg.prcCatgCd_R.getValue());

        return getSsmEZDClient().queryObjectList("getTierdByCatg", params);
    }

    public S21SsmEZDResult getPrcRateTpCd(NSAL1330CMsg bizMsg, NSAL1330_RCMsg rBizMsg) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        mapParam.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());
        mapParam.put("activFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // mapParam.put("prcBaseDt", ZYPDateUtil.getSalesDate());
        mapParam.put("prcBaseDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
        // QC#29248
//        mapParam.put("mdlId", rBizMsg.mdlId_R.getValue());
        List<BigDecimal> mdlIdList = new ArrayList<BigDecimal>(bizMsg.I.getValidCount());
        mdlIdList.add(rBizMsg.mdlId_R.getValue());
        mapParam.put("mdlIdList", mdlIdList);
        mapParam.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("termMthNum", bizMsg.termMthAot.getValue());
        mapParam.put("rownum1", 1);

        return getSsmEZDClient().queryObject("getPrcRateTpCd", mapParam);
    }

    public S21SsmEZDResult getBasePrcForNonMeter(NSAL1330CMsg bizMsg, NSAL1330_RCMsg rBizMsg) {
        Map<String, Object> mapParam = new HashMap<String, Object>();

        mapParam.put("glblCmpyCd", getGlobalCompanyCode());
        mapParam.put("prcCatgCd", rBizMsg.prcCatgCd_R.getValue());
        mapParam.put("activFlg", ZYPConstant.FLG_ON_Y);
        mapParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // mapParam.put("slsDt", ZYPDateUtil.getSalesDate());
        mapParam.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
        mapParam.put("mdlId", rBizMsg.mdlId_R.getValue());
        mapParam.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R.getValue());
        mapParam.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        mapParam.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        mapParam.put("termMthNum", bizMsg.termMthAot.getValue());
        mapParam.put("rownum1", 1);

        return getSsmEZDClient().queryObject("getBasePrcForNonMeter", mapParam);
    }

    // QC#16141 ADD START
    /**
     * get Bill To Customer Info List
     * @param bizMsg NSAL1320CMsg
     * @param custLocNum Customer location Number
     * @return Bill To Customer Info List
     */
    public S21SsmEZDResult getBillToCustInfoList(NSAL1330CMsg bizMsg, String custLocNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("custCd", custLocNum);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getBillToCustInfoList", params);
    }

    // QC#16141 ADD END

    public S21SsmEZDResult cntOverlappingTerm(NSAL1330_UCMsg uBizMsg, NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("refCpoOrdNum", bizMsg.refCpoOrdNum);
        params.put("dsContrPk", bizMsg.dsContrPk);
        params.put("shellLineNum", bizMsg.shellLineNum);
        params.put("fromMth", bizMsg.fromPerMthNum);
        params.put("toMth", bizMsg.toPerMthNum);
        params.put("bllgMtrLbCd", uBizMsg.bllgMtrLbCd_U);
        params.put("dsOrdPosnNum", uBizMsg.dsOrdPosnNum_U);
        // START 2019/01/18 K.Fujimoto [QC#29782,ADD]
        params.put("dsContrCratTpShell", DS_CONTR_CRAT_TP.SHELL);
        // END   2019/01/18 K.Fujimoto [QC#29782,ADD]

        return getSsmEZDClient().queryObject("cntOverlappingTerm", params);
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInitDataFromShellContrHeader(NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bizMsg.dsContrPk.getValue());
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("shellLineNum", bizMsg.shellLineNum.getValue());
        params.put("dsContrStsCd", DS_CONTR_STS.ORDER);
        // START 2017/07/28 [QC#20088, ADD]
        params.put("svcMemoTpCd", SVC_MEMO_TP.OVERRIDE_SHELL_CONTRACT);
        params.put("svcMemoTrxNm", NSAL1330Constant.SVC_MEMO_TRX_NM_CPO_ORD_NUM);
        // END   2017/07/28 [QC#20088, ADD]
        // START 2019/01/18 K.Fujimoto [QC#29782,ADD]
        params.put("dsContrCratTpShell", DS_CONTR_CRAT_TP.SHELL);
        // END   2019/01/18 K.Fujimoto [QC#29782,ADD]

        String stmtId = "getInitDataFromShellContrHeader";
        return getSsmEZDClient().queryObject(stmtId, params);
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInitDataFromModelPricingHeader(NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bizMsg.dsContrPk.getValue());
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("dsContrStsCd", DS_CONTR_STS.ORDER);
        // START 2017/06/20 [QC#19325, ADD]
        params.put("dsContrDtlStsCd", DS_CONTR_DTL_STS.ORDER);
        // END   2017/06/20 [QC#19325, ADD]
        params.put("ordLineStsPartiallyShipped", ORD_LINE_STS.PARTIALLY_SHIPPED);
        params.put("shpgStsArrived", SHPG_STS.ARRIVED);
        params.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        params.put("dsContrDtlTpCdMac", new String[] { DS_CONTR_DTL_TP.BASE_ONLY, DS_CONTR_DTL_TP.USAGE_ONLY, DS_CONTR_DTL_TP.BASE_AND_USAGE } );
        params.put("xsMtrFirstFlgN", ZYPConstant.FLG_OFF_N);
        // START 2017/06/14 [QC#19039, ADD]
        params.put("isFleet" , DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue()) ? ZYPConstant.FLG_ON_Y : null);
        // END   2017/06/14 [QC#19039, ADD]
        // START 2019/01/18 K.Fujimoto [QC#29782,ADD]
        params.put("dsContrCratTpShell", DS_CONTR_CRAT_TP.SHELL);
        // END   2019/01/18 K.Fujimoto [QC#29782,ADD]
        // START 2019/10/09 K.Kitachi [QC#54074, ADD]
        params.put("ordLineStsCancelled", ORD_LINE_STS.CANCELLED);
        params.put("shpgStsCancelled", SHPG_STS.CANCELLED);
        // END 2019/10/09 K.Kitachi [QC#54074, ADD]

        String stmtId = "getInitDataFromModelPricingHeader";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInitDataFromModelPricingDetail(NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bizMsg.dsContrPk.getValue());
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        params.put("dsContrStsCd", DS_CONTR_STS.ORDER);
        params.put("xsMtrFirstFlg", ZYPConstant.FLG_ON_Y);
        params.put("xsMtrFirstFlgN", ZYPConstant.FLG_OFF_N);
        // START 2017/08/09 T.Kanasaka [QC#18193,DEL]
//        params.put("isRegular" , DS_CONTR_CATG.REGULAR.equals(bizMsg.dsContrCatgCd.getValue()) ? ZYPConstant.FLG_ON_Y : null);
//        params.put("isFleet" , DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue()) ? ZYPConstant.FLG_ON_Y : null);
//        params.put("isAggregate", DS_CONTR_CATG.AGGREGATE.equals(bizMsg.dsContrCatgCd.getValue()) ? ZYPConstant.FLG_ON_Y : null);
        // END 2017/08/09 T.Kanasaka [QC#18193,DEL]
        params.put("flgNmP", NSAL1330Constant.XX_FLG_PARENT);
        params.put("flgNmH", NSAL1330Constant.XX_FLG_HARD);
        params.put("flgNmT", NSAL1330Constant.XX_FLG_TIER);
        // START 2019/01/18 K.Fujimoto [QC#29782,ADD]
        params.put("dsContrCratTpShell", DS_CONTR_CRAT_TP.SHELL);
        // END   2019/01/18 K.Fujimoto [QC#29782,ADD]

        String stmtId = "getInitDataFromModelPricingDetail";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInitDataFromConfigPricingHeader(NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bizMsg.dsContrPk.getValue());
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("dsContrStsCd", DS_CONTR_STS.ORDER);
        // START 2017/06/20 [QC#19325, ADD]
        params.put("dsContrDtlStsCd", DS_CONTR_DTL_STS.ORDER);
        // END   2017/06/20 [QC#19325, ADD]
        params.put("ordLineStsPartiallyShipped", ORD_LINE_STS.PARTIALLY_SHIPPED);
        params.put("shpgStsArrived", SHPG_STS.ARRIVED);
        params.put("dsContrDtlTpCdMac", new String[] { DS_CONTR_DTL_TP.BASE_ONLY, DS_CONTR_DTL_TP.USAGE_ONLY, DS_CONTR_DTL_TP.BASE_AND_USAGE } );
        params.put("configCatgOutBound", CONFIG_CATG.OUTBOUND);
        params.put("xsMtrFirstFlgN", ZYPConstant.FLG_OFF_N);
        // START 2019/01/18 K.Fujimoto [QC#29782,ADD]
        params.put("dsContrCratTpShell", DS_CONTR_CRAT_TP.SHELL);
        // END   2019/01/18 K.Fujimoto [QC#29782,ADD]
        // START 2019/02/26 K.Fujimoto [QC#30525,ADD]
        params.put("dsContrCratTpSupply", DS_CONTR_CRAT_TP.SUPPLY);
        // END   2019/02/26 K.Fujimoto [QC#30525,ADD]
        // 2019/05/08 QC#50174 Add Start
        params.put("ordLineStsCancel", ORD_LINE_STS.CANCELLED);
        // 2019/05/08 QC#50174 Add End
        // START 2019/10/09 K.Kitachi [QC#54074, ADD]
        params.put("shpgStsCancelled", SHPG_STS.CANCELLED);
        // END 2019/10/09 K.Kitachi [QC#54074, ADD]
        String stmtId = "getInitDataFromConfigPricingHeader";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInitDataFromConfigPricingDetail(NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bizMsg.dsContrPk.getValue());
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("shpgStsArrived", SHPG_STS.ARRIVED);
        params.put("xsMtrFirstFlg", ZYPConstant.FLG_ON_Y);
        params.put("xsMtrFirstFlgN", ZYPConstant.FLG_OFF_N);
        // START 2017/08/09 T.Kanasaka [QC#18193,DEL]
//        params.put("isRegular" , DS_CONTR_CATG.REGULAR.equals(bizMsg.dsContrCatgCd.getValue()) ? ZYPConstant.FLG_ON_Y : null);
//        params.put("isFleet" , DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue()) ? ZYPConstant.FLG_ON_Y : null);
//        params.put("isAggregate", DS_CONTR_CATG.AGGREGATE.equals(bizMsg.dsContrCatgCd.getValue()) ? ZYPConstant.FLG_ON_Y : null);
        // END 2017/08/09 T.Kanasaka [QC#18193,DEL]
        params.put("dsContrDtlTpCd", new String[] { DS_CONTR_DTL_TP.BASE_ONLY, DS_CONTR_DTL_TP.USAGE_ONLY, DS_CONTR_DTL_TP.BASE_AND_USAGE } );
        params.put("flgNmP", NSAL1330Constant.XX_FLG_PARENT);
        params.put("flgNmH", NSAL1330Constant.XX_FLG_HARD);
        params.put("flgNmT", NSAL1330Constant.XX_FLG_TIER);
        // START 2019/01/18 K.Fujimoto [QC#29782,ADD]
        params.put("dsContrCratTpShell", DS_CONTR_CRAT_TP.SHELL);
        // END   2019/01/18 K.Fujimoto [QC#29782,ADD]
        // 2019/05/08 QC#50174 Add Start
        params.put("ordLineStsCancel", ORD_LINE_STS.CANCELLED);
        // 2019/05/08 QC#50174 Add End

        String stmtId = "getInitDataFromConfigPricingDetail";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInitDataFromAccCharge(NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bizMsg.dsContrPk.getValue());
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("dsContrDtlTpCd", new String[] { DS_CONTR_DTL_TP.ACCESSORIES } );
        // START 2017/06/20 [QC#19325, MOD]
//        params.put("dsContrStsCd", DS_CONTR_STS.ORDER);
        params.put("dsContrDtlStsCd", DS_CONTR_DTL_STS.ORDER);
        // END   2017/06/20 [QC#19325, MOD]
        params.put("ordLineStsPartiallyShipped", ORD_LINE_STS.PARTIALLY_SHIPPED);
        params.put("shpgStsArrived", SHPG_STS.ARRIVED);
        // START 2017/06/14 [QC#18934, ADD]
        params.put("isFleet" , DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue()) ? ZYPConstant.FLG_ON_Y : null);
        // END   2017/06/14 [QC#18934, ADD]
        // START 2019/01/18 K.Fujimoto [QC#29782,ADD]
        params.put("dsContrCratTpShell", DS_CONTR_CRAT_TP.SHELL);
        params.put("dsContrCratTpSupply", DS_CONTR_CRAT_TP.SUPPLY);
        params.put("dsContrCratTpOther", DS_CONTR_CRAT_TP.OTHER);
        // END   2019/01/18 K.Fujimoto [QC#29782,ADD]
        // START 2019/10/09 K.Kitachi [QC#54074, ADD]
        params.put("ordLineStsCancelled", ORD_LINE_STS.CANCELLED);
        params.put("shpgStsCancelled", SHPG_STS.CANCELLED);
        // END 2019/10/09 K.Kitachi [QC#54074, ADD]

        String stmtId = "getInitDataFromAccCharge";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInitDataFromRentalEqChargeBaseAcc(NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bizMsg.dsContrPk.getValue());
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("addlChrgCatgCd", ADDL_CHRG_CATG.RENTAL);
        // START 2017/06/20 [QC#19325, MOD]
//        params.put("dsContrStsCd", DS_CONTR_STS.ORDER);
        params.put("dsContrDtlStsCd", DS_CONTR_DTL_STS.ORDER);
        // END   2017/06/20 [QC#19325, MOD]
        params.put("ordLineStsPartiallyShipped", ORD_LINE_STS.PARTIALLY_SHIPPED);
        params.put("shpgStsArrived", SHPG_STS.ARRIVED);
        // START 2019/01/18 K.Fujimoto [QC#29782,ADD]
        params.put("dsContrCratTpShell", DS_CONTR_CRAT_TP.SHELL);
        // END   2019/01/18 K.Fujimoto [QC#29782,ADD]
        // START 2019/10/09 K.Kitachi [QC#54074, ADD]
        params.put("ordLineStsCancelled", ORD_LINE_STS.CANCELLED);
        params.put("shpgStsCancelled", SHPG_STS.CANCELLED);
        // END 2019/10/09 K.Kitachi [QC#54074, ADD]

        String stmtId = "getInitDataFromRentalEqChargeBaseAcc";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInitDataFromAddlSvcCharge(NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bizMsg.dsContrPk.getValue());
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("dsContrDtlTpCd", new String[] { DS_CONTR_DTL_TP.BASE_ONLY, DS_CONTR_DTL_TP.BASE_AND_USAGE, DS_CONTR_DTL_TP.ACCESSORIES } );
        params.put("addlChrgCatgCd", ADDL_CHRG_CATG.NORMAL);
        // START 2017/06/20 [QC#19325, MOD]
//        params.put("dsContrStsCd", DS_CONTR_STS.ORDER);
        params.put("dsContrDtlStsCd", DS_CONTR_DTL_STS.ORDER);
        // END   2017/06/20 [QC#19325, MOD]
        params.put("ordLineStsPartiallyShipped", ORD_LINE_STS.PARTIALLY_SHIPPED);
        params.put("shpgStsArrived", SHPG_STS.ARRIVED);
        // START 2019/01/18 K.Fujimoto [QC#29782,ADD]
        params.put("dsContrCratTpShell", DS_CONTR_CRAT_TP.SHELL);
        // END   2019/01/18 K.Fujimoto [QC#29782,ADD]
        // START 2019/10/09 K.Kitachi [QC#54074, ADD]
        params.put("ordLineStsCancelled", ORD_LINE_STS.CANCELLED);
        params.put("shpgStsCancelled", SHPG_STS.CANCELLED);
        // END 2019/10/09 K.Kitachi [QC#54074, ADD]

        String stmtId = "getInitDataFromAddlSvcCharge";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsOrdPosnInfo(NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("refCpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("shellLineNum", bizMsg.shellLineNum.getValue());
        // mod start 2017/06/21 CSA Defect#19330
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.addAsryFlg.getValue())) {
            params.put("dsContrDtlTpCd", new String[] {DS_CONTR_DTL_TP.ACCESSORIES });
        } else {
            params.put("dsContrDtlTpCd", new String[] {DS_CONTR_DTL_TP.BASE_ONLY, DS_CONTR_DTL_TP.USAGE_ONLY, DS_CONTR_DTL_TP.BASE_AND_USAGE });
        }
        // mod end 2017/06/21 CSA Defect#19330
        // START 2019/01/09 S.Kitamura [QC#26928, ADD]
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        // END 2019/01/09 S.Kitamura [QC#26928, ADD]
        // START 2019/01/18 K.Fujimoto [QC#29782,ADD]
        params.put("dsContrCratTpShell", DS_CONTR_CRAT_TP.SHELL);
        // END   2019/01/18 K.Fujimoto [QC#29782,ADD]
        String stmtId = "getDsOrdPosnInfo";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    // START 2017/10/25 [QC#21556, ADD]
    /**
     * @param bizMsg NSAL1330CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInitDataFromModelPricingHeaderForFleetAddMachine(NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bizMsg.dsContrPk.getValue());
        params.put("cpoOrdNum", bizMsg.refCpoOrdNum.getValue());
        params.put("dsContrStsCd", DS_CONTR_STS.ORDER);
        params.put("dsContrDtlStsCd", DS_CONTR_DTL_STS.ORDER);
        params.put("ordLineStsPartiallyShipped", ORD_LINE_STS.PARTIALLY_SHIPPED);
        params.put("shpgStsArrived", SHPG_STS.ARRIVED);
        params.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        params.put("dsContrDtlTpCdFlt", DS_CONTR_DTL_TP.FLEET);
        params.put("dsContrDtlTpCdMac", new String[] { DS_CONTR_DTL_TP.BASE_ONLY, DS_CONTR_DTL_TP.USAGE_ONLY, DS_CONTR_DTL_TP.BASE_AND_USAGE } );
        params.put("xsMtrFirstFlgN", ZYPConstant.FLG_OFF_N);
        params.put("isFleet" , DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue()) ? ZYPConstant.FLG_ON_Y : null);
        // START 2019/01/18 K.Fujimoto [QC#29782,ADD]
        params.put("dsContrCratTpShell", DS_CONTR_CRAT_TP.SHELL);
        // END   2019/01/18 K.Fujimoto [QC#29782,ADD]

        String stmtId = "getInitDataFromModelPricingHeaderForFleetAddMachine";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInitDataFromModelPricingDetailForFleetAddMachine(NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bizMsg.dsContrPk.getValue());
        params.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        params.put("dsContrDtlTpCdFlt", DS_CONTR_DTL_TP.FLEET);
        params.put("xsMtrFirstFlg", ZYPConstant.FLG_ON_Y);
        params.put("xsMtrFirstFlgN", ZYPConstant.FLG_OFF_N);
        params.put("flgNmP", NSAL1330Constant.XX_FLG_PARENT);
        params.put("flgNmH", NSAL1330Constant.XX_FLG_HARD);
        params.put("flgNmT", NSAL1330Constant.XX_FLG_TIER);
        // START 2019/01/18 K.Fujimoto [QC#29782,ADD]
        params.put("dsContrCratTpShell", DS_CONTR_CRAT_TP.SHELL);
        // END   2019/01/18 K.Fujimoto [QC#29782,ADD]

        String stmtId = "getInitDataFromModelPricingDetailForFleetAddMachine";
        return getSsmEZDClient().queryObjectList(stmtId, params);
    }

    public S21SsmEZDResult getMtrLbAggAddMachine(NSAL1330CMsg bizMsg, String[] bllgMtrLbCds) {
        int index = bizMsg.xxNum_A.getValueInt();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcMtrPkgPk", bizMsg.A.no(index).prcMtrPkgPk_A.getValue().toString());
        params.put("mdlId", bizMsg.A.no(index).mdlId_A.getValue().toString());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
        params.put("bllgMtrLbCd", bllgMtrLbCds);

        return getSsmEZDClient().queryObjectList("getMtrLbAggAddMachine", params);
    }

    // START 2018/07/10 K.Kojima [QC#27135,MOD]
    // public S21SsmEZDResult getMtrLbAggAddMachineConfig(NSAL1330_RCMsg rBizMsg, String[] bllgMtrLbCds) {
    public S21SsmEZDResult getMtrLbAggAddMachineConfig(NSAL1330_RCMsg rBizMsg, String[] bllgMtrLbCds, NSAL1330CMsg bizMsg) {
    // END 2018/07/10 K.Kojima [QC#27135,MOD]
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcMtrPkgPk", rBizMsg.prcMtrPkgPk_R.getValue().toString());
        params.put("mdlId", rBizMsg.mdlId_R.getValue().toString());
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("slsDt", bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
        params.put("bllgMtrLbCd", bllgMtrLbCds);

        return getSsmEZDClient().queryObjectList("getMtrLbAggAddMachine", params);
    }

    public S21SsmEZDResult getAggContrBllgLbCdList(NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrPk", bizMsg.dsContrPk_AD.getValue());
        params.put("dsContrCatgCd", bizMsg.dsContrCatgCd.getValue());
        params.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);

        return getSsmEZDClient().queryObjectList("getAggContrBllgLbCdList", params);
    }

    public S21SsmEZDResult getAggContrXsMtrAmtRate(NSAL1330CMsg bizMsg, NSAL1330_ZCMsg zBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrPk", bizMsg.dsContrPk_AD.getValue());
        params.put("dsContrCatgCd", bizMsg.dsContrCatgCd.getValue());
        params.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        params.put("bllgMtrLbCd", zBizMsg.bllgMtrLbCd_Z.getValue());
        params.put("xsMtrFirstFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObject("getAggContrXsMtrAmtRate", params);
    }

    public S21SsmEZDResult getAggContrXsMtrAmtRateConfig(NSAL1330CMsg bizMsg, NSAL1330_UCMsg uBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrPk", bizMsg.dsContrPk_AD.getValue());
        params.put("dsContrCatgCd", bizMsg.dsContrCatgCd.getValue());
        params.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        params.put("bllgMtrLbCd", uBizMsg.bllgMtrLbCd_U.getValue());
        params.put("xsMtrFirstFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObject("getAggContrXsMtrAmtRate", params);
    }

    public S21SsmEZDResult getTierInfo_BandSearchForAggAddMachine(NSAL1330CMsg bizMsg, NSAL1330_ZCMsg zBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrPk", bizMsg.dsContrPk_AD.getValue());
        params.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        params.put("bllgMtrLbCd", zBizMsg.bllgMtrLbCd_Z.getValue());

        return getSsmEZDClient().queryObjectList("getTierInfo_BandSearchForAggAddMachine", params);
    }

    public S21SsmEZDResult getTierInfo_BandSearchForAggAddMachine(NSAL1330CMsg bizMsg) {
        NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(bizMsg.xxNum_Z.getValueInt());
        return getTierInfo_BandSearchForAggAddMachine(bizMsg, zBizMsg);
    }

    public S21SsmEZDResult getTierInfo_BandSearchForConfigAggAddMachine(NSAL1330CMsg bizMsg, NSAL1330_UCMsg uBizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrPk", bizMsg.dsContrPk_AD.getValue());
        params.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        params.put("bllgMtrLbCd", uBizMsg.bllgMtrLbCd_U.getValue());

        return getSsmEZDClient().queryObjectList("getTierInfo_BandSearchForAggAddMachine", params);
    }

    public S21SsmEZDResult getTierInfo_BandSearchForConfigAggAddMachine(NSAL1330CMsg bizMsg) {
        NSAL1330_UCMsg uBizMsg = bizMsg.U.no(bizMsg.xxNum_Z.getValueInt());
        return getTierInfo_BandSearchForConfigAggAddMachine(bizMsg, uBizMsg);
    }

    public S21SsmEZDResult getPrcTierSvcOfferCdFromContrForAddMachine(NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsContrPk", bizMsg.dsContrPk_AD.getValue());
        params.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);

        return getSsmEZDClient().queryObject("getPrcTierSvcOfferCdFromContrForAddMachine", params);
    }
    // END   2017/10/25 [QC#21556, ADD]

    // START 2018/10/10 K.Kojima [QC#28715,ADD]
    public S21SsmEZDResult getAddlChrgMdseItemTpCd(NSAL1330CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("itemTpCtxTpCd", "CPO_SVC_ADDL_CHRG_QLFY_ITEM_TP");

        return getSsmEZDClient().queryObject("getAddlChrgMdseItemTpCd", params);
    }
    // END 2018/10/10 K.Kojima [QC#28715,ADD]

    // 2019/07/18 S21_NA#51327 Add Start
//    public S21SsmEZDResult checkCreatedContract(NSAL1330CMsg bizMsg, List<BigDecimal> dsContrDtlPk) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        params.put("dsContrDtlPk", dsContrDtlPk);
//        params.put("dsContrStsCd", DS_CONTR_STS.ORDER);
//
//        return getSsmEZDClient().queryObjectList("checkCreatedContract", params);
//
//     }
    // 2019/07/18 S21_NA#51327 Add End
}
