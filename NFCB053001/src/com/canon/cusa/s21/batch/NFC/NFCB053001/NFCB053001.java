/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB053001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDDebugOutput;
import parts.common.EZDMessageEnv;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_RCPT_RCV_INFO_WRKTMsg;
import business.db.AR_RCPT_RCV_INTFCTMsg;
import business.db.AR_RCPT_RCV_INTFCTMsgArray;
import business.db.DS_BANK_ACCTTMsg;
import business.db.DS_BANK_BRTMsg;
import business.db.DS_CUST_BANK_ACCT_RELNTMsg;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCDbConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_BAT_INFO_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CUST_ID_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_LOCK_BOX_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BANK_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Receive Data Check -Customer check
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/03/2015   Fujitsu         H.Nakajima      Create          N/A
 * 01/07/2016   CSAI            K.Uramori       Update          Apply Specification Change
 * 02/09/2016   Fujitsu         T.Tanaka        Update          Def#2601
 * 03/04/2016   Fujitsu         T.Tanaka        Update          Def#5016
 * 12/16/2016   Fujitsu         T.Murai         Update          Def#16530
 * 01/04/2017   Hitachi         E.Kameishi      Update          Def#16536
 * 06/05/2017   Hitachi         E.Kameishi      Update          Def#18600,18703
 * 09/05/2017   Hitachi         T.Tsuchida      Update          Def#20939
 * 11/15/2017   Hitachi         Y.Takeno        Update          QC#17322
 * 12/11/2017   Fujitsu         M.Ohno          Update          QC#22262
 * 01/19/2018   Fujitsu         T.Murai         Update          QC#22421
 * 02/26/2018   Fujitsu         T.Murai         Update          QC#24219
 * 03/01/2018   Fujitsu         T.Murai         Update          QC#24465
 * 2018/05/09   Fujitsu         H.Ikeda         Update          QC#25808
 * 2018/05/10   Fujitsu         H.Ikeda         Update          QC#25703
 * 2018/05/16   Fujitsu         H.Ikeda         Update          QC#26179
 * 2018/05/21   Fujitsu         H.Ikeda         Update          QC#26204
 * 2018/05/22   Fujitsu         H.Ikeda         Update          QC#25769
 * 2018/05/29   Fujitsu         H.Ikeda         Update          QC#26368
 * 2018/07/04   Fujitsu         H.Ikeda         Update          QC#25731
 * 2018/08/07   Fujitsu         H.Ikeda         Update          QC#27569
 * 2018/08/23   Fujitsu         H.Ikeda         Update          QC#27776
 * 2018/11/16   Fujitsu         H.Ikeda         Update          QC#29278
 * 2018/11/28   Fujitsu         H.Ikeda         Update          QC#28839
 * 2019/05/28   Fujitsu         H.Ikeda         Update          QC#50544
 * 2021/06/07   CITS            G.Delgado       Update          QC#58800
 * 2021/10/18   CITS            S.Go            Update          QC#59273
 * 2023/05/08   Hitachi         S.Fujita        Update          QC#61244
 *</pre>
 */
public class NFCB053001 extends S21BatchMain {

    /** Program Id */
    private static final String[] PROGRAM_ID = {"NFCB053001" };

    /** Entry Parameter Error [@] */
    private static final String NFCM0501E = "NFCM0501E";

    /** The specified date does not exist. */
    private static final String NFCM0574E = "NFCM0574E";

    /** Process Started: [@] */
    private static final String NFCM0584I = "NFCM0584I";

    /** Process Ended: [@]. */
    private static final String NFCM0593I = "NFCM0593I";

    /**
     * Customer number cannot be identified using customer number in
     * the receipt record.
     */
    private static final String NFCM0736W = "NFCM0736W";

    /**
     * The combination of Customer Bank Routing and Account Number
     * (MICR number) in the receipt record does not exist in the
     * system.
     */
    private static final String NFCM0737W = "NFCM0737W";

    /**
     * MICR number refers to more than one customer.
     */
    private static final String NFCM0738W = "NFCM0738W";

    /**
     * Customer number cannot be identified using invoice number in
     * the receipt record. Receipt will be created as 'UNIDENTIFIED
     * RECEIPT'.
     */
    private static final String NFCM0739W = "NFCM0739W";

    /**
     * MICR number could be created in Bank Account Master with Common
     * Bank(Dummy).
     */
    private static final String NFCM0741W = "NFCM0741W";

    /**
     * Receipt number already exists for this customer and amount.
     */
    private static final String NFCM0742E = "NFCM0742E";

    /** GLBL_CMPY_CD */
    private static final String[] MSG_STR_GLBL_CMPY_CD = {"GLBL_CMPY_CD" };

    /** Common Bank Branch Routing Number */
    private static final String[] MSG_STR_COMN_BANK_BR_RTE_NUM = {"Common Bank Branch Routing Number" };

    /** DEFAULT_EFF_THRU_DT: 99991231 */
//    private static final String DEFAULT_EFF_THRU_DT = "99991231";
    private static final String DEFAULT_EFF_THRU_DT = "";

    /** Common Bank Branch Routing Number */
    private String comnBankBrRteNum = "";

    /** GlobalCompanyCode */
    private String glblCmpyCd = "";

    /** Processing Count */
    private int procCount = 0;

    /** normal Count */
    private int normalCnt = 0;

    /** err Count */
    private int errCnt = 0;

    /** SQL Access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Batch Proc Date */
    private String batchProcDt = "";

    /** Set of LOCK_BOX_FILE_NM having Error */
    private Set<String> errorLockBoxFileNms = new HashSet<String>();

    private static final String DS_BANK_COMMON = "99";

    // START 2017/09/05 T.Tsuchida [QC#20939,ADD]
    /** NFCI1050 : CFS Misdirected */
    private static final String NFCI1050 = "NFCI1050";
    // END 2017/09/05 T.Tsuchida [QC#20939,ADD]

    private String stdCcyCd = "";
    private String ctryCd = "";
    private static final String[] MSG_STR_GLBL_CMPY = {"GLBL_CMPY" };

    // START 2017/01/04 E.Kameishi [QC#16536,ADD]
    /** DS Bank Accout Pk */
    private BigDecimal dsBankAcctPk = null;

    /** Proc Flag */
    boolean procFlg = true;

    /** Common component for Database accessing */
    private S21SsmBatchClient ssmBatchClient = null;

    // END 2017/01/04 E.Kameishi [QC#16536,ADD]

    // START 2018/07/04 H.Ikeda [QC#25731,ADD]
    // START 2021/06/07 G.Delgado [QC#58800,DEL]
//    /** Data(true) */
//    private String trueData = "0";
//
//    /** Data(false) */
//    private String falseData = "1";
    // END 2021/06/07 G.Delgado [QC#58800,DEL]

    /** commitCnt */
    private int commitCnt = 1000;

    /** Multiple Count */
    private String multipleCnt = null;

    /** Multiple No */
    private int multipleNo = 0;
    
    /** Default Multiple No */
    private final int defMultipleNo = 10;
    // END   2018/07/04 H.Ikeda [QC#25731,ADD]

    // START 2018/08/07 H.Ikeda [QC#27569,ADD]
    /** DS_ACCT_NUM(Inv) */
    private static final String INV_DS_ACCT_NUM = "2";
    // END   2018/08/07 H.Ikeda [QC#27569,ADD]

    // START 2018/08/07 H.Ikeda [QC#28839,ADD]
    private HashMap<BigDecimal, String> billToCustAcctCdMap = new HashMap<BigDecimal, String>();
    // END   2018/08/07 H.Ikeda [QC#28839,ADD]

    // START 2021/06/07 G.Delgado [QC#58800,ADD]
    /** Check number pattern : ^%s[A-Z]*$ */
    private static final String CHECK_NUM_PATTERN = "^%s[A-Z]*$";

    /** Check number max length : 15 */
    private static final int CHECK_NUM_MAX_LEN = 15;

    /** Check number last sequence */
    private static final String CHECK_NUM_LAST_SEQ = new String(new char[CHECK_NUM_MAX_LEN]).replace("\0", "Z");

    /** A */
    private static final char CHAR_A = 'A';

    /** B */
    private static final char CHAR_Z = 'Z';
    // END 2021/06/07 G.Delgado [QC#58800,ADD]

    /**
     * Initialization method
     */
    @Override
    protected void initRoutine() {
        debugLog("initRoutine start");

        S21InfoLogOutput.println(NFCM0584I, PROGRAM_ID);

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // START 2017/01/04 E.Kameishi [QC#16536,ADD]
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        // END 2017/01/04 E.Kameishi [QC#16536,ADD]
        if (!getGlblCmpyCd()) {
            execAbendException(NFCM0501E, MSG_STR_GLBL_CMPY_CD);
        }
//Def#2601
//        if (!getComnBankBrRteNum()) {
//            execAbendException(NFCM0501E, MSG_STR_COMN_BANK_BR_RTE_NUM);
//        }

        if (!getGlobalCompany()) {
            execAbendException(NFCM0501E, MSG_STR_GLBL_CMPY);
        }

        this.batchProcDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        if (S21StringUtil.isEmpty(this.batchProcDt)) {
            execAbendException(NFCM0574E);
        }

        // START 2018/07/04 H.Ikeda [QC#25731,ADD]
        String num = getUserVariable1();
        if (ZYPCommonFunc.hasValue(num)) {
            this.multipleCnt = num;
        }

        num = getUserVariable2();
        if (ZYPCommonFunc.hasValue(num)) {
            this.multipleNo = Integer.parseInt(num);
        } else {
            this.multipleNo = defMultipleNo;
        }
        // END   2018/07/04 H.Ikeda [QC#25731,ADD]

        debugLog("initRoutine end");
    }

    /**
     * Main method
     */
    @Override
    protected void mainRoutine() {
        debugLog("mainRoutine start");

        execute();

        debugLog("mainRoutine end");
    }

    /**
     * Termination method
     */
    @Override
    protected void termRoutine() {
        debugLog("termRoutine start");

        TERM_CD termCd = TERM_CD.NORMAL_END;

        // mod 2016/01/07 no warning state
        /*
         * if (this.errCnt > 0) { termCd = TERM_CD.WARNING_END; }
         */

        debugLog("termRoutine : termCd=<" + termCd + "> normalCnt=<" + this.normalCnt + "> errCnt=<" + this.errCnt + "> procCount=<" + this.procCount + ">");
        setTermState(termCd, this.normalCnt, this.errCnt, this.procCount);

        S21InfoLogOutput.println(NFCM0593I, PROGRAM_ID);

        debugLog("termRoutine end");
    }

    /**
     * Main method that is called from batch
     * @param args parameter
     */
    public static void main(String[] args) {
        new NFCB053001().executeBatch(NFCB053001.class.getSimpleName());
    }

    /**
     * Get Global Company Code
     * @return boolean true:Normal false:Failure
     */
    private boolean getGlblCmpyCd() {
        debugLog("getGlblCmpyCd start");

        /* Get Global Company Code */
        this.glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        if (S21StringUtil.isEmpty(this.glblCmpyCd)) {
            debugLog("getGlblCmpyCd : isEmpty error");
            return false;
        }

        debugLog("getGlblCmpyCd end");
        return true;
    }

    /**
     * Get Common Bank Branch Routing Number from Batch Parameter
     * @reutrn boolean true:Normal false:Failure
     */
    private boolean getComnBankBrRteNum() {
        debugLog("getComnBankBrRteNum start");

        this.comnBankBrRteNum = getUserVariable1();
        if (S21StringUtil.isEmpty(this.comnBankBrRteNum)) {
            debugLog("getComnBankBrRteNum : getUserVariable2 error");
            return false;
        }

        debugLog("getComnBankBrRteNum end");
        return true;
    }

    /**
     * Receipt Data Creation
     */
    private void execute() {
        debugLog("execute start");

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = getArBatRcptNmStatement();

            rs = stmt.executeQuery();
            while (rs.next()) {
                String arBatRcptNm = rs.getString("AR_BAT_RCPT_NM");
                String rcvFuncId = rs.getString("RCV_FUNC_ID"); //ADD 2018/02/28 S21_NA#24465

                // MOD START 2018/02/28 S21_NA#24465
                TERM_CD termCd = doProcess(arBatRcptNm, rcvFuncId);
//                TERM_CD termCd = doProcess(arBatRcptNm);
                // MOD START 2018/02/28 S21_NA#24465
                if (TERM_CD.NORMAL_END.equals(termCd)) {
                    this.normalCnt++;
                    this.procCount++;

                } else if (TERM_CD.WARNING_END.equals(termCd)) {
                    this.errCnt++;
                    this.procCount++;

                }
            }

            for (String errorLockBoxFileNm : this.errorLockBoxFileNms) {
                this.updateArLockBoxStsToError(errorLockBoxFileNm);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            debugLog("execute : closeResource");
        }

        debugLog("execute end");
    }

//    private TERM_CD doProcess_org(String arBatRcptNm, String rcvFuncId) throws SQLException { // ADD PARAM 2018/02/28 S21_NA#24465
//        TERM_CD termCd = TERM_CD.NORMAL_END;
//
//        Map<String, List<AR_RCPT_RCV_INTFCTMsg>> custRcptNumIntfcListMap = getCustRcptNumIntfcListMap(arBatRcptNm);
//
//        List<ResultDataBean> resultDataList = new ArrayList<ResultDataBean>();
//        List<AR_RCPT_RCV_INTFCTMsg> updList = new ArrayList<AR_RCPT_RCV_INTFCTMsg>();
//
//        for (Entry<String, List<AR_RCPT_RCV_INTFCTMsg>> custRcptNumIntfcList : custRcptNumIntfcListMap.entrySet()) {
//
//            List<AR_RCPT_RCV_INTFCTMsg> intfcInfoList = custRcptNumIntfcList.getValue();
//
//            ResultDataBean resultData = new ResultDataBean();
//
//            //boolean first = true;
//
//            // intfcInfoList: List of AR_RCPT_RCV_INTFC whose
//            // CUST_RCPT_NUMs are same
//            // START 2017/01/04 E.Kameishi [QC#16536,MOD]
//            procFlg = true;
//            dsBankAcctPk = null;
//            // END 2017/01/04 E.Kameishi [QC#16536,MOD]
//            // START 2017/06/06 E.Kameishi [QC#18604,ADD]
//            List<AR_RCPT_RCV_INTFCTMsg> arRcptRcvIntfcList = new ArrayList<AR_RCPT_RCV_INTFCTMsg>();
//
//            // ADD START 2018/02/28 S21_NA#24465
//            if (NFCI1050.equals(rcvFuncId)) {
//                for (AR_RCPT_RCV_INTFCTMsg intfcInfo : intfcInfoList) {
//
//                    if (AR_CUST_ID_STS.SAVED.equals(intfcInfo.arCustIdStsCd.getValue())) {
//
//                        AccountInfoBean accountData = this.identifyCustomerAccount(intfcInfo, resultData);
//                        this.registerCustomerAccount(intfcInfo, accountData, resultData, rcvFuncId);
//
//                        arRcptRcvIntfcList.add(updateArRcptRcvIntfc(intfcInfo, resultData));
//                        updList.addAll(arRcptRcvIntfcList);
//                        resultDataList.add(resultData);
//                    }
//                    this.masterSave(resultData);
//                    resultData = new ResultDataBean();
//                    procFlg = true;
//                    dsBankAcctPk = null;
//                }
//                continue;
//            }
//            // ADD END 2018/02/28 S21_NA#24465
//
//            // Add Start 2018/01/19 S21_NA#22421
//            AccountInfoBean accountData = null;
//            accountData = this.identifyCustomerAccount(intfcInfoList, resultData);
//            // And End 2018/01/24 S21_NA#22421
//            for (AR_RCPT_RCV_INTFCTMsg intfcInfo : intfcInfoList) {
//
//                if (AR_CUST_ID_STS.SAVED.equals(intfcInfo.arCustIdStsCd.getValue())) {
//                    // Mod Start 2018/01/19 S21_NA#22421
//                    this.registerCustomerAccount(intfcInfo, accountData, resultData, rcvFuncId);
////                    ResultDataBean checkedData = this.registerCustomerAccount(intfcInfo, resultDataList);
//                    // Mod Start 2018/01/19 S21_NA#22421
//
//                    //first = false;
//
//                    // Mod Start 2018/01/19 S21_NA#22421
//                    arRcptRcvIntfcList.add(updateArRcptRcvIntfc(intfcInfo, resultData));
//                 // arRcptRcvIntfcList.add(updateArRcptRcvIntfc(intfcInfo, checkedData));
//                 // resultDataList.add(checkedData);
//                    // Mod End 2018/01/19 S21_NA#22421
//                    // When customer is successfully identified
//                    //if (checkedData.getAccountInfo() != null) {
//                        // ---------- mod 2016/01/17 No Warning State
//                        /*
//                         * if (resultData.getErrorIntfcPk() != null) {
//                         * // termCd = TERM_CD.WARNING_END; }
//                         */
//                        // ---------- end 2016/01/07
//                        //break; // go to next CUST_RCPT_NUM
//                    //}
//                    resultDataList.add(resultData);
//                }
//            }
//
//            //updList.addAll(updateArRcptRcvIntfcList(intfcInfoList, resultData));
//            updList.addAll(arRcptRcvIntfcList);
//            //resultDataList.add(resultData);
//            // END 2017/06/06 E.Kameishi [QC#18604,MOD]
//
//            // MOD START 2018/02/28 S21_NA#24219 
//            //this.save(updList, resultData, termCd); // Add 2018/01/19 S21_NA#22421
//            this.masterSave(resultData);
//            // MOD END 2018/02/28 S21_NA#24219 
//        }
//
////        this.save(updList, resultDataList, termCd); // Del 2018/01/19 S21_NA#22421
//        this.save(updList, resultDataList); // ADD 2018/02/28 S21_NA#24219
//        return termCd;
//    }

    private TERM_CD doProcess(String arBatRcptNm, String rcvFuncId) throws SQLException {
        TERM_CD termCd = TERM_CD.NORMAL_END;
        // MOD Start 2018/07/04 QC#25731
        //Map<String, List<AR_RCPT_RCV_INTFCTMsg>> custRcptNumIntfcListMap = getCustRcptNumIntfcListMap(arBatRcptNm);
        Map<String, List<AR_RCPT_RCV_INTFCTMsg>> custRcptNumIntfcListMap = getCustRcptNumIntfcListMap(arBatRcptNm, rcvFuncId);
        // MOD End   2018/07/04 QC#25731
        List<ResultDataBean> resultDataList = new ArrayList<ResultDataBean>();
        List<AR_RCPT_RCV_INTFCTMsg> updList = new ArrayList<AR_RCPT_RCV_INTFCTMsg>();

        for (Entry<String, List<AR_RCPT_RCV_INTFCTMsg>> custRcptNumIntfcList : custRcptNumIntfcListMap.entrySet()) {

            List<AR_RCPT_RCV_INTFCTMsg> intfcInfoList = custRcptNumIntfcList.getValue();

            ResultDataBean resultData = new ResultDataBean();

            procFlg = true;
            dsBankAcctPk = null;

            List<AR_RCPT_RCV_INTFCTMsg> arRcptRcvIntfcList = new ArrayList<AR_RCPT_RCV_INTFCTMsg>();

            if (NFCI1050.equals(rcvFuncId)) {
                List<List<String>> lDataList = new ArrayList<List<String>>();
                for (AR_RCPT_RCV_INTFCTMsg intfcInfo : intfcInfoList) {

                    if (AR_CUST_ID_STS.SAVED.equals(intfcInfo.arCustIdStsCd.getValue())) {

                        AccountInfoBean accountData = this.identifyCustomerAccount(intfcInfo, resultData);
                        this.registerCustomerAccount(lDataList, intfcInfo, accountData, resultData, rcvFuncId);

                        arRcptRcvIntfcList.add(updateArRcptRcvIntfc(intfcInfo, resultData));
                        updList.addAll(arRcptRcvIntfcList);
                        resultDataList.add(resultData);
                    }
                    this.masterSave(resultData);
                    resultData = new ResultDataBean();
                    procFlg = true;
                    dsBankAcctPk = null;
                }
                continue;
            }

            AccountInfoBean accountData = null;
            accountData = this.identifyCustomerAccount(intfcInfoList, resultData);

            List<List<String>> lDataList = new ArrayList<List<String>>();
            // ADD START  2019/05/28 S21_NA#50544
            boolean chkFlg = false;
            // ADD END    2019/05/28 S21_NA#50544
            for (AR_RCPT_RCV_INTFCTMsg intfcInfo : intfcInfoList) {

                if (AR_CUST_ID_STS.SAVED.equals(intfcInfo.arCustIdStsCd.getValue())) {

                    this.registerCustomerAccount(lDataList, intfcInfo, accountData, resultData, rcvFuncId);

                    arRcptRcvIntfcList.add(updateArRcptRcvIntfc(intfcInfo, resultData));

                    // MOD START  2019/05/28 S21_NA#50544
                    //resultDataList.add(resultData);
                    chkFlg = true;
                }
            }
            if (chkFlg) {
                resultDataList.add(resultData);
            }
            // MOD END    2019/05/28 S21_NA#50544

            updList.addAll(arRcptRcvIntfcList);

            this.masterSave(resultData);
        }
        this.save(updList, resultDataList); // ADD 2018/02/28 S21_NA#24219
        return termCd;
    }
    
    private List<AR_RCPT_RCV_INTFCTMsg> updateArRcptRcvIntfcList(List<AR_RCPT_RCV_INTFCTMsg> intfcInfoList, ResultDataBean resultData) {
        List<AR_RCPT_RCV_INTFCTMsg> updList = new ArrayList<AR_RCPT_RCV_INTFCTMsg>();

        for (AR_RCPT_RCV_INTFCTMsg intfcInfo : intfcInfoList) {
            AR_RCPT_RCV_INTFCTMsg arRcptRcvIntfc = updateArRcptRcvIntfc(intfcInfo, resultData);

            updList.add(arRcptRcvIntfc);
        }

        return updList;
    }

    private PreparedStatement getArBatRcptNmStatement() throws SQLException {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
        queryParam.put("arCustIdStsNotComp", AR_CUST_ID_STS.SAVED);
        queryParam.put("arLockBoxStsWorkInProgress", AR_LOCK_BOX_STS.WORK_IN_PROGRESS);

        return this.ssmLLClient.createPreparedStatement("getArBatRcptNm", queryParam, execParam);
    }

    // DEL START 2018/02/26 S21_NA#24219
//    private String getArBatRcptNm(ResultSet rs) throws SQLException {
//        return rs.getString("AR_BAT_RCPT_NM");
//    }
    // DEL END 2018/02/26 S21_NA#24219

    // MOD START 2018/07/04 QC#25731
    //private Map<String, List<AR_RCPT_RCV_INTFCTMsg>> getCustRcptNumIntfcListMap(String arBatRcptNm) throws SQLException {
    private Map<String, List<AR_RCPT_RCV_INTFCTMsg>> getCustRcptNumIntfcListMap(String arBatRcptNm, String rcvFuncId) throws SQLException {

        Map<String, List<AR_RCPT_RCV_INTFCTMsg>> map = new HashMap<String, List<AR_RCPT_RCV_INTFCTMsg>>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (ZYPCommonFunc.hasValue(this.multipleCnt)) {
            List<String> lineList = new ArrayList<String>();
            Map<String, Object> sqlParam = new HashMap<String, Object>();
            sqlParam.put("glblCmpyCd", this.glblCmpyCd);
            sqlParam.put("arBatRcptNm", arBatRcptNm);
            sqlParam.put("rcvFuncId", rcvFuncId);
            List<Map<String,Object>> ifIdList = (List<Map<String,Object>>)ssmBatchClient.queryObjectList("getLineNum", sqlParam);
            int cnt = Integer.parseInt(this.multipleCnt);
            for (int i = 0; i < ifIdList.size(); i++) {
                String lineNum = ifIdList.get(i).get("AR_LOCK_BOX_BAT_LINE_NUM").toString();
                int no = chgStr(lineNum) % this.multipleNo;
                if (no == cnt) {
                    lineList.add(lineNum);
                }
            }
            if (lineList.size() != 0) {
                String[] lineStr = new String[lineList.size()];
                for (int i = 0; i < lineList.size(); i++) {
                    lineStr[i] = lineList.get(i);
                }
                sqlParam.put("lineList", lineStr);
                ps = this.ssmLLClient.createPreparedStatement("getArRcptRcvIntfc", sqlParam);
                rs = ps.executeQuery();
                while(rs.next()) {
                    AR_RCPT_RCV_INTFCTMsg inMsg = createArRcptRcvIntfcTMsg(rs);

                    String custRcptNum = inMsg.custRcptNum.getValue();
                    List<AR_RCPT_RCV_INTFCTMsg> list = map.get(custRcptNum);

                    if (list == null) {
                        list = new ArrayList<AR_RCPT_RCV_INTFCTMsg>();
                        map.put(custRcptNum, list);
                    }
                    list.add(inMsg);
                }
            }
            return map;
        } else {
            AR_RCPT_RCV_INTFCTMsg inMsg = new AR_RCPT_RCV_INTFCTMsg();
            inMsg.setConditionValue("arBatRcptNm01", arBatRcptNm);
            inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            inMsg.setSQLID("002");
            // retrieve AR_RCPT_RCV_INTFC by target batch name
            AR_RCPT_RCV_INTFCTMsgArray tMsgArray = (AR_RCPT_RCV_INTFCTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);

            for (int i = 0; i < tMsgArray.getValidCount(); i++) {
                AR_RCPT_RCV_INTFCTMsg intfcInfo = tMsgArray.no(i);

                String custRcptNum = intfcInfo.custRcptNum.getValue();
                List<AR_RCPT_RCV_INTFCTMsg> list = map.get(custRcptNum);

                if (list == null) {
                    list = new ArrayList<AR_RCPT_RCV_INTFCTMsg>();
                    map.put(custRcptNum, list);
                }

                list.add(intfcInfo);
            }
            return map;
        }
        // MOD END   2018/07/04 QC#25731
    }

    // ADD START   2018/07/04 QC#25731
    private AR_RCPT_RCV_INTFCTMsg createArRcptRcvIntfcTMsg(ResultSet rs) throws SQLException{
        AR_RCPT_RCV_INTFCTMsg inMsg = new AR_RCPT_RCV_INTFCTMsg();
        
        ZYPEZDItemValueSetter.setValue(inMsg.ezTableID,             rs.getString("EZTABLEID"));
        ZYPEZDItemValueSetter.setValue(inMsg.ezCancelFlag,          rs.getString("EZCANCELFLAG"));
        ZYPEZDItemValueSetter.setValue(inMsg.ezInTime,              rs.getString("EZINTIME"));
        ZYPEZDItemValueSetter.setValue(inMsg.ezInTimeZone,          rs.getString("EZINTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(inMsg.ezInCompanyCode,       rs.getString("EZINCOMPANYCODE"));
        ZYPEZDItemValueSetter.setValue(inMsg.ezInUserID,            rs.getString("EZINUSERID"));
        ZYPEZDItemValueSetter.setValue(inMsg.ezInAplID,             rs.getString("EZINAPLID"));
        ZYPEZDItemValueSetter.setValue(inMsg.ezUpTime,              rs.getString("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(inMsg.ezUpTimeZone,          rs.getString("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(inMsg.ezUpCompanyCode,       rs.getString("EZUPCOMPANYCODE"));
        ZYPEZDItemValueSetter.setValue(inMsg.ezUpUserID,            rs.getString("EZUPUSERID"));
        ZYPEZDItemValueSetter.setValue(inMsg.ezUpAplID,             rs.getString("EZUPAPLID"));
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd,            this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.arRcptRcvIntfcPk,      rs.getBigDecimal("AR_RCPT_RCV_INTFC_PK"));
        ZYPEZDItemValueSetter.setValue(inMsg.rcvFuncId,             rs.getString("RCV_FUNC_ID"));
        ZYPEZDItemValueSetter.setValue(inMsg.rcvDt,                 rs.getString("RCV_DT"));
        ZYPEZDItemValueSetter.setValue(inMsg.depDt,                 rs.getString("DEP_DT"));
        ZYPEZDItemValueSetter.setValue(inMsg.depTm,                 rs.getString("DEP_TM"));
        ZYPEZDItemValueSetter.setValue(inMsg.arBatRcptNm,           rs.getString("AR_BAT_RCPT_NM"));
        ZYPEZDItemValueSetter.setValue(inMsg.remBankRteNum,         rs.getString("REM_BANK_RTE_NUM"));
        ZYPEZDItemValueSetter.setValue(inMsg.remDsBankAcctNum,      rs.getString("REM_DS_BANK_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(inMsg.remDsBankMicrNum,      rs.getString("REM_DS_BANK_MICR_NUM"));
        ZYPEZDItemValueSetter.setValue(inMsg.remDsBankAcctPk,       rs.getBigDecimal("REM_DS_BANK_ACCT_PK"));
        ZYPEZDItemValueSetter.setValue(inMsg.custBankRteNum,        rs.getString("CUST_BANK_RTE_NUM"));
        ZYPEZDItemValueSetter.setValue(inMsg.custDsBankAcctNum,     rs.getString("CUST_DS_BANK_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(inMsg.custDsBankMicrNum,     rs.getString("CUST_DS_BANK_MICR_NUM"));
        ZYPEZDItemValueSetter.setValue(inMsg.custDsBankAcctPk,      rs.getBigDecimal("CUST_DS_BANK_ACCT_PK"));
        ZYPEZDItemValueSetter.setValue(inMsg.custAcctRefNum,        rs.getString("CUST_ACCT_REF_NUM"));
        ZYPEZDItemValueSetter.setValue(inMsg.payerCustCd,           rs.getString("PAYER_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.billToCustCd,          rs.getString("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.custRcptNum,           rs.getString("CUST_RCPT_NUM"));
        ZYPEZDItemValueSetter.setValue(inMsg.vldCustRcptNum,        rs.getString("VLD_CUST_RCPT_NUM"));
        ZYPEZDItemValueSetter.setValue(inMsg.custRcptAmt,           rs.getBigDecimal("CUST_RCPT_AMT"));
        ZYPEZDItemValueSetter.setValue(inMsg.custInvNum,            rs.getString("CUST_INV_NUM"));
        ZYPEZDItemValueSetter.setValue(inMsg.arTrxNum,              rs.getString("AR_TRX_NUM"));
        ZYPEZDItemValueSetter.setValue(inMsg.grpInvNum,             rs.getString("GRP_INV_NUM"));
        ZYPEZDItemValueSetter.setValue(inMsg.custInvAmt,            rs.getBigDecimal("CUST_INV_AMT"));
        ZYPEZDItemValueSetter.setValue(inMsg.arRcptSrcCd,           rs.getString("AR_RCPT_SRC_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.arRcptRcvErrFlg,       rs.getString("AR_RCPT_RCV_ERR_FLG"));
        ZYPEZDItemValueSetter.setValue(inMsg.arCustIdStsCd,         rs.getString("AR_CUST_ID_STS_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.arRcptRcvWrkCratFlg,   rs.getString("AR_RCPT_RCV_WRK_CRAT_FLG"));
        ZYPEZDItemValueSetter.setValue(inMsg.arLockBoxStsCd,        rs.getString("AR_LOCK_BOX_STS_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.arLockBoxFileNm,       rs.getString("AR_LOCK_BOX_FILE_NM"));
        ZYPEZDItemValueSetter.setValue(inMsg.arLockBoxBatNum,       rs.getString("AR_LOCK_BOX_BAT_NUM"));
        ZYPEZDItemValueSetter.setValue(inMsg.arLockBoxBatLineNum,   rs.getString("AR_LOCK_BOX_BAT_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(inMsg.arLockBoxBatLineSqNum, rs.getString("AR_LOCK_BOX_BAT_LINE_SQ_NUM"));
        ZYPEZDItemValueSetter.setValue(inMsg.arLockBoxCd,           rs.getString("AR_LOCK_BOX_CD"));
        ZYPEZDItemValueSetter.setValue(inMsg.hdrLockBoxNumTxt,      rs.getString("HDR_LOCK_BOX_NUM_TXT"));
        ZYPEZDItemValueSetter.setValue(inMsg.batLockBoxNumTxt,      rs.getString("BAT_LOCK_BOX_NUM_TXT"));
        ZYPEZDItemValueSetter.setValue(inMsg.trailLockBoxNumTxt,    rs.getString("TRAIL_LOCK_BOX_NUM_TXT"));
        ZYPEZDItemValueSetter.setValue(inMsg.arLockBoxFileErrFlg,   rs.getString("AR_LOCK_BOX_FILE_ERR_FLG"));
        ZYPEZDItemValueSetter.setValue(inMsg.intfcFileRecCnt,       rs.getBigDecimal("INTFC_FILE_REC_CNT"));
        ZYPEZDItemValueSetter.setValue(inMsg.lockBoxRecCnt,         rs.getBigDecimal("LOCK_BOX_REC_CNT"));
        ZYPEZDItemValueSetter.setValue(inMsg.lockBoxTotAmt,         rs.getBigDecimal("LOCK_BOX_TOT_AMT"));
        ZYPEZDItemValueSetter.setValue(inMsg.batRcptRecCnt,         rs.getBigDecimal("BAT_RCPT_REC_CNT"));
        ZYPEZDItemValueSetter.setValue(inMsg.batRcptTotAmt,         rs.getBigDecimal("BAT_RCPT_TOT_AMT"));
        // Add Start 2018/11/16 S21_NA#29278
        ZYPEZDItemValueSetter.setValue(inMsg.lockBoxNtfyStsCd,      rs.getString("LOCK_BOX_NTFY_STS_CD"));
        // Add End   2018/11/16 S21_NA#29278
        return inMsg;
    }

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
    // ADD END   2018/07/04 QC#25731
    
 	// Del Start 2018/01/19 S21_NA#22421
//    private ResultDataBean identifyCustomerAccount(AR_RCPT_RCV_INTFCTMsg intfcInfo, List<ResultDataBean> resultDataList) throws SQLException {
//        BigDecimal arRcptRcvIntfcPk = intfcInfo.arRcptRcvIntfcPk.getValue();
//        String custBankMicrNum = intfcInfo.custDsBankMicrNum.getValue();
//
//        // START 2017/09/05 T.Tsuchida [QC#20939,MOD]
//        //START 2017/06/02 E.Kameishi [QC#18703, ADD]
//        //String arLockBoxCd = intfcInfo.arLockBoxCd.getValue();
//        String rcvFuncId = intfcInfo.rcvFuncId.getValue();
//        ResultDataBean resultData = new ResultDataBean();
//        //END 2017/06/02 E.Kameishi [QC#18703, ADD]
//        // END 2017/09/05 T.Tsuchida [QC#20939,MOD]
//
//        //Def#5061
//        custBankMicrNum = custBankMicrNum.replace(" ", "");
//
//        // 1) Check by customer account number
//        AccountInfoBean identifiedAccountInfo = identifyByDsAcctNum(arRcptRcvIntfcPk, intfcInfo.custAcctRefNum.getValue(), resultData);
//
//        if (identifiedAccountInfo == null) {
//            // START 2017/09/05 T.Tsuchida [QC#20939,MOD]
//            //START 2017/06/05 E.Kameishi [QC#18703, MOD]
//            //if (first) {
//                // 2) check by MICR info
////                identifiedAccountInfo = identifyByMicr(arRcptRcvIntfcPk, intfcInfo.custDsBankMicrNum.getValue(), resultData);
////                if (!AR_LOCK_BOX.CFS_LOCKBOX.equals(arLockBoxCd)) {
//                if (!NFCI1050.equals(rcvFuncId)) {
//                    identifiedAccountInfo = identifyByMicr(arRcptRcvIntfcPk, custBankMicrNum, resultData);
//                }
//            //}
//            //END 2017/06/05 E.Kameishi [QC#18703, MOD]
//            // END 2017/09/05 T.Tsuchida [QC#20939,MOD]
//        }
//
//        if (identifiedAccountInfo == null) {
//            // 3) Check by Bill#/Invoice#
//            identifiedAccountInfo = identifyByInvoiceNum(arRcptRcvIntfcPk, intfcInfo.custInvNum.getValue(), resultData);
//        }
//
//        // When customer is successfully identified.
//        if (identifiedAccountInfo != null) {
//            if (ZYPCommonFunc.hasValue(custBankMicrNum) && identifiedAccountInfo.getCustDsBankAcctPk() == null) {
//
//                //Def#5016
//                // Create DS_BANK_ACCT
//                BigDecimal custDsBankAcctPk = registerBankAccount(intfcInfo, identifiedAccountInfo, resultData, custBankMicrNum);
//                identifiedAccountInfo.setCustDsBankAcctPk(custDsBankAcctPk);
//            }
//
//            resultData.setAccountInfo(identifiedAccountInfo);
//
//            if (ZYPCommonFunc.hasValue(intfcInfo.arLockBoxFileNm)) {
//
//                boolean hasError = hasConsistencyError(intfcInfo, identifiedAccountInfo, resultData);
//
//                if (hasError) {
//                    resultData.setErrorIntfcPk(arRcptRcvIntfcPk);
//
//                    String lockBoxFileNm = intfcInfo.arLockBoxFileNm.getValue();
//
//                    if (ZYPCommonFunc.hasValue(lockBoxFileNm)) {
//                        this.errorLockBoxFileNms.add(lockBoxFileNm);
//                    }
//
//                } else {
//                    resultData.setVldCustRcptNum(intfcInfo.custRcptNum.getValue());
//                }
//            }
//        } else {
//            if (ZYPCommonFunc.hasValue(custBankMicrNum)) { // ADD 2016/12/16 [QC#16530]
//                // START 2017/01/04 E.Kameishi [QC#16536,ADD]
//                if (procFlg) {
//                    // Def#2066
//                    BigDecimal bankAcctPK = registerBankAccount_UnidentifiedAccountInfo(intfcInfo, identifiedAccountInfo, resultData, custBankMicrNum, resultDataList);
//
//                    if (bankAcctPK != null) {
//                        dsBankAcctPk = bankAcctPK;
//                        procFlg = false;
//                    }
//                }
//                ZYPEZDItemValueSetter.setValue(intfcInfo.custDsBankAcctPk, dsBankAcctPk);
//             // END 2017/01/04 E.Kameishi [QC#16536,ADD]
//            } // ADD 2016/12/16 [QC#16530]
//        }
//
//        return resultData;
//    }
 	// Del End 2018/01/19 S21_NA#22421

    private AccountInfoBean identifyByDsAcctNum(BigDecimal arRcptRcvIntfcPk, String dsAcctNum, ResultDataBean resultData) throws SQLException {
        if (!ZYPCommonFunc.hasValue(dsAcctNum)) {
            return null;
        }

        AccountInfoBean accountInfo = getCustomerAccount(dsAcctNum);

        if (accountInfo == null) {
            String message = EZDMessageEnv.getI18nMessage(NFCM0736W);

            AR_RCPT_RCV_INFO_WRKTMsg arRcptRcvInfoWrk = registValidationResult(arRcptRcvIntfcPk, message, AR_BAT_INFO_LVL.WARNING);
            resultData.addArRcptRcvInfoWrk(arRcptRcvInfoWrk);
        }

        return accountInfo;
    }

    private AccountInfoBean getCustomerAccount(String dsAcctNum) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            final Map<String, String> ssmParam = new HashMap<String, String>();

            ssmParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);

            // mod 2016/01/07 customer existance check by SELL_TO_CUST
            // ssmParam.put("dsAcctNum", dsAcctNum);
            // ssmParam.put("dsAcctRelnTpCdBillTo",
            // DS_ACCT_RELN_TP.BILL_TO);
            // ssmParam.put("procDt", this.batchProcDt);
            ssmParam.put("cust", dsAcctNum);

            stmt = this.ssmLLClient.createPreparedStatement("customerCheck", ssmParam);
            // End

            rs = stmt.executeQuery();

            if (rs.next()) {
                AccountInfoBean accountInfo = new AccountInfoBean();

                accountInfo.setPayerCustCd(rs.getString("PAYER_CUST_CD"));
                accountInfo.setDsAcctCustPk(rs.getBigDecimal("DS_ACCT_CUST_PK"));

                return accountInfo;
            }

            return null;

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            debugLog("execute : closeResource");
        }
    }

    // MOD START 2018/05/21 QC#25769
    //private AccountInfoBean identifyByMicr(BigDecimal arRcptRcvIntfcPk, String custBankMicrNum, ResultDataBean resultData) throws SQLException {
    private AccountInfoBean identifyByMicr(BigDecimal arRcptRcvIntfcPk, String custBankMicrNum, ResultDataBean resultData,  String arLockBoxCd) throws SQLException {
    // MOD END   2018/05/21 QC#25769
        if (!ZYPCommonFunc.hasValue(custBankMicrNum)) {
            return null;
        }
        // MOD START 2018/05/22 QC#25769
        //List<AccountInfoBean> accountInfoList = getCustomerAccountByMicr(custBankMicrNum);
        List<AccountInfoBean> accountInfoList = getCustomerAccountByMicr(custBankMicrNum, arLockBoxCd);
        // MOD END   2018/05/22 QC#25769

        int accountInfoCount = accountInfoList.size();

        if (accountInfoCount == 0) {
            // mod 2016/01/07
            /*
             * String message =
             * EZDMessageEnv.getI18nMessage(NFCM0737W);
             * AR_RCPT_RCV_INFO_WRKTMsg arRcptRcvInfoWrk =
             * registValidationResult(arRcptRcvIntfcPk, message,
             * AR_BAT_INFO_LVL.WARNING);
             * resultData.addArRcptRcvInfoWrk(arRcptRcvInfoWrk);
             */

            // Redo search without effective date condition
            // MOD START 2018/05/22 QC#25769
            //accountInfoList = getCustAcctByMicrWODateCond(custBankMicrNum);
            accountInfoList = getCustAcctByMicrWODateCond(custBankMicrNum, arLockBoxCd);
            // MOD END   2018/05/22 QC#25769

            // If hit one record
            if (accountInfoList.size() == 1) {
                return accountInfoList.get(0);
            } else if (accountInfoList.size() == 0) {
                String message = EZDMessageEnv.getI18nMessage(NFCM0737W);

                AR_RCPT_RCV_INFO_WRKTMsg arRcptRcvInfoWrk = registValidationResult(arRcptRcvIntfcPk, message, AR_BAT_INFO_LVL.WARNING);
                resultData.addArRcptRcvInfoWrk(arRcptRcvInfoWrk);
                // If hit multiple records
            } else if (accountInfoList.size() > 1) {

                AccountInfoBean mostFuture = accountInfoList.get(0);

                // If most future effective from date is previous
                // date, take this record
                if (mostFuture.getEffFromDt().compareTo(this.batchProcDt) <= 0) {
                    return mostFuture;
                }

                AccountInfoBean mostPast = accountInfoList.get(accountInfoList.size() - 1);

                // If most past effective from date is future date,
                // take this record
                if (mostPast.getEffFromDt().compareTo(this.batchProcDt) >= 0) {
                    return mostPast;
                }

            }

            // mod 2016/01/07
            /*
             * } else if (accountInfoCount > 1) { String message =
             * EZDMessageEnv.getI18nMessage(NFCM0738W);
             * AR_RCPT_RCV_INFO_WRKTMsg arRcptRcvInfoWrk =
             * registValidationResult(arRcptRcvIntfcPk, message,
             * AR_BAT_INFO_LVL.WARNING);
             * resultData.addArRcptRcvInfoWrk(arRcptRcvInfoWrk); }
             * else {
             */
        } else if (accountInfoCount == 1) {
            return accountInfoList.get(0);
        }

        return null;
    }

    // MOD START 2018/05/22 QC#25769
    //private List<AccountInfoBean> getCustAcctByMicrWODateCond(String custBankMicrNum) throws SQLException {
    private List<AccountInfoBean> getCustAcctByMicrWODateCond(String custBankMicrNum, String arLockBoxCd) throws SQLException {
    // MOD END   2018/05/22 QC#25769

        List<AccountInfoBean> accountInfoList = new ArrayList<AccountInfoBean>(1);

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            final Map<String, String> ssmParam = new HashMap<String, String>();

            ssmParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
            ssmParam.put("custBankMicrNum", custBankMicrNum);
            ssmParam.put("dsBankAcctTpCdExternal", DS_BANK_ACCT_TP.EXTERNAL);
            // ssmParam.put("dsAcctRelnTpCdBillTo",
            // DS_ACCT_RELN_TP.BILL_TO);
            // ssmParam.put("procDt", this.batchProcDt);
            ssmParam.put("isDtCond", null);
            // ADD START 2018/05/22 QC#25769
            ssmParam.put("arLockBoxCd", arLockBoxCd);
            // ADD END   2018/05/22 QC#25769
            // MOD START 201/02/26 S21_NA#24219
//            stmt = this.ssmLLClient.createPreparedStatement("getCustomerAccountByMicr", ssmParam);
            stmt = this.ssmLLClient.createPreparedStatement("getCustomerAccountByMicrWODateCond", ssmParam);
            // MOD END 201/02/26 S21_NA#24219

            rs = stmt.executeQuery();

            while (rs.next()) {
                AccountInfoBean accountInfo = new AccountInfoBean();

                accountInfo.setCustDsBankAcctPk(rs.getBigDecimal("CUST_DS_BANK_ACCT_PK"));
                accountInfo.setPayerCustCd(rs.getString("PAYER_CUST_CD"));
                accountInfo.setDsAcctCustPk(rs.getBigDecimal("DS_ACCT_CUST_PK"));
                accountInfo.setEffFromDt(rs.getString("EFF_FROM_DT"));

                accountInfoList.add(accountInfo);
            }

            return accountInfoList;

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            debugLog("execute : closeResource");
        }
    }

    // MOD START 2018/05/22 QC#25769
    //private List<AccountInfoBean> getCustomerAccountByMicr(String custBankMicrNum) throws SQLException {
    private List<AccountInfoBean> getCustomerAccountByMicr(String custBankMicrNum, String arLockBoxCd) throws SQLException {
    // MOD END   2018/05/22 QC#25769

        List<AccountInfoBean> accountInfoList = new ArrayList<AccountInfoBean>(1);

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            final Map<String, String> ssmParam = new HashMap<String, String>();

            ssmParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
            ssmParam.put("custBankMicrNum", custBankMicrNum);
            ssmParam.put("dsBankAcctTpCdExternal", DS_BANK_ACCT_TP.EXTERNAL);
            // START 2017/11/15 [QC#17322, DEL]
            // ssmParam.put("dsAcctRelnTpCdBillTo", DS_ACCT_RELN_TP.BILL_TO);
            // END   2017/11/15 [QC#17322, DEL]
            ssmParam.put("procDt", this.batchProcDt);
            ssmParam.put("isDtCond", ZYPConstant.FLG_ON_Y);
            // ADD START 2018/05/22 QC#25769
            ssmParam.put("arLockBoxCd", arLockBoxCd);
            // ADD END   2018/05/22 QC#25769
            stmt = this.ssmLLClient.createPreparedStatement("getCustomerAccountByMicr", ssmParam);

            rs = stmt.executeQuery();

            while (rs.next()) {
                AccountInfoBean accountInfo = new AccountInfoBean();

                accountInfo.setCustDsBankAcctPk(rs.getBigDecimal("CUST_DS_BANK_ACCT_PK"));
                accountInfo.setPayerCustCd(rs.getString("PAYER_CUST_CD"));
                accountInfo.setDsAcctCustPk(rs.getBigDecimal("DS_ACCT_CUST_PK"));
                // add 2016/01/07 */
                accountInfo.setEffFromDt(rs.getString("EFF_FROM_DT"));
                // end

                accountInfoList.add(accountInfo);
            }

            return accountInfoList;

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            debugLog("execute : closeResource");
        }
    }

    private AccountInfoBean identifyByInvoiceNum(BigDecimal arRcptRcvIntfcPk, String custInvNum, ResultDataBean resultData) throws SQLException {
        if (!ZYPCommonFunc.hasValue(custInvNum)) {
            return null;
        }

        // check by group invoice number
        // MOD START 2018/02/26 S21_NA#24219
        AccountInfoBean accountInfo = getCustomerAccountByInvNum(custInvNum);
        // AccountInfoBean accountInfo = getCustomerAccountByInvNum(custInvNum, true);
        // MOD END 2018/02/26 S21_NA#24219

        // DEL START 2018/02/26 S21_NA#24219
//        if (accountInfo == null) {
//            // check by invoice#
//            accountInfo = getCustomerAccountByInvNum(custInvNum, false);
//        }
//
//        if (accountInfo == null) {
//
//            // ------ add 2016/01/07 check by source system document#
//            accountInfo = getCustomerAccountBySrcDocNum(custInvNum);
            // DEL END 2018/02/26 S21_NA#24219
            if (accountInfo == null) {

                String message = EZDMessageEnv.getI18nMessage(NFCM0739W);

                AR_RCPT_RCV_INFO_WRKTMsg arRcptRcvInfoWrk = registValidationResult(arRcptRcvIntfcPk, message, AR_BAT_INFO_LVL.WARNING);
                resultData.addArRcptRcvInfoWrk(arRcptRcvInfoWrk);
            } else {
                return accountInfo;
            }
            // ------ end 2016/01/07

            // DEL START 2018/02/26 S21_NA#24219
//        } else {
//            return accountInfo;
//        }
            // DEL END 2018/02/26 S21_NA#24219

        return null;
    }

    private AccountInfoBean getCustomerAccountByInvNum(String custInvNum) throws SQLException { // DEL PARAM 2018/02/26 S21_NA#24219 boolean consolidated
        AccountInfoBean accountInfo = null;

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            final Map<String, String> ssmParam = new HashMap<String, String>();

            ssmParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
            ssmParam.put("custInvNum", custInvNum);
            ssmParam.put("arTrxTpCdRcpt", AR_TRX_TP.RECEIPT);

            // DEL START 2018/02/26 S21_NA#24219
            // String statementId ="getCustomerAccountByConsolidatedInvNum";
            // if (!consolidated) {
            // DEL END 2018/02/26 S21_NA#24219
            String statementId = "getCustomerAccountByInvNum";
            // } // DEL 2018/02/26 S21_NA#24219

            stmt = this.ssmLLClient.createPreparedStatement(statementId, ssmParam);

            rs = stmt.executeQuery();

            if (rs.next()) {
                accountInfo = new AccountInfoBean();

                accountInfo.setPayerCustCd(rs.getString("PAYER_CUST_CD"));
                accountInfo.setDsAcctCustPk(rs.getBigDecimal("DS_ACCT_CUST_PK"));
                // 2017/12/11 QC#22262 add start
                accountInfo.setLocNum(rs.getString("LOC_NUM"));
                // 2017/12/11 QC#22262 add start

            }

            return accountInfo;

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            debugLog("execute : closeResource");
        }
    }
    // DEL START 2018/02/26 S21_NA#24219
//    private AccountInfoBean getCustomerAccountBySrcDocNum(String custInvNum) throws SQLException {
//        AccountInfoBean accountInfo = null;
//
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            final Map<String, String> ssmParam = new HashMap<String, String>();
//
//            ssmParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
//            ssmParam.put("custInvNum", custInvNum);
//            ssmParam.put("arTrxTpCdRcpt", AR_TRX_TP.RECEIPT);
//            // START 2017/11/15 [QC#17322, DEL]
//            // ssmParam.put("dsAcctRelnTpCdBillTo", DS_ACCT_RELN_TP.BILL_TO);
//            // END   2017/11/15 [QC#17322, DEL]
//            ssmParam.put("procDt", this.batchProcDt);
//
//            String statementId = "getCustomerAccountByDocSrcNum";
//
//            stmt = this.ssmLLClient.createPreparedStatement(statementId, ssmParam);
//
//            rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                accountInfo = new AccountInfoBean();
//
//                accountInfo.setPayerCustCd(rs.getString("PAYER_CUST_CD"));
//                accountInfo.setDsAcctCustPk(rs.getBigDecimal("DS_ACCT_CUST_PK"));
//                // 2017/12/11 QC#22262 add start
//                accountInfo.setLocNum(rs.getString("LOC_NUM"));
//                // 2017/12/11 QC#22262 add start
//
//            }
//
//            return accountInfo;
//
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
//            debugLog("execute : closeResource");
//        }
//    }
    // DEL END 2018/02/26 S21_NA#24219

    // MOD START 2018/05/22 S21_NA#25769
    //private BigDecimal registerBankAccount(AR_RCPT_RCV_INTFCTMsg acRcptRcvIntfc, AccountInfoBean identifiedAccountInfo, ResultDataBean resultData, String custBankMicrNum, String rcvFuncId) throws SQLException {
    private BigDecimal registerBankAccount(AR_RCPT_RCV_INTFCTMsg acRcptRcvIntfc, AccountInfoBean identifiedAccountInfo, ResultDataBean resultData, String custBankMicrNum, String rcvFuncId, String arLockBoxCd) throws SQLException {
    // MOD END   2018/05/22 S21_NA#25769

        //Def#5016
        // ADD START 2018/03/02 S21_NA#24465
        BigDecimal dsBankAcctPk = null;
        if (NFCI1050.equals(rcvFuncId)) {
            // MOD START 2018/05/22 S21_NA#25769
            //Map<String, Object> dsBankAcctMap = getBankAccountByMicr(custBankMicrNum, identifiedAccountInfo.getDsAcctCustPk());
            Map<String, Object> dsBankAcctMap = getBankAccountByMicr(custBankMicrNum, identifiedAccountInfo.getDsAcctCustPk(), arLockBoxCd);
            // MOD END   2018/05/22 S21_NA#25769

            dsBankAcctPk = (BigDecimal) dsBankAcctMap.get("CUST_DS_BANK_ACCT_PK");
            // DEL START 2018/08/23 S21_NA#27776
            //BigDecimal dsCustBankAcctRelnPk = (BigDecimal) dsBankAcctMap.get("DS_CUST_BANK_ACCT_RELN_PK");

            //String effFromDt = this.batchProcDt;
            //String deptDt = acRcptRcvIntfc.depDt.getValue();

            //if (deptDt.compareTo(effFromDt) < 0) {
            //    effFromDt = deptDt;
            //}

            if (dsBankAcctPk != null) {
                //if (dsCustBankAcctRelnPk == null) {
                //DS_CUST_BANK_ACCT_RELNTMsg dsCustBankAcctReln = registBankAccountReln(acRcptRcvIntfc, effFromDt, identifiedAccountInfo, dsBankAcctPk);
                //resultData.setDsCustBankAcctReln(dsCustBankAcctReln);
                //return dsBankAcctPk;
                //} else {
                    return dsBankAcctPk;
                //}
            }
            //DEL END  2018/08/23 S21_NA#27776
        } else {
            // MOD START 2018/05/22 S21_NA#25769
            // ADD END 2018/03/02 S21_NA#24465
            //dsBankAcctPk = getBankAccountByMicrAndCustAccount(custBankMicrNum, identifiedAccountInfo.getDsAcctCustPk());
            dsBankAcctPk = getBankAccountByMicrAndCustAccount(custBankMicrNum, identifiedAccountInfo.getDsAcctCustPk(), arLockBoxCd);
            // MOD END 2018/05/22 S21_NA#25769
            if (dsBankAcctPk != null) {
                return dsBankAcctPk;
            }
        } // ADD 2018/03/02 S21_NA#24465
        //DefID2601
        // Create New Bank Branch
        //        DS_BANK_BRTMsg bankBranch = getBankBranch(acRcptRcvIntfc.custBankRteNum.getValue());
        //
        //        if (bankBranch == null) {
        //            String message = EZDMessageEnv.getI18nMessage(NFCM0741W);
        //
        //            AR_RCPT_RCV_INFO_WRKTMsg arRcptRcvInfoWrk = registValidationResult(acRcptRcvIntfc.arRcptRcvIntfcPk.getValue(), message, AR_BAT_INFO_LVL.WARNING);
        //            resultData.addArRcptRcvInfoWrk(arRcptRcvInfoWrk);
        //
        //            bankBranch = getBankBranch(this.comnBankBrRteNum);
        //
        //            if (bankBranch == null) {
        //                return null;
        //            }
        //        }
        DS_BANK_BRTMsg bankBranch = registBankBranch(acRcptRcvIntfc);
        resultData.setDsBankBr(bankBranch);

        String effFromDt = this.batchProcDt;
        String deptDt = acRcptRcvIntfc.depDt.getValue();

        if (deptDt.compareTo(effFromDt) < 0) {
            effFromDt = deptDt;
        }

        //Def#5016
        DS_BANK_ACCTTMsg dsBankAcct = registBankAccount(acRcptRcvIntfc, bankBranch, effFromDt, custBankMicrNum);
        resultData.setDsBankAcct(dsBankAcct);

        dsBankAcctPk = dsBankAcct.dsBankAcctPk.getValue();
        // MOD START 2018/08/23 S21_NA#27776
        if (!NFCI1050.equals(rcvFuncId)) {
            DS_CUST_BANK_ACCT_RELNTMsg dsCustBankAcctReln = registBankAccountReln(acRcptRcvIntfc, effFromDt, identifiedAccountInfo, dsBankAcctPk);
            resultData.setDsCustBankAcctReln(dsCustBankAcctReln);
        }
        // MOD END   2018/08/23 S21_NA#27776
        return dsBankAcctPk;
    }

    // Mod Start 2018/01/19 S21_NA#22421
    private BigDecimal registerBankAccount_UnidentifiedAccountInfo(AR_RCPT_RCV_INTFCTMsg acRcptRcvIntfc, AccountInfoBean identifiedAccountInfo, ResultDataBean resultData, String custBankMicrNum) throws SQLException {
  // private BigDecimal registerBankAccount_UnidentifiedAccountInfo(AR_RCPT_RCV_INTFCTMsg acRcptRcvIntfc, AccountInfoBean identifiedAccountInfo, ResultDataBean resultData, String custBankMicrNum, List<ResultDataBean> resultDataList) throws SQLException {
    // Mod End 2018/01/19 S21_NA#22421

        // START 2017/01/04 E.Kameishi [QC#16536,MOD]
        // ResultDataList Existence check 
        // Del Start 2018/01/19 S21_NA#22421
//        for (ResultDataBean rsltData : resultDataList) {
//            DS_BANK_ACCTTMsg dsBankAcctTMsg = rsltData.getDsBankAcct();
//            if (dsBankAcctTMsg != null && dsBankAcctTMsg.dsBankMicrNum.getValue().equals(custBankMicrNum)) {
//                return dsBankAcctTMsg.dsBankAcctPk.getValue();
//            }
//        }
        // Del End 2018/01/19 S21_NA#224210

        // DS_BANK_ACCT table Existence check 
        BigDecimal bankAccoutPK = getBankAccoutPK(acRcptRcvIntfc, custBankMicrNum);
        //DS_BANK_BRTMsg bankBr = getBankBranch(acRcptRcvIntfc.custBankRteNum.getValue());
        if (bankAccoutPK != null) {
            return bankAccoutPK;
        }
        // END 2017/01/04 E.Kameishi [QC#16536,MOD]

        DS_BANK_BRTMsg bankBranch = registBankBranch(acRcptRcvIntfc);
        resultData.setDsBankBr(bankBranch);

        String effFromDt = this.batchProcDt;
        String deptDt = acRcptRcvIntfc.depDt.getValue();

        if (deptDt.compareTo(effFromDt) < 0) {
            effFromDt = deptDt;
        }

        //Def#5016
        DS_BANK_ACCTTMsg dsBankAcct = registBankAccount(acRcptRcvIntfc, bankBranch, effFromDt, custBankMicrNum);
        resultData.setDsBankAcct(dsBankAcct);

        // START 2017/01/04 E.Kameishi [QC#16536,ADD]
        return dsBankAcct.dsBankAcctPk.getValue();
        // END 2017/01/04 E.Kameishi [QC#16536,ADD]
    }

    private DS_BANK_BRTMsg getBankBranch(String custBankRteNum) throws SQLException {
        DS_BANK_BRTMsg bankBranch = null;

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            final Map<String, String> ssmParam = new HashMap<String, String>();

            ssmParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
            ssmParam.put("custBankRteNum", custBankRteNum);

            stmt = this.ssmLLClient.createPreparedStatement("getBankBranch", ssmParam);

            rs = stmt.executeQuery();

            if (rs.next()) {
                bankBranch = new DS_BANK_BRTMsg();

                ZYPEZDItemValueSetter.setValue(bankBranch.bankRteNum, rs.getString("BANK_RTE_NUM"));
                ZYPEZDItemValueSetter.setValue(bankBranch.dsBankCd, rs.getString("DS_BANK_CD"));

                //Def#2601
                ZYPEZDItemValueSetter.setValue(bankBranch.dsBankBrPk, rs.getBigDecimal("DS_BANK_BR_PK"));
            }

            return bankBranch;

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            debugLog("execute : closeResource");
        }
    }

    private AR_RCPT_RCV_INFO_WRKTMsg registValidationResult(BigDecimal arRcptRcvIntfcPk, String batInfoMsgTxt, String batInfoLvCd) {
        AR_RCPT_RCV_INFO_WRKTMsg tMsg = new AR_RCPT_RCV_INFO_WRKTMsg();

        BigDecimal arRcptRcvInfoWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AR_RCPT_RCV_INFO_WRK_SQ);

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.arRcptRcvInfoWrkPk, arRcptRcvInfoWrkPk);
        ZYPEZDItemValueSetter.setValue(tMsg.arRcptRcvIntfcPk, arRcptRcvIntfcPk);
        ZYPEZDItemValueSetter.setValue(tMsg.arBatInfoMsgTxt, batInfoMsgTxt);
        ZYPEZDItemValueSetter.setValue(tMsg.arBatInfoLvlCd, batInfoLvCd);

        return tMsg;
    }

    private DS_BANK_BRTMsg registBankBranch(AR_RCPT_RCV_INTFCTMsg acRcptRcvIntfc) {

        DS_BANK_BRTMsg tMsg = new DS_BANK_BRTMsg();

        BigDecimal dsBankBrPk = ZYPOracleSeqAccessor.getNumberBigDecimal("DS_BANK_BR_SQ");

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsBankBrPk, dsBankBrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.dsBankCd, DS_BANK_COMMON);
        ZYPEZDItemValueSetter.setValue(tMsg.bankRteNum, acRcptRcvIntfc.custBankRteNum.getValue());

        ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, this.ctryCd);

        return tMsg;
    }

    private DS_BANK_ACCTTMsg registBankAccount(AR_RCPT_RCV_INTFCTMsg acRcptRcvIntfc, DS_BANK_BRTMsg bankBranch, String effFromDt, String custBankMicrNum) {
        DS_BANK_ACCTTMsg tMsg = new DS_BANK_ACCTTMsg();

        BigDecimal dsBankAcctPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BANK_ACCT_SQ);

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsBankAcctPk, dsBankAcctPk);
        ZYPEZDItemValueSetter.setValue(tMsg.dsBankAcctNum, acRcptRcvIntfc.custDsBankAcctNum);
        ZYPEZDItemValueSetter.setValue(tMsg.dsBankAcctNm, acRcptRcvIntfc.custDsBankAcctNum);
        ZYPEZDItemValueSetter.setValue(tMsg.dsBankAcctTpCd, DS_BANK_ACCT_TP.EXTERNAL);
        //Def#5016
        //        ZYPEZDItemValueSetter.setValue(tMsg.dsBankMicrNum, acRcptRcvIntfc.custDsBankMicrNum);
        ZYPEZDItemValueSetter.setValue(tMsg.dsBankMicrNum, custBankMicrNum);

        ZYPEZDItemValueSetter.setValue(tMsg.arLockBoxCd, acRcptRcvIntfc.arLockBoxCd);

        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, effFromDt);
        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, DEFAULT_EFF_THRU_DT);

        ZYPEZDItemValueSetter.setValue(tMsg.dsBankCd, bankBranch.dsBankCd);
        ZYPEZDItemValueSetter.setValue(tMsg.bankRteNum, bankBranch.bankRteNum);

        //Def#2601
        ZYPEZDItemValueSetter.setValue(tMsg.dsBankBrPk, bankBranch.dsBankBrPk);


        // TODO Is it OK not to set AR_RCPT_SRC_CD and CCY_CD??
        ZYPEZDItemValueSetter.setValue(tMsg.arRcptSrcCd, acRcptRcvIntfc.arRcptSrcCd);
        // START 2017/01/04 E.Kameishi [QC#16536,MOD]
        ZYPEZDItemValueSetter.setValue(tMsg.ccyCd, this.stdCcyCd);
        //ZYPEZDItemValueSetter.setValue(tMsg.ccyCd, this.ctryCd);
        // END 2017/01/04 E.Kameishi [QC#16536,MOD]

        return tMsg;
    }

    private DS_CUST_BANK_ACCT_RELNTMsg registBankAccountReln(AR_RCPT_RCV_INTFCTMsg acRcptRcvIntfc, String effFromDt, AccountInfoBean identifiedAccountInfo, BigDecimal dsBankAcctPk) {
        DS_CUST_BANK_ACCT_RELNTMsg tMsg = new DS_CUST_BANK_ACCT_RELNTMsg();

        BigDecimal dsCustBankAcctRelnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CUST_BANK_ACCT_RELN_SQ);

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsCustBankAcctRelnPk, dsCustBankAcctRelnPk);
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctCustPk, identifiedAccountInfo.getDsAcctCustPk());
        ZYPEZDItemValueSetter.setValue(tMsg.dsBankAcctPk, dsBankAcctPk);
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, effFromDt);
        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, DEFAULT_EFF_THRU_DT);
        // 2017/12/11 QC#22262 add start
        ZYPEZDItemValueSetter.setValue(tMsg.locNum, identifiedAccountInfo.getLocNum());
        // 2017/12/11 QC#22262 add start

        return tMsg;
    }

    private boolean hasConsistencyError(AR_RCPT_RCV_INTFCTMsg arRcptRcvIntfc, AccountInfoBean accountInfo, ResultDataBean resultData) throws SQLException {
        String custRcptNum = arRcptRcvIntfc.custRcptNum.getValue();
        String payerCustCd = accountInfo.getPayerCustCd();
        BigDecimal custRcptAmt = arRcptRcvIntfc.custRcptAmt.getValue();

        int count = countArRcptRcvWrk(custRcptNum, payerCustCd, custRcptAmt);

        if (count == 0) {
            count = countArRcpt(custRcptNum, payerCustCd, custRcptAmt);
        }

        if (count > 0) {
            String message = EZDMessageEnv.getI18nMessage(NFCM0742E);

            AR_RCPT_RCV_INFO_WRKTMsg arRcptRcvInfoWrk = registValidationResult(arRcptRcvIntfc.arRcptRcvIntfcPk.getValue(), message, AR_BAT_INFO_LVL.ERROR);
            resultData.addArRcptRcvInfoWrk(arRcptRcvInfoWrk);

            return true;
        }

        return false;
    }

    // ADD START 2018/06/25 S21_NA#25731
    private boolean hasConsistencyError(List<List<String>> lDataList, AR_RCPT_RCV_INTFCTMsg arRcptRcvIntfc, AccountInfoBean accountInfo, ResultDataBean resultData) throws SQLException {
        String custRcptNum = arRcptRcvIntfc.custRcptNum.getValue();
        String payerCustCd = accountInfo.getPayerCustCd();
        BigDecimal custRcptAmt = arRcptRcvIntfc.custRcptAmt.getValue();
        // START 2021/10/18 S.Go [QC#59273,ADD]
        String arLockBoxBatNum = arRcptRcvIntfc.arLockBoxBatNum.getValue();
        String arLockBoxBatLineNum = arRcptRcvIntfc.arLockBoxBatLineNum.getValue();
        // END 2021/10/18 S.Go [QC#59273,ADD]
        String updCustRcptNum = null;
        List<String> sDataList = new ArrayList<String>();
        sDataList.add(custRcptNum);
        sDataList.add(payerCustCd);
        if (custRcptAmt != null) {
            sDataList.add(custRcptAmt.toString());
        } else {
            sDataList.add("0");
        }
        // START 2021/10/18 S.Go [QC#59273,ADD]
        sDataList.add(arLockBoxBatNum);
        sDataList.add(arLockBoxBatLineNum);
        // END 2021/10/18 S.Go [QC#59273,ADD]
        boolean dataFlg = false;

        for (int i = 0; i < lDataList.size(); i++) {
            List<String> data = lDataList.get(i);
            // START 2021/10/18 S.Go [QC#59273,MOD]
            // if (data.contains(custRcptNum)) {
            if (data.contains(sDataList.get(0))) {
            // END 2021/10/18 S.Go [QC#59273,MOD]
                if (data.contains(payerCustCd)) {
                    if (data.contains(custRcptAmt.toString())) {
                        // START 2021/10/18 S.Go [QC#59273,MOD]
//                      dataFlg =true;
//                      updCustRcptNum = data.get(3);
                        updCustRcptNum = data.get(5);
                        // END 2021/10/18 S.Go [QC#59273,MOD]
                        // START 2021/10/18 S.Go [QC#59273,ADD]
                        if (!arLockBoxBatNum.equals(data.get(3)) || !arLockBoxBatLineNum.equals(data.get(4))) {
                            // Get current sequence
                            custRcptNum = getCustRcptNumNextSeq(updCustRcptNum);
                        } else {
                            dataFlg = true;
                            break;
                        }
                        // END 2021/10/18 S.Go [QC#59273,ADD]
                    }
                }
            }
        }

        if (dataFlg) {
            // START 2021/06/07 G.Delgado [QC#58800,MOD]
//            if (workcd.equals(falseData)) {
//                return false;
            if (ZYPCommonFunc.hasValue(updCustRcptNum)) {
                // Set the same check#
                custRcptNum = updCustRcptNum;
            // END 2021/06/07 G.Delgado [QC#58800,MOD]
            } else {
                String message = EZDMessageEnv.getI18nMessage(NFCM0742E);
                AR_RCPT_RCV_INFO_WRKTMsg arRcptRcvInfoWrk = registValidationResult(arRcptRcvIntfc.arRcptRcvIntfcPk.getValue(), message, AR_BAT_INFO_LVL.ERROR);
                resultData.addArRcptRcvInfoWrk(arRcptRcvInfoWrk);
                return true;
            }
        } else {
            // START 2021/06/07 G.Delgado [QC#58800,MOD]
//            int count = countArRcptRcvWrk(custRcptNum, payerCustCd, custRcptAmt);
//
//            if (count == 0) {
//                count = countArRcpt(custRcptNum, payerCustCd, custRcptAmt);
//            }
            String ptrnParam = custRcptNum;
            do {
                String currSeq = null;
                ptrnParam = getCustRcptNumPtrnParam(ptrnParam, custRcptNum);
                // START 2021/10/18 S.Go [QC#59273,ADD]
                if (countArRcptRcvIntfc(custRcptNum, payerCustCd, custRcptAmt) > 0) {
                    currSeq = getCustRcptNumSeqFromArRcptRcvIntfc(ptrnParam, payerCustCd, custRcptAmt);
                // END 2021/10/18 S.Go [QC#59273,ADD]
                // START 2021/10/18 S.Go [QC#59273,MOD]
//                if (countArRcptRcvWrk(custRcptNum, payerCustCd, custRcptAmt) > 0) {
                } else if (countArRcptRcvWrk(custRcptNum, payerCustCd, custRcptAmt) > 0) {
                // END 2021/10/18 S.Go [QC#59273,MOD]
                	currSeq = getCustRcptNumSeqFromArRcptRcvWrk(ptrnParam, payerCustCd, custRcptAmt);
                } else if (countArRcpt(custRcptNum, payerCustCd, custRcptAmt) > 0) {
                    currSeq = getCustRcptNumSeqFromArRcpt(ptrnParam, payerCustCd, custRcptAmt);
                } else {
                    break;
                }

                // Update check# to next sequence
                custRcptNum = getCustRcptNumNextSeq(currSeq);
            } while (ZYPCommonFunc.hasValue(custRcptNum));
            // END 2021/06/07 G.Delgado [QC#58800,MOD]

            // START 2021/06/07 G.Delgado [QC#58800,MOD]
            // Unable to generate unique check number
            // if (count > 0) {
            if (!ZYPCommonFunc.hasValue(custRcptNum)) {
            // END 2021/06/07 G.Delgado [QC#58800,MOD]
                String message = EZDMessageEnv.getI18nMessage(NFCM0742E);

                AR_RCPT_RCV_INFO_WRKTMsg arRcptRcvInfoWrk = registValidationResult(arRcptRcvIntfc.arRcptRcvIntfcPk.getValue(), message, AR_BAT_INFO_LVL.ERROR);
                resultData.addArRcptRcvInfoWrk(arRcptRcvInfoWrk);
                // START 2021/06/07 G.Delgado [QC#58800,MOD]
                // sDataList.add(trueData);
                sDataList.add(null);
                // END 2021/06/07 G.Delgado [QC#58800,MOD]
                lDataList.add(sDataList);
                return true;
            }

            // START 2021/06/07 G.Delgado [QC#58800,MOD]
//            sDataList.add(falseData);
//            lDataList.add(sDataList);
//            return false;
//        }
            sDataList.add(custRcptNum);
            lDataList.add(sDataList);
        }

        if (!arRcptRcvIntfc.custRcptNum.getValue().equals(custRcptNum)) {
            // Update with valid check#
            ZYPEZDItemValueSetter.setValue(arRcptRcvIntfc.custRcptNum, custRcptNum);
            // Update lockbox status
            ZYPEZDItemValueSetter.setValue(arRcptRcvIntfc.arLockBoxStsCd, AR_LOCK_BOX_STS.REPROCESS);
        }

        return false;
        // END 2021/06/07 G.Delgado [QC#58800,MOD]
    }
    // ADD END   2018/06/25 S21_NA#25731

    // MOD START 2018/05/22 S21_NA#25769
    //private BigDecimal getBankAccountByMicrAndCustAccount(String custBankMicrNum, BigDecimal dsAcctCustPk) throws SQLException {
    private BigDecimal getBankAccountByMicrAndCustAccount(String custBankMicrNum, BigDecimal dsAcctCustPk, String arLockBoxCd) throws SQLException {        
    // MOD END   2018/05/22 S21_NA#25769
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            final Map<String, String> ssmParam = new HashMap<String, String>();

            ssmParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
            ssmParam.put("custBankMicrNum", custBankMicrNum);
            ssmParam.put("dsBankAcctTpCdExternal", DS_BANK_ACCT_TP.EXTERNAL);
            ssmParam.put("dsAcctCustPk", dsAcctCustPk.toString());
            // ADD START 2018/05/22 S21_NA#25769
            ssmParam.put("arLockBoxCd", arLockBoxCd);
            // ADD END   2018/05/22 S21_NA#25769
            stmt = this.ssmLLClient.createPreparedStatement("getBankAccountByMicrAndCustAccount", ssmParam);

            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getBigDecimal("CUST_DS_BANK_ACCT_PK");
            }

            return null;

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            debugLog("execute : closeResource");
        }
    }

    // MOD START 2018/05/22 S21_NA#25769
    // ADD START 2018/03/02 S21_NA#24465
    //private Map<String, Object> getBankAccountByMicr(String custBankMicrNum, BigDecimal dsAcctCustPk) throws SQLException {
    private Map<String, Object> getBankAccountByMicr(String custBankMicrNum, BigDecimal dsAcctCustPk, String arLockBoxCd) throws SQLException {
    // MOD END   2018/05/22 S21_NA#25769

        final Map<String, String> ssmParam = new HashMap<String, String>();

        ssmParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
        ssmParam.put("custBankMicrNum", custBankMicrNum);
        ssmParam.put("dsBankAcctTpCdExternal", DS_BANK_ACCT_TP.EXTERNAL);
        ssmParam.put("dsAcctCustPk", dsAcctCustPk.toString());
        // MOD START 2018/05/22 S21_NA#25769
        ssmParam.put("arLockBoxCd", arLockBoxCd);
        // MOD END   2018/05/22 S21_NA#25769
        List<Map<String, Object>> bankList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getBankAccountByMicr", ssmParam);

        if (bankList != null && bankList.size() > 0) {
            return bankList.get(0);
        }
        return new HashMap<String, Object>();

    }
    // ADD START 2018/03/02 S21_NA#24465

    private int countArRcptRcvWrk(String custRcptNum, String payerCustCd, BigDecimal custRcptAmt) throws SQLException {
        int cnt = 0;

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            final Map<String, String> ssmParam = new HashMap<String, String>();

            ssmParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
            ssmParam.put("custRcptNum", custRcptNum);
            ssmParam.put("payerCustCd", payerCustCd);
            ssmParam.put("custRcptAmt", custRcptAmt.toString());

            stmt = this.ssmLLClient.createPreparedStatement("countArRcptRcvWrk", ssmParam);

            rs = stmt.executeQuery();

            if (rs.next()) {
                cnt = rs.getInt("CNT");
            }

            return cnt;

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            debugLog("execute : closeResource");
        }
    }

    private int countArRcpt(String custRcptNum, String payerCustCd, BigDecimal custRcptAmt) throws SQLException {
        int cnt = 0;

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            final Map<String, String> ssmParam = new HashMap<String, String>();

            ssmParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
            ssmParam.put("custRcptNum", custRcptNum);
            ssmParam.put("payerCustCd", payerCustCd);
            ssmParam.put("custRcptAmt", custRcptAmt.toString());

            stmt = this.ssmLLClient.createPreparedStatement("countArRcpt", ssmParam);

            rs = stmt.executeQuery();

            if (rs.next()) {
                cnt = rs.getInt("CNT");
            }

            return cnt;

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            debugLog("execute : closeResource");
        }
    }

    private AR_RCPT_RCV_INTFCTMsg updateArRcptRcvIntfc(AR_RCPT_RCV_INTFCTMsg tMsg, ResultDataBean resultData) {
        BigDecimal arRcptRcvIntfcPk = tMsg.arRcptRcvIntfcPk.getValue();

        AccountInfoBean accountInfo = resultData.getAccountInfo();

        if (accountInfo != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.custDsBankAcctPk, accountInfo.getCustDsBankAcctPk());
            ZYPEZDItemValueSetter.setValue(tMsg.payerCustCd, accountInfo.getPayerCustCd());
            // ----- start mod 2016/01/08 not update BILL_TO_CUST_CD
            // ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd,
            // accountInfo.getBillToCustCd());
            // ----- end 2016/01/08
            ZYPEZDItemValueSetter.setValue(tMsg.arCustIdStsCd, AR_CUST_ID_STS.IDENTIFIED);

        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.arCustIdStsCd, AR_CUST_ID_STS.UN_IDENTIFIED);

        }

        if (resultData.getVldCustRcptNum() != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.vldCustRcptNum, resultData.getVldCustRcptNum());
        }

        if (arRcptRcvIntfcPk.equals(resultData.getErrorIntfcPk())) {
            ZYPEZDItemValueSetter.setValue(tMsg.arRcptRcvErrFlg, ZYPConstant.FLG_ON_Y);
            tMsg.arLockBoxStsCd.setValue(AR_LOCK_BOX_STS.ERROR);
        }

        // START 2018/08/07 H.Ikeda [QC#28839,ADD]
        String billToCustAcctCd = billToCustAcctCdMap.get(arRcptRcvIntfcPk);
        if (ZYPCommonFunc.hasValue(billToCustAcctCd)) {
            ZYPEZDItemValueSetter.setValue(tMsg.billToCustAcctCd, billToCustAcctCd);
        }
        // END   2018/08/07 H.Ikeda [QC#28839,ADD]

        return tMsg;
    }

    // MOD 2018/06/20 S21_NA#25731
//    private void save(List<AR_RCPT_RCV_INTFCTMsg> updTMsgList, List<ResultDataBean> resultDataList) {
//        List<AR_RCPT_RCV_INFO_WRKTMsg> arRcptRcvInfoWrkTMsgList = new ArrayList<AR_RCPT_RCV_INFO_WRKTMsg>();
//        // DEL START 2018/03/02 S21_NA#24654
////        List<DS_BANK_ACCTTMsg> dsBankAcctTMsgList = new ArrayList<DS_BANK_ACCTTMsg>();
////        List<DS_CUST_BANK_ACCT_RELNTMsg> dsCustBankAcctRelnTMsgList = new ArrayList<DS_CUST_BANK_ACCT_RELNTMsg>();
////        //Def#2601
////        List<DS_BANK_BRTMsg> dsBankBrTMsgList = new ArrayList<DS_BANK_BRTMsg>();
//        // DEL START 2018/03/02 S21_NA#24654
//
//        if (updTMsgList.size() > 0) { // ADD 2018/03/02 S21_NA#24654
//            EZDFastTBLAccessor.update(updTMsgList.toArray(new AR_RCPT_RCV_INTFCTMsg[0]));
//        } // ADD 2018/03/02 S21_NA#24654
//        for (int i = 0; i < resultDataList.size(); i++) {
//            ResultDataBean resultData = resultDataList.get(i);
//
//            arRcptRcvInfoWrkTMsgList.addAll(resultData.getArRcptRcvInfoWrkList());
//            // DEL START 2018/02/21 S21_NA#24421
////              // Def#2601
////              DS_BANK_BRTMsg dsBankBr = resultData.getDsBankBr();
////              if (dsBankBr != null) {
////                  dsBankBrTMsgList.add(dsBankBr);
////              }
////
////              DS_BANK_ACCTTMsg dsBankAcct = resultData.getDsBankAcct();
////              if (dsBankAcct != null) {
////                  dsBankAcctTMsgList.add(dsBankAcct);
////              }
////
////              DS_CUST_BANK_ACCT_RELNTMsg dsCustBankAcctReln = resultData.getDsCustBankAcctReln();
////              if (dsCustBankAcctReln != null) {
////                  dsCustBankAcctRelnTMsgList.add(dsCustBankAcctReln);
////              }
//              // DEL END 2018/02/21 S21_NA#24421
//        }
//
//        if (!arRcptRcvInfoWrkTMsgList.isEmpty()) {
//            EZDFastTBLAccessor.insert(arRcptRcvInfoWrkTMsgList.toArray(new AR_RCPT_RCV_INFO_WRKTMsg[0]));
//        }
//          // DEL START 2018/02/21 S21_NA#24421
////          // Def#2601
////          if (!dsBankBrTMsgList.isEmpty()) {
////              EZDFastTBLAccessor.insert(dsBankBrTMsgList.toArray(new DS_BANK_BRTMsg[0]));
////          }
////          if (!dsBankAcctTMsgList.isEmpty()) {
////              EZDFastTBLAccessor.insert(dsBankAcctTMsgList.toArray(new DS_BANK_ACCTTMsg[0]));
////          }
////          if (!dsCustBankAcctRelnTMsgList.isEmpty()) {
////              EZDFastTBLAccessor.insert(dsCustBankAcctRelnTMsgList.toArray(new DS_CUST_BANK_ACCT_RELNTMsg[0]));
////          }
//          // DEL END 2018/02/21 S21_NA#24421
//    }

    private void save(List<AR_RCPT_RCV_INTFCTMsg> updTMsgList, List<ResultDataBean> resultDataList) {
        List<AR_RCPT_RCV_INTFCTMsg> arRcptRcvIntfcList = new ArrayList<AR_RCPT_RCV_INTFCTMsg>();
        for (int i = 0; i < updTMsgList.size(); i++) {
            AR_RCPT_RCV_INTFCTMsg arRcptRcvIntfcTMsg = updTMsgList.get(i);
            arRcptRcvIntfcList.add(arRcptRcvIntfcTMsg);
            if (arRcptRcvIntfcList.size() >= commitCnt) {
                EZDFastTBLAccessor.update(arRcptRcvIntfcList.toArray(new AR_RCPT_RCV_INTFCTMsg[arRcptRcvIntfcList.size()]));
                arRcptRcvIntfcList.clear();
                commit();
            }
        }
        if (arRcptRcvIntfcList.size() > 0) {
            // remaining
            EZDFastTBLAccessor.update(arRcptRcvIntfcList.toArray(new AR_RCPT_RCV_INTFCTMsg[arRcptRcvIntfcList.size()]));
            commit();
        }
        List<AR_RCPT_RCV_INFO_WRKTMsg> arRcptRcvInfoWrkTMsgList = new ArrayList<AR_RCPT_RCV_INFO_WRKTMsg>();
        List<AR_RCPT_RCV_INFO_WRKTMsg> wrkTMsgList = new ArrayList<AR_RCPT_RCV_INFO_WRKTMsg>();

        // MOD START  2019/05/28 S21_NA#50544
        // MOD START  2018/07/06 S21_NA#25731
        if (resultDataList.size() > 0) {
//            ResultDataBean resultData = resultDataList.get(0);
//            arRcptRcvInfoWrkTMsgList.addAll(resultData.getArRcptRcvInfoWrkList());
//            for (int i = 0; i < arRcptRcvInfoWrkTMsgList.size(); i++) {
//                wrkTMsgList.add(arRcptRcvInfoWrkTMsgList.get(i));
//                if (wrkTMsgList.size() >= commitCnt) {
//                    EZDFastTBLAccessor.insert(wrkTMsgList.toArray(new AR_RCPT_RCV_INFO_WRKTMsg[wrkTMsgList.size()]));
//                    commit();
//                    wrkTMsgList.clear();
//                }
//            }
            for (int i = 0; i < resultDataList.size(); i++) {
                arRcptRcvInfoWrkTMsgList.addAll(resultDataList.get(i).getArRcptRcvInfoWrkList());
            }
            for (int j = 0; j < arRcptRcvInfoWrkTMsgList.size(); j++) {
                wrkTMsgList.add(arRcptRcvInfoWrkTMsgList.get(j));
                if (wrkTMsgList.size() >= commitCnt) {
                    EZDFastTBLAccessor.insert(wrkTMsgList.toArray(new AR_RCPT_RCV_INFO_WRKTMsg[wrkTMsgList.size()]));
                    commit();
                    wrkTMsgList.clear();
                }
            }
        }
        // MOD END   2018/07/06 S21_NA#25731
        // MOD END   2019/05/28 S21_NA#50544
        if (wrkTMsgList.size() > 0) {
            // remaining
            EZDFastTBLAccessor.insert(wrkTMsgList.toArray(new AR_RCPT_RCV_INFO_WRKTMsg[wrkTMsgList.size()]));
            commit();
        }
    }
    // MOD 2018/06/20 S21_NA#25731

    // Add Start 2018/02/28 S21_NA#22421
    private void masterSave(ResultDataBean resultData) {

        DS_BANK_BRTMsg dsBankBr = resultData.getDsBankBr();
        if (dsBankBr != null) {
            EZDFastTBLAccessor.insert(dsBankBr);
        }

        DS_BANK_ACCTTMsg dsBankAcct = resultData.getDsBankAcct();
        if (dsBankAcct != null) {
            EZDFastTBLAccessor.insert(dsBankAcct);
        }

        DS_CUST_BANK_ACCT_RELNTMsg dsCustBankAcctReln = resultData.getDsCustBankAcctReln();
        if (dsCustBankAcctReln != null) {
            EZDFastTBLAccessor.insert(dsCustBankAcctReln);
        }
    }
    // Add End 2018/02/28 S21_NA#22421

    private void updateArLockBoxStsToError(String errorLockBoxFileNm) {
        AR_RCPT_RCV_INTFCTMsg inMsg = new AR_RCPT_RCV_INTFCTMsg();

        inMsg.setConditionValue("arLockBoxFileNm01", errorLockBoxFileNm);
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setSQLID("001");

        AR_RCPT_RCV_INTFCTMsgArray tMsgArray = (AR_RCPT_RCV_INTFCTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);

        List<AR_RCPT_RCV_INTFCTMsg> updTMsgList = new ArrayList<AR_RCPT_RCV_INTFCTMsg>();

        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            AR_RCPT_RCV_INTFCTMsg updTMsg = tMsgArray.no(i);

            updTMsg.arLockBoxStsCd.setValue(AR_LOCK_BOX_STS.ERROR);
            updTMsgList.add(updTMsg);

            // Mod Start 2018/06/25 S21_NA#25744
            if (updTMsgList.size() >= commitCnt) {
                EZDFastTBLAccessor.update(updTMsgList.toArray(new AR_RCPT_RCV_INTFCTMsg[updTMsgList.size()]));
                updTMsgList.clear();
            }
        }
        if (!updTMsgList.isEmpty()) {
            EZDFastTBLAccessor.update(updTMsgList.toArray(new AR_RCPT_RCV_INTFCTMsg[updTMsgList.size()]));
            // Mod End 2018/06/25 S21_NA#25744
        }
    }

    /**
     * Execute AbendException
     * @param msgId String
     */
    private void execAbendException(String msgId) {
        setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
        throw new S21AbendException(msgId);
    }

    /**
     * Execute AbendException
     * @param msgId String
     * @param msgStr String[]
     */
    private void execAbendException(String msgId, String[] msgStr) {
        setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
        throw new S21AbendException(msgId, msgStr);
    }

    /**
     * Output Debug Log
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }

    /**
     * 
     */
    private boolean getGlobalCompany() {
        GLBL_CMPYTMsg inMsg = new GLBL_CMPYTMsg();
        inMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        GLBL_CMPYTMsg outMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg != null) {
            this.stdCcyCd = outMsg.stdCcyCd.getValue();
            this.ctryCd = outMsg.ctryCd.getValue();
            return true;
        }
        return false;
    }
    // START 2017/01/04 E.Kameishi [QC#16536,ADD]
    private BigDecimal getBankAccoutPK(AR_RCPT_RCV_INTFCTMsg acRcptRcvIntfc, String custBankMicrNum) throws SQLException {

            Map<String, String> ssmParam = new HashMap<String, String>();

            ssmParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
            ssmParam.put("custBankMicrNum", custBankMicrNum);
            ssmParam.put("procDt", this.batchProcDt);

            List<BigDecimal> bankAcctPkList = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getBankAccoutPK", ssmParam);

            if (bankAcctPkList.size() > 0) {
                return bankAcctPkList.get(0);
            } else {
               return null;
            }
    }
    // END 2017/01/04 E.Kameishi [QC#16536,ADD]

    // Add Start 2018/01/19 S21_NA#22421
    // ADD START 2018/03/01 S21_NA#24465
    /**
     * @param intfcInfo AR_RCPT_RCV_INTFCTMsg
     * @param resultData ResultDataBean
     * @return AccountInfoBean
     */
    private AccountInfoBean identifyCustomerAccount(AR_RCPT_RCV_INTFCTMsg intfcInfo, ResultDataBean resultData) throws SQLException {

        BigDecimal arRcptRcvIntfcPk = intfcInfo.arRcptRcvIntfcPk.getValue();
        String custBankMicrNum = intfcInfo.custDsBankMicrNum.getValue();
        String rcvFuncId = intfcInfo.rcvFuncId.getValue();
        // ADD START 2018/05/22 QC#25769
        String arLockBoxCd = intfcInfo.arLockBoxCd.getValue();
        // ADD END   2018/05/22 QC#25769

        custBankMicrNum = custBankMicrNum.replace(" ", "");

        AccountInfoBean accountInfoByMicr = null;
        AccountInfoBean accountInfoByInv = null;

        // 1) Check by customer account number
        AccountInfoBean accountInfo = identifyByDsAcctNum(arRcptRcvIntfcPk, intfcInfo.custAcctRefNum.getValue(), resultData);

        if (accountInfo == null) {
            // 2) check by MICR info
            if (!NFCI1050.equals(rcvFuncId)) {
                // MOD START 2018/05/22 QC#25769
                //accountInfoByMicr = identifyByMicr(arRcptRcvIntfcPk, custBankMicrNum, resultData);
                accountInfoByMicr = identifyByMicr(arRcptRcvIntfcPk, custBankMicrNum, resultData, arLockBoxCd);
                // MOD END   2018/05/22 QC#25769
            }

            // 3) Check by Bill#/Invoice#
            accountInfoByInv = identifyByInvoiceNum(arRcptRcvIntfcPk, intfcInfo.custInvNum.getValue(), resultData);

            if (accountInfoByMicr == null) {
                accountInfo = accountInfoByInv;
            } else if (accountInfoByInv == null) {
                accountInfo = accountInfoByMicr;
            } else {
                if (!isEqualAccoutnInfo(accountInfoByInv, accountInfoByMicr)) {
                    return null;
                } else {
                    accountInfo = accountInfoByMicr;
                }
            }
        }
        return accountInfo;
    }
    // ADD END 2018/03/01 S21_NA#24465

    // DEL START 2018/05/21 S21_NA#26204
//    /**
//     * @param intfcInfoList List<AR_RCPT_RCV_INTFCTMsg>
//     * @param resultData ResultDataBean
//     * @return AccountInfoBean
//     */
/*
    private AccountInfoBean identifyCustomerAccount(List<AR_RCPT_RCV_INTFCTMsg> intfcInfoList, ResultDataBean resultData) throws SQLException {

//        boolean resultFlg = true; // DEL 2018/02/23 S21_NA#24219
        AccountInfoBean identifiedAccountInfo = null;
        for (AR_RCPT_RCV_INTFCTMsg intfcInfo : intfcInfoList) {

            // MOD START 2018/03/01 S21_NA#24465 -- make method identifyCustomerAccount(AR_RCPT_RCV_INTFCTMsg, ResultDataBean)
//            BigDecimal arRcptRcvIntfcPk = intfcInfo.arRcptRcvIntfcPk.getValue();
//            String custBankMicrNum = intfcInfo.custDsBankMicrNum.getValue();
//            String rcvFuncId = intfcInfo.rcvFuncId.getValue();
//
//            custBankMicrNum = custBankMicrNum.replace(" ", "");
//
//            AccountInfoBean accountInfoByMicr = null; // ADD 2018/02/28 S21_NA#24219
//            AccountInfoBean accountInfoByInv = null; // ADD 2018/02/28 S21_NA#24219
//
//            // 1) Check by customer account number
//            AccountInfoBean accountInfo = identifyByDsAcctNum(arRcptRcvIntfcPk, intfcInfo.custAcctRefNum.getValue(), resultData);
//
//            if (accountInfo == null) {
//                // 2) check by MICR info
//                if (!NFCI1050.equals(rcvFuncId)) {
//                    // MOD START 2018/02/28 S21_NA#24219
//                    accountInfoByMicr = identifyByMicr(arRcptRcvIntfcPk, custBankMicrNum, resultData);
////                    accountInfo = identifyByMicr(arRcptRcvIntfcPk, custBankMicrNum, resultData);
//                    // MOD END 2018/02/28 S21_NA#24219
//                }
//                // DEL START 2018/02/28 S21_NA#24219
////            }
//    //
////            if (accountInfo == null) {
//                // DEL END 2018/02/28 S21_NA#24219
//
//                // 3) Check by Bill#/Invoice#
//                // MOD START 2018/02/28 S21_NA#24219
//                accountInfoByInv = identifyByInvoiceNum(arRcptRcvIntfcPk, intfcInfo.custInvNum.getValue(), resultData);
////                accountInfo = identifyByInvoiceNum(arRcptRcvIntfcPk, intfcInfo.custInvNum.getValue(), resultData);
//                // MOD END 2018/02/28 S21_NA#24219
////            } // DEL 2018/02/28 S21_NA#24219
//
//            // ADD START 2018/02/28 S21_NA#24219
//                if (accountInfoByMicr == null) {
//                    accountInfo = accountInfoByInv;
//                } else if (accountInfoByInv == null) {
//                    accountInfo = accountInfoByMicr;
//                } else {
//                    if (!isEqualAccoutnInfo(accountInfoByInv, accountInfoByMicr)) {
//                        return null;
//                    } else {
//                        accountInfo = accountInfoByMicr;
//                    }
//                }
//            }
          AccountInfoBean accountInfo = identifyCustomerAccount(intfcInfo, resultData);
            // MOD END 2018/03/01 S21_NA#24465

            // MOD START 2018/02/23 S21_NA#24219
            if (accountInfo != null) {
//          if (resultFlg && accountInfo != null) {
            // MOD END   2018/02/23 S21_NA#24219
                if (identifiedAccountInfo == null) {
                    identifiedAccountInfo = accountInfo;
                } else if (!isEqualAccoutnInfo(identifiedAccountInfo, accountInfo)) {
                    // MOD START 2018/02/23 S21_NA#24219
                    return null;
//                    resultFlg = false;
                    // MOD END 2018/02/23 S21_NA#24219
                }
            }
        }

        // DEL START 2018/02/23 S21_NA#24219
//        if (!resultFlg) {
//            return null;
//        }
        // DEL END 2018/02/23 S21_NA#24219
        return identifiedAccountInfo;
    }
*/
    // DEL END 2018/05/21 S21_NA#26204
    
    // ADD START 2018/05/10 QC#25703
    /**
     * @param intfcInfoList List<AR_RCPT_RCV_INTFCTMsg>
     * @param resultData ResultDataBean
     * @return AccountInfoBean
     */
    private AccountInfoBean identifyCustomerAccount(List<AR_RCPT_RCV_INTFCTMsg> intfcInfoList, ResultDataBean resultData) throws SQLException {

        BigDecimal arRcptRcvIntfcPk = BigDecimal.ZERO;
        String custAcctRefNum  = null;
        String custInvNum      = null;
        String custBankMicrNum = null;
        String arLockBoxCd     = null;
        int intfcInfoListCnt = 0;
        int noDataCnt        = 0;

        if (intfcInfoList != null) {
            intfcInfoListCnt = intfcInfoList.size();
        }

        // START 2018/11/28 H.Ikeda [QC#28839,ADD]
        HashSet<String> hsPayerCustCd = new HashSet<String>();
        // END   2018/11/28 H.Ikeda [QC#28839,ADD]

        AccountInfoBean identifiedAccountInfo = null;
        for (AR_RCPT_RCV_INTFCTMsg intfcInfo : intfcInfoList) {
            if (ZYPCommonFunc.hasValue(intfcInfo.custAcctRefNum)) {
                custAcctRefNum = intfcInfo.custAcctRefNum.getValue();
            }
            if (ZYPCommonFunc.hasValue(intfcInfo.arRcptRcvIntfcPk)) {
                arRcptRcvIntfcPk = intfcInfo.arRcptRcvIntfcPk.getValue();
            }
            if (ZYPCommonFunc.hasValue(intfcInfo.custInvNum)) {
                custInvNum = intfcInfo.custInvNum.getValue();
            }

            // 1) Check by customer account number
            AccountInfoBean accountInfo = identifyByDsAcctNum(arRcptRcvIntfcPk, custAcctRefNum, resultData);
            if (accountInfo == null) {
                // Check by Bill#/Invoice#
                AccountInfoBean accountInfoByInv = identifyByInvoiceNum(arRcptRcvIntfcPk, custInvNum, resultData);
                if (accountInfoByInv == null) {
                    noDataCnt++;
                } else {
                    if (identifiedAccountInfo == null) {
                        identifiedAccountInfo = accountInfoByInv;
                        // START 2018/11/28 H.Ikeda [QC#28839,ADD]
                        billToCustAcctCdMap.put(arRcptRcvIntfcPk, identifiedAccountInfo.getPayerCustCd());
                        // END   2018/11/28 H.Ikeda [QC#28839,ADD]
                    } else {
                        // MOD START 2018/05/21 QC#25204
                        //if (!isEqualAccoutnInfo(identifiedAccountInfo, accountInfoByInv)) {
                        //    return null;
                        //}
                        // START 2018/11/28 H.Ikeda [QC#28839,MOD]
                        //AccountInfoBean aIB = isEqualAccoutnInfoInv(identifiedAccountInfo, accountInfoByInv);
                        AccountInfoBean aIB = isEqualAccoutnInfoInv(identifiedAccountInfo, accountInfoByInv, hsPayerCustCd, arRcptRcvIntfcPk);
                        // END   2018/11/28 H.Ikeda [QC#28839,MOD]
                        if (aIB == null) {
                            return null;
                        }else {
                            identifiedAccountInfo = aIB;
                        }
                        // MOD END   2018/05/21 QC#25204
                    }
                }
            } else {
                identifiedAccountInfo = accountInfo;
            }
        }

        if (intfcInfoList != null) {
            if (ZYPCommonFunc.hasValue(intfcInfoList.get(0).rcvFuncId)) {
                if (NFCI1050.equals(intfcInfoList.get(0).rcvFuncId.getValue())) {
                    return null;
                }
            }
        }
        // bank
        if (ZYPCommonFunc.hasValue(intfcInfoList.get(0).custDsBankMicrNum)) {
            // check by MICR info
            custBankMicrNum = intfcInfoList.get(0).custDsBankMicrNum.getValue();
            if (ZYPCommonFunc.hasValue(custBankMicrNum)) {
                custBankMicrNum = custBankMicrNum.replace(" ", "");
            }
        }
        // MOD START 2018/05/21 QC#25769
        if (ZYPCommonFunc.hasValue(intfcInfoList.get(0).arLockBoxCd)) {
            arLockBoxCd = intfcInfoList.get(0).arLockBoxCd.getValue();
        }
        //AccountInfoBean accountInfoByMicr = identifyByMicr(arRcptRcvIntfcPk, custBankMicrNum, resultData);
        AccountInfoBean accountInfoByMicr = identifyByMicr(arRcptRcvIntfcPk, custBankMicrNum, resultData, arLockBoxCd);
        // MOD END   2018/05/21 QC#25769

        if (intfcInfoListCnt == noDataCnt) {
            if (accountInfoByMicr != null) {
                return accountInfoByMicr;
            }
        } else {
            if (accountInfoByMicr != null) {
                // MOD START 2018/05/21 QC#25204
                //if (isEqualAccoutnInfo(identifiedAccountInfo, accountInfoByMicr)) {
                //    return accountInfoByMicr;
                //} else {
                //    return null;
                //}
                return isEqualAccountInfoBank(identifiedAccountInfo, accountInfoByMicr);
                // MOD END   2018/05/21 QC#25204
            } else {
                return identifiedAccountInfo;
            }
        }
        return null;

        
// -----------------------------------------------------------------------------------
//        AccountInfoBean identifiedAccountInfo = null;
//        for (AR_RCPT_RCV_INTFCTMsg intfcInfo : intfcInfoList) {
//            AccountInfoBean accountInfo = identifyCustomerAccount(intfcInfo, resultData);
//            if (accountInfo != null) {
//                if (identifiedAccountInfo == null) {
//                    identifiedAccountInfo = accountInfo;
//                } else if (!isEqualAccoutnInfo(identifiedAccountInfo, accountInfo)) {
//                    return null;
//                }
//            }
//        }
//
//        return identifiedAccountInfo;
    }
    // ADD END  2018/05/10 QC#25703

    /**
     * isEqualAccoutnInfo
     * 
     * @param accountInfoX  AccountInfoBean
     * @param accountInfoY  AccountInfoBean
     * @param hsPayerCustCd HashSet<String>
     * @param arRcptRcvIntfcPk BigDecimal
     * @return
     */
    // START 2018/11/28 H.Ikeda [QC#28839,MOD]
    //private AccountInfoBean isEqualAccoutnInfoInv(AccountInfoBean accountInfoX, AccountInfoBean accountInfoY) {
    private AccountInfoBean isEqualAccoutnInfoInv(AccountInfoBean accountInfoX, AccountInfoBean accountInfoY, HashSet<String> hsPayerCustCd, BigDecimal arRcptRcvIntfcPk) {
    // END   2018/11/28 H.Ikeda [QC#28839,MOD]

        // itself
        if (ZYPCommonFunc.hasValue(accountInfoY.getPayerCustCd())) {
            if (accountInfoX.getPayerCustCd().equals(accountInfoY.getPayerCustCd())) {
                // START 2018/11/28 H.Ikeda [QC#28839,ADD]
                hsPayerCustCd.add(accountInfoX.getPayerCustCd());
                hsPayerCustCd.add(accountInfoY.getPayerCustCd());
                billToCustAcctCdMap.put(arRcptRcvIntfcPk, accountInfoY.getPayerCustCd());
                // END   2018/11/28 H.Ikeda [QC#28839,ADD]
                return accountInfoY;
            }
        }

        List<Map<String,Object>> relnCust = new ArrayList<Map<String,Object>>();

        // get related customer account
        List<Map<String,Object>> rslt = getRelatedCustomerMap(accountInfoY.getPayerCustCd());
        if (rslt != null && rslt.size() > 0) {
            relnCust.addAll(rslt);
            // add itself
            //if (ZYPCommonFunc.hasValue(accountInfoY.getPayerCustCd())) {
            //    relnCust.add(accountInfoY.getPayerCustCd());
            //}

            for (int i = 0; i < relnCust.size(); i++) {
                if (S21StringUtil.isEquals(relnCust.get(i).get("RELN_DS_ACCT_NUM").toString(), accountInfoX.getPayerCustCd())) {
                    // START 2018/11/28 H.Ikeda [QC#28839,ADD]
                    hsPayerCustCd.add(accountInfoX.getPayerCustCd());
                    hsPayerCustCd.add(accountInfoY.getPayerCustCd());
                    // END   2018/11/28 H.Ikeda [QC#28839,ADD]
                    if ("1".equals(relnCust.get(i).get("DATA_TYPE").toString())) {
                        billToCustAcctCdMap.put(arRcptRcvIntfcPk, accountInfoX.getPayerCustCd());
                        return accountInfoX;
                    } else {
                        billToCustAcctCdMap.put(arRcptRcvIntfcPk, accountInfoY.getPayerCustCd());
                        return accountInfoY;
                    }
                }
            }
            // START 2018/11/28 H.Ikeda [QC#28839,ADD]
            for (String xPayerCustCds : hsPayerCustCd) {
                if (S21StringUtil.isEquals(accountInfoY.getPayerCustCd(), xPayerCustCds) == false) {
                    for (int j = 0; j < relnCust.size(); j++) {
                        if (S21StringUtil.isEquals(relnCust.get(j).get("RELN_DS_ACCT_NUM").toString(), xPayerCustCds)) {
                            billToCustAcctCdMap.put(arRcptRcvIntfcPk, xPayerCustCds);
                            return accountInfoX;
                        }
                    }
                }
            }
            // END   2018/11/28 H.Ikeda [QC#28839,ADD]
        }
        // START   2023/05/08 S.Fujita [QC#61244,MOD]
        // return null;
        billToCustAcctCdMap.put(arRcptRcvIntfcPk, accountInfoY.getPayerCustCd());
        return accountInfoX;
        // END   2023/05/08 S.Fujita [QC#61244,MOD]
    }
    
    /**
     * isEqualAccountInfoBank
     * 
     * @param accountInfoInv  AccountInfoBean
     * @param accountInfoBank AccountInfoBean
     * @return AccountInfoBean
     */
    private AccountInfoBean isEqualAccountInfoBank(AccountInfoBean accountInfoInv, AccountInfoBean accountInfoBank) {

        if (accountInfoBank.getPayerCustCd().equals(accountInfoInv.getPayerCustCd())) {
            // ADD START 2018/05/29 S21_NA#26368
            //return accountInfoInv;
            return accountInfoBank;
            // ADD END   2018/05/29 S21_NA#26368
        }

        List<Map<String,Object>> relnCust = new ArrayList<Map<String,Object>>();

        // get related customer account
        List<Map<String,Object>> rslt = getRelatedCustomerMap(accountInfoBank.getPayerCustCd());
        if (rslt != null && rslt.size() > 0) {
            relnCust.addAll(rslt);
            for (int i = 0; i < relnCust.size(); i++) {
                if (S21StringUtil.isEquals(relnCust.get(i).get("RELN_DS_ACCT_NUM").toString(), accountInfoInv.getPayerCustCd())) {
                    // START 2018/08/07 H.Ikeda [QC#27569,MOD]
                    String type = relnCust.get(i).get("DATA_TYPE").toString();
                    if (INV_DS_ACCT_NUM.equals(type)) {
                        accountInfoInv.setCustDsBankAcctPk(new BigDecimal(INV_DS_ACCT_NUM));
                        return accountInfoInv;
                    } else {
                        // ADD START 2018/05/29 S21_NA#26368
                        //return accountInfoInv;
                        return accountInfoBank;
                        // ADD END   2018/05/29 S21_NA#26368
                    }
                    // END   2018/08/07 H.Ikeda [QC#27569,MOD]
                }
            }
        }

        return null;
    }
    
    private boolean isEqualAccoutnInfo(AccountInfoBean accountInfoX, AccountInfoBean accountInfoY) {
        // ADD START 2018/05/09 S21_NA#25808
        List<String> relnCust = new ArrayList<String>();
        // ADD END   2018/05/09 S21_NA#25808

        // X == null
        if (accountInfoX == null) {
            if (accountInfoY == null) {
                return true;
            } else {
                return false;
            }
        } else {
            // X != null
            if (accountInfoY == null) {
                return false;
            } else {
                // ADD START 2018/05/09 S21_NA#25808
                // get related customer account
                List<String> rslt = getRelatedCustomer(accountInfoY.getPayerCustCd());
                if (rslt != null && rslt.size() > 0) {
                    relnCust.addAll(rslt);
                    // add itself
                    if (ZYPCommonFunc.hasValue(accountInfoY.getPayerCustCd())) {
                        relnCust.add(accountInfoY.getPayerCustCd());
                    }
                    for (int i = 0; i < relnCust.size(); i++) {
                        if (S21StringUtil.isEquals(relnCust.get(i), accountInfoX.getPayerCustCd())) {
                            return true;
                        }
                    }
                }
                // ADD END   2018/05/09 S21_NA#25808

                // X != null && Y != null
                if (ZYPCommonFunc.hasValue(accountInfoX.getDsAcctCustPk()) && //
                        accountInfoX.getDsAcctCustPk().compareTo(accountInfoY.getDsAcctCustPk()) == 0 && //
                        S21StringUtil.isEquals(accountInfoX.getPayerCustCd(), accountInfoY.getPayerCustCd())) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    // ADD START 2018/05/09 S21_NA#25808
    @SuppressWarnings("unchecked")
    private List<String> getRelatedCustomer(String payerCustCd) {

        if (!ZYPCommonFunc.hasValue(payerCustCd)) {
            return null;
        }

        Map<String, String> sqlParam = new HashMap<String, String>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("payerCustCd", payerCustCd);
        sqlParam.put("flgY", ZYPConstant.FLG_ON_Y);
        sqlParam.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.MYCSA_ACCOUNT);
        // ADD START 2018/05/16 S21_NA#26179
        sqlParam.put("slsDt", this.batchProcDt);
        // ADD END   2018/05/16 S21_NA#26179

        return (List<String>) ssmBatchClient.queryObjectList("getRelatedCustomer", sqlParam);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getRelatedCustomerMap(String payerCustCd) {

        if (!ZYPCommonFunc.hasValue(payerCustCd)) {
            return null;
        }

        Map<String, String> sqlParam = new HashMap<String, String>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("payerCustCd", payerCustCd);
        sqlParam.put("flgY", ZYPConstant.FLG_ON_Y);
        sqlParam.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.MYCSA_ACCOUNT);
        // ADD START 2018/05/16 S21_NA#26179
        sqlParam.put("slsDt", this.batchProcDt);
        // ADD END   2018/05/16 S21_NA#26179

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRelatedCustomerMap", sqlParam);
    }
    // ADD END   2018/05/09 S21_NA#25808

  private ResultDataBean registerCustomerAccount(List<List<String>> lDataList, AR_RCPT_RCV_INTFCTMsg intfcInfo, AccountInfoBean identifiedAccountInfo, ResultDataBean resultData, String rcvFuncId) throws SQLException {

        BigDecimal arRcptRcvIntfcPk = intfcInfo.arRcptRcvIntfcPk.getValue();
        String custBankMicrNum = intfcInfo.custDsBankMicrNum.getValue();
        // ADD START 2018/05/22 S21_NA#25769
        String arLockBoxCd = intfcInfo.arLockBoxCd.getValue();
        // ADD END   2018/05/22 S21_NA#25769
        
        custBankMicrNum = custBankMicrNum.replace(" ", "");

        // When customer is successfully identified.
        if (identifiedAccountInfo != null) {
            if (ZYPCommonFunc.hasValue(custBankMicrNum) && identifiedAccountInfo.getCustDsBankAcctPk() == null) {

                // MOD START 2018/05/22 S21_NA#25769
                // Create DS_BANK_ACCT
                //BigDecimal custDsBankAcctPk = registerBankAccount(intfcInfo, identifiedAccountInfo, resultData, custBankMicrNum, rcvFuncId);
                BigDecimal custDsBankAcctPk = registerBankAccount(intfcInfo, identifiedAccountInfo, resultData, custBankMicrNum, rcvFuncId, arLockBoxCd);
                // MOD END   2018/05/22 S21_NA#25769
                identifiedAccountInfo.setCustDsBankAcctPk(custDsBankAcctPk);
            }
            // START 2018/08/07 H.Ikeda [QC#27569,ADD]
            else if (ZYPCommonFunc.hasValue(identifiedAccountInfo.getCustDsBankAcctPk()) && new BigDecimal(INV_DS_ACCT_NUM).compareTo(identifiedAccountInfo.getCustDsBankAcctPk()) == 0) {
                identifiedAccountInfo.setCustDsBankAcctPk(null);
            }
            // END   2018/08/07 H.Ikeda [QC#27569,ADD]

            resultData.setAccountInfo(identifiedAccountInfo);

            if (ZYPCommonFunc.hasValue(intfcInfo.arLockBoxFileNm)) {

                //boolean hasError = hasConsistencyError(intfcInfo, identifiedAccountInfo, resultData);
                
                boolean hasError = hasConsistencyError(lDataList, intfcInfo, identifiedAccountInfo, resultData);
                if (hasError) {
                    resultData.setErrorIntfcPk(arRcptRcvIntfcPk);

                    String lockBoxFileNm = intfcInfo.arLockBoxFileNm.getValue();

                    if (ZYPCommonFunc.hasValue(lockBoxFileNm)) {
                        this.errorLockBoxFileNms.add(lockBoxFileNm);
                    }

                } else {
                    resultData.setVldCustRcptNum(intfcInfo.custRcptNum.getValue());
                }
            }
        } else {
            if (ZYPCommonFunc.hasValue(custBankMicrNum)) {
                if (procFlg) {
                    BigDecimal bankAcctPK = registerBankAccount_UnidentifiedAccountInfo(intfcInfo, identifiedAccountInfo, resultData, custBankMicrNum);

                    if (bankAcctPK != null) {
                        dsBankAcctPk = bankAcctPK;
                        procFlg = false;
                    }
                }
                ZYPEZDItemValueSetter.setValue(intfcInfo.custDsBankAcctPk, dsBankAcctPk);
            }
        }
        return resultData;
    }
    // Add End 2018/01/19 S21_NA#22421

    // START 2021/06/07 G.Delgado [QC#58800,ADD]
    /**
     * Generates next sequence of a check number
     * @param custRcptNum String
     * @return String
     */
    private String getCustRcptNumNextSeq(String custRcptNum) {
        if (!ZYPCommonFunc.hasValue(custRcptNum) || CHECK_NUM_LAST_SEQ.equals(custRcptNum)) {
            return null;
        }

        char[] chArr = custRcptNum.toCharArray();
        boolean incrNextChar = true;
        for (int i = chArr.length - 1; i >= 0; i--) {
            if (!Character.isUpperCase(chArr[i])) {
                break;
            }
            if (chArr[i] == CHAR_Z) {
                chArr[i] = CHAR_A;
            } else {
                chArr[i] = (char) (chArr[i] + 1);
                incrNextChar = false;
                break;
            }
        }
        String nextCustRcptNum = String.valueOf(chArr);
        if (incrNextChar) {
            nextCustRcptNum = S21StringUtil.concatStrings(nextCustRcptNum, String.valueOf(CHAR_A));
        }

        int excessChar = nextCustRcptNum.length() - CHECK_NUM_MAX_LEN;
        if (excessChar > 0) {
            return nextCustRcptNum.substring(excessChar);
        } else {
            return nextCustRcptNum;
        }
    }

    /**
     * Get substring of check number to use for searching sequences
     * @param custRcptNum String
     * @param currSeq String
     * @return String
     */
    private String getCustRcptNumPtrnParam(String custRcptNum, String currSeq) {
        if (!ZYPCommonFunc.hasValue(custRcptNum) || !ZYPCommonFunc.hasValue(currSeq)) {
            return null;
        }

        String subStr = null;
        for (int i = 0; i < custRcptNum.length(); i++) {
            subStr = custRcptNum.substring(i);
            if (currSeq.startsWith(subStr)) {
                return subStr;
            }
        }
        return currSeq;
    }

    /**
     * Get latest sequence of a check number in AR_RCPT_RCV_WRK
     * @param custRcptNum String
     * @param payerCustCd String
     * @param custRcptAmt BigDecimal
     * @return String
     */
    private String getCustRcptNumSeqFromArRcptRcvWrk(String custRcptNum, String payerCustCd, BigDecimal custRcptAmt) {
        Map<String, String> sqlParam = new HashMap<String, String>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("payerCustCd", payerCustCd);
        sqlParam.put("custRcptAmt", custRcptAmt.toString());
        sqlParam.put("custRcptNumPtrn", String.format(CHECK_NUM_PATTERN, custRcptNum));

        return (String) ssmBatchClient.queryObject("getCustRcptNumSeqFromArRcptRcvWrk", sqlParam);
    }

    /**
     * Get latest sequence of a check number in AR_RCPT
     * @param custRcptNum String
     * @param payerCustCd String
     * @param custRcptAmt BigDecimal
     * @return String
     */
    private String getCustRcptNumSeqFromArRcpt(String custRcptNum, String payerCustCd, BigDecimal custRcptAmt) {
        Map<String, String> sqlParam = new HashMap<String, String>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("payerCustCd", payerCustCd);
        sqlParam.put("custRcptAmt", custRcptAmt.toString());
        sqlParam.put("custRcptNumPtrn", String.format(CHECK_NUM_PATTERN, custRcptNum));

        return (String) ssmBatchClient.queryObject("getCustRcptNumSeqFromArRcpt", sqlParam);
    }
    // END 2021/06/07 G.Delgado [QC#58800,ADD]

    // START 2021/10/18 S.Go [QC#59273,ADD]
    /**
     * Count the number of entries in AR_RCPT_RCV_INTFC with the specific custRcptNum, payerCustCd and custRcptAmt
     * @param custRcptNum String
     * @param payerCustCd String
     * @param custRcptAmt BigDecimal
     * @return int
     */
    private int countArRcptRcvIntfc(String custRcptNum, String payerCustCd, BigDecimal custRcptAmt) throws SQLException {
        int cnt = 0;

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            final Map<String, String> ssmParam = new HashMap<String, String>();

            ssmParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
            ssmParam.put("custRcptNum", custRcptNum);
            ssmParam.put("payerCustCd", payerCustCd);
            ssmParam.put("custRcptAmt", custRcptAmt.toString());

            stmt = this.ssmLLClient.createPreparedStatement("countArRcptRcvIntfc", ssmParam);

            rs = stmt.executeQuery();

            if (rs.next()) {
                cnt = rs.getInt("CNT");
            }

            return cnt;

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            debugLog("execute : closeResource");
        }
    }

    /**
     * Get latest sequence of a check number in AR_RCPT_RCV_INTFC
     * @param custRcptNum String
     * @param payerCustCd String
     * @param custRcptAmt BigDecimal
     * @return String
     */
    private String getCustRcptNumSeqFromArRcptRcvIntfc(String custRcptNum, String payerCustCd, BigDecimal custRcptAmt) {
        Map<String, String> sqlParam = new HashMap<String, String>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("payerCustCd", payerCustCd);
        sqlParam.put("custRcptAmt", custRcptAmt.toString());
        sqlParam.put("custRcptNumPtrn", String.format(CHECK_NUM_PATTERN, custRcptNum));

        return (String) ssmBatchClient.queryObject("getCustRcptNumSeqFromArRcptRcvIntfc", sqlParam);
    }
    // END 2021/10/18 S.Go [QC#59273,ADD]
}
