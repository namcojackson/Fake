/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0280;

import static business.servlet.NSBL0280.constant.NSBL0280Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;

import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0280.NSBL0280CMsg;

import business.servlet.NSBL0280.common.NSBL0280CommonLogic;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/17   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSBL0280_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0280BMsg scrnMsg = (NSBL0280BMsg) bMsg;

        NSBL0280CMsg bizMsg = NSBL0280CommonLogic.setRequestData_SearchCommon();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0280BMsg scrnMsg = (NSBL0280BMsg) bMsg;
        NSBL0280CMsg bizMsg = (NSBL0280CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0280CommonLogic.initialize(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSBL0280BMsg scrnMsg = (NSBL0280BMsg) bMsg;

        scrnMsg.svcSkillResrcTpCd_S.setNameForMessage("Resource Type");
        scrnMsg.psnCd.setNameForMessage("Resource");
        scrnMsg.fullPsnNm.setNameForMessage("Resource Name");
        scrnMsg.svcSkillTpCd_S.setNameForMessage("Skill Type");
        scrnMsg.svcSkillDescTxt.setNameForMessage("Skill Name");

        scrnMsg.xxRadioBtn_A.setNameForMessage("#");
        scrnMsg.xxRadioBtn_B.setNameForMessage("#");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NSBL0280_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.svcSkillTpDescTxt_A.setNameForMessage("Skill Type");
            abMsg.svcSkillDescTxt_A.setNameForMessage("Skill Name");
            abMsg.svcSkillLvlPk_AS.setNameForMessage("Skill Level");
            abMsg.effFromDt_A.setNameForMessage("Effective From");
            abMsg.effThruDt_A.setNameForMessage("Effective Thru");
        }

        for (int j = 0; j < scrnMsg.B.length(); j++) {
            NSBL0280_BBMsg bbMsg = scrnMsg.B.no(j);
            bbMsg.techCd_B.setNameForMessage("Person Code");
            bbMsg.fullPsnNm_B.setNameForMessage("Person Name");
            bbMsg.svcSkillResrcTpCd_BS.setNameForMessage("Resource Type");
            bbMsg.svcSkillLvlPk_BS.setNameForMessage("Skill Level");
            bbMsg.effFromDt_B.setNameForMessage("Effective From");
            bbMsg.effThruDt_B.setNameForMessage("Effective Thru");
        }
    }
}
