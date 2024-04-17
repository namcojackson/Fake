/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7410;

import static business.servlet.NMAL7410.constant.NMAL7410Constant.BIZ_ID;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.FUNCTION_CD;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7410.NMAL7410CMsg;
import business.servlet.NMAL7410.common.NMAL7410CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/29   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */
public class NMAL7410_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7410BMsg scrnMsg = (NMAL7410BMsg) bMsg;

        NMAL7410CMsg bizMsg = new NMAL7410CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7410BMsg scrnMsg = (NMAL7410BMsg) bMsg;
        NMAL7410CMsg bizMsg = (NMAL7410CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.setFocusItem(scrnMsg.crListTxt);

        NMAL7410CommonLogic.initialControlScreen(this, getUserProfileService(), scrnMsg);
        NMAL7410CommonLogic.setProtect(this, scrnMsg);
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL7410BMsg scrnMsg = (NMAL7410BMsg) bMsg;
        scrnMsg.crListTxt.setNameForMessage("CSMP Price List");
        scrnMsg.crListGnrnNum.setNameForMessage("CSMP Generation#");
        scrnMsg.prcCatgNm.setNameForMessage("CSA Price List");
        scrnMsg.effFromDt_FR.setNameForMessage("Effective From :from");
        scrnMsg.effFromDt_TO.setNameForMessage("Effective From :to");
        scrnMsg.effThruDt_FR.setNameForMessage("Effective Thru :from");
        scrnMsg.effThruDt_TO.setNameForMessage("Effective Thru :to");
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NMAL7410_ABMsg contactMsg = scrnMsg.A.no(i);
            contactMsg.xxChkBox_A.setNameForMessage("Line Selection Check Box");
            contactMsg.crListTxt_A.setNameForMessage("CSMP Price Lsit");
            contactMsg.crListGnrnNum_A.setNameForMessage("CSMP Generation#");
            contactMsg.csaPrcCatgCd_A.setNameForMessage("CSA Price List");
            contactMsg.prcCatgNm_A.setNameForMessage("Description");
            contactMsg.effFromDt_A.setNameForMessage("Effective From");
            contactMsg.effThruDt_A.setNameForMessage("Effective Thru");
        }
    }
}
