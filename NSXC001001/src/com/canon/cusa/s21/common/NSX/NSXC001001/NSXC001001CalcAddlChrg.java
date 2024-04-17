/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;

/**
 * <pre>
 * Calculation Additional Charge
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/05/2015   Hitachi         K.Kishimoto     Create
 * 03/08/2016   Hitachi         K.Kishimoto     Update          QC#3406
 * 04/17/2017   Hitachi         T.Tomita        Update          QC#18223
 * 2017/12/21   Hitachi         K.Kojima        Update          QC#18562
 * </pre>
 */
public class NSXC001001CalcAddlChrg {

    // START 2017/12/21 K.Kojima [QC#18562,ADD]
    /** DAYS_OF_YEAR */
    public static final int DAYS_OF_YEAR = 365;
    // END 2017/12/21 K.Kojima [QC#18562,ADD]

    public void calcAddrChrg(CalcAddlChrgInfo calcAddlChrgInfo) {

        calcAddlChrgInfo.setAddlChrgAmt(BigDecimal.ZERO);

        if (!checkMandatory(calcAddlChrgInfo)) {
            return;
        }

        // add start 2017/04/16 CSA Defect#18223
        if (!hasValue(calcAddlChrgInfo.getFlatRateAmt())) {
            calcAddlChrgInfo.setFlatRateAmt(BigDecimal.ZERO);
        }
        if (!hasValue(calcAddlChrgInfo.getAplcPct())) {
            calcAddlChrgInfo.setAplcPct(BigDecimal.ZERO);
        }
        // add end 2017/04/16 CSA Defect#18223

        // Mod Start 03/08/2016 <QC#3406>
        BigDecimal flatRateAmt = calcAddlChrgInfo.getFlatRateAmt();
        BigDecimal basePrcAmt = calcAddlChrgInfo.getBasePrcAmt();
        BigDecimal aplcPct = calcAddlChrgInfo.getAplcPct().divide(BigDecimal.valueOf(100));
        int aftDeclPntDigitNum = calcAddlChrgInfo.getAftDeclPntDigitNum().intValue();

        // START 2017/12/21 K.Kojima [QC#18562,ADD]
        String addlChrgFromDt = calcAddlChrgInfo.getAddlChrgFromDt();
        String addlChrgThueDt = calcAddlChrgInfo.getAddlChrgThueDt();
        String bllgFromDt = calcAddlChrgInfo.getBllgFromDt();
        String bllgThruDt = calcAddlChrgInfo.getBllgThruDt();
        String perBllgCycleCd = calcAddlChrgInfo.getPerBllgCycleCd();
        BigDecimal prrtDivRate = calcAddlChrgInfo.getPrrtDivRate();
        // END 2017/12/21 K.Kojima [QC#18562,ADD]

        if (flatRateAmt.compareTo(BigDecimal.ZERO) != 0) {
            // START 2017/12/21 K.Kojima [QC#18562,ADD]
            if (BLLG_CYCLE.DAILY.equals(perBllgCycleCd) || !addlChrgFromDt.equals(bllgFromDt) || !addlChrgThueDt.equals(bllgThruDt)) {
                int diffDays = ZYPDateUtil.getDiffDays(addlChrgThueDt, addlChrgFromDt) + 1;
                BigDecimal year = new BigDecimal(DAYS_OF_YEAR);
                BigDecimal divRate = year.divide(prrtDivRate, aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
                flatRateAmt = flatRateAmt.multiply(new BigDecimal(diffDays)).divide(divRate, aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
            }
            // END 2017/12/21 K.Kojima [QC#18562,ADD]
            calcAddlChrgInfo.setAddlChrgAmt(flatRateAmt);
        } else {
            // START 2017/12/21 K.Kojima [QC#18562,ADD]
            if (!addlChrgFromDt.equals(bllgFromDt) || !addlChrgThueDt.equals(bllgThruDt)) {
                int diffDaysBD = ZYPDateUtil.getDiffDays(bllgThruDt, bllgFromDt) + 1;
                int diffDaysAC = ZYPDateUtil.getDiffDays(addlChrgThueDt, addlChrgFromDt) + 1;
                basePrcAmt = basePrcAmt.multiply(new BigDecimal(diffDaysAC)).divide(new BigDecimal(diffDaysBD), aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
            }
            // END 2017/12/21 K.Kojima [QC#18562,ADD]
            calcAddlChrgInfo.setAddlChrgAmt(basePrcAmt.multiply(aplcPct).setScale(aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP));
        }
        // Mod End   03/08/2016 <QC#3406>
        return;
    }

    private boolean checkMandatory(CalcAddlChrgInfo calcAddlChrgInfo) {
        if (!hasValue(calcAddlChrgInfo.getBasePrcAmt())) {
            return false;
        }
        // del start 2017/04/16 CSA Defect#18223
//        if (!hasValue(calcAddlChrgInfo.getFlatRateAmt())) {
//            return false;
//        }
//        if (!hasValue(calcAddlChrgInfo.getAplcPct())) {
//            return false;
//        }
        // del end 2017/04/16 CSA Defect#18223
        // Del Start 03/08/2016 <QC#3406>
//        if (!hasValue(calcAddlChrgInfo.getInvUpToDt())) {
//            return false;
//        }
        // Del End   03/08/2016 <QC#3406>
        if (!hasValue(calcAddlChrgInfo.getBllgThruDt())) {
            return false;
        }
        if (!hasValue(calcAddlChrgInfo.getAftDeclPntDigitNum())) {
            return false;
        }
        return true;
    }
}
