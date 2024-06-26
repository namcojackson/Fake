/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLGL0020.NLGL0020CMsg;
import business.servlet.NLGL0020.common.NLGL0020CommonLogic;
import business.servlet.NLGL0020.constant.NLGL0020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * PO Maintenance
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 02/11/2014     CSAI            M.shimamura       Create             MW Replace Initial
 *</pre>
 */
public class NLGL0020Scrn00_OnClick_UPD_UncheckAll extends S21CommonHandler implements NLGL0020Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) bMsg;
        NLGL0020CMsg bizMsg = NLGL0020CommonLogic.setRequestData_NLGL0020Scrn00_Function_20();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) bMsg;
        NLGL0020CMsg bizMsg = (NLGL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_ID_UPD);
        NLGL0020CommonLogic.commonBtnControl_NLGL0020Scrn00_UPD_TAB(this);
    }
}
