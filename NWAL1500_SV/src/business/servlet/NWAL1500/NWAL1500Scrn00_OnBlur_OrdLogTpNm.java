/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/15   Fujitsu         M.Hara          Create          QC#1692
 * 2016/05/18   Fujitsu         T.Murai         Update          QC#5337
 *</pre>
 */
public class NWAL1500Scrn00_OnBlur_OrdLogTpNm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        // S21_NA#5337 Mod Start
        // if (ZYPCommonFunc.hasValue(scrnMsg.ordLogTpDescTxt_NM)) {
        // scrnMsg.addCheckItem(scrnMsg.ordLogTpDescTxt_NM);
        // scrnMsg.putErrorScreen();
        // }
        if (ZYPCommonFunc.hasValue(scrnMsg.ordLogTpCd)) {
            scrnMsg.addCheckItem(scrnMsg.ordLogTpCd);
            scrnMsg.putErrorScreen();
        }
        // S21_NA#5337 Mod End

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        // S21_NA#5337 Mod Start
        // if (!ZYPCommonFunc.hasValue(scrnMsg.ordLogTpDescTxt_NM)) {
        // return null;
        // }
        if (!ZYPCommonFunc.hasValue(scrnMsg.ordLogTpCd)) {
            return null;
        }
        // S21_NA#5337 Mod End

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

        // S21_NA#5337 Mod Start
        // if (!ZYPCommonFunc.hasValue(scrnMsg.ordLogTpDescTxt_NM)) {
        // scrnMsg.ordLogTpCd.clear();
        // scrnMsg.setFocusItem(scrnMsg.invCmntTxt);
        // return;
        // }
        if (!ZYPCommonFunc.hasValue(scrnMsg.ordLogTpCd)) {
            scrnMsg.ordLogTpDescTxt_NM.clear();
            scrnMsg.setFocusItem(scrnMsg.invCmntTxt);
            return;
        }
        // S21_NA#5337 Mod End

        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.ordLogTpCd); // S21_NA#5337 Mod
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = NWAL1500CommonLogic.getParamNWAL1130ForLogType(scrnMsg, false);
            setArgForSubScreen(params);
            return;
        }

        scrnMsg.setFocusItem(scrnMsg.invCmntTxt);
    }
}
