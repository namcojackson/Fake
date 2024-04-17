/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0780;

import static business.servlet.NSAL0780.constant.NSAL0780Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0780.NSAL0780CMsg;
import business.servlet.NSAL0780.common.NSAL0780CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Fleet Rollup Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   Hitachi         A.Kohinata      Create          N/A
 * 2017/03/01   Hitachi         K.Kitachi       Update          QC#17752
 *</pre>
 */
public class NSAL0780_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0780BMsg scrnMsg = (NSAL0780BMsg) bMsg;
        NSAL0780CMsg bizMsg = new NSAL0780CMsg();

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params.length == 8) {
                setValue(scrnMsg.dsAcctNm_H, (EZDBStringItem) params[0]);
                setValue(scrnMsg.dsAcctNum_H, (EZDBStringItem) params[1]);
                setValue(scrnMsg.serNum_H, (EZDBStringItem) params[2]);
                setValue(scrnMsg.dsContrNum_H, (EZDBStringItem) params[3]);
                setValue(scrnMsg.bllgFromDt_H, (EZDBDateItem) params[4]);
                setValue(scrnMsg.bllgThruDt_H, (EZDBDateItem) params[5]);
                if (ZYPCommonFunc.hasValue((EZDBStringItem) params[6]) && ZYPConstant.FLG_ON_Y.equals(((EZDBStringItem) params[6]).getValue())) {
                    setValue(scrnMsg.xxChkBox_H1, ZYPConstant.CHKBOX_ON_Y);
                }
                if (ZYPCommonFunc.hasValue((EZDBStringItem) params[7]) && ZYPConstant.FLG_ON_Y.equals(((EZDBStringItem) params[7]).getValue())) {
                    setValue(scrnMsg.xxChkBox_H2, ZYPConstant.CHKBOX_ON_Y);
                }
            }
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0780BMsg scrnMsg = (NSAL0780BMsg) bMsg;
        NSAL0780CMsg bizMsg = (NSAL0780CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0780CommonLogic.initialControlScreen(this, scrnMsg);

        if (scrnMsg.A.getValidCount() > 0) {
            NSAL0780CommonLogic.protectFields(scrnMsg);
            NSAL0780CommonLogic.setRowColors(scrnMsg);
        }
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.dsAcctNm_H);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL0780BMsg scrnMsg = (NSAL0780BMsg) bMsg;
        scrnMsg.dsAcctNm_H.setNameForMessage("Customer Name");
        scrnMsg.dsAcctNum_H.setNameForMessage("Customer#");
        scrnMsg.serNum_H.setNameForMessage("Machine Serial#");
        scrnMsg.dsContrNum_H.setNameForMessage("Contract#");
        scrnMsg.bllgFromDt_H.setNameForMessage("Billing From Date");
        scrnMsg.bllgThruDt_H.setNameForMessage("Billing Thru Date");
        scrnMsg.xxChkBox_H1.setNameForMessage("Meter Read is not completed");
        scrnMsg.xxChkBox_H2.setNameForMessage("Errors Only");
    }
}
