/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC570001;

import static com.canon.cusa.s21.api.NMZ.NMZC570001.constant.NMZC570001Constant.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CTRYTMsg;
import business.db.DS_ACCT_RVW_PROSTMsg;
import business.db.DS_ACCT_RVW_PROSTMsgArray;
import business.db.STTMsg;
import business.parts.NMZC570001PMsg;
import business.parts.NMZC570001_xxErrProsRvwListPMsgArray;
import business.parts.NMZC570001_xxProsRvwListPMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_XREF_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 *<pre>
 * Prospect Review Inbound API.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/21/2016   Hitachi         Y.Takeno        Create          N/A
 * 08/04/2016   Fujitsu         N.Sugiura       Update          QC#12418
 * 12/19/2016   Fujitsu         Y.Kanefusa      Update          QC#16681
 * 06/14/2017   Hitachi         E.Kameishi      Update          QC#18619
 * 07/26/2017   Fujitsu         H.Sugawara      Update          QC#19874
 * 08/23/2017   Fujitsu         R.Nakamura      Update          QC#20658
 * 11/21/2017   Fujitsu         N.Sugiura       Update          QC#21629
 * 03/27/2018   Fujitsu         R.Nakamura      Update          QC#25062
 * 08/07/2018   Fujitsu         Hd.Sugawara     Update          QC#27513
 * 09/10/2018   Fujitsu         Hd.Sugawara     Update          QC#25762
 * 09/11/2018   Fujitsu         S.Kosaka        Update          QC#25180
 *</pre>
 */
public class NMZC570001 extends S21ApiCommonBase {

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient;

    /** hasError */
    private boolean hasError;

    /** Sales Date */
    private String slsDt;

    /** Mail Row Count */
    private int mailRowCount = DEFAULT_MAIL_ROW_COUNT;

    /**
     * initialize API.
     */
    public NMZC570001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(NMZC570001.class);
        hasError = false;
    }

    /**
     * <pre>
     * Prospect Review Inbound API (List)
     * </pre>
     * @param params List<NMZC570001PMsg>
     * @param batchType batchType
     */
    public void execute(final List<NMZC570001PMsg> params, final ONBATCH_TYPE batchType) {
        for (NMZC570001PMsg msg : params) {
            execute(msg, batchType);
        }
    }

    /**
     * <pre>
     * Prospect Review Inbound API
     * </pre>
     * @param param NMZC570001PMsg
     * @param batchType batchType
     */
    public void execute(final NMZC570001PMsg param, final ONBATCH_TYPE batchType) {
        this.hasError = false;

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        if (checkParam(msgMap)) {
            doProcess(msgMap);
        }
        msgMap.flush();
    }

    private boolean checkParam(S21ApiMessageMap msgMap) {
        NMZC570001PMsg pMsg = (NMZC570001PMsg) msgMap.getPmsg();

        // Global Company Code
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, GLBL_CMPY_CD);
        }
        // Sales Date
        if (ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            this.slsDt = pMsg.slsDt.getValue();
        } else {
            this.slsDt = ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue());
        }
        // Prospect Review List
        if (pMsg.xxProsRvwList.getValidCount() == 0) {
            msgMap.addXxMsgIdWithPrm(ZZM9000E, new String[] {"Prospect Review List" });
            return false;
        }

        return true;
    }

    private void doProcess(S21ApiMessageMap msgMap) {
        int normalCount = 0;
        int errorCount = 0;
        int skipCount = 0;

        NMZC570001PMsg pMsg = (NMZC570001PMsg) msgMap.getPmsg();

        // VAR_CHAR_CONST : NMZC5700_MAIL_ROW_CNT
        String strMailRowCount = ZYPCodeDataUtil.getVarCharConstValue(CONST_NAME_NMZC5700_MAIL_ROW_CNT, pMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(strMailRowCount)) {
            mailRowCount = Integer.valueOf(strMailRowCount);
        }

        for (int i = 0; i < pMsg.xxProsRvwList.getValidCount(); i++) {
            NMZC570001_xxProsRvwListPMsg propsRvw = pMsg.xxProsRvwList.no(i);

            // 2017/11/21 CSA-QC#20597 Add Start
            setRvwProsNum(propsRvw);
            // 2017/11/21 CSA-QC#20597 Add End

            if (!checkProsRvwList(pMsg, propsRvw)) {
                errorCount++;
                continue;
            }

            DS_ACCT_RVW_PROSTMsg acctRvwProsTMsg = findDsAcctRvwPros(pMsg.glblCmpyCd.getValue(), propsRvw.rvwProsNum.getValue());
            if (acctRvwProsTMsg == null) {
                // insert DS_ACCT_RVW_PROS
                acctRvwProsTMsg = new DS_ACCT_RVW_PROSTMsg();
                ZYPEZDItemValueSetter.setValue(acctRvwProsTMsg.dsAcctRvwProsPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ACCT_RVW_PROS_SQ));
                setDsAcctRvwPros(pMsg,  propsRvw,  acctRvwProsTMsg);
                S21ApiTBLAccessor.insert(acctRvwProsTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(acctRvwProsTMsg.getReturnCode())) {
                    addProsRvwListMessage(pMsg, propsRvw, NMAM8570E, null);
                    errorCount++;
                    continue;
                }

            } else if (!ZYPCommonFunc.hasValue(acctRvwProsTMsg.prosRvwStsCd)) {
                // update DS_ACCT_RVW_PROS
                setDsAcctRvwPros(pMsg,  propsRvw,  acctRvwProsTMsg);
                S21ApiTBLAccessor.update(acctRvwProsTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(acctRvwProsTMsg.getReturnCode())) {
                    addProsRvwListMessage(pMsg, propsRvw, NMAM8570E, null);
                    errorCount++;
                    continue;
                }
            } else {
                // skip record
                skipCount++;
                continue;
            }

            normalCount++;
        }

        if (this.hasError) {
            // set xxMsgId
            msgMap.addXxMsgId(NMAM8304E);
        }
        if (this.hasError || skipCount > 0) {
            // send error mail
            sendErrorMail(pMsg, normalCount, errorCount, skipCount);
        }
    }

    private boolean checkProsRvwList(NMZC570001PMsg pMsg, NMZC570001_xxProsRvwListPMsg prListPMsg) {
        // Prospect Review ID
        if (!ZYPCommonFunc.hasValue(prListPMsg.prosRvwId)) {
            addProsRvwListMessage(pMsg, prListPMsg, ZZZM9007E, new String[] {"Prospect Review ID" });
            return false;
        }
        // Review Prospect Number
        if (!ZYPCommonFunc.hasValue(prListPMsg.rvwProsNum)) {
            addProsRvwListMessage(pMsg, prListPMsg, ZZZM9007E, new String[] {"Review Prospect Number" });
            return false;
        }
        // Before Direct Sales Account Name
        if (!ZYPCommonFunc.hasValue(prListPMsg.befDsAcctNm)) {
            addProsRvwListMessage(pMsg, prListPMsg, ZZZM9007E, new String[] {"Before Direct Sales Account Name" });
            return false;
        }
        // Mod Start 2018/08/07 QC#27513
        //// Before Bill To First Line Address
        //if (!ZYPCommonFunc.hasValue(prListPMsg.befBillToFirstLineAddr)) {
        //    addProsRvwListMessage(pMsg, prListPMsg, ZZZM9007E, new String[] {"Before Bill To First Line Address" });
        //    return false;
        //}
        //// Before Bill To City Address
        //if (!ZYPCommonFunc.hasValue(prListPMsg.befBillToCtyAddr)) {
        //    addProsRvwListMessage(pMsg, prListPMsg, ZZZM9007E, new String[] {"Before Bill To City Address" });
        //    return false;
        //}
        //// Before Bill to State Name
        //if (!ZYPCommonFunc.hasValue(prListPMsg.befBillToStNm)) {
        //    addProsRvwListMessage(pMsg, prListPMsg, ZZZM9007E, new String[] {"Before Bill to State Name" });
        //    return false;
        //}
        //// Before Bill To Postal Code
        //if (!ZYPCommonFunc.hasValue(prListPMsg.befBillToPostCd)) {
        //    addProsRvwListMessage(pMsg, prListPMsg, ZZZM9007E, new String[] {"Before Bill To Postal Code" });
        //    return false;
        //}
        // Before Location First Line Address
        if (!ZYPCommonFunc.hasValue(prListPMsg.befLocFirstLineAddr)) {
            addProsRvwListMessage(pMsg, prListPMsg, ZZZM9007E, new String[] {"Before Location First Line Address" });
            return false;
        }
        // Before Ship to City Address
        if (!ZYPCommonFunc.hasValue(prListPMsg.befShipToCtyAddr)) {
            addProsRvwListMessage(pMsg, prListPMsg, ZZZM9007E, new String[] {"Before Ship to City Address" });
            return false;
        }
        // Before Ship to State Name
        if (!ZYPCommonFunc.hasValue(prListPMsg.befShipToStNm)) {
            addProsRvwListMessage(pMsg, prListPMsg, ZZZM9007E, new String[] {"Before Ship to State Name" });
            return false;
        }
        // Before Ship To Postal Code
        if (!ZYPCommonFunc.hasValue(prListPMsg.befShipToPostCd)) {
            addProsRvwListMessage(pMsg, prListPMsg, ZZZM9007E, new String[] {"Before Ship To Postal Code" });
            return false;
        }
        // Mod End 2018/08/07 QC#27513

        return true;
    }

    private void addProsRvwListMessage(NMZC570001PMsg pMsg, NMZC570001_xxProsRvwListPMsg prListPMsg, String msgId, String[] msgPrms) {
        int count = pMsg.xxErrProsRvwList.getValidCount();
        if (count >= pMsg.xxErrProsRvwList.length()) {
            return;
        }

        String message = S21MessageFunc.clspGetMessage(msgId, msgPrms);
        ZYPEZDItemValueSetter.setValue(pMsg.xxErrProsRvwList.no(count).appMsgTxt, cutOffMessage(message, MAIL_ITEM_MAX_LENGTH_MESSSAGE));
        ZYPEZDItemValueSetter.setValue(pMsg.xxErrProsRvwList.no(count).dsAcctNm, prListPMsg.befDsAcctNm);
        ZYPEZDItemValueSetter.setValue(pMsg.xxErrProsRvwList.no(count).rvwProsNum, prListPMsg.rvwProsNum);
        pMsg.xxErrProsRvwList.setValidCount(count + 1);
        hasError = true;
    }

    private String cutOffMessage(String message, int maxLength) {
        if (message == null) {
            return null;
        } else if (message.length() > maxLength) {
            return message.substring(0, maxLength);
        } else {
            return message;
        }
    }

    private DS_ACCT_RVW_PROSTMsg findDsAcctRvwPros(String glblCmpyCd, String rvwProsNum) {
        DS_ACCT_RVW_PROSTMsg inMsg = new DS_ACCT_RVW_PROSTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("rvwProsNum01", rvwProsNum);

        DS_ACCT_RVW_PROSTMsgArray tMsgArray = (DS_ACCT_RVW_PROSTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            return tMsgArray.no(0);
        }
        return null;
    }

    private void setDsAcctRvwPros(NMZC570001PMsg pMsg, NMZC570001_xxProsRvwListPMsg propsRvw,  DS_ACCT_RVW_PROSTMsg tMsg) {

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prosRvwId, propsRvw.prosRvwId);
        ZYPEZDItemValueSetter.setValue(tMsg.rvwProsNum, propsRvw.rvwProsNum);
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, propsRvw.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(tMsg.locNum, propsRvw.locNum);
        ZYPEZDItemValueSetter.setValue(tMsg.befDsAcctNm, propsRvw.befDsAcctNm);
        ZYPEZDItemValueSetter.setValue(tMsg.befDsAcctDescTxt, propsRvw.befDsAcctDescTxt);
        ZYPEZDItemValueSetter.setValue(tMsg.befBillToFirstLineAddr, propsRvw.befBillToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.befBillToCtyAddr, propsRvw.befBillToCtyAddr);
        // 08/04/2016 QC#12418 Mod Start
        // ZYPEZDItemValueSetter.setValue(tMsg.befBillToStNm, propsRvw.befBillToStNm);
        ZYPEZDItemValueSetter.setValue(tMsg.befBillToStCd, S21StringUtil.subStringByLength(propsRvw.befBillToStNm.getValue(), 0, 2));
        // 08/04/2016 QC#12418 Mod End
        ZYPEZDItemValueSetter.setValue(tMsg.befBillToCntyNm, propsRvw.befBillToCntyNm);
        ZYPEZDItemValueSetter.setValue(tMsg.befBillToPostCd, propsRvw.befBillToPostCd);
        ZYPEZDItemValueSetter.setValue(tMsg.befBillToDsAcctNm, propsRvw.befBillToDsAcctNm);
        ZYPEZDItemValueSetter.setValue(tMsg.befLocFirstLineAddr, propsRvw.befLocFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.befShipToCtyAddr, propsRvw.befShipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.befShipToStNm, propsRvw.befShipToStNm);
        ZYPEZDItemValueSetter.setValue(tMsg.befShipToCntyNm, propsRvw.befShipToCntyNm);
        ZYPEZDItemValueSetter.setValue(tMsg.befShipToPostCd, propsRvw.befShipToPostCd);
        ZYPEZDItemValueSetter.setValue(tMsg.befDsLocRevAmt, propsRvw.befDsLocRevAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.befDunsNum, propsRvw.befDunsNum);
        ZYPEZDItemValueSetter.setValue(tMsg.befDsCustSicDescTxt, propsRvw.befDsCustSicDescTxt);
        ZYPEZDItemValueSetter.setValue(tMsg.befDsLocEmpNum, propsRvw.befDsLocEmpNum);
        ZYPEZDItemValueSetter.setValue(tMsg.befDsCustSicCd, propsRvw.befDsCustSicCd);
        ZYPEZDItemValueSetter.setValue(tMsg.befDsUltDunsNum, propsRvw.befDsUltDunsNum);
        ZYPEZDItemValueSetter.setValue(tMsg.befTelNum, propsRvw.befTelNum);
        ZYPEZDItemValueSetter.setValue(tMsg.befFaxNum, propsRvw.befFaxNum);
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctUrl, propsRvw.dsAcctUrl);
        ZYPEZDItemValueSetter.setValue(tMsg.acctProgrCd, propsRvw.acctProgrCd);
        if (ZYPCommonFunc.hasValue(propsRvw.acctIdAddrRvwProsFlg)) {
            ZYPEZDItemValueSetter.setValue(tMsg.acctIdAddrRvwProsFlg, propsRvw.acctIdAddrRvwProsFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.acctIdAddrRvwProsFlg, ZYPConstant.FLG_OFF_N);
        }
        
        // START 2017/06/14 E.Kameishi [QC#18619,ADD]
        // START 2018/09/11 S.Kosaka [QC#25180,UPD]
        // ZYPEZDItemValueSetter.setValue(tMsg.befCtryNm, getCtryNmByCtryCd(pMsg.glblCmpyCd.getValue(), propsRvw.befCtryCd.getValue()));
        String countryName = null;
        if (ZYPCommonFunc.hasValue(propsRvw.befCtryCd)) {
            countryName = getCtryNmByCtryCd(pMsg.glblCmpyCd.getValue(), propsRvw.befCtryCd.getValue());
        } else {
            String countryCode = getCtryCdByStCd(pMsg.glblCmpyCd.getValue(), propsRvw.befShipToStNm.getValue());
            countryName = getCtryNmByCtryCd(pMsg.glblCmpyCd.getValue(), countryCode);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.befCtryNm, countryName);
        // END 2018/09/11 S.Kosaka [QC#25180,UPD]
        // END 2017/06/14 E.Kameishi [QC#18619,ADD]
        // QC#16681 2016/12/19 Mod Start
        // ZYPEZDItemValueSetter.setValue(tMsg.acctSrcTxt, propsRvw.acctSrcTxt);
        ZYPEZDItemValueSetter.setValue(tMsg.acctSrcTxt, CONST_ACCT_SRC_TXT);
        // QC#16681 2016/12/19 Mod End
        // Mod Start 2017/07/26 QC#19874
        //ZYPEZDItemValueSetter.setValue(tMsg.befPsnNum, propsRvw.befPsnNum);
        if(ZYPCommonFunc.hasValue(propsRvw.befPsnNum)) {
            // Mod Start 2018/03/27 QC#25062
//            ZYPEZDItemValueSetter.setValue(tMsg.befPsnNum, this.getPsnCdFromSfUserMappingTbl(propsRvw.befPsnNum.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.befPsnNum, this.getPsnCdFromSfUserMappingTbl(propsRvw.befPsnNum.getValue(), propsRvw.lineBizTpNm.getValue()));
            // Mod End 2018/03/27 QC#25062
        }else{
            tMsg.befPsnNum.clear();
        }
        // Mod End 2017/07/26 QC#19874
        if (ZYPCommonFunc.hasValue(propsRvw.xtrnlCratDtTmTs)) {
            String strXtrnlCratDtTmTsUtc = propsRvw.xtrnlCratDtTmTs.getValue() + FILLER_MSEC_STRING;
            String strXtrnlCratDtTmTsSys = ZYPLocalTimeUtil.convertTimeUTC2Sys(strXtrnlCratDtTmTsUtc);
            ZYPEZDItemValueSetter.setValue(tMsg.xtrnlCratDtTmTs, strXtrnlCratDtTmTsSys.substring(0, LENGTH_TS));
        }
        ZYPEZDItemValueSetter.setValue(tMsg.dsXrefAcctTpCd, DS_XREF_ACCT_TP.SFDC);
        // Mod Start 2017/08/22 QC#20658
//        ZYPEZDItemValueSetter.setValue(tMsg.lineBizTpCd, LINE_BIZ_TP.ESS);
        if (!ZYPCommonFunc.hasValue(propsRvw.lineBizTpNm)) {
            // do nothing
        } else if (LINE_BIZ_TP_NM_LFS.equals(propsRvw.lineBizTpNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.lineBizTpCd, LINE_BIZ_TP.LFS);
        } else if (LINE_BIZ_TP_NM_PPS.equals(propsRvw.lineBizTpNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.lineBizTpCd, LINE_BIZ_TP.PPS);
        } else if (LINE_BIZ_TP_NM_ESS.equals(propsRvw.lineBizTpNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.lineBizTpCd, LINE_BIZ_TP.ESS);
        }
        // Mod End 2017/08/22 QC#20658

        ZYPEZDItemValueSetter.setValue(tMsg.trtyTrkHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.dupAcctLocFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.prosSendFlg, ZYPConstant.FLG_OFF_N);
    }

    private void sendErrorMail(NMZC570001PMsg pMsg, int normalCount, int errorCount, int skipCount) {
        // Get From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(pMsg.glblCmpyCd.getValue(), MAIL_GROUP_ID_FROM);
        S21MailAddress fromAddress;
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NMAM8028E, new String[] {MAIL_GROUP_FROM, MAIL_GROUP_ID_FROM, "" });
        }
        if (!ZYPCommonFunc.hasValue(addrFromList.get(0).getAddress())) {
            throw new S21AbendException(NMAM8029E, new String[] {MAIL_GROUP_FROM, MAIL_GROUP_ID_FROM, "" });
        }

        // Get To Mail Address.
        S21MailGroup groupTo = new S21MailGroup(pMsg.glblCmpyCd.getValue(), MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NMAM8028E, new String[] {MAIL_GROUP_TO, MAIL_GROUP_ID_TO, "" });
        }
        if (!ZYPCommonFunc.hasValue(addrToList.get(0).getAddress())) {
            throw new S21AbendException(NMAM8029E, new String[] {MAIL_GROUP_TO, MAIL_GROUP_ID_TO, "" });
        }

        // Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(pMsg.glblCmpyCd.getValue(), MAIL_TEMPLATE_ID_01);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NMAM8031E, new String[] {MAIL_TEMPLATE_ID_01 });
        }

        fromAddress = addrFromList.get(0);

        S21Mail mail = new S21Mail(pMsg.glblCmpyCd.getValue());

        // Set Mail Address.
        mail.setFromAddress(fromAddress);
        mail.setToAddressList(addrToList);

        // Create Mail Template Parameter Map
        Map<String, String> paramMap = createMailTemplateParamMap(pMsg.xxErrProsRvwList, normalCount, errorCount, skipCount);

        // Set Parameter
        Iterator<Map.Entry<String, String>> iterator = paramMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            template.setTemplateParameter(entry.getKey(), entry.getValue());
        }

        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject());
        mail.postMail();
    }

    private Map<String, String> createMailTemplateParamMap(NMZC570001_xxErrProsRvwListPMsgArray xxErrProsRvwList, int normalCount, int errorCount, int skipCount) {
        Map<String, String> prmMap = new HashMap<String, String>();
        String errDate = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);

        // ${ApiId}
        prmMap.put(MAIL_ITEM_API_ID, MAIL_ITEM_VALUE_API_ID);
        // ${FunctionName}
        prmMap.put(MAIL_ITEM_FUNCTION_NAME, MAIL_ITEM_VALUE_FUNCTION_NAME);

        // ${TableName}
        prmMap.put(MAIL_ITEM_TABLE_NAME, MAIL_ITEM_VALUE_TABLE_NAME);

        // ${NormalRecordCount}
        prmMap.put(MAIL_ITEM_NORMAL_CNT, String.valueOf(normalCount));

        // ${ErrorRecordCount}
        prmMap.put(MAIL_ITEM_ERROR_CNT, String.valueOf(errorCount));

        // ${SkipRecordCount}
        prmMap.put(MAIL_ITEM_SKIP_CNT, String.valueOf(skipCount));

        // ${TotalRecordCount}
        int totalCount = normalCount + errorCount + skipCount;
        prmMap.put(MAIL_ITEM_TOTAL_CNT, String.valueOf(totalCount));

        // ${errDate}
        prmMap.put(MAIL_ITEM_ERR_DATE, errDate);

        // ${message}
        String newLine = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mailRowCount && i < xxErrProsRvwList.getValidCount(); i++) {
            String rvwProsNum = xxErrProsRvwList.no(i).rvwProsNum.getValue();
            String appMsgTxt = xxErrProsRvwList.no(i).appMsgTxt.getValue();
            sb.append(String.format(MAIL_ITEM_FORMAT_MESSAGE, rvwProsNum, appMsgTxt));
            sb.append(newLine);
        }
        prmMap.put(MAIL_ITEM_MESSAGE, sb.toString());

        return prmMap;
    }
    // START 2017/06/14 E.Kameishi [QC#18619,ADD]
    /**
     * @param ctryCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private static String getCtryNmByCtryCd(String glblCmpyCd, String ctryCd) {
        CTRYTMsg tMsg = new CTRYTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.ctryCd.setValue(ctryCd);
        tMsg = (CTRYTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            return tMsg.ctryNm.getValue();
        }
        return "";
    }
    // END 2017/06/14 E.Kameishi [QC#18619,ADD]

    // Add Start 2017/07/26 QC#19874
    // Mod Start 2018/03/27 QC#25062
    /**
     * @param sfUserId String
     * @retrun String
     */
//    private String getPsnCdFromSfUserMappingTbl(String sfUserId) {
    private String getPsnCdFromSfUserMappingTbl(String sfUserId, String lobCd) {
        // CANON_E404_SF_USER_MAPPING_TBL.SF_USER_ID
        Map<String, String> param = new HashMap<String, String>();
        param.put("sfUserId", sfUserId);
        if (LINE_BIZ_TP_NM_LFS.equals(lobCd)) {
            param.put("lobCd", LINE_BIZ_TP.LFS);
        } else if (LINE_BIZ_TP_NM_PPS.equals(lobCd)) {
            param.put("lobCd", LINE_BIZ_TP.PPS);
        } else if (LINE_BIZ_TP_NM_ESS.equals(lobCd)) {
            // Mod Start 2018/09/10 QC#25762
            //param.put("lobCd", LINE_BIZ_TP.PPS);
            param.put("lobCd", LINE_BIZ_TP.ESS);
            // Mod End 2018/09/10 QC#25762
        } else {
            return null;
        }

        String psnCd = (String) this.ssmBatchClient.queryObject("getPsnCdFromSfUserMappingTbl", param);

        return psnCd;
    }
    // Mod End 2018/03/27 QC#25062
    // Add End 2017/07/26 QC#19874
    // 2017/11/21 CSA-QC#20597 Add Start
    private static void setRvwProsNum(NMZC570001_xxProsRvwListPMsg propsRvw) {
        if (!(ZYPCommonFunc.hasValue(propsRvw.lineBizTpNm) && LINE_BIZ_TP_NM_ESS.equals(propsRvw.lineBizTpNm.getValue()))) {
            ZYPEZDItemValueSetter.setValue(propsRvw.rvwProsNum, propsRvw.xxRvwProsNum);
            ZYPEZDItemValueSetter.setValue(propsRvw.befBillToDsAcctNm, propsRvw.xxBefBillToDsAcctNm);
            ZYPEZDItemValueSetter.setValue(propsRvw.acctIdAddrRvwProsFlg, propsRvw.xxAcctIdAddrRvwProsFlg);
            ZYPEZDItemValueSetter.setValue(propsRvw.acctSrcTxt, propsRvw.xxAcctSrcTxt);
        }
    }
    // 2017/11/21 CSA-QC#20597 Add End

    // START 2018/09/11 S.Kosaka [QC#25180,UPD]
    /**
     * @param ctryCd String
     * @param stCd String
     * @return returnValue
     */
    private static String getCtryCdByStCd(String glblCmpyCd, String stCd) {
        STTMsg tMsg = new STTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.stCd.setValue(stCd);
        tMsg = (STTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            return tMsg.ctryCd.getValue();
        }
        return "";
    }
    // END 2018/09/11 S.Kosaka [QC#25180,UPD]
}
