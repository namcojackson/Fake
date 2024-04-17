/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0090;

import static business.servlet.NLBL0090.constant.NLBL0090Constant.BIZ_APP_ID;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.PAGENATION_NEXT;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.SRCH_FUNCTION_ID;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.TBL_NAME_A;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL0090.NLBL0090CMsg;
import business.servlet.NLBL0090.common.NLBL0090CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/22   Fujitsu         Mori            Create          N/A
 *</pre>
 */
public class NLBL0090Scrn00_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;

        NLBL0090CommonLogic.setPagenationTableNm(scrnMsg, ctx);

        // pagenation initillize
        if (TBL_NAME_A.equals(scrnMsg.xxTblNm_Z1.getValue())) {
            // set values to items of pagenation for next page
            // pagenation
            NLBL0090CommonLogic.clearPagenationTableA(scrnMsg, PAGENATION_NEXT);
        } else {
            NLBL0090CommonLogic.clearPagenationTableB(scrnMsg, PAGENATION_NEXT);
        }

        NLBL0090CMsg bizMsg = new NLBL0090CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(SRCH_FUNCTION_ID);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;
        NLBL0090CMsg bizMsg = (NLBL0090CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (TBL_NAME_A.equals(scrnMsg.xxTblNm_Z1.getValue())) {

            // Combine Cells
            NLBL0090CommonLogic.setTblBackColorAndUnitCellsA(scrnMsg);

            // Radio button status
            scrnMsg.xxRadioBtn_A1.clear();
            scrnMsg.setFocusItem(scrnMsg.xxRadioBtn_A1);
        } else {

            NLBL0090CommonLogic.setTblBackColorB(scrnMsg);
        }
    }

}
