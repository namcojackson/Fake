/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3000.NFCL3000CMsg;
import business.servlet.NFCL3000.common.NFCL3000CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/24   Fujitsu         T.Tanaka        Create          N/A
 * 2016/08/05   Fujitsu         S.Yoshida       Update          QC#12862
 *</pre>
 */
public class NFCL3000Scrn00_WarehouseName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        if(!ZYPCommonFunc.hasValue(scrnMsg.shipFromInvtyLocCd_H4)) {
            scrnMsg.rtlWhNm_H4.clear();
            return;
        }

        scrnMsg.addCheckItem(scrnMsg.shipFromInvtyLocCd_H4);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        if(!ZYPCommonFunc.hasValue(scrnMsg.shipFromInvtyLocCd_H4)) {
            return null;
        }

        NFCL3000CMsg bizMsg = new NFCL3000CMsg();
        bizMsg.setBusinessID("NFCL3000");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        NFCL3000CMsg bizMsg  = (NFCL3000CMsg) cMsg;

        if(!ZYPCommonFunc.hasValue(scrnMsg.shipFromInvtyLocCd_H4)) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL3000CommonLogic.addCheckItem_AddLine(scrnMsg);
        scrnMsg.putErrorScreen();
    }
}
