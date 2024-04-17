package com.canon.cusa.s21.common.NWX.NWXC220001;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.XTRNL_INTFC_XREFTMsg;
import business.parts.NMZC611001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC611001.NMZC611001;
import com.canon.cusa.s21.common.NWX.NWXC220001.constant.NWXC220001Constant;
import com.canon.cusa.s21.common.NWX.NWXC220001.constant.NWXC220001Constant.MSG_ID;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INTFC_XREF_CTX_TP;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 * <pre>
 * ImportAttribute
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/24   Fujitsu         M.Hara          Create          N/A
 * 2016/10/25   Fujitsu         T.Ishii         Update          S21_NA#15559
 * 2016/11/02   Fujitsu         T.Ishii         Update          S21_NA#14815
 * 2017/01/06   Fujitsu         S.Ohki          Update          S21_NA#16845
 * 2017/10/25   CITS            T.Gotoda        Update          S21_NA#22011, 22012
 * 2017/11/10   Fujitsu         S.Takami        Update          S21_NA#22464
 * 2018/06/04   Fujitsu         A.Kosai         Update          S21_NA#26443
 * 2018/09/07   Fujitsu         S.Takami        Update          S21_NA#28085
 * 2018/10/25   Fujitsu         M.Ohno          Update          S21_NA#28759
 * 2018/11/29   Fujitsu         R.Nakamura      Update          S21_NA#29424
 * 2018/12/14   Fujitsu         T.Noguchi       Update          S21_NA#29315
 * </pre>
 */
public final class NWXC220001ImportAttribute {

    public static NWXC220001Result<List<?>> deriveEdiAttrb(String glblCmpyCd, BigDecimal dsImptOrdPk) {

        List<?> ediAttrbList = NWXC220001Query.getInstance().getEdiAttrb(glblCmpyCd, dsImptOrdPk);

        NWXC220001Result<List<?>> result = new NWXC220001Result<List<?>>(ediAttrbList);

        if (!NWXC220001Util.hasValueList(ediAttrbList)) {
            result.addErrorInfo(MSG_ID.NWAM0809E, "DS_IMPT_EXT_ATTRB");
        }

        return result;
    }

    public static NWXC220001Result<XTRNL_INTFC_XREFTMsg> deriveDefSvcLvlCd(XTRNL_INTFC_XREFTMsg inTMsg) {

        NWXC220001Result<XTRNL_INTFC_XREFTMsg> result = deriveDefCommonAttrb(inTMsg);

        if (!result.hasError()) {
            XTRNL_INTFC_XREFTMsg resultTMsg = result.getResultObject();

            List<?> svcLvlCdList = NWXC220001Query.getInstance().getShpgSvcLvlCarrReln(resultTMsg.glblCmpyCd.getValue(), resultTMsg.trgtAttrbTxt_01.getValue(), resultTMsg.trgtAttrbTxt_02.getValue());

            if (!NWXC220001Util.hasValueList(svcLvlCdList)) {
                result.addErrorInfo(MSG_ID.NWAM0809E, "SHPG_SVC_LVL_CARR_RELN");
            }
        }

        return result;
    }

    public static NWXC220001Result<String> deriveDefAddPmtTermCashDiscCd(String glblCmpyCd, String billToCustCd, String billToCustAcctCd) {

        String pmtTermCashDiscCd = NWXC220001Query.getInstance().getPmtTermCashDiscCd1(glblCmpyCd, billToCustCd);

        if (!ZYPCommonFunc.hasValue(pmtTermCashDiscCd)) {
            pmtTermCashDiscCd = NWXC220001Query.getInstance().getPmtTermCashDiscCd2(glblCmpyCd, billToCustAcctCd);
        }

        NWXC220001Result<String> result = null;
        if (ZYPCommonFunc.hasValue(pmtTermCashDiscCd)) {
            result = new NWXC220001Result<String>(pmtTermCashDiscCd);
        } else {
            result = new NWXC220001Result<String>(null, MSG_ID.NWAM0809E, "CUST_CR_PRFL");
        }

        return result;
    }

    // Add Start 2017/10/24 QC#22011
    public static NWXC220001Result<String> deriveDefFrtCondCd(XTRNL_INTFC_XREFTMsg inTMsg, String glblCmpyCd, String slsDt, String dsOrdTpCd) {

        String frtCondCd = null;
        NWXC220001Result<XTRNL_INTFC_XREFTMsg> resultAttrb = deriveDefCommonAttrb(inTMsg);

        if (!resultAttrb.hasError()) {
            frtCondCd = resultAttrb.getResultObject().trgtAttrbTxt_01.getValue();
        }

        if (!ZYPCommonFunc.hasValue(frtCondCd)) {

            DS_ORD_TP_PROC_DFNTMsg dsOrdTpPrcDfn = getDsOrdTpPrcDfn(glblCmpyCd, slsDt, dsOrdTpCd);
            if (dsOrdTpPrcDfn != null) {
                frtCondCd = dsOrdTpPrcDfn.frtCondCd.getValue();
            }
        }

        NWXC220001Result<String> result;
        if (ZYPCommonFunc.hasValue(frtCondCd)) {
            result = new NWXC220001Result<String>(frtCondCd);
        } else {
            result = new NWXC220001Result<String>(null, MSG_ID.ZZZM9006E, "Freight Condition Code");
        }

        return result;
    }

    private static DS_ORD_TP_PROC_DFNTMsg getDsOrdTpPrcDfn(String glblCmpyCd, String slsDt, String dsOrdTpCd) {

        if (S21StringUtil.isEmpty(dsOrdTpCd)) {

            return null;
        }

        DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, dsOrdTpCd);

        DS_ORD_TP_PROC_DFNTMsg outTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (outTMsg != null) {

            if (slsDt.compareTo(outTMsg.effFromDt.getValue()) < 0) {

                return null;
            }

            if (ZYPCommonFunc.hasValue(outTMsg.effThruDt) && slsDt.compareTo(outTMsg.effThruDt.getValue()) > 0) {

                return null;
            }

            if (S21StringUtil.isEquals(outTMsg.actvFlg.getValue(), ZYPConstant.FLG_ON_Y)) {

                return outTMsg;
            }
        }
        return null;
    }
    // Add End 2017/10/24 QC#22011

    // 2018/12/14 S21_NA#29315 Mod Start
    //public static NWXC220001Result<String> deriveDefCarrAcctNum(String dsEdiTrdPtnrRefCd, XTRNL_INTFC_XREFTMsg inTMsg, String idocPoNoteTxt, String glblCmpyCd, String slsDt, String dsAcctNum, ONBATCH_TYPE type) {
    public static NWXC220001Result<String> deriveDefCarrAcctNum(String dsEdiTrdPtnrRefCd, XTRNL_INTFC_XREFTMsg inTMsg, String idocPoNoteTxt, String glblCmpyCd, String slsDt, String dsAcctNum, ONBATCH_TYPE type, String locNum, String lineBizTpCd, String dsBizAreaCd, String frtCondCd, String shpgSvcLvlCd) {
    // 2018/12/14 S21_NA#29315 Mod End

        String carrAcctNum = null;
        if (isContentsInNcr(dsEdiTrdPtnrRefCd)) {
            NWXC220001Result<XTRNL_INTFC_XREFTMsg> resultAttrb = deriveDefCommonAttrb(inTMsg);

            if (!resultAttrb.hasError()) {
                carrAcctNum = resultAttrb.getResultObject().trgtAttrbTxt_01.getValue();
            }

        } else if (isContentsInArcMg(dsEdiTrdPtnrRefCd) && ZYPCommonFunc.hasValue(idocPoNoteTxt)) {

            // Mod Start 2018/11/29 QC#29424
//            String[] splitStrings = idocPoNoteTxt.split(" ");
            Pattern splitPattern = Pattern.compile("[\\n\\s]+");
            String[] splitStrings = splitPattern.split(idocPoNoteTxt);
            // Mod End 2018/11/29 QC#29424
            Pattern p = Pattern.compile("^[0-9]{4,}$");

            for (int i = 0; i < splitStrings.length; i++) {
                if (p.matcher(splitStrings[i]).find()) {
                    carrAcctNum = splitStrings[i];
                    break;
                }
            }
        }

        if (!ZYPCommonFunc.hasValue(carrAcctNum)) {
            NMZC611001PMsg pMsg = new NMZC611001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
            ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, dsAcctNum);
            // 2018/12/14 S21_NA#29315 Add Start
            ZYPEZDItemValueSetter.setValue(pMsg.locNum, locNum);
            ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, lineBizTpCd);
            ZYPEZDItemValueSetter.setValue(pMsg.dsBizAreaCd, dsBizAreaCd);
            ZYPEZDItemValueSetter.setValue(pMsg.frtCondCd, frtCondCd);
            ZYPEZDItemValueSetter.setValue(pMsg.shpgSvcLvlCd, shpgSvcLvlCd);
            // 2018/12/14 S21_NA#29315 Add End
            new NMZC611001().execute(pMsg, type);

            carrAcctNum = pMsg.dsCarrAcctNum.getValue();
        }

        NWXC220001Result<String> result;
        if (ZYPCommonFunc.hasValue(carrAcctNum)) {
            result = new NWXC220001Result<String>(carrAcctNum);
        } else {
            result = new NWXC220001Result<String>(null, MSG_ID.ZZZM9006E, "Carrier Account Number");
        }

        return result;
    }

    public static NWXC220001Result<String> checkDuplicatePo(XTRNL_INTFC_XREFTMsg inTMsg, String ordNum, String custIssPoNum, String ordHdrStsCd) {

        NWXC220001Result<XTRNL_INTFC_XREFTMsg> resultAttrb = deriveDefCommonAttrb(inTMsg);

        NWXC220001Result<String> result = new NWXC220001Result<String>(null);

        if (resultAttrb.hasError()) {
            result.addErrorInfo(resultAttrb.getErrInfoList());
        } else if (ZYPConstant.FLG_ON_Y.equals(resultAttrb.getResultObject().trgtAttrbTxt_01.getValue())) {
            String glblCmpyCd = inTMsg.glblCmpyCd.getValue();

            boolean isSuccess = true;

            List<?> dupPoList = NWXC220001Query.getInstance().getDuplicatePo("getDuplicateOrd", glblCmpyCd, ordNum, custIssPoNum, ordHdrStsCd);

            if (NWXC220001Util.hasValueList(dupPoList)) {
                isSuccess = false;
            }

            if (isSuccess) {
                dupPoList = NWXC220001Query.getInstance().getDuplicatePo("getDuplicateMaintPo", glblCmpyCd, ordNum, custIssPoNum, ordHdrStsCd);
                if (NWXC220001Util.hasValueList(dupPoList)) {
                    isSuccess = false;
                }
            }

            if (isSuccess) {
                dupPoList = NWXC220001Query.getInstance().getDuplicatePo("getDuplicateLeasePo", glblCmpyCd, ordNum, custIssPoNum, ordHdrStsCd);
                if (NWXC220001Util.hasValueList(dupPoList)) {
                    isSuccess = false;
                }
            }

            if (!isSuccess) {
                result.addErrorInfo(MSG_ID.NWAM0810E);
                custIssPoNum = (String) ((Map<?, ?>) dupPoList.get(0)).get("PO_NUM");
                result.setResultObject(custIssPoNum);
            }
        }

        return result;
    }

    public static NWXC220001Result<Map<String, String>> deriveDefMdse(String glblCmpyCd, String sellToCustCd, String idocPoDtlMatNum01, String idocPoDtlMatNum02) { // S21_NA#14815

        Map<String, String> resultMdse = new HashMap<String, String>();
        NWXC220001Result<Map<String, String>> result = new NWXC220001Result<Map<String, String>>(resultMdse);

        // manufacture merchandise
        if (S21StringUtil.isNotEmpty(idocPoDtlMatNum02)) {

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> mdseList = (List<Map<String, Object>>) NWXC220001Query.getInstance().getDefMdse("getDefMdse1", glblCmpyCd, idocPoDtlMatNum02);

            if (!NWXC220001Util.hasValueList(mdseList)) {

                mdseList = (List<Map<String, Object>>) NWXC220001Query.getInstance().getDefMdse("getDefMdse2", glblCmpyCd, idocPoDtlMatNum02);
            }

            if (NWXC220001Util.hasValueList(mdseList)) {

                // 2017/11/10 S21_NA#22464 Add Start
//                if (mdseList.size() > 1) { 2018/09/07 S21_NA#28085 Mod Condition
                if (hasOtherOrdTakeMdseItems(glblCmpyCd, mdseList)) { // 2018/09/07 S21_NA#28085 Mod Condition
                    result.addErrorInfo(MSG_ID.NWZM2249E);
                }
                // 2017/11/10 S21_NA#22464 Add End

                Map<String, Object> mdseMap = (Map<String, Object>) mdseList.get(0);

                // 2018/10/24 S21_NA#28759 Add Start
                resultMdse.put("MDSE_CD", changeMdseCd((String) mdseMap.get("MDSE_CD"), glblCmpyCd));
                // 2018/10/24 S21_NA#28759 Add End

//                resultMdse.put("MDSE_CD", (String) mdseMap.get("MDSE_CD"));
                resultMdse.put("MDSE_NM", (String) mdseMap.get("MDSE_NM"));

            } else {

                // 2017/01/06 S21_NA#16845 Mod Start
//                result.addErrorInfo(MSG_ID.NWAM0104E);
                if (!S21StringUtil.isNotEmpty(idocPoDtlMatNum01)) {
                    result.addErrorInfo(MSG_ID.NWAM0104E);
                }
                // 2017/01/06 S21_NA#16845 Mod End
            }
        }

        // 2017/01/06 S21_NA#16845 Add Start
        if (ZYPCommonFunc.hasValue(resultMdse.get("MDSE_CD"))) {
            return result;
        }
        // 2017/01/06 S21_NA#16845 Add End

        // customer merchandise
        if (S21StringUtil.isNotEmpty(idocPoDtlMatNum01)) {

            @SuppressWarnings("unchecked")
            // Mod Start 2017/10/25 QC#22012
            List<Map<String, Object>> custMdseList = (List<Map<String, Object>>) NWXC220001Query.getInstance().getDefMdseFromCustMdseXref(glblCmpyCd, idocPoDtlMatNum01, sellToCustCd);
            // Mod End 2017/10/25 QC#22012
            if (NWXC220001Util.hasValueList(custMdseList)) {

                Map<String, Object> mdseMap = (Map<String, Object>) custMdseList.get(0);
                if (S21StringUtil.isEmpty(idocPoDtlMatNum02)) {
                    // 2018/10/24 S21_NA#28759 Add Start
                    resultMdse.put("MDSE_CD", changeMdseCd((String) mdseMap.get("MDSE_CD"), glblCmpyCd));
                    // 2018/10/24 S21_NA#28759 Add End

//                    resultMdse.put("MDSE_CD", (String) mdseMap.get("MDSE_CD"));
                    resultMdse.put("MDSE_NM", (String) mdseMap.get("MDSE_NM"));
                }
            } else {

                // 2017/01/06 S21_NA#16845 Mod Start
//                result.addErrorInfo(MSG_ID.NWZM1468E);
                result.addErrorInfo(MSG_ID.NWZM2205E);
                // 2017/01/06 S21_NA#16845 Mod End
            }
        }

        // Add Start 2017/10/25 QC#22012
        if (!ZYPCommonFunc.hasValue(resultMdse.get("MDSE_CD"))) {
            result.addErrorInfo(MSG_ID.NWAM0104E);
        }
        // Add End 2017/10/25 QC#22012

        return result;
    }

    public static NWXC220001Result<XTRNL_INTFC_XREFTMsg> deriveDefCommonAttrb(XTRNL_INTFC_XREFTMsg inTMsg) {
        return deriveDefCommonAttrb(inTMsg, null);
    }

    public static NWXC220001Result<XTRNL_INTFC_XREFTMsg> deriveDefCommonAttrb(XTRNL_INTFC_XREFTMsg inTMsg, String overWrMapNm) {
        int srcAttrbNum = 0;

        String intfcXrefCtxTp = inTMsg.intfcXrefCtxTpCd.getValue();

        if (INTFC_XREF_CTX_TP.ORDER_TYPE_MAPPING.equals(intfcXrefCtxTp)) {
            srcAttrbNum = 3;
        } else if (INTFC_XREF_CTX_TP.NCR_ORDER_TYPE_MAPPING.equals(intfcXrefCtxTp)) {
            srcAttrbNum = 2;
        } else if (INTFC_XREF_CTX_TP.SHIPPING_SERVICE_LEVEL_MAPPING.equals(intfcXrefCtxTp)) {
            srcAttrbNum = 4;
        } else if (INTFC_XREF_CTX_TP.NCR_CARRIER_ACCOUNT_NUMBER_MAPPING.equals(intfcXrefCtxTp)) {
            srcAttrbNum = 4;
        } else if (INTFC_XREF_CTX_TP.CUSTOMER_PO_DUPLICATION_CHECK.equals(intfcXrefCtxTp)) {
            srcAttrbNum = 1;
        } else if (INTFC_XREF_CTX_TP.CUSTOMER_MAPPING.equals(intfcXrefCtxTp)) {
            srcAttrbNum = 4;
        } else if (INTFC_XREF_CTX_TP.FREIGHT_MAPPING.equals(intfcXrefCtxTp)) {
            srcAttrbNum = 4;
        } else if (INTFC_XREF_CTX_TP.DROP_SHIP_MAPPING.equals(intfcXrefCtxTp)) {
            srcAttrbNum = 1;
        } else if (INTFC_XREF_CTX_TP.UOM_MAPPING.equals(intfcXrefCtxTp)) {
            // 2018/06/04 S21_NA#26443 Mod Start
//            srcAttrbNum = 4;
            srcAttrbNum = 5;
            // 2018/06/04 S21_NA#26443 Mod End
        } else if (INTFC_XREF_CTX_TP.CONTACT_MAPPING.equals(intfcXrefCtxTp)) {
            srcAttrbNum = 4;
        }

        return deriveDefCommonAttrb(inTMsg, srcAttrbNum, overWrMapNm);
    }

    private static NWXC220001Result<XTRNL_INTFC_XREFTMsg> deriveDefCommonAttrb(XTRNL_INTFC_XREFTMsg inTMsg, int srcAttrbNum, String overWrMapNm) {
        List<String> allParamList = Arrays.asList(inTMsg.srcAttrbTxt_01.getValue(), inTMsg.srcAttrbTxt_02.getValue(), inTMsg.srcAttrbTxt_03.getValue(), inTMsg.srcAttrbTxt_04.getValue(), inTMsg.srcAttrbTxt_05.getValue(),
                inTMsg.srcAttrbTxt_06.getValue(), inTMsg.srcAttrbTxt_07.getValue(), inTMsg.srcAttrbTxt_08.getValue(), inTMsg.srcAttrbTxt_09.getValue(), inTMsg.srcAttrbTxt_10.getValue());

        List<String> paramList = allParamList.subList(0, srcAttrbNum);

        return deriveDefCommonAttrb(inTMsg, paramList, overWrMapNm);
    }

    private static NWXC220001Result<XTRNL_INTFC_XREFTMsg> deriveDefCommonAttrb(XTRNL_INTFC_XREFTMsg inTMsg, List<String> srcAttrbList, String overWrMapNm) {

        final Map<String, String> mappingNameMap = new HashMap<String, String>() {
            {
                put(INTFC_XREF_CTX_TP.ORDER_TYPE_MAPPING, "S21 Order Type");
                put(INTFC_XREF_CTX_TP.NCR_ORDER_TYPE_MAPPING, "S21 NCR Order Type");
                put(INTFC_XREF_CTX_TP.SHIPPING_SERVICE_LEVEL_MAPPING, "S21 Shipping Service Level");
                put(INTFC_XREF_CTX_TP.NCR_CARRIER_ACCOUNT_NUMBER_MAPPING, "NCR Carrier Account Number");
                put(INTFC_XREF_CTX_TP.CUSTOMER_PO_DUPLICATION_CHECK, "Customer PO Duplication Check");
                put(INTFC_XREF_CTX_TP.CUSTOMER_MAPPING, "Customer");
                put(INTFC_XREF_CTX_TP.FREIGHT_MAPPING, "S21 Freight Condition");
                put(INTFC_XREF_CTX_TP.DROP_SHIP_MAPPING, "Drop Ship");
                put(INTFC_XREF_CTX_TP.UOM_MAPPING, "UOM");
                put(INTFC_XREF_CTX_TP.CONTACT_MAPPING, "Contact");
            }
        };

        List<?> xtrnlIntfcXrefList = NWXC220001Query.getInstance().getXtrnlIntfcXref(inTMsg, srcAttrbList);

        NWXC220001Result<XTRNL_INTFC_XREFTMsg> result = null;
        if (NWXC220001Util.hasValueList(xtrnlIntfcXrefList) && xtrnlIntfcXrefList.size() == 1) { // S21_NA#15559

            result = new NWXC220001Result<XTRNL_INTFC_XREFTMsg>((XTRNL_INTFC_XREFTMsg) xtrnlIntfcXrefList.get(0));
        } else {
            String mappingNm;

            if (ZYPCommonFunc.hasValue(overWrMapNm)) {
                mappingNm = overWrMapNm;
            } else {
                mappingNm = mappingNameMap.get(inTMsg.intfcXrefCtxTpCd.getValue());
            }

            result = new NWXC220001Result<XTRNL_INTFC_XREFTMsg>(null, MSG_ID.NWAM0826E, mappingNm);
        }

        return result;
    }

    public static boolean isContentsInNcr(String dsEdiTrdPtnrRefCd) {
        return (NWXC220001Util.isTargetContents(dsEdiTrdPtnrRefCd, NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_NCR));
    }

    public static boolean isContentsInArc(String dsEdiTrdPtnrRefCd) {
        return (NWXC220001Util.isTargetContents(dsEdiTrdPtnrRefCd, NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_ARC));
    }

    public static boolean isContentsInArcMg(String dsEdiTrdPtnrRefCd) {
        return (NWXC220001Util.isTargetContents(dsEdiTrdPtnrRefCd, NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_ARC, NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_MG));
    }

    public static boolean isContentsInNcrMgJpmc(String dsEdiTrdPtnrRefCd) {
        return (NWXC220001Util.isTargetContents(dsEdiTrdPtnrRefCd, NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_NCR, NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_MG, NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_JPMC));
    }

    public static boolean isContentsInArcMgJpmc(String dsEdiTrdPtnrRefCd) {
        return (NWXC220001Util.isTargetContents(dsEdiTrdPtnrRefCd, NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_ARC, NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_MG, NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_JPMC));
    }

    // 2018/09/07 S21_NA#28085 Add Start
    private static boolean hasOtherOrdTakeMdseItems(String glblCmpyCd, List<Map<String, Object>> mdseList) {

        if (mdseList.size() == 1) {
            return false;
        }

        ORD_TAKE_MDSETMsg queryOrdTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(queryOrdTakeMdseTMsg.glblCmpyCd, glblCmpyCd);
        int ordTakeMdseCdLen = queryOrdTakeMdseTMsg.getAttr("ordTakeMdseCd").getDigit();

        String mdseCd = (String) mdseList.get(0).get("MDSE_CD");
        String ordTakeMdseCd = mdseCd;
        if (mdseCd.length() > ordTakeMdseCdLen) {
            ordTakeMdseCd = mdseCd.substring(0, ordTakeMdseCdLen);
        }
        ZYPEZDItemValueSetter.setValue(queryOrdTakeMdseTMsg.ordTakeMdseCd, ordTakeMdseCd);
        ORD_TAKE_MDSETMsg rsltOrdTakeMdseTMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(queryOrdTakeMdseTMsg);
        String keyMdseCd = mdseCd;
        boolean isUsingOrdTake = false;
        if (rsltOrdTakeMdseTMsg != null) {
            keyMdseCd = ordTakeMdseCd;
            isUsingOrdTake = true;
        }

        for (int i = 1; i < mdseList.size(); i++) {
            String compMdseCd = (String) mdseList.get(i).get("MDSE_CD");
            if (isUsingOrdTake && compMdseCd.length() > ordTakeMdseCdLen) {
                compMdseCd = compMdseCd.substring(0, ordTakeMdseCdLen);
            }
            if (!S21StringUtil.isEquals(keyMdseCd, compMdseCd)) {
                return true;
            }
        }
        return false;
    }
    // 2018/09/07 S21_NA#28085 Add End

    // 2018/10/24 S21_NA#28759 Add Start
    private static String changeMdseCd(String mdseCd, String glblCmpyCd) {
        ORD_TAKE_MDSETMsg queryOrdTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(queryOrdTakeMdseTMsg.glblCmpyCd, glblCmpyCd);
        int ordTakeMdseCdLen = queryOrdTakeMdseTMsg.getAttr("ordTakeMdseCd").getDigit();

        String ordTakeMdseCd = mdseCd;
        if (mdseCd.length() > ordTakeMdseCdLen) {
            ordTakeMdseCd = mdseCd.substring(0, ordTakeMdseCdLen);
        }
        ZYPEZDItemValueSetter.setValue(queryOrdTakeMdseTMsg.ordTakeMdseCd, ordTakeMdseCd);

        ORD_TAKE_MDSETMsg rsltOrdTakeMdseTMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(queryOrdTakeMdseTMsg);
        if (rsltOrdTakeMdseTMsg != null) {
            return ordTakeMdseCd;
        } else {
            return mdseCd;
        }
    }
    // 2018/10/24 S21_NA#28759 Add End
}
