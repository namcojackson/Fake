/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1350;

import static business.blap.NPAL1350.constant.NPAL1350Constant.BTN_PRINT;
import static business.blap.NPAL1350.constant.NPAL1350Constant.EVENT_NM_NPAL1350_PRINT;
import static business.blap.NPAL1350.constant.NPAL1350Constant.INSERT_WRK_ORD_BOM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.NLBM0024E;
import static business.blap.NPAL1350.constant.NPAL1350Constant.NPAM1527E;
import static business.blap.NPAL1350.constant.NPAL1350Constant.ZZZM9003I;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1350.common.NPAL1350CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WRK_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_WRK_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Business ID : NPAL1350 Kitting WO Search
 * Function Name : Report
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/14/2018   CITS        K.Fukumura          Update          QC#22324
 *</pre>
 */

public class NPAL1350BL09 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {

            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPAL1350_PRINT.equals(screenAplID)) {
                doProcess_NPAL1350_Print((NPAL1350CMsg) cMsg, (NPAL1350SMsg) sMsg);

            } else {
                return;
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Print
     * @param cMsg NPAL1350CMsg
     * @param sMsg NPAL1350SMsg
     */
    private void doProcess_NPAL1350_Print(NPAL1350CMsg cMsg, NPAL1350SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        // copy data from CMsg onto SMsg
        if (cMsg.A.getValidCount() != 0) {
            int nowIndex = cMsg.xxPageShowFromNum.getValueInt();
            int nowLastIndex = cMsg.xxPageShowToNum.getValueInt();
            int q = 0;

            for (int j = nowIndex - 1; j < nowLastIndex; j++) {
                EZDMsg.copy(cMsg.A.no(q), null, sMsg.A.no(j), null);
                q++;
            }
        }

        // CheckBox Y Select
        List<Integer> selectIdxList = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);
        
        // CheckBox Y Not Exist Is Err
        if (selectIdxList.isEmpty()) {
            cMsg.setMessageInfo("NPAM0049E", null);
            return;
        } 
        
        // WorkOrdStatus Check
        for (int i = 0; i < sMsg.A.length(); i++){
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).xxChkBox_A1) && ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                if (!(WRK_ORD_STS.VALIDATED.equals(sMsg.A.no(i).wrkOrdStsCd_A1.getValue()) || WRK_ORD_STS.SHIPPED.equals(sMsg.A.no(i).wrkOrdStsCd_A1.getValue()))) {
                    cMsg.setMessageInfo("NAAM0070E", new String[] {"In Progress"});
                    return;
                }
            }
            
        }
        
        
        if (!NPAL1350CommonLogic.print(cMsg, sMsg, glblCmpyCd)) {
            return;
        }

        cMsg.setMessageInfo(ZZZM9003I, new String[] {BTN_PRINT });

    }

}
