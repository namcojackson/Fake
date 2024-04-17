/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0870;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0870.NSAL0870CMsg;
import business.servlet.NSAL0870.common.NSAL0870CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Meter Interface Status Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NSAL0870_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NSAL0870");
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0870BMsg scrnMsg = (NSAL0870BMsg) bMsg;

        NSAL0870CMsg bizMsg = new NSAL0870CMsg();
        bizMsg.setBusinessID("NSAL0870");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0870BMsg scrnMsg = (NSAL0870BMsg) bMsg;
        NSAL0870CMsg bizMsg  = (NSAL0870CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0870CommonLogic.initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL0870BMsg scrnMsg = (NSAL0870BMsg) bMsg;
        scrnMsg.serNum_01.setNameForMessage("Serial#");
        scrnMsg.mtrReadSrcTpCd_1V.setNameForMessage("Meter Source");
        scrnMsg.dsMtrProcStsCd_1V.setNameForMessage("Status");
        scrnMsg.mtrReadDt_FR.setNameForMessage("Meter Read From");
        scrnMsg.mtrReadDt_TO.setNameForMessage("Meter Read Thru");

    }
}
