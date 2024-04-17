/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1530.common;

import static business.blap.NPAL1530.constant.NPAL1530Constant.DB_PARAM_CMSG;
import static business.blap.NPAL1530.constant.NPAL1530Constant.DB_PARAM_MRP_ENBL_FLG;
import static business.blap.NPAL1530.constant.NPAL1530Constant.DB_PARAM_RGTN_STS_CD;
import static business.blap.NPAL1530.constant.NPAL1530Constant.NPAM0224E;
import static business.blap.NPAL1530.constant.NPAL1530Constant.NPAM1495E;
import static business.blap.NPAL1530.constant.NPAL1530Constant.NPAM1573E;
import static business.blap.NPAL1530.constant.NPAL1530Constant.PLAN_NAME;
import static business.blap.NPAL1530.constant.NPAL1530Constant.SUB_WAREHOUSE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1530.NPAL1530CMsg;
import business.blap.NPAL1530.NPAL1530Query;
import business.blap.NPAL1530.NPAL1530SMsg;
import business.db.RTL_WHTMsg;
import business.db.SWHTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_INFO_RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_RUN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Business ID : NPAL1530 Min-Max Planning Report Run Screen
 * Function Name : Common Logic (BLAP)
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/23/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1530CommonLogic {
    /**
     * Set Warehouse Name
     * @param cMsg NPAL1530CMsg
     * @param sMsg NPAL1530SMsg
     */
    public static void setWarehouseName(NPAL1530CMsg cMsg, NPAL1530SMsg sMsg) {

        RTL_WHTMsg tMsg = new RTL_WHTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.rtlWhCd.setValue(cMsg.rtlWhCd.getValue());

        RTL_WHTMsg existTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (existTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, existTMsg.rtlWhNm.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.locTpCd, existTMsg.locTpCd.getValue());
        } else {
            cMsg.rtlWhNm.clear();
            cMsg.locTpCd.clear();
            cMsg.rtlWhCd.setErrorInfo(1, NPAM0224E, new String[] {cMsg.rtlWhCd.getValue() });
        }
    }

    /**
     * Set Sub Warehouse Name
     * @param cMsg NPAL1530CMsg
     * @param sMsg NPAL1530SMsg
     */
    public static void setSubWarehouseName(NPAL1530CMsg cMsg, NPAL1530SMsg sMsg) {

        SWHTMsg tMsg = new SWHTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.rtlSwhCd.setValue(cMsg.rtlSwhCd.getValue());

        SWHTMsg existTMsg = (SWHTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (existTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm, existTMsg.rtlSwhNm.getValue());
        } else {
            cMsg.rtlSwhNm.clear();
            cMsg.rtlSwhCd.setErrorInfo(1, NPAM0224E, new String[] {cMsg.rtlSwhCd.getValue() });
        }
    }

    /**
     * <pre>
     * Submit Check
     * </pre>
     * @param sMsg NPAL1530SMsg
     * @param cMsg NPAL1530CMsg
     * @return boolean
     */
    public static boolean submitCheck(NPAL1530CMsg cMsg, NPAL1530SMsg sMsg) {

        Map<String, Object> ssmParamPlnNm = new HashMap<String, Object>();
        Map<String, Object> ssmParamWhSwh = new HashMap<String, Object>();
        Map<String, Object> ssmParamMrpRunSts = new HashMap<String, Object>();
        
        ssmParamPlnNm.put(DB_PARAM_RGTN_STS_CD, MRP_INFO_RGTN_STS.AVAILABLE);
        ssmParamPlnNm.put(DB_PARAM_MRP_ENBL_FLG, ZYPConstant.FLG_ON_Y);
        ssmParamPlnNm.put(DB_PARAM_CMSG, cMsg);
        
        ssmParamWhSwh.put(DB_PARAM_RGTN_STS_CD, MRP_INFO_RGTN_STS.AVAILABLE);
        ssmParamWhSwh.put(DB_PARAM_MRP_ENBL_FLG, ZYPConstant.FLG_ON_Y);
        ssmParamWhSwh.put(DB_PARAM_CMSG, cMsg);
        
        ssmParamMrpRunSts.put(DB_PARAM_MRP_ENBL_FLG, ZYPConstant.FLG_ON_Y);
        ssmParamMrpRunSts.put(DB_PARAM_RGTN_STS_CD, MRP_INFO_RGTN_STS.AVAILABLE);

        List<String> mrpRunStsCdList = new ArrayList<String>();
        mrpRunStsCdList.add(MRP_RUN_STS.READY_FOR_RUN);
        mrpRunStsCdList.add(MRP_RUN_STS.PROCESSING);
        ssmParamMrpRunSts.put("mrpRunStsCdList", mrpRunStsCdList);

        if (PLAN_NAME.equals(cMsg.xxCmntTxt_SL.getValue())) {
           
            // Check Plan Name
            S21SsmEZDResult mrpPlanNameResult = NPAL1530Query.getInstance().getPlanName(ssmParamPlnNm);

            if (!mrpPlanNameResult.isCodeNormal()) {
                // QC#12117
                // cMsg.setMessageInfo(NPAM0224E, new String[]{cMsg.mrpPlnNm.getValue()});
                cMsg.setMessageInfo(NPAM1573E, new String[] {cMsg.mrpPlnNm.getValue() });
                return false;
            }

            cMsg.rtlWhCd.clear();
            cMsg.rtlWhNm.clear();
            cMsg.rtlSwhCd.clear();
            cMsg.rtlSwhNm.clear();
            ssmParamMrpRunSts.put(DB_PARAM_CMSG, cMsg);
            
            // Check MRP Run Status for Plan Name
            S21SsmEZDResult mrpRunStsResult = NPAL1530Query.getInstance().getMrpRunStatus(ssmParamMrpRunSts);

            if (mrpRunStsResult.isCodeNormal()) {
                cMsg.setMessageInfo(NPAM1495E);
                return false;
            }
        }
        if (SUB_WAREHOUSE.equals(cMsg.xxCmntTxt_SL.getValue())) {
            
            // QC#12117
            // Check WH/SWH
            S21SsmEZDResult mrpWhSwhResult = NPAL1530Query.getInstance().getWarehouseAndSubWarehouse(ssmParamWhSwh);

            if (!mrpWhSwhResult.isCodeNormal()) {
                cMsg.setMessageInfo(NPAM1573E, new String[]{cMsg.rtlWhCd.getValue() + "," + cMsg.rtlSwhCd.getValue()});
                return false;
            }

            setWarehouseName(cMsg, sMsg);
            setSubWarehouseName(cMsg, sMsg);

            cMsg.mrpPlnNm.clear();
            ssmParamMrpRunSts.put(DB_PARAM_CMSG, cMsg);

            // Check MRP Run Status  for WH/SWH
            S21SsmEZDResult mrpRunStsResult = NPAL1530Query.getInstance().getMrpRunStatus(ssmParamMrpRunSts);

            if (mrpRunStsResult.isCodeNormal()) {
                cMsg.setMessageInfo(NPAM1495E);
                return false;
            }
        }
        return true;
    }
}
