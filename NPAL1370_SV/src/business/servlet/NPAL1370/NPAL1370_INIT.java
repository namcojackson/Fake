/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1370;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NPAL1370.common.NPAL1370CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Business ID : NPAL1370 Min Max Planning Copy Popup
 * Function Name : Init
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/03/2015   CITS       Takeshi Tokutomi     Create          N/A
 *</pre>
 */
public class NPAL1370_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do Nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1370BMsg scrnMsg = (NPAL1370BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            EZDBStringItem param0 = (EZDBStringItem) params[0];
            EZDBStringItem param1 = (EZDBStringItem) params[1];
            EZDBStringItem param2 = (EZDBStringItem) params[2];
            EZDBStringItem param3 = (EZDBStringItem) params[3];
            // Param4 is Enable Flag. Length is 1.
            EZDBStringItem param4 = (EZDBStringItem) params[4];

            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_FR, param0);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd_FR, param1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_TO, param2);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd_TO, param3);
            ZYPEZDItemValueSetter.setValue(scrnMsg.mrpEnblFlg, param4);

        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, this.getUserProfileService().getGlobalCompanyCode());

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1370BMsg scrnMsg = (NPAL1370BMsg) bMsg;
        NPAL1370CommonLogic.setScrnItemProtection(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.rtlWhCd_FR);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NPAL1370CommonLogic.setNameForMessage((NPAL1370BMsg) arg0);

    }
}
