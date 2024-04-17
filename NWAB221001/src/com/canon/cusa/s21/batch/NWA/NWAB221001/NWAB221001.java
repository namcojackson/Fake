/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB221001;

import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBStringItem;
import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTMsg;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPOTMsgArray;
import business.db.DS_IMPT_ORDTMsg;
import business.db.DS_IMPT_ORD_ERRTMsg;
import business.db.EDI_PO_ACK_DTLTMsg;
import business.db.EDI_PO_ACK_HDRTMsg;
import business.db.EDI_PO_ACK_SHPG_PLNTMsg;
import business.parts.NWZC226001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC226001.NWZC226001;
import com.canon.cusa.s21.api.NWZ.NWZC226001.constant.NWZC226001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB221001.constant.NWAB221001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB221001.constant.NWAB221001Constant.MSG_ID;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.EDI_ACK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmListResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Order Import Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/17/2016   Fujitsu         M.Hara          Create          N/A
 * 04/28/2016   Fujitsu         M.Hara          Update          QC#7708
 * 05/09/2016   Fujitsu         M.Hara          Update          QC#7997
 * 05/10/2016   Fujitsu         T.Ishii         Update          QC#7997-2
 * 05/20/2016   Fujitsu         M.Hara          Update          S21_NA#8409
 * 06/17/2016   Fujitsu         M.Hara          Update          S21_NA#10087
 * 06/28/2016   Fujitsu         M.Hara          Update          S21_NA#10977
 * 07/06/2016   Fujitsu         M.Hara          Update          S21_NA#11577
 * 07/08/2016   Fujitsu         M.Hara          Update          S21_NA#11629
 * 07/11/2016   Fujitsu         T.Ishii         Update          S21_NA#6480
 * 07/11/2016   Fujitsu         T.Ishii         Update          S21_NA#10986
 * 07/15/2016   Fujitsu         M.Hara          Update          S21_NA#11837
 * 07/21/2016   Fujitsu         M.Hara          Update          S21_NA#11838
 * 07/21/2016   Fujitsu         T.Ishii         Update          S21_NA#12074
 * 07/27/2016   Fujitsu         M.Hara          Update          S21_NA#11841
 * 07/28/2016   Fujitsu         M.Hara          Update          S21_NA#11850
 * 07/28/2016   Fujitsu         M.Hara          Update          S21_NA#11852
 * 08/04/2016   Fujitsu         M.Hara          Update          S21_NA#13029
 * 08/05/2016   Fujitsu         M.Hara          Update          S21_NA#13089
 * 08/10/2016   Fujitsu         M.Hara          Update          S21_NA#13148
 * 08/14/2016   Fujitsu         H.Nagashima     Update          S21_NA#13747
 * 08/31/2016   Fujitsu         M.Hara          Update          S21_NA#8003
 * 09/08/2016   Fujitsu         Y.Taoka         Update          S21_NA#8003
 * 09/09/2016   Fujitsu         S.Ohki          Update          S21_NA#14278
 * 09/15/2016   Fujitsu         S.Ohki          Update          S21_NA#14261
 * 09/29/2016   Fujitsu         S.Ohki          Update          S21_NA#8659
 * 10/04/2016   Fujitsu         T.Ishii         Update          S21_NA#14922
 * 10/05/2016   Fujitsu         T.Ishii         Update          S21_NA#15004
 * 10/06/2016   Fujitsu         S.Ohki          Update          S21_NA#14884
 * 10/12/2016   Fujitsu         S.Ohki          Update          S21_NA#12145
 * 10/14/2016   Fujitsu         S.Ohki          Update          S21_NA#14744
 * 10/14/2016   Fujitsu         W.Honda         Update          S21_NA#14927
 * 10/14/2016   Fujitsu         K.Sato          Update          S21_NA#11964
 * 10/17/2016   Fujitsu         S.Ohki          Update          S21_NA#15234
 * 10/27/2016   Fujitsu         M.Ohno          Update          S21_NA#15569
 * 10/28/2016   Fujitsu         M.Ohno          Update          S21_NA#15645
 * 11/01/2016   Fujitsu         S.Ohki          Update          S21_NA#15662
 * 11/02/2016   Fujitsu         T.Ishii         Update          S21_NA#14815
 * 11/07/2016   Fujitsu         S.Ohki          Update          S21_NA#15791
 * 11/17/2016   Fujitsu         T.Ishii         Update          S21_NA#16027
 * 11/25/2016   Fujitsu         S.Ohki          Update          S21_NA#16066
 * 12/14/2016   Fujitsu         T.Yoshida       Update          S21_NA#15837
 * 12/26/2016   Fujitsu         S.Ohki          Update          S21_NA#16617
 * 01/24/2017   Fujitsu         M.Ohno          Update          S21_NA#16747
 * 03/02/2017   Fujitsu         N.Aoyama        Update          S21_NA#16575
 * 03/13/2017   Fujitsu         S.Ohki          Update          S21_NA#16790
 * 08/01/2017   CITS            K.Ogino         Update          S21_NA#19273
 * </pre>
 */
public class NWAB221001 extends S21BatchMain {

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** Mail Template ID */
    private String mailTemplateId = null;

    /** Header Bean List */
    private List< ? > hdrBeanList = null;

    /** exclude error message code (not for screen) */
    private final List<String> excludeMessageCode = asList("NWZM1621E", "NWZM1624E", "NWZM1626E"); // S21_NA#10986

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB221001().executeBatch(NWAB221001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        writeStartLogLn("initRoutine");

        try {
            this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

            // Global Company Code
            this.glblCmpyCd = getGlobalCompanyCode();
            if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
                throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Global Company Code"));
            }

            // Sales Date
            this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

            // Mail Template
            this.mailTemplateId = getUserVariable1();
            if (!ZYPCommonFunc.hasValue(this.mailTemplateId)) {
                throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Mail Template ID"));
            }
        } finally {
            writeEndLogLn("initRoutine");
        }
    }

    @Override
    protected void mainRoutine() {
        writeStartLogLn("mainRoutine");
        ImptHdrBean hdrBean = null;

        try {
            // *****************************************************************
            // Get ImptHdrBean From DS_IMPT_ORD
            // *****************************************************************
            DS_IMPT_ORDTMsg dsImptOrdTMsg;
            String upStsCd = null;
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS);

            ImptHdrBeanCreator beancreater = new ImptHdrBeanCreator();
            hdrBeanList = ssmBatchClient.queryObjectList("getImptHdr", ssmParam, beancreater);

            for (int hdrIndex = 0; hdrIndex < hdrBeanList.size(); hdrIndex++) {

                hdrBean = (ImptHdrBean) hdrBeanList.get(hdrIndex);

                // *************************************************************
                // Table Line Lock
                // *************************************************************
                dsImptOrdTMsg = new DS_IMPT_ORDTMsg();
                ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.dsImptOrdPk, hdrBean.getDsImptOrdPk());

                dsImptOrdTMsg = (DS_IMPT_ORDTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsImptOrdTMsg);
                checkTMsgDbAccess(dsImptOrdTMsg);

                callOrderImportAPI(hdrBean);

                if (hdrBean.hasError()) {

                    // import error.
                    this.rollback();

                    upStsCd = IMPT_STS.ERROR;
                    // *************************************************************
                    // Regist EDI ACK Data for error
                    // *************************************************************
                    if (hdrBean.isEdiData() && ZYPConstant.FLG_ON_Y.equals(hdrBean.getSendPoAckFlg())) {

                        registEDiAckData(hdrBean);
                    }
                } else {

                    // import successfully
                    upStsCd = IMPT_STS.SUCCESS;
                }

                // *************************************************************
                // Update Import Status Code
                // *************************************************************
                ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.imptStsCd, upStsCd);
                ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.cpoOrdNum, hdrBean.getNewOrdNum());
                EZDTBLAccessor.updateSelectionField(dsImptOrdTMsg, new String[] {"imptStsCd", "cpoOrdNum" });
                addUpdateErrorMsgList(dsImptOrdTMsg, hdrBean, true);

                commit();
                writeLogLn("commited (%s)", getTargetKey(hdrBean));

                // *************************************************************
                // Insert DS_IMPT_ORD_ERR
                // *************************************************************
                insertDsImptOrdErr(hdrBean);

                commit();
            }
        } finally {
            writeEndLogLn("mainRoutine");
        }
    }

    private void callOrderImportAPI(ImptHdrBean hdrBean) {

        NWZC226001PMsg param = new NWZC226001PMsg();

        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.slsDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(param.dsImptOrdPk, hdrBean.getDsImptOrdPk());
        ZYPEZDItemValueSetter.setValue(param.xxModeCd, NWZC226001Constant.MODE_CREATE_ORDER);  // 2017/03/13 S21_NA#16790 Mod

        new NWZC226001().execute(param, ONBATCH_TYPE.ONLINE);

        for (int i = 0; i < param.xxMsgPrmList.getValidCount(); i++) {

            DsImptOrdErrBean dsImptOrdErr = new DsImptOrdErrBean(hdrBean);
            EZDMsg.copy(param.xxMsgPrmList.no(i), null, dsImptOrdErr, null);
            if (!ZYPCommonFunc.hasValue(dsImptOrdErr.dsImptOrdPk)) {

                ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdPk, hdrBean.getDsImptOrdPk());
            }
            hdrBean.setErrorBean(dsImptOrdErr);
        }

        if (param.xxMsgPrmList.getValidCount() == 0) {

            if (!ZYPCommonFunc.hasValue(param.cpoOrdNum)) {

                DsImptOrdErrBean dsImptOrdErr = new DsImptOrdErrBean(hdrBean);
                ZYPEZDItemValueSetter.setValue(dsImptOrdErr.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdPk, hdrBean.getDsImptOrdPk());
                ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdErrMsgId, MSG_ID.NWZM2200E.toString());
                ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdErrTxt, S21MessageFunc.clspGetMessage(MSG_ID.NWZM2200E.toString()));

                hdrBean.setErrorBean(dsImptOrdErr);
            } else {

                hdrBean.setNewOrdNum(param.cpoOrdNum.getValue());
            }
        }
    }

    @Override
    protected void termRoutine() {
        writeStartLogLn("termRoutine");
        try {
            int normalRecCnt = 0;
            int errRecCnt = 0;

            ImptHdrBean hdrBean;
            for (int i = 0; i < this.hdrBeanList.size(); i++) {
                hdrBean = (ImptHdrBean) this.hdrBeanList.get(i);

                if (hdrBean.hasError()) {
                    postErrorMail(hdrBean);
                }

                if (hdrBean.hasError()) {
                    errRecCnt++;
                } else {
                    normalRecCnt++;
                }
            }

            TERM_CD termCd = TERM_CD.NORMAL_END;

            if (errRecCnt > 0) {
                termCd = TERM_CD.WARNING_END;
            }

            setTermState(termCd, normalRecCnt, errRecCnt, normalRecCnt + errRecCnt);

        } finally {
            writeEndLogLn("termRoutine");
        }
    }

    /**
     * ImptHdrBeanCreator
     * @author
     */
    protected class ImptHdrBeanCreator extends S21SsmListResultSetHandlerSupport {

        @Override
        protected List<ImptHdrBean> doProcessQueryResult(ResultSet rs) throws SQLException {
            writeStartLogLn("doProcessQueryResult");

            try {
                List<ImptHdrBean> beanList = new ArrayList<ImptHdrBean>();

                rs.last();
                int recCnt = rs.getRow();
                rs.first();
                if (0 == recCnt) {
                    writeErrLog(MSG_ID.ZZOM0011E.toString());
                } else {
                    setImptHdrBean(rs, beanList, recCnt);
                }

                return beanList;
            } finally {
                writeEndLogLn("doProcessQueryResult");
            }
        }

        /**
         * setImptHdrBean
         * @param rs ResultSet
         * @param beanList List<ImptHdrBean>
         * @param recAllCnt int
         * @throws SQLException
         */
        void setImptHdrBean(ResultSet rs, List<ImptHdrBean> beanList, int recAllCnt) throws SQLException {
            writeStartLogLn("doProcessQueryResult");

            try {
                int recCnt = 0;

                ImptHdrBean bean;

                rs.first();

                do {
                    bean = new ImptHdrBean();
                    bean.setDsImptOrdPk(convToBigDecimal(rs.getBigDecimal("DS_IMPT_ORD_PK")));
                    bean.setCpoSrcTpCd(convToString(rs.getString("CPO_SRC_TP_CD")));
                    bean.setOrdSrcImptTs(convToString(rs.getString("ORD_SRC_IMPT_TS")));
                    bean.setOrdSrcRefNum(convToString(rs.getString("ORD_SRC_REF_NUM")));
                    bean.setImptStsCd(convToString(rs.getString("IMPT_STS_CD")));
                    bean.setSysSrcCd(convToString(rs.getString("SYS_SRC_CD")));
                    bean.setDsOrdCatgCd(convToString(rs.getString("DS_ORD_CATG_CD")));
                    bean.setDsOrdTpCd(convToString(rs.getString("DS_ORD_TP_CD")));
                    bean.setDsOrdRsnCd(convToString(rs.getString("DS_ORD_RSN_CD")));
                    bean.setCpoTpCd(convToString(rs.getString("CPO_ORD_TP_CD")));
                    bean.setCustIssPoNum(convToString(rs.getString("CUST_ISS_PO_NUM")));
                    bean.setCustIssPoDt(convToString(rs.getString("CUST_ISS_PO_DT")));
                    bean.setBillToCustAcctCd(convToString(rs.getString("BILL_TO_CUST_ACCT_CD")));
                    bean.setBillToCustCd(convToString(rs.getString("BILL_TO_CUST_CD")));
                    bean.setShipToCustAcctCd(convToString(rs.getString("SHIP_TO_CUST_ACCT_CD")));
                    bean.setShipToCustCd(convToString(rs.getString("SHIP_TO_CUST_CD")));
                    bean.setSellToCustCd(convToString(rs.getString("SELL_TO_CUST_CD")));
                    bean.setSoldToCustLocCd(convToString(rs.getString("SOLD_TO_CUST_LOC_CD")));
                    bean.setDropShipFlg(convToString(rs.getString("DROP_SHIP_FLG")));
                    bean.setShipToLocNm(convToString(rs.getString("SHIP_TO_LOC_NM")));
                    bean.setShipToAddlLocNm(convToString(rs.getString("SHIP_TO_ADDL_LOC_NM")));
                    bean.setShipToFirstLineAddr(convToString(rs.getString("SHIP_TO_FIRST_LINE_ADDR")));
                    bean.setShipToScdLineAddr(convToString(rs.getString("SHIP_TO_SCD_LINE_ADDR")));
                    bean.setShipToThirdLineAddr(convToString(rs.getString("SHIP_TO_THIRD_LINE_ADDR")));
                    bean.setShipToFrthLineAddr(convToString(rs.getString("SHIP_TO_FRTH_LINE_ADDR")));
                    bean.setShipToCtyAddr(convToString(rs.getString("SHIP_TO_CTY_ADDR")));
                    bean.setShipToStCd(convToString(rs.getString("SHIP_TO_ST_CD")));
                    bean.setShipToProvNm(convToString(rs.getString("SHIP_TO_PROV_NM")));
                    bean.setShipToCntyNm(convToString(rs.getString("SHIP_TO_CNTY_NM")));
                    bean.setShipToPostCd(convToString(rs.getString("SHIP_TO_POST_CD")));
                    bean.setShipToCtryCd(convToString(rs.getString("SHIP_TO_CTRY_CD")));
                    bean.setShipTo01RefCmntTxt(convToString(rs.getString("SHIP_TO_01_REF_CMNT_TXT")));
                    bean.setShipTo02RefCmntTxt(convToString(rs.getString("SHIP_TO_02_REF_CMNT_TXT")));
                    bean.setAdminPsnCd(convToString(rs.getString("ADMIN_PSN_CD")));
                    bean.setRddDt(convToString(rs.getString("RDD_DT")));
                    bean.setFrtCondCd(convToString(rs.getString("FRT_COND_CD")));
                    bean.setSpclHdlgTpCd(convToString(rs.getString("SPCL_HDLG_TP_CD")));
                    bean.setCarrCd(convToString(rs.getString("CARR_CD")));
                    bean.setCarrSvcLvlCd(convToString(rs.getString("CARR_SVC_LVL_CD")));
                    bean.setShpgSvcLvlCd(convToString(rs.getString("SHPG_SVC_LVL_CD")));
                    bean.setFrtChrgToCd(convToString(rs.getString("FRT_CHRG_TO_CD")));
                    bean.setFrtChrgMethCd(convToString(rs.getString("FRT_CHRG_METH_CD")));
                    bean.setCarrAcctNum(convToString(rs.getString("CARR_ACCT_NUM")));
                    bean.setAddPmtTermCashDiscCd(convToString(rs.getString("ADD_PMT_TERM_CASH_DISC_CD")));
                    bean.setDsPmtMethCd(convToString(rs.getString("DS_PMT_METH_CD")));
                    bean.setPrePmtChkNum(convToString(rs.getString("PRE_PMT_CHK_NUM")));
                    bean.setPrePmtAmt(convToBigDecimal(rs.getBigDecimal("PRE_PMT_AMT")));
                    bean.setPrePmtTpCd(convToString(rs.getString("PRE_PMT_TP_CD")));
                    bean.setPrcBaseDt(convToString(rs.getString("PRC_BASE_DT")));
                    bean.setNegoDealAmt(convToBigDecimal(rs.getBigDecimal("NEGO_DEAL_AMT")));
                    bean.setPrcCatgCd(convToString(rs.getString("PRC_CATG_CD")));
                    bean.setFlPrcListCd(convToString(rs.getString("FL_PRC_LIST_CD")));
                    bean.setPrcContrNum(convToString(rs.getString("PRC_CONTR_NUM")));
                    bean.setCsmpContrNum(convToString(rs.getString("CSMP_CONTR_NUM")));
                    bean.setDlrRefNum(convToString(rs.getString("DLR_REF_NUM")));
                    bean.setOrdSgnDt(convToString(rs.getString("ORD_SGN_DT")));
                    bean.setAquNum(convToString(rs.getString("AQU_NUM")));
                    bean.setSlsRepTocCd(convToString(rs.getString("SLS_REP_TOC_CD")));
                    bean.setLoanPerDaysAot(rs.getInt("LOAN_PER_DAYS_AOT"));
                    // S21_NA#11841
                    if (rs.wasNull()) {
                        bean.setLoanPerDaysAot(null);
                    }
                    bean.setLeaseCmpyPoNum(convToString(rs.getString("LEASE_CMPY_PO_NUM")));
                    bean.setLeaseTermCd(convToString(rs.getString("LEASE_TERM_CD")));
                    // 11/01/2016 S21_NA#15662 add Start
                    bean.setLeaseEndTermPrchOptCd(convToString(rs.getString("LEASE_PRCH_OPT_CD")));
                    // 11/01/2016 S21_NA#15662 add End
                    bean.setLeasePmtFreqCd(convToString(rs.getString("LEASE_PMT_FREQ_CD")));
                    bean.setOrdLogTpCd(convToString(rs.getString("ORD_LOG_TP_CD")));
                    bean.setCrRebilRsnCatgCd(convToString(rs.getString("CR_REBIL_RSN_CATG_CD")));
                    bean.setOrigOrdNum(convToString(rs.getString("ORIG_ORD_NUM")));
                    bean.setSendInvFlg(convToString(rs.getString("SEND_INV_FLG")));
                    bean.setReBillPairCpoOrdNum(convToString(rs.getString("RE_BILL_PAIR_CPO_ORD_NUM")));
                    bean.setOrdHdrEdtblFlg(convToString(rs.getString("ORD_HDR_EDTBL_FLG")));
                    bean.setOrdCratModeCd(convToString(rs.getString("ORD_CRAT_MODE_CD")));
                    // Unit Test#201
                    bean.setAutoDocAttFlg(convToString(rs.getString("AUTO_DOC_ATT_FLG")));
                    bean.setDclnSvcCd(convToString(rs.getString("DCLN_SVC_CD")));
                    bean.setLeaseTermMthAot(convToBigDecimal(rs.getBigDecimal("LEASE_TERM_MTH_AOT")));
                    beanList.add(bean);
                    bean.setPrcFrzFlg(convToString(rs.getString("PRC_FRZ_FLG"))); // S21_NA#16027

                    writeLogLn("\r\n***** [%d/%d Record] *****", ++recCnt, recAllCnt);
                    writeLogLn(bean.toString());
                } while (rs.next());
            } finally {
                writeEndLogLn("doProcessQueryResult");
            }
        }
    }

    /**
     * deriveCpo
     * @param cpoOrdNum String
     * @return CPOTMsg
     */
    private CPOTMsg deriveCpo(String cpoOrdNum) {
        writeStartLogLn("deriveCpo", cpoOrdNum);

        try {
            CPOTMsg cpoCondTMsg = new CPOTMsg();
            cpoCondTMsg.setSQLID("001");
            cpoCondTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            cpoCondTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);

            CPOTMsgArray cpoTMsgArray = (CPOTMsgArray) EZDTBLAccessor.findByCondition(cpoCondTMsg);

            if (hasValidValue(cpoTMsgArray)) {
                return cpoTMsgArray.no(0);
            } else {
                return null;
            }
        } finally {
            writeEndLogLn("deriveCpo", cpoOrdNum);
        }
    }

    /**
     * registEDiAckData
     * @param hdrBean ImptHdrBean
     */
    private void registEDiAckData(ImptHdrBean hdrBean) {
        writeStartLogLn("registEDiAckData", hdrBean);

        try {

            // *****************************************************************
            // Update Latest Create Flag
            // *****************************************************************
            if (ZYPCommonFunc.hasValue(hdrBean.getOrdSrcRefNum())) {
                updateLtstCratFlg(hdrBean);
            }

            // *****************************************************************
            // Insert EDI_PO_ACK_HDR
            // *****************************************************************
            insertEdiPoAckHdr(hdrBean);

            // *****************************************************************
            // Insert EDI_PO_ACK_DTL
            // *****************************************************************
            insertEdiPoAckDtl(hdrBean);

            // *****************************************************************
            // Insert EDI_PO_ACK_SHPG_PLN
            // *****************************************************************
            insertEdiPoAckShpgPln(hdrBean);
        } finally {
            writeEndLogLn("registEDiAckData", hdrBean);
        }
    }

    private boolean insertEdiPoAckHdr(ImptHdrBean hdrBean) {
        writeStartLogLn("insertEdiPoAckHdr", hdrBean);

        try {
            BigDecimal ediPoAckHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_PO_ACK_HDR_SQ);
            hdrBean.setEdiPoAckHdrPk(ediPoAckHdrPk);

            EDI_PO_ACK_HDRTMsg tMsg = new EDI_PO_ACK_HDRTMsg();
            if (hdrBean.hasError()) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.ediPoAckHdrPk, ediPoAckHdrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.ordSrcRefNum, hdrBean.getOrdSrcRefNum());
                tMsg.cpoOrdNum.clear();
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdCatgCd, hdrBean.getDsOrdCatgCd());
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, hdrBean.getDsOrdTpCd());
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdRsnCd, hdrBean.getDsOrdRsnCd());
                ZYPEZDItemValueSetter.setValue(tMsg.addPmtTermCashDiscCd, hdrBean.getAddPmtTermCashDiscCd());
                ZYPEZDItemValueSetter.setValue(tMsg.frtCondCd, hdrBean.getFrtCondCd());
                ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, hdrBean.getShpgSvcLvlCd());
                ZYPEZDItemValueSetter.setValue(tMsg.carrSvcLvlCd, hdrBean.getCarrSvcLvlCd());
                ZYPEZDItemValueSetter.setValue(tMsg.billToCustAcctCd, hdrBean.getBillToCustAcctCd());
                ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, hdrBean.getBillToCustCd());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCustAcctCd, hdrBean.getShipToCustAcctCd());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, hdrBean.getShipToCustCd());
                ZYPEZDItemValueSetter.setValue(tMsg.sellToCustCd, hdrBean.getSellToCustCd());
                ZYPEZDItemValueSetter.setValue(tMsg.soldToCustLocCd, hdrBean.getSoldToCustLocCd());
                ZYPEZDItemValueSetter.setValue(tMsg.dropShipFlg, hdrBean.getDropShipFlg());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToLocNm, hdrBean.getShipToLocNm());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToAddlLocNm, hdrBean.getShipToAddlLocNm());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToFirstLineAddr, hdrBean.getShipToFirstLineAddr());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToScdLineAddr, hdrBean.getShipToScdLineAddr());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToThirdLineAddr, hdrBean.getShipToThirdLineAddr());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToFrthLineAddr, hdrBean.getShipToFrthLineAddr());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCtyAddr, hdrBean.getShipToCtyAddr());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToStCd, hdrBean.getShipToStCd());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToProvNm, hdrBean.getShipToProvNm());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCntyNm, hdrBean.getShipToCntyNm());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToPostCd, hdrBean.getShipToPostCd());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCtryCd, hdrBean.getShipToCtryCd());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToFirstRefCmntTxt, hdrBean.getShipTo01RefCmntTxt());
                ZYPEZDItemValueSetter.setValue(tMsg.shipToScdRefCmntTxt, hdrBean.getShipTo02RefCmntTxt());
                tMsg.entCpoTotDealNetAmt.clear();
                ZYPEZDItemValueSetter.setValue(tMsg.custIssPoNum, hdrBean.getCustIssPoNum());
                ZYPEZDItemValueSetter.setValue(tMsg.custIssPoDt, hdrBean.getCustIssPoDt());
                ZYPEZDItemValueSetter.setValue(tMsg.carrAcctNum, hdrBean.getCarrAcctNum());
                tMsg.cpoOrdTs.clear();
                ZYPEZDItemValueSetter.setValue(tMsg.rddDt, hdrBean.getRddDt());
                ZYPEZDItemValueSetter.setValue(tMsg.ediAckSentFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.ltstCratFlg, ZYPConstant.FLG_ON_Y);
                tMsg.delyAddlCmntTxt.clear();
                tMsg.prcCalcDt.clear();
                ZYPEZDItemValueSetter.setValue(tMsg.ltstCratFlg, ZYPConstant.FLG_ON_Y);
            } else {
                String newOrdNum = hdrBean.getNewOrdNum();

                CPOTMsg cpoTMsg = deriveCpo(newOrdNum);
                hdrBean.setOrigCpo(cpoTMsg);

                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.ediPoAckHdrPk, ediPoAckHdrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.ordSrcRefNum, cpoTMsg.ordSrcRefNum);
                ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, cpoTMsg.cpoOrdNum);
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdCatgCd, cpoTMsg.dsOrdCatgCd);
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, cpoTMsg.dsOrdTpCd);
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdRsnCd, cpoTMsg.dsOrdRsnCd);
                ZYPEZDItemValueSetter.setValue(tMsg.addPmtTermCashDiscCd, cpoTMsg.addPmtTermCashDiscCd);
                ZYPEZDItemValueSetter.setValue(tMsg.frtCondCd, cpoTMsg.frtCondCd);
                ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, cpoTMsg.addShpgSvcLvlCd);
                ZYPEZDItemValueSetter.setValue(tMsg.carrSvcLvlCd, cpoTMsg.carrSvcLvlCd);
                ZYPEZDItemValueSetter.setValue(tMsg.billToCustAcctCd, cpoTMsg.billToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, cpoTMsg.billToCustCd);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCustAcctCd, cpoTMsg.shipToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, cpoTMsg.addShipToCustCd);
                ZYPEZDItemValueSetter.setValue(tMsg.sellToCustCd, cpoTMsg.sellToCustCd);
                ZYPEZDItemValueSetter.setValue(tMsg.soldToCustLocCd, cpoTMsg.soldToCustLocCd);
                ZYPEZDItemValueSetter.setValue(tMsg.dropShipFlg, cpoTMsg.addDropShipFlg);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToLocNm, cpoTMsg.addShipToLocNm);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToAddlLocNm, cpoTMsg.addShipToAddlLocNm);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToFirstLineAddr, cpoTMsg.addShipToFirstLineAddr);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToScdLineAddr, cpoTMsg.addShipToScdLineAddr);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToThirdLineAddr, cpoTMsg.addShipToThirdLineAddr);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToFrthLineAddr, cpoTMsg.addShipToFrthLineAddr);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCtyAddr, cpoTMsg.addShipToCtyAddr);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToStCd, cpoTMsg.addShipToStCd);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToProvNm, cpoTMsg.addShipToProvNm);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCntyNm, cpoTMsg.addShipToCntyNm);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToPostCd, cpoTMsg.addShipToPostCd);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCtryCd, cpoTMsg.addShipToCtryCd);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToFirstRefCmntTxt, cpoTMsg.addShipTo01RefCmntTxt);
                ZYPEZDItemValueSetter.setValue(tMsg.shipToScdRefCmntTxt, cpoTMsg.addShipTo02RefCmntTxt);
                ZYPEZDItemValueSetter.setValue(tMsg.entCpoTotDealNetAmt, cpoTMsg.entCpoTotDealNetAmt);
                ZYPEZDItemValueSetter.setValue(tMsg.custIssPoNum, cpoTMsg.custIssPoNum);
                ZYPEZDItemValueSetter.setValue(tMsg.custIssPoDt, cpoTMsg.custIssPoDt);
                ZYPEZDItemValueSetter.setValue(tMsg.carrAcctNum, cpoTMsg.carrAcctNum);
                ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdTs, cpoTMsg.cpoOrdTs);
                ZYPEZDItemValueSetter.setValue(tMsg.rddDt, cpoTMsg.addRddDt);
                ZYPEZDItemValueSetter.setValue(tMsg.ediAckSentFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.ltstCratFlg, ZYPConstant.FLG_ON_Y);
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", this.glblCmpyCd);
                ssmParam.put("cpoOrdNum", newOrdNum);
                ZYPEZDItemValueSetter.setValue(tMsg.delyAddlCmntTxt, (String) ssmBatchClient.queryObject("getDelyAddlCmnt", ssmParam));
                ZYPEZDItemValueSetter.setValue(tMsg.prcCalcDt, cpoTMsg.prcCalcDt);
            }

            S21FastTBLAccessor.insert(tMsg);
            if (!addInsertErrorMsgList(tMsg, hdrBean, true)) {
                return false;
            }

            return true;
        } finally {
            writeEndLogLn("insertEdiPoAckHdr", hdrBean);
        }
    }

    private boolean insertEdiPoAckDtl(ImptHdrBean hdrBean) {
        writeStartLogLn("insertEdiPoAckDtl", hdrBean);

        try {
            BigDecimal ediPoAckDtlPk;
            EDI_PO_ACK_DTLTMsg tMsg;
            ExpendMdseBean mdseBean;
            String ediAckStsCd;
            boolean isSuccess = true;

            if (hdrBean.hasError()) {

                for (DsImptLineBean lineBean : hdrBean.getDsImptOrdLineList()) {
                    tMsg = new EDI_PO_ACK_DTLTMsg();
                    ediPoAckDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_PO_ACK_DTL_SQ);
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.ediPoAckDtlPk, ediPoAckDtlPk);
                    ZYPEZDItemValueSetter.setValue(tMsg.ediPoAckHdrPk, hdrBean.getEdiPoAckHdrPk());
                    ZYPEZDItemValueSetter.setValue(tMsg.ordSrcRefNum, convToString(hdrBean.getOrdSrcRefNum()));
                    ZYPEZDItemValueSetter.setValue(tMsg.ordSrcRefLineNum, lineBean.ordSrcRefLineNum);
                    ZYPEZDItemValueSetter.setValue(tMsg.ordSrcRefLineSubNum, lineBean.ordSrcRefLineSubNum);
                    tMsg.cpoOrdNum.clear();
                    tMsg.cpoDtlLineNum.clear();
                    tMsg.cpoDtlLineSubNum.clear();

                    if (lineBean.hasEdiCustUomError) {
                        ediAckStsCd = EDI_ACK_STS.ITEM_REJECTED_UNIT_OF_MEASURE;
                    } else {
                        ediAckStsCd = EDI_ACK_STS.ITEM_REJECTED;
                    }
                    ZYPEZDItemValueSetter.setValue(tMsg.ediAckStsCd, ediAckStsCd);
                    tMsg.ordLineStsCd.clear();
                    ZYPEZDItemValueSetter.setValue(tMsg.ordQty, lineBean.ordQty);
                    ZYPEZDItemValueSetter.setValue(tMsg.ordCustUomQty, lineBean.ordCustUomQty);
                    ZYPEZDItemValueSetter.setValue(tMsg.entDealNetUnitPrcAmt, lineBean.entDealNetUnitPrcAmt);
                    ZYPEZDItemValueSetter.setValue(tMsg.entCpoDtlDealNetAmt, lineBean.cpoDtlDealNetAmt);
                    ZYPEZDItemValueSetter.setValue(tMsg.entCpoDtlDealSlsAmt, lineBean.cpoDtlDealSlsAmt);
                    ZYPEZDItemValueSetter.setValue(tMsg.unitNetWt, lineBean.unitNetWt);
                    ZYPEZDItemValueSetter.setValue(tMsg.ccyCd, lineBean.ccyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, lineBean.rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, lineBean.rtlSwhCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.custUomCd, lineBean.custUomCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, lineBean.mdseCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.custMdseCd, lineBean.custMdseCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.exchRate, lineBean.exchRate);

                    S21FastTBLAccessor.insert(tMsg);
                    if (!addInsertErrorMsgList(tMsg, hdrBean, true)) {
                        isSuccess = false;
                    }
                }
            } else {
                String newOrdNum = hdrBean.getNewOrdNum();
                Map<String, Object> ssmParam = new HashMap<String, Object>();

                ssmParam.put("glblCmpyCd", this.glblCmpyCd);
                ssmParam.put("cpoOrdNum", newOrdNum);

                List< ? > cpoDtlList = (List< ? >) ssmBatchClient.queryObjectList("getCpoDtlForEdiAck", ssmParam);

                if (!hasValueList(cpoDtlList)) {
                    return true;
                }
                Map< ? , ? > cpoDtl;
                for (int i = 0; i < cpoDtlList.size(); i++) {
                    cpoDtl = (Map< ? , ? >) cpoDtlList.get(i);

                    tMsg = new EDI_PO_ACK_DTLTMsg();
                    ediPoAckDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_PO_ACK_DTL_SQ);
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.ediPoAckDtlPk, ediPoAckDtlPk);
                    ZYPEZDItemValueSetter.setValue(tMsg.ediPoAckHdrPk, hdrBean.getEdiPoAckHdrPk());
                    ZYPEZDItemValueSetter.setValue(tMsg.ordSrcRefNum, convToString(hdrBean.getOrigCpo().ordSrcRefNum.getValue()));
                    ZYPEZDItemValueSetter.setValue(tMsg.ordSrcRefLineNum, convToString(cpoDtl.get("ORD_SRC_REF_LINE_NUM")));
                    ZYPEZDItemValueSetter.setValue(tMsg.ordSrcRefLineSubNum, convToString(cpoDtl.get("ORD_SRC_REF_LINE_SUB_NUM")));
                    ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, convToString(cpoDtl.get("CPO_ORD_NUM")));
                    ZYPEZDItemValueSetter.setValue(tMsg.cpoDtlLineNum, convToString(cpoDtl.get("CPO_DTL_LINE_NUM")));
                    ZYPEZDItemValueSetter.setValue(tMsg.cpoDtlLineSubNum, convToString(cpoDtl.get("CPO_DTL_LINE_SUB_NUM")));

                    mdseBean = hdrBean.getImptLineMdseBean(tMsg.cpoDtlLineNum.getValue(), tMsg.cpoDtlLineSubNum.getValue());
                    mdseBean.setEdiPoAckDtlPk(ediPoAckDtlPk);
                    if (mdseBean.isSpuersession()) {
                        ediAckStsCd = EDI_ACK_STS.ITEM_SUPERCEDED;
                    } else {
                        ediAckStsCd = EDI_ACK_STS.ITEM_ACCEPTED;
                    }
                    ZYPEZDItemValueSetter.setValue(tMsg.ediAckStsCd, ediAckStsCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.ordLineStsCd, convToString(cpoDtl.get("ORD_LINE_STS_CD")));
                    ZYPEZDItemValueSetter.setValue(tMsg.ordQty, convToBigDecimal(cpoDtl.get("ORD_QTY")));
                    ZYPEZDItemValueSetter.setValue(tMsg.ordCustUomQty, convToBigDecimal(cpoDtl.get("ORD_CUST_UOM_QTY")));
                    ZYPEZDItemValueSetter.setValue(tMsg.entDealNetUnitPrcAmt, convToBigDecimal(cpoDtl.get("ENT_DEAL_NET_UNIT_PRC_AMT")));
                    ZYPEZDItemValueSetter.setValue(tMsg.entCpoDtlDealNetAmt, convToBigDecimal(cpoDtl.get("ENT_CPO_DTL_DEAL_NET_AMT")));
                    ZYPEZDItemValueSetter.setValue(tMsg.entCpoDtlDealSlsAmt, convToBigDecimal(cpoDtl.get("ENT_CPO_DTL_DEAL_SLS_AMT")));
                    ZYPEZDItemValueSetter.setValue(tMsg.unitNetWt, convToBigDecimal(cpoDtl.get("UNIT_NET_WT")));
                    ZYPEZDItemValueSetter.setValue(tMsg.ccyCd, convToString(cpoDtl.get("CCY_CD")));
                    ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, convToString(cpoDtl.get("RTL_WH_CD")));
                    ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, convToString(cpoDtl.get("RTL_SWH_CD")));
                    ZYPEZDItemValueSetter.setValue(tMsg.custUomCd, convToString(cpoDtl.get("CUST_UOM_CD")));
                    ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, convToString(cpoDtl.get("MDSE_CD")));
                    ZYPEZDItemValueSetter.setValue(tMsg.custMdseCd, convToString(cpoDtl.get("CUST_MDSE_CD")));
                    ZYPEZDItemValueSetter.setValue(tMsg.exchRate, convToBigDecimal(cpoDtl.get("EXCH_RATE")));

                    S21FastTBLAccessor.insert(tMsg);
                    if (!addInsertErrorMsgList(tMsg, hdrBean, true)) {
                        isSuccess = false;
                    }
                }
            }

            return isSuccess;
        } finally {
            writeEndLogLn("insertEdiPoAckDtl", hdrBean);
        }
    }

    private boolean insertEdiPoAckShpgPln(ImptHdrBean hdrBean) {
        writeStartLogLn("insertEdiPoAckShpgPln", hdrBean);

        try {
            boolean isSuccess = true;

            if (!hdrBean.hasError()) {
                BigDecimal ediPoAckShpgPlnPk;
                EDI_PO_ACK_SHPG_PLNTMsg tMsg;
                ExpendMdseBean mdseBean;
                String newOrdNum = hdrBean.getNewOrdNum();
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                String trxLineNum, trxLineSubNum;

                ssmParam.put("glblCmpyCd", this.glblCmpyCd);
                ssmParam.put("trxHdrNum", newOrdNum);

                List< ? > shpgPlnList = (List< ? >) ssmBatchClient.queryObjectList("getSpngPlnForEdiAck", ssmParam);

                if (!hasValueList(shpgPlnList)) {
                    return true;
                }
                Map< ? , ? > shpgPln;
                for (int i = 0; i < shpgPlnList.size(); i++) {
                    shpgPln = (Map< ? , ? >) shpgPlnList.get(i);
                    trxLineNum = convToString(shpgPln.get("TRX_LINE_NUM"));
                    trxLineSubNum = convToString(shpgPln.get("TRX_LINE_SUB_NUM"));
                    mdseBean = hdrBean.getImptLineMdseBean(trxLineNum, trxLineSubNum);

                    tMsg = new EDI_PO_ACK_SHPG_PLNTMsg();
                    ediPoAckShpgPlnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_PO_ACK_SHPG_PLN_SQ);
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.ediPoAckShpgPlnPk, ediPoAckShpgPlnPk);
                    ZYPEZDItemValueSetter.setValue(tMsg.ediPoAckDtlPk, mdseBean.getEdiPoAckDtlPk());
                    ZYPEZDItemValueSetter.setValue(tMsg.ediPoAckHdrPk, hdrBean.getEdiPoAckHdrPk());
                    ZYPEZDItemValueSetter.setValue(tMsg.shpgPlnNum, convToString(shpgPln.get("SHPG_PLN_NUM")));
                    ZYPEZDItemValueSetter.setValue(tMsg.trxHdrNum, convToString(shpgPln.get("TRX_HDR_NUM")));
                    ZYPEZDItemValueSetter.setValue(tMsg.trxLineNum, trxLineNum);
                    ZYPEZDItemValueSetter.setValue(tMsg.trxLineSubNum, trxLineSubNum);
                    ZYPEZDItemValueSetter.setValue(tMsg.shpgStsCd, convToString(shpgPln.get("SHPG_STS_CD")));
                    ZYPEZDItemValueSetter.setValue(tMsg.spDealUnitPrcAmt, convToBigDecimal(shpgPln.get("SP_DEAL_UNIT_PRC_AMT")));
                    ZYPEZDItemValueSetter.setValue(tMsg.ordQty, convToBigDecimal(shpgPln.get("ORD_QTY")));
                    ZYPEZDItemValueSetter.setValue(tMsg.rddDt, convToString(shpgPln.get("RDD_DT")));
                    ZYPEZDItemValueSetter.setValue(tMsg.pddDt, convToString(shpgPln.get("PDD_DT")));

                    S21FastTBLAccessor.insert(tMsg);
                    if (!addInsertErrorMsgList(tMsg, mdseBean.getDsImptLineBean(), true)) {
                        isSuccess = false;
                    }
                }
            }

            return isSuccess;
        } finally {
            writeEndLogLn("insertEdiPoAckShpgPln", hdrBean);
        }
    }

    private void updateLtstCratFlg(ImptHdrBean hdrBean) {
        writeStartLogLn("updateLtstCratFlg", hdrBean);

        try {
            EDI_PO_ACK_HDRTMsg hdrTMsg;
            BigDecimal ediPoAckHdrPk;
            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("ordSrcRefNum", hdrBean.getOrdSrcRefNum());
            List< ? > shpgPlnList = (List< ? >) ssmBatchClient.queryObjectList("getEdiPoAckHdrForEdiAck", ssmParam);

            if (!hasValueList(shpgPlnList)) {
                return;
            }

            for (int i = 0; i < shpgPlnList.size(); i++) {
                ediPoAckHdrPk = (BigDecimal) shpgPlnList.get(i);

                hdrTMsg = new EDI_PO_ACK_HDRTMsg();

                ZYPEZDItemValueSetter.setValue(hdrTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(hdrTMsg.ediPoAckHdrPk, ediPoAckHdrPk);

                hdrTMsg = (EDI_PO_ACK_HDRTMsg) S21FastTBLAccessor.findByKey(hdrTMsg);
                checkTMsgDbAccess(hdrTMsg);

                if (hdrTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(hdrTMsg.ltstCratFlg, ZYPConstant.FLG_OFF_N);
                    EZDTBLAccessor.updateSelectionField(hdrTMsg, new String[] {"ltstCratFlg" });
                    addUpdateErrorMsgList(hdrTMsg, hdrBean, true);
                }
            }
        } finally {
            writeEndLogLn("updateLtstCratFlg", hdrBean);
        }
    }

    private void insertDsImptOrdErr(ImptHdrBean hdrBean) {
        writeStartLogLn("insertDsImptOrdErr", hdrBean);

        try {
            List<DsImptOrdErrBean> dsImptOrdErrList = hdrBean.getAllErrorBean();

            for (DsImptOrdErrBean errBean : dsImptOrdErrList) {
                if (this.excludeMessageCode.contains(errBean.dsImptOrdErrMsgId.getValue())) { // S21_NA#10986
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(errBean.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(errBean.dsImptOrdErrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_ERR_SQ));
                S21FastTBLAccessor.insert((DS_IMPT_ORD_ERRTMsg) errBean);
                addInsertErrorMsgList((DS_IMPT_ORD_ERRTMsg) errBean, hdrBean, true);
            }
        } finally {
            writeErrLog(hdrBean);

            writeEndLogLn("insertDsImptOrdErr", hdrBean);
        }
    }

    private boolean checkTMsgDbAccess(EZDTMsg tMsg) {
        return checkTMsgDbAccess(tMsg, true);
    }

    private boolean checkTMsgDbAccess(EZDTMsg tMsg, boolean doThrowErr) {
        if (tMsg != null && !EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            if (doThrowErr) {
                throw new S21AbendException(MSG_ID.NMAM0283E.toString());
            }
            return false;
        }

        return true;
    }

    private boolean addInsertErrorMsgList(EZDTMsg tMsg, IImportBean owner, boolean doThrowErr) {
        if (!checkTMsgDbAccess(tMsg, doThrowErr)) {
            DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableInsertError(owner, tMsg.getTableName());
            owner.getDsImptOrdErrList().add(errBean);
            return false;
        }

        return true;
    }

    private boolean addUpdateErrorMsgList(EZDTMsg tMsg, IImportBean owner, boolean doThrowErr) {
        if (!checkTMsgDbAccess(tMsg, doThrowErr)) {
            DsImptOrdErrBean errBean = DsImptOrdErrBean.createTableUpdateError(owner, tMsg.getTableName());
            owner.getDsImptOrdErrList().add(errBean);
            return false;
        }

        return true;
    }

    private void postErrorMail(ImptHdrBean hdrBean) {
        writeStartLogLn("postErrorMail", hdrBean);

        try {

            // *****************************************************************
            // Derive From Mail Address
            // *****************************************************************
            S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, NWAB221001Constant.CST_MAIL_GRP_ID_FROM);
            List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

            if (!hasValueList(addrFromList)) {
                return;
            }

            S21MailAddress from = addrFromList.get(0);

            // *****************************************************************
            // Derive To Mail Address
            // *****************************************************************
            S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, NWAB221001Constant.PROGRAM_ID);
            groupTo.setMailKey1(hdrBean.getCpoSrcTpCd());
            List<S21MailAddress> addrToList = groupTo.getMailAddress();

            if (!hasValueList(addrToList)) {
                return;
            }

            // *****************************************************************
            // Create Mail Body
            // *****************************************************************
            S21MailTemplate template = new S21MailTemplate(glblCmpyCd, this.mailTemplateId);
            if (template == null) {
                return;
            }
            template.setTemplateParameter("batchId", this.getClass().getSimpleName());
            template.setTemplateParameter("errDate", ZYPDateUtil.getCurrentSystemTime("MM/dd/yyyy HH:mm:ss.SSS"));
            String errMsg = hdrBean.getAllErrMsg();
            template.setTemplateParameter("message", errMsg);

            // *****************************************************************
            // Post mail
            // *****************************************************************
            S21Mail mail;
            for (S21MailAddress addr : addrToList) {
                mail = new S21Mail(this.glblCmpyCd);
                mail.setFromAddress(from);
                mail.setToAddress(addr);
                mail.setMailTemplate(template);
                mail.postMail();
            }

            return;
        } finally {
            writeEndLogLn("postErrorMail", hdrBean);
        }
    }

    private static <T extends EZDMsgArray> boolean hasValidValue(T msgEzArray) {
        return (msgEzArray != null && msgEzArray.getValidCount() > 0);
    }

    private static <T extends List< ? >> boolean hasValueList(T list) {
        return (list != null && list.size() > 0);
    }

    /**
     * Create Array.
     * @param <T>
     * @param param
     * @return
     */
    private static <T> T[] toArray(T... param) {
        return param;
    }

    /**
     * Convert To BigDecimal
     * @param val Object
     * @return BigDecimal
     */
    private static BigDecimal convToBigDecimal(Object val) {
        if (null == val) {
            return null;
        } else if (val instanceof BigDecimal) {
            return (BigDecimal) val;
        } else if (val instanceof EZDBStringItem) {
            return convToBigDecimal(((EZDBStringItem) val).getValue());
        } else if (val instanceof EZDBBigDecimalItem) {
            return ((EZDBBigDecimalItem) val).getValue();
        } else if (val instanceof EZDCStringItem) {
            return convToBigDecimal(((EZDCStringItem) val).getValue());
        } else if (val instanceof EZDTStringItem) {
            return convToBigDecimal(((EZDTStringItem) val).getValue());
        } else if (val instanceof EZDCBigDecimalItem) {
            return ((EZDCBigDecimalItem) val).getValue();
        } else if (val instanceof EZDTBigDecimalItem) {
            return ((EZDTBigDecimalItem) val).getValue();
        } else {
            return new BigDecimal(val.toString());
        }
    }

    /**
     * Convert To String
     * @param val Object
     * @return String
     */
    private String convToString(Object val) {
        return convToString(val, null);
    }

    /**
     * Convert To String
     * @param val Object
     * @param defVal String
     * @return String
     */
    private String convToString(Object val, String defVal) {
        if (null == val) {
            return defVal;
        } else if (val instanceof BigDecimal) {
            return ((BigDecimal) val).toPlainString();
        } else {
            return val.toString();
        }
    }

    /**
     * writeStartLogLn
     * @param methodNm
     */
    private void writeStartLogLn(String methodNm) {
        writeLogLn("[START] %s", methodNm);
    }

    private void writeStartLogLn(String methodNm, Object target) {
        writeLogLn("[START] %s(%s)", methodNm, getTargetKey(target));
    }

    /**
     * writeEndLogLn
     * @param methodNm
     */
    private void writeEndLogLn(String methodNm) {
        writeLogLn("[END] %s\r\n", methodNm);
    }

    private void writeEndLogLn(String methodNm, Object target) {
        writeLogLn("[END] %s(%s)\r\n", methodNm, getTargetKey(target));
    }

    private void writeErrLog(ImptHdrBean hdrBean) {
        if (hdrBean != null) {
            // *****************************************************************
            // Error
            // *****************************************************************
            List<DsImptOrdErrBean> allErrorList = hdrBean.getAllErrorBean();
            for (DsImptOrdErrBean errBean : allErrorList) {
                writeErrLog(errBean);
            }
        }
    }

    private String getTargetKey(Object target) {
        StringBuilder sb = new StringBuilder();

        if (target instanceof ImptHdrBean) {
            sb.append(String.format("DsImptOrdPk:%.0f HasError:%b", ((ImptHdrBean) target).getDsImptOrdPk(), ((ImptHdrBean) target).hasError()));
        } else if (target instanceof DsImptOrdConfigBean) {
            DsImptOrdConfigBean configBean = (DsImptOrdConfigBean) target;
            sb.append(String.format("DsImptOrdPk:%.0f - DsImptOrdConfigPk:%.0f HasError:%b", configBean.imptHdrBean.getDsImptOrdPk(), configBean.dsImptOrdConfigPk.getValue(), configBean.hasError(true)));
        } else if (target instanceof DsImptLineBean) {
            DsImptLineBean lineBean = (DsImptLineBean) target;
            sb.append(String.format("DsImptOrdPk:%.0f - DsImptOrdDtlPk:%.0f HasError:%b", lineBean.imptHdrBean.getDsImptOrdPk(), lineBean.dsImptOrdDtlPk.getValue(), lineBean.hasError(true)));
        } else if (target instanceof DsImptRtnLineBean) {
            DsImptRtnLineBean rtnBean = (DsImptRtnLineBean) target;
            sb.append(String.format("DsImptOrdPk:%.0f - DsImptOrdRtrnDtlPk:%.0f HasError:%b", rtnBean.imptHdrBean.getDsImptOrdPk(), rtnBean.dsImptOrdRtrnDtlPk.getValue(), rtnBean.hasError(true)));
        } else if (target instanceof ExpendMdseBean) {
            ExpendMdseBean mdseBean = (ExpendMdseBean) target;
            sb.append(String.format("DsImptOrdDtlPk:%.0f - MdseCd:%s", mdseBean.getDsImptLineBean().dsImptOrdDtlPk.getValue(), mdseBean.getChildMdseCd()));
        } else if (target == null) {
            sb.append("Target:null");
        } else {
            sb.append(String.format("Target:%s", target.toString()));
        }

        return sb.toString();
    }

    private static void writeErrLog(String msgId) {
        writeLogLn(S21MessageFunc.clspGetMessage(msgId));
    }

    /**
     * writeErrIdLog
     * @param msgId
     */
    @SuppressWarnings("unused")
    private static void writeErrLog(String msgId, String[] list) {
        writeLogLn(S21MessageFunc.clspGetMessage(msgId, list));
    }

    private static void writeErrLog(DsImptOrdErrBean errBean) {
        writeLogLn(errBean.toString());
    }

    /**
     * WriteLogLn
     * @param format
     * @param param
     */
    private static void writeLogLn(String format, Object... param) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("[%s]", NWAB221001Constant.PROGRAM_ID));

        if (param.length > 0) {
            sb.append(String.format(format, param));
        } else {
            sb.append(format);
        }

        S21InfoLogOutput.println(sb.toString());
    }
}
