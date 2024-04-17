/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0950;

import static business.servlet.NSAL0950.constant.NSAL0950Constant.BUSINESS_ID;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.FUNCTION_SEARCH;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0950.NSAL0950CMsg;
import business.servlet.NSAL0950.common.NSAL0950CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/14   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSAL0950Scrn00_AddLine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0950BMsg scrnMsg = (NSAL0950BMsg) bMsg;

        NSAL0950CMsg bizMsg = new NSAL0950CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0950BMsg scrnMsg = (NSAL0950BMsg) bMsg;
        NSAL0950CMsg bizMsg = (NSAL0950CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0950CommonLogic.initialControlScreen(this, scrnMsg);
        NSAL0950CommonLogic.controlScreenDetailFields(this, scrnMsg, true);

        if (scrnMsg.A.getValidCount() > 0) {
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                scrnMsg.setFocusItem(scrnMsg.dsContrVldCatgCd_SS);
            } else {
                setValue(scrnMsg.xxRadioBtn_A, new BigDecimal(scrnMsg.A.getValidCount() - 1));
                scrnMsg.setFocusItem(scrnMsg.dsContrVldCatgCd_DS);
            }
        }
    }
}
