/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2030.NLAL2030CMsg;
import business.servlet.NLAL2030.common.NLAL2030CommonLogic;
import business.servlet.NLAL2030.constant.NLAL2030Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLAL2030Scrn00_OnChange_ChkBoxHdr
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/15/2016   CSAI            Y.Imazu         Create          QC#6086
 *</pre>
 */
public class NLAL2030Scrn00_OnChange_ChkBoxHdr extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;

        int eventLine = getButtonSelectNumber();
        scrnMsg.xxNum.setValue(eventLine);

        NLAL2030CMsg bizMsg = new NLAL2030CMsg();
        bizMsg.setBusinessID(NLAL2030Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;
        NLAL2030CMsg bizMsg  = (NLAL2030CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLAL2030CommonLogic.initCmnBtnProp(this);
        NLAL2030CommonLogic.controlScreenFields(this, scrnMsg);

        if (NLAL2030Constant.MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {

            throw new EZDFieldErrorException();
        }

        // Set Focus
        int eventLine = getButtonSelectNumber();
        scrnMsg.setFocusItem(scrnMsg.A.no(eventLine).xxChkBox_A1);
    }
}
