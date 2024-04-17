/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0430.common;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDMessageInfo;
import business.blap.NSAL0430.NSAL0430CMsg;
import business.blap.NSAL0430.NSAL0430_DCMsg;
import business.blap.NSAL0430.NSAL0430_DCMsgArray;
import business.blap.NSAL0430.constant.NSAL0430Constant;

import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001SvcPhysMtrRead;
import com.canon.cusa.s21.common.NSX.NSXC003001.SvcPhysMtrReadInfoBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   CUSA            SRAA            Create          N/A
 * 2015/12/14   Hitachi         K.Kasai         Update          QC1896
 * 2016/07/07   Hitachi         K.Kishimoto     Update          QC#11466
 * 2017/09/06   Hitachi         A.Kohinata      Update          QC#15134
 * 2018/12/28   Hitachi         S.Kitamura      Update          QC#29718
 *</pre>
 */
public class NSAL0430CommonLogic {

    public static void resetParameter(NSAL0430CMsg cMsg) {
        BigDecimal svcMachMstrPk = cMsg.svcMachMstrPk.getValue();
        // ADD START 2015/12/14 K.Kasai [QC1896]
        String mtrEstProcDt = cMsg.mtrEstProcDt.getValue();
        // ADD END 2015/12/14 K.Kasai [QC1896]
        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(cMsg.D);
        ZYPTableUtil.clear(cMsg.P);
        ZYPEZDItemValueSetter.setValue(cMsg.svcMachMstrPk, svcMachMstrPk);
        // ADD START 2015/12/14 K.Kasai [QC1896]
        ZYPEZDItemValueSetter.setValue(cMsg.mtrEstProcDt, mtrEstProcDt);
        // ADD END 2015/12/14 K.Kasai [QC1896]
        String slsDt = getBizIdSlsDt();
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt, slsDt);
    }

    public static String getBizIdSlsDt() {
        String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, NSAL0430Constant.BIZ_ID);
        return slsDt;
    }

    public static void checkParameter(NSAL0430CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.svcMachMstrPk)) {
            cMsg.setMessageInfo(NSAL0430Constant.NSAM0219E, new String[] {"No input parameter found" });
        }
    }

    public static boolean isEqualNum(BigDecimal a, BigDecimal b) {
        if (a == null && b == null) {
            return true;
        }
        if (a != null && b != null && a.compareTo(b) == 0) {
            return true;
        }
        return false;
    }

    public static boolean hasError(NSAL0430CMsg cMsg) {
        if ("E".equals(cMsg.getMessageKind())) {
            return true;
        }
        try {
            Field field = NSAL0430CMsg.class.getSuperclass().getDeclaredField("errorHash");
            field.setAccessible(true);
            Map<String, EZDMessageInfo> errorHash = (Map<String, EZDMessageInfo>) field.get(cMsg);
            // Add Start 07/07/2016 <QC#11466>
            if (errorHash == null) {
                return false;
            }
            // Add End 07/07/2016 <QC#11466>
            return !errorHash.isEmpty();
        } catch (Exception e) {
            throw new S21AbendException(e);
        }
    }

    public static void clearEstimation(NSAL0430CMsg cMsg) {
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            cMsg.A.no(i).xxCopyUnitDaysQty_AE.clear();
            cMsg.A.no(i).readMtrCnt_AE.clear();
        }
    }

    public static SvcPhysMtrReadInfoBean toBean(NSAL0430_DCMsg msg) {
        SvcPhysMtrReadInfoBean bean = new SvcPhysMtrReadInfoBean();
        bean.setSvcPhysMtrReadPk(msg.svcPhysMtrReadPk_D.getValue());
        bean.setSvcPhysMtrPk(msg.svcPhysMtrPk_D.getValue());
        bean.setMtrReadDt(msg.mtrReadDt_D.getValue());
        bean.setReadMtrCnt(msg.readMtrCnt_D.getValue());
        return bean;
    }

    // START 2018/12/28 S.Kitamura [QC#29718,ADD]
    public static SvcPhysMtrReadInfoBean toBean(Map<String, Object> rsltMap) {
        SvcPhysMtrReadInfoBean bean = new SvcPhysMtrReadInfoBean();
        bean.setSvcPhysMtrReadPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_READ_PK"));
        bean.setSvcPhysMtrPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
        bean.setMtrReadDt((String) rsltMap.get("MTR_READ_DT"));
        bean.setReadMtrCnt((BigDecimal) rsltMap.get("READ_MTR_CNT"));
        return bean;
    }

    // END 2018/12/28 S.Kitamura [QC#29718,ADD]

    public static BigDecimal getAvgDlyCopyVol(NSAL0430_DCMsgArray msgArray, BigDecimal svcPhysMtrPk, BigDecimal svcPhysMtrReadGrpPk) {
        SvcPhysMtrReadInfoBean newRead = null;
        SvcPhysMtrReadInfoBean oldRead = null;
        for (int i = 0; i < msgArray.length(); i++) {
            BigDecimal cmpSvcPhysMtrPk = msgArray.no(i).svcPhysMtrPk_D.getValue();
            BigDecimal cmpSvcPhysMtrReadGrpPk = msgArray.no(i).svcPhysMtrReadGrpSq_D.getValue();
            if (newRead != null && oldRead == null && isEqualNum(svcPhysMtrPk, cmpSvcPhysMtrPk)) {
                oldRead = toBean(msgArray.no(i));
                break;
            }
            if (newRead == null && isEqualNum(svcPhysMtrPk, cmpSvcPhysMtrPk) && isEqualNum(svcPhysMtrReadGrpPk, cmpSvcPhysMtrReadGrpPk)) {
                newRead = toBean(msgArray.no(i));
            }
        }
        if (newRead != null && oldRead != null) {
            List<SvcPhysMtrReadInfoBean> mtrReadList = new ArrayList<SvcPhysMtrReadInfoBean>();
            mtrReadList.add(oldRead);
            mtrReadList.add(newRead);
            // mod start 2017/09/06 QC#15134
            //return NSXC003001SvcPhysMtrRead.calcAvgDlyCopyVol(mtrReadList);
            String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
            return NSXC003001SvcPhysMtrRead.calcAvgDlyCopyVol(glblCmpyCd, mtrReadList);
            // mod end 2017/09/06 QC#15134
        } else {
            return BigDecimal.ZERO;
        }
    }

    public static BigDecimal getReadMtrCnt(NSAL0430_DCMsgArray msgArray, BigDecimal svcPhysMtrPk, BigDecimal svcPhysMtrReadGrpSq) {
        for (int i = 0; i < msgArray.getValidCount(); i++) {
            BigDecimal cmpSvcPhysMtrPk = msgArray.no(i).svcPhysMtrPk_D.getValue();
            BigDecimal cmpSvcPhysMtrReadGrpSq = msgArray.no(i).svcPhysMtrReadGrpSq_D.getValue();
            if (isEqualNum(svcPhysMtrPk, cmpSvcPhysMtrPk) && isEqualNum(svcPhysMtrReadGrpSq, cmpSvcPhysMtrReadGrpSq)) {
                return msgArray.no(i).readMtrCnt_D.getValue();
            }
        }
        return null;
    }
}
