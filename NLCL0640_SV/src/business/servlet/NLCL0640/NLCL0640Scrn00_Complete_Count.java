/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0640;


/** 
 * <pre>
 * Business ID : NLCL0640 Tech PI Count
 * Function Name : Complete Count
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/11/2016   CITS            Makoto Okigami  Create          N/A
 * 05/09/2018   CITS            Y.Iwasaki       Update          QC#10572
 *</pre>
 */
// OBSOLETED
/*
public class NLCL0640Scrn00_Complete_Count extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0640BMsg scrnMsg = (NLCL0640BMsg) bMsg;

        NLCL0640CMsg bizMsg = new NLCL0640CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0640BMsg scrnMsg = (NLCL0640BMsg) bMsg;
        NLCL0640CMsg bizMsg  = (NLCL0640CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind())) {
            setResult(NOT_SHOW_DIALOG);
            return;
        }

        NLCL0640CommonLogic.ctrlScrnItemDisp(this, scrnMsg);

        // set focus
        scrnMsg.setFocusItem(scrnMsg.cntInpQty);

        if (PHYS_INVTY_CNT_STS.PI_COMPLETED.equals(scrnMsg.physInvtyCntStsCd.getValue())
                || PHYS_INVTY_CNT_STS.WAITAPPROVAL.equals(scrnMsg.physInvtyCntStsCd.getValue())) {

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

    }
}
*/
