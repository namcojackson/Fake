/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLBL0040.NLBL0040CMsg;
//import business.servlet.NLBL0040.constant.NLBL0040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   CUSA            Y.Aikawa        Create          N/A
 *</pre>
 */
public class NLBL0040_NWAL1130 extends S21CommonHandler {

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
        if (ZYPCommonFunc.hasValue(scrnMsg.Z.no(0).xxComnScrColValTxt)) {
            // Inventory Location Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.whCd_H2, scrnMsg.Z.no(0).xxComnScrColValTxt);
            // Inventory Location Name
            ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_H2, scrnMsg.Z.no(1).xxComnScrColValTxt);
        } else {
            // Inventory Location Code
            scrnMsg.whCd_H2.clear();
            // Inventory Location Name
            scrnMsg.locNm_H2.clear();
        }
        // Check item error
        scrnMsg.addCheckItem(scrnMsg.whCd_H2);
        scrnMsg.putErrorScreen();
        // Focus
        scrnMsg.setFocusItem(scrnMsg.whCd_H2);
    }
}
