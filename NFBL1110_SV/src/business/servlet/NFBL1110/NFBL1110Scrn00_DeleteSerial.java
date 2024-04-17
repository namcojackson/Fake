/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1110;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NFBL1110.common.NFBL1110CommonLogic;
import business.servlet.NFBL1110.constant.NFBL1110Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * AP Invoice Maintenance Batch Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   CUSA            Y.Aikawa        Create          N/A
 * 2016/08/23   Fujitsu         T.Murai         Update          QC#12830
 * </pre>
 */
public class NFBL1110Scrn00_DeleteSerial extends S21CommonHandler implements NFBL1110Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;

        // Check if at least 1 checkbox is checked.
        boolean checked = false;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // Mod Start 2016/08/23 QC#12830
//            if (!scrnMsg.A.no(i).xxChkBox_A1.isClear()
//            &&  ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
            if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).xxGrpFlg_A1.getValue()) //
                    && ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
            // Mod End 2016/08/23 QC#12830
                checked = true;
            }
        }
        if (!checked) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                // Mod Start 2016/08/23 QC#12830
//                if (!scrnMsg.A.no(i).xxChkBox_A1.isInputProtected()) {
//                    scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NZZM0011E);
                if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).xxGrpFlg_A1.getValue())) {
                    scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NFBM0236E, new String[] {"Serial Line" });
                    // Mod Start 2016/08/23 QC#12830
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
                }
            }
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;

        List<Integer> unCheckedRows = new ArrayList<Integer>();

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // Mod Start 2016/08/23 QC#12830
//            int remainder = i % INT_6;
//            if (remainder == 0 && scrnMsg.A.no(i).xxChkBox_A1.isClear()) {
//                for (int j = 0; j < INT_6; j++) {
//                    unCheckedRows.add(i+j);
            // }
            // }
            if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).xxGrpFlg_A1.getValue()) //
                    && scrnMsg.A.no(i).xxChkBox_A1.isClear()) {
                unCheckedRows.add(i);
                for (int j = i + 1; j < scrnMsg.A.getValidCount(); j++) {
                    if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(j).xxGrpFlg_A1.getValue())) {
                        break;
                    }
                    unCheckedRows.add(j);
                }
            }
            // Mod End 2016/08/22 QC#12830
        }

        List<NFBL1110_ABMsg> unCheckedRecords = new ArrayList<NFBL1110_ABMsg>();
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

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.clear();
        }
        /** Initialize input control */ 
        NFBL1110CommonLogic.initControl(this, scrnMsg);
        /** Set focus when opening screen */
        scrnMsg.setFocusItem(scrnMsg.ovrdSerNum_AD);
    }
}
