/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6840;

import static business.servlet.NMAL6840.constant.NMAL6840Constant.RTL_SWH_SORT_NUM_MAX_VALUE;
import static business.servlet.NMAL6840.constant.NMAL6840Constant.RTL_SWH_SORT_NUM_MIN_VALUE;

import java.util.HashSet;
import java.util.Set;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6840.NMAL6840CMsg;
import business.servlet.NMAL6840.common.NMAL6840CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <p>
 * Business ID : NMAL6840 Sub WH Setup<br>
 * Function Name : The business process for Submit<br>
 * </p>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/27/2015   CITS            M.Ito           Create          N/A
 * </pre>
 */
public class NMAL6840Scrn00_CMN_Submit extends S21CommonHandler {

    /*
     * (non-Javadoc)
     * @see
     * com.canon.cusa.s21.framework.online.servlet.S21CommonHandler
     * #checkInput(parts.servletcommon.EZDApplicationContext,
     * parts.common.EZDBMsg)
     */
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6840BMsg scrnMsg = (NMAL6840BMsg) bMsg;

        checkItemFields(scrnMsg);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.canon.cusa.s21.framework.online.servlet.S21CommonHandler
     * #setRequestData(parts.servletcommon.EZDApplicationContext,
     * parts.common.EZDBMsg)
     */
    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return NMAL6840CommonLogic.copyScrnMsgToBizMsgForUpdate((NMAL6840BMsg) bMsg);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.canon.cusa.s21.framework.online.servlet.S21CommonHandler
     * #doProcess(parts.servletcommon.EZDApplicationContext,
     * parts.common.EZDBMsg, parts.common.EZDCMsg)
     */
    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6840BMsg scrnMsg = (NMAL6840BMsg) bMsg;
        EZDMsg.copy((NMAL6840CMsg) cMsg, null, scrnMsg, null);

        // Checks the items fields.
        checkItemFields(scrnMsg);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        NMAL6840CommonLogic.doProcessOfSearch(scrnMsg, this);
    }

    /**
     * <p>
     * Checks the item fields.
     * <ul>
     * <li>mandatory check</li>
     * <li>type check</li>
     * <li>duplication check</li>
     * <li>range check</li>
     *</ul>
     * </p>
     * @param scrnMsg
     */
        // Sub WH Codes for duplication check.
    private void checkItemFields(NMAL6840BMsg scrnMsg) {
        Set<String> rtlSwhCds = new HashSet<String>(scrnMsg.A.getValidCount());

        for (int rowIndex = 0; rowIndex < scrnMsg.A.getValidCount(); rowIndex++) {

            NMAL6840_ABMsg scrnRow = scrnMsg.A.no(rowIndex);

            // mandatory and type check.
            scrnMsg.addCheckItem(scrnRow.rtlSwhCd_A1);
            scrnMsg.addCheckItem(scrnRow.rtlSwhNm_A1);
//            scrnMsg.addCheckItem(scrnRow.rtlSwhDescTxt_A1);
//            scrnMsg.addCheckItem(scrnRow.mdseCostTpCd_A1);
//            scrnMsg.addCheckItem(scrnRow.mdseInvtyCostPct_A1);
//            scrnMsg.addCheckItem(scrnRow.rtlSwhSortNum_A1);
//            scrnMsg.addCheckItem(scrnRow.rtlSwhDsblFlg_A1);

            // duplication check.
            NMAL6840CommonLogic.checkIsDuplicated(scrnRow.rtlSwhCd_A1, rtlSwhCds);

            // range check.
//            NMAL6840CommonLogic.checkIsWithinRange(scrnRow.mdseInvtyCostPct_A1, MDSE_INVTY_COST_PCT_MIN_VALUE, MDSE_INVTY_COST_PCT_MAX_VALUE);
            NMAL6840CommonLogic.checkIsWithinRange(scrnRow.rtlSwhSortNum_A1, RTL_SWH_SORT_NUM_MIN_VALUE, RTL_SWH_SORT_NUM_MAX_VALUE);
        }

        scrnMsg.putErrorScreen();
    }
}
