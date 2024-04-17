/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6820;

import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.BIZ_APP_ID;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.MODE_UPDATE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_SHIP_TO_CODE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_RTRN_TO_CODE;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL6820.NMAL6820CMsg;
import business.servlet.NMAL6820.common.NMAL6820CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NMAL6820 Warehouse Setup
 * Function Name : Return Action from Account Search Popup(NMAL6760)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/29/2016   CSAI            D.Fukaya        Create          QC# 1596/2363/2365
 *</pre>
 */
public class NMAL6820_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if (EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            return null;
        }

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
        NMAL6820CMsg bizMsg = new NMAL6820CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if (EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            return;
        }

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
        NMAL6820CMsg bizMsg = (NMAL6820CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (EVENT_NM_AL6820SCRN00_OPEN_WIN_SHIP_TO_CODE.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.locNum_S1);
                scrnMsg.putErrorScreen();
                scrnMsg.setFocusItem(scrnMsg.locNum_S1);
            }
            if (EVENT_NM_AL6820SCRN00_OPEN_WIN_RTRN_TO_CODE.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.locNum_R1);
                scrnMsg.putErrorScreen();
                scrnMsg.setFocusItem(scrnMsg.locNum_R1);
            }
        }

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);
        if (MODE_UPDATE.equals(scrnMsg.xxModeCd_G1.getValue())) {
            NMAL6820CommonLogic.cntrlScrnItemDispUpdateMode(this, scrnMsg, funcList);
        } else {
            NMAL6820CommonLogic.cntrlScrnItemDispCreateMode(this, scrnMsg, funcList);
        }
    }
}
