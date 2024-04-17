/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7270;

import static business.servlet.NMAL7270.constant.NMAL7270Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7270.common.NMAL7270CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7270Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         M.Nakamura      Create          N/A
 * 2016/06/28   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 * 2017/02/28   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7270Scrn00_CMN_Clear extends S21CommonHandler {

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

        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);

        scrnMsg.prcRuleHdrPk_H1.clear();
        scrnMsg.prcRuleHdrPk_BK.clear();
        scrnMsg.prcRuleNm_H1.clear();
        scrnMsg.prcRuleDescTxt_H1.clear();
        scrnMsg.lineBizTpCd_H1.clear();
        scrnMsg.prcRuleCatgCd_H1.clear();
        scrnMsg.applyAutoFlg_H1.clear();
        scrnMsg.ovrdFlg_H1.clear();
        scrnMsg.actvFlg_H1.clear();
        scrnMsg.effFromDt_H1.clear();
        scrnMsg.effThruDt_H1.clear();
        scrnMsg.defRulePrcdNum_H1.clear();
        scrnMsg.prcRulePrcdPk_H1.clear();     // QC#9694 2016/06/28 Add
        scrnMsg.prcRulePrcdGrpNm_H1.clear();  // QC#9694 2016/06/28 Add

        scrnMsg.xxFullNm_H1.clear();
        scrnMsg.cratDt_H1.clear();
        scrnMsg.xxFullNm_H2.clear();
        scrnMsg.lastUpdDt_H1.clear();

        ZYPTableUtil.clear(scrnMsg.A);
        ZYPTableUtil.clear(scrnMsg.B);

        NMAL7270CommonLogic.initCmnBtnProp(this);
        // Mod Start 2017/02/28 QC#16011
//        NMAL7270CommonLogic.setBtnProp(this, scrnMsg);
//        NMAL7270CommonLogic.setScrnCtrl(scrnMsg);
        NMAL7270CommonLogic.setBtnProp(this, scrnMsg, getUserProfileService());
        NMAL7270CommonLogic.setScrnCtrl(scrnMsg, getUserProfileService());
        // Mod End 2017/02/28 QC#16011

        NMAL7270CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NMAL7270CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");

        scrnMsg.setFocusItem(scrnMsg.prcRuleHdrPk_H1);
    }
}
