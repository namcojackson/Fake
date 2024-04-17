/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7270;

import static business.servlet.NMAL7270.constant.NMAL7270Constant.BIZ_ID;

import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7270.NMAL7270CMsg;
import business.servlet.NMAL7270.common.NMAL7270CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL7270_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         M.Nakamura      Create          N/A
 * 2015/04/06   Fujitsu         Y.Kanefusa      Update          QC#6397
 * 2016/02/28   Fujitsu         R.Nakamura      Update          QC#16011
 * 2018/09/12   Fujitsu         M.Ohno          Upadte          QC#9700
 *</pre>
 */
public class NMAL7270_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7270BMsg scrnMsg = (NMAL7270BMsg) bMsg;
        NMAL7270CMsg bizMsg = new NMAL7270CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null) {
            if (params[0] instanceof EZDBBigDecimalItem) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleHdrPk_BK, (EZDBBigDecimalItem) params[0]);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleHdrPk_BK, (BigDecimal) params[0]);
            }
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7270BMsg scrnMsg = (NMAL7270BMsg) bMsg;
        NMAL7270CMsg bizMsg = (NMAL7270CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7270CommonLogic.initCmnBtnProp(this);
        NMAL7270CommonLogic.initScrnProtect(scrnMsg);
        // Mod Start 2017/02/28 QC#16011
//        NMAL7270CommonLogic.setBtnProp(this, scrnMsg);
//        NMAL7270CommonLogic.setScrnCtrl(scrnMsg);
//        NMAL7270CommonLogic.allAttrbCtrl(this, scrnMsg);
        NMAL7270CommonLogic.setBtnProp(this, scrnMsg, getUserProfileService());
        NMAL7270CommonLogic.setScrnCtrl(scrnMsg, getUserProfileService());
        NMAL7270CommonLogic.allAttrbCtrl(this, scrnMsg, getUserProfileService());
        // Mod End 2017/02/28 QC#16011

        NMAL7270CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NMAL7270CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");

        setAppFracDigit(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.prcRuleHdrPk_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7270BMsg scrnMsg = (NMAL7270BMsg) bMsg;

        scrnMsg.prcRuleHdrPk_H1.setNameForMessage("Rule ID");
        scrnMsg.prcRuleNm_H1.setNameForMessage("Rule Name");
        scrnMsg.prcRuleDescTxt_H1.setNameForMessage("Description");
        scrnMsg.lineBizTpCd_H1.setNameForMessage("Line of Business");
        scrnMsg.prcRuleCatgCd_H1.setNameForMessage("Rule  Category");
        scrnMsg.applyAutoFlg_H1.setNameForMessage("Apply automatically");
        scrnMsg.ovrdFlg_H1.setNameForMessage("Override");
        scrnMsg.actvFlg_H1.setNameForMessage("Active");
        scrnMsg.effFromDt_H1.setNameForMessage("Effective Date From");
        scrnMsg.effThruDt_H1.setNameForMessage("Effective Date To");
        scrnMsg.defRulePrcdNum_H1.setNameForMessage("Default Rule Precedence#");

        scrnMsg.xxFullNm_H1.setNameForMessage("Created By");
        scrnMsg.cratDt_H1.setNameForMessage("Creation Date");
        scrnMsg.xxFullNm_H2.setNameForMessage("Last Updated By");
        scrnMsg.lastUpdDt_H1.setNameForMessage("Last Update Date");
        // 2018/09/12 QC#9700 add start
        scrnMsg.prcGrpTrxTpCd_H1.setNameForMessage("Transaction Type");
        // 2018/09/12 QC#9700 add start

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage("Check Box");
            scrnMsg.A.no(i).prcRuleTrxCatgCd_A1.setNameForMessage("Category");
            scrnMsg.A.no(i).prcRuleAttrbCd_A1.setNameForMessage("Attribute");
            scrnMsg.A.no(i).prcRuleCondFromTxt_A1.setNameForMessage("Value From");
            scrnMsg.A.no(i).xxRecNmTxt_A1.setNameForMessage("Value From Name");
            scrnMsg.A.no(i).prcRuleCondThruTxt_A1.setNameForMessage("Value To");
            scrnMsg.A.no(i).xxFromDt_A1.setNameForMessage("Value From");
            scrnMsg.A.no(i).xxThruDt_A1.setNameForMessage("Value To");
            scrnMsg.A.no(i).prcRuleCondNum_A1.setNameForMessage("Condition#");
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage("Effective Date From");
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage("Effective Date To");
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).xxChkBox_B1.setNameForMessage("Check Box");
            scrnMsg.B.no(i).prcRuleCondGrpCd_B1.setNameForMessage("Adj Con");
            scrnMsg.B.no(i).prcRuleDtlChrgNm_B1.setNameForMessage("Charge Name");
            scrnMsg.B.no(i).prcRuleModTpCd_B1.setNameForMessage("Modify");
            scrnMsg.B.no(i).prcRuleAdjTpCd_B1.setNameForMessage("Adjustment");
            scrnMsg.B.no(i).prcRuleAttrbCd_B1.setNameForMessage("Type");
            scrnMsg.B.no(i).prcRuleAdjLvlCd_B1.setNameForMessage("Adj Level");
            scrnMsg.B.no(i).prcFmlaPk_B1.setNameForMessage("Value");
            scrnMsg.B.no(i).prcRuleDtlRate_B1.setNameForMessage("Value");
            scrnMsg.B.no(i).prcRuleDtlTxt_B1.setNameForMessage("Value");

            scrnMsg.B.no(i).effFromDt_B1.setNameForMessage("Effective Date From");
            scrnMsg.B.no(i).effThruDt_B1.setNameForMessage("Effective Date To");
        }
    }
    private void setAppFracDigit(NMAL7270BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).prcRuleDtlRate_B1.setAppFracDigit(2);
        }
    }

}
