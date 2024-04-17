/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_RMA;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/01/23   Fujitsu         T.Aoi           Create          QC#18798(Sol#173)
 *</pre>
 */
public class NWAL2200Scrn00_Config_Check_All extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, false, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (!scrnMsg.A.no(i).xxChkBox_LC.isInputProtected()) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxChkBox_LC, ZYPConstant.FLG_ON_Y);
                } else {
                    scrnMsg.A.no(i).xxChkBox_LC.clear();
                }
            }
        } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                if (!scrnMsg.C.no(i).xxChkBox_RC.isInputProtected()) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(i).xxChkBox_RC, ZYPConstant.FLG_ON_Y);
                } else {
                    scrnMsg.C.no(i).xxChkBox_RC.clear();
                }
            }
        }
    }
}
