/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1400;

import static business.servlet.NSAL1400.common.NSAL1400CommonLogic.initialize;
import static business.servlet.NSAL1400.constant.NSAL1400Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1400.NSAL1400CMsg;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 * NSAL1400 Named Customer Resource setup
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/01   Hitachi         T.Tomita        Create          QC#18362
 *</pre>
 */
public class NSAL1400_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1400BMsg scrnMsg = (NSAL1400BMsg) bMsg;

        NSAL1400CMsg bizMsg = new NSAL1400CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1400BMsg scrnMsg = (NSAL1400BMsg) bMsg;
        NSAL1400CMsg bizMsg  = (NSAL1400CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        initialize(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.svcLineBizCd_H);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL1400BMsg scrnMsg = (NSAL1400BMsg) bMsg;
        // Header
        scrnMsg.svcLineBizCd_H.setNameForMessage("LOB");
        scrnMsg.dsAcctNm_H.setNameForMessage("Customer Name");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).svcLineBizCd_A.setNameForMessage("LOB");
            scrnMsg.A.no(i).dsAcctNum_A.setNameForMessage("Costomer");
            scrnMsg.A.no(i).contrAdminPsnCd_A.setNameForMessage("Resource");
            scrnMsg.A.no(i).effFromDt_A.setNameForMessage("Start Date");
            scrnMsg.A.no(i).effThruDt_A.setNameForMessage("End Date");
        }
    }
}
