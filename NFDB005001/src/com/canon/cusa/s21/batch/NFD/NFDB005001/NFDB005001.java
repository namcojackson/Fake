/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFD.NFDB005001;

import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.ALIGN_TAB_WIDTH;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.COL_CONTRACT_NUMBER;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.COL_END_DATE;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.COL_START_DATE;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.COL_TAB_SPACING;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.CONTR_HLD_STRTGY_NOTIFY_CLT_ITEM_CD;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.FETCH_SIZE_MAX;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.INT_8;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.KEY_CLT_STRGY_MAIL_ROW_CNT;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.MAIL_TMPL_CREDIT_CONTR_LIST;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.MAIL_TMPL_ID;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.MAIL_TMPL_KEY_ACCT_NUM;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.MAIL_TMPL_KEY_BILL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.MAIL_TMPL_KEY_CUST_TP;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.MAX_ROW;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.NFBM0184E;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.NFDM0004E;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.NFDM0005E;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.UPDATE_CNT;
import static com.canon.cusa.s21.batch.NFD.NFDB005001.NFDB005001Constant.ZZZM9025E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import business.db.CLT_STRGY_WRK_ITEM_TRXTMsg;
import business.db.CUST_CR_PRFLTMsg;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;

import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContractTracking;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_STRGY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CHK_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_RSN;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 04/27/2015   Fujitsu         Y.Kamide        Create          N/A
 * 03/18/2016   CSAI            K.Uramori       Update          QC#5525
 * 2016/07/28   Hitachi         K.Kojima        Update          QC#12096
 * 2016/09/26   Hitachi         K.Kojima        Update          QC#13004
 * 2017/01/05   Hitachi         E.Kameishi      Update          QC#16817
 * 2017/06/26   Hitachi         E.Kameishi      Update          QC#18754
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 * 2021/07/07   CITS            G.Delgado       Update          QC#58946
 * 2021/08/16   CITS            G.Delgado       Update          QC#59080
 * 2022/03/25   CITS            K.Suzuki        Update          QC#59868
 *</pre>
 */
public class NFDB005001 extends S21BatchMain implements ZYPConstant {

    /** Normal Counter */
    private int normCnt = 0;

    /** Error Counter */
    private int errCnt = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Process Date */
    private String batProcDt = null;

    /** Process User ID */
    private String userId = null;

    /** Service Memo Reason Description Text */
    private String svcMemoRsnDescTxt = null;

    /** maxRowCnt */
    private int maxRowCnt = 0;

    /** Term Code */
    private TERM_CD termCd = null;

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Default email address */
    private String defEmlAdd = "";

    /** Default email address list */
    private List<String> defEmlAddList = new ArrayList<String>();

    @Override
    protected void initRoutine() {
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.termCd = TERM_CD.NORMAL_END;
        this.userId = this.getClass().getSimpleName().substring(0, INT_8);

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        this.batProcDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd, BUSINESS_ID);

        BigDecimal numConstVal = ZYPCodeDataUtil.getNumConstValue(KEY_CLT_STRGY_MAIL_ROW_CNT, this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(numConstVal)) {
            this.maxRowCnt = numConstVal.intValue();
        } else {
            this.maxRowCnt = MAX_ROW;
        }

        this.svcMemoRsnDescTxt = ZYPCodeDataUtil.getName("SVC_MEMO_RSN", this.glblCmpyCd, SVC_MEMO_RSN.STRATEGY_HOLD);

        // ----- start add 2016/01/13 get default email address from
        // VAR_CHAR_CONST
        defEmlAdd = ZYPCodeDataUtil.getVarCharConstValue(NFDB005001Constant.AR_CLT_DEF_EML_ADDR, this.glblCmpyCd);
        if (!hasValue(defEmlAdd)) {
            // error
            throw new S21AbendException(ZZZM9025E, new String[] {"Default Collector's Email Address" });
        }
        // split the email addresses
        divDefEmlAdd();
        // ----- end 2016/01/13
    }

    private void divDefEmlAdd() {
        StringTokenizer st = new StringTokenizer(defEmlAdd, ",");

        while (st.hasMoreTokens()) {
            defEmlAddList.add(st.nextToken());
        }

    }

    @Override
    protected void mainRoutine() {
        execute();
    }

    @Override
    protected void termRoutine() {
        setTermState(termCd, normCnt, errCnt, normCnt + errCnt);
    }

    /**
     * main
     * @param args argument
     */
    public static void main(String[] args) {
        new NFDB005001().executeBatch(NFDB005001.class.getSimpleName());
    }

    /**
     * execute
     */
    private void execute() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getWorkItemTran();
            rs = ps.executeQuery();
            Map<String, MailData> mailDataMap = new HashMap<String, MailData>();
            while (rs.next()) {
                String billToCustCd = rs.getString("BILL_TO_CUST_CD");
                if (billToCustCd != null && !mailDataMap.containsKey(billToCustCd)) {
                    mailDataMap.put(billToCustCd, createMailData(rs));
                }
            }

            updateContractAndNotify(mailDataMap);

            rs.beforeFirst();
            updateCltStrtgyWrkItemTrx(rs);
            commit();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * updateCltStrtgyWrkItemTrx
     * @throws SQLException
     */
    private void updateCltStrtgyWrkItemTrx(ResultSet rs) throws SQLException {

        List<CLT_STRGY_WRK_ITEM_TRXTMsg> updateList = new ArrayList<CLT_STRGY_WRK_ITEM_TRXTMsg>();
        while (rs.next()) {

            BigDecimal cltStrgyWorkItemTrxPk = rs.getBigDecimal("CLT_STRGY_WRK_ITEM_TRX_PK");
            CLT_STRGY_WRK_ITEM_TRXTMsg updateMsg = getCltStrtgyWrkItemTrxForUpdate(cltStrgyWorkItemTrxPk);
            if (updateMsg == null) {
                continue;
            }

            setValue(updateMsg.cltWrkItemWsrdDt, this.batProcDt);
            setValue(updateMsg.cltWrkItemStsCd, CLT_WRK_ITEM_STS.COMPLETED);
            setValue(updateMsg.cltWrkItemWerdDt, this.batProcDt);

            updateList.add(updateMsg);
            if (updateList.size() >= UPDATE_CNT) {
                updateCltStrtgyWrkItemTrx(updateList);
                updateList.clear();
            }

        }
        if (updateList.size() > 0) {
            updateCltStrtgyWrkItemTrx(updateList);
        }
    }

    /**
     * updateCltStrtgyWrkItemTrx
     * @param updateList List<CLT_STRGY_WRK_ITEM_TRXTMsg>
     */
    private void updateCltStrtgyWrkItemTrx(List<CLT_STRGY_WRK_ITEM_TRXTMsg> updateList) {
        int cnt = S21FastTBLAccessor.update(updateList.toArray(new CLT_STRGY_WRK_ITEM_TRXTMsg[0]));
        if (cnt != updateList.size()) {
            String[] args = {"CLT_STRGY_WRK_ITEM_TRX" };
            throw new S21AbendException(NFDM0004E, args);
        }
        commit();
    }

    /**
     * createMailData
     * @param rs ResultSet
     * @return MailData
     * @throws SQLException
     */
    private MailData createMailData(ResultSet rs) throws SQLException {
        MailData data = new MailData();
        data.setBillToCustPk(rs.getBigDecimal("BILL_TO_CUST_PK"));
        data.setBillToCustCd(rs.getString("BILL_TO_CUST_CD"));
        data.setDsAcctNum(rs.getString("CLT_ACCT_CD"));
        data.setLocNum(rs.getString("LOC_NUM"));
        data.setCltStrgyNm(rs.getString("CLT_CUST_TP_NM"));
        data.setFirstEmlAddr(rs.getString("EML_ADDR_BILL"));
        data.setScdEmlAddr(rs.getString("EML_ADDR_ACCT"));
        return data;
    }

    /**
     * updateContractAndNotify
     * @param mailDataMap Map<String, MailData>
     */
    private void updateContractAndNotify(Map<String, MailData> mailDataMap) {
        for (String bllgToCustCd : mailDataMap.keySet()) {
            updateContractAndNotify(mailDataMap.get(bllgToCustCd));
        }
    }

    /**
     * updateContractAndNotify
     * @param mailData MailDatas
     */
    private void updateContractAndNotify(MailData mailData) {
        int cnt = updateContract(mailData);
        if (cnt > 0) {
            updateCustCrPrfl(mailData.getBillToCustPk(), mailData.getDsAcctNum());
            notifyCollector(mailData);

            this.normCnt++;
        }
    }

    /**
     * updateContract
     * @param mailData MailData
     * @return cnt
     */
    private int updateContract(MailData mailData) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        Map<BigDecimal, BigDecimal> mapDtl = new HashMap<BigDecimal, BigDecimal>();
        Map<BigDecimal, BigDecimal> mapBllgMtr = new HashMap<BigDecimal, BigDecimal>();
        Map<BigDecimal, BigDecimal> mapPrcEff = new HashMap<BigDecimal, BigDecimal>();

        // START 2021/07/07 G.Delgado [QC#58946, ADD]
        Map<String, List<List<String>>> contractMap = new HashMap<String, List<List<String>>>();
        mailData.setContracts(contractMap);
        // END 2021/07/07 G.Delgado [QC#58946, ADD]

        int cnt = 0;
        try {
            ps = getContractList(mailData.getBillToCustCd());
            rs = ps.executeQuery();
            BigDecimal targetDsContrPk = null;

            List<DS_CONTR_DTLTMsg> updateList = new ArrayList<DS_CONTR_DTLTMsg>();
            List<DS_CONTR_BLLG_MTRTMsg> updateListMtr = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
            List<DS_CONTR_PRC_EFFTMsg> updateListEff = new ArrayList<DS_CONTR_PRC_EFFTMsg>();
            // START 2021/07/07 G.Delgado [QC#58946, ADD]
            List<BigDecimal> updateListContr = new ArrayList<BigDecimal>();
            // END 2021/07/07 G.Delgado [QC#58946, ADD]

            while (rs.next()) {
                BigDecimal dsContrPk = rs.getBigDecimal("DS_CONTR_PK");
                BigDecimal dsContrDtlPk = rs.getBigDecimal("DS_CONTR_DTL_PK");
                BigDecimal dsContrBllgMtrPk = rs.getBigDecimal("DS_CONTR_BLLG_MTR_PK");
                BigDecimal dsContrPrcEffPk = rs.getBigDecimal("DS_CONTR_PRC_EFF_PK");

                // When contract pk breaks
                if (!hasValue(targetDsContrPk) || targetDsContrPk.compareTo(dsContrPk) != 0) {
                    if (hasValue(targetDsContrPk)) { // skip first
                        DS_CONTRTMsg dsContrTMsg = getDsContrForUpdate(targetDsContrPk);
                        if (dsContrTMsg != null
                                && ZYPConstant.FLG_OFF_N.equals(dsContrTMsg.contrHldFlg.getValue())) {
                            setValue(dsContrTMsg.contrHldFlg, FLG_ON_Y);
                            updateContract(dsContrTMsg, updateList, updateListMtr, updateListEff);
                            cnt++;
                            createContrTrk(targetDsContrPk);
                            // START 2021/07/07 G.Delgado [QC#58946, ADD]
                            if (!updateListContr.contains(targetDsContrPk)) {
                                addContractInfoToMap(contractMap, dsContrTMsg);
                            }
                            // END 2021/07/07 G.Delgado [QC#58946, ADD]
                        } else {
                            updateContract(null, updateList, updateListMtr, updateListEff);
                        }
                    }
                    updateList.clear();
                    updateListMtr.clear();
                    updateListEff.clear();
                    targetDsContrPk = dsContrPk;
                }

                if (!mapDtl.containsValue(dsContrDtlPk)) {
                    DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtlForUpdate(dsContrDtlPk);
                    if (dsContrDtlTMsg != null
                        && ZYPConstant.FLG_OFF_N.equals(dsContrDtlTMsg.contrHldFlg.getValue())) {
                        setValue(dsContrDtlTMsg.contrHldFlg, FLG_ON_Y);
                        updateList.add(dsContrDtlTMsg);
                        cnt++;
                    }
                }

                if (!mapBllgMtr.containsValue(dsContrBllgMtrPk) && (hasValue(dsContrBllgMtrPk))) {
                    DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = getDsContrBllgMtrForUpdate(dsContrBllgMtrPk);
                    if (dsContrBllgMtrTMsg != null
                        && ZYPConstant.FLG_OFF_N.equals(dsContrBllgMtrTMsg.contrHldFlg.getValue())) {
                        setValue(dsContrBllgMtrTMsg.contrHldFlg, FLG_ON_Y);
                        updateListMtr.add(dsContrBllgMtrTMsg);
                        cnt++;
                    }
                }

                if (!mapPrcEff.containsValue(dsContrPrcEffPk) && hasValue(dsContrPrcEffPk)) {
                    DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = getDsContrPrcEffForUpdate(dsContrPrcEffPk);
                    if (dsContrPrcEffTMsg != null
                        && ZYPConstant.FLG_OFF_N.equals(dsContrPrcEffTMsg.contrHldFlg.getValue())) {
                        setValue(dsContrPrcEffTMsg.contrHldFlg, FLG_ON_Y);
                        updateListEff.add(dsContrPrcEffTMsg);
                        cnt++;
                    }
                }
                mapDtl.put(dsContrDtlPk, dsContrDtlPk);
                mapBllgMtr.put(dsContrBllgMtrPk, dsContrBllgMtrPk);
                mapPrcEff.put(dsContrPrcEffPk, dsContrPrcEffPk);

            } // while

            if (updateList.size() > 0 || updateListMtr.size() > 0 || updateListEff.size() > 0) {
                DS_CONTRTMsg dsContrTMsg = getDsContrForUpdate(targetDsContrPk);
                if (dsContrTMsg != null
                        && ZYPConstant.FLG_OFF_N.equals(dsContrTMsg.contrHldFlg.getValue())) {
                    setValue(dsContrTMsg.contrHldFlg, FLG_ON_Y);
                    updateContract(dsContrTMsg, updateList, updateListMtr, updateListEff);
                    createContrTrk(targetDsContrPk);
                    // START 2021/07/07 G.Delgado [QC#58946, ADD]
                    if (!updateListContr.contains(targetDsContrPk)) {
                        addContractInfoToMap(contractMap, dsContrTMsg);
                    }
                    // END 2021/07/07 G.Delgado [QC#58946, ADD]
                } else {
                    updateContract(null, updateList, updateListMtr, updateListEff);
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        return cnt;
    }

    /**
     * notifyCollector
     * @param mailData MailDatas
     */
    private void notifyCollector(MailData mailData) {

        List<String> contractList = new ArrayList<String>();
        // START 2021/07/07 G.Delgado [QC#58946, MOD]
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            ps = getNotifyContractList(mailData.getBillToCustCd());
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                contractList.add(createContractRow(rs));
//                if (contractList.size() >= this.maxRowCnt) {
//                    break;
//                }
//            }
//        } catch (SQLException e) {
//            sqlExceptionHandler(e);
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(ps, rs);
//        }

        Map<String, List<List<String>>> contractMap = mailData.getContracts();
        if (contractMap.isEmpty()) {
            return;
        }

        // Sort contract numbers
        List<String> contrNumList = new ArrayList<String>(contractMap.keySet());
        Collections.sort(contrNumList);

        // Set contract number column max data length
        int contrNumMaxLen = getMaxStrLength(contrNumList);
        if (contrNumMaxLen < COL_CONTRACT_NUMBER.length()) {
            contrNumMaxLen = COL_CONTRACT_NUMBER.length();
        }

        // Add header
        String[] colHeaders = {COL_CONTRACT_NUMBER, COL_START_DATE, COL_END_DATE };
        contractList.add(formatContractRow(Arrays.asList(colHeaders), contrNumMaxLen));

        for (String contrNum : contrNumList) {
            for (List<String> row : contractMap.get(contrNum)) {
                contractList.add(formatContractRow(row, contrNumMaxLen));
                if (contractList.size() >= this.maxRowCnt) {
                    break;
                }
            }
        }
        // END 2021/07/07 G.Delgado [QC#58946, MOD]

        sendMail(mailData, contractList);
    }

    /**
     *<pre>
     * Send Mail
     * @param mailData MailData
     * @param contractList List<String>
     *</pre>
     */
    public void sendMail(MailData mailData, List<String> contractList) {

        //--- start mod 2016/03/18
        // Even if contract data doesn't exist, email should be sent
        //if (contractList.size() == 0) {
        //    return;
        //}
        //---- end 2016/03/18

        // 1. Get From Address
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1("NFD");
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (!addrFromList.isEmpty()) {
            from = addrFromList.get(0);
        }

        // 2. Get To Address
        // ---- start mod 2016/01/13
        // S21MailAddress addrTo = getAddrTo(mailData);
        List<S21MailAddress> addrToList = getAddrTo(mailData);
        // ---- end 2016/01/13

        // 4. Create Subject and Body
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TMPL_ID);
        if (template == null) {
            throw new S21AbendException(NFBM0184E, new String[] {MAIL_TMPL_ID });
        }

        template.setTemplateParameter(MAIL_TMPL_KEY_ACCT_NUM, nullToEmpty(mailData.getDsAcctNum()));
        // START 2021/07/07 G.Delgado [QC#58946, MOD]
        // template.setTemplateParameter(MAIL_TMPL_KEY_LOC_NUM, nullToEmpty(mailData.getLocNum()));
        template.setTemplateParameter(MAIL_TMPL_KEY_BILL_TO_CUST_CD, nullToEmpty(mailData.getBillToCustCd()));
        // END 2021/07/07 G.Delgado [QC#58946, MOD]
        template.setTemplateParameter(MAIL_TMPL_KEY_CUST_TP, nullToEmpty(mailData.getCltStrgyNm()));
        if (contractList.size() > 0) {
            template.setTemplateParameter(MAIL_TMPL_CREDIT_CONTR_LIST, toStringForList(contractList));
        }

        // 5. Call Mail API
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(from);
        // ---- start mod 2016/01/13
        // mail.setToAddress(addrTo);
        mail.setToAddressList(addrToList);
        // ---- end 2016/01/13
        mail.setMailTemplate(template);
        mail.postMail();
    }

    /**
     * updateCustCrPrfl
     * @param billToCustPk BigDecimal
     */
    private void updateCustCrPrfl(BigDecimal billToCustPk, String dsAcctNum) {
        //---- start mod 2016/03/18 update DS_ACCT_CR_PRFL. If there is CUST_CR_PRFL, update it, too.
        updateDsAcctCrPrfl(dsAcctNum);
        CUST_CR_PRFLTMsg custCrPrflTMsg = getCustCrPrflForUpdate(billToCustPk);
        if (custCrPrflTMsg != null) {
            setValue(custCrPrflTMsg.crChkReqTpCd, CR_CHK_REQ_TP.HOLD);
            S21FastTBLAccessor.update(custCrPrflTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(custCrPrflTMsg.getReturnCode())) {
                String[] args = {"CUST_CR_PRFL" };
                throw new S21AbendException(NFDM0004E, args);
            }
        }
        //---- end 2016/03/18
    }

    //---- start add 2016/03/18
    /**
     * updateDsAcctCrPrfl
     * @param dsAcctNum String
     */
    private void updateDsAcctCrPrfl(String dsAcctNum) {
        DS_ACCT_CR_PRFLTMsg acctCrPrflTMsg = getDsAcctCrPrflForUpdate(dsAcctNum);
        if (acctCrPrflTMsg == null) {
            String[] args = {"DS_ACCT_CR_PRFL", "DS_ACCT_NUM=" + dsAcctNum };
            throw new S21AbendException(NFDM0005E, args);
        }

        setValue(acctCrPrflTMsg.crChkReqTpCd, CR_CHK_REQ_TP.HOLD);

        S21FastTBLAccessor.update(acctCrPrflTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(acctCrPrflTMsg.getReturnCode())) {
            String[] args = {"DS_ACCT_CR_PRFL" };
            throw new S21AbendException(NFDM0004E, args);
        }
    }

    /**
     * getCustCrPrflForUpdate
     * @param dsContrPk BigDecimal
     * @return CUST_CR_PRFLTMsg
     */
    private DS_ACCT_CR_PRFLTMsg getDsAcctCrPrflForUpdate(String dsAcctNum) {
        DS_ACCT_CR_PRFLTMsg inMsg = new DS_ACCT_CR_PRFLTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.dsAcctNum, dsAcctNum);
        return (DS_ACCT_CR_PRFLTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
    }
    //---- end 2016/03/18

    /**
     * updateContract
     * @param updateContr DS_CONTRTMsg
     * @param updateList List<DS_CONTR_DTLTMsg>
     * @param updateListMtr List<DS_CONTR_BLLG_MTRTMsg>
     * @param updateListEff List<DS_CONTR_PRC_EFFTMsg>
     */
    private void updateContract(DS_CONTRTMsg updateContr, List<DS_CONTR_DTLTMsg> updateList, List<DS_CONTR_BLLG_MTRTMsg> updateListMtr, List<DS_CONTR_PRC_EFFTMsg> updateListEff) {
        if (updateContr != null) {
            updateDsContr(updateContr);
        }

        if (updateList.size() > 0) {
            int updateCnt = S21FastTBLAccessor.update(updateList.toArray(new DS_CONTR_DTLTMsg[0]));
            if (updateCnt != updateList.size()) {
                String[] args = {"DS_CONTR_DTL" };
                throw new S21AbendException(NFDM0004E, args);
            }
        }

        if (updateListMtr.size() > 0) {
            int updateCnt = S21FastTBLAccessor.update(updateListMtr.toArray(new DS_CONTR_BLLG_MTRTMsg[0]));
            if (updateCnt != updateListMtr.size()) {
                String[] args = {"DS_CONTR_BLLG_MTR" };
                throw new S21AbendException(NFDM0004E, args);
            }
        }

        if (updateListEff.size() > 0) {
            int updateCnt = S21FastTBLAccessor.update(updateListEff.toArray(new DS_CONTR_PRC_EFFTMsg[0]));
            if (updateCnt != updateListEff.size()) {
                String[] args = {"DS_CONTR_PRC_EFF" };
                throw new S21AbendException(NFDM0004E, args);
            }
        }
    }

    /**
     * updateDsContr
     * @param dsContrTMsg DS_CONTRTMsg
     */
    private void updateDsContr(DS_CONTRTMsg dsContrTMsg) {
        S21FastTBLAccessor.update(dsContrTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrTMsg.getReturnCode())) {
            String[] args = {"DS_CONTR" };
            throw new S21AbendException(NFDM0004E, args);
        }
    }

    /**
     * createContrTrk
     * @param dsContrPk BigDecimal
     */
    private boolean createContrTrk(BigDecimal dsContrPk) {
        if (!NSXC001001ContractTracking.callContrTrk(this.glblCmpyCd, ContrTrkProcMode.USER_OPERATION.code, dsContrPk, this.userId, this.batProcDt, SVC_MEMO_RSN.STRATEGY_HOLD, this.svcMemoRsnDescTxt, ONBATCH_TYPE.BATCH)) {
            S21InfoLogOutput.println(NSXC001001ContractTracking.ERR_MSG_ID);
            return false;
        }
        return true;
    }

    private String toStringForList(List<String> list) {
        String lineSeparator = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                sb.append(lineSeparator);
            }
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    private List<S21MailAddress> getAddrTo(MailData mailData) {
        // mod 2016/01/13 default email address
        List<S21MailAddress> toAddrList = new ArrayList<S21MailAddress>();

        String toStr = null;
        if (hasValue(mailData.getFirstEmlAddr())) {
            toStr = mailData.getFirstEmlAddr();
            toAddrList.add(new S21MailAddress(this.glblCmpyCd, toStr));
        } else if (hasValue(mailData.getScdEmlAddr())) {
            toStr = mailData.getScdEmlAddr();
            toAddrList.add(new S21MailAddress(this.glblCmpyCd, toStr));
        } else {
            for (String add : defEmlAddList) {
                toStr = add;
                toAddrList.add(new S21MailAddress(this.glblCmpyCd, toStr));
            }
        }
        return toAddrList;
    }

    // START 2021/07/07 G.Delgado [QC#58946, MOD]
//    /**
//     * createContractRow
//     * @param rs ResultSet
//     * @return contractRowString
//     * @throws SQLException
//     */
//    private String createContractRow(ResultSet rs) throws SQLException {
//        StringBuilder sb = new StringBuilder();
//        sb.append(nullToEmpty(rs.getString("DS_CONTR_NUM")));
//        sb.append(DELIM);
//        sb.append(nullToEmpty(rs.getString("DS_CONTR_DTL_PK")));
//        sb.append(DELIM);
//        sb.append(nullToEmpty(ZYPDateUtil.formatEzd8ToDisp(rs.getString("CONTR_VRSN_EFF_FROM_DT"))));
//        sb.append(DELIM);
//        sb.append(nullToEmpty(ZYPDateUtil.formatEzd8ToDisp(rs.getString("CONTR_VRSN_EFF_THRU_DT"))));
//        sb.append(DELIM);
//        sb.append(nullToEmpty(ZYPDateUtil.formatEzd8ToDisp(rs.getString("CONTR_CLO_DT"))));
//        sb.append(DELIM);
//        sb.append(nullToEmpty(rs.getString("HLD_BLLG_RSN_NM")));
//        return sb.toString();
//    }

    /**
     * createContractRow
     * @param dsContr DS_CONTRTMsg
     * @return List<String>
     */
    private List<String> createContractRow(DS_CONTRTMsg dsContr) {
        List<String> row = new ArrayList<String>();
        row.add(nullToEmpty(dsContr.dsContrNum.getValue()));
        row.add(nullToEmpty(ZYPDateUtil.formatEzd8ToDisp(dsContr.contrVrsnEffFromDt.getValue())));
        row.add(nullToEmpty(ZYPDateUtil.formatEzd8ToDisp(dsContr.contrVrsnEffThruDt.getValue())));
        return row;
    }
    // END 2021/07/07 G.Delgado [QC#58946, MOD]

    /**
     * getWorkItemTran
     * @return PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement getWorkItemTran() throws SQLException {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("batProcDt", this.batProcDt);
        queryParam.put("cltStrgyStsCd", CLT_STRGY_STS.OPEN);
        queryParam.put("cltWrkItemCd", CONTR_HLD_STRTGY_NOTIFY_CLT_ITEM_CD);
        queryParam.put("cltWrkItemStsCd", CLT_WRK_ITEM_STS.PENDING);
        // START 2017/06/27 E.Kameishi [QC#18754, ADD]
        queryParam.put("wrkItmStsOpen", CLT_WRK_ITEM_STS.OPEN);
        // END 2017/06/27 E.Kameishi [QC#18754, ADD]
        queryParam.put("flgY", FLG_ON_Y);
        // START 2017/08/03 J.Kim [QC#18754, ADD]
        queryParam.put("wrkTpCd", CLT_WRK_TP.MANUAL);
        // END 2017/08/03 J.Kim [QC#18754, ADD]

        return this.ssmLLClient.createPreparedStatement("getWorkItemTran", queryParam, getExecPrm());
    }

    /**
     * getContractList
     * @param billToCustCd String
     * @return PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement getContractList(String billToCustCd) throws SQLException {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        // START 2021/08/16 G.Delgado [QC#59080, DEL]
        // queryParam.put("batProcDt", this.batProcDt);
        // queryParam.put("invTpCd", INV_TP.INVOICE);
        // END 2021/08/16 G.Delgado [QC#59080, DEL]
        queryParam.put("billToCustCd", billToCustCd);
        // START 2021/08/16 G.Delgado [QC#59080, DEL]
        // List<String> arCashApplyStsCdList = new ArrayList<String>();
        // arCashApplyStsCdList.add(AR_CASH_APPLY_STS.UNAPPLIED);
        // arCashApplyStsCdList.add(AR_CASH_APPLY_STS.PARTIAL);
        // queryParam.put("arCashApplyStsCdList", arCashApplyStsCdList);
        // queryParam.put("arTrxTpInv", AR_TRX_TP.INVOICE);
        // queryParam.put("arTrxTpDem", AR_TRX_TP.DEBIT_MEMO);
        // END 2021/08/16 G.Delgado [QC#59080, DEL]

        // START 2021/08/16 G.Delgado [QC#59080, ADD]
        List<String> inactiveDsContrStsCdList = new ArrayList<String>();
        inactiveDsContrStsCdList.add(DS_CONTR_STS.EXPIRED);
        inactiveDsContrStsCdList.add(DS_CONTR_STS.CANCELLED);
        inactiveDsContrStsCdList.add(DS_CONTR_STS.TERMINATED);
        queryParam.put("inactiveDsContrStsCdList", inactiveDsContrStsCdList);
        // END 2021/08/16 G.Delgado [QC#59080, ADD]

        return this.ssmLLClient.createPreparedStatement("getContractList", queryParam, getExecPrm());
    }

    // START 2021/07/07 G.Delgado [QC#58946, DEL]
//    /**
//     * getNotifyContractList
//     * @param billToCustCd String
//     * @return PreparedStatement
//     * @throws SQLException
//     */
//    private PreparedStatement getNotifyContractList(String billToCustCd) throws SQLException {
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put("glblCmpyCd", this.glblCmpyCd);
//        queryParam.put("invTpCd", INV_TP.INVOICE);
//        queryParam.put("billToCustCd", billToCustCd);
//        List<String> dsContrStsCdList = new ArrayList<String>();
//        dsContrStsCdList.add(DS_CONTR_STS.CANCELLED);
//        dsContrStsCdList.add(DS_CONTR_STS.EXPIRED);
//        queryParam.put("dsContrStsCdList", dsContrStsCdList);
//
//        return this.ssmLLClient.createPreparedStatement("getNotifyContractList", queryParam, getExecPrm());
//    }
    // END 2021/07/07 G.Delgado [QC#58946, DEL]

    /**
     * getCltStrtgyWrkItemTrxForUpdate
     * @param cltStrgyWorkItemTrxPk BigDecimal
     * @return CLT_STRGY_WRK_ITEM_TRXTMsg
     */
    private CLT_STRGY_WRK_ITEM_TRXTMsg getCltStrtgyWrkItemTrxForUpdate(BigDecimal cltStrgyWorkItemTrxPk) {
        CLT_STRGY_WRK_ITEM_TRXTMsg inMsg = new CLT_STRGY_WRK_ITEM_TRXTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.cltStrgyWrkItemTrxPk, cltStrgyWorkItemTrxPk);

        return (CLT_STRGY_WRK_ITEM_TRXTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * getDsContrForUpdate
     * @param dsContrPk BigDecimal
     * @return DS_CONTRTMsg
     */
    private DS_CONTRTMsg getDsContrForUpdate(BigDecimal dsContrPk) {
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * getDsContrDtlForUpdate
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_DTLTMsg
     */
    private DS_CONTR_DTLTMsg getDsContrDtlForUpdate(BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * getDsContrBllgMtrForUpdate
     * @param dsContrBllgMtrPk BigDecimal
     * @return DS_CONTR_BLLG_MTRTMsg
     */
    private DS_CONTR_BLLG_MTRTMsg getDsContrBllgMtrForUpdate(BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        return (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * getDsContrPrcEffForUpdate
     * @param dsContrPrcEffPk BigDecimal
     * @return DS_CONTR_PRC_EFFTMsg
     */
    private DS_CONTR_PRC_EFFTMsg getDsContrPrcEffForUpdate(BigDecimal dsContrPrcEffPk) {
        DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.dsContrPrcEffPk, dsContrPrcEffPk);
        return (DS_CONTR_PRC_EFFTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * getCustCrPrflForUpdate
     * @param dsContrPk BigDecimal
     * @return CUST_CR_PRFLTMsg
     */
    private CUST_CR_PRFLTMsg getCustCrPrflForUpdate(BigDecimal billToCustPk) {
        CUST_CR_PRFLTMsg inMsg = new CUST_CR_PRFLTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.billToCustPk, billToCustPk);
        return (CUST_CR_PRFLTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * get S21SsmExecutionParameter
     * @return S21SsmExecutionParameter
     */
    private S21SsmExecutionParameter getExecPrm() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    private static String nullToEmpty(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    // START 2021/07/07 G.Delgado [QC#58946, ADD]
    /**
     * addContractInfoToMap
     * @param contractMap Map<String, List<List<String>>>
     * @param dsContr DS_CONTRTMsg
     */
    private void addContractInfoToMap(Map<String, List<List<String>>> contractMap, DS_CONTRTMsg dsContr) {
        String contrNum = dsContr.dsContrNum.getValue();
        List<List<String>> infoList = contractMap.get(contrNum);
        if (infoList == null) {
            infoList = new ArrayList<List<String>>();
            infoList.add(createContractRow(dsContr));
            contractMap.put(contrNum, infoList);
        } else {
            infoList.add(createContractRow(dsContr));
        }
    }

    /**
     * formatContractRow
     * @param contrRow List<String>
     * @return String
     */
    private String formatContractRow(List<String> contrRow, int contrNumMaxLen) {
        StringBuilder sb = new StringBuilder();
        // Contract number + padding
        sb.append(ZYPCommonFunc.paddingSpace(contrRow.get(0), true, getColWidth(contrNumMaxLen)));
        // Start date + padding
        sb.append(ZYPCommonFunc.paddingSpace(contrRow.get(1), true, getColWidth(COL_START_DATE.length())));
        // End date
        sb.append(contrRow.get(2));
        return sb.toString();
    }

    /**
     * getColWidth
     * @param colDataMaxLen int
     * @return int
     */
    private int getColWidth(int colDataMaxLen) {
        // Get number of tabs for column
        int tabs = (colDataMaxLen / ALIGN_TAB_WIDTH) + COL_TAB_SPACING;

        return tabs * ALIGN_TAB_WIDTH;
    }

    /**
     * getMaxStrLength
     * @param strList List<String>
     * @return int
     */
    private int getMaxStrLength(List<String> strList) {
        int maxLen = 0;
        for (String str : strList) {
            if (str.length() > maxLen) {
                maxLen = str.length();
            }
        }
        return maxLen;
    }
    // END 2021/07/07 G.Delgado [QC#58946, ADD]
}
