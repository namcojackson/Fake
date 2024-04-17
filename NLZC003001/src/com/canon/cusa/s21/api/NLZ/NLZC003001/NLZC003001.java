/**
 *  <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC003001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDPMsgArray;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDDBRecordLockedException;
import business.db.INVTY_ORDTMsg;
import business.db.INVTY_ORD_DTLTMsg;
import business.db.INVTY_ORD_SERTMsg;
import business.parts.NLZC003001PMsg;
import business.parts.NWZC107001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC107001.NWZC107001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.batch.S21ResultSetMapper;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;

/**
 *<pre>
 * Inventory Order API
 * Create, Update, Close, Cancel for Inventory Order, Inventory Order Detail.
 *</pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/10/2009   Fujitsu         S.Yoshida       Create          N/A
 * 08/19/2009   Fujitsu         S.Yoshida       Update          N/A
 * 10/20/2009   Fujitsu         S.Yoshida       Update          N/A
 * 10/22/2009   Fujitsu         S.Yoshida       Update          815
 * 10/30/2009   Fujitsu         S.Yoshida       Update          1224
 * 12/17/2009   Fujitsu         S.Yoshida       Update          Write Off
 * 04/14/2010   Fujitsu         S.Yoshida       Update          Def.5017
 * 06/10/2010   Fujitsu         M.Yamada        Update          Def.1268
 * 06/22/2010   Fujitsu         M.Yamada        Update          Def.5015
 * 07/19/2012   CSAI            N.Sasaki        Update          ITG#397579
 * 09/16/2015   CITS            T.Tokutomi      Update
 * 05/27/2016   CSAI            K.Lee           Update          QC#9152
 * 04/12/2018   CITS            S.Katsuma       Update          SOL#078,160
 * 06/25/2018   CITS            T.Tokutomi      Update          QC#26823
 *</pre>
 */
public class NLZC003001 extends S21ApiCommonBase {

    // -- Process Type --------------------------
    /** Process Type : Create */
    public static final String PROC_TP_CRAT = "1";

    /** Process Type : Update */
    public static final String PROC_TP_UPD = "2";

    /** Process Type : Close */
    public static final String PROC_TP_CLO = "3";

    /** Process Type : Cancel */
    public static final String PROC_TP_CANC = "4";

    /** Process Type : Header Close */
    public static final String PROC_TP_HDR_CLO = "5";

    // -- Data Type -----------------------------
    /** Data Type : Header */
    public static final String DT_TP_HDR = "H";

    /** Data Type : Detail */
    public static final String DT_TP_DTL = "D";

    // -- Error Message Code --------------------
    /** Invalid Process Type */
    public static final String MSG_ID_NLZM0001E = "NLZM0001E";

    /** Data Type error */
    public static final String MSG_ID_NLZM0047E = "NLZM0047E";

    /** null input parameter Data Company Code */
    public static final String MSG_ID_NLZM0003E = "NLZM0003E";

    /** null input parameter Request Qty */
    public static final String MSG_ID_NLZM0009E = "NLZM0009E";

    /** DB error */
    public static final String MSG_ID_NLZM0044E = "NLZM0044E";

    /** Inventory Order Number is not found */
    public static final String MSG_ID_NLZM0048E = "NLZM0048E";

    /** Inventory Order Line Number is not found */
    public static final String MSG_ID_NLZM0049E = "NLZM0049E";

    /** Inventory Order has been updated by another user. */
    public static final String MSG_ID_NLZM0050E = "NLZM0050E";

    /** Inventory Order is not found */
    public static final String MSG_ID_NLZM0052E = "NLZM0052E";

    /** Inventory Order Detail is not found */
    public static final String MSG_ID_NLZM0053E = "NLZM0053E";

    /** Inventory Order Status is mismatch */
    public static final String MSG_ID_NLZM0054E = "NLZM0054E";

    /** Inventory Order Detail has been updated by another user. */
    public static final String MSG_ID_NLZM0110E = "NLZM0110E";

    /** Inventory Order cann't update status. */
    public static final String MSG_ID_NLZM0111E = "NLZM0111E";

    /** Inventory Order Detail cann't update status. */
    public static final String MSG_ID_NLZM0112E = "NLZM0112E";

    /** null input parameter error */
    public static final String MSG_ID_NLZM0113E = "NLZM0113E";

    /** Input parameter is fraud. */
    public static final String MSG_ID_NLZM0114E = "NLZM0114E";

    /** Inventory Order Type error */
    public static final String MSG_ID_NLZM0115E = "NLZM0115E";

    /** Process Type is fraud */
    public static final String MSG_ID_NLZM0117E = "NLZM0117E";

    // -- Line Type -----------------------------
    /** Line Type : Close */
    public static final String LINE_TP_CLO = "0";

    /** Line Type : Cancel */
    public static final String LINE_TP_CANC = "1";

    // -- Other Internal constants --------------
    /** Normal end */
    private static final String RTNCD_NORMAL_END = "0000";

    /** for Debug */
    private static final int CST_DEBUG_MSG_LVL = 1;

    // -- DB Column ---------------
    /** DV Column : RTL_WH_CD */
    private static final String RTL_WH_CD = "RTL_WH_CD";

    // -- DB Column ---------------
    /** DV Column : RTL_WH_CD */
    private static final String RTL_SWH_CD = "RTL_SWH_CD";

    // -- Other Internal Variable ---------------
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Inventory Order Number */
    private String newInvtyOrdNum = null;

    /** TimeStamp Format */
    private static final String TIMESTAMPFORMAT = "yyyyMMddHHmmssSSS";

    /**
     * <pre>
     * Constructor
     * </pre>
     * @param none
     * @throws none
     */
    public NLZC003001() {
        super();

        // Initialization SSM Batch Client.
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Inventory Order API (List)
     * </pre>
     * @param inpPrmMsg Input parameter list
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(final List<NLZC003001PMsg> inpPrmMsg, final ONBATCH_TYPE onBatchType) {
        for (int i = 0; i < inpPrmMsg.size(); i++) {
            execute(inpPrmMsg.get(i), onBatchType);
        }

        newInvtyOrdNum = null;
    }

    /**
     * <pre>
     * Inventory Order API
     * </pre>
     * @param inpPrmPMsg Input parameter
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(final NLZC003001PMsg inpPrmPMsg, final ONBATCH_TYPE onBatchType) {

        // Create Message Map
        final S21ApiMessageMap msgMap = new S21ApiMessageMap(inpPrmPMsg);

        try {

            // Process Type
            String procTpCd = inpPrmPMsg.xxProcTpCd.getValue();

            // Create
            if (PROC_TP_CRAT.equals(procTpCd)) {
                executeCreate(msgMap, onBatchType);

                // Update
            } else if (PROC_TP_UPD.equals(procTpCd)) {
                executeUpdate(msgMap, onBatchType);

                // Close
            } else if (PROC_TP_CLO.equals(procTpCd)) {
                executeClose(msgMap, onBatchType);

                // Cancel
            } else if (PROC_TP_CANC.equals(procTpCd)) {
                executeCancel(msgMap, onBatchType);

                // Header Close
            } else if (PROC_TP_HDR_CLO.equals(procTpCd)) {
                executeHeadClose(msgMap, onBatchType);

                // Unexpected
            } else {
                this.addMsgId(msgMap, MSG_ID_NLZM0001E);
                return;
            }

            // locked by another user
        } catch (EZDDBRecordLockedException e) {
            printDebugLog("The subjective Inventory Order record has been locked by another user.");
            throw e;

        } finally {

            // Flush Message Map.
            msgMap.flush();
        }
    }

    /**
     * <pre>
     * The create processing is executed. 
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     * @param onBatchType Kind of Online or Batch.
     */
    private void executeCreate(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        // Check the input parameters.
        if (!checkInputCommon(msgMap)) {
            return;
        }
        if (!checkInputCreate(msgMap, onBatchType)) {
            return;
        }

        // Input parameter PMsg
        NLZC003001PMsg inpPrmPMsg = (NLZC003001PMsg) msgMap.getPmsg();

        // Data Type
        String dtTpCd = inpPrmPMsg.xxDtTpCd.getValue();

        // Inventory Order Type
        String invtyOrdTpCd = inpPrmPMsg.invtyOrdTpCd.getValue();

        // Global Company Code
        String glblCmpyCd = inpPrmPMsg.glblCmpyCd.getValue();

        // Get the Inventory Order Number.
        String invtyOrdNum = getNewInvtyOrdNum(glblCmpyCd, onBatchType, dtTpCd);
        if (!ZYPCommonFunc.hasValue(invtyOrdNum)) {
            this.addMsgId(msgMap, MSG_ID_NLZM0114E);
            return;
        }

        // Header
        if (DT_TP_HDR.equals(dtTpCd)) {

            // Create the Inventory Order TMsg.
            INVTY_ORDTMsg invtyOrdTMsg = new INVTY_ORDTMsg();

            // Set the parameters.
            invtyOrdTMsg.glblCmpyCd.setValue(glblCmpyCd);
            invtyOrdTMsg.invtyOrdNum.setValue(invtyOrdNum);
            invtyOrdTMsg.invtyOrdTpCd.setValue(invtyOrdTpCd);
            invtyOrdTMsg.invtyLocCd.setValue(inpPrmPMsg.invtyLocCd.getValue());
            invtyOrdTMsg.locStsCd.setValue(inpPrmPMsg.locStsCd.getValue());
            invtyOrdTMsg.trxCd.setValue(inpPrmPMsg.trxCd.getValue());
            invtyOrdTMsg.trxRsnCd.setValue(inpPrmPMsg.trxRsnCd.getValue());
            invtyOrdTMsg.tocCd.setValue(inpPrmPMsg.tocCd.getValue());
            invtyOrdTMsg.shpgSvcLvlCd.setValue(inpPrmPMsg.shpgSvcLvlCd.getValue());
            invtyOrdTMsg.dcTrnsfRddDt.setValue(inpPrmPMsg.dcTrnsfRddDt.getValue());
            invtyOrdTMsg.dcTrnsfRsdDt.setValue(inpPrmPMsg.dcTrnsfRsdDt.getValue());
            invtyOrdTMsg.prodCtrlCd.setValue(inpPrmPMsg.prodCtrlCd.getValue());
            invtyOrdTMsg.dsplTpCd.setValue(inpPrmPMsg.dsplTpCd.getValue());
            invtyOrdTMsg.insClmNumTxt.setValue(inpPrmPMsg.insClmNumTxt.getValue());
            invtyOrdTMsg.fromInvtyOrdNum.setValue(inpPrmPMsg.fromInvtyOrdNum.getValue());
            if (ZYPCommonFunc.hasValue(inpPrmPMsg.dmgClmCpltFlg)) {
                invtyOrdTMsg.dmgClmCpltFlg.setValue(inpPrmPMsg.dmgClmCpltFlg.getValue());
            } else {
                invtyOrdTMsg.dmgClmCpltFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            invtyOrdTMsg.invtyOrdStsCd.setValue(inpPrmPMsg.invtyOrdStsCd.getValue());
            invtyOrdTMsg.firstInvtyOrdCmntTxt.setValue(inpPrmPMsg.firstInvtyOrdCmntTxt.getValue());
            invtyOrdTMsg.scdInvtyOrdCmntTxt.setValue(inpPrmPMsg.scdInvtyOrdCmntTxt.getValue());
            invtyOrdTMsg.thirdInvtyOrdCmntTxt.setValue(inpPrmPMsg.thirdInvtyOrdCmntTxt.getValue());
            invtyOrdTMsg.frthInvtyOrdCmntTxt.setValue(inpPrmPMsg.frthInvtyOrdCmntTxt.getValue());
            invtyOrdTMsg.trxSrcTpCd.setValue(inpPrmPMsg.trxSrcTpCd.getValue());
            invtyOrdTMsg.sysSrcCd.setValue(inpPrmPMsg.sysSrcCd.getValue());

            ZYPEZDItemValueSetter.setValue(invtyOrdTMsg.adjTrxTpCd, inpPrmPMsg.adjTrxTpCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdTMsg.invtyOrdSubmtTs, ZYPDateUtil.getCurrentSystemTime(TIMESTAMPFORMAT));
            ZYPEZDItemValueSetter.setValue(invtyOrdTMsg.svcConfigMstrPk, inpPrmPMsg.svcConfigMstrPk);
            ZYPEZDItemValueSetter.setValue(invtyOrdTMsg.mdlId, inpPrmPMsg.mdlId);

            // Create the Inventory Order record.
            if (!create(msgMap, invtyOrdTMsg)) {
                return;
            }

            // Detail
        } else if (DT_TP_DTL.equals(dtTpCd)) {

            // Create the Inventory Order Detail TMsg.
            INVTY_ORD_DTLTMsg invtyOrdDtlTMsg = new INVTY_ORD_DTLTMsg();

            // Set the parameters.
            invtyOrdDtlTMsg.glblCmpyCd.setValue(glblCmpyCd);
            invtyOrdDtlTMsg.invtyOrdNum.setValue(invtyOrdNum);
            invtyOrdDtlTMsg.invtyOrdLineNum.setValue(inpPrmPMsg.invtyOrdLineNum.getValue());
            invtyOrdDtlTMsg.mdseCd.setValue(inpPrmPMsg.mdseCd.getValue());
            invtyOrdDtlTMsg.stkStsCd.setValue(inpPrmPMsg.stkStsCd.getValue());
            invtyOrdDtlTMsg.invtyLocCd.setValue(inpPrmPMsg.invtyLocCd_D1.getValue());
            invtyOrdDtlTMsg.locStsCd.setValue(inpPrmPMsg.locStsCd_D1.getValue());
            invtyOrdDtlTMsg.toStkStsCd.setValue(inpPrmPMsg.toStkStsCd.getValue());
            invtyOrdDtlTMsg.ordQty.setValue(inpPrmPMsg.ordQty.getValue());
            invtyOrdDtlTMsg.dmgClmQty.setValue(inpPrmPMsg.dmgClmQty.getValue());
            invtyOrdDtlTMsg.invtyOrdDtlStsCd.setValue(inpPrmPMsg.invtyOrdDtlStsCd.getValue());

            // Get Warehouse code / sub warehouse code
            Map<String, Object> whCdFrm = getWhCdFrmInvtyLocV(glblCmpyCd, inpPrmPMsg.invtyLocCd.getValue());
            Map<String, Object> whCdTo = getWhCdFrmInvtyLocV(glblCmpyCd, inpPrmPMsg.invtyLocCd_D1.getValue());

            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.invtyOrdLineNum, inpPrmPMsg.invtyOrdLineNum);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.prchReqNum, inpPrmPMsg.prchReqNum);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.prchReqLineNum, inpPrmPMsg.prchReqLineNum);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.prchReqLineSubNum, inpPrmPMsg.prchReqLineSubNum);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.prchReqRecTpCd, inpPrmPMsg.prchReqRecTpCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.trxRefSrcTpCd, inpPrmPMsg.trxRefSrcTpCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.trxRefNum, inpPrmPMsg.trxRefNum);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.trxRefLineNum, inpPrmPMsg.trxRefLineNum);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.trxRefLineSubNum, inpPrmPMsg.trxRefLineSubNum);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.fsrNum, inpPrmPMsg.fsrNum);
            if (whCdFrm != null) {
                ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.fromRtlWhCd, (String) whCdFrm.get(RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.fromRtlSwhCd, (String) whCdFrm.get(RTL_SWH_CD));
            }
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.invtyLocCd, inpPrmPMsg.invtyLocCd);
            if (whCdTo != null) {
                ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.toRtlWhCd, (String) whCdTo.get(RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.toRtlSwhCd, (String) whCdTo.get(RTL_SWH_CD));
            }
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.toInvtyLocCd, inpPrmPMsg.invtyLocCd_D1);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.shipToCustCd, inpPrmPMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.adjCatgCd, inpPrmPMsg.adjCatgCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.invtyOrdLineCmntTxt, inpPrmPMsg.invtyOrdLineCmntTxt);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.invtyOrdLineCostAmt, inpPrmPMsg.invtyOrdLineCostAmt);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.adjAcctAliasNm, inpPrmPMsg.adjAcctAliasNm);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.coaCmpyCd, inpPrmPMsg.coaCmpyCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.coaBrCd, inpPrmPMsg.coaBrCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.coaAcctCd, inpPrmPMsg.coaAcctCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.coaProdCd, inpPrmPMsg.coaProdCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.coaChCd, inpPrmPMsg.coaChCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.coaCcCd, inpPrmPMsg.coaCcCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.coaAfflCd, inpPrmPMsg.coaAfflCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.coaExtnCd, inpPrmPMsg.coaExtnCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.coaProjCd, inpPrmPMsg.coaProjCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.svcConfigMstrPk, inpPrmPMsg.svcConfigMstrPk);
            if (ZYPCommonFunc.hasValue(inpPrmPMsg.rmvConfigFlg)) {
                ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.rmvConfigFlg, inpPrmPMsg.rmvConfigFlg);
            } else {
                ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.rmvConfigFlg, ZYPConstant.FLG_OFF_N);
            }

            // Create the Inventory Order Detail record.
            if (!create(msgMap, invtyOrdDtlTMsg)) {
                return;
            }

            // Create the Inventory Order Serial TMsg.
            for (int i = 0; i < inpPrmPMsg.serialInfoList.getValidCount(); i++) {
                INVTY_ORD_SERTMsg invtyOrdSerTMsg = new INVTY_ORD_SERTMsg();

                // Set the parameters.
                ZYPEZDItemValueSetter.setValue(invtyOrdSerTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(invtyOrdSerTMsg.invtyOrdSerPk, ZYPOracleSeqAccessor.getNumberBigDecimal("INVTY_ORD_SER_SQ"));
                ZYPEZDItemValueSetter.setValue(invtyOrdSerTMsg.invtyOrdNum, invtyOrdNum);
                ZYPEZDItemValueSetter.setValue(invtyOrdSerTMsg.invtyOrdLineNum, inpPrmPMsg.serialInfoList.no(i).invtyOrdLineNum);
                ZYPEZDItemValueSetter.setValue(invtyOrdSerTMsg.serNum, inpPrmPMsg.serialInfoList.no(i).serNum);

                // Create the DS Inventory Order Detail record.
                if (!create(msgMap, invtyOrdSerTMsg)) {
                    return;
                }
            }

            // Unexpected
        } else {
            return;
        }

        // set output parameter
        inpPrmPMsg.invtyOrdNum.setValue(invtyOrdNum);
    }

    /**
     * <pre>
     * The update processing is executed.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     * @param onBatchType Kind of Online or Batch.
     */
    private void executeUpdate(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        // Check the input parameters.
        if (!checkInputCommon(msgMap)) {
            return;
        }

        // Input parameter PMsg
        NLZC003001PMsg inpPrmPMsg = (NLZC003001PMsg) msgMap.getPmsg();

        // Data Type
        String dtTpCd = inpPrmPMsg.xxDtTpCd.getValue();

        // Global Company Code
        String glblCmpyCd = inpPrmPMsg.glblCmpyCd.getValue();

        // Inventory Order Number
        String invtyOrdNum = inpPrmPMsg.invtyOrdNum.getValue();

        // Header
        if (DT_TP_HDR.equals(dtTpCd)) {

            // Find the Inventory Order for update.
            INVTY_ORDTMsg invtyOrdTMsg = new INVTY_ORDTMsg();
            invtyOrdTMsg.glblCmpyCd.setValue(glblCmpyCd);
            invtyOrdTMsg.invtyOrdNum.setValue(invtyOrdNum);
            invtyOrdTMsg = (INVTY_ORDTMsg) findForUpdate(msgMap, invtyOrdTMsg);
            if (invtyOrdTMsg == null) {
                return;
            }

            // Check the Time stamp.
            if (!checkTimestamp(msgMap, invtyOrdTMsg)) {
                return;
            }

            // Check the input parameters.
            if (!checkInputUpdate(msgMap, onBatchType, invtyOrdTMsg, null)) {
                return;
            }

            // Set the input parameters.
            invtyOrdTMsg.invtyOrdTpCd.setValue(inpPrmPMsg.invtyOrdTpCd.getValue());
            invtyOrdTMsg.invtyLocCd.setValue(inpPrmPMsg.invtyLocCd.getValue());
            invtyOrdTMsg.locStsCd.setValue(inpPrmPMsg.locStsCd.getValue());
            invtyOrdTMsg.trxCd.setValue(inpPrmPMsg.trxCd.getValue());
            invtyOrdTMsg.trxRsnCd.setValue(inpPrmPMsg.trxRsnCd.getValue());
            invtyOrdTMsg.tocCd.setValue(inpPrmPMsg.tocCd.getValue());
            invtyOrdTMsg.shpgSvcLvlCd.setValue(inpPrmPMsg.shpgSvcLvlCd.getValue());
            invtyOrdTMsg.dcTrnsfRddDt.setValue(inpPrmPMsg.dcTrnsfRddDt.getValue());
            invtyOrdTMsg.dcTrnsfRsdDt.setValue(inpPrmPMsg.dcTrnsfRsdDt.getValue());
            invtyOrdTMsg.prodCtrlCd.setValue(inpPrmPMsg.prodCtrlCd.getValue());
            invtyOrdTMsg.dsplTpCd.setValue(inpPrmPMsg.dsplTpCd.getValue());
            invtyOrdTMsg.insClmNumTxt.setValue(inpPrmPMsg.insClmNumTxt.getValue());
            invtyOrdTMsg.fromInvtyOrdNum.setValue(inpPrmPMsg.fromInvtyOrdNum.getValue());
            if (ZYPCommonFunc.hasValue(inpPrmPMsg.dmgClmCpltFlg)) {
                invtyOrdTMsg.dmgClmCpltFlg.setValue(inpPrmPMsg.dmgClmCpltFlg.getValue());
            }
            invtyOrdTMsg.invtyOrdStsCd.setValue(inpPrmPMsg.invtyOrdStsCd.getValue());
            invtyOrdTMsg.firstInvtyOrdCmntTxt.setValue(inpPrmPMsg.firstInvtyOrdCmntTxt.getValue());
            invtyOrdTMsg.scdInvtyOrdCmntTxt.setValue(inpPrmPMsg.scdInvtyOrdCmntTxt.getValue());
            invtyOrdTMsg.thirdInvtyOrdCmntTxt.setValue(inpPrmPMsg.thirdInvtyOrdCmntTxt.getValue());
            invtyOrdTMsg.frthInvtyOrdCmntTxt.setValue(inpPrmPMsg.frthInvtyOrdCmntTxt.getValue());
            invtyOrdTMsg.trxSrcTpCd.setValue(inpPrmPMsg.trxSrcTpCd.getValue());
            invtyOrdTMsg.sysSrcCd.setValue(inpPrmPMsg.sysSrcCd.getValue());

            ZYPEZDItemValueSetter.setValue(invtyOrdTMsg.adjTrxTpCd, inpPrmPMsg.adjTrxTpCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdTMsg.invtyOrdSubmtTs, ZYPDateUtil.getCurrentSystemTime(TIMESTAMPFORMAT));

            // Update the Inventory Order record.
            if (!update(msgMap, invtyOrdTMsg)) {
                return;
            }

            // Detail
        } else if (DT_TP_DTL.equals(dtTpCd)) {

            // Find the Inventory Order for update.
            INVTY_ORDTMsg invtyOrdTMsg = new INVTY_ORDTMsg();
            invtyOrdTMsg.glblCmpyCd.setValue(glblCmpyCd);
            invtyOrdTMsg.invtyOrdNum.setValue(invtyOrdNum);
            invtyOrdTMsg = (INVTY_ORDTMsg) findByKey(msgMap, invtyOrdTMsg);
            if (invtyOrdTMsg == null) {
                return;
            }

            // Find the Inventory Order Detail for update.
            INVTY_ORD_DTLTMsg invtyOrdDtlTMsg = new INVTY_ORD_DTLTMsg();
            invtyOrdDtlTMsg.glblCmpyCd.setValue(glblCmpyCd);
            invtyOrdDtlTMsg.invtyOrdNum.setValue(invtyOrdNum);
            invtyOrdDtlTMsg.invtyOrdLineNum.setValue(inpPrmPMsg.invtyOrdLineNum.getValue());
            invtyOrdDtlTMsg = (INVTY_ORD_DTLTMsg) findForUpdate(msgMap, invtyOrdDtlTMsg);
            if (invtyOrdDtlTMsg == null) {
                return;
            }

            // Check the Time stamp.
            if (!checkTimestamp(msgMap, invtyOrdDtlTMsg)) {
                return;
            }

            // Check the input parameters.
            if (!checkInputUpdate(msgMap, onBatchType, invtyOrdTMsg, invtyOrdDtlTMsg)) {
                return;
            }

            // Set the parameters.
            invtyOrdDtlTMsg.mdseCd.setValue(inpPrmPMsg.mdseCd.getValue());
            invtyOrdDtlTMsg.stkStsCd.setValue(inpPrmPMsg.stkStsCd.getValue());
            invtyOrdDtlTMsg.invtyLocCd.setValue(inpPrmPMsg.invtyLocCd_D1.getValue());
            invtyOrdDtlTMsg.locStsCd.setValue(inpPrmPMsg.locStsCd_D1.getValue());
            invtyOrdDtlTMsg.toStkStsCd.setValue(inpPrmPMsg.toStkStsCd.getValue());
            invtyOrdDtlTMsg.ordQty.setValue(inpPrmPMsg.ordQty.getValue());
            invtyOrdDtlTMsg.dmgClmQty.setValue(inpPrmPMsg.dmgClmQty.getValue());
            invtyOrdDtlTMsg.invtyOrdDtlStsCd.setValue(inpPrmPMsg.invtyOrdDtlStsCd.getValue());

            // Get Warehouse code / sub warehouse code
            Map<String, Object> whCdFrm = getWhCdFrmInvtyLocV(glblCmpyCd, inpPrmPMsg.invtyLocCd.getValue());
            Map<String, Object> whCdTo = getWhCdFrmInvtyLocV(glblCmpyCd, inpPrmPMsg.invtyLocCd_D1.getValue());

            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.prchReqNum, inpPrmPMsg.prchReqNum);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.prchReqLineNum, inpPrmPMsg.prchReqLineNum);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.prchReqLineSubNum, inpPrmPMsg.prchReqLineSubNum);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.prchReqRecTpCd, inpPrmPMsg.prchReqRecTpCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.trxRefSrcTpCd, inpPrmPMsg.trxRefSrcTpCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.trxRefNum, inpPrmPMsg.trxRefNum);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.trxRefLineNum, inpPrmPMsg.trxRefLineNum);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.trxRefLineSubNum, inpPrmPMsg.trxRefLineSubNum);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.fsrNum, inpPrmPMsg.fsrNum);
            if (whCdFrm != null) {
                ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.fromRtlWhCd, (String) whCdFrm.get(RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.fromRtlSwhCd, (String) whCdFrm.get(RTL_SWH_CD));
            }
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.invtyLocCd, inpPrmPMsg.invtyLocCd);
            if (whCdTo != null) {
                ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.toRtlWhCd, (String) whCdTo.get(RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.toRtlSwhCd, (String) whCdTo.get(RTL_SWH_CD));
            }
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.toInvtyLocCd, inpPrmPMsg.invtyLocCd_D1);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.shipToCustCd, inpPrmPMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.adjCatgCd, inpPrmPMsg.adjCatgCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.invtyOrdLineCmntTxt, inpPrmPMsg.invtyOrdLineCmntTxt);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.invtyOrdLineCostAmt, inpPrmPMsg.invtyOrdLineCostAmt);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.adjAcctAliasNm, inpPrmPMsg.adjAcctAliasNm);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.coaCmpyCd, inpPrmPMsg.coaCmpyCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.coaBrCd, inpPrmPMsg.coaBrCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.coaAcctCd, inpPrmPMsg.coaAcctCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.coaProdCd, inpPrmPMsg.coaProdCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.coaChCd, inpPrmPMsg.coaChCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.coaCcCd, inpPrmPMsg.coaCcCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.coaAfflCd, inpPrmPMsg.coaAfflCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.coaExtnCd, inpPrmPMsg.coaExtnCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.coaProjCd, inpPrmPMsg.coaProjCd);
            ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.svcConfigMstrPk, inpPrmPMsg.svcConfigMstrPk);
            if (ZYPCommonFunc.hasValue(inpPrmPMsg.rmvConfigFlg)) {
                ZYPEZDItemValueSetter.setValue(invtyOrdDtlTMsg.rmvConfigFlg, inpPrmPMsg.rmvConfigFlg);
            }

            // Update the Inventory Order Detail record.
            if (!update(msgMap, invtyOrdDtlTMsg)) {
                return;
            }

            // Update(Submit) the Inventory Order Serial TMsg.
            for (int i = 0; i < inpPrmPMsg.serialInfoList.getValidCount(); i++) {
                INVTY_ORD_SERTMsg invtyOrdSerTMsg = new INVTY_ORD_SERTMsg();
                ZYPEZDItemValueSetter.setValue(invtyOrdSerTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(invtyOrdSerTMsg.invtyOrdSerPk, ZYPOracleSeqAccessor.getNumberBigDecimal("INVTY_ORD_SER_SQ"));
                ZYPEZDItemValueSetter.setValue(invtyOrdSerTMsg.invtyOrdNum, inpPrmPMsg.serialInfoList.no(i).invtyOrdNum);
                ZYPEZDItemValueSetter.setValue(invtyOrdSerTMsg.invtyOrdLineNum, inpPrmPMsg.serialInfoList.no(i).invtyOrdLineNum);
                ZYPEZDItemValueSetter.setValue(invtyOrdSerTMsg.serNum, inpPrmPMsg.serialInfoList.no(i).serNum);

                // Create the DS Inventory Order Detail record.
                if (!create(msgMap, invtyOrdSerTMsg)) {
                    return;
                }
            }
        }
    }

    /**
     * <pre>
     * The close processing is executed.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     * @param onBatchType Kind of Online or Batch.
     */
    private void executeClose(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        // Check the input parameter.
        if (!checkInputCommon(msgMap)) {
            return;
        }

        // Input parameter PMsg
        NLZC003001PMsg inpPrmPMsg = (NLZC003001PMsg) msgMap.getPmsg();

        // Data Type
        String dtTpCd = inpPrmPMsg.xxDtTpCd.getValue();

        // Global Company Code
        String glblCmpyCd = inpPrmPMsg.glblCmpyCd.getValue();

        // Inventory Order Number
        String invtyOrdNum = inpPrmPMsg.invtyOrdNum.getValue();

        // Header
        if (DT_TP_HDR.equals(dtTpCd)) {

            // Find the Inventory Order for update.
            INVTY_ORDTMsg invtyOrdTMsg = new INVTY_ORDTMsg();
            invtyOrdTMsg.glblCmpyCd.setValue(glblCmpyCd);
            invtyOrdTMsg.invtyOrdNum.setValue(invtyOrdNum);
            invtyOrdTMsg = (INVTY_ORDTMsg) findForUpdate(msgMap, invtyOrdTMsg);
            if (invtyOrdTMsg == null) {
                return;
            }
            if (INVTY_ORD_TP.ADJUSTMENT.equals(invtyOrdTMsg.invtyOrdTpCd.getValue())) {
                invtyOrdTMsg.firstInvtyOrdCmntTxt.setValue(inpPrmPMsg.firstInvtyOrdCmntTxt.getValue());
                invtyOrdTMsg.scdInvtyOrdCmntTxt.setValue(inpPrmPMsg.scdInvtyOrdCmntTxt.getValue());
                invtyOrdTMsg.thirdInvtyOrdCmntTxt.setValue(inpPrmPMsg.thirdInvtyOrdCmntTxt.getValue());
                invtyOrdTMsg.frthInvtyOrdCmntTxt.setValue(inpPrmPMsg.frthInvtyOrdCmntTxt.getValue());
            }

            // Check the Time stamp.
            if (!checkTimestamp(msgMap, invtyOrdTMsg)) {
                return;
            }

            // Check the input parameters.
            if (!checkInputClose(msgMap, onBatchType, invtyOrdTMsg, null)) {
                return;
            }

            // Update the Inventory Order Detail.
            if (!updateInvtyOrdDtls(msgMap, onBatchType, invtyOrdTMsg)) {
                return;
            }

            // Set the parameter.
            invtyOrdTMsg.invtyOrdStsCd.setValue(INVTY_ORD_STS.CLOSED);

            ZYPEZDItemValueSetter.setValue(invtyOrdTMsg.invtyOrdCloseTs, ZYPDateUtil.getCurrentSystemTime(TIMESTAMPFORMAT));

            // Update the Inventory Order record.
            if (!update(msgMap, invtyOrdTMsg)) {
                return;
            }

            // Detail
        } else if (DT_TP_DTL.equals(dtTpCd)) {

            // Find the Inventory Order.
            INVTY_ORDTMsg invtyOrdTMsg = new INVTY_ORDTMsg();
            invtyOrdTMsg.glblCmpyCd.setValue(glblCmpyCd);
            invtyOrdTMsg.invtyOrdNum.setValue(invtyOrdNum);
            invtyOrdTMsg = (INVTY_ORDTMsg) findByKey(msgMap, invtyOrdTMsg);
            if (invtyOrdTMsg == null) {
                return;
            }

            // Find the Inventory Order Detail for update.
            INVTY_ORD_DTLTMsg invtyOrdDtlTMsg = new INVTY_ORD_DTLTMsg();
            invtyOrdDtlTMsg.glblCmpyCd.setValue(glblCmpyCd);
            invtyOrdDtlTMsg.invtyOrdNum.setValue(invtyOrdNum);
            invtyOrdDtlTMsg.invtyOrdLineNum.setValue(inpPrmPMsg.invtyOrdLineNum.getValue());
            invtyOrdDtlTMsg = (INVTY_ORD_DTLTMsg) findForUpdate(msgMap, invtyOrdDtlTMsg);
            if (invtyOrdDtlTMsg == null) {
                return;
            }

            // Check the Time stamp.
            if (!checkTimestamp(msgMap, invtyOrdDtlTMsg)) {
                return;
            }

            // Check the input parameters.
            if (!checkInputClose(msgMap, onBatchType, invtyOrdTMsg, invtyOrdDtlTMsg)) {
                return;
            }

            // Set the parameter.
            invtyOrdDtlTMsg.invtyOrdDtlStsCd.setValue(INVTY_ORD_STS.CLOSED);

            // Update the Inventory Order Detail record.
            if (!update(msgMap, invtyOrdDtlTMsg)) {
                return;
            }
        }
    }

    /**
     * <pre>
     * The cancel processing is executed.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     * @param onBatchType Kind of Online or Batch.
     */
    private void executeCancel(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        // Check the input parameter.
        if (!checkInputCommon(msgMap)) {
            return;
        }

        // Input parameter PMsg
        NLZC003001PMsg inpPrmPMsg = (NLZC003001PMsg) msgMap.getPmsg();

        // Data Type
        String dtTpCd = inpPrmPMsg.xxDtTpCd.getValue();

        // Global Company Code
        String glblCmpyCd = inpPrmPMsg.glblCmpyCd.getValue();

        // Inventory Order Number
        String invtyOrdNum = inpPrmPMsg.invtyOrdNum.getValue();

        // Inventory Order Type
        String invtyOrdTp = null;

        // Header
        if (DT_TP_HDR.equals(dtTpCd)) {

            // Find the Inventory Order for update.
            INVTY_ORDTMsg invtyOrdTMsg = new INVTY_ORDTMsg();
            invtyOrdTMsg.glblCmpyCd.setValue(glblCmpyCd);
            invtyOrdTMsg.invtyOrdNum.setValue(invtyOrdNum);
            invtyOrdTMsg = (INVTY_ORDTMsg) findForUpdate(msgMap, invtyOrdTMsg);
            if (invtyOrdTMsg == null) {
                return;
            }

            // Check the Time stamp.
            if (!checkTimestamp(msgMap, invtyOrdTMsg)) {
                return;
            }

            // Check the input parameters.
            if (!checkInputCancel(msgMap, onBatchType, invtyOrdTMsg, null)) {
                return;
            }

            // Update the Inventory Order Detail.
            if (!updateInvtyOrdDtls(msgMap, onBatchType, invtyOrdTMsg)) {
                return;
            }

            // Inventory Order Type = Damaged Claim(Comp)
            // Damage Claim(Enty)'s Damage Claim Completion Flag is Y
            // to N.
            invtyOrdTp = invtyOrdTMsg.invtyOrdTpCd.getValue();
            if (INVTY_ORD_TP.DAMAGED_CLAIM_COMP.equals(invtyOrdTp)) {

                // Find the Inventory Order for update. (Damage
                // Claim(Entry))
                INVTY_ORDTMsg invtyOrdEntryTMsg = new INVTY_ORDTMsg();
                invtyOrdEntryTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
                invtyOrdEntryTMsg.invtyOrdNum.setValue(invtyOrdTMsg.fromInvtyOrdNum.getValue());
                invtyOrdEntryTMsg = (INVTY_ORDTMsg) findForUpdate(msgMap, invtyOrdEntryTMsg);
                if (invtyOrdEntryTMsg == null) {
                    return;
                }

                // Damage Claim Completion Flag = Y
                if (ZYPConstant.FLG_ON_Y.equals(invtyOrdEntryTMsg.dmgClmCpltFlg.getValue())) {

                    // Update the Damage Claim Completion Flag is N.
                    invtyOrdEntryTMsg.dmgClmCpltFlg.setValue(ZYPConstant.FLG_OFF_N);
                    if (!update(msgMap, invtyOrdEntryTMsg)) {
                        return;
                    }
                }
            }

            // Update the Inventory Order Status is cancel.
            invtyOrdTMsg.invtyOrdStsCd.setValue(INVTY_ORD_STS.CANCEL);

            ZYPEZDItemValueSetter.setValue(invtyOrdTMsg.invtyOrdCancTs, ZYPDateUtil.getCurrentSystemTime(TIMESTAMPFORMAT));

            if (!update(msgMap, invtyOrdTMsg)) {
                return;
            }

            // Detail
        } else if (DT_TP_DTL.equals(dtTpCd)) {

            // Inventory Order Line Number
            String invtyOrdLineNum = inpPrmPMsg.invtyOrdLineNum.getValue();

            // Find the Inventory Order.
            INVTY_ORDTMsg invtyOrdTMsg = new INVTY_ORDTMsg();
            invtyOrdTMsg.glblCmpyCd.setValue(glblCmpyCd);
            invtyOrdTMsg.invtyOrdNum.setValue(invtyOrdNum);
            invtyOrdTMsg = (INVTY_ORDTMsg) findByKey(msgMap, invtyOrdTMsg);
            if (invtyOrdTMsg == null) {
                return;
            }

            // Find the Inventory Order Detail for update.
            INVTY_ORD_DTLTMsg invtyOrdDtlTMsg = new INVTY_ORD_DTLTMsg();
            invtyOrdDtlTMsg.glblCmpyCd.setValue(glblCmpyCd);
            invtyOrdDtlTMsg.invtyOrdNum.setValue(invtyOrdNum);
            invtyOrdDtlTMsg.invtyOrdLineNum.setValue(invtyOrdLineNum);
            invtyOrdDtlTMsg = (INVTY_ORD_DTLTMsg) findForUpdate(msgMap, invtyOrdDtlTMsg);
            if (invtyOrdDtlTMsg == null) {
                return;
            }

            // Check the Time stamp.
            if (!checkTimestamp(msgMap, invtyOrdDtlTMsg)) {
                return;
            }

            // Check the input parameters.
            if (!checkInputCancel(msgMap, onBatchType, invtyOrdTMsg, invtyOrdDtlTMsg)) {
                return;
            }

            // Inventory Order Type = Damaged Claim(Comp)
            invtyOrdTp = invtyOrdTMsg.invtyOrdTpCd.getValue();
            if (INVTY_ORD_TP.DAMAGED_CLAIM_COMP.equals(invtyOrdTp)) {

                // From Inventory Order Number
                String fromInvtyOrdNum = invtyOrdTMsg.fromInvtyOrdNum.getValue();

                // Find the Inventory Order Detail for update. (Damage
                // Claim(Entry))
                INVTY_ORD_DTLTMsg invtyOrdDtlEntryTMsg = new INVTY_ORD_DTLTMsg();
                invtyOrdDtlEntryTMsg.glblCmpyCd.setValue(glblCmpyCd);
                invtyOrdDtlEntryTMsg.invtyOrdNum.setValue(fromInvtyOrdNum);
                invtyOrdDtlEntryTMsg.invtyOrdLineNum.setValue(invtyOrdLineNum);
                invtyOrdDtlEntryTMsg = (INVTY_ORD_DTLTMsg) findForUpdate(msgMap, invtyOrdDtlEntryTMsg);
                if (invtyOrdDtlEntryTMsg == null) {
                    return;
                }

                // back Completion Qty
                // Detail(Entry).Damage Claim Qty =
                // Detail(Entry).Damage Claim Qty - Detail(Comp).Order
                // Qty
                try {
                    invtyOrdDtlEntryTMsg.dmgClmQty.setValue(invtyOrdDtlEntryTMsg.dmgClmQty.getValue().subtract(invtyOrdDtlTMsg.ordQty.getValue()));
                } catch (EZDAbendException e) {
                    EZDDebugOutput.println(1, e.getMessage(), null);
                }

                // Update the Inventory Order record.
                if (!update(msgMap, invtyOrdDtlEntryTMsg)) {
                    return;
                }

                // Find the Inventory Order for update. (Damage
                // Claim(Comp))
                INVTY_ORDTMsg invtyOrdEntryTMsg = new INVTY_ORDTMsg();
                invtyOrdEntryTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
                invtyOrdEntryTMsg.invtyOrdNum.setValue(fromInvtyOrdNum);
                invtyOrdEntryTMsg = (INVTY_ORDTMsg) findForUpdate(msgMap, invtyOrdEntryTMsg);
                if (invtyOrdEntryTMsg == null) {
                    return;
                }

                // Damage Claim Completion Flag = Y
                if (ZYPConstant.FLG_ON_Y.equals(invtyOrdEntryTMsg.dmgClmCpltFlg.getValue())) {

                    // Update the Damage Claim Completion Flag is N.
                    invtyOrdEntryTMsg.dmgClmCpltFlg.setValue(ZYPConstant.FLG_OFF_N);
                    if (!update(msgMap, invtyOrdEntryTMsg)) {
                        return;
                    }
                }
            }

            // AllocationのCancel
            // Inventory Order Status is not Cancel.
            if (!INVTY_ORD_STS.CANCEL.equals(invtyOrdDtlTMsg.invtyOrdDtlStsCd.getValue())) {

                // is not Item change or Item change and order qty > 0
                if (checkAllocation(invtyOrdTp, invtyOrdDtlTMsg.ordQty.getValue())) {

                    // START 2018/04/12 S.Katsuma [SOL#078,160,ADD]
                    // Execute Allocation for non CPO API.
                    // QC#26823 Update. Add conditional statement.
                    if (!(ADJ_TRX_TP.ADJUSTMENT.equals(invtyOrdTMsg.adjTrxTpCd.getValue()) //
                            || ADJ_TRX_TP.CYCLE_COUNT_ADJUSTMENTS.equals(invtyOrdTMsg.adjTrxTpCd.getValue()) //
                            || ADJ_TRX_TP.PHYSICAL_INVENTORY_ADJUSTMENT.equals(invtyOrdTMsg.adjTrxTpCd.getValue()))) {
                        if (!executeAllocNonCpoApi(msgMap, onBatchType, invtyOrdTMsg, invtyOrdDtlTMsg)) {
                            return;
                        }
                    }
                    // END 2018/04/12 S.Katsuma [SOL#078,160,ADD]
                }
            }

            // Set the Inventory Order DetailStatus is cancel.
            invtyOrdDtlTMsg.invtyOrdDtlStsCd.setValue(INVTY_ORD_STS.CANCEL);

            // Update the Inventory Order Detail record.
            if (!update(msgMap, invtyOrdDtlTMsg)) {
                return;
            }
        }
    }

    /**
     * <pre>
     * The Header Close processing is executed.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     * @param onBatchType Kind of Online or Batch.
     */
    private void executeHeadClose(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        // Check the input parameter.
        if (!checkInputCommon(msgMap)) {
            return;
        }

        // Input parameter PMsg
        NLZC003001PMsg inpPrmPMsg = (NLZC003001PMsg) msgMap.getPmsg();

        // Global Company Code
        String glblCmpyCd = inpPrmPMsg.glblCmpyCd.getValue();

        // Inventory Order Number
        String invtyOrdNum = inpPrmPMsg.invtyOrdNum.getValue();

        // Find the Inventory Order for update.
        INVTY_ORDTMsg invtyOrdTMsg = new INVTY_ORDTMsg();
        invtyOrdTMsg.glblCmpyCd.setValue(glblCmpyCd);
        invtyOrdTMsg.invtyOrdNum.setValue(invtyOrdNum);
        invtyOrdTMsg = (INVTY_ORDTMsg) findForUpdate(msgMap, invtyOrdTMsg);
        if (invtyOrdTMsg == null) {
            return;
        }

        // Check the Time stamp.
        if (!checkTimestamp(msgMap, invtyOrdTMsg)) {
            return;
        }

        // Check the input parameters.
        if (!checkInputHeadClose(msgMap, onBatchType, invtyOrdTMsg, null)) {
            return;
        }

        // Check the Inventory Order Detail.
        inpPrmPMsg.xxLineTpCd.setValue(LINE_TP_CANC);
        if (!updateInvtyOrdDtls(msgMap, onBatchType, invtyOrdTMsg)) {
            return;
        }

        // Set the parameter.
        if (LINE_TP_CANC.equals(inpPrmPMsg.xxLineTpCd.getValue())) {
            invtyOrdTMsg.invtyOrdStsCd.setValue(INVTY_ORD_STS.CANCEL);
        } else {
            invtyOrdTMsg.invtyOrdStsCd.setValue(INVTY_ORD_STS.CLOSED);
        }

        if (LINE_TP_CANC.equals(inpPrmPMsg.xxLineTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(invtyOrdTMsg.invtyOrdCancTs, ZYPDateUtil.getCurrentSystemTime(TIMESTAMPFORMAT));
        } else {
            ZYPEZDItemValueSetter.setValue(invtyOrdTMsg.invtyOrdCloseTs, ZYPDateUtil.getCurrentSystemTime(TIMESTAMPFORMAT));
        }

        // Update the Inventory Order record.
        if (!update(msgMap, invtyOrdTMsg)) {
            return;
        }
    }

    /**
     * <pre>
     * Check the input parameters.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     * @return Result (true:Normal, false:Error)
     */
    private boolean checkInputCommon(S21ApiMessageMap msgMap) {

        // Input parameter PMsg
        NLZC003001PMsg inpPrmPMsg = (NLZC003001PMsg) msgMap.getPmsg();

        // Check the Data Type.
        String dtTpCd = inpPrmPMsg.xxDtTpCd.getValue();
        if (!ZYPCommonFunc.hasValue(dtTpCd) || (!DT_TP_HDR.equals(dtTpCd) && !DT_TP_DTL.equals(dtTpCd))) {
            this.addMsgId(msgMap, MSG_ID_NLZM0047E);
            return false;
        }

        // Check the Global Company Code.
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.glblCmpyCd)) {
            this.addMsgId(msgMap, MSG_ID_NLZM0003E);
            return false;
        }

        // Check the Inventory Order Number.
        if (!PROC_TP_CRAT.equals(inpPrmPMsg.xxProcTpCd.getValue()) && !ZYPCommonFunc.hasValue(inpPrmPMsg.invtyOrdNum)) {
            this.addMsgId(msgMap, MSG_ID_NLZM0048E);
            return false;
        }

        // Data Type = Detail
        // Check the Inventory Order Line Number.
        if (DT_TP_DTL.equals(dtTpCd) && !ZYPCommonFunc.hasValue(inpPrmPMsg.invtyOrdLineNum)) {
            this.addMsgId(msgMap, MSG_ID_NLZM0049E);
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Inventory Order Type check.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param invtyOrdTpCd Inventory Order Type
     * @param msgMap Message Map
     * @return Result (true:Normal, false:Error)
     */
    private boolean checkInvtyOrdTp(String invtyOrdTpCd, S21ApiMessageMap msgMap) {

        // Check the Inventory Order Type.
        if (ZYPCommonFunc.hasValue(invtyOrdTpCd) 
                && (INVTY_ORD_TP.ADJUSTMENT.equals(invtyOrdTpCd) //
                 || INVTY_ORD_TP.DISPOSAL.equals(invtyOrdTpCd) //
                 || INVTY_ORD_TP.ITEM_CHANGE.equals(invtyOrdTpCd) //
                 || INVTY_ORD_TP.DC_TRANSFER.equals(invtyOrdTpCd) //
                 || INVTY_ORD_TP.INTERNAL_DC_TRANSFER.equals(invtyOrdTpCd) //
                 || INVTY_ORD_TP.STOCK_STATUS_CHANGE.equals(invtyOrdTpCd) //
                 || INVTY_ORD_TP.SUB_WH_CHANGE.equals(invtyOrdTpCd) //
                 || INVTY_ORD_TP.CONFIGURATION_CHANGE.equals(invtyOrdTpCd) //
                 || INVTY_ORD_TP.SUBCONTRACT.equals(invtyOrdTpCd) //
                 || INVTY_ORD_TP.REFURBISH_OUT.equals(invtyOrdTpCd) //
                )) {
            return true;
        }

        this.addMsgId(msgMap, MSG_ID_NLZM0115E);
        return false;
    }

    /**
     * <pre>
     * Check the Input parameters. (Create)
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     * @param onBatchType Kind of Online or Batch.
     * @return Result (true:Normal, false:Error)
     */
    private boolean checkInputCreate(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        // Input parameters PMsg
        NLZC003001PMsg inpPrmPMsg = (NLZC003001PMsg) msgMap.getPmsg();

        // Data Type
        String dtTpCd = inpPrmPMsg.xxDtTpCd.getValue();

        // Inventory Order Type
        String invtyOrdTpCd = inpPrmPMsg.invtyOrdTpCd.getValue();

        // Check the Inventory Order Type.
        if (!checkInvtyOrdTp(invtyOrdTpCd, msgMap)) {
            return false;
        }

        // Check on each Data Type
        // Header
        if (DT_TP_HDR.equals(dtTpCd)) {

            // Check the Mandatory input parameter. (Common)
            if (!ZYPCommonFunc.hasValue(inpPrmPMsg.invtyLocCd) //
                    || !ZYPCommonFunc.hasValue(inpPrmPMsg.trxCd) //
                    || !ZYPCommonFunc.hasValue(inpPrmPMsg.trxRsnCd) //
                    || !ZYPCommonFunc.hasValue(inpPrmPMsg.trxSrcTpCd) //
                    || !ZYPCommonFunc.hasValue(inpPrmPMsg.sysSrcCd)) {
                this.addMsgId(msgMap, MSG_ID_NLZM0113E);
                return false;
            }

            // Check the Mandatory input parameter. (not Adjustment)
            if (!INVTY_ORD_TP.ADJUSTMENT.equals(invtyOrdTpCd) && !ZYPCommonFunc.hasValue(inpPrmPMsg.locStsCd)) {
                this.addMsgId(msgMap, MSG_ID_NLZM0113E);
                return false;
            }

            // Check the Inventory Order Status.
            if (!ZYPCommonFunc.hasValue(inpPrmPMsg.invtyOrdStsCd)) {
                this.addMsgId(msgMap, MSG_ID_NLZM0113E);
                return false;
            }

            // Check the Mandatory input parameter.
            if (INVTY_ORD_TP.DISPOSAL.equals(invtyOrdTpCd)
                    || INVTY_ORD_TP.DC_TRANSFER.equals(invtyOrdTpCd)
                    || INVTY_ORD_TP.INTERNAL_DC_TRANSFER.equals(invtyOrdTpCd)
                    || INVTY_ORD_TP.SUBCONTRACT.equals(invtyOrdTpCd)
                    || INVTY_ORD_TP.REFURBISH_OUT.equals(invtyOrdTpCd)) {

                // Shipping Service Level
                if (!ZYPCommonFunc.hasValue(inpPrmPMsg.shpgSvcLvlCd)) {
                    this.addMsgId(msgMap, MSG_ID_NLZM0113E);
                    return false;
                }

                // DC-Transfer Request Delivery Date
                // DC-Transfer Request Ship Date
                if ((!ZYPCommonFunc.hasValue(inpPrmPMsg.dcTrnsfRddDt) && !ZYPCommonFunc.hasValue(inpPrmPMsg.dcTrnsfRsdDt)) //
                        || (ZYPCommonFunc.hasValue(inpPrmPMsg.dcTrnsfRddDt) && ZYPCommonFunc.hasValue(inpPrmPMsg.dcTrnsfRsdDt))) {
                    this.addMsgId(msgMap, MSG_ID_NLZM0113E);
                    return false;
                }
            }

            // Check the Mandatory input parameter for Disposal
            if (INVTY_ORD_TP.DISPOSAL.equals(invtyOrdTpCd)) {

                // onBatchType is Batch
                if (ONBATCH_TYPE.BATCH.equals(onBatchType)) {

                    // Product Control Code
                    if (!ZYPCommonFunc.hasValue(inpPrmPMsg.prodCtrlCd)) {
                        this.addMsgId(msgMap, MSG_ID_NLZM0113E);
                        return false;
                    }
                }

                // Disposal Type Code
                if (!ZYPCommonFunc.hasValue(inpPrmPMsg.dsplTpCd)) {
                    this.addMsgId(msgMap, MSG_ID_NLZM0113E);
                    return false;
                }
            }

            // Detail
        } else if (DT_TP_DTL.equals(dtTpCd)) {

            // Check the Mandatory input parameter. (Common)
            if (!ZYPCommonFunc.hasValue(inpPrmPMsg.invtyOrdLineNum) //
                    || !ZYPCommonFunc.hasValue(inpPrmPMsg.mdseCd) //
                    || !ZYPCommonFunc.hasValue(inpPrmPMsg.stkStsCd) //
                    || !ZYPCommonFunc.hasValue(inpPrmPMsg.invtyLocCd) //
                    || !ZYPCommonFunc.hasValue(inpPrmPMsg.locStsCd_D1)) {
                this.addMsgId(msgMap, MSG_ID_NLZM0113E);
                return false;
            }

            // Check the Order Qty.
            BigDecimal ordQty = inpPrmPMsg.ordQty.getValue();
            if (!ZYPCommonFunc.hasValue(ordQty) || BigDecimal.ZERO.compareTo(ordQty) == 0) {
                this.addMsgId(msgMap, MSG_ID_NLZM0009E);
                return false;
            }

            // Check the Inventory Order Detail Status.
            if (!ZYPCommonFunc.hasValue(inpPrmPMsg.invtyOrdDtlStsCd)) {
                this.addMsgId(msgMap, MSG_ID_NLZM0113E);
                return false;
            }

            // Check the Mandatory input parameter by Inventory Order Type
            if (INVTY_ORD_TP.DC_TRANSFER.equals(invtyOrdTpCd)
                    || INVTY_ORD_TP.INTERNAL_DC_TRANSFER.equals(invtyOrdTpCd)
                    || INVTY_ORD_TP.SUB_WH_CHANGE.equals(invtyOrdTpCd)
                    || INVTY_ORD_TP.CONFIGURATION_CHANGE.equals(invtyOrdTpCd)
                    || INVTY_ORD_TP.SUBCONTRACT.equals(invtyOrdTpCd)) {

                // Location Code
                if (!ZYPCommonFunc.hasValue(inpPrmPMsg.invtyLocCd_D1)) {
                    this.addMsgId(msgMap, MSG_ID_NLZM0113E);
                    return false;
                }

                // Stock Status Change
            } else if (INVTY_ORD_TP.STOCK_STATUS_CHANGE.equals(invtyOrdTpCd)) {

                // To Status Code
                if (!ZYPCommonFunc.hasValue(inpPrmPMsg.toStkStsCd)) {
                    this.addMsgId(msgMap, MSG_ID_NLZM0113E);
                    return false;
                }
            }

            // Unexpected
        } else {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Check the Input parameters. (Update)
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     * @param onBatchType Kind of Online or Batch.
     * @return Result (true:Normal, false:Error)
     */
    private boolean checkInputUpdate(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, INVTY_ORDTMsg invtyOrdTMsg, INVTY_ORD_DTLTMsg invtyOrdDtlTMsg) {

        // Input parameter PMsg
        NLZC003001PMsg inpPrmPMsg = (NLZC003001PMsg) msgMap.getPmsg();

        // Data Type
        String dtTpCd = inpPrmPMsg.xxDtTpCd.getValue();

        // Inventory Order Type
        String invtyOrdTpCd = invtyOrdTMsg.invtyOrdTpCd.getValue();

        // Check the Inventory Order Type.
        if (!checkInvtyOrdTp(invtyOrdTpCd, msgMap)) {
            return false;
        }

        // Check on each Data Type.
        // Header
        if (DT_TP_HDR.equals(dtTpCd)) {

            // Inventory Order Status(Input)
            String invtyOrdStsInp = inpPrmPMsg.invtyOrdStsCd.getValue();
            if (!ZYPCommonFunc.hasValue(invtyOrdStsInp)) {
                this.addMsgId(msgMap, MSG_ID_NLZM0113E);
                return false;
            }

            // Detail
        } else if (DT_TP_DTL.equals(dtTpCd)) {

            // Check the Inventory Order Detail record.
            if (invtyOrdDtlTMsg == null) {
                this.addMsgId(msgMap, MSG_ID_NLZM0113E);
                return false;
            }

            // Inventory Order Detail Status(Input)
            String invtyOrdDtlStsInp = inpPrmPMsg.invtyOrdDtlStsCd.getValue();
            if (!ZYPCommonFunc.hasValue(invtyOrdDtlStsInp)) {
                this.addMsgId(msgMap, MSG_ID_NLZM0113E);
                return false;
            }

            // Damaged Claim(Entry)
            if (INVTY_ORD_TP.DAMAGED_CLAIM_ENTRY.equals(invtyOrdTpCd)) {

                // Damaged Claim Qty
                if (!ZYPCommonFunc.hasValue(inpPrmPMsg.dmgClmQty)) {
                    this.addMsgId(msgMap, MSG_ID_NLZM0009E);
                    return false;
                }
            }

            // Unexpected
        } else {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Check the Input parameters. (Close)
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     * @param onBatchType Kind of Online or Batch.
     * @return Result (true:Normal, false:Error)
     */
    private boolean checkInputClose(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, INVTY_ORDTMsg invtyOrdTMsg, INVTY_ORD_DTLTMsg invtyOrdDtlTMsg) {

        // Input parameter PMsg
        NLZC003001PMsg inpPrmPMsg = (NLZC003001PMsg) msgMap.getPmsg();

        // Data Type
        String dtTpCd = inpPrmPMsg.xxDtTpCd.getValue();

        // Inventory Order Type
        String invtyOrdTpCd = invtyOrdTMsg.invtyOrdTpCd.getValue();

        // Check the Inventory Order Type.
        if (!checkInvtyOrdTp(invtyOrdTpCd, msgMap)) {
            return false;
        }
        if (INVTY_ORD_TP.LOSS_ON_SHIPMENT.equals(invtyOrdTpCd) || INVTY_ORD_TP.LOSS_ON_RECEIVING.equals(invtyOrdTpCd)) {
            this.addMsgId(msgMap, MSG_ID_NLZM0117E);
            return false;
        }

        // Check on each Data Type.
        // Header
        if (DT_TP_HDR.equals(dtTpCd)) {

            // Inventory Order Status(DB) is Null or Cancel.
            String invtyOrdStsDb = invtyOrdTMsg.invtyOrdStsCd.getValue();
            if (!ZYPCommonFunc.hasValue(invtyOrdStsDb) || INVTY_ORD_STS.CANCEL.equals(invtyOrdStsDb)) {
                this.addMsgId(msgMap, MSG_ID_NLZM0111E);
                return false;
            }

            // Detail
        } else if (DT_TP_DTL.equals(dtTpCd)) {

            // Inventory Order Detail
            if (invtyOrdDtlTMsg == null) {
                this.addMsgId(msgMap, MSG_ID_NLZM0113E);
                return false;
            }

            // Inventory Order Detail Status(DB) is Null or Cancel.
            String invtyOrdDtlStsDb = invtyOrdDtlTMsg.invtyOrdDtlStsCd.getValue();
            if (!ZYPCommonFunc.hasValue(invtyOrdDtlStsDb) || INVTY_ORD_STS.CANCEL.equals(invtyOrdDtlStsDb)) {
                this.addMsgId(msgMap, MSG_ID_NLZM0112E);
                return false;
            }

            // Unexpected
        } else {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Check the Input parameters. (Cancel)
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     * @param onBatchType Kind of Online or Batch.
     * @return Result (true:Normal, false:Error)
     */
    private boolean checkInputCancel(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, INVTY_ORDTMsg invtyOrdTMsg, INVTY_ORD_DTLTMsg invtyOrdDtlTMsg) {

        // Input parameter PMsg
        NLZC003001PMsg inpPrmPMsg = (NLZC003001PMsg) msgMap.getPmsg();

        // Data Type
        String dtTpCd = inpPrmPMsg.xxDtTpCd.getValue();

        // Inventory Order Type
        String invtyOrdTpCd = invtyOrdTMsg.invtyOrdTpCd.getValue();

        // Check the Inventory Order Type.
        if (!checkInvtyOrdTp(invtyOrdTpCd, msgMap)) {
            return false;
        }
        // START 2018/04/12 S.Katsuma [SOL#078,160,MOD]
//        if (INVTY_ORD_TP.LOSS_ON_SHIPMENT.equals(invtyOrdTpCd) || INVTY_ORD_TP.LOSS_ON_RECEIVING.equals(invtyOrdTpCd) || INVTY_ORD_TP.ADJUSTMENT.equals(invtyOrdTpCd)) {
        if (INVTY_ORD_TP.LOSS_ON_SHIPMENT.equals(invtyOrdTpCd) || INVTY_ORD_TP.LOSS_ON_RECEIVING.equals(invtyOrdTpCd)) {
            this.addMsgId(msgMap, MSG_ID_NLZM0117E);
            return false;
        }
        // END 2018/04/12 S.Katsuma [SOL#078,160,MOD]

        // Check on each Data Type.
        // Header
        if (DT_TP_HDR.equals(dtTpCd)) {

            // Inventory Order Status(DB) is Null or Closed.
            String invtyOrdStsCdDb = invtyOrdTMsg.invtyOrdStsCd.getValue();
            if (!ZYPCommonFunc.hasValue(invtyOrdStsCdDb) || INVTY_ORD_STS.CLOSED.equals(invtyOrdStsCdDb)) {
                this.addMsgId(msgMap, MSG_ID_NLZM0111E);
                return false;
            }

            // Detail
        } else if (DT_TP_DTL.equals(dtTpCd)) {

            // Inventory Order Detail
            if (invtyOrdDtlTMsg == null) {
                this.addMsgId(msgMap, MSG_ID_NLZM0113E);
                return false;
            }

            // Inventory Order Detail Status(DB) is Null or Closed.
            String invtyOrdDtlStsDb = invtyOrdDtlTMsg.invtyOrdDtlStsCd.getValue();
            if (!ZYPCommonFunc.hasValue(invtyOrdDtlStsDb) || INVTY_ORD_STS.CLOSED.equals(invtyOrdDtlStsDb)) {
                this.addMsgId(msgMap, MSG_ID_NLZM0112E);
                return false;
            }

            // Unexpected
        } else {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Check the Input parameters. (Header Close)
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     * @param onBatchType Kind of Online or Batch.
     * @return Result (true:Normal, false:Error)
     */
    private boolean checkInputHeadClose(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, INVTY_ORDTMsg invtyOrdTMsg, INVTY_ORD_DTLTMsg invtyOrdDtlTMsg) {

        // Input parameter PMsg
        NLZC003001PMsg inpPrmPMsg = (NLZC003001PMsg) msgMap.getPmsg();

        // Data Type
        String dtTpCd = inpPrmPMsg.xxDtTpCd.getValue();
        if (!DT_TP_HDR.equals(dtTpCd)) {
            this.addMsgId(msgMap, MSG_ID_NLZM0047E);
            return false;
        }

        // Inventory Order Status(DB) is Null.
        String invtyOrdStsCdDb = invtyOrdTMsg.invtyOrdStsCd.getValue();
        if (!ZYPCommonFunc.hasValue(invtyOrdStsCdDb)) {
            this.addMsgId(msgMap, MSG_ID_NLZM0111E);
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Get the Inventory Order Number.
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @param onBatchType Kind of Online or Batch.
     * @param dtTpCd Data Type
     * @return
     */
    private String getNewInvtyOrdNum(String glblCmpyCd, ONBATCH_TYPE onBatchType, String dtTpCd) {

        // Header
        if (DT_TP_HDR.equals(dtTpCd)) {

            // numbering will return the results.
            newInvtyOrdNum = ZYPNumbering.getUniqueID(glblCmpyCd, getSeqKey(onBatchType));
        }

        return newInvtyOrdNum;
    }

    /**
     * <pre>
     * Get oracle sequence key.
     * </pre>
     * @param onBatchType Kind of Online or Batch.
     * @return oracle sequence key
     */
    private String getSeqKey(ONBATCH_TYPE onBatchType) {

        // Started from Online.
        if (ONBATCH_TYPE.ONLINE.equals(onBatchType)) {
            return "IM#_ONL";

            // Started from not Online. (Batch)
        } else {
            return "IM#_BAT";
        }
    }

    /**
     * <pre>
     * Create the record.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     * @param invtyOrdTMsg create informations
     * @return Result (true:Normal, false:Error)
     */
    private boolean create(S21ApiMessageMap msgMap, EZDTMsg invtyOrdTMsg) {

        // Execute create.
        S21ApiTBLAccessor.create(invtyOrdTMsg);
        if (!RTNCD_NORMAL_END.equals(invtyOrdTMsg.getReturnCode())) {
            this.addMsgId(msgMap, MSG_ID_NLZM0044E);
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Update the record.
     * If an error occurs, add a message to the Message Map.
     * </pre>
     * @param msgMap Message Map
     * @param invtyOrdTMsg update informations
     * @return Result (true:Normal, false:Error)
     */
    private boolean update(S21ApiMessageMap msgMap, EZDTMsg invtyOrdTMsg) {

        // Execute update.
        S21ApiTBLAccessor.update(invtyOrdTMsg);
        if (!RTNCD_NORMAL_END.equals(invtyOrdTMsg.getReturnCode())) {
            this.addMsgId(msgMap, MSG_ID_NLZM0044E);
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Find the record for update.
     * If an error occurs, return the null.
     * </pre>
     * @param msgMap Message Map
     * @param invtyOrdTMsg find informations
     * @return Result
     */
    private EZDTMsg findForUpdate(S21ApiMessageMap msgMap, EZDTMsg invtyOrdTMsg) {

        // Get error message id.
        String errMsgId = getNotFoundMsg(invtyOrdTMsg);

        // Find the record for update.
        invtyOrdTMsg = S21ApiTBLAccessor.findByKeyForUpdate(invtyOrdTMsg);
        if (invtyOrdTMsg == null) {
            this.addMsgId(msgMap, errMsgId);
            return null;
        }

        // If an error occurs, add a message to the Message Map.
        if (!RTNCD_NORMAL_END.equals(invtyOrdTMsg.getReturnCode())) {
            this.addMsgId(msgMap, MSG_ID_NLZM0044E);
            return null;
        }

        return invtyOrdTMsg;
    }

    /**
     * <pre>
     * Find the record.
     * If an error occurs, return the null.
     * </pre>
     * @param msgMap Message Map
     * @param invtyOrdTMsg find informations
     * @return Result
     */
    private EZDTMsg findByKey(S21ApiMessageMap msgMap, EZDTMsg invtyOrdTMsg) {

        // Get error message id.
        String errMsg = getNotFoundMsg(invtyOrdTMsg);

        // Find the record.
        invtyOrdTMsg = S21ApiTBLAccessor.findByKey(invtyOrdTMsg);
        if (invtyOrdTMsg == null) {
            this.addMsgId(msgMap, errMsg);
            return null;
        }

        // If an error occurs, add a message to the Message Map.
        if (!RTNCD_NORMAL_END.equals(invtyOrdTMsg.getReturnCode())) {
            this.addMsgId(msgMap, MSG_ID_NLZM0044E);
            return null;
        }

        return invtyOrdTMsg;
    }

    /**
     * <pre>
     * Get message id for record not found.
     * </pre>
     * @param invtyOrdTMsg TMsg
     * @return message id
     */
    private String getNotFoundMsg(EZDTMsg invtyOrdTMsg) {
        if (invtyOrdTMsg instanceof INVTY_ORDTMsg) {
            return MSG_ID_NLZM0052E;
        } else if (invtyOrdTMsg instanceof INVTY_ORD_DTLTMsg) {
            return MSG_ID_NLZM0053E;
        } else {
            return MSG_ID_NLZM0044E;
        }
    }

    /**
     * <pre>
     * Check the Time stamp.
     * </Pre>
     * @param msgMap Message Map
     * @param invtyOrdTMsg TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean checkTimestamp(S21ApiMessageMap msgMap, EZDTMsg invtyOrdTMsg) {

        String dbUpTs = null;
        String dbUpTz = null;
        String errMsg = null;

        // Input parameter PMsg
        NLZC003001PMsg inpPrmPMsg = (NLZC003001PMsg) msgMap.getPmsg();
        String rqstTs = inpPrmPMsg.xxRqstTs.getValue();
        String rqstTz = inpPrmPMsg.xxRqstTz.getValue();

        // Time stamp not set.
        if (!ZYPCommonFunc.hasValue(rqstTs) || !ZYPCommonFunc.hasValue(rqstTz)) {
            return true;
        }

        // Check the Time stamp.
        if (invtyOrdTMsg instanceof INVTY_ORDTMsg) {
            dbUpTs = ((INVTY_ORDTMsg) invtyOrdTMsg).ezUpTime.getValue();
            dbUpTz = ((INVTY_ORDTMsg) invtyOrdTMsg).ezUpTimeZone.getValue();
            errMsg = MSG_ID_NLZM0050E;
        } else if (invtyOrdTMsg instanceof INVTY_ORD_DTLTMsg) {
            dbUpTs = ((INVTY_ORD_DTLTMsg) invtyOrdTMsg).ezUpTime.getValue();
            dbUpTz = ((INVTY_ORD_DTLTMsg) invtyOrdTMsg).ezUpTimeZone.getValue();
            errMsg = MSG_ID_NLZM0110E;
        } else if (invtyOrdTMsg instanceof INVTY_ORD_SERTMsg) {
            dbUpTs = ((INVTY_ORD_SERTMsg) invtyOrdTMsg).ezUpTime.getValue();
            dbUpTz = ((INVTY_ORD_SERTMsg) invtyOrdTMsg).ezUpTimeZone.getValue();
            errMsg = MSG_ID_NLZM0110E;
        } else {
            this.addMsgId(msgMap, MSG_ID_NLZM0044E);
            return false;
        }

        // If an error occurs, add a message to the Message Map.
        if (!ZYPDateUtil.isSameTimeStamp(rqstTs, rqstTz, dbUpTs, dbUpTz)) {
            this.addMsgId(msgMap, errMsg);
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Set the message id.
     * </pre>
     * @param msgMap Message Manager
     * @param inpPrmMsg EZD PMessage
     * @param msgId String setting value for Message ID
     */
    private void addMsgId(S21ApiMessageMap msgMap, String msgId) {

        // Set the message id.
        msgMap.addXxMsgId(msgId);
        printDebugLog("setMsgId:" + msgId);
    }

    /**
     * <pre>
     * Print debug message.
     * </pre>
     * @param debugMsg debug message
     */
    private void printDebugLog(String debugMsg) {
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, debugMsg, this);
        }
    }

    /**
     * <pre>
     * Check the result for API.
     * </pre>
     * @param msgIdList Result message id list for API.
     * @param msgMap Message Map
     * @return Result (true:Normal, false:Error)
     */
    private boolean checkApiResult(EZDPMsgArray msgIdList, S21ApiMessageMap msgMap) {

        if (msgIdList.getValidCount() > 0) {
            NLZC003001PMsg inpPrmPMsg = (NLZC003001PMsg) msgMap.getPmsg();
            EZDMsg.copy(msgIdList, null, inpPrmPMsg.xxMsgIdList, null);
            inpPrmPMsg.xxMsgIdList.setValidCount(msgIdList.getValidCount());
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Execute Allocation for non CPO API.
     * </pre>
     * @param msgMap Message Map
     * @param onBatchType Kind of Online or Batch.
     * @param invtyOrdTMsg Inventory Order TMsg
     * @param invtyOrdDtlTMsg Inventory Order Detail TMsg
     * @return Result (true:Normal, false:Error)
     */
    private boolean executeAllocNonCpoApi(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, INVTY_ORDTMsg invtyOrdTMsg, INVTY_ORD_DTLTMsg invtyOrdDtlTMsg) {

        // Input parameter PMsg
        NLZC003001PMsg inpPrmPMsg = (NLZC003001PMsg) msgMap.getPmsg();

        // Set parameter for Allocation for non CPO API.
        NWZC107001PMsg allocApiRossPMsg = new NWZC107001PMsg();
        allocApiRossPMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
        allocApiRossPMsg.slsDt.setValue(ZYPDateUtil.getSalesDate());
        allocApiRossPMsg.xxRqstTpCd.setValue(NWZC107001.REQ_TP_CANCEL);
        allocApiRossPMsg.trxSrcTpCd.setValue(invtyOrdTMsg.trxSrcTpCd.getValue());
        allocApiRossPMsg.trxHdrNum.setValue(invtyOrdDtlTMsg.invtyOrdNum.getValue());
        allocApiRossPMsg.trxLineNum.setValue(invtyOrdDtlTMsg.invtyOrdLineNum.getValue());
        allocApiRossPMsg.trxLineSubNum.setValue("001");

        // Execute Allocation for non CPO API.
        NWZC107001 allocApiRoss = new NWZC107001();
        allocApiRoss.execute(allocApiRossPMsg, onBatchType);
        return checkApiResult(allocApiRossPMsg.xxMsgIdList, msgMap);
    }

    /**
     * <pre>
     * Update all Inventory Order Detail which related to Inventoy Order.
     * If an error occurs, return the null.
     * </pre>
     * @param msgMapS21ApiMessageMap
     */
    private boolean updateInvtyOrdDtls(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, INVTY_ORDTMsg invtyOrdTMsg) {

        // Input parameter PMsg
        NLZC003001PMsg inpPrmPMsg = (NLZC003001PMsg) msgMap.getPmsg();

        // Set SQL parameter
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", inpPrmPMsg.glblCmpyCd.getValue());
        queryParam.put("invtyOrdNum", inpPrmPMsg.invtyOrdNum.getValue());

        // onBatchType = ONLINE
        // No Wait
        if (ONBATCH_TYPE.ONLINE.equals(onBatchType)) {
            queryParam.put("noWaitFlg", inpPrmPMsg.invtyOrdNum.getValue());
        }

        // Execute SSM
        return (Boolean) ssmBatchClient.queryObject("getInvtyOrdDtls", queryParam, new UpdateInvtyOrdDtls(msgMap, onBatchType, invtyOrdTMsg));
    }

    /**
     * <pre>
     * Check Allocation
     * </pre>
     * @param invtyOrdTp Inventory Order Type
     * @param ordQty Order Qty
     * @return Result (true:Normal, false:Error)
     */
    private boolean checkAllocation(String invtyOrdTp, BigDecimal ordQty) {

        // is not Item change or Item change and order qty > 0
        if (!INVTY_ORD_TP.ITEM_CHANGE.equals(invtyOrdTp)) {
            return true;

        } else if (BigDecimal.ZERO.compareTo(ordQty) < 0) {
            return true;
        }

        return false;
    }

    /**
     * <pre>
     * Inventory Order Detail perform the update process of the original search results.
     * </pre>
     * @author q02665
     */
    protected class UpdateInvtyOrdDtls extends S21SsmBooleanResultSetHandlerSupport {

        /** Message Map */
        private S21ApiMessageMap msgMap;

        /** Kind of Online or Batch */
        private ONBATCH_TYPE onBatchType;

        /** Inventory Order TMsg */
        private INVTY_ORDTMsg invtyOrdTMsg;

        // -- Process mode --------------------
        /** mode：skip */
        private static final int MODE_SKIP = 1;

        /** mode：error */
        private static final int MODE_ERR = 2;

        /** mode：normal */
        private static final int MODE_NORM = 3;

        /** mode：default */
        private static final int MODE_DEF = 0;

        /**
         * <pre>
         * Constructor
         * </pre>
         * @param msgMap Message Map
         * @param onBatchType Kind of Online or Batch.
         * @param invtyOrdTMsg Inventory Order情報
         */
        public UpdateInvtyOrdDtls(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, INVTY_ORDTMsg invtyOrdTMsg) {
            this.msgMap = msgMap;
            this.onBatchType = onBatchType;
            this.invtyOrdTMsg = invtyOrdTMsg;
        }

        /**
         * <pre>
         * Inventory Order Detail perform the update process of the original search results.
         * In the case of target Process Type will be close or cancel.
         * In the other cases, will not proceed and return False.
         * </pre>
         * @param rs ResultSet
         * @return Result (true:Normal, false:Error)
         * @throws SQLException
         */
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            // Input parameter PMsg
            NLZC003001PMsg inpPrmPMsg = (NLZC003001PMsg) msgMap.getPmsg();

            // Process Type
            String procTpCd = inpPrmPMsg.xxProcTpCd.getValue();

            // Inventory Order Detail
            INVTY_ORD_DTLTMsg invtyOrdDtlTMsg = null;

            int count = 0;
            for (; rs.next(); count++) {
                invtyOrdDtlTMsg = new INVTY_ORD_DTLTMsg();
                S21ResultSetMapper.map(rs, invtyOrdDtlTMsg);

                int mode = MODE_DEF;

                // Close
                if (PROC_TP_CLO.equals(procTpCd)) {
                    mode = updateInvtyOrdDtlClose(invtyOrdDtlTMsg);

                    // Cancel
                } else if (PROC_TP_CANC.equals(procTpCd)) {
                    mode = updateInvtyOrdDtlCancel(inpPrmPMsg, invtyOrdDtlTMsg);

                    // Header Close
                } else if (PROC_TP_HDR_CLO.equals(procTpCd)) {
                    mode = checkInvtyOrdDtlHeadClose(inpPrmPMsg, invtyOrdDtlTMsg);

                    // Unexpected
                } else {
                    return false;
                }

                switch (mode) {
                    case MODE_SKIP:
                        continue;
                    case MODE_ERR:
                        return false;
                    default:
                        // none
                }
            }

            // Search result is not found.
            if (count == 0) {
                addMsgId(msgMap, MSG_ID_NLZM0053E);
                return false;
            }

            return true;
        }

        /**
         * <pre>
         * Update the Inventory Order Detail for Close.
         * </pre>
         * @param invtyOrdDtlTMsg update informations
         * @return Result<br>
         * MODE_SKIP:Skip the record.<br>
         * MODE_ERR :Unrecoverable error.<br>
         * MODE_NORM:Normal end.<br>
         */
        private int updateInvtyOrdDtlClose(INVTY_ORD_DTLTMsg invtyOrdDtlTMsg) {

            // Inventory Order Detail Status
            String invtyOrdDtlSts = invtyOrdDtlTMsg.invtyOrdDtlStsCd.getValue();

            // Inventory Order Detail Status is Closed or Cancel
            // Processing is shifted to the following record for an
            // update off the subject.
            if (INVTY_ORD_STS.CLOSED.equals(invtyOrdDtlSts) || INVTY_ORD_STS.CANCEL.equals(invtyOrdDtlSts)) {
                return MODE_SKIP;
            }

            // Set the Inventory Order Detail Status is Closed.
            invtyOrdDtlTMsg.invtyOrdDtlStsCd.setValue(INVTY_ORD_STS.CLOSED);

            // Update the Inventory Order Detail record.
            if (!update(msgMap, invtyOrdDtlTMsg)) {
                return MODE_ERR;
            }

            return MODE_NORM;
        }

        /**
         * <pre>
         * Update the Inventory Order Detail for Cancel. 
         * </pre>
         * @param invtyOrdDtlTMsg update informations
         * @return Result<br>
         * MODE_SKIP:Skip the record.<br>
         * MODE_ERR :Unrecoverable error.<br>
         * MODE_NORM:Normal end.<br>
         */
        private int updateInvtyOrdDtlCancel(NLZC003001PMsg inpPrmPMsg, INVTY_ORD_DTLTMsg invtyOrdDtlTMsg) {

            String invtyOrdDtlSts = invtyOrdDtlTMsg.invtyOrdDtlStsCd.getValue();

            // Inventory Order Detail Status is Closed or Cancel
            // Processing is shifted to the following record for an
            // update off the subject.
            if (INVTY_ORD_STS.CLOSED.equals(invtyOrdDtlSts) || INVTY_ORD_STS.CANCEL.equals(invtyOrdDtlSts)) {
                return MODE_SKIP;
            }

            // Set the Inventory Order Detail Status is Cancel.
            invtyOrdDtlTMsg.invtyOrdDtlStsCd.setValue(INVTY_ORD_STS.CANCEL);

            // Inventory Order Type
            String invtyOrdTp = invtyOrdTMsg.invtyOrdTpCd.getValue();

            // Inventory Order Type = Damaged Claim(Comp)
            // Update Completion Qty for Damaged Claim(Entry).
            if (INVTY_ORD_TP.DAMAGED_CLAIM_COMP.equals(invtyOrdTp)) {

                // Find the Inventory Order Detail for update.
                // [Find conditions]
                // - HeaderのFrom Inventory Order Number => Inventory
                // Order Number
                // - DetailのInventory Order Line Number => Inventory
                // Order Number
                INVTY_ORD_DTLTMsg invtyOrdDtlEntryTMsg = new INVTY_ORD_DTLTMsg();
                invtyOrdDtlEntryTMsg.glblCmpyCd.setValue(inpPrmPMsg.glblCmpyCd.getValue());
                invtyOrdDtlEntryTMsg.invtyOrdNum.setValue(invtyOrdTMsg.fromInvtyOrdNum.getValue());
                invtyOrdDtlEntryTMsg.invtyOrdLineNum.setValue(invtyOrdDtlTMsg.invtyOrdLineNum.getValue());

                // Find the Inventory Order Detail for update.
                invtyOrdDtlEntryTMsg = (INVTY_ORD_DTLTMsg) findForUpdate(msgMap, invtyOrdDtlEntryTMsg);
                if (invtyOrdDtlEntryTMsg == null) {
                    return MODE_ERR;
                }

                // back Completion Qty
                // Detail(Entry).Damage Claim Qty =
                // Detail(Entry).Damage Claim Qty - Detail(Comp).Order
                // Qty
                try {
                    invtyOrdDtlEntryTMsg.dmgClmQty.setValue(invtyOrdDtlEntryTMsg.dmgClmQty.getValue().subtract(invtyOrdDtlTMsg.ordQty.getValue()));
                } catch (EZDAbendException e) {
                    EZDDebugOutput.println(1, e.getMessage(), null);
                }

                // Update the Inventory Order Detail record.
                if (!update(msgMap, invtyOrdDtlEntryTMsg)) {
                    return MODE_ERR;
                }
            }

            // is not Item change or Item change and order qty > 0
            if (checkAllocation(invtyOrdTp, invtyOrdDtlTMsg.ordQty.getValue())) {

                // START 2018/04/12 S.Katsuma [SOL#078,160,ADD]
                // Execute Allocation for non CPO API.
                // QC#26823 Update. Add conditional statement.
                if (!(ADJ_TRX_TP.ADJUSTMENT.equals(invtyOrdTMsg.adjTrxTpCd.getValue())//
                        || ADJ_TRX_TP.CYCLE_COUNT_ADJUSTMENTS.equals(invtyOrdTMsg.adjTrxTpCd.getValue()) //
                        || ADJ_TRX_TP.PHYSICAL_INVENTORY_ADJUSTMENT.equals(invtyOrdTMsg.adjTrxTpCd.getValue()))) {
                    if (!executeAllocNonCpoApi(msgMap, onBatchType, invtyOrdTMsg, invtyOrdDtlTMsg)) {
                        return MODE_ERR;
                    }
                }
                // END 2018/04/12 S.Katsuma [SOL#078,160,ADD]
            }

            // Update the Inventory Order Detail record.
            if (!update(msgMap, invtyOrdDtlTMsg)) {
                return MODE_ERR;
            }

            return MODE_NORM;
        }

        /**
         * <pre>
         * Check the Inventory Order Detail for Header Close.
         * If other records to "Cancel" exist, Line Type Code to set the "Close".
         * </pre>
         * @param invtyOrdDtlTMsg update informations
         * @return Result<br>
         * MODE_SKIP:Skip the record.<br>
         * MODE_ERR :Unrecoverable error.<br>
         * MODE_NORM:Normal end.<br>
         */
        private int checkInvtyOrdDtlHeadClose(NLZC003001PMsg inpPrmPMsg, INVTY_ORD_DTLTMsg invtyOrdDtlTMsg) {

            // Inventory Order Detail Status
            String invtyOrdDtlSts = invtyOrdDtlTMsg.invtyOrdDtlStsCd.getValue();

            // Inventory Order Detail Status is not "Cancel".
            // Set the Line Type Code is "Close".
            // If an "Close" occurs, return the "Error".
            if (!INVTY_ORD_STS.CANCEL.equals(invtyOrdDtlSts)) {
                inpPrmPMsg.xxLineTpCd.setValue(LINE_TP_CLO);
                if (INVTY_ORD_STS.CLOSED.equals(invtyOrdDtlSts)) {
                    return MODE_SKIP;
                } else {
                    return MODE_ERR;
                }
            } else {
                return MODE_SKIP;
            }
        }
    }

    /**
     * Get warehouse code / sub warehouse code from ds invty loc view
     * @param glblCmpyCd String
     * @param invtyLocCd String
     * @return Map<String,Object> (RTL_WH_CD,RTL_SWH_CD)
     */
    private Map<String, Object> getWhCdFrmInvtyLocV(String glblCmpyCd, String invtyLocCd) {

        // Set SQL parameter
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("invtyLocCd", invtyLocCd);

        return (Map<String, Object>) ssmBatchClient.queryObject("getWhCdFrmInvtyLocV", queryParam);
    }
}
