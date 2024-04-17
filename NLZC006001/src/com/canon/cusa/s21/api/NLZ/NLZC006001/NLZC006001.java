/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC006001;

import business.db.INVTY_ORDTMsg;
import business.parts.NLZC004001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC004001.NLZC004001;
import com.canon.cusa.s21.api.NLZ.NLZC004001.constant.NLZC004001Constant;
import com.canon.cusa.s21.api.NLZ.NLZC005001.NLZC005001TokenBizObject;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CreateApprovalHistory;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CreateApprovalHistoryBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_ACT_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.util.S21NwfDateUtil;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfApproveEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfCancelEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfProcessEndEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfReassignEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfRejectEvent;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfAuthException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;

/**
 *<pre>
 * Inventory Adjustment Approval from WF API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/13/2018   CITS            T.Tokutomi      Create          QC#18380
 * 04/26/2018   CITS            T.Tokutomi      Update          QC#25840
 * 04/28/2020   CITS            M.Furugoori     Update          QC#56461
 * 05/21/2020   CITS            M.Furugoori     Update          QC#56549
 *</pre>
 */
public class NLZC006001 extends S21ApiCommonBase //
        implements S21NwfApproveEvent<S21NwfContext, S21NwfToken>, S21NwfReassignEvent<S21NwfContext, S21NwfToken>, S21NwfRejectEvent<S21NwfContext, S21NwfToken>, S21NwfProcessEndEvent<S21NwfContext, S21NwfToken> , S21NwfCancelEvent<S21NwfContext, S21NwfToken>{

    /** Date Format */
    private static final String DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS";

    // START 2020/05/21 [QC#56549,ADD]
    /** PSN_CD_SIZE */
    private static final int PSN_CD_SIZE = 8;
    // END 2020/05/21 [QC#56549,ADD]

    /**
     * Constructor
     */
    public NLZC006001() {
        super();
    }

    /**
     * S21NwfApproveEvent approve
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfBizException error
     */
    @Override
    public void approve(String name, S21NwfContext context, S21NwfToken token) throws S21NwfAuthException, S21NwfBizException, S21NwfException {

        // Get TokenObject of WorkFlow
        NLZC005001TokenBizObject tokenBiz = (NLZC005001TokenBizObject) token.getTokenObj();

        // Create Approval History
        createApprovalHistory(tokenBiz, APVL_HIST_ACT_TP.APPROVED, context.getUserID());

    }

    /**
     * S21NwfReassignEvent reassign
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @param fromUser String
     * @param toUser String
     * @param comment String
     * @throws S21NwfException error
     */
    @Override
    public void reassign(String name, S21NwfContext context, S21NwfToken token, String fromUser, String toUser, String comment) throws S21NwfAuthException, S21NwfBizException, S21NwfException {

        // Get TokenObject of WorkFlow
        NLZC005001TokenBizObject tokenBiz = (NLZC005001TokenBizObject) token.getTokenObj();

        // Create Approval History
        createApprovalHistory(tokenBiz, APVL_HIST_ACT_TP.FORWARD, context.getUserID());

    }

    /**
     * S21NwfRejectEvent reject
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfBizException error
     */
    @Override
    public void reject(String name, S21NwfContext context, S21NwfToken token) throws S21NwfAuthException, S21NwfBizException, S21NwfException {

        // Get TokenObject of WorkFlow
        NLZC005001TokenBizObject tokenBiz = (NLZC005001TokenBizObject) token.getTokenObj();

        // Create Approval History
        createApprovalHistory(tokenBiz, APVL_HIST_ACT_TP.REJECTED, context.getUserID());

    }

    /**
     * S21NwfCancelEvent reject
     *  QC#25840 Add method.
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfBizException error
     */
    @Override
    public void cancel(String name, S21NwfContext context, S21NwfToken token) throws S21NwfAuthException, S21NwfBizException, S21NwfException {
        // Get TokenObject of WorkFlow
        NLZC005001TokenBizObject tokenBiz = (NLZC005001TokenBizObject) token.getTokenObj();

        // Create Approval History
        createApprovalHistory(tokenBiz, APVL_HIST_ACT_TP.REJECTED, context.getUserID());
    }

    /**
     * S21NwfProcessEndEvent end
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfBizException error
     */
    @Override
    public void end(String name, S21NwfContext context, S21NwfToken token) throws S21NwfAuthException, S21NwfBizException, S21NwfException {
        // Get TokenObject of WorkFlow
        NLZC005001TokenBizObject tokenBiz = (NLZC005001TokenBizObject) token.getTokenObj();

        if ("APPROVE".equals(name)) {

            executeInventoryAjustment(tokenBiz, NLZC004001Constant.PROC_TP_CLO);

        } else if ("REJECT".equals(name)) {
            executeInventoryAjustment(tokenBiz, NLZC004001Constant.PROC_TP_CANC);

        } else if ("CANCEL".equals(name)) {
            // QC#25840 Add.
            executeInventoryAjustment(tokenBiz, NLZC004001Constant.PROC_TP_CANC);

        } else if (S21NwfConst.COND_END_NO_USER.equals(name)) {
            /**
             * The approver is not found. Please set up it at
             * workflow.
             */
            throw new S21NwfBizException("NLCM0168E", null);
        } else {
            /** Undefined value is set in Process mode. */
            throw new S21NwfBizException("NLCM0169E", null);
        }

    }

    private void createApprovalHistory(NLZC005001TokenBizObject tokenBiz, String apvlHistActTpCd, String actOpUser) throws S21NwfBizException {
        String glblCmpyCd = tokenBiz.getGlblCmpyCd();
        String invtyOrdNum = tokenBiz.getInvtyOrdNum();
        String endTimestamp = S21NwfDateUtil.getSystemTimestamp();
        String comments = tokenBiz.getComment();
        String apvlHistSrcTpCd = tokenBiz.getApvlHistSrcTpCd();

        NPXC001001CreateApprovalHistoryBean inParam = new NPXC001001CreateApprovalHistoryBean();
        inParam.setGlblCmpyCd(glblCmpyCd);
        inParam.setApvlHistSrcTpCd(apvlHistSrcTpCd);
        inParam.setTrxRefNum(invtyOrdNum);
        if (ZYPCommonFunc.hasValue(endTimestamp)) {
            inParam.setApvlHistInfoTs(endTimestamp);
        } else {
            inParam.setApvlHistInfoTs(ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
        }
        inParam.setApvlHistActTpCd(apvlHistActTpCd);
        // START 2020/05/21 [QC#56549,ADD]
        if (actOpUser.length() > PSN_CD_SIZE) {
            actOpUser = actOpUser.substring(0, PSN_CD_SIZE);
        }
        // END 2020/05/21 [QC#56549,ADD]
        inParam.setApvlByPsnCd(actOpUser);
        inParam.setApvlHistTxt(comments);

        String rtrnCd = NPXC001001CreateApprovalHistory.createApprovalHistory(inParam);
        if (!NPXC001001CreateApprovalHistory.NORMAL.equals(rtrnCd)) {
            /** An error occurred in NPXC001001CreateApprovalHistory. */
            throw new S21NwfBizException("NPZM0212E", null);
        }
    }

    private INVTY_ORDTMsg getInvtyOrd(String glblCmpyCd, String invtyOrdNum) {

        INVTY_ORDTMsg msg = new INVTY_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(msg.invtyOrdNum, invtyOrdNum);

        return (INVTY_ORDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(msg);

    }

    private void executeInventoryAjustment(NLZC005001TokenBizObject tokenBiz, String process) throws S21NwfBizException {

        String glblCmpyCd = tokenBiz.getGlblCmpyCd();
        String invtyOrnNum = tokenBiz.getInvtyOrdNum();

        INVTY_ORDTMsg invtyOrd = getInvtyOrd(glblCmpyCd, invtyOrnNum);

        if (invtyOrd == null) {
            // Inventory Order does not exist.
            throw new S21NwfBizException("NLZM0052E", null);
        }
        NLZC004001PMsg pMsg = new NLZC004001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.xxProcTpCd, process);
        ZYPEZDItemValueSetter.setValue(pMsg.xxDtTpCd, NLZC004001Constant.DT_TP_HDR);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(pMsg.invtyOrdNum, invtyOrd.invtyOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.invtyOrdTpCd, invtyOrd.invtyOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, invtyOrd.invtyLocCd);
        ZYPEZDItemValueSetter.setValue(pMsg.locStsCd, invtyOrd.locStsCd);
        ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, invtyOrd.trxSrcTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, invtyOrd.sysSrcCd);
        ZYPEZDItemValueSetter.setValue(pMsg.adjTrxTpCd, invtyOrd.adjTrxTpCd);

        NLZC004001 api = new NLZC004001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            // START 04/23/2020 [QC#56461,MOD]
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            // throw API first error message.
//            throw new S21NwfBizException(pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), null);
            throw new S21NwfBizException(msgId, msgPrms);
            // END   04/23/2020 [QC#56461,MOD]
        }

    }
}
