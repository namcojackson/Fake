/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1160;

import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_APVLLIMIT;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_MEMBER;
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
 * 01/11/2017   CITS            R.Shimamoto     Update          QC#17059
 *</pre>
 */
public class NPAL1160Scrn00_OpenWin_Member extends S21CommonHandler {

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

        String glblCmpyCd = getGlobalCompanyCode();
        int rowNum = getButtonSelectNumber();
        Object[] params = null;
        if (DISPLAY_TAB_NM_MEMBER.equals(scrnMsg.xxDplyTab.getValue())) {
            if (rowNum >= 0) {
                // detail
                params = NPAL1160CommonLogic.setMemberPopupParamForMemberDetail(scrnMsg, glblCmpyCd, rowNum);
            } else {
                // header
                params = NPAL1160CommonLogic.setMemberPopupParamForMemberHeader(scrnMsg, glblCmpyCd);
            }

        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(scrnMsg.xxDplyTab.getValue())) {
            // QC#17059 Mod.
            if (rowNum >= 0) {
                // detail
                params = NPAL1160CommonLogic.setMemberPopupParamForApprovalLimitDetail(scrnMsg, glblCmpyCd, rowNum);
            } else {
                // header
                params = NPAL1160CommonLogic.setMemberPopupParamForApprovalLimitHeader(scrnMsg, glblCmpyCd);
            }

        }

        // Subscreen transition
        setArgForSubScreen(params);

    }
}
