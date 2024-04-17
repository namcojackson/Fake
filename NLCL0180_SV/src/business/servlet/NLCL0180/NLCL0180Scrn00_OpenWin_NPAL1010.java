/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0180;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLCL0180.NLCL0180CMsg;
//import business.servlet.NLCL0180.constant.NLCL0180Constant;

import business.servlet.NLCL0180.common.NLCL0180CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/22   Fujitsu         T.Tozuka        Update          R-OM025 Inventory Transaction
 *</pre>
 */
public class NLCL0180Scrn00_OpenWin_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0180BMsg scrnMsg = (NLCL0180BMsg) bMsg;
        NLCL0180CommonLogic.initialize(this, scrnMsg);
        NLCL0180CommonLogic.commonBtnControl_NLCL0180Scrn00_Open_NPAL1010(this);
        NLCL0180CommonLogic.scrnItemControl_NLCL0180Scrn00_Open_NPAL1010(scrnMsg);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mdseNm_A1.setInputProtected(true);
        }

        // Inventory Location Code
        ZYPEZDItemValueSetter.setValue(scrnMsg.invtyLocCd_LI, scrnMsg.whCd_P1);

        // Inventory Location Name
        ZYPEZDItemValueSetter.setValue(scrnMsg.invtyLocNm_LI, scrnMsg.locNm_P1);

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
