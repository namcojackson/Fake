/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0150;

import static business.servlet.NWCL0150.constant.NWCL0150Constant.BIZ_APP_ID;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0150.NWCL0150CMsg;
import business.servlet.NWCL0150.common.NWCL0150CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NWCL0150:Lease Package Hold Release Screen
 * Function Name : Search
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/09/2016   CITS            K.Ogino         Create          N/A
 */
public class NWCL0150Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWCL0150BMsg scrnMsg = (NWCL0150BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        scrnMsg.addCheckItem(scrnMsg.invNum);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWCL0150BMsg scrnMsg = (NWCL0150BMsg) bMsg;

        NWCL0150CMsg bizMsg = new NWCL0150CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWCL0150BMsg scrnMsg = (NWCL0150BMsg) bMsg;
        NWCL0150CMsg bizMsg = (NWCL0150CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWCL0150CommonLogic.setAttr(scrnMsg);

        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).cpoOrdNum_A1);
        }
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}