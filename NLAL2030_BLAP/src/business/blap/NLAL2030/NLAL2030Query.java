/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL2030;

import static business.blap.NLAL2030.constant.NLAL2030Constant.SEARCH_RWS_TP_CPO;
import static business.blap.NLAL2030.constant.NLAL2030Constant.SEARCH_RWS_TP_KC;
import static business.blap.NLAL2030.constant.NLAL2030Constant.SEARCH_RWS_TP_KT;
import static business.blap.NLAL2030.constant.NLAL2030Constant.SEARCH_RWS_TP_KU;
import static business.blap.NLAL2030.constant.NLAL2030Constant.SEARCH_RWS_TP_PO;
import static business.blap.NLAL2030.constant.NLAL2030Constant.SEARCH_RWS_TP_PR;
import static business.blap.NLAL2030.constant.NLAL2030Constant.SEARCH_RWS_TP_RMN;
import static business.blap.NLAL2030.constant.NLAL2030Constant.SEARCH_RWS_TP_SO;
import static business.blap.NLAL2030.constant.NLAL2030Constant.SEARCH_RWS_TP_UC;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import business.blap.NLAL2030.constant.NLAL2030Constant;

import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_WRK_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WRK_ORD_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NLAL2030Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         Y.Taoka         Create          N/A
 * 06/03/2016   CSAI            Y.Imazu         Update          QC#8663
 * 06/06/2016   CSAI            Y.Imazu         Update          QC#8475
 * 06/08/2017   CITS            M.Naito         Update          QC#18382
 * 06/20/2017   CITS            M.Naito         Update          QC#19269
 * 08/10/2017   CITS            S.Katsuma       Update          QC#19232
 * 09/21/2017   CITS            S.Endo          Update          Sol#069(QC#18270)
 * 01/15/2018   CITS            K.Ogino         Update          QC#23438
 * 02/21/2018   CITS            K.Ogino         Update          QC#24070
 * 02/25/2019   CITS            K.Ogino         Update          QC#30456
 * 12/14/2020   CITS            A.Marte         Update          QC#58069
 * 03/22/2021   CITS            A.Marte         Update          QC#57124
 * 11/11/2021   CITS            A.Marte         Update          QC$59350
 * 10/26/2022   Hitachi         M.Kikushima     Update          QC#60413
 * 11/23/2022   CITS            R.Azucena       Update          QC#60835
 * 03/02/2023   CITS            F.Fadriquela    Update          QC#61225
 *</pre>
 */
public final class NLAL2030Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NLAL2030Query MY_INSTANCE = new NLAL2030Query();

    /**
     * Private constructor
     */
    private NLAL2030Query() {
        super();
    }

    /**
     * Get the NLAL2030Query instance.
     * @return NLAL2030Query instance
     */
    public static NLAL2030Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getSavedSearchOptionList
     * @param glblCmpyCd String
     * @param usrId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String glblCmpyCd, String usrId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("srchOptAplId", NLAL2030Constant.BIZ_ID);
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }

    /**
     * Search
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @param searchMode int
     * @param querySsmId String
     * @param sceOrdTpList List<String>
     * @param ordNumList List<String>
     * @param ordNumLineNumList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchOrd(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg, int searchMode, String querySsmId, List<String> sceOrdTpList, List<String> ordNumList, List<String> ordNumLineNumList) {

        Map<String, Object> params = createParamOrd(bizMsg, searchMode, sceOrdTpList, ordNumList, ordNumLineNumList);
        params.put("rowNum", glblMsg.A.length() + 1);

        return getSsmEZDClient().queryObjectList(querySsmId, params);
    }

    /**
     * Search
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @param searchMode int
     * @param querySsmId String
     * @param sceOrdTpList List<String>
     * @param ordNumList List<String>
     * @param ordNumLineNumList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchOrd(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg, List<String> sceOrdTpList) {

        Map<String, Object> params = createParamOrd(bizMsg, 0, sceOrdTpList, null, null);
        params.put("rowNum", glblMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("searchOrd", params, glblMsg.A);
    }

    /**
     * createParamHeader
     * @param bizMsg NLAL2030CMsg
     * @param params Map<String, Object>
     * @param sceOrdTpList List<String>
     * @return Map<String, Object>
     */
    private static Map<String, Object> createParamHeader(NLAL2030CMsg bizMsg, Map<String, Object> params, List<String> sceOrdTpList) {

        params.put("trxOrdNum", bizMsg.trxOrdNum.getValue());
        params.put("rwsRefNum", bizMsg.rwsRefNum.getValue());
        params.put("imptInvBolNum", bizMsg.imptInvBolNum.getValue());
        params.put("mdseCd", bizMsg.mdseCd.getValue());
        params.put("flipMdseCd", bizMsg.flipMdseCd.getValue());
        params.put("rwsNum", bizMsg.rwsNum.getValue());
        params.put("rwsStsCd", bizMsg.rwsStsCd.getValue());
        params.put("rtlWhCd", bizMsg.rtlWhCd.getValue());
        params.put("rtlSwhCd", bizMsg.rtlSwhCd.getValue());
        params.put("whCd", bizMsg.whCd.getValue());
        params.put("whInEtaDt_FR", bizMsg.whInEtaDt_FR.getValue());
        params.put("whInEtaDt_TO", bizMsg.whInEtaDt_TO.getValue());
        params.put("fromLocCd", bizMsg.fromLocCd.getValue());
        params.put("carrCd", bizMsg.carrCd.getValue());
        params.put("serNum", bizMsg.serNum.getValue());
        params.put("svcConfigMstrPk", bizMsg.svcConfigMstrPk.getValue());
        params.put("sceOrdTpCdTr", SCE_ORD_TP.TECH_REQUEST);
        params.put("sceOrdTpCdDt", SCE_ORD_TP.DC_TRANSFER);

        if (sceOrdTpList != null && !sceOrdTpList.isEmpty()) {

            params.put("sceOrdTpList", sceOrdTpList);
        }

        if (chkDtlDrch(bizMsg)) {

            params.put("dtlSrchFlg", ZYPConstant.FLG_ON_Y);

        } else {

            params.put("dtlSrchFlg", ZYPConstant.FLG_OFF_N);
        }

        return params;
    }

    /**
     * chkDtlDrch
     * @param bizMsg NLAL2030CMsg
     * @return boolean
     */
    private static boolean chkDtlDrch(NLAL2030CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.mdseCd)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(bizMsg.whInEtaDt_FR)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(bizMsg.whInEtaDt_TO)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(bizMsg.rtlWhCd)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(bizMsg.rtlSwhCd)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(bizMsg.carrCd)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(bizMsg.flipMdseCd)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(bizMsg.whCd)) {

            return true;
        }

        return false;
    }

    /**
     * createParamOrd
     * @param bizMsg NLAL2030CMsg
     * @param searchMode int
     * @param sceOrdTpList List<String>
     * @param ordNumList List<String>
     * @param ordNumLineNumList List<String>
     * @return Map<String, Object>
     */
    private static Map<String, Object> createParamOrd(NLAL2030CMsg bizMsg, int searchMode, List<String> sceOrdTpList, List<String> ordNumList, List<String> ordNumLineNumList) {

        Map<String, Object> params = createCommonParamNew(bizMsg);

        if (searchMode == NLAL2030Constant.SEARCH_MODE_RESEARCH) {

            params.put("trxOrdNumList", ordNumList);
            params.put("trxOrdNumLineNumList", ordNumLineNumList);

        } else {

            createParamHeader(bizMsg, params, sceOrdTpList);
        }

        return params;
    }

    /**
     * createCommonParam
     * @param bizMsg NLAL2030CMsg
     * @return Map<String, Object>
     */
    private static Map<String, Object> createCommonParam(NLAL2030CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("inbdOtbdCd", INBD_OTBD.INBOUND);
        params.put("bizId", NLAL2030Constant.BIZ_ID);
        params.put("wrkOrdStsCd", WRK_ORD_STS.CANCELLED);
        params.put("sceOrdTpCdDt", SCE_ORD_TP.DC_TRANSFER);
        params.put("sceOrdTpCdKt", SCE_ORD_TP.KITTING);
        params.put("sceOrdTpCdKu", SCE_ORD_TP.UN_KITTING);
        params.put("sceOrdTpCdKc", SCE_ORD_TP.KITTING_CANCEL);
        params.put("dsWrkOrdTpCdKit", DS_WRK_ORD_TP.KIT);
        params.put("dsWrkOrdTpCdUnKit", DS_WRK_ORD_TP.UN_KIT);
        params.put("NLAL2030_RWS_CTRL", "NLAL2030_RWS_CTRL");
        // QC#19269
        params.put("dplyTpPo", SEARCH_RWS_TP_PO);
        params.put("dplyTpCpo", SEARCH_RWS_TP_CPO);
        params.put("dplyTpDt", SEARCH_RWS_TP_SO);
        params.put("dplyTpPr", SEARCH_RWS_TP_PR);
        params.put("dplyTpKt", SEARCH_RWS_TP_KT);
        params.put("dplyTpKu", SEARCH_RWS_TP_KU);
        params.put("dplyTpUc", SEARCH_RWS_TP_UC);
        params.put("dplyTpKc", SEARCH_RWS_TP_KC);
        params.put("dplyTpRu", SEARCH_RWS_TP_RMN);
        params.put("wrkOrdStsAllc", WRK_ORD_STS.HARD_ALLOCATED);
        params.put("wrkOrdStsValid", WRK_ORD_STS.VALIDATED);
        params.put("wrkOrdStsShip", WRK_ORD_STS.SHIPPED);
        params.put("wrkOrdStsRcv", WRK_ORD_STS.RECEIVING);
        params.put("wrkOrdStsRcvComp", WRK_ORD_STS.RECEIVING_COMPLETION);
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("flgN", ZYPConstant.FLG_OFF_N);

        return params;
    }

    /**
     * createCommonParam
     * @param bizMsg NLAL2030CMsg
     * @return Map<String, Object>
     */
    private static Map<String, Object> createCommonParamNew(NLAL2030CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("flgN", ZYPConstant.FLG_OFF_N);
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsWrkOrdTpCdKit", DS_WRK_ORD_TP.KIT);
        params.put("sceOrdTpCdKc", SCE_ORD_TP.KITTING_CANCEL);
        params.put("wrkOrdStsAllc", WRK_ORD_STS.HARD_ALLOCATED);
        params.put("wrkOrdStsValid", WRK_ORD_STS.VALIDATED);
        params.put("wrkOrdStsShip", WRK_ORD_STS.SHIPPED);
        params.put("wrkOrdStsRcv", WRK_ORD_STS.RECEIVING);
        params.put("wrkOrdStsRcvComp", WRK_ORD_STS.RECEIVING_COMPLETION);
        params.put("inbdOtbdCd", INBD_OTBD.INBOUND);
        params.put("NLAL2030_RWS_CTRL", "NLAL2030_RWS_CTRL");

        return params;
    }

    /**
     * Search
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @param searchMode int
     * @param searchRwsTp String
     * @param sceOrdTpList List<String>
     * @param rwsNumList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchRws(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg, int searchMode, String searchRwsTp, List<String> sceOrdTpList, List<String> rwsNumList) {

        Map<String, Object> params = createParamRws(bizMsg, glblMsg, searchMode, searchRwsTp, sceOrdTpList, rwsNumList);
        params.put("rowNum", glblMsg.B.length() + 1);

        return getSsmEZDClient().queryObjectList("searchRws", params);
    }

    /**
     * createParamRws
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @param searchMode int
     * @param searchRwsTp String
     * @param sceOrdTpList List<String>
     * @param rwsNumList List<String>
     * @return Map<String, Object>
     */
    public static Map<String, Object> createParamRws(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg, int searchMode, String searchRwsTp, List<String> sceOrdTpList, List<String> rwsNumList) {

        Map<String, Object> params = createCommonParam(bizMsg);
        params.put("searchRwsTp", searchRwsTp);

        // For Re-Search RWS Number
        if (searchMode == NLAL2030Constant.SEARCH_MODE_RESEARCH) {

            params.put("rwsNumList", rwsNumList);

        // For moving Tab
        } else if (searchMode == NLAL2030Constant.SEARCH_MODE_TAB) {

            HashSet<String> set = new HashSet<String>();

            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

                if (ZYPConstant.CHKBOX_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A1.getValue())) {

                    set.add(glblMsg.A.no(i).trxOrdNum_A1.getValue());
                }
            }

            List<String> trxOrdNumList = new  ArrayList<String>(set);
            params.put("trxOrdNumList", trxOrdNumList);

        // Search
        } else {

            createParamHeader(bizMsg, params, sceOrdTpList);
        }

        return params;
    }

    /**
     * getSerNumListForOrd
     * @param glblCmpyCd String
     * @param ordNum String
     * @param lineNum String
     * @param lineSubNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSerNumListForOrd(String glblCmpyCd, String ordNum, String lineNum, String lineSubNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("trxOrdNum", ordNum);
        params.put("trxLineNum", lineNum);
        // QC#24413
        params.put("partially", RWS_STS.RECEIVING);

        if (ZYPCommonFunc.hasValue(lineSubNum)) {

            params.put("trxLineSubNum", lineSubNum);
        }

        return getSsmEZDClient().queryObjectList("getSerNumListForOrd", params);
    }



    /**
     * getSerNumListForRws
     * @param ssmId String
     * @param glblCmpyCd String
     * @param rwsNum String
     * @param rwsLineNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSerNumListForRws(String ssmId, String glblCmpyCd, String rwsNum, String rwsLineNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);
        params.put("rwsLineNum", rwsLineNum);

        return getSsmEZDClient().queryObjectList(ssmId, params);
    }

    /**
     * getPoRcvRws
     * @param glblCmpyCd String
     * @param poRcvNum String
     * @param rwsNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoRcvRws(String glblCmpyCd, String poRcvNum, String rwsNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("poRcvNum", poRcvNum);
        params.put("rwsNum", rwsNum);

        return getSsmEZDClient().queryObjectList("getPoRcvRws", params);
    }

    /**
     * checkShpgStsForRP
     * @param glblCmpyCd String
     * @param lineMsg NLAL2030_BSMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkShpgStsForRP(String glblCmpyCd, NLAL2030_BSMsg lineMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("poOrdNum", lineMsg.poOrdNum_B1.getValue());
        params.put("rwsNum", lineMsg.rwsNum_B1.getValue());
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("flgN", ZYPConstant.FLG_OFF_N);

        return getSsmEZDClient().queryObject("checkShpgStsForRP", params);
    }

    /**
     * getParty
     * @param params Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getParty(Map<String, Object> params) {
        return getSsmEZDClient().queryObject("getPartyNm", params);
    }

    /**
     * getSceOrdTpList
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSceOrdTpList(String glblCmpyCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("inbdOtbdCd", INBD_OTBD.INBOUND);
        params.put("bizId", NLAL2030Constant.BIZ_ID);
        params.put("dsCondConstGrpId", "NLAL2030_RWS_CTRL");
        return getSsmEZDClient().queryObjectList("getSceOrdTpList", params);
    }

    /**
     * getSsmParamDownLoadOrd
     * @param bizMsg NLAL2030CMsg
     * @param rowNum int
     * @param sceOrdTpList List<String>
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParamDownLoadOrd(NLAL2030CMsg bizMsg, int rowNum, List<String> sceOrdTpList) {

        Map<String, Object> ssmParam = createParamOrd(bizMsg, NLAL2030Constant.SEARCH_MODE_HDR, sceOrdTpList, null, null);
        ssmParam.put("rowNum", rowNum);
        return ssmParam;
    }

    /**
     * getSsmParamDownLoadRws
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @param rowNum int
     * @param searchRwsTp String
     * @param sceOrdTpList List<String>
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParamDownLoadRws(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg, int rowNum, String searchRwsTp, List<String> sceOrdTpList) {

        Map<String, Object> ssmParam = createParamRws(bizMsg, glblMsg, NLAL2030Constant.SEARCH_MODE_HDR, searchRwsTp, sceOrdTpList, null);
        ssmParam.put("rowNum", rowNum);
        return ssmParam;
    }

    /**
     * getSsmIdBySrcOrd
     * @param glblCmpyCd String
     * @param trxOrdNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSsmIdBySrcOrd(String glblCmpyCd, String trxOrdNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("trxOrdNum", trxOrdNum);
        return getSsmEZDClient().queryObjectList("getSsmIdBySrcOrd", params);
    }

    /**
     * getSceOrdTpListBefMarg
     * @param glblCmpyCd String
     * @param sceOrdTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSceOrdTpListBefMarg(String glblCmpyCd, String sceOrdTpCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("sceOrdTpCd", sceOrdTpCd);
        params.put("dsCondConstGrpId", "NLAL2030_RWS_CTRL");
        params.put("inbdOtbdCd", INBD_OTBD.INBOUND);
        params.put("bizId", NLAL2030Constant.BIZ_ID);
        return getSsmEZDClient().queryObjectList("getSceOrdTpListBefMarg", params);
    }

    /**
     * getRwsSceOrdTp
     * @param bizMsg NLAL2030CMsg
     * @param sceOrdTpList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRwsSceOrdTp(NLAL2030CMsg bizMsg, List<String> sceOrdTpList) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("inbdOtbdCd", INBD_OTBD.INBOUND);
        params.put("bizId", NLAL2030Constant.BIZ_ID);

        createParamHeader(bizMsg, params, sceOrdTpList);

        return getSsmEZDClient().queryObjectList("getRwsSceOrdTp", params);
    }

    /**
     * getDsCondConstForRwsCtrl
     * @param glblCmpyCd String
     * @param sceOrdTpList List<String>
     * @param dplySceOrdTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsCondConstForRwsCtrl(String glblCmpyCd, List<String> sceOrdTpList, String dplySceOrdTpCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsCondConstGrpId", "NLAL2030_RWS_CTRL");
        params.put("sceOrdTpList", sceOrdTpList);
        params.put("dplySceOrdTpCd", dplySceOrdTpCd);
        return getSsmEZDClient().queryObjectList("getDsCondConstForRwsCtrl", params);
    }
    
    /**
     * 
     * @param glblCmpyCd String
     * @param rwsNum String
     * @return
     */
    public S21SsmEZDResult getRwsAllView(String glblCmpyCd, String rwsNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);
        params.put("inbdCd", INBD_OTBD.INBOUND);
        params.put("sceOrdTpDT", SCE_ORD_TP.DC_TRANSFER);
        params.put("sceOrdTpRM", SCE_ORD_TP.REMAN_ITEM_CHANGE);
        params.put("fromLocTpCust", LOC_TP.CUSTOMER);
        params.put("wmsDropStsCd", ZYPConstant.FLG_ON_1);

        return getSsmEZDClient().queryObjectList("getRwsAllView", params);
    }

    /**
     * checkKittingCancel
     * @param glblCmpyCd String
     * @param rwsNum String
     * @return S21SsmEZDResult
     */
    public  S21SsmEZDResult checkKittingCancel(String glblCmpyCd, String rwsNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);
        params.put("ku", NLAL2030Constant.UNKITTING_CANCEL);
        params.put("kc", NLAL2030Constant.DCC_KITTING_CANCEL);
        params.put("uc", NLAL2030Constant.DCC_UNKITTING_CANCEL);

        return getSsmEZDClient().queryObject("checkKittingCancel", params);
    }

    // START 2017/08/10 S.Katsuma [QC#19232,ADD]
    /**
     * checkOrdRwsQty
     * @param glblCmpyCd
     * @param trxOrdNum
     * @param trxLineNum
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkOrdRwsQty(String glblCmpyCd, String trxOrdNum, String trxLineNum, String trxLineSubNum, String searchRwsTp) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("trxOrdNum", trxOrdNum);
        params.put("trxLineNum", trxLineNum);
        params.put("trxLineSubNum", trxLineSubNum);
        params.put("searchRwsTp", searchRwsTp);

        return getSsmEZDClient().queryObject("checkOrdRwsQty", params);
    }
    // END 2017/08/10 S.Katsuma [QC#19232,ADD]

    //2017/09/21 S.Endo Add Sol#069(QC#18270) START
    
    /**
     * getBuyBackFromRtlWh
     * @param glblCmpyCd glblCmpyCd
     * @param rwsNum rwsNum
     * @param rwsDtlLineNum rwsDtlNum
     * @return
     */
    public S21SsmEZDResult getBuyBackFromRtlWh(String glblCmpyCd, String rwsNum, String rwsDtlLineNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);
        params.put("rwsDtlLineNum", rwsDtlLineNum);

        return getSsmEZDClient().queryObject("getBuyBackFromRtlWh", params);
    }

    // START 2023/03/02 F.Fadriquela [QC#61225, DEL]
    /**
     * QC#23438
     * getSvcMachMstrPk
     * @param glblCmpyCd String
     * @param trxHdrNum String
     * @param trxLineNum String
     * @param trxLineSubNum String
     * @return BigDecimal
     */
    /*public BigDecimal getSvcMachMstrPk(String glblCmpyCd, String trxHdrNum, String trxLineNum, String trxLineSubNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("trxHdrNum", trxHdrNum);
        queryParam.put("trxLineNum", trxLineNum);
        queryParam.put("trxLineSubNum", trxLineSubNum);

        return (BigDecimal) getSsmEZDClient().queryObject("getSvcMachMstrPk", queryParam).getResultObject();
    }*/
    // END 2023/03/02 F.Fadriquela [QC#61225, DEL]

    /**
     * QC#23438
     * getSvcMachMstrHist
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @return
     */
    public S21SsmEZDResult getSvcMachMstrHist(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("svcMachMstrPk", svcMachMstrPk);

        return getSsmEZDClient().queryObjectList("getSvcMachMstrHist", queryParam);
    }

    /**
     * getPermissionWhList
     * QC#18461-Sol#085 Add method.
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPermissionWhList(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPermissionWhList", ssmParam);
    }

    /**
     * getSerialFromReturnOrdRws
     * QC#18461-Sol#085 Add method.
     * @param glblCmpyCd String
     * @param rwsNum String
     * @param rwsLineNum String
     * @param mdseCd String
     * @return serNum String
     */
    public String getSerialFromReturnOrdRws(String glblCmpyCd, String rwsNum, String rwsLineNum, String mdseCd) {
        
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("rwsNum", rwsNum);
        queryParam.put("rwsLineNum", rwsLineNum);
        queryParam.put("mdseCd", mdseCd);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("getSerialFromReturnOrdRws", queryParam);

        return (String) result.getResultObject();
    }
    
    /**
     * getSerialFromReturnOrdRws
     * QC#18461-Sol#085 Add method.
     * @param glblCmpyCd String
     * @param rwsNum String
     * @param rwsLineNum String
     * @param mdseCd String
     * @return serNum String
     */
    public List<Map<String, Object>> getSerialFromPoRws(String glblCmpyCd, String oldRwsNum, String newRwsNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("oldRwsNum", oldRwsNum);
        queryParam.put("newRwsNum", newRwsNum);

        S21SsmEZDResult result = getSsmEZDClient().queryObjectList("getSerialFromPoRws", queryParam);

        return (List<Map<String, Object>>) result.getResultObject();
    }

    /**
     * getDsCpoRtrnInfo Add QC#24070
     * @param glblCmpyCd String
     * @param rtrnLine NLAL2030_ASMsg
     * @return List<Map<String, Object>>
     */
    public Map<String, Object> getDsCpoRtrnInfo(String glblCmpyCd, NLAL2030_ASMsg rtrnLine) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("cpoOrdNum", rtrnLine.trxOrdNum_A1.getValue());
        queryParam.put("dsCpoRtrnLineNum", rtrnLine.trxLineNum_A1.getValue());
        queryParam.put("dsCpoRtrnLineSubNum", rtrnLine.trxLineSubNum_A1.getValue());
        queryParam.put("dsOrdPosnNum", rtrnLine.dsOrdPosnNum_A1.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("getDsCpoRtrnInfo", queryParam);

        return (Map<String, Object>) result.getResultObject();
    }

    /**
     * Add QC#24413. getOpenRwsQty
     * @param glblCmpyCd String
     * @param ordNum String
     * @param lineNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOpenRwsQty(String glblCmpyCd, String ordNum, String lineNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("trxOrdNum", ordNum);
        params.put("trxLineNum", lineNum);
        params.put("printed", RWS_STS.PRINTED);
        params.put("partially", RWS_STS.RECEIVING);

        return getSsmEZDClient().queryObject("getOpenRwsQty", params);
    }
    // QC#30456
    /**
     * getRwsLineNum Add QC#24070
     * @param glblCmpyCd String
     * @param rtrnLine NLAL2030_ASMsg
     * @return String
     */
    public String getRwsLineNum(String glblCmpyCd, NLAL2030_ASMsg rtrnLine) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("rwsNum", rtrnLine.rwsNum_A1.getValue());
        queryParam.put("dsCpoRtrnLineNum", rtrnLine.trxLineNum_A1.getValue());
        queryParam.put("dsCpoRtrnLineSubNum", rtrnLine.trxLineSubNum_A1.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("getRwsLineNum", queryParam);

        return (String) result.getResultObject();
    }

    public BigDecimal getRwsCnt(String glblCmpyCd, String rwsNum, List<String> rwsDtlLineList) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);
        params.put("rwsDtlLineList",rwsDtlLineList);

        return (BigDecimal) getSsmEZDClient().queryObject("getRwsCnt", params).getResultObject();
    }

    // START 2020/12/14 A.Marte [QC#58069, MOD]
    /**
     * getWmsPdltRecord
     * @param glblCmpyCd String
     * @param ordNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWmsPdltRecord(String glblCmpyCd, String ordNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("inbdOrdNum", ordNum);
        params.put("wmsTaskCd", NLAL2030Constant.WMS_TASK_CD_PO_CANCEL);

        return getSsmEZDClient().queryObjectList("getWmsPdlt", params);
    }
    // END 2020/12/14 A.Marte [QC#58069, MOD]

    // START 2021/03/22 A.Marte [QC#57124, ADD]
    /**
     * getPrchReqRef
     * @param glblCmpyCd String
     * @param rwsNum String
     * @param trxLineNum String
     * @param trxLineSubNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrchReqRef(String glblCmpyCd, String rwsNum, String trxLineNum, String trxLineSubNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);
        params.put("trxLineNum", trxLineNum);
        params.put("trxLineSubNum", trxLineSubNum);

        return getSsmEZDClient().queryObjectList("getPrchReqRef", params);
    }
    // END 2021/03/22 A.Marte [QC#57124, ADD]

    // START 2021/11/11 A.Marte [QC#59350, ADD]
    /**
     * getValidInvtyLocCd
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @param rtlSwhCd String
     * @return String
     */
    public String getValidInvtyLocCd(String glblCmpyCd, String rtlWhCd, String rtlSwhCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rtlWhCd", rtlWhCd);
        params.put("rtlSwhCd", rtlSwhCd);

        return (String) getSsmEZDClient().queryObject("getValidInvtyLocCd", params).getResultObject();
    }
    // END 2021/11/11 A.Marte [QC#59350, ADD]

    // START 2022/10/26 M.Kikushima [QC#60413,ADD]
    /**
     * countValidAPInvoiceForCancel
     * @param glblCmpyCd String
     * @param poNum String
     * @return S21SsmEZDResult
     */
    public BigDecimal countValidAPInvoiceForCancel(String glblCmpyCd, String poNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("poNum", poNum);
        params.put("acctInvStsCd", ACCT_INV_STS.ENTERED);

        return (BigDecimal) getSsmEZDClient().queryObject("countValidAPInvoiceForCancel", params).getResultObject();

    }
    // START 2022/10/26 M.Kikushima [QC#60413,ADD]

    // START 2022/11/23 R.Azucena [QC#60835, ADD]
    /**
     * getExistRwsRefNumList
     * @param glblCmpyCd String
     * @param whCd String
     * @param baseRwsRefNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getExistRwsRefNumList(String glblCmpyCd, String whCd, String baseRwsRefNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("whCd", whCd);
        params.put("rwsRefNum", baseRwsRefNum);

        return getSsmEZDClient().queryObjectList("getExistRwsRefNumList", params);
    }
    // END 2022/11/23 R.Azucena [QC#60835, ADD]
}
