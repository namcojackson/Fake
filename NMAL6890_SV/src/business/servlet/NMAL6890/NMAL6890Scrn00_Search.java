/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6890;

import static business.servlet.NMAL6890.constant.NMAL6890Constant.BIZ_APP_ID;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6890.NMAL6890CMsg;
import business.servlet.NMAL6890.common.NMAL6890CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NMAL6890 Warehouse Search
 * Function Name : Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/11/2016   CITS            Y.Kuroda        Create          N/A
 * 10/21/2022   HITACHI         B.Amarsanaa     Update          QC#60608
 *</pre>
 */
public class NMAL6890Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6890BMsg scrnMsg = (NMAL6890BMsg) bMsg;

        // Input Attribute Check Area
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        //10/21/2022 HITACHI B.Amarsanaa (QC#60608) ADD START 
        scrnMsg.addCheckItem(scrnMsg.ctyAddr);
        //10/21/2022 HITACHI B.Amarsanaa (QC#60608) ADD END
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6890BMsg scrnMsg = (NMAL6890BMsg) bMsg;

        // set values to items of pagenation for next page pagenation
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        NMAL6890CMsg bizMsg = new NMAL6890CMsg();

        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6890BMsg scrnMsg = (NMAL6890BMsg) bMsg;
        NMAL6890CMsg bizMsg = (NMAL6890CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }

        // disable the input fields.
        NMAL6890CommonLogic.setTableScreen(scrnMsg);

        // clear image file of sort columns (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        // focus on Supplier Number.
        scrnMsg.setFocusItem(scrnMsg.rtlWhNm_H1);

    }
}
