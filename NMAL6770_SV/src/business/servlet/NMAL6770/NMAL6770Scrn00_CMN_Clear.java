/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6770;

import static business.servlet.NMAL6770.constant.NMAL6770Constant.BUSINESS_ID;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL6770.constant.NMAL6770Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6770.NMAL6770CMsg;
import business.servlet.NMAL6770.common.NMAL6770CommonLogic;
import business.servlet.NMAL6770.constant.NMAL6770Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/23   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL6770Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6770BMsg scrnMsg = (NMAL6770BMsg) bMsg;

        NMAL6770CMsg bizMsg = new NMAL6770CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6770BMsg scrnMsg = (NMAL6770BMsg) bMsg;
        NMAL6770CMsg bizMsg = (NMAL6770CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6770CommonLogic.initialControlScreen(this, scrnMsg);

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        // QC#7781
        if (NMAL6770Constant.MULT_SEL_MODE_CD.equals(scrnMsg.xxModeCd_H1.getValue())) {
            tblColor.setAlternateRowsBG("B", scrnMsg.B);
        }
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.dsAcctNm_H1);
    }
}
