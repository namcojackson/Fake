/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7270;

import static business.servlet.NMAL7270.constant.NMAL7270Constant.BIZ_ID;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.MESSAGE_KIND_ERROR;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7270.NMAL7270CMsg;
import business.servlet.NMAL7270.common.NMAL7270CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7270Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         M.Nakamura      Create          N/A
 * 2017/02/28   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7270Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7270BMsg scrnMsg = (NMAL7270BMsg) bMsg;
        NMAL7270CommonLogic.checkMandatoryCmn(scrnMsg);
        NMAL7270CommonLogic.checkBusinessCmn(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7270BMsg scrnMsg = (NMAL7270BMsg) bMsg;

        NMAL7270CMsg bizMsg = new NMAL7270CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7270BMsg scrnMsg = (NMAL7270BMsg) bMsg;
        NMAL7270CMsg bizMsg = (NMAL7270CMsg) cMsg;

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.prcRuleNm_H1);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCatgCd_H1);
        scrnMsg.addCheckItem(scrnMsg.lineBizTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.prcRulePrcdGrpNm_H1);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleCondFromTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleCondThruTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxFromDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxThruDt_A1);
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondGrpCd_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleAdjTpCd_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleModTpCd_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleAdjLvlCd_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleAttrbCd_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcFmlaPk_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleDtlRate_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleDtlTxt_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).effThruDt_B1);
        }
        scrnMsg.putErrorScreen();

        // Mod Start 2017/02/28 QC#16011
//        NMAL7270CommonLogic.allAttrbCtrl(this, scrnMsg);
//        NMAL7270CommonLogic.setBtnProp(this, scrnMsg);
        NMAL7270CommonLogic.allAttrbCtrl(this, scrnMsg, getUserProfileService());
        NMAL7270CommonLogic.setBtnProp(this, scrnMsg, getUserProfileService());
        // Mod End 2017/02/28 QC#16011

        NMAL7270CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NMAL7270CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");
    }
}