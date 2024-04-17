/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860;

import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.getParamNWAL1130ForSupplierType;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.LINK_OPENWIN_SUPPLIER_TYPE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/02/28   Fujitsu         C.Hara          Create          QC#55971
 *</pre>
 */

public class NMAL6860Scrn00_OpenWin_SupplierType extends S21CommonHandler {
       @Override
        protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        }

        @Override
        protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
            return null;
        }

        @Override
        protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg,EZDCMsg cMsg) {
            NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;

            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_AD, LINK_OPENWIN_SUPPLIER_TYPE);
            Object[] params = getParamNWAL1130ForSupplierType(scrnMsg);

            setArgForSubScreen(params);
        }

}
