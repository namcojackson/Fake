/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB024001;

import parts.common.EZDTMsg;
import business.db.AR_RCPT_RCV_WRKTMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * NFCB0240Util Common method for CSV
 *
 * Date         Company       Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/02/2010   Canon         K.Usui          Create          N/A
 * 03/18/2010   Canon         I.Kondo         Update          N/A 
 *</pre>
 */
public class NFCB0240Util {

    /** CSV ID : Paymentech1 */
    public static final int CSV_ID_PAYMENTECH_1 = 1;

    /** CSV ID : Paymentech2 */
    public static final int CSV_ID_PAYMENTECH_2 = 2;

    /** RCPT_CHK_NUM */
    public static final int RCPT_CHK_NUM_INDEX = 15;

    /** INV_30_NUM */
    public static final int INV_30_NUM_INDEX = 13;

    /** INV_NUM */
    public static final int INV_NUM_INDEX = 13;

    /** BIGIN INDEX */
    public static final int BIGIN_INDEX = 0;

    /**
     * @param rsChk30Num String
     * @param initChk30Num String
     * @return rtnChk30Num String
     */
    public static String getChk30Num(String initChk30Num, String rsChk30Num) {
        return convChk30Num(isChk30Num(initChk30Num, rsChk30Num));
    }

    /**
     * Change of chk30num
     * @param chk30Num String
     * @return chk30Num String
     */
    public static String convChk30Num(String chk30Num) {
        if (S21StringUtil.isNotEmpty(chk30Num)) {
            if (chk30Num.length() > RCPT_CHK_NUM_INDEX) {
                int lastIndex = chk30Num.length();
                int startIndex = lastIndex - RCPT_CHK_NUM_INDEX;
                return chk30Num.substring(startIndex);
            } else {
                return chk30Num;
            }
        } else {
            return chk30Num;
        }
    }

    /**
     * @param rsChk30Num String
     * @param initChk30Num String
     * @return rtnChk30Num String
     */
    public static String isChk30Num(String initChk30Num, String rsChk30Num) {

        String rtnChk30Num = NFCConst.CST_DB_INIT_VAL_STR;

        if (!NFCConst.CST_CHK_30_NUM_NUMBER_ALL9.equals(initChk30Num)) {
            if (NFCConst.CST_CHK_30_NUM_NUMBER_ALL9.equals(rsChk30Num)) {
                rtnChk30Num = initChk30Num;
            } else {
                rtnChk30Num = rsChk30Num;
            }
        } else {
            rtnChk30Num = NFCConst.CST_CHK_30_NUM_NUMBER_ALL9;
        }

        return rtnChk30Num;
    }

    /**
     * Change of inv30num
     * @param inv30Num String
     * @return inv30Num String
     */
    public static String convInv30Num(String inv30Num) {
        if (S21StringUtil.isNotEmpty(inv30Num)) {
            if (inv30Num.length() > INV_30_NUM_INDEX) {
                int lastIndex = INV_30_NUM_INDEX;
                return inv30Num.substring(BIGIN_INDEX, lastIndex);
            } else {
                return inv30Num;
            }
        } else {
            return inv30Num;
        }
    }

    /**
     * Change of invnum
     * @param invNum String
     * @return invNum String
     */
    public static String convInvNum(String invNum) {
        if (S21StringUtil.isNotEmpty(invNum)) {
            if (invNum.length() > INV_NUM_INDEX) {
                int lastIndex = INV_NUM_INDEX;
                return invNum.substring(BIGIN_INDEX, lastIndex);
            } else {
                return invNum;
            }
        } else {
            return invNum;
        }
    }

    /**
     * @param csvId String
     * @param recordData String
     * @return rtnTMsg EZDTMsg
     */
    public static EZDTMsg convertDbString(int csvId, String recordData) {

        EZDTMsg rtnTMsg = new AR_RCPT_RCV_WRKTMsg();
        switch (csvId) {
            case CSV_ID_PAYMENTECH_1:
                AR_RCPT_RCV_WRKTMsg aaa = new AR_RCPT_RCV_WRKTMsg();
                rtnTMsg = aaa;
                break;
            case CSV_ID_PAYMENTECH_2:
                AR_RCPT_RCV_WRKTMsg bbb = new AR_RCPT_RCV_WRKTMsg();
                rtnTMsg = bbb;
                break;
            default:
                break;
        }
        return rtnTMsg;

    }

}
