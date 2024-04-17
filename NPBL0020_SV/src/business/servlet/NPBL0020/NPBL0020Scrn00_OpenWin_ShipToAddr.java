/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_SEARCH;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;
import business.servlet.NPBL0020.common.NPBL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Open Window Ship To Change Pop Up(NWAL0140)
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/13/2017   CITS            K.Ogino         Create          QC#17483
 *</pre>
 */
public class NPBL0020Scrn00_OpenWin_ShipToAddr extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        int eventRowIndex = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(eventRowIndex));

        NPBL0020CMsg bizMsg = new NPBL0020CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        NPBL0020CMsg bizMsg = (NPBL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int index = getButtonSelectNumber();
        if (("E".equals(bizMsg.getMessageKind()) //
                || scrnMsg.A.no(index).rtlWhNm_A2.isError() //
                || scrnMsg.A.no(index).shipToLocNm_E1.isError() //
                || scrnMsg.vndCd.isError() //
                || scrnMsg.dplyVndNm.isError())) {
            scrnMsg.addCheckItem(scrnMsg.A.no(index).rtlWhNm_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).shipToLocNm_E1);
            scrnMsg.addCheckItem(scrnMsg.vndCd);
            scrnMsg.addCheckItem(scrnMsg.dplyVndNm);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        } else {
            Object[] params = NPBL0020CommonLogic.setParamForShipToAddr(scrnMsg, getButtonSelectNumber());

            // Subscreen transition
            setArgForSubScreen(params);

        }
    }
}
