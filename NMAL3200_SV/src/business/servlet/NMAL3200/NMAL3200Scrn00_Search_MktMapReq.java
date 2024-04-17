/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3200;

import static business.servlet.NMAL3200.constant.NMAL3200Constant.BIZ_APP_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL3200.NMAL3200CMsg;
import business.servlet.NMAL3200.common.NMAL3200CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NMAL3200 Marketing Data Analysis
 * Function Name : Search_MktMapReq
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            K.Ogino         Create          N/A
 */
public class NMAL3200Scrn00_Search_MktMapReq extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.mktgMapRqstPk_DL);
        scrnMsg.addCheckItem(scrnMsg.mktgFldMapNm_DL);
        scrnMsg.addCheckItem(scrnMsg.upldErrFlg_SL);
        scrnMsg.addCheckItem(scrnMsg.mktgMapRqstProcFlg_SL);
        scrnMsg.putErrorScreen();

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
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, bMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        if (scrnMsg.A.getValidCount() > 0) {
            NMAL3200CommonLogic.setAttr(this, scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.A.no(0).mktgMapRqstPk_A1);
        }
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

    }
}
