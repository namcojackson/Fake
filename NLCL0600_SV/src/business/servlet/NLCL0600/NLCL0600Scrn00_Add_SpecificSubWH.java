/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0600;

import static business.servlet.NLCL0600.constant.NLCL0600Constant.BIZ_APP_ID;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.DISPLAY_NM_RTL_SWH_CD;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.NLCM0025E;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.NLZM2273E;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.NLZM2273E_P01;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0600.NLCL0600CMsg;
import business.servlet.NLCL0600.common.NLCL0600CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NLCL0600 PI Entry
 * Function Name : Add Specific SubWH
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/30/2016   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NLCL0600Scrn00_Add_SpecificSubWH extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0600BMsg scrnMsg = (NLCL0600BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd);

        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd)) {
            scrnMsg.rtlSwhCd.setErrorInfo(1, ZZM9000E, new String[] {DISPLAY_NM_RTL_SWH_CD});
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.rtlSwhCd.getValue().equals(scrnMsg.A.no(i).rtlSwhCd_A1.getValue())) {
                scrnMsg.rtlSwhCd.setErrorInfo(1, NLZM2273E, new String[] {NLZM2273E_P01, scrnMsg.rtlSwhCd.getValue()});
                break;
            }
        }

        if (scrnMsg.A.getValidCount() >= scrnMsg.A.length()) {
            scrnMsg.setMessageInfo(NLCM0025E);
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0600BMsg scrnMsg = (NLCL0600BMsg) bMsg;

        NLCL0600CMsg bizMsg = new NLCL0600CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0600BMsg scrnMsg = (NLCL0600BMsg) bMsg;
        NLCL0600CMsg bizMsg  = (NLCL0600CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd);
        scrnMsg.putErrorScreen();

        NLCL0600CommonLogic.setTableScreen(scrnMsg);

    }
}
