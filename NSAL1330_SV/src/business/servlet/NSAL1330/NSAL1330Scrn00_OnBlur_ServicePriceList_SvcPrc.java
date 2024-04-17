/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.BIZ_ID;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_SVC_PRC_SP;
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
 * NSAL1330Scrn00_OnBlur_ServicePriceList_SvcPrc
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 * 2017/10/27   Hitachi         Y.Takeno        Update          QC#21556-1
 *</pre>
 */
public class NSAL1330Scrn00_OnBlur_ServicePriceList_SvcPrc extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxNum_A.setValue(selectIndex);

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIndex).prcCatgNm_A)) {
            scrnMsg.addCheckItem(scrnMsg.A.no(selectIndex).prcCatgNm_A);
            scrnMsg.putErrorScreen();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        NSAL1330CommonLogic.clearSvcPrcListInfo(scrnMsg, selectIndex);
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIndex).prcCatgNm_A)) {
            scrnMsg.A.no(selectIndex).prcRateTpCd_A.clear();
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
        NSAL1330_ABMsg aScrnMsg = scrnMsg.A.no(selectIndex);
        //        if (ZYPCommonFunc.hasValue(aScrnMsg.prcTierSvcOfferCd_A)) {
        //            aScrnMsg.prcTierSvcOfferCd_A.setInputProtected(true);
        //            aScrnMsg.prcTierSvcOfferCd_AB.setInputProtected(true);
        //        } else {
        //            aScrnMsg.prcTierSvcOfferCd_A.setInputProtected(false);
        //            aScrnMsg.prcTierSvcOfferCd_AB.setInputProtected(false);
        //        }
        if (!ZYPCommonFunc.hasValue(aScrnMsg.prcCatgNm_A)) {
            scrnMsg.setFocusItem(aScrnMsg.prcCatgNm_A);
            NSAL1330CommonLogic.clearSvcPrcListInfo(scrnMsg, selectIndex);
            NSAL1330CommonLogic.setUsgPrcAreaCtrl(scrnMsg, this);
            NSAL1330CommonLogic.setInputProtectedOnChangeForUsage(scrnMsg);

            NSAL1330CommonLogic.setBandButtonControl(this, scrnMsg);
            NSAL1330CommonLogic.setSupplyButton(this, aScrnMsg, selectIndex, scrnMsg);

            return;
        }

        NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        //        if (ZYPCommonFunc.hasValue(aScrnMsg.prcTierSvcOfferCd_A)) {
        //            aScrnMsg.prcTierSvcOfferCd_A.setInputProtected(true);
        //            aScrnMsg.prcTierSvcOfferCd_AB.setInputProtected(true);
        //        } else {
        //            aScrnMsg.prcTierSvcOfferCd_A.setInputProtected(false);
        //            aScrnMsg.prcTierSvcOfferCd_AB.setInputProtected(false);
        //        }
        NSAL1330CommonLogic.setSupplyButton(this, aScrnMsg, selectIndex, scrnMsg);
        NSAL1330CommonLogic.setBandButtonControl(this, scrnMsg);
        NSAL1330CommonLogic.setUsgPrcAreaCtrl(scrnMsg, this);
        NSAL1330CommonLogic.setInputProtectedOnChangeForUsage(scrnMsg);
        // START 2017/10/27 [QC#21556-1, ADD]
        NSAL1330CommonLogic.overrideProtectedForAddContr(this, scrnMsg);
        // END   2017/10/27 [QC#21556-1, ADD]

        scrnMsg.addCheckItem(aScrnMsg.prcCatgNm_A);
        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            scrnMsg.xxNum_A.setValue(getButtonSelectNumber());
            Object[] params //
            = NSAL1330CommonLogic.getNWAL1760Prm(scrnMsg, POP_UP_SVC_PRC_SP, PRC_CTX_TP.SERVICE_PRICING, aScrnMsg.prcCatgNm_A.getValue());
            setArgForSubScreen(params);
        }
        scrnMsg.setFocusItem(aScrnMsg.prcMtrPkgPk_A);
    }
}
