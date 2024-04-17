/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC102001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.*;

import business.parts.NSZC101001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC101001.NSZC101001;
import com.canon.cusa.s21.api.NSZ.NSZC101001.constant.NSZC101001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
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
 * Complete Contract Approval WF API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/21/2016   Hitachi         A.Kohinata      Create          QC#1088
 * 2016/12/28   Hitachi         K.Kojima        Update          QC#15640
 *</pre>
 */
public class NSZC102001 implements S21NwfApproveEvent<S21NwfContext, S21NwfToken>, S21NwfRejectEvent<S21NwfContext, S21NwfToken>, S21NwfProcessEndEvent<S21NwfContext, S21NwfToken> {

    /** The Supervisor does not exists. */
    public static final String NSZM0968E = "NSZM0968E";

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
        NSZC102001TokenObject tokenObj = (NSZC102001TokenObject) token.getTokenObj();
        if (SIGNAL_APPROVE.equals(name)) {
            // Approve Mode
            callApi(tokenObj, NSZC101001Constant.PROC_TP_APPROVE, context.getUserID());
        } else if (SIGNAL_REJECT.equals(name)) {
            // Reject Mode
            callApi(tokenObj, NSZC101001Constant.PROC_TP_REJECT, context.getUserID());
        } else if (COND_END_NO_USER.equals(name)) {
            // START 2016/12/28 K.Kojima [QC#15640,MOD]
            // throw new S21AbendException(NSZM0968E);
            throw new S21NwfBizException(NSZM0968E, null);
            // END 2016/12/28 K.Kojima [QC#15640,MOD]
        }
    }

    private void callApi(NSZC102001TokenObject tokenObj, String xxModeCd, String userId) {
        String slsDt = ZYPDateUtil.getSalesDate(tokenObj.getHdrAttrb3());

        // set PMsg
        NSZC101001PMsg pMsg = new NSZC101001PMsg();
        setValue(pMsg.glblCmpyCd, tokenObj.getHdrAttrb3());
        setValue(pMsg.slsDt, slsDt);
        setValue(pMsg.xxProcTpCd, xxModeCd);
        setValue(pMsg.dsContrNum, tokenObj.getHdrAttrb1());
        setValue(pMsg.xxUsrIdTxt, userId);
        // call API
        NSZC101001 api = new NSZC101001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            throw new S21AbendException(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
        }
    }
}
