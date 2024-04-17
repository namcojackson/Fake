package com.canon.cusa.s21.api.NWZ.NWZC403001;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.VND_RTRNTMsg;
import business.db.VND_RTRN_DTLTMsg;
import business.db.VND_RTRN_DTLTMsgArray;
import business.parts.NWZC107001PMsg;
import business.parts.NWZC403001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC107001.NWZC107001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;

/**
 * <pre>
 * Vendor Return Update
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/06/2009   SRAA            S.Kumabuchi     Create          N/A
 * </pre>
 */
public class NWZC403001 extends S21ApiCommonBase {

    /** Create Mode */
    private static final String MODE_CREATE = "11";

    /** Change Mode */
    private static final String MODE_CHANGE = "13";

    /** Closed Mode */
    private static final String MODE_CLOSED = "15";

    /** Undo Mode */
    private static final String MODE_UNDO = "09";

    /** Vendor Return Numbering Item */
    private static final String VND_RTRN_NUMBERING_TXT = "VR#_M";

    /**
     * <pre>
     * It is a constructor.
     * </pre>
     */
    public NWZC403001() {
        super();
    }

    /**
     * <pre>
     * Refer to the class comment.
     * One Msg in List is taken out, and the execute(NWZC403001PMsg, ONBATCH_TYPE) is executed. 
     * </pre>
     * 
     * @see #execute(NWZC403001PMsg,
     * com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE)
     * @param params Interface Msg list of Vendor Return Update API
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NWZC403001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NWZC403001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    /**
     * <pre>
     * Refer to the class comment.
     * </pre>
     * 
     * @param param Interface Msg of Vendor Return Update API
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC403001PMsg param, final ONBATCH_TYPE onBatchType) {

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("--- START ---");
            writeDebugLog("InputData=" + param.toString());
        }

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        doProcess(msgMap, onBatchType);

        msgMap.flush();

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("OutputData=" + param.toString());
            writeDebugLog("--- END ---");
        }
    }

    private void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        NWZC403001PMsg inMsg = (NWZC403001PMsg) msgMap.getPmsg();

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("Mode=" + inMsg.xxModeCd.getValue());
        }

        if (MODE_CREATE.equals(inMsg.xxModeCd.getValue())) {
            if (!checkInputHeaderCommon(msgMap, MODE_CREATE)) {
                return;
            }
            executeUpdateCreateMode(msgMap, onBatchType);
        } else if (MODE_CHANGE.equals(inMsg.xxModeCd.getValue())) {
            if (!checkInputHeaderCommon(msgMap, MODE_CHANGE)) {
                return;
            }
            executeUpdateChangeMode(msgMap, onBatchType);
        } else if (MODE_CLOSED.equals(inMsg.xxModeCd.getValue())) {
            if (!checkInputHeaderCommon(msgMap, MODE_CLOSED)) {
                return;
            }
            executeUpdateClosedMode(msgMap, onBatchType);
        } else if (MODE_UNDO.equals(inMsg.xxModeCd.getValue())) {
            if (!checkInputHeaderCommon(msgMap, MODE_UNDO)) {
                return;
            }
            executeUpdateUndoMode(msgMap, onBatchType);
        } else {
            this.addMsgId(msgMap, "NWZM0790E");
            return;
        }
    }

    /**
     * <pre>
     *  Update for Create Mode
     * </pre>
     * 
     * @param msgMap Message Map
     * @param onBatchType ONBATCH_TYPE
     */
    private void executeUpdateCreateMode(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        NWZC403001PMsg inMsg = (NWZC403001PMsg) msgMap.getPmsg();
        VND_RTRNTMsg headerMsg = new VND_RTRNTMsg();
        VND_RTRN_DTLTMsg detailMsg = new VND_RTRN_DTLTMsg();

        String numbering = null;
        // get Vendor Return Number
        numbering = ZYPNumbering.getUniqueID(inMsg.glblCmpyCd.getValue(), VND_RTRN_NUMBERING_TXT);

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("Vendor Return Number:" + numbering);
        }

        // Header Data
        ZYPEZDItemValueSetter.setValue(headerMsg.glblCmpyCd, inMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.vndRtrnNum, numbering);
        if (!ZYPCommonFunc.hasValue(inMsg.exptFlg.getValue())) {
        	ZYPEZDItemValueSetter.setValue(headerMsg.exptFlg, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(headerMsg.exptFlg, inMsg.exptFlg.getValue());
        }
        ZYPEZDItemValueSetter.setValue(headerMsg.vndRtrnTpCd, inMsg.vndRtrnTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.vndRtrnSubmtDt, inMsg.vndRtrnSubmtDt.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.trxSrcTpCd, inMsg.trxSrcTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.sysSrcCd, inMsg.sysSrcCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.invtyLocCd, inMsg.invtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.focCd, inMsg.focCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.slsRepOrSlsTeamTocCd, inMsg.slsRepOrSlsTeamTocCd.getValue());
        if (!ZYPCommonFunc.hasValue(inMsg.psIssRqstFlg.getValue())) {
        	ZYPEZDItemValueSetter.setValue(headerMsg.psIssRqstFlg, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(headerMsg.psIssRqstFlg, inMsg.psIssRqstFlg.getValue());
        }
        ZYPEZDItemValueSetter.setValue(headerMsg.packMethCd, inMsg.packMethCd.getValue());
        if (!ZYPCommonFunc.hasValue(inMsg.prtlShipAllwFlg.getValue())) {
        	ZYPEZDItemValueSetter.setValue(headerMsg.prtlShipAllwFlg, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(headerMsg.prtlShipAllwFlg, inMsg.prtlShipAllwFlg.getValue());
        }
        ZYPEZDItemValueSetter.setValue(headerMsg.rsdDt, inMsg.rsdDt.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.rddDt, inMsg.rddDt.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.firstProdCtrlCd, inMsg.firstProdCtrlCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.destPortNm, inMsg.destPortNm.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.exptLoadPortCd, inMsg.exptLoadPortCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.exptLoadPortNm, inMsg.exptLoadPortNm.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.billToVndCd, inMsg.billToVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.debitToVndCd, inMsg.debitToVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToVndCd, inMsg.shipToVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToLocNm, inMsg.shipToLocNm.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToAddlLocNm, inMsg.shipToAddlLocNm.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToFirstLineAddr, inMsg.shipToFirstLineAddr.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToScdLineAddr, inMsg.shipToScdLineAddr.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToThirdLineAddr, inMsg.shipToThirdLineAddr.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToFrthLineAddr, inMsg.shipToFrthLineAddr.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToCtyAddr, inMsg.shipToCtyAddr.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToStCd, inMsg.shipToStCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToProvNm, inMsg.shipToProvNm.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToCntyNm, inMsg.shipToCntyNm.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToPostCd, inMsg.shipToPostCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToCtryCd, inMsg.shipToCtryCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToFirstRefCmntTxt, inMsg.shipToFirstRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToScdRefCmntTxt, inMsg.shipToScdRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.docLangCd, inMsg.docLangCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.cashDiscTermCd, inMsg.cashDiscTermCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.advRcptNum, inMsg.advRcptNum.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.lcNum, inMsg.lcNum.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.frtChrgRspbTpCd, inMsg.frtChrgRspbTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.insChrgRspbTpCd, inMsg.insChrgRspbTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.pmtTermCd, inMsg.pmtTermCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.pmtMethCd, inMsg.pmtMethCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.pmtCondCd, inMsg.pmtCondCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.usTaxCd, inMsg.usTaxCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.dealCcyCd, inMsg.dealCcyCd.getValue());
        if (!ZYPCommonFunc.hasValue(inMsg.totVndRtrnAmt.getValue())) {
            ZYPEZDItemValueSetter.setValue(headerMsg.totVndRtrnAmt, BigDecimal.ZERO);
        } else {
            ZYPEZDItemValueSetter.setValue(headerMsg.totVndRtrnAmt, inMsg.totVndRtrnAmt.getValue());
        }
        ZYPEZDItemValueSetter.setValue(headerMsg.shpgMethCd, inMsg.shpgMethCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shpgTermCd, inMsg.shpgTermCd.getValue());
        if (!ZYPCommonFunc.hasValue(inMsg.vndRtrnHldFlg.getValue())) {
        	ZYPEZDItemValueSetter.setValue(headerMsg.vndRtrnHldFlg, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(headerMsg.vndRtrnHldFlg, inMsg.vndRtrnHldFlg.getValue());
        }

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("----- Detail CREATE Start -----");
        }

        // Detail Data
        if (inMsg.List.getValidCount() > 0) {
            for (int rowCount = 0; rowCount < inMsg.List.getValidCount(); rowCount++) {

                if (!checkInputDetailCommon(msgMap, rowCount, MODE_CREATE)) {
                    ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, numbering);
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, inMsg.List.no(rowCount).trxLineNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, inMsg.List.no(rowCount).trxLineSubNum.getValue());
                    return;
                }

                ZYPEZDItemValueSetter.setValue(detailMsg.glblCmpyCd, inMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.vndRtrnNum, numbering);
                ZYPEZDItemValueSetter.setValue(detailMsg.trxLineNum, inMsg.List.no(rowCount).trxLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.trxLineSubNum, inMsg.List.no(rowCount).trxLineSubNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.vndRtrnDtlSubmtDt, inMsg.List.no(rowCount).vndRtrnSubmtDt_D1.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.mdseCd, inMsg.List.no(rowCount).mdseCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.mdseNm, inMsg.List.no(rowCount).mdseNm.getValue());
                if (!ZYPCommonFunc.hasValue(inMsg.List.no(rowCount).avalQty.getValue())) {
                    ZYPEZDItemValueSetter.setValue(detailMsg.avalQty, BigDecimal.ZERO);
                } else {
                    ZYPEZDItemValueSetter.setValue(detailMsg.avalQty, inMsg.List.no(rowCount).avalQty.getValue());
                }
                if (!ZYPCommonFunc.hasValue(inMsg.List.no(rowCount).rtrnQty.getValue())) {
                    ZYPEZDItemValueSetter.setValue(detailMsg.rtrnQty, BigDecimal.ZERO);
                } else {
                    ZYPEZDItemValueSetter.setValue(detailMsg.rtrnQty, inMsg.List.no(rowCount).rtrnQty.getValue());
                }
                if (!ZYPCommonFunc.hasValue(inMsg.List.no(rowCount).exchRate.getValue())) {
                    ZYPEZDItemValueSetter.setValue(detailMsg.exchRate, BigDecimal.ZERO);
                } else {
                    ZYPEZDItemValueSetter.setValue(detailMsg.exchRate, inMsg.List.no(rowCount).exchRate.getValue());
                }
                ZYPEZDItemValueSetter.setValue(detailMsg.locStsCd, inMsg.List.no(rowCount).locStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.stkStsCd, inMsg.List.no(rowCount).stkStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.uomCd, inMsg.List.no(rowCount).uomCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.invNum, inMsg.List.no(rowCount).invNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.slsRepOrSlsTeamTocCd, inMsg.List.no(rowCount).slsRepOrSlsTeamTocCd_D1.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.usTaxCd, inMsg.List.no(rowCount).usTaxCd_D1.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.funcCcyCd, inMsg.List.no(rowCount).funcCcyCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.dealCcyCd, inMsg.List.no(rowCount).dealCcyCd_D1.getValue());
                if (!ZYPCommonFunc.hasValue(inMsg.List.no(rowCount).dealVndRtrnUnitPrcAmt.getValue())) {
                    ZYPEZDItemValueSetter.setValue(detailMsg.dealVndRtrnUnitPrcAmt, BigDecimal.ZERO);
                } else {
                    ZYPEZDItemValueSetter.setValue(detailMsg.dealVndRtrnUnitPrcAmt, inMsg.List.no(rowCount).dealVndRtrnUnitPrcAmt.getValue());
                }
                if (!ZYPCommonFunc.hasValue(inMsg.List.no(rowCount).origVndRtrnUnitPrcAmt.getValue())) {
                    ZYPEZDItemValueSetter.setValue(detailMsg.origVndRtrnUnitPrcAmt, BigDecimal.ZERO);
                } else {
                    ZYPEZDItemValueSetter.setValue(detailMsg.origVndRtrnUnitPrcAmt, inMsg.List.no(rowCount).origVndRtrnUnitPrcAmt.getValue());
                }
                ZYPEZDItemValueSetter.setValue(detailMsg.coaAcctCd, inMsg.List.no(rowCount).coaAcctCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.trxCd, inMsg.List.no(rowCount).trxCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.trxRsnCd, inMsg.List.no(rowCount).trxRsnCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.exptLicRqstTpCd, inMsg.List.no(rowCount).exptLicRqstTpCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.exptLicNum, inMsg.List.no(rowCount).exptLicNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.exptLicExprDt, inMsg.List.no(rowCount).exptLicExprDt.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.exptGenlLicSymCd, inMsg.List.no(rowCount).exptGenlLicSymCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.eccnNum, inMsg.List.no(rowCount).eccnNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.schdBNum, inMsg.List.no(rowCount).schdBNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.rwsNum, inMsg.List.no(rowCount).rwsNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.rwsDtlLineNum, inMsg.List.no(rowCount).rwsDtlLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.inbdSrcTpCd, inMsg.List.no(rowCount).inbdSrcTpCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.rwsWhInEtaDt, inMsg.List.no(rowCount).rwsWhInEtaDt.getValue());
                if (!ZYPCommonFunc.hasValue(inMsg.List.no(rowCount).vndRtrnDtlHldFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(detailMsg.vndRtrnDtlHldFlg, ZYPConstant.FLG_OFF_N);
                } else {
                    ZYPEZDItemValueSetter.setValue(detailMsg.vndRtrnDtlHldFlg, inMsg.List.no(rowCount).vndRtrnDtlHldFlg.getValue());
                }

                // Create for Detail(VND_RTRN_DTL)
                if (!create(detailMsg)) {
                    ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, inMsg.List.no(rowCount).trxLineNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, inMsg.List.no(rowCount).trxLineSubNum.getValue());
                    this.addMsgId(msgMap, "NWZM0815E");
                    return;
                }
            }
        }

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("----- Detail CREATE End -----");
            writeDebugLog("----- Header CREATE Start -----");
        }

        // Create for Header(VND_RTRN)
        if (!create(headerMsg)) {
            ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, "");
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, "");
            this.addMsgId(msgMap, "NWZM0808E");
            return;
        }

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("----- Header CREATE End -----");
        }

        return;
    }

    /**
     * <pre>
     *  Update for Change Mode
     * </pre>
     * 
     * @param msgMap Message Map
     * @param onBatchType ONBATCH_TYPE
     */
    private void executeUpdateChangeMode(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        NWZC403001PMsg inMsg = (NWZC403001PMsg) msgMap.getPmsg();
        VND_RTRNTMsg headerMsg = new VND_RTRNTMsg();
        VND_RTRN_DTLTMsg detailMsg = new VND_RTRN_DTLTMsg();

        // Header Data
        ZYPEZDItemValueSetter.setValue(headerMsg.glblCmpyCd, inMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());

        // select for update
        headerMsg = (VND_RTRNTMsg)S21ApiTBLAccessor.findByKeyForUpdate(headerMsg);
        // When no record hit
        if (headerMsg == null) {
            ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, "");
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, "");
            this.addMsgId(msgMap, "NWZM0809E");
            return;
        }

        if (!ZYPCommonFunc.hasValue(inMsg.exptFlg.getValue())) {
        	ZYPEZDItemValueSetter.setValue(headerMsg.exptFlg, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(headerMsg.exptFlg, inMsg.exptFlg.getValue());
        }
        ZYPEZDItemValueSetter.setValue(headerMsg.vndRtrnTpCd, inMsg.vndRtrnTpCd.getValue());        	
        ZYPEZDItemValueSetter.setValue(headerMsg.vndRtrnCloDt, inMsg.vndRtrnCloDt.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.trxSrcTpCd, inMsg.trxSrcTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.sysSrcCd, inMsg.sysSrcCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.invtyLocCd, inMsg.invtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.focCd, inMsg.focCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.slsRepOrSlsTeamTocCd, inMsg.slsRepOrSlsTeamTocCd.getValue());
        if (!ZYPCommonFunc.hasValue(inMsg.psIssRqstFlg.getValue())) {
        	ZYPEZDItemValueSetter.setValue(headerMsg.psIssRqstFlg, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(headerMsg.psIssRqstFlg, inMsg.psIssRqstFlg.getValue());
        }
        ZYPEZDItemValueSetter.setValue(headerMsg.packMethCd, inMsg.packMethCd.getValue());
        if (!ZYPCommonFunc.hasValue(inMsg.prtlShipAllwFlg.getValue())) {
        	ZYPEZDItemValueSetter.setValue(headerMsg.prtlShipAllwFlg, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(headerMsg.prtlShipAllwFlg, inMsg.prtlShipAllwFlg.getValue());
        }
        ZYPEZDItemValueSetter.setValue(headerMsg.rsdDt, inMsg.rsdDt.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.rddDt, inMsg.rddDt.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.firstProdCtrlCd, inMsg.firstProdCtrlCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.destPortNm, inMsg.destPortNm.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.exptLoadPortCd, inMsg.exptLoadPortCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.exptLoadPortNm, inMsg.exptLoadPortNm.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.billToVndCd, inMsg.billToVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.debitToVndCd, inMsg.debitToVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToVndCd, inMsg.shipToVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToLocNm, inMsg.shipToLocNm.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToAddlLocNm, inMsg.shipToAddlLocNm.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToFirstLineAddr, inMsg.shipToFirstLineAddr.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToScdLineAddr, inMsg.shipToScdLineAddr.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToThirdLineAddr, inMsg.shipToThirdLineAddr.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToFrthLineAddr, inMsg.shipToFrthLineAddr.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToCtyAddr, inMsg.shipToCtyAddr.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToStCd, inMsg.shipToStCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToProvNm, inMsg.shipToProvNm.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToCntyNm, inMsg.shipToCntyNm.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToPostCd, inMsg.shipToPostCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToCtryCd, inMsg.shipToCtryCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToFirstRefCmntTxt, inMsg.shipToFirstRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shipToScdRefCmntTxt, inMsg.shipToScdRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.docLangCd, inMsg.docLangCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.cashDiscTermCd, inMsg.cashDiscTermCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.advRcptNum, inMsg.advRcptNum.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.lcNum, inMsg.lcNum.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.frtChrgRspbTpCd, inMsg.frtChrgRspbTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.insChrgRspbTpCd, inMsg.insChrgRspbTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.pmtTermCd, inMsg.pmtTermCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.pmtMethCd, inMsg.pmtMethCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.pmtCondCd, inMsg.pmtCondCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.usTaxCd, inMsg.usTaxCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.dealCcyCd, inMsg.dealCcyCd.getValue());
        if (!ZYPCommonFunc.hasValue(inMsg.totVndRtrnAmt.getValue())) {
            ZYPEZDItemValueSetter.setValue(headerMsg.totVndRtrnAmt, BigDecimal.ZERO);
        } else {
            ZYPEZDItemValueSetter.setValue(headerMsg.totVndRtrnAmt, inMsg.totVndRtrnAmt.getValue());
        }
        ZYPEZDItemValueSetter.setValue(headerMsg.shpgMethCd, inMsg.shpgMethCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.shpgTermCd, inMsg.shpgTermCd.getValue());
        if (!ZYPCommonFunc.hasValue(inMsg.vndRtrnHldFlg.getValue())) {
        	ZYPEZDItemValueSetter.setValue(headerMsg.vndRtrnHldFlg, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(headerMsg.vndRtrnHldFlg, inMsg.vndRtrnHldFlg.getValue());
        }

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("----- Detail UPDATE Start -----");
        }

        // Detail Data
        if (inMsg.List.getValidCount() > 0) {
            for (int rowCount = 0; rowCount < inMsg.List.getValidCount(); rowCount++) {

                if (!checkInputDetailCommon(msgMap, rowCount, MODE_CHANGE)) {
                    ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, inMsg.List.no(rowCount).trxLineNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, inMsg.List.no(rowCount).trxLineSubNum.getValue());
                    return;
                }

                ZYPEZDItemValueSetter.setValue(detailMsg.glblCmpyCd, inMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.trxLineNum, inMsg.List.no(rowCount).trxLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.trxLineSubNum, inMsg.List.no(rowCount).trxLineSubNum.getValue());

                // select for update
                detailMsg = (VND_RTRN_DTLTMsg)S21ApiTBLAccessor.findByKeyForUpdate(detailMsg);
                // When no record hit
                if (detailMsg == null) {
                    ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, inMsg.List.no(rowCount).trxLineNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, inMsg.List.no(rowCount).trxLineSubNum.getValue());
                    this.addMsgId(msgMap, "NWZM0813E");
                    return;
                }

                ZYPEZDItemValueSetter.setValue(detailMsg.vndRtrnDtlCloDt, inMsg.List.no(rowCount).vndRtrnDtlCloDt.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.mdseCd, inMsg.List.no(rowCount).mdseCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.mdseNm, inMsg.List.no(rowCount).mdseNm.getValue());
                if (!ZYPCommonFunc.hasValue(inMsg.List.no(rowCount).avalQty.getValue())) {
                    ZYPEZDItemValueSetter.setValue(detailMsg.avalQty, BigDecimal.ZERO);
                } else {
                    ZYPEZDItemValueSetter.setValue(detailMsg.avalQty, inMsg.List.no(rowCount).avalQty.getValue());
                }
                if (!ZYPCommonFunc.hasValue(inMsg.List.no(rowCount).rtrnQty.getValue())) {
                    ZYPEZDItemValueSetter.setValue(detailMsg.rtrnQty, BigDecimal.ZERO);
                } else {
                    ZYPEZDItemValueSetter.setValue(detailMsg.rtrnQty, inMsg.List.no(rowCount).rtrnQty.getValue());
                }
                if (!ZYPCommonFunc.hasValue(inMsg.List.no(rowCount).exchRate.getValue())) {
                    ZYPEZDItemValueSetter.setValue(detailMsg.exchRate, BigDecimal.ZERO);
                } else {
                    ZYPEZDItemValueSetter.setValue(detailMsg.exchRate, inMsg.List.no(rowCount).exchRate.getValue());
                }
                ZYPEZDItemValueSetter.setValue(detailMsg.locStsCd, inMsg.List.no(rowCount).locStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.stkStsCd, inMsg.List.no(rowCount).stkStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.uomCd, inMsg.List.no(rowCount).uomCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.invNum, inMsg.List.no(rowCount).invNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.slsRepOrSlsTeamTocCd, inMsg.List.no(rowCount).slsRepOrSlsTeamTocCd_D1.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.usTaxCd, inMsg.List.no(rowCount).usTaxCd_D1.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.funcCcyCd, inMsg.List.no(rowCount).funcCcyCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.dealCcyCd, inMsg.List.no(rowCount).dealCcyCd_D1.getValue());
                if (!ZYPCommonFunc.hasValue(inMsg.List.no(rowCount).dealVndRtrnUnitPrcAmt.getValue())) {
                    ZYPEZDItemValueSetter.setValue(detailMsg.dealVndRtrnUnitPrcAmt, BigDecimal.ZERO);
                } else {
                    ZYPEZDItemValueSetter.setValue(detailMsg.dealVndRtrnUnitPrcAmt, inMsg.List.no(rowCount).dealVndRtrnUnitPrcAmt.getValue());
                }
                if (!ZYPCommonFunc.hasValue(inMsg.List.no(rowCount).origVndRtrnUnitPrcAmt.getValue())) {
                    ZYPEZDItemValueSetter.setValue(detailMsg.origVndRtrnUnitPrcAmt, BigDecimal.ZERO);
                } else {
                    ZYPEZDItemValueSetter.setValue(detailMsg.origVndRtrnUnitPrcAmt, inMsg.List.no(rowCount).origVndRtrnUnitPrcAmt.getValue());
                }
                ZYPEZDItemValueSetter.setValue(detailMsg.coaAcctCd, inMsg.List.no(rowCount).coaAcctCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.trxCd, inMsg.List.no(rowCount).trxCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.trxRsnCd, inMsg.List.no(rowCount).trxRsnCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.exptLicRqstTpCd, inMsg.List.no(rowCount).exptLicRqstTpCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.exptLicNum, inMsg.List.no(rowCount).exptLicNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.exptLicExprDt, inMsg.List.no(rowCount).exptLicExprDt.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.exptGenlLicSymCd, inMsg.List.no(rowCount).exptGenlLicSymCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.eccnNum, inMsg.List.no(rowCount).eccnNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.schdBNum, inMsg.List.no(rowCount).schdBNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.rwsNum, inMsg.List.no(rowCount).rwsNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.rwsDtlLineNum, inMsg.List.no(rowCount).rwsDtlLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.inbdSrcTpCd, inMsg.List.no(rowCount).inbdSrcTpCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.rwsWhInEtaDt, inMsg.List.no(rowCount).rwsWhInEtaDt.getValue());
                if (!ZYPCommonFunc.hasValue(inMsg.List.no(rowCount).vndRtrnDtlHldFlg.getValue())) {
                	ZYPEZDItemValueSetter.setValue(detailMsg.vndRtrnDtlHldFlg, ZYPConstant.FLG_OFF_N);
                } else {
                	ZYPEZDItemValueSetter.setValue(detailMsg.vndRtrnDtlHldFlg, inMsg.List.no(rowCount).vndRtrnDtlHldFlg.getValue());
                }

                // Update for Detail(VND_RTRN_DTL)
                if (!update(detailMsg)) {
                    ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, inMsg.List.no(rowCount).trxLineNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, inMsg.List.no(rowCount).trxLineSubNum.getValue());
                    this.addMsgId(msgMap, "NWZM0813E");
                    return;
                }
            }
        }

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("----- Detail UPDATE End -----");
            writeDebugLog("----- Header UPDATE Start -----");
        }

        // Update for Header(VND_RTRN)
        if (!update(headerMsg)) {
            ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, "");
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, "");
            this.addMsgId(msgMap, "NWZM0809E");
            return;
        }

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("----- Header UPDATE End -----");
        }

        return;
    }

    /**
     * <pre>
     *  Update for Closed Mode
     * </pre>
     * 
     * @param msgMap Message Map
     * @param onBatchType ONBATCH_TYPE
     */
    private void executeUpdateClosedMode(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        NWZC403001PMsg inMsg = (NWZC403001PMsg) msgMap.getPmsg();
        VND_RTRNTMsg headerMsg = new VND_RTRNTMsg();
        VND_RTRN_DTLTMsg detailMsg = new VND_RTRN_DTLTMsg();
        VND_RTRN_DTLTMsg wrkMsg = new VND_RTRN_DTLTMsg();
        String[] headerColumn = {"vndRtrnCloDt"};
        String[] detailColumn = {"vndRtrnDtlCloDt"};

        // Header Data
        ZYPEZDItemValueSetter.setValue(headerMsg.glblCmpyCd, inMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());

        // select for update
        headerMsg = (VND_RTRNTMsg)S21ApiTBLAccessor.findByKeyForUpdate(headerMsg);
        // When no record hit
        if (headerMsg == null) {
            ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, "");
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, "");
            this.addMsgId(msgMap, "NWZM0809E");
            return;
        }

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("----- Detail UPDATE Start -----");
        }

        // Detail Data
        if (inMsg.List.getValidCount() > 0) {
            for (int rowCount = 0; rowCount < inMsg.List.getValidCount(); rowCount++) {

                if (!checkInputDetailCommon(msgMap, rowCount, MODE_CLOSED)) {
                    ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, inMsg.List.no(rowCount).trxLineNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, inMsg.List.no(rowCount).trxLineSubNum.getValue());
                    return;
                }

                ZYPEZDItemValueSetter.setValue(detailMsg.glblCmpyCd, inMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.trxLineNum, inMsg.List.no(rowCount).trxLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.trxLineSubNum, inMsg.List.no(rowCount).trxLineSubNum.getValue());

                // select for update
                detailMsg = (VND_RTRN_DTLTMsg)S21ApiTBLAccessor.findByKeyForUpdate(detailMsg);
                // When no record hit
                if (detailMsg == null) {
                    ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, inMsg.List.no(rowCount).trxLineNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, inMsg.List.no(rowCount).trxLineSubNum.getValue());
                    this.addMsgId(msgMap, "NWZM0813E");
                    return;
                }

                ZYPEZDItemValueSetter.setValue(detailMsg.vndRtrnDtlCloDt, inMsg.List.no(rowCount).vndRtrnDtlCloDt.getValue());

                // check the CLOSE_DATE
                if (!checkClosedDate(msgMap, detailMsg)) {
                    return;
                }

                // Update for Detail(VND_RTRN_DTL)
                if (!updateFields(detailMsg, detailColumn)) {
                    ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, inMsg.List.no(rowCount).trxLineNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, inMsg.List.no(rowCount).trxLineSubNum.getValue());
                    this.addMsgId(msgMap, "NWZM0813E");
                    return;
                }
            }
        }

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("----- Detail UPDATE End -----");
            writeDebugLog("----- Header UPDATE Start -----");
        }

        wrkMsg.setSQLID("001");
        wrkMsg.setConditionValue("glblCmpyCd01", inMsg.glblCmpyCd.getValue());
        wrkMsg.setConditionValue("vndRtrnNum01", inMsg.vndRtrnNum.getValue());
        int cnt = S21ApiTBLAccessor.count(wrkMsg);
        String vndRtrnCloDt = null;

        if (cnt == 0) {
            if (!ZYPCommonFunc.hasValue(inMsg.vndRtrnCloDt.getValue())) {
            	vndRtrnCloDt = getClosedDate(wrkMsg);
            	if (vndRtrnCloDt == null) {
                    ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
                    this.addMsgId(msgMap, "NWZM0809E");
                    return;
                } else {
                    ZYPEZDItemValueSetter.setValue(headerMsg.vndRtrnCloDt, vndRtrnCloDt);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(headerMsg.vndRtrnCloDt, inMsg.vndRtrnCloDt.getValue());
            }

            // Update for Header(VND_RTRN)
            if (!updateFields(headerMsg, headerColumn)) {
                ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
                this.addMsgId(msgMap, "NWZM0809E");
                return;
            }
        }

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("----- Header UPDATE End -----");
        }

        return;
    }

    /**
     * <pre>
     *  Update for Undo Mode
     * </pre>
     * 
     * @param msgMap Message Map
     * @param onBatchType ONBATCH_TYPE
     */
    private void executeUpdateUndoMode(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        NWZC403001PMsg inMsg = (NWZC403001PMsg) msgMap.getPmsg();
        VND_RTRNTMsg headerMsg = new VND_RTRNTMsg();
        VND_RTRN_DTLTMsg detailMsg = new VND_RTRN_DTLTMsg();
        VND_RTRN_DTLTMsg wrkMsg = new VND_RTRN_DTLTMsg();
        String[] headerColumn = {"vndRtrnCancDt"};
        String[] detailColumn = {"vndRtrnDtlCancDt"};

        // Header Data
        ZYPEZDItemValueSetter.setValue(headerMsg.glblCmpyCd, inMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(headerMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());

        // select for update
        headerMsg = (VND_RTRNTMsg)S21ApiTBLAccessor.findByKeyForUpdate(headerMsg);
        // When no record hit
        if (headerMsg == null) {
            ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, "");
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, "");
            this.addMsgId(msgMap, "NWZM0809E");
            return;
        }

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("----- Detail UPDATE Start -----");
        }

        // Detail Data
        if (inMsg.List.getValidCount() > 0) {
            for (int rowCount = 0; rowCount < inMsg.List.getValidCount(); rowCount++) {

                if (!checkInputDetailCommon(msgMap, rowCount, MODE_UNDO)) {
                    ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, inMsg.List.no(rowCount).trxLineNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, inMsg.List.no(rowCount).trxLineSubNum.getValue());
                	return;
                }

                ZYPEZDItemValueSetter.setValue(detailMsg.glblCmpyCd, inMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.trxLineNum, inMsg.List.no(rowCount).trxLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(detailMsg.trxLineSubNum, inMsg.List.no(rowCount).trxLineSubNum.getValue());

                // select for update
                detailMsg = (VND_RTRN_DTLTMsg)S21ApiTBLAccessor.findByKeyForUpdate(detailMsg);
                // When no record hit
                if (detailMsg == null) {
                    ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, inMsg.List.no(rowCount).trxLineNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, inMsg.List.no(rowCount).trxLineSubNum.getValue());
                    this.addMsgId(msgMap, "NWZM0813E");
                    return;
                }

                ZYPEZDItemValueSetter.setValue(detailMsg.vndRtrnDtlCancDt, inMsg.List.no(rowCount).vndRtrnDtlCancDt.getValue());

                // check the CANCEL_DATE
                if (!checkCancelDate(msgMap, detailMsg)) {
                    return;
                }

                // Update for Detail(VND_RTRN_DTL)
                if (!updateFields(detailMsg, detailColumn)) {
                    ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, inMsg.List.no(rowCount).trxLineNum.getValue());
                    ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, inMsg.List.no(rowCount).trxLineSubNum.getValue());
                    this.addMsgId(msgMap, "NWZM0813E");
                    return;
                }

                //#XXXX Takashimada 2011/04/20 add start
                //allocation cancel
                NWZC107001PMsg pMsg = new NWZC107001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, inMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.xxSysSrcCd, SYS_SRC.S21_EXPORT);
                ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC107001.REQ_TP_CANCEL);
                ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, headerMsg.trxSrcTpCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.trxHdrNum, inMsg.vndRtrnNum.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.trxLineNum, inMsg.List.no(rowCount).trxLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.trxLineSubNum, inMsg.List.no(rowCount).trxLineSubNum.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(inMsg.glblCmpyCd.getValue()));

                NWZC107001 cancAlloc = new NWZC107001();
                cancAlloc.execute(pMsg, onBatchType);
                if (S21ApiUtil.isXxMsgId(pMsg)) {
                    String msg = S21ApiUtil.getXxMsgIdList(pMsg).get(0);
                    this.addMsgId(msgMap, msg);
                    return;
                }
                //#XXXX Takashimada 2011/04/20 add end
            }
        }

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("----- Detail UPDATE End -----");
            writeDebugLog("----- Header UPDATE Start -----");
        }

        wrkMsg.setSQLID("002");
        wrkMsg.setConditionValue("glblCmpyCd01", inMsg.glblCmpyCd.getValue());
        wrkMsg.setConditionValue("vndRtrnNum01", inMsg.vndRtrnNum.getValue());
        int cnt = S21ApiTBLAccessor.count(wrkMsg);
        String vndRtrnCancDt = null;

//        if (cnt != 0) {
        if (cnt == 0) {
            if (!ZYPCommonFunc.hasValue(inMsg.vndRtrnCancDt.getValue())) {
            	vndRtrnCancDt = getCancelDate(wrkMsg);
                if (vndRtrnCancDt == null) {
                    ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
                    this.addMsgId(msgMap, "NWZM0809E");
                    return;
                } else {
                    ZYPEZDItemValueSetter.setValue(headerMsg.vndRtrnCancDt, vndRtrnCancDt);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(headerMsg.vndRtrnCancDt, inMsg.vndRtrnCancDt.getValue());
            }

            // Update for Header(VND_RTRN)
            if (!updateFields(headerMsg, headerColumn)) {
                ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, inMsg.vndRtrnNum.getValue());
                this.addMsgId(msgMap, "NWZM0809E");
                return;
            }
        }

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("----- Header UPDATE End -----");
        }

        return;
    }

    /**
     * <pre>
     * Create
     * </pre>
     * 
     * @param msgMap Message Map
     * @param createTMsg EZDTMsg
     * @return Normal:true Error:false
     */
    private boolean create(EZDTMsg createTMsg) {

        // execute DB update
        S21ApiTBLAccessor.create(createTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(createTMsg.getReturnCode())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Update for Selection Fields
     * </pre>
     * 
     * @param updateTMsg EZDTMsg
     * @param column String[]
     * @return Normal:true Error:false
     */
    private boolean updateFields(EZDTMsg updateTMsg, String[] column) {

        // execute DB update
        S21ApiTBLAccessor.updateSelectionField(updateTMsg, column);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * @param updateTMsg EZDTMsg
     * @return Normal:true Error:false
     */
    private boolean update(EZDTMsg updateTMsg) {

        // execute DB update
        S21ApiTBLAccessor.update(updateTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Check closed date
     * </pre>
     * 
     * @param msgMap Message Map
     * @param wrkOrdTMsg VND_RTRN_DTLTMsg
     * @return Normal:true Error:false
     */
    private boolean checkClosedDate(S21ApiMessageMap msgMap, VND_RTRN_DTLTMsg wrkTMsg) {

        NWZC403001PMsg inMsg = (NWZC403001PMsg) msgMap.getPmsg();
        wrkTMsg = (VND_RTRN_DTLTMsg) S21ApiTBLAccessor.findByKey(wrkTMsg);

        // When no record hit
        if (wrkTMsg == null) {
            // add error message
            ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, wrkTMsg.vndRtrnNum.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, wrkTMsg.trxLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, wrkTMsg.trxLineSubNum.getValue());
            this.addMsgId(msgMap, "NWZM0813E");
            return false;
        }

        // When DB error
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wrkTMsg.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, wrkTMsg.vndRtrnNum.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, wrkTMsg.trxLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, wrkTMsg.trxLineSubNum.getValue());
            this.addMsgId(msgMap, "NWZM0813E");
            return false;
        }

        // When already closed
        String closedDate = wrkTMsg.vndRtrnDtlCloDt.getValue();
        if (ZYPCommonFunc.hasValue(closedDate)) {
            // add error message
            ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, wrkTMsg.vndRtrnNum.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, wrkTMsg.trxLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, wrkTMsg.trxLineSubNum.getValue());
            this.addMsgId(msgMap, "NWZM0810E");
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Check cancel date
     * </pre>
     * 
     * @param msgMap Message Map
     * @param wrkOrdTMsg VND_RTRN_DTLTMsg
     * @return Normal:true Error:false
     */
    private boolean checkCancelDate(S21ApiMessageMap msgMap, VND_RTRN_DTLTMsg wrkTMsg) {

        NWZC403001PMsg inMsg = (NWZC403001PMsg) msgMap.getPmsg();
        wrkTMsg = (VND_RTRN_DTLTMsg) S21ApiTBLAccessor.findByKey(wrkTMsg);

        // When no record hit
        if (wrkTMsg == null) {
            // add error message
            ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, wrkTMsg.vndRtrnNum.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, wrkTMsg.trxLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, wrkTMsg.trxLineSubNum.getValue());
            this.addMsgId(msgMap, "NWZM0813E");
            return false;
        }

        // When DB error
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wrkTMsg.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, wrkTMsg.vndRtrnNum.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, wrkTMsg.trxLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, wrkTMsg.trxLineSubNum.getValue());
            this.addMsgId(msgMap, "NWZM0813E");
            return false;
        }

        // When already canseled
        String cancelDate = wrkTMsg.vndRtrnDtlCancDt.getValue();
        if (ZYPCommonFunc.hasValue(cancelDate)) {
            // add error message
            ZYPEZDItemValueSetter.setValue(inMsg.vndRtrnNum, wrkTMsg.vndRtrnNum.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineNum, wrkTMsg.trxLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.List.no(0).trxLineSubNum, wrkTMsg.trxLineSubNum.getValue());
            this.addMsgId(msgMap, "NWZM0811E");
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Get cancel date
     * </pre>
     * 
     * @param wrkOrdTMsg VND_RTRN_DTLTMsg
     * @return cancelDate String
     */
    private String getCancelDate(VND_RTRN_DTLTMsg wrkTMsg) {

    	String cancelDate = null;
        wrkTMsg.setSQLID("004");
        wrkTMsg.setMaxCount(1);
        VND_RTRN_DTLTMsgArray wrkTMsgArray = (VND_RTRN_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(wrkTMsg);

        if (wrkTMsgArray.getValidCount() == 0) {
            return null;
        }

        // When DB error
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wrkTMsgArray.no(0).getReturnCode())) {
            return null;
        }

        cancelDate = wrkTMsgArray.no(0).vndRtrnDtlCancDt.getValue();
        if (!ZYPCommonFunc.hasValue(cancelDate)) {
            return null;
        }

        return cancelDate;
    }

    /**
     * <pre>
     * Get closed date
     * </pre>
     * 
     * @param wrkOrdTMsg VND_RTRN_DTLTMsg
     * @return closedDate String
     */
    private String getClosedDate(VND_RTRN_DTLTMsg wrkTMsg) {

    	String closedDate = null;
        wrkTMsg.setSQLID("003");
        wrkTMsg.setMaxCount(1);
        VND_RTRN_DTLTMsgArray wrkTMsgArray = (VND_RTRN_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(wrkTMsg);

        if (wrkTMsgArray.getValidCount() == 0) {
            return null;
        }

        // When DB error
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wrkTMsgArray.no(0).getReturnCode())) {
            return null;
        }

        closedDate = wrkTMsgArray.no(0).vndRtrnDtlCloDt.getValue();
        if (!ZYPCommonFunc.hasValue(closedDate)) {
            return null;
        }

        return closedDate;
    }

    /**
     * Header - Check input parameter.
     * @param msgMap Message Map
     * @param mode Mode of branching processes
     * @return Normal:true Error:false
     */
    private boolean checkInputHeaderCommon(S21ApiMessageMap msgMap, String mode) {

        // IN-parameter PMsg
        NWZC403001PMsg inMsg = (NWZC403001PMsg) msgMap.getPmsg();

        // input parameter null check
        // Global Company Code
        if (!ZYPCommonFunc.hasValue(inMsg.glblCmpyCd)) {
            this.addMsgId(msgMap, "NWZM0789E");
            return false;
        }

        // Vendor Return Number
        if (MODE_CHANGE.equals(mode) || MODE_CLOSED.equals(mode) || MODE_UNDO.equals(mode)) {
            if (!ZYPCommonFunc.hasValue(inMsg.vndRtrnNum)) {
                this.addMsgId(msgMap, "NWZM0791E");
                return false;
            }
        }

        if (MODE_CREATE.equals(mode) || MODE_CHANGE.equals(mode)) {
            // Vendor Return Type Code
            if (!ZYPCommonFunc.hasValue(inMsg.vndRtrnTpCd)) {
                this.addMsgId(msgMap, "NWZM0792E");
                return false;
            }

            // Inventory Location Code
            if (!ZYPCommonFunc.hasValue(inMsg.invtyLocCd)) {
                this.addMsgId(msgMap, "NWZM0793E");
                return false;
            }

            // Requested Delivery Date
            if (!ZYPCommonFunc.hasValue(inMsg.rddDt)) {
                this.addMsgId(msgMap, "NWZM0794E");
                return false;
            }

            // Export Loading Port Code
            if (!ZYPCommonFunc.hasValue(inMsg.exptLoadPortCd)) {
                this.addMsgId(msgMap, "NWZM0795E");
                return false;
            }

            // Ship To Vendor Code
            if (!ZYPCommonFunc.hasValue(inMsg.shipToVndCd)) {
                this.addMsgId(msgMap, "NWZM0796E");
                return false;
            }

            // Payment Term Code
            if (!ZYPCommonFunc.hasValue(inMsg.pmtTermCd)) {
                this.addMsgId(msgMap, "NWZM0797E");
                return false;
            }

            // Payment Method Code
            if (!ZYPCommonFunc.hasValue(inMsg.pmtMethCd)) {
                this.addMsgId(msgMap, "NWZM0798E");
                return false;
            }

            // Payment Condition Code
            if (!ZYPCommonFunc.hasValue(inMsg.pmtCondCd)) {
                this.addMsgId(msgMap, "NWZM0799E");
                return false;
            }

            // US Tax Code
            if (!ZYPCommonFunc.hasValue(inMsg.usTaxCd)) {
                this.addMsgId(msgMap, "NWZM0800E");
                return false;
            }

            // Deal Currency Code
            if (!ZYPCommonFunc.hasValue(inMsg.dealCcyCd)) {
                this.addMsgId(msgMap, "NWZM0801E");
                return false;
            }

            // Shipping Term Code
            if (!ZYPCommonFunc.hasValue(inMsg.shpgTermCd)) {
                this.addMsgId(msgMap, "NWZM0802E");
                return false;
            }
        }
        return true;
    }

    /**
     * Detail - Check input parameter.
     * @param msgMap Message Map
     * @param rowCount Integer
     * @param mode Mode of branching processes
     * @return Normal:true Error:false
     */
    private boolean checkInputDetailCommon(S21ApiMessageMap msgMap, Integer rowCount, String mode) {

        // IN-parameter PMsg
        NWZC403001PMsg inMsg = (NWZC403001PMsg) msgMap.getPmsg();

        // Transaction Line Number
        if (!ZYPCommonFunc.hasValue(inMsg.List.no(rowCount).trxLineNum)) {
            this.addMsgId(msgMap, "NWZM0803E");
            return false;
        }

        // Transaction Line Subordinate Number
        if (!ZYPCommonFunc.hasValue(inMsg.List.no(rowCount).trxLineSubNum)) {
            this.addMsgId(msgMap, "NWZM0804E");
            return false;
        }

        // Vendor Return Detail Cancel Date
        if (MODE_UNDO.equals(mode)) {
            if (!ZYPCommonFunc.hasValue(inMsg.List.no(rowCount).vndRtrnDtlCancDt)) {
                this.addMsgId(msgMap, "NWZM0805E");
                return false;
            }
        }

        // Vendor Return Detail Close Date
        if (MODE_CLOSED.equals(mode)) {
            if (!ZYPCommonFunc.hasValue(inMsg.List.no(rowCount).vndRtrnDtlCloDt)) {
                this.addMsgId(msgMap, "NWZM0806E");
                return false;
            }
        }

        // Vendor Return Submit Date
        if (MODE_CREATE.equals(mode)) {
            if (!ZYPCommonFunc.hasValue(inMsg.List.no(rowCount).vndRtrnSubmtDt_D1)) {
                this.addMsgId(msgMap, "NWZM0807E");
                return false;
            }
        }
        return true;
    }

    private void addMsgId(S21ApiMessageMap msgMap, String msgId) {
        msgMap.addXxMsgId(msgId);
        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("setMsgId:" + msgId);
        }
    }

    private void writeDebugLog(String str) {
        EZDDebugOutput.println(1, "[NWZC403001] " + str, this);
    }

}
