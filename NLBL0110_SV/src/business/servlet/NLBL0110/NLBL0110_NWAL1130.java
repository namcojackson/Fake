/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLBL0110.NLBL0110CMsg;
//import business.servlet.NLBL0110.constant.NLBL0110Constant;

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
public class NLBL0110_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLBL0110BMsg scrnMsg = (NLBL0110BMsg) bMsg;
        //NLBL0110CMsg bizMsg  = (NLBL0110CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

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
