/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC103001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.*;

import java.util.List;

import business.db.SVC_CR_REBILTMsg;
import business.db.SVC_CR_REBILTMsgArray;
import business.parts.NSZC053001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC053001.NSZC053001;
import com.canon.cusa.s21.api.NSZ.NSZC053001.NSZC053001TokenObject;
import com.canon.cusa.s21.api.NSZ.NSZC053001.NSZC053001TokenObjectLine;
import com.canon.cusa.s21.api.NSZ.NSZC053001.constant.NSZC053001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfApproveEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfProcessEndEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfRejectEvent;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;

/**
 *<pre>
 * Credit Rebill Workflow API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Hitachi         T.Aoyagi        Update          QC#5435
 * 2016/04/26   Hitachi         K.Yamada        Update          QC#5566
 * 2016/12/28   Hitachi         K.Kojima        Update          QC#15640
 * 2019/12/16   Hitachi         A.Kohinata      Update          QC#55068
 *</pre>
 */
public class NSZC103001 implements S21NwfApproveEvent<S21NwfContext, S21NwfToken>,
                                        S21NwfRejectEvent<S21NwfContext, S21NwfToken>,
                                        S21NwfProcessEndEvent<S21NwfContext, S21NwfToken> {

    /** Signal Cancel */
    public static final String SG_CANCEL = "CANCEL";

    /**
     * An error occurred in Workflow Process.
     */
    public static final String NSZM0926E = "NSZM0926E";

    // add start 2019/12/16 QC#55068
    /**
     * The target data does not exist in Contract.
     */
    public static final String NSZM0617E = "NSZM0617E";

    /**
     * You can not process this workflow since a contract for this machine does not exist or has already terminated. 
     * Please use AR credit/rebill funtion to fix it.
     */
    public static final String NSZM1369E = "NSZM1369E";
    // add end 2019/12/16 QC#55068

    /**
     * Approve
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfException S21NwfException
     */
    public void approve(String name, S21NwfContext context, S21NwfToken token) throws S21NwfException {
        return;
    }

    /**
     * Reject
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfException S21NwfException
     */
    public void reject(String name, S21NwfContext context, S21NwfToken token) throws S21NwfException {
        return;
    }

    /**
     * End
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfException S21NwfException
     */
    public void end(String name, S21NwfContext context, S21NwfToken token) throws S21NwfException {
        NSZC053001TokenObject tokenObj = (NSZC053001TokenObject) token.getTokenObj();
        List<NSZC053001TokenObjectLine> lines = tokenObj.getLineData();
        for (NSZC053001TokenObjectLine line : lines) {
            if (SIGNAL_APPROVE.equals(name)) {
                // Approve Mode
                callApi(line, NSZC053001Constant.MODE_WORK_FLOW_APPROVE);
            } else if (SIGNAL_REJECT.equals(name)) {
                // mod start 2016/04/26 CSA Defect#5566
                // Cancel Mode
                callApi(line, NSZC053001Constant.MODE_CI_CANCEL);
                // mod end 2016/04/26 CSA Defect#5566
            } else if (SG_CANCEL.equals(name)){
                // Cancel
                continue;
                // START 2016/12/28 K.Kojima [QC#15640,ADD]
            } else if (COND_END_NO_USER.equals(name)) {
                throw new S21NwfBizException("NSZM1077E", null);
                // END 2016/12/28 K.Kojima [QC#15640,ADD]
            } else {
                // System Error
                throw new S21AbendException(NSZM0926E);
            }
        }
    }

    private void callApi(NSZC053001TokenObjectLine line, String xxModeCd) throws S21NwfException {

        String slsDt = ZYPDateUtil.getSalesDate(line.getGlblCmpyCd());
        SVC_CR_REBILTMsg svcCrRebilTMsg = getSvcCrRebilTMsgByIncdtId(line);
        if (svcCrRebilTMsg == null) {
            throw new S21AbendException(NSZM0926E);
        }

        // set PMsg
        NSZC053001PMsg pMsg = new NSZC053001PMsg();
        setValue(pMsg.glblCmpyCd, line.getGlblCmpyCd());
        setValue(pMsg.xxModeCd, xxModeCd);
        setValue(pMsg.slsDt, slsDt);
        setValue(pMsg.svcCrRebilPk, svcCrRebilTMsg.svcCrRebilPk);
        // call API
        NSZC053001 api = new NSZC053001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            // mod start 2019/12/16 QC#55068
            //throw new S21AbendException(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            if (NSZM0617E.equals(msgList.get(0).getXxMsgid())) {
                throw new S21NwfBizException(NSZM1369E, null);
            } else {
                throw new S21NwfBizException(msgList.get(0).getXxMsgid(), msgList.get(0).getXxMsgPrmArray());
            }
            // mod end 2019/12/16 QC#55068
        }
    }

    public SVC_CR_REBILTMsg getSvcCrRebilTMsgByIncdtId(NSZC053001TokenObjectLine line) {

        SVC_CR_REBILTMsg inMsg = new SVC_CR_REBILTMsg();
        inMsg.setConditionValue("glblCmpyCd01", line.getGlblCmpyCd());
        inMsg.setConditionValue("custIncdtId01", line.getCustIncdtId());
        inMsg.setMaxCount(1);
        inMsg.setSQLID("001");

        SVC_CR_REBILTMsgArray svcCrRebilTMsgArray = (SVC_CR_REBILTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);

        if (svcCrRebilTMsgArray.length() > 0) {
            return svcCrRebilTMsgArray.no(0);
        } else {
            return null;
        }
    }
}
