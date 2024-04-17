/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1230;

import static business.servlet.NPAL1230.constant.NPAL1230Constant.SCRN_ID;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BIZ_APP_ID;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.ZZM9000E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1230.NPAL1230CMsg;
import business.servlet.NPAL1230.common.NPAL1230CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/01/12   CITS            S.Katsuma       Create          N/A
 *</pre>
 */
public class NPAL1230Scrn00_GetSupplierSiteName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1230BMsg scrnMsg = (NPAL1230BMsg) bMsg;

        int selectNum = this.getButtonSelectNumber();

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(selectNum).vndCd_A)) {
            scrnMsg.A.no(selectNum).vndCd_A.setErrorInfo(1, ZZM9000E, new String[] {"Primary Supplier Site", " Please select." });
            scrnMsg.addCheckItem(scrnMsg.A.no(selectNum).vndCd_A);
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1230BMsg scrnMsg = (NPAL1230BMsg) bMsg;

        int selectNum = this.getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(selectNum));

        NPAL1230CMsg bizMsg = new NPAL1230CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1230BMsg scrnMsg = (NPAL1230BMsg) bMsg;
        NPAL1230CMsg bizMsg = (NPAL1230CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int selectNum = this.getButtonSelectNumber();

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        NPAL1230CommonLogic.setTableScreen(scrnMsg);

        if (scrnMsg.A.no(selectNum).vndCd_A.isError()) {
            scrnMsg.addCheckItem(scrnMsg.A.no(selectNum).vndCd_A);
            scrnMsg.putErrorScreen();
        } else {
            // set focus
            scrnMsg.setFocusItem(scrnMsg.A.no(selectNum).vndCd_A);
        }

    }
}
