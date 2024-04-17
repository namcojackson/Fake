/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0990;

import static business.blap.NSAL0990.constant.NSAL0990Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0990.common.NSAL0990CommonLogic;
import business.blap.NSAL0990.constant.NSAL0990Constant;
import business.db.MDSETMsg;
import business.db.MDSETMsgArray;
import business.parts.NMZC610001PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157004PMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
/**
 *<pre>
 * Supply Order
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/06   Hitachi         K.Kasai         Create          N/A
 * 2016/02/23   Hitachi         T.Tsuchida      Update          QC#4117
 * 2016/03/10   Hitachi         A.Kohinata      Update          QC#5210
 * 2016/03/11   Hitachi         A.Kohinata      Update          QC#5354
 * 2016/03/15   Hitachi         A.Kohinata      Update          QC#5375
 * 2016/03/16   Hitachi         A.Kohinata      Update          QC#5530
 * 2016/03/29   Hitachi         A.Kohinata      Update          QC#5790
 * 2016/03/31   Hitachi         K.Kasai         Update          QC#6344
 * 2016/09/21   Hitachi         N.Arai          Update          QC#11616
 * 2016/10/13   Hitachi         A.Kohinata      Update          QC#9885
 * 2016/10/19   Hitachi         A.Kohinata      Update          QC#15352
 * 2016/10/20   Hitachi         A.Kohinata      Update          QC#15323
 * 2017/01/06   Hitachi         K.Ochiai        Update          QC#16012
 * 2017/05/09   Hitachi         K.Kitachi       Update          QC#18277
 * 2017/09/15   Hitachi         U.Kim           Update          QC#18726
 * 2017/09/28   Hitachi         U.Kim           Update          QC#18726-1
 * 2017/11/22   Hitachi         M.Kidokoro      Update          QC#20497
 * 2017/12/26   Fujitsu         K.Ishizuka      Update          QC#20164(Sol#356)
 * 2018/02/09   Hitachi         M.Kidokoro      Update          QC#23065
 * 2018/02/14   Hitachi         U.Kim           Update          QC#20297(Sol#379)
 * 2018/02/21   Hitachi         M.Kidokoro      Update          QC#23144-1
 * 2018/04/11   Hitachi         K.Kitachi       Update          QC#11642
 * 2018/07/03   Hitachi         K.Kitachi       Update          QC#26924
 * 2018/07/04   CITS            T.Wada          Update          QC#23726
 * 2018/07/09   CITS            T.Wada          Update          QC#23726-1
 * 2018/07/30   Hitachi         T.Tomita        Update          QC#14307
 * 2018/08/09   Hitachi         K.Kim           Update          QC#27251
 * 2018/09/20   CITS            T.Wada          Update          QC#28333
 * 2018/09/25   Hitachi         K.Kitachi       Update          QC#26260
 * 2018/11/09   Hitachi         K.Kim           Update          QC#29169
 * 2018/11/15   Fujitsu         M.Yamada        Update          QC#29191
 * 2018/11/21   Fujitsu         M.Yamada        Update          QC#29302
 * 2018/12/12   Fujitsu         H.Kumagai       Update          QC#29315
 * 2018/12/21   Fujitsu         Hd.Sugawara     Update          QC#29315
 * 2019/01/21   Hitachi         A.Kohinata      Update          QC#27304
 * 2024/03/07   Hitachi         K.Kishimoto     Update          QC#63547
 *</pre>
 */
public class NSAL0990BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0990CMsg cMsg = (NSAL0990CMsg) arg0;
        super.preDoProcess(cMsg, null);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0990_INIT".equals(screenAplID)) {
                doProcess_NSAL0990_INIT(cMsg);
            } else if ("NSAL0990Scrn00_Add_Mdse".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_Add_Mdse(cMsg);
            } else if ("NSAL0990Scrn00_Calcu".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_Calcu(cMsg);
            } else if ("NSAL0990Scrn00_CMN_Return".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_CMN_Return(cMsg);
            } else if ("NSAL0990Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_CMN_Submit(cMsg);
            } else if ("NSAL0990Scrn00_DeleteLine".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_DeleteLine(cMsg);
            } else if ("NSAL0990Scrn00_DeleteQuote".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_DeleteQuote(cMsg);
            } else if ("NSAL0990Scrn00_Disp_SupplyOrder".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_Disp_SupplyOrder(cMsg);
            } else if ("NSAL0990Scrn00_Disp_SupplyOrderEdit".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_Disp_SupplyOrderEdit(cMsg);
            } else if ("NSAL0990Scrn00_OnBlur_BillToLocation".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_OnBlur_BillToLocation(cMsg);
            } else if ("NSAL0990Scrn00_OnBlur_ChangePeriod".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_OnBlur_ChangePeriod(cMsg);
            } else if ("NSAL0990Scrn00_OnBlur_ShipToLocation".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_OnBlur_ShipToLocation(cMsg);
            } else if ("NSAL0990Scrn00_OnBlur_DeriveFromFreightTerms".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_OnBlur_DeriveFromFreightTerms(cMsg);
            } else if ("NSAL0990Scrn00_OnBlur_DeriveFromCarrierServiceLevel".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_OnBlur_DeriveFromCarrierServiceLevel(cMsg);
            } else if ("NSAL0990Scrn00_Search_BillTo".equals(screenAplID)) {
//                doProcess_NSAL0990Scrn00_Search_BillTo(cMsg);
                doProcess_NSAL0990Scrn00_OnBlur_BillToLocation(cMsg);
            } else if ("NSAL0990Scrn00_Search_MdseNm".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_Search_MdseNm(cMsg);
            } else if ("NSAL0990Scrn00_Search_ShipTo".equals(screenAplID)) {
//              doProcess_NSAL0990Scrn00_Search_ShipTo(cMsg);
                doProcess_NSAL0990Scrn00_OnBlur_ShipToLocation(cMsg);
            } else if ("NSAL0990_NMAL6760".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_NMAL6760(cMsg);
            } else if ("NSAL0990Scrn00_OpenWin_SupplyOrderSer".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_OpenWin_SupplyOrderSer(cMsg);
            } else if ("NSAL0990_NSAL1020".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_NSAL1020(cMsg);
            } else if ("NSAL0990Scrn00_MoveWin_CreditCard".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_OnBlur_BillToLocation(cMsg);
            } else if ("NSAL0990Scrn00_OpenWin_CarrierServiceLevel".equals(screenAplID)) {
                doProcessNSAL0990Scrn00_OpenWin_CarrierServiceLevel(cMsg);
            // add start 2016/10/13 CSA Defect#9885
            } else if ("NSAL0990Scrn00_FilterByModel".equals(screenAplID)) {
                doProcessNSAL0990Scrn00_FilterByModel(cMsg);
            // add end 2016/10/13 CSA Defect#9885
            // START 2017/11/22 M.Kidokoro [QC#20497, ADD]
            } else if ("NSAL0990_NMAL6050".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_OnBlur_DeriveFromFreightTerms(cMsg);
            // END 2017/11/22 M.Kidokoro [QC#20497, ADD]
            // START 2018/04/11 K.Kitachi [QC#11642, ADD]
            } else if ("NSAL0990Scrn00_OpenWin_ContactSearch".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_OpenWin_ContactSearch(cMsg);
            // END 2018/04/11 K.Kitachi [QC#11642, ADD]
            // START 2018/07/03 T.Wada [QC#23726, ADD]
                // Del Start 2018/12/21 QC#29315
            //} else if ("NSAL0990Scrn00_OnChange_ShpgSvcLvlCd".equals(screenAplID)) {
            //    doProcess_NSAL0990Scrn00_OnChange_ShpgSvcLvlCd(cMsg);
                // Del End 2018/12/21 QC#29315
            // END 2018/07/03 T.Wada [QC#23726, ADD]
            // QC#29191
            } else if ("NSAL0990_NWAL1130".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_NWAL1130(cMsg);
            // add start 2019/01/21 QC#27304
            } else if ("NSAL0990Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_CMN_Save(cMsg);
            // add end 2019/01/21 QC#27304
            }
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NSAL0990_INIT(NSAL0990CMsg cMsg) {

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());
        setValue(cMsg.svcMachMstrPk_BK, cMsg.svcMachMstrPk);
        NSAL0990CommonLogic.createPullDown(cMsg);
        // mod start 2019/01/21 QC#27304
        NSAL0990CommonLogic.setHeaderInfo(cMsg, ZYPConstant.FLG_ON_Y);
        // mod end 2019/01/21 QC#27304
        NSAL0990CommonLogic.getOrdTp(cMsg);
        // 2018/12/12 Add Start QC#29315
        setValue(cMsg.dsBizAreaCd, NSAL0990CommonLogic.getDsBizAreaCd(cMsg));
        // 2018/12/12 Add End QC#29315
        NSAL0990CommonLogic.setOrdHdrDtl(cMsg);
        NSAL0990CommonLogic.setShpgInfo(cMsg);
        NSAL0990CommonLogic.setTonerAllotInfo(cMsg);
        NSAL0990CommonLogic.setOrderHist(cMsg);
        NSAL0990CommonLogic.setLineDetail(cMsg);
        NSAL0990CommonLogic.setSupplyStats(cMsg);
        NSAL0990CommonLogic.getCcyDigitNum(cMsg);
        NSAL0990CommonLogic.setShpgLbl(cMsg); // 2017/12/26 K.Ishizuka S21_NA#20164(Sol#356)
        // START 2017/11/29 K.Kojima [QC#20497,ADD]
        NSAL0990CommonLogic.setDefFrtCond(cMsg);
        // END 2017/11/29 K.Kojima [QC#20497,ADD]
        // START 2018/07/02 T.Wada [QC#23726,ADD]
        NSAL0990CommonLogic.setDefCarrSvcLvl(cMsg);
        // END 2018/07/02 T.Wada [QC#23726,ADD]
        // add start 2016/10/13 CSA Defect#9885
        NSAL0990CommonLogic.setAllLineDetail(cMsg, cMsg.xxScrDply.getValue());
        NSAL0990CommonLogic.createModelPullDown(cMsg, cMsg.xxScrDply.getValue());
        // add end 2016/10/13 CSA Defect#9885
        // add start 2016/10/20 CSA Defect#15323
        // START 2018/02/14 U.Kim [QC#20297(Sol#379), ADD]
        NSAL0990CommonLogic.setDefShpgCmt(cMsg);
        // END 2018/02/14 U.Kim [QC#20297(Sol#379), ADD]
        // START 2018/09/25 K.Kitachi [QC#26260, DEL]
//        if (!hasValue(cMsg.slsRepCd)) {
//            cMsg.setMessageInfo(NSAL0990Constant.NSAM0179E, new String[] {"Sales Rep" });
//        }
        // END 2018/09/25 K.Kitachi [QC#26260, DEL]
        // add end 2016/10/20 CSA Defect#15323
        // Add Start 2018/07/31 QC#14307
        NSAL0990CommonLogic.setSpclInstructionItem(cMsg);
        // Add End 2018/07/31 QC#14307
        // add start 2019/01/21 QC#27304
        setValue(cMsg.dsContrDtlPk_BK, cMsg.dsContrDtlPk);
        setValue(cMsg.serNum_BK, cMsg.serNum);
        NSAL0990CommonLogic.setPsnNm(cMsg);
        // add end 2019/01/21 QC#27304
    }

    private void doProcess_NSAL0990Scrn00_Add_Mdse(NSAL0990CMsg cMsg) {
        // START 2017/05/09 K.Kitachi [QC#18277, ADD]
        if (cMsg.C.getValidCount() == cMsg.C.length()) {
            cMsg.setMessageInfo(NSAL0990Constant.NSAM0112E);
            return;
        }
        // END 2017/05/09 K.Kitachi [QC#18277, ADD]
        //get MDSE Info
        if (!NSAL0990CommonLogic.getAddLineDetail(cMsg)) {
            return;
        }

        // mod start 2019/01/21 QC#27304
        int validCnt = cMsg.C.getValidCount();
        cMsg.C.setValidCount(validCnt + 1);
        if (cMsg.svcMachMstrPk.getValue().compareTo(cMsg.svcMachMstrPk_BK.getValue()) == 0) {
            setValue(cMsg.C.no(validCnt).prcCondManDelFlg_C, ZYPConstant.FLG_OFF_N);
            setValue(cMsg.C.no(validCnt).xxTblSortNum_C, BigDecimal.ONE);
            setValue(cMsg.C.no(validCnt).xxSortNum_C, new BigDecimal(validCnt));
        } else {
            setValue(cMsg.C.no(validCnt).prcCondManDelFlg_C, ZYPConstant.FLG_ON_Y);
            setValue(cMsg.C.no(validCnt).xxTblSortNum_C, NSAL0990CommonLogic.getMaxTblSortNum(cMsg).add(BigDecimal.ONE));
            setValue(cMsg.C.no(validCnt).xxSortNum_C, new BigDecimal(validCnt));
        }

        //get Price List
        NWZC157001PMsg prcApiPMsg = NSAL0990CommonLogic.callPricingApiOfGetBasePriceMode(cMsg, cMsg.mdseCd.getValue(), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE.intValue());
        if (NSAL0990CommonLogic.checkPrcApiParam(cMsg, prcApiPMsg)) {
            // set Field Amount
            NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(0);
            // START 2018/08/09 K.Kim [QC#27251, DEL]
            // setValue(cMsg.C.no(validCnt).ordCustUomQty_C, BigDecimal.ZERO);
            // END 2018/08/09 K.Kim [QC#27251, DEL]
            setValue(cMsg.C.no(validCnt).entCpoDtlDealSlsAmt_C, BigDecimal.ZERO);
            setValue(cMsg.C.no(validCnt).entDealNetUnitPrcAmt_C, prcTotalPMsg.xxUnitGrsPrcAmt);
            setValue(cMsg.C.no(validCnt).xxDplyCtrlFlg_C, ZYPConstant.FLG_OFF_N);
        } else {
            //NSAL0990CommonLogic.clearAddRow(cMsg);
            cMsg.setMessageInfo(null);
            cMsg.setMessageInfo(NSAM0745W);
            setValue(cMsg.C.no(validCnt).entCpoDtlDealSlsAmt_C, BigDecimal.ZERO);
            setValue(cMsg.C.no(validCnt).entDealNetUnitPrcAmt_C, BigDecimal.ZERO);
            setValue(cMsg.C.no(validCnt).xxDplyCtrlFlg_C, ZYPConstant.FLG_OFF_N);
        }

        // add start 2016/10/13 CSA Defect#9885
        int validCntH = cMsg.H.getValidCount();
        EZDMsg.copy(cMsg.C.no(validCnt), "C", cMsg.H.no(validCntH), "H");
        setValue(cMsg.H.no(validCntH).xxSetFlg_H, ZYPConstant.FLG_ON_Y);
        cMsg.H.setValidCount(validCntH + 1);
        setValue(cMsg.C.no(validCnt).xxRowNum_C, BigDecimal.valueOf(validCntH));
        // add end 2016/10/13 CSA Defect#9885
        // mod end 2019/01/21 QC#27304
    }

    private void doProcess_NSAL0990Scrn00_Calcu(NSAL0990CMsg cMsg) {
        // add start 2016/10/13 CSA Defect#9885
        NSAL0990CommonLogic.resetFilter(cMsg, cMsg.xxScrDply.getValue());
        // add end 2016/10/13 CSA Defect#9885

        if (NSAL0990Constant.MODE_1.equals(cMsg.xxScrDply.getValue())) {
            // del start 2019/01/21 QC#27304
            // mandatory check
            //if (!NSAL0990CommonLogic.checkMandatory(cMsg)) {
            //    return;
            //}
            //if (!NSAL0990CommonLogic.checkValidation(cMsg)) {
            //    return;
            //}
            // del end 2019/01/21 QC#27304

            for (int i = 0; i < cMsg.C.getValidCount(); i++) {
                if (!hasValue(cMsg.C.no(i).ordCustUomQty_C)) {
                    cMsg.C.no(i).ordCustUomQty_C.setErrorInfo(1, NSAL0990Constant.ZZZM9007E, new String[] {"Ordered Qty" });
                    return;
                }
            }
            BigDecimal dtlAmt = BigDecimal.ZERO;
            BigDecimal totAmt = BigDecimal.ZERO;
            for (int i = 0; i < cMsg.C.getValidCount(); i++) {
                NSAL0990_CCMsg cCMsg = cMsg.C.no(i);

                // add start 2019/01/21 QC#27304
                NWZC157001PMsg prcApiPMsg = NSAL0990CommonLogic.callPricingApiOfGetBasePriceMode(cMsg, cCMsg.mdseCd_C.getValue(), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE.intValue());
                if (NSAL0990CommonLogic.checkPrcApiParam(cMsg, prcApiPMsg)) {
                    NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(0);
                    setValue(cCMsg.entDealNetUnitPrcAmt_C, prcTotalPMsg.xxUnitGrsPrcAmt);
                } else {
                    cMsg.setMessageInfo(null);
                    cMsg.setMessageInfo(NSAM0745W);
                    setValue(cCMsg.entDealNetUnitPrcAmt_C, BigDecimal.ZERO);
                }
                // add end 2019/01/21 QC#27304

                dtlAmt = cCMsg.ordCustUomQty_C.getValue().multiply(cCMsg.entDealNetUnitPrcAmt_C.getValue());
                // START 2018/02/09 M.Kidokoro [QC#23065,ADD]
                // START 2018/07/03 K.Kitachi [QC#26924, MOD]
//                if (NSAL0990Query.getInstance().isTonerExistsSplyReln(cMsg.glblCmpyCd.getValue(), cMsg.C.no(i).mdseCd_C.getValue(), cMsg.svcTermCondDataDispTxt_02.getValue())) {
                // START 2018/9/19 T.Wada [QC#28333 MOD]
                //if (NSAL0990Query.getInstance().isExistsSplyReln(cMsg.glblCmpyCd.getValue(), cMsg.C.no(i).mdseCd_C.getValue())) {
                BigDecimal t_MdlId = null;
                // START 2018/11/09 [QC#29169 MOD]
                // if (ZYPCommonFunc.hasValue(cMsg.t_MdlId)) {
                //     t_MdlId =  cMsg.t_MdlId.getValue();
                // }
                if (ZYPCommonFunc.hasValue(cMsg.C.no(i).t_MdlId_C)) {
                    t_MdlId = cMsg.C.no(i).t_MdlId_C.getValue();
                }
                // END 2018/11/09 [QC#29169 MOD]
//                if (NSAL0990Query.getInstance().isExistsSplyReln(cMsg.glblCmpyCd.getValue(), cMsg.C.no(i).mdseCd_C.getValue(), t_MdlId)) { // QC#29302
                if (NSAL0990Query.getInstance().isExistsSplyReln(cMsg, cMsg.C.no(i).mdseCd_C.getValue(), t_MdlId)) { // QC#29302
                // END 2018/9/19 T.Wada [QC#28333 MOD]
                // END 2018/07/03 K.Kitachi [QC#26924, MOD]
                    dtlAmt = BigDecimal.ZERO;
                }
                // END 2018/02/09 M.Kidokoro [QC#23065,ADD]
                setValue(cCMsg.entCpoDtlDealSlsAmt_C, dtlAmt);
                totAmt = totAmt.add(dtlAmt);
            }
            setValue(cMsg.xxTotAmt_C, totAmt);
        } else if (NSAL0990Constant.MODE_2.equals(cMsg.xxScrDply.getValue())) {
            NSAL0990CommonLogic.calcuLineDtlForEditMode(cMsg);
        }
    }

    private void doProcess_NSAL0990Scrn00_CMN_Return(NSAL0990CMsg cMsg) {

    }

    private void doProcess_NSAL0990Scrn00_CMN_Submit(NSAL0990CMsg cMsg) {

    }

    private void doProcess_NSAL0990Scrn00_DeleteLine(NSAL0990CMsg cMsg) {

        int deleteRowCnt = 0;
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.C.no(i).xxChkBox_C.getValue())) {
                // START 2017/09/15 U.Kim [QC#18726, MOD]
                // deleteRowCnt++;
                // add start 2016/10/13 CSA Defect#9885
                // setValue(cMsg.H.no(cMsg.C.no(i).xxRowNum_C.getValueInt()).xxSetFlg_H, ZYPConstant.FLG_OFF_N);
                // add end 2016/10/13 CSA Defect#9885
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.C.no(i).xxDplyCtrlFlg_C.getValue())) {
                    // START 2018/08/09 K.Kim [QC#27251, MOD]
                    // setValue(cMsg.C.no(cMsg.C.no(i).xxRowNum_C.getValueInt()).ordCustUomQty_C, BigDecimal.ZERO);
                    cMsg.C.no(cMsg.C.no(i).xxRowNum_C.getValueInt()).ordCustUomQty_C.clear();
                    // END 2018/08/09 K.Kim [QC#27251, MOD]
                } else {
                    deleteRowCnt++;
                    setValue(cMsg.H.no(cMsg.C.no(i).xxRowNum_C.getValueInt()).xxSetFlg_H, ZYPConstant.FLG_OFF_N);
                }
                // END 2017/09/15 U.Kim [QC#18726, MOD]
            } else {
                EZDMsg.copy(cMsg.C.no(i), null, cMsg.C.no(i - deleteRowCnt), null);
            }
        }
        for (int i = cMsg.C.getValidCount() - deleteRowCnt; i < cMsg.C.getValidCount(); i++) {
            cMsg.C.no(i).clear();
        }
        cMsg.C.setValidCount(cMsg.C.getValidCount() - deleteRowCnt);
    }

    private void doProcess_NSAL0990Scrn00_DeleteQuote(NSAL0990CMsg cMsg) {
        // add start 2016/10/13 CSA Defect#9885
        NSAL0990CommonLogic.resetFilter(cMsg, cMsg.xxScrDply.getValue());
        // add end 2016/10/13 CSA Defect#9885
        // START 2017/09/15 U.Kim [QC#18726, ADD]
        String preSerNum = "";
        // END 2017/09/15 U.Kim [QC#18726, ADD]
        int deleteRowCnt = 0;
        boolean delSerGrp = false;
        BigDecimal delSvcMachMstrPk = null;
        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.E.no(i).xxChkBox_E.getValue())) {
                // START 2017/09/15 U.Kim [QC#18726, DEL]
                // deleteRowCnt++;
                // END 2017/09/15 U.Kim [QC#18726, DEL]
                delSerGrp = true;
                delSvcMachMstrPk = cMsg.E.no(i).svcMachMstrPk_E.getValue();
                // START 2017/09/15 U.Kim [QC#18726, MOD]
                // add start 2016/10/13 CSA Defect#9885
                // setValue(cMsg.H.no(cMsg.E.no(i).xxRowNum_E.getValueInt()).xxSetFlg_H, ZYPConstant.FLG_OFF_N);
                // add end 2016/10/13 CSA Defect#9885
                if (ZYPConstant.FLG_OFF_N.equals(cMsg.E.no(i).prcCondManDelFlg_E.getValue())) {
                    // START 2018/08/09 K.Kim [QC#27251, MOD]
                    // setValue(cMsg.E.no(cMsg.E.no(i).xxRowNum_E.getValueInt()).ordCustUomQty_E, BigDecimal.ZERO);
                    cMsg.E.no(cMsg.E.no(i).xxRowNum_E.getValueInt()).ordCustUomQty_E.clear();
                    // END 2018/08/09 K.Kim [QC#27251, MOD]
                    // START 2017/09/28 U.Kim [QC#18726-1, ADD]
                    delSerGrp = false;
                    // END 2017/09/28 U.Kim [QC#18726-1, ADD]
                } else if (preSerNum.equals(cMsg.E.no(i).serNum_E.getValue())) {
                    // START 2018/08/09 K.Kim [QC#27251, MOD]
                    // setValue(cMsg.E.no(cMsg.E.no(i).xxRowNum_E.getValueInt()).ordCustUomQty_E, BigDecimal.ZERO);
                    cMsg.E.no(cMsg.E.no(i).xxRowNum_E.getValueInt()).ordCustUomQty_E.clear();
                    // END 2018/08/09 K.Kim [QC#27251, MOD]
                    // START 2017/09/28 U.Kim [QC#18726-1, ADD]
                    EZDMsg.copy(cMsg.E.no(i), null, cMsg.E.no(i - deleteRowCnt), null);
                    delSerGrp = false;
                    // END 2017/09/28 U.Kim [QC#18726-1, ADD]
                } else {
                    deleteRowCnt++;
                    setValue(cMsg.H.no(cMsg.E.no(i).xxRowNum_E.getValueInt()).xxSetFlg_H, ZYPConstant.FLG_OFF_N);
                }
                // END 2017/09/15 U.Kim [QC#18726, MOD]
            } else if (delSerGrp && delSvcMachMstrPk.equals(cMsg.E.no(i).svcMachMstrPk_E.getValue())) {
                // START 2017/09/28 U.Kim [QC#18726-1, DEL]
                // START 2017/09/15 U.Kim [QC#18726, MOD]
                // deleteRowCnt++;
                // add start 2016/10/13 CSA Defect#9885
                // setValue(cMsg.H.no(cMsg.E.no(i).xxRowNum_E.getValueInt()).xxSetFlg_H, ZYPConstant.FLG_OFF_N);
                // add end 2016/10/13 CSA Defect#9885
                // if (ZYPConstant.FLG_OFF_N.equals(cMsg.E.no(i).prcCondManDelFlg_E.getValue())) {
                //     setValue(cMsg.E.no(cMsg.E.no(i).xxRowNum_E.getValueInt()).ordCustUomQty_E, BigDecimal.ZERO);
                // } else if (preSerNum.equals(cMsg.E.no(i).serNum_E.getValue())) {
                //    setValue(cMsg.E.no(cMsg.E.no(i).xxRowNum_E.getValueInt()).ordCustUomQty_E, BigDecimal.ZERO);
                // } else {
                // END 2017/09/28 U.Kim [QC#18726-1, DEL]
                deleteRowCnt++;
                setValue(cMsg.H.no(cMsg.E.no(i).xxRowNum_E.getValueInt()).xxSetFlg_H, ZYPConstant.FLG_OFF_N);
                // START 2017/09/28 U.Kim [QC#18726-1, DEL]
                // }
                // END 2017/09/15 U.Kim [QC#18726, MOD]
                // END 2017/09/28 U.Kim [QC#18726-1, DEL]
            } else if (delSerGrp && !delSvcMachMstrPk.equals(cMsg.E.no(i).svcMachMstrPk_E.getValue())) {
                EZDMsg.copy(cMsg.E.no(i), null, cMsg.E.no(i - deleteRowCnt), null);
                delSerGrp = false;
            } else {
                EZDMsg.copy(cMsg.E.no(i), null, cMsg.E.no(i - deleteRowCnt), null);
            }
            // START 2017/09/28 U.Kim [QC#18726-1, ADD]
            preSerNum = cMsg.E.no(i).serNum_E.getValue();
            // END 2017/09/28 U.Kim [QC#18726-1, ADD]
        }
        for (int i = cMsg.E.getValidCount() - deleteRowCnt; i < cMsg.E.getValidCount(); i++) {
            cMsg.E.no(i).clear();
        }
        cMsg.E.setValidCount(cMsg.E.getValidCount() - deleteRowCnt);

        // add start 2016/10/13 CSA Defect#9885
        NSAL0990CommonLogic.createModelPullDown(cMsg, cMsg.xxScrDply.getValue());
        // add end 2016/10/13 CSA Defect#9885
    }

    private void doProcess_NSAL0990Scrn00_Disp_SupplyOrder(NSAL0990CMsg cMsg) {
        // add start 2016/10/13 CSA Defect#9885
        NSAL0990CommonLogic.resetFilter(cMsg, NSAL0990Constant.MODE_2);
        // add end 2016/10/13 CSA Defect#9885

        // mod start 2019/01/21 QC#27304
        NSAL0990CommonLogic.setHeaderInfo(cMsg, ZYPConstant.FLG_OFF_N);
        // mod end 2019/01/21 QC#27304
        NSAL0990CommonLogic.getOrdTp(cMsg);
        NSAL0990CommonLogic.setOrdHdrDtl(cMsg);
        NSAL0990CommonLogic.setShpgInfo(cMsg);
        NSAL0990CommonLogic.setTonerAllotInfo(cMsg);
        NSAL0990CommonLogic.setOrderHist(cMsg);
        // START 2018/02/21 M.Kidokoro [QC#23144-1,MOD]
//        boolean newSerialFlg = true;
//        int j = 0;
//        ZYPTableUtil.clear(cMsg.C);
//        BigDecimal totAmt = BigDecimal.ZERO;
//        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
//            if (cMsg.svcMachMstrPk.getValue().compareTo(cMsg.E.no(i).svcMachMstrPk_E.getValue()) == 0) {
//                newSerialFlg = false;
//                EZDMsg.copy(cMsg.E.no(i), "E", cMsg.C.no(j), "C");
//                cMsg.C.no(j).xxChkBox_C.clear();
//                if (hasValue(cMsg.C.no(j).entCpoDtlDealSlsAmt_C)) {
//                    totAmt = totAmt.add(cMsg.C.no(j).entCpoDtlDealSlsAmt_C.getValue());
//                }
//                j++;
//            }
//        }
//        cMsg.C.setValidCount(j);
//        setValue(cMsg.xxTotAmt_C, totAmt);
//        // mod start 2016/10/19 CSA Defect#15293
//        boolean result = true;
//        if (newSerialFlg) {
//            result = NSAL0990CommonLogic.setLineDetail(cMsg);
//        }
        // mod start 2019/01/21 QC#27304
        NSAL0990CommonLogic.setLineDetail(cMsg);

        BigDecimal totAmt = BigDecimal.ZERO;
        boolean exists = false;
        int k = cMsg.C.getValidCount();
        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
            exists = false;
            if (cMsg.svcMachMstrPk.getValue().compareTo(cMsg.E.no(i).svcMachMstrPk_E.getValue()) == 0) {
                for (int j = 0; j < cMsg.C.getValidCount(); j++) {
                    if (cMsg.C.no(j).mdseCd_C.getValue().compareTo(cMsg.E.no(i).mdseCd_E.getValue()) == 0) {
                        EZDMsg.copy(cMsg.E.no(i), "E", cMsg.C.no(j), "C");
                        cMsg.C.no(j).xxChkBox_C.clear();
                        if (hasValue(cMsg.C.no(j).entCpoDtlDealSlsAmt_C)) {
                            totAmt = totAmt.add(cMsg.C.no(j).entCpoDtlDealSlsAmt_C.getValue());
                        }
                        exists = true;
                    }
                }
                if (!exists) {
                    EZDMsg.copy(cMsg.E.no(i), "E", cMsg.C.no(k), "C");
                    cMsg.C.no(k).xxChkBox_C.clear();
                    totAmt = totAmt.add(cMsg.C.no(k).entCpoDtlDealSlsAmt_C.getValue());
                    k++;
                }
            }
        }
        cMsg.C.setValidCount(k);

        setValue(cMsg.xxTotAmt_C, totAmt);
        // END 2018/02/21 M.Kidokoro [QC#23144-1,MOD]

        // add start 2016/10/13 CSA Defect#9885
        NSAL0990CommonLogic.setAllLineDetail(cMsg, NSAL0990Constant.MODE_1);
        NSAL0990CommonLogic.createModelPullDown(cMsg, NSAL0990Constant.MODE_1);
        // add end 2016/10/13 CSA Defect#9885
        // mod end 2016/10/19 CSA Defect#15293
        // mod end 2019/01/21 QC#27304
    }

    private void doProcess_NSAL0990Scrn00_Disp_SupplyOrderEdit(NSAL0990CMsg cMsg) {
        // add start 2016/10/13 CSA Defect#9885
        NSAL0990CommonLogic.resetFilter(cMsg, NSAL0990Constant.MODE_1);
        // add end 2016/10/13 CSA Defect#9885

        // mod start 2019/01/21 QC#27304
        if (!NSAL0990CommonLogic.checkValidationForDisp_SupplyOrderEdit(cMsg)) {
            return;
        }

        //calcu_Line Details for Supply Order Edit
        // mod start 2016/10/19 CSA Defect#15293
        NSAL0990CommonLogic.calcuLineDtlForEditMode(cMsg);

        // add start 2016/10/13 CSA Defect#9885
        NSAL0990CommonLogic.setAllLineDetail(cMsg, NSAL0990Constant.MODE_2);
        NSAL0990CommonLogic.createModelPullDown(cMsg, NSAL0990Constant.MODE_2);
        // add end 2016/10/13 CSA Defect#9885
        // mod end 2016/10/19 CSA Defect#15293
        // mod end 2019/01/21 QC#27304
    }

    private void doProcess_NSAL0990Scrn00_OnBlur_BillToLocation(NSAL0990CMsg cMsg) {
         // add start 2019/01/21 QC#27304
        if (!hasValue(cMsg.billToLocNum)) {
            cMsg.xxLocAddrNm_A1.clear();
            cMsg.xxLocAddrNm_A2.clear();
            cMsg.xxLocAddrNm_A3.clear();
            cMsg.ctyAddr_A1.clear();
            cMsg.stCd_A1.clear();
            cMsg.postCd_A1.clear();
            cMsg.ctryCd_A1.clear();
            cMsg.xxLocAddrNm_D.clear();
            cMsg.billToLocNum_SI.clear();
            return;
        }
        // add end 2019/01/21 QC#27304

        NMZC610001PMsg billToCustInfoApiPMsg = NSAL0990CommonLogic.callCustomerInfomationGetApiForGet(cMsg, NSAL0990Constant.BILL_TO);
        if (S21ApiUtil.isXxMsgId(billToCustInfoApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(billToCustInfoApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
                if (msgId.endsWith("E")) {
                    cMsg.billToLocNum.setErrorInfo(1, msgId, msgPrms);
                    return;
                }
            }
        } else {
            // Mod Start 2018/07/30 QC#14307
            // get BILL_TO Info
            // NSAL0990CommonLogic.getBillToAddr(cMsg);
            if (NSAL0990CommonLogic.getBillToAddr(cMsg)) {
                NSAL0990CommonLogic.setBillToLocNumSI(cMsg);
            }
            // Mod End 2018/07/30 QC#14307
        }
    }

    private void doProcess_NSAL0990Scrn00_OnBlur_ChangePeriod(NSAL0990CMsg cMsg) {
        NSAL0990CommonLogic.setOrderHist(cMsg);
    }

    // QC#23726 Add Start
    // Del Start 2018/12/21 QC#29315
    //private void doProcess_NSAL0990Scrn00_OnChange_ShpgSvcLvlCd(NSAL0990CMsg cMsg) {
    //    NSAL0990CommonLogic.setCarrSvcLvl(cMsg);
    //}
    // Del End 2018/12/21 QC#29315
    // QC#23726 Add End

    private void doProcess_NSAL0990Scrn00_OnBlur_ShipToLocation(NSAL0990CMsg cMsg) {
        // add start 2019/01/21 QC#27304
        if (!hasValue(cMsg.curLocNum)) {
            cMsg.xxLocAddrNm_A4.clear();
            cMsg.xxLocAddrNm_A5.clear();
            cMsg.xxLocAddrNm_A6.clear();
            cMsg.ctyAddr_A2.clear();
            cMsg.stCd_A2.clear();
            cMsg.postCd_A2.clear();
            cMsg.ctryCd_A2.clear();
            cMsg.curLocAcctNum.clear();
            cMsg.curLocNum_SI.clear();
            return;
        }
        // add end 2019/01/21 QC#27304

        NMZC610001PMsg shipToCustInfoApiPMsg = NSAL0990CommonLogic.callCustomerInfomationGetApiForGet(cMsg, NSAL0990Constant.SHIP_TO);
        if (S21ApiUtil.isXxMsgId(shipToCustInfoApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(shipToCustInfoApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
                if (msgId.endsWith("E")) {
                    cMsg.curLocNum.setErrorInfo(1, msgId, msgPrms);
                    return;
                }
            }
        } else {
            // Mod Start 2018/07/30 QC#14307
            // get SHIP_TO Info
            // NSAL0990CommonLogic.getShipToAddr(cMsg);
            if (NSAL0990CommonLogic.getShipToAddr(cMsg)) {
                NSAL0990CommonLogic.setCurLocNumSI(cMsg);
            }
            // Mod End 2018/07/30 QC#14307
        }

    }

    private void doProcess_NSAL0990Scrn00_OnBlur_DeriveFromFreightTerms(NSAL0990CMsg cMsg) {
        // START 2017/11/29 K.Kojima [QC#20497,ADD]
        if (!hasValue(cMsg.frtCondDescTxt)) {
            cMsg.frtCondCd.clear();
            NSAL0990CommonLogic.setShpgSvcLvlPullDown(cMsg);
            return;
        }
        // END 2017/11/29 K.Kojima [QC#20497,ADD]

        Map<String, String> freightTermInfo = NSAL0990CommonLogic.getFreightTermInfo(cMsg);
        if (freightTermInfo == null) {
            // START 2017/11/29 K.Kojima [QC#20497,ADD]
            cMsg.frtCondCd.clear();
            cMsg.shpgSvcLvlCd_CD.clear();
            cMsg.shpgSvcLvlDescTxt_NM.clear();
            // END 2017/11/29 K.Kojima [QC#20497,ADD]
            return;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.frtCondCd, freightTermInfo.get("FRT_COND_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.frtCondDescTxt, freightTermInfo.get("FRT_COND_DESC_TXT"));

        // QC#23726-1 Add Start
        // Del Start 2018/12/21 QC#29315
        //NSAL0990CommonLogic.setDefCarrSvcLvl(cMsg);
        // Del End 2018/12/21 QC#29315
        // QC#23726-1 Add End
        NSAL0990CommonLogic.setShpgSvcLvlPullDown(cMsg);
    }

    private void doProcess_NSAL0990Scrn00_OnBlur_DeriveFromCarrierServiceLevel(NSAL0990CMsg cMsg) {

        if (!NSAL0990CommonLogic.checkValidationForCarrSvclvl(cMsg)) {
            return;
        }

        Map<String, String> carrSvcLvlInfo = NSAL0990CommonLogic.getCarrSvcLvlInfo(cMsg);
        if (carrSvcLvlInfo == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.carrSvcLvlCd, carrSvcLvlInfo.get("CARR_SVC_LVL_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.carrSvcLvlDescTxt, carrSvcLvlInfo.get("CARR_SVC_LVL_DESC_TXT"));
    }

    private void doProcess_NSAL0990Scrn00_Search_MdseNm(NSAL0990CMsg cMsg) {

        // get Service Program Info
        NSAL0990Query query = NSAL0990Query.getInstance();

        // get Additional Line Detail
        if (query.getSvcSplyMisc(cMsg.glblCmpyCd.getValue(), cMsg.slsDt.getValue(), cMsg.mdseCd.getValue()) != null) {
            //get MDSE info from ORD_TAKE_MDSE
            MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(cMsg.glblCmpyCd.getValue(), cMsg.mdseCd.getValue());
            //get Order Type
            MDSETMsg condition = new MDSETMsg();
            condition.setSQLID("001");
            condition.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            condition.setConditionValue("mdseCd01", mdseTMsg.mdseCd.getValue());
            condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

            MDSETMsgArray outTMsgArray = (MDSETMsgArray) EZDTBLAccessor.findByCondition(condition);
            if (outTMsgArray.getValidCount() > 0) {
             // START 2016/09/21 N.Arai [QC#11616, MOD]
             // setValue(cMsg.mdseNm, outTMsgArray.no(0).mdseNm);
                setValue(cMsg.mdseDescShortTxt, mdseTMsg.mdseDescShortTxt);
             // END 2016/09/21 N.Arai [QC#11616, MOD]
            } else {
                cMsg.mdseCd.setErrorInfo(1, NSAM0011E, new String[]{"Entered Mede Code"});
            }
        } else {
            cMsg.mdseCd.setErrorInfo(1, NSAM0413E);
        }
    }

    private void doProcess_NSAL0990Scrn00_NMAL6760(NSAL0990CMsg cMsg) {
        if ("OpenWin_BillTo".equals(cMsg.xxScrEventNm.getValue())) {
            doProcess_NSAL0990Scrn00_OnBlur_BillToLocation(cMsg);
        } else if ("OpenWin_ShipTo".equals(cMsg.xxScrEventNm.getValue())) {
            doProcess_NSAL0990Scrn00_OnBlur_ShipToLocation(cMsg);
        }
    }

    private void doProcess_NSAL0990Scrn00_OpenWin_SupplyOrderSer(NSAL0990CMsg cMsg) {
        // add start 2016/10/13 CSA Defect#9885
        NSAL0990CommonLogic.resetFilter(cMsg, cMsg.xxScrDply.getValue());
        // add end 2016/10/13 CSA Defect#9885

        // mod start 2019/01/21 QC#27304
        // mandatory check
        //if (!NSAL0990CommonLogic.checkMandatory(cMsg)) {
        //    return;
        //}
        //if (!NSAL0990CommonLogic.checkValidation(cMsg)) {
        //    return;
        //}

        // check Bill To Cust and Account Code
        if (!NSAL0990CommonLogic.checkBillToCust(cMsg)) {
            return;
        }
        // check Ship To Cust and Account Code
        if (!NSAL0990CommonLogic.checkShipToCust(cMsg)) {
            return;
        }
        // check Bill To Cust and Ship To Cust
        if (!NSAL0990CommonLogic.checkBillShipSoldRelation(cMsg)) {
            return;
        }
        // mod end 2019/01/21 QC#27304

        // START 2016/02/23 T.Tsuchida [QC#4117, MOD]
        if (NSAL0990Constant.MODE_1.equals(cMsg.xxScrDply.getValue())) {
            // START 2024/03/07 [QC#63547, ADD]
            if (!NSAL0990CommonLogic.checkValidationForAddMore(cMsg)) {
                return;
            }
            // END   2024/03/07 [QC#63547, ADD]
            NSAL0990CommonLogic.sortLineDetailToFMsgForSplyOrdMode(cMsg);
        } else if (NSAL0990Constant.MODE_2.equals(cMsg.xxScrDply.getValue())) {
            NSAL0990CommonLogic.sortLineDetailToFMsgForEditMode(cMsg);
        }
        ZYPTableUtil.clear(cMsg.E);
        for (int k = 0; k < cMsg.F.getValidCount(); k++) {
            EZDMsg.copy(cMsg.F.no(k), "F", cMsg.E.no(k), "E");
            cMsg.E.setValidCount(k + 1);
        }
        // END 2016/02/23 T.Tsuchida [QC#4117, MOD]
    }

    private void doProcess_NSAL0990Scrn00_NSAL1020(NSAL0990CMsg cMsg) {
        if (NSAL0990Constant.MODE_1.equals(cMsg.xxScrDply.getValue())) {
            // mod start 2019/01/21 QC#27304
            NSAL0990CommonLogic.setHeaderInfo(cMsg, ZYPConstant.FLG_OFF_N);
            // mod end 2019/01/21 QC#27304
            NSAL0990CommonLogic.getOrdTp(cMsg);
            NSAL0990CommonLogic.setOrdHdrDtl(cMsg);
            NSAL0990CommonLogic.setShpgInfo(cMsg);
            NSAL0990CommonLogic.setTonerAllotInfo(cMsg);
            NSAL0990CommonLogic.setOrderHist(cMsg);
            NSAL0990CommonLogic.setLineDetail(cMsg);
            // ADD start 2017/01/06 CSA Defect#16012
            NSAL0990CommonLogic.setSupplyStats(cMsg);
            // ADD end 2017/01/06 CSA Defect#16012
        } else {
            // mod start 2019/01/21 QC#27304
            NSAL0990CommonLogic.setHeaderInfo(cMsg, ZYPConstant.FLG_OFF_N);
            // mod end 2019/01/21 QC#27304
            NSAL0990CommonLogic.getOrdTp(cMsg);
            NSAL0990CommonLogic.setLineDetail(cMsg);
            NSAL0990CommonLogic.sortLineDetailToFMsgForSplyOrdMode(cMsg);
            for (int k = 0; k < cMsg.F.getValidCount(); k++) {
                EZDMsg.copy(cMsg.F.no(k), "F", cMsg.E.no(k), "E");
                cMsg.E.setValidCount(k + 1);
            }
        }
        // add start 2016/10/13 CSA Defect#9885
        NSAL0990CommonLogic.setAllLineDetail(cMsg, cMsg.xxScrDply.getValue());
        NSAL0990CommonLogic.createModelPullDown(cMsg, cMsg.xxScrDply.getValue());
        // add end 2016/10/13 CSA Defect#9885
    }

    private void doProcessNSAL0990Scrn00_OpenWin_CarrierServiceLevel(NSAL0990CMsg cMsg) {
        // mandatory check
        if (!NSAL0990CommonLogic.checkMandatoryForCarrSvclvl(cMsg)) {
            return;
        }
        if (!NSAL0990CommonLogic.checkValidationForCarrSvclvl(cMsg)) {
            return;
        }
    }

    // add start 2016/10/13 CSA Defect#9885
    private void doProcessNSAL0990Scrn00_FilterByModel(NSAL0990CMsg cMsg) {

        if (NSAL0990Constant.MODE_1.equals(cMsg.xxScrDply.getValue())) {
            for (int i = 0; i < cMsg.C.getValidCount(); i++) {
                EZDMsg.copy(cMsg.C.no(i), "C", cMsg.H.no(cMsg.C.no(i).xxRowNum_C.getValueInt()), "H");
            }

            ZYPTableUtil.clear(cMsg.C);
            int j = 0;
            for (int i = 0; i < cMsg.H.getValidCount(); i++) {
                if (ZYPConstant.FLG_OFF_N.equals(cMsg.H.no(i).xxSetFlg_H.getValue())) {
                    continue;
                }
                if (hasValue(cMsg.t_MdlId_S)) {
                    if (hasValue(cMsg.H.no(i).t_MdlId_H) && cMsg.t_MdlId_S.getValue().compareTo(cMsg.H.no(i).t_MdlId_H.getValue()) == 0) {
                        EZDMsg.copy(cMsg.H.no(i), "H", cMsg.C.no(j), "C");
                        setValue(cMsg.C.no(j).xxRowNum_C, BigDecimal.valueOf(i));
                        j++;
                    }
                } else {
                    EZDMsg.copy(cMsg.H.no(i), "H", cMsg.C.no(j), "C");
                    setValue(cMsg.C.no(j).xxRowNum_C, BigDecimal.valueOf(i));
                    j++;
                }
            }
            cMsg.C.setValidCount(j);

        } else {
            for (int i = 0; i < cMsg.E.getValidCount(); i++) {
                EZDMsg.copy(cMsg.E.no(i), "E", cMsg.H.no(cMsg.E.no(i).xxRowNum_E.getValueInt()), "H");
            }

            ZYPTableUtil.clear(cMsg.E);
            int j = 0;
            for (int i = 0; i < cMsg.H.getValidCount(); i++) {
                if (ZYPConstant.FLG_OFF_N.equals(cMsg.H.no(i).xxSetFlg_H.getValue())) {
                    continue;
                }
                if (hasValue(cMsg.t_MdlId_S)) {
                    if (hasValue(cMsg.H.no(i).t_MdlId_H) && cMsg.t_MdlId_S.getValue().compareTo(cMsg.H.no(i).t_MdlId_H.getValue()) == 0) {
                        EZDMsg.copy(cMsg.H.no(i), "H", cMsg.E.no(j), "E");
                        setValue(cMsg.E.no(j).xxRowNum_E, BigDecimal.valueOf(i));
                        j++;
                    }
                } else {
                    EZDMsg.copy(cMsg.H.no(i), "H", cMsg.E.no(j), "E");
                    setValue(cMsg.E.no(j).xxRowNum_E, BigDecimal.valueOf(i));
                    j++;
                }
            }
            cMsg.E.setValidCount(j);
        }
    }
    // add end 2016/10/13 CSA Defect#9885

    // START 2018/04/11 K.Kitachi [QC#11642, ADD]
    private void doProcess_NSAL0990Scrn00_OpenWin_ContactSearch(NSAL0990CMsg cMsg) {
        if (!hasValue(cMsg.curLocNum)) {
            cMsg.locNum_HD.clear();
            return;
        }
        setValue(cMsg.locNum_HD, NSAL0990CommonLogic.getLocNum(cMsg));
    }
    // END 2018/04/11 K.Kitachi [QC#11642, ADD]

    // QC#29121
    private void doProcess_NSAL0990Scrn00_NWAL1130(NSAL0990CMsg cMsg) {
        // mod start 2019/01/21 QC#27304
        if ("OpenWin_FreightTerms".equals(cMsg.xxScrEventNm.getValue()) || "OnBlur_DeriveFromFreightTerms".equals(cMsg.xxScrEventNm.getValue())) {
            doProcess_NSAL0990Scrn00_OnBlur_DeriveFromFreightTerms(cMsg);

        } else if ("OpenWin_EditingList".equals(cMsg.xxScrEventNm.getValue())) {
            if (!NSAL0990CommonLogic.setHeaderInfoBySvcSplyOrd(cMsg)) {
                return;
            }
            NSAL0990CommonLogic.getOrdTp(cMsg);
            setValue(cMsg.dsBizAreaCd, NSAL0990CommonLogic.getDsBizAreaCd(cMsg));
            NSAL0990CommonLogic.setOrdHdrDtl(cMsg);
            NSAL0990CommonLogic.setShpgInfo(cMsg);
            if (!NSAL0990CommonLogic.setLineDetailBySvcSplyOrd(cMsg)) {
                return;
            }
            setValue(cMsg.xxScrDply, NSAL0990Constant.MODE_2);
            NSAL0990CommonLogic.calcuLineDtlForEditMode(cMsg);
            NSAL0990CommonLogic.setAllLineDetail(cMsg, cMsg.xxScrDply.getValue());
            NSAL0990CommonLogic.createModelPullDown(cMsg, cMsg.xxScrDply.getValue());
        }
        // mod end 2019/01/21 QC#27304
    }

    // add start 2019/01/21 QC#27304
    private void doProcess_NSAL0990Scrn00_CMN_Save(NSAL0990CMsg cMsg) {
    }
    // add end 2019/01/21 QC#27304
}
