/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/21   CUSA            Fujitsu         Create          R-OM025 Inventory Transaction
 *</pre>
 */
public class NLBL0040_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL0040BMsg scrnMsg = (NLBL0040BMsg) bMsg;

        
        // Set Return Value
        if (ZYPCommonFunc.hasValue(scrnMsg.invtyLocCd_P2)) {
            // Inventory Location Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.whCd_H2, scrnMsg.invtyLocCd_P2);
            // Inventory Location Name
            ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_H2, scrnMsg.invtyLocNm_P2);
        }

        // Check item error
        scrnMsg.addCheckItem(scrnMsg.whCd_H2);
        scrnMsg.putErrorScreen();

        // Focus
        scrnMsg.setFocusItem(scrnMsg.whCd_H2);
    }
}
