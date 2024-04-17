/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7270;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7270.common.NMAL7270CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7270Scrn00_OnChange_Attrb_PrcAdjDtl
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         M.Nakamura      Create          N/A
 * 2016/05/19   Fujitsu         Y.Kanefusa      Update          N/A
 * 2017/03/01   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7270Scrn00_OnChange_Attrb_PrcAdjDtl extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7270BMsg scrnMsg = (NMAL7270BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();

        scrnMsg.B.no(selectIdx).prcFmlaPk_B1.clear();
        // QC#6530 2016/05/19 Add Start
        scrnMsg.B.no(selectIdx).prcFmlaNm_B1.clear();
        // QC#6530 2016/05/19 Add End
        scrnMsg.B.no(selectIdx).prcRuleDtlRate_B1.clear();
        scrnMsg.B.no(selectIdx).prcRuleDtlTxt_B1.clear();
        if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIdx).effFromDt_B1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIdx).effFromDt_B1, scrnMsg.slsDt);
        }

        // Mod Start 2017/03/01 QC#16011
//        NMAL7270CommonLogic.dtlAttrbCtrl(this, selectIdx, scrnMsg);
        boolean updateAuthFlg = NMAL7270CommonLogic.updateAuthority(getUserProfileService());
        NMAL7270CommonLogic.dtlAttrbCtrl(this, selectIdx, scrnMsg, updateAuthFlg);
        // Mod End 2017/03/01 QC#16011
   }
}
