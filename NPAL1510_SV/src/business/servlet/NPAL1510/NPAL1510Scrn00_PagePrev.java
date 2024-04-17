/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1510;

import static business.servlet.NPAL1510.constant.NPAL1510Constant.BUSINESS_APPL_ID;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BUSINESS_SCREEN_ID;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDGUIAttribute;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1510.NPAL1510CMsg;
import business.servlet.NPAL1510.common.NPAL1510CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1510 PO Search
 * Function Name : PagePrev
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   CUSA            K.Ogino         Create          N/A
 * 2020/05/12   CITS            M.Furugoori     Update          QC#51167
 *</pre>
 */
public class NPAL1510Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1510BMsg scrnMsg = (NPAL1510BMsg) bMsg;
        NPAL1510CMsg bizMsg = new NPAL1510CMsg();
        bizMsg.setBusinessID(BUSINESS_APPL_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1510BMsg scrnMsg = (NPAL1510BMsg) bMsg;
        NPAL1510CMsg bizMsg = (NPAL1510CMsg) cMsg;
        String displayLevel = bizMsg.xxDplyByCtrlItemCdNm.getValue();
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NPAL1510CommonLogic.setTableSettings(scrnMsg, displayLevel);

        // START 2020/05/12 [QC#51167,ADD]
        int length = scrnMsg.A.getValidCount();
        for (int i = 0; i < length; i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).carrTrkUrl_A1.getValue())) {
                scrnMsg.A.no(i).carrTrkUrl_A1.setInputProtected(false);
            } else {
                scrnMsg.A.no(i).carrTrkUrl_A1.setInputProtected(true);
                EZDGUIAttribute link = new EZDGUIAttribute(BUSINESS_SCREEN_ID,"carrTrk"+i);
                link.setStyleAttribute("color", "black");
                link.setStyleAttribute("text-decoration", "none");
                scrnMsg.addGUIAttribute(link);
            }
        }
        // END 2020/05/12 [QC#51167,ADD]
    }
}
