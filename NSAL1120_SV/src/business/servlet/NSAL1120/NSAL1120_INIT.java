/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1120;

import static business.servlet.NSAL1120.common.NSAL1120CommonLogic.createCMsgForSearch;
import static business.servlet.NSAL1120.common.NSAL1120CommonLogic.initialControlScreen;
import static business.servlet.NSAL1120.constant.NSAL1120Constant.BIZ_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1120.NSAL1120CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         O.Okuma         Create          N/A
 * 2016/04/25   Hitachi         T.Kanasaka      Update          QC#7056
 * 2017/09/15   Hitachi         E.Kameishi      Update          QC#18636
 * 2018/08/27   Hitachi         K.Kishimoto     Update          QC#24555
 *</pre>
 */
public class NSAL1120_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1120BMsg scrnMsg = (NSAL1120BMsg) bMsg;

        NSAL1120CMsg bizMsg = createCMsgForSearch();
        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            int i = 0;

            setValue(scrnMsg.xxModeCd,        (EZDBStringItem) params[i++]);
            setValue(scrnMsg.svcCrRebilPk,    (EZDBBigDecimalItem) params[i++]);
            setValue(scrnMsg.svcCrRebilStsCd, (EZDBStringItem) params[i++]);
            setValue(scrnMsg.svcCrRebilDtlPk, (EZDBBigDecimalItem) params[i++]);
            setValue(scrnMsg.svcInvLinePk,    (EZDBBigDecimalItem) params[i++]);

        } else {
            scrnMsg.xxModeCd.clear();
            scrnMsg.svcCrRebilPk.clear();
            scrnMsg.svcCrRebilStsCd.clear();
            scrnMsg.svcCrRebilDtlPk.clear();
            scrnMsg.svcInvLinePk.clear();
        }
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1120BMsg scrnMsg = (NSAL1120BMsg) bMsg;
        NSAL1120CMsg bizMsg  = (NSAL1120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL1120BMsg scrnMsg = (NSAL1120BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).newBaseDealAmt_A.setNameForMessage("Base Price New");
            // START 2018/08/27 [QC#24555, ADD]
            scrnMsg.A.no(i).newBaseUnitAmt_A.setNameForMessage("Base Price New/Period");
            // END   2018/08/27 [QC#24555, ADD]
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).newStartReadMtrCnt_B.setNameForMessage("New Start Read");
            scrnMsg.B.no(i).newEndReadMtrCnt_B.setNameForMessage("New End Read");
            //START 2017/09/15 E.Kameishi [QC#18636,MOD]
            scrnMsg.B.no(i).newTestMtrCnt_B.setNameForMessage("New Test");
//            scrnMsg.B.no(i).newStartTestMtrCnt_B.setNameForMessage("New Start Test");
//            scrnMsg.B.no(i).newEndTestMtrCnt_B.setNameForMessage("New End Test");
            //END 2017/09/15 E.Kameishi [QC#18636,MOD]
        }

        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).newXsCopyQty_C.setNameForMessage("New Copy Vol");
            scrnMsg.C.no(i).newXsMtrAmtRate_C.setNameForMessage("New Rate");
            // START 2018/08/27 [QC#24555, ADD]
            scrnMsg.C.no(i).newUnitXsCopyQty_C.setNameForMessage("New Copy Vol/Period");
            // END   2018/08/27 [QC#24555, ADD]
        }
    }
}
