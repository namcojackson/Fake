/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.SCREEN_ID;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TAB_DETAIL;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TAB_HEADER;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TBL_ID_L;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TBL_ID_R;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1080.common.NPAL1080CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * Function Name : Button Action to Detail Tab
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS       Yasushi Nomura        Create          N/A
 * 12/19/2016   CITS            K.Ogino         Update          QC#15820
 *</pre>
 */
public class NPAL1080Scrn00_TAB_Detail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.delyAddlCmntTxt_H2);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        // Display TAB = Detail
        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);

        // QC#15820
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        String tabName = scrnMsg.xxDplyTab.getValue();

        if (TAB_DETAIL.equals(tabName)) {

            tblColor.setAlternateRowsBG(TBL_ID_L, scrnMsg.A);
            tblColor.setAlternateRowsBG(TBL_ID_R, scrnMsg.A);

        } 
        NPAL1080CommonLogic.commonInit(scrnMsg);
    }
}
