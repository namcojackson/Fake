/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB722001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import parts.common.EZDItem;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CSMP_INV_ERRTMsg;
import business.db.DS_INV_TPTMsg;
import business.db.INV_LINETMsg;
//import business.db.INV_PRT_CTRLTMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.parts.NFZC309001PMsg;
import business.parts.NWZC040001PMsg;
import business.parts.NWZC040002PMsg;
import business.parts.NWZC040003PMsg;
import business.parts.NWZC040005PMsg;
import business.parts.NWZC040006PMsg;
import business.parts.NWZC040007PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;

import com.canon.cusa.s21.api.NFC.NFZC309001.NFZC309001;
import com.canon.cusa.s21.api.NWZ.NWZC040001.NWZC040001;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Exchange;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmount;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmoutData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangePriceData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
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
 * CSMP Auto Claim creation Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/28   CSAI            Miyauchi        Create          N/A
 * 2016/07/26   Fujitsu         S.Yoshida       Update          QC#12252,12442
 * 2016/07/29   Fujitsu         C.Tanaka        Update          QC#12606
 * 2016/08/03   Fujitsu         C.Tanaka        Update          QC#12823
 * 2016/10/06   SRAA            K.Aratani       Update          QC#15080
 * 2017/10/23   Fujitsu         T.Aoi           Update          QC#20719
 * 2017/11/24   Hitachi         E.Kameishi      Update          QC#19735
 * 2017/12/05   Hitachi         E.Kameishi      Update          QC#22921
 * 2017/12/12   Fujitsu         M.Ohno          Update          QC#22813
 * 2018/01/05   Fujitsu         Hd.Sugawara     Update          QC#22371
 * 2018/03/22   Fujitsu         H.Nagashima     Update          QC#24677
 * 2018/03/29   Fujitsu         M.Ohno          Update          QC#24677-2
 * 2019/02/27   Fujitsu         S.Kosaka        Update          QC#30533
 * 
 * </pre>
 */
public class NWCB722001 extends S21BatchMain implements NWCB722001Constant {

    /** User Profile */
    private S21UserProfileService profile;
    /** Global Company Code */
    private String glblCmpyCd;
    /** Sales Date */
    private String slsDt;
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;
    /** Normal Record Count */
    private int normalRcCnt = 0;
    /** Total Record Count */
    private int totRcCnt = 0;
    /** Error Record Count */
    private int errRcCnt = 0;
//    /** Line Price Diff Row **/
//    private int dealSlsMaxRow = 0;
//    /** Line Price Diff Total Amount **/
//    private BigDecimal dealSlsCrTotAmt = BigDecimal.ZERO;
//    /** Line Price Diff Row **/
//    private int funcSlsMaxRow = 0;
//    /** Line Price Max Diff Total Amount **/
//    private BigDecimal funcSlsCrTotAmt = BigDecimal.ZERO;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    ArrayList<String> errMsgList = new ArrayList<String>();

    /** */
    private String varCharBillToAcct = "";
    private String varCharBillToCust = "";
    private String varCharDsInvTpCd = "";
    private String varCharPmtTermCashDisc = "";
    private String varCharTrxCd = "";
    private String varCharTrxRsnCd = "";
    private String varCharPrcList05 = "";
    private String dueDate = "";
    private String pmtTermCd = "";
    private String prmrySlsRepTocCd = "";

    @Override
    protected void initRoutine() {
        S21InfoLogOutput.println("initRoutine Method Start");

        /** Initialize SSM Batch client. */
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        /** Get User Profile Service Instance */
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        /** Get Global Company Code */
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        /** Get Sales Date */
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        normalRcCnt = 0;
        totRcCnt = 0;
        errRcCnt = 0;

        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(MSG_ID.NWCM0059E.name(), new String[] {"Global Company Code"});
        }
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            throw new S21AbendException(MSG_ID.NWCM0059E.name(), new String[] {"Sales Date"});
        }

        S21InfoLogOutput.println("initRoutine Method End");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        S21InfoLogOutput.println("Main Method Start");

        /** Initialize S21BatchMain */
        new NWCB722001().executeBatch(NWCB722001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }

    @Override
    protected void termRoutine() {
        S21InfoLogOutput.println("termRoutine Method Start");

        setTermState(this.termCd, this.normalRcCnt, this.errRcCnt, this.totRcCnt);

        S21InfoLogOutput.println("termRoutine Method End");
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void mainRoutine() {
        S21InfoLogOutput.println("mainRoutine Method Start");

        //Init
        //String compLevel = "";
        errMsgList = new ArrayList<String>();

        //Get VAR_CHAR_CONST values
        getVarCharConst();

        //Get Due Date value
        this.dueDate = getDueDate();

        //Get Pmt Term Cd value
        this.pmtTermCd = getPmtTrmCd();

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("csmpInvProcStsCd_1", "1");
        queryParam.put("csmpInvProcStsCd_9", "9");
        queryParam.put("ordLineCtxTpCd","CSMP_CLAIM_EXCL");
        List csmpClaimList = this.ssmBatchClient.queryObjectList("getCsmpClaimList", queryParam);

        if ( csmpClaimList == null || csmpClaimList.size() <= 0) {
        } else {
            //CSMP Import Invoice
            errMsgList = chkFlowCase(csmpClaimList);
        }

        //Send Mail
        if ( errMsgList != null && errMsgList.size() != 0 ) {
            if ( sendMail( errMsgList ) ) {
                // Send Mail Error
            }
        }

        S21InfoLogOutput.println("mainRoutine Method End");
    }

    /***
     * The method explanation: Obtain some VAR_CHAR_CONST values.
     * It will be abend when the value is nothing.
     */
    @SuppressWarnings("unchecked")
    public void getVarCharConst() {

        //Get VAR_CHAR_CONST value for Checking Status.
        this.varCharBillToAcct = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_ARG.CSMP_CLAIM_BILL_TO_ACCT.name(), this.glblCmpyCd);
        if ( !ZYPCommonFunc.hasValue(this.varCharBillToAcct ) ) {
            throw new S21AbendException(MSG_ID.NMAM8432E.name(), new String[] {VAR_CHAR_ARG.CSMP_CLAIM_BILL_TO_ACCT.name()});
        }
        //QC#15080
        //DS_ACCT_CUST exists check
        Map<String, Object> extChkPrm = new HashMap<String, Object>();
        extChkPrm.put("glblCmpyCd", this.glblCmpyCd);
        extChkPrm.put("sellToCustCd", this.varCharBillToAcct);
        Map<String, Object> extChkMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getDsAcctCust", extChkPrm);
        if ( extChkMap == null || extChkMap.get("SELL_TO_CUST_PK") == null) {
            throw new S21AbendException(MSG_ID.NMAM8432E.name(), new String[] {VAR_CHAR_ARG.CSMP_CLAIM_BILL_TO_ACCT.name()});
        }

        //QC#15080
        //BILL_TO_CUST exists check
        extChkPrm = new HashMap<String, Object>();
        extChkPrm.put("glblCmpyCd", this.glblCmpyCd);
        extChkPrm.put("sellToCustCd", this.varCharBillToAcct);
        extChkPrm.put("slsDt", this.slsDt);
        extChkMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getBillToCust", extChkPrm);
        if ( extChkMap == null || extChkMap.get("BILL_TO_CUST_CD") == null) {
            throw new S21AbendException(MSG_ID.NMAM8432E.name(), new String[] {VAR_CHAR_ARG.CSMP_CLAIM_BILL_TO_ACCT.name()});
        }
        this.varCharBillToCust = (String) extChkMap.get("BILL_TO_CUST_CD");

        this.varCharDsInvTpCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_ARG.CSMP_CLAIM_DS_INV_TP_CD.name(), this.glblCmpyCd);
        if ( !ZYPCommonFunc.hasValue(this.varCharDsInvTpCd) ) {
            throw new S21AbendException(MSG_ID.NMAM8432E.name(), new String[] {VAR_CHAR_ARG.CSMP_CLAIM_DS_INV_TP_CD.name()});
        }
        //QC#15080
        //DS_INV_TP exists check
        DS_INV_TPTMsg dsInvTpTMsg = new DS_INV_TPTMsg();
        ZYPEZDItemValueSetter.setValue(dsInvTpTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsInvTpTMsg.dsInvTpCd, varCharDsInvTpCd);
        dsInvTpTMsg = (DS_INV_TPTMsg) S21CacheTBLAccessor.findByKey(dsInvTpTMsg);
        if (dsInvTpTMsg == null || dsInvTpTMsg.autoSeqCd.getValue() == null) {
            throw new S21AbendException(MSG_ID.NMAM8432E.name(), new String[] {VAR_CHAR_ARG.CSMP_CLAIM_DS_INV_TP_CD.name()});
        }
        
        this.varCharPmtTermCashDisc = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_ARG.CSMP_CLAIM_PMT_TERM_CASH_DISC.name(), this.glblCmpyCd);
        if ( !ZYPCommonFunc.hasValue(this.varCharPmtTermCashDisc) ) {
            throw new S21AbendException(MSG_ID.NMAM8432E.name(), new String[] {VAR_CHAR_ARG.CSMP_CLAIM_PMT_TERM_CASH_DISC.name()});
        }
        //QC#15080
        //PMT_TERM_CASH_DISC exists check
        PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = new PMT_TERM_CASH_DISCTMsg();
        ZYPEZDItemValueSetter.setValue(pmtTermCashDiscTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmtTermCashDiscTMsg.pmtTermCashDiscCd, varCharPmtTermCashDisc);
        pmtTermCashDiscTMsg = (PMT_TERM_CASH_DISCTMsg) S21CacheTBLAccessor.findByKey(pmtTermCashDiscTMsg);
        if (pmtTermCashDiscTMsg == null) {
            throw new S21AbendException(MSG_ID.NMAM8432E.name(), new String[] {VAR_CHAR_ARG.CSMP_CLAIM_PMT_TERM_CASH_DISC.name()});
        }

        this.varCharTrxCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_ARG.CSMP_CLAIM_TRX_CD.name(), this.glblCmpyCd);
        if ( !ZYPCommonFunc.hasValue(this.varCharTrxCd) ) {
            throw new S21AbendException(MSG_ID.NMAM8432E.name(), new String[] {VAR_CHAR_ARG.CSMP_CLAIM_TRX_CD.name()});
        }
        //TRX exists check
//        TRXTMsg trxTMsg = new TRXTMsg();
//        ZYPEZDItemValueSetter.setValue(trxTMsg.glblCmpyCd, this.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(trxTMsg.trxCd, varCharTrxCd);
//        trxTMsg = (TRXTMsg) S21CacheTBLAccessor.findByKey(trxTMsg);
//        if (trxTMsg == null) {
//            throw new S21AbendException(MSG_ID.NMAM8432E.name(), new String[] {VAR_CHAR_ARG.CSMP_CLAIM_TRX_CD.name()});
//        }

        this.varCharTrxRsnCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_ARG.CSMP_CLAIM_TRX_RSN_CD.name(), this.glblCmpyCd);
        if ( !ZYPCommonFunc.hasValue(this.varCharTrxRsnCd)) {
            throw new S21AbendException(MSG_ID.NMAM8432E.name(), new String[] {VAR_CHAR_ARG.CSMP_CLAIM_TRX_RSN_CD.name()});
        }
        //TRX_RSN exists check
//        TRX_RSNTMsg trxRsnTMsg = new TRX_RSNTMsg();
//        ZYPEZDItemValueSetter.setValue(trxRsnTMsg.glblCmpyCd, this.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(trxRsnTMsg.trxCd, varCharTrxCd);
//        trxRsnTMsg = (TRX_RSNTMsg) S21CacheTBLAccessor.findByKey(trxRsnTMsg);
//        if (trxTMsg == null) {
//            throw new S21AbendException(MSG_ID.NMAM8432E.name(), new String[] {VAR_CHAR_ARG.CSMP_CLAIM_TRX_RSN_CD.name()});
//        }

        this.varCharPrcList05 = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_ARG.CSMP_PRC_LIST_05.name(), this.glblCmpyCd);
        if ( !ZYPCommonFunc.hasValue(this.varCharPrcList05)) {
            throw new S21AbendException(MSG_ID.NMAM8432E.name(), new String[] {VAR_CHAR_ARG.CSMP_PRC_LIST_05.name()});
        }
    }

    private String getDueDate () {
        NFZC309001 dueDateCalcApi = new NFZC309001();
        NFZC309001PMsg dueDateCalcApiPMsg = new NFZC309001PMsg();

        ZYPEZDItemValueSetter.setValue(dueDateCalcApiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dueDateCalcApiPMsg.pmtTermCashDiscCd, this.varCharPmtTermCashDisc);
        ZYPEZDItemValueSetter.setValue(dueDateCalcApiPMsg.trxDt, this.slsDt);

        dueDateCalcApi.execute(dueDateCalcApiPMsg, ONBATCH_TYPE.BATCH);
        if (dueDateCalcApiPMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < dueDateCalcApiPMsg.xxMsgIdList.getValidCount(); i++) {
                String msgId = dueDateCalcApiPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                S21InfoLogOutput.println(msgId);
                //errInfoList.add(bean.getErrInfo(S21MessageFunc.clspGetMessage(msgId)));
            }
            return null;
        }

        return dueDateCalcApiPMsg.dueDt.getValue();
    }

    private String getPmtTrmCd () {
        //
        PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = (PMT_TERM_CASH_DISCTMsg) ZYPCodeDataUtil.findByCode(PMT_TERM_CASH_DISC.class, glblCmpyCd, this.varCharPmtTermCashDisc);
        if (pmtTermCashDiscTMsg == null) {
            return "";
        }

        return pmtTermCashDiscTMsg.pmtTermCd.getValue();
    }

    @SuppressWarnings("unchecked")
    public ArrayList<String> chkFlowCase( List<Map> csmpClaimList ) {

        //boolean blCompHdr = false;
        boolean blSetItem = false;
        boolean blCreateInv = false;
        boolean blImportInv = false;
        boolean blImportInvErr = false;
        //boolean blImportInvHdrErr = false;
        List<String> compList = new ArrayList<String>();
        List<String> invList = new ArrayList<String>();
        DS_INV_TPTMsg dsInvTpTMsg = new DS_INV_TPTMsg();
        ArrayList<String> errMsgList = new ArrayList<String>();
        //List<String> aftrMrktItems = new ArrayList<String>();
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        String prcCatgNm = "";
        String prcCatgCd = "";
        List<NWZC040001PMsg> impInvHdrPMsgList = new ArrayList<NWZC040001PMsg>();
        List<NWZC040002PMsg> impInvShipPMsgList= new ArrayList<NWZC040002PMsg>();
        List<NWZC040003PMsg> impInvLinePMsgList = new ArrayList<NWZC040003PMsg>();
        List<NWZC040005PMsg> pMsgTaxList = new ArrayList<NWZC040005PMsg>();
        List<NWZC040006PMsg> impInvSerPMsgList = new ArrayList<NWZC040006PMsg>();
        List<NWZC040007PMsg> impInvSlsCrMsgList = new ArrayList<NWZC040007PMsg>();
        List<String> invNumList = new ArrayList<String>();
        List<String> invBolNumList = new ArrayList<String>();
        List<String> invLineNumList = new ArrayList<String>();
        List<String> invLineSubNumList = new ArrayList<String>();
        List<String> invLineSubTrxNumList = new ArrayList<String>();
        //QC#24677 add Start
        List<NWZC040003PMsg> aftMrktItemInvLineList = new ArrayList<NWZC040003PMsg>();
        List<NWZC040007PMsg> aftMrktItemInvSlsCrList = new ArrayList<NWZC040007PMsg>();
        //QC#24677 add End

//Add Start QC#12823
        dsInvTpTMsg = new DS_INV_TPTMsg();
        ZYPEZDItemValueSetter.setValue(dsInvTpTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsInvTpTMsg.dsInvTpCd, varCharDsInvTpCd);
        dsInvTpTMsg = (DS_INV_TPTMsg) S21CacheTBLAccessor.findByKey(dsInvTpTMsg);

        // START 2017/11/24 E.Kameishi [QC#19735, MOD]
        //String invNum = ZYPExtnNumbering.getUniqueID(glblCmpyCd, dsInvTpTMsg.autoSeqCd.getValue());
        String invNum = ZYPMaxTenDigitsNumbering.getUniqueID(glblCmpyCd, dsInvTpTMsg.autoSeqCd.getValue());
        // END 2017/11/24 E.Kameishi [QC#19735, MOD]
//Add End   QC#12823

        //QC#25120
        List removedList = new ArrayList();
        for (Map<String, Object> csmpClaim : csmpClaimList) {
            if (ZYPCommonFunc.hasValue((String) csmpClaim.get(COL_NM_SL.INV_NUM.name()))
                && !removedList.contains((String) csmpClaim.get(COL_NM_SL.INV_NUM.name()))) {
                remove((String) csmpClaim.get(COL_NM_SL.INV_NUM.name()));
                removedList.add((String) csmpClaim.get(COL_NM_SL.INV_NUM.name()));
            }
        }
        int iBolCnt = 0;
        int iLineCnt = 0;
        int iLpCnt2 = 0;
        for (int iLpCnt=1; iLpCnt <= csmpClaimList.size() + 1; iLpCnt++) {
            if (iLpCnt == csmpClaimList.size() + 1) {
                iLpCnt2 = csmpClaimList.size() - 1;
            } else {
                iLpCnt2 = iLpCnt - 1;
                totRcCnt ++;
            }
            Map<String, Object> csmpClaim = csmpClaimList.get(iLpCnt2);

            //Check Header of each Component Level.
            //blCompHdr = false;
            if (!invList.contains(csmpClaim.get(COL_NM_SL.INV_NUM.name()).toString())) {
                prmrySlsRepTocCd = csmpClaim.get(COL_NM_SL.SLS_REP_TOC_CD.name()).toString();
                invList.add(csmpClaim.get(COL_NM_SL.INV_NUM.name()).toString());
            }
            if ( !compList.contains(csmpClaim.get(COL_NM_SL.INV_NUM.name()).toString() + csmpClaim.get(COL_NM_SL.DS_ORD_POSN_NUM.name())) || iLpCnt == csmpClaimList.size() + 1 ) {
                if ( blImportInv ) {
                    blImportInvErr = false;
                    //(10)'.Call Import Invoice API each component.(Execute the Import Invoice API)
//QC#24677 add Start
                    if (!aftMrktItemInvLineList.isEmpty()) {
                        // get max line number
                        int maxLineNum = 0;
                        for (NWZC040003PMsg linePMsg : impInvLinePMsgList) {
                            int lineNum = Integer.parseInt(linePMsg.invLineNum.getValue());
                            if (maxLineNum < lineNum) {
                                maxLineNum = lineNum;
                            }
                        }
                        //add after market item line
                        for (NWZC040003PMsg aftMrktItemInvLinePMsg : aftMrktItemInvLineList) {
                            String origLineNum = aftMrktItemInvLinePMsg.invLineNum.getValue();
                            String origLineSubNum = aftMrktItemInvLinePMsg.invLineSubNum.getValue();
                            String newLineNum = ZYPCommonFunc.leftPad(String.valueOf(++maxLineNum), 5, "0");
                            String newLineSubNum = "001";
                            // add sales credit param
                            for (NWZC040007PMsg aftInvSlsCrPMsg : aftMrktItemInvSlsCrList) {
                                if (origLineNum.equals(aftInvSlsCrPMsg.invLineNum.getValue())
                                        && origLineSubNum.equals(aftInvSlsCrPMsg.invLineSubNum.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(aftInvSlsCrPMsg.invLineNum, newLineNum);
                                    ZYPEZDItemValueSetter.setValue(aftInvSlsCrPMsg.invLineSubNum, newLineSubNum);
                                    impInvSlsCrMsgList.add(aftInvSlsCrPMsg);
                                }
                            }
                            // add line param
                            ZYPEZDItemValueSetter.setValue(aftMrktItemInvLinePMsg.invLineNum, newLineNum);
                            ZYPEZDItemValueSetter.setValue(aftMrktItemInvLinePMsg.invLineSubNum, newLineSubNum);
                            impInvLinePMsgList.add(aftMrktItemInvLinePMsg);

                        }
                    }
//QC#24677 add End
                    /****************************************/
                    /* Invoice Import API                   */
                    /****************************************/
                    NWZC040001 imptInvApi = new NWZC040001();
                    imptInvApi.execute(impInvHdrPMsgList, impInvShipPMsgList, impInvLinePMsgList, null, pMsgTaxList, impInvSerPMsgList, impInvSlsCrMsgList, ONBATCH_TYPE.BATCH);

//Add Start QC#12823
                    // START 2017/11/24 E.Kameishi [QC#19735, MOD]
                    //invNum = ZYPExtnNumbering.getUniqueID(glblCmpyCd, dsInvTpTMsg.autoSeqCd.getValue());
                    invNum = ZYPMaxTenDigitsNumbering.getUniqueID(glblCmpyCd, dsInvTpTMsg.autoSeqCd.getValue());
                    // END 2017/11/24 E.Kameishi [QC#19735, MOD]
//Add End   QC#12823

                    /****************************************/
                    /* Invoice Import API Error             */
                    /****************************************/
                    // Header
                    for(NWZC040001PMsg pMsgHdr : impInvHdrPMsgList ) {
                        if(pMsgHdr.xxMsgIdList.getValidCount() > 0) {
                            for(int i = 0; i < pMsgHdr.xxMsgIdList.getValidCount(); i++) {
                                String errId = pMsgHdr.xxMsgIdList.no(i).xxMsgId.getValue();
                                errMsgList.add( csmpClaim.get(COL_NM_SL.INV_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.INV_BOL_LINE_NUM.name()).toString() +
                                        "," + csmpClaim.get(COL_NM_SL.INV_LINE_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.INV_LINE_SUB_NUM.name()).toString() + 
                                        "," + csmpClaim.get(COL_NM_SL.INV_LINE_SUB_TRX_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.CPO_ORD_NUM.name()).toString() + 
                                        "," + castToString(csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_NUM.name())) + "," + castToString(csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_SUB_NUM.name())) + 
                                        "," + ERR_MSG.IMPORT_INVOICE_ERROR.getValue() + "" + " : " + S21MessageFunc.clspGetMessage(errId));
                                blImportInvErr = true;
                                continue;
                            }
                        }
                    }
                    // BOL
                    for(NWZC040002PMsg pMsgBol : impInvShipPMsgList ) {
                        if( pMsgBol.xxMsgIdList.getValidCount() > 0) {
                            for(int i = 0; i < pMsgBol.xxMsgIdList.getValidCount(); i++) {
                                String errId = pMsgBol.xxMsgIdList.no(i).xxMsgId.getValue();
                                errMsgList.add(csmpClaim.get(COL_NM_SL.INV_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.INV_BOL_LINE_NUM.name()).toString() +
                                        "," + csmpClaim.get(COL_NM_SL.INV_LINE_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.INV_LINE_SUB_NUM.name()).toString() + 
                                        "," + csmpClaim.get(COL_NM_SL.INV_LINE_SUB_TRX_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.CPO_ORD_NUM.name()).toString() + 
                                        "," + castToString(csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_NUM.name())) + "," + castToString(csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_SUB_NUM.name())) + 
                                        "," + ERR_MSG.IMPORT_INVOICE_ERROR.getValue() + "" + " : " + S21MessageFunc.clspGetMessage(errId));
                                blImportInvErr = true;
                                continue;
                            }
                        }
                    }
                    
                    // Line
                    for(NWZC040003PMsg pMsgLine : impInvLinePMsgList ) {
                        if(pMsgLine.xxMsgIdList.getValidCount() > 0) {
                            for(int i = 0; i < pMsgLine.xxMsgIdList.getValidCount(); i++) {
                                String errId = pMsgLine.xxMsgIdList.no(i).xxMsgId.getValue();
                                errMsgList.add( csmpClaim.get(COL_NM_SL.INV_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.INV_BOL_LINE_NUM.name()).toString() +
                                        "," + csmpClaim.get(COL_NM_SL.INV_LINE_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.INV_LINE_SUB_NUM.name()).toString() + 
                                        "," + csmpClaim.get(COL_NM_SL.INV_LINE_SUB_TRX_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.CPO_ORD_NUM.name()).toString() + 
                                        "," + castToString(csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_NUM.name())) + "," + castToString(csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_SUB_NUM.name())) + 
                                        "," + ERR_MSG.IMPORT_INVOICE_ERROR.getValue() + csmpClaim.get(COL_NM_SL.MDSE_CD.name()).toString() + " : " + S21MessageFunc.clspGetMessage(errId));
                                blImportInvErr = true;
                                continue;
                            }
                        }
                    }
                    // TAX
                    //for(NWZC040005PMsg pMsgTax : pMsgTaxList ) {
                    //    if(pMsgTax.xxMsgIdList.getValidCount() > 0) {
                    //        for(int i = 0; i < pMsgTax.xxMsgIdList.getValidCount(); i++) {
                    //            String errId = pMsgTax.xxMsgIdList.no(i).xxMsgId.getValue();
                    //            bizMsg.setMessageInfo(errId);
                    //            bizMsg.xxDplyTab.setValue(TAB_Line);
                    //            return false;
                    //        }
                    //    }
                    //}
                    // Serial
                    for(NWZC040006PMsg pMsgSer : impInvSerPMsgList ) {
                        if(pMsgSer.xxMsgIdList.getValidCount() > 0) {
                            for(int i = 0; i < pMsgSer.xxMsgIdList.getValidCount(); i++) {
                                String errId = pMsgSer.xxMsgIdList.no(i).xxMsgId.getValue();
                                errMsgList.add( csmpClaim.get(COL_NM_SL.INV_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.INV_BOL_LINE_NUM.name()).toString() +
                                        "," + csmpClaim.get(COL_NM_SL.INV_LINE_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.INV_LINE_SUB_NUM.name()).toString() + 
                                        "," + csmpClaim.get(COL_NM_SL.INV_LINE_SUB_TRX_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.CPO_ORD_NUM.name()).toString() + 
                                        "," + castToString(csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_NUM.name())) + "," + castToString(csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_SUB_NUM.name())) + 
                                        "," + ERR_MSG.IMPORT_INVOICE_ERROR.getValue() + csmpClaim.get(COL_NM_SL.MDSE_CD.name()).toString() + " : " + S21MessageFunc.clspGetMessage(errId));
                                blImportInvErr = true;
                                continue;
                            }
                        }
                    }
                    // Sales Credit
                    for(NWZC040007PMsg pMsgSlsCr : impInvSlsCrMsgList ) {
                        if(pMsgSlsCr.xxMsgIdList.getValidCount() > 0) {
                            for(int i = 0; i < pMsgSlsCr.xxMsgIdList.getValidCount(); i++) {
                                String errId = pMsgSlsCr.xxMsgIdList.no(i).xxMsgId.getValue();
                                errMsgList.add( csmpClaim.get(COL_NM_SL.INV_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.INV_BOL_LINE_NUM.name()).toString() +
                                        "," + csmpClaim.get(COL_NM_SL.INV_LINE_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.INV_LINE_SUB_NUM.name()).toString() + 
                                        "," + csmpClaim.get(COL_NM_SL.INV_LINE_SUB_TRX_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.CPO_ORD_NUM.name()).toString() + 
                                        "," + castToString(csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_NUM.name())) + "," + castToString(csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_SUB_NUM.name())) + 
                                        "," + ERR_MSG.IMPORT_INVOICE_ERROR.getValue() + csmpClaim.get(COL_NM_SL.MDSE_CD.name()).toString() + " : " + S21MessageFunc.clspGetMessage(errId));
                                blImportInvErr = true;
                                continue;
                            }
                        }
                    }

                    //(11).Update process
                    if ( !blImportInvErr ){
                        for (int iLastUpdateCnt=0; iLastUpdateCnt<invNumList.size(); iLastUpdateCnt++) {
                            // Update the Status
                            if (!updateInvProcSts(invNumList.get(iLastUpdateCnt).toString(), invBolNumList.get(iLastUpdateCnt).toString(), invLineNumList.get(iLastUpdateCnt), invLineSubNumList.get(iLastUpdateCnt).toString(), invLineSubTrxNumList.get(iLastUpdateCnt).toString()) ) {
                                rollback();
                                continue;
                            }
                        }
                        // QC#12823 MOD Start
                        normalRcCnt += iLineCnt;
                        // QC#12823 MOD End
                    }
                    //Initialize these Arguments of Import Invoice API
                    impInvHdrPMsgList = new ArrayList<NWZC040001PMsg>();
                    impInvShipPMsgList= new ArrayList<NWZC040002PMsg>();
                    impInvLinePMsgList = new ArrayList<NWZC040003PMsg>();
                    impInvSerPMsgList = new ArrayList<NWZC040006PMsg>();
                    impInvSlsCrMsgList = new ArrayList<NWZC040007PMsg>();
                    //QC#24677 add Start
                    aftMrktItemInvLineList = new ArrayList<NWZC040003PMsg>();
                    aftMrktItemInvSlsCrList = new ArrayList<NWZC040007PMsg>();
                    //QC#24677 add End
                }
                commit();
            }

            if (iLpCnt == csmpClaimList.size() + 1) {
                return errMsgList;
            }

            if ( !compList.contains(csmpClaim.get(COL_NM_SL.INV_NUM.name()).toString() + csmpClaim.get(COL_NM_SL.DS_ORD_POSN_NUM.name())) ) {
                //Initialize some variable
                //blCompHdr = true;
                blCreateInv = true;
                blImportInv = false;
                iLineCnt = 0;
                iBolCnt = 0;
                compList.add(csmpClaim.get(COL_NM_SL.INV_NUM.name()).toString() + csmpClaim.get(COL_NM_SL.DS_ORD_POSN_NUM.name()));
            }

            // Add Start 2018/01/05 QC#22371
            if (isCsmpExclude(csmpClaim)) {
                skipProcess(csmpClaim);
                normalRcCnt++;
                continue;
            }
            // Add End 2018/01/05 QC#22371

            blSetItem = false;

            //Set Header Information to Original Object.
            //Additional Information
            csmpClaim.put(COL_NM_SL.CSMP_PRC_LIST_CD_SAVE.name(), "");
            csmpClaim.put(COL_NM_SL.ISTL_DT_SAVE.name(), "");

            if (csmpClaim.get(COL_NM_SL.INV_LINE_SUB_NUM.name()).toString().equals(DEF_VAL.INV_LINE_SUB_NUM_000.getValue())) {

                //(1).Check After Market or Not.
                String ordTakeMdseCd = (String) csmpClaim.get(COL_NM_SL.ORD_TAKE_MDSE_CD.name());
                if (ZYPCommonFunc.hasValue(ordTakeMdseCd)) {
                    blSetItem = isSetItem(ordTakeMdseCd);

                } else {
                    String mdse = (String) csmpClaim.get(COL_NM_SL.MDSE_CD.name());
                    if (ZYPCommonFunc.hasValue(mdse)) {
                        blSetItem = isSetItem(mdse);
                    } else {
                        blSetItem = false;
                    }
                }
            }

            //(2).CSMP Contract Check
            String csmpContNum = isCsmpContract(csmpClaim);
            if (csmpContNum.equals("")) {
//Mod Start QC#12252
                errProcess(csmpClaim, "Not exists in CSMP Contract Master");
//Mod End   QC#12252
                blCreateInv = false;
                errMsgList.add( csmpClaim.get(COL_NM_SL.INV_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.INV_BOL_LINE_NUM.name()).toString() +
                                        "," + csmpClaim.get(COL_NM_SL.INV_LINE_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.INV_LINE_SUB_NUM.name()).toString() + 
                                        "," + csmpClaim.get(COL_NM_SL.INV_LINE_SUB_TRX_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.CPO_ORD_NUM.name()).toString() + 
                                        "," + castToString(csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_NUM.name())) + "," + castToString(csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_SUB_NUM.name())) + 
                                        "," + ERR_MSG.CONTRACT_ERROR.getValue() + csmpClaim.get(COL_NM_SL.MDSE_CD.name()).toString());
            } else {
                csmpClaim.put(COL_NM_SL.CSMP_CONTR_NUM.name(), csmpContNum);
            }

            if (csmpClaim.get(COL_NM_SL.CSMP_PRC_LIST_CD.name()) == null) {

                //(3).Pricing API check Mode=1
                prcCatgNm = isPricingMode1(csmpClaim);
                if (prcCatgNm.equals(PRC_CD.ERROR.getValue())) {
                    continue;
                }
            } else {

                //(4).Special CSMP Exclude AR Number check
                String spclCsmpExclArNm = getSpclCsmpExclArNm(csmpClaim);
                if (spclCsmpExclArNm.equals(PRC_CD.ERROR.getValue())) {
                    skipProcess(csmpClaim);
                    continue;
                }
            } 

            //(5).Install Date Check
            String intlDt = getSvcMachMstrForIstlDt(csmpClaim);
            if (intlDt.equals(PRC_CD.ERROR.getValue())) {
//Mod Start QC#12252
                StringBuilder errMsg = new StringBuilder();
                errMsg.append("Main Machine has not installed yet. Machine PK = ");
                errMsg.append(csmpClaim.get(COL_NM_SL.SVC_CONFIG_MSTR_PK.name()));
                errProcess(csmpClaim, errMsg.toString());
//Mod End  QC#12252
                blCreateInv = false;
                errMsgList.add( csmpClaim.get(COL_NM_SL.INV_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.INV_BOL_LINE_NUM.name()).toString() +
                        "," + csmpClaim.get(COL_NM_SL.INV_LINE_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.INV_LINE_SUB_NUM.name()).toString() + 
                        "," + csmpClaim.get(COL_NM_SL.INV_LINE_SUB_TRX_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.CPO_ORD_NUM.name()).toString() + 
                        "," + castToString(csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_NUM.name())) + "," + castToString(csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_SUB_NUM.name())) + 
                        "," + ERR_MSG.INSTLATION_ERRO.getValue() + csmpClaim.get(COL_NM_SL.MDSE_CD.name()).toString());
            }

//QC#24677 del Start
//            //(6).Get After Market Items
//            aftrMrktItems = new ArrayList<String>();
//            if (blSetItem) {
//                // After Market Item
//                aftrMrktItems = getSetAftrMrktItemList(csmpClaim);
//            } else {
//                aftrMrktItems.add( getAftrMrktItem((String) csmpClaim.get(COL_NM_SL.MDSE_CD.name()), csmpClaim) );
//            }
//QC#24677 del End

            //(7).Replace the price list
            prcCatgCd = getReplacePriceCategoryCode(prcCatgNm, (String) csmpClaim.get(COL_NM_SL.INV_DT.name()),  (String) csmpClaim.get(COL_NM_SL.CSMP_PRC_LIST_CD.name()));
            // Save this Price Category
            //QC#12252-Rev2
            //csmpClaim.put(COL_NM_SL.PRC_CATG_CD.name(), prcCatgCd);
            //csmpClaim.put(COL_NM_SL.PRC_CATG_CD_SAVE.name(), prcCatgCd);
            //QC#12252-Rev2
//Add Start QC#12252
            if (!ZYPCommonFunc.hasValue((String) csmpClaim.get(COL_NM_SL.CSMP_PRC_LIST_CD.name()))) {
                csmpClaim.put(COL_NM_SL.CSMP_PRC_LIST_CD.name(), prcCatgCd);
            }
//Add End   QC#12252
            
            //QC#12252-Rev2
            csmpClaim.put(COL_NM_SL.CSMP_PRC_LIST_CD_SAVE.name(), prcCatgCd);
            //QC#12252-Rev2

            //(8).Pricing API check Mode=2
            prcApiPMsg = new NWZC157001PMsg();
            String trxLineNum = DEF_VAL.TRX_LINE_NUM_1.getValue();
//Mod Start QC#12823
            prcApiPMsg = isPricingMode2(csmpClaim, trxLineNum, prcApiPMsg, 0);
//Mod End   QC#12823
            new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.BATCH);
            if (isErrorMsg(prcApiPMsg)) {

                StringBuilder errMsg = new StringBuilder();
                errMsg.append("CSMP Price could not get : Price Category = ");
                errMsg.append(castToString(csmpClaim.get(COL_NM_SL.CSMP_PRC_LIST_CD.name())));
                errMsg.append(" Merchandise = ");
                errMsg.append(castToString(csmpClaim.get(COL_NM_SL.MDSE_CD.name())));
                errProcess(csmpClaim, errMsg.toString());
                errMsgList.add( csmpClaim.get(COL_NM_SL.INV_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.INV_BOL_LINE_NUM.name()).toString() +
                        "," + csmpClaim.get(COL_NM_SL.INV_LINE_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.INV_LINE_SUB_NUM.name()).toString() + 
                        "," + csmpClaim.get(COL_NM_SL.INV_LINE_SUB_TRX_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.CPO_ORD_NUM.name()).toString() + 
                        "," + castToString(csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_NUM.name())) + "," + castToString(csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_SUB_NUM.name())) + 
                        "," + "Pricing API Error : Merchandise Code : " + csmpClaim.get(COL_NM_SL.MDSE_CD.name()).toString());
                continue;
            }

            //(9).Get Argument for Import Invoice API
            //(9-1). Get Line Information
            List<Map> lineList = null;
            if (!csmpClaim.get(COL_NM_SL.INV_LINE_SUB_NUM.name()).toString().equals(DEF_VAL.INV_LINE_SUB_NUM_000.getValue())) {
                lineList = new ArrayList<Map>();
            } else {
                lineList = addLineList(csmpClaim);
            }
            //(9-2). Get Serial Number
            String serNum = getSerNum(csmpClaim);
            //(9-3). Get Sales Credit
            List<Map> slsCrdt = getSlsCreditList(csmpClaim);

            //QC#24677 add Start
            
            //QC#24677 add End

            if (blCreateInv) {
                //(10).Set Import Invoice API Parameters.
//Del Start QC#12823
//                dsInvTpTMsg = new DS_INV_TPTMsg();
//                ZYPEZDItemValueSetter.setValue(dsInvTpTMsg.glblCmpyCd, this.glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(dsInvTpTMsg.dsInvTpCd, varCharDsInvTpCd);
//                dsInvTpTMsg = (DS_INV_TPTMsg) S21CacheTBLAccessor.findByKey(dsInvTpTMsg);
//                String invNum = ZYPExtnNumbering.getUniqueID(glblCmpyCd, dsInvTpTMsg.autoSeqCd.getValue());
//Del End   QC#12823

                //Base Information
                int invBolLineNum = iBolCnt + 1;
                String invLineSub = csmpClaim.get(COL_NM_SL.INV_LINE_SUB_NUM.name()).toString();
                //☆INV_LINE_SUB_NUM Setting
                //if (blSetItem) {
                //    invLineSub = DEF_VAL.INV_LINE_SUB_NUM_000.getValue();
                //} else {
                //    invLineSub = DEF_VAL.INV_LINE_SUB_NUM_001.getValue();
                //}
                //BigDecimal lineDealAmt = (BigDecimal) csmpClaim.get(COL_NM_SL.INV_LINE_DEAL_NET_AMT.name());
                //BigDecimal lineFuncAmt = calcFuncAmt(this.glblCmpyCd, CCY.US_DOLLAR, slsDt, lineDealAmt);
                BigDecimal lineDealAmt = (BigDecimal) prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(0).calcPrcAmtRate.getValue();
                //BigDecimal lineFuncAmt = calcFuncAmt(this.glblCmpyCd, CCY.US_DOLLAR, slsDt, lineDealAmt);
                calcFuncAmt(this.glblCmpyCd, CCY.US_DOLLAR, slsDt, lineDealAmt);
                // QC#12823 MOD Start
                if (iLineCnt == 0) {
                    impInvHdrPMsgList.add(setImpInvValForHdr(csmpClaim, invNum));
                    impInvShipPMsgList.add(setImpInvValForShipment(csmpClaim, invNum, Integer.toString(invBolLineNum)));
                }
                // QC#12823 MOD End
                //QC#24677 mod Start
//                impInvLinePMsgList.add(setImpInvValForLine(csmpClaim, invNum, Integer.toString(invBolLineNum), Integer.toString(iLineCnt+1), invLineSub, prcApiPMsg));
                NWZC040003PMsg invLinePMsg = setImpInvValForLine(csmpClaim, invNum, Integer.toString(invBolLineNum), Integer.toString(iLineCnt+1), invLineSub, prcApiPMsg);
                impInvLinePMsgList.add(invLinePMsg);
                //QC#24677 mod End
                if ( serNum != null ) {
                    impInvSerPMsgList.add(setImpInvValForSer(invNum, Integer.toString(invBolLineNum), Integer.toString(iLineCnt+1), invLineSub, serNum));
                }

//QC#24675 mod Start
//                dealSlsCrTotAmt = BigDecimal.ZERO;
//                funcSlsCrTotAmt = BigDecimal.ZERO;
//                dealSlsMaxRow = 0;
//                funcSlsMaxRow = 0;
                BigDecimal dealMaxAmt = BigDecimal.ZERO;
//                int iSlsCnt = 0;
                
                NWZC040007PMsg invSlsCrPMsg;
                List<NWZC040007PMsg> invSlsCrPMsgList = new ArrayList<NWZC040007PMsg>();
                BigDecimal totInvSlsCrAmt = BigDecimal.ZERO;
                BigDecimal invSlsCrAmt = BigDecimal.ZERO;
                int maxAmtIdx = 0;
                for ( Map<String, Object> slsCrdtLine : slsCrdt ) {
//                    impInvSlsCrMsgList.add(setImpInvValForSlsCr(Integer.toString(invBolLineNum), Integer.toString(iLineCnt+1), invLineSub, invNum, slsCrdtLine, prcApiPMsg, 1));
                    invSlsCrPMsg = setImpInvValForSlsCr(Integer.toString(invBolLineNum), Integer.toString(iLineCnt+1), invLineSub, invNum, slsCrdtLine, prcApiPMsg, 1, invLinePMsg);
                    invSlsCrPMsgList.add(invSlsCrPMsg);
                    invSlsCrAmt = invSlsCrPMsg.dealSlsCrAmt.getValue();
                    totInvSlsCrAmt = totInvSlsCrAmt.add(invSlsCrAmt);
                    
//                    if ( impInvSlsCrMsgList.get(iSlsCnt).dealSlsCrAmt.getValue().compareTo(dealMaxAmt) >= 0  ) {
//                        dealSlsMaxRow = iSlsCnt;
//                        funcSlsMaxRow = iSlsCnt;
//                        dealMaxAmt = impInvSlsCrMsgList.get(iSlsCnt).dealSlsCrAmt.getValue();
//                    }
                    if (invSlsCrAmt.compareTo(dealMaxAmt) >= 0 ) {
                        maxAmtIdx = slsCrdt.indexOf(slsCrdtLine);
                        dealMaxAmt = invSlsCrAmt;
                    }

//                    iSlsCnt++;
                }
//                ZYPEZDItemValueSetter.setValue(impInvSlsCrMsgList.get(dealSlsMaxRow).dealSlsCrAmt,  ((BigDecimal)impInvSlsCrMsgList.get(dealSlsMaxRow).dealSlsCrAmt.getValue()).add((lineDealAmt.subtract(dealSlsCrTotAmt))));
//                ZYPEZDItemValueSetter.setValue(impInvSlsCrMsgList.get(funcSlsMaxRow).funcSlsCrAmt,  ((BigDecimal)impInvSlsCrMsgList.get(funcSlsMaxRow).funcSlsCrAmt.getValue()).add((lineFuncAmt.subtract(funcSlsCrTotAmt))));
                BigDecimal diffAmt = lineDealAmt.subtract(totInvSlsCrAmt);
                if (diffAmt.compareTo(BigDecimal.ZERO) != 0) {
                    NWZC040007PMsg slsCrPMsg = invSlsCrPMsgList.get(maxAmtIdx);
                    BigDecimal dealSlsCrAmt = dealMaxAmt.add(diffAmt);
                    ZYPEZDItemValueSetter.setValue(slsCrPMsg.dealSlsCrAmt, dealSlsCrAmt);
                    ZYPEZDItemValueSetter.setValue(slsCrPMsg.funcSlsCrAmt, dealSlsCrAmt);
                }
                impInvSlsCrMsgList.addAll(invSlsCrPMsgList);
 //QC#24675 mod End

                //Parents information
                invNumList.add(csmpClaim.get(COL_NM_SL.INV_NUM.name()).toString());
                invBolNumList.add(csmpClaim.get(COL_NM_SL.INV_BOL_LINE_NUM.name()).toString());
                invLineNumList.add(csmpClaim.get(COL_NM_SL.INV_LINE_NUM.name()).toString());
                invLineSubNumList.add(csmpClaim.get(COL_NM_SL.INV_LINE_SUB_NUM.name()).toString());
                invLineSubTrxNumList.add(csmpClaim.get(COL_NM_SL.INV_LINE_SUB_TRX_NUM.name()).toString());

                //Additional Line Information for Set Items
                String strSetChilCnt = "";
                int iSetChilCnt = 1;
                List<NWZC040003PMsg> cmptInvLinePMsgList = new ArrayList<NWZC040003PMsg>();  //QC#24677 add
                for ( Map<String, Object> line : lineList ) {
                    //(9-2)'. Get Serial Number
                    serNum = getSerNumForLine(line);
                    //(9-3)'. Get Sales Credit
                    slsCrdt = getSlsCreditListForLine(line);
                    
                    //lineDealAmt = BigDecimal.ZERO;
                    //lineFuncAmt = BigDecimal.ZERO;

                    //Line Sub Number Setting
                    strSetChilCnt = line.get(COL_NM_SL.INV_LINE_SUB_NUM.name()).toString();
                    //QC#24677 mod Start
//                    impInvLinePMsgList.add(setImpInvValForLineForLine(line, invNum, Integer.toString(invBolLineNum), Integer.toString(iLineCnt+1), strSetChilCnt, prcApiPMsg, (String) csmpClaim.get(COL_NM_SL.UOM_CD.name()), (String) csmpClaim.get(COL_NM_SL.CSMP_PRC_LIST_CD_SAVE.name())));
                    NWZC040003PMsg cmptInvLinePMsg = setImpInvValForLineForLine(line, invNum, Integer.toString(invBolLineNum), Integer.toString(iLineCnt+1), strSetChilCnt, prcApiPMsg, (String) csmpClaim.get(COL_NM_SL.UOM_CD.name()), (String) csmpClaim.get(COL_NM_SL.CSMP_PRC_LIST_CD_SAVE.name()));
                    impInvLinePMsgList.add(cmptInvLinePMsg);
                    cmptInvLinePMsgList.add(cmptInvLinePMsg);
                    //QC#24677 mod End
                    if ( serNum != null ) {
                        impInvSerPMsgList.add(setImpInvValForSer(invNum, Integer.toString(invBolLineNum), Integer.toString(iLineCnt+1), strSetChilCnt, serNum));
                    }
                    for ( Map<String, Object> slsCrdtLine : slsCrdt ) {
                    //    dealSlsCrTotAmt = BigDecimal.ZERO;
                    //    funcSlsCrTotAmt = BigDecimal.ZERO;
                    //    dealSlsMaxRow = 0;
                    //    funcSlsMaxRow = 0;
                        //QC#24675 mod Start
//                        impInvSlsCrMsgList.add(setImpInvValForSlsCrOfLine(Integer.toString(invBolLineNum), Integer.toString(iLineCnt+1), strSetChilCnt, invNum, slsCrdtLine, prcApiPMsg, iSetChilCnt));
                        invSlsCrPMsg = setImpInvValForSlsCrOfLine(Integer.toString(invBolLineNum), Integer.toString(iLineCnt+1), strSetChilCnt, invNum, slsCrdtLine, prcApiPMsg, iSetChilCnt);
                        impInvSlsCrMsgList.add(invSlsCrPMsg);
                        invSlsCrPMsgList.add(invSlsCrPMsg);
                        //QC#24675 mod End
                    }
                    //ZYPEZDItemValueSetter.setValue(impInvSlsCrMsgList.get(dealLinePrcDiffRow-1).dealSlsCrAmt, impInvSlsCrMsgList.get(dealLinePrcDiffRow-1).dealSlsCrAmt.getValue().add(dealLinePrcMaxDiffAmt));
                    //ZYPEZDItemValueSetter.setValue(impInvSlsCrMsgList.get(funcLinePrcDiffRow-1).funcSlsCrAmt, impInvSlsCrMsgList.get(funcLinePrcDiffRow-1).funcSlsCrAmt.getValue().add(funcLinePrcMaxDiffAmt));
                    iSetChilCnt++;

                    //Parents information
                    invNumList.add(line.get(COL_NM_SL.INV_NUM.name()).toString());
                    invBolNumList.add(line.get(COL_NM_SL.INV_BOL_LINE_NUM.name()).toString());
                    invLineNumList.add(line.get(COL_NM_SL.INV_LINE_NUM.name()).toString());
                    invLineSubNumList.add(line.get(COL_NM_SL.INV_LINE_SUB_NUM.name()).toString());
                    invLineSubTrxNumList.add(line.get(COL_NM_SL.INV_LINE_SUB_TRX_NUM.name()).toString());
//Del Start QC#12442
//                    invBolLineNum ++;
//Del End   QC#12442
                }
//QC#24677 add Start
                //After Market Item
                NWZC040003PMsg aftInvLinePMsg;
                List<NWZC040007PMsg> aftInvSlsCrPMsgList;
                if (!csmpClaim.get(COL_NM_SL.INV_LINE_SUB_NUM.name()).toString().equals(DEF_VAL.INV_LINE_SUB_NUM_000.getValue())) {
                    // Regular Item
                    aftInvLinePMsg = getInvLineParamForAfterMarketItem(csmpClaim, invLinePMsg, false);

                    if (aftInvLinePMsg == null) {
                        blImportInv = false;
                        blImportInvErr = true;
                        continue;

                    } else if (ZYPCommonFunc.hasValue(aftInvLinePMsg.mdseCd.getValue())) {

                        aftMrktItemInvLineList.add(aftInvLinePMsg);
                        aftInvSlsCrPMsgList = getInvSlsCrParamForAfterMarketItem(aftInvLinePMsg, invSlsCrPMsgList);
                        aftMrktItemInvSlsCrList.addAll(aftInvSlsCrPMsgList);
                    }
                } else {
                    //Set Component
                    if (blSetItem) {
                        for (NWZC040003PMsg cmptInvLinePMsg : cmptInvLinePMsgList) {
                            aftInvLinePMsg = getInvLineParamForAfterMarketItem(csmpClaim, cmptInvLinePMsg, true);

                            if (aftInvLinePMsg == null) {
                                blImportInv = false;
                                blImportInvErr = true;
                                break;
                            } else if (ZYPCommonFunc.hasValue(aftInvLinePMsg.mdseCd.getValue())) {
                                String bolLineNum = aftInvLinePMsg.invBolLineNum.getValue();
                                String lineNum = aftInvLinePMsg.invLineNum.getValue();
                                String lineSubNum = aftInvLinePMsg.invLineSubNum.getValue();

                                aftMrktItemInvLineList.add(aftInvLinePMsg);
                                List<NWZC040007PMsg> slsCrPMsgList = new ArrayList<NWZC040007PMsg>();
                                for (NWZC040007PMsg pmsg : invSlsCrPMsgList) {
                                    if (bolLineNum.equals(pmsg.invBolLineNum.getValue()) 
                                            && lineNum.equals(pmsg.invLineNum.getValue()) 
                                            && lineSubNum.equals(pmsg.invLineSubNum.getValue())) {
                                        slsCrPMsgList.add(pmsg);
                                    }
                                }
                                aftInvSlsCrPMsgList = getInvSlsCrParamForAfterMarketItem(aftInvLinePMsg, slsCrPMsgList);
                                aftMrktItemInvSlsCrList.addAll(aftInvSlsCrPMsgList);
                            }
                        }
                        if (blImportInvErr) {
                            continue;
                        }
                    }
                }
//QC#24677 add End
                blImportInv = true;
            }
            iLineCnt ++;
        }
        
        return errMsgList;
    }
//QC#24677 add Start
    private NWZC040003PMsg getInvLineParamForAfterMarketItem(Map<String, Object> csmpClaimMap, NWZC040003PMsg invLinePMsg, boolean setItemFlg) {

        NWZC040003PMsg aftMrktItemInvLine = new NWZC040003PMsg();

        //(6).Get After Market Items
        String afterMarketItemCd = getAftrMrktItem(invLinePMsg.mdseCd.getValue(), csmpClaimMap, setItemFlg);
        if (afterMarketItemCd.length() == 0) {
            return aftMrktItemInvLine;
        }

        EZDMsg.copy(invLinePMsg, null, aftMrktItemInvLine, null);
        ZYPEZDItemValueSetter.setValue(aftMrktItemInvLine.mdseCd, afterMarketItemCd);

        //(8).Pricing API check Mode=2
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        prcApiPMsg = isPricingMode2(csmpClaimMap, DEF_VAL.TRX_LINE_NUM_1.getValue(), prcApiPMsg, 0);
        //set after market item code
        NWZC157002PMsg prcLineApiPMsg = prcApiPMsg.NWZC157002PMsg.no(0);
        ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.mdseCd, aftMrktItemInvLine.mdseCd);

        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.BATCH);
        if (isErrorMsg(prcApiPMsg)) {

            StringBuilder errMsg = new StringBuilder();
            errMsg.append("CSMP Price could not get : Price Category = ");
            errMsg.append(castToString(csmpClaimMap.get(COL_NM_SL.CSMP_PRC_LIST_CD.name())));
            errMsg.append(" Merchandise = ");
            errMsg.append(castToString(csmpClaimMap.get(COL_NM_SL.MDSE_CD.name())));
            errProcess(csmpClaimMap, errMsg.toString());
            errMsgList.add( csmpClaimMap.get(COL_NM_SL.INV_NUM.name()).toString() + "," + csmpClaimMap.get(COL_NM_SL.INV_BOL_LINE_NUM.name()).toString() +
                    "," + csmpClaimMap.get(COL_NM_SL.INV_LINE_NUM.name()).toString() + "," + csmpClaimMap.get(COL_NM_SL.INV_LINE_SUB_NUM.name()).toString() + 
                    "," + csmpClaimMap.get(COL_NM_SL.INV_LINE_SUB_TRX_NUM.name()).toString() + "," + csmpClaimMap.get(COL_NM_SL.CPO_ORD_NUM.name()).toString() + 
                    "," + castToString(csmpClaimMap.get(COL_NM_SL.CPO_DTL_LINE_NUM.name())) + "," + castToString(csmpClaimMap.get(COL_NM_SL.CPO_DTL_LINE_SUB_NUM.name())) + 
                    "," + "Pricing API Error : Merchandise Code : " + csmpClaimMap.get(COL_NM_SL.MDSE_CD.name()).toString());
            return null;
        }
        BigDecimal lineDealAmt = (BigDecimal) prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(0).calcPrcAmtRate.getValue();
        BigDecimal unitPrcAmt = (BigDecimal) prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(0).autoPrcAmtRate.getValue();
//        BigDecimal lineFuncAmt = calcFuncAmt(this.glblCmpyCd, CCY.US_DOLLAR, slsDt, lineDealAmt);
//        aftMrktItemInvLine = setImpInvValForLine(csmpClaimMap, invLinePMsg.invNum.getValue(), invLinePMsg.invBolLineNum.getValue(), "1", "001", prcApiPMsg);

        aftMrktItemInvLine.setMdseCd.clear();
        ZYPEZDItemValueSetter.setValue(aftMrktItemInvLine.dealNetUnitPrcAmt, unitPrcAmt);
        ZYPEZDItemValueSetter.setValue(aftMrktItemInvLine.invLineDealNetAmt, lineDealAmt);
        ZYPEZDItemValueSetter.setValue(aftMrktItemInvLine.dealGrsUnitPrcAmt, unitPrcAmt);
        ZYPEZDItemValueSetter.setValue(aftMrktItemInvLine.dealGrsTotPrcAmt,  lineDealAmt);
        // 2018/03/29 QC#24677-2 add start
        ZYPEZDItemValueSetter.setValue(aftMrktItemInvLine.baseCmptFlg,  ZYPConstant.FLG_OFF_N);
        // 2018/03/29 QC#24677-2 add end

        return aftMrktItemInvLine;
    }

    private List<NWZC040007PMsg> getInvSlsCrParamForAfterMarketItem(NWZC040003PMsg invLinePMsg, List<NWZC040007PMsg> invSlsCrPMsgList) {

        List<NWZC040007PMsg> aftInvSlsCrPMsgList = new ArrayList<NWZC040007PMsg>();

        NWZC040007PMsg aftInvSlsCrPMsg;
        BigDecimal dealNetAmt = invLinePMsg.invLineDealNetAmt.getValue();
        BigDecimal dealSlsCrAmt;
        BigDecimal totalDealSlsCrAmt = BigDecimal.ZERO;
        BigDecimal maxAmt = BigDecimal.ZERO;
        int maxAmtIdx = 0;
        for (NWZC040007PMsg invSlsCrPMsg : invSlsCrPMsgList) {
            aftInvSlsCrPMsg = new NWZC040007PMsg();
            EZDMsg.copy(invSlsCrPMsg, null, aftInvSlsCrPMsg, null);

            ZYPEZDItemValueSetter.setValue(aftInvSlsCrPMsg.mdseCd, invLinePMsg.mdseCd.getValue());

            if (ZYPCommonFunc.hasValue(invLinePMsg.setMdseCd)) {
                dealSlsCrAmt = BigDecimal.ZERO;
            } else {
                dealSlsCrAmt = dealNetAmt.multiply(aftInvSlsCrPMsg.invLineSplPct.getValue()).divide(BigDecimal.valueOf(100))
                                    .multiply(aftInvSlsCrPMsg.slsRepCrPct.getValue()).divide(BigDecimal.valueOf(100))
                                    .setScale(2,BigDecimal.ROUND_HALF_UP);
                totalDealSlsCrAmt = totalDealSlsCrAmt.add(dealSlsCrAmt);
            }
            if (maxAmt.compareTo(dealSlsCrAmt) < 0) {
                maxAmt = dealSlsCrAmt;
                maxAmtIdx = invSlsCrPMsgList.indexOf(invSlsCrPMsg);
            }

            ZYPEZDItemValueSetter.setValue(aftInvSlsCrPMsg.dealSlsCrAmt, dealSlsCrAmt);
            ZYPEZDItemValueSetter.setValue(aftInvSlsCrPMsg.funcSlsCrAmt, dealSlsCrAmt);
            aftInvSlsCrPMsgList.add(aftInvSlsCrPMsg);
        }

        BigDecimal diffAmt = dealNetAmt.subtract(totalDealSlsCrAmt);
        if (diffAmt.compareTo(BigDecimal.ZERO) != 0) {
            aftInvSlsCrPMsg = aftInvSlsCrPMsgList.get(maxAmtIdx);
            dealSlsCrAmt = aftInvSlsCrPMsg.dealSlsCrAmt.getValue().add(diffAmt);
            ZYPEZDItemValueSetter.setValue(aftInvSlsCrPMsg.dealSlsCrAmt, dealSlsCrAmt);
            ZYPEZDItemValueSetter.setValue(aftInvSlsCrPMsg.funcSlsCrAmt, dealSlsCrAmt);
        }
        
        return aftInvSlsCrPMsgList;
    }

    //QC#24677 add End

    @SuppressWarnings("unchecked")
    public boolean isSetItem( String ordTakeMdseCd ) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("ordTakeMdseCd", ordTakeMdseCd);
        List aftrMrktSetItem = this.ssmBatchClient.queryObjectList("findSetItem", queryParam);

        if ( aftrMrktSetItem != null && aftrMrktSetItem.size() > 0 ) {
            return true;
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    public String isCsmpContract(Map<String, Object> csmpClaim) {

        String sellTo = "";
        String dlrRefNum = "";
        String invDt = "";

        if (csmpClaim.get(COL_NM_SL.SELL_TO_CUST_CD.name()) != null) {
            sellTo = csmpClaim.get(COL_NM_SL.SELL_TO_CUST_CD.name()).toString();
        }
        if (csmpClaim.get(COL_NM_SL.DLR_REF_NUM.name()) != null) {
            dlrRefNum = csmpClaim.get(COL_NM_SL.DLR_REF_NUM.name()).toString();
        }
        if (csmpClaim.get(COL_NM_SL.INV_DT.name()) != null) {
            invDt = csmpClaim.get(COL_NM_SL.INV_DT.name()).toString();
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("dsAcctNum", sellTo);
        queryParam.put("dlrRefNum", dlrRefNum);
        queryParam.put("effFromDtFrom", invDt);
        queryParam.put("effFromDtTo", invDt);
        List<Map> csmpContract = this.ssmBatchClient.queryObjectList("findAftrMrktByItem", queryParam);
        // START 2017/12/05 E.Kameishi [QC#22921, MOD]
        if ( csmpContract == null || csmpContract.size() < 1 || csmpContract.size() > 1 || csmpContract.get(0).get(COL_NM_SL.CSMP_NUM.name()) == null ) {
            return "";
        }
        // END 2017/12/05 E.Kameishi [QC#22921, MOD]

        return csmpContract.get(0).get(COL_NM_SL.CSMP_NUM.name()).toString();
    }

    // Add Start 2018/01/05 QC#22371
    /**
     * @param csmpClaim
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    private boolean isCsmpExclude(Map<String, Object> csmpClaim) {
        boolean result = false;
        String shipFromInvtyLocCd = (String) csmpClaim.get(COL_NM_SL.SHIP_FROM_INVTY_LOC_CD.name());

        if (!ZYPCommonFunc.hasValue(shipFromInvtyLocCd)) {
            return false;
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invtyLocCd", shipFromInvtyLocCd);
        List<Map<String, Object>> csmpExclFlgList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCsmpExclFlg", queryParam);

        if (csmpExclFlgList == null || csmpExclFlgList.size() < 1) {
            result = false;
        } else {
            String csmpExclFlg = (String) csmpExclFlgList.get(0).get(COL_NM_SL.CSMP_EXCL_FLG.name());
            if (ZYPConstant.FLG_ON_Y.equals(csmpExclFlg)) {
                result = true;
            } else {
                result = false;
            }
        }

        return result;
    }
    // Add End 2018/01/05 QC#22371

    @SuppressWarnings("unchecked")
    private String isPricingMode1(Map<String, Object> csmpClaim) {

        try {
            NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, this.slsDt);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.CSMP_CREDIT);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, (String) csmpClaim.get(COL_NM_SL.LINE_BIZ_TP_CD.name()));
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, (String) csmpClaim.get(COL_NM_SL.SELL_TO_CUST_CD.name()));
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.csmpNum, (String) csmpClaim.get(COL_NM_SL.CSMP_CONTR_NUM.name()));
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.dlrRefNum, (String) csmpClaim.get(COL_NM_SL.DLR_REF_NUM.name()));
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);

            new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.BATCH);

            if (isErrorMsg(prcApiPMsg)) {
                skipProcess(csmpClaim);
                return PRC_CD.ERROR.getValue();
            }

            if (prcApiPMsg.xxPrcList == null || prcApiPMsg.xxPrcList.getValidCount() == 0 || prcApiPMsg.xxPrcList.getValidCount() > 1 ) {
                skipProcess(csmpClaim);
                errMsgList.add( csmpClaim.get(COL_NM_SL.INV_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.INV_BOL_LINE_NUM.name()).toString() +
                        "," + csmpClaim.get(COL_NM_SL.INV_LINE_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.INV_LINE_SUB_NUM.name()).toString() + 
                        "," + csmpClaim.get(COL_NM_SL.INV_LINE_SUB_TRX_NUM.name()).toString() + "," + csmpClaim.get(COL_NM_SL.CPO_ORD_NUM.name()).toString() + 
                        "," + castToString(csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_NUM.name())) + "," + castToString(csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_SUB_NUM.name())) + 
                        "," + ERR_MSG.CONTRACT_ERROR.getValue() + csmpClaim.get(COL_NM_SL.MDSE_CD.name()).toString());
                return PRC_CD.ERROR.getValue();
            }
            csmpClaim.put(COL_NM_SL.CSMP_PRC_LIST_CD.name(), prcApiPMsg.xxPrcList.no(0).prcCatgCd.getValue());
            csmpClaim.put(COL_NM_SL.CSMP_PRC_LIST_CD_SAVE.name(), prcApiPMsg.xxPrcList.no(0).prcCatgCd.getValue());

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("prcCatgCd", prcApiPMsg.xxPrcList.no(0).prcCatgCd.getValue().toString());
            queryParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
            queryParam.put("delFlg", ZYPConstant.FLG_OFF_N);
            queryParam.put("effFromDtFrom", (String) csmpClaim.get(COL_NM_SL.INV_DT.name()));
            queryParam.put("effFromDtTo", (String) csmpClaim.get(COL_NM_SL.INV_DT.name()));
            List<Map> csmpPriceListName = (List<Map>) this.ssmBatchClient.queryObjectList("getCsmpPriceListName", queryParam);

            if ( csmpPriceListName != null && csmpPriceListName.size() != 1 ) {
//Mod Start QC#12252
                errProcess(csmpClaim, "Can not get CSMP Price List.");
//Mod End   QC#12252
                return PRC_CD.ERROR.getValue();
            }
            
            return csmpPriceListName.get(0).get(COL_NM_SL.PRC_CATG_NM.name()).toString();

        } finally {
        }
    }

    private boolean isErrorMsg(NWZC157001PMsg pMsg) {


        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
        for (S21ApiMessage msg : msgList) {
            String msgId = msg.getXxMsgid();
            if (ZYPCommonFunc.hasValue(msgId)) {
                if (msgId.endsWith("E")) {
System.out.println("Level-1");
System.out.println(msgId);
System.out.println(S21MessageFunc.clspGetMessage(msgId));
                    return true;
                }
            }
        }

        for (int i = 0; i < pMsg.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg prcLine = pMsg.NWZC157002PMsg.no(i);

            List<S21ApiMessage> msgListIn = S21ApiUtil.getXxMsgList(prcLine);
            for (S21ApiMessage msg : msgListIn) {
                String msgId = msg.getXxMsgid();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    if (msgId.endsWith("E")) {
System.out.println("Level-2");
System.out.println(msgId);
System.out.println(S21MessageFunc.clspGetMessage(msgId));
                        return true;
                    }
                }
            }

            for (int i2 = 0; i2 < prcLine.NWZC157003PMsg.getValidCount(); i2++ ) {
                NWZC157003PMsg prcLine2 = prcLine.NWZC157003PMsg.no(i2);

                List<S21ApiMessage> msgListIn2 = S21ApiUtil.getXxMsgList(prcLine2);
                for (S21ApiMessage msg2 : msgListIn2) {
                    String msgId2 = msg2.getXxMsgid();
                    if (ZYPCommonFunc.hasValue(msgId2)) {
                        if (msgId2.endsWith("E")) {
System.out.println("Level-3");
System.out.println(msgId2);
System.out.println(S21MessageFunc.clspGetMessage(msgId2));
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    private String getSpclCsmpExclArNm (Map<String, Object> csmpClaim) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("prcCatgCd", (String) csmpClaim.get(COL_NM_SL.CSMP_PRC_LIST_CD.name()));
        queryParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        queryParam.put("effFromDtFrom", (String) csmpClaim.get(COL_NM_SL.INV_DT.name()));
        queryParam.put("effFromDtTo", (String) csmpClaim.get(COL_NM_SL.INV_DT.name()));
        List<Map> spclCsmpExclArNm = (List<Map>) this.ssmBatchClient.queryObjectList("getSpclCsmpExclArNm", queryParam);

        if ( spclCsmpExclArNm == null || spclCsmpExclArNm.size() <= 0 ) {
            return PRC_CD.ERROR.getValue();
        }

        if ( spclCsmpExclArNm != null && spclCsmpExclArNm.size() > 0 ) {

            if (spclCsmpExclArNm.get(0).get(COL_NM_SL.SPCL_CSMP_EXCL_AR_NM.name()) != null
                    && spclCsmpExclArNm.get(0).get(COL_NM_SL.SPCL_CSMP_EXCL_AR_NM.name()) != "") {
                 return PRC_CD.ERROR.getValue();
            }
        }

        return PRC_CD.SUCCESS.getValue();
    }

    @SuppressWarnings("unchecked")
    private String getSvcMachMstrForIstlDt (Map<String, Object> csmpClaim) {
        // QC#12606 MOD Start
        if (!ZYPCommonFunc.hasValue((BigDecimal) csmpClaim.get(COL_NM_SL.SVC_CONFIG_MSTR_PK.name()))) {
            return PRC_CD.SUCCESS.getValue();
        }
        // QC#12606 MOD End

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("svcConfigMstrPk", (BigDecimal) csmpClaim.get(COL_NM_SL.SVC_CONFIG_MSTR_PK.name()));
        List<Map> svcMachMstr = (List<Map>) this.ssmBatchClient.queryObjectList("getSvcMachMstr", queryParam);

        if ( svcMachMstr == null || svcMachMstr.size() <= 0 ) {
            return PRC_CD.ERROR.getValue();
        }

        // QC#12246 MOD Start
        //if (svcMachMstr.get(0).get(COL_NM_SL.ISTL_DT.name()) != null) {
        if (!ZYPCommonFunc.hasValue((String) svcMachMstr.get(0).get(COL_NM_SL.ISTL_DT.name()))) {
        // QC#12246 MOD End
            return PRC_CD.ERROR.getValue();
        }

        return PRC_CD.SUCCESS.getValue();
    }

//QC#24677 del Start
//    @SuppressWarnings("unchecked")
//    private List<String> getSetAftrMrktItemList(Map<String, Object> csmpClaim) {
//
//        List<String> aftrMrktItemList = new ArrayList<String>();
//
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put("glblCmpyCd", this.glblCmpyCd);
//        queryParam.put("invNum", (String) csmpClaim.get(COL_NM_SL.INV_NUM.name()));
//        queryParam.put("invBolLineNum", (String) csmpClaim.get(COL_NM_SL.INV_BOL_LINE_NUM.name()));
//        queryParam.put("invLineNUm", (String) csmpClaim.get(COL_NM_SL.INV_LINE_NUM.name()));
//        //queryParam.put("ordTakeMdseCd", (String) csmpClaim.get(COL_NM_SL.MDSE_CD.name()));
//        List<Map> chkAftrMrktList = (List<Map>) this.ssmBatchClient.queryObjectList("getSetAftrMrktItemList", queryParam);
//
//        if ( chkAftrMrktList == null || chkAftrMrktList.size() < 1 ) {
//            return null;
//        } else {
//
//            String aftrMrktItem = "";
//            for (Map<String, Object> chkAftrMrkt : chkAftrMrktList ) {
//                //
////                aftrMrktItem = getAftrMrktItem((String) chkAftrMrkt.get((String) chkAftrMrkt.get(COL_NM_SL.MDSE_CD.name())), csmpClaim);
//                aftrMrktItem = getAftrMrktItem((String) chkAftrMrkt.get(COL_NM_SL.MDSE_CD.name()), csmpClaim);
//                
////                if (aftrMrktItem != "") {
//                if (aftrMrktItem.length() != 0) {
//                    aftrMrktItemList.add(aftrMrktItem);
//                }
//            }
//        }
//        
//        return aftrMrktItemList;
//    }
//QC#24677 del End

    @SuppressWarnings("unchecked")
    private String getAftrMrktItem( String mdseCd, Map<String, Object> csmpClaim, boolean setItemFlg ) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        //queryParam.put("invNum", (String) csmpClaim.get(COL_NM_SL.INV_NUM.name()));
        //queryParam.put("invBolLineNum", (String) csmpClaim.get(COL_NM_SL.INV_BOL_LINE_NUM.name()));
        //queryParam.put("invLineNUm", (String) csmpClaim.get(COL_NM_SL.INV_LINE_NUM.name()));
        if ( mdseCd == null  || mdseCd == "" ) {
            return "";
        }
//QC#24677 mod Start
//        queryParam.put("ordTakeMdseCd", mdseCd);
        String searchMdseCd;
        if (setItemFlg) {
            //Set Component
            //derive order take mdse in query
            searchMdseCd = mdseCd;
            queryParam.put("setItemFlg", "Y");

        } else {
            //Regular
            String ordTakeMdseCd = (String) csmpClaim.get((String) COL_NM_SL.ORD_TAKE_MDSE_CD.name());
            if (ordTakeMdseCd != null) {
                searchMdseCd = ordTakeMdseCd;
            } else {
                searchMdseCd = mdseCd;
            }
        }
        queryParam.put("mdseCd", searchMdseCd);
//QC#24677 mod End

        List<Map> aftrMrktItem = (List<Map>) this.ssmBatchClient.queryObjectList("getAftrMrktItem", queryParam);
        
        if ( aftrMrktItem == null || aftrMrktItem.size() < 1 || aftrMrktItem.size() > 1 ) {
            return "";
        } else {
            return aftrMrktItem.get(0).get((String) COL_NM_SL.CSMP_AFT_MKT_MDSE_CD.name()).toString();
        }
    }

    @SuppressWarnings("unchecked")
    private String getReplacePriceCategoryCode(String prcCatgNm, String invDt, String prcCatgCd) {

        //QC12252-Rev2 Start
        if (!prcCatgNm.startsWith(DEF_VAL.CSMP_SPEC_NAME_START.getValue())) {
            return prcCatgCd;
        }
        //QC12252-Rev2 End

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("prcCatgCd", this.varCharPrcList05);
        queryParam.put("actvFlg", FLG.Y.name());
        queryParam.put("delFlg", FLG.N.name());
        queryParam.put("invDt", invDt);
        List<Map> prcCatgCdList = (List<Map>) this.ssmBatchClient.queryObjectList("getReplacePriceCatg", queryParam);

        if ( prcCatgCdList == null || prcCatgCdList.size() < 1) {
            return prcCatgCd;
//Mod Start QC#12252
        } else if ( !prcCatgCdList.get(0).get(COL_NM_SL.PRC_CATG_CD.name()).toString().equals("") ) {
//Mod End   QC#12252
            return prcCatgCdList.get(0).get(COL_NM_SL.PRC_CATG_CD.name()).toString();
        } else {
            return prcCatgCd;
        }
    }

    private NWZC157001PMsg isPricingMode2( Map<String, Object> csmpClaim, String trxLineNum, NWZC157001PMsg prcApiPMsg, int iDtlCnt ) {

        try {

            //Header Information
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_BASE_PRICE);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, this.slsDt);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.CSMP_CREDIT);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, (String) csmpClaim.get(COL_NM_SL.LINE_BIZ_TP_CD.name()));
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, (String) csmpClaim.get(COL_NM_SL.SELL_TO_CUST_CD.name()));
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.csmpNum, (String) csmpClaim.get(COL_NM_SL.CSMP_CONTR_NUM.name()));
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.dlrRefNum, (String) csmpClaim.get(COL_NM_SL.DLR_REF_NUM.name()));
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);

            //Line Information (Add Line Information)
            NWZC157002PMsg prcLineApiPMsg = prcApiPMsg.NWZC157002PMsg.no(iDtlCnt);
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.trxLineNum, trxLineNum);
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.trxLineSubNum, DEF_VAL.TRX_LINE_SUB_NUM_001.getValue());
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.configCatgCd,  DEF_VAL.CONF_CATG_CD_LINE_CONF.getValue());
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.billToCustCd, varCharBillToCust);
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.shipToCustCd, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_CUST_CD.name()));
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.dsAcctNum_SH, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_CUST_ACCT_CD.name()));
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.dsAcctNum_BL, varCharBillToAcct);
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.prcCatgCd, (String) csmpClaim.get(COL_NM_SL.CSMP_PRC_LIST_CD.name()));
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.csmpNum, (String) csmpClaim.get(COL_NM_SL.CSMP_CONTR_NUM.name()));
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.dlrRefNum, (String) csmpClaim.get(COL_NM_SL.DLR_REF_NUM.name()));
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.ccyCd, (String) csmpClaim.get(COL_NM_SL.DEAL_CCY_CD.name()));
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.mdseCd, (String) csmpClaim.get(COL_NM_SL.MDSE_CD.name()));
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.pkgUomCd, (String) csmpClaim.get(COL_NM_SL.UOM_CD.name()));
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.ordQty, (BigDecimal) csmpClaim.get(COL_NM_SL.SHIP_QTY.name()));
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.ordCustUomQty, (BigDecimal) csmpClaim.get(COL_NM_SL.ORD_CUST_UOM_QTY.name()));
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.invQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.firstLineAddr_SH, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_FIRST_LINE_ADDR.name()));
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.scdLineAddr_SH, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_SCD_LINE_ADDR.name()));
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.ctyAddr_SH, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_CTY_ADDR.name()));
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.stCd_SH, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_ST_CD.name()));
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.cntyNm_SH, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_CNTY_NM.name()));
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.postCd_SH, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_POST_CD.name()));
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.ctryCd_SH, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_CTRY_CD.name()));
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.slsRepOrSlsTeamTocCd, (String) csmpClaim.get(COL_NM_SL.SLS_REP_TOC_CD.name()));
            ZYPEZDItemValueSetter.setValue(prcLineApiPMsg.xxPrcCloFlg, FLG.N.name());
            prcApiPMsg.NWZC157002PMsg.setValidCount(iDtlCnt+1);

            NWZC157003PMsg prcLinePriceApiPMsg = prcLineApiPMsg.NWZC157003PMsg.no(0);
            ZYPEZDItemValueSetter.setValue(prcLinePriceApiPMsg.trxLineNum, DEF_VAL.TRX_LINE_NUM_1.getValue());
            ZYPEZDItemValueSetter.setValue(prcLinePriceApiPMsg.trxLineSubNum, DEF_VAL.TRX_LINE_SUB_NUM_1.getValue());
            ZYPEZDItemValueSetter.setValue(prcLinePriceApiPMsg.configCatgCd, DEF_VAL.CONF_CATG_CD_O.getValue());
            ZYPEZDItemValueSetter.setValue(prcLinePriceApiPMsg.prcDtlGrpCd, DEF_VAL.PRC_DTL_GRP_CD_BASE.getValue());
            ZYPEZDItemValueSetter.setValue(prcLinePriceApiPMsg.prcCondManEntryFlg, FLG.N.name());
            ZYPEZDItemValueSetter.setValue(prcLinePriceApiPMsg.prcCondManAddFlg, FLG.N.name());
            ZYPEZDItemValueSetter.setValue(prcLinePriceApiPMsg.prcCondManDelFlg, FLG.N.name());
            ZYPEZDItemValueSetter.setValue(prcLinePriceApiPMsg.pkgUomCd, (String) csmpClaim.get(COL_NM_SL.UOM_CD.name()));
            ZYPEZDItemValueSetter.setValue(prcLinePriceApiPMsg.prcCondUnitCd, DEF_VAL.PRC_COND_UNIT_CD_AMT.getValue());
            ZYPEZDItemValueSetter.setValue(prcLinePriceApiPMsg.autoPrcCcyCd, (String) csmpClaim.get(COL_NM_SL.DEAL_CCY_CD.name()));
            ZYPEZDItemValueSetter.setValue(prcLinePriceApiPMsg.autoPrcAmtRate, (BigDecimal) csmpClaim.get(COL_NM_SL.DEAL_NET_UNIT_PRC_AMT.name()));
            ZYPEZDItemValueSetter.setValue(prcLinePriceApiPMsg.calcPrcAmtRate, ((BigDecimal) csmpClaim.get(COL_NM_SL.DEAL_NET_UNIT_PRC_AMT.name())).multiply((BigDecimal) csmpClaim.get(COL_NM_SL.SHIP_QTY.name())));
            ZYPEZDItemValueSetter.setValue(prcLinePriceApiPMsg.unitPrcAmt, (BigDecimal) csmpClaim.get(COL_NM_SL.DEAL_NET_UNIT_PRC_AMT.name()));

            return prcApiPMsg;

        } finally {
        }
    }

    @SuppressWarnings("unchecked")
    private List<Map> addLineList( Map<String, Object> csmpClaim ) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", (String) csmpClaim.get(COL_NM_SL.INV_NUM.name()));
        queryParam.put("invBolLineNum", (String) csmpClaim.get(COL_NM_SL.INV_BOL_LINE_NUM.name()));
        queryParam.put("invLineNum", (String) csmpClaim.get(COL_NM_SL.INV_LINE_NUM.name()));
        queryParam.put("invLineSubNum", DEF_VAL.INV_LINE_SUB_NUM_000.getValue());
        List<Map> getLineList = (List<Map>) this.ssmBatchClient.queryObjectList("getLineList", queryParam);

        if ( getLineList == null || getLineList.size() < 1 ) {
             return null;
        }

        return getLineList;
    }

    @SuppressWarnings("unchecked")
    private String getSerNum( Map<String, Object> csmpClaim ) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", (String) csmpClaim.get(COL_NM_SL.INV_NUM.name()));
        queryParam.put("invBolLineNum", (String) csmpClaim.get(COL_NM_SL.INV_BOL_LINE_NUM.name()));
        queryParam.put("invLineNum", (String) csmpClaim.get(COL_NM_SL.INV_LINE_NUM.name()));
        queryParam.put("invLineSubNum", (String) csmpClaim.get(COL_NM_SL.INV_LINE_SUB_NUM.name()));
        queryParam.put("invTrxLineSubNum", (String) csmpClaim.get(COL_NM_SL.INV_LINE_SUB_TRX_NUM.name()));
        List<Map> serNumList = (List<Map>) this.ssmBatchClient.queryObjectList("getSerNum", queryParam);

        if ( serNumList == null || serNumList.size() < 1) {
             return null;
        }
        
        return serNumList.get(0).get(COL_NM_SL.SER_NUM.name()).toString();
    }

    @SuppressWarnings("unchecked")
    private String getSerNumForLine( Map<String, Object> line ) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", (String) line.get(COL_NM_SL.INV_NUM.name()));
        queryParam.put("invBolLineNum", (String) line.get(COL_NM_SL.INV_BOL_LINE_NUM.name()));
        queryParam.put("invLineNum", (String) line.get(COL_NM_SL.INV_LINE_NUM.name()));
        queryParam.put("invLineSubNum", (String) line.get(COL_NM_SL.INV_LINE_SUB_NUM.name()));
        queryParam.put("invTrxLineSubNum", (String) line.get(COL_NM_SL.INV_LINE_SUB_TRX_NUM.name()));
        List<Map> serNumList = (List<Map>) this.ssmBatchClient.queryObjectList("getSerNum", queryParam);

        if ( serNumList == null || serNumList.size() < 1) {
             return null;
        }
        
        return serNumList.get(0).get(COL_NM_SL.SER_NUM.name()).toString();
    }

    @SuppressWarnings("unchecked")
    private List<Map> getSlsCreditList( Map<String, Object> csmpClaim) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", (String) csmpClaim.get(COL_NM_SL.INV_NUM.name()));
        queryParam.put("invBolLineNum", (String) csmpClaim.get(COL_NM_SL.INV_BOL_LINE_NUM.name()));
        queryParam.put("invLineNum", (String) csmpClaim.get(COL_NM_SL.INV_LINE_NUM.name()));
        queryParam.put("invLineSubNum", (String) csmpClaim.get(COL_NM_SL.INV_LINE_SUB_NUM.name()));
        queryParam.put("invTrxLineSubNum", (String) csmpClaim.get(COL_NM_SL.INV_LINE_SUB_TRX_NUM.name()));
        List<Map> slsCrdtList = (List<Map>) this.ssmBatchClient.queryObjectList("getSlsCreditList", queryParam);

        if ( slsCrdtList == null || slsCrdtList.size() < 1) {
             return null;
        }

        return slsCrdtList;
    }

    @SuppressWarnings("unchecked")
    private List<Map> getSlsCreditListForLine( Map<String, Object> line) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", (String) line.get(COL_NM_SL.INV_NUM.name()));
        queryParam.put("invBolLineNum", (String) line.get(COL_NM_SL.INV_BOL_LINE_NUM.name()));
        queryParam.put("invLineNum", (String) line.get(COL_NM_SL.INV_LINE_NUM.name()));
        queryParam.put("invLineSubNum", (String) line.get(COL_NM_SL.INV_LINE_SUB_NUM.name()));
        queryParam.put("invTrxLineSubNum", (String) line.get(COL_NM_SL.INV_LINE_SUB_TRX_NUM.name()));
        List<Map> slsCrdtList = (List<Map>) this.ssmBatchClient.queryObjectList("getSlsCreditList", queryParam);

        if ( slsCrdtList == null || slsCrdtList.size() < 1) {
             return null;
        }

        return slsCrdtList;
    }

    private NWZC040001PMsg setImpInvValForHdr(Map<String, Object> csmpClaim, String invNum) {

        //Header Parameter
        NWZC040001PMsg impInvApiHdrPMsg = new NWZC040001PMsg();
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.invNum, invNum);
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.invDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.acctDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.invTpCd, INV_TP.INVOICE.toString());
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.netDueDt, this.dueDate);
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.cpoOrdNum, (String) csmpClaim.get(COL_NM_SL.CPO_ORD_NUM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.ordDt, (String) csmpClaim.get(COL_NM_SL.ORD_DT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.custIssPoNum, (String) csmpClaim.get(COL_NM_SL.CUST_ISS_PO_NUM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.custIssPoDt, (String) csmpClaim.get(COL_NM_SL.CUST_ISS_PO_DT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.billToCustCd, varCharBillToCust);
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.sellToCustCd, (String) csmpClaim.get(COL_NM_SL.SELL_TO_CUST_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.sellToLocNm, (String) csmpClaim.get(COL_NM_SL.SELL_TO_LOC_NM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.sellToAddlLocNm, (String) csmpClaim.get(COL_NM_SL.SELL_TO_ADDL_LOC_NM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.sellToFirstLineAddr, (String) csmpClaim.get(COL_NM_SL.SELL_TO_FIRST_LINE_ADDR.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.sellToScdLineAddr, (String) csmpClaim.get(COL_NM_SL.SELL_TO_SCD_LINE_ADDR.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.sellToThirdLineAddr, (String) csmpClaim.get(COL_NM_SL.SELL_TO_THIRD_LINE_ADDR.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.sellToFrthLineAddr, (String) csmpClaim.get(COL_NM_SL.SELL_TO_FRTH_LINE_ADDR.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.sellToCtyAddr, (String) csmpClaim.get(COL_NM_SL.SELL_TO_CTY_ADDR.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.sellToCntyNm, (String) csmpClaim.get(COL_NM_SL.SELL_TO_CNTY_NM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.sellToProvNm, (String) csmpClaim.get(COL_NM_SL.SELL_TO_CNTY_NM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.sellToStCd, (String) csmpClaim.get(COL_NM_SL.SELL_TO_ST_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.sellToPostCd, (String) csmpClaim.get(COL_NM_SL.SELL_TO_POST_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.sellToCtryCd, (String) csmpClaim.get(COL_NM_SL.SELL_TO_CTRY_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.sellToFirstRefCmntTxt, (String) csmpClaim.get(COL_NM_SL.SELL_TO_FIRST_REF_CMNT_TXT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.sellToScdRefCmntTxt, (String) csmpClaim.get(COL_NM_SL.SELL_TO_SCD_REF_CMNT_TXT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.payerCustCd, this.varCharBillToCust);
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.pmtTermCd, this.pmtTermCd);
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.cashDiscTermCd, this.pmtTermCd);
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.slsAdminPsnCd, (String) csmpClaim.get(COL_NM_SL.SLS_ADMIN_PSN_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.invPrintStsCd, DEF_VAL.EXCLUDE_CD_0.getValue());
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.trxSrcTpCd, TRX_SRC_TP.WHOLE_SALES);
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.invMlSendStsCd, DEF_VAL.EXCLUDE_CD_0.getValue());
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.invEdiSendStsCd, DEF_VAL.EXCLUDE_CD_0.getValue());
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.invFaxSendStsCd, DEF_VAL.EXCLUDE_CD_0.getValue());
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.invEmlSendStsCd, DEF_VAL.EXCLUDE_CD_0.getValue());
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.sysSrcCd, SYS_SRC.S21_ORDER);
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.pmtTermCashDiscCd, this.varCharPmtTermCashDisc);
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.histCratCpltFlg, FLG.Y.toString());
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.dsOrdTpCd, (String) csmpClaim.get(COL_NM_SL.DS_ORD_TP_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.dsOrdRsnCd, (String) csmpClaim.get(COL_NM_SL.DS_ORD_RSN_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.dsInvTpCd, this.varCharDsInvTpCd);
        // 2017/12/12 QC#22813 mod start
        // ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.srcSysDocNum, invNum);
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.srcSysDocNum, (String) csmpClaim.get(COL_NM_SL.CPO_ORD_NUM.name()));
        // 2017/12/12 QC#22813 mod end
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.slsRepTocCd, this.prmrySlsRepTocCd);
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.itrlInvErrFlg, FLG.N.toString());
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.billToCustAcctCd, this.varCharBillToAcct);
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.soldToCustLocCd, (String) csmpClaim.get(COL_NM_SL.SOLD_TO_CUST_LOC_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.lineBizTpCd, (String) csmpClaim.get(COL_NM_SL.LINE_BIZ_TP_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.dsBizAreaCd, (String) csmpClaim.get(COL_NM_SL.DS_BIZ_AREA_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.dsOrdCatgCd, (String) csmpClaim.get(COL_NM_SL.DS_ORD_CATG_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.csmpOrigInvNum, (String) csmpClaim.get(COL_NM_SL.INV_NUM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.csmpInvClmStsCd, "1");
        ZYPEZDItemValueSetter.setValue(impInvApiHdrPMsg.ordDt, (String) csmpClaim.get(COL_NM_SL.ORD_DT.name()));

        return impInvApiHdrPMsg;
    }
    private NWZC040002PMsg setImpInvValForShipment( Map<String, Object> csmpClaim, String invNum, String invBolLineNum ) {
        // ------------------ Shipment ------------------
        NWZC040002PMsg impInvApiShipmentPMsg = new NWZC040002PMsg();
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.invNum, invNum);
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.invBolLineNum, ZYPCommonFunc.leftPad(invBolLineNum, 3, "0"));
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.shipToCustCd, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_CUST_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.dropShipFlg, (String) csmpClaim.get(COL_NM_SL.DROP_SHIP_FLG.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.shipToLocNm, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_LOC_NM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.shipToAddlLocNm, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_ADDL_LOC_NM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.shipToFirstLineAddr, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_FIRST_LINE_ADDR.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.shipToScdLineAddr, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_SCD_LINE_ADDR.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.shipToThirdLineAddr, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_THIRD_LINE_ADDR.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.shipToFrthLineAddr, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_FRTH_LINE_ADDR.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.shipToCtyAddr, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_FRTH_LINE_ADDR.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.shipToCntyNm, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_CNTY_NM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.shipToProvNm, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_PROV_NM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.shipToStCd, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_ST_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.shipToPostCd, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_POST_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.shipToCtryCd, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_CTRY_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.shipToFirstRefCmntTxt, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_FIRST_REF_CMNT_TXT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.shipToScdRefCmntTxt, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_SCD_REF_CMNT_TXT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.exptFlg, FLG.N.toString());
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.frtDealTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.frtFuncTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.frtTaxPct, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.shipDt, (String) csmpClaim.get(COL_NM_SL.SHIP_DT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiShipmentPMsg.shipToCustAcctCd, (String) csmpClaim.get(COL_NM_SL.SHIP_TO_CUST_ACCT_CD.name()));

        return impInvApiShipmentPMsg;
    }
    private NWZC040003PMsg setImpInvValForLine( Map<String, Object> csmpClaim, String invNum, String invBolLineNum, String invLineNum, String invLineSubNum, NWZC157001PMsg prcApiPMsg ) {
        // ------------------ Line ------------------ 
        NWZC040003PMsg impInvApiLinePMsg = new NWZC040003PMsg();
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.invNum, invNum);
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.invBolLineNum, ZYPCommonFunc.leftPad(invBolLineNum, 3, "0"));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.invLineNum, ZYPCommonFunc.leftPad(invLineNum, 5, "0"));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.invLineSubNum, ZYPCommonFunc.leftPad(invLineSubNum, 3, "0"));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.invLineSubTrxNum, DEF_VAL.INV_LINE_SUB_TRX_NUM.getValue());
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.stkStsCd, STK_STS.GOOD.toString());
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.slsRepTocCd, (String) csmpClaim.get(COL_NM_SL.SLS_REP_TOC_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.mdseCd, (String) csmpClaim.get(COL_NM_SL.MDSE_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.mdseNm, (String) csmpClaim.get(COL_NM_SL.MDSE_NM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.coaProdCd, (String) csmpClaim.get(COL_NM_SL.COA_PROD_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.trxCd, this.varCharTrxCd);
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.trxRsnCd, this.varCharTrxRsnCd);
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.ordQty, (BigDecimal) csmpClaim.get(COL_NM_SL.ORD_QTY.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.shipQty, (BigDecimal) csmpClaim.get(COL_NM_SL.SHIP_QTY.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.boQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.manPrcFlg, FLG.N.toString());
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.dealNetUnitPrcAmt, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(0).autoPrcAmtRate.getValue());
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.invLineDealTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.invLineDealNetAmt, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(0).calcPrcAmtRate.getValue());
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.dealDiscUnitPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.taxFlg, FLG.N.toString());
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.taxPct, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.setMdseCd, (String) csmpClaim.get(COL_NM_SL.SET_MDSE_CD.name()));
        //ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.unitCostAmt, BigDecimal.ZERO);
        //ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.shipCmplCostAmt, BigDecimal.ZERO); // 2017/10/23 QC#20719 Del
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.dealGrsUnitPrcAmt, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(0).autoPrcAmtRate.getValue());
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.dealGrsTotPrcAmt, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(0).calcPrcAmtRate.getValue());
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.setRatioKeepFlg, FLG.N.toString());
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.firstBllgAttrbValTxt, (String) csmpClaim.get(COL_NM_SL.FIRST_BLLG_ATTRB_VAL_TXT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.scdBllgAttrbValTxt, (String) csmpClaim.get(COL_NM_SL.SCD_BLLG_ATTRB_VAL_TXT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.thirdBllgAttrbValTxt, (String) csmpClaim.get(COL_NM_SL.THIRD_BLLG_ATTRB_VAL_TXT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.frthBllgAttrbValTxt, (String) csmpClaim.get(COL_NM_SL.FRTH_BLLG_ATTRB_VAL_TXT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.uomCd, (String) csmpClaim.get(COL_NM_SL.UOM_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.dsOrdPosnNum, (String) csmpClaim.get(COL_NM_SL.DS_ORD_POSN_NUM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.dsCpoLineNum, (BigDecimal) csmpClaim.get(COL_NM_SL.DS_CPO_LINE_NUM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.dsCpoLineSubNum, (BigDecimal) csmpClaim.get(COL_NM_SL.DS_CPO_LINE_SUB_NUM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.custIssPoDt, (String) csmpClaim.get(COL_NM_SL.CUST_ISS_PO_DT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.custIssPoNum, (String) csmpClaim.get(COL_NM_SL.CUST_ISS_PO_NUM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.invDplyQty, (BigDecimal) csmpClaim.get(COL_NM_SL.INV_DPLY_QTY.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.cpoDtlLineNum, (String) csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_NUM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.cpoDtlLineSubNum, (String) csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_SUB_NUM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.prcCatgCd, (String) csmpClaim.get(COL_NM_SL.CSMP_PRC_LIST_CD_SAVE.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.dsOrdLineCatgCd, (String) csmpClaim.get(COL_NM_SL.DS_ORD_LINE_CATG_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.baseCmptFlg, (String) csmpClaim.get(COL_NM_SL.BASE_CMPT_FLG.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.csmpContrNum, (String) csmpClaim.get(COL_NM_SL.CSMP_CONTR_NUM.name()));

        return impInvApiLinePMsg;
    }
    private NWZC040003PMsg setImpInvValForLineForLine( Map<String, Object> line, String invNum, String invBolLineNum, String invLineNum, String invLineSubNum, NWZC157001PMsg prcApiPMsg, String strUomCd, String prcCatgCd ) {
        // ------------------ Line ------------------ 
        NWZC040003PMsg impInvApiLinePMsg = new NWZC040003PMsg();
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.invNum, invNum);
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.invBolLineNum, ZYPCommonFunc.leftPad(invBolLineNum, 3, "0"));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.invLineNum, ZYPCommonFunc.leftPad(invLineNum, 5, "0"));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.invLineSubNum, ZYPCommonFunc.leftPad(invLineSubNum, 3, "0"));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.invLineSubTrxNum, DEF_VAL.INV_LINE_SUB_TRX_NUM.getValue());
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.stkStsCd, STK_STS.GOOD.toString());
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.slsRepTocCd, (String) line.get(COL_NM_SL.SLS_REP_TOC_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.mdseCd, (String) line.get(COL_NM_SL.MDSE_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.mdseNm, (String) line.get(COL_NM_SL.MDSE_NM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.coaProdCd, (String) line.get(COL_NM_SL.COA_PROD_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.trxCd, this.varCharTrxCd);
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.trxRsnCd, this.varCharTrxRsnCd);
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.ordQty, (BigDecimal) line.get(COL_NM_SL.ORD_QTY.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.shipQty, (BigDecimal) line.get(COL_NM_SL.SHIP_QTY.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.boQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.manPrcFlg, FLG.N.toString());
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.dealNetUnitPrcAmt, (BigDecimal) line.get(COL_NM_SL.DEAL_NET_UNIT_PRC_AMT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.invLineDealTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.invLineDealNetAmt, (BigDecimal) line.get(COL_NM_SL.INV_LINE_DEAL_NET_AMT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.dealDiscUnitPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.taxFlg, FLG.N.toString());
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.taxPct, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.setMdseCd, (String) line.get(COL_NM_SL.SET_MDSE_CD.name()));
        //ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.unitCostAmt, BigDecimal.ZERO);
        //ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.shipCmplCostAmt, BigDecimal.ZERO); // 2017/10/23 QC#20719 Del
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.dealGrsUnitPrcAmt, (BigDecimal) line.get(COL_NM_SL.DEAL_GRS_UNIT_PRC_AMT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.dealGrsTotPrcAmt, (BigDecimal) line.get(COL_NM_SL.DEAL_GRS_TOT_PRC_AMT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.setRatioKeepFlg, FLG.N.toString());
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.firstBllgAttrbValTxt, (String) line.get(COL_NM_SL.FIRST_BLLG_ATTRB_VAL_TXT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.scdBllgAttrbValTxt, (String) line.get(COL_NM_SL.SCD_BLLG_ATTRB_VAL_TXT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.thirdBllgAttrbValTxt, (String) line.get(COL_NM_SL.THIRD_BLLG_ATTRB_VAL_TXT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.frthBllgAttrbValTxt, (String) line.get(COL_NM_SL.FRTH_BLLG_ATTRB_VAL_TXT.name()));
        //ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.uomCd, (String) line.get(COL_NM_SL.UOM_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.uomCd, strUomCd);
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.dsOrdPosnNum, (String) line.get(COL_NM_SL.DS_ORD_POSN_NUM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.dsCpoLineNum, (BigDecimal) line.get(COL_NM_SL.DS_CPO_LINE_NUM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.dsCpoLineSubNum, (BigDecimal) line.get(COL_NM_SL.DS_CPO_LINE_SUB_NUM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.custIssPoDt, (String) line.get(COL_NM_SL.CUST_ISS_PO_DT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.custIssPoNum, (String) line.get(COL_NM_SL.CUST_ISS_PO_NUM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.invDplyQty, (BigDecimal) line.get(COL_NM_SL.INV_DPLY_QTY.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.cpoDtlLineNum, (String) line.get(COL_NM_SL.CPO_DTL_LINE_NUM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.cpoDtlLineSubNum, (String) line.get(COL_NM_SL.CPO_DTL_LINE_SUB_NUM.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.prcCatgCd, prcCatgCd);
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.dsOrdLineCatgCd, (String) line.get(COL_NM_SL.DS_ORD_LINE_CATG_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiLinePMsg.baseCmptFlg, (String) line.get(COL_NM_SL.BASE_CMPT_FLG.name()));

        return impInvApiLinePMsg;
    }
    private NWZC040006PMsg setImpInvValForSer(String invNum, String invBolLineNum, String invLineNum, String invLineSubNum, String serNum) {
        // ------------------ Serial Number Detail ------------------
        NWZC040006PMsg impInvApiSerPMsg = new NWZC040006PMsg();
        //int iLineSubNum = 0;
        ZYPEZDItemValueSetter.setValue(impInvApiSerPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(impInvApiSerPMsg.invNum, invNum);
        ZYPEZDItemValueSetter.setValue(impInvApiSerPMsg.invBolLineNum, ZYPCommonFunc.leftPad(invBolLineNum, 3, "0"));
        ZYPEZDItemValueSetter.setValue(impInvApiSerPMsg.invLineNum, ZYPCommonFunc.leftPad(invLineNum, 5, "0"));
        ZYPEZDItemValueSetter.setValue(impInvApiSerPMsg.invLineSubNum, invLineSubNum);
        ZYPEZDItemValueSetter.setValue(impInvApiSerPMsg.invTrxLineSubNum, DEF_VAL.INV_TRX_LINE_SUB_NUM.getValue());
        ZYPEZDItemValueSetter.setValue(impInvApiSerPMsg.serNum, serNum);
        
        return impInvApiSerPMsg;
    }
    private NWZC040007PMsg setImpInvValForSlsCrOfLine(String invBolLineNum, String invLineNum, String invLineSubNum, String invNum, Map<String, Object> slsCrdtLine, NWZC157001PMsg prcApiPMsg, int rowCnt) {
        // ------------------ Invoice Sales Credit ------------------

        NWZC040007PMsg impInvApiSlsCrPMsg = new NWZC040007PMsg();
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.invNum, invNum);
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.invBolLineNum, ZYPCommonFunc.leftPad(invBolLineNum, 3, "0"));
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.invLineNum, ZYPCommonFunc.leftPad(invLineNum, 5, "0"));
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.invLineSubNum, invLineSubNum);
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.invLineSubTrxNum, DEF_VAL.INV_LINE_SUB_TRX_NUM.getValue());
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.mdseCd, (String) slsCrdtLine.get(COL_NM_SL.MDSE_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.invLineSplPct, (BigDecimal) slsCrdtLine.get(COL_NM_SL.INV_LINE_SPL_PCT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.slsRepTocCd, (String) slsCrdtLine.get(COL_NM_SL.SLS_REP_TOC_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.slsRepCrPct, (BigDecimal) slsCrdtLine.get(COL_NM_SL.SLS_REP_CR_PCT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.dealSlsCrAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.funcSlsCrAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.dealCcyCd, (String) slsCrdtLine.get(COL_NM_SL.DEAL_CCY_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.dfrdAcctgRuleCd, (String) slsCrdtLine.get(COL_NM_SL.DFRD_ACCTG_RULE_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.trxCd, varCharTrxCd);
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.trxRsnCd, varCharTrxRsnCd);

        return impInvApiSlsCrPMsg;
    }
    private NWZC040007PMsg setImpInvValForSlsCr(String invBolLineNum, String invLineNum, String invLineSubNum, String invNum, Map<String, Object> slsCrdtLine, NWZC157001PMsg prcApiPMsg, int rowCnt, NWZC040003PMsg invLinePMsg) {
        // ------------------ Invoice Sales Credit ------------------

        NWZC040007PMsg impInvApiSlsCrPMsg = new NWZC040007PMsg();
//        BigDecimal dealSlsCrAmt = prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(0).autoPrcAmtRate.getValue().multiply( (BigDecimal) slsCrdtLine.get(COL_NM_SL.INV_LINE_SPL_PCT.name()) ).divide(BigDecimal.valueOf(100)).multiply((BigDecimal) slsCrdtLine.get(COL_NM_SL.SLS_REP_CR_PCT.name())).divide(BigDecimal.valueOf(100));
        BigDecimal dealSlsCrAmt = invLinePMsg.invLineDealNetAmt.getValue()
                                .multiply( (BigDecimal) slsCrdtLine.get(COL_NM_SL.INV_LINE_SPL_PCT.name()) ).divide(BigDecimal.valueOf(100))
                                .multiply((BigDecimal) slsCrdtLine.get(COL_NM_SL.SLS_REP_CR_PCT.name())).divide(BigDecimal.valueOf(100));
//        BigDecimal funcSlsCrAmt = calcFuncAmt(this.glblCmpyCd, CCY.US_DOLLAR, slsDt, (prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(0).autoPrcAmtRate.getValue().multiply( (BigDecimal) slsCrdtLine.get(COL_NM_SL.INV_LINE_SPL_PCT.name()) ).divide(BigDecimal.valueOf(100)).multiply((BigDecimal) slsCrdtLine.get(COL_NM_SL.SLS_REP_CR_PCT.name())).divide(BigDecimal.valueOf(100))));
        BigDecimal funcSlsCrAmt = dealSlsCrAmt;
//        dealSlsCrTotAmt = dealSlsCrTotAmt.add(dealSlsCrAmt);
//        funcSlsCrTotAmt = funcSlsCrTotAmt.add(funcSlsCrAmt);
        //funcSlsCrAmt = funcSlsCrAmt.multiply( (BigDecimal) slsCrdtLine.get(COL_NM_SL.INV_LINE_SPL_PCT.name()) ).divide(BigDecimal.valueOf(100)).multiply((BigDecimal) slsCrdtLine.get(COL_NM_SL.SLS_REP_CR_PCT.name())).divide(BigDecimal.valueOf(100));
        
        //BigDecimal funcSlsCrAmt = ((BigDecimal) slsCrdtLine.get(COL_NM_SL.FUNC_SLS_CR_AMT.name())).multiply( (BigDecimal) slsCrdtLine.get(COL_NM_SL.INV_LINE_SPL_PCT.name()) ).divide(BigDecimal.valueOf(100)).multiply((BigDecimal) slsCrdtLine.get(COL_NM_SL.SLS_REP_CR_PCT.name())).divide(BigDecimal.valueOf(100));
        //if ( dealLinePrcMaxDiffAmt.equals(BigDecimal.ZERO) ) {
        //    dealLinePrcMaxDiffAmt = (BigDecimal) dealSlsCrAmt.subtract(lineDealAmt).setScale(2,BigDecimal.ROUND_HALF_UP);
        //    dealLinePrcDiffRow = rowCnt;
        //} else {
        //    if (lineDealAmt.compareTo(dealSlsCrAmt) <= 0 ) {
        //        dealLinePrcMaxDiffAmt = dealLinePrcMaxDiffAmt.add((BigDecimal) dealSlsCrAmt.subtract(lineDealAmt).setScale(2,BigDecimal.ROUND_HALF_UP));
        //        dealLinePrcDiffRow = rowCnt;
        //    } else {
        //        dealLinePrcMaxDiffAmt = dealLinePrcMaxDiffAmt.add((BigDecimal) dealSlsCrAmt.subtract(lineDealAmt).setScale(2,BigDecimal.ROUND_HALF_UP));
        //    }
        //}
        //if ( funcLinePrcMaxDiffAmt.equals(BigDecimal.ZERO) ) {
        //    funcLinePrcMaxDiffAmt = (BigDecimal) funcSlsCrAmt.subtract(lineFuncAmt).setScale(2,BigDecimal.ROUND_HALF_UP);
        //    funcLinePrcDiffRow = rowCnt;
        //} else {
        //    if (lineFuncAmt.compareTo(funcSlsCrAmt) <= 0 ) {
        //        funcLinePrcMaxDiffAmt = funcLinePrcMaxDiffAmt.add((BigDecimal) funcSlsCrAmt.subtract(lineDealAmt).setScale(2,BigDecimal.ROUND_HALF_UP));
        //        funcLinePrcDiffRow = rowCnt;
        //    } else {
        //        funcLinePrcMaxDiffAmt = funcLinePrcMaxDiffAmt.add((BigDecimal) funcSlsCrAmt.subtract(lineDealAmt).setScale(2,BigDecimal.ROUND_HALF_UP));
        //    }
        //}
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.invNum, invNum);
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.invBolLineNum, ZYPCommonFunc.leftPad(invBolLineNum, 3, "0"));
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.invLineNum, ZYPCommonFunc.leftPad(invLineNum, 5, "0"));
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.invLineSubNum, invLineSubNum);
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.invLineSubTrxNum, DEF_VAL.INV_LINE_SUB_TRX_NUM.getValue());
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.mdseCd, (String) slsCrdtLine.get(COL_NM_SL.MDSE_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.invLineSplPct, (BigDecimal) slsCrdtLine.get(COL_NM_SL.INV_LINE_SPL_PCT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.slsRepTocCd, (String) slsCrdtLine.get(COL_NM_SL.SLS_REP_TOC_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.slsRepCrPct, (BigDecimal) slsCrdtLine.get(COL_NM_SL.SLS_REP_CR_PCT.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.dealSlsCrAmt, dealSlsCrAmt.setScale(2,BigDecimal.ROUND_HALF_UP));
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.funcSlsCrAmt, funcSlsCrAmt.setScale(2,BigDecimal.ROUND_HALF_UP));
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.dealCcyCd, (String) slsCrdtLine.get(COL_NM_SL.DEAL_CCY_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.dfrdAcctgRuleCd, (String) slsCrdtLine.get(COL_NM_SL.DFRD_ACCTG_RULE_CD.name()));
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.trxCd, varCharTrxCd);
        ZYPEZDItemValueSetter.setValue(impInvApiSlsCrPMsg.trxRsnCd, varCharTrxRsnCd);

        return impInvApiSlsCrPMsg;
    }

    private boolean sendMail( ArrayList<String> errInfoList ) {

        StringBuilder sbMailContext = new StringBuilder();
        sbMailContext.append("Orig Inv#, Bol#, Line#, Line Sub#, Sub Trx#, Ord#, Ord Line#, Ord Line Sub#, Error Message\r\n");

        for ( String errInfo : errInfoList) {
            sbMailContext.append(errInfo + "\r\n");
        }

        //
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL.ML_TMPL_ID.getValue());
        if (template == null) {
            //S21InfoLogOutput.println(MSG_ERROR_CD..toString(), new String[] {MAIL_CONST.ML_TMPL_ID.getKey()});
            return false;
        }
        template.setTemplateParameter(MAIL.REPLACE_SUB_BATCH_ID.getValue(), DEF_VAL.PROGRAM_ID.getValue());
        template.setTemplateParameter(MAIL.REPLACE_ERR_DT.getValue(), ZYPDateUtil.getCurrentSystemTime("yyyy/MM/dd HH:mm:ss"));
        template.setTemplateParameter(MAIL.REPLACE_MESSAGE.getValue(), sbMailContext.toString());

        
        /*******************************
         * Send Mail
         *******************************/

        /*******************************
         * Set "From" email address to
         * "S21_AFB@cusa.canon.com"
         *******************************/
        S21MailGroup mailGroup = new S21MailGroup(glblCmpyCd, MAIL.ML_GRP_ID_FROM.getValue());
        List<S21MailAddress> addrFromList = mailGroup.getMailAddress();
        S21MailAddress from = null;
        if (addrFromList.size() <= 0) {
            throw new S21AbendException(MSG_ID.ZZMM0007E.toString(), new String[] {"From Address" });
        } else {
            from = addrFromList.get(0);
        }
        /*******************************
         * Get "To" email address
         *******************************/
        mailGroup = new S21MailGroup(glblCmpyCd, MAIL.ML_GRP_ID_TO.getValue());
        List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>();
        addrToList = mailGroup.getMailAddress();
        if (addrToList.size() <= 0) {
            throw new S21AbendException(MSG_ID.ZZMM0007E.toString(), new String[] {"To Address" });
        }

        /*******************************
         * Get "CC" email address
         *******************************/
        List<S21MailAddress> addrCcList = new ArrayList<S21MailAddress>();
        addrCcList = mailGroup.getMailAddress();

        /*******************************
         * Send mail.
         *******************************/
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setMailTemplate(template);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        if (addrCcList.size() <= 0) {
            mail.setCcAddressList(addrCcList);
        }
        mail.setSubject(template.getSubject("en"), "ISO-8859-1");
        Map<String, String> mailParamMap = new HashMap<String, String>();
        if (mailParamMap.containsKey("attachmentId")) {
            mail.setAttachmentId(Integer.parseInt((String)mailParamMap.get("attachmentId")));
        }
        // 2019/02/27 QC#30533 Mod Start
//        String mailEvent = mail.sendMail();
//        if(!ZYPCommonFunc.hasValue(mailEvent)){
//            return false;
//        } else {
//            String mailResult = mail.getResultCd();
//            if (ZYPCommonFunc.hasValue(mailResult)) {
//                return false;
//            }
//        }
        mail.postMail();
        // 2019/02/27 QC#30533 Mod End
        
        return true;
    }

//Mod Start QC#12252
    private boolean errProcess( Map<String, Object> csmpClaim, String errMsg) {
//Mod End   QC#12252

        // Set Error Count
        errRcCnt ++;

        //Update INV_LINE
        INV_LINETMsg paramTMsg = new INV_LINETMsg();
        ZYPEZDItemValueSetter.setValue(paramTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(paramTMsg.invNum, (String) csmpClaim.get(COL_NM_SL.INV_NUM.name()));
        ZYPEZDItemValueSetter.setValue(paramTMsg.invBolLineNum, (String) csmpClaim.get(COL_NM_SL.INV_BOL_LINE_NUM.name()));
        ZYPEZDItemValueSetter.setValue(paramTMsg.invLineNum, (String) csmpClaim.get(COL_NM_SL.INV_LINE_NUM.name()));
        ZYPEZDItemValueSetter.setValue(paramTMsg.invLineSubNum, (String) csmpClaim.get(COL_NM_SL.INV_LINE_SUB_NUM.name()));
        ZYPEZDItemValueSetter.setValue(paramTMsg.invLineSubTrxNum, (String) csmpClaim.get(COL_NM_SL.INV_LINE_SUB_TRX_NUM.name()));
        
        INV_LINETMsg updateTMsg = (INV_LINETMsg) EZDTBLAccessor.findByKeyForUpdate(paramTMsg);
        // Set Error Code to Process Status Code.
        ZYPEZDItemValueSetter.setValue(updateTMsg.csmpInvProcStsCd, DEF_VAL.INV_PROC_STS_CD_ERROR.getValue());
        //Update
        EZDTBLAccessor.update(updateTMsg);
        //Check Update result
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
            return false;
        }

        //Insert CSMP_INV_ERR
        CSMP_INV_ERRTMsg insrtTMsg = new CSMP_INV_ERRTMsg();
        ZYPEZDItemValueSetter.setValue(insrtTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insrtTMsg.csmpInvErrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CSMP_INV_ERR_SQ));
        ZYPEZDItemValueSetter.setValue(insrtTMsg.origInvNum,  (String) csmpClaim.get(COL_NM_SL.INV_NUM.name()));
        ZYPEZDItemValueSetter.setValue(insrtTMsg.origInvBolLineNum,  (String) csmpClaim.get(COL_NM_SL.INV_BOL_LINE_NUM.name()));
        ZYPEZDItemValueSetter.setValue(insrtTMsg.origInvLineNum,  (String) csmpClaim.get(COL_NM_SL.INV_LINE_NUM.name()));
        ZYPEZDItemValueSetter.setValue(insrtTMsg.origInvLineSubNum,  (String) csmpClaim.get(COL_NM_SL.INV_LINE_SUB_NUM.name()));
        ZYPEZDItemValueSetter.setValue(insrtTMsg.origInvLineSubTrxNum,  (String) csmpClaim.get(COL_NM_SL.INV_LINE_SUB_TRX_NUM.name()));
        ZYPEZDItemValueSetter.setValue(insrtTMsg.origCpoOrdNum,  (String) csmpClaim.get(COL_NM_SL.CPO_ORD_NUM.name()));
        ZYPEZDItemValueSetter.setValue(insrtTMsg.origDsOrdPosnNum,  (String) csmpClaim.get(COL_NM_SL.DS_ORD_POSN_NUM.name()));
        ZYPEZDItemValueSetter.setValue(insrtTMsg.origCpoDtlLineNum,  (String) csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_NUM.name()));
        ZYPEZDItemValueSetter.setValue(insrtTMsg.origCpoDtlLineSubNum,  (String) csmpClaim.get(COL_NM_SL.CPO_DTL_LINE_SUB_NUM.name()));
//Mod Start QC#12252
        ZYPEZDItemValueSetter.setValue(insrtTMsg.csmpInvErrMsgTxt,  errMsg);
//Mod End   QC#12252
        ZYPEZDItemValueSetter.setValue(insrtTMsg.csmpInvErrDt,  this.slsDt);
        //Insert
        EZDTBLAccessor.insert(insrtTMsg);
        //Check Update result
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insrtTMsg.getReturnCode())) {
            return false;
        }

        return true;
    }

    private boolean skipProcess( Map<String, Object> csmpClaim ) {
        //Update INV_LINE
        INV_LINETMsg paramTMsg = new INV_LINETMsg();
        ZYPEZDItemValueSetter.setValue(paramTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(paramTMsg.invNum, (String) csmpClaim.get(COL_NM_SL.INV_NUM.name()));
        ZYPEZDItemValueSetter.setValue(paramTMsg.invBolLineNum, (String) csmpClaim.get(COL_NM_SL.INV_BOL_LINE_NUM.name()));
        ZYPEZDItemValueSetter.setValue(paramTMsg.invLineNum, (String) csmpClaim.get(COL_NM_SL.INV_LINE_NUM.name()));
        ZYPEZDItemValueSetter.setValue(paramTMsg.invLineSubNum, (String) csmpClaim.get(COL_NM_SL.INV_LINE_SUB_NUM.name()));
        ZYPEZDItemValueSetter.setValue(paramTMsg.invLineSubTrxNum, (String) csmpClaim.get(COL_NM_SL.INV_LINE_SUB_TRX_NUM.name()));
        
        INV_LINETMsg updateTMsg = (INV_LINETMsg) EZDTBLAccessor.findByKeyForUpdate(paramTMsg);
        // Set Error Code to Process Status Code.
        ZYPEZDItemValueSetter.setValue(updateTMsg.csmpInvProcStsCd, DEF_VAL.INV_PROC_STS_CD_NONE.getValue());
        //Update
        EZDTBLAccessor.update(updateTMsg);
        //Check Update result
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
            return false;
        }
        
        return true;
    }

    private boolean updateInvProcSts( String invNum, String invBolLineNum, String invLineNum, String invLineSubNum, String invLineSubTrxNum ) {
        //
        INV_LINETMsg invLineTMsg = new INV_LINETMsg();
        invLineTMsg.glblCmpyCd.setValue(this.getGlobalCompanyCode());
        invLineTMsg.invNum.setValue(invNum);
        invLineTMsg.invBolLineNum.setValue(invBolLineNum);
        invLineTMsg.invLineNum.setValue(invLineNum);
        invLineTMsg.invLineSubNum.setValue(invLineSubNum);
        invLineTMsg.invLineSubTrxNum.setValue(invLineSubTrxNum);

        INV_LINETMsg updateTMsg = (INV_LINETMsg) EZDTBLAccessor.findByKeyForUpdate(invLineTMsg);

        //Status Change to Complete
        updateTMsg.csmpInvProcStsCd.setValue(DEF_VAL.CSMP_INV_PROC_STS_CD_COMP.getValue());

        //Update
        EZDTBLAccessor.update(updateTMsg);
        
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
            return false;
        }
        
        return true;
    }

    /**
     * <pre>
     * get Func Amount
     * @param glblCmpyCd Global Company Code
     * @param dealCcyCd Deal Currency Code
     * @param slsDt Sales Date
     * @param dealAmt deal Amount
     * @return Function Amount
     * </pre>
     */
    public BigDecimal calcFuncAmt(String glblCmpyCd, String dealCcyCd, String slsDt, BigDecimal dealAmt) {

        BigDecimal funcAmt = null;
        NWXC001001ExchangeData exchData = new NWXC001001ExchangeData();
        exchData.setGlblCmpyCd(glblCmpyCd);
        exchData.setCcyCd(dealCcyCd);
        exchData.setSlsDt(slsDt);
        List<NWXC001001ExchangePriceData> priceDataList = new ArrayList<NWXC001001ExchangePriceData>();
        NWXC001001ExchangeAmoutData exchAmtData = new NWXC001001ExchangeAmoutData();

        NWXC001001ExchangeAmount grsAmt = new NWXC001001ExchangeAmount();
        grsAmt.setDealAmt(dealAmt);

        exchAmtData.setGrsAmt(grsAmt);
        priceDataList.add(exchAmtData);

        exchData.setPriceData(priceDataList);

        NWXC001001Exchange.exchFuncUnitPrice(exchData);
        if (!exchData.getXxMsgIdList().isEmpty()) {
            return null;
        }
        for (int i = 0; i < exchData.getPriceData().size(); i++) {
            NWXC001001ExchangePriceData prcData = exchData.getPriceData().get(i);
            for (int j = 0; j < prcData.getAmountList().size(); j++) {
                funcAmt = prcData.getAmountList().get(j).getFuncAmt();
            }
        }
        return funcAmt;
    }
    private String castToString(Object val) {
        if (val ==null) {
            return "";
        }
        return (String) val;
    }
    //QC#25120
    @SuppressWarnings("unchecked")
    private Map<String, Object> remove(String origInvNum) {

        Map<String, Object> returnMap = new HashMap<String, Object>();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("origInvNum", origInvNum);
        List<Map<String, Object>> list = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCSMPInvErr", ssmParam);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) list.get(i);
                CSMP_INV_ERRTMsg deleteCsmpinvErrTMsg = new CSMP_INV_ERRTMsg();
                deleteCsmpinvErrTMsg.glblCmpyCd.setValue(glblCmpyCd);
                deleteCsmpinvErrTMsg.csmpInvErrPk.setValue((BigDecimal)map.get("CSMP_INV_ERR_PK"));
                
                deleteCsmpinvErrTMsg = (CSMP_INV_ERRTMsg) EZDTBLAccessor.findByKeyForUpdate(deleteCsmpinvErrTMsg);
                if (deleteCsmpinvErrTMsg != null) {
                    EZDTBLAccessor.remove(deleteCsmpinvErrTMsg);
                }
            }
        }
        return returnMap;
    }
}