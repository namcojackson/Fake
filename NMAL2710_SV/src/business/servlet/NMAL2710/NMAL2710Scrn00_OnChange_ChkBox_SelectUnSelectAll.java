/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2710;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2710.common.NMAL2710CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2710Scrn00_OnChange_ChkBox_SelectUnSelectAll
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/16   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL2710Scrn00_OnChange_ChkBox_SelectUnSelectAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2710BMsg scrnMsg = (NMAL2710BMsg) bMsg;

        NMAL2710CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL2710CommonLogic.addCheckItemForDetail(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2710BMsg scrnMsg = (NMAL2710BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_DH.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxChkBox_A1, ZYPConstant.FLG_ON_Y);
            } else {
                scrnMsg.A.no(i).xxChkBox_A1.clear();
            }
        }

        scrnMsg.setFocusItem(scrnMsg.xxChkBox_DH);
    }
}
