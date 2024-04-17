/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFAL0080.common.NFAL0080CommonLogic;
import business.servlet.NFAL0080.constant.NFAL0080Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID :
 * NFAL0080Scrn00_OnChange_COA_SEG_LKUP_TP_CDs
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0080Scrn00_OnChange_COA_SEG_LKUP_TP_CD extends S21CommonHandler implements NFAL0080Constant {

    /** Line index */
    private int index = -1;

    /** NFAL0080CommonLogic */
    private NFAL0080CommonLogic common = new NFAL0080CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0080BMsg scrnMsg = (NFAL0080BMsg) bMsg;

        common.setInputProtectedEditableFields(scrnMsg, this, false);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.A.no(i).coaSegLkupTpCd_A3.getValue().equals(BLANK)) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).coaSegLkupTpCd_A3);
                common.setInputProtectedEditableFields(scrnMsg, this, true);
                // Let selected row editable
                scrnMsg.A.no(i).coaSegLkupTpCd_A3.setInputProtected(false);
                scrnMsg.putErrorScreen();
            }
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String coaSegLkupTpCd = scrnMsg.A.no(i).coaSegLkupTpCd_A3.getValue();
            String coaSegLkupTpCdOrg = scrnMsg.A.no(i).coaSegLkupTpCd_OR.getValue();

            if (!coaSegLkupTpCd.equals(coaSegLkupTpCdOrg)) {
                index = i;
                recordAlreadyExist(scrnMsg, index);
                break;
            }
        }
        System.out.print(index);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0080BMsg scrnMsg = (NFAL0080BMsg) bMsg;
        // NFAL0080CMsg bizMsg = new NFAL0080CMsg();
        // bizMsg.setBusinessID("NFAL0080");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);
        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0080BMsg scrnMsg = (NFAL0080BMsg) bMsg;
        // NFAL0080CMsg bizMsg = (NFAL0080CMsg) cMsg;
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (index == -1) {
            scrnMsg.setFocusItem(scrnMsg.eligCoaSegPtrnCd);
        }
    }

    private void recordAlreadyExist(NFAL0080BMsg scrnMsg, int rowIndex) {

        String eligCoaSegPtrnCd = scrnMsg.A.no(rowIndex).eligCoaSegPtrnCd_A.getValue();
        String coaSegLkupTpCd = scrnMsg.A.no(rowIndex).coaSegLkupTpCd_A3.getValue();

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (i == rowIndex) {
                continue;
            }
            if (eligCoaSegPtrnCd.equals(scrnMsg.A.no(i).eligCoaSegPtrnCd_A.getValue()) && coaSegLkupTpCd.equals(scrnMsg.A.no(i).coaSegLkupTpCd_A3.getValue())) {

                scrnMsg.A.no(rowIndex).coaSegLkupTpCd_A3.setErrorInfo(1, NFAM0070E, new String[] {RECORD, ELIG_COA_SEG_PTRN_TABLE });

                scrnMsg.addCheckItem(scrnMsg.A.no(rowIndex).coaSegLkupTpCd_A3);
                scrnMsg.setFocusItem(scrnMsg.A.no(rowIndex).coaSegLkupTpCd_A3);
                common.setInputProtectedEditableFields(scrnMsg, this, true);

                scrnMsg.putErrorScreen();
            }
        }
    }

}
