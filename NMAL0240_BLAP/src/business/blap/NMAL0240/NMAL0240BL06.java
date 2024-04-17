/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0240;

import static business.blap.NMAL0240.constant.NMAL0240Constant.BOM_ITEM;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CMPSN;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CMPSN_CHNG_REQ;
import static business.blap.NMAL0240.constant.NMAL0240Constant.COMPONENT;
import static business.blap.NMAL0240.constant.NMAL0240Constant.MDSE;
import static business.blap.NMAL0240.constant.NMAL0240Constant.MDSE_ITEM_STS;
import static business.blap.NMAL0240.constant.NMAL0240Constant.NMAM0072E;
import static business.blap.NMAL0240.constant.NMAL0240Constant.NMAM0137E;
import static business.blap.NMAL0240.constant.NMAL0240Constant.NMAM0176E;
import static business.blap.NMAL0240.constant.NMAL0240Constant.NMAM0177E;
import static business.blap.NMAL0240.constant.NMAL0240Constant.NMAM0208E;
import static business.blap.NMAL0240.constant.NMAL0240Constant.NMAM8111E;
import static business.blap.NMAL0240.constant.NMAL0240Constant.NMAM8283E;
import static business.blap.NMAL0240.constant.NMAL0240Constant.NMAM8390E;
import static business.blap.NMAL0240.constant.NMAL0240Constant.NMAM8402E;
import static business.blap.NMAL0240.constant.NMAL0240Constant.NMAM8493E;
import static business.blap.NMAL0240.constant.NMAL0240Constant.NZZM0003E;
import static business.blap.NMAL0240.constant.NMAL0240Constant.ORD_TAKE_LEN;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL0240.common.NMAL0240CommonLogic;
import business.db.CMPSNTMsg;
import business.db.CMPSN_CHNG_REQTMsg;
import business.db.CMPSN_MSGTMsg;
import business.db.MDSETMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL0240BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Fujitsu         C.Tanaka        Create          CSA
 * 2016/06/23   SRAA            K.Aratani       Update          QC#10653
 * 2017/02/01   Fujitsu         M.Ohno          Update          QC#17335
 * 2018/03/14   CITS            K.Ogino         Update          QC#22324
 *</pre>
 */
public class NMAL0240BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL0240CMsg bizMsg = (NMAL0240CMsg) cMsg;
            NMAL0240SMsg glblMsg = (NMAL0240SMsg) sMsg;

            if ("NMAL0240Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL0240Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL0240Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NMAL0240Scrn00_CMN_Delete(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    private void doProcess_NMAL0240Scrn00_CMN_Submit(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {

        int length = bizMsg.A.getValidCount();
        if (length == 0) {
            boolean changedFlg = deleteCmpsnReq(bizMsg, glblMsg);
            if (!changedFlg) {
                // No change has been made.
                bizMsg.setMessageInfo("NMAM8333I");
            }
            return;
        }

        if (!errCheck(bizMsg, glblMsg)) {
            return;
        }

        NMAL0240_ACMsg aCMsg = null;

        // 2017/02/01 S21_NA#17335 Add Start
        for (int i = 0; i < length; i++) {
            aCMsg = bizMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(aCMsg.mainCmpsnFlg_A1, ZYPConstant.FLG_OFF_N);
        }
        // 2017/02/01 S21_NA#17335 Add End

        String slsDt = ZYPDateUtil.getSalesDate();
        // Save Composition
        boolean updEffThruDt = true;
        boolean changedFlg = false;
        // QC#10653
        List<String> mdseCdDealConfigList = new ArrayList<String>();
        for (int i = 0; i < length; i++) {
            aCMsg = bizMsg.A.no(i);

            // Skip Set Component
            if (ZYPCommonFunc.hasValue(aCMsg.xxNum_A2)) {
                continue;
            }

            if (0 == ZYPDateUtil.compare(slsDt, bizMsg.effFromDt_A.getValue())) {
                if (!ZYPCommonFunc.hasValue(aCMsg.cmpsnPk_A1)) {
                    insertCmpsn(bizMsg, aCMsg, mdseCdDealConfigList);
                    changedFlg = true;
                } else {
                    // if Batch was not process request and effective
                    // from date came.
                    if (ZYPCommonFunc.hasValue(aCMsg.cmpsnChngReqPk_A1)) {
                        if (isChangedReq(bizMsg, aCMsg)) {
                            updateCmpsnChngReq(bizMsg, aCMsg);
                            changedFlg = true;
                        }
                    }
                }
                // if effective from date is future day, create
                // CMPSN_CHNG_REQ
            } else {
                updEffThruDt = false;
                if (ZYPCommonFunc.hasValue(aCMsg.cmpsnChngReqPk_A1)) {
                    if (isChangedReq(bizMsg, aCMsg)) {
                        updateCmpsnChngReq(bizMsg, aCMsg);
                        changedFlg = true;
                    }
                } else {
                    insertCmpsnChngReq(bizMsg, aCMsg);
                    changedFlg = true;
                }
            }
        }

        saveMdseInfo(bizMsg);

        // No Open PopupScreen set CMPSN_MSG
        if (!ZYPCommonFunc.hasValue(bizMsg.R.no(0).cmpsnMsgPk)) {
            String mdseCd = bizMsg.mdseCd.getValue();
            String glblCmpyCd = getGlobalCompanyCode();

            MDSETMsg mdseTMsg = new MDSETMsg();
            ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
            mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);

            if (mdseTMsg == null) {
                return;
            }

            S21SsmEZDResult ssmResult = NMAL0240Query.getInstance().getPoMessageList(glblCmpyCd, mdseCd, bizMsg.R.length());

            if (ssmResult.isCodeNormal()) {

                // Search Data Set
                List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();

                for (Map<String, Object> cmpsnMsgMap : resultMapList) {
                    BigDecimal msgPk = (BigDecimal) cmpsnMsgMap.get("CMPSN_MSG_PK");
                    BigDecimal segID = (BigDecimal) cmpsnMsgMap.get("CMPSN_MSG_SEG_ID");
                    String msgTxt = (String) cmpsnMsgMap.get("CMPSN_MSG_TXT");

                    int count = segID.intValue();
                    NMAL0240_RCMsg rCMsg = bizMsg.R.no(count - 1);
                    ZYPEZDItemValueSetter.setValue(rCMsg.cmpsnMsgPk, msgPk);
                    ZYPEZDItemValueSetter.setValue(rCMsg.cmpsnMsgSegId, segID);
                    ZYPEZDItemValueSetter.setValue(rCMsg.cmpsnMsgTxt, msgTxt);
                }
            }
        }

        // QC#22324 Save CMPSN_MSG
        for (int i = 0; i < bizMsg.R.length(); i++) {

            boolean isRegist = false;
            NMAL0240_RCMsg rcMsg = bizMsg.R.no(i);

            CMPSN_MSGTMsg tMsg = new CMPSN_MSGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(tMsg.prntMdseCd, bizMsg.mdseCd);

            if (ZYPCommonFunc.hasValue(rcMsg.cmpsnMsgPk)) {
                ZYPEZDItemValueSetter.setValue(tMsg.cmpsnMsgPk, rcMsg.cmpsnMsgPk);
                isRegist = true;
                tMsg = (CMPSN_MSGTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                if (tMsg == null) {
                    bizMsg.setMessageInfo(NMAM8111E, new String[] {"CMPSN_MSG" });
                    return;
                }
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    return;
                }
            } else {
                BigDecimal cmpsnMsgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CMPSN_MSG_SQ);
                ZYPEZDItemValueSetter.setValue(tMsg.cmpsnMsgPk, cmpsnMsgPk);
                isRegist = false;
            }
            if (ZYPCommonFunc.hasValue(rcMsg.cmpsnMsgSegId)) {
                ZYPEZDItemValueSetter.setValue(tMsg.cmpsnMsgSegId, rcMsg.cmpsnMsgSegId);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.cmpsnMsgSegId, new BigDecimal(i + 1));
            }
            ZYPEZDItemValueSetter.setValue(tMsg.cmpsnMsgTxt, rcMsg.cmpsnMsgTxt);

            if (isRegist) {
                EZDTBLAccessor.update(tMsg);
                if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"CMPSN_MSG" });
                    return;
                }
            } else {
                EZDTBLAccessor.insert(tMsg);
                if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0176E, new String[] {"CMPSN_MSG" });
                    return;
                }
            }

        }

        if (updEffThruDt) {
            updateEffThruDt(bizMsg);
        }

        boolean changedFlg2 = deleteCmpsnReq(bizMsg, glblMsg);

        if (!changedFlg && !changedFlg2) {
            // No change has been made.
            bizMsg.setMessageInfo("NMAM8333I");
        } else {
            // QC#10653
            if (!mdseCdDealConfigList.isEmpty()) {
                // invoke Master Data Messaging
                NMAL0240CommonLogic.invokeMasterDataMessaging(mdseCdDealConfigList);
            }
        }
    }

    /**
     * CMN_Delete Event
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    @SuppressWarnings("unchecked")
    private void doProcess_NMAL0240Scrn00_CMN_Delete(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {

        int count = 0;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).cmpsnChngReqPk_A1)) {
                count++;
            }
        }
        int length = glblMsg.A.getValidCount();
        String glblCmpyCd = getGlobalCompanyCode();
        String prntMdseCd = glblMsg.mdseCd.getValue();

        boolean cmpsnExistsFlg = false;
        S21SsmEZDResult r = NMAL0240Query.getInstance().findCmpsn(bizMsg.mdseCd.getValue());
        if (r.isCodeNormal()) {
            Map<String, Object> resultMap = (Map<String, Object>) r.getResultObject();
            if (resultMap != null && resultMap.get("CMPSN_PK") != null) {
                cmpsnExistsFlg = true;
            }
        }

        // if CUSA BOM & Already populate on CMPSN, Error
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox.getValue()) && cmpsnExistsFlg) {
            // Error
            // NMAM8402E=0,This components are already imported from
            // CUSA to CSA.
            bizMsg.mdseCd.setErrorInfo(1, NMAM8402E, new String[] {bizMsg.mdseCd.getValue() });
            return;
        }

        CMPSN_CHNG_REQTMsg[] cmpsnChngReqTMsgArr = new CMPSN_CHNG_REQTMsg[count];

        int cnt = 0;
        NMAL0240_ASMsg aSMsg = null;
        for (int i = 0; i < length; i++) {
            aSMsg = glblMsg.A.no(i);
            if (ZYPCommonFunc.hasValue(aSMsg.cmpsnChngReqPk_A1)) {

                CMPSN_CHNG_REQTMsg cmpsnChngReqTMsg = new CMPSN_CHNG_REQTMsg();
                ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.cmpsnChngReqPk, aSMsg.cmpsnChngReqPk_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.cmpsnPk, aSMsg.cmpsnPk_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.prntMdseCd, prntMdseCd);

                cmpsnChngReqTMsg = (CMPSN_CHNG_REQTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(cmpsnChngReqTMsg);
                if (!RTNCD_NORMAL.equals(cmpsnChngReqTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM8111E, new String[] {CMPSN_CHNG_REQ });
                    return;
                }
                cmpsnChngReqTMsgArr[cnt] = cmpsnChngReqTMsg;

                cnt++;
            }
        }

        if (0 < cnt) {
            int result1 = S21FastTBLAccessor.removeLogical(cmpsnChngReqTMsgArr);
            if (result1 != cnt) {
                bizMsg.setMessageInfo(NMAM0208E, new String[] {CMPSN_CHNG_REQ });
                return;
            }
        }
    }

    private void insertCmpsn(NMAL0240CMsg bizMsg, NMAL0240_ACMsg aCMsg, List<String> mdseCdDealConfigList) {

        // QC#10653
        boolean isExistsMdse = NMAL0240CommonLogic.existsCmpsn(getGlobalCompanyCode(), bizMsg.mdseCd.getValue());

        CMPSNTMsg cmpsnTMsg = new CMPSNTMsg();

        ZYPEZDItemValueSetter.setValue(cmpsnTMsg.glblCmpyCd, getGlobalCompanyCode());

        ZYPEZDItemValueSetter.setValue(cmpsnTMsg.cmpsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CMPSN_SQ));
        ZYPEZDItemValueSetter.setValue(aCMsg.cmpsnPk_A1, cmpsnTMsg.cmpsnPk.getValue());

        ZYPEZDItemValueSetter.setValue(cmpsnTMsg.prntMdseCd, bizMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnTMsg.childMdseQty, aCMsg.childMdseQty_A1.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnTMsg.effFromDt, bizMsg.effFromDt_A.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnTMsg.effThruDt, bizMsg.effThruDt_A.getValue());

        ZYPEZDItemValueSetter.setValue(cmpsnTMsg.mainCmpsnFlg, aCMsg.mainCmpsnFlg_A1.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnTMsg.allocRatio, new BigDecimal(0));

        EZDCStringItem ordTakeMdseCd = aCMsg.ordTakeMdseCd_A1;
        EZDCStringItem mdseCmpsnTpCd = aCMsg.mdseCmpsnTpCd_A1;
        if (ZYPCommonFunc.hasValue(ordTakeMdseCd)) {
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.childOrdTakeMdseCd, ordTakeMdseCd.getValue());
            if (ZYPCommonFunc.hasValue(mdseCmpsnTpCd)) {
                ZYPEZDItemValueSetter.setValue(cmpsnTMsg.mdseCmpsnTpCd, mdseCmpsnTpCd.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(cmpsnTMsg.mdseCmpsnTpCd, MDSE_CMPSN_TP.SET_ORDERTAKE_MDSE);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.childMdseCd, aCMsg.mdseCd_A1.getValue());
            if (ZYPCommonFunc.hasValue(mdseCmpsnTpCd)) {
                ZYPEZDItemValueSetter.setValue(cmpsnTMsg.mdseCmpsnTpCd, mdseCmpsnTpCd.getValue());
            } else {
                String mdseItemTpCd = bizMsg.mdseItemTpCd.getValue();
                if (MDSE_ITEM_TP.SET.equals(mdseItemTpCd)) {
                    ZYPEZDItemValueSetter.setValue(cmpsnTMsg.mdseCmpsnTpCd, MDSE_CMPSN_TP.SET_MDSE);
                } else if (MDSE_ITEM_TP.KIT.equals(mdseItemTpCd)) {
                    ZYPEZDItemValueSetter.setValue(cmpsnTMsg.mdseCmpsnTpCd, MDSE_CMPSN_TP.KIT_MDSE);
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(cmpsnTMsg.baseCmptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cmpsnTMsg.mndCmptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cmpsnTMsg.actvFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cmpsnTMsg.cmpsnRevnNum, bizMsg.cmpsnRevnNum_A.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnTMsg.cmpsnRevnCmntTxt, bizMsg.cmpsnRevnCmntTxt_A.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnTMsg.pkgUomCd, aCMsg.basePkgUomCd_A1.getValue());

        S21FastTBLAccessor.insert(cmpsnTMsg);
        if (!RTNCD_NORMAL.equals(cmpsnTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NMAM0177E, new String[] {CMPSN });
            return;
        }

        // QC#10653
        if (!mdseCdDealConfigList.contains(cmpsnTMsg.prntMdseCd.getValue())) {
            mdseCdDealConfigList.add(cmpsnTMsg.prntMdseCd.getValue());
        }

    }

    private void updateEffThruDt(NMAL0240CMsg bizMsg) {

        String slsDt = ZYPDateUtil.getSalesDate();
        String newFromDt = bizMsg.effFromDt_A.getValue();
        String endDt = ZYPDateUtil.addDays(newFromDt, -1);
        String effThruDt = bizMsg.effThruDt_B.getValue();
        String effFromDt = bizMsg.effFromDt_B.getValue();
        if ((0 <= ZYPDateUtil.compare(newFromDt, effFromDt) && 0 < ZYPDateUtil.compare(effThruDt, endDt)) || !ZYPCommonFunc.hasValue(bizMsg.effThruDt_B)) {
            effThruDt = endDt;
        }

        if (0 > ZYPDateUtil.compare(effThruDt, effFromDt)) {
            effThruDt = effFromDt;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.effThruDt_B, effThruDt);

        CMPSNTMsg cmpsnTMsg = new CMPSNTMsg();
        NMAL0240_BCMsg bCMsg = null;
        String thruDt = bizMsg.effThruDt_B.getValue();
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            bCMsg = bizMsg.B.no(i);

            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.cmpsnPk, bCMsg.cmpsnPk_B1.getValue());
            ZYPEZDItemValueSetter.setValue(cmpsnTMsg.prntMdseCd, bizMsg.mdseCd.getValue());

            cmpsnTMsg = (CMPSNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(cmpsnTMsg);
            if (cmpsnTMsg == null) {
                bizMsg.setMessageInfo(NMAM8111E, new String[] {CMPSN });
                return;
            }

            if (!ZYPDateUtil.isSameTimeStamp(bCMsg.ezUpTime_B1.getValue(), bCMsg.ezUpTimeZone_B1.getValue(), cmpsnTMsg.ezUpTime.getValue(), cmpsnTMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            }

            if (ZYPDateUtil.compare(cmpsnTMsg.effThruDt.getValue(), thruDt) == 0) {
                return;
            }

            if (ZYPDateUtil.compare(effFromDt, effThruDt) == 0 && ZYPDateUtil.compare(slsDt, effFromDt) == 0) {
                // Logical Remove
                S21FastTBLAccessor.removeLogical(new CMPSNTMsg[] {cmpsnTMsg });
                if (!RTNCD_NORMAL.equals(cmpsnTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {CMPSN });
                    return;
                }

            } else {
                // Update
                ZYPEZDItemValueSetter.setValue(cmpsnTMsg.effThruDt, thruDt);
                ZYPEZDItemValueSetter.setValue(cmpsnTMsg.actvFlg, ZYPConstant.FLG_OFF_N);

                S21FastTBLAccessor.update(cmpsnTMsg);
                if (!RTNCD_NORMAL.equals(cmpsnTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {CMPSN });
                    return;
                }
            }

        }
    }

    private void insertCmpsnChngReq(NMAL0240CMsg bizMsg, NMAL0240_ACMsg aCMsg) {

        CMPSN_CHNG_REQTMsg cmpsnChngReqTMsg = new CMPSN_CHNG_REQTMsg();

        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.glblCmpyCd, getGlobalCompanyCode());

        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.cmpsnChngReqPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CMPSN_CHNG_REQ_SQ));
        ZYPEZDItemValueSetter.setValue(aCMsg.cmpsnChngReqPk_A1, cmpsnChngReqTMsg.cmpsnChngReqPk.getValue());

        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.cmpsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CMPSN_SQ));
        ZYPEZDItemValueSetter.setValue(aCMsg.cmpsnPk_A1, cmpsnChngReqTMsg.cmpsnPk.getValue());

        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.prntMdseCd, bizMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.childMdseQty, aCMsg.childMdseQty_A1.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.effFromDt, bizMsg.effFromDt_A.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.effThruDt, bizMsg.effThruDt_A.getValue());

        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.mainCmpsnFlg, aCMsg.mainCmpsnFlg_A1.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.allocRatio, new BigDecimal(0));

        cmpsnChngReqTMsg.childOrdTakeMdseCd.clear();
        cmpsnChngReqTMsg.childMdseCd.clear();
        if (ZYPCommonFunc.hasValue(aCMsg.ordTakeMdseCd_A1)) {
            ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.childOrdTakeMdseCd, aCMsg.ordTakeMdseCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.mdseCmpsnTpCd, MDSE_CMPSN_TP.SET_ORDERTAKE_MDSE);
        } else {
            ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.childMdseCd, aCMsg.mdseCd_A1.getValue());
            if (MDSE_ITEM_TP.SET.equals(bizMsg.mdseItemTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.mdseCmpsnTpCd, MDSE_CMPSN_TP.SET_MDSE);
            } else if (MDSE_ITEM_TP.KIT.equals(bizMsg.mdseItemTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.mdseCmpsnTpCd, MDSE_CMPSN_TP.KIT_MDSE);
            }
        }

        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.baseCmptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.mndCmptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.actvFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.cmpsnRevnNum, bizMsg.cmpsnRevnNum_A.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.cmpsnRevnCmntTxt, bizMsg.cmpsnRevnCmntTxt_A.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.pkgUomCd, aCMsg.basePkgUomCd_A1.getValue());

        S21FastTBLAccessor.insert(cmpsnChngReqTMsg);
        if (!RTNCD_NORMAL.equals(cmpsnChngReqTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NMAM0177E, new String[] {CMPSN_CHNG_REQ });
            return;
        }
    }

    private boolean isChangedReq(NMAL0240CMsg bizMsg, NMAL0240_ACMsg aCMsg) {

        CMPSN_CHNG_REQTMsg cmpsnChngReqTMsg = new CMPSN_CHNG_REQTMsg();
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.cmpsnChngReqPk, aCMsg.cmpsnChngReqPk_A1.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.cmpsnPk, aCMsg.cmpsnPk_A1.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.prntMdseCd, bizMsg.mdseCd.getValue());
        cmpsnChngReqTMsg = (CMPSN_CHNG_REQTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(cmpsnChngReqTMsg);
        if (cmpsnChngReqTMsg == null) {
            return true;
        }

        String mdseCmpsnTpCd = "";
        if (ZYPCommonFunc.hasValue(aCMsg.ordTakeMdseCd_A1)) {
            mdseCmpsnTpCd = MDSE_CMPSN_TP.SET_ORDERTAKE_MDSE;
        } else {
            if (MDSE_ITEM_TP.SET.equals(bizMsg.mdseItemTpCd.getValue())) {
                mdseCmpsnTpCd = MDSE_CMPSN_TP.SET_MDSE;
            } else if (MDSE_ITEM_TP.KIT.equals(bizMsg.mdseItemTpCd.getValue())) {
                mdseCmpsnTpCd = MDSE_CMPSN_TP.KIT_MDSE;
            }
        }
        if (!isEquals(cmpsnChngReqTMsg.childMdseQty.getValue(), aCMsg.childMdseQty_A1.getValue()) || !isEquals(cmpsnChngReqTMsg.effFromDt.getValue(), bizMsg.effFromDt_A.getValue())
                || !isEquals(cmpsnChngReqTMsg.effThruDt.getValue(), bizMsg.effThruDt_A.getValue()) || !isEquals(cmpsnChngReqTMsg.mainCmpsnFlg.getValue(), aCMsg.mainCmpsnFlg_A1.getValue())
                || !isEquals(cmpsnChngReqTMsg.childMdseCd.getValue(), aCMsg.mdseCd_A1.getValue()) || !isEquals(cmpsnChngReqTMsg.childOrdTakeMdseCd.getValue(), aCMsg.ordTakeMdseCd_A1.getValue())
                || !isEquals(cmpsnChngReqTMsg.mdseCmpsnTpCd.getValue(), mdseCmpsnTpCd) || !isEquals(cmpsnChngReqTMsg.cmpsnRevnNum.getValue(), bizMsg.cmpsnRevnNum_A.getValue())
                || !isEquals(cmpsnChngReqTMsg.cmpsnRevnCmntTxt.getValue(), bizMsg.cmpsnRevnCmntTxt_A.getValue())) {
            return true;
        }

        return false;
    }

    private boolean isEquals(String val1, String val2) {
        String str1 = "";
        if (val1 != null) {
            str1 = val1;
        }
        String str2 = "";
        if (val2 != null) {
            str2 = val2;
        }
        if (str1.equals(str2)) {
            return true;
        }
        return false;
    }

    private boolean isEquals(BigDecimal val1, BigDecimal val2) {
        if (val1 != null) {
            if (val2 == null) {
                return false;
            }
        } else {
            if (val2 == null) {
                return true;
            } else {
                return false;
            }
        }
        if (val1.compareTo(val2) == 0) {
            return true;
        }
        return false;
    }

    private void updateCmpsnChngReq(NMAL0240CMsg bizMsg, NMAL0240_ACMsg aCMsg) {

        CMPSN_CHNG_REQTMsg cmpsnChngReqTMsg = new CMPSN_CHNG_REQTMsg();

        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.glblCmpyCd, getGlobalCompanyCode());

        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.cmpsnChngReqPk, aCMsg.cmpsnChngReqPk_A1.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.cmpsnPk, aCMsg.cmpsnPk_A1.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.prntMdseCd, bizMsg.mdseCd.getValue());
        cmpsnChngReqTMsg = (CMPSN_CHNG_REQTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(cmpsnChngReqTMsg);
        if (cmpsnChngReqTMsg == null) {
            bizMsg.setMessageInfo(NMAM8111E, new String[] {CMPSN_CHNG_REQ });
            return;
        }

        if (!ZYPDateUtil.isSameTimeStamp(aCMsg.ezUpTime_A1.getValue(), aCMsg.ezUpTimeZone_A1.getValue(), cmpsnChngReqTMsg.ezUpTime.getValue(), cmpsnChngReqTMsg.ezUpTimeZone.getValue())) {
            bizMsg.setMessageInfo(NZZM0003E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.childMdseQty, aCMsg.childMdseQty_A1.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.effFromDt, bizMsg.effFromDt_A.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.effThruDt, bizMsg.effThruDt_A.getValue());

        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.mainCmpsnFlg, aCMsg.mainCmpsnFlg_A1.getValue());

        cmpsnChngReqTMsg.childOrdTakeMdseCd.clear();
        cmpsnChngReqTMsg.childMdseCd.clear();
        if (ZYPCommonFunc.hasValue(aCMsg.ordTakeMdseCd_A1)) {
            ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.childOrdTakeMdseCd, aCMsg.ordTakeMdseCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.mdseCmpsnTpCd, MDSE_CMPSN_TP.SET_ORDERTAKE_MDSE);
        } else {
            ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.childMdseCd, aCMsg.mdseCd_A1.getValue());
            if (MDSE_ITEM_TP.SET.equals(bizMsg.mdseItemTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.mdseCmpsnTpCd, MDSE_CMPSN_TP.SET_MDSE);
            } else if (MDSE_ITEM_TP.KIT.equals(bizMsg.mdseItemTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.mdseCmpsnTpCd, MDSE_CMPSN_TP.KIT_MDSE);
            }
        }
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.actvFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.cmpsnRevnNum, bizMsg.cmpsnRevnNum_A.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.cmpsnRevnCmntTxt, bizMsg.cmpsnRevnCmntTxt_A.getValue());
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.pkgUomCd, aCMsg.basePkgUomCd_A1.getValue());

        S21FastTBLAccessor.update(cmpsnChngReqTMsg);
        if (!RTNCD_NORMAL.equals(cmpsnChngReqTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NMAM0177E, new String[] {CMPSN_CHNG_REQ });
            return;
        }
    }

    @SuppressWarnings("unchecked")
    private void saveMdseInfo(NMAL0240CMsg bizMsg) {

        String mdseCd = bizMsg.mdseCd.getValue();
        S21SsmEZDResult ssmResult = NMAL0240Query.getInstance().findMdseItemSts(mdseCd);
        if (ssmResult.isCodeNormal()) {

            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();

            MDSETMsg mdseTMsg = new MDSETMsg();
            ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
            mdseTMsg = (MDSETMsg) S21FastTBLAccessor.findByKey(mdseTMsg);
            if (mdseTMsg == null) {
                bizMsg.setMessageInfo(NMAM8111E, new String[] {MDSE });
                return;
            }
            ZYPEZDItemValueSetter.setValue(mdseTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
            ZYPEZDItemValueSetter.setValue(mdseTMsg.dsctnFlg, (String) resultMap.get("DSCTN_FLG"));
            ZYPEZDItemValueSetter.setValue(mdseTMsg.sellHldFlg, (String) resultMap.get("SELL_HLD_FLG"));
            S21FastTBLAccessor.update(mdseTMsg);
            if (!RTNCD_NORMAL.equals(mdseTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {MDSE });
                return;
            }
        } else {
            bizMsg.setMessageInfo(NMAM8111E, new String[] {MDSE_ITEM_STS });
            return;
        }
    }

    @SuppressWarnings("unchecked")
    private boolean errCheck(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {

        // Duplicate Check
        int length = bizMsg.A.getValidCount();
        NMAL0240_ACMsg aCMsg1 = null;
        NMAL0240_ACMsg aCMsg2 = null;
        for (int i = 0; i < length; i++) {
            aCMsg1 = bizMsg.A.no(i);
            for (int j = 0; j < length; j++) {
                aCMsg2 = bizMsg.A.no(j);
                if (i != j && aCMsg1.mdseCd_A1.getValue().equals(aCMsg2.mdseCd_A1.getValue())) {
                    aCMsg1.mdseCd_A1.setErrorInfo(1, NMAM0072E, new String[] {COMPONENT });
                    aCMsg2.mdseCd_A1.setErrorInfo(1, NMAM0072E, new String[] {COMPONENT });
                    return false;
                }
            }
        }

        // BOM Item# Check
        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, bizMsg.mdseCd.getValue());
        mdseTMsg = (MDSETMsg) S21FastTBLAccessor.findByKey(mdseTMsg);
        if (mdseTMsg == null) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox.getValue())) {
                // bizMsg.mdseCd.setErrorInfo(1, NMAM8284E);
                // NMAM8390E=0,[@] exists in CUSA components and must
                // be registered to CSA Item Master as Active. Please
                // verify CSA Item master status .
                bizMsg.mdseCd.setErrorInfo(1, NMAM8390E, new String[] {bizMsg.mdseCd.getValue() });
            } else {
                bizMsg.mdseCd.setErrorInfo(1, NMAM8111E, new String[] {BOM_ITEM });
            }
            return false;
        }

        boolean cmpsnExistsFlg = false;
        S21SsmEZDResult r = NMAL0240Query.getInstance().findCmpsn(bizMsg.mdseCd.getValue());
        if (r.isCodeNormal()) {
            Map<String, Object> resultMap = (Map<String, Object>) r.getResultObject();
            if (resultMap != null && resultMap.get("CMPSN_PK") != null) {
                cmpsnExistsFlg = true;
            }
        }

        // if CUSA BOM & Already populate on CMPSN, Error
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox.getValue()) && cmpsnExistsFlg) {
            // Error
            // NMAM8402E=0,This components are already imported from
            // CUSA to CSA.
            bizMsg.mdseCd.setErrorInfo(1, NMAM8402E, new String[] {bizMsg.mdseCd.getValue() });
            return false;
        }

        // if CUSA MDSE.RGTN_STS_CD <> 'P20', Error
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox.getValue()) && !RGTN_STS.READY_FOR_ORDER_TAKING.equals(NMAL0240CommonLogic.getCusaMdseRgtnSts(getGlobalCompanyCode(), bizMsg.mdseCd.getValue()))) {
            // Error
            // NMAM8493E=0,This item code on CUSA Merchandise Master
            // is not ready to import to CSA.
            bizMsg.mdseCd.setErrorInfo(1, NMAM8493E);
            return false;
        }

        String slsDt = ZYPDateUtil.getSalesDate();
        boolean find = true;
        String mdseCd = null;
        NMAL0240_ACMsg aCMsg = null;
        for (int i = 0; i < length; i++) {

            aCMsg = bizMsg.A.no(i);
            mdseCd = aCMsg.mdseCd_A1.getValue();

            // Component Check
            if (!ZYPCommonFunc.hasValue(aCMsg.cmpsnChngReqPk_A1)) {
                S21SsmEZDResult ssmResult = NMAL0240Query.getInstance().findOrdTakeMdse(mdseCd);
                if (ssmResult.isCodeNormal()) {
                    Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
                    ZYPEZDItemValueSetter.setValue(aCMsg.ordTakeMdseCd_A1, (String) resultMap.get("ORD_TAKE_MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(aCMsg.mdseCd_A2, (String) resultMap.get("MDSE_CD"));

                    ZYPEZDItemValueSetter.setValue(aCMsg.coaMdseTpCd_A1, (String) resultMap.get("COA_MDSE_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(aCMsg.invtyCtrlFlg_A1, (String) resultMap.get("INVTY_CTRL_FLG"));
                    ZYPEZDItemValueSetter.setValue(aCMsg.basePkgUomCd_A1, (String) resultMap.get("BASE_PKG_UOM_CD"));
                    find = true;

                    if (mdseCd.length() == ORD_TAKE_LEN && MDSE_ITEM_TP.KIT.equals((String) resultMap.get("MDSE_ITEM_TP_CD"))) {
                        aCMsg.mdseCd_A1.setErrorInfo(1, NMAM8283E);
                        return false;
                    }
                } else {
                    find = false;
                }
            }

            if (!find) {
                S21SsmEZDResult ssmResult = NMAL0240Query.getInstance().findMdse(mdseCd);
                if (ssmResult.isCodeNormal()) {
                    Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
                    ZYPEZDItemValueSetter.setValue(aCMsg.coaMdseTpCd_A1, (String) resultMap.get("COA_MDSE_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(aCMsg.invtyCtrlFlg_A1, (String) resultMap.get("INVTY_CTRL_FLG"));
                    ZYPEZDItemValueSetter.setValue(aCMsg.basePkgUomCd_A1, (String) resultMap.get("BASE_PKG_UOM_CD"));
                    find = true;
                } else {
                    find = false;
                }
            }

            if (!find) {
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox.getValue())) {
                    // aCMsg.mdseCd_A1.setErrorInfo(1, NMAM8284E);
                    // NMAM8390E=0,[@] exists in CUSA components and
                    // must be registered to CSA Item Master as
                    // Active. Please verify CSA Item master status .
                    aCMsg.mdseCd_A1.setErrorInfo(1, NMAM8390E, new String[] {aCMsg.mdseCd_A1.getValue() });
                } else {
                    aCMsg.mdseCd_A1.setErrorInfo(1, NMAM8111E, new String[] {COMPONENT });
                }
                return false;
            }

            // Hierarchy Check
            if (0 == ZYPDateUtil.compare(slsDt, bizMsg.effFromDt_A.getValue())) {
                S21SsmEZDResult ssmResult = NMAL0240Query.getInstance().checkHierarchy(mdseCd, bizMsg.mdseCd.getValue(), slsDt);
                if (!ssmResult.isCodeNotFound()) {
                    aCMsg.mdseCd_A1.setErrorInfo(1, NMAM0137E);
                    return false;
                }
            } else {
                S21SsmEZDResult ssmResult = NMAL0240Query.getInstance().checkHierarchy(mdseCd, bizMsg.mdseCd.getValue(), bizMsg.effFromDt_A.getValue());
                if (!ssmResult.isCodeNotFound()) {
                    aCMsg.mdseCd_A1.setErrorInfo(1, NMAM0137E);
                    return false;
                }
            }
        }

        return true;
    }

    private boolean deleteCmpsnReq(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {

        boolean changedFlg = false;
        int len = glblMsg.X.getValidCount();
        if (len > 0) {

            changedFlg = true;
            CMPSN_CHNG_REQTMsg[] cmpsnChngReqTMsgArr = new CMPSN_CHNG_REQTMsg[len];
            int cnt = 0;

            String glblCmpyCd = getGlobalCompanyCode();
            String prntMdseCd = glblMsg.mdseCd.getValue();
            NMAL0240_XSMsg xSMsg = null;
            for (int i = 0; i < glblMsg.X.getValidCount(); i++) {
                xSMsg = glblMsg.X.no(i);

                CMPSN_CHNG_REQTMsg cmpsnChngReqTMsg = new CMPSN_CHNG_REQTMsg();
                ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.cmpsnChngReqPk, xSMsg.cmpsnChngReqPk_X1.getValue());
                ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.cmpsnPk, xSMsg.cmpsnPk_X1.getValue());
                ZYPEZDItemValueSetter.setValue(cmpsnChngReqTMsg.prntMdseCd, prntMdseCd);

                cmpsnChngReqTMsg = (CMPSN_CHNG_REQTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(cmpsnChngReqTMsg);
                if (!RTNCD_NORMAL.equals(cmpsnChngReqTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM8111E, new String[] {CMPSN_CHNG_REQ });
                    return changedFlg;
                }
                cmpsnChngReqTMsgArr[cnt] = cmpsnChngReqTMsg;
                cnt++;
            }

            int result1 = S21FastTBLAccessor.removeLogical(cmpsnChngReqTMsgArr);
            if (result1 != len) {
                bizMsg.setMessageInfo(NMAM0208E, new String[] {CMPSN_CHNG_REQ });
                return changedFlg;
            }
        }
        return changedFlg;
    }

}
