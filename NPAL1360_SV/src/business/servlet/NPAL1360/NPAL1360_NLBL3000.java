/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1360;

import static business.servlet.NPAL1360.constant.NPAL1360Constant.EVENT_OPEN_WIN_KIT_SERIAL;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.EVENT_OPEN_WIN_SUPPLY_SERIAL;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_0;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.KIT_ITEM_LINE_NUM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1360 Kitting Work Order Entry
 * Function Name : Return Serial Number Entry Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/13/2016   CITS            Keiichi Masaki  Create          N/A
 * 01/31/2017   CITS            Y.IWASAKI       Update          QC#16109
 *</pre>
 */
public class NPAL1360_NLBL3000 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.S);

        // Copy from scrnMsg.W to scrnMsg.S
        for (int i = 0; i < scrnMsg.W.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).wrkOrdDtlLineNum_S1, scrnMsg.W.no(i).wrkOrdDtlLineNum_W1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).serNum_S1, scrnMsg.W.no(i).serNum_W1);
        }
        scrnMsg.S.setValidCount(scrnMsg.W.getValidCount());

        // Copy from PopUp Value to scrnMsg.S
        int serCnt = scrnMsg.W.getValidCount();
        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.L.no(i).xxDplyTrxNumTxt_L1)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(serCnt).wrkOrdDtlLineNum_S1, scrnMsg.L.no(i).xxDplyTrxNumTxt_L1);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(serCnt).wrkOrdDtlLineNum_S1, KIT_ITEM_LINE_NUM);
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(serCnt).serNum_S1, scrnMsg.L.no(i).serNum_L1);
            serCnt++;
            scrnMsg.S.setValidCount(serCnt);
        }

        // QC#16109
        // Concatenate and display serials into cMsg.serNum
        StringBuilder sbSerials = new StringBuilder();
        for (int i = 0; i < scrnMsg.L.getValidCount(); ++i) {
            if (ZYPCommonFunc.hasValue(scrnMsg.L.no(i).serNum_L1)) {
                sbSerials.append(",").append(scrnMsg.L.no(i).serNum_L1.getValue());
            }
        }
        if (sbSerials.length() > 0) {
            sbSerials.delete(0, 1);
        }
        if (sbSerials.length() > 30) {
            sbSerials.delete(30, sbSerials.length());
        }

        if (EVENT_OPEN_WIN_KIT_SERIAL.equals(scrnMsg.eventNm.getValue())) {
            // set Kit Serial#
            ZYPEZDItemValueSetter.setValue(scrnMsg.serNum, sbSerials.toString());
        } else if (EVENT_OPEN_WIN_SUPPLY_SERIAL.equals(scrnMsg.eventNm.getValue())) {
            // set Component Serial#
            int lineNum = Integer.parseInt(scrnMsg.L.no(INDEX_0).xxDplyTrxNumTxt_L1.getValue()) - 1;
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineNum).serNum_A1, sbSerials.toString());
        }
    }
}
