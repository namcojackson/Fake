/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1100.common;

import static business.blap.NSAL1100.constant.NSAL1100Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.api.NSZ.NSZC053001.NSZC053001;
import com.canon.cusa.s21.api.NSZ.NSZC053001.constant.NSZC053001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.user.S21NwfUserRole;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizProcess;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizWorkItem;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL1100.NSAL1100CMsg;
import business.blap.NSAL1100.NSAL1100Query;
import business.blap.NSAL1100.NSAL1100SMsg;
import business.blap.NSAL1100.constant.NSAL1100Constant;
import business.db.SVC_CR_REBILTMsg;
import business.db.SVC_CR_REBILTMsgArray;
import business.parts.NSZC053001PMsg;

/**
 *<pre>
 * Approval List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Hitachi         A.Kohinata      Create          N/A
 * 2016/04/25   Hitachi         K.Yamada        Update          CSA QC#6946
 * 2017/09/14   Hitachi         K.Kojima        Update          QC#20397
 * 2017/09/26   CITS            M.Naito         Update          QC#21253
 * 2017/09/26   CITS            M.Naito         Update          QC#18529
 * 2017/10/19   Hitachi         K.Kojima        Update          QC#21260
 * 2018/06/12   Fujitsu         T.Ogura         Update          QC#22382
 *</pre>
 */
public class NSAL1100CommonLogic {

    // START 2018/06/12 T.Ogura [QC#22382,DEL]
//    /**
//     * Create Pull Down
//     * @param cMsg NSAL1100CMsg
//     */
//    public static void createPullDown(NSAL1100CMsg cMsg) {
//
//        ZYPCodeDataUtil.createPulldownList(SVC_CR_REBIL_RSN.class, cMsg.svcCrRebilRsnCd_L, cMsg.svcCrRebilRsnDescTxt_L);
//    }
    // END   2018/06/12 T.Ogura [QC#22382,DEL]

    /**
     * Search Approval Info
     * @param cMsg NSAL1100CMsg
     * @param sMsg NSAL1100SMsg
     */
    public static void searchApprovalInfo(NSAL1100CMsg cMsg, NSAL1100SMsg sMsg) {

        clearMsg(cMsg, sMsg);
        if (!hasValue(cMsg.svcCrRebilPk_P)) {
            return;
        }
        NSAL1100Query.getInstance().getHeaderInfo(cMsg);
        getWorkflowInfo(cMsg, sMsg);

        pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * Call Credit Rebill Update API
     * @param cMsg NSAL1100CMsg
     * @param modeCd String
     */
    public static void callCreditRebillUpdateAPI(NSAL1100CMsg cMsg, String modeCd) {

        NSZC053001PMsg apiPMsg = new NSZC053001PMsg();
        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, modeCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCrRebilPk, cMsg.svcCrRebilPk_P);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCrRebilRsnTxt, cMsg.svcCrRebilRsnTxt);

        if (!NSZC053001Constant.MODE_CI_CANCEL.equals(modeCd)) {
            // START 2018/06/12 T.Ogura [QC#22382,DEL]
//            ZYPEZDItemValueSetter.setValue(apiPMsg.svcCrRebilRsnCd, cMsg.svcCrRebilRsnCd);
            // END   2018/06/12 T.Ogura [QC#22382,DEL]
            ZYPEZDItemValueSetter.setValue(apiPMsg.svcCrRebilProcCd, cMsg.svcCrRebilProcCd_P);
            for (int i = 0; i < cMsg.P.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(apiPMsg.xxCrRebilDtlList.no(i).svcCrRebilDtlPk, cMsg.P.no(i).svcCrRebilDtlPk);
                ZYPEZDItemValueSetter.setValue(apiPMsg.xxCrRebilDtlList.no(i).invPrintFlg, cMsg.P.no(i).invPrintFlg);
            }
        }

        NSZC053001 api = new NSZC053001();
        api.execute(apiPMsg, ONBATCH_TYPE.ONLINE);

        if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
            cMsg.setMessageInfo(msgList.get(0).getXxMsgid(), msgList.get(0).getXxMsgPrmArray());
        // START 2017/10/19 K.Kojima [QC#21260,ADD]
        } else {
            cMsg.setMessageInfo(NSAL1100Constant.NZZM0002I);
            cMsg.xxModeCd_P.clear();
        // END 2017/10/19 K.Kojima [QC#21260,ADD]
        }
    }

    /**
     * get SVC_CR_REBIL
     * @param cMsg NSAL1100CMsg
     * @return SVC_CR_REBILTMsgArray
     */
    public static SVC_CR_REBILTMsgArray getSvcCrRebil(NSAL1100CMsg cMsg) {
        SVC_CR_REBILTMsg inMsg = new SVC_CR_REBILTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("custIncdtId01", cMsg.custIncdtId_H.getValue());
        return (SVC_CR_REBILTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSAL1100CMsg
     * @param sMsg NSAL1100SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSAL1100CMsg cMsg, NSAL1100SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSAL1100CMsg cMsg, NSAL1100SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
    }

    private static void clearMsg(NSAL1100CMsg cMsg, NSAL1100SMsg sMsg) {

        cMsg.custIncdtId_H.clear();
        cMsg.svcCrRebilStsDescTxt_H.clear();
        cMsg.svcCrRebilDescTxt_H.clear();
        ZYPTableUtil.clear(cMsg.A);
        // START 2018/06/12 T.Ogura [QC#22382,DEL]
//        cMsg.svcCrRebilRsnCd.clear();
        // END   2018/06/12 T.Ogura [QC#22382,DEL]
        cMsg.svcCrRebilRsnTxt.clear();
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
        ZYPTableUtil.clear(sMsg.A);
    }

    private static void getWorkflowInfo(NSAL1100CMsg cMsg, NSAL1100SMsg sMsg) {

        String wfId = getWorkflowId(CONST_KEY_SVC_CR_REBIL_APVL_WF_ID, cMsg.glblCmpyCd.getValue());

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        try {
            S21NwfContext context = factory.getContex();
            List<S21NwfProcess> procs = context.getProcessForBiz(wfId, cMsg.svcCrRebilPk_P.getValue().toString());

            int idx = 0;
            boolean idxOver = false;
            if (procs.size() > 0) {
                S21NwfProcess proc = procs.get(0);
                S21NwfUtilBizProcess p = (S21NwfUtilBizProcess) proc;
                List<S21NwfUtilBizWorkItem> tasks = p.getTasks();

                String separator = System.getProperty("line.separator");

                for (S21NwfUtilBizWorkItem wi : tasks) {
                    if (wi.isApprovable()) {
                        if (idx >= sMsg.A.length()) {
                            idxOver = true;
                            break;
                        }
                        // Seq
                        setValue(sMsg.A.no(idx).xxRowNum_A, BigDecimal.valueOf(idx + 1));
                        // User
                        List<S21NwfUserRole> users = wi.getToUsers();
                        // mod start 2016/04/25 CSA Defect#6946
                        StringBuilder asgToId = new StringBuilder();
                        for (S21NwfUserRole user : users) {
                            asgToId.append(user.getUserRole());
                            asgToId.append(separator);

                            String group = user.getGroup();
                            if (!hasValue(group)) {
                                setValue(sMsg.A.no(idx).xxWfAsgToNm_A, getPsnNm(user.getUserRole(), cMsg.glblCmpyCd.getValue()));
                            }
                        }
                        setValue(sMsg.A.no(idx).xxWfAsgToNm_ID, asgToId.toString());
                        // mod end 2016/04/25 CSA Defect#6946
                        // Approval Date
                        setValue(sMsg.A.no(idx).xxDtTxt_A, formatDate(wi.getEndTimestamp()));
                        // Comments
                        // START 2017/09/26 M.Naito [QC#18529, MOD]
                        if (ZYPCommonFunc.hasValue(wi.getComment())) {
                            setValue(sMsg.A.no(idx).xxWfCmntTxt_A, wi.getComment());
                        } else {
                            setValue(sMsg.A.no(idx).xxWfCmntTxt_A, wi.getActName());
                        }
                        // END 2017/09/26 M.Naito [QC#18529, MOD]
                        // Actual Approver
                        setValue(sMsg.A.no(idx).xxWfActOpNm_A, getPsnNm(wi.getActOpUser(), cMsg.glblCmpyCd.getValue()));
                        idx++;
                    }
                }
            }
            sMsg.A.setValidCount(idx);

            if (idxOver) {
                cMsg.setMessageInfo(NZZM0001W);
            } else if (idx == 0) {
                // No result
                // START 2017/09/26 M.Naito [QC#21253, DEL]
//                cMsg.setMessageInfo(ZZZM9001E);
                // END 2017/09/26 M.Naito [QC#21253, DEL]
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }

        } catch (S21NwfSystemException e) {
            e.printStackTrace();
            cMsg.setMessageInfo(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
        }
    }

    private static String getWorkflowId(String constKey, String glblCmpyCd) {

        String wfId = ZYPCodeDataUtil.getVarCharConstValue(constKey, glblCmpyCd);
        if (wfId == null) {
            throw new S21AbendException("[Error]Not Found 'VAR_CHAR_CONST' : varCharConstNm=" + constKey);
        }
        return wfId;
    }

    private static String formatDate(String timestamp) {

        if (!hasValue(timestamp) || timestamp.length() < DATE_LENGTH) {
            return "";
        }
        return ZYPDateUtil.formatEzd8ToDisp(timestamp.substring(0, DATE_LENGTH));
    }

    @SuppressWarnings("unchecked")
    private static String getPsnNm(String psnCd, String glblCmpyCd) {

        if (!hasValue(psnCd)) {
            return "";
        }

        S21SsmEZDResult ssmResult = NSAL1100Query.getInstance().getFullPsnNm(psnCd, glblCmpyCd);
        if (ssmResult.isCodeNotFound()) {
            // START 2017/09/14 K.Kojima [QC#20397,MOD]
            // return "";
            return psnCd;
            // END 2017/09/14 K.Kojima [QC#20397,MOD]
        }
        Map<String, String> result = (Map<String, String>) ssmResult.getResultObject();
        String psnNm = (String) result.get("FULL_PSN_NM");
        if (psnNm == null) {
            // START 2017/09/14 K.Kojima [QC#20397,MOD]
            // psnNm = "";
            psnNm = psnCd;
            // END 2017/09/14 K.Kojima [QC#20397,MOD]
        }
        return psnNm;
    }
}
