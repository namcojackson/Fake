/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7290;

import static business.servlet.NMAL7290.constant.NMAL7290Constant.BIZ_ID;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.FUNC_CD_20;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.MSG_PRC_RULE_PRCD_GRP_NUM;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.NMAM0836E;
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
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7290Scrn00_Search_PrcRulePrcd
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   Fujitsu         C.Tanaka        Create          N/A
 * 2016/07/08   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 *</pre>
 */
public class NMAL7290Scrn00_Search_PrcRulePrcd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7290BMsg scrnMsg = (NMAL7290BMsg) bMsg;
        // QC#9694 2016/07/08 Mod Start
        //if (!ZYPCommonFunc.hasValue(scrnMsg.prcRulePrcdGrpNum)) {
        //    scrnMsg.prcRulePrcdGrpNum.setErrorInfo(1, NMAM0836E, new String[] {MSG_PRC_RULE_PRCD_GRP_NUM });
        //}
        //scrnMsg.addCheckItem(scrnMsg.prcRulePrcdGrpNum);
        //scrnMsg.putErrorScreen();
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcRulePrcdPk)) {
            scrnMsg.prcRulePrcdPk.setErrorInfo(1, NMAM0836E, new String[] {MSG_PRC_RULE_PRCD_GRP_NUM });
        }
        scrnMsg.addCheckItem(scrnMsg.prcRulePrcdPk);
        scrnMsg.putErrorScreen();
        // QC#9694 2016/07/08 Mod End
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7290BMsg scrnMsg = (NMAL7290BMsg) bMsg;

        NMAL7290CMsg bizMsg = new NMAL7290CMsg();
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
            throw new EZDFieldErrorException();
        }

        NMAL7290CommonLogic.ctrlCmnBtnProp(this, getUserProfileService(), scrnMsg);
        NMAL7290CommonLogic.ctrlBtnProp(this, scrnMsg);
        NMAL7290CommonLogic.setTblColor(scrnMsg, TBL_B1);
    }
}
