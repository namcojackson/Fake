/**
 * <Pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLCL0090.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLCL0090.NLCL0090CMsg;
import business.blap.NLCL0090.NLCL0090Query;
import business.blap.NLCL0090.constant.NLCL0090Constant;
import business.db.AVAL_INVTY_APP_IDTMsg;
import business.db.INVTYTMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_WHTMsg;
import business.db.SWHTMsg;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * <pre>
 * Item Change Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/06/29   Fujitsu         FXS)KF.Qian     Create          N/A
 * 2010/04/15   Fujitsu         S.Yoshida       Update          Def.5017
 * 2013/05/23   Fujitsu         F.Saito         Update          R-OM025 Inventory Transaction
 * 03/03/2016   CSAI            Y.Imazu         Update          CSA
 *</pre>
 */
public class NLCL0090CommonLogic implements NLCL0090Constant {

    /**
     * getInvty
     * @param bizMsg NLCL0090CMsg
     * @param mdseCd String
     * @return INVTYTMsg
     */
    public static INVTYTMsg getInvty(NLCL0090CMsg bizMsg, String mdseCd) {

        INVTYTMsg invtyTMsg = new INVTYTMsg();
        ZYPEZDItemValueSetter.setValue(invtyTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(invtyTMsg.locStsCd, bizMsg.locStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTMsg.stkStsCd, bizMsg.stkStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(invtyTMsg.invtyLocCd, bizMsg.invtyLocCd.getValue());

        return (INVTYTMsg) EZDTBLAccessor.findByKey(invtyTMsg);
    }

    /**
     * getInvtyLoc
     * @param bizMsg NLCL0090CMsg
     * @return RTL_SWHTMsg
     */
    public static RTL_SWHTMsg getInvtyLoc(NLCL0090CMsg bizMsg) {

        RTL_SWHTMsg rtlSwhTMsg = new RTL_SWHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlWhCd, bizMsg.rtlWhCd.getValue());
        ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlSwhCd, bizMsg.rtlSwhCd.getValue());

        return (RTL_SWHTMsg) EZDTBLAccessor.findByKey(rtlSwhTMsg);
    }

    /**
     * findInvtyInfoForRecChk
     * @param bizMsg NLCL0090CMsg
     * @param mdseItem EZDCStringItem
     * @param invtyAvalQtyItem EZDCBigDecimalItem
     * @param invtyQtyItem EZDCBigDecimalItem
     * @return boolean
     */
    public static boolean findInvtyInfoForRecChk(NLCL0090CMsg bizMsg, EZDCStringItem mdseItem, EZDCBigDecimalItem invtyAvalQtyItem, EZDCBigDecimalItem invtyQtyItem) {

        INVTYTMsg invtyTMsg = getInvty(bizMsg, mdseItem.getValue());

        if (invtyTMsg == null || !ZYPCommonFunc.hasValue(invtyTMsg.invtyAvalQty) || BigDecimal.ZERO.equals(invtyTMsg.invtyAvalQty.getValue())) {

            ZYPEZDItemValueSetter.setValue(invtyAvalQtyItem, BigDecimal.ZERO);
            mdseItem.setErrorInfo(1, NLCM0021E);
            bizMsg.setMessageInfo(ZZM9037E);
            return false;
        }

        ZYPEZDItemValueSetter.setValue(invtyAvalQtyItem, invtyTMsg.invtyAvalQty.getValue());

        if (BigDecimal.ZERO.compareTo(invtyAvalQtyItem.getValue()) > 0) {

            invtyAvalQtyItem.setErrorInfo(1, NLCM0032E);
            bizMsg.setMessageInfo(ZZM9037E);
            return false;
        }

        if (invtyAvalQtyItem.getValue().compareTo(invtyQtyItem.getValue()) < 0) {

            invtyQtyItem.setErrorInfo(1, NLCM0031E);
            bizMsg.setMessageInfo(ZZM9037E);
            return false;
        }

        return true;
    }

    /**
     * findAvalInvtyAppId
     * @param bizMsg NLCL0090CMsg
     * @return boolean
     */
    public static boolean findAvalInvtyAppId(NLCL0090CMsg bizMsg) {

        AVAL_INVTY_APP_IDTMsg avalInvtyAppIDTMsg = new AVAL_INVTY_APP_IDTMsg();
        ZYPEZDItemValueSetter.setValue(avalInvtyAppIDTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(avalInvtyAppIDTMsg.bizAppId, BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(avalInvtyAppIDTMsg.locStsCd, bizMsg.locStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(avalInvtyAppIDTMsg.stkStsCd, bizMsg.stkStsCd.getValue());

        avalInvtyAppIDTMsg = (AVAL_INVTY_APP_IDTMsg) EZDTBLAccessor.findByKey(avalInvtyAppIDTMsg);

        if (avalInvtyAppIDTMsg == null) {

            bizMsg.stkStsCd.setErrorInfo(1, NLCM0067E, new String[] {STKSTS });
            bizMsg.setMessageInfo(ZZM9037E);
            return false;
        }

        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param mdseItem EZDCStringItem
     * @return Map<String, Object>
     */
    public static Map<String, Object> findMdse(NLCL0090CMsg bizMsg, EZDCStringItem mdseItem) {

        S21SsmEZDResult ssmResult = NLCL0090Query.getInstance().getMdseInfo(bizMsg.glblCmpyCd.getValue(), mdseItem.getValue());

        if (ssmResult.isCodeNormal()) {

            Map<String, Object> mdseMap = (Map<String, Object>) ssmResult.getResultObject();

            if (mdseMap != null) {

                return mdseMap;
            }
        }

        mdseItem.setErrorInfo(1, NLZM2278E, new String[] {"Item Number" });
        bizMsg.setMessageInfo(ZZM9037E);

        return null;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NLCL0090CMsg
     * @param mdseItem EZDCStringItem
     * @param rgtnStsCd String
     * @return boolean
     */
    public static boolean checkMdseRgtnSts(NLCL0090CMsg bizMsg, EZDCStringItem mdseItem, String rgtnStsCd) {

         if (RGTN_STS.PENDING_COMPLETION.equals(rgtnStsCd)
                || RGTN_STS.TERMINATED.equals(rgtnStsCd)) {

             mdseItem.setErrorInfo(1, NLCM0065E, new String[] {"Item Number" });
             bizMsg.setMessageInfo(ZZM9037E);
            return false;
        }

        return true;
    }

    /**
     * checkMdseAddFrom
     * @param bizMsg NLCL0090CMsg
     * @return boolean
     */
    public static boolean checkMdseAddFrom(NLCL0090CMsg bizMsg) {

        Map<String, Object> mdseMap = findMdse(bizMsg, bizMsg.mdseCd_HF);

        if (mdseMap != null) {

            ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt_HF, (String) mdseMap.get("MDSE_DESC_SHORT_TXT"));

            if (checkMdseRgtnSts(bizMsg, bizMsg.mdseCd_HF, (String) mdseMap.get("RGTN_STS_CD"))) {

                ZYPEZDItemValueSetter.setValue(bizMsg.mdseTpCd_HF, (String) mdseMap.get("MDSE_TP_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.invtyCtrlFlg_HF, (String) mdseMap.get("INVTY_CTRL_FLG"));
                ZYPEZDItemValueSetter.setValue(bizMsg.invtyValFlg_HF, (String) mdseMap.get("INVTY_VAL_FLG"));
                ZYPEZDItemValueSetter.setValue(bizMsg.firstProdCtrlCd_HF, (String) mdseMap.get("FIRST_PROD_CTRL_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.rgtnStsCd_HF, (String) mdseMap.get("RGTN_STS_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shpgSerTakeFlg_HF, (String) mdseMap.get("SHPG_SER_TAKE_FLG"));
                ZYPEZDItemValueSetter.setValue(bizMsg.instlBaseCtrlFlg_HF, (String) mdseMap.get("INSTL_BASE_CTRL_FLG"));
                return true;
            }
        }

        return false;
    }

    /**
     * checkMdseAddTo
     * @param bizMsg NLCL0090CMsg
     * @return boolean
     */
    public static boolean checkMdseAddTo(NLCL0090CMsg bizMsg) {

        Map<String, Object> mdseMap = findMdse(bizMsg, bizMsg.mdseCd_HT);

        if (mdseMap != null) {

            ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt_HT, (String) mdseMap.get("MDSE_DESC_SHORT_TXT"));

            if (checkMdseRgtnSts(bizMsg, bizMsg.mdseCd_HT, (String) mdseMap.get("RGTN_STS_CD"))) {

                ZYPEZDItemValueSetter.setValue(bizMsg.mdseTpCd_HT, (String) mdseMap.get("MDSE_TP_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.invtyCtrlFlg_HT, (String) mdseMap.get("INVTY_CTRL_FLG"));
                ZYPEZDItemValueSetter.setValue(bizMsg.invtyValFlg_HT, (String) mdseMap.get("INVTY_VAL_FLG"));
                ZYPEZDItemValueSetter.setValue(bizMsg.firstProdCtrlCd_HT, (String) mdseMap.get("FIRST_PROD_CTRL_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.rgtnStsCd_HT, (String) mdseMap.get("RGTN_STS_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shpgSerTakeFlg_HT, (String) mdseMap.get("SHPG_SER_TAKE_FLG"));
                ZYPEZDItemValueSetter.setValue(bizMsg.instlBaseCtrlFlg_HT, (String) mdseMap.get("INSTL_BASE_CTRL_FLG"));
                return true;
            }
        }

        return false;
    }

    /**
     * Check Location Code
     * @param bizMsg NLCL0090CMsg
     * @param userProf S21UserProfileService
     * @param userInfo S21UserInfo
     * @return OK(true) or NG(false)
     */
    public static boolean isLocCdCheck(NLCL0090CMsg bizMsg, S21UserProfileService userProf, S21UserInfo userInfo) {

        boolean isChkWhAndSwh = true;

        // Retail WH Check
        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, bizMsg.rtlWhCd.getValue());

        rtlWhTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rtlWhTMsg);

        if (rtlWhTMsg != null) {

            ZYPEZDItemValueSetter.setValue(bizMsg.locNm_P1, rtlWhTMsg.rtlWhNm.getValue());

        } else {

            bizMsg.rtlWhCd.setErrorInfo(1, NLZM2278E, new String[] {"Warehouse" });
            bizMsg.setMessageInfo(ZZM9037E);
            isChkWhAndSwh = false;
        }

        // Sub WH Check
        SWHTMsg sWhTMsg = new SWHTMsg();
        ZYPEZDItemValueSetter.setValue(sWhTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(sWhTMsg.rtlSwhCd, bizMsg.rtlSwhCd.getValue());

        sWhTMsg = (SWHTMsg) EZDTBLAccessor.findByKey(sWhTMsg);

        if (sWhTMsg != null) {

            ZYPEZDItemValueSetter.setValue(bizMsg.locNm_P2, sWhTMsg.rtlSwhNm.getValue());

        } else {

            bizMsg.rtlSwhCd.setErrorInfo(1, NLZM2278E, new String[] {"Sub Warehouse" });
            bizMsg.setMessageInfo(ZZM9037E);
            isChkWhAndSwh = false;
        }

        if (!isChkWhAndSwh) {

            return false;
        }

        // Inventory Location Check
        RTL_SWHTMsg rtlSwhTMsg = new RTL_SWHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlWhCd, bizMsg.rtlWhCd.getValue());
        ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlSwhCd, bizMsg.rtlSwhCd.getValue());

        rtlSwhTMsg = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(rtlSwhTMsg);

        if (rtlSwhTMsg != null) {

            ZYPEZDItemValueSetter.setValue(bizMsg.invtyLocCd, rtlSwhTMsg.invtyLocCd.getValue());

        } else {

            bizMsg.rtlWhCd.setErrorInfo(1, NLZM2279E, new String[] {"Warehouse", "Sub Warehouse" });
            bizMsg.rtlSwhCd.setErrorInfo(1, NLZM2279E, new String[] {"Warehouse", "Sub Warehouse" });
            bizMsg.setMessageInfo(ZZM9037E);
            return false;
        }

        // Get Security Attribute
        List<S21DataSecurityAttributeData> securityAttr = userProf.getDataSecurityAttributeDataListFor(userInfo.getUserId(), BUSINESS_ID, S21DataSecurityAttributeData.NAME_WAREHOUSE);

        // Get Location Info
        NMXC100001EnableWHData locInfo = NMXC100001EnableWH.checkEnableWH(bizMsg.glblCmpyCd.getValue(), bizMsg.invtyLocCd.getValue(), BUSINESS_ID, securityAttr, ZYPConstant.FLG_OFF_N);

        // NG LocCd
        if (locInfo == null) {

            bizMsg.rtlWhCd.setErrorInfo(1, NLCM0004E);
            bizMsg.rtlSwhCd.setErrorInfo(1, NLCM0004E);
            bizMsg.setMessageInfo(ZZM9037E);
            return false;

        } else if (ZYPCommonFunc.hasValue(locInfo.getXxMsgId())) {

            bizMsg.rtlWhCd.setErrorInfo(1, locInfo.getXxMsgId());
            bizMsg.rtlSwhCd.setErrorInfo(1, locInfo.getXxMsgId());
            bizMsg.setMessageInfo(ZZM9037E);
            return false;
        }

        return true;
    }

    /**
     * Check Serial (From)
     * @param bizMsg NLCL0090CMsg
     * @param index int
     * @return OK(true) or NG(false)
     */
    public static boolean checkSerialAddFrom(NLCL0090CMsg bizMsg, int index) {

        String mdseCd = null;
        String serNum = null;
        EZDCStringItem serItem = null;
        EZDCBigDecimalItem machMstrPkItem = null;
        EZDCBigDecimalItem configMstrPkItem = null;
        EZDCBigDecimalItem invtyQty = null;

        if (index == -1) {

            mdseCd = bizMsg.mdseCd_HF.getValue();
            serNum = bizMsg.serNum_HF.getValue();
            serItem = bizMsg.serNum_HF;
            machMstrPkItem = bizMsg.svcMachMstrPk_HF;
            configMstrPkItem = bizMsg.svcConfigMstrPk_HF;
            invtyQty = bizMsg.invtyQty_HF;

        } else {

            mdseCd = bizMsg.A.no(index).mdseCd_DF.getValue();
            serNum = bizMsg.A.no(index).serNum_DF.getValue();
            serItem = bizMsg.A.no(index).serNum_DF;
            machMstrPkItem = bizMsg.A.no(index).svcMachMstrPk_DF;
            configMstrPkItem = bizMsg.A.no(index).svcConfigMstrPk_DF;
            invtyQty = bizMsg.A.no(index).invtyQty_DF;
        }

        S21SsmEZDResult ssmResult = NLCL0090Query.getInstance().getSvcMachMstrInfo(bizMsg.glblCmpyCd.getValue(), mdseCd, serNum);

        if (ssmResult.isCodeNormal()) {

            Map<String, Object> machMstrInfoMap = (Map<String, Object>) ssmResult.getResultObject();

            if (machMstrInfoMap != null && !machMstrInfoMap.isEmpty()) {

                boolean isChkMachMstr = true;
                String errMsgId = null;

                // Allocated by other order
                if (ZYPCommonFunc.hasValue((String) machMstrInfoMap.get("TRX_HDR_NUM"))
                        || ZYPConstant.FLG_OFF_N.equals((String) machMstrInfoMap.get("SVC_MACH_MAINT_AVAL_FLG"))) {

                    isChkMachMstr = false;
                    errMsgId = NLZM2317E;

                // Not Located at WH
                } else if (!SVC_MACH_MSTR_STS.CREATED.equals((String) machMstrInfoMap.get("SVC_MACH_MSTR_STS_CD"))
                        && !SVC_MACH_MSTR_STS.REMOVED.equals((String) machMstrInfoMap.get("SVC_MACH_MSTR_STS_CD"))) {

                    isChkMachMstr = false;
                    errMsgId = NLZM2318E;

                // A part of configuration
                } else if (!chkConfigCompCnt(bizMsg.glblCmpyCd.getValue(), (BigDecimal) machMstrInfoMap.get("SVC_CONFIG_MSTR_PK"))) {

                    isChkMachMstr = false;
                    errMsgId = NLCM0150E;

                // Different WH
                } else if (!bizMsg.invtyLocCd.getValue().equals((String) machMstrInfoMap.get("CUR_LOC_NUM"))) {

                    isChkMachMstr = false;
                    errMsgId = NLCM0151E;

                // Different location status
                } else if (!bizMsg.locStsCd.getValue().equals((String) machMstrInfoMap.get("SVC_MACH_MSTR_LOC_STS_CD"))) {

                    isChkMachMstr = false;
                    errMsgId = NLCM0152E;

                // Different stock status
                } else if (!bizMsg.stkStsCd.getValue().equals((String) machMstrInfoMap.get("STK_STS_CD"))) {

                    isChkMachMstr = false;
                    errMsgId = NLCM0153E;

                // Quantity
                } else if (ZYPCommonFunc.hasValue((BigDecimal) machMstrInfoMap.get("SVC_CONFIG_MSTR_PK")) && BigDecimal.ONE.compareTo(invtyQty.getValue()) < 0) {

                    isChkMachMstr = false;
                    errMsgId = NLCM0167E;
                }

                if (!isChkMachMstr) {

                    serItem.setErrorInfo(1, errMsgId);
                    bizMsg.setMessageInfo(ZZM9037E);
                    return false;
                }

                ZYPEZDItemValueSetter.setValue(machMstrPkItem, (BigDecimal) machMstrInfoMap.get("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(configMstrPkItem, (BigDecimal) machMstrInfoMap.get("SVC_CONFIG_MSTR_PK"));

                if (index != -1) {

                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).ezUpTime_IB, (String) machMstrInfoMap.get("EZUPTIME"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).ezUpTimeZone_IB, (String) machMstrInfoMap.get("EZUPTIMEZONE"));
                }

                return true;
            }
        }

        serItem.setErrorInfo(1, NLZM2278E, new String[] {"Serial" });
        bizMsg.setMessageInfo(ZZM9037E);
        return false;
    }

    /**
     * Check Configuration Component Count
     * @param machMstrInfoMap Map<String, Object>
     * @return OK(true) or NG(false)
     */
    private static boolean chkConfigCompCnt(String glblCmpyCd, BigDecimal svcConfigMstrPk) {

        if (ZYPCommonFunc.hasValue(svcConfigMstrPk)) {

            S21SsmEZDResult ssmResult = NLCL0090Query.getInstance().getConfigCompCnt(glblCmpyCd, svcConfigMstrPk);

            BigDecimal configCompCnt = (BigDecimal) ssmResult.getResultObject();

            if (ZYPCommonFunc.hasValue(configCompCnt) && BigDecimal.ONE.compareTo(configCompCnt) < 0) {

                return false;
            }
        }

        return true;
    }

    /**
     * Check Serial (To)
     * @param bizMsg NLCL0090CMsg
     * @param mdseItem EZDCStringItem
     * @param serItem EZDCStringItem
     * @return OK(true) or NG(false)
     */
    public static boolean checkSerialAddTo(NLCL0090CMsg bizMsg, EZDCStringItem mdseItem, EZDCStringItem serItem) {

        S21SsmEZDResult ssmResult = NLCL0090Query.getInstance().getSvcMachMstrInfo(bizMsg.glblCmpyCd.getValue(), mdseItem.getValue(), serItem.getValue());

        if (ssmResult.isCodeNormal()) {

            Map<String, Object> machMstrInfoMap = (Map<String, Object>) ssmResult.getResultObject();

            if (machMstrInfoMap != null && machMstrInfoMap.isEmpty()) {

                serItem.setErrorInfo(1, NLZM2141E);
                bizMsg.setMessageInfo(ZZM9037E);
                return false;
            }
        }

        return true;
    }

    /**
     * clearScrnItemValue
     * @param bizMsg NLCL0090CMsg
     */
    public static void clearScrnItemValue(NLCL0090CMsg bizMsg) {

        // Header
        bizMsg.rtlWhCd.clear();
        bizMsg.rtlSwhCd.clear();
        bizMsg.locNm_P1.clear();
        bizMsg.locNm_P2.clear();
        bizMsg.invtyOrdNum.clear();
        bizMsg.soNum.clear();
        bizMsg.locStsCd.clear();
        bizMsg.stkStsCd.clear();
        bizMsg.firstInvtyOrdCmntTxt.clear();
        bizMsg.scdInvtyOrdCmntTxt.clear();
        bizMsg.thirdInvtyOrdCmntTxt.clear();
        bizMsg.invtyOrdStsDescTxt.clear();
        bizMsg.xxTsDsp19Txt_SB.clear();
        bizMsg.xxTsDsp19Txt_CL.clear();

        // From
        bizMsg.mdseCd_HF.clear();
        bizMsg.mdseDescShortTxt_HF.clear();
        bizMsg.invtyQty_HF.clear();
        bizMsg.invtyAvalQty_HF.clear();
        bizMsg.serNum_HF.clear();
        bizMsg.svcMachMstrPk_HF.clear();
        bizMsg.shpgSerTakeFlg_HF.clear();
        bizMsg.instlBaseCtrlFlg_HF.clear();
        bizMsg.svcMachMstrPk_HF.clear();
        ZYPTableUtil.clear(bizMsg.A);

        // To
        bizMsg.mdseCd_HT.clear();
        bizMsg.mdseDescShortTxt_HT.clear();
        bizMsg.invtyQty_HT.clear();
        bizMsg.serNum_HT.clear();
        bizMsg.svcMachMstrPk_HT.clear();
        bizMsg.shpgSerTakeFlg_HT.clear();
        bizMsg.instlBaseCtrlFlg_HT.clear();
        bizMsg.svcMachMstrPk_HT.clear();
        ZYPTableUtil.clear(bizMsg.B);

        // Hidden
        bizMsg.xxScrEventNm.clear();
        bizMsg.mdseTpCd_HF.clear();
        bizMsg.invtyCtrlFlg_HF.clear();
        bizMsg.invtyValFlg_HF.clear();
        bizMsg.firstProdCtrlCd_HF.clear();
        bizMsg.rgtnStsCd_HF.clear();
        bizMsg.mdseTpCd_HT.clear();
        bizMsg.invtyCtrlFlg_HT.clear();
        bizMsg.invtyValFlg_HT.clear();
        bizMsg.firstProdCtrlCd_HT.clear();
        bizMsg.rgtnStsCd_HT.clear();
        ZYPTableUtil.clear(bizMsg.P);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdDplyFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.submtFlg, ZYPConstant.FLG_OFF_N);
    }
}
