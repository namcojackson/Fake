/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB056001;

import static com.canon.cusa.s21.batch.NSA.NSAB056001.constant.NSAB056001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_COND_CONSTTMsg;
import business.db.DS_COND_CONSTTMsgArray;
import business.db.SVC_BAT_ERR_LOGTMsg;
import business.db.SVC_BR_POST_XREF_TRKTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTR_TRKTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;

/**
 * <pre>
 * Branch Code Creation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/10/2016   Hitachi         Y.Osawa          Create          N/A
 * 07/25/2016   Hitachi         T.Mizuki         Update          QC#11947
 * 11/21/2016   Hitachi         N.Arai           Update          QC#16096
 * 11/22/2016   Hitachi         N.Arai           Update          QC#15829
 * 2017/05/25   Hitachi         K.Kojima         Update          QC#18687
 * 2018/12/19   Hitachi         K.Kitachi        Update          QC#29676
 * 2023/09/11   Hitachi         K.Watanabe       Update          QC#61310
 * </pre>
 */
public class NSAB056001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** System TimeStamp */
    private String sysTime = null;

    /** Error Message list */
    private List<String> errMsgList = new ArrayList<String>();

    /** SVC MACH MSTR PK list */
    private List<String> svcMachMstrPkList = new ArrayList<String>();

    /** Serial Number list */
    private List<String> serNumList = new ArrayList<String>();

    /** Serial Number */
    private String serNum = null;

    /** FROM_EFF_FROM_DT */
    private String fromEffFromDt = null;

    /** TO_EFF_FROM_DT */
    private String toEffFromDt = null;

    /** FROM_FLD_SVC_BR_CD */
    private String fromFldSvcBrCd = null;

    /** TO_FLD_SVC_BR_CD */
    private String toFldSvcBrCd = null;

    /** Error Massage */
    private String errMsg = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Total Count */
    private int totalCount;

    /** Normal Count */
    private int normalCount;

    /** Error Count */
    private int errorCount;

    /** Mail Template ID */
    private String mailTemplateId = null;

    /** Termination Code */
    private TERM_CD termCd = null;

    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSZM0392E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }
        // mod start 2016/07/25 CSA QC#11947
        // Sales date
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BATCH_ID);
        if (!hasValue(slsDt)) {
            throw new S21AbendException(NSZM0392E, new String[] {MSG_ITEM_SALES_DATE });
        }
        // mod end 2016/07/25 CSA QC#11947
        // DS_COND_CONST
        DS_COND_CONSTTMsgArray dsCondConstTMsgArray = getDsCondConstTMsgArray();

        if (dsCondConstTMsgArray != null) {
            setDsCondConstTMsg(dsCondConstTMsgArray);
        }

        // Mail Template
        this.mailTemplateId = SET_MAIL_TEMPLATE_ID;

        // initialize
        this.sysTime = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);

        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    }

    @Override
    protected void mainRoutine() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("none", SET_PARAM_NULL);
        // START 2023/09/11 K.Watanabe [QC#61310, ADD]
        List<String> svcMachMstrStsCdList = new ArrayList<String>();
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.CREATED);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.REMOVED);
        paramMap.put("svcMachMstrStsCdList", svcMachMstrStsCdList);
        paramMap.put("rtlWhCatgCd", RTL_WH_CATG.SHOWROOM);
        // END 2023/09/11 K.Watanabe [QC#61310, ADD]
        // mod start 2016/07/25 CSA QC#11947
        paramMap.put("slsDt", this.slsDt);
        paramMap.put("maxDt", "99991231");
        // mod end 2016/07/25 CSA QC#11947
// mod start 2016/11/21 CSA QC#16096
        paramMap.put("defFinBrCd", ZYPCodeDataUtil.getVarCharConstValue(NSAB0560_DEFAULT_FIN_BR, this.glblCmpyCd));
// mod end 2016/11/21 CSA QC#16096
        if (this.serNum != null) {
            paramMap.put("serNum", this.serNum);
        }
        if (this.fromEffFromDt != null) {
            paramMap.put("fromEffFromDt", this.fromEffFromDt);
        }
        if (this.toEffFromDt != null) {
            paramMap.put("toEffFromDt", this.toEffFromDt);
        }
        if (this.fromFldSvcBrCd != null) {
            paramMap.put("fromFldSvcBrCd", this.fromFldSvcBrCd);
        }
        if (this.toFldSvcBrCd != null) {
            paramMap.put("toFldSvcBrCd", this.toFldSvcBrCd);
        }

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getDiffMachineData", paramMap, execParam);
            rs = stmt.executeQuery();

            // START 2018/12/19 K.Kitachi [QC#29676, DEL]
//            boolean updateFlg = false;
//            boolean updateFinFlg = true;
//            boolean errorFinFlg = false;
//            boolean insertFlg = false;
//            String brCdTpDescTxt = null;
            // END 2018/12/19 K.Kitachi [QC#29676, DEL]
            while (rs.next()) {
                // START 2018/12/19 K.Kitachi [QC#29676, ADD]
                boolean updateFlg = false;
                boolean updateFinFlg = true;
                boolean errorFinFlg = false;
                boolean insertFlg = false;
                String brCdTpDescTxt = null;
                // END 2018/12/19 K.Kitachi [QC#29676, ADD]
                // mod start 2016/07/25 CSA QC#11947
                if (ZYPConstant.FLG_ON_Y.equals(rs.getString("FIN_FLG")) && !ZYPCommonFunc.hasValue(rs.getString("COA_BR_CD"))) {
                    updateFinFlg = false;
                    errorFinFlg = true;
                } else if (ZYPConstant.FLG_OFF_N.equals(rs.getString("FIN_FLG"))) {
                    updateFinFlg = false;
                }
                // update SVC_MACH_MSTR
                if (ZYPConstant.FLG_ON_Y.equals(rs.getString("FLD_FLG")) || updateFinFlg) {
                    updateFlg = updateMachineInBranch(rs.getBigDecimal("SVC_MACH_MSTR_PK"), rs.getString("SER_NUM"), rs.getString("FLD_SVC_BR_CD"), rs.getString("FLD_FLG"), rs.getString("FIN_BR_CD"), updateFinFlg);
                }

                // set brCdTpDescTxt
                StringBuffer sb = new StringBuffer();
                if (ZYPConstant.FLG_ON_Y.equals(rs.getString("FLD_FLG"))) {
                    sb.append(FLD_SVC_BR_CD);
                    if (ZYPConstant.FLG_ON_Y.equals(rs.getString("FIN_FLG"))) {
                        sb.append(COMMA);
                        sb.append(FIN_BR_CD);
                    }
                } else if (ZYPConstant.FLG_ON_Y.equals(rs.getString("FIN_FLG"))) {
                    sb.append(FIN_BR_CD);
                }
                brCdTpDescTxt = sb.toString();

                // insert SVC_BR_POST_XREF_TRK
                if (updateFlg) {
                    insertFlg = updateResultRegistration(rs, ZYPConstant.FLG_ON_Y, brCdTpDescTxt, errorFinFlg);
                    if (insertFlg && !errorFinFlg) {
                        commit();
                        normalCount++;
                    } else if (insertFlg && errorFinFlg) {
                        addErrMsg(rs.getBigDecimal("SVC_MACH_MSTR_PK").toString(), rs.getString("SER_NUM"), MSG_ITEM_FINANCIAL_BRANCH, null, new String[] {});
                        errorProcess(rs.getBigDecimal("SVC_MACH_MSTR_PK").toString(), rs.getString("SER_NUM"));
                        commit();
                        errorCount++;
                        // mod end 2016/07/25 CSA QC#11947
                    } else {
                        rollback();
                        errorProcess(rs.getBigDecimal("SVC_MACH_MSTR_PK").toString(), rs.getString("SER_NUM"));
                        commit();
                        errorCount++;
                    }
                } else {
                    // START 2018/12/19 K.Kitachi [QC#29676, ADD]
                    addErrMsg(rs.getBigDecimal("SVC_MACH_MSTR_PK").toString(), rs.getString("SER_NUM"), MSG_ITEM_SERVICE_FINANCIAL_BRANCH, null, new String[] {});
                    // END 2018/12/19 K.Kitachi [QC#29676, ADD]
                    // mod start 2016/07/25 CSA QC#11947
                    updateResultRegistration(rs, ZYPConstant.FLG_OFF_N, brCdTpDescTxt, errorFinFlg);
                    // mod end 2016/07/25 CSA QC#11947
                    errorProcess(rs.getBigDecimal("SVC_MACH_MSTR_PK").toString(), rs.getString("SER_NUM"));
                    commit();
                    errorCount++;
                }
                totalCount++;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    // mod start 2016/07/25 CSA QC#11947
    private boolean updateMachineInBranch(BigDecimal pk, String serNumber, String svcBrCd, String svcFlg, String finBrCd, boolean finFlg) {
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();

        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcMachMstrPk, pk);

        inMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);

        if (inMsg == null) {
            addErrMsg(pk.toString(), serNumber, null, NZZM0003E, new String[] {"SVC_MACH_MSTR" });
            return false;
        }

// START 2016/11/22 N.Arai [QC#15829, MOD]
        String oldFldSvcBrCd = inMsg.fldSvcBrCd.getValue();
        String oldFinBrCd = inMsg.finBrCd.getValue();
// END 2016/11/22 N.Arai [QC#15829, MOD]

        if (ZYPConstant.FLG_ON_Y.equals(svcFlg)) {
            setValue(inMsg.fldSvcBrCd, svcBrCd);
        }
        if (finFlg) {
            setValue(inMsg.finBrCd, finBrCd);
        }
        S21FastTBLAccessor.update(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            addErrMsg(pk.toString(), serNumber, null, NSAM0470E, new String[] {"SVC_MACH_MSTR", SVC_MACH_MSTR_PK + "=" + pk });
            return false;
        }

// START 2016/11/22 N.Arai [QC#15829, MOD]
        if (!ZYPCommonFunc.hasValue(oldFldSvcBrCd) && ZYPCommonFunc.hasValue(inMsg.fldSvcBrCd)) {
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(createSvcMachMstrTrk(pk, FLD_SVC_BR_CD, oldFldSvcBrCd, inMsg.fldSvcBrCd.getValue()))) {
                addErrMsg(pk.toString(), serNumber, null, NSAM0032E, new String[] {"SVC_MACH_MSTR_TRK"});
                return false;
            }
        } else if (ZYPCommonFunc.hasValue(oldFldSvcBrCd) && !ZYPCommonFunc.hasValue(inMsg.fldSvcBrCd)) {
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(createSvcMachMstrTrk(pk, FLD_SVC_BR_CD, oldFldSvcBrCd, inMsg.fldSvcBrCd.getValue()))) {
                addErrMsg(pk.toString(), serNumber, null, NSAM0032E, new String[] {"SVC_MACH_MSTR_TRK"});
                return false;
            }
        } else if (!oldFldSvcBrCd.equals(inMsg.fldSvcBrCd.getValue())) {
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(createSvcMachMstrTrk(pk, FLD_SVC_BR_CD, oldFldSvcBrCd, inMsg.fldSvcBrCd.getValue()))) {
                addErrMsg(pk.toString(), serNumber, null, NSAM0032E, new String[] {"SVC_MACH_MSTR_TRK"});
                return false;
            }
        }
        if (!ZYPCommonFunc.hasValue(oldFinBrCd) && ZYPCommonFunc.hasValue(inMsg.finBrCd)) {
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(createSvcMachMstrTrk(pk, FIN_BR_CD, oldFinBrCd, inMsg.finBrCd.getValue()))) {
                addErrMsg(pk.toString(), serNumber, null, NSAM0032E, new String[] {"SVC_MACH_MSTR_TRK"});
                return false;
            }
        } else if (ZYPCommonFunc.hasValue(oldFinBrCd) && !ZYPCommonFunc.hasValue(inMsg.finBrCd)) {
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(createSvcMachMstrTrk(pk, FIN_BR_CD, oldFinBrCd, inMsg.finBrCd.getValue()))) {
                addErrMsg(pk.toString(), serNumber, null, NSAM0032E, new String[] {"SVC_MACH_MSTR_TRK"});
                return false;
            }
        } else if (!oldFinBrCd.equals(inMsg.finBrCd.getValue())) {
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(createSvcMachMstrTrk(pk, FIN_BR_CD, oldFinBrCd, inMsg.finBrCd.getValue()))) {
                addErrMsg(pk.toString(), serNumber, null, NSAM0032E, new String[] {"SVC_MACH_MSTR_TRK"});
                return false;
            }
        }
// END 2016/11/22 N.Arai [QC#15829, MOD]
        return true;
    }

// START 2016/11/22 N.Arai [QC#15829, MOD]
    private String createSvcMachMstrTrk(BigDecimal svcMachMstrPk, String updFld, String oldVal, String newVal) {
        SVC_MACH_MSTR_TRKTMsg tMsg = new SVC_MACH_MSTR_TRKTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.svcMachMstrTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MACH_MSTR_TRK_SQ));
        setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
        setValue(tMsg.trxRgtnDt, this.slsDt);
        setValue(tMsg.updFldTxt, updFld);
        setValue(tMsg.oldValTxt, oldVal);
        setValue(tMsg.newValTxt, newVal);
        setValue(tMsg.updUsrId, BATCH_ID);
        setValue(tMsg.noteExistFlg, ZYPConstant.FLG_OFF_N);
        EZDTBLAccessor.create(tMsg);
        return tMsg.getReturnCode();
    }
// END 2016/11/22 N.Arai [QC#15829, MOD]

    private boolean updateResultRegistration(ResultSet rs, String updtRsltFlg, String brCdTpDescTxt, boolean errorFinFlg) throws SQLException {
        SVC_BR_POST_XREF_TRKTMsg inMsg = new SVC_BR_POST_XREF_TRKTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcBrPostXrefTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_BR_POST_XREF_TRK_SQ));
        setValue(inMsg.svcBrPostXrefProcTs, this.sysTime);
        setValue(inMsg.serNum, rs.getString("SER_NUM"));
        setValue(inMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
        setValue(inMsg.ctyAddr, rs.getString("CTY_ADDR"));
        setValue(inMsg.postCd, rs.getString("POST_CD"));

        setValue(inMsg.brCdTpDescTxt, brCdTpDescTxt);

// mod start 2016/11/21 CSA QC#16096
        String finBrCd = rs.getString("FIN_BR_CD");
// mod end 2016/11/21 CSA QC#16096

        // START 2017/05/25 K.Kojima [QC#18687,ADD]
        String finFlg = rs.getString("FIN_FLG");
        // END 2017/05/25 K.Kojima [QC#18687,ADD]

        if (errorFinFlg) {
            setValue(inMsg.procRsltFlg, ZYPConstant.FLG_OFF_N);
            setValue(inMsg.batErrMsgTxt, MSG_ITEM_FINANCIAL_BRANCH);
        } else {
            if (updtRsltFlg.equals(ZYPConstant.FLG_OFF_N)) {
                setValue(inMsg.procRsltFlg, updtRsltFlg);
                // START 2017/05/25 K.Kojima [QC#18687,ADD]
                if (finFlg.equals(ZYPConstant.FLG_ON_Y)) {
                    // END 2017/05/25 K.Kojima [QC#18687,ADD]
                    setValue(inMsg.batErrMsgTxt, MSG_ITEM_SERVICE_FINANCIAL_BRANCH);
                    // START 2017/05/25 K.Kojima [QC#18687,ADD]
                }
                // END 2017/05/25 K.Kojima [QC#18687,ADD]
            } else {
                setValue(inMsg.procRsltFlg, ZYPConstant.FLG_ON_Y);
// mod start 2016/11/21 CSA QC#16096
                // START 2017/05/25 K.Kojima [QC#18687,ADD]
                if (finFlg.equals(ZYPConstant.FLG_ON_Y)) {
                    // END 2017/05/25 K.Kojima [QC#18687,ADD]
                    if (finBrCd.equals(ZYPCodeDataUtil.getVarCharConstValue(NSAB0560_DEFAULT_FIN_BR, this.glblCmpyCd))) {
                        setValue(inMsg.batErrMsgTxt, MSG_ITEM_FINANCIAL_BRANCH_DEFAULT);
                    }
                    // START 2017/05/25 K.Kojima [QC#18687,ADD]
                }
                // END 2017/05/25 K.Kojima [QC#18687,ADD]
// mod end 2016/11/21 CSA QC#16096
            }
        }
        S21FastTBLAccessor.insert(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            addErrMsg(rs.getBigDecimal("SVC_MACH_MSTR_PK").toString(), rs.getString("SER_NUM"), null, NSAM0469E, new String[] {"SVC_BR_POST_XREF_TRK", SER_NUM + "=" + rs.getString("SER_NUM") });
            return false;
        }
        return true;
    }

    // mod end 2016/07/25 CSA QC#11947
    private void errorProcess(String updateSvcMachMstrPk, String updateSerNum) {
        this.termCd = TERM_CD.WARNING_END;

        SVC_BAT_ERR_LOGTMsg inMsg = new SVC_BAT_ERR_LOGTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcBatErrLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_BAT_ERR_LOG_SQ));
        setValue(inMsg.bizAppId, BIZ_APP_ID);
        setValue(inMsg.svcBatErrLogTs, this.sysTime);
        setValue(inMsg.svcBatErrKeyNum_01, updateSvcMachMstrPk);
        setValue(inMsg.svcBatErrKeyNum_02, updateSerNum);
        setValue(inMsg.svcBatErrKeyNm_01, updateSvcMachMstrPk);
        setValue(inMsg.svcBatErrKeyNm_02, updateSerNum);
        setValue(inMsg.svcBatErrMsgTxt, this.errMsg);
        S21FastTBLAccessor.insert(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            // mod start 2016/07/25 CSA QC#11947
            addErrMsg(updateSvcMachMstrPk, updateSerNum, null, NSAM0469E, new String[] {"SVC_BAT_ERR_LOG", SVC_MACH_MSTR_PK + "=" + updateSvcMachMstrPk });
        }
        // mod end 2016/07/25 CSA QC#11947
    }

    private DS_COND_CONSTTMsgArray getDsCondConstTMsgArray() {
        DS_COND_CONSTTMsg inMsg = new DS_COND_CONSTTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsCondConstGrpId01", CONST_GRP_ID);
        DS_COND_CONSTTMsgArray outMsg = (DS_COND_CONSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsg.getValidCount() > 0) {
            return outMsg;
        } else {
            return null;
        }
    }

    private void setDsCondConstTMsg(DS_COND_CONSTTMsgArray inMsgArray) {
        for (int i = 0; i < inMsgArray.getValidCount(); i++) {
            if (inMsgArray.no(i).dsCondConstCd.getValue().equals(SER_NUM)) {
                if (inMsgArray.no(i).dsCondConstValTxt_01.getValue().length() <= CHECK_DIGIT_30) {
                    this.serNum = inMsgArray.no(i).dsCondConstValTxt_01.getValue();
                }
            } else if (inMsgArray.no(i).dsCondConstCd.getValue().equals(FROM_EFF_FROM_DT)) {

                if (isFormatCratSvcCratTsOrigTxt(inMsgArray.no(i).dsCondConstValTxt_01.getValue())) {
                    this.fromEffFromDt = inMsgArray.no(i).dsCondConstValTxt_01.getValue();
                }
            } else if (inMsgArray.no(i).dsCondConstCd.getValue().equals(TO_EFF_FROM_DT)) {
                if (isFormatCratSvcCratTsOrigTxt(inMsgArray.no(i).dsCondConstValTxt_01.getValue())) {
                    this.toEffFromDt = inMsgArray.no(i).dsCondConstValTxt_01.getValue();
                }
            } else if (inMsgArray.no(i).dsCondConstCd.getValue().equals(FROM_FLD_SVC_BR_CD)) {
                if (isNumber(inMsgArray.no(i).dsCondConstValTxt_01.getValue()) && (inMsgArray.no(i).dsCondConstValTxt_01.getValue().length() <= CHECK_DIGIT_3)) {
                    this.fromFldSvcBrCd = inMsgArray.no(i).dsCondConstValTxt_01.getValue();
                }
            } else if (inMsgArray.no(i).dsCondConstCd.getValue().equals(TO_FLD_SVC_BR_CD)) {
                if (isNumber(inMsgArray.no(i).dsCondConstValTxt_01.getValue()) && (inMsgArray.no(i).dsCondConstValTxt_01.getValue().length() <= CHECK_DIGIT_3)) {
                    this.toFldSvcBrCd = inMsgArray.no(i).dsCondConstValTxt_01.getValue();
                }
            }
        }
    }

    private boolean isNumber(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isFormatCratSvcCratTsOrigTxt(String target) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);
        try {
            format.setLenient(false);
            format.parse(target);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    // mod start 2016/07/25 CSA QC#11947
    private void addErrMsg(String updateSvcMachMstrPk, String updateSerNum, String errorMsg, String msgId, String... param) {
        if (hasValue(msgId)) {
            this.errMsg = S21MessageFunc.clspGetMessage(msgId, param);
        } else {
            this.errMsg = errorMsg;
        }
        this.errMsgList.add(this.errMsg);
        this.svcMachMstrPkList.add(updateSvcMachMstrPk);
        this.serNumList.add(updateSerNum);
    }

    // mod end 2016/07/25 CSA QC#11947
    private void postErrorMail() {

        // *****************************************************************
        // Deriving From Mail Address
        // *****************************************************************
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GRP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if (addrFromList.size() <= 0) {
            return;
        }

        S21MailAddress from = addrFromList.get(0);

        // *****************************************************************
        // Deriving To Mail Address
        // *****************************************************************
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, BIZ_APP_ID);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();

        if (addrToList.size() <= 0) {
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
        template.setTemplateParameter("errDate", ZYPDateUtil.formatEzd17ToDisp(this.sysTime));

        StringBuilder msgBuf = new StringBuilder();
        for (int i = 0; i < this.errMsgList.size(); i++) {
            msgBuf.append(this.errMsgList.get(i));
            msgBuf.append(ERR_MSG_CRLF);
            msgBuf.append(ERR_MSG_SPACE_7);
        }
        String errorMessage = msgBuf.toString();
        template.setTemplateParameter("message", errorMessage);

        msgBuf = new StringBuilder();
        for (int i = 0; i < this.svcMachMstrPkList.size(); i++) {
            msgBuf.append(ERR_MSG_SPACE_1);
            msgBuf.append(svcMachMstrPkList.get(i));
            msgBuf.append(ERR_MSG_SPACE_4);
            msgBuf.append(serNumList.get(i));
            msgBuf.append(ERR_MSG_CRLF);
        }
        errorMessage = msgBuf.toString();

        template.setTemplateParameter("ErrInfo", errorMessage);

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
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            // Send error mail
            postErrorMail();

            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB056001().executeBatch(NSAB056001.class.getSimpleName());
    }
}
