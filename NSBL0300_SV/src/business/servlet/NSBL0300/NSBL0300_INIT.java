/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0300;

import static business.servlet.NSBL0300.constant.NSBL0300Constant.*;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0300.NSBL0300CMsg;
import business.servlet.NSBL0300.common.NSBL0300CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSBL0300_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0300BMsg scrnMsg = (NSBL0300BMsg) bMsg;

        NSBL0300CMsg bizMsg = new NSBL0300CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0300BMsg scrnMsg = (NSBL0300BMsg) bMsg;
        NSBL0300CMsg bizMsg  = (NSBL0300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NSBL0300CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NSBL0300CommonLogic.activateButtonsByFuncList(this, scrnMsg, functionList);
        NSBL0300CommonLogic.activateScreenItemsByFuncList(this, functionList, scrnMsg);
        NSBL0300CommonLogic.alternateTableRowColor(scrnMsg);
        NSBL0300CommonLogic.focusItem(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSBL0300BMsg scrnMsg = (NSBL0300BMsg) bMsg;
        scrnMsg.svcSkillLvlGrpCd_H3.setNameForMessage("Rating Scale Type");
        scrnMsg.svcSkillLvlGrpDescTxt.setNameForMessage("Rating Scale Type Description");
        scrnMsg.effFromDt.setNameForMessage("Effective Date From");
        scrnMsg.effThruDt.setNameForMessage("Effective Date Thru");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).svcSkillLvlSortNum_A.setNameForMessage("Order");
            scrnMsg.A.no(i).svcSkillLvlNm_A.setNameForMessage("Skill Level Name");
            scrnMsg.A.no(i).svcSkillLvlDescTxt_A.setNameForMessage("Skill Level Description");
            scrnMsg.A.no(i).effFromDt_A.setNameForMessage("Effective Date From");
            scrnMsg.A.no(i).effThruDt_A.setNameForMessage("Effective Date Thru");
        }
    }
}
