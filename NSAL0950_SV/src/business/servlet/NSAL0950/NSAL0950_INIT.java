/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0950;

import static business.servlet.NSAL0950.constant.NSAL0950Constant.BUSINESS_ID;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.FUNCTION_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0950.NSAL0950CMsg;
import business.servlet.NSAL0950.common.NSAL0950CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/14   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSAL0950_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
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
        NSAL0950CMsg bizMsg  = (NSAL0950CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0950CommonLogic.initialControlScreen(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.dsContrVldCatgCd_SS);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0950BMsg scrnMsg = (NSAL0950BMsg) bMsg;

        // Search Criteria
        scrnMsg.dsContrVldCatgCd_SS.setNameForMessage("Category Name");
        scrnMsg.dsContrVldNm_S.setNameForMessage("Validation Name");
        scrnMsg.dsContrVldDescTxt_S.setNameForMessage("Description");
        scrnMsg.vldLvlContrFlg_S.setNameForMessage("Level - Contract");
        scrnMsg.vldLvlMachFlg_S.setNameForMessage("Level - Machine");
        scrnMsg.effFromDt_S.setNameForMessage("Start Date");
        scrnMsg.effToDt_S.setNameForMessage("End Date");
        scrnMsg.vldCmptTxt_S.setNameForMessage("Component Name");
        scrnMsg.primVldFlg_S.setNameForMessage("Primary Validation");
        scrnMsg.ovrdVldRsltAvalFlg_S.setNameForMessage("Validation Result Override");
        scrnMsg.vldRegFlg_S.setNameForMessage("Contact Type - Non-Fleet");
        scrnMsg.vldFleetFlg_S.setNameForMessage("Contact Type - Fleet");
        scrnMsg.vldAggrFlg_S.setNameForMessage("Contact Type - Aggregate");
        scrnMsg.vldWtyFlg_S.setNameForMessage("Contact Type - warranty");
        // Search Result List
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dsContrVldCatgCd_AS.setNameForMessage("Category Name");
            scrnMsg.A.no(i).dsContrVldNm_A.setNameForMessage("Validation Name");
            scrnMsg.A.no(i).vldLvlContrFlg_A.setNameForMessage("Level - Contract");
            scrnMsg.A.no(i).vldLvlMachFlg_A.setNameForMessage("Level - Machine");
            scrnMsg.A.no(i).vldAggrFlg_A.setNameForMessage("Contact Type - Aggregate");
            scrnMsg.A.no(i).vldFleetFlg_A.setNameForMessage("Contact Type - Fleet");
            scrnMsg.A.no(i).vldRegFlg_A.setNameForMessage("Contact Type - Non-Fleet");
            scrnMsg.A.no(i).vldWtyFlg_A.setNameForMessage("Contact Type - warranty");
            scrnMsg.A.no(i).primVldFlg_A.setNameForMessage("Primary Validation");
            scrnMsg.A.no(i).ovrdVldRsltAvalFlg_A.setNameForMessage("Validation Result Override");
            scrnMsg.A.no(i).effFromDt_A.setNameForMessage("Start Date");
            scrnMsg.A.no(i).effToDt_A.setNameForMessage("End Date");
        }
        // Input Area
        scrnMsg.dsContrVldCatgCd_DS.setNameForMessage("Category Name");
        scrnMsg.dsContrVldNm_D.setNameForMessage("Validation Name");
        scrnMsg.dsContrVldDescTxt_D.setNameForMessage("Description");
        scrnMsg.vldLvlContrFlg_D.setNameForMessage("Level - Contract");
        scrnMsg.vldLvlMachFlg_D.setNameForMessage("Level - Machine");
        scrnMsg.effFromDt_D.setNameForMessage("Start Date");
        scrnMsg.effToDt_D.setNameForMessage("End Date");
        scrnMsg.vldCmptTxt_D.setNameForMessage("Component Name");
        scrnMsg.primVldFlg_D.setNameForMessage("Primary Validation");
        scrnMsg.ovrdVldRsltAvalFlg_D.setNameForMessage("Validation Result Override");
        scrnMsg.vldRegFlg_D.setNameForMessage("Contact Type - Non-Fleet");
        scrnMsg.vldFleetFlg_D.setNameForMessage("Contact Type - Fleet");
        scrnMsg.vldAggrFlg_D.setNameForMessage("Contact Type - Aggregate");
        scrnMsg.vldWtyFlg_D.setNameForMessage("Contact Type - warranty");
    }
}
