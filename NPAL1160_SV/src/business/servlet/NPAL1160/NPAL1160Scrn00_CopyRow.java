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
import static business.servlet.NPAL1160.constant.NPAL1160Constant.FUNCTION_CD_SEARCH;
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
 * 11/29/2016   CITS            K.Kameoka       Update          QC#16149
 *</pre>
 */
public class NPAL1160Scrn00_CopyRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1160BMsg scrnMsg = (NPAL1160BMsg) bMsg;

        if (DISPLAY_TAB_NM_TEAM.equals(scrnMsg.xxDplyTab.getValue())) {
            NPAL1160CommonLogic.checkSelectedLineA(scrnMsg);
        } else if (DISPLAY_TAB_NM_MEMBER.equals(scrnMsg.xxDplyTab.getValue())) {
            NPAL1160CommonLogic.checkSelectedLineB(scrnMsg);
        } else if (DISPLAY_TAB_NM_TRANSACTION.equals(scrnMsg.xxDplyTab.getValue())) {
            NPAL1160CommonLogic.checkSelectedLineC(scrnMsg);
        } else if (DISPLAY_TAB_NM_LOCATION.equals(scrnMsg.xxDplyTab.getValue())) {
            NPAL1160CommonLogic.checkSelectedLineD(scrnMsg);
        } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(scrnMsg.xxDplyTab.getValue())) {
            NPAL1160CommonLogic.checkSelectedLineE(scrnMsg);
        }
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

        // QC#16149 Start
        if (!"E".equals(bizMsg.getMessageKind())) {
            // QC#16149 End
            if (DISPLAY_TAB_NM_TEAM.equals(scrnMsg.xxDplyTab.getValue())) {
                NPAL1160CommonLogic.controlItemsCopyLineTeam(this, scrnMsg, bizMsg.xxCellIdx.getValue());

            } else if (DISPLAY_TAB_NM_MEMBER.equals(scrnMsg.xxDplyTab.getValue())) {
                NPAL1160CommonLogic.controlItemsCopyLineMember(this, scrnMsg, bizMsg.xxCellIdx.getValue());

            } else if (DISPLAY_TAB_NM_TRANSACTION.equals(scrnMsg.xxDplyTab.getValue())) {
                NPAL1160CommonLogic.controlItemsCopyLineTransaction(this, scrnMsg, bizMsg.xxCellIdx.getValue());

            } else if (DISPLAY_TAB_NM_LOCATION.equals(scrnMsg.xxDplyTab.getValue())) {
                NPAL1160CommonLogic.controlItemsCopyLineLocation(this, scrnMsg, bizMsg.xxCellIdx.getValue());

            } else if (DISPLAY_TAB_NM_APVLLIMIT.equals(scrnMsg.xxDplyTab.getValue())) {
                NPAL1160CommonLogic.controlItemsCopyLineApvlLimit(this, scrnMsg, bizMsg.xxCellIdx.getValue());

            }
            // QC#16149 Start
        }
        // QC#16149 End
    }
}
