/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL2200.NWAL2200CMsg;
import business.servlet.NWAL2200.common.NWAL2200CommonLogic;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/02/24   Fujitsu         Y.Kanefusa      Create          S21_NA#13688
 *</pre>
 */
public class NWAL2200Scrn00_OnBlur_DeriveFromFreightTerms extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.frtCondDescTxt)) {
            return null;
        }

        NWAL2200CMsg bizMsg = new NWAL2200CMsg();
        bizMsg.setBusinessID("NWAL2200");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        setResult(ZYPConstant.FLG_OFF_N);

        if (!ZYPCommonFunc.hasValue(scrnMsg.frtCondDescTxt)) {
            scrnMsg.setFocusItem(scrnMsg.shpgSvcLvlCd);
            return;
        }
        NWAL2200CMsg bizMsg  = (NWAL2200CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.frtCondDescTxt);
        scrnMsg.putErrorScreen();

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = NWAL2200CommonLogic.getParamNMAL6050ForFrtTerm(scrnMsg);
            setArgForSubScreen(params);
            return;
        }
        NWAL2200CommonLogicForScreenFields.setConditionForAddlTab(this, scrnMsg);

    }
}
