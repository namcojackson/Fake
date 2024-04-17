/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 * 2017/06/20   Fujitsu         T.Kikuhara      Create          QC#19267
 * 2019/09/03   CITS            K.Ogino         Update          QC#53009
 * 2021/10/19   CITS            R.Azucena       Update          QC#58899
 *</pre>
 */
package business.blap.NLCL0290;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLCL0290.common.NLCL0290CommonLogic;
import business.blap.NLCL0290.constant.NLCL0290Constant;
import business.db.ADJ_CATGTMsg;
import business.db.COA_ACCTTMsg;
import business.db.INVTYTMsg;
import business.db.RTL_SWHTMsg;
import business.db.RWS_HDRTMsg;
import business.parts.NLZC003001PMsg;
import business.parts.NLZC004001PMsg;
import business.parts.NLZC200001PMsg;
import business.parts.NLZC205001PMsg;
import business.parts.NLZC206001PMsg;
import business.parts.NLZC207001PMsg;
import business.parts.NLZC302001PMsg;
import business.parts.NLZC304001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC003001PMsg;
import business.parts.NWZC107001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC003001.NLZC003001;
import com.canon.cusa.s21.api.NLZ.NLZC004001.NLZC004001;
import com.canon.cusa.s21.api.NLZ.NLZC060001.constant.NLZC060001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC107001.NWZC107001;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADJ_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   CSAI            K.Lee           Create          N/A
 * 2016/11/02   CITS            K.Kameoka       Update          QC#15558
 * 2017/01/27   CITS            R.Shimamoto     Update          QC#17190
 * 2017/02/09   CITS            M.Naito         Update          QC#17488
 * 2017/09/25   CITS            K.Ogino         Update          QC#21291
 * 2018/03/02   CITS            S.Katsuma       Update          QC#20688
 * 2018/04/06   CITS            K.Masaki        Update          QC#18472/18490
 * 06/05/2018   CITS            K.Ogino         Update          QC#26383
 * 07/05/2018   CITS            S.Katsuma       Update          QC#25991
 * 11/30/2018   CITS            T.Hakodate      Update          QC#29172
 * 12/18/2018   CITS            M.Naito         Update          QC#28769
 * 04/19/2019   CITS            K.Ogino         Update          QC#31148
 * 05/09/2019   CITS            T.Tokutomi      Update          QC#50008
 * 05/09/2019   CITS            T.Tokutomi      Update          QC#50439
 * 06/03/2019   CITS            M.Naito         Update          QC#50408
 * 06/10/2019   CITS            M.Naito         Update          QC#50751
 * 08/22/2019   Fujitsu         T.Ogura         Update          QC#52583
 * 2020/07/10   CITS            K.Ogino         Update          QC#57258
 * 2022/10/21   Hitachi         N.Takatsu       Update          QC#60603
 * 2022/12/12   Hitachi         E.Watabe        Update          QC#60829
 *</pre>
 */
public class NLCL0290BL06 extends S21BusinessHandler implements NLCL0290Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NLCL0290Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLCL0290Scrn00_CMN_Submit(cMsg, sMsg, getGlobalCompanyCode());
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NLCL0290Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg, String glblCmpyCd) {

        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_CMN_Submit START -----", null);
        //
        NLCL0290CMsg bizMsg = (NLCL0290CMsg) cMsg;
        NLCL0290SMsg globalMsg = (NLCL0290SMsg) sMsg;

        NLCL0290CommonLogic.copyPage(bizMsg, bizMsg.A, globalMsg.A);
        globalMsg.clearErrorInfo();

        int firstErrNum = -1;
        boolean errFlg = false;

        // QC:14488
        // if (ZYPCommonFunc.hasValue(bizMsg.xxScrItem130Txt_H)) {
        // boolean check =
        // NLCL0290CommonLogic.validateSegmentStringForHeader(glblCmpyCd,
        // bizMsg, globalMsg, bizMsg.xxScrItem130Txt_H.getValue(),
        // false);
        // if (!check) {
        // errFlg = true;
        // }
        // }

        if (globalMsg.A.getValidCount() == 0) {
            bizMsg.setMessageInfo("NLAM0002E");
        }

        String configFlg = null;

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            // QC:18490 Start
            if (ZYPConstant.FLG_OFF_0.equals(globalMsg.A.no(i).xxConfigFlg_A.getValue())) {
                // Config Header
                configFlg = ZYPConstant.FLG_ON_Y;
            } else {
                if (!ZYPCommonFunc.hasValue(bizMsg.svcConfigMstrPk)) {
                    // Not Config
                    configFlg = ZYPConstant.FLG_OFF_N;
                } else {
                    // Config Detail
                    configFlg = ZYPConstant.FLG_OFF_0;
                }
            }

            if (ZYPConstant.FLG_ON_Y.equals(configFlg)) {
                // Config Header
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).toInvtyLocCd_A, bizMsg.rtlWhCd_H.getValue() + globalMsg.A.no(0).toRtlSwhCd_A.getValue());
            } else {
                // Not Config
                if (ZYPConstant.FLG_OFF_N.equals(configFlg)) {
                    if (ADJ_TRX_TP.ADJUSTMENT.equals(bizMsg.adjTrxTpCd_H.getValue())) {
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).toInvtyLocCd_A, bizMsg.rtlWhCd_H.getValue() + globalMsg.A.no(i).toRtlSwhCd_A.getValue());
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).fromInvtyLocCd_A, globalMsg.A.no(i).toInvtyLocCd_A);
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).fromRtlSwhCd_A, globalMsg.A.no(i).toRtlSwhCd_A);
                    } else if (ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(bizMsg.adjTrxTpCd_H.getValue())) {
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).fromInvtyLocCd_A, bizMsg.rtlWhCd_H.getValue() + globalMsg.A.no(i).fromRtlSwhCd_A.getValue());
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).toInvtyLocCd_A, bizMsg.rtlWhCd_H.getValue() + globalMsg.A.no(i).toRtlSwhCd_A.getValue());
                    }
                }
                // Copy from config header to detail.
                if(ZYPConstant.FLG_OFF_0.equals(configFlg)) {
                    if (!errFlg) {
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).toInvtyLocCd_A, globalMsg.A.no(0).toInvtyLocCd_A);
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).toRtlSwhCd_A, globalMsg.A.no(0).toRtlSwhCd_A);
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).adjCatgCd_A, globalMsg.A.no(0).adjCatgCd_A);
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxScrItem130Txt_A, globalMsg.A.no(0).xxScrItem130Txt_A);
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invtyOrdLineCmntTxt_A, globalMsg.A.no(0).invtyOrdLineCmntTxt_A);
                    }
                }
            }
            // Set Available Inventory
            if (ZYPConstant.FLG_OFF_N.equals(configFlg) || ZYPConstant.FLG_OFF_0.equals(configFlg)) {
                INVTYTMsg invtyTMsg = NLCL0290CommonLogic.getInvtyTMsg(getGlobalCompanyCode(), globalMsg.A.no(i).fromInvtyLocCd_A.getValue(), globalMsg.A.no(i).mdseCd_A.getValue(), globalMsg.A.no(i).stkStsCd_A.getValue(), globalMsg.A.no(i).locStsCd_AH.getValue());
                if (invtyTMsg != null) {
                    // QC#53009
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invtyAvalQty_A, invtyTMsg.invtyAvalQty);
                } else {
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invtyAvalQty_A, BigDecimal.ZERO);
                }
            }
            // QC:18490 End

            // QC:51155 Start
            for (int d = i+1; d < globalMsg.A.getValidCount(); d++) {

            	if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).svcConfigMstrPk_A)) {

            		continue;
                }

            	if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).serNum_A)) {
            		// Serialized
                    // START 2019/08/22 T.Ogura [QC#52583,MOD]
//            		if (ZYPCommonFunc.hasValue(globalMsg.A.no(d).serNum_A) && globalMsg.A.no(i).serNum_A.getValue().equals(globalMsg.A.no(d).serNum_A.getValue()) ){
//            			globalMsg.A.no(i).serNum_A.setErrorInfo(1, "NMAM0072E", new String[] {"Serial" });
//            			globalMsg.A.no(d).serNum_A.setErrorInfo(1, "NMAM0072E", new String[] {"Serial" });
//            			errFlg = true;
//            		}
                    if (ZYPCommonFunc.hasValue(globalMsg.A.no(d).serNum_A) && globalMsg.A.no(i).serNum_A.getValue().equals(globalMsg.A.no(d).serNum_A.getValue())){
                        if (globalMsg.A.no(i).mdseCd_A.getValue().equals(globalMsg.A.no(d).mdseCd_A.getValue())) {
                            globalMsg.A.no(i).mdseCd_A.setErrorInfo(1, "NMAM0072E", new String[] {"Item Number & Serial" });
                            globalMsg.A.no(i).serNum_A.setErrorInfo(1, "NMAM0072E", new String[] {"Item Number & Serial" });
                            globalMsg.A.no(d).mdseCd_A.setErrorInfo(1, "NMAM0072E", new String[] {"Item Number & Serial" });
                            globalMsg.A.no(d).serNum_A.setErrorInfo(1, "NMAM0072E", new String[] {"Item Number & Serial" });
                            errFlg = true;
                        }
                    }
                    // END   2019/08/22 T.Ogura [QC#52583,MOD]
                // START 2021/10/19 R.Azucena[QC#58899, DEL]
                // } else {
                    // Non Serialized
                //     if ( globalMsg.A.no(i).mdseCd_A.getValue().equals(globalMsg.A.no(d).mdseCd_A.getValue())
                //             && globalMsg.A.no(i).toRtlSwhCd_A.getValue().equals(globalMsg.A.no(d).toRtlSwhCd_A.getValue())
                //             && globalMsg.A.no(i).stkStsCd_A.getValue().equals(globalMsg.A.no(d).stkStsCd_A.getValue()) ) {
                //         globalMsg.A.no(i).mdseCd_A.setErrorInfo(1, "NMAM0072E", new String[] {"Item Number & Destination Sub WH & Stock Status" });
                //         globalMsg.A.no(i).toRtlSwhCd_A.setErrorInfo(1, "NMAM0072E", new String[] {"Item Number & Destination Sub WH & Stock Status" });
                //         globalMsg.A.no(i).stkStsCd_A.setErrorInfo(1, "NMAM0072E", new String[] {"Item Number & Destination Sub WH & Stock Status" });
                //         globalMsg.A.no(d).mdseCd_A.setErrorInfo(1, "NMAM0072E", new String[] {"Item Number & Destination Sub WH & Stock Status" });
                //         globalMsg.A.no(d).toRtlSwhCd_A.setErrorInfo(1, "NMAM0072E", new String[] {"Item Number & Destination Sub WH & Stock Status" });
                //         globalMsg.A.no(d).stkStsCd_A.setErrorInfo(1, "NMAM0072E", new String[] {"Item Number & Destination Sub WH & Stock Status" });
                //         errFlg = true;
                //     }
                // END 2021/10/19 R.Azucena[QC#58899, DEL]
                }

            }
            // QC:51155 End

            // QC:18490
            if (ZYPConstant.FLG_OFF_N.equals(configFlg)) {
                // Order Quantity Check
                if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).ordQty_A)) {
                    globalMsg.A.no(i).ordQty_A.setErrorInfo(1, "NFCM0504E", new String[] {"Order Qty" });
                    errFlg = true;

                } else if (BigDecimal.ZERO.equals(globalMsg.A.no(i).ordQty_A.getValue())) {
                    globalMsg.A.no(i).ordQty_A.setErrorInfo(1, "NLAM1334E", new String[] {"Order Qty" });
                    errFlg = true;

                } else {

                    if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).serNum_A) && !BigDecimal.ONE.equals(globalMsg.A.no(i).ordQty_A.getValue().abs())) {
                        globalMsg.A.no(i).ordQty_A.setErrorInfo(1, "NLAM1331E", new String[] {});
                        errFlg = true;
                    }

                    // QC:18472. Mod QC#53009
                    if (ADJ_TRX_TP.ADJUSTMENT.equals(bizMsg.adjTrxTpCd_H.getValue())) {
                        if (ZYPConstant.FLG_OFF_N.equals(configFlg) || ZYPConstant.FLG_OFF_0.equals(configFlg)) {
                            INVTYTMsg invtyTMsg = NLCL0290CommonLogic.getInvtyTMsg(getGlobalCompanyCode(), globalMsg.A.no(i).fromInvtyLocCd_A.getValue(), globalMsg.A.no(i).mdseCd_A.getValue(), globalMsg.A.no(i).stkStsCd_A.getValue(),globalMsg.A.no(i).locStsCd_AH.getValue());
                            BigDecimal invQty = BigDecimal.ZERO;
                            if (invtyTMsg != null) {
                                invQty = invtyTMsg.invtyQty.getValue();
                            }

                            if (invQty.intValue() + globalMsg.A.no(i).ordQty_A.getValueInt() < 0) {
                                globalMsg.A.no(i).ordQty_A.setErrorInfo(1, "NLZM2521E");
                                errFlg = true;
                            }
                        }
                    }

                    if (ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(bizMsg.adjTrxTpCd_H.getValue())
                        && globalMsg.A.no(i).ordQty_A.getValueInt() < 0) {
                        globalMsg.A.no(i).ordQty_A.setErrorInfo(1, "NLCM0221E");
                        errFlg = true;
                    }

//                    if (ZYPConstant.FLG_ON_Y.equals(bizMsg.destSwhReqFlg_H.getValue())) {
//                        if (BigDecimal.ZERO.compareTo(globalMsg.A.no(i).ordQty_A.getValue()) > 0) {
//                            globalMsg.A.no(i).ordQty_A.setErrorInfo(1, "NLAM1332E", new String[] {ZYPCodeDataUtil.getName(ADJ_TRX_TP.class, getGlobalCompanyCode(), bizMsg.adjTrxTpCd_H.getValue()) });
//                            errFlg = true;
//                        }
//                    } else {
//                        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.adjQtyIncrFlg_H.getValue()) && BigDecimal.ZERO.compareTo(globalMsg.A.no(i).ordQty_A.getValue()) < 0) {
//                            globalMsg.A.no(i).ordQty_A.setErrorInfo(1, "NLAM1332E", new String[] {ZYPCodeDataUtil.getName(ADJ_TRX_TP.class, getGlobalCompanyCode(), bizMsg.adjTrxTpCd_H.getValue()) });
//                            errFlg = true;
//                        }
    //
//                        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.adjQtyDecrFlg_H.getValue()) && BigDecimal.ZERO.compareTo(globalMsg.A.no(i).ordQty_A.getValue()) > 0) {
//                            globalMsg.A.no(i).ordQty_A.setErrorInfo(1, "NLAM1333E", new String[] {ZYPCodeDataUtil.getName(ADJ_TRX_TP.class, getGlobalCompanyCode(), bizMsg.adjTrxTpCd_H.getValue()) });
//                            errFlg = true;
//                        }
//                    }
                }
            }

            // Account Check
            // QC:18472 Start
            if (ZYPConstant.FLG_OFF_N.equals(configFlg) || (ZYPConstant.FLG_ON_Y.equals(configFlg))) {
                if (ADJ_CATG.SPECIAL_ACCOUNT.equals(globalMsg.A.no(i).adjCatgCd_A.getValue())) {
                    if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxScrItem130Txt_A)) {
                        globalMsg.A.no(i).xxScrItem130Txt_A.setErrorInfo(1, "NFCM0504E", new String[] {"Account" });
                        errFlg = true;
                    }

                    COA_ACCTTMsg tMsg = new COA_ACCTTMsg();

                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.coaAcctCd, globalMsg.A.no(i).xxScrItem130Txt_A.getValue());

                    COA_ACCTTMsg existTMsg = (COA_ACCTTMsg) EZDTBLAccessor.findByKey(tMsg);

                    if (existTMsg == null) {
                        globalMsg.A.no(i).xxScrItem130Txt_A.setErrorInfo(1, "NLCM0197E", new String[] {"Account" });
                        errFlg = true;
                    }

                    if (!NLCL0290CommonLogic.checkAllowAccount(glblCmpyCd, bizMsg.adjTrxTpCd_H.getValue(), globalMsg.A.no(i).xxScrItem130Txt_A.getValue())) {
                        globalMsg.A.no(i).xxScrItem130Txt_A.setErrorInfo(1, "NLCM0214E");
                    }
                }
            }

//            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.acctReqFlg_H.getValue()) && !ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxScrItem130Txt_A)) {
//                globalMsg.A.no(i).xxScrItem130Txt_A.setErrorInfo(1, "NFCM0504E", new String[] {"Account" });
//                errFlg = true;
//            }
//
//            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxScrItem130Txt_A)) {
//                bizMsg.xxNum.setValue(i);
//                boolean check = NLCL0290CommonLogic.validateSegmentStringForDetail(glblCmpyCd, bizMsg, globalMsg, true, false);
//                if (!check) {
//                    errFlg = true;
//                }
//            }
            // QC:18472 End

            // Item Check
            // QC:18490
            String mdseDescShortTxt = null;
            String shpgSerTakeFlg = null;
            String instlBaseCtrlFlg = null;
            if (ZYPConstant.FLG_OFF_N.equals(configFlg)) {
                // START 2018/03/02 S.Katsuma [QC#20866,ADD]
                String invtyCtrlFlg = null;
                // END 2018/03/02 S.Katsuma [QC#20866,ADD]
                if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).mdseCd_A)) {
                    globalMsg.A.no(i).mdseCd_A.setErrorInfo(1, "NFCM0504E", new String[] {"Item" });
                    errFlg = true;

                } else {
                    // Item Check
                    Map<String, Object> mdseMap = NLCL0290CommonLogic.getMdseMap(getGlobalCompanyCode(), globalMsg.A.no(i).mdseCd_A.getValue());

                    if (mdseMap == null) {
                        globalMsg.A.no(i).mdseCd_A.setErrorInfo(1, "NMZM0154E", new String[] {"Item" });
                        errFlg = true;
                    } else {

                        mdseDescShortTxt = (String) mdseMap.get("MDSE_DESC_SHORT_TXT");
                        shpgSerTakeFlg = (String) mdseMap.get("SHPG_SER_TAKE_FLG");
                        instlBaseCtrlFlg = (String) mdseMap.get("INSTL_BASE_CTRL_FLG");
                        // START 2018/03/02 S.Katsuma [QC#20866,ADD]
                        invtyCtrlFlg = (String) mdseMap.get("INVTY_CTRL_FLG");
                        // END 2018/03/02 S.Katsuma [QC#20866,ADD]

                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).mdseDescShortTxt_A, mdseDescShortTxt);

                        if (ZYPConstant.FLG_ON_Y.equals(shpgSerTakeFlg)) {
                            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).serNum_A)) {
                                globalMsg.A.no(i).serNum_A.setErrorInfo(1, "NLBM1252E", new String[] {});
                                errFlg = true;
                            }
                        } else {
                            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).serNum_A)) {
                                globalMsg.A.no(i).serNum_A.setErrorInfo(1, "NSAM0451E", new String[] {});
                                errFlg = true;
                            }
                        }

                        // START 2018/03/02 S.Katsuma [QC#20866,ADD]
                        if (ZYPConstant.FLG_OFF_N.equals(invtyCtrlFlg)) {
                            globalMsg.A.no(i).mdseCd_A.setErrorInfo(1, "NLCM0198E", new String[] {});
                            errFlg = true;
                        }
                        // END 2018/03/02 S.Katsuma [QC#20866,ADD]
                    }

                    // START 2018/07/05 S.Katsuma [QC#25991,ADD]
                    if (ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(bizMsg.adjTrxTpCd_H.getValue())) {
                        for (int j = 0; j < globalMsg.A.getValidCount(); j++) {
                            // Skip the same line
                            if (i != j) {
                                String iMdseCd = globalMsg.A.no(i).mdseCd_A.getValue();
                                String iFromRtlSwhCd = globalMsg.A.no(i).fromRtlSwhCd_A.getValue();
                                String jMdseCd = globalMsg.A.no(j).mdseCd_A.getValue();
                                String jFromRtlSwhCd = globalMsg.A.no(j).fromRtlSwhCd_A.getValue();
                                if (ZYPConstant.FLG_ON_Y.equals(shpgSerTakeFlg)) {
                                    String iToRtlSwhCd = globalMsg.A.no(i).toRtlSwhCd_A.getValue();
                                    String jToRtlSwhCd = globalMsg.A.no(j).toRtlSwhCd_A.getValue();
                                    // Serial
                                    if (iMdseCd.equals(jMdseCd) && iFromRtlSwhCd.equals(jFromRtlSwhCd) && !iToRtlSwhCd.equals(jToRtlSwhCd)) {
                                        globalMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "NLCM0225E", new String[] {});
                                        globalMsg.A.no(j).xxChkBox_A.setErrorInfo(1, "NLCM0225E", new String[] {});
                                        errFlg = true;
                                    }
                                } else {
                                    // Non-Serial
                                    if (iMdseCd.equals(jMdseCd) && iFromRtlSwhCd.equals(jFromRtlSwhCd)) {
                                        globalMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "NLCM0226E", new String[] {});
                                        globalMsg.A.no(j).xxChkBox_A.setErrorInfo(1, "NLCM0226E", new String[] {});
                                        errFlg = true;
                                    }
                                }
                            }
                        }
                    }
                    // END 2018/07/05 S.Katsuma [QC#25991,ADD]
                }
            }

            // QC#17488
            // QC:18490
            // Comment Check
            if (ZYPConstant.FLG_OFF_N.equals(configFlg) || (ZYPConstant.FLG_ON_Y.equals(configFlg))) {
                ADJ_CATGTMsg adjCatgTMsg = new ADJ_CATGTMsg();
                ZYPEZDItemValueSetter.setValue(adjCatgTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(adjCatgTMsg.adjCatgCd, globalMsg.A.no(i).adjCatgCd_A.getValue());
                adjCatgTMsg = (ADJ_CATGTMsg) EZDTBLAccessor.findByKey(adjCatgTMsg);
                // QC#26383
                if (adjCatgTMsg != null) {
                    if (ZYPConstant.FLG_ON_Y.equals(adjCatgTMsg.cmntReqFlg.getValue())) {
                        if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).invtyOrdLineCmntTxt_A)) {
                            globalMsg.A.no(i).invtyOrdLineCmntTxt_A.setErrorInfo(1, "NWBM0136E", new String[] {adjCatgTMsg.adjCatgDescTxt.getValue(), "Reason Code", "Comments" });
                            errFlg = true;
                        }
                    }
                }
            }

            RTL_SWHTMsg rtlSwhTMsg = null;
            // QC:18490
            if (ZYPConstant.FLG_OFF_N.equals(configFlg)) {
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).fromRtlSwhCd_A)) {
                    rtlSwhTMsg = NLCL0290CommonLogic.getRTL_SWHTMsg(getGlobalCompanyCode(), bizMsg.rtlWhCd_H.getValue(), globalMsg.A.no(i).fromRtlSwhCd_A.getValue());
                    if (rtlSwhTMsg == null) {
                        globalMsg.A.no(i).fromRtlSwhCd_A.setErrorInfo(1, "NMZM0154E", new String[] {"Source Sub WH" });
                        errFlg = true;
                    } else {
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).fromInvtyLocCd_A, rtlSwhTMsg.invtyLocCd);
                    }
                }
            }

            // QC#29172 mod start
            if (!ADJ_TRX_TP.STOCK_STATUS_CHANGE.equals(bizMsg.adjTrxTpCd_H.getValue())) {

                if (ZYPConstant.FLG_OFF_N.equals(configFlg) || (i == 0 && ZYPConstant.FLG_ON_Y.equals(configFlg))) {
                    if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).toRtlSwhCd_A)) {
                        rtlSwhTMsg = NLCL0290CommonLogic.getRTL_SWHTMsg(getGlobalCompanyCode(), bizMsg.rtlWhCd_H.getValue(), globalMsg.A.no(i).toRtlSwhCd_A.getValue());
                        if (rtlSwhTMsg == null) {
                            globalMsg.A.no(i).toRtlSwhCd_A.setErrorInfo(1, "NMZM0154E", new String[] {"Destination Sub WH" });
                            errFlg = true;
                        } else {
                            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).toInvtyLocCd_A, rtlSwhTMsg.invtyLocCd);
                        }
                    } else {
                        // SWH exist in the Master.
                        if (ZYPCommonFunc.hasValue(bizMsg.rtlSwhCd_LC.no(0))) {
                            globalMsg.A.no(i).toRtlSwhCd_A.setErrorInfo(1, "NLCM0222E", new String[] {"Destination Sub WH" });
                            errFlg = true;
                        }
                    }
                }
            }
            // QC#29172 mod end
            
            // Serial Validation
            // QC:18490
            BigDecimal svcMachMstrPk = null;
            BigDecimal svcConfigMstrPk = null;
            String svcMachTpDescTxt = null;
            String svcMachMstrStsCd = null;
            String svcMachMaintAvalFlg = null;
            String curLocNum = null;
            // QC:18472 Start
            String mdseCd = null;
            String serNum = null;
            // QC:18472 End
            // START 2019/06/03 M.Naito [QC#50408,ADD]
            String svcMachTpCd = null;
            // END 2019/06/03 M.Naito [QC#50408,ADD]
            if (ZYPConstant.FLG_OFF_N.equals(configFlg)) {
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).serNum_A) && ZYPConstant.FLG_ON_Y.equals(instlBaseCtrlFlg)) {
                    Map<String, Object> ibMap = NLCL0290CommonLogic.getIbMap(getGlobalCompanyCode(), globalMsg.A.no(i).mdseCd_A.getValue(), globalMsg.A.no(i).serNum_A.getValue(), bizMsg.adjTrxTpCd_H.getValue(), globalMsg.A.no(i).stkStsCd_A
                            .getValue());

                    if (ibMap != null) {
                        svcMachMstrPk = (BigDecimal) ibMap.get("SVC_MACH_MSTR_PK");
                        svcConfigMstrPk = (BigDecimal) ibMap.get("SVC_CONFIG_MSTR_PK");
                        svcMachTpDescTxt = (String) ibMap.get("SVC_MACH_TP_DESC_TXT");
                        svcMachMstrStsCd = (String) ibMap.get("SVC_MACH_MSTR_STS_CD");
                        svcMachMaintAvalFlg = (String) ibMap.get("SVC_MACH_MAINT_AVAL_FLG");
                        curLocNum = (String) ibMap.get("CUR_LOC_NUM");

                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).svcMachMstrPk_A, svcMachMstrPk);
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).svcConfigMstrPk_A, svcConfigMstrPk);
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).svcMachTpDescTxt_A, svcMachTpDescTxt);
                        // START 2019/06/03 M.Naito [QC#50408,ADD]
                        // Check BaseComponent
                        svcMachTpCd = (String) ibMap.get("SVC_MACH_TP_CD");
                        // END 2019/06/03 M.Naito [QC#50408,ADD]
                    }
                    // QC#2972 add start
                    if (!ADJ_TRX_TP.STOCK_STATUS_CHANGE.equals(bizMsg.adjTrxTpCd_H.getValue())) {
                        // When Increase, Already exist IB will be
                        // Error
                        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.destSwhReqFlg_H.getValue()) && BigDecimal.ZERO.compareTo(globalMsg.A.no(i).ordQty_A.getValue()) < 0) {
                            if (ibMap != null && !SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {
                                globalMsg.A.no(i).serNum_A.setErrorInfo(1, "NLZM2299E", new String[] {});
                                errFlg = true;
                            }
                        }
                    }else{
                        if (ibMap == null) {
                            globalMsg.A.no(i).serNum_A.setErrorInfo(1, "NLZM2293E", new String[] {});
                            errFlg = true;
                        } else if (ZYPConstant.FLG_OFF_N.equals(svcMachMaintAvalFlg)) {
                            globalMsg.A.no(i).serNum_A.setErrorInfo(1, "NLZM2294E", new String[] {});
                            errFlg = true;
                        } else if (!SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd) && !SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {
                            globalMsg.A.no(i).serNum_A.setErrorInfo(1, "NLZM2295E", new String[] {});
                            errFlg = true;
                        } else if (!globalMsg.A.no(i).fromInvtyLocCd_A.getValue().equals(curLocNum)) {
                            globalMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "NLZM2296E", new String[] {});
                            errFlg = true;
                        }
                    }
                    // QC#29172 mod end

                    // When Decrease, IB Check
                    if (ZYPConstant.FLG_ON_Y.equals(bizMsg.destSwhReqFlg_H.getValue()) || BigDecimal.ZERO.compareTo(globalMsg.A.no(i).ordQty_A.getValue()) > 0) {
                        if (ibMap == null) {
                            globalMsg.A.no(i).serNum_A.setErrorInfo(1, "NLZM2293E", new String[] {});
                            errFlg = true;
                        } else if (ZYPConstant.FLG_OFF_N.equals(svcMachMaintAvalFlg)) {
                            globalMsg.A.no(i).serNum_A.setErrorInfo(1, "NLZM2294E", new String[] {});
                            errFlg = true;
                        } else if (!SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd) && !SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {
                            globalMsg.A.no(i).serNum_A.setErrorInfo(1, "NLZM2295E", new String[] {});
                            errFlg = true;
                        } else if (!globalMsg.A.no(i).fromInvtyLocCd_A.getValue().equals(curLocNum)) {
                            globalMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "NLZM2296E", new String[] {});
                            errFlg = true;
                        }
                    }
                // QC#57258 Add else if process. Non Serial IB Single Item Check.
                } else if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).serNum_A) //
                        && ZYPConstant.FLG_ON_Y.equals(instlBaseCtrlFlg)
                        && ZYPCommonFunc.hasValue(globalMsg.A.no(i).ordQty_A) //
                        && (BigDecimal.ZERO.compareTo(globalMsg.A.no(i).ordQty_A.getValue()) > 0 || (!ADJ_TRX_TP.ADJUSTMENT.equals(bizMsg.adjTrxTpCd_H.getValue())))) {

                    if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).mdseCd_A) //
                            && ZYPCommonFunc.hasValue(globalMsg.A.no(i).fromInvtyLocCd_A) //
                            && ZYPCommonFunc.hasValue(globalMsg.A.no(i).stkStsCd_A) //
                            && ZYPCommonFunc.hasValue(globalMsg.A.no(i).ordQty_A)) {

                        Map<String, Object> queryParam = new HashMap<String, Object>();
                        queryParam.put("MDSE_CD", globalMsg.A.no(i).mdseCd_A.getValue());
                        queryParam.put("INVTY_LOC_CD", globalMsg.A.no(i).fromInvtyLocCd_A.getValue());
                        queryParam.put("FROM_STK_STS_CD", globalMsg.A.no(i).stkStsCd_A.getValue());
                        queryParam.put("ORD_QTY", globalMsg.A.no(i).ordQty_A.getValue().abs());

                        List<Map<String, Object>> machMstrInfoListExeList = NLCL0290CommonLogic.searchNonConfig(glblCmpyCd, queryParam);

                        if (machMstrInfoListExeList == null) {
                            globalMsg.A.no(i).mdseCd_A.setErrorInfo(1, "NLCM0242E", new String[] {});
                            globalMsg.A.no(i).stkStsCd_A.setErrorInfo(1, "NLCM0242E", new String[] {});
                            globalMsg.A.no(i).fromRtlSwhCd_A.setErrorInfo(1, "NLCM0242E", new String[] {});
                            globalMsg.A.no(i).ordQty_A.setErrorInfo(1, "NLCM0242E", new String[] {});
                            errFlg = true;
                        } else {
                            if (machMstrInfoListExeList.size() > 0) {
                                // ord qty is greater than s21 machine qty.
                                if (machMstrInfoListExeList.size() < globalMsg.A.no(i).ordQty_A.getValue().abs().intValue()) {
                                    globalMsg.A.no(i).mdseCd_A.setErrorInfo(1, "NLCM0242E", new String[] {});
                                    globalMsg.A.no(i).stkStsCd_A.setErrorInfo(1, "NLCM0242E", new String[] {});
                                    globalMsg.A.no(i).fromRtlSwhCd_A.setErrorInfo(1, "NLCM0242E", new String[] {});
                                    globalMsg.A.no(i).ordQty_A.setErrorInfo(1, "NLCM0242E", new String[] {});
                                    errFlg = true;
                                }
                            }
                        }
                    }
                }

                // START 2019/06/03 M.Naito [QC#50408,MOD]
                // QC:18490 Start
                // Check config 
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).serNum_A)) {
                    mdseCd = globalMsg.A.no(i).mdseCd_A.getValue();
                    serNum = globalMsg.A.no(i).serNum_A.getValue();
                    if (!NLCL0290CommonLogic.checkConfig(glblCmpyCd, mdseCd, serNum)) {
                        if (ZYPCommonFunc.hasValue(svcMachTpCd) && SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
                            globalMsg.A.no(i).serNum_A.setErrorInfo(1, "NLCM0216E");
                            errFlg = true;
                        // QC#57258 Add Start
                        } else if (ADJ_TRX_TP.STOCK_STATUS_CHANGE.equals(bizMsg.adjTrxTpCd_H.getValue())) {
                            globalMsg.A.no(i).serNum_A.setErrorInfo(1, "NLCM0241E");
                            errFlg = true;
                        }
                        // QC#57258 Add End
                    }
                }
                // QC:18490 End
                // END 2019/06/03 M.Naito [QC#50408,MOD]
                // START 2019/06/10 M.Naito [QC#50751,ADD]
                if (BigDecimal.ZERO.compareTo(globalMsg.A.no(i).ordQty_A.getValue()) > 0 && !ZYPCommonFunc.hasValue(globalMsg.A.no(i).serNum_A)) {
                    if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).fromInvtyLocCd_A) && ZYPCommonFunc.hasValue(globalMsg.A.no(i).locStsCd_AH) && ZYPCommonFunc.hasValue(globalMsg.A.no(i).locStsCd_AH)) {
                        mdseCd = globalMsg.A.no(i).mdseCd_A.getValue();
                        String invtyLocCd = globalMsg.A.no(i).fromInvtyLocCd_A.getValue();
                        String locStsCd = globalMsg.A.no(i).locStsCd_AH.getValue();
                        String stkStsCd = globalMsg.A.no(i).stkStsCd_A.getValue();
                        if (!NLCL0290CommonLogic.isExistInvtyDtlDly(glblCmpyCd, mdseCd, invtyLocCd, locStsCd, stkStsCd, globalMsg.A.no(i).ordQty_A.getValueInt())) {
                            globalMsg.A.no(i).ordQty_A.setErrorInfo(1, "NLCM0235E");
                            errFlg = true;
                        }
                    }
                }
                // END 2019/06/10 M.Naito [QC#50751,ADD]
            }

            // QC:18472-18490 Start
            if (ZYPConstant.FLG_OFF_N.equals(configFlg) || (ZYPConstant.FLG_ON_Y.equals(configFlg))) {
                // Sub Warehouse Check
//                if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).toRtlSwhCd_A)) {
//                    globalMsg.A.no(i).toRtlSwhCd_A.setErrorInfo(1, "NFCM0504E", new String[] {"Destination SWH" });
//                    errFlg = true;
//                }

                if (ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(bizMsg.adjTrxTpCd_H.getValue())) {
                    // Sub Warehouse Check
                    if (globalMsg.A.no(i).fromRtlSwhCd_A.getValue().equals(globalMsg.A.no(i).toRtlSwhCd_A.getValue())) {
                        globalMsg.A.no(i).fromRtlSwhCd_A.setErrorInfo(1, "NLAM1330E", new String[] {});
                        errFlg = true;
                    }
                }

                if (ZYPConstant.FLG_OFF_N.equals(configFlg)
                    && ADJ_TRX_TP.ADJUSTMENT.equals(bizMsg.adjTrxTpCd_H.getValue())
                    && ZYPCommonFunc.hasValue(bizMsg.rtlSwhCd_LC.no(0))) {
                    // Sub Warehouse Check
                    if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).fromRtlSwhCd_A)) {
                        globalMsg.A.no(i).fromRtlSwhCd_A.setErrorInfo(1, "NFCM0504E", new String[] {"Source SWH" });
                        errFlg = true;
                    }
                }
            }
            // QC:18472-18490 End

            // Set Item Cost
            // QC:18472 Start
            if (ZYPConstant.FLG_OFF_N.equals(configFlg) || ZYPConstant.FLG_OFF_0.equals(configFlg)) {
            // QC:18472 End
                // NLXC0010 get Inventory Item Cost
                NLXC001001GetInventoryItemCostBean bean = new NLXC001001GetInventoryItemCostBean();
                bean.setGlblCmpyCd(getGlobalCompanyCode());
                bean.setInvtyLocCd(globalMsg.A.no(i).fromInvtyLocCd_A.getValue());
                bean.setMdseCd(globalMsg.A.no(i).mdseCd_A.getValue());
                bean.setQty(globalMsg.A.no(i).ordQty_A.getValue());
                NLXC001001GetInventoryItemCostBean retNLXC001001GetInventoryItemCostBean = NLXC001001GetInventoryItemCost.getInventoryItemCost(bean);
                BigDecimal totPrcAmt = retNLXC001001GetInventoryItemCostBean.getTotPrcAmt();
                BigDecimal unitPrcAmt = retNLXC001001GetInventoryItemCostBean.getUnitPrcAmt();
                if (totPrcAmt != null) {
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invtyOrdLineCostAmt_A, totPrcAmt);
                }
                if (unitPrcAmt != null) {
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).unitPrcAmt_A, unitPrcAmt);
                }
            }

            if (errFlg) {
                if (firstErrNum < 0) {
                    firstErrNum = i;
                }
                // QC#31148
                globalMsg.A.no(i).svcMachMstrPk_A.clear();
                globalMsg.A.no(i).svcConfigMstrPk_A.clear();
                globalMsg.A.no(i).svcMachTpDescTxt_A.clear();

                // QC:18490 Start
                if (ZYPConstant.FLG_OFF_N.equals(configFlg)) {
                    if (ADJ_TRX_TP.ADJUSTMENT.equals(bizMsg.adjTrxTpCd_H.getValue())) {
                        globalMsg.A.no(i).fromInvtyLocCd_A.clear();
                        globalMsg.A.no(i).fromRtlSwhCd_A.clear();
                    }
                }
                // QC:18490 End

                // QC#19267 ADD START
                //globalMsg.A.no(i).svcMachMstrPk_A.clear();
                //globalMsg.A.no(i).svcConfigMstrPk_A.clear();
                //globalMsg.A.no(i).svcMachTpDescTxt_A.clear();
                // QC#19267 ADD END

                // QC:15558 Start
                // QC:14488
                // NLCL0290CommonLogic.firstErrorPage(bizMsg,
                // globalMsg, firstErrNum);
                // return;
                // QC:15558 End
            }
        }

        // QC:15558 Start
        if (errFlg) {
            NLCL0290CommonLogic.firstErrorPage(bizMsg, globalMsg, firstErrNum);
            return;
        }
        // QC:15558 End
        // START 2022/10/21 N.Takatsu[QC#60603, ADD]
        if (!NLCL0290CommonLogic.isAutoTransRetailWhCheck(glblCmpyCd, bizMsg.rtlWhCd_H.getValue())) {
            if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg_A.getValue())
                  // START 2022/12/12 E.Watabe[QC#60829, ADD]
                  && !ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(bizMsg.adjTrxTpCd_H.getValue())) {
                  // END 2022/12/12 E.Watabe[QC#60829, ADD]
                bizMsg.setMessageInfo(NLCM0244W);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_A, ZYPConstant.FLG_ON_Y);
                return;
            }
        }
        // END 2022/10/21 N.Takatsu[QC#60603, ADD]

        // Create Adjustment Order
        // QC:18472
        //if (INVTY_ORD_TP.ADJUSTMENT.equals(bizMsg.invtyOrdTpCd_H.getValue())) {

        // START 2018/12/18 M.Naito [QC#28769,MOD]
        /*************************************************************
         * Check PI
         ************************************************************/
        Map<String, String> piMap = new HashMap<String, String>();
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            String fromInvtyLocCd = globalMsg.A.no(i).fromInvtyLocCd_A.getValue();
            if (ZYPCommonFunc.hasValue(fromInvtyLocCd)) {
                if (!piMap.containsKey(fromInvtyLocCd)) {
                    piMap.put(fromInvtyLocCd, NLCL0290CommonLogic.checkOpenedPhysInvty(glblCmpyCd, fromInvtyLocCd));
                }
                if (NLZC060001Constant.RETURN_CODE_ERROR.equals(piMap.get(fromInvtyLocCd))) {
                    globalMsg.A.no(i).fromRtlSwhCd_A.setErrorInfo(1, "NLBM1347E");
                    errFlg = true;
                    firstErrNum = 1;
                }
            }

            String toInvtyLocCd = globalMsg.A.no(i).toInvtyLocCd_A.getValue();
            if (ZYPCommonFunc.hasValue(toInvtyLocCd)) {
                if (!piMap.containsKey(toInvtyLocCd)) {
                    piMap.put(toInvtyLocCd, NLCL0290CommonLogic.checkOpenedPhysInvty(glblCmpyCd, toInvtyLocCd));
                }
                if (NLZC060001Constant.RETURN_CODE_ERROR.equals(piMap.get(toInvtyLocCd))) {
                    globalMsg.A.no(i).toRtlSwhCd_A.setErrorInfo(1, "NLBM1347E");
                    errFlg = true;
                    firstErrNum = 1;
                }
            }
        }
        if (errFlg) {
            NLCL0290CommonLogic.firstErrorPage(bizMsg, globalMsg, firstErrNum);
            return;
        }

        // QC#29172 add start
        if (ADJ_TRX_TP.STOCK_STATUS_CHANGE.equals(bizMsg.adjTrxTpCd_H.getValue())) {

        // END 2018/12/18 M.Naito [QC#28769,MOD]
            /*************************************************************
             * Create Stock Status Change Order
             ************************************************************/
            // NLZC0030 - Inventory Order API Call
            List<NLZC003001PMsg> invtyOrdUpdApiPMsgList = NLCL0290CommonLogic.execInventoryOrder(glblCmpyCd, bizMsg, globalMsg);

            NLZC003001 invtyOrdUpdApi = new NLZC003001();
            invtyOrdUpdApi.execute(invtyOrdUpdApiPMsgList, ONBATCH_TYPE.ONLINE);

            for (NLZC003001PMsg outPMsg : invtyOrdUpdApiPMsgList) {
                if (!S21ApiUtil.getXxMsgIdList(outPMsg).isEmpty()) {
                    for (int j = 0; j < outPMsg.xxMsgIdList.getValidCount(); j++) {
                        String msgId = outPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                        bizMsg.setMessageInfo(msgId);
                        if (ZYPCommonFunc.hasValue(msgId)) {
                            if (msgId.endsWith("E")) {
                                errFlg = true;
                                if (firstErrNum < 0) {
                                    firstErrNum = j;
                                }
                            }
                        }
                    }
                }
            }

            if (errFlg) {
                NLCL0290CommonLogic.firstErrorPage(bizMsg, globalMsg, firstErrNum);
                return;
            }

            String invtyOrdNum = invtyOrdUpdApiPMsgList.get(0).invtyOrdNum.getValue();

            if (!ZYPCommonFunc.hasValue(invtyOrdNum)) {
                // Fail to create inventory order.
                bizMsg.setMessageInfo("NLCM0228E");
                return;
            }

            // ***********************************************************************************************
            // inventory order created.
            // ***********************************************************************************************
            List<NSZC001001PMsg> machMstrInfoList = new ArrayList<NSZC001001PMsg>();
            List<NSZC001001PMsg> machMstrUpdateInfoList = new ArrayList<NSZC001001PMsg>();

            List<Map<String, Object>> invtyOrdInfoList = NLCL0290CommonLogic.getInvtyOrdInfoList(glblCmpyCd, invtyOrdNum);

            for (Map<String, Object> invtyOrdInfo : invtyOrdInfoList) {

                // ***********************************************************************************************
                // NWZC1070 - Allocation For Non CPO API Call
                // ***********************************************************************************************
                NWZC107001PMsg allocApiPMsg = NLCL0290CommonLogic.execDivAllocation(glblCmpyCd, invtyOrdInfo);

                NWZC107001 api = new NWZC107001();

                api.execute(allocApiPMsg, ONBATCH_TYPE.ONLINE);

                if (!S21ApiUtil.getXxMsgIdList(allocApiPMsg).isEmpty()) {

                    for (int j = 0; j < allocApiPMsg.xxMsgIdList.getValidCount(); j++) {
                        String msgId = allocApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                        if (ZYPCommonFunc.hasValue(msgId)) {
                            bizMsg.setMessageInfo(msgId);
                            if (msgId.endsWith("E")) {
                                errFlg = true;
                                if (firstErrNum < 0) {
                                    firstErrNum = j;
                                }
                            }
                        }
                    }
                }
                if (errFlg) {
                    NLCL0290CommonLogic.firstErrorPage(bizMsg, globalMsg, firstErrNum);
                    return;
                }

                // ***********************************************************************************************
                // NSZC0010 - Machine Master Update API
                // Call.INSTL_BASE_CTRL_FLG = 'Y'
                // ***********************************************************************************************
                if (ZYPConstant.FLG_ON_Y.equals((String) invtyOrdInfo.get("INSTL_BASE_CTRL_FLG"))) {
                    // QC#57258 Mod. bizMsg Add
                    machMstrInfoList = NLCL0290CommonLogic.execSvcMachMstrAllocApi(bizMsg, glblCmpyCd, invtyOrdInfo);

                    if (machMstrInfoList == null) {
                        errFlg = true;
                        // QC#57258 Mod. move to execSvcMachMstrAllocApi method
//                        bizMsg.setMessageInfo("NLCM0228E");
                    } else {
                        for (NSZC001001PMsg outPMsg : machMstrInfoList) {
                            if (!S21ApiUtil.getXxMsgIdList(outPMsg).isEmpty()) {
                                for (int j = 0; j < allocApiPMsg.xxMsgIdList.getValidCount(); j++) {
                                    String msgId = allocApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                                    if (ZYPCommonFunc.hasValue(msgId)) {
                                        bizMsg.setMessageInfo(msgId);
                                        if (msgId.endsWith("E")) {
                                            errFlg = true;
                                            if (firstErrNum < 0) {
                                                firstErrNum = j;
                                            }
                                        }
                                    }
                                }
                            }
                            machMstrUpdateInfoList.add(outPMsg);
                        }
                    }
                    if (errFlg) {
                        // QC#57258 Mod. Bug Code. Screen Line is Deleted.
//                        NLCL0290CommonLogic.firstErrorPage(bizMsg, globalMsg, firstErrNum);
                        return;
                    }

                }
                // ***********************************************************************************************
                // NWZC0030 - Shipping Plan Update API Call
                // ***********************************************************************************************
                List<NWZC003001PMsg> shpgPlnUpdApiPMsgList = NLCL0290CommonLogic.execShippingPlanUpdate(glblCmpyCd, invtyOrdInfo);

                for (NWZC003001PMsg outPMsg : shpgPlnUpdApiPMsgList) {
                    if (!S21ApiUtil.getXxMsgIdList(outPMsg).isEmpty()) {
                        for (int j = 0; j < outPMsg.xxMsgIdList.getValidCount(); j++) {
                            String msgId = outPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                            if (ZYPCommonFunc.hasValue(msgId)) {
                                bizMsg.setMessageInfo(msgId);
                                if (msgId.endsWith("E")) {
                                    errFlg = true;
                                    if (firstErrNum < 0) {
                                        firstErrNum = j;
                                    }
                                }
                            }
                        }
                    }
                }
                if (errFlg) {
                    NLCL0290CommonLogic.firstErrorPage(bizMsg, globalMsg, firstErrNum);
                    return;
                }
            }
            // ***********************************************************************************************
            // NLZC2050 - SO API
            // ***********************************************************************************************
            List<NLZC205001PMsg> soApiPMsgList = NLCL0290CommonLogic.execShippingOrder(glblCmpyCd, invtyOrdInfoList);

            for (NLZC205001PMsg outPMsg : soApiPMsgList) {
                if (!S21ApiUtil.getXxMsgIdList(outPMsg).isEmpty()) {
                    for (int j = 0; j < outPMsg.xxMsgIdList.getValidCount(); j++) {
                        String msgId = outPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                        if (ZYPCommonFunc.hasValue(msgId)) {
                            bizMsg.setMessageInfo(msgId);
                            if (msgId.endsWith("E")) {
                                errFlg = true;
                                if (firstErrNum < 0) {
                                    firstErrNum = j;
                                }
                            }
                        }
                    }
                }
            }

            String soNum = soApiPMsgList.get(0).soNum.getValue();

            if (!ZYPCommonFunc.hasValue(soNum)) {
                // Fail to create shipping plan. NLCM0230E
                bizMsg.setMessageInfo("NLCM0230E");
                return;
            }
            if (errFlg) {
                NLCL0290CommonLogic.firstErrorPage(bizMsg, globalMsg, firstErrNum);
                return;
            }

            // ***********************************************************************************************
            // NLZC2000 - RWS API
            // ***********************************************************************************************
            NLZC200001PMsg rwsApiPMsg = NLCL0290CommonLogic.execRWSCreation(glblCmpyCd, soNum);

            for (int i = 0; i < rwsApiPMsg.xxMsgIdList.getValidCount(); i++) {
                String msgId = rwsApiPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        errFlg = true;
                        if (firstErrNum < 0) {
                            firstErrNum = i;
                        }
                    }
                }
            }
            String rwsNum = rwsApiPMsg.RWSNumList.no(0).rwsNum.getValue();

            if (!ZYPCommonFunc.hasValue(rwsNum)) {
                // Fail to create RWS.
                bizMsg.setMessageInfo("NLCM0230E");
                return;
            }
            if (errFlg) {
                NLCL0290CommonLogic.firstErrorPage(bizMsg, globalMsg, firstErrNum);
                return;
            }

            // ***********************************************************************************************
            // NLZC3040 - RWS Serial API
            // ***********************************************************************************************
            NLZC304001PMsg rwsSerApiPMsg = NLCL0290CommonLogic.execRwsSerCreation(glblCmpyCd, rwsNum, invtyOrdInfoList);

            for (int i = 0; i < rwsSerApiPMsg.xxMsgIdList.getValidCount(); i++) {
                String msgId = rwsSerApiPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        errFlg = true;
                        if (firstErrNum < 0) {
                            firstErrNum = i;
                        }
                    }
                }
            }
            if (errFlg) {
                NLCL0290CommonLogic.firstErrorPage(bizMsg, globalMsg, firstErrNum);
                return;
            }

            /*************************************************************
             * Execute Stock Status Change
             ************************************************************/
            RWS_HDRTMsg rwsHdrMap = NLCL0290CommonLogic.getRwsHdr(glblCmpyCd, rwsNum);

            // ***********************************************************************************************
            // NLZC2060 - S21 DC Put Away Confirmation API
            // ***********************************************************************************************
            NLZC206001PMsg putAwayConfApiPmsg = NLCL0290CommonLogic.execRwsPutAwayConf(rwsHdrMap);

            for (int i = 0; i < putAwayConfApiPmsg.xxMsgIdList.getValidCount(); i++) {
                String msgId = putAwayConfApiPmsg.xxMsgIdList.no(i).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        errFlg = true;
                        if (firstErrNum < 0) {
                            firstErrNum = i;
                        }
                    }
                }
            }
            if (errFlg) {
                NLCL0290CommonLogic.firstErrorPage(bizMsg, globalMsg, firstErrNum);
                return;
            }

            // ***********************************************************************************************
            // NLZC2070 - S21 DC RWS Completion API
            // ***********************************************************************************************
            NLZC207001PMsg rwsCompletionApiPmsg = NLCL0290CommonLogic.execRwsCompletion(rwsHdrMap);

            for (int i = 0; i < rwsCompletionApiPmsg.xxMsgIdList.getValidCount(); i++) {
                String msgId = rwsCompletionApiPmsg.xxMsgIdList.no(i).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        errFlg = true;
                        if (firstErrNum < 0) {
                            firstErrNum = i;
                        }
                    }
                }
            }
            if (errFlg) {
                NLCL0290CommonLogic.firstErrorPage(bizMsg, globalMsg, firstErrNum);
                return;
            }

            // ***********************************************************************************************
            // NLZC3020 - Serial Transaction Update API
            // ***********************************************************************************************

            NLZC302001PMsg serTrxApipMsg = NLCL0290CommonLogic.execSerialTransaction(rwsHdrMap, invtyOrdInfoList, soNum);
            for (int i = 0; i < serTrxApipMsg.xxMsgIdList.getValidCount(); i++) {
                String msgId = serTrxApipMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        errFlg = true;
                        if (firstErrNum < 0) {
                            firstErrNum = i;
                        }
                    }
                }
            }
            if (errFlg) {
                NLCL0290CommonLogic.firstErrorPage(bizMsg, globalMsg, firstErrNum);
                return;
            }

            // ***********************************************************************************************
            // NSZC0010 - Machine Master Update API Call
            // ***********************************************************************************************
            // The case Machine Master the Machine Master was
            // Allocation
            for (NSZC001001PMsg machMstrInfo : machMstrUpdateInfoList) {

                // ALLOCATION_OFF
                NSZC001001PMsg svcMachMstrApiPMsg = NLCL0290CommonLogic.execSvcMachMstrAllocationOff(glblCmpyCd, machMstrInfo);
                for (int i = 0; i < svcMachMstrApiPMsg.xxMsgIdList.getValidCount(); i++) {
                    String msgId = svcMachMstrApiPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    if (ZYPCommonFunc.hasValue(msgId)) {
                        bizMsg.setMessageInfo(msgId);
                        if (msgId.endsWith("E")) {
                            errFlg = true;
                            if (firstErrNum < 0) {
                                firstErrNum = i;
                            }
                        }
                    }
                }

                // UPDATE_INVENTORY
                NSZC001001PMsg svcMachMstrUpdateInventryPmsg = NLCL0290CommonLogic.execSvcMachMstrUpdateInventry(invtyOrdInfoList, glblCmpyCd, machMstrInfo);
                for (int i = 0; i < svcMachMstrUpdateInventryPmsg.xxMsgIdList.getValidCount(); i++) {
                    String msgId = svcMachMstrUpdateInventryPmsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    if (ZYPCommonFunc.hasValue(msgId)) {
                        if (msgId.endsWith("E")) {
                            bizMsg.setMessageInfo(msgId);
                            errFlg = true;
                            if (firstErrNum < 0) {
                                firstErrNum = i;
                            }
                        }
                    }
                }
                if (errFlg) {
                    NLCL0290CommonLogic.firstErrorPage(bizMsg, globalMsg, firstErrNum);
                    return;
                }
            }
            Map<String, Object> invtyOrdInfo = invtyOrdInfoList.get(0);
            ZYPEZDItemValueSetter.setValue(bizMsg.invtyOrdNum_H, (String) invtyOrdInfo.get("INVTY_ORD_NUM"));

        } else {

            // Call Adjustment Order API (Detail)
            List<NLZC004001PMsg> adjOrdApiPMsgList = NLCL0290CommonLogic.getAdjustmentApiPMsgDetailList(getGlobalCompanyCode(), bizMsg, globalMsg);
            NLZC004001 nlzc004001 = new NLZC004001();
            nlzc004001.execute(adjOrdApiPMsgList, ONBATCH_TYPE.ONLINE);

            for (NLZC004001PMsg adjOrdApiPMsg : adjOrdApiPMsgList) {
                if (S21ApiUtil.isXxMsgId(adjOrdApiPMsg)) {
                    for (int i = 0; i < adjOrdApiPMsg.xxMsgIdList.getValidCount(); i++) {
                        S21ApiMessage msg = S21ApiUtil.getXxMsgList(adjOrdApiPMsg).get(0);
                        String msgId = msg.getXxMsgid();
                        bizMsg.setMessageInfo(msgId);
                        if (msgId.endsWith("E")) {
                            bizMsg.setMessageInfo(msgId);
                            errFlg = true;
                            if (firstErrNum < 0) {
                                firstErrNum = i;
                            }
                        }
                    }
                }
            }
            if (errFlg) {
                NLCL0290CommonLogic.firstErrorPage(bizMsg, globalMsg, firstErrNum);
                return;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.invtyOrdNum_H, adjOrdApiPMsgList.get(0).invtyOrdNum);
        }
        // QC#29172 add end

        // QC#50008 send Mail to FSM.
        if (NLCL0290CommonLogic.isTechOrCustomer(glblCmpyCd, bizMsg.rtlWhCd_H.getValue())) {
            String rtlWhCd = bizMsg.rtlWhCd_H.getValue();

            List<S21MailAddress> fsrMailAddrList = NLCL0290CommonLogic.getFSMEmailAddress(glblCmpyCd, rtlWhCd);

            if (fsrMailAddrList.isEmpty()) {
                // Error
                bizMsg.setMessageInfo(NLCM0234E, new String[]{"Not found FSM eMail address."});
                return;
            }

            List<S21MailAddress> whOwnrMailAddrList = NLCL0290CommonLogic.getWhOwnrEmailAddress(glblCmpyCd, rtlWhCd);

            S21MailAddress fromMaillAddr;
            if (ZYPCommonFunc.hasValue(this.getContextUserInfo().getEmailAddress())) {
                fromMaillAddr = new S21MailAddress(glblCmpyCd, this.getContextUserInfo().getEmailAddress());
            } else {
                List<S21MailAddress> sysMailAddrList = NLCL0290CommonLogic.getSystemEmailAddress(glblCmpyCd);

                if (sysMailAddrList.isEmpty()) {
                    // Error
                    bizMsg.setMessageInfo(NLCM0234E, new String[] {"Not found system admin eMail address." });
                    return;
                } else {
                    fromMaillAddr = sysMailAddrList.get(0);
                }
            }

            String oprUserId = this.getContextUserInfo().getUserId();
            String oprUserNm = this.getContextUserInfo().getFullName();

            S21MailTemplate template = NLCL0290CommonLogic.setMailTemplateOfSendTechWhNotif(glblCmpyCd, oprUserId, oprUserNm, bizMsg, globalMsg);

            // Send mail
            NLCL0290CommonLogic.sendMail(glblCmpyCd, fsrMailAddrList, whOwnrMailAddrList, fromMaillAddr, template);
        }

        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_CMN_Submit END -----", null);
    }

}
