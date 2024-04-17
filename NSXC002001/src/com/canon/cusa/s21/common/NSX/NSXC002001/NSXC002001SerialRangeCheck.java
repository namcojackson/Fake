/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC002001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import business.db.SVC_MOD_SER_RNGTMsg;
import business.db.SVC_MOD_SER_RNGTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

/**
 * <pre>
 * Serial Range Check
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2015   Hitachi         Y.Tsuchimoto    Create          NA#Modification Status Update API
 * 02/03/2016   Hitachi         A.Kohinata      Update          CSA UnitTest#92
 * 2018/08/20   Hitachi         K.Kitachi       Update          CSA QC#27460
 * 2021/10/25   CITS            R.Cabral        Update          CSA QC#58668
 * </pre>
 */
public class NSXC002001SerialRangeCheck {
    /**
     * S21SsmBatchClient object.
     */
// START 2021/10/25 R.Cabral [QC#58668, DEL]
//    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC002001SerialRangeCheck.class);
// END   2021/10/25 R.Cabral [QC#58668, DEL]

    /**
     * isSerialRangeCheck
     * @param glblCmpyCd Global Company Code
     * @param svcModPlnId Service Modification Plan ID
     * @param mdseCd Mdse Code
     * @param serNum Serial#
     * @param svcModDtlPk Mod Detail PK
     * @return true:, false:
     */
    public static boolean isSerialRangeCheck(String glblCmpyCd, String svcModPlnId, String mdseCd, String serNum, BigDecimal svcModDtlPk) {
        // START 2021/10/25 R.Cabral [QC#58668, MOD]
//        if (checkParameter(glblCmpyCd, svcModPlnId, mdseCd, serNum)) {
//            BigDecimal svcModDtlPk = getModificationInfo(glblCmpyCd, svcModPlnId, mdseCd);
//            if (svcModDtlPk == null) {
//                return false;
//            }
        if (checkParameter(glblCmpyCd, svcModPlnId, mdseCd, serNum, svcModDtlPk)) {
            // END   2021/10/25 R.Cabral [QC#58668, MOD]

            BigDecimal lgSerNum = new BigDecimal(serNum.length());
            SVC_MOD_SER_RNGTMsgArray rangeTmsgList = getSvcModSerRng(glblCmpyCd, svcModDtlPk, lgSerNum);
            if (rangeTmsgList.getValidCount() == 0) {
                return false;
            }
            if (isCheckSerialRange(rangeTmsgList, serNum)) {
                return true;
            }
        }
        return false;
    }
// START 2021/10/25 R.Cabral [QC#58668, DEL]
//    private static BigDecimal getModificationInfo(String glblCmpyCd, String svcModPlnId, String mdseCd) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", glblCmpyCd);
//        param.put("svcModPlnId", svcModPlnId);
//        param.put("mdseCd", mdseCd);
//        // START 2018/08/20 K.Kitachi [QC#27460, ADD]
//        List<String> mdseItemRelnTpCdList = new ArrayList<String>();
//        mdseItemRelnTpCdList.add(MDSE_ITEM_RELN_TP.REFURBISHED);
//        mdseItemRelnTpCdList.add(MDSE_ITEM_RELN_TP.REMANUFACTURED);
//        param.put("mdseItemRelnTpCdList", mdseItemRelnTpCdList);
//        // END 2018/08/20 K.Kitachi [QC#27460, ADD]
//
//        return (BigDecimal) SSM_CLIENT.queryObject("getModificationInfo", param);
//    }
// END   2021/10/25 R.Cabral [QC#58668, DEL]

    private static SVC_MOD_SER_RNGTMsgArray getSvcModSerRng(String glblCmpyCd, BigDecimal svcModDtlPk, BigDecimal lgSerNum) {
        SVC_MOD_SER_RNGTMsg param = new SVC_MOD_SER_RNGTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("svcModDtlPk01", svcModDtlPk);
        param.setConditionValue("lgSerNum01", lgSerNum);

        return (SVC_MOD_SER_RNGTMsgArray) S21ApiTBLAccessor.findByCondition(param);
    }

    private static boolean isCheckSerialRange(SVC_MOD_SER_RNGTMsgArray rangeTmsgList, String serNum) {

        for (int i = 0; i < rangeTmsgList.getValidCount(); i++) {
            SVC_MOD_SER_RNGTMsg tMsg = (SVC_MOD_SER_RNGTMsg) rangeTmsgList.get(i);
            String svcModFromSerNum = tMsg.svcModFromSerNum.getValue();
            String svcModToSerNum = tMsg.svcModToSerNum.getValue();

            boolean lineCheckFlg = true;
            for (int j = 0; j < svcModFromSerNum.length(); j++) {
                // mod start 02/03/2016 CSA UnitTest#92
//                String fromChar = String.valueOf(svcModFromSerNum.charAt(j));
//                String toChar = String.valueOf(svcModToSerNum.charAt(j));
//                String serNumChar = String.valueOf(serNum.charAt(j));
//                // From check
//                if (fromChar == null || fromChar.compareTo(serNumChar) > 0) {
//                    lineCheckFlg = false;
//                    break;
//                }
//                // To Check
//                if (toChar == null || toChar.compareTo(serNumChar) < 0) {
//                    lineCheckFlg = false;
//                    break;
//                }
                lineCheckFlg = isCheckSerialNum(svcModFromSerNum, svcModToSerNum, serNum);
                if (!lineCheckFlg) {
                    break;
                }
                // mod end 02/03/2016 CSA UnitTest#92
            }
            if (lineCheckFlg) {
                return true;
            }
        }
        return false;
    }

    // add start 02/03/2016 CSA UnitTest#92
    /**
     * isCheckSerialNum
     * @param fromSerNum From Serial#
     * @param toSerNum To Serial#
     * @param serNum Serial#
     * @return true:, false:
     */
    public static boolean isCheckSerialNum(String fromSerNum, String toSerNum, String serNum) {

        // 000 -> [0]:null, [1]:000 -> OK
        // AAA -> [0]:AAA, [1]:null -> NG
        // AAA000 -> [0]:AAA, [1]:000 -> OK
        // 000AAA -> [0]:000AAA, [1]:null -> NG
        String[] splitSerNumFrom = splitSerialNumber(fromSerNum);
        String fromSerNumPfx = splitSerNumFrom[0];
        String fromSerNumNmrc = splitSerNumFrom[1];

        String[] splitSerNumTo = splitSerialNumber(toSerNum);
        String toSerNumPfx = splitSerNumTo[0];
        String toSerNumNmrc = splitSerNumTo[1];

        int lgSerNum = 0;
        if (hasValue(fromSerNum)) {
            lgSerNum = fromSerNum.length();
        }

        // From:Number, Thru:Alphabet -> All blank
        if (!hasValue(fromSerNumPfx) && hasValue(fromSerNumNmrc) && hasValue(toSerNumPfx) && !hasValue(toSerNumNmrc)) {
            fromSerNumPfx = "";
            fromSerNumNmrc = "";
            toSerNumPfx = "";
            toSerNumNmrc = "";
        }

        String[] splitSerNum = splitSerialNumber(serNum);
        String serNumPfx = splitSerNum[0];
        String serNumNmrc = splitSerNum[1];

        // Both From and Thru alphabetic parts must be corresponding.
        // or All Blanks.
        if ((serNumPfx.equals(fromSerNumPfx) && serNumPfx.equals(toSerNumPfx)) || (!hasValue(fromSerNumPfx) && !hasValue(fromSerNumNmrc) && !hasValue(toSerNumPfx) && !hasValue(toSerNumNmrc))) {

            // Check Length
            if (serNum.length() != lgSerNum) {
                return false;
            }

            // At the unsetting
            if ("".equals(serNumNmrc) || "".equals(fromSerNumNmrc) || "".equals(toSerNumNmrc)) {

                if ("".equals(serNumNmrc) && "".equals(fromSerNumNmrc) && "".equals(toSerNumNmrc)) {
                    return true;
                } else if (!"".equals(serNumNmrc) && "".equals(fromSerNumNmrc) && "".equals(toSerNumNmrc)) {
                    return true;
                } else {
                    return false;
                }

                // Check range
            } else {
                BigDecimal serNumNumber = new BigDecimal(serNumNmrc);
                BigDecimal fromSerNumNumber = new BigDecimal(fromSerNumNmrc);
                BigDecimal toSerNumNumber = new BigDecimal(toSerNumNmrc);

                if (fromSerNumNumber.compareTo(serNumNumber) <= 0 && serNumNumber.compareTo(toSerNumNumber) <= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String[] splitSerialNumber(String serNum) {
        String[] splitSerNum = new String[2];

        if (!hasValue(serNum)) {
            splitSerNum[0] = "";
            splitSerNum[1] = "";
            return splitSerNum;
        }

        int i = serNum.length();
        for (; 0 < i; i--) {
            try {
                new BigDecimal(String.valueOf(serNum.charAt(i - 1)));
            } catch (NumberFormatException e) {
                break;
            }
        }

        splitSerNum[0] = serNum.substring(0, i);
        splitSerNum[1] = serNum.substring(i);

        return splitSerNum;
    }
    // add end 02/03/2016 CSA UnitTest#92

    // START 2021/10/25 R.Cabral [QC#58668, MOD]
//    private static boolean checkParameter(String glblCmpyCd, String svcModPlnId, String mdseCd, String serNum) {
    private static boolean checkParameter(String glblCmpyCd, String svcModPlnId, String mdseCd, String serNum, BigDecimal svcModDtlPk) {
    // END   2021/10/25 R.Cabral [QC#58668, MOD]
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(svcModPlnId)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(serNum)) {
            return false;
        }
        // START 2021/10/25 R.Cabral [QC#58668, ADD]
        if (!ZYPCommonFunc.hasValue(svcModDtlPk)) {
            return false;
        }
        // END   2021/10/25 R.Cabral [QC#58668, ADD]
        return true;
    }
}
