/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC002001;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import business.db.FSR_VISITTMsg;
import business.db.FSR_VISITTMsgArray;
import business.db.SVC_TASKTMsg;
import business.db.SVC_TASKTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

/**
 * <pre>
 * Get Down Time
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/06/2016   Hitachi         K.Kasai         Create          N/A
 * </pre>
 */
public class NSXC002001GetDownTm {

    /** Date Time Format */
    private static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";

    /**
     * Get Down Time
     * @param glblCmpyCd String
     * @param svcTaskNum String
     * @return downTime BigDecimal
     */
    public static BigDecimal getDownTm(String glblCmpyCd, String svcTaskNum) {
        BigDecimal downTm = BigDecimal.ZERO;
        if (!checkParameter(glblCmpyCd, svcTaskNum)) {
            return downTm;
        }

        String svcTaskRcvDtTm = getSvcTaskRcvDtTm(glblCmpyCd, svcTaskNum);
        if (!ZYPCommonFunc.hasValue(svcTaskRcvDtTm)) {
            return downTm;
        }

        String fsrVisitCpltDtTm = getFsrVisitCpltDtTm(glblCmpyCd, svcTaskNum);
        if (!ZYPCommonFunc.hasValue(fsrVisitCpltDtTm)) {
            return downTm;
        }

        downTm = calcDownTm(svcTaskRcvDtTm, fsrVisitCpltDtTm);
        return downTm;
    }

    private static String getSvcTaskRcvDtTm(String glblCmpyCd, String svcTaskNum) {
        SVC_TASKTMsg tMsg = new SVC_TASKTMsg();
        tMsg.setSQLID("013");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        tMsg.setConditionValue("machDownFlg01", ZYPConstant.FLG_ON_Y);

        SVC_TASKTMsgArray tmsgList = (SVC_TASKTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (tmsgList.getValidCount() == 0) {
            return null;
        }

        String svcTaskRcvDtTm = tmsgList.no(0).svcTaskRcvDt.getValue().concat(tmsgList.no(0).svcTaskRcvTm.getValue());
        return svcTaskRcvDtTm;
    }

    private static String getFsrVisitCpltDtTm(String glblCmpyCd, String svcTaskNum) {
        FSR_VISITTMsg tMsg = new FSR_VISITTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("svcTaskNum01", svcTaskNum);

        FSR_VISITTMsgArray tmsgList = (FSR_VISITTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (tmsgList.getValidCount() == 0) {
            return null;
        }

        String fsrVisitCpltDtTm = tmsgList.no(0).fsrVisitCpltDt.getValue().concat(tmsgList.no(0).fsrVisitCpltTm.getValue());
        return fsrVisitCpltDtTm;
    }

    private static boolean checkParameter(String glblCmpyCd, String svcTaskNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(svcTaskNum)) {
            return false;
        }
        return true;
    }

    private static BigDecimal calcDownTm(String strFromDtTm, String strToDtTm) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date fromDtTm = null;
        Date toDtTm = null;
        try {
            fromDtTm = sdf.parse(strFromDtTm);
            toDtTm = sdf.parse(strToDtTm);
        } catch (ParseException e) {
            return BigDecimal.ZERO;
        }
        long timeDiff = (toDtTm.getTime() - fromDtTm.getTime()) / (1000 * 60);
        return BigDecimal.valueOf(timeDiff);
    }
}
