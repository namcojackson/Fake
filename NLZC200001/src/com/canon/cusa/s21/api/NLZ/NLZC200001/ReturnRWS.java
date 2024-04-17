/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC200001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AREA_LEAD_TMTMsg;
import business.db.AREA_LEAD_TMTMsgArray;
import business.db.DS_CPO_CTAC_PSNTMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.MDSE_STORE_PKGTMsgArray;
import business.db.RWS_DTLTMsg;
import business.db.RWS_HDRTMsg;
import business.db.RWS_MSGTMsg;
import business.db.RWS_SCHD_DTL_TRKTMsg;
import business.db.RWS_SERTMsg;
import business.db.SCE_ORD_TPTMsg;
import business.db.SCE_ORD_TPTMsgArray;
import business.db.SHPG_WTTMsg;
import business.db.SHPG_WTTMsgArray;
import business.db.TRNSP_LTTMsg;
import business.db.TRNSP_LTTMsgArray;
import business.parts.NLZC200001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC200001.constant.NLZC200001Constant;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.common.NLX.NLXC041001.NLXC041001GetDisptSchdCoord;
import com.canon.cusa.s21.common.NLX.NLXC041001.NLXC041001SchdCoordInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DRCT_SHIP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_TRK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_COORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_MODE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Return RWS
 * Create RWS of return order.
 *</pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/16/2015   CITS            T.Tokutomi      Create
 * 06/23/2016   CSAI            Y.Imazu         Update          QC#10650
 * 07/08/2016   CSAI            Y.Imazu         Update          QC#7755
 * 09/12/2016   CITS            T.Wada          Update          QC#5142
 * 03/22/2017   CITS            T.Tokutomi      Update          QC#18115
 * 03/22/2017   CITS            T.Tokutomi      Update          Merge DS
 * 09/07/2017   CITS            T.Tokutomi      Update          QC#19359
 * 10/17/2017   CITS            S.Katsuma       Update          SO#454
 * 10/30/2017   CITS            T.Tokutomi      Update          QC#21528
 * 12/14/2017   CITS            K.Ogino         Update          QC#22101
 * 12/14/2017   Fujitsu         S.Takami        Update          S21_NA#21151
 * 02/25/2019   Fujitsu         S.Takami        Update          QC#30392
 * 05/09/2019   Fujitsu         T.Ogura         Update          QC#50027
 * 05/17/2019   CITS            T.Tokutomi      Update          QC#50392
 *</pre>
 */
class ReturnRWS extends S21ApiCommonBase {

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;

    /**
     * Constructor
     */
    public ReturnRWS() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NLZC404001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NLZC200001PMsg param, ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = null;

        try {
            msgMap = new S21ApiMessageMap(param);

            // Login User ID
            String loginUser = getLoginUserId();

            // Mandatory Check
            if (!hasInputParameter(msgMap)) {
                return;
            }

            // Get Order Category Code for Service Exchange
            List<String> svcExchgCatgList = getSvcExchgCatgList(msgMap);

            // get Return Order
            ArrayList<ReturnOrderBean> rtrnOrdLst = getReturnOrderList(msgMap);

            // base warehouse code. return warehouse code is unique.
            String baseWhCd = rtrnOrdLst.get(0).getRtlWhCd();

            // deliver RWS reference Number
            String rwsRefNum = "";
            if (ZYPCommonFunc.hasValue(param.rwsRefNum)) {
                if (doesExistRws(param.glblCmpyCd.getValue(), baseWhCd, param.rwsRefNum.getValue())) {
                    msgMap.addXxMsgId(NLZC200001Constant.NLZM1025E);
                    return;
                } else {
                    rwsRefNum = param.rwsRefNum.getValue();
                }
            } else {
                rwsRefNum = getRwsReferenceNumber(param.glblCmpyCd.getValue(), param.cpoOrdNum.getValue(), baseWhCd);
            }

            // get RWS Number
            String rwsNumber = getRWSNumber(param.glblCmpyCd.getValue(), onBatchType);

            // All weight
            BigDecimal fullWeight = getAllReturnItemWeight(param.glblCmpyCd.getValue(), rtrnOrdLst);

            // Create Detail
            int i = 1;
            String frstRtrnLT = "99991231";
            boolean baseCmptFlg = false;
            BigDecimal svcConfigMstPk = null;
            DS_CPO_CTAC_PSNTMsg dsCpoCtacPsn = null;

            // QC#5142 T.Wada Add Start
            // get SER_APVL_REQ_FLG
            String serApvlReqFlg = getSerApvlReqFlg(param.glblCmpyCd.getValue());
            // QC#5142 T.Wada Add End

            for (ReturnOrderBean rtrnOrd : rtrnOrdLst) {
                // Check wareHouse
                if (!baseWhCd.equals(rtrnOrd.getRtlWhCd())) {
                    msgMap.addXxMsgId(NLZC200001Constant.NLZM2303E);
                    return;
                }
                // Sub Line Num x => 00x
                String subLineNum = ZYPCommonFunc.leftPad(String.valueOf(i), NLZC200001Constant.MAX_CHAR_SUB_LINE_NUM, NLZC200001Constant.LF_PAD_CHAR);

                // Schedule Coordinator Assignment Psn
                String schdCoordPsn = getScheduleCoordinator(msgMap, baseWhCd, rtrnOrd.getShipToStCd());

                // Return Lead Date
                String rtrnLD = getReturnLeadTime(param.glblCmpyCd.getValue(), param.slsDt.getValue(), rtrnOrd, fullWeight);
                // Latest Return Lead Date
                if (ZYPDateUtil.compare(frstRtrnLT, rtrnLD) > 0) {
                    frstRtrnLT = rtrnLD;
                }

                // baseCmptChk QC#21528 Update check logic.
                if (isBaseComponent(param.glblCmpyCd.getValue(), rtrnOrd)) {

                    if (svcExchgCatgList == null || svcExchgCatgList.isEmpty() || !svcExchgCatgList.contains(rtrnOrd.getDsOrdCatgCd())) {

                        baseCmptFlg = true;
                        svcConfigMstPk = rtrnOrd.getSvcConfigMstrPk();
                    }
                }

                // ctact psn
                if (ZYPCommonFunc.hasValue(rtrnOrd.getDsCpoConfigPk())) {
                    DS_CPO_CTAC_PSNTMsg ctacPsn = getContactPerson(param.glblCmpyCd.getValue(), param.cpoOrdNum.getValue(), rtrnOrd.getDsCpoConfigPk());
                    if (ctacPsn != null) {
                        if (dsCpoCtacPsn != null) {
                            if (dsCpoCtacPsn.dsCpoCtacPsnPk.getValue().compareTo(ctacPsn.dsCpoCtacPsnPk.getValue()) > 0) {
                                dsCpoCtacPsn = ctacPsn;
                            }
                        } else {
                            dsCpoCtacPsn = ctacPsn;
                        }
                    }
                }

                // Create
                if (!insertRwsDtl(param.glblCmpyCd.getValue(), rwsNumber, subLineNum, rtrnLD, rtrnOrd, serApvlReqFlg, schdCoordPsn)) {
                    // DB Error
                    msgMap.addXxMsgId(NLZC200001Constant.NLZM2304E);
                    return;
                }

                if (!insertRwsSchdDtlTrk(param.glblCmpyCd.getValue(), rwsNumber, subLineNum, schdCoordPsn, loginUser, rtrnOrd)) {
                    // DB Error
                    msgMap.addXxMsgId(NLZC200001Constant.NLZM2307E);
                    return;
                }
                // Create Serial
                if (ZYPCommonFunc.hasValue(rtrnOrd.getSerNum())) {
                    if (!insertRwsSer(param.glblCmpyCd.getValue(), rwsNumber, subLineNum, rtrnOrd)) {
                        // DB Error
                        msgMap.addXxMsgId(NLZC200001Constant.NLZM2308E);
                        return;
                    }
                }
                i++;
            }

            // Create Header
            if (baseCmptFlg) {
                if (!insertRwsHdr(param, rwsNumber, rwsRefNum, frstRtrnLT, rtrnOrdLst.get(0), dsCpoCtacPsn, svcConfigMstPk, rtrnOrdLst.get(0).getDsCpoConfigPk())) {
                    // DB Error
                    msgMap.addXxMsgId(NLZC200001Constant.NLZM2309E);
                    return;
                }
            } else {
                if (!insertRwsHdr(param, rwsNumber, rwsRefNum, frstRtrnLT, rtrnOrdLst.get(0), dsCpoCtacPsn, null, rtrnOrdLst.get(0).getDsCpoConfigPk())) {
                    // DB Error
                    msgMap.addXxMsgId(NLZC200001Constant.NLZM2309E);
                    return;
                }
            }
            if (!insertRwsMsg(param.glblCmpyCd.getValue(), rwsNumber, rtrnOrdLst.get(0).getDelyAddlCmntTxt())) {
                // DB Error
                msgMap.addXxMsgId(NLZC200001Constant.NLZM2311E);
                return;
            }

            // Set Output Parameter
            ZYPEZDItemValueSetter.setValue(param.RWSNumList.no(0).rwsNum, rwsNumber);
            // 12/14/2017 S21_NA#21151 Add Start
            param.RWSNumList.setValidCount(param.RWSNumList.getValidCount() + 1);
            // 12/14/2017 S21_NA#21151 Add End

        } finally {
            msgMap.flush();
        }
    }

    /**
     * Check Input Parameter
     * @param msgMap S21ApiMessageMap
     * @return true:OK false:NG
     */
    private boolean hasInputParameter(S21ApiMessageMap msgMap) {

        NLZC200001PMsg param = (NLZC200001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NLZC200001Constant.NLZM1001E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NLZC200001Constant.NLZM1001E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.cpoOrdNum)) {
            msgMap.addXxMsgId(NLZC200001Constant.NLZM1001E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.dsOrdPosnNum)) {
            msgMap.addXxMsgId(NLZC200001Constant.NLZM1001E);
            return false;
        }

        for (int i = 0; i < param.xxDetailList.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(param.xxDetailList.no(i).dsCpoRtrnLineNum)) {
                msgMap.addXxMsgId(NLZC200001Constant.NLZM1001E);
                return false;
            }

            if (!ZYPCommonFunc.hasValue(param.xxDetailList.no(i).dsCpoRtrnLineSubNum)) {
                msgMap.addXxMsgId(NLZC200001Constant.NLZM1001E);
                return false;
            }

            if (!ZYPCommonFunc.hasValue(param.xxDetailList.no(i).ordQty)) {
                msgMap.addXxMsgId(NLZC200001Constant.NLZM1001E);
                return false;
            }
        }

        return true;
    }

    /**
     * getSvcExchgCatgList
     * @param msgMap S21ApiMessageMap
     * @return ArrayList<String>
     */
    private ArrayList<String> getSvcExchgCatgList(S21ApiMessageMap msgMap) {

        NLZC200001PMsg pMsg = (NLZC200001PMsg) msgMap.getPmsg();

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("ordCatgCtxTpCd", "SERVICE_EXCHANGE");

        return (ArrayList<String>) this.ssmBatchClient.queryObjectList("getSvcExchgCatgList", paramMap);
    }

    /**
     * getReturnOrderList
     * @param msgMap S21ApiMessageMap
     * @return Return Order List (ArrayList<ReturnOrderBean>)
     */
    private ArrayList<ReturnOrderBean> getReturnOrderList(S21ApiMessageMap msgMap) {

        NLZC200001PMsg pMsg = (NLZC200001PMsg) msgMap.getPmsg();

        ArrayList<ReturnOrderBean> rtnOrdLst = new ArrayList<ReturnOrderBean>();

        // set common SSM Parameter
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("cpoOrdNum", pMsg.cpoOrdNum.getValue());
        paramMap.put("dsOrdPosnNum", pMsg.dsOrdPosnNum.getValue());

        for (int i = 0; i < pMsg.xxDetailList.getValidCount(); i++) {

            paramMap.put("dsCpoRtrnLineNum", pMsg.xxDetailList.no(i).dsCpoRtrnLineNum.getValue());
            paramMap.put("dsCpoRtrnLineSubNum", pMsg.xxDetailList.no(i).dsCpoRtrnLineSubNum.getValue());

//            ReturnOrderBean rtrnOrd = (ReturnOrderBean) this.ssmBatchClient.queryObject("getReturnOrderDetail", paramMap);

            Map<String, Object> rtrnOrd = (Map<String, Object>) this.ssmBatchClient.queryObject("getReturnOrderDetail", paramMap);

            if (rtrnOrd != null) {
                
                ReturnOrderBean bean = new ReturnOrderBean();
                
                bean.setCpoOrdNum((String)rtrnOrd.get("CPO_ORD_NUM"));
                bean.setDsCpoRtrnLineNum((String)rtrnOrd.get("DS_CPO_RTRN_LINE_NUM"));
                bean.setDsCpoRtrnLineSubNum((String)rtrnOrd.get("DS_CPO_RTRN_LINE_SUB_NUM"));
                bean.setDsOrdPosnNum((String)rtrnOrd.get("DS_ORD_POSN_NUM"));
                bean.setDsCpoConfigPk((BigDecimal)rtrnOrd.get("DS_CPO_CONFIG_PK"));
                bean.setRtlWhCd((String)rtrnOrd.get("RTL_WH_CD"));
                bean.setRtlSwhCd((String)rtrnOrd.get("RTL_SWH_CD"));
                bean.setInvtyLocCd((String)rtrnOrd.get("INVTY_LOC_CD"));
                bean.setShipToCustCd((String)rtrnOrd.get("SHIP_TO_CUST_CD"));
                bean.setShipToLocNm((String)rtrnOrd.get("SHIP_TO_LOC_NM"));
                bean.setShipToAddlLocNm((String)rtrnOrd.get("SHIP_TO_ADDL_LOC_NM"));
                bean.setShipToCtyAddr((String)rtrnOrd.get("SHIP_TO_CTY_ADDR"));
                bean.setShipToStCd((String)rtrnOrd.get("SHIP_TO_ST_CD"));
                bean.setShipToCtryCd((String)rtrnOrd.get("SHIP_TO_CTRY_CD"));
                bean.setShipToFirstLineAddr((String)rtrnOrd.get("SHIP_TO_FIRST_LINE_ADDR"));
                bean.setShipToScdLineAddr((String)rtrnOrd.get("SHIP_TO_SCD_LINE_ADDR"));
                bean.setShipToThirdLineAddr((String)rtrnOrd.get("SHIP_TO_THIRD_LINE_ADDR"));
                bean.setShipToFrthLineAddr((String)rtrnOrd.get("SHIP_TO_FRTH_LINE_ADDR"));
                bean.setShipToPostCd((String)rtrnOrd.get("SHIP_TO_POST_CD"));
                bean.setTrxCd((String)rtrnOrd.get("TRX_CD"));
                bean.setTrxRsnCd((String)rtrnOrd.get("TRX_RSN_CD"));
                bean.setSvcConfigMstrPk((BigDecimal)rtrnOrd.get("SVC_CONFIG_MSTR_PK"));
                bean.setBaseCmptFlg((String)rtrnOrd.get("BASE_CMPT_FLG"));
                bean.setStkStsCd((String)rtrnOrd.get("STK_STS_CD"));
                bean.setMdseCd((String)rtrnOrd.get("MDSE_CD"));
                bean.setRqstPickUpDt((String)rtrnOrd.get("RQST_PICK_UP_DT"));
                bean.setThirdPtyDspTpCd((String)rtrnOrd.get("THIRD_PTY_DSP_TP_CD"));
                bean.setShpgSerTakeFlg((String)rtrnOrd.get("SHPG_SER_TAKE_FLG"));
                bean.setRcvSerTakeFlg((String)rtrnOrd.get("RCV_SER_TAKE_FLG"));
                bean.setAddShpgSvcLvlCd((String)rtrnOrd.get("ADD_SHPG_SVC_LVL_CD"));
                bean.setAddFrtChrgToCd((String)rtrnOrd.get("ADD_FRT_CHRG_TO_CD"));
                bean.setAddFrtChrgMethCd((String)rtrnOrd.get("ADD_FRT_CHRG_METH_CD"));
                bean.setDelyAddlCmntTxt((String)rtrnOrd.get("DELY_ADDL_CMNT_TXT"));
                bean.setCarrCd((String)rtrnOrd.get("CARR_CD"));
                bean.setShpgSvcLvlCd((String)rtrnOrd.get("SHPG_SVC_LVL_CD"));
                bean.setSellToCustCd((String)rtrnOrd.get("SELL_TO_CUST_CD"));
                bean.setOrdBookTs((String)rtrnOrd.get("ORD_BOOK_TS"));
                bean.setDsOrdCatgCd((String)rtrnOrd.get("DS_ORD_CATG_CD"));
                // START 2017/10/17 S.Katsuma [Sol#454,ADD]
                bean.setSellToFirstRefCmntTxt((String)rtrnOrd.get("SELL_TO_FIRST_REF_CMNT_TXT"));
                // END 2017/10/17 S.Katsuma [Sol#454,ADD]
                // QC#22101
                bean.setSvcMachMstrPk((BigDecimal)rtrnOrd.get("SVC_MACH_MSTR_PK"));

                // QC#50392 Add.
                bean.setRqstIstlDt((String)rtrnOrd.get("RQST_ISTL_DT"));
                bean.setRqstIstlTm((String)rtrnOrd.get("RQST_ISTL_TM"));
                bean.setTechMeetTruckDefFlg((String)rtrnOrd.get("TECH_MEET_TRUCK_DEF_FLG"));

                bean.setOrdQty(pMsg.xxDetailList.no(i).ordQty.getValue().abs());
                bean.setSerNum(pMsg.xxDetailList.no(i).serNum.getValue());

                rtnOrdLst.add(bean);
            }
        }

        return rtnOrdLst;
    }

    /**
     * @param glblCmpyCd String
     * @param whCd String
     * @param rwsRefNum String
     * @return true:Exist false: not Exist
     */
    private boolean doesExistRws(String glblCmpyCd, String whCd, String rwsRefNum) {

        RWS_HDRTMsg rwsHdr = new RWS_HDRTMsg();
        rwsHdr.setSQLID("001");
        rwsHdr.setConditionValue("glblCmpyCd01", glblCmpyCd);
        rwsHdr.setConditionValue("rwsRefNum01", whCd);
        rwsHdr.setConditionValue("whCd01", rwsRefNum);

        if (S21ApiTBLAccessor.count(rwsHdr) > 0) {

            return true;
        }

        return false;
    }

    /**
     * get RWS Number
     * @param glblCmpyCd String
     * @param onBatchType ONBATCH_TYPE
     * @return RWS# String
     */
    private String getRWSNumber(String glblCmpyCd, ONBATCH_TYPE onBatchType) {

        if (ONBATCH_TYPE.BATCH.equals(onBatchType)) {

            return ZYPNumbering.getUniqueID(glblCmpyCd, NLXSceConst.RWS_BATCH_KEY);

        } else {

            return ZYPNumbering.getUniqueID(glblCmpyCd, NLXSceConst.RWS_ONLINE_KEY);
        }
    }

    /**
     * get RWS Reference Number
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param whCd String
     * @return rws reference Number (String)
     */
    private String getRwsReferenceNumber(String glblCmpyCd, String cpoOrdNum, String whCd) {

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("whCd", whCd);
        paramMap.put("rwsRefNum", "^" + cpoOrdNum + "-" + "[0-9]{1,2}" + "$");

        String maxRwsRefNum = (String) ssmBatchClient.queryObject("getMaxRwsRefNum", paramMap);
        String rwsRefNum = "";

        if (maxRwsRefNum == null) {

            if (cpoOrdNum.length() < NLZC200001Constant.RWS_REF_NUM_LEN - 2) {

                rwsRefNum = cpoOrdNum + "-" + "01";

            } else {

                rwsRefNum = cpoOrdNum + "-" + "1";
            }

        } else {

            int index = maxRwsRefNum.lastIndexOf("-");
            String strRevision = String.valueOf(Integer.parseInt(maxRwsRefNum.substring(index + 1)) + 1);

            if (cpoOrdNum.length() < NLZC200001Constant.RWS_REF_NUM_LEN - 2) {

                rwsRefNum = cpoOrdNum + "-" + ZYPCommonFunc.leftPad(strRevision, 2, NLZC200001Constant.LF_PAD_CHAR);

            } else {

                rwsRefNum = cpoOrdNum + "-" + strRevision;
            }
        }

        return rwsRefNum;
    }

    /**
     * QC#19359 Update.
     * get Schedule Coordinator
     * @param msgMap S21ApiMessageMap
     * @param whCd String
     * @param stateCd String
     * @return Schedule Coordinator Person Code (String)
     */
    private String getScheduleCoordinator(S21ApiMessageMap msgMap, String whCd, String stateCd) {

        NLZC200001PMsg param = (NLZC200001PMsg) msgMap.getPmsg();

        NLXC041001SchdCoordInfo schdCoordInfo = NLXC041001GetDisptSchdCoord.getDisptSchdCoordForSalesOrder(param.glblCmpyCd.getValue(), param.slsDt.getValue(), param.cpoOrdNum.getValue(), whCd, stateCd);

        if (schdCoordInfo != null) {
            if (schdCoordInfo.getErrList() != null && !schdCoordInfo.getErrList().isEmpty()) {

                // Error
                for (String errId : schdCoordInfo.getErrList()) {
                    msgMap.addXxMsgId(errId);
                }
            }
            return schdCoordInfo.getSchdCoordPsnCd();
        } else {
            return null;
        }
    }

    /**
     * getReturnLeadTime
     * @param glblCmpyCd String
     * @param slsDt String
     * @param rtrnOrd ReturnOrderBean
     * @param weight BigDecimal
     * @return returnLeadTime<Day> (String)
     */
    private String getReturnLeadTime(String glblCmpyCd, String slsDt, ReturnOrderBean rtrnOrd, BigDecimal weight) {

        // Request Pick Up Date
        String rqstPckUpDt = rtrnOrd.getRqstPickUpDt();

        if (!ZYPCommonFunc.hasValue(rqstPckUpDt)) {

            // convert yyyyMMddHHmmssSSS => yyyyMMdd
            String ordBookTs = rtrnOrd.getOrdBookTs().substring(0, NLZC200001Constant.MAX_CHAR_DATE);
            BigDecimal dffAddRtrnPickUpDays = new BigDecimal(ZYPCodeDataUtil.getVarCharConstValue(NLZC200001Constant.DFF_ADD_RTRN_PICK_UP_DAYS, glblCmpyCd));

            rqstPckUpDt = EZDCommonFunc.addDay(ordBookTs, dffAddRtrnPickUpDays.intValue());
        }

        // Transportation LT
        BigDecimal trsprtLt = getTransaportationLT(glblCmpyCd, slsDt, rtrnOrd, weight);

        return EZDCommonFunc.addDay(rqstPckUpDt, trsprtLt.intValue());
    }

    /**
     * getTransaportationLT
     * @param glblCmpyCd String
     * @param slsDt String
     * @param rtrnOrd ReturnOrderBean
     * @param weight BigDecimal
     * @return transport Lead Time Day (BigDecimal)
     */
    private BigDecimal getTransaportationLT(String glblCmpyCd, String slsDt, ReturnOrderBean rtrnOrd, BigDecimal weight) {

        // GUARANTEED_DAYS_DELIVERY
        if (ZYPCommonFunc.hasValue(rtrnOrd.getShpgSvcLvlCd()) || ZYPCommonFunc.hasValue(rtrnOrd.getAddShpgSvcLvlCd())) {

            HashMap<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("glblCmpyCd", glblCmpyCd);

            if (ZYPCommonFunc.hasValue(rtrnOrd.getShpgSvcLvlCd())) {

                paramMap.put("shpgSvcLvlCd", rtrnOrd.getShpgSvcLvlCd());

            } else {

                paramMap.put("shpgSvcLvlCd", rtrnOrd.getAddShpgSvcLvlCd());
            }

            ArrayList<ShpgSvcInfoBean> shpgSvcLeadTmList = new ArrayList<ShpgSvcInfoBean>();
            List<Map<String, Object>> list = (List<Map<String, Object>>)this.ssmBatchClient.queryObjectList("getShpgSvcLeadTm", paramMap);

            if(!list.isEmpty()){
                for(Map<String, Object> m: list){
                    ShpgSvcInfoBean s = new ShpgSvcInfoBean();

                    s.setDelyLeadAot((BigDecimal)m.get("DELY_LEAD_AOT"));
                    s.setShpgSvcTpCd((String)m.get("SHPG_SVC_TP_CD"));

                    shpgSvcLeadTmList.add(s);
                }
            }
            
            
            if (!shpgSvcLeadTmList.isEmpty()) {

                ShpgSvcInfoBean shpgSvcLeadTm = (ShpgSvcInfoBean) shpgSvcLeadTmList.get(0);

                if (SHPG_SVC_TP.GUARANTEED_DAYS_DELIVERY.equals(shpgSvcLeadTm.getShpgSvcTpCd())) {

                    // DelyLeadAot(Time)/24 = DelyLeadAot(Day)
                    return shpgSvcLeadTm.getDelyLeadAot().divide(NLZC200001Constant.HOUR_24);
                }
            }
        }

        // Other
        String frtChrgTo = rtrnOrd.getAddFrtChrgToCd();
        String frtChrgMtd = rtrnOrd.getAddFrtChrgMethCd();

        if (!(ZYPCommonFunc.hasValue(frtChrgTo) && ZYPCommonFunc.hasValue(frtChrgMtd))) {

            frtChrgTo = ZYPCodeDataUtil.getVarCharConstValue(NLZC200001Constant.DFF_RTRN_FRT_CHRG_TO_CD, glblCmpyCd);
            frtChrgMtd = ZYPCodeDataUtil.getVarCharConstValue(NLZC200001Constant.DFF_RTRN_FRT_CHRG_METH_CD, glblCmpyCd);
        }

        // get Shipping mode code.
        String shpgModeCd = getShpgModeCd(//
                glblCmpyCd //
                , slsDt //
                , rtrnOrd.getShipToCustCd() //
                , rtrnOrd.getSellToCustCd() //
                , frtChrgTo //
                , frtChrgMtd //
                , weight);

        // Deliver Transportation LT From TRNSP_LT
        TRNSP_LTTMsg srchTLCond = new TRNSP_LTTMsg();
        srchTLCond.setSQLID("001");
        srchTLCond.setConditionValue("glblCmpyCd01", glblCmpyCd);
        srchTLCond.setConditionValue("whCd01", rtrnOrd.getRtlWhCd());
        srchTLCond.setConditionValue("stCd01", rtrnOrd.getShipToStCd());
        srchTLCond.setConditionValue("shpgModeCd01", shpgModeCd);
        srchTLCond.setConditionValue("fromZipCd01", rtrnOrd.getShipToPostCd());
        srchTLCond.setConditionValue("toZipCd01", rtrnOrd.getShipToPostCd());
        srchTLCond.setConditionValue("effFromDt01", slsDt);
        srchTLCond.setConditionValue("effThruDt01", slsDt);

        TRNSP_LTTMsgArray trnspLtArray = (TRNSP_LTTMsgArray) S21ApiTBLAccessor.findByCondition(srchTLCond);

        if (trnspLtArray == null || trnspLtArray.getValidCount() == 0) {

            // Deliver Transportation LT From TRNSP_LT
            AREA_LEAD_TMTMsg srchALCond = new AREA_LEAD_TMTMsg();
            srchALCond.setSQLID("001");
            srchALCond.setConditionValue("glblCmpyCd01", glblCmpyCd);
            srchALCond.setConditionValue("whCd01", rtrnOrd.getRtlWhCd());
            srchALCond.setConditionValue("stCd01", rtrnOrd.getShipToStCd());
            srchALCond.setConditionValue("shpgModeCd01", shpgModeCd);
            srchALCond.setConditionValue("effFromDt01", slsDt);
            srchALCond.setConditionValue("effThruDt01", slsDt);

            AREA_LEAD_TMTMsgArray areaLeadArray = (AREA_LEAD_TMTMsgArray) S21ApiTBLAccessor.findByCondition(srchALCond);

            if (areaLeadArray != null && areaLeadArray.getValidCount() != 0) {

                // DelyLeadAot(Time)/24 = DelyLeadAot(Day)
                return areaLeadArray.no(0).delyLeadAot.getValue().divide(NLZC200001Constant.HOUR_24);
            }

        } else {

            // DelyLeadAot(Time)/24 = DelyLeadAot(Day)
            return trnspLtArray.no(0).trnspLtAot.getValue().divide(NLZC200001Constant.HOUR_24);
        }

        return new BigDecimal(ZYPCodeDataUtil.getVarCharConstValue(NLZC200001Constant.DFF_RTRN_TRNSP_DAYS, glblCmpyCd));
    }

    /**
     * getShpgModeCd
     * @param glblCmpyCd String
     * @param slsDt String
     * @param rtrnOrd String
     * @param weight String
     * @return shpgModeCd(String)
     */
    private String getShpgModeCd(String glblCmpyCd, String slsDt, String shipToCustCd, String sellToCustCd, String frtChrgToCd, String frtChrgMthCd, BigDecimal weight) {

        // Deliver From ship-to
        SHPG_WTTMsg srcCond = new SHPG_WTTMsg();
        srcCond.setSQLID("003");
        srcCond.setConditionValue("glblCmpyCd01", glblCmpyCd);
        srcCond.setConditionValue("shipToCustCd01", shipToCustCd);
        srcCond.setConditionValue("sellToCustCd01", sellToCustCd);
        srcCond.setConditionValue("transMinWt01", weight.setScale(0, BigDecimal.ROUND_HALF_UP));
        srcCond.setConditionValue("transMaxWt01", weight.setScale(0, BigDecimal.ROUND_HALF_UP));
        srcCond.setConditionValue("frtChrgMethCd01", frtChrgMthCd);
        srcCond.setConditionValue("frtChrgToCd01", frtChrgToCd);
        srcCond.setConditionValue("effFromDt01", slsDt);
        srcCond.setConditionValue("effThruDt01", slsDt);

        SHPG_WTTMsgArray shpgWtFrmShipTo = (SHPG_WTTMsgArray) S21ApiTBLAccessor.findByCondition(srcCond);

        if (shpgWtFrmShipTo != null && shpgWtFrmShipTo.getValidCount() != 0) {

            return shpgWtFrmShipTo.no(0).shpgModeCd.getValue();

        } else {

            // Deliver From sell-to
            srcCond.setConditionValue("shipToCustCd01", "*");

            SHPG_WTTMsgArray shpgWtFrmSellTo = (SHPG_WTTMsgArray) S21ApiTBLAccessor.findByCondition(srcCond);

            if (shpgWtFrmSellTo != null && shpgWtFrmSellTo.getValidCount() != 0) {

                return shpgWtFrmSellTo.no(0).shpgModeCd.getValue();

            } else {

                // Default
                srcCond.setConditionValue("sellToCustCd01", "*");

                SHPG_WTTMsgArray shpgWtDef = (SHPG_WTTMsgArray) S21ApiTBLAccessor.findByCondition(srcCond);

                if (shpgWtDef != null && shpgWtDef.getValidCount() != 0) {

                    return shpgWtDef.no(0).shpgModeCd.getValue();
                }
            }
        }

        return SHPG_MODE.PARCEL;
    }

    /**
     * getAllReturnItemWeight
     * @param glblCmpyCd String
     * @param rtrnOrdLst String
     * @return weight (BigDecimal)
     */
    private BigDecimal getAllReturnItemWeight(String glblCmpyCd, List<ReturnOrderBean> rtrnOrdLst) {

        BigDecimal shpgWght = BigDecimal.ZERO;

        for (ReturnOrderBean record : rtrnOrdLst) {

            shpgWght = shpgWght.add(getMdseWeight(glblCmpyCd, record.getMdseCd()).multiply(record.getOrdQty()));
        }

        return shpgWght;
    }

    /**
     * getMdseWeight
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return in pound weight(BigDecimal)
     */
    private BigDecimal getMdseWeight(String glblCmpyCd, String mdseCd) {

        MDSE_STORE_PKGTMsg srchCodition = new MDSE_STORE_PKGTMsg();
        srchCodition.setSQLID("003");
        srchCodition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        srchCodition.setConditionValue("mdseCd01", mdseCd);
        srchCodition.setConditionValue("pkgUomCd01", PKG_UOM.EACH);

        // Primary Key Search.
        MDSE_STORE_PKGTMsgArray mdseStrPkgLst = (MDSE_STORE_PKGTMsgArray) S21ApiTBLAccessor.findByCondition(srchCodition);

        // no result.
        if (mdseStrPkgLst != null && mdseStrPkgLst.getValidCount() != 0) {

            if (ZYPCommonFunc.hasValue(mdseStrPkgLst.no(0).inPoundWt)) {

                return mdseStrPkgLst.no(0).inPoundWt.getValue();

            } else {

                return BigDecimal.ZERO;
            }

        } else {

            return BigDecimal.ZERO;
        }
    }

    /**
     * insertRwsDtl
     * @param glblCmpyCd String
     * @param rwsNum String
     * @param rwsLineNum String
     * @param trnsLT String
     * @param rtrnOrd ReturnOrderBean
     * @return true:create Success false:Error
     */
    private boolean insertRwsDtl(String glblCmpyCd, String rwsNum, String rwsLineNum, String trnsLT, ReturnOrderBean rtrnOrd, String serApvlReqFlg, String schdCoordPsnCd) {

        RWS_DTLTMsg rwsDtl = new RWS_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(rwsDtl.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsDtl.rwsNum, rwsNum);
        ZYPEZDItemValueSetter.setValue(rwsDtl.rwsDtlLineNum, rwsLineNum);
        ZYPEZDItemValueSetter.setValue(rwsDtl.invtyStkStsCd, rtrnOrd.getStkStsCd());
        ZYPEZDItemValueSetter.setValue(rwsDtl.whInEtaDt, trnsLT);
        ZYPEZDItemValueSetter.setValue(rwsDtl.rwsQty, rtrnOrd.getOrdQty());
        ZYPEZDItemValueSetter.setValue(rwsDtl.rwsPutAwayQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rwsDtl.mdseCd, rtrnOrd.getMdseCd());
        ZYPEZDItemValueSetter.setValue(rwsDtl.trxLineNum, rtrnOrd.getDsCpoRtrnLineNum());
        ZYPEZDItemValueSetter.setValue(rwsDtl.trxLineSubNum, rtrnOrd.getDsCpoRtrnLineSubNum());
        ZYPEZDItemValueSetter.setValue(rwsDtl.rwsStsCd, RWS_STS.PRINTED);

        ZYPEZDItemValueSetter.setValue(rwsDtl.rtlWhCd, rtrnOrd.getRtlWhCd());
        ZYPEZDItemValueSetter.setValue(rwsDtl.rtlSwhCd, rtrnOrd.getRtlSwhCd());
        ZYPEZDItemValueSetter.setValue(rwsDtl.invtyLocCd, rtrnOrd.getInvtyLocCd());
        ZYPEZDItemValueSetter.setValue(rwsDtl.shipFromInvtyLocCd, rtrnOrd.getShipToCustCd());
        ZYPEZDItemValueSetter.setValue(rwsDtl.thirdPtyDspTpCd, rtrnOrd.getThirdPtyDspTpCd());
        ZYPEZDItemValueSetter.setValue(rwsDtl.trxCd, rtrnOrd.getTrxCd());
        ZYPEZDItemValueSetter.setValue(rwsDtl.trxRsnCd, rtrnOrd.getTrxRsnCd());
        ZYPEZDItemValueSetter.setValue(rwsDtl.serApvlReqFlg, serApvlReqFlg);

        if (ZYPConstant.FLG_ON_Y.equals(rtrnOrd.getShpgSerTakeFlg()) || ZYPConstant.FLG_ON_Y.equals(rtrnOrd.getRcvSerTakeFlg())) {
            ZYPEZDItemValueSetter.setValue(rwsDtl.serNumTakeFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(rwsDtl.serNumTakeFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(rwsDtl.schdCoordStsCd, SCHD_COORD_STS.AWAITING_SCHEDULING);

        if (schdCoordPsnCd != null) {

            ZYPEZDItemValueSetter.setValue(rwsDtl.schdCoordPsnCd, schdCoordPsnCd);
        }

        ZYPEZDItemValueSetter.setValue(rwsDtl.carrCd, rtrnOrd.getCarrCd());

        if (ZYPCommonFunc.hasValue(rtrnOrd.getShpgSvcLvlCd())) {

            ZYPEZDItemValueSetter.setValue(rwsDtl.shpgSvcLvlCd, rtrnOrd.getShpgSvcLvlCd());

        } else {

            ZYPEZDItemValueSetter.setValue(rwsDtl.shpgSvcLvlCd, rtrnOrd.getAddShpgSvcLvlCd());
        }

        ZYPEZDItemValueSetter.setValue(rwsDtl.rtrnTrkStsCd, RTRN_TRK_STS.ASSIGNED_TO_LOCAL);
        // QC#50392 Update.
        String techMeetFlg = rtrnOrd.getTechMeetTruckDefFlg();
        if(ZYPConstant.FLG_ON_Y.equals(techMeetFlg)){
            ZYPEZDItemValueSetter.setValue(rwsDtl.schdPickUpTm, rtrnOrd.getRqstIstlTm());
            ZYPEZDItemValueSetter.setValue(rwsDtl.techMeetTruckFlg, techMeetFlg);
        } else {
            rwsDtl.techMeetTruckFlg.setValue(ZYPConstant.FLG_OFF_N);    // START 2019/05/09 T.Ogura [QC#50027,ADD]
        }

        // insert
        S21ApiTBLAccessor.insert(rwsDtl);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsDtl.getReturnCode())) {
            return false;
        }

        return true;
    }

    /**
     * insertRwsSchdDtlTrk
     * @param glblCmpyCd String
     * @param rwsNum String
     * @param rwsLineNum String
     * @param schdCoordPsnCd String
     * @param userId String
     * @param rtrnOrd ReturnOrderBean
     * @return true:create Success false:Error
     */
    private boolean insertRwsSchdDtlTrk(String glblCmpyCd, String rwsNum, String rwsLineNum, String schdCoordPsnCd, String userId, ReturnOrderBean rtrnOrd) {

        RWS_SCHD_DTL_TRKTMsg rwsSchdDtlTrk = new RWS_SCHD_DTL_TRKTMsg();
        ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrk.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrk.rwsSchdDtlTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(NLZC200001Constant.RWS_SCHD_DTL_TRK_SQ));

        if (ZYPCommonFunc.hasValue(userId)) {

            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrk.updUsrId, userId);
        }

        ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrk.updTs, ZYPDateUtil.getCurrentSystemTime(NLZC200001Constant.FORMAT_TIMESTAMP));
        ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrk.rwsNum, rwsNum);
        ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrk.rwsDtlLineNum, rwsLineNum);
        ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrk.schdCoordStsCd, SCHD_COORD_STS.AWAITING_SCHEDULING);

        if (schdCoordPsnCd != null) {

            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrk.schdCoordPsnCd, schdCoordPsnCd);
        }

        ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrk.carrCd, rtrnOrd.getCarrCd());

        if (ZYPCommonFunc.hasValue(rtrnOrd.getShpgSvcLvlCd())) {

            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrk.shpgSvcLvlCd, rtrnOrd.getShpgSvcLvlCd());

        } else {

            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrk.shpgSvcLvlCd, rtrnOrd.getAddShpgSvcLvlCd());
        }

        ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrk.rtrnTrkStsCd, RTRN_TRK_STS.ASSIGNED_TO_LOCAL);

        // insert
        S21ApiTBLAccessor.insert(rwsSchdDtlTrk);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsSchdDtlTrk.getReturnCode())) {

            return false;
        }

        return true;
    }

    /**
     * insertRwsSer
     * @param glblCmpyCd String
     * @param rwsNum String
     * @param rwsLineNum String
     * @param rtrnOrd ReturnOrderBean
     * @return true:create Success false:Error
     */
    private boolean insertRwsSer(String glblCmpyCd, String rwsNum, String rwsLineNum, ReturnOrderBean rtrnOrd) {

        RWS_SERTMsg rwsSer = new RWS_SERTMsg();
        ZYPEZDItemValueSetter.setValue(rwsSer.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsSer.rwsNum, rwsNum);
        ZYPEZDItemValueSetter.setValue(rwsSer.rwsLineNum, rwsLineNum);
        ZYPEZDItemValueSetter.setValue(rwsSer.serNum, rtrnOrd.getSerNum());
        ZYPEZDItemValueSetter.setValue(rwsSer.mdseCd, rtrnOrd.getMdseCd());
        ZYPEZDItemValueSetter.setValue(rwsSer.putAwayChkStsCd, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(rwsSer.serNumSendFlg, ZYPConstant.FLG_OFF_N);

        // insert
        S21ApiTBLAccessor.insert(rwsSer);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsSer.getReturnCode())) {

            return false;
        }

        return true;
    }

    /**
     * insertRwsHdr
     * @param glblCmpyCd String
     * @param rwsNum String
     * @param rwsRefNum String
     * @param cpoOrdNum String
     * @param bolNum String
     * @param rtrnLTDay String
     * @param rtrnOrd ReturnOrderBean
     * @param ctscPsn DS_CPO_CTAC_PSNTMsg
     * @return true:create Success false:Error
     */
    private boolean insertRwsHdr(NLZC200001PMsg param, String rwsNum, String rwsRefNum, String rtrnLTDay, ReturnOrderBean rtrnOrd, DS_CPO_CTAC_PSNTMsg ctscPsn, BigDecimal svcConfigMstrPk, BigDecimal dsCpoConfigPk) {

        RWS_HDRTMsg rwsHdr = new RWS_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(rwsHdr.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(rwsHdr.rwsNum, rwsNum);
        ZYPEZDItemValueSetter.setValue(rwsHdr.inbdSrcTpCd, INBD_SRC_TP.RETURN);
        ZYPEZDItemValueSetter.setValue(rwsHdr.rwsStsCd, RWS_STS.PRINTED);
        ZYPEZDItemValueSetter.setValue(rwsHdr.rwsPrtFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rwsHdr.imptInvBolNum, param.bolNum.getValue());
        ZYPEZDItemValueSetter.setValue(rwsHdr.whCd, rtrnOrd.getRtlWhCd());
        ZYPEZDItemValueSetter.setValue(rwsHdr.whInEtaDt, rtrnLTDay);
        ZYPEZDItemValueSetter.setValue(rwsHdr.ltstWhInEtaDt, rtrnLTDay);
        ZYPEZDItemValueSetter.setValue(rwsHdr.fromLocNm_01, rtrnOrd.getShipToLocNm());
        ZYPEZDItemValueSetter.setValue(rwsHdr.fromLocNm_02, rtrnOrd.getShipToAddlLocNm());
        ZYPEZDItemValueSetter.setValue(rwsHdr.fromLocCtyAddr, rtrnOrd.getShipToCtyAddr());
        ZYPEZDItemValueSetter.setValue(rwsHdr.fromLocStCd, rtrnOrd.getShipToStCd());
        ZYPEZDItemValueSetter.setValue(rwsHdr.fromLocCtryCd, rtrnOrd.getShipToCtryCd());

        // 2019/02/25 QC#30392 Del Start
//        // START 2017/10/17 S.Katsuma [Sol#454,ADD]
//        if (ZYPCommonFunc.hasValue(rtrnOrd.getSellToFirstRefCmntTxt())) {
//            String ctacPsnNm = rtrnOrd.getSellToFirstRefCmntTxt();
//            if (NLZC200001Constant.MAX_CHAR_CTAC_PSN_NM < ctacPsnNm.length()) {
//                ctacPsnNm = ctacPsnNm.substring(0, NLZC200001Constant.MAX_CHAR_CTAC_PSN_NM);
//            }
//            ZYPEZDItemValueSetter.setValue(rwsHdr.fromLocPsnNm, ctacPsnNm);
//        }
//        // END 2017/10/17 S.Katsuma [Sol#454,ADD]
//        if (ctscPsn != null) {
//
//            // START 2017/10/16 S.Katsuma [Sol#454,DEL]
////            String ctacPsnNm = ctscPsn.ctacPsnFirstNm.getValue() + ctscPsn.ctacPsnLastNm.getValue();
////
////            if (NLZC200001Constant.MAX_CHAR_CTAC_PSN_NM < ctacPsnNm.length()) {
////
////                ctacPsnNm = ctacPsnNm.substring(0, NLZC200001Constant.MAX_CHAR_CTAC_PSN_NM);
////            }
//
////            ZYPEZDItemValueSetter.setValue(rwsHdr.fromLocPsnNm, ctacPsnNm);
//            // END 2017/10/16 S.Katsuma [Sol#454,DEL]
//            ZYPEZDItemValueSetter.setValue(rwsHdr.fromLocTelNum, ctscPsn.ctacPsnTelNum);
//        }
        // 2019/02/25 QC#30392 Del End

        // 2019/02/25 QC#30392 Add Start
        String ctacPsnNm = "";
        if (ctscPsn != null) {
            ctacPsnNm = ctscPsn.ctacPsnFirstNm.getValue() + " " + ctscPsn.ctacPsnLastNm.getValue();
            if (!ZYPCommonFunc.hasValue(ctacPsnNm) && ZYPCommonFunc.hasValue(rtrnOrd.getSellToFirstRefCmntTxt())) {
                ctacPsnNm = rtrnOrd.getSellToFirstRefCmntTxt();
            }
            ZYPEZDItemValueSetter.setValue(rwsHdr.fromLocTelNum, ctscPsn.ctacPsnTelNum);
        } else {
            ctacPsnNm = rtrnOrd.getSellToFirstRefCmntTxt();
        }
        if (ZYPCommonFunc.hasValue(ctacPsnNm) && ctacPsnNm.length() > NLZC200001Constant.MAX_CHAR_CTAC_PSN_NM) {
            ctacPsnNm = ctacPsnNm.substring(0, NLZC200001Constant.MAX_CHAR_CTAC_PSN_NM);
        }
        ZYPEZDItemValueSetter.setValue(rwsHdr.fromLocPsnNm, ctacPsnNm);
        // 2019/02/25 QC#30392 Add End

        ZYPEZDItemValueSetter.setValue(rwsHdr.fromLocAddr_01, rtrnOrd.getShipToFirstLineAddr());
        ZYPEZDItemValueSetter.setValue(rwsHdr.fromLocAddr_02, rtrnOrd.getShipToScdLineAddr());
        ZYPEZDItemValueSetter.setValue(rwsHdr.fromLocAddr_03, rtrnOrd.getShipToThirdLineAddr());
        ZYPEZDItemValueSetter.setValue(rwsHdr.fromLocAddr_04, rtrnOrd.getShipToFrthLineAddr());
        ZYPEZDItemValueSetter.setValue(rwsHdr.fromLocTpCd, LOC_TP.CUSTOMER);
        ZYPEZDItemValueSetter.setValue(rwsHdr.fromLocCd, rtrnOrd.getShipToCustCd());

        ZYPEZDItemValueSetter.setValue(rwsHdr.dnldDtTmTs, ZYPDateUtil.getCurrentSystemTime(NLZC200001Constant.FORMAT_DATE_TIME));

        // SCE_ORD_TP INBOUND & Return from customer info
        SCE_ORD_TPTMsg sceOrdTpInbd = getSceOrdTpRTInboud(param.glblCmpyCd.getValue());

        ZYPEZDItemValueSetter.setValue(rwsHdr.trxCd, sceOrdTpInbd.trxCd);
        ZYPEZDItemValueSetter.setValue(rwsHdr.trxRsnCd, sceOrdTpInbd.trxRsnCd);
        ZYPEZDItemValueSetter.setValue(rwsHdr.sceOrdTpCd, SCE_ORD_TP.RETURN);

        ZYPEZDItemValueSetter.setValue(rwsHdr.fromLocPostCd, rtrnOrd.getShipToPostCd());
        ZYPEZDItemValueSetter.setValue(rwsHdr.wmsDropStsCd, NLZC200001Constant.WMS_DROP_STS_CD_NOT_DRP);
        ZYPEZDItemValueSetter.setValue(rwsHdr.rwsRefNum, rwsRefNum);

        ZYPEZDItemValueSetter.setValue(rwsHdr.drctShipTpCd, DRCT_SHIP_TP.N_OR_A);
        ZYPEZDItemValueSetter.setValue(rwsHdr.schdEtaChkFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rwsHdr.finalEtaChkFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(rwsHdr.trxOrdNum, param.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(rwsHdr.sysSrcCd, param.sysSrcCd);

        ZYPEZDItemValueSetter.setValue(rwsHdr.trxSrcTpCd, TRX_SRC_TP.WHOLE_SALES_RETURN);
        ZYPEZDItemValueSetter.setValue(rwsHdr.pickUpRqstFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rwsHdr.dsCpoConfigPk, dsCpoConfigPk);

        ZYPEZDItemValueSetter.setValue(rwsHdr.shipFromAcctNm, getDsAcctNm(param.glblCmpyCd.getValue(), rtrnOrd.getShipToCustCd()));

        if (svcConfigMstrPk != null) {
            ZYPEZDItemValueSetter.setValue(rwsHdr.svcConfigMstrPk, svcConfigMstrPk);
        }

        ZYPEZDItemValueSetter.setValue(rwsHdr.srcOrdNum, param.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(rwsHdr.serAllocTrxHdrNum, param.cpoOrdNum);

        // insert
        S21ApiTBLAccessor.insert(rwsHdr);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdr.getReturnCode())) {

            return false;
        }

        return true;
    }

    /**
     * insertRwsMsg
     * @param glblCmpyCd String
     * @param rwsNum String
     * @param delyAddlCmnt String
     * @return true:create Success false:Error
     */
    private boolean insertRwsMsg(String glblCmpyCd, String rwsNum, String delyAddlCmnt) {

        /** QC#18115 03/22/2017 T.Tokutomi Start **/
        String tempComment = delyAddlCmnt;
        ArrayList<String> commentList = new ArrayList<String>();

        while (ZYPCommonFunc.hasValue(tempComment)) {
            if (NLZC200001Constant.MAX_RWS_MSG_TXT > tempComment.length()) {
                commentList.add(tempComment);
                tempComment = null;
            } else {
                commentList.add(tempComment.substring(0, NLZC200001Constant.MAX_RWS_MSG_TXT));
                tempComment = tempComment.substring(NLZC200001Constant.MAX_RWS_MSG_TXT, tempComment.length());
            }
        }

        for (int i = 0; i < commentList.size(); i++) {
            RWS_MSGTMsg rwsMsg = new RWS_MSGTMsg();
            ZYPEZDItemValueSetter.setValue(rwsMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rwsMsg.rwsNum, rwsNum);
            ZYPEZDItemValueSetter.setValue(rwsMsg.rwsMsgSqNum, String.valueOf(i + 1));
            ZYPEZDItemValueSetter.setValue(rwsMsg.rwsMsgTxt, commentList.get(i));

            // insert
            S21ApiTBLAccessor.insert(rwsMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsMsg.getReturnCode())) {

                return false;
            }
        }

        /** QC#18115 03/22/2017 T.Tokutomi End **/

        return true;
    }

    /**
     * getSceOrdTpRTInboud
     * @param glblCmpyCd String
     * @return SCE_ORD_TPTMsg
     */
    private SCE_ORD_TPTMsg getSceOrdTpRTInboud(String glblCmpyCd) {

        SCE_ORD_TPTMsg cond = new SCE_ORD_TPTMsg();
        cond.setSQLID("002");
        cond.setConditionValue("glblCmpyCd01", glblCmpyCd);
        cond.setConditionValue("sceOrdTpCd01", SCE_ORD_TP.RETURN);
        cond.setConditionValue("inbdOtbdCd01", INBD_OTBD.INBOUND);

        // Primary Key Search
        SCE_ORD_TPTMsgArray sceOrdTpArray = (SCE_ORD_TPTMsgArray) S21ApiTBLAccessor.findByCondition(cond);

        if (sceOrdTpArray != null && sceOrdTpArray.getValidCount() != 0) {

            return sceOrdTpArray.no(0);
        }

        return null;
    }

    /**
     * getContactPerson
     * @param glblCmpyCd
     * @param cpoOrdNum
     * @param dsCpoConfigPk
     * @return DS_CPO_CTAC_PSNTMsg
     */
    private DS_CPO_CTAC_PSNTMsg getContactPerson(String glblCmpyCd, String cpoOrdNum, BigDecimal dsCpoConfigPk) {

        // START 2017/10/17 S.Katsuma [Sol#454,ADD]
//        DS_CPO_CTAC_PSNTMsg cond = new DS_CPO_CTAC_PSNTMsg();
//        cond.setSQLID("008");
//        cond.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        cond.setConditionValue("cpoOrdNum01", cpoOrdNum);
//        cond.setConditionValue("dsCpoConfigPk01", dsCpoConfigPk);
//        cond.setConditionValue("ctacPsnTpCd01", CTAC_TP.SHIP_TO_CONTACT);
//
//        DS_CPO_CTAC_PSNTMsgArray dsCpoCtacPsnArray = (DS_CPO_CTAC_PSNTMsgArray) S21ApiTBLAccessor.findByCondition(cond);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("dsCpoConfigPk", dsCpoConfigPk);
        param.put("ctacPsnTpDelivIns", CTAC_TP.DELIVERY_INSTALL);
        param.put("ctacPsnTpOrdCtac", CTAC_TP.ORDER_CONTACT);

        List<DS_CPO_CTAC_PSNTMsg> dsCpoCtacPsnArray = (List<DS_CPO_CTAC_PSNTMsg>) this.ssmBatchClient.queryObjectList("getContactPerson", param);

//        if (dsCpoCtacPsnArray != null && dsCpoCtacPsnArray.getValidCount() != 0) {
        if (dsCpoCtacPsnArray != null && dsCpoCtacPsnArray.size() != 0) {

//            return dsCpoCtacPsnArray.no(0);
            return dsCpoCtacPsnArray.get(0);
        }
        // END 2017/10/17 S.Katsuma [Sol#454,ADD]

        return null;
    }

    /**
     * getLoginUserId
     * @return
     */
    private String getLoginUserId() {

        S21UserProfileServiceFactory profileService = S21UserProfileServiceFactory.getInstance();

        if (profileService != null) {

            S21UserProfileService profile = profileService.getService();

            if (profile != null) {

                S21UserInfo userInfo = profile.getContextUserInfo();

                if (userInfo != null) {

                    return userInfo.getUserId();
                }
            }
        }

        return null;
    }

    /**
     * getSerApvlReqFlg
     * @param glblCmpyCd
     * @return
     */
    private String getSerApvlReqFlg(String glblCmpyCd) {
        SCE_ORD_TPTMsg sceOrdTpT = new SCE_ORD_TPTMsg();
        sceOrdTpT.glblCmpyCd.setValue(glblCmpyCd);
        sceOrdTpT.sceOrdTpCd.setValue(SCE_ORD_TP.RETURN);
        sceOrdTpT.inbdOtbdCd.setValue(INBD_OTBD.INBOUND);
        sceOrdTpT = (SCE_ORD_TPTMsg) EZDTBLAccessor.findByKey(sceOrdTpT);
        if (sceOrdTpT != null) {
            return sceOrdTpT.serApvlReqFlg.getValue();
        } else {
            return null;
        }
    }

    /**
     * getDsAcctNm
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return DS_ACCT_NM
     */
    private String getDsAcctNm(String glblCmpyCd, String shipToCustCd) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("shipToCustCd", shipToCustCd);

        return (String) ssmBatchClient.queryObject("getDsAcctNm", paramMap);
    }

    /**
     * isBaseComponent
     * @param glblCmpyCd String
     * @param bean ReturnOrderBean
     * @return true(Yes) / false(No)
     */
    private boolean isBaseComponent(String glblCmpyCd, ReturnOrderBean bean) {

        if (ZYPCommonFunc.hasValue(bean.getSvcConfigMstrPk())) {
            String sqlId = "getSvcMachTpCd";
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", glblCmpyCd);
            paramMap.put("svcConfigMstrPk", bean.getSvcConfigMstrPk());
            paramMap.put("mdseCd", bean.getMdseCd());
            if (ZYPCommonFunc.hasValue(bean.getSvcMachMstrPk())) {
                paramMap.put("svcMachMstrPk", bean.getSvcMachMstrPk());
                sqlId = "getSvcMachTpCdWithPk";
            } else {
                paramMap.put("trxHdrNum", bean.getCpoOrdNum());
                paramMap.put("trxLinenum", bean.getDsCpoRtrnLineNum());
                paramMap.put("trxLineSubNum", bean.getDsCpoRtrnLineSubNum());
            }

            String svcMachTpCd = (String) ssmBatchClient.queryObject(sqlId, paramMap);

            if (ZYPCommonFunc.hasValue(svcMachTpCd) && SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
                return true;
            }
        }

        return false;
    }
}
