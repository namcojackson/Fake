/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2400;

import static business.servlet.NWAL2400.constant.NWAL2400Constant.BIZ_APP_ID;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.EVENT_NM_ORDER_REASON;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2400.NWAL2400CMsg;
import business.servlet.NWAL2400.common.NWAL2400CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NWAL2400 CUSA Retail Dealer Maintenance
 * </pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/12   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NWAL2400Scrn00_OpenWin_OrderReason extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2400BMsg scrnMsg = (NWAL2400BMsg) bMsg;

        int selectNum = getButtonSelectNumber();

        // checks the mandatory fields.
        scrnMsg.addCheckItem(scrnMsg.A.no(selectNum).dsOrdCatgDescTxt_A);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2400BMsg scrnMsg = (NWAL2400BMsg) bMsg;

        int selectNum = getButtonSelectNumber();
        scrnMsg.xxRowNum.setValue(selectNum);

        NWAL2400CMsg bizMsg = new NWAL2400CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2400BMsg scrnMsg = (NWAL2400BMsg) bMsg;
        NWAL2400CMsg bizMsg  = (NWAL2400CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int selectNum = getButtonSelectNumber();

        if (scrnMsg.A.no(selectNum).dsOrdCatgDescTxt_A.getErrorCode() != 0) {
            scrnMsg.addCheckItem(scrnMsg.A.no(selectNum).dsOrdCatgDescTxt_A);
            scrnMsg.putErrorScreen();
            // Search do not set error message on fields.
            throw new EZDFieldErrorException();
        }

        // Making of subscreen delivery information
        Object[] params = NWAL2400CommonLogic.getParamNWAL1130ForOrderReason(scrnMsg, selectNum);

        // Subscreen transition
        setArgForSubScreen(params);

        scrnMsg.eventNm.setValue(EVENT_NM_ORDER_REASON);
    }
}
