/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0260;

import static business.servlet.NSBL0260.constant.NSBL0260Constant.BUSINESS_ID;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.FUNCTION_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0260.NSBL0260CMsg;
import business.servlet.NSBL0260.common.NSBL0260CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSBL0260_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0260BMsg scrnMsg = (NSBL0260BMsg) bMsg;

        NSBL0260CMsg bizMsg = new NSBL0260CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0260BMsg scrnMsg = (NSBL0260BMsg) bMsg;
        NSBL0260CMsg bizMsg  = (NSBL0260CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSBL0260CommonLogic.initialControlScreen(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.mdlNm_C);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSBL0260BMsg scrnMsg = (NSBL0260BMsg) bMsg;

        // Search Criteria
        scrnMsg.mdlNm_C.setNameForMessage("Model Name Combination");
        scrnMsg.serNum_C.setNameForMessage("Serial Number Combination");
        scrnMsg.mdlNm_H.setNameForMessage("Model Name");
        scrnMsg.dsAcctNum_H.setNameForMessage("Customer#");
        scrnMsg.xxChkBox_ON.setNameForMessage("Serviceable ON");
        scrnMsg.xxChkBox_OF.setNameForMessage("Serviceable OFF");
        // Search Result List
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).mdlNm_A.setNameForMessage("Model Name");
            scrnMsg.A.no(i).serNum_A.setNameForMessage("Serial Number");
            scrnMsg.A.no(i).dsMdlEolDt_AN.setNameForMessage("No New Contracts After Date");
            scrnMsg.A.no(i).dsMdlEolDt_AS.setNameForMessage("Stop Service Date");
            scrnMsg.A.no(i).actvFlg_A.setNameForMessage("Serviceable Flag");
            scrnMsg.A.no(i).dsAcctNum_A.setNameForMessage("Customer Number");
            scrnMsg.A.no(i).svcConfigMstrPk_A.setNameForMessage("Config Number");
            scrnMsg.A.no(i).dsAcctNm_A.setNameForMessage("Customer Name");
            scrnMsg.A.no(i).ctyAddr_A.setNameForMessage("City");
            scrnMsg.A.no(i).stCd_A.setNameForMessage("State");
        }
    }
}
