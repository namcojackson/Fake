/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Return Action from NMAL6800(Item Search Popup)
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/17/2016   CITS            Makoto Okigami  Create          N/A
 * 12/12/2018   Fujitsu         S.Takami        Update          QC#29456
 *</pre>
 */
public class NPBL0020_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // 2018/12/12 QC#29456 Del Start
//        // There is no processing.
//
//        return null;
        // 2018/12/12 QC#29456 Del End
        // 2018/12/12 QC#29456 Add Start
        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            NPBL0020CMsg bizMsg = new NPBL0020CMsg();
            bizMsg.setBusinessID(BIZ_APP_ID);
            bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;

        } else {
            return null;
        }
        // 2018/12/12 QC#29456 Add End
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            int eventRowIndex = getButtonSelectNumber();
            scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).mdseCd_A1);
        }
        // 2018/12/12 QC#29456 Add Start
        if (cMsg != null) {
            NPBL0020CMsg bizMsg = (NPBL0020CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            int index = getButtonSelectNumber();
            scrnMsg.addCheckItem(scrnMsg.A.no(index).xxScrItem50Txt_A1);
            scrnMsg.putErrorScreen();
        }
        // 2018/12/12 QC#29456 Add End
    }
}
