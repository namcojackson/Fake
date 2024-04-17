/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1540;

import static business.servlet.NPAL1540.constant.NPAL1540Constant.BIZ_APP_ID;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.SERIAL_POPUP_PARAM_ENTRY;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.SERIAL_POPUP_PARAM_READ;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1540.NPAL1540CMsg;
import business.servlet.NPAL1540.common.NPAL1540CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NPAL1540 Manual ASN Entry
 * Function Name : Open Serial Entry
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/25   CITS            Makoto Okigami  Create          N/A
 * 02/26/2018   CITS            T.Wada          Update          QC#20445
 *</pre>
 */
public class NPAL1540Scrn00_OpenWin_SerEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1540BMsg scrnMsg = (NPAL1540BMsg) bMsg;

        int eventRowIndex = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(eventRowIndex));

        NPAL1540CMsg bizMsg = new NPAL1540CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1540BMsg scrnMsg = (NPAL1540BMsg) bMsg;
        NPAL1540CMsg bizMsg  = (NPAL1540CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int eventRowIndex = getButtonSelectNumber();
        String enableUpdateFlag = null;
        if (NPAL1540CommonLogic.isUpdatable()) {
        	// QC#20445
//            if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem54Txt)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem54Txt) || ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(eventRowIndex).rcvSerTakeFlg_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, SERIAL_POPUP_PARAM_READ);
                enableUpdateFlag = ZYPConstant.FLG_OFF_N;
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, SERIAL_POPUP_PARAM_ENTRY);
                enableUpdateFlag = ZYPConstant.FLG_ON_Y;
            }
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, SERIAL_POPUP_PARAM_READ);
            enableUpdateFlag = ZYPConstant.FLG_OFF_N;
        }
        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(i).xxEdtModeFlg_L1, enableUpdateFlag);
        }

        Object[] params = new Object[12];
        params[0] = scrnMsg.P.no(0).xxPopPrm.getValue();
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.A.no(eventRowIndex).asnQty_A1;
        params[5] = scrnMsg.P.no(5).xxPopPrm.getValue();
        params[6] = scrnMsg.P.no(6).xxPopPrm;
        params[7] = scrnMsg.P.no(7).xxPopPrm;
        params[8] = scrnMsg.L;
        params[9] = scrnMsg.P.no(9).xxPopPrm;
        params[10] = scrnMsg.P.no(10).xxPopPrm;
        params[11] = scrnMsg.P.no(11).xxPopPrm.getValue();

        setArgForSubScreen(params);

    }
}
