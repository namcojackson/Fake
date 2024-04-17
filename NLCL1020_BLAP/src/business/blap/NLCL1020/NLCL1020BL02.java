/**
 * <pre> * Copyright(c)2010 Canon USA Inc. All rights reserved. * </pre>
 */
package business.blap.NLCL1020;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import business.blap.NLCL1020.common.NLCL1020CommonLogic;
import business.blap.NLCL1020.constant.NLCL1020Constant;
import business.db.ALL_MDSE_VTMsg;
import business.db.AVAL_INVTY_APP_IDTMsg;
import business.db.INVTYTMsg;
import business.db.MDSETMsg;

import com.canon.cusa.s21.common.NLX.NLXC011001.NLXLocationStatus;
import com.canon.cusa.s21.common.NLX.NLXC012001.NLXStockStatus;
import com.canon.cusa.s21.common.NLX.NLXC034001.NLXProdLineCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityProfile;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/07/2013   Fujitsu         Y.Taoka         Create          R-WH001
 * 01/22/2019   Fujitsu         S.Ohki          Update          QC#30004
 * 04/13/2023   CSA             G.Quan          Insert          QC#61206
 *</pre>
 */
public class NLCL1020BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            // NLCL1020_INIT
            if (NLCL1020Constant.NLCL1020_INIT.equals(screenAplID)) {
                doProcess_NLCL1020_INIT(cMsg, sMsg);

                // NLCL1020Scrn00_Add_Dtaill_Line
            } else if (NLCL1020Constant.NLCL1020SCRN00_ADD_DTAILL_LINE.equals(screenAplID)) {
                doProcess_NLCL1020Scrn00_Add_Dtaill_Line(cMsg, sMsg);

                // NLCL1020Scrn00_SearchTrx
            } else if (NLCL1020Constant.NLCL1020SCRN00_SEARCH_TRX.equals(screenAplID)) {
                doProcess_NLCL1020Scrn00_SearchTrx(cMsg, sMsg);

                // NLCL1020Scrn00_CMN_Apply
            } else if (NLCL1020Constant.NLCL1020SCRN00_CMN_APPLY.equals(screenAplID)) {
                doProcess_NLCL1020Scrn00_CMN_Apply(cMsg, sMsg);

                // NLCL1020Scrn00_CMN_Submit
            } else if (NLCL1020Constant.NLCL1020SCRN00_CMN_SUBMIT.equals(screenAplID)) {
                // do nothing

                // NLCL1020Scrn00_CMN_Download
            } else if (NLCL1020Constant.NLCL1020SCRN00_CMN_DOWNLOAD.equals(screenAplID)) {
                doProcess_NLCL1020Scrn00_CMN_Download(cMsg, sMsg);

                // NLCL1020Scrn00_Display_MDSEName
            } else if (NLCL1020Constant.NLCL1020SCRN00_DISPLAY_MDSENAME.equals(screenAplID)) {
                doProcess_NLCL1020Scrn00_Display_MDSEName(cMsg, sMsg);

                // NLCL1020Scrn00_Display_AvalQty
            } else if (NLCL1020Constant.NLCL1020SCRN00_DISPLAY_AVAL_QTY.equals(screenAplID)) {
                doProcess_NLCL1020Scrn00_Display_AvalQty(cMsg, sMsg);

                // NLCL1020Scrn00_Onchange_StkSts
            } else if (NLCL1020Constant.NLCL1020SCRN00_ONCHANGE_STK_STS.equals(screenAplID)) {
                doProcess_NLCL1020Scrn00_Onchange_StkSts(cMsg, sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLCL1020_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLCL1020_INIT================================START", this);

        NLCL1020CMsg bizMsg = (NLCL1020CMsg) cMsg;

        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        ZYPEZDItemValueSetter.setValue(bizMsg.xxLocRoleTpCdListTxt_TP, NLCL1020CommonLogic.getLocRoleTpForPopup(getGlobalCompanyCode()));

        if (!isLocStsListBox(bizMsg)) {
            return;
        }
        if (!isSSListBox(bizMsg)) {
            return;
        }

        EZDDebugOutput.println(1, "doProcess_NLCL1020_INIT================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLCL1020Scrn00_Add_Dtaill_Line(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLCL1020Scrn00_Add_Dtaill_Line================================START", this);

        NLCL1020CMsg bizMsg = (NLCL1020CMsg) cMsg;

        //Check Location Code From/To
        boolean outIsAvalLocCd = NLCL1020CommonLogic.isAvalLocCd(bizMsg);
        if (!outIsAvalLocCd) {
            return;
        }

        if (!NLCL1020CommonLogic.isLocCorrelation(bizMsg)) {
            return;
        }

        MDSETMsg outGetMDSEInfo = getMDSEInfo(bizMsg);

        if (outGetMDSEInfo == null) {
            return;
        }

        // QC#30004 Del Start
//        boolean outIsAvalUseMdseCd = isAvalUseMdseCd(bizMsg, outGetMDSEInfo);
//
//        if (!outIsAvalUseMdseCd) {
//            return;
//        }
        // QC#30004 Del End

        boolean outIsSSAllocationCheck = isSSAllocationCheck(bizMsg);

        if (!outIsSSAllocationCheck) {
            return;
        }

        boolean outIsAvailableQtyCheck = isAvailableQtyCheck(bizMsg);

        if (!outIsAvailableQtyCheck) {
            return;
        }

        int index = bizMsg.A.getValidCount();

        bizMsg.A.setValidCount(index + 1);

        bizMsg.A.no(index).xxChkBox_AD.clear();
        bizMsg.A.no(index).invtyOrdLineNum_A1.setValue(String.valueOf(index + 1));
        bizMsg.A.no(index).mdseCd_A1.setValue(bizMsg.mdseCd_HD.getValue());
        bizMsg.A.no(index).mdseDescShortTxt_A1.setValue(bizMsg.mdseDescShortTxt_HD.getValue());
        bizMsg.A.no(index).stkStsCd_AP.setValue(bizMsg.stkStsCd_PH.getValue());
        bizMsg.A.no(index).invtyAvalQty_AI.setValue(bizMsg.invtyAvalQty_HI.getValue());
        bizMsg.A.no(index).invtyAvalQty_AO.setValue(bizMsg.invtyAvalQty_HO.getValue());

        EZDDebugOutput.println(1, "doProcess_NLCL1020Scrn00_Add_Dtaill_Line================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLCL1020Scrn00_Display_MDSEName(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLCL1020Scrn00_Display_MDSEName================================START", this);

        NLCL1020CMsg bizMsg = (NLCL1020CMsg) cMsg;

        ALL_MDSE_VTMsg outMdseMsg = NLCL1020CommonLogic.findMdseInfobyView(bizMsg);

        if (outMdseMsg == null) {

            bizMsg.mdseCd_HD.setErrorInfo(1, NLCL1020Constant.NLCM0002E);
            EZDDebugOutput.println(1, "doProcess_NLCL1020Scrn00_Display_MDSEName--### Error ###--[Mdse Nm]No Search Results.", this);
            return;
        } else {
            bizMsg.mdseDescShortTxt_HD.setValue(outMdseMsg.mdseDescShortTxt.getValue());
        }

        EZDDebugOutput.println(1, "doProcess_NLCL1020Scrn00_Display_MDSEName================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLCL1020Scrn00_CMN_Download(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLCL1020Scrn00_CMN_Download================================START", this);

        NLCL1020CMsg bizMsg = (NLCL1020CMsg) cMsg;

        //Location Check
        if (!NLCL1020CommonLogic.isAvalLocCdFroCSV(bizMsg)) {
            return;
        }

        csvDonwnLoad(bizMsg);

        EZDDebugOutput.println(1, "doProcess_NLCL1020Scrn00_CMN_Download================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @SuppressWarnings("unchecked")
    private void doProcess_NLCL1020Scrn00_SearchTrx(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLCL1020Scrn00_SearchTrx================================START", this);

        NLCL1020CMsg bizMsg = (NLCL1020CMsg) cMsg;
        bizMsg.A.setValidCount(0);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        ssmParam.put("invtyOrdTpCd", INVTY_ORD_TP.INTERNAL_DC_TRANSFER);
        ssmParam.put("invtyOrdNum", bizMsg.invtyOrdNum.getValue());

        List<Object> list = NLCL1020CommonLogic.searchByTrxNum(ssmParam);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);

                if (i == 0) {
                    // 10/20/2015 mod start
//                    bizMsg.invtyLocCd_FR.setValue(NLCL1020CommonLogic.checkNull((String) map.get("FROM_LOC_CD")));
//                    bizMsg.invtyLocNm_FR.setValue(NLCL1020CommonLogic.checkNull((String) map.get("FROM_INVTY_LOC_NM")));
//                    bizMsg.invtyLocCd_TO.setValue(NLCL1020CommonLogic.checkNull((String) map.get("TO_LOC_CD")));
//                    bizMsg.invtyLocNm_TO.setValue(NLCL1020CommonLogic.checkNull((String) map.get("TO_INVTY_LOC_NM")));
                    ZYPEZDItemValueSetter.setValue(bizMsg.fromRtlWhCd, NLCL1020CommonLogic.checkNull((String) map.get("FROM_RTL_WH_CD")));
                    ZYPEZDItemValueSetter.setValue(bizMsg.fromRtlSwhCd, NLCL1020CommonLogic.checkNull((String) map.get("FROM_RTL_SWH_CD")));
                    ZYPEZDItemValueSetter.setValue(bizMsg.locNm_FR, NLCL1020CommonLogic.checkNull((String) map.get("FROM_INVTY_LOC_NM")));
                    ZYPEZDItemValueSetter.setValue(bizMsg.toRtlWhCd, NLCL1020CommonLogic.checkNull((String) map.get("TO_RTL_WH_CD")));
                    ZYPEZDItemValueSetter.setValue(bizMsg.toRtlSwhCd, NLCL1020CommonLogic.checkNull((String) map.get("TO_RTL_SWH_CD")));
                    ZYPEZDItemValueSetter.setValue(bizMsg.locNm_TO, NLCL1020CommonLogic.checkNull((String) map.get("TO_INVTY_LOC_NM")));
                    // 10/20/2015 mod end
                    // Comment
                    bizMsg.firstInvtyOrdCmntTxt.setValue(NLCL1020CommonLogic.checkNull((String) map.get("FIRST_INVTY_ORD_CMNT_TXT")));
                    bizMsg.scdInvtyOrdCmntTxt.setValue(NLCL1020CommonLogic.checkNull((String) map.get("SCD_INVTY_ORD_CMNT_TXT")));
                    bizMsg.thirdInvtyOrdCmntTxt.setValue(NLCL1020CommonLogic.checkNull((String) map.get("THIRD_INVTY_ORD_CMNT_TXT")));
                }

                bizMsg.A.no(i).xxChkBox_AD.clear();
                bizMsg.A.no(i).invtyOrdLineNum_A1.setValue(Integer.toString((NLCL1020CommonLogic.checkNull((BigDecimal) map.get("INVTY_ORD_LINE_NUM"))).intValue()));
                bizMsg.A.no(i).mdseCd_A1.setValue(NLCL1020CommonLogic.checkNull((String) map.get("MDSE_CD")));
                bizMsg.A.no(i).mdseDescShortTxt_A1.setValue(NLCL1020CommonLogic.checkNull((String) map.get("MDSE_DESC_SHORT_TXT")));
                bizMsg.A.no(i).stkStsCd_AP.setValue(NLCL1020CommonLogic.checkNull((String) map.get("STK_STS_CD")));
                bizMsg.A.no(i).invtyAvalQty_AI.setValue(NLCL1020CommonLogic.checkNull((BigDecimal) map.get("ORD_QTY")));
                // START 2023/04/13 G.Quan [QC#61206, ADD]
                bizMsg.A.no(i).xxRecHistCratTs_A1.setValue(NLCL1020CommonLogic.checkNull((String) map.get("XX_REC_HIST_CRAT_TS")));
                bizMsg.A.no(i).xxRecHistCratByNm_A1.setValue(NLCL1020CommonLogic.checkNull(ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM"))));
                bizMsg.A.no(i).xxRecHistUpdTs_A1.setValue(NLCL1020CommonLogic.checkNull((String) map.get("XX_REC_HIST_UPD_TS")));
                bizMsg.A.no(i).xxRecHistUpdByNm_A1.setValue(NLCL1020CommonLogic.checkNull(ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM"))));
                bizMsg.A.no(i).xxRecHistTblNm_A1.setValue(NLCL1020CommonLogic.checkNull((String) map.get("XX_REC_HIST_TBL_NM")));
                // END 2023/04/13 G.Quan [QC#61206, ADD]

                BigDecimal invtyAvalQty = getAvalQty(bizMsg, i);
                if (invtyAvalQty != null && ZYPCommonFunc.hasValue(invtyAvalQty)) {
                    bizMsg.A.no(i).invtyAvalQty_AO.setValue(invtyAvalQty);
                }

                bizMsg.A.setValidCount(i + 1);
            }
        } else {
            bizMsg.setMessageInfo(NLCL1020Constant.NLCM0111E, new String[] {"No Result", "Found" });
        }

        EZDDebugOutput.println(1, "doProcess_NLCL1020Scrn00_SearchTrx================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NLCL1020CMsg
     * @param index  int
     * @return
     */
    private BigDecimal getAvalQty(NLCL1020CMsg bizMsg, int index) {

        NLCL1020_ACMsg aCMsg = bizMsg.A.no(index);
        INVTYTMsg tMsg = NLCL1020CommonLogic.findInvtyInfo(bizMsg, aCMsg.mdseCd_A1.getValue(), aCMsg.stkStsCd_AP.getValue());
        if (tMsg != null) {
            return tMsg.invtyAvalQty.getValue();
        } else {
            return null;
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NLCL1020CMsg
     * @return boolean
     */
    private boolean csvDonwnLoad(NLCL1020CMsg bizMsg) {
        Map<String, Object> ssmParam = getParamMap(bizMsg);
        final boolean isNormalEnd = NLCL1020Query.getInstance().getResultForCsv(bizMsg, ssmParam);

        // Nothing data or over limit.
        if (!isNormalEnd) {
            return false;
        }
        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NLCL1020CMsg
     * @return Map<String, Object>
     */
    private Map<String, Object> getParamMap(NLCL1020CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        ssmParam.put("readyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("salesDate", ZYPDateUtil.getSalesDate());
        ssmParam.put("dcStock", LOC_STS.DC_STOCK);

        // 10/20/2015 mod start
//        ssmParam.put("locFrom", bizMsg.invtyLocCd_FR.getValue());
//        ssmParam.put("locTo", bizMsg.invtyLocCd_TO.getValue());
        ssmParam.put("locFrom", NLCL1020CommonLogic.getInvtyLocCdFromRtlSwh(this.getGlobalCompanyCode(), bizMsg.fromRtlWhCd.getValue(), bizMsg.fromRtlSwhCd.getValue()));
        ssmParam.put("locTo", NLCL1020CommonLogic.getInvtyLocCdFromRtlSwh(this.getGlobalCompanyCode(), bizMsg.toRtlWhCd.getValue(), bizMsg.toRtlSwhCd.getValue()));
        // 10/20/2015 mod end

        return ssmParam;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLCL1020Scrn00_CMN_Apply(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLCL1020Scrn00_CMN_Apply================================START", this);

        NLCL1020CMsg bizMsg = (NLCL1020CMsg) cMsg;

        NLCL1020CommonLogic.isSubmitApplyCheck(bizMsg);

        EZDDebugOutput.println(1, "doProcess_NLCL1020Scrn00_CMN_Apply================================END", this);
    }


    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLCL1020Scrn00_Display_AvalQty(EZDCMsg cMsg, EZDSMsg sMsg) {
        NLCL1020CMsg bizMsg = (NLCL1020CMsg) cMsg;
        getCurrentAvailability(bizMsg);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLCL1020Scrn00_Onchange_StkSts(EZDCMsg cMsg, EZDSMsg sMsg) {
        NLCL1020CMsg bizMsg = (NLCL1020CMsg) cMsg;
        getCurrentAvailability(bizMsg);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @return boolean
     */
    private boolean isLocStsListBox(NLCL1020CMsg bizMsg) {

        String msgID = NLXLocationStatus.exec(bizMsg.glblCmpyCd.getValue(), NLCL1020Constant.BUSINESS_ID, bizMsg.locStsCd_H1, bizMsg.xxLocStsTxt_H1);

        if (ZYPCommonFunc.hasValue(msgID)) {
            bizMsg.setMessageInfo(msgID);
            EZDDebugOutput.println(1, "isLocStsListBox--### Error ###--[NLXLocationStatus]Abnormal results.", this);

            return false;
        }

        for (int i = 0; i < bizMsg.locStsCd_H1.length(); i++) {

            if (ZYPCommonFunc.hasValue(bizMsg.locStsCd_H1.no(i))) {
                if (LOC_STS.DC_STOCK.equals(bizMsg.locStsCd_H1.no(i).getValue())) {
                    bizMsg.locStsCd_P1.setValue(LOC_STS.DC_STOCK);
                    return true;
                }
            }
        }

        bizMsg.setMessageInfo(NLCL1020Constant.NLCM0001E);
        EZDDebugOutput.println(1, "isLocStsListBox--### Error ###", this);

        return false;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @return boolean
     */
    private boolean isSSListBox(NLCL1020CMsg bizMsg) {

        String msgID = NLXStockStatus.exec(bizMsg.glblCmpyCd.getValue(), NLCL1020Constant.BUSINESS_ID, bizMsg.stkStsCd_HH, bizMsg.xxStkStsTxt_HH);

        if (ZYPCommonFunc.hasValue(msgID)) {

            bizMsg.setMessageInfo(msgID);
            EZDDebugOutput.println(1, "isSSListBox--### Error ###--[NLXStockStatus]Abnormal results.", this);

            return false;
        }
        return true;
    }


    /**
     * Get Current Available Qty.
     * @param bizMsg Business Component Interface Message
     */
    private void getCurrentAvailability(NLCL1020CMsg bizMsg) {
        bizMsg.invtyAvalQty_HO.clear();

        INVTYTMsg invtyTMsg = NLCL1020CommonLogic.findInvtyInfo(bizMsg, bizMsg.mdseCd_HD.getValue(), bizMsg.stkStsCd_PH.getValue());
        if (invtyTMsg == null) {
            bizMsg.mdseCd_HD.setErrorInfo(1, NLCL1020Constant.NLCM0021E);
            bizMsg.stkStsCd_PH.setErrorInfo(1, NLCL1020Constant.NLCM0021E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.invtyAvalQty_HO, invtyTMsg.invtyAvalQty.getValue());
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @return MDSETMsg
     */
    private MDSETMsg getMDSEInfo(NLCL1020CMsg bizMsg) {

        bizMsg.mdseDescShortTxt_HD.clear();

        MDSETMsg inMdseMsg = new MDSETMsg();

        MDSETMsg outMdseMsg = NLCL1020CommonLogic.findMdseInfo(bizMsg, inMdseMsg);
        ALL_MDSE_VTMsg outMdseMsgbyView = NLCL1020CommonLogic.findMdseInfobyView(bizMsg);
        if (outMdseMsg == null) {
            bizMsg.mdseCd_HD.setErrorInfo(1, NLCL1020Constant.NLCM0002E);
            EZDDebugOutput.println(1, "getMDSEInfo--### Error ###--[Mdse]No Search Results.", this);

            return null;
        } else {
            if (outMdseMsgbyView != null) {
            String mdseDescShortTxt = outMdseMsgbyView.mdseDescShortTxt.getValue();
            bizMsg.mdseDescShortTxt_HD.setValue(mdseDescShortTxt);
            }
        }
        return outMdseMsg;
    }

    // QC#30004 Del Start
//    /**
//     * <dd>The method explanation: The business peculiarity
//     * processing is executed.
//     * @param bizMsg Business Component Interface Message MDSETMsg
//     * mdseMsg
//     * @return boolean
//     */
//    private boolean isAvalUseMdseCd(NLCL1020CMsg bizMsg, MDSETMsg mdseMsg) {
//        S21DataSecurityProfile dsProfile = getUserProfileService().getDataSecurityProfileFor(NLCL1020Constant.BUSINESS_ID);
//        List<S21DataSecurityAttributeData> dsAttrList = dsProfile.getDataSecurityAttributeDataListFor("PCS");
//
//        String msgCd = NLXProdLineCheck.exec(mdseMsg, dsAttrList);
//        if (ZYPCommonFunc.hasValue(msgCd)) {
//            bizMsg.mdseCd_HD.setErrorInfo(1, msgCd);
//            EZDDebugOutput.println(1, "isAvalUseMdseCd--### Error ###--[NLXProdLineCheck]Abnormal results.", this);
//            return false;
//        }
//        return true;
//    }
    // QC#30004 Del End

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @return boolean
     */
    private boolean isSSAllocationCheck(NLCL1020CMsg bizMsg) {

        AVAL_INVTY_APP_IDTMsg tMsg = NLCL1020CommonLogic.findAvalInvtyAppIdInfo(bizMsg, bizMsg.stkStsCd_PH.getValue());

        if (tMsg == null) {
            bizMsg.stkStsCd_PH.setErrorInfo(1, NLCL1020Constant.NLCM0067E, new String[] {NLCL1020Constant.STOCK_STATUS });
            EZDDebugOutput.println(1, "isSSAllocationCheck--### Error ###--[Aval Invyt App Id]No Search Results.", this);

            return false;
        }
        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @return boolean
     */
    private boolean isAvailableQtyCheck(NLCL1020CMsg bizMsg) {

        bizMsg.invtyAvalQty_HO.clear();

        String mdseCd = bizMsg.mdseCd_HD.getValue();
        String stkStsCd = bizMsg.stkStsCd_PH.getValue();

        INVTYTMsg outInvtyMsg = NLCL1020CommonLogic.findInvtyInfo(bizMsg, mdseCd, stkStsCd);

        BigDecimal invtyAvalQty = BigDecimal.ZERO;

        if (outInvtyMsg == null) {
            bizMsg.mdseCd_HD.setErrorInfo(1, NLCL1020Constant.NLCM0021E);
            EZDDebugOutput.println(1, "isAvailableQtyCheck--### Error ###--[Invty]No Search Results.", this);

            return false;

        } else {

            if (ZYPCommonFunc.hasValue(outInvtyMsg.invtyAvalQty)) {

                invtyAvalQty = outInvtyMsg.invtyAvalQty.getValue();
                bizMsg.invtyAvalQty_HO.setValue(invtyAvalQty);
            }
        }

        if (BigDecimal.ZERO.compareTo(invtyAvalQty) > 0) {
            bizMsg.invtyAvalQty_HO.setErrorInfo(1, NLCL1020Constant.NLCM0032E);
            EZDDebugOutput.println(1, "isAvailableQtyCheck--### Error ###--[InvtyAvalQty]is Minus.", this);

            return false;
        }

        if (invtyAvalQty.compareTo(bizMsg.invtyAvalQty_HI.getValue()) < 0) {

            bizMsg.invtyAvalQty_HI.setErrorInfo(1, NLCL1020Constant.NLCM0016E, new String[] {NLCL1020Constant.QTY, NLCL1020Constant.CURRENT_AVAILABLE });
            EZDDebugOutput.println(1, "isAvailableQtyCheck--### Error ###--[InvtyAvalQty]Comparison.", this);

            return false;
        }
        return true;

    }

}
