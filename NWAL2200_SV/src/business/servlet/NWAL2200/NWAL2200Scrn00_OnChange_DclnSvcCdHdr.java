/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2200.NWAL2200CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200Scrn00_OnChange_DclnSvcCdHdr
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/14   CITS            S.Tanikawa      Create          QC#13769
 *</pre>
 */
public class NWAL2200Scrn00_OnChange_DclnSvcCdHdr extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        int idx = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(idx);

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
