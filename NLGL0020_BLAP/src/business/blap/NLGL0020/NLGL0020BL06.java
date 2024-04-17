/**
 * <pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0020;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLGL0020.constant.NLGL0020Constant;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.RTL_SWHTMsg;
import business.db.RWS_DTLTMsg;
import business.db.RWS_DTLTMsgArray;
import business.db.RWS_HDRTMsg;
import business.db.RWS_SERTMsg;
import business.db.RWS_SERTMsgArray;
import business.db.SCE_ORD_TPTMsg;
import business.db.WMS_INBD_PO_DTLTMsg;
import business.db.WMS_INBD_PO_HDRTMsg;
import business.db.WMS_INBD_RWS_WRKTMsg;
import business.db.WMS_INBD_TRXTMsg;

import com.canon.cusa.s21.common.NLX.NLXC014001.NLXC014001;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TASK;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * PO Maintenance
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 08/30/2013     CSAI            N.Sekine          Create            MW Replace Initial
 * 05/25/2017     CITS            S.Endo            Update            RS#3173
 * 07/03/2017     CITS            S.Endo            Update            QC#19042
 * 07/05/2017     CITS            R.Shimamoto       Update            QC#19750
 *</pre>
 */
public class NLGL0020BL06 extends S21BusinessHandler implements NLGL0020Constant {
    private BigDecimal decUOM;
    /**
     * The method explanation: this is a method of the execution after
     * the SV enent(setRequestData).
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLGL0020SCRN00_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_CMN_Submit(cMsg, sMsg);
            } else if (EVENT_NM_NLGL0020SCRN00_ONCLICK_DELETE.equals(screenAplID)) {
                doProcess_NLGL0020Scrn00_CMN_Delete(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * The method explanation: The event[SUBMIT] processing is
     * executed.
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NLGL0020Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {

        NLGL0020CMsg bizMsg = (NLGL0020CMsg) cMsg;
        NLGL0020SMsg globalMsg = (NLGL0020SMsg) sMsg;

        RWS_HDRTMsg fromRwsHdrTMsg = new RWS_HDRTMsg();

        if (TAB_ID_DNLD.equals(bizMsg.xxDplyTab.getValue())) {
            String strTrxSeq = ZYPOracleSeqAccessor.getNumberString(TO_WMS_DATA_IF_SQ, LEN_TO_WMS_SEQ);
            String wkWH = bizMsg.whCd_E2.getValue();
            String strPOIdNum = "";

            // When Submit Type = "Copy", create records.
            if (ZYPConstant.FLG_ON_1.equals(bizMsg.xxSrchRqstDtTpCd_E2.getValue())) {
                // copy button only
                strPOIdNum = ZYPNumbering.getUniqueID(PO_ID_ONLINE_KEY);
                strPOIdNum = DATA_VALUE_Y + wkWH + strPOIdNum.substring(DATA_VALUE_INT_1, DATA_VALUE_INT_6);
                String newRwsNum = setPoHeaderData(bizMsg, strTrxSeq, strPOIdNum, wkWH, fromRwsHdrTMsg);
                if (bizMsg.F.getValidCount() == 0) {
                    setPoDetailData(bizMsg, globalMsg, strTrxSeq, strPOIdNum, wkWH, newRwsNum, fromRwsHdrTMsg);
                } else {
                    if (!setItemInputData(bizMsg, globalMsg, strTrxSeq, strPOIdNum)) {
                        return;
                    } else {
                        setPoDetailData(bizMsg, globalMsg, strTrxSeq, strPOIdNum, wkWH, newRwsNum, fromRwsHdrTMsg);
                    }
                }
                // When Submit Type = "Resend", set
                // RWS_HDR.WMS_DROP_STS_CD="0" only.
                // When Submit Type = "Copy", set
                // RWS_HDR.WMS_DROP_STS_CD="0" after create records.
                changeRWSHdrWmsDropStsCd(bizMsg);

                bizMsg.setMessageInfo(NLGM0073I, new String[] {DATA_VALUE_INSERT, strPOIdNum });
            } else {
                changeRWSHdrWmsDropStsCd(bizMsg);
                bizMsg.setMessageInfo(NLGM0025I, new String[] {DATA_VALUE_INSERT });
            }
        } else {
            // submit process for UpLoad
            setWmsInbdTrx(bizMsg);
            bizMsg.setMessageInfo(NLGM0025I, new String[] {DATA_VALUE_INSERT });
        }
    }

    private void changeRWSHdrWmsDropStsCd(NLGL0020CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_WH_CD, bizMsg.whCd_02.getValue());
        ssmParam.put(DB_PARAM_PO_ID, bizMsg.wmsPoId_E1.getValue());
        ssmParam.put(DB_PARAM_SQ_NUM, bizMsg.G.no(0).wmsSqNum_G2);

        S21SsmEZDResult resultQuery = NLGL0020Query.getInstance().getRwsHdrKey(ssmParam);
        if (!resultQuery.isCodeNotFound()) {
            Map<String, Object> rwsHdrKey = (Map<String, Object>) resultQuery.getResultObject();
            RWS_HDRTMsg rwsHdrTMsg = new RWS_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.glblCmpyCd, (String)rwsHdrKey.get(GLBL_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.rwsNum, (String)rwsHdrKey.get(DB_RWS_NUM));
            rwsHdrTMsg = (RWS_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(rwsHdrTMsg);
            if (rwsHdrTMsg != null){
                ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.wmsDropStsCd,DATA_VALUE_0);
                EZDTBLAccessor.update(rwsHdrTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdrTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NLGM0008E, new String[] {TBL_RWS_HDR //
                            , DB_GLBL_CMPY_CD + DELIMITER_COLON + DB_RWS_NUM //
                            , rwsHdrTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + rwsHdrTMsg.rwsNum.getValue() });
                }
            }
            
        }
    }

    /**
     * The method explanation: The event[Delete] processing is
     * executed.
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NLGL0020Scrn00_CMN_Delete(EZDCMsg cMsg, EZDSMsg sMsg) {

        NLGL0020CMsg bizMsg = (NLGL0020CMsg) cMsg;

        if (TAB_ID_UPD.equals(bizMsg.xxDplyTab.getValue())) {

            WMS_INBD_TRXTMsg wmsInbdTrxTMsg = new WMS_INBD_TRXTMsg();
            for (int i = 0; i < bizMsg.I.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.I.no(i).xxChkBox_I1.getValue())) {
                    if (!ZYPCommonFunc.hasValue(bizMsg.I.no(i).ezInTime_I1) || //
                            !ZYPConstant.FLG_OFF_0.equals(bizMsg.I.no(i).ezCancelFlag_I1.getValue())) {
                        continue;
                    }

                    wmsInbdTrxTMsg.clear();
                    ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsInbdTrxPk, bizMsg.I.no(i).wmsInbdTrxPk_I1);
                    wmsInbdTrxTMsg = (WMS_INBD_TRXTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(wmsInbdTrxTMsg);

                    if (wmsInbdTrxTMsg != null) {
                        EZDTBLAccessor.logicalRemove(wmsInbdTrxTMsg);

                        // when update error, message sets the CMsg
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxTMsg.getReturnCode())) {
                            bizMsg.setMessageInfo(NLGM0008E, new String[] {WMS_INBD_TRX //
                                    , DB_GLBL_CMPY_CD + DELIMITER_COLON + DB_WMS_INBD_TRX_PK //
                                    , wmsInbdTrxTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + wmsInbdTrxTMsg.wmsInbdTrxPk.getValue() });
                            return;
                        }
                        bizMsg.setMessageInfo(NLGM0073I, new String[] {DATA_VALUE_DELETE, //
                                REC_ID_ONLINE_KEY + DELIMITER_COLON + wmsInbdTrxTMsg.wmsRecId.getValue() });
                    }
                }
            }
        }
    }

    /**
     * To set All information of WMS_INBD_PO_HDR from WMS_INBD_PO_HDR
     * @param bizMsg NLGL0020CMsg
     * @return String created RWS_HDR record's RWS_NUM(PK)
     */
    private String setPoHeaderData(NLGL0020CMsg bizMsg, String strTrxSeq, String strPOIdNum, String wkWH, RWS_HDRTMsg fromRwsHdrTMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_WH_CD, bizMsg.whCd_02.getValue());
        ssmParam.put(DB_PARAM_RTL_WH_CD, bizMsg.rtlWhCd_01.getValue());
        ssmParam.put(DB_PARAM_PO_ID, bizMsg.wmsPoId_E1.getValue());
        ssmParam.put(DB_PARAM_SQ_NUM, bizMsg.G.no(0).wmsSqNum_G2);

        S21SsmEZDResult dsPOHead = NLGL0020Query.getInstance().getPoHeaderData(ssmParam);

        List<Map> dsPOHeadList = (List<Map>) dsPOHead.getResultObject();

        RWS_HDRTMsg insertTMsg = new RWS_HDRTMsg();
        WMS_INBD_PO_HDRTMsg wmsPoHdrTMsg = new WMS_INBD_PO_HDRTMsg();

        for (int i = 0; i < dsPOHeadList.size(); i++) {
            String rtlWh = null;
            Map listData = dsPOHeadList.get(i);
            //Create RWS_HDR data
            ZYPEZDItemValueSetter.setValue(fromRwsHdrTMsg.rwsNum, (String)listData.get(DB_RWS_NUM));
            ZYPEZDItemValueSetter.setValue(fromRwsHdrTMsg.glblCmpyCd,(String)listData.get(GLBL_CMPY_CD));
            fromRwsHdrTMsg = (RWS_HDRTMsg) EZDTBLAccessor.findByKey(fromRwsHdrTMsg);

            if (fromRwsHdrTMsg != null) {
                EZDMsg.copy(fromRwsHdrTMsg, null, insertTMsg, null);
                ZYPEZDItemValueSetter.setValue(insertTMsg.rwsNum,ZYPNumbering.getUniqueID(getGlobalCompanyCode(), NLXSceConst.RWS_ONLINE_KEY));
                ZYPEZDItemValueSetter.setValue(insertTMsg.whCd, bizMsg.rtlWhCd_E1.getValue());
                ZYPEZDItemValueSetter.setValue(insertTMsg.wmsDropStsCd, DATA_VALUE_0);
                ZYPEZDItemValueSetter.setValue(insertTMsg.rwsRefNum, strPOIdNum);
                ZYPEZDItemValueSetter.setValue(insertTMsg.svcConfigMstrPk, bizMsg.svcConfigMstrPk_E1.getValue());
            }

            EZDTBLAccessor.create(insertTMsg);

            // when Insert error, message sets the CMsg
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NLGM0008E, new String[] {TBL_RWS_HDR //
                        , DB_GLBL_CMPY_CD + DELIMITER_COLON + DB_RWS_NUM //
                        , insertTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + insertTMsg.rwsNum.getValue() });
                return null;
            }

            if (DATA_VALUE_2.equals(bizMsg.xxSrchRqstDtTpCd_E2.getValue())) {
                // logical detele Hdr
                wmsPoHdrTMsg.clear();
                ZYPEZDItemValueSetter.setValue(wmsPoHdrTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(wmsPoHdrTMsg.whCd, bizMsg.whCd_02.getValue());
                ZYPEZDItemValueSetter.setValue(wmsPoHdrTMsg.wmsSqNum, bizMsg.G.no(0).wmsSqNum_G2);
                wmsPoHdrTMsg = (WMS_INBD_PO_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(wmsPoHdrTMsg);

                if (wmsPoHdrTMsg != null) {
                    EZDTBLAccessor.logicalRemove(wmsPoHdrTMsg);
                    // when update error, message sets the CMsg
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsPoHdrTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NLGM0008E, new String[] {WMS_INBD_TRX //
                                , DB_GLBL_CMPY_CD + DELIMITER_COLON + DB_WMS_SQ_NUM //
                                , wmsPoHdrTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + wmsPoHdrTMsg.wmsSqNum.getValue() });
                        return null;
                    }
                }
            }
        }
        return insertTMsg.rwsNum.getValue();
    }

    /**
     * To set All information of WMS_INBD_PO_HDR from WMS_INBD_PO_HDR
     * @param bizMsg NLGL0020CMsg
     */
    private void setPoDetailData(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg, String strTrxSeq, String strPOIdNum, String wkWH, String newRwsNum, RWS_HDRTMsg fromRwsHdrTMsg) {

        // Create RWS_DTL records.
        RWS_DTLTMsg fromRwsDtlTMsg = new RWS_DTLTMsg();
        RWS_DTLTMsg prevLineRwsDtlTMsg = new RWS_DTLTMsg();

        fromRwsDtlTMsg.setSQLID("001");
        fromRwsDtlTMsg.setConditionValue("glblCmpyCd01", fromRwsHdrTMsg.glblCmpyCd.getValue());
        fromRwsDtlTMsg.setConditionValue("rwsNum01", fromRwsHdrTMsg.rwsNum.getValue());

        RWS_DTLTMsgArray fromRwsDtlTMsgArray = (RWS_DTLTMsgArray) EZDTBLAccessor.findByCondition(fromRwsDtlTMsg);

        prevLineRwsDtlTMsg = copyFromExistsRwsDtlRecord(bizMsg, newRwsNum, prevLineRwsDtlTMsg, fromRwsDtlTMsgArray);

        // Create RWS_SER records.
        RWS_SERTMsg fromRwsSerTMsg = new RWS_SERTMsg();

        fromRwsSerTMsg.setSQLID("002");
        fromRwsSerTMsg.setConditionValue("glblCmpyCd01", fromRwsHdrTMsg.glblCmpyCd.getValue());
        fromRwsSerTMsg.setConditionValue("rwsNum01", fromRwsHdrTMsg.rwsNum.getValue());

        RWS_SERTMsgArray fromRwsSerTMsgArray = (RWS_SERTMsgArray) EZDTBLAccessor.findByCondition(fromRwsSerTMsg);

        if (fromRwsSerTMsgArray.length() > 0) {
            copyFromExistsRwsSerRecord(bizMsg, newRwsNum, fromRwsSerTMsgArray);
        }
        // create additional line.
        if (bizMsg.F.getValidCount() > fromRwsDtlTMsgArray.length()) {
            createNewRwsRecords(bizMsg, newRwsNum, prevLineRwsDtlTMsg, fromRwsDtlTMsgArray);
        }

        WMS_INBD_PO_DTLTMsg wmsPoDtlTMsg = new WMS_INBD_PO_DTLTMsg();
        for (int i = 0; i < fromRwsDtlTMsgArray.length(); i++) {
            fromRwsDtlTMsg = fromRwsDtlTMsgArray.no(i);
            // copy = "1",resend = "2"
            if (DATA_VALUE_2.equals(bizMsg.xxSrchRqstDtTpCd_E2.getValue())) {
                // logical detele Dtl
                wmsPoDtlTMsg.clear();
                ZYPEZDItemValueSetter.setValue(wmsPoDtlTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(wmsPoDtlTMsg.whCd, bizMsg.whCd_02.getValue());
                ZYPEZDItemValueSetter.setValue(wmsPoDtlTMsg.wmsSqNum, bizMsg.G.no(0).wmsSqNum_G2);
                ZYPEZDItemValueSetter.setValue(wmsPoDtlTMsg.wmsLineNum, new BigDecimal(fromRwsDtlTMsg.rwsDtlLineNum.getValue().replaceAll("^0+", "")));
                wmsPoDtlTMsg = (WMS_INBD_PO_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(wmsPoDtlTMsg);

                if (wmsPoDtlTMsg != null) {
                    EZDTBLAccessor.logicalRemove(wmsPoDtlTMsg);
                    // when update error, message sets the CMsg
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsPoDtlTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NLGM0008E, new String[] {WMS_INBD_TRX //
                                , DB_GLBL_CMPY_CD + DELIMITER_COLON + DB_WMS_SQ_NUM //
                                , wmsPoDtlTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + wmsPoDtlTMsg.wmsSqNum.getValue() });
                        return;
                    }
                }
            }
        }
    }

    private void createNewRwsRecords(NLGL0020CMsg bizMsg, String newRwsNum, RWS_DTLTMsg prevLineRwsDtlTMsg, RWS_DTLTMsgArray fromRwsDtlTMsgArray) {
        for (int k = fromRwsDtlTMsgArray.length(); k < bizMsg.F.getValidCount(); k++) {
            NLGL0020_FCMsg fromFMsg = bizMsg.F.no(k);

            // create additonal RWS_DTL record
            RWS_DTLTMsg newRwsDtlTMsg = new RWS_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.rwsNum, newRwsNum);
            ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.rwsDtlLineNum, fromFMsg.wmsLineNum_F1.getValue().toPlainString());
            ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.trxLineNum, fromFMsg.wmsLineNum_F1.getValue().toPlainString());
            ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.mdseCd, fromFMsg.wmsMdseCd_F1.getValue());
            if (ZYPCommonFunc.hasValue(fromFMsg.wmsStkStsCd_F2.getValue())) {
                ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.invtyStkStsCd, fromFMsg.wmsStkStsCd_F2.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.invtyStkStsCd, DATA_VALUE_1);
            }
            ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.rwsQty, decUOM);
            ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.rwsPutAwayQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.whInEtaDt, prevLineRwsDtlTMsg.whInEtaDt.getValue());
            ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.rwsStsCd, DATA_VALUE_10);
            ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.rtlWhCd, bizMsg.rtlWhCd_E1.getValue());
            String searchRtlSwhKey = "";
            if (ZYPConstant.FLG_ON_Y.equals(ZYPCodeDataUtil.getVarCharConstValue(WMS_PACK_CD_SET_OWNER_CD_FLG, getGlobalCompanyCode()))) {
                searchRtlSwhKey = bizMsg.invtyOwnrCd_E1.getValue();
            } else {
                searchRtlSwhKey = bizMsg.rtlWhCd_E1.getValue();
            }
            if (fromFMsg.packCdTxt_F1.getValue().startsWith(searchRtlSwhKey)) {
                ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.rtlSwhCd, fromFMsg.packCdTxt_F1.getValue().substring(searchRtlSwhKey.length()));
            }
            RTL_SWHTMsg rtlSwhTMsg = new RTL_SWHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlWhCd, bizMsg.rtlWhCd_E1.getValue());
            ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlSwhCd, newRwsDtlTMsg.rtlSwhCd.getValue());
            ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.glblCmpyCd, newRwsDtlTMsg.glblCmpyCd.getValue());
            rtlSwhTMsg = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(rtlSwhTMsg);
            if (rtlSwhTMsg != null) {
                ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.invtyLocCd, rtlSwhTMsg.invtyLocCd.getValue());
            }
            ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.shipFromRtlWhCd, prevLineRwsDtlTMsg.shipFromRtlWhCd.getValue());
            ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.shipFromRtlSwhCd, prevLineRwsDtlTMsg.shipFromRtlSwhCd.getValue());
            ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.shipFromInvtyLocCd, prevLineRwsDtlTMsg.shipFromInvtyLocCd.getValue());
            ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.prchReqNum, prevLineRwsDtlTMsg.prchReqLineNum.getValue());
            if (ZYPCommonFunc.hasValue(fromFMsg.usrCdRefTxt_F1)) {
                ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.thirdPtyDspTpCd, fromFMsg.usrCdRefTxt_F1.getValue());
            }

            SCE_ORD_TPTMsg sceOrdTypeTMsg = new SCE_ORD_TPTMsg();
            ZYPEZDItemValueSetter.setValue(sceOrdTypeTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(sceOrdTypeTMsg.sceOrdTpCd, bizMsg.B.no(0).sceOrdTpCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(sceOrdTypeTMsg.inbdOtbdCd, DATA_VALUE_1);
            sceOrdTypeTMsg = (SCE_ORD_TPTMsg) EZDTBLAccessor.findByKey(sceOrdTypeTMsg);
            if (sceOrdTypeTMsg != null && ZYPConstant.FLG_ON_Y.equals(sceOrdTypeTMsg.serNumTakeFlg)) {
                MDSETMsg mdseTMsg = new MDSETMsg();
                ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, fromFMsg.wmsMdseCd_F1.getValue());
                mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);
                if (("RT".equals(bizMsg.B.no(0).sceOrdTpCd_B1.getValue()) || "DT".equals(bizMsg.B.no(0).sceOrdTpCd_B1.getValue()) || "TR".equals(bizMsg.B.no(0).sceOrdTpCd_B1.getValue())
                        || "KC".equals(bizMsg.B.no(0).sceOrdTpCd_B1.getValue()) || "RP".equals(bizMsg.B.no(0).sceOrdTpCd_B1.getValue()))
                        && ZYPConstant.FLG_ON_Y.equals(mdseTMsg.shpgSerTakeFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.serNumTakeFlg, ZYPConstant.FLG_ON_Y);
                } else if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.rcvSerTakeFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.serNumTakeFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.serNumTakeFlg, ZYPConstant.FLG_OFF_N);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.serNumTakeFlg, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPCommonFunc.hasValue(fromFMsg.serApvlReqFlg_F1)) {
                ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.serApvlReqFlg, fromFMsg.serApvlReqFlg_F1.getValue());
            }
            ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.carrCd, prevLineRwsDtlTMsg.carrCd.getValue());
            ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.techMeetTruckFlg, ZYPConstant.FLG_OFF_N);

            EZDTBLAccessor.create(newRwsDtlTMsg);

            // when Insert error, message sets the CMsg
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newRwsDtlTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NLGM0008E, new String[] {TBL_RWS_DTL //
                        , DB_GLBL_CMPY_CD + DELIMITER_COLON + DB_RWS_NUM + DELIMITER_COLON + DB_RWS_DTL_LINE_NUM //
                        , newRwsDtlTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + newRwsDtlTMsg.rwsNum.getValue() + DELIMITER_COLON + newRwsDtlTMsg.rwsDtlLineNum.getValue() });
            }
            // create RWS_SER when serial info is set.
            if (ZYPCommonFunc.hasValue(fromFMsg.serNum_F1)) {
                RWS_SERTMsg newRwsSerTMsg = new RWS_SERTMsg();
                ZYPEZDItemValueSetter.setValue(newRwsSerTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(newRwsSerTMsg.rwsNum, newRwsNum);
                ZYPEZDItemValueSetter.setValue(newRwsSerTMsg.rwsLineNum, fromFMsg.wmsLineNum_F1.getValue().toPlainString());
                ZYPEZDItemValueSetter.setValue(newRwsSerTMsg.serNum, fromFMsg.serNum_F1.getValue());
                ZYPEZDItemValueSetter.setValue(newRwsSerTMsg.mdseCd, fromFMsg.wmsMdseCd_F1.getValue());
                ZYPEZDItemValueSetter.setValue(newRwsSerTMsg.putAwayChkStsCd, DATA_VALUE_0);
                ZYPEZDItemValueSetter.setValue(newRwsSerTMsg.serNumSendFlg, ZYPConstant.FLG_OFF_N);

                EZDTBLAccessor.create(newRwsSerTMsg);

                // when Insert error, message sets the CMsg
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newRwsSerTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NLGM0008E, new String[] {
                            TBL_RWS_DTL //
                            ,
                            DB_GLBL_CMPY_CD + DELIMITER_COLON + DB_RWS_NUM + DELIMITER_COLON + DB_RWS_LINE_NUM + DELIMITER_COLON + DB_SER_NUM + DELIMITER_COLON + DB_MDSE_CD//
                            ,
                            newRwsSerTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + newRwsSerTMsg.rwsNum.getValue() + DELIMITER_COLON + newRwsSerTMsg.rwsLineNum.getValue() + DELIMITER_COLON + newRwsSerTMsg.serNum.getValue()
                                    + DELIMITER_COLON + newRwsSerTMsg.mdseCd.getValue() });
                }
            }
        }
    }

    private void copyFromExistsRwsSerRecord(NLGL0020CMsg bizMsg, String newRwsNum, RWS_SERTMsgArray fromRwsSerTMsgArray) {
        RWS_SERTMsg fromRwsSerTMsg;
        for (int i = 0; i < fromRwsSerTMsgArray.length(); i++) {
            RWS_SERTMsg newRwsSerTMsg = new RWS_SERTMsg();
            fromRwsSerTMsg = fromRwsSerTMsgArray.no(i);

            EZDMsg.copy(fromRwsSerTMsg, null, newRwsSerTMsg, null);

            ZYPEZDItemValueSetter.setValue(newRwsSerTMsg.rwsNum, newRwsNum);

            if (bizMsg.F.getValidCount() > 0) {
                NLGL0020_FCMsg fromFMsg = new NLGL0020_FCMsg();
                for (int j = 0; j < bizMsg.F.getValidCount(); j++) {
                    // match LineNum
                    if (bizMsg.F.no(j).wmsLineNum_F1.getValueInt() -1 == i) {
                        fromFMsg = bizMsg.F.no(j);
                        break;
                    }
                }
                if (ZYPCommonFunc.hasValue(fromFMsg.wmsMdseCd_F1)) {
                    ZYPEZDItemValueSetter.setValue(newRwsSerTMsg.mdseCd, fromFMsg.wmsMdseCd_F1.getValue());
                }
                if (ZYPCommonFunc.hasValue(fromFMsg.serNum_F1)) {
                    ZYPEZDItemValueSetter.setValue(newRwsSerTMsg.serNum, fromFMsg.serNum_F1.getValue());
                }
            }

            EZDTBLAccessor.create(newRwsSerTMsg);

            // when Insert error, message sets the CMsg
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newRwsSerTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NLGM0008E, new String[] {
                        TBL_RWS_DTL //
                        ,
                        DB_GLBL_CMPY_CD + DELIMITER_COLON + DB_RWS_NUM + DELIMITER_COLON + DB_RWS_LINE_NUM + DELIMITER_COLON + DB_SER_NUM + DELIMITER_COLON + DB_MDSE_CD//
                        ,
                        newRwsSerTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + newRwsSerTMsg.rwsNum.getValue() + DELIMITER_COLON + newRwsSerTMsg.rwsLineNum.getValue() + DELIMITER_COLON + newRwsSerTMsg.serNum.getValue()
                                + DELIMITER_COLON + newRwsSerTMsg.mdseCd.getValue() });
            }
        }
    }

    private RWS_DTLTMsg copyFromExistsRwsDtlRecord(NLGL0020CMsg bizMsg, String newRwsNum, RWS_DTLTMsg prevLineRwsDtlTMsg, RWS_DTLTMsgArray fromRwsDtlTMsgArray) {
        RWS_DTLTMsg fromRwsDtlTMsg;
        for (int i = 0; i < fromRwsDtlTMsgArray.length(); i++) {
            RWS_DTLTMsg newRwsDtlTMsg = new RWS_DTLTMsg();
            fromRwsDtlTMsg = fromRwsDtlTMsgArray.no(i);

            EZDMsg.copy(fromRwsDtlTMsg, null, newRwsDtlTMsg, null);
            ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.rwsNum, newRwsNum);
            ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.rtlWhCd, bizMsg.rtlWhCd_E1.getValue());
            RTL_SWHTMsg rtlSwhTMsg = new RTL_SWHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlWhCd, bizMsg.rtlWhCd_E1.getValue());
            ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlSwhCd, fromRwsDtlTMsg.rtlSwhCd.getValue());
            ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.glblCmpyCd, fromRwsDtlTMsg.glblCmpyCd.getValue());
            rtlSwhTMsg = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(rtlSwhTMsg);
            if (rtlSwhTMsg != null) {
                ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.invtyLocCd, rtlSwhTMsg.invtyLocCd.getValue());
            }

            if (bizMsg.F.getValidCount() > 0) {
                NLGL0020_FCMsg fromFMsg = new NLGL0020_FCMsg();
                for (int j = 0; j < bizMsg.F.getValidCount(); j++) {
                    // match LineNum
                    if (bizMsg.F.no(j).wmsLineNum_F1.getValueInt() - 1 == i) {
                        fromFMsg = bizMsg.F.no(j);
                        break;
                    }
                }
                if (ZYPCommonFunc.hasValue(fromFMsg.wmsMdseCd_F1)) {
                    ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.mdseCd, fromFMsg.wmsMdseCd_F1.getValue());
                }
                if (ZYPCommonFunc.hasValue(fromFMsg.wmsStkStsCd_F2)) {
                    ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.invtyStkStsCd, fromFMsg.wmsStkStsCd_F2.getValue());
                }

                if (ZYPCommonFunc.hasValue(fromFMsg.xxChkBox_F9) && ZYPConstant.FLG_ON_Y.equals(fromFMsg.xxChkBox_F9.getValue())) {
                    ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.rwsQty, fromFMsg.rwsQty_F1.getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.rwsQty, decUOM);
                }
                String searchRtlSwhKey = "";
                if (ZYPConstant.FLG_ON_Y.equals(ZYPCodeDataUtil.getVarCharConstValue(WMS_PACK_CD_SET_OWNER_CD_FLG, getGlobalCompanyCode()))) {
                    searchRtlSwhKey = bizMsg.invtyOwnrCd_E1.getValue();
                } else {
                    searchRtlSwhKey = bizMsg.rtlWhCd_E1.getValue();
                }
                if (fromFMsg.packCdTxt_F1.getValue().startsWith(searchRtlSwhKey)) {
                    ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.rtlSwhCd, fromFMsg.packCdTxt_F1.getValue().substring(searchRtlSwhKey.length()));
                }
                if (ZYPCommonFunc.hasValue(fromFMsg.usrCdRefTxt_F1)) {
                    ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.thirdPtyDspTpCd, fromFMsg.usrCdRefTxt_F1.getValue());
                }
                if (ZYPCommonFunc.hasValue(fromFMsg.serApvlReqFlg_F1)) {
                    ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.serApvlReqFlg, fromFMsg.serApvlReqFlg_F1.getValue());
                }
                ZYPEZDItemValueSetter.setValue(newRwsDtlTMsg.techMeetTruckFlg, ZYPConstant.FLG_OFF_N);
            }
            EZDTBLAccessor.create(newRwsDtlTMsg);

            // when Insert error, message sets the CMsg
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newRwsDtlTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NLGM0008E, new String[] {TBL_RWS_DTL //
                        , DB_GLBL_CMPY_CD + DELIMITER_COLON + DB_RWS_NUM + DELIMITER_COLON + DB_RWS_DTL_LINE_NUM //
                        , newRwsDtlTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + newRwsDtlTMsg.rwsNum.getValue() + DELIMITER_COLON + newRwsDtlTMsg.rwsDtlLineNum.getValue() });
            }
            prevLineRwsDtlTMsg = fromRwsDtlTMsg;
        }
        return prevLineRwsDtlTMsg;
    }

    /**
     * To set All information of WMS_INBD_PO_HDR from WMS_INBD_PO_HDR
     * @param bizMsg NLGL0020CMsg
     */
    private boolean setItemInputData(NLGL0020CMsg bizMsg, NLGL0020SMsg globalMsg, String strTrxSeq, String strPOIdNum) {

        for (int i = 0; i < bizMsg.F.getValidCount(); i++) {
            WMS_INBD_RWS_WRKTMsg insertTMsg = new WMS_INBD_RWS_WRKTMsg();
            String stkSTSwk = bizMsg.F.no(i).wmsStkStsCd_F2.getValue();
            decUOM = getItemInputinfo(bizMsg, strTrxSeq, strPOIdNum, i);

            if (decUOM == BigDecimal.ZERO) {
                bizMsg.F.no(i).wmsMdseCd_F1.setErrorInfo(1, NLGM0077E);
                bizMsg.setMessageInfo(NLGM0077E);
                return false;
            }
        }
        return true;
    }

    /**
     * To get Item Input information information of WMS_INBD_PO_HDR
     * from WMS_INBD_PO_HDR
     * @param bizMsg NLGL0020CMsg
     */
    private BigDecimal getItemInputinfo(NLGL0020CMsg bizMsg, String strTrxSeq, String strPOIdNum, int rowCnt) {

        BigDecimal resltQty = BigDecimal.ZERO;

        if (bizMsg.F.getValidCount() == 0) {
            bizMsg.setMessageInfo(NLGM0035E);
            return resltQty;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_MDSE_CD, bizMsg.F.no(rowCnt).wmsMdseCd_F1.getValue());
        S21SsmEZDResult dsMdseCNT = NLGL0020Query.getInstance().getMDSECount(ssmParam);

        List<Map> dsMdseCntList = (List<Map>) dsMdseCNT.getResultObject();
        if (dsMdseCntList.isEmpty()) {
            // MDSE does not exist.
            bizMsg.setMessageInfo(NLGM0057E);
            return resltQty;
        }

        ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_WH_CD, bizMsg.whCd_E2.getValue());
        ssmParam.put(DB_PARAM_MDSE_CD, bizMsg.F.no(rowCnt).wmsMdseCd_F1.getValue());
        ssmParam.put(DB_PARAM_UOM_CD, bizMsg.F.no(rowCnt).wmsUomCd_F2.getValue());
        S21SsmEZDResult dsMdse = NLGL0020Query.getInstance().getMDSEData(ssmParam);

        List<Map> dsMdseList = (List<Map>) dsMdse.getResultObject();
        if (dsMdseList.isEmpty()) {
            resltQty = (bizMsg.F.no(rowCnt).rwsQty_F1.getValue());
            return resltQty; // Default value
        }

        for (int i = 0; i < dsMdseList.size(); i++) {
            Map listData = dsMdseList.get(i);

            if (listData.size() == DATA_VALUE_INT_0) {
                bizMsg.setMessageInfo(NLGM0060E, new String[] {DATA_VALUE_QTY_UNIT });
                resltQty = (bizMsg.F.no(rowCnt).rwsQty_F1.getValue());
                return resltQty;
            }
            resltQty = ((BigDecimal) listData.get(DB_WMS_BASE_UOM_QTY)).multiply((bizMsg.F.no(rowCnt).rwsQty_F1.getValue()));

            if (resltQty.compareTo(DATA_VALUE_DEC_999999999) == 1) {
                bizMsg.setMessageInfo(NLGM0060E, new String[] {DATA_VALUE_QTY_UNIT });
                return resltQty;
            }

            if (resltQty == BigDecimal.ZERO) {
                bizMsg.setMessageInfo(NLGM0060E, new String[] {DATA_VALUE_QTY_UNIT });
                return resltQty;
            }
        }
        return resltQty;
    }

    /**
     * To set All information of WMS_INBD_PO_HDR from
     * WMS_INBD_PO_HDR(Upload Edit Tab)
     * @param bizMsg NLGL0020CMsg
     */
    private void setWmsInbdTrx(NLGL0020CMsg bizMsg) {

        WMS_INBD_TRXTMsg wmsInbdTrxTMsg = new WMS_INBD_TRXTMsg();
        SCE_ORD_TPTMsg sceOrdTpTMsg = null;
        for (int i = 0; i < bizMsg.I.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.I.no(i).xxChkBox_I1.getValue())) {
                continue;
            }

            wmsInbdTrxTMsg.clear();
            BigDecimal wmsInbdTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsInbdTrxPk, wmsInbdTrxPk);
            if (ZYPCommonFunc.hasValue(bizMsg.I.no(i).ezInTime_I1)) {
                ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsUpdHistNum, NLXC014001.nullToZero(bizMsg.I.no(i).wmsUpdHistNum_I1.getValue()).add(BigDecimal.ONE));
            } else {
                ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsUpdHistNum, BigDecimal.ZERO);
            }
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsDmgFlg, DATA_VALUE_N);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOldDmgFlg, DATA_VALUE_N);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.ordLineCpltFlg, DATA_VALUE_N);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.packMarkSeptFlg, DATA_VALUE_N);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.whCd, bizMsg.whCd_02.getValue());
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcProcStsCd, ZYPConstant.FLG_ON_1);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.procStsCd, PROC_STS.IN_COMPLETED);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsRecId, ZYPNumbering.getUniqueID(REC_ID_ONLINE_KEY) + DATA_VALUE_0);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsTaskCd, bizMsg.I.no(i).wmsTaskCd_I2.getValue());
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.inbdOrdLineNum, bizMsg.I.no(i).inbdOrdLineNum_I1.getValue());
//            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.inbdOrdTpCd, bizMsg.I.no(i).wmsOrdTpCd_I2.getValue());
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.inbdOrdNum, bizMsg.wmsPoId_I1.getValue());
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsTrxDtTmTs, ZYPDateUtil.getCurrentSystemTime(YYYYMMDDHHMMSS_BEFORE));
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsMdseCd, bizMsg.I.no(i).wmsMdseCd_I1.getValue());
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsStkStsCd, bizMsg.I.no(i).wmsStkStsCd_I2.getValue());
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOrdQty, bizMsg.I.no(i).wmsOrdQty_I1.getValue());
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsPkgCd, bizMsg.I.no(i).packCdTxt_I1.getValue());
            // QC#19750 Mod.
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.serNum, bizMsg.I.no(i).serNum_I1);
            if (ZYPCommonFunc.hasValue(bizMsg.I.no(i).sceOrdTpCd_I2)) {
            	sceOrdTpTMsg = new SCE_ORD_TPTMsg();
            	ZYPEZDItemValueSetter.setValue(sceOrdTpTMsg.glblCmpyCd, getGlobalCompanyCode());
            	ZYPEZDItemValueSetter.setValue(sceOrdTpTMsg.sceOrdTpCd, bizMsg.I.no(i).sceOrdTpCd_I2);
            	ZYPEZDItemValueSetter.setValue(sceOrdTpTMsg.inbdOtbdCd, INBD_OTBD.INBOUND);
            	SCE_ORD_TPTMsg outMsg = (SCE_ORD_TPTMsg) EZDTBLAccessor.findByKey(sceOrdTpTMsg);
            	if (outMsg != null && ZYPCommonFunc.hasValue(outMsg.s80OrdTpCd)) {
            		ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.inbdOrdTpCd, outMsg.s80OrdTpCd);
            	}
            }

            EZDTBLAccessor.create(wmsInbdTrxTMsg);

            // when Insert error, message sets the CMsg
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NLGM0008E, new String[] {WMS_INBD_TRX //
                        , DB_GLBL_CMPY_CD + DELIMITER_COLON + DB_WMS_INBD_TRX_PK //
                        , wmsInbdTrxTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + wmsInbdTrxTMsg.wmsInbdTrxPk.getValue() });
            }
            updOrderCloseDate(bizMsg, i);

            if (ZYPCommonFunc.hasValue(bizMsg.I.no(i).ezInTime_I1)) {
                //delete original data
                wmsInbdTrxTMsg.clear();
                ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsInbdTrxPk, bizMsg.I.no(i).wmsInbdTrxPk_I1);
                wmsInbdTrxTMsg = (WMS_INBD_TRXTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(wmsInbdTrxTMsg);

                if (wmsInbdTrxTMsg != null) {
                    EZDTBLAccessor.logicalRemove(wmsInbdTrxTMsg);

                    // when update error, message sets the CMsg
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NLGM0008E, new String[] {WMS_INBD_TRX //
                                , DB_GLBL_CMPY_CD + DELIMITER_COLON + DB_WMS_INBD_TRX_PK //
                                , wmsInbdTrxTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + wmsInbdTrxTMsg.wmsInbdTrxPk.getValue() });
                        return;
                    }
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCntDplyFlg_01, ZYPConstant.FLG_ON_1);
    }
    /**
     * Update PO Order Close Date to Null.
     * @param wmsInbdTrxTMsg WMS Transaction Data
     * @param bizMsg NLGL0020CMsg
     * @param rowCnt Row number
     * @return error code
     */
    private void updOrderCloseDate(NLGL0020CMsg bizMsg, int rowCnt) {

        String wmsTaskCd = bizMsg.I.no(rowCnt).wmsTaskCd_I2.getValue();
        String inbdOrdTpCd = bizMsg.I.no(rowCnt).wmsOrdTpCd_I2.getValue();
        String inbdOrdNum = bizMsg.wmsPoId_I1.getValue();
        String whCd = bizMsg.whCd_02.getValue();

        boolean updProcFlg = false;
        if (WMS_TASK.PDLT.equals(wmsTaskCd) || WMS_TASK.PCLS.equals(wmsTaskCd) || WMS_TASK.PCFM.equals(wmsTaskCd)) {
            if (!(WMS_ORD_TP.INBOUND_ITEM_CHANGE.equals(inbdOrdTpCd) || WMS_ORD_TP.INBOUND_STOCK_STATUS_CHANGE.equals(inbdOrdTpCd))) {
                updProcFlg = true;
            }
        }
        if (updProcFlg) {
            // For not in(Order Type item Change(R) and stock
            // status
            // change(S)),
            // to update the close date, of WMS_INBD_PO_HDR.
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
            ssmParam.put(DB_PARAM_WH_CD, whCd);
            ssmParam.put(DB_PARAM_PO_ID, inbdOrdNum);
            S21SsmEZDResult poHdr = NLGL0020Query.getInstance().getWmsInbdPoHdr(ssmParam);
            List poHdrList = (List) poHdr.getResultObject();

            if (!poHdrList.isEmpty()) {
                WMS_INBD_PO_HDRTMsg wmsInbdPoHdrTMsg = (WMS_INBD_PO_HDRTMsg) poHdrList.get(DATA_VALUE_INT_0);
                if (ZYPCommonFunc.hasValue(wmsInbdPoHdrTMsg.wmsCloDtTmTs.getValue())) {
                    ZYPEZDItemValueSetter.setValue(wmsInbdPoHdrTMsg.wmsCloDtTmTs, BLANK);
                    EZDTBLAccessor.updateSelectionField(wmsInbdPoHdrTMsg, new String[] {KEY_WMS_CLO_DT_TM_TS });

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdPoHdrTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NLGM0008E, new String[] {wmsInbdPoHdrTMsg.getTableName() //
                                , GLBL_CMPY_CD + DELIMITER_COLON + WH_CD + DELIMITER_COLON + WMS_PO_ID //
                                , getGlobalCompanyCode() + DELIMITER_COLON + whCd + DELIMITER_COLON + inbdOrdNum });
                        return;
                    }
                }
            }
        }
    }
}
