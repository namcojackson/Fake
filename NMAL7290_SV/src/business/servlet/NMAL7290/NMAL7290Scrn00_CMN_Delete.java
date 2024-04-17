/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7290;

import static business.servlet.NMAL7290.constant.NMAL7290Constant.TBL_B1;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL7290.NMAL7290CMsg;
//import business.servlet.NMAL7290.constant.NMAL7290Constant;

import business.blap.NMAL7290.NMAL7290CMsg;
import business.servlet.NMAL7290.common.NMAL7290CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/08   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 *</pre>
 */
public class NMAL7290Scrn00_CMN_Delete extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7290BMsg scrnMsg = (NMAL7290BMsg) bMsg;

        NMAL7290CMsg bizMsg = new NMAL7290CMsg();
        bizMsg.setBusinessID("NMAL7290");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL7290BMsg scrnMsg = (NMAL7290BMsg) bMsg;
        NMAL7290CMsg bizMsg = (NMAL7290CMsg) cMsg;

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (ZYPConstant.FLG_ON_1.equals(scrnMsg.xxWrnSkipFlg.getValue())) {
            return;
        }

        NMAL7290CommonLogic.initCmnBtnProp(this, scrnMsg);
        NMAL7290CommonLogic.ctrlCmnBtnProp(this, getUserProfileService(), scrnMsg);
        NMAL7290CommonLogic.ctrlBtnProp(this, scrnMsg);
        NMAL7290CommonLogic.ctrlInputProp(scrnMsg);
        NMAL7290CommonLogic.setTblColor(scrnMsg, TBL_B1);
    }
}
