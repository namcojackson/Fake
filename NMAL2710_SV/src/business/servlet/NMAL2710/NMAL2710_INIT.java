/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2710;

import static business.servlet.NMAL2710.constant.NMAL2710Constant.BIZ_ID;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2710.NMAL2710CMsg;
import business.servlet.NMAL2710.common.NMAL2710CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL2710_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/16   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL2710_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2710BMsg scrnMsg = (NMAL2710BMsg) bMsg;
        NMAL2710CMsg bizMsg = new NMAL2710CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2710BMsg scrnMsg = (NMAL2710BMsg) bMsg;
        NMAL2710CMsg bizMsg = (NMAL2710CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2710CommonLogic.controlScreen(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.bizAreaOrgCd_H);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL2710BMsg scrnMsg = (NMAL2710BMsg) bMsg;

        // Header
        scrnMsg.bizAreaOrgCd_H.setNameForMessage("Business Area");
        scrnMsg.trtyGrpTpCd_H.setNameForMessage("Territory Group");
        scrnMsg.orgNm_H.setNameForMessage("Territory Name");
        scrnMsg.trtyRuleFromValTxt_H.setNameForMessage("Value From");
        scrnMsg.trtyRuleThruValTxt_H.setNameForMessage("Value To");
        scrnMsg.xxChkBox_DH.setNameForMessage("Check Box");

        // Detail Control
        scrnMsg.orgNm_DC.setNameForMessage("Move Postal Code To");
        scrnMsg.effFromDt_DC.setNameForMessage("Move Effective From");
        scrnMsg.effThruDt_DC.setNameForMessage("Move Effective To");
        scrnMsg.rqstRsltCmntTxt_DC.setNameForMessage("Mass Update Reason");

        // Detail
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage("Check Box");
            scrnMsg.A.no(i).orgNm_A1.setNameForMessage("Territory Name");
            scrnMsg.A.no(i).orgDescTxt_A1.setNameForMessage("Territory Description");
            scrnMsg.A.no(i).bizAreaOrgDescTxt_A1.setNameForMessage("Business Area");
            scrnMsg.A.no(i).trtyGrpTpDescTxt_A1.setNameForMessage("Territory Group");
            scrnMsg.A.no(i).trtyRuleOprdTpDescTxt_A1.setNameForMessage("Operator Type");
            scrnMsg.A.no(i).trtyRuleFromValTxt_A1.setNameForMessage("Value From");
            scrnMsg.A.no(i).trtyRuleThruValTxt_A1.setNameForMessage("Value To");
            scrnMsg.A.no(i).trtyRuleLogicTpDescTxt_A1.setNameForMessage("Logic");
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage("Effective Date From");
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage("Effective Date To");
        }
    }
}
