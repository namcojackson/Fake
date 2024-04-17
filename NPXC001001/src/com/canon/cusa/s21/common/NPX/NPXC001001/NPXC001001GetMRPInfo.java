/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NPX.NPXC001001;

import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import business.db.MRP_INFOTMsg;
import business.db.MRP_INFOTMsgArray;
import business.db.TECH_LOCTMsg;

/**
 * <pre>
 * GetParamMRPInfo
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/11/07   Hitachi         T.Aoyagi        Create          N/A
 * 2013/01/25   Hitachi         S.Tanabe        Update          QC456
 * 2013/03/19   Hitachi         T.Kanasaka      Update          QC788
 *</pre>
 */
public class NPXC001001GetMRPInfo {

    /**
     * MRP_INFO_ALLTECHNICIAN
     */
    // START 2013/03/19 T.Kanasaka [788 MOD]
    // private static String mrpInfoAlltechnician = "MRP_INFO_ALLTECHNICIAN";
    private static String mrpInfoAlltechnician = "PROCR_INFO_ALLTECHNICIAN";
    // END 2013/03/19 T.Kanasaka [788 MOD]

    /**
     * Get MRPInfo Record
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param invtyLocCd String
     * @return MRP_INFOTMsg
     */
    public static MRP_INFOTMsg getMRPInfo(String glblCmpyCd, String mdseCd, String invtyLocCd) {

        MRP_INFOTMsg mrpInfoTMsg = selectMRP_INFO(glblCmpyCd, mdseCd, invtyLocCd);

        if (mrpInfoTMsg == null) {

            if (0 < countTECH_LOC(glblCmpyCd, invtyLocCd)) {

                mrpInfoTMsg = selectMRP_INFO(glblCmpyCd, mdseCd, ZYPCodeDataUtil.getVarCharConstValue(mrpInfoAlltechnician, glblCmpyCd));
            }
        }

        return mrpInfoTMsg;
    }

    private static MRP_INFOTMsg selectMRP_INFO(String glblCmpyCd, String mdseCd, String invtyLocCd) {
        MRP_INFOTMsg tMsg = new MRP_INFOTMsg();
        // START 2013/01/25 S.Tanabe(QC456) UPD
        // ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd,
        // glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
        // ZYPEZDItemValueSetter.setValue(tMsg.invtyLocCd,
        // invtyLocCd);
        // return (MRP_INFOTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        MRP_INFOTMsg rMsg = null;
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("mdseCd01", mdseCd);
        tMsg.setConditionValue("invtyLocCd01", invtyLocCd);
        tMsg.setConditionValue("mrpInfoRgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        MRP_INFOTMsgArray resultMsgArray = (MRP_INFOTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (resultMsgArray.getValidCount() > 0) {
            rMsg = (MRP_INFOTMsg) resultMsgArray.get(0);
        }
        return rMsg;
        // START 2013/01/25 S.Tanabe(QC456) UPD
    }

    private static int countTECH_LOC(String glblCmpyCd, String invtyLocCd) {

        TECH_LOCTMsg tMsg = new TECH_LOCTMsg();

        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("techLocCd01", invtyLocCd);
        tMsg.setConditionValue("locRoleTpCd01", LOC_ROLE_TP.TECHNICIAN);
        tMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        return S21ApiTBLAccessor.count(tMsg);
    }

}
