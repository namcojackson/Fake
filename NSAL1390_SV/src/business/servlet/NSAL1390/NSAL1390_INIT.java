/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1390;

import static business.servlet.NSAL1390.constant.NSAL1390Constant.BUSINESS_ID;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.FUNCTION_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1390.NSAL1390CMsg;
import business.servlet.NSAL1390.common.NSAL1390CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * Renewal/Annual Escalation Letter Setup
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/21   Fujitsu         T.Murai         Create          S21_NA#8661(Sol#004)
 *</pre>
 */
public class NSAL1390_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
         checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1390BMsg scrnMsg = (NSAL1390BMsg) bMsg;

        NSAL1390CMsg bizMsg = new NSAL1390CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1390BMsg scrnMsg = (NSAL1390BMsg) bMsg;
        NSAL1390CMsg bizMsg = (NSAL1390CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL1390CommonLogic.initialize(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.lineBizTpCd_H);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL1390BMsg scrnMsg = (NSAL1390BMsg) bMsg;

        // set Header
        scrnMsg.lineBizTpCd_H.setNameForMessage("LOB");
        scrnMsg.svcRgPk_H.setNameForMessage("Region");
        scrnMsg.svcRgDescTxt_H.setNameForMessage("Region Name");
        scrnMsg.svcContrBrCd_H.setNameForMessage("Branch");
        scrnMsg.svcContrBrDescTxt_H.setNameForMessage("Branch Name");

        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());
        // set Detail
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage("Check Box");
            scrnMsg.A.no(i).lineBizTpCd_A.setNameForMessage("LOB");
            scrnMsg.A.no(i).svcRgPk_A.setNameForMessage("Region");
            scrnMsg.A.no(i).svcRgDescTxt_A.setNameForMessage("Region Name");
            scrnMsg.A.no(i).svcContrBrCd_A.setNameForMessage("Branch");
            scrnMsg.A.no(i).svcContrBrDescTxt_A.setNameForMessage("Branch Name");
            scrnMsg.A.no(i).xxChkBox_A2.setNameForMessage("Renewal Letter");
            scrnMsg.A.no(i).xxChkBox_A3.setNameForMessage("Annual Escalation");
            scrnMsg.A.no(i).xxChkBox_A4.setNameForMessage("Print old Price");
        }
    }
}
