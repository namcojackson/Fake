/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWW.NWWB001001;

//import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.CHK_EMAIL_PATTERN;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.BATCH_ID;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.BATCH_NM;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.MAIL_TEMPLATE_ID_FOR_BIZ_ERROR;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.MAIL_TEMPLATE_KEY_BATCH_ID;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.MAIL_TEMPLATE_KEY_BATCH_NM;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.MAIL_TEMPLATE_KEY_BATCH_PROC_LOG_ID;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.MAIL_TEMPLATE_KEY_MSG_INFO;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.NEW_LINE;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.CONT_TP_HTML;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.CRLF;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.DATE_PATTERN;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.EQUAL;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.HTML_TBL_REPL_NM;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.JOB_COUNT_NM;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.MAX_DT;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.ML_BODY_REPL_NM;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.NTYF_HTML_TMPL_NM;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.NWWM0011E;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.NWWM0015E;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.NWWM0020E;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.NWWM0021E;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.PARAM_KEY_GROUP;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.PARAM_PATTERN;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.QUOTE;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.VAR_CHAR_CONST;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.ZZZM9026E;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.NTFY_CTR_HTML_ERR_MSG;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.DEFAULT_NTFY_CTR_HTML_ERR_MSG;
import static com.canon.cusa.s21.batch.NWW.NWWB001001.constant.NWWB001001Constant.MAX_ML_BODY_LEN;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.dbcommon.EZDDBRetryRequestException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.GLBL_CMPYTMsg;
import business.db.NTFY_DIST_QLFYTMsg;
import business.db.NTFY_RUN_JOBTMsg;
import business.db.NTFY_SEND_EML_INFOTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.NTFY_ATT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.NTFY_DIST_QLFY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.NTFY_FREQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.NTFY_INTVL_UOM_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailAttachment;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.common.NMX.NMXC106001.NMXC106001CommonCheckUtil;

/**
 *<pre>
 * Notification Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/20   Fujitsu         M.Ohno          Create
 * 2016/10/06   Fujitsu         Mz.Takahashi    Update          QC#14431
 * 2016/11/24   Fujitsu         M.Ohno          Create          S21_NA#16206
 * 2019/09/20   Fujitsu         M.Ohno          Update          S21_NA#53601
 * 2019/11/29   Fujitsu         S.Iidaka        Update          QC#54226
 * 2019/12/03   Fujitsu         S.Iidaka        Update          QC#53020
 * 2019/12/26   Fujitsu         Mz.Takahashi    Update          QC#55211
 * 2020/02/05   Fujitsu         Y.Kanefusa      Update          QC#55211-1
 * 2022/09/19   CITS            R.Azucena       Update          QC#60563
 *</pre>
 */
public class NWWB001001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** process date time */
    private String procDt = null;

    /** start time stamp */
    private String startTs = null;

    /** User Param */
    private String varUser1 = null;

    /** job ID */
    private String runJobId = null;

    /** html temp */
    private String htmlTmpl = null;

    /** job Count */
    private BigDecimal jobCount = BigDecimal.ZERO;

    /** psnColNm */
    private String psnColNm = null;

    /** mlColNm */
    private String mlColNm = null;

    /** tblNm */
    private String tblNm = null;

    /** Error Map */
    private Map<String, String> errorMap = new TreeMap<String, String>();

    /** errorMessage */
    private StringBuilder errorMessage = new StringBuilder();

    // START 2022/09/19 R.Azucena [QC#60563 ADD]
    /** ntfyCtrHtmlErrMsg */
    private String ntfyCtrHtmlErrMsg = null;
    // END 2022/09/19 R.Azucena [QC#60563 ADD]

    @Override
    protected void initRoutine() {
        this.glblCmpyCd = super.getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CodeTableAccessor.findByKey(glblCmpyTMsg);

        if (glblCmpyTMsg == null) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Global Company Code" });
        }

        this.varUser1 = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(this.varUser1)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Job ID" });
        }

        this.procDt = ZYPDateUtil.getBatProcDate();
        this.startTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.runJobId = ZYPMaxTenDigitsNumbering.getUniqueID(this.glblCmpyCd, "NTFY_RUN_JOB_ID");
        // 2019/09/20 S21_NA#53601 Add Start
        commit();
        // 2019/09/20 S21_NA#53601 Add End

        this.htmlTmpl = ZYPCodeDataUtil.getVarCharConstValue(NTYF_HTML_TMPL_NM, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.htmlTmpl)) {
            throw new S21AbendException(NWWM0015E, new String[] {NTYF_HTML_TMPL_NM, VAR_CHAR_CONST });
        }

        this.jobCount = ZYPCodeDataUtil.getNumConstValue(JOB_COUNT_NM, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.jobCount)) {
            throw new S21AbendException(NWWM0015E, new String[] {JOB_COUNT_NM, VAR_CHAR_CONST });
        }

        // get TblNm and Column
        NTFY_DIST_QLFYTMsg inTMsg = new NTFY_DIST_QLFYTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.ntfyDistQlfyCd, NTFY_DIST_QLFY.EMAIL);
        inTMsg = (NTFY_DIST_QLFYTMsg) S21CodeTableAccessor.findByKey(inTMsg);

        if (inTMsg == null) {
            throw new S21AbendException(NWWM0015E, new String[] {"EMAIL", "NTFY_DIST_QLFY" });
        }

        this.mlColNm = inTMsg.ntfyDistQlfyColNm.getValue();
        this.tblNm = inTMsg.ntfyDistQlfyTblNm.getValue();

        inTMsg.clear();

        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.ntfyDistQlfyCd, NTFY_DIST_QLFY.EMP_ID);
        inTMsg = (NTFY_DIST_QLFYTMsg) S21CodeTableAccessor.findByKey(inTMsg);

        if (inTMsg == null) {
            throw new S21AbendException(NWWM0015E, new String[] {"EMP_ID", "NTFY_DIST_QLFY" });
        }

        this.psnColNm = inTMsg.ntfyDistQlfyColNm.getValue();

        // START 2022/09/19 R.Azucena [QC#60563 ADD]
        this.ntfyCtrHtmlErrMsg = ZYPCodeDataUtil.getVarCharConstValue(NTFY_CTR_HTML_ERR_MSG, this.glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(this.ntfyCtrHtmlErrMsg)) {
            this.ntfyCtrHtmlErrMsg = DEFAULT_NTFY_CTR_HTML_ERR_MSG;
        }
        // END 2022/09/19 R.Azucena [QC#60563 ADD]
    }

    @Override
    protected void mainRoutine() {
        List<Map<String, Object>> ntfyHdrList = getNtfyHdr();
        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        try {
            for (Map<String, Object> ntfyHdr : ntfyHdrList) {

                rsSet = null;
                stmt = null;

                List<Map<String, Object>> ntfyActDtlList = getNtfyActDtl((BigDecimal) ntfyHdr.get("NTFY_HDR_PK"));

                List<String> sbscrList = getNtfySbscr((BigDecimal) ntfyHdr.get("NTFY_HDR_PK"), psnColNm, mlColNm, tblNm);

                // 2019/11/29 QC#54226 Add Start
                if ((Clob) ntfyHdr.get("NTFY_SQL_CLOD") != null) {

                    String checkResult = checkSqlStatement("execSQL", clobToString((Clob) ntfyHdr.get("NTFY_SQL_CLOD")));
                    if (ZYPCommonFunc.hasValue(checkResult)) {
                        StringBuffer hdr = new StringBuffer("[Notif Id]").append((String) ntfyHdr.get("NTFY_HDR_ID"))
                                .append(NEW_LINE).append("[Notif Nm]").append((String) ntfyHdr.get("NTFY_HDR_NM"))
                                .append(NEW_LINE).append("[Error]").append(checkResult);
                        StringBuffer sql = new StringBuffer("[SQL]").append(clobToString((Clob) ntfyHdr.get("NTFY_SQL_CLOD")));
                        // append(NEW_LINE)
                        this.errorMap.put(hdr.toString(), sql.toString());
                        this.totalErrCount++;
                        continue;
                    }
                }
                // 2019/11/29 QC#54226 Add End

                for (Map<String, Object> ntfyActDtl : ntfyActDtlList) {

                    String mlTxt = "";
                    String toTxt = "";
                    String ccTxt = "";
                    String bccTxt = "";
                    String subjTxt = "";
                    String rpyToTxt = "";
                    StringBuilder distListNmTxt = new StringBuilder();

                    if (ZYPConstant.FLG_OFF_N.equals((String) ntfyActDtl.get("RTRV_TO_ADDR_FROM_SQL_FLG"))) {
                        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

                        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
                        execParam.setMaxRows(0);
                        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
                        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

                        if ((Clob) ntfyHdr.get("NTFY_SQL_CLOD") != null) {
                            stmt = this.ssmLLClient.createPreparedStatement("execSQL", clobToString((Clob) ntfyHdr.get("NTFY_SQL_CLOD")), execParam);
                            rsSet = stmt.executeQuery();
                        }

                        List<Map<String, String>> distListAssignList = getDistListAssign((BigDecimal) ntfyActDtl.get("NTFY_ACT_DTL_PK"));
                        List<String> mlAddrList = getMlAddr(distListAssignList, mlColNm, tblNm);
                        List<String> distListNmList = getDistListNm((BigDecimal) ntfyActDtl.get("NTFY_ACT_DTL_PK"));

                        for (Map<String, String> distListAssign : distListAssignList) {

                            if (NTFY_DIST_QLFY.EMAIL.equals(distListAssign.get("NTFY_DIST_QLFY_CD")) //
                                    && !mlAddrList.contains(distListAssign.get("NTFY_DIST_LIST_ASG_VAL_TXT"))) {
                                mlAddrList.add(distListAssign.get("NTFY_DIST_LIST_ASG_VAL_TXT"));
                            }
                        }

                        for (String distListNm : distListNmList) {

                            if (distListNmTxt.length() > 0) {
                                distListNmTxt.append(",");
                            }

                            distListNmTxt.append(distListNm);
                        }

                        // Create Replace Param Map
                        int colCnt = 0;
                        Map<String, Object> paramMap = new HashMap<String, Object>();
                        if (rsSet != null) {
                            if (rsSet.next()) {
                                colCnt = rsSet.getMetaData().getColumnCount();
                                for (int i = 1; i <= colCnt; i++) {
                                    String paramKey = rsSet.getMetaData().getColumnName(i);
                                    Object paramValue = rsSet.getObject(i);
                                    paramMap.put(paramKey, paramValue);
                                }
                                rsSet.beforeFirst();
                            }
                        }

                        mlTxt = replacePlaceHld(clobToString((Clob) ntfyActDtl.get("NTFY_EML_BODY_CLOD")), paramMap);
                        toTxt = replacePlaceHld((String) ntfyActDtl.get("NTFY_EML_TO_ADDR"), paramMap);
                        ccTxt = replacePlaceHld((String) ntfyActDtl.get("NTFY_EML_CC_ADDR"), paramMap);
                        bccTxt = replacePlaceHld((String) ntfyActDtl.get("NTFY_EML_BCC_ADDR"), paramMap);
                        subjTxt = "[" + (String) ntfyHdr.get("NTFY_HDR_ID") + "]" + replacePlaceHld((String) ntfyActDtl.get("NTFY_EML_SUBJ_TXT"), paramMap);
                        rpyToTxt = replacePlaceHld((String) ntfyActDtl.get("NTFY_EML_RPY_TO_ADDR"), paramMap);

                        // create toMlAddrList
                        String[] toTxts = toTxt.split(",");
                        for (int i = 0; i < toTxts.length; i++) {
                            if (!mlAddrList.contains(toTxts[i])) {
                                mlAddrList.add(toTxts[i]);
                            }
                        }

                        // subscription
                        for (String sbscr : sbscrList) {
                            if (mlAddrList.contains(sbscr)) {
                                mlAddrList.remove(sbscrList.indexOf(sbscr));
                            }
                        }

                        S21Mail mail = new S21Mail(this.glblCmpyCd);

                        if (NTFY_ATT_TP.EXCEL.equals((String) ntfyActDtl.get("NTFY_ATT_TP_CD"))) {
                            if (rsSet != null) {
                                // Mod Start 2016/11/24 M.Ohno S21_NA#16206
                                String csvTxt = createCsv(rsSet, (BigDecimal) ntfyActDtl.get("NTFY_ACT_DTL_PK"), paramMap);
                                if (ZYPCommonFunc.hasValue(csvTxt)) {
                                    S21MailAttachment attachment = new S21MailAttachment(this.glblCmpyCd);
                                    attachment.setAttachment(csvTxt);
                                    StringBuilder fileNm = new StringBuilder();
                                    fileNm = fileNm.append((String) ntfyHdr.get("NTFY_HDR_ID")).append("_").append(this.startTs).append(".csv");
                                    attachment.setFileName(fileNm.toString());
                                    mail.setAttachment(attachment);
                                }
                                // Mod End   2016/11/24 M.Ohno S21_NA#16206
                            }
                            mail.setText(mlTxt);

                        } else if (NTFY_ATT_TP.HTML.equals((String) ntfyActDtl.get("NTFY_ATT_TP_CD"))) {
                            String htmlTxt = createHtml(rsSet, (BigDecimal) ntfyActDtl.get("NTFY_ACT_DTL_PK"), paramMap, mlTxt);
                            mail.setContentType(CONT_TP_HTML);
                            // START 2022/09/19 R.Azucena [QC#60563 MOD]
                            // mail.setText(htmlTxt);
                            setMlTextHtml(mail, mlTxt, htmlTxt);
                            // END 2022/09/19 R.Azucena [QC#60563 MOD]
                        }

                        // post mail
                        if (ZYPCommonFunc.hasValue(subjTxt)) {
                            mail.setSubject(subjTxt);
                        }

                        if (mlAddrList != null && mlAddrList.size() > 0) {
                            mail.setToAddressList(createMlAddrList(mlAddrList));
                        }

                        if (ZYPCommonFunc.hasValue(ccTxt)) {
                            mail.setCcAddressList(createMlAddrList(ccTxt));
                        }

                        if (ZYPCommonFunc.hasValue(bccTxt)) {
                            mail.setBccAddressList(createMlAddrList(bccTxt));
                        }

                        if (ZYPCommonFunc.hasValue(rpyToTxt)) {
                            mail.setFromAddress(createMlAddrList(rpyToTxt).get(0));
                        }

                        mail.postMail();

                        // Create Send Mail Information
                        NTFY_SEND_EML_INFOTMsg histTMsg = new NTFY_SEND_EML_INFOTMsg();
                        ZYPEZDItemValueSetter.setValue(histTMsg.glblCmpyCd, this.glblCmpyCd);
                        BigDecimal histPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.NTFY_SEND_EML_INFO_SQ);
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfySendEmlInfoPk, histPk);
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfyHdrId, (String) ntfyHdr.get("NTFY_HDR_ID"));
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfyHdrNm, (String) ntfyHdr.get("NTFY_HDR_NM"));
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfyHdrDescTxt, (String) ntfyHdr.get("NTFY_HDR_DESC_TXT"));
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfyBizAreaTpCd, (String) ntfyHdr.get("NTFY_BIZ_AREA_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfySubAreaTpCd, (String) ntfyHdr.get("NTFY_SUB_AREA_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfyRunJobId, this.runJobId);
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfyRunTs, this.startTs);
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfyActId, (String) ntfyActDtl.get("NTFY_ACT_ID"));
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfyActNm, (String) ntfyActDtl.get("NTFY_ACT_NM"));
                        // 2019/09/20 S21_NA#53601 Mod Start
//                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfyActDtlHistId, ZYPMaxTenDigitsNumbering.getUniqueID(this.glblCmpyCd, "NTFY_ACT_DTL_HIST_ID"));
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfyActDtlHistId, String.format("%08d", histPk.intValue()));
                        // 2019/09/20 S21_NA#53601 Mod End
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfyActTpCd, (String) ntfyActDtl.get("NTFY_ACT_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfyOtptTpCd, (String) ntfyActDtl.get("NTFY_OTPT_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfyEmlRpyToAddr, rpyToTxt);
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfyEmlToAddr, toTxt);
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfyEmlCcAddr, ccTxt);
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfyEmlBccAddr, bccTxt);
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfyDistListNmListTxt, distListNmTxt.toString());
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfyEmlSubjTxt, subjTxt);
                        ZYPEZDItemValueSetter.setValue(histTMsg.ntfyHistDelDt, ZYPDateUtil.addDays(this.procDt, ((BigDecimal) ntfyHdr.get("HIST_DAYS_AOT")).intValue()));
                        EZDTBLAccessor.insert(histTMsg);

                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(histTMsg.getReturnCode())) {
                            rollback();
                            throw new S21AbendException(NWWM0021E, new String[] {histTMsg.getTableName() });
                        }

                        // update CLOB(NTFY_EML_BODY_CLOD)
                        if (!new NtfyEmlBodyClobAccessor(histTMsg).updateSql(mlTxt)) {
                            rollback();
                            throw new S21AbendException(NWWM0021E, new String[] {histTMsg.getTableName() });
                        }
                        this.totalNmlCount++;

                    } else {
                        // RYRV_TO_ADDR_FROM_SQL Mode
                        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

                        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
                        execParam.setMaxRows(0);
                        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
                        execParam.setResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE);
                        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

                        if ((Clob) ntfyHdr.get("NTFY_SQL_CLOD") == null) {
                            S21InfoLogOutput.println(ZZZM9025E, new String[] {"SQL" });
                            this.totalErrCount++;
                            this.termCd = TERM_CD.WARNING_END;
                            continue;

                        }
                        // 2019/12/03 QC#53020 Mod Start
                        // stmt = this.ssmLLClient.createPreparedStatement("execSQLForOrderBy", clobToString((Clob) ntfyHdr.get("NTFY_SQL_CLOD")), execParam);
                        stmt = this.ssmLLClient.createPreparedStatement("execSQL", clobToString((Clob) ntfyHdr.get("NTFY_SQL_CLOD")), execParam);
                        // 2019/12/03 QC#53020 Mod End
                        rsSet = stmt.executeQuery();

                        // Create Replace Param Map
                        int colCnt = 0;
                        Map<String, Object> paramMap = new HashMap<String, Object>();

                        if (rsSet.next()) {
                            colCnt = rsSet.getMetaData().getColumnCount();
                            for (int i = 1; i <= colCnt; i++) {
                                String paramKey = rsSet.getMetaData().getColumnName(i);
                                Object paramValue = rsSet.getObject(i);
                                paramMap.put(paramKey, paramValue);
                            }
                            rsSet.beforeFirst();
                        }

                        mlTxt = replacePlaceHld(clobToString((Clob) ntfyActDtl.get("NTFY_EML_BODY_CLOD")), paramMap);
                        ccTxt = replacePlaceHld((String) ntfyActDtl.get("NTFY_EML_CC_ADDR"), paramMap);
                        bccTxt = replacePlaceHld((String) ntfyActDtl.get("NTFY_EML_BCC_ADDR"), paramMap);
                        subjTxt = "[" + (String) ntfyHdr.get("NTFY_HDR_ID") + "]" + replacePlaceHld((String) ntfyActDtl.get("NTFY_EML_SUBJ_TXT"), paramMap);
                        rpyToTxt = replacePlaceHld((String) ntfyActDtl.get("NTFY_EML_RPY_TO_ADDR"), paramMap);

                        StringBuilder csvTxt = new StringBuilder();
                        StringBuilder htmlTxt = new StringBuilder();
                        StringBuilder headerTxt = new StringBuilder();
                        String beforeAddr = "";
                        boolean isBreak = false;

                        List<Map<String, Object>> ntfyActDtlColList = getNtfyActDtlCol((BigDecimal) ntfyActDtl.get("NTFY_ACT_DTL_PK"));

                        // Create Table Header
                        if (ntfyActDtlColList != null && ntfyActDtlColList.size() > 0) {

                            if (NTFY_ATT_TP.EXCEL.equals((String) ntfyActDtl.get("NTFY_ATT_TP_CD"))) {
                                createCsvHeader(ntfyActDtlColList, headerTxt);
                                csvTxt.append(headerTxt.toString());

                            } else if (NTFY_ATT_TP.HTML.equals((String) ntfyActDtl.get("NTFY_ATT_TP_CD"))) {
                                createHtmlHeader(ntfyActDtlColList, htmlTxt);
                                headerTxt.append(htmlTxt.toString());
                            }
                        }

                        while (rsSet.next()) {
                            toTxt = rsSet.getString(1);

                            // post mail
                            if (ZYPCommonFunc.hasValue(beforeAddr) && !beforeAddr.equals(toTxt)) {
                                S21Mail mail = new S21Mail(this.glblCmpyCd);

                                if (NTFY_ATT_TP.EXCEL.equals((String) ntfyActDtl.get("NTFY_ATT_TP_CD"))) {

                                    if (ZYPCommonFunc.hasValue(csvTxt.toString())) {

                                        S21MailAttachment attachment = new S21MailAttachment(this.glblCmpyCd);
                                        attachment.setAttachment(csvTxt.toString());
                                        StringBuilder fileNm = new StringBuilder();
                                        fileNm = fileNm.append((String) ntfyHdr.get("NTFY_HDR_ID")).append("_").append(this.startTs).append(".csv");
                                        attachment.setFileName(fileNm.toString());
                                        mail.setAttachment(attachment);

                                        csvTxt.delete(0, csvTxt.length());
                                        csvTxt.append(headerTxt.toString());
                                    }
                                    mail.setText(mlTxt);

                                } else if (NTFY_ATT_TP.HTML.equals((String) ntfyActDtl.get("NTFY_ATT_TP_CD"))) {
                                    String resultTxt = this.htmlTmpl;
                                    resultTxt = resultTxt.replace(ML_BODY_REPL_NM, htmlEscape(mlTxt));
                                    resultTxt = resultTxt.replace(HTML_TBL_REPL_NM, htmlTxt.toString());
                                    mail.setContentType(CONT_TP_HTML);
                                    // START 2022/09/19 R.Azucena [QC#60563 MOD]
                                    // mail.setText(resultTxt);
                                    setMlTextHtml(mail, mlTxt, resultTxt);
                                    // END 2022/09/19 R.Azucena [QC#60563 MOD]

                                    htmlTxt.delete(0, htmlTxt.length());
                                    htmlTxt.append(headerTxt.toString());
                                }

                                // post mail
                                if (ZYPCommonFunc.hasValue(subjTxt)) {
                                    mail.setSubject(subjTxt);
                                }

                                if (ZYPCommonFunc.hasValue(beforeAddr)) {
                                    mail.setToAddressList(createMlAddrList(beforeAddr));
                                }

                                if (ZYPCommonFunc.hasValue(ccTxt)) {
                                    mail.setCcAddressList(createMlAddrList(ccTxt));
                                }

                                if (ZYPCommonFunc.hasValue(bccTxt)) {
                                    mail.setBccAddressList(createMlAddrList(bccTxt));
                                }

                                if (ZYPCommonFunc.hasValue(rpyToTxt)) {
                                    mail.setFromAddress(createMlAddrList(rpyToTxt).get(0));
                                }

                                mail.postMail();

                                // Create Send Mail Information
                                NTFY_SEND_EML_INFOTMsg histTMsg = new NTFY_SEND_EML_INFOTMsg();
                                ZYPEZDItemValueSetter.setValue(histTMsg.glblCmpyCd, this.glblCmpyCd);
                                BigDecimal histPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.NTFY_SEND_EML_INFO_SQ);
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfySendEmlInfoPk, histPk);
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfyHdrId, (String) ntfyHdr.get("NTFY_HDR_ID"));
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfyHdrNm, (String) ntfyHdr.get("NTFY_HDR_NM"));
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfyHdrDescTxt, (String) ntfyHdr.get("NTFY_HDR_DESC_TXT"));
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfyBizAreaTpCd, (String) ntfyHdr.get("NTFY_BIZ_AREA_TP_CD"));
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfySubAreaTpCd, (String) ntfyHdr.get("NTFY_SUB_AREA_TP_CD"));
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfyRunJobId, this.runJobId);
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfyRunTs, this.startTs);
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfyActId, (String) ntfyActDtl.get("NTFY_ACT_ID"));
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfyActNm, (String) ntfyActDtl.get("NTFY_ACT_NM"));
                                // 2019/09/20 S21_NA#53601 Mod Start
//                              ZYPEZDItemValueSetter.setValue(histTMsg.ntfyActDtlHistId, ZYPMaxTenDigitsNumbering.getUniqueID(this.glblCmpyCd, "NTFY_ACT_DTL_HIST_ID"));
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfyActDtlHistId, String.format("%08d", histPk.intValue()));
                                // 2019/09/20 S21_NA#53601 Mod End
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfyActTpCd, (String) ntfyActDtl.get("NTFY_ACT_TP_CD"));
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfyOtptTpCd, (String) ntfyActDtl.get("NTFY_OTPT_TP_CD"));
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfyEmlRpyToAddr, rpyToTxt);
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfyEmlToAddr, beforeAddr);
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfyEmlCcAddr, ccTxt);
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfyEmlBccAddr, bccTxt);
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfyDistListNmListTxt, distListNmTxt.toString());
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfyEmlSubjTxt, subjTxt);
                                ZYPEZDItemValueSetter.setValue(histTMsg.ntfyHistDelDt, ZYPDateUtil.addDays(this.procDt, ((BigDecimal) ntfyHdr.get("HIST_DAYS_AOT")).intValue()));

                                EZDTBLAccessor.insert(histTMsg);
                                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(histTMsg.getReturnCode())) {
                                    rollback();
                                    this.totalErrCount++;
                                    throw new S21AbendException(NWWM0021E, new String[] {histTMsg.getTableName() });
                                }

                                // update CLOB(NTFY_EML_BODY_CLOD)
                                if (!new NtfyEmlBodyClobAccessor(histTMsg).updateSql(mlTxt)) {
                                    rollback();
                                    this.totalErrCount++;
                                    throw new S21AbendException(NWWM0021E, new String[] {histTMsg.getTableName() });
                                }
                                this.totalNmlCount++;

                            }

                            beforeAddr = toTxt;
                            // subscription
                            if (sbscrList.contains(toTxt)) {
                                continue;
                            }

                            // Mail Address Pattern Check
                            // 2016/10/06 QC#14431 Modify Start --------------
                            //if (!ZYPCommonFunc.hasValue(toTxt) || !toTxt.matches(CHK_EMAIL_PATTERN)) {
                            if (!ZYPCommonFunc.hasValue(toTxt) || !NMXC106001CommonCheckUtil.checkEmailFormat(toTxt)) {
                            // 2016/10/06 QC#14431 Modify End --------------
                                S21InfoLogOutput.println(NWWM0011E); //TODO
                                isBreak = true;
                                break;
                            }

                            if (ntfyActDtlColList != null && ntfyActDtlColList.size() > 0) {
                                if (NTFY_ATT_TP.EXCEL.equals((String) ntfyActDtl.get("NTFY_ATT_TP_CD"))) {
                                    createCsvOneLine(ntfyActDtlColList, paramMap, csvTxt, rsSet);
                                } else if (NTFY_ATT_TP.HTML.equals((String) ntfyActDtl.get("NTFY_ATT_TP_CD"))) {
                                    createHtmlOneLine(ntfyActDtlColList, paramMap, htmlTxt, rsSet);
                                }
                            }

                        }

                        // last post mail
                        if (ZYPCommonFunc.hasValue(beforeAddr) && !isBreak) {
                            S21Mail mail = new S21Mail(this.glblCmpyCd);

                            if (NTFY_ATT_TP.EXCEL.equals((String) ntfyActDtl.get("NTFY_ATT_TP_CD"))) {

                                if (ZYPCommonFunc.hasValue(csvTxt.toString())) {

                                    S21MailAttachment attachment = new S21MailAttachment(this.glblCmpyCd);
                                    attachment.setAttachment(csvTxt.toString());
                                    StringBuilder fileNm = new StringBuilder();
                                    fileNm = fileNm.append((String) ntfyHdr.get("NTFY_HDR_ID")).append("_").append(this.startTs).append(".csv");
                                    attachment.setFileName(fileNm.toString());
                                    mail.setAttachment(attachment);

                                    csvTxt.delete(0, csvTxt.length());
                                    csvTxt.append(headerTxt.toString());
                                }
                                mail.setText(mlTxt);

                            } else if (NTFY_ATT_TP.HTML.equals((String) ntfyActDtl.get("NTFY_ATT_TP_CD"))) {
                                String resultTxt = this.htmlTmpl;
                                resultTxt = resultTxt.replace(ML_BODY_REPL_NM, htmlEscape(mlTxt));
                                resultTxt = resultTxt.replace(HTML_TBL_REPL_NM, htmlTxt.toString());
                                mail.setContentType(CONT_TP_HTML);
                                // START 2022/09/19 R.Azucena [QC#60563 MOD]
                                // mail.setText(resultTxt);
                                setMlTextHtml(mail, mlTxt, resultTxt);
                                // END 2022/09/19 R.Azucena [QC#60563 MOD]

                                htmlTxt.delete(0, htmlTxt.length());
                                htmlTxt.append(headerTxt.toString());
                            }

                            // post mail
                            if (ZYPCommonFunc.hasValue(subjTxt)) {
                                mail.setSubject(subjTxt);
                            }

                            if (ZYPCommonFunc.hasValue(beforeAddr)) {
                                mail.setToAddressList(createMlAddrList(beforeAddr));
                            }

                            if (ZYPCommonFunc.hasValue(ccTxt)) {
                                mail.setCcAddressList(createMlAddrList(ccTxt));
                            }

                            if (ZYPCommonFunc.hasValue(bccTxt)) {
                                mail.setBccAddressList(createMlAddrList(bccTxt));
                            }

                            if (ZYPCommonFunc.hasValue(rpyToTxt)) {
                                mail.setFromAddress(createMlAddrList(rpyToTxt).get(0));
                            }

                            mail.postMail();

                            // Create Send Mail Information
                            NTFY_SEND_EML_INFOTMsg histTMsg = new NTFY_SEND_EML_INFOTMsg();
                            ZYPEZDItemValueSetter.setValue(histTMsg.glblCmpyCd, this.glblCmpyCd);
                            BigDecimal histPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.NTFY_SEND_EML_INFO_SQ);
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfySendEmlInfoPk, histPk);
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfyHdrId, (String) ntfyHdr.get("NTFY_HDR_ID"));
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfyHdrNm, (String) ntfyHdr.get("NTFY_HDR_NM"));
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfyHdrDescTxt, (String) ntfyHdr.get("NTFY_HDR_DESC_TXT"));
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfyBizAreaTpCd, (String) ntfyHdr.get("NTFY_BIZ_AREA_TP_CD"));
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfySubAreaTpCd, (String) ntfyHdr.get("NTFY_SUB_AREA_TP_CD"));
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfyRunJobId, this.runJobId);
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfyRunTs, this.startTs);
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfyActId, (String) ntfyActDtl.get("NTFY_ACT_ID"));
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfyActNm, (String) ntfyActDtl.get("NTFY_ACT_NM"));
                            // 2019/09/20 S21_NA#53601 Mod Start
//                          ZYPEZDItemValueSetter.setValue(histTMsg.ntfyActDtlHistId, ZYPMaxTenDigitsNumbering.getUniqueID(this.glblCmpyCd, "NTFY_ACT_DTL_HIST_ID"));
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfyActDtlHistId, String.format("%08d", histPk.intValue()));
                            // 2019/09/20 S21_NA#53601 Mod End
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfyActTpCd, (String) ntfyActDtl.get("NTFY_ACT_TP_CD"));
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfyOtptTpCd, (String) ntfyActDtl.get("NTFY_OTPT_TP_CD"));
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfyEmlRpyToAddr, rpyToTxt);
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfyEmlToAddr, toTxt);
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfyEmlCcAddr, ccTxt);
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfyEmlBccAddr, bccTxt);
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfyDistListNmListTxt, distListNmTxt.toString());
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfyEmlSubjTxt, subjTxt);
                            ZYPEZDItemValueSetter.setValue(histTMsg.ntfyHistDelDt, ZYPDateUtil.addDays(this.procDt, ((BigDecimal) ntfyHdr.get("HIST_DAYS_AOT")).intValue()));

                            EZDTBLAccessor.insert(histTMsg);

                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(histTMsg.getReturnCode())) {
                                rollback();
                                this.totalErrCount++;
                                throw new S21AbendException(NWWM0021E, new String[] {histTMsg.getTableName() });
                            }

                            // update CLOB(NTFY_EML_BODY_CLOD)
                            if (!new NtfyEmlBodyClobAccessor(histTMsg).updateSql(mlTxt)) {
                                rollback();
                                this.totalErrCount++;
                                throw new S21AbendException(NWWM0021E, new String[] {histTMsg.getTableName() });
                            }

                            this.totalNmlCount++;
                        } else {
                            // 2020/01/15 QC#55211 Del Start
                            // this.totalErrCount++;
                            // this.termCd = TERM_CD.WARNING_END;
                            // 2020/01/15 QC#55211 Del End

                            // 2019/12/25 QC#55211 Add Start
                            // 2020/02/05 QC#55211-1 Mod Start
                            //if (!ZYPCommonFunc.hasValue(beforeAddr)){ 
                            if (!ZYPCommonFunc.hasValue(beforeAddr) || isBreak) {
                            // 2020/02/05 QC#55211-1 Mod End
                                String hdrId = (String) ntfyHdr.get("NTFY_HDR_ID");
                                String hdrNm = (String) ntfyHdr.get("NTFY_HDR_NM");
                                String hdrDescTxt = (String) ntfyHdr.get("NTFY_HDR_DESC_TXT");

                                S21InfoLogOutput.println(String.format("Failed to get Email To address. [Notif ID:%s] [Name:%s] [Description:%s]", hdrId, hdrNm, hdrDescTxt));
                                this.totalNmlCount++;
                            } else {
                                this.totalErrCount++;
                                this.termCd = TERM_CD.WARNING_END;
                            }
                            // 2019/12/25 QC#55211 Add End
                        }

                        rsSet.beforeFirst();
                    }

                }

                NTFY_RUN_JOBTMsg runTMsg = new NTFY_RUN_JOBTMsg();
                ZYPEZDItemValueSetter.setValue(runTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(runTMsg.ntfyHdrPk, (BigDecimal) ntfyHdr.get("NTFY_HDR_PK"));

                runTMsg = (NTFY_RUN_JOBTMsg) EZDTBLAccessor.findByKeyForUpdateWait(runTMsg);

                String nextRunTs = getNextRunTs(ntfyHdr);
                ZYPEZDItemValueSetter.setValue(runTMsg.nextRunTs, nextRunTs);
                ZYPEZDItemValueSetter.setValue(runTMsg.lastRunTs, this.startTs);

                EZDTBLAccessor.update(runTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(runTMsg.getReturnCode())) {
                    rollback();
                    this.totalErrCount++;
                    throw new S21AbendException(NWWM0020E, new String[] {runTMsg.getTableName() });
                }
            }

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } catch (ParseException e) {
            e.printStackTrace();

            // 2019/12/25 QC#55211 Add Start
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            S21InfoLogOutput.println(sw.toString());
            // 2019/12/25 QC#55211 Add End
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }

    }

    private List<String> getDistListNm(BigDecimal ntfyActDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("ntfyActDtlPk", ntfyActDtlPk);
        return (List<String>) this.ssmBatchClient.queryObjectList("getDistListNm", ssmParam);
    }

    private List<Map<String, Object>> getNtfyHdr() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("procDt", this.procDt);
        ssmParam.put("jobCount", this.jobCount);
        ssmParam.put("jobId", this.varUser1);
        ssmParam.put("runTs", this.startTs);
        ssmParam.put("maxDt", MAX_DT);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getNtfyHdr", ssmParam);
    }

    private List<Map<String, Object>> getNtfyActDtl(BigDecimal ntfyHdrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("ntfyHdrPk", ntfyHdrPk);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getNtfyActDtl", ssmParam);
    }

    private List<Map<String, String>> getDistListAssign(BigDecimal ntfyActDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("procDt", this.procDt);
        ssmParam.put("maxDt", MAX_DT);
        ssmParam.put("ntfyActDtlPk", ntfyActDtlPk);
        return (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getDistListAssign", ssmParam);
    }

    private List<String> getNtfySbscr(BigDecimal ntfyHdrPk, String psnColNm, String mlColNm, String tblNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("ntfyHdrPk", ntfyHdrPk);
        ssmParam.put("psnColNm", psnColNm);
        ssmParam.put("mlColNm", mlColNm);
        ssmParam.put("tblNm", tblNm);
        return (List<String>) this.ssmBatchClient.queryObjectList("getNtfySbscr", ssmParam);
    }

    private List<String> getMlAddr(List<Map<String, String>> distListAssignList, String colNm, String tblNm) {
        if (distListAssignList == null || distListAssignList.size() <= 0) {
            return new ArrayList<String>();
        }

        String sql = createSearchMailAddrSQL(distListAssignList);

        if (!ZYPCommonFunc.hasValue(sql)) {
            return new ArrayList<String>();
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("colNm", colNm);
        ssmParam.put("tblNm", tblNm);
        ssmParam.put("sql", sql);
        return (List<String>) this.ssmBatchClient.queryObjectList("getMlAddr", ssmParam);
    }

    private String createSearchMailAddrSQL(List<Map<String, String>> distListAssignList) {
        StringBuilder sql = new StringBuilder();
        sql.append("(").append(CRLF);
        int i = 0;
        for (Map<String, String> distListAssign : distListAssignList) {

            if (NTFY_DIST_QLFY.EMAIL.equals(distListAssign.get("NTFY_DIST_QLFY_CD"))) {
                continue;
            }

            if (i != 0) {
                sql.append("OR ");
            }

            NTFY_DIST_QLFYTMsg inTMsg = new NTFY_DIST_QLFYTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.ntfyDistQlfyCd, distListAssign.get("NTFY_DIST_QLFY_CD"));
            inTMsg = (NTFY_DIST_QLFYTMsg) S21CodeTableAccessor.findByKey(inTMsg);
            String colNm = inTMsg.ntfyDistQlfyColNm.getValue();
            String value = distListAssign.get("NTFY_DIST_LIST_ASG_VAL_TXT");
            sql.append("A.").append(colNm).append(EQUAL).append(QUOTE).append(value).append(QUOTE);
            sql.append(CRLF);
            i++;
        }

        if (i == 0) {
            return "";
        }
        sql.append(")");
        return sql.toString();
    }

    private String replacePlaceHld(String tmp, Map<String, Object> paramMap) {
        if (!ZYPCommonFunc.hasValue(tmp)) {
            return "";
        }
        Pattern pattern = Pattern.compile(PARAM_PATTERN);
        Matcher matcher = pattern.matcher(tmp);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {

            String paramKey = matcher.group(PARAM_KEY_GROUP);
            if (!paramMap.containsKey(paramKey)) {
                continue;
            }
            String param = paramMap.get(paramKey).toString();
            param = param.replaceAll("\\\\", "\\\\\\\\");
            param = param.replaceAll("\\$", "\\\\\\$");
            matcher.appendReplacement(sb, param);

        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private String createCsv(ResultSet rsSet, BigDecimal ntfyActDtlPk, Map<String, Object> paramMap) throws SQLException {
        List<Map<String, Object>> ntfyActDtlColList = getNtfyActDtlCol(ntfyActDtlPk);
        if (ntfyActDtlColList == null || ntfyActDtlColList.size() <= 0) {
            return "";
        }

        StringBuilder csvTxt = new StringBuilder();

        // header label
        createCsvHeader(ntfyActDtlColList, csvTxt);

        // detail column
        while (rsSet.next()) {
            createCsvOneLine(ntfyActDtlColList, paramMap, csvTxt, rsSet);
        }

        rsSet.beforeFirst();

        return csvTxt.toString();

    }

    private List<Map<String, Object>> getNtfyActDtlCol(BigDecimal ntfyActDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("ntfyActDtlPk", ntfyActDtlPk);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getNtfyActDtlCol", ssmParam);

    }

    private List<S21MailAddress> createMlAddrList(String addrListStr) {
        String[] addr = addrListStr.split(",");
        List<S21MailAddress> addrList = new ArrayList<S21MailAddress>(addr.length);
        for (int i = 0; i < addr.length; i++) {
            addrList.add(new S21MailAddress(this.glblCmpyCd, addr[i]));
        }

        return addrList;
    }

    private List<S21MailAddress> createMlAddrList(List<String> addrListStr) {
        List<S21MailAddress> addrList = new ArrayList<S21MailAddress>(addrListStr.size());

        for (String addr : addrListStr) {

            if (!ZYPCommonFunc.hasValue(addr)) {
                continue;
            }

            addrList.add(new S21MailAddress(this.glblCmpyCd, addr));
        }

        return addrList;
    }

    private void createCsvHeader(List<Map<String, Object>> ntfyActDtlColList, StringBuilder csvTxt) {
        for (int i = 0; i < ntfyActDtlColList.size(); i++) {
            if (i != 0) {
                csvTxt.append(",");
            }

            csvTxt.append(ntfyActDtlColList.get(i).get("HDR_LB_NM"));
        }
        csvTxt.append(CRLF);
    }

    private void createCsvOneLine(List<Map<String, Object>> ntfyActDtlColList, Map<String, Object> paramMap, StringBuilder csvTxt, ResultSet rsSet) throws SQLException {
        int i = 0;
        for (Map<String, Object> ntfyActDtlCol : ntfyActDtlColList) {
            if (i != 0) {
                csvTxt.append(",");
            }

            String colNm = ((String) ntfyActDtlCol.get("PLACE_HLD_NM")).substring(2, ((String) ntfyActDtlCol.get("PLACE_HLD_NM")).length() - 1);

            if (!paramMap.containsKey(colNm)) {
                csvTxt.append((String) ntfyActDtlCol.get("PLACE_HLD_NM"));
            } else {
                csvTxt.append(nullCheckToString(rsSet.getObject(colNm)));
            }

            i++;
        }
        csvTxt.append(CRLF);
    }

    private String createHtml(ResultSet rsSet, BigDecimal ntfyActDtlPk, Map<String, Object> paramMap, String mlTxt) throws SQLException {

        String resultTxt = this.htmlTmpl;

        resultTxt = resultTxt.replace(ML_BODY_REPL_NM, htmlEscape(mlTxt));
        List<Map<String, Object>> ntfyActDtlColList = getNtfyActDtlCol(ntfyActDtlPk);
        if (ntfyActDtlColList == null || ntfyActDtlColList.size() <= 0) {
            resultTxt = resultTxt.replace(HTML_TBL_REPL_NM, "");
            return resultTxt;
        }

        StringBuilder htmlTxt = new StringBuilder();

        // table label
        createHtmlHeader(ntfyActDtlColList, htmlTxt);

        // detail column
        if (rsSet != null) {

            while (rsSet.next()) {
                createHtmlOneLine(ntfyActDtlColList, paramMap, htmlTxt, rsSet);
            }

            rsSet.beforeFirst();
        }

        resultTxt = resultTxt.replace(HTML_TBL_REPL_NM, htmlTxt.toString());

        return resultTxt;

    }

    private void createHtmlHeader(List<Map<String, Object>> ntfyActDtlColList, StringBuilder htmlTxt) {
        for (int i = 0; i < ntfyActDtlColList.size(); i++) {
            if (i == 0) {
                htmlTxt.append("<tr>");
            }

            htmlTxt.append("<th>").append(ntfyActDtlColList.get(i).get("HDR_LB_NM")).append("</th>");
        }
        htmlTxt.append("</tr>");
        htmlTxt.append(CRLF);
    }

    private void createHtmlOneLine(List<Map<String, Object>> ntfyActDtlColList, Map<String, Object> paramMap, StringBuilder htmlTxt, ResultSet rsSet) throws SQLException {
        int i = 0;
        for (Map<String, Object> ntfyActDtlCol : ntfyActDtlColList) {
            if (i == 0) {
                htmlTxt.append("<tr>");
            }

            String colNm = ((String) ntfyActDtlCol.get("PLACE_HLD_NM")).substring(2, ((String) ntfyActDtlCol.get("PLACE_HLD_NM")).length() - 1);

            htmlTxt.append("<td>");

            if (!paramMap.containsKey(colNm)) {
                htmlTxt.append((String) ntfyActDtlCol.get("PLACE_HLD_NM"));
            } else {
                htmlTxt.append(nullCheckToString(rsSet.getObject(colNm)));
            }

            htmlTxt.append("</td>");

            i++;
        }
        htmlTxt.append("</tr>");
        htmlTxt.append(CRLF);
    }

    private static String htmlEscape(String strVal) {
        StringBuffer strResult = new StringBuffer();
        for (int i = 0; i < strVal.length(); i++) {
            switch (strVal.charAt(i)) {
                case '&':
                    strResult.append("&amp;");
                    break;
                case '<':
                    strResult.append("&lt;");
                    break;
                case '>':
                    strResult.append("&gt;");
                    break;
                default:
                    strResult.append(strVal.charAt(i));
                    break;
            }
        }
        return strResult.toString();
    }

    private String clobToString(Clob clob) throws SQLException {
        return clob.getSubString(1, (int) clob.length());
    }

    private String nullCheckToString(Object obj) {
        if (obj == null) {
            return "";
        }

        return obj.toString();
    }

    private String getNextRunTs(Map<String, Object> ntfyHdr) throws ParseException {
        String nowTs = this.startTs;
        SimpleDateFormat dateformat = new SimpleDateFormat(DATE_PATTERN);
        Calendar cal = Calendar.getInstance();
        Date date = new Date(dateformat.parse(nowTs).getTime());
        cal.setTime(date);

        if (NTFY_FREQ_TP.DAILY.equals(ntfyHdr.get("NTFY_FREQ_TP_CD"))) {
            String endHM = (String) ntfyHdr.get("NTFY_END_HOUR_MN");
            String startHM = (String) ntfyHdr.get("NTFY_START_HOUR_MN");

            if (ZYPCommonFunc.hasValue((BigDecimal) ntfyHdr.get("NTFY_INTVL_AOT"))) {
                int intvl = ((BigDecimal) ntfyHdr.get("NTFY_INTVL_AOT")).intValue();

                // add min
                if (NTFY_INTVL_UOM_TP.MINUTES.equals(ntfyHdr.get("NTFY_INTVL_UOM_TP_CD"))) {
                    cal.add(Calendar.MINUTE, intvl);
                } else {
                    // add hour
                    cal.add(Calendar.HOUR_OF_DAY, intvl);
                }

                // add result HHmm
                String hourMin = String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + String.format("%02d", cal.get(Calendar.MINUTE));

                if (endHM.compareTo(hourMin) < 0) {
                    cal.add(Calendar.DATE, 1);
                    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHM.substring(0, 2)));
                    cal.set(Calendar.MINUTE, Integer.parseInt(startHM.substring(2, 4)));

                } else if (startHM.compareTo(hourMin) > 0) {
                    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHM.substring(0, 2)));
                    cal.set(Calendar.MINUTE, Integer.parseInt(startHM.substring(2, 4)));

                }

            } else if (ZYPCommonFunc.hasValue((String) ntfyHdr.get("NTFY_RUN_MN_LIST_TXT"))) {
                String runMnTxt = (String) ntfyHdr.get("NTFY_RUN_MN_LIST_TXT");

                List<String> runMnList = new ArrayList<String>();
                if (ZYPCommonFunc.hasValue(runMnTxt)) {
                    String[] runMnTxts = runMnTxt.split(",");
                    for (int i = 0; i < runMnTxts.length; i++) {
                        runMnList.add(String.format("%2s", runMnTxts[i]).replace(" ", "0"));
                    }
                }

                Collections.sort(runMnList);
                int mn = cal.get(Calendar.MINUTE);

                // next Hour
                if (mn == Integer.parseInt(Collections.max(runMnList)) //
                        || mn > Integer.parseInt(Collections.max(runMnList))) {
                    cal.add(Calendar.HOUR_OF_DAY, 1);
                    cal.set(Calendar.MINUTE, Integer.parseInt(Collections.min(runMnList)));

                } else {

                    // next Minute
                    for (String runMn : runMnList) {
                        int runMnInt = Integer.parseInt(runMn);
                        if (mn > runMnInt || mn == runMnInt) {
                            continue;
                        }
                        cal.set(Calendar.MINUTE, Integer.parseInt(runMn));
                        break;
                    }
                }

                // add result HHmm
                String hourMin = String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + String.format("%02d", cal.get(Calendar.MINUTE));

                if (endHM.compareTo(hourMin) < 0) {
                    cal.add(Calendar.DATE, 1);
                    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHM.substring(0, 2)));
                    cal.set(Calendar.MINUTE, Integer.parseInt(startHM.substring(2, 4)));

                } else if (startHM.compareTo(hourMin) > 0) {
                    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHM.substring(0, 2)));
                    cal.set(Calendar.MINUTE, Integer.parseInt(startHM.substring(2, 4)));

                }

            } else {

                // add result HHmm
                String hourMin = String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + String.format("%02d", cal.get(Calendar.MINUTE));

                if (startHM.compareTo(hourMin) <= 0) {
                    cal.add(Calendar.DATE, 1);
                }

                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHM.substring(0, 2)));
                cal.set(Calendar.MINUTE, Integer.parseInt(startHM.substring(2, 4)));
            }

        } else if (NTFY_FREQ_TP.MONTHLY.equals(ntfyHdr.get("NTFY_FREQ_TP_CD"))) {
            String runDayTxt = (String) ntfyHdr.get("NTFY_RUN_DAY_LIST_TXT");

            List<String> runDayList = new ArrayList<String>();
            if (ZYPCommonFunc.hasValue(runDayTxt)) {
                String[] runDayTxts = runDayTxt.split(",");
                for (int i = 0; i < runDayTxts.length; i++) {
                    runDayList.add(String.format("%2s", runDayTxts[i]).replace(" ", "0"));
                }
            }

            Collections.sort(runDayList);
            int day = cal.get(Calendar.DATE);
            int lastDay = cal.getActualMaximum(Calendar.DATE);

            List<String> runDayListSub = new ArrayList<String>();
            runDayListSub.addAll(runDayList);

            // remove day
            if (ZYPCommonFunc.hasValue(runDayTxt)) {
                for (String runDay : runDayList) {
                    if (Integer.parseInt(runDay) > lastDay) {
                        runDayList.remove(runDay);
                    }
                }
            }

            // EOM Flag
            if (ZYPConstant.FLG_ON_Y.equals(ntfyHdr.get("NTFY_EOM_FLG"))) {
                if (!runDayList.contains(String.valueOf(lastDay))) {
                    runDayList.add(String.valueOf(lastDay));
                }
            }

            // next Month
            if (runDayList.size() < 0 //
                    || day == Integer.parseInt(Collections.max(runDayList)) //
                    || day > Integer.parseInt(Collections.max(runDayList))) {

                cal.add(Calendar.MONTH, 1);

                if (ZYPConstant.FLG_ON_Y.equals(ntfyHdr.get("NTFY_EOM_FLG"))) {
                    if (!runDayListSub.contains(cal.getActualMaximum(Calendar.DATE))) {
                        runDayListSub.add(String.valueOf(cal.getActualMaximum(Calendar.DATE)));
                    }
                }

                if (Integer.parseInt(Collections.min(runDayListSub)) < cal.getActualMaximum(Calendar.DATE)) {
                    cal.set(Calendar.DATE, Integer.parseInt(Collections.min(runDayListSub)));

                } else {
                    cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                }

            } else {

                // next Day
                for (String runDay : runDayList) {
                    int runDayInt = Integer.parseInt(runDay);

                    if (day > runDayInt || day == runDayInt) {
                        continue;
                    }
                    cal.set(Calendar.DATE, Integer.parseInt(runDay));
                    break;
                }
            }

            String startHM = (String) ntfyHdr.get("NTFY_START_HOUR_MN");

            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHM.substring(0, 2)));
            cal.set(Calendar.MINUTE, Integer.parseInt(startHM.substring(2, 4)));

        } else if (NTFY_FREQ_TP.WEEKLY.equals(ntfyHdr.get("NTFY_FREQ_TP_CD"))) {
            int week = cal.get(Calendar.DAY_OF_WEEK);
            List<Integer> weekList = new ArrayList<Integer>();

            if (ZYPConstant.FLG_ON_Y.equals(ntfyHdr.get("NTFY_RUN_SUN_FLG"))) {
                weekList.add(Calendar.SUNDAY);
            }

            if (ZYPConstant.FLG_ON_Y.equals(ntfyHdr.get("NTFY_RUN_MON_FLG"))) {
                weekList.add(Calendar.MONDAY);
            }

            if (ZYPConstant.FLG_ON_Y.equals(ntfyHdr.get("NTFY_RUN_TUE_FLG"))) {
                weekList.add(Calendar.TUESDAY);
            }

            if (ZYPConstant.FLG_ON_Y.equals(ntfyHdr.get("NTFY_RUN_WED_FLG"))) {
                weekList.add(Calendar.WEDNESDAY);
            }

            if (ZYPConstant.FLG_ON_Y.equals(ntfyHdr.get("NTFY_RUN_THU_FLG"))) {
                weekList.add(Calendar.THURSDAY);
            }

            if (ZYPConstant.FLG_ON_Y.equals(ntfyHdr.get("NTFY_RUN_FRI_FLG"))) {
                weekList.add(Calendar.FRIDAY);
            }

            if (ZYPConstant.FLG_ON_Y.equals(ntfyHdr.get("NTFY_RUN_SAT_FLG"))) {
                weekList.add(Calendar.SATURDAY);
            }

            // next Week
            if (week == Collections.max(weekList) //
                    || week > Collections.max(weekList)) {
                cal.add(Calendar.DATE, Calendar.SATURDAY - week + Collections.min(weekList));

            } else {

                // next Day
                for (int weekInt : weekList) {

                    if (week > weekInt || week == weekInt) {
                        continue;
                    }
                    cal.add(Calendar.DATE, weekInt - week);
                    break;
                }
            }

            String startHM = (String) ntfyHdr.get("NTFY_START_HOUR_MN");

            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHM.substring(0, 2)));
            cal.set(Calendar.MINUTE, Integer.parseInt(startHM.substring(2, 4)));

        }

        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 000);

        return dateformat.format(cal.getTime());
    }

    // 2019/11/29 QC#54226 Add Start
    /**
     * check SQL Statement.
     * @param stmtId statement id
     * @param sql SQL
     * @param execParam SQL parameter
     * @return SQL Ignored Message
     */
    private String checkSqlStatement(String stmtId, String sql) {

        String sqlIgnoredMsg = null;

        try {
            this.ssmBatchClient.queryObjectList(stmtId, sql);
        } catch (Exception e) {
            if (e instanceof EZDDBRetryRequestException) {
                Throwable th = ((EZDDBRetryRequestException) e).getCause();
                if (th instanceof SQLException) {
                    sqlIgnoredMsg = ((SQLException) th).getMessage();
                } else {
                    throw (RuntimeException) th;
                }
            } else {
                throw (RuntimeException) e;
            }
        }

        return sqlIgnoredMsg;
    }

    private void sendErrorMail() {
        if (this.errorMap == null || this.errorMap.size() == 0) {
            return;
        }
        for (String key : this.errorMap.keySet()) {
            String val = this.errorMap.get(key);
            this.errorMessage.append(NEW_LINE).append(key).append(NEW_LINE);
            this.errorMessage.append(val);
        }

        postMail();
        commit();
    }

    private void postMail() {
        S21MailGroup mailGroupFrom = null;
        S21MailGroup mailGroupTo = null;
        S21MailAddress fromAddress = null;
        List<S21MailAddress> toAddressList = null;
        S21MailTemplate mailTemplate = null;
        S21Mail mail = null;

        mailGroupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        fromAddress = mailGroupFrom.getMailAddress().get(0);

        mailGroupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        toAddressList = mailGroupTo.getMailAddress();

        mailTemplate = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID_FOR_BIZ_ERROR);
        mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_BATCH_ID, BATCH_ID);
        mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_BATCH_NM, BATCH_NM);
        mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_BATCH_PROC_LOG_ID, getBatchProcessLogID());
        mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_MSG_INFO, this.errorMessage.toString());

        mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(fromAddress);
        mail.setToAddressList(toAddressList);
        mail.setMailTemplate(mailTemplate);
        mail.postMail();
    }

    // 2019/11/29 QC#54226 Add End

    @Override
    protected void termRoutine() {
        this.totalCount = this.totalNmlCount + this.totalErrCount;
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);
        // 2019/11/29 QC#54226 Add Start
        sendErrorMail();
        // 2019/11/29 QC#54226 Add End

    }

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NWWB001001().executeBatch(NWWB001001.class.getSimpleName());
    }

    // START 2022/09/19 R.Azucena [QC#60563 ADD]
    /**
     * setMlTextHtml
     * @param mail S21Mail
     * @param mlTxt String
     * @param htmlTxt String
     */
    private void setMlTextHtml(S21Mail mail, String mlTxt, String htmlTxt) {
        if (htmlTxt.length() > MAX_ML_BODY_LEN) {
            String localMailTxt = String.format("<tr><td>%s</td></tr>", this.ntfyCtrHtmlErrMsg);
            String resultTxt = this.htmlTmpl;

            resultTxt = resultTxt.replace(ML_BODY_REPL_NM, htmlEscape(mlTxt));
            resultTxt = resultTxt.replace(HTML_TBL_REPL_NM, localMailTxt);
            mail.setText(resultTxt);
        } else {
            mail.setText(htmlTxt);
        }
    }
    // START 2022/09/19 R.Azucena [QC#60563 ADD]
}
