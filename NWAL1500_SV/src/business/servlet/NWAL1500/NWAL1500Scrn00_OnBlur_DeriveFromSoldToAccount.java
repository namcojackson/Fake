/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.RESULT_PARAM_SPECIAL_INSTRUCTION;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForOrderEntryAction;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/14   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/09   Fujitsu         M.Mikio         Create          QC#1636
 * 2016/02/23   Fujitsu         Y.Taoka         Update          QC#1694
 * 2018/07/27   Fujitsu         T.Noguchi       Update          S21_NA#14307
 *</pre>
 */
public class NWAL1500Scrn00_OnBlur_DeriveFromSoldToAccount extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd)) {
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

        if (!ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd)) {
            // QC#1636
            scrnMsg.soldToCustAcctNm.clear();
            scrnMsg.soldToCustLocCd.clear();
            scrnMsg.xxAllLineAddr_SE.clear();
            scrnMsg.entSoldToCustLocAddr.clear();

            scrnMsg.setFocusItem(scrnMsg.soldToCustLocCd);
            return;
        }

        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.sellToCustCd);
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        // 2018/07/27 S21_NA#14307 Add Start
        if (NWAL1500CommonLogic.checkSpecialInstrctionData(scrnMsg)) {
            setResult(RESULT_PARAM_SPECIAL_INSTRUCTION);

            Object[] params = NWAL1500CommonLogicForOrderEntryAction.getArgForSubScreenSpecialInstruction(scrnMsg);
            setArgForSubScreen(params);
            return;
        }
        // 2018/07/27 S21_NA#14307 Add End

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

            Object[] params = NWAL1500CommonLogic.getParamNMAL6760ForSoldTo(scrnMsg);
            setArgForSubScreen(params);
            return;
        }

        scrnMsg.setFocusItem(scrnMsg.negoDealAmt);
    }
}
