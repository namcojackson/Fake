/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0530;

import static business.servlet.NSAL0530.constant.NSAL0530Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Solution Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Hitachi         T.Tomita        Create          N/A
 *</pre>
 */
public class NSAL0530_NWAL1130 extends S21CommonHandler {

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

        NSAL0530BMsg scrnMsg = (NSAL0530BMsg) bMsg;
        if (!CMN_CLOSE.equals(getLastGuard())) {
            if (OPENWIN_SVCMACHMSTR.equals(scrnMsg.xxScrEventNm.getValue())) {
                String svcMachMstrPk = scrnMsg.B.no(0).xxComnScrColValTxt.getValue();
                if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                    setValue(scrnMsg.svcMachMstrPk_H, new BigDecimal(svcMachMstrPk));
                }
            } else if (OPENWIN_MDL.equals(scrnMsg.xxScrEventNm.getValue())) {
                String mdlNm = scrnMsg.B.no(1).xxComnScrColValTxt.getValue();
                if (ZYPCommonFunc.hasValue(mdlNm)) {
                    setValue(scrnMsg.mdlNm_H, mdlNm);
                }
            }
        }
        if (OPENWIN_SVCMACHMSTR.equals(scrnMsg.xxScrEventNm.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.svcMachMstrPk_H);
        } else if (OPENWIN_MDL.equals(scrnMsg.xxScrEventNm.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.mdlNm_H);
        }

        scrnMsg.xxScrEventNm.clear();
    }
}
