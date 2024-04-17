/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0620;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/22   Hitachi         K.Kojima        Create          QC#16168
 * 2019/08/30   Hitachi         T.Aoyagi        Update          QC#53005
 *</pre>
 */
public class NSAL0620_NSAL1240 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;
        // START 2019/08/30 [QC#53005,ADD]
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_SrchSer".equals(scrEventNm)) {
        // END 2019/08/30 [QC#53005,ADD]
            if (scrnMsg.O.getValidCount() > 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_H, scrnMsg.O.no(0).serNum_O.getValue());
            }
            scrnMsg.setFocusItem(scrnMsg.serNum_H);
        // START 2019/08/30 [QC#53005,ADD]
        } else if ("OpenWin_SrchConfig".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk_P)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.svcConfigMstrPk_H, scrnMsg.svcConfigMstrPk_P.getValue());
            }
            scrnMsg.setFocusItem(scrnMsg.svcConfigMstrPk_H);
        }
        // END 2019/08/30 [QC#53005,ADD]
    }
}
