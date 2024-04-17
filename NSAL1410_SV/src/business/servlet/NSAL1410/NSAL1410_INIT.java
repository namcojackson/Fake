/*
 * <pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1410;

import static business.servlet.NSAL1410.common.NSAL1410CommonLogic.*;
import static business.servlet.NSAL1410.constant.NSAL1410Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1410.NSAL1410CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Contract Branch Rep Update
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/06/07   Hitachi         A.Kohinata      Create          QC#60080
 *</pre>
 */
public class NSAL1410_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1410BMsg scrnMsg = (NSAL1410BMsg) bMsg;

        NSAL1410CMsg bizMsg = new NSAL1410CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1410BMsg scrnMsg = (NSAL1410BMsg) bMsg;
        NSAL1410CMsg bizMsg = (NSAL1410CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.putErrorScreen();
        initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL1410BMsg scrnMsg = (NSAL1410BMsg) bMsg;

        scrnMsg.dsContrNum_H.setNameForMessage("Contr#");
        scrnMsg.svcContrBrCd_H.setNameForMessage("Branch#");
        scrnMsg.svcContrBrDescTxt_H.setNameForMessage("Branch Name");
        scrnMsg.psnCd_H.setNameForMessage("Branch Rep");
        scrnMsg.xxPsnNm_H.setNameForMessage("Branch Rep Name");
        scrnMsg.billToCustCd_H.setNameForMessage("Bill To Code");
        scrnMsg.locNm_H.setNameForMessage("Bill To Name");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).psnCd_A2.setNameForMessage("New Branch Rep Code");
        }
    }
}
