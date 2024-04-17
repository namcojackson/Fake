/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.BIZ_ID;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_SVC_PRC_AC;
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
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1330Scrn00_OnBlur_ServicePriceList_AsryChrg
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330Scrn00_OnBlur_ServicePriceList_AsryChrg extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.prcCatgNm_HJ);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcCatgNm_HJ)) {
            scrnMsg.prcCatgCd_HJ.clear();
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
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcCatgNm_HJ)) {
            scrnMsg.setFocusItem(scrnMsg.prcCatgNm_HJ);
            scrnMsg.prcCatgCd_HJ.clear();
            return;
        }

        NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.prcCatgNm_HJ);
        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params //
            = NSAL1330CommonLogic.getNWAL1760Prm(//
                    scrnMsg, POP_UP_SVC_PRC_AC //
                    , PRC_CTX_TP.SERVICE_PRICING //
                    , scrnMsg.prcCatgNm_HJ.getValue());
            setArgForSubScreen(params);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.prcCatgCd_HJ)) {
            for (int i = 0; i < scrnMsg.J.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.J.no(i).addlBasePrcCatgCd_J)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.J.no(i).addlBasePrcCatgCd_J, scrnMsg.prcCatgCd_HJ);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.J.no(i).prcCatgNm_J, scrnMsg.prcCatgNm_HJ);
                }
            }
            if (scrnMsg.J.getValidCount() > 0) {
                scrnMsg.setFocusItem(scrnMsg.J.no(0).mdseCd_J);
            } else {
                scrnMsg.setFocusItem(scrnMsg.prcCatgNm_HJ);
            }
        }

    }
}