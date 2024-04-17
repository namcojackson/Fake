/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0140;

import static business.servlet.NFDL0140.constant.NFDL0140Constant.*;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0140.NFDL0140CMsg;
import business.servlet.NFDL0140.common.NFDL0140CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public class NFDL0140_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0140BMsg scrnMsg = (NFDL0140BMsg) bMsg;

        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null) {
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBStringItem) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cltStrgyCd, ((EZDBStringItem) prm[0]).getValue());
                }
            }
        }

        NFDL0140CMsg bizMsg = new NFDL0140CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0140BMsg scrnMsg = (NFDL0140BMsg) bMsg;
        NFDL0140CMsg bizMsg = (NFDL0140CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFDL0140CommonLogic.setupScreenItems(this, scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFDL0140BMsg scrnMsg = (NFDL0140BMsg) bMsg;

        for (int num = 0; num < scrnMsg.A.length(); num++) {
            scrnMsg.A.no(num).cltOverDueRangeLowAmt_A.setNameForMessage("Balance Low");
            scrnMsg.A.no(num).cltOverDueRangeHighAmt_A.setNameForMessage("Balance High");
            scrnMsg.A.no(num).cltCustTpCd_SV.setNameForMessage("Customer Type");
        }

    }
}
