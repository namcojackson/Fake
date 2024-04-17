/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0410;

import static business.servlet.NSBL0410.constant.NSBL0410Constant.*;
import static business.servlet.NSBL0410.common.NSBL0410CommonLogic.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0410.NSBL0410CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Hitachi         M.Gotou         Create          N/A
 * 2016/02/26   Hitachi         M.Gotou         Update          QC#4719
 * 2016/03/01   Hitachi         M.Gotou         Update          QC#4724
 * 2016/07/13   Hitachi         O.Okuma         Update          QC#11685
 * 2018/05/31   Hitachi         U.Kim           Update          QC#22393
 *</pre>
 */
public class NSBL0410Scrn00_Add extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0410BMsg scrnMsg = (NSBL0410BMsg) bMsg;
        // START 2018/05/31 U.Kim [QC#22393, MOD]
        // addCheckItem_Add(scrnMsg);
        inputCheckForAdd(scrnMsg);
        // END 2018/05/31 U.Kim [QC#22393, MOD]
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0410BMsg scrnMsg = (NSBL0410BMsg) bMsg;

        NSBL0410CMsg bizMsg = new NSBL0410CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0410BMsg scrnMsg = (NSBL0410BMsg) bMsg;
        NSBL0410CMsg bizMsg  = (NSBL0410CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        initialControlScreen_Add(this, scrnMsg);
        addCheckItem_Add(scrnMsg);
        scrnMsg.putErrorScreen();
        // START 2018/06/04 U.Kim [QC#22393, ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseCd)) {
        // END 2018/06/04 U.Kim [QC#22393, ADD]
            if (scrnMsg.A.getValidCount() > 0) {
                scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).svcMnfCd_A);
            } else {
                scrnMsg.setFocusItem(scrnMsg.mdseCd);
            }
        // START 2018/06/04 U.Kim [QC#22393, ADD]
        } else {
            if (scrnMsg.A.getValidCount() > 0) {
                scrnMsg.setFocusItem(scrnMsg.A.no(0).svcMnfCd_A);
            } else {
                scrnMsg.setFocusItem(scrnMsg.mdseCd);
            }
        }
        // END 2018/06/04 U.Kim [QC#22393, ADD]
    }
}
