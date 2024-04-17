/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import business.blap.NWAL2200.NWAL2200CMsg;
import business.servlet.NWAL2200.common.NWAL2200CommonLogic;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200_NWAL0140
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 *</pre>
 */
public class NWAL2200_NWAL0140 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        if (scrnMsg.xxCellIdx.getValueInt() != -1) {
            return null;
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

        if (bizMsg != null) {

            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        int selectIdx = scrnMsg.xxCellIdx.getValueInt();

        if (selectIdx == -1) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxAllLineAddr_SH, NWAL2200CommonLogic.cmbnAddr(scrnMsg));
            scrnMsg.setFocusItem(scrnMsg.shipToCustAcctCd);
        } else {
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).billToCustLocCd_LC);
            } else {
                scrnMsg.setFocusItem(scrnMsg.C.no(selectIdx).billToCustLocCd_RC);
            }
        }
    }
}
