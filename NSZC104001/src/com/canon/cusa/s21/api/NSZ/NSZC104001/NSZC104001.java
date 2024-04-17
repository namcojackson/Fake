/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC104001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;

import java.util.List;

import business.parts.NSZC055001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC054001.NSZC054001TokenObject;
import com.canon.cusa.s21.api.NSZ.NSZC054001.NSZC054001TokenObjectLine;
import com.canon.cusa.s21.api.NSZ.NSZC055001.NSZC055001;
import com.canon.cusa.s21.api.NSZ.NSZC055001.constant.NSZC0550001Constant;
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
 * Preview Billing WF API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/30/2016   Hitachi         T.Aoyagi        Create          QC#1467
 * 2016/12/12   Hitachi         K.Kojima        Update          QC#15640
 * 2018/06/29   Hitachi         K.Kitachi       Update          QC#22245
 * 2019/11/28   Hitachi         K.Kishimoto     Update          QC#53567
 *</pre>
 */
public class NSZC104001 implements S21NwfApproveEvent<S21NwfContext, S21NwfToken>,
                                        S21NwfRejectEvent<S21NwfContext, S21NwfToken>,
                                        S21NwfProcessEndEvent<S21NwfContext, S21NwfToken> {

    /**
     * An error occurred in Workflow Process.
     */
    public static final String NSZM0926E = "NSZM0926E";

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
        NSZC054001TokenObject tokenObj = (NSZC054001TokenObject) token.getTokenObj();
        List<NSZC054001TokenObjectLine> lines = tokenObj.getLineData();
        for (NSZC054001TokenObjectLine line : lines) {
            if (SIGNAL_APPROVE.equals(name)) {
                // Approve Mode
                // START 2019/11/28 [QC#53567, Mod]
                callApi(line, context.getUserID(), tokenObj.getBizId());
                // END   2019/11/28 [QC#53567, Mod]
                // START 2016/12/12 K.Kojima [QC#15640,ADD]
            } else if (COND_END_NO_USER.equals(name)) {
                throw new S21NwfBizException("NSZM1077E", null);
                // END 2016/12/12 K.Kojima [QC#15640,ADD]
            } else {
                continue;
            }
        }
    }

    // START 2019/11/28 [QC#53567, Mod]
    private void callApi(NSZC054001TokenObjectLine line, String userId, String wfBizId) {
        // set PMsg
        NSZC055001PMsg pMsg = new NSZC055001PMsg();
        setValue(pMsg.glblCmpyCd, line.getGlblCmpyCd());
        setValue(pMsg.xxProcTpCd, NSZC0550001Constant.XX_PROC_TP_CD_APPROVE);
        setValue(pMsg.dsContrNum, line.getDsContrNum());
        pMsg.dsContrPk.clear();
        setValue(pMsg.svcMachMstrPk, line.getSvcMachMstrPk());
        setValue(pMsg.billToCustCd, line.getBillToCustCd());
        setValue(pMsg.svcContrBllgThruDt, line.getSvcContrBllgThruDt());
        // START 2018/06/29 K.Kitachi [QC#22245, MOD]
        setValue(pMsg.usrId, userId);
        // END 2018/06/29 K.Kitachi [QC#22245, MOD]
        // START 2019/11/28 [QC#53567, Add]
        setValue(pMsg.bizId, wfBizId);
        // END   2019/11/28 [QC#53567, Add]
        // call API
        NSZC055001 api = new NSZC055001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            throw new S21AbendException(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
        }
    }
    // END   2019/11/28 [QC#53567, Mod]
}
