/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0520;

import java.io.Serializable;
import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0520.NSAL0520CMsg;
import business.servlet.NSAL0520.common.NSAL0520CommonLogic;
import business.servlet.NSAL0520.constant.NSAL0520Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/01   CUSA            SRAA            Create          N/A
 *</pre>
 */
public class NSAL0520_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0520BMsg scrnMsg = (NSAL0520BMsg) bMsg;

        BigDecimal svcMachMstrPk = null;

        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null) {
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBBigDecimalItem) {
                    svcMachMstrPk = ((EZDBBigDecimalItem) prm[0]).getValue();
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.svcMachMstrPk, svcMachMstrPk);

        NSAL0520CMsg bizMsg = new NSAL0520CMsg();
        bizMsg.setBusinessID(NSAL0520Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0520Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0520BMsg scrnMsg = (NSAL0520BMsg) bMsg;
        NSAL0520CMsg bizMsg = (NSAL0520CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0520CommonLogic.setupScreenItems(this, scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
    }

}
