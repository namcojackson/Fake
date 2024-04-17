/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/24   Fujitsu         T.Tozuka        Create          R-OM025 Inventory Transaction
 *</pre>
 */
public class NLBL0110Scrn00_OpenWin_Location_Info extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL0110BMsg scrnMsg = (NLBL0110BMsg) bMsg;

        // Inventory Location Code
        ZYPEZDItemValueSetter.setValue(scrnMsg.invtyLocCd_LI, scrnMsg.whCd_H2);

        // Inventory Location Name
        ZYPEZDItemValueSetter.setValue(scrnMsg.invtyLocNm_LI, scrnMsg.locNm_H2);

        // Data Security Flag
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkInpValFlg_LI, ZYPConstant.FLG_ON_Y);
        // Virtual WH Flag
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSelFlg_LI, ZYPConstant.FLG_ON_Y);
        // Location Role Type Code(Return)
        scrnMsg.locRoleTpCd_LI.clear();

        // Create Parameter
        Object[] param = new Object[]{scrnMsg.invtyLocCd_LI,
                                        scrnMsg.invtyLocNm_LI,
                                        scrnMsg.xxLocRoleTpCdListTxt_LI,
                                        scrnMsg.xxChkInpValFlg_LI,
                                        scrnMsg.xxSelFlg_LI,
                                        scrnMsg.locRoleTpCd_LI};

        // Call Popup
        setArgForSubScreen(param);
    }
}
