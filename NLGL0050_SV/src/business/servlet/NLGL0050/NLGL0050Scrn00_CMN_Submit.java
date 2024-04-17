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
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLGL0050.NLGL0050CMsg;
import business.servlet.NLGL0050.common.NLGL0050CommonLogic;
import business.servlet.NLGL0050.constant.NLGL0050Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
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
public class NLGL0050Scrn00_CMN_Submit extends S21CommonHandler implements NLGL0050Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0050BMsg scrnMsg = (NLGL0050BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.wmsCarrCd_T2);
        scrnMsg.addCheckItem(scrnMsg.wmsDescTxt_T2);
        scrnMsg.addCheckItem(scrnMsg.wmsCarrNm_T2);
        scrnMsg.addCheckItem(scrnMsg.carrSvcTxt_T2);
        scrnMsg.addCheckItem(scrnMsg.carrScacCd_T2);
        scrnMsg.addCheckItem(scrnMsg.fuelUpchgTpCd_P2);
        scrnMsg.addCheckItem(scrnMsg.fuelUpchgAmt_T2);
        scrnMsg.addCheckItem(scrnMsg.addlUpchgTpCd_P2);
        scrnMsg.addCheckItem(scrnMsg.addlUpchgAmt_T2);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0050BMsg scrnMsg = (NLGL0050BMsg) bMsg;
        NLGL0050CMsg bizMsg = NLGL0050CommonLogic.setRequestData_NLGL0050Scrn00_Function_40();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0050BMsg scrnMsg = (NLGL0050BMsg) bMsg;
        NLGL0050CMsg bizMsg = (NLGL0050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (ZYPCommonFunc.hasValue(scrnMsg.getMessageCode()) && scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            if (NLGM0050E.equals(scrnMsg.getMessageCode())) {
                scrnMsg.addCheckItem(scrnMsg.wmsCarrCd_T2);
                scrnMsg.putErrorScreen();
            }
            return;
        }

        NLGL0050CommonLogic.inputFieldControl_NLGL0050Scrn00_Edit(this, scrnMsg);
        NLGL0050CommonLogic.commonBtnControl_SUBMIT(this);
    }
}
