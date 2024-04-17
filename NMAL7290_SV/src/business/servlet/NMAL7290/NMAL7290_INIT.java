/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7290;

import static business.servlet.NMAL7290.constant.NMAL7290Constant.BIZ_ID;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.FUNC_CD_20;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.MSG_PRC_RULE_PRCD_GRP_NM;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.MSG_PRC_RULE_PRCD_GRP_NUM;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.TBL_B1;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7290.NMAL7290CMsg;
import business.servlet.NMAL7290.common.NMAL7290CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL7290_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   Fujitsu         C.Tanaka        Create          N/A
 * 2016/07/08   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 *</pre>
 */
public class NMAL7290_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7290BMsg scrnMsg = (NMAL7290BMsg) bMsg;
        NMAL7290CMsg bizMsg = new NMAL7290CMsg();

        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == 1) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRulePrcdGrpNum, (EZDBBigDecimalItem) params[1]);
            // QC#9694 2016/07/08 Mod Start
            //ZYPEZDItemValueSetter.setValue(scrnMsg.prcRulePrcdGrpNum, (EZDBBigDecimalItem) params[0]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRulePrcdPk, (EZDBBigDecimalItem) params[0]);
            // QC#9694 2016/07/08 Mod End
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7290BMsg scrnMsg = (NMAL7290BMsg) bMsg;
        NMAL7290CMsg bizMsg = (NMAL7290CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
//            throw new EZDFieldErrorException();
            NMAL7290CommonLogic.initCmnBtnProp(this, scrnMsg);
            NMAL7290CommonLogic.ctrlCmnBtnProp(this, getUserProfileService(), scrnMsg);
            NMAL7290CommonLogic.ctrlBtnProp(this, scrnMsg);
            NMAL7290CommonLogic.ctrlInputProp(scrnMsg);
            NMAL7290CommonLogic.setTblColor(scrnMsg, TBL_B1);
            return;
        }

        NMAL7290CommonLogic.initCmnBtnProp(this, scrnMsg);
        NMAL7290CommonLogic.ctrlCmnBtnProp(this, getUserProfileService(), scrnMsg);
        NMAL7290CommonLogic.ctrlBtnProp(this, scrnMsg);
        NMAL7290CommonLogic.ctrlInputProp(scrnMsg);
        NMAL7290CommonLogic.setTblColor(scrnMsg, TBL_B1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7290BMsg scrnMsg = (NMAL7290BMsg) bMsg;

        // QC#9694 2016/07/08 Mod Start
        //scrnMsg.prcRulePrcdGrpNum.setNameForMessage(MSG_PRC_RULE_PRCD_GRP_NUM);
        scrnMsg.prcRulePrcdPk.setNameForMessage(MSG_PRC_RULE_PRCD_GRP_NUM);
        // QC#9694 2016/07/08 Mod End
        scrnMsg.prcRulePrcdGrpNm.setNameForMessage(MSG_PRC_RULE_PRCD_GRP_NM);
        // QC#9694 2016/07/08 Add Start
        scrnMsg.prcPrcdActTpCd.setNameForMessage("Precedence Action");
        scrnMsg.actvFlg.setNameForMessage("Active");
        scrnMsg.effFromDt.setNameForMessage("Effective Date From");
        scrnMsg.effThruDt.setNameForMessage("Effective Date To");
        // QC#9694 2016/07/08 Add End

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).prcRulePrcdSqNum_B.setNameForMessage("Seq");
            // QC#9694 2016/07/08 Del Start
            // scrnMsg.B.no(i).prcRuleOpTpCd_B.setNameForMessage("Operator");
            // scrnMsg.B.no(i).prcRuleEvtrTpCd_B.setNameForMessage("Evaluator");
            // QC#9694 2016/07/08 Del Start
        }
    }

}
