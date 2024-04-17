/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0050;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFAL0050.common.NFAL0050CommonLogic;
import business.servlet.NFAL0050.constant.NFAL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID : NFAL0050Scrn00_DeleteRowBtn
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0050Scrn00_DeleteRowBtn extends S21CommonHandler implements NFAL0050Constant {

    /** Singleton instance. */
    NFAL0050CommonLogic common = new NFAL0050CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        List<Integer> checkedRows = new ArrayList<Integer>();
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (CHECKED.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                checkedRows.add(i);
                break;
            }
        }
        if (checkedRows.isEmpty()) {
            scrnMsg.setFocusItem(scrnMsg.ajeLineIdxNum_3);
            scrnMsg.setMessageInfo("ZZM9000E", new String[] {"Check Box" });
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;

        // NFAL0050CMsg bizMsg = new NFAL0050CMsg();
        // bizMsg.setBusinessID("NFAL0050");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        // NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<Integer> unCheckedRows = new ArrayList<Integer>();
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.A.no(i).xxChkBox_A.isClear()) {
                unCheckedRows.add(i);
            }
        }

        List<NFAL0050_ABMsg> unCheckedRecords = new ArrayList<NFAL0050_ABMsg>();
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
        common.setSelelctUnSelectAllBtn(scrnMsg, this);
        // common.clearChkBoxes(scrnMsg);
        // common.clearAddRow(scrnMsg);

        if (scrnMsg.A.getValidCount() == 0) {
            // Let editable for indicator list boxes
            common.setInputProtectedIndicatorList(scrnMsg, false);
        } else {
            // Doesn't let editable for indicator list boxes
            common.setInputProtectedIndicatorList(scrnMsg, true);
        }

        common.enableAddRowButton(scrnMsg, this, true);
        common.setSubmitDeleteBtn(scrnMsg, this);
    }

}
