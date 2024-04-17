/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8830;

import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDSMsg;
import business.blap.NYEL8830.common.NYEL8830CommonLogic;

import com.canon.cusa.s21.common.NYX.NYXC880001.NYXC880001Query;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.nwf.util.process.S21NwfUtilProcessFactory;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NYEL8830BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/29   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NYEL8830BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NYEL8830CMsg bizMsg = (NYEL8830CMsg) cMsg;
            NYEL8830SMsg glblMsg = (NYEL8830SMsg) sMsg;

            if ("NYEL8830_INIT".equals(screenAplID)) {
                doProcess_NYEL8830_INIT(bizMsg, glblMsg);

            } else if ("NYEL8830Scrn00_Refresh".equals(screenAplID)) {
                doProcess_NYEL8830Scrn00_Refresh(bizMsg, glblMsg);

            } else if ("NYEL8830Scrn00_Select_TaskNm".equals(screenAplID)) {
                doProcess_NYEL8830Scrn00_Select_TaskNm(bizMsg);

            } else if ("NYEL8830_NYEL8820".equals(screenAplID)) {
                doProcess_NYEL8830_NYEL8820(bizMsg, glblMsg);

            } else if ("NYEL8830_NWAL1500".equals(screenAplID)) {
                doProcess_NYEL8830_NWAL1500(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NYEL8830_INIT(NYEL8830CMsg bizMsg, NYEL8830SMsg glblMsg) {
        NYEL8830CommonLogic.search(bizMsg, glblMsg, this.getContextUserInfo().getUserId());
    }

    /**
     * Refresh Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NYEL8830Scrn00_Refresh(NYEL8830CMsg bizMsg, NYEL8830SMsg glblMsg) {
        NYEL8830CommonLogic.search(bizMsg, glblMsg, this.getContextUserInfo().getUserId());
    }

    /**
     * NYEL8820 Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NYEL8830Scrn00_Select_TaskNm(NYEL8830CMsg bizMsg) {
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfProcess proc = null;
        try {
            S21NwfContext context = factory.getContex();
            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());
            proc = context.getProcess(bizMsg.wfProcPk.getValue());
        } catch (NumberFormatException e) {
            bizMsg.setMessageInfo("NMZM0014E");
            return;
        } catch (S21NwfSystemException e) {
            EZDMessageInfo info = e.getMessageInfo();

            if (info != null) {
                bizMsg.setMessageInfo(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
            } else {
                bizMsg.setMessageInfo("NMZM0014E", new String[] {bizMsg.wfProcPk.getValue().toPlainString() });
            }
            return;
        }
        
        if (proc == null) {
            bizMsg.setMessageInfo("NMZM0014E");
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbTxt_01, proc.getDocumentId());
        if (!ZYPCommonFunc.hasValue(bizMsg.wfBizAttrbTxt_01)) {
            bizMsg.setMessageInfo("NMZM0014E");
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NYEL8830_NYEL8820(NYEL8830CMsg bizMsg, NYEL8830SMsg glblMsg) {
        NYEL8830CommonLogic.search(bizMsg, glblMsg, this.getContextUserInfo().getUserId());
    }

    /**
     * NWAL1500 Event
     * @param bizMsg
     */
    private void doProcess_NYEL8830_NWAL1500(NYEL8830CMsg bizMsg, NYEL8830SMsg glblMsg) {
        NYEL8830CommonLogic.search(bizMsg, glblMsg, this.getContextUserInfo().getUserId());
    }
}
