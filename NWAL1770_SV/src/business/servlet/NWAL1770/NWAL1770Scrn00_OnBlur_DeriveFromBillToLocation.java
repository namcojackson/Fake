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
 * 2016/01/12   Fujitsu         T.Yoshida       Create          N/A
 * 2017/02/21   Fujitsu         Y.Kanefusa      Update          S21_NA#17474
 * 2018/03/09   Fujitsu         A.Kosai         Update          S21_NA#22387
 * 2018/06/21   Fujitsu         T.Noguchi       Update          S21_NA#14307
 *</pre>
 */
public class NWAL1770Scrn00_OnBlur_DeriveFromBillToLocation extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.billToCustCd)) {
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

        if (!ZYPCommonFunc.hasValue(scrnMsg.billToCustCd)) {
            scrnMsg.xxAllLineAddr_BT.clear();
            // 2018/03/09 S21_NA#22387 Add Start
            scrnMsg.billToLocNum.clear();
            // 2018/03/09 S21_NA#22387 Add End
            scrnMsg.setFocusItem(scrnMsg.shipToCustAcctNm);
            return;
        }

        NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.billToCustCd);
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        NWAL1770CommonLogicForScrnFields.setProtectByBillTo(scrnMsg); // QC#17474 2017/02/21 Add

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

            Object[] params = NWAL1770CommonLogic.getParamNMAL6760ForBillTo(scrnMsg);
            setArgForSubScreen(params);
            return;
        }

        scrnMsg.setFocusItem(scrnMsg.shipToCustAcctNm);
    }
}
