/*
 * <Pre>Copyright(c)2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NWAL1130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1130.common.NWAL1130CommonLogic;
import business.servlet.NWAL1130.constant.NWAL1130Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 *  Common PopUp NWAL1130Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/22/2012   Fujitsu         T.Ishii         Create          N/A
 * 10/09/2015   Fujitsu         M.Nakamura      Update          S21 CSA
 * 02/19/2016   Fujitsu         W.Honda         Update          S21 CSA QC#1130
 * 2016/07/25   Hitachi         T.Tomita        Update          QC#11471
 *</pre>
 */
public class NWAL1130Scrn00_CMN_Clear extends S21CommonHandler implements NWAL1130Constant {
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1130BMsg scrnMsg = (NWAL1130BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
//        // START 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
//        scrnMsg.xxComnScrCondValTxt_H0.clear();
//        scrnMsg.xxComnScrCondValTxt_H1.clear();
//        scrnMsg.xxComnScrCondValTxt_H2.clear();
//        scrnMsg.xxComnScrCondValTxt_H3.clear();
//        scrnMsg.xxComnScrCondValTxt_H4.clear();
//        // S21 CSA Add Start
//        scrnMsg.xxComnScrCondValTxt_H5.clear();
//        scrnMsg.xxComnScrCondValTxt_H6.clear();
//        scrnMsg.xxComnScrCondValTxt_H7.clear();
//        scrnMsg.xxComnScrCondValTxt_H8.clear();
//        scrnMsg.xxComnScrCondValTxt_H9.clear();
//        scrnMsg.xxComnScrCondValTxt_HA.clear();
//        scrnMsg.xxComnScrCondValTxt_HB.clear();
//        // S21 CSA Add End
        scrnMsg.xxScrItem500Txt_H0.clear();
        scrnMsg.xxScrItem500Txt_H1.clear();
        scrnMsg.xxScrItem500Txt_H2.clear();
        scrnMsg.xxScrItem500Txt_H3.clear();
        scrnMsg.xxScrItem500Txt_H4.clear();
        scrnMsg.xxScrItem500Txt_H5.clear();
        scrnMsg.xxScrItem500Txt_H6.clear();
        scrnMsg.xxScrItem500Txt_H7.clear();
        scrnMsg.xxScrItem500Txt_H8.clear();
        scrnMsg.xxScrItem500Txt_H9.clear();
        scrnMsg.xxScrItem500Txt_HA.clear();
        scrnMsg.xxScrItem500Txt_HB.clear();
        // END 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        NWAL1130CommonLogic.initDisplayInfo(this, scrnMsg);

        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
