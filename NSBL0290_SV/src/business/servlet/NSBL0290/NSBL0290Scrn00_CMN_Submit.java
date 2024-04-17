/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0290;

import static business.servlet.NSBL0290.constant.NSBL0290Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0290.NSBL0290CMsg;
import business.servlet.NSBL0290.common.NSBL0290CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/09   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSBL0290Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0290BMsg scrnMsg = (NSBL0290BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.svcSkillTpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.svcSkillLvlGrpCd_S);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H);
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcSkillNm);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcSkillDescTxt);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcAliasRate);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt);
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0290BMsg scrnMsg = (NSBL0290BMsg) bMsg;

        NSBL0290CMsg bizMsg = NSBL0290CommonLogic.setRequestData_UpdateCommon();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0290BMsg scrnMsg = (NSBL0290BMsg) bMsg;
        NSBL0290CMsg bizMsg = (NSBL0290CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0290CommonLogic.addCheckItem(this, scrnMsg);
        scrnMsg.putErrorScreen();

        NSBL0290CommonLogic.submitScreenControl(this, scrnMsg);

        if (MESSAGE_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.setFocusItem(scrnMsg.svcSkillTpDescTxt);
    }
}
