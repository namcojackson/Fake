/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1260;

import static business.servlet.NSAL1260.constant.NSAL1260Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1260.NSAL1260CMsg;
import business.servlet.NSAL1260.common.NSAL1260CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Contract Machine Upload
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/22   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSAL1260_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1260BMsg scrnMsg = (NSAL1260BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            if (params.length > 0 && params[0] instanceof EZDBBigDecimalItem) {
                setValue(scrnMsg.dsContrPk, (EZDBBigDecimalItem) params[0]);
            }
        }

        NSAL1260CMsg bizMsg = new NSAL1260CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1260BMsg scrnMsg = (NSAL1260BMsg) bMsg;
        NSAL1260CMsg bizMsg = (NSAL1260CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1260CommonLogic.initialize(this);
        NSAL1260CommonLogic.screenItemControl(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL1260BMsg scrnMsg = (NSAL1260BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).svcMachMstrPk_A.setNameForMessage("IB ID");
            scrnMsg.A.no(i).serNum_A.setNameForMessage("Serail#");
            scrnMsg.A.no(i).bllgCycleCd_A.setNameForMessage("Frequency");
            scrnMsg.A.no(i).basePrcDealAmt_A.setNameForMessage("Price");
            scrnMsg.A.no(i).mtrReadMethCd_A.setNameForMessage("Meter Read Method");
            scrnMsg.A.no(i).billToAcctNum_A.setNameForMessage("Account#");
            scrnMsg.A.no(i).dsAcctNm_A.setNameForMessage("Account Name");
            scrnMsg.A.no(i).contrVrsnEffFromDt_A.setNameForMessage("Effective From");
            scrnMsg.A.no(i).contrVrsnEffThruDt_A.setNameForMessage("Effective To");
        }
    }
}
