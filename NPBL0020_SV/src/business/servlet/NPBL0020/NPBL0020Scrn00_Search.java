/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.DISPLAY_NM_PRCH_REQ_NUM;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.NAMM0027E;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.NMAM0038I;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.SCRN_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.TAB_DETAIL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;
import business.servlet.NPBL0020.common.NPBL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Button Action to Search
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS            Makoto Okigami  Create          N/A
 * 08/24/2016   CITS            K.Ogino         Update          QC#9058
 *</pre>
 */
public class NPBL0020Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.prchReqNum)) {
            scrnMsg.prchReqNum.setErrorInfo(1, NAMM0027E, new String[] {DISPLAY_NM_PRCH_REQ_NUM });
        }

        scrnMsg.addCheckItem(scrnMsg.prchReqNum);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        // set values to items of pagenation for next page pagenation
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        NPBL0020CMsg bizMsg = new NPBL0020CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        NPBL0020CMsg bizMsg = (NPBL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (NMAM0038I.equals(scrnMsg.getMessageCode())) {
            scrnMsg.prchReqTpCd_SL.clear();
            NPBL0020CommonLogic.setCtrlScrnItemDispInit(this, scrnMsg);
        } else {
            NPBL0020CommonLogic.setCtrlScrnItemDispAfterSearch(this, scrnMsg);
        }

        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
        // set focus
        scrnMsg.setFocusItem(scrnMsg.prchReqNum);

    }
}
