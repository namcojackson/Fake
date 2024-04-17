/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6460;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6460.common.NMAL6460CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/19   Hitachi         T.Aoyagi        Update          QC#8569
 *</pre>
 */
public class NMAL6460Scrn00_Change_ConslFlg extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6460BMsg scrnMsg = (NMAL6460BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();
        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(selectIdx).pmtTermConslFlg_A1.getValue())) {
            scrnMsg.A.no(selectIdx).pmtTermConslLastDomFlg_A1.setValue(ZYPConstant.FLG_OFF_N);
            scrnMsg.A.no(selectIdx).pmtTermConslDueDay_A1.clear();
            scrnMsg.A.no(selectIdx).pmtTermConslAddMthNum_A1.clear();
        }
        String userId = getUserProfileService().getContextUserInfo().getUserId();
        NMAL6460CommonLogic.setControlScreen(this, scrnMsg, userId);
        scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).pmtTermConslFlg_A1);
    }
}
