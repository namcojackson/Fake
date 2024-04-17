/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2170;

import static business.servlet.NWAL2170.constant.NWAL2170Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2170.NWAL2170CMsg;
import business.servlet.NWAL2170.NWAL2170BMsg;
import business.servlet.NWAL2170.common.NWAL2170CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2170Scrn00_OnBlur_DeriveFromDsAcctNm
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Fujitsu         M.Yamada        Create          N/A
 * 2017/05/30   Fujitsu         S.Ohki          Update          RS#8233
 *</pre>
 */
public class NWAL2170Scrn00_OnBlur_DeriveFromDsAcctNm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2170BMsg scrnMsg = (NWAL2170BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIndex);

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIndex).dsAcctNm)) {
            scrnMsg.addCheckItem(scrnMsg.A.no(selectIndex).dsAcctNm);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2170BMsg scrnMsg = (NWAL2170BMsg) bMsg;

        int selectIndex = getButtonSelectNumber();
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIndex).dsAcctNm)) {
            return null;
        }

        NWAL2170CMsg bizMsg = new NWAL2170CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2170BMsg scrnMsg = (NWAL2170BMsg) bMsg;
        setResult(ZYPConstant.FLG_OFF_N);

        int selectIndex = getButtonSelectNumber();
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIndex).dsAcctNm)) {
            scrnMsg.A.no(selectIndex).dsAcctNum.clear();
            scrnMsg.A.no(selectIndex).xxBillToAcctNmSrchTxt.clear();
            scrnMsg.A.no(selectIndex).soldToCustLocCd.clear();
            scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).dsAcctNum);
            return;
        }

        NWAL2170CMsg bizMsg = (NWAL2170CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.A.no(selectIndex).dsAcctNm);
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            scrnMsg.A.no(selectIndex).dsAcctNum.clear();
            scrnMsg.A.no(selectIndex).xxBillToAcctNmSrchTxt.clear();
            scrnMsg.A.no(selectIndex).soldToCustLocCd.clear();

            Object[] params = NWAL2170CommonLogic.getParamNMAL6760ForBillTo(scrnMsg);
            setArgForSubScreen(params);
            return;
        }

        scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).soldToCustLocCd);

    }
}
