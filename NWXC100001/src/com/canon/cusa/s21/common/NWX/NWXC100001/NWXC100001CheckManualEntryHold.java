package com.canon.cusa.s21.common.NWX.NWXC100001;

// import business.parts.NWZC215001_APMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Check Manual Entry Hold
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/16/2012   Fujitsu         D.Yanagisawa    Create          N/A
 * 01/21/2013   Fujitsu         D.Yanagisawa    Update          Defect#158
 * 01/30/2013   Fujitsu         D.Yanagisawa    Update          Defect#518
 * 05/22/2013   Fujitsu         D.Yanagisawa    Update          Defect#1211
 * </pre>
 */
public class NWXC100001CheckManualEntryHold {

    /**
     * Value is missing in the parameter's required field.
     */
//    private static final String NWZM0382E = "NWZM0382E";

    /**
     * <pre>
     * Check Manual Entry Hold
     * </pre>
     * @param prcApiAPMsgArray NWZC215001_APMsgArray
     * @return manualEntryHoldFlag String
     */
//    public static String checkManualEntryHold(NWZC215001_APMsgArray prcApiAPMsgArray) {
//
//        if (prcApiAPMsgArray == null) {
//            throw new S21AbendException(NWZM0382E);
//        }
//
//        for (int i = 0; i < prcApiAPMsgArray.getValidCount(); i++) {
//            if (ZYPConstant.FLG_OFF_N.equals(prcApiAPMsgArray.no(i).prcCondManDelFlg.getValue())
//                    && ZYPCommonFunc.hasValue(prcApiAPMsgArray.no(i).manPrcAmtRate.getValue())) {
//                if (!(prcApiAPMsgArray.no(i).autoPrcAmtRate.getValue().compareTo(prcApiAPMsgArray.no(i).manPrcAmtRate.getValue()) == 0)) {
//                    return ZYPConstant.FLG_ON_Y;
//                }
//            } else {
//                // UPD for Defect#518 01/21/2013 START
//                if (ZYPConstant.FLG_ON_Y.equals(prcApiAPMsgArray.no(i).prcCondManDelFlg.getValue())) {
//                    // ADD for Defect#158 01/21/2013 START
//                    return ZYPConstant.FLG_ON_Y;
//                    // UPD for Defect#158 01/21/2013 E N D
//                }
//                // ADD for Defect#518 01/21/2013 E N D
//            }
//        }
//
//        return ZYPConstant.FLG_OFF_N;
//    }
}
