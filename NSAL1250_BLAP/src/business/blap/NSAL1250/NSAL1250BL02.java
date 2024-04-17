/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1250;

import static business.blap.NSAL1250.constant.NSAL1250Constant.DETAIL1_COLUMN_NAME_AA;
import static business.blap.NSAL1250.constant.NSAL1250Constant.DETAIL1_COLUMN_NAME_AV;
import static business.blap.NSAL1250.constant.NSAL1250Constant.DETAIL1_COLUMN_NAME_COUNTER;
import static business.blap.NSAL1250.constant.NSAL1250Constant.DETAIL1_COLUMN_NAME_END_DATE;
import static business.blap.NSAL1250.constant.NSAL1250Constant.DETAIL1_COLUMN_NAME_START_DATE;
import static business.blap.NSAL1250.constant.NSAL1250Constant.DETAIL2_COLUMN_NAME_ACTION;
import static business.blap.NSAL1250.constant.NSAL1250Constant.DETAIL2_COLUMN_NAME_ACTION_DATE;
import static business.blap.NSAL1250.constant.NSAL1250Constant.DETAIL2_COLUMN_NAME_DETAIL;
import static business.blap.NSAL1250.constant.NSAL1250Constant.DETAIL2_COLUMN_NAME_FROM;
import static business.blap.NSAL1250.constant.NSAL1250Constant.DETAIL2_COLUMN_NAME_TO;
import static business.blap.NSAL1250.constant.NSAL1250Constant.DIVIDE_ROUND_UP_POINT_DIGITS;
import static business.blap.NSAL1250.constant.NSAL1250Constant.NSAM0439E;
import static business.blap.NSAL1250.constant.NSAL1250Constant.NZZM0001W;
import static business.blap.NSAL1250.constant.NSAL1250Constant.PARAM1_STR_PART1;
import static business.blap.NSAL1250.constant.NSAL1250Constant.PARAM1_STR_PART2;
import static business.blap.NSAL1250.constant.NSAL1250Constant.PARAM2_STR_PART1;
import static business.blap.NSAL1250.constant.NSAL1250Constant.PARAM2_STR_PART2;
import static business.blap.NSAL1250.constant.NSAL1250Constant.PARAM2_STR_PART3;
import static business.blap.NSAL1250.constant.NSAL1250Constant.PARAMCOMP_STR_PART1;
import static business.blap.NSAL1250.constant.NSAL1250Constant.PARAMCOMP_STR_PART2;
import static business.blap.NSAL1250.constant.NSAL1250Constant.PROCESS_DEF_NSWP0002;
import static business.blap.NSAL1250.constant.NSAL1250Constant.PROCESS_DEF_NSWP0005;
import static business.blap.NSAL1250.constant.NSAL1250Constant.TABLE_DETAIL1;
import static business.blap.NSAL1250.constant.NSAL1250Constant.TABLE_DETAIL2;
import static business.blap.NSAL1250.constant.NSAL1250Constant.TS_LENGTH;
import static business.blap.NSAL1250.constant.NSAL1250Constant.ZZZM9001E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL1250.common.NSAL1250CommonLogic;
import business.db.SVC_CONTR_BLLGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPFormatUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.model.S21NwfWorkItem;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.user.S21NwfUserRole;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizHistWorkItem;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizProcess;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizWorkItem;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Preview Billing Workflow Details
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/07   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/09/14   Hitachi         N.Arai          Update          QC#10729
 * 2017/09/06   Hitachi         K.Kishimoto     Update          QC#20963
 * 2018/03/23   Hitachi         U.Kim           Update          QC#18884(Sol337)
 * 2019/09/02   Hitachi         K.Kitachi       Update          QC#52695
 * 2019/11/18   Hitachi         H.Umeda         Update          QC#52308
 * 2019/11/28   Hitachi         K.Kishimoto     Update          QC#53567
 *</pre>
 */
public class NSAL1250BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            NSAL1250CMsg bizMsg = (NSAL1250CMsg) cMsg;
            NSAL1250SMsg glblMsg = (NSAL1250SMsg) sMsg;
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL1250_INIT".equals(screenAplID)) {
                doProcess_NSAL1250_INIT(bizMsg, glblMsg);
            } else if ("NSAL1250Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL1250Scrn00_CMN_Reset(bizMsg, glblMsg);
            } else if ("NSAL1250Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL1250Scrn00_PageNext((NSAL1250CMsg) cMsg, (NSAL1250SMsg) sMsg);
            } else if ("NSAL1250Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL1250Scrn00_PagePrev((NSAL1250CMsg) cMsg, (NSAL1250SMsg) sMsg);
            } else if ("NSAL1250Scrn00_ChangeRead".equals(screenAplID)) {
                doProcess_NSAL1250Scrn00_CMN_ChangeRead((NSAL1250CMsg) cMsg, (NSAL1250SMsg) sMsg);
            } else if ("NSAL1250Scrn00_CMN_Approve".equals(screenAplID)) {
                doProcess_NSAL1250Scrn00_CMN_Approve(bizMsg, glblMsg);
            } else if ("NSAL1250Scrn00_Approve".equals(screenAplID)) {
                doProcess_NSAL1250Scrn00_CMN_Approve(bizMsg, glblMsg);
            // START 09/14/2016 N.Arai [QC#10729, MOD]
            } else if ("NSAL1250_NSAL1300".equals(screenAplID)) {
                doProcess_NSAL1250_INIT(bizMsg, glblMsg);
            // START 09/14/2016 N.Arai [QC#10729, MOD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Init)
     * @param cMsg NSAL1250CMsg
     * @param sMsg NSAL1250SMsg
     */
    private void doProcess_NSAL1250_INIT(NSAL1250CMsg cMsg, NSAL1250SMsg sMsg) {
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        cMsg.setCommitSMsg(true);
        // init
        // START 2019/11/18 H.Umeda [QC#52308, MOD]
        //setHeader(cMsg);
        //setDetail1(cMsg, sMsg);
        //setDetail2(cMsg, sMsg);
        if(setHeader(cMsg)){
            setDetail1(cMsg, sMsg);
            setDetail2(cMsg, sMsg);
        }
        // END   2019/11/18 H.Umeda [QC#52308, MOD]

    }

// START 2019/11/18 H.Umeda [QC#52308, MOD]
//  private void setHeader(NSAL1250CMsg cMsg) {
    private boolean setHeader(NSAL1250CMsg cMsg) {
// END   2019/11/18 H.Umeda [QC#52308, MOD]
        S21SsmEZDResult ssmResult = NSAL1250Query.getInstance().getTransactionInfo(cMsg.svcContrBllgPk.getValue());
        // START 2019/11/18 H.Umeda [QC#52308, ADD]
        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(ZZZM9001E);
            return false;
        }
        // END   2019/11/18 H.Umeda [QC#52308, ADD]
        Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
        if (map != null) {
            setValue(cMsg.svcMachMstrPk, (BigDecimal) map.get("SVC_MACH_MSTR_PK"));
            // START 2019/09/02 K.Kitachi [QC#52695, ADD]
            setValue(cMsg.dsContrDtlPk, (BigDecimal) map.get("DS_CONTR_DTL_PK"));
            // END 2019/09/02 K.Kitachi [QC#52695, ADD]
            setValue(cMsg.xxWfCmntTxt_1, getParam1(map));
            setValue(cMsg.xxWfCmntTxt_2, getParam2(map));
        }
        // START 2018/03/23 U.Kim [QC#18884(Sol337), ADD]
        setValue(cMsg.wfBizAppId, PROCESS_DEF_NSWP0002);
        BigDecimal mtrCopyQty = (BigDecimal) map.get("MTR_COPY_QTY");
        // START 2019/11/18 H.Umeda [QC#52308, ADD]
        if( hasValue(mtrCopyQty) == false ){
            cMsg.setMessageInfo(ZZZM9001E);
            return false;
        }
        // END   2019/11/18 H.Umeda [QC#52308, ADD]
        if (mtrCopyQty.compareTo(BigDecimal.ZERO) < 0) {
            setValue(cMsg.wfBizAppId, PROCESS_DEF_NSWP0005);
            setValue(cMsg.xxWfCmntTxt_1, getParamForComp1(map));
            setValue(cMsg.xxWfCmntTxt_2, getParamForComp2(map));
        }
        // END 2018/03/23 U.Kim [QC#18884(Sol337), ADD]
        setHeaderWorkFlow(cMsg);
        // START 09/14/2016 N.Arai [QC#10729, MOD]
        if (!S21NwfWorkItem.STATUS.RUN.getCode().equals(cMsg.wfProcStsCd.getValue())) {
            cMsg.xxWfCmntTxt_2.clear();
            cMsg.setCommitSMsg(true);
            // No result
            cMsg.setMessageInfo(ZZZM9001E);
            // START 2019/11/18 H.Umeda [QC#52308, MOD]
            //return;
            return true;
            // START 2019/11/18 H.Umeda [QC#52308, MOD]
        }
        // START 09/14/2016 N.Arai [QC#10729, MOD]
        // START 2019/11/18 H.Umeda [QC#52308, MOD]
        //return;
        return true;
        // START 2019/11/18 H.Umeda [QC#52308, MOD]
    }

    private String getParam1(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        String dsContrNum = (String) map.get("DS_CONTR_NUM");
        String serNum = (String) map.get("SER_NUM");
        return PARAM1_STR_PART1 + dsContrNum + PARAM1_STR_PART2 + serNum;
    }

    private String getParam2(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        String serNum = (String) map.get("SER_NUM");
        // Mod Start 2017/09/06 <QC#20963>
        BigDecimal limitAmt = (BigDecimal) map.get("BLLG_LIMIT_AMT");
        String limitAmtStr = ZYPFormatUtil.formatNumToSysDisp(limitAmt);
        
        return PARAM2_STR_PART1 + serNum + PARAM2_STR_PART2 + limitAmtStr + PARAM2_STR_PART3;
        // Mod End   2017/09/06 <QC#20963>
    }

    // START 2018/03/23 U.Kim [QC#18884(Sol337, ADD]
    private String getParamForComp1(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        String dsContrNum = (String) map.get("DS_CONTR_NUM");
        String serNum = (String) map.get("SER_NUM");
        return PARAMCOMP_STR_PART1 + dsContrNum + PARAM1_STR_PART2 + serNum;
    }

    private String getParamForComp2(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        String serNum = (String) map.get("SER_NUM");
        return PARAM2_STR_PART1 + serNum + PARAMCOMP_STR_PART2;
    }
    // END 2018/03/23 U.Kim [QC#18884(Sol337, ADD]

    private void setHeaderWorkFlow(NSAL1250CMsg cMsg) {
    // MTR_CHRG_DEAL_AMT
        S21NwfUtilContextFactory factory = new S21NwfUtilContextFactory();

        String documentId = NSAL1250CommonLogic.getDocumentId(cMsg.svcContrBllgPk.getValue());
        // START 2019/09/02 K.Kitachi [QC#52695, ADD]
        boolean isSetWfInfo = false;
        // END 2019/09/02 K.Kitachi [QC#52695, ADD]
        try {
            S21NwfContext context = factory.getContex();
            // START 2018/03/23 U.Kim [QC#18884(Sol337), MOD]
            // List<S21NwfProcess> procs = context.getProcessForBiz(PROCESS_DEF_NSWP0002, documentId);
            List<S21NwfProcess> procs = context.getProcessForBiz(cMsg.wfBizAppId.getValue(), documentId);
            // END 2018/03/23 U.Kim [QC#18884(Sol337), MOD]
            for (S21NwfProcess proc : procs) {
                S21NwfUtilBizProcess p = (S21NwfUtilBizProcess) proc;
                List<S21NwfUtilBizWorkItem> tasks = p.getTasks();
                for (S21NwfUtilBizWorkItem wi : tasks) {
                    if (S21NwfWorkItem.STATUS.RUN.getCode().equals(wi.getStatusCode())) {
                        // To Users
                        List<S21NwfUserRole> users = wi.getToUsers();
                        for (S21NwfUserRole user : users) {
                            // To Users
                            setValue(cMsg.xxWfActOpNm, user.getUserRole());
                            setValue(cMsg.xxCtacPsnNmTxt, NSAL1250CommonLogic.getPsnNm(getGlobalCompanyCode(), user.getUserRole()));
                        }
                        // Sent
                        String sent = ZYPDateUtil.formatEzd17ToDisp(wi.getStartTimestamp());
                        if (hasValue(sent)) {
                            sent = sent.substring(0, TS_LENGTH);
                        }
                        setValue(cMsg.xxTsDsp19Txt, sent);
                        // START 09/14/2016 N.Arai [QC#10729, MOD]
                        setValue(cMsg.wfProcStsCd, wi.getStatusCode());
                        // END 09/14/2016 N.Arai [QC#10729, MOD]
                        // START 2019/09/02 K.Kitachi [QC#52695, ADD]
                        isSetWfInfo = true;
                        // END 2019/09/02 K.Kitachi [QC#52695, ADD]
                    }
                }
            }
            // START 2019/09/02 K.Kitachi [QC#52695, ADD]
            if (isSetWfInfo) {
                return;
            }
            BigDecimal dsContrDtlPk = cMsg.dsContrDtlPk.getValue();
            documentId = S21StringUtil.concatStrings(documentId, dsContrDtlPk.toPlainString());
            procs = context.getProcessForBiz(cMsg.wfBizAppId.getValue(), documentId);
            for (S21NwfProcess proc : procs) {
                S21NwfUtilBizProcess p = (S21NwfUtilBizProcess) proc;
                List<S21NwfUtilBizWorkItem> tasks = p.getTasks();
                for (S21NwfUtilBizWorkItem wi : tasks) {
                    if (S21NwfWorkItem.STATUS.RUN.getCode().equals(wi.getStatusCode())) {
                        // To Users
                        List<S21NwfUserRole> users = wi.getToUsers();
                        for (S21NwfUserRole user : users) {
                            // To Users
                            setValue(cMsg.xxWfActOpNm, user.getUserRole());
                            setValue(cMsg.xxCtacPsnNmTxt, NSAL1250CommonLogic.getPsnNm(getGlobalCompanyCode(), user.getUserRole()));
                        }
                        // Sent
                        String sent = ZYPDateUtil.formatEzd17ToDisp(wi.getStartTimestamp());
                        if (hasValue(sent)) {
                            sent = sent.substring(0, TS_LENGTH);
                        }
                        setValue(cMsg.xxTsDsp19Txt, sent);
                        setValue(cMsg.wfProcStsCd, wi.getStatusCode());
                    }
                }
            }
            // END 2019/09/02 K.Kitachi [QC#52695, ADD]
        } catch (S21NwfSystemException e1) {
            // START 2018/03/23 U.Kim [QC#18884(Sol337), MOD]
            // cMsg.setMessageInfo(NSAM0439E, new String[] {PROCESS_DEF_NSWP0002, documentId });
            cMsg.setMessageInfo(NSAM0439E, new String[] {cMsg.wfBizAppId.getValue(), documentId });
            // END 2018/03/23 U.Kim [QC#18884(Sol337), MOD]
            return;
        }
    }

    private void setDetail1(NSAL1250CMsg cMsg, NSAL1250SMsg sMsg) {
        // mod start 2016/09/30 CSA Defect#10729
        SVC_CONTR_BLLGTMsg svcContrBllg = NSAL1250Query.getInstance().getSvcContrBllg(cMsg.svcContrBllgPk.getValue());
        S21SsmEZDResult ssmResult = NSAL1250Query.getInstance().getDetail1(svcContrBllg, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            // Result > 200
            int queryResCnt = ssmResult.getQueryResultCount();
            int maxCnt = queryResCnt;
            if (queryResCnt > sMsg.A.length()) {
                maxCnt = sMsg.A.length();
                cMsg.setMessageInfo(NZZM0001W);
            }
            List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
            int i = 0;
            for (; i < maxCnt; i++) {
                Map<String, Object> detail = list.get(i);
                setValue(sMsg.A.no(i).mtrLbDescTxt_A, (String) detail.get(DETAIL1_COLUMN_NAME_COUNTER));
                setValue(sMsg.A.no(i).svcContrBllgFromDt_A, (String) detail.get(DETAIL1_COLUMN_NAME_START_DATE));
                setValue(sMsg.A.no(i).svcContrBllgThruDt_A, (String) detail.get(DETAIL1_COLUMN_NAME_END_DATE));
                setValue(sMsg.A.no(i).mtrChrgDealAmt_AV, (BigDecimal) detail.get(DETAIL1_COLUMN_NAME_AV));
                setValue(sMsg.A.no(i).mtrChrgDealAmt_AA, (BigDecimal) detail.get(DETAIL1_COLUMN_NAME_AA));
                setValue(sMsg.A.no(i).mtrChrgDealAmt_AP, getChargeDealAmtRate((BigDecimal) detail.get(DETAIL1_COLUMN_NAME_AV), (BigDecimal) detail.get(DETAIL1_COLUMN_NAME_AA)));
            }
            sMsg.A.setValidCount(i);
            // set Paging Data
            NSAL1250CommonLogic.pagenationA(cMsg, sMsg, 0);
        }
    }

    private BigDecimal getChargeDealAmtRate(BigDecimal mtrChrgDealAmtAv, BigDecimal mtrChrgDealAmtAa) {
        if (mtrChrgDealAmtAv == null || (mtrChrgDealAmtAv.compareTo(BigDecimal.ZERO) == 0) || mtrChrgDealAmtAa == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal rtn = mtrChrgDealAmtAa.divide(mtrChrgDealAmtAv, DIVIDE_ROUND_UP_POINT_DIGITS, BigDecimal.ROUND_DOWN);
        rtn = rtn.multiply(new BigDecimal("100"));
        return rtn.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private void setDetail2(NSAL1250CMsg cMsg, NSAL1250SMsg sMsg) {
        S21NwfUtilContextFactory factory = new S21NwfUtilContextFactory();
        String documentId = NSAL1250CommonLogic.getDocumentId(cMsg.svcContrBllgPk.getValue());
        try {
            S21NwfContext context = factory.getContex();
            // START 2018/03/23 U.Kim [QC#18884(Sol337), MOD]
            // List<S21NwfProcess> procs = context.getProcessForBiz(PROCESS_DEF_NSWP0002, documentId);
            List<S21NwfProcess> procs = context.getProcessForBiz(cMsg.wfBizAppId.getValue(), documentId);
            // END 2018/03/23 U.Kim [QC#18884(Sol337), MOD]
            List<Map<String, String>> dispList = new ArrayList<Map<String, String>>();
            for (S21NwfProcess proc : procs) {
                S21NwfUtilBizProcess p = (S21NwfUtilBizProcess) proc;
                List<S21NwfUtilBizHistWorkItem> history = p.getHistory();
                for (S21NwfUtilBizHistWorkItem wi : history) {
                    Map<String, String> his = new HashMap<String, String>();
                    // Action Date
                    his.put(DETAIL2_COLUMN_NAME_ACTION_DATE, wi.getEndTimestamp());
                    // Action
                    his.put(DETAIL2_COLUMN_NAME_ACTION, wi.getActName());
                    // From
                    his.put(DETAIL2_COLUMN_NAME_FROM, wi.getFromUser());
                    // To
                    his.put(DETAIL2_COLUMN_NAME_TO, wi.getActOpUser());
                    // Detail
                    his.put(DETAIL2_COLUMN_NAME_DETAIL, wi.getComment());
                    dispList.add(his);
                }
            }
            // START 2019/09/02 K.Kitachi [QC#52695, ADD]
            BigDecimal dsContrDtlPk = cMsg.dsContrDtlPk.getValue();
            documentId = S21StringUtil.concatStrings(documentId, dsContrDtlPk.toPlainString());
            procs = context.getProcessForBiz(cMsg.wfBizAppId.getValue(), documentId);
            for (S21NwfProcess proc : procs) {
                S21NwfUtilBizProcess p = (S21NwfUtilBizProcess) proc;
                List<S21NwfUtilBizHistWorkItem> history = p.getHistory();
                for (S21NwfUtilBizHistWorkItem wi : history) {
                    Map<String, String> his = new HashMap<String, String>();
                    // Action Date
                    his.put(DETAIL2_COLUMN_NAME_ACTION_DATE, wi.getEndTimestamp());
                    // Action
                    his.put(DETAIL2_COLUMN_NAME_ACTION, wi.getActName());
                    // From
                    his.put(DETAIL2_COLUMN_NAME_FROM, wi.getFromUser());
                    // To
                    his.put(DETAIL2_COLUMN_NAME_TO, wi.getActOpUser());
                    // Detail
                    his.put(DETAIL2_COLUMN_NAME_DETAIL, wi.getComment());
                    dispList.add(his);
                }
            }
            // END 2019/09/02 K.Kitachi [QC#52695, ADD]
            // set Detail2
            int queryResCnt = dispList.size();
            int maxCnt = queryResCnt;
            if (queryResCnt > sMsg.B.length()) {
                maxCnt = sMsg.B.length();
                cMsg.setMessageInfo(NZZM0001W);
            }
            int i = 0;
            for (; i < maxCnt; i++) {
                Map<String, String> his = dispList.get(i);
                String actionDate = ZYPDateUtil.formatEzd17ToDisp(his.get(DETAIL2_COLUMN_NAME_ACTION_DATE));
                if (hasValue(actionDate)) {
                    actionDate = actionDate.substring(0, TS_LENGTH);
                }
                setValue(sMsg.B.no(i).xxTsDsp19Txt_B, actionDate);
                setValue(sMsg.B.no(i).xxWfActOpNm_B, his.get(DETAIL2_COLUMN_NAME_ACTION));
                setValue(sMsg.B.no(i).xxCtacPsnNmTxt_BF, his.get(DETAIL2_COLUMN_NAME_FROM));
                setValue(sMsg.B.no(i).xxCtacPsnNmTxt_BT, his.get(DETAIL2_COLUMN_NAME_TO));
                setValue(sMsg.B.no(i).xxWfCmntTxt_B, his.get(DETAIL2_COLUMN_NAME_DETAIL));
            }
            sMsg.B.setValidCount(i);
            // set Paging Data
            NSAL1250CommonLogic.pagenationB(cMsg, sMsg, 0);

        } catch (S21NwfSystemException e1) {
            // START 2018/03/23 U.Kim [QC#18884(Sol337), MOD]
            // cMsg.setMessageInfo(NSAM0439E, new String[] {PROCESS_DEF_NSWP0002, documentId });
            cMsg.setMessageInfo(NSAM0439E, new String[] {cMsg.wfBizAppId.getValue(), documentId });
            // END 2018/03/23 U.Kim [QC#18884(Sol337), MOD]
            return;
        }
    }

    /**
     * do process (Page Next)
     * @param cMsg NSAL1250CMsg
     * @param sMsg NSAL1250SMsg
     */
    private void doProcess_NSAL1250Scrn00_PageNext(NSAL1250CMsg cMsg, NSAL1250SMsg sMsg) {
        if (cMsg.xxPageTblNm.getValue().equals(TABLE_DETAIL1)) {
            int rowIndex = NSAL1250CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum_A.getValueInt() + 1);
            NSAL1250CommonLogic.pagenationA(cMsg, sMsg, rowIndex);
        } else if (cMsg.xxPageTblNm.getValue().equals(TABLE_DETAIL2)) {
            int rowIndex = NSAL1250CommonLogic.convertPageNumToFirstRowIndex(cMsg.B.length(), cMsg.xxPageShowCurNum_B.getValueInt() + 1);
            NSAL1250CommonLogic.pagenationB(cMsg, sMsg, rowIndex);
        }

    }

    /**
     * do process (Page Prev)
     * @param cMsg NSAL1250CMsg
     * @param sMsg NSAL1250SMsg
     */
    private void doProcess_NSAL1250Scrn00_PagePrev(NSAL1250CMsg cMsg, NSAL1250SMsg sMsg) {
        if (cMsg.xxPageTblNm.getValue().equals(TABLE_DETAIL1)) {
            int rowIndex = NSAL1250CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum_A.getValueInt() - 1);
            NSAL1250CommonLogic.pagenationA(cMsg, sMsg, rowIndex);
        } else if (cMsg.xxPageTblNm.getValue().equals(TABLE_DETAIL2)) {
            int rowIndex = NSAL1250CommonLogic.convertPageNumToFirstRowIndex(cMsg.B.length(), cMsg.xxPageShowCurNum_B.getValueInt() - 1);
            NSAL1250CommonLogic.pagenationB(cMsg, sMsg, rowIndex);
        }
    }

    /**
     * do process (Reset)
     * @param cMsg NSAL1250CMsg
     * @param sMsg NSAL1250SMsg
     */
    private void doProcess_NSAL1250Scrn00_CMN_Reset(NSAL1250CMsg cMsg, NSAL1250SMsg sMsg) {
        BigDecimal svcContrBllgPk = cMsg.svcContrBllgPk.getValue();
        cMsg.clear();
        setValue(cMsg.svcContrBllgPk, svcContrBllgPk);
        doProcess_NSAL1250_INIT(cMsg, sMsg);
    }

    /**
     * do process (Approve)
     * @param cMsg NSAL1250CMsg
     * @param sMsg NSAL1250SMsg
     */
    private void doProcess_NSAL1250Scrn00_CMN_Approve(NSAL1250CMsg cMsg, NSAL1250SMsg sMsg) {
        BigDecimal svcContrBllgPk = cMsg.svcContrBllgPk.getValue();
        // START 2019/11/28 [QC#53567, ADD]
        BigDecimal otherSvcContrBllgPk = NSAL1250Query.getInstance().getSvcContrBllgPk(svcContrBllgPk);
        if (otherSvcContrBllgPk != null) {
            svcContrBllgPk = otherSvcContrBllgPk;
        }
        // END   2019/11/28 [QC#53567, ADD]
        cMsg.clear();
        setValue(cMsg.svcContrBllgPk, svcContrBllgPk);
        doProcess_NSAL1250_INIT(cMsg, sMsg);
    }

    /**
     * do process (ChangeRead)
     * @param cMsg NSAL1250CMsg
     * @param sMsg NSAL1250SMsg
     */
    private void doProcess_NSAL1250Scrn00_CMN_ChangeRead(NSAL1250CMsg cMsg, NSAL1250SMsg sMsg) {
        // START 09/14/2016 N.Arai [QC#10729, DEL]
        // S21SsmEZDResult ssmResult = NSAL1250Query.getInstance().getTransactionInfo(cMsg.svcContrBllgPk.getValue());
        // Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
        // if (map != null) {
        //     setValue(cMsg.svcMachMstrPk, (BigDecimal) map.get("SVC_MACH_MSTR_PK"));
        // }
    }

}
