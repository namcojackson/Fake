/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0140;

import static business.blap.NFDL0140.constant.NFDL0140Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFDL0140.common.NFDL0140CommonLogic;
import business.db.CLT_STRGYTMsg;
import business.db.CLT_STRGY_RELN_CUST_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         K.Kojima        Create          N/A
 * 2016/03/17   Hitachi         K.Kojima        Update          CSA QC#5349
 * 2016/08/01   Hitachi         K.Kojima        Update          QC#12493
 *</pre>
 */
public class NFDL0140BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NFDL0140Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0140Scrn00_CMN_Submit((NFDL0140CMsg) cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NFDL0140Scrn00_CMN_Submit(NFDL0140CMsg cMsg) {

        boolean checkResult = true;
        // START 2016/03/17 K.Kojima [QC#5349,DEL]
        // HashMap<String, Integer> cdCheck = new HashMap<String,
        // Integer>();
        // for (int num = 0; num < cMsg.A.getValidCount(); num++) {
        // String cltCustTpCd =
        // cMsg.A.no(num).cltCustTpCd_SV.getValue();
        // if (cdCheck.containsKey(cltCustTpCd)) {
        // cMsg.A.no(num).cltCustTpCd_SV.setErrorInfo(1, NFDM0019E,
        // new String[] {"Customer Type" });
        // if (cdCheck.get(cltCustTpCd).intValue() != -1) {
        // cMsg.A.no(cdCheck.get(cltCustTpCd).intValue()).cltCustTpCd_SV.setErrorInfo(1,
        // NFDM0019E, new String[] {"Customer Type" });
        // }
        // checkResult = false;
        // } else {
        // if
        // (XX_TP_CD_UPD.equals(cMsg.A.no(num).xxTpCd_A.getValue())) {
        // cdCheck.put(cltCustTpCd, Integer.valueOf(-1));
        // } else {
        // cdCheck.put(cltCustTpCd, Integer.valueOf(num));
        // }
        // }
        // }
        // END 2016/03/17 K.Kojima [QC#5349,DEL]

        // START 2016/03/17 K.Kojima [QC#5349,ADD]
        HashMap<String, HashMap<Integer, NFDL0140_ACMsg>> cdCheck = new HashMap<String, HashMap<Integer, NFDL0140_ACMsg>>();
        for (int num = 0; num < cMsg.A.getValidCount(); num++) {
            NFDL0140_ACMsg acMsg = cMsg.A.no(num);
            String cltCustTpCd = acMsg.cltCustTpCd_SV.getValue();
            BigDecimal lowAmt = acMsg.cltOverDueRangeLowAmt_A.getValue();
            BigDecimal highAmt = acMsg.cltOverDueRangeHighAmt_A.getValue();
            if (lowAmt.compareTo(highAmt) > 0) {
                cMsg.A.no(num).cltOverDueRangeLowAmt_A.setErrorInfo(1, NFDM0022E);
                cMsg.A.no(num).cltOverDueRangeHighAmt_A.setErrorInfo(1, NFDM0022E);
                checkResult = false;
                continue;
            }
            if (cdCheck.containsKey(cltCustTpCd)) {
                HashMap<Integer, NFDL0140_ACMsg> cdCheckTarget = cdCheck.get(cltCustTpCd);
                Iterator<Integer> ite = cdCheckTarget.keySet().iterator();
                while (ite.hasNext()) {
                    Integer key = ite.next();
                    NFDL0140_ACMsg acMsgTarget = cdCheckTarget.get(key);
                    BigDecimal lowAmtTarget = acMsgTarget.cltOverDueRangeLowAmt_A.getValue();
                    BigDecimal highAmtTarget = acMsgTarget.cltOverDueRangeHighAmt_A.getValue();
                    if (lowAmt.compareTo(highAmtTarget) <= 0 && lowAmtTarget.compareTo(highAmt) <= 0) {
                        cMsg.A.no(num).cltCustTpCd_SV.setErrorInfo(1, NFDM0019E, new String[] {"Customer Type" });
                        cMsg.A.no(num).cltOverDueRangeLowAmt_A.setErrorInfo(1, NFDM0019E, new String[] {"Balance Low" });
                        cMsg.A.no(num).cltOverDueRangeHighAmt_A.setErrorInfo(1, NFDM0019E, new String[] {"Balance High" });
                        if (key.intValue() >= 0) {
                            cMsg.A.no(key.intValue()).cltCustTpCd_SV.setErrorInfo(1, NFDM0019E, new String[] {"Customer Type" });
                            cMsg.A.no(key.intValue()).cltOverDueRangeLowAmt_A.setErrorInfo(1, NFDM0019E, new String[] {"Balance Low" });
                            cMsg.A.no(key.intValue()).cltOverDueRangeHighAmt_A.setErrorInfo(1, NFDM0019E, new String[] {"Balance High" });
                        }
                        checkResult = false;
                    }
                }
                if (XX_TP_CD_UPD.equals(cMsg.A.no(num).xxTpCd_A.getValue())) {
                    cdCheckTarget.put(Integer.valueOf(num * -1), acMsg);
                } else {
                    cdCheckTarget.put(Integer.valueOf(num), acMsg);
                }
                cdCheck.put(cltCustTpCd, cdCheckTarget);
            } else {
                HashMap<Integer, NFDL0140_ACMsg> cdCheckTarget = new HashMap<Integer, NFDL0140_ACMsg>();
                if (XX_TP_CD_UPD.equals(cMsg.A.no(num).xxTpCd_A.getValue())) {
                    if (num == 0) {
                        cdCheckTarget.put(Integer.valueOf(-999), acMsg);
                    } else {
                        cdCheckTarget.put(Integer.valueOf(num * -1), acMsg);
                    }
                } else {
                    cdCheckTarget.put(Integer.valueOf(num), acMsg);
                }
                cdCheck.put(cltCustTpCd, cdCheckTarget);
            }
        }
        // END 2016/03/17 K.Kojima [QC#5349,ADD]

        if (checkResult == false) {
            return;
        }

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String cltStrgyCd = cMsg.cltStrgyCd.getValue();

        for (int num = 0; num < cMsg.A.getValidCount(); num++) {
            String cltCustTpCd = cMsg.A.no(num).cltCustTpCd_SV.getValue();
            BigDecimal cltOverDueRangeLowAmt = cMsg.A.no(num).cltOverDueRangeLowAmt_A.getValue();
            BigDecimal cltOverDueRangeHighAmt = cMsg.A.no(num).cltOverDueRangeHighAmt_A.getValue();
            String sameData = NFDL0140Query.getInstance().getCltOverDueRangeAmtSameData(glblCmpyCd, cltStrgyCd, cltCustTpCd, cltOverDueRangeLowAmt, cltOverDueRangeHighAmt);
            if (sameData != null) {
                if (XX_TP_CD_INS.equals(cMsg.A.no(num).xxTpCd_A.getValue())) {
                    // START 2016/03/17 K.Kojima [QC#5297,MOD]
                    // cMsg.A.no(num).cltCustTpCd_SV.setErrorInfo(1,
                    // NFDM0022E, new String[] {sameData });
                    cMsg.A.no(num).cltCustTpCd_SV.setErrorInfo(1, NFDM0032E, new String[] {"Customer Type", sameData });
                    // START 2016/03/17 K.Kojima [QC#5297,MOD]
                }
                // START 2016/03/17 K.Kojima [QC#5349,MOD]
                // cMsg.A.no(num).cltOverDueRangeLowAmt_A.setErrorInfo(1,
                // NFDM0022E, new String[] {sameData });
                // cMsg.A.no(num).cltOverDueRangeHighAmt_A.setErrorInfo(1,
                // NFDM0022E, new String[] {sameData });
                cMsg.A.no(num).cltOverDueRangeLowAmt_A.setErrorInfo(1, NFDM0032E, new String[] {"Balance Low", sameData });
                cMsg.A.no(num).cltOverDueRangeHighAmt_A.setErrorInfo(1, NFDM0032E, new String[] {"Balance High", sameData });
                // START 2016/03/17 K.Kojima [QC#5349,MOD]
                checkResult = false;
            }
        }

        if (checkResult == false) {
            return;
        }

        CLT_STRGYTMsg cltStrgyTMsg = NFDL0140CommonLogic.getCltStrgy(glblCmpyCd, cltStrgyCd);
        if (cltStrgyTMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }

        List<CLT_STRGY_RELN_CUST_TPTMsg> deleteList = new ArrayList<CLT_STRGY_RELN_CUST_TPTMsg>(cMsg.D.length());

        for (int num = 0; num < cMsg.D.getValidCount(); num++) {
            NFDL0140_DCMsg dcMsg = cMsg.D.no(num);
            CLT_STRGY_RELN_CUST_TPTMsg tMsg = NFDL0140CommonLogic.getCltStrgyRelnCustTpForUpdateNoWait(glblCmpyCd, cltStrgyCd, dcMsg.cltCustTpCd_D.getValue(), dcMsg.cltOverDueRangeLowAmt_D.getValue());
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

        // START 2016/08/01 K.Kojima [QC#12493,ADD]
        for (int num = 0; num < cMsg.A.getValidCount(); num++) {
            NFDL0140_ACMsg acMsg = cMsg.A.no(num);
            if (XX_TP_CD_UPD.equals(acMsg.xxTpCd_A.getValue()) && acMsg.cltOverDueRangeLowAmt_BK.getValue().compareTo(acMsg.cltOverDueRangeLowAmt_A.getValue()) != 0) {
                CLT_STRGY_RELN_CUST_TPTMsg tMsg = NFDL0140CommonLogic.getCltStrgyRelnCustTpForUpdateNoWait(glblCmpyCd, cltStrgyCd, acMsg.cltCustTpCd_SV.getValue(), acMsg.cltOverDueRangeLowAmt_BK.getValue());
                if (tMsg == null) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                if (!ZYPDateUtil.isSameTimeStamp(acMsg.ezUpTime_A.getValue(), acMsg.ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                deleteList.add(tMsg);
            }
        }
        // END 2016/08/01 K.Kojima [QC#12493,ADD]

        for (CLT_STRGY_RELN_CUST_TPTMsg tMsg : deleteList) {
            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NFDM0021E, new String[] {"CLT_STRGY_RELN_WRK_ITEM" });
                return;
            }
        }

        List<CLT_STRGY_RELN_CUST_TPTMsg> insertList = new ArrayList<CLT_STRGY_RELN_CUST_TPTMsg>(cMsg.A.length());
        List<CLT_STRGY_RELN_CUST_TPTMsg> updateList = new ArrayList<CLT_STRGY_RELN_CUST_TPTMsg>(cMsg.A.length());

        for (int num = 0; num < cMsg.A.getValidCount(); num++) {
            NFDL0140_ACMsg acMsg = cMsg.A.no(num);
            CLT_STRGY_RELN_CUST_TPTMsg tMsg = NFDL0140CommonLogic.getCltStrgyRelnCustTpForUpdateNoWait(glblCmpyCd, cltStrgyCd, acMsg.cltCustTpCd_SV.getValue(), acMsg.cltOverDueRangeLowAmt_A.getValue());
            if (tMsg == null) {
                tMsg = new CLT_STRGY_RELN_CUST_TPTMsg();
                setValue(tMsg.glblCmpyCd, glblCmpyCd);
                setValue(tMsg.cltStrgyCd, cltStrgyCd);
                setValue(tMsg.cltCustTpCd, acMsg.cltCustTpCd_SV);
                setValue(tMsg.cltOverDueRangeLowAmt, acMsg.cltOverDueRangeLowAmt_A);
                setValue(tMsg.cltOverDueRangeHighAmt, acMsg.cltOverDueRangeHighAmt_A);
                insertList.add(tMsg);
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(acMsg.ezUpTime_A.getValue(), acMsg.ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                setValue(tMsg.cltOverDueRangeHighAmt, acMsg.cltOverDueRangeHighAmt_A);
                updateList.add(tMsg);
            }
        }

        for (CLT_STRGY_RELN_CUST_TPTMsg msg : updateList) {
            EZDTBLAccessor.update(msg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(msg.getReturnCode())) {
                cMsg.setMessageInfo(NFDM0004E, new String[] {"CLT_STRGY_RELN_CUST_TP" });
                return;
            }
        }

        for (CLT_STRGY_RELN_CUST_TPTMsg msg : insertList) {
            EZDTBLAccessor.create(msg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(msg.getReturnCode())) {
                cMsg.setMessageInfo(NFDM0013E, new String[] {"CLT_STRGY_RELN_CUST_TP" });
                return;
            }
        }

    }

}
