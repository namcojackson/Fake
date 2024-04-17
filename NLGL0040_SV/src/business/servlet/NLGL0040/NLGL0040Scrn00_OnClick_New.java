/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLGL0040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLGL0040.NLGL0040CMsg;
import business.servlet.NLGL0040.common.NLGL0040CommonLogic;
import business.servlet.NLGL0040.constant.NLGL0040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Ship Via Mapping from HOST to WMS
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 08/12/2013     CSAI            N.Sekine          Create             MW Replace Initial
 *</pre>
 */
public class NLGL0040Scrn00_OnClick_New extends S21CommonHandler implements NLGL0040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // there is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0040BMsg scrnMsg = (NLGL0040BMsg) bMsg;
        NLGL0040CMsg bizMsg = NLGL0040CommonLogic.setRequestData_NLGL0040Scrn00_Function_20();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0040BMsg scrnMsg = (NLGL0040BMsg) bMsg;
        NLGL0040CMsg bizMsg = (NLGL0040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLGL0040CommonLogic.commonBtnControl_NLGL0040Scrn00_CMN_ClearforNew(this, scrnMsg);
        NLGL0040CommonLogic.inputFieldControl_NLGL0040Scrn00_New(this, scrnMsg);
        NLGL0040CommonLogic.commonBtnControl_NLGL0040Scrn00_CMN_NEW(this);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_ID_EDIT);
    }
}
