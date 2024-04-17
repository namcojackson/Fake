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
 * 2016/10/21   Hitachi         K.Kojima        Update          QC#15137
 *</pre>
 */
public class NSBL0290Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0290BMsg scrnMsg = (NSBL0290BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.svcSkillTpCd_S);
        // START 2016/10/21 K.Kojima [QC#15137,ADD]
        scrnMsg.addCheckItem(scrnMsg.svcSkillNm_H);
        scrnMsg.addCheckItem(scrnMsg.svcSkillDescTxt_H);
        // END 2016/10/21 K.Kojima [QC#15137,ADD]
        scrnMsg.putErrorScreen();
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

        if (MESSAGE_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NSBL0290CommonLogic.searchScreenControl(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.svcSkillTpDescTxt);
    }
}
