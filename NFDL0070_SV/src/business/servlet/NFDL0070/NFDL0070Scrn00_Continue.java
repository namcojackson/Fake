/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0070;

import static business.servlet.NFDL0070.constant.NFDL0070Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0070.NFDL0070CMsg;
import business.servlet.NFDL0070.common.NFDL0070CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NFDL0070Scrn00_Continue.
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/16   Fujitsu         M.Yamada        Create          N/A
 * 2016/11/11   Hitachi         J.Kim           Update          QC#15756
 * 2018/07/06   Hitachi         Y.Takeno        Update          QC#26989
 *</pre>
 */
public class NFDL0070Scrn00_Continue extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // START 2016/11/11 J.Kim [QC#15756,ADD]
        NFDL0070BMsg scrnMsg = (NFDL0070BMsg) bMsg;
        NFDL0070CMsg bizMsg = NFDL0070CommonLogic.setRequestData_NFDL0070_INIT(scrnMsg);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
        // END 2016/11/11 J.Kim [QC#15756,ADD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0070BMsg scrnMsg = (NFDL0070BMsg) bMsg;

        // START 2016/11/11 J.Kim [QC#15756,ADD]
        NFDL0070CMsg bizMsg  = (NFDL0070CMsg) cMsg;

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // END 2016/11/11 J.Kim [QC#15756,ADD]

        if (scrnMsg.xxRadioBtn.isClear()) {
            EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
            scrnMsg.xxRadioBtn.setErrorInfo(1, MSG_ID.NFDM0002E.toString() //
                    , new String[] {converter.convLabel2i18nLabel("", "Line") });
            scrnMsg.addCheckItem(scrnMsg.xxRadioBtn);
            scrnMsg.putErrorScreen();
            return;
        }
        int n = scrnMsg.xxRadioBtn.getValueInt();
        ZYPEZDItemValueSetter.setValue(scrnMsg.arTrxNum_P, scrnMsg.A.no(n).arTrxNum_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.arCustRefNum_P, scrnMsg.A.no(n).arCustRefNum_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.arTrxBalPk_P, scrnMsg.A.no(n).arTrxBalPk_A1);

        Object[] params = new Object[NUM_OF_PARAMS_NFDL0080];
        int i = 0;
        params[i++] = scrnMsg.arTrxNum_P;
        params[i++] = scrnMsg.arCustRefNum_P;
        params[i] = scrnMsg.arTrxBalPk_P;

        setArgForSubScreen(params);
        EZDDebugOutput.println(1, "doProcess Continue params[0]:" + scrnMsg.arTrxNum_P.getValue(), this);
        EZDDebugOutput.println(1, "doProcess Continue params[1]:" + scrnMsg.arCustRefNum_P.getValue(), this);
        EZDDebugOutput.println(1, "doProcess Continue params[2]:" + scrnMsg.arTrxBalPk_P.getValue(), this);
    }
}
