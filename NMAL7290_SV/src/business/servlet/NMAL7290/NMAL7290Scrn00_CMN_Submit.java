/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7290;

import static business.servlet.NMAL7290.constant.NMAL7290Constant.BIZ_ID;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.FUNC_CD_40;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.MSG_OP;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.MSG_PRC_RULE_PRCD_GRP_NM;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.MSG_PRC_RULE_PRCD_GRP_NUM;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.NMAM0836E;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.NZZM0002I;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.TBL_B1;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7290.NMAL7290CMsg;
import business.servlet.NMAL7290.common.NMAL7290CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_PRCD_ACT_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7290Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   Fujitsu         C.Tanaka        Create          N/A
 * 2016/07/08   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 *</pre>
 */
public class NMAL7290Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7290BMsg scrnMsg = (NMAL7290BMsg) bMsg;

        // QC#9694 2016/07/08 Mod Start
        //if (!ZYPCommonFunc.hasValue(scrnMsg.prcRulePrcdGrpNum)) {
        //    scrnMsg.prcRulePrcdGrpNum.setErrorInfo(1, NMAM0836E, new String[] {MSG_PRC_RULE_PRCD_GRP_NUM });
        //} else if (!ZYPCommonFunc.hasValue(scrnMsg.prcRulePrcdGrpNm)) {
        //    scrnMsg.prcRulePrcdGrpNm.setErrorInfo(1, NMAM0836E, new String[] {MSG_PRC_RULE_PRCD_GRP_NM });
        //}

        //for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
        //    if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).prcRulePrcdSqNum_B)) {
        //        scrnMsg.B.no(i).prcRulePrcdSqNum_B.setErrorInfo(1, NMAM0836E, new String[] {MSG_OP });
        //    }
        //}
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcRulePrcdGrpNm)) {
            scrnMsg.prcRulePrcdGrpNm.setErrorInfo(1, NMAM0836E, new String[] {MSG_PRC_RULE_PRCD_GRP_NM });
        }

        if (PRC_PRCD_ACT_TP.ALL.equals(scrnMsg.prcPrcdActTpCd.getValue())) {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).prcRulePrcdSqNum_B)) {
                    scrnMsg.B.no(i).prcRulePrcdSqNum_B.setErrorInfo(1, NMAM0836E, new String[] {MSG_OP });
                }
            }
        }
        // QC#9694 2016/07/08 Mod End

        NMAL7290CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7290BMsg scrnMsg = (NMAL7290BMsg) bMsg;

        NMAL7290CMsg bizMsg = new NMAL7290CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_40);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7290BMsg scrnMsg = (NMAL7290BMsg) bMsg;
        NMAL7290CMsg bizMsg = (NMAL7290CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7290CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }

        NMAL7290CommonLogic.setTblColor(scrnMsg, TBL_B1);
        //scrnMsg.setFocusItem(scrnMsg.prcRulePrcdGrpNum); // QC#9694 2016/07/08 Mod
        scrnMsg.setFocusItem(scrnMsg.prcRulePrcdPk);
    }
}
