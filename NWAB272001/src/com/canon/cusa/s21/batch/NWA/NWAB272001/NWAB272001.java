/*
 * <Pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB272001;

import static com.canon.cusa.s21.batch.NWA.NWAB272001.constant.NWAB272001Constant.APP_BATCH;
import static com.canon.cusa.s21.batch.NWA.NWAB272001.constant.NWAB272001Constant.EIP_REQ_ERROR;
import static com.canon.cusa.s21.batch.NWA.NWAB272001.constant.NWAB272001Constant.EIP_REQ_EXCEPTION;
import static com.canon.cusa.s21.batch.NWA.NWAB272001.constant.NWAB272001Constant.ERR_PRM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB272001.constant.NWAB272001Constant.FROM_ADDR_ERROR;
import static com.canon.cusa.s21.batch.NWA.NWAB272001.constant.NWAB272001Constant.FROM_GRP_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB272001.constant.NWAB272001Constant.PDF;
import static com.canon.cusa.s21.batch.NWA.NWAB272001.constant.NWAB272001Constant.SHIPPING_ORDER;
import static com.canon.cusa.s21.batch.NWA.NWAB272001.constant.NWAB272001Constant.SHIPPING_ORDER_NUMBER;
import static com.canon.cusa.s21.batch.NWA.NWAB272001.constant.NWAB272001Constant.SHIPPING_ORDER_PRINT_API;
import static com.canon.cusa.s21.batch.NWA.NWAB272001.constant.NWAB272001Constant.TO_ADDR_ERROR;
import static com.canon.cusa.s21.batch.NWA.NWAB272001.constant.NWAB272001Constant.UNDER_BAR;
import static com.canon.cusa.s21.batch.NWA.NWAB272001.constant.NWAB272001Constant.ZZZM9025E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.SHPG_ORDTMsg;
import business.db.SO_TO_VND_NTFY_CTRLTMsg;

import com.canon.cusa.s21.batch.NWA.NWAB272001.constant.NWAB272001Constant;
import com.canon.cusa.s21.common.NLX.NLXC019001.NLXSOReport;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21EmailOutputParameter;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;
/**
 * <pre>
 * Shipping Order Notification to Vendor Send
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/09/07   Hitachi         N.Takatsu       Create          QC#60256
 * </pre>
 */
/**
 * @author Q17710
 *
 */
public class NWAB272001 extends S21BatchMain {

    /** Normal Count */
    private int normalCount = 0;

    /** Total Count */
    private int totalCount = 0;

    /** Terminate Cord */
    private TERM_CD termCd;

    /** Global Company Code */
    private String glblCmpyCd;


    /** PDF Convert Date (YYYMMDDHHmmSSsss) */
    private String sendTime;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient;

    /** S21EIPPrintingService */
    S21EIPPrintingService service = new S21EIPPrintingService();

    /**
     * It is the main method that is called from the batch processing.
     * @param args Argument
     */
    public static void main(String[] args) {
        new NWAB272001().executeBatch(NWAB272001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(ZZZM9025E, new String[] {ERR_PRM_GLBL_CMPY_CD });
        }

        this.termCd = TERM_CD.NORMAL_END;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getCtrlGrp();
            rs = ps.executeQuery();

            while (rs.next()) {
                boolean execFlg = true;
                this.totalCount++;
                String soNum = rs.getString("SO_NUM");
                SO_TO_VND_NTFY_CTRLTMsg ctrlTMsg = getSoToVndNtfyCtrl(soNum);
                if (ctrlTMsg == null) {
                    continue;
                }
                String procStsCd = PROC_STS.COMPLEATED;
                // Create PDF & Mail Send
                execFlg = createPdfAndSend(rs, ctrlTMsg);

                // Update CtrlTbl
                if (execFlg == true) {
                    procStsCd = PROC_STS.COMPLEATED;
                    this.normalCount++;
                } else {
                    procStsCd = PROC_STS.ERROR;
                    this.termCd = TERM_CD.WARNING_END;
                    rollback();
                }
                updateCtrl(rs, procStsCd, ctrlTMsg);
                commit();
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        if (this.normalCount > 0) {
            service.activateAsyncReportJob();
            commit();
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalCount, this.totalCount - this.normalCount, this.totalCount);
    }

    /**
     * @return
     * @throws SQLException
     */
    private PreparedStatement getCtrlGrp() throws SQLException {
        Map<String, Object> mlPrmMap = new HashMap<String, Object>();
        mlPrmMap.put("glblCmpyCd", this.glblCmpyCd);
        mlPrmMap.put("inNotCompleted", PROC_STS.IN_COMPLETED);
        mlPrmMap.put("error", PROC_STS.ERROR);
        return ssmLLClient.createPreparedStatement("getMlSendPrm", mlPrmMap);
    }


    /**
     * @param rs
     * @return
     * @throws SQLException 
     */
    @SuppressWarnings("unchecked")
    private boolean createPdfAndSend(ResultSet rs, SO_TO_VND_NTFY_CTRLTMsg ctrlTMsg) throws SQLException {
        String soNum = ctrlTMsg.soNum.getValue();
        this.sendTime = ZYPDateUtil.getCurrentSystemTime(NWAB272001Constant.FORMAT_TIMESTAMP);
        BigDecimal reportPk = null;

        NLXSOReport report = new NLXSOReport();
        List<String> xxMsgIdList = new ArrayList<String>();
        SHPG_ORDTMsg soTmsg = new SHPG_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(soTmsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(soTmsg.glblCmpyCd, glblCmpyCd);
        soTmsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKeyForUpdate(soTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(soTmsg.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(ctrlTMsg.vldMsgTxt, S21MessageFunc.clspGetMessage(NWAB272001Constant.NWAM8466E, new String[] {SHIPPING_ORDER, SHIPPING_ORDER_NUMBER + soNum }));
            return false;
        }
        report = new NLXSOReport();
        reportPk = report.setSoRptWrk(glblCmpyCd, APP_BATCH, this.sendTime, soNum, xxMsgIdList);
        if (xxMsgIdList.size() != 0) {
            ZYPEZDItemValueSetter.setValue(ctrlTMsg.vldMsgTxt, S21MessageFunc.clspGetMessage(NWAB272001Constant.NWAM8469E, new String[] {SHIPPING_ORDER_PRINT_API + soNum }));
            return false;
        }
        ZYPEZDItemValueSetter.setValue(soTmsg.soPrtFlg, ZYPConstant.FLG_ON_Y);
        EZDTBLAccessor.update(soTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(soTmsg.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(ctrlTMsg.vldMsgTxt, S21MessageFunc.clspGetMessage(NWAB272001Constant.NWAM0741E, new String[] {SHIPPING_ORDER_NUMBER + soNum, soTmsg.getTableName() }));
            return false;
        }
        // Create Pdf
        S21ReportRequestBean requestBean = new S21ReportRequestBean(NWAB272001Constant.REPORT_ID);
        requestBean.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
        requestBean.setRptArcFlg(true);
        requestBean.setRptJobId(APP_BATCH);
        // Report Title
        String reportTitle = ZYPCommonFunc.concatString(NWAB272001Constant.REPORT_TITLE, NWAB272001Constant.REPORT_TITLE_SEPARATOR, soNum);
        reportTitle = ZYPCommonFunc.concatString(reportTitle, NWAB272001Constant.REPORT_TITLE_SEPARATOR, reportPk.toPlainString());
        requestBean.setRptTtlNm(reportTitle);

        S21InputParameter param = requestBean.getInputParamBeanInstance();
        param.addReportParameter(NWAB272001Constant.INTL_LANG_VAL_COL_NM, param.getSystemDefaultLanguage());
        param.addReportParameter(NWAB272001Constant.GLBL_CMPY_CD, glblCmpyCd);
        param.addReportParameter(NWAB272001Constant.SO_NUM, soNum);
        param.addReportParameter(NWAB272001Constant.SHPG_ORD_RPT_PRINT_RQST_SQ, reportPk);

        // Set Template
        String mlTmplId = rs.getString("ML_TMPL_ID");
        S21MailTemplate s21MailTemplate = new S21MailTemplate(this.glblCmpyCd, mlTmplId);
        s21MailTemplate.setTemplateParameter("soNum", soNum);

        S21EmailOutputParameter mailOutParam = requestBean.getEmailOutParamInstance();
        mailOutParam.setBranchNo("01");
        mailOutParam.setAttachementFlag(true);
        String prmAttachFileName = soNum + UNDER_BAR + this.sendTime + PDF;
        mailOutParam.setAttachFileName(prmAttachFileName);
        mailOutParam.setSubject(s21MailTemplate.getSubject());
        mailOutParam.setBodyText(s21MailTemplate.getBody());
        // From Address
        String fromAddress = getFromAddress();
        if (ZYPCommonFunc.hasValue(fromAddress)) {
            mailOutParam.setSenderAddress(fromAddress);
        } else {
            ZYPEZDItemValueSetter.setValue(ctrlTMsg.vldMsgTxt, S21MessageFunc.clspGetMessage(NWAB272001Constant.ZZZM9025E, new String[] {FROM_ADDR_ERROR + FROM_GRP_ID }));
            S21InfoLogOutput.println(FROM_ADDR_ERROR + FROM_GRP_ID);
            return false;
        }
        // Set To Mail Address
        List<String> mlGrpList = null;
        Map<String, Object> grpPrm = new HashMap<String, Object>();
        grpPrm.put("glblCmpyCd", this.glblCmpyCd);
        String mlGrpId = rs.getString("ML_GRP_ID");
        grpPrm.put("mlGrpId", mlGrpId);
        mlGrpList = (List<String>) ssmBatchClient.queryObjectList("getNtfyMailAddress", grpPrm);
        // Set To Mail Address
        List<String> toMailList = getMailList(mlGrpList, this.glblCmpyCd);
        if (toMailList == null || toMailList.size() == 0) {
            ZYPEZDItemValueSetter.setValue(ctrlTMsg.vldMsgTxt, S21MessageFunc.clspGetMessage(NWAB272001Constant.ZZZM9025E, new String[] {TO_ADDR_ERROR + mlGrpId }));
            S21InfoLogOutput.println(TO_ADDR_ERROR + mlGrpId);
            return false;
        }
        for (String toMail : toMailList) {
            mailOutParam.addToAddress(toMail);
        }

        requestBean.setEmailOutParamBean(mailOutParam);
        requestBean.setInputParamBean(param);
        long rptRqstPk;
        try {
            rptRqstPk = service.createReportByAsync(requestBean);
            if (rptRqstPk != 0) {
                return true;
            } else {
                ZYPEZDItemValueSetter.setValue(ctrlTMsg.vldMsgTxt, S21MessageFunc.clspGetMessage(NWAB272001Constant.NWAM8467E, new String[] {EIP_REQ_ERROR + soNum }));
                S21InfoLogOutput.println(EIP_REQ_ERROR + soNum);
                return false;
            }
        } catch (Exception e) {
            ZYPEZDItemValueSetter.setValue(ctrlTMsg.vldMsgTxt, S21MessageFunc.clspGetMessage(NWAB272001Constant.NWAM8467E, new String[] {EIP_REQ_EXCEPTION + soNum }));
            S21InfoLogOutput.println(EIP_REQ_EXCEPTION + soNum);
            return false;
        }
    }

    private String getFromAddress() {
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, FROM_GRP_ID);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if (addrFromList != null && !addrFromList.isEmpty()) {
            return addrFromList.get(0).getAddress();
        }

        return null;
    }

    /**
     * <dd>The method explanation: The business peculiarity processing
     * is executed. String mailAddress
     * @param mailAddress String
     * @param glblCmpyCd String
     * @return List
     */
    private List<String> getMailList(List<String> addrList, String glblCmpyCd) {

        List<String> mailAddrList = new ArrayList<String>();

        for (String addrStr : addrList) {
            if (!ZYPCommonFunc.hasValue(addrStr)) {
                continue;
            } else if (addrStr.contains("@")) {
                mailAddrList.add(addrStr);
            } else {
                return null;
            }
        }
        return mailAddrList;
    }

    /**
     * @param rs
     * @param procStsCd
     * @throws SQLException 
     */
    private void updateCtrl(ResultSet rs, String procStsCd, SO_TO_VND_NTFY_CTRLTMsg ctrlTMsg) throws SQLException {
        ZYPEZDItemValueSetter.setValue(ctrlTMsg.ntfyProcStsCd, procStsCd);
        ZYPEZDItemValueSetter.setValue(ctrlTMsg.ntfySendTs, this.sendTime);
        S21FastTBLAccessor.update(ctrlTMsg);
    }

    private SO_TO_VND_NTFY_CTRLTMsg getSoToVndNtfyCtrl(String soNum) {
        SO_TO_VND_NTFY_CTRLTMsg ctrlTMsg = new SO_TO_VND_NTFY_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(ctrlTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ctrlTMsg.soNum, soNum);
        ctrlTMsg = (SO_TO_VND_NTFY_CTRLTMsg) S21FastTBLAccessor.findByKeyForUpdate(ctrlTMsg);
        if (ctrlTMsg != null) {
            ctrlTMsg.vldMsgTxt.clear();
        }
        return ctrlTMsg;
    }
}
