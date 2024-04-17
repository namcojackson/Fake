/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0410;

import static business.servlet.NSAL0410.constant.NSAL0410Constant.BIZ_ID;
import static business.servlet.NSAL0410.constant.NSAL0410Constant.EDIT_MODE;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0410.NSAL0410CMsg;
import business.servlet.NSAL0410.common.NSAL0410CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Additional Charge
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/24   Fujitsu         C.Naito         Create          N/A
 * 2015/11/26   Hitachi         T.Tomita        Update          N/A
 * 2016/02/16   Hitachi         K.Kasai         Update          QC#3021
 * 2018/10/09   Hitachi         K.Kitachi       Update          QC#28603
 *</pre>
 */
public class NSAL0410_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;

        // for Delete Event
        scrnMsg.xxPgFlg_DE.clear();

        // Security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;

        NSAL0410CMsg bizMsg = new NSAL0410CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            NSAL0410CommonLogic.setInputParam(this, scrnMsg, (Object[]) arg);
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;
        NSAL0410CMsg bizMsg = (NSAL0410CMsg) cMsg;
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NSAL0410CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID(), getGlobalCompanyCode(), true);
        // set Focus
        scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A);
        // START 2018/10/09 K.Kitachi [QC#28603, ADD]
        // cut digit
        setAppFracDigit(scrnMsg);
        // END 2018/10/09 K.Kitachi [QC#28603, ADD]

        // Reference Mode
        if (!EDIT_MODE.equals(scrnMsg.xxModeCd_P.getValue())) {
            // All inactive
            NSAL0410CommonLogic.initCommonButton(this, scrnMsg);
            NSAL0410CommonLogic.allFieldsInactive(this, scrnMsg);
            return;
        }

        // has error
        if (scrnMsg.getMessageCode().endsWith("E")) {
            throw new EZDFieldErrorException();
        }
        // START 2018/10/09 K.Kitachi [QC#28603, DEL]
        // cut digit
//        setAppFracDigit(scrnMsg);
        // END 2018/10/09 K.Kitachi [QC#28603, DEL]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).addlChrgTpCd_SE.setNameForMessage("Charge Type");
            scrnMsg.A.no(i).effFromDt_A.setNameForMessage("Start Date");
            scrnMsg.A.no(i).effThruDt_A.setNameForMessage("End Date");
            scrnMsg.A.no(i).trmnDt_A.setNameForMessage("Terminate Date");
            scrnMsg.A.no(i).bllgCycleCd_SE.setNameForMessage("Frequency");
            scrnMsg.A.no(i).svcBillByTpCd_SE.setNameForMessage("Bill By");
            scrnMsg.A.no(i).addlChrgFlatDealPrcAmt_A.setNameForMessage("Flat Price");
            scrnMsg.A.no(i).addlChrgAplcPct_A.setNameForMessage("Applicable%");
            scrnMsg.A.no(i).addlChrgInvTpCd_SE.setNameForMessage("Invoice Type");
        }
    }

    private void setAppFracDigit(EZDBMsg bMsg) {
        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).addlChrgFlatDealPrcAmt_A.setAppFracDigit(2);
            // mod start 2016/02/15 CSA Defect#3021
            scrnMsg.A.no(i).addlChrgAplcPct_A.setAppFracDigit(2);
            // mod end 2016/02/15 CSA Defect#3021
        }
    }
}
