/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/18   Hitachi         T.Kanasaka      Create          QC3270
 * 2018/05/15   Hitachi         K.Kitachi       Update          QC#24265
 *</pre>
 */
public class NSAL0300Scrn00_ChangBaseChrgToLeaseCmpyFlg extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.baseChrgToLeaseCmpyFlg.getValue())) {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).baseBillToCustCd_B, scrnMsg.leaseCmpyCd);
            }
        }

        // START 2018/05/15 K.Kitachi [QC#24265, ADD]
        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.baseChrgToLeaseCmpyFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.usgChrgToLeaseCmpyFlg.getValue())) {
            scrnMsg.cfsLeaseNumCmntTxt.clear();
        }
        // END 2018/05/15 K.Kitachi [QC#24265, ADD]

        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.usgChrgToLeaseCmpyFlg);
    }
}
