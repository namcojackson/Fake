/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7180;

import static business.servlet.NMAL7180.constant.NMAL7180Constant.BIZ_ID;
import static business.servlet.NMAL7180.constant.NMAL7180Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7180.NMAL7180CMsg;
import business.servlet.NMAL7180.common.NMAL7180CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7180Scrn00_CMN_Reset
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Fujitsu         W.Honda         Create          N/A
 * 2017/08/21   Fujitsu         M.Yamada        Update          QC#18785(L3)
 *</pre>
 */
public class NMAL7180Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7180BMsg scrnMsg = (NMAL7180BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.A);
        NMAL7180CommonLogic.clearHeaderItem(scrnMsg);

        NMAL7180CMsg bizMsg = new NMAL7180CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7180BMsg scrnMsg = (NMAL7180BMsg) bMsg;
        NMAL7180CMsg bizMsg = (NMAL7180CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7180CommonLogic.controlScreen(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.prcGrpNm);
        // QC#18785
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        scrnMsg.xxSortTblNm.clear();
        scrnMsg.xxSortItemNm.clear();
        scrnMsg.xxSortOrdByTxt.clear();
    }
}
