/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0140;

import static business.servlet.NWCL0140.constant.NWCL0140Constant.BIZ_APP_ID;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_PAGE_JUMP_EL;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_PAGE_JUMP_EX;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.TABLE_A;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.TABLE_B;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0140.NWCL0140CMsg;
import business.servlet.NWCL0140.common.NWCL0140CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;

/**
 * <pre>
 * Business ID : NWCL0140 CFS Lease Package Maintenance Screen
 * Function Name : NWCL0140Scrn00_PageJump
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/30/2016   CITS            K.Ogino         Create          N/A
 */
public class NWCL0140Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWCL0140BMsg scrnMsg = (NWCL0140BMsg) bMsg;
        String tblNm = (String) (((HttpDispatchContext) ctx.getDispatchContext()).getParameters().get("xxPagenationTableNm"))[0];
        if (TABLE_A.equals(tblNm)) {
            S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum_EL, scrnMsg.xxPageShowToNum_EL, scrnMsg.xxPageShowOfNum_EL, scrnMsg.xxPageShowCurNum_EL, scrnMsg.xxPageShowTotNum_EL);
        } else if (TABLE_B.equals(tblNm)) {
            S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.B, scrnMsg.xxPageShowFromNum_EX, scrnMsg.xxPageShowToNum_EX, scrnMsg.xxPageShowOfNum_EX, scrnMsg.xxPageShowCurNum_EX, scrnMsg.xxPageShowTotNum_EX);
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWCL0140BMsg scrnMsg = (NWCL0140BMsg) bMsg;

        // page jump common settings
        String tblNm = (String) (((HttpDispatchContext) ctx.getDispatchContext()).getParameters().get("xxPagenationTableNm"))[0];
        if (TABLE_A.equals(tblNm)) {
            scrnMsg.xxScrEventNm.setValue(EVENT_NM_NWCL0140_PAGE_JUMP_EL);
        } else if (TABLE_B.equals(tblNm)) {
            scrnMsg.xxScrEventNm.setValue(EVENT_NM_NWCL0140_PAGE_JUMP_EX);
        }

        NWCL0140CMsg bizMsg = new NWCL0140CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWCL0140BMsg scrnMsg = (NWCL0140BMsg) bMsg;
        NWCL0140CMsg bizMsg = (NWCL0140CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWCL0140CommonLogic.setTableColumnAttr(scrnMsg);
    }
}
