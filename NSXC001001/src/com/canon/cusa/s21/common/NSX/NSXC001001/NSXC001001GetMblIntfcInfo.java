/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import business.db.S21_PSNTMsg;
import business.db.SVC_DISPT_EVENTTMsg;
import business.db.SVC_TASK_STSTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MBL_INTFC_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

/**
 * <pre>
 * Get Moble Interface Info
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/01/2016   Hitachi         T.Iwamoto       Create          QC#11185
 *</pre>
 */
public class NSXC001001GetMblIntfcInfo {

    /**
     * getMblIntfcInfo
     * @param bean NSXC001001GetMblIntfcInfoBean
     * @return
     */
    public static void getMblIntfcInfo(NSXC001001GetMblIntfcInfoBean bean) {

        // MblIntfcFlg
        if (!ZYPCommonFunc.hasValue(bean.getMblIntfcFlg())) {
            bean.setMblIntfcFlg(getMblIntfcFlg(bean.getGlblCmpyCd(), bean.getSvcDisptEventCd()));
        }

        // MblIntfcProcCd
        bean.setMblIntfcProcCd(getMblIntfcProcCd(bean));
    }

    private static String getMblIntfcFlg(String glblCmpyCd, String svcDisptEventCd) {
        SVC_DISPT_EVENTTMsg tMsg = new SVC_DISPT_EVENTTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.svcDisptEventCd, svcDisptEventCd);
        SVC_DISPT_EVENTTMsg outTMsg = (SVC_DISPT_EVENTTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (outTMsg == null) {
            return ZYPConstant.FLG_ON_Y;
        }
        return outTMsg.mblIntfcFlg.getValue();
    }

    private static String getMblIntfcProcCd(NSXC001001GetMblIntfcInfoBean bean) {
        // mblIntfcFlg check
        if (ZYPConstant.FLG_OFF_N.equals(bean.getMblIntfcFlg())) {
            return MBL_INTFC_PROC.NO_NEED;
        }

        // status check
        if (ZYPCommonFunc.hasValue(bean.getSvcTaskStsCd())) {
            SVC_TASK_STSTMsg srchSvcTaskStsTMsg = new SVC_TASK_STSTMsg();
            srchSvcTaskStsTMsg.glblCmpyCd.setValue(bean.getGlblCmpyCd());
            srchSvcTaskStsTMsg.svcTaskStsCd.setValue(bean.getSvcTaskStsCd());
            SVC_TASK_STSTMsg svcTaskStsTMsg = (SVC_TASK_STSTMsg) S21ApiTBLAccessor.findByKey(srchSvcTaskStsTMsg);
            if (svcTaskStsTMsg != null && ZYPConstant.FLG_OFF_N.equals(svcTaskStsTMsg.xtrnlSendReqFlg.getValue())) {
                return MBL_INTFC_PROC.NO_NEED;
            }
        }

        // person type check
        if (ZYPCommonFunc.hasValue(bean.getTechCd())) {
            S21_PSNTMsg srchS21PsnTMsg = new S21_PSNTMsg();
            srchS21PsnTMsg.glblCmpyCd.setValue(bean.getGlblCmpyCd());
            srchS21PsnTMsg.psnCd.setValue(bean.getTechCd());
            S21_PSNTMsg s21PsnTMsg = (S21_PSNTMsg) S21ApiTBLAccessor.findByKey(srchS21PsnTMsg);
            if (s21PsnTMsg != null && ZYPConstant.FLG_OFF_N.equals(s21PsnTMsg.delFlg.getValue()) && !PSN_TP.EMPLOYEE.equals(s21PsnTMsg.psnTpCd.getValue())) {
                return MBL_INTFC_PROC.NO_NEED;
            }
        }

        return MBL_INTFC_PROC.NOT_PROCESSED;
    }
}
