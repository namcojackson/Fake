/*
 * <pre>Copyright (c) 2019 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC047001;

import static com.canon.cusa.s21.api.NSZ.NSZC047001.constant.NSZC047001Constant.ZZZM9007E;
import business.parts.NSZC047099PMsg;

import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * Contract Billing Schedule API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/06/13   Hitachi         K.Kitachi       Create          QC#50811
 * </pre>
 */
public class M99RecovAccSchdRelnProcess {

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatchTp;

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
        this.onBatchTp = onBatchType;
        checkParameter(msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        recovAccSchdRelnProc(msgMap);
    }

    private void recovAccSchdRelnProc(S21ApiMessageMap msgMap) {
        NSZC047099PMsg pMsg = (NSZC047099PMsg) msgMap.getPmsg();
        NSZC047001CommonLogic.recovAccSchdReln(msgMap, pMsg.glblCmpyCd.getValue(), pMsg.dsContrPk.getValue());
    }

    private void checkParameter(S21ApiMessageMap msgMap) {
        NSZC047099PMsg pMsg = (NSZC047099PMsg) msgMap.getPmsg();
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.glblCmpyCd, ZZZM9007E, new String[] {"Global Company Code" });
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.slsDt, ZZZM9007E, new String[] {"Salse Date" });
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.dsContrPk, ZZZM9007E, new String[] {"DS Contract PK" });
    }
}
