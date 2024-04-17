/*
 * <Pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/17   CSA             G.Quan          Create          QC#61387
 *</pre>
 */
package business.blap.NFAL0270.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFAL0270.NFAL0270CMsg;
import business.blap.NFAL0270.NFAL0270Query;
import business.blap.NFAL0270.NFAL0270SMsg;
import business.blap.NFAL0270.NFAL0270_ASMsg;
import business.blap.NFAL0270.NFAL0270_BSMsg;
import business.db.AJE_INV_MDL_GRP_ALLOCTMsg;
import business.db.DS_MDL_GRPTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import static business.blap.NFAL0270.constant.NFAL0270Constant.*;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/17   Hitachi         G.Quan          Create          QC#61387
 *</pre>
 */
public class NFAL0270CommonLogic {

    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();

    /**
     * setPulldownAdjTrxTypeList
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NFAL0270CMsg
     */
    public static void setPulldownSvcInvChrgTpCdList(String glblCmpyCd, NFAL0270CMsg cMsg) {

        cMsg.svcInvChrgTpCd_H.clear();
        cMsg.svcInvChrgTpCd_PD.clear();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcInvChrgTpCdBc", SVC_INV_CHRG_TP.BASE_CHARGE);
        param.put("svcInvChrgTpCdMc", SVC_INV_CHRG_TP.METER_CHARGE);
        // Execute
        S21SsmEZDResult result = NFAL0270Query.getInstance().getSvcInvChrgTpCdList(param);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMap = (List<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < resultMap.size(); i++) {

                ZYPEZDItemValueSetter.setValue(cMsg.svcInvChrgTpCd_PD.no(i), (String) resultMap.get(i).get("SVC_INV_CHRG_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.svcInvChrgTpDescTxt_PD.no(i), (String) resultMap.get(i).get("SVC_INV_CHRG_TP_DESC_TXT"));
            }
        }
    }

    /**
     * copy from SMsg to CMsg
     * @param cMsg NFAL0270CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void copyPage(NFAL0270CMsg cMsg, EZDCMsgArray bizMsgAry, EZDSMsgArray shareMsgAry) {
        int startIndex = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int dispRowNum = 0;

        if (cMsg.xxPageShowFromNum_A.getValueInt() < 0) {
            return;
         }
         for (; dispRowNum < bizMsgAry.length() && dispRowNum < bizMsgAry.getValidCount(); dispRowNum++) {
             EZDMsg.copy(bizMsgAry.get(dispRowNum), null, shareMsgAry.get(startIndex + dispRowNum), null);
         }
         shareMsgAry.setValidCount(cMsg.xxPageShowOfNum_A.getValueInt());
     }

    /**
     * Previous Page
     * @param cMsg NFAL0270CMsg
     * @param sMsg NFAL0270SMsg
     */
    public static void prevPage(NFAL0270CMsg cMsg, NFAL0270SMsg sMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;

        bizMsgAry = cMsg.A;
        shareMsgAry = sMsg.A;
        cMsg.xxPageShowFromNum_A.setValue(cMsg.xxPageShowFromNum_A.getValueInt() - bizMsgAry.length());
        dispPage(cMsg, bizMsgAry, shareMsgAry);
     }

    /**
     * Next Page
     * @param cMsg NFAL0270CMsg
     * @param sMsg NFAL0270SMsg
     */
    public static void nextPage(NFAL0270CMsg cMsg, NFAL0270SMsg sMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;
        bizMsgAry = cMsg.A;
        shareMsgAry = sMsg.A;
        cMsg.xxPageShowFromNum_A.setValue(cMsg.xxPageShowFromNum_A.getValueInt() + bizMsgAry.length());
        dispPage(cMsg, bizMsgAry, shareMsgAry);
     }
    /**
     * Last Page
     * @param cMsg NFAL0270CMsg
     * @param sMsg NFAL0270SMsg
     */
    public static void lastPage(NFAL0270CMsg cMsg, NFAL0270SMsg sMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;
        bizMsgAry = cMsg.A;
        shareMsgAry = sMsg.A;
        BigDecimal lastPageFromNum = getLastPageFromNum(cMsg, sMsg);
        cMsg.xxPageShowFromNum_A.setValue(lastPageFromNum);
        dispPage(cMsg, bizMsgAry, shareMsgAry);
    }

    /**
     * Get Last Page From Number
     * @param cMsg NFAL0270CMsg
     * @param sMsg NFAL0270SMsg
     * @return BigDecimal
     */
    public static BigDecimal getLastPageFromNum(NFAL0270CMsg cMsg, NFAL0270SMsg sMsg) {
        int pageCnt = sMsg.A.getValidCount() / cMsg.A.length();
        int lastPageFromNum = cMsg.A.length() * pageCnt + 1;
        if (sMsg.A.getValidCount() % cMsg.A.length() == 0) {
            lastPageFromNum = lastPageFromNum - cMsg.A.length();
        }
        return new BigDecimal(lastPageFromNum);
    }

    /** 
     * copy from SMsg to CMsg
     * @param cMsg NFAL0270CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void dispPage(NFAL0270CMsg cMsg, EZDCMsgArray bizMsgAry, EZDSMsgArray shareMsgAry) {
        ZYPTableUtil.clear(bizMsgAry);

        int startIndex = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int dispRowNum = 0;
        if (cMsg.xxPageShowFromNum_A.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry.length() && startIndex + dispRowNum < shareMsgAry.getValidCount(); dispRowNum++) {
            EZDMsg.copy(shareMsgAry.get(startIndex + dispRowNum), null, bizMsgAry.get(dispRowNum), null);
        }
        bizMsgAry.setValidCount(dispRowNum);
        cMsg.xxPageShowToNum_A.setValue(startIndex + dispRowNum);
        cMsg.xxPageShowOfNum_A.setValue(shareMsgAry.getValidCount());
    }

    /**
     * search
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NFAL0270CMsg
     * @param sMsg NFAL0270SMsg
     */
    public static void search(String glblCmpyCd, NFAL0270CMsg cMsg, NFAL0270SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        S21SsmEZDResult ssmResult = NFAL0270Query.getInstance().getAllocation(glblCmpyCd, cMsg, sMsg, sMsg.A.length() + 1);

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> resulList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();

            int index = 0;
            for (Map<String, Object> resultMap : resulList) {
                if (index < sMsg.A.length()) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdlGrpId_A, (BigDecimal) resultMap.get("MDL_GRP_ID"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdlGrpNm_A, (String) resultMap.get("MDL_GRP_NM"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).svcInvChrgTpCd_A, (String) resultMap.get("SVC_INV_CHRG_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).svcAllocTpCd_A, (String) resultMap.get("SVC_ALLOC_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).svcInvChrgTpCd_A, (String) resultMap.get("SVC_INV_CHRG_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).svcAllocTpCd_A, (String) resultMap.get("SVC_ALLOC_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).equipAllocPct_A, (BigDecimal) resultMap.get("EQUIP_ALLOC_PCT"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).svcAllocPct_A, (BigDecimal) resultMap.get("SVC_ALLOC_PCT"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).splyAllocPct_A, (BigDecimal) resultMap.get("SPLY_ALLOC_PCT"));
                    //setRsltToSMsg(sMsg.A.no(index), resultMap);
                }

                index++;
            }

            int resultCnt = index;

            if (index == 0) {

                cMsg.setMessageInfo(NZZM0000E);
                cMsg.xxPageShowFromNum_A.clear();
                cMsg.xxPageShowOfNum_A.clear();
                cMsg.xxPageShowToNum_A.clear();
                return;
            }

            if (index > sMsg.A.length()) {

                cMsg.setMessageInfo(ZZZM9002W);
                resultCnt = sMsg.A.length();

            } else {

                cMsg.setMessageInfo(NZZM0002I);
            }

            sMsg.A.setValidCount(resultCnt);

            // copy 1page sMsg => cMsg
            int i = 0;

            for (; i < sMsg.A.getValidCount(); i++) {

                if (i == cMsg.A.length()) {

                    break;
                }

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }

            cMsg.A.setValidCount(i);

            // set Page info
            cMsg.xxPageShowFromNum_A.setValue(1);
            cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum_A.setValue(resultCnt);
        } else {
            cMsg.setMessageInfo(NZZM0000E);
            cMsg.xxPageShowFromNum_A.clear();
            cMsg.xxPageShowOfNum_A.clear();
            cMsg.xxPageShowToNum_A.clear();
            cMsg.setCommitSMsg(true);
        }
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NFAL0270CMsg
     * @param sMsg NFAL0270SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NFAL0270CMsg cMsg, NFAL0270SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }



    /**
     * updateSvcModDtl
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NFAL0270CMsg
     * @param sMsg NFAL0270SMsg
     */
    public static void updateSvcModDtl(String glblCmpyCd, NFAL0270CMsg cMsg, NFAL0270SMsg sMsg) {

        // Delete
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            AJE_INV_MDL_GRP_ALLOCTMsg tMsg = getAimgaB(glblCmpyCd, sMsg.B.no(i));
            if (tMsg == null) {
                continue;
            }
            setValue(tMsg.glblCmpyCd, glblCmpyCd);
            setValue(tMsg.mdlGrpId, sMsg.B.no(i).mdlGrpId_B.getValue());
            setValue(tMsg.svcInvChrgTpCd, sMsg.B.no(i).svcInvChrgTpCd_B.getValue());
            setValue(tMsg.svcAllocTpCd, sMsg.B.no(i).svcAllocTpCd_B.getValue());
            EZDFastTBLAccessor.logicalRemoveByPartialValue(tMsg, new String[] {"glblCmpyCd", "mdlGrpId", "svcInvChrgTpCd", "svcAllocTpCd" });
            if (!EZDFastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NFAM0211E, new String[] {"AJE_INV_MDL_GRP_ALLOC"});
                return;
            }
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            // insert or update
            AJE_INV_MDL_GRP_ALLOCTMsg tMsg = getAimgaA(glblCmpyCd, sMsg.A.no(i));
            if (tMsg == null) {
                AJE_INV_MDL_GRP_ALLOCTMsg insertTMsg = new AJE_INV_MDL_GRP_ALLOCTMsg();
                setValue(insertTMsg.glblCmpyCd, glblCmpyCd);
                setValue(insertTMsg.mdlGrpId, sMsg.A.no(i).mdlGrpId_A);
                setValue(insertTMsg.svcInvChrgTpCd, sMsg.A.no(i).svcInvChrgTpCd_A);
                setValue(insertTMsg.svcAllocTpCd, sMsg.A.no(i).svcAllocTpCd_A);
                setValue(insertTMsg.equipAllocPct, sMsg.A.no(i).equipAllocPct_A);
                setValue(insertTMsg.svcAllocPct, sMsg.A.no(i).svcAllocPct_A);
                setValue(insertTMsg.splyAllocPct, sMsg.A.no(i).splyAllocPct_A);
                EZDTBLAccessor.create(insertTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NFAM0180E, new String[] {"AJE_INV_MDL_GRP_ALLOC" });
                    return;
                }
            } else {
                if (checkLine(glblCmpyCd, sMsg.A.no(i))) {
                    setValue(tMsg.mdlGrpId, sMsg.A.no(i).mdlGrpId_A.getValue());
                    setValue(tMsg.svcInvChrgTpCd, sMsg.A.no(i).svcInvChrgTpCd_A);
                    setValue(tMsg.svcAllocTpCd, sMsg.A.no(i).svcAllocTpCd_A);
                    setValue(tMsg.equipAllocPct, sMsg.A.no(i).equipAllocPct_A);
                    setValue(tMsg.svcAllocPct, sMsg.A.no(i).svcAllocPct_A);
                    setValue(tMsg.splyAllocPct, sMsg.A.no(i).splyAllocPct_A);

                    EZDTBLAccessor.update(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NFAM0181E, new String[] {"AJE_INV_MDL_GRP_ALLOC" });
                        return;
                    }
                }
            }
        }
    }

    /**
     * checkPercent
     * @param aSMsg NFAL0270_ASMsg
     * @return 100:false
     */
    public static boolean checkPercent(NFAL0270_ASMsg aSMsg) {

        EZDSBigDecimalItem equipAllocPct = aSMsg.equipAllocPct_A;
        if (!ZYPCommonFunc.hasValue(equipAllocPct)) {
            ZYPEZDItemValueSetter.setValue(equipAllocPct, ZERO);
        }

        EZDSBigDecimalItem svcAllocPct = aSMsg.svcAllocPct_A;
        if (!ZYPCommonFunc.hasValue(svcAllocPct)) {
            ZYPEZDItemValueSetter.setValue(svcAllocPct, ZERO);
        }

        EZDSBigDecimalItem splyAllocPct = aSMsg.splyAllocPct_A;
        if (!ZYPCommonFunc.hasValue(splyAllocPct)) {
            ZYPEZDItemValueSetter.setValue(splyAllocPct, ZERO);
        }

        BigDecimal allocationTotal = equipAllocPct.getValue().add(svcAllocPct.getValue()).add(splyAllocPct.getValue());
        if (allocationTotal.compareTo(HUNDRED) != 0 || allocationTotal.compareTo(HUNDRED_DECIMAL_POINT) != 0) {
            return true;
        }
        return false;
    }

    private static boolean checkLine(String glblCmpyCd, NFAL0270_ASMsg aSMsg) {
        if (!hasValue(aSMsg.mdlGrpId_A)) {
            return true;
        }
        NFAL0270_ASMsg cmpDstASMsg = aSMsg;
        AJE_INV_MDL_GRP_ALLOCTMsg cmpSrcTMsg = getAimgaA(glblCmpyCd, cmpDstASMsg);

        if (cmpSrcTMsg != null) {
            if (checkChangeLine(cmpSrcTMsg, cmpDstASMsg)) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    private static boolean checkChangeLine(AJE_INV_MDL_GRP_ALLOCTMsg cmpSrc, NFAL0270_ASMsg cmpDst) {
        // mdlGrpId
        if (checkChangeItemBigDecimal(cmpSrc.mdlGrpId, cmpDst.mdlGrpId_A)) {
            return true;
        }
        // svcInvChrgTpCd
        if (checkChangeItemString(cmpSrc.svcInvChrgTpCd, cmpDst.svcInvChrgTpCd_A)) {
            return true;
        }
        // svcAllocTpCd
        if (checkChangeItemString(cmpSrc.svcAllocTpCd, cmpDst.svcAllocTpCd_A)) {
            return true;
        }
        // equipAllocPct
        if (checkChangeItemBigDecimal(cmpSrc.equipAllocPct, cmpDst.equipAllocPct_A)) {
            return true;
        }
        // svcAllocPct
        if (checkChangeItemBigDecimal(cmpSrc.svcAllocPct, cmpDst.svcAllocPct_A)) {
            return true;
        }
        // splyAllocPct
        if (checkChangeItemBigDecimal(cmpSrc.splyAllocPct, cmpDst.splyAllocPct_A)) {
            return true;
        }

        return false;
    }

    private static boolean checkChangeItemString(EZDTStringItem item1, EZDSStringItem item2) {
        if (hasValue(item1)) {
            if (hasValue(item2)) {
                if (item1.getValue().equals(item2.getValue())) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            if (hasValue(item2)) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static boolean checkChangeItemBigDecimal(EZDTBigDecimalItem item1, EZDSBigDecimalItem item2) {
        if (hasValue(item1)) {
            if (hasValue(item2)) {
                if (item1.getValue().equals(item2.getValue())) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            if (hasValue(item2)) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * checkMdlGrpIdMaster
     * @param glblCmpyCd glblCmpyCd
     * @param aSMsg NFAL0270_ASMsg
     * @return true:null
     */
    public static boolean checkMdlGrpIdMaster(String glblCmpyCd, NFAL0270_ASMsg aSMsg) {
        DS_MDL_GRPTMsg prmTMsg = new DS_MDL_GRPTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.mdlGrpId, aSMsg.mdlGrpId_A.getValue());
        DS_MDL_GRPTMsg tMsg = (DS_MDL_GRPTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
        if (tMsg == null) {
            return true;
        }
        return false;
    }

    private static AJE_INV_MDL_GRP_ALLOCTMsg getAimgaA(String glblCmpyCd, NFAL0270_ASMsg aSMsg) {
        AJE_INV_MDL_GRP_ALLOCTMsg prmTMsg = new AJE_INV_MDL_GRP_ALLOCTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.mdlGrpId, aSMsg.mdlGrpId_A.getValue());
        setValue(prmTMsg.svcInvChrgTpCd, aSMsg.svcInvChrgTpCd_A.getValue());
        setValue(prmTMsg.svcAllocTpCd, aSMsg.svcAllocTpCd_A.getValue());
        return (AJE_INV_MDL_GRP_ALLOCTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    private static AJE_INV_MDL_GRP_ALLOCTMsg getAimgaB(String glblCmpyCd, NFAL0270_BSMsg bSMsg) {
        AJE_INV_MDL_GRP_ALLOCTMsg prmTMsg = new AJE_INV_MDL_GRP_ALLOCTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.mdlGrpId, bSMsg.mdlGrpId_B.getValue());
        setValue(prmTMsg.svcInvChrgTpCd, bSMsg.svcInvChrgTpCd_B.getValue());
        setValue(prmTMsg.svcAllocTpCd, bSMsg.svcAllocTpCd_B.getValue());
        return (AJE_INV_MDL_GRP_ALLOCTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }
}
