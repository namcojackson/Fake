/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL3300.NMAL3300CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2018/11/12   Fujitsu         Hd.Sugawara     Update          QC#28683
 *</pre>
 */
public class NMAL3300Scrn00_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3300BMsg scrnMsg = (NMAL3300BMsg) bMsg;

        // Del Start 2018/11/12 QC#28683
        //String tblNm = (String) (((HttpDispatchContext) ctx.getDispatchContext()).getParameters().get("xxPagenationTableNm"))[0];
        //if (tblNm.equals("A")) {
        //    scrnMsg.xxModeInd.setValue("A");
        //} else if (tblNm.equals("B")){
        //    scrnMsg.xxModeInd.setValue("B");
        //} else if (tblNm.equals("C")){
        //    scrnMsg.xxModeInd.setValue("C");
        //} else if (tblNm.equals("D")){
        //    scrnMsg.xxModeInd.setValue("D");
        //}
        // Del End 2018/11/12 QC#28683

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3300BMsg scrnMsg = (NMAL3300BMsg) bMsg;

        NMAL3300CMsg bizMsg = new NMAL3300CMsg();
        bizMsg.setBusinessID("NMAL3300");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL3300BMsg scrnMsg = (NMAL3300BMsg) bMsg;
        NMAL3300CMsg bizMsg = (NMAL3300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.putErrorScreen();
//        NMAL3300CommonLogic.controlDetailScreenFields(this, scrnMsg);

    }
}
