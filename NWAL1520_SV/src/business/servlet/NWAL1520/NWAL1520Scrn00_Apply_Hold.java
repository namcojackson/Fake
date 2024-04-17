/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1520;

import static business.servlet.NWAL1520.constant.NWAL1520Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1520.NWAL1520CMsg;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.LINK_NM_HOLD_NAME_APPLY_HOLD;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.NWAM0298E;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.NWAM0300E;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.NWAM0674E;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.LINK_NM_HOLD_REASON_APPLY_HOLD;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.SCRN_ID_00;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import business.servlet.NWAL1520.common.NWAL1520CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1520Scrn00_Apply_Hold
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/30   Fujitsu         A.Suda          Create          N/A
 * 2017/08/07   Fujitsu         H.Ikeda         Update          QC#7164
 *</pre>
 */
public class NWAL1520Scrn00_Apply_Hold extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1520BMsg scrnMsg = (NWAL1520BMsg) bMsg;

        scrnMsg.xxErrFlg_HR.setValue(ZYPConstant.FLG_OFF_N);
        scrnMsg.xxErrFlg_HN.setValue(ZYPConstant.FLG_OFF_N);

        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum_A);
        scrnMsg.addCheckItem(scrnMsg.condSqlTxt_A);
        scrnMsg.addCheckItem(scrnMsg.configCatgCd_A);
        scrnMsg.addCheckItem(scrnMsg.hldApplyRsnDescTxt_A);
        scrnMsg.addCheckItem(scrnMsg.hldRsnDescTxt_A);
        scrnMsg.addCheckItem(scrnMsg.hldUntilDt_A);
        scrnMsg.addCheckItem(scrnMsg.hldDtlTxt_A);

        if (!hasValue(scrnMsg.cpoOrdNum_A)) {
            scrnMsg.cpoOrdNum_A.setErrorInfo(1, NWAM0298E, new String[] {"Order Number" });
        }
        if (hasValue(scrnMsg.configCatgCd_A) && !hasValue(scrnMsg.condSqlTxt_A)) {
            scrnMsg.condSqlTxt_A.setErrorInfo(1, NWAM0674E, new String[] {"Category Type", "Line Number" });
        }
        if (!hasValue(scrnMsg.configCatgCd_A) && hasValue(scrnMsg.condSqlTxt_A)) {
            scrnMsg.configCatgCd_A.setErrorInfo(1, NWAM0674E, new String[] {"Line Number", "Category Type" });
        }
        if (!hasValue(scrnMsg.hldApplyRsnDescTxt_A)) {
            scrnMsg.hldApplyRsnDescTxt_A.setErrorInfo(1, NWAM0298E, new String[] {"Hold Reason" });
        }
        if (!hasValue(scrnMsg.hldRsnDescTxt_A)) {
            scrnMsg.hldRsnDescTxt_A.setErrorInfo(1, NWAM0298E, new String[] {"Hold Name" });
        }

        if (hasValue(scrnMsg.condSqlTxt_A) && NWAL1520CommonLogic.checkLineNum(scrnMsg.condSqlTxt_A.getValue())) {
            scrnMsg.condSqlTxt_A.setErrorInfo(1, NWAM0300E, new String[] {"Line Number" });
        }
        // 2017/08/07 QC#7164 Add Start
        if (!hasValue(scrnMsg.hldDtlTxt_A)) {
            scrnMsg.hldDtlTxt_A.setErrorInfo(1, NWAM0298E, new String[] {"Hold Comments" });
        }
        // 2017/08/07 QC#7164 Add End
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1520BMsg scrnMsg = (NWAL1520BMsg) bMsg;

        NWAL1520CMsg bizMsg = new NWAL1520CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1520BMsg scrnMsg = (NWAL1520BMsg) bMsg;
        NWAL1520CMsg bizMsg = (NWAL1520CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        this.setResult("no");

        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        NWAL1520CommonLogic.initCmnBtnProp(this);
        NWAL1520CommonLogic.setScreenItemInit(this, scrnMsg);
        NWAL1520CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.V, "V_Right");
        NWAL1520CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.L, "A_Right");

        scrnMsg.addCheckItem(scrnMsg.hldApplyRsnDescTxt_A);
        scrnMsg.addCheckItem(scrnMsg.hldRsnDescTxt_A);
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum_A);
        scrnMsg.addCheckItem(scrnMsg.hldUntilDt_A);
        scrnMsg.addCheckItem(scrnMsg.condSqlTxt_A);

        scrnMsg.putErrorScreen();

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            return;
        }

        // NWAL1520Scrn00_OpenWin_ApplyReason
        if (FLG_ON_Y.equals(scrnMsg.xxErrFlg_HR.getValue())) {
            this.setResult("yes2");
            scrnMsg.xxScrEventNm.setValue(LINK_NM_HOLD_REASON_APPLY_HOLD);
            Object[] params = NWAL1520CommonLogic.getApplyReasonPopUpParams(scrnMsg);
            setArgForSubScreen(params);
            return;

        }

        // NWAL1520Scrn00_OpenWin_ApplyHold
        if (FLG_ON_Y.equals(scrnMsg.xxErrFlg_HN.getValue())) {
            this.setResult("yes1");
            scrnMsg.xxScrEventNm.setValue(LINK_NM_HOLD_NAME_APPLY_HOLD);
            Object[] params = NWAL1520CommonLogic.getApplyHoldPopUpParams(scrnMsg, getGlobalCompanyCode());
            setArgForSubScreen(params);
            return;
        }

    }

}
