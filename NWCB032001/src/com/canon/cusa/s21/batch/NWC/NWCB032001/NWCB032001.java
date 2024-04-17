package com.canon.cusa.s21.batch.NWC.NWCB032001;

import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.BIGDECIMAL_PERCENT_100;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.CUST_ISS_PO_NUM_HEADER;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.DATE_FORMAT_MM_DD_YYYY;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.DATE_FORMAT_YYYYMMDD;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.EASYPAC1_SHORTFALL_DEBIT_BILL_ONRY;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.ERR_MSG_BILL_TO_CUST_ACCT_CD;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.HYPHEN;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.INVOICE_KEY_LINE_NUM_001;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.INVOICE_KEY_LINE_SUB_NUM_001;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.INVOICE_KEY_NUM_001;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.INV_LINE_SUB_NUM_SET_PARENT;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.INV_PRINT_STS_CD_UNTREATED;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.MAIL_ERR_MSG_INVOICE_IMPORT;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.MAIL_ERR_MSG_TAX_CALCULATION_PROCESS;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.ORD_CATG_CTX_TP_CD_EASY_PACK1;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.ORD_CATG_CTX_TP_CD_EASY_PACK1_RETURN;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.PERCENT;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.PROGRAM_NM;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.SORT_NUM_1;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.SORT_NUM_2;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.SPACE;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.STRING_FORMAT_ZERO_PADDING;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.SYS_SRC_CD_NW;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.VAR_CHAR_CONST_KEY_DEFAULT_TAX_TRX_TP;
import static com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.VAR_CHAR_CONST_NM_INV_PRINT_SHORT_FALL_ITEM;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CCYTMsg;
import business.db.DS_INV_TPTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.DS_TAX_GRP_EXEMTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsgArray;
import business.db.MDSETMsg;
import business.db.SVC_ALLOC_TPTMsg;
import business.parts.NFZC309001PMsg;
import business.parts.NMZC260001PMsg;
import business.parts.NMZC260001_defSlsRepListPMsg;
import business.parts.NMZC260001_defSlsRepListPMsgArray;
import business.parts.NMZC610001PMsg;
import business.parts.NWZC036101PMsg;
import business.parts.NWZC036101_taxCalculateInputLinePMsg;
import business.parts.NWZC036101_taxCalculateOutputLinePMsg;
import business.parts.NWZC040001PMsg;
import business.parts.NWZC040002PMsg;
import business.parts.NWZC040003PMsg;
import business.parts.NWZC040005PMsg;
import business.parts.NWZC040007PMsg;

import com.canon.cusa.s21.api.NFC.NFZC309001.NFZC309001;
import com.canon.cusa.s21.api.NMZ.NMZC260001.NMZC260001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC036101.NWZC036101;
import com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant;
import com.canon.cusa.s21.api.NWZ.NWZC040001.NWZC040001;
import com.canon.cusa.s21.batch.NWC.NWCB032001.constant.NWCB032001Constant.MSG_ID;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Mail;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001MailSubstituteString;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_DR_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_DR_SUB_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SLS_TAX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TRX_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.EASY_PACK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * EasyPAC1 Shortfall Debit Creation Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/07   Fujitsu         S.Ohki          Create          N/A
 * 2016/08/22   Fujitsu         H.Nagashima     Update          QC#8659
 * 2016/09/19   SRAA            K.Aratani       Update          QC#14262
 * 2017/03/02   Fujitsu         H.Nagashima     Update          QC#16531
 * 2017/06/29   Fujitsu         S.Ohki          Update          QC#19623
 * 2017/07/04   Fujitsu         H.Ikeda         Update          QC#19636
 * 2017/07/21   Fujitsu         H.Ikeda         Update          QC#19738
 * 2017/07/21   Fujitsu         H.Ikeda         Update          QC#20029
 * 2017/10/23   Fujitsu         T.Aoi           Update          QC#20719
 * 2017/11/22   Hitachi         E.Kameishi      Update          QC#19735
 * 2018/08/09   Fujitsu         R.Nakamura      Update          QC#27442
 * 2018/08/31   Fujitsu         W.Honda         Update          QC#28052
 * 2018/09/11   Fujitsu         T.Aoi           Update          QC#28183
 * 2020/03/04   Fujitsu         Y.Kanefusa      Update          S21_NA#56085
 * 2020/04/24   CITS            K.Ogino         Update          QC#56638
 *</pre>
 */
public class NWCB032001 extends S21BatchMain {

    /** Term Code */
    private TERM_CD termCd;

    /** Error Record Count */
    private int errRecCnt;

    /** records */
    private int totalRecCnt;

    /** Normal Record Count */
    private int normalRecCnt;

    /** Function CcyScale */
    private int funcCcyScale;

    /** Function CcyCode */
    private String funcCcyCd;

    /** SSM */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Dfrd Acctg Rule Cd */
    private String dfrdAcctgRuleCd = null;

    /** Sales Date */
    private String slsDate = null;

    /** Mail template ID */
    private String mailTemplateId = null;

    /** error Bean List */
    private List<InvoiceDataBean> errBeanList;

    private String sfDummyMdseCd = null;   //QC16531 add

    // Add Start 2018/08/09 QC#27442
    /** Order Category Map */
    private Map<String, Object> ordCatgMap = null;
    // Add End 2018/08/09 QC#27442
    /**
     * @param args
     */
    public static void main(String[] args) {
        new NWCB032001().executeBatch();
    }

    @Override
    protected void initRoutine() {
        this.normalRecCnt = 0;
        this.errRecCnt = 0;
        this.termCd = TERM_CD.NORMAL_END;

        this.errBeanList = new ArrayList<InvoiceDataBean>();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Global Company Code"));
        }

        // Sales Date
        this.slsDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.slsDate)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Sales Date"));
        }

        // Mail Template
        mailTemplateId = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(mailTemplateId)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Mail Template Id"));
        }

        if (!getFuncCcyCd()) {
            throw new S21AbendException(MSG_ID.NWZM0257E.toString());
        }

        if (!getFuncCcyScale()) {
            throw new S21AbendException(MSG_ID.NWZM0368E.toString());
        }

        this.sfDummyMdseCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_INV_PRINT_SHORT_FALL_ITEM, this.glblCmpyCd);

    }

    @Override
    protected void mainRoutine() {

        // Create Debit Memo
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        StringBuilder slsDtLike = new StringBuilder();
        slsDtLike.append(this.slsDate.substring(0, 6)).append(PERCENT);
        ssmParam.put("slsDtLike", slsDtLike.toString());
        ssmParam.put("slsDt", this.slsDate);
        ssmParam.put("fnlzInvFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("autoDrCratFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("easyPackTpCd", EASY_PACK_TP.EASYPAC);  // 2017/09/01 Mods QC#17149(Sol#259)
        ssmParam.put("invTpCd", INV_TP.INVOICE);              // 2017/06/29 Mod QC#19623
        ssmParam.put("crDrRsnCd", CR_DR_RSN.EASY_PACK1);
        ssmParam.put("crDrSubRsnCd", CR_DR_SUB_RSN.SHORT_FALL_DEBIT);
        ssmParam.put("invLineSubNum", INV_LINE_SUB_NUM_SET_PARENT);

        List<String> ordCatgCtxTpCdList = new ArrayList<String>();
        ordCatgCtxTpCdList.add(ORD_CATG_CTX_TP_CD_EASY_PACK1);
        ordCatgCtxTpCdList.add(ORD_CATG_CTX_TP_CD_EASY_PACK1_RETURN);
        ssmParam.put("ordCatgCtxTpCdList", ordCatgCtxTpCdList);
        // Add Start 2018/08/09 QC#27442
        ssmParam.put("trxCd", TRX.SALES);
        ssmParam.put("trxRsnCd", TRX_RSN.CASH_LEASE_BILL_ONLY);
        // Add End 2018/08/09 QC#27442

        boolean rslt = (Boolean) ssmBatchClient.queryObject("getTargetInvoice", ssmParam, new DebitMemoCreator());
        if (!rslt) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);

        boolean isNeededMailInfo = TERM_CD.WARNING_END == this.termCd;
        isNeededMailInfo = isNeededMailInfo || TERM_CD.ABNORMAL_END == this.termCd;
        isNeededMailInfo = isNeededMailInfo || this.errRecCnt > 0;

        if (isNeededMailInfo) {
            if (!errBeanList.isEmpty()) {
                for (InvoiceDataBean errBean : errBeanList) {
                    if (!postErrorMail(errBean)) {
                        throw new S21AbendException(MSG_ID.NLBM0024E.toString(), toArray(PROGRAM_NM + ":mail sending process"));
                    }
                }
            }
        }
    }

    /**
     * <pre>
     * Post Error Mail
     * </pre>
     * @param invoiceBean InvoiceDataBean
     */
    private boolean postErrorMail(InvoiceDataBean bean) {

        final List<NWXC001001MailSubstituteString> sbsStrList = new ArrayList<NWXC001001MailSubstituteString>(3);

        NWXC001001MailSubstituteString sbsStr;

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("batchId");
        sbsStr.setSbstStr(this.getClass().getSimpleName());
        sbsStrList.add(sbsStr);

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("batchNm");
        sbsStr.setSbstStr(PROGRAM_NM);
        sbsStrList.add(sbsStr);

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("ErrorInfo");
        sbsStr.setSbstStr(bean.getMailErrMsg());
        sbsStrList.add(sbsStr);

        boolean isNormalEnd = new NWXC001001Mail().postMail(glblCmpyCd, BUSINESS_ID, mailTemplateId, sbsStrList);

        return isNormalEnd;

    }

    /**
     * ResultSet of SQL process.
     */
    protected class DebitMemoCreator extends S21SsmBooleanResultSetHandlerSupport {

        @Override
        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            Boolean rslt = Boolean.TRUE;

            // Has No Result Data
             if (!rs.next()) {
                 return true;
             }

            InvoiceDataBean invoiceBean = new InvoiceDataBean();
            BigDecimal itemSumAmt = BigDecimal.ZERO;
            BigDecimal itemRtnSumAmt = BigDecimal.ZERO;
            BigDecimal totalAmt = BigDecimal.ZERO;
            String billToCustAcctCdBk = rs.getString("BILL_TO_CUST_ACCT_CD");
            BigDecimal splyPgmMlyQuotQty = rs.getBigDecimal("SPLY_PGM_MLY_QUOT_QTY");
            setBeanCustFirstData(invoiceBean, rs);
            String billToCustAcctCd = "";
            // Mod Start 2018/08/31 QC#28052
//            String billToCustCd = "";
            String billToCustCdBk = rs.getString("BILL_TO_CUST_CD");
            // Mod End 2018/08/31 QC#28052
            do {
                billToCustAcctCd = rs.getString("BILL_TO_CUST_ACCT_CD");
                // Del Start 2018/08/31 QC#28052
//                billToCustCd = rs.getString("BILL_TO_CUST_CD");
                // Del End 2018/08/31 QC#28052
                String invTpCd = rs.getString("INV_TP_CD");
                BigDecimal ordQty = rs.getBigDecimal("ORD_QTY");
                BigDecimal areaOfPaperNum = rs.getBigDecimal("AREA_OF_PAPER_NUM");
                if (areaOfPaperNum == null) {
                    areaOfPaperNum = BigDecimal.ZERO;
                }
                BigDecimal splyUseQty = ordQty.multiply(areaOfPaperNum);

                if (billToCustAcctCdBk.equals(billToCustAcctCd)) {

                    if (INV_TP.CREDIT_MEMO.equals(invTpCd)) {
                        itemRtnSumAmt = itemRtnSumAmt.add(splyUseQty);
                    } else {
                        itemSumAmt = itemSumAmt.add(splyUseQty);
                    }

                } else {

                    totalAmt = totalAmt.add(itemSumAmt).subtract(itemRtnSumAmt);
                    // 2018/09/11 QC#28183 Mod Start
                    //if (totalAmt.compareTo(BigDecimal.ZERO) > 0) {
                    if (itemSumAmt.compareTo(BigDecimal.ZERO) > 0 || itemRtnSumAmt.compareTo(BigDecimal.ZERO) > 0) {
                    // 2018/09/11 QC#28183 Mod End
                        if (totalAmt.compareTo(splyPgmMlyQuotQty) < 0) {
                            // Add Start 2018/08/09 QC#27442
                            // Add Start 2018/08/31 QC#28052
//                            if (!ZYPCommonFunc.hasValue(billToCustCd)) {
                            if (!ZYPCommonFunc.hasValue(billToCustCdBk)) {
                            // Add Start 2018/08/31 QC#28052
                                totalAmt = BigDecimal.ZERO;
                            }
                            // Add End 2018/08/09 QC#27442
                            if (createDebitMemo(invoiceBean, totalAmt)) {
                                normalRecCnt++;
                                commit();
                            } else {
                                errRecCnt++;
                                rollback();
                            }
                            totalRecCnt++;
                        }
                    }

                    invoiceBean = new InvoiceDataBean();
                    setBeanCustFirstData(invoiceBean, rs);

                    itemSumAmt = BigDecimal.ZERO;
                    itemRtnSumAmt = BigDecimal.ZERO;
                    totalAmt = BigDecimal.ZERO;
                    billToCustAcctCdBk = rs.getString("BILL_TO_CUST_ACCT_CD");
                    // Add Start 2018/08/31 QC#28052
                    billToCustCdBk = rs.getString("BILL_TO_CUST_CD");
                    // Add Start 2018/08/31 QC#28052
                    splyPgmMlyQuotQty = rs.getBigDecimal("SPLY_PGM_MLY_QUOT_QTY");

                    if (INV_TP.CREDIT_MEMO.equals(invTpCd)) {
                        itemRtnSumAmt = itemRtnSumAmt.add(splyUseQty);
                    } else {
                        itemSumAmt = itemSumAmt.add(splyUseQty);
                    }
                }
            } while (rs.next());

            // Last Data
            totalAmt = totalAmt.add(itemSumAmt).subtract(itemRtnSumAmt);
            // 2018/09/11 QC#28183 Mod Start
            //if (totalAmt.compareTo(BigDecimal.ZERO) > 0) {
            if (itemSumAmt.compareTo(BigDecimal.ZERO) > 0 || itemRtnSumAmt.compareTo(BigDecimal.ZERO) > 0) {
            // 2018/09/11 QC#28183 Mod End
                if (totalAmt.compareTo(splyPgmMlyQuotQty) < 0) {
                    // Add Start 2018/08/09 QC#27442
                    // Add Start 2018/08/31 QC#28052
//                  if (!ZYPCommonFunc.hasValue(billToCustCd)) {
                    if (!ZYPCommonFunc.hasValue(billToCustCdBk)) {
                    // Add Start 2018/08/31 QC#28052
                        totalAmt = BigDecimal.ZERO;
                    }
                    // Add End 2018/08/09 QC#27442
                    if (createDebitMemo(invoiceBean, totalAmt)) {
                        normalRecCnt++;
                        commit();
                    } else {
                        errRecCnt++;
                        rollback();
                    }
                    totalRecCnt++;
                }
            }

            if (errRecCnt > 0) {
                rslt = Boolean.FALSE;
            }
            return rslt;
        }
    }

    /**
     * <pre>
     * Create Debit Memo
     * </pre>
     * @param invoiceBean InvoiceDataBean
     * @param totalAmt BigDecimal
     */
    private boolean createDebitMemo(InvoiceDataBean invoiceBean, BigDecimal totalAmt) {

        Map<String, Object> shipMap = getShipToInfo(invoiceBean);
        if (shipMap == null || shipMap.isEmpty()) {
            return false;
        }

        setBeanTotalData(invoiceBean, totalAmt);

//        if (!callPricingApi(invoiceBean, shipMap)) {
        if (!callTaxCalcApi(invoiceBean, shipMap)) {     //QC#16531 mod
            return false;
        }

        if (!callInvoiceImportApi(invoiceBean)) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Set Bean Customer First Data
     * </pre>
     * @param invoiceBean InvoiceDataBean
     * @param rs ResultSet
     */
    private void setBeanCustFirstData(InvoiceDataBean invoiceBean, ResultSet rs) throws SQLException {

        // Add Start 2018/08/09 QC#27442
        Map<String, Object> shipToCustMap = null;
        Map<String, Object> pmtTermMap = null;
        String tocCd = "";
        boolean noInvFlg = false;

        if (!ZYPCommonFunc.hasValue(rs.getString("BILL_TO_CUST_CD"))) {
            noInvFlg = true;
            String sellToCustCd = rs.getString("BILL_TO_CUST_ACCT_CD");
            if (getDefaultBillShip(invoiceBean, sellToCustCd)) {
                return;
            }
            shipToCustMap = getShipToCustInfo(invoiceBean.getShipToCustCd());
            pmtTermMap = getPmtTermInfo(invoiceBean.getBillToCustCd(), sellToCustCd);
            if (null == this.ordCatgMap) {
                this.ordCatgMap = getOrdCatgInfo();
            }
            // call NMZC2600 Default Sales Rep API
            NMZC260001PMsg nMZC260001PMsg = new NMZC260001PMsg();
            // 2020/04/27 QC#56638 Mod
            if (!callDefSlsRepApi(nMZC260001PMsg, shipToCustMap, invoiceBean)) {
                return;
            }
            String lineBizTpCd = (String) this.ordCatgMap.get("LINE_BIZ_TP_CD");
            String trtyGrpTpTxt = getTrtyGrpTpTxtFromDsOrdTpCd((String) this.ordCatgMap.get("DS_ORD_TP_CD"));
            List<String> trtyGrpTpCdList = new ArrayList<String>();
            if (ZYPCommonFunc.hasValue(trtyGrpTpTxt)) {
                trtyGrpTpCdList = Arrays.asList(trtyGrpTpTxt.split(","));
            }
            LINE_BIZ_ROLE_TPTMsg lineBizRoleTpTMsg = new LINE_BIZ_ROLE_TPTMsg();
            lineBizRoleTpTMsg.setSQLID("001");
            lineBizRoleTpTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            lineBizRoleTpTMsg.setConditionValue("primRepRoleFlg01", ZYPConstant.FLG_ON_Y);
            LINE_BIZ_ROLE_TPTMsgArray tMsgArray = (LINE_BIZ_ROLE_TPTMsgArray) EZDTBLAccessor.findByCondition(lineBizRoleTpTMsg);
            List<String> targetWriterList = new ArrayList<String>();
            if (tMsgArray != null && tMsgArray.length() > 0) {
                for (int i = 0; i < tMsgArray.length(); i++) {
                    LINE_BIZ_ROLE_TPTMsg tMsg = tMsgArray.no(i);
                    targetWriterList.add(tMsg.lineBizRoleTpCd.getValue());
                }
            }

            List<NMZC260001_defSlsRepListPMsg> matchLobSlsRepPMsgList = new ArrayList<NMZC260001_defSlsRepListPMsg>();
            List<NMZC260001_defSlsRepListPMsg> matchLobRoleSlsRepPMsgList = new ArrayList<NMZC260001_defSlsRepListPMsg>();

            NMZC260001_defSlsRepListPMsgArray defSlsRepMsgArray = nMZC260001PMsg.defSlsRepList;

            for (int i = 0; i < defSlsRepMsgArray.getValidCount(); i++) {
                NMZC260001_defSlsRepListPMsg defSlsRepPMsg = defSlsRepMsgArray.no(i);
                if (ZYPCommonFunc.hasValue(defSlsRepPMsg.xxRsltFlg) && ZYPConstant.FLG_ON_Y.equals(defSlsRepPMsg.xxRsltFlg.getValue())//
                        && !matchLobRoleSlsRepPMsgList.isEmpty()) {
                    continue;
                }
                if ((trtyGrpTpCdList.isEmpty() && defSlsRepPMsg.lineBizTpCd.getValue().equals(lineBizTpCd))
                        || (trtyGrpTpCdList.size() > 0 && trtyGrpTpCdList.contains(defSlsRepPMsg.trtyGrpTpCd.getValue()))) {
                    matchLobSlsRepPMsgList.add(defSlsRepPMsg);

                    String lineBizRoleTpCd = defSlsRepPMsg.lineBizRoleTpCd.getValue();
                    if (targetWriterList.contains(lineBizRoleTpCd)) {
                        matchLobRoleSlsRepPMsgList.add(defSlsRepPMsg);
                    }
                }
            }
            if (defSlsRepMsgArray.getValidCount() > 0) {
                if (matchLobRoleSlsRepPMsgList.size() == 1) {
                    tocCd = matchLobRoleSlsRepPMsgList.get(0).tocCd.getValue();
                }
            }

        }
        // Add End 2018/08/09 QC#27442

        // Mod Start 2018/08/09 QC#27742
        if (noInvFlg) {
            invoiceBean.setPayerCustCd(invoiceBean.getBillToCustCd());
            invoiceBean.setPmtTermCd((String) pmtTermMap.get("PMT_TERM_CD"));
            invoiceBean.setPmtTermCashDiscCd((String) pmtTermMap.get("PMT_TERM_CASH_DISC_CD"));
            invoiceBean.setSellToCustCd((String) shipToCustMap.get("SHIP_TO_CUST_ACCT_CD"));
            invoiceBean.setDealCcyCd(this.funcCcyCd);
            invoiceBean.setDsOrdTpCd((String) this.ordCatgMap.get("DS_ORD_TP_CD"));
            invoiceBean.setDsInvTpCd((String) this.ordCatgMap.get("DS_INV_TP_CD"));
            invoiceBean.setSlsRepTocCd(tocCd);
            invoiceBean.setLineBizTpCd((String) this.ordCatgMap.get("LINE_BIZ_TP_CD"));
            invoiceBean.setDsBizAreaCd(DS_BIZ_AREA.SUPPLIES);
            invoiceBean.setBillToCustAcctCd(rs.getString("BILL_TO_CUST_ACCT_CD"));
            invoiceBean.setDsOrdCatgCd((String) this.ordCatgMap.get("DS_ORD_CATG_CD"));
            invoiceBean.setShipFromInvtyLocCd(rs.getString("SHIP_FROM_INVTY_LOC_CD"));
            invoiceBean.setShipToCustCd((String) shipToCustMap.get("SHIP_TO_CUST_CD"));
            invoiceBean.setDropShipFlg(ZYPConstant.FLG_OFF_N);
            invoiceBean.setShipToLocNm((String) shipToCustMap.get("SHIP_TO_LOC_NM"));
            invoiceBean.setShipToAddlLocNm((String) shipToCustMap.get("SHIP_TO_ADDL_LOC_NM"));
            invoiceBean.setShipToFirstLineAddr((String) shipToCustMap.get("SHIP_TO_FIRST_LINE_ADDR"));
            invoiceBean.setShipToScdLineAddr((String) shipToCustMap.get("SHIP_TO_SCD_LINE_ADDR"));
            invoiceBean.setShipToThirdLineAddr((String) shipToCustMap.get("SHIP_TO_THIRD_LINE_ADDR"));
            invoiceBean.setShipToFrthLineAddr((String) shipToCustMap.get("SHIP_TO_FRTH_LINE_ADDR"));
            invoiceBean.setShipToCtyAddr((String) shipToCustMap.get("SHIP_TO_CTY_ADDR"));
            invoiceBean.setShipToCntyNm((String) shipToCustMap.get("SHIP_TO_CNTY_NM"));
            invoiceBean.setShipToProvNm((String) shipToCustMap.get("SHIP_TO_PROV_NM"));
            invoiceBean.setShipToStCd((String) shipToCustMap.get("SHIP_TO_ST_CD"));
            invoiceBean.setShipToPostCd((String) shipToCustMap.get("SHIP_TO_POST_CD"));
            invoiceBean.setShipToCtryCd((String) shipToCustMap.get("SHIP_TO_CTRY_CD"));
            invoiceBean.setShipToFirstRefCmntTxt((String) shipToCustMap.get("SHIP_TO_FIRST_REF_CMNT_TXT"));
            invoiceBean.setShipToScdRefCmntTxt((String) shipToCustMap.get("SHIP_TO_SCD_REF_CMNT_TXT"));
            invoiceBean.setShipToCustAcctCd((String) shipToCustMap.get("SHIP_TO_CUST_ACCT_CD"));
            invoiceBean.setTrxCd(rs.getString("TRX_CD"));
            invoiceBean.setTrxRsnCd(rs.getString("TRX_RSN_CD"));
            invoiceBean.setOrdQty(rs.getBigDecimal("ORD_QTY"));
            invoiceBean.setSetMdseCd(rs.getString("SET_MDSE_CD"));
            invoiceBean.setSplyPgmUnitAmtRate(rs.getBigDecimal("SPLY_PGM_UNIT_AMT_RATE"));
            invoiceBean.setSplyPgmMlyQuotQty(rs.getBigDecimal("SPLY_PGM_MLY_QUOT_QTY"));
            invoiceBean.setOrdCatgCtxTpCd((String) this.ordCatgMap.get("ORD_CATG_CTX_TP_CD"));
            invoiceBean.setMdseTpCd(rs.getString("MDSE_TP_CD"));
            invoiceBean.setThisMthTotStdCostAmt(rs.getBigDecimal("THIS_MTH_TOT_STD_COST_AMT"));
            invoiceBean.setAreaOfPaperNum(rs.getBigDecimal("AREA_OF_PAPER_NUM"));
            invoiceBean.setBasePkgUomCd(PKG_UOM.EACH);
            invoiceBean.setDfnLineBizTpCd(rs.getString("DFN_LINE_BIZ_TP_CD"));
        } else {
            invoiceBean.setBillToCustCd(rs.getString("BILL_TO_CUST_CD"));
            invoiceBean.setPayerCustCd(rs.getString("PAYER_CUST_CD"));
            invoiceBean.setPmtTermCd(rs.getString("PMT_TERM_CD"));
            invoiceBean.setPmtTermCashDiscCd(rs.getString("PMT_TERM_CASH_DISC_CD"));
            invoiceBean.setSellToCustCd(rs.getString("SELL_TO_CUST_CD"));
            invoiceBean.setDealCcyCd(rs.getString("DEAL_CCY_CD"));
            invoiceBean.setDsOrdTpCd(rs.getString("DS_ORD_TP_CD"));
            invoiceBean.setDsInvTpCd(rs.getString("DS_INV_TP_CD"));
            invoiceBean.setSlsRepTocCd(rs.getString("SLS_REP_TOC_CD"));
            invoiceBean.setLineBizTpCd(rs.getString("LINE_BIZ_TP_CD"));
            invoiceBean.setDsBizAreaCd(rs.getString("DS_BIZ_AREA_CD"));
            invoiceBean.setBillToCustAcctCd(rs.getString("BILL_TO_CUST_ACCT_CD"));
            invoiceBean.setDsOrdCatgCd(rs.getString("DS_ORD_CATG_CD"));
            invoiceBean.setShipFromInvtyLocCd(rs.getString("SHIP_FROM_INVTY_LOC_CD"));
            invoiceBean.setShipToCustCd(rs.getString("SHIP_TO_CUST_CD"));
            invoiceBean.setDropShipFlg(rs.getString("DROP_SHIP_FLG"));
            invoiceBean.setShipToLocNm(rs.getString("SHIP_TO_LOC_NM"));
            invoiceBean.setShipToAddlLocNm(rs.getString("SHIP_TO_ADDL_LOC_NM"));
            invoiceBean.setShipToFirstLineAddr(rs.getString("SHIP_TO_FIRST_LINE_ADDR"));
            invoiceBean.setShipToScdLineAddr(rs.getString("SHIP_TO_SCD_LINE_ADDR"));
            invoiceBean.setShipToThirdLineAddr(rs.getString("SHIP_TO_THIRD_LINE_ADDR"));
            invoiceBean.setShipToFrthLineAddr(rs.getString("SHIP_TO_FRTH_LINE_ADDR"));
            invoiceBean.setShipToCtyAddr(rs.getString("SHIP_TO_CTY_ADDR"));
            invoiceBean.setShipToCntyNm(rs.getString("SHIP_TO_CNTY_NM"));
            invoiceBean.setShipToProvNm(rs.getString("SHIP_TO_PROV_NM"));
            invoiceBean.setShipToStCd(rs.getString("SHIP_TO_ST_CD"));
            invoiceBean.setShipToPostCd(rs.getString("SHIP_TO_POST_CD"));
            invoiceBean.setShipToCtryCd(rs.getString("SHIP_TO_CTRY_CD"));
            invoiceBean.setShipToFirstRefCmntTxt(rs.getString("SHIP_TO_FIRST_REF_CMNT_TXT"));
            invoiceBean.setShipToScdRefCmntTxt(rs.getString("SHIP_TO_SCD_REF_CMNT_TXT"));
            invoiceBean.setShipToCustAcctCd(rs.getString("SHIP_TO_CUST_ACCT_CD"));
            invoiceBean.setTrxCd(rs.getString("TRX_CD"));
            invoiceBean.setTrxRsnCd(rs.getString("TRX_RSN_CD"));
            invoiceBean.setOrdQty(rs.getBigDecimal("ORD_QTY"));
            invoiceBean.setSetMdseCd(rs.getString("SET_MDSE_CD"));
            invoiceBean.setSplyPgmUnitAmtRate(rs.getBigDecimal("SPLY_PGM_UNIT_AMT_RATE"));
            invoiceBean.setSplyPgmMlyQuotQty(rs.getBigDecimal("SPLY_PGM_MLY_QUOT_QTY"));
            invoiceBean.setOrdCatgCtxTpCd(rs.getString("ORD_CATG_CTX_TP_CD"));
            invoiceBean.setMdseTpCd(rs.getString("MDSE_TP_CD"));
            invoiceBean.setThisMthTotStdCostAmt(rs.getBigDecimal("THIS_MTH_TOT_STD_COST_AMT"));
            invoiceBean.setAreaOfPaperNum(rs.getBigDecimal("AREA_OF_PAPER_NUM"));
            invoiceBean.setBasePkgUomCd(rs.getString("BASE_PKG_UOM_CD"));
            invoiceBean.setDfnLineBizTpCd(rs.getString("DFN_LINE_BIZ_TP_CD"));
        }
        // Mod End 2018/08/09 QC#27742

        // Customer Issue Purchase Order Number
        StringBuilder sbCustIssPoNum = new StringBuilder();
        String date = ZYPDateUtil.DateFormatter(slsDate, DATE_FORMAT_YYYYMMDD, DATE_FORMAT_MM_DD_YYYY);
        String poNum = String.format(STRING_FORMAT_ZERO_PADDING, totalRecCnt+1);
        sbCustIssPoNum.append(CUST_ISS_PO_NUM_HEADER).append(HYPHEN).append(date).append(HYPHEN).append(String.valueOf(poNum));
        invoiceBean.setCustIssPoNum(sbCustIssPoNum.toString());

        // Invoice Number
        DS_INV_TPTMsg dsInvTpTMsg = new DS_INV_TPTMsg();
        ZYPEZDItemValueSetter.setValue(dsInvTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsInvTpTMsg.dsInvTpCd, invoiceBean.getDsInvTpCd());
        dsInvTpTMsg = (DS_INV_TPTMsg) S21CacheTBLAccessor.findByKey(dsInvTpTMsg);
        // START 2017/11/22 E.Kameishi [QC#19735, MOD]
        //invoiceBean.setInvNum(ZYPExtnNumbering.getUniqueID(glblCmpyCd, dsInvTpTMsg.autoSeqCd.getValue()));
        invoiceBean.setInvNum(ZYPMaxTenDigitsNumbering.getUniqueID(glblCmpyCd, dsInvTpTMsg.autoSeqCd.getValue()));
        // END 2017/11/22 E.Kameishi [QC#19735, MOD]

        // Net Due Date
        String netDueDt = getNetDueDt(invoiceBean);
        invoiceBean.setNetDueDt(netDueDt);
    }

    /**
     * <pre>
     * Set Bean Total Data
     * </pre>
     * @param invoiceBean InvoiceDataBean
     * @param totalAmt BigDecimal
     */
    private void setBeanTotalData(InvoiceDataBean invoiceBean, BigDecimal totalAmt) {

        invoiceBean.setSplyUseTotalQty(totalAmt);

        BigDecimal splyPgmMlyQuotQty = invoiceBean.getSplyPgmMlyQuotQty();
        BigDecimal splyPgmShipQty = splyPgmMlyQuotQty.subtract(totalAmt);
        invoiceBean.setSplyPgmShipQty(splyPgmShipQty);

        BigDecimal splyPgmUnitAmtRate = invoiceBean.getSplyPgmUnitAmtRate();
        BigDecimal dealNetUnitPrcAmt = splyPgmShipQty.multiply(splyPgmUnitAmtRate);
        BigDecimal dealNetUnitPrcAmtRound = dealNetUnitPrcAmt.setScale(funcCcyScale, RoundingMode.HALF_UP);

        invoiceBean.setDealNetUnitPrcAmt(dealNetUnitPrcAmtRound);
        invoiceBean.setDealGrsUnitPrcAmt(dealNetUnitPrcAmtRound);
        invoiceBean.setDealGrsTotPrcAmt(dealNetUnitPrcAmtRound);
        invoiceBean.setInvLineDealNetAmt(dealNetUnitPrcAmtRound);
    }

    // 2017/07/04 QC#19636 Mod Start
//    /**
//     * <pre>
//     * Get Ship To Info
//     * </pre>
//     * @param invoiceBean InvoiceDataBean
//     * @return Map
//     */
//    private Map<String, Object> getShipToInfo(InvoiceDataBean invoiceBean) {
//        NMZC610001PMsg pmsg = new NMZC610001PMsg();
//        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(pmsg.xxModeCd, NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP);
//        ZYPEZDItemValueSetter.setValue(pmsg.dsAcctNum_I1, invoiceBean.getBillToCustAcctCd());
//        ZYPEZDItemValueSetter.setValue(pmsg.billToCustCd, invoiceBean.getBillToCustCd());   //QC#8659 add
//
//        NMZC610001 api = new NMZC610001();
//        api.execute(pmsg, ONBATCH_TYPE.BATCH);
//
//        boolean errFlg = false;
//        List<S21ApiMessage> msgListIn = S21ApiUtil.getXxMsgList(pmsg);
//        for (S21ApiMessage msg : msgListIn) {
//            String msgId = msg.getXxMsgid();
//            if (ZYPCommonFunc.hasValue(msgId)) {
//                S21InfoLogOutput.println(msgId);
//                errFlg = true;
//            }
//        }
//
//        if (errFlg) {
//            return null;
//        }
//
//        // Ship To Customer
//        final Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
//        ssmParam.put("shipToCustCd", pmsg.DefaultBillShipList.no(0).shipToCustCd.getValue());
//        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
//
//        Map<String, Object> resMap = (Map<String, Object>) ssmBatchClient.queryObject("getShipToInfo", ssmParam);
//
//        if (resMap == null || resMap.isEmpty()) {
//            S21InfoLogOutput.println(MSG_ID.NWCM0115E.toString(), toArray("Ship To Cust"));
//        }
//
//        return resMap;
//    }

    /**
     * <pre>
     * Get Ship To Info
     * </pre>
     * @param invoiceBean InvoiceDataBean
     * @return Map
     */
    private Map<String, Object> getShipToInfo(InvoiceDataBean invoiceBean) {
        // Ship To Customer
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("shipToCustCd", invoiceBean.getShipToCustCd());

        Map<String, Object> resMap = (Map<String, Object>) ssmBatchClient.queryObject("getShipToCust", ssmParam);

        if (resMap == null || resMap.isEmpty()) {
            S21InfoLogOutput.println(MSG_ID.NWCM0115E.toString(), toArray("Ship To Cust"));
        }

        return resMap;
    }

    // Add Start 2018/08/09 QC#27442
    /**
     * <pre>
     * getShipToCustInfo
     * </pre>
     * @param shipToCustCd String
     * @return Map
     */
    private Map<String, Object> getShipToCustInfo(String shipToCustCd) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("shipToCustCd", shipToCustCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        Map<String, Object> resMap = (Map<String, Object>) ssmBatchClient.queryObject("getShipToCustInfo", ssmParam);

        if (resMap == null || resMap.isEmpty()) {
            S21InfoLogOutput.println(MSG_ID.NWCM0115E.toString(), toArray("Ship To Cust"));
        }

        return resMap;
    }

    /**
     * <pre>
     * Get Order Category Info
     * </pre>
     * @return Map
     */
    private Map<String, Object> getOrdCatgInfo() {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP_CD_EASY_PACK1);
        ssmParam.put("invTpCd", INV_TP.INVOICE);

        Map<String, Object> resMap = (Map<String, Object>) ssmBatchClient.queryObject("getOrdCatgInfo", ssmParam);

        if (resMap == null || resMap.isEmpty()) {
            S21InfoLogOutput.println(MSG_ID.NWCM0115E.toString(), toArray("Order Category"));
        }

        return resMap;
    }

    /**
     * <pre>
     * Get Order Category Info
     * </pre>
     * @param billToCustCd String
     * @param sellToCustCd String
     * @return Map
     */
    private Map<String, Object> getPmtTermInfo(String billToCustCd, String sellToCustCd) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("billToCustCd", billToCustCd);
        ssmParam.put("sellToCustCd", sellToCustCd);
        ssmParam.put("sort1", SORT_NUM_1);
        ssmParam.put("sort2", SORT_NUM_2);

        Map<String, Object> resMap = (Map<String, Object>) ssmBatchClient.queryObject("getPmtTermInfo", ssmParam);

        if (resMap == null || resMap.isEmpty()) {
            S21InfoLogOutput.println(MSG_ID.NWCM0115E.toString(), toArray("Payment Term"));
        }

        return resMap;
    }

    /**
     * <pre>
     * Call Default Sales Rep API
     * </pre>
     * @param pMsg NMZC260001PMsg
     * @param shipToCustMap Map<String, Object>
     * @return boolean
     */
    private boolean callDefSlsRepApi(NMZC260001PMsg pMsg, Map<String, Object> shipToCustMap, InvoiceDataBean invoiceBean) {

        String shipToCustCd = (String) shipToCustMap.get("SHIP_TO_CUST_CD");
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, shipToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, (String) this.ordCatgMap.get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, (String) this.ordCatgMap.get("DS_ORD_TP_CD"));
        // 2020/04/27 QC#56638 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, invoiceBean.getBillToCustCd());
        // 2020/04/27 QC#56638 Add End

        // call NMZC2600 Dafault Sales Rep API
        new NMZC260001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();

                if (msgId.endsWith("E")) {
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    S21InfoLogOutput.println(msgId.toString(), msgPrms);
                    return false;
                // 2020/04/27 QC#56638 Add Start
                } else if (ZYPCommonFunc.hasValue(shipToCustCd) && msgId.endsWith(MSG_ID.NWAM0757W.toString())) {
                    S21InfoLogOutput.println(MSG_ID.NWAM0757W.toString());
                } else if (ZYPCommonFunc.hasValue(pMsg.billToCustCd) && msgId.endsWith(MSG_ID.NWAM0981W.toString())) {
                    S21InfoLogOutput.println(MSG_ID.NWAM0981W.toString());
                }
                boolean isShipBase = isSlsReqDef(pMsg.dsOrdTpCd.getValue());
                if (isShipBase && ZYPCommonFunc.hasValue(shipToCustCd) && msgId.endsWith("W")) {
                    S21InfoLogOutput.println(MSG_ID.NWAM0757W.toString());
                } else if (!isShipBase && ZYPCommonFunc.hasValue(pMsg.billToCustCd) && msgId.endsWith("W")) {
                    S21InfoLogOutput.println(MSG_ID.NWAM0981W.toString());
                }
                // 2020/04/27 QC#56638 Add End
            }
        }

        return true;
    }

    /**
     * <pre>
     * Get Cnty Info
     * </pre>
     * @param invoiceBean InvoiceDataBean
     * @return Map
     */
    private Map<String, Object> getCntyInfo(InvoiceDataBean invoiceBean) {
        // 2017/07/21 QC#19738 Mod Start
        if (!ZYPCommonFunc.hasValue(invoiceBean.getShipToCntyNm()) || !ZYPCommonFunc.hasValue(invoiceBean.getShipToStCd())) {
            return null;
        }
        // 2017/07/21 QC#19738 Mod End
        // Ship To Customer
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("cntyNm", invoiceBean.getShipToCntyNm());
        ssmParam.put("stCd", invoiceBean.getShipToStCd());

        Map<String, Object> resMap = (Map<String, Object>) ssmBatchClient.queryObject("getCnty", ssmParam);

        if (resMap == null || resMap.isEmpty()) {
            S21InfoLogOutput.println(MSG_ID.NWCM0115E.toString(), toArray("CNTY"));
        }

        return resMap;
    }
    // 2017/07/04 QC#19636 Mod End

    /**
     * <pre>
     * Get Net Due Date
     * </pre>
     * @param invoiceBean InvoiceDataBean
     * @return String
     */
    private String getNetDueDt(InvoiceDataBean invoiceBean) {

        // Due Date Calculation API
        NFZC309001PMsg param = new NFZC309001PMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.pmtTermCashDiscCd, invoiceBean.getPmtTermCashDiscCd());
        ZYPEZDItemValueSetter.setValue(param.trxDt, slsDate);

        NFZC309001 dueDateCalculationAPI = new NFZC309001();
        dueDateCalculationAPI.execute(param, ONBATCH_TYPE.BATCH);
        if (param.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < param.xxMsgIdList.getValidCount(); i++) {
                S21InfoLogOutput.println(param.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            return null;
        }
        return param.dueDt.getValue();
    }
//QC#16531 del Start
//    /**
//     * <pre>
//     * Call Pricing API
//     * </pre>
//     * @param invoiceBean InvoiceDataBean
//     * @param shipMap Map
//     */
//    private boolean callPricingApi(InvoiceDataBean invoiceBean, Map<String, Object> shipMap) {
//
//        NWZC157001PMsg pricingParam = new NWZC157001PMsg();
//        createPricingApiParamHdr(invoiceBean, pricingParam);
//        createPricingApiParamLine(invoiceBean, shipMap, pricingParam);
//
//        NWZC157001 prcAPI = new NWZC157001();
//        prcAPI.execute(pricingParam, ONBATCH_TYPE.BATCH);
//
//        List<InvoiceLineDtlBean> dtlBeanList = invoiceBean.getInvLineDtlBeanList();
//        List<String> errMsgList = new ArrayList<String>();
//
//        List<S21ApiMessage> msgListIn = S21ApiUtil.getXxMsgList(pricingParam);
//        for (S21ApiMessage msg : msgListIn) {
//            String msgId = msg.getXxMsgid();
//            if (ZYPCommonFunc.hasValue(msgId)) {
//                S21InfoLogOutput.println(msgId);
//                errMsgList.add(msgId);
//            }
//        }
//
//        BigDecimal autoPrcAmtRateTotal = BigDecimal.ZERO;
//
//        for (int i = 0; i < pricingParam.NWZC157002PMsg.getValidCount(); i++) {
//
//            NWZC157002PMsg prcLineParam = pricingParam.NWZC157002PMsg.no(i);
//            List<S21ApiMessage> lineMsgListIn = S21ApiUtil.getXxMsgList(prcLineParam);
//
//            for (S21ApiMessage msg : lineMsgListIn) {
//                String msgId = msg.getXxMsgid();
//                if (ZYPCommonFunc.hasValue(msgId)) {
//                    S21InfoLogOutput.println(msgId);
//                    errMsgList.add(msgId);
//                }
//            }
//
//            for (int j = 0; j < prcLineParam.NWZC157003PMsg.getValidCount(); j++) {
//                NWZC157003PMsg prcFactorParam = prcLineParam.NWZC157003PMsg.no(j);
//                List<S21ApiMessage> factMsgListIn = S21ApiUtil.getXxMsgList(prcFactorParam);
//                for (S21ApiMessage msg : factMsgListIn) {
//                    String msgId = msg.getXxMsgid();
//                    if (ZYPCommonFunc.hasValue(msgId)) {
//                        S21InfoLogOutput.println(msgId);
//                        errMsgList.add(msgId);
//                    }
//                }
//
//                String prcCondTpCd = prcFactorParam.prcCondTpCd.getValue();
//                String dsSlsTaxTpCd = "";
//
//                if (!PRC_DTL_GRP.TAX.equals(prcFactorParam.prcDtlGrpCd.getValue())) {
//                    continue;
//                }
//
//                if (PRC_COND_TP.ITEM_TAX1.equals(prcCondTpCd)) {
//                    dsSlsTaxTpCd = DS_SLS_TAX_TP.STATE_TAX;
//                } else if (PRC_COND_TP.ITEM_TAX2.equals(prcCondTpCd)) {
//                    dsSlsTaxTpCd = DS_SLS_TAX_TP.COUNTY_TAX;
//                } else if (PRC_COND_TP.ITEM_TAX3.equals(prcCondTpCd)) {
//                    dsSlsTaxTpCd = DS_SLS_TAX_TP.CITY_TAX;
//                } else {
//                    continue;
//                }
//
//                if (!ZYPCommonFunc.hasValue(prcFactorParam.autoPrcAmtRate)) {
//                    continue;
//                }
//
//                InvoiceLineDtlBean dtlBean = new InvoiceLineDtlBean();
//                dtlBean.setDsSlsTaxTpCd(dsSlsTaxTpCd);
//                dtlBean.setDealSlsTaxAmt(prcFactorParam.unitPrcAmt.getValue());
//                dtlBean.setFuncSlsTaxAmt(prcFactorParam.unitPrcAmt.getValue());
//                dtlBean.setSlsTaxPct(prcFactorParam.autoPrcAmtRate.getValue());
//                dtlBeanList.add(dtlBean);
//                autoPrcAmtRateTotal = autoPrcAmtRateTotal.add(prcFactorParam.autoPrcAmtRate.getValue());
//            }
//        }
//
//        invoiceBean.setTaxPct(autoPrcAmtRateTotal);
//        invoiceBean.setInvLineDealTaxAmt(pricingParam.NWZC157004PMsg.no(0).xxTotTaxAmt.getValue());
//
//        if (!errMsgList.isEmpty() || dtlBeanList == null || dtlBeanList.isEmpty()) {
//            setError(invoiceBean, MSG_ID.NWCM0113E.toString(), MAIL_ERR_MSG_PRICING_PROCESS);
//            return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * <pre>
//     * Create Pricing API Parameter Header
//     * </pre>
//     * @param invoiceBean InvoiceDataBean
//     * @param pricingParam NWZC157001PMsg
//     */
//    private void createPricingApiParamHdr(InvoiceDataBean invoiceBean, NWZC157001PMsg pricingParam) {
//        ZYPEZDItemValueSetter.setValue(pricingParam.glblCmpyCd, this.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(pricingParam.dsOrdTpCd, invoiceBean.getDsOrdTpCd());
//        ZYPEZDItemValueSetter.setValue(pricingParam.xxModeCd, NWZC157001.RECALC);
//        ZYPEZDItemValueSetter.setValue(pricingParam.prcBaseDt, slsDate);
//        ZYPEZDItemValueSetter.setValue(pricingParam.prcCtxTpCd, PRC_CTX_TP.SERVICE_PRICING);
//        ZYPEZDItemValueSetter.setValue(pricingParam.lineBizTpCd, invoiceBean.getDfnLineBizTpCd());
//        ZYPEZDItemValueSetter.setValue(pricingParam.dsAcctNum, invoiceBean.getSellToCustCd());
//        ZYPEZDItemValueSetter.setValue(pricingParam.taxCalcFlg, ZYPConstant.FLG_ON_Y);
//        ZYPEZDItemValueSetter.setValue(pricingParam.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);
//
//    }
//
//    /**
//     * <pre>
//     * Create Pricing API Parameter Line
//     * </pre>
//     * @param invoiceBean InvoiceDataBean
//     * @param shipMap Map
//     * @param pricingParam NWZC157001PMsg
//     */
//    private void createPricingApiParamLine(InvoiceDataBean invoiceBean, Map<String, Object> shipMap, NWZC157001PMsg pricingParam) {
//
//        NWZC157002PMsg prcLineParam = pricingParam.NWZC157002PMsg.no(0);
//        ZYPEZDItemValueSetter.setValue(prcLineParam.trxLineNum, TRX_LINE_KEY_NUM);
//        ZYPEZDItemValueSetter.setValue(prcLineParam.trxLineSubNum, TRX_LINE_KEY_NUM);
//        ZYPEZDItemValueSetter.setValue(prcLineParam.billToCustCd, invoiceBean.getBillToCustCd());
//        ZYPEZDItemValueSetter.setValue(prcLineParam.shipToCustCd, (String) shipMap.get("SHIP_TO_CUST_CD"));
//        ZYPEZDItemValueSetter.setValue(prcLineParam.dsAcctNum_SH, (String) shipMap.get("SELL_TO_CUST_CD"));
//        ZYPEZDItemValueSetter.setValue(prcLineParam.dsAcctNum_BL, invoiceBean.getBillToCustAcctCd());
//        ZYPEZDItemValueSetter.setValue(prcLineParam.ccyCd, invoiceBean.getDealCcyCd());
//        String ezContract = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_INV_PRINT_SHORT_FALL_ITEM, this.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(prcLineParam.mdseCd, ezContract);
//        ZYPEZDItemValueSetter.setValue(prcLineParam.pkgUomCd, invoiceBean.getBasePkgUomCd());
//        ZYPEZDItemValueSetter.setValue(prcLineParam.ordQty, BigDecimal.ONE);
//        ZYPEZDItemValueSetter.setValue(prcLineParam.ordCustUomQty, BigDecimal.ONE);
//        ZYPEZDItemValueSetter.setValue(prcLineParam.invQty, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(prcLineParam.firstLineAddr_SH, (String) shipMap.get("FIRST_LINE_ADDR"));
//        ZYPEZDItemValueSetter.setValue(prcLineParam.scdLineAddr_SH, (String) shipMap.get("SCD_LINE_ADDR"));
//        ZYPEZDItemValueSetter.setValue(prcLineParam.ctyAddr_SH, (String) shipMap.get("CTY_ADDR"));
//        ZYPEZDItemValueSetter.setValue(prcLineParam.stCd_SH, (String) shipMap.get("ST_CD"));
//        ZYPEZDItemValueSetter.setValue(prcLineParam.cntyNm_SH, (String) shipMap.get("CNTY_NM"));
//        ZYPEZDItemValueSetter.setValue(prcLineParam.postCd_SH, (String) shipMap.get("POST_CD"));
//        ZYPEZDItemValueSetter.setValue(prcLineParam.ctryCd_SH, (String) shipMap.get("CTRY_CD"));
//        ZYPEZDItemValueSetter.setValue(prcLineParam.slsRepOrSlsTeamTocCd, invoiceBean.getSlsRepTocCd());
//        ZYPEZDItemValueSetter.setValue(prcLineParam.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
//        pricingParam.NWZC157002PMsg.setValidCount(1);
//
//        createPricingApiParamPriceFactor(invoiceBean, shipMap, prcLineParam);
//    }
//
//    /**
//     * <pre>
//     * Create Pricing API Parameter Price Factor
//     * </pre>
//     * @param invoiceBean InvoiceDataBean
//     * @param shipMap Map
//     * @param prcLineParam NWZC157002PMsg
//     */
//    private void createPricingApiParamPriceFactor(InvoiceDataBean invoiceBean, Map<String, Object> shipMap, NWZC157002PMsg prcLineParam) {
//
//        NWZC157003PMsg prcFactorParam = prcLineParam.NWZC157003PMsg.no(0);
//        ZYPEZDItemValueSetter.setValue(prcFactorParam.trxLineNum, TRX_LINE_KEY_NUM);
//        ZYPEZDItemValueSetter.setValue(prcFactorParam.trxLineSubNum, TRX_LINE_KEY_NUM);
//        ZYPEZDItemValueSetter.setValue(prcFactorParam.configCatgCd, CONFIG_CATG.OUTBOUND);
//        ZYPEZDItemValueSetter.setValue(prcFactorParam.prcDtlGrpCd, PRC_DTL_GRP.BASE_PRICE);
//        ZYPEZDItemValueSetter.setValue(prcFactorParam.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(prcFactorParam.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(prcFactorParam.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(prcFactorParam.pkgUomCd, invoiceBean.getBasePkgUomCd());
//        ZYPEZDItemValueSetter.setValue(prcFactorParam.prcCondUnitCd, PRC_COND_UNIT.AMT);
//        ZYPEZDItemValueSetter.setValue(prcFactorParam.autoPrcCcyCd, invoiceBean.getDealCcyCd());
//        ZYPEZDItemValueSetter.setValue(prcFactorParam.autoPrcAmtRate, invoiceBean.getDealNetUnitPrcAmt());
//        ZYPEZDItemValueSetter.setValue(prcFactorParam.calcPrcAmtRate, invoiceBean.getDealNetUnitPrcAmt());
//        ZYPEZDItemValueSetter.setValue(prcFactorParam.unitPrcAmt, invoiceBean.getDealNetUnitPrcAmt());
//        prcLineParam.NWZC157003PMsg.setValidCount(1);
//    }
//QC#16531 del End
    /**
     * <pre>
     * Call Invoice Import API
     * </pre>
     * @param invoiceBean InvoiceDataBean
     */
    private boolean callInvoiceImportApi(InvoiceDataBean invoiceBean) {

        List<NWZC040001PMsg> invHeaderPmsgList = new ArrayList<NWZC040001PMsg>();
        List<NWZC040002PMsg> invShipmentPmsgList = new ArrayList<NWZC040002PMsg>();
        List<NWZC040003PMsg> invLinePmsgList = new ArrayList<NWZC040003PMsg>();
        List<NWZC040005PMsg> invlineTaxPmsgList = new ArrayList<NWZC040005PMsg>();
        List<NWZC040007PMsg> invSlsCrPmsgList = new ArrayList<NWZC040007PMsg>();

        setInvHeaderParam(invHeaderPmsgList, invoiceBean);
        setInvShipmentParam(invShipmentPmsgList, invoiceBean);
        setInvLineParam(invLinePmsgList, invoiceBean);
        setInvSalesCreditParam(invSlsCrPmsgList, invoiceBean);

        for (InvoiceLineDtlBean dtlBean : invoiceBean.getInvLineDtlBeanList()) {
            setInvLineTaxParam(invlineTaxPmsgList, invoiceBean, dtlBean);
        }

        NWZC040001 api = new NWZC040001();
        api.execute(invHeaderPmsgList, invShipmentPmsgList, invLinePmsgList, null, invlineTaxPmsgList, null, invSlsCrPmsgList, ONBATCH_TYPE.BATCH);

        List<String> errMsgList = new ArrayList<String>();

        for (NWZC040001PMsg invHeader : invHeaderPmsgList) {
            if (invHeader.xxMsgIdList.getValidCount() > 0) {
                for (int n = 0; n < invHeader.xxMsgIdList.getValidCount(); n++) {
                    String errId = invHeader.xxMsgIdList.no(n).xxMsgId.getValue();
                    errMsgList.add(errId);
                    S21InfoLogOutput.println(errId);
                }
            }
        }

        for (NWZC040002PMsg shipment : invShipmentPmsgList) {
            if (shipment.xxMsgIdList.getValidCount() > 0) {
                for (int n = 0; n < shipment.xxMsgIdList.getValidCount(); n++) {
                    String errId = shipment.xxMsgIdList.no(n).xxMsgId.getValue();
                    errMsgList.add(errId);
                    S21InfoLogOutput.println(errId);
                }
            }
        }

        for (NWZC040003PMsg line : invLinePmsgList) {
            if (line.xxMsgIdList.getValidCount() > 0) {
                for (int n = 0; n < line.xxMsgIdList.getValidCount(); n++) {
                    String errId = line.xxMsgIdList.no(n).xxMsgId.getValue();
                    errMsgList.add(errId);
                    S21InfoLogOutput.println(errId);
                }
            }
        }

        for (NWZC040005PMsg tax : invlineTaxPmsgList) {
            if (tax.xxMsgIdList.getValidCount() > 0) {
                for (int n = 0; n < tax.xxMsgIdList.getValidCount(); n++) {
                    String errId = tax.xxMsgIdList.no(n).xxMsgId.getValue();
                    errMsgList.add(errId);
                    S21InfoLogOutput.println(errId);
                }
            }
        }

        for (NWZC040007PMsg slsCr : invSlsCrPmsgList) {
            if (slsCr.xxMsgIdList.getValidCount() > 0) {
                for (int n = 0; n < slsCr.xxMsgIdList.getValidCount(); n++) {
                    String errId = slsCr.xxMsgIdList.no(n).xxMsgId.getValue();
                    errMsgList.add(errId);
                    S21InfoLogOutput.println(errId);
                }
            }
        }

        if (!errMsgList.isEmpty()) {
            setError(invoiceBean, MSG_ID.NWCM0114E.toString(), MAIL_ERR_MSG_INVOICE_IMPORT);
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Set Invoice Header Parameter
     * </pre>
     * @param invHeaderPmsgList List<NWZC040001PMsg>
     * @param invoiceBean InvoiceDataBean
     */
    private void setInvHeaderParam(List<NWZC040001PMsg> invHeaderPmsgList, InvoiceDataBean invoiceBean) {

        NWZC040001PMsg param = new NWZC040001PMsg();

        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.invNum, invoiceBean.getInvNum());
        ZYPEZDItemValueSetter.setValue(param.invDt, this.slsDate);
        ZYPEZDItemValueSetter.setValue(param.acctDt, this.slsDate);
        ZYPEZDItemValueSetter.setValue(param.invTpCd, INV_TP.INVOICE);  // 2017/06/29 Mod QC#19623
        ZYPEZDItemValueSetter.setValue(param.netDueDt, invoiceBean.getNetDueDt());
        ZYPEZDItemValueSetter.setValue(param.custIssPoNum, invoiceBean.getCustIssPoNum());
        ZYPEZDItemValueSetter.setValue(param.billToCustCd, invoiceBean.getBillToCustCd());
        ZYPEZDItemValueSetter.setValue(param.sellToCustCd, invoiceBean.getBillToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(param.payerCustCd, invoiceBean.getPayerCustCd());
        ZYPEZDItemValueSetter.setValue(param.pmtTermCd, invoiceBean.getPmtTermCd());
        ZYPEZDItemValueSetter.setValue(param.invPrintStsCd, INV_PRINT_STS_CD_UNTREATED);
        ZYPEZDItemValueSetter.setValue(param.trxSrcTpCd, TRX_SRC_TP.WHOLE_SALES);
        ZYPEZDItemValueSetter.setValue(param.crDrRsnCd, CR_DR_RSN.EASY_PACK1);
        ZYPEZDItemValueSetter.setValue(param.crDrRsnSubCd, CR_DR_SUB_RSN.SHORT_FALL_DEBIT);
        ZYPEZDItemValueSetter.setValue(param.sysSrcCd, SYS_SRC_CD_NW);
        ZYPEZDItemValueSetter.setValue(param.pmtTermCashDiscCd, invoiceBean.getPmtTermCashDiscCd());
        ZYPEZDItemValueSetter.setValue(param.histCratCpltFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(param.dsOrdTpCd, invoiceBean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(param.dsInvTpCd, invoiceBean.getDsInvTpCd());
        ZYPEZDItemValueSetter.setValue(param.srcSysDocNum, invoiceBean.getInvNum());
        ZYPEZDItemValueSetter.setValue(param.slsRepTocCd, invoiceBean.getSlsRepTocCd());
        ZYPEZDItemValueSetter.setValue(param.itrlInvErrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(param.billToCustAcctCd, invoiceBean.getBillToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(param.soldToCustLocCd, invoiceBean.getBillToCustCd());
        ZYPEZDItemValueSetter.setValue(param.lineBizTpCd, invoiceBean.getLineBizTpCd());
        ZYPEZDItemValueSetter.setValue(param.dsBizAreaCd, invoiceBean.getDsBizAreaCd());
        ZYPEZDItemValueSetter.setValue(param.dsOrdCatgCd, invoiceBean.getDsOrdCatgCd());
        invHeaderPmsgList.add(param);
    }

    /**
     * <pre>
     * Set Invoice Shipment Parameter
     * </pre>
     * @param invShipmentPmsgList List<NWZC040002PMsg>
     * @param invoiceBean InvoiceDataBean
     */
    private void setInvShipmentParam(List<NWZC040002PMsg> invShipmentPmsgList, InvoiceDataBean invoiceBean) {

        NWZC040002PMsg param = new NWZC040002PMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.invNum, invoiceBean.getInvNum());
        ZYPEZDItemValueSetter.setValue(param.invBolLineNum, INVOICE_KEY_NUM_001);
        // 2017/07/21 QC#20029 DEL Start
        //ZYPEZDItemValueSetter.setValue(param.shipFromInvtyLocCd, invoiceBean.getShipFromInvtyLocCd());
        // 2017/07/21 QC#20029 DEL End
        ZYPEZDItemValueSetter.setValue(param.shipToCustCd, invoiceBean.getShipToCustCd());
        // 2017/07/21 QC#20029 DEL Start
        //ZYPEZDItemValueSetter.setValue(param.dropShipFlg, invoiceBean.getDropShipFlg());
        // 2017/07/21 QC#20029 DEL End
        ZYPEZDItemValueSetter.setValue(param.shipToLocNm, invoiceBean.getShipToLocNm());
        ZYPEZDItemValueSetter.setValue(param.shipToAddlLocNm, invoiceBean.getShipToAddlLocNm());
        ZYPEZDItemValueSetter.setValue(param.shipToFirstLineAddr, invoiceBean.getShipToFirstLineAddr());
        ZYPEZDItemValueSetter.setValue(param.shipToScdLineAddr, invoiceBean.getShipToScdLineAddr());
        ZYPEZDItemValueSetter.setValue(param.shipToThirdLineAddr, invoiceBean.getShipToThirdLineAddr());
        ZYPEZDItemValueSetter.setValue(param.shipToFrthLineAddr, invoiceBean.getShipToFrthLineAddr());
        ZYPEZDItemValueSetter.setValue(param.shipToCtyAddr, invoiceBean.getShipToCtyAddr());
        ZYPEZDItemValueSetter.setValue(param.shipToCntyNm, invoiceBean.getShipToCntyNm());
        ZYPEZDItemValueSetter.setValue(param.shipToProvNm, invoiceBean.getShipToProvNm());
        ZYPEZDItemValueSetter.setValue(param.shipToStCd, invoiceBean.getShipToStCd());
        ZYPEZDItemValueSetter.setValue(param.shipToPostCd, invoiceBean.getShipToPostCd());
        ZYPEZDItemValueSetter.setValue(param.shipToCtryCd, invoiceBean.getShipToCtryCd());
        ZYPEZDItemValueSetter.setValue(param.shipToFirstRefCmntTxt, invoiceBean.getShipToFirstRefCmntTxt());
        ZYPEZDItemValueSetter.setValue(param.shipToScdRefCmntTxt, invoiceBean.getShipToScdRefCmntTxt());
        ZYPEZDItemValueSetter.setValue(param.frtDealTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(param.frtFuncTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(param.frtTaxPct, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(param.custIssPoNum, invoiceBean.getCustIssPoNum());
        ZYPEZDItemValueSetter.setValue(param.shipToCustAcctCd, invoiceBean.getShipToCustAcctCd());
        invShipmentPmsgList.add(param);
    }

    /**
     * <pre>
     * Set Invoice Line Parameter
     * </pre>
     * @param invShipmentPmsgList List<NWZC040003PMsg>
     * @param invoiceBean InvoiceDataBean
     */
    private void setInvLineParam(List<NWZC040003PMsg> invLinePmsgList, InvoiceDataBean invoiceBean) {

        NWZC040003PMsg param = new NWZC040003PMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.invNum, invoiceBean.getInvNum());
        ZYPEZDItemValueSetter.setValue(param.invBolLineNum, INVOICE_KEY_NUM_001);
        ZYPEZDItemValueSetter.setValue(param.invLineNum, INVOICE_KEY_LINE_NUM_001);
        ZYPEZDItemValueSetter.setValue(param.invLineSubNum, INVOICE_KEY_LINE_SUB_NUM_001);
        ZYPEZDItemValueSetter.setValue(param.slsRepTocCd, invoiceBean.getSlsRepTocCd());
        String ezContract = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_INV_PRINT_SHORT_FALL_ITEM, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.mdseCd, ezContract);
        ZYPEZDItemValueSetter.setValue(param.trxCd, invoiceBean.getTrxCd());
        ZYPEZDItemValueSetter.setValue(param.trxRsnCd, invoiceBean.getTrxRsnCd());
        ZYPEZDItemValueSetter.setValue(param.ordQty, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(param.shipQty, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(param.boQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(param.dealNetUnitPrcAmt, invoiceBean.getDealNetUnitPrcAmt());
        ZYPEZDItemValueSetter.setValue(param.invLineDealTaxAmt, invoiceBean.getInvLineDealTaxAmt());
        ZYPEZDItemValueSetter.setValue(param.invLineDealNetAmt, invoiceBean.getInvLineDealNetAmt());
        ZYPEZDItemValueSetter.setValue(param.dealDiscUnitPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(param.taxFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(param.taxPct, invoiceBean.getTaxPct());
        //ZYPEZDItemValueSetter.setValue(param.shipCmplCostAmt, BigDecimal.ZERO); // 2017/10/23 QC#20719 Del
        ZYPEZDItemValueSetter.setValue(param.dealGrsUnitPrcAmt, invoiceBean.getDealGrsUnitPrcAmt());
        ZYPEZDItemValueSetter.setValue(param.dealGrsTotPrcAmt, invoiceBean.getDealGrsTotPrcAmt());
        ZYPEZDItemValueSetter.setValue(param.uomCd, invoiceBean.getBasePkgUomCd());
        ZYPEZDItemValueSetter.setValue(param.splyPgmShipQty, invoiceBean.getSplyPgmShipQty());
        ZYPEZDItemValueSetter.setValue(param.splyPgmUnitAmtRate, invoiceBean.getSplyPgmUnitAmtRate());
        ZYPEZDItemValueSetter.setValue(param.taxCalcGeoCd, invoiceBean.getTaxCalcGeoCd());  //QC#16531 add
        invLinePmsgList.add(param);
    }

    /**
     * <pre>
     * Set Invoice Line Tax Parameter
     * </pre>
     * @param invlineTaxPmsgList List<NWZC040005PMsg>
     * @param invoiceBean InvoiceDataBean
     * @param dtlBean InvoiceLineDtlBean
     */
    private void setInvLineTaxParam(List<NWZC040005PMsg> invlineTaxPmsgList, InvoiceDataBean invoiceBean, InvoiceLineDtlBean dtlBean) {

        NWZC040005PMsg param = new NWZC040005PMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.invNum, invoiceBean.getInvNum());
        ZYPEZDItemValueSetter.setValue(param.invBolLineNum, INVOICE_KEY_NUM_001);
        ZYPEZDItemValueSetter.setValue(param.invLineNum, INVOICE_KEY_LINE_NUM_001);
        ZYPEZDItemValueSetter.setValue(param.invLineSubNum, INVOICE_KEY_LINE_SUB_NUM_001);
        ZYPEZDItemValueSetter.setValue(param.dsSlsTaxTpCd, dtlBean.getDsSlsTaxTpCd());
        ZYPEZDItemValueSetter.setValue(param.funcSlsTaxAmt, dtlBean.getFuncSlsTaxAmt());
        ZYPEZDItemValueSetter.setValue(param.dealSlsTaxAmt, dtlBean.getDealSlsTaxAmt());
        ZYPEZDItemValueSetter.setValue(param.slsTaxPct, dtlBean.getSlsTaxPct());
        ZYPEZDItemValueSetter.setValue(param.taxAreaId, dtlBean.getTaxAreaId());    //QC#16531 add
        ZYPEZDItemValueSetter.setValue(param.taxRsltDescTxt, dtlBean.getTaxRsltDescTxt());    //QC#16531 add
        invlineTaxPmsgList.add(param);
    }

    /**
     * <pre>
     * Set Invoice Line Tax Parameter
     * </pre>
     * @param invlineTaxPmsgList List<NWZC040005PMsg>
     * @param invoiceBean InvoiceDataBean
     * @param dtlBean InvoiceLineDtlBean
     */
    private void setInvSalesCreditParam(List<NWZC040007PMsg> invSlsCrPmsgList, InvoiceDataBean invoiceBean) {

        NWZC040007PMsg param = new NWZC040007PMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.invNum, invoiceBean.getInvNum());
        ZYPEZDItemValueSetter.setValue(param.invBolLineNum, INVOICE_KEY_NUM_001);
        ZYPEZDItemValueSetter.setValue(param.invLineNum, INVOICE_KEY_LINE_NUM_001);
        ZYPEZDItemValueSetter.setValue(param.invLineSubNum, INVOICE_KEY_LINE_SUB_NUM_001);
        String ezContract = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_INV_PRINT_SHORT_FALL_ITEM, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.mdseCd, ezContract);
        ZYPEZDItemValueSetter.setValue(param.invLineSplPct, BIGDECIMAL_PERCENT_100);
        ZYPEZDItemValueSetter.setValue(param.slsRepTocCd, invoiceBean.getSlsRepTocCd());
        ZYPEZDItemValueSetter.setValue(param.slsRepCrPct, BIGDECIMAL_PERCENT_100);
        ZYPEZDItemValueSetter.setValue(param.dealSlsCrAmt, invoiceBean.getDealNetUnitPrcAmt());
        ZYPEZDItemValueSetter.setValue(param.funcSlsCrAmt, invoiceBean.getDealNetUnitPrcAmt());
        ZYPEZDItemValueSetter.setValue(param.dealCcyCd, invoiceBean.getDealCcyCd());
        ZYPEZDItemValueSetter.setValue(param.trxCd, TRX.SALES);
        ZYPEZDItemValueSetter.setValue(param.trxRsnCd, EASYPAC1_SHORTFALL_DEBIT_BILL_ONRY);

        if (!ZYPCommonFunc.hasValue(this.dfrdAcctgRuleCd)) {
            MDSETMsg dsMdseInfoTMsg = new MDSETMsg();
            ZYPEZDItemValueSetter.setValue(dsMdseInfoTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsMdseInfoTMsg.mdseCd, ezContract);
            dsMdseInfoTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(dsMdseInfoTMsg);
            this.dfrdAcctgRuleCd = dsMdseInfoTMsg.dfrdAcctgRuleCd.getValue();
        }

        ZYPEZDItemValueSetter.setValue(param.dfrdAcctgRuleCd, this.dfrdAcctgRuleCd);

        invSlsCrPmsgList.add(param);
    }

    /**
     * <pre>
     * To Array
     * </pre>
     * @param appendMsgList String[]
     * @return String[]
     */
    private String[] toArray(String... appendMsgList) {
        String[] msgArray = new String[appendMsgList.length];
        for (int i = 0; i < msgArray.length; i++) {
            msgArray[i] = appendMsgList[i];
        }
        return msgArray;
    }

    /**
     * <pre>
     * To Array
     * </pre>
     * @param appendMsgList String[]
     * @return String[]
     */
    private void setError(InvoiceDataBean invoiceBean, String errCd, String errMsg) {

        InvoiceDataBean errBean = new InvoiceDataBean();
        errBean.setBillToCustAcctCd(invoiceBean.getBillToCustAcctCd());

        StringBuilder mailMsg = new StringBuilder();
        mailMsg.append(errMsg).append(SPACE);
        mailMsg.append(ERR_MSG_BILL_TO_CUST_ACCT_CD).append(invoiceBean.getBillToCustAcctCd());
        errBean.setMailErrMsg(mailMsg.toString());

        errBeanList.add(errBean);
    }

    /**
     * <pre>
     * Get FuncCcyCd
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @return boolean
     */
    private boolean getFuncCcyCd() {

        // if cache doesn't have GLBL_CMPY infomation, find
        // [GLBL_CMPY] table it.
        GLBL_CMPYTMsg glblTMsg = new GLBL_CMPYTMsg();
        glblTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        glblTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblTMsg);
        if (glblTMsg == null) {
            return false;
        }

        this.funcCcyCd = glblTMsg.stdCcyCd.getValue();
        return true;
    }

    /**
     * <pre>
     * Get FuncCcyScale
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @return boolean
     */
    private boolean getFuncCcyScale() {
        CCYTMsg ccyTMsg = new CCYTMsg();
        ccyTMsg.glblCmpyCd.setValue(glblCmpyCd);
        ccyTMsg.ccyCd.setValue(funcCcyCd);

        ccyTMsg = (CCYTMsg) S21CodeTableAccessor.findByKey(ccyTMsg);
        if (ccyTMsg == null || ccyTMsg.aftDeclPntDigitNum.getValue() == null) {
            return false;
        }

        this.funcCcyScale = ccyTMsg.aftDeclPntDigitNum.getValue().intValue();
        return true;
    }

    //QC#16531 add Start
    /**
     * <pre>
     * Call Tax Calculation API
     * </pre>
     * @param invoiceBean InvoiceDataBean
     * @param shipMap     Map<String, Object>
     */
    private boolean callTaxCalcApi(InvoiceDataBean invoiceBean, Map<String, Object> shipMap) {
        NWZC036101PMsg taxcalcPMsg = new NWZC036101PMsg();
        setTaxCalcApiParam(taxcalcPMsg, invoiceBean, shipMap);

        new NWZC036101().execute(taxcalcPMsg, ONBATCH_TYPE.BATCH);

        return getTaxCalculationAPIResult(invoiceBean, taxcalcPMsg);
    }

    /**
     * <pre>
     * Set Tax Calculation API Parameter
     * </pre>
     * @param invoiceBean InvoiceDataBean
     * @param shipMap     Map<String, Object>
     */
    private void setTaxCalcApiParam(NWZC036101PMsg taxcalcPMsg, InvoiceDataBean invoiceBean, Map<String, Object> shipMap) {
    // 2017/07/04 QC#19636 Mod End
        setValue(taxcalcPMsg.glblCmpyCd,    this.glblCmpyCd);
        setValue(taxcalcPMsg.slsDt,         this.slsDate);
        setValue(taxcalcPMsg.xxModeCd,      NWZC036101Constant.PROC_MODE_INVOICE);
        //Sell To Account Number
        setValue(taxcalcPMsg.dsAcctNum_SE,  invoiceBean.getSellToCustCd());
        //Bill To Account Number
        setValue(taxcalcPMsg.billToAcctNum, invoiceBean.getBillToCustAcctCd());
        //Bill To Location Number
        setValue(taxcalcPMsg.billToLocNum,  invoiceBean.getBillToCustCd());

        //Bill to  Vertex Group Exemption
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd",    glblCmpyCd);
        mapParam.put("billToCustCd",  invoiceBean.getBillToCustCd());
        mapParam.put("billToAcctCd",  invoiceBean.getBillToCustAcctCd());
        String billToTaxExemGrpCd = (String) ssmBatchClient.queryObject("getBillToTaxGrpExemCd", mapParam);
        setValue(taxcalcPMsg.dsTaxGrpExemCd_B, billToTaxExemGrpCd);

        //Bill to  Vertex Group Exemption Resale Flg
        String billToTaxGrpExemReslFlg = null;
        if (ZYPCommonFunc.hasValue(billToTaxExemGrpCd)) {
            DS_TAX_GRP_EXEMTMsg dsTaxGrpExemTmsg = new DS_TAX_GRP_EXEMTMsg();
            setValue(dsTaxGrpExemTmsg.glblCmpyCd,     glblCmpyCd);
            setValue(dsTaxGrpExemTmsg.dsTaxGrpExemCd, billToTaxExemGrpCd);
            dsTaxGrpExemTmsg = (DS_TAX_GRP_EXEMTMsg) S21CacheTBLAccessor.findByKey(dsTaxGrpExemTmsg);
            if (dsTaxGrpExemTmsg != null) {
                billToTaxGrpExemReslFlg = dsTaxGrpExemTmsg.dsTaxGrpExemReslFlg.getValue();
            }
        }
        setValue(taxcalcPMsg.dsTaxGrpExemReslFlg_B, billToTaxGrpExemReslFlg);

        //Ship To Account Number
        // 2017/07/04 QC#19636 Mod Start
//        String shipToAcctCd = (String) shipMap.get("SELL_TO_CUST_CD");
        String shipToAcctCd = invoiceBean.getSellToCustCd();
        // 2017/07/04 QC#19636 Mod End
        setValue(taxcalcPMsg.dsAcctNum_ST,  shipToAcctCd);

        //Ship To Location Number
        // 2017/07/04 QC#19636 Mod Start
//        String shipToCustCd = (String) shipMap.get("SHIP_TO_CUST_CD");
        String shipToCustCd = invoiceBean.getShipToCustCd();
        // 2017/07/04 QC#19636 Mod End
        setValue(taxcalcPMsg.shipToLocNum,  shipToCustCd);

        //Ship to Vertex Group Exemption
        mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd",   glblCmpyCd);
        mapParam.put("shipToCustCd", shipToCustCd);
        mapParam.put("shipToAcctCd", shipToAcctCd);
        String shipToTaxExemGrpCd = (String) ssmBatchClient.queryObject("getShipToTaxGrpExemCd", mapParam);
        setValue(taxcalcPMsg.dsTaxGrpExemCd_ST, shipToTaxExemGrpCd);

        DS_INV_TPTMsg dsInvTpTMsg = new DS_INV_TPTMsg();
        setValue(dsInvTpTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(dsInvTpTMsg.dsInvTpCd, invoiceBean.getDsInvTpCd());
        dsInvTpTMsg = (DS_INV_TPTMsg) S21CacheTBLAccessor.findByKey(dsInvTpTMsg);
        if (dsInvTpTMsg != null) {
            //Tax Calculation Flag
            setValue(taxcalcPMsg.taxCalcFlg, dsInvTpTMsg.taxCalcFlg.getValue());
            //Tax Exemption
            setValue(taxcalcPMsg.taxExemFlg, dsInvTpTMsg.taxExemFlg.getValue());
            //Tax Exemption Reason Code
            setValue(taxcalcPMsg.taxExemRsnCd, dsInvTpTMsg.taxExemRsnCd.getValue());
        }
        //Transaction Date
        setValue(taxcalcPMsg.trxDt, this.slsDate);
        //Tax Calculate Header Num
        setValue(taxcalcPMsg.xxTaxCalcHdrNum, invoiceBean.getInvNum());

        // 2017/07/04 QC#19636 Mod Start
//        String ctyAddr = (String) shipMap.get("CTY_ADDR");
//        String stCd = (String) shipMap.get("ST_CD");
        String ctyAddr = invoiceBean.getShipToCtyAddr();
        String stCd = invoiceBean.getShipToStCd();
//        setValue(taxcalcPMsg.taxAreaId_ST, getTaxAreaId(glblCmpyCd, ctyAddr, (BigDecimal) shipMap.get("CNTY_PK"), stCd));
//        //Ship To Tax Area ID
//        setValue(taxcalcPMsg.geoCd_ST, (String) shipMap.get("GEO_CD"));
//        //Ship To Inside City Limit Flag
//        setValue(taxcalcPMsg.dsInsdCtyLimitFlg_ST, (String) shipMap.get("DS_INSD_CTY_LIMIT_FLG"));

        Map<String, Object> cntyInfo = getCntyInfo(invoiceBean);
        if (cntyInfo != null && !shipMap.isEmpty()) {
            setValue(taxcalcPMsg.taxAreaId_ST, getTaxAreaId(glblCmpyCd, ctyAddr, (BigDecimal) cntyInfo.get("CNTY_PK"), stCd));
        }
        //Ship To Tax Area ID
        setValue(taxcalcPMsg.geoCd_ST, (String) shipMap.get("GEO_CD"));
        //Ship To Inside City Limit Flag
        String flg = (String) shipMap.get("DS_INSD_CTY_LIMIT_FLG");
        if (!ZYPCommonFunc.hasValue(flg)) {
            flg = ZYPConstant.FLG_OFF_N;
        }
        setValue(taxcalcPMsg.dsInsdCtyLimitFlg_ST, flg);

        //Ship to First Line Address
//        setValue(taxcalcPMsg.firstLineAddr_ST, (String) shipMap.get("FIRST_LINE_ADDR"));
        //Ship to Second Line Address
//        setValue(taxcalcPMsg.scdLineAddr_ST, (String) shipMap.get("SCD_LINE_ADDR"));
        //Ship to First Line Address
        setValue(taxcalcPMsg.firstLineAddr_ST, invoiceBean.getShipToFirstLineAddr());
        //Ship to Second Line Address
        setValue(taxcalcPMsg.scdLineAddr_ST, invoiceBean.getShipToScdLineAddr());
        // 2017/07/04 QC#19636 Mod End

        //Ship to City Address
        setValue(taxcalcPMsg.ctyAddr_ST, ctyAddr);
        //Ship to State Code
        setValue(taxcalcPMsg.stCd_ST, stCd);
        // 2017/07/04 QC#19636 Mod Start
//        //Ship To County Name
//        setValue(taxcalcPMsg.cntyNm_ST, (String) shipMap.get("CNTY_NM"));
//        //Ship To Post Code
//        setValue(taxcalcPMsg.postCd_ST, (String) shipMap.get("POST_CD"));
//        //Ship To Country Code
//        setValue(taxcalcPMsg.ctryCd_ST, (String) shipMap.get("CTRY_CD"));

        //Ship To County Name
        setValue(taxcalcPMsg.cntyNm_ST, invoiceBean.getShipToCntyNm());
        //Ship To Post Code
        setValue(taxcalcPMsg.postCd_ST, invoiceBean.getShipToPostCd());
        //Ship To Country Code
        setValue(taxcalcPMsg.ctryCd_ST, invoiceBean.getShipToCtryCd());
        // 2017/07/04 QC#19636 Mod End

        // get salesrep address from S21_PSN
        mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("tocCd",      invoiceBean.getSlsRepTocCd());
        mapParam.put("invDt",      this.slsDate);
        Map<String, Object> mapRes = (Map<String, Object>) ssmBatchClient.queryObject("getSalesRepAdress", mapParam);

        //Sales Rep Tax Area ID
        setValue(taxcalcPMsg.geoCd_SR, (String) mapRes.get("GEO_CD"));
        //Sales Rep Inside City Limit Flag
        setValue(taxcalcPMsg.dsInsdCtyLimitFlg_SR, (String) mapRes.get("DS_INSD_CTY_LIMIT_FLG"));
        //Sales Rep First Line Address
        setValue(taxcalcPMsg.firstLineAddr_SR, (String) mapRes.get("FIRST_LINE_ADDR"));
        //Sales Rep Second Line Address
        setValue(taxcalcPMsg.scdLineAddr_SR, (String) mapRes.get("SCD_LINE_ADDR"));
        //Sales Rep City Address
        setValue(taxcalcPMsg.ctyAddr_SR, (String) mapRes.get("CTY_ADDR"));
        //Sales Rep County Name
        setValue(taxcalcPMsg.cntyNm_SR, (String) mapRes.get("CNTY_NM"));
        //Sales Rep State Code
        setValue(taxcalcPMsg.stCd_SR, (String) mapRes.get("ST_CD"));
        //Sales Rep Post Code
        setValue(taxcalcPMsg.postCd_SR, (String) mapRes.get("POST_CD"));
        //Sales Rep Country Code
        setValue(taxcalcPMsg.ctryCd_SR, (String) mapRes.get("CTRY_CD"));

        NWZC036101_taxCalculateInputLinePMsg taxCalcInputLinePMsg = (NWZC036101_taxCalculateInputLinePMsg) taxcalcPMsg.taxCalculateInputLine.no(0);
        taxcalcPMsg.taxCalculateInputLine.setValidCount(1);

        //Tax Calculate Line Number
        setValue(taxCalcInputLinePMsg.xxTaxCalcLineNum_A, BigDecimal.ZERO.toString());
        //Merchandise Code
        setValue(taxCalcInputLinePMsg.mdseCd_A, sfDummyMdseCd);
        //get mdse info
        getMdseInfoForTaxParam(sfDummyMdseCd, taxCalcInputLinePMsg);

        //Shipped Quantity
        setValue(taxCalcInputLinePMsg.shipQty_A, BigDecimal.ONE);
        //Function Net Unit Price Amount
        setValue(taxCalcInputLinePMsg.funcNetUnitPrcAmt_A, invoiceBean.getDealNetUnitPrcAmt());
        //Sales Amount
        setValue(taxCalcInputLinePMsg.slsAmt_A, invoiceBean.getDealNetUnitPrcAmt());

        // get ship from
        String invtyLocCd = invoiceBean.getShipFromInvtyLocCd();
        if (hasValue(invtyLocCd)) {
            mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd",   glblCmpyCd);
            mapParam.put("invtyLocCd", invtyLocCd);
            mapRes = (Map<String, Object>) ssmBatchClient.queryObject("queryDS_INVTY_LOC_V", mapParam);

            if (mapRes != null) {
                //Ship from Tax Area ID
                setValue(taxCalcInputLinePMsg.geoCd_AF, (String) mapRes.get("GEO_CD"));
                //Ship from First Line Address
                setValue(taxCalcInputLinePMsg.firstLineAddr_AF, (String) mapRes.get("FIRST_LINE_ADDR"));
                //Ship from Second Line Address
                setValue(taxCalcInputLinePMsg.scdLineAddr_AF, (String) mapRes.get("SCD_LINE_ADDR"));
                //Ship from City Address
                setValue(taxCalcInputLinePMsg.ctyAddr_AF, (String) mapRes.get("CTY_ADDR"));
                //Ship from County Name
                setValue(taxCalcInputLinePMsg.cntyNm_AF, (String) mapRes.get("CNTY_NM"));
                //Ship from State Code
                setValue(taxCalcInputLinePMsg.stCd_AF, (String) mapRes.get("ST_CD"));
                //Ship from Post Code
                setValue(taxCalcInputLinePMsg.postCd_AF, (String) mapRes.get("POST_CD"));
                //Ship from Country Code
                setValue(taxCalcInputLinePMsg.ctryCd_AF, (String) mapRes.get("CTRY_CD"));
            }
        }
    }


    /**
     * getTaxCalculationAPIResult
     * @param invImptApiBean
     * @param taxcalcPMsgList
     * @return List<NWZC040005PMsg>
     */
    private boolean getTaxCalculationAPIResult(InvoiceDataBean invoiceBean, NWZC036101PMsg taxcalcPMsg) {

        boolean isSuccess = true;

        NWZC036101_taxCalculateOutputLinePMsg taxcalcOutputLinePMsg;
        List<BigDecimal> taxAmtList;
        List<BigDecimal> taxPctList;
        List<String> taxResultList;
        List<String> slsTaxTpList = new ArrayList<String>();
        slsTaxTpList.add(DS_SLS_TAX_TP.STATE_TAX);
        slsTaxTpList.add(DS_SLS_TAX_TP.COUNTY_TAX);
        slsTaxTpList.add(DS_SLS_TAX_TP.CITY_TAX);

        if (taxcalcPMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < taxcalcPMsg.xxMsgIdList.getValidCount(); i++) {
                String errId = taxcalcPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                S21InfoLogOutput.println(errId);
            }
            isSuccess = false;
        }
        if (!isSuccess) {
            setError(invoiceBean, MSG_ID.NWCM0113E.toString(), MAIL_ERR_MSG_TAX_CALCULATION_PROCESS);
            return false;
        }

        taxcalcOutputLinePMsg = (NWZC036101_taxCalculateOutputLinePMsg) taxcalcPMsg.taxCalculateOutputLine.no(0);
        BigDecimal taxAmt  = taxcalcOutputLinePMsg.invLineFuncTaxAmt.getValue();
        if (taxAmt == null) {
            taxAmt = BigDecimal.ZERO;
        }
        BigDecimal taxPct  = taxcalcOutputLinePMsg.xxTaxCalcLineTaxPct.getValue();
        if (taxPct == null) {
            taxPct = BigDecimal.ZERO;
        }

        taxAmtList = new ArrayList<BigDecimal>();
        taxAmtList.add(taxcalcOutputLinePMsg.taxAmt_01.getValue());
        taxAmtList.add(taxcalcOutputLinePMsg.taxAmt_02.getValue());
        taxAmtList.add(taxcalcOutputLinePMsg.taxAmt_03.getValue());
        taxPctList = new ArrayList<BigDecimal>();
        taxPctList.add(taxcalcOutputLinePMsg.taxPct_01.getValue());
        taxPctList.add(taxcalcOutputLinePMsg.taxPct_02.getValue());
        taxPctList.add(taxcalcOutputLinePMsg.taxPct_03.getValue());
        taxResultList = new ArrayList<String>();
        taxResultList.add(taxcalcOutputLinePMsg.xxVtxRsltCd_01.getValue());
        taxResultList.add(taxcalcOutputLinePMsg.xxVtxRsltCd_02.getValue());
        taxResultList.add(taxcalcOutputLinePMsg.xxVtxRsltCd_03.getValue());

        invoiceBean.setInvLineDealTaxAmt(taxAmt);
        invoiceBean.setTaxPct(taxPct);
        invoiceBean.setTaxCalcGeoCd(taxcalcOutputLinePMsg.taxAreaId.getValue());

        List<InvoiceLineDtlBean> dtlBeanList = invoiceBean.getInvLineDtlBeanList();
        for (int i = 0; i < taxResultList.size(); i++) {
            InvoiceLineDtlBean dtlBean = new InvoiceLineDtlBean();
            taxAmt = taxAmtList.get(i);
            taxPct = taxPctList.get(i);
            if (taxAmt != null) {
                dtlBean.setDsSlsTaxTpCd(slsTaxTpList.get(i));
                dtlBean.setDealSlsTaxAmt(taxAmt);
                dtlBean.setFuncSlsTaxAmt(taxAmt);
                dtlBean.setSlsTaxPct(taxPct);
                dtlBean.setTaxAreaId(taxcalcOutputLinePMsg.taxAreaId.getValue());
                dtlBean.setTaxRsltDescTxt(taxResultList.get(i));
                dtlBeanList.add(dtlBean);
            }
        }

        return true;
    }
    private String getTaxAreaId(String glblCmpyCd, String shipToCtyAddr, BigDecimal shipToCntyPk, String shipToStCd) {
        String taxAreaId = null;

        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", glblCmpyCd);
        inParam.put("ctyAddr", shipToCtyAddr);
        inParam.put("cntyPk", shipToCntyPk);
        inParam.put("stCd", shipToStCd);

        Map<String, Object> resultMap = (Map<String, Object>)ssmBatchClient.queryObject("getTaxAreaId", inParam);
        if (resultMap == null) {
            inParam.put("ctyAddr", null);
            resultMap = (Map<String, Object>)ssmBatchClient.queryObject("getTaxAreaId", inParam);
        }
        if (resultMap == null) {
            inParam.put("cntyPk", null);
            resultMap = (Map<String, Object>)ssmBatchClient.queryObject("getTaxAreaId", inParam);
        }
        if (resultMap != null) {
            taxAreaId = (String)resultMap.get("TAX_AREA_ID");
        }

        return taxAreaId;
    }

    private void getMdseInfoForTaxParam(String mdseCd, NWZC036101_taxCalculateInputLinePMsg taxCalcInputLinePMsg) {

        MDSETMsg dsMdseInfoTMsg = new MDSETMsg();
        setValue(dsMdseInfoTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(dsMdseInfoTMsg.mdseCd,     this.sfDummyMdseCd);
        dsMdseInfoTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(dsMdseInfoTMsg);

        String svcAllocTpCd = dsMdseInfoTMsg.svcAllocTpCd.getValue();
        String taxExemTpCd  = dsMdseInfoTMsg.taxExemTpCd.getValue();

        String svcAllocTrxTpNm = null;
        if (svcAllocTpCd != null) {
            SVC_ALLOC_TPTMsg svcAllocTpTMsg = new SVC_ALLOC_TPTMsg();
            setValue(svcAllocTpTMsg.glblCmpyCd,   glblCmpyCd);
            setValue(svcAllocTpTMsg.svcAllocTpCd, svcAllocTpCd);
            svcAllocTpTMsg = (SVC_ALLOC_TPTMsg) S21CacheTBLAccessor.findByKey(svcAllocTpTMsg);
            if (svcAllocTpTMsg != null) {
                svcAllocTrxTpNm = svcAllocTpTMsg.svcAllocTrxTpNm.getValue();
            }
        }
        if (!hasValue(svcAllocTrxTpNm)) {
            // default set if value is null
            svcAllocTrxTpNm = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_DEFAULT_TAX_TRX_TP, glblCmpyCd);
        }

        //Service Allocation Type
        setValue(taxCalcInputLinePMsg.svcAllocTpCd_A, svcAllocTpCd);
        //Trx Type
        setValue(taxCalcInputLinePMsg.svcAllocTrxTpNm_A, svcAllocTrxTpNm);
        //Product Tax Code
        setValue(taxCalcInputLinePMsg.taxExemTpCd_A, taxExemTpCd);

    }
    //QC#16531 add End
    // Add Start 2018/08/09 QC#27442
    /**
     * <pre>
     * Get Territory Group Type Code From DS Order Type Code
     * @param dsOrdTpCd String
     * @return String
     * </pre>
     */
    public String getTrtyGrpTpTxtFromDsOrdTpCd(String dsOrdTpCd) {

        if (!ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            return null;
        }

        DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.dsOrdTpCd, dsOrdTpCd);

        dsOrdTpProcDfnTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(dsOrdTpProcDfnTMsg);
        if (dsOrdTpProcDfnTMsg != null) {
            return dsOrdTpProcDfnTMsg.trtyGrpTpTxt.getValue();
        }

        return null;
    }
    /**
     * <pre>
     * Get Ship To Info
     * </pre>
     * @param invoiceBean InvoiceDataBean
     */
    private boolean getDefaultBillShip(InvoiceDataBean invoiceBean, String sellToCustCd) {
        NMZC610001PMsg pmsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmsg.xxModeCd, NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP);
        ZYPEZDItemValueSetter.setValue(pmsg.dsAcctNum_I1, sellToCustCd);
        ZYPEZDItemValueSetter.setValue(pmsg.dsTrxRuleTpCd, DS_TRX_RULE_TP.SUPPLIES);

        NMZC610001 api = new NMZC610001();
        api.execute(pmsg, ONBATCH_TYPE.BATCH);

        boolean errFlg = false;
        List<S21ApiMessage> msgListIn = S21ApiUtil.getXxMsgList(pmsg);
        for (S21ApiMessage msg : msgListIn) {
            String msgId = msg.getXxMsgid();
            if (ZYPCommonFunc.hasValue(msgId)) {
                S21InfoLogOutput.println(msgId);
                errFlg = true;
            }
        }
        if (!ZYPCommonFunc.hasValue(pmsg.DefaultBillShipList.no(0).shipToCustCd)) {
            S21InfoLogOutput.println(MSG_ID.NWZM0507E.toString());
            errFlg = true;
        }
        if (!ZYPCommonFunc.hasValue(pmsg.DefaultBillShipList.no(0).billToCustCd)) {
            S21InfoLogOutput.println(MSG_ID.NWZM0510E.toString());
            errFlg = true;
        }
        invoiceBean.setShipToCustCd(pmsg.DefaultBillShipList.no(0).shipToCustCd.getValue());
        invoiceBean.setBillToCustCd(pmsg.DefaultBillShipList.no(0).billToCustCd.getValue());

        return errFlg;

    }
    // Add End 2018/08/09 QC#27442

    /**
     * 2020/04/27 QC#56638 Add
     * Get Sales Req Defaulting
     * @param dsOrdTpCd String
     * @return Boolean
     */
    private boolean isSlsReqDef(String dsOrdTpCd) {

        boolean isShipBase = true;
        if (ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, dsOrdTpCd);
            tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(tMsg);

            if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.baseLocTxt)) {
                if ("Ship To Location".equals(tMsg.baseLocTxt.getValue())) {
                    isShipBase = true;
                } else if ("Sold To Location".equals(tMsg.baseLocTxt.getValue())) {
                    isShipBase = false;
                } else {
                    isShipBase = true;
                }
            }
        }

        return isShipBase;
    }
}
