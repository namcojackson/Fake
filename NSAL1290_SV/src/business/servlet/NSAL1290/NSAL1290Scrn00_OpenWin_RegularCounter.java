/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1290;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL1290.common.NSAL1290CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/01   Hitachi         M.Gotou         Create          N/A
 *</pre>
 */
public class NSAL1290Scrn00_OpenWin_RegularCounter extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1290BMsg scrnMsg = (NSAL1290BMsg) bMsg;
        setValue(scrnMsg.xxScrEventNm, ctx.getEventName());
        String mtrLbNm = null;
        if (hasValue(scrnMsg.mtrLbNm_H)) {
            mtrLbNm = scrnMsg.mtrLbNm_H.getValue();
        }
        String mtrLbDescTxt = null;
        String mtrLbTp = MTR_LB_TP.REGULAR_METER;
        setArgForSubScreen(NSAL1290CommonLogic.createMtrLbListPopupParameter(scrnMsg, mtrLbNm, mtrLbDescTxt, mtrLbTp));
    }
}
