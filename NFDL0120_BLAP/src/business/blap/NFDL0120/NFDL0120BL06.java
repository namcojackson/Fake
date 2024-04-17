/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0120;

import static business.blap.NFDL0120.constant.NFDL0120Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFDL0120.common.NFDL0120CommonLogic;
import business.db.CLT_STRGYTMsg;
import business.db.CLT_STRGY_RELN_WRK_ITEMTMsg;
import business.db.CLT_WRK_ITEMTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Hitachi         K.Kojima        Create          N/A
 * 2016/07/19   Hitachi         K.Kojima        Update          QC#10188
 *</pre>
 */
public class NFDL0120BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NFDL0120Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0120Scrn00_CMN_Submit((NFDL0120CMsg) cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NFDL0120Scrn00_CMN_Submit(NFDL0120CMsg cMsg) {

        boolean checkResult = true;
        HashMap<String, Integer> cdCheck = new HashMap<String, Integer>();
        for (int num = 0; num < cMsg.A.getValidCount(); num++) {
            String cltWrkItemCd = cMsg.A.no(num).cltWrkItemCd_SV.getValue();
            if (cdCheck.containsKey(cltWrkItemCd)) {
                cMsg.A.no(num).cltWrkItemCd_SV.setErrorInfo(1, NFDM0019E, new String[] {"Work Item Code" });
                if (cdCheck.get(cltWrkItemCd).intValue() != -1) {
                    cMsg.A.no(cdCheck.get(cltWrkItemCd).intValue()).cltWrkItemCd_SV.setErrorInfo(1, NFDM0019E, new String[] {"Work Item Code" });
                }
                checkResult = false;
            } else {
                if (XX_TP_CD_UPD.equals(cMsg.A.no(num).xxTpCd_A.getValue())) {
                    cdCheck.put(cltWrkItemCd, Integer.valueOf(-1));
                } else {
                    cdCheck.put(cltWrkItemCd, Integer.valueOf(num));
                }
            }
        }

        // START 2016/07/19 K.Kojima [QC#10188,ADD]
        HashMap<BigDecimal, Integer> cdCheck2 = new HashMap<BigDecimal, Integer>();
        for (int num = 0; num < cMsg.A.getValidCount(); num++) {
            BigDecimal cltWrkItemSortNum = cMsg.A.no(num).cltWrkItemSortNum_A.getValue();
            if (cdCheck2.containsKey(cltWrkItemSortNum)) {
                cMsg.A.no(num).cltWrkItemSortNum_A.setErrorInfo(1, NFDM0019E, new String[] {"Sort Number" });
                cMsg.A.no(cdCheck2.get(cltWrkItemSortNum).intValue()).cltWrkItemSortNum_A.setErrorInfo(1, NFDM0019E, new String[] {"Sort Number" });
                checkResult = false;
            } else {
                cdCheck2.put(cltWrkItemSortNum, Integer.valueOf(num));
            }
        }
        // END 2016/07/19 K.Kojima [QC#10188,ADD]

        if (checkResult == false) {
            return;
        }

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String cltStrgyCd = cMsg.cltStrgyCd.getValue();

        CLT_STRGYTMsg cltStrgyTMsg = NFDL0120CommonLogic.getCltStrgy(glblCmpyCd, cltStrgyCd);
        if (cltStrgyTMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }

        for (int num = 0; num < cMsg.A.getValidCount(); num++) {
            NFDL0120_ACMsg acMsg = cMsg.A.no(num);
            CLT_WRK_ITEMTMsg cltWrkItemTMsg = NFDL0120CommonLogic.getCltWrkItem(glblCmpyCd, acMsg.cltWrkItemCd_SV.getValue());
            if (cltWrkItemTMsg == null) {
                cMsg.setMessageInfo(ZZZM9004E);
                return;
            }
        }

        List<CLT_STRGY_RELN_WRK_ITEMTMsg> deleteList = new ArrayList<CLT_STRGY_RELN_WRK_ITEMTMsg>(cMsg.D.length());

        for (int num = 0; num < cMsg.D.getValidCount(); num++) {
            NFDL0120_DCMsg dcMsg = cMsg.D.no(num);
            CLT_STRGY_RELN_WRK_ITEMTMsg tMsg = NFDL0120CommonLogic.getCltStrgyRelnWrkItemForUpdateNoWait(glblCmpyCd, cltStrgyCd, dcMsg.cltWrkItemCd_D.getValue());
            if (tMsg == null) {
                cMsg.setMessageInfo(ZZZM9004E);
                return;
            }
            if (!ZYPDateUtil.isSameTimeStamp(dcMsg.ezUpTime_D.getValue(), dcMsg.ezUpTimeZone_D.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(ZZZM9004E);
                return;
            }
            deleteList.add(tMsg);
        }

        for (CLT_STRGY_RELN_WRK_ITEMTMsg tMsg : deleteList) {
            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NFDM0021E, new String[] {"CLT_STRGY_RELN_WRK_ITEM" });
                return;
            }
        }

        List<CLT_STRGY_RELN_WRK_ITEMTMsg> insertList = new ArrayList<CLT_STRGY_RELN_WRK_ITEMTMsg>(cMsg.A.length());
        List<CLT_STRGY_RELN_WRK_ITEMTMsg> updateList = new ArrayList<CLT_STRGY_RELN_WRK_ITEMTMsg>(cMsg.A.length());

        for (int num = 0; num < cMsg.A.getValidCount(); num++) {
            NFDL0120_ACMsg acMsg = cMsg.A.no(num);
            CLT_STRGY_RELN_WRK_ITEMTMsg tMsg = NFDL0120CommonLogic.getCltStrgyRelnWrkItemForUpdateNoWait(glblCmpyCd, cltStrgyCd, acMsg.cltWrkItemCd_SV.getValue());
            if (tMsg == null) {
                tMsg = new CLT_STRGY_RELN_WRK_ITEMTMsg();
                setValue(tMsg.glblCmpyCd, glblCmpyCd);
                setValue(tMsg.cltStrgyCd, cltStrgyCd);
                setValue(tMsg.cltWrkItemCd, acMsg.cltWrkItemCd_SV);
                // START 2016/07/19 K.Kojima [QC#10188,ADD]
                setValue(tMsg.cltWrkItemSortNum, acMsg.cltWrkItemSortNum_A);
                // END 2016/07/19 K.Kojima [QC#10188,ADD]
                insertList.add(tMsg);
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(acMsg.ezUpTime_A.getValue(), acMsg.ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                // START 2016/07/19 K.Kojima [QC#10188,ADD]
                setValue(tMsg.cltWrkItemSortNum, acMsg.cltWrkItemSortNum_A);
                // END 2016/07/19 K.Kojima [QC#10188,ADD]
                updateList.add(tMsg);
            }
        }

        for (CLT_STRGY_RELN_WRK_ITEMTMsg msg : updateList) {
            EZDTBLAccessor.update(msg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(msg.getReturnCode())) {
                cMsg.setMessageInfo(NFDM0004E, new String[] {"CLT_STRGY_RELN_WRK_ITEM" });
                return;
            }
        }

        for (CLT_STRGY_RELN_WRK_ITEMTMsg msg : insertList) {
            EZDTBLAccessor.create(msg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(msg.getReturnCode())) {
                cMsg.setMessageInfo(NFDM0013E, new String[] {"CLT_STRGY_RELN_WRK_ITEM" });
                return;
            }
        }

    }

}
