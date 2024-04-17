/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPB.NPBB002001;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import business.parts.NPZC103001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.batch.NPB.NPBB002001.constant.NPBB002001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_COST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_CTRL_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NPBB0020 Create Used Parts Return Request Batch
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/24/2016   CITS       Yasushi Nomura        Create          N/A
 * 08/03/2016   CITS       K.Ogino               Update          QC#11784
 * 09/02/2016   CITS       K.Ogino               Update          QC#11713
 * 09/07/2016   CITS       K.Ogino               Update          QC#11711
 *</pre>
 */
public class NPBB002001 extends S21BatchMain {
    /** Global Company Code */
    private String globalCompanyCode = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Sales Date */
    private String salesDate = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** total commit count */
    private int totalCommitCount = 0;

    /** error count */
    private int errorCount = 0;

    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    /** mail template */
    private S21MailTemplate mailTemplate = null;

    /** mail template */
    private StringBuilder errorMessage = null;

    @Override
    protected void initRoutine() {
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        globalCompanyCode = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(globalCompanyCode)) {
            throw new S21AbendException(NPBB002001Constant.NPAM1479E);
        }

        salesDate = ZYPDateUtil.getSalesDate(globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            throw new S21AbendException(NPBB002001Constant.NPAM1480E);
        }

        mailTemplate = new S21MailTemplate(globalCompanyCode, NPBB002001Constant.MAIL_TEMPLATE_ID);
        if ((mailTemplate == null) || (!ZYPCommonFunc.hasValue(mailTemplate.getBody()))) {
            throw new S21AbendException(NPBB002001Constant.NLAM1272E, new String[] {NPBB002001Constant.MAIL_TEMPLATE_ID });
        }
    }

    @Override
    protected void mainRoutine() {
        String[] rtrnCtrlTpCdList = null;
        String mode = getUserVariable1();
        if (NPBB002001Constant.RUN_MODE_RF.equals(mode)) {
            rtrnCtrlTpCdList = new String[] {RTRN_CTRL_TP.REFURBISH };
        } else if (NPBB002001Constant.RUN_MODE_DP.equals(mode)) {
            rtrnCtrlTpCdList = new String[] {RTRN_CTRL_TP.DISPOSAL };
        } else {
            rtrnCtrlTpCdList = new String[] {RTRN_CTRL_TP.REFURBISH, RTRN_CTRL_TP.DISPOSAL };
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPBB002001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPBB002001Constant.DB_PARAM_MDSE_COST_TP_CD_STANDARD, MDSE_COST_TP.STANDARD_COST);
            paramMap.put(NPBB002001Constant.DB_PARAM_MDSE_COST_TP_CD_RECOVERY, MDSE_COST_TP.ASSET_RECOVERY);
            paramMap.put(NPBB002001Constant.DB_PARAM_RTRN_CTRL_TP_CD_LIST, rtrnCtrlTpCdList);
            paramMap.put(NPBB002001Constant.DB_PARAM_EFF_FROM_DT, salesDate);
            paramMap.put(NPBB002001Constant.DB_PARAM_EFF_THRU_DT, salesDate);
            paramMap.put(NPBB002001Constant.DB_PARAM_LOC_STS_CD, LOC_STS.DC_STOCK);
            paramMap.put(NPBB002001Constant.DB_PARAM_MDSE_ITEM_STS_CD, MDSE_ITEM_STS.INACTIVE);
            paramMap.put(NPBB002001Constant.DB_PARAM_CMPY_INVTY_FLG, ZYPConstant.FLG_OFF_N);
            paramMap.put(NPBB002001Constant.DB_PARAM_INVTY_ACCT_CD, INVTY_ACCT.ASSET);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("search", paramMap, execParam);
            NPZC103001PMsg pMsg = null;
            String rtrnCtrlTpCd = null;
            String rtlWhCd = null;
            String rtlSwhCd = null;
            String rtrnToVndCd = null;
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String rtrnCtrlTpCdTemp = resultSet.getString(NPBB002001Constant.RTRN_CTRL_TP_CD);
                String rtlWhCdTemp = resultSet.getString(NPBB002001Constant.RTL_WH_CD);
                String rtlSwhCdTemp = resultSet.getString(NPBB002001Constant.RTL_SWH_CD);
                String rtrnToVndCdTemp = resultSet.getString(NPBB002001Constant.RTRN_TO_VND_CD);
                Boolean flag = (pMsg == null);
                // QC#11713 START
                if (rtrnToVndCdTemp == null) {
                    if (RTRN_CTRL_TP.REFURBISH.equals(rtrnCtrlTpCdTemp)) {
                        // error
                        errorCount++;
                        if (errorMessage == null) {
                            errorMessage = new StringBuilder();
                        }
                        errorMessage.append(System.getProperty(NPBB002001Constant.LINE_SEPARATOR));
                        errorMessage.append(S21MessageFunc.clspGetMessage(NPBB002001Constant.NPBM0008E));
                        continue;
                    } else if (RTRN_CTRL_TP.DISPOSAL.equals(rtrnCtrlTpCdTemp)) {
                        flag = (pMsg == null);
                    } else {
                        flag = true;
                    }
                }
                // QC#11713 END
                if (flag || (rtrnCtrlTpCd == null) || (rtlWhCdTemp == null)) {
                    flag = true;
                }
                if (flag || (rtlSwhCdTemp == null)) {
                    flag = true;
                }
                if (flag || (!rtrnCtrlTpCd.equals(rtrnCtrlTpCdTemp)) || (!rtlWhCd.equals(rtlWhCdTemp))) {
                    flag = true;
                }
                if (flag || (!rtlSwhCd.equals(rtlSwhCdTemp)) || (ZYPCommonFunc.hasValue(rtrnToVndCd)) && (!rtrnToVndCd.equals(rtrnToVndCdTemp))) {
                    flag = true;
                }
                if (flag) {
                    if (pMsg != null) {
                        // call API
                        executeNPZC1030(pMsg);
                    }
                    pMsg = new NPZC103001PMsg();
                    pMsg.prchReqInfo.setValidCount(0);
                    rtrnCtrlTpCd = rtrnCtrlTpCdTemp;
                    rtlWhCd = rtlWhCdTemp;
                    rtlSwhCd = rtlSwhCdTemp;
                    rtrnToVndCd = rtrnToVndCdTemp;

                    ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_CREATE);
                    ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_SUBMIT);
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
                    ZYPEZDItemValueSetter.setValue(pMsg.procDt, salesDate);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqRecTpCd, PRCH_REQ_REC_TP.INVENTORY_REQUEST);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqTpCd, resultSet.getString(NPBB002001Constant.PRCH_REQ_TP_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqCratByPsnCd, ZYPCodeDataUtil.getVarCharConstValue(NPBB002001Constant.PR_CRAT_SYSTEM_USER, globalCompanyCode));
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqRqstByPsnCd, ZYPCodeDataUtil.getVarCharConstValue(NPBB002001Constant.PR_CRAT_SYSTEM_USER, globalCompanyCode));
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqSrcTpCd, PRCH_REQ_SRC_TP.PARTS_REFURB_AUTO);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchGrpCd, resultSet.getString(NPBB002001Constant.PRCH_GRP_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, resultSet.getString(NPBB002001Constant.PRCH_REQ_APVL_STS_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, rtrnToVndCd);
                }
                // Detail
                int index = pMsg.prchReqInfo.getValidCount();
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(index).prntVndCd, resultSet.getString(NPBB002001Constant.RTRN_TO_PRNT_VND_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(index).vndCd, rtrnToVndCd);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(index).mdseCd, resultSet.getString(NPBB002001Constant.MDSE_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(index).prchReqQty, resultSet.getBigDecimal(NPBB002001Constant.INVTY_AVAL_QTY));
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(index).prchReqDispQty, resultSet.getBigDecimal(NPBB002001Constant.INVTY_AVAL_QTY));
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(index).srcInvtyLocCd, resultSet.getString(NPBB002001Constant.INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(index).fromStkStsCd, resultSet.getString(NPBB002001Constant.STK_STS_CD));

                pMsg.prchReqInfo.setValidCount(index + 1);
            }
            if (pMsg != null) {
                // call API
                executeNPZC1030(pMsg);
            }
            if (0 < errorCount) {
                termCd = TERM_CD.WARNING_END;
                sendMail();
                commit();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

    private void executeNPZC1030(NPZC103001PMsg pMsg) {
        NPZC103001 dPZC103001 = new NPZC103001();
        dPZC103001.execute(pMsg, ONBATCH_TYPE.ONLINE);

        // error check
        if (0 == pMsg.xxMsgIdList.getValidCount()) {
            commit();
            totalCommitCount++;
            return;
        }
        // error case
        rollback();
        errorCount++;
        if (errorMessage == null) {
            errorMessage = new StringBuilder();
        }
        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
            if (i != 0) {
                errorMessage.append(System.getProperty(NPBB002001Constant.LINE_SEPARATOR));
            }
            errorMessage.append(S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(i).xxMsgId.getValue()));
        }
    }

    private void sendMail() {
        S21Mail mail = new S21Mail(globalCompanyCode);
        // Get mail group
        S21MailGroup groupTo = new S21MailGroup(globalCompanyCode, NPBB002001Constant.MAIL_TO_GROUP_ID);
        // Get mail group
        S21MailGroup groupFrom = new S21MailGroup(globalCompanyCode, NPBB002001Constant.MAIL_FROM_GROUP_ID);

        // Set address
        List<S21MailAddress> toAddrList = groupTo.getMailAddress();
        if (toAddrList.isEmpty()) {
            throw new S21AbendException(NPBB002001Constant.NPZM0161E);
        }
        mail.setToAddressList(toAddrList);
        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
        mail.setFromAddress(fromAddrList.get(0));

        // Set template parameter
        Date execDate = new Date();
        SimpleDateFormat sdfDate = new SimpleDateFormat();
        sdfDate.applyPattern(NPBB002001Constant.DATE_TIME_FORMAT);

        mailTemplate.setTemplateParameter(NPBB002001Constant.EMAIL_PARAM_BATCH_ID, NPBB002001Constant.BATCH_ID);
        mailTemplate.setTemplateParameter(NPBB002001Constant.EMAIL_PARAM_ERR_DATE, sdfDate.format(execDate));
        mailTemplate.setTemplateParameter(NPBB002001Constant.EMAIL_PARAM_MESSAGE, errorMessage.toString());

        // Set mail subject
        mail.setSubject(mailTemplate.getSubject(NPBB002001Constant.ML_LANG), NPBB002001Constant.ML_LANG_CD);
        mail.setMailTemplate(mailTemplate);

        // Post
        mail.postMail();
    }

    @Override
    protected void termRoutine() {
        // Set EndCode and CommitCount
        setTermState(termCd, totalCommitCount, errorCount, totalCommitCount + errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NPBB002001().executeBatch(NPBB002001.class.getSimpleName());
    }
}
