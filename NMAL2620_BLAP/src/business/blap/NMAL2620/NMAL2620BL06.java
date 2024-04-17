/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2620;

import static business.blap.NMAL2620.constant.NMAL2620Constant.CHK_BOX_A;
import static business.blap.NMAL2620.constant.NMAL2620Constant.NMAM0183E;
import static business.blap.NMAL2620.constant.NMAL2620Constant.ON_LINE;
import static business.blap.NMAL2620.constant.NMAL2620Constant.SUBMITTED;
import static business.blap.NMAL2620.constant.NMAL2620Constant.TIME_STAMP_FORMAT;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL2620.common.NMAL2620CommonLogic;
import business.blap.NMAL2620.constant.NMAL2620Constant;
import business.db.TRTY_UPD_RQST_DTLTMsg;
import business.db.TRTY_UPD_RQST_HDRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_UPD_MODE_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Territory Mass Update
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/26   Hitachi         T.Mizuki        Create          N/A
 * 2016/04/27   Fujitsu         C.Yokoi         Update          N/A
 * 2016/08/02   Fujitsu         C.Yokoi         Update          CSA-QC#7500
 *</pre>
 */
public class NMAL2620BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        NMAL2620CMsg bizMsg = (NMAL2620CMsg) cMsg;
        NMAL2620SMsg sharedMsg = (NMAL2620SMsg) sMsg;
        super.preDoProcess(bizMsg, sharedMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL2620Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL2620Scrn00_CMN_Submit(bizMsg, sharedMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL2620Scrn00_CMN_Submit(NMAL2620CMsg cMsg, NMAL2620SMsg sMsg) {
        // 2016/04/27 CSA-QC# Delete Start
//        if (!ZYPCommonFunc.hasValue(cMsg.trtyUpdModeTpCd_V)) {
//            cMsg.setMessageInfo(ZZZM9007E, new String[] {"Select Mode"});
//            return;
//        } else {
//            if (cMsg.trtyUpdModeTpCd_V.getValue().equals(SERVICE_MODE_01)) {
//                if (!ZYPCommonFunc.hasValue(cMsg.xxPsnNm_D)) {
//                    cMsg.setMessageInfo(ZZZM9007E, new String[] {"Move Territory To"});
//                    return;
//                }
//                if (!ZYPCommonFunc.hasValue(cMsg.xxFromDt)) {
//                    cMsg.setMessageInfo(ZZZM9007E, new String[] {"Move Effective From"});
//                    return;
//                }
//                if (!ZYPCommonFunc.hasValue(cMsg.xxThruDt)) {
//                    if (Integer.parseInt(cMsg.xxFromDt.getValue()) >= Integer.parseInt(cMsg.xxThruDt.getValue())) {
//                        cMsg.setMessageInfo(NMAM0185E);
//                        return;
//                    }
//                }
//            } else if (cMsg.trtyUpdModeTpCd_V.getValue().equals(SERVICE_MODE_02)) {
//                if (!ZYPCommonFunc.hasValue(cMsg.endDt)) {
//                    cMsg.setMessageInfo(ZZZM9007E, new String[] {"End Date Territory On"});
//                    return;
//                }
//            }
//        }
//        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.A, CHK_BOX_A, ZYPConstant.CHKBOX_ON_Y);
//        if (checkList.size() == 0) {
//            cMsg.setMessageInfo("NMAM0007I");
//            return;
//        }
        // 2016/04/27 CSA-QC# Delete End

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.A, CHK_BOX_A, ZYPConstant.CHKBOX_ON_Y);

        if (TRTY_UPD_MODE_TP.MOVE_RESOURCE.equals(cMsg.trtyUpdModeTpCd_V.getValue())) {
            // check
            if (!checkMoveTerritory(cMsg)) {
                return;
            }

            // get Move Effective To Date
            String moveEffToDate = "99991231";
            if (ZYPCommonFunc.hasValue(cMsg.xxThruDt)) {
                moveEffToDate = cMsg.xxThruDt.getValue();
            }

            for (int i = 0; i < checkList.size(); i++) {
                if (!NMAL2620CommonLogic.checkEffectiveDate(cMsg, checkList.get(i), moveEffToDate)) {
                    return;
                }
                // if (!NMAL2620CommonLogic.checkBusinessArea(cMsg, checkList.get(i))) {
                //    return;
                // }
                 if (!NMAL2620CommonLogic.checkExistMoveToRsrcTrtyReln(cMsg, checkList.get(i), moveEffToDate)) {
                    return;
                 }
                if (cMsg.xxPsnNm_D.getValue().equals(cMsg.A.no(checkList.get(i)).xxPsnNm_A.getValue())) {
                    cMsg.setMessageInfo(NMAM0183E, new String[] {"if selected resource is same resource(Move Territory To), it"});
                    return;
                }
            }
        } else if (TRTY_UPD_MODE_TP.END_TERRITORIES.equals(cMsg.trtyUpdModeTpCd_V.getValue())) {
            for (int i = 0; i < checkList.size(); i++) {
                if (!NMAL2620CommonLogic.checkTargetTerritories(cMsg, checkList.get(i))) {
                    return;
                }
                // 2016/08/02 CSA-QC#7500 Add Start
                if (!NMAL2620CommonLogic.checkActiveChildTerritory(cMsg, checkList.get(i), checkList)) {
                    return;
                }
                if (!NMAL2620CommonLogic.checkActiveParentTerritory(cMsg, checkList.get(i), checkList)) {
                    return;
                }
                // 2016/08/02 CSA-QC#7500 Add End
                if (!NMAL2620CommonLogic.getEffFrDt(cMsg, checkList.get(i))) {
                    return;
                }
                if (!NMAL2620CommonLogic.getRelnDt(cMsg, checkList.get(i))) {
                    return;
                }
            }
        }

        // insert
        BigDecimal hdrSq = saveTrtyUpdRqstHdr(cMsg);
        for (int i = 0; i < checkList.size(); i++) {
            saveTrtyUpdRqstDtl(cMsg, sMsg, checkList.get(i), hdrSq);
        }
    }

    private boolean checkMoveTerritory(NMAL2620CMsg bizMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", (String) bizMsg.glblCmpyCd.getValue());
        queryParam.put("psnFirstNm", bizMsg.xxPsnNm_D.getValue());

        S21SsmEZDResult ssmResult = NMAL2620Query.getInstance().checkMoveTerritory(queryParam);
        if (ssmResult.isCodeNormal()) {
            // Error if resource could not identify
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt != 1) {
                bizMsg.setMessageInfo(NMAL2620Constant.NMAM8489E, new String[] {"Resource"});
                return false;
            }
        } else {
            bizMsg.setMessageInfo(NMAL2620Constant.NMAM8489E, new String[] {"Resource"});
            return false;
        }

        // set Person Number
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        Map<String, Object> map = (Map<String, Object>) resultList.get(0);
        if (map.get("PSN_NUM") != null) {
               ZYPEZDItemValueSetter.setValue(bizMsg.psnNum_D, ((String) map.get("PSN_NUM")));
        } else {
            bizMsg.setMessageInfo(NMAL2620Constant.NMAM8489E, new String[] {"Resource"});
            return false;
        }

        return true;
    }

    private BigDecimal saveTrtyUpdRqstHdr(NMAL2620CMsg cMsg) {

        TRTY_UPD_RQST_HDRTMsg trtyUpdRqstHdrTMsg = new TRTY_UPD_RQST_HDRTMsg();
        String glblCmpyCd = getGlobalCompanyCode();
        String userId = getContextUserInfo().getUserId();
        BigDecimal hdrSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_UPD_RQST_HDR_SQ);

        ZYPEZDItemValueSetter.setValue(trtyUpdRqstHdrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(trtyUpdRqstHdrTMsg.trtyUpdRqstHdrPk, hdrSq);
        ZYPEZDItemValueSetter.setValue(trtyUpdRqstHdrTMsg.rqstUsrId, userId);
        ZYPEZDItemValueSetter.setValue(trtyUpdRqstHdrTMsg.rqstCratTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));
        ZYPEZDItemValueSetter.setValue(trtyUpdRqstHdrTMsg.rqstCratSysTpCd, ON_LINE);
        ZYPEZDItemValueSetter.setValue(trtyUpdRqstHdrTMsg.rqstRsltTpCd, SUBMITTED);
        // ZYPEZDItemValueSetter.setValue(trtyUpdRqstHdrTMsg.rqstRsltCmntTxt, cMsg.rqstRsltCmntTxt.getValue());

        S21FastTBLAccessor.insert(trtyUpdRqstHdrTMsg);

        return hdrSq;
    }

    private void saveTrtyUpdRqstDtl(NMAL2620CMsg cMsg, NMAL2620SMsg sMsg, int no, BigDecimal hdrSq) {

        TRTY_UPD_RQST_DTLTMsg trtyUpdRqstDltTMsg = new TRTY_UPD_RQST_DTLTMsg();
        String glblCmpyCd = getGlobalCompanyCode();
        BigDecimal dtlSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_UPD_RQST_DTL_SQ);

        ZYPEZDItemValueSetter.setValue(trtyUpdRqstDltTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(trtyUpdRqstDltTMsg.trtyUpdRqstDtlPk, dtlSq);
        ZYPEZDItemValueSetter.setValue(trtyUpdRqstDltTMsg.trtyUpdRqstHdrPk, hdrSq);
        ZYPEZDItemValueSetter.setValue(trtyUpdRqstDltTMsg.trtyOrgCd, cMsg.A.no(no).orgCd_A.getValue());
        ZYPEZDItemValueSetter.setValue(trtyUpdRqstDltTMsg.curPsnNum, cMsg.A.no(no).psnNum_A.getValue());
        ZYPEZDItemValueSetter.setValue(trtyUpdRqstDltTMsg.movePsnNum, cMsg.psnNum_D);
        ZYPEZDItemValueSetter.setValue(trtyUpdRqstDltTMsg.trtyUpdModeTpCd, cMsg.trtyUpdModeTpCd_V);
        ZYPEZDItemValueSetter.setValue(trtyUpdRqstDltTMsg.moveEffFromDt, cMsg.xxFromDt);
        ZYPEZDItemValueSetter.setValue(trtyUpdRqstDltTMsg.moveEffThruDt, cMsg.xxThruDt);
        ZYPEZDItemValueSetter.setValue(trtyUpdRqstDltTMsg.trtyEndDt, cMsg.endDt);
        ZYPEZDItemValueSetter.setValue(trtyUpdRqstDltTMsg.massUpdRsnCmntTxt, cMsg.rqstRsltCmntTxt.getValue());

        S21FastTBLAccessor.insert(trtyUpdRqstDltTMsg);

    }
}
