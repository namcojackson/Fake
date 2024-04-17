/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7270;

import static business.servlet.NMAL7270.constant.NMAL7270Constant.BIZ_ID;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.NMAM0025E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7270.NMAL7270CMsg;
import business.servlet.NMAL7270.common.NMAL7270CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7270Scrn00_OpenWin_CondGrpRules
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/28   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7270Scrn00_OpenWin_CondGrpRules extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7270BMsg scrnMsg = (NMAL7270BMsg) bMsg;
        NMAL7270CommonLogic.checkMandatoryCmn(scrnMsg);
        NMAL7270CommonLogic.checkBusinessCmn(scrnMsg);

        // Add Start 2017/02/28 QC#16011
        boolean updateAuthFlg = NMAL7270CommonLogic.updateAuthority(getUserProfileService());
        if (!updateAuthFlg && !ZYPCommonFunc.hasValue(scrnMsg.prcRuleHdrPk_BK)) {
            scrnMsg.setMessageInfo(NMAM0025E);
        }
        scrnMsg.putErrorScreen();
        // Add End 2017/02/28 QC#16011
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7270BMsg scrnMsg = (NMAL7270BMsg) bMsg;
        // Add Start 2017/02/28 QC#16011
        boolean updateAuthFlg = NMAL7270CommonLogic.updateAuthority(getUserProfileService());

        if (!updateAuthFlg) {
            return null;
        }
        // Add End 2017/02/28 QC#16011

        NMAL7270CMsg bizMsg = new NMAL7270CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // Mod Start 2017/03/01 QC#16011
        NMAL7270BMsg scrnMsg = (NMAL7270BMsg) bMsg;
        NMAL7270CMsg bizMsg = (NMAL7270CMsg) cMsg;
        boolean updateAuthFlg = NMAL7270CommonLogic.updateAuthority(getUserProfileService());
        if (updateAuthFlg) {

            if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            }

            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        // Mod Start 2017/03/01 QC#16011

        // Mod Start 2017/03/01 QC#16011
//        NMAL7270CommonLogic.allAttrbCtrl(this, scrnMsg);
        NMAL7270CommonLogic.allAttrbCtrl(this, scrnMsg, getUserProfileService());
        // Mod End 2017/03/01 QC#16011

        scrnMsg.addCheckItem(scrnMsg.prcRuleNm_H1);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCatgCd_H1);
        scrnMsg.addCheckItem(scrnMsg.lineBizTpCd_H1);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleCondFromTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleCondThruTxt_A1);
        }
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondGrpCd_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleAdjTpCd_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleModTpCd_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).effThruDt_B1);
        }
        scrnMsg.putErrorScreen();

        Object[] params = new Object[1];
        params[0]  = scrnMsg.prcRuleHdrPk_BK;

        setArgForSubScreen(params);
        openMultiModeScreen();
    }
}
