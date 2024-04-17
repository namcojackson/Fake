/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1050.NSAL1050CMsg;
import business.servlet.NSAL1050.common.NSAL1050CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * T&C Attributes Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   CUSA            T.Mizuki         Create          N/A
 *</pre>
 */
public class NSAL1050_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NSAL1050");
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1050BMsg scrnMsg = (NSAL1050BMsg) bMsg;

        NSAL1050CMsg bizMsg = new NSAL1050CMsg();
        bizMsg.setBusinessID("NSAL1050");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1050BMsg scrnMsg = (NSAL1050BMsg) bMsg;
        NSAL1050CMsg bizMsg = (NSAL1050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1050CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        scrnMsg.xxChkBox_H.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.setFocusItem(scrnMsg.svcTermAttrbDescTxt);
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL1050BMsg scrnMsg = (NSAL1050BMsg) bMsg;
        scrnMsg.svcTermAttrbDescTxt.setNameForMessage("T&C Attribute");
        scrnMsg.xxChkBox_H.setNameForMessage("Active Only");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.setNameForMessage("Check Box");
            scrnMsg.A.no(i).svcTermAttrbDescTxt_A.setNameForMessage("T&C Attribute");
            scrnMsg.A.no(i).svcTermCondAttrbNm_A.setNameForMessage("Short Name");
            scrnMsg.A.no(i).svcTermCondDataSrcCd_A.setNameForMessage("LOV DDF Maaping");
            scrnMsg.A.no(i).svcTermCondSrcDescTxt_A.setNameForMessage("LOV DDF Maaping");
            scrnMsg.A.no(i).svcTermDataTpCd_1V.setNameForMessage("Data Type");
            scrnMsg.A.no(i).svcTermAttrbSortNum_A.setNameForMessage("Seq");
            scrnMsg.A.no(i).svcTermCondCatgPk_1V.setNameForMessage("Category");
            scrnMsg.A.no(i).xxChkBox_AC.setNameForMessage("Contract");
            scrnMsg.A.no(i).xxChkBox_AM.setNameForMessage("Machine");
            scrnMsg.A.no(i).xxChkBox_AA.setNameForMessage("Active");
            scrnMsg.A.no(i).effFromDt_A.setNameForMessage("Start");
            scrnMsg.A.no(i).effToDt_A.setNameForMessage("End");
        }
    }
}
