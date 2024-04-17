/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.BIZ_ID;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_RENTAL_EQUIP_COVERED_ITEM;
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
 * NSAL1330Scrn00_OnBlur_CoveredItem_RentalEquip
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330Scrn00_OnBlur_CoveredItem_RentalEquip extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxNum_B.setValue(selectIndex);

        scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).mdseCd_B);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).mdseCd_B)) {
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
        if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).mdseCd_B)) {
            scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).mdseCd_B);
            scrnMsg.B.no(selectIndex).mdseDescShortTxt_B.clear();
            return;
        }

        NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).mdseCd_B);
        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            scrnMsg.xxNum_B.setValue(getButtonSelectNumber());
            Object[] params //
            = NSAL1330CommonLogic.getCoveredItemPopUpPrm(scrnMsg, getButtonSelectNumber(), POP_UP_RENTAL_EQUIP_COVERED_ITEM, scrnMsg.B.no(selectIndex).mdseCd_B.getValue());
            setArgForSubScreen(params);
        }

    }
}
