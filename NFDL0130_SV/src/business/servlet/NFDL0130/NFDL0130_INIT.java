/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0130;

import static business.servlet.NFDL0130.constant.NFDL0130Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0130.NFDL0130CMsg;
import business.servlet.NFDL0130.common.NFDL0130CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         K.Kojima        Create          N/A
 * 2016/02/17   Hitachi         K.Kojima        Update          CSA QC#4282
 * 2016/09/12   Hitachi         K.Kojima        Update          QC#12997
 *</pre>
 */
public class NFDL0130_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0130BMsg scrnMsg = (NFDL0130BMsg) bMsg;

        NFDL0130CMsg bizMsg = new NFDL0130CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0130BMsg scrnMsg = (NFDL0130BMsg) bMsg;
        NFDL0130CMsg bizMsg = (NFDL0130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFDL0130CommonLogic.setupScreenItems(this, scrnMsg);

        NFDL0130CommonLogic.setFocusRule(scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFDL0130BMsg scrnMsg = (NFDL0130BMsg) bMsg;

        for (int num = 0; num < scrnMsg.A.length(); num++) {
            // mod start 2016/02/17 CSA Defect#4282
            // scrnMsg.A.no(num).cltWrkItemCd_A.setNameForMessage("Work Item Code");
            // scrnMsg.A.no(num).cltWrkItemNm_A.setNameForMessage("Work Item Name");
            // scrnMsg.A.no(num).cltWrkTpCd_SV.setNameForMessage("Work Type Code");
            scrnMsg.A.no(num).cltWrkItemCd_A.setNameForMessage("Code");
            scrnMsg.A.no(num).cltWrkItemNm_A.setNameForMessage("Name");
            scrnMsg.A.no(num).cltWrkTpCd_SV.setNameForMessage("Work Item Type");
            // mod end 2016/02/17 CSA Defect#4282
            scrnMsg.A.no(num).cltWrkCatgCd_A.setNameForMessage("Work Category Code");
            scrnMsg.A.no(num).cltWrkCatgNm_A.setNameForMessage("Work Category Name");
            // START 2016/09/12 K.Kojima [QC#12997,DEL]
            // scrnMsg.A.no(num).cltWrkPrtyCd_A.setNameForMessage("Work Priority Code");
            // scrnMsg.A.no(num).cltWrkPrtyNm_A.setNameForMessage("Work Priority Name");
            // END 2016/09/12 K.Kojima [QC#12997,DEL]
            // mod start 2016/02/17 CSA Defect#4282
            // scrnMsg.A.no(num).cltWrkWaitDaysAot_A.setNameForMessage("Work Wait Days");
            // scrnMsg.A.no(num).cltWrkEsclWaitDaysAot_A.setNameForMessage("Work Escalation Days");
            // scrnMsg.A.no(num).cltWrkOptItemFlg_A.setNameForMessage("Work Option Item Flag");
            // scrnMsg.A.no(num).cltWrkEsclFlg_A.setNameForMessage("Work Escalation Flag");
            // scrnMsg.A.no(num).cltWrkNtfyFlg_A.setNameForMessage("Work Notify Flag");
            // scrnMsg.A.no(num).cltWrkDescTxt_A.setNameForMessage("Work Description");
            scrnMsg.A.no(num).cltWrkWaitDaysAot_A.setNameForMessage("Wait time(days)");
            scrnMsg.A.no(num).cltWrkEsclWaitDaysAot_A.setNameForMessage("Escalation Time(days)");
            // START 2016/09/12 K.Kojima [QC#12997,MOD]
            // scrnMsg.A.no(num).cltWrkOptItemFlg_A.setNameForMessage("Original Item");
            scrnMsg.A.no(num).cltWrkOptItemFlg_A.setNameForMessage("Optional Item");
            // END 2016/09/12 K.Kojima [QC#12997,MOD]
            scrnMsg.A.no(num).cltWrkEsclFlg_A.setNameForMessage("Escalation");
            scrnMsg.A.no(num).cltWrkNtfyFlg_A.setNameForMessage("Notify");
            scrnMsg.A.no(num).cltWrkDescTxt_A.setNameForMessage("Work Item Description");
            // mod end 2016/02/17 CSA Defect#4282
        }

    }
}
