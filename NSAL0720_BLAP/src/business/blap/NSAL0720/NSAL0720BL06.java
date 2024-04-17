/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0720;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static business.blap.NSAL0720.constant.NSAL0720Constant.*;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0720.common.NSAL0720CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Update Bill To
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Hitachi         K.Kasai         Create          N/A
 * 2016/05/23   Hitachi         M.Gotou         Update          QC#7557
 * 2016/07/25   Hitachi         K.Yamada        Update          CSA QC#12001
 * 2016/11/11   Hitachi         T.Mizuki        Update          QC#4210
 *</pre>
 */
public class NSAL0720BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0720CMsg cMsg = (NSAL0720CMsg) arg0;
        NSAL0720SMsg sMsg = (NSAL0720SMsg) arg1;
        super.preDoProcess(cMsg, null);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0720Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0720Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NSAL0720Scrn00_CMN_Submit(NSAL0720CMsg cMsg, NSAL0720SMsg sMsg) {
        // add start 2016/07/25 CSA Defect#12001
        NSAL0720CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        // add end 2016/07/25 CSA Defect#12001
        //Validation
        if (!checkExistSelectCheckbox(sMsg)) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }
        // mod start 2016/07/27 CSA Defect#12001
        NSAL0720_ASMsg aSMsg = new NSAL0720_ASMsg();
        boolean isError = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            aSMsg = sMsg.A.no(i);
            if (CHKBOX_ON_Y.equals(aSMsg.xxChkBox_A1.getValue()) && validationCheck(cMsg, aSMsg, i)) {
                isError = true;
            }
            if (CHKBOX_ON_Y.equals(aSMsg.xxChkBox_A2.getValue()) && validationCheck(cMsg, aSMsg, i)) {
                isError = true;
            }
        }
        // mod end 2016/07/27 CSA Defect#12001
        // mod start 2016/11/08 CSA QC#4210
        if (!isError) {
            //update billToCust
            NSAL0720CommonLogic.updateBillTo(sMsg, cMsg);
        }

        NSAL0720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        // mod end 2016/11/08 CSA QC#4210
    }

    /**
     * @param cMsg
     * @param existCheck
     * @return
     */
    private boolean checkExistSelectCheckbox(NSAL0720SMsg sMsg) {
        boolean existCheck = false;
        // mod start 2016/07/25 CSA Defect#12001
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())
                    || FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A2.getValue())) {
                existCheck = true;
                break;
            }
        }
        // mod end 2016/07/25 CSA Defect#12001
        return existCheck;
    }

    /**
     * @param cMsg
     * @param aCMsg
     * @param selectRow
     */
    private boolean validationCheck(NSAL0720CMsg cMsg, NSAL0720_ASMsg aSMsg, int selectRow) {
        boolean rtnVal = false;
        // mod start 2016/07/25 CSA Defect#12001
        /*if (hasValue(aCMsg.xxScrItem34Txt_A1)) {
            BigDecimal curDsContrPk = aCMsg.dsContrPk_A1.getValue();
            for (int i = selectRow; i < cMsg.A.getValidCount(); i++) {
                if (!hasValue(cMsg.A.no(i).serNum_A1)
                        && hasValue(cMsg.A.no(i).dsContrPk_A1)
                        && curDsContrPk.compareTo(cMsg.A.no(i).dsContrPk_A1.getValue()) == 0) {
                    // add start 2016/05/23 CSA Defect#7557
                    if (fleetCheck(cMsg.A.no(i))) {
                        continue;
                    }
                    // add end 2016/05/23 CSA Defect#7557
                    if (!hasValue(cMsg.A.no(i).billToCustLocAddr_A2)) {
                        cMsg.A.no(i).billToCustLocAddr_A2.setErrorInfo(1, NSAM0007E, new String[] {"New Bill To" });
                        rtnVal = true;
                    }
                }
            }
        } else if (hasValue(aCMsg.serNum_A1)) {
            BigDecimal curDsContrDtlPk = aCMsg.dsContrDtlPk_A1.getValue();
            for (int i = selectRow; i < cMsg.A.getValidCount(); i++) {
                if (!hasValue(cMsg.A.no(i).serNum_A1)
                        && hasValue(cMsg.A.no(i).dsContrDtlPk_A1)
                        && curDsContrDtlPk.compareTo(cMsg.A.no(i).dsContrDtlPk_A1.getValue()) == 0) {
                    // add start 2016/05/23 CSA Defect#7557
                    if (fleetCheck(cMsg.A.no(i))) {
                        continue;
                    }
                    // add end 2016/05/23 CSA Defect#7557
                    if (!hasValue(cMsg.A.no(i).billToCustLocAddr_A2)) {
                        cMsg.A.no(i).billToCustLocAddr_A2.setErrorInfo(1, NSAM0007E, new String[] {"New Bill To" });
                        rtnVal = true;
                    }
                }
            }
        } else {
            if (!hasValue(aCMsg.billToCustLocAddr_A2)) {
                aCMsg.billToCustLocAddr_A2.setErrorInfo(1, NSAM0007E, new String[] {"New Bill To" });
                return true;
            }
        }*/
        if (!hasValue(aSMsg.billToCustLocAddr_A2)) {
            aSMsg.billToCustLocAddr_A2.setErrorInfo(1, NSAM0007E, new String[] {"New Bill To" });
            cMsg.setMessageInfo(NSAM0558E);
            return true;
        }
        // mod end 2016/07/25 CSA Defect#12001
        return rtnVal;
    }

    // add start 2016/05/23 CSA Defect#7557
    private boolean fleetCheck(NSAL0720_ACMsg aCMsg) {
        if (DS_CONTR_CATG.FLEET.equals(aCMsg.dsContrCatgCd_A1.getValue())) {
            if (hasValue(aCMsg.xxScrItem34Txt_A1) && !FLG_ON_Y.equals(aCMsg.xxChkBox_A1.getValue())) {
                return true;
            } else if (!hasValue(aCMsg.xxScrItem34Txt_A1) && !FLG_ON_Y.equals(aCMsg.xxChkBox_A2.getValue())) {
                return true;
            }
        }
        return false;
    }
    // add end 2016/05/23 CSA Defect#7557

}
