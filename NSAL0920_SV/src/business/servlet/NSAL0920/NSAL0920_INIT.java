/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0920;

import static business.servlet.NSAL0920.constant.NSAL0920Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0920.NSAL0920CMsg;
import business.servlet.NSAL0920.common.NSAL0920CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Contract Billing Search
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         K.Kasai         Create          N/A
 * 2016/10/18   Hitachi         N.Arai          Update          QC#15216
 * 2017/01/23   Hitachi         K.Kitachi       Update          QC#17219
 * 2018/07/04   Hitachi         K.Kitachi       Update          QC#26891
 *</pre>
 */
public class NSAL0920_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0920BMsg scrnMsg = (NSAL0920BMsg) bMsg;
        NSAL0920CMsg bizMsg = new NSAL0920CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_SEARCH);

        // START 2018/07/04 K.Kitachi [QC#26891, ADD]
        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null) {
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof String) {
                    setValue(scrnMsg.dsContrNum, (String) prm[0]);
                }
                if (prm.length > 1 && prm[1] != null && prm[1] instanceof String) {
                    setValue(scrnMsg.xxFromDt, (String) prm[1]);
                    setValue(scrnMsg.xxThruDt, (String) prm[1]);
                }
                if (prm.length > 2 && prm[2] != null && prm[2] instanceof String) {
                    setValue(scrnMsg.xxChkBox_HY, (String) prm[2]);
                }
            }
        }
        // END 2018/07/04 K.Kitachi [QC#26891, ADD]

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0920BMsg scrnMsg = (NSAL0920BMsg) bMsg;
        NSAL0920CMsg bizMsg = (NSAL0920CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0920CommonLogic.initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0920BMsg scrnMsg = (NSAL0920BMsg) bMsg;
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());

        scrnMsg.dsContrNum.setNameForMessage("Contract#");
        scrnMsg.dsAcctNum.setNameForMessage("Customer#");
        scrnMsg.dsAcctNm.setNameForMessage("Customer Name");
        scrnMsg.locNum.setNameForMessage("Bill To Location Num");
        scrnMsg.locNm.setNameForMessage("Bill To Location Name");
        scrnMsg.xxFromDt.setNameForMessage("Interface Date From");
        scrnMsg.xxThruDt.setNameForMessage("Interface Date To");
// START 2016/10/18 N.Arai [QC#15216, MOD]
        scrnMsg.svcContrBrCd_H3.setNameForMessage("Branch Cd");
// START 2016/10/18 N.Arai [QC#15216, MOD]
        // START 2017/01/23 K.Kitachi [QC#17219, ADD]
        scrnMsg.psnCd_H3.setNameForMessage("Supervisor");
        // END 2017/01/23 K.Kitachi [QC#17219, ADD]
    }
}
