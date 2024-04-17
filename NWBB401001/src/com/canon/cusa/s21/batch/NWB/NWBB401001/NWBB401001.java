package com.canon.cusa.s21.batch.NWB.NWBB401001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NLZC200001_xxDetailListPMsg;
import business.parts.NLZC200001_xxDetailListPMsgArray;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC153001PMsg;
import business.parts.NWZC153002PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC200001.NLZC200001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NWZ.NWZC153001.NWZC153001;
import com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_TP;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmListResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/** <pre>
 * Return RWS Creation Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   Fujitsu         M.Hara          Create          N/A
 * 2015/12/25   Fujitsu         S.Takami        Update          S21_NA#2503
 * 2016/06/01   Fujitsu         S.Yamamoto      Update          S21_NA#9255
 * 2016/06/02   Fujitsu         M.Hara          Update          S21_NA#9255-2
 * 2017/09/21   Fujitsu         S.Takami        Update          S21_NA#21151
 * 2017/10/03   CITS            K.Ogino         Update          QC#21519
 * 2017/12/12   Fujitsu         S.Takami        Update          S21_NA#21151-3
 * 2017/12/13   Fujitsu         S.Takami        Update          S21_NA#21151-4
 * 2018/05/29   Fujitsu         K.Ishizuka      Update          S21_NA#25854
 * 2018/08/10   Fujitsu         M.Yamada        Update          QC#27596
 * 2018/09/13   Fujitsu         K.Ishizuka      Update          S21_NA#28182
 * 2019/03/17   Fujitsu         K.Ishizuka      Update          S21_NA#30713
 * 2023/12/12   Hitachi         K.Watanabe      Update          QC#61300
 * </pre>
 */
public class NWBB401001 extends S21BatchMain {

    /**
     *  Message IDs
     *
     */
    private static enum MSG_ID {
        /** [@] is mandatory. */
        ZZZM9025E,
        /** No search results found. */
        ZZOM0011E,
        /** Database error : [@@@@]. */
        ZZSM4101E,
        /** Could not get the [@]. */
        NWBM0031E,
    }

    /** Program Id */
    private static final String PROGRAM_ID = "NWBB4010";

    /** Program Name */
    private static final String PROGRAM_NM = "Return RWA Creation Batch.";

    /** Set Item Parent Sub Number */
    private static final String FIXED_SET_ITEM_CPO_DTL_LINE_SUB_NUM = "000";

    /** CPO Return Update API Request Type Parameter. Value = 5:RWS Creation */
//    private static final String RQST_HDR_UPD_STS = "5";  2015/12/25 S21_NA#2503 Del

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Ds Return RWS Api Bean Map. */
    LinkedHashMap<String, DsRtrnRwsApiBean> mapGrouping = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** Mail template ID */
    private  String mailTemplateId = null;

    // 2017/09/21 S21_NA#21151 Add Start
    /** Error Return Detail List */
    private LinkedHashMap<String, List<BookedReturnBean>> errBookedRtrnBeanMap = new LinkedHashMap<String, List<BookedReturnBean>>(0);
    /** Mail Language */
    public static final String ML_LANG = "en";
    /** Mail Language Code */
    public static final String ML_LANG_CD = "ISO-8859-1";
    // 2017/09/21 S21_NA#21151 Add End

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWBB401001().executeBatch();
    }

    @Override
    protected void initRoutine() {
        writeStartLogLn("initRoutine");

        try {
            this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
            mapGrouping = new LinkedHashMap<String, DsRtrnRwsApiBean>();

            // Global Company Code
            this.glblCmpyCd = getGlobalCompanyCode();
            if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
                throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Global Company Code"));
            }

            // Sales Date
            this.salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);

            // UserVariable1
            this.mailTemplateId = getUserVariable1();
            if (!ZYPCommonFunc.hasValue(this.mailTemplateId)) {
                throw new S21AbendException(MSG_ID.NWBM0031E.toString(), toArray("Mail Template ID"));
            }
        } finally {
            writeEndLogLn("initRoutine");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void mainRoutine() {
        writeStartLogLn("mainRoutine");
        try {
            // Search Target Data
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("ordHdrSts", ORD_HDR_STS.VALIDATED);
            ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES_RETURN);
            ssmParam.put("whTpCommon", WH_TP.COMMON);
            ssmParam.put("rtrnLnStsBooked", RTRN_LINE_STS.BOOKED);
            ssmParam.put("rtrnLnStsEnted", RTRN_LINE_STS.ENTERED);
            ssmParam.put("parentSubNum", FIXED_SET_ITEM_CPO_DTL_LINE_SUB_NUM);

            Boolean isSuccess;
            BookedReturnBeanListCreator beanCreator = new BookedReturnBeanListCreator();
            List<BookedReturnBean> readBeanList = (List<BookedReturnBean>) ssmBatchClient.queryObjectList("getBookedReturn", ssmParam, beanCreator);

            // *********************************************************************
            // Set DS Return RWS API parameters.
            // *********************************************************************
            setApiParam(readBeanList, mapGrouping);

            for (DsRtrnRwsApiBean apiParamApiBean : mapGrouping.values()) {
                isSuccess = false;

                // *********************************************************************
                // Call DS Return RWS API.
                // *********************************************************************
                if (callDsRtrnRwsApi(apiParamApiBean)) {

                    // *********************************************************************
                    // Call CPO Return Line Update API.
                    // *********************************************************************
                    if (callCpoRtrnLnUpApi(apiParamApiBean)) {
                        isSuccess = true;
                    }

                }

                if (isSuccess) {
                    // 2017/12/12 S21_NA#21151-3 Add Start
                    boolean changeSmmStatusSuccessFlg = true;
                    List <BookedReturnBean> bookedRtrnBeanList = apiParamApiBean.getReadBeanList();
                    for (BookedReturnBean bookedRtrnBean : bookedRtrnBeanList) {
                        // if (ZYPCommonFunc.hasValue(bookedRtrnBean.getSvcMachMstrPk())) { // 2018/09/13 S21_NA#28182 Mod
                        if (ZYPCommonFunc.hasValue(bookedRtrnBean.getSvcMachMstrPk()) && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, bookedRtrnBean.getInstlBaseCtrlFlg())) {
                            if (!changeSmmStatus(bookedRtrnBean)) {
                                String key = bookedRtrnBean.getErrorHeaderGroupingKey();
                                List<BookedReturnBean> errBookedRtrnBeanList = errBookedRtrnBeanMap.get(key);
                                if (errBookedRtrnBeanList == null) {
                                    errBookedRtrnBeanList = new ArrayList<BookedReturnBean>(0);
                                }
                                errBookedRtrnBeanList.add(bookedRtrnBean);
                                errBookedRtrnBeanMap.put(key, errBookedRtrnBeanList);
                                changeDplyStatusToError(bookedRtrnBean);
                                changeSmmStatusSuccessFlg = false;
                                break;
                            }
                        }
                    }
                    if (!changeSmmStatusSuccessFlg) {
                        rollback();
                        continue;
                    }
                    // 2017/12/12 S21_NA#21151-3 Add End
                    commit();
                } else {
                    rollback();
                }
            }
        } finally {
            writeEndLogLn("mainRoutine");
        }
    }

    @Override
    protected void termRoutine() {
        writeStartLogLn("termRoutine");
        try {
            int normalRecCnt = 0;
            int errRecCnt = 0;
            int listCnt;

            for (DsRtrnRwsApiBean apiBean : mapGrouping.values()) {

                listCnt = apiBean.xxDetailList.getValidCount();
                // S21_NA#9255-2
                if (apiBean.getCpoRtrnLineUpdApiErr().size() > 0) {
                    postErrorMail(apiBean);
                    errRecCnt += listCnt;
                } else {
                    // 2017/12/13 S21_NA#21151-4 Mod Start
//                    normalRecCnt += listCnt;
                    if (!hasSvcMachMstrErr(apiBean)) {
                        normalRecCnt += listCnt;
                    }
                    // 2017/12/13 S21_NA#21151-4 Mod End
                }
            }

            // 2017/09/21 S21_NA#21151 Add Start
            if (this.errBookedRtrnBeanMap.size() > 0) { // 2017/12/13 S21_NA#21151-4 Add if condition
                postErrorMailFromSvcMachMstrErr();
            } // 2017/12/13 S21_NA#21151-4 Add if condition
            // 2017/09/21 S21_NA#21151 Add End
            TERM_CD termCd = TERM_CD.NORMAL_END;

            // 2017/09/21 S21_NA#21151 Add Start
            for (List<BookedReturnBean> bookedReturnBean : errBookedRtrnBeanMap.values()) {
                errRecCnt += bookedReturnBean.size();
            }
            // 2017/09/21 S21_NA#21151 Add End

            if (errRecCnt > 0) {
                termCd = TERM_CD.WARNING_END;
            }

            setTermState(termCd, normalRecCnt, errRecCnt, normalRecCnt + errRecCnt);
        } finally {
            writeEndLogLn("termRoutine");
        }
    }

    // S21_NA#9255-2
    /**
     * postErrorMail
     * @return
     */
//    private boolean postErrorMail(DsRtrnRwsApiBean apiBean) {
//
//        writeStartLogLn("postErrorMail");
//
//        try {
//            final List<NWXC001001MailSubstituteString> sbsStrList = new ArrayList<NWXC001001MailSubstituteString>();
//
//            NWXC001001MailSubstituteString sbsStr;
//
//            sbsStr = new NWXC001001MailSubstituteString();
//            sbsStr.setSbstKey("batchId");
//            sbsStr.setSbstStr(this.getClass().getSimpleName());
//            sbsStrList.add(sbsStr);
//
//            sbsStr = new NWXC001001MailSubstituteString();
//            sbsStr.setSbstKey("batchNm");
//            sbsStr.setSbstStr(PROGRAM_NM);
//            sbsStrList.add(sbsStr);
//
//            sbsStr = new NWXC001001MailSubstituteString();
//            sbsStr.setSbstKey("batchProcLogId");
//            sbsStr.setSbstStr(super.getBatchProcessLogID());
//            sbsStrList.add(sbsStr);
//
//            sbsStr = new NWXC001001MailSubstituteString();
//            sbsStr.setSbstKey("cpoOrdNum");
//            sbsStr.setSbstStr(apiBean.cpoOrdNum.getValue());
//            sbsStrList.add(sbsStr);
//
//            sbsStr = new NWXC001001MailSubstituteString();
//            sbsStr.setSbstKey("dsCpoPosnNum");
//            sbsStr.setSbstStr(apiBean.dsOrdPosnNum.getValue());
//            sbsStrList.add(sbsStr);
//
//            List<String> errMsgList = apiBean.getAllErrMsgID();
//            errMsgList = getDistList(errMsgList);
//            String errInfo = "";
//            for (String msgId : errMsgList) {
//                errInfo += (S21MessageFunc.clspGetMessage(msgId) + "\r\n");
//            }
//            sbsStr = new NWXC001001MailSubstituteString();
//            sbsStr.setSbstKey("ErrorInfo");
//            sbsStr.setSbstStr(errInfo);
//            sbsStrList.add(sbsStr);
//
//            boolean isNormalEnd = new NWXC001001Mail().postMail(this.glblCmpyCd, "NWBB4010", this.mailTemplateId, sbsStrList);
//
//            writeLogLn("NWXC001001Mail().postMail RetVal = %b", isNormalEnd);
//
//            return isNormalEnd;
//
//        } finally {
//            writeEndLogLn("postErrorMail");
//        }
//    }

    private void postErrorMail(DsRtrnRwsApiBean apiBean) {
        writeStartLogLn("postErrorMail");

        try {

            // *****************************************************************
            // Deriving From Mail Address
            // *****************************************************************
            S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, "FROM0005");
            List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

            if (addrFromList == null || addrFromList.size() == 0) {
                return;
            }

            S21MailAddress from = addrFromList.get(0);

            // *****************************************************************
            // Deriving To Mail Address
            // *****************************************************************
            S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, PROGRAM_ID);
            List<S21MailAddress> addrToList = groupTo.getMailAddress();

            if (addrToList == null || addrToList.size() == 0) {
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
            template.setTemplateParameter("batchNm", PROGRAM_NM);
            template.setTemplateParameter("batchProcLogId", super.getBatchProcessLogID());
            template.setTemplateParameter("cpoOrdNum", apiBean.cpoOrdNum.getValue());
            template.setTemplateParameter("dsCpoPosnNum", apiBean.dsOrdPosnNum.getValue());
            List<String> errMsgList = apiBean.getCpoRtrnLineUpdApiErr();
            errMsgList = getDistList(errMsgList);
            // 2017/09/21 S21_NA#21151 Mod Start
//            String errInfo = "";
            StringBuffer errInfo = new StringBuffer("");
            // 2017/09/21 S21_NA#21151 Mod Start
            for (String msgId : errMsgList) {
                // 2017/09/21 S21_NA#21151 Mod Start
                // errInfo += (S21MessageFunc.clspGetMessage(msgId) + "\r\n");
                errInfo.append(S21MessageFunc.clspGetMessage(msgId) + "\r\n");
                // 2017/09/21 S21_NA#21151 Mod End
            }

            // 2017/09/21 S21_NA#21151 Add Start
            // 2017/12/13 S21_NA#21151-4 Mod Start
//            String errKey = BookedReturnBean.getErrorHeaderGroupingKey(apiBean);
            String errKey = apiBean.getErrorHeaderGroupingKey();
            // 2017/12/13 S21_NA#21151-4 Mod End
            addSvcMachMstrUpdErr(errInfo, errKey);
            // 2017/09/21 S21_NA#21151 Add End
            // 2017/09/21 S21_NA#21151 Mod Start
//            template.setTemplateParameter("ErrorInfo", errInfo);
            template.setTemplateParameter("ErrorInfo", errInfo.toString());
            // 2017/09/21 S21_NA#21151 Mod End

            // *****************************************************************
            // Post mail
            // *****************************************************************
            S21Mail mail;
            String mailPk;
            for (S21MailAddress addr : addrToList) {
                mail = new S21Mail(this.glblCmpyCd);
                mail.setFromAddress(from);
                mail.setToAddress(addr);
                mail.setMailTemplate(template);
                mail.setSubject(template.getSubject(ML_LANG), ML_LANG_CD);
                mailPk = mail.postMail();
                if (ZYPCommonFunc.hasValue(mailPk)) {
                    writeLogLn("The postMail Function Error.");
                } else {
                    writeLogLn("mailPk = %s", mailPk);
                }
            }

            return;
        } finally {
            writeEndLogLn("postErrorMail");
        }
    }

    // 2017/09/21 S21_NA#21151 Add Start
    private void addSvcMachMstrUpdErr(StringBuffer errInfoBuffer, String errKey) {

        List<BookedReturnBean> errBookedRtrnBeanList = errBookedRtrnBeanMap.get(errKey);
        if (errBookedRtrnBeanList != null) {
            for (BookedReturnBean errBookedRtrnBean : errBookedRtrnBeanList) {
                errInfoBuffer.append(getErrorMsg(errBookedRtrnBean));
            }
            errBookedRtrnBeanMap.remove(errKey);
        }
    }

    private String getErrorMsg(BookedReturnBean errBookedRtrnBean) {

        StringBuffer errInfoBuffer = new StringBuffer("");
        String errLine = errBookedRtrnBean.getDsCpoLineNum().toString();
        if (ZYPCommonFunc.hasValue(errBookedRtrnBean.getDsCpoLineSubNum()) //
                && BigDecimal.ZERO.compareTo(errBookedRtrnBean.getDsCpoLineSubNum()) != 0) {
            errLine = errLine + "." + errBookedRtrnBean.getDsCpoLineSubNum();
        }
        for (String msgId : errBookedRtrnBean.getSvcMachMastrErrMsgList()) {
            errInfoBuffer.append(errLine + ": " + S21MessageFunc.clspGetMessage(msgId) + "\r\n");
        }
        return errInfoBuffer.toString();
    }

    private void postErrorMailFromSvcMachMstrErr() {
        writeStartLogLn("postErrorMailFromSvcMachMstrErr");

        try {

            // *****************************************************************
            // Deriving From Mail Address
            // *****************************************************************
            S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, "FROM0005");
            List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

            if (addrFromList == null || addrFromList.size() == 0) {
                return;
            }

            S21MailAddress from = addrFromList.get(0);

            // *****************************************************************
            // Deriving To Mail Address
            // *****************************************************************
            S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, PROGRAM_ID);
            List<S21MailAddress> addrToList = groupTo.getMailAddress();

            if (addrToList == null || addrToList.size() == 0) {
                return;
            }

            // *****************************************************************
            // Create Mail Body
            // *****************************************************************
            Set<String> keys = errBookedRtrnBeanMap.keySet();
            List<String> keyList = new ArrayList<String>(0);
            for (String key : keys) {
                keyList.add(key);
            }
            Collections.sort(keyList);

            for (String key : keyList) {
                S21MailTemplate template = new S21MailTemplate(glblCmpyCd, this.mailTemplateId);
                if (template == null) {
                    return;
                }
                List<BookedReturnBean> errBookedRtrnBeanList = errBookedRtrnBeanMap.get(key);
                int dataCnt = 0;
                StringBuffer errInfo = new StringBuffer();
                for (BookedReturnBean errBookedRtrnBean : errBookedRtrnBeanList) {
                    if (dataCnt == 0) {
                        template.setTemplateParameter("batchId", this.getClass().getSimpleName());
                        template.setTemplateParameter("batchNm", PROGRAM_NM);
                        template.setTemplateParameter("batchProcLogId", super.getBatchProcessLogID());
                        template.setTemplateParameter("cpoOrdNum", errBookedRtrnBean.getCpoOrdNum());
                        template.setTemplateParameter("dsCpoPosnNum", errBookedRtrnBean.getDsOrdPosnNum());
                    }
                    errInfo.append(getErrorMsg(errBookedRtrnBean));
                    dataCnt++;
                }
                template.setTemplateParameter("ErrorInfo", errInfo.toString());
                // *****************************************************************
                // Post mail
                // *****************************************************************
                S21Mail mail = new S21Mail(this.glblCmpyCd);
                String mailPk;
                mail.setFromAddress(from);
                mail.setToAddressList(addrToList);
                mail.setMailTemplate(template);
                mail.setSubject(template.getSubject(ML_LANG), ML_LANG_CD);
                mailPk = mail.postMail();
                if (ZYPCommonFunc.hasValue(mailPk)) {
                    writeLogLn("The postMail Function Error.");
                } else {
                    writeLogLn("mailPk = %s", mailPk);
                }
            }

            return;
        } finally {
            writeEndLogLn("postErrorMailFromSvcMachMstrErr");
        }
    }
    // 2017/09/21 S21_NA#21151 Add End
    /**
     * Call CPO Return Line Update API.
     * @param apiBean
     * @return
     */
    private Boolean callCpoRtrnLnUpApi(DsRtrnRwsApiBean apiBean) {

        writeStartLogLn("callCpoRtrnLnUpApi");

        try {
            NWZC153001PMsg apiMsg = new NWZC153001PMsg();

            // Global Company Code
            ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, this.glblCmpyCd);
            // Request Type
            // 2015/12/25 S21_NA#2503 Mod Start
//            ZYPEZDItemValueSetter.setValue(apiMsg.xxRqstTpCd, RQST_HDR_UPD_STS);
            ZYPEZDItemValueSetter.setValue(apiMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_UPD_STS);
            // 2015/12/25 S21_NA#2503 Mod End
            // CPO Order Number
            ZYPEZDItemValueSetter.setValue(apiMsg.cpoOrdNum, apiBean.cpoOrdNum);

            // Sales Date
            ZYPEZDItemValueSetter.setValue(apiMsg.slsDt, salesDate); // 2015/12/25 S21_NA#2503 Add

            List<NWZC153002PMsg> apiDtlMsgList = new ArrayList<NWZC153002PMsg>();
//            NWZC153002PMsg apiDtlMsg; 2015/12/25 S21_NA#2503 Del
//            NLZC404001_xxDetailListPMsg apiDtlBean; 2015/12/25 S21_NA#2503 Del

            int i = 0; // 2015/12/25 S21_NA#2503 Del
         // 2015/12/25 S21_NA#2503 Del Mod Start
//            for (int i = 0; i < apiBean.xxDetailList.getValidCount(); i++) {
            for (; i < apiBean.xxDetailList.getValidCount(); i++) {
                // 2015/12/25 S21_NA#2503 Del Mod End
                NLZC200001_xxDetailListPMsg apiDtlBean = apiBean.xxDetailList.no(i);
//                apiDtlMsg = new NWZC153002PMsg(); 2015/12/25 S21_NA#2503 Del

                // 2015/12/25 S21_NA#2503 Mod Start
//                // CPO Detail Line Number
//                ZYPEZDItemValueSetter.setValue(apiDtlMsg.dsCpoLineNum, convToBigDecimal(apiDtlBean.dsCpoRtrnLineNum.getValue()));
//
//                // CPO Detail Line Sub Number
//                ZYPEZDItemValueSetter.setValue(apiDtlMsg.dsCpoLineSubNum, convToBigDecimal(apiDtlBean.dsCpoRtrnLineSubNum.getValue()));
//
//                apiDtlMsgList.add(apiDtlMsg);
                apiMsg.ordRtrnDtlList.no(i).xxRqstTpCd.setValue(NWZC153001Constant.RQST_DTL_RWS_CREATE);
                apiMsg.ordRtrnDtlList.no(i).dsCpoRtrnLineNum.setValue(apiDtlBean.dsCpoRtrnLineNum.getValue());
                apiMsg.ordRtrnDtlList.no(i).dsCpoRtrnLineSubNum.setValue(apiDtlBean.dsCpoRtrnLineSubNum.getValue());
                // 2015/12/25 S21_NA#2503 Mod Start
            }
            apiMsg.ordRtrnDtlList.setValidCount(i); // 2015/12/25 S21_NA#2503 Add

            NWZC153001 api = new NWZC153001();

            api.execute(apiMsg, apiDtlMsgList, ONBATCH_TYPE.BATCH);

            // S21_NA#9255
            String errMsgId, errMsg;
            for (int n = 0; n < apiMsg.xxMsgIdList.getValidCount(); n++) {
                errMsgId = apiMsg.xxMsgIdList.no(n).xxMsgId.getValue();
                if (!S21StringUtil.isEmpty(errMsgId) && errMsgId.endsWith("E")) { // 2017/12/13 S21_NA#21151-4 Add
                    errMsg = S21MessageFunc.clspGetMessage(errMsgId);
                    // 2017/12/13 S21_NA#21151-4 Mod Start
//                  apiBean.getCpoRtrnLineUpdApiErr().add(errMsg);
                    apiBean.addCpoRtrnLineUpdApiErr(errMsg);
                    // 2017/12/13 S21_NA#21151-4 Mod End
                } // 2017/12/13 S21_NA#21151-4 Add
                S21InfoLogOutput.println(errMsgId);
            }

            return (apiMsg.xxMsgIdList.getValidCount() == 0);

        } finally {
            writeEndLogLn("callCpoRtrnLnUpApi");
        }
    }

    /**
     * Call DS Return RWS API.
     * @param apiParam
     * @return
     */
    private Boolean callDsRtrnRwsApi(DsRtrnRwsApiBean apiParam) {

        // QC#21519 Add
        boolean ret = true;
        writeStartLogLn("callDsRtrnRwsApi");

        try {
            NLZC200001 api;

            api = new NLZC200001();

            writeLogLn("\r\n***** [NLZC200001 API Parameters] %s *****");
            writeLogLn(apiParam.toString());

            api.execute(apiParam, ONBATCH_TYPE.BATCH);

            // S21_NA#9255
            String errMsgId, errMsg;
            for (int n = 0; n < apiParam.xxMsgIdList.getValidCount(); n++) {
                errMsgId = apiParam.xxMsgIdList.no(n).xxMsgId.getValue();
                // QC#21519 Add
                S21InfoLogOutput.println(errMsgId);
                if (errMsgId.lastIndexOf("E") != -1) {
                    errMsg = S21MessageFunc.clspGetMessage(errMsgId);
                    // 2017/12/13 S21_NA#21151-4 Mod Start
//                    apiParam.getCpoRtrnLineUpdApiErr().add(errMsg);
                    apiParam.addCpoRtrnLineUpdApiErr(errMsg);
                    // 2017/12/13 S21_NA#21151-4 Mod End
                    ret = false;
                }
            }

            // 2017/12/13 S21_NA#21151-4 Add Start
            if (apiParam.getCpoRtrnLineUpdApiErr().size() == 0 //
                    && apiParam.RWSNumList.getValidCount() == 0) {
                for (int n = 0; n < apiParam.xxMsgIdList.getValidCount(); n++) {
                    errMsgId = apiParam.xxMsgIdList.no(n).xxMsgId.getValue();
                    // QC#21519 Add
                    S21InfoLogOutput.println(errMsgId);
                    if (errMsgId.endsWith("E") || errMsgId.endsWith("W")) {
                        errMsg = S21MessageFunc.clspGetMessage(errMsgId);
                        apiParam.addCpoRtrnLineUpdApiErr(errMsg);
                    }
                }
                return false;
            }
            // 2017/12/13 S21_NA#21151-4 Add End
            return ret;
        } finally {
            writeEndLogLn("callDsRtrnRwsApi");
        }
    }

    /**
     * Set DS Return RWS API parameters.
     * @param readBeanList
     * @param mapGrp
     */
    private void setApiParam(
                            List<BookedReturnBean> readBeanList,
                            LinkedHashMap<String, DsRtrnRwsApiBean> mapGrp) {
        DsRtrnRwsApiBean groupingBean;
        NLZC200001_xxDetailListPMsgArray dtlMsgList;
        NLZC200001_xxDetailListPMsg dtlMsg;
        String hdrGrpKey;
        int validCnt;

        for (BookedReturnBean bean : readBeanList) {
            hdrGrpKey = bean.getHeaderGroupingKey();
            groupingBean = mapGrp.get(hdrGrpKey);

            if (groupingBean == null) {
                groupingBean = new DsRtrnRwsApiBean(hdrGrpKey);
                mapGrp.put(hdrGrpKey, groupingBean);
            }
            // GLBL_CMPY_CD
            ZYPEZDItemValueSetter.setValue(groupingBean.glblCmpyCd, this.glblCmpyCd);
            // SYS_SRC_CD
            ZYPEZDItemValueSetter.setValue(groupingBean.sysSrcCd, SYS_SRC.S21_ORDER);
            // IBND_SRC_TP_CD
            ZYPEZDItemValueSetter.setValue(groupingBean.inbdSrcTpCd, INBD_SRC_TP.RETURN);
            // SLS_DT
            ZYPEZDItemValueSetter.setValue(groupingBean.slsDt, this.salesDate);
            // CPO_ORD_NUM
            ZYPEZDItemValueSetter.setValue(groupingBean.cpoOrdNum, bean.getCpoOrdNum());
            // DS_ORD_POSN_NUM
            ZYPEZDItemValueSetter.setValue(groupingBean.dsOrdPosnNum, bean.getDsOrdPosnNum());

            dtlMsgList = groupingBean.xxDetailList;

            validCnt = dtlMsgList.getValidCount();
            dtlMsg = dtlMsgList.no(validCnt);
            // DS_CPO_RTRN_LINE_NUM
            ZYPEZDItemValueSetter.setValue(dtlMsg.dsCpoRtrnLineNum, bean.getDsCpoRtrnLineNum());
            // DS_CPO_RTRN_LINE_SUB_NUM
            ZYPEZDItemValueSetter.setValue(dtlMsg.dsCpoRtrnLineSubNum, bean.getDsCpoRtrnLineSubNum());
            // ORD_QTY
            ZYPEZDItemValueSetter.setValue(dtlMsg.ordQty, bean.getOrdQty());
            // SER_NUM
            ZYPEZDItemValueSetter.setValue(dtlMsg.serNum, bean.getSerNum());

            dtlMsgList.setValidCount(++validCnt);
            // 2019/03/13 S21_NA#30713 Add Start
            if (ZYPCommonFunc.hasValue(bean.getDsContrNum())) {
                bean.setSvcMachMstrPk(null);
            }
            // 2019/03/13 S21_NA#30713 Add End
            // 2017/12/12 S21_NA#21151-3 Add Start
            if (ZYPCommonFunc.hasValue(bean.getSvcMachMstrPk())) {
                groupingBean.addReadBeanList(bean);
            }
            // 2017/12/12 S21_NA#21151-3 Add End
        }
    }


    /**
     * BookedReturnBeanListCreator
     * @author 
     *
     */
    protected class BookedReturnBeanListCreator extends S21SsmListResultSetHandlerSupport {

        @Override
        protected List<BookedReturnBean> doProcessQueryResult(ResultSet rs) throws SQLException {
            List<BookedReturnBean> beanList = new ArrayList<BookedReturnBean>();

            rs.last();
            int recCnt = rs.getRow();
            rs.first();
            if (0 == recCnt) {
                writeErrIdLog(MSG_ID.ZZOM0011E.toString());
            } else {
                setOrderCloseBeanList(rs, beanList, recCnt);
            }

            return beanList;
        }

        /**
         * setOrderCloseBeanList
         * @param rs
         * @param beanList
         * @param recAllCnt
         * @throws SQLException
         */
        void setOrderCloseBeanList(ResultSet rs, List<BookedReturnBean> beanList, int recAllCnt) throws SQLException {
            int recCnt = 0;
            BookedReturnBean bookedRtrnBean;
            rs.first();

            do {
                bookedRtrnBean = new BookedReturnBean();

                bookedRtrnBean.setCpoOrdNum(convToString(rs.getString("CPO_ORD_NUM")));
                bookedRtrnBean.setDsOrdPosnNum(convToString(rs.getString("DS_ORD_POSN_NUM")));
                bookedRtrnBean.setRtlWhCd(convToString(rs.getString("RTL_WH_CD")));
                bookedRtrnBean.setDsCpoRtrnLineNum(convToString(rs.getString("DS_CPO_RTRN_LINE_NUM")));
                bookedRtrnBean.setDsCpoRtrnLineSubNum(convToString(rs.getString("DS_CPO_RTRN_LINE_SUB_NUM")));
                bookedRtrnBean.setOrdQty(convToBigDecimal(rs.getBigDecimal("ORD_QTY")));
                bookedRtrnBean.setSerNum(convToString(rs.getString("SER_NUM")));
                // START 2023/12/12 K.Watanabe [QC#61300, ADD]
                bookedRtrnBean.setToBeDeinstalledBy(convToString(rs.getString("ISTL_BY_SVC_PRVD_PTY_CD")));
                // END   2023/12/12 K.Watanabe [QC#61300, ADD]
                // 2017/09/21 S21_NA#21151 Add Start
                if (rs.getString("SVC_MACH_MSTR_PK") != null) {
                    bookedRtrnBean.setSvcMachMstrPk(convToBigDecimal(rs.getString("SVC_MACH_MSTR_PK")));
                } else {
                    bookedRtrnBean.setSvcMachMstrPk(null);
                }
                bookedRtrnBean.setStkStsCd(convToString(rs.getString("STK_STS_CD")));
                if (!ZYPCommonFunc.hasValue(bookedRtrnBean.getStkStsCd())) {
                    bookedRtrnBean.setStkStsCd(STK_STS.GOOD);
                }
                bookedRtrnBean.setInvtyLocCd(convToString(rs.getString("INVTY_LOC_CD")));

                bookedRtrnBean.setDsCpoLineNum(convToBigDecimal(rs.getBigDecimal("DS_CPO_LINE_NUM")));
                bookedRtrnBean.setDsCpoLineSubNum(convToBigDecimal(rs.getBigDecimal("DS_CPO_LINE_SUB_NUM")));
                // 2017/12/12 S21_NA#21151-3 Del Start
//                // Update Service Machine Master Status
//                if (ZYPCommonFunc.hasValue(bookedRtrnBean.getSvcMachMstrPk())) {
//                    if (!changeSmmStatus(bookedRtrnBean)) {
//                        String key = bookedRtrnBean.getErrorHeaderGroupingKey();
//                        List<BookedReturnBean> errBookedRtrnBeanList = errBookedRtrnBeanMap.get(key);
//                        if (errBookedRtrnBeanList == null) {
//                            errBookedRtrnBeanList = new ArrayList<BookedReturnBean>(0);
//                        }
//                        errBookedRtrnBeanList.add(bookedRtrnBean);
//                        errBookedRtrnBeanMap.put(key, errBookedRtrnBeanList);
//                        changeDplyStatusToError(bookedRtrnBean);
//                    } else {
//                        beanList.add(bookedRtrnBean);
//                    }
//                } else {
//                    beanList.add(bookedRtrnBean);
//                }
                // 2017/12/12 S21_NA#21151-3 Del End
                // 2017/09/21 S21_NA#21151 Add End
                bookedRtrnBean.setSvcConfigMstrPk(convToString(rs.getString("SVC_CONFIG_MSTR_PK"))); // 2018/05/29 S21_NA#25854 Add
                bookedRtrnBean.setInstlBaseCtrlFlg(convToString(rs.getString("INSTL_BASE_CTRL_FLG"))); // 2018/05/29 S21_NA#25854 Add
                bookedRtrnBean.setDsContrNum(convToString(rs.getString("DS_CONTR_NUM"))); // 2019/03/17 S21_NA#30713 Add

                beanList.add(bookedRtrnBean);

                writeLogLn("\r\n***** [%d/%d Recourd] *****", ++recCnt, recAllCnt);
                writeLogLn(bookedRtrnBean.toString());
            } while (rs.next());
        }
    }

    /**
     * Convert To String.
     * @param val
     * @return
     */
    private static String convToString(String val) {
        if (ZYPCommonFunc.hasValue(val)) {
            return val;
        } else {
            return String.valueOf("");
        }
    }

    /**
     * Convert To BigDecimal
     * @param val
     * @return
     */
    private static BigDecimal convToBigDecimal(Object val) {
        if (null == val) {
            return BigDecimal.ZERO;
        } else if (val instanceof BigDecimal) {
            return (BigDecimal) val;
        } else {
            return new BigDecimal(val.toString());
        }
    }

    /**
     * Create Array.
     * @param <T>
     * @param param
     * @return
     */
    private static <T> T[] toArray(T... param) {
        return param;
    }

    /**
     * getDistList
     * @param <T>
     * @param srcList
     * @return
     */
    private static <T> List<T> getDistList(List<T> srcList) {
        List<T> retList = new ArrayList<T>();

        for (T val : srcList) {
            if (!retList.contains(val)) {
                retList.add(val);
            }
        }

        return retList;
    }

    /**
     * writeStartLogLn
     * @param methodNm
     */
    private void writeStartLogLn(String methodNm) {
        writeLogLn("[START] %s", methodNm);
    }

    /**
     * writeEndLogLn
     * @param methodNm
     */
    private void writeEndLogLn(String methodNm) {
        writeLogLn("[END] %s\r\n", methodNm);
    }

    /**
     * getSimpleClsNm
     * @param caller
     * @return
     */
    @SuppressWarnings("unchecked")
    private static String getSimpleClsNm(Class caller) {
        return caller.getSimpleName();
    }

    /**
     * writeErrIdLog
     * @param msgId
     */
    private void writeErrIdLog(String msgId) {
        writeLogLn(S21MessageFunc.clspGetMessage(msgId));
    }

    /**
     * WriteLogLn
     * @param format
     * @param param
     */
    private void writeLogLn(String format, Object... param) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("[%s]", getSimpleClsNm(this.getClass())));

        if (param.length > 0) {
            sb.append(String.format(format, param));
        } else {
            sb.append(format);
        }

        S21InfoLogOutput.println(sb.toString());
    }

    // 2017/12/12 S21_NA#21151-3 Add Start (Moved from inner class "BookedReturnBeanListCreator" 2017/09/21 S21_NA#21151)
    private boolean changeSmmStatus(BookedReturnBean bookedRtrnBean) {

        if (!ZYPCommonFunc.hasValue(bookedRtrnBean.getSvcMachMstrPk())) {
            return true;
        }

        SVC_MACH_MSTRTMsg smmTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(smmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(smmTMsg.svcMachMstrPk, bookedRtrnBean.getSvcMachMstrPk());
        smmTMsg = (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(smmTMsg);
        if (smmTMsg == null) {
            return false;
        }
        if (!S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, smmTMsg.svcMachMaintAvalFlg.getValue()) //
                || (!S21StringUtil.isEquals(SVC_MACH_MSTR_STS.INSTALLED, smmTMsg.svcMachMstrStsCd.getValue()) //
                && !S21StringUtil.isEquals(SVC_MACH_MSTR_STS.DEALER_SERVICE, smmTMsg.svcMachMstrStsCd.getValue()))) { // QC#27596
            String errMsgId = "NWZM1472E";
            String errMsg = S21MessageFunc.clspGetMessage(errMsgId);
            bookedRtrnBean.getSvcMachMastrErrMsgList().add(errMsg);
            S21InfoLogOutput.println(errMsgId);
            return false;
        }
        if (!callMachMstrUpdApi(bookedRtrnBean, getRmaModeMachMstrUpdMsg(bookedRtrnBean))) {
            return false;
        }

        if (!callMachMstrUpdApi(bookedRtrnBean, getAllocOnModeMachMstrUpdMsg(bookedRtrnBean))) {
            return false;
        }
        return true;
    }

    private NSZC001001PMsg getRmaModeMachMstrUpdMsg(BookedReturnBean bookedRtrnBean) {

        NSZC001001PMsg machMstrUpdMsg = new NSZC001001PMsg();

        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.slsDt, salesDate);
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.xxModeCd, ProcessMode.RMA.code);
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.svcMachMstrPk, bookedRtrnBean.getSvcMachMstrPk());
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.stkStsCd, bookedRtrnBean.getStkStsCd());
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.rtrnToWhCd, bookedRtrnBean.getInvtyLocCd());

        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxHdrNum, bookedRtrnBean.getCpoOrdNum());
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxLineNum, bookedRtrnBean.getDsCpoRtrnLineNum());
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxLineSubNum, bookedRtrnBean.getDsCpoRtrnLineSubNum());
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_TRANSIT);
        // START 2023/12/12 K.Watanabe [QC#61300, ADD]
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.svcBySvcPrvdPtyCd, bookedRtrnBean.getToBeDeinstalledBy());
        // END   2023/12/12 K.Watanabe [QC#61300, ADD]

        return machMstrUpdMsg;
    }

    private NSZC001001PMsg getAllocOnModeMachMstrUpdMsg(BookedReturnBean bookedRtrnBean) {

        NSZC001001PMsg machMstrUpdMsg = new NSZC001001PMsg();
        machMstrUpdMsg.glblCmpyCd.setValue(glblCmpyCd);
        machMstrUpdMsg.slsDt.setValue(salesDate);
        machMstrUpdMsg.xxModeCd.setValue(ProcessMode.ALLOCATION_ON.code);
        machMstrUpdMsg.svcMachMstrPk.setValue(bookedRtrnBean.getSvcMachMstrPk());
        ZYPEZDItemValueSetter.setValue(machMstrUpdMsg.trxHdrNum, bookedRtrnBean.getCpoOrdNum());
        machMstrUpdMsg.trxLineNum.setValue(bookedRtrnBean.getDsCpoRtrnLineNum());
        machMstrUpdMsg.trxLineSubNum.setValue(bookedRtrnBean.getDsCpoRtrnLineSubNum());

        return machMstrUpdMsg;
    }

    private boolean callMachMstrUpdApi(BookedReturnBean bookedRtrnBean, NSZC001001PMsg machMstrUpdMsg) {
        NSZC001001 machMstrUpdApi = new NSZC001001();
        machMstrUpdApi.execute(machMstrUpdMsg, ONBATCH_TYPE.BATCH);

        boolean isApiResultSuccess = true;
        if (machMstrUpdMsg.xxMsgIdList.getValidCount() > 0) {
            String errMsgId = null;
            String errMsg = null;
            for (int n = 0; n < machMstrUpdMsg.xxMsgIdList.getValidCount(); n++) {
                errMsgId = machMstrUpdMsg.xxMsgIdList.no(n).xxMsgId.getValue();
                errMsg = S21MessageFunc.clspGetMessage(errMsgId);
                bookedRtrnBean.getSvcMachMastrErrMsgList().add(errMsg);
                S21InfoLogOutput.println(errMsgId);
            }
            isApiResultSuccess = false;
        }
        return isApiResultSuccess;
    }

    private void changeDplyStatusToError(BookedReturnBean bookedRtrnBean) {

        DS_CPO_RTRN_DTLTMsg tMsg = new DS_CPO_RTRN_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, bookedRtrnBean.getCpoOrdNum());
        ZYPEZDItemValueSetter.setValue(tMsg.dsCpoRtrnLineNum, bookedRtrnBean.getDsCpoRtrnLineNum());
        ZYPEZDItemValueSetter.setValue(tMsg.dsCpoRtrnLineSubNum, bookedRtrnBean.getDsCpoRtrnLineSubNum());

        tMsg = (DS_CPO_RTRN_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);

        if (tMsg == null) {
            return;
        }
        tMsg.rtrnLineDplyStsCd.setValue(RTRN_LINE_DPLY_STS.ERROR_WAITING_FOR_CANCEL);

        S21FastTBLAccessor.update(tMsg);
    }
    // 2017/12/12 S21_NA#21151-3 End Start (Moved from inner class "BookedReturnBeanListCreator" 2017/09/21 S21_NA#21151)

    // 2017/12/13 S21_NA#21151-4 Add Start
    private boolean hasSvcMachMstrErr(DsRtrnRwsApiBean apiBean) {

        String key = apiBean.getErrorHeaderGroupingKey();

        Set<String> errKeys = this.errBookedRtrnBeanMap.keySet();
        for (String errKey : errKeys) {
            if (S21StringUtil.isEquals(key, errKey)) {
                return true;
            }
        }
        return false;
    }
    // 2017/12/13 S21_NA#21151-4 Add End
}
