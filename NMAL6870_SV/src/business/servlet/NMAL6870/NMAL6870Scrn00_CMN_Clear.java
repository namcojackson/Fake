/*
 * <Pre>Copyright(c)2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NMAL6870;

import static business.servlet.NMAL6870.constant.NMAL6870Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6870.common.NMAL6870CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Multi Selection Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/27   Fujitsu         S.Yoshida         Create          N/A
 *</pre>
 */
public class NMAL6870Scrn00_CMN_Clear extends S21CommonHandler {
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

        NMAL6870BMsg scrnMsg = (NMAL6870BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        ZYPTableUtil.clear(scrnMsg.R);
        scrnMsg.xxComnScrCondValTxt_H0.clear();
        scrnMsg.xxComnScrCondValTxt_H1.clear();
        scrnMsg.xxComnScrCondValTxt_H2.clear();
        scrnMsg.xxComnScrCondValTxt_H3.clear();
        scrnMsg.xxComnScrCondValTxt_H4.clear();
        scrnMsg.xxComnScrCondValTxt_H5.clear();
        scrnMsg.xxComnScrCondValTxt_H6.clear();
        scrnMsg.xxComnScrCondValTxt_H7.clear();
        scrnMsg.xxComnScrCondValTxt_H8.clear();
        scrnMsg.xxComnScrCondValTxt_H9.clear();
        scrnMsg.xxComnScrCondValTxt_HA.clear();
        scrnMsg.xxComnScrCondValTxt_HB.clear();

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        NMAL6870CommonLogic.initDisplayInfo(this, scrnMsg);

        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
