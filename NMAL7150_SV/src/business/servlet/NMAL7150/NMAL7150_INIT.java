/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7150;

import static business.servlet.NMAL7150.constant.NMAL7150Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7150.common.NMAL7150CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * CSMP Contract Synchronization  Errors Correction
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7150_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7150BMsg scrnMsg = (NMAL7150BMsg) bMsg;

        NMAL7150CommonLogic.initControlScreen(this, scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL7150BMsg scrnMsg = (NMAL7150BMsg) bMsg;

        scrnMsg.effFromDt_TD.setNameForMessage("Transactioni Date From");
        scrnMsg.effThruDt_TD.setNameForMessage("Transaction Date To");
        scrnMsg.xxChkBox_E.setNameForMessage("Process Flag Error");
        scrnMsg.xxChkBox_R.setNameForMessage("Process Flag Reprocess");
        scrnMsg.xxChkBox_D.setNameForMessage("Process Flag Delete");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NMAL7150_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.xxChkBox_A.setNameForMessage("Check box");
            abMsg.dlrRefNum_A.setNameForMessage("Dealer Ref Num");
            abMsg.prevCsmpNum_A.setNameForMessage("Prev Contract");
            abMsg.prevUsrTxt_A.setNameForMessage("Prev End User");
        }

    }
}
