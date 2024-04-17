/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import static business.servlet.NSAL0990.constant.NSAL0990Constant.BIZ_ID;
import static business.servlet.NSAL0990.constant.NSAL0990Constant.NSAM0131E;
import static business.servlet.NSAL0990.constant.NSAL0990Constant.MODE_1;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0990.NSAL0990CMsg;
import business.servlet.NSAL0990.common.NSAL0990CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         K.Kasai         Create          N/A
 * 2016/02/23   Hitachi         T.Tsuchida      Update          QC#4117
 * 2016/07/06   Hitachi         O.Okuma         Update          QC#9630
 * 2024/03/07   Hitachi         K.Kishimoto     Update          QC#63546
 *</pre>
 */
public class NSAL0990_NSAL1020 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // START 2016/02/23 T.Tsuchida [QC#4117, ADD]
        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }
        // END 2016/02/23 T.Tsuchida [QC#4117, ADD]

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        // START 2024/03/07 [QC#63546, ADD]
        setValue(scrnMsg.xxScrDply, MODE_1);
        // END   2024/03/07 [QC#63546, ADD]
        // START 2016/02/23 T.Tsuchida [QC#4117, MOD]
        if (hasValue(scrnMsg.svcMachMstrPk_P)) {
            setValue(scrnMsg.svcMachMstrPk, scrnMsg.svcMachMstrPk_P);
        } else {
            scrnMsg.setMessageInfo(NSAM0131E, new String[] {"Service Machine Master PK" });
            throw new EZDFieldErrorException();
        }
        if (hasValue(scrnMsg.dsContrDtlPk_P)) {
            setValue(scrnMsg.dsContrDtlPk, scrnMsg.dsContrDtlPk_P);
        } else {
            scrnMsg.setMessageInfo(NSAM0131E, new String[] {"Ds Contract Detail PK" });
            throw new EZDFieldErrorException();
        }
        // END 2016/02/23 T.Tsuchida [QC#4117, MOD]

        NSAL0990CMsg bizMsg = new NSAL0990CMsg();
        bizMsg.setBusinessID("NSAL0990");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // START 2016/02/23 T.Tsuchida [QC#4117, ADD]
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        // END 2016/02/23 T.Tsuchida [QC#4117, ADD]

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        NSAL0990CMsg bizMsg  = (NSAL0990CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NSAL0990CommonLogic.activateButtons(this, scrnMsg, functionList);
        NSAL0990CommonLogic.activateScreenItems(this, functionList, scrnMsg);
        NSAL0990CommonLogic.alternateTableRowColor(scrnMsg);
        NSAL0990CommonLogic.focusItem(scrnMsg);
        NSAL0990CommonLogic.formatItem(scrnMsg);
        NSAL0990CommonLogic.setXxDplyCtrlFlg(scrnMsg);
    }
}
