/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3160;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Distribution Coordinator Work Bench
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/13   CITS            S.Katsuma       Create          N/A
 *</pre>
 */
public class NLBL3160_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NLBL3160BMsg scrnMsg = (NLBL3160BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3160BMsg scrnMsg = (NLBL3160BMsg) bMsg;
        if (!"CMN_Close".equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.P.no(6).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm, scrnMsg.P.no(7).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd, scrnMsg.P.no(8).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm, scrnMsg.P.no(9).xxPopPrm);
        }
        if ("OpenWin_RtlWHInfo".equals(scrnMsg.xxMntEventNm.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.rtlWhCd);
        } else if ("OpenWin_RtlSWHInfo".equals(scrnMsg.xxMntEventNm.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.rtlSwhCd);
        }
    }
}
