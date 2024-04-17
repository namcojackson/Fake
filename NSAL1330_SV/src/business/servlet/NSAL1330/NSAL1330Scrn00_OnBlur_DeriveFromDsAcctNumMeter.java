/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1330.NSAL1330CMsg;
import business.servlet.NSAL1330.common.NSAL1330CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1330Scrn00_OnBlur_DeriveFromDsAcctNumMeter
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330Scrn00_OnBlur_DeriveFromDsAcctNumMeter extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxNum_Z.setValue(selectIndex);

        if (ZYPCommonFunc.hasValue(scrnMsg.U.no(selectIndex).billToCustCd_U)) {
            scrnMsg.addCheckItem(scrnMsg.U.no(selectIndex).billToCustCd_U);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        int selectIndex = getButtonSelectNumber();
        if (!ZYPCommonFunc.hasValue(scrnMsg.U.no(selectIndex).billToCustCd_U)) {
            return null;
        }

        NSAL1330CMsg bizMsg = new NSAL1330CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        setResult(ZYPConstant.FLG_OFF_N);

        int selectIndex = getButtonSelectNumber();

        if (!ZYPCommonFunc.hasValue(scrnMsg.U.no(selectIndex).billToCustCd_U)) {
            scrnMsg.U.no(selectIndex).dsAcctNm_U.clear(); // 2017/03/29 S21_NA#18171 Mod
            scrnMsg.U.no(selectIndex).xxGenlFldAreaTxt_U.clear();
            scrnMsg.U.no(selectIndex).billToLocNum_U.clear();
            scrnMsg.setFocusItem(scrnMsg.U.no(selectIndex).billToCustCd_U);
            return;
        }

        NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.U.no(selectIndex).billToCustCd_U);
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            scrnMsg.U.no(selectIndex).dsAcctNm_U.clear(); // 2017/03/29 S21_NA#18171 Mod
            scrnMsg.U.no(selectIndex).xxGenlFldAreaTxt_U.clear();
            scrnMsg.U.no(selectIndex).billToLocNum_U.clear();

            Object[] params = NSAL1330CommonLogic.getParamNMAL6760ForBillTo(scrnMsg, false);
            setArgForSubScreen(params);
            return;
        }

        scrnMsg.setFocusItem(scrnMsg.U.no(selectIndex).billToCustCd_U);

    }
}
