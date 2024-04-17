/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1530;

import static business.blap.NPAL1530.constant.NPAL1530Constant.DATEFORMAT;
import static business.blap.NPAL1530.constant.NPAL1530Constant.DB_PARAM_CMSG;
import static business.blap.NPAL1530.constant.NPAL1530Constant.DB_PARAM_MRP_ENBL_FLG;
import static business.blap.NPAL1530.constant.NPAL1530Constant.DB_PARAM_RGTN_STS_CD;
import static business.blap.NPAL1530.constant.NPAL1530Constant.EVENT_NM_NPAL1530_CMN_SUBMIT;
import static business.blap.NPAL1530.constant.NPAL1530Constant.MRP_RUN_PRM_SQ;
import static business.blap.NPAL1530.constant.NPAL1530Constant.NLAM0014E;
import static business.blap.NPAL1530.constant.NPAL1530Constant.NLAM1091E;
import static business.blap.NPAL1530.constant.NPAL1530Constant.ZZZM9003I;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1530.common.NPAL1530CommonLogic;
import business.db.MRP_INFOTMsg;
import business.db.MRP_INFOTMsgArray;
import business.db.MRP_RUN_PRMTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_INFO_RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_RUN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Business ID : NPAL1530 Min-Max Planning Report Run Screen
 * Function Name : Update Business Process
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/23/2016   CITS            Keiichi Masaki  Create          N/A
 * 10/04/2017   CITS            K.Fukumura      Update          QC#21230
 *</pre>
 */
public class NPAL1530BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPAL1530_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NPAL1530_SUBMIT((NPAL1530CMsg) cMsg, (NPAL1530SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
    /**
     * SUBMIT
     * @param cMsg NPAL1530CMsg
     * @param sMsg NPAL1530SMsg
     */
    private void doProcess_NPAL1530_SUBMIT(NPAL1530CMsg cMsg, NPAL1530SMsg sMsg) {

        if (NPAL1530CommonLogic.submitCheck(cMsg, sMsg)) {
            insertMrpRunPrmUpdateMrpInfo(cMsg, sMsg);
        }
    }
    /**
     * insert MRP_RUN_PRM update MRP_INFO
     * @param cMsg NPAL1530CMsg
     * @param sMsg NPAL1530SMsg
     */
     private void insertMrpRunPrmUpdateMrpInfo(NPAL1530CMsg cMsg, NPAL1530SMsg sMsg) {

         Map<String, Object> ssmParamInfPnlRtlWhSwh = new HashMap<String, Object>();
         
         ssmParamInfPnlRtlWhSwh.put(DB_PARAM_MRP_ENBL_FLG, ZYPConstant.FLG_ON_Y);
         ssmParamInfPnlRtlWhSwh.put(DB_PARAM_RGTN_STS_CD, MRP_INFO_RGTN_STS.AVAILABLE);
         ssmParamInfPnlRtlWhSwh.put(DB_PARAM_CMSG, cMsg);
         
         S21SsmEZDResult mrpInfPnlRtlWhSwhResult = NPAL1530Query.getInstance().getMrpInfoPlanWhSwh(ssmParamInfPnlRtlWhSwh);
         List<Map<String, Object>> mrpInfPlnRtlWhSwhList = (List<Map<String, Object>>) mrpInfPnlRtlWhSwhResult.getResultObject();
         
         // Get UserProfile
         S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
         S21UserInfo userInfo = profileService.getContextUserInfo();
         
         // Min(ItemCount) Group Search (Item Count Minimum)
         for (Map<String, Object> mrpInfPlnRtlWhSwh : mrpInfPlnRtlWhSwhList) {
             // Insert MRP_RUN_PRM
             MRP_RUN_PRMTMsg tMsgMrpRunPrm = new MRP_RUN_PRMTMsg();


             if (!ZYPCommonFunc.hasValue(cMsg.cratPrchReqFlg)) {
                 ZYPEZDItemValueSetter.setValue(cMsg.cratPrchReqFlg, ZYPConstant.FLG_OFF_N);
             }
             if (!ZYPCommonFunc.hasValue(cMsg.printItemStsFlg)) {
                 ZYPEZDItemValueSetter.setValue(cMsg.printItemStsFlg, ZYPConstant.FLG_OFF_N);
             }
             if (!ZYPCommonFunc.hasValue(cMsg.printItemDescFlg)) {
                 ZYPEZDItemValueSetter.setValue(cMsg.printItemDescFlg, ZYPConstant.FLG_OFF_N);
             }

             tMsgMrpRunPrm.mrpRunPrmPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(MRP_RUN_PRM_SQ));
             ZYPEZDItemValueSetter.setValue(tMsgMrpRunPrm.glblCmpyCd, cMsg.glblCmpyCd);
             ZYPEZDItemValueSetter.setValue(tMsgMrpRunPrm.mrpRunRqstTs, ZYPDateUtil.getCurrentSystemTime(DATEFORMAT));
             ZYPEZDItemValueSetter.setValue(tMsgMrpRunPrm.mrpRunRqstByCd, userInfo.getUserId());
             tMsgMrpRunPrm.mrpRunSchdId.clear();
             ZYPEZDItemValueSetter.setValue(tMsgMrpRunPrm.mrpRunStsCd, MRP_RUN_STS.READY_FOR_RUN);
             tMsgMrpRunPrm.mrpRunModeTpCd.clear();
             tMsgMrpRunPrm.mrpRunGrpId.clear();
             tMsgMrpRunPrm.lineBizTpCd.clear();
             ZYPEZDItemValueSetter.setValue(tMsgMrpRunPrm.mrpPlnNm, cMsg.mrpPlnNm);
             ZYPEZDItemValueSetter.setValue(tMsgMrpRunPrm.rtlWhCd, cMsg.rtlWhCd);
             ZYPEZDItemValueSetter.setValue(tMsgMrpRunPrm.rtlSwhCd, cMsg.rtlSwhCd);
             ZYPEZDItemValueSetter.setValue(tMsgMrpRunPrm.dmndCtoffDt, cMsg.dmndCtoffDt);
             ZYPEZDItemValueSetter.setValue(tMsgMrpRunPrm.dmndCtoffDaysAot, cMsg.dmndCtoffDaysAot);
             ZYPEZDItemValueSetter.setValue(tMsgMrpRunPrm.splyCtoffDt, cMsg.splyCtoffDt);
             ZYPEZDItemValueSetter.setValue(tMsgMrpRunPrm.splyCtoffDaysAot, cMsg.splyCtoffDaysAot);
             ZYPEZDItemValueSetter.setValue(tMsgMrpRunPrm.cratPrchReqFlg, cMsg.cratPrchReqFlg);
             ZYPEZDItemValueSetter.setValue(tMsgMrpRunPrm.printItemStsFlg, cMsg.printItemStsFlg);
             ZYPEZDItemValueSetter.setValue(tMsgMrpRunPrm.printItemDescFlg, cMsg.printItemDescFlg);

             S21FastTBLAccessor.insert(tMsgMrpRunPrm); 

             if (!RTNCD_NORMAL.equals(tMsgMrpRunPrm.getReturnCode())) {
                 cMsg.setMessageInfo(NLAM1091E);
                 cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                 return;
             }

             // Insert MRP_RUN_PRM
             List<MRP_INFOTMsg> updateMrpInfo = new ArrayList<MRP_INFOTMsg>();
             MRP_INFOTMsgArray tMsgArrayMrpInfo = new MRP_INFOTMsgArray();
             MRP_INFOTMsg tMsgMrpInfo = new MRP_INFOTMsg();

             tMsgMrpInfo.setSQLID("007");
             tMsgMrpInfo.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
             tMsgMrpInfo.setConditionValue("mrpEnblFlg01", ZYPConstant.FLG_ON_Y);
             tMsgMrpInfo.setConditionValue("mrpInfoRgtnStsCd01", MRP_INFO_RGTN_STS.AVAILABLE);
             // QC#21230 Start
             // tMsgMrpInfo.setConditionValue("mrpPlnNm01", cMsg.mrpPlnNm.getValue());
             // tMsgMrpInfo.setConditionValue("rtlWhCd01", cMsg.rtlWhCd.getValue());
             // tMsgMrpInfo.setConditionValue("rtlSwhCd01", cMsg.rtlSwhCd.getValue());
             tMsgMrpInfo.setConditionValue("mrpPlnNm01", mrpInfPlnRtlWhSwh.get("MRP_PLN_NM"));
             tMsgMrpInfo.setConditionValue("rtlWhCd01", mrpInfPlnRtlWhSwh.get("RTL_WH_CD"));
             tMsgMrpInfo.setConditionValue("rtlSwhCd01", mrpInfPlnRtlWhSwh.get("RTL_SWH_CD"));
             // QC#21230 End
             tMsgArrayMrpInfo = (MRP_INFOTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(tMsgMrpInfo);

             if (tMsgArrayMrpInfo.getValidCount() == 0) {
                 cMsg.setMessageInfo(NLAM0014E);
                 cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                 return;
             }

             for (int i = 0; i < tMsgArrayMrpInfo.getValidCount(); i++) {

                 ZYPEZDItemValueSetter.setValue(tMsgArrayMrpInfo.no(i).mrpRunPrmPk, tMsgMrpRunPrm.mrpRunPrmPk);
                 updateMrpInfo.add(tMsgArrayMrpInfo.no(i));
             }

             // Update DB
             if (0 < updateMrpInfo.size()) {
                 int ret = S21FastTBLAccessor.update(updateMrpInfo.toArray(new MRP_INFOTMsg[updateMrpInfo.size()]));
                 if (ret != updateMrpInfo.size()) {
                     cMsg.setMessageInfo(NLAM0014E);
                     cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                     return;
                 }
             }
         }

         // Normal End
         cMsg.setMessageInfo(ZZZM9003I, new String[] {"Submit" });
     }
}
