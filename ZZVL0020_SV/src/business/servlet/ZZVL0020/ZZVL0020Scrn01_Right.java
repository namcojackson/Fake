/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZVL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZVL0020.ZZVL0020CMsg;
import business.servlet.ZZVL0020.common.ZZVL0020CommonLogic;
import business.servlet.ZZVL0020.constant.ZZVL0020Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/26   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZVL0020Scrn01_Right extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //ZZVL0020BMsg scrnMsg = (ZZVL0020BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZVL0020BMsg scrnMsg = (ZZVL0020BMsg) bMsg;

        ZZVL0020CMsg bizMsg = new ZZVL0020CMsg();
        bizMsg.setBusinessID("ZZVL0020");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

//        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZVL0020BMsg scrnMsg = (ZZVL0020BMsg) bMsg;
        ZZVL0020CMsg bizMsg  = (ZZVL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21SortTarget sortTarget = new S21SortTarget(scrnMsg.B, scrnMsg.B.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add("menuProcNm_B1", S21SortKey.ASC);
        sortKey.add("upTabNm_B1", S21SortKey.ASC);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, scrnMsg.B.getValidCount());

        ZZVL0020CommonLogic.dspScrn01(scrnMsg, this);

        // Error
        scrnMsg.putErrorScreen();

        S21SortColumnIMGController.clearIMG(ZZVL0020Constant.SCREEN_NAME1, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG(ZZVL0020Constant.SCREEN_NAME1, scrnMsg, scrnMsg.B.no(0).getBaseContents());

    }
}