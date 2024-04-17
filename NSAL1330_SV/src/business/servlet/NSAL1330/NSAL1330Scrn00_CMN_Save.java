/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.BIZ_ID;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NZZM0002I;
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
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1330Scrn00_CMN_Save
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330Scrn00_CMN_Save extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        NSAL1330CommonLogic.checkForSave(scrnMsg);

        NSAL1330CommonLogic.addCheckItems(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        NSAL1330CMsg bizMsg = new NSAL1330CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;
        //        if ("E".equals(bizMsg.getMessageKind())) {
        //            scrnMsg.putErrorScreen();
        //            throw new EZDFieldErrorException();
        //        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL1330CommonLogic.setUsgPrcAreaCtrl(scrnMsg, this);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1330CommonLogic.setConfigPricingAreaCtrlByConfig(//
                    scrnMsg, i, S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, scrnMsg.A.no(i).xxSmryLineFlg_A.getValue()));
        }

        NSAL1330CommonLogic.addCheckItems(scrnMsg);
        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);

            int ixFocus = 0;
//            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cpoSvcPrcPk_A)) {
//                    ixFocus = i;
//                }
//            }
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dsContrDtlPk_A)) {
                    ixFocus = i;
                }
            }
            if (scrnMsg.A.getValidCount() > 0) {
                scrnMsg.setFocusItem(scrnMsg.A.no(ixFocus).prcCatgNm_A);
            }
        }

    }
}
