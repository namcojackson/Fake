/**
 * <Pre>
 * 
 * Copyright (c) 2013 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.servlet.NLGL0050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLGL0050.NLGL0050CMsg;
import business.servlet.NLGL0050.common.NLGL0050CommonLogic;
import business.servlet.NLGL0050.constant.NLGL0050Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Ship Via Mapping from WMS to HOST
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/16   CSAI            Y.Miyauchi      Create          MW Replace Initial
 *</pre>
 */
public class NLGL0050Scrn00_CMN_Reset extends S21CommonHandler implements NLGL0050Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0050BMsg scrnMsg = (NLGL0050BMsg) bMsg;
        NLGL0050CMsg bizMsg = NLGL0050CommonLogic.setRequestData_NLGL0050Scrn00_Function_20();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0050BMsg scrnMsg = (NLGL0050BMsg) bMsg;
        NLGL0050CMsg bizMsg = (NLGL0050CMsg) cMsg;

        if (TAB_ID_LIST.equals(bizMsg.xxDplyTab.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_ID_LIST);
            NLGL0050CommonLogic.inputFieldControl_NLGL0050Scrn00_List(this, scrnMsg);
            NLGL0050CommonLogic.commonBtnControl_NLGL0050Scrn00_CMN_LIST(this);
            NLGL0050CommonLogic.busBtnControl_NLGL0050Scrn00_Prev_Disable(this);
        }
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
