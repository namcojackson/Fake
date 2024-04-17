/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1160;

import static business.servlet.NPAL1160.constant.NPAL1160Constant.BIZ_APP_ID;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_APVLLIMIT;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_LOCATION;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_MEMBER;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_TEAM;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_TRANSACTION;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.FUNCTION_CD_UPDATE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1160.NPAL1160CMsg;
import business.servlet.NPAL1160.common.NPAL1160CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1160 PO/Inventory Approval Maintenace
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/17/2016   CITS            R Shimamoto     Create          N/A
 *</pre>
 */
public class NPAL1160Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1160BMsg scrnMsg = (NPAL1160BMsg) bMsg;
        String glblCmpyCd = getGlobalCompanyCode();
        NPAL1160CommonLogic.checkSubmit(scrnMsg, glblCmpyCd);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1160BMsg scrnMsg = (NPAL1160BMsg) bMsg;

        NPAL1160CMsg bizMsg = new NPAL1160CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1160BMsg scrnMsg = (NPAL1160BMsg) bMsg;
        NPAL1160CMsg bizMsg = (NPAL1160CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (DISPLAY_TAB_NM_TEAM.equals(scrnMsg.xxDplyTab.getValue())) {
            NPAL1160CommonLogic.setTableScreenA(this, scrnMsg);

        } else if (DISPLAY_TAB_NM_MEMBER.equals(scrnMsg.xxDplyTab.getValue())) {
            NPAL1160CommonLogic.setTableScreenB(this, scrnMsg);

        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(scrnMsg.xxDplyTab.getValue())) {
            NPAL1160CommonLogic.setTableScreenC(this, scrnMsg);

        } else if (DISPLAY_TAB_NM_LOCATION.equals(scrnMsg.xxDplyTab.getValue())) {
            NPAL1160CommonLogic.setTableScreenD(this, scrnMsg);

        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(scrnMsg.xxDplyTab.getValue())) {
            NPAL1160CommonLogic.setTableScreenE(this, scrnMsg);
        }

        NPAL1160CommonLogic.addCheckItemForDetail(scrnMsg);
        scrnMsg.putErrorScreen();
    }
}
