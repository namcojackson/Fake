/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB011001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.dbcommon.EZDTBLAccessor;
import business.db.RTL_SWHTMsg;
import business.db.RTL_WHTMsg;
import business.db.RWS_CPLTTMsg;
import business.db.RWS_CPLT_WRKTMsg;
import business.db.RWS_HDRTMsg;
import business.db.RWS_HDRTMsgArray;
import business.db.RWS_PUT_AWAY_SER_WRKTMsg;
import business.db.RWS_PUT_AWAY_WRKTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.WMS_INBD_PO_DTLTMsg;
import business.db.WMS_INBD_PO_HDRTMsg;
import business.db.WMS_INBD_SO_DTLTMsg;
import business.db.WMS_INBD_SO_HDRTMsg;
import business.db.WMS_INBD_TRXTMsg;
import business.parts.NLZC060001PMsg;
import business.parts.NLZC208001PMsg;
import business.parts.NLZC209001PMsg;
import business.parts.NLZC403001PMsg;
import business.parts.NLZC407001PMsg;
import business.parts.NSZC001001PMsg;

import com.canon.cusa.s21.api.NLG.NLGC001001.NLGC001001;
import com.canon.cusa.s21.api.NLZ.NLZC060001.NLZC060001;
import com.canon.cusa.s21.api.NLZ.NLZC060001.constant.NLZC060001Constant;
import com.canon.cusa.s21.api.NLZ.NLZC208001.NLZC208001;
import com.canon.cusa.s21.api.NLZ.NLZC209001.NLZC209001;
import com.canon.cusa.s21.api.NLZ.NLZC403001.NLZC403001;
import com.canon.cusa.s21.api.NLZ.NLZC403001.constant.NLZC403001Constant;
import com.canon.cusa.s21.api.NLZ.NLZC407001.NLZC407001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC014001.NLXC014001;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TASK;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Batch Program Class for Translate Put Away and RWS from WMS
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/19/2013   CSAI            K.Kondo         Create          MW Replace Initial
 * 11/25/2014   CSAI            K.Hayashida     Update          ITG#555240
 * 12/10/2015   CSAI            K.Lee           Update          S21WDS NA Initial
 * 09/16/2016   CITS            T.Wada          Update          QC#14505
 * 10/21/2016   CSAI            Y.Imazu         Update          QC#15512
 * 02/01/2017   CITS            Y.Fujii         Update          QC#17214
 * 03/06/2017   CITS            T.Wada          Update          QC#17975
 * 04/05/2017   CITS            R.Shimamoto     Update          QC#17981
 * 08/18/2017   CITS            Y.Iwasaki       Update          QC#20499
 * 08/22/2017   CITS            T.Wada          Update          QC#20499-1
 * 10/12/2017   CITS            T.Tokutomi      Update          QC#21731
 * 10/13/2017   CITS            T.Hakodate      UPDATE          QC#21857
 * 06/28/2018   CITS            k.Ogino         UPDATE          QC#26824
 * 11/19/2018   Fujitsu         H.Kumagai       Update          QC#28881
 * 01/17/2019   CITS            K.Ogino         Update          QC#29976
 * 08/10/2019   CITS            M.Naito         Update          QC#52572
 * 09/03/2019   CITS            R.Kurahashi     Update          QC#52866
 * 10/02/2019   CITS            M.Naito         Update          QC#53482
 * 10/07/2019   CITS            M.Naito         Update          QC#52526
 * 10/08/2019   CITS            M.Naito         Update          QC#53482
 * 11/05/2019   Fujitsu         T.Ogura         Update          QC#54080
 * 11/12/2019   CITS            M.Naito         Update          QC#54375
 * 12/24/2019   CITS            T.Wada          Update          QC#55161
 * 08/24/2020   CITS            A.Marte         Update          QC#55400
 * 02/22/2020   CITS            J.Evangelista   Update          QC#58380
 * 09/16/2021   CITS            R.Azucena       Update          QC#58435
 * 04/28/2022   CITS            J.Evangelista   Update          QC#58807
 * 08/30/2022   CITS            R.Azucena       Update          QC#60378
 * 05/17/2023   CITS            R.Azucena       Update          QC#61483
 * 08/31/2023   CITS            K.Ikeda         Update          QC#61763
 *</pre>
 */
public class NLGB011001 extends S21BatchMain implements NLGB011001Constant {

    // *********************************************************
    // Instance Variables: Basic
    // *********************************************************

    /** Termination Code of Execution Process */
    private TERM_CD termCd = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** Global Company Code */
    private String glblCmpyCd = "";

    /** Warehouse Group Cd */
    private String whGpCd = "";

    /** Sales Date */
    private String salesDate = null;

    /** PO Process Code */
    private String poProcCd = "";

    /** Counter of Records: Normal */
    private int totalCount;

    /** Counter of Records: Error */
    private int totalErrCount = 0;

    /** Target Warehouse Code */
    private String[] trgtWhCdAry = null;

    /** Flag for Check Normal End */
    private boolean termNormalFlg;

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = new ArrayList<Map<String, String>>();

    /** Duplicate Serial# WMS_INBD_TRX List */
    private ArrayList<WMS_INBD_TRXTMsg> dupSerTrxList = new ArrayList<WMS_INBD_TRXTMsg>();

    /** serNumList */
    private Map<String, List<String>> mdseSerNumList = new HashMap<String, List<String>>();

    // QC#17981 Add.
    /** NLGB0110_PRM_ORDTPCDSTKSTSCHNG */
    private String[] paramOrdTpCdStkStsChngWmsOrdTp = null;

    /** NLGB0110_PRM_ITEM_CHNG_REMAN */
    private String[] paramItemChangeRemanWmsOrdTp = null;

    /** NLGB0110_INBD_ORD_TP_CD_NOT */
    private String[] inboundOrdTpCdNotWmsOrdTp = null;

    /** NLGB0110_INBD_ORD_TP_CD_ARR */
    private String[] inboundOrdTpCdArrWmsOrdTp = null;

    /** NLGB0110_UPD_GETWMSINBDSOHDR */
    private String[] updateGetWmsInbdSoHdrWmsOrdTp = null;

    /** NLGB0110_GET_RTLWH_FROM_SOHDR */
    private String[] getRtlWhFromSoHdrWmsOrdTp = null;

    /** NLGB0110_GET_RTLWH_FROM_PCC */
    private String[] getRtlWhFromPccWmsOrdTp = null;

    /** Put Away Skip Sce Order Type List */
    private final List<String> putAwaySkipSceTpList = new ArrayList<String>();

    // START 2019/10/08 M.Naito [QC#52526,ADD]
    /** errMsgList4ExclWmsInbdCheck */
    private List<String> errMsgList4ExclWmsInbdCheck = new ArrayList<String>();
    // END 2019/10/08 M.Naito [QC#52526,ADD]

    // START 2019/11/12 M.Naito [QC#54375,ADD]
    /** notProcInbdOrdToCdList */
    private List<String> notProcInbdOrdToCdList = new ArrayList<String>();
    // END 2019/11/12 M.Naito [QC#54375,ADD]

    // START 2022/08/30 R.Azucena [QC#60378 ADD]
    /** itemChangeWhCdMap */
    private Map<String, String> itemChangeWhCdMap = new HashMap<String, String>();
    // END 2022/08/30 R.Azucena [QC#60378 ADD]

    // *********************************************************
    // Methods
    // *********************************************************
    /**
     * Startups.
     * @param args arcuments
     */
    public static void main(String[] args) {

        new NLGB011001().executeBatch(NLGB011001.class.getSimpleName());
    }

    /**
     * Initialization Routine.
     */
    @Override
    protected void initRoutine() {

        // Initialize Variables
        termCd = TERM_CD.NORMAL_END;

        // Getting Global Company Code
        S21UserProfileServiceFactory profFactory = S21UserProfileServiceFactory.getInstance();
        S21UserProfileService prof = profFactory.getService();
        glblCmpyCd = prof.getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {

            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_GLBL_CMPY_CD });
        }

        // Getting Execute Warehouse Group Code
        whGpCd = S21BatchUtil.getUserVariable1();

        if (!ZYPCommonFunc.hasValue(whGpCd)) {

            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_WH_GRP_CD });
        }

        // Getting Execute PO Process Code
        poProcCd = S21BatchUtil.getUserVariable2();

        if (!ZYPCommonFunc.hasValue(poProcCd)) {

            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_PO_PROCESS_CD });
        }

        salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(salesDate)) {

            throw new S21AbendException("NPAM1173E", new String[] {"Sales Date" });
        }

        // Put Away Skip List
        // 10/10/2017 CITS T.Hakodate Update QC#21433 START
        /*
         * putAwaySkipSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_KT);
         * putAwaySkipSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_KU);
         * putAwaySkipSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_KC);
         * putAwaySkipSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_RP); //
         * 2017/10/13 MOD T.Hakodate QC#21857 START //
         * putAwaySkipSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_RM); //
         * 2017/10/13 MOD T.Hakodate QC#21857 END
         * putAwaySkipSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_BB);
         * putAwaySkipSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_IT);
         * putAwaySkipSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_SC);
         * putAwaySkipSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_IC);
         * putAwaySkipSceTpList.add(NLXSceConst.SCE_ORD_TP_CD_SW);
         */

        String putAwaySkipSceTpConst = ZYPCodeDataUtil.getVarCharConstValue("NLGB0110_PUT_AWAY_SKIP_LIST", getGlobalCompanyCode());

        if (putAwaySkipSceTpConst != null) {

            String[] putAwaySkipSceTp = putAwaySkipSceTpConst.split(",");

            for (int i = 0; i < putAwaySkipSceTp.length; i++) {

                putAwaySkipSceTpList.add(putAwaySkipSceTp[i]);
            }

        }

        // 10/10/2017 CITS T.Hakodate Update QC#21433 END

        // START 2019/10/08 M.Naito [QC#52526,ADD]
        String valErrMsgExclWmsInbdCheck = ZYPCodeDataUtil.getVarCharConstValue("ERR_MSG_EXCL_WMS_INBD_CHECK", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(valErrMsgExclWmsInbdCheck)) {
            errMsgList4ExclWmsInbdCheck = Arrays.asList(valErrMsgExclWmsInbdCheck.split(","));
        }
        // END 2019/10/08 M.Naito [QC#52526,ADD]

        // START 2019/11/12 M.Naito [QC#54375,ADD]
        String notProcInbdOrdToCdListConst = ZYPCodeDataUtil.getVarCharConstValue("NLGB0110_INBD_ORD_TP_NOT_PROC", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(notProcInbdOrdToCdListConst)) {
            notProcInbdOrdToCdList = Arrays.asList(notProcInbdOrdToCdListConst.split(","));
        }
        // END 2019/11/12 M.Naito [QC#54375,ADD]
    }

    /**
     * Main Routine.
     */
    @Override
    protected void mainRoutine() {

        try {

            if (!WH_GP_CD_3_THIRD_PARTY_WH.equals(whGpCd)) {

                trgtWhCdAry = NLGC001001.getTrgtWhCd(glblCmpyCd, whGpCd);
            }

            if (!WH_GP_CD_3_THIRD_PARTY_WH.equals(whGpCd) && trgtWhCdAry == null) {

                outputErr(NLGM0047E, new String[] {whGpCd });

            } else {

                // QC#17981 Add.
                getWmsOrdTpCdFromVarCharConst();

                // 1. PO Receipt
                if (PO_PROC_PO_RECEIPT.equals(poProcCd)) {

                     doProcReceipt();

                // 2. PO Close
                } else if (PO_PROC_CLOSE.equals(poProcCd)) {

                    doProcClose();
                }

//                // 3. PO Cancel
//                } else if (PO_PROC_CANCEL.equals(poProcCd)) {
//
//                    doProcCancel();
//                }
            }
        // QC#29976
        } catch (Exception e) {
            rollback();
            throw new S21AbendException(e);

        } finally {

            if (!errMsgList.isEmpty()) {

                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(BUSINESS_ID, errMsgList);
                commit();
            }

            if (!dupSerTrxList.isEmpty()) {

                sendSerialDuplicateErrMail();
            }
        }
    }

    /**
     * Termination Routine.
     */
    @Override
    protected void termRoutine() {

        if (!this.termNormalFlg == false) {

            this.termCd = TERM_CD.WARNING_END;
        }

        // Setting Process Termination Code
        setTermState(this.termCd, this.totalCount, this.totalErrCount, this.totalCount + this.totalErrCount);
    }

    /**
     * PO Receipt Data Transfer to S21 Interface Table
     */
    private void doProcReceipt() {

        /*************************************************************
         * 1. Get WMS_INBD_TRX Data (RCVD)
         ************************************************************/
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("wmsTrgtWhCd", trgtWhCdAry);
        queryParam.put("lgWmsStkStsCd", LG_WMS_STK_STS_CD);
        queryParam.put("lowLimitOrdQty", LOW_LIMIT_ORD_QTY);
        queryParam.put("wmsTaskRcvd", WMS_TASK.RCVD);
        
        // 10/10/2017 CITS T.Hakodate Update QC#21433 START
        // QC#17981 Mod.
        // queryParam.put("ordTpCdStkStsChng",
        // WMS_ORD_TP.INBOUND_STOCK_STATUS_CHANGE);
        // queryParam.put("ordTpCdItemChngReman",
        // WMS_ORD_TP.INBOUND_ITEM_CHANGE_FOR_REMAN);
        // queryParam.put("ordTpCdStkStsChng",
        // paramOrdTpCdStkStsChngWmsOrdTp[0]);
        // 2017/10/13 MOD T.Hakodate QC#21857 START
        // queryParam.put("ordTpCdItemChngReman",
        // paramItemChangeRemanWmsOrdTp[0]);
        // 2017/10/13 MOD T.Hakodate QC#21857 START

        String[] inbdOrdToCdListConst = null;
        List<String> inbdOrdToCdList = new ArrayList<String>();

        inbdOrdToCdListConst = ZYPCodeDataUtil.getVarCharConstValue("NLGB0110_INBD_ORD_TP", glblCmpyCd).split(",");

        for (String wmsOrdTpCd : inbdOrdToCdListConst) {

            inbdOrdToCdList.add(wmsOrdTpCd);
        }

        queryParam.put("inbdOrdToCdList", inbdOrdToCdList);

        // 10/10/2017 CITS T.Hakodate Update QC#21433 END

        queryParam.put("procStsCd", PROC_STS.IN_COMPLETED);
        queryParam.put("intfcProcStsCd", "1");

        // START 2019/11/12 M.Naito [QC#54375,ADD]
        // Get unnecessary data
        queryParam.put("notProcInbdOrdToCdList", notProcInbdOrdToCdList);
        queryParam.put("notProcFlag", ZYPConstant.FLG_ON_Y);
        List<WMS_INBD_TRXTMsg> noProcWmsInbdTrxTMsgList = this.ssmBatchClient.queryObjectList("getPoReceipt", queryParam);

        if (noProcWmsInbdTrxTMsgList != null && !noProcWmsInbdTrxTMsgList.isEmpty()) {
            // update process status code (Processed)
            for (WMS_INBD_TRXTMsg wmsInbdTrxTMsg : noProcWmsInbdTrxTMsgList) {
                this.totalCount++;
                updTrxProcSts(wmsInbdTrxTMsg, PROC_STS.COMPLEATED, null);
                commit();
            }
        }
        queryParam.put("notProcFlag", null);
        // END 2019/11/12 M.Naito [QC#54375,ADD]
        
        // START 2023/8/31 K.Ikeda [QC#61763,ADD] 
        queryParam.put("wmsTaskPcls", WMS_TASK.PCLS);
        queryParam.put("putAwaySkipSceTpList", putAwaySkipSceTpList);
        // END 2023/8/31 K.Ikeda [QC#61763,ADD] 

        List<WMS_INBD_TRXTMsg> wmsInbdTrxTMsgList = this.ssmBatchClient.queryObjectList("getPoReceipt", queryParam);

        if (wmsInbdTrxTMsgList == null || wmsInbdTrxTMsgList.isEmpty()) {
            // START 2019/10/02 M.Naito [QC#53482,ADD]
            queryParam.put("noReceivingFlag", ZYPConstant.FLG_ON_Y);
            wmsInbdTrxTMsgList = this.ssmBatchClient.queryObjectList("getPoReceipt", queryParam);
            if (wmsInbdTrxTMsgList == null || wmsInbdTrxTMsgList.isEmpty()) {
                return;
            }
            for (WMS_INBD_TRXTMsg wmsInbdTrxTMsg : wmsInbdTrxTMsgList) {
                this.totalCount++;
                updTrxProcSts(wmsInbdTrxTMsg, PROC_STS.COMPLEATED, null);
                commit();
            }
            // END 2019/10/02 M.Naito [QC#53482,ADD]
            return;
        }

        // Start S21WDS NA Initial Upd
        ArrayList<String> piErrRwsList = new ArrayList<String>();
        ArrayList<String> nonPiRwsList = new ArrayList<String>();
        // End S21WDS NA Initial Upd

        String rwsNumPre = "";
        BigDecimal wrkTrxId = null;
        BigDecimal unitId = BigDecimal.ONE;
        BigDecimal seqNumber = BigDecimal.ONE;
        BigDecimal rwsNumSeq = BigDecimal.ZERO;

        S21TransactionTableAccessor trxAccessor = new S21TransactionTableAccessor();

        for (WMS_INBD_TRXTMsg wmsInbdTrxTMsg : wmsInbdTrxTMsgList) {

            String errorCode = "";
            String sceOrdTpCd = "";

            RTL_SWHTMsg rtlSwhTMsg = new RTL_SWHTMsg();

            /*************************************************************
             * 2. Check Inbound PO/SO Data
             ************************************************************/
            Map<String, String> rtlWhMap = getRtlWhMap(wmsInbdTrxTMsg, true);
            String rwsNum = rtlWhMap.get("rwsNum");

            if (!ZYPCommonFunc.hasValue(rwsNum)) {

                errorCode = NLGM0080E;
                outputErr(NLGM0080E, new String[] {wmsInbdTrxTMsg.inbdOrdNum.getValue(), NLXCMsgHelper.toListedString(wmsInbdTrxTMsg.wmsInbdTrxPk.getValue()) });

            } else {

                // QC#55161 Add Start
                String rwsSts = getRwsHdrSts(rwsNum);
                if(RWS_STS.RECEIVED.equals(rwsSts)) {
                    this.totalCount++;
                    unitId = unitId.add(BigDecimal.ONE);
                    updTrxProcSts(wmsInbdTrxTMsg, PROC_STS.COMPLEATED, null);
                    continue;
                }
                // QC#55161 Add End

                // Check PI Activity
                if (piErrRwsList.contains(rwsNum)) {

                    S21InfoLogOutput.println(NLBM1347E);
                    this.totalErrCount++;
                    continue;

                } else if (!nonPiRwsList.contains(rwsNum)) {

                    if (!chkPiActivity(rwsNum)) {

                        piErrRwsList.add(rwsNum);
                        S21InfoLogOutput.println(NLBM1347E);
                        this.totalErrCount++;
                        continue;

                    } else {

                        nonPiRwsList.add(rwsNum);
                    }
                }

                // Check Order Type
                if (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.inbdOrdTpCd.getValue())) {

                    outputErr(NLAM1009E, new String[] {wmsInbdTrxTMsg.intfcId.getValue(),  wmsInbdTrxTMsg.wmsInbdTrxPk.getValue().toPlainString(), unitId.toString(), seqNumber.toString(), "s80ordTpCd" });
                    errorCode = NLAM1009E;

                } else {

                    sceOrdTpCd = convOrdTpCd(wmsInbdTrxTMsg.inbdOrdTpCd.getValue(), null, rwsNum);

                    if (!ZYPCommonFunc.hasValue(sceOrdTpCd)) {

                        outputErr(NLAM1013E, new String[] {"SCE_ORD_TP", "S80_ORD_TP_CD", wmsInbdTrxTMsg.inbdOrdTpCd.getValue() });
                        errorCode = NLAM1013E;
                    }
                }

                // Check Inventory Location
                // START 2022/08/30 R.Azucena [QC#60378 MOD]
                // if (!ZYPCommonFunc.hasValue(rtlWhMap.get("RTL_SWH_CD"))) {

                // outputErrMailCut(NLAM1009E, new String[] {wmsInbdTrxTMsg.intfcId.getValue(), wmsInbdTrxTMsg.wmsInbdTrxPk.getValue().toPlainString(), unitId.toString(), seqNumber.toString(), "whCd" }, sceOrdTpCd);
                // START 2023/05/17 R.Azucena [QC#61483 MOD]
                // String rtlSwhCd = getRtlSwhCd(wmsInbdTrxTMsg.inbdOrdNum.getValue(), rtlWhMap.get("RTL_SWH_CD"));
                String rtlSwhCd = getRtlSwhCd(wmsInbdTrxTMsg.inbdOrdNum.getValue(), rtlWhMap.get("RTL_SWH_CD"), sceOrdTpCd);
                // END 2023/05/17 R.Azucena [QC#61483 MOD]

                if (!ZYPCommonFunc.hasValue(rtlSwhCd)) {
                    outputErrMailCut(NLAM1009E, new String[] {wmsInbdTrxTMsg.intfcId.getValue(), wmsInbdTrxTMsg.wmsInbdTrxPk.getValue().toPlainString(), unitId.toString(), seqNumber.toString(), "swhCd" }, sceOrdTpCd);
                // END 2022/08/30 R.Azucena [QC#60378 MOD]
                    errorCode = this.getErrCode(errorCode, NLAM1009E);

                } else {

                    ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlWhCd, rtlWhMap.get("RTL_WH_CD"));
                    // START 2022/08/30 R.Azucena [QC#60378 MOD]
                    // ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlSwhCd, rtlWhMap.get("RTL_SWH_CD"));
                    ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlSwhCd, rtlSwhCd);
                    // END 2022/08/30 R.Azucena [QC#60378 MOD]
                    rtlSwhTMsg = (RTL_SWHTMsg) S21ApiTBLAccessor.findByKey(rtlSwhTMsg);

                    if (rtlSwhTMsg == null) {

                        outputErr(NLAM1235E, new String[] {wmsInbdTrxTMsg.wmsInbdTrxPk.getValue().toPlainString(), "", unitId.toString(), seqNumber.toString(), "whCd" });
                        errorCode = this.getErrCode(errorCode, NLAM1235E);
                    }
                }
            }
            // End S21WDS NA Initial Upd

            if (ZYPCommonFunc.hasValue(errorCode)) {

                doErrProc(wmsInbdTrxTMsg, errorCode);
                continue;
            }

            /*************************************************************
             * 3. Check WMS_INBD_TRX Data
             ************************************************************/
            if (!chkWmsInbdTrxForPutAway(wmsInbdTrxTMsg, unitId, seqNumber, rtlWhMap, sceOrdTpCd)) {

                continue;
            }

            /*************************************************************
             * 4. Create Put Away Work Table
             ************************************************************/
            RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkTMsg = new RWS_PUT_AWAY_WRKTMsg();

            // Set Transaction ID & Sq ID
            if (rwsNum.equals(rwsNumPre)) {

                rwsNumSeq = rwsNumSeq.add(BigDecimal.ONE);

            } else {

                wrkTrxId = trxAccessor.getNextTransactionId();
                rwsNumSeq = BigDecimal.ONE;
            }

            rwsNumPre = rwsNum;

            // Create RWS Put Away Work
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.glblCmpyCd, wmsInbdTrxTMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.wrkTrxId, ZYPCommonFunc.leftPad(String.valueOf(wrkTrxId), TRAN_ID_LENGTH, LF_PAD_CHAR));
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.sqId, ZYPCommonFunc.leftPad(String.valueOf(rwsNumSeq), SEQ_ID_LENGTH, LF_PAD_CHAR));
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.rwsNum, rwsNum);
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.rwsDtlLineNum, ZYPCommonFunc.leftPad(String.valueOf(wmsInbdTrxTMsg.inbdOrdLineNum.getValueInt()), RWS_LINE_LENGTH, LF_PAD_CHAR));
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.invtyStkStsCd, wmsInbdTrxTMsg.wmsStkStsCd);

            if (putAwaySkipSceTpList.contains(sceOrdTpCd)) {

                ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.procStsCd, PROC_STS.COMPLEATED);

            } else {

                ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.procStsCd, PROC_STS.IN_COMPLETED);
            }

            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.sceOrdTpCd, sceOrdTpCd);
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.s80OrdTpCd, wmsInbdTrxTMsg.inbdOrdTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.rwsStkDtTmTs, this.getSalesDateTm());

            if (ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.wmsOrdQty.getValue())) {

                ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.rwsStkQty, wmsInbdTrxTMsg.wmsOrdQty.getValue());

            } else {

                ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.rwsStkQty, BigDecimal.ZERO);
            }

            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.whCd, rtlSwhTMsg.invtyLocCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.mdseCd, wmsInbdTrxTMsg.wmsMdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.rwsRefNum, wmsInbdTrxTMsg.inbdOrdNum.getValue());
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.srcTpCd, SRC_TP_CD_WMS);
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.s80StkStsCd, "S".concat(wmsInbdTrxTMsg.wmsStkStsCd.getValue()));
            ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.svcMachProcStsCd, SVC_MACH_PROC_STS.IN_COMPLETED);
            S21FastTBLAccessor.insert(rwsPutAwayWrkTMsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayWrkTMsg.getReturnCode())) {

                errorCode = NLGM0045E;
                outputErr(NLGM0045E, new String[] {rwsPutAwayWrkTMsg.getTableName(), TBL_WMS_INBD_TRX //
                        , NLXCMsgHelper.toListedString(COL_WMS_INBD_TRX_PK) //
                        , NLXCMsgHelper.toListedString(wmsInbdTrxTMsg.wmsInbdTrxPk.getValue()) });

            } else if (ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.serNum)) {

                // Create RWS Put Away Serial Work
                RWS_PUT_AWAY_SER_WRKTMsg rwsPutAwaySerWrkTMsg = new RWS_PUT_AWAY_SER_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(rwsPutAwaySerWrkTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(rwsPutAwaySerWrkTMsg.wrkTrxId, rwsPutAwayWrkTMsg.wrkTrxId.getValue());
                ZYPEZDItemValueSetter.setValue(rwsPutAwaySerWrkTMsg.sqId, rwsPutAwayWrkTMsg.sqId.getValue());
                ZYPEZDItemValueSetter.setValue(rwsPutAwaySerWrkTMsg.serLineNum, ZYPCommonFunc.leftPad(String.valueOf(BigDecimal.ONE), SER_LINE_NUM_LENGTH, LF_PAD_CHAR));
                ZYPEZDItemValueSetter.setValue(rwsPutAwaySerWrkTMsg.rwsNum, rwsNum);
                ZYPEZDItemValueSetter.setValue(rwsPutAwaySerWrkTMsg.rwsDtlLineNum, rwsPutAwayWrkTMsg.rwsDtlLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(rwsPutAwaySerWrkTMsg.invtyStkStsCd, rwsPutAwayWrkTMsg.invtyStkStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(rwsPutAwaySerWrkTMsg.serNum, wmsInbdTrxTMsg.serNum.getValue());
                ZYPEZDItemValueSetter.setValue(rwsPutAwaySerWrkTMsg.mdseCd, rwsPutAwayWrkTMsg.mdseCd.getValue());
                S21FastTBLAccessor.create(rwsPutAwaySerWrkTMsg);

                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwaySerWrkTMsg.getReturnCode())) {

                    errorCode = NLGM0045E;
                    outputErr(NLGM0045E, new String[] {rwsPutAwaySerWrkTMsg.getTableName(), TBL_WMS_INBD_TRX //
                            , NLXCMsgHelper.toListedString(COL_WMS_INBD_TRX_PK) //
                            , NLXCMsgHelper.toListedString(wmsInbdTrxTMsg.wmsInbdTrxPk.getValue()) });
                }

                // mdse serial list add
                List<String> serNumList = mdseSerNumList.get(rwsPutAwaySerWrkTMsg.mdseCd.getValue());

                if (serNumList == null) {

                    serNumList = new ArrayList<String>();
                    mdseSerNumList.put(rwsPutAwaySerWrkTMsg.mdseCd.getValue(), serNumList);
                }

                serNumList.add(rwsPutAwaySerWrkTMsg.serNum.getValue());
            }

            if (ZYPCommonFunc.hasValue(errorCode)) {

                doErrProc(wmsInbdTrxTMsg, errorCode);
                continue;
            }

            /*************************************************************
             * 5. Execute Put Away Confirmation
             ************************************************************/
            if (!putAwaySkipSceTpList.contains(sceOrdTpCd)) {

                // Data Lock
                Map<String, String> ssmParam = new HashMap<String, String>();
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                ssmParam.put("rwsNum", rwsNum);

                List<Map<String, Object>> rwsHdrLockMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRwsHdrLock", ssmParam);

                if (rwsHdrLockMapList == null || rwsHdrLockMapList.isEmpty()) {

                    continue;
                }

                // Call Update Put Away Confirmation API
                NLZC208001PMsg pmsg = new NLZC208001PMsg();
                ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pmsg.slsDt, salesDate);
                ZYPEZDItemValueSetter.setValue(pmsg.wrkTrxId, rwsPutAwayWrkTMsg.wrkTrxId.getValue());
                ZYPEZDItemValueSetter.setValue(pmsg.sqId, rwsPutAwayWrkTMsg.sqId.getValue());

                NLZC208001 apiNLZC208001 = new NLZC208001();
                apiNLZC208001.execute(pmsg, S21ApiInterface.ONBATCH_TYPE.BATCH);

                if (!NLXC014001.getMesRtn(pmsg)) {

                    boolean firstErrFlg = true;

                    for (String xxMsgId : S21ApiUtil.getXxMsgIdList(pmsg)) {

                        if (firstErrFlg) {

                            errorCode = xxMsgId;
                        }

                        if (!NLZC208001.NLZM1007E.equals(xxMsgId) && !NLZC208001.NLZM1010E.equals(xxMsgId)) {

                            outputErr(xxMsgId);

                        } else if (NLZC208001.NLZM1007E.equals(xxMsgId)) {

                            outputErr(NLZM1051E, new String[]{rwsPutAwayWrkTMsg.rwsRefNum.getValue()});

                        } else {

                            outputErr(NLZM1052E, new String[]{rwsPutAwayWrkTMsg.rwsRefNum.getValue()});
                        }

                        firstErrFlg = false;
                    }

                    doErrProc(wmsInbdTrxTMsg, errorCode);
                    continue;
                }

                // Update RWS Put Away Work
                ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.procStsCd, PROC_STS.COMPLEATED);
                updRwsPutAwayWrk(rwsPutAwayWrkTMsg);

                for (String xxMsgId : S21ApiUtil.getXxMsgIdList(pmsg)) {

                    S21InfoLogOutput.println(xxMsgId);
                    this.termNormalFlg = false;
                }

                // Update Serial for Put Away Confirmation
                if (!callUpdSerForPutAwayApi(rwsPutAwayWrkTMsg, wmsInbdTrxTMsg)) {

                    continue;
                }

                // RWS Close Check
                if (chkRwsCls(rwsPutAwayWrkTMsg.rwsNum.getValue())) {

                    if (!callUpdSerFoRwsCompApi(null, rwsPutAwayWrkTMsg, wmsInbdTrxTMsg)) {

                        continue;
                    }

                    // START 2022/04/28 J.Evangelista [QC#58807,ADD]
                    if (SCE_ORD_TP.RETURN.equals(sceOrdTpCd) && !trmnIntgItem(rwsNum, wmsInbdTrxTMsg)) {

                        continue;
                    }
                    // END 2022/04/28 J.Evangelista [QC#58807,ADD]

                    // START 2019/08/10 M.Naito [QC#52572,MOD]
                    if (WH_GP_CD_2_DBS_WH.equals(whGpCd) || WH_GP_CD_3_THIRD_PARTY_WH.equals(whGpCd)) {
                        // insert RWS_CPLT
                        RWS_CPLTTMsg rwsCpltT = new RWS_CPLTTMsg();
                        rwsCpltT.glblCmpyCd.setValue(wmsInbdTrxTMsg.glblCmpyCd.getValue());
                        rwsCpltT.rwsNum.setValue(rwsNum);
                        rwsCpltT.whCd.setValue(rtlWhMap.get("RTL_WH_CD"));
                        rwsCpltT.rwsRefNum.setValue(wmsInbdTrxTMsg.inbdOrdNum.getValue());
                        rwsCpltT.sceOrdTpCd.setValue(sceOrdTpCd);
                        rwsCpltT.rwsCloDtTmTs.setValue(getSalesDateTm());
                        S21ApiTBLAccessor.insert(rwsCpltT);

                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rwsCpltT.getReturnCode())) {
                            errorCode = NLGM0045E;
                            outputErr(NLGM0045E, new String[] {rwsCpltT.getTableName(), TBL_WMS_INBD_TRX //
                                    , NLXCMsgHelper.toListedString(COL_WMS_INBD_TRX_PK) //
                                    , NLXCMsgHelper.toListedString(wmsInbdTrxTMsg.wmsInbdTrxPk.getValue()) });
                        }
                    }
                    // END 2019/08/10 M.Naito [QC#52572,MOD]
                }
            }

            /*************************************************************
             * 6. Update WMS_INBD_TRX & Commit
             ************************************************************/
            this.totalCount++;
            updTrxProcSts(wmsInbdTrxTMsg, PROC_STS.COMPLEATED, errorCode);
            unitId = unitId.add(BigDecimal.ONE);
            commit();
        }
        // START 2019/10/02 M.Naito [QC#53482,ADD]
        queryParam.put("noReceivingFlag", "Y");
        wmsInbdTrxTMsgList = this.ssmBatchClient.queryObjectList("getPoReceipt", queryParam);
        if (wmsInbdTrxTMsgList == null || wmsInbdTrxTMsgList.isEmpty()) {
            return;
        }
        for (WMS_INBD_TRXTMsg wmsInbdTrxTMsg : wmsInbdTrxTMsgList) {
            this.totalCount++;
            updTrxProcSts(wmsInbdTrxTMsg, PROC_STS.COMPLEATED, null);
            commit();
        }
        // END 2019/10/02 M.Naito [QC#53482,ADD]
    }

    /**
     * Check WMS_INBD_TRX (RCVD)
     * 
     * @param wmsInbdTrxTMsg WMS_INBD_TRXTMsg
     * @param unitId BigDecimal
     * @param seqNumber BigDecimal
     * @param rtlWhMap Map<String, String>
     * @param sceOrdTpCd String
     * @return boolean (Check OK : true / NG : false)
     */
    private boolean chkWmsInbdTrxForPutAway(WMS_INBD_TRXTMsg wmsInbdTrxTMsg, BigDecimal unitId, BigDecimal seqNumber, Map<String, String> rtlWhMap, String sceOrdTpCd) {

        String errorCode = null;

        if (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.inbdOrdNum.getValue())) {

            outputErrMailCut(NLAM1009E, new String[] {wmsInbdTrxTMsg.intfcId.getValue(), wmsInbdTrxTMsg.wmsInbdTrxPk.getValue().toPlainString(), unitId.toString(), seqNumber.toString(), "poNum" }, sceOrdTpCd);
            errorCode = this.getErrCode(errorCode, NLAM1009E);
        }

        if (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.inbdOrdLineNum)) {

            outputErrMailCut(NLAM1009E, new String[] {wmsInbdTrxTMsg.intfcId.getValue(), wmsInbdTrxTMsg.wmsInbdTrxPk.getValue().toPlainString(), unitId.toString(), seqNumber.toString(), "rwsLineNum" }, sceOrdTpCd);
            errorCode = this.getErrCode(errorCode, NLAM1009E);
        }

        if (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.wmsMdseCd.getValue())) {

            outputErrMailCut(NLAM1009E, new String[] {wmsInbdTrxTMsg.intfcId.getValue(), wmsInbdTrxTMsg.wmsInbdTrxPk.getValue().toPlainString(), unitId.toString(), seqNumber.toString(), "mdseCd" }, sceOrdTpCd);
            errorCode = this.getErrCode(errorCode, NLAM1009E);
        }

        if (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.wmsStkStsCd.getValue())) {

            outputErrMailCut(NLAM1009E, new String[] {wmsInbdTrxTMsg.intfcId.getValue(), wmsInbdTrxTMsg.wmsInbdTrxPk.getValue().toPlainString(), unitId.toString(), seqNumber.toString(), "s80stkStsCd" }, sceOrdTpCd);
            errorCode = this.getErrCode(errorCode, NLAM1009E);
        }

        if (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.wmsOrdQty.getValue())) {

            outputErrMailCut(NLAM1009E, new String[] {wmsInbdTrxTMsg.intfcId.getValue(), wmsInbdTrxTMsg.wmsInbdTrxPk.getValue().toPlainString(), unitId.toString(), seqNumber.toString(), "stkInQty" }, sceOrdTpCd);
            errorCode = this.getErrCode(errorCode, NLAM1009E);
        }

        if (!EZDCommonFunc.isNumberCheck(wmsInbdTrxTMsg.inbdOrdLineNum.getValue().toString())) {

            wmsInbdTrxTMsg.inbdOrdLineNum.clear();
            outputErrMailCut(NLAM1010E, new String[] {wmsInbdTrxTMsg.intfcId.getValue(), wmsInbdTrxTMsg.wmsInbdTrxPk.getValue().toPlainString(), unitId.toString(), seqNumber.toString(), "rwsLineNum" }, sceOrdTpCd);
            errorCode = this.getErrCode(errorCode, NLAM1010E);
        }

        if (!EZDCommonFunc.isNumberCheck(wmsInbdTrxTMsg.wmsOrdQty.getValue().toString())) {

            wmsInbdTrxTMsg.wmsOrdQty.clear();
            outputErrMailCut(NLAM1010E, new String[] {wmsInbdTrxTMsg.intfcId.getValue(), wmsInbdTrxTMsg.wmsInbdTrxPk.getValue().toPlainString(), unitId.toString(), seqNumber.toString(), "stkInQty" }, sceOrdTpCd);
            errorCode = this.getErrCode(errorCode, NLAM1010E);
        }

        if (!EZDCommonFunc.isStrNumCheckHanniNullOK(wmsInbdTrxTMsg.inbdOrdLineNum.getValue().toString(), LINENUM_MIN, LINENUM_MAX)) {

            wmsInbdTrxTMsg.inbdOrdLineNum.clear();
            outputErrMailCut(NLAM1010E, new String[] {wmsInbdTrxTMsg.intfcId.getValue(), wmsInbdTrxTMsg.wmsInbdTrxPk.getValue().toPlainString(), unitId.toString(), seqNumber.toString(), "rwsLineNum" }, sceOrdTpCd);
            errorCode = this.getErrCode(errorCode, NLAM1010E);
        }

        if (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.wmsStkStsCd)) {

            outputErrMailCut(NLAM1013E, new String[] {"STK_STS_CONV", "S80_STK_STS_CD", wmsInbdTrxTMsg.wmsStkStsCd.getValue() }, sceOrdTpCd);
            errorCode = this.getErrCode(errorCode, NLAM1010E);
        }

        if (ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.serNum)) {

            String serErrCd = checkSerNumDuplicate(wmsInbdTrxTMsg, rtlWhMap.get("RTL_WH_CD"), rtlWhMap.get("rwsNum"));
            errorCode = this.getErrCode(errorCode, serErrCd);
        }

        if (ZYPCommonFunc.hasValue(errorCode)) {

            doErrProc(wmsInbdTrxTMsg, errorCode);
            return false;
        }

        return true;
    }

    /**
     * Check RWS Close or Open
     * @param rwsNum String
     * @return boolean (true : Close / false : Open)
     */
    private boolean chkRwsCls(String rwsNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("rwsNum", rwsNum);

        String rwsOpenFlg = (String) this.ssmBatchClient.queryObject("getRwsHdrSts", queryParam);

        if (ZYPConstant.FLG_ON_Y.equals(rwsOpenFlg)) {

            return false;
        }

        return true;
    }

    /**
     * PO Close Data Transfer to S21 Interface Table
     */
    private void doProcClose() {

        BigDecimal unitId = BigDecimal.ONE;
        BigDecimal seqNumber = BigDecimal.ONE;
        S21TransactionTableAccessor trxAccessor = new S21TransactionTableAccessor();

        /*************************************************************
         * 1. Get WMS_INBD_TRX Data
         ************************************************************/
        // Search for POClose(PCLS / PCM) data to be processed from WMS_INBD_TRX.
        // Check of the following after the search.
        //
        // 1.RCVD associated with their PCLS have all.Make sure that the products and quantities of Download Data (WMS_INBD_PO_DTL) exists.
        // If this is not the case in check, it does not interface to s21.
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("wmsTrgtWhCd", trgtWhCdAry);
        queryParam.put("wmsTaskPcls", WMS_TASK.PCLS);
        queryParam.put("wmsTaskPcfm", WMS_TASK.PCFM);
        queryParam.put("wmsTaskRcvd", WMS_TASK.RCVD);
        // Start S21WDS NA Initial Upd
        // queryParam.put("ordTpCdItemChng", WMS_ORD_TP.INBOUND_ITEM_CHANGE);
        // queryParam.put("ordTpCdStkStsChng", WMS_ORD_TP.INBOUND_STOCK_STATUS_CHANGE);
        // QC#17981 Mod.
        // String[] inbdOrdTpCdNot = new String[]{WMS_ORD_TP.INBOUND_ITEM_CHANGE, WMS_ORD_TP.INBOUND_STOCK_STATUS_CHANGE, WMS_ORD_TP.INBOUND_ITEM_CHANGE_FOR_REMAN, WMS_ORD_TP.INBOUND_PACKAGE_CODE_CHANGE};
        // String[] inbdOrdTpCdArr = new String[]{WMS_ORD_TP.INBOUND_ITEM_CHANGE, WMS_ORD_TP.INBOUND_STOCK_STATUS_CHANGE, WMS_ORD_TP.INBOUND_PACKAGE_CODE_CHANGE};
        String[] inbdOrdTpCdNot = inboundOrdTpCdNotWmsOrdTp;
        String[] inbdOrdTpCdArr = inboundOrdTpCdArrWmsOrdTp;

        // End S21WDS NA Initial Upd
        queryParam.put("inbdOrdTpCdNot", inbdOrdTpCdNot);
        queryParam.put("inbdOrdTpCdArr", inbdOrdTpCdArr);
        queryParam.put("procStsCdInComp", PROC_STS.IN_COMPLETED);
        queryParam.put("procStsCdComp", PROC_STS.COMPLEATED);
        queryParam.put("intfcProcStsCd", "1");
        // START 2019/10/08 M.Naito [QC#52526,MOD]
        List<WMS_INBD_TRXTMsg> rcvdErrorWmsInbdTrxTMsgList = this.ssmBatchClient.queryObjectList("getPoClose", queryParam);

        if (rcvdErrorWmsInbdTrxTMsgList == null || rcvdErrorWmsInbdTrxTMsgList.isEmpty()) {

            return;
        }
        for (WMS_INBD_TRXTMsg rcvdErrorWmsInbdTrxTMsg : rcvdErrorWmsInbdTrxTMsgList) {
            if (ZYPCommonFunc.hasValue(rcvdErrorWmsInbdTrxTMsg.errMsgCd.getValue())) {
                this.totalErrCount++;
                updTrxProcSts(rcvdErrorWmsInbdTrxTMsg, PROC_STS.COMPLEATED, null);
                commit();
            }
        }
        // END 2019/10/08 M.Naito [QC#52526,MOD]

        List<WMS_INBD_TRXTMsg> wmsInbdTrxTMsgList = this.ssmBatchClient.queryObjectList("getPoClose", queryParam);

        if (wmsInbdTrxTMsgList == null || wmsInbdTrxTMsgList.isEmpty()) {

            return;
        }

        // Confirm whether the quantity of PCLS and RCVD is inagreement
        List<WMS_INBD_TRXTMsg> chkdWmsInbdTrxTMsgList = chkQtyOfPoClose(wmsInbdTrxTMsgList);

        if (chkdWmsInbdTrxTMsgList.isEmpty()) {

            return;
        }

        ArrayList<String> piErrRwsList = new ArrayList<String>();
        ArrayList<String> nonPiRwsList = new ArrayList<String>();

        // Creating Transaction ID
        BigDecimal trxId = trxAccessor.getNextTransactionId();
        BigDecimal rwsNumSeq = BigDecimal.ZERO;

        for (WMS_INBD_TRXTMsg wmsInbdTrxTMsg : chkdWmsInbdTrxTMsgList) {

            String errorCode = null;

            /*************************************************************
             * 2. Check Inbound PO/SO Data
             ************************************************************/
            String sceOrdTpCd = null;
            String rwsNum = null;
            String inbdOrdNum = wmsInbdTrxTMsg.inbdOrdNum.getValue();
            // QC#21731
            boolean skipRecord = false;
            // QC#52866
            String rwsSts = null;

            // START 2019/11/12 M.Naito [QC#54375,ADD]
            if (notProcInbdOrdToCdList.contains(wmsInbdTrxTMsg.inbdOrdTpCd.getValue())) {
                // update process status code (Processed)
                this.totalCount++;
                updTrxProcSts(wmsInbdTrxTMsg, PROC_STS.COMPLEATED, null);
                continue;
            }
            // END 2019/11/12 M.Naito [QC#54375,ADD]

            Map<String, String> rtlWhMap = getRtlWhMap(wmsInbdTrxTMsg, false);
            RWS_HDRTMsg rwsHdrTMsg = getRwsNum(inbdOrdNum, rtlWhMap);

            if (rwsHdrTMsg != null) {

                rwsNum = rwsHdrTMsg.rwsNum.getValue();
                sceOrdTpCd = convOrdTpCd(wmsInbdTrxTMsg.inbdOrdTpCd.getValue(), rwsHdrTMsg.sceOrdTpCd.getValue(), null);
                // START 2019/09/03 R.Kurahashi [QC#52866,MOD]
                rwsSts = getRwsHdrSts(rwsNum);
                if(!RWS_STS.RECEIVED.equals(rwsSts)) {

                    // Check PI Activity
                    if (piErrRwsList.contains(rwsNum)) {
    
                        S21InfoLogOutput.println(NLBM1347E);
                        this.totalErrCount++;
                        continue;
    
                    } else if (!nonPiRwsList.contains(rwsNum)) {
    
                        if (!chkPiActivity(rwsNum)) {
    
                            piErrRwsList.add(rwsNum);
                            S21InfoLogOutput.println(NLBM1347E);
                            this.totalErrCount++;
                            continue;
    
                        } else {
    
                            nonPiRwsList.add(rwsNum);
                        }
                    }
    
                    // Check Put Away Data
                    Map<String, String> ssmParam = new HashMap<String, String>();
                    ssmParam.put("glblCmpyCd", glblCmpyCd);
                    ssmParam.put("inbdOrdNum", inbdOrdNum);
                    ssmParam.put("procStsCdInComp", PROC_STS.IN_COMPLETED);
                    ssmParam.put("procStsCdErr", PROC_STS.ERROR);
                    BigDecimal notProcRwsPutAwayWrkCnt = (BigDecimal) ssmBatchClient.queryObject("getNotProcRwsPutAwayWrkCnt", ssmParam);
    
                    if (notProcRwsPutAwayWrkCnt != null && BigDecimal.ZERO.compareTo(notProcRwsPutAwayWrkCnt) < 0) {
    
                    	// QC#20499-1 Start
                    	String rwsStsCd = getRwsHdrSts(rwsNum);
                    	if (RWS_STS.RECEIVED.equals(rwsStsCd)) {
                            this.totalCount++;
                            updTrxProcSts(wmsInbdTrxTMsg, PROC_STS.COMPLEATED, errorCode);
                            unitId = unitId.add(BigDecimal.ONE);
                            commit();
                    		continue;
                    	}
                    	// QC#20499-1 End
                    	// 2018/11/19 Mod Start QC#28881
    //                    outputErr(NLAM1106E, new String[] {rwsHdrTMsg.rwsRefNum.getValue() });
                        outputErr(NLAM1106E, new String[] {wmsInbdTrxTMsg.inbdOrdNum.getValue() });
                        // 2018/11/19 Mod End QC#28881
                        // 2020/08/24 Mod Start QC#55400
                        updTrxProcSts(wmsInbdTrxTMsg, PROC_STS.COMPLEATED, NLAM1106E);
                        this.totalErrCount++;
                        // 2020/08/24 Mod End QC#55400
                        continue;
                    }
    
                    // Check Order Type
                    if (!ZYPCommonFunc.hasValue(sceOrdTpCd)) {
    
                        outputErr(NLAM1013E, new String[] {"SCE_ORD_TP", "S80_ORD_TP_CD", wmsInbdTrxTMsg.inbdOrdTpCd.getValue() });
                        errorCode = NLAM1013E;
                    }
    
                    // Check Warehouse
                    if (!ZYPCommonFunc.hasValue(rtlWhMap.get("RTL_WH_CD"))) {
    
                        outputErr(NLAM1009E, new String[] {wmsInbdTrxTMsg.intfcId.getValue(), wmsInbdTrxTMsg.wmsInbdTrxPk.getValue().toPlainString(), unitId.toString(), seqNumber.toString(), "whCd" });
                        errorCode = this.getErrCode(errorCode, NLAM1009E);
    
                    } else {
    
                        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
                        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, rtlWhMap.get("RTL_WH_CD"));
                        rtlWhTMsg = (RTL_WHTMsg) S21ApiTBLAccessor.findByKey(rtlWhTMsg);
    
                        if (rtlWhTMsg == null) {
    
                            outputErr(NLAM1235E, new String[] {wmsInbdTrxTMsg.wmsInbdTrxPk.getValue().toPlainString(), "", unitId.toString(), seqNumber.toString(), "whCd" });
                            errorCode = this.getErrCode(errorCode, NLAM1235E);
                        }
                    }
    
                    // QC#21731 KT or KU Special Logic. check SO Close.
                    if (ZYPCommonFunc.hasValue(sceOrdTpCd) //
                            && (SCE_ORD_TP.KITTING.equals(sceOrdTpCd) || SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd))) {
    
                        SHPG_ORDTMsg soTMsg = getShpgOrd(inbdOrdNum);
                        if (soTMsg == null || !SHPG_STS.SHIPPED.equals(soTMsg.shpgStsCd.getValue())) {
                            outputErr(NLGM0084E, new String[] {wmsInbdTrxTMsg.wmsInbdTrxPk.getValue().toPlainString(), "", unitId.toString(), seqNumber.toString(), inbdOrdNum});
                            errorCode = this.getErrCode(errorCode, NLGM0084E);
                            skipRecord = true;
                        }
                    }
                }
                // END 2019/09/03 R.Kurahashi [QC#52866,MOD]
            } else {

                outputErr(NLAM1013E, new String[] {"RWS_HDR", "RWS_REF_NUM", wmsInbdTrxTMsg.inbdOrdNum.getValue() });
                errorCode = NLAM1001E;
            }

            if (ZYPCommonFunc.hasValue(errorCode)) {
                // QC#21731 skip check.
                if (skipRecord) {
                    rollback();
                } else {
                    doErrProc(wmsInbdTrxTMsg, errorCode);
                }
                continue;
            }

            // START 2019/10/08 M.Naito [QC#52526,MOD]
            // START 2019/09/03 R.Kurahashi [QC#52866,MOD]
            if (!RWS_STS.RECEIVED.equals(rwsSts)) {

                /*************************************************************
                 * 3. Check WMS_INBD_TRX Data
                 ************************************************************/
                if (!(ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.inbdOrdNum))) {

                    outputErr(NLAM1009E, new String[] {wmsInbdTrxTMsg.intfcId.getValue(), wmsInbdTrxTMsg.wmsInbdTrxPk.getValue().toPlainString(), unitId.toString(), seqNumber.toString(), "poNum" });
                    errorCode = this.getErrCode(errorCode, NLAM1009E);
                }

                if (!(ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.inbdOrdTpCd))) {

                    outputErr(NLAM1009E, new String[] {wmsInbdTrxTMsg.intfcId.getValue(), wmsInbdTrxTMsg.wmsInbdTrxPk.getValue().toPlainString(), unitId.toString(), seqNumber.toString(), "s80ordTpCd" });
                    errorCode = this.getErrCode(errorCode, NLAM1009E);
                }

                if (ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.serNum)) {

                    String serErrCd = checkSerNumDuplicate(wmsInbdTrxTMsg, rtlWhMap.get("RTL_WH_CD"), rwsNum);
                    errorCode = this.getErrCode(errorCode, serErrCd);
                }

                if (ZYPCommonFunc.hasValue(errorCode)) {

                    doErrProc(wmsInbdTrxTMsg, errorCode);
                    continue;
                }

                /*************************************************************
                 * 4. Create RWS Complete Work Table
                 ************************************************************/
                rwsNumSeq = rwsNumSeq.add(BigDecimal.ONE);

                RWS_CPLT_WRKTMsg rwsCpltWrkTMsg = new RWS_CPLT_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(rwsCpltWrkTMsg.glblCmpyCd, wmsInbdTrxTMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(rwsCpltWrkTMsg.wrkTrxId, ZYPCommonFunc.leftPad(trxId.toString(), TRAN_ID_LENGTH, LF_PAD_CHAR));
                ZYPEZDItemValueSetter.setValue(rwsCpltWrkTMsg.sqId, ZYPCommonFunc.leftPad(String.valueOf(rwsNumSeq), SEQ_ID_LENGTH, LF_PAD_CHAR));
                ZYPEZDItemValueSetter.setValue(rwsCpltWrkTMsg.rwsNum, rwsNum);
                ZYPEZDItemValueSetter.setValue(rwsCpltWrkTMsg.procStsCd, PROC_STS.IN_COMPLETED);
                ZYPEZDItemValueSetter.setValue(rwsCpltWrkTMsg.sceOrdTpCd, sceOrdTpCd);
                ZYPEZDItemValueSetter.setValue(rwsCpltWrkTMsg.s80OrdTpCd, wmsInbdTrxTMsg.inbdOrdTpCd.getValue());
                ZYPEZDItemValueSetter.setValue(rwsCpltWrkTMsg.rwsCloDtTmTs, getSalesDateTm());
                ZYPEZDItemValueSetter.setValue(rwsCpltWrkTMsg.whCd, rtlWhMap.get("RTL_WH_CD"));
                ZYPEZDItemValueSetter.setValue(rwsCpltWrkTMsg.rwsRefNum, wmsInbdTrxTMsg.inbdOrdNum.getValue());
                ZYPEZDItemValueSetter.setValue(rwsCpltWrkTMsg.srcTpCd, SRC_TP_CD_WMS);
                ZYPEZDItemValueSetter.setValue(rwsCpltWrkTMsg.svcMachProcStsCd, SVC_MACH_PROC_STS.IN_COMPLETED);
                S21FastTBLAccessor.insert(rwsCpltWrkTMsg);

                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rwsCpltWrkTMsg.getReturnCode())) {

                    errorCode = NLGM0045E;
                    outputErr(NLGM0045E, new String[] {rwsCpltWrkTMsg.getTableName(), TBL_WMS_INBD_TRX //
                            , NLXCMsgHelper.toListedString(COL_WMS_INBD_TRX_PK) //
                            , NLXCMsgHelper.toListedString(wmsInbdTrxTMsg.wmsInbdTrxPk.getValue()) });
                }

                if (!ZYPCommonFunc.hasValue(errorCode)) {

                    // Update SO/PO Order Close Date
                    errorCode = updOrderCloseDate(wmsInbdTrxTMsg);
                }

                if (ZYPCommonFunc.hasValue(errorCode)) {

                    doErrProc(wmsInbdTrxTMsg, errorCode);
                    continue;
                }

                /*************************************************************
                 * 5. Execute RWS Completion
                 ************************************************************/
                // Data Lock
                Map<String, String> ssmParam = new HashMap<String, String>();
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                ssmParam.put("rwsNum", rwsNum);

                List<Map<String, Object>> rwsHdrLockMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRwsHdrLock", ssmParam);

                if (rwsHdrLockMapList == null || rwsHdrLockMapList.isEmpty()) {

                    continue;
                }

                // Call RWS Complete API
                NLZC209001PMsg pmsg = new NLZC209001PMsg();
                ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pmsg.wrkTrxId, rwsCpltWrkTMsg.wrkTrxId.getValue());
                ZYPEZDItemValueSetter.setValue(pmsg.sqId, rwsCpltWrkTMsg.sqId.getValue());
                ZYPEZDItemValueSetter.setValue(pmsg.slsDt, salesDate);

                NLZC209001 apiNLZC209001 = new NLZC209001();
                apiNLZC209001.execute(pmsg, S21ApiInterface.ONBATCH_TYPE.BATCH);

                if (!NLXC014001.getMesRtn(pmsg)) {

                    boolean errFlg = false;

                    for (String xxMsgId : S21ApiUtil.getXxMsgIdList(pmsg)) {

                        if (!errFlg) {

                            errorCode = xxMsgId;
                        }

                        outputErr(xxMsgId);
                        errFlg = true;
                    }

                    doErrProc(wmsInbdTrxTMsg, errorCode);
                    continue;
                }

                // Update RWS Complete Work
                ZYPEZDItemValueSetter.setValue(rwsCpltWrkTMsg.procStsCd, PROC_STS.COMPLEATED);
                updRwsCpltWrk(rwsCpltWrkTMsg);

                for (String xxMsgId : S21ApiUtil.getXxMsgIdList(pmsg)) {

                    S21InfoLogOutput.println(xxMsgId);
                    this.termNormalFlg = false;
                }

                // Update Serial for RWS Completion API
                if (!callUpdSerFoRwsCompApi(rwsCpltWrkTMsg, null, wmsInbdTrxTMsg)) {

                    continue;
                }

                // START 2022/04/28 J.Evangelista [QC#58807,ADD]
                // Terminate Intangible Item
                if (!SCE_ORD_TP.RETURN.equals(sceOrdTpCd) && !trmnIntgItem(rwsNum, wmsInbdTrxTMsg)) {

                    continue;
                }
                // END 2022/04/28 J.Evangelista [QC#58807,ADD]

                /*************************************************************
                 * 6. Update WMS_INBD_TRX & Commit
                 ************************************************************/
                this.totalCount++;
                updTrxProcSts(wmsInbdTrxTMsg, PROC_STS.COMPLEATED, errorCode);
                unitId = unitId.add(BigDecimal.ONE);
                commit();
//            }
            } else {
                this.totalCount++;
                updTrxProcSts(wmsInbdTrxTMsg, PROC_STS.COMPLEATED, null);
                unitId = unitId.add(BigDecimal.ONE);
                commit();
            }
            // END 2019/09/03 R.Kurahashi [QC#52866,MOD]
            // END 2019/10/08 M.Naito [QC#52526,MOD]
        }
    }

    /**
     * Update Serial for Put Away Confirmation API (NLZC4070)
     * 
     * @param rwsPutAwayWrkTMsg RWS_PUT_AWAY_WRKTMsg
     * @param wmsInbdTrxTMsg WMS_INBD_TRXTMsg
     * @return boolean (true : OK / false : NG)
     */
    private boolean callUpdSerForPutAwayApi(RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkTMsg, WMS_INBD_TRXTMsg wmsInbdTrxTMsg) {

        String errorCode = null;

        NLZC407001PMsg pMsg = new NLZC407001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.rwsNum, rwsPutAwayWrkTMsg.rwsNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.inputList.no(0).wrkTrxId, rwsPutAwayWrkTMsg.wrkTrxId.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.inputList.no(0).sqId, rwsPutAwayWrkTMsg.sqId.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.inputList.no(0).rwsDtlLineNum, rwsPutAwayWrkTMsg.rwsDtlLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.inputList.no(0).invtyStkStsCd, rwsPutAwayWrkTMsg.invtyStkStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.inputList.no(0).mdseCd, rwsPutAwayWrkTMsg.mdseCd.getValue());
        pMsg.inputList.setValidCount(1);

        NLZC407001 api = new NLZC407001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

                errorCode = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();

                if (ZYPCommonFunc.hasValue(errorCode)) {

                    this.termNormalFlg = false;

                    if (errorCode.endsWith("E")) {
                        // START 2021/09/16 R.Azucena[QC#58435, MOD]
                        // outputErr(errorCode);
                        if (NLZM2091E.equals(errorCode)) {
                            errorCode = NLGM0099E;
                            outputErr(errorCode, new String[] {rwsPutAwayWrkTMsg.rwsNum.getValue(), rwsPutAwayWrkTMsg.rwsDtlLineNum.getValue(), rwsPutAwayWrkTMsg.mdseCd.getValue() });
                        } else {
                            outputErr(errorCode);
                        }
                        // END 2021/09/16 R.Azucena[QC#58435, MOD]

                        doErrProc(wmsInbdTrxTMsg, errorCode);
                        return false;
                    }
                }
            }
        }

        // Update RWS_PUT_AWAY_WRK
        ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.svcMachProcStsCd, SVC_MACH_PROC_STS.COMPLETED);
        ZYPEZDItemValueSetter.setValue(rwsPutAwayWrkTMsg.errMsgCd, errorCode);
        updRwsPutAwayWrk(rwsPutAwayWrkTMsg);

        return true;
    }

    /**
     * Update Serial for RWS Completion API (NLZC4070)
     * 
     * @param rwsCpltWrkTMsg RWS_CPLT_WRKTMsg
     * @param rwsPutAwayWrkTMsg RWS_PUT_AWAY_WRKTMsg
     * @param wmsInbdTrxTMsg WMS_INBD_TRXTMsg
     * @return boolean (true : OK / false : NG)
     */
    private boolean callUpdSerFoRwsCompApi(RWS_CPLT_WRKTMsg rwsCpltWrkTMsg, RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkTMsg, WMS_INBD_TRXTMsg wmsInbdTrxTMsg) {

        String rwsNum = null;
        String sceOrdTpCd = null;
        String errorCode = null;

        if (rwsCpltWrkTMsg != null) {

            rwsNum = rwsCpltWrkTMsg.rwsNum.getValue();
            sceOrdTpCd = rwsCpltWrkTMsg.sceOrdTpCd.getValue();

        } else {

            rwsNum = rwsPutAwayWrkTMsg.rwsNum.getValue();
            sceOrdTpCd = rwsPutAwayWrkTMsg.sceOrdTpCd.getValue();
        }

        if (putAwaySkipSceTpList.contains(sceOrdTpCd)) {

            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("rwsNum", rwsNum);
            queryParam.put("procStsCd", PROC_STS.COMPLEATED);
            queryParam.put("svcMachProcStsCd", SVC_MACH_PROC_STS.IN_COMPLETED);
            List<Map<String, String>> skipPutAwayWrkLst = (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getSkipPutAwayWrk", queryParam);

            for (Map<String, String> skipPutAwayWrkMap : skipPutAwayWrkLst) {

                RWS_PUT_AWAY_WRKTMsg skipRwsPutAwayWrkTMsg = new RWS_PUT_AWAY_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(skipRwsPutAwayWrkTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(skipRwsPutAwayWrkTMsg.wrkTrxId, (String) skipPutAwayWrkMap.get("WRK_TRX_ID"));
                ZYPEZDItemValueSetter.setValue(skipRwsPutAwayWrkTMsg.sqId, (String) skipPutAwayWrkMap.get("SQ_ID"));
                skipRwsPutAwayWrkTMsg = (RWS_PUT_AWAY_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(skipRwsPutAwayWrkTMsg);

                if (!callUpdSerForPutAwayApi(skipRwsPutAwayWrkTMsg, wmsInbdTrxTMsg)) {

                    return false;
                }
            }
        }

        NLZC407001PMsg pMsg = new NLZC407001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.rwsNum, rwsNum);
        ZYPEZDItemValueSetter.setValue(pMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.xxPrcCloFlg, ZYPConstant.FLG_ON_Y);

        NLZC407001 apiNLZC407001 = new NLZC407001();
        apiNLZC407001.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

                errorCode = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();

                if (ZYPCommonFunc.hasValue(errorCode)) {

                    this.termNormalFlg = false;

                    if (errorCode.endsWith("E")) {

                        outputErr(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                        doErrProc(wmsInbdTrxTMsg, errorCode);
                        return false;
                    }
                }
            }
        }

        // Update RWS_CPLT_WRK
        if (rwsCpltWrkTMsg != null) {

            ZYPEZDItemValueSetter.setValue(rwsCpltWrkTMsg.svcMachProcStsCd, SVC_MACH_PROC_STS.COMPLETED);
            ZYPEZDItemValueSetter.setValue(rwsCpltWrkTMsg.errMsgCd, errorCode);
            updRwsCpltWrk(rwsCpltWrkTMsg);
        }

        return true;
    }

    /**
     * Update RWS_PUT_AWAY_WRK
     * 
     * @param rwsPutAwayWrkTMsg RWS_PUT_AWAY_WRKTMsg
     */
    private void updRwsPutAwayWrk(RWS_PUT_AWAY_WRKTMsg rwsPutAwayWrkTMsg) {

        EZDTBLAccessor.update(rwsPutAwayWrkTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsPutAwayWrkTMsg.getReturnCode())) {

            rollback();
            throw new S21AbendException(NLAM1134E, new String[] {rwsPutAwayWrkTMsg.getTableName(), NLXCMsgHelper.toListedString("GLBL_CMPY_CD", "WRK_TRX_ID", "SQ_ID"),
                    NLXCMsgHelper.toListedString(rwsPutAwayWrkTMsg.glblCmpyCd, rwsPutAwayWrkTMsg.wrkTrxId, rwsPutAwayWrkTMsg.sqId) });
        }
    }

    /**
     * Update RWS_CPLT_WRK
     * 
     * @param rwsCpltWrkTMsg RWS_CPLT_WRKTMsg
     */
    private void updRwsCpltWrk(RWS_CPLT_WRKTMsg rwsCpltWrkTMsg) {

        EZDTBLAccessor.update(rwsCpltWrkTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsCpltWrkTMsg.getReturnCode())) {

            throw new S21AbendException(NLAM1134E, new String[] {rwsCpltWrkTMsg.getTableName(), NLXCMsgHelper.toListedString("GLBL_CMPY_CD", "WRK_TRX_ID", "SQ_ID"),
                    NLXCMsgHelper.toListedString(rwsCpltWrkTMsg.glblCmpyCd.getValue(), rwsCpltWrkTMsg.wrkTrxId.getValue(), rwsCpltWrkTMsg.sqId.getValue()) });
        }
    }

    /**
     * Putting Error Message.
     * 
     * Output to logs the specified message.
     * And Append to Message List for error notification mail.
     * 
     * @param msgId String
     */
    private void outputErr(String msgId) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId));
        // START 2019/11/05 T.Ogura [QC#54080,ADD]
        if (errMsgList.contains(mailParam) ) {
            return;
        }
        // END   2019/11/05 T.Ogura [QC#54080,ADD]
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(msgId);
    }

    /**
     * Error Process
     * @param wmsInbdTrxTMsg WMS_INBD_TRXTMsg
     * @param errorCode String
     */
    private void doErrProc(WMS_INBD_TRXTMsg wmsInbdTrxTMsg, String errorCode) {

        // START 2019/10/08 M.Naito [QC#52526,ADD]
        if (isInclSkipList(errorCode)) {
            rollback();
            this.totalErrCount++;
            updTrxProcSts(wmsInbdTrxTMsg, PROC_STS.COMPLEATED, errorCode);
            commit();
            return;
        }
        // END 2019/10/08 M.Naito [QC#52526,ADD]

        rollback();
        this.totalErrCount++;
        updTrxProcSts(wmsInbdTrxTMsg, PROC_STS.ERROR, errorCode);
        commit();
    }

//    /**
//     * PO Cancel Data Transfer to S21 Interface Table (NLBI0020_01)
//     */
//    private void doProcCancel() {
//
//        BigDecimal unitId = BigDecimal.ONE;
//        S21TransactionTableAccessor trxAccessor = new S21TransactionTableAccessor();
//
//        // Search for PDLT data to be processed from WMS_INBD_TRX.
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("wmsTrgtWhCd", trgtWhCdAry);
//        queryParam.put("wmsTaskPdlt", WMS_TASK.PDLT);
////        queryParam.put("ordTpCdItemChng", WMS_ORD_TP.INBOUND_ITEM_CHANGE);
////        queryParam.put("ordTpCdStkStsChng", WMS_ORD_TP.INBOUND_STOCK_STATUS_CHANGE);
//        // QC#17981 Mod.
////        String[] inbdOrdTpCdArr = new String[]{WMS_ORD_TP.INBOUND_ITEM_CHANGE, WMS_ORD_TP.INBOUND_STOCK_STATUS_CHANGE, WMS_ORD_TP.INBOUND_ITEM_CHANGE_FOR_REMAN, WMS_ORD_TP.INBOUND_PACKAGE_CODE_CHANGE};
//        String[] inbdOrdTpCdArr = InboundOrdTpCdArrIfWmsOrdTp;
//
//        queryParam.put("inbdOrdTpCdArr", inbdOrdTpCdArr);
//        queryParam.put("procStsCd", PROC_STS.IN_COMPLETED);
//        List trxList = this.ssmBatchClient.queryObjectList("getPoCancel", queryParam);
//        if (trxList == null || trxList.isEmpty()) {
//            return;
//        }
//
//        // Creating Transaction ID
//        BigDecimal trxId = trxAccessor.getNextTransactionId();
//        int ifTblRegdCnt = 0;
//
//        for (int i = 0; i < trxList.size(); i++) {
//            String errorCode = "";
//            boolean isIfTblRegd = false;
//            WMS_INBD_TRXTMsg wmsInbdTrxTMsg = (WMS_INBD_TRXTMsg) trxList.get(i);
//            Map<String, String> rtlWhMap = getRtlWhMap(wmsInbdTrxTMsg, false);
//
//            // QC#17981 Mod.
////            if (WMS_ORD_TP.INBOUND_ITEM_CHANGE.equals(wmsInbdTrxTMsg.inbdOrdTpCd.getValue()) || WMS_ORD_TP.INBOUND_STOCK_STATUS_CHANGE.equals(wmsInbdTrxTMsg.inbdOrdTpCd.getValue())) {
//            if (Arrays.asList(createNlgc001001WmsOrdTp).contains(wmsInbdTrxTMsg.inbdOrdTpCd.getValue())) { 
//
//                // Creating Fixed length Data
//                StringBuilder wmsSoConfDataIf = new StringBuilder();
//                //wmsSoConfDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsInbdTrxTMsg.whCd.getValue(), LG_INTFC_WH_CD));
//                wmsSoConfDataIf.append(NLGC001001.convFixedLgLeftAlign(rtlWhMap.get(KEY_RTL_WH_CD), LG_INTFC_WH_CD));
//                String wmsRecId = wmsInbdTrxTMsg.wmsRecId.getValue();
//                if (ZYPCommonFunc.hasValue(wmsRecId) && wmsRecId.length() > LG_INTFC_WMS_REC_ID) {
//                    wmsRecId = wmsRecId.substring(LG_CUT_WMS_REC_ID_FROM, LG_INTFC_WMS_REC_ID);
//                }
//                wmsSoConfDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsRecId, LG_INTFC_WMS_REC_ID));
//                wmsSoConfDataIf.append(NLGC001001.convFixedLgLeftAlign(INTFC_REC_TP_PO_CANCEL, LG_INTFC_REC_TP));
//                wmsSoConfDataIf.append(NLGC001001.convFixedLgLeftAlign(INTFC_DTL_TP, LG_INTFC_DTL_TP));
//                wmsSoConfDataIf.append(NLGC001001.convFixedLgLeftAlign("", LG_INTFC_FILL));
//                wmsSoConfDataIf.append(NLGC001001.convFixedLgLeftAlign(INTFC_CMPY_CD, LG_INTFC_CMPY_CD));
//                //wmsSoConfDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsInbdTrxTMsg.whCd.getValue(), LG_INTFC_WH_CD));
//                wmsSoConfDataIf.append(NLGC001001.convFixedLgLeftAlign(rtlWhMap.get(KEY_RTL_WH_CD), LG_INTFC_WH_CD));
//                wmsSoConfDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsInbdTrxTMsg.inbdOrdNum.getValue(), LG_INTFC_INBD_ORD_NUM));
//                wmsSoConfDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsInbdTrxTMsg.inbdOrdTpCd.getValue(), LG_INTFC_INBD_ORD_TP_CD));
//                wmsSoConfDataIf.append(NLGC001001.convFixedLgLeftAlign(NLGC001001.confIntfcDtTmTsFmt(wmsInbdTrxTMsg.wmsTrxDtTmTs.getValue()) //
//                        , LG_INTFC_WMS_TRX_DT_TM_TS));
//                wmsSoConfDataIf.append(WMS_TOT_WT_IF);
//                wmsSoConfDataIf.append(WMS_FRT_CHRG_AMT_IF);
//                wmsSoConfDataIf.append(PDLT_ORD_PROC_STS_IF);
//
//                // Insert into NLBI0020_01
//                NLBI0020_01TMsg albi0020TMsg = new NLBI0020_01TMsg();
//                ZYPEZDItemValueSetter.setValue(albi0020TMsg.interfaceId, INTFC_ID.SO_CONFIRMATION_MW);
//                ZYPEZDItemValueSetter.setValue(albi0020TMsg.transactionId, trxId);
//                ZYPEZDItemValueSetter.setValue(albi0020TMsg.segmentId, BigDecimal.ONE);
//                ZYPEZDItemValueSetter.setValue(albi0020TMsg.unitId, unitId);
//                ZYPEZDItemValueSetter.setValue(albi0020TMsg.seqNumber, BigDecimal.ONE);
//                ZYPEZDItemValueSetter.setValue(albi0020TMsg.wmsSoConfDataIf, wmsSoConfDataIf.toString());
//                S21FastTBLAccessor.insert(albi0020TMsg);
//
//                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(albi0020TMsg.getReturnCode())) {
//                    errorCode = NLGM0045E;
//                    outputErr(NLGM0045E, new String[] {albi0020TMsg.getTableName(), TBL_WMS_INBD_TRX //
//                            , NLXCMsgHelper.toListedString(COL_WMS_INBD_TRX_PK) //
//                            , NLXCMsgHelper.toListedString(wmsInbdTrxTMsg.wmsInbdTrxPk.getValue()) });
//                } else {
//                    isIfTblRegd = true;
//                }
//            }
//            if (!ZYPCommonFunc.hasValue(errorCode)) {
//                // Update SO/PO Order Close Date
//                errorCode = updOrderCloseDate(wmsInbdTrxTMsg);
//            }
//
//            if (!ZYPCommonFunc.hasValue(errorCode)) {
//                this.normalRecCount++;
//                if (isIfTblRegd) {
//                    ifTblRegdCnt++;
//                }
//                // Update Process Status Code(COMPLEATED)
//                updTrxProcSts(wmsInbdTrxTMsg, PROC_STS.COMPLEATED, errorCode);
//                // QC#17981 Mod.
////                if (WMS_ORD_TP.INBOUND_ITEM_CHANGE.equals(wmsInbdTrxTMsg.inbdOrdTpCd.getValue()) || WMS_ORD_TP.INBOUND_STOCK_STATUS_CHANGE.equals(wmsInbdTrxTMsg.inbdOrdTpCd.getValue())) {
//                if (Arrays.asList(updateProcStsCompWmsOrdTp).contains(wmsInbdTrxTMsg.inbdOrdTpCd.getValue())) { 
//                    // Increment UNIT_ID
//                    unitId = unitId.add(BigDecimal.ONE);
//                }
//                commit();
//            } else {
//                this.totalErrCount++;
//                rollback();
//                // Update Process Status Code(ERROR)
//                updTrxProcSts(wmsInbdTrxTMsg, PROC_STS.ERROR, errorCode);
//                commit();
//            }
//        }
//        if (ifTblRegdCnt > 0) {
//            // Insert into INTERFACE_TRANSACTION
//            trxAccessor.createIntegrationRecordForBatch(INTFC_ID.SO_CONFIRMATION_MW, trxId);
//            commit();
//        }
//    }

    /**
     * Get Order Type Code to convert S21
     * 
     * @param s80OrdTpCd String
     * @param rwsSceOrdTpCd String
     * @param rwsNum String
     * @return String
     */
    private String convOrdTpCd(String s80OrdTpCd, String rwsSceOrdTpCd, String rwsNum) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("s80OrdTpCd", s80OrdTpCd);
        queryParam.put("inbdOtbdCd", INBD_OTBD.INBOUND);
        List<String> sceOrdTpLst = (List<String>) this.ssmBatchClient.queryObjectList("getSceOrdTp", queryParam);

        if (sceOrdTpLst == null || sceOrdTpLst.isEmpty()) {

            return null;
        }

        if (sceOrdTpLst.size() == 1) {

            return sceOrdTpLst.get(0);

        } else {

            String chkSceOrdTpCd = null;

            if (!ZYPCommonFunc.hasValue(rwsSceOrdTpCd) && ZYPCommonFunc.hasValue(rwsNum)) {

                RWS_HDRTMsg rwsHdrTMsg = new RWS_HDRTMsg();
                ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.rwsNum, rwsNum);
                rwsHdrTMsg = (RWS_HDRTMsg) EZDTBLAccessor.findByKey(rwsHdrTMsg);

                if (rwsHdrTMsg != null) {

                    chkSceOrdTpCd = rwsHdrTMsg.sceOrdTpCd.getValue();

                } else {

                    return null;
                }

            } else {

                chkSceOrdTpCd = rwsSceOrdTpCd;
            }

            for (String sceOrdTpCd : sceOrdTpLst) {

                if (ZYPCommonFunc.hasValue(sceOrdTpCd) && sceOrdTpCd.equals(chkSceOrdTpCd)) {

                    return sceOrdTpCd;
                }
            }
        }

        return null;
    }

    /**
     * Get RWS Number
     * 
     * @param glblCmpyCd String
     * @param poNum String
     * @param rtlWhMap Map<String, String>
     * @return RWS_HDRTMsg
     */
    private RWS_HDRTMsg getRwsNum(String poNum, Map<String, String> rtlWhMap) {

        RWS_HDRTMsg rwsHdrT = new RWS_HDRTMsg();
        rwsHdrT.setConditionValue("glblCmpyCd01", glblCmpyCd);
        rwsHdrT.setConditionValue("rwsRefNum01", poNum);
        rwsHdrT.setConditionValue("whCd01", rtlWhMap.get("RTL_WH_CD"));
        rwsHdrT.setSQLID("009");
        RWS_HDRTMsgArray rwsHdrArrayT = (RWS_HDRTMsgArray) EZDTBLAccessor.findByCondition(rwsHdrT);

        if (rwsHdrArrayT.length() == 0) {

            outputErr(NLAM1001E, new String[] {"RWS_HDR", NLXCMsgHelper.toListedString("GLBL_CMPY_CD", "RWS_REF_NUM", "WH_CD"),
                    NLXCMsgHelper.toListedString(this.glblCmpyCd, poNum, rtlWhMap.get("RTL_SWH_CD")) });

            return null;
        }

        return rwsHdrArrayT.no(0);
    }
    /**
     * getRwsHdrSts
     * @param rwsNum
     * @return
     */
    private String getRwsHdrSts(String rwsNum) {

        RWS_HDRTMsg rwsHdrTMsg = new RWS_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.rwsNum, rwsNum);
        rwsHdrTMsg = (RWS_HDRTMsg) EZDTBLAccessor.findByKey(rwsHdrTMsg);
        return rwsHdrTMsg.rwsStsCd.getValue();
    }

    /**
     * Check PI Activity
     * @param rwsNum String
     * @return boolean (true : OK, false : NG)
     */
    private boolean chkPiActivity(String rwsNum) {

        NLZC060001PMsg pMsg = new NLZC060001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.rwsNum, rwsNum);

        NLZC060001 nlzc060001 = new NLZC060001();
        nlzc060001.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (NLZC060001Constant.RETURN_CODE_ERROR.equals(pMsg.xxRsltStsCd.getValue())) {

            return false;
        }

        return true;
    }

    /**
     * Get Sales Date Time stamp
     * 
     * @return yyyymmdd + hhmmss
     */
    private String getSalesDateTm() {

        String yyyymmdd = ZYPDateUtil.getSalesDate();
        String hhmmss = ZYPDateUtil.getCurrentSystemTime("HHmmss");

        return yyyymmdd + hhmmss;
    }

    /**
     * Putting Error Message for email.
     * <p>
     * Output to logs the specified message.
     * And Append to Message List for error notification mail.
     * 
     * @param String msgId
     * @param String[] msgParams
     * @param String sceOrdTpCd
     */
    private void outputErrMailCut(String msgId, String[] msgParam, String sceOrdTpCd) {

        if (ZYPCommonFunc.hasValue(sceOrdTpCd) && !putAwaySkipSceTpList.contains(sceOrdTpCd)) {

            Map<String, String> mailParam = new HashMap<String, String>();
            mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
            mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
            // START 2019/11/05 T.Ogura [QC#54080,ADD]
            if (errMsgList.contains(mailParam) ) {
                return;
            }
            // END   2019/11/05 T.Ogura [QC#54080,ADD]
            errMsgList.add(mailParam);
        }

        S21InfoLogOutput.println(msgId, msgParam);
    }

    /**
     * Confirm whether the quantity of PCLS and RCVD is inagreement.
     * When not in agreement, if it has passed over the definite
     * period of time(Table:VAR_CHAR_CONST key:CHK_PO_CLO_MIN_INTVL),
     * it will interface with S21.
     * @param wmsInbdTrxTMsgList List<WMS_INBD_TRXTMsg>
     * @return List<WMS_INBD_TRXTMsg> checked List
     */
    private List<WMS_INBD_TRXTMsg> chkQtyOfPoClose(List<WMS_INBD_TRXTMsg> wmsInbdTrxTMsgList) {

        // Getting Minute to WMS PO Close Check Interval
        long poCloseMinIntvl = 0;
        String chkPoCloMinIntvl = ZYPCodeDataUtil.getVarCharConstValue(VLD_INTVL_PO_CLO_QTY_MIN, glblCmpyCd);

        if (ZYPCommonFunc.hasValue(chkPoCloMinIntvl)) {

            poCloseMinIntvl = Long.parseLong(chkPoCloMinIntvl);

        } else {

            poCloseMinIntvl = DEF_PO_CLO_MIN_INTVL;
        }

        List<WMS_INBD_TRXTMsg> chkdWmsInbdTrxTMsgList = new ArrayList<WMS_INBD_TRXTMsg>();

        // Calculation of interval judgment date and time
        String poCloseChkDtTmTs = EZDCommonFunc.toyyyyMMddHHmmss(EZDCommonFunc.toTimeMinute(ZYPDateUtil.getCurrentSystemTime(CUR_DT_TM_FMT)) //
                - (poCloseMinIntvl * MIN_CALC_MULT_VAL));

        for (WMS_INBD_TRXTMsg wmsInbdTrxTMsg : wmsInbdTrxTMsgList) {

            String ezInTime = wmsInbdTrxTMsg.ezInTime.getValue().substring(LG_CUT_FROM_EZINTIME, LG_CUT_TO_EZINTIME);

            if (WMS_TASK.PCLS.equals(wmsInbdTrxTMsg.wmsTaskCd.getValue()) //
                    && (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.wmsOrdQty.getValue()) || wmsInbdTrxTMsg.wmsOrgQty.getValue().compareTo(wmsInbdTrxTMsg.wmsOrdQty.getValue()) != 0)) {

                if (poCloseChkDtTmTs.compareTo(ezInTime) > 0) {

                    // START 2021/02/22 J.Evangelista [QC#58380,MOD]
                    // S21InfoLogOutput.println(NLGM0056I, new String[] {wmsInbdTrxTMsg.inbdOrdNum.getValue(), wmsInbdTrxTMsg.getTableName() });
                    // chkdWmsInbdTrxTMsgList.add(wmsInbdTrxTMsg);
                    String sceOrdTpCd = "";
                    Map<String, String> rtlWhMap = getRtlWhMap(wmsInbdTrxTMsg, false);
                    RWS_HDRTMsg rwsHdrTMsg = getRwsNum(wmsInbdTrxTMsg.inbdOrdNum.getValue(), rtlWhMap);
                    if (rwsHdrTMsg != null) {
                        sceOrdTpCd = convOrdTpCd(wmsInbdTrxTMsg.inbdOrdTpCd.getValue(), rwsHdrTMsg.sceOrdTpCd.getValue(), null);
                    }
                    if (NLXSceConst.SCE_ORD_TP_CD_SC.equals(sceOrdTpCd)
                            || NLXSceConst.SCE_ORD_TP_CD_IC.equals(sceOrdTpCd)
                            || NLXSceConst.SCE_ORD_TP_CD_CC.equals(sceOrdTpCd)
                            || NLXSceConst.SCE_ORD_TP_CD_SW.equals(sceOrdTpCd)
                            || NLXSceConst.SCE_ORD_TP_CD_IT.equals(sceOrdTpCd)
                            || NLXSceConst.SCE_ORD_TP_CD_BB.equals(sceOrdTpCd)) {

                        // Since it passed over the definite period of time and the quantity of
                        // PO Receipt (RCVD) is insufficient, PO Close (PCLS) processing is stopped.
                        outputErr(NLGM0094E, new String[] {wmsInbdTrxTMsg.inbdOrdNum.getValue(), wmsInbdTrxTMsg.getTableName()});
                    } else {

                        // Since it passed over the definite period of time while PO Receipt (RCVD) had been
                        // deficiency in quantity, it interfaces with S21.
                        S21InfoLogOutput.println(NLGM0056I, new String[] {wmsInbdTrxTMsg.inbdOrdNum.getValue(), wmsInbdTrxTMsg.getTableName() });
                        chkdWmsInbdTrxTMsgList.add(wmsInbdTrxTMsg);
                    }
                    // END 2021/02/22 J.Evangelista [QC#58380,MOD]
                } else {

                    // Since the quantity of PO Receipt (RCVD) is insufficient, POClose processing is stopped.
                    S21InfoLogOutput.println(NLGM0055I, new String[] {wmsInbdTrxTMsg.inbdOrdNum.getValue(), wmsInbdTrxTMsg.getTableName() });
                }

            } else {

                chkdWmsInbdTrxTMsgList.add(wmsInbdTrxTMsg);
            }
        }

        return chkdWmsInbdTrxTMsgList;
    }

    /**
     * update WMS_INBD_TRX Table of PROC_STS_CD and ERR_MSG_CD
     * @param wmsInbdTrxTMsg WMS_INBD_TRXTMsg
     * @param procStsCd String
     * @param errMsgCd String
     */
    private void updTrxProcSts(WMS_INBD_TRXTMsg wmsInbdTrxTMsg, String procStsCd, String errMsgCd) {

        WMS_INBD_TRXTMsg updWmsInbdTrxTMsg = (WMS_INBD_TRXTMsg) EZDTBLAccessor.findByKeyForUpdate(wmsInbdTrxTMsg);
        ZYPEZDItemValueSetter.setValue(updWmsInbdTrxTMsg.procStsCd, procStsCd);

        if (!ZYPCommonFunc.hasValue(errMsgCd)) {

            updWmsInbdTrxTMsg.errMsgCd.clear();

        } else {

            ZYPEZDItemValueSetter.setValue(updWmsInbdTrxTMsg.errMsgCd, errMsgCd);
        }

        EZDTBLAccessor.updateSelectionField(updWmsInbdTrxTMsg, new String[] {"procStsCd", "errMsgCd" });
    }

    /**
     * Update SO/PO Order Close Date.
     * @param wmsInbdTrxTMsg WMS_INBD_TRXTMsg
     * @return String (Error Code)
     */
    private String updOrderCloseDate(WMS_INBD_TRXTMsg wmsInbdTrxTMsg) {

        String wmsTaskCd = wmsInbdTrxTMsg.wmsTaskCd.getValue();
        String inbdOrdTpCd = wmsInbdTrxTMsg.inbdOrdTpCd.getValue();
        String inbdOrdNum = wmsInbdTrxTMsg.inbdOrdNum.getValue();
        String wmsTrxDtTmTs = wmsInbdTrxTMsg.wmsTrxDtTmTs.getValue();
        String curDt = ZYPDateUtil.getCurrentSystemTime(CUR_DT_TM_FMT);

        if (!ZYPCommonFunc.hasValue(wmsTrxDtTmTs)) {

            wmsTrxDtTmTs = curDt;
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("whCd", wmsInbdTrxTMsg.whCd.getValue());

        // QC#17981 Mod.
//        if (WMS_ORD_TP.INBOUND_ITEM_CHANGE.equals(inbdOrdTpCd) || WMS_ORD_TP.INBOUND_STOCK_STATUS_CHANGE.equals(inbdOrdTpCd)) {
        if (Arrays.asList(updateGetWmsInbdSoHdrWmsOrdTp).contains(inbdOrdTpCd)) {

            // For Order Type item Change(R) or stock status change(S),
            // to update the cancellation date or close date, of WMS_INBD_SO_HDR.
            // If it has been canceled or already closed, it is an error.
            String updColNm = "";

            if (WMS_TASK.PDLT.equals(wmsTaskCd)) {

                updColNm = KEY_WMS_CANC_DT_TM_TS;

            } else {

                updColNm = KEY_SHIP_DT_TM_TS;
            }

            queryParam.put("wmsSoId", inbdOrdNum);

            List<WMS_INBD_SO_HDRTMsg> wmsInbdSoHdrTMsgList = (List<WMS_INBD_SO_HDRTMsg>) this.ssmBatchClient.queryObjectList("getWmsInbdSoHdr", queryParam);

            if (wmsInbdSoHdrTMsgList != null && !wmsInbdSoHdrTMsgList.isEmpty()) {

                WMS_INBD_SO_HDRTMsg wmsInbdSoHdrTMsg = (WMS_INBD_SO_HDRTMsg) wmsInbdSoHdrTMsgList.get(0);

                if (!ZYPCommonFunc.hasValue(wmsInbdSoHdrTMsg.wmsCancDtTmTs.getValue()) && !ZYPCommonFunc.hasValue(wmsInbdSoHdrTMsg.shipDtTmTs.getValue())) {

                    ZYPEZDItemValueSetter.setValue(wmsInbdSoHdrTMsg.wmsCancDtTmTs, wmsTrxDtTmTs);
                    ZYPEZDItemValueSetter.setValue(wmsInbdSoHdrTMsg.shipDtTmTs, wmsTrxDtTmTs);
                    EZDTBLAccessor.updateSelectionField(wmsInbdSoHdrTMsg, new String[] {updColNm });

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdSoHdrTMsg.getReturnCode())) {

                        outputErr(NLGM0046E, new String[] {wmsInbdSoHdrTMsg.getTableName() //
                                , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WH_CD, COL_WMS_SQ_NUM) //
                                , NLXCMsgHelper.toListedString(wmsInbdSoHdrTMsg.glblCmpyCd.getValue() //
                                        , wmsInbdSoHdrTMsg.whCd.getValue() //
                                        , wmsInbdSoHdrTMsg.wmsSqNum.getValue()) });
                        return NLGM0046E;
                    }

                } else {

                    if (ZYPCommonFunc.hasValue(wmsInbdSoHdrTMsg.wmsCancDtTmTs.getValue())) {

                        outputErr(NLGM0053E, new String[] {inbdOrdNum, wmsInbdSoHdrTMsg.getTableName(), COL_WMS_CANC_DT_TM_TS //
                                , wmsInbdSoHdrTMsg.wmsCancDtTmTs.getValue() });

                    } else {

                        outputErr(NLGM0053E, new String[] {inbdOrdNum, wmsInbdSoHdrTMsg.getTableName(), COL_SHIP_DT_TM_TS //
                                , wmsInbdSoHdrTMsg.shipDtTmTs.getValue() });
                    }

                    return NLGM0053E;
                }
            }

        } else {

            // For not in(Order Type item Change(R) and stock status change(S)),
            // to update the close date, of WMS_INBD_PO_HDR.
            // If it has been already closed, it is an error.
            queryParam.put("wmsPoId", inbdOrdNum);
            List<WMS_INBD_PO_HDRTMsg> poHdrList = (List<WMS_INBD_PO_HDRTMsg>) this.ssmBatchClient.queryObjectList("getWmsInbdPoHdr", queryParam);

            if (!poHdrList.isEmpty()) {

                WMS_INBD_PO_HDRTMsg wmsInbdPoHdrTMsg = (WMS_INBD_PO_HDRTMsg) poHdrList.get(0);

                if (!ZYPCommonFunc.hasValue(wmsInbdPoHdrTMsg.wmsCloDtTmTs.getValue())) {

                    ZYPEZDItemValueSetter.setValue(wmsInbdPoHdrTMsg.wmsCloDtTmTs, wmsTrxDtTmTs);
                    EZDTBLAccessor.updateSelectionField(wmsInbdPoHdrTMsg, new String[] {KEY_WMS_CLO_DT_TM_TS });

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdPoHdrTMsg.getReturnCode())) {

                        outputErr(NLGM0046E, new String[] {wmsInbdPoHdrTMsg.getTableName() //
                                , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WH_CD, COL_WMS_SQ_NUM) //
                                , NLXCMsgHelper.toListedString(wmsInbdPoHdrTMsg.glblCmpyCd.getValue() //
                                        , wmsInbdPoHdrTMsg.whCd.getValue() //
                                        , wmsInbdPoHdrTMsg.wmsSqNum.getValue()) });
                        return NLGM0046E;
                    }

                } else {

                    outputErr(NLGM0053E, new String[] {inbdOrdNum, wmsInbdPoHdrTMsg.getTableName(), COL_WMS_CLO_DT_TM_TS //
                            , wmsInbdPoHdrTMsg.wmsCloDtTmTs.getValue() });
                    return NLGM0053E;
                }
            }
        }

        return null;
    }

    // Start S21WDS NA Initial Upd
    /**
     * getRtlWhMap
     * @param wmsInbdTrxTMsg WMS_INBD_TRXTMsg
     * @param isPoReceipt boolean
     * @return Map<String, String>
     */
    private Map<String, String> getRtlWhMap(WMS_INBD_TRXTMsg wmsInbdTrxTMsg, boolean isPoReceipt) {

        Map<String, String> resultMap = new HashMap<String, String>();

        String inbdOrdTpCd = wmsInbdTrxTMsg.inbdOrdTpCd.getValue();
        String inbdOrdNum = wmsInbdTrxTMsg.inbdOrdNum.getValue();

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("whCd", wmsInbdTrxTMsg.whCd.getValue());
// QC#14505 
//        if (WMS_ORD_TP.INBOUND_ITEM_CHANGE.equals(inbdOrdTpCd) || WMS_ORD_TP.INBOUND_STOCK_STATUS_CHANGE.equals(inbdOrdTpCd) || WMS_ORD_TP.INBOUND_ITEM_CHANGE_FOR_REMAN.equals(inbdOrdTpCd) || WMS_ORD_TP.INBOUND_PACKAGE_CODE_CHANGE.equals(inbdOrdTpCd)) {
        // QC#17981 Mod.
//        if (WMS_ORD_TP.INBOUND_ITEM_CHANGE.equals(inbdOrdTpCd) || WMS_ORD_TP.INBOUND_STOCK_STATUS_CHANGE.equals(inbdOrdTpCd) || WMS_ORD_TP.INBOUND_ITEM_CHANGE_FOR_REMAN.equals(inbdOrdTpCd)) {
        if (Arrays.asList(getRtlWhFromSoHdrWmsOrdTp).contains(inbdOrdTpCd)) {

            resultMap = getRtlWhMapFromSoHdr(wmsInbdTrxTMsg, isPoReceipt, queryParam);

//            // For Order Type item Change(R) or stock status change(S),
//            queryParam.put("wmsSoId", inbdOrdNum);
//            BigDecimal wmsSqNum = (BigDecimal) this.ssmBatchClient.queryObject("getWmsInbdSoHdrMaxSqNum", queryParam);
//
//            if (wmsSqNum != null) {
//                WMS_INBD_SO_HDRTMsg inMsg = new WMS_INBD_SO_HDRTMsg();
//                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, wmsInbdTrxTMsg.glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(inMsg.whCd, wmsInbdTrxTMsg.whCd);
//                ZYPEZDItemValueSetter.setValue(inMsg.wmsSoId, wmsInbdTrxTMsg.inbdOrdNum);
//                ZYPEZDItemValueSetter.setValue(inMsg.wmsSqNum, wmsSqNum);
//                WMS_INBD_SO_HDRTMsg outMsg = (WMS_INBD_SO_HDRTMsg) EZDTBLAccessor.findByKey(inMsg);
//                if (outMsg != null) {
//                    resultMap.put(KEY_RTL_WH_CD, outMsg.rtlWhCd.getValue());
//                }
//
//                if (isPoReceipt) {
//                    WMS_INBD_SO_DTLTMsg inDtlMsg = new WMS_INBD_SO_DTLTMsg();
//                    ZYPEZDItemValueSetter.setValue(inDtlMsg.glblCmpyCd, wmsInbdTrxTMsg.glblCmpyCd);
//                    ZYPEZDItemValueSetter.setValue(inDtlMsg.whCd, wmsInbdTrxTMsg.whCd);
//                    ZYPEZDItemValueSetter.setValue(inDtlMsg.wmsSoId, wmsInbdTrxTMsg.inbdOrdNum);
//                    ZYPEZDItemValueSetter.setValue(inDtlMsg.wmsSqNum, wmsSqNum);
//                    ZYPEZDItemValueSetter.setValue(inDtlMsg.wmsLineNum, wmsInbdTrxTMsg.inbdOrdLineNum);
//                    WMS_INBD_SO_DTLTMsg outDtlMsg = (WMS_INBD_SO_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);
//                    if (outDtlMsg != null) {
//                        resultMap.put(KEY_RTL_WH_CD, outDtlMsg.rtlWhCd.getValue());
//                        resultMap.put(KEY_RTL_SWH_CD, outDtlMsg.rtlSwhCd.getValue());
//                    }
//                }
//            }

        } else {

            // For not in(Order Type item Change(R) and stock status change(S)),
            queryParam.put("wmsPoId", inbdOrdNum);
            BigDecimal wmsSqNum = (BigDecimal) this.ssmBatchClient.queryObject("getWmsInbdPoHdrMaxSqNum", queryParam);

            if (wmsSqNum != null) {

                WMS_INBD_PO_HDRTMsg wmsInbdPoHdrTMsg = new WMS_INBD_PO_HDRTMsg();
                ZYPEZDItemValueSetter.setValue(wmsInbdPoHdrTMsg.glblCmpyCd, wmsInbdTrxTMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(wmsInbdPoHdrTMsg.whCd, wmsInbdTrxTMsg.whCd);
                ZYPEZDItemValueSetter.setValue(wmsInbdPoHdrTMsg.wmsPoId, wmsInbdTrxTMsg.inbdOrdNum);
                ZYPEZDItemValueSetter.setValue(wmsInbdPoHdrTMsg.wmsSqNum, wmsSqNum);
                wmsInbdPoHdrTMsg = (WMS_INBD_PO_HDRTMsg) EZDTBLAccessor.findByKey(wmsInbdPoHdrTMsg);

                if (wmsInbdPoHdrTMsg != null) {

                    resultMap.put("rwsNum", wmsInbdPoHdrTMsg.rwsNum.getValue());
                    resultMap.put("RTL_WH_CD", wmsInbdPoHdrTMsg.rtlWhCd.getValue());
                }

                if (isPoReceipt) {

                    WMS_INBD_PO_DTLTMsg wmsInbdPoDtlTMsg = new WMS_INBD_PO_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(wmsInbdPoDtlTMsg.glblCmpyCd, wmsInbdTrxTMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(wmsInbdPoDtlTMsg.whCd, wmsInbdTrxTMsg.whCd);
                    ZYPEZDItemValueSetter.setValue(wmsInbdPoDtlTMsg.wmsPoId, wmsInbdTrxTMsg.inbdOrdNum);
                    ZYPEZDItemValueSetter.setValue(wmsInbdPoDtlTMsg.wmsSqNum, wmsSqNum);
                    ZYPEZDItemValueSetter.setValue(wmsInbdPoDtlTMsg.wmsLineNum, wmsInbdTrxTMsg.inbdOrdLineNum);
                    wmsInbdPoDtlTMsg = (WMS_INBD_PO_DTLTMsg) EZDTBLAccessor.findByKey(wmsInbdPoDtlTMsg);

                    if (wmsInbdPoDtlTMsg != null) {

                        resultMap.put("RTL_WH_CD", wmsInbdPoDtlTMsg.rtlWhCd.getValue());
                        resultMap.put("RTL_SWH_CD", wmsInbdPoDtlTMsg.rtlSwhCd.getValue());
                    }
                }
            }

            // QC#17981 Mod.
//            if(!ZYPCommonFunc.hasValue(resultMap.get(KEY_RWS_NUM)) && (WMS_ORD_TP.INBOUND_PACKAGE_CODE_CHANGE.equals(inbdOrdTpCd))) {
            if (!ZYPCommonFunc.hasValue(resultMap.get("rwsNum")) && (Arrays.asList(getRtlWhFromPccWmsOrdTp).contains(inbdOrdTpCd))) {

                resultMap = getRtlWhMapFromSoHdr(wmsInbdTrxTMsg, isPoReceipt, queryParam);

            } else if (!ZYPCommonFunc.hasValue(resultMap.get("rwsNum")) && WH_GP_CD_3_THIRD_PARTY_WH.equals(whGpCd))   {
                // QC#26824
                resultMap = getRtlWhMapFromSoHdr(wmsInbdTrxTMsg, isPoReceipt, queryParam);

            }
        }

        return resultMap;

    }
    // End S21WDS NA Initial Upd

    /**
     * getRtlWhMapFromSoHdr
     * @param wmsInbdTrxTMsg WMS_INBD_TRXTMsg
     * @param isPoReceipt boolean
     * @param queryParam Map<String, Object>
     * @return resultMap
     */
    private Map<String, String> getRtlWhMapFromSoHdr(WMS_INBD_TRXTMsg wmsInbdTrxTMsg, boolean isPoReceipt, Map<String, Object> queryParam) {

        Map<String, String> resultMap = new HashMap<String, String>();

        queryParam.put("wmsSoId", wmsInbdTrxTMsg.inbdOrdNum.getValue());
        BigDecimal wmsSqNum = (BigDecimal) this.ssmBatchClient.queryObject("getWmsInbdSoHdrMaxSqNum", queryParam);

        if (wmsSqNum != null) {

            WMS_INBD_SO_HDRTMsg wmsInbdSoHdrTMsg = new WMS_INBD_SO_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(wmsInbdSoHdrTMsg.glblCmpyCd, wmsInbdTrxTMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdSoHdrTMsg.whCd, wmsInbdTrxTMsg.whCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdSoHdrTMsg.wmsSoId, wmsInbdTrxTMsg.inbdOrdNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdSoHdrTMsg.wmsSqNum, wmsSqNum);
            wmsInbdSoHdrTMsg = (WMS_INBD_SO_HDRTMsg) EZDTBLAccessor.findByKey(wmsInbdSoHdrTMsg);

            if (wmsInbdSoHdrTMsg != null) {

                resultMap.put("RTL_WH_CD", wmsInbdSoHdrTMsg.rtlWhCd.getValue());

                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", wmsInbdTrxTMsg.glblCmpyCd.getValue());
                ssmParam.put("rwsRefNum", wmsInbdTrxTMsg.inbdOrdNum.getValue());
                ssmParam.put("whCd", wmsInbdSoHdrTMsg.rtlWhCd.getValue());
                List<Map<String, Object>> rwsHdrList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRwsNumBySoNum", ssmParam);

                if (rwsHdrList != null && rwsHdrList.size() > 0) {

                    resultMap.put("rwsNum", (String) rwsHdrList.get(0).get("RWS_NUM"));
                }
            }

            if (isPoReceipt) {

                WMS_INBD_SO_DTLTMsg wmsInbdSoDtlTMsg = new WMS_INBD_SO_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(wmsInbdSoDtlTMsg.glblCmpyCd, wmsInbdTrxTMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(wmsInbdSoDtlTMsg.whCd, wmsInbdTrxTMsg.whCd);
                ZYPEZDItemValueSetter.setValue(wmsInbdSoDtlTMsg.wmsSoId, wmsInbdTrxTMsg.inbdOrdNum);
                ZYPEZDItemValueSetter.setValue(wmsInbdSoDtlTMsg.wmsSqNum, wmsSqNum);
                ZYPEZDItemValueSetter.setValue(wmsInbdSoDtlTMsg.wmsLineNum, wmsInbdTrxTMsg.inbdOrdLineNum);
                wmsInbdSoDtlTMsg = (WMS_INBD_SO_DTLTMsg) EZDTBLAccessor.findByKey(wmsInbdSoDtlTMsg);

                if (wmsInbdSoDtlTMsg != null) {

                    resultMap.put("RTL_WH_CD", wmsInbdSoDtlTMsg.rtlWhCd.getValue());
                    resultMap.put("RTL_SWH_CD", wmsInbdSoDtlTMsg.rtlSwhCd.getValue());
                }
            }
        }

        return resultMap;
    }

    /**
     * Add Error ID and Message
     * @param msgId String
     * @param msgParam String[]
     */
    private void outputErr(String msgId, String[] msgParam) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        // START 2019/11/05 T.Ogura [QC#54080,ADD]
        if (errMsgList.contains(mailParam) ) {
            return;
        }
        // END   2019/11/05 T.Ogura [QC#54080,ADD]
        errMsgList.add(mailParam);
        S21InfoLogOutput.println(msgId, msgParam);
    }

    /**
     * checkSerNumDuplicate
     * @param wmsInbdTrxTMsg WMS_INBD_TRXTMsg
     * @param rtlWhCd String
     * @param rwsNum String
     * @return String Error Message ID
     */
    private String checkSerNumDuplicate(WMS_INBD_TRXTMsg wmsInbdTrxTMsg, String rtlWhCd, String rwsNum) {

        String errMsg = checkSerialValidationAPI(wmsInbdTrxTMsg, rtlWhCd, rwsNum);

        if (ZYPCommonFunc.hasValue(errMsg)) {

            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.errMsgCd, errMsg);
            dupSerTrxList.add(wmsInbdTrxTMsg);
            return errMsg;
        }

        if (!checkPutAwaySerWrkSerNum(wmsInbdTrxTMsg)) {

            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.errMsgCd, NLGM0082E);
            dupSerTrxList.add(wmsInbdTrxTMsg);
            return NLGM0082E;
        }

        List<String> serNumList = mdseSerNumList.get(wmsInbdTrxTMsg.wmsMdseCd.getValue());

        if (serNumList != null && serNumList.indexOf(wmsInbdTrxTMsg.serNum.getValue()) >= 0) {

            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.errMsgCd, NLGM0082E);
            dupSerTrxList.add(wmsInbdTrxTMsg);
            return NLGM0082E;
        }

        return errMsg;
    }

    /**
     * checkSerialValidationAPI
     * @param wmsInbdTrxTMsg WMS_INBD_TRXTMsg
     * @param rtlWhCd String
     * @param rwsNum String
     * @return String Error Message ID
     */
    private String checkSerialValidationAPI(WMS_INBD_TRXTMsg wmsInbdTrxTMsg, String rtlWhCd, String rwsNum) {

        RWS_HDRTMsg rwsHdrTMsg = new RWS_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.rwsNum, rwsNum);
        rwsHdrTMsg = (RWS_HDRTMsg) EZDTBLAccessor.findByKey(rwsHdrTMsg);

        String rwsRefNum = "";

        if (rwsHdrTMsg != null) {

            rwsRefNum = rwsHdrTMsg.rwsRefNum.getValue();
        }

        NLZC403001PMsg param = new NLZC403001PMsg();
        ZYPEZDItemValueSetter.setValue(param.xxModeCd, NLZC403001Constant.MODE_INBOUND);
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.mdseCd, wmsInbdTrxTMsg.wmsMdseCd);
        ZYPEZDItemValueSetter.setValue(param.serNum, wmsInbdTrxTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(param.whCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(param.rwsRefNum, rwsRefNum);

        // Execute
        NLZC403001 api = new NLZC403001();
        api.execute(param, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(param)) {

            // First Error Message Only.
            return param.xxMsgIdList.no(0).xxMsgId.getValue();
        }

        return "";
    }

    /**
     * checkPutAwaySerWrkSerNum
     * @param wmsInbdTrxTMsg wmsInbdTrxTMsg
     * @return boolean (true:exist /false: not)
     */
    private boolean checkPutAwaySerWrkSerNum(WMS_INBD_TRXTMsg wmsInbdTrxTMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", wmsInbdTrxTMsg.glblCmpyCd.getValue());
        ssmParam.put("mdseCd", wmsInbdTrxTMsg.wmsMdseCd.getValue());
        ssmParam.put("serNum", wmsInbdTrxTMsg.serNum.getValue());
        ssmParam.put("procStsInCompleted", PROC_STS.IN_COMPLETED);
        ssmParam.put("procStsCompleated", PROC_STS.COMPLEATED);
        ssmParam.put("svcMachProcStsInCompleted", SVC_MACH_PROC_STS.IN_COMPLETED);
        ssmParam.put("svcMachProcStsError", SVC_MACH_PROC_STS.ERROR);

        BigDecimal serCnt = (BigDecimal) this.ssmBatchClient.queryObject("getPutAwaySerCnt", ssmParam);

        if (serCnt != null && BigDecimal.ZERO.compareTo(serCnt) < 0) {

            return false;
        }

        return true;
    }

    /**
     * sendSerialDuplicateErrMail.
     */
    private void sendSerialDuplicateErrMail() {

        // address to Retail WH List
        Map<String, List<WMS_INBD_TRXTMsg>> retailWhMap = new HashMap<String, List<WMS_INBD_TRXTMsg>>();

        // address to WMS WH List
        Map<String, List<WMS_INBD_TRXTMsg>> wmsWhMap = new HashMap<String, List<WMS_INBD_TRXTMsg>>();

        // address to default List
        //List<WMS_INBD_TRXTMsg> defalutList = new ArrayList<WMS_INBD_TRXTMsg>();
        Map<String, List<WMS_INBD_TRXTMsg>> defalutMap = new HashMap<String, List<WMS_INBD_TRXTMsg>>();

        S21MailGroup groupToDefault = null;

        for (WMS_INBD_TRXTMsg dupSerWmsInbdTrx : dupSerTrxList) {

            String wmsWhCd = dupSerWmsInbdTrx.whCd.getValue();
            Map<String, String> rtlWhMap = getRtlWhMap(dupSerWmsInbdTrx, true);
            String rtlWhCd = rtlWhMap.get("RTL_WH_CD");

            // Retail WH List exists check
            if (retailWhMap.containsKey(rtlWhCd)) {

                setDupSerWmsInbdTrxList(retailWhMap, rtlWhCd, dupSerWmsInbdTrx);
                continue;

            // WMS WH List exists check
            } else if (wmsWhMap.containsKey(wmsWhCd)) {

                setDupSerWmsInbdTrxList(wmsWhMap, wmsWhCd, dupSerWmsInbdTrx);
                continue;

            } else if (defalutMap.containsKey(wmsWhCd)) {

                setDupSerWmsInbdTrxList(defalutMap, wmsWhCd, dupSerWmsInbdTrx);
                continue;
            }

            // Get To Mail Address(RWH)
            S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_RWH);
            groupTo.setMailKey1(rtlWhCd);
            List<S21MailAddress> addrToList = groupTo.getMailAddress();

            if (addrToList != null && !addrToList.isEmpty()) {

                setDupSerWmsInbdTrxList(retailWhMap, rtlWhCd, dupSerWmsInbdTrx);
                continue;
            }

            // Get To Mail Address(WMS_WH)
            groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_WMS_WH);
            groupTo.setMailKey1(wmsWhCd);
            addrToList = groupTo.getMailAddress();

            if (addrToList != null && !addrToList.isEmpty()) {

                setDupSerWmsInbdTrxList(wmsWhMap, wmsWhCd, dupSerWmsInbdTrx);
                continue;
            }

            // Get To Mail Address(default)
            if (groupToDefault == null) {

                groupToDefault = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_DEF);
                groupToDefault.setMailKey1(MAIL_KEY_TO);
                addrToList = groupToDefault.getMailAddress();

                if (addrToList == null || addrToList.isEmpty()) {

                    throw new S21AbendException(NPAM0063E, new String[] {MAIL_GROUP_ID_DEF, MAIL_KEY_TO});
                }
            }

            setDupSerWmsInbdTrxList(defalutMap, wmsWhCd, dupSerWmsInbdTrx);
        }

        // Send Retail Wh Error Mail
        if (!retailWhMap.isEmpty()) {

            sendSerialDuplicateErrMailToRetailWh(retailWhMap);
        }

        // Send WMS Wh Error Mail
        if (!wmsWhMap.isEmpty()) {

            sendSerialDuplicateErrMailToWmsWh(wmsWhMap);
        }

        // Send Default Error Mail
        if (!defalutMap.isEmpty()) {

            sendSerialDuplicateErrMailToDefault(defalutMap);
        }
    }

    /**
     * setDupSerWmsInbdTrxList
     * @param dupSerMap Map<String, List<WMS_INBD_TRXTMsg>>
     * @param mapKey String
     * @param dupSerWmsInbdTrx WMS_INBD_TRXTMsg
     */
    private void setDupSerWmsInbdTrxList(Map<String, List<WMS_INBD_TRXTMsg>> dupSerMap, String mapKey, WMS_INBD_TRXTMsg dupSerWmsInbdTrx) {

        List<WMS_INBD_TRXTMsg> dupSerWmsInbdTrxList = dupSerMap.get(mapKey);

        if (dupSerWmsInbdTrxList == null) {

            dupSerWmsInbdTrxList = new ArrayList<WMS_INBD_TRXTMsg>();
        }

        dupSerWmsInbdTrxList.add(dupSerWmsInbdTrx);
        dupSerMap.put(mapKey, dupSerWmsInbdTrxList);
    }

    /**
     * sendSerialDuplicateErrMailToRetailWh.
     * @param whMap Map<String, List<WMS_INBD_TRXTMsg>>
     */
    private void sendSerialDuplicateErrMailToRetailWh(Map<String, List<WMS_INBD_TRXTMsg>> whMap) {

        for (String whCd : whMap.keySet()) {

            List<WMS_INBD_TRXTMsg> serialErrTrxList = whMap.get(whCd);

            S21Mail mail = new S21Mail(glblCmpyCd);

            // Get from address
            S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
            List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

            if (!addrFromList.isEmpty()) {

                mail.setFromAddress(addrFromList.get(0));
            }

            // Get To Mail Address
            S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_RWH);
            groupTo.setMailKey1(whCd);
            List<S21MailAddress> addrToList = groupTo.getMailAddress();

            if (addrToList.isEmpty()) {

                throw new S21AbendException(NPAM0063E, new String[] {MAIL_GROUP_ID_RWH, whCd});
            }

            mail.setToAddressList(addrToList);

            // Get Template
            S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_SERIAL_ERR);

            if (!ZYPCommonFunc.hasValue(template.getSubject())) {

                throw new S21AbendException(NPAM0064E, new String[] {MAIL_TEMPLATE_ID_SERIAL_ERR});
            }

            StringBuilder sb = new StringBuilder();
            sb.append(DTL_MSG_HDR);

            for (WMS_INBD_TRXTMsg trx : serialErrTrxList) {

                sb.append(LINE_FEED_CODE);
                sb.append((whCd + BLANK_100).substring(0, WIDTH_WH_CD));
                sb.append(BLANK_2 + (trx.wmsMdseCd.getValue() + BLANK_100).substring(0, WIDTH_MDSE_CD));
                sb.append(BLANK_2 + (trx.serNum.getValue() + BLANK_100).substring(0, WIDTH_SERIAL));
                sb.append(BLANK_2 + (trx.inbdOrdNum.getValue() + BLANK_100).substring(0, WIDTH_INBD_ORD_NUM));
                String errMsg = S21MessageFunc.clspGetMessage(trx.errMsgCd.getValue());
                sb.append(BLANK_2 + errMsg);
            }

            template.setTemplateParameter(MT_WH_CD, whCd);
            template.setTemplateParameter(MT_DTL_MSG, sb.toString());
            mail.setMailTemplate(template);
            mail.postMail();

            commit();
        }
    }

    /**
     * sendSerialDuplicateErrMailToWmsWh.
     * @param whMap Map<String, List<WMS_INBD_TRXTMsg>>
     */
    private void sendSerialDuplicateErrMailToWmsWh(Map<String, List<WMS_INBD_TRXTMsg>> whMap) {

        for (String whCd : whMap.keySet()) {

            List<WMS_INBD_TRXTMsg> serialErrTrxList = whMap.get(whCd);

            S21Mail mail = new S21Mail(glblCmpyCd);

            // Get from address
            S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
            List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

            if (!addrFromList.isEmpty()) {

                mail.setFromAddress(addrFromList.get(0));
            }

            // Get To Mail Address
            S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_WMS_WH);
            groupTo.setMailKey1(whCd);
            List<S21MailAddress> addrToList = groupTo.getMailAddress();

            if (addrToList.isEmpty()) {

                throw new S21AbendException(NPAM0063E, new String[] {MAIL_GROUP_ID_WMS_WH, whCd});
            }

            mail.setToAddressList(addrToList);

            // Get Template
            S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_SERIAL_ERR);

            if (!ZYPCommonFunc.hasValue(template.getSubject())) {

                throw new S21AbendException(NPAM0064E, new String[] {MAIL_TEMPLATE_ID_SERIAL_ERR});
            }

            StringBuilder sb = new StringBuilder();
            sb.append(DTL_MSG_HDR);

            for (WMS_INBD_TRXTMsg trx : serialErrTrxList) {

                sb.append(LINE_FEED_CODE);
                sb.append((whCd + BLANK_100).substring(0, WIDTH_WH_CD));
                sb.append(BLANK_2 + (trx.wmsMdseCd.getValue() + BLANK_100).substring(0, WIDTH_MDSE_CD));
                sb.append(BLANK_2 + (trx.serNum.getValue() + BLANK_100).substring(0, WIDTH_SERIAL));
                sb.append(BLANK_2 + (trx.inbdOrdNum.getValue() + BLANK_100).substring(0, WIDTH_INBD_ORD_NUM));
                String errMsg = S21MessageFunc.clspGetMessage(trx.errMsgCd.getValue());
                sb.append(BLANK_2 + errMsg);
            }

            template.setTemplateParameter(MT_WH_CD, whCd);
            template.setTemplateParameter(MT_DTL_MSG, sb.toString());
            mail.setMailTemplate(template);
            mail.postMail();

            commit();
        }
    }

    /**
     * sendSerialDuplicateErrMailToDefault.
     * @param whMap Map<String, List<WMS_INBD_TRXTMsg>>
     */
    private void sendSerialDuplicateErrMailToDefault(Map<String, List<WMS_INBD_TRXTMsg>> whMap) {

        for (String whCd : whMap.keySet()) {

            List<WMS_INBD_TRXTMsg> serialErrTrxList = whMap.get(whCd);

            S21Mail mail = new S21Mail(glblCmpyCd);

            // Get from address
            S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
            List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

            if (!addrFromList.isEmpty()) {

                mail.setFromAddress(addrFromList.get(0));
            }

            // Get To Mail Address
            S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_DEF);
            groupTo.setMailKey1(MAIL_KEY_TO);
            List<S21MailAddress> addrToList = groupTo.getMailAddress();

            if (addrToList.isEmpty()) {

                throw new S21AbendException(NPAM0063E, new String[] {MAIL_GROUP_ID_DEF, whCd});
            }

            mail.setToAddressList(addrToList);

            // Get Template
            S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_SERIAL_ERR);

            if (!ZYPCommonFunc.hasValue(template.getSubject())) {

                throw new S21AbendException(NPAM0064E, new String[] {MAIL_TEMPLATE_ID_SERIAL_ERR});
            }

            StringBuilder sb = new StringBuilder();
            sb.append(DTL_MSG_HDR);

            for (WMS_INBD_TRXTMsg trx : serialErrTrxList) {

                sb.append(LINE_FEED_CODE);
                sb.append((whCd + BLANK_100).substring(0, WIDTH_WH_CD));
                sb.append(BLANK_2 + (trx.wmsMdseCd.getValue() + BLANK_100).substring(0, WIDTH_MDSE_CD));
                sb.append(BLANK_2 + (trx.serNum.getValue() + BLANK_100).substring(0, WIDTH_SERIAL));
                sb.append(BLANK_2 + (trx.inbdOrdNum.getValue() + BLANK_100).substring(0, WIDTH_INBD_ORD_NUM));
                String errMsg = S21MessageFunc.clspGetMessage(trx.errMsgCd.getValue());
                sb.append(BLANK_2 + errMsg);
            }

            template.setTemplateParameter(MT_WH_CD, whCd);
            template.setTemplateParameter(MT_DTL_MSG, sb.toString());
            mail.setMailTemplate(template);
            mail.postMail();

            commit();
        }
    }

    /**
     * getWmsOrdTpCdFromVarCharConst
     */
    private void getWmsOrdTpCdFromVarCharConst() {

        this.paramOrdTpCdStkStsChngWmsOrdTp = ZYPCodeDataUtil.getVarCharConstValue("NLGB0110_PRM_ORDTPCDSTKSTSCHNG", glblCmpyCd).split(",");
        this.paramItemChangeRemanWmsOrdTp = ZYPCodeDataUtil.getVarCharConstValue("NLGB0110_PRM_ITEM_CHNG_REMAN", glblCmpyCd).split(",");
        this.inboundOrdTpCdNotWmsOrdTp = ZYPCodeDataUtil.getVarCharConstValue("NLGB0110_INBD_ORD_TP_CD_NOT", glblCmpyCd).split(",");
        this.inboundOrdTpCdArrWmsOrdTp = ZYPCodeDataUtil.getVarCharConstValue("NLGB0110_INBD_ORD_TP_CD_ARR", glblCmpyCd).split(",");
        this.updateGetWmsInbdSoHdrWmsOrdTp = ZYPCodeDataUtil.getVarCharConstValue("NLGB0110_UPD_GETWMSINBDSOHDR", glblCmpyCd).split(",");
        this.getRtlWhFromSoHdrWmsOrdTp = ZYPCodeDataUtil.getVarCharConstValue("NLGB0110_GET_RTLWH_FROM_SOHDR", glblCmpyCd).split(",");
        this.getRtlWhFromPccWmsOrdTp = ZYPCodeDataUtil.getVarCharConstValue("NLGB0110_GET_RTLWH_FROM_PCC", glblCmpyCd).split(",");
    }

    /**
     * getErrCode
     * @param errCodeCueernt String
     * @param errCodeNew String
     * @return String
     */
    private String getErrCode(String errCodeCueernt, String errCodeNew) {

        if (ZYPCommonFunc.hasValue(errCodeCueernt)) {

            return errCodeCueernt;

        } else {

            return errCodeNew;
        }
    }
    
    /**
     * getShpgOrd
     * QC#21731 add method. 
     * @param soNum String
     * @return SHPG_ORDTMsg
     */
    private SHPG_ORDTMsg getShpgOrd(String soNum) {
        SHPG_ORDTMsg soTMsg = new SHPG_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(soTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(soTMsg.soNum, soNum);

        return (SHPG_ORDTMsg) EZDTBLAccessor.findByKey(soTMsg);
    }

    // START 2019/10/08 M.Naito [QC#52526,ADD]
    /**
     * isInclSkipList
     * @param msgId
     * @return
     */
    private boolean isInclSkipList(String msgId) {

        // Const「 ERR_MSG_EXCL_WMS_INBD_CHECK」SetUp Check
        if (errMsgList4ExclWmsInbdCheck.isEmpty()) {
            return false;
        }

        for (int i = 0; i < errMsgList4ExclWmsInbdCheck.size(); i++) {
            if (msgId.equals(errMsgList4ExclWmsInbdCheck.get(i))) {
                return true;
            }
        }
        return false;
    }
    // END 2019/10/08 M.Naito [QC#52526,ADD]

    // START 2022/04/28 J.Evangelista [QC#58807,ADD]
    /**
     * trmnIntgItem
     * @param rwsNum
     * @param wmsInbdTrxTMsg
     * @return boolean
     */
    private boolean trmnIntgItem(String rwsNum, WMS_INBD_TRXTMsg wmsInbdTrxTMsg) {

        BigDecimal svcConfigMstrPk = chkRtrnMachInRws(rwsNum);
        if (!ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
            return true; // No further processing necessary.
        }

        NSZC001001 api = new NSZC001001();

        List<Map<String, Object>> resultList = getIntgItemForTrmn(svcConfigMstrPk);
        for (Map<String, Object> result : resultList) {

            NSZC001001PMsg pMsg = new NSZC001001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.salesDate);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.RWS.code);
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, (BigDecimal) result.get("SVC_MACH_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.TERMINATED);
            ZYPEZDItemValueSetter.setValue(pMsg.effThruDt, this.salesDate);
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.GONE);
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachQty, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, svcConfigMstrPk);

            api.execute(pMsg, ONBATCH_TYPE.BATCH);
            if (S21ApiUtil.isXxMsgId(pMsg)) {

                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

                    String errorCode = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    if (ZYPCommonFunc.hasValue(errorCode)) {

                        this.termNormalFlg = false;

                        if (errorCode.endsWith("E")) {

                            outputErr(errorCode);
                            doErrProc(wmsInbdTrxTMsg, errorCode);
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * chkRtrnMachInRws
     * @param rwsNum
     * @return BigDecimal
     */
    private BigDecimal chkRtrnMachInRws(String rwsNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("rwsNum", rwsNum);
        params.put("svcMachTpCd", SVC_MACH_TP.MACHINE);
        params.put("rtrnRsnCd", new String[] {RTRN_RSN.SERVICE_EXCHANGE});

        return (BigDecimal) this.ssmBatchClient.queryObject("chkRtrnMachInRws", params);
    }

    /**
     * getIntgItemForTrmn
     * @param svcConfigMstrPk
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getIntgItemForTrmn(BigDecimal svcConfigMstrPk) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcConfigMstrPk", svcConfigMstrPk);
        params.put("invtyCtrlFlg", ZYPConstant.FLG_OFF_N);
        params.put("instlBaseCtrlFlg", ZYPConstant.FLG_ON_Y);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getIntgItemForTrmn", params);
    }
    // END 2022/04/28 J.Evangelista [QC#58807,ADD]

    // START 2022/08/30 R.Azucena [QC#60378 ADD]
    /**
     * getRtlSwhCd
     * @param inbdOrdNum String
     * @param rtlWhMapSwhCd String
     * @param sceOrdTpCd String
     * @return String
     */
    // START 2023/05/17 R.Azucena [QC#61483 MOD]
    // private String getRtlSwhCd(String inbdOrdNum, String rtlWhMapSwhCd) {
    //     SHPG_ORDTMsg soTMsg = getShpgOrd(inbdOrdNum);

    //     if (ZYPCommonFunc.hasValue(inbdOrdNum) && soTMsg != null && SCE_ORD_TP.ITEM_CHANGE.equals(soTMsg.sceOrdTpCd.getValue())) {
    private String getRtlSwhCd(String inbdOrdNum, String rtlWhMapSwhCd, String sceOrdTpCd) {
        if (ZYPCommonFunc.hasValue(inbdOrdNum) && SCE_ORD_TP.ITEM_CHANGE.equals(sceOrdTpCd)) {
    // END 2023/05/17 R.Azucena [QC#61483 MOD]
            if (ZYPCommonFunc.hasValue(rtlWhMapSwhCd)) {
                if (!itemChangeWhCdMap.containsKey(inbdOrdNum)) {
                    itemChangeWhCdMap.put(inbdOrdNum, rtlWhMapSwhCd);
                }
            } else {
                return itemChangeWhCdMap.get(inbdOrdNum);
            }
        }

        return rtlWhMapSwhCd;
    }
    // END 2022/08/30 R.Azucena [QC#60378 ADD]
}