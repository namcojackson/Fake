/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0410;

import static business.servlet.NSBL0410.constant.NSBL0410Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;


import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NSBL0410Scrn00_MoveWin_PartsEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0410BMsg scrnMsg = (NSBL0410BMsg) bMsg;

        Object[] params = new Object[2];

        String svcModCancDt = scrnMsg.A.no(getButtonSelectNumber()).svcModCancDt_A.getValue();
        String svcModObslDt = scrnMsg.A.no(getButtonSelectNumber()).svcModObslDt_A.getValue();
        if (ZYPDateUtil.isFutureDate(svcModCancDt) && ZYPDateUtil.isFutureDate(svcModObslDt)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, REF_MODE);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, EDIT_MODE);
        }
        params[0] = scrnMsg.xxModeCd;
        params[1] = scrnMsg.A.no(getButtonSelectNumber()).svcModDtlPk_A;

        setArgForSubScreen(params);

    }
}
