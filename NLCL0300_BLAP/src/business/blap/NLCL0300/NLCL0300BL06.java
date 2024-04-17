/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLCL0300;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLCL0300.common.NLCL0300CommonLogic;
import business.db.INVTYTMsg;
import business.db.RTL_SWHTMsg;
import business.parts.NLZC003001PMsg;
import business.parts.NLZC200001PMsg;
import business.parts.NLZC205001PMsg;
import business.parts.NLZC304001PMsg;
import business.parts.NSZC048001PMsg;
import business.parts.NWZC003001PMsg;
import business.parts.NWZC107001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC003001.NLZC003001;
import com.canon.cusa.s21.api.NLZ.NLZC200001.NLZC200001;
import com.canon.cusa.s21.api.NLZ.NLZC205001.NLZC205001;
import com.canon.cusa.s21.api.NLZ.NLZC304001.NLZC304001;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.api.NWZ.NWZC107001.NWZC107001;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   CSAI            K.Lee           Create          N/A
 * 2017/02/07   CITS            Y.IWASAKI       Update          QC#17234
 * 2019/08/13   CITS            M.Naito         Update          QC#52185
 * 2019/08/13   CITS            M.Naito         Update          QC#51011
 * 2019/09/05   CITS            T.Wada          Update          QC#52528
 * 2019/11/26   CITS            T.Wada          Update          QC#54380
 * 2020/01/22   CITS            T.Wada          Update          QC#55539
 * 2021/02/15   CITS            A.Marte         Update          QC#59705
 * 2022/03/15   CITS            M.Furugoori     Update          QC#59822
 * 2023/05/19   Hitachi         TZ.Win          Update          QC#61203
 *</pre>
 */

public class NLCL0300BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NLCL0300Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLCL0300Scrn00_CMN_Submit(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NLCL0300Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(1, "----- doProcess_NLCL0300Scrn00_CMN_Submit START -----", null);
        NLCL0300CMsg bizMsg = (NLCL0300CMsg) cMsg;
        bizMsg.clearErrorInfo();

        boolean errFlg = false;
        // START 2019/08/13 M.Naito [QC#51011,ADD]
        String prntRtlSwhCd = null;
        // END 2019/08/13 M.Naito [QC#51011,ADD]

        if (bizMsg.A.getValidCount() == 0) {
            bizMsg.setMessageInfo("NLAM0002E");
            return;
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(0).rmvConfigFlg_A.getValue())) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).rmvConfigFlg_A.getValue())) {
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).svcConfigMstrPk_A)) {
                        bizMsg.A.no(i).rmvConfigFlg_A.setErrorInfo(1, "NLZM2471E");
                    } else {
                        bizMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "NLZM2471E");
                    }
                    errFlg = true;
                }
            }
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            //Order Quantity Check
            if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).ordQty_A)) {
                bizMsg.A.no(i).ordQty_A.setErrorInfo(1, "NFCM0504E", new String[] {"Order Qty"});
                errFlg = true;

            } else if (BigDecimal.ZERO.equals(bizMsg.A.no(i).ordQty_A.getValue())) {
                bizMsg.A.no(i).ordQty_A.setErrorInfo(1, "NLAM1334E", new String[] {"Order Qty"});
                errFlg = true;

            } else {

               if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).serNum_A) && !BigDecimal.ONE.equals(bizMsg.A.no(i).ordQty_A.getValue().abs())) {
                   bizMsg.A.no(i).ordQty_A.setErrorInfo(1, "NLAM1331E", new String[]{});
                   errFlg = true;
               } else if (BigDecimal.ZERO.compareTo(bizMsg.A.no(i).ordQty_A.getValue()) >= 0) {
                   bizMsg.A.no(i).ordQty_A.setErrorInfo(1, "NLZM2463E", new String[]{});
                   errFlg = true;
               }
            }

            //Item Check
            String mdseDescShortTxt = null;
            String shpgSerTakeFlg = null;
            String instlBaseCtrlFlg = null;

            if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).mdseCd_A)) {
            	bizMsg.A.no(i).mdseCd_A.setErrorInfo(1, "NFCM0504E", new String[] {"Item"});
                errFlg = true;
            } else {
                //Item Check
                Map<String, Object> mdseMap = NLCL0300CommonLogic.getMdseMap(getGlobalCompanyCode(), bizMsg.A.no(i).mdseCd_A.getValue());

                if (mdseMap == null) {
                    bizMsg.A.no(i).mdseCd_A.setErrorInfo(1, "NMZM0154E", new String[]{"Item"});
                    errFlg = true;
                } else {

                    mdseDescShortTxt = (String) mdseMap.get("MDSE_DESC_SHORT_TXT");
                    shpgSerTakeFlg = (String) mdseMap.get("SHPG_SER_TAKE_FLG");
                    instlBaseCtrlFlg = (String) mdseMap.get("INSTL_BASE_CTRL_FLG");

                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).mdseDescShortTxt_A, mdseDescShortTxt);

                    if (ZYPConstant.FLG_OFF_N.equals(shpgSerTakeFlg)) {
                        if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).serNum_A)) {
                            bizMsg.A.no(i).serNum_A.setErrorInfo(1, "NSAM0451E", new String[]{});
                            errFlg = true;
                        }
                    }
                }
            }

            RTL_SWHTMsg rtlSwhTMsg = NLCL0300CommonLogic.getRTL_SWHTMsg(getGlobalCompanyCode(), bizMsg.rtlWhCd_H.getValue(), bizMsg.A.no(i).rtlSwhCd_A.getValue());
            if (rtlSwhTMsg == null) {
                bizMsg.A.no(i).rtlSwhCd_A.setErrorInfo(1, "NMZM0154E", new String[]{"Sub Warehouse"});
                errFlg = true;
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).invtyLocCd_A, rtlSwhTMsg.invtyLocCd);
            }

            // START 2023/05/19 TZ.Win [QC#61203, ADD]
            Map<String, Object> rtlWhMap = NLCL0300CommonLogic.getRtlWhMap(getGlobalCompanyCode(), bizMsg.rtlWhCd_H.getValue(), bizMsg.rtlWhNm_H.getValue());
            String rtlWhCatgCd = null;
            if (rtlWhMap == null) {
                bizMsg.rtlWhCd_H.setErrorInfo(1, "NPAM1361E", new String[]{"Warehouse"});
                return;
            } else {
                rtlWhCatgCd = (String) rtlWhMap.get("RTL_WH_CATG_CD");
            }
            if (!RTL_WH_CATG.SHOWROOM.equals(rtlWhCatgCd)) {
            // END 2023/05/19 TZ.Win [QC#61203, ADD]
                // START 2019/08/14 M.Naito [QC#51011,ADD]
                if (!ZYPCommonFunc.hasValue(prntRtlSwhCd)) {
                    prntRtlSwhCd = bizMsg.A.no(i).rtlSwhCd_A.getValue();
                }
                // QC#55539 Add Start
                if (!isAllRemove(bizMsg)) {
                    if (!prntRtlSwhCd.equals(bizMsg.A.no(i).rtlSwhCd_A.getValue())) {
                        if(ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).rmvConfigFlg_A.getValue())) {
                            if (!swhCheck(bizMsg, prntRtlSwhCd)) {
                                bizMsg.A.no(i).rtlSwhCd_A.setErrorInfo(1, "NLCM0237E");
                                errFlg = true;
                            }
                        } else {
                            bizMsg.A.no(i).rtlSwhCd_A.setErrorInfo(1, "NLCM0237E");
                            errFlg = true;
                        }
                    }
                // END 2019/08/14 M.Naito [QC#51011,ADD]
                }
                // QC#55539 Add End
            // START 2023/05/19 TZ.Win [QC#61203, ADD]
            }
            // END 2023/05/19 TZ.Win [QC#61203, ADD]

            //When IB track able Item & Serial Entered, IB Check
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).serNum_A) && ZYPConstant.FLG_ON_Y.equals(instlBaseCtrlFlg)) {
                Map<String, Object> ibMap = NLCL0300CommonLogic.getIbMap(getGlobalCompanyCode(), bizMsg.A.no(i).mdseCd_A.getValue(), bizMsg.A.no(i).serNum_A.getValue());
                BigDecimal svcConfigMstrPk = null;
                String svcMachMstrStsCd = null;
                String svcMachMaintAvalFlg = null;
                String curLocNum = null;

                if (ibMap != null) {
                    svcConfigMstrPk = (BigDecimal) ibMap.get("SVC_CONFIG_MSTR_PK");
                    svcMachMstrStsCd = (String) ibMap.get("SVC_MACH_MSTR_STS_CD");
                    svcMachMaintAvalFlg = (String) ibMap.get("SVC_MACH_MAINT_AVAL_FLG");
                    curLocNum = (String) ibMap.get("CUR_LOC_NUM");

                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).svcConfigMstrPk_A, svcConfigMstrPk);
                }

                if (ibMap == null) {
                    bizMsg.A.no(i).serNum_A.setErrorInfo(1, "NLZM2293E", new String[]{});
                    errFlg = true;
                } else if (ZYPConstant.FLG_OFF_N.equals(svcMachMaintAvalFlg)) {
                    bizMsg.A.no(i).serNum_A.setErrorInfo(1, "NLZM2294E", new String[]{});
                    errFlg = true;
                } else if (!SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd) && !SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd) ) {
                    bizMsg.A.no(i).serNum_A.setErrorInfo(1, "NLZM2295E", new String[]{});
                    errFlg = true;
                } else if (!bizMsg.A.no(i).invtyLocCd_A.getValue().equals(curLocNum)) {
                    bizMsg.A.no(i).rtlSwhCd_A.setErrorInfo(1, "NLZM2296E", new String[]{});
                    errFlg = true;
                    }
            }

            // START 2022/02/15 A.Marte [QC#59705,ADD]
            // Single-Item Non-serial Check
            if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).serNum_A)) {
                // START 2022/03/15 [QC#59822,ADD]
                if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).svcConfigMstrPk_A)) {
                // END 2022/03/15 [QC#59822,ADD]
                    //getCount
                    boolean hasAvailableSingleItem = NLCL0300CommonLogic.hasAvailNonSerialSingleItem(getGlobalCompanyCode(), bizMsg.A.no(i).mdseCd_A.getValue(), bizMsg.A.no(i).invtyLocCd_A.getValue());

                    if (!hasAvailableSingleItem) {
                        bizMsg.A.no(i).mdseCd_A.setErrorInfo(1, "NLCM0243E", new String[]{bizMsg.A.no(i).mdseCd_A.getValue(), bizMsg.A.no(i).stkStsCd_A.getValue()});
                        errFlg = true;
                    }
                }
            }
            // END 2022/02/15 A.Marte [QC#59705,ADD]

            //Set Available Inventory
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).invtyLocCd_A) && ZYPCommonFunc.hasValue(bizMsg.A.no(i).stkStsCd_A)) {
                INVTYTMsg invtyTMsg = NLCL0300CommonLogic.getInvtyTMsg(getGlobalCompanyCode(), bizMsg.A.no(i).invtyLocCd_A.getValue(), bizMsg.A.no(i).mdseCd_A.getValue(), bizMsg.A.no(i).stkStsCd_A.getValue(), LOC_STS.DC_STOCK);
                if (invtyTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).invtyAvalQty_A, invtyTMsg.invtyAvalQty);
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).invtyAvalQty_A, BigDecimal.ZERO);
                }
                if (bizMsg.A.no(i).invtyAvalQty_A.getValue().compareTo(bizMsg.A.no(i).ordQty_A.getValue()) < 0) {
                    bizMsg.A.no(i).ordQty_A.setErrorInfo(1, "NLZM2466E", new String[]{});
                    errFlg = true;
                }
            }

            //Set Item Cost
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).invtyLocCd_A)) {
                // NLXC0010 get Inventory Item Cost
                NLXC001001GetInventoryItemCostBean bean = new NLXC001001GetInventoryItemCostBean();
                bean.setGlblCmpyCd(getGlobalCompanyCode());
                bean.setInvtyLocCd(bizMsg.A.no(i).invtyLocCd_A.getValue());
                bean.setMdseCd(bizMsg.A.no(i).mdseCd_A.getValue());
                bean.setQty(bizMsg.A.no(i).ordQty_A.getValue());
                NLXC001001GetInventoryItemCostBean retNLXC001001GetInventoryItemCostBean = NLXC001001GetInventoryItemCost.getInventoryItemCost(bean);
                BigDecimal totPrcAmt = retNLXC001001GetInventoryItemCostBean.getTotPrcAmt();
                BigDecimal unitPrcAmt = retNLXC001001GetInventoryItemCostBean.getUnitPrcAmt();
                if (totPrcAmt != null) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).invtyOrdLineCostAmt_A, totPrcAmt);
                }
                if (unitPrcAmt != null) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).unitPrcAmt_A, unitPrcAmt);
                }
            }
        }

        if (errFlg) {
            return;
        }

        // START 2019/08/13 M.Naito [QC#52185,ADD]
        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg.getValue())) {
            if (ZYPCommonFunc.hasValue(bizMsg.svcConfigMstrPk_H) && NLCL0300CommonLogic.isSpecifiedOrder(getGlobalCompanyCode(), bizMsg.svcConfigMstrPk_H.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                bizMsg.setMessageInfo("NLCM0236W");
                return;
            }
        } else {
            bizMsg.xxWrnSkipFlg.clear();
        }
        // END 2019/08/13 M.Naito [QC#52185,ADD]

        // QC#52528 Mod Start
        int mainMachLineNum = 0;
        if (!isAllRemove(bizMsg)) {
            // QC#54380 Mod Start
        	NLCL0300CMsg errMsg = new NLCL0300CMsg();
            for (int c = 0; c < bizMsg.A.getValidCount(); c++) {
                mainMachLineNum = c;
//                NSZC048001PMsg svcMdlApiPMsg = NLCL0300CommonLogic.callSvcMdlApi(getGlobalCompanyCode(), bizMsg);
                NSZC048001PMsg svcMdlApiPMsg = NLCL0300CommonLogic.callSvcMdlApi(getGlobalCompanyCode(), bizMsg, mainMachLineNum);

                if (S21ApiUtil.isXxMsgId(svcMdlApiPMsg)) {
                    for (int i = 0; i < svcMdlApiPMsg.xxMsgIdList.getValidCount(); i++) {
                        if (!ZYPCommonFunc.hasValue(errMsg.getMessageCode())) {

                            S21ApiMessage msg = S21ApiUtil.getXxMsgList(svcMdlApiPMsg).get(i);
                            String msgId = msg.getXxMsgid();
                            String[] params = msg.getXxMsgPrmArray();
//                          bizMsg.setMessageInfo(msgId, params);
                            errMsg.setMessageInfo(msgId, params);
                            if (msgId.endsWith("E")) {
                                errFlg = true;
                            }
                        }
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.mdlId_H, svcMdlApiPMsg.mdlId);
                    ZYPEZDItemValueSetter.setValue(bizMsg.mdlId_HL, svcMdlApiPMsg.mdlId);
                    errFlg = false;
                    break;
                }
            }
            if (errFlg) {
                EZDMessageInfo errMsgInfo = errMsg.getMessageInfo();
                bizMsg.setMessageInfo(errMsgInfo.getCode(), errMsgInfo.getParameter());
                return;
            }
            
        }
        // QC#52528 Mod End

        // QC#54380 Add Start
        if (mainMachLineNum != 0) {
            // bizMessage Change registration sequence
            NLCL0300CMsg bizMsgTmp = chgRegSeq(bizMsg, mainMachLineNum);
            EZDMsg.copy((NLCL0300CMsg)bizMsgTmp, null, (NLCL0300CMsg)bizMsg, null);

        }
        // QC#54380 Add End
        
        if (!ZYPCommonFunc.hasValue(bizMsg.svcConfigMstrPk_HL)) {
            bizMsg.svcConfigMstrPk_HL.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONFIG_MSTR_SQ));
        }

        //Create Configuration Change Order
        // Call Inventory Order API (Header)
        NLZC003001PMsg invtyOrdApiHdrPMsg = NLCL0300CommonLogic.getInventoryOrderApiPMsgHeader(getGlobalCompanyCode(), bizMsg);
        NLZC003001 nlzc003001 = new NLZC003001();
        nlzc003001.execute(invtyOrdApiHdrPMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(invtyOrdApiHdrPMsg)) {
            for (int i = 0; i < invtyOrdApiHdrPMsg.xxMsgIdList.getValidCount(); i++) {
                S21ApiMessage msg = S21ApiUtil.getXxMsgList(invtyOrdApiHdrPMsg).get(i);
                String msgId = msg.getXxMsgid();
                String[] params = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, params);
                if (msgId.endsWith("E")) {
                    errFlg = true;
                    return;
                }
            }
        }


        // Call Inventory Order API (Detail)
        List<NLZC003001PMsg> invtyOrdApiPMsgList = NLCL0300CommonLogic.getInventoryOrderApiPMsgDetailList(getGlobalCompanyCode(), bizMsg, invtyOrdApiHdrPMsg);
        nlzc003001.execute(invtyOrdApiPMsgList, ONBATCH_TYPE.ONLINE);
        int invtyOrdIdx = 0;
        for (NLZC003001PMsg invtyOrdApiPMsg : invtyOrdApiPMsgList) {
            if (S21ApiUtil.isXxMsgId(invtyOrdApiPMsg)) {
                for (int i = 0; i < invtyOrdApiPMsg.xxMsgIdList.getValidCount(); i++) {
                    S21ApiMessage msg = S21ApiUtil.getXxMsgList(invtyOrdApiPMsg).get(i);
                    String msgId = msg.getXxMsgid();
                    String[] params = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, params);
                    if (msgId.endsWith("E")) {
                        errFlg = true;
                        return;
                    }
                }
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(invtyOrdIdx).invtyOrdNum_T, invtyOrdApiPMsg.invtyOrdNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(invtyOrdIdx).invtyOrdLineNum_T, invtyOrdApiPMsg.invtyOrdLineNum);
            invtyOrdIdx++;
        }

        // Call Allocation for Non CPO API 
        List<NWZC107001PMsg> allocNonCpoApiPMsgList = NLCL0300CommonLogic.getAllocNonCpoApiPMsgList(getGlobalCompanyCode(), bizMsg);
        NWZC107001 nwzc107001 = new NWZC107001();
        nwzc107001.execute(allocNonCpoApiPMsgList, ONBATCH_TYPE.ONLINE);
        for (NWZC107001PMsg allocNonCpoApiPMsg : allocNonCpoApiPMsgList) {
            if (S21ApiUtil.isXxMsgId(allocNonCpoApiPMsg)) {
                for (int i = 0; i < allocNonCpoApiPMsg.xxMsgIdList.getValidCount(); i++) {
                    S21ApiMessage msg = S21ApiUtil.getXxMsgList(allocNonCpoApiPMsg).get(i);
                    String msgId = msg.getXxMsgid();
                    String[] params = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, params);
                    if (msgId.endsWith("E")) {
                        errFlg = true;
                        return;
                    }
                }
            }
        }

        // Call Shipping Plan Update API 
        List<NWZC003001PMsg> shippingPlanUpdateApiPMsgList = NLCL0300CommonLogic.getShippingPlanUpdateApiPMsgList(getGlobalCompanyCode(), bizMsg);
        NWZC003001 nwzc003001 = new NWZC003001();
        nwzc003001.execute(shippingPlanUpdateApiPMsgList, ONBATCH_TYPE.ONLINE);
        for (NWZC003001PMsg shippingPlanUpdateApiPMsg : shippingPlanUpdateApiPMsgList) {
            if (S21ApiUtil.isXxMsgId(shippingPlanUpdateApiPMsg)) {
                for (int i = 0; i < shippingPlanUpdateApiPMsg.xxMsgIdList.getValidCount(); i++) {
                    S21ApiMessage msg = S21ApiUtil.getXxMsgList(shippingPlanUpdateApiPMsg).get(i);
                    String msgId = msg.getXxMsgid();
                    String[] params = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, params);
                    if (msgId.endsWith("E")) {
                        errFlg = true;
                        return;
                    }
                }
            }
        }

        // Call SO Update API 
        List<NLZC205001PMsg> soApiPMsgList = NLCL0300CommonLogic.getSoApiPMsgList(getGlobalCompanyCode(), bizMsg);
        NLZC205001 nlzc205001 = new NLZC205001();
        nlzc205001.execute(soApiPMsgList, ONBATCH_TYPE.ONLINE);
        for (NLZC205001PMsg soApiPMsg : soApiPMsgList) {
            if (S21ApiUtil.isXxMsgId(soApiPMsg)) {
                for (int i = 0; i < soApiPMsg.xxMsgIdList.getValidCount(); i++) {
                    S21ApiMessage msg = S21ApiUtil.getXxMsgList(soApiPMsg).get(i);
                    String msgId = msg.getXxMsgid();
                    String[] params = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, params);
                    if (msgId.endsWith("E")) {
                        errFlg = true;
                        return;
                    }
                }
            }

        }

        // Call RWS Update API 
        List<NLZC200001PMsg> rwsApiPMsgList = NLCL0300CommonLogic.getRwsApiPMsgList(soApiPMsgList);
        NLZC200001 nlzc200001 = new NLZC200001();
        nlzc200001.execute(rwsApiPMsgList, ONBATCH_TYPE.ONLINE);
        for (NLZC200001PMsg rwsApiPMsg : rwsApiPMsgList) {
            if (S21ApiUtil.isXxMsgId(rwsApiPMsg)) {
                for (int i = 0; i < rwsApiPMsg.xxMsgIdList.getValidCount(); i++) {
                    S21ApiMessage msg = S21ApiUtil.getXxMsgList(rwsApiPMsg).get(0);
                    String msgId = msg.getXxMsgid();
                    String[] params = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, params);
                    if (msgId.endsWith("E")) {
                        errFlg = true;
                        return;
                    }
                }
            }
        }

        // Call RWS Serial Update API 
        List<NLZC304001PMsg> rwsSerApiPMsgList = NLCL0300CommonLogic.getRwsSerApiPMsg(getGlobalCompanyCode(), bizMsg, rwsApiPMsgList);
        if (rwsSerApiPMsgList.size() > 0) {
            NLZC304001 nlzc304001 = new NLZC304001();
            nlzc304001.execute(rwsSerApiPMsgList, ONBATCH_TYPE.ONLINE);
            for (NLZC304001PMsg rwsSerApiPMsg : rwsSerApiPMsgList) {
                if (S21ApiUtil.isXxMsgId(rwsSerApiPMsg)) {
                    for (int i = 0; i < rwsSerApiPMsg.xxMsgIdList.getValidCount(); i++) {
                        S21ApiMessage msg = S21ApiUtil.getXxMsgList(rwsSerApiPMsg).get(0);
                        String msgId = msg.getXxMsgid();
                        String[] params = msg.getXxMsgPrmArray();
                        bizMsg.setMessageInfo(msgId, params);
                        if (msgId.endsWith("E")) {
                            errFlg = true;
                            return;
                        }
                    }
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.invtyOrdNum_H, invtyOrdApiHdrPMsg.invtyOrdNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.svcConfigMstrPk_H, bizMsg.svcConfigMstrPk_HL);

        EZDDebugOutput.println(1, "----- doProcess_NLCL0300Scrn00_CMN_Submit END -----", null);
    }
    // QC#52528 Add Start
    /**
     * checkRemoveAll
     * @param bizMsg
     * @return
     */
    private boolean isAllRemove(NLCL0300CMsg bizMsg) {
        int rmvChkCnt = 0;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).rmvConfigFlg_A.getValue())) {
                rmvChkCnt++;
                continue;
            }
        }
        if (bizMsg.A.getValidCount() == rmvChkCnt) {
            return true;
        } else {
            return false;
        }
    }
    // QC#52528 Add End

    // QC#54380 Add Start
    /**
     * chgRegSeq
     */
    private NLCL0300CMsg chgRegSeq(NLCL0300CMsg bizMsg, int mainMachLineNum) {

        NLCL0300CMsg bizMsgTmp = new NLCL0300CMsg();
        EZDMsg.copy((NLCL0300CMsg)bizMsg, null, (NLCL0300CMsg)bizMsgTmp, null);

        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).xxChkBox_A, bizMsg.A.no(mainMachLineNum).xxChkBox_A);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).invtyOrdNum_T, bizMsg.A.no(mainMachLineNum).invtyOrdNum_T);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).invtyOrdLineNum_T, bizMsg.A.no(mainMachLineNum).invtyOrdLineNum_T);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).invtyOrdNum_A, bizMsg.A.no(mainMachLineNum).invtyOrdNum_A);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).invtyOrdLineNum_A, bizMsg.A.no(mainMachLineNum).invtyOrdLineNum_A);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).svcConfigMstrPk_A, bizMsg.A.no(mainMachLineNum).svcConfigMstrPk_A);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).mdseCd_A, bizMsg.A.no(mainMachLineNum).mdseCd_A);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).mdseDescShortTxt_A, bizMsg.A.no(mainMachLineNum).mdseDescShortTxt_A);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).stkStsCd_A, bizMsg.A.no(mainMachLineNum).stkStsCd_A);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).rtlSwhCd_A, bizMsg.A.no(mainMachLineNum).rtlSwhCd_A);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).ordQty_A, bizMsg.A.no(mainMachLineNum).ordQty_A);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).invtyAvalQty_A, bizMsg.A.no(mainMachLineNum).invtyAvalQty_A);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).serNum_A, bizMsg.A.no(mainMachLineNum).serNum_A);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).rmvConfigFlg_A, bizMsg.A.no(mainMachLineNum).rmvConfigFlg_A);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).invtyLocCd_A, bizMsg.A.no(mainMachLineNum).invtyLocCd_A);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).invtyOrdLineCostAmt_A, bizMsg.A.no(mainMachLineNum).invtyOrdLineCostAmt_A);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).unitPrcAmt_A, bizMsg.A.no(mainMachLineNum).unitPrcAmt_A);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).rtlWhCd_AH, bizMsg.A.no(mainMachLineNum).rtlWhCd_AH);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).rtlWhNm_AH, bizMsg.A.no(mainMachLineNum).rtlWhNm_AH);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).svcConfigMstrPk_AH, bizMsg.A.no(mainMachLineNum).svcConfigMstrPk_AH);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).mdlId_AH, bizMsg.A.no(mainMachLineNum).mdlId_AH);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).mdlDescTxt_AH, bizMsg.A.no(mainMachLineNum).mdlDescTxt_AH);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).firstInvtyOrdCmntTxt_AH, bizMsg.A.no(mainMachLineNum).firstInvtyOrdCmntTxt_AH);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).scdInvtyOrdCmntTxt_AH, bizMsg.A.no(mainMachLineNum).scdInvtyOrdCmntTxt_AH);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).thirdInvtyOrdCmntTxt_AH, bizMsg.A.no(mainMachLineNum).thirdInvtyOrdCmntTxt_AH);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).invtyOrdStsDescTxt_AH, bizMsg.A.no(mainMachLineNum).invtyOrdStsDescTxt_AH);
        ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(0).soNum_AH, bizMsg.A.no(mainMachLineNum).soNum_AH);

        int cc = 1;
        for (int c = 0; c < bizMsg.A.getValidCount(); c++) {
            if (c == mainMachLineNum) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).xxChkBox_A, bizMsg.A.no(c).xxChkBox_A);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).invtyOrdNum_T, bizMsg.A.no(c).invtyOrdNum_T);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).invtyOrdLineNum_T, bizMsg.A.no(c).invtyOrdLineNum_T);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).invtyOrdNum_A, bizMsg.A.no(c).invtyOrdNum_A);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).invtyOrdLineNum_A, bizMsg.A.no(c).invtyOrdLineNum_A);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).svcConfigMstrPk_A, bizMsg.A.no(c).svcConfigMstrPk_A);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).mdseCd_A, bizMsg.A.no(c).mdseCd_A);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).mdseDescShortTxt_A, bizMsg.A.no(c).mdseDescShortTxt_A);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).stkStsCd_A, bizMsg.A.no(c).stkStsCd_A);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).rtlSwhCd_A, bizMsg.A.no(c).rtlSwhCd_A);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).ordQty_A, bizMsg.A.no(c).ordQty_A);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).invtyAvalQty_A, bizMsg.A.no(c).invtyAvalQty_A);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).serNum_A, bizMsg.A.no(c).serNum_A);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).rmvConfigFlg_A, bizMsg.A.no(c).rmvConfigFlg_A);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).invtyLocCd_A, bizMsg.A.no(c).invtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).invtyOrdLineCostAmt_A, bizMsg.A.no(c).invtyOrdLineCostAmt_A);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).unitPrcAmt_A, bizMsg.A.no(c).unitPrcAmt_A);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).rtlWhCd_AH, bizMsg.A.no(c).rtlWhCd_AH);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).rtlWhNm_AH, bizMsg.A.no(c).rtlWhNm_AH);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).svcConfigMstrPk_AH, bizMsg.A.no(c).svcConfigMstrPk_AH);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).mdlId_AH, bizMsg.A.no(c).mdlId_AH);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).mdlDescTxt_AH, bizMsg.A.no(c).mdlDescTxt_AH);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).firstInvtyOrdCmntTxt_AH, bizMsg.A.no(c).firstInvtyOrdCmntTxt_AH);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).scdInvtyOrdCmntTxt_AH, bizMsg.A.no(c).scdInvtyOrdCmntTxt_AH);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).thirdInvtyOrdCmntTxt_AH, bizMsg.A.no(c).thirdInvtyOrdCmntTxt_AH);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).invtyOrdStsDescTxt_AH, bizMsg.A.no(c).invtyOrdStsDescTxt_AH);
            ZYPEZDItemValueSetter.setValue(bizMsgTmp.A.no(cc).soNum_AH, bizMsg.A.no(c).soNum_AH);
            cc++;
    	}

    	
    	return bizMsgTmp;
    }
    // QC#54380 Add End

    // QC#55539 Add Start
    /**
     * swhCheck
     * @param bizMsg
     * @return
     */
    private boolean swhCheck(NLCL0300CMsg bizMsg, String prntRtlSwhCd) {
    	String curSwhCd = null;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).rmvConfigFlg_A.getValue())) {
                curSwhCd = bizMsg.A.no(i).rtlSwhCd_A.getValue();
                if (!curSwhCd.equals(prntRtlSwhCd)) {
                    return false;
                }
            }
        }
        return true;
    }
    // QC#55539 Add End
}
