/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8899.common;

import static business.servlet.NYEL8899.constant.NYEL8899Constant.BTN_CMN_SAV;
import static business.servlet.NYEL8899.constant.NYEL8899Constant.BTN_CMN_SUB;
import static business.servlet.NYEL8899.constant.NYEL8899Constant.BTN_CMN_APL;
import static business.servlet.NYEL8899.constant.NYEL8899Constant.BTN_CMN_APR;
import static business.servlet.NYEL8899.constant.NYEL8899Constant.BTN_CMN_RJT;
import static business.servlet.NYEL8899.constant.NYEL8899Constant.BTN_CMN_DWL;
import static business.servlet.NYEL8899.constant.NYEL8899Constant.BTN_CMN_DEL;
import static business.servlet.NYEL8899.constant.NYEL8899Constant.BTN_CMN_CLR;
import static business.servlet.NYEL8899.constant.NYEL8899Constant.BTN_CMN_RST;
import static business.servlet.NYEL8899.constant.NYEL8899Constant.BTN_CMN_RTN;

import java.util.List;

import parts.common.EZDAbendException;
import business.servlet.NYEL8899.NYEL8899BMsg;

import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.model.impl.S21NwfWorkItemImpl;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcessFactory;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.nwf.util.process.S21NwfUtilProcessFactory;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8899CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8899CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(NYEL8899BMsg scrnMsg, S21CommonHandler handler) {
        // TODO [Template] Please change the 4th parameters
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

        //initCtrl(scrnMsg, handler);
    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * @param scrnMsg
     * @param handler
     */
    private static void initCtrl(NYEL8899BMsg scrnMsg, S21CommonHandler handler) {
        int wfaccessApp = 0;
        boolean isApproval = false;
        int wfrejectApp = 0;
        int isSubmit = 1;

        S21NwfProcessFactory procFactory = S21NwfUtilProcessFactory.getInstance();

        if (procFactory != null) {
            S21NwfContextFactory factory = new S21NwfUtilContextFactory();
            List<S21NwfProcess> processes;
            try {
                S21NwfContext context = factory.getContex();
                context.setProcessFactory(procFactory);
                processes = context.getProcess(scrnMsg.wfProcNm.getValue(), scrnMsg.wfBizAttrbTxt_01.getValue());
            } catch (S21NwfSystemException e) {
                throw new EZDAbendException(e);
            }

            if ((processes != null) && (processes.size() > 0)) {
                S21NwfProcess process = processes.get(0);
                
                //isSubmit = 0;
                isSubmit = 1;
                S21NwfToken token = process.getToken();
                
                if (!(S21NwfProcess.STATUS.CLOSE.equals(process.getStatus()) || S21NwfProcess.STATUS.CANCEL.equals(process.getStatus()))) {
                //if (process.isActive()) {
                    S21NwfWorkItemImpl activeTask = token.getActiveWorkItem();

                    if (activeTask != null) {
                        /*
                        if (token.getActiveWorkItem().isSubmitable()) {
                            isSubmit = 1;
                        }
                        */

                        if (token.getActiveWorkItem().isApprovable()) {
                            wfaccessApp = 1;
                            isApproval = true;
                        }

                        if (token.getActiveWorkItem().isRejectable()) {
                            wfrejectApp = 1;
                            isApproval = true;
                        }
                    }
                }
            }
        }
        scrnMsg.xxWfAsgCmntTxt.setInputProtected(!isApproval);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], isSubmit, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], wfaccessApp, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], wfrejectApp, null);
    }
}
