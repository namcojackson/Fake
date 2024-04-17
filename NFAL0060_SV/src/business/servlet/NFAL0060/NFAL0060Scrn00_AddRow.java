/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 * 
 * 
 */
package business.servlet.NFAL0060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0060.NFAL0060CMsg;
import business.servlet.NFAL0060.common.NFAL0060CommonLogic;
import business.servlet.NFAL0060.constant.NFAL0060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID : NFAL0060Scrn00_AddRow
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */

/**
 *<pre>
 * Auto Write-Off Result Inquiry Screen Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/24   CSAI            K.Uramori       Update          QC#5849 remove AJE_INTFC_TP from key
 *</pre>
 */
public class NFAL0060Scrn00_AddRow extends S21CommonHandler implements NFAL0060Constant {

    /** Singleton instance. */
    private NFAL0060CommonLogic common = new NFAL0060CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0060BMsg scrnMsg = (NFAL0060BMsg) bMsg;

        //if (!scrnMsg.ajePtrnIndTpCd_NW.isClear() && !scrnMsg.ajeIntfcTpCd_3.isClear() && sameKeyAlreadyExistInTheList(scrnMsg)) {
        if (!scrnMsg.ajePtrnIndTpCd_NW.isClear() && sameKeyAlreadyExistInTheList(scrnMsg)) {
            // NFAM0034E=0, @ cannot be added because
            // it is already in the list
            //scrnMsg.ajeIntfcTpCd_3.setErrorInfo(1, "NFAM0034E", new String[] {scrnMsg.ajeIntfcTpNm.getValue() + ":" + scrnMsg.ajePtrnIndTpCd_NW.getValue() });
            scrnMsg.ajePtrnIndTpCd_NW.setErrorInfo(1, "NFAM0034E", new String[] {scrnMsg.ajePtrnIndTpCd_NW.getValue() });
        }

        scrnMsg.addCheckItem(scrnMsg.ajeCdTblListCd_3);
        scrnMsg.addCheckItem(scrnMsg.ajePtrnIndTpCd_NW);
        scrnMsg.addCheckItem(scrnMsg.ajePtrnIndTpNm_NW);
        //scrnMsg.addCheckItem(scrnMsg.ajeIntfcTpCd_3);
        // add the text field
        scrnMsg.addCheckItem(scrnMsg.ajeIntfcColTxt_IN);
        
        scrnMsg.putErrorScreen();
    }

    private boolean sameKeyAlreadyExistInTheList(NFAL0060BMsg scrnMsg) {

        String newIndTpCd = scrnMsg.ajePtrnIndTpCd_NW.getValue();
        //String newIntfcTpCd = scrnMsg.ajeIntfcTpCd_3.getValue();
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String ajePtrnIndTpCd = scrnMsg.A.no(i).ajePtrnIndTpCd_A.getValue();
            //String ajeIntfcTpCd = scrnMsg.A.no(i).ajeIntfcTpCd_A.getValue();
            if (newIndTpCd.equals(ajePtrnIndTpCd)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0060BMsg scrnMsg = (NFAL0060BMsg) bMsg;
        NFAL0060CMsg bizMsg = new NFAL0060CMsg();
        bizMsg.setBusinessID("NFAL0060");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

        // return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0060BMsg scrnMsg = (NFAL0060BMsg) bMsg;
        NFAL0060CMsg bizMsg = (NFAL0060CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        //scrnMsg.addCheckItem(scrnMsg.ajeIntfcTpCd_3);
        //scrnMsg.addCheckItem(scrnMsg.ajeIntfcTpNm);
        scrnMsg.addCheckItem(scrnMsg.ajePtrnIndTpCd_3);
        scrnMsg.addCheckItem(scrnMsg.ajePtrnIndTpCd_NW);
        scrnMsg.putErrorScreen();

        int addIndex = scrnMsg.A.getValidCount();
        int addedRows = scrnMsg.C.getValidCount();
        int newLastRow = addIndex + addedRows;
        int newIndex = 0;

        if (newLastRow >= scrnMsg.A.length()) {
            // NFAM0072E=0, "Record" cannot be added because it is
            // exceeding
            // the maximum number of row [@]
            scrnMsg.setMessageInfo("NFAM0072E", new String[] {"Record", Integer.toString(scrnMsg.A.length()) });
            return;
        }

        // New row starts from next to existing row(s)
        for (int i = addIndex; i < newLastRow; i++) {
            // Indicator Type
            scrnMsg.A.no(i).ajePtrnIndTpCd_A.setValue(scrnMsg.ajePtrnIndTpCd_NW.getValue());
            // Indicator Type Description
            scrnMsg.A.no(i).ajePtrnIndTpNm_A.setValue(scrnMsg.ajePtrnIndTpNm_NW.getValue());
            // Actual Code
            scrnMsg.A.no(i).ajePtrnActlCd_A.setValue(scrnMsg.C.no(newIndex).ajePtrnActlCd_C.getValue());
            // Actual Code Description
            scrnMsg.A.no(i).ajePtrnActlNm_A.setValue(scrnMsg.C.no(newIndex).ajePtrnActlNm_C.getValue());
            // Intfc Type
            //scrnMsg.A.no(i).ajeIntfcTpCd_A.setValue(scrnMsg.ajeIntfcTpCd_3.getValue());
            // Interface Type Description
            //scrnMsg.A.no(i).ajeIntfcTpNm_A.setValue(scrnMsg.ajeIntfcTpNm.getValue());
            // Interface Column (Column name to be looked)
            // change to text box
            scrnMsg.A.no(i).ajeIntfcColTxt_A.setValue(scrnMsg.ajeIntfcColTxt_IN.getValue());

            newIndex++;
        }

        scrnMsg.A.setValidCount(addIndex + newIndex);

        setAddedRow(scrnMsg);

        NFAL0060CommonLogic.setInputProtectedTextFiled(scrnMsg);

        // Doesn't let add row when exceeding max row num
        if (scrnMsg.A.getValidCount() >= scrnMsg.A.length()) {
            common.enableAddRow(scrnMsg, this, false);
        } else {
            common.enableAddRow(scrnMsg, this, true);
        }

        //---- start mod 2016/03/24
        //common.clearAddRow(scrnMsg, this);
        NFAL0060CommonLogic.controlInputFields(scrnMsg, this, true);
        //---- end 2016/03/24
        common.clearChkBoxes(scrnMsg);
        common.enableSubmitDelete(scrnMsg, this, true);

      //---- start add 2016/03/24 enable delete function for this mode
        NFAL0060CommonLogic.controlChkBox(scrnMsg, this, false);
        //---- end 2016/03/24
        
        //scrnMsg.setFocusItem(scrnMsg.ajeIntfcTpCd_3);
        scrnMsg.setFocusItem(scrnMsg.ajePtrnIndTpCd_3);
    }

    private void setAddedRow(NFAL0060BMsg scrnMsg) {
        // Store total added row
        // for exclusion control
        int addIndex = scrnMsg.D.getValidCount();
        int addedRows = scrnMsg.C.getValidCount();
        int newLastRow = addIndex + addedRows;
        int newIndex = 0;

        for (int i = addIndex; i < newLastRow; i++) {
            //scrnMsg.D.no(i).ajeIntfcTpCd_D.setValue(scrnMsg.ajeIntfcTpCd_3.getValue());
            scrnMsg.D.no(i).ajePtrnIndTpCd_D.setValue(scrnMsg.ajePtrnIndTpCd_NW.getValue());
            scrnMsg.D.no(i).ajePtrnActlCd_D.setValue(scrnMsg.C.no(newIndex).ajePtrnActlCd_C.getValue());
            newIndex++;
        }
        scrnMsg.D.setValidCount(addIndex + newIndex);
    }

}
