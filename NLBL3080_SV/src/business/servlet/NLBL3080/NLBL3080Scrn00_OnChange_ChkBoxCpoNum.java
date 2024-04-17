/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3080;

import static business.servlet.NLBL3080.constant.NLBL3080Constant.TAB_ID_ORD;
import static business.servlet.NLBL3080.constant.NLBL3080Constant.TAB_ID_ORD_LINE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3080.NLBL3080CMsg;
import business.servlet.NLBL3080.common.NLBL3080CommonLogic;
import business.servlet.NLBL3080.constant.NLBL3080Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * NLBL3080Scrn00_OnChange_ChkBoxCpoNum
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   CITS            H.Sugawara      Create          N/A
 * 2018/11/26   Fujitsu         S.Takami        Update          QC#27765
 *</pre>
 */
public class NLBL3080Scrn00_OnChange_ChkBoxCpoNum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;
        final int eventLine = getButtonSelectNumber();
        scrnMsg.xxNum_EV.setValue(eventLine);

        NLBL3080CMsg bizMsg = new NLBL3080CMsg();
        bizMsg.setBusinessID(NLBL3080Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;
        NLBL3080CMsg bizMsg  = (NLBL3080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3080CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        final int eventLine = getButtonSelectNumber();
        // 2018/11/26 QC#27765 Mod Start
//        scrnMsg.setFocusItem(scrnMsg.B.no(eventLine).xxChkBox_B1);
        if (TAB_ID_ORD.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.A.no(eventLine).xxChkBox_A1);
        } else if (TAB_ID_ORD_LINE.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.B.no(eventLine).xxChkBox_B1);
        }
        // 2018/11/26 QC#27765 Mod End

    }
}
