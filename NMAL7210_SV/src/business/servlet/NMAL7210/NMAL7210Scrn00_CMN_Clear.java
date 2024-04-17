/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7210;

import static business.servlet.NMAL7210.constant.NMAL7210Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7210.common.NMAL7210CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7210Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NMAL7210Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7210BMsg scrnMsg = (NMAL7210BMsg) bMsg;

        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        scrnMsg.prcFmlaNm_H1.clear();
        scrnMsg.prcFmlaTpCd_H1.clear();
        scrnMsg.prcFmlaDescTxt_H1.clear();
        scrnMsg.effFromDt_H1.clear();
        scrnMsg.effThruDt_H1.clear();
        scrnMsg.actvFlg_H1.clear();
        scrnMsg.xxRadioBtn.clear();

        ZYPTableUtil.clear(scrnMsg.A);

        NMAL7210CommonLogic.initCmnBtnProp(this);
        NMAL7210CommonLogic.setBtnProp(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.prcFmlaNm_H1);
    }
}
