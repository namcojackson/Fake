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
 * 2016/02/24   Fujitsu         T.Tanaka        Create          N/A
 * 2016/08/01   CUSA            Fujitsu         Update          QC#10148
 *</pre>
 */
public class NFCL3000Scrn00_ShipToName_D extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        int idx = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(idx);

        if(!ZYPCommonFunc.hasValue(scrnMsg.D.no(idx).shipToCustAcctCd_D1)) {
            scrnMsg.D.no(idx).shipToCustAcctNm_D1.clear();
            return;
        }

        scrnMsg.addCheckItem(scrnMsg.D.no(idx).shipToCustAcctCd_D1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        int idx = scrnMsg.xxCellIdx.getValueInt();
        if(!ZYPCommonFunc.hasValue(scrnMsg.D.no(idx).shipToCustAcctCd_D1)) {
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

        int idx = scrnMsg.xxCellIdx.getValueInt();
        if(!ZYPCommonFunc.hasValue(scrnMsg.D.no(idx).shipToCustAcctCd_D1)) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2016/08/01 S.Fujita [QC#10148,MOD]
//        scrnMsg.addCheckItem(scrnMsg.D.no(no).shipToCustCd_D1);
        scrnMsg.addCheckItem(scrnMsg.D.no(idx).shipToCustAcctCd_D1);
        // END   2016/08/01 S.Fujita [QC#10148,MOD]
        scrnMsg.putErrorScreen();
    }
}
