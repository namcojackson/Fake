/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860;

import static business.servlet.NMAL6860.constant.NMAL6860Constant.SCRN_ID;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.TAB_NM_DETAIL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import parts.transitioncommon.EZDTransition;
import business.blap.NMAL6860.NMAL6860CMsg;
import business.servlet.NMAL6860.common.NMAL6860CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID   : NMAL6860 Supplier Entry
 * Function Name : The business process for TAB Detail.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/04   CITS            M.Ouchi         Create          N/A
 * 2016/10/03   CITS            R.Shimamoto     Update          QC#12768
 * 2021/03/01   CITS            G.Delgado       Update          QC#56057
 * </pre>
 */
public class NMAL6860Scrn00_TAB_Detail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;

        NMAL6860CommonLogic.addCheckGeneralTab(scrnMsg);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            setNextTransition(EZDTransition.STAY, null);
            return;
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return NMAL6860CommonLogic.copyScrnMsgToBizMsgForSearch((NMAL6860BMsg) bMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;
        NMAL6860CMsg bizMsg = (NMAL6860CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
            scrnMsg.addCheckItem(scrnMsg.vndPmtTermDescTxt_H1);

            // Add Check Item for Address Validation
            for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(index).ctryCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(index).xxComnScrFirstValTxt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(index).xxComnScrScdValTxt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(index).ctyAddr_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(index).postCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(index).stCd_A);

                scrnMsg.addCheckItem(scrnMsg.A.no(index).xxComnScrFirstValTxt_AL);
            }

            scrnMsg.putErrorScreen();
            return;
        }

        // focus on Invoice Match Option of Detail Tab.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_NM_DETAIL);
        scrnMsg.setFocusItem(scrnMsg.invMatchTpCd_H2);

        // sets the input fields of Common Header.
        NMAL6860CommonLogic.setInputProtectedForCommonHeader(scrnMsg);
        // START 2021/03/01 G.Delgado [QC#56057,DEL]
        // sets the input fields of Detail Tab.
        // NMAL6860CommonLogic.setInputProtectedForDetailTab(scrnMsg);
        // END 2021/03/01 G.Delgado [QC#56057,DEL]

        NMAL6860CommonLogic.setCursorRuleForSwhDetail(scrnMsg);
    }
}
