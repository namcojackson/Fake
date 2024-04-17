/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLCL1010.NLCL1010CMsg;
//import business.servlet.NLCL1010.constant.NLCL1010Constant;

import business.servlet.NLCL1010.constant.NLCL1010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/02   Hitachi         T.Tomita        Create          QC#3586
 *</pre>
 */
public class NLCL1010_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1010BMsg scrnMsg = (NLCL1010BMsg) bMsg;
        String eventNm = scrnMsg.xxScrEventNm.getValue();

        if (!NLCL1010Constant.POPUP_CLOSE.equals(getLastGuard())) {
            if ("OpenWin_SrchMdseCd".equals(eventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_C0, scrnMsg.P.no(0).xxPopPrm);
            } else if ("OpenWin_SrchOrigMdseCd".equals(eventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.origMdseCd_C0, scrnMsg.P.no(0).xxPopPrm);
            } else if ("OpenWin_AddMdseCd".equals(eventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_H0, scrnMsg.P.no(0).xxPopPrm);
            } else if ("OpenWin_AddOldMdseCd".equals(eventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.oldMdseCd_H0, scrnMsg.P.no(0).xxPopPrm);
            }
        }
    }
}
