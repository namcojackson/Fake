/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.BIZ_ID;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_SVC_PRC_SPC;
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
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1330Scrn00_OnBlur_ServicePriceList_SvcPrc_forConfigPricing
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 * 2017/10/27   Hitachi         Y.Takeno        Update          QC#21556-1
 *</pre>
 */
public class NSAL1330Scrn00_OnBlur_ServicePriceList_SvcPrc_forConfigPricing extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxNum_A.setValue(selectIndex);

        if (ZYPCommonFunc.hasValue(scrnMsg.R.no(selectIndex).prcCatgNm_R)) {
            scrnMsg.addCheckItem(scrnMsg.R.no(selectIndex).prcCatgNm_R);
            scrnMsg.putErrorScreen();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        NSAL1330CommonLogic.clearSvcPrcListInfoForConfig(scrnMsg, selectIndex);
        if (!ZYPCommonFunc.hasValue(scrnMsg.R.no(selectIndex).prcCatgNm_R)) {
            scrnMsg.R.no(selectIndex).prcRateTpCd_R.clear();
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
        NSAL1330_RBMsg rScrnMsg = scrnMsg.R.no(selectIndex);
        if (!ZYPCommonFunc.hasValue(rScrnMsg.prcCatgNm_R)) {
            scrnMsg.setFocusItem(rScrnMsg.prcCatgNm_R);
            NSAL1330CommonLogic.clearSvcPrcListInfoForConfig(scrnMsg, selectIndex);
            NSAL1330CommonLogic.setUsgPrcAreaCtrlConfig(scrnMsg, this);
            NSAL1330CommonLogic.setInputProtectedOnChangeForUsageConfig(scrnMsg);

            NSAL1330CommonLogic.setBandButtonControlConfig(this, scrnMsg);
            NSAL1330CommonLogic.setSupplyButton(this, rScrnMsg, selectIndex, scrnMsg);

            return;
        }

        NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL1330CommonLogic.setSupplyButton(this, rScrnMsg, selectIndex, scrnMsg);
        NSAL1330CommonLogic.setBandButtonControlConfig(this, scrnMsg);
        NSAL1330CommonLogic.setUsgPrcAreaCtrlConfig(scrnMsg, this);
        NSAL1330CommonLogic.setInputProtectedOnChangeForUsageConfig(scrnMsg);
        // START 2017/10/27 [QC#21556-1, ADD]
        NSAL1330CommonLogic.overrideProtectedForAddContr(this, scrnMsg);
        // END   2017/10/27 [QC#21556-1, ADD]

        scrnMsg.addCheckItem(rScrnMsg.prcCatgNm_R);
        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            scrnMsg.xxNum_A.setValue(getButtonSelectNumber());
            Object[] params //
            = NSAL1330CommonLogic.getNWAL1760Prm(scrnMsg, POP_UP_SVC_PRC_SPC, PRC_CTX_TP.SERVICE_PRICING, rScrnMsg.prcCatgNm_R.getValue());
            setArgForSubScreen(params);
        }
        scrnMsg.setFocusItem(rScrnMsg.prcMtrPkgPk_R);
    }
}
