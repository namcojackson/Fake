/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFDL0020.NFDL0020CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/14   Fujitsu         Y.Matsui        Create          QC#24809
 * 2018/05/16   Fujitsu         Y.Matsui        Update          QC#24329
 * 2018/06/04   Fujitsu         Y.Matsui        Update          QC#24809
 *</pre>
 */
public class NFDL0020Scrn00_Click_AcctBillTo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();

        if (selectIdx < 0) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.billToCustCd_H)) {
                scrnMsg.billToCustCd_H.setErrorInfo(1, "NFDM0002E", new String[] {"Bill To" });
                scrnMsg.addCheckItem(scrnMsg.billToCustCd_H);
                scrnMsg.putErrorScreen();
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        NFDL0020CMsg bizMsg = new NFDL0020CMsg();
        bizMsg.setBusinessID("NFDL0020");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        NFDL0020CMsg bizMsg  = (NFDL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.billToCustCd_H);
        scrnMsg.putErrorScreen();

        int selectIdx = getButtonSelectNumber();

        Object[] params = new Object[3];
        params[0] = "01"; // Mode Edit:01
        params[1] = scrnMsg.dsAcctNum_H;
        if (selectIdx < 0) {
            params[2] = scrnMsg.billToLocNum_H;
        } else {
            params[2] = scrnMsg.J.no(selectIdx).billToLocNum_J;
        }
        setArgForSubScreen(params);
    }
}
