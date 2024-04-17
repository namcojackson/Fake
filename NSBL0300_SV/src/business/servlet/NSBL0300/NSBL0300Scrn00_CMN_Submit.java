/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0300;

import static business.servlet.NSBL0300.constant.NSBL0300Constant.BIZ_ID;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0300.NSBL0300CMsg;
import business.servlet.NSBL0300.common.NSBL0300CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSBL0300Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0300BMsg scrnMsg = (NSBL0300BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.svcSkillLvlGrpCd_H3);
        scrnMsg.addCheckItem(scrnMsg.svcSkillLvlGrpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcSkillLvlSortNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcSkillLvlNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcSkillLvlDescTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A);
            NSBL0300CommonLogic.formatFlg(scrnMsg.A.no(i));
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0300BMsg scrnMsg = (NSBL0300BMsg) bMsg;
        NSBL0300CommonLogic.reflectToLine(this, scrnMsg);

        NSBL0300CMsg bizMsg = new NSBL0300CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0300BMsg scrnMsg = (NSBL0300BMsg) bMsg;
        NSBL0300CMsg bizMsg  = (NSBL0300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSBL0300CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        NSBL0300CommonLogic.focusItem(scrnMsg);
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        EZDMsg.copy(scrnMsg.A, "A", scrnMsg.D, "D");
        scrnMsg.D.setValidCount(scrnMsg.A.getValidCount());
        NSBL0300CommonLogic.activateButtons(this, scrnMsg);
        NSBL0300CommonLogic.activateScreenItems(this, scrnMsg);
        NSBL0300CommonLogic.alternateTableRowColor(scrnMsg);
    }
}
