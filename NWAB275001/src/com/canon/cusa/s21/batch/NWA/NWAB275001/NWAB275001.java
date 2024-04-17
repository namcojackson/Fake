/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB275001;

import static com.canon.cusa.s21.batch.NWA.NWAB275001.constant.NWAB275001Constant.AUTO_INSTALLED;
import static com.canon.cusa.s21.batch.NWA.NWAB275001.constant.NWAB275001Constant.PROGRAM_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB275001.constant.NWAB275001Constant.PROGRAM_NM;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.GLBL_CMPYTMsg;
import business.parts.NSZC001001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.batch.NWA.NWAB275001.constant.NWAB275001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB275001.constant.NWAB275001Constant.DB_ITEM;
import com.canon.cusa.s21.batch.NWA.NWAB275001.constant.NWAB275001Constant.MSGID;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Machine Master Auto Install Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/04/08   CITS            N.Sang          Create          QC#63689
 *</pre>
 */
public class NWAB275001 extends S21BatchMain {

    /** transaction target CPO header count */
    private int trxCpoCount;

    /** normal processed data count */
    private int normalCount;

    /** error data count */
    private int errorCount;

    /** Terminate Code */
    private TERM_CD termCd;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmClient = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Date */
    private String slsDt;

    /** mail body text for error */
    private StringBuilder mailText;

    /**
     * main.
     * @param args Batch parameters
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NWAB275001().executeBatch(NWAB275001.class.getSimpleName());
    }

    /**
     * Initialize routine
     */
    @Override
    protected void initRoutine() {

        // Initialize SSM
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());

        this.glblCmpyCd = getGlobalCompanyCode();
        if (S21StringUtil.isEmpty(glblCmpyCd)) {
            throw new S21AbendException(MSGID.ZZZM9025E.toString(), new String[] {"Global Company Code" });
        }
        if (isGlblCmpyCdNotExist()) {
            throw new S21AbendException(MSGID.ZZZM9026E.toString(), new String[] {"Global Company Code " + glblCmpyCd });
        }

        slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, PROGRAM_ID);
        if (ZYPDateUtil.isValidDate(slsDt, NWAB275001Constant.DATE_TYPE_YYYYMMDD)) {
            throw new S21AbendException(MSGID.ZZZM9026E.toString(), new String[] {"Sales Date" + slsDt });
        }
        
        this.trxCpoCount = 0;
        this.normalCount = 0;
        this.errorCount = 0;
        this.mailText = new StringBuilder("");
        this.termCd = TERM_CD.NORMAL_END;

    }

    /**
     * Main routine
     */
    @Override
    protected void mainRoutine() {

        Map<String, String> ssmParam = new HashMap<String, String>();
        List<Map<String, Object>> rsltMapList = getTargetData(ssmParam);

        List<NSZC001001PMsg> pMsgList = new ArrayList<NSZC001001PMsg>();
        String keyTrxHdrNum = null;

        NSZC001001PMsg pMsg = new NSZC001001PMsg();

        for (Map<String, Object> rsltMap : rsltMapList) {
            if (keyTrxHdrNum == null) {
                this.trxCpoCount++;
                keyTrxHdrNum = (String) rsltMap.get(DB_ITEM.TRX_HDR_NUM.toString());
            }

            if (!keyTrxHdrNum.equals((String) rsltMap.get(DB_ITEM.TRX_HDR_NUM.toString()))) {
                this.trxCpoCount++;
                execMachineMasterUpdateAPI(pMsgList);
                pMsgList.clear();
                keyTrxHdrNum = (String) rsltMap.get(DB_ITEM.TRX_HDR_NUM.toString());
            }

            pMsg.clear();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.INSTALLATION.code);
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, (BigDecimal) rsltMap.get(DB_ITEM.SVC_MACH_MSTR_PK.toString()));
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.INSTALLED);
            ZYPEZDItemValueSetter.setValue(pMsg.istlDt, this.slsDt);
            ZYPEZDItemValueSetter.setValue(pMsg.shpgPlnNum, (String) rsltMap.get(DB_ITEM.SHPG_PLN_NUM.toString()));
            ZYPEZDItemValueSetter.setValue(pMsg.istlStsUpdCpltFlg, FLG_ON_Y);

            ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, (BigDecimal) rsltMap.get(DB_ITEM.SVC_CONFIG_MSTR_PK.toString()));
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.AT_CUSTOMER);

            ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, (String) rsltMap.get(DB_ITEM.TRX_HDR_NUM.toString()));
            ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineNum, (String) rsltMap.get(DB_ITEM.TRX_LINE_NUM.toString()));
            ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineSubNum, (String) rsltMap.get(DB_ITEM.TRX_LINE_SUB_NUM.toString()));

            ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, (String) rsltMap.get(DB_ITEM.STK_STS_CD.toString()));
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, (String) rsltMap.get(DB_ITEM.SVC_MACH_MSTR_LOC_STS_CD.toString()));

            pMsgList.add(pMsg);
        }
        if (rsltMapList.size() > 0 && pMsgList.size() > 0) {
            execMachineMasterUpdateAPI(pMsgList);
        }
    }

    /**
     * Terminate routine.
     */
    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalCount, this.errorCount, this.trxCpoCount);
        if (TERM_CD.WARNING_END == this.termCd) {
            sendMail();
        }
    }

    /**
     * Execute the Machine Master Update API.(NSZC0010)
     * @param pMsgList List<NSZC001001PMsg>
     */
    private void execMachineMasterUpdateAPI(List<NSZC001001PMsg> pMsgList) {

        NSZC001001 api = new NSZC001001();
        api.execute(pMsgList, ONBATCH_TYPE.BATCH);
        boolean isErrorEnd = false;

        for (NSZC001001PMsg pMsg : pMsgList) {

            List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);
            if (!msgList.isEmpty()) {
                for (String xxMsgId : msgList) {

                    String msg = S21MessageFunc.clspGetMessage(MSGID.NWAM0189E.toString(), editNWAM0189EMsgPrm(pMsg, xxMsgId));
                    mailText.append(msg).append(System.getProperty("line.separator"));
                    S21InfoLogOutput.println(msg);
                }
                isErrorEnd = true;
            }
        }
        if (isErrorEnd) {
            this.errorCount++;
            this.termCd = TERM_CD.WARNING_END;
            rollback();
            return;
        }
        this.normalCount++;
        commit();
        return;
    }

    /**
     * Validation for global company code.
     * @return true : global company code is not exist. <br>
     * false : global company code is exist.
     */
    private boolean isGlblCmpyCdNotExist() {
        GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        tMsg = (GLBL_CMPYTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return true;
        }
        return false;
    }

    /**
     * Get target data.
     * @param ssmParam ssmParam
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getTargetData(Map<String, String> ssmParam) {

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mainMachTp", SVC_MACH_TP.MACHINE);
        ssmParam.put("machTp", SVC_MACH_TP.ACCESSORY);
        ssmParam.put("CtxTpInstalled", AUTO_INSTALLED);
        ssmParam.put("newCfgActTp", CONFIG_TP.NEW);
        ssmParam.put("shpgStsArrived", SHPG_STS.ARRIVED);
        ssmParam.put("svcMachMstrStsInstalled", SVC_MACH_MSTR_STS.INSTALLED);
        ssmParam.put("svcMachMstrStsW4I", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);

        return (List<Map<String, Object>>) this.ssmClient.queryObjectList("getTargetData", ssmParam);
    }

    /**
     * Send Mail
     */
    private void sendMail() {

        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, NWAB275001Constant.MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(NWAB275001Constant.MAIL_KEY_FROM);

        S21MailAddress fromAddr = null;
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if (addrFromList != null && !addrFromList.isEmpty()) {

            fromAddr = addrFromList.get(0);
            S21Mail mail = new S21Mail(glblCmpyCd);
            mail.setFromAddress(fromAddr);

            S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, NWAB275001Constant.MAIL_GROUP_ID_TO);
            groupTo.setMailKey1(NWAB275001Constant.MAIL_KEY_TO);

            List<S21MailAddress> addrToList = groupTo.getMailAddress();
            if (addrToList.isEmpty()) {
                String[] errMsg = new String[] {NWAB275001Constant.MAIL_GROUP_ID_TO, NWAB275001Constant.MAIL_KEY_TO };
                S21InfoLogOutput.println(MSGID.NWAM0516E.toString(), errMsg);
                throw new S21AbendException(MSGID.NWAM0516E.toString(), errMsg);
            }
            mail.setToAddressList(addrToList);

            S21MailTemplate mailTemp = new S21MailTemplate(glblCmpyCd, NWAB275001Constant.MAIL_TEMPLATE_ID_M001);

            if (mailTemp != null) {
                mailTemp.setTemplateParameter(NWAB275001Constant.MAIL_TEMPLATE_KEY_BATCH_ID, PROGRAM_ID);
                mailTemp.setTemplateParameter(NWAB275001Constant.MAIL_TEMPLATE_KEY_BATCH_NM, PROGRAM_NM);
                mailTemp.setTemplateParameter(NWAB275001Constant.MAIL_TEMPLATE_KEY_ERR_DATA, this.mailText.toString());
                mail.setMailTemplate(mailTemp);

                String resCd = mail.postMail();
                if (!ZYPCommonFunc.hasValue(resCd)) {
                    throw new S21AbendException(MSGID.NWAM0268E.toString());
                }
            } else {
                String[] errMsg = new String[] {NWAB275001Constant.MAIL_TEMPLATE_ID_M001 };
                S21InfoLogOutput.println(MSGID.NWAM0319E.toString(), errMsg);
                throw new S21AbendException(MSGID.NWAM0319E.name(), errMsg);
            }
        } else {
            String[] errMsg = new String[] {NWAB275001Constant.MAIL_GROUP_ID_FROM, NWAB275001Constant.MAIL_KEY_FROM };
            S21InfoLogOutput.println(MSGID.NWAM0516E.name(), errMsg);
            throw new S21AbendException(MSGID.NWAM0516E.name(), errMsg);
        }
    }

    /**
     * Edit message for NWAM0189E
     * @param pMsg
     * @param msgList
     * @return String[]
     */
    private String[] editNWAM0189EMsgPrm(NSZC001001PMsg pMsg, String xxMsgId) {

        StringBuilder sb = new StringBuilder("");

        editKeyMsg(pMsg, sb);
        sb.append("APIID[").append(NWAB275001Constant.API_ID_NSZC0010).append("] ");
        sb.append("MSGID[").append(xxMsgId).append("] ");
        sb.append("MSGTXT[").append(S21MessageFunc.clspGetMessage(xxMsgId)).append("]");

        String[] s = new String[] {sb.toString() };
        return s;
    }

    /**
     * Edit message for key of data.
     * @param pMsg
     * @param sb
     */
    private void editKeyMsg(NSZC001001PMsg pMsg, StringBuilder sb) {
        sb.append("TRX_HDR_NUM : ").append(pMsg.cpoOrdNum.getValue()).append("] [");
        sb.append("TRX_LINE_NUM : ").append(pMsg.cpoDtlLineNum.getValue()).append("] [");
        sb.append("TRX_LINE_SUB_NUM : ").append(pMsg.cpoDtlLineSubNum.getValue()).append("] [");
        sb.append("SHPG_PLN_NUM : ").append(pMsg.shpgPlnNum.getValue()).append("] ");
    }

}
