/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL2020;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDDBRetryRequestException;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLAL2020.constant.NLAL2020Constant;
import business.db.SVC_MACH_MSTRTMsg;

import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NLAL2020Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         Y.Taoka         Create          N/A
 * 06/02/2016   CSAI            Y.Imazu         Update          QC#8663
 * 06/03/2016   CSAI            Y.Imazu         Update          QC#7977
 * 06/03/2016   CSAI            Y.Imazu         Update          QC#7981
 * 06/06/2016   CSAI            Y.Imazu         Update          QC#5061
 * 06/16/2016   CSAI            Y.Imazu         Update          QC#7952
 * 08/25/2016   CITS            S.Endo          Update          QC#13142
 * 04/13/2017   CITS            T.Tokutomi      Update          Merge DS
 * 05/08/2017   CITS            R.Shimamoto     Update          QC#18427
 * 05/08/2017   CITS            Y.Imazu         Update          QC#19049
 * 06/14/2017   CITS            S.Endo          Update          QC#19049
 * 08/30/2017   CITS            T.Hakodate      Update          Sol#069(QC#18270)
 * 03/09/2018   CITS            K.Ogino         Update          QC#15629
 * 05/20/2020   Fujitsu         T.Ogura         Update          QC#56650
 * 12/16/2020   CITS            A.Marte         Update          QC#58070
 * 04/28/2022   CITS            J.Evangelista   Update          QC#58807
 * 10/26/2022   Hitachi         M.Kikushima     Update          QC#60413
 * 08/31/2023   Hitachi         TZ.Win          Update          QC#61792
 *</pre>
 */
public final class NLAL2020Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NLAL2020Query MY_INSTANCE = new NLAL2020Query();

    /**
     * Private constructor
     */
    private NLAL2020Query() {
        super();
    }

    /**
     * Get the NLAL2020Query instance.
     * @return NLAL2020Query instance
     */
    public static NLAL2020Query getInstance() {
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
        params.put("srchOptAplId", NLAL2020Constant.BIZ_ID);
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }

    /**
     * Search
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        Map<String, Object> params = setCommonParam(bizMsg, glblMsg);
        params.put("rwsRefNum", bizMsg.rwsRefNum_H.getValue());
        params.put("rwsNum", bizMsg.rwsNum_H.getValue());
        params.put("bolNum", bizMsg.bolNum_H.getValue());
        params.put("rwsDtlStsCd", bizMsg.rwsStsCd_H.getValue());
        params.put("srcOrdNum", bizMsg.srcOrdNum_H.getValue());
        params.put("svcConfigMstrPk", bizMsg.svcConfigMstrPk_H.getValue());
        params.put("serNum", bizMsg.serNum_H.getValue());
        params.put("shipToRtlWhCd", bizMsg.shipToRtlWhCd_H.getValue());
        params.put("rcvRtlWhCd", bizMsg.rtlWhCd_H.getValue());
        params.put("shipFromLocCd", bizMsg.shipLocCd_H.getValue());
        params.put("whInEtaDtFrom", bizMsg.whInEtaDt_FR.getValue());
        params.put("whInEtaDtTo", bizMsg.whInEtaDt_TO.getValue());

        if (ZYPCommonFunc.hasValue(bizMsg.sceOrdTpCd_H)) {

            params.put("sceOrdTpList", getSceOrdTpListBefMarg(bizMsg));
        }

        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_CL) && ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_CL.getValue())) {

            params.put("closeFlg", bizMsg.xxChkBox_CL.getValue());
        }

        // START 2023/08/31 TZ.Win [QC#61792,ADD]
        if (!ZYPCommonFunc.hasValue(bizMsg.xxChkBox_WW) || !ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_WW.getValue())) {

            List<String> whSysTpCdList = new ArrayList<String>();
            String whSysTpCdConst = ZYPCodeDataUtil.getVarCharConstValue("NLAL2020_NOT_ALLOW_RCV", bizMsg.glblCmpyCd.getValue());
            if (ZYPCommonFunc.hasValue(whSysTpCdConst)) {
                whSysTpCdList = Arrays.asList(whSysTpCdConst.split(","));
            } else
            {
                whSysTpCdList = Arrays.asList("00");
            }
            params.put("whSysTpCdList", whSysTpCdList);
        }
        // END 2023/08/31 TZ.Win [QC#61792,ADD]

        params.put("srcDocLineNumFromPd", new String[] {SCE_ORD_TP.DOMESTIC_CANON_GROUP//
                , SCE_ORD_TP.DOMESTIC_PO_EXISTS//
                , SCE_ORD_TP.DOMESTIC_NON_CSA_INVENTORY//
                , SCE_ORD_TP.REPAIR_SUBCONTRACT//
                , SCE_ORD_TP.BUY_BACK });
        params.put("srcDocLineNumFromDcrd", new String[] {SCE_ORD_TP.RETURN });
        params.put("srcDocLineNumFromCd", new String[] {SCE_ORD_TP.DC_TRANSFER });
        params.put("srcDocLineNumFromPrchReq", new String[] {SCE_ORD_TP.TECH_REQUEST });
        params.put("srcDocLineNumFromBlank", new String[] {SCE_ORD_TP.KITTING //
                , SCE_ORD_TP.REMAN_PARTS_USAGE //
                , SCE_ORD_TP.REMAN_ITEM_CHANGE });
        params.put("srcDocLineNumFromPoRcv", new String[] {SCE_ORD_TP.UN_KITTING });

        params.put("startSubStr", 1);
        params.put("endSubStr", glblMsg.A.no(0).getAttr("serNum_A1").getDigit());
        // QC#15629
        params.put("mdseItemTpMachine", MDSE_ITEM_TP.MACHINE);

        return getSsmEZDClient().queryEZDMsgArray("getRcvOrdToSMsg", params, glblMsg.A);
    }

    /**
     * searchAfterCloseRWS
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchAfterCloseRWS(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg, List<String> rwsNumList) {
        Map<String, Object> params = setCommonParam(bizMsg, glblMsg);
        params.put("rwsRefNum", bizMsg.rwsRefNum_H.getValue());
        params.put("rwsNum", bizMsg.rwsNum_H.getValue());
        params.put("bolNum", bizMsg.bolNum_H.getValue());
        params.put("rwsDtlStsCd", bizMsg.rwsStsCd_H.getValue());
        params.put("srcOrdNum", bizMsg.srcOrdNum_H.getValue());
        params.put("svcConfigMstrPk", bizMsg.svcConfigMstrPk_H.getValue());
        params.put("serNum", bizMsg.serNum_H.getValue());
        params.put("shipToRtlWhCd", bizMsg.shipToRtlWhCd_H.getValue());
        params.put("rcvRtlWhCd", bizMsg.rtlWhCd_H.getValue());
        params.put("shipFromLocCd", bizMsg.shipLocCd_H.getValue());
        params.put("whInEtaDtFrom", bizMsg.whInEtaDt_FR.getValue());
        params.put("whInEtaDtTo", bizMsg.whInEtaDt_TO.getValue());

        if (ZYPCommonFunc.hasValue(bizMsg.sceOrdTpCd_H)) {

            params.put("sceOrdTpList", getSceOrdTpListBefMarg(bizMsg));
        }

        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_CL) && ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_CL.getValue())) {

            params.put("closeFlg", bizMsg.xxChkBox_CL.getValue());
        }

        // START 2023/08/31 TZ.Win [QC#61792,ADD]
        if (!ZYPCommonFunc.hasValue(bizMsg.xxChkBox_WW) || !ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_WW.getValue())) {

            List<String> whSysTpCdList = new ArrayList<String>();
            String whSysTpCdConst = ZYPCodeDataUtil.getVarCharConstValue("NLAL2020_NOT_ALLOW_RCV", bizMsg.glblCmpyCd.getValue());
            if (ZYPCommonFunc.hasValue(whSysTpCdConst)) {
                whSysTpCdList = Arrays.asList(whSysTpCdConst.split(","));
            } else
            {
                whSysTpCdList = Arrays.asList("00");
            }
            params.put("whSysTpCdList", whSysTpCdList);
        }
        // END 2023/08/31 TZ.Win [QC#61792,ADD]

        params.put("srcDocLineNumFromPd", new String[] {SCE_ORD_TP.DOMESTIC_CANON_GROUP//
                , SCE_ORD_TP.DOMESTIC_PO_EXISTS//
                , SCE_ORD_TP.DOMESTIC_NON_CSA_INVENTORY//
                , SCE_ORD_TP.REPAIR_SUBCONTRACT//
                , SCE_ORD_TP.BUY_BACK });
        params.put("srcDocLineNumFromDcrd", new String[] {SCE_ORD_TP.RETURN });
        params.put("srcDocLineNumFromCd", new String[] {SCE_ORD_TP.DC_TRANSFER });
        params.put("srcDocLineNumFromPrchReq", new String[] {SCE_ORD_TP.TECH_REQUEST });
        params.put("srcDocLineNumFromBlank", new String[] {SCE_ORD_TP.KITTING //
                , SCE_ORD_TP.REMAN_PARTS_USAGE //
                , SCE_ORD_TP.REMAN_ITEM_CHANGE });
        params.put("srcDocLineNumFromPoRcv", new String[] {SCE_ORD_TP.UN_KITTING });

        params.put("startSubStr", 1);
        params.put("endSubStr", glblMsg.A.no(0).getAttr("serNum_A1").getDigit());
        params.put("rwsNumList", rwsNumList);
        params.put("closeFlg2", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryEZDMsgArray("getRcvOrdToSMsgAfterRWSClose", params, glblMsg.A);
    }

    /**
     * getSceOrdTpListBefMarg
     * @param bizMsg NLAL2020CMsg
     * @return List<String>
     */
    private List<String> getSceOrdTpListBefMarg(NLAL2020CMsg bizMsg) {

        List<String> sceOrdTpList = new ArrayList<String>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("sceOrdTpCd", bizMsg.sceOrdTpCd_H.getValue());
        params.put("dsCondConstGrpId", "NLAL2020_RCV_CTRL");
        params.put("inbdOtbdCd", INBD_OTBD.INBOUND);
        params.put("bizId", NLAL2020Constant.BIZ_ID);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObjectList("getSceOrdTpListBefMarg", params);

        if (!ssmResult.isCodeNotFound()) {

            sceOrdTpList = (List<String>) ssmResult.getResultObject();

        } else {

            sceOrdTpList.add(bizMsg.sceOrdTpCd_H.getValue());
        }

        return sceOrdTpList;
    }

    /**
     * Set Common Parameter
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     * @return Map<String, Object>
     */
    private Map<String, Object> setCommonParam(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("bizId", NLAL2020Constant.BIZ_ID);
        params.put("constVal", "NLAL2020_RCV_CTRL");
        params.put("rowNum", glblMsg.A.length() + 1);
        params.put("sceOrdTpCdBb", SCE_ORD_TP.BUY_BACK);
        params.put("sceOrdTpCdDt", SCE_ORD_TP.DC_TRANSFER);
        params.put("sceOrdTpCdKt", SCE_ORD_TP.KITTING);
        params.put("sceOrdTpCdKu", SCE_ORD_TP.UN_KITTING);
        params.put("wrkOrdKuLineNum", "000");
        params.put("inbdOtbdCd", INBD_OTBD.INBOUND);

        return params;
    }

    /**
     * getSceOrdTpList
     * @param bizMsg NLAL2020CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSceOrdTpList(NLAL2020CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("inbdOtbdCd", INBD_OTBD.INBOUND);
        params.put("bizId", NLAL2020Constant.BIZ_ID);
        params.put("dsCondConstGrpId", "NLAL2020_RCV_CTRL");
        return getSsmEZDClient().queryObjectList("getSceOrdTpList", params);
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
     * getPermissionWhList
     * @param params Map<String, String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPermissionWhList(Map<String, String> params) {
        return getSsmEZDClient().queryObjectList("getPermissionWhList", params);
    }

    /**
     * getSvcMachMstr
     * @param bizMsg NLAL2020CMsg
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @return SVC_MACH_MSTRTMsg
     */
    public SVC_MACH_MSTRTMsg getSvcMachMstr(NLAL2020CMsg bizMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {

        try {

            svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(svcMachMstrTMsg);

        } catch (EZDDBRecordLockedException rle) {

            bizMsg.setMessageInfo("NLBM1087E");
            return null;

        } catch (EZDDBRetryRequestException rre) {

            bizMsg.setMessageInfo("NLBM1077E");
            return null;
        }

        if (svcMachMstrTMsg == null) {

            bizMsg.setMessageInfo("NLBM1087E");
            return null;
        }

        return svcMachMstrTMsg;
    }

    /**
     * getSerNumList
     * @param bizMsg NLAL2020CMsg
     * @param bizMsgLine NLAL2020_ACMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSerNumList(NLAL2020CMsg bizMsg, NLAL2020_ACMsg bizMsgLine) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("rwsNum", bizMsgLine.rwsNum_A1.getValue());
        params.put("rwsLineNum", bizMsgLine.rwsDtlLineNum_A1.getValue());
        params.put("mdseCd", bizMsgLine.mdseCd_A1.getValue());
        params.put("rwsDtlOpenFlg", bizMsgLine.openFlg_A1.getValue());
        params.put("invtyStkStsCd", bizMsgLine.invtyStkStsCd_A1.getValue());

        if (bizMsgLine.shipToRtlWhCd_A1.getValue().equals(bizMsgLine.rtlWhCd_A1.getValue())) {

            params.put("artRcvFlg", ZYPConstant.FLG_OFF_N);

        } else {

            params.put("rcvRtlWhCd", bizMsgLine.rtlWhCd_A1.getValue());
            params.put("artRcvFlg", ZYPConstant.FLG_ON_Y);
        }

        return getSsmEZDClient().queryObjectList("getSerNumList", params);
    }

    /**
     * getSerNumListGlobal
     * @param glblCmpyCd String
     * @param glbllLineMsg NLAL2020_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSerNumListGlobal(String glblCmpyCd, NLAL2020_ASMsg glbllLineMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", glbllLineMsg.rwsNum_A1.getValue());
        params.put("rwsLineNum", glbllLineMsg.rwsDtlLineNum_A1.getValue());
        params.put("mdseCd", glbllLineMsg.mdseCd_A1.getValue());
        params.put("rwsDtlOpenFlg", glbllLineMsg.openFlg_A1.getValue());
        params.put("invtyStkStsCd", glbllLineMsg.invtyStkStsCd_A1.getValue());

        if (glbllLineMsg.shipToRtlWhCd_A1.getValue().equals(glbllLineMsg.rtlWhCd_A1.getValue())) {

            params.put("artRcvFlg", ZYPConstant.FLG_OFF_N);

        } else {

            params.put("rcvRtlWhCd", glbllLineMsg.rtlWhCd_A1.getValue());
            params.put("artRcvFlg", ZYPConstant.FLG_ON_Y);
        }

        return getSsmEZDClient().queryObjectList("getSerNumList", params);
    }

    /**
     * getMeterEntry
     * @param bizMsg NLAL2020CMsg
     * @param serNumList ArrayList<String>
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMeterEntry(NLAL2020CMsg bizMsg, ArrayList<String> serNumList, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("serNumList", serNumList);
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObjectList("getMeterEntry", params);
    }

    /**
     * getRwsDtlRcvFlgList
     * @param glblCmpyCd String
     * @param rwsNum String
     * @return ArrayList<String>
     */
    public ArrayList<String> getRwsDtlRcvFlgList(String glblCmpyCd, String rwsNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObjectList("getRwsDtlRcvFlgList", params);

        if (!ssmResult.isCodeNotFound()) {

            return (ArrayList<String>) ssmResult.getResultObject();
        }

        return null;
    }

    /**
     * getConfigCmptList
     * @param glblCmpyCd String
     * @param detachCmptMachList List<BigDecimal>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getConfigCmptList(String glblCmpyCd, List<BigDecimal> detachCmptMachList) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("detachCmptMachList", detachCmptMachList);

        return getSsmEZDClient().queryObjectList("getConfigCmptList", params);
    }

    /**
     * getInvtyLoc
     * @param glblCmpyCd String
     * @param rcvRtlWhCd String
     * @param rwsRtlSwhCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvtyLoc(String glblCmpyCd, String rcvRtlWhCd, String rwsRtlSwhCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rtlWhCd", rcvRtlWhCd);
        params.put("rtlSwhCd", rwsRtlSwhCd);

        return getSsmEZDClient().queryObject("getInvtyLoc", params);
    }

    /**
     * get PO Detail Info(PO_ORD_NUM/PO_ORD_DTL_LINE_NUM)
     * @param glblCmpyCd String
     * @param rwsNum String
     * @param rwsDtlLineNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPODetailInfo(String glblCmpyCd, String rwsNum, String rwsDtlLineNum) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("rwsNum", rwsNum);
        paramMap.put("rwsDtlLineNum", rwsDtlLineNum);
        paramMap.put("prchReqRecTpCd", PRCH_REQ_REC_TP.TECH_REQUEST);

        return getSsmEZDClient().queryObject("getDgDpDnInfo", paramMap);
    }

    /**
     * get Active PO Detail KeyList
     * @param glblCmpyCd String
     * @param poOrdNum String
     * @param mdseCd String
     * @param poOrdDtlLineNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getActivePODtlKeyList(String glblCmpyCd, String poOrdNum, String mdseCd, String poOrdDtlLineNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("poOrdNum", poOrdNum);

        // Merchandise Code
        if (ZYPCommonFunc.hasValue(mdseCd)) {
            queryParam.put("mdseCd", mdseCd);
        }
        // Purchase Order Status Code NOT IN ("CLOSED",
        // "CANCELLED")
        queryParam.put("poStsCds", new String[] {PO_STS.CLOSED, PO_STS.CANCELLED });
        if (ZYPCommonFunc.hasValue(poOrdDtlLineNum)) {
            queryParam.put("poOrdDtlLineNum", poOrdDtlLineNum);
        }
        return getSsmEZDClient().queryObjectList("queryActivePODtlKeyList", queryParam);
    }

    /**
     * get All PO Detail KeyList
     * @param glblCmpyCd String
     * @param poOrdNum String
     * @param mdseCd String
     * @param poOrdDtlLineNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAllPODtlKeyList(String glblCmpyCd, String poOrdNum, String mdseCd, String poOrdDtlLineNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("poOrdNum", poOrdNum);

        // Merchandise Code
        if (ZYPCommonFunc.hasValue(mdseCd)) {
            queryParam.put("mdseCd", mdseCd);
        }
        if (ZYPCommonFunc.hasValue(poOrdDtlLineNum)) {
            queryParam.put("poOrdDtlLineNum", poOrdDtlLineNum);
        }
        return getSsmEZDClient().queryObjectList("queryAllPODtlKeyList", queryParam);
    }

    /**
     * getRwsStkStsCd
     * @param bizMsg NLAL2020CMsg
     * @param glblMsgLine NLAL2020_ASMsg
     * @return List<Map<String, Object>>
     */
    public String getRwsStkStsCd(NLAL2020CMsg bizMsg, NLAL2020_ASMsg glblMsgLine) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("rwsNum", glblMsgLine.rwsNum_A1.getValue());
        params.put("rwsDtlLineNum", glblMsgLine.rwsDtlLineNum_A1.getValue());

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getRwsStkStsCd", params);

        if (!ssmResult.isCodeNotFound()) {

            return (String) ssmResult.getResultObject();
        }

        return null;
    }

    /** QC#18427 Add.
     * checkDuplicateRwsPutAwaySer
     * @param params Map<String, String>
     * @return String
     */
    public String checkDuplicateRwsPutAwaySer(Map<String, String> params) {

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("checkDuplicateRwsPutAwaySer", params);

        if (!ssmResult.isCodeNotFound()) {

            return (String) ssmResult.getResultObject();
        }

        return null;
    }

    /**
     * getSvcExchgCatgList
     * @param glblCmpyCd String
     * @return List<String>
     */
    public List<String> getSvcExchgCatgList(String glblCmpyCd) {

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("ordCatgCtxTpCd", "SERVICE_EXCHANGE");

        return (List<String>) getSsmEZDClient().queryObjectList("getSvcExchgCatgList", params).getResultObject();
    }
    
    
    // 08/30/2017 CITS T.Hakodate Add Sol#069(QC#18270) START
    /**
     * getShpgStatus
     * @param glblCmpyCd String
     * @param rwsNum String
     * @param rwsDtlLineNum String
     * @return S21SsmEZDResult
     */
    public ArrayList<String> getShpgStatus(String glblCmpyCd, String rwsNum) {

        Map<String, Object> params = new HashMap<String, Object>();

        List<String> sceOrdTpList = new ArrayList<String>();
        sceOrdTpList.add(SCE_ORD_TP.KITTING);
        sceOrdTpList.add(SCE_ORD_TP.UN_KITTING);
        sceOrdTpList.add(SCE_ORD_TP.REMAN_PARTS_BACK_INVENTORY);

        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);
        params.put("sceOrdTpList", sceOrdTpList);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObjectList("getShpgStatus", params);

        return (ArrayList<String>) ssmResult.getResultObject();

    }

    // 08/30/2017 CITS T.Hakodate Add Sol#069(QC#18270) END

    /** 
     * chkSvcConfigPk
     * @param params Map<String, Object>
     * @return boolean
     */
    public boolean chkSvcConfigPk(Map<String, Object> params) {

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("chkSvcConfigPk", params);

        if (ssmResult.isCodeNormal()) {

            return true;
        }

        return false;
    }

    // START 05/20/2020 T.Ogura [QC#56650,ADD]
    /**
     * getShpgStsCd
     * @param glblCmpyCd String
     * @param rwsNum String
     * @param rwsDtlLineNum String
     * @return String
     */
    public String getShpgStsCd(String glblCmpyCd, String rwsNum, String rwsDtlLineNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);
        params.put("rwsDtlLineNum", rwsDtlLineNum);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getShpgStsCd", params);

        if (!ssmResult.isCodeNotFound()) {

            return (String) ssmResult.getResultObject();
        }

        return null;
    }
    // END   05/20/2020 T.Ogura [QC#56650,ADD]

    // START 2020/12/16 A.Marte [QC#58070, ADD]
   /**
     * getWhSysTpCd
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWhSysTpCd(String glblCmpyCd, String rtlWhCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rtlWhCd", rtlWhCd);

        return getSsmEZDClient().queryObjectList("getRtlWhSysTpCd", params);
    }
    // END 2020/12/16 A.Marte [QC#58070, ADD]

    // START 2022/04/28 J.Evangelista [QC#58807,ADD]
    /**
     * chkRtrnMachInRws
     * @param glblCmpyCd String
     * @param rwsNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkRtrnMachInRws(String glblCmpyCd, String rwsNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);
        params.put("svcMachTpCd", SVC_MACH_TP.MACHINE);
        params.put("rtrnRsnCd", new String[] {RTRN_RSN.SERVICE_EXCHANGE});

        return getSsmEZDClient().queryObject("chkRtrnMachInRws", params);
    }

    /**
     * getIntgItemForTrmn
     * @param glblCmpyCd String
     * @param svcConfigMstrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getIntgItemForTrmn(String glblCmpyCd, BigDecimal svcConfigMstrPk) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcConfigMstrPk", svcConfigMstrPk);
        params.put("invtyCtrlFlg", ZYPConstant.FLG_OFF_N);
        params.put("instlBaseCtrlFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("getIntgItemForTrmn", params);
    }
    // END 2022/04/28 J.Evangelista [QC#58807,ADD]


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
}
