/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZPL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZPL0030.ZZPL0030CMsg;
import business.servlet.ZZPL0030.common.ZZPL0030CommonLogic;
import business.servlet.ZZPL0030.constant.ZZPL0030Constant;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/23   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0030_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), ZZPL0030Constant.BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;

        ZZPL0030CMsg bizMsg = new ZZPL0030CMsg();
        bizMsg.setBusinessID(ZZPL0030Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(ZZPL0030Constant.FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;
        ZZPL0030CMsg bizMsg = (ZZPL0030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.setFocusItem(scrnMsg.xxRptNm_0);

        // set common button properties
        ZZPL0030CommonLogic.setButtonProperties_INIT(this);

        // set table background color
        ZZPL0030CommonLogic.setTableRowColor(scrnMsg, ZZPL0030Constant.SCREENID_SCRN00);

    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) arg0;

        // ZZPL0030Scrn00 Header
        scrnMsg.xxRptNm_0.setNameForMessage("Report ID");

        // ZZPL0030Scrn01 Header
        scrnMsg.xxCratDt_FR.setNameForMessage("Creation Date (From)");
        scrnMsg.xxCratDt_TO.setNameForMessage("Creation Date (To)");
        scrnMsg.xxRptSrchTxt_1.setNameForMessage("Advanced Search Parameter");
        scrnMsg.xxRptSrchTxt_2.setNameForMessage("Advanced Search Parameter");
        scrnMsg.xxRptSrchTxt_3.setNameForMessage("Advanced Search Parameter");
        scrnMsg.xxRptSrchTxt_4.setNameForMessage("Advanced Search Parameter");

    }
}
