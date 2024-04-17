/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC301001;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_APPLY_INTFC_WRKTMsgArray;
import business.db.AR_RCPT_RCV_HISTTMsg;
import business.db.AR_RCPT_RCV_HISTTMsgArray;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.parts.NFZC301001PMsg;
import business.parts.NFZC301002PMsg;

import com.canon.cusa.s21.common.NFX.NFXC304001.NFCProcessStatus;
import com.canon.cusa.s21.common.NFX.NFXC307001.NFCCurrencyConversion;
import com.canon.cusa.s21.common.NFX.NFXC307001.NFXC3070Bean;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TRX_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/05/2009   Canon           Y.Kondo         Create          N/A
 * 11/10/2009   Canon           Y.Kondo         Update          Updated APPLY_GRP_SUB_PK increment process
 * 11/10/2009   Canon           Y.Kondo         Update          AR_APPLY_INTFC_WRK INTFC_ID, UPLD_CSV_ID, UPLD_CSV_RQST_PK add
 * 11/11/2009   Canon           Y.Kondo         Update          DefID 753
 * 11/12/2009   Canon           Y.Kondo         Update          DefID 727
 * 11/12/2009   Canon           Y.Kondo         Update          DefID 1602
 * 11/20/2009   Canon           Y.Kondo         Update          DefID 1972
 * 01/27/2010   Canon           H.Tokunaga      Update          ACC case add. it is DED case copy. And, The COA_PROD_CD item is added.
 * 06/26/2010   Canon           I.Kondo         Update          DefID 7433 No.154
 * 11/20/2010   Canon           T.Tanaka        Update          DefID M125
 * 11/24/2010   Canon           I.Kondo         Update          DefID M125
 * 04/15/2011   Canon           T.Tanaka        Update          DefID 1986
 * 04/13/2015   Canon           K.Kimura        Update          For CSA
 * 04/21/2016   Canon           T.Tsuchida      Update          DefId 4925
 * 04/15/2021   CITS            G.Delgado       Update          QC#58689
 * 04/22/2022   CITS            D.Mamaril       Update          QC#59333
 * </pre>
 */
public class NFZC301001 extends S21ApiCommonBase {

    /** */
    public static final String NFCM0522E = "NFCM0522E";

    /** */
    public static final String NFCM0523E = "NFCM0523E";

    /** */
    public static final String NFCM0524E = "NFCM0524E";

    /** */
    public static final String NFCM0525E = "NFCM0525E";

    /** */
    public static final String NFCM0526E = "NFCM0526E";

    /** */
    public static final String NFCM0527E = "NFCM0527E";

    /** */
    public static final String NFCM0528E = "NFCM0528E";

    /** */
    public static final String NFCM0529E = "NFCM0529E";

    /** */
    public static final String NFCM0530E = "NFCM0530E";

    /** */
    public static final String NFCM0535E = "NFCM0535E";

    /** */
    public static final String NFCM0536E = "NFCM0536E";

    /** */
    public static final String NFCM0537E = "NFCM0537E";

    /** */
    public static final String NFCM0538E = "NFCM0538E";

    /** */
    public static final String NFCM0539E = "NFCM0539E";

    /** */
    public static final String NFCM0540E = "NFCM0540E";

    // Add Start 2015/04/13 for CSA
    /** */
    public static final String NFCM0542E = "NFCM0542E";
    // Add End 2015/04/13 for CSA

    /** */
    public static final String NFCM0543E = "NFCM0543E";

    /** */
    public static final String NFCM0544E = "NFCM0544E";

    /** */
    public static final String NFCM0545E = "NFCM0545E";

    /** */
    public static final String NFCM0546E = "NFCM0546E";

    /** */
    public static final String NFCM0547E = "NFCM0547E";

    /** */
    public static final String NFCM0548E = "NFCM0548E";

    /** */
    public static final String NFCM0549E = "NFCM0549E";

    /** */
    public static final String NFCM0550E = "NFCM0550E";

    /** */
    public static final String NFCM0551E = "NFCM0551E";

    /** */
    public static final String NFCM0552E = "NFCM0552E";

    /** */
    public static final String NFCM0553E = "NFCM0553E";

    /** */
    public static final String NFCM0554E = "NFCM0554E";

    /** */
    public static final String NFCM0555E = "NFCM0555E";

    /** */
    public static final String NFCM0556E = "NFCM0556E";

    /** */
    public static final String NFCM0557E = "NFCM0557E";

    /** */
    public static final String NFCM0558E = "NFCM0558E";

    /** */
    public static final String NFCM0559E = "NFCM0559E";

    /** */
    public static final String NFCM0562E = "NFCM0562E";

    /** */
    public static final String NFCM0563E = "NFCM0563E";

    /** */
    public static final String NFCM0564E = "NFCM0564E";

    /** */
    public static final String NFCM0565E = "NFCM0565E";

    /** */
    public static final String NFCM0566E = "NFCM0566E";

    /** */
    public static final String NFCM0567E = "NFCM0567E";

    /** */
    public static final String NFCM0568E = "NFCM0568E";

    /** */
    public static final String NFCM0575E = "NFCM0575E";

    /** */
    public static final String NFCM0577E = "NFCM0577E";

    /** */
    public static final String NFCM0578E = "NFCM0578E";

    /** */
    public static final String NFCM0583E = "NFCM0583E";

    /** */
    public static final String NFCM0595E = "NFCM0595E";

    /** */
    public static final String NFCM0596E = "NFCM0596E";

    /** */
    public static final String NFCM0597E = "NFCM0597E";

    /** */
    public static final String NFCM0598E = "NFCM0598E";

    /** */
    public static final String NFCM0599E = "NFCM0599E";

    /** */
    public static final String NFCM0600E = "NFCM0600E";

    /** */
    public static final String NFCM0611E = "NFCM0611E";

    /** BIZ_APP_ID : NFCL0640(OFFSET ENTRY). */
    public static final String BIZ_APP_ID_0640 = "NFCL0640";

    /** BIZ_APP_ID : NFCL0650(ADJUSTMENT ENTRY). */
    public static final String BIZ_APP_ID_0650 = "NFCL0650";

    /** AUTO_CRAT_FLG : N. */
    public static final String AUTO_CRAT_FLG_N = "N";

    /** EZ_CANCEL_FLAG : 0. */
    public static final String EZ_CANCEL_FLAG = "0";

    /** S21 Invoice Data : NFCB0010(RECEPTION). */
    public static final String S21_INVOICE_DATA_0010 = "NFCB0010";

    /** S21 Invoice Data : NFCB0020(CHECK). */
    public static final String S21_INVOICE_DATA_0020 = "NFCB0020";

    /** S21 Invoice Data : NFCB0030(CREATION). */
    public static final String S21_INVOICE_DATA_0030 = "NFCB0030";

    /** Manual Receipt Entry : NFCL0210. */
    public static final String MANUAL_RECEIPT_ENTRY = "NFCL0210";

    /** Manual Refund Entry : NFCL0220. */
    public static final String MANUAL_REFUND_ENTRY = "NFCL0220";

    // 2009.10.28 [Purge Offset] add start
    /** Purge Offset : NFCL0660. */
    public static final String PURGE_OFFSET = "NFCL0660";

    // 2009.10.28 [Purge Offset] add end

    /** Cross Purge Offset : NFCL0710. */
    public static final String CROSS_PURGE_OFFSET = "NFCL0710";

    /** Uploaded Receipt Data Check : NFCB0210. */
    public static final String UPLOADED_RECEIPT_DATA_CHECK = "NFCB0210";

    /** Uploaded Cash Application Data Check : NFCB0420. */
    public static final String UPLOADED_CASH_APPLICATION_DATA_CHECK = "NFCB0420";

    /** Auto Cash Application : NFCB0430. */
    public static final String AUTO_CASH_APPLICATION = "NFCL0430";

    // 2016.04.21 [Refund DefId 4925] add start
    /** Refund : NFCB3060. */
    public static final String REFUND = "NFCB3060";

    // 2016.04.21 [Refund DefId 4925] add end

    /** four figure */
    public static final int FOUR_FIGURE = 4;

    /** five figure */
    public static final int FIVE_FIGURE = 5;

    /** Init Number : AR_CASH_APP_SQ_NUM. */
    public static final String INIT_CASH_APP_SQ_NUM = "00000";

    /** Global Company Code : DB. */
    private static final String GLBL_CMPY_CD = "glblCmpyCd";

    /** Apply Group Key : DB. */
    private static final String APPLY_GRP_KEY = "applyGrpKey";

    /** Deal Sequence Number : DB. */
    private static final String DEAL_SQ_NUM = "dealSqNum";

    /** Group Invoice Number : DB. */
    private static final String GRP_INV_NUM = "grpInvNum";

    /** Reciept Number : DB. */
    private static final String RCPT_NUM = "rcptNum";

    /** Max Over Number : DB. */
    private static final int MAX_OVER_NUMBER = 10001;

    /** first data : DB. */
    private static final int FIRST = 1;

    /** first data index : DB. */
    private static final int FIRST_INDEX = 0;

    /** PERCENT ACCOUNT. */
    private static final BigDecimal PERCENT = BigDecimal.valueOf(0.01);

    /**
     */
    public NFZC301001() {
        super();
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param param NFZC301001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NFZC301001PMsg param, final ONBATCH_TYPE onBatchType) {

        debugLog("execute : start");

        if (param == null) {
            return;
        }

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        creationReceiptData(msgMap, onBatchType);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            msgMap.flush();
        }

        if (!NFCConst.CST_NFZC301001_RTN_CD_CPLT.equals(msgMap.getPmsg().getReturnCode())) {

            infoLog(msgMap);
        }
        debugLog("execute : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param params List<NFZC301001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NFZC301001PMsg> params, final ONBATCH_TYPE onBatchType) {

        if (params != null) {
            for (NFZC301001PMsg msg : params) {
                execute(msg, onBatchType);
            }
        }
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    public void creationReceiptData(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("creationReceiptData : start");

        checkParam(msgMap);

        // NFZC301002PMsg LIST
        List<NFZC301002PMsg> wrkPList = new ArrayList<NFZC301002PMsg>();

        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCdNFZC301001(msgMap))) {
            wrkPList = getApplyIntfcWrk(msgMap);
        }
        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCdNFZC301001(msgMap))) {
            getGroupInvoice(msgMap, wrkPList);
        }
        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCdNFZC301001(msgMap))) {

            NFCCashApplyPattern apiPattern = new NFCCashApplyPattern();
            apiPattern.execute(wrkPList, onBatchType);
        }
        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCd(msgMap, wrkPList))) {

            setSameDataControl(wrkPList);

            NFCCashApplyCheckExist apiCheckExist = new NFCCashApplyCheckExist();
            apiCheckExist.execute(wrkPList, onBatchType);
        }
        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCd(msgMap, wrkPList))) {

            setRcptHdr(wrkPList);

            NFCCashApplyDiscount apiDiscount = new NFCCashApplyDiscount();
            apiDiscount.execute(wrkPList, onBatchType);
        }
        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCd(msgMap, wrkPList))) {

            setApplyItem(wrkPList);

            checkBalance(msgMap, wrkPList, onBatchType);
        }

        setCcyInfo(wrkPList);

        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCd(msgMap, wrkPList))) {

            setApplyItem(wrkPList);

            NFCCashApplyUpdateArRcpt updArRcpt = new NFCCashApplyUpdateArRcpt();
            updArRcpt.execute(wrkPList, onBatchType);
        }
        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCd(msgMap, wrkPList))) {

            NFCCashApplyUpdateRcptArTrxBal updRcptArTrxBal = new NFCCashApplyUpdateRcptArTrxBal();
            updRcptArTrxBal.execute(wrkPList, onBatchType);
        }
        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCd(msgMap, wrkPList))) {

            NFCCashApplyUpdateArAdj updArAdj = new NFCCashApplyUpdateArAdj();
            updArAdj.execute(wrkPList, onBatchType);
        }
        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCd(msgMap, wrkPList))) {
            // START 2021/04/15 G.Delgado [QC#58689,ADD]
            setHdrTrxCustCd(wrkPList);
            // END 2021/04/15 G.Delgado [QC#58689,ADD]

            NFCCashApplyUpdateInvArTrxBal updInvArTrxBal = new NFCCashApplyUpdateInvArTrxBal();
            updInvArTrxBal.execute(wrkPList, onBatchType);
        }
        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCd(msgMap, wrkPList))) {

            NFCCashApplyUpdateArRcptUnApply updArRcptUnApply = new NFCCashApplyUpdateArRcptUnApply();
            updArRcptUnApply.execute(wrkPList, onBatchType);
        }
        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCd(msgMap, wrkPList))) {

            setCashAppPk(wrkPList);

            NFCCashApplyUpdateArCashApp updArCashApp = new NFCCashApplyUpdateArCashApp();
            updArCashApp.execute(wrkPList, onBatchType);
        }
        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCd(msgMap, wrkPList))) {

            NFCCashApplyUpdateRcptArBalInstnWrk updRcptArBalInstnWrk = new NFCCashApplyUpdateRcptArBalInstnWrk();
            updRcptArBalInstnWrk.execute(wrkPList, onBatchType);
        }
        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCd(msgMap, wrkPList))) {

            NFCCashApplyUpdateInvArBalInstnWrk updInvArBalInstnWrk = new NFCCashApplyUpdateInvArBalInstnWrk();
            updInvArBalInstnWrk.execute(wrkPList, onBatchType);
        }
        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCd(msgMap, wrkPList))) {
            setProcessStatus(msgMap, wrkPList);
        }
        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCdNFZC301001(msgMap))) {

            NFCCashApplyUpdateCreditBalance updateCreditBalance = new NFCCashApplyUpdateCreditBalance();
            updateCreditBalance.execute(wrkPList, onBatchType);
        }
        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCd(msgMap, wrkPList))) {

            NFCCashApplyUpdateArApplyIntfcWrk updArApplyIntfcWrk = new NFCCashApplyUpdateArApplyIntfcWrk();
            updArApplyIntfcWrk.execute(wrkPList, onBatchType, NFCConst.CST_NFZC301001_RTN_CD_CPLT);
        } else if (NFCConst.CST_NFZC301001_RTN_CD_ERR.equals(getRtnCd(msgMap, wrkPList))) {

            NFCCashApplyUpdateArApplyIntfcWrk updArApplyIntfcWrk = new NFCCashApplyUpdateArApplyIntfcWrk();
            updArApplyIntfcWrk.execute(wrkPList, onBatchType, NFCConst.CST_NFZC301001_RTN_CD_ERR);
        }

        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCd(msgMap, wrkPList))) {

            NFZC301001PMsg param = (NFZC301001PMsg) msgMap.getPmsg();
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_CPLT);
        }
        debugLog("creationReceiptData : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void checkParam(S21ApiMessageMap msgMap) {

        debugLog("checkParam : start");

        NFZC301001PMsg param = (NFZC301001PMsg) msgMap.getPmsg();
        if (S21StringUtil.isEmpty(param.glblCmpyCd.getValue()) || S21StringUtil.isEmpty(param.applyGrpKey.getValue()) || S21StringUtil.isEmpty(param.dealSqNum.getValue()) || S21StringUtil.isEmpty(param.batDt.getValue())) {
            msgMap.addXxMsgId(NFCM0522E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
        } else {
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_UNPROC);
        }
        debugLog("checkParam : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private List<NFZC301002PMsg> getApplyIntfcWrk(S21ApiMessageMap msgMap) {

        debugLog("getApplyIntfcWrk : start");

        // NFZC301002PMsg LIST
        List<NFZC301002PMsg> resultWrkPList = new ArrayList<NFZC301002PMsg>();

        NFZC301001PMsg param = (NFZC301001PMsg) msgMap.getPmsg();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        map.put(APPLY_GRP_KEY, param.applyGrpKey.getValue());
        map.put(DEAL_SQ_NUM, param.dealSqNum.getValue());

        AR_APPLY_INTFC_WRKTMsgArray wrkTArray = new AR_APPLY_INTFC_WRKTMsgArray();
        wrkTArray.setMsgList(new AR_APPLY_INTFC_WRKTMsg[MAX_OVER_NUMBER]);

        NFZC301001Query.getInstance().getApplyIntfcWrk(map, wrkTArray);

        if (wrkTArray.getValidCount() == 0) {
            msgMap.addXxMsgId(NFCM0523E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
        } else if (wrkTArray.getValidCount() == MAX_OVER_NUMBER) {
            msgMap.addXxMsgId(NFCM0548E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
        } else {

            for (int i = 0; i < wrkTArray.getValidCount(); i++) {
                AR_APPLY_INTFC_WRKTMsg wrkT = wrkTArray.no(i);
                resultWrkPList.add(replaceApplyIntfcWrk(wrkT, param));
            }
        }
        debugLog("getApplyIntfcWrk : end");
        return resultWrkPList;
    }

    /**
     * <pre>
     * Get Group Invoice
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param wrkPList List<NFZC301002PMsg>
     */
    private void getGroupInvoice(S21ApiMessageMap msgMap, List<NFZC301002PMsg> wrkPList) {

        debugLog("getGroupInvoice : start");

        NFZC301001PMsg param = (NFZC301001PMsg) msgMap.getPmsg();
        List<NFZC301002PMsg> tempList = new ArrayList<NFZC301002PMsg>();
        // Get Max DEAL_SQ_DTL_NUM and APPLY_GRP_SUB_PK
        AR_APPLY_INTFC_WRKTMsg dealSqNumT = getDealSqNumMax(msgMap);
        if (!NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCdNFZC301001(msgMap))) {
            return;
        }
        AR_APPLY_INTFC_WRKTMsg applyGrpSubPkT = getApplyGrpSubPkMax(msgMap);
        if (!NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCdNFZC301001(msgMap))) {
            return;
        }
        int maxDealSqNum = Integer.parseInt(dealSqNumT.dealSqDtlNum.getValue());
        BigDecimal maxApplyGrpSubPk = applyGrpSubPkT.applyGrpSubPk.getValue();
        for (NFZC301002PMsg wrkP : wrkPList) {

            if (NFCConst.CST_GRP_INV_FLG_GRP_ON.equals(wrkP.grpInvFlg.getValue())) {

                Map<String, Object> map = new HashMap<String, Object>();
                map.put(GLBL_CMPY_CD, wrkP.glblCmpyCd.getValue());
                map.put(GRP_INV_NUM, wrkP.invNum.getValue());

                AR_TRX_BALTMsgArray trxTArray = new AR_TRX_BALTMsgArray();
                trxTArray.setMsgList(new AR_TRX_BALTMsg[MAX_OVER_NUMBER]);

                NFZC301001Query.getInstance().getGroupInvoice(map, trxTArray);

                if (trxTArray.getValidCount() == 0) {
                    msgMap.addXxMsgId(NFCM0524E);
                    param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ERR);
                    return;
                }
                if (wrkPList.size() + tempList.size() + trxTArray.getValidCount() >= MAX_OVER_NUMBER) {
                    msgMap.addXxMsgId(NFCM0548E);
                    param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
                    return;
                }
                for (int i = 0; i < trxTArray.getValidCount(); i++) {

                    AR_TRX_BALTMsg trxT = trxTArray.no(i);
                    // increment
                    maxDealSqNum++;
                    maxApplyGrpSubPk = maxApplyGrpSubPk.add(BigDecimal.ONE);
                    tempList.add(replaceGroupInvoice(wrkP, trxT, maxApplyGrpSubPk, maxDealSqNum));
                }
            }
        }
        wrkPList.addAll(tempList);
        debugLog("getGroupInvoice : end");
    }

    /**
     * <pre>
     * </pre>
     */
    protected void setSameDataControl(List<NFZC301002PMsg> wrkPList) {

        debugLog("setSameDataControl : start");

        for (NFZC301002PMsg pmsg : wrkPList) {

            // "1060","8010","1120"
            if (!NFCConst.CST_DB_INIT_VAL_STR.equals(pmsg.rcptNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(pmsg.xxHdrNumGetFlg.getValue()) && !NFCConst.CST_XX_PROC_CASE_TP_CD_ADD_ACC.equals(pmsg.xxProcCaseTpCd.getValue())
                    && !NFCConst.CST_XX_PROC_CASE_TP_CD_GRP_INV.equals(pmsg.xxProcCaseTpCd.getValue()) && !NFCConst.CST_XX_PROC_CASE_TP_CD_BANK_FEE.equals(pmsg.xxProcCaseTpCd.getValue())) {

                pmsg.xxHdrNum.setValue(pmsg.rcptNum.getValue());
                pmsg.xxHdrNumGetFlg.setValue(NFCConst.CST_XX_HDR_NUM_GET_FLG_ON);

                for (NFZC301002PMsg tmpPmsg : wrkPList) {

                    if (pmsg.xxHdrNum.getValue().equals(tmpPmsg.rcptNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(tmpPmsg.xxHdrNumGetFlg.getValue())) {

                        tmpPmsg.xxHdrNum.setValue(tmpPmsg.rcptNum.getValue());
                        tmpPmsg.xxHdrNumGetFlg.setValue(NFCConst.CST_XX_HDR_NUM_GET_FLG_OFF);
                    }
                }
            }
        }
        for (NFZC301002PMsg pmsg : wrkPList) {

            if (!NFCConst.CST_DB_INIT_VAL_STR.equals(pmsg.invNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(pmsg.xxInvNumGetFlg.getValue()) && !NFCConst.CST_XX_PROC_CASE_TP_CD_ADD_ACC.equals(pmsg.xxProcCaseTpCd.getValue())
                    && !NFCConst.CST_XX_PROC_CASE_TP_CD_GRP_INV.equals(pmsg.xxProcCaseTpCd.getValue())) {

                pmsg.xxIntfcInvNum.setValue(pmsg.invNum.getValue());
                pmsg.xxInvNumGetFlg.setValue(NFCConst.CST_XX_INV_NUM_GET_FLG_ON);

                for (NFZC301002PMsg tmpPmsg : wrkPList) {

                    if (pmsg.xxIntfcInvNum.getValue().equals(tmpPmsg.invNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(tmpPmsg.xxInvNumGetFlg.getValue())) {

                        tmpPmsg.xxIntfcInvNum.setValue(tmpPmsg.invNum.getValue());
                        tmpPmsg.xxInvNumGetFlg.setValue(NFCConst.CST_XX_INV_NUM_GET_FLG_OFF);
                    }
                }
            }
        }
        for (NFZC301002PMsg pmsg : wrkPList) {

            if (NFCConst.CST_XX_PROC_CASE_TP_CD_GRP_INV.equals(pmsg.xxProcCaseTpCd.getValue())) {

                for (NFZC301002PMsg tmpPmsg : wrkPList) {

                    tmpPmsg.grpInvFlg.setValue(NFCConst.CST_GRP_INV_FLG_GRP_ON);
                }
                break;
            }
        }
        debugLog("setSameDataControl : end");
    }

    /**
     * <pre>
     * </pre>
     */
    protected void setRcptHdr(List<NFZC301002PMsg> wrkPList) {

        debugLog("setRcptHdr : start");

        for (NFZC301002PMsg pmsg : wrkPList) {

            if (NFCConst.CST_XX_HDR_NUM_GET_FLG_ON.equals(pmsg.xxHdrNumGetFlg.getValue())) {

                if (NFCConst.CST_DB_INIT_VAL_STR.equals(pmsg.xxHdrTrxBillToCustCd.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(pmsg.payerCustCd.getValue())) {

                    pmsg.xxHdrTrxBillToCustCd.setValue(pmsg.payerCustCd.getValue());
                }
                if (NFCConst.CST_DB_INIT_VAL_STR.equals(pmsg.xxHdrTrxPayerCustCd.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(pmsg.payerCustCd.getValue())) {

                    pmsg.xxHdrTrxPayerCustCd.setValue(pmsg.payerCustCd.getValue());
                }
                for (NFZC301002PMsg tmpPmsg : wrkPList) {

                    if (pmsg.xxHdrNum.getValue().equals(tmpPmsg.rcptNum.getValue()) && NFCConst.CST_XX_HDR_NUM_GET_FLG_OFF.equals(tmpPmsg.xxHdrNumGetFlg.getValue())) {

                        tmpPmsg.xxHdrRcptAmt.setValue(pmsg.xxHdrRcptAmt.getValue());
                        tmpPmsg.xxHdrApplyAmt.setValue(pmsg.xxHdrApplyAmt.getValue());
                        tmpPmsg.xxHdrAdjAmt.setValue(pmsg.xxHdrAdjAmt.getValue());
                        tmpPmsg.xxHdrRfAmt.setValue(pmsg.xxHdrRfAmt.getValue());
                        tmpPmsg.xxHdrVoidAmt.setValue(pmsg.xxHdrVoidAmt.getValue());
                        tmpPmsg.xxHdrRmngBalAmt.setValue(pmsg.xxHdrRmngBalAmt.getValue());
                        tmpPmsg.xxHdrRcptDt.setValue(pmsg.xxHdrRcptDt.getValue());
                        tmpPmsg.xxHdrCratMethTpCd.setValue(pmsg.xxHdrCratMethTpCd.getValue());
                        tmpPmsg.xxHdrTrxBalPk.setValue(pmsg.xxHdrTrxBalPk.getValue());
                        tmpPmsg.xxHdrTrxRmngGrsAmt.setValue(pmsg.xxHdrTrxRmngGrsAmt.getValue());
                        tmpPmsg.xxHdrTrxArTrxTpCd.setValue(pmsg.xxHdrTrxArTrxTpCd.getValue());
                        tmpPmsg.xxHdrTrxGlDt.setValue(pmsg.xxHdrTrxGlDt.getValue());
                        tmpPmsg.xxHdrTrxBillToCustCd.setValue(pmsg.xxHdrTrxBillToCustCd.getValue());
                        tmpPmsg.xxHdrTrxSellToCustCd.setValue(pmsg.xxHdrTrxSellToCustCd.getValue());
                        tmpPmsg.xxHdrTrxPayerCustCd.setValue(pmsg.xxHdrTrxPayerCustCd.getValue());
                        tmpPmsg.exptFlg.setValue(pmsg.exptFlg.getValue());
                    }
                }
            }
        }
        debugLog("setRcptHdr : end");
    }

    /**
     * <pre>
     * </pre>
     */
    protected void setApplyItem(List<NFZC301002PMsg> wrkPList) {

        debugLog("setApplyItem : start");

        for (NFZC301002PMsg wrkT : wrkPList) {
            wrkT.xxIntfcRcptApplyAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxIntfcInvApplyAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxIntfcDedctApplyAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxIntfcRfAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxIntfcVoidAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxIntfcCashDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxIntfcInvAdjAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxIntfcAdjAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxIntfcInvAdjRsvdAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxIntfcOnAcctCashAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxIntfcCrOfsApplyAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxTotRcptApplyAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxTotInvApplyAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxTotDedctApplyAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxTotRfAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxTotVoidAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxTotCashDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxTotInvAdjAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxTotAdjAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxTotManAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxTotDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxTotExtnDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxTotInvAdjRsvdAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxInvTotRmngGrsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxInvTotCrAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxTotOnAcctCashAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxInvTotOrigGrsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxTotNetSlsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxTotNetDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxTotNetExtnDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxTotInvDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            wrkT.xxTotInvExtnDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        }

        for (NFZC301002PMsg wrkT : wrkPList) {

            if (!NFCConst.CST_XX_PROC_CASE_TP_CD_GRP_INV.equals(wrkT.xxProcCaseTpCd.getValue())) {

                if (NFCConst.CST_XX_PROC_CASE_TP_CD_PMT.equals(wrkT.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_WRK.equals(wrkT.xxProcCaseTpCd.getValue())
                        || NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_PMT.equals(wrkT.xxProcCaseTpCd.getValue())) {

                    wrkT.xxIntfcInvApplyAmt.setValue(wrkT.xxIntfcInvApplyAmt.getValue().add(wrkT.dealApplyAmt.getValue()));

                }
                if (NFCConst.CST_XX_PROC_CASE_TP_CD_NEW_RCPT.equals(wrkT.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_UPLOAD.equals(wrkT.xxProcCaseTpCd.getValue())) {

                    wrkT.xxIntfcRcptApplyAmt.setValue(wrkT.xxIntfcRcptApplyAmt.getValue().add(wrkT.dealOnAcctCashAmt.getValue()));

                } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_ADD_DED_OR_ACC.equals(wrkT.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADD_DED_OR_ACC.equals(wrkT.xxProcCaseTpCd.getValue())) {

                    wrkT.xxIntfcDedctApplyAmt.setValue(wrkT.xxIntfcDedctApplyAmt.getValue().add(wrkT.dealApplyAdjAmt.getValue()));

                } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_ADD_ADJ.equals(wrkT.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADD_ADJ.equals(wrkT.xxProcCaseTpCd.getValue())) {

                    wrkT.xxIntfcAdjAmt.setValue(wrkT.xxIntfcAdjAmt.getValue().add(wrkT.dealApplyAdjAmt.getValue()));

                } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_RF.equals(wrkT.xxProcCaseTpCd.getValue())) {

                    wrkT.xxIntfcRfAmt.setValue(wrkT.xxIntfcRfAmt.getValue().add(wrkT.dealApplyAdjAmt.getValue()));

                } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_RCPT.equals(wrkT.xxProcCaseTpCd.getValue())) {

                    wrkT.xxIntfcVoidAmt.setValue(wrkT.xxIntfcVoidAmt.getValue().add(wrkT.dealOnAcctCashAmt.getValue()));

                } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_ADD_ACC.equals(wrkT.xxProcCaseTpCd.getValue())) {

                    wrkT.xxIntfcOnAcctCashAmt.setValue(wrkT.xxIntfcOnAcctCashAmt.getValue().add(wrkT.dealOnAcctCashAmt.getValue()));

                }
                wrkT.xxIntfcCashDiscAmt.setValue(wrkT.xxIntfcCashDiscAmt.getValue().add(wrkT.dealCashDiscAmt.getValue()));

                if ((!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkT.rcptNum.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkT.invNum.getValue()) && AR_ADJ_TRX_TP.ADJUSTMENT.equals(wrkT.arAdjTrxTpCd.getValue()))
                        || NFCConst.CST_XX_PROC_CASE_TP_CD_ADJ_AUTO_APVL.equals(wrkT.xxProcCaseTpCd.getValue())) {

                    wrkT.xxIntfcInvAdjAmt.setValue(wrkT.xxIntfcInvAdjAmt.getValue().add(wrkT.dealApplyAdjAmt.getValue()));
                }
                if (NFCConst.CST_XX_PROC_CASE_TP_CD_CNT_BAL.equals(wrkT.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_CNT_BAL.equals(wrkT.xxProcCaseTpCd.getValue())) {
                    wrkT.xxIntfcCrOfsApplyAmt.setValue(wrkT.xxIntfcCrOfsApplyAmt.getValue().add(wrkT.dealApplyAmt.getValue()));
                }

                // START 2022/04/22 D.Mamaril [QC#59333,ADD]
                if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADD_ADJ.equals(wrkT.xxProcCaseTpCd.getValue()) && AR_ADJ_TRX_TP.REFUND.equals(wrkT.arAdjTrxTpCd.getValue())) {
                    wrkT.xxIntfcAdjAmt.setValue(wrkT.xxIntfcAdjAmt.getValue().subtract(wrkT.dealApplyAdjAmt.getValue()));
                    wrkT.xxIntfcRfAmt.setValue(wrkT.xxIntfcRfAmt.getValue().add(wrkT.dealApplyAdjAmt.getValue()));
                }
                // END 2022/04/22 D.Mamaril [QC#59333,ADD]
            }
        }

        for (NFZC301002PMsg pmsg : wrkPList) {

            if (NFCConst.CST_XX_HDR_NUM_GET_FLG_ON.equals(pmsg.xxHdrNumGetFlg.getValue())) {

                for (NFZC301002PMsg tmpPmsg : wrkPList) {

                    if (pmsg.xxHdrNum.getValue().equals(tmpPmsg.rcptNum.getValue())) {

                        pmsg.xxTotRcptApplyAmt.setValue(pmsg.xxTotRcptApplyAmt.getValue().add(tmpPmsg.xxIntfcRcptApplyAmt.getValue()));
                        pmsg.xxTotInvApplyAmt.setValue(pmsg.xxTotInvApplyAmt.getValue().add(tmpPmsg.xxIntfcInvApplyAmt.getValue()));
                        pmsg.xxTotDedctApplyAmt.setValue(pmsg.xxTotDedctApplyAmt.getValue().add(tmpPmsg.xxIntfcDedctApplyAmt.getValue()));
                        pmsg.xxTotRfAmt.setValue(pmsg.xxTotRfAmt.getValue().add(tmpPmsg.xxIntfcRfAmt.getValue()));
                        pmsg.xxTotVoidAmt.setValue(pmsg.xxTotVoidAmt.getValue().add(tmpPmsg.xxIntfcVoidAmt.getValue()));
                        pmsg.xxTotOnAcctCashAmt.setValue(pmsg.xxTotOnAcctCashAmt.getValue().add(tmpPmsg.xxIntfcOnAcctCashAmt.getValue()));
                        pmsg.xxTotInvAdjAmt.setValue(pmsg.xxTotInvAdjAmt.getValue().add(tmpPmsg.xxIntfcInvAdjAmt.getValue()));
                        pmsg.xxTotAdjAmt.setValue(pmsg.xxTotAdjAmt.getValue().add(tmpPmsg.xxIntfcAdjAmt.getValue()));
                        pmsg.xxTotManAmt.setValue(pmsg.xxTotManAmt.getValue().add(tmpPmsg.xxDtlManAmt.getValue()));
                        pmsg.xxTotDiscAmt.setValue(pmsg.xxTotDiscAmt.getValue().add(tmpPmsg.xxDtlDiscAmt.getValue()));
                        pmsg.xxTotExtnDiscAmt.setValue(pmsg.xxTotExtnDiscAmt.getValue().add(tmpPmsg.xxDtlExtnDiscAmt.getValue()));
                        pmsg.xxInvTotRmngGrsAmt.setValue(pmsg.xxInvTotRmngGrsAmt.getValue().add(tmpPmsg.xxInvTrxRmngGrsAmt.getValue()));
                        pmsg.xxInvTotOrigGrsAmt.setValue(pmsg.xxInvTotOrigGrsAmt.getValue().add(tmpPmsg.xxInvTrxOrigGrsAmt.getValue()));
                        pmsg.xxTotNetSlsAmt.setValue(pmsg.xxTotNetSlsAmt.getValue().add(tmpPmsg.xxInvTrxNetSlsAmt.getValue()));
                        pmsg.xxTotNetDiscAmt.setValue(pmsg.xxTotNetDiscAmt.getValue().add(tmpPmsg.xxNetDiscAmt.getValue()));
                        pmsg.xxTotNetExtnDiscAmt.setValue(pmsg.xxTotNetExtnDiscAmt.getValue().add(tmpPmsg.xxNetExtnDiscAmt.getValue()));
                        pmsg.xxTotInvDiscAmt.setValue(pmsg.xxTotInvDiscAmt.getValue().add(tmpPmsg.xxInvDiscAmt.getValue()));
                        pmsg.xxTotInvExtnDiscAmt.setValue(pmsg.xxTotInvExtnDiscAmt.getValue().add(tmpPmsg.xxInvExtnDiscAmt.getValue()));
                    }
                }
            }
        }
        for (NFZC301002PMsg pmsg : wrkPList) {

            if (NFCConst.CST_XX_INV_NUM_GET_FLG_ON.equals(pmsg.xxInvNumGetFlg.getValue())) {

                for (NFZC301002PMsg tmpPmsg : wrkPList) {

                    if (pmsg.invNum.getValue().equals(tmpPmsg.xxIntfcInvNum.getValue())) {

                        pmsg.xxInvTotCrAmt.setValue(pmsg.xxInvTotCrAmt.getValue().add(tmpPmsg.xxIntfcCrOfsApplyAmt.getValue()));
                    }
                }
            }
        }
        debugLog("setApplyItem : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void checkBalance(S21ApiMessageMap msgMap, List<NFZC301002PMsg> wrkPList, ONBATCH_TYPE onBatchType) {

        debugLog("checkBalance : start");

        NFCCashApplyCheckBalance chkBal = new NFCCashApplyCheckBalance();

        chkBal.checkRcptHdr(wrkPList, onBatchType);
        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCd(msgMap, wrkPList))) {

            for (NFZC301002PMsg pmsg : wrkPList) {

                if (NFCConst.CST_XX_HDR_NUM_GET_FLG_ON.equals(pmsg.xxHdrNumGetFlg.getValue())) {

                    for (NFZC301002PMsg tmpPmsg : wrkPList) {

                        if (pmsg.xxHdrNum.getValue().equals(tmpPmsg.rcptNum.getValue()) && NFCConst.CST_XX_HDR_NUM_GET_FLG_OFF.equals(tmpPmsg.xxHdrNumGetFlg.getValue())) {

                            chkBal.setRcptHdrDtl(pmsg, tmpPmsg, onBatchType);
                        }
                    }
                }
            }
        }
        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCd(msgMap, wrkPList))) {

            chkBal.checkRcptDtl(wrkPList, onBatchType);
        }
        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(getRtnCd(msgMap, wrkPList))) {

            chkBal.checkDiff(wrkPList, onBatchType);
        }
        debugLog("checkBalance : end");
    }

    /**
     * <pre>
     * </pre>
     */
    protected void setCashAppPk(List<NFZC301002PMsg> wrkPList) {

        debugLog("setCashAppPk : start");

        for (NFZC301002PMsg pmsg : wrkPList) {

            if (NFCConst.CST_XX_HDR_NUM_GET_FLG_ON.equals(pmsg.xxHdrNumGetFlg.getValue())) {

                for (NFZC301002PMsg tmpPmsg : wrkPList) {

                    if (pmsg.xxHdrNum.getValue().equals(tmpPmsg.rcptNum.getValue()) && NFCConst.CST_XX_HDR_NUM_GET_FLG_OFF.equals(tmpPmsg.xxHdrNumGetFlg.getValue())) {

                        tmpPmsg.xxHdrTrxRmngGrsAmt.setValue(pmsg.xxHdrTrxRmngGrsAmt.getValue());
                        tmpPmsg.xxHdrTrxBillToCustCd.setValue(pmsg.xxHdrTrxBillToCustCd.getValue());
                        tmpPmsg.xxHdrTrxSellToCustCd.setValue(pmsg.xxHdrTrxSellToCustCd.getValue());
                        tmpPmsg.xxHdrTrxPayerCustCd.setValue(pmsg.xxHdrTrxPayerCustCd.getValue());
                        tmpPmsg.arCashAppPk.setValue(pmsg.arCashAppPk.getValue());
                    }
                }
            }
        }
        debugLog("setCashAppPk : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void setProcessStatus(S21ApiMessageMap msgMap, List<NFZC301002PMsg> wrkPList) {

        debugLog("setProcessStatus : start");

        NFZC301001PMsg param = (NFZC301001PMsg) msgMap.getPmsg();
        NFCProcessStatus processStatus = new NFCProcessStatus();
        for (NFZC301002PMsg wrkP : wrkPList) {

            if (!NFCConst.CST_XX_PROC_TP_CD_NON.equals(wrkP.xxRcptRcvProcTpCd.getValue()) && NFCConst.CST_XX_HDR_NUM_GET_FLG_ON.equals(wrkP.xxHdrNumGetFlg.getValue())) {

                if (NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcvHdrNum.getValue())) {

                    if (NFCConst.CST_CREATE_METH_TP_CD_AUTO.equals(wrkP.xxHdrCratMethTpCd.getValue()) || NFCConst.CST_CREATE_METH_TP_CD_CSV.equals(wrkP.xxHdrCratMethTpCd.getValue())) {

                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(GLBL_CMPY_CD, wrkP.glblCmpyCd.getValue());
                        map.put(RCPT_NUM, wrkP.rcptNum.getValue());

                        AR_RCPT_RCV_HISTTMsgArray wrkTArray = new AR_RCPT_RCV_HISTTMsgArray();
                        wrkTArray.setMsgList(new AR_RCPT_RCV_HISTTMsg[FIRST]);
                        NFZC301001Query.getInstance().getRcptRcvHist(map, wrkTArray);

                        if (wrkTArray.getValidCount() > 0) {

                            wrkP.rcptInProcSqPk.setValue(wrkTArray.no(FIRST_INDEX).rcvHistSqPk.getValue());
                            wrkP.rcvHdrNum.setValue(wrkTArray.no(FIRST_INDEX).rcvHdrNum.getValue());
                        }
                        if (!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcvHdrNum.getValue())) {

                            String result = processStatus.setProcessStatus(wrkP.glblCmpyCd.getValue(), wrkP.rcptInProcSqPk.getValue(), wrkP.rcvHdrNum.getValue(), NFCConst.CST_PROC_STS_APPLY, wrkP.xxHdrCratMethTpCd.getValue(), wrkP.usrId
                                    .getValue());
                            if (!NFCConst.CST_RTN_CD_NORM.equals(result)) {
                                msgMap.addXxMsgId(NFCM0563E);
                                param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
                                break;
                            }
                        }
                    }
                } else {

                    String result = processStatus
                            .setProcessStatus(wrkP.glblCmpyCd.getValue(), wrkP.rcptInProcSqPk.getValue(), wrkP.rcvHdrNum.getValue(), NFCConst.CST_PROC_STS_APPLY, wrkP.xxHdrCratMethTpCd.getValue(), wrkP.usrId.getValue());
                    if (!NFCConst.CST_RTN_CD_NORM.equals(result)) {
                        msgMap.addXxMsgId(NFCM0563E);
                        param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
                        break;
                    }
                }
            }
        }
        debugLog("setProcessStatus : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param wrkTmsg AR_APPLY_INTFC_WRKTMsg
     * @param param NFZC301001PMsg
     * @return afzc301002 NFZC301002PMsg
     */
    private NFZC301002PMsg replaceApplyIntfcWrk(AR_APPLY_INTFC_WRKTMsg wrkTmsg, NFZC301001PMsg param) {

        debugLog("replaceApplyIntfcWrk : start");

        NFZC301002PMsg afzc301002 = new NFZC301002PMsg();
        afzc301002.glblCmpyCd.setValue(wrkTmsg.glblCmpyCd.getValue());
        afzc301002.applyGrpKey.setValue(wrkTmsg.applyGrpKey.getValue());
        afzc301002.applyGrpSubPk.setValue(initNumber(wrkTmsg.applyGrpSubPk.getValue()));
        afzc301002.bizAppId.setValue(wrkTmsg.bizAppId.getValue());
        afzc301002.procTpCd.setValue(wrkTmsg.procTpCd.getValue());
        afzc301002.dealSqNum.setValue(wrkTmsg.dealSqNum.getValue());
        afzc301002.dealSqDtlNum.setValue(wrkTmsg.dealSqDtlNum.getValue());
        afzc301002.procStsCd.setValue(wrkTmsg.procStsCd.getValue());
        afzc301002.usrId.setValue(wrkTmsg.usrId.getValue());
        afzc301002.rcptNum.setValue(wrkTmsg.rcptNum.getValue());
        afzc301002.rcptDtlNum.setValue(wrkTmsg.rcptDtlNum.getValue());
        afzc301002.rcvFuncId.setValue(wrkTmsg.rcvFuncId.getValue());
        afzc301002.rcptInProcSqPk.setValue(initNumber(wrkTmsg.rcptInProcSqPk.getValue()));
        afzc301002.rcvHdrNum.setValue(wrkTmsg.rcvHdrNum.getValue());
        afzc301002.rcvDtlNum.setValue(wrkTmsg.rcvDtlNum.getValue());
        afzc301002.rcptGlDt.setValue(wrkTmsg.rcptGlDt.getValue());
        afzc301002.payerCustCd.setValue(wrkTmsg.payerCustCd.getValue());
        afzc301002.rcptTrxBalPk.setValue(initNumber(wrkTmsg.rcptTrxBalPk.getValue()));
        afzc301002.rcptHdrLastUpdTs.setValue(wrkTmsg.rcptHdrLastUpdTs.getValue());
        afzc301002.rcptHdrTz.setValue(wrkTmsg.rcptHdrTz.getValue());
        afzc301002.rcptTrxBalLastUpdTs.setValue(wrkTmsg.rcptTrxBalLastUpdTs.getValue());
        afzc301002.rcptTrxBalTz.setValue(wrkTmsg.rcptTrxBalTz.getValue());
        afzc301002.grpInvFlg.setValue(wrkTmsg.grpInvFlg.getValue());
        afzc301002.invNum.setValue(wrkTmsg.invNum.getValue());
        afzc301002.arTrxTpCd.setValue(wrkTmsg.arTrxTpCd.getValue());
        afzc301002.invTrxBalPk.setValue(initNumber(wrkTmsg.invTrxBalPk.getValue()));
        afzc301002.invTrxBalLastUpdTs.setValue(wrkTmsg.invTrxBalLastUpdTs.getValue());
        afzc301002.invTrxBalTz.setValue(wrkTmsg.invTrxBalTz.getValue());
        afzc301002.crNum.setValue(wrkTmsg.crNum.getValue());
        afzc301002.crTrxBalPk.setValue(initNumber(wrkTmsg.crTrxBalPk.getValue()));
        afzc301002.crTrxBalLastUpdTs.setValue(wrkTmsg.crTrxBalLastUpdTs.getValue());
        afzc301002.crTrxBalTz.setValue(wrkTmsg.crTrxBalTz.getValue());
        afzc301002.dealCcyCd.setValue(wrkTmsg.dealCcyCd.getValue());
        afzc301002.dealApplyAmt.setValue(initNumber(wrkTmsg.dealApplyAmt.getValue()));
        afzc301002.cashAppGlDt.setValue(wrkTmsg.cashAppGlDt.getValue());
        afzc301002.cashDiscPct.setValue(initNumber(wrkTmsg.cashDiscPct.getValue()));
        afzc301002.dealCashDiscAmt.setValue(initNumber(wrkTmsg.dealCashDiscAmt.getValue()));
        afzc301002.dealOnAcctCashAmt.setValue(initNumber(wrkTmsg.dealOnAcctCashAmt.getValue()));
        afzc301002.arAdjNum.setValue(wrkTmsg.arAdjNum.getValue());
        afzc301002.arAdjTrxTpCd.setValue(wrkTmsg.arAdjTrxTpCd.getValue());
        afzc301002.arAdjTpCd.setValue(wrkTmsg.arAdjTpCd.getValue());
        afzc301002.dealApplyAdjAmt.setValue(initNumber(wrkTmsg.dealApplyAdjAmt.getValue()));
        afzc301002.dealApplyAdjRsvdAmt.setValue(initNumber(wrkTmsg.dealApplyAdjRsvdAmt.getValue()));
        afzc301002.adjCmntTxt.setValue(wrkTmsg.adjCmntTxt.getValue());
        afzc301002.apvlPsnCd.setValue(wrkTmsg.apvlPsnCd.getValue());
        afzc301002.tocCd.setValue(wrkTmsg.tocCd.getValue());
        afzc301002.arCustRefNum.setValue(wrkTmsg.arCustRefNum.getValue());
        afzc301002.xxGrpFlg.setValue(NFCConst.CST_XX_GRP_FLG_CRAT_INV_OFF);
        afzc301002.xxProcCaseTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxAdjProcTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxTrxInvProcTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxTrxRcptProcTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxRcptHdrProcTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxRcptUnProcTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxCashAppProcTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxInstnInvProcTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxInstnRcptProcTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxRcptRcvProcTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxCrPrflProcTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxHdrNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxHdrNumGetFlg.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxHdrRcptAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxHdrApplyAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxHdrAdjAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxHdrRfAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxHdrVoidAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxHdrRmngBalAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxHdrRcptDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxHdrCratMethTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxHdrTrxBalPk.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxHdrTrxRmngGrsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxHdrTrxArTrxTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxHdrTrxGlDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxHdrTrxBillToCustCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxHdrTrxSellToCustCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxHdrTrxPayerCustCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxArDiffAdjNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxArAdjNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxAdjStsCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxInvTotRmngGrsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxInvInvNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxInvTrxBalPk.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxInvTrxApplyGrsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxInvTrxCashDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxInvTrxCrAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxInvTrxAdjAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxInvTrxAdjRsvdAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxInvTrxRmngGrsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxInvTrxArTrxTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxCrTrxOfsRmngAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxInvTrxBillToCustCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxInvTrxSellToCustCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxInvTrxPayerCustCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxIntfcInvApplyAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxIntfcDedctApplyAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxIntfcRcptApplyAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxIntfcRfAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxIntfcVoidAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxIntfcCashDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxIntfcInvAdjAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxIntfcAdjAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxIntfcInvAdjRsvdAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxIntfcCrOfsApplyAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxIntfcUpdTs.setValue(wrkTmsg.ezUpTime.getValue());
        afzc301002.xxIntfcUpdTz.setValue(wrkTmsg.ezUpTimeZone.getValue());
        afzc301002.xxTotInvApplyAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxTotDedctApplyAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxTotRcptApplyAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxTotRfAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxTotVoidAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxTotCashDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxTotInvAdjAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxTotAdjAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxTotInvAdjRsvdAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxTotManAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxTotDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxTotExtnDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.arCashAppPk.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxCashDiscGetFlg.setValue(NFCConst.CST_FLAG_OFF);
        afzc301002.xxDtlInvNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxDtlAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxDtlAutoCratFlg.setValue(NFCConst.CST_FLAG_OFF);
        afzc301002.xxDtlManAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxDtlTrxBalPk.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxDtlTrxRmngGrsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxDtlTrxInvDueDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxDtlTrxCcyCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxDtlTotMatchFlg.setValue(NFCConst.CST_FLAG_OFF);
        afzc301002.xxDtlDiscFlg.setValue(NFCConst.CST_FLAG_OFF);
        afzc301002.xxDtlDiscPct.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxDtlDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxDtlExtnDiscFlg.setValue(NFCConst.CST_FLAG_OFF);
        afzc301002.xxDtlExtnDiscPct.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxDtlExtnDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxDtlDiffAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxDtlDiffSetFlg.setValue(NFCConst.CST_FLAG_OFF);
        afzc301002.nonOpsMiscPmtAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.miscIncPmtAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.nonOpsMiscInvAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.miscIncInvAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.batDt.setValue(param.batDt.getValue());
        afzc301002.xxIntfcInvNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxInvNumGetFlg.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxInvTotCrAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxIntfcOnAcctCashAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxTotOnAcctCashAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.arAdjLastUpdTs.setValue(wrkTmsg.arAdjLastUpdTs.getValue());
        afzc301002.arAdjTz.setValue(wrkTmsg.arAdjTz.getValue());
        afzc301002.xxInvTrxOrigGrsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxInvTotOrigGrsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.nonOpsMiscPmtAmtPct.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.miscIncPmtAmtPct.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.nonOpsMiscInvAmtPct.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.miscIncInvAmtPct.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.roundMethCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.aftDeclPntDigitNum.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxCashApplyStsCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxDiscGracePerFromDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxDiscGracePerThruDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxInvTrxNetSlsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxNetDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxNetExtnDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxInvDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxInvExtnDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxCashDiscApplyCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.xxTotNetSlsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxTotNetDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxTotNetExtnDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxTotInvDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.xxTotInvExtnDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        afzc301002.intfcId.setValue(wrkTmsg.intfcId.getValue());
        afzc301002.upldCsvId.setValue(wrkTmsg.upldCsvId.getValue());
        afzc301002.upldCsvRqstPk.setValue(wrkTmsg.upldCsvRqstPk.getValue());
        afzc301002.xxInvTrxAttAdjNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.coaProdCd.setValue(wrkTmsg.coaProdCd.getValue());
        afzc301002.setReturnCode(NFCConst.CST_PROC_STS_CD_UNPROC);

        debugLog("replaceApplyIntfcWrk : end");

        return afzc301002;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param pmsg NFZC301002PMsg
     * @param trxTmsg AR_TRX_BALTMsg
     * @return afzc301002 NFZC301002PMsg
     */
    private NFZC301002PMsg replaceGroupInvoice(NFZC301002PMsg pmsg, AR_TRX_BALTMsg trxTmsg, BigDecimal applyGrpSubPk, int maxDealSqNum) {

        debugLog("replaceGroupInvoice : start");

        NFZC301002PMsg afzc301002 = new NFZC301002PMsg();
        afzc301002.glblCmpyCd.setValue(pmsg.glblCmpyCd.getValue());
        afzc301002.applyGrpKey.setValue(pmsg.applyGrpKey.getValue());
        afzc301002.applyGrpSubPk.setValue(applyGrpSubPk);
        afzc301002.bizAppId.setValue(pmsg.bizAppId.getValue());
        afzc301002.procTpCd.setValue(pmsg.procTpCd.getValue());
        afzc301002.dealSqNum.setValue(pmsg.dealSqNum.getValue());
        afzc301002.dealSqDtlNum.setValue(NFZC301001.zeroPadding(maxDealSqNum, FOUR_FIGURE));
        afzc301002.procStsCd.setValue(NFCConst.CST_PROC_STS_CD_UNPROC);
        afzc301002.usrId.setValue(pmsg.usrId.getValue());
        afzc301002.rcptNum.setValue(pmsg.rcptNum.getValue());
        afzc301002.rcptDtlNum.setValue(pmsg.rcptDtlNum.getValue());
        afzc301002.rcvFuncId.setValue(pmsg.rcvFuncId.getValue());
        afzc301002.rcptInProcSqPk.setValue(pmsg.rcptInProcSqPk.getValue());
        afzc301002.rcvHdrNum.setValue(pmsg.rcvHdrNum.getValue());
        afzc301002.rcvDtlNum.setValue(pmsg.rcvDtlNum.getValue());
        afzc301002.rcptGlDt.setValue(pmsg.rcptGlDt.getValue());
        afzc301002.payerCustCd.setValue(pmsg.payerCustCd.getValue());
        afzc301002.rcptTrxBalPk.setValue(pmsg.rcptTrxBalPk.getValue());
        afzc301002.rcptHdrLastUpdTs.setValue(pmsg.rcptHdrLastUpdTs.getValue());
        afzc301002.rcptHdrTz.setValue(pmsg.rcptHdrTz.getValue());
        afzc301002.rcptTrxBalLastUpdTs.setValue(pmsg.rcptTrxBalLastUpdTs.getValue());
        afzc301002.rcptTrxBalTz.setValue(pmsg.rcptTrxBalTz.getValue());
        afzc301002.grpInvFlg.setValue(NFCConst.CST_GRP_INV_FLG_GRP_OFF);
        afzc301002.invNum.setValue(trxTmsg.arTrxNum.getValue());
        afzc301002.arTrxTpCd.setValue(trxTmsg.arTrxTpCd.getValue());
        afzc301002.invTrxBalPk.setValue(initNumber(trxTmsg.arTrxBalPk.getValue()));
        afzc301002.invTrxBalLastUpdTs.setValue(trxTmsg.ezUpTime.getValue());
        afzc301002.invTrxBalTz.setValue(trxTmsg.ezUpTimeZone.getValue());
        afzc301002.crNum.setValue(pmsg.crNum.getValue());
        afzc301002.crTrxBalPk.setValue(pmsg.crTrxBalPk.getValue());
        afzc301002.crTrxBalLastUpdTs.setValue(pmsg.crTrxBalLastUpdTs.getValue());
        afzc301002.crTrxBalTz.setValue(pmsg.crTrxBalTz.getValue());
        afzc301002.dealCcyCd.setValue(pmsg.dealCcyCd.getValue());
        afzc301002.dealApplyAmt.setValue(initNumber(trxTmsg.dealRmngBalGrsAmt.getValue()));
        afzc301002.cashAppGlDt.setValue(pmsg.cashAppGlDt.getValue());
        afzc301002.cashDiscPct.setValue(pmsg.cashDiscPct.getValue());
        afzc301002.dealCashDiscAmt.setValue(pmsg.dealCashDiscAmt.getValue());
        afzc301002.dealOnAcctCashAmt.setValue(pmsg.dealOnAcctCashAmt.getValue());
        afzc301002.arAdjNum.setValue(pmsg.arAdjNum.getValue());
        afzc301002.arAdjTrxTpCd.setValue(pmsg.arAdjTrxTpCd.getValue());
        afzc301002.arAdjTpCd.setValue(pmsg.arAdjTpCd.getValue());
        afzc301002.dealApplyAdjAmt.setValue(pmsg.dealApplyAdjAmt.getValue());
        afzc301002.dealApplyAdjRsvdAmt.setValue(pmsg.dealApplyAdjRsvdAmt.getValue());
        afzc301002.adjCmntTxt.setValue(pmsg.adjCmntTxt.getValue());
        afzc301002.apvlPsnCd.setValue(pmsg.apvlPsnCd.getValue());
        afzc301002.tocCd.setValue(pmsg.tocCd.getValue());
        afzc301002.arCustRefNum.setValue(pmsg.arCustRefNum.getValue());
        afzc301002.xxGrpFlg.setValue(NFCConst.CST_XX_GRP_FLG_CRAT_INV_ON);
        afzc301002.xxProcCaseTpCd.setValue(pmsg.xxProcCaseTpCd.getValue());
        afzc301002.xxAdjProcTpCd.setValue(pmsg.xxAdjProcTpCd.getValue());
        afzc301002.xxTrxInvProcTpCd.setValue(pmsg.xxTrxInvProcTpCd.getValue());
        afzc301002.xxTrxRcptProcTpCd.setValue(pmsg.xxTrxRcptProcTpCd.getValue());
        afzc301002.xxRcptHdrProcTpCd.setValue(pmsg.xxRcptHdrProcTpCd.getValue());
        afzc301002.xxRcptUnProcTpCd.setValue(pmsg.xxRcptUnProcTpCd.getValue());
        afzc301002.xxCashAppProcTpCd.setValue(pmsg.xxCashAppProcTpCd.getValue());
        afzc301002.xxInstnInvProcTpCd.setValue(pmsg.xxInstnInvProcTpCd.getValue());
        afzc301002.xxInstnRcptProcTpCd.setValue(pmsg.xxInstnRcptProcTpCd.getValue());
        afzc301002.xxRcptRcvProcTpCd.setValue(pmsg.xxRcptRcvProcTpCd.getValue());
        afzc301002.xxCrPrflProcTpCd.setValue(pmsg.xxCrPrflProcTpCd.getValue());
        afzc301002.xxHdrNum.setValue(pmsg.xxHdrNum.getValue());
        afzc301002.xxHdrNumGetFlg.setValue(pmsg.xxHdrNumGetFlg.getValue());
        afzc301002.xxHdrRcptAmt.setValue(pmsg.xxHdrRcptAmt.getValue());
        afzc301002.xxHdrApplyAmt.setValue(pmsg.xxHdrApplyAmt.getValue());
        afzc301002.xxHdrAdjAmt.setValue(pmsg.xxHdrAdjAmt.getValue());
        afzc301002.xxHdrRfAmt.setValue(pmsg.xxHdrRfAmt.getValue());
        afzc301002.xxHdrVoidAmt.setValue(pmsg.xxHdrVoidAmt.getValue());
        afzc301002.xxHdrRmngBalAmt.setValue(pmsg.xxHdrRmngBalAmt.getValue());
        afzc301002.xxHdrRcptDt.setValue(pmsg.xxHdrRcptDt.getValue());
        afzc301002.xxHdrCratMethTpCd.setValue(pmsg.xxHdrCratMethTpCd.getValue());
        afzc301002.xxHdrTrxBalPk.setValue(pmsg.xxHdrTrxBalPk.getValue());
        afzc301002.xxHdrTrxRmngGrsAmt.setValue(pmsg.xxHdrTrxRmngGrsAmt.getValue());
        afzc301002.xxHdrTrxArTrxTpCd.setValue(pmsg.xxHdrTrxArTrxTpCd.getValue());
        afzc301002.xxHdrTrxGlDt.setValue(pmsg.xxHdrTrxGlDt.getValue());
        afzc301002.xxHdrTrxBillToCustCd.setValue(pmsg.xxHdrTrxBillToCustCd.getValue());
        afzc301002.xxHdrTrxSellToCustCd.setValue(pmsg.xxHdrTrxSellToCustCd.getValue());
        afzc301002.xxHdrTrxPayerCustCd.setValue(pmsg.xxHdrTrxPayerCustCd.getValue());
        afzc301002.xxArDiffAdjNum.setValue(pmsg.xxArDiffAdjNum.getValue());
        afzc301002.xxArAdjNum.setValue(pmsg.xxArAdjNum.getValue());
        afzc301002.xxAdjStsCd.setValue(pmsg.xxAdjStsCd.getValue());
        afzc301002.xxInvTotRmngGrsAmt.setValue(pmsg.xxInvTotRmngGrsAmt.getValue());
        afzc301002.xxInvInvNum.setValue(pmsg.xxInvInvNum.getValue());
        afzc301002.xxInvTrxBalPk.setValue(pmsg.xxInvTrxBalPk.getValue());
        afzc301002.xxInvTrxApplyGrsAmt.setValue(initNumber(trxTmsg.dealApplyGrsAmt.getValue()));
        afzc301002.xxInvTrxCashDiscAmt.setValue(initNumber(trxTmsg.dealApplyCashDiscAmt.getValue()));
        afzc301002.xxInvTrxCrAmt.setValue(initNumber(trxTmsg.dealApplyCrAmt.getValue()));
        afzc301002.xxInvTrxAdjAmt.setValue(initNumber(trxTmsg.dealApplyAdjAmt.getValue()));
        afzc301002.xxInvTrxAdjRsvdAmt.setValue(initNumber(trxTmsg.dealApplyAdjRsvdAmt.getValue()));
        afzc301002.xxInvTrxRmngGrsAmt.setValue(initNumber(trxTmsg.dealRmngBalGrsAmt.getValue()));
        afzc301002.xxInvTrxArTrxTpCd.setValue(trxTmsg.arTrxTpCd.getValue());
        afzc301002.xxCrTrxOfsRmngAmt.setValue(pmsg.xxCrTrxOfsRmngAmt.getValue());
        afzc301002.xxInvTrxBillToCustCd.setValue(trxTmsg.billToCustCd.getValue());
        afzc301002.xxInvTrxSellToCustCd.setValue(trxTmsg.sellToCustCd.getValue());
        afzc301002.xxInvTrxPayerCustCd.setValue(trxTmsg.payerCustCd.getValue());
        afzc301002.xxIntfcInvApplyAmt.setValue(pmsg.xxIntfcInvApplyAmt.getValue());
        afzc301002.xxIntfcDedctApplyAmt.setValue(pmsg.xxIntfcDedctApplyAmt.getValue());
        afzc301002.xxIntfcRcptApplyAmt.setValue(pmsg.xxIntfcRcptApplyAmt.getValue());
        afzc301002.xxIntfcRfAmt.setValue(pmsg.xxIntfcRfAmt.getValue());
        afzc301002.xxIntfcVoidAmt.setValue(pmsg.xxIntfcVoidAmt.getValue());
        afzc301002.xxIntfcCashDiscAmt.setValue(pmsg.xxIntfcCashDiscAmt.getValue());
        afzc301002.xxIntfcInvAdjAmt.setValue(pmsg.xxIntfcInvAdjAmt.getValue());
        afzc301002.xxIntfcAdjAmt.setValue(pmsg.xxIntfcAdjAmt.getValue());
        afzc301002.xxIntfcInvAdjRsvdAmt.setValue(pmsg.xxIntfcInvAdjRsvdAmt.getValue());
        afzc301002.xxIntfcCrOfsApplyAmt.setValue(pmsg.xxIntfcCrOfsApplyAmt.getValue());
        afzc301002.xxIntfcUpdTs.setValue(pmsg.xxIntfcUpdTs.getValue());
        afzc301002.xxIntfcUpdTz.setValue(pmsg.xxIntfcUpdTz.getValue());
        afzc301002.xxTotInvApplyAmt.setValue(pmsg.xxTotInvApplyAmt.getValue());
        afzc301002.xxTotDedctApplyAmt.setValue(pmsg.xxTotDedctApplyAmt.getValue());
        afzc301002.xxTotRcptApplyAmt.setValue(pmsg.xxTotRcptApplyAmt.getValue());
        afzc301002.xxTotRfAmt.setValue(pmsg.xxTotRfAmt.getValue());
        afzc301002.xxTotVoidAmt.setValue(pmsg.xxTotVoidAmt.getValue());
        afzc301002.xxTotCashDiscAmt.setValue(pmsg.xxTotCashDiscAmt.getValue());
        afzc301002.xxTotInvAdjAmt.setValue(pmsg.xxTotInvAdjAmt.getValue());
        afzc301002.xxTotAdjAmt.setValue(pmsg.xxTotAdjAmt.getValue());
        afzc301002.xxTotInvAdjRsvdAmt.setValue(pmsg.xxTotInvAdjRsvdAmt.getValue());
        afzc301002.xxTotManAmt.setValue(pmsg.xxTotManAmt.getValue());
        afzc301002.xxTotDiscAmt.setValue(pmsg.xxTotDiscAmt.getValue());
        afzc301002.xxTotExtnDiscAmt.setValue(pmsg.xxTotExtnDiscAmt.getValue());
        afzc301002.arCashAppPk.setValue(pmsg.arCashAppPk.getValue());
        afzc301002.xxCashDiscGetFlg.setValue(pmsg.xxCashDiscGetFlg.getValue());
        afzc301002.xxDtlInvNum.setValue(pmsg.xxDtlInvNum.getValue());
        afzc301002.xxDtlAmt.setValue(pmsg.xxDtlAmt.getValue());
        afzc301002.xxDtlAutoCratFlg.setValue(pmsg.xxDtlAutoCratFlg.getValue());
        afzc301002.xxDtlManAmt.setValue(pmsg.xxDtlManAmt.getValue());
        afzc301002.xxDtlTrxBalPk.setValue(pmsg.xxDtlTrxBalPk.getValue());
        afzc301002.xxDtlTrxRmngGrsAmt.setValue(pmsg.xxDtlTrxRmngGrsAmt.getValue());
        afzc301002.xxDtlTrxInvDueDt.setValue(pmsg.xxDtlTrxInvDueDt.getValue());
        afzc301002.xxDtlTrxCcyCd.setValue(pmsg.xxDtlTrxCcyCd.getValue());
        afzc301002.xxDtlTotMatchFlg.setValue(pmsg.xxDtlTotMatchFlg.getValue());
        afzc301002.xxDtlDiscFlg.setValue(pmsg.xxDtlDiscFlg.getValue());
        afzc301002.xxDtlDiscPct.setValue(pmsg.xxDtlDiscPct.getValue());
        afzc301002.xxDtlDiscAmt.setValue(pmsg.xxDtlDiscAmt.getValue());
        afzc301002.xxDtlExtnDiscFlg.setValue(pmsg.xxDtlExtnDiscFlg.getValue());
        afzc301002.xxDtlExtnDiscPct.setValue(pmsg.xxDtlExtnDiscPct.getValue());
        afzc301002.xxDtlExtnDiscAmt.setValue(pmsg.xxDtlExtnDiscAmt.getValue());
        afzc301002.xxDtlDiffAmt.setValue(pmsg.xxDtlDiffAmt.getValue());
        afzc301002.xxDtlDiffSetFlg.setValue(pmsg.xxDtlDiffSetFlg.getValue());
        afzc301002.nonOpsMiscPmtAmt.setValue(pmsg.nonOpsMiscPmtAmt.getValue());
        afzc301002.miscIncPmtAmt.setValue(pmsg.miscIncPmtAmt.getValue());
        afzc301002.nonOpsMiscInvAmt.setValue(pmsg.nonOpsMiscInvAmt.getValue());
        afzc301002.miscIncInvAmt.setValue(pmsg.miscIncInvAmt.getValue());
        afzc301002.batDt.setValue(pmsg.batDt.getValue());
        afzc301002.xxIntfcInvNum.setValue(pmsg.xxIntfcInvNum.getValue());
        afzc301002.xxInvNumGetFlg.setValue(pmsg.xxInvNumGetFlg.getValue());
        afzc301002.xxInvTotCrAmt.setValue(pmsg.xxInvTotCrAmt.getValue());
        afzc301002.xxIntfcOnAcctCashAmt.setValue(pmsg.xxIntfcOnAcctCashAmt.getValue());
        afzc301002.xxTotOnAcctCashAmt.setValue(pmsg.xxTotOnAcctCashAmt.getValue());
        afzc301002.arAdjLastUpdTs.setValue(pmsg.arAdjLastUpdTs.getValue());
        afzc301002.arAdjTz.setValue(pmsg.arAdjTz.getValue());
        afzc301002.xxInvTrxOrigGrsAmt.setValue(pmsg.xxInvTrxOrigGrsAmt.getValue());
        afzc301002.xxInvTotOrigGrsAmt.setValue(pmsg.xxInvTotOrigGrsAmt.getValue());
        afzc301002.nonOpsMiscPmtAmtPct.setValue(pmsg.nonOpsMiscPmtAmtPct.getValue());
        afzc301002.miscIncPmtAmtPct.setValue(pmsg.miscIncPmtAmtPct.getValue());
        afzc301002.nonOpsMiscInvAmtPct.setValue(pmsg.nonOpsMiscInvAmtPct.getValue());
        afzc301002.miscIncInvAmtPct.setValue(pmsg.miscIncInvAmtPct.getValue());
        afzc301002.roundMethCd.setValue(pmsg.roundMethCd.getValue());
        afzc301002.aftDeclPntDigitNum.setValue(pmsg.aftDeclPntDigitNum.getValue());
        afzc301002.xxCashApplyStsCd.setValue(pmsg.xxCashApplyStsCd.getValue());
        afzc301002.xxDiscGracePerFromDt.setValue(pmsg.xxDiscGracePerFromDt.getValue());
        afzc301002.xxDiscGracePerThruDt.setValue(pmsg.xxDiscGracePerThruDt.getValue());
        afzc301002.xxInvTrxNetSlsAmt.setValue(pmsg.xxInvTrxNetSlsAmt.getValue());
        afzc301002.xxNetDiscAmt.setValue(pmsg.xxNetDiscAmt.getValue());
        afzc301002.xxNetExtnDiscAmt.setValue(pmsg.xxNetExtnDiscAmt.getValue());
        afzc301002.xxInvDiscAmt.setValue(pmsg.xxInvDiscAmt.getValue());
        afzc301002.xxInvExtnDiscAmt.setValue(pmsg.xxInvExtnDiscAmt.getValue());
        afzc301002.xxCashDiscApplyCd.setValue(pmsg.xxCashDiscApplyCd.getValue());
        afzc301002.xxTotNetSlsAmt.setValue(pmsg.xxTotNetSlsAmt.getValue());
        afzc301002.xxTotNetDiscAmt.setValue(pmsg.xxTotNetDiscAmt.getValue());
        afzc301002.xxTotNetExtnDiscAmt.setValue(pmsg.xxTotNetExtnDiscAmt.getValue());
        afzc301002.xxTotInvDiscAmt.setValue(pmsg.xxTotInvDiscAmt.getValue());
        afzc301002.xxTotInvExtnDiscAmt.setValue(pmsg.xxTotInvExtnDiscAmt.getValue());
        afzc301002.intfcId.setValue(pmsg.intfcId.getValue());
        afzc301002.upldCsvId.setValue(pmsg.upldCsvId.getValue());
        afzc301002.upldCsvRqstPk.setValue(pmsg.upldCsvRqstPk.getValue());
        afzc301002.xxInvTrxAttAdjNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        afzc301002.coaProdCd.setValue(pmsg.coaProdCd.getValue());
        afzc301002.exptFlg.setValue(pmsg.exptFlg.getValue());
        afzc301002.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_UNPROC);

        debugLog("replaceGroupInvoice : end");

        return afzc301002;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @return returnCd ReturnCD
     */
    protected String getRtnCdNFZC301001(S21ApiMessageMap msgMap) {

        debugLog("getRtnCdNFZC301001 : start");

        NFZC301001PMsg param = (NFZC301001PMsg) msgMap.getPmsg();

        debugLog("getRtnCdNFZC301001 : end");
        return param.getReturnCode();
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param afzc301001Map S21ApiMessageMap
     * @return returnCd ReturnCD
     */
    protected String getRtnCd(S21ApiMessageMap afzc301001Map, List<NFZC301002PMsg> afzc301002Maps) {

        debugLog("getRtnCd : start");

        NFZC301001PMsg param301001 = (NFZC301001PMsg) afzc301001Map.getPmsg();

        for (NFZC301002PMsg msg : afzc301002Maps) {
            if (param301001.getReturnCode().compareTo(msg.getReturnCode()) < 0) {

                param301001.setReturnCode(msg.getReturnCode());
                List<String> list = getMessageList(msg);
                if (list.size() > 0) {
                    for (String xxMsgId : list) {
                        afzc301001Map.addXxMsgId(xxMsgId);
                    }
                }
            }
        }
        debugLog("getRtnCd : end");

        return param301001.getReturnCode();
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @return returnCd ReturnCD
     */
    protected static String getRtnCdNFZC301002(S21ApiMessageMap msgMap) {

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        return param.getReturnCode();
    }

    /**
     * <pre>
     * Original - Apply - CashDisc - CreditMemo - Adjustment - Revaluation
     * </pre>
     * 
     * @param orig BigDecimal
     * @param apply BigDecimal
     * @param cashDisc BigDecimal
     * @param cr BigDecimal
     * @param adj BigDecimal
     * @param rval BigDecimal
     * @return rmngBalAmt
     */
    protected static BigDecimal subtractFuncAmt(BigDecimal orig, BigDecimal apply, BigDecimal cashDisc, BigDecimal cr, BigDecimal adj, BigDecimal rval) {

        return subtractAmt(orig, apply, cashDisc, cr, adj, null, rval);
    }

    /**
     * <pre>
     * Original - Apply - CashDisc - CreditMemo - Adjustment
     * </pre>
     * 
     * @param orig BigDecimal
     * @param apply BigDecimal
     * @param cashDisc BigDecimal
     * @param cr BigDecimal
     * @param adj BigDecimal
     * @return rmngBalAmt
     */
    protected static BigDecimal subtractBalAmt(BigDecimal orig, BigDecimal apply, BigDecimal cashDisc, BigDecimal cr, BigDecimal adj) {

        return subtractAmt(orig, apply, cashDisc, cr, adj, null, null);
    }

    /**
     * <pre>
     * Original - Apply - CashDisc - CreditMemo - Adjustment - Void - Revaluation
     * </pre>
     * 
     * @param orig BigDecimal
     * @param apply BigDecimal
     * @param cashDisc BigDecimal
     * @param cr BigDecimal
     * @param adj BigDecimal
     * @param rcptVoid BigDecimal
     * @param rval BigDecimal
     * @return rmngBalAmt
     */
    protected static BigDecimal subtractFuncVoidAmt(BigDecimal orig, BigDecimal apply, BigDecimal cashDisc, BigDecimal cr, BigDecimal adj, BigDecimal rcptVoid, BigDecimal rval) {

        return subtractAmt(orig, apply, cashDisc, cr, adj, rcptVoid, rval);
    }

    /**
     * <pre>
     * Original - Apply - CashDisc - CreditMemo - Adjustment - Void
     * </pre>
     * 
     * @param orig BigDecimal
     * @param apply BigDecimal
     * @param cashDisc BigDecimal
     * @param cr BigDecimal
     * @param adj BigDecimal
     * @param rcptVoid BigDecimal
     * @return rmngBalAmt
     */
    protected static BigDecimal subtractBalVoidAmt(BigDecimal orig, BigDecimal apply, BigDecimal cashDisc, BigDecimal cr, BigDecimal adj, BigDecimal rcptVoid) {

        return subtractAmt(orig, apply, cashDisc, cr, adj, rcptVoid, null);
    }

    /**
     * <pre>
     * Original - Apply - CashDisc - CreditMemo - Adjustment - Revaluation
     * </pre>
     * 
     * @param orig BigDecimal
     * @param apply BigDecimal
     * @param cashDisc BigDecimal
     * @param cr BigDecimal
     * @param adj BigDecimal
     * @param rcptVoid BigDecimal
     * @param rval BigDecimal
     * @return rmngBalAmt
     */
    private static BigDecimal subtractAmt(BigDecimal orig, BigDecimal apply, BigDecimal cashDisc, BigDecimal cr, BigDecimal adj, BigDecimal rcptVoid, BigDecimal rval) {

        if (orig == null) {
            orig = BigDecimal.ZERO;
        }
        if (apply == null) {
            apply = BigDecimal.ZERO;
        }
        if (cashDisc == null) {
            cashDisc = BigDecimal.ZERO;
        }
        if (cr == null) {
            cr = BigDecimal.ZERO;
        }
        if (adj == null) {
            adj = BigDecimal.ZERO;
        }
        if (rcptVoid == null) {
            rcptVoid = BigDecimal.ZERO;
        }
        if (rval == null) {
            rval = BigDecimal.ZERO;
        }
        BigDecimal tmpApply = orig.subtract(apply);
        BigDecimal tmpCashDisc = tmpApply.subtract(cashDisc);
        BigDecimal tmpCr = tmpCashDisc.subtract(cr);
        BigDecimal rmngAdj = tmpCr.subtract(adj);
        BigDecimal rmngVoid = rmngAdj.subtract(rcptVoid);
        BigDecimal rmngBalAmt = rmngVoid.subtract(rval);

        return rmngBalAmt;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param number Number
     */
    protected static String zeroPadding(int number, int place) {

        StringBuilder st = new StringBuilder();
        for (int i = 0; i < place; i++) {

            st.append("0");
        }
        DecimalFormat format = new DecimalFormat(st.toString());
        String arCashAppSqNum = format.format(number);

        return arCashAppSqNum;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param number Number
     * @return number Number
     */
    protected static BigDecimal initNumber(BigDecimal number) {

        if (number == null) {
            number = NFCConst.CST_DB_INIT_VAL_NUM;
        }
        return number;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    protected static BigDecimal account(BigDecimal amt, BigDecimal pct, BigDecimal aftDeclPntDigitNum, String roundMethCd) {

        BigDecimal result = NFCConst.CST_DB_INIT_VAL_NUM;
        if (amt == null) {
            amt = NFCConst.CST_DB_INIT_VAL_NUM;
        }
        if (pct == null) {
            pct = NFCConst.CST_DB_INIT_VAL_NUM;
        }
        if (aftDeclPntDigitNum == null) {
            aftDeclPntDigitNum = NFCConst.CST_DB_INIT_VAL_NUM;
        }
        if (roundMethCd == null) {
            roundMethCd = NFCConst.CST_DB_INIT_VAL_STR;
        }

        boolean negativeFlg;
        if (amt.compareTo(BigDecimal.ZERO) < 0) {
            amt = amt.negate();
            negativeFlg = true;
        } else {
            negativeFlg = false;
        }
        BigDecimal discount = amt.multiply(pct).multiply(PERCENT);

        if (roundMethCd.equals(NFCConst.CST_ROUND_METH_CD_ROUND_UP)) {
            result = discount.setScale(aftDeclPntDigitNum.intValue(), RoundingMode.CEILING);
        } else if (roundMethCd.equals(NFCConst.CST_ROUND_METH_CD_ROUND_OFF)) {
            result = discount.setScale(aftDeclPntDigitNum.intValue(), RoundingMode.HALF_UP);
        } else if (roundMethCd.equals(NFCConst.CST_ROUND_METH_CD_ROUND_DOWN)) {
            result = discount.setScale(aftDeclPntDigitNum.intValue(), RoundingMode.FLOOR);
        }

        if (negativeFlg) {
            result = result.negate();
        }

        return result;
    }

    /**
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }

    /**
     * @param msgMap S21ApiMessageMap
     */
    private void infoLog(S21ApiMessageMap msgMap) {

        debugLog("infoLog : start");

        NFZC301001PMsg param = (NFZC301001PMsg) msgMap.getPmsg();
        String returnCd = param.getReturnCode();
        String msgId = getMessageList(param).get(0);
        StringBuilder st = new StringBuilder();
        st.append("[");
        st.append(param.glblCmpyCd.getValue());
        st.append("][");
        st.append(param.applyGrpKey.getValue());
        st.append("][");
        st.append(param.dealSqNum.getValue());
        st.append("][");
        st.append(param.batDt.getValue());
        st.append("]");
        String pmsgData = st.toString();
        String[] infoParam = {returnCd, msgId, pmsgData };
        S21InfoLogOutput.println(NFCM0583E, infoParam);
        debugLog("infoLog : end");
    }

    /**
     * Get DEAL_SQ_DTL_NUM Max
     * @param msgMap S21ApiMessageMap
     * @return arApplyIntfcWrkT AR_APPLY_INTFC_WRKTMsg
     */
    private AR_APPLY_INTFC_WRKTMsg getDealSqNumMax(S21ApiMessageMap msgMap) {

        debugLog("getDealSqNumMax start");

        // Create SSM Component
        NFZC301001PMsg param = (NFZC301001PMsg) msgMap.getPmsg();
        AR_APPLY_INTFC_WRKTMsg arApplyIntfcWrkT = new AR_APPLY_INTFC_WRKTMsg();
        // Set AR_APPLY_INTFC_WRK Get Parameter
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        map.put(APPLY_GRP_KEY, param.applyGrpKey.getValue());
        map.put(DEAL_SQ_NUM, param.dealSqNum.getValue());
        // Get AR_APPLY_INTFC_WRK
        NFZC301001Query.getInstance().getDealSqNumMax(map, arApplyIntfcWrkT);
        if (S21StringUtil.isEmpty(arApplyIntfcWrkT.dealSqDtlNum.getValue())) {
            msgMap.addXxMsgId(NFCM0523E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
        }

        debugLog("getDealSqNumMax end");
        return arApplyIntfcWrkT;
    }

    /**
     * Get APPLY_GRP_SUB_PK Max
     * @param msgMap S21ApiMessageMap
     * @return arApplyIntfcWrkT AR_APPLY_INTFC_WRKTMsg
     */
    private AR_APPLY_INTFC_WRKTMsg getApplyGrpSubPkMax(S21ApiMessageMap msgMap) {

        debugLog("getApplyGrpSubPkMax start");

        // Create SSM Component
        NFZC301001PMsg param = (NFZC301001PMsg) msgMap.getPmsg();
        AR_APPLY_INTFC_WRKTMsg arApplyIntfcWrkT = new AR_APPLY_INTFC_WRKTMsg();
        // Set AR_APPLY_INTFC_WRK Get Parameter
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        map.put(APPLY_GRP_KEY, param.applyGrpKey.getValue());
        // Get AR_APPLY_INTFC_WRK
        NFZC301001Query.getInstance().getApplyGrpSubPkMax(map, arApplyIntfcWrkT);
        if (arApplyIntfcWrkT.applyGrpSubPk.getValue() == null) {
            msgMap.addXxMsgId(NFCM0523E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
        }

        debugLog("getApplyGrpSubPkMax end");
        return arApplyIntfcWrkT;
    }

    /**
     * Set Currency Info.
     * @param param
     * @return
     */
    private boolean setCcyInfo(final List<NFZC301002PMsg> params) {
        debugLog("setNFXC3070Bean start");

        BigDecimal exchRate = null;
        String funcCcyCd = null;

        // Get AR_RCPT Data
        for (NFZC301002PMsg pmsg : params) {
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(pmsg.getReturnCode()) && NFCConst.CST_XX_HDR_NUM_GET_FLG_ON.equals(pmsg.xxHdrNumGetFlg.getValue())) {
                exchRate = pmsg.exchRate.getValue();
                funcCcyCd = pmsg.funcCcyCd.getValue();
                break;
            }
        }

        for (NFZC301002PMsg pmsg : params) {

            // Set AR_RCPT Exchnage Rate, Func Ccy Cd to pmsg
            if (ZYPCommonFunc.hasValue(exchRate) && ZYPCommonFunc.hasValue(funcCcyCd)) {
                pmsg.exchRate.setValue(exchRate);
                pmsg.funcCcyCd.setValue(funcCcyCd);
            } else {
                String glDt = pmsg.cashAppGlDt.getValue();
                if (ZYPCommonFunc.hasValue(pmsg.cashAppGlDt)) {
                    glDt = pmsg.cashAppGlDt.getValue();
                } else {
                    glDt = pmsg.rcptGlDt.getValue();
                }

                NFCCurrencyConversion afxc307001 = new NFCCurrencyConversion();
                NFXC3070Bean currencyData = afxc307001.convertCurrency(pmsg.glblCmpyCd.getValue(), pmsg.dealCcyCd.getValue(), BigDecimal.ZERO, glDt, null);
                if (!NFCConst.CST_RTN_CD_NORM.equals(currencyData.getRtrnCd())) {
                    return false;
                }
                pmsg.exchRate.setValue(currencyData.getExchRate());
                pmsg.funcCcyCd.setValue(currencyData.getFuncCcyCd());
            }
        }
        debugLog("setNFXC3070Bean end");

        return true;
    }

    // START 2021/04/15 G.Delgado [QC#58689,ADD]
    /**
     * Set header transaction customer codes
     * @param wrkPList List<NFZC301002PMsg>
     */
    protected void setHdrTrxCustCd(List<NFZC301002PMsg> wrkPList) {
        for (NFZC301002PMsg pmsg : wrkPList) {
            if (NFCConst.CST_XX_HDR_NUM_GET_FLG_ON.equals(pmsg.xxHdrNumGetFlg.getValue()) && ZYPCommonFunc.hasValue(pmsg.xxHdrNum)) {
                for (NFZC301002PMsg tmpPmsg : wrkPList) {
                    if (pmsg.xxHdrNum.getValue().equals(tmpPmsg.rcptNum.getValue()) && NFCConst.CST_XX_HDR_NUM_GET_FLG_OFF.equals(tmpPmsg.xxHdrNumGetFlg.getValue())) {
                        tmpPmsg.xxHdrTrxBillToCustCd.setValue(pmsg.xxHdrTrxBillToCustCd.getValue());
                        tmpPmsg.xxHdrTrxSellToCustCd.setValue(pmsg.xxHdrTrxSellToCustCd.getValue());
                        tmpPmsg.xxHdrTrxPayerCustCd.setValue(pmsg.xxHdrTrxPayerCustCd.getValue());
                    }
                }
            }
        }
    }
    // END 2021/04/15 G.Delgado [QC#58689,ADD]
}
