/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/24   Fujitsu         T.Yoshida       Create          N/A
 * </pre>
 */
public class NWAL1500Scrn00_OnBlur_DeriveFromAssnProgramName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.dsOrdTpDescTxt);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.prcContrNm)) {
            return null;
        }

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        setResult(ZYPConstant.FLG_OFF_N);

        if (!ZYPCommonFunc.hasValue(scrnMsg.prcContrNm)) {
            scrnMsg.prcContrNum.clear();
            scrnMsg.setFocusItem(scrnMsg.prcContrNum);
            return;
        }

        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.dsOrdTpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.prcContrNm);
        scrnMsg.putErrorScreen();

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = NWAL1500CommonLogic.getParamNWAL1130ForAssnProgram(scrnMsg);
            setArgForSubScreen(params);
            return;
        }

        scrnMsg.setFocusItem(scrnMsg.flPrcListNm);
    }
}