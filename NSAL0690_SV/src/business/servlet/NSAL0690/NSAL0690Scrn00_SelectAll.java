/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0690;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Renew Contract or Machine on Contract
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         K.Kasai         Create          N/A
 * 2015/12/23   Hitachi         T.Tsuchida      Update          QC#1461
 * 2017/10/27   Hitachi         K.Kojima        Update          QC#21742
 *</pre>
 */
public class NSAL0690Scrn00_SelectAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0690BMsg scrnMsg = (NSAL0690BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_AL.getValue())) {
                for (int j = 0; j < scrnMsg.B.getValidCount(); j++) {
                    if (scrnMsg.B.no(j).dsContrPk_B1.getValue().compareTo(scrnMsg.A.no(i).dsContrPk_A1.getValue()) == 0) {
                        // START 2017/10/27 K.Kojima [QC#21742,MOD]
                        // ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(j).xxChkBox_B1, CHKBOX_ON_Y);
                        if (!scrnMsg.B.no(j).xxChkBox_B1.isInputProtected()) {
                            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(j).xxChkBox_B1, CHKBOX_ON_Y);
                        }
                        // END 2017/10/27 K.Kojima [QC#21742,MOD]
                    }
                }
            } else {
                for (int j = 0; j < scrnMsg.B.getValidCount(); j++) {
                    if (scrnMsg.B.no(j).dsContrPk_B1.getValue().compareTo(scrnMsg.A.no(i).dsContrPk_A1.getValue()) == 0) {
                        // START 2017/10/27 K.Kojima [QC#21742,MOD]
                        // ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(j).xxChkBox_B1, FLG_OFF_N);
                        if (!scrnMsg.B.no(j).xxChkBox_B1.isInputProtected()) {
                            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(j).xxChkBox_B1, FLG_OFF_N);
                        }
                        // END 2017/10/27 K.Kojima [QC#21742,MOD]
                    }
                }
            }
        }
    }
}
