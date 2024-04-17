/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1320;

import static business.servlet.NSAL1320.constant.NSAL1320Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1320.NSAL1320CMsg;
import business.servlet.NSAL1320.NSAL1320BMsg;
import business.servlet.NSAL1320.common.NSAL1320CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1320Scrn00_OnBlur_DeriveFromDsAcctNum
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1320Scrn00_OnBlur_DeriveFromDsAcctNum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIndex);

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIndex).dsAcctNum)) {
            scrnMsg.addCheckItem(scrnMsg.A.no(selectIndex).dsAcctNum);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;

        int selectIndex = getButtonSelectNumber();
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIndex).dsAcctNum)) {
            return null;
        }

        NSAL1320CMsg bizMsg = new NSAL1320CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        setResult(ZYPConstant.FLG_OFF_N);

        int selectIndex = getButtonSelectNumber();

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIndex).dsAcctNum)) {
            scrnMsg.A.no(selectIndex).dsAcctNm.clear();
            scrnMsg.A.no(selectIndex).xxGenlFldAreaTxt_BI.clear();
            scrnMsg.A.no(selectIndex).soldToCustLocCd.clear();
            scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).dsAcctNm);
            return;
        }

        NSAL1320CMsg bizMsg = (NSAL1320CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.A.no(selectIndex).dsAcctNum);
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            scrnMsg.A.no(selectIndex).dsAcctNm.clear();
            scrnMsg.A.no(selectIndex).xxGenlFldAreaTxt_BI.clear();
            scrnMsg.A.no(selectIndex).soldToCustLocCd.clear();

            Object[] params = NSAL1320CommonLogic.getParamNMAL6760ForBillTo(scrnMsg);
            setArgForSubScreen(params);
            return;
        }

        scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).soldToCustLocCd);

    }
}
