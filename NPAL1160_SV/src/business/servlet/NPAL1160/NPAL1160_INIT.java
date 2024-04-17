/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1160;

import static business.servlet.NPAL1160.constant.NPAL1160Constant.*;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_NM_TEAM_NAME;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.DISPLAY_TAB_NM_TEAM;
import static business.servlet.NPAL1160.constant.NPAL1160Constant.FUNCTION_CD_SEARCH;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1160.NPAL1160CMsg;
import business.servlet.NPAL1160.common.NPAL1160CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

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
public class NPAL1160_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1160BMsg scrnMsg = (NPAL1160BMsg) bMsg;
        scrnMsg.xxDplyTab.setValue(DISPLAY_TAB_NM_TEAM);
        NPAL1160CommonLogic.addCheckItemForDetail(scrnMsg);
        scrnMsg.putErrorScreen();

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1160BMsg scrnMsg = (NPAL1160BMsg) bMsg;
        NPAL1160CMsg bizMsg = new NPAL1160CMsg();

        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1160BMsg scrnMsg = (NPAL1160BMsg) bMsg;
        NPAL1160CMsg bizMsg = (NPAL1160CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        NPAL1160CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        // init default tab.
        scrnMsg.xxDplyTab.setValue(DISPLAY_TAB_NM_TEAM);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NPAL1160BMsg scrnMsg = (NPAL1160BMsg) arg0;
        scrnMsg.apvlTeamNm_TT.setNameForMessage(DISPLAY_NM_TEAM_NAME);
        scrnMsg.apvlTeamDescTxt_TT.setNameForMessage(DISPLAY_NM_TEAM_DESCRIPTION);
        scrnMsg.apvlTeamNm_MT.setNameForMessage(DISPLAY_NM_TEAM_NAME);
        scrnMsg.fullPsnNm_MT.setNameForMessage(DISPLAY_NM_MEMBER_NAME);
        scrnMsg.apvlTeamNm_ST.setNameForMessage(DISPLAY_NM_TEAM_NAME);
        scrnMsg.apvlTeamNm_LT.setNameForMessage(DISPLAY_NM_TEAM_NAME);
        scrnMsg.rtlWhNm_LT.setNameForMessage(DISPLAY_NM_WAREHOUSE_NAME);

        scrnMsg.xxPageShowCurNum.setNameForMessage(DISPLAY_NM_CURRENT_PAGE_NUMBER);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).apvlTeamNm_A1.setNameForMessage(DISPLAY_NM_TEAM_NAME);
            scrnMsg.A.no(i).apvlTeamDescTxt_A1.setNameForMessage(DISPLAY_NM_TEAM_DESCRIPTION);
        }
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).apvlTeamNm_B1.setNameForMessage(DISPLAY_NM_TEAM_NAME);
            scrnMsg.B.no(i).fullPsnNm_B1.setNameForMessage(DISPLAY_NM_MEMBER_NAME);
            scrnMsg.B.no(i).psnCd_B1.setNameForMessage(DISPLAY_NM_MEMBER_ID);
        }
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).apvlTeamNm_C1.setNameForMessage(DISPLAY_NM_TEAM_NAME);
        }
        for (int i = 0; i < scrnMsg.D.length(); i++) {
            scrnMsg.D.no(i).apvlTeamNm_D1.setNameForMessage(DISPLAY_NM_TEAM_NAME);
            scrnMsg.D.no(i).rtlWhCd_D1.setNameForMessage(DISPLAY_NM_WAREHOUSE_CODE);
            scrnMsg.D.no(i).rtlWhNm_D1.setNameForMessage(DISPLAY_NM_WAREHOUSE_NAME);
        }
        for (int i = 0; i < scrnMsg.E.length(); i++) {
            scrnMsg.E.no(i).psnCd_E1.setNameForMessage(DISPLAY_NM_MEMBER_ID);
            scrnMsg.E.no(i).fullPsnNm_E1.setNameForMessage(DISPLAY_NM_MEMBER_NAME);
            scrnMsg.E.no(i).apvlLimitAmt_E1.setNameForMessage(DISPLAY_NM_LIMIT_AMOUNT);
        }

    }
}
