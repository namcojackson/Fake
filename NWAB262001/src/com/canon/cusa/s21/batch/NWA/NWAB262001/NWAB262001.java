/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB262001;

import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.BATCH_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.BATCH_STR;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.BIZ_APP_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.COLUMN_BIZ_APP_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.COMMA;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.DEF_EML_BR_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.FETCH_SIZE_MAX;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.FROM_KEY1;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.ML_GRP_ID_DEF;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.ML_GRP_ID_LFS;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.ML_GRP_ID_PPS;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.ML_OUT_DT_FMT;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.ML_OUT_DT_NLS_PARAM;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.ML_SEND_CPO_SRC_TP_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.NWAB2620M001;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.NWAB2620M002;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.NWAF0060;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.NWAF0070;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.NWAM0268E;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.NWBM0118E;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.PARENTHESES_CLOSE;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.PDF;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.PDF_SEND_CPO_SRC_TP_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.REPORT_NM_ORD_CONF;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.SHPG_PLN_BIZ_DAY;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.SUCCESS_STS;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.SUPPLIES_ORDER;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.TRX_HDR_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB262001.constant.NWAB262001Constant.TXT_SEND_CPO_SRC_TP_CD;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CPO_CTAC_PSNTMsg;
import business.db.DS_CPO_CTAC_PSNTMsgArray;
import business.db.HIST_IS_WEB_SEND_CONFTMsg;
import business.db.ORD_CONF_RPT_WRKTMsg;
import business.parts.NWZC169001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC169001.NWZC169001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21EmailOutputParameter;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;

/**
 * <pre>
 * Order Confirmation Send Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/18/2016   CUSA            Fujitsu         Create          N/A
 * 2018/04/11   Hitachi         K.Kitachi       Update          QC#11642
 * 2018/08/26   Fujitsu         M.Ohno          Update          QC#27711
 * 2018/08/30   Fujitsu         M.Ishii         Update          QC#27811
 * 2019/02/26   Fujitsu         K.Ishizuka      Update          QC#30290
 * 2019/06/04   Fujitsu         S.Kosaka        Update          QC#50652
 * 2019/07/09   Fujitsu         Mz.Takahashi    Update          QC#51252
 * 2019/10/16   Fujitsu         Y.Kanefusa      Update          S21_NA#54168
 * 2023/10/06   Hitachi         H.Watanabe      Update          QC#61782
 * 2023/11/17   Hitachi         H.Watanabe      Update          QC#62204
 *</pre>
 */
public class NWAB262001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Execute Param */
    S21SsmExecutionParameter excParam = null;

    /** emlBrNum */
    private String emlBrNum = null;

    /** Term code */
    private TERM_CD termCd = null;

    /** total search count */
    private int searchCnt = 0;

    /** success count */
    private int successCnt = 0;

    // START 2018/04/11 K.Kitachi [QC#11642, ADD]
    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** mlSendCpoSrcTpCd */
    private String mlSendCpoSrcTpCd = null;

    /** mlOutDtFmt */
    private String mlOutDtFmt = null;

    /** mlOutDtNlsParam */
    private String mlOutDtNlsParam = null;

    /** shpgPlnBizDay */
    private String shpgPlnBizDay = null;
    // END 2018/04/11 K.Kitachi [QC#11642, ADD]

    // 2018/08/26 QC#27711 add start
    /** pdfSendCpoSrcTpCd */
    private String pdfSendCpoSrcTpCd = null;

    /** txtSendCpoSrcTpCd */
    private String txtSendCpoSrcTpCd = null;
    // 2018/08/26 QC#27711 add end

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB262001().executeBatch(NWAB262001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());
        // START 2018/04/11 K.Kitachi [QC#11642, ADD]
        this.ssmBatchClient = S21SsmBatchClient.getClient(getClass());
        // END 2018/04/11 K.Kitachi [QC#11642, ADD]
        this.termCd = TERM_CD.NORMAL_END;

        this.glblCmpyCd = getGlobalCompanyCode();
        // Global company code
        if (!hasValue(this.glblCmpyCd)) {
            // [@] is mandatory.
            throw new S21AbendException("ZZZM9025E", new String[] {"Global Company Code" });
        }

        // Sales date
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BATCH_ID);

        this.emlBrNum = ZYPCodeDataUtil.getVarCharConstValue(DEF_EML_BR_NUM, glblCmpyCd);
        // START 2018/04/11 K.Kitachi [QC#11642, ADD]
        this.mlSendCpoSrcTpCd = ZYPCodeDataUtil.getVarCharConstValue(ML_SEND_CPO_SRC_TP_CD, this.glblCmpyCd);
        this.mlOutDtFmt = ZYPCodeDataUtil.getVarCharConstValue(ML_OUT_DT_FMT, this.glblCmpyCd);
        this.mlOutDtNlsParam = ZYPCodeDataUtil.getVarCharConstValue(ML_OUT_DT_NLS_PARAM, this.glblCmpyCd);
        this.shpgPlnBizDay = ZYPCodeDataUtil.getVarCharConstValue(SHPG_PLN_BIZ_DAY, this.glblCmpyCd);
        // END 2018/04/11 K.Kitachi [QC#11642, ADD]
        // 2018/08/26 QC#27711 add start
        this.pdfSendCpoSrcTpCd = ZYPCodeDataUtil.getVarCharConstValue(PDF_SEND_CPO_SRC_TP_CD, this.glblCmpyCd);
        this.txtSendCpoSrcTpCd = ZYPCodeDataUtil.getVarCharConstValue(TXT_SEND_CPO_SRC_TP_CD, this.glblCmpyCd);
        // 2018/08/26 QC#27711 add end

        this.excParam = new S21SsmExecutionParameter();
        this.excParam.setFetchSize(FETCH_SIZE_MAX);
        this.excParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        this.excParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        this.excParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, successCnt, searchCnt - successCnt, searchCnt);
    }

    /**
     * doProcess
     */
    private void doProcess() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        S21EIPPrintingService service = new S21EIPPrintingService();

        try {
            deletePrintWorkTable();
            commit();

            ps = ssmLLClient.createPreparedStatement("getTargetOrderList", getParam(), excParam);
            rs = ps.executeQuery();
            while (rs.next()) {
                searchCnt++;
                OrdConfBean bean = createOrdConfBean(rs);
                DS_CPO_CTAC_PSNTMsgArray array = bean.getDsCpoCtacPsnTMsgArray();
                if (array == null || array.getValidCount() < 1) {
                    rollback();
                    continue;
                }

                if (!callSplyQuoteReportCratApi(bean)) {
                    rollback();
                    continue;
                }

                // 2023/10/06 QC#61782 Mod Start
//                executeEipProcessForSendMail(bean, service);
                List<String> toAddressList = getToAddressList(array);
                for (String toAddress : toAddressList) {
                    executeEipProcessForSendMail(bean, service, toAddress);
                }
                // 2023/10/06 QC#61782 Mod End

                insertHistIsWebSendConf(bean);

                commit();
                successCnt++;
            }

            if (successCnt > 0) {
                service.activateAsyncReportJob();
                commit();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    @SuppressWarnings("unchecked")
    private void deletePrintWorkTable() {
        // 2019/02/26 S21_NA#30290 Mod Start
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("bizAppId", BIZ_APP_ID);
        param.put("otptOpCd", BATCH_STR);
        param.put("trxHdrNum", TRX_HDR_NUM);
        // 2019/06/04 QC#50652 Mod Start
        //param.put("stsFail", FAIL_STS);
        param.put("prtRqstSts", SUCCESS_STS);
        // 2019/06/04 QC#50652 Mod End
        // QC#54168 2019/10/16 Mod Start
        param.put("columnBizAppId", COLUMN_BIZ_APP_ID);
        // QC#54168 2019/10/16 Mod End

        List<ORD_CONF_RPT_WRKTMsg> rsltList = (List<ORD_CONF_RPT_WRKTMsg>) this.ssmBatchClient.queryObjectList("getWrkDelTargetList", param);
        if (rsltList != null && 0 < rsltList.size()) {
            List<ORD_CONF_RPT_WRKTMsg> ordConfRptWrlList4Del = new ArrayList<ORD_CONF_RPT_WRKTMsg>();
            for (ORD_CONF_RPT_WRKTMsg tmsg : rsltList) {
                tmsg.glblCmpyCd.setValue(this.glblCmpyCd);
                tmsg = (ORD_CONF_RPT_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);
                if (tmsg != null) {
                    ordConfRptWrlList4Del.add(tmsg);
                }
            }
            if (!ordConfRptWrlList4Del.isEmpty()) {
                S21ApiTBLAccessor.remove(ordConfRptWrlList4Del.toArray(new ORD_CONF_RPT_WRKTMsg[0]));
            }
        }
        // ORD_CONF_RPT_WRKTMsg inTMsg = new ORD_CONF_RPT_WRKTMsg();
        // inTMsg.setSQLID("001");
        // inTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        // inTMsg.setConditionValue("bizAppId01", BIZ_APP_ID);
        // inTMsg.setConditionValue("otptOpCd01", BATCH_STR);
        // ORD_CONF_RPT_WRKTMsgArray outTMsgArray = (ORD_CONF_RPT_WRKTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inTMsg);

        // if (outTMsgArray != null && outTMsgArray.getValidCount() > 0) {
            // for (int i = 0; i < outTMsgArray.getValidCount(); i++) {
                // ORD_CONF_RPT_WRKTMsg outTMsg = (ORD_CONF_RPT_WRKTMsg) outTMsgArray.get(i);
                // if (S21FastTBLAccessor.RTNCD_NORMAL.equals(outTMsg.getReturnCode())) {
                //     S21ApiTBLAccessor.remove(outTMsg);
                //}
            // }
        // }
        // 2019/02/26 S21_NA#30290 Mod End
    }

    private OrdConfBean createOrdConfBean(ResultSet rs) throws SQLException {
        OrdConfBean bean = new OrdConfBean();
        bean.setCpoOrdNum(rs.getString("CPO_ORD_NUM"));
        bean.setSellToCustCd(rs.getString("SELL_TO_CUST_CD"));
        bean.setLineBizTpCd(rs.getString("LINE_BIZ_TP_CD"));
        bean.setDsAcctCustNm(rs.getString("DS_ACCT_NM"));
        // START 2018/04/11 K.Kitachi [QC#11642, ADD]
        bean.setCpoSrcTpCd(rs.getString("CPO_SRC_TP_CD"));
        // END 2018/04/11 K.Kitachi [QC#11642, ADD]

        // 2019/07/26 QC#51252 Add Start
        bean.setDsOrdCatgCd(rs.getString("DS_ORD_CATG_CD"));
        bean.setDsOrdTpCd(rs.getString("DS_ORD_TP_CD"));
        // 2019/07/26 QC#51252 Add End

        bean.setDsCpoCtacPsnTMsgArray(getDsCpoCtacPsn(glblCmpyCd, bean.getCpoOrdNum()));

        return bean;
    }

    /**
     * Call Supply Quote Report Creation API
     * @param bean OrderInfoBean
     * @return No Error : true
     */
    public boolean callSplyQuoteReportCratApi(OrdConfBean bean) {

        NWZC169001PMsg pMsg = new NWZC169001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.splyQuoteRptTpCd, NWZC169001.ORDER_CONF_CD);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.bizAppId, BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.otptOpCd, BATCH_STR);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, bean.getLineBizTpCd());
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, bean.getCpoOrdNum());
        ZYPEZDItemValueSetter.setValue(pMsg.delFlg, ZYPConstant.FLG_OFF_N);

        new NWZC169001().execute(pMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                setErrorInfo(msg.getXxMsgid());
                return false;
            }
        }
        return true;
    }

    private void setErrorInfo(String msgId) {
        setErrorInfo(msgId, null);
    }

    private void setErrorInfo(String msgId, String[] params) {
        S21InfoLogOutput.println(msgId, params);
    }

    private void insertHistIsWebSendConf(OrdConfBean bean) {
        HIST_IS_WEB_SEND_CONFTMsg data = new HIST_IS_WEB_SEND_CONFTMsg();
        ZYPEZDItemValueSetter.setValue(data.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(data.histIsWebSendConfPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.HIST_IS_WEB_SEND_CONF_SQ));
        ZYPEZDItemValueSetter.setValue(data.cpoOrdNum, bean.getCpoOrdNum());
        S21FastTBLAccessor.insert(data);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(data.getReturnCode())) {
            throw new S21AbendException(NWBM0118E, new String[] {"HIST_IS_WEB_SEND_CONF" });
        }
    }

    /**
     * Execute EIP Process For Send Mail
     * @param bean OrderInfoBean
     * @param service S21EIPPrintingService
     * @param toAddress String
     */
    // 2023/10/06 QC#61782 Mod Start
//    private void executeEipProcessForSendMail(OrdConfBean bean, S21EIPPrintingService service) {
    private void executeEipProcessForSendMail(OrdConfBean bean, S21EIPPrintingService service, String toAddress) {
    // 2023/10/06 QC#61782 Mod End

        // From Address
        // 2019/07/26 QC#51252 Mod Start
        String fromAddress = getFromAddress(bean);
        //String fromAddress = getFromAddress();
        // 2019/07/26 QC#51252 Mod End

        if (!ZYPCommonFunc.hasValue(fromAddress)) {
            throw new S21AbendException(NWAM0268E);
        }

        // Report ID
        String reportId = getReportId(bean.getLineBizTpCd());

        // Mail Template
        // START 2018/04/11 K.Kitachi [QC#11642, MOD]
//        S21MailTemplate template = getTemplate(bean);
        S21MailTemplate template = null;
        // 2018/08/26 QC#27711 mod start
        List<String> pdfList = Arrays.asList(this.pdfSendCpoSrcTpCd.split(COMMA));
        List<String> txtList = Arrays.asList(this.txtSendCpoSrcTpCd.split(COMMA));

        if (pdfList.contains(bean.getCpoSrcTpCd())) {
//        if (bean.getCpoSrcTpCd().equals(CPO_SRC_TP.IS_WEB)) {
            template = getTemplate(bean);
//        } else {
        } else if (txtList.contains(bean.getCpoSrcTpCd())) {
            template = getTemplateNotIsWeb(bean);
        }
        // 2018/08/26 QC#27711 mod end
        // END 2018/04/11 K.Kitachi [QC#11642, MOD]
        if (template == null) {
            // 2018/08/30 QC#27811 Mod Start
//            throw new S21AbendException(NWAM0268E);
            infoLogOut(NWAM0268E, bean.getCpoOrdNum());
            // 2018/08/30 QC#27811 Mod End
        }

        S21ReportRequestBean request = new S21ReportRequestBean(reportId);
        request.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
        request.setRptArcFlg(true);

        S21InputParameter inputParam = request.getInputParamBeanInstance();
        inputParam.addReportParameter("GLBL_CMPY_CD", this.glblCmpyCd);
        inputParam.addReportParameter("INTL_LANG_VAL_COL_NM", inputParam.getSystemDefaultLanguage());
        inputParam.addReportParameter("BIZ_APP_ID", BIZ_APP_ID);
        inputParam.addReportParameter("OTPT_OP_CD", BATCH_STR);
        inputParam.addReportParameter("LINE_BIZ_TP_CD", bean.getLineBizTpCd());
        inputParam.addReportParameter("SPLY_QUOTE_RPT_TP_CD", NWZC169001.ORDER_CONF_CD);
        inputParam.addReportParameter("TRX_HDR_NUM", bean.getCpoOrdNum());

        request.setInputParamBean(inputParam);

        S21EmailOutputParameter outputParam = request.getEmailOutParamInstance();
        outputParam.setBranchNo(emlBrNum);
        outputParam.setAttachementFlag(true);

        // 2023/10/06 QC#61782 Del Start
//        DS_CPO_CTAC_PSNTMsgArray array = bean.getDsCpoCtacPsnTMsgArray();
//        int ctacPsnCnt = array.getValidCount();
        // 2023/10/06 QC#61782 Del End
        // START 2018/04/11 K.Kitachi [QC#11642, MOD]
//        for (int i = 0; i < ctacPsnCnt; i++) {
//            outputParam.addToAddress(array.no(i).ctacPsnEmlAddr.getValue());
//        }
        // 2023/10/06 QC#61782 Del Start
//        List<String> emlAddrList = new ArrayList<String>();
//        for (int i = 0; i < ctacPsnCnt; i++) {
//            String emlAddr = array.no(i).ctacPsnEmlAddr.getValue();
//            if (!hasValue(emlAddr)) {
//                continue;
//            }
//            if (emlAddrList.contains(emlAddr)) {
//                continue;
//            }
//            emlAddrList.add(emlAddr);
//        }
//        for (String emlAddr : emlAddrList) {
//            outputParam.addToAddress(emlAddr);
//        }
        // 2023/10/06 QC#61782 Del End
        // END 2018/04/11 K.Kitachi [QC#11642, MOD]
        // 2018/08/31 QC#27811 Mod Start
//        outputParam.setSubject(template.getSubject());
//        outputParam.setBodyText(template.getBody());
//        outputParam.setAttachFileName(getReportFileName(bean.getCpoOrdNum()));
//        outputParam.setSenderAddress(fromAddress);
//        request.setEmailOutParamBean(outputParam);
//
//        // Create Report
//        service.createReportByAsync(request);

        if (template != null) {
            outputParam.setSubject(template.getSubject());
            outputParam.setBodyText(template.getBody());
            outputParam.setAttachFileName(getReportFileName(bean.getCpoOrdNum()));
            outputParam.setSenderAddress(fromAddress);
            // 2023/10/06 QC#61782 Add Start
            outputParam.addToAddress(toAddress);
            // 2023/10/06 QC#61782 Add End
            request.setEmailOutParamBean(outputParam);

            // Create Report
            service.createReportByAsync(request);
        }
        // 2018/08/31 QC#27811 Mod End
    }

    /**
     * Get From Address
     * @return From Address
     */
    private String getFromAddress(OrdConfBean bean) {
        //2019/07/09  QC#51252 Mod Start
        String mlGrpId = "";

        if (LINE_BIZ_TP.LFS.equals(bean.getLineBizTpCd())){
            mlGrpId = ML_GRP_ID_LFS;
        } else {
            mlGrpId = ML_GRP_ID_PPS;
        }

        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, mlGrpId);
        groupFrom.setMailKey1(FROM_KEY1);
        groupFrom.setMailKey2(bean.getDsOrdCatgCd());
        groupFrom.setMailKey3(bean.getDsOrdTpCd());

        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if (addrFromList != null && !addrFromList.isEmpty()) {
            return addrFromList.get(0).getAddress();
        }

        groupFrom = new S21MailGroup(this.glblCmpyCd, ML_GRP_ID_DEF);
        groupFrom.setMailKey1(FROM_KEY1);
        addrFromList = groupFrom.getMailAddress();

        if (addrFromList != null && !addrFromList.isEmpty()) {
            return addrFromList.get(0).getAddress();
        }

        //2019/07/09  QC#51252 Mod Start
        //S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, FROM0002);
        //groupFrom.setMailKey1("NW");

        //List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        //if (addrFromList != null && !addrFromList.isEmpty()) {
        //    return addrFromList.get(0).getAddress();
        //}
        //2019/07/09  QC#51252 Mod End

        return null;
    }

    /**
     * Get Report ID
     * @param bizMsg NWAL1770CMsg
     * @return Report ID
     */
    public static String getReportId(String lineBizTpCd) {

        String reportId = null;
        if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
            reportId = NWAF0060;
        } else {
            reportId = NWAF0070;
        }
        return reportId;
    }

    /**
     * Get Mail Template
     * @param bean OrderInfoBean
     * @return S21MailTemplate
     */
    private S21MailTemplate getTemplate(OrdConfBean bean) {

        // Create template
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, NWAB2620M001);
        if (template == null) {
            return null;
        }

        // Set Parameter
        template.setTemplateParameter("cpoOrdNum", bean.getCpoOrdNum());
        template.setTemplateParameter("dsAcctNum", bean.getSellToCustCd());
        template.setTemplateParameter("dsAcctNm", bean.getDsAcctCustNm());

        return template;
    }

    // START 2018/04/11 K.Kitachi [QC#11642, ADD]
    /**
     * Get Mail Template Not IS Web
     * @param bean OrderInfoBean
     * @return S21MailTemplate
     */
    private S21MailTemplate getTemplateNotIsWeb(OrdConfBean bean) {

        // Create template
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, NWAB2620M002);
        if (template == null) {
            return null;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", bean.getCpoOrdNum());
        // 2018/08/26 QC#27711 mod start
//        param.put("ctacTpCd", CTAC_TP.DELIVERY_INSTALL);
        param.put("ctacTpCd", CTAC_TP.ORDER_CONTACT);
        // 2018/08/26 QC#27711 mod start
        param.put("s21DtFmt", ZYPDateUtil.TYPE_YYYYMMDD);
        param.put("mlOutDtFmt", this.mlOutDtFmt);
        param.put("mlOutDtNlsParam", this.mlOutDtNlsParam);

        List<Map<String, Object>> rsltMapList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getMailSendParam", param);
        if (rsltMapList.size() == 0) {
            return null;
        }

        // Set Parameter
        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> rsltMap : rsltMapList) {
            sb.append("Model/Serial Number: ");
            sb.append((String) rsltMap.get("MDL_NM"));
            sb.append(" | ");
            sb.append((String) rsltMap.get("SER_NUM"));
            sb.append("\r\n");
            sb.append("Order Date: ");
            sb.append((String) rsltMap.get("CPO_ORD_DT"));
            sb.append("\r\n");
            sb.append("Supply Order Number: ");
            sb.append((String) rsltMap.get("CPO_ORD_NUM"));
            sb.append("\r\n");
            sb.append("Attention Line: ");
            if (hasValue((String) rsltMap.get("SELL_TO_FIRST_REF_CMNT_TXT"))) {
                sb.append((String) rsltMap.get("SELL_TO_FIRST_REF_CMNT_TXT"));
                sb.append("-");
            }
            if (hasValue((String) rsltMap.get("CTAC_PSN_NM"))) {
                sb.append((String) rsltMap.get("CTAC_PSN_NM"));
                sb.append("-");
            }
            sb.append((String) rsltMap.get("SER_NUM"));
            sb.append("\r\n");
            sb.append("Item Ordered: ");
            sb.append((String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
            sb.append("\r\n");
            sb.append("Ordered Quantity: ");
            sb.append((BigDecimal) rsltMap.get("ORD_QTY"));
            sb.append("\r\n");
            sb.append("\r\n");
        }
        template.setTemplateParameter("ordDtlInfo", sb.toString());
        template.setTemplateParameter("shipToInfo", (String) rsltMapList.get(0).get("SHIP_TO_INFO"));
        template.setTemplateParameter("shpgPlnBizDay", this.shpgPlnBizDay);

        return template;
    }
    // END 2018/04/11 K.Kitachi [QC#11642, ADD]

    /**
     * Get Report File Name
     * @param cpoOrdNum String
     * @return Attach File Name
     */
    public String getReportFileName(String cpoOrdNum) {
        return REPORT_NM_ORD_CONF + cpoOrdNum + PARENTHESES_CLOSE + PDF;
    }

    /**
     * getParam
     * @return sql parameter
     */
    private Map<String, Object> getParam() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        // START 2018/04/11 K.Kitachi [QC#11642, MOD]
//        paramMap.put("isWebCpoSrcTpCd", CPO_SRC_TP.IS_WEB);
        String[] cpoSrcTpCdList = this.mlSendCpoSrcTpCd.split(COMMA);
        paramMap.put("cpoSrcTpCdList", cpoSrcTpCdList);
        // END 2018/04/11 K.Kitachi [QC#11642, MOD]
        paramMap.put("flgY", ZYPConstant.FLG_ON_Y);
        // 2018/08/30 QC#27811 Add Start
        paramMap.put("ordCatgCtxTpCd", SUPPLIES_ORDER);
        // 2018/08/30 QC#27811 Add End

        return paramMap;
    }

    /**
     * getDsCpoCtacPsn
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return DS_CPO_CTAC_PSNTMsgArray
     */
    private DS_CPO_CTAC_PSNTMsgArray getDsCpoCtacPsn(String glblCmpyCd, String cpoOrdNum) {
        DS_CPO_CTAC_PSNTMsg inTMsg = new DS_CPO_CTAC_PSNTMsg();
        inTMsg.setSQLID("010");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        return (DS_CPO_CTAC_PSNTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
    }

    // 2023/10/06 QC#61782 Add Start
    /**
     * getToAddressList
     * @param array DS_CPO_CTAC_PSNTMsgArray
     * @return List<String>
     */
    private List<String> getToAddressList(DS_CPO_CTAC_PSNTMsgArray array) {
        List<String> toAddressList = new ArrayList<String>();
        for (int i = 0; i < array.getValidCount(); i++) {
            String emlAddr = array.no(i).ctacPsnEmlAddr.getValue();
            if (!hasValue(emlAddr)) {
                continue;
            }
            // 2023/11/17 QC#62204 Add Start
            emlAddr = emlAddr.toLowerCase();
            // 2023/11/17 QC#62204 Add End
            if (toAddressList.contains(emlAddr)) {
                continue;
            }
            toAddressList.add(emlAddr);
        }
        return toAddressList;
    }
    // 2023/10/06 QC#61782 Add End
}
