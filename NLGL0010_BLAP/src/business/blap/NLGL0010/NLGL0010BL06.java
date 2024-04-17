/**
 * <pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0010;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLGL0010.common.NLGL0010CommonLogic;
import business.blap.NLGL0010.constant.NLGL0010Constant;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.RTL_SWHTMsg;
import business.db.SCE_ORD_TPTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_CUST_DTLTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SHPG_ORD_DTLTMsgArray;
import business.db.SHPG_ORD_MSGTMsg;
import business.db.SO_SERTMsg;
import business.db.WMS_INBD_SO_HDRTMsg;
import business.db.WMS_INBD_TRXTMsg;

import com.canon.cusa.s21.common.NLX.NLXC014001.NLXC014001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TASK;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_UOM;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * SO Mainenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/20/2013   CSAI            Y.Miyauchi      Create          MW Replace Initial
 * 05/31/2017   CITS            S.Endo          Update          RS#3168
 * 06/21/2017   CITS            S.Endo          Update          QC#19042
 * 06/30/2017   CITS            S.Endo          Update          QC#19645
 * 07/03/2017   CITS            S.Endo          Update          QC#19042
 * 07/05/2017   CITS            K.Ogino         Update          QC#18949
 *</pre>
 */
public class NLGL0010BL06 extends S21BusinessHandler implements NLGL0010Constant {
    /**
     * UOM Qty
     */
    private BigDecimal iUomQty = null;

    /**
     * Weight
     */
    private BigDecimal iWeight = null;

    /**
     * Volume
     */
    private BigDecimal iVolume = null;

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_CMN_Submit((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else if (EVENT_NM_CMN_DELETE.equals(screenAplID)) {
                doProcess_NLGL0010Scrn00_CMN_Delete((NLGL0010CMsg) cMsg, (NLGL0010SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[INIT] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_CMN_Submit(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        if (TAB_SO_DNLD_EDT.equals(cMsg.xxDplyTab.getValue())) {

            // When Submit Type = "Copy", create records.
            if (ZYPConstant.FLG_ON_1.equals(cMsg.xxTpCd_J2.getValue())) {
                if (validate(cMsg)) {
                    changeShpgOrdWmsDropFlg(cMsg);
                    String strShipOdr = "";
                    //String strTrxSeq = ZYPOracleSeqAccessor.getNumberString(SEQ_TO_WMS_DATA_IF_SQ, SEQ_TRX_DIGITS);
                    strShipOdr = NLGL0010CommonLogic.getTrxSeqByRadio(cMsg, getGlobalCompanyCode());
                    SHPG_ORDTMsg fromShpgOrdTMsg = new SHPG_ORDTMsg();
                    // insert SHPG_ORD
                    String fromShpgOrdSo;
                    fromShpgOrdTMsg = searchCopyTargetShpgOdr(cMsg, fromShpgOrdTMsg);

                    if (fromShpgOrdTMsg != null) {
                        fromShpgOrdSo = fromShpgOrdTMsg.soNum.getValue();
                        createShpgOrd(cMsg, strShipOdr, fromShpgOrdTMsg);

                        // insert SHPG_ORD_CUST_DTL
                        createShpgOrdCustDtls(cMsg, strShipOdr, fromShpgOrdSo);

                            // Create SHPG_ORD_DTL records.
                            SHPG_ORD_DTLTMsg fromShpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();
                            SHPG_ORD_DTLTMsg prevLineShpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();
                            fromShpgOrdDtlTMsg.setSQLID("001");
                            fromShpgOrdDtlTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
                            fromShpgOrdDtlTMsg.setConditionValue("soNum01", fromShpgOrdSo);

                            SHPG_ORD_DTLTMsgArray fromShpgOrdDtlTMsgArray = (SHPG_ORD_DTLTMsgArray) EZDTBLAccessor.findByCondition(fromShpgOrdDtlTMsg);
                            prevLineShpgOrdDtlTMsg = copyFromExistsShpgOrdDtlRecord(cMsg, strShipOdr, prevLineShpgOrdDtlTMsg, fromShpgOrdDtlTMsgArray);

                            // Create SO_SER records.
                            Map<String, Object> param = new HashMap<String, Object>();
                            param.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
                            param.put(DB_PARAM_SO_NUM, fromShpgOrdSo);

                            S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getSoSerPk(param);
                            if (!ssmResult.isCodeNotFound()) {
                                copyFromExistsSoSerRecord(cMsg, strShipOdr, (List<Map<String, Object>>) ssmResult.getResultObject());
                            }
                            //create additional line.
                            if (cMsg.K.getValidCount() > fromShpgOrdDtlTMsgArray.length()) {
                                createNewRecords(cMsg, strShipOdr, prevLineShpgOrdDtlTMsg, fromShpgOrdDtlTMsgArray);
                            }

                            //create SHPG_ORD_MSG
                            param = new HashMap<String, Object>();
                            param.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
                            param.put(DB_PARAM_SO_NUM, fromShpgOrdSo);
                            ssmResult = NLGL0010Query.getInstance().getShpgOrdMsgPk(param);
                            if (!ssmResult.isCodeNotFound()) {
                                createShpgOrdMsgReord(cMsg, strShipOdr, (List<Map<String, Object>>) ssmResult.getResultObject());
                            }
                    }
                    cMsg.setMessageInfo(NLGM0073I, new String[] {DATA_VALUE_INSERT, strShipOdr.trim() });
                }
            } else {
                changeShpgOrdWmsDropFlg(cMsg);
                cMsg.setMessageInfo(NLGM0073I, new String[] {DATA_VALUE_INSERT, "" });
            }
        } else {
            if (!NLGL0010CommonLogic.isErrorBeforUpdate(sMsg, cMsg)) {
                ZYPEZDItemValueSetter.setValue(sMsg.xxBtnFlg_01, ZYPConstant.FLG_ON_1);
                return;
            }
            if (!doProcess_Upload(cMsg, sMsg)) {
                return;
            }
            ZYPEZDItemValueSetter.setValue(sMsg.xxBtnFlg_01, ZYPConstant.FLG_OFF_0);
            cMsg.setMessageInfo(NLGM0025I, new String[] {DATA_VALUE_INSERT });
        }

    }

    private void createShpgOrdMsgReord(NLGL0010CMsg cMsg, String strShipOdr, List<Map<String, Object>> shpgOrdMsgPkList) {
        SHPG_ORD_MSGTMsg fromShpgOrdMsgTMsg;
        for (int i = 0; i < shpgOrdMsgPkList.size(); i++) {
            SHPG_ORD_MSGTMsg newShpgOrdMsgTMsg = new SHPG_ORD_MSGTMsg();
            fromShpgOrdMsgTMsg = new SHPG_ORD_MSGTMsg();

            ZYPEZDItemValueSetter.setValue(fromShpgOrdMsgTMsg.glblCmpyCd, (String) shpgOrdMsgPkList.get(i).get(DB_SEL_GLBL_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(fromShpgOrdMsgTMsg.soNum, (String) shpgOrdMsgPkList.get(i).get(DB_SO_NUM));
            ZYPEZDItemValueSetter.setValue(fromShpgOrdMsgTMsg.soMsgTpCd, (String) shpgOrdMsgPkList.get(i).get(DB_SO_MSG_TP_CD));
            ZYPEZDItemValueSetter.setValue(fromShpgOrdMsgTMsg.txtSqNum, (String) shpgOrdMsgPkList.get(i).get(DB_TXT_SQ_NUM));
            fromShpgOrdMsgTMsg = (SHPG_ORD_MSGTMsg) EZDTBLAccessor.findByKey(fromShpgOrdMsgTMsg);
            if (fromShpgOrdMsgTMsg != null) {
                EZDMsg.copy(fromShpgOrdMsgTMsg, null, newShpgOrdMsgTMsg, null);
                ZYPEZDItemValueSetter.setValue(newShpgOrdMsgTMsg.soNum, strShipOdr);

                EZDTBLAccessor.create(newShpgOrdMsgTMsg);
                // when Insert error, message sets the CMsg
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newShpgOrdMsgTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NLGM0008E, new String[] {
                            SHPG_ORD_MSG
                            , DB_SEL_GLBL_CMPY_CD + DELIMITER_COLON + DB_SO_NUM + DELIMITER_COLON + DB_SO_MSG_TP_CD + DB_TXT_SQ_NUM
                            , newShpgOrdMsgTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + newShpgOrdMsgTMsg.soNum.getValue() + DELIMITER_COLON + newShpgOrdMsgTMsg.soMsgTpCd.getValue() + DELIMITER_COLON + newShpgOrdMsgTMsg.txtSqNum.getValue()});
                }
            }
        }
    }

    private void createNewRecords(NLGL0010CMsg cMsg, String strShipOdr, SHPG_ORD_DTLTMsg prevLineShpgOrdDtlTMsg, SHPG_ORD_DTLTMsgArray fromShpgOrdDtlTMsgArray) {
        for (int k = fromShpgOrdDtlTMsgArray.length(); k < cMsg.K.getValidCount(); k++) {
            NLGL0010_KCMsg fromKMsg = cMsg.K.no(k);
            calculate(cMsg, k);

            //create additional SHPG_ORD_DTL record
            SHPG_ORD_DTLTMsg newShpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.soNum, strShipOdr);
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.soSlpNum, String.format("%03d", fromKMsg.wmsLineNum_K1.getValueInt()));
            if (ZYPCommonFunc.hasValue(fromKMsg.wmsStkStsCd_K2)) {
                ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.fromStkStsCd, fromKMsg.wmsStkStsCd_K2.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.fromStkStsCd, ZYPConstant.FLG_ON_1);
            }
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.toStkStsCd, fromKMsg.wmsStkStsCd_K4.getValue());

            MDSE_STORE_PKGTMsg mdseStorePkgTMsg = null;
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
            params.put(DB_PARAM_INVTY_OWNR_CD, cMsg.whCd_J2.getValue());
            S21SsmEZDResult result = NLGL0010Query.getInstance().get3PLWhCode(params);
            if (!result.isCodeNotFound()) {
                // 3PL
                mdseStorePkgTMsg = new MDSE_STORE_PKGTMsg();
                ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.mdseCd, fromKMsg.wmsMdseCd_K1.getValue());
                ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.pkgUomCd, "EA");
                mdseStorePkgTMsg = (MDSE_STORE_PKGTMsg) EZDTBLAccessor.findByKey(mdseStorePkgTMsg);
            }
            if (mdseStorePkgTMsg != null) {
                // 3PL
                ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.totShpgVol, mdseStorePkgTMsg.inInchVol.getValue().multiply(fromKMsg.wmsShipQty_K1.getValue()));
                ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.totShpgWt, mdseStorePkgTMsg.inPoundWt.getValue().multiply(fromKMsg.wmsShipQty_K1.getValue()));
                ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.shpgQty, fromKMsg.wmsShipQty_K1.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.totShpgVol, iVolume.multiply(fromKMsg.wmsShipQty_K1.getValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
                ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.totShpgWt, iWeight.multiply(fromKMsg.wmsShipQty_K1.getValue()));
                ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.shpgQty, iUomQty.multiply(fromKMsg.wmsShipQty_K1.getValue()));
            }
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.shpgPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.discPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.unitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.discUnitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.rqstOrdQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.shipQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.setShpgQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.shpgBalQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.shpgStsCd, SHPG_STS.S_OR_O_PRINTED);
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.batNumTakeFlg, ZYPConstant.FLG_OFF_N);
            //get SER_NUM_TAKE_FLG
            params = new HashMap<String, Object>();
            params.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
            params.put(DB_PARAM_SCE_ORD_TP_CD, cMsg.I.no(0).fill40Txt_I1.getValue());
            result = NLGL0010Query.getInstance().getSceOrdTypeSerNumTakeFlg(params);

            if (!result.isCodeNotFound() && ZYPConstant.FLG_ON_Y.equals((String) result.getResultObject())) {
                params = new HashMap<String, Object>();
                params.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
                params.put(DB_PARAM_MDSE_CD, fromKMsg.wmsMdseCd_K1.getValue());
                result = NLGL0010Query.getInstance().getMdseSerNumTakeFlg(params);
                if (!result.isCodeNotFound() && ZYPConstant.FLG_ON_Y.equals((String) result.getResultObject())) {
                    ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.serNumTakeFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.serNumTakeFlg, ZYPConstant.FLG_OFF_N);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.serNumTakeFlg, ZYPConstant.FLG_OFF_N);
            }
            if (mdseStorePkgTMsg == null) {
                mdseStorePkgTMsg = new MDSE_STORE_PKGTMsg();
                ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.mdseCd, fromKMsg.wmsMdseCd_K1.getValue());
                ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.pkgUomCd, fromKMsg.wmsUomCd_K2.getValue());
                mdseStorePkgTMsg = (MDSE_STORE_PKGTMsg) EZDTBLAccessor.findByKey(mdseStorePkgTMsg);
            }
            if (mdseStorePkgTMsg != null) {
                ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.inEachQty, mdseStorePkgTMsg.inEachQty.getValue());
            }
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.mdseCd, fromKMsg.wmsMdseCd_K1.getValue());
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.trxHdrNum, prevLineShpgOrdDtlTMsg.trxHdrNum.getValue());
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.expFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.sysSrcCd, prevLineShpgOrdDtlTMsg.sysSrcCd.getValue());
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.configItemFlg, fromKMsg.indConfigFlg_K1.getValue());
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.rtlWhCd, cMsg.rtlWhCd_01.getValue());

            String searchRtlSwhKey = "";
            if (ZYPConstant.FLG_ON_Y.equals(ZYPCodeDataUtil.getVarCharConstValue(WMS_PACK_CD_SET_OWNER_CD_FLG, getGlobalCompanyCode()))) {
                searchRtlSwhKey = cMsg.invtyOwnrCd_J1.getValue();
            } else {
                searchRtlSwhKey = cMsg.rtlWhCd_01.getValue();
            }
            if (fromKMsg.packCdTxt_K1.getValue().startsWith(searchRtlSwhKey)) {
                ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.rtlSwhCd, fromKMsg.packCdTxt_K1.getValue().substring(searchRtlSwhKey.length()));
            }
            RTL_SWHTMsg rtlSwhTMsg = new RTL_SWHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlWhCd, cMsg.rtlWhCd_01.getValue());
            ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlSwhCd, newShpgOrdDtlTMsg.rtlSwhCd.getValue());
            rtlSwhTMsg = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(rtlSwhTMsg);
            if (rtlSwhTMsg != null) {
                ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.rtlSwhCd, rtlSwhTMsg.rtlSwhCd.getValue());
                ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.invtyLocCd, rtlSwhTMsg.invtyLocCd.getValue());
            }
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.shipToRtlWhCd, prevLineShpgOrdDtlTMsg.shipToRtlWhCd.getValue());
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.shipToRtlSwhCd, prevLineShpgOrdDtlTMsg.shipToRtlSwhCd.getValue());
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.toInvtyLocCd, prevLineShpgOrdDtlTMsg.toInvtyLocCd.getValue());
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.dsSoLineStsCd, DS_SO_LINE_STS.ALLOCATED);
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.pickSvcConfigMstrPk, cMsg.svcConfigMstrPk_J1.getValue());
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.prchReqNum, prevLineShpgOrdDtlTMsg.prchReqNum.getValue());
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.svcMachProcStsCd, prevLineShpgOrdDtlTMsg.svcMachProcStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.preIstlShopRqstFlg, fromKMsg.indConfigFlg_K1.getValue());
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.rmvConfigFlg, fromKMsg.rmvConfigFlg_K1.getValue());
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.backOrdImpctTpCd, fromKMsg.backOrdImpctTpCd_K1.getValue());
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.pickConfQty, BigDecimal.ZERO);

            SCE_ORD_TPTMsg sceOrdTypeTMsg = new SCE_ORD_TPTMsg();
            ZYPEZDItemValueSetter.setValue(sceOrdTypeTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(sceOrdTypeTMsg.sceOrdTpCd, cMsg.I.no(0).fill40Txt_I1.getValue());
            ZYPEZDItemValueSetter.setValue(sceOrdTypeTMsg.inbdOtbdCd, "2");
            sceOrdTypeTMsg = (SCE_ORD_TPTMsg) EZDTBLAccessor.findByKey(sceOrdTypeTMsg);
            if (sceOrdTypeTMsg != null) {
                if (sceOrdTypeTMsg.sceOrdTpCd.getValue().equals("RS")) {
                    params = new HashMap<String, Object>();
                    params.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
                    params.put(DB_PARAM_SO_NUM, newShpgOrdDtlTMsg.soNum.getValue());
                    params.put(DB_PARAM_SO_SLP_NUM, newShpgOrdDtlTMsg.soSlpNum.getValue());
                    result = NLGL0010Query.getInstance().getDsOrdTpProcDfnFlg(params);
                    S21SsmEZDResult result2 = NLGL0010Query.getInstance().getOrdProcTpFlg(params);
                    S21SsmEZDResult result3 = NLGL0010Query.getInstance().getMdseFlg(params);

                    if (!result.isCodeNotFound() && ZYPConstant.FLG_ON_Y.equals((String) result.getResultObject())
                            && !result2.isCodeNotFound() && ZYPConstant.FLG_ON_Y.equals((String) result2.getResultObject())
                                    && !result3.isCodeNotFound() && ZYPConstant.FLG_ON_Y.equals((String) result3.getResultObject())) {
                                ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.svcMachMstrLocStsCd, "11");
                            } else {
                                ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.svcMachMstrLocStsCd, "");
                            }
                } else {
                    ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.svcMachMstrLocStsCd, sceOrdTypeTMsg.svcMachMstrLocStsCd.getValue());
                }
            }

            EZDTBLAccessor.create(newShpgOrdDtlTMsg);

            //when Insert error,message sets CMsg
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newShpgOrdDtlTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NLGM0008E, new String[] {SHPG_ORD_DTL, DB_SEL_GLBL_CMPY_CD + DELIMITER_COLON + SO_SLP_NUM + DELIMITER_COLON + DB_SO_NUM,
                        newShpgOrdDtlTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + newShpgOrdDtlTMsg.soSlpNum.getValue() + DELIMITER_COLON + newShpgOrdDtlTMsg.soNum.getValue() });
            }

            //create SO_SER when serial info is set.
            if (ZYPCommonFunc.hasValue(fromKMsg.serNum_K1)) {
                SO_SERTMsg newSoSerTMsg = new SO_SERTMsg();
                ZYPEZDItemValueSetter.setValue(newSoSerTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(newSoSerTMsg.soSerPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SO_SER_SQ));
                ZYPEZDItemValueSetter.setValue(newSoSerTMsg.soNum, strShipOdr);
                ZYPEZDItemValueSetter.setValue(newSoSerTMsg.soSlpNum, String.format("%03d", fromKMsg.wmsLineNum_K1.getValueInt()));
                ZYPEZDItemValueSetter.setValue(newSoSerTMsg.trxHdrNum, newShpgOrdDtlTMsg.trxHdrNum.getValue());
                ZYPEZDItemValueSetter.setValue(newSoSerTMsg.trxLineNum, newShpgOrdDtlTMsg.trxLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(newSoSerTMsg.trxLineSubNum, newShpgOrdDtlTMsg.trxLineSubNum.getValue());
                ZYPEZDItemValueSetter.setValue(newSoSerTMsg.serNum, fromKMsg.serNum_K1.getValue());
                ZYPEZDItemValueSetter.setValue(newSoSerTMsg.mdseCd, fromKMsg.wmsMdseCd_K1.getValue());
                ZYPEZDItemValueSetter.setValue(newSoSerTMsg.ordAsgFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(newSoSerTMsg.whPickFlg, ZYPConstant.FLG_OFF_N);

                EZDTBLAccessor.create(newSoSerTMsg);

                // when Insert error, message sets the CMsg
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newSoSerTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NLGM0008E, new String[] {
                            SO_SER
                            , DB_SEL_GLBL_CMPY_CD + DELIMITER_COLON + SO_SER_PK
                            , newSoSerTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + newSoSerTMsg.soSerPk.getValue()});
                }
            }
        }
    }

    private void copyFromExistsSoSerRecord(NLGL0010CMsg cMsg, String strShipOdr, List<Map<String, Object>> soSerPkList) {
        SO_SERTMsg fromSoSerTMsg;
        for (int i = 0; i < soSerPkList.size(); i++) {
            SO_SERTMsg newSoSerTMsg = new SO_SERTMsg();
            fromSoSerTMsg = new SO_SERTMsg();
            ZYPEZDItemValueSetter.setValue(fromSoSerTMsg.soSerPk, (BigDecimal) soSerPkList.get(i).get(SO_SER_PK));
            ZYPEZDItemValueSetter.setValue(fromSoSerTMsg.glblCmpyCd, (String) soSerPkList.get(i).get(DB_SEL_GLBL_CMPY_CD));
            fromSoSerTMsg = (SO_SERTMsg) EZDTBLAccessor.findByKey(fromSoSerTMsg);
            if (fromSoSerTMsg != null) {
                EZDMsg.copy(fromSoSerTMsg, null, newSoSerTMsg, null);

                ZYPEZDItemValueSetter.setValue(newSoSerTMsg.soSerPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SO_SER_SQ));
                ZYPEZDItemValueSetter.setValue(newSoSerTMsg.soNum, strShipOdr);

                if (cMsg.K.getValidCount() > 0) {
                    NLGL0010_KCMsg fromKMsg = new NLGL0010_KCMsg();
                    for (int j = 0; j < cMsg.K.getValidCount(); j++) {
                        //match LineNum
                        if (cMsg.K.no(j).wmsLineNum_K1.getValueInt() - 1 == i) {
                            fromKMsg = cMsg.K.no(j);
                            break;
                        }
                    }
                    if (ZYPCommonFunc.hasValue(fromKMsg.serNum_K1)) {
                        ZYPEZDItemValueSetter.setValue(newSoSerTMsg.serNum, fromKMsg.serNum_K1.getValue());
                    }
                    if (ZYPCommonFunc.hasValue(fromKMsg.wmsMdseCd_K1)) {
                        ZYPEZDItemValueSetter.setValue(newSoSerTMsg.mdseCd, fromKMsg.wmsMdseCd_K1.getValue());
                    }
                }
                EZDTBLAccessor.create(newSoSerTMsg);

                // when Insert error, message sets the CMsg
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newSoSerTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NLGM0008E, new String[] {
                            SO_SER
                            , DB_SEL_GLBL_CMPY_CD + DELIMITER_COLON + SO_SER_PK
                            , newSoSerTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + newSoSerTMsg.soSerPk.getValue()});
                }
            }
        }
    }

    private SHPG_ORD_DTLTMsg copyFromExistsShpgOrdDtlRecord(NLGL0010CMsg cMsg, String strShipOdr, SHPG_ORD_DTLTMsg prevLineShpgOrdDtlTMsg, SHPG_ORD_DTLTMsgArray fromShpgOrdDtlTMsgArray) {
        SHPG_ORD_DTLTMsg fromShpgOrdDtlTMsg;
        for (int i = 0; i < fromShpgOrdDtlTMsgArray.length(); i++) {
            SHPG_ORD_DTLTMsg newShpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();
            fromShpgOrdDtlTMsg = fromShpgOrdDtlTMsgArray.no(i);

            EZDMsg.copy(fromShpgOrdDtlTMsg, null, newShpgOrdDtlTMsg, null);
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.soNum, strShipOdr);
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.shpgPlnNum, "");
            ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.rtlWhCd, cMsg.rtlWhCd_01.getValue());
            RTL_SWHTMsg rtlSwhTMsg = new RTL_SWHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlWhCd, cMsg.rtlWhCd_01.getValue());
            ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlSwhCd, fromShpgOrdDtlTMsg.rtlSwhCd.getValue());
            rtlSwhTMsg = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(rtlSwhTMsg);
            if (rtlSwhTMsg != null) {
                ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.invtyLocCd, rtlSwhTMsg.invtyLocCd.getValue());
            }

            if (cMsg.K.getValidCount() > 0) {
                NLGL0010_KCMsg fromKMsg = new NLGL0010_KCMsg();
                for (int j = 0; j < cMsg.K.getValidCount(); j++) {
                    // match LineNum
                    if (cMsg.K.no(j).wmsLineNum_K1.getValueInt() - 1 == i) {
                        fromKMsg = cMsg.K.no(j);
                        calculate(cMsg, j);
                        break;
                    }
                }
                if (ZYPCommonFunc.hasValue(fromKMsg.wmsStkStsCd_K2)) {
                    ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.fromStkStsCd, fromKMsg.wmsStkStsCd_K2.getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.fromStkStsCd, ZYPConstant.FLG_ON_1);
                }
                ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.toStkStsCd, fromKMsg.wmsStkStsCd_K4.getValue());

                MDSE_STORE_PKGTMsg mdseStorePkgTMsg = null;
                Map<String, Object> params = new HashMap<String, Object>();
                params.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
                params.put(DB_PARAM_INVTY_OWNR_CD, cMsg.whCd_J2.getValue());
                S21SsmEZDResult result = NLGL0010Query.getInstance().get3PLWhCode(params);
                if (!result.isCodeNotFound()) {
                    // 3PL
                    mdseStorePkgTMsg = new MDSE_STORE_PKGTMsg();
                    ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.glblCmpyCd, fromShpgOrdDtlTMsg.glblCmpyCd.getValue());
                    if (ZYPCommonFunc.hasValue(fromKMsg.wmsMdseCd_K1)) {
                        ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.mdseCd, fromKMsg.wmsMdseCd_K1.getValue());
                    } else {
                        ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.mdseCd, fromShpgOrdDtlTMsg.mdseCd.getValue());
                    }
                    ZYPEZDItemValueSetter.setValue(mdseStorePkgTMsg.pkgUomCd, "EA");
                    mdseStorePkgTMsg = (MDSE_STORE_PKGTMsg) EZDTBLAccessor.findByKey(mdseStorePkgTMsg);
                }

                if (ZYPCommonFunc.hasValue(fromKMsg.wmsShipQty_K1)) {
                    if (mdseStorePkgTMsg != null) {
                        // 3PL
                        ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.totShpgVol, mdseStorePkgTMsg.inInchVol.getValue().multiply(fromKMsg.wmsShipQty_K1.getValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.totShpgWt, mdseStorePkgTMsg.inPoundWt.getValue().multiply(fromKMsg.wmsShipQty_K1.getValue()));
                        ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.shpgQty, fromKMsg.wmsShipQty_K1.getValue());
                    } else {
                        ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.totShpgVol, iVolume.multiply(fromKMsg.wmsShipQty_K1.getValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.totShpgWt, iWeight.multiply(fromKMsg.wmsShipQty_K1.getValue()));
                        ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.shpgQty, iUomQty.multiply(fromKMsg.wmsShipQty_K1.getValue()));
                    }
                }
                if (ZYPCommonFunc.hasValue(fromKMsg.wmsMdseCd_K1)) {
                    ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.mdseCd, fromKMsg.wmsMdseCd_K1.getValue());
                }
                String searchRtlSwhKey = "";
                if (ZYPConstant.FLG_ON_Y.equals(ZYPCodeDataUtil.getVarCharConstValue(WMS_PACK_CD_SET_OWNER_CD_FLG, getGlobalCompanyCode()))) {
                    searchRtlSwhKey = cMsg.invtyOwnrCd_J1.getValue();
                } else {
                    searchRtlSwhKey = cMsg.rtlWhCd_01.getValue();
                }
                if (fromKMsg.packCdTxt_K1.getValue().startsWith(searchRtlSwhKey)) {
                    ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.rtlSwhCd, fromKMsg.packCdTxt_K1.getValue().substring(searchRtlSwhKey.length()));
                }
                rtlSwhTMsg = new RTL_SWHTMsg();
                ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlWhCd, cMsg.rtlWhCd_01.getValue());
                ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlSwhCd, newShpgOrdDtlTMsg.rtlSwhCd.getValue());
                rtlSwhTMsg = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(rtlSwhTMsg);
                if (rtlSwhTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.invtyLocCd, rtlSwhTMsg.invtyLocCd.getValue());
                }
                if (ZYPCommonFunc.hasValue(fromKMsg.svcConfigMstrPk_K1)) {
                    ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.pickSvcConfigMstrPk, fromKMsg.svcConfigMstrPk_K1.getValue());
                }
                if (ZYPCommonFunc.hasValue(fromKMsg.indConfigFlg_K1)) {
                    ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.preIstlShopRqstFlg, fromKMsg.indConfigFlg_K1.getValue());
                }
                if (ZYPCommonFunc.hasValue(fromKMsg.rmvConfigFlg_K1)) {
                    ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.rmvConfigFlg, fromKMsg.rmvConfigFlg_K1.getValue());
                }
                if (ZYPCommonFunc.hasValue(fromKMsg.backOrdImpctTpCd_K1)) {
                    ZYPEZDItemValueSetter.setValue(newShpgOrdDtlTMsg.backOrdImpctTpCd, fromKMsg.backOrdImpctTpCd_K1.getValue());
                }

                EZDTBLAccessor.create(newShpgOrdDtlTMsg);
                // when Insert error, message sets the CMsg
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newShpgOrdDtlTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NLGM0008E, new String[] {SHPG_ORD_DTL, DB_SEL_GLBL_CMPY_CD + DELIMITER_COLON + SO_SLP_NUM + DELIMITER_COLON + DB_SO_NUM,
                            newShpgOrdDtlTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + newShpgOrdDtlTMsg.soSlpNum.getValue() + DELIMITER_COLON + newShpgOrdDtlTMsg.soNum.getValue() });
                }
                prevLineShpgOrdDtlTMsg = fromShpgOrdDtlTMsg;

            }
        }
        return prevLineShpgOrdDtlTMsg;
    }

    private void calculate(NLGL0010CMsg cMsg, int iItemCnt) {
        Map<String, Object> ssmParamBase = new HashMap<String, Object>();
        ssmParamBase.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParamBase.put(DB_PARAM_WH_CD, cMsg.whCd_J2.getValue());
        ssmParamBase.put(DB_PARAM_WMS_MDSE_CD, cMsg.K.no(iItemCnt).wmsMdseCd_K1.getValue());
        ssmParamBase.put(DB_PARAM_WMS_UOM_CD, cMsg.K.no(iItemCnt).wmsUomCd_K2.getValue());
        S21SsmEZDResult ssmResultBase = NLGL0010Query.getInstance().getItemBaseInfo(ssmParamBase);

        Map map = null;
        if (!ssmResultBase.isCodeNormal() && !WMS_UOM.EACH.equals(cMsg.K.no(iItemCnt).wmsUomCd_K2.getValue())) {
            cMsg.K.no(iItemCnt).wmsMdseCd_K1.setErrorInfo(1, NLGM0077E);
            cMsg.setMessageInfo(NLGM0077E);
            return;
        } else if (ssmResultBase.isCodeNormal()) {
            List<Map> list = (List<Map>) ssmResultBase.getResultObject();
            map = (Map) list.get(0);
        }
        if (map == null) {
            iUomQty = MDSE_EA_BASE_UOM_QTY;
            iWeight = MDSE_EA_BASE_WEIGHT;
            iVolume = MDSE_EA_BASE_VOLUME;
        } else {
            iUomQty = (BigDecimal) map.get(DB_WMS_BASE_UOM_QTY);
            iWeight = (BigDecimal) map.get(DB_WMS_MDSE_WT);
            iVolume = (BigDecimal) map.get(DB_WMS_MDSE_VOL);
        }
    }

    private SHPG_ORDTMsg searchCopyTargetShpgOdr(NLGL0010CMsg cMsg, SHPG_ORDTMsg fromShpgOrdTMsg) {
        String maxRowId = "";

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_WMS_SO_ID, cMsg.I.no(0).wmsSoId_I1.getValue());
        ssmParam.put(DB_PARAM_WH_CD, cMsg.whCd_J2.getValue());

        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getMaxRowIdOfShpgDrd(ssmParam);
        if (!ssmResult.isCodeNotFound()) {
            maxRowId = (String) ssmResult.getResultObject();
        }
        if (!ZYPCommonFunc.hasValue(maxRowId)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        param.put(DB_PARAM_ROWID, maxRowId);

        ssmResult = NLGL0010Query.getInstance().getShpgOrdKeyFromRowId(param);

        if (!ssmResult.isCodeNotFound()) {
            Map<String, Object> shpOrdKey = (Map<String, Object>) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(fromShpgOrdTMsg.soNum, (String) shpOrdKey.get(DB_SO_NUM));
            ZYPEZDItemValueSetter.setValue(fromShpgOrdTMsg.glblCmpyCd, (String) shpOrdKey.get(DB_SEL_GLBL_CMPY_CD));

            fromShpgOrdTMsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKey(fromShpgOrdTMsg);
        }
        return fromShpgOrdTMsg;
    }

    private void createShpgOrd(NLGL0010CMsg cMsg, String strShipOdr, SHPG_ORDTMsg fromShpgOrdTMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        S21SsmEZDResult ssmResult;
        Map<String, Object> param;
        SHPG_ORDTMsg insertTMsg = new SHPG_ORDTMsg();
        EZDMsg.copy(fromShpgOrdTMsg, null, insertTMsg, null);
        ZYPEZDItemValueSetter.setValue(insertTMsg.soNum, strShipOdr);
        ZYPEZDItemValueSetter.setValue(insertTMsg.whCd, cMsg.rtlWhCd_01.getValue());
        ZYPEZDItemValueSetter.setValue(insertTMsg.custIssPoNum, cMsg.I.no(0).custOrdNum_I1.getValue());

        param = new HashMap<String, Object>();
        param.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        param.put(DB_PARAM_WMS_FRT_OUT_CD, cMsg.wmsFrtOutCd_J2.getValue());

        ssmResult = NLGL0010Query.getInstance().getFlightTermsInfo(param);
        if (!ssmResult.isCodeNotFound()) {
            Map<String, Object> flightTermInfo = (Map<String, Object>) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(insertTMsg.frtChrgToCd, (String) flightTermInfo.get(FRT_CHRG_TO_CD));
            ZYPEZDItemValueSetter.setValue(insertTMsg.frtChrgMethCd, (String) flightTermInfo.get(FRT_CHRG_METH_CD));
        }
        if (!ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_J1) && ZYPCommonFunc.hasValue(cMsg.tplSvcLvlCd_J2)) {
            param = new HashMap<String, Object>();
            param.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
            param.put(DB_PARAM_INVTY_OWNR_CD, cMsg.whCd_J2.getValue());
            S21SsmEZDResult result = NLGL0010Query.getInstance().get3PLWhCode(param);
            if (!result.isCodeNotFound()) {
                // 3PL
                param = new HashMap<String, Object>();
                param.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
                param.put(DB_PARAM_SHIP_VIA_CD, cMsg.tplSvcLvlCd_J2.getValue());
                S21SsmEZDResult ssl = NLGL0010Query.getInstance().getRelatedSSL(ssmParam);
                if (!ssl.isCodeNotFound()) {
                    List<String> sslList = (List<String>) ssl.getResultObject();
                    ZYPEZDItemValueSetter.setValue(insertTMsg.shpgSvcLvlCd, sslList.get(0));
                } else {
                    // Normal
                    param = new HashMap<String, Object>();
                    param.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
                    param.put(DB_PARAM_SHIP_VIA_CD, cMsg.tplSvcLvlCd_J2.getValue());
                    param.put(DB_PARAM_INVTY_OWNR_CD, cMsg.whCd_J2.getValue());
                    S21SsmEZDResult sslCarrier = NLGL0010Query.getInstance().get3PLRelatedSSLCarrier(ssmParam);
                    if (!sslCarrier.isCodeNotFound()) {
                        List<Map<String, String>> sslCarrierList = (List<Map<String, String>>) sslCarrier.getResultObject();
                        ZYPEZDItemValueSetter.setValue(insertTMsg.shpgSvcLvlCd, sslCarrierList.get(0).get(DB_SHPG_SVC_LVL_CD));
                    }
                }
            }
        } else if (ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_J1)) {
            ZYPEZDItemValueSetter.setValue(insertTMsg.shpgSvcLvlCd, cMsg.shpgSvcLvlCd_J1.getValue());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        try {
            Date date = sdf.parse(cMsg.I.no(0).xxDtTm_I2.getValue());
            sdf.applyPattern("yyyyMMdd");
            ZYPEZDItemValueSetter.setValue(insertTMsg.rddDt, sdf.format(date));
            sdf.applyPattern("MM/dd/yyyy HH:mm:ss");
            date = sdf.parse(cMsg.I.no(0).xxDtTm_I3.getValue());
            sdf.applyPattern("yyyyMMdd");
            ZYPEZDItemValueSetter.setValue(insertTMsg.rsdDt, sdf.format(date));
        } catch (ParseException e) {

        }
        ZYPEZDItemValueSetter.setValue(insertTMsg.wmsDropFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insertTMsg.soConfigFlg, cMsg.asmReqFlg_J1.getValue());
        ZYPEZDItemValueSetter.setValue(insertTMsg.svcConfigMstrPk, cMsg.svcConfigMstrPk_J1.getValue());
        boolean preinstallFlg = false;
        for (int j = 0; j < cMsg.K.getValidCount(); j++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.K.no(j).indConfigFlg_K1.getValue())) {
                preinstallFlg = true;
                break;
            }
        }
        if (preinstallFlg) {
            ZYPEZDItemValueSetter.setValue(insertTMsg.preIstlShopRqstFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(insertTMsg.preIstlShopRqstFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(insertTMsg.rtrnItemInclFlg, cMsg.rtrnItemInclFlg_J1.getValue());
        ZYPEZDItemValueSetter.setValue(insertTMsg.schdDelyDt, cMsg.schdDelyDt_J1.getValue());
        ZYPEZDItemValueSetter.setValue(insertTMsg.carrCd, cMsg.carrCd_J1.getValue());
        if (ZYPCommonFunc.hasValue(insertTMsg.soNum)) {
            EZDTBLAccessor.create(insertTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NLGM0008E, new String[] {SHPG_ORD, DB_SEL_GLBL_CMPY_CD + DELIMITER_COLON + DB_SO_NUM, insertTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + insertTMsg.soNum.getValue() });
            }
        }
    }

    private void createShpgOrdCustDtls(NLGL0010CMsg cMsg, String strShipOdr, String fromShpgOrdSo) {
        S21SsmEZDResult ssmResult;
        Map<String, Object> param;
        param = new HashMap<String, Object>();
        param.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        param.put(DB_PARAM_SO_NUM, fromShpgOrdSo);
        ssmResult = NLGL0010Query.getInstance().getShpgOrdCustDtlKey(param);
        if (!ssmResult.isCodeNotFound()) {
            List<Map<String, Object>> shpgOrdDtlKeyList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (Map<String, Object> shpgOrdDtlKey : shpgOrdDtlKeyList) {
                SHPG_ORD_CUST_DTLTMsg fromShpgOrdDtlTMsg = new SHPG_ORD_CUST_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(fromShpgOrdDtlTMsg.soNum, (String) shpgOrdDtlKey.get(DB_SO_NUM));
                ZYPEZDItemValueSetter.setValue(fromShpgOrdDtlTMsg.soCustDataTpCd, (String) shpgOrdDtlKey.get(SO_CUST_DATA_TP_CD));
                ZYPEZDItemValueSetter.setValue(fromShpgOrdDtlTMsg.glblCmpyCd, (String) shpgOrdDtlKey.get(DB_SEL_GLBL_CMPY_CD));
                fromShpgOrdDtlTMsg = (SHPG_ORD_CUST_DTLTMsg) EZDTBLAccessor.findByKey(fromShpgOrdDtlTMsg);
                SHPG_ORD_CUST_DTLTMsg insertDtlTMsg = new SHPG_ORD_CUST_DTLTMsg();
                if (fromShpgOrdDtlTMsg != null) {
                    EZDMsg.copy(fromShpgOrdDtlTMsg, null, insertDtlTMsg, null);
                    ZYPEZDItemValueSetter.setValue(insertDtlTMsg.soNum, strShipOdr);
                }
                if (ZYPCommonFunc.hasValue(insertDtlTMsg.soNum)) {
                    EZDTBLAccessor.create(insertDtlTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insertDtlTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NLGM0008E, new String[] {SHPG_ORD_CUST_DTL, DB_SEL_GLBL_CMPY_CD + DELIMITER_COLON + DB_SO_NUM + DELIMITER_COLON + SO_CUST_DATA_TP_CD
                                , insertDtlTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + insertDtlTMsg.soNum.getValue() + DELIMITER_COLON + insertDtlTMsg.soCustDataTpCd.getValue() });
                    }
                }
            }
        }
    }

    private boolean validate(NLGL0010CMsg cMsg) {
        //Check Ship Via & Carrier & SSL

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        params.put(DB_PARAM_INVTY_OWNR_CD, cMsg.whCd_J2.getValue());
        S21SsmEZDResult result = NLGL0010Query.getInstance().get3PLWhCode(params);

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        if (!result.isCodeNotFound()) {
            //check 3PL
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
            ssmParam.put(DB_PARAM_SHPG_SVC_LVL_CD, cMsg.shpgSvcLvlCd_J1.getValue());
            ssmParam.put(DB_PARAM_CARR_CD, cMsg.carrCd_J1.getValue());
            ssmParam.put(DB_PARAM_INVTY_OWNR_CD, cMsg.whCd_J2.getValue());
            ssmParam.put(DB_PARAM_SHIP_VIA_CD, cMsg.tplSvcLvlCd_J2.getValue());

            S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getValid3PLSSLCarrier(ssmParam);
            if (ssmResult.isCodeNotFound()) {
                cMsg.shpgSvcLvlCd_J1.setErrorInfo(1, NLGM0069E, new String[]{"Combination of Carrier and Shpg Svc Lvl"});
                cMsg.carrCd_J1.setErrorInfo(1, NLGM0069E, new String[]{"Combination of Carrier and Shpg Svc Lvl"});
                cMsg.setMessageInfo(ZZM9037E);
                return false;
            }
        } else {
            //check Normal
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
            ssmParam.put(DB_PARAM_SHIP_VIA_CD, cMsg.tplSvcLvlCd_J2.getValue());
            ssmParam.put(DB_PARAM_SHPG_SVC_LVL_CD, cMsg.shpgSvcLvlCd_J1.getValue());

            S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getValidSSL(ssmParam);
            if (ssmResult.isCodeNotFound()) {
                cMsg.tplSvcLvlCd_J2.setErrorInfo(1, NLGM0069E, new String[]{"Combination of ShipVia and Shpg Svc Lvl"});
                cMsg.shpgSvcLvlCd_J1.setErrorInfo(1, NLGM0069E, new String[]{"Combination of ShipVia and Shpg Svc Lvl"});
                cMsg.setMessageInfo(ZZM9037E);
                return false;
            }
        }

        ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_SHPG_SVC_LVL_CD, cMsg.shpgSvcLvlCd_J1.getValue());
        ssmParam.put(DB_PARAM_CARR_CD, cMsg.carrCd_J1.getValue());

        S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getValidSSLCarrier(ssmParam);
        if (ssmResult.isCodeNotFound()) {
            cMsg.shpgSvcLvlCd_J1.setErrorInfo(1, NLGM0069E, new String[]{"Combination of Carrier and Shpg Svc Lvl"});
            cMsg.carrCd_J1.setErrorInfo(1, NLGM0069E, new String[]{"Combination of Carrier and Shpg Svc Lvl"});
            cMsg.setMessageInfo(ZZM9037E);
            return false;
        }
        return true;
    }

    private void changeShpgOrdWmsDropFlg(NLGL0010CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_WH_CD, bizMsg.whCd_02.getValue());
        ssmParam.put(DB_PARAM_WMS_SO_ID, bizMsg.I.no(0).wmsSoId_I1.getValue());

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A1.getValue())) {
                ssmParam.put(DB_PARAM_WMS_SQ_NUM, bizMsg.A.no(i).wmsSqNum_A1.getValueInt());
                break;
            }
        }

        S21SsmEZDResult resultQuery = NLGL0010Query.getInstance().getShpgOrdKey(ssmParam);
        if (!resultQuery.isCodeNotFound()) {
            Map<String, Object> shpgOrdKey = (Map<String, Object>) resultQuery.getResultObject();
            SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.glblCmpyCd, (String) shpgOrdKey.get(DB_SEL_GLBL_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.soNum, (String) shpgOrdKey.get(DB_SO_NUM));
            shpgOrdTMsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(shpgOrdTMsg);
            if (shpgOrdTMsg != null) {
                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.wmsDropFlg, ZYPConstant.FLG_OFF_N);
                EZDTBLAccessor.update(shpgOrdTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NLGM0008E, new String[] {SHPG_ORD
                            , DB_SEL_GLBL_CMPY_CD + DELIMITER_COLON + DB_SO_NUM
                            , shpgOrdTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + shpgOrdTMsg.soNum.getValue() });
                }

            }
        }
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[Delete] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0010Scrn00_CMN_Delete(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        if (TAB_SO_UPLD_EDT.equals(cMsg.xxDplyTab.getValue())) {
            WMS_INBD_TRXTMsg wmsInbdTrxTMsg = new WMS_INBD_TRXTMsg();
            for (int i = 0; i < cMsg.O.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.O.no(i).xxChkBox_O1.getValue())) {
                    if (!ZYPCommonFunc.hasValue(cMsg.O.no(i).ezInTime_O1) || //
                            !ZYPConstant.FLG_OFF_0.equals(cMsg.O.no(i).ezCancelFlag_O1.getValue())) {
                        continue;
                    }

                    wmsInbdTrxTMsg.clear();
                    ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsInbdTrxPk, cMsg.O.no(i).wmsInbdTrxPk_O1);
                    wmsInbdTrxTMsg = (WMS_INBD_TRXTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(wmsInbdTrxTMsg);

                    if (wmsInbdTrxTMsg != null) {
                        EZDTBLAccessor.logicalRemove(wmsInbdTrxTMsg);

                        // when update error, message sets the CMsg
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxTMsg.getReturnCode())) {
                            cMsg.setMessageInfo(NLGM0008E, new String[] {WMS_INBD_TRX //
                                    , DB_SEL_GLBL_CMPY_CD + DELIMITER_COLON + DB_WMS_INBD_TRX_PK //
                                    , wmsInbdTrxTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + wmsInbdTrxTMsg.wmsInbdTrxPk.getValue() });
                            return;
                        }
                        cMsg.setMessageInfo(NLGM0073I, new String[] {DATA_VALUE_DELETE, //
                                DB_WMS_REC_ID + DELIMITER_COLON + wmsInbdTrxTMsg.wmsRecId.getValue()});
                    }
                }
            }
        }
    }

    /**
     * Do upload process.
     * @param cMsg NLGL0010CMsg
     * @param sMsg NLGL0010SMsg
     * @return isSuccess
     */
    private boolean doProcess_Upload(NLGL0010CMsg cMsg, NLGL0010SMsg sMsg) {

        String whCd = cMsg.whCd_02.getValue();

        WMS_INBD_TRXTMsg wmsInbdTrxTMsg = new WMS_INBD_TRXTMsg();
        for (int i = 0; i < cMsg.O.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(cMsg.O.no(i).xxChkBox_O1.getValue())) {
                continue;
            }

            wmsInbdTrxTMsg.clear();
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.whCd, whCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsInbdTrxPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ));
            if (ZYPCommonFunc.hasValue(cMsg.O.no(i).ezInTime_O1)) {
                ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsUpdHistNum, NLXC014001.nullToZero(cMsg.O.no(i).wmsUpdHistNum_O1.getValue()).add(BigDecimal.ONE));
            } else {
                ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsUpdHistNum, BigDecimal.ZERO);
            }
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcProcStsCd, PROC_STS.COMPLEATED);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.procStsCd, PROC_STS.IN_COMPLETED);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsRecId, ZYPNumbering.getUniqueID(REC_ID_ONLINE_KEY) + DATA_VALUE_0);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsTaskCd, cMsg.O.no(i).wmsTaskCd_O2);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.otbdOrdNum, cMsg.O.no(i).otbdOrdNum_O1);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.otbdOrdLineNum, cMsg.O.no(i).otbdOrdLineNum_O1);
            // QC#18949 Start
            if (ZYPCommonFunc.hasValue( cMsg.O.no(i).otbdOrdTpCd_O2)) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
                param.put(DB_PARAM_SCE_ORD_TP_CD, cMsg.O.no(i).otbdOrdTpCd_O2.getValue());
                param.put(DB_PARAM_INBD_OTBD_CD, INBD_OTBD.OUTBOUND);
                S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getS80OrdTpCd(param);
                if (ssmResult.isCodeNormal()) {
                    ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.otbdOrdTpCd, (String) ssmResult.getResultObject());
                }
            }
            // QC#18949 End
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.inbdOrdNum, cMsg.O.no(i).inbdOrdNum_O1);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.inbdOrdLineNum, cMsg.O.no(i).inbdOrdLineNum_O1);

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
            queryParam.put(DB_PARAM_WH_CD, cMsg.whCd_02.getValue());
            queryParam.put(DB_PARAM_WMS_SO_ID, cMsg.wmsSoId_O1.getValue());

            // QC#18949 Start
            if (ZYPCommonFunc.hasValue( cMsg.O.no(i).inbdOrdTpCd_O2)) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
                param.put(DB_PARAM_SCE_ORD_TP_CD, cMsg.O.no(i).inbdOrdTpCd_O2.getValue());
                param.put(DB_PARAM_INBD_OTBD_CD, INBD_OTBD.INBOUND);
                S21SsmEZDResult ssmResult = NLGL0010Query.getInstance().getS80OrdTpCd(param);
                if (ssmResult.isCodeNormal()) {
                    ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.inbdOrdTpCd, (String) ssmResult.getResultObject());
                }
            }
            // QC#18949 End
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsTrxDtTmTs, ZYPDateUtil.getCurrentSystemTime(FMT_YYYYMMDDHHMMSS));
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsTotWt, cMsg.O.no(i).wmsTotWt_O1);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsFrtChrgAmt, cMsg.O.no(i).wmsFrtChrgAmt_O1);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOrdStsCd, cMsg.O.no(i).wmsOrdStsCd_O2);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsCarrCd, cMsg.O.no(i).wmsCarrCd_O1);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsTrailId, cMsg.O.no(i).wmsTrailId_O1);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.proNum, cMsg.O.no(i).proNum_O1);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.bolNum, cMsg.O.no(i).bolNum_O1);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOutCntnrNum, cMsg.O.no(i).wmsOutCntnrNum_O1);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsMdseCd, cMsg.O.no(i).wmsMdseCd_O1);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsStkStsCd, cMsg.O.no(i).wmsStkStsCd_O2);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOrdQty, cMsg.O.no(i).wmsOrdQty_O1);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.tmsFrtChrgAmt, cMsg.O.no(i).tmsFrtChrgAmt_O1);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOrgHostId, cMsg.O.no(i).wmsOrgHostId_O1);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.serNum, cMsg.O.no(i).serNum_O1);
            if (ZYPCommonFunc.hasValue(cMsg.O.no(i).xxDt10Dt_O1)) {
                ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.estDockDtTmTs, ZYPDateUtil.DateFormatter(cMsg.O.no(i).xxDt10Dt_O1.getValue() //
                        , FMT_YYYYMMDD, FMT_YYYYMMDDHHMMSS));
            }
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsShipId, cMsg.O.no(i).wmsShipId_O1);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsCntnrId, cMsg.O.no(i).wmsCntnrId_O1);
            // Set default
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.ordLineCpltFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsPkgCd, cMsg.O.no(i).packCdTxt_O1);
            EZDTBLAccessor.create(wmsInbdTrxTMsg);

            // when update error, message sets the CMsg
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NLGM0008E, new String[] {WMS_INBD_TRX //
                        , DB_SEL_GLBL_CMPY_CD + DELIMITER_COLON + DB_WMS_INBD_TRX_PK //
                        , wmsInbdTrxTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + wmsInbdTrxTMsg.wmsInbdTrxPk.getValue() });
                return false;
            }
            updOrderCloseDate(cMsg, cMsg.wmsSoId_O1.getValue(), i);

            if (ZYPCommonFunc.hasValue(cMsg.O.no(i).ezInTime_O1)) {
                //delete original data
                wmsInbdTrxTMsg.clear();
                ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsInbdTrxPk, cMsg.O.no(i).wmsInbdTrxPk_O1);
                wmsInbdTrxTMsg = (WMS_INBD_TRXTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(wmsInbdTrxTMsg);

                if (wmsInbdTrxTMsg != null) {
                    EZDTBLAccessor.logicalRemove(wmsInbdTrxTMsg);

                    // when update error, message sets the CMsg
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NLGM0008E, new String[] {WMS_INBD_TRX //
                                , DB_SEL_GLBL_CMPY_CD + DELIMITER_COLON + DB_WMS_INBD_TRX_PK //
                                , wmsInbdTrxTMsg.glblCmpyCd.getValue() + DELIMITER_COLON + wmsInbdTrxTMsg.wmsInbdTrxPk.getValue() });
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Update SO Order Close Date.
     * @param wmsInbdTrxTMsg WMS Transaction Data
     * @return error code
     */
    private void updOrderCloseDate(NLGL0010CMsg cMsg, String wmsSoId, int rowCnt) {

        String otbdOrdNum = cMsg.O.no(rowCnt).otbdOrdNum_O1.getValue();
        String inbdOrdNum = cMsg.O.no(rowCnt).inbdOrdNum_O1.getValue();
        String wmsTaskCd = cMsg.O.no(rowCnt).wmsTaskCd_O2.getValue();
        String whCd = cMsg.whCd_02.getValue();
        String wmsOrdStsCd = cMsg.O.no(rowCnt).wmsOrdStsCd_O2.getValue();

        boolean updProcFlg = false;
        if (ZYPCommonFunc.hasValue(otbdOrdNum) //
                && (WMS_TASK.OSHP.equals(wmsTaskCd) || (WMS_TASK.OSC.equals(wmsTaskCd) && DB_PARAM_CANCEL.equals(wmsOrdStsCd)))) {
            String otbdOrdTpCd = cMsg.O.no(rowCnt).otbdOrdTpCd_O2.getValue();
            if (!(WMS_ORD_TP.INBOUND_ITEM_CHANGE.equals(otbdOrdTpCd) || WMS_ORD_TP.INBOUND_STOCK_STATUS_CHANGE.equals(otbdOrdTpCd))) {
                if (wmsSoId.equals(otbdOrdNum)) {
                    updProcFlg = true;
                }
            }
        } else if (ZYPCommonFunc.hasValue(inbdOrdNum) //
                && (WMS_TASK.PDLT.equals(wmsTaskCd) || WMS_TASK.PCLS.equals(wmsTaskCd) || WMS_TASK.PCFM.equals(wmsTaskCd))) {
            String inbdOrdTpCd = cMsg.O.no(rowCnt).inbdOrdTpCd_O2.getValue();
            if (WMS_ORD_TP.INBOUND_ITEM_CHANGE.equals(inbdOrdTpCd) || WMS_ORD_TP.INBOUND_STOCK_STATUS_CHANGE.equals(inbdOrdTpCd)) {
                if (wmsSoId.equals(inbdOrdNum)) {
                    updProcFlg = true;
                }
            }
        }

        if (updProcFlg) {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
            queryParam.put(DB_PARAM_WH_CD, whCd);
            queryParam.put(DB_PARAM_WMS_SO_ID, wmsSoId);

            S21SsmEZDResult soHdr = NLGL0010Query.getInstance().getWmsInbdSoHdr(queryParam);
            List soHdrList = (List) soHdr.getResultObject();

            // to update the cancellation date or close date, of
            // WMS_INBD_SO_HDR.
            if (!soHdrList.isEmpty()) {
                WMS_INBD_SO_HDRTMsg wmsInbdSoHdrTMsg = (WMS_INBD_SO_HDRTMsg) soHdrList.get(DATA_VALUE_INT_0);

                if (ZYPCommonFunc.hasValue(wmsInbdSoHdrTMsg.wmsCancDtTmTs.getValue()) //
                        || ZYPCommonFunc.hasValue(wmsInbdSoHdrTMsg.shipDtTmTs.getValue())) {
                    ZYPEZDItemValueSetter.setValue(wmsInbdSoHdrTMsg.wmsCancDtTmTs, BLANK);
                    ZYPEZDItemValueSetter.setValue(wmsInbdSoHdrTMsg.shipDtTmTs, BLANK);
                    EZDTBLAccessor.updateSelectionField(wmsInbdSoHdrTMsg, new String[] {KEY_WMS_CANC_DT_TM_TS, KEY_SHIP_DT_TM_TS });

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdSoHdrTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NLGM0008E, new String[] {wmsInbdSoHdrTMsg.getTableName() //
                                , DB_SEL_GLBL_CMPY_CD + DELIMITER_COLON + DB_WH_CD + DELIMITER_COLON + WMS_SO_ID //
                                , getGlobalCompanyCode() + DELIMITER_COLON + whCd + DELIMITER_COLON + wmsSoId });
                    }
                }
            }
        }
    }
}
