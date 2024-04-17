/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2720;

import static business.servlet.NMAL2720.constant.NMAL2720Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2720.NMAL2720CMsg;
import business.servlet.NMAL2720.common.NMAL2720CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL2720_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/22   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL2720_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;
        NMAL2720CMsg bizMsg = new NMAL2720CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;
        NMAL2720CMsg bizMsg = (NMAL2720CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2720CommonLogic.initCmnBtnProp(this);
        NMAL2720CommonLogic.setScrnCtrl(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.bizAreaOrgCd_H);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;

        // Header
        scrnMsg.bizAreaOrgCd_H.setNameForMessage("Business Area");
        scrnMsg.xxPsnNm_H.setNameForMessage("Resource Name");
        scrnMsg.psnNum_H.setNameForMessage("Resource#");
        scrnMsg.orgNm_H.setNameForMessage("Organization Name");
        scrnMsg.psnCd_H.setNameForMessage("Employee ID");
        scrnMsg.xxChkBox_AH.setNameForMessage("Check Box");

        // Detail Control
        scrnMsg.xxPsnNm_D1.setNameForMessage("Move Organization To");
        scrnMsg.psnNum_D1.setNameForMessage("Move Resource#");
        scrnMsg.effFromDt_D1.setNameForMessage("Move Effective From");
        scrnMsg.effThruDt_D1.setNameForMessage("Move Effective To");
        scrnMsg.massUpdRsnCmntTxt_D1.setNameForMessage("Mass Update Reason");

        // Detail
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage("Check Box");
            scrnMsg.A.no(i).orgNm_A1.setNameForMessage("Organization Name");
            scrnMsg.A.no(i).orgDescTxt_A1.setNameForMessage("Organization Description");
            scrnMsg.A.no(i).bizAreaOrgDescTxt_A1.setNameForMessage("Business Area");
            scrnMsg.A.no(i).lineBizTpDescTxt_A1.setNameForMessage("Line of Business");
            scrnMsg.A.no(i).xxPsnNm_A1.setNameForMessage("Resource Name");
            scrnMsg.A.no(i).psnNum_A1.setNameForMessage("Resource#");
            scrnMsg.A.no(i).orgFuncRoleTpDescTxt_A1.setNameForMessage("Role");
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage("Effective Date From");
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage("Effective Date To");
        }
    }
}
