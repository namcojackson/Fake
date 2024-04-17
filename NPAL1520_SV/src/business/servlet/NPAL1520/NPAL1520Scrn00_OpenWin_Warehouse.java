/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1520;

import static business.servlet.NPAL1520.constant.NPAL1520Constant.BIZ_APP_ID;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.EVENT_OPEN_WIN_WAREHOUSE;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.FUNCTION_CD_SEARCH;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1520.NPAL1520CMsg;
import business.servlet.NPAL1520.common.NPAL1520CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1520 Min-Max Planning Inquiry
 * Function Name : Open Window Warehouse Pop Up
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/11/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1520Scrn00_OpenWin_Warehouse extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1520BMsg scrnMsg = (NPAL1520BMsg) bMsg;

        scrnMsg.locTpCd.clear();

        if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCatgCd_SL)) {
            NPAL1520CMsg bizMsg = new NPAL1520CMsg();
            bizMsg.setBusinessID(BIZ_APP_ID);
            bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        } else {
            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1520BMsg scrnMsg = (NPAL1520BMsg) bMsg;
        NPAL1520CMsg bizMsg = (NPAL1520CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCatgCd_SL)) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        // Set Event Name.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_EV, EVENT_OPEN_WIN_WAREHOUSE);

        Object[] params = NPAL1520CommonLogic.setLocationPopupParam(scrnMsg);
        setArgForSubScreen(params);

    }
}
