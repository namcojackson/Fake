/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL4500;
import static business.servlet.NMAL4500.constant.NMAL4500Constant.BIZ_ID;
import static business.servlet.NMAL4500.constant.NMAL4500Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL4500.NMAL4500CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   CITS            S.Endo          Create          QC#10840
 *</pre>
 */
public class NMAL4500Scrn00_GetAddress extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.postCd_01);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;

        NMAL4500CMsg bizMsg = new NMAL4500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;
        NMAL4500CMsg bizMsg  = (NMAL4500CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.postCd_01);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr_01);
        scrnMsg.addCheckItem(scrnMsg.stCd_01);
        scrnMsg.addCheckItem(scrnMsg.cntyPk_03);
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.postCd_01);
    }
}
