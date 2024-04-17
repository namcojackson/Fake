/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0480;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0480.common.NSAL0480CommonLogic;
import business.servlet.NSAL0480.constant.NSAL0480Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/07   Hitachi         N.Arai          Create          QC#15001
 *</pre>
 */
public class NSAL0480Scrn00_OpenWin_SvcSkill extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0480BMsg scrnMsg = (NSAL0480BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(NSAL0480Constant.ITEM_NM_SVC_SKILL);

        // set param
        NSAL0480CommonLogic.setParamSvcSkill(scrnMsg);
        setArgForSubScreen(NSAL0480CommonLogic.getParamNMAL6050(scrnMsg));

    }
}
