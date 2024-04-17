/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLEL0060;

import static business.blap.NLEL0060.constant.NLEL0060Constant.COL_DS_ASSET_MSTR_PK;
import static business.blap.NLEL0060.constant.NLEL0060Constant.ON_SITE;
import static business.blap.NLEL0060.constant.NLEL0060Constant.SOLD;
import static business.blap.NLEL0060.constant.NLEL0060Constant.YYYY_LENGTH;
import static business.blap.NLEL0060.constant.NLEL0060Constant.PROC_MODE_51;
import static business.blap.NLEL0060.constant.NLEL0060Constant.PROC_MODE_11;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLEL0060.common.NLEL0060CommonLogic;
import business.db.AMT_CHNG_RSN_TPTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_ASSET_HIST_COL_MAPTMsg;
import business.db.DS_ASSET_HIST_COL_MAPTMsgArray;
import business.db.DS_ASSET_MSTRTMsg;

import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INTFC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NLEL0060Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/04/15   Hitachi         T.Tsuchida      Update          QC#7050
 * 2016/04/15   Hitachi         T.Tsuchida      Update          QC#7135
 * 2016/04/19   Hitachi         J.Kim           Update          QC#6774
 * 2016/06/03   Hitachi         T.Tsuchida      Update          QC#8981
 * 2016/06/03   Hitachi         T.Tsuchida      Update          QC#9472
 * 2016/06/09   Hitachi         T.Tsuchida      Update          QC#9663
 * 2016/06/30   Hitachi         J.Kim           Update          QC#10864
 * 2016/07/21   Hitachi         Y.Tsuchimoto    Update          QC#11019
 * 2016/08/30   Fujitsu         C.Tanaka        Update          QC#13360
 * 2016/09/14   Fujitsu         C.Tanaka        Update          QC#12697
 * 2016/09/26   Fujitsu         C.Tanaka        Update          QC#11422, 14407
 * 2016/09/27   Hitachi         J.Kim           Update          QC#13372
 * 2016/10/20   Hitachi         J.Kim           Update          QC#11899
 * 2016/10/20   Hitachi         J.Kim           Update          QC#11899
 * 2016/12/16   Hitachi         E.Kameishi      Update          QC#15901
 * 2017/02/21   Hitachi         J.Kim           Update          QC#17589
 * 2017/05/15   CITS            M.Naito         Update          Merge DS Lv2
 * 2017/03/09   Hitachi         E.Kameishi      Update          QC#17987
 * 2017/11/07   Hitachi         J.Kim           Update          QC#16345
 * 2018/01/26   CITS            K.Ogino         Update          QC#22750
 * 2018/02/02   Hitachi         J.Kim           Update          QC#22267
 * 2018/03/07   Hitachi         J.Kim           Update          QC#24636
 * 2018/04/16   Hitachi         J.Kim           Update          QC#22807
 * 2018/05/15   CITS            K.Ogino         Update          QC#25162
 * 2018/05/17   Hitachi         J.Kim           Update          QC#25879
 * 2018/07/24   Hitachi         K.Kojima        Update          QC#27233
 *</pre>
 */
public final class NLEL0060Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NLEL0060Query MY_INSTANCE = new NLEL0060Query();

    /**
     * Private constructor
     */
    private NLEL0060Query() {
        super();
    }

    /**
     * Get the NLEL0060Query instance.
     * @return NLEL0060Query instance
     */
    public static NLEL0060Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getAvalAssetStsList
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAvalAssetStsList(String glblCmpyCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsCondConstGrpId", "NLEL0060_ASSET_STS");

        return getSsmEZDClient().queryObjectList("getAvalAssetStsList", params);
    }

    /**
     * Search Detail
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchDetail(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("bizMsg", bizMsg);
        // START 2016/06/30 J.Kim [QC#10864,ADD]
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        // END 2016/06/30 J.Kim [QC#10864,ADD]
        params.put("rowNum", glblMsg.A.length() + 1);
        // START 2017/05/15 M.Naito
        params.put("svcMachStsCRAT", SVC_MACH_MSTR_STS.CREATED);
        params.put("svcMachStsRMV", SVC_MACH_MSTR_STS.REMOVED);
        // END 2017/05/15 M.Naito
        params.put("dsCondConstGrpId", "NLEL0060_ASSET_STS");
        // START 2018/03/07 J.Kim [QC#24636,ADD]
        params.put("procMode", PROC_MODE_51);
        params.put("mdseItemTp", MDSE_ITEM_TP.TEXT_ITEM);
        // END 2018/03/07 J.Kim [QC#24636,ADD]
        // START 2018/04/06 J.Kim [QC#23703,ADD]
        params.put("assetTpCd", ASSET_TP.EMSD_ASSET);
        // END 2018/04/06 J.Kim [QC#23703,ADD]
        // START 2018/04/16 J.Kim [QC#22807,ADD]
        // QC#25162
        params.put("depcCoaAcctCdFlg", NLEL0060CommonLogic.existCoaFlg(bizMsg.depcCoaAcctCd_S1, bizMsg.depcCoaAcctCd_S2));
        params.put("existCoaFlg", NLEL0060CommonLogic.existCoaFlg(bizMsg));
        // END 2018/04/16 J.Kim [QC#22807,ADD]
        // START 2018/06/14 J.Kim [QC#26639,ADD]
        params.put("assetStsCd", ASSET_STS.RETIRE);
        // END 2018/06/14 J.Kim [QC#24844,ADD]
        // START 2018/06/20 J.Kim [QC#24936,ADD]
        params.put("procMode11", PROC_MODE_11);
        // END 2018/06/20 J.Kim [QC#24844,ADD]
        // START 2018/07/24 K.Kojima [QC#27233,ADD]
        params.put("sellToCustSearchFlag", NLEL0060CommonLogic.getSellToCustSearchFlag(bizMsg));
        // END 2018/07/24 K.Kojima [QC#27233,ADD]

        return getSsmEZDClient().queryEZDMsgArray("searchDetail", params, glblMsg.A);
    }

    /**
     * Search Assignment
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchAssignment(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("bizMsg", bizMsg);
        // START 2016/04/19 J.Kim [QC#6774,ADD]
        params.put("rtrnWhCd", ON_SITE);
        // END 2016/04/19 J.Kim [QC#6774,ADD]
        // START 2016/04/15 T.Tsuchida [QC#7135,MOD]
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        // END 2016/04/15 T.Tsuchida [QC#7135,MOD]
        params.put("rowNum", glblMsg.B.length() + 1);

        String glblCmpyCd = getGlobalCompanyCode();
        params.put("newcore", ZYPCodeDataUtil.getVarCharConstValue("AJE_NEW_CORE_CD", glblCmpyCd));
        params.put("defBrNewcore", ZYPCodeDataUtil.getVarCharConstValue("AJE_DEF_COA_BR_NONCORE", glblCmpyCd));
        params.put("defCcNewcore", ZYPCodeDataUtil.getVarCharConstValue("AJE_DEF_COA_CC_NONCORE", glblCmpyCd));

        // START 2017/05/15 M.Naito
        params.put("svcMachStsCRAT", SVC_MACH_MSTR_STS.CREATED);
        params.put("svcMachStsRMV", SVC_MACH_MSTR_STS.REMOVED);
        // END 2017/05/15 M.Naito
        params.put("dsCondConstGrpId", "NLEL0060_ASSET_STS");
        // START 2018/04/16 J.Kim [QC#22807,ADD]
        params.put("procMode", PROC_MODE_51);
        // QC#25162
        params.put("depcCoaAcctCdFlg", NLEL0060CommonLogic.existCoaFlg(bizMsg.depcCoaAcctCd_S1, bizMsg.depcCoaAcctCd_S2));
        params.put("existCoaFlg", NLEL0060CommonLogic.existCoaFlg(bizMsg));
        // END 2018/04/16 J.Kim [QC#22807,ADD]
        // START 2018/06/20 J.Kim [QC#24936,ADD]
        params.put("procMode11", PROC_MODE_11);
        params.put("assetStsRe",  ASSET_STS.RETIRE);
        params.put("locationSold", SOLD);
        // END 2018/06/20 J.Kim [QC#24936,ADD]
        // START 2018/07/24 K.Kojima [QC#27233,ADD]
        params.put("dsAssetMstrPkList", NLEL0060CommonLogic.getDsAssetMstrPkList(glblMsg));
        // END 2018/07/24 K.Kojima [QC#27233,ADD]

        return getSsmEZDClient().queryEZDMsgArray("searchAssignment", params, glblMsg.B);
    }

    /**
     * Search Financial
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchFinancial(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("bizMsg", bizMsg);
        // START 2016/06/30 J.Kim [QC#10864,ADD]
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        // END 2016/06/30 J.Kim [QC#10864,ADD]
        // START 2016/07/21 Y.Tsuchimoto [QC#11019,ADD]
        params.put("deprnTrx", TRX.DEPRECIATION);
        params.put("deprnTrxRsn", TRX_RSN.DEPRECIATION);
        String salesDate = ZYPDateUtil.getSalesDate();
        params.put("slsDtYyyy", salesDate.substring(0, YYYY_LENGTH));
        params.put("percent", "%");
        // END 2016/07/21 Y.Tsuchimoto [QC#11019,ADD]
        params.put("rowNum", glblMsg.C.length() + 1);

        // START 2017/05/15 M.Naito
        params.put("svcMachStsCRAT", SVC_MACH_MSTR_STS.CREATED);
        params.put("svcMachStsRMV", SVC_MACH_MSTR_STS.REMOVED);
        // END 2017/05/15 M.Naito
        params.put("dsCondConstGrpId", "NLEL0060_ASSET_STS");
        // START 2018/04/16 J.Kim [QC#22807,ADD]
        params.put("procMode", PROC_MODE_51);
        // QC#25162
        params.put("depcCoaAcctCdFlg", NLEL0060CommonLogic.existCoaFlg(bizMsg.depcCoaAcctCd_S1, bizMsg.depcCoaAcctCd_S2));
        params.put("existCoaFlg", NLEL0060CommonLogic.existCoaFlg(bizMsg));
        // END 2018/04/16 J.Kim [QC#22807,ADD]
        // START 2018/05/07 J.Kim [QC#24758,ADD]
        params.put("assetStsRe", ASSET_STS.RETIRE);
        // END 2018/05/07 J.Kim [QC#24758,ADD]
        // START 2018/07/24 K.Kojima [QC#27233,ADD]
        params.put("dsAssetMstrPkList", NLEL0060CommonLogic.getDsAssetMstrPkList(glblMsg));
        // END 2018/07/24 K.Kojima [QC#27233,ADD]

        return getSsmEZDClient().queryEZDMsgArray("searchFinancial", params, glblMsg.C);
    }

    /**
     * Search Transaction
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchTransaction(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("bizMsg", bizMsg);
        // START 2016/06/03 T.Tsuchida [QC#9472,MOD]
        params.put("trxCdIsDep", TRX.DEPRECIATION);
        params.put("trxRsnCdIsDep", TRX_RSN.DEPRECIATION);
        // END 2016/06/03 T.Tsuchida [QC#9472,MOD]
        // START 2016/06/09 T.Tsuchida [QC#9663,MOD]
        params.put("trxCdIsRentalShip", TRX.RENTAL_SHIPMENT);
        params.put("trxRsnCdIsRentalAssetImport", TRX_RSN.RENTAL_ASSET_IMPORT);
        params.put("trxRsnCdIsRentalManualAsset", TRX_RSN.RENTAL_MANUAL_ASSET_CREATION);
        params.put("trxCdIsEMSDShip", TRX.EMSD_SHIPMENT);
        params.put("trxRsnCdIsEMSDAssetImport", TRX_RSN.EMSD_ASSET_IMPORT);
        params.put("trxRsnCdIsEMSDManualAsset", TRX_RSN.EMSD_MANUAL_ASSET_CREATION);
        // END 2016/06/09 T.Tsuchida [QC#9663,MOD]
        // START 2016/10/20 T.Tsuchida [QC#11899,ADD]
        params.put("trxCdIsAdjustment", TRX.ADJUSTMENT);
        params.put("trxRsnCdIsAssetInitialCostChange", TRX_RSN.ASSET_INITIAL_COST_CHANGE);
        // END 2016/10/20 T.Tsuchida [QC#11899,ADD]
        params.put("rowNum", glblMsg.D.length() + 1);

        // START 2017/05/15 M.Naito
        params.put("svcMachStsCRAT", SVC_MACH_MSTR_STS.CREATED);
        params.put("svcMachStsRMV", SVC_MACH_MSTR_STS.REMOVED);
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        // END 2017/05/15 M.Naito
        // QC#22750
        params.put("ajeIntfcTpAssetTrx", AJE_INTFC_TP.ASSET_TRANSACTION);
        params.put("dsCondConstGrpId", "NLEL0060_ASSET_STS");
        // START 2018/04/16 J.Kim [QC#22807,ADD]
        params.put("procMode", PROC_MODE_51);
        // QC#25162
        params.put("depcCoaAcctCdFlg", NLEL0060CommonLogic.existCoaFlg(bizMsg.depcCoaAcctCd_S1, bizMsg.depcCoaAcctCd_S2));
        params.put("existCoaFlg", NLEL0060CommonLogic.existCoaFlg(bizMsg));
        // END 2018/04/16 J.Kim [QC#22807,ADD]
        // START 2018/07/24 K.Kojima [QC#27233,ADD]
        params.put("sellToCustSearchFlag", NLEL0060CommonLogic.getSellToCustSearchFlag(bizMsg));
        // END 2018/07/24 K.Kojima [QC#27233,ADD]

        return getSsmEZDClient().queryEZDMsgArray("searchTransaction", params, glblMsg.D);
    }

    /**
     * Search Dep Sim
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchDepSim(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("bizMsg", bizMsg);
        params.put("rowNum", glblMsg.E.length() + 1);
        // START 2018/02/02 J.Kim [QC#22267,ADD]
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("svcMachStsCRAT", SVC_MACH_MSTR_STS.CREATED);
        params.put("svcMachStsRMV", SVC_MACH_MSTR_STS.REMOVED);
        params.put("dsCondConstGrpId", "NLEL0060_ASSET_STS");
        // END 2018/02/02 J.Kim [QC#22267,ADD]
        // START 2018/04/16 J.Kim [QC#22807,ADD]
        params.put("procMode", PROC_MODE_51);
        // QC#25162
        params.put("depcCoaAcctCdFlg", NLEL0060CommonLogic.existCoaFlg(bizMsg.depcCoaAcctCd_S1, bizMsg.depcCoaAcctCd_S2));
        params.put("existCoaFlg", NLEL0060CommonLogic.existCoaFlg(bizMsg));
        // END 2018/04/16 J.Kim [QC#22807,ADD]

        return getSsmEZDClient().queryEZDMsgArray("searchDepSim", params, glblMsg.E);
    }

    // START 2016/09/27 J.Kim [QC#13372,ADD]
    /**
     * Search Asset History
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchAssetHistory(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("bizMsg", bizMsg);
        params.put("rowNum", glblMsg.F.length() + 1);
        // START 2016/12/16 E.Kameishi [QC#15901,ADD]
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        // END 2016/12/16 E.Kameishi [QC#15901,ADD]
        params.put("dsCondConstGrpId", "NLEL0060_HIST_STS");
        // START 2018/04/16 J.Kim [QC#22807,ADD]
        params.put("procMode", PROC_MODE_51);
        // QC#25162
        params.put("depcCoaAcctCdFlg", NLEL0060CommonLogic.existCoaFlg(bizMsg.depcCoaAcctCd_S1, bizMsg.depcCoaAcctCd_S2));
        params.put("existCoaFlg", NLEL0060CommonLogic.existCoaFlg(bizMsg));
        // END 2018/04/16 J.Kim [QC#22807,ADD]

        return getSsmEZDClient().queryEZDMsgArray("searchAssetHistory", params, glblMsg.F);
    }
    // END 2016/09/27 J.Kim [QC#13372,ADD]

    /**
     * Get DepcSmltnRqstDtTmTs
     * @param assetTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDepcSmltnRqstDtTmTs(String assetTpCd) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", getGlobalCompanyCode());
        paramMap.put("assetTpCd", assetTpCd);

        return getSsmEZDClient().queryObject("getDepcSmltnRqstDtTmTs", paramMap);
    }

    // START 2016/04/15 T.Tsuchida [QC#7050,MOD]
//    /**
//     * Get Asset For Depreciation
//     * @param assetTpCd String
//     * @return ssmResult
//     */
//    public Map<String, Object> getAssetForDepriciation(String assetTpCd) {
//        Map<String, Object> map = null;
//
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("glblCmpyCd", getGlobalCompanyCode());
//        paramMap.put("assetTpCd", assetTpCd);
//        paramMap.put("assetStsCd", ASSET_STS.PENDING);
//
//        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getAssetForDepriciation", paramMap);
//        if (!ssmResult.isCodeNotFound()) {
//            map = (Map<String, Object>) ssmResult.getResultObject();
//        }
//
//        return map;
//    }

    /**
     * Get Asset For Depreciation
     * @param assetTpCd String
     * @return ssmResult
     */
    public List<Map<String, Object>> getAssetForDepriciation(String assetTpCd) {

        List<Map<String, Object>> list = null;

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", getGlobalCompanyCode());
        paramMap.put("assetTpCd", assetTpCd);
        paramMap.put("assetStsCd", ASSET_STS.PENDING);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObjectList("getAssetForDepriciation", paramMap);
        if (!ssmResult.isCodeNotFound()) {
            list = (List<Map<String, Object>>) ssmResult.getResultObject();
        }

        return list;
    }

    // END 2016/04/15 T.Tsuchida [QC#7050,MOD]

    // START 2016/06/03 T.Tsuchida [QC#8981,MOD]
    /**
     * Get Warehouse Name
     * @param rtnWhCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWhNm(String rtnWhCd) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", getGlobalCompanyCode());
        paramMap.put("rtnWhCd", rtnWhCd);

        return getSsmEZDClient().queryObject("getWhNm", paramMap);
    }

    // END 2016/06/03 T.Tsuchida [QC#8981,MOD]

    /**
     * Check if COA_MDSE_TP_CD exists
     * @param coaProjcd String
     * @param mdseTpCtxTpCd String
     * @return boolean
     */
    public boolean existCoaMdseTpCd(String coaProjcd, String mdseTpCtxTpCd) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", getGlobalCompanyCode());
        paramMap.put("coaProjCd", coaProjcd);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("existCoaMdseTpCd", paramMap);
        if (ssmResult.isCodeNormal()) {
            Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
            if (map != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if RTRN_WH_CD exists
     * @param rtrnWhCd String
     * @return boolean
     */
    public boolean existRtrnWhCd(String rtrnWhCd) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", getGlobalCompanyCode());
        paramMap.put("rtrnWhCd", rtrnWhCd);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("existRtrnWhCd", paramMap);
        if (ssmResult.isCodeNormal()) {
            Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
            if (map != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get Customer Number
     * @param dsAcctNm String
     * @return String
     */
    public String getDsAcctNum(String dsAcctNm) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", getGlobalCompanyCode());
        paramMap.put("dsAcctNm", dsAcctNm);
        paramMap.put("rgtnStsCd_P99", RGTN_STS.TERMINATED);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObjectList("getDsAcctNum", paramMap);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (list != null) {
                return (String) list.get(0).get("SELL_TO_CUST_CD");
            }
        }
        return null;
    }

    /**
     * Check if Parent Asset has same Book Type
     * @param prntDsAssetMstrPk BigDecimal
     * @param assetTpNm String
     * @return boolean
     */
    public boolean isSameBookType(BigDecimal prntDsAssetMstrPk, String assetTpNm) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", getGlobalCompanyCode());
        paramMap.put("dsAssetMstrPk", prntDsAssetMstrPk);
        // START 2016/10/24 J.Kim [QC#11026,DEL]
        // paramMap.put("procModeCd", procModeCd);
        // END 2016/10/24 J.Kim [QC#11026,DEL]
        paramMap.put("assetTpNm", assetTpNm);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("isSameBookType", paramMap);
        if (ssmResult.isCodeNormal()) {
            Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
            if (map != null) {
                return true;
            }
        }

        return false;
    }

    /**
     * getAssetHistColMapFindByCondition
     * @param glblCmpyCd String
     * @return DS_ASSET_HIST_COL_MAPTMsgArray
     */
    public DS_ASSET_HIST_COL_MAPTMsgArray getAssetHistColMapFindByCondition(String glblCmpyCd) {
        DS_ASSET_HIST_COL_MAPTMsg dsAssetHistColMapTMsg = new DS_ASSET_HIST_COL_MAPTMsg();
        dsAssetHistColMapTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsAssetHistColMapTMsg.setConditionValue("enblFlg01", ZYPConstant.FLG_ON_Y);
        dsAssetHistColMapTMsg.setSQLID("001");
        DS_ASSET_HIST_COL_MAPTMsgArray outTMsgArray = (DS_ASSET_HIST_COL_MAPTMsgArray) EZDTBLAccessor.findByCondition(dsAssetHistColMapTMsg);
        if (outTMsgArray.length() == 0) {
            return null;
        }
        return outTMsgArray;
    }

    // START 2016/10/26 J.Kim [QC#17589,ADD]
    /**
     * getAssetDepreciation
     * @param glblCmpyCd String
     * @param mapList Map<String, Object>
     * @return List<BigDecimal>
     */
    public List<Map<String, Object>> getAssetDepreciation(String glblCmpyCd, Map<String, Object> mapList) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("prntDsAssetMstrPk", mapList.get(COL_DS_ASSET_MSTR_PK));
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getAssetMstrInfo", queryParam).getResultObject();
    }

    // END 2016/10/26 J.Kim [QC#17589,ADD]

    // START 2017/05/15 M.Naito
    /**
     * getCpoLineNum
     * @param glblCmpyCd String
     * @param cpoDtlTMsg CPO_DTLTMsg
     * @return List<BigDecimal>
     */
    public List<Map<String, Object>> getCpoLineNum(String glblCmpyCd, CPO_DTLTMsg cpoDtlTMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("cpoOrdNum", cpoDtlTMsg.cpoOrdNum.getValue());
        if (ZYPCommonFunc.hasValue(cpoDtlTMsg.dsOrdPosnNum.getValue())) {
            queryParam.put("dsOrdPosnNum", cpoDtlTMsg.dsOrdPosnNum.getValue());
        }
        if (ZYPCommonFunc.hasValue(cpoDtlTMsg.dsCpoLineNum.getValue())) {
            queryParam.put("dsCpoLineNum", cpoDtlTMsg.dsCpoLineNum.getValue());
        }
        if (ZYPCommonFunc.hasValue(cpoDtlTMsg.dsCpoLineSubNum.getValue())) {
            queryParam.put("dsCpoLineSubNum", cpoDtlTMsg.dsCpoLineSubNum.getValue());
        }
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getCpoLineNum", queryParam).getResultObject();
    }
    // END 2017/05/15 M.Naito

    // START 2016/11/01 J.Kim [QC#16345,ADD]
    public Map<String, String> getDefaultCoaMapList(String glblCmpyCd, NLEL0060_BSMsg sMsg) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", glblCmpyCd);
        map.put("dsAssetMstrpk", sMsg.prntDsAssetMstrPk_B1.getValue());
        map.put("assetTpCd", sMsg.assetTpCd_B1.getValue());
        map.put("flgY", ZYPConstant.FLG_ON_Y);
        map.put("slsDt", ZYPDateUtil.getSalesDate());
        map.put("newcore", ZYPCodeDataUtil.getVarCharConstValue("AJE_NEW_CORE_CD", glblCmpyCd));
        map.put("defBrNewcore", ZYPCodeDataUtil.getVarCharConstValue("AJE_DEF_COA_BR_NONCORE", glblCmpyCd));
        map.put("defCcNewcore", ZYPCodeDataUtil.getVarCharConstValue("AJE_DEF_COA_CC_NONCORE", glblCmpyCd));

        Map<String, String> defaultCoa = (Map<String, String>) getSsmEZDClient().queryObject("getDefaultCoaList", map).getResultObject();
        return defaultCoa;
    }
    // END 2016/11/01 J.Kim [QC#16345,ADD]

    // START 2018/05/17 J.Kim [QC#25879,ADD]
    /**
     * getSalesRep
     * @param glblCmpyCd String
     * @param slsRepTocCd String
     * @return Map<String, String>
     */
    public Map<String, String> getSalesRep(String glblCmpyCd, String slsRepTocCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("slsRepTocCd", slsRepTocCd);
        queryParam.put("slsDt", ZYPDateUtil.getSalesDate());
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getSalesRep", queryParam);
        if (ssmResult.isCodeNormal()) {
            Map<String, String> saleRepInfo = (Map<String, String>) ssmResult.getResultObject();
            if (saleRepInfo != null) {
                return saleRepInfo;
            }
        }

        return null;
    }
    // END 2018/05/17 J.Kim [QC#25879,ADD]

    // START 2018/07/31 J.Kim [QC#27021,ADD]
    /**
     * getPrntDsAssetMstrPk
     * @param dsAssetMstrPk BigDecimal
     * @return DS_ASSET_MSTRTMsg
     */
    public DS_ASSET_MSTRTMsg getPrntDsAssetMstrPk(BigDecimal dsAssetMstrPk) {

        DS_ASSET_MSTRTMsg inTMsg = new DS_ASSET_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inTMsg.dsAssetMstrPk, dsAssetMstrPk);
        DS_ASSET_MSTRTMsg outTMsg = (DS_ASSET_MSTRTMsg) S21FastTBLAccessor.findByKey(inTMsg);
        return outTMsg;
    }
    // END 2018/07/31 J.Kim [QC#27021,ADD]

    // START 2018/08/01 J.Kim [QC#26901,ADD]
    /**
     * setValueChangeReason
     * @param dsAssetMstrPk BigDecimal
     * @return AMT_CHNG_RSN_TPTMsg
     */
    public AMT_CHNG_RSN_TPTMsg setValueChangeReason(String amtChngRsnTpCd) {

        AMT_CHNG_RSN_TPTMsg inTMsg = new AMT_CHNG_RSN_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inTMsg.amtChngRsnTpCd, amtChngRsnTpCd);
        AMT_CHNG_RSN_TPTMsg outTMsg = (AMT_CHNG_RSN_TPTMsg) S21FastTBLAccessor.findByKey(inTMsg);
        return outTMsg;
    }
    // END 2018/08/01 J.Kim [QC#26901,ADD]
}
