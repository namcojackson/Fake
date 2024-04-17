/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0620;

import static business.servlet.NLCL0620.constant.NLCL0620Constant.BIZ_APP_ID;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDGUIAttribute;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0620.NLCL0620CMsg;
import business.servlet.NLCL0620.common.NLCL0620CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NLCL0620 Tech PI Entry
 * Function Name : Button Action to Prev Page
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/03/2016   CITS            Makoto Okigami  Create          N/A
 * 06/25/2020   CITS            M.Furugoori     Update          QC#56979
 *</pre>
 */
public class NLCL0620Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0620BMsg scrnMsg = (NLCL0620BMsg) bMsg;

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowFromNum.getValueInt() - scrnMsg.A.length() - 1);
        scrnMsg.xxPageShowToNum.clear();

        NLCL0620CMsg bizMsg = new NLCL0620CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0620BMsg scrnMsg = (NLCL0620BMsg) bMsg;
        NLCL0620CMsg bizMsg  = (NLCL0620CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL0620CommonLogic.setTableScreen(scrnMsg);

        // START 2020/06/25 [QC#56979,ADD]
        int length = scrnMsg.A.getValidCount();
        for (int i = 0; i < length; i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).carrTrkUrl_A1.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).proNum_A1.getValue())) {
                scrnMsg.A.no(i).carrTrkUrl_A1.setInputProtected(false);
            } else {
                scrnMsg.A.no(i).carrTrkUrl_A1.setInputProtected(true);
                EZDGUIAttribute link = new EZDGUIAttribute(SCRN_ID,"carrTrk"+i);
                link.setStyleAttribute("color", "black");
                link.setStyleAttribute("text-decoration", "none");
                scrnMsg.addGUIAttribute(link);
            }
        }
        // END 2020/06/25 [QC#56979,ADD]
    }
}
