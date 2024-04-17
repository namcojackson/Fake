/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2030;

import static business.servlet.NWAL2030.constant.NWAL2030Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2030.NWAL2030CMsg;
import business.servlet.NWAL2030.common.NWAL2030CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Hold Set Up Screen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public class NWAL2030_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2030BMsg scrnMsg = (NWAL2030BMsg) bMsg;

        NWAL2030CMsg bizMsg = new NWAL2030CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2030BMsg scrnMsg = (NWAL2030BMsg) bMsg;
        NWAL2030CMsg bizMsg = (NWAL2030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2030CommonLogic.setupScreenItems(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.hldRsnCd);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL2030BMsg scrnMsg = (NWAL2030BMsg) bMsg;

        scrnMsg.hldRsnCd.setNameForMessage("Hold ID");
        scrnMsg.hldRsnNm.setNameForMessage("Hold Name");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.setNameForMessage("Check Box");
            scrnMsg.A.no(i).hldRsnCd_A.setNameForMessage("Hold ID");
            scrnMsg.A.no(i).hldRsnNm_A.setNameForMessage("Hold Name");
            scrnMsg.A.no(i).hldRsnDescTxt_A.setNameForMessage("Hold Description");
            scrnMsg.A.no(i).hldLvlCd_SV.setNameForMessage("Hold Level");
            scrnMsg.A.no(i).ordProcNodeCd_SV.setNameForMessage("Workflow Activity");
            scrnMsg.A.no(i).hldEffFromDt_A.setNameForMessage("Start Date");
            scrnMsg.A.no(i).hldEffToDt_A.setNameForMessage("End Date");
        }

    }

}
