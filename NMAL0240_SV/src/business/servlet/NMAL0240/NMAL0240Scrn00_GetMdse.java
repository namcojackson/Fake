/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0240;

import static business.servlet.NMAL0240.constant.NMAL0240Constant.BIZ_ID;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.FUNC_CD_20;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.ZZM9000E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0240.NMAL0240CMsg;
import business.servlet.NMAL0240.common.NMAL0240CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0240Scrn00_GetMdse
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/08   Fujitsu         C.Tanaka        Create          CSA
 *</pre>
 */
public class NMAL0240Scrn00_GetMdse extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL0240BMsg scrnMsg = (NMAL0240BMsg) bMsg;

        int idx = getButtonSelectNumber();
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).mdseCd_A1)) {
            scrnMsg.A.no(idx).mdseCd_A1.setErrorInfo(1, ZZM9000E, new String[] {"Component" });
            scrnMsg.addCheckItem(scrnMsg.A.no(idx).mdseCd_A1);
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0240BMsg scrnMsg = (NMAL0240BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx_A, new BigDecimal(getButtonSelectNumber()));

        NMAL0240CMsg bizMsg = new NMAL0240CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0240BMsg scrnMsg = (NMAL0240BMsg) bMsg;
        NMAL0240CMsg bizMsg = (NMAL0240CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL0240CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }
}
