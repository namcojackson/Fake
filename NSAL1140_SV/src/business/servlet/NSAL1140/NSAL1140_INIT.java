/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1140;


import static business.servlet.NSAL1140.constant.NSAL1140Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1140.NSAL1140CMsg;
import business.servlet.NSAL1140.common.NSAL1140CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Supply Watch.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Hitachi         T.Nishimura        Create          N/A
 *</pre>
 */
public class NSAL1140_INIT extends S21INITCommonHandler {

    /**
     * 
     */
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1140BMsg scrnMsg = (NSAL1140BMsg) bMsg;
        // add start 2016/02/24 CSA Defect#4122
        String dsContrNum = null;

        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] params = (Object[]) ser;
            if (params != null) {
                if (params.length > 0 && params[0] != null && params[0] instanceof EZDBStringItem) {
                    dsContrNum = ((EZDBStringItem) params[0]).getValue();
                } else {
                    scrnMsg.setMessageInfo(NSAM0131E, new String[]{"Contract#"});
                    throw new EZDFieldErrorException();
                }
            }
        }
        setValue(scrnMsg.dsContrNum, dsContrNum);
        // add end 2016/02/24 CSA Defect#4122
        NSAL1140CMsg bizMsg = new NSAL1140CMsg();

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1140BMsg scrnMsg = (NSAL1140BMsg) bMsg;
        NSAL1140CMsg bizMsg  = (NSAL1140CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL1140CommonLogic.initialControlScreen(this, scrnMsg);
    }


    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL1140BMsg scrnMsg = (NSAL1140BMsg) bMsg;
        scrnMsg.shipToCustAcctCd_01.setNameForMessage("Customer#");
        scrnMsg.dsAcctNm_01.setNameForMessage("Customer Name");
        scrnMsg.svcContrBrCd.setNameForMessage("Branch");
        scrnMsg.dsAcctGrpNm.setNameForMessage("Customer Profile Name");
        scrnMsg.procDt_01.setNameForMessage("Date From");
        scrnMsg.procDt_02.setNameForMessage("Date To");
        scrnMsg.dsContrNum.setNameForMessage("Contract#");
        scrnMsg.srchOptNm_02.setNameForMessage("New Search Options");
        scrnMsg.abuseVarPct.setNameForMessage("CAP%");
        scrnMsg.abuseActCmntTxt.setNameForMessage("Notes");
        scrnMsg.svcSplyExprDt.setNameForMessage("Expiration Date");

    }

}
