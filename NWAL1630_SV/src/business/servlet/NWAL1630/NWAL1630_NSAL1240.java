/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1630;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg; // import parts.common.EZDMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import static business.servlet.NWAL1630.constant.NWAL1630Constant.BIZ_ID;
import static business.servlet.NWAL1630.constant.NWAL1630Constant.EVENT_NM_CMN_CLOSE;

import business.blap.NWAL1630.NWAL1630CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/22   Fujitsu         T.Ogura         Create          S21_NA#18859(Sol#154)
 * 2019/01/24   Fujitsu         K.Ishizuka      Update          S21_NA#29446
 *</pre>
 */
public class NWAL1630_NSAL1240 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2019/01/24 S21_NA#29446 Mod Start
        // return null;
        NWAL1630BMsg scrnMsg = (NWAL1630BMsg) bMsg;
        if (EVENT_NM_CMN_CLOSE.equals(getLastGuard()) && scrnMsg.O.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrNum, scrnMsg.dsContrNum_PO);
            ZYPEZDItemValueSetter.setValue(scrnMsg.serNum, scrnMsg.O.no(0).serNum_O);
        }
        NWAL1630CMsg bizMsg = new NWAL1630CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // 2019/01/24 S21_NA#29446 Mod End
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1630BMsg scrnMsg = (NWAL1630BMsg) bMsg;
        // 2019/01/24 S21_NA#29446 Add Start
        NWAL1630CMsg bizMsg = (NWAL1630CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // 2019/01/24 S21_NA#29446 Add End
        // 2019/01/24 S21_NA#29446 Del Start
        // if (EVENT_NM_CMN_CLOSE.equals(getLastGuard()) && scrnMsg.O.getValidCount() > 0) {
        //     ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrNum, scrnMsg.dsContrNum_PO);
        //     ZYPEZDItemValueSetter.setValue(scrnMsg.serNum, scrnMsg.O.no(0).serNum_O);
        // }
        // 2019/01/24 S21_NA#29446 Del End

        scrnMsg.setFocusItem(scrnMsg.serNum);
    }
}
