/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;
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
 * NWAL2200Scrn00_OnBlur_ChangeWH
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 *</pre>
 */
public class NWAL2200Scrn00_OnBlur_ChangeWH extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, false, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();

        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIndex);

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).rtlWhNm_LL)) {
                scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).rtlWhNm_LL);
            }
        } else {
            if (ZYPCommonFunc.hasValue(scrnMsg.D.no(selectIndex).rtlWhNm_RL)) {
                scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).rtlWhNm_RL);
            }
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.B.no(selectIndex).rtlWhCd_LL.clear();
            scrnMsg.B.no(selectIndex).rtlSwhCd_LL.clear();
            scrnMsg.B.no(selectIndex).rtlSwhNm_LL.clear();
            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).rtlWhNm_LL)) {
                return null;
            }
        } else {
            scrnMsg.D.no(selectIndex).rtlWhCd_RL.clear();
            scrnMsg.D.no(selectIndex).rtlSwhCd_RL.clear();
            scrnMsg.D.no(selectIndex).rtlSwhNm_RL.clear();
            // scrnMsg.D.no(selectIndex).thirdPtyDspTpCd_RL.clear();
            if (!ZYPCommonFunc.hasValue(scrnMsg.D.no(selectIndex).rtlWhNm_RL)) {
                return null;
            }
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
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).rtlWhNm_LL)) {
                scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).rtlSwhNm_LL);
                return;
            }
        } else {
            if (!ZYPCommonFunc.hasValue(scrnMsg.D.no(selectIndex).rtlWhNm_RL)) {
                scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).rtlSwhNm_RL);
                return;
            }
        }

        NWAL2200CMsg bizMsg = (NWAL2200CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).rtlWhNm_LL);
        } else {
            scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).rtlWhNm_RL);
        }
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = NWAL2200CommonLogic.getParamNWAL1130ForWh(scrnMsg);
            setArgForSubScreen(params);
            return;
        }

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).rtlSwhNm_LL);
        } else {
            scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).rtlSwhNm_RL);
        }
    }
}
