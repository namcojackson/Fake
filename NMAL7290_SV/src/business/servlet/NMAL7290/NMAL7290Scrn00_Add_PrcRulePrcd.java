/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7290;

import static business.servlet.NMAL7290.constant.NMAL7290Constant.BIZ_ID;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.CHKBOX_A;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.FUNC_CD_20;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.MSG_MAX_ROW;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.MSG_PRC_RULE;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.NMAM0010E;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.NMAM0050E;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.NMAM8054E;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.NMAM8306E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7290.NMAL7290CMsg;
import business.servlet.NMAL7290.common.NMAL7290CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7290Scrn00_Add_PrcRulePrcd
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   Fujitsu         C.Tanaka        Create          N/A
 * 2016/07/08   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 *</pre>
 */
public class NMAL7290Scrn00_Add_PrcRulePrcd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7290BMsg scrnMsg = (NMAL7290BMsg) bMsg;

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.A, CHKBOX_A, ZYPConstant.CHKBOX_ON_Y);
        if (checkList.isEmpty()) {
            scrnMsg.setMessageInfo(NMAM8054E);
            throw new EZDFieldErrorException();
        }

        if (scrnMsg.B.getValidCount() + 1 == scrnMsg.B.length()) {
            scrnMsg.setMessageInfo(NMAM0050E, new String[] {MSG_MAX_ROW });
            throw new EZDFieldErrorException();
        }

        if (scrnMsg.B.getValidCount() > 0) {
            for (int idx : checkList) {
                if (!scrnMsg.A.no(idx).prcRuleCatgDescTxt_A.getValue().equals(scrnMsg.B.no(0).prcRuleCatgDescTxt_B.getValue())) {
                    scrnMsg.setMessageInfo(NMAM8306E);
                    throw new EZDFieldErrorException();
                }

                for (int j = 0; j < scrnMsg.B.getValidCount(); j++) {
                    if (scrnMsg.A.no(idx).prcRuleHdrPk_A.getValue().equals(scrnMsg.B.no(j).prcRuleHdrPk_B.getValue())) {
                        scrnMsg.setMessageInfo(NMAM0010E, new String[] {MSG_PRC_RULE });
                        throw new EZDFieldErrorException();
                    }
                }
            }
        }
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

        NMAL7290CommonLogic.ctrlCmnBtnProp(this, getUserProfileService(), scrnMsg);
        NMAL7290CommonLogic.ctrlBtnProp(this, scrnMsg);
        NMAL7290CommonLogic.ctrlInputProp(scrnMsg); // QC#9694 2016/07/08 Add 

    }
}
