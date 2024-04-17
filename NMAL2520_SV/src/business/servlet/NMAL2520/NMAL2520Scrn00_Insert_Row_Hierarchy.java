/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2520;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2520.NMAL2520CMsg;
import business.servlet.NMAL2520.common.NMAL2520CommonLogic;
import business.servlet.NMAL2520.constant.NMAL2520Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/10   Fujitsu         K.Koitabashi    Create          N/A
 * 2018/09/20   Fujitsu         S.Kosaka        Update          QC#27724
 *</pre>
 */
public class NMAL2520Scrn00_Insert_Row_Hierarchy extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
        NMAL2520CommonLogic.clearMandantoryCheck(scrnMsg);
        NMAL2520CommonLogic.addCheckItems(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        NMAL2520CMsg bizMsg = new NMAL2520CMsg();
        bizMsg.setBusinessID(NMAL2520Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2520Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
        NMAL2520CMsg bizMsg = (NMAL2520CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.A.getValidCount() + 1 > scrnMsg.A.length()) {
            setButtonEnabled(NMAL2520Constant.BTN_INSERT_HIERARCHY, false);
        }
        if (scrnMsg.A.getValidCount() > 0) {
            setButtonEnabled(NMAL2520Constant.BTN_DELETE_HIERARCHY, true);
        }

        NMAL2520CommonLogic.controlOrgLink(scrnMsg);
        NMAL2520CommonLogic.setHierarchyOrgStatDate(scrnMsg);
        // 2018/09/20 QC#27724,ADD Add Start
        NMAL2520CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, NMAL2520Constant.SCREEN_TABLE_NAME_HIERARCHY_UP);
        // 2018/09/20 QC#27724,ADD Add End
    }
}
