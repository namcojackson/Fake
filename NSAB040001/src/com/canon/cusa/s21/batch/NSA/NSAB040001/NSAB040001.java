/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB040001;

import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.BATCH_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.CFS_QLTY_ASRN_CHK_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.CFS_SER_AUD_QLTY_ASRN_DEL_VAL;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.DS_CONTR_DTL_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.DS_CONTR_EDI_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.DS_CONTR_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.DS_CONTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.DS_CONTR_VLD_CATG_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.DS_CONTR_VLD_RSLT_MSG_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.DS_CONTR_VLD_RSLT_WRK_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.DS_CONTR_VLD_STS_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.EMAIL_PARAM_BATCH_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.EMAIL_PARAM_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.EMAIL_PARAM_MSG;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.LEASE_CMPY_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.MAIL_FROM_GROUP_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.MAIL_TO_GROUP_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.MSG_ITEM_COLON;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.MSG_ITEM_DS_CONTR_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.MSG_ITEM_SALES_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.MULTI_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.NSAB0400MULTI_CONTR_CALC_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.NSAM0032E;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.NSAM0033E;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.NSAM0045E;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.NSZM0392E;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.NSZM0543E;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.SALES_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.SVC_CONTR_BR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.TIME_FORMAT_MM_DD_YYYY_HH_MM;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.USR_VAL_1;
import static com.canon.cusa.s21.batch.NSA.NSAB040001.constant.NSAB040001Constant.ZZM9000E;
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
import business.db.CFS_SER_AUD_QLTY_ASRNTMsg;
import business.parts.NSZC057001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_EDI;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 *<pre>
 * CFS Contract Validation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/26/2016   CITS            T.Kikuhara      Create          N/A
 * 02/22/2017   Hitachi         K.Kitachi       Update          QC#17626
 * 03/26/2017   Hitachi         T.Tomita        Update          QC#18073
 * 07/31/2017   Hitachi         M.Kidokoro      Update          QC#20116
 * 09/20/2017   Hitachi         M.Kidokoro      Update          QC#21231
 * 02/02/2018   Hitachi         U.Kim           Update          QC#23687
 * 02/16/2018   CITS            M.Naito         Update          QC#24204
 *</pre>
 */
public class NSAB040001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** Insert Count */
    private int insertCount = 0;

    /** Error Count */
    private int errorCount = 0;

    // del start 2017/03/27 CSA Defect#18073
//    /** Error Message */
//    private String errMsg = null;
    // del end 2017/03/27 CSA Defect#18073

    // START 2018/02/02 U.Kim [QC#23687, ADD]
    /** User Variable1 */
    private String usrVar1;

    /** Multi Count */
    private BigDecimal multiCnt;
    // END 2018/02/02 U.Kim [QC#23687, ADD]

    /** error message list */
    private List<String> errMsgList = new ArrayList<String>();

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /**
     * @param args parameter
     */
    public static void main(String[] args) {
        new NSAB040001().executeBatch(NSAB040001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Sales date
        salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!hasValue(this.salesDate)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_SALES_DATE });
        }

        // del start 2017/03/26 CSA Defect#18073
//        // Purge Old CFS Serial Audit data
//        purgeCfsSerAud();
        // del end 2017/03/26 CSA Defect#18073

        // START 2018/02/02 U.Kim [QC#23687, ADD]
        this.usrVar1 = getUserVariable1();
        if (!hasValue(usrVar1)) {
            throw new S21AbendException(NSZM0392E, new String[] {"User Variable1"});
        }

        this.multiCnt = ZYPCodeDataUtil.getNumConstValue(NSAB0400MULTI_CONTR_CALC_CNT, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.multiCnt)) {
            this.multiCnt = BigDecimal.ONE;
        }
        // EnD 2018/02/02 U.Kim [QC#23687, ADD]
    }

    @Override
    protected void mainRoutine() {
        // add start 2017/03/26 CSA Defect#18073
        // Purge Old CFS Serial Audit data
        if (!purgeCfsSerAud()) {
            this.errorCount++;
            rollback();
            sendErrorMail();
            return;
        }
        // add end 2017/03/26 CSA Defect#18073

        // Get Contract data for check
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(GLBL_CMPY_CD, this.glblCmpyCd);
        param.put(DS_CONTR_EDI_CD, DS_CONTR_EDI.CFS);
        param.put(CFS_QLTY_ASRN_CHK_FLG, ZYPConstant.FLG_ON_Y);
        param.put(SALES_DT, this.salesDate);
        // START 2018/02/02 U.Kim [QC#23687, ADD]
        param.put(MULTI_CNT, this.multiCnt);
        param.put(USR_VAL_1, this.usrVar1);
        // END 2018/02/02 U.Kim [QC#23687, ADD]

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            // START 2017/02/22 K.Kitachi [QC#17626, MOD]
            stmt = this.ssmLLClient.createPreparedStatement("getDsContr", param, execParam);
            // END 2017/02/22 K.Kitachi [QC#17626, MOD]
            rs = stmt.executeQuery();

            // Loop for Get Contract data
            while (rs.next()) {
                String dsContrNum = rs.getString(DS_CONTR_NUM);

                // Check Contract data
                NSZC057001PMsg pmsg = new NSZC057001PMsg();
                setValue(pmsg.glblCmpyCd, this.glblCmpyCd);
                setValue(pmsg.slsDt, this.salesDate);
                setValue(pmsg.dsContrNum, dsContrNum);
                new NSZC057001().execute(pmsg, ONBATCH_TYPE.BATCH);
                if (S21ApiUtil.isXxMsgId(pmsg)) {
                    for (int i = 0; i < pmsg.xxMsgIdList.getValidCount(); i++) {
                        // mod start 2017/03/27 CSA Defect#18073
//                        String xxMsgTxt = S21MessageFunc.clspGetMessage(pmsg.xxMsgIdList.no(i).xxMsgId.getValue());
                        String xxMsgTxt = createMsgDsContrNum(dsContrNum) + S21MessageFunc.clspGetMessage(pmsg.xxMsgIdList.no(i).xxMsgId.getValue());
                        // mod end 2017/03/27 CSA Defect#18073
                        errMsgList.add(xxMsgTxt);
                    }
                    this.errorCount++;
                    rollback();
                    // del start 2017/03/27 CSA Defect#18073
//                    sendErrorMail();
//                    // Error Mail DB Insert commit
//                    commit();
                    // del end 2017/03/27 CSA Defect#18073
                    continue;
                }

                // START 2018/02/16 M.Naito [QC#24204, DEL]
//                // START 2017/02/22 K.Kitachi [QC#17626, ADD]
//                boolean isUpdErrFlg = false;
//                BigDecimal dsContrPk = rs.getBigDecimal(DS_CONTR_PK);
//                // mod start 2017/03/27 CSA Defect#18073
////                BigDecimal prntDsContrTrkPk = updDsContr(dsContrPk, rs.getString(DS_CONTR_CTRL_STS_CD), pmsg.dsContrVldRsltMsgTxt.getValue());
//                BigDecimal prntDsContrTrkPk = updDsContr(dsContrPk, rs.getString(DS_CONTR_CTRL_STS_CD), pmsg.dsContrVldRsltMsgTxt.getValue(), dsContrNum);
//                if (!hasValue(prntDsContrTrkPk)) {
//                    isUpdErrFlg = true;
//                }
//                List<DS_CONTR_DTLTMsg> dsContrDtlList = null;
//                if (!isUpdErrFlg) {
////                    dsContrDtlList = updDsContrDtl(dsContrPk, prntDsContrTrkPk, pmsg.dsContrVldRsltMsgTxt.getValue());
//                    dsContrDtlList = updDsContrDtl(dsContrPk, prntDsContrTrkPk, pmsg.dsContrVldRsltMsgTxt.getValue(), dsContrNum);
//                    if (!isUpdErrFlg && (dsContrDtlList == null || dsContrDtlList.isEmpty())) {
//                        isUpdErrFlg = true;
//                    }
//                }
//                List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrList = null;
//                if (!isUpdErrFlg) {
////                    dsContrBllgMtrList = updDsContrBllgMtr(dsContrDtlList, prntDsContrTrkPk, pmsg.dsContrVldRsltMsgTxt.getValue());
//                    dsContrBllgMtrList = updDsContrBllgMtr(dsContrDtlList, prntDsContrTrkPk, pmsg.dsContrVldRsltMsgTxt.getValue(), dsContrNum);
//                    if (!isUpdErrFlg && dsContrBllgMtrList == null) {
//                        isUpdErrFlg = true;
//                    }
//                }
//                if (!isUpdErrFlg) {
////                    if (!isUpdErrFlg && !updDsContrPrcEffBase(dsContrDtlList, prntDsContrTrkPk, pmsg.dsContrVldRsltMsgTxt.getValue())) {
//                    if (!isUpdErrFlg && !updDsContrPrcEffBase(dsContrDtlList, prntDsContrTrkPk, pmsg.dsContrVldRsltMsgTxt.getValue(), dsContrNum)) {
//                        isUpdErrFlg = true;
//                    }
//                }
//                if (!isUpdErrFlg) {
////                    if (!isUpdErrFlg && !updDsContrPrcEffUsg(dsContrBllgMtrList, dsContrPk, prntDsContrTrkPk, pmsg.dsContrVldRsltMsgTxt.getValue())) {
//                    if (!isUpdErrFlg && !updDsContrPrcEffUsg(dsContrBllgMtrList, dsContrPk, prntDsContrTrkPk, pmsg.dsContrVldRsltMsgTxt.getValue(), dsContrNum)) {
//                        isUpdErrFlg = true;
//                    }
//                }
//                if (!isUpdErrFlg) {
//                    if (!isUpdErrFlg && !createOverrideMemo(dsContrPk, dsContrNum)) {
//                        isUpdErrFlg = true;
//                    }
//                }
//                if (isUpdErrFlg) {
//                    this.errorCount++;
//                    rollback();
//                    // del start 2017/03/26 CSA Defect#18073
////                    sendErrorMail();
////                    commit();
//                    // del end 2017/03/26 CSA Defect#18073
//                    continue;
//                }
//                // END 2017/02/22 K.Kitachi [QC#17626, ADD]
                // END 2018/02/16 M.Naito [QC#24204, DEL]

                // mod start 2017/03/27 CSA Defect#18073
                // Insert CFS Serial Audit Table
//                if (!NSZC057001Constant.SUCCESS.equals(pmsg.dsContrVldRsltMsgTxt.getValue())) {
//                    if (insertCfsSerAud(dsContrNum)) {
//                        this.insertCount++;
//                    } else {
//                    if (!insertCfsSerAud(dsContrNum)) {
//                        this.errorCount++;
//                    }
//                }
                if (!insertCfsSerAud(dsContrNum)) {
                    this.errorCount++;
                    rollback();
                    continue;
                }
                // mod end 2017/03/27 CSA Defect#18073

                // mod start 2017/03/26 CSA Defect#18073
                // Delete Valid Result Work
                // START 2018/02/27 U.Kim [QC#23687, DEL]
//                if (!deleteValidRsltWk(dsContrNum)) {
//                    this.errorCount++;
//                    rollback();
//                    continue;
//                }
                // END 2018/02/27 U.Kim [QC#23687, DEL]
                // mod end 2017/03/26 CSA Defect#18073

                // add start 2017/03/27 CSA Defect#18073
                this.insertCount++;
                // add end 2017/03/27 CSA Defect#18073
                commit();

            }
            // add start 2017/03/27 CSA Defect#18073
            sendErrorMail();
            // add end 2017/03/27 CSA Defect#18073

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.insertCount, this.errorCount);
    }

    // mod start 2017/03/26 CSA Defect#18073
    /**
     * Purge Old CFS Serial Audit data
     */
    private boolean purgeCfsSerAud() {

        BigDecimal delValConst = ZYPCodeDataUtil.getNumConstValue(CFS_SER_AUD_QLTY_ASRN_DEL_VAL, glblCmpyCd);
        if (!hasValue(delValConst)) {
            throw new S21AbendException(NSZM0543E, new String[] {CFS_SER_AUD_QLTY_ASRN_DEL_VAL });
        }
        int delVal = delValConst.intValue();
        String delDate = ZYPDateUtil.addDays(salesDate, -delVal);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(SALES_DT, delDate);
        List<BigDecimal> purgeList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getCfsSerAudPkForPurge", queryParam);
        for (BigDecimal purgePk : purgeList) {
            CFS_SER_AUD_QLTY_ASRNTMsg tMsg = new CFS_SER_AUD_QLTY_ASRNTMsg();
            setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(tMsg.cfsSerAudQltyAsrnPk, purgePk);
            EZDTBLAccessor.remove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
//                errMsg = S21MessageFunc.clspGetMessage(NSAM0033E, new String[] {tMsg.getTableName() });
//                rollback();
//                sendErrorMail();
                String xxMsgTxt = S21MessageFunc.clspGetMessage(NSAM0033E, new String[] {tMsg.getTableName() });
                this.errMsgList.add(xxMsgTxt);
                return false;
            }
        }
        return true;
    }
    // mod end 2017/03/26 CSA Defect#18073

    /**
     * Insert CFS Serial Audit Table
     */
    private boolean insertCfsSerAud(String dsContrNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(DS_CONTR_NUM, dsContrNum);
        List<String> dsContrVldStsList = new ArrayList<String>();
        dsContrVldStsList.add(DS_CONTR_VLD_STS.SUCCESS);
        dsContrVldStsList.add(DS_CONTR_VLD_STS.NOT_VALIDATED);
        queryParam.put(DS_CONTR_VLD_STS_CD, dsContrVldStsList);
        queryParam.put(DS_CONTR_VLD_CATG_CD, DS_CONTR_VLD_CATG.CFS_INSOURCE);
        Map<String, Object> insMap = (Map<String, Object>) ssmBatchClient.queryObject("getCfsSerAud", queryParam);
        if (insMap == null) {
            // mod start 2017/03/26 CSA Defect#18073
//            return false;
            return true;
            // mod end 2017/03/26 CSA Defect#18073
        }

        CFS_SER_AUD_QLTY_ASRNTMsg tMsg = new CFS_SER_AUD_QLTY_ASRNTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.cfsSerAudQltyAsrnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CFS_SER_AUD_QLTY_ASRN_SQ));
        setValue(tMsg.serNum, (String) insMap.get(SER_NUM));
        setValue(tMsg.dsContrNum, dsContrNum);
        setValue(tMsg.dsContrPk, (BigDecimal) insMap.get(DS_CONTR_PK));
        setValue(tMsg.dsContrDtlPk, (BigDecimal) insMap.get(DS_CONTR_DTL_PK));
        setValue(tMsg.dsContrVldRsltWrkPk, (BigDecimal) insMap.get(DS_CONTR_VLD_RSLT_WRK_PK));
        setValue(tMsg.leaseDlrCd, (String) insMap.get(LEASE_CMPY_CD));
        setValue(tMsg.svcContrBrCd, (String) insMap.get(SVC_CONTR_BR_CD));
        setValue(tMsg.dsContrCatgCd, (String) insMap.get(DS_CONTR_VLD_CATG_CD));
        setValue(tMsg.dsContrVldRsltMsgTxt, (String) insMap.get(DS_CONTR_VLD_RSLT_MSG_TXT));
        setValue(tMsg.cratDt, salesDate);

        EZDTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            // mod start 2017/03/26 CSA Defect#18073
//            errMsg = S21MessageFunc.clspGetMessage(NSAM0032E, new String[] {tMsg.getTableName() });
//            sendErrorMail();
            String xxMsgTxt = createMsgDsContrNum(dsContrNum) + S21MessageFunc.clspGetMessage(NSAM0032E, new String[] {tMsg.getTableName() });
            this.errMsgList.add(xxMsgTxt);
            // mod end 2017/03/26 CSA Defect#18073
            return false;
        }

        return true;

    }

    // mod start 2017/03/26 CSA Defect#18073
    // START 2018/02/27 U.Kim [QC#23687, DEL]
//    /**
//     * Delete Valid Result Work
//     */
//    private boolean deleteValidRsltWk(String dsContrNum) {
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put(GLBL_CMPY_CD, glblCmpyCd);
//        queryParam.put(DS_CONTR_NUM, dsContrNum);
//        queryParam.put(DS_CONTR_VLD_CATG_CD, DS_CONTR_VLD_CATG.CFS_INSOURCE);
//        List<BigDecimal> delPkList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getDsConVldRsltWkPkForDel", queryParam);
//
//        for (BigDecimal delPk : delPkList) {
//            DS_CONTR_VLD_RSLT_WRKTMsg tMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
//            setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
//            setValue(tMsg.dsContrVldRsltWrkPk, delPk);
//            EZDTBLAccessor.remove(tMsg);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
////                errMsg = S21MessageFunc.clspGetMessage(NSAM0033E, new String[] {tMsg.getTableName() });
////                sendErrorMail();
//                String xxMsgTxt = createMsgDsContrNum(dsContrNum) + S21MessageFunc.clspGetMessage(NSAM0033E, new String[] {tMsg.getTableName() });
//                this.errMsgList.add(xxMsgTxt);
//                return false;
//            }
//        }
//        return true;
//    }
    // END 2018/02/27 U.Kim [QC#23687, DEL]
    // mod end 2017/03/26 CSA Defect#18073

    /**
     * Error Process
     */
    private void sendErrorMail() {
        // add start 2017/03/27 CSA Defect#18073
        if (this.errMsgList.size() == 0) {
            return;
        }
        String errMsg = createEmailParamMsg();
        // add end 2017/03/27 CSA Defect#18073

        // Get Mail Address From
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_FROM_GROUP_ID);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        S21MailAddress addrFrom = null;
        if (!addrFromList.isEmpty()) {
            addrFrom = addrFromList.get(0);
        }

        // Get Mail Address To
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_TO_GROUP_ID);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList.isEmpty()) {
            throw new S21AbendException(NSAM0045E, new String[] {MAIL_TO_GROUP_ID });
        }

        // Get Mail Subject and Body
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!hasValue(template.getBody())) {
            throw new S21AbendException(NSAM0045E, new String[] {MAIL_TEMPLATE_ID });
        }
        template.setTemplateParameter(EMAIL_PARAM_BATCH_ID, BATCH_ID);
        String currentTime = ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT_MM_DD_YYYY_HH_MM);
        template.setTemplateParameter(EMAIL_PARAM_DATE, currentTime);
        template.setTemplateParameter(EMAIL_PARAM_MSG, errMsg);

        // Call Mail API
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(addrFrom);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();

        return;

    }

    // START 2018/02/16 M.Naito [QC#24204, DEL]
//    // mod start 2017/03/27 CSA Defect#18073
//    // START 2017/02/22 K.Kitachi [QC#17626, ADD]
//    private BigDecimal updDsContr(BigDecimal dsContrPk, String dsContrCtrlStsCd, String dsContrVldRsltMsgTxt, String dsContrNum) {
//        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
//        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
//        setValue(inMsg.dsContrPk, dsContrPk);
//
//        inMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
//        if (inMsg == null) {
////            this.errMsg = S21MessageFunc.clspGetMessage(NZZM0003E);
//            String xxMsgTxt = createMsgDsContrNum(dsContrNum) + S21MessageFunc.clspGetMessage(NZZM0003E);
//            this.errMsgList.add(xxMsgTxt);
//            return null;
//        }
//
//        if (NSZC057001Constant.SUCCESS.equals(dsContrVldRsltMsgTxt)) {
//            // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
////            if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd)) {
//            if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.ENTERED.equals(dsContrCtrlStsCd)) {
//            // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
//                if (this.salesDate.compareTo(inMsg.contrVrsnEffFromDt.getValue()) >= 0) {
//                    setValue(inMsg.dsContrStsCd, DS_CONTR_STS.EFFECTIVE);
//                } else {
//                    setValue(inMsg.dsContrStsCd, DS_CONTR_STS.APPROVED);
//                }
//            } else {
//                setValue(inMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
//            }
//        } else {
//            setValue(inMsg.qltyAsrnHldFlg, ZYPConstant.FLG_ON_Y);
//            // START 2017/07/31 M.Kidokoro [QC#20116, ADD]
//            // START 2017/09/20 M.Kidokoro [QC#21231, MOD]
////            if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd)) {
//            if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.ENTERED.equals(dsContrCtrlStsCd)) {
//            // END 2017/09/20 M.Kidokoro [QC#21231, MOD]
//                setValue(inMsg.dsContrStsCd, DS_CONTR_STS.ENTERED);
//                setValue(inMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
//            }
//            // END 2017/07/31 M.Kidokoro [QC#20116, ADD]
//        }
//
//        S21FastTBLAccessor.update(inMsg);
//        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
////            this.errMsg = S21MessageFunc.clspGetMessage(NSAM0031E, new String[] {inMsg.getTableName() });
//            String xxMsgTxt = createMsgDsContrNum(dsContrNum) + S21MessageFunc.clspGetMessage(NSAM0031E, new String[] {inMsg.getTableName() });
//            this.errMsgList.add(xxMsgTxt);
//            return null;
//        }
//
//        // call contract tracking api
//        NSZC077001PMsg pMsg = new NSZC077001PMsg();
//        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
//        setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
//        setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.CONTRACT_HEADER);
//        setValue(pMsg.dsContrPk, dsContrPk);
//        setValue(pMsg.stsMemoRsnCd, DS_CONTR_TRK_RSN.COMPLETE_CONTRACT);
//        setValue(pMsg.stsMemoUpdPsnCd, BIZ_APP_ID);
//
//        new NSZC077001().execute(pMsg, ONBATCH_TYPE.BATCH);
//
//        if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
//            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
//            S21ApiMessage msg = msgList.get(0);
////            this.errMsg = S21MessageFunc.clspGetMessage(msg.getXxMsgid(), msg.getXxMsgPrmArray());
//            String xxMsgTxt = createMsgDsContrNum(dsContrNum) + createMsgDsContrNum(dsContrNum) + S21MessageFunc.clspGetMessage(msg.getXxMsgid(), msg.getXxMsgPrmArray());
//            this.errMsgList.add(xxMsgTxt);
//            return null;
//        }
//
//        return pMsg.prntDsContrTrkPk.getValue();
//    }
//
//    private List<DS_CONTR_DTLTMsg> updDsContrDtl(BigDecimal dsContrPk, BigDecimal prntDsContrTrkPk, String dsContrVldRsltMsgTxt, String dsContrNum) {
//
//        List<Map<String, Object>> dsContrDtlList = getUpdDsContrDtl(dsContrPk);
//
//        List<DS_CONTR_DTLTMsg> updList = new ArrayList<DS_CONTR_DTLTMsg>(dsContrDtlList.size());
//        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>(dsContrDtlList.size());
//        for (Map<String, Object> dsContrDtl : dsContrDtlList) {
//            DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
//            setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
//            setValue(inMsg.dsContrDtlPk, (BigDecimal) dsContrDtl.get("DS_CONTR_DTL_PK"));
//
//            inMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
//            if (inMsg == null) {
////                this.errMsg = S21MessageFunc.clspGetMessage(NZZM0003E);
//                String xxMsgTxt = createMsgDsContrNum(dsContrNum) + S21MessageFunc.clspGetMessage(NZZM0003E);
//                this.errMsgList.add(xxMsgTxt);
//                return null;
//            }
//            // START 2017/07/31 M.Kidokoro [QC#20116, ADD]
//            String dsContrCtrlStsCd = (String) dsContrDtl.get("DS_CONTR_CTRL_STS_CD");
//            // END 2017/07/31 M.Kidokoro [QC#20116, ADD]
//            if (NSZC057001Constant.SUCCESS.equals(dsContrVldRsltMsgTxt)) {
//                // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
////                if (DS_CONTR_CTRL_STS.DRAFT.equals((String) dsContrDtl.get("DS_CONTR_CTRL_STS_CD"))) {
//                if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.ENTERED.equals(dsContrCtrlStsCd)) {
//                // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
//                    setValue(inMsg.dsContrDtlStsCd, getDsContrDtlStsCd(inMsg.contrEffFromDt.getValue()));
//                } else {
//                    setValue(inMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
//                }
//            } else {
//                setValue(inMsg.qltyAsrnHldFlg, ZYPConstant.FLG_ON_Y);
//                // START 2017/07/31 M.Kidokoro [QC#20116, ADD]
//                // START 2017/09/20 M.Kidokoro [QC#21231, MOD]
////                if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd)) {
//                if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.ENTERED.equals(dsContrCtrlStsCd)) {
//                // END 2017/09/20 M.Kidokoro [QC#21231, MOD]
//                    setValue(inMsg.dsContrDtlStsCd, DS_CONTR_DTL_STS.SUBMITED);
//                    setValue(inMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
//                }
//                // END 2017/07/31 M.Kidokoro [QC#20116, ADD]
//            }
//            updList.add(inMsg);
//
//            NSZC077001PMsg pMsg = new NSZC077001PMsg();
//            setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
//            setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
//            setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE);
//            setValue(pMsg.dsContrPk, dsContrPk);
//            setValue(pMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
//            setValue(pMsg.prntDsContrTrkPk, prntDsContrTrkPk);
//            setValue(pMsg.stsMemoUpdPsnCd, BIZ_APP_ID);
//            trkList.add(pMsg);
//        }
//
//        if (!updList.isEmpty()) {
//            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_DTLTMsg[0]));
//            if (updCnt != updList.size()) {
////                this.errMsg = S21MessageFunc.clspGetMessage(NSAM0031E, new String[] {new DS_CONTRTMsg().getTableName() });
//                String xxMsgTxt = createMsgDsContrNum(dsContrNum) + S21MessageFunc.clspGetMessage(NSAM0031E, new String[] {new DS_CONTR_DTLTMsg().getTableName() });
//                this.errMsgList.add(xxMsgTxt);
//                return null;
//            }
//        }
//
//        // call contract tracking api
//        new NSZC077001().execute(trkList, ONBATCH_TYPE.BATCH);
//
//        for (NSZC077001PMsg pMsg : trkList) {
//            if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
//                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
//                S21ApiMessage msg = msgList.get(0);
////                this.errMsg = S21MessageFunc.clspGetMessage(msg.getXxMsgid(), msg.getXxMsgPrmArray());
//                String xxMsgTxt = createMsgDsContrNum(dsContrNum) + S21MessageFunc.clspGetMessage(msg.getXxMsgid(), msg.getXxMsgPrmArray());
//                this.errMsgList.add(xxMsgTxt);
//                return null;
//            }
//        }
//
//        return updList;
//    }
//
//    private List<DS_CONTR_BLLG_MTRTMsg> updDsContrBllgMtr(List<DS_CONTR_DTLTMsg> dsContrDtlList, BigDecimal prntDsContrTrkPk, String dsContrVldRsltMsgTxt, String dsContrNum) {
//
//        List<DS_CONTR_BLLG_MTRTMsg> updList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
//        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>();
//
//        for (DS_CONTR_DTLTMsg dsContrDtl : dsContrDtlList) {
//            List<Map<String, Object>> dsContrBllgMtrList = getUpdDsContrBllgMtr(dsContrDtl.dsContrDtlPk.getValue());
//
//            for (Map<String, Object> dsContrBllgMtr : dsContrBllgMtrList) {
//                DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
//                setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
//                setValue(inMsg.dsContrBllgMtrPk, (BigDecimal) dsContrBllgMtr.get("DS_CONTR_BLLG_MTR_PK"));
//
//                inMsg = (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
//                if (inMsg == null) {
////                    this.errMsg = S21MessageFunc.clspGetMessage(NZZM0003E);
//                    String xxMsgTxt = createMsgDsContrNum(dsContrNum) + S21MessageFunc.clspGetMessage(NZZM0003E);
//                    this.errMsgList.add(xxMsgTxt);
//                    return null;
//                }
//                // START 2017/07/31 M.Kidokoro [QC#20116, ADD]
//                String dsContrCtrlStsCd = (String) dsContrBllgMtr.get("DS_CONTR_CTRL_STS_CD");
//                // END 2017/07/31 M.Kidokoro [QC#20116, ADD]
//                if (NSZC057001Constant.SUCCESS.equals(dsContrVldRsltMsgTxt)) {
//                    // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
////                    if (DS_CONTR_CTRL_STS.DRAFT.equals((String) dsContrBllgMtr.get("DS_CONTR_CTRL_STS_CD"))) {
//                    if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.ENTERED.equals(dsContrCtrlStsCd)) {
//                    // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
//                        setValue(inMsg.dsContrBllgMtrStsCd, getDsContrDtlStsCd(dsContrDtl.contrEffFromDt.getValue()));
//                    } else {
//                        setValue(inMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
//                    }
//                } else {
//                    setValue(inMsg.qltyAsrnHldFlg, ZYPConstant.FLG_ON_Y);
//                    // START 2017/07/31 M.Kidokoro [QC#20116, ADD]
//                    // START 2017/09/20 M.Kidokoro [QC#21231, MOD]
////                    if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd)) {
//                    if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.ENTERED.equals(dsContrCtrlStsCd)) {
//                    // END 2017/09/20 M.Kidokoro [QC#21231, MOD]
//                        setValue(inMsg.dsContrBllgMtrStsCd, DS_CONTR_DTL_STS.SUBMITED);
//                        setValue(inMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
//                    }
//                    // END 2017/07/31 M.Kidokoro [QC#20116, ADD]
//                }
//                updList.add(inMsg);
//
//                NSZC077001PMsg pMsg = new NSZC077001PMsg();
//                setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
//                setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
//                setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE);
//                setValue(pMsg.dsContrPk, dsContrDtl.dsContrPk);
//                setValue(pMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
//                setValue(pMsg.dsContrBllgMtrPk, inMsg.dsContrBllgMtrPk);
//                setValue(pMsg.prntDsContrTrkPk, prntDsContrTrkPk);
//                setValue(pMsg.stsMemoUpdPsnCd, BIZ_APP_ID);
//                trkList.add(pMsg);
//            }
//        }
//
//        if (!updList.isEmpty()) {
//            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_BLLG_MTRTMsg[0]));
//            if (updCnt != updList.size()) {
////                this.errMsg = S21MessageFunc.clspGetMessage(NSAM0031E, new String[] {new DS_CONTR_BLLG_MTRTMsg().getTableName() });
//                String xxMsgTxt = createMsgDsContrNum(dsContrNum) + S21MessageFunc.clspGetMessage(NSAM0031E, new String[] {new DS_CONTR_BLLG_MTRTMsg().getTableName() });
//                this.errMsgList.add(xxMsgTxt);
//                return null;
//            }
//        }
//
//        // call contract tracking api
//        new NSZC077001().execute(trkList, ONBATCH_TYPE.BATCH);
//
//        for (NSZC077001PMsg pMsg : trkList) {
//            if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
//                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
//                S21ApiMessage msg = msgList.get(0);
////                this.errMsg = S21MessageFunc.clspGetMessage(msg.getXxMsgid(), msg.getXxMsgPrmArray());
//                String xxMsgTxt = createMsgDsContrNum(dsContrNum) + S21MessageFunc.clspGetMessage(msg.getXxMsgid(), msg.getXxMsgPrmArray());
//                this.errMsgList.add(xxMsgTxt);
//                return null;
//            }
//        }
//
//        return updList;
//    }
//
//    private boolean updDsContrPrcEffBase(List<DS_CONTR_DTLTMsg> dsContrDtlList, BigDecimal prntDsContrTrkPk, String dsContrVldRsltMsgTxt, String dsContrNum) {
//
//        List<DS_CONTR_PRC_EFFTMsg> updList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();
//        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>();
//
//        for (DS_CONTR_DTLTMsg dsContrDtl : dsContrDtlList) {
//            List<Map<String, Object>> dsContrPrcEffList = getUpdDsContrPrcEffBase(dsContrDtl.dsContrDtlPk.getValue());
//
//            for (Map<String, Object> dsContrPrcEff : dsContrPrcEffList) {
//                DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
//                setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
//                setValue(inMsg.dsContrPrcEffPk, (BigDecimal) dsContrPrcEff.get("DS_CONTR_PRC_EFF_PK"));
//
//                inMsg = (DS_CONTR_PRC_EFFTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
//                if (inMsg == null) {
////                    this.errMsg = S21MessageFunc.clspGetMessage(NZZM0003E);
//                    String xxMsgTxt = createMsgDsContrNum(dsContrNum) + S21MessageFunc.clspGetMessage(NZZM0003E);
//                    this.errMsgList.add(xxMsgTxt);
//                    return false;
//                }
//                // START 2017/07/31 M.Kidokoro [QC#20116, ADD]
//                String dsContrCtrlStsCd = (String) dsContrPrcEff.get("DS_CONTR_CTRL_STS_CD");
//                // END 2017/07/31 M.Kidokoro [QC#20116, ADD]
//                if (NSZC057001Constant.SUCCESS.equals(dsContrVldRsltMsgTxt)) {
//                    // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
////                    if (DS_CONTR_CTRL_STS.DRAFT.equals((String) dsContrPrcEff.get("DS_CONTR_CTRL_STS_CD"))) {
//                    if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.ENTERED.equals(dsContrCtrlStsCd)) {
//                    // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
//                        setValue(inMsg.dsContrPrcEffStsCd, getDsContrDtlStsCd(dsContrDtl.contrEffFromDt.getValue()));
//                    } else {
//                        setValue(inMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
//                    }
//                } else {
//                    setValue(inMsg.qltyAsrnHldFlg, ZYPConstant.FLG_ON_Y);
//                    // START 2017/07/31 M.Kidokoro [QC#20116, ADD]
//                    // START 2017/09/20 M.Kidokoro [QC#21231, MOD]
////                    if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd)) {
//                    if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.ENTERED.equals(dsContrCtrlStsCd)) {
//                    // END 2017/09/20 M.Kidokoro [QC#21231, MOD]
//                        setValue(inMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.SUBMITED);
//                        setValue(inMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
//                    }
//                    // END 2017/07/31 M.Kidokoro [QC#20116, ADD]
//                }
//                updList.add(inMsg);
//
//                NSZC077001PMsg pMsg = new NSZC077001PMsg();
//                setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
//                setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
//                setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE_PE);
//                setValue(pMsg.dsContrPk, dsContrDtl.dsContrPk);
//                setValue(pMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
//                setValue(pMsg.dsContrPrcEffPk, inMsg.dsContrPrcEffPk);
//                setValue(pMsg.contrPrcEffFromDt, inMsg.contrPrcEffFromDt);
//                setValue(pMsg.contrPrcEffThruDt, inMsg.contrPrcEffThruDt);
//                setValue(pMsg.prntDsContrTrkPk, prntDsContrTrkPk);
//                setValue(pMsg.stsMemoUpdPsnCd, BIZ_APP_ID);
//                trkList.add(pMsg);
//            }
//        }
//
//        if (!updList.isEmpty()) {
//            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_PRC_EFFTMsg[0]));
//            if (updCnt != updList.size()) {
////                this.errMsg = S21MessageFunc.clspGetMessage(NSAM0031E, new String[] {new DS_CONTR_PRC_EFFTMsg().getTableName() });
//                String xxMsgTxt = createMsgDsContrNum(dsContrNum) + S21MessageFunc.clspGetMessage(NSAM0031E, new String[] {new DS_CONTR_PRC_EFFTMsg().getTableName() });
//                this.errMsgList.add(xxMsgTxt);
//                return false;
//            }
//        }
//
//        // call contract tracking api
//        new NSZC077001().execute(trkList, ONBATCH_TYPE.BATCH);
//
//        for (NSZC077001PMsg pMsg : trkList) {
//            if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
//                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
//                S21ApiMessage msg = msgList.get(0);
////                this.errMsg = S21MessageFunc.clspGetMessage(msg.getXxMsgid(), msg.getXxMsgPrmArray());
//                String xxMsgTxt = createMsgDsContrNum(dsContrNum) + S21MessageFunc.clspGetMessage(msg.getXxMsgid(), msg.getXxMsgPrmArray());
//                this.errMsgList.add(xxMsgTxt);
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    private boolean updDsContrPrcEffUsg(List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrList, BigDecimal dsContrPk, BigDecimal prntDsContrTrkPk, String dsContrVldRsltMsgTxt, String dsContrNum) {
//
//        List<DS_CONTR_PRC_EFFTMsg> updList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();
//        List<NSZC077001PMsg> trkList = new ArrayList<NSZC077001PMsg>();
//
//        for (DS_CONTR_BLLG_MTRTMsg dsContrBllgMtr : dsContrBllgMtrList) {
//            List<Map<String, Object>> dsContrPrcEffList = getUpdDsContrPrcEffUsg(dsContrBllgMtr.dsContrBllgMtrPk.getValue());
//
//            for (Map<String, Object> dsContrPrcEff : dsContrPrcEffList) {
//                DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
//                setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
//                setValue(inMsg.dsContrPrcEffPk, (BigDecimal) dsContrPrcEff.get("DS_CONTR_PRC_EFF_PK"));
//
//                inMsg = (DS_CONTR_PRC_EFFTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
//                if (inMsg == null) {
////                    this.errMsg = S21MessageFunc.clspGetMessage(NZZM0003E);
//                    String xxMsgTxt = createMsgDsContrNum(dsContrNum) + S21MessageFunc.clspGetMessage(NZZM0003E);
//                    this.errMsgList.add(xxMsgTxt);
//                    return false;
//                }
//                // START 2017/07/31 M.Kidokoro [QC#20116, ADD]
//                String dsContrCtrlStsCd = (String) dsContrPrcEff.get("DS_CONTR_CTRL_STS_CD");
//                // END 2017/07/31 M.Kidokoro [QC#20116, ADD]
//                if (NSZC057001Constant.SUCCESS.equals(dsContrVldRsltMsgTxt)) {
//                    // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
////                    if (DS_CONTR_CTRL_STS.DRAFT.equals((String) dsContrPrcEff.get("DS_CONTR_CTRL_STS_CD"))) {
//                    if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.ENTERED.equals(dsContrCtrlStsCd)) {
//                    // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
//                        setValue(inMsg.dsContrPrcEffStsCd, getDsContrDtlStsCd(inMsg.contrPrcEffFromDt.getValue()));
//                    } else {
//                        setValue(inMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
//                    }
//                } else {
//                    setValue(inMsg.qltyAsrnHldFlg, ZYPConstant.FLG_ON_Y);
//                    // START 2017/07/31 M.Kidokoro [QC#20116, ADD]
//                    // START 2017/09/20 M.Kidokoro [QC#21231, MOD]
////                    if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd)) {
//                    if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.ENTERED.equals(dsContrCtrlStsCd)) {
//                    // END 2017/09/20 M.Kidokoro [QC#21231, MOD]
//                        setValue(inMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.SUBMITED);
//                        setValue(inMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
//                    }
//                    // END 2017/07/31 M.Kidokoro [QC#20116, ADD]
//                }
//                updList.add(inMsg);
//
//                NSZC077001PMsg pMsg = new NSZC077001PMsg();
//                setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
//                setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
//                setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE_PE);
//                setValue(pMsg.dsContrPk, dsContrPk);
//                setValue(pMsg.dsContrDtlPk, inMsg.dsContrDtlPk);
//                setValue(pMsg.dsContrBllgMtrPk, inMsg.dsContrBllgMtrPk);
//                setValue(pMsg.dsContrPrcEffPk, inMsg.dsContrPrcEffPk);
//                setValue(pMsg.contrPrcEffFromDt, inMsg.contrPrcEffFromDt);
//                setValue(pMsg.contrPrcEffThruDt, inMsg.contrPrcEffThruDt);
//                setValue(pMsg.prntDsContrTrkPk, prntDsContrTrkPk);
//                setValue(pMsg.stsMemoUpdPsnCd, BIZ_APP_ID);
//                trkList.add(pMsg);
//            }
//        }
//
//        if (!updList.isEmpty()) {
//            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_PRC_EFFTMsg[0]));
//            if (updCnt != updList.size()) {
////                this.errMsg = S21MessageFunc.clspGetMessage(NSAM0031E, new String[] {new DS_CONTR_PRC_EFFTMsg().getTableName() });
//                String xxMsgTxt = createMsgDsContrNum(dsContrNum) + S21MessageFunc.clspGetMessage(NSAM0031E, new String[] {new DS_CONTR_PRC_EFFTMsg().getTableName() });
//                this.errMsgList.add(xxMsgTxt);
//                return false;
//            }
//        }
//
//        // call contract tracking api
//        new NSZC077001().execute(trkList, ONBATCH_TYPE.BATCH);
//
//        for (NSZC077001PMsg pMsg : trkList) {
//            if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
//                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
//                S21ApiMessage msg = msgList.get(0);
////                this.errMsg = S21MessageFunc.clspGetMessage(msg.getXxMsgid(), msg.getXxMsgPrmArray());
//                String xxMsgTxt = createMsgDsContrNum(dsContrNum) + S21MessageFunc.clspGetMessage(msg.getXxMsgid(), msg.getXxMsgPrmArray());
//                this.errMsgList.add(xxMsgTxt);
//                return false;
//            }
//        }
//
//        return true;
//    }
//    // mod end 2017/03/27 CSA Defect#18073
//
//    private boolean createOverrideMemo(BigDecimal dsContrPk, String dsContrNum) {
//
//        StringBuilder sb = new StringBuilder();
//        String separtor = System.getProperty("line.separator");
//
//        sb.append("These validation results have been overridden as success by ");
//        sb.append(BATCH_ID);
//        sb.append(" on ");
//        sb.append(this.salesDate);
//        sb.append(separtor);
//
//        List<Map<String, String>> errVldRsltList = findErrContrVldRslt(dsContrNum);
//
//        for (Map<String, String> errVldRslt : errVldRsltList) {
//
//            sb.append("Serial#:");
//            sb.append(errVldRslt.get("SER_NUM"));
//            sb.append("  Validation Name:");
//            sb.append(errVldRslt.get("DS_CONTR_VLD_NM"));
//            sb.append("  Result:");
//            sb.append(errVldRslt.get("DS_CONTR_VLD_STS_DESC_TXT"));
//            sb.append(separtor);
//
//        }
//
//        SVC_MEMOTMsg inMsg = new SVC_MEMOTMsg();
//        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
//        setValue(inMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_MEMO_SQ));
//        setValue(inMsg.dsContrPk, dsContrPk);
//        setValue(inMsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
//        setValue(inMsg.svcMemoTpCd, SVC_MEMO_TP.OVERRIDE_CONTRACT_VALIDATION);
//        String svcMemoRsnCd = ZYPCodeDataUtil.getVarCharConstValue(SVC_MEMO_RSN_FOR_CPLT_CONTR, this.glblCmpyCd);
//        setValue(inMsg.svcMemoRsnCd, svcMemoRsnCd);
//
//        if (sb.length() > SVC_MEMO_SIZE) {
//            setValue(inMsg.svcCmntTxt, sb.substring(0, SVC_MEMO_SIZE));
//        } else {
//            setValue(inMsg.svcCmntTxt, sb.toString());
//        }
//
//        S21FastTBLAccessor.insert(inMsg);
//        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
//            // mod start 2017/03/27 CSA Defect#18073
////            this.errMsg = S21MessageFunc.clspGetMessage(NSAM0032E, new String[] {inMsg.getTableName() });
//            String xxMsgTxt = createMsgDsContrNum(dsContrNum) + S21MessageFunc.clspGetMessage(NSAM0032E, new String[] {inMsg.getTableName() });
//            this.errMsgList.add(xxMsgTxt);
//            // mod end 2017/03/27 CSA Defect#18073
//            return false;
//        }
//        return true;
//    }
//
//    private String getDsContrDtlStsCd(String effFromDt) {
//
//        String dsContrDtlStsCd = "";
//
//        if (this.salesDate.compareTo(effFromDt) >= 0) {
//            dsContrDtlStsCd = DS_CONTR_DTL_STS.ACTIVE;
//        } else {
//            dsContrDtlStsCd = DS_CONTR_DTL_STS.SIGNED;
//        }
//        return dsContrDtlStsCd;
//    }
//
//    private List<Map<String, Object>> getUpdDsContrDtl(BigDecimal dsContrPk) {
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put("glblCmpyCd", this.glblCmpyCd);
//        queryParam.put("dsContrPk", dsContrPk);
//        queryParam.put("cfsQltyAsrnChkFlg", ZYPConstant.FLG_ON_Y);
//
//        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getUpdDsContrDtl", queryParam);
//    }
//
//    private List<Map<String, Object>> getUpdDsContrBllgMtr(BigDecimal dsContrDtlPk) {
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put("glblCmpyCd", this.glblCmpyCd);
//        queryParam.put("dsContrDtlPk", dsContrDtlPk);
//        queryParam.put("cfsQltyAsrnChkFlg", ZYPConstant.FLG_ON_Y);
//
//        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getUpdDsContrBllgMtr", queryParam);
//    }
//
//    private List<Map<String, Object>> getUpdDsContrPrcEffBase(BigDecimal dsContrDtlPk) {
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put("glblCmpyCd", this.glblCmpyCd);
//        queryParam.put("dsContrDtlPk", dsContrDtlPk);
//        queryParam.put("cfsQltyAsrnChkFlg", ZYPConstant.FLG_ON_Y);
//
//        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getUpdDsContrPrcEffBase", queryParam);
//    }
//
//    private List<Map<String, Object>> getUpdDsContrPrcEffUsg(BigDecimal dsContrBllgMtrPk) {
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put("glblCmpyCd", this.glblCmpyCd);
//        queryParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
//        queryParam.put("cfsQltyAsrnChkFlg", ZYPConstant.FLG_ON_Y);
//
//        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getUpdDsContrPrcEffUsg", queryParam);
//    }
//
//    private List<Map<String, String>> findErrContrVldRslt(String dsContrNum) {
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put("glblCmpyCd", this.glblCmpyCd);
//        queryParam.put("dsContrNum", dsContrNum);
//
//        Set<String> vldStsSet = new LinkedHashSet<String>();
//        vldStsSet.add(DS_CONTR_VLD_STS.ERROR);
//        vldStsSet.add(DS_CONTR_VLD_STS.NOT_VALIDATED);
//
//        queryParam.put("vldStsSet", vldStsSet);
//        queryParam.put("rowNum", ERR_CONTR_ROW_NUM);
//
//        return (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("findErrContrVldRslt", queryParam);
//    }
//    // END 2017/02/22 K.Kitachi [QC#17626, ADD]
    // END 2018/02/16 M.Naito [QC#24204, DEL]
    // add start 2017/03/27 CSA Defect#18073
    private String createMsgDsContrNum(String dsContrNum) {
        String rtnVal = "";
        if (!hasValue(dsContrNum)) {
            return rtnVal;
        }
        return MSG_ITEM_DS_CONTR_NUM + dsContrNum + MSG_ITEM_COLON;
    }

    private String createEmailParamMsg() {
        StringBuilder rtnMsg = new StringBuilder();
        for (String msg : this.errMsgList) {
            if (!hasValue(msg)) {
                continue;
            }

            if (hasValue(rtnMsg.toString())) {
                rtnMsg.append(S21Mail.LINE_SEPARATOR);
            }
            rtnMsg.append(msg);
        }
        return rtnMsg.toString();
    }
    // add end 2017/03/27 CSA Defect#18073
}
