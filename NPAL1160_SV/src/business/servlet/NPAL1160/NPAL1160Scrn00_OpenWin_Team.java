/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1160;

import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_LOCATION;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_MEMBER;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_TRANSACTION;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
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
public class NPAL1160Scrn00_OpenWin_Team extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1160BMsg scrnMsg = (NPAL1160BMsg) bMsg;

        int rowNum = getButtonSelectNumber();
        Object[] params = null;
        if (DISPLAY_TAB_NM_MEMBER.equals(scrnMsg.xxDplyTab.getValue())) {
            if (rowNum >= 0) {
                params = NPAL1160CommonLogic.setTeamPopupParamForMemberDetail(scrnMsg);
            } else {
                params = NPAL1160CommonLogic.setTeamPopupParamForMemberHeader(scrnMsg);
            }

        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(scrnMsg.xxDplyTab.getValue())) {
            if (rowNum >= 0) {
                params = NPAL1160CommonLogic.setTeamPopupParamForTransactionDetail(scrnMsg);
            } else {
                params = NPAL1160CommonLogic.setTeamPopupParamForTransactionHeader(scrnMsg);
            }

        } else if (DISPLAY_TAB_NM_LOCATION.equals(scrnMsg.xxDplyTab.getValue())) {
            if (rowNum >= 0) {
                params = NPAL1160CommonLogic.setTeamPopupParamForLocationDetail(scrnMsg);
            } else {
                params = NPAL1160CommonLogic.setTeamPopupParamForLocationHeader(scrnMsg);
            }

        }

        // Subscreen transition
        setArgForSubScreen(params);

    }
}
