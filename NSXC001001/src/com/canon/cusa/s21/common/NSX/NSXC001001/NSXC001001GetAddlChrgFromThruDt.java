/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

/**
 * <pre>
 * Get Additional Charge From Thru Date 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/05/2015   Hitachi         K.Kishimoto     Create
 * 03/08/2016   Hitachi         K.Kishimoto     Update          QC3406
 * 03/30/2016   Hitachi         K.Kishimoto     Update          QC5740
 * 2017/12/21   Hitachi         K.Kojima        Update          QC#18562
 * </pre>
 */
public class NSXC001001GetAddlChrgFromThruDt {

    // START 2017/12/22 K.Kojima [QC#18562,DEL]
    // END 2017/12/22 K.Kojima [QC#18562,DEL]

    public void getAddlChrgFromThruDt(AddlChrgFromThruDtInfo addlChrgFromThruDtInfo) {

        addlChrgFromThruDtInfo.setAddlChrgFromDt(null);
        addlChrgFromThruDtInfo.setAddlChrgThruDt(null);
        
        if (!checkMandatory(addlChrgFromThruDtInfo)) {
            return;
        }

        // START 2017/12/22 K.Kojima [QC#18562,ADD]
        String bllgFromDt = addlChrgFromThruDtInfo.getBllgFromDt();
        String bllgThruDt = addlChrgFromThruDtInfo.getBllgThruDt();
        String effFromDt = addlChrgFromThruDtInfo.getEffFromDt();
        String effThruDt = addlChrgFromThruDtInfo.getEffThruDt();
        if (effFromDt.compareTo(bllgFromDt) <= 0) {
            addlChrgFromThruDtInfo.setAddlChrgFromDt(bllgFromDt);
        } else {
            addlChrgFromThruDtInfo.setAddlChrgFromDt(effFromDt);
        }
        if (effThruDt.compareTo(bllgThruDt) >= 0) {
            addlChrgFromThruDtInfo.setAddlChrgThruDt(bllgThruDt);
        } else {
            addlChrgFromThruDtInfo.setAddlChrgThruDt(effThruDt);
        }
        // END 2017/12/22 K.Kojima [QC#18562,ADD]

        // START 2017/12/22 K.Kojima [QC#18562,DEL]
        // END 2017/12/22 K.Kojima [QC#18562,DEL]
    }

    // START 2017/12/22 K.Kojima [QC#18562,DEL]
    // END 2017/12/22 K.Kojima [QC#18562,DEL]

    private boolean checkMandatory(AddlChrgFromThruDtInfo addlChrgFromThruDtInfo) {
        if (!hasValue(addlChrgFromThruDtInfo.getEffFromDt())) {
            return false;
        }
        if (!hasValue(addlChrgFromThruDtInfo.getEffThruDt())) {
            return false;
        }
        // Del Start 03/08/2016 <QC#3406>
//        if (!hasValue(addlChrgFromThruDtInfo.getTrmnDt())) {
//            return false;
//        }
//        if (!hasValue(addlChrgFromThruDtInfo.getTrmnDt())) {
//            return false;
//        }
        // Del Start 03/08/2016 <QC#3406>
        // START 2017/12/22 K.Kojima [QC#18562,ADD]
        if (!hasValue(addlChrgFromThruDtInfo.getBllgFromDt())) {
            return false;
        }
        // END 2017/12/22 K.Kojima [QC#18562,ADD]
        if (!hasValue(addlChrgFromThruDtInfo.getBllgThruDt())) {
            return false;
        }
        return true;
    }

    // START 2017/12/22 K.Kojima [QC#18562,DEL]
    // END 2017/12/22 K.Kojima [QC#18562,DEL]

}
