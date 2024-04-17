/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 *
 * Date         Company    Name         Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/16/2009   Canon      M.Moriyama   Create          N/A
 * 10/29/2010   Canon      I.Kondo      Update          change to S21OracleSeqAccessor.
 * </pre>
 */
package com.canon.cusa.s21.common.NFX.NFXC306001;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.common.S21OracleSeqAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * 
 */
public class NFCNumbering {

    /* */
    private static final BigDecimal EZD_ERR_GETBIGDECIMAL = new BigDecimal(-1);

    /**
     */
    public NFCNumbering() {
        super();

    }

    /**
     * <pre>
     * </pre>
     */
    public NFXC3060Bean getNumber(String oraSqId, String prefix, int figure) {
        NFXC3060Bean rtrnVal = new NFXC3060Bean();

        if (S21StringUtil.isEmpty(oraSqId)) {
            rtrnVal.setRtrnNo(NFCConst.CST_RTN_CD_ERR);
            return rtrnVal;
        }

        /*
         */
        if (figure == 0) {

            BigDecimal seqNo = S21OracleSeqAccessor.getSeqNumber(oraSqId);

            if (EZD_ERR_GETBIGDECIMAL.equals(seqNo)) {
                rtrnVal.setRtrnNo(NFCConst.CST_RTN_CD_ERR);
                return rtrnVal;
            } else {
                rtrnVal.setOraSqNo(seqNo);
            }

        } else {

            if (figure < 0) {

                rtrnVal.setRtrnNo(NFCConst.CST_RTN_CD_ERR);
                return rtrnVal;
            }

            String seqNoStr;
            seqNoStr = getNumberString(oraSqId, figure);

            if (S21StringUtil.isEmpty(seqNoStr)) {
                rtrnVal.setRtrnNo(NFCConst.CST_RTN_CD_ERR);
                return rtrnVal;
            } else {

                if (prefix != null) {
                    seqNoStr = prefix + seqNoStr;
                }
                rtrnVal.setOraSqNoStr(seqNoStr);
            }
        }
        rtrnVal.setRtrnNo(NFCConst.CST_RTN_CD_NORM);
        return rtrnVal;
    }

    /**
     * @param seqName String
     * @param digit int
     * @return String
     */
    private static String getNumberString(String seqName, int digit) {
        BigDecimal num = S21OracleSeqAccessor.getSeqNumber(seqName);
        if (!ZYPCommonFunc.hasValue(num)) {
            return null;
        }
        DecimalFormat fmt = new DecimalFormat(getFormatString(digit));
        String rtn = fmt.format(num);
        if (rtn.length() > digit) {
            throw new S21AbendException("NFCM0722E", new String[] {seqName, Integer.toString(digit), rtn });
        }
        return rtn;
    }

    /**
     * @param digit int
     * @return String
     */
    private static String getFormatString(int digit) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < digit; i++) {
            buf.append('0');
        }
        return buf.toString();
    }
}
