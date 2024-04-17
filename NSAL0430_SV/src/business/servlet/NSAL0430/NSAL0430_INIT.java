/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0430;

import java.io.Serializable;
import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0430.NSAL0430CMsg;
import business.servlet.NSAL0430.common.NSAL0430CommonLogic;
import business.servlet.NSAL0430.constant.NSAL0430Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   CUSA            SRAA            Create          N/A
 * 2015/12/14   Hitachi         K.Kasai         Update          QC1896
 *</pre>
 */
public class NSAL0430_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0430BMsg scrnMsg = (NSAL0430BMsg) bMsg;

        BigDecimal svcMachMstrPk = null;
        // ADD START 2015/12/14 K.Kasai [QC1896]
        String mtrEstProcDt = null;
        // ADD END 2015/12/14 K.Kasai [QC1896]

        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null) {
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBBigDecimalItem) {
                    svcMachMstrPk = ((EZDBBigDecimalItem) prm[0]).getValue();
                }
                // ADD START 2015/12/14 K.Kasai [QC1896]
                if (prm.length > 0 && prm[2] != null && prm[2] instanceof EZDBDateItem) {
                    mtrEstProcDt = ((EZDBDateItem) prm[2]).getValue();
                }
                // ADD END 2015/12/14 K.Kasai [QC1896]
            }
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.svcMachMstrPk, svcMachMstrPk);
        // ADD START 2015/12/14 K.Kasai [QC1896]
        ZYPEZDItemValueSetter.setValue(scrnMsg.mtrEstProcDt, mtrEstProcDt);
        // ADD END 2015/12/14 K.Kasai [QC1896]

        NSAL0430CMsg bizMsg = new NSAL0430CMsg();
        bizMsg.setBusinessID(NSAL0430Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0430Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0430BMsg scrnMsg = (NSAL0430BMsg) bMsg;
        NSAL0430CMsg bizMsg = (NSAL0430CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0430CommonLogic.setupScreenItems(this, scrnMsg);
        NSAL0430CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
    }
}
