/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0060;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFAL0060.common.NFAL0060CommonLogic;
import business.servlet.NFAL0060.constant.NFAL0060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID : NFAL0060Scrn00_DeleteRow
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
public class NFAL0060Scrn00_DeleteRow extends S21CommonHandler implements NFAL0060Constant {

    /** Singleton instance. */
    NFAL0060CommonLogic common = new NFAL0060CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0060BMsg scrnMsg = (NFAL0060BMsg) bMsg;
        List<Integer> checkedRows = new ArrayList<Integer>();
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (CHECKED.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                checkedRows.add(i);
                break;
            }
        }
        if (checkedRows.isEmpty()) {
            scrnMsg.setMessageInfo("ZZM9000E", new String[] {"Check Box" });
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0060BMsg scrnMsg = (NFAL0060BMsg) bMsg;
        // NFAL0060CMsg bizMsg = new NFAL0060CMsg();
        // bizMsg.setBusinessID("NFAL0060");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);
        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0060BMsg scrnMsg = (NFAL0060BMsg) bMsg;
        // NFAL0060CMsg bizMsg = (NFAL0060CMsg) cMsg;
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);

        storeDeleteRecord(scrnMsg);

        List<Integer> unCheckedRows = new ArrayList<Integer>();
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.A.no(i).xxChkBox_A.isClear()) {
                unCheckedRows.add(i);
            }
        }
        // Delete added row where checked
        // for exclusion control
        deleteCheckedRecordInAddRecord(scrnMsg, unCheckedRows);

        List<NFAL0060_ABMsg> unCheckedRecords = new ArrayList<NFAL0060_ABMsg>();
        Iterator<Integer> it = unCheckedRows.iterator();
        while (it.hasNext()) {
            unCheckedRecords.add(scrnMsg.A.no(it.next()));
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (i < unCheckedRecords.size()) {
                EZDMsg.copy(unCheckedRecords.get(i), null, scrnMsg.A.no(i), null);
            } else {
                scrnMsg.A.no(i).clear();
            }
        }
        scrnMsg.A.setValidCount(unCheckedRecords.size());

        common.clearChkBoxes(scrnMsg);
        //---- start del 2016/03/24
        //common.clearAddRow(scrnMsg, this);
        //---- end 2016/03/24
        common.enableDeleteRow(scrnMsg, this, false);
    }

    private void storeDeleteRecord(NFAL0060BMsg scrnMsg) {

        int deleteIndex = scrnMsg.B.getValidCount();
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (CHECKED.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                scrnMsg.B.no(deleteIndex).ezUpTime_B.setValue(scrnMsg.A.no(i).ezUpTime_A.getValue());
                scrnMsg.B.no(deleteIndex).ezUpTimeZone_B.setValue(scrnMsg.A.no(i).ezUpTimeZone_A.getValue());
                //scrnMsg.B.no(deleteIndex).ajeIntfcTpCd_B.setValue(scrnMsg.A.no(i).ajeIntfcTpCd_A.getValue());
                scrnMsg.B.no(deleteIndex).ajePtrnIndTpCd_B.setValue(scrnMsg.A.no(i).ajePtrnIndTpCd_A.getValue());
                scrnMsg.B.no(deleteIndex).ajePtrnActlCd_B.setValue(scrnMsg.A.no(i).ajePtrnActlCd_A.getValue());
                deleteIndex++;
            }
        }
        scrnMsg.B.setValidCount(deleteIndex);
    }

    private void deleteCheckedRecordInAddRecord(NFAL0060BMsg scrnMsg, List<Integer> unCheckedRows) {

        List<NFAL0060_DBMsg> unCheckedRecords = new ArrayList<NFAL0060_DBMsg>();
        Iterator<Integer> it = unCheckedRows.iterator();
        while (it.hasNext()) {
            unCheckedRecords.add(scrnMsg.D.no(it.next()));
        }
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            if (i < unCheckedRecords.size()) {
                EZDMsg.copy(unCheckedRecords.get(i), null, scrnMsg.D.no(i), null);
            } else {
                scrnMsg.D.no(i).clear();
            }
        }
        scrnMsg.D.setValidCount(unCheckedRecords.size());
    }

}
