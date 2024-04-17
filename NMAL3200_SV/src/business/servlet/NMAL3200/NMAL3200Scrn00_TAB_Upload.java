/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3200;

import static business.servlet.NMAL3200.constant.NMAL3200Constant.SCRN_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.TAB_UPLOAD;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL3200.common.NMAL3200CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NMAL3200 Marketing Data Analysis
 * Function Name : TAB_Upload
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            K.Ogino         Create          N/A
 * 07/27/2016   CITS            K.Ogino         Update          QC#11815
 */
public class NMAL3200Scrn00_TAB_Upload extends S21CommonHandler {

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
        scrnMsg.xxDplyTab.setValue(TAB_UPLOAD);
        scrnMsg.setFocusItem(scrnMsg.mktgFldMapNm_DB);
        NMAL3200CommonLogic.setCommonButtonInit(this);

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
