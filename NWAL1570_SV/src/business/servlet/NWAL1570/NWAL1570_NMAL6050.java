/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1570_NMAL6050
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         K.Sato          Create          N/A
 * 2016/09/27   Fujitsu         K.Sato          Update          QC#13415
 *</pre>
 */
public class NWAL1570_NMAL6050 extends S21CommonHandler {

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

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

        final String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_BusinessUnit".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxCoaExtnSrchTxt);
        } else if ("OpenWin_Branch".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxCoaBrSrchTxt);
        } else if ("OpenWin_COAProduct".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxCoaProdSrchTxt);
        } else if ("OpenWin_COAMdse".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxCoaMdseTpSrchTxt);
        } else if ("OpenWin_LineCategory".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxLineCatgSrchTxt);
        } else if ("OpenWin_LineSource".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxOrdLineSrcSrchTxt);
        } else if ("OpenWin_WH".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxRtlWhSrchTxt);
        // QC#13415 DEL Start
//        } else if ("OpenWin_POVendor".equals(scrEventNm)) {
//            scrnMsg.setFocusItem(scrnMsg.xxVndSrchTxt);
        // QC#13415 DEL End
        } else if ("OpenWin_Zone".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxOrdZnSrchTxt);
        }
    }
}
