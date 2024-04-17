/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1530;

import static business.servlet.NPAL1530.constant.NPAL1530Constant.BIZ_APP_ID;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.ZZZM9007E;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.DISPLAY_NM_RTL_WH_CD;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1530.NPAL1530CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1530 Min-Max Planning Report Run Screen
 * Function Name : Set Warehouse Name
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/24/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1530Scrn00_SetWarehouseName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1530BMsg scrnMsg = (NPAL1530BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)) {
            scrnMsg.rtlWhNm.clear();
            scrnMsg.rtlWhCd.setErrorInfo(1, ZZZM9007E, new String[] {DISPLAY_NM_RTL_WH_CD });
        }
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1530BMsg scrnMsg = (NPAL1530BMsg) bMsg;

        NPAL1530CMsg bizMsg = new NPAL1530CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1530BMsg scrnMsg = (NPAL1530BMsg) bMsg;
        NPAL1530CMsg bizMsg  = (NPAL1530CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.rtlWhCd.isError()) {
            scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
            scrnMsg.putErrorScreen();
        } else {
            // set focus
            scrnMsg.setFocusItem(scrnMsg.rtlWhCd);
        }

    }
}
