/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0960;

import static business.servlet.NSAL0960.constant.NSAL0960Constant.BUSINESS_ID;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.FUNCTION_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0960.NSAL0960CMsg;
import business.servlet.NSAL0960.common.NSAL0960CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/21   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSAL0960_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0960BMsg scrnMsg = (NSAL0960BMsg) bMsg;

        NSAL0960CMsg bizMsg = new NSAL0960CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0960BMsg scrnMsg = (NSAL0960BMsg) bMsg;
        NSAL0960CMsg bizMsg  = (NSAL0960CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0960CommonLogic.initialControlScreen(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.dsContrVldListNm_H);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0960BMsg scrnMsg = (NSAL0960BMsg) bMsg;

        // List Header
        scrnMsg.dsContrVldListNm_H.setNameForMessage("List Name");
        scrnMsg.dsContrVldListDescTxt_H.setNameForMessage("Description");
        scrnMsg.effFromDt_H.setNameForMessage("Start Date");
        scrnMsg.effToDt_H.setNameForMessage("End Date");

        // List Detail
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).vldSortNum_A.setNameForMessage("Seq");
            scrnMsg.A.no(i).dsContrVldCatgDescTxt_A.setNameForMessage("Category");
            scrnMsg.A.no(i).dsContrVldNm_A.setNameForMessage("Validation");
            scrnMsg.A.no(i).dsContrVldDescTxt_A.setNameForMessage("Description");
            scrnMsg.A.no(i).vldActCd_AS.setNameForMessage("Action");
            scrnMsg.A.no(i).effFromDt_A.setNameForMessage("Start Date");
            scrnMsg.A.no(i).effToDt_A.setNameForMessage("End Date");
        }
    }
}
