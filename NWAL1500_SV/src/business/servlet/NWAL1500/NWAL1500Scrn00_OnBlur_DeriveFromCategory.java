/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.RESULT_PARAM_SPECIAL_INSTRUCTION;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForOrderEntryAction;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         T.Yoshida       Create          N/A        QC#1694
 * 2018/07/11   Fujitsu         T.Noguchi       Update          S21_NA#26713
 * 2018/07/27   Fujitsu         T.Noguchi       Update          S21_NA#14307
 *</pre>
 */
public class NWAL1500Scrn00_OnBlur_DeriveFromCategory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgDescTxt);
        if (scrnMsg.dsOrdCatgDescTxt.isError()) {
            scrnMsg.dsOrdTpDescTxt.clear();
        }

        // 2018/07/11 S21_NA#26713 Add Start
        scrnMsg.dsOrdCatgCd.clear();
        // 2018/07/11 S21_NA#26713 Add End

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        setResult(ZYPConstant.FLG_OFF_N);

        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgDescTxt);
        scrnMsg.putErrorScreen();

        // 2018/07/27 S21_NA#14307 Add Start
        if (NWAL1500CommonLogic.checkSpecialInstrctionData(scrnMsg)) {
            setResult(RESULT_PARAM_SPECIAL_INSTRUCTION);
            NWAL1500CommonLogicForScrnFields.setProtectByOrdCatgBizCtx(this, scrnMsg);

            Object[] params = NWAL1500CommonLogicForOrderEntryAction.getArgForSubScreenSpecialInstruction(scrnMsg);
            setArgForSubScreen(params);
            return;
        }
        // 2018/07/27 S21_NA#14307 Add End

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = NWAL1500CommonLogic.getParamNWAL1130ForOrdCatg(scrnMsg);
            setArgForSubScreen(params);
            return;
        }

        NWAL1500CommonLogicForScrnFields.setProtectByOrdCatgBizCtx(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.dsOrdTpDescTxt);
    }
}
