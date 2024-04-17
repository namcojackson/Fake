/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0290;

import static business.servlet.NSBL0290.constant.NSBL0290Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0290.NSBL0290CMsg;
import business.servlet.NSBL0290.common.NSBL0290CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/09   Hitachi         J.Kim           Create          N/A
 * 2016/10/21   Hitachi         K.Kojima        Update          QC#15137
 *</pre>
 */
public class NSBL0290_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0290BMsg scrnMsg = (NSBL0290BMsg) bMsg;

        NSBL0290CMsg bizMsg = NSBL0290CommonLogic.setRequestData_SearchCommon();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0290BMsg scrnMsg = (NSBL0290BMsg) bMsg;
        NSBL0290CMsg bizMsg = (NSBL0290CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0290CommonLogic.initialize(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSBL0290BMsg scrnMsg = (NSBL0290BMsg) bMsg;

        scrnMsg.svcSkillTpCd_S.setNameForMessage("Skill Type");
        scrnMsg.svcSkillTpDescTxt.setNameForMessage("Skill Type Description");
        scrnMsg.svcSkillLvlGrpCd_S.setNameForMessage("Use Scale");
        scrnMsg.effFromDt_H.setNameForMessage("Effective From");
        scrnMsg.effThruDt_H.setNameForMessage("Effective Thru");
        // START 2016/10/21 K.Kojima [QC#15137,ADD]
        scrnMsg.svcSkillNm_H.setNameForMessage("Skill Name");
        scrnMsg.svcSkillDescTxt_H.setNameForMessage("Skill Description");
        // END 2016/10/21 K.Kojima [QC#15137,ADD]
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox.setNameForMessage("#");
            scrnMsg.A.no(i).svcSkillNm.setNameForMessage("Skill Name");
            scrnMsg.A.no(i).svcSkillDescTxt.setNameForMessage("Skill Description");
            scrnMsg.A.no(i).svcAliasRate.setNameForMessage("Alias");
            scrnMsg.A.no(i).effFromDt.setNameForMessage("Effective From");
            scrnMsg.A.no(i).effThruDt.setNameForMessage("Effective Thru");
        }
    }
}
