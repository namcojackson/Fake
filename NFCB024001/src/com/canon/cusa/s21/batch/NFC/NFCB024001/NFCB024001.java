/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB024001;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_EDI_SEND_BANKTMsg;
import business.db.AR_RCPT_IN_PROC_WRKTMsg;
import business.db.AR_RCPT_RCV_WRKTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.common.NFX.NFXC307001.NFCCurrencyConversion;
import com.canon.cusa.s21.common.NFX.NFXC307001.NFXC3070Bean;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCsvCmnMethod;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCDbConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CUST_ID_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_EDI_SEND_BANK;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NFCB024001 Receipt Data Check
 *
 * Date         Company       Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/17/2009   Canon         K.Usui          Create          N/A
 * 11/11/2009   Canon         K.Usui          Update          DefID-1570,1724
 * 11/19/2009   Canon         K.Usui          Update          DefID-1690,1708,1888,1997
 * 11/20/2009   Canon         K.Usui          Update          DefID-2005
 * 11/25/2009   Canon         K.Usui          Update          DefID-2101
 * 12/17/2009   Canon         K.Usui          Update          DefID-2698,2734
 * 12/30/2009   Canon         M.Moriyama      Update          DefID-2834
 * 01/07/2010   Canon         K.Usui          Update          DefID-2701,2706
 * 01/08/2010   Canon         K.Usui          Update          DefID-2646
 * 01/20/2010   Canon         K.Usui          Update          DefID-2701,2706,2804
 * 01/26/2010   Canon         K.Usui          Update          DefID-2734
 * 01/28/2010   Canon         M.Moriyama      Update          For TOC/PROD Code,For BAT_SEQ_NUM,DefID-2702
 * 03/01/2010   Canon         I.Kondo         Update          DefID-4190,4228,4233
 * 03/05/2010   Canon         H.Tokunaga      Update          DefID-4297 
 * 03/09/2010   Canon         K.Usui          Update          DELETE CHK_30_NUM CHECK
 * 03/10/2010   Canon         M.Moriyama      Update          DefID-4554
 * 03/11/2010   Canon         M.Moriyama      Update          DefID-4621, 4733
 * 03/18/2010   Canon         I.Kondo         Update          DefID-4898
 * 03/21/2010   Canon         M.Moriyama      Update          DefID-5063
 * 03/24/2010   Canon         M.Moriyama      Update          DefID-5143
 * 03/26/2010   Canon         I.Kondo         Update          DefID-4773
 * 04/07/2010   Canon         H.Tokunaga      Update          DefID-5410
 * 05/25/2010   Canon         S.Uehara        Update          DefID-6699  No.033
 * 06/02/2010   Canon         I.Kondo         Update          DefID-6836  No.070
 * 06/19/2010   Canon         K.Kimura        Update          DefID-7268  No.133
 * 07/20/2010   Canon         Y.Aikawa        Update          N/A
 * 07/29/2010   Canon         I.Kondo         Update          Merge.
 * 08/09/2010   Canon         I.Kondo         Update          Merge.
 * 08/19/2010   Canon         K.Kimura        Update          DefID-7339  No.339
 * 09/01/2010   Canon         D.Yoshida       Update          DefID-8113  No.365
 * 09/01/2010   Canon         K.Kimura        Update          DefID-8010  No.366
 * 10/12/2010   Canon         I.Kondo         Update          R2 -> R3 Merge.
 * 10/14/2010   Canon         K.Kimura        Update          DefID-8239  No.434
 * 10/19/2010   Canon         I.Kondo         Update          R2 -> R3 Merge.
 * 12/18/2010   Canon         K.Kimura        Update          M92
 * 01/11/2011   Canon         K.Kimura        Update          Emergency
 * 06/23/2011   Canon         K.Kimura        Update          DefID:2266
 * 09/12/2011   Canon         T.Tanaka        Update          add S21 Service & Repair ITG#361485,364467
 * 04/06/2015   Fujitsu       T.Yoshida       Update          For North America(CSA)
 * 10/06/2015   Fujitsu       T.Tanaka        Update          delete invConslTpCd
 * 12/16/2015   CSAI          K.Uramori       Update          modification and other
 * 07/08/2016   Fujitsu       S.Fujita        Update          QC#11520
 * 2016/07/25   Hitachi       J.Kim           Update          QC#9476
 * 2016/07/29   Hitachi       T.Tsuchida      Update          QC#12649
 * 2016/08/26   Fujitsu       S.Fujita        Update          QC#13844
 * 2016/11/30   Fujitsu       S.Fujita        Update          QC#16254
 * 2017/01/13   Fujitsu       T.Murai         Update          QC#16837
 * 2017/09/05   Hitachi       T.Tsuchida      Update          QC#20957
 * 2018/07/04   Fujitsu       H.Ikeda         Update          QC#25731
 * 2018/08/21   Fujitsu       H.Ikeda         Update          QC#27776
 * 2019/03/22   Fujitsu       T.Ogura         Update          QC#30565
 * 2019/05/08   Fujitsu       H.Ikeda         Update          QC#50140
 * 2020/01/14   Fujitsu       H.Ikeda         Update          QC#55180
 *</pre>
 */
public class NFCB024001 extends S21BatchMain {

    // -- CONSTANTS --//
    /** Message Id */
    private static final String NFCM0584I = "NFCM0584I";

    /** Message Id */
    private static final String NFCM0593I = "NFCM0593I";

    /** Message Id */
    private static final String NFCM0522E = "NFCM0522E";

    /** Message Id */
    private static final String NFCM0606E = "NFCM0606E";

    /** Message Id */
    private static final String NFCM0607E = "NFCM0607E";

    /** Message Id */
    private static final String NFCM0531E = "NFCM0531E";

    /** Message Id */
    private static final String NFVM0608E = "NFVM0608E";

    /** Message Id */
    private static final String NFCM0550E = "NFCM0550E";

    /** Message Id */
    private static final String NFCM0532E = "NFCM0532E";

    /** error account date */
    private static final String NFCM0632E = "NFCM0632E";

    /** Message String : ITEM Name */
    private static final String[] MSG_STR_GLBL_CMPY_CD = {"GLBL_CMPY_CD" };

    /** Message String : ITEM Name */
    private static final String[] MSG_STR_INTERFACE_ID = {"INTERFACE_ID" };

    /** Message String : ITEM Name */
    private static final String[] MSG_STR_VAR_CHAR_CONST_TOC_CD = {NFCConst.CST_VAR_CHAR_CONST_NAME_TOC_CD };

    /** Message String : ITEM Name */
    private static final String[] MSG_STR_VAR_CHAR_CONST_PROD_CD = {NFCConst.CST_VAR_CHAR_CONST_NAME_PROD_CD };

    /** Message String : TBL Name */
    private static final String[] TBL_NAME_AR_RCPT_IN_PROC_WRK = {"AR_RCPT_IN_PROC_WRK" };

    /** Message String : TBL Name */
    private static final String[] TBL_NAME_AR_APPLY_INTFC_WRK = {"AR_APPLY_INTFC_WRK" };

    /** Message String : PROGRAM Name */
    private static final String[] PROGRAM_ID = {"NFCB024001" };

    /** BIGIN INDEX */
    private static final int BIGIN_INDEX = 0;

    /** END INDEX */
    // START 2017/09/05 T.Tsuchida [QC#20957,MOD]
    //private static final int END_INDEX = 6;
    private static final int END_INDEX = 8;
    // END 2017/09/05 T.Tsuchida [QC#20957,MOD]

    /** INV_30_NUM */
    private static final int INV_30_NUM_S_INDEX = 3;

    /** FIRST_REC_INDEX */
    private static final int FIRST_REC_INDEX = 0;

    /** DecimalFormat */
    private static final String CST_DECIMAL_FORMAT_0000 = "0000";

    /** BigDecimal ZERO */
    private static final BigDecimal ZERO = BigDecimal.ZERO;

    /** ERROR MODE 0 : OFF */
    private static final String ERROR_MODE_OFF = "OFF";

    /** ERROR MODE 1 : ON (AR_APPLY_INTFC_WRK INSERT NG) */
    private static final String ERROR_MODE_ON_NG = "ON_NG";

    /** ERROR MODE 2 : ON (AR_APPLY_INTFC_WRK INSERT OK) */
    private static final String ERROR_MODE_ON_OK = "ON_OK";

    /** _EZCancelFlag */
    private static final String CST_EZ_CANCEL_FLAG = "0";

    /** _EZInCompanyCode */
    private static final String CST_EZ_IN_COMPANY_CODE = "000";

    /** _EZUpdateApplicationID */
    private static final String CST_EZ_UPDATE_APPLICATION_ID = "SMB01000";

    /** BIZ_APP_ID */
    private static final String CST_BIZ_APP_ID = "NFCB0240";

    /** BAT_NUM making up character character */
    private static final String CST_BAT_NUM_MAKING_UP_CHAR = "  ";

    /** BAT_NUM length */
    private static final int CST_BAT_NUM_LENGTH = 3;

    /** BAT_SEQ_NUM format character */
    private static final String CST_BAT_SEQ_NUM_FORMAT_CHAR = "%04d";

    /** BAT_NUM for CSV UPLOAD */
    private static final String CST_BAT_NUM_CSV_UPLOAD = "PC";

    /** TimeStamp format for RCV_DT, RCV_TM and RCV_TS */
    private static final String TYPE_TIME_STAMP = "yyyyMMddHHmmss";

    /** INV_NUM length for SQS */
    private static final int CST_INV_NUM_LENGTH_SQS = 8;

    // START 2016/07/29 T.Tsuchida [QC#12649,ADD]
    /** BAT_NUM for CSV UPLOAD */
    private static final String CST_ANSI_PMT_METH_TP_CD_CREDIT_CARD = "04";
    // END 2016/07/29 T.Tsuchida [QC#12649,ADD]

    // -- FIELDS --//
    /** interfaceId. */
    private String interfaceId;

    /** UserVariable1 */
    private String userVariable1;

    /** globalCompanyCode. */
    private String globalCompanyCode;

    /** PAYER_CUST_CD */
    private String initPayerCustCd = NFCConst.CST_DB_INIT_VAL_STR;

    /** AR_BANK_ACCT_CD */
    private String initArBankAcctCd = NFCConst.CST_DB_INIT_VAL_STR;

    /** AR_BANK_ACCT_CD */
    private String initArSoNumMatchFlg = NFCConst.CST_DB_INIT_VAL_STR;

    /** ACCT_DT */
    private String initAcctDt = NFCConst.CST_DB_INIT_VAL_STR;

    /** USR_ID */
    private String initUsrId = NFCConst.CST_DB_INIT_VAL_STR;

    /** VAR_CHAR_CONST value of TOC_CD */
    private String cstTocCd = NFCConst.CST_DB_INIT_VAL_STR;

    /** VAR_CHAR_CONST value of PROD_CD */
    private String cstProdCd = NFCConst.CST_DB_INIT_VAL_STR;

    /** AR_BAT_NUM */
    private String initBatNum = NFCConst.CST_DB_INIT_VAL_STR;

    /** EDI_BAT_NUM */
    private String ediBatNum = NFCConst.CST_DB_INIT_VAL_STR;

    /** CHK_30_NUM */
    private String initChk30Num = NFCConst.CST_DB_INIT_VAL_STR;

    /** Processing Count */
    private int procCount = 0;

    /** normal Count */
    private int normalCnt = 0;

    /** err Count */
    private int errCnt = 0;

    /** ERROR FLG */
    private String errFlg;

    /** depDt01CheckResult. */
    private boolean depDt01CheckResult = false;

    /** AR_RCPT_RCV_WRKTMsg. */
    private List<AR_RCPT_RCV_WRKTMsg> forSaveArRcptRcvWrkTMsgList = new ArrayList<AR_RCPT_RCV_WRKTMsg>();

    /** INSERT TMSG(AR_RCPT_RCV_WRKTMsg). */
    private List<AR_RCPT_IN_PROC_WRKTMsg> insertArRcptInProcWrkTMsg = new ArrayList<AR_RCPT_IN_PROC_WRKTMsg>();

    /** INSERT TMSG(AR_APPLY_INTFC_WRKTMsg). */
    private List<AR_APPLY_INTFC_WRKTMsg> insertArApplyIntfcWrkTMsg = new ArrayList<AR_APPLY_INTFC_WRKTMsg>();

    /** judgeInvRepetitionCode */
    private String judgeInvRepetitionCode = NFCConst.DUP_ERR_CD_NOMAL;

    /** APPLY_GRP_PK */
    private int applyGrpPk = 0;

    /** batProcDate */
    private String batProcDate = null;

    /** validityCheckOfPayerFlg */
    private String validityCheckOfPayerFlg = NFCConst.CST_FLAG_OFF;

    /** SQL Access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** rcptBatSqNum */
    private int seterRcptBatSqNum = 0;

    /** autoCratRefNum */
    private String autoCratRefNum = NFCConst.CST_DB_INIT_VAL_STR;

    /** System TimeStamp */
    private String sysTimeStamp = null;

    /** grpInvFlgMap */
    private Map<String, GRP_INV_FLG_INFO> grpInvFlgMap = new HashMap<String, GRP_INV_FLG_INFO>();

    /** KEY_SEPARATOR */
    private static final String KEY_SEPARATOR = "|";

    /** AR_RGTN_STS_CD */
    private String arRgtnStsCd = "";

    /** INPUT PARAMETER ERROR (AR_RGTN_STS_CD) */
    private static final String[] PARAM_ERR_AR_RGTN_STS_CD = new String[] {"AR_RGTN_STS_CD" };

    /** Message Id : Input Parameter Error [@]. */
    private static final String NFCM0501E = "NFCM0501E";

    // for North America(CSA) 2015/04/06 Add Start
    /** North America(CSA) Flag */
    private boolean isNaFlg = false;

    /** Receipt Only Flag */
    private boolean isRcptOnlyFlg = false;
    // for North America(CSA) 2015/04/06 Add End

    // START 2018/07/04 H.Ikeda [QC#25731,ADD]
    /** commitCnt */
    private int commitCnt = 1000;

    /** Payment Number */
    private String multipleCnt = null;

    /** Multiple No */
    private int multipleNo = 0;
    
    /** Default Multiple No */
    private final int defMultipleNo = 10;
    
    /** CFS IF ID */
    private final String cfsIfId = "NFCI1040";
    // END   2018/07/04 H.Ikeda [QC#25731,ADD]

    /**
     * @param args String[]
     */
    public static void main(final String[] args) {
        new NFCB024001().executeBatch(NFCB024001.class.getSimpleName());
    }

    /**
     * Initial processing
     */
    @Override
    protected final void initRoutine() {

        S21InfoLogOutput.println(NFCM0584I, PROGRAM_ID);

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        this.globalCompanyCode = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();

        if (S21StringUtil.isEmpty(this.globalCompanyCode)) {
            this.errCnt++;
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0522E, MSG_STR_GLBL_CMPY_CD);
        }

        this.interfaceId = S21BatchUtil.getInterfaceID();
        this.userVariable1 = S21BatchUtil.getUserVariable1();

        if (S21StringUtil.isEmpty(this.interfaceId) && S21StringUtil.isEmpty(this.userVariable1)) {
            this.errCnt++;
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0522E, MSG_STR_INTERFACE_ID);
        }

        // Get AR_RGTN_STS_CD
        arRgtnStsCd = ZYPCodeDataUtil.getVarCharConstValue("AR_RGTN_STS_CD", this.globalCompanyCode);
        if (S21StringUtil.isEmpty(arRgtnStsCd)) {
            setTermState(TERM_CD.ABNORMAL_END, normalCnt, errCnt, procCount);
            throw new S21AbendException(NFCM0501E, PARAM_ERR_AR_RGTN_STS_CD);
        }
        debugLog("arRgtnStsCd=<" + arRgtnStsCd + ">");

        this.autoCratRefNum = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.CST_VAR_CHAR_CONST_NAME_AR_AUTO_CRAT_REF_NUM, this.globalCompanyCode);

        this.sysTimeStamp = ZYPDateUtil.getCurrentSystemTime(TYPE_TIME_STAMP);

        // START 2018/07/04 H.Ikeda [QC#25731,ADD]
        String num = getUserVariable2();
        if (ZYPCommonFunc.hasValue(num)) {
            this.multipleCnt = num;
        }

        num = getUserVariable3();
        if (ZYPCommonFunc.hasValue(num)) {
            this.multipleNo = Integer.parseInt(num);
        } else {
            this.multipleNo = defMultipleNo;
        }
        // END   2018/07/04 H.Ikeda [QC#25731,ADD]
    }

    /**
     * Main processing
     */
    @Override
    protected final void mainRoutine() {

        initializationOfInternalVariable();

        // memo. Upload mode won't be used in S21 CSA.
        if (isUpload()) {
            if (NFCCsvCmnMethod.CST_UPLOAD_CSV_RECEIPT.equals(this.userVariable1)) {
                executeForEdiCsvUpld();
            } else if (NFCCsvCmnMethod.CST_UPLOAD_CSV_PAYMENTECH_1.equals(this.userVariable1)) {
                executeForPaymentech1CsvUpld();
            } else if (NFCCsvCmnMethod.CST_UPLOAD_CSV_PAYMENTECH_2.equals(this.userVariable1)) {
                executeForPaymentech2CsvUpld();
            }
        } else {
            execute();
        }

        commit();
    }

    /**
     * Termination
     */
    @Override
    protected final void termRoutine() {

        setTermState(TERM_CD.NORMAL_END, this.normalCnt, this.errCnt, this.procCount);
        S21InfoLogOutput.println(NFCM0593I, PROGRAM_ID);

    }

    /**
     * Main batch processing
     */
    private void execute() {

        // for North America(CSA) 2015/04/06 Add Start
        this.isNaFlg = true;
        // for North America(CSA) 2015/04/06 Add End

        PreparedStatement stmtSelect = null;
        ResultSet rs = null;

        try {
            stmtSelect = selectArRcptRcvWrk(this.interfaceId);
            rs = stmtSelect.executeQuery();

            String checkRcvHdrNum = NFCConst.CST_DB_INIT_VAL_STR;
            this.initChk30Num = NFCConst.CST_DB_INIT_VAL_STR;
            // START 2016/08/26 S.Fujita [QC#13844,ADD]
            BigDecimal checkRcvSqPk = NFCConst.CST_DB_INIT_VAL_NUM;
            // END   2016/08/26 S.Fujita [QC#13844,ADD]

            if (rs.first()) {
                checkRcvHdrNum = convStr(rs.getString(NFCDbConst.RCV_HDR_NUM));
                this.initChk30Num = convStr(rs.getString(NFCDbConst.CHK_30_NUM));
                // START 2016/08/26 S.Fujita [QC#13844,ADD]
                checkRcvSqPk = rs.getBigDecimal(NFCDbConst.RCV_SQ_PK);
                // END   2016/08/26 S.Fujita [QC#13844,ADD]
                rs.beforeFirst();
                while (rs.next()) {
                    // START 2016/08/26 S.Fujita [QC#13844,MOD]
//                    if (checkRcvHdrNum.equals(convStr(rs.getString(NFCDbConst.RCV_HDR_NUM)))) {
                    if (checkRcvSqPk.compareTo(rs.getBigDecimal(NFCDbConst.RCV_SQ_PK)) == 0 && checkRcvHdrNum.equals(convStr(rs.getString(NFCDbConst.RCV_HDR_NUM)))) {
                    // END   2016/08/26 S.Fujita [QC#13844,MOD]
                        priorProcessing(rs);
                        setForSaveArRcptRcvWrkTMsgList(rs);
                        setInsertArRcptInProcWrkTMsg(rs);
                        setInsertArApplyIntfcWrkTMsg(rs);
                        checkRcvHdrNum = notBreakProcessing(rs);
                        // START 2016/08/26 S.Fujita [QC#13844,ADD]
                        checkRcvSqPk = rs.getBigDecimal(NFCDbConst.RCV_SQ_PK);
                        // END   2016/08/26 S.Fujita [QC#13844,ADD]
                    } else {
                        validityCheckOfPaymentData(this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).depDt_01.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chkAmt.getValue(), this.forSaveArRcptRcvWrkTMsgList
                                .get(FIRST_REC_INDEX).ansiPmtMethCd.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chk30Num.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chkApplyDt.getValue());
                        int arRcptRcvWrkTMsgIndex = 0;
                        
                        for (AR_RCPT_RCV_WRKTMsg arRcptRcvWrkTMsg : this.forSaveArRcptRcvWrkTMsgList) {
                            // for North America(CSA) 2015/04/06 Mod Start
                            if (validityCheckOfUnspecifiedPayer(arRcptRcvWrkTMsgIndex)) {
                                validityCheckOfGrpInvouceForNA(arRcptRcvWrkTMsgIndex, arRcptRcvWrkTMsg.inv30Num.getValue(), arRcptRcvWrkTMsg.inv30OrigNum.getValue(), arRcptRcvWrkTMsg.exptFlg.getValue());
                            }
                            // for North America(CSA) 2015/04/06 Mod End
                            arRcptRcvWrkTMsgIndex++;
                        }
                        getPayerFromCustNmDictionary();
                        
                        // 2015/12/16 order change. Payer to be set before validation.
                        // for North America(CSA) 2015/04/06 Add Start
                        overwriteOfPayer();
                        // for North America(CSA) 2015/04/06 Add End
                        // end 2015/12/16
                        
                        validityCheckOfPayer();
                        // for North America(CSA) 2015/04/06 Del Start
                        // theFinalCheckOfPayer();
                        // for North America(CSA) 2015/04/06 Del End
                        
                        editOfHeaderInformation();
                        pntDigit();
                        // for North America(CSA) 2015/04/06 Del Start
                        // judgeInvRepetition();
                        // for North America(CSA) 2015/04/06 Del End
                        registrationProcessing();
                        this.isRcptOnlyFlg = false;

                        upDateArRcptRcvWrk();

                        this.procCount++;
                        this.normalCnt = this.procCount;

                        this.initChk30Num = convStr(rs.getString(NFCDbConst.CHK_30_NUM));

                        initializationOfInternalVariable();
                        priorProcessing(rs);
                        setForSaveArRcptRcvWrkTMsgList(rs);
                        setInsertArRcptInProcWrkTMsg(rs);
                        setInsertArApplyIntfcWrkTMsg(rs);
                        checkRcvHdrNum = notBreakProcessing(rs);
                        // START 2016/08/26 S.Fujita [QC#13844,ADD]
                        checkRcvSqPk = rs.getBigDecimal(NFCDbConst.RCV_SQ_PK);
                        // END   2016/08/26 S.Fujita [QC#13844,ADD]
                    }
                } // while

                validityCheckOfPaymentData(this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).depDt_01.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chkAmt.getValue(), this.forSaveArRcptRcvWrkTMsgList
                        .get(FIRST_REC_INDEX).ansiPmtMethCd.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chk30Num.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chkApplyDt.getValue());
                int arRcptRcvWrkTMsgIndex = 0;
                for (AR_RCPT_RCV_WRKTMsg arRcptRcvWrkTMsg : this.forSaveArRcptRcvWrkTMsgList) {
                    // for North America(CSA) 2015/04/06 Mod Start
                    if (validityCheckOfUnspecifiedPayer(arRcptRcvWrkTMsgIndex)) {
                        validityCheckOfGrpInvouceForNA(arRcptRcvWrkTMsgIndex, arRcptRcvWrkTMsg.inv30Num.getValue(), arRcptRcvWrkTMsg.inv30OrigNum.getValue(), arRcptRcvWrkTMsg.exptFlg.getValue());
                    }
                    // for North America(CSA) 2015/04/06 Mod End
                    arRcptRcvWrkTMsgIndex++;
                }
                getPayerFromCustNmDictionary();
                
                // 2015/12/16 order change. Payer to be set before validation.
                // for North America(CSA) 2015/04/06 Add Start
                overwriteOfPayer();
                // for North America(CSA) 2015/04/06 Add End
                // end 2015/12/16
                
                validityCheckOfPayer();
                theFinalCheckOfPayer();
                
                editOfHeaderInformation();
                pntDigit();
                // for North America(CSA) 2015/04/06 Del Start
                // judgeInvRepetition();
                // for North America(CSA) 2015/04/06 Del End
                registrationProcessing();

                this.procCount++;
                this.normalCnt = this.procCount;

                upDateArRcptRcvWrk();
            }

            this.updateGrpInvFlg();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * Main batch processing of UPLD
     */
    private void executeForEdiCsvUpld() {

        String rcvFuncId;

        if (S21StringUtil.isNotEmpty(this.interfaceId)) {
            rcvFuncId = this.interfaceId;
        } else {
            rcvFuncId = this.userVariable1;
        }

        PreparedStatement stmtSelect = null;
        ResultSet rs = null;

        try {

            stmtSelect = selectArRcptRcvWrk(rcvFuncId);
            rs = stmtSelect.executeQuery();

            String checkRcvHdrNum = NFCConst.CST_DB_INIT_VAL_STR;
            BigDecimal checkUpldCsvrqstPk = BigDecimal.ZERO;

            if (rs.first()) {
                checkRcvHdrNum = convStr(rs.getString(NFCDbConst.RCV_HDR_NUM));
                checkUpldCsvrqstPk = rs.getBigDecimal(NFCDbConst.UPLD_CSV_RQST_PK);
                rs.beforeFirst();
                while (rs.next()) {
                    if (checkUpldCsvrqstPk.compareTo(rs.getBigDecimal(NFCDbConst.UPLD_CSV_RQST_PK)) == 0) {
                        if (checkRcvHdrNum.equals(convStr(rs.getString(NFCDbConst.RCV_HDR_NUM)))) {
                            priorProcessing(rs);
                            setForSaveArRcptRcvWrkTMsgList(rs);
                            setInsertArRcptInProcWrkTMsg(rs);
                            setInsertArApplyIntfcWrkTMsg(rs);
                            checkRcvHdrNum = notBreakProcessing(rs);
                            checkUpldCsvrqstPk = rs.getBigDecimal(NFCDbConst.UPLD_CSV_RQST_PK);
                        } else {
                            validityCheckOfPaymentData(this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).depDt_01.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chkAmt.getValue(), this.forSaveArRcptRcvWrkTMsgList
                                    .get(FIRST_REC_INDEX).ansiPmtMethCd.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chk30Num.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chkApplyDt.getValue());
                            validityCheckOfPayer();
                            theFinalCheckOfPayer();
                            editOfHeaderInformation();
                            judgeInvRepetition();
                            isErrorMode(ERROR_MODE_ON_NG);
                            registrationProcessing();

                            upDateArRcptRcvWrk();

                            this.procCount++;
                            this.normalCnt = this.procCount;

                            initializationOfInternalVariable();
                            priorProcessing(rs);
                            setForSaveArRcptRcvWrkTMsgList(rs);
                            setInsertArRcptInProcWrkTMsg(rs);
                            setInsertArApplyIntfcWrkTMsg(rs);
                            checkRcvHdrNum = notBreakProcessing(rs);
                            checkUpldCsvrqstPk = rs.getBigDecimal(NFCDbConst.UPLD_CSV_RQST_PK);
                        }
                    } else {
                        validityCheckOfPaymentData(this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).depDt_01.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chkAmt.getValue(), this.forSaveArRcptRcvWrkTMsgList
                                .get(FIRST_REC_INDEX).ansiPmtMethCd.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chk30Num.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chkApplyDt.getValue());
                        validityCheckOfPayer();
                        theFinalCheckOfPayer();
                        editOfHeaderInformation();
                        judgeInvRepetition();
                        isErrorMode(ERROR_MODE_ON_NG);
                        registrationProcessing();

                        upDateArRcptRcvWrk();

                        this.procCount++;
                        this.normalCnt = this.procCount;

                        initializationOfInternalVariable();
                        priorProcessing(rs);
                        setForSaveArRcptRcvWrkTMsgList(rs);
                        setInsertArRcptInProcWrkTMsg(rs);
                        setInsertArApplyIntfcWrkTMsg(rs);
                        checkRcvHdrNum = notBreakProcessing(rs);
                        checkUpldCsvrqstPk = rs.getBigDecimal(NFCDbConst.UPLD_CSV_RQST_PK);
                    }
                }

                validityCheckOfPaymentData(this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).depDt_01.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chkAmt.getValue(), this.forSaveArRcptRcvWrkTMsgList
                        .get(FIRST_REC_INDEX).ansiPmtMethCd.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chk30Num.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chkApplyDt.getValue());
                validityCheckOfPayer();
                theFinalCheckOfPayer();
                editOfHeaderInformation();
                judgeInvRepetition();
                isErrorMode(ERROR_MODE_ON_NG);
                registrationProcessing();

                this.procCount++;
                this.normalCnt = this.procCount;

                upDateArRcptRcvWrk();
            }

            this.updateGrpInvFlg();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * Main batch processing of UPLD
     */
    private void executeForPaymentech1CsvUpld() {

        String rcvFuncId;

        if (S21StringUtil.isNotEmpty(this.interfaceId)) {
            rcvFuncId = this.interfaceId;
        } else {
            rcvFuncId = this.userVariable1;
        }

        PreparedStatement stmtSelect = null;
        ResultSet rs = null;

        try {

            stmtSelect = selectArRcptRcvWrk(rcvFuncId);
            rs = stmtSelect.executeQuery();

            String checkRcvHdrNum = NFCConst.CST_DB_INIT_VAL_STR;

            if (rs.first()) {
                checkRcvHdrNum = convStr(rs.getString(NFCDbConst.RCV_HDR_NUM));
                rs.beforeFirst();
                while (rs.next()) {
                    if (checkRcvHdrNum.equals(convStr(rs.getString(NFCDbConst.RCV_HDR_NUM)))) {
                        priorProcessing(rs);
                        setForSaveArRcptRcvWrkTMsgList(rs);
                        setInsertArRcptInProcWrkTMsg(rs);
                        setInsertArApplyIntfcWrkTMsg(rs);
                        checkRcvHdrNum = notBreakProcessing(rs);
                    } else {
                        validityCheckOfPaymentData(this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).depDt_01.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chkAmt.getValue(), this.forSaveArRcptRcvWrkTMsgList
                                .get(FIRST_REC_INDEX).ansiPmtMethCd.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chk30Num.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chkApplyDt.getValue());
                        int arRcptRcvWrkTMsgIndex = 0;
                        for (AR_RCPT_RCV_WRKTMsg arRcptRcvWrkTMsg : this.forSaveArRcptRcvWrkTMsgList) {
                            validityCheckOfGrpInvouce(arRcptRcvWrkTMsgIndex, arRcptRcvWrkTMsg.inv30Num.getValue(), null, arRcptRcvWrkTMsg.exptFlg.getValue());
                            arRcptRcvWrkTMsgIndex++;
                        }
                        getPayerFromCustNmDictionary();
                        validityCheckOfPayer();
                        theFinalCheckOfPayer();
                        editOfHeaderInformation();
                        judgeInvRepetition();
                        checkSoNumMatchFlag();
                        registrationProcessing();

                        upDateArRcptRcvWrk();

                        this.procCount++;
                        this.normalCnt = this.procCount;

                        initializationOfInternalVariable();
                        priorProcessing(rs);
                        setForSaveArRcptRcvWrkTMsgList(rs);
                        setInsertArRcptInProcWrkTMsg(rs);
                        setInsertArApplyIntfcWrkTMsg(rs);
                        checkRcvHdrNum = notBreakProcessing(rs);
                    }
                }

                validityCheckOfPaymentData(this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).depDt_01.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chkAmt.getValue(), this.forSaveArRcptRcvWrkTMsgList
                        .get(FIRST_REC_INDEX).ansiPmtMethCd.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chk30Num.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chkApplyDt.getValue());
                int arRcptRcvWrkTMsgIndex = 0;
                for (AR_RCPT_RCV_WRKTMsg arRcptRcvWrkTMsg : this.forSaveArRcptRcvWrkTMsgList) {
                    validityCheckOfGrpInvouce(arRcptRcvWrkTMsgIndex, arRcptRcvWrkTMsg.inv30Num.getValue(), null, arRcptRcvWrkTMsg.exptFlg.getValue());
                    arRcptRcvWrkTMsgIndex++;
                }
                getPayerFromCustNmDictionary();
                validityCheckOfPayer();
                theFinalCheckOfPayer();
                editOfHeaderInformation();
                judgeInvRepetition();
                checkSoNumMatchFlag();
                registrationProcessing();

                this.procCount++;
                this.normalCnt = this.procCount;

                upDateArRcptRcvWrk();
            }

            this.updateGrpInvFlg();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * Main batch processing of UPLD
     */
    private void executeForPaymentech2CsvUpld() {

        String rcvFuncId;

        if (S21StringUtil.isNotEmpty(this.interfaceId)) {
            rcvFuncId = this.interfaceId;
        } else {
            rcvFuncId = this.userVariable1;
        }

        PreparedStatement stmtSelect = null;
        ResultSet rs = null;

        try {

            stmtSelect = selectArRcptRcvWrk(rcvFuncId);
            rs = stmtSelect.executeQuery();

            String checkRcvHdrNum = NFCConst.CST_DB_INIT_VAL_STR;

            if (rs.first()) {
                checkRcvHdrNum = convStr(rs.getString(NFCDbConst.RCV_HDR_NUM));
                rs.beforeFirst();
                while (rs.next()) {
                    if (checkRcvHdrNum.equals(convStr(rs.getString(NFCDbConst.RCV_HDR_NUM)))) {
                        priorProcessing(rs);
                        setForSaveArRcptRcvWrkTMsgList(rs);
                        setInsertArRcptInProcWrkTMsg(rs);
                        setInsertArApplyIntfcWrkTMsg(rs);
                        checkRcvHdrNum = notBreakProcessing(rs);
                    } else {
                        validityCheckOfPaymentData(this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).depDt_01.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chkAmt.getValue(), this.forSaveArRcptRcvWrkTMsgList
                                .get(FIRST_REC_INDEX).ansiPmtMethCd.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chk30Num.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chkApplyDt.getValue());
                        int arRcptRcvWrkTMsgIndex = 0;
                        for (AR_RCPT_RCV_WRKTMsg arRcptRcvWrkTMsg : this.forSaveArRcptRcvWrkTMsgList) {
                            validityCheckOfGrpInvouce(arRcptRcvWrkTMsgIndex, arRcptRcvWrkTMsg.inv30Num.getValue(), null, arRcptRcvWrkTMsg.exptFlg.getValue());
                            arRcptRcvWrkTMsgIndex++;
                        }
                        getPayerFromCustNmDictionary();
                        validityCheckOfPayer();
                        theFinalCheckOfPayer();
                        editOfHeaderInformation();
                        judgeInvRepetition();
                        checkSoNumMatchFlag();
                        registrationProcessing();

                        upDateArRcptRcvWrk();

                        this.procCount++;
                        this.normalCnt = this.procCount;

                        initializationOfInternalVariable();
                        priorProcessing(rs);
                        setForSaveArRcptRcvWrkTMsgList(rs);
                        setInsertArRcptInProcWrkTMsg(rs);
                        setInsertArApplyIntfcWrkTMsg(rs);
                        checkRcvHdrNum = notBreakProcessing(rs);
                    }
                }

                validityCheckOfPaymentData(this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).depDt_01.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chkAmt.getValue(), this.forSaveArRcptRcvWrkTMsgList
                        .get(FIRST_REC_INDEX).ansiPmtMethCd.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chk30Num.getValue(), this.forSaveArRcptRcvWrkTMsgList.get(FIRST_REC_INDEX).chkApplyDt.getValue());
                int arRcptRcvWrkTMsgIndex = 0;
                for (AR_RCPT_RCV_WRKTMsg arRcptRcvWrkTMsg : this.forSaveArRcptRcvWrkTMsgList) {
                    validityCheckOfGrpInvouce(arRcptRcvWrkTMsgIndex, arRcptRcvWrkTMsg.inv30Num.getValue(), null, arRcptRcvWrkTMsg.exptFlg.getValue());
                    arRcptRcvWrkTMsgIndex++;
                }
                getPayerFromCustNmDictionary();
                validityCheckOfPayer();
                theFinalCheckOfPayer();
                editOfHeaderInformation();
                judgeInvRepetition();
                checkSoNumMatchFlag();
                registrationProcessing();

                this.procCount++;
                this.normalCnt = this.procCount;

                upDateArRcptRcvWrk();
            }

            this.updateGrpInvFlg();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * Acquisition of AR_RCPT_RCV_WRK
     * @param rcvFuncId String
     * @return PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement selectArRcptRcvWrk(String rcvFuncId) throws SQLException {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        // START 2018/07/04 H.ikeda [QC#25731,MOD]
        //Map<String, String> queryParam = new HashMap<String, String>();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
        queryParam.put(NFCDbConst.RCV_FUNC_ID_J, rcvFuncId);
        queryParam.put(NFCDbConst.RCV_STS_CD_J, NFCConst.CST_RCV_STS_CD_UNPROC);

        if (ZYPCommonFunc.hasValue(this.multipleCnt)) {
            if (cfsIfId.equals(rcvFuncId)) {
                List<String> lineList = new ArrayList<String>();
                int cnt = Integer.parseInt(this.multipleCnt);
                PreparedStatement ps = this.ssmLLClient.createPreparedStatement("getArRcptRcvWrkSqNumList", queryParam, execParam);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String sqNum = rs.getString(NFCDbConst.RCPT_BAT_SQ_NUM);
                    int no = chgStr(sqNum) % this.multipleNo;
                    if (no == cnt) {
                        lineList.add(sqNum);
                    }
                }
                String[] lineStr;
                if (lineList.size() != 0) {
                    lineStr = new String[lineList.size()];
                    for (int i = 0; i < lineList.size(); i++) {
                        lineStr[i] = lineList.get(i);
                    }
                } else {
                    lineStr = new String[1];
                    lineStr[0] = this.multipleCnt;
                }
                queryParam.put("lineList", lineStr);
            }
        }
        // END   2018/07/04 H.ikeda [QC#25731,MOD]

        return this.ssmLLClient.createPreparedStatement("getArRcptRcvWrk", queryParam, execParam);

    }
    // START 2018/07/04 H.ikeda [QC#25731,ADD]
    private int chgStr(String item) {
        if (item == null) {
            return 0;
        }
        Pattern ptn = Pattern.compile("^0+([0-9]+.*)");
        Matcher mch = ptn.matcher(item);
        String output = null;
        if (mch.matches()) {
           output = mch.group(1);
           if (output == null) {
               return 0;
           }
        }
        return Integer.parseInt(output);
    }
    // END   2018/07/04 H.ikeda [QC#25731,ADD]

    private void updateGrpInvFlg() throws SQLException {
        debugLog("updateGrpInvFlg -- start");
        List<AR_RCPT_IN_PROC_WRKTMsg> updList = new ArrayList<AR_RCPT_IN_PROC_WRKTMsg>();

        Set set = this.grpInvFlgMap.entrySet();
        Iterator ite = set.iterator();

        while (ite.hasNext()) {
            Entry entry = (Entry) ite.next();

            GRP_INV_FLG_INFO info = (GRP_INV_FLG_INFO) entry.getValue();
            AR_RCPT_IN_PROC_WRKTMsg cond = info.getCondition();

            cond = (AR_RCPT_IN_PROC_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdate(cond);

            if (!cond.getReturnCode().equals(S21FastTBLAccessor.RTNCD_NOT_FOUND)) {
                ZYPEZDItemValueSetter.setValue(cond.grpInvFlg, NFCCmnMethod.convertDbString(info.getGrpInvFlg()));
                updList.add(cond);
            }
            // START 2018/07/04 H.ikeda [QC#25731,MOD]
            if (updList.size() >= commitCnt) {
                S21FastTBLAccessor.update(updList.toArray(new AR_RCPT_IN_PROC_WRKTMsg[updList.size()]));
                updList.clear();
            }
        }
        if (updList.size() != 0) {
            //AR_RCPT_IN_PROC_WRKTMsg[] updArray = (AR_RCPT_IN_PROC_WRKTMsg[]) updList.toArray(new AR_RCPT_IN_PROC_WRKTMsg[0]);
            int resultCnt = S21FastTBLAccessor.update(updList.toArray(new AR_RCPT_IN_PROC_WRKTMsg[updList.size()]));
            // END   2018/07/04 H.ikeda [QC#25731,MOD]
            debugLog("update count: " + resultCnt);
        }
        debugLog("updateGrpInvFlg -- end");
    }

    /**
     * checkSoNumMatchFlag
     */
    private void checkSoNumMatchFlag() {

        if (NFCConst.CST_FLAG_OFF.equals(this.initArSoNumMatchFlg)) {
            isErrorMode(ERROR_MODE_ON_OK);
            debugLog("ERROR MODE SET : ERROR_MODE_ON_OK : ArSoNumMatchFlg = 'N' ");
            return;
        }

    }

    /**
     * Acquisition of BAT_PROC_DATE
     */
    private void getBatProcDate() {

        this.batProcDate = ZYPDateUtil.getBatProcDate(this.globalCompanyCode);

    }

    /**
     * Acquisition of AR_EDI_SEND_BANK
     * @param acctMaCd String
     */
    private void getArEdiSendBank(String acctMaCd, String batNum) {

        AR_EDI_SEND_BANKTMsg arEdiSendBankTmsg = (AR_EDI_SEND_BANKTMsg) ZYPCodeDataUtil.findByCode(AR_EDI_SEND_BANK.class, this.globalCompanyCode, acctMaCd);
        if (arEdiSendBankTmsg != null) {
            this.initPayerCustCd = arEdiSendBankTmsg.payerCustCd.getValue();
            this.initArBankAcctCd = arEdiSendBankTmsg.arBankAcctCd.getValue();
            this.initArSoNumMatchFlg = arEdiSendBankTmsg.arSoNumMatchFlg.getValue();
            if (NFCConst.CST_FLAG_OFF.equals(arEdiSendBankTmsg.arAutoCashAppFlg.getValue())) {
                isErrorMode(ERROR_MODE_ON_NG);
            }
            if (S21StringUtil.isEmpty(batNum)) {
                this.initBatNum = arEdiSendBankTmsg.arBatNum.getValue();
            }
            if (!isUpload()) {
                this.ediBatNum = arEdiSendBankTmsg.arBatNum.getValue();
            }
        } else {
            if (!isUpload()) {
                this.ediBatNum = CST_BAT_NUM_MAKING_UP_CHAR;
            }
        }

    }

    /**
     * Acquisition of AR_ACCT_DT
     */
    private void getArAcctDt() {

        PreparedStatement stmtSelect = null;
        ResultSet rs = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            // Get Account data
            String subSysCd = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.CST_VAR_CHAR_CONST_NAME_SUB_SYS_ID, this.globalCompanyCode);
            if (S21StringUtil.isEmpty(subSysCd)) {
                this.errCnt++;
                setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
                throw new S21AbendException(NFCM0632E);
            }

            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
            queryParam.put(NFCDbConst.SUB_SYS_CD_J, subSysCd);
            queryParam.put(NFCDbConst.ONL_BAT_TP_CD_J, NFCConst.CST_ONL_BAT_TP_CD_BAT);

            stmtSelect = this.ssmLLClient.createPreparedStatement("getArAcctDt", queryParam, execParam);
            rs = stmtSelect.executeQuery();

            if (rs.first()) {
                this.initAcctDt = convStr(rs.getString(NFCDbConst.ACCT_DT));
            } else {
                this.errCnt++;
                setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
                throw new S21AbendException(NFCM0606E);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

    }

    /**
     * Acquisition of VAR_CHAR_CONST
     */
    private void getVarCharConst() {

        this.initUsrId = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.CST_VAR_CHAR_CONST_NAME_AR_BAT_USR_ID, this.globalCompanyCode);

        if (S21StringUtil.isEmpty(this.initUsrId)) {
            this.errCnt++;
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0607E);
        }

        this.cstTocCd = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.CST_VAR_CHAR_CONST_NAME_TOC_CD, this.globalCompanyCode);

        if (S21StringUtil.isEmpty(this.cstTocCd)) {
            this.errCnt++;
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0531E, MSG_STR_VAR_CHAR_CONST_TOC_CD);
        }

        this.cstProdCd = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.CST_VAR_CHAR_CONST_NAME_PROD_CD, this.globalCompanyCode);

        if (S21StringUtil.isEmpty(this.cstProdCd)) {
            this.errCnt++;
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0531E, MSG_STR_VAR_CHAR_CONST_PROD_CD);
        }

    }

    /**
     * Acquisition of VAR_CHAR_CONST
     * @param rs ResultSet
     * @throws SQLException
     */
    private String notBreakProcessing(ResultSet rs) throws SQLException {

        return convStr(rs.getString(NFCDbConst.RCV_HDR_NUM));

    }

    /**
     * 3.Validity check of payment data
     * @param depDt_01 String
     * @param chkAmt BigDecimal
     * @param ansiPmtMethCd String
     * @param chk30Num String
     * @param chkApplyDt String
     */
    private void validityCheckOfPaymentData(String depDt01, BigDecimal chkAmt, String ansiPmtMethCd, String chk30Num, String chkApplyDt) {

        this.depDt01CheckResult = ZYPDateUtil.isValidDate(depDt01, ZYPDateUtil.TYPE_YYYYMMDD);
        if (!this.depDt01CheckResult) {
            isErrorMode(ERROR_MODE_ON_NG);
            debugLog("ERROR MODE SET : ERROR_MODE_ON_NG : DEP_DT_01 Err");
        }
        if (ZERO.compareTo(chkAmt) == 0) {
            isErrorMode(ERROR_MODE_ON_NG);
            debugLog("ERROR MODE SET : ERROR_MODE_ON_NG : CHK_AMT Err");
        }
        if (NFCConst.CST_ANSI_PMT_METH_TP_CD_ACH.equals(ansiPmtMethCd) || NFCConst.CST_ANSI_PMT_METH_TP_CD_CHK.equals(ansiPmtMethCd)) {
            if (S21StringUtil.isNotEmpty(chkApplyDt)) {
                if (!ZYPDateUtil.isValidDate(chkApplyDt, ZYPDateUtil.TYPE_YYYYMMDD)) {
                    isErrorMode(ERROR_MODE_ON_NG);
                    debugLog("ERROR MODE SET : ERROR_MODE_ON_NG : CHL_APPLY_DT Err");
                }
            }
        }
    }

    // for North America(CSA) 2015/04/06 Add Start
    /**
     * Validity check of Unspecified Payer
     * @param arRcptRcvWrkTMsgListIndex int
     * @return true : Payer have been identified.
     */
    private boolean validityCheckOfUnspecifiedPayer(int arRcptRcvWrkTMsgListIndex) {

        if (forSaveArRcptRcvWrkTMsgList.size() != insertArApplyIntfcWrkTMsg.size()) {
            return true;
        }

        AR_RCPT_RCV_WRKTMsg rcvWrkTMsg = this.forSaveArRcptRcvWrkTMsgList.get(arRcptRcvWrkTMsgListIndex);
        String arCustIdStsCd = rcvWrkTMsg.arCustIdStsCd.getValue();

        if (ZYPCommonFunc.hasValue(arCustIdStsCd) && AR_CUST_ID_STS.UN_IDENTIFIED.equals(arCustIdStsCd)) {
            this.isRcptOnlyFlg = true;
            return false;
        }

        return true;
    }
    // for North America(CSA) 2015/04/06 Add End

    /**
     * 4.Check related to INVOICE
     * @param arRcptRcvWrkTMsgListIndex int
     * @param inv30Num String
     * @param payerCustCd String
     */
    private void validityCheckOfGrpInvouce(int arRcptRcvWrkTMsgListIndex, String inv30Num, String inv30OrigNum, String exptFlg) {

        if ((S21StringUtil.isEmpty(inv30Num)) || (inv30Num.length() < INV_30_NUM_S_INDEX)) {
            isErrorMode(ERROR_MODE_ON_OK);
            this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).grpInvFlg.setValue(NFCConst.CST_GRP_INV_FLG_GRP_OFF);
            setTrxBal(arRcptRcvWrkTMsgListIndex, NFCConst.CST_DB_INIT_VAL_NUM, NFCConst.CST_DB_INIT_VAL_STR, NFCConst.CST_DB_INIT_VAL_NUM, NFCConst.CST_DB_INIT_VAL_STR, NFCConst.CST_DB_INIT_VAL_STR);
            debugLog("ERROR MODE SET : ERROR_MODE_ON_OK : INV_30_NUM NULL or SHORT");

            this.putGrpInvInfoToMap(this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).rcptInProcSqPk.getValue(), this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).rcvHdrNum.getValue(), this.insertArApplyIntfcWrkTMsg
                    .get(arRcptRcvWrkTMsgListIndex).rcvDtlNum.getValue(), this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).grpInvFlg.getValue());

            return;
        }

        if (inv30OrigNum == null) {
            inv30OrigNum = inv30Num;
        } else if (!ZYPCommonFunc.hasValue(inv30OrigNum)) {
            inv30OrigNum = inv30Num;
        }

        List<String> inv30NumList = new ArrayList<String>();
        if (S21StringUtil.isEmpty(this.initArSoNumMatchFlg) || NFCConst.CST_FLAG_OFF.equals(this.initArSoNumMatchFlg)) {
            PreparedStatement stmtSelect = null;
            ResultSet rs = null;
            try {
                S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

                Map<String, String> queryParam = new HashMap<String, String>();
                queryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
                queryParam.put(NFCDbConst.INV_NUM_J, inv30Num);

                stmtSelect = this.ssmLLClient.createPreparedStatement("getArInvInfoFromGrpInvChk", queryParam, execParam);
                rs = stmtSelect.executeQuery();

                if (rs.first()) {
                    this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).grpInvFlg.setValue(NFCConst.CST_GRP_INV_FLG_GRP_ON);
                    inv30NumList.add(inv30Num);
                    setPayer(arRcptRcvWrkTMsgListIndex, convStr(rs.getString(NFCDbConst.PAYER_CUST_CD)));
                } else {
                    this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).grpInvFlg.setValue(NFCConst.CST_GRP_INV_FLG_GRP_OFF);
                }
            } catch (SQLException e) {
                sqlExceptionHandler(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            }
        } else {
            this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).grpInvFlg.setValue(NFCConst.CST_GRP_INV_FLG_GRP_OFF);
        }

        this.putGrpInvInfoToMap(this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).rcptInProcSqPk.getValue(), this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).rcvHdrNum.getValue(), this.insertArApplyIntfcWrkTMsg
                .get(arRcptRcvWrkTMsgListIndex).rcvDtlNum.getValue(), this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).grpInvFlg.getValue());

        if (NFCConst.CST_GRP_INV_FLG_GRP_OFF.equals(this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).grpInvFlg.getValue())) {

            PreparedStatement invChkStmtSelect = null;
            ResultSet invChkRs = null;

            try {
                S21SsmExecutionParameter invChkExecParam = new S21SsmExecutionParameter();

                Map<String, String> invChkQueryParam = new HashMap<String, String>();
                invChkQueryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
                invChkQueryParam.put(NFCDbConst.INV_NUM_J, inv30Num);
                invChkQueryParam.put("arCashApplyStsCd01", AR_CASH_APPLY_STS.UNAPPLIED);
                invChkQueryParam.put("arCashApplyStsCd02", AR_CASH_APPLY_STS.PARTIAL);
                invChkQueryParam.put(NFCDbConst.EXPT_FLG_J, exptFlg);
                // START 2019/05/08 [QC#50140, ADD]
                // START 2020/01/14 [QC#55180, MOD]
                //invChkQueryParam.put("arTrxTpCd", AR_TRX_TP.RECEIPT);
                invChkQueryParam.put("rcp", AR_TRX_TP.RECEIPT);
                invChkQueryParam.put("acc", AR_TRX_TP.ON_ACCOUNT);
                // END   2020/01/14 [QC#55180, MOD]
                // END   2019/05/08 [QC#50140, ADD]

                invChkStmtSelect = this.ssmLLClient.createPreparedStatement("getArInvInfoFromInvChk", invChkQueryParam, invChkExecParam);
                invChkRs = invChkStmtSelect.executeQuery();

                while (invChkRs.next()) {
                    if (inv30Num.equals(convStr(invChkRs.getString(NFCDbConst.INV_NUM)))) {
                        inv30NumList.add(convStr(invChkRs.getString(NFCDbConst.INV_NUM)));
                    }
                }

            } catch (SQLException e) {
                sqlExceptionHandler(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(invChkStmtSelect, invChkRs);
            }

            if (inv30NumList.size() == 0) {

                inv30NumList.clear();

                PreparedStatement trxChkStmtSelect = null;
                ResultSet trxChkRs = null;

                try {
                    S21SsmExecutionParameter trxChkExecParam = new S21SsmExecutionParameter();

                    Map<String, String> invChkQueryParam = new HashMap<String, String>();
                    invChkQueryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
                    invChkQueryParam.put(NFCDbConst.AR_CUST_REF_NUM_J, inv30OrigNum);
                    invChkQueryParam.put("arCashApplyStsCd01", AR_CASH_APPLY_STS.UNAPPLIED);
                    invChkQueryParam.put("arCashApplyStsCd02", AR_CASH_APPLY_STS.PARTIAL);
                    invChkQueryParam.put(NFCDbConst.EXPT_FLG_J, exptFlg);
                    // START 2019/05/08 [QC#50140, ADD]
                    // START 2020/01/14 [QC#55180, MOD]
                    //invChkQueryParam.put("arTrxTpCd", AR_TRX_TP.RECEIPT);
                    invChkQueryParam.put("rcp", AR_TRX_TP.RECEIPT);
                    invChkQueryParam.put("acc", AR_TRX_TP.ON_ACCOUNT);
                    // END   2020/01/14 [QC#55180, MOD]
                    // END   2019/05/08 [QC#50140, ADD]

                    trxChkStmtSelect = this.ssmLLClient.createPreparedStatement("getArTrxBalFromInvChk", invChkQueryParam, trxChkExecParam);
                    trxChkRs = trxChkStmtSelect.executeQuery();
                    int dbGetSize = 0;
                    while (trxChkRs.next()) {
                        dbGetSize++;
                    }
                    if (dbGetSize == 1) {
                        trxChkRs.first();
                        inv30NumList.add(convStr(trxChkRs.getString(NFCDbConst.AR_TRX_NUM)));
                    }
                } catch (SQLException e) {
                    sqlExceptionHandler(e);
                } finally {
                    S21SsmLowLevelCodingClient.closeResource(trxChkStmtSelect, trxChkRs);
                }
            }

            if (inv30NumList.size() == 0) {
                PreparedStatement invNumEditStmtSelect = null;
                ResultSet invNumEditRs = null;

                try {
                    S21SsmExecutionParameter invNumEditExecParam = new S21SsmExecutionParameter();

                    Map<String, String> invNumEditQueryParam = new HashMap<String, String>();
                    invNumEditQueryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);

                    invNumEditStmtSelect = this.ssmLLClient.createPreparedStatement("getArEdiInvNumEdit", invNumEditQueryParam, invNumEditExecParam);
                    invNumEditRs = invNumEditStmtSelect.executeQuery();

                    while (invNumEditRs.next()) {
                        String findItem = NFCConst.CST_DB_INIT_VAL_STR;
                        if (NFCConst.CST_INV_NUM_EDIT_PROC_TP_CD_ADD_HEAD.equals(convStr(invNumEditRs.getString(NFCDbConst.INV_NUM_EDIT_PROC_TP_CD)))) {
                            findItem = convStr(invNumEditRs.getString(NFCDbConst.ADD_CHAR_TXT)) + inv30Num;
                        }
                        if (NFCConst.CST_INV_NUM_EDIT_PROC_TP_CD_DEL_FORWARD.equals(convStr(invNumEditRs.getString(NFCDbConst.INV_NUM_EDIT_PROC_TP_CD)))) {
                            // START 2019/03/22 T.Ogura [QC#30565,MOD]
//                            findItem = inv30Num.substring(invNumEditRs.getInt(NFCDbConst.DEL_CHAR_CNT));
                            if (inv30Num.length() > invNumEditRs.getInt(NFCDbConst.DEL_CHAR_CNT)) {
                                findItem = inv30Num.substring(invNumEditRs.getInt(NFCDbConst.DEL_CHAR_CNT));
                            }
                            // END   2019/03/22 T.Ogura [QC#30565,MOD]
                        }
                        if (NFCConst.CST_INV_NUM_EDIT_PROC_TP_CD_DEL_REAR_SIDE.equals(convStr(invNumEditRs.getString(NFCDbConst.INV_NUM_EDIT_PROC_TP_CD)))) {
                            int editNum = 0;
                            if (!NFCConst.CST_DB_INIT_VAL_STR.equals(convStr(invNumEditRs.getString(NFCDbConst.ADD_CHAR_TXT)))) {
                                findItem = convStr(invNumEditRs.getString(NFCDbConst.ADD_CHAR_TXT)) + inv30Num;
                                editNum = findItem.length();
                            } else {
                                editNum = inv30Num.length();
                                findItem = inv30Num;
                            }
                            // START 2019/03/22 T.Ogura [QC#30565,MOD]
//                            editNum = editNum - invNumEditRs.getInt(NFCDbConst.DEL_CHAR_CNT);
//                            findItem = findItem.substring(0, editNum);
                            if (editNum > invNumEditRs.getInt(NFCDbConst.DEL_CHAR_CNT)) {
                                editNum = editNum - invNumEditRs.getInt(NFCDbConst.DEL_CHAR_CNT);
                                findItem = findItem.substring(0, editNum);
                            }
                            // END   2019/03/22 T.Ogura [QC#30565,MOD]
                        }

                        invChkStmtSelect = null;
                        invChkRs = null;

                        try {
                            S21SsmExecutionParameter invChkExecParam = new S21SsmExecutionParameter();

                            Map<String, String> invChkQueryParam = new HashMap<String, String>();
                            invChkQueryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
                            invChkQueryParam.put(NFCDbConst.INV_NUM_J, findItem);
                            invChkQueryParam.put("arCashApplyStsCd01", AR_CASH_APPLY_STS.UNAPPLIED);
                            invChkQueryParam.put("arCashApplyStsCd02", AR_CASH_APPLY_STS.PARTIAL);
                            invChkQueryParam.put(NFCDbConst.EXPT_FLG_J, exptFlg);
                            // START 2019/05/08 [QC#50140, ADD]
                            // START 2020/01/14 [QC#55180, MOD]
                            //invChkQueryParam.put("arTrxTpCd", AR_TRX_TP.RECEIPT);
                            invChkQueryParam.put("rcp", AR_TRX_TP.RECEIPT);
                            invChkQueryParam.put("acc", AR_TRX_TP.ON_ACCOUNT);
                            // END   2020/01/14 [QC#55180, MOD]
                            // END   2019/05/08 [QC#50140, ADD]

                            invChkStmtSelect = this.ssmLLClient.createPreparedStatement("getArInvInfoFromInvChk", invChkQueryParam, invChkExecParam);
                            invChkRs = invChkStmtSelect.executeQuery();

                            while (invChkRs.next()) {
                                if (findItem.equals(convStr(invChkRs.getString(NFCDbConst.INV_NUM)))) {
                                    inv30NumList.add(convStr(invChkRs.getString(NFCDbConst.INV_NUM)));
                                }
                            }
                        } catch (SQLException e) {
                            sqlExceptionHandler(e);
                        } finally {
                            S21SsmLowLevelCodingClient.closeResource(invChkStmtSelect, invChkRs);
                        }
                    }
                } catch (SQLException e) {
                    sqlExceptionHandler(e);
                } finally {
                    S21SsmLowLevelCodingClient.closeResource(invNumEditStmtSelect, invNumEditRs);
                }
            }

            if (!isUpload()) {
                if (inv30NumList.size() == 0) {
                    if (inv30Num.length() == CST_INV_NUM_LENGTH_SQS) {
                        invChkStmtSelect = null;
                        invChkRs = null;

                        try {
                            S21SsmExecutionParameter invChkExecParam = new S21SsmExecutionParameter();

                            Map<String, String> invChkQueryParam = new HashMap<String, String>();
                            invChkQueryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
                            invChkQueryParam.put(NFCDbConst.INV_NUM_J, inv30Num + "00");
                            invChkQueryParam.put("arCashApplyStsCd01", AR_CASH_APPLY_STS.UNAPPLIED);
                            invChkQueryParam.put("arCashApplyStsCd02", AR_CASH_APPLY_STS.PARTIAL);
                            invChkQueryParam.put(NFCDbConst.EXPT_FLG_J, exptFlg);
                            invChkQueryParam.put("sysSrcCdSQS", SYS_SRC.SQS);
                            invChkQueryParam.put("sysSrcCdSQS1", SYS_SRC.S21_SERVICE_AND_REPAIR);
                            invChkStmtSelect = this.ssmLLClient.createPreparedStatement("getArInvInfoFromInvChkSQS", invChkQueryParam, invChkExecParam);
                            invChkRs = invChkStmtSelect.executeQuery();

                            while (invChkRs.next()) {
                                inv30NumList.add(convStr(invChkRs.getString(NFCDbConst.INV_NUM)));
                            }
                        } catch (SQLException e) {
                            sqlExceptionHandler(e);
                        } finally {
                            S21SsmLowLevelCodingClient.closeResource(invChkStmtSelect, invChkRs);
                        }
                    }
                }
            }

            if (NFCConst.CST_FLAG_ON.equals(this.initArSoNumMatchFlg)) {
                if (inv30NumList.size() == 0) {
                    PreparedStatement invBolChkStmtSelect = null;
                    ResultSet invBolChkRs = null;

                    try {
                        S21SsmExecutionParameter invBolChkExecParam = new S21SsmExecutionParameter();

                        Map<String, String> invBolChkQueryParam = new HashMap<String, String>();
                        invBolChkQueryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
                        invBolChkQueryParam.put(NFCDbConst.SO_NUM_J, inv30Num);
                        invBolChkQueryParam.put("arCashApplyStsCd01", AR_CASH_APPLY_STS.UNAPPLIED);
                        invBolChkQueryParam.put("arCashApplyStsCd02", AR_CASH_APPLY_STS.PARTIAL);
                        invBolChkQueryParam.put(NFCDbConst.EXPT_FLG_J, exptFlg);

                        invBolChkStmtSelect = this.ssmLLClient.createPreparedStatement("getArInvBolInfoFromInvChk", invBolChkQueryParam, invBolChkExecParam);
                        invBolChkRs = invBolChkStmtSelect.executeQuery();

                        if (invBolChkRs.first()) {
                            inv30NumList.add(convStr(invBolChkRs.getString(NFCDbConst.INV_NUM)));
                        } else {
                            isErrorMode(ERROR_MODE_ON_OK);
                            debugLog("ERROR MODE SET : ERROR_MODE_ON_OK : There is no INV_30_NUM NULL in INV_BOL.");
                        }

                    } catch (SQLException e) {
                        sqlExceptionHandler(e);
                    } finally {
                        S21SsmLowLevelCodingClient.closeResource(invBolChkStmtSelect, invBolChkRs);
                    }
                }
            }
        }

        if (inv30NumList.size() == 1) {
            this.insertArRcptInProcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).invNum.setValue(inv30NumList.get(FIRST_REC_INDEX));
            this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).invNum.setValue(inv30NumList.get(FIRST_REC_INDEX));

            if (NFCConst.CST_GRP_INV_FLG_GRP_OFF.equals(this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).grpInvFlg.getValue())) {
                PreparedStatement arTrxBalStmtSelect = null;
                ResultSet arTrxBalRs = null;

                try {
                    S21SsmExecutionParameter arTrxBalExecParam = new S21SsmExecutionParameter();

                    Map<String, String> arTrxBalQueryParam = new HashMap<String, String>();
                    arTrxBalQueryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
                    arTrxBalQueryParam.put(NFCDbConst.INV_30_NUM_J, inv30NumList.get(FIRST_REC_INDEX));
                    // START 2019/05/08 [QC#50140, ADD]
                    // START 2020/01/14 [QC#55180, MOD]
                    //invChkQueryParam.put("arTrxTpCd", AR_TRX_TP.RECEIPT);
                    arTrxBalQueryParam.put("rcp", AR_TRX_TP.RECEIPT);
                    arTrxBalQueryParam.put("acc", AR_TRX_TP.ON_ACCOUNT);
                    // END   2020/01/14 [QC#55180, MOD]
                    // END   2019/05/08 [QC#50140, ADD]

                    arTrxBalStmtSelect = this.ssmLLClient.createPreparedStatement("getArTrxBal", arTrxBalQueryParam, arTrxBalExecParam);
                    arTrxBalRs = arTrxBalStmtSelect.executeQuery();

                    int dbGetSize = 0;
                    while (arTrxBalRs.next()) {
                        dbGetSize++;
                    }

                    if (dbGetSize == 1) {
                        arTrxBalRs.first();
                        setTrxBal(arRcptRcvWrkTMsgListIndex, arTrxBalRs.getBigDecimal(NFCDbConst.DEAL_RMNG_BAL_GRS_AMT), convStr(arTrxBalRs.getString(NFCDbConst.AR_TRX_TP_CD)), arTrxBalRs.getBigDecimal(NFCDbConst.AR_TRX_BAL_PK),
                                convStr(arTrxBalRs.getString(NFCDbConst.EZUPTIME)), convStr(arTrxBalRs.getString(NFCDbConst.EZUPTIMEZONE)));
                        setPayer(arRcptRcvWrkTMsgListIndex, convStr(arTrxBalRs.getString(NFCDbConst.PAYER_CUST_CD)));
                    } else {
                        isErrorMode(ERROR_MODE_ON_OK);
                        debugLog("ERROR MODE SET : ERROR_MODE_ON_OK : GET SIZE NOT 1 INV_30_NUM NULL in AR_TRX_BAL.");
                    }

                } catch (SQLException e) {
                    sqlExceptionHandler(e);
                } finally {
                    S21SsmLowLevelCodingClient.closeResource(arTrxBalStmtSelect, arTrxBalRs);
                }

            } else {
                setTrxBal(arRcptRcvWrkTMsgListIndex, NFCConst.CST_DB_INIT_VAL_NUM, NFCConst.CST_DB_INIT_VAL_STR, NFCConst.CST_DB_INIT_VAL_NUM, NFCConst.CST_DB_INIT_VAL_STR, NFCConst.CST_DB_INIT_VAL_STR);
            }
        } else {
            isErrorMode(ERROR_MODE_ON_OK);
            this.insertArRcptInProcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).invNum.setValue(convStr(this.forSaveArRcptRcvWrkTMsgList.get(arRcptRcvWrkTMsgListIndex).inv30Num.getValue()));
            this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).invNum.setValue(this.forSaveArRcptRcvWrkTMsgList.get(arRcptRcvWrkTMsgListIndex).inv30Num.getValue());
            setTrxBal(arRcptRcvWrkTMsgListIndex, NFCConst.CST_DB_INIT_VAL_NUM, NFCConst.CST_DB_INIT_VAL_STR, NFCConst.CST_DB_INIT_VAL_NUM, NFCConst.CST_DB_INIT_VAL_STR, NFCConst.CST_DB_INIT_VAL_STR);
            this.insertArRcptInProcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).payerCustCd.setValue(this.initPayerCustCd);
            debugLog("ERROR MODE SET : ERROR_MODE_ON_OK : INV_30_NUM MORE.");
        }
        inv30NumList.clear();

    }

    // for North America(CSA) 2015/04/06 Add Start
    /**
     * 4.Check related to INVOICE for North America(CSA)
     * @param arRcptRcvWrkTMsgListIndex int
     * @param inv30Num String
     * @param payerCustCd String
     */
    private void validityCheckOfGrpInvouceForNA(int arRcptRcvWrkTMsgListIndex, String inv30Num, String inv30OrigNum, String exptFlg) {

        PreparedStatement invChkStmtSelect = null;
        ResultSet invChkRs = null;

        if ((S21StringUtil.isEmpty(inv30Num))) {
            this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).grpInvFlg.setValue(NFCConst.CST_GRP_INV_FLG_GRP_OFF);
            setTrxBal(arRcptRcvWrkTMsgListIndex, NFCConst.CST_DB_INIT_VAL_NUM, NFCConst.CST_DB_INIT_VAL_STR, NFCConst.CST_DB_INIT_VAL_NUM, NFCConst.CST_DB_INIT_VAL_STR, NFCConst.CST_DB_INIT_VAL_STR);
            this.putGrpInvInfoToMap(this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).rcptInProcSqPk.getValue(), this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).rcvHdrNum.getValue(), this.insertArApplyIntfcWrkTMsg
                    .get(arRcptRcvWrkTMsgListIndex).rcvDtlNum.getValue(), this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).grpInvFlg.getValue());
            this.isRcptOnlyFlg = true;
            return;

        } else {
            try {
                S21SsmExecutionParameter invChkExecParam = new S21SsmExecutionParameter();

                Map<String, String> invChkQueryParam = new HashMap<String, String>();
                invChkQueryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
                invChkQueryParam.put(NFCDbConst.INV_NUM_J, inv30Num);
                invChkQueryParam.put("arCashApplyStsCd01", AR_CASH_APPLY_STS.UNAPPLIED);
                invChkQueryParam.put("arCashApplyStsCd02", AR_CASH_APPLY_STS.PARTIAL);
                invChkQueryParam.put(NFCDbConst.EXPT_FLG_J, exptFlg);
                // START 2019/05/08 [QC#50140, ADD]
                // START 2020/01/14 [QC#55180, MOD]
                //invChkQueryParam.put("arTrxTpCd", AR_TRX_TP.RECEIPT);
                invChkQueryParam.put("rcp", AR_TRX_TP.RECEIPT);
                invChkQueryParam.put("acc", AR_TRX_TP.ON_ACCOUNT);
                // END   2020/01/14 [QC#55180, MOD]
                // END   2019/05/08 [QC#50140, ADD]

                invChkStmtSelect = this.ssmLLClient.createPreparedStatement("getArInvInfoFromInvChk", invChkQueryParam, invChkExecParam);
                invChkRs = invChkStmtSelect.executeQuery();

                // check count
                invChkRs.last();
                if (invChkRs.getRow() == 1) {
                    this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).grpInvFlg.setValue(NFCConst.CST_GRP_INV_FLG_GRP_OFF);
                    setTrxBal(arRcptRcvWrkTMsgListIndex, this.insertArRcptInProcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).dealRcptDtlAmt.getValue(), convStr(invChkRs.getString(NFCDbConst.AR_TRX_TP_CD)), invChkRs
                            .getBigDecimal(NFCDbConst.AR_TRX_BAL_PK), convStr(invChkRs.getString(NFCDbConst.EZUPTIME)), convStr(invChkRs.getString(NFCDbConst.EZUPTIMEZONE)));
                    this.putGrpInvInfoToMap(this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).rcptInProcSqPk.getValue(), this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).rcvHdrNum.getValue(),
                            this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).rcvDtlNum.getValue(), this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).grpInvFlg.getValue());
                    return;
                }
            } catch (SQLException e) {
                sqlExceptionHandler(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(invChkStmtSelect, invChkRs);
            }
        }

        if (inv30OrigNum == null) {
            inv30OrigNum = inv30Num;
        } else if (!ZYPCommonFunc.hasValue(inv30OrigNum)) {
            inv30OrigNum = inv30Num;
        }

        List<String> inv30NumList = new ArrayList<String>();
        if (S21StringUtil.isEmpty(this.initArSoNumMatchFlg) || NFCConst.CST_FLAG_OFF.equals(this.initArSoNumMatchFlg)) {
            PreparedStatement stmtSelect = null;
            ResultSet rs = null;
            try {
                S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

                Map<String, String> queryParam = new HashMap<String, String>();
                queryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
                queryParam.put(NFCDbConst.INV_NUM_J, inv30Num);

                stmtSelect = this.ssmLLClient.createPreparedStatement("getArInvInfoFromGrpInvChk", queryParam, execParam);
                rs = stmtSelect.executeQuery();

                if (rs.first()) {
                    this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).grpInvFlg.setValue(NFCConst.CST_GRP_INV_FLG_GRP_ON);
                    inv30NumList.add(inv30Num);
                    setPayer(arRcptRcvWrkTMsgListIndex, convStr(rs.getString(NFCDbConst.PAYER_CUST_CD)));
                } else {
                    this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).grpInvFlg.setValue(NFCConst.CST_GRP_INV_FLG_GRP_OFF);
                }
            } catch (SQLException e) {
                sqlExceptionHandler(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            }
        } else {
            this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).grpInvFlg.setValue(NFCConst.CST_GRP_INV_FLG_GRP_OFF);
        }

        this.putGrpInvInfoToMap(this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).rcptInProcSqPk.getValue(), this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).rcvHdrNum.getValue(), this.insertArApplyIntfcWrkTMsg
                .get(arRcptRcvWrkTMsgListIndex).rcvDtlNum.getValue(), this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).grpInvFlg.getValue());

        if (NFCConst.CST_GRP_INV_FLG_GRP_OFF.equals(this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).grpInvFlg.getValue())) {

            if (inv30NumList.size() == 0) {

                inv30NumList.clear();

                PreparedStatement trxChkStmtSelect = null;
                ResultSet trxChkRs = null;

                try {
                    S21SsmExecutionParameter trxChkExecParam = new S21SsmExecutionParameter();

                    Map<String, String> invChkQueryParam = new HashMap<String, String>();
                    invChkQueryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
                    invChkQueryParam.put(NFCDbConst.AR_CUST_REF_NUM_J, inv30OrigNum);
                    invChkQueryParam.put("arCashApplyStsCd01", AR_CASH_APPLY_STS.UNAPPLIED);
                    invChkQueryParam.put("arCashApplyStsCd02", AR_CASH_APPLY_STS.PARTIAL);
                    invChkQueryParam.put(NFCDbConst.EXPT_FLG_J, exptFlg);
                    // START 2019/05/08 [QC#50140, ADD]
                    // START 2020/01/14 [QC#55180, MOD]
                    //invChkQueryParam.put("arTrxTpCd", AR_TRX_TP.RECEIPT);
                    invChkQueryParam.put("rcp", AR_TRX_TP.RECEIPT);
                    invChkQueryParam.put("acc", AR_TRX_TP.ON_ACCOUNT);
                    // END   2020/01/14 [QC#55180, MOD]
                    // END   2019/05/08 [QC#50140, ADD]

                    trxChkStmtSelect = this.ssmLLClient.createPreparedStatement("getArTrxBalFromInvChk", invChkQueryParam, trxChkExecParam);
                    trxChkRs = trxChkStmtSelect.executeQuery();
                    int dbGetSize = 0;
                    while (trxChkRs.next()) {
                        dbGetSize++;
                    }
                    if (dbGetSize == 1) {
                        trxChkRs.first();
                        inv30NumList.add(convStr(trxChkRs.getString(NFCDbConst.AR_TRX_NUM)));
                    }
                } catch (SQLException e) {
                    sqlExceptionHandler(e);
                } finally {
                    S21SsmLowLevelCodingClient.closeResource(trxChkStmtSelect, trxChkRs);
                }
            }

            if (inv30NumList.size() == 0) {
                PreparedStatement invNumEditStmtSelect = null;
                ResultSet invNumEditRs = null;

                try {
                    S21SsmExecutionParameter invNumEditExecParam = new S21SsmExecutionParameter();

                    Map<String, String> invNumEditQueryParam = new HashMap<String, String>();
                    invNumEditQueryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);

                    invNumEditStmtSelect = this.ssmLLClient.createPreparedStatement("getArEdiInvNumEdit", invNumEditQueryParam, invNumEditExecParam);
                    invNumEditRs = invNumEditStmtSelect.executeQuery();

                    while (invNumEditRs.next()) {
                        String findItem = NFCConst.CST_DB_INIT_VAL_STR;
                        if (NFCConst.CST_INV_NUM_EDIT_PROC_TP_CD_ADD_HEAD.equals(convStr(invNumEditRs.getString(NFCDbConst.INV_NUM_EDIT_PROC_TP_CD)))) {
                            findItem = convStr(invNumEditRs.getString(NFCDbConst.ADD_CHAR_TXT)) + inv30Num;
                        }
                        if (NFCConst.CST_INV_NUM_EDIT_PROC_TP_CD_DEL_FORWARD.equals(convStr(invNumEditRs.getString(NFCDbConst.INV_NUM_EDIT_PROC_TP_CD)))) {
                            // START 2019/03/22 T.Ogura [QC#30565,MOD]
//                            findItem = inv30Num.substring(invNumEditRs.getInt(NFCDbConst.DEL_CHAR_CNT));
                            if (inv30Num.length() > invNumEditRs.getInt(NFCDbConst.DEL_CHAR_CNT)) {
                                findItem = inv30Num.substring(invNumEditRs.getInt(NFCDbConst.DEL_CHAR_CNT));
                            }
                            // END   2019/03/22 T.Ogura [QC#30565,MOD]
                        }
                        if (NFCConst.CST_INV_NUM_EDIT_PROC_TP_CD_DEL_REAR_SIDE.equals(convStr(invNumEditRs.getString(NFCDbConst.INV_NUM_EDIT_PROC_TP_CD)))) {
                            int editNum = 0;
                            if (!NFCConst.CST_DB_INIT_VAL_STR.equals(convStr(invNumEditRs.getString(NFCDbConst.ADD_CHAR_TXT)))) {
                                findItem = convStr(invNumEditRs.getString(NFCDbConst.ADD_CHAR_TXT)) + inv30Num;
                                editNum = findItem.length();
                            } else {
                                editNum = inv30Num.length();
                                findItem = inv30Num;
                            }
                            // START 2019/03/22 T.Ogura [QC#30565,MOD]
//                            editNum = editNum - invNumEditRs.getInt(NFCDbConst.DEL_CHAR_CNT);
//                            findItem = findItem.substring(0, editNum);
                            if (editNum > invNumEditRs.getInt(NFCDbConst.DEL_CHAR_CNT)) {
                                editNum = editNum - invNumEditRs.getInt(NFCDbConst.DEL_CHAR_CNT);
                                findItem = findItem.substring(0, editNum);
                            }
                            // END   2019/03/22 T.Ogura [QC#30565,MOD]
                        }

                        invChkStmtSelect = null;
                        invChkRs = null;

                        try {
                            S21SsmExecutionParameter invChkExecParam = new S21SsmExecutionParameter();

                            Map<String, String> invChkQueryParam = new HashMap<String, String>();
                            invChkQueryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
                            invChkQueryParam.put(NFCDbConst.INV_NUM_J, findItem);
                            invChkQueryParam.put("arCashApplyStsCd01", AR_CASH_APPLY_STS.UNAPPLIED);
                            invChkQueryParam.put("arCashApplyStsCd02", AR_CASH_APPLY_STS.PARTIAL);
                            invChkQueryParam.put(NFCDbConst.EXPT_FLG_J, exptFlg);
                            // START 2019/05/08 [QC#50140, ADD]
                            // START 2020/01/14 [QC#55180, MOD]
                            //invChkQueryParam.put("arTrxTpCd", AR_TRX_TP.RECEIPT);
                            invChkQueryParam.put("rcp", AR_TRX_TP.RECEIPT);
                            invChkQueryParam.put("acc", AR_TRX_TP.ON_ACCOUNT);
                            // END   2020/01/14 [QC#55180, MOD]
                            // END   2019/05/08 [QC#50140, ADD]

                            invChkStmtSelect = this.ssmLLClient.createPreparedStatement("getArInvInfoFromInvChk", invChkQueryParam, invChkExecParam);
                            invChkRs = invChkStmtSelect.executeQuery();

                            while (invChkRs.next()) {
                                if (findItem.equals(convStr(invChkRs.getString(NFCDbConst.INV_NUM)))) {
                                    inv30NumList.add(convStr(invChkRs.getString(NFCDbConst.INV_NUM)));
                                }
                            }
                        } catch (SQLException e) {
                            sqlExceptionHandler(e);
                        } finally {
                            S21SsmLowLevelCodingClient.closeResource(invChkStmtSelect, invChkRs);
                        }
                    }
                } catch (SQLException e) {
                    sqlExceptionHandler(e);
                } finally {
                    S21SsmLowLevelCodingClient.closeResource(invNumEditStmtSelect, invNumEditRs);
                }
            }

            if (!isUpload()) {
                if (inv30NumList.size() == 0) {
                    if (inv30Num.length() == CST_INV_NUM_LENGTH_SQS) {
                        invChkStmtSelect = null;
                        invChkRs = null;

                        try {
                            S21SsmExecutionParameter invChkExecParam = new S21SsmExecutionParameter();

                            Map<String, String> invChkQueryParam = new HashMap<String, String>();
                            invChkQueryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
                            invChkQueryParam.put(NFCDbConst.INV_NUM_J, inv30Num + "00");
                            invChkQueryParam.put("arCashApplyStsCd01", AR_CASH_APPLY_STS.UNAPPLIED);
                            invChkQueryParam.put("arCashApplyStsCd02", AR_CASH_APPLY_STS.PARTIAL);
                            invChkQueryParam.put(NFCDbConst.EXPT_FLG_J, exptFlg);
                            invChkQueryParam.put("sysSrcCdSQS", SYS_SRC.SQS);
                            invChkQueryParam.put("sysSrcCdSQS1", SYS_SRC.S21_SERVICE_AND_REPAIR);
                            invChkStmtSelect = this.ssmLLClient.createPreparedStatement("getArInvInfoFromInvChkSQS", invChkQueryParam, invChkExecParam);
                            invChkRs = invChkStmtSelect.executeQuery();

                            while (invChkRs.next()) {
                                inv30NumList.add(convStr(invChkRs.getString(NFCDbConst.INV_NUM)));
                            }
                        } catch (SQLException e) {
                            sqlExceptionHandler(e);
                        } finally {
                            S21SsmLowLevelCodingClient.closeResource(invChkStmtSelect, invChkRs);
                        }
                    }
                }
            }

            if (NFCConst.CST_FLAG_ON.equals(this.initArSoNumMatchFlg)) {
                if (inv30NumList.size() == 0) {
                    PreparedStatement invBolChkStmtSelect = null;
                    ResultSet invBolChkRs = null;

                    try {
                        S21SsmExecutionParameter invBolChkExecParam = new S21SsmExecutionParameter();

                        Map<String, String> invBolChkQueryParam = new HashMap<String, String>();
                        invBolChkQueryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
                        invBolChkQueryParam.put(NFCDbConst.SO_NUM_J, inv30Num);
                        invBolChkQueryParam.put("arCashApplyStsCd01", AR_CASH_APPLY_STS.UNAPPLIED);
                        invBolChkQueryParam.put("arCashApplyStsCd02", AR_CASH_APPLY_STS.PARTIAL);
                        invBolChkQueryParam.put(NFCDbConst.EXPT_FLG_J, exptFlg);

                        invBolChkStmtSelect = this.ssmLLClient.createPreparedStatement("getArInvBolInfoFromInvChk", invBolChkQueryParam, invBolChkExecParam);
                        invBolChkRs = invBolChkStmtSelect.executeQuery();

                        if (invBolChkRs.first()) {
                            inv30NumList.add(convStr(invBolChkRs.getString(NFCDbConst.INV_NUM)));
                        } else {
                            isErrorMode(ERROR_MODE_ON_OK);
                            debugLog("ERROR MODE SET : ERROR_MODE_ON_OK : There is no INV_30_NUM NULL in INV_BOL.");
                        }

                    } catch (SQLException e) {
                        sqlExceptionHandler(e);
                    } finally {
                        S21SsmLowLevelCodingClient.closeResource(invBolChkStmtSelect, invBolChkRs);
                    }
                }
            }
        }

        if (inv30NumList.size() == 1) {
            this.insertArRcptInProcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).invNum.setValue(inv30NumList.get(FIRST_REC_INDEX));
            this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).invNum.setValue(inv30NumList.get(FIRST_REC_INDEX));

            if (NFCConst.CST_GRP_INV_FLG_GRP_OFF.equals(this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).grpInvFlg.getValue())) {
                PreparedStatement arTrxBalStmtSelect = null;
                ResultSet arTrxBalRs = null;

                try {
                    S21SsmExecutionParameter arTrxBalExecParam = new S21SsmExecutionParameter();

                    Map<String, String> arTrxBalQueryParam = new HashMap<String, String>();
                    arTrxBalQueryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
                    arTrxBalQueryParam.put(NFCDbConst.INV_30_NUM_J, inv30NumList.get(FIRST_REC_INDEX));
                    // START 2019/05/08 [QC#50140, ADD]
                    // START 2020/01/14 [QC#55180, MOD]
                    //invChkQueryParam.put("arTrxTpCd", AR_TRX_TP.RECEIPT);
                    arTrxBalQueryParam.put("rcp", AR_TRX_TP.RECEIPT);
                    arTrxBalQueryParam.put("acc", AR_TRX_TP.ON_ACCOUNT);
                    // END   2020/01/14 [QC#55180, MOD]
                    // END   2019/05/08 [QC#50140, ADD]

                    arTrxBalStmtSelect = this.ssmLLClient.createPreparedStatement("getArTrxBal", arTrxBalQueryParam, arTrxBalExecParam);
                    arTrxBalRs = arTrxBalStmtSelect.executeQuery();

                    int dbGetSize = 0;
                    while (arTrxBalRs.next()) {
                        dbGetSize++;
                    }

                    if (dbGetSize == 1) {
                        arTrxBalRs.first();
                        setTrxBal(arRcptRcvWrkTMsgListIndex, this.insertArRcptInProcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).dealRcptDtlAmt.getValue(), convStr(arTrxBalRs.getString(NFCDbConst.AR_TRX_TP_CD)), arTrxBalRs
                                .getBigDecimal(NFCDbConst.AR_TRX_BAL_PK), convStr(arTrxBalRs.getString(NFCDbConst.EZUPTIME)), convStr(arTrxBalRs.getString(NFCDbConst.EZUPTIMEZONE)));
                        setPayer(arRcptRcvWrkTMsgListIndex, convStr(arTrxBalRs.getString(NFCDbConst.PAYER_CUST_CD)));
                    } else {
                        isErrorMode(ERROR_MODE_ON_OK);
                        debugLog("ERROR MODE SET : ERROR_MODE_ON_OK : GET SIZE NOT 1 INV_30_NUM NULL in AR_TRX_BAL.");
                    }

                } catch (SQLException e) {
                    sqlExceptionHandler(e);
                } finally {
                    S21SsmLowLevelCodingClient.closeResource(arTrxBalStmtSelect, arTrxBalRs);
                }

            } else {
                setTrxBal(arRcptRcvWrkTMsgListIndex, NFCConst.CST_DB_INIT_VAL_NUM, NFCConst.CST_DB_INIT_VAL_STR, NFCConst.CST_DB_INIT_VAL_NUM, NFCConst.CST_DB_INIT_VAL_STR, NFCConst.CST_DB_INIT_VAL_STR);
            }
        } else {
            this.insertArRcptInProcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).invNum.setValue(convStr(this.forSaveArRcptRcvWrkTMsgList.get(arRcptRcvWrkTMsgListIndex).inv30Num.getValue()));
            this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).invNum.setValue(this.forSaveArRcptRcvWrkTMsgList.get(arRcptRcvWrkTMsgListIndex).inv30Num.getValue());
            setTrxBal(arRcptRcvWrkTMsgListIndex, NFCConst.CST_DB_INIT_VAL_NUM, NFCConst.CST_DB_INIT_VAL_STR, NFCConst.CST_DB_INIT_VAL_NUM, NFCConst.CST_DB_INIT_VAL_STR, NFCConst.CST_DB_INIT_VAL_STR);
            this.insertArRcptInProcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).payerCustCd.setValue(this.initPayerCustCd);
            this.isRcptOnlyFlg = true;
            // Def#5097
            this.insertArApplyIntfcWrkTMsg.get(arRcptRcvWrkTMsgListIndex).procStsCd.setValue(NFCConst.CST_PROC_STS_CD_RCPT_ONLY);
        }
        inv30NumList.clear();

    }
    // for North America(CSA) 2015/04/06 Add End

    /**
     * 5.Acquisition of PAYER from customer name identification master
     */
    private void getPayerFromCustNmDictionary() {

        for (AR_RCPT_IN_PROC_WRKTMsg tMsg : this.insertArRcptInProcWrkTMsg) {
            if (S21StringUtil.isEmpty(tMsg.payerCustCd.getValue())) {
                PreparedStatement arCustNmConvDictStmtSelect = null;
                ResultSet arCustNmConvDictRs = null;
                try {
                    S21SsmExecutionParameter arCustNmConvDictExecParam = new S21SsmExecutionParameter();

                    Map<String, String> arCustNmConvDictQueryParam = new HashMap<String, String>();
                    arCustNmConvDictQueryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
                    arCustNmConvDictQueryParam.put(NFCDbConst.EDI_RCV_CUST_NM_J, tMsg.ediRcvCustNm.getValue());

                    arCustNmConvDictStmtSelect = this.ssmLLClient.createPreparedStatement("getArCustNmConvDict", arCustNmConvDictQueryParam, arCustNmConvDictExecParam);
                    arCustNmConvDictRs = arCustNmConvDictStmtSelect.executeQuery();

                    if (arCustNmConvDictRs.first()) {
                        tMsg.payerCustCd.setValue(convStr(arCustNmConvDictRs.getString(NFCDbConst.BILL_TO_CUST_CD)));
                    }
                } catch (SQLException e) {
                    sqlExceptionHandler(e);
                } finally {
                    S21SsmLowLevelCodingClient.closeResource(arCustNmConvDictStmtSelect, arCustNmConvDictRs);
                }
            }
        }

    }

    /**
     * 6.The bank ID master's for EDI transmission PAYER check
     */
    private void validityCheckOfPayer() {

        for (AR_RCPT_IN_PROC_WRKTMsg tMsg : this.insertArRcptInProcWrkTMsg) {
            if (S21StringUtil.isNotEmpty(this.initPayerCustCd)) {  // In case of S21 CSA, initPayerCustCd will be empty, so this logic won't be executed.
                PreparedStatement billToCustStmtSelect = null;
                ResultSet billToCustRs = null;
                try {
                    S21SsmExecutionParameter billToCustExecParam = new S21SsmExecutionParameter();

                    Map<String, String> billToCustQueryParam = new HashMap<String, String>();
                    billToCustQueryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
                    billToCustQueryParam.put(NFCDbConst.BILL_TO_CUST_CD_J, this.initPayerCustCd);
                    billToCustQueryParam.put(NFCDbConst.RGSTN_STS_CD_J, arRgtnStsCd);

                    // 2015/12/16 change to SELL_TO_CUST
                    billToCustStmtSelect = this.ssmLLClient.createPreparedStatement("getDsAcctCust", billToCustQueryParam, billToCustExecParam);
                    // end 2015/12/16
                    
                    billToCustRs = billToCustStmtSelect.executeQuery();

                    if (billToCustRs.first()) {
                        tMsg.payerCustCd.setValue(convStr(billToCustRs.getString(NFCDbConst.BILL_TO_CUST_CD)));
                    } else {
                        this.validityCheckOfPayerFlg = NFCConst.CST_FLAG_ON;
                    }
                } catch (SQLException e) {
                    sqlExceptionHandler(e);
                } finally {
                    S21SsmLowLevelCodingClient.closeResource(billToCustStmtSelect, billToCustRs);
                }
            } else {
                if (S21StringUtil.isNotEmpty(tMsg.payerCustCd.getValue())) {
                    PreparedStatement billToCustStmtSelect = null;
                    ResultSet billToCustRs = null;
                    try {
                        S21SsmExecutionParameter billToCustExecParam = new S21SsmExecutionParameter();

                        Map<String, String> billToCustQueryParam = new HashMap<String, String>();
                        billToCustQueryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
                        billToCustQueryParam.put(NFCDbConst.BILL_TO_CUST_CD_J, tMsg.payerCustCd.getValue());
                        billToCustQueryParam.put(NFCDbConst.RGSTN_STS_CD_J, arRgtnStsCd);

                        // 2015/12/16 change to SELL_TO_CUST
                        billToCustStmtSelect = this.ssmLLClient.createPreparedStatement("getDsAcctCust", billToCustQueryParam, billToCustExecParam);
                        // end 2015/12/16
                        
                        billToCustRs = billToCustStmtSelect.executeQuery();

                        if (billToCustRs.first()) {
                            tMsg.payerCustCd.setValue(convStr(billToCustRs.getString(NFCDbConst.BILL_TO_CUST_CD)));
                        } else {
                            this.validityCheckOfPayerFlg = NFCConst.CST_FLAG_ON;
                        }
                    } catch (SQLException e) {
                        sqlExceptionHandler(e);
                    } finally {
                        S21SsmLowLevelCodingClient.closeResource(billToCustStmtSelect, billToCustRs);
                    }
                }
            }
        }

    }

    /**
     * 
     * 
     */
    private void theFinalCheckOfPayer() {
        debugLog("theFinalCheckOfPayer START");

        List<String> chkPayerList = new ArrayList<String>();

        String payer = NFCConst.CST_DB_INIT_VAL_STR;

        for (AR_RCPT_IN_PROC_WRKTMsg tMsg : this.insertArRcptInProcWrkTMsg) {
            payer = tMsg.payerCustCd.getValue();
            debugLog("PAYER =< " + payer + " >");
            if (0 > chkPayerList.indexOf(payer)) {
                chkPayerList.add(payer);
            }
        }

        if (chkPayerList.size() == 1) {
            if (0 <= chkPayerList.indexOf(NFCConst.CST_DB_INIT_VAL_STR)) {
                isErrorMode(ERROR_MODE_ON_OK);
                debugLog("ERROR MODE SET : ERROR_MODE_ON_OK : PAYER ALL SPACE");
            }
        } else {
            if (chkPayerList.size() == 2) {
                if (0 <= chkPayerList.indexOf(NFCConst.CST_DB_INIT_VAL_STR)) {
                    payerBuries();
                } else {
                    isErrorMode(ERROR_MODE_ON_OK);
                    debugLog("ERROR MODE SET : ERROR_MODE_ON_OK : PAYER 2 PIECES");
                    payerClear();
                }
            } else {
                isErrorMode(ERROR_MODE_ON_OK);
                debugLog("ERROR MODE SET : ERROR_MODE_ON_OK : PAYER 2 PIECES OR MORE");
                payerClear();
            }
        }

        debugLog("theFinalCheckOfPayer END");
    }

    // for North America(CSA) 2015/04/06 Add Start
    /**
     * overwrite Payer by "AR_RCPT_RCV_WRK.PAYER_CUST_CD"
     */
    private void overwriteOfPayer() {
        debugLog("overwriteOfPayer START");

        // check Each List Size
        if (this.forSaveArRcptRcvWrkTMsgList.size() != this.insertArRcptInProcWrkTMsg.size()) {
            return;
        } else if (this.forSaveArRcptRcvWrkTMsgList.size() != this.insertArApplyIntfcWrkTMsg.size()) {
            return;
        }

        for (int index = 0; index < this.forSaveArRcptRcvWrkTMsgList.size(); index++) {
            String payer = this.forSaveArRcptRcvWrkTMsgList.get(index).payerCustCd.getValue();
            // 2015/12/16 mod. Even if payer customer code of AR_RCPT_RCV_WRK is null, overwrite the code by it.
            /*if (!ZYPCommonFunc.hasValue(payer)) {
                continue;
            }
            */
            // 2015/12/16

            // overwrite Payer
            this.insertArRcptInProcWrkTMsg.get(index).payerCustCd.setValue(payer);
            this.insertArApplyIntfcWrkTMsg.get(index).payerCustCd.setValue(payer);
        }

        debugLog("overwriteOfPayer END");
    }
    // for North America(CSA) 2015/04/06 Add End

    /**
     * 8.Edit of header information
     */
    private void editOfHeaderInformation() {

        int index = 0;
        for (AR_RCPT_IN_PROC_WRKTMsg tMsg : this.insertArRcptInProcWrkTMsg) {
            if (S21StringUtil.isNotEmpty(tMsg.payerCustCd.getValue())) {
                if (NFCConst.CST_FLAG_OFF.equals(this.validityCheckOfPayerFlg)) {
                    PreparedStatement custCrPrflStmtSelect = null;
                    ResultSet custCrPrflRs = null;
                    try {
                        S21SsmExecutionParameter custCrPrflExecParam = new S21SsmExecutionParameter();

                        Map<String, String> custCrPrflQueryParam = new HashMap<String, String>();
                        custCrPrflQueryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
                        custCrPrflQueryParam.put(NFCDbConst.BILL_TO_CUST_CD_J, tMsg.payerCustCd.getValue());

                        // 2015/12/16 mod. CR_MGR_PSN_CD to be retrieved from DS_ACCT_CR_PRFL
                        custCrPrflStmtSelect = this.ssmLLClient.createPreparedStatement("getAcctCrPrfl", custCrPrflQueryParam, custCrPrflExecParam);
                        // end 2015/12/16
                        
                        custCrPrflRs = custCrPrflStmtSelect.executeQuery();

                        if (custCrPrflRs.first()) {
                            // tMsg.crAnlstPsnCd.setValue(convStr(custCrPrflRs.getString(NFCDbConst.CR_MGR_PSN_CD))); // DEL 2017/01/04 [QC#16867]
                            if (S21StringUtil.isEmpty(tMsg.dealCcyCd.getValue())) {
                                tMsg.dealCcyCd.setValue(convStr(custCrPrflRs.getString(NFCDbConst.CCY_CD)));
                            }
                        } else {
                            isErrorMode(ERROR_MODE_ON_OK);
                            debugLog("ERROR MODE SET : ERROR_MODE_ON_OK : PAYER does not exist in DS_ACCT_CR_PRFL.");
                        }
                    } catch (SQLException e) {
                        sqlExceptionHandler(e);
                    } finally {
                        S21SsmLowLevelCodingClient.closeResource(custCrPrflStmtSelect, custCrPrflRs);
                    }
                }
            }
            if (S21StringUtil.isEmpty(tMsg.dealCcyCd.getValue())) {
                GLBL_CMPYTMsg glblCmpyT = (GLBL_CMPYTMsg) ZYPCodeDataUtil.findByCode(GLBL_CMPY.class, this.globalCompanyCode, this.globalCompanyCode);
                tMsg.dealCcyCd.setValue(glblCmpyT.stdCcyCd.getValue());
            }
            if (!NFCConst.CST_CREATE_METH_TP_CD_CSV.equals(this.forSaveArRcptRcvWrkTMsgList.get(index).upldCratMethTpCd.getValue())) {
                String compAcctDt = this.initAcctDt.substring(BIGIN_INDEX, END_INDEX);
                if (this.depDt01CheckResult) {
                    String rcvWrkDepDt01 = this.forSaveArRcptRcvWrkTMsgList.get(index).depDt_01.getValue();
                    if (rcvWrkDepDt01.compareTo(this.batProcDate) > 0) {
                        tMsg.glDt.setValue(this.batProcDate);
                        isErrorMode(ERROR_MODE_ON_NG);
                        debugLog("ERROR MODE SET : ERROR_MODE_ON_NG : GL_DT Err.");
                    } else {
                        String compDepDt01 = rcvWrkDepDt01.substring(BIGIN_INDEX, END_INDEX);
                        if (compAcctDt.compareTo(compDepDt01) > 0) {
                            tMsg.glDt.setValue(this.initAcctDt);
                        } else {
                            tMsg.glDt.setValue(rcvWrkDepDt01);
                        }
                    }
                }
                String rcvWrkRcvDt = this.forSaveArRcptRcvWrkTMsgList.get(index).rcvDt.getValue();
                if (rcvWrkRcvDt.compareTo(this.batProcDate) > 0) {
                    tMsg.rcptDt.setValue(this.batProcDate);
                    isErrorMode(ERROR_MODE_ON_NG);
                    debugLog("ERROR MODE SET : ERROR_MODE_ON_NG : RCPT_DT Err.");
                } else {
                    String compRcvDt = rcvWrkRcvDt.substring(BIGIN_INDEX, END_INDEX);
                    if (compAcctDt.compareTo(compRcvDt) > 0) {
                        tMsg.rcptDt.setValue(this.initAcctDt);
                    } else {
                        tMsg.rcptDt.setValue(rcvWrkRcvDt);
                    }
                }
            }
            index++;
        }

    }

    /**
     * 9.Amount of money rounding
     */
    private void pntDigit() {

        PreparedStatement arCcyCtrlStmtSelect = null;
        ResultSet arCcyCtrlRs = null;
        try {
            S21SsmExecutionParameter arCcyCtrlExecParam = new S21SsmExecutionParameter();

            Map<String, String> arCcyCtrlQueryParam = new HashMap<String, String>();
            arCcyCtrlQueryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.globalCompanyCode);
            arCcyCtrlQueryParam.put(NFCDbConst.CCY_CD_J, this.insertArRcptInProcWrkTMsg.get(FIRST_REC_INDEX).dealCcyCd.getValue());

            arCcyCtrlStmtSelect = this.ssmLLClient.createPreparedStatement("getArCcyCtrl", arCcyCtrlQueryParam, arCcyCtrlExecParam);
            arCcyCtrlRs = arCcyCtrlStmtSelect.executeQuery();

            if (arCcyCtrlRs.first()) {
                BigDecimal chkAmt = this.insertArRcptInProcWrkTMsg.get(FIRST_REC_INDEX).dealRcptAmt.getValue();
                BigDecimal chkAmt2 = this.insertArRcptInProcWrkTMsg.get(FIRST_REC_INDEX).dealRcptAmt.getValue().setScale(0, RoundingMode.FLOOR);
                if (chkAmt.compareTo(chkAmt2) != 0) {
                    chkAmt = chkAmt.setScale(arCcyCtrlRs.getInt(NFCDbConst.AFT_DECL_PNT_DIGIT_NUM), RoundingMode.FLOOR);
                } else {
                    chkAmt = chkAmt2;
                }
                for (AR_RCPT_IN_PROC_WRKTMsg tMsg : this.insertArRcptInProcWrkTMsg) {
                    BigDecimal chkRcptTotAmt = tMsg.dealRcptDtlAmt.getValue();
                    BigDecimal chkRcptTotAmt2 = tMsg.dealRcptDtlAmt.getValue().setScale(0, RoundingMode.FLOOR);
                    if (chkRcptTotAmt.compareTo(chkRcptTotAmt2) != 0) {
                        chkRcptTotAmt = chkRcptTotAmt.setScale(arCcyCtrlRs.getInt(NFCDbConst.AFT_DECL_PNT_DIGIT_NUM), RoundingMode.FLOOR);
                    } else {
                        chkRcptTotAmt = chkRcptTotAmt2;
                    }
                    tMsg.dealRcptAmt.setValue(chkAmt);
                    tMsg.dealRcptRmngBalAmt.setValue(chkAmt);
                    tMsg.dealRcptDtlAmt.setValue(chkRcptTotAmt);
                }
            } else {
                isErrorMode(ERROR_MODE_ON_OK);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(arCcyCtrlStmtSelect, arCcyCtrlRs);
        }
    }

    /**
     * 10.Registration processing
     */
    private void registrationProcessing() {

        if (isUpload()) {
            generatInsertArRcptInProcWrkTMsgForUpld();
        } else {
            generatInsertArRcptInProcWrkTMsg();
        }

        BigDecimal headerPaymentTotal = BigDecimal.ZERO;
        BigDecimal detailPaymentTotal = BigDecimal.ZERO;
        for (AR_RCPT_IN_PROC_WRKTMsg headerPaymentTotalTMsg : this.insertArRcptInProcWrkTMsg) {
            headerPaymentTotal = headerPaymentTotalTMsg.dealRcptAmt.getValue();
            detailPaymentTotal = detailPaymentTotal.add(headerPaymentTotalTMsg.dealRcptDtlAmt.getValue());
        }

        boolean differenceFlg = false;
        if (headerPaymentTotal.compareTo(detailPaymentTotal) != 0) {
            int lastIndex = this.insertArRcptInProcWrkTMsg.size();
            lastIndex--;
            BigDecimal differenceAmt = BigDecimal.ZERO;
            differenceAmt = headerPaymentTotal.subtract(detailPaymentTotal);
            addArRcptInProcWrkTMsg(this.insertArRcptInProcWrkTMsg.get(lastIndex), 1, differenceAmt);
            addArApplyIntfcWrkTMsg(lastIndex, this.insertArRcptInProcWrkTMsg.get(lastIndex), 1);
        } else {
            differenceFlg = true;
        }

        String batNum = NFCConst.CST_DB_INIT_VAL_STR;
        // START 2018/06/25 H.ikeda [QC#25731,ADD]
        List<AR_RCPT_IN_PROC_WRKTMsg> insertList = new ArrayList<AR_RCPT_IN_PROC_WRKTMsg>();
        // END   2018/06/25 H.Ikeda [QC#25731,ADD]
        for (AR_RCPT_IN_PROC_WRKTMsg insertTMsg : this.insertArRcptInProcWrkTMsg) {
            if (isUpload()) {
                batNum = CST_BAT_NUM_CSV_UPLOAD + String.format(CST_BAT_SEQ_NUM_FORMAT_CHAR, this.seterRcptBatSqNum);
                insertTMsg.rcptBatNum.setValue(batNum.substring(0, CST_BAT_NUM_LENGTH));
                insertTMsg.rcptBatSqNum.setValue(batNum.substring(CST_BAT_NUM_LENGTH));

            } else {
                if (NFCConst.CST_DB_INIT_VAL_STR.equals(insertTMsg.rcptBatSqNum.getValue())) {
                    // ediBatNum will be blank in case of NA since getArEdiSendBank won't be executed.
                    if (isNaFlg) {
                        ediBatNum = CST_BAT_NUM_MAKING_UP_CHAR;
                    }
                    batNum = ediBatNum.substring(0, 2) + String.format(CST_BAT_SEQ_NUM_FORMAT_CHAR, this.seterRcptBatSqNum);
                    insertTMsg.rcptBatNum.setValue(batNum.substring(0, CST_BAT_NUM_LENGTH));
                    insertTMsg.rcptBatSqNum.setValue(batNum.substring(CST_BAT_NUM_LENGTH));
                }
            }
            ZYPEZDItemValueSetter.setValue(insertTMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);
            // START 2018/06/25 H.ikeda [QC#25731,MOD]
            insertList.add(insertTMsg);
            if (insertList.size() >= commitCnt) {
                S21FastTBLAccessor.insert(insertList.toArray(new AR_RCPT_IN_PROC_WRKTMsg[insertList.size()]));
                insertList.clear();
            }
            //EZDTBLAccessor.insert(insertTMsg);
            //if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(insertTMsg.getReturnCode())) {
            //    setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            //    throw new S21AbendException(NFCM0532E, TBL_NAME_AR_RCPT_IN_PROC_WRK);
            //}
        }
        if (insertList.size() != 0) {
            S21FastTBLAccessor.insert(insertList.toArray(new AR_RCPT_IN_PROC_WRKTMsg[insertList.size()]));
            insertList.clear();
        }
        // END   2018/06/25 H.Ikeda [QC#25731,MOD]
        this.seterRcptBatSqNum++;

        if (!ERROR_MODE_ON_NG.equals(this.errFlg)) {
            for (int insertIndex = 0; insertIndex < this.insertArRcptInProcWrkTMsg.size(); insertIndex++) {
                generatArApplyIntfcWrkTMsg(this.insertArRcptInProcWrkTMsg.get(insertIndex), insertIndex, differenceFlg);
            }
            // START 2018/06/25 H.ikeda [QC#25731,MOD]
            List<AR_APPLY_INTFC_WRKTMsg> insList = new ArrayList<AR_APPLY_INTFC_WRKTMsg>(); 
            // END   2018/06/25 H.ikeda [QC#25731,MOD]
            for (AR_APPLY_INTFC_WRKTMsg insertTMsg : this.insertArApplyIntfcWrkTMsg) {
                insList.add(insertTMsg);
                if (insList.size() >= commitCnt) {
                    S21FastTBLAccessor.insert(insList.toArray(new AR_APPLY_INTFC_WRKTMsg[insList.size()]));
                    insList.clear();
                }
                //EZDTBLAccessor.insert(insertTMsg);
                //if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(insertTMsg.getReturnCode())) {
                //    setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
                //    throw new S21AbendException(NFCM0532E, TBL_NAME_AR_APPLY_INTFC_WRK);
                //}
            }
            if (insList.size() >= 0) {
                S21FastTBLAccessor.insert(insList.toArray(new AR_APPLY_INTFC_WRKTMsg[insList.size()]));
                insList.clear();
            }
        }
    }

    /**
     * 12.Renewal of AR_RCPT_RCV_WRK
     */
    private void upDateArRcptRcvWrk() {

        List<AR_RCPT_RCV_WRKTMsg> updateList = new ArrayList<AR_RCPT_RCV_WRKTMsg>();
        for (AR_RCPT_RCV_WRKTMsg tMsg : this.forSaveArRcptRcvWrkTMsgList) {
            AR_RCPT_RCV_WRKTMsg target = (AR_RCPT_RCV_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
            if (target == null) {
                this.errCnt++;
                setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
                throw new S21AbendException(NFVM0608E);
            }

            target.ezCancelFlag.setValue(CST_EZ_CANCEL_FLAG);
            target.ezInCompanyCode.setValue(CST_EZ_IN_COMPANY_CODE);
            target.rcvStsCd.setValue(NFCConst.CST_RCV_STS_CD_PROC);
            // START 2018/06/25 H.ikeda [QC#25731,MOD]
            updateList.add(target);
            if (updateList.size() >= commitCnt) {
                S21FastTBLAccessor.update(updateList.toArray(new AR_RCPT_RCV_WRKTMsg[updateList.size()]));
                updateList.clear();
            }
            //EZDTBLAccessor.update(target);
            //if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(target.getReturnCode())) {
            //    this.errCnt++;
            //    setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            //    throw new S21AbendException(NFVM0608E);
            //}
        }
        if (updateList.size() > 0) {
            S21FastTBLAccessor.update(updateList.toArray(new AR_RCPT_RCV_WRKTMsg[updateList.size()]));
            updateList.clear();
        }
        // END   2018/06/25 H.ikeda [QC#25731,MOD]
    }

    /**
     * Initialization of internal variable.
     */
    private void initializationOfInternalVariable() {

        isErrorMode(ERROR_MODE_OFF);
        debugLog("ERROR MODE SET : ERROR_MODE_OFF : RESET.");

        this.validityCheckOfPayerFlg = NFCConst.CST_FLAG_OFF;

        this.initUsrId = NFCConst.CST_DB_INIT_VAL_STR;
        this.initAcctDt = NFCConst.CST_DB_INIT_VAL_STR;
        this.initPayerCustCd = NFCConst.CST_DB_INIT_VAL_STR;
        this.initArBankAcctCd = NFCConst.CST_DB_INIT_VAL_STR;
        this.judgeInvRepetitionCode = NFCConst.DUP_ERR_CD_NOMAL;

        this.forSaveArRcptRcvWrkTMsgList.clear();
        this.insertArRcptInProcWrkTMsg.clear();
        this.insertArApplyIntfcWrkTMsg.clear();

    }

    /**
     * prior Processing
     * @param rs ResultSet
     * @throws SQLException
     */
    private void priorProcessing(ResultSet rs) throws SQLException {

        if ((S21StringUtil.isEmpty(this.initUsrId) && (S21StringUtil.isEmpty(this.initAcctDt)))) {
            if (isUpload()) {
                getBatProcDate();
                getArAcctDt();
                getVarCharConst();
            } else {
                getBatProcDate();

                // for North America(CSA) 2015/04/06 Mod Start
                if (!this.isNaFlg) {
                    getArEdiSendBank(convStr(rs.getString(NFCDbConst.ACCT_MA_CD)), convStr(rs.getString(NFCDbConst.BAT_NUM)));
                }
                // for North America(CSA) 2015/04/06 Mod End

                getArAcctDt();
                getVarCharConst();
            }
        }
    }

    /**
     * Setting of ForSaveArRcptRcvWrkTMsg
     * @param rs ResultSet
     * @throws SQLException
     */
    private void setForSaveArRcptRcvWrkTMsgList(ResultSet rs) throws SQLException {

        AR_RCPT_RCV_WRKTMsg setTMsg = new AR_RCPT_RCV_WRKTMsg();

        setTMsg.glblCmpyCd.setValue(convStr(rs.getString(NFCDbConst.GLBL_CMPY_CD)));
        setTMsg.rcvSqPk.setValue(rs.getBigDecimal(NFCDbConst.RCV_SQ_PK));
        setTMsg.rcvHdrNum.setValue(convStr(rs.getString(NFCDbConst.RCV_HDR_NUM)));
        setTMsg.rcvDtlNum.setValue(convStr(rs.getString(NFCDbConst.RCV_DTL_NUM)));
        setTMsg.rcvDt.setValue(convStr(rs.getString(NFCDbConst.RCV_DT)));
        setTMsg.rcvFuncId.setValue(convStr(rs.getString(NFCDbConst.RCV_FUNC_ID)));
        setTMsg.ansiIncgSendId.setValue(convStr(rs.getString(NFCDbConst.ANSI_INCG_SEND_ID)));
        setTMsg.ansiIncgCtrlNum.setValue(convStr(rs.getString(NFCDbConst.ANSI_INCG_CTRL_NUM)));
        setTMsg.ansiTrxCd.setValue(convStr(rs.getString(NFCDbConst.ANSI_TRX_CD)));
        setTMsg.ansiIncgRcvId.setValue(convStr(rs.getString(NFCDbConst.ANSI_INCG_RCV_ID)));
        setTMsg.ansiFuncId.setValue(convStr(rs.getString(NFCDbConst.ANSI_FUNC_ID)));
        setTMsg.entryDt_01.setValue(convStr(rs.getString(NFCDbConst.ENTRY_DT_01)));
        setTMsg.entryTm_01.setValue(convStr(rs.getString(NFCDbConst.ENTRY_TM_01)));
        setTMsg.firstBillToCustCd.setValue(convStr(rs.getString(NFCDbConst.FIRST_BILL_TO_CUST_CD)));
        setTMsg.chk30Num.setValue(NFCB0240Util.getChk30Num(this.initChk30Num, convStr(rs.getString(NFCDbConst.CHK_30_NUM))));
        setTMsg.bankIdNum.setValue(convStr(rs.getString(NFCDbConst.BANK_ID_NUM)));
        if (S21StringUtil.isEmpty(convStr(rs.getString(NFCDbConst.BAT_NUM)))) {
            setTMsg.batNum.setValue(this.initBatNum);
        } else {
            setTMsg.batNum.setValue(convStr(rs.getString(NFCDbConst.BAT_NUM)));
        }
        setTMsg.rcvChkNum.setValue(convStr(rs.getString(NFCDbConst.RCV_CHK_NUM)));
        setTMsg.depDt_01.setValue(convStr(rs.getString(NFCDbConst.DEP_DT_01)));
        setTMsg.ansiTrxHdlgCd.setValue(convStr(rs.getString(NFCDbConst.ANSI_TRX_HDLG_CD)));
        setTMsg.chkAmt.setValue(rs.getBigDecimal(NFCDbConst.CHK_AMT));
        setTMsg.ansiCrDrFlg.setValue(convStr(rs.getString(NFCDbConst.ANSI_CR_DR_FLG)));
        setTMsg.ansiPmtFmtCd.setValue(convStr(rs.getString(NFCDbConst.ANSI_PMT_FMT_CD)));
        setTMsg.custBankIdNum.setValue(convStr(rs.getString(NFCDbConst.CUST_BANK_ID_NUM)));
        setTMsg.custBankAcctNum.setValue(convStr(rs.getString(NFCDbConst.CUST_BANK_ACCT_NUM)));
        setTMsg.canonBankIdNum.setValue(convStr(rs.getString(NFCDbConst.CANON_BANK_ID_NUM)));
        setTMsg.ansiCdOrigCompId.setValue(convStr(rs.getString(NFCDbConst.ANSI_CD_ORIG_COMP_ID)));
        setTMsg.sellTpCd.setValue(convStr(rs.getString(NFCDbConst.SELL_TP_CD)));
        setTMsg.arRcptRcvTrxTpCd.setValue(convStr(rs.getString(NFCDbConst.AR_RCPT_RCV_TRX_TP_CD)));
        setTMsg.trxTpNum.setValue(convStr(rs.getString(NFCDbConst.TRX_TP_NUM)));
        setTMsg.chk30NumCd.setValue(convStr(rs.getString(NFCDbConst.CHK_30_NUM_CD)));
        setTMsg.chkDtCd.setValue(convStr(rs.getString(NFCDbConst.CHK_DT_CD)));
        setTMsg.chkApplyDt.setValue(convStr(rs.getString(NFCDbConst.CHK_APPLY_DT)));
        setTMsg.effDtCd.setValue(convStr(rs.getString(NFCDbConst.EFF_DT_CD)));
        setTMsg.chkEffDt.setValue(convStr(rs.getString(NFCDbConst.CHK_EFF_DT)));
        setTMsg.apvlFlg_01.setValue(convStr(rs.getString(NFCDbConst.APVL_FLG_01)));
        setTMsg.brBankCd.setValue(convStr(rs.getString(NFCDbConst.BR_BANK_CD)));
        setTMsg.acctMaCd.setValue(convStr(rs.getString(NFCDbConst.ACCT_MA_CD)));
        setTMsg.entryDt_02.setValue(convStr(rs.getString(NFCDbConst.ENTRY_DT_02)));
        setTMsg.entryTm_02.setValue(convStr(rs.getString(NFCDbConst.ENTRY_TM_02)));
        setTMsg.entryOpCd_01.setValue(convStr(rs.getString(NFCDbConst.ENTRY_TM_02)));
        setTMsg.entryTermCd_01.setValue(convStr(rs.getString(NFCDbConst.ENTRY_TERM_CD_01)));
        setTMsg.updDt_01.setValue(convStr(rs.getString(NFCDbConst.UPD_DT_01)));
        setTMsg.updTm_01.setValue(convStr(rs.getString(NFCDbConst.UPD_TM_01)));
        setTMsg.updOpCd_01.setValue(convStr(rs.getString(NFCDbConst.UPD_OP_CD_01)));
        setTMsg.updTermCd_01.setValue(convStr(rs.getString(NFCDbConst.UPD_TERM_CD_01)));
        setTMsg.depDt_02.setValue(convStr(rs.getString(NFCDbConst.DEP_DT_02)));
        setTMsg.apvlFlg_02.setValue(convStr(rs.getString(NFCDbConst.APVL_FLG_02)));
        setTMsg.scdBillToCustCd.setValue(convStr(rs.getString(NFCDbConst.SCD_BILL_TO_CUST_CD)));
        setTMsg.inv30NumCd.setValue(convStr(rs.getString(NFCDbConst.INV_30_NUM_CD)));
        setTMsg.inv30OrigNum.setValue(convStr(rs.getString(NFCDbConst.INV_30_ORIG_NUM)));
        setTMsg.chkRcptTotAmt.setValue(rs.getBigDecimal(NFCDbConst.CHK_RCPT_TOT_AMT));
        setTMsg.invOrigTotAmt.setValue(rs.getBigDecimal(NFCDbConst.INV_ORIG_TOT_AMT));
        setTMsg.invDiscTotAmt.setValue(rs.getBigDecimal(NFCDbConst.INV_DISC_TOT_AMT));
        setTMsg.ansiPmtActCd.setValue(convStr(rs.getString(NFCDbConst.ANSI_PMT_ACT_CD)));
        setTMsg.ansiDt.setValue(convStr(rs.getString(NFCDbConst.ANSI_DT)));
        setTMsg.entryDt_03.setValue(convStr(rs.getString(NFCDbConst.ENTRY_DT_03)));
        setTMsg.entryTm_03.setValue(convStr(rs.getString(NFCDbConst.ENTRY_TM_03)));
        setTMsg.entryOpCd_02.setValue(convStr(rs.getString(NFCDbConst.ENTRY_OP_CD_02)));
        setTMsg.entryTermCd_02.setValue(convStr(rs.getString(NFCDbConst.ENTRY_TERM_CD_02)));
        setTMsg.updDt_02.setValue(convStr(rs.getString(NFCDbConst.UPD_DT_02)));
        setTMsg.updTm_02.setValue(convStr(rs.getString(NFCDbConst.UPD_TM_02)));
        setTMsg.updOpCd_02.setValue(convStr(rs.getString(NFCDbConst.UPD_OP_CD_02)));
        setTMsg.updTermCd_02.setValue(convStr(rs.getString(NFCDbConst.UPD_TERM_CD_02)));
        setTMsg.cashApplyAmt.setValue(rs.getBigDecimal(NFCDbConst.CASH_APPLY_AMT));
        setTMsg.ansiRsnCd.setValue(convStr(rs.getString(NFCDbConst.ANSI_RSN_CD)));
        setTMsg.refCd.setValue(convStr(rs.getString(NFCDbConst.REF_CD)));
        setTMsg.refNum.setValue(convStr(rs.getString(NFCDbConst.REF_NUM)));
        setTMsg.rcvStsCd.setValue(convStr(rs.getString(NFCDbConst.RCV_STS_CD)));
        setTMsg.ansiNm.setValue(convStr(rs.getString(NFCDbConst.ANSI_NM)));
        setTMsg.upldCratMethTpCd.setValue(convStr(rs.getString(NFCDbConst.UPLD_CRAT_METH_TP_CD)));
        setTMsg.upldArRcptTpCd.setValue(convStr(rs.getString(NFCDbConst.UPLD_AR_RCPT_TP_CD)));
        setTMsg.upldGlDt.setValue(convStr(rs.getString(NFCDbConst.UPLD_GL_DT)));
        setTMsg.upldArRcptTrxTpCd.setValue(convStr(rs.getString(NFCDbConst.UPLD_AR_RCPT_TRX_TP_CD)));
        setTMsg.upldRcptBatNum.setValue(convStr(rs.getString(NFCDbConst.UPLD_RCPT_BAT_NUM)));
        setTMsg.upldArBankAcctCd.setValue(convStr(rs.getString(NFCDbConst.UPLD_AR_BANK_ACCT_CD)));
        setTMsg.upldDealCcyCd.setValue(convStr(rs.getString(NFCDbConst.UPLD_DEAL_CCY_CD)));
        setTMsg.upldArCustRefNum.setValue(convStr(rs.getString(NFCDbConst.UPLD_AR_CUST_REF_NUM)));
        setTMsg.upldArCustRefTpCd.setValue(convStr(rs.getString(NFCDbConst.UPLD_AR_CUST_REF_TP_CD)));
        setTMsg.upldRcptFirstCmntTxt.setValue(convStr(rs.getString(NFCDbConst.UPLD_RCPT_FIRST_CMNT_TXT)));
        setTMsg.upldRcptScdCmntTxt.setValue(convStr(rs.getString(NFCDbConst.UPLD_RCPT_SCD_CMNT_TXT)));
        setTMsg.inv30Num.setValue(NFCB0240Util.convInv30Num(convStr(rs.getString(NFCDbConst.INV_30_NUM))));

        if (NFCConst.CST_CREATE_METH_TP_CD_CSV.equals(convStr(rs.getString(NFCDbConst.UPLD_CRAT_METH_TP_CD)))) {
            setTMsg.ansiPmtMethCd.setValue(convStr(rs.getString(NFCDbConst.UPLD_AR_RCPT_TP_CD)));
        } else {
            setTMsg.ansiPmtMethCd.setValue(convStr(rs.getString(NFCDbConst.ANSI_PMT_METH_CD)));
        }

        // not sure what is this code, but remain as-is since no harm.
        // for North America(CSA) 2015/04/06 Add Start
        setTMsg.payerCustCd.setValue(convStr(rs.getString(NFCDbConst.PAYER_CUST_CD)));
        setTMsg.arCustIdStsCd.setValue(convStr(rs.getString(NFCDbConst.AR_CUST_ID_STS_CD)));
        // for North America(CSA) 2015/04/06 Add End

        setTMsg.exptFlg.setValue(convStr(rs.getString(NFCDbConst.EXPT_FLG)));
        this.forSaveArRcptRcvWrkTMsgList.add(setTMsg);
    }

    /**
     * Setting of InsertArRcptInProcWrkTMsg
     * @param rs ResultSet
     * @throws SQLException
     */
    private void setInsertArRcptInProcWrkTMsg(ResultSet rs) throws SQLException {

        AR_RCPT_IN_PROC_WRKTMsg setTMsg = new AR_RCPT_IN_PROC_WRKTMsg();

        setTMsg.ezCancelFlag.setValue(CST_EZ_CANCEL_FLAG);
        setTMsg.ezInCompanyCode.setValue(CST_EZ_IN_COMPANY_CODE);
        setTMsg.glblCmpyCd.setValue(convStr(rs.getString(NFCDbConst.GLBL_CMPY_CD)));
        setTMsg.rcptInProcSqPk.setValue(rs.getBigDecimal(NFCDbConst.RCV_SQ_PK));
        setTMsg.rcvHdrNum.setValue(convStr(rs.getString(NFCDbConst.RCV_HDR_NUM)));
        setTMsg.rcvDtlNum.setValue(convStr(rs.getString(NFCDbConst.RCV_DTL_NUM)));
        if (isUpload()) {
            setTMsg.rcvDt.setValue(convStr(rs.getString(NFCDbConst.RCV_DT)));
            setTMsg.rcvTm.setValue(convStr(rs.getString(NFCDbConst.ENTRY_TM_01)));
            setTMsg.rcvTs.setValue(convStr(rs.getString(NFCDbConst.RCV_DT)) + convStr(rs.getString(NFCDbConst.ENTRY_TM_01)));
        } else {
            setTMsg.rcvDt.setValue(this.sysTimeStamp.substring(0, ZYPDateUtil.TYPE_YYYYMMDD.length()));
            setTMsg.rcvTm.setValue(this.sysTimeStamp.substring(ZYPDateUtil.TYPE_YYYYMMDD.length()));
            setTMsg.rcvTs.setValue(this.sysTimeStamp);
        }
        setTMsg.rcvFuncId.setValue(convStr(rs.getString(NFCDbConst.RCV_FUNC_ID)));
        setTMsg.rcptStsCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.dealRcptAmt.setValue(rs.getBigDecimal(NFCDbConst.CHK_AMT));
        setTMsg.dealApplyAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.dealRfAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.dealVoidAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.dealRcptRmngBalAmt.setValue(rs.getBigDecimal(NFCDbConst.CHK_AMT));
        setTMsg.exchRate.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.funcCcyCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.funcRcptAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.funcApplyAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.funcRfAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.funcVoidAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.funcRcptRmngBalAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.rcptDt.setValue(convStr(rs.getString(NFCDbConst.DEP_DT_01)));
        setTMsg.rcptChkNum.setValue(NFCB0240Util.getChk30Num(this.initChk30Num, convStr(rs.getString(NFCDbConst.CHK_30_NUM))));
        setTMsg.crAnlstPsnCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.rcptFirstCmntTxt.setValue(convStr(rs.getString(NFCDbConst.UPLD_RCPT_FIRST_CMNT_TXT)));
        setTMsg.rcptScdCmntTxt.setValue(convStr(rs.getString(NFCDbConst.UPLD_RCPT_SCD_CMNT_TXT)));
        setTMsg.arCashApplyStsCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.voidFlg.setValue(NFCConst.CST_FLAG_OFF);
        setTMsg.arRcptVoidRsnCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.voidDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.voidFirstCmntTxt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.voidScdCmntTxt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.voidGlDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.arRcptRfRsnCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.rfDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.firstRfCmntTxt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.scdRfCmntTxt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.rfGlDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.rfExchRate.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.rcvSqPk.setValue(rs.getBigDecimal(NFCDbConst.RCV_SQ_PK));
        setTMsg.rcvTrxTpCd.setValue(NFCConst.CST_AR_TRX_TP_CD_INVOICE);
        setTMsg.arEdiSendBankCd.setValue(convStr(rs.getString(NFCDbConst.ACCT_MA_CD)));
        setTMsg.ediRcvCustNm.setValue(convStr(rs.getString(NFCDbConst.ANSI_NM)));
        
//        setTMsg.dealRcptDtlAmt.setValue(rs.getBigDecimal(NFCDbConst.CHK_RCPT_TOT_AMT));
        setTMsg.dealRcptDtlAmt.setValue(rs.getBigDecimal(NFCDbConst.INV_ORIG_TOT_AMT));

        setTMsg.funcRcptDtlAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.autoCratFlg.setValue(NFCConst.CST_FLAG_OFF);
        setTMsg.payerCustCd.setValue(convStr(rs.getString(NFCDbConst.FIRST_BILL_TO_CUST_CD)));
        setTMsg.rcvTrxNum.setValue(NFCB0240Util.convInv30Num(convStr(rs.getString(NFCDbConst.INV_30_NUM))));
        setTMsg.invNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.tocCd.setValue(this.cstTocCd);
        setTMsg.coaProdCd.setValue(this.cstProdCd);
        setTMsg.arCustRefTpCd.setValue(NFCConst.CST_AGING_TRX_TP_INV);
        if (NFCConst.CST_CREATE_METH_TP_CD_CSV.equals(convStr(rs.getString(NFCDbConst.UPLD_CRAT_METH_TP_CD)))) {
            setTMsg.rcptChkDt.setValue(convStr(rs.getString(NFCDbConst.CHK_APPLY_DT)));
            setTMsg.rcptBatNum.setValue(convStr(rs.getString(NFCDbConst.UPLD_RCPT_BAT_NUM)));
            setTMsg.arRcptTrxTpCd.setValue(convStr(rs.getString(NFCDbConst.UPLD_AR_RCPT_TRX_TP_CD)));
            setTMsg.arRcptTpCd.setValue(convStr(rs.getString(NFCDbConst.UPLD_AR_RCPT_TP_CD)));
            setTMsg.dealCcyCd.setValue(convStr(rs.getString(NFCDbConst.UPLD_DEAL_CCY_CD)));
            setTMsg.glDt.setValue(convStr(rs.getString(NFCDbConst.UPLD_GL_DT)));
            setTMsg.arBankAcctCd.setValue(convStr(rs.getString(NFCDbConst.UPLD_AR_BANK_ACCT_CD)));
            setTMsg.cratMethTpCd.setValue(convStr(rs.getString(NFCDbConst.UPLD_CRAT_METH_TP_CD)));
            if (NFCCsvCmnMethod.CST_UPLOAD_CSV_RECEIPT.equals(this.userVariable1)) {
                setTMsg.arCustRefNum.setValue(convStr(rs.getString(NFCDbConst.UPLD_AR_CUST_REF_NUM)));
            } else {
                setTMsg.arCustRefNum.setValue(convStr(rs.getString(NFCDbConst.INV_30_ORIG_NUM)));
            }
            setTMsg.invNum.setValue(NFCB0240Util.convInvNum(convStr(rs.getString(NFCDbConst.INV_30_ORIG_NUM))));
        } else {
            setTMsg.rcptChkDt.setValue(convStr(rs.getString(NFCDbConst.DEP_DT_01)));
            if (S21StringUtil.isEmpty(convStr(rs.getString(NFCDbConst.BAT_NUM)))) {
                setTMsg.rcptBatNum.setValue(this.initBatNum);
            } else {
                setTMsg.rcptBatNum.setValue(convStr(rs.getString(NFCDbConst.BAT_NUM)));
            }
            setTMsg.rcptBatSqNum.setValue(convStr(rs.getString(NFCDbConst.RCPT_BAT_SQ_NUM)));

            // for North America(CSA) 2015/04/06 Mod Start
            String rcvFuncId = convStr(rs.getString(NFCDbConst.RCV_FUNC_ID));
            // START 2018/08/21 H.Ikeda [QC#127776,MOD]
            //if (NFCConst.CST_INTERFACEID_CBS.equals(rcvFuncId) || NFCConst.CST_INTERFACEID_CFS.equals(rcvFuncId) || NFCConst.CST_INTERFACEID_CFS_REGULAR.equals(rcvFuncId) || NFCConst.CST_INTERFACEID_CFS_CPC.equals(rcvFuncId)
            //        || NFCConst.CST_INTERFACEID_CFS_MD.equals(rcvFuncId)) {
            if (NFCConst.CST_INTERFACEID_CBS.equals(rcvFuncId)) {
            // END   2018/08/21 H.Ikeda [QC#127776,MOD]
                setTMsg.arRcptTrxTpCd.setValue(NFCConst.CST_AR_RCPT_TRX_TP_CD_02);
            } else {
                setTMsg.arRcptTrxTpCd.setValue(NFCConst.CST_AR_RCPT_TRX_TP_CD_00);
            }
            // for North America(CSA) 2015/04/06 Mod End

            if (NFCConst.CST_INTERFACEID_BOA.equals(convStr(rs.getString(NFCDbConst.RCV_FUNC_ID)))) {
                // Bank of America data needs if-then logic to
                // determine RcptTp.
                String pmtMethTpCd = rs.getString(NFCDbConst.ANSI_PMT_METH_CD);
                if (pmtMethTpCd != null && pmtMethTpCd.equals(NFCConst.CST_ANSI_PMT_METH_TP_CD_ACH)) {
                    setTMsg.arRcptTpCd.setValue(NFCConst.CST_AR_RCPT_TP_CD_02);
                } else {
                    setTMsg.arRcptTpCd.setValue(NFCConst.CST_AR_RCPT_TP_CD_01);
                }
            } else {
                setTMsg.arRcptTpCd.setValue(NFCConst.CST_AR_RCPT_TP_CD_02);
            }
            // START 2016/07/29 T.Tsuchida [QC#12649,ADD]
            String pmtMethTpCd = rs.getString(NFCDbConst.ANSI_PMT_METH_CD);
            if (pmtMethTpCd != null && pmtMethTpCd.equals(CST_ANSI_PMT_METH_TP_CD_CREDIT_CARD)) {
                setTMsg.arRcptTpCd.setValue(NFCConst.CST_AR_RCPT_TP_CD_04);
            }
            // END 2016/07/29 T.Tsuchida [QC#12649,ADD]

            setTMsg.dealCcyCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
            setTMsg.glDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
            if (S21StringUtil.isNotEmpty(this.initArBankAcctCd)) {
                setTMsg.arBankAcctCd.setValue(this.initArBankAcctCd);
            } else {
                setTMsg.arBankAcctCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
            }
            setTMsg.cratMethTpCd.setValue(NFCConst.CST_CREATE_METH_TP_CD_AUTO);

            // for North America(CSA) 2015/04/06 Add Start
            if (this.isNaFlg) {
                if (ZYPCommonFunc.hasValue(rs.getString(NFCDbConst.INV_30_ORIG_NUM))) {
                    setTMsg.arCustRefNum.setValue(convStr(rs.getString(NFCDbConst.INV_30_ORIG_NUM)));
                } else if (ZYPCommonFunc.hasValue(rs.getString(NFCDbConst.INV_30_NUM))) {
                    setTMsg.arCustRefNum.setValue(convStr(rs.getString(NFCDbConst.INV_30_NUM)));
                } else if (ZYPCommonFunc.hasValue(rs.getString(NFCDbConst.CUST_ACCT_REF_NUM))) {
                    setTMsg.arCustRefNum.setValue(convStr(rs.getString(NFCDbConst.CUST_ACCT_REF_NUM)));
                    setTMsg.arCustRefTpCd.setValue(NFCConst.CST_AR_CUST_REF_TP_CD_OTHERS);
                } else if (ZYPCommonFunc.hasValue(rs.getString(NFCDbConst.CUST_INV_NUM))) {
                    setTMsg.arCustRefNum.setValue(convStr(rs.getString(NFCDbConst.CUST_INV_NUM)));
                }

                setTMsg.invNum.setValue(convStr(rs.getString(NFCDbConst.INV_30_NUM)));
                setTMsg.arBatRcptNm.setValue(convStr(rs.getString(NFCDbConst.AR_BAT_RCPT_NM)));
                setTMsg.remDsBankAcctPk.setValue(rs.getBigDecimal(NFCDbConst.REM_DS_BANK_ACCT_PK));
                setTMsg.custDsBankAcctPk.setValue(rs.getBigDecimal(NFCDbConst.CUST_DS_BANK_ACCT_PK));
                setTMsg.custAcctRefNum.setValue(convStr(rs.getString(NFCDbConst.CUST_ACCT_REF_NUM)));
                setTMsg.custRcptNum.setValue(convStr(rs.getString(NFCDbConst.CUST_RCPT_NUM)));
                setTMsg.custInvNum.setValue(convStr(rs.getString(NFCDbConst.CUST_INV_NUM)));
//                setTMsg.invConslTpCd.setValue(convStr(rs.getString(NFCDbConst.INV_CONSL_TP_CD)));
                setTMsg.arRcptSrcCd.setValue(convStr(rs.getString(NFCDbConst.AR_RCPT_SRC_CD)));
                setTMsg.arCustIdStsCd.setValue(convStr(rs.getString(NFCDbConst.AR_CUST_ID_STS_CD)));
                setTMsg.arLockBoxFileNm.setValue(convStr(rs.getString(NFCDbConst.AR_LOCK_BOX_FILE_NM)));
                setTMsg.arLockBoxCd.setValue(convStr(rs.getString(NFCDbConst.AR_LOCK_BOX_CD)));
                setTMsg.batRcptRecCnt.setValue(rs.getBigDecimal(NFCDbConst.BAT_RCPT_REC_CNT));
                setTMsg.batRcptTotAmt.setValue(rs.getBigDecimal(NFCDbConst.BAT_RCPT_TOT_AMT));
                
                
                // 2015/12/16 mod. set AR_CASH_APPLY_STS_CD by AS_CUST_ID_STS
                if (AR_CUST_ID_STS.IDENTIFIED.equals(rs.getString(NFCDbConst.AR_CUST_ID_STS_CD))) {
                    ZYPEZDItemValueSetter.setValue(setTMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.UNAPPLIED);
                } else if (AR_CUST_ID_STS.UN_IDENTIFIED.equals(rs.getString(NFCDbConst.AR_CUST_ID_STS_CD))) {
                    ZYPEZDItemValueSetter.setValue(setTMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.UNIDENTIFIED);
                }
                // end 2012/12/16

            } else {
                setTMsg.arCustRefNum.setValue(convStr(rs.getString(NFCDbConst.INV_30_NUM)));
            }
            // for North America(CSA) 2015/04/06 Add End
        }
        setTMsg.dupErrCd.setValue(NFCConst.DUP_ERR_CD_NOMAL);
        setTMsg.exptFlg.setValue(convStr(rs.getString(NFCDbConst.EXPT_FLG)));
        // START 2016/07/25 J.Kim [QC#9476,ADD]
        ZYPEZDItemValueSetter.setValue(setTMsg.crCardApvlCd, (String) rs.getString("CR_CARD_APVL_CD"));
        // END 2016/07/25 J.Kim [QC#9476,ADD]

        this.insertArRcptInProcWrkTMsg.add(setTMsg);

    }

    /**
     * Setting of InsertArApplyIntfcWrkTMsg
     * @param rs ResultSet
     * @throws SQLException
     */
    private void setInsertArApplyIntfcWrkTMsg(ResultSet rs) throws SQLException {

        AR_APPLY_INTFC_WRKTMsg setTMsg = new AR_APPLY_INTFC_WRKTMsg();

        setTMsg.ezCancelFlag.setValue(CST_EZ_CANCEL_FLAG);
        setTMsg.ezInCompanyCode.setValue(CST_EZ_IN_COMPANY_CODE);
        setTMsg.glblCmpyCd.setValue(convStr(rs.getString(NFCDbConst.GLBL_CMPY_CD)));
        setTMsg.applyGrpKey.setValue(convStr(rs.getString(NFCDbConst.RCV_SQ_PK)));
        this.applyGrpPk++;
        setTMsg.applyGrpSubPk.setValue(this.applyGrpPk);
        setTMsg.bizAppId.setValue(CST_BIZ_APP_ID);
        if (isUpload()) {
            setTMsg.intfcId.setValue(NFCConst.CST_DB_INIT_VAL_STR);
            setTMsg.invNum.setValue(NFCB0240Util.convInvNum(convStr(rs.getString(NFCDbConst.INV_30_NUM))));
            if (S21StringUtil.isNotEmpty(this.interfaceId)) {
                setTMsg.upldCsvId.setValue(this.interfaceId);
            } else {
                setTMsg.upldCsvId.setValue(this.userVariable1);
            }
        } else {
            setTMsg.intfcId.setValue(this.interfaceId);
            setTMsg.upldCsvId.setValue(NFCConst.CST_DB_INIT_VAL_STR);
            // for North America(CSA) 2015/04/06 Mod Start
            if (this.isNaFlg) {
                setTMsg.invNum.setValue(convStr(rs.getString(NFCDbConst.INV_30_NUM)));
            } else {
                setTMsg.invNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
            }
            // for North America(CSA) 2015/04/06 Mod End
        }
        setTMsg.upldCsvRqstPk.setValue(rs.getBigDecimal(NFCDbConst.RCV_SQ_PK));
        setTMsg.procTpCd.setValue(NFCConst.CST_PROC_TP_CD_NEW);
        setTMsg.dealSqNum.setValue(convStr(rs.getString(NFCDbConst.RCV_HDR_NUM)));
        setTMsg.procStsCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.usrId.setValue(this.initUsrId);
        setTMsg.rcptNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.rcptDtlNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.rcvFuncId.setValue(convStr(rs.getString(NFCDbConst.RCV_FUNC_ID)));
        setTMsg.rcptInProcSqPk.setValue(rs.getBigDecimal(NFCDbConst.RCV_SQ_PK));
        setTMsg.rcvHdrNum.setValue(convStr(rs.getString(NFCDbConst.RCV_HDR_NUM)));
        setTMsg.rcvDtlNum.setValue(convStr(rs.getString(NFCDbConst.RCV_DTL_NUM)));
        setTMsg.rcptGlDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.payerCustCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.rcptTrxBalPk.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.rcptHdrLastUpdTs.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.rcptHdrTz.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.rcptTrxBalLastUpdTs.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.rcptTrxBalTz.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.grpInvFlg.setValue(NFCConst.CST_GRP_INV_FLG_GRP_OFF);
        setTMsg.arTrxTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.invTrxBalPk.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.invTrxBalLastUpdTs.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.invTrxBalTz.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.crNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.crTrxBalPk.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.crTrxBalLastUpdTs.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.crTrxBalTz.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.dealCcyCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.dealApplyAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.cashAppGlDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.cashDiscPct.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.dealCashDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.dealOnAcctCashAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.arAdjNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.arAdjTrxTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.arAdjTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.dealApplyAdjAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.dealApplyAdjRsvdAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        setTMsg.adjCmntTxt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.apvlPsnCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.tocCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.coaProdCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.arCustRefNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.arAdjLastUpdTs.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        setTMsg.arAdjTz.setValue(NFCConst.CST_DB_INIT_VAL_STR);

        this.insertArApplyIntfcWrkTMsg.add(setTMsg);

        this.putGrpInvInfoToMap(setTMsg.rcptInProcSqPk.getValue(), setTMsg.rcvHdrNum.getValue(), setTMsg.rcvDtlNum.getValue(), setTMsg.grpInvFlg.getValue());

    }

    /**
     * Generation of AR_RCPT_IN_PROC_WRKTMsg
     */
    private void generatInsertArRcptInProcWrkTMsg() {

        for (AR_RCPT_IN_PROC_WRKTMsg tMsg : this.insertArRcptInProcWrkTMsg) {

            tMsg.rcptStsCd.setValue(NFCConst.CST_RCPT_STS_CD_ERR);

            if (NFCConst.CST_NULL_CONV_DATE.equals(tMsg.rcptChkDt.getValue())) {
                tMsg.rcptChkDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
            }

            if (ZYPDateUtil.isValidDate(tMsg.rcptDt.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {

                NFXC3070Bean rtnBean = getAfxc3070Bean(tMsg.glblCmpyCd.getValue(), tMsg.dealCcyCd.getValue(), tMsg.dealRcptAmt.getValue(), tMsg.glDt.getValue());
                tMsg.exchRate.setValue(rtnBean.getExchRate());
                tMsg.funcCcyCd.setValue(rtnBean.getFuncCcyCd());
                tMsg.funcRcptAmt.setValue(rtnBean.getFuncAmt());
                rtnBean = getAfxc3070Bean(tMsg.glblCmpyCd.getValue(), tMsg.dealCcyCd.getValue(), tMsg.dealRcptDtlAmt.getValue(), tMsg.glDt.getValue());
                tMsg.funcRcptDtlAmt.setValue(rtnBean.getFuncAmt());
            } else {
                tMsg.exchRate.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
                tMsg.funcCcyCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
                tMsg.funcRcptAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
                tMsg.funcRcptDtlAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            }

            tMsg.funcRcptRmngBalAmt.setValue(tMsg.funcRcptAmt.getValue());

         // 2015/12/16 modification. Set by AR_RCPT_RCV_WRK.AR_CUST_ID_STS_CD. Move to setInsertArRcptInProcWrkTMsg
            /*if (S21StringUtil.isNotEmpty(tMsg.payerCustCd.getValue())) {
                tMsg.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_APPLY);
            } else {
                tMsg.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY);
            }
            */
            // end 2015/12/16

            if (S21StringUtil.isEmpty(tMsg.rcptChkNum.getValue())) {
                tMsg.rcptChkNum.setValue(NFCConst.CST_CHK_30_NUM_NUMBER_ALL9);
            }

            // START 2016/07/08 S.Fujita [QC#11520,ADD]
            if (S21StringUtil.isEmpty(tMsg.custRcptNum.getValue())) {
                tMsg.custRcptNum.setValue(NFCConst.CST_CHK_30_NUM_NUMBER_ALL9);
            }
            // END   2016/07/08 S.Fujita [QC#11520,ADD]
        }
    }

    /**
     * Generation of AR_RCPT_IN_PROC_WRKTMsg for INSERT
     */
    private void generatInsertArRcptInProcWrkTMsgForUpld() {

        for (AR_RCPT_IN_PROC_WRKTMsg tMsg : this.insertArRcptInProcWrkTMsg) {

            tMsg.rcptStsCd.setValue(NFCConst.CST_RCPT_STS_CD_ERR);

            if (ZYPDateUtil.isValidDate(tMsg.rcptDt.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {

                NFXC3070Bean rtnBean = getAfxc3070Bean(tMsg.glblCmpyCd.getValue(), tMsg.dealCcyCd.getValue(), tMsg.dealRcptAmt.getValue(), tMsg.glDt.getValue());
                tMsg.exchRate.setValue(rtnBean.getExchRate());
                tMsg.funcCcyCd.setValue(rtnBean.getFuncCcyCd());
                tMsg.funcRcptAmt.setValue(rtnBean.getFuncAmt());
                rtnBean = getAfxc3070Bean(tMsg.glblCmpyCd.getValue(), tMsg.dealCcyCd.getValue(), tMsg.dealRcptDtlAmt.getValue(), tMsg.glDt.getValue());
                tMsg.funcRcptDtlAmt.setValue(rtnBean.getFuncAmt());
            } else {
                tMsg.exchRate.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
                tMsg.funcCcyCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
                tMsg.funcRcptAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
                tMsg.funcRcptDtlAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            }

            tMsg.funcRcptRmngBalAmt.setValue(tMsg.funcRcptAmt.getValue());

            // 2015/12/16 modification. Set by AR_RCPT_RCV_WRK.AR_CUST_ID_STS_CD. Move to setInsertArRcptInProcWrkTMsg
            /*if (S21StringUtil.isNotEmpty(tMsg.payerCustCd.getValue())) {
                tMsg.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_APPLY);
            } else {
                tMsg.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY);
            }
            */
            //end 2015/12/16

            if (S21StringUtil.isEmpty(tMsg.rcptChkNum.getValue())) {
                tMsg.rcptChkNum.setValue(NFCConst.CST_CHK_30_NUM_NUMBER_ALL9);
            }

            // START 2016/07/08 S.Fujita [QC#11520,ADD]
            if (S21StringUtil.isEmpty(tMsg.custRcptNum.getValue())) {
                tMsg.custRcptNum.setValue(NFCConst.CST_CHK_30_NUM_NUMBER_ALL9);
            }
            // END   2016/07/08 S.Fujita [QC#11520,ADD]
        }

    }

    /**
     * Output record initialization processing
     * @param tMsg AR_RCPT_IN_PROC_WRKTMsg
     * @param addFlg int
     * @param differenceAmt BigDecimal
     */
    private void addArRcptInProcWrkTMsg(AR_RCPT_IN_PROC_WRKTMsg tMsg, int addFlg, BigDecimal differenceAmt) {

        AR_RCPT_IN_PROC_WRKTMsg seterTMsg = new AR_RCPT_IN_PROC_WRKTMsg();
        seterTMsg.ezTableID.setValue(tMsg.ezTableID.getValue());
        seterTMsg.ezCancelFlag.setValue(tMsg.ezCancelFlag.getValue());
        seterTMsg.ezInTime.setValue(tMsg.ezInTime.getValue());
        seterTMsg.ezInTimeZone.setValue(tMsg.ezInTimeZone.getValue());
        seterTMsg.ezInCompanyCode.setValue(tMsg.ezInCompanyCode.getValue());
        seterTMsg.ezInUserID.setValue(tMsg.ezInUserID.getValue());
        seterTMsg.ezInAplID.setValue(tMsg.ezInAplID.getValue());
        seterTMsg.ezUpTime.setValue(tMsg.ezUpTime.getValue());
        seterTMsg.ezUpTimeZone.setValue(tMsg.ezUpTimeZone.getValue());
        seterTMsg.ezUpCompanyCode.setValue(tMsg.ezUpCompanyCode.getValue());
        seterTMsg.ezUpUserID.setValue(tMsg.ezUpUserID.getValue());
        seterTMsg.ezUpAplID.setValue(tMsg.ezUpAplID.getValue());
        seterTMsg.glblCmpyCd.setValue(tMsg.glblCmpyCd.getValue());
        seterTMsg.rcptInProcSqPk.setValue(tMsg.rcptInProcSqPk.getValue());
        seterTMsg.rcvHdrNum.setValue(tMsg.rcvHdrNum.getValue());
        int rcvDtlNum = Integer.parseInt(tMsg.rcvDtlNum.getValue());
        rcvDtlNum++;
        DecimalFormat df = new DecimalFormat(CST_DECIMAL_FORMAT_0000);
        String after = df.format(rcvDtlNum);
        seterTMsg.rcvDtlNum.setValue(after);
        seterTMsg.rcvDt.setValue(tMsg.rcvDt.getValue());
        seterTMsg.rcvTm.setValue(tMsg.rcvTm.getValue());
        seterTMsg.rcvFuncId.setValue(tMsg.rcvFuncId.getValue());
        seterTMsg.rcptStsCd.setValue(tMsg.rcptStsCd.getValue());
        seterTMsg.rcptBatNum.setValue(tMsg.rcptBatNum.getValue());
        seterTMsg.rcptBatSqNum.setValue(tMsg.rcptBatSqNum.getValue());
        seterTMsg.arRcptTrxTpCd.setValue(tMsg.arRcptTrxTpCd.getValue());
        seterTMsg.arRcptTpCd.setValue(tMsg.arRcptTpCd.getValue());
        seterTMsg.dealCcyCd.setValue(tMsg.dealCcyCd.getValue());
        seterTMsg.dealRcptAmt.setValue(tMsg.dealRcptAmt.getValue());
        seterTMsg.dealApplyAmt.setValue(tMsg.dealApplyAmt.getValue());
        seterTMsg.dealRfAmt.setValue(tMsg.dealRfAmt.getValue());
        seterTMsg.dealVoidAmt.setValue(tMsg.dealVoidAmt.getValue());
        seterTMsg.dealRcptRmngBalAmt.setValue(tMsg.dealRcptRmngBalAmt.getValue());
        seterTMsg.exchRate.setValue(tMsg.exchRate.getValue());
        seterTMsg.funcCcyCd.setValue(tMsg.funcCcyCd.getValue());
        seterTMsg.funcRcptAmt.setValue(tMsg.funcRcptAmt.getValue());
        seterTMsg.funcApplyAmt.setValue(tMsg.funcApplyAmt.getValue());
        seterTMsg.funcRfAmt.setValue(tMsg.funcRfAmt.getValue());
        seterTMsg.funcVoidAmt.setValue(tMsg.funcVoidAmt.getValue());
        seterTMsg.funcRcptRmngBalAmt.setValue(tMsg.funcRcptRmngBalAmt.getValue());
        seterTMsg.rcptDt.setValue(tMsg.rcptDt.getValue());
        seterTMsg.glDt.setValue(tMsg.glDt.getValue());
        seterTMsg.rcptChkNum.setValue(tMsg.rcptChkNum.getValue());
        seterTMsg.rcptChkDt.setValue(tMsg.rcptChkDt.getValue());
        seterTMsg.payerCustCd.setValue(tMsg.payerCustCd.getValue());
        seterTMsg.tocCd.setValue(tMsg.tocCd.getValue());
        seterTMsg.coaProdCd.setValue(tMsg.coaProdCd.getValue());
        seterTMsg.crAnlstPsnCd.setValue(tMsg.crAnlstPsnCd.getValue());
        seterTMsg.arBankAcctCd.setValue(tMsg.arBankAcctCd.getValue());
        seterTMsg.rcptFirstCmntTxt.setValue(tMsg.rcptFirstCmntTxt.getValue());
        seterTMsg.rcptScdCmntTxt.setValue(tMsg.rcptScdCmntTxt.getValue());
        seterTMsg.arCashApplyStsCd.setValue(tMsg.arCashApplyStsCd.getValue());
        seterTMsg.voidFlg.setValue(tMsg.voidFlg.getValue());
        seterTMsg.arRcptVoidRsnCd.setValue(tMsg.arRcptVoidRsnCd.getValue());
        seterTMsg.voidDt.setValue(tMsg.voidDt.getValue());
        seterTMsg.voidFirstCmntTxt.setValue(tMsg.voidFirstCmntTxt.getValue());
        seterTMsg.voidScdCmntTxt.setValue(tMsg.voidScdCmntTxt.getValue());
        seterTMsg.voidGlDt.setValue(tMsg.voidGlDt.getValue());
        seterTMsg.arRcptRfRsnCd.setValue(tMsg.arRcptRfRsnCd.getValue());
        seterTMsg.rfDt.setValue(tMsg.rfDt.getValue());
        seterTMsg.firstRfCmntTxt.setValue(tMsg.rfDt.getValue());
        seterTMsg.scdRfCmntTxt.setValue(tMsg.scdRfCmntTxt.getValue());
        seterTMsg.rfGlDt.setValue(tMsg.rfGlDt.getValue());
        seterTMsg.rfExchRate.setValue(tMsg.rfExchRate.getValue());
        seterTMsg.rcvTs.setValue(tMsg.rcvTs.getValue());
        seterTMsg.rcvSqPk.setValue(tMsg.rcvSqPk.getValue());
        seterTMsg.rcvTrxTpCd.setValue(tMsg.rcvTrxTpCd.getValue());
        seterTMsg.rcvTrxNum.setValue(tMsg.rcvTrxNum.getValue());
        seterTMsg.arEdiSendBankCd.setValue(tMsg.arEdiSendBankCd.getValue());
        seterTMsg.ediRcvCustNm.setValue(tMsg.ediRcvCustNm.getValue());
        seterTMsg.cratMethTpCd.setValue(tMsg.cratMethTpCd.getValue());
        seterTMsg.invNum.setValue(this.autoCratRefNum);
        seterTMsg.arCustRefNum.setValue(this.autoCratRefNum);
        seterTMsg.arCustRefTpCd.setValue(tMsg.arCustRefTpCd.getValue());
        seterTMsg.dealRcptDtlAmt.setValue(differenceAmt);
        seterTMsg.dupErrCd.setValue(this.judgeInvRepetitionCode);

        if (ZYPDateUtil.isValidDate(tMsg.rcptDt.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            NFXC3070Bean rtnBean = getAfxc3070Bean(tMsg.glblCmpyCd.getValue(), tMsg.dealCcyCd.getValue(), differenceAmt, tMsg.glDt.getValue());
            seterTMsg.funcRcptDtlAmt.setValue(rtnBean.getFuncAmt());
        } else {
            seterTMsg.funcRcptDtlAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        }

        seterTMsg.autoCratFlg.setValue(NFCConst.CST_FLAG_ON);
        seterTMsg.exptFlg.setValue(tMsg.exptFlg.getValue());

        this.insertArRcptInProcWrkTMsg.add(seterTMsg);

    }

    /**
     * Generation of AR_APPLY_INTFC_WRKTMsg for INSERT
     * @param paramTMsg AR_RCPT_IN_PROC_WRKTMsg
     * @param index int
     * @param differenceFlg boolean
     */
    private void generatArApplyIntfcWrkTMsg(AR_RCPT_IN_PROC_WRKTMsg paramTMsg, int index, boolean differenceFlg) {

        this.insertArApplyIntfcWrkTMsg.get(index).ezCancelFlag.setValue(CST_EZ_CANCEL_FLAG);
        this.insertArApplyIntfcWrkTMsg.get(index).ezInCompanyCode.setValue(CST_EZ_IN_COMPANY_CODE);
        this.insertArApplyIntfcWrkTMsg.get(index).ezUpAplID.setValue(CST_EZ_UPDATE_APPLICATION_ID);
        this.insertArApplyIntfcWrkTMsg.get(index).glblCmpyCd.setValue(paramTMsg.glblCmpyCd.getValue());
        String applyGrpKey;
        if (S21StringUtil.isNotEmpty(this.interfaceId)) {
            applyGrpKey = this.interfaceId + paramTMsg.rcvSqPk.getValue();
        } else {
            applyGrpKey = this.userVariable1 + paramTMsg.rcvSqPk.getValue();
        }
        this.insertArApplyIntfcWrkTMsg.get(index).applyGrpKey.setValue(applyGrpKey);
        this.insertArApplyIntfcWrkTMsg.get(index).bizAppId.setValue(CST_BIZ_APP_ID);
        if (isUpload()) {
            this.insertArApplyIntfcWrkTMsg.get(index).intfcId.setValue(NFCConst.CST_DB_INIT_VAL_STR);
            if (S21StringUtil.isNotEmpty(this.interfaceId)) {
                this.insertArApplyIntfcWrkTMsg.get(index).upldCsvId.setValue(this.interfaceId);
            } else {
                this.insertArApplyIntfcWrkTMsg.get(index).upldCsvId.setValue(this.userVariable1);
            }
        } else {
            this.insertArApplyIntfcWrkTMsg.get(index).intfcId.setValue(this.interfaceId);
            this.insertArApplyIntfcWrkTMsg.get(index).upldCsvId.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        }
        this.insertArApplyIntfcWrkTMsg.get(index).upldCsvRqstPk.setValue(paramTMsg.rcvSqPk.getValue());
        this.insertArApplyIntfcWrkTMsg.get(index).procTpCd.setValue(NFCConst.CST_PROC_TP_CD_NEW);
        this.insertArApplyIntfcWrkTMsg.get(index).dealSqNum.setValue(paramTMsg.rcvHdrNum.getValue());
        int dealSqDtlNum = index + 1;
        DecimalFormat df = new DecimalFormat(CST_DECIMAL_FORMAT_0000);
        String after = df.format(dealSqDtlNum);
        this.insertArApplyIntfcWrkTMsg.get(index).dealSqDtlNum.setValue(after);

        // for North America(CSA) 2015/04/06 Mod Start
        if (this.isRcptOnlyFlg) {
//Def#5097
            if (NFCConst.CST_FLAG_ON.equals(paramTMsg.autoCratFlg.getValue())) {
                this.insertArApplyIntfcWrkTMsg.get(index).procStsCd.setValue(NFCConst.CST_PROC_STS_CD_EDIERR);
            } else {
                if (!ERROR_MODE_OFF.equals(this.errFlg) && !ERROR_MODE_ON_OK.equals(this.errFlg)) {
                    this.insertArApplyIntfcWrkTMsg.get(index).procStsCd.setValue(NFCConst.CST_PROC_STS_CD_EDIERR);
                } else {
                    if(ZYPCommonFunc.hasValue(this.insertArApplyIntfcWrkTMsg.get(index).payerCustCd.getValue())) {
                        if(this.insertArApplyIntfcWrkTMsg.get(index).invTrxBalPk.getValue().compareTo(BigDecimal.ZERO)!=0) {
                            this.insertArApplyIntfcWrkTMsg.get(index).procStsCd.setValue(NFCConst.CST_PROC_STS_CD_UNPROC);
                        } else {
                            // START 2016/11/30 S.Fujita [QC#16254,MOD]
                            this.insertArApplyIntfcWrkTMsg.get(index).procStsCd.setValue(NFCConst.CST_PROC_STS_CD_RCPT_ONLY);
//                            this.insertArApplyIntfcWrkTMsg.get(index).procStsCd.setValue(NFCConst.CST_PROC_STS_CD_EDIERR);
                            // END   2016/11/30 S.Fujita [QC#16254,MOD]
                        }
                    } else {
                        this.insertArApplyIntfcWrkTMsg.get(index).procStsCd.setValue(NFCConst.CST_PROC_STS_CD_RCPT_ONLY);
                    }
                }
            }
        } else {
            if (NFCConst.CST_FLAG_ON.equals(paramTMsg.autoCratFlg.getValue())) {
                this.insertArApplyIntfcWrkTMsg.get(index).procStsCd.setValue(NFCConst.CST_PROC_STS_CD_EDIERR);
            } else {
                if (!ERROR_MODE_OFF.equals(this.errFlg) && !ERROR_MODE_ON_OK.equals(this.errFlg)) {
                    this.insertArApplyIntfcWrkTMsg.get(index).procStsCd.setValue(NFCConst.CST_PROC_STS_CD_EDIERR);
                } else {
                    this.insertArApplyIntfcWrkTMsg.get(index).procStsCd.setValue(NFCConst.CST_PROC_STS_CD_UNPROC);
                }
            }
        }
        
//        if(this.insertArApplyIntfcWrkTMsg.get(index).procStsCd.getValue().equals(NFCConst.CST_PROC_STS_CD_UNPROC)) {
//            BigDecimal rmngAmt = getRmngBalance(this.globalCompanyCode,this.insertArApplyIntfcWrkTMsg.get(index).invNum.getValue());
//            if(rmngAmt.compareTo(this.insertArApplyIntfcWrkTMsg.get(index).dealApplyAmt.getValue()) < 0 ) {
//                this.insertArApplyIntfcWrkTMsg.get(index).procStsCd.setValue(NFCConst.CST_PROC_STS_CD_EDIERR);
//            }
//        }
        // for North America(CSA) 2015/04/06 Mod End

        this.insertArApplyIntfcWrkTMsg.get(index).usrId.setValue(this.initUsrId);
        this.insertArApplyIntfcWrkTMsg.get(index).rcptNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        this.insertArApplyIntfcWrkTMsg.get(index).rcptDtlNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        this.insertArApplyIntfcWrkTMsg.get(index).rcvFuncId.setValue(paramTMsg.rcvFuncId.getValue());
        this.insertArApplyIntfcWrkTMsg.get(index).rcptInProcSqPk.setValue(paramTMsg.rcvSqPk.getValue());
        this.insertArApplyIntfcWrkTMsg.get(index).rcvHdrNum.setValue(paramTMsg.rcvHdrNum.getValue());
        this.insertArApplyIntfcWrkTMsg.get(index).rcvDtlNum.setValue(paramTMsg.rcvDtlNum.getValue());
        this.insertArApplyIntfcWrkTMsg.get(index).rcptGlDt.setValue(paramTMsg.glDt.getValue());
        this.insertArApplyIntfcWrkTMsg.get(index).cashAppGlDt.setValue(paramTMsg.glDt.getValue());
        this.insertArApplyIntfcWrkTMsg.get(index).payerCustCd.setValue(convStr(paramTMsg.payerCustCd.getValue()));
        this.insertArApplyIntfcWrkTMsg.get(index).rcptTrxBalPk.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        this.insertArApplyIntfcWrkTMsg.get(index).rcptHdrLastUpdTs.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        this.insertArApplyIntfcWrkTMsg.get(index).rcptHdrTz.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        this.insertArApplyIntfcWrkTMsg.get(index).rcptTrxBalLastUpdTs.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        this.insertArApplyIntfcWrkTMsg.get(index).rcptTrxBalTz.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        if (NFCConst.CST_FLAG_ON.equals(paramTMsg.autoCratFlg.getValue())) {
            this.insertArApplyIntfcWrkTMsg.get(index).grpInvFlg.setValue(this.insertArApplyIntfcWrkTMsg.get(index).grpInvFlg.getValue());
        } else {
            this.insertArApplyIntfcWrkTMsg.get(index).grpInvFlg.setValue(this.insertArApplyIntfcWrkTMsg.get(index).grpInvFlg.getValue());
            if (NFCConst.CST_GRP_INV_FLG_GRP_OFF.equals(this.insertArApplyIntfcWrkTMsg.get(index).grpInvFlg.getValue())) {
                if (S21StringUtil.isEmpty(this.insertArApplyIntfcWrkTMsg.get(index).arTrxTpCd.getValue())) {
                    this.insertArApplyIntfcWrkTMsg.get(index).arTrxTpCd.setValue(paramTMsg.rcvTrxTpCd.getValue());
                }
            } else {
                this.insertArApplyIntfcWrkTMsg.get(index).arTrxTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
                this.insertArApplyIntfcWrkTMsg.get(index).invTrxBalPk.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
                this.insertArApplyIntfcWrkTMsg.get(index).invTrxBalLastUpdTs.setValue(NFCConst.CST_DB_INIT_VAL_STR);
                this.insertArApplyIntfcWrkTMsg.get(index).invTrxBalTz.setValue(NFCConst.CST_DB_INIT_VAL_STR);
            }
        }
        this.insertArApplyIntfcWrkTMsg.get(index).crNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        this.insertArApplyIntfcWrkTMsg.get(index).crTrxBalPk.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        this.insertArApplyIntfcWrkTMsg.get(index).crTrxBalLastUpdTs.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        this.insertArApplyIntfcWrkTMsg.get(index).crTrxBalTz.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        this.insertArApplyIntfcWrkTMsg.get(index).dealCcyCd.setValue(paramTMsg.dealCcyCd.getValue());

        if (differenceFlg == false) {
            if (NFCConst.CST_FLAG_ON.equals(paramTMsg.autoCratFlg.getValue())) {
                this.insertArApplyIntfcWrkTMsg.get(index).dealApplyAmt.setValue(paramTMsg.dealRcptDtlAmt.getValue());
            } else {
                if (NFCConst.CST_GRP_INV_FLG_GRP_ON.equals(this.insertArApplyIntfcWrkTMsg.get(index).grpInvFlg.getValue())) {
                    this.insertArApplyIntfcWrkTMsg.get(index).dealApplyAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
                }
            }
        }
        this.insertArApplyIntfcWrkTMsg.get(index).cashDiscPct.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        this.insertArApplyIntfcWrkTMsg.get(index).dealCashDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        this.insertArApplyIntfcWrkTMsg.get(index).dealOnAcctCashAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        this.insertArApplyIntfcWrkTMsg.get(index).arAdjNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        this.insertArApplyIntfcWrkTMsg.get(index).arAdjTrxTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        this.insertArApplyIntfcWrkTMsg.get(index).arAdjTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        this.insertArApplyIntfcWrkTMsg.get(index).dealApplyAdjAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        this.insertArApplyIntfcWrkTMsg.get(index).dealApplyAdjRsvdAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        this.insertArApplyIntfcWrkTMsg.get(index).adjCmntTxt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        this.insertArApplyIntfcWrkTMsg.get(index).apvlPsnCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        this.insertArApplyIntfcWrkTMsg.get(index).tocCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        this.insertArApplyIntfcWrkTMsg.get(index).coaProdCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        this.insertArApplyIntfcWrkTMsg.get(index).arCustRefNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        this.insertArApplyIntfcWrkTMsg.get(index).arAdjLastUpdTs.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        this.insertArApplyIntfcWrkTMsg.get(index).arAdjTz.setValue(NFCConst.CST_DB_INIT_VAL_STR);

        this.putGrpInvInfoToMap(this.insertArApplyIntfcWrkTMsg.get(index).rcptInProcSqPk.getValue(), this.insertArApplyIntfcWrkTMsg.get(index).rcvHdrNum.getValue(), this.insertArApplyIntfcWrkTMsg.get(index).rcvDtlNum.getValue(),
                this.insertArApplyIntfcWrkTMsg.get(index).grpInvFlg.getValue());
    }

    /**
     * Output record initialization processing
     * @param index int
     * @param tMsg AR_RCPT_IN_PROC_WRKTMsg
     * @param addFlg int
     * @param differenceAmt BigDecimal
     */
    private void addArApplyIntfcWrkTMsg(int index, AR_RCPT_IN_PROC_WRKTMsg tMsg, int addFlg) {

        AR_APPLY_INTFC_WRKTMsg tmsg = new AR_APPLY_INTFC_WRKTMsg();
        tmsg.ezCancelFlag.setValue(CST_EZ_CANCEL_FLAG);
        tmsg.ezInCompanyCode.setValue(CST_EZ_IN_COMPANY_CODE);
        tmsg.ezUpAplID.setValue(CST_EZ_UPDATE_APPLICATION_ID);
        tmsg.glblCmpyCd.setValue(tMsg.glblCmpyCd.getValue());
        if (S21StringUtil.isNotEmpty(this.interfaceId)) {
            tmsg.applyGrpKey.setValue(this.interfaceId + tMsg.rcvSqPk.getValue());
        } else {
            tmsg.applyGrpKey.setValue(this.userVariable1 + tMsg.rcvSqPk.getValue());
        }
        this.applyGrpPk++;
        tmsg.applyGrpSubPk.setValue(this.applyGrpPk);
        tmsg.bizAppId.setValue(CST_BIZ_APP_ID);
        if (isUpload()) {
            this.insertArApplyIntfcWrkTMsg.get(index).intfcId.setValue(NFCConst.CST_DB_INIT_VAL_STR);
            if (S21StringUtil.isNotEmpty(this.interfaceId)) {
                tmsg.upldCsvId.setValue(this.interfaceId);
            } else {
                tmsg.upldCsvId.setValue(this.userVariable1);
            }
        } else {
            this.insertArApplyIntfcWrkTMsg.get(index).intfcId.setValue(this.interfaceId);
            this.insertArApplyIntfcWrkTMsg.get(index).upldCsvId.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        }
        tmsg.upldCsvRqstPk.setValue(tMsg.rcvSqPk.getValue());
        tmsg.procTpCd.setValue(NFCConst.CST_PROC_TP_CD_NEW);
        tmsg.dealSqNum.setValue(tMsg.rcvHdrNum.getValue());
        int dealSqDtlNum = index + 1;
        DecimalFormat df = new DecimalFormat(CST_DECIMAL_FORMAT_0000);
        String after = df.format(dealSqDtlNum);
        tmsg.dealSqDtlNum.setValue(after);
        tmsg.procStsCd.setValue(NFCConst.CST_PROC_STS_CD_EDIERR);
        tmsg.usrId.setValue(this.initUsrId);
        tmsg.rcptNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        tmsg.rcptDtlNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        tmsg.rcvFuncId.setValue(tMsg.rcvFuncId.getValue());
        tmsg.rcptInProcSqPk.setValue(tMsg.rcvSqPk.getValue());
        tmsg.rcvHdrNum.setValue(tMsg.rcvHdrNum.getValue());
        tmsg.rcvDtlNum.setValue(tMsg.rcvDtlNum.getValue());
        tmsg.rcptGlDt.setValue(tMsg.glDt.getValue());
        tmsg.cashAppGlDt.setValue(tMsg.glDt.getValue());
        tmsg.payerCustCd.setValue(convStr(tMsg.payerCustCd.getValue()));
        tmsg.rcptTrxBalPk.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        tmsg.rcptHdrLastUpdTs.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        tmsg.rcptHdrTz.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        tmsg.rcptTrxBalLastUpdTs.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        tmsg.rcptTrxBalTz.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        tmsg.grpInvFlg.setValue(this.insertArApplyIntfcWrkTMsg.get(index).grpInvFlg.getValue());
        tmsg.invNum.setValue(this.insertArApplyIntfcWrkTMsg.get(index).invNum.getValue());
        tmsg.arTrxTpCd.setValue(this.insertArApplyIntfcWrkTMsg.get(index).arTrxTpCd.getValue());
        tmsg.invTrxBalPk.setValue(this.insertArApplyIntfcWrkTMsg.get(index).invTrxBalPk.getValue());
        tmsg.invTrxBalLastUpdTs.setValue(this.insertArApplyIntfcWrkTMsg.get(index).invTrxBalLastUpdTs.getValue());
        tmsg.invTrxBalTz.setValue(this.insertArApplyIntfcWrkTMsg.get(index).invTrxBalTz.getValue());
        tmsg.crNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        tmsg.crTrxBalPk.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        tmsg.crTrxBalLastUpdTs.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        tmsg.crTrxBalTz.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        tmsg.dealCcyCd.setValue(tMsg.dealCcyCd.getValue());
        tmsg.dealApplyAmt.setValue(tMsg.dealRcptDtlAmt.getValue());
        tmsg.cashDiscPct.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        tmsg.dealCashDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        tmsg.dealOnAcctCashAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        tmsg.arAdjNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        tmsg.arAdjTrxTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        tmsg.arAdjTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        tmsg.dealApplyAdjAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        tmsg.dealApplyAdjRsvdAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        tmsg.adjCmntTxt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        tmsg.apvlPsnCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        tmsg.tocCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        tmsg.coaProdCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        tmsg.arCustRefNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        tmsg.arAdjLastUpdTs.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        tmsg.arAdjTz.setValue(NFCConst.CST_DB_INIT_VAL_STR);

        this.insertArApplyIntfcWrkTMsg.add(tmsg);

        this.putGrpInvInfoToMap(tmsg.rcptInProcSqPk.getValue(), tmsg.rcvHdrNum.getValue(), tmsg.rcvDtlNum.getValue(), tmsg.grpInvFlg.getValue());
    }

    /**
     * UPLD judgment
     */
    private boolean isUpload() {
        if (S21StringUtil.isNotEmpty(this.interfaceId)) {
            return false;
        }
        return true;
    }

    /**
     * Setting of error flag
     */
    private void isErrorMode(String errorMode) {

        if (ERROR_MODE_OFF.equals(errorMode)) {
            this.errFlg = errorMode;
        } else if (ERROR_MODE_ON_NG.equals(errorMode)) {
            this.errFlg = errorMode;
        } else {
            if (ERROR_MODE_OFF.equals(this.errFlg)) {
                this.errFlg = errorMode;
            }
        }

    }

    /**
     * Change processing
     * @param strVal String
     * @return String
     */
    private String convStr(String strVal) {
        return NFCCmnMethod.convertDbString(strVal);
    }

    /**
     * Setting of transaction
     * @param index int
     * @param targetAmt BigDecimal
     * @param arTrxTpCd String
     * @param arTrxBalPk BigDecimal
     * @param ezUpTime String
     * @param ezUpTimeZone String
     */
    private void setTrxBal(int index, BigDecimal targetAmt, String arTrxTpCd, BigDecimal arTrxBalPk, String ezUpTime, String ezUpTimeZone) {
        this.insertArApplyIntfcWrkTMsg.get(index).dealApplyAmt.setValue(targetAmt);
        this.insertArApplyIntfcWrkTMsg.get(index).arTrxTpCd.setValue(arTrxTpCd);
        this.insertArApplyIntfcWrkTMsg.get(index).invTrxBalPk.setValue(arTrxBalPk);
        this.insertArApplyIntfcWrkTMsg.get(index).invTrxBalLastUpdTs.setValue(ezUpTime);
        this.insertArApplyIntfcWrkTMsg.get(index).invTrxBalTz.setValue(ezUpTimeZone);
    }

    /**
     * judgeInvRepetition
     * @param rs ResultSet
     */
    private void judgeInvRepetition() {

        for (AR_RCPT_IN_PROC_WRKTMsg rowTMsg : this.insertArRcptInProcWrkTMsg) {
            if (S21StringUtil.isNotEmpty(rowTMsg.invNum.getValue())) {
                for (AR_RCPT_IN_PROC_WRKTMsg colTMsg : this.insertArRcptInProcWrkTMsg) {
                    if (rowTMsg.invNum.getValue().equals(colTMsg.invNum.getValue())) {
                        if (!rowTMsg.rcvDtlNum.getValue().equals(colTMsg.rcvDtlNum.getValue())) {
                            rowTMsg.dupErrCd.setValue(NFCConst.DUP_ERR_CD_INVOICE_REPETITION);
                            isErrorMode(ERROR_MODE_ON_NG);

                            colTMsg.invNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
                            colTMsg.dupErrCd.setValue(NFCConst.DUP_ERR_CD_INVOICE_REPETITION);
                        }
                    }
                }
            }
        }

        if (this.errFlg.equals(ERROR_MODE_ON_NG)) {
            debugLog("ERROR MODE SET : ERROR_MODE_ON_NG : INVOICE_REPETITION");
        }
    }

    /**
     * Acquisition of Afxc3070Bean
     * @param glblCmpyCd String
     * @param dealCcyCd String
     * @param dealRcptAmt BigDecimal
     * @param rcptDt String
     * @return rtnBean NFXC3070Bean
     */
    private NFXC3070Bean getAfxc3070Bean(String glblCmpyCd, String dealCcyCd, BigDecimal dealRcptAmt, String glDt) {

        NFCCurrencyConversion afcCcyCnv = new NFCCurrencyConversion();

        NFXC3070Bean rtnBean = afcCcyCnv.convertCurrency(glblCmpyCd, dealCcyCd, dealRcptAmt, glDt, null);

        if (NFCConst.CST_RTN_CD_ERR.equals(rtnBean.getRtrnCd())) {
            this.errCnt++;
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0550E);
        }

        return rtnBean;
    }

    /**
     * 
     * 
     */
    private void setPayer(int index, String rsPayer) {

        if (NFCConst.CST_CREATE_METH_TP_CD_CSV.equals(this.insertArRcptInProcWrkTMsg.get(index).cratMethTpCd.getValue())) {
            // CSV
            if (S21StringUtil.isEmpty(this.insertArRcptInProcWrkTMsg.get(index).payerCustCd.getValue())) {
                if (S21StringUtil.isNotEmpty(this.initPayerCustCd)) {
                    if (this.initPayerCustCd.equals(rsPayer)) {
                        this.insertArRcptInProcWrkTMsg.get(index).payerCustCd.setValue(rsPayer);
                    } else {
                        this.insertArRcptInProcWrkTMsg.get(index).payerCustCd.setValue(this.initPayerCustCd);
                    }
                } else {
                    this.insertArRcptInProcWrkTMsg.get(index).payerCustCd.setValue(rsPayer);
                }
            }
        } else {
            // EDI
            if (S21StringUtil.isNotEmpty(this.initPayerCustCd)) {
                if (this.initPayerCustCd.equals(rsPayer)) {
                    this.insertArRcptInProcWrkTMsg.get(index).payerCustCd.setValue(rsPayer);
                } else {
                    this.insertArRcptInProcWrkTMsg.get(index).payerCustCd.setValue(this.initPayerCustCd);
                }
            } else {
                this.insertArRcptInProcWrkTMsg.get(index).payerCustCd.setValue(rsPayer);
            }
        }

    }

    /**
     * 
     * 
     */
    private void payerClear() {
        for (AR_RCPT_IN_PROC_WRKTMsg tMsg : this.insertArRcptInProcWrkTMsg) {
            tMsg.payerCustCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        }

        for (AR_APPLY_INTFC_WRKTMsg tMsg : this.insertArApplyIntfcWrkTMsg) {
            tMsg.payerCustCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        }
    }

    /**
     * 
     * 
     */
    private void payerBuries() {
        debugLog("payerBuries START");

        String payer = NFCConst.CST_DB_INIT_VAL_STR;

        for (AR_RCPT_IN_PROC_WRKTMsg tMsg : this.insertArRcptInProcWrkTMsg) {
            if (S21StringUtil.isNotEmpty(tMsg.payerCustCd.getValue())) {
                if (S21StringUtil.isEmpty(payer)) {
                    payer = tMsg.payerCustCd.getValue();
                    break;
                }
            }
        }

        if (S21StringUtil.isNotEmpty(payer)) {
            for (AR_RCPT_IN_PROC_WRKTMsg tMsg : this.insertArRcptInProcWrkTMsg) {
                if (S21StringUtil.isEmpty(tMsg.payerCustCd.getValue())) {
                    tMsg.payerCustCd.setValue(payer);
                }
            }
            for (AR_APPLY_INTFC_WRKTMsg tMsg : this.insertArApplyIntfcWrkTMsg) {
                if (S21StringUtil.isEmpty(tMsg.payerCustCd.getValue())) {
                    tMsg.payerCustCd.setValue(payer);
                }
            }
        }

        debugLog("payerBuries END");
    }

    private void putGrpInvInfoToMap(BigDecimal seq, String hdrNum, String dtlNum, String grpInvFlg) {
        String grpInvFlgKey = this.createGrpInvFlgKey(seq, hdrNum, dtlNum);
        GRP_INV_FLG_INFO grpInvFlgInfo = this.createGrpInvFlgInfo(seq, hdrNum, dtlNum, grpInvFlg);
        this.grpInvFlgMap.put(grpInvFlgKey, grpInvFlgInfo);
    }

    private String createGrpInvFlgKey(BigDecimal seq, String hdrNum, String dtlNum) {
        StringBuffer sb = new StringBuffer();
        sb.append(this.globalCompanyCode);
        sb.append(KEY_SEPARATOR);
        sb.append(String.valueOf(seq));
        sb.append(KEY_SEPARATOR);
        sb.append(hdrNum);
        sb.append(KEY_SEPARATOR);
        sb.append(dtlNum);
        return sb.toString();
    }

    private GRP_INV_FLG_INFO createGrpInvFlgInfo(BigDecimal seq, String hdrNum, String dtlNum, String grpInvFlg) {
        GRP_INV_FLG_INFO grpInvFlgInfo = new GRP_INV_FLG_INFO();
        grpInvFlgInfo.setGrpInvFlgInfo(this.globalCompanyCode, seq, hdrNum, dtlNum, grpInvFlg);
        return grpInvFlgInfo;
    }

    /**
     */
    private static class GRP_INV_FLG_INFO {

        /***/
        private AR_RCPT_IN_PROC_WRKTMsg condition = new AR_RCPT_IN_PROC_WRKTMsg();

        /***/
        private String grpInvFlg;

        GRP_INV_FLG_INFO() {
        }

        public AR_RCPT_IN_PROC_WRKTMsg getCondition() {
            return condition;
        }

        public String getGrpInvFlg() {
            return grpInvFlg;
        }

        public void setGrpInvFlgInfo(String glblCmpyCd, BigDecimal rcptInProcSqPk, String rcvHdrNum, String rcvDtlNum, String grpInvFlgParam) {
            ZYPEZDItemValueSetter.setValue(this.condition.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(this.condition.rcptInProcSqPk, rcptInProcSqPk);
            ZYPEZDItemValueSetter.setValue(this.condition.rcvHdrNum, rcvHdrNum);
            ZYPEZDItemValueSetter.setValue(this.condition.rcvDtlNum, rcvDtlNum);
            this.grpInvFlg = grpInvFlgParam;
        }

    }

    /**
     * 
     * @param glblCmpyCd
     * @param arTrxNum
     * @return
     */
    public static BigDecimal getRmngBalance(String glblCmpyCd, String arTrxNum) {
        
        AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("arTrxNum01", arTrxNum);
        
        AR_TRX_BALTMsgArray outMsg = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if(outMsg!=null) {
            return outMsg.no(0).dealRmngBalGrsAmt.getValue();
        }
        return BigDecimal.ZERO;
    }

    /** */
    private void debugLog(String msg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, "=== " + msg + " ===", this);
    }

}
