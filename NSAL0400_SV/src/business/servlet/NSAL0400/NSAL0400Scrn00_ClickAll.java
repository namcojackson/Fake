/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0400;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0400.common.NSAL0400CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NSAL0400Scrn00_ClickAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0400BMsg scrnMsg = (NSAL0400BMsg) bMsg;

        int idx = getButtonSelectNumber();
        BigDecimal dsContrPk = scrnMsg.B.no(idx).dsContrPk_B.getValue();
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0400_ABMsg abMsg = scrnMsg.A.no(i);
            if (dsContrPk.compareTo(abMsg.dsContrPk_AH.getValue()) != 0) {
                continue;
            }
            if (abMsg.xxChkBox_AD.isInputProtected()) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(abMsg.xxChkBox_AD, scrnMsg.B.no(idx).xxChkBox_B);
        }

        if (scrnMsg.A.getValidCount() > 0) {
            NSAL0400CommonLogic.protectFieldsAndButtons(this, scrnMsg);
            NSAL0400CommonLogic.setRowColors(scrnMsg);
        }


    }
}
