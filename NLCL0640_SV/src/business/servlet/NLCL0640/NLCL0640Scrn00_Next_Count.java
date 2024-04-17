/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0640;


/** 
 * <pre>
 * Business ID : NLCL0640 Tech PI Count
 * Function Name : Next Count
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/10/2016   CITS            Makoto Okigami  Create          N/A
 * 05/09/2018   CITS            Y.Iwasaki       Update          QC#10572
 *</pre>
 */
//OBSOLETED
/*
public class NLCL0640Scrn00_Next_Count extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0640BMsg scrnMsg = (NLCL0640BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.techLocCd_SL);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.cntInpQty);
        scrnMsg.addCheckItem(scrnMsg.serNum);

        scrnMsg.putErrorScreen();

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
            scrnMsg.addCheckItem(scrnMsg.mdseCd);
            scrnMsg.addCheckItem(scrnMsg.cntInpQty);
            scrnMsg.addCheckItem(scrnMsg.serNum);
            scrnMsg.putErrorScreen();
            return;
        } else {
            if (PHYS_INVTY_CNT_STS.COUNTING.equals(scrnMsg.physInvtyCntStsCd.getValue())) {
                // set focus
                scrnMsg.setFocusItem(scrnMsg.mdseCd);
            } else if (PHYS_INVTY_CNT_STS.RECOUNTING.equals(scrnMsg.physInvtyCntStsCd.getValue())) {
                // set focus
                scrnMsg.setFocusItem(scrnMsg.cntInpQty);
            }
        }

        NLCL0640CommonLogic.ctrlScrnItemDisp(this, scrnMsg);
    }
}
*/
