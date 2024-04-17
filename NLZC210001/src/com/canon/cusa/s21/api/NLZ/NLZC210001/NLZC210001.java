package com.canon.cusa.s21.api.NLZ.NLZC210001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;

import business.parts.NLZC210001PMsg;
import business.parts.NLZC210002PMsg;
import business.parts.NLZC211001PMsg;
import business.db.SHPG_ORD_CONF_WRKTMsg;
import business.db.SHPG_ORD_CONF_DTL_WRKTMsg;
import business.db.SHIP_SER_NUM_WRKTMsg;

import com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_PROC_STS;

/**
 * <pre>
 * SO Confirmation from S21 DC
 *   Get SO Confirmation information from the parameter then store them into work tables.
 *   Call Update SO Confirmation API, and reflect them in business tables.
 * 
 * [Required Program]
 *   NLBC001001 Constant Component
 *   NLZC211001 Update SO Confirmation API
 *     NLZC003001 Inventory Order Update API
 *       NLZC001001 Inventory Allocation API
 *       NWZC107001 Allocation for non CPO API (Stub)
 *     NPZC002001 Work Order Update API
 *     NWZC003001 Shipping Plan Update API
 *       NWZC044001 Add Hold Info API
 *         NWZC004001 Validation Process Manager API (Stub)
 *     NLZC201001 PO Receiving API
 *       NLXC016001 Constant Component
 *       NLXC017001 Business Common Component
 *       NLXC025001 Log Output Component
 *     NLZC200001 RWS API
 *       NLXC016001 Constant Component
 *       NLXC017001 Business Common Component
 *       NLXC025001 Log Output Component
 *     NLZC002001 Inventory Update API
 *       NLCC001001 Constant Component
 * 
 * [Required Data]
 *   ML_GRP_ADDR (ML_GRP_ID=NLZC2100 is required)
 *   ML_USR_ADDR (ML_GRP_ID=NLZC2100 is required)
 *   ML_TMPL (ML_TMPL_ID=NLBB9999M001 is required)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/10/2009   Fujitsu         K.Ozasa         Create          N/A
 * 09/21/2010   CSAI            D.Fukaya        Update          N/A
 * 10/06/2010   CSAI            D.Fukaya        Update          N/A
 * 10/02/2015   CITS            T.Tokutomi      Update          N/A
 * 12/08/2015   CITS            T.Tokutomi      Update          QC#594
 * 12/11/2015   CSAI            Y.Imazu         Update          CSA SWH
 * 03/31/2016   CSAI            Y.Imazu         Update          QC#6379
 * 08/26/2016   CITS            T.Tokutomi      Update          QC#10302
 *</pre>
 */
public class NLZC210001 extends S21ApiCommonBase {

    /** Program ID for Log */
    private static final String PROGRAM_ID = " ## NLZC210001 ## ";

    /** Message Type: E */
    private static final String MSG_TYPE_ERROR = "E";

    /** Please fill out/select the required field. */
    public static final String NLZM2001E = "NLZM2001E";

    /** The data specified does not exist. */
    public static final String NLZM2005E = "NLZM2005E";

    /** The process abended. */
    public static final String NLZM2030E = "NLZM2030E";

    /** The value you entered is incorrect. */
    public static final String NLZM2031E = "NLZM2031E";

    /** The process abended. */
    public static final String NLZM2039E = "NLZM2039E";

    /** The (@) was (@) . ResultCount = (@) */
    public static final String ZZBM0009I = "ZZBM0009I";

    /** WH_CD does not exist. */
    public static final String NLBM1060E = "NLBM1060E";

    /** SHPG_ORD_CONF_WRK */
    private static final String SHPG_ORD_CONF_WRK = "SHPG_ORD_CONF_WRK";

    /** SHPG_ORD_CONF_DTL_WRK */
    private static final String SHPG_ORD_CONF_DTL_WRK = "SHPG_ORD_CONF_DTL_WRK";

    /** SHIP_SER_NUM_WRK */
    private static final String SHIP_SER_NUM_WRK = "SHIP_SER_NUM_WRK";

    /** Data Created */
    private static final String DATA_CREATED = "created";

    /** SRC_TP_CD: Received from S21 */
    private static final String SRC_TP_CD_FROM_S21 = "S";

    /** DB Access Component Return CD: Normal End */
    private static final String RETURN_CD_NORMAL_END = "0000";

    /** DB Access Component Return CD: No Data */
    private static final String RETURN_CD_NO_DATA = "2000";

    /** Line Feed Code */
    private static final String LINE_FEED_CODE = "\r\n";

    /** Mail Message Header */
    private static final String MAIL_MSG_HEADER = "SO#      Message#  Message";

    /** Mail Group ID */
    private static final String MAIL_GROUP_ID = "NLZC2100";

    /** Mail Template ID */
    private static final String MAIL_TEMPLATE_ID = "NLBB9999M001";

    /** Mail Key: From */
    private static final String MAIL_KEY_FROM = "From";

    /** Mail Key: To */
    private static final String MAIL_KEY_TO = "To";

    /** Mail Template Key: Batch ID */
    private static final String MAIL_TEMPLATE_KEY_ID = "batchId";

    /** Mail Template Key: Error Date */
    private static final String MAIL_TEMPLATE_KEY_DATE = "errDate";

    /** Mail Template Key: Message Information */
    private static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    /** Business ID */
    private static final String BUSINESS_ID = "NLZC2100";

    /** Date Time Pattern For Mail */
    private static final String DATE_TIME_PATTERN = "MM/dd/yyyy HH:mm:ss";

    /** Mode: so close */
    private static final String MODE_SO_CLOSE_ONLY = "SO_CLOSE_ONLY";

    /** Mode: split ship */
    private static final String MODE_SPLIT = "SPLIT";

    /** Mode: default */
    private static final String MODE_DEFAULT = "DEFAULT";

    /** process Mode */
    private static String mode = MODE_DEFAULT;

    /** Work Header Count */
    private int cntWrkHdr = 0;

    /** Work Detail Count */
    private int cntWrkDtl = 0;

    /** Serial# Work Count */
    private int cntWrkSer = 0;

    /**
     * S21SsmBatchClient instance.
     */
    private S21SsmBatchClient client = null;

    /**
     * Initialize
     */
    public NLZC210001() {
        super();
        // initializes SSM Client.
        this.client = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param paramDtl NLZC210001PMsg
     * @param paramSer NLZC210002PMsg
     * @param onBatchType Online/Batch Type
     */
    public void execute(final NLZC210001PMsg paramDtl, final NLZC210002PMsg paramSer, final ONBATCH_TYPE onBatchType) {

        List<NLZC210001PMsg> paramsDtl = Collections.synchronizedList(new ArrayList<NLZC210001PMsg>());
        paramsDtl.add(paramDtl);

        // Serial# parameter is not necessary
        List<NLZC210002PMsg> paramsSer;
        if (paramSer != null) {
            paramsSer = Collections.synchronizedList(new ArrayList<NLZC210002PMsg>());
            paramsSer.add(paramSer);
        } else {
            paramsSer = null;
        }

        execute(paramsDtl, paramsSer, onBatchType);

    }

    /**
     * execute
     * @param paramsDtl List of NLZC210001PMsg
     * @param paramsSer List of NLZC210002PMsg
     * @param onBatchType Online/Batch Type
     */
    public void execute(final List<NLZC210001PMsg> paramsDtl, final List<NLZC210002PMsg> paramsSer, final ONBATCH_TYPE onBatchType) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ execute ] start", this);

        doSoConfirmation(paramsDtl, paramsSer, onBatchType);
        outputProcessCnt();

        EZDDebugOutput.println(1, PROGRAM_ID + "[ execute ] end", this);
    }

    /**
     * Output Process Count
     */
    private void outputProcessCnt() {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ outputProcessCnt ] start", this);

        String[] str1 = {SHPG_ORD_CONF_WRK, DATA_CREATED, String.valueOf(this.cntWrkHdr) };
        String[] str2 = {SHPG_ORD_CONF_DTL_WRK, DATA_CREATED, String.valueOf(this.cntWrkDtl) };
        String[] str3 = {SHIP_SER_NUM_WRK, DATA_CREATED, String.valueOf(this.cntWrkSer) };

        S21InfoLogOutput.println(ZZBM0009I, str1);
        S21InfoLogOutput.println(ZZBM0009I, str2);
        S21InfoLogOutput.println(ZZBM0009I, str3);

        EZDDebugOutput.println(1, PROGRAM_ID + "[ outputProcessCnt ] end", this);
    }

    /**
     * Do SO Confirmation
     * @param paramsDtl List of NLZC210001PMsg
     * @param paramsSer List of NLZC210002PMsg
     * @param onBatchType Online/Batch Type
     */
    private void doSoConfirmation(final List<NLZC210001PMsg> paramsDtl, final List<NLZC210002PMsg> paramsSer, final ONBATCH_TYPE onBatchType) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ doSoConfirmation ] start", this);

        // Set Mode
        setMode(paramsDtl);

        // Check parameter
        boolean errFlg = checkInParameter(paramsDtl, paramsSer);
        if (errFlg) {
            return;
        }

        // Get transaction ID
        S21TransactionTableAccessor accessor = new S21TransactionTableAccessor();
        BigDecimal transactionId = accessor.getNextTransactionId();
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> transactionId:" + transactionId, this);

        // Create SO conf work data (header/detail)
        errFlg = createSoConfWrk(paramsDtl, transactionId);
        if (errFlg) {
            return;
        }

        // Create serial# work data
        createSerNumWrk(paramsSer, transactionId);

        // Call Update SO confirmation API
        String errMsgId = callUpdateSoConfirmation(paramsDtl, transactionId, onBatchType);

        // Update process status of work data
        updateWrkProcStatus(paramsDtl, transactionId, errMsgId);

        EZDDebugOutput.println(1, PROGRAM_ID + "[ doSoConfirmation ] end", this);
    }

    /**
     * setMode
     * @param paramsDtl List<NLZC210001PMsg>
     */
    private void setMode(List<NLZC210001PMsg> paramsDtl) {

        // get First Record.
        NLZC210001PMsg param = paramsDtl.get(0);

        // header so proc status empty
        if (!ZYPCommonFunc.hasValue(param.soProcStsCd)) {
            mode = MODE_SPLIT;
        } else if (!ZYPCommonFunc.hasValue(param.soSlpNum)//
                && !ZYPCommonFunc.hasValue(param.soProcStsCd_DT)//
                && !ZYPCommonFunc.hasValue(param.mdseCd)//
                && !ZYPCommonFunc.hasValue(param.fromStkStsCd)//
                && !ZYPCommonFunc.hasValue(param.shipQty)//
                && !ZYPCommonFunc.hasValue(param.bolNum)//
                && !ZYPCommonFunc.hasValue(param.vndCd)//
                && !ZYPCommonFunc.hasValue(param.proNum)//
                && !ZYPCommonFunc.hasValue(param.totFrtAmt)//
                && SO_PROC_STS.SHIP.equals(param.soProcStsCd.getValue())) {
            mode = MODE_SO_CLOSE_ONLY;
        } else {
            mode = MODE_DEFAULT;
        }
    }

    /**
     * Check Input Parameter
     * @param paramsDtl List of NLZC210001PMsg
     * @param paramsSer List of NLZC210002PMsg
     * @return Check Result
     */
    private boolean checkInParameter(final List<NLZC210001PMsg> paramsDtl, final List<NLZC210002PMsg> paramsSer) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ checkInParameter ] start", this);

        int cnt = 0;
        boolean errFlg = false;
        String glblCmpyCd = null;
        String soNum = null;
        /* 12/11/2015 CSAI Y.Imazu Del CSA SWH START */
        // String whCd = null;
        // String sceOrdTpCd = null;
        /* 12/11/2015 CSAI Y.Imazu Del CSA SWH END */
        String soProcStsCd = null;
        /* 12/11/2015 CSAI Y.Imazu Del CSA SWH START */
        // String shipDtTmTs = null;
        /* 12/11/2015 CSAI Y.Imazu Del CSA SWH END */
        /* 03/31/2016 CSAI Y.Imazu Add QC#6379 START */
        String dtlSoProcStsCd = null;
        /* 03/31/2016 CSAI Y.Imazu Add QC#6379 END */
        String glblCmpyCdWrk = null;
        String soNumWrk = null;
        /* 12/11/2015 CSAI Y.Imazu Del CSA SWH START */
        // String whCdWrk = null;
        // String sceOrdTpCdWrk = null;
        /* 12/11/2015 CSAI Y.Imazu Del CSA SWH END */
        String soProcStsCdWrk = null;
        /* 12/11/2015 CSAI Y.Imazu Del CSA SWH START */
        // String shipDtTmTsWrk = null;
        /* 12/11/2015 CSAI Y.Imazu Del CSA SWH END */
        /* 03/31/2016 CSAI Y.Imazu Add QC#6379 START */
        String dtlSoProcStsCdWrk = null;
        /* 03/31/2016 CSAI Y.Imazu Add QC#6379 END */

        for (NLZC210001PMsg paramDtl : paramsDtl) {

            S21ApiMessageMap msgMap = new S21ApiMessageMap(paramDtl);

            // Check SO conf detail work parameter
            errFlg = checkDtlParameter(paramDtl, msgMap);
            if (errFlg) {
                // Copy all messages to the parameter
                msgMap.flush();
                return errFlg;
            }

            // Get header items
            glblCmpyCd = paramDtl.glblCmpyCd.getValue();
            soNum = paramDtl.soNum.getValue();
            /* 12/11/2015 CSAI Y.Imazu Del CSA SWH START */
            // whCd = paramDtl.whCd.getValue();
            // sceOrdTpCd = paramDtl.sceOrdTpCd.getValue();
            /* 12/11/2015 CSAI Y.Imazu Del CSA SWH END */
            soProcStsCd = paramDtl.soProcStsCd.getValue();
            /* 12/11/2015 CSAI Y.Imazu Del CSA SWH START */
            // shipDtTmTs = paramDtl.shipDtTmTs.getValue();
            /* 12/11/2015 CSAI Y.Imazu Del CSA SWH END */
            /* 03/31/2016 CSAI Y.Imazu Add QC#6379 START */
            dtlSoProcStsCd = paramDtl.soProcStsCd_DT.getValue();
            /* 03/31/2016 CSAI Y.Imazu Add QC#6379 END */

            // Check if header items are as same as the previous
            // record
            if (cnt > 0) {
                if (MODE_SPLIT.equals(mode)) {
                    if (!glblCmpyCd.equals(glblCmpyCdWrk) //
                            || !soNum.equals(soNumWrk) //
                            /* 12/11/2015 CSAI Y.Imazu Del CSA SWH START */
                            // || !whCd.equals(whCdWrk) //
                            // || !sceOrdTpCd.equals(sceOrdTpCdWrk) //
                            // || !shipDtTmTs.equals(shipDtTmTsWrk) //
                            /* 12/11/2015 CSAI Y.Imazu Del CSA SWH END */
                            || ZYPCommonFunc.hasValue(paramDtl.soProcStsCd.getValue())) {
                        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Parameter is not right of split shipment - Header not same", this);
                        S21InfoLogOutput.println(NLZM2031E);
                        msgMap.addXxMsgId(NLZM2031E);
                        // Copy all messages to the parameter
                        msgMap.flush();
                        return true;
                    }
                } else if (MODE_SO_CLOSE_ONLY.equals(mode)) {
                    if (!glblCmpyCd.equals(glblCmpyCdWrk) //
                            || !soNum.equals(soNumWrk) //
                            /* 12/11/2015 CSAI Y.Imazu Del CSA SWH START */
                            // || !whCd.equals(whCdWrk) //
                            // || !sceOrdTpCd.equals(sceOrdTpCdWrk) //
                            // || !shipDtTmTs.equals(shipDtTmTsWrk) //
                            /* 12/11/2015 CSAI Y.Imazu Del CSA SWH END */
                    ) {
                        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Parameter is not right - Header not same", this);
                        S21InfoLogOutput.println(NLZM2031E);
                        msgMap.addXxMsgId(NLZM2031E);
                        // Copy all messages to the parameter
                        msgMap.flush();
                        return true;
                    }
                    // Detail need All Blank
                    if (ZYPCommonFunc.hasValue(paramDtl.soSlpNum)//
                            || ZYPCommonFunc.hasValue(paramDtl.soProcStsCd_DT)//
                            || ZYPCommonFunc.hasValue(paramDtl.mdseCd)//
                            || ZYPCommonFunc.hasValue(paramDtl.fromStkStsCd)//
                            || ZYPCommonFunc.hasValue(paramDtl.shipQty)//
                            || ZYPCommonFunc.hasValue(paramDtl.bolNum)//
                            || ZYPCommonFunc.hasValue(paramDtl.vndCd)//
                            || ZYPCommonFunc.hasValue(paramDtl.proNum)//
                            || ZYPCommonFunc.hasValue(paramDtl.totFrtAmt)) {
                        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Parameter is not right - SO Close Only is not need detail.", this);
                        S21InfoLogOutput.println(NLZM2031E);
                        msgMap.addXxMsgId(NLZM2031E);
                        msgMap.flush();
                        return true;
                    }

                } else {

                    /** Mode: Default **/
                    /* 12/11/2015 CSAI Y.Imazu Mod CSA SWH START */
                    //if (!glblCmpyCd.equals(glblCmpyCdWrk) || !soNum.equals(soNumWrk) || !whCd.equals(whCdWrk) || !sceOrdTpCd.equals(sceOrdTpCdWrk) || !soProcStsCd.equals(soProcStsCdWrk) || !shipDtTmTs.equals(shipDtTmTsWrk)) {
                    if (!glblCmpyCd.equals(glblCmpyCdWrk) || !soNum.equals(soNumWrk) || !soProcStsCd.equals(soProcStsCdWrk)) {
                    /* 12/11/2015 CSAI Y.Imazu Mod CSA SWH END */

                        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Parameter is not right - Header not same", this);
                        S21InfoLogOutput.println(NLZM2031E);
                        msgMap.addXxMsgId(NLZM2031E);
                        // Copy all messages to the parameter
                        msgMap.flush();
                        return true;
                    }
                }
            }

            // Store header items for next record check
            glblCmpyCdWrk = glblCmpyCd;
            soNumWrk = soNum;
            /* 12/11/2015 CSAI Y.Imazu Del CSA SWH START */
            // whCdWrk = whCd;
            // sceOrdTpCdWrk = sceOrdTpCd;
            /* 12/11/2015 CSAI Y.Imazu Del CSA SWH END */
            soProcStsCdWrk = soProcStsCd;
            /* 12/11/2015 CSAI Y.Imazu Del CSA SWH START */
            // shipDtTmTsWrk = shipDtTmTs;
            /* 12/11/2015 CSAI Y.Imazu Del CSA SWH END */
            /* 03/31/2016 CSAI Y.Imazu Add QC#6379 START */
            dtlSoProcStsCdWrk = dtlSoProcStsCd;
            /* 03/31/2016 CSAI Y.Imazu Add QC#6379 END */
            cnt++;
        }

        if (paramsSer != null) {

            /* 03/31/2016 CSAI Y.Imazu Add QC#6379 START */
            soProcStsCdWrk = dtlSoProcStsCdWrk;
            /* 03/31/2016 CSAI Y.Imazu Add QC#6379 END */

            for (NLZC210002PMsg paramSer : paramsSer) {

                S21ApiMessageMap msgMap = new S21ApiMessageMap(paramSer);

                // Check serial# parameter
                errFlg = checkSerParameter(paramSer, msgMap);
                if (errFlg) {
                    // Copy all messages to the parameter
                    msgMap.flush();
                    return errFlg;
                }

                // Get header items
                glblCmpyCd = paramSer.glblCmpyCd.getValue();
                soNum = paramSer.soNum.getValue();
                /* 12/11/2015 CSAI Y.Imazu Del CSA SWH START */
                // whCd = paramSer.whCd.getValue();
                // sceOrdTpCd = paramSer.sceOrdTpCd.getValue();
                /* 12/11/2015 CSAI Y.Imazu Del CSA SWH END */
                soProcStsCd = paramSer.soProcStsCd.getValue();
                /* 12/11/2015 CSAI Y.Imazu Del CSA SWH START */
                // shipDtTmTs = paramSer.shipDtTmTs.getValue();
                /* 12/11/2015 CSAI Y.Imazu Del CSA SWH END */

                // Check if header items are as same as the previous record
                /* 12/11/2015 CSAI Y.Imazu Mod CSA SWH START */
                // if (!glblCmpyCd.equals(glblCmpyCdWrk) || !soNum.equals(soNumWrk) || !whCd.equals(whCdWrk) || !sceOrdTpCd.equals(sceOrdTpCdWrk) || !soProcStsCd.equals(soProcStsCdWrk) || !shipDtTmTs.equals(shipDtTmTsWrk)) {
                if (!glblCmpyCd.equals(glblCmpyCdWrk) || !soNum.equals(soNumWrk) || !soProcStsCd.equals(soProcStsCdWrk)) {
                /* 12/11/2015 CSAI Y.Imazu Mod CSA SWH END */

                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Parameter is not right - Header not same", this);
                    S21InfoLogOutput.println(NLZM2031E);
                    msgMap.addXxMsgId(NLZM2031E);
                    // Copy all messages to the parameter
                    msgMap.flush();
                    return true;
                }

                // Store header items for next record check
                glblCmpyCdWrk = glblCmpyCd;
                soNumWrk = soNum;
                /* 12/11/2015 CSAI Y.Imazu Del CSA SWH START */
                // whCdWrk = whCd;
                // sceOrdTpCdWrk = sceOrdTpCd;
                /* 12/11/2015 CSAI Y.Imazu Del CSA SWH END */
                soProcStsCdWrk = soProcStsCd;
                /* 12/11/2015 CSAI Y.Imazu Del CSA SWH START */
                // shipDtTmTsWrk = shipDtTmTs;
                /* 12/11/2015 CSAI Y.Imazu Del CSA SWH END */
                cnt++;
            }
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ checkInParameter ] end", this);
        return errFlg;
    }

    /**
     * Check SO Conf Detail Work Parameter
     * @param param NLZC210001PMsg
     * @param msgMap Message Map
     * @return Check Result
     */
    private boolean checkDtlParameter(NLZC210001PMsg param, S21ApiMessageMap msgMap) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ checkDtlParameter ] start", this);

        boolean errFlg = false;

        // Common Check

        // GLBL_CMPY_CD
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - glblCmpyCd", this);
            S21InfoLogOutput.println(NLZM2001E);
            msgMap.addXxMsgId(NLZM2001E);
            return true;
        }

        // SO_NUM
        if (!ZYPCommonFunc.hasValue(param.soNum)) {
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - soNum", this);
            S21InfoLogOutput.println(NLZM2001E);
            msgMap.addXxMsgId(NLZM2001E);
            return true;
        }

        // WH_CD
        if (!ZYPCommonFunc.hasValue(param.whCd)) {
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - whCd", this);
            S21InfoLogOutput.println(NLZM2001E);
            msgMap.addXxMsgId(NLZM2001E);
            return true;
        }

        // SCE_ORD_TP_CD
        if (!ZYPCommonFunc.hasValue(param.sceOrdTpCd)) {
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - sceOrdTpCd", this);
            S21InfoLogOutput.println(NLZM2001E);
            msgMap.addXxMsgId(NLZM2001E);
            return true;
        }
        String sceOrdTpCd = param.sceOrdTpCd.getValue();

        if (MODE_SPLIT.equals(mode)) {
            /** Mode: Split **/

            // SHIP_DT_TM_TS
            if (!ZYPCommonFunc.hasValue(param.shipDtTmTs)) {
                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - shipDtTmTs", this);
                S21InfoLogOutput.println(NLZM2001E);
                msgMap.addXxMsgId(NLZM2001E);
                return true;
            }

            // SO_SLP_NUM
            if (!ZYPCommonFunc.hasValue(param.soSlpNum)) {
                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - soSlpNum", this);
                S21InfoLogOutput.println(NLZM2001E);
                msgMap.addXxMsgId(NLZM2001E);
                return true;
            }

            // SO_PROC_STS_CD (Detail items)
            if (!ZYPCommonFunc.hasValue(param.soProcStsCd_DT) //
                    || !SO_PROC_STS.SHIP.equals(param.soProcStsCd_DT.getValue())) {

                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - soProcStsCdDt", this);
                S21InfoLogOutput.println(NLZM2001E);
                msgMap.addXxMsgId(NLZM2001E);
                return true;
            }

            // MDSE_CD
            if (!ZYPCommonFunc.hasValue(param.mdseCd)) {
                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - mdseCd", this);
                S21InfoLogOutput.println(NLZM2001E);
                msgMap.addXxMsgId(NLZM2001E);
                return true;
            }

            // FROM_STK_STS_CD
            if (!ZYPCommonFunc.hasValue(param.fromStkStsCd)) {
                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - fromStkStsCd", this);
                S21InfoLogOutput.println(NLZM2001E);
                msgMap.addXxMsgId(NLZM2001E);
                return true;
            }

            // SHIP_QTY
            if (!ZYPCommonFunc.hasValue(param.shipQty)) {
                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - shipQty", this);
                S21InfoLogOutput.println(NLZM2001E);
                msgMap.addXxMsgId(NLZM2001E);
                return true;
            }

            if (SCE_ORD_TP.REGULAR.equals(sceOrdTpCd) || SCE_ORD_TP.TRIAL_FOR_SALE.equals(sceOrdTpCd) || SCE_ORD_TP.TRIAL_FOR_USE.equals(sceOrdTpCd) || SCE_ORD_TP.LOAN.equals(sceOrdTpCd) || SCE_ORD_TP.DIRECT_SALES.equals(sceOrdTpCd)) {

                // BOL_NUM OR (VND_CD AND PRO_NUM) is required
                if (!ZYPCommonFunc.hasValue(param.bolNum) && (!ZYPCommonFunc.hasValue(param.vndCd) || !ZYPCommonFunc.hasValue(param.proNum))) {
                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - bolNum / vndCd + proNum", this);
                    S21InfoLogOutput.println(NLZM2001E);
                    msgMap.addXxMsgId(NLZM2001E);
                    return true;
                }
            }
        } else if (MODE_SO_CLOSE_ONLY.equals(mode)) {
            /** Mode: SO Close Only **/

            // SHIP_DT_TM_TS
            if (!ZYPCommonFunc.hasValue(param.shipDtTmTs)) {
                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - shipDtTmTs", this);
                S21InfoLogOutput.println(NLZM2001E);
                msgMap.addXxMsgId(NLZM2001E);
                return true;
            }

        } else {
            /** Mode: Default **/

            // SO_PROC_STS_CD
            if (!ZYPCommonFunc.hasValue(param.soProcStsCd)) {
                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - soProcStsCd", this);
                S21InfoLogOutput.println(NLZM2001E);
                msgMap.addXxMsgId(NLZM2001E);
                return true;
            }
            String soProcStsCd = param.soProcStsCd.getValue();

            // SHIP_DT_TM_TS
            if (!ZYPCommonFunc.hasValue(param.shipDtTmTs)) {
                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - shipDtTmTs", this);
                S21InfoLogOutput.println(NLZM2001E);
                msgMap.addXxMsgId(NLZM2001E);
                return true;
            }

            // SO_SLP_NUM
            if (SO_PROC_STS.SHIP.equals(soProcStsCd) || SO_PROC_STS.LINE_VOID.equals(soProcStsCd)) {
                if (!ZYPCommonFunc.hasValue(param.soSlpNum)) {
                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - soSlpNum", this);
                    S21InfoLogOutput.println(NLZM2001E);
                    msgMap.addXxMsgId(NLZM2001E);
                    return true;
                }
            }

            // SO_PROC_STS_CD (Detail items)
            if (SO_PROC_STS.SHIP.equals(soProcStsCd) || SO_PROC_STS.LINE_VOID.equals(soProcStsCd)) {
                if (!ZYPCommonFunc.hasValue(param.soProcStsCd_DT)) {
                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - soProcStsCdDt", this);
                    S21InfoLogOutput.println(NLZM2001E);
                    msgMap.addXxMsgId(NLZM2001E);
                    return true;
                }
            }
            String soProcStsCdDtl = param.soProcStsCd_DT.getValue();

            // MDSE_CD
            if (SO_PROC_STS.SHIP.equals(soProcStsCd) || SO_PROC_STS.LINE_VOID.equals(soProcStsCd)) {
                if (!ZYPCommonFunc.hasValue(param.mdseCd)) {
                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - mdseCd", this);
                    S21InfoLogOutput.println(NLZM2001E);
                    msgMap.addXxMsgId(NLZM2001E);
                    return true;
                }
            }

            // FROM_STK_STS_CD
            if (SO_PROC_STS.SHIP.equals(soProcStsCdDtl)) {
                if (!ZYPCommonFunc.hasValue(param.fromStkStsCd)) {
                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - fromStkStsCd", this);
                    S21InfoLogOutput.println(NLZM2001E);
                    msgMap.addXxMsgId(NLZM2001E);
                    return true;
                }
            }

            // SHIP_QTY
            if (SO_PROC_STS.SHIP.equals(soProcStsCdDtl)) {
                if (!ZYPCommonFunc.hasValue(param.shipQty)) {
                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - shipQty", this);
                    S21InfoLogOutput.println(NLZM2001E);
                    msgMap.addXxMsgId(NLZM2001E);
                    return true;
                }
            }

            if (SO_PROC_STS.SHIP.equals(soProcStsCdDtl)) {
                if (SCE_ORD_TP.REGULAR.equals(sceOrdTpCd) || SCE_ORD_TP.TRIAL_FOR_SALE.equals(sceOrdTpCd) || SCE_ORD_TP.TRIAL_FOR_USE.equals(sceOrdTpCd) || SCE_ORD_TP.LOAN.equals(sceOrdTpCd) || SCE_ORD_TP.DIRECT_SALES.equals(sceOrdTpCd)) {

                    // BOL_NUM OR (VND_CD AND PRO_NUM) is required
                    if (!ZYPCommonFunc.hasValue(param.bolNum) && (!ZYPCommonFunc.hasValue(param.vndCd) || !ZYPCommonFunc.hasValue(param.proNum))) {
                        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - bolNum / vndCd + proNum", this);
                        S21InfoLogOutput.println(NLZM2001E);
                        msgMap.addXxMsgId(NLZM2001E);
                        return true;
                    }
                }
            }
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ checkDtlParameter ] end", this);
        return errFlg;
    }

    /**
     * Check Serial# Parameter
     * @param param NLZC210002PMsg
     * @param msgMap Message Map
     * @return Check Result
     */
    private boolean checkSerParameter(NLZC210002PMsg param, S21ApiMessageMap msgMap) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ checkSerParameter ] start", this);

        boolean errFlg = false;

        // GLBL_CMPY_CD
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - glblCmpyCd", this);
            S21InfoLogOutput.println(NLZM2001E);
            msgMap.addXxMsgId(NLZM2001E);
            return true;
        }

        // SO_NUM
        if (!ZYPCommonFunc.hasValue(param.soNum)) {
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - soNum", this);
            S21InfoLogOutput.println(NLZM2001E);
            msgMap.addXxMsgId(NLZM2001E);
            return true;
        }

        // WH_CD
        if (!ZYPCommonFunc.hasValue(param.whCd)) {
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - whCd", this);
            S21InfoLogOutput.println(NLZM2001E);
            msgMap.addXxMsgId(NLZM2001E);
            return true;
        }

        // SCE_ORD_TP_CD
        if (!ZYPCommonFunc.hasValue(param.sceOrdTpCd)) {
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - sceOrdTpCd", this);
            S21InfoLogOutput.println(NLZM2001E);
            msgMap.addXxMsgId(NLZM2001E);
            return true;
        }

        // SO_PROC_STS_CD
        if (!ZYPCommonFunc.hasValue(param.soProcStsCd)) {
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - soProcStsCd", this);
            S21InfoLogOutput.println(NLZM2001E);
            msgMap.addXxMsgId(NLZM2001E);
            return true;
        }

        // SHIP_DT_TM_TS
        if (!ZYPCommonFunc.hasValue(param.shipDtTmTs)) {
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - shipDtTmTs", this);
            S21InfoLogOutput.println(NLZM2001E);
            msgMap.addXxMsgId(NLZM2001E);
            return true;
        }

        // SO_SLP_NUM
        if (!ZYPCommonFunc.hasValue(param.soSlpNum)) {
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - soSlpNum", this);
            S21InfoLogOutput.println(NLZM2001E);
            msgMap.addXxMsgId(NLZM2001E);
            return true;
        }

        // The following serial related items need to be checked
        // whether SER_NUM_TAK_FLG is on or not.
        // If error occurs, return not Warning but Error since problem
        // occurs when SHIP_SER_NUM data is created after.

        // MDSE_CD
        if (!ZYPCommonFunc.hasValue(param.mdseCd)) {
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - mdseCd", this);
            S21InfoLogOutput.println(NLZM2001E);
            msgMap.addXxMsgId(NLZM2001E);
            return true;
        }

        // SER_NUM
        if (!ZYPCommonFunc.hasValue(param.serNum)) {
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - serNum", this);
            S21InfoLogOutput.println(NLZM2001E);
            msgMap.addXxMsgId(NLZM2001E);
            return true;
        }

        // SER_TAKE_TM_TS
        if (!ZYPCommonFunc.hasValue(param.serTakeDtTmTs)) {
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Required parameter is not set. - serTakeDtTmTs", this);
            S21InfoLogOutput.println(NLZM2001E);
            msgMap.addXxMsgId(NLZM2001E);
            return true;
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ checkSerParameter ] end", this);
        return errFlg;
    }

    /**
     * Create SO Conf Work Data (header/detail)
     * @param params List of NLZC211001PMsg
     * @param transactionId Transaction ID
     */
    private boolean createSoConfWrk(final List<NLZC210001PMsg> params, BigDecimal transactionId) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ createSoConfWrk ] start", this);

        int sqIdHdr = 1;
        int sqIdDtl = 1;

        if (MODE_SPLIT.equals(mode)) {
            /** Mode: Split **/
            // Create Detail. Don't create header.
            for (NLZC210001PMsg param : params) {
                // Create work detail
                createShpgOrdWrkDtl(param, transactionId, sqIdDtl);
                sqIdDtl++;
                cntWrkDtl++;
            }

        } else if (MODE_SO_CLOSE_ONLY.equals(mode)) {
            /** Mode: SO Close Only **/
            // Create Header. Don't create Detail.
            NLZC210001PMsg param = params.get(0);

            S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

            // get WH_CD and exist check
            String whcd = getWhCdFromSWH(param.glblCmpyCd.getValue(), param.whCd.getValue());

            if (whcd == null) {
                msgMap.addXxMsgId(NLBM1060E);
                msgMap.flush();
                return true;
            }

            // Create work header
            createShpgOrdWrkHdr(param, transactionId, sqIdHdr, whcd);
            cntWrkHdr++;

        } else {
            /** Mode: Default **/

            for (NLZC210001PMsg param : params) {

                // 1st record only
                if (cntWrkHdr == 0) {

                    S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

                    // get WH_CD and exist check
                    String whcd = getWhCdFromSWH(param.glblCmpyCd.getValue(), param.whCd.getValue());

                    if (whcd == null) {
                        msgMap.addXxMsgId(NLBM1060E);
                        msgMap.flush();
                        return true;
                    }

                    // Create work header
                    createShpgOrdWrkHdr(param, transactionId, sqIdHdr, whcd);
                    cntWrkHdr++;
                }

                // If not Order Void
                if (!SO_PROC_STS.ORDER_VOID.equals(param.soProcStsCd.getValue())) {

                    // Create work detail
                    createShpgOrdWrkDtl(param, transactionId, sqIdDtl);
                    sqIdDtl++;
                    cntWrkDtl++;
                }
            }
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ createSoConfWrk ] end", this);

        return false;
    }

    /**
     * createShpgOrdWrkHdr
     * @param param NLZC210001PMsg
     * @param transactionId BigDecimal
     * @param sqIdHdr int
     * @param whcd String
     */
    private void createShpgOrdWrkHdr(NLZC210001PMsg param, BigDecimal transactionId, int sqIdHdr, String whcd) {
        // Create work header
        SHPG_ORD_CONF_WRKTMsg inMsg = new SHPG_ORD_CONF_WRKTMsg();
        inMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        inMsg.soNum.setValue(param.soNum.getValue());
        inMsg.wrkTrxId.setValue(String.valueOf(transactionId));
        inMsg.sqId.setValue(String.valueOf(sqIdHdr));
        inMsg.procStsCd.setValue(PROC_STS.IN_COMPLETED);
        inMsg.errMsgCd.clear();
        inMsg.whCd.setValue(whcd);
        inMsg.sceOrdTpCd.setValue(param.sceOrdTpCd.getValue());
        inMsg.s80OrdTpCd.clear();
        inMsg.soProcStsCd.setValue(param.soProcStsCd.getValue());
        inMsg.soDataActTpCd.clear();
        inMsg.shipDtTmTs.setValue(param.shipDtTmTs.getValue());
        inMsg.totShipWt.setValue(new BigDecimal(0));
        inMsg.totFrtAmt.setValue(new BigDecimal(0));
        inMsg.srcTpCd.setValue(SRC_TP_CD_FROM_S21);
        S21ApiTBLAccessor.create(inMsg);
        if (!RETURN_CD_NORMAL_END.equals(inMsg.getReturnCode())) {
            // The process abended.
            throw new S21AbendException(NLZM2030E);
        }
    }

    /**
     * createShpgOrdWrkDtl
     * @param param NLZC210001PMsg
     * @param transactionId BigDecimal
     * @param sqIdDtl sqIdDtl
     */
    private void createShpgOrdWrkDtl(NLZC210001PMsg param, BigDecimal transactionId, int sqIdDtl) {
        // Create work detail
        SHPG_ORD_CONF_DTL_WRKTMsg inMsg = new SHPG_ORD_CONF_DTL_WRKTMsg();
        inMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        inMsg.soNum.setValue(param.soNum.getValue());
        inMsg.soSlpNum.setValue(param.soSlpNum.getValue());
        inMsg.wrkTrxId.setValue(String.valueOf(transactionId));
        inMsg.sqId.setValue(String.valueOf(sqIdDtl));
        inMsg.proNum.setValue(param.proNum.getValue());
        inMsg.vndCd.setValue(param.vndCd.getValue());
        inMsg.procStsCd.setValue(PROC_STS.IN_COMPLETED);
        inMsg.errMsgCd.clear();
        inMsg.mdseCd.setValue(param.mdseCd.getValue());
        inMsg.whCd.setValue(param.whCd.getValue());
        inMsg.fromStkStsCd.setValue(param.fromStkStsCd.getValue());
        inMsg.s80StkStsCd.clear();
        inMsg.bolNum.setValue(param.bolNum.getValue());
        inMsg.totShipWt.setValue(new BigDecimal(0));
        inMsg.totFrtAmt.setValue(param.totFrtAmt.getValue());
        inMsg.shipQty.setValue(param.shipQty.getValue());
        inMsg.soDataActTpCd.clear();
        inMsg.soProcStsCd.setValue(param.soProcStsCd_DT.getValue());
        inMsg.srcTpCd.setValue(SRC_TP_CD_FROM_S21);
        inMsg.ucc128Cd.clear();
        inMsg.shipDtTmTs.setValue(param.shipDtTmTs.getValue());
        /**
         * QC#10302
         */
        ZYPEZDItemValueSetter.setValue(inMsg.shpgSvcLvlCd, param.shpgSvcLvlCd);
        S21ApiTBLAccessor.create(inMsg);
        if (!RETURN_CD_NORMAL_END.equals(inMsg.getReturnCode())) {
            // The process abended.
            throw new S21AbendException(NLZM2030E);
        }
    }

    /**
     * Get WH_CD from swh table.
     * @param glblCmpyCd String
     * @param whCdAndSwhCd (whCd+swhCd) String
     * @return whCd
     */
    private String getWhCdFromSWH(String glblCmpyCd, String whCdAndSwhCd) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("glblCmpyCd", glblCmpyCd);
        queryParams.put("whCdAndSwhCd", whCdAndSwhCd);

        return (String) this.client.queryObject("getWhCdFromSWH", queryParams);
    }

    /**
     * Create Serial# Work Data
     * @param params List of NLZC211002PMsg
     * @param transactionId Transaction ID
     */
    private void createSerNumWrk(final List<NLZC210002PMsg> params, BigDecimal transactionId) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ createSerNumWrk ] start", this);

        int sqIdSer = 1;

        if (params != null) {
            for (NLZC210002PMsg param : params) {

                SHIP_SER_NUM_WRKTMsg inMsg = new SHIP_SER_NUM_WRKTMsg();
                inMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                inMsg.soNum.setValue(param.soNum.getValue());
                inMsg.wrkTrxId.setValue(String.valueOf(transactionId));
                inMsg.sqId.setValue(String.valueOf(sqIdSer));
                inMsg.procStsCd.setValue(PROC_STS.IN_COMPLETED);
                inMsg.errMsgCd.clear();
                inMsg.mdseCd.setValue(param.mdseCd.getValue());
                inMsg.whCd.setValue(param.whCd.getValue());
                inMsg.soSerId.clear();
                inMsg.serNum.setValue(param.serNum.getValue());
                inMsg.serTakeDtTmTs.setValue(param.serTakeDtTmTs.getValue());
                inMsg.soSlpNum.setValue(param.soSlpNum.getValue());
                // Need to set value for Serial Temp
                inMsg.serIntfcProcStsCd.setValue(PROC_STS.IN_COMPLETED);
                inMsg.serIntfcErrCd.clear();
                inMsg.srcTpCd.setValue(SRC_TP_CD_FROM_S21);
                S21ApiTBLAccessor.create(inMsg);
                if (!RETURN_CD_NORMAL_END.equals(inMsg.getReturnCode())) {
                    // The process abended.
                    throw new S21AbendException(NLZM2030E);
                }
                sqIdSer++;
                cntWrkSer++;
            }
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ createSerNumWrk ] end", this);
    }

    /**
     * Call Update SO Confirmation API
     * @param params List of NLZC210001PMsg
     * @param transactionId Transaction ID
     * @param onBatchType Online/Batch Type
     * @return Error Message ID (Return only one message even if
     * multiple messages are returned from the API)
     */
    private String callUpdateSoConfirmation(final List<NLZC210001PMsg> params, BigDecimal transactionId, final ONBATCH_TYPE onBatchType) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ callUpdateSoConfirmation ] start", this);

        String errMsgId = null;
        String message = null;
        StringBuilder messageInfo = null;

        // Get 1st record of list to get header items
        NLZC210001PMsg param = params.get(0);

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        // Set API parameter
        NLZC211001PMsg pmsg = new NLZC211001PMsg();
        pmsg.wrkTrxId.setValue(transactionId.toString());
        pmsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        pmsg.soNum.setValue(param.soNum.getValue());

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> wrkTrxId:" + transactionId.toString(), this);
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> glblCmpyCd:" + param.glblCmpyCd.getValue(), this);
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> soNum:" + param.soNum.getValue(), this);

        // Excecute API
        NLZC211001 api = new NLZC211001();
        api.execute(pmsg, onBatchType);

        // If any message ID is returned, copy it to message map
        // and create mail message information
        List<String> errList = S21ApiUtil.getXxMsgIdList(pmsg);
        if (!errList.isEmpty()) {

            for (String xxMsgId : errList) {

                EZDDebugOutput.println(1, PROGRAM_ID + "xxMsgId:" + xxMsgId, this);
                msgMap.addXxMsgId(xxMsgId);

                // Create mail message information
                message = S21MessageFunc.clspGetMessage(xxMsgId);
                if (messageInfo == null) {
                    messageInfo = new StringBuilder();
                    messageInfo.append(MAIL_MSG_HEADER);
                    messageInfo.append(LINE_FEED_CODE);
                }
                messageInfo.append(param.soNum.getValue());
                messageInfo.append(" ");
                messageInfo.append(message);
                messageInfo.append(LINE_FEED_CODE);
                if (xxMsgId.endsWith(MSG_TYPE_ERROR)) {
                    errMsgId = xxMsgId;
                }
            }
        }

        // Copy all messages to the parameter
        msgMap.flush();

        // Call mail component
        if (messageInfo != null) {
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> messageInfo:" + messageInfo.toString(), this);
            postMail(param.glblCmpyCd.getValue(), messageInfo.toString());
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ callUpdateSoConfirmation ] end", this);
        return errMsgId;
    }

    /**
     * Update Process Status of Work Data
     * @param params List of NLZC210001PMsg
     * @param transactionId Transaction ID
     * @param errMsgId Error Message ID
     */
    private void updateWrkProcStatus(final List<NLZC210001PMsg> params, BigDecimal transactionId, String errMsgId) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ updateWrkStatus ] start", this);

        int sqIdHdr = 1;

        // Get 1st record of list to get header items
        NLZC210001PMsg param = params.get(0);

        // [Header]

        // Search
        SHPG_ORD_CONF_WRKTMsg inMsgHdr = new SHPG_ORD_CONF_WRKTMsg();
        inMsgHdr.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        inMsgHdr.soNum.setValue(param.soNum.getValue());
        inMsgHdr.wrkTrxId.setValue(transactionId.toString());
        inMsgHdr.sqId.setValue(String.valueOf(sqIdHdr));
        SHPG_ORD_CONF_WRKTMsg outMsgHdr = (SHPG_ORD_CONF_WRKTMsg) S21ApiTBLAccessor.findByKey(inMsgHdr);

        if (outMsgHdr != null) {
            // Update
            if (errMsgId == null) {
                outMsgHdr.procStsCd.setValue(PROC_STS.COMPLEATED);
                outMsgHdr.errMsgCd.clear();
            } else {
                outMsgHdr.procStsCd.setValue(PROC_STS.ERROR);
                outMsgHdr.errMsgCd.setValue(errMsgId);
            }
            S21ApiTBLAccessor.update(outMsgHdr);
            if (!RETURN_CD_NORMAL_END.equals(outMsgHdr.getReturnCode())) {
                // The process abended.
                throw new S21AbendException(NLZM2030E);
            }
        }

        // [Detail]
        // SO Close Only don't create Detail. no update target.
        if (!MODE_SO_CLOSE_ONLY.equals(mode)) {
            // Update by partial key
            SHPG_ORD_CONF_DTL_WRKTMsg outMsgDtl = new SHPG_ORD_CONF_DTL_WRKTMsg();
            outMsgDtl.wrkTrxId.setValue(transactionId.toString());
            outMsgDtl.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            outMsgDtl.soNum.setValue(param.soNum.getValue());

            if (errMsgId == null) {
                outMsgDtl.procStsCd.setValue(PROC_STS.COMPLEATED);
                outMsgDtl.errMsgCd.clear();
            } else {
                outMsgDtl.procStsCd.setValue(PROC_STS.ERROR);
                outMsgDtl.errMsgCd.setValue(errMsgId);
            }
            int updateDtlCnt = S21ApiTBLAccessor.updateByPartialKey(outMsgDtl, new String[] {"procStsCd", "errMsgCd" });
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> cnt:" + updateDtlCnt, this);
            if (!RETURN_CD_NORMAL_END.equals(outMsgDtl.getReturnCode()) && !RETURN_CD_NO_DATA.equals(outMsgDtl.getReturnCode())) {
                // The process abended.
                throw new S21AbendException(NLZM2030E);
            }
        }

        // [Serial]

        // Update by partial key
        SHIP_SER_NUM_WRKTMsg outMsgSer = new SHIP_SER_NUM_WRKTMsg();
        outMsgSer.wrkTrxId.setValue(transactionId.toString());
        outMsgSer.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        outMsgSer.soNum.setValue(param.soNum.getValue());
        if (errMsgId == null) {
            outMsgSer.procStsCd.setValue(PROC_STS.COMPLEATED);
            outMsgSer.errMsgCd.clear();
        } else {
            outMsgSer.procStsCd.setValue(PROC_STS.ERROR);
            outMsgSer.errMsgCd.setValue(errMsgId);
        }
        int updateSerCnt = S21ApiTBLAccessor.updateByPartialKey(outMsgSer, new String[] {"procStsCd", "errMsgCd" });
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> cnt:" + updateSerCnt, this);
        if (!RETURN_CD_NORMAL_END.equals(outMsgSer.getReturnCode()) && !RETURN_CD_NO_DATA.equals(outMsgSer.getReturnCode())) {
            // The process abended.
            throw new S21AbendException(NLZM2030E);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ updateWrkStatus ] end", this);
    }

    /**
     * Call postMail
     * @param glblCmpyCd Global Company CD
     * @param messageInfo Message Information of Mail Body
     */
    private void postMail(String glblCmpyCd, String messageInfo) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ postMail ] start", this);

        // Create mail
        S21Mail mail = new S21Mail(glblCmpyCd);

        // Create group
        S21MailGroup group = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID);

        // Get from-address users of the group
        group.setMailKey1(MAIL_KEY_FROM);
        List<S21MailAddress> fromAddrList = group.getMailAddress();

        if (!fromAddrList.isEmpty()) {
            // Set from-address
            mail.setFromAddress(fromAddrList.get(0));
        }

        // Get to-address users of the group
        group.setMailKey1(MAIL_KEY_TO);
        List<S21MailAddress> toAddrList = group.getMailAddress();

        if (toAddrList.isEmpty()) {
            throw new S21AbendException(NLZM2039E);
        }
        // Set to-address
        mail.setToAddressList(toAddrList);

        // Get current system time
        String currentTime = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN);
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> currentTime:" + currentTime, this);

        // Create template
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);

        // Set template parameter
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_ID, BUSINESS_ID);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, currentTime);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, messageInfo);

        // Set template
        mail.setMailTemplate(template);

        // Post mail
        mail.postMail();

        EZDDebugOutput.println(1, PROGRAM_ID + "[ postMail ] end", this);
    }

}
