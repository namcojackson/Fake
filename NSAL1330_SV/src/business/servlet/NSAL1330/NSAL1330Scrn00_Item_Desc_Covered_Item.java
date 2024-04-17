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
public class NSAL1330Scrn00_Item_Desc_Covered_Item extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        int index = getButtonSelectNumber();

        if (!ZYPCommonFunc.hasValue(scrnMsg.J.no(index).mdseCd_J)) {
            scrnMsg.J.no(index).mdseCd_J.setErrorInfo(1, NSAM0649E, new String[] {"Covered Item" });
            scrnMsg.addCheckItem(scrnMsg.J.no(index).mdseCd_J);
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
            scrnMsg.setFocusItem(scrnMsg.J.no(index).mdseCd_J);
            throw new EZDFieldErrorException();
        }

        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.J.no(index).addlBasePrcDealAmt_J);
    }
}