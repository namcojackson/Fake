/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0810;

import static business.servlet.NSAL0810.common.NSAL0810CommonLogic.checkInputForTable;
import static business.servlet.NSAL0810.common.NSAL0810CommonLogic.initialControlScreen;
import static business.servlet.NSAL0810.constant.NSAL0810Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0810.NSAL0810CMsg;
import business.servlet.NSAL0810.common.NSAL0810CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Interface Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/04/04   Hitachi         T.Iwamoto       Update          QC#5822
 * 2016/06/24   Hitachi         Y.Tsuchimoto    Update          QC#10826
 *</pre>
 */
public class NSAL0810Scrn00_ValidateData extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;
        NSAL0810CommonLogic.addCheckItem(scrnMsg, false);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;

        NSAL0810CMsg bizMsg = new NSAL0810CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;
        NSAL0810CMsg bizMsg = (NSAL0810CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        initialControlScreen(this, scrnMsg);
        checkInputForTable(this, scrnMsg);
        EZDMessageInfo info = bizMsg.getMessageInfo();
        if (info != null && !"NSAM0015E".equals(info.getCode()) && !"NSAM0408E".equals(info.getCode())) {
            // START 2016/06/24 [QC#10826, MOD]
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                // MOD start 2016/04/04 CSA Defect#5822
                if (hasValue(scrnMsg.A.no(i).intfcErrMsgTxt_A) && ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).intfcErrMsgTxt_A);
                    scrnMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0541E);
                }
                // MOD end 2016/04/04 CSA Defect#5822
            }
            // END   2016/06/24 [QC#10826, MOD]
        }
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }
}
