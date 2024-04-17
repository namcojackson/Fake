/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.EVENT_NM_NMAL2340_LINE;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.EVENT_NM_NMAL2340_RMA;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2200.NWAL2200CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200_NWAL2340 AddressMassApply
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/29   CITS            S.Tanikawa      Create          Unit Test#202
 *</pre>
 */
public class NWAL2200_NWAL2340 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        if ("EVENT_CMN_CLOSE".equals(getLastGuard())) {
            return null;
        }

        if (EVENT_NM_NMAL2340_LINE.equals(scrnMsg.xxScrEventNm.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                for (int j = 0; j < scrnMsg.P.getValidCount(); j++) {
                    if (scrnMsg.P.no(j).xxPopPrm_0.getValue().equals(scrnMsg.A.no(i).addrLbTxt_LC.getValue())) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToCustAcctCd_LC, scrnMsg.P.no(j).xxPopPrm_1);
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToCustLocCd_LC, scrnMsg.P.no(j).xxPopPrm_2);
                    }
                }
            }
        } else if (EVENT_NM_NMAL2340_RMA.equals(scrnMsg.xxScrEventNm.getValue())) {
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                for (int j = 0; j < scrnMsg.P.getValidCount(); j++) {
                    if (scrnMsg.P.no(j).xxPopPrm_0.getValue().equals(scrnMsg.C.no(i).addrLbTxt_RC.getValue())) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(i).shipToCustAcctCd_RC, scrnMsg.P.no(j).xxPopPrm_1);
                        ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(i).shipToCustLocCd_RC, scrnMsg.P.no(j).xxPopPrm_2);
                    }
                }
            }
        }
        NWAL2200CMsg bizMsg = new NWAL2200CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        NWAL2200CMsg bizMsg = (NWAL2200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
