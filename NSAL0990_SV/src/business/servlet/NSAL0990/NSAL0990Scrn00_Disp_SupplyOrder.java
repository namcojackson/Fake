/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0990.NSAL0990CMsg;
import business.servlet.NSAL0990.common.NSAL0990CommonLogic;
import business.servlet.NSAL0990.constant.NSAL0990Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         K.Kasai         Create          N/A
 * 2016/07/06   Hitachi         O.Okuma         Update          QC#9630
 *</pre>
 */
public class NSAL0990Scrn00_Disp_SupplyOrder extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        NSAL0990CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;

        NSAL0990CMsg bizMsg = new NSAL0990CMsg();
        if ("Disp_SupplyOrder".equals(getLastGuard())) {
            setValue(scrnMsg.svcMachMstrPk, scrnMsg.E.no(getButtonSelectNumber()).svcMachMstrPk_E);
            setValue(scrnMsg.dsContrDtlPk, scrnMsg.E.no(getButtonSelectNumber()).dsContrDtlPk_E);
        }
        bizMsg.setBusinessID(NSAL0990Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        NSAL0990CMsg bizMsg  = (NSAL0990CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0990CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        //change Display mode
        setValue(scrnMsg.xxScrDply, NSAL0990Constant.MODE_1);
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NSAL0990Constant.BIZ_ID);
        NSAL0990CommonLogic.activateButtons(this, scrnMsg, functionList);
        NSAL0990CommonLogic.activateScreenItems(this, functionList, scrnMsg);
        NSAL0990CommonLogic.formatItem(scrnMsg);
        NSAL0990CommonLogic.alternateTableRowColor(scrnMsg);
        if (scrnMsg.C.getValidCount() == 0) {
            NSAL0990CommonLogic.focusItem(scrnMsg);
        } else {
            scrnMsg.setFocusItem(scrnMsg.C.no(0).ordCustUomQty_C);
        }
        NSAL0990CommonLogic.setXxDplyCtrlFlg(scrnMsg);
    }
}
