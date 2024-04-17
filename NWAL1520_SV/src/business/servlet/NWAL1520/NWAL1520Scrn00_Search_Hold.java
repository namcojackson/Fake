/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1520;

import static business.servlet.NWAL1520.constant.NWAL1520Constant.BIZ_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.NWAM0300E;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.LINK_NM_HOLD_REASON_VIEW_HOLD;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.LINK_NM_HOLD_NAME_VIEW_HOLD;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.SCRN_ID_00;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1520.NWAL1520CMsg;
import business.servlet.NWAL1520.common.NWAL1520CommonLogic;


import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;


/**
 *<pre>
 * NWAL1520Scrn00_Search_Hold
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/30   Fujitsu         A.Suda          Create          N/A
 *</pre>
 */
public class NWAL1520Scrn00_Search_Hold extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1520BMsg scrnMsg = (NWAL1520BMsg) bMsg;

        scrnMsg.xxErrFlg_HR.setValue(ZYPConstant.FLG_OFF_N);
        scrnMsg.xxErrFlg_HN.setValue(ZYPConstant.FLG_OFF_N);

        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum_V);
        scrnMsg.addCheckItem(scrnMsg.condSqlTxt_V);
        scrnMsg.addCheckItem(scrnMsg.hldApplyRsnDescTxt_V);
        scrnMsg.addCheckItem(scrnMsg.hldRsnDescTxt_V);
        scrnMsg.addCheckItem(scrnMsg.hldUntilDt_V);

        if (hasValue(scrnMsg.condSqlTxt_V) && NWAL1520CommonLogic.checkLineNum(scrnMsg.condSqlTxt_V.getValue())) {
            scrnMsg.condSqlTxt_V.setErrorInfo(1, NWAM0300E, new String[] {"Line Number" });
        }

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1520BMsg scrnMsg = (NWAL1520BMsg) bMsg;

        NWAL1520CMsg bizMsg = new NWAL1520CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1520BMsg scrnMsg = (NWAL1520BMsg) bMsg;
        NWAL1520CMsg bizMsg = (NWAL1520CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        this.setResult("no");

        scrnMsg.addCheckItem(scrnMsg.hldRsnDescTxt_V);
        scrnMsg.addCheckItem(scrnMsg.hldApplyRsnDescTxt_V);

        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);

        scrnMsg.putErrorScreen();

        NWAL1520CommonLogic.initCmnBtnProp(this);
        NWAL1520CommonLogic.setInputProtected(this, scrnMsg);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            return;
        }

        // NWAL1520Scrn00_OpenWin_ApplyReason
        if (FLG_ON_Y.equals(scrnMsg.xxErrFlg_HR.getValue())) {
            this.setResult("yes2");
            scrnMsg.xxScrEventNm.setValue(LINK_NM_HOLD_REASON_VIEW_HOLD);
            Object[] params = NWAL1520CommonLogic.getApplyReasonPopUpParams(scrnMsg);
            setArgForSubScreen(params);
            return;

        }

        // NWAL1520Scrn00_OpenWin_ViewHold
        if (FLG_ON_Y.equals(scrnMsg.xxErrFlg_HN.getValue())) {
            this.setResult("yes1");
            scrnMsg.xxScrEventNm.setValue(LINK_NM_HOLD_NAME_VIEW_HOLD);
            Object[] params = NWAL1520CommonLogic.getViewHoldPopUpParams(scrnMsg, getGlobalCompanyCode());
            setArgForSubScreen(params);
            return;
        }

        NWAL1520CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.V, "V_Right");
        NWAL1520CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.L, "A_Right");
        NWAL1520CommonLogic.setInputProtectedforCheckBox(this, scrnMsg);

    }
}
