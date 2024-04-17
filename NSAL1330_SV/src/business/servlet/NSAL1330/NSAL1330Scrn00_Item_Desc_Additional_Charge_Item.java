/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAM0649E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1330.NSAL1330CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330Scrn00_Item_Desc_Additional_Charge_Item extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        int index = getButtonSelectNumber();

        if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(index).mdseCd_CI)) {
            scrnMsg.C.no(index).mdseCd_CI.setErrorInfo(1, NSAM0649E, new String[] {"Additional Charge Item" });
            scrnMsg.addCheckItem(scrnMsg.C.no(index).mdseCd_CI);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        int index = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_A, new BigDecimal(index));

        NSAL1330CMsg bizMsg = new NSAL1330CMsg();
        bizMsg.setBusinessID("NSAL1330");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int index = getButtonSelectNumber();

        // has error
        if (scrnMsg.getMessageCode().endsWith("E")) {
            scrnMsg.setFocusItem(scrnMsg.C.no(index).mdseCd_CI);
            throw new EZDFieldErrorException();
        }
        scrnMsg.addCheckItem(scrnMsg.C.no(index).mdseCd_CI);  // 2017/03/08 S21_NA#16752 Add
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.C.no(index).mdseCd_CI);
    }
}
