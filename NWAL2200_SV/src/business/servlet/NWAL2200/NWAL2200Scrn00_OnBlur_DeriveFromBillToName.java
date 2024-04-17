/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
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
 * NWAL2200Scrn00_OnBlur_DeriveFromBillToName
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2018/07/25   Fujitsu         Mz.Takahashi    Update          S21_NA#14307
 *</pre>
 */
public class NWAL2200Scrn00_OnBlur_DeriveFromBillToName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, false, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();

        if (ZYPCommonFunc.hasValue(scrnMsg.billToCustAcctNm)) {
            scrnMsg.addCheckItem(scrnMsg.billToCustAcctNm);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.billToCustAcctNm)) {
            // Add Start 2018/07/27 S21_NA#14307
            scrnMsg.billToCustAcctCd.clear();
            scrnMsg.billToCustCd.clear();
            scrnMsg.xxAllLineAddr_BT.clear();
            // Add End 2018/07/27 S21_NA#14307
            return null;
        }

        NWAL2200CMsg bizMsg = new NWAL2200CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        setResult(ZYPConstant.FLG_OFF_N);

        if (!ZYPCommonFunc.hasValue(scrnMsg.billToCustAcctNm)) {
            scrnMsg.setFocusItem(scrnMsg.billToCustAcctCd);
            return;
        }

        NWAL2200CMsg bizMsg = (NWAL2200CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.billToCustAcctNm);
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            scrnMsg.xxCellIdx.setValue(-1);
            scrnMsg.billToCustAcctCd.clear();
            scrnMsg.billToCustCd.clear();

            Object[] params = NWAL2200CommonLogic.getParamNMAL6760ForBillTo(scrnMsg);
            setArgForSubScreen(params);
            return;

        // Add Start 2018/07/25 S21_NA#14307
        } else if (ZYPConstant.FLG_ON_1.equals(scrnMsg.xxRqstFlg.getValue())){
            setResult(ZYPConstant.FLG_ON_1);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            setArgForSubScreen(NWAL2200CommonLogic.getArgForSubScreen(scrnMsg, this.getGlobalCompanyCode()));
            return;
        }
        // Add End 2018/07/25 S21_NA#14307

        scrnMsg.setFocusItem(scrnMsg.shipToCustAcctNm);
    }
}