/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB013001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CONSL_BILL_RGNRTMsg;

import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Mail;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001MailSubstituteString;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONSL_RGNR_ACT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONSL_RGNR_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONSL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEF_INV_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Populate Regeneration Invoice Staging Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/25   Fujitsu         H.Nagashima     Create          N/A
 * 2016/08/16   SRAA            K.Aratani       Create          QC#13107
 * 2019/10/15   Fujitsu         Y.Kanefusa      Update          S21_NA#53589
 * 2019/11/02   Fujitsu         M.Ohno          Update          S21_NA#53873
 *</pre>
 */
public class NWCB013001 extends S21BatchMain {

    /**
     *  Message IDs
     * @author Q09081
     *
     */
    private static enum MSG_ID {
        /** [@] is mandatory. */
        ZZZM9025E,
        /** No search results found. */
        ZZOM0011E,
        /** @ ended abnormally. */
        NLBM0024E,
        /** It failed to register CONSL_BILL_RGNR Table. */
        NWCM0108E
    }

    /** Program ID for Log */
//    private static final String PROGRAM_ID = " ## NWCB013001 ## ";

    /** Program Name */
    private static final String PROGRAM_NM = "Populate Regeneration Invoice Staging Batch";

    /** Invoice Print Status Cd Unprocess */
    private static final String CONST_INV_PRINT_STS_CD_UNPROCESS = "1";

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

    /** Mail template ID */
    private  String mailTemplateId = null;

    /** error Bean List */
    private List<ConslBillRgnrBean> errBeanList;

    /**
     * main process
     * @param args String[] batch parameters
     */
    public static void main(String[] args) {
        new NWCB013001().executeBatch();
    }

    @Override
    protected void initRoutine() {
        this.normalRecCnt = 0;
        this.errRecCnt = 0;
        this.termCd = TERM_CD.NORMAL_END;

        this.errBeanList = new ArrayList<ConslBillRgnrBean>();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Global Company Code"));
        }

        mailTemplateId = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(mailTemplateId)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Mail Template Id"));
        }

    }

    @Override
    protected void mainRoutine() {
        // Search Target Data
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd",      this.glblCmpyCd);
        ssmParam.put("invPrintStsCd",   CONST_INV_PRINT_STS_CD_UNPROCESS);
        ssmParam.put("fnlzInvFlg",      ZYPConstant.FLG_ON_Y);
        ssmParam.put("excludeSysSrcCd", SYS_SRC.S21_ACCOUNTING_AR);
        ssmParam.put("conslStsCd20", CONSL_STS.REJECTED); // QC#53589 2019/10/15 Add
        // 2019/11/02 QC#53873 Add Start
        ssmParam.put("consBillStatus",      ZYPConstant.FLG_OFF_N);
        // 2019/11/02 QC#53873 Add End

        boolean rslt = (Boolean) ssmBatchClient.queryObject("getRegenerationTargetInvoice", ssmParam, new ConsolidationRegenerationCreator());
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
                for (ConslBillRgnrBean errBean : errBeanList) {
                    if (!postErrorMail(errBean)) {
                        throw new S21AbendException(MSG_ID.NLBM0024E.toString(), toArray(PROGRAM_NM + ":mail sending process"));
                    }
                }
            }
        }
    }

    private boolean postErrorMail(ConslBillRgnrBean bean) {

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

        boolean isNormalEnd = new NWXC001001Mail().postMail(glblCmpyCd, "NWCB0130", mailTemplateId, sbsStrList);

        return isNormalEnd;

    }

    /**
     * ResultSet of SQL process.
     *
     */
    protected class ConsolidationRegenerationCreator extends S21SsmBooleanResultSetHandlerSupport {

        @Override
        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            Boolean rslt = Boolean.TRUE;
            rs.last();
            totalRecCnt = rs.getRow();
            rs.first();
            if (0 == totalRecCnt) {
                S21InfoLogOutput.println(MSG_ID.ZZOM0011E.toString());
            } else {
                rslt = createConsoliBillRegeneration(rs);
            }
            return rslt;
        }

        private Boolean createConsoliBillRegeneration(ResultSet rs) throws SQLException {

            Boolean rslt = Boolean.TRUE;
            rs.first();
            ConslBillRgnrBean bean;
            int tranCount = 0;
            int commitCount = getCommitCount();

            do {
                bean = new ConslBillRgnrBean(rs);

                //QC#13107
                //Rebill Invoice Check if same group or not
                if (!chkSameConslGrp(bean)) {
                    continue;
                }
                
                CONSL_BILL_RGNRTMsg conslBillRgerTMsg = new CONSL_BILL_RGNRTMsg();
                BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONSL_BILL_RGNR_SQ);

                ZYPEZDItemValueSetter.setValue(conslBillRgerTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(conslBillRgerTMsg.conslBillRgnrPk, pk);
                ZYPEZDItemValueSetter.setValue(conslBillRgerTMsg.invNum, bean.getInvNum());
                ZYPEZDItemValueSetter.setValue(conslBillRgerTMsg.trgtConslBillPk, bean.getConslBillPk());
                ZYPEZDItemValueSetter.setValue(conslBillRgerTMsg.conslRgnrProcCd, CONSL_RGNR_PROC.UN_PROCESSED);
                ZYPEZDItemValueSetter.setValue(conslBillRgerTMsg.conslRgnrActTpCd, CONSL_RGNR_ACT_TP.REGENERATE);
                EZDTBLAccessor.insert(conslBillRgerTMsg);

                if (EZDTBLAccessor.RTNCD_NORMAL.equals(conslBillRgerTMsg.getReturnCode())) {
                    normalRecCnt++;
                    tranCount++;
                    if (tranCount > commitCount) {
                        tranCount = 0;
                        commit();
                    }
                } else {
                    errBeanList.add(bean);
                    errRecCnt++;
                    // print log
                    S21InfoLogOutput.println(MSG_ID.NWCM0108E.toString(), toArray(bean.getInvNum()));
                }

            } while (rs.next());

            if (tranCount > 0) {
                commit();
            }
            if (!errBeanList.isEmpty()) {
                rslt = Boolean.FALSE;
            }
            return rslt;
        }

     }

    private String[] toArray(String ...appendMsgList) {
        String[] msgArray = new String[appendMsgList.length];
        for (int i = 0; i < msgArray.length; i++) {
            msgArray[i] = appendMsgList[i];
        }
        return msgArray;
    }

    //QC#13107
    private boolean chkSameConslGrp(ConslBillRgnrBean bean) {
        //Bill To Customer Code
        if (bean.getBillToCustCd().equals(bean.getConslBillToCustCd())) {
            //Not Easy PAC & Rebill Invoice
            if (!"Y".equals(bean.getEasyPacFlg()) && !INV_TP.CREDIT_MEMO.equals(bean.getInvTpCd())) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("glblCmpyCd",     glblCmpyCd);
                param.put("conslBillPk",    bean.getConslBillPk());
                List<Map<String, String>> conslBillGrpList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getConslBillGrp", param);
                if (conslBillGrpList != null && conslBillGrpList.size() > 0) {
                    for (Map<String, String> map : conslBillGrpList) {
                        String defInvGrpCd = (String) map.get("DEF_INV_GRP_CD");
                        if (!ZYPCommonFunc.hasValue(defInvGrpCd)) {
                            continue;
                        }
                        String defInvGrpTxt = (String) map.get("DEF_INV_GRP_TXT");
                        String grpValTxt = "";
                        if (DEF_INV_GRP.PO_NUM.equals(defInvGrpCd)) {
                            grpValTxt = bean.getCustIssPoNum();
                        } else if (DEF_INV_GRP.CONTRACT_NUM.equals(defInvGrpCd)) {
                            grpValTxt = bean.getDsContrNum();
                        } else if (DEF_INV_GRP.BILLING_PERIOD.equals(defInvGrpCd)) {
                            grpValTxt = bean.getBllgPer();
                        } else if (DEF_INV_GRP.SERIAL_NUM.equals(defInvGrpCd)) {
                            grpValTxt = bean.getSerNum();
                        } else if (DEF_INV_GRP.MODEL.equals(defInvGrpCd)) {
                            grpValTxt = bean.getMdlNm();
                        }
                        if (!grpValTxt.equals(defInvGrpTxt)) {
                            return false;
                        }
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }
}

