/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0640;

import static business.servlet.NLCL0640.constant.NLCL0640Constant.BIZ_APP_ID;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.NOT_SHOW_DIALOG;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.SHOW_DIALOG;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0640.NLCL0640CMsg;
import business.servlet.NLCL0640.common.NLCL0640CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_CNT_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/09/2018   CITS            Y.Iwasaki       Update          QC#10572
 * 05/08/2018   CITS            T.Tokutomi      Update          QC#50029
 * 05/15/2023   Hitachi         TZ.Win          Update          QC#61427
 *</pre>
 */
public class NLCL0640Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NLCL0640BMsg scrnMsg = (NLCL0640BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0640BMsg scrnMsg = (NLCL0640BMsg) bMsg;

        NLCL0640CMsg bizMsg = new NLCL0640CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

        //return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0640BMsg scrnMsg = (NLCL0640BMsg) bMsg;
        NLCL0640CMsg bizMsg  = (NLCL0640CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2023/05/15 TZ.Win [QC#61427, MOD]
        if ("E".equals(bizMsg.getMessageKind()) || "W".equals(bizMsg.getMessageKind())) {
        // END 2023/05/15 TZ.Win [QC#61427, MOD]
            setResult(NOT_SHOW_DIALOG);
            return;
        }

        NLCL0640CommonLogic.ctrlScrnItemDisp(this, scrnMsg);

        // set focus
        scrnMsg.setFocusItem(scrnMsg.cntInpQty);

        // QC#50029 Update.
        if (PHYS_INVTY_CNT_STS.PI_COMPLETED.equals(scrnMsg.physInvtyCntStsCd.getValue()) //
                || PHYS_INVTY_CNT_STS.WAITAPPROVAL.equals(scrnMsg.physInvtyCntStsCd.getValue())//
                || PHYS_INVTY_CNT_STS.PI_VARIANCE.equals(scrnMsg.physInvtyCntStsCd.getValue())) {

            // set popup parameter
            Object[] params = NLCL0640CommonLogic.setTechPIFinishDialogParam(scrnMsg, getGlobalCompanyCode());
            setResult(SHOW_DIALOG);
            // send popup parameter
            setArgForSubScreen(params);

        } else if (PHYS_INVTY_CNT_STS.RECOUNTING.equals(scrnMsg.physInvtyCntStsCd.getValue())) {

            // set popup parameter
            Object[] params = NLCL0640CommonLogic.setReCountDialogParam(scrnMsg, getGlobalCompanyCode());
            setResult(SHOW_DIALOG);
            // send popup parameter
            setArgForSubScreen(params);

        } else {
            setResult(NOT_SHOW_DIALOG);
        }

        NLCL0640CommonLogic.ctrlScrnItemDisp(this, scrnMsg);
    }
}
