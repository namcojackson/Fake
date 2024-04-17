/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   Fujitsu         T.Yoshida       Create          N/A
 * </pre>
 */
public class NWAL1500Scrn00_OnBlur_DeriveFromFloorPriceList extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgDescTxt);
        scrnMsg.addCheckItem(scrnMsg.dsOrdTpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.sellToCustCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIndex);

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).flPrcListNm_LL)) {
                return null;
            }
        } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.D.no(selectIndex).flPrcListNm_RL)) {
                return null;
            }
        } else {
            if (!ZYPCommonFunc.hasValue(scrnMsg.flPrcListNm)) {
                return null;
            }
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

        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).flPrcListNm_LL)) {
                scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).serNum_LL);
                return;
            }
        } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.D.no(selectIndex).flPrcListNm_RL)) {
                scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).serNum_RL);
                return;
            }
        } else {
            if (!ZYPCommonFunc.hasValue(scrnMsg.flPrcListNm)) {
                scrnMsg.setFocusItem(scrnMsg.loanPerDaysAot);
                return;
            }
        }

        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).flPrcListNm_LL);
        } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).flPrcListNm_RL);
        } else {
            scrnMsg.addCheckItem(scrnMsg.flPrcListNm);
        }
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = NWAL1500CommonLogic.getParamNWAL1760(scrnMsg, PRC_CTX_TP.FLOOR_PRICE, true);
            setArgForSubScreen(params);
            return;
        }

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).serNum_LL);
        } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).serNum_RL);
        } else {
            scrnMsg.setFocusItem(scrnMsg.loanPerDaysAot);
        }
    }
}
