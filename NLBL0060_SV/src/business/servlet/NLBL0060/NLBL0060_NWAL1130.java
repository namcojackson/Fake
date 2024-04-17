/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0060;

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
 * 2015/10/05   CUSA            Y.Aikawa        Create          N/A
 *</pre>
 */
public class NLBL0060_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLBL0060BMsg scrnMsg = (NLBL0060BMsg) bMsg;
        int index = getButtonSelectNumber();
        if (0 > index) {
            /** Header */
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
        } else {
            /** Line */
            // Set Return Value
            if (ZYPCommonFunc.hasValue(scrnMsg.Z.no(0).xxComnScrColValTxt)) {
                // Inventory Location Code
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).whCd_A1, scrnMsg.Z.no(0).xxComnScrColValTxt);
                // Inventory Location Name
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).locNm_A1, scrnMsg.Z.no(1).xxComnScrColValTxt);
            } else {
                // Inventory Location Code
                scrnMsg.A.no(index).whCd_A1.clear();
                // Inventory Location Name
                scrnMsg.A.no(index).locNm_A1.clear();
            }
            // Check item error
            scrnMsg.addCheckItem(scrnMsg.A.no(index).whCd_A1);
            scrnMsg.putErrorScreen();
            // Focus
            scrnMsg.setFocusItem(scrnMsg.A.no(index).whCd_A1);
        }       
    }
}
