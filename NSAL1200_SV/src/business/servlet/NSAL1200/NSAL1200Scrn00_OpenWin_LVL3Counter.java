/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1200;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL1200.common.NSAL1200CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Counter Group Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/03   Hitachi         T.Kanasaka      Create          QC#18193
 *</pre>
 */
public class NSAL1200Scrn00_OpenWin_LVL3Counter extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1200BMsg scrnMsg = (NSAL1200BMsg) bMsg;
        setValue(scrnMsg.xxScrEventNm, ctx.getEventName());
        int rowIndex = getButtonSelectNumber();
        String mtrLbCd = scrnMsg.A.no(rowIndex).mdlMtrLbCd_A.getValue();
        String bllgMtrLbCd = scrnMsg.A.no(rowIndex).bllgMtrLbCd_L3.getValue();
        String mtrLbDescTxt = scrnMsg.A.no(rowIndex).mtrLbDescTxt_L3.getValue();
        setArgForSubScreen(NSAL1200CommonLogic.createBllgMtrListPopupParameter(scrnMsg, mtrLbCd, bllgMtrLbCd, mtrLbDescTxt));
    }
}
