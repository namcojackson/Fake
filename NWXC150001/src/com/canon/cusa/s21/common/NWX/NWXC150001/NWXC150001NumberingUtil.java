/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC150001;

import java.util.regex.Pattern;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * numbering utility for CSA
 */
public final class NWXC150001NumberingUtil {

    /**
     * regular expression (CPO detail line number)
     */
    private static final String REGEXP_FORMAT_CPO_DTL_LINE_NUM = "^[0-9][0-9][0-9]$";

    /** Numeric display Last number : 999 */
    public static final String THREE_NINE = "999";

    /** First String : A00 */
    public static final String FIRST_STR = "A00";

    /**
     * minimum CPO detail line number
     */
    private static final String MIN_CPO_DTL_LINE_NUM = "001";

    /**
     * maximum CPO detail line number
     */
//    private static final String MAX_CPO_DTL_LINE_NUM = "Z99";
    private static final String MAX_CPO_DTL_LINE_NUM = "ZZZ";

    /** 0 to Z */
    public static final String ZERO_TO_Z = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * <pre>
     * get next CPO detail line number
     * </pre>
     * @param cpoDtlLineNum - current CPO detail line number
     * @return <pre>
     * parameter - cpoDtlLineNum is .. 
     * normal : next CPO detail line number
     * empty : first CPO detail line number (001)
     * maximum CPO detail line number (Z99) : null
     * invalid format : null
     * 
     * <pre>
     */
    public static String getNextCpoDtlLineNum(String cpoDtlLineNum) {

        if (S21StringUtil.isEmpty(cpoDtlLineNum)) {

            // first number
            return MIN_CPO_DTL_LINE_NUM;
        }

        if (S21StringUtil.isEquals(cpoDtlLineNum, MAX_CPO_DTL_LINE_NUM)) {

            // overflow
            return null;
        }

        Pattern pattern = Pattern.compile(REGEXP_FORMAT_CPO_DTL_LINE_NUM);
//        if (!pattern.matcher(cpoDtlLineNum).find()) {
        if (pattern.matcher(cpoDtlLineNum).find()) {

            // invalid format
//            return null;
            if (S21StringUtil.isEquals(cpoDtlLineNum, THREE_NINE)) {
                return FIRST_STR;
            }

            int numVal = Integer.parseInt(cpoDtlLineNum);
            numVal++;
            return ZYPCommonFunc.leftPad(String.valueOf(numVal), 3, "0");
        }

//        char[] digit1 = S21StringUtil.subStringByLength(cpoDtlLineNum, 0, 1).toCharArray();
//        int digit23 = Integer.parseInt(S21StringUtil.subStringByLength(cpoDtlLineNum, 1, 2));

        String digit1 = S21StringUtil.subStringByLength(cpoDtlLineNum, 0, 1);
        String digit2 = S21StringUtil.subStringByLength(cpoDtlLineNum, 1, 1);
        String digit3 = S21StringUtil.subStringByLength(cpoDtlLineNum, 2, 1);

        int digit1idx = ZERO_TO_Z.indexOf(digit1);
        int digit2idx = ZERO_TO_Z.indexOf(digit2);
        int digit3idx = ZERO_TO_Z.indexOf(digit3);

        if (ZERO_TO_Z.length() != digit3idx + 1) {
            return S21StringUtil.subStringByLength(ZERO_TO_Z, digit1idx, 1)
                    + S21StringUtil.subStringByLength(ZERO_TO_Z, digit2idx, 1)
                    + S21StringUtil.subStringByLength(ZERO_TO_Z, digit3idx + 1, 1);
        } else {
            if (ZERO_TO_Z.length() == digit2idx + 1) {
                return S21StringUtil.subStringByLength(ZERO_TO_Z, digit1idx + 1, 1)
                        + S21StringUtil.subStringByLength(ZERO_TO_Z, digit2idx + 1, 1)
                        + S21StringUtil.subStringByLength(ZERO_TO_Z, digit3idx + 1, 1);
            } else {
                return S21StringUtil.subStringByLength(ZERO_TO_Z, digit1idx, 1)
                + S21StringUtil.subStringByLength(ZERO_TO_Z, digit2idx + 1, 1)
                + S21StringUtil.subStringByLength(ZERO_TO_Z, digit3idx + 1, 1);
            }
        }
        // increment line number
//        digit23++;
//        digit23 = digit23 % 100;
//        if (digit23 == 0) {
//            if (digit1[0] == 0x0039) {
//                digit1[0] = 0x0041;
//            } else {
//                digit1[0]++;
//            }
//        }

//        return String.valueOf(digit1) + ZYPCommonFunc.leftPad(String.valueOf(digit23), 2, "0");
    }
}
