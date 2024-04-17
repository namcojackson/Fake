/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7090;

import static business.servlet.NMAL7090.constant.NMAL7090Constant.BIZ_ID;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.EVENT_PAGE_PREV;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.TABLE_A;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.TABLE_B;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.TABLE_C;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7090.NMAL7090CMsg;
import business.servlet.NMAL7090.common.NMAL7090CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;

/**
 * <pre>
 * Business ID  : NMAL7090 Item Supersessions Mass Add
 * Function Name: Page Next
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */

public class NMAL7090Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7090BMsg scrnMsg = (NMAL7090BMsg) bMsg;

        String tblNm = (String) (((HttpDispatchContext) ctx.getDispatchContext()).getParameters().get("xxPagenationTableNm"))[0];
        if (tblNm.equals(TABLE_A)) {
            scrnMsg.xxModeInd.setValue(TABLE_A);
        } else if (tblNm.equals(TABLE_B)) {
            NMAL7090CommonLogic.addCheckTableB(scrnMsg, false);
            scrnMsg.xxModeInd.setValue(TABLE_B);
        } else if (tblNm.equals(TABLE_C)) {
            scrnMsg.xxModeInd.setValue(TABLE_C);
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7090BMsg scrnMsg = (NMAL7090BMsg) bMsg;

        if (TABLE_A.equals(scrnMsg.xxModeInd.getValue())) {
            // set values to items of pagenation for next page pagenation
            ZYPTableUtil.clear(scrnMsg.A);
            scrnMsg.xxPageShowFromNum_A.setValue(scrnMsg.xxPageShowFromNum_A.getValueInt() - scrnMsg.A.length());
            scrnMsg.xxPageShowToNum_A.clear();
            scrnMsg.xxPageShowOfNum_A.clear();
        } else if (TABLE_B.equals(scrnMsg.xxModeInd.getValue())) {
            scrnMsg.xxPageShowFromNum_B.setValue(scrnMsg.xxPageShowFromNum_B.getValueInt() - scrnMsg.B.length() - 1);
            scrnMsg.xxPageShowToNum_B.clear();
        } else if (TABLE_C.equals(scrnMsg.xxModeInd.getValue())) {
            // set values to items of pagenation for next page pagenation
            ZYPTableUtil.clear(scrnMsg.C);
            scrnMsg.xxPageShowFromNum_C.setValue(scrnMsg.xxPageShowFromNum_C.getValueInt() - scrnMsg.C.length());
            scrnMsg.xxPageShowToNum_C.clear();
            scrnMsg.xxPageShowOfNum_C.clear();
        }

        NMAL7090CMsg bizMsg = new NMAL7090CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7090BMsg scrnMsg = (NMAL7090BMsg) bMsg;
        NMAL7090CMsg bizMsg = (NMAL7090CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7090CommonLogic.setScrnProp(this, scrnMsg, EVENT_PAGE_PREV);
    }
}
