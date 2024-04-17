/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3080;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL3080.common.NLBL3080CommonLogic;
import business.blap.NLBL3080.contant.NLBL3080Constant;
import business.db.INVTYTMsg;
import business.db.INVTYTMsgArray;
import business.db.SHPG_PLNTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   CITS            H.Sugawara      Create          N/A
 * 02/24/2016   CSAI            Y.Imazu         Update          QC#4652
 * 2016/03/22   CITS            T.Tokutomi      Update          QC#4596
 * 2017/01/26   CITS            T.Kikuhara      Update          QC#15650
 * 2017/06/23   CITS            R.Shimamoto     Update          QC#19517
 * 07/26/2017   CITS            S.Endo          Update          Sol#072(QC#18386)
 * 02/05/2018   CITS            K.Ogino         Update          QC#23841
 * 2018/06/26   CITS            K.Ogino         Update          QC#25456
 * 2018/07/23   CITS            K.Ogino         Update          QC#27047
 * 2018/12/21   CITS            T.Tokutomi      Update          QC#29214
 * 2019/03/26   CITS            K.Ogino         Update          QC#30903
 * 2022/08/09   Hitachi         T.NEMA          Update          QC#60319
 *</pre>
 */
public final class NLBL3080Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLBL3080Query MY_INSTANCE = new NLBL3080Query();

    /**
     * Constructor.
     */
    private NLBL3080Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return  NLBL3080Query
     */
    public static NLBL3080Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * search_Ord
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search_Ord(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        Map<String, Object> ssmParam = createSearchCond(cMsg);
        ssmParam.put("rownum", sMsg.A.length() + 1);

        return getSsmEZDClient().queryObjectList("SearchOrd", ssmParam);
    }

    /**
     * search_OrdLine
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search_OrdLine(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        Map<String, Object> ssmParam = createSearchCond(cMsg);
        ssmParam.put("rownum", sMsg.B.length() + 1);
        // START 2022/08/09 T.NEMA [QC#60319, MOD]
        ssmParam.put("mdseItemTp", MDSE_ITEM_TP.SET);
        // END   2022/08/09 T.NEMA [QC#60319, MOD]
        return getSsmEZDClient().queryObjectList("SearchOrdLine", ssmParam);
    }

    /**
     * search_OrdLineResearch
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search_OrdLineResearch(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        createSearchCondInit(cMsg, ssmParam, cMsg.glblCmpyCd.getValue());
        ssmParam.put("rownum", sMsg.B.length() + 1);
        // START 2022/08/09 T.NEMA [QC#60319, MOD]
        ssmParam.put("mdseItemTp", MDSE_ITEM_TP.SET);
        // END   2022/08/09 T.NEMA [QC#60319, MOD]
        ArrayList<String> cpoOrdNumList = new ArrayList<String>();
        ArrayList<String> dsOrdPosnNumList = new ArrayList<String>();

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            cpoOrdNumList.add(sMsg.B.no(i).cpoOrdNum_B1.getValue());
            // QC#23841
            dsOrdPosnNumList.add(sMsg.B.no(i).cpoOrdNum_B1.getValue() + sMsg.B.no(i).cpoDtlLineNum_B1.getValue() + sMsg.B.no(i).dsOrdPosnNum_B1.getValue());
        }

        ssmParam.put("cpoOrdNumList", cpoOrdNumList);
        ssmParam.put("dsOrdPosnNumList", dsOrdPosnNumList);

        return getSsmEZDClient().queryObjectList("SearchOrdLine", ssmParam);
    }

    /**
     * search_OrdLineFromTab
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search_OrdLineTab(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        createSearchCondInit(cMsg, ssmParam, cMsg.glblCmpyCd.getValue());
        ssmParam.put("bothFlag", ZYPConstant.FLG_ON_Y);
        ssmParam.put("rownum", sMsg.B.length() + 1);
        // START 2022/08/09 T.NEMA [QC#60319, MOD]
        ssmParam.put("mdseItemTp", MDSE_ITEM_TP.SET);
        // END   2022/08/09 T.NEMA [QC#60319, MOD]
        ArrayList<String> cpoOrdNumList = new ArrayList<String>();
        ArrayList<String> dsOrdPosnNumList = new ArrayList<String>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A2.getValue())) {

                dsOrdPosnNumList.add(sMsg.A.no(i).cpoOrdNum_A1.getValue() + sMsg.A.no(i).dsOrdPosnNum_A1.getValue());

                if (!cpoOrdNumList.contains(sMsg.A.no(i).cpoOrdNum_A1.getValue())) {

                    cpoOrdNumList.add(sMsg.A.no(i).cpoOrdNum_A1.getValue());
                }
            }
        }

        ssmParam.put("cpoOrdNumList", cpoOrdNumList);
        ssmParam.put("dsOrdPosnNumList", dsOrdPosnNumList);
        List<String> notAllocWhList = NLBL3080CommonLogic.getNotAllocWhList(cMsg.glblCmpyCd.getValue());

        if (!notAllocWhList.isEmpty()) {

            ssmParam.put("notAllocWhList", notAllocWhList.toArray(new String[notAllocWhList.size()]));
        }

        return getSsmEZDClient().queryObjectList("SearchOrdLine", ssmParam);
    }

    /**
     * getMdseQtyList
     * @param glblCmpyCd String
     * @param sMsgBLine NLBL3080_BSMsg
     * @return S21SsmEZDResult
     */
    public INVTYTMsgArray getMdseQtyList(String glblCmpyCd, NLBL3080_BSMsg sMsgBLine) {

        INVTYTMsg inMsg = new INVTYTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mdseCd01", sMsgBLine.mdseCd_B1.getValue());
        inMsg.setConditionValue("invtyLocCd01", sMsgBLine.invtyLocCd_B1.getValue());
        inMsg.setConditionValue("locStsCd01", LOC_STS.DC_STOCK);
        inMsg.setConditionValue("stkStsCd01", sMsgBLine.stkStsCd_B1.getValue());

        return (INVTYTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * getSvcMachMstrList
     * @param glblCmpyCd String
     * @param sMsgBLine NLBL3080_BSMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMachMstrList(String glblCmpyCd, NLBL3080_BSMsg sMsgBLine) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        // 2017/08/15 Hisashi QC#20622 Add.
        if (ZYPCommonFunc.hasValue(sMsgBLine.serNum_B1)) {
        	ssmParam.put("serNum", sMsgBLine.serNum_B1.getValue());
        }
        ssmParam.put("mdseCd", sMsgBLine.mdseCd_B1.getValue());
        // 2017/06/28 R.Shimamoto QC#18187 Add.
        if (ZYPCommonFunc.hasValue(sMsgBLine.pickSvcConfigMstrPk_B1)) {
        	ssmParam.put("pickSvcConfigMstrPk", sMsgBLine.pickSvcConfigMstrPk_B1.getValue());
        }
        ssmParam.put("svcMachMaintAvalFlgY", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("getSvcMachMstrList", ssmParam);
    }

    /**
     * getDefMdseSvcMachMstrList
     * @param glblCmpyCd String
     * @param sMsgBLine NLBL3080_BSMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefMdseSvcMachMstrList(String glblCmpyCd, NLBL3080_BSMsg sMsgBLine) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("serNum", sMsgBLine.serNum_B1.getValue());
        ssmParam.put("mdseCd", sMsgBLine.mdseCd_B1.getValue());

        return getSsmEZDClient().queryObjectList("getDefMdseSvcMachMstrList", ssmParam);
    }

    /**
     * getDefMdseSvcMachMstrListForMdlChk
     * @param glblCmpyCd String
     * @param sMsgBLine NLBL3080_BSMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefMdseSvcMachMstrListForMdlChk(String glblCmpyCd, NLBL3080_BSMsg sMsgBLine) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("serNum", sMsgBLine.serNum_B1.getValue());
        ssmParam.put("mdseCd", sMsgBLine.mdseCd_B1.getValue());
        ssmParam.put("mdlId", sMsgBLine.mdlId_B1.getValue());

        return getSsmEZDClient().queryObjectList("getDefMdseSvcMachMstrListForMdlChk", ssmParam);
    }

    /**
     * getActvAssetCnt
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getActvAssetCnt(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("actvAssetFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObject("getActvAssetCnt", ssmParam);
    }

    /**
     * getConfigCompLineCnt
     * @param glblCmpyCd String
     * @param sMsgBLine NLBL3080_BSMsg
     * @param shpgStsCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getConfigCompLineCnt(String glblCmpyCd, NLBL3080_BSMsg sMsgBLine, String shpgStsCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", sMsgBLine.cpoOrdNum_B1.getValue());
        ssmParam.put("pickSvcConfigMstrPk", sMsgBLine.pickSvcConfigMstrPk_B1.getValue());
        ssmParam.put("shpgStsCd", shpgStsCd);

        return getSsmEZDClient().queryObject("getConfigCompLineCnt", ssmParam);
    }

    /**
     * getSetCompLineCnt
     * @param glblCmpyCd String
     * @param sMsgBLine NLBL3080_BSMsg
     * @param shpgStsCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSetCompLineCnt(String glblCmpyCd, NLBL3080_BSMsg sMsgBLine, String shpgStsCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", sMsgBLine.cpoOrdNum_B1.getValue());
        ssmParam.put("setShpgPlnNum", sMsgBLine.setShpgPlnNum_B1.getValue());
        ssmParam.put("shpgStsCd", shpgStsCd);

        return getSsmEZDClient().queryObject("getSetCompLineCnt", ssmParam);
    }

    /**
     * getShipToCustNm
     * @param cMsg NLBL3080CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToCustNm(NLBL3080CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("shipToCustCd", cMsg.shipToCustCd.getValue());

        return getSsmEZDClient().queryObject("GetShipToCustNm", ssmParam);
    }

    /**
     * getSavedSearchOptionList
     * @param bizMsg NLBL3080CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(NLBL3080CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("srchOptAplId", NLBL3080Constant.BUSINESS_ID);
        params.put("srchOptUsrId", cMsg.usrId.getValue());

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }

    /**
     * getSupdReln
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSupdReln(String glblCmpyCd, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getSupdReln", params);
    }

    /**
     * getSsmParamDownLoadOrderLine
     * @param cMsg NLBL3080CMsg
     * @param rownum int
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParamDownLoadOrderLine(NLBL3080CMsg cMsg, int rownum) {

        Map<String, Object> ssmParam = createSearchCond(cMsg);
        ssmParam.put("rownum", rownum);
        ssmParam.put("locStsCd", LOC_STS.DC_STOCK);
        return ssmParam;
    }

    /**
     * createSearchCond
     * @param cMsg NLBL3080CMsg
     * @param glblCmpyCd String
     * @return Map<String, Object>
     */
    private static Map<String, Object> createSearchCond(NLBL3080CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        createSearchCondInit(cMsg, ssmParam, cMsg.glblCmpyCd.getValue());

        if (ZYPCommonFunc.hasValue(cMsg.cpoOrdNum)) {
            ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd)) {
            ssmParam.put("rtlWhCd", cMsg.rtlWhCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd)) {
            ssmParam.put("shipToCustCd", cMsg.shipToCustCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.rddDt_FR)) {
            ssmParam.put("rddDtFr", cMsg.rddDt_FR.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.rddDt_TO)) {
            ssmParam.put("rddDtTo", cMsg.rddDt_TO.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.serNum)) {
            ssmParam.put("serNum", cMsg.serNum.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.svcConfigMstrPk)) {
            ssmParam.put("svcConfigMstrPk", cMsg.svcConfigMstrPk.getValue());
        }

        if (ZYPCommonFunc.hasValue(cMsg.tocCd)) {
            ssmParam.put("tocCd", cMsg.tocCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.mdseCd)) {
            // Check order take mdse_cd.
            if(NLBL3080CommonLogic.isOrdTakeMdse(cMsg.mdseCd.getValue())){
                ssmParam.put("ordTakeMdseCd", cMsg.mdseCd.getValue());
            } else {
                ssmParam.put("mdseCd", cMsg.mdseCd.getValue());
            }
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxOrdDt_FR)) {
            ssmParam.put("ordDtFr", cMsg.xxOrdDt_FR.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxOrdDt_TO)) {
            ssmParam.put("ordDtTo", cMsg.xxOrdDt_TO.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsOrdCatgCd)) {
            ssmParam.put("dsOrdCatgCd", cMsg.dsOrdCatgCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsOrdCatgCd)) {
            ssmParam.put("dsOrdTpCd", cMsg.dsOrdTpCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.t_MdlNm)) {
            ssmParam.put("mdlNm", cMsg.t_MdlNm.getValue());
        }
        List<String> notAllocWhList = NLBL3080CommonLogic.getNotAllocWhList(cMsg.glblCmpyCd.getValue());
        if (!notAllocWhList.isEmpty()) {
            ssmParam.put("notAllocWhList", notAllocWhList.toArray(new String[notAllocWhList.size()]));
        }
        return ssmParam;
    }

    /**
     * createSearchCondInit
     * @param cMsg NLBL3080CMsg
     * @param ssmParam Map<String, Object>
     * @param glblCmpyCd String
     * @return Map<String, Object>
     */
    private static Map<String, Object> createSearchCondInit(NLBL3080CMsg cMsg, Map<String, Object> ssmParam, String glblCmpyCd) {

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordBookFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("invtyCtrlFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("shpgStsCdValid", SHPG_STS.VALIDATED);
        ssmParam.put("shpgStsCdHrdAlloc", SHPG_STS.HARD_ALLOCATED);
        ssmParam.put("locStsDcStk", LOC_STS.DC_STOCK);
        ssmParam.put("configCatgCd", CONFIG_CATG.OUTBOUND);

        // Start QC#27047
        if (
                (
                        !ZYPCommonFunc.hasValue(cMsg.xxChkBox_BO) //
                        && !ZYPCommonFunc.hasValue(cMsg.xxChkBox_AL) //
                        && ZYPConstant.FLG_OFF_N.equals(cMsg.xxChkBox_BO.getValue()) //
                        && ZYPConstant.FLG_OFF_N.equals(cMsg.xxChkBox_AL.getValue())
                 )
                 && ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_NS.getValue())) {

            ssmParam.put("noCratSoFlag", ZYPConstant.FLG_ON_Y);

        }

        if ((!ZYPCommonFunc.hasValue(cMsg.xxChkBox_NS) || ZYPConstant.FLG_OFF_N.equals(cMsg.xxChkBox_NS.getValue())) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_BO.getValue())) {

            ssmParam.put("actualBOFlg", ZYPConstant.FLG_ON_Y);
            ssmParam.put("backOrderFlag", ZYPConstant.FLG_ON_Y);

        }

        if ((!ZYPCommonFunc.hasValue(cMsg.xxChkBox_NS) || ZYPConstant.FLG_OFF_N.equals(cMsg.xxChkBox_NS.getValue())) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_AL.getValue())) {

            ssmParam.put("toBeAllocFlg", ZYPConstant.FLG_ON_Y);
            ssmParam.put("backOrderFlag", ZYPConstant.FLG_ON_Y);

        }

        if (ssmParam.containsKey("actualBOFlg") && ssmParam.containsKey("toBeAllocFlg")) {
            ssmParam.put("allAllocFlg", ZYPConstant.FLG_ON_Y);
        }

        if (!ssmParam.containsKey("actualBOFlg") && !ssmParam.containsKey("toBeAllocFlg") && !ssmParam.containsKey("noCratSoFlag")) {
            ssmParam.put("bothFlag", ZYPConstant.FLG_ON_Y);
        }
        // End QC#27047

        // QC#15650 add start
        ssmParam.put("relFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("relPntHardAllocFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("hldLvlCd", HLD_LVL.CPO_DETAIL_LEVEL);
        ssmParam.put("whTpCd", WH_TP.COMMON);
        // QC#15650 add end

        return ssmParam;
    }

    /**
     * updateShpgPlnValidated
     * @param sMsg NLBL3080SMsg
	 * @param glblCmpyCd String
     * @param idx Integer
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult updateShpgPlnValidated(NLBL3080SMsg sMsg, String glblCmpyCd, Integer idx) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("shpgPlnNum", sMsg.B.no(idx).shpgPlnNum_B1.getValue());
        ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
        ssmParam.put("TrxHdrNum", sMsg.B.no(idx).cpoOrdNum_B1.getValue());
        ssmParam.put("TrxLineNum", sMsg.B.no(idx).cpoDtlLineNum_B1.getValue());
        ssmParam.put("TrxLineSubNum", sMsg.B.no(idx).cpoDtlLineSubNum_B1.getValue());
        ssmParam.put("shpgStsCd", SHPG_STS.VALIDATED);

        return getSsmEZDClient().queryObjectList("updateShpgPlnValidated", ssmParam);
    }

    /**
     * getShpgPlnDplyLineNum
     * @param glblCmpyCd String
     * @param trxHdrNum String
     * @param trxLineNum String
     * @param trxLineSubNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShpgPlnDplyLineNum(String glblCmpyCd, String trxHdrNum, String trxLineNum, String trxLineSubNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("trxHdrNum", trxHdrNum);
        ssmParam.put("trxLineNum", trxLineNum);
        ssmParam.put("trxLineSubNum", trxLineSubNum);

        return getSsmEZDClient().queryObject("getShpgPlnDplyLineNum", ssmParam);
    }

    /**
     * getInvtyAvalQty
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param invtyLocCd String
     * @param stkStsCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvtyAvalQty(String glblCmpyCd, String mdseCd, String invtyLocCd, String stkStsCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("invtyLocCd", invtyLocCd);
        ssmParam.put("locStsCd", LOC_STS.DC_STOCK);
        ssmParam.put("stkStsCd", stkStsCd);

        return getSsmEZDClient().queryObject("getInvtyAvalQty", ssmParam);
    }

    /**
     * getMdseDescShortTxt
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseDescShortTxt(String glblCmpyCd, String mdseCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getMdseDescShortTxt", ssmParam);
    }

    /**
     * checkSetShpgPln
     * @param glblCmpyCd String
     * @param setShpgPlnNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkSetShpgPln(final String glblCmpyCd, final String setShpgPlnNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("setShpgPlnNum", setShpgPlnNum);

        return getSsmEZDClient().queryObjectList("checkSetShpgPln", ssmParam);
    }

    /**
     * getSoftAllocTotQty
     * @param shpgPlnMsg SHPG_PLNTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSoftAllocTotQty(SHPG_PLNTMsg shpgPlnMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", shpgPlnMsg.glblCmpyCd.getValue());
        ssmParam.put("setShpgPlnNum", shpgPlnMsg.setShpgPlnNum.getValue());
        ssmParam.put("trxHdrNum", shpgPlnMsg.trxHdrNum.getValue());
        ssmParam.put("trxLineNum", shpgPlnMsg.trxLineNum.getValue());
        ssmParam.put("trxLineSubNum", shpgPlnMsg.trxLineSubNum.getValue());
        ssmParam.put("trxSrcTpCd", shpgPlnMsg.trxSrcTpCd.getValue());

        return getSsmEZDClient().queryObjectList("getSoftAllocTotQty", ssmParam);
    }

    /**
     * getHldRsnCd
     * @param cMsg DADL0010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHldRsnCd(NLBL3080CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("hldTpCd", HLD_TP.SALES_HOLD);
        ssmParam.put("hldLvlCd", HLD_LVL.SHIPPING_PLAN_LEVEL);
        ssmParam.put("nightBatFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("dtmBatFlg", ZYPConstant.FLG_OFF_N);

        return getSsmEZDClient().queryObjectList("getHldRsnCd", ssmParam);
    }

    /**
     * getHldInfo
     * @param cMsg DADL0010CMsg
     * @param hldRsnCdLst List
     * @param delShplgPlnNumLst List
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHldInfo(NLBL3080CMsg cMsg, List<String> hldRsnCdLst, List<String> delShplgPlnNumLst) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("hldRsnCd", hldRsnCdLst);
        ssmParam.put("delShplgPlnNumLst", delShplgPlnNumLst);

        return getSsmEZDClient().queryObjectList("getHldInfo", ssmParam);
    }

    /**
     * getShpgPlnEachComp
     * information on each ComponentItem.
     * @param glblCmpyCd String
     * @param trxSrcTpCd String
     * @param trxHdrNum String
     * @param trxLineNum String
     * @param setShpgPlnNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShpgPlnEachComp(final String glblCmpyCd, final String trxSrcTpCd, final String trxHdrNum, final String trxLineNum, final String setShpgPlnNum) {

        Map<String, String> key = new HashMap<String, String>();
        key.put("glblCmpyCd", glblCmpyCd);
        key.put("trxHdrNum", trxHdrNum);
        key.put("trxLineNum", trxLineNum);
        key.put("trxSrcTpCd", trxSrcTpCd);
        key.put("setShpgPlnNum", setShpgPlnNum);
        key.put("shpgStsCdHA", SHPG_STS.HARD_ALLOCATED);
        key.put("shpgStsCdSO", SHPG_STS.S_OR_O_CANCELLED);
        key.put("shpgStsCdPO", SHPG_STS.P_OR_O_CANCELLED);
        key.put("on", ZYPConstant.FLG_ON_Y);
        key.put("trxLineNumSet", "000");
        key.put("off", ZYPConstant.FLG_OFF_N);
        key.put("shpgStsCdVA", SHPG_STS.VALIDATED);

        return getSsmEZDClient().queryObjectList("getShpgPlnEachComp", key);
    }

    /**
     * getVendorDrop
     * @param glblCmpyCd String
     * @param trxHdrNum String
     * @param trxLineNum String
     * @param setShpgPlnNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getVendorDrop(String glblCmpyCd, String trxHdrNum, String trxLineNum, String setShpgPlnNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("trxHdrNum", trxHdrNum);
        ssmParam.put("trxLineNum", trxLineNum);
        ssmParam.put("setShpgPlnNum", setShpgPlnNum);
        ssmParam.put("shpgStsCdVA", SHPG_STS.VALIDATED);
        ssmParam.put("on", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("getVendorDrop", ssmParam);
    }

    /**
     * getIntangible
     * @param glblCmpyCd String
     * @param trxHdrNum String
     * @param trxLineNum String
     * @param setShpgPlnNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getIntangible(String glblCmpyCd, String trxHdrNum, String trxLineNum, String setShpgPlnNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("trxHdrNum", trxHdrNum);
        ssmParam.put("trxLineNum", trxLineNum);
        ssmParam.put("setShpgPlnNum", setShpgPlnNum);
        ssmParam.put("shpgStsCdVA", SHPG_STS.VALIDATED);
        ssmParam.put("off", ZYPConstant.FLG_OFF_N);

        return getSsmEZDClient().queryObjectList("getIntangible", ssmParam);
    }

    /**
     * getShpgLvlSalesHold
     * @param glblCmpyCd String
     * @param shpgPlnNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShpgLvlSalesHold(final String glblCmpyCd, final String shpgPlnNum) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("shpgPlnNum", shpgPlnNum);
        param.put("hldLvlCd", HLD_LVL.SHIPPING_PLAN_LEVEL);
        param.put("hldTpCd", HLD_TP.SALES_HOLD);

        return getSsmEZDClient().queryObjectList("getShpgLvlSalesHold", param);
    }

    /**
     * getInstlBaseCtrlFlg
     * @param glblCmpyCd String
     * @param sMsgBLine NLBL3080_BSMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInstlBaseCtrlFlg(String glblCmpyCd, NLBL3080_BSMsg sMsgBLine) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", sMsgBLine.mdseCd_B1.getValue());

        return getSsmEZDClient().queryObjectList("getInstlBaseCtrlFlg", ssmParam);
    }

    /**
     * getPickSvcConfigForNonSerial
     * @param glblCmpyCd String
     * @param sMsgBLine NLBL3080_BSMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPickSvcConfigForNonSerial(String glblCmpyCd, NLBL3080_BSMsg sMsgBLine) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", sMsgBLine.mdseCd_B1.getValue());
        ssmParam.put("svcMachMaintAvalFlgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("pickSvcConfigMstrPk", sMsgBLine.pickSvcConfigMstrPk_B1.getValue());

        return getSsmEZDClient().queryObjectList("getPickSvcConfigForNonSerial", ssmParam);
    }
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    /**
     * getShpgPlnDplyLineNum
     * @param glblCmpyCd String
     * @param trxHdrNum String
     * @param trxLineNum String
     * @param trxLineSubNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getModelCriticality(String glblCmpyCd, String cpoOrdNum, String dsOrdPosnNum, String mdseCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("dsOrdPosnNum", dsOrdPosnNum);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("configCatgCd", "O");

        return getSsmEZDClient().queryObject("getModelCriticality", ssmParam);
    }
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END

    /**
     * Add QC#25456 countHld
     * @param glblCmpyCd String
     * @param sMsgBLine NLBL3080_BSMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countHld(String glblCmpyCd, NLBL3080_BSMsg sMsgBLine) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("hldLvlCpo", HLD_LVL.CPO_LEVEL);
        ssmParam.put("hldLvlCpoDtl", HLD_LVL.CPO_DETAIL_LEVEL);
        ssmParam.put("hldLvlShpg", HLD_LVL.SHIPPING_PLAN_LEVEL);
        ssmParam.put("cpoOrdNum", sMsgBLine.cpoOrdNum_B1.getValue());
        ssmParam.put("cpoDtlLineNum", sMsgBLine.cpoDtlLineNum_B1.getValue());
        ssmParam.put("cpoDtlLineSubNum", sMsgBLine.cpoDtlLineSubNum_B1.getValue());
        ssmParam.put("shpgPlanNum", sMsgBLine.shpgPlnNum_B1.getValue());
        ssmParam.put("trxLineSubNum", "000");

        return getSsmEZDClient().queryObject("countHld", ssmParam);
    }
    
    
    /**
     * getCompatibleItem
     * QC#29214 Add method.
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCompatibleItem(String glblCmpyCd, String mdseCd, List<String> targetMdseRelnTpList) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", glblCmpyCd);
        queryParam.put("MDSE_CD", mdseCd);
        queryParam.put("MDSE_ITEM_RELN_TP_CD", targetMdseRelnTpList);

        return getSsmEZDClient().queryObjectList("getCompatibleItem", queryParam);
    }

    /**
     * Add QC#30903
     * getMachMstrAllocOffList
     * @param glblCmpyCd String
     * @param sMsgBLine NLBL3080_BSMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMachMstrAllocOffList(String glblCmpyCd, NLBL3080_BSMsg sMsgBLine) {

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("trxHdrNum", sMsgBLine.cpoOrdNum_B1.getValue());
        param.put("trxLineNum", sMsgBLine.cpoDtlLineNum_B1.getValue());
        param.put("trxLineSubNum", sMsgBLine.cpoDtlLineSubNum_B1.getValue());

        return getSsmEZDClient().queryObjectList("getMachMstrAllocOffList", param);
    }
}
