/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0040;

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
 * 2013/05/21   CUSA            Fujitsu         Create          R-OM025 Inventory Transaction
 *</pre>
 */
public class NLBL0040Scrn00_OpenWin_Location_Info extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL0040BMsg scrnMsg = (NLBL0040BMsg) bMsg;

        // Inventory Location Code
        ZYPEZDItemValueSetter.setValue(scrnMsg.invtyLocCd_P2, scrnMsg.whCd_H2);

        // Inventory Location Name
        ZYPEZDItemValueSetter.setValue(scrnMsg.invtyLocNm_P2, scrnMsg.locNm_H2);

        // Data Security Flag
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkInpValFlg_P2, ZYPConstant.FLG_ON_Y);
        // Virtual WH Flag
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSelFlg_P2, ZYPConstant.FLG_OFF_N);
        // Location Role Type Code(Return)
        scrnMsg.locRoleTpCd_P2.clear();

        // Create Parameter
        Object[] param = new Object[]{scrnMsg.invtyLocCd_P2,
                                        scrnMsg.invtyLocNm_P2,
                                        scrnMsg.xxLocRoleTpCdListTxt_P2,
                                        scrnMsg.xxChkInpValFlg_P2,
                                        scrnMsg.xxSelFlg_P2,
                                        scrnMsg.locRoleTpCd_P2};

        // Call Popup
        setArgForSubScreen(param);
    }
}
