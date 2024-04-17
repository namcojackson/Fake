/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0120;

import static business.servlet.NFDL0120.constant.NFDL0120Constant.*;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0120.NFDL0120CMsg;
import business.servlet.NFDL0120.common.NFDL0120CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Hitachi         K.Kojima        Create          N/A
 * 2016/07/19   Hitachi         K.Kojima        Update          QC#10188
 *</pre>
 */
public class NFDL0120_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0120BMsg scrnMsg = (NFDL0120BMsg) bMsg;

        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null) {
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBStringItem) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cltStrgyCd, ((EZDBStringItem) prm[0]).getValue());
                }
            }
        }

        NFDL0120CMsg bizMsg = new NFDL0120CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0120BMsg scrnMsg = (NFDL0120BMsg) bMsg;
        NFDL0120CMsg bizMsg = (NFDL0120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFDL0120CommonLogic.setupScreenItems(this, scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFDL0120BMsg scrnMsg = (NFDL0120BMsg) bMsg;

        for (int num = 0; num < scrnMsg.A.length(); num++) {
            scrnMsg.A.no(num).cltWrkItemCd_SV.setNameForMessage("Work Item Code");
            // START 2016/07/19 K.Kojima [QC#10188,ADD]
            scrnMsg.A.no(num).cltWrkItemSortNum_A.setNameForMessage("Sort Number");
            // END 2016/07/19 K.Kojima [QC#10188,ADD]
        }

    }
}
