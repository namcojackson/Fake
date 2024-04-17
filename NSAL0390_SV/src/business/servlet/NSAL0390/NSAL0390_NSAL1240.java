/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0390;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/18   Hitachi         A.Kohinata      Create          QC#4212
 *</pre>
 */
public class NSAL0390_NSAL1240 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0390BMsg scrnMsg = (NSAL0390BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_SrchSerFrom".equals(scrEventNm)) {
            setValue(scrnMsg.serNum_HF, scrnMsg.O.no(0).serNum_O);
        } else if ("OpenWin_SrchSerThru".equals(scrEventNm)) {
            setValue(scrnMsg.serNum_HT, scrnMsg.O.no(0).serNum_O);
        } else if ("OpenWin_SrchIBFrom".equals(scrEventNm)) {
            setValue(scrnMsg.svcMachMstrPk_HF, scrnMsg.O.no(0).svcMachMstrPk_O);
        } else if ("OpenWin_SrchIBThru".equals(scrEventNm)) {
            setValue(scrnMsg.svcMachMstrPk_HT, scrnMsg.O.no(0).svcMachMstrPk_O);
        }
    }
}
