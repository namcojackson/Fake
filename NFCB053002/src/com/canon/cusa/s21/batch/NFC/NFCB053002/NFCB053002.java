package com.canon.cusa.s21.batch.NFC.NFCB053002;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_RCPT_RCV_INFO_WRKTMsg;
import business.db.AR_RCPT_RCV_INTFCTMsg;
import business.db.AR_RCPT_RCV_WRKTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.CUST_CR_PRFLTMsg;
import business.db.CUST_CR_PRFLTMsgArray;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.DS_ACCT_CR_PRFLTMsgArray;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCDbConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_BAT_INFO_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CUST_ID_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_LOCK_BOX_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AUTO_CASH_HRCH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Expand Receipt to Individual Invoice
 * 
 * <pre>
 * 
 * Date         Company         Name            Create/Update                  Defect No
 * ---------------------------------------------------------------------------------------
 * 1/4/2016     CSAI            K.Uramori       Create                         N/A
 * 3/1/2016     Fujitsu         T.Tanaka        Update                         Def#4790
 * 3/2/2016     Fujitsu         T.Tanaka        Update                         Def#4999
 * 3/8/2016     Fujitsu         T.Tanaka        Update                         Def#5154
 * 3/11/2016    Fujitsu         T.Tanaka        Update                         Def#5296
 * 4/04/2016    Fujitsu         T.Tanaka        Update                         Def#6521
 * 4/21/2016    Fujitsu         S.Fujita        Update                         QC#6616,6635
 * 6/05/2017    Hitachi         E.Kameishi      Update                         QC#18703
 * 9/05/2017    Hitachi         T.Tsuchida      Update                         QC#20939
 * 11/15/2017   Hitachi         Y.Takeno        Update                         QC#17322
 * 04/18/2018   Hitachi         E.Kameishi      Update                         QC#21037
 * 05/10/2018   Hitachi         E.Kameishi      Update                         QC#25721
 * 05/10/2018   Hitachi         E.Kameishi      Update                         QC#26054
 * 2018/05/17   Fujitsu         H.Ikeda         Update                         QC#26179
 * 2018/07/04   Fujitsu         H.Ikeda         Update                         QC#25731
 * 2018/07/13   Fujitsu         H.Ikeda         Update                         QC#26860
 * 2018/07/30   Fujitsu         H.Ikeda         Update                         QC#27409
 * 2018/08/02   Fujitsu         T.Murai         Update                         QC#26249
 * 2018/08/07   Fujitsu         H.Ikeda         Update                         QC#25912
 * 2018/08/09   Fujitsu         H.Ikeda         Update                         QC#24757
 * 2018/08/10   Fujitsu         H.Ikeda         Update                         QC#27662
 * 2018/08/20   Fujitsu         H.Ikeda         Update                         QC#25706
 * 2018/08/22   Fujitsu         H.Ikeda         Update                         QC#27776
 * 2018/08/28   Fujitsu         H.Ikeda         Update                         QC#26135
 * 2018/09/03   Fujitsu         H.Ikeda         Update                         QC#28062
 * 2018/09/10   Fujitsu         H.Ikeda         Update                         QC#28057
 * 2018/10/11   Fujitsu         H.Ikeda         Update                         QC#28752
 * 2018/10/24   Fujitsu         H.Ikeda         Update                         QC#28815
 * 2018/11/20   Fujitsu         H.Ikeda         Update                         QC#29293
 * 2018/11/30   Fujitsu         H.Ikeda         Update                         QC#28839
 * 2019/01/21   Fujitsu         H.Ikeda         Update                         QC#29995
 * 2019/07/09   Fujitsu         H.Ikeda         Update                         QC#51259
 * 2019/08/23   Fujitsu         T.Murai         Update                         QC#52961
 * 2019/09/24   Fujitsu         H.Ikeda         Update                         QC#53535
 * 2020/01/08   Fujitsu         H.Ikeda         Update                         QC#55180
 * 2020/03/09   Fujitsu         H.Ikeda         Update                         QC#56146
 * 2022/03/03   Hitachi         R.Takau         Update                         QC#61167
 * </pre>
 */
public class NFCB053002 extends S21BatchMain implements ZYPConstant, NFCB053002Constant {

    /** Ssm Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SQL Access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Processing Count */
    private int totalRecordCnt = 0;

    /** normal Count */
    private int normalRecordCnt = 0;

    /** err Count */
    private int errorRecordCnt = 0;

    /** Termination Code */
    private TERM_CD termCd;

    /** batProcDate */
    private String batProcDate = null;

    /** GLOBAL_COMPANY_CODE */
    private String glblCmpyCd = null;

    // START 2017/09/05 T.Tsuchida [QC#20939,ADD]
    /** NFCI1040 : CFS Misdirected */
    private static final String NFCI1050 = "NFCI1050";
    // END 2017/09/05 T.Tsuchida [QC#20939,ADD]

    /** List of TMsgs */
    private List<AR_RCPT_RCV_WRKTMsg> listTmsg;

    /** List of TMsgs */
    private List<AR_RCPT_RCV_INFO_WRKTMsg> listInfoTmsg;

    /** List of TMsgs */
    private List<AR_RCPT_RCV_INTFCTMsg> listIntfcTmsg;

    // START 2018/07/04 H.Ikeda [QC#25731,ADD]
    /** Payment Number */
    private String multipleCnt = null;

    /** Multiple No */
    private int multipleNo = 0;
    
    /** Default Multiple No */
    private final int defMultipleNo = 10;
    // END   2018/07/04 H.Ikeda [QC#25731,ADD]
    
    @Override
    protected void initRoutine() {
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        this.termCd = TERM_CD.NORMAL_END;

        if (S21StringUtil.isEmpty(this.glblCmpyCd)) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException(NFCM0501E, new String[] {NFCDbConst.GLBL_CMPY_CD });
        }

        this.batProcDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);

        listTmsg = new ArrayList<AR_RCPT_RCV_WRKTMsg>();
        listInfoTmsg = new ArrayList<AR_RCPT_RCV_INFO_WRKTMsg>();
        listIntfcTmsg = new ArrayList<AR_RCPT_RCV_INTFCTMsg>();

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
    }

    @Override
    protected void mainRoutine() {
        PreparedStatement stmt = null;
        PreparedStatement stmtSub = null;
        ResultSet rs = null;
        ResultSet rsSub = null;

        try {
            stmt = getTargetData();
            rs = stmt.executeQuery();

            String prevBatNm = "";
            String prevVldCustNum = "";
            BigDecimal rcvSqPk = null;
            //START 2017/06/05 E.Kameishi [QC#18703,MOD]
            String prevPayerCustCd = "";
            String preCustDsBankMicrNum = "";
            BigDecimal chkAmt = BigDecimal.ZERO;
            BigDecimal chkActAmt = BigDecimal.ZERO; // 2018/08/02 T.Murai [QC#26249,ADD]
            boolean updateRcvWrkFlg = false;
            //END 2017/06/05 E.Kameishi [QC#18703,MOD]
            //START 2018/07/30 H.Ikeda [QC#27409,ADD]
            String rcPayerCustCd = "";
            //END   2018/07/30 H.Ikeda [QC#27409,ADD]
            int hdrCnt = 0;
            int dtlCnt = 0;
            // START 2018/07/30 H.Ikeda [QC#27409,DEL]
            //List<String> relnCust = new ArrayList<String>();
            // END   2018/07/30 H.Ikeda [QC#27409,DEL]
            // START 2018/04/18 E.Kameishi [QC#21037,ADD]
            List<Map<String, Object>> trxbalList = new ArrayList<Map<String, Object>>();
            // END 2018/04/18 E.Kameishi [QC#21037,ADD]
            // START 2018/08/10 H.Ikeda [QC#27662,ADD]
            List<String> trxBalUseList = new ArrayList<String>();
            // END   2018/08/10 H.Ikeda [QC#27662,ADD]
            // START 2018/09/03 H.Ikeda [QC#28062,ADD]
            List<Map<String, Object>> trxbalCrmList = new ArrayList<Map<String, Object>>();
            // END 2018/09/03 H.Ikeda [QC#28062,ADD]
            // Start 2018/09/10 H.Ikeda [QC#28057,ADD]
            HashMap<String, List<Map<String, Object>>> trxbalListMap = new HashMap<String, List<Map<String, Object>>>();
            HashMap<String, List<Map<String, Object>>> trxbalCrmListMap = new HashMap<String, List<Map<String, Object>>>();
            // End   2018/09/10 H.Ikeda [QC#28057,ADD]
            // START 2019/07/09 H.Ikeda [QC#51259, ADD]
            String arLockBoxBatNum = "";     // AR_LOCK_BOX_BAT_NUM
            String arLockBoxBatLineNum = ""; // AR_LOCK_BOX_BAT_LINE_NUM
            // END   2019/07/09 H.Ikeda [QC#51259, ADD]
            while (rs.next()) {
                //START 2017/06/05 E.Kameishi [QC#18703,MOD]
                updateRcvWrkFlg = false;
                //END 2017/06/05 E.Kameishi [QC#18703,MOD]
                // When batch name break
                if (ZYPCommonFunc.hasValue(rs.getString(AR_BAT_RCPT_NM)) && !prevBatNm.equals(rs.getString(AR_BAT_RCPT_NM))) {

                    // get new RCV_SQ_PK
                    rcvSqPk = ZYPOracleSeqAccessor.getNumberBigDecimal(NFCConst.CST_SEQ_ID_RCV);

                    prevBatNm = rs.getString(AR_BAT_RCPT_NM);

                    // reset header number counter
//                    hdrCnt = 0;
                    // START 2018/07/06 H.Ikeda [QC#25731,ADD]
                    hdrCnt = getRcvHdrNum(prevBatNm);
                    // END   2018/07/06 H.Ikeda [QC#25731,ADD]

                    // reset detail number counter Def#5154
                    dtlCnt = 0;

                    // reset
                    prevVldCustNum = "";
                }
                // START 2019/07/09 H.Ikeda [QC#51259, MOD]
                if (arLockBoxBatNum.equals(rs.getString(AR_LOCK_BOX_BAT_NUM)) && !arLockBoxBatLineNum.equals(rs.getString(AR_LOCK_BOX_BAT_LINE_NUM))) {
                    // reset
                    prevVldCustNum = "";
                }
                arLockBoxBatNum     = rs.getString(AR_LOCK_BOX_BAT_NUM);      // AR_LOCK_BOX_BAT_NUM
                arLockBoxBatLineNum = rs.getString(AR_LOCK_BOX_BAT_LINE_NUM); // AR_LOCK_BOX_BAT_LINE_NUM
                // START 2017/09/05 T.Tsuchida [QC#20939,MOD]
                //START 2017/06/05 E.Kameishi [QC#18703,MOD]
//                if ((ZYPCommonFunc.hasValue(rs.getString(VLD_CUST_RCPT_NUM)) && !prevVldCustNum.equals(rs.getString(VLD_CUST_RCPT_NUM)))
//                        || (ZYPCommonFunc.hasValue(rs.getString(VLD_CUST_RCPT_NUM)) && prevVldCustNum.equals(rs.getString(VLD_CUST_RCPT_NUM)) 
//                                && ZYPCommonFunc.hasValue(rs.getString(PAYER_CUST_CD)) && !prevPayerCustCd.equals(rs.getString(PAYER_CUST_CD)))
////                                || (!AR_LOCK_BOX.CFS_LOCKBOX.equals(rs.getString(AR_LOCK_BOX_CD))
//                                  || (!NFCI1050.equals(rs.getString(RCV_FUNC_ID))
//                                        && ZYPCommonFunc.hasValue(rs.getString(VLD_CUST_RCPT_NUM)) && prevVldCustNum.equals(rs.getString(VLD_CUST_RCPT_NUM))
//                                        && !ZYPCommonFunc.hasValue(rs.getString(PAYER_CUST_CD))
//                                        && ZYPCommonFunc.hasValue(rs.getString(CUST_DS_BANK_MICR_NUM)) && !preCustDsBankMicrNum.equals(rs.getString(CUST_DS_BANK_MICR_NUM)))
////                                        || AR_LOCK_BOX.CFS_LOCKBOX.equals(rs.getString(AR_LOCK_BOX_CD)) && !ZYPCommonFunc.hasValue(rs.getString(PAYER_CUST_CD))) {
//                                        // START 2018/08/07 H.Ikeda [QC#25912,MOD]
//                                        //|| NFCI1050.equals(rs.getString(RCV_FUNC_ID)) && !ZYPCommonFunc.hasValue(rs.getString(PAYER_CUST_CD))) {
//                                        || NFCI1050.equals(rs.getString(RCV_FUNC_ID))) {
//                                        // END   2018/08/07 H.Ikeda [QC#25912,MOD]
                if (
                    (ZYPCommonFunc.hasValue(rs.getString(VLD_CUST_RCPT_NUM)) && !prevVldCustNum.equals(rs.getString(VLD_CUST_RCPT_NUM))) 
                     || 
                    (ZYPCommonFunc.hasValue(rs.getString(VLD_CUST_RCPT_NUM)) && prevVldCustNum.equals(rs.getString(VLD_CUST_RCPT_NUM)) && 
                     ZYPCommonFunc.hasValue(rs.getString(PAYER_CUST_CD)) && !prevPayerCustCd.equals(rs.getString(PAYER_CUST_CD)))
                     ||
                    (!NFCI1050.equals(rs.getString(RCV_FUNC_ID)) && 
                     ZYPCommonFunc.hasValue(rs.getString(VLD_CUST_RCPT_NUM)) && prevVldCustNum.equals(rs.getString(VLD_CUST_RCPT_NUM)) && !ZYPCommonFunc.hasValue(rs.getString(PAYER_CUST_CD)) && 
                     ZYPCommonFunc.hasValue(rs.getString(CUST_DS_BANK_MICR_NUM)) && !preCustDsBankMicrNum.equals(rs.getString(CUST_DS_BANK_MICR_NUM))) 
                     ||
                    NFCI1050.equals(rs.getString(RCV_FUNC_ID))
                   ) {
                // END   2019/07/09 H.Ikeda [QC#51259, MOD]
                    // START 2018/08/07 H.Ikeda [QC#24757,MOD]
//                  if (hdrCnt > 0 && dtlCnt > 0 && AR_LOCK_BOX.CFS_LOCKBOX.equals(rs.getString(AR_LOCK_BOX_CD)))
                    if (hdrCnt > 0 && dtlCnt > 0 && NFCI1050.equals(rs.getString(RCV_FUNC_ID))) {
                        updateRcvWrk(rcvSqPk, hdrCnt, chkAmt);
                    }
                    // END   2018/08/07 H.Ikeda [QC#24757,MOD]
                    // increment header number
                    hdrCnt += 1;
                    updateRcvWrkFlg = true;
                    chkAmt = rs.getBigDecimal(CUST_INV_AMT);
                    prevVldCustNum = rs.getString(VLD_CUST_RCPT_NUM);
                    prevPayerCustCd = rs.getString(PAYER_CUST_CD);
                    preCustDsBankMicrNum = rs.getString(CUST_DS_BANK_MICR_NUM);
                    //END 2017/06/05 E.Kameishi [QC#18703,MOD]
                    chkActAmt = BigDecimal.ZERO; // 2018/08/02 T.Murai [QC#26249,ADD]
                    // END 2017/09/05 T.Tsuchida [QC#20939,MOD]

                    // START 2018/07/30 H.Ikeda [QC#27409,DEL]
                    //// get related customer account
                    //List<String> rslt = getRelatedCustomer(rs.getString(PAYER_CUST_CD));
                    //
                    //relnCust.clear();
                    //
                    //if (rslt != null && rslt.size() > 0) {
                    //    relnCust.addAll(rslt);
                    //}
                    //
                    //// add itself
                    // if (ZYPCommonFunc.hasValue(rs.getString(PAYER_CUST_CD))) {
                    //    relnCust.add(rs.getString(PAYER_CUST_CD));
                    //}
                    // END  2018/07/30 H.Ikeda [QC#27409,DEL]
                    // reset detail number counter
                    dtlCnt = 0;

                    // START 2018/04/18 E.Kameishi [QC#21037,ADD]
                    if (!hasValue(rs.getString(PAYER_CUST_CD))) {
                        dtlCnt += 1;
                        createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, rs.getString(CUST_INV_NUM), BigDecimal.ZERO, null, false);
                        updateIntfc(rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK), null, null);
                        continue;
                    }
                    // Start 2018/09/10 H.Ikeda [QC#28057,MOD]
                    if (trxbalListMap.get(rs.getString(PAYER_CUST_CD)) == null) {
                        //trxbalList.clear();
                        String billToCustCd =  getCustomer(rs.getBigDecimal(CUST_DS_BANK_ACCT_PK));
                        String autoCashHrchCd = getHrchCdFromBill(billToCustCd, rs.getString(PAYER_CUST_CD));
                        // START 2018/10/24 H.Ikeda [QC#28815,MOD]
                        //if (AUTO_CASH_HRCH.USA_RETAIL_DEFAULT.equals(autoCashHrchCd)) {
                        if (AUTO_CASH_HRCH.CSA_CLEAR_ACCOUNT.equals(autoCashHrchCd)) {
                        // END   2018/10/24 H.Ikeda [QC#28815,MOD]
                            // Start 2018/08/28 H.Ikeda [QC#26135,MOD]
                            //trxbalList= getTrxBalByDueDt(rs.getString(PAYER_CUST_CD));
                            // Start 2018/09/03 H.Ikeda [QC#28062,MOD]
                            //trxbalList= getTrxBalByDueDt(rs.getString(PAYER_CUST_CD), rs.getString(AR_LOCK_BOX_FILE_NM));
                            trxbalList= getTrxBalByDueDt(rs.getString(PAYER_CUST_CD), rs.getString(AR_LOCK_BOX_FILE_NM), false);
                            // End   2018/09/03 H.Ikeda [QC#28062,MOD]
                            // End   2018/08/28 H.Ikeda [QC#26135,MOD]
                        }
                    } else {
                        trxbalList= trxbalListMap.get(rs.getString(PAYER_CUST_CD));
                    }
                    // END 2018/04/18 E.Kameishi [QC#21037,ADD]
                    if (trxbalCrmListMap.get(rs.getString(PAYER_CUST_CD)) == null) {
                        //trxbalCrmList.clear();
                        String billToCustCd =  getCustomer(rs.getBigDecimal(CUST_DS_BANK_ACCT_PK));
                        String autoCashHrchCd = getHrchCdFromBill(billToCustCd, rs.getString(PAYER_CUST_CD));
                        // START 2018/10/24 H.Ikeda [QC#28815,MOD]
                        //if (AUTO_CASH_HRCH.USA_RETAIL_DEFAULT.equals(autoCashHrchCd)) {
                        if (AUTO_CASH_HRCH.CSA_CLEAR_ACCOUNT.equals(autoCashHrchCd)) {
                        // END   2018/10/24 H.Ikeda [QC#28815,MOD]
                            trxbalCrmList= getTrxBalByDueDt(rs.getString(PAYER_CUST_CD), rs.getString(AR_LOCK_BOX_FILE_NM), true);
                        }
                    } else {
                        trxbalCrmList= trxbalCrmListMap.get(rs.getString(PAYER_CUST_CD));
                    }
                    // End  2018/09/10 H.Ikeda [QC#28057,MOD]
                    //START 2018/07/30 H.Ikeda [QC#27409,ADD]
                    rcPayerCustCd = rs.getString(PAYER_CUST_CD);
                    //END   2018/07/30 H.Ikeda [QC#27409,ADD]
                }

                //START 2017/06/05 E.Kameishi [QC#18703,MOD]
                if (!updateRcvWrkFlg) {
                    chkAmt = chkAmt.add(rs.getBigDecimal(CUST_INV_AMT));
                }
                //END 2017/06/05 E.Kameishi [QC#18703,MOD]

                // START 2018/05/10 E.Kameishi [QC#25721,MOD]
                // If CUST_INV_NUM is null, create AR_RCPT_RCV_WRK and
                // go to next record.
                //if (!hasValue(rs.getString(CUST_INV_NUM))) {
                //    dtlCnt += 1;
//                    createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, null, BigDecimal.ZERO, null);
                //    createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, null, BigDecimal.ZERO, null, false);
                //    updateIntfc(rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK), null, null);

                //    continue;
                //}

                // Def#4790 Payer is null
                if (!hasValue(rs.getString(PAYER_CUST_CD))) {
                    dtlCnt += 1;
                    //Def#5154
                    //                    createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, null, BigDecimal.ZERO, null);
//                    createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, rs.getString(CUST_INV_NUM), BigDecimal.ZERO, null);
                    createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, rs.getString(CUST_INV_NUM), BigDecimal.ZERO, null, false);
                    updateIntfc(rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK), null, null);

                    continue;
                }

                boolean isGrpInv = true;

                // START 2016/04/21 S.Fujita [QC#6616,MOD]
                BigDecimal rcptAmt = BigDecimal.ZERO;
                rcptAmt = rs.getBigDecimal(CUST_INV_AMT);
                BigDecimal calRmngAmt = BigDecimal.ZERO;
                boolean isEnd = false;
                // END 2016/04/21 S.Fujita [QC#6616,MOD]

                // START 2018/11/30 H.Ikeda [QC#28839,MOD]
                String billToCustAcctCd = rs.getString(BILL_TO_CUST_ACCT_CD);
                // END   2018/11/30 H.Ikeda [QC#28839,MOD]

                try {
                    // Check if it is group invoice
                    if (hasValue(rs.getString(CUST_INV_NUM))) {
                        //START   2018/07/30 H.Ikeda [QC#27409,MOD]
                        //stmtSub = getTrxBalByGrpInv(rs.getString(CUST_INV_NUM), relnCust);
                        // START 2018/11/30 H.Ikeda [QC#28839,MOD]
                        if (hasValue(billToCustAcctCd)) {
                            stmtSub = getTrxBalByGrpInv(rs.getString(CUST_INV_NUM), billToCustAcctCd);
                        } else {
                            stmtSub = getTrxBalByGrpInv(rs.getString(CUST_INV_NUM), rcPayerCustCd);
                        }
                        // END   2018/11/30 H.Ikeda [QC#28839,MOD]
                        //END     2018/07/30 H.Ikeda [QC#27409,MOD]
                        rsSub = stmtSub.executeQuery();

                        boolean isNext = rsSub.next();
                        boolean intfcUpFlg = false;
                        
                        

                        // START  2019/09/17 H.Ikeda [QC#53535, MOD]
                        isGrpInv = false;
                        List<String> wornList = new ArrayList<String>();

                        //START 2023/03/20 R.Takau [QC#61167,ADD]
                        BigDecimal ableAmt = BigDecimal.ZERO;
                        if (isNext) {
                            if (BigDecimal.ZERO.compareTo(rcptAmt) == 0) {
                                ableAmt = rs.getBigDecimal(CUST_RCPT_AMT).subtract(chkActAmt);
                            } else {
                                ableAmt = rcptAmt;
                            }
                        }
                        //END 2023/03/20 R.Takau [QC#61167,ADD]

                        while (isNext) {
                            isGrpInv = true;
                            dtlCnt += 1;
                            //START 2023/03/20 R.Takau [QC#61167,MOD]
//                            if (rcptAmt.subtract(rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT)).compareTo(BigDecimal.ZERO) > 0) {
//                                rcptAmt = rcptAmt.subtract(rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT));
//                                calRmngAmt = rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT);
//                            } else {
//                                calRmngAmt = rcptAmt;
//                                isEnd = true;
//                            }
                            if (ableAmt.subtract(rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT)).compareTo(BigDecimal.ZERO) > 0) {
                                ableAmt = ableAmt.subtract(rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT));
                                calRmngAmt = rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT);
                            } else {
                                calRmngAmt = ableAmt;
                                isEnd = true;
                            }
                            //END 2023/03/20 R.Takau [QC#61167,MOD]

                            // create AR_RCPT_RCV_WRK
                            createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, rsSub.getString(AR_TRX_NUM), calRmngAmt, rsSub.getString(GRP_INV_NUM), true);
                            chkActAmt = chkActAmt.add(calRmngAmt);
                            // update AR_RCPT_RCV_INTFC
                            if (!intfcUpFlg) {
                                updateIntfc(rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK), rsSub.getString(AR_TRX_NUM), rsSub.getString(GRP_INV_NUM));
                                intfcUpFlg = true;
                            }
                            // Cash Application Status check
                            if (!AR_CASH_APPLY_STS.PARTIAL.equals(rsSub.getString(AR_CASH_APPLY_STS_CD)) && !AR_CASH_APPLY_STS.UNAPPLIED.equals(rsSub.getString(AR_CASH_APPLY_STS_CD))) {
                                String chkData = rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK) + rsSub.getString(AR_TRX_NUM) + rsSub.getString(GRP_INV_NUM);
                                if (chkWornData(wornList, chkData)) {
                                    // create AR_RCPT_RCV_INFO_WRK
                                    createInfoWrk(rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK), rsSub.getString(AR_TRX_NUM), rsSub.getString(GRP_INV_NUM));
                                }
                            }
                            if (isEnd) {
                                break;
                            }
                            isNext = rsSub.next();
                        }

//                        if (isNext) {
//                            // START 2016/04/21 S.Fujita [QC#6616,MOD]
////                          BigDecimal rcptAmt = BigDecimal.ZERO;
////                          rcptAmt = rs.getBigDecimal(CUST_RCPT_AMT);
////                          BigDecimal rmngAmt = BigDecimal.ZERO;
////                          boolean isEnd = false;
//                            // END 2016/04/21 S.Fujita [QC#6616,MOD]
//                            // START  2018/08/10 H.Ikeda [QC#27662,MOD]
//                            if (BigDecimal.ZERO.compareTo(rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT)) != 0 ) {
//                                // START 2018/10/11 H.Ikeda [QC#28752,MOD]
//                                  if (chkAmtCrm(rcptAmt, rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT))){
//                                    // START 2018/08/22 H.Ikeda [QC#27776,ADD]
//                                    List<String> wornList = new ArrayList<String>();
//                                    // END   2018/08/22 H.Ikeda [QC#27776,ADD]
//                                    while (isNext) {
//                                        dtlCnt += 1;
//                                        // START 2016/04/21 S.Fujita [QC#6616,MOD]
////                                      if( rcptAmt.subtract(rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT)).compareTo(BigDecimal.ZERO) > 0) {
////                                          rcptAmt = rcptAmt.subtract(rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT));
////                                          rmngAmt = rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT);
////                                      } else {
////                                          rmngAmt = rcptAmt;
////                                          isEnd = true;
////                                      }
//                                        if (rcptAmt.subtract(rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT)).compareTo(BigDecimal.ZERO) > 0) {
//                                            rcptAmt = rcptAmt.subtract(rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT));
//                                            calRmngAmt = rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT);
//                                        } else {
//                                            calRmngAmt = rcptAmt;
//                                            isEnd = true;
//                                        }
//                                        // END 2016/04/21 S.Fujita [QC#6616,MOD] 
//
//                                        // create AR_RCPT_RCV_WRK
////                                      createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, rsSub.getString(AR_TRX_NUM), rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT), rsSub.getString(GRP_INV_NUM));
//                                        // START 2016/04/21 S.Fujita [QC#6616,MOD]
////                                      createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, rsSub.getString(AR_TRX_NUM), rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT), rsSub.getString(GRP_INV_NUM), true);
//                                        createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, rsSub.getString(AR_TRX_NUM), calRmngAmt, rsSub.getString(GRP_INV_NUM), true);
//                                        // END 2016/04/21 S.Fujita [QC#6616,MOD] 
//
//                                        // START  2018/08/10 H.Ikeda [QC#27662,ADD]
//////                                        trxBalUseList.add(rsSub.getString(AR_TRX_NUM));
//                                        // END    2018/08/10 H.Ikeda [QC#27662,ADD]
//
//                                        chkActAmt = chkActAmt.add(calRmngAmt); // 2018/08/02 T.Murai [QC#26249,ADD]
//
//                                        // update AR_RCPT_RCV_INTFC
//                                        if (!intfcUpFlg) {
//
//                                            updateIntfc(rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK), rsSub.getString(AR_TRX_NUM), rsSub.getString(GRP_INV_NUM));
//
//                                            intfcUpFlg = true;
//                                        }
//
//                                        // Cash Application Status check
//                                        if (!AR_CASH_APPLY_STS.PARTIAL.equals(rsSub.getString(AR_CASH_APPLY_STS_CD)) && !AR_CASH_APPLY_STS.UNAPPLIED.equals(rsSub.getString(AR_CASH_APPLY_STS_CD))) {
//                                            // START 2018/08/22 H.Ikeda [QC#27776,MOD]
//                                            String chkData = rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK) + rsSub.getString(AR_TRX_NUM) + rsSub.getString(GRP_INV_NUM);
//                                            if (chkWornData(wornList, chkData)) {
//                                                // create AR_RCPT_RCV_INFO_WRK
//                                                createInfoWrk(rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK), rsSub.getString(AR_TRX_NUM), rsSub.getString(GRP_INV_NUM));
//                                            }
//                                            // END   2018/08/22 H.Ikeda [QC#27776,MOD]
//                                        }
//
//                                        if (isEnd) {
//                                            break;
//                                        }
//
//                                        isNext = rsSub.next();
//                                    }
//                                  } else {
//                                    // no data
//                                    isGrpInv = false;
//                                }
//                                // END  2018/10/11 H.Ikeda [QC#28752,MOD]
//                              } else {
//                                // no data
//                                isGrpInv = false;
//                            }
//                            // END   2018/08/10 H.Ikeda [QC#27662,MOD]
//                        } else {
//                            // no data
//                            isGrpInv = false;
//                        }
                    // END    2018/09/17 H.Ikeda [QC#53535, MOD]
                    } else {
                        // no data
                        isGrpInv = false;
                    }
                } catch (SQLException sqlEx) {
                    rollback();
                    throw new S21AbendException(NFDM0003E, new String[] {sqlEx.getMessage() });
                } finally {
                    S21SsmLowLevelCodingClient.closeResource(stmtSub, rsSub);
                }

                boolean isTrxExist = true;

                if (!isGrpInv) {

                    // AR_TRX_BAL check with the invoice#
                    try {
                        if (hasValue(rs.getString(CUST_INV_NUM))) {
                            //START 2018/07/30 H.Ikeda [QC#27409,MOD]
                            //stmtSub = getTrxBalByInv(rs.getString(CUST_INV_NUM), relnCust);
                            // START 2018/11/30 H.Ikeda [QC#28839,MOD]
                            //END   2018/07/30 H.Ikeda [QC#27409,MOD]
                            if (hasValue(billToCustAcctCd)) {
                                stmtSub = getTrxBalByInv(rs.getString(CUST_INV_NUM), billToCustAcctCd);
                            } else {
                                stmtSub = getTrxBalByInv(rs.getString(CUST_INV_NUM), rcPayerCustCd);
                            }
                            // END   2018/11/30 H.Ikeda [QC#28839,MOD]
                            rsSub = stmtSub.executeQuery();

                            boolean isNext = rsSub.next();

                            if (isNext) {
                                // START  2018/08/10 H.Ikeda [QC#27662,MOD]
                                if (BigDecimal.ZERO.compareTo(rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT)) != 0 ) {
                                    // START 2018/10/11 H.Ikeda [QC#28752,MOD]
                                    if (chkAmtCrm(rcptAmt, rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT))){
                                        dtlCnt += 1;

                                        // create AR_RCPT_RCV_WRK
                                        //Def#5296
                                        //                            createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, rsSub.getString(AR_TRX_NUM), rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT), null);
//                                      createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, rsSub.getString(AR_TRX_NUM), rs.getBigDecimal(CUST_INV_AMT), null);

                                        // START 2016/04/21 S.Fujita [QC#6635,MOD]
                                        // START 2018/08/02 T.Murai [QC#26249,MOD]
                                        // calRmngAmt = getCalRmngAmt(rcptAmt, calRmngAmt, rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT));
                                        calRmngAmt = getCalRmngAmt(rcptAmt, calRmngAmt, rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT), rs.getBigDecimal(CUST_RCPT_AMT).subtract(chkActAmt));
                                        // END 2018/08/02 T.Murai [QC#26249,MOD]

                                        createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, rsSub.getString(AR_TRX_NUM), calRmngAmt, null, false);
//                                      createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, rsSub.getString(AR_TRX_NUM), rs.getBigDecimal(CUST_INV_AMT), null, false);
                                        // END 2016/04/21 S.Fujita [QC#6635,MOD]

                                        // START  2018/08/10 H.Ikeda [QC#27662,ADD]
////                                        trxBalUseList.add(rsSub.getString(AR_TRX_NUM));
                                        // END    2018/08/10 H.Ikeda [QC#27662,ADD]

                                        // update AR_RCPT_RCV_INTFC
                                        updateIntfc(rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK), rsSub.getString(AR_TRX_NUM), null);

                                        chkActAmt = chkActAmt.add(calRmngAmt); // 2018/08/02 T.Murai [QC#26249,ADD]
                                        // Cash Application Status check
                                        if (!AR_CASH_APPLY_STS.PARTIAL.equals(rsSub.getString(AR_CASH_APPLY_STS_CD)) && !AR_CASH_APPLY_STS.UNAPPLIED.equals(rsSub.getString(AR_CASH_APPLY_STS_CD))) {

                                            // create AR_RCPT_RCV_INFO_WRK
                                            createInfoWrk(rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK), rsSub.getString(AR_TRX_NUM), rsSub.getString(AR_TRX_NUM));
                                        }
                                    } else {
                                        // no data
                                        isTrxExist = false;
                                    }
                                    // END   2018/10/11 H.Ikeda [QC#28752,MOD]
                                } else {
                                    // no data
                                    isTrxExist = false;
                                }
                                // END  2018/08/10 H.Ikeda [QC#27662,MOD]
                            } else {
                                // no data
                                isTrxExist = false;
                            }
                        } else {
                            // no data
                            isTrxExist = false;
                        }

                    } catch (SQLException sqlEx) {
                        rollback();
                        throw new S21AbendException(NFDM0003E, new String[] {sqlEx.getMessage() });
                    } finally {
                        S21SsmLowLevelCodingClient.closeResource(stmtSub, rsSub);
                    }
                }

                
                boolean isExist = true;
                if (!isTrxExist) {
                    try { // START 2018/06/21 H.Ikeda [QC#25731,MOD]
                        // AR_TRX_BAL check with by source document number
                        if (hasValue(rs.getString(CUST_INV_NUM))) {
                            //START 2018/07/30 H.Ikeda [QC#27409,MOD]
                            //stmtSub = getTrxBalBySrcSysDoc(rs.getString(CUST_INV_NUM), relnCust);
                            // START 2018/11/30 H.Ikeda [QC#28839,MOD]
                            //END   2018/07/30 H.Ikeda [QC#27409,MOD]
                            if (hasValue(billToCustAcctCd)) {
                                stmtSub = getTrxBalBySrcSysDoc(rs.getString(CUST_INV_NUM), billToCustAcctCd);
                            } else {
                                stmtSub = getTrxBalBySrcSysDoc(rs.getString(CUST_INV_NUM), rcPayerCustCd);
                            }
                            // END   2018/11/30 H.Ikeda [QC#28839,MOD]
                            rsSub = stmtSub.executeQuery();

                            boolean isNext = rsSub.next();

                            if (isNext) {
                                // START  2018/08/10 H.Ikeda [QC#27662,MOD]
                                if (BigDecimal.ZERO.compareTo(rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT)) != 0 ) {
                                    // START 2018/10/11 H.Ikeda [QC#28752,MOD]
                                    if (chkAmtCrm(rcptAmt, rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT))){
                                        dtlCnt += 1;

                                        // create AR_RCPT_RCV_WRK
                                        // set rs.getString(CUST_INV_NUM) to group invoice number so that customer invoice number is set to invoice ogirinal number.
//                                        createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, rsSub.getString(AR_TRX_NUM), rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT), rs.getString(CUST_INV_NUM));

                                        // START 2016/04/21 S.Fujita [QC#6635,MOD]
                                        // START 2018/08/04 T.Murai  [QC#26249,MOD]
                                        // calRmngAmt = getCalRmngAmt(rcptAmt, calRmngAmt, rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT));
                                        calRmngAmt = getCalRmngAmt(rcptAmt, calRmngAmt, rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT), rs.getBigDecimal(CUST_RCPT_AMT).subtract(chkActAmt));
                                        // END 2018/08/04 T.Murai  [QC#26249,MOD]
                                        createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, rsSub.getString(AR_TRX_NUM), calRmngAmt, rs.getString(CUST_INV_NUM), false);
//                                      createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, rsSub.getString(AR_TRX_NUM), rsSub.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT), rs.getString(CUST_INV_NUM), false);
                                        // END 2016/04/21 S.Fujita [QC#6635,MOD]

                                        // START  2018/08/10 H.Ikeda [QC#27662,ADD]
////                                        trxBalUseList.add(rsSub.getString(AR_TRX_NUM));
                                        // END    2018/08/10 H.Ikeda [QC#27662,ADD]

                                        // update AR_RCPT_RCV_INTFC
                                        updateIntfc(rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK), rsSub.getString(AR_TRX_NUM), null);

                                        chkActAmt = chkActAmt.add(calRmngAmt); // 2018/08/02 T.Murai [QC#26249,ADD]
                                        // Cash Application Status check
                                        if (!AR_CASH_APPLY_STS.PARTIAL.equals(rsSub.getString(AR_CASH_APPLY_STS_CD)) && !AR_CASH_APPLY_STS.UNAPPLIED.equals(rsSub.getString(AR_CASH_APPLY_STS_CD))) {

                                            // create AR_RCPT_RCV_INFO_WRK
                                            createInfoWrk(rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK), rsSub.getString(AR_TRX_NUM), rsSub.getString(AR_TRX_NUM));
                                        }
                                    } else {
                                     // no data
                                        isExist = false;
                                    }
                                    // END   2018/10/11 H.Ikeda [QC#28752,MOD]
                                } else {
                                    // no data
                                    isExist = false;
                                }
                                // END  2018/08/10 H.Ikeda [QC#27662,MOD]
                            } else {
                                // no data
                                isExist = false;
                                
                            }
                        } else {
                            // no data
                            isExist = false;
                        }
                        // END 2018/05/10 E.Kameishi [QC#25721,MOD]
                        // START 2018/04/18 E.Kameishi [QC#21037,MOD]
                        if (!isExist) {
                            // AR_TRX_BAL check with due date
                            // START 2018/05/10 E.Kameishi [QC#26054,MOD]
                            BigDecimal tmpRcptAmt = rcptAmt;
                            // Start 2018/09/03 H.Ikeda [QC#28062,MOD]
                            if (BigDecimal.ZERO.compareTo(rs.getBigDecimal(CUST_INV_AMT)) > 0) {
                                if (trxbalCrmList.size() > 0) {
                                    int trxCnt = 0;
                                    List<String> wornList = new ArrayList<String>();
                                    for (int i = 0; i <trxbalCrmList.size(); i++) {
                                        if (chkUsedTrxBalData((String) trxbalCrmList.get(i).get(AR_TRX_NUM), trxBalUseList)) {
                                            dtlCnt += 1;
                                            trxCnt += 1;
                                            BigDecimal rmngBalGrsAmt = (BigDecimal) trxbalCrmList.get(i).get(DEAL_RMNG_BAL_GRS_AMT);
                                            calRmngAmt = getCalRmngAmt(tmpRcptAmt, calRmngAmt, rmngBalGrsAmt, rs.getBigDecimal(CUST_RCPT_AMT).subtract(chkActAmt));
                                            // create ArRcptRcvWrk
                                            createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, (String) trxbalCrmList.get(i).get(AR_TRX_NUM), calRmngAmt, rs.getString(CUST_INV_NUM), false);
                                            chkActAmt = chkActAmt.add(calRmngAmt);
                                            // update AR_RCPT_RCV_INTFC
                                            updateIntfc(rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK), (String) trxbalCrmList.get(i).get(AR_TRX_NUM), null);
                                            BigDecimal subAmt = rmngBalGrsAmt.subtract(calRmngAmt);
                                            if (subAmt.compareTo(BigDecimal.ZERO) >= 0) {
                                                trxbalCrmList.get(i).put(DEAL_RMNG_BAL_GRS_AMT, BigDecimal.ZERO);
                                            } else {
                                                trxbalCrmList.get(i).put(DEAL_RMNG_BAL_GRS_AMT, subAmt);
                                            }
                                            tmpRcptAmt = tmpRcptAmt.subtract(calRmngAmt);

                                            // Cash Application Status check
                                            if (!AR_CASH_APPLY_STS.PARTIAL.equals((String) trxbalCrmList.get(i).get(AR_CASH_APPLY_STS_CD)) && !AR_CASH_APPLY_STS.UNAPPLIED.equals((String) trxbalCrmList.get(i).get(AR_CASH_APPLY_STS_CD))) {
                                                // START 2018/08/22 H.Ikeda [QC#27776,MOD]
                                                String chkData = rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK) + (String) trxbalCrmList.get(i).get(AR_TRX_NUM);
                                                if (chkWornData(wornList, chkData)) {
                                                    // create AR_RCPT_RCV_INFO_WRK
                                                    createInfoWrk(rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK), (String) trxbalCrmList.get(i).get(AR_TRX_NUM), (String) trxbalCrmList.get(i).get(AR_TRX_NUM));
                                                }
                                                // END   2018/08/22 H.Ikeda [QC#27776,MOD]
                                            }
                                            if (tmpRcptAmt.compareTo(BigDecimal.ZERO) <= 0) {
                                                break;
                                            }
                                        } 
                                    }
                                    if (trxCnt == 0) {
                                        // no data
                                        dtlCnt += 1;
                                        noDataCreate(dtlCnt, rcvSqPk, hdrCnt,  rs);
                                    }
                                    Iterator<Map<String, Object>> it = trxbalCrmList.iterator();
                                    while (it.hasNext()) {
                                         Map<String, Object> tmpMap = it.next();
                                         BigDecimal tmpGrsAmt = (BigDecimal) tmpMap.get(DEAL_RMNG_BAL_GRS_AMT);
                                         if (tmpGrsAmt.compareTo(BigDecimal.ZERO) == 0) {
                                             // Start 2018/09/03 H.Ikeda [QC#28062,ADD]
                                             trxBalUseList.add((String)tmpMap.get(AR_TRX_NUM));
                                             // End   2018/09/03 H.Ikeda [QC#28062,ADD]
                                             it.remove();
                                        }
                                    }
                                } else {
                                    // no data
                                    dtlCnt += 1;
                                    noDataCreate(dtlCnt, rcvSqPk, hdrCnt,  rs);
                                }
                            } else {
                                if (trxbalList.size() > 0) {
                                    int trxCnt = 0;
                                    // START 2018/08/22 H.Ikeda [QC#27776,ADD]
                                    List<String> wornList = new ArrayList<String>();
                                    // END   2018/08/22 H.Ikeda [QC#27776,ADD]
                                    for (int i = 0; i < trxbalList.size(); i++) {
                                        // START  2018/08/10 H.Ikeda [QC#27662,MOD]
                                        if (chkUsedTrxBalData((String) trxbalList.get(i).get(AR_TRX_NUM), trxBalUseList)) {
                                            // END    2018/08/10 H.Ikeda [QC#27662,MOD]
                                            BigDecimal rmngBalGrsAmt = (BigDecimal) trxbalList.get(i).get(DEAL_RMNG_BAL_GRS_AMT);
                                            if (rmngBalGrsAmt.compareTo(BigDecimal.ZERO) > 0) {
                                                dtlCnt += 1;
                                                trxCnt += 1;
                                                // START 2018/08/04 T.Murai  [QC#26249,MOD]
                                                // calRmngAmt = getCalRmngAmt(tmpRcptAmt, calRmngAmt, rmngBalGrsAmt);
                                                calRmngAmt = getCalRmngAmt(tmpRcptAmt, calRmngAmt, rmngBalGrsAmt, rs.getBigDecimal(CUST_RCPT_AMT).subtract(chkActAmt));
                                                // START 2018/08/04 T.Murai  [QC#26249,MOD]

                                                createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, (String) trxbalList.get(i).get(AR_TRX_NUM), calRmngAmt, rs.getString(CUST_INV_NUM), false);

                                                chkActAmt = chkActAmt.add(calRmngAmt); // 2018/08/02 T.Murai [QC#26249,ADD]
                                                // update AR_RCPT_RCV_INTFC
                                                updateIntfc(rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK), (String) trxbalList.get(i).get(AR_TRX_NUM), null);

                                                BigDecimal subAmt = rmngBalGrsAmt.subtract(calRmngAmt);
                                                if (rmngBalGrsAmt.subtract(calRmngAmt).compareTo(BigDecimal.ZERO) > 0) {
                                                    trxbalList.get(i).put(DEAL_RMNG_BAL_GRS_AMT, subAmt);
                                                } else {
                                                    trxbalList.get(i).put(DEAL_RMNG_BAL_GRS_AMT, BigDecimal.ZERO);
                                                }
                                                tmpRcptAmt = tmpRcptAmt.subtract(calRmngAmt);

                                                // Cash Application Status check
                                                if (!AR_CASH_APPLY_STS.PARTIAL.equals((String) trxbalList.get(i).get(AR_CASH_APPLY_STS_CD)) && !AR_CASH_APPLY_STS.UNAPPLIED.equals((String) trxbalList.get(i).get(AR_CASH_APPLY_STS_CD))) {
                                                    // START 2018/08/22 H.Ikeda [QC#27776,MOD]
                                                    String chkData = rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK) + (String) trxbalList.get(i).get(AR_TRX_NUM);
                                                    if (chkWornData(wornList, chkData)) {
                                                        // create AR_RCPT_RCV_INFO_WRK
                                                        createInfoWrk(rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK), (String) trxbalList.get(i).get(AR_TRX_NUM), (String) trxbalList.get(i).get(AR_TRX_NUM));
                                                    }
                                                    // END   2018/08/22 H.Ikeda [QC#27776,MOD]
                                                }
                                                if (tmpRcptAmt.compareTo(BigDecimal.ZERO) <= 0) {
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    if (trxCnt == 0) {
                                        // no data
                                        dtlCnt += 1;
                                        noDataCreate(dtlCnt, rcvSqPk, hdrCnt,  rs);
                                    }
                                    Iterator<Map<String, Object>> it = trxbalList.iterator();
                                    while (it.hasNext()) {
                                        Map<String, Object> tmpMap = it.next();
                                        BigDecimal tmpGrsAmt = (BigDecimal) tmpMap.get(DEAL_RMNG_BAL_GRS_AMT);
                                        if (tmpGrsAmt.compareTo(BigDecimal.ZERO) == 0) {
                                            // Start 2018/09/03 H.Ikeda [QC#28062,ADD]
                                            trxBalUseList.add((String)tmpMap.get(AR_TRX_NUM));
                                            // End   2018/09/03 H.Ikeda [QC#28062,ADD]
                                            it.remove();
                                        }
                                    }
                                } else {
                                    // no data
                                    dtlCnt += 1;
                                    noDataCreate(dtlCnt, rcvSqPk, hdrCnt,  rs);
                                }
                                // END 2018/04/18 E.Kameishi [QC#21037,MOD]
                                // END 2018/05/10 E.Kameishi [QC#26054,MOD]
                            }
                            // End 2018/09/03 H.Ikeda [QC#28062,MOD]
                        }
                        // Start 2018/09/10 H.Ikeda [QC#28057,ADD]
                        if (hasValue(rcPayerCustCd)) {
                            if (trxbalListMap.get(rcPayerCustCd) == null && !trxbalList.isEmpty()) {
                                trxbalListMap.put(rcPayerCustCd, trxbalList);
                            }
                            if (trxbalCrmListMap.get(rcPayerCustCd) == null && !trxbalCrmList.isEmpty()) {
                                trxbalCrmListMap.put(rcPayerCustCd, trxbalCrmList);
                            }
                        }
                        // End   2018/09/10 H.Ikeda [QC#28057,ADD]
                    } catch (SQLException sqlEx) {
                        rollback();
                        throw new S21AbendException(NFDM0003E, new String[] {sqlEx.getMessage() });
                    } finally {
                        S21SsmLowLevelCodingClient.closeResource(stmtSub, rsSub);
                    }
                    // END   2018/06/21 H.Ikeda [QC#25731,MOD]
                }
            } // loop

            rs.previous();
            // START 2017/09/05 T.Tsuchida [QC#20939,MOD]
//            if (hdrCnt > 0 && dtlCnt > 0 && AR_LOCK_BOX.CFS_LOCKBOX.equals(rs.getString(AR_LOCK_BOX_CD))) {
            if (hdrCnt > 0 && dtlCnt > 0 && NFCI1050.equals(rs.getString(RCV_FUNC_ID))) {
            // END 2017/09/05 T.Tsuchida [QC#20939,MOD]
                updateRcvWrk(rcvSqPk, hdrCnt, chkAmt);
            }

            // process remaining data
            if (listTmsg.size() != 0) {
                submitTmsgs(true);
            }

            if (listInfoTmsg.size() != 0) {
                submitInfoTmsgs(true);
            }

            if (listIntfcTmsg.size() != 0) {
                submitIntfcTmsgs(true);
            }

            // START 2018/11/07 H.Ikeda [QC#29293,DEL]
            // complete
            //commit();
            // END   2018/11/07 H.Ikeda [QC#29293,DEL]
            
            // START 2018/11/07 H.Ikeda [QC#28062,ADD]
            chkSameInvData();
            // END   2018/11/07 H.Ikeda [QC#28062,ADD]

            // START 2018/10/24 H.Ikeda [QC#28815,ADD]
            chkAmtData();
            commit();
            // END   2018/10/24 H.Ikeda [QC#28815,ADD]
        } catch (SQLException sqlEx) {
            rollback();
            throw new S21AbendException(NFDM0003E, new String[] {sqlEx.getMessage() });
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }

    }

    // START 2018/11/07 H.Ikeda [QC#28062,ADD]
    /**
     * chkSameInvData
     * 
     * @throws SQLException
     */
    private void chkSameInvData() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = getSameChkData();
            rs = stmt.executeQuery();
            BigDecimal rcvSqPk   = BigDecimal.ZERO;
            String rcvHdrNum = "";
            String rcvDtlNum = "";
            while (rs.next()) {
                if (rcvSqPk.compareTo(rs.getBigDecimal(RCV_SQ_PK)) == 0 && rcvHdrNum.equals(rs.getString(RCV_HDR_NUM))) {
                    // noThing
                } else {
                    rcvSqPk = rs.getBigDecimal(RCV_SQ_PK);
                    rcvHdrNum = rs.getString(RCV_HDR_NUM);
                    rcvDtlNum = getMaxRcvDtlNum (rcvSqPk, rcvHdrNum);
                }

                rcvDtlNum = createRcvDtlNum(rcvDtlNum);
                // join
                String inv30Num = rs.getString(INV_30_NUM);
                chkSameInvData(rcvSqPk, rcvHdrNum, inv30Num, rcvDtlNum);
            }
        } catch (SQLException sqlEx) {
            rollback();
            throw sqlEx;
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * getMaxRcvDtlNum
     * 
     * @param rcvSqPk   BigDecimal
     * @param rcvHdrNum String
     * @return String
     */
    private String getMaxRcvDtlNum (BigDecimal rcvSqPk, String rcvHdrNum) {
        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("rcvSqPk", rcvSqPk);
        sqlParam.put("rcvHdrNum", rcvHdrNum);
        return (String) ssmBatchClient.queryObject("getMaxRcvDtlNum", sqlParam);
    }

    /**
     * chkSameInvData
     * 
     * @param rcvSqPk       BigDecimal
     * @param rcvHdrNum     String
     * @param invOrgNum     String
     * @param maxRcvDtlNum  String
     * @throws SQLException
     */
    private void chkSameInvData(BigDecimal rcvSqPk, String rcvHdrNum, String invOrgNum, String maxRcvDtlNum) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        BigDecimal addChkRcptTotAmt = BigDecimal.ZERO;
        BigDecimal addInvOrigTotAmt = BigDecimal.ZERO;
        List<String> dtlList = new ArrayList<String>();
        try {
            stmt = getSameArRcptRcvWrk(rcvSqPk, rcvHdrNum);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String inv30Num = rs.getString(INV_30_NUM);
                if (invOrgNum.equals(inv30Num)) {
                    String rcvDtlNum = rs.getString(RCV_DTL_NUM);
                    dtlList.add(rcvDtlNum);
                    BigDecimal chkRcptTotAmt = rs.getBigDecimal(CHK_RCPT_TOT_AMT);
                    BigDecimal invOrigTotAmt = rs.getBigDecimal(INV_ORIG_TOT_AMT);

                    addChkRcptTotAmt = addChkRcptTotAmt.add(chkRcptTotAmt);
                    addInvOrigTotAmt = addInvOrigTotAmt.add(invOrigTotAmt);
                }
            }
            // amt check
            addInvOrigTotAmt = chkInvOrigTotAmt(invOrgNum, addInvOrigTotAmt);
            // insert
            insertArRcptRcvWrk(rcvSqPk, rcvHdrNum, dtlList.get(0), invOrgNum, maxRcvDtlNum, addChkRcptTotAmt, addInvOrigTotAmt);
            // remove
            for (int i = 0; i < dtlList.size(); i++) {
                AR_RCPT_RCV_WRKTMsg tmsg = new AR_RCPT_RCV_WRKTMsg();
                setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
                setValue(tmsg.rcvSqPk, rcvSqPk);
                setValue(tmsg.rcvHdrNum, rcvHdrNum);
                setValue(tmsg.rcvDtlNum, dtlList.get(i));
                EZDTBLAccessor.logicalRemove(tmsg);
            }
        } catch (SQLException sqlEx) {
            throw sqlEx;
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * chkInvOrigTotAmt
     * 
     * @param invOrgNum        String
     * @param addInvOrigTotAmt BigDecimal
     * @return BigDecimal
     */
    private BigDecimal chkInvOrigTotAmt(String invOrgNum, BigDecimal addInvOrigTotAmt) {

        BigDecimal rmngBalance = getRmngBalance(invOrgNum);

        if (rmngBalance.compareTo(addInvOrigTotAmt) >= 0) {
            return addInvOrigTotAmt;
        } else {
            return rmngBalance;
        }
    }

    /**
     * getRmngBalance
     * 
     * @param arTrxNum   String
     * @return BigDecimal
     */
    private BigDecimal getRmngBalance(String arTrxNum) {

        AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("arTrxNum01", arTrxNum);

        AR_TRX_BALTMsgArray outMsg = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsg != null && outMsg.length() != 0) {
            String arCashApplyStsCd = outMsg.no(0).arCashApplyStsCd.getValue();
            if (AR_CASH_APPLY_STS.PARTIAL.equals(arCashApplyStsCd) || AR_CASH_APPLY_STS.UNAPPLIED.equals(arCashApplyStsCd)) {
                return outMsg.no(0).dealRmngBalGrsAmt.getValue();
            }
        }
        return BigDecimal.ZERO;
    }

    /**
     * insertArRcptRcvWrk
     * 
     * @param rcvSqPk          BigDecimal
     * @param rcvHdrNum        String
     * @param setRcvDtlNum     String
     * @param invOrgNum        String
     * @param maxRcvDtlNum     String
     * @param addChkRcptTotAmt BigDecimal
     * @param addInvOrigTotAmt BigDecimal
     */
    private void insertArRcptRcvWrk(BigDecimal rcvSqPk, String rcvHdrNum, String setRcvDtlNum, String invOrgNum, String maxRcvDtlNum, BigDecimal addChkRcptTotAmt, BigDecimal addInvOrigTotAmt) {
        AR_RCPT_RCV_WRKTMsg tmsg = new AR_RCPT_RCV_WRKTMsg();
        setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tmsg.rcvSqPk, rcvSqPk);
        setValue(tmsg.rcvHdrNum, rcvHdrNum);
        setValue(tmsg.rcvDtlNum, setRcvDtlNum);

        tmsg = (AR_RCPT_RCV_WRKTMsg) S21FastTBLAccessor.findByKey(tmsg);

        setValue(tmsg.rcvDtlNum, maxRcvDtlNum);
        setValue(tmsg.chkRcptTotAmt, addChkRcptTotAmt);
        setValue(tmsg.invOrigTotAmt, addInvOrigTotAmt);
        setValue(tmsg.inv30Num, invOrgNum);
        setValue(tmsg.custInvNum, invOrgNum);

        S21FastTBLAccessor.insert(tmsg);
    }

    /**
     * createRcvDtlNum
     * 
     * @param maxRcvDtlNum String
     * @return String
     */
    private String createRcvDtlNum(String maxRcvDtlNum) {
        String x = maxRcvDtlNum.replaceAll("^0*", "");
        return zeroPadding(String.valueOf(Integer.parseInt(x) + 1), 4);
    }


     /**
      * zeroPadding
      * 
      * @param input  String
      * @param length int
      * @return String
      */
    private String zeroPadding(String input, int length){
         return String.format("%" + length + "s", input).replace(" ", "0");
     }

//    /**
//     * joinInvData
//     * 
//     * @param rcvSqPk   BigDecimal
//     * @param rcvHdrNum String
//     * @param rcvDtlNum String
//     * @throws SQLException
//     */
//    private void joinInvData(BigDecimal rcvSqPk, String rcvHdrNum, String rcvDtlNum) throws SQLException {
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        try {
//            stmt = getSameInvData(rcvSqPk, rcvHdrNum);
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                String inv30OrgNum = rs.getString("INV_30_NUM");
//                chkSameInvData(rcvSqPk, rcvHdrNum, inv30OrgNum, rcvDtlNum);
//            }
//        } catch (SQLException sqlEx) {
//            throw sqlEx;
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
//        }
//    }

    /**
     * getSameArRcptRcvWrk
     * 
     * @param rcvSqPk   BigDecimal
     * @param rcvHdrNum String
     * @return PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement getSameArRcptRcvWrk(BigDecimal rcvSqPk, String rcvHdrNum) throws SQLException {

        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("rcvSqPk", rcvSqPk);
        sqlParam.put("rcvHdrNum", rcvHdrNum);

        return this.ssmLLClient.createPreparedStatement("getSameArRcptRcvWrk", sqlParam);
    }

    /**
     * getSameInvData
     * 
     * @param rcvSqPk   BigDecimal
     * @param rcvHdrNum String
     * @return PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement getSameInvData(BigDecimal rcvSqPk, String rcvHdrNum) throws SQLException {

        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("rcvStsCd", NFCConst.CST_RCV_STS_CD_UNPROC);
        sqlParam.put("rcvSqPk", rcvSqPk);
        sqlParam.put("rcvHdrNum", rcvHdrNum);

        return this.ssmLLClient.createPreparedStatement("getSameInvData", sqlParam);
    }

    /**
     * getSameChkData
     * 
     * @return PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement getSameChkData() throws SQLException {

        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("rcvStsCd", NFCConst.CST_RCV_STS_CD_UNPROC);

        return this.ssmLLClient.createPreparedStatement("getSameChkData", sqlParam);
    }
    // END   2018/11/07 H.Ikeda [QC#28062,ADD]

    // START 2018/10/24 H.Ikeda [QC#28815,ADD]
    /**
     * chkAmtData
     * @throws SQLException 
     */
    private void chkAmtData() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ResultSet rss = null;
        try {
            stmt = getArRcptRcvWrk();
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (BigDecimal.ZERO.compareTo(rs.getBigDecimal(RSLT)) == 0) {
                    EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, "=== " + "No Error" + " ===", this);
                } else {
                    BigDecimal chkAmt = rs.getBigDecimal(CHK_AMT);
                    try {
                        stmt = getArRcptRcvWrkData(rs);
                        rss = stmt.executeQuery();
                        if (BigDecimal.ONE.compareTo(rs.getBigDecimal(RSLT)) == 0) {
                            chkCashApply(rss, chkAmt, rs.getBigDecimal(SUM_CHK_RCPT_TOT_AMT));
                        } else {
                            chkNotCashApply(rss, chkAmt);
                        }
                    } catch (SQLException e) {
                        throw e;
                    } finally {
                        S21SsmLowLevelCodingClient.closeResource(stmt, rss);
                    }
                }
            }
        } catch (SQLException sqlEx) {
            rollback();
            throw sqlEx;
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * chkCashApply
     * 
     * @param rs               ResultSet
     * @param chkAmt           BigDecimal
     * @param sumChkRcptTotAmt BigDecimal
     * @throws SQLException
     */
    private void chkCashApply(ResultSet rs, BigDecimal chkAmt, BigDecimal sumChkRcptTotAmt) throws SQLException {
        BigDecimal setAmt = sumChkRcptTotAmt.subtract(chkAmt);
        while (rs.next()) {
            BigDecimal chkRcptTotAmt = rs.getBigDecimal(CHK_RCPT_TOT_AMT);
            if (BigDecimal.ZERO.compareTo(chkRcptTotAmt) < 0) {
                if (chkRcptTotAmt.compareTo(setAmt) >= 0) {
                    updateArRcptRcvWrk(rs, chkRcptTotAmt.subtract(setAmt));
                    break;
                } else {
                    setAmt = setAmt.subtract(chkRcptTotAmt);
                    updateArRcptRcvWrk(rs, BigDecimal.ZERO);
                }
            }
        }
    }

    /**
     * chkNotCashApply
     * 
     * @param rs     ResultSet
     * @param chkAmt BigDecimal
     * @throws SQLException
     */
    private void chkNotCashApply(ResultSet rs, BigDecimal chkAmt) throws SQLException {
        BigDecimal setPlusAmt = BigDecimal.ZERO;
        BigDecimal setMinusAmt = BigDecimal.ZERO;
        while (rs.next()) {
            BigDecimal chkRcptTotAmt = rs.getBigDecimal(CHK_RCPT_TOT_AMT);
            if (BigDecimal.ZERO.compareTo(chkRcptTotAmt) <= 0) {
                setPlusAmt = setPlusAmt.add(chkRcptTotAmt);
            } else {
                setMinusAmt = setMinusAmt.add(chkRcptTotAmt);
            }
        }

        // START 2019/09/25 H.Ikeda [QC#53535, ADD]
        if (setMinusAmt.negate().compareTo(setPlusAmt) > 0) {

            if (chkAmt.compareTo(setPlusAmt) > 0) {
                rs.beforeFirst();
                while (rs.next()) {
                    BigDecimal chkRcptTotAmt = rs.getBigDecimal(CHK_RCPT_TOT_AMT);
                    if (BigDecimal.ZERO.compareTo(chkRcptTotAmt) > 0) {
                        updateArRcptRcvWrk(rs, BigDecimal.ZERO);
                    }
                }
                return;
            } else {
                BigDecimal cAmt = setPlusAmt.subtract(chkAmt);
                BigDecimal mnsCAmt = cAmt;
                BigDecimal plsCAmt = cAmt;
                rs.beforeFirst();
                while (rs.next()) {
                    BigDecimal chkRcptTotAmt = rs.getBigDecimal(CHK_RCPT_TOT_AMT);
                    if (BigDecimal.ZERO.compareTo(chkRcptTotAmt) > 0) {
                        if (BigDecimal.ZERO.compareTo(mnsCAmt) != 0) {
                            if (chkRcptTotAmt.negate().compareTo(mnsCAmt) > 0) {
                                mnsCAmt = chkRcptTotAmt.add(chkRcptTotAmt.negate().subtract(mnsCAmt));
                                updateArRcptRcvWrk(rs, mnsCAmt);
                                if (cAmt.subtract(mnsCAmt.negate()).compareTo(BigDecimal.ZERO) == 0) {
                                    mnsCAmt = BigDecimal.ZERO;
                                } else {
                                    mnsCAmt = cAmt.subtract(mnsCAmt.negate());
                                }
                            }
                            // START 2020/03/09 H.Ikeda [QC#56146,ADD]
                            else {
                                mnsCAmt = mnsCAmt.subtract(chkRcptTotAmt.negate());
                            }
                            // END   2020/03/09 H.Ikeda [QC#56146,ADD]
                        } else {
                            updateArRcptRcvWrk(rs, mnsCAmt);
                        }
                    } else {
                        if (BigDecimal.ZERO.compareTo(plsCAmt) != 0) {
                            updateArRcptRcvWrk(rs, chkRcptTotAmt);
                            if (chkRcptTotAmt.compareTo(plsCAmt) > 0) {
                                plsCAmt = BigDecimal.ZERO;
                            } else {
                                plsCAmt = plsCAmt.subtract(chkRcptTotAmt);
                            }
                        }
                    }
                }
                return;
            }
        }
        // END 2019/09/25 H.Ikeda [QC#53535, ADD]

        rs.beforeFirst();

        if (setPlusAmt.compareTo(BigDecimal.ZERO) == 0) {
            while (rs.next()) {
                BigDecimal chkRcptTotAmt = rs.getBigDecimal(CHK_RCPT_TOT_AMT);
                if (BigDecimal.ZERO.compareTo(chkRcptTotAmt) > 0) {
                    updateArRcptRcvWrk(rs, BigDecimal.ZERO);
                }
            }
        } else {
            boolean setFlg = true;
            while (rs.next()) {
                BigDecimal chkRcptTotAmt = rs.getBigDecimal(CHK_RCPT_TOT_AMT);
                if (BigDecimal.ZERO.compareTo(chkRcptTotAmt) > 0) {
                    updateArRcptRcvWrk(rs, BigDecimal.ZERO);
                } else {
                    if (setFlg) {
                        if (BigDecimal.ZERO.compareTo(chkRcptTotAmt) == 0) {
                            EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, "=== " + "Amt 0 Data" + " ===", this);
                        } else {
                            setMinusAmt = setMinusAmt.add(chkRcptTotAmt);
                            if (BigDecimal.ZERO.compareTo(setMinusAmt) >= 0) {
                                updateArRcptRcvWrk(rs, BigDecimal.ZERO);
                            } else {
                                setFlg = false;
                                updateArRcptRcvWrk(rs, setMinusAmt);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * updateArRcptRcvWrk
     * 
     * @param rs     ResultSet
     * @param setAmt BigDecimal
     * @throws SQLException
     */
    private void updateArRcptRcvWrk(ResultSet rs, BigDecimal setAmt) throws SQLException {
        AR_RCPT_RCV_WRKTMsg tmsg = new AR_RCPT_RCV_WRKTMsg();

        setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tmsg.rcvSqPk, rs.getBigDecimal(RCV_SQ_PK));
        setValue(tmsg.rcvHdrNum, rs.getString(RCV_HDR_NUM));
        setValue(tmsg.rcvDtlNum, rs.getString(RCV_DTL_NUM));

        // START 2018/11/20 H.Ikeda [QC#29293,MOD]
        //tmsg = (AR_RCPT_RCV_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tmsg);
        tmsg = (AR_RCPT_RCV_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);
        // END   2018/11/20 H.Ikeda [QC#29293,MOD]

    //    setValue(tmsg.chkRcptTotAmt, setAmt);
        setValue(tmsg.invOrigTotAmt, setAmt);

        S21FastTBLAccessor.update(tmsg);
    }

    /**
     * getArRcptRcvWrkData
     * 
     * @param  rs ResultSet
     * @return PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement getArRcptRcvWrkData(ResultSet rs) throws SQLException {

        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("rcvSqPk", rs.getBigDecimal(RCV_SQ_PK));
        sqlParam.put("rcvHdrNum", rs.getString(RCV_HDR_NUM));

        return this.ssmLLClient.createPreparedStatement("getArRcptRcvWrkData", sqlParam);
    }

    /**
     * getArRcptRcvWrk
     * 
     * @return PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement getArRcptRcvWrk() throws SQLException {

        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("rcvStsCd", NFCConst.CST_RCV_STS_CD_UNPROC);

        return this.ssmLLClient.createPreparedStatement("getArRcptRcvWrk", sqlParam);
    }
    // END   2018/10/24 H.Ikeda [QC#28815,ADD]

    // START 2018/10/11 H.Ikeda [QC#28752,ADD]
    /**
     * chkAmtCrm
     * 
     * @param custInvAmt
     * @param dealRmngBalGrsAmt
     * @return boolean
     */
    private boolean chkAmtCrm(BigDecimal custInvAmt, BigDecimal dealRmngBalGrsAmt) {
        if (BigDecimal.ZERO.compareTo(custInvAmt) == 0 && BigDecimal.ZERO.compareTo(dealRmngBalGrsAmt) > 0) {
            return false;
        }

        // START 2019/01/21 H.Ikeda [QC#29995,ADD]
        if (BigDecimal.ZERO.compareTo(custInvAmt) > 0 && BigDecimal.ZERO.compareTo(dealRmngBalGrsAmt) < 0) {
            return false;
        }
        // END   2019/01/21 H.Ikeda [QC#29995,ADD]

        return true;
    }
    // END   2018/10/11 H.Ikeda [QC#28752,ADD]

    // Start 2018/09/03 H.Ikeda [QC#28062,ADD]
    private void noDataCreate(int dtlCnt, BigDecimal rcvSqPk, int hdrCnt, ResultSet rs) throws SQLException {
        // START 2018/07/02 H.Ikeda [QC#20939,MOD]
        createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, rs.getString(CUST_INV_NUM), null, null, false);
        //createArRcptRcvWrk(rcvSqPk, hdrCnt, dtlCnt, rs, rs.getString(CUST_INV_NUM), rcptAmt, null, false);
        // END   2018/07/02 H.Ikeda [QC#25736,MOD]

        // create AR_RCPT_RCV_INFO_WRK
        createInfoWrk(rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK), rs.getString(CUST_INV_NUM), null);

        updateIntfc(rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK), null, null);
    }
    // End   2018/09/03 H.Ikeda [QC#28062,ADD]
    // START 2018/08/22 H.Ikeda [QC#27776,MOD]
    /**
     * Check WornList
     * 
     * @param wornList List<String>
     * @param chkData  String
     * @return boolean
     */
    private boolean chkWornData(List<String> wornList, String chkData) {
        if (wornList.contains(chkData)) {
            return false;
        } else {
            wornList.add(chkData);
            return true;
        }
    }

    // START  2018/08/10 H.Ikeda [QC#27662,ADD]
    /**
     * chkUsedTrxBalData
     * 
     * @param arTrxNum      String
     * @param trxBalUseList List<String>
     * @return boolean
     */
    private boolean chkUsedTrxBalData(String arTrxNum, List<String> trxBalUseList) {
        for (int i = 0; i < trxBalUseList.size(); i++) {
            if (arTrxNum.equals(trxBalUseList.get(i))) {
                return false;
            }
        }
        return true;
    }
    // END   2018/08/10 H.Ikeda [QC#27662,ADD]
    
//    private PreparedStatement getTargetData() throws SQLException {
//        // START 2018/07/04 H.Ikeda [QC#25731,MOD]
//        List<String> lineList = new ArrayList<String>();
//        
//        Map<String, Object> sqlParam = new HashMap<String, Object>();
//        //Map<String, String> sqlParam = new HashMap<String, String>();
//        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
//        sqlParam.put("flgY", FLG_ON_Y);
//        sqlParam.put("flgN", FLG_OFF_N);
//        sqlParam.put("inComp", AR_CUST_ID_STS.SAVED);
//
//        if (ZYPCommonFunc.hasValue(this.multipleCnt)) {
//            PreparedStatement stmt = null;
//            ResultSet rs = null;
//            try {
//                stmt = this.ssmLLClient.createPreparedStatement("getTargetDataListNum", sqlParam);
//                rs = stmt.executeQuery();
//                int cnt = Integer.parseInt(this.multipleCnt);
//                while (rs.next()) {
//                    String lineNum = rs.getString("AR_LOCK_BOX_BAT_LINE_NUM");
//                    int no = chgStr(lineNum) % this.multipleNo;
//                    if (no == cnt) {
//                        lineList.add(lineNum);
//                    }
//                }
//            } catch (SQLException sqlEx) {
//                rollback();
//                throw new S21AbendException(NFDM0003E, new String[] {sqlEx.getMessage() });
//            } finally {
//                S21SsmLowLevelCodingClient.closeResource(stmt, rs);
//            }
//            String[] lineStr;
//            if (lineList.size() != 0) {
//                lineStr = new String[lineList.size()];
//                for (int i = 0; i < lineList.size(); i++) {
//                    lineStr[i] = lineList.get(i);
//                }
//            } else {
//                lineStr = new String[1];
//                lineStr[0] = this.multipleCnt;
//            }
//            sqlParam.put("lineList", lineStr);
//        }
//        // END   2018/07/04 H.Ikeda [QC#25731,MOD]
//
//        return this.ssmLLClient.createPreparedStatement("getTargetData", sqlParam);
//
//    }

    private PreparedStatement getTargetData() throws SQLException {
        // START 2018/07/04 H.Ikeda [QC#25731,MOD]
        List<String> lineList = new ArrayList<String>();
        
        Map<String, Object> sqlParam = new HashMap<String, Object>();
        //Map<String, String> sqlParam = new HashMap<String, String>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("flgY", FLG_ON_Y);
        sqlParam.put("flgN", FLG_OFF_N);
        sqlParam.put("inComp", AR_CUST_ID_STS.SAVED);

        if (ZYPCommonFunc.hasValue(this.multipleCnt)) {
            List<String> rslt =(List<String>) ssmBatchClient.queryObjectList("getTargetDataListNum", sqlParam);
            int cnt = Integer.parseInt(this.multipleCnt);
            for (int i = 0; i < rslt.size(); i++) {
                String lineNum = rslt.get(i);
                int no = chgStr(lineNum) % this.multipleNo;
                if (no == cnt) {
                    lineList.add(lineNum);
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
            sqlParam.put("lineList", lineStr);
        }
        // END   2018/07/04 H.Ikeda [QC#25731,MOD]

        return this.ssmLLClient.createPreparedStatement("getTargetData", sqlParam);
    }

    // START 2018/07/04 H.Ikeda [QC#25731,ADD]
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
    // END   2018/07/04 H.Ikeda [QC#25731,ADD]
    //START 2018/07/30 H.Ikeda [QC#27409,MOD]
    //private PreparedStatement getTrxBalByGrpInv(String custInvNum, List<String> custList) throws SQLException {
    private PreparedStatement getTrxBalByGrpInv(String custInvNum, String rcPayerCustCd) throws SQLException {
    //END   2018/07/30 H.Ikeda [QC#27409,ADD]

        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("custInvNum", custInvNum);
        sqlParam.put("rcp", AR_TRX_TP.RECEIPT);
        //START 2020/01/08 H.Ikeda [QC#55180,ADD]
        sqlParam.put("acc", AR_TRX_TP.ON_ACCOUNT);
        //END   2020/01/08 H.Ikeda [QC#55180,ADD]
        //START 2018/07/30 H.Ikeda [QC#27409,MOD]
        //sqlParam.put("custList", custList);
        sqlParam.put("payerCustCd", rcPayerCustCd);
        sqlParam.put("flgY", FLG_ON_Y);
        sqlParam.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.MYCSA_ACCOUNT);
        sqlParam.put("slsDt", this.batProcDate);
        //END   2018/07/30 H.Ikeda [QC#27409,MOD]

        return this.ssmLLClient.createPreparedStatement("getTrxBalByGrpInv", sqlParam);

    }

    //START 2018/07/30 H.Ikeda [QC#27409,MOD]
    //private PreparedStatement getTrxBalByInv(String custInvNum, List<String> custList) throws SQLException {
    private PreparedStatement getTrxBalByInv(String custInvNum, String rcPayerCustCd) throws SQLException {
    //END    2018/07/30 H.Ikeda [QC#27409,MOD]

        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("custInvNum", custInvNum);
        sqlParam.put("rcp", AR_TRX_TP.RECEIPT);
        //START 2020/01/08 H.Ikeda [QC#55180,ADD]
        sqlParam.put("acc", AR_TRX_TP.ON_ACCOUNT);
        //END   2020/01/08 H.Ikeda [QC#55180,ADD]
        //START 2018/07/30 H.Ikeda [QC#27409,MOD]
        //sqlParam.put("custList", custList);
        sqlParam.put("payerCustCd", rcPayerCustCd);
        sqlParam.put("flgY", FLG_ON_Y);
        sqlParam.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.MYCSA_ACCOUNT);
        sqlParam.put("slsDt", this.batProcDate);
        //END   2018/07/30 H.Ikeda [QC#27409,MOD]

        return this.ssmLLClient.createPreparedStatement("getTrxBalByInv", sqlParam);

    }

    //START 2018/07/30 H.Ikeda [QC#27409,MOD]
    //private PreparedStatement getTrxBalBySrcSysDoc(String custInvNum, List<String> custList) throws SQLException {
    private PreparedStatement getTrxBalBySrcSysDoc(String custInvNum, String rcPayerCustCd) throws SQLException {
    //END   2018/07/30 H.Ikeda [QC#27409,MOD]

        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("custInvNum", custInvNum);
        sqlParam.put("rcp", AR_TRX_TP.RECEIPT);
        //START 2020/01/08 H.Ikeda [QC#55180,ADD]
        sqlParam.put("acc", AR_TRX_TP.ON_ACCOUNT);
        //END   2020/01/08 H.Ikeda [QC#55180,ADD]
        //START 2018/07/30 H.Ikeda [QC#27409,MOD]
        //sqlParam.put("custList", custList);
        sqlParam.put("payerCustCd", rcPayerCustCd);
        sqlParam.put("flgY", FLG_ON_Y);
        sqlParam.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.MYCSA_ACCOUNT);
        sqlParam.put("slsDt", this.batProcDate);
        //END   2018/07/30 H.Ikeda [QC#27409,MOD]

        return this.ssmLLClient.createPreparedStatement("getTrxBalBySrcSysDoc", sqlParam);

    }

    private void createArRcptRcvWrk(BigDecimal rcvSqPk, int hdrCnt, int dtlCnt, ResultSet rs, String arTrxNum, BigDecimal rmngBal, String grpInvNum, boolean isGrpInv) throws SQLException {

        AR_RCPT_RCV_WRKTMsg tmsg = new AR_RCPT_RCV_WRKTMsg();

        setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tmsg.rcvSqPk, rcvSqPk);
        setValue(tmsg.rcvHdrNum, String.format(RCV_HDR_NUM_FMT, hdrCnt));
        setValue(tmsg.rcvDtlNum, String.format(RCV_DTL_NUM_FMT, dtlCnt));
        setValue(tmsg.rcvDt, this.batProcDate);
        setValue(tmsg.rcvFuncId, rs.getString(RCV_FUNC_ID));
        setValue(tmsg.entryDt_01, rs.getString(DEP_DT));
        setValue(tmsg.entryTm_01, rs.getString(DEP_TM));
        setValue(tmsg.chk30Num, rs.getString(VLD_CUST_RCPT_NUM));

        // START 2018/08/20 H.Ikeda [QC#25706,MOD]
        //if (AR_RCPT_SRC.BANK_OF_AMERICA_LOCKBOX.equals(rs.getString(AR_RCPT_SRC_CD))) {
        String arRcptSrcCd = rs.getString(AR_RCPT_SRC_CD);
        if (AR_RCPT_SRC.BANK_OF_AMERICA_LOCKBOX.equals(arRcptSrcCd) || AR_RCPT_SRC.CFS_CLEARING.equals(arRcptSrcCd)) {
        // END   2018/08/20 H.Ikeda [QC#25706,MOD]
            setValue(tmsg.batNum, rs.getString(AR_LOCK_BOX_BAT_NUM));
        } else {
            setValue(tmsg.batNum, AR_RCPT_SRC_999);
        }
        setValue(tmsg.depDt_01, rs.getString(DEP_DT));
        setValue(tmsg.chkAmt, rs.getBigDecimal(CUST_RCPT_AMT));
        setValue(tmsg.ansiCrDrFlg, FLG_OFF_N);
        setValue(tmsg.apvlFlg_01, FLG_OFF_N);
        setValue(tmsg.apvlFlg_02, FLG_OFF_N);
        setValue(tmsg.inv30Num, arTrxNum);
        setValue(tmsg.inv30NumCd, "IV");
        setValue(tmsg.inv30OrigNum, nullVal(grpInvNum, arTrxNum));

        //Def#4999
        if (ZYPCommonFunc.hasValue(rmngBal)) {
            setValue(tmsg.chkRcptTotAmt, rmngBal);
            // START 2016/04/21 S.Fujita [QC#6635,MOD]
            setValue(tmsg.invOrigTotAmt, rmngBal);
            // END 2016/04/21 S.Fujita [QC#6635,MOD]
        } else {
            setValue(tmsg.chkRcptTotAmt, BigDecimal.ZERO);
            // START 2016/04/21 S.Fujita [QC#6635,MOD]
            setValue(tmsg.invOrigTotAmt, BigDecimal.ZERO);
            // END 2016/04/21 S.Fujita [QC#6635,MOD]
        }
//Def#6521
//        setValue(tmsg.invOrigTotAmt, rs.getBigDecimal(CUST_INV_AMT));
        // START 2016/04/21 S.Fujita [QC#6635,MOD]
//        if (isGrpInv) {
//            if (ZYPCommonFunc.hasValue(rmngBal)) {
//                setValue(tmsg.invOrigTotAmt, rmngBal);
//            } else {
//                setValue(tmsg.chkRcptTotAmt, BigDecimal.ZERO);
//            }
//        } else {
//            setValue(tmsg.invOrigTotAmt, rs.getBigDecimal(CUST_INV_AMT));
//        }
        // END 2016/04/21 S.Fujita [QC#6635,MOD]
        setValue(tmsg.invDiscTotAmt, BigDecimal.ZERO);
        setValue(tmsg.cashApplyAmt, BigDecimal.ZERO);
        setValue(tmsg.rcvStsCd, PROC_STS.IN_COMPLETED);
        setValue(tmsg.upldCratMethTpCd, "A");
        setValue(tmsg.rcptBatSqNum, rs.getString(AR_LOCK_BOX_BAT_LINE_NUM));
        setValue(tmsg.exptFlg, FLG_OFF_N);
        setValue(tmsg.arRcptRcvIntfcPk, rs.getBigDecimal(AR_RCPT_RCV_INTFC_PK));
        setValue(tmsg.arBatRcptNm, rs.getString(AR_BAT_RCPT_NM));
        setValue(tmsg.remDsBankAcctPk, rs.getBigDecimal(REM_DS_BANK_ACCT_PK));
        setValue(tmsg.custDsBankAcctPk, rs.getBigDecimal(CUST_DS_BANK_ACCT_PK));
        setValue(tmsg.custAcctRefNum, rs.getString(CUST_ACCT_REF_NUM));
        setValue(tmsg.payerCustCd, rs.getString(PAYER_CUST_CD));
        setValue(tmsg.custRcptNum, rs.getString(VLD_CUST_RCPT_NUM));
        setValue(tmsg.custInvNum, rs.getString(CUST_INV_NUM));
        setValue(tmsg.arRcptSrcCd, rs.getString(AR_RCPT_SRC_CD));
        setValue(tmsg.arCustIdStsCd, rs.getString(AR_CUST_ID_STS_CD));
        setValue(tmsg.arLockBoxFileNm, rs.getString(AR_LOCK_BOX_FILE_NM));
        setValue(tmsg.arLockBoxCd, rs.getString(AR_LOCK_BOX_CD));
        setValue(tmsg.batRcptRecCnt, rs.getBigDecimal(BAT_RCPT_REC_CNT));
        setValue(tmsg.batRcptTotAmt, rs.getBigDecimal(BAT_RCPT_TOT_AMT));

        listTmsg.add(tmsg);

        submitTmsgs(false);
    }

    private String nullVal(String val, String rplsVal) {

        if (!hasValue(val)) {
            return rplsVal;
        }

        return val;
    }

    private boolean submitTmsgs(boolean isLast) {

        if (listTmsg.size() == BULK_CNT || isLast) {
            AR_RCPT_RCV_WRKTMsg[] tmsgs = listTmsg.toArray(new AR_RCPT_RCV_WRKTMsg[listTmsg.size()]);

            // insert using fast table accessor
            int retCnt = S21FastTBLAccessor.insert(tmsgs);

            if (retCnt != listTmsg.size()) {
                throw new S21AbendException(NFDM0013E, new String[] {"AR_RCPT_RCV_WRK" });
            } else {
                // initialize
                listTmsg = new ArrayList<AR_RCPT_RCV_WRKTMsg>();
                normalRecordCnt += retCnt;
            }
            return true;
        } else {
            return true;
        }

    }

    private void createInfoWrk(BigDecimal intfcPk, String arTrxNum, String grpInvNum) {
        AR_RCPT_RCV_INFO_WRKTMsg tmsg = new AR_RCPT_RCV_INFO_WRKTMsg();

        BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.AR_RCPT_RCV_INFO_WRK_SQ);

        setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tmsg.arRcptRcvInfoWrkPk, seqNum);
        setValue(tmsg.arRcptRcvIntfcPk, intfcPk);

        if (hasValue(grpInvNum)) {
            setValue(tmsg.arBatInfoMsgTxt, S21MessageFunc.clspGetMessage(NFCM0778W, new String[] {arTrxNum, grpInvNum }));
        } else {
            setValue(tmsg.arBatInfoMsgTxt, S21MessageFunc.clspGetMessage(NFCM0779W, new String[] {arTrxNum }));
        }

        setValue(tmsg.arBatInfoLvlCd, AR_BAT_INFO_LVL.WARNING);

        listInfoTmsg.add(tmsg);

        submitInfoTmsgs(false);

    }

    private boolean submitInfoTmsgs(boolean isLast) {

        if (listInfoTmsg.size() == BULK_CNT || isLast) {
            AR_RCPT_RCV_INFO_WRKTMsg[] tmsgs = listInfoTmsg.toArray(new AR_RCPT_RCV_INFO_WRKTMsg[listInfoTmsg.size()]);

            // insert using fast table accessor
            int retCnt = S21FastTBLAccessor.insert(tmsgs);

            if (retCnt != listInfoTmsg.size()) {
                throw new S21AbendException(NFDM0013E, new String[] {"AR_RCPT_RCV_INFO_WRK" });
            } else {
                // initialize
                listInfoTmsg = new ArrayList<AR_RCPT_RCV_INFO_WRKTMsg>();
            }
            return true;
        } else {
            return true;
        }

    }

    private void updateSkipIntfc(BigDecimal intfcPk) {
        AR_RCPT_RCV_INTFCTMsg tmsg = new AR_RCPT_RCV_INTFCTMsg();

        setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tmsg.arRcptRcvIntfcPk, intfcPk);

        tmsg = (AR_RCPT_RCV_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

        setValue(tmsg.arRcptRcvWrkCratFlg, FLG_ON_Y);
        setValue(tmsg.arLockBoxStsCd, AR_LOCK_BOX_STS.ERROR);

        listIntfcTmsg.add(tmsg);

        submitIntfcTmsgs(false);
    }

    private void updateIntfc(BigDecimal intfcPk, String arTrxNum, String grpInvNum) {
        AR_RCPT_RCV_INTFCTMsg tmsg = new AR_RCPT_RCV_INTFCTMsg();

        setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tmsg.arRcptRcvIntfcPk, intfcPk);

        tmsg = (AR_RCPT_RCV_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

        setValue(tmsg.arTrxNum, arTrxNum);
        setValue(tmsg.grpInvNum, grpInvNum);
        setValue(tmsg.arRcptRcvWrkCratFlg, FLG_ON_Y);
        setValue(tmsg.arLockBoxStsCd, AR_LOCK_BOX_STS.RECEIPT_WORK_GENERATED);

        listIntfcTmsg.add(tmsg);

        // START 2018/06/21 H.Ikeda [QC#25731,ADD]
        submitIntfcTmsgs(false);
        // END   2018/06/21 H.Ikeda [QC#25731,ADD]
    }

    //START 2017/06/05 E.Kameishi [QC#18703,ADD]
    private void updateRcvWrk(BigDecimal rcvPk, int hdrCnt, BigDecimal chkAmt) {
        AR_RCPT_RCV_WRKTMsg tmsg = new AR_RCPT_RCV_WRKTMsg();

        for (int j = 0; j<listTmsg.size(); j++) {
            tmsg = (AR_RCPT_RCV_WRKTMsg) listTmsg.get(j);
            if (rcvPk.compareTo(tmsg.rcvSqPk.getValue()) == 0
                    && String.format(RCV_HDR_NUM_FMT, hdrCnt).equals(tmsg.rcvHdrNum.getValue())) {
                setValue(tmsg.chkAmt, chkAmt);
                listTmsg.set(j, tmsg);
            }
        }
    }
    //END 2017/06/05 E.Kameishi [QC#18703,ADD]
    
    private boolean submitIntfcTmsgs(boolean isLast) {

        if (listIntfcTmsg.size() == BULK_CNT || isLast) {
            AR_RCPT_RCV_INTFCTMsg[] tmsgs = listIntfcTmsg.toArray(new AR_RCPT_RCV_INTFCTMsg[listIntfcTmsg.size()]);

            // update using fast table accessor
            int retCnt = S21FastTBLAccessor.update(tmsgs);

            if (retCnt != listIntfcTmsg.size()) {
                throw new S21AbendException(NFDM0004E, new String[] {"AR_RCPT_RCV_INTFC" });
            } else {
                // initialize
                listIntfcTmsg = new ArrayList<AR_RCPT_RCV_INTFCTMsg>();
            }
            return true;
        } else {
            return true;
        }

    }

    @SuppressWarnings("unchecked")
    private List<String> getRelatedCustomer(String payerCustCd) {

        if (!ZYPCommonFunc.hasValue(payerCustCd)) {
            return null;
        }

        Map<String, String> sqlParam = new HashMap<String, String>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("payerCustCd", payerCustCd);
        sqlParam.put("flgY", FLG_ON_Y);
        // START 2017/11/15 [QC#17322, ADD]
        sqlParam.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.MYCSA_ACCOUNT);
        // END   2017/11/15 [QC#17322, ADD]
        // START 2018/05/17 [QC#26179, ADD]
        sqlParam.put("slsDt", this.batProcDate);
        // END   2018/05/17 [QC#26179, ADD]

        return (List<String>) ssmBatchClient.queryObjectList("getRelatedCustomer", sqlParam);
    }

    // START 2016/04/21 S.Fujita [QC#6635,MOD]
    // START 2018/08/02 T.Murai [QC#26249,MOD]
    private BigDecimal getCalRmngAmt(BigDecimal rcptAmt, BigDecimal calRmngAmt, BigDecimal rmngBal, BigDecimal ableAmt) {
//        private BigDecimal getCalRmngAmt(BigDecimal rcptAmt, BigDecimal calRmngAmt, BigDecimal rmngBal) {
   // END 2018/08/02 T.Murai [QC#26249,MOD]

        // START 2018/08/02 T.Murai [QC#26249,ADD]
        if (BigDecimal.ZERO.compareTo(rcptAmt) == 0) {
            if (BigDecimal.ZERO.compareTo(rmngBal) < 0) {
                // START 2019/08/23 [QC#52961,ADD]
                if (BigDecimal.ZERO.compareTo(ableAmt) >= 0) {
                    return BigDecimal.ZERO;
                }
                // END 2019/08/23 [QC#52961,ADD]
                if (BigDecimal.ZERO.compareTo(ableAmt.subtract(rmngBal)) < 0) {
                    return rmngBal;
                } else {
                    return ableAmt;
                }
            } else {
                return rmngBal;
            }
        }
        // END 2018/08/02 T.Murai [QC#26249,ADD]

        // START 2018/07/13 H.Ikeda [QC#26860,MOD]
        if (rcptAmt.compareTo(BigDecimal.ZERO) < 0 && rmngBal.compareTo(BigDecimal.ZERO) < 0) {
            if (rcptAmt.subtract(rmngBal).compareTo(BigDecimal.ZERO) > 0) {
                calRmngAmt = rcptAmt;
            } else {
                calRmngAmt = rmngBal;
            } 
        } else {
            if (rcptAmt.subtract(rmngBal).compareTo(BigDecimal.ZERO) > 0) {
              calRmngAmt = rmngBal;
            } else {
              // deubg
              calRmngAmt = rcptAmt;
            }
        }
        return calRmngAmt;
        // END   2018/07/13 H.Ikeda [QC#26860,MOD]
    }
    // END 2016/04/21 S.Fujita [QC#6635,MOD]

    @Override
    protected void termRoutine() {
        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, normalRecordCnt, errorRecordCnt, normalRecordCnt);

        S21InfoLogOutput.println("termRoutine Method End");
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        S21InfoLogOutput.println("Main Method Start");

        new NFCB053002().executeBatch(NFCB053002.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }

    // START 2018/04/18 E.Kameishi [QC#21037,MOD]
    @SuppressWarnings("unchecked")
    private String getCustomer(BigDecimal custDsBankAcctPk) {

        if (!ZYPCommonFunc.hasValue(custDsBankAcctPk)) {
            return null;
        }

        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("custDsBankAcctPk", custDsBankAcctPk);

        return (String) ssmBatchClient.queryObject("getCustomer", sqlParam);
    }

    @SuppressWarnings("unchecked")
    private String getHrchCdFromPayerCust(String payerCustCd) {

        DS_ACCT_CR_PRFLTMsg dsAcctrPrfl = new DS_ACCT_CR_PRFLTMsg();
        dsAcctrPrfl.setSQLID("001");
        dsAcctrPrfl.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        dsAcctrPrfl.setConditionValue("dsAcctNum01", payerCustCd);
        // START 2018/11/07 H.Ikeda [QC#29293,MOD]
        //DS_ACCT_CR_PRFLTMsgArray dsAcctrPrflArray = (DS_ACCT_CR_PRFLTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(dsAcctrPrfl);
        DS_ACCT_CR_PRFLTMsgArray dsAcctrPrflArray = (DS_ACCT_CR_PRFLTMsgArray) EZDTBLAccessor.findByCondition(dsAcctrPrfl);
        // END   2018/11/07 H.Ikeda [QC#29293,MOD]

        if (dsAcctrPrflArray.getValidCount() > 0) {
            return dsAcctrPrflArray.no(0).autoCashHrchCd.getValue();
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private String getHrchCdFromBill(String billToCustCd, String payerCustCd) {

        if (billToCustCd == null) {
            return getHrchCdFromPayerCust(payerCustCd);
        }

        CUST_CR_PRFLTMsg custCrPrfl = new CUST_CR_PRFLTMsg();
        custCrPrfl.setSQLID("001");
        custCrPrfl.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        custCrPrfl.setConditionValue("billToCustCd01", billToCustCd);
        // START 2018/11/07 H.Ikeda [QC#29293,MOD]
        //CUST_CR_PRFLTMsgArray custCrPrflArray = (CUST_CR_PRFLTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(custCrPrfl);
        CUST_CR_PRFLTMsgArray custCrPrflArray = (CUST_CR_PRFLTMsgArray) EZDTBLAccessor.findByCondition(custCrPrfl);
        // END   2018/11/07 H.Ikeda [QC#29293,MOD]

        if (custCrPrflArray.getValidCount() > 0) {
            return custCrPrflArray.no(0).autoCashHrchCd.getValue();
        } else {
            return getHrchCdFromPayerCust(payerCustCd);
        }
    }

    @SuppressWarnings("unchecked")
    // Start 2018/08/28 H.Ikedai [QC#26135,MOD]
    //private List<Map<String, Object>> getTrxBalByDueDt(String payerCustNum) {
    // Start 2018/09/03 H.Ikeda [QC#28062,MOD]
    //private List<Map<String, Object>> getTrxBalByDueDt(String payerCustNum, String fileName) {
    private List<Map<String, Object>> getTrxBalByDueDt(String payerCustNum, String fileName, boolean chkAmtType) {
        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        if (chkAmtType) {
            sqlParam.put("crm", AR_TRX_TP.CREDIT_MEMO);
        } else {
            sqlParam.put("rcp", AR_TRX_TP.RECEIPT);
            sqlParam.put("crm", AR_TRX_TP.CREDIT_MEMO);
            //START 2020/01/08 H.Ikeda [QC#55180,ADD]
            sqlParam.put("acc", AR_TRX_TP.ON_ACCOUNT);
            //END   2020/01/08 H.Ikeda [QC#55180,ADD]
        }
        // End 2018/09/03 H.Ikeda [QC#28062,MOD]
        sqlParam.put("payerCustNum", payerCustNum);
        sqlParam.put("arLockBoxFileNm", fileName);
        // END   2018/08/28 H.Ikedai [QC#26135,MOD]

        return  (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getTrxBalByDueDt", sqlParam);
    }
    // END 2018/04/18 E.Kameishi [QC#21037,MOD]

    // START 2018/07/06 H.Ikeda [QC#25731,ADD]
    private int getRcvHdrNum(String arbatRcptNm) {
        int rtn = 0;

        if (ZYPCommonFunc.hasValue(this.multipleCnt)) {
            Map<String, String> sqlParam = new HashMap<String, String>();
            sqlParam.put("glblCmpyCd", this.glblCmpyCd);
            sqlParam.put("arbatRcptNm", arbatRcptNm);
            String rcvHdrNumStr = (String) ssmBatchClient.queryObject("getRcvHdrNum", sqlParam);
            if (ZYPCommonFunc.hasValue(rcvHdrNumStr)) {
                Pattern ptn = Pattern.compile("^0+([0-9]+.*)");
                Matcher mch = ptn.matcher(rcvHdrNumStr);
                if (mch.matches()) {
                  rtn = Integer.parseInt(mch.group(1));
                }
            }
        }

        return rtn;
    }
    // END   2018/07/06 H.Ikeda [QC#25731,ADD]
}
