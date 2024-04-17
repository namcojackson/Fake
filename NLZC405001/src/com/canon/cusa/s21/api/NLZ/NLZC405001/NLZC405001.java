/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC405001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDItemAttribute;
import parts.common.EZDPMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CARR_RSNTMsg;
import business.db.RTRN_TRK_NTFY_WRKTMsg;
import business.db.RTRN_TRK_STSTMsg;
import business.db.RWS_DTLTMsg;
import business.db.RWS_SCHD_DTL_TRKTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SHPG_ORD_SCHD_TRKTMsg;
import business.parts.NLZC402001PMsg;
import business.parts.NLZC405001PMsg;
import business.parts.NWZC180001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC402001.NLZC402001;
import com.canon.cusa.s21.api.NLZ.NLZC405001.constant.NLZC405001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.POD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.POD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_TRK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Update Delivery/Pickup Schedule from eCarriers API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/14/2016   CSAI            Y.Imazu         Create          CSA
 * 07/11/2016   CSAI            Y.Imazu         Update          QC#10917
 * 04/10/2017   CITS            R.Shimamoto     Update          QC#18272
 * 06/08/2017   CITS            R.Shimamoto     Update          QC#18272-1
 * 06/13/2017   CITS            R.Shimamoto     Update          QC#18620
 * 09/26/2017   CITS            T.Wada          Update          QC#21305
 * 02/02/2018   CITS            T.Hakodate      Update          QC#23921
 * 09/01/2019   CITS            K.Ogino         Update          QC#52975-1
 * </pre>
 */
public class NLZC405001 extends S21ApiCommonBase {

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE paramOnBatchType;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /** Error Message Text Index */
    private int errMsgTxtIndex;

    /** Default Carrier Code */
    private String defCarrCd;

    /** S21SsmBatchClient */
    private static S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NLZC405001.class);

    /** Constructor */
    public NLZC405001() {

        super();
    }

    /**
     * Execute Return Pickup Confirmation API
     * @param pMsgList List<NLZC405001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NLZC405001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {

        for (NLZC405001PMsg pMsg : pMsgList) {

            execute(pMsg, onBatchType);
        }
    }

    /**
     * Execute Return Pickup Confirmation API
     * @param pMsg NLZC405001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NLZC405001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        msgMap = new S21ApiMessageMap(pMsg);
        errMsgTxtIndex = 0;
        paramOnBatchType = onBatchType;

        // Check API Parameter
        if (chkReqParams(pMsg)) {

            // Specify & Execute Event
            CARR_RSNTMsg carrRsnTMsg = new CARR_RSNTMsg();
            ZYPEZDItemValueSetter.setValue(carrRsnTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(carrRsnTMsg.carrRsnCd, pMsg.carrRsnCd.getValue());

            carrRsnTMsg = (CARR_RSNTMsg) S21ApiTBLAccessor.findByKey(carrRsnTMsg);

            if (carrRsnTMsg == null) {

                setParamErrMsg(pMsg, NLZC405001Constant.NLZM2390E);

            } else {

                defCarrCd = ZYPCodeDataUtil.getVarCharConstValue(NLZC405001Constant.NLZC4050_DEF_CARR_CD, pMsg.glblCmpyCd.getValue());

                if (!ZYPCommonFunc.hasValue(defCarrCd)) {

                    setParamErrMsg(pMsg, NLZC405001Constant.NLZM2407E);

                } else if (INBD_OTBD.OUTBOUND.equals(pMsg.inbdOtbdCd.getValue())) {

                    if (ZYPConstant.FLG_ON_Y.equals(carrRsnTMsg.actlConfFlg.getValue())) {

                        doProcessOtbdActl(pMsg);

                    } else {
                        // QC#52975-1
                        doProcessOtbdSchd(pMsg, carrRsnTMsg);
                    }

                } else if (INBD_OTBD.INBOUND.equals(pMsg.inbdOtbdCd.getValue())) {

                    if (ZYPConstant.FLG_ON_Y.equals(carrRsnTMsg.actlConfFlg.getValue())) {

                        doProcessInbdActl(pMsg);

                    } else {

                        doProcessInbdSchd(pMsg);
                    }
                }
            }
        }

        msgMap.flush();
    }

    /**
     * Check Request Parameter
     * @param pMsg NLZC405001PMsg
     * @return boolean Check Result
     */
    private boolean chkReqParams(NLZC405001PMsg pMsg) {

        boolean isChkNorm = true;

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {

            setParamErrMsg(pMsg, NLZC405001Constant.NLZM2259E);
            isChkNorm =  false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {

            setParamErrMsg(pMsg, NLZC405001Constant.NLZM2079E);
            isChkNorm =  false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.inbdOtbdCd)) {

            setParamErrMsg(pMsg, NLZC405001Constant.NLZM2387E);
            isChkNorm =  false;

        } else if (!INBD_OTBD.INBOUND.equals(pMsg.inbdOtbdCd.getValue()) && (!INBD_OTBD.OUTBOUND.equals(pMsg.inbdOtbdCd.getValue()))) {

            setParamErrMsg(pMsg, NLZC405001Constant.NLZM2388E);
            isChkNorm =  false;

        } else if (INBD_OTBD.INBOUND.equals(pMsg.inbdOtbdCd.getValue()) && !ZYPCommonFunc.hasValue(pMsg.cpoOrdNum) && !ZYPCommonFunc.hasValue(pMsg.trxHdrNum)) {

            setParamErrMsg(pMsg, NLZC405001Constant.NLZM2406E);
            isChkNorm =  false;

        } else if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum) && !ZYPCommonFunc.hasValue(pMsg.trxHdrNum) && !ZYPCommonFunc.hasValue(pMsg.proNum)) {

            setParamErrMsg(pMsg, NLZC405001Constant.NLZM2405E);
            isChkNorm =  false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.carrRsnCd)) {

            setParamErrMsg(pMsg, NLZC405001Constant.NLZM2389E);
            isChkNorm =  false;
        }

        return isChkNorm;
    }

    /**
     * Update Shipping Order (Schedule). Mod QC#52975-1.
     * @param pMsg NLZC405001PMsg
     */
    private void doProcessOtbdSchd(NLZC405001PMsg pMsg, CARR_RSNTMsg carrRsnTMsg) {

        /*************************************************************
         * 1. Get SO Data
         ************************************************************/
        List<Map<String, Object>> soDtlLst = getSoDtlLst(pMsg);

        if (soDtlLst == null) {

            return;
        }

        List<String> soLst = new ArrayList<String>();

        for (Map<String, Object> soDtlMap : soDtlLst) {
            // QC#52975-1
            if ((soLst.isEmpty() || !soLst.contains((String) soDtlMap.get("SO_NUM"))) && ZYPConstant.FLG_ON_Y.equals(carrRsnTMsg.schdUpdFlg.getValue())) {

                /*************************************************************
                 * 2. Update SO Schedule
                 ************************************************************/
                SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.soNum, (String) soDtlMap.get("SO_NUM"));
                shpgOrdTMsg = (SHPG_ORDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(shpgOrdTMsg);

                if (shpgOrdTMsg != null) {

                    if (ZYPCommonFunc.hasValue(pMsg.xxRqstDt)) {

                        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdDelyDt, pMsg.xxRqstDt.getValue());
                        // QC#18272-1 Add.
                        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.schdDelyTm, pMsg.schdDelyTm.getValue());
                    }

                    S21ApiTBLAccessor.update(shpgOrdTMsg);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdTMsg.getReturnCode())) {

                        setParamErrMsg(pMsg, NLZC405001Constant.NLZM2393E);
                        break;
                    }

                } else {

                    setParamErrMsg(pMsg, NLZC405001Constant.NLZM2392E);
                    break;
                }

                /*************************************************************
                 * 3. Create SO Schedule Detail Tracking
                 ************************************************************/
                String updUsrId = shpgOrdTMsg.ezUpUserID.getValue();
                String updTs = shpgOrdTMsg.ezUpTime.getValue();
                // QC#18620 Add.
                String updCarrUsr = "";

                if (ZYPCommonFunc.hasValue(pMsg.updUsrId)) {

                    updUsrId = pMsg.updUsrId.getValue();
                }

                if (ZYPCommonFunc.hasValue(pMsg.updTs)) {

                    updTs = pMsg.updTs.getValue();
                }

                if (ZYPCommonFunc.hasValue(pMsg.carrUpdUsrNm)) {
                	updCarrUsr = pMsg.carrUpdUsrNm.getValue();
                }

                SHPG_ORD_SCHD_TRKTMsg shpgOrdSchdTrkTMsg = new SHPG_ORD_SCHD_TRKTMsg();
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.shpgOrdSchdTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SHPG_ORD_SCHD_TRK_SQ));
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.updUsrId, updUsrId);
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.updTs, updTs);
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.soNum, shpgOrdTMsg.soNum.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdCoordStsCd, shpgOrdTMsg.schdCoordStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdCoordPsnCd, shpgOrdTMsg.schdCoordPsnCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdDelyDt, shpgOrdTMsg.schdDelyDt.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.carrCd, shpgOrdTMsg.carrCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.shpgSvcLvlCd, shpgOrdTMsg.shpgSvcLvlCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.tempSchdFlg, shpgOrdTMsg.tempSchdFlg.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.tempSchdRsnCd, shpgOrdTMsg.tempSchdRsnCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.tempSchdCmntTxt, shpgOrdTMsg.tempSchdCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdDurnTmNum, shpgOrdTMsg.schdDurnTmNum.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.carrRsnCd, pMsg.carrRsnCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.carrCmntTxt, pMsg.carrCmntTxt.getValue());
                // QC#18272-1 Add.
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdDelyTm, pMsg.schdDelyTm.getValue());
                // QC#18620 Add.
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.carrUpdUsrNm, updCarrUsr);

                S21ApiTBLAccessor.insert(shpgOrdSchdTrkTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdSchdTrkTMsg.getReturnCode())) {

                    setParamErrMsg(pMsg, NLZC405001Constant.NLZM2394E);
                    break;
                }

                soLst.add((String) soDtlMap.get("SO_NUM"));
            }

            /*************************************************************
             * 4. Update DS Shipping Order Detail
             ************************************************************/
            SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.soNum, (String) soDtlMap.get("SO_NUM"));
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.soSlpNum, (String) soDtlMap.get("SO_SLP_NUM"));

            shpgOrdDtlTMsg = (SHPG_ORD_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(shpgOrdDtlTMsg);

            if (shpgOrdDtlTMsg != null) {

                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.carrRsnCd, pMsg.carrRsnCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.carrCmntTxt, pMsg.carrCmntTxt.getValue());

                S21ApiTBLAccessor.update(shpgOrdDtlTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdDtlTMsg.getReturnCode())) {

                    setParamErrMsg(pMsg, NLZC405001Constant.NLZM2396E);
                    break;
                }

            } else {

                setParamErrMsg(pMsg, NLZC405001Constant.NLZM2395E);
                break;
            }
        }
    }

    /**
     * Update DS Shipping Order (Actual)
     * @param pMsg NLZC405001PMsg
     */
    private void doProcessOtbdActl(NLZC405001PMsg pMsg) {

        /*************************************************************
         * 1. Get SO Data
         ************************************************************/
        List<Map<String, Object>> soDtlLst = getSoDtlLst(pMsg);

        if (soDtlLst == null) {

            return;
        }

        List<String> soLst = new ArrayList<String>();
        List<String> proNumLst = new ArrayList<String>();

        for (Map<String, Object> soDtlMap : soDtlLst) {

            /*************************************************************
             * 2. Update DS Shipping Order Detail
             ************************************************************/
            SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.soNum, (String) soDtlMap.get("SO_NUM"));
            ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.soSlpNum, (String) soDtlMap.get("SO_SLP_NUM"));

            shpgOrdDtlTMsg = (SHPG_ORD_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(shpgOrdDtlTMsg);

            if (shpgOrdDtlTMsg != null) {

                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.carrRsnCd, pMsg.carrRsnCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.carrCmntTxt, pMsg.carrCmntTxt.getValue());

                S21ApiTBLAccessor.update(shpgOrdDtlTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdDtlTMsg.getReturnCode())) {

                    setParamErrMsg(pMsg, NLZC405001Constant.NLZM2396E);
                    break;
                }

            } else {

                setParamErrMsg(pMsg, NLZC405001Constant.NLZM2395E);
                break;
            }

            /*************************************************************
             * 3. Create SO Schedule Tracking
             ************************************************************/
            if (soLst.isEmpty() || !soLst.contains((String) soDtlMap.get("SO_NUM"))) {

                SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.soNum, (String) soDtlMap.get("SO_NUM"));

                shpgOrdTMsg = (SHPG_ORDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(shpgOrdTMsg);

                if (shpgOrdTMsg == null) {

                    setParamErrMsg(pMsg, NLZC405001Constant.NLZM2392E);
                    break;
                }

                String updUsrId = shpgOrdDtlTMsg.ezUpUserID.getValue();
                String updTs = shpgOrdDtlTMsg.ezUpTime.getValue();
                // QC#18620 Add.
                String updCarrUsr = "";

                if (ZYPCommonFunc.hasValue(pMsg.updUsrId)) {

                    updUsrId = pMsg.updUsrId.getValue();
                }

                if (ZYPCommonFunc.hasValue(pMsg.updTs)) {

                    updTs = pMsg.updTs.getValue();
                }

                if (ZYPCommonFunc.hasValue(pMsg.carrUpdUsrNm)) {
                	updCarrUsr = pMsg.carrUpdUsrNm.getValue();
                }

                SHPG_ORD_SCHD_TRKTMsg shpgOrdSchdTrkTMsg = new SHPG_ORD_SCHD_TRKTMsg();
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.shpgOrdSchdTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SHPG_ORD_SCHD_TRK_SQ));
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.updUsrId, updUsrId);
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.updTs, updTs);
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.soNum, shpgOrdTMsg.soNum.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdCoordStsCd, shpgOrdTMsg.schdCoordStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdCoordPsnCd, shpgOrdTMsg.schdCoordPsnCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdDelyDt, shpgOrdTMsg.schdDelyDt.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.carrCd, shpgOrdTMsg.carrCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.shpgSvcLvlCd, shpgOrdTMsg.shpgSvcLvlCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.tempSchdFlg, shpgOrdTMsg.tempSchdFlg.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.tempSchdRsnCd, shpgOrdTMsg.tempSchdRsnCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.tempSchdCmntTxt, shpgOrdTMsg.tempSchdCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.schdDurnTmNum, shpgOrdTMsg.schdDurnTmNum.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.carrRsnCd, pMsg.carrRsnCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.carrCmntTxt, pMsg.carrCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(shpgOrdSchdTrkTMsg.carrUpdUsrNm, updCarrUsr);

                S21ApiTBLAccessor.insert(shpgOrdSchdTrkTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdSchdTrkTMsg.getReturnCode())) {

                    setParamErrMsg(pMsg, NLZC405001Constant.NLZM2394E);
                    break;
                }

                soLst.add((String) soDtlMap.get("SO_NUM"));
            }

            /*************************************************************
             * 4. Call Delivery Confirmation API
             ************************************************************/
            String rtlWhAndCarr = ZYPCommonFunc.concatString((String) soDtlMap.get("RTL_WH_CD"), ":", (String) soDtlMap.get("CARR_CD"));
            String proNumChkVal = ZYPCommonFunc.concatString(rtlWhAndCarr, ":", (String) soDtlMap.get("PRO_NUM"));

            if (proNumLst.isEmpty() || !proNumLst.contains(proNumChkVal)) {

                NLZC402001PMsg delyConfPmsg = new NLZC402001PMsg();
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.ediShpprNm, (String) soDtlMap.get("RTL_WH_NM"));
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.ediShpprAddr, convSubString(delyConfPmsg, "ediShpprAddr", (String) soDtlMap.get("RTL_WH_FIRST_LINE_ADDR")));
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.ediShpprCtyNm, (String) soDtlMap.get("RTL_WH_CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.ediShpprStCd, (String) soDtlMap.get("RTL_WH_ST_CD"));
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.ediShpprPostCd, convSubString(delyConfPmsg, "ediShpprPostCd", (String) soDtlMap.get("RTL_WH_POST_CD")));
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.ediCnsgnNm, convSubString(delyConfPmsg, "ediCnsgnNm", (String) soDtlMap.get("SHIP_TO_ACCT_NM")));
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.ediCnsgnAddr, convSubString(delyConfPmsg, "ediCnsgnAddr", (String) soDtlMap.get("SHIP_TO_FIRST_LINE_ADDR")));
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.ediCnsgnCtyNm, (String) soDtlMap.get("SHIP_TO_CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.ediCnsgnStCd, (String) soDtlMap.get("SHIP_TO_ST_CD"));
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.ediCnsgnPostCd, convSubString(delyConfPmsg, "ediCnsgnPostCd", (String) soDtlMap.get("SHIP_TO_POST_CD")));
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.ediProNum, (String) soDtlMap.get("PRO_NUM"));

                // START 2018/02/02 T.Hakodate [QC#23921,MOD]
                if (ZYPCommonFunc.hasValue(pMsg.ediStsCd)) {
                    ZYPEZDItemValueSetter.setValue(delyConfPmsg.ediStsCd, pMsg.ediStsCd.getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(delyConfPmsg.ediStsCd, POD_STS.COMPLETED_DEPARTED_DELIVERY_LOCATION);
                }
                // END 2018/02/02 T.Hakodate [QC#23921,MOD]

                ZYPEZDItemValueSetter.setValue(delyConfPmsg.ediStsDt, pMsg.xxRqstDt.getValue());
                // QC#18272 Add.
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.ediStsTm, pMsg.actlDelyTm.getValue());

                ZYPEZDItemValueSetter.setValue(delyConfPmsg.ediTmCd, NLZC405001Constant.EDI_TM_CD_LT);
                // QC#21305 Mod.
                if (ZYPCommonFunc.hasValue(pMsg.podSrcTpCd)) {
                	ZYPEZDItemValueSetter.setValue(delyConfPmsg.podSrcTpCd, pMsg.podSrcTpCd.getValue());
                } else {
                	ZYPEZDItemValueSetter.setValue(delyConfPmsg.podSrcTpCd, POD_SRC_TP.ECARRIERS);
                }
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.ediStsCtyNm, (String) soDtlMap.get("SHIP_TO_CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.ediStsStCd, (String) soDtlMap.get("SHIP_TO_ST_CD"));
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.soNum, (String) soDtlMap.get("SO_NUM"));
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.soSlpNum, (String) soDtlMap.get("SO_SLP_NUM"));
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.carrCd, (String) soDtlMap.get("CARR_CD"));
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.carrRsnCd, pMsg.carrRsnCd.getValue());
                ZYPEZDItemValueSetter.setValue(delyConfPmsg.carrCmntTxt, pMsg.carrCmntTxt.getValue());

                NLZC402001 delyConfApi = new NLZC402001();
                delyConfApi.execute(delyConfPmsg, paramOnBatchType);

                List<String> msgIdList = S21ApiUtil.getXxMsgIdList(delyConfPmsg);

                if (!msgIdList.isEmpty()) {

                    boolean isApiErr = true;

                    for (String msgId : msgIdList) {

                        if (ZYPCommonFunc.hasValue(msgId)) {

                            setParamErrMsg(pMsg, msgId);

                            if (msgId.endsWith("E")) {

                                isApiErr = false;
                            }
                        }
                    }

                    if (!isApiErr) {

                        break;
                    }
                }

                proNumLst.add(proNumChkVal);
            }
        }
    }

    /**
     * Get Shipping Order Detail List
     * @param pMsg NLZC405001PMsg
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getSoDtlLst(NLZC405001PMsg pMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", pMsg.cpoOrdNum.getValue());
        queryParam.put("cpoDtlLineNum", pMsg.cpoDtlLineNum.getValue());
        queryParam.put("cpoDtlLineSubNum", pMsg.cpoDtlLineSubNum.getValue());
        queryParam.put("soNum", pMsg.trxHdrNum.getValue());
        queryParam.put("soSlpNum", pMsg.trxLineNum.getValue());
        queryParam.put("proNum", pMsg.proNum.getValue());
        queryParam.put("soPrintFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("shipFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("defCarrCd", defCarrCd);
        queryParam.put("soCustDataTpCd", SO_CUST_DATA_TP.SHIP_TO);

        List<Map<String, Object>> soDtlLst = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSoDtlLst", queryParam);

        if (soDtlLst == null || soDtlLst.isEmpty()) {

            if (ZYPCommonFunc.hasValue(pMsg.proNum) && (ZYPCommonFunc.hasValue(pMsg.cpoOrdNum) || ZYPCommonFunc.hasValue(pMsg.trxHdrNum))) {

                queryParam.remove("proNum");
                soDtlLst = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSoDtlLst", queryParam);
            }
        }

        if (soDtlLst == null || soDtlLst.isEmpty()) {

            setParamErrMsg(pMsg, NLZC405001Constant.NLZM2391E);
            return null;
        }

        return soDtlLst;
    }

    /**
     * convSubString
     * @param pMsg EZDPMsg
     * @param itemName String
     * @param item EZDSStringItem
     * @return String
     */
    private String convSubString(EZDPMsg pMsg, String itemName, String itemVal) {

        int itemSize = getItemSize(pMsg, itemName);

        if (ZYPCommonFunc.hasValue(itemVal) && itemSize < itemVal.length()) {

            return itemVal.substring(0, itemSize);

        } else {

            return itemVal;
        }
    }

    /**
     * getItemSize
     * @param pMsg EZDPMsg
     * @param itemName String
     * @return int
     */
    private int getItemSize(EZDPMsg pMsg, String itemName) {

        EZDItemAttribute attr = pMsg.getAttr(itemName);

        if (attr != null) {

            return attr.getDigit();

        } else {

            return 0;
        }
    }

    /**
     * Update Receiving Work Sheet (Schedule)
     * @param pMsg NLZC405001PMsg
     */
    private void doProcessInbdSchd(NLZC405001PMsg pMsg) {

        /*************************************************************
         * 1. Get RWS Data
         ************************************************************/
        List<Map<String, Object>> rwsDtlLst = getRwsDtlLst(pMsg);

        if (rwsDtlLst == null) {

            return;
        }

        for (Map<String, Object> rwsDtlMap : rwsDtlLst) {

            /*************************************************************
             * 2. Update RWS Schedule Detail
             ************************************************************/
            boolean isRtrnTrkStsChange = false;

            RWS_DTLTMsg rwsDtlTMsg = new RWS_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.rwsNum, (String) rwsDtlMap.get("RWS_NUM"));
            ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.rwsDtlLineNum, (String) rwsDtlMap.get("RWS_DTL_LINE_NUM"));

            rwsDtlTMsg = (RWS_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(rwsDtlTMsg);

            if (rwsDtlTMsg != null) {

                if (ZYPCommonFunc.hasValue(pMsg.xxRqstDt)) {

                    ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.schdPickUpDt, pMsg.xxRqstDt.getValue());
                }

                String updRtrnTrkStsCd = getRtrnTrkSts(pMsg, rwsDtlMap);

                if (!ZYPCommonFunc.hasValue(updRtrnTrkStsCd)) {

                    break;

                } else if (!updRtrnTrkStsCd.equals(rwsDtlTMsg.rtrnTrkStsCd.getValue())) {

                    ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.rtrnTrkStsCd, updRtrnTrkStsCd);
                    isRtrnTrkStsChange = true;
                }

                S21ApiTBLAccessor.update(rwsDtlTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsDtlTMsg.getReturnCode())) {

                    setParamErrMsg(pMsg, NLZC405001Constant.NLZM2399E);
                    break;
                }

            } else {

                setParamErrMsg(pMsg, NLZC405001Constant.NLZM2398E);
                break;
            }

            /*************************************************************
             * 3. Create RWS Schedule Detail Tracking
             ************************************************************/
            String updUsrId = rwsDtlTMsg.ezUpUserID.getValue();
            String updTs = rwsDtlTMsg.ezUpTime.getValue();
            // QC#18620 Add.
            String updCarrUsr = "";

            if (ZYPCommonFunc.hasValue(pMsg.updUsrId)) {

                updUsrId = pMsg.updUsrId.getValue();
            }

            if (ZYPCommonFunc.hasValue(pMsg.updTs)) {

                updTs = pMsg.updTs.getValue();
            }

            if (ZYPCommonFunc.hasValue(pMsg.carrUpdUsrNm)) {
            	updCarrUsr = pMsg.carrUpdUsrNm.getValue();
            }

            RWS_SCHD_DTL_TRKTMsg rwsSchdDtlTrkTMsg = new RWS_SCHD_DTL_TRKTMsg();
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rwsSchdDtlTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.RWS_SCHD_DTL_TRK_SQ));
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.updUsrId, updUsrId);
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.updTs, updTs);
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rwsNum, rwsDtlTMsg.rwsNum.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rwsDtlLineNum, rwsDtlTMsg.rwsDtlLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.schdCoordStsCd, rwsDtlTMsg.schdCoordStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.schdCoordPsnCd, rwsDtlTMsg.schdCoordPsnCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.schdPickUpDt, rwsDtlTMsg.schdPickUpDt.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.carrCd, rwsDtlTMsg.carrCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.shpgSvcLvlCd, rwsDtlTMsg.shpgSvcLvlCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rtrnTrkStsCd, rwsDtlTMsg.rtrnTrkStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rtrnTrkNtfyGrpCd, rwsDtlTMsg.rtrnTrkNtfyGrpCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.carrRsnCd, pMsg.carrRsnCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.carrCmntTxt, pMsg.carrCmntTxt.getValue());
            // QC#18620 Add.
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.carrUpdUsrNm, updCarrUsr);

            S21ApiTBLAccessor.insert(rwsSchdDtlTrkTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsSchdDtlTrkTMsg.getReturnCode())) {

                setParamErrMsg(pMsg, NLZC405001Constant.NLZM2307E);
                break;
            }

            /*************************************************************
             * 4. Create Return Tracking Notification Work
             ************************************************************/
            if (isRtrnTrkStsChange) {

                RTRN_TRK_STSTMsg rtrnTrkStsTMsg = new RTRN_TRK_STSTMsg();
                ZYPEZDItemValueSetter.setValue(rtrnTrkStsTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(rtrnTrkStsTMsg.rtrnTrkStsCd, rwsDtlTMsg.rtrnTrkStsCd.getValue());

                rtrnTrkStsTMsg = (RTRN_TRK_STSTMsg) S21ApiTBLAccessor.findByKey(rtrnTrkStsTMsg);

                if (rtrnTrkStsTMsg == null) {

                    setParamErrMsg(pMsg, NLZC405001Constant.NLZM2400E);
                    break;
                }

                if (ZYPConstant.FLG_ON_Y.equals(rtrnTrkStsTMsg.ntfyMlSendFlg.getValue())) {

                    RTRN_TRK_NTFY_WRKTMsg rtrnTrkNtfyWrkTMsg = new RTRN_TRK_NTFY_WRKTMsg();
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.rtrnTrkNtfyWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.RTRN_TRK_NTFY_WRK_SQ));
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.updUsrId, updUsrId);
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.updTs, updTs);
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.rwsNum, rwsDtlTMsg.rwsNum.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.rwsDtlLineNum, rwsDtlTMsg.rwsDtlLineNum.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.schdCoordStsCd, rwsDtlTMsg.schdCoordStsCd.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.schdCoordPsnCd, rwsDtlTMsg.schdCoordPsnCd.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.schdPickUpDt, rwsDtlTMsg.schdPickUpDt.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.carrCd, rwsDtlTMsg.carrCd.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.shpgSvcLvlCd, rwsDtlTMsg.shpgSvcLvlCd.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.rtrnTrkStsCd, rwsDtlTMsg.rtrnTrkStsCd.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.rtrnTrkNtfyGrpCd, rwsDtlTMsg.rtrnTrkNtfyGrpCd.getValue());
                    // QC#18620 Add.
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.carrUpdUsrNm, updCarrUsr);

                    S21ApiTBLAccessor.insert(rtrnTrkNtfyWrkTMsg);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtrnTrkNtfyWrkTMsg.getReturnCode())) {

                        setParamErrMsg(pMsg, NLZC405001Constant.NLZM2401E);
                        break;
                    }
                }
            }

            /*************************************************************
             * 5. Update RWS Detail
             ************************************************************/
            RWS_DTLTMsg updateRwsDtlTMsg = new RWS_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(updateRwsDtlTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(updateRwsDtlTMsg.rwsNum, (String) rwsDtlMap.get("RWS_NUM"));
            ZYPEZDItemValueSetter.setValue(updateRwsDtlTMsg.rwsDtlLineNum, (String) rwsDtlMap.get("RWS_DTL_LINE_NUM"));

            updateRwsDtlTMsg = (RWS_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(updateRwsDtlTMsg);

            if (updateRwsDtlTMsg != null) {

                ZYPEZDItemValueSetter.setValue(updateRwsDtlTMsg.carrRsnCd, pMsg.carrRsnCd.getValue());
                ZYPEZDItemValueSetter.setValue(updateRwsDtlTMsg.carrCmntTxt, pMsg.carrCmntTxt.getValue());

                S21ApiTBLAccessor.update(updateRwsDtlTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateRwsDtlTMsg.getReturnCode())) {

                    setParamErrMsg(pMsg, NLZC405001Constant.NLZM2403E);
                    break;
                }

            } else {

                setParamErrMsg(pMsg, NLZC405001Constant.NLZM2402E);
                break;
            }
        }
    }

    /**
     * Update Receiving Work Sheet (Actual)
     * @param pMsg NLZC405001PMsg
     */
    private void doProcessInbdActl(NLZC405001PMsg pMsg) {

        /*************************************************************
         * 1. Get RWS Data
         ************************************************************/
        List<Map<String, Object>> rwsDtlLst = getRwsDtlLst(pMsg);

        if (rwsDtlLst == null) {

            return;
        }

        for (Map<String, Object> rwsDtlMap : rwsDtlLst) {

            /*************************************************************
             * 2. Update RWS Detail
             ************************************************************/
            RWS_DTLTMsg rwsDtlTMsg = new RWS_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.rwsNum, (String) rwsDtlMap.get("RWS_NUM"));
            ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.rwsDtlLineNum, (String) rwsDtlMap.get("RWS_DTL_LINE_NUM"));

            rwsDtlTMsg = (RWS_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(rwsDtlTMsg);

            if (rwsDtlTMsg != null) {

                ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.actlPickUpDt, pMsg.xxRqstDt.getValue());
                ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.carrRsnCd, pMsg.carrRsnCd.getValue());
                ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.carrCmntTxt, pMsg.carrCmntTxt.getValue());

                S21ApiTBLAccessor.update(rwsDtlTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsDtlTMsg.getReturnCode())) {

                    setParamErrMsg(pMsg, NLZC405001Constant.NLZM2403E);
                    break;
                }

            } else {

                setParamErrMsg(pMsg, NLZC405001Constant.NLZM2402E);
                break;
            }

            /*************************************************************
             * 3. Create RWS Schedule Detail Tracking
             ************************************************************/
            RWS_DTLTMsg rwsDtlTrkTMsg = new RWS_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(rwsDtlTrkTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsDtlTrkTMsg.rwsNum, (String) rwsDtlMap.get("RWS_NUM"));
            ZYPEZDItemValueSetter.setValue(rwsDtlTrkTMsg.rwsDtlLineNum, (String) rwsDtlMap.get("RWS_DTL_LINE_NUM"));

            rwsDtlTrkTMsg = (RWS_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(rwsDtlTrkTMsg);

            if (rwsDtlTrkTMsg == null) {

                setParamErrMsg(pMsg, NLZC405001Constant.NLZM2398E);
                break;
            }

            String updUsrId = rwsDtlTrkTMsg.ezUpUserID.getValue();
            String updTs = rwsDtlTrkTMsg.ezUpTime.getValue();
            // QC#18620 Add.
            String updCarrUsr = "";

            if (ZYPCommonFunc.hasValue(pMsg.updUsrId)) {

                updUsrId = pMsg.updUsrId.getValue();
            }

            if (ZYPCommonFunc.hasValue(pMsg.updTs)) {

                updTs = pMsg.updTs.getValue();
            }

            if (ZYPCommonFunc.hasValue(pMsg.carrUpdUsrNm)) {
            	updCarrUsr = pMsg.carrUpdUsrNm.getValue();
            }

            RWS_SCHD_DTL_TRKTMsg rwsSchdDtlTrkTMsg = new RWS_SCHD_DTL_TRKTMsg();
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rwsSchdDtlTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.RWS_SCHD_DTL_TRK_SQ));
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.updUsrId, updUsrId);
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.updTs, updTs);
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rwsNum, rwsDtlTrkTMsg.rwsNum.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rwsDtlLineNum, rwsDtlTrkTMsg.rwsDtlLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.schdCoordStsCd, rwsDtlTrkTMsg.schdCoordStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.schdCoordPsnCd, rwsDtlTrkTMsg.schdCoordPsnCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.schdPickUpDt, rwsDtlTrkTMsg.schdPickUpDt.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.carrCd, rwsDtlTrkTMsg.carrCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.shpgSvcLvlCd, rwsDtlTrkTMsg.shpgSvcLvlCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rtrnTrkStsCd, rwsDtlTrkTMsg.rtrnTrkStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rtrnTrkNtfyGrpCd, rwsDtlTrkTMsg.rtrnTrkNtfyGrpCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.carrRsnCd, pMsg.carrRsnCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.carrCmntTxt, pMsg.carrCmntTxt.getValue());
            // QC#18620 Add.
            ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.carrUpdUsrNm, updCarrUsr);

            S21ApiTBLAccessor.insert(rwsSchdDtlTrkTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsSchdDtlTrkTMsg.getReturnCode())) {

                setParamErrMsg(pMsg, NLZC405001Constant.NLZM2307E);
                break;
            }
        }
    }

    /**
     * Get RWS Detail List
     * @param pMsg NLZC405001PMsg
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getRwsDtlLst(NLZC405001PMsg pMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", pMsg.cpoOrdNum.getValue());
        queryParam.put("cpoDtlLineNum", pMsg.cpoDtlLineNum.getValue());
        queryParam.put("cpoDtlLineSubNum", pMsg.cpoDtlLineSubNum.getValue());
        queryParam.put("rwsNum", pMsg.trxHdrNum.getValue());
        queryParam.put("rwsDtlLineNum", pMsg.trxLineNum.getValue());
        queryParam.put("rwsOpenFlg", ZYPConstant.FLG_ON_Y);

        List<Map<String, Object>> rwsDtlLst = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRwsDtlLst", queryParam);

        if (rwsDtlLst == null || rwsDtlLst.isEmpty()) {

            setParamErrMsg(pMsg, NLZC405001Constant.NLZM2397E);
            return null;
        }

        return rwsDtlLst;
    }

    /**
     * Get Return Tracking Status
     * @param pMsg NLZC405001PMsg
     * @param rwsDtlMap Map<String, Object>
     * @return String
     */
    private String getRtrnTrkSts(NLZC405001PMsg pMsg, Map<String, Object> rwsDtlMap) {

        if (!ZYPCommonFunc.hasValue((String) rwsDtlMap.get("RTL_WH_CD"))) {

            setParamErrMsg(pMsg, NLZC405001Constant.NLZM2404E);
            return null;
        }

        NWZC180001PMsg dflWhApiPMsg = new NWZC180001PMsg();
        ZYPEZDItemValueSetter.setValue(dflWhApiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(dflWhApiPMsg.slsDt, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(dflWhApiPMsg.xxModeCd, NWZC180001Constant.PROC_MODE_INBD);
        ZYPEZDItemValueSetter.setValue(dflWhApiPMsg.dsOrdCatgCd, (String) rwsDtlMap.get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(dflWhApiPMsg.dsOrdTpCd, (String) rwsDtlMap.get("DS_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(dflWhApiPMsg.dsRtrnRsnCd, (String) rwsDtlMap.get("RTRN_RSN_CD"));
        ZYPEZDItemValueSetter.setValue(dflWhApiPMsg.mdseCd, (String) rwsDtlMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(dflWhApiPMsg.postCd, (String) rwsDtlMap.get("FROM_LOC_POST_CD"));

        NWZC180001 dflWhApi = new NWZC180001();
        dflWhApi.execute(dflWhApiPMsg, ONBATCH_TYPE.ONLINE);

        List<String> msgIdList = S21ApiUtil.getXxMsgIdList(dflWhApiPMsg);

        if (!msgIdList.isEmpty()) {

            boolean isApiErr = false;

            for (String msgId : msgIdList) {

                if (ZYPCommonFunc.hasValue(msgId)) {

                    setParamErrMsg(pMsg, msgId);

                    if (msgId.endsWith("E")) {

                        isApiErr = true;
                    }
                }
            }

            if (isApiErr) {

                return null;
            }
        }

        if (((String) rwsDtlMap.get("RTL_WH_CD")).equals(dflWhApiPMsg.rtlWhCd.getValue())) {

            return RTRN_TRK_STS.P_OR_U_SCHEDULED_BY_LOCAL_WAREHOUSE;
        }

        return RTRN_TRK_STS.PICKUP_SCHEDULED_BY_RMA_GROUP;
    }

    /**
     * Set API Parameter Check error message
     * @param pMsg NLZC405001PMsg
     * @param msgId String
     */
    private void setParamErrMsg(NLZC405001PMsg pMsg, String msgId) {

        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgTxtList.no(errMsgTxtIndex).xxDsMsgEntryTxt, S21MessageFunc.clspGetMessage(msgId).substring(NLZC405001Constant.MSG_SUBSTRING_LENGTH));
        msgMap.addXxMsgId(msgId);
        errMsgTxtIndex++;
        // QC#18272 Add.
        pMsg.xxMsgTxtList.setValidCount(errMsgTxtIndex);

        S21InfoLogOutput.println(msgId);
        setS21InfoLogParamVal(pMsg);
    }

    /**
     * Set Parameter Value for S21InfoLog
     * @param pMsg NLZC405001PMsg
     */
    private void setS21InfoLogParamVal(NLZC405001PMsg pMsg) {

        if (ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {

            S21InfoLogOutput.println(NLZC405001Constant.NAZM0280E, new String[] {NLZC405001Constant.PARAM_GLBL_CMPY_CD, pMsg.glblCmpyCd.getValue() });
        }

        if (ZYPCommonFunc.hasValue(pMsg.slsDt)) {

            S21InfoLogOutput.println(NLZC405001Constant.NAZM0280E, new String[] {NLZC405001Constant.PARAM_SLS_DT, pMsg.slsDt.getValue() });
        }

        if (ZYPCommonFunc.hasValue(pMsg.inbdOtbdCd)) {

            S21InfoLogOutput.println(NLZC405001Constant.NAZM0280E, new String[] {NLZC405001Constant.PARAM_INBD_OTBD_CD, pMsg.inbdOtbdCd.getValue() });
        }

        if (ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {

            S21InfoLogOutput.println(NLZC405001Constant.NAZM0280E, new String[] {NLZC405001Constant.PARAM_CPO_ORD_NUM, pMsg.cpoOrdNum.getValue() });
        }

        if (ZYPCommonFunc.hasValue(pMsg.cpoDtlLineNum)) {

            S21InfoLogOutput.println(NLZC405001Constant.NAZM0280E, new String[] {NLZC405001Constant.PARAM_CPO_DTL_LINE_NUM, pMsg.cpoDtlLineNum.getValue() });
        }

        if (ZYPCommonFunc.hasValue(pMsg.cpoDtlLineSubNum)) {

            S21InfoLogOutput.println(NLZC405001Constant.NAZM0280E, new String[] {NLZC405001Constant.PARAM_CPO_DTL_LINE_SUB_NUM, pMsg.cpoDtlLineSubNum.getValue() });
        }

        if (ZYPCommonFunc.hasValue(pMsg.trxHdrNum)) {

            S21InfoLogOutput.println(NLZC405001Constant.NAZM0280E, new String[] {NLZC405001Constant.PARAM_TRX_HDR_NUM, pMsg.trxHdrNum.getValue() });
        }

        if (ZYPCommonFunc.hasValue(pMsg.trxLineNum)) {

            S21InfoLogOutput.println(NLZC405001Constant.NAZM0280E, new String[] {NLZC405001Constant.PARAM_TRX_LINE_NUM, pMsg.trxLineNum.getValue() });
        }

        if (ZYPCommonFunc.hasValue(pMsg.proNum)) {

            S21InfoLogOutput.println(NLZC405001Constant.NAZM0280E, new String[] {NLZC405001Constant.PARAM_PRO_NUM, pMsg.proNum.getValue() });
        }

        if (ZYPCommonFunc.hasValue(pMsg.carrRsnCd)) {

            S21InfoLogOutput.println(NLZC405001Constant.NAZM0280E, new String[] {NLZC405001Constant.PARAM_CARR_RSN_CD, pMsg.carrRsnCd.getValue() });
        }

        if (ZYPCommonFunc.hasValue(pMsg.carrCmntTxt)) {

            S21InfoLogOutput.println(NLZC405001Constant.NAZM0280E, new String[] {NLZC405001Constant.PARAM_CARR_CMNT_TXT, pMsg.carrCmntTxt.getValue() });
        }

        if (ZYPCommonFunc.hasValue(pMsg.updUsrId)) {

            S21InfoLogOutput.println(NLZC405001Constant.NAZM0280E, new String[] {NLZC405001Constant.PARAM_UPD_USR_ID, pMsg.updUsrId.getValue() });
        }

        if (ZYPCommonFunc.hasValue(pMsg.updTs)) {

            S21InfoLogOutput.println(NLZC405001Constant.NAZM0280E, new String[] {NLZC405001Constant.PARAM_UPD_TS, pMsg.updTs.getValue() });
        }

        if (ZYPCommonFunc.hasValue(pMsg.xxRqstDt)) {

            S21InfoLogOutput.println(NLZC405001Constant.NAZM0280E, new String[] {NLZC405001Constant.PARAM_XX_RQST_DT, pMsg.xxRqstDt.getValue() });
        }
    }
}
