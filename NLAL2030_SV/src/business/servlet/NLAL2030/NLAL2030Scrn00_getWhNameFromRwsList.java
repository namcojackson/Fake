/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2030;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2030.NLAL2030CMsg;
import business.servlet.NLAL2030.constant.NLAL2030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/07/2018   CITS            T.Tokutomi      Create         QC#18461-Sol#085
 *</pre>
 */
public class NLAL2030Scrn00_getWhNameFromRwsList extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        int index = getButtonSelectNumber();
        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(index).rtlWhCd_B1)) {
            scrnMsg.B.no(index).rtlWhCd_B1.setErrorInfo(1, NLAL2030Constant.ZZM9000E, new String[] {"Warehouse" });
        }

        scrnMsg.addCheckItem(scrnMsg.B.no(index).rtlWhCd_B1);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;

        NLAL2030CMsg bizMsg = new NLAL2030CMsg();
        bizMsg.setBusinessID(NLAL2030Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // Select Button Number.
        ZYPEZDItemValueSetter.setValue(bizMsg.xxNum, new BigDecimal(getButtonSelectNumber()));

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;
        NLAL2030CMsg bizMsg = (NLAL2030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int index = getButtonSelectNumber();
        scrnMsg.addCheckItem(scrnMsg.B.no(index).rtlWhCd_B1);
        scrnMsg.putErrorScreen();
    }
}
