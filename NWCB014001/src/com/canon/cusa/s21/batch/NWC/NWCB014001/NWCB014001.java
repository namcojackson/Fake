/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB014001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.CONSL_BILLTMsg;
import business.db.CONSL_BILL_GRPTMsg;
import business.db.CONSL_BILL_LINETMsg;
import business.db.CONSL_BILL_LINETMsgArray;
import business.db.CONSL_BILL_RGNRTMsg;
import business.db.INVTMsg;
import business.db.INV_PRT_CTRLTMsg;
import business.db.INV_PRT_FLEET_SUB_TOTTMsg;
import business.db.INV_PRT_HDRTMsg;
import business.db.INV_PRT_MAINT_SUB_TOTTMsg;
import business.db.INV_PRT_SLS_PART_SUB_TOTTMsg;
import business.db.INV_PRT_SMRYTMsg;
import business.db.SVC_INVTMsg;
import business.db.SVC_INVTMsgArray;
import business.parts.NFZC309001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC309001.NFZC309001;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Mail;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001MailSubstituteString;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONSL_PRINT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONSL_RGNR_ACT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONSL_RGNR_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONSL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Regenerate Consolidated Bills Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   Fujitsu         H.Nagashima     Create          N/A
 * 2017/02/26   SRAA            K.ARatani       Update          QC#17777
 * 2017/03/22   SRAA            K.ARatani       Update          QC#18144
 * 2017/06/13   Fujitsu         A.Kosai         Update          QC#18632
 * 2017/03/22   SRAA            K.Aratani       Update          QC#22088
 * 2022/09/09   Hitachi         S.Naya          Update          QC#60140
 * 
 *</pre>
 */
public class NWCB014001 extends S21BatchMain {

    /**
     * Message IDs
     * @author Q09081
     */
    private static enum MSG_ID {
        /** [@] is mandatory. */
        ZZZM9025E,
        /** No search results found. */
        ZZOM0011E,
        /** @ ended abnormally. */
        NLBM0024E,
        /** It failed to register @ Table. */
        NWCM0109E,
        /** It failed to update @ Table. */
        NWCM0110E,
        //START 2022/09/09 S.Naya [QC#60140,ADD]
        /** It failed to get [@].(@) */
        NWCM0112E,
        //END   2022/09/09 S.Naya [QC#60140,ADD]
        /** It failed to delete @ Table. */
        NWCM0111E
    }

    /** Program Name */
    private static final String PROGRAM_NM = "Regenerate Consolidated Bills Batch";

    /** Create Program ID */
    private static final String CRAT_PGM_ID = "NWCB014001";

    /** Create Person CD */
    private static final String CRAT_PSN_CD = "NWCB0140";

    /** Normal Record Count */
    private int normalRecCnt;

    /** Error Record Count */
    private int errRecCnt;

    /** Term Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** records */
    private int totalRecCnt;

    /** SSM */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** Mail template ID */
    private String mailTemplateId = null;

    /** error Bean List */
    private List<ConslBillBean> errBeanList;

    /** digit consolidated bill line number */
    private static final int DIGIT_CONSL_BILL_LINE_NUM = new CONSL_BILL_LINETMsg().getAttr("conslBillLineNum").getDigit();

    // 2017/06/13 QC#18632 Add Start
    /** Automatic Sequence Number Code (Consolidated Bill Number) */
    private static final String CONST_AUTO_SEQ_CD_CONSL_BILL_NUM = "CONSL_BILL_NUM";
    // 2017/06/13 QC#18632 Add End

    /**
     * main process
     * @param args String[] batch parameters
     */
    public static void main(String[] args) {
        new NWCB014001().executeBatch();
    }

    @Override
    protected void initRoutine() {
        this.normalRecCnt = 0;
        this.errRecCnt = 0;
        this.termCd = TERM_CD.NORMAL_END;

        this.errBeanList = new ArrayList<ConslBillBean>();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Global Company Code"));
        }

        // Sales Date
        slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        mailTemplateId = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(mailTemplateId)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Mail Template Id"));
        }

    }

    @Override
    protected void mainRoutine() {

        // Search Target Data
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("conslRgnrProcCd", CONSL_RGNR_PROC.UN_PROCESSED);

        boolean rslt = (Boolean) ssmBatchClient.queryObject("getTargetConslBillPk", ssmParam, new ConsolidatedBillRegenerationCreator());
        if (!rslt) {
            if (this.normalRecCnt > 0) {
                this.termCd = TERM_CD.WARNING_END;
            } else {
                this.termCd = TERM_CD.ABNORMAL_END;
            }
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);

        boolean isNeededMailInfo = TERM_CD.WARNING_END == this.termCd;
        isNeededMailInfo = isNeededMailInfo || TERM_CD.ABNORMAL_END == this.termCd;
        isNeededMailInfo = isNeededMailInfo || this.errRecCnt > 0;

        if (isNeededMailInfo) {
            // post error mail.
            if (!errBeanList.isEmpty()) {
                for (ConslBillBean errBean : errBeanList) {
                    if (!postErrorMail(errBean)) {
                        throw new S21AbendException(MSG_ID.NLBM0024E.toString(), toArray(PROGRAM_NM + ":mail sending process"));
                    }
                }
            }
        }
    }

    private boolean postErrorMail(ConslBillBean bean) {

        final List<NWXC001001MailSubstituteString> sbsStrList = new ArrayList<NWXC001001MailSubstituteString>();

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
        sbsStr.setSbstKey("batchProcLogId");
        sbsStr.setSbstStr(super.getBatchProcessLogID());
        sbsStrList.add(sbsStr);

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("invNum");
        sbsStr.setSbstStr(bean.getInvNum());
        sbsStrList.add(sbsStr);

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("conslBillPk");
        sbsStr.setSbstStr(String.valueOf(bean.getConslBillPk()));
        sbsStrList.add(sbsStr);

        boolean isNormalEnd = new NWXC001001Mail().postMail(glblCmpyCd, "NWCB0140", mailTemplateId, sbsStrList);

        return isNormalEnd;

    }

    /**
     * ResultSet of SQL process.
     */
    protected class ConsolidatedBillRegenerationCreator extends S21SsmBooleanResultSetHandlerSupport {

        @Override
        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            Boolean rslt = Boolean.TRUE;

            // Has no result data
            if (!rs.next()) {
                return true;
            }

            // QC#18144
            // Accumulate target conslBillPk
            List<Map<String, Object>> targetList = new ArrayList<Map<String, Object>>();
            List<BigDecimal> targetConslBillPkList = new ArrayList<BigDecimal>();
            do {
                Map<String, Object> targetMap = new HashMap<String, Object>();
                targetMap.put("TRGT_CONSL_BILL_PK", rs.getBigDecimal("TRGT_CONSL_BILL_PK"));
                targetMap.put("RETRANSMIT_FLG", rs.getString("RETRANSMIT_FLG"));
                targetMap.put("NOTRETRANSMIT_FLG", rs.getString("NOTRETRANSMIT_FLG"));
                targetList.add(targetMap);
                targetConslBillPkList.add(rs.getBigDecimal("TRGT_CONSL_BILL_PK"));
            } while (rs.next());

            String canonE479InvSrchTbl = ZYPCodeDataUtil.getVarCharConstValue("CANON_E479_INV_SRCH_TBL", glblCmpyCd);

            for (Map<String, Object> targetMap : targetList) {
                totalRecCnt++;
                BigDecimal conslBillPk = (BigDecimal) targetMap.get("TRGT_CONSL_BILL_PK");
                String retransmitFlg = (String) targetMap.get("RETRANSMIT_FLG");
                String notRetransmitFlag = (String) targetMap.get("NOTRETRANSMIT_FLG");

                // ###############################################################################################
                // If canon_e479_excel_detail,
                // canon_e479_excel_control, canon_E479_inv_Srch_Tbl
                // available
                // ###############################################################################################
                if (ZYPCommonFunc.hasValue(canonE479InvSrchTbl)) {
                    // ###############################################################################################
                    // update related bill's
                    // INV_PRT_CTRL.INV_SPCL_BILL_PROC_STS_CD =
                    // '1'(Unprocessed) per same URN#
                    // ###############################################################################################
                    Map<String, Object> paramRelatedBill = new HashMap<String, Object>();
                    paramRelatedBill.put("glblCmpyCd", glblCmpyCd);
                    paramRelatedBill.put("conslBillPk", conslBillPk.toString());
                    List<Map<String, Object>> relatedBillList = ssmBatchClient.queryObjectList("getRelatedConslBillPk", paramRelatedBill);
                    if (relatedBillList != null && !relatedBillList.isEmpty()) {
                        for (Map<String, Object> relatedBillMap : relatedBillList) {
                            BigDecimal relatedBillPk = (BigDecimal) relatedBillMap.get("BILL_NUMBER");
                            if (relatedBillPk != null && !targetConslBillPkList.contains(relatedBillPk)) {
                                Map<String, Object> paramInvPrtCtrl = new HashMap<String, Object>();
                                paramInvPrtCtrl.put("glblCmpyCd", glblCmpyCd);
                                paramInvPrtCtrl.put("conslBillPk", relatedBillPk.toString());
                                List<Map<String, Object>> invPrtCtrlList = ssmBatchClient.queryObjectList("getTargetInvPrtCtrl", paramInvPrtCtrl);
                                if (invPrtCtrlList != null && !invPrtCtrlList.isEmpty()) {
                                    for (Map<String, Object> invPrtCtrlMap : invPrtCtrlList) {
                                        BigDecimal invPrtCtrlPk = (BigDecimal) invPrtCtrlMap.get("INV_PRT_CTRL_PK");
                                        INV_PRT_CTRLTMsg invPrtCtrlTMsg = new INV_PRT_CTRLTMsg();
                                        invPrtCtrlTMsg.glblCmpyCd.setValue(glblCmpyCd);
                                        invPrtCtrlTMsg.invPrtCtrlPk.setValue(invPrtCtrlPk);
                                        invPrtCtrlTMsg = (INV_PRT_CTRLTMsg) EZDTBLAccessor.findByKey(invPrtCtrlTMsg);
                                        if (invPrtCtrlTMsg != null) {
                                            invPrtCtrlTMsg.invSpclBillProcStsCd.setValue("1");
                                            EZDTBLAccessor.update(invPrtCtrlTMsg);
                                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(invPrtCtrlTMsg.getReturnCode())) {
                                                String val = "invPrtCtrlPk:" + invPrtCtrlPk;
                                                S21InfoLogOutput.println(MSG_ID.NWCM0110E.toString(), toArray(invPrtCtrlTMsg.getTableName(), val));
                                                return false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // QC#17961
                // ##########################
                // Only retransmit request
                // ##########################
                if ("Y".equals(retransmitFlg) && "N".equals(notRetransmitFlag)) {
                    // ###################################################################
                    // update INV_PRT_CTRL.INV_SPCL_BILL_PROC_STS_CD=1
                    // by CONSL_BILL_NUM
                    // ###################################################################
                    Map<String, Object> paramInvPrtCtrl = new HashMap<String, Object>();
                    paramInvPrtCtrl.put("glblCmpyCd", glblCmpyCd);
                    paramInvPrtCtrl.put("conslBillPk", conslBillPk);
                    List<Map<String, Object>> invPrtCtrlList = ssmBatchClient.queryObjectList("getTargetInvPrtCtrl", paramInvPrtCtrl);
                    if (invPrtCtrlList != null && !invPrtCtrlList.isEmpty()) {
                        for (Map<String, Object> invPrtCtrlMap : invPrtCtrlList) {
                            BigDecimal invPrtCtrlPk = (BigDecimal) invPrtCtrlMap.get("INV_PRT_CTRL_PK");
                            INV_PRT_CTRLTMsg invPrtCtrlTMsg = new INV_PRT_CTRLTMsg();
                            invPrtCtrlTMsg.glblCmpyCd.setValue(glblCmpyCd);
                            invPrtCtrlTMsg.invPrtCtrlPk.setValue(invPrtCtrlPk);
                            invPrtCtrlTMsg = (INV_PRT_CTRLTMsg) EZDTBLAccessor.findByKey(invPrtCtrlTMsg);
                            if (invPrtCtrlTMsg != null) {
                                invPrtCtrlTMsg.invSpclBillProcStsCd.setValue("1");
                                EZDTBLAccessor.update(invPrtCtrlTMsg);
                                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(invPrtCtrlTMsg.getReturnCode())) {
                                    String val = "invPrtCtrlPk:" + invPrtCtrlPk;
                                    S21InfoLogOutput.println(MSG_ID.NWCM0110E.toString(), toArray(invPrtCtrlTMsg.getTableName(), val));
                                    return false;
                                }
                            }
                        }
                    }
                    // #################################################################
                    // update CONSL_BILL_RGNR.CONSL_RGNR_PROC_CD=01 by
                    // CONSL_BILL_NUM
                    // #################################################################
                    Map<String, Object> paramConslBillRgnr = new HashMap<String, Object>();
                    paramConslBillRgnr.put("glblCmpyCd", glblCmpyCd);
                    paramConslBillRgnr.put("trgtConslBillPk", conslBillPk);
                    List<Map<String, Object>> conslBillRgnrList = ssmBatchClient.queryObjectList("getTargetConslBillRgnr", paramConslBillRgnr);
                    if (conslBillRgnrList != null && !conslBillRgnrList.isEmpty()) {
                        for (Map<String, Object> conslBillRgnrMap : conslBillRgnrList) {
                            BigDecimal conslBillRgnrPk = (BigDecimal) conslBillRgnrMap.get("CONSL_BILL_RGNR_PK");
                            CONSL_BILL_RGNRTMsg conslBillRgnrTMsg = new CONSL_BILL_RGNRTMsg();
                            conslBillRgnrTMsg.glblCmpyCd.setValue(glblCmpyCd);
                            conslBillRgnrTMsg.conslBillRgnrPk.setValue(conslBillRgnrPk);
                            conslBillRgnrTMsg = (CONSL_BILL_RGNRTMsg) EZDTBLAccessor.findByKey(conslBillRgnrTMsg);
                            if (conslBillRgnrTMsg != null) {
                                conslBillRgnrTMsg.conslRgnrProcCd.setValue(CONSL_RGNR_PROC.PROCESSED);
                                conslBillRgnrTMsg.conslRgnrProcDt.setValue(slsDt);
                                EZDTBLAccessor.update(conslBillRgnrTMsg);
                                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(conslBillRgnrTMsg.getReturnCode())) {
                                    String val = "conslBillRgnrPk:" + conslBillRgnrPk;
                                    S21InfoLogOutput.println(MSG_ID.NWCM0110E.toString(), toArray(conslBillRgnrTMsg.getTableName(), val));
                                    return false;
                                }
                            }
                        }
                    }

                } else {
                    // ##### create new conslBill #####
                    if (!createConslBill(conslBillPk)) {
                        errRecCnt++;
                        rollback();
                        continue;
                    }
                }

                normalRecCnt++;
                commit();

            }

            if (errRecCnt > 0) {
                rslt = Boolean.FALSE;
            }
            return rslt;
        }

    }

    private boolean createConslBill(BigDecimal conslBillPk) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("conslBillPk", conslBillPk);

        List<Map<String, Object>> mapResList = ssmBatchClient.queryObjectList("getTargetConsolidatedBillData", param);

        if (mapResList.isEmpty()) {
            return true;
        }

        List<ConslBillBean> insertConslBillBeanList = new ArrayList<ConslBillBean>();

        for (Map<String, Object> mapRes : mapResList) {
            ConslBillBean bean = new ConslBillBean();
            bean.setResult(mapRes);

            if (insertConslBillBeanList.isEmpty()) {
                // update status
                if (!updateOldConslBillSts(conslBillPk)) {
                    errBeanList.add(bean);
                    return false;
                }

                //QC#22088
                // logical delete invoice Print stage
                //if (!logicalDeleteForInvoicePrintStage(bean)) {
                //    errBeanList.add(bean);
                //    return false;
                //}

                // clear pk
                if (!clearPkForInvoicePrintStage(bean)) {
                    errBeanList.add(bean);
                    return false;
                }
            }

            insertConslBillBeanList.add(bean);

            // clear group invoice number
            if (!updateArTrxBalByConslBillPk(bean.getInvNum())) {
                errBeanList.add(bean);
                return false;
            }
            if (!updateInvByConslBillPk(bean.getInvNum())) {
                errBeanList.add(bean);
                return false;
            }

            // update status
            if (ZYPCommonFunc.hasValue(bean.getConslBillRgnrPk())) {
                if (!updateConslBillRgnr(bean)) {
                    errBeanList.add(bean);
                    return false;
                }

                // update InvPrtCtrl
                if (!updateInvPrtCtrlByInvNum(bean.getInvNum())) {
                    errBeanList.add(bean);
                    return false;
                }
            }
        }

        // insert CONSL_BILL
        if (!insertConslBillBeanList.isEmpty()) {
            if (!insertConslBill(insertConslBillBeanList)) {
                return false;
            }

        }

        return true;
    }

    private boolean clearPkForInvoicePrintStage(ConslBillBean bean) {

        BigDecimal conslBillPk = bean.getConslBillPk();
        // clear conslidated bill number
        // QC#17765
        if (!updateInvPrtCtrlByConslBillPk(conslBillPk)) {
            return false;
        }

        if (!updateInvPrtFleetSubTotByConslBillPk(conslBillPk)) {
            return false;
        }

        if (!updateInvPrtMaintSubTotByConslBillPk(conslBillPk)) {
            return false;
        }

        if (!updateInvPrtSlsPartSubTotByConslBillPk(conslBillPk)) {
            return false;
        }

        return true;
    }

    //QC#22088
    //private boolean logicalDeleteForInvoicePrintStage(ConslBillBean bean) {
    //
    //    BigDecimal conslBillPk = bean.getConslBillPk();
    //    if (!deleteInvPrtCtrlByConslBillPk(conslBillPk)) {
    //        return false;
    //    }
    //    if (!deleteInvPrtHdrByConslBillPk(conslBillPk)) {
    //        return false;
    //    }
    //    if (!deleteInvPrtSmryByConslBillPk(conslBillPk)) {
    //        return false;
    //    }
    //
    //    return true;
    //}

    private boolean updateConslBillRgnr(ConslBillBean bean) {

        CONSL_BILL_RGNRTMsg conslBillRgnrTMsg = new CONSL_BILL_RGNRTMsg();
        ZYPEZDItemValueSetter.setValue(conslBillRgnrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(conslBillRgnrTMsg.conslBillRgnrPk, bean.getConslBillRgnrPk());
        ZYPEZDItemValueSetter.setValue(conslBillRgnrTMsg.conslRgnrProcCd, CONSL_RGNR_PROC.PROCESSED);
        ZYPEZDItemValueSetter.setValue(conslBillRgnrTMsg.conslRgnrProcDt, slsDt);

        EZDTBLAccessor.updateSelectionField(conslBillRgnrTMsg, toArray("conslRgnrProcCd", "conslRgnrProcDt"));

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(conslBillRgnrTMsg.getReturnCode())) {
            String val = "conslBillRgnrPk:" + bean.getConslBillRgnrPk();
            S21InfoLogOutput.println(MSG_ID.NWCM0110E.toString(), toArray(conslBillRgnrTMsg.getTableName(), val));
            return false;
        }
        return true;
    }

    private boolean updateInvPrtCtrlByInvNum(String invNum) {

        boolean isSuccess = true;
        INV_PRT_CTRLTMsg condTMsg = new INV_PRT_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(condTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(condTMsg.invNum, invNum);

        INV_PRT_CTRLTMsg updTMsg = new INV_PRT_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(updTMsg.conslBillFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(updTMsg.invOtptReqFlg, ZYPConstant.FLG_OFF_N);

        EZDTBLAccessor.updateByPartialValue(condTMsg, toArray("glblCmpyCd", "invNum"), updTMsg, toArray("conslBillFlg", "invOtptReqFlg"));

        if (EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode()) || EZDTBLAccessor.RTNCD_NOT_FOUND.equals(updTMsg.getReturnCode())) {
            isSuccess = true;
        } else {
            String val = "invNum:" + invNum;
            S21InfoLogOutput.println(MSG_ID.NWCM0110E.toString(), toArray(updTMsg.getTableName(), val));
            isSuccess = false;
        }

        return isSuccess;
    }

    // QC#17765
    private boolean updateInvPrtCtrlByConslBillPk(BigDecimal conslBillPk) {

        boolean isSuccess = true;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("conslBillNum", String.valueOf(conslBillPk));
        List<BigDecimal> pkList = ssmBatchClient.queryObjectList("getConsolidatedinvoice", param);
        if (pkList.isEmpty()) {
            return true;
        }
        for (BigDecimal invPrtCtrlPk : pkList) {
            INV_PRT_CTRLTMsg condTMsg = new INV_PRT_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(condTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(condTMsg.invPrtCtrlPk, invPrtCtrlPk);
            INV_PRT_CTRLTMsg resultTMsg = (INV_PRT_CTRLTMsg) EZDTBLAccessor.findByKey(condTMsg);
            if (resultTMsg != null) {
                resultTMsg.conslBillNum.clear();
                if ("2".equals(resultTMsg.invSpclBillProcStsCd.getValue()) || "1".equals(resultTMsg.invSpclBillProcStsCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(resultTMsg.invSpclBillProcStsCd, "1");
                } else {
                    ZYPEZDItemValueSetter.setValue(resultTMsg.invSpclBillProcStsCd, "0");
                }
                resultTMsg.conslBillInvDt.clear();
                resultTMsg.conslBillTotAmt.clear();
                resultTMsg.conslBillInvCratPsnCd.clear();
                resultTMsg.conslBillInvPgCnt.clear();
                EZDTBLAccessor.update(resultTMsg);
                if (EZDTBLAccessor.RTNCD_NORMAL.equals(resultTMsg.getReturnCode()) || EZDTBLAccessor.RTNCD_NOT_FOUND.equals(resultTMsg.getReturnCode())) {
                    isSuccess = true;
                } else {
                    String val = "conslBillNum:" + conslBillPk;
                    S21InfoLogOutput.println(MSG_ID.NWCM0110E.toString(), toArray(resultTMsg.getTableName(), val));
                    isSuccess = false;
                }
            }
        }
        
        
//        INV_PRT_CTRLTMsg condTMsg = new INV_PRT_CTRLTMsg();
//        ZYPEZDItemValueSetter.setValue(condTMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(condTMsg.conslBillNum, String.valueOf(conslBillPk));
//
//        INV_PRT_CTRLTMsg updTMsg = new INV_PRT_CTRLTMsg();
//
//        // QC#17883
//        ZYPEZDItemValueSetter.setValue(updTMsg.invSpclBillProcStsCd, "1");
//        EZDTBLAccessor.updateByPartialValue(condTMsg, toArray("glblCmpyCd", "conslBillNum"), updTMsg, toArray("conslBillNum", "invSpclBillProcStsCd", "conslBillInvDt", "conslBillTotAmt", "conslBillInvCratPsnCd", "conslBillInvPgCnt"));
//
//        if (EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode()) || EZDTBLAccessor.RTNCD_NOT_FOUND.equals(updTMsg.getReturnCode())) {
//            isSuccess = true;
//        } else {
//            String val = "conslBillNum:" + conslBillPk;
//            S21InfoLogOutput.println(MSG_ID.NWCM0110E.toString(), toArray(updTMsg.getTableName(), val));
//            isSuccess = false;
//        }

        return isSuccess;
    }

    private boolean updateInvPrtFleetSubTotByConslBillPk(BigDecimal conslBillPk) {

        boolean isSuccess = true;
        INV_PRT_FLEET_SUB_TOTTMsg condTMsg = new INV_PRT_FLEET_SUB_TOTTMsg();
        ZYPEZDItemValueSetter.setValue(condTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(condTMsg.conslBillNum, String.valueOf(conslBillPk));

        INV_PRT_FLEET_SUB_TOTTMsg updTMsg = new INV_PRT_FLEET_SUB_TOTTMsg();

        EZDTBLAccessor.updateByPartialValue(condTMsg, toArray("glblCmpyCd", "conslBillNum"), updTMsg, toArray("conslBillNum"));

        if (EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode()) || EZDTBLAccessor.RTNCD_NOT_FOUND.equals(updTMsg.getReturnCode())) {
            isSuccess = true;
        } else {
            String val = "conslBillNum:" + conslBillPk;
            S21InfoLogOutput.println(MSG_ID.NWCM0110E.toString(), toArray(updTMsg.getTableName(), val));
            isSuccess = false;

        }

        return isSuccess;
    }

    private boolean updateInvPrtMaintSubTotByConslBillPk(BigDecimal conslBillPk) {

        boolean isSuccess = true;
        INV_PRT_MAINT_SUB_TOTTMsg condTMsg = new INV_PRT_MAINT_SUB_TOTTMsg();
        ZYPEZDItemValueSetter.setValue(condTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(condTMsg.conslBillNum, String.valueOf(conslBillPk));

        INV_PRT_MAINT_SUB_TOTTMsg updTMsg = new INV_PRT_MAINT_SUB_TOTTMsg();

        EZDTBLAccessor.updateByPartialValue(condTMsg, toArray("glblCmpyCd", "conslBillNum"), updTMsg, toArray("conslBillNum"));

        if (EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode()) || EZDTBLAccessor.RTNCD_NOT_FOUND.equals(updTMsg.getReturnCode())) {
            isSuccess = true;
        } else {
            String val = "conslBillNum:" + conslBillPk;
            S21InfoLogOutput.println(MSG_ID.NWCM0110E.toString(), toArray(updTMsg.getTableName(), val));
            isSuccess = false;
            ConslBillBean errBean = new ConslBillBean();
            errBean.setConslBillPk(conslBillPk);
            errBeanList.add(errBean);
        }

        return isSuccess;
    }

    private boolean updateInvPrtSlsPartSubTotByConslBillPk(BigDecimal conslBillPk) {

        boolean isSuccess = true;
        INV_PRT_SLS_PART_SUB_TOTTMsg condTMsg = new INV_PRT_SLS_PART_SUB_TOTTMsg();
        ZYPEZDItemValueSetter.setValue(condTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(condTMsg.conslBillNum, String.valueOf(conslBillPk));

        INV_PRT_SLS_PART_SUB_TOTTMsg updTMsg = new INV_PRT_SLS_PART_SUB_TOTTMsg();

        EZDTBLAccessor.updateByPartialValue(condTMsg, toArray("glblCmpyCd", "conslBillNum"), updTMsg, toArray("conslBillNum"));

        if (EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode()) || EZDTBLAccessor.RTNCD_NOT_FOUND.equals(updTMsg.getReturnCode())) {
            isSuccess = true;
        } else {
            String val = "conslBillNum:" + conslBillPk;
            S21InfoLogOutput.println(MSG_ID.NWCM0110E.toString(), toArray(updTMsg.getTableName(), val));
            isSuccess = false;
        }

        return isSuccess;
    }

    private boolean updateArTrxBalByConslBillPk(String invNum) {

        boolean isSuccess = true;
        AR_TRX_BALTMsg condTMsg = new AR_TRX_BALTMsg();
        ZYPEZDItemValueSetter.setValue(condTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(condTMsg.arTrxNum, invNum);

        AR_TRX_BALTMsg updTMsg = new AR_TRX_BALTMsg();

        EZDTBLAccessor.updateByPartialValue(condTMsg, toArray("glblCmpyCd", "arTrxNum"), updTMsg, toArray("grpInvNum"));

        if (EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode()) || EZDTBLAccessor.RTNCD_NOT_FOUND.equals(updTMsg.getReturnCode())) {
            isSuccess = true;
        } else {
            String val = "arTrxNum:" + invNum;
            S21InfoLogOutput.println(MSG_ID.NWCM0110E.toString(), toArray(updTMsg.getTableName(), val));
            isSuccess = false;
        }

        return isSuccess;
    }

    private boolean updateInvByConslBillPk(String invNum) {

        boolean isSuccess = true;
        INVTMsg condTMsg = new INVTMsg();
        ZYPEZDItemValueSetter.setValue(condTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(condTMsg.invNum, invNum);

        INVTMsg updTMsg = new INVTMsg();

        EZDTBLAccessor.updateByPartialValue(condTMsg, toArray("glblCmpyCd", "invNum"), updTMsg, toArray("grpInvNum"));

        if (EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode()) || EZDTBLAccessor.RTNCD_NOT_FOUND.equals(updTMsg.getReturnCode())) {
            isSuccess = true;
        } else {
            String val = "invNum:" + invNum;
            S21InfoLogOutput.println(MSG_ID.NWCM0110E.toString(), toArray(updTMsg.getTableName(), val));
            isSuccess = false;
        }

        return isSuccess;
    }

    private boolean updateOldConslBillSts(BigDecimal conslBillPk) {

        boolean isSuccess = true;

        CONSL_BILLTMsg updTMsg = new CONSL_BILLTMsg();
        ZYPEZDItemValueSetter.setValue(updTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(updTMsg.conslBillPk, conslBillPk);
        ZYPEZDItemValueSetter.setValue(updTMsg.conslStsCd, CONSL_STS.REJECTED);

        EZDTBLAccessor.updateSelectionField(updTMsg, toArray("conslStsCd"));

        if (EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode()) || EZDTBLAccessor.RTNCD_NOT_FOUND.equals(updTMsg.getReturnCode())) {
            isSuccess = true;
        } else {
            String val = "conslBillPk:" + conslBillPk;
            S21InfoLogOutput.println(MSG_ID.NWCM0110E.toString(), toArray(updTMsg.getTableName(), val));
            isSuccess = false;
        }

        return isSuccess;
    }

//QC#22088
//    private boolean deleteInvPrtCtrlByConslBillPk(BigDecimal conslBillPk) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", glblCmpyCd);
//        param.put("conslBillNum", String.valueOf(conslBillPk));
//        List<BigDecimal> pkList = ssmBatchClient.queryObjectList("getInvPrtCtrlByConslBillNum", param);
//
//        if (pkList.isEmpty()) {
//            return true;
//        }
//
//        for (BigDecimal invPrtCtrlPk : pkList) {
//            INV_PRT_CTRLTMsg condTMsg = new INV_PRT_CTRLTMsg();
//            ZYPEZDItemValueSetter.setValue(condTMsg.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(condTMsg.invPrtCtrlPk, invPrtCtrlPk);
//
//            // QC#17777-2
//            INV_PRT_CTRLTMsg resultTMsg = (INV_PRT_CTRLTMsg) EZDTBLAccessor.findByKey(condTMsg);
//            if (resultTMsg != null) {
//                ZYPEZDItemValueSetter.setValue(resultTMsg.invSpclBillProcStsCd, "0");
//                EZDTBLAccessor.update(resultTMsg);
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(resultTMsg.getReturnCode())) {
//                    String val = "invPrtCtrlPk:" + invPrtCtrlPk;
//                    S21InfoLogOutput.println(MSG_ID.NWCM0110E.toString(), toArray(resultTMsg.getTableName(), val));
//                    return false;
//                }
//            }
//
//            EZDTBLAccessor.logicalRemove(condTMsg);
//
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(condTMsg.getReturnCode())) {
//                String val = "invPrtCtrlPk:" + invPrtCtrlPk;
//                S21InfoLogOutput.println(MSG_ID.NWCM0111E.toString(), toArray(condTMsg.getTableName(), val));
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    private boolean deleteInvPrtHdrByConslBillPk(BigDecimal conslBillPk) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", glblCmpyCd);
//        param.put("invBillNum", String.valueOf(conslBillPk));
//        List<BigDecimal> pkList = ssmBatchClient.queryObjectList("getInvPrtHdrByConslBillNum", param);
//
//        if (pkList.isEmpty()) {
//            return true;
//        }
//
//        for (BigDecimal invPrtCtrlPk : pkList) {
//            INV_PRT_HDRTMsg condTMsg = new INV_PRT_HDRTMsg();
//            ZYPEZDItemValueSetter.setValue(condTMsg.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(condTMsg.invPrtCtrlPk, invPrtCtrlPk);
//            EZDTBLAccessor.logicalRemove(condTMsg);
//
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(condTMsg.getReturnCode())) {
//                String val = "invPrtCtrlPk:" + invPrtCtrlPk;
//                S21InfoLogOutput.println(MSG_ID.NWCM0111E.toString(), toArray(condTMsg.getTableName(), val));
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    private boolean deleteInvPrtSmryByConslBillPk(BigDecimal conslBillPk) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", glblCmpyCd);
//        param.put("invBillNum", String.valueOf(conslBillPk));
//        List<BigDecimal> pkList = ssmBatchClient.queryObjectList("getInvPrtSmryByConslBillNum", param);
//
//        if (pkList.isEmpty()) {
//            return true;
//        }
//
//        for (BigDecimal invPrtSmryPk : pkList) {
//            INV_PRT_SMRYTMsg condTMsg = new INV_PRT_SMRYTMsg();
//            ZYPEZDItemValueSetter.setValue(condTMsg.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(condTMsg.invPrtSmryPk, invPrtSmryPk);
//            EZDTBLAccessor.logicalRemove(condTMsg);
//
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(condTMsg.getReturnCode())) {
//                String val = "invPrtSmryPk:" + invPrtSmryPk;
//                S21InfoLogOutput.println(MSG_ID.NWCM0111E.toString(), toArray(condTMsg.getTableName(), val));
//                return false;
//            }
//        }
//
//        return true;
//    }

    private boolean insertConslBill(List<ConslBillBean> conslBillBeanList) {

        ConslBillBean headerBean = conslBillBeanList.get(0);
        BigDecimal conslTotDealNetAmt = headerBean.getConslTotDealNetAmt();
        BigDecimal conslTotFuncNetAmt = headerBean.getConslTotFuncNetAmt();
        BigDecimal conslDueTotDealNetAmt = headerBean.getConslDueTotDealNetAmt();
        BigDecimal conslDueTotFuncNetAmt = headerBean.getConslDueTotFuncNetAmt();
        CONSL_BILLTMsg conslBillTMsg = new CONSL_BILLTMsg();
        CONSL_BILL_LINETMsg conslBillLineTMsg;

        int lineNum = 0;
        // 2017/06/13 QC#18632 Mod Start
//        BigDecimal newConslBillPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONSL_BILL_SQ);
        BigDecimal newConslBillPk = new BigDecimal(ZYPMaxTenDigitsNumbering.getUniqueID(this.glblCmpyCd, CONST_AUTO_SEQ_CD_CONSL_BILL_NUM));
        // 2017/06/13 QC#18632 Mod End
        //START 2022/09/09 S.Naya [QC#60140,ADD]
        int lineInsertNum = 0;
        //END   2022/09/09 S.Naya [QC#60140,ADD]

        for (ConslBillBean bean : conslBillBeanList) {

            String actTpCd = bean.getConslRgnrActTpCd();
            // create CONSL_BILL_LINE
            conslBillLineTMsg = new CONSL_BILL_LINETMsg();

            if (ZYPCommonFunc.hasValue(bean.getConslBillRgnrPk())) {
                ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslBillTrxId, bean.getInvNum());
                ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslBillTrxNum, bean.getInvNum());
                ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslBillTrxTpCd, bean.getInvTpCd());
                ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslBillTrxDt, bean.getInvDt());

                BigDecimal lineTotDealNetAmt = bean.getInvTotDealNetAmt();
                BigDecimal lineTotFuncNetAmt = bean.getInvTotFuncNetAmt();
                BigDecimal lineDueTotDealNetAmt = bean.getDealRmngBalGrsAmt();
                BigDecimal lineDueTotFuncNetAmt = bean.getFuncRmngBalGrsAmt();

                ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslTotDealNetAmt, lineTotDealNetAmt);
                ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslTotFuncNetAmt, lineTotFuncNetAmt);
                ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslDueTotDealNetAmt, lineDueTotDealNetAmt);
                ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslDueTotFuncNetAmt, lineDueTotFuncNetAmt);

                if (INV_TP.CREDIT_MEMO.equals(bean.getInvTpCd())) {
                    lineTotDealNetAmt = lineTotDealNetAmt.negate();
                    lineTotFuncNetAmt = lineTotFuncNetAmt.negate();
                    lineDueTotDealNetAmt = lineDueTotDealNetAmt.negate();
                    lineDueTotFuncNetAmt = lineDueTotFuncNetAmt.negate();
                }

                if (CONSL_RGNR_ACT_TP.DROP.equals(actTpCd)) {
                    lineTotDealNetAmt = lineTotDealNetAmt.negate();
                    lineTotFuncNetAmt = lineTotFuncNetAmt.negate();
                    lineDueTotDealNetAmt = lineDueTotDealNetAmt.negate();
                    lineDueTotFuncNetAmt = lineDueTotFuncNetAmt.negate();
                }

                if (CONSL_RGNR_ACT_TP.ADD.equals(actTpCd) || CONSL_RGNR_ACT_TP.REGENERATE.equals(actTpCd) || CONSL_RGNR_ACT_TP.DROP.equals(actTpCd)) {
                    conslTotDealNetAmt = conslTotDealNetAmt.add(lineTotDealNetAmt);
                    conslTotFuncNetAmt = conslTotFuncNetAmt.add(lineTotFuncNetAmt);
                    conslDueTotDealNetAmt = conslDueTotDealNetAmt.add(lineDueTotDealNetAmt);
                    conslDueTotFuncNetAmt = conslDueTotFuncNetAmt.add(lineDueTotFuncNetAmt);
                }

                // drop original consolidated amount if act_tp is add
                if (CONSL_RGNR_ACT_TP.ADD.equals(actTpCd)) {
                    Map<String, Object> param = new HashMap<String, Object>();
                    param.put("glblCmpyCd", glblCmpyCd);
                    param.put("conslBillPk", bean.getConslBillPk());
                    param.put("invNum", bean.getInvNum());
                    Map<String, BigDecimal> mapRes = (Map<String, BigDecimal>) ssmBatchClient.queryObject("searchConslBillLineNetAmt", param);

                    if (mapRes != null) {
                        conslTotDealNetAmt = conslTotDealNetAmt.subtract(mapRes.get("CONSL_TOT_DEAL_NET_AMT"));
                        conslTotFuncNetAmt = conslTotFuncNetAmt.subtract(mapRes.get("CONSL_TOT_FUNC_NET_AMT"));
                        conslDueTotDealNetAmt = conslDueTotDealNetAmt.subtract(mapRes.get("CONSL_DUE_TOT_DEAL_NET_AMT"));
                        conslDueTotFuncNetAmt = conslDueTotFuncNetAmt.subtract(mapRes.get("CONSL_DUE_TOT_FUNC_NET_AMT"));
                    }
                }

            } else {
                ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslBillTrxId, bean.getConslBillTrxId());
                ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslBillTrxNum, bean.getConslBillTrxNum());
                ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslBillTrxTpCd, bean.getConslBillTrxTpCd());
                ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslBillTrxDt, bean.getConslBillTrxDt());

                ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslTotDealNetAmt, bean.getLineTotDealNetAmt());
                ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslTotFuncNetAmt, bean.getLineTotFuncNetAmt());
                ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslDueTotDealNetAmt, bean.getLineDueTotDealNetAmt());
                ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslDueTotFuncNetAmt, bean.getLineDueTotFuncNetAmt());
            }

            if (CONSL_RGNR_ACT_TP.DROP.equals(actTpCd)) {
                continue;
            }

            ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslBillPk, newConslBillPk);
            String conslBillLineNum = ZYPCommonFunc.leftPad(Integer.toString(++lineNum), DIGIT_CONSL_BILL_LINE_NUM, "0");
            ZYPEZDItemValueSetter.setValue(conslBillLineTMsg.conslBillLineNum, conslBillLineNum);

            EZDTBLAccessor.insert(conslBillLineTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(conslBillLineTMsg.getReturnCode())) {
                String val = newConslBillPk + "_" + conslBillLineNum + "_" + bean.getInvNum();
                S21InfoLogOutput.println(MSG_ID.NWCM0109E.toString(), toArray(conslBillLineTMsg.getTableName(), val));
                ConslBillBean errBean = new ConslBillBean();
                errBean.setConslBillPk(newConslBillPk);
                errBean.setInvNum(bean.getInvNum());
                errBeanList.add(errBean);
                return false;
            }
            //START 2022/09/09 S.Naya [QC#60140,ADD]
            lineInsertNum++;
            //END   2022/09/09 S.Naya [QC#60140,ADD]
        }

        if (lineNum == 0) {
            return true;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("conslBillPk", headerBean.getConslBillPk());
        List<Map<String, Object>> conslBillGrpList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("searchConslBillGrpList", param);
        if (!conslBillGrpList.isEmpty()) {
            for (Map<String, Object> conslBillGrpMap : conslBillGrpList) {

                CONSL_BILL_GRPTMsg conslBillGrpTMsg = new CONSL_BILL_GRPTMsg();
                ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.glblCmpyCd, glblCmpyCd);
                BigDecimal grpSeq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONSL_BILL_GRP_SQ);
                ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.conslBillGrpPk, grpSeq);
                ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.conslBillPk, newConslBillPk);
                ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.defInvGrpCd, (String) conslBillGrpMap.get("DEF_INV_GRP_CD"));
                ZYPEZDItemValueSetter.setValue(conslBillGrpTMsg.defInvGrpTxt, (String) conslBillGrpMap.get("DEF_INV_GRP_TXT"));
                EZDTBLAccessor.insert(conslBillGrpTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(conslBillGrpTMsg.getReturnCode())) {
                    String val = "conslBillPk:" + newConslBillPk;
                    S21InfoLogOutput.println(MSG_ID.NWCM0109E.toString(), toArray(conslBillGrpTMsg.getTableName(), val));
                    ConslBillBean errBean = new ConslBillBean();
                    errBean.setConslBillPk(newConslBillPk);
                    errBeanList.add(errBean);
                    return false;
                }
            }
        }

        // create CONSL_BILL
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.conslBillPk, newConslBillPk);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.billToCustAcctCd, headerBean.getBillToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.billToCustAcctNm, headerBean.getBillToCustAcctNm());
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.billToCustCd, headerBean.getBillToCustCd());
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.billToCustLocNum, headerBean.getBillToCustLocNum());
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.conslStsCd, CONSL_STS.ACCEPTED);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.conslPrintStsCd, CONSL_PRINT_STS.NOT_PRINTED);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.pmtTermCd, headerBean.getPmtTermCd());
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.ctoffDt, headerBean.getCtoffDt());
        //START 2022/09/09 S.Naya [QC#60140,MOD]
        //ZYPEZDItemValueSetter.setValue(conslBillTMsg.dueDt, headerBean.getDueDt());
        String dueDate;
        Boolean syncDueDtFlg = false;
        if (isSyncDueDt(headerBean, lineInsertNum)) {
            dueDate = getDueDate(headerBean);
            syncDueDtFlg = true;
        } else {
            dueDate = headerBean.getDueDt();
        }
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.dueDt, dueDate);
        //END   2022/09/09 S.Naya [QC#60140,MOD]
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.cratPgmId, CRAT_PGM_ID);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.cratPsnCd, CRAT_PSN_CD);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.cratDt, slsDt);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.pmtTermCashDiscCd, headerBean.getPmtTermCashDiscCd());
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.pmtTermCashDiscDescTxt, headerBean.getPmtTermCashDiscDescTxt());
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.conslTotDealNetAmt, conslTotDealNetAmt);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.conslTotFuncNetAmt, conslTotFuncNetAmt);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.conslDueTotDealNetAmt, conslDueTotDealNetAmt);
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.conslDueTotFuncNetAmt, conslDueTotFuncNetAmt);

        // QC#13107
        // CUST_INV_SRC_CD
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.custInvSrcCd, headerBean.getCustInvSrcCd());
        // INV_GRP_NUM
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.invGrpNum, headerBean.getInvGrpNum());
        // EASY_PAC_FLG
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.easyPacFlg, headerBean.getEasyPacFlg());

        // QC#17777
        // ORIG_CONSL_BILL_PK
        ZYPEZDItemValueSetter.setValue(conslBillTMsg.origConslBillPk, headerBean.getConslBillPk());

        EZDTBLAccessor.insert(conslBillTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(conslBillTMsg.getReturnCode())) {
            String val = "conslBillPk:" + newConslBillPk;
            S21InfoLogOutput.println(MSG_ID.NWCM0109E.toString(), toArray(conslBillTMsg.getTableName(), val));
            ConslBillBean errBean = new ConslBillBean();
            errBean.setConslBillPk(newConslBillPk);
            errBeanList.add(errBean);
            return false;
        }

        //START 2022/09/09 S.Naya [QC#60140,ADD]
        if (syncDueDtFlg) {
            for (ConslBillBean bean : conslBillBeanList) {
                // Skip Credit Memo
                if (INV_TP.CREDIT_MEMO.equals(bean.getInvTpCd())) {
                    continue;
                } else if (bean.getInvTpCd() == null) {
                    INVTMsg invTMsg = new INVTMsg();
                    ZYPEZDItemValueSetter.setValue(invTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(invTMsg.invNum, bean.getInvNum());
                    invTMsg = (INVTMsg) EZDTBLAccessor.findByKey(invTMsg);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(invTMsg.getReturnCode())) {
                        String val = "invNum:" + bean.getInvNum();
                        S21InfoLogOutput.println(MSG_ID.NWCM0112E.toString(), toArray(invTMsg.getTableName(), val));
                        return false;
                    }

                    if (INV_TP.CREDIT_MEMO.equals(invTMsg.invTpCd.getValue())) {
                        continue;
                    }
                }

                // Reflect DueDate to INV.NET_DUE_DT
                if (!updateInvDueDate(dueDate, bean.getInvNum())) {
                    return false;
                }
                // Reflect DueDate to AR_TRX_VAL.NET_DUE_DT
                if (!updateArTrxBalDueDate(dueDate, bean.getInvNum())) {
                    return false;
                }
            }
        }
        // END   2022/09/09 S.Naya [QC#60140,ADD]

        return true;
    }

    private String[] toArray(String... appendMsgList) {
        String[] msgArray = new String[appendMsgList.length];
        for (int i = 0; i < msgArray.length; i++) {
            msgArray[i] = appendMsgList[i];
        }
        return msgArray;
    }
    //START 2022/09/09 S.Naya [QC#60140,ADD]
    private boolean isSyncDueDt(ConslBillBean bean, int lineInsertNum) {
        // Skip EasyPack
        if (ZYPConstant.FLG_ON_Y.equals(bean.getEasyPacFlg())) {
            return false;
        }

        // Regenerate or RePrint Check
        CONSL_BILL_LINETMsg conslBillLineTMsg = new CONSL_BILL_LINETMsg();
        conslBillLineTMsg.setSQLID("001");
        conslBillLineTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        conslBillLineTMsg.setConditionValue("conslBillPk01", bean.getConslBillPk());
        CONSL_BILL_LINETMsgArray conslBillLineArray = (CONSL_BILL_LINETMsgArray) EZDTBLAccessor.findByCondition(conslBillLineTMsg);

        // Skip RePrint
        if (conslBillLineArray.getValidCount() == lineInsertNum) {
            return false;
        }
        // Skip LinkContract
        if (isLinkContr(conslBillLineArray)) {
            return false;
        }

        return true;
    }

    private boolean isLinkContr(CONSL_BILL_LINETMsgArray conslBillLineTMsgArray) {
        SVC_INVTMsg svcInvTMsg = new SVC_INVTMsg();
        svcInvTMsg.setSQLID("001");
        svcInvTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);

        for (int i = 0; i < conslBillLineTMsgArray.getValidCount(); i++) {
            CONSL_BILL_LINETMsg lineTMsg = conslBillLineTMsgArray.no(i);

            svcInvTMsg.setConditionValue("svcInvNum01", lineTMsg.conslBillTrxNum.getValue());
            SVC_INVTMsgArray svcInvTMsgArray = (SVC_INVTMsgArray) EZDTBLAccessor.findByCondition(svcInvTMsg);

            if (svcInvTMsgArray.getValidCount() == 0) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(svcInvTMsgArray.no(0).contrLinkNum.getValue())) {
                return true;
            }
        }

        return false;
    }

    private String getDueDate(ConslBillBean bean) {

        NFZC309001PMsg pmsg = new NFZC309001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmsg.trxDt, slsDt);
        // pmt term cash disc cd is setting by getTargetData SQL
        ZYPEZDItemValueSetter.setValue(pmsg.pmtTermCashDiscCd, bean.getPmtTermCashDiscCd());

        NFZC309001 dueDateCalculationAPI = new NFZC309001();
        dueDateCalculationAPI.execute(pmsg, ONBATCH_TYPE.BATCH);
        if (pmsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < pmsg.xxMsgIdList.getValidCount(); i++) {
                String msgId = pmsg.xxMsgIdList.no(i).xxMsgId.getValue();
                S21InfoLogOutput.println(msgId);
            }
            return null;
        }

        return pmsg.dueDt.getValue();
    }

    private boolean updateInvDueDate(String dueDate, String invNum) {

        boolean isSuccess = true;

        INVTMsg invTMsg = new INVTMsg();
        ZYPEZDItemValueSetter.setValue(invTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invTMsg.invNum, invNum);
        invTMsg = (INVTMsg) EZDTBLAccessor.findByKey(invTMsg);

        if (EZDTBLAccessor.RTNCD_NORMAL.equals(invTMsg.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(invTMsg.netDueDt, dueDate);
            EZDTBLAccessor.update(invTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(invTMsg.getReturnCode())) {
                isSuccess = false;
                S21InfoLogOutput.println(MSG_ID.NWCM0110E.toString(), toArray(invTMsg.getTableName()));
            }
        } else {
            isSuccess = false;
            String val = "invNum:" + invNum;
            S21InfoLogOutput.println(MSG_ID.NWCM0112E.toString(), toArray(invTMsg.getTableName(), val));
        }

        return isSuccess;
    }

    private boolean updateArTrxBalDueDate(String dueDate, String invNum) {

        boolean isSuccess = true;

        AR_TRX_BALTMsg arTrxBalTMsg = new AR_TRX_BALTMsg();
        arTrxBalTMsg.setSQLID("001");
        arTrxBalTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        arTrxBalTMsg.setConditionValue("arTrxNum01", invNum);
        AR_TRX_BALTMsgArray arTrxBalTMsgArray = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(arTrxBalTMsg);

        if (arTrxBalTMsgArray.getValidCount() > 0) {
            arTrxBalTMsg = arTrxBalTMsgArray.no(0);
            ZYPEZDItemValueSetter.setValue(arTrxBalTMsg.invDueDt, dueDate);

            EZDTBLAccessor.update(arTrxBalTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(arTrxBalTMsg.getReturnCode())) {
                isSuccess = false;
                S21InfoLogOutput.println(MSG_ID.NWCM0110E.toString(), toArray(arTrxBalTMsg.getTableName()));
            }
        }

        return isSuccess;
    }
    //END   2022/09/09 S.Naya [QC#60140,ADD]
}
