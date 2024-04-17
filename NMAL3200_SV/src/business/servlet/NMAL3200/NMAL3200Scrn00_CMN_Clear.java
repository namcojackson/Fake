/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3200;

import static business.servlet.NMAL3200.constant.NMAL3200Constant.*;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_SEARCH;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL3200.NMAL3200CMsg;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NMAL3200 Marketing Data Analysis
 * Function Name : CMN_Clear
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            K.Ogino         Create          N/A
 * 07/20/2016   CITS            K.Ogino         Update          QC#12033
 */
public class NMAL3200Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) bMsg;

        NMAL3200CMsg bizMsg = new NMAL3200CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) bMsg;
        NMAL3200CMsg bizMsg = (NMAL3200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        this.setButtonEnabled(BTN_SEARCH, true);
        this.setButtonEnabled(BTN_UPLOAD, false);
        scrnMsg.xxFileData.setInputProtected(true);
        scrnMsg.mktgFldMapNm_SC.setInputProtected(false);
        if (TAB_DOWNLOAD.equals(scrnMsg.xxDplyTab.getValue())) {
            S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        }
    }
}
