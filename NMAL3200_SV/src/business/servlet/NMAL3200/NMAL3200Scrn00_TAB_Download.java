/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3200;

import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_2_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_2_NAME;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_2_VAL;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.SCRN_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.TAB_DOWNLOAD;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NMAL3200 Marketing Data Analysis
 * Function Name : TAB_Download
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            K.Ogino         Create          N/A
 */
public class NMAL3200Scrn00_TAB_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) bMsg;
        scrnMsg.xxDplyTab.setValue(TAB_DOWNLOAD);
        scrnMsg.setFocusItem(scrnMsg.mktgMapRqstPk_DL);
        this.setButtonProperties(BTN_CMN_BTN_2_ID, BTN_CMN_BTN_2_NAME, BTN_CMN_BTN_2_VAL, 0, null);

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, bMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

    }
}
