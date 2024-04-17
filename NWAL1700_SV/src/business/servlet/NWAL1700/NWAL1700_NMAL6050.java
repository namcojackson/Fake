/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1700_NMAL6050
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1700_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("NWAL1700Scrn00_OpenWin_ArTran".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.dsInvTpCd);
        } else if ("NWAL1700Scrn00_OpenWin_Carrier".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.defCarrSvcLvlCd);
        } else if ("NWAL1700Scrn00_OpenWin_AJE".equals(scrEventNm)) {
            int cellIdx = scrnMsg.xxCellIdx.getValueInt();
            if (cellIdx >= 0) {
                scrnMsg.setFocusItem(scrnMsg.A.no(cellIdx).ajeAcctBatCd_A);
            }
        }
    }
}
