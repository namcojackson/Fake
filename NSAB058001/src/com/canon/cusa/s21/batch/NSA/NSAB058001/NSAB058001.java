/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB058001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_BR_RESRC_RELNTMsg;
import business.db.SVC_CONTR_BRTMsg;
import business.db.SVC_RGTMsg;
import business.db.SVC_RG_BR_RELNTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Update Contract Branch Hierarchy
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/22   Hitachi         T.Aoyagi        Create          N/A
 * 2016/07/29   Hitachi         T.Tomita        Update          QC#12499, 12534
 * 2016/09/22   Fujitsu         S.Nakai         Update          QC#
 * 2016/10/17   Fujitsu         Y.Zhang         Update          QC#15002
 * 2016/12/12   Hitachi         Y.Takeno        Update          QC#16273
 * </pre>
 */
public class NSAB058001 extends S21BatchMain {

    /** [@] is not registered.(@) */
    private static final String NSAM0069E = "NSAM0069E";

    /** Failed to update "@". */
    private static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    private static final String NSAM0032E = "NSAM0032E";

    /** Failed to delete "@". */
    private static final String NSAM0033E = "NSAM0033E";
    /** [@] field is mandatory. */
    private static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    private static final String ZZZM9006E = "ZZZM9006E";

    /** SSM ID : getDsOrgUnitRg */
    private static final String SSM_ID_GET_DS_ORG_UNIT_RG = "getDsOrgUnitRg";

    /** SSM ID : getSvcRg */
    private static final String SSM_ID_GET_SVC_RG = "getSvcRg";

    /** SSM ID : getDsOrgUnitBr */
    private static final String SSM_ID_GET_DS_ORG_UNIT_BR = "getDsOrgUnitBr";

    /** SSM ID : getSvcContrBr */
    private static final String SSM_ID_GET_SVC_CONTR_BR = "getSvcContrBr";

    /** SSM ID : getDsOrgUnitRgBr */
    private static final String SSM_ID_GET_DS_ORG_UNIT_RG_BR = "getDsOrgUnitRgBr";

    /** SSM ID : getSvcRgBrReln */
    private static final String SSM_ID_GET_SVC_RG_BR_RELN = "getSvcRgBrReln";

    /** SSM ID : getDsOrgUnitResrc */
    private static final String SSM_ID_GET_DS_ORG_UNIT_RESRC = "getDsOrgUnitResrc";

    /** SSM ID : getSvcBrResrcReln */
    private static final String SSM_ID_GET_SVC_BR_RESRC_RELN = "getSvcBrResrcReln";

    /** message Item: GlobalCompanyCode */
    private static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** MAX_COMMIT_NUMBER:1000 */
    private static final int MAX_COMMIT_NUMBER = 1000;

    /** Length 3 */
    private static final int LEN_3 = 3;

    /** Length 30 */
    private static final int LEN_30 = 30;

    /** Business Application ID */
    private static final String BIZ_APP_ID = "NSAB0580";

    /** mail group id for From */
    private static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    private static final String MAIL_GROUP_ID_TO = "NSAB0580";

    /** template ID */
    private static final String MAIL_TEMPLATE_ID = BIZ_APP_ID + "M001";

    /** template parameter key : batch id */
    private static final String MAIL_TEMPLATE_KEY_ID = "batchId";

    /** template parameter key : err date */
    private static final String MAIL_TEMPLATE_KEY_DATE = "errDate";

    /** template parameter key : message */
    private static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    /** Date Time Pattern For Mail */
    private static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Date */
    private String salesDate;

    /** Contract Branch First Organization Code */
    private String contrBrFirstOrgCd;

    /** Contract Line Business System Source Code */
    private String contrLineBizSysSrcCd;

    /** Commit Number */
    private int commitNumber;

    /** Total Count */
    private int totalCount;

    /** error Count */
    private int errorCount;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatClient = null;

    /** From Address List */
    private List<S21MailAddress> fromAddrList = null;

    /** To Address List */
    private List<S21MailAddress> addrToList = null;

    /** Mail Template */
    private S21MailTemplate template = null;

    /** Error Message List */
    private List<String> errMsgList = new ArrayList<String>();

    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Sales Date Organization Code
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd, NSAB058001.class.getSimpleName());

        // Get Contract Branch First Organization Code
        this.contrBrFirstOrgCd = ZYPCodeDataUtil.getVarCharConstValue("CONTR_BR_FIRST_ORG_CD", this.glblCmpyCd);
        if (!hasValue(this.contrBrFirstOrgCd)) {
            throw new S21AbendException(ZZZM9006E, new String[]{"VAR_CHAR_CONST:CONTR_BR_FIRST_ORG_CD"});
        }

        // Get Contract Branch First Organization Code
        this.contrLineBizSysSrcCd = ZYPCodeDataUtil.getVarCharConstValue("CONTR_LINE_BIZ_SYS_SRC_CD", this.glblCmpyCd);
        if (!hasValue(this.contrLineBizSysSrcCd)) {
            throw new S21AbendException(ZZZM9006E, new String[]{"VAR_CHAR_CONST:CONTR_LINE_BIZ_SYS_SRC_CD"});
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        this.fromAddrList = groupFrom.getMailAddress();
        if (fromAddrList == null || fromAddrList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"From mail-address.", MAIL_GROUP_ID_FROM });
        }

        // Get To Address
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        this.addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Mail Template
        this.template = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!hasValue(template.getBody())) {
            throw new S21AbendException(NSAM0069E, new String[] {"Mailtemplate", MAIL_TEMPLATE_ID });
        }

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.totalCount = 0;
        this.errorCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatClient = S21SsmBatchClient.getClient(getClass());
    }

    @Override
    protected void mainRoutine() {

        // SVC_RG
        deleteSvcRg();
        insertUpdateSvcRg();
        // SVC_CONTR_BR
        deleteSvcContrBr();
        insertUpdateSvcContrBr();
        // SVC_RG_BR_RELN
        deleteSvcRgBrReln();
        insertUpdateSvcRgBrReln();
        // SVC_BR_RESRC_RELN
        deleteSvcBrResrcReln();
        insertUpdateSvcBrResrcReln();

        sendMail();
    }

    @Override
    protected void termRoutine() {
        this.errorCount = this.errMsgList.size();
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.totalCount - this.errorCount, this.errorCount, this.totalCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB058001().executeBatch(NSAB058001.class.getSimpleName());
    }

    private S21SsmExecutionParameter getExecParam() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execParam;
    }

    private Map<String, Object> getSsmParam(String ssmId) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);

        if (SSM_ID_GET_DS_ORG_UNIT_RG.equals(ssmId) || SSM_ID_GET_SVC_RG.equals(ssmId)) {
            ssmParam.put("firstOrgCd", this.contrBrFirstOrgCd);

        } else if (SSM_ID_GET_DS_ORG_UNIT_BR.equals(ssmId) || SSM_ID_GET_SVC_CONTR_BR.equals(ssmId)) {
            ssmParam.put("firstOrgCd", this.contrBrFirstOrgCd);

        } else if (SSM_ID_GET_DS_ORG_UNIT_RG_BR.equals(ssmId) || SSM_ID_GET_SVC_RG_BR_RELN.equals(ssmId)) {
            ssmParam.put("firstOrgCd", this.contrBrFirstOrgCd);

        } else if (SSM_ID_GET_DS_ORG_UNIT_RESRC.equals(ssmId) || SSM_ID_GET_SVC_BR_RESRC_RELN.equals(ssmId)) {
            ssmParam.put("firstOrgCd", this.contrBrFirstOrgCd);

        }
        return ssmParam;
    }

    private void insertUpdateSvcRg() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();
        Map<String, Object> ssmParam = getSsmParam(SSM_ID_GET_DS_ORG_UNIT_RG);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(SSM_ID_GET_DS_ORG_UNIT_RG, ssmParam, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {

                BigDecimal svcRgPk = getSvcRgPkByOrgCd(rs.getString("ORG_CD"));
                if (!hasValue(svcRgPk)) {
                    insertSvcRg(rs);
                    totalCount++;
                    continue;
                }
                SVC_RGTMsg inMsg = new SVC_RGTMsg();
                setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
                setValue(inMsg.svcRgPk, svcRgPk);
                inMsg = (SVC_RGTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
                if (inMsg != null) {
                    if (isUpdate(rs, inMsg)) {
                        updateSvcRg(rs, inMsg);
                        totalCount++;
                    }
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void insertSvcRg(ResultSet rs) throws SQLException {
        SVC_RGTMsg inMsg = new SVC_RGTMsg();

        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        BigDecimal svcRgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_RG_SQ);
        setValue(inMsg.svcRgPk, svcRgPk);
        setValue(inMsg.svcRgNm, truncStr(rs.getString("ORG_NM"), LEN_30));
        setValue(inMsg.svcRgDescTxt, rs.getString("ORG_DESC_TXT"));
        // START 2016/07/29 T.Tomita [QC#12534, MOD]
//        setValue(inMsg.svcLineBizCd, rs.getString("TRTY_GRP_TP_CD"));
        setValue(inMsg.svcLineBizCd, rs.getString("LINE_BIZ_TP_CD"));
        // END 2016/07/29 T.Tomita [QC#12534, MOD]
        setValue(inMsg.svcRgActvFlg, getActvFlg(rs.getString("EFF_FROM_DT"), rs.getString("EFF_THRU_DT")));
        setValue(inMsg.effFromDt, rs.getString("EFF_FROM_DT"));
        setValue(inMsg.effThruDt, rs.getString("EFF_THRU_DT"));
        setValue(inMsg.orgCd, rs.getString("ORG_CD"));

        S21FastTBLAccessor.insert(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            throw new S21AbendException(NSAM0032E, new String[] {"SVC_RG"});
        }
    }

    private void updateSvcRg(ResultSet rs, SVC_RGTMsg inMsg) throws SQLException {

        setValue(inMsg.svcRgNm, truncStr(rs.getString("ORG_NM"), LEN_30));
        setValue(inMsg.svcRgDescTxt, rs.getString("ORG_DESC_TXT"));
        // START 2016/07/29 T.Tomita [QC#12534, MOD]
//        setValue(inMsg.svcLineBizCd, rs.getString("TRTY_GRP_TP_CD"));
        setValue(inMsg.svcLineBizCd, rs.getString("LINE_BIZ_TP_CD"));
        // END 2016/07/29 T.Tomita [QC#12534, MOD]
        setValue(inMsg.svcRgActvFlg, getActvFlg(rs.getString("EFF_FROM_DT"), rs.getString("EFF_THRU_DT")));
        setValue(inMsg.effFromDt, rs.getString("EFF_FROM_DT"));
        setValue(inMsg.effThruDt, rs.getString("EFF_THRU_DT"));
        setValue(inMsg.orgCd, rs.getString("ORG_CD"));

        S21FastTBLAccessor.update(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            throw new S21AbendException(NSAM0031E, new String[] {"SVC_RG"});
        }
    }

    private void deleteSvcRg() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();
        Map<String, Object> ssmParam = getSsmParam(SSM_ID_GET_SVC_RG);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(SSM_ID_GET_SVC_RG, ssmParam, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                SVC_RGTMsg inMsg = new SVC_RGTMsg();
                setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
                setValue(inMsg.svcRgPk, rs.getBigDecimal("SVC_RG_PK"));
                inMsg = (SVC_RGTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
                if (inMsg == null) {
                    continue;
                }
                EZDTBLAccessor.logicalRemove(inMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                    throw new S21AbendException(NSAM0033E, new String[] {"SVC_RG"});
                }
                totalCount++;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void insertUpdateSvcContrBr() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();
        Map<String, Object> ssmParam = getSsmParam(SSM_ID_GET_DS_ORG_UNIT_BR);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(SSM_ID_GET_DS_ORG_UNIT_BR, ssmParam, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String svcContrBrCd = truncStr(rs.getString("ORG_NM"), LEN_3);

                SVC_CONTR_BRTMsg inMsg = new SVC_CONTR_BRTMsg();
                setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
                setValue(inMsg.svcContrBrCd, svcContrBrCd);
                inMsg = (SVC_CONTR_BRTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
                if (inMsg == null) {
                    insertSvcContrBr(rs, svcContrBrCd);
                    totalCount++;
                } else {
                    if (isUpdate(rs, inMsg)) {
                        updateSvcContrBr(rs, inMsg);
                        totalCount++;
                    }
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void insertSvcContrBr(ResultSet rs, String svcContrBrCd) throws SQLException {
        SVC_CONTR_BRTMsg inMsg = new SVC_CONTR_BRTMsg();

        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcContrBrCd, svcContrBrCd);
        setValue(inMsg.svcContrBrDescTxt, rs.getString("ORG_NM"));
        setValue(inMsg.svcContrBrAutoEstFlg, rs.getString("AUTO_EST_FLG"));
        setValue(inMsg.svcContrBrActvFlg, getActvFlg(rs.getString("EFF_FROM_DT"), rs.getString("EFF_THRU_DT")));
        setValue(inMsg.effFromDt, rs.getString("EFF_FROM_DT"));
        setValue(inMsg.effThruDt, rs.getString("EFF_THRU_DT"));
        setValue(inMsg.orgCd, rs.getString("ORG_CD"));

     // START 2016/10/17 Y.Zhang [QC#15002, MOD]
        EZDTBLAccessor.create(inMsg);
     // END 2016/10/17 Y.Zhang [QC#15002, MOD]
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            throw new S21AbendException(NSAM0032E, new String[] {"SVC_CONTR_BR"});
        }
    }

    private void updateSvcContrBr(ResultSet rs, SVC_CONTR_BRTMsg inMsg) throws SQLException {

        setValue(inMsg.svcContrBrDescTxt, rs.getString("ORG_NM"));
        setValue(inMsg.svcContrBrAutoEstFlg, rs.getString("AUTO_EST_FLG"));
        setValue(inMsg.svcContrBrActvFlg, getActvFlg(rs.getString("EFF_FROM_DT"), rs.getString("EFF_THRU_DT")));
        setValue(inMsg.effFromDt, rs.getString("EFF_FROM_DT"));
        setValue(inMsg.effThruDt, rs.getString("EFF_THRU_DT"));
        setValue(inMsg.orgCd, rs.getString("ORG_CD"));

        S21FastTBLAccessor.update(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            throw new S21AbendException(NSAM0031E, new String[] {"SVC_CONTR_BR"});
        }
    }

    private void deleteSvcContrBr() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();
        Map<String, Object> ssmParam = getSsmParam(SSM_ID_GET_SVC_CONTR_BR);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(SSM_ID_GET_SVC_CONTR_BR, ssmParam, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                SVC_CONTR_BRTMsg inMsg = new SVC_CONTR_BRTMsg();
                setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
                setValue(inMsg.svcContrBrCd, rs.getString("SVC_CONTR_BR_CD"));
                inMsg = (SVC_CONTR_BRTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
                if (inMsg == null) {
                    continue;
                }
                EZDTBLAccessor.logicalRemove(inMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                    throw new S21AbendException(NSAM0033E, new String[] {"SVC_CONTR_BR"});
                }
                totalCount++;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void insertUpdateSvcRgBrReln() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();
        Map<String, Object> ssmParam = getSsmParam(SSM_ID_GET_DS_ORG_UNIT_RG_BR);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(SSM_ID_GET_DS_ORG_UNIT_RG_BR, ssmParam, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                BigDecimal svcRgPk = rs.getBigDecimal("SVC_RG_PK");
                String svcContrBrCd = rs.getString("SVC_CONTR_BR_CD");
                if (!hasValue(svcRgPk) || !hasValue(svcContrBrCd)) {
                    continue;
                }

                BigDecimal svcRgBrRelnPk = getSvcRgBrRelnPk(svcRgPk, svcContrBrCd);
                if (svcRgBrRelnPk == null) {
                    insertSvcRgBrReln(rs);
                }
                totalCount++;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void insertSvcRgBrReln(ResultSet rs) throws SQLException {
        SVC_RG_BR_RELNTMsg inMsg = new SVC_RG_BR_RELNTMsg();

        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        BigDecimal svcRgBrRelnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_RG_BR_RELN_SQ);
        setValue(inMsg.svcRgBrRelnPk, svcRgBrRelnPk);
        setValue(inMsg.svcRgPk, rs.getBigDecimal("SVC_RG_PK"));
        setValue(inMsg.svcContrBrCd, rs.getString("SVC_CONTR_BR_CD"));
        setValue(inMsg.svcRgOrgCd, rs.getString("SVC_RG_ORG_CD"));
        setValue(inMsg.svcBrOrgCd, rs.getString("SVC_BR_ORG_CD"));

        S21FastTBLAccessor.insert(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            throw new S21AbendException(NSAM0032E, new String[] {"SVC_RG_BR_RELN"});
        }
    }

    private void deleteSvcRgBrReln() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();
        Map<String, Object> ssmParam = getSsmParam(SSM_ID_GET_SVC_RG_BR_RELN);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(SSM_ID_GET_SVC_RG_BR_RELN, ssmParam, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                SVC_RG_BR_RELNTMsg inMsg = new SVC_RG_BR_RELNTMsg();
                setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
                setValue(inMsg.svcRgBrRelnPk, rs.getBigDecimal("SVC_RG_BR_RELN_PK"));
                inMsg = (SVC_RG_BR_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
                if (inMsg == null) {
                    continue;
                }
                EZDTBLAccessor.logicalRemove(inMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                    throw new S21AbendException(NSAM0033E, new String[] {"SVC_RG_BR_RELN"});
                }
                totalCount++;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void insertUpdateSvcBrResrcReln() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();
        Map<String, Object> ssmParam = getSsmParam(SSM_ID_GET_DS_ORG_UNIT_RESRC);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(SSM_ID_GET_DS_ORG_UNIT_RESRC, ssmParam, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String orgFuncRoleTpCd = rs.getString("ORG_FUNC_ROLE_TP_CD");
                String psnCd = rs.getString("PSN_CD");
                String brCd = rs.getString("SVC_CONTR_BR_CD");

                BigDecimal svcBrResrcRelnPk = getSvcBrResrcRelnPk(orgFuncRoleTpCd, psnCd, brCd);

                SVC_BR_RESRC_RELNTMsg inMsg = new SVC_BR_RESRC_RELNTMsg();
                setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
                setValue(inMsg.svcBrResrcRelnPk, svcBrResrcRelnPk);
                inMsg = (SVC_BR_RESRC_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
                if (inMsg == null) {
                    insertSvcBrResrcReln(rs);
                    totalCount++;
                } else {
                    if (isUpdate(rs, inMsg)) {
                        updateSvcBrResrcReln(rs, inMsg);
                        totalCount++;
                    }
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void insertSvcBrResrcReln(ResultSet rs) throws SQLException {
        SVC_BR_RESRC_RELNTMsg inMsg = new SVC_BR_RESRC_RELNTMsg();

        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        BigDecimal svcBrResrcRelnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_BR_RESRC_RELN_SQ);
        setValue(inMsg.svcBrResrcRelnPk, svcBrResrcRelnPk);
        setValue(inMsg.svcContrBrCd, rs.getString("SVC_CONTR_BR_CD"));
        setValue(inMsg.psnCd, rs.getString("PSN_CD"));
        setValue(inMsg.svcOrgFuncRoleTpCd, rs.getString("ORG_FUNC_ROLE_TP_CD"));
        setValue(inMsg.svcBrResrcDefFromNm, rs.getString("ASG_CUST_FROM_NM"));
        setValue(inMsg.svcBrResrcDefThruNm, rs.getString("ASG_CUST_TO_NM"));
        setValue(inMsg.svcBrResrcActvFlg, getActvFlg(rs.getString("EFF_FROM_DT"), rs.getString("EFF_THRU_DT")));
        setValue(inMsg.effFromDt, rs.getString("EFF_FROM_DT"));
        setValue(inMsg.effThruDt, rs.getString("EFF_THRU_DT"));
        setValue(inMsg.orgCd, rs.getString("ORG_CD"));

        S21FastTBLAccessor.insert(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            throw new S21AbendException(NSAM0032E, new String[] {"SVC_BR_RESRC_RELN"});
        }
    }

    private void updateSvcBrResrcReln(ResultSet rs, SVC_BR_RESRC_RELNTMsg inMsg) throws SQLException {

        setValue(inMsg.svcContrBrCd, rs.getString("SVC_CONTR_BR_CD"));
        setValue(inMsg.svcBrResrcDefFromNm, rs.getString("ASG_CUST_FROM_NM"));
        setValue(inMsg.svcBrResrcDefThruNm, rs.getString("ASG_CUST_TO_NM"));
        setValue(inMsg.svcBrResrcActvFlg, getActvFlg(rs.getString("EFF_FROM_DT"), rs.getString("EFF_THRU_DT")));
        setValue(inMsg.effFromDt, rs.getString("EFF_FROM_DT"));
        setValue(inMsg.effThruDt, rs.getString("EFF_THRU_DT"));

        S21FastTBLAccessor.update(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            throw new S21AbendException(NSAM0031E, new String[] {"SVC_BR_RESRC_RELN"});
        }
    }

    private void deleteSvcBrResrcReln() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();
        Map<String, Object> ssmParam = getSsmParam(SSM_ID_GET_SVC_BR_RESRC_RELN);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(SSM_ID_GET_SVC_BR_RESRC_RELN, ssmParam, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                SVC_BR_RESRC_RELNTMsg inMsg = new SVC_BR_RESRC_RELNTMsg();
                setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
                setValue(inMsg.svcBrResrcRelnPk, rs.getBigDecimal("SVC_BR_RESRC_RELN_PK"));
                inMsg = (SVC_BR_RESRC_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
                if (inMsg == null) {
                    continue;
                }
                EZDTBLAccessor.logicalRemove(inMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                    throw new S21AbendException(NSAM0033E, new String[] {"SVC_BR_RESRC_RELN"});
                }
                totalCount++;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private String getActvFlg(String fromDt, String thruDt) {

        if (!hasValue(fromDt)) {
            return ZYPConstant.FLG_OFF_N;
        }
        if (!hasValue(thruDt)) {
            thruDt = this.salesDate;
        }
        if (fromDt.compareTo(this.salesDate) <= 0 && this.salesDate.compareTo(thruDt) <= 0) {
            return ZYPConstant.FLG_ON_Y;
        }
        return ZYPConstant.FLG_OFF_N;
    }

    private boolean isUpdate(ResultSet rs, SVC_RGTMsg inMsg) throws SQLException {

        if (!compare(truncStr(rs.getString("ORG_NM"), LEN_30), inMsg.svcRgNm.getValue())) {
            return true;
        } else if (!compare(rs.getString("ORG_DESC_TXT"), inMsg.svcRgDescTxt.getValue())) {
            return true;
        // START 2016/07/29 T.Tomita [QC#12534, MOD]
//        } else if (!compare(rs.getString("TRTY_GRP_TP_CD"), inMsg.svcLineBizCd.getValue())) {
        } else if (!compare(rs.getString("LINE_BIZ_TP_CD"), inMsg.svcLineBizCd.getValue())) {
        // END 2016/07/29 T.Tomita [QC#12534, MOD]
            return true;
        } else if (!compare(rs.getString("EFF_FROM_DT"), inMsg.effFromDt.getValue())) {
            return true;
        } else if (!compare(rs.getString("EFF_THRU_DT"), inMsg.effThruDt.getValue())) {
            return true;
        }
        String actvFlg = getActvFlg(rs.getString("EFF_FROM_DT"), rs.getString("EFF_THRU_DT"));
        if (!compare(actvFlg, inMsg.svcRgActvFlg.getValue())) {
            return true;
        }
        return false;
    }

    private boolean isUpdate(ResultSet rs, SVC_CONTR_BRTMsg inMsg) throws SQLException {

        if (!compare(rs.getString("ORG_NM"), inMsg.svcContrBrDescTxt.getValue())) {
            return true;
        } else if (!compare(rs.getString("AUTO_EST_FLG"), inMsg.svcContrBrAutoEstFlg.getValue())) {
            return true;
        } else if (!compare(rs.getString("EFF_FROM_DT"), inMsg.effFromDt.getValue())) {
            return true;
        } else if (!compare(rs.getString("EFF_THRU_DT"), inMsg.effThruDt.getValue())) {
            return true;
        }
        String actvFlg = getActvFlg(rs.getString("EFF_FROM_DT"), rs.getString("EFF_THRU_DT"));
        if (!compare(actvFlg, inMsg.svcContrBrActvFlg.getValue())) {
            return true;
        }
        return false;
    }

    private boolean isUpdate(ResultSet rs, SVC_BR_RESRC_RELNTMsg inMsg) throws SQLException {

        if (!compare(rs.getString("ASG_CUST_FROM_NM"), inMsg.svcBrResrcDefFromNm.getValue())) {
            return true;
        } else if (!compare(rs.getString("ASG_CUST_TO_NM"), inMsg.svcBrResrcDefThruNm.getValue())) {
            return true;
        } else if (!compare(rs.getString("EFF_FROM_DT"), inMsg.effFromDt.getValue())) {
            return true;
        } else if (!compare(rs.getString("EFF_THRU_DT"), inMsg.effThruDt.getValue())) {
            return true;
        }
        String actvFlg = getActvFlg(rs.getString("EFF_FROM_DT"), rs.getString("EFF_THRU_DT"));
        if (!compare(actvFlg, inMsg.svcBrResrcActvFlg.getValue())) {
            return true;
        }
        return false;
    }

    private boolean compare(String val1, String val2) {

        if (!hasValue(val1) && !hasValue(val2)) {
            return true;
        }
        if (!hasValue(val1)) {
            return false;
        }
        if (!hasValue(val2)) {
            return false;
        }
        return val1.equals(val2);
    }

    private String truncStr(String str, int len) {

        if (hasValue(str) && str.length() > len) {
            return str.substring(0, len);
        }
        return str;
    }

    private void sendMail() {

        if (this.errMsgList.isEmpty()) {
            return;
        }

        S21MailAddress from = null;
        if (!this.fromAddrList.isEmpty()) {
            from = this.fromAddrList.get(0);
        }

        // Create Subject and Body
        String currentTime = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);

        String newLine = System.getProperty("line.separator");
        StringBuilder msgBuf = new StringBuilder();
        for (String errMsg : this.errMsgList) {
            msgBuf.append(errMsg);
            msgBuf.append(newLine);
        }

        template.setTemplateParameter(MAIL_TEMPLATE_KEY_ID, BIZ_APP_ID);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, currentTime);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, msgBuf.toString());

        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();

        StringBuilder logBuf = new StringBuilder();
        logBuf.append(newLine);
        logBuf.append("==================== Skip or Error Data ====================");
        logBuf.append(newLine);
        logBuf.append(msgBuf);
        logBuf.append("============================================================");
        S21InfoLogOutput.println(logBuf.toString());
    }

    private BigDecimal getSvcRgPkByOrgCd(String orgCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("orgCd", orgCd);
        return (BigDecimal) ssmBatClient.queryObject("getSvcRgPkByOrgCd", ssmParam);
    }

    private BigDecimal getSvcRgBrRelnPk(BigDecimal svcRgPk, String svcContrBrCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("svcRgPk", svcRgPk);
        ssmParam.put("svcContrBrCd", svcContrBrCd);
        return (BigDecimal) ssmBatClient.queryObject("getSvcRgBrRelnPk", ssmParam);
    }

    private BigDecimal getSvcBrResrcRelnPk(String orgFuncRoleTpCd, String psnCd, String brCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("orgFuncRoleTpCd", orgFuncRoleTpCd);
        ssmParam.put("psnCd", psnCd);
        ssmParam.put("brCd", brCd);
        return (BigDecimal) ssmBatClient.queryObject("getSvcBrResrcRelnPk", ssmParam);
    }
}