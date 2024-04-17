/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1510;

import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.addCheckItemBizLayerErr;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.checkFormatItem;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.checkNameMandatory;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.checkRadioButton;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.convTimeforScreen;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.setAppFracDigit;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.timeCompareCheck;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BUSINESS_ID;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.FUNC_CD_UPDT;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.NWAM0014E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1510.NWAL1510CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   CUSA            S.Ohki          Create          N/A
 * 2016/07/11   Fujitsu         H.Ikeda         Update          S21_NA#5030
 *</pre>
 */
public class NWAL1510Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum_H0)) {
            scrnMsg.cpoOrdNum_H0.setErrorInfo(1, NWAM0014E);
        }
        // S21_NA#5030 Add start
        checkRadioButton(scrnMsg);
        // S21_NA#5030 Add end
        checkFormatItem(scrnMsg);
        timeCompareCheck(scrnMsg);
        checkNameMandatory(scrnMsg);
        addCheckItemBizLayerErr(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;

        NWAL1510CMsg bizMsg = new NWAL1510CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPDT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;
        NWAL1510CMsg bizMsg = (NWAL1510CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        setAppFracDigit(scrnMsg);
        convTimeforScreen(scrnMsg, bizMsg);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum_H0);
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum_H0);
        scrnMsg.putErrorScreen();
    }
}
