/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3000.NFCL3000CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/29   Fujitsu         T.Tanaka        Create          N/A
 * 2016/07/14   Fujitsu         S.Fujita        Update          QC#11157
 *</pre>
 */
public class NFCL3000Scrn00_SalesRepName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        // START 2016/07/14 S.Fujita [QC#11157,MOD] 
        if(!ZYPCommonFunc.hasValue(scrnMsg.psnNum_H1)) {
        // END   2016/07/14 S.Fujita [QC#11157,MOD]
            scrnMsg.slsRepTocCd_H1.clear();
            scrnMsg.slsRepTocNm_H1.clear();
            scrnMsg.coaBrNm_H1.clear();
            return;
        }
        scrnMsg.addCheckItem(scrnMsg.psnNum_H1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        if(!ZYPCommonFunc.hasValue(scrnMsg.psnNum_H1)) {
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

        if(!ZYPCommonFunc.hasValue(scrnMsg.psnNum_H1)) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // START 2016/07/14 S.Fujita [QC#11157,MOD] 
//        scrnMsg.addCheckItem(scrnMsg.slsRepTocCd_H1);
        scrnMsg.addCheckItem(scrnMsg.psnNum_H1);
        // END   2016/07/14 S.Fujita [QC#11157,MOD]
        scrnMsg.putErrorScreen();

    }
}
