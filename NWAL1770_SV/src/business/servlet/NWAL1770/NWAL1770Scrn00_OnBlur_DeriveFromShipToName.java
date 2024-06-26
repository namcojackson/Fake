/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.RESULT_PARAM_SPECIAL_INSTRUCTION;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.servlet.NWAL1770.common.NWAL1770CommonLogic;
import business.servlet.NWAL1770.common.NWAL1770CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/15   Fujitsu         T.Yoshida       Create          N/A
 * 2017/09/05   Fujitsu         H.Sugawara      Update          QC#20923
 * 2018/03/09   Fujitsu         A.Kosai         Update          S21_NA#22387
 * 2018/06/21   Fujitsu         T.Noguchi       Update          S21_NA#14307
 *</pre>
 */
public class NWAL1770Scrn00_OnBlur_DeriveFromShipToName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctNm)) {
            return null;
        }

        NWAL1770CMsg bizMsg = new NWAL1770CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        setResult(ZYPConstant.FLG_OFF_N);

        if (!ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctNm)) {
            scrnMsg.shipToCustAcctCd.clear();
            scrnMsg.shipToCustCd.clear();
            scrnMsg.xxAllLineAddr_SH.clear();
            // 2018/03/09 S21_NA#22387 Add Start
            scrnMsg.shipToLocNum.clear();
            // 2018/03/09 S21_NA#22387 Add End
            scrnMsg.setFocusItem(scrnMsg.shipToCustAcctCd);
            return;
        }

        NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.shipToCustAcctNm);
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        // Mod Start 2017/09/05 QC#20923
        //NWAL1770CommonLogicForScrnFields.setProtectByBillTo(scrnMsg); // QC#17474 2017/02/21 Add
        NWAL1770CommonLogicForScrnFields.setProtect(this, scrnMsg);
        // Mod End 2017/09/05 QC#20923

        // 2018/06/21 QC#14307 Add Start
        if (NWAL1770CommonLogic.checkSpecialInstrctionData(scrnMsg)) {
            setResult(RESULT_PARAM_SPECIAL_INSTRUCTION);

            Object[] params = NWAL1770CommonLogic.getParamNMAL3300ForOnBlur(scrnMsg);
            setArgForSubScreen(params);
            return;
        }
        // 2018/06/21 QC#14307 Add End

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

            Object[] params = NWAL1770CommonLogic.getParamNMAL6760ForShipTo(scrnMsg);
            setArgForSubScreen(params);
            return;
        }

        scrnMsg.setFocusItem(scrnMsg.soldToCustAcctNm);
    }
}
