/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLBL0110.NLBL0110CMsg;
//import business.servlet.NLBL0110.constant.NLBL0110Constant;

import business.servlet.NLBL0110.common.NLBL0110CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/24   Fujitsu         T.Tozuka        Create          R-OM025 Inventory Transaction
 *</pre>
 */
public class NLBL0110_NPAL1010 extends S21CommonHandler {

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
        NLBL0110CommonLogic.cntrlDispScrnItem(this, scrnMsg);

        // Set Return Value
        if (ZYPCommonFunc.hasValue(scrnMsg.invtyLocCd_LI)) {
            // Inventory Location Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.whCd_H2, scrnMsg.invtyLocCd_LI);
            // Inventory Location Name
            ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_H2, scrnMsg.invtyLocNm_LI);
        }

    }
}
