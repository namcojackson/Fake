/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0180;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0180.common.NLCL0180CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/22   Fujitsu         T.Tozuka        Create          R-OM025 Inventory Transaction *</pre>
 */
public class NLCL0180_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0180BMsg scrnMsg = (NLCL0180BMsg) bMsg;

        NLCL0180CommonLogic.initialize(this, scrnMsg);
        NLCL0180CommonLogic.commonBtnControl_NLCL0180_NPAL1010(this);
        NLCL0180CommonLogic.scrnItemControl_NLCL0180_NPAL1010(scrnMsg);

        // Set Return Value
        if (ZYPCommonFunc.hasValue(scrnMsg.invtyLocCd_LI)) {
            // Inventory Location Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.whCd_P1, scrnMsg.invtyLocCd_LI);
            // Inventory Location Name
            ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_P1, scrnMsg.invtyLocNm_LI);
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mdseNm_A1.setInputProtected(true);
        }
    }
}
