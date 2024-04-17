/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770.common;

import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_CMN_ADD_BTN_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_CMN_ADD_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_CMN_ADD_LABEL;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.KEY_QUOTE_CANCELLED;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.TAB_ITEMS;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.ALL_RGTN_AUTH;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_ADD_CTAC_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_ADD_ITEM_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_ATT_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_CALC_QUOTE_AMT_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_CANC_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_CHK_CONTR_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_CONF_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_CREDIT_CARD_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_DEL_CTAC_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_INVTY_INQ_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_PRC_DTL_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_PRFT_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_QUOTE_COPY_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_QUOTE_SRCH_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_SHPG_DTL_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_SLS_CREDIT_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_SPCL_INSTN_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_TRK_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_VIEW_CHNG_LOG_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.REF_AUTH;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL1770.NWAL1770BMsg;
import business.servlet.NWAL1770.NWAL1770_ABMsg;
import business.servlet.NWAL1770.NWAL1770_BBMsg;
import business.servlet.NWAL1770.NWAL1770_CBMsg;
import business.servlet.NWAL1770.NWAL1770_HBMsg;
import business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/05   Fujitsu         T.Yoshida       Create          N/A
 * 2016/05/16   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/08/10   Fujitsu         S.Iidaka        Update          S21_NA#13173
 * 2016/08/31   Fujitsu         T.Murai         Update          S21_AN#11547
 * 2017/08/01   Hitachi         E.Kameishi      Update          QC#20295
 * 2017/08/07   Fujitsu         Y.Kanefusa      Update          S21_NA#10347
 * 2017/08/09   Fujitsu         H.Nagashima     Update          QC#16452
 * 2017/09/28   Fujitsu         T.Murai         Update          S21_NA#21121
 * 2017/10/03   Fujitsu         S.Ohki          Update          S21_NA#21068-2
 * 2017/10/18   Fujitsu         W.Honda         Update          S21_NA#20246-1(L3 Sol#454)
 * 2017/10/26   Fujitsu         R.Nakamura      Update          S21_NA#21323
 * 2017/11/27   Fujitsu         M.Ohno          Update          S21_NA#21155
 * 2018/02/13   Fujitsu         T.Aoi           Update          S21_NA#21165
 * 2018/03/02   Fujitsu         K.Ishizuka      Update          S21_NA#22956
 * 2018/05/11   Fujitsu         T.Aoi           Update          S21_NA#22139
 * 2018/05/22   Fujitsu         T.Aoi           Update          S21_NA#22139-2
 * 2018/06/25   Fujitsu         H.Nagashima     Update          S21_NA#23726
 * 2018/09/27   Fujitsu         S.Kosaka        Update          QC#28517
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 *</pre>
 */
public class NWAL1770CommonLogicForScrnFields {

    /**
     * Set Screen Protect
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1770BMsg
     */
    public static void setProtect(EZDCommonHandler handler, NWAL1770BMsg scrnMsg) {

        // Set All Active Field
        setControlFields(scrnMsg);
        setAllActiveFields(handler, scrnMsg);

        // Set Protect By Status
        setProtectByStatus(handler, scrnMsg);

        // Set Protect By Inherent Control
        setProtectByInherentControl(handler, scrnMsg);

        // Set Protect By Authority
        setProtectByAuthority(handler, scrnMsg);
    }

    /**
     * Set Control Fields
     * @param scrnMsg Screen Msg
     */
    private static void setControlFields(NWAL1770BMsg scrnMsg) {

        setControlFieldsForLink(scrnMsg);
        setControlFieldsForDigit(scrnMsg);
    }

    /**
     * Set Control Fields For Link
     * @param scrnMsg Screen Msg
     */
    private static void setControlFieldsForLink(NWAL1770BMsg scrnMsg) {

        // Header
        scrnMsg.dsOrdCatgDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.slsRepTocNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.psnNum_LK.setValue(ZYPConstant.FLG_ON_Y); // S21_NA#7861 Mod
        scrnMsg.prcCatgNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.cpoOrdNum_LK.setValue(ZYPConstant.FLG_ON_Y);

        // Customer / Contact Tab
        scrnMsg.billToCustAcctCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.billToCustCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.billToCustAcctNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.shipToCustAcctCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.shipToCustCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.shipToCustAcctNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.dropShipFlg_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.sellToCustCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.soldToCustLocCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.soldToCustAcctNm_LK.setValue(ZYPConstant.FLG_ON_Y);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1770_ABMsg contactMsg = scrnMsg.A.no(i);
            contactMsg.ctacPsnFirstNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        }

        // Delivery / Payment Tab
        scrnMsg.frtCondDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.carrSvcLvlDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        // Add 2016/08/31 QC#11547
        scrnMsg.pmtTermCashDiscDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);

        // Items Tab
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(i);
            itemLineMsg.mdseCd_LK.setValue(ZYPConstant.FLG_ON_Y);
            itemLineMsg.prcCatgNm_BL.setValue(ZYPConstant.FLG_ON_Y);
            itemLineMsg.rtlWhNm_LK.setValue(ZYPConstant.FLG_ON_Y);
            itemLineMsg.rtlSwhNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        }

        // Addtional Tab
        scrnMsg.prcContrNum_LK.setValue(ZYPConstant.FLG_ON_Y);
    }

    /**
     * Set Control Fields For Digit
     * @param scrnMsg Screen Msg
     */
    private static void setControlFieldsForDigit(NWAL1770BMsg scrnMsg) {

        // Header
        scrnMsg.xxSubTotCalcPrcAmt.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotChrgPrcAmt.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotTaxAmt.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotAmt.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());

        // Items Tab
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(i);
            itemLineMsg.dealSplyQuoteDtlSlsAmt_B.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            itemLineMsg.dealPrcListPrcAmt_B.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            itemLineMsg.xxDtlDiscAmt_B.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            itemLineMsg.xxTotDiscAmt_B.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            itemLineMsg.xxSubTotCalcPrcAmt_B.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            itemLineMsg.xxTotFrtAmt_B.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            itemLineMsg.xxTotTaxAmt_B.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            itemLineMsg.xxTotAmt_B.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        }

        // Additional Data Tab
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            NWAL1770_CBMsg addlMsg = scrnMsg.C.no(i);
            // QC#28517 2018/09/27 Mod Start
            //addlMsg.xxTotUnitNetWt_C.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            addlMsg.xxTotUnitNetWt_C.setAppFracDigit(NWAL1770ConstantForScrnFields.DIGIT_TOT_UNIT_NET_WT);
            // QC#28517 2018/09/27 Mod End
        }
    }

    /**
     * Set Screen Protect By Inherent Control
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setProtectByInherentControl(EZDCommonHandler handler, NWAL1770BMsg scrnMsg) {

        setProtectBySetComponent(scrnMsg);
        setProtectBySearchSucceeded(handler, scrnMsg);
        setProtectByReferenceField(scrnMsg);
        setProtectByFrtCond(scrnMsg);
        setProtectByPmtMeth(handler, scrnMsg);
        setProtectByToBeCanceled(scrnMsg);
        setProtectByDropShipAvalFlg(scrnMsg);
        setProtectByBillTo(scrnMsg);     // QC#17474 2017/02/21 Add
        setProtectByMdse(scrnMsg);
        setProtectByContact(scrnMsg);   // QC#16452 add
    }

    /**
     * Set Screen Protect By Set Component
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setProtectBySetComponent(NWAL1770BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(i);
            if (ZYPCommonFunc.hasValue(itemLineMsg.dplyQuoteLineSubNum_B)) {
                itemLineMsg.xxChkBox_B.setInputProtected(true);
                itemLineMsg.mdseCd_B.setInputProtected(true);
                itemLineMsg.mdseCd_LK.clear();
                itemLineMsg.ordCustUomQty_B.setInputProtected(true);
                itemLineMsg.custUomCd_B.setInputProtected(true);
                // Add Start 2017/10/26 QC#21323
                itemLineMsg.custMdseCd_B.setInputProtected(true);
                // Add End 2017/10/26 QC#21323
                itemLineMsg.dealSplyQuoteDtlSlsAmt_B.setInputProtected(true);
                itemLineMsg.prcCatgNm_B.setInputProtected(true);
                itemLineMsg.prcCatgNm_BL.clear();
                itemLineMsg.supdLockFlg_B.setInputProtected(true);
                itemLineMsg.dsOrdLineCatgCd_B.setInputProtected(true);
                itemLineMsg.ordLineSrcCd_B.setInputProtected(true);
                itemLineMsg.rtlWhNm_B.setInputProtected(true);
                itemLineMsg.rtlWhNm_LK.clear();
                itemLineMsg.rtlSwhNm_B.setInputProtected(true);
                itemLineMsg.rtlSwhNm_LK.clear();
                itemLineMsg.prcBaseDt_B.setInputProtected(true); // 2017/10/03 S21_NA#21068 Add
                itemLineMsg.rddDt_B.setInputProtected(true); // 2018/02/13 QC#21165 Add
            }
        }
    }

    /**
     * Set Screen Protect By Search Succeeded
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setProtectBySearchSucceeded(EZDCommonHandler handler, NWAL1770BMsg scrnMsg) {

        // Search Button
        if (ZYPCommonFunc.hasValue(scrnMsg.xxSrchNum)) {
            scrnMsg.splyQuoteNum.setInputProtected(true);
            handler.setButtonEnabled(BTN_QUOTE_SRCH_EVENT_NM, false);
        }

        // Copy Button
        if (ZYPCommonFunc.hasValue(scrnMsg.splyQuoteNum)) {
            handler.setButtonEnabled(BTN_QUOTE_COPY_EVENT_NM, true);
        } else if (scrnMsg.splyQuoteNum.isInputProtected()) {
            handler.setButtonEnabled(BTN_QUOTE_COPY_EVENT_NM, false);
        }
    }

    /**
     * Set Screen Protect By Reference Field
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setProtectByReferenceField(NWAL1770BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.firstBllgAttrbNm)) {
            scrnMsg.firstBllgAttrbValTxt.setInputProtected(true);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.scdBllgAttrbNm)) {
            scrnMsg.scdBllgAttrbValTxt.setInputProtected(true);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.thirdBllgAttrbNm)) {
            scrnMsg.thirdBllgAttrbValTxt.setInputProtected(true);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.frthBllgAttrbNm)) {
            scrnMsg.frthBllgAttrbValTxt.setInputProtected(true);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.fifthBllgAttrbNm)) {
            scrnMsg.fifthBllgAttrbValTxt.setInputProtected(true);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.sixthBllgAttrbNm)) {
            scrnMsg.sixthBllgAttrbValTxt.setInputProtected(true);
        }
    }

    /**
     * Set Screen Protect By Freight Condition
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setProtectByFrtCond(NWAL1770BMsg scrnMsg) {

        String splyQuoteStsCd = scrnMsg.splyQuoteStsCd.getValue();

        if (!ZYPCommonFunc.hasValue(splyQuoteStsCd) || SPLY_QUOTE_STS.SAVED.equals(splyQuoteStsCd)) {
            // QC#23726 2018/06/25 mod Start
            scrnMsg.carrSvcLvlDescTxt.setInputProtected(false);
            scrnMsg.carrSvcLvlDescTxt_LK.setInputProtected(false);
            scrnMsg.carrSvcLvlDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
            if (FRT_COND.COLLECT.equals(scrnMsg.frtCondCd.getValue())) {
                scrnMsg.carrAcctNum.setInputProtected(false);
//                scrnMsg.carrSvcLvlDescTxt.setInputProtected(false);
//                scrnMsg.carrSvcLvlDescTxt_LK.setInputProtected(false);
//                scrnMsg.carrSvcLvlDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                scrnMsg.carrAcctNum.setInputProtected(true);
//                scrnMsg.carrSvcLvlDescTxt.setInputProtected(true);
                scrnMsg.carrAcctNum.clear();
//                scrnMsg.carrSvcLvlDescTxt.clear();
//                scrnMsg.carrSvcLvlDescTxt_LK.clear();
            }
            // QC#23726 2018/06/25 mod End
        }
    }

    /**
     * Set Screen Protect By Payment Method
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setProtectByPmtMeth(EZDCommonHandler handler, NWAL1770BMsg scrnMsg) {

//        if (SPLY_QUOTE_STS.SAVED.equals(scrnMsg.splyQuoteStsCd.getValue())) { // Mod 2017/09/28 S21_NA#21121
        if (!SPLY_QUOTE_STS.CANCELLED.equals(scrnMsg.splyQuoteStsCd.getValue()) && !SPLY_QUOTE_STS.SUBMITTED.equals(scrnMsg.splyQuoteStsCd.getValue())) {

            if (DS_PMT_METH.CREDIT_CARD.equals(scrnMsg.dsPmtMethCd.getValue()) || ZYPConstant.FLG_ON_Y.equals(scrnMsg.pmtCcFlg.getValue())) {
                handler.setButtonEnabled(BTN_CREDIT_CARD_EVENT_NM, true);
            } else {
                handler.setButtonEnabled(BTN_CREDIT_CARD_EVENT_NM, false);
            }
        }
    }

    /**
     * Set Screen Protect By To Be Canceled
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setProtectByToBeCanceled(NWAL1770BMsg scrnMsg) {

        if (!SPLY_QUOTE_STS.SUBMITTED.equals(scrnMsg.splyQuoteStsCd.getValue())) {
            String cancelStsTxt = ZYPCodeDataUtil.getVarCharConstValue(KEY_QUOTE_CANCELLED, scrnMsg.glblCmpyCd.getValue());
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(i);

                if (!cancelStsTxt.equals(itemLineMsg.splyQuoteStsDescTxt_B.getValue())) {
                    continue;
                }

                itemLineMsg.mdseCd_LK.clear();
                itemLineMsg.mdseCd_B.setInputProtected(true);
                itemLineMsg.ordCustUomQty_B.setInputProtected(true);
                itemLineMsg.custUomCd_B.setInputProtected(true);
                // Add Start 2017/10/26 QC#21323
                itemLineMsg.custMdseCd_B.setInputProtected(true);
                // Add End 2017/10/26 QC#21323
                itemLineMsg.dealSplyQuoteDtlSlsAmt_B.setInputProtected(true);
                itemLineMsg.prcCatgNm_B.setInputProtected(true);
                itemLineMsg.prcCatgNm_BL.clear();
                itemLineMsg.supdLockFlg_B.setInputProtected(true);
                itemLineMsg.dsOrdLineCatgCd_B.setInputProtected(true);
                itemLineMsg.ordLineSrcCd_B.setInputProtected(true);
                itemLineMsg.rtlWhNm_B.setInputProtected(true);
                itemLineMsg.rtlWhNm_LK.clear();
                itemLineMsg.rtlSwhNm_B.setInputProtected(true);
                itemLineMsg.rtlSwhNm_LK.clear();
            }
        }
    }

    /**
     * Set Screen Protect By Drop Ship Available Flag
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setProtectByDropShipAvalFlg(NWAL1770BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.dropShipAvalFlg) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.dropShipAvalFlg.getValue())) {
            scrnMsg.dropShipFlg_LK.setInputProtected(false);
            scrnMsg.dropShipFlg_LK.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.dropShipFlg_LK.setInputProtected(true);
            scrnMsg.dropShipFlg_LK.clear();
        }
    }

    // QC#17474 2017/02/21 Add Start
    /**
     * Set Screen Protect By Bill To Infomation
     * @param scrnMsg NWAL1770BMsg
     */
    public static void setProtectByBillTo( NWAL1770BMsg scrnMsg) {
        // Protect Item by billTo Credit Profile
        if (!scrnMsg.billToCustAcctCd.isInputProtected()) {
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, scrnMsg.ovrdPmtTermFlg.getValue())) {
                scrnMsg.pmtTermCashDiscDescTxt.setInputProtected(false);
                scrnMsg.pmtTermCashDiscDescTxt_LK.setInputProtected(false);
            } else {
                scrnMsg.pmtTermCashDiscDescTxt.setInputProtected(true);
                scrnMsg.pmtTermCashDiscDescTxt_LK.setInputProtected(true);
            }
        }
    }
    /**
     * Control Merchandise Field
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setProtectByMdse(NWAL1770BMsg scrnMsg) {

        String splyQuoteStsCd = scrnMsg.splyQuoteStsCd.getValue();

        if (!ZYPCommonFunc.hasValue(splyQuoteStsCd) || SPLY_QUOTE_STS.SAVED.equals(splyQuoteStsCd)) {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(i);
                if (ZYPCommonFunc.hasValue(itemLineMsg.mdseCd_B)) {
                    itemLineMsg.mdseCd_B.setInputProtected(true);
                    itemLineMsg.mdseCd_LK.clear();
                } else {
                    itemLineMsg.mdseCd_B.setInputProtected(false);
                    itemLineMsg.mdseCd_LK.setValue(ZYPConstant.FLG_ON_Y);
                }
            }
        }
    }

    /**
     * Set Screen Protect By Status
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setProtectByStatus(EZDCommonHandler handler, NWAL1770BMsg scrnMsg) {

        // set Header Protect
        setProtectByHdrSts(handler, scrnMsg);

        // set Item Line Protect
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        if (TAB_ITEMS.equals(dplyTab)) {
            setProtectByItemLineSts(handler, scrnMsg);
        }
    }

    /**
     * Set Screen Protect By Authority
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setProtectByAuthority(EZDCommonHandler handler, NWAL1770BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            String funcId = scrnMsg.Z.no(i).xxFuncId.getValue();

            // Only Reference Authority
            if (scrnMsg.Z.getValidCount() == 1 && REF_AUTH.equals(funcId)) {
                NWAL1770CommonLogic.inactiveRegistrationButton(handler);
            }

            // Registration Authority
            if (ALL_RGTN_AUTH.equals(funcId)) {
                if (!SPLY_QUOTE_STS.SUBMITTED.equals(scrnMsg.splyQuoteStsCd.getValue())) {
                    setEnableByAllRgtnAuth(handler, scrnMsg);
                }
            }
        }
    }

    /**
     * Set Enable By All Registration Authority
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1770BMsg
     */
    public static void setEnableByAllRgtnAuth(EZDCommonHandler handler, NWAL1770BMsg scrnMsg) {

        String cancelStsTxt = ZYPCodeDataUtil.getVarCharConstValue(KEY_QUOTE_CANCELLED, scrnMsg.glblCmpyCd.getValue());

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(i);
            String splyQuoteLineStsCd = itemLineMsg.splyQuoteLineStsCd_B.getValue();

            if (cancelStsTxt.equals(itemLineMsg.splyQuoteStsDescTxt_B.getValue()) || ZYPCommonFunc.hasValue(itemLineMsg.dplyQuoteLineSubNum_B)) {
                continue;
            }

            if (!ZYPCommonFunc.hasValue(splyQuoteLineStsCd) || SPLY_QUOTE_STS.SAVED.equals(splyQuoteLineStsCd)) {
// S21_NA#13173 Add START
                if (ZYPConstant.FLG_ON_Y.equals(itemLineMsg.xxErrFlg_B.getValue())) {
                    itemLineMsg.dealSplyQuoteDtlSlsAmt_B.setInputProtected(true);
                } else {
// S21_NA#13173 Add END
                    itemLineMsg.dealSplyQuoteDtlSlsAmt_B.setInputProtected(false);
                } // S21_NA#13173 Add
            }
        }
    }

    /**
     * Set Screen Protect By Header Status
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setProtectByHdrSts(EZDCommonHandler handler, NWAL1770BMsg scrnMsg) {

        final String hdrStsCd = scrnMsg.splyQuoteStsCd.getValue();

        if (ZYPCommonFunc.hasValue(hdrStsCd)) {
            if (SPLY_QUOTE_STS.SAVED.equals(hdrStsCd)) {
                setProtectHdrStsForSaved(handler, scrnMsg);
            } else if (SPLY_QUOTE_STS.SUBMITTED.equals(hdrStsCd)) {
                setProtectHdrStsForSubmitted(handler, scrnMsg);
            } else {
                setProtectHdrStsForCancelled(handler, scrnMsg);
            }
        } else {
            setProtectHdrStsForUnregistered(handler, scrnMsg);
        }
    }

    /**
     * Set Screen Protect By Item Line Status
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setProtectByItemLineSts(EZDCommonHandler handler, NWAL1770BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(i);
            final String lineStsCd = itemLineMsg.splyQuoteLineStsCd_B.getValue();

            if (ZYPCommonFunc.hasValue(lineStsCd)) {
                if (SPLY_QUOTE_STS.SAVED.equals(lineStsCd)) {
                    setLineItemProtectForSaved(itemLineMsg);
                } else if (SPLY_QUOTE_STS.SUBMITTED.equals(lineStsCd)) {
                    setLineItemProtectForSubmitted(itemLineMsg);
                } else {
                    setLineItemProtectForCancelled(itemLineMsg);
                }
            } else {
                setLineItemProtectForUnregistered(itemLineMsg);
            }
        }
    }

    /**
     * Screen protecting control [Header Status : Unregistered]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setProtectHdrStsForUnregistered(EZDCommonHandler handler, NWAL1770BMsg scrnMsg) {

        // Header
        setHdrItemProtectForUnregistered(scrnMsg);
        setHdrBtnProtectForUnregistered(handler);

        // Customer Tab
        setCustTabBtnProtectForUnregistered(handler);

        // Delivery / Payment Tab
        setDelyPmtTabBtnProtectForUnregistered(handler);

        // Item Tab
        setItemTabBtnProtectForUnregistered(handler);

        // Additional Tab
        setAddlTabBtnProtectForUnregistered(handler);
    }

    /**
     * Screen protecting control [Header Status : Saved]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setProtectHdrStsForSaved(EZDCommonHandler handler, NWAL1770BMsg scrnMsg) {

        // Header
        setHdrItemProtectForSaved(scrnMsg);
        setHdrBtnProtectForSaved(handler);

        // Customer Tab
        setCustTabBtnProtectForSaved(handler);
    }

    /**
     * Screen protecting control [Header Status : Submitted]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setProtectHdrStsForSubmitted(EZDCommonHandler handler, NWAL1770BMsg scrnMsg) {

        // Header
        setHdrItemProtectForSubmitted(scrnMsg);
        setHdrBtnProtectForSubmitted(handler);

        // Customer Tab
        setCustTabItemProtectForSubmitted(scrnMsg);
        setCustTabBtnProtectForSubmitted(handler);

        // Delivery / Payment Tab
        setDelyPmtTabItemProtectForSubmitted(scrnMsg);
        setDelyPmtTabBtnProtectForSubmitted(handler);

        // Comments Tab
        setCommentTabItemProtectForSubmitted(scrnMsg);

        // Item Tab
        setItemTabBtnProtectForSubmitted(handler);

        // Additional Tab
        setAddlTabItemProtectForSubmitted(scrnMsg);

        // Common
        NWAL1770CommonLogic.inactiveRegistrationButton(handler);
    }

    /**
     * Screen protecting control [Header Status : Cancelled]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setProtectHdrStsForCancelled(EZDCommonHandler handler, NWAL1770BMsg scrnMsg) {

        // Header
        setHdrItemProtectForCancelled(scrnMsg);
        setHdrBtnProtectForCancelled(handler);

        // Customer Tab
        setCustTabItemProtectForCancelled(scrnMsg);
        setCustTabBtnProtectForCancelled(handler);

        // Delivery / Payment Tab
        setDelyPmtTabItemProtectForCancelled(scrnMsg);
        setDelyPmtTabBtnProtectForCancelled(handler);

        // Comments Tab
        setCommentTabItemProtectForCancelled(scrnMsg);

        // Item Tab
        setItemTabBtnProtectForCancelled(handler);

        // Additional Tab
        setAddlTabItemProtectForCancelled(scrnMsg);

        // Common
        NWAL1770CommonLogic.inactiveRegistrationButton(handler);
    }

    /**
     * Set All Active Fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setAllActiveFields(EZDCommonHandler handler, NWAL1770BMsg scrnMsg) {

        // Header
        scrnMsg.splyQuoteNum.setInputProtected(false);
        scrnMsg.splyQuoteNm.setInputProtected(false); // 2018/03/02 S21_NA#22956 Add
        scrnMsg.dsOrdCatgDescTxt_LK.setInputProtected(false);
        scrnMsg.dsOrdCatgDescTxt.setInputProtected(false);
        scrnMsg.dsOrdTpCd.setInputProtected(false);
        scrnMsg.splyQuoteSrcTpCd.setInputProtected(false);
        scrnMsg.splyQuoteDt.setInputProtected(false);
        scrnMsg.splyQuoteVldDaysAot.setInputProtected(false);
        scrnMsg.splyQuoteVldThruDt.setInputProtected(true);
        scrnMsg.splyQuoteStsDescTxt.setInputProtected(true);
        scrnMsg.custIssPoNum.setInputProtected(false);
        scrnMsg.custIssPoDt.setInputProtected(false);
        scrnMsg.slsRepTocNm_LK.setInputProtected(false);
        scrnMsg.slsRepTocNm.setInputProtected(false);
        // S21_NA#7861 Mod Start
        // scrnMsg.slsRepPsnCd_LK.setInputProtected(false);
        // scrnMsg.slsRepPsnCd.setInputProtected(false);
        scrnMsg.psnNum_LK.setInputProtected(false);
        scrnMsg.psnNum.setInputProtected(false);
        // S21_NA#7861 Mod End
        scrnMsg.xxScrItem54Txt_CB.setInputProtected(true);
        scrnMsg.xxScrItem54Txt_CE.setInputProtected(true);
        scrnMsg.prcCatgNm_LK.setInputProtected(false);
        scrnMsg.prcCatgNm.setInputProtected(false);
        scrnMsg.cpoOrdNum_LK.setInputProtected(false);
        scrnMsg.xxSubTotCalcPrcAmt.setInputProtected(true);
        scrnMsg.xxTotChrgPrcAmt.setInputProtected(true);
        scrnMsg.xxTotTaxAmt.setInputProtected(true);
        scrnMsg.xxTotAmt.setInputProtected(true);
        handler.setButtonEnabled(BTN_QUOTE_SRCH_EVENT_NM, true);
        handler.setButtonEnabled(BTN_QUOTE_COPY_EVENT_NM, true);
        handler.setButtonEnabled(BTN_SLS_CREDIT_EVENT_NM, true);
        handler.setButtonEnabled(BTN_SHPG_DTL_EVENT_NM, true);
        handler.setButtonEnabled(BTN_ATT_EVENT_NM, true);
        handler.setButtonEnabled(BTN_CALC_QUOTE_AMT_EVENT_NM, true);

        // Customer Contact Tab
        scrnMsg.billToCustAcctCd.setInputProtected(false);
        scrnMsg.billToCustAcctCd_LK.setInputProtected(false);
        scrnMsg.billToCustCd.setInputProtected(false);
        scrnMsg.billToCustCd_LK.setInputProtected(false);
        scrnMsg.billToCustAcctNm.setInputProtected(false);
        scrnMsg.billToCustAcctNm_LK.setInputProtected(false);
        scrnMsg.xxAllLineAddr_BT.setInputProtected(true);
        scrnMsg.shipToCustAcctCd.setInputProtected(false);
        scrnMsg.shipToCustAcctCd_LK.setInputProtected(false);
        scrnMsg.shipToCustCd.setInputProtected(false);
        scrnMsg.shipToCustCd_LK.setInputProtected(false);
        scrnMsg.dropShipFlg_LK.setInputProtected(false);
        scrnMsg.shipToCustAcctNm.setInputProtected(false);
        scrnMsg.shipToCustAcctNm_LK.setInputProtected(false);
        scrnMsg.xxAllLineAddr_SH.setInputProtected(true);
        scrnMsg.sellToCustCd.setInputProtected(false);
        scrnMsg.sellToCustCd_LK.setInputProtected(false);
        scrnMsg.soldToCustLocCd.setInputProtected(false);
        scrnMsg.soldToCustLocCd_LK.setInputProtected(false);
        scrnMsg.soldToCustAcctNm.setInputProtected(false);
        scrnMsg.soldToCustAcctNm_LK.setInputProtected(false);
        scrnMsg.xxAllLineAddr_SE.setInputProtected(true);
        // Add Start 2017/10/13 QC#20246-1(L3 Sol#454)
        scrnMsg.sellToFirstRefCmntTxt.setInputProtected(false);
        // Add End 2017/10/13 QC#20246-1(L3 Sol#454)
        // 2017/11/27 S21_NA#21155 add start
        scrnMsg.shipToLocNm_DS.setInputProtected(true);
        // 2017/11/27 S21_NA#21155 add end

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1770_ABMsg contactMsg = scrnMsg.A.no(i);
            contactMsg.xxChkBox_A.setInputProtected(false);
            contactMsg.ctacPsnTpCd_A.setInputProtected(false);
            contactMsg.ctacPsnFirstNm_A.setInputProtected(false);
            contactMsg.ctacPsnFirstNm_LK.setInputProtected(false);
            contactMsg.ctacPsnLastNm_A.setInputProtected(false);
            contactMsg.ctacPsnTelNum_A.setInputProtected(false);
            contactMsg.ctacPsnExtnNum_A.setInputProtected(false);
            contactMsg.ctacPsnEmlAddr_A.setInputProtected(false);
            contactMsg.ctacPsnFaxNum_A.setInputProtected(false);
            contactMsg.ctacCustRefTpCd_A.setInputProtected(false);
        }

        handler.setButtonEnabled(BTN_SPCL_INSTN_EVENT_NM, true);
        handler.setButtonEnabled(BTN_CONF_EVENT_NM, true);
        // 2018/05/22 QC#22139-2 Mod Start
        //handler.setButtonEnabled(BTN_TRK_EVENT_NM, true);
        handler.setButtonEnabled(BTN_TRK_EVENT_NM, false);
        // 2018/05/22 QC#22139-2 Mod End
        handler.setButtonEnabled(BTN_ADD_CTAC_EVENT_NM, true);
        handler.setButtonEnabled(BTN_DEL_CTAC_EVENT_NM, true);

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        scrnMsg.crAnlstPsnNm.setInputProtected(true);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]

        // Delivery / Payment Tab
        scrnMsg.frtCondDescTxt_LK.setInputProtected(false);
        scrnMsg.frtCondDescTxt.setInputProtected(false);
        scrnMsg.carrSvcLvlDescTxt_LK.setInputProtected(true);
        scrnMsg.carrSvcLvlDescTxt_LK.clear();
        scrnMsg.carrSvcLvlDescTxt.setInputProtected(true);
        scrnMsg.shpgSvcLvlCd.setInputProtected(false);
        scrnMsg.carrAcctNum.setInputProtected(true);
        scrnMsg.spclHdlgTpCd.setInputProtected(false);
        scrnMsg.rddDt.setInputProtected(false);
        // Mod 2016/08/30 S21_NA#11547
        scrnMsg.pmtTermCashDiscDescTxt.setInputProtected(false);
        scrnMsg.pmtTermCashDiscDescTxt_LK.setInputProtected(false);
        //scrnMsg.pmtTermCashDiscDescTxt.setInputProtected(true);
        scrnMsg.dsPmtMethCd.setInputProtected(false);
        handler.setButtonEnabled(BTN_CREDIT_CARD_EVENT_NM, true);

        // Item Tab
        handler.setButtonEnabled(BTN_ADD_ITEM_EVENT_NM, true);
        handler.setButtonEnabled(BTN_CANC_EVENT_NM, true);
        handler.setButtonEnabled(BTN_PRFT_EVENT_NM, true);
        handler.setButtonEnabled(BTN_PRC_DTL_EVENT_NM, true);
        handler.setButtonEnabled(BTN_INVTY_INQ_EVENT_NM, true);
        handler.setButtonEnabled(BTN_CHK_CONTR_EVENT_NM, true);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(i);
            itemLineMsg.xxChkBox_B.setInputProtected(false);
            itemLineMsg.mdseCd_B.setInputProtected(false);
            itemLineMsg.mdseCd_LK.setInputProtected(false);
            itemLineMsg.mnfItemCd_B.setInputProtected(true);
            itemLineMsg.mdseDescShortTxt_B.setInputProtected(true);
            itemLineMsg.ordCustUomQty_B.setInputProtected(false);
            itemLineMsg.custUomCd_B.setInputProtected(false);
            itemLineMsg.dealSplyQuoteDtlSlsAmt_B.setInputProtected(false);
            itemLineMsg.dealPrcListPrcAmt_B.setInputProtected(true);
            itemLineMsg.xxDtlDiscAmt_B.setInputProtected(true);
            itemLineMsg.prcCatgNm_B.setInputProtected(false);
            itemLineMsg.prcCatgNm_BL.setInputProtected(false);
            itemLineMsg.rddDt_B.setInputProtected(false); // 2018/02/13 QC#21165 Add
            // QC#10347 2017/07/24 Add Start
            itemLineMsg.prcBaseDt_B.setInputProtected(false);
            // QC#10347 2017/07/24 Add End
            itemLineMsg.xxTotDiscAmt_B.setInputProtected(true);
            itemLineMsg.xxSubTotCalcPrcAmt_B.setInputProtected(true);
            itemLineMsg.xxTotFrtAmt_B.setInputProtected(true);
            itemLineMsg.xxTotTaxAmt_B.setInputProtected(true);
            itemLineMsg.xxTotAmt_B.setInputProtected(true);
            // Mod Start 2017/10/26 QC#21323
//            itemLineMsg.custMdseCd_B.setInputProtected(true);
            itemLineMsg.custMdseCd_B.setInputProtected(false);
            // Mod End 2017/10/26 QC#21323
            itemLineMsg.supdLockFlg_B.setInputProtected(false);
            itemLineMsg.dsOrdLineCatgCd_B.setInputProtected(false);
            itemLineMsg.ordLineSrcCd_B.setInputProtected(false);
            itemLineMsg.rtlWhNm_B.setInputProtected(false);
            itemLineMsg.rtlWhNm_LK.setInputProtected(false);
            itemLineMsg.rtlSwhNm_B.setInputProtected(false);
            itemLineMsg.rtlSwhNm_LK.setInputProtected(false);
            itemLineMsg.ordQty_B.setInputProtected(true);
            itemLineMsg.splyQuoteStsDescTxt_B.setInputProtected(true);
            // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            itemLineMsg.hazMatFlg_B.setInputProtected(true);
            // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        }

        // Comments Tab
        // 2018/05/11 QC#22139 Add Start
        scrnMsg.quotePrintCmntTxt.setInputProtected(false);
        scrnMsg.ordPrintCmntTxt.setInputProtected(false);
        scrnMsg.shpgCmntPrintCd.setInputProtected(false);
        // 2018/05/11 QC#22139 Add End
        scrnMsg.shpgCmntTxt.setInputProtected(false);
        scrnMsg.splyQuoteCmntTxt.setInputProtected(false);
        scrnMsg.invCmntTxt.setInputProtected(false);

        // Additional Data Tab
        scrnMsg.xxPsnNm_SV.setInputProtected(true);
        scrnMsg.xxTsDsp19Txt_SV.setInputProtected(true);
        scrnMsg.xxPsnNm_SB.setInputProtected(true);
        scrnMsg.xxTsDsp19Txt_SB.setInputProtected(true);
        scrnMsg.splyQuoteRptOtptLogPk.setInputProtected(true);
        scrnMsg.prcContrNum_LK.setInputProtected(false);
        scrnMsg.firstBllgAttrbNm.setInputProtected(true);
        scrnMsg.firstBllgAttrbValTxt.setInputProtected(false);
        scrnMsg.scdBllgAttrbNm.setInputProtected(true);
        scrnMsg.scdBllgAttrbValTxt.setInputProtected(false);
        scrnMsg.thirdBllgAttrbNm.setInputProtected(true);
        scrnMsg.thirdBllgAttrbValTxt.setInputProtected(false);
        scrnMsg.frthBllgAttrbNm.setInputProtected(true);
        scrnMsg.frthBllgAttrbValTxt.setInputProtected(false);
        scrnMsg.fifthBllgAttrbNm.setInputProtected(true);
        scrnMsg.fifthBllgAttrbValTxt.setInputProtected(false);
        scrnMsg.sixthBllgAttrbNm.setInputProtected(true);
        scrnMsg.sixthBllgAttrbValTxt.setInputProtected(false);
        scrnMsg.prcContrNum.setInputProtected(false);
        scrnMsg.dsAcctClsDescTxt.setInputProtected(true);
        scrnMsg.xxScrItem54Txt_GL.setInputProtected(true);
        handler.setButtonEnabled(BTN_VIEW_CHNG_LOG_EVENT_NM, true);

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            NWAL1770_CBMsg addlMsg = scrnMsg.C.no(i);
            addlMsg.mdseCd_C.setInputProtected(true);
            addlMsg.mdseDescShortTxt_C.setInputProtected(true);
            addlMsg.xxTotUnitNetWt_C.setInputProtected(true);
            addlMsg.coaMdseTpDescTxt_C.setInputProtected(true);
            addlMsg.splyQuoteStsDescTxt_C.setInputProtected(true);
            addlMsg.coaProdDescTxt_C.setInputProtected(true);
            addlMsg.zerothProdCtrlNm_C.setInputProtected(true);
            addlMsg.firstProdCtrlNm_C.setInputProtected(true);
            addlMsg.scdProdCtrlNm_C.setInputProtected(true);
            addlMsg.thirdProdCtrlNm_C.setInputProtected(true);
            addlMsg.frthProdCtrlNm_C.setInputProtected(true);
        }

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            NWAL1770_HBMsg ordHistMsg = scrnMsg.H.no(i);

            ordHistMsg.mdseCd_H.setInputProtected(true);
            ordHistMsg.mnfItemCd_H.setInputProtected(true);
            ordHistMsg.mdseDescShortTxt_H.setInputProtected(true);
            ordHistMsg.ordCustUomQty_H.setInputProtected(true);
        }
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
}

    /**
     * Set Header Item Protect [Header Status : Unregistered]
     * @param lineMsg NWAL1770_BBMsg
     */
    private static void setHdrItemProtectForUnregistered(NWAL1770BMsg scrnMsg) {

        scrnMsg.cpoOrdNum_LK.setInputProtected(true);
        scrnMsg.cpoOrdNum_LK.clear();
    }

    /**
     * Set Header Item Protect [Header Status : Saved]
     * @param lineMsg NWAL1770_BBMsg
     */
    private static void setHdrItemProtectForSaved(NWAL1770BMsg scrnMsg) {

        scrnMsg.cpoOrdNum_LK.setInputProtected(true);
        scrnMsg.cpoOrdNum_LK.clear();
    }

    /**
     * Set Header Item Protect [Header Status : Submitted]
     * @param lineMsg NWAL1770_BBMsg
     */
    private static void setHdrItemProtectForSubmitted(NWAL1770BMsg scrnMsg) {

        scrnMsg.dsOrdCatgDescTxt.setInputProtected(true);
        scrnMsg.dsOrdCatgDescTxt_LK.clear();
        scrnMsg.dsOrdTpCd.setInputProtected(true);
        scrnMsg.splyQuoteSrcTpCd.setInputProtected(true);
        scrnMsg.splyQuoteDt.setInputProtected(true);
        scrnMsg.splyQuoteVldDaysAot.setInputProtected(true);
        scrnMsg.custIssPoNum.setInputProtected(true);
        scrnMsg.custIssPoDt.setInputProtected(true);
        scrnMsg.slsRepTocNm.setInputProtected(true);
        scrnMsg.slsRepTocNm_LK.clear();
        // S21_NA#7861 Mod Start
        // scrnMsg.slsRepPsnCd.setInputProtected(true);
        // scrnMsg.slsRepPsnCd_LK.clear();
        scrnMsg.psnNum.setInputProtected(true);
        scrnMsg.psnNum_LK.clear();
        // S21_NA#7861 Mod End
        scrnMsg.prcCatgNm.setInputProtected(true);
        scrnMsg.prcCatgNm_LK.clear();
        scrnMsg.splyQuoteNm.setInputProtected(true); // 2018/03/02 S21_NA#22956 Add
    }

    /**
     * Set Header Item Protect [Header Status : Cancelled]
     * @param lineMsg NWAL1770_BBMsg
     */
    private static void setHdrItemProtectForCancelled(NWAL1770BMsg scrnMsg) {

        scrnMsg.dsOrdCatgDescTxt.setInputProtected(true);
        scrnMsg.dsOrdCatgDescTxt_LK.clear();
        scrnMsg.dsOrdTpCd.setInputProtected(true);
        scrnMsg.splyQuoteSrcTpCd.setInputProtected(true);
        scrnMsg.splyQuoteDt.setInputProtected(true);
        scrnMsg.splyQuoteVldDaysAot.setInputProtected(true);
        scrnMsg.custIssPoNum.setInputProtected(true);
        scrnMsg.custIssPoDt.setInputProtected(true);
        scrnMsg.slsRepTocNm.setInputProtected(true);
        scrnMsg.slsRepTocNm_LK.clear();
        // S21_NA#7861 Mod Start
        // scrnMsg.slsRepPsnCd.setInputProtected(true);
        // scrnMsg.slsRepPsnCd_LK.clear();
        scrnMsg.psnNum.setInputProtected(true);
        scrnMsg.psnNum_LK.clear();
        // S21_NA#7861 Mod End
        scrnMsg.prcCatgNm.setInputProtected(true);
        scrnMsg.prcCatgNm_LK.clear();
        scrnMsg.cpoOrdNum_LK.setInputProtected(true);
        scrnMsg.cpoOrdNum_LK.clear();
        scrnMsg.splyQuoteNm.setInputProtected(true); // 2018/03/02 S21_NA#22956 Add
    }

    /**
     * Set Header Button Protect [Header Status : Unregistered]
     * @param handler EZDCommonHandler
     */
    private static void setHdrBtnProtectForUnregistered(EZDCommonHandler handler) {

        handler.setButtonEnabled(BTN_ATT_EVENT_NM, false);
        handler.setButtonEnabled(BTN_SHPG_DTL_EVENT_NM, false);
    }

    /**
     * Set Header Button Protect [Header Status : Saved]
     * @param handler EZDCommonHandler
     */
    private static void setHdrBtnProtectForSaved(EZDCommonHandler handler) {

        handler.setButtonEnabled(BTN_SHPG_DTL_EVENT_NM, false);
    }

    /**
     * Set Header Button Protect [Header Status : Submitted]
     * @param handler EZDCommonHandler
     */
    private static void setHdrBtnProtectForSubmitted(EZDCommonHandler handler) {

        handler.setButtonEnabled(BTN_CALC_QUOTE_AMT_EVENT_NM, false);
    }

    /**
     * Set Header Button Protect [Header Status : Cancelled]
     * @param handler EZDCommonHandler
     */
    private static void setHdrBtnProtectForCancelled(EZDCommonHandler handler) {

        handler.setButtonEnabled(BTN_SHPG_DTL_EVENT_NM, false);
        handler.setButtonEnabled(BTN_CALC_QUOTE_AMT_EVENT_NM, false);
    }

    /**
     * Set Customer Tab Item Protect [Header Status : Submitted]
     * @param lineMsg NWAL1770_BBMsg
     */
    private static void setCustTabItemProtectForSubmitted(NWAL1770BMsg scrnMsg) {

        scrnMsg.billToCustAcctCd.setInputProtected(true);
        scrnMsg.billToCustAcctCd_LK.clear();
        scrnMsg.billToCustCd.setInputProtected(true);
        scrnMsg.billToCustCd_LK.clear();
        scrnMsg.billToCustAcctNm.setInputProtected(true);
        scrnMsg.billToCustAcctNm_LK.clear();
        scrnMsg.shipToCustAcctCd.setInputProtected(true);
        scrnMsg.shipToCustAcctCd_LK.clear();
        scrnMsg.shipToCustCd.setInputProtected(true);
        scrnMsg.shipToCustCd_LK.clear();
        scrnMsg.shipToCustAcctNm.setInputProtected(true);
        scrnMsg.shipToCustAcctNm_LK.clear();
        scrnMsg.sellToCustCd.setInputProtected(true);
        scrnMsg.sellToCustCd_LK.clear();
        scrnMsg.soldToCustLocCd.setInputProtected(true);
        scrnMsg.soldToCustLocCd_LK.clear();
        scrnMsg.soldToCustAcctNm.setInputProtected(true);
        scrnMsg.soldToCustAcctNm_LK.clear();
        // Add Start 2017/10/13 QC#20246-1(L3 Sol#454)
        scrnMsg.sellToFirstRefCmntTxt.setInputProtected(true);
        // Add End 2017/10/13 QC#20246-1(L3 Sol#454)

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1770_ABMsg ctacLineMsg = scrnMsg.A.no(i);
            ctacLineMsg.xxChkBox_A.setInputProtected(true);
            ctacLineMsg.ctacPsnTpCd_A.setInputProtected(true);
            ctacLineMsg.ctacPsnFirstNm_A.setInputProtected(true);
            ctacLineMsg.ctacPsnFirstNm_LK.clear();
            ctacLineMsg.ctacPsnLastNm_A.setInputProtected(true);
            ctacLineMsg.ctacPsnTelNum_A.setInputProtected(true);
            ctacLineMsg.ctacPsnExtnNum_A.setInputProtected(true);
            ctacLineMsg.ctacPsnEmlAddr_A.setInputProtected(true);
            ctacLineMsg.ctacPsnFaxNum_A.setInputProtected(true);
        }
    }

    /**
     * Set Customer Tab Item Protect [Header Status : Cancelled]
     * @param lineMsg NWAL1770_BBMsg
     */
    private static void setCustTabItemProtectForCancelled(NWAL1770BMsg scrnMsg) {

        scrnMsg.billToCustAcctCd.setInputProtected(true);
        scrnMsg.billToCustAcctCd_LK.clear();
        scrnMsg.billToCustCd.setInputProtected(true);
        scrnMsg.billToCustCd_LK.clear();
        scrnMsg.billToCustAcctNm.setInputProtected(true);
        scrnMsg.billToCustAcctNm_LK.clear();
        scrnMsg.shipToCustAcctCd.setInputProtected(true);
        scrnMsg.shipToCustAcctCd_LK.clear();
        scrnMsg.shipToCustCd.setInputProtected(true);
        scrnMsg.shipToCustCd_LK.clear();
        scrnMsg.shipToCustAcctNm.setInputProtected(true);
        scrnMsg.shipToCustAcctNm_LK.clear();
        scrnMsg.sellToCustCd.setInputProtected(true);
        scrnMsg.sellToCustCd_LK.clear();
        scrnMsg.soldToCustLocCd.setInputProtected(true);
        scrnMsg.soldToCustLocCd_LK.clear();
        scrnMsg.soldToCustAcctNm.setInputProtected(true);
        scrnMsg.soldToCustAcctNm_LK.clear();
        // Add Start 2017/10/13 QC#20246-1(L3 Sol#454)
        scrnMsg.sellToFirstRefCmntTxt.setInputProtected(true);
        // Add End 2017/10/13 QC#20246-1(L3 Sol#454)

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1770_ABMsg ctacLineMsg = scrnMsg.A.no(i);
            ctacLineMsg.xxChkBox_A.setInputProtected(true);
            ctacLineMsg.ctacPsnTpCd_A.setInputProtected(true);
            ctacLineMsg.ctacPsnFirstNm_A.setInputProtected(true);
            ctacLineMsg.ctacPsnFirstNm_LK.clear();
            ctacLineMsg.ctacPsnLastNm_A.setInputProtected(true);
            ctacLineMsg.ctacPsnTelNum_A.setInputProtected(true);
            ctacLineMsg.ctacPsnExtnNum_A.setInputProtected(true);
            ctacLineMsg.ctacPsnEmlAddr_A.setInputProtected(true);
            ctacLineMsg.ctacPsnFaxNum_A.setInputProtected(true);
        }
    }

    /**
     * Set Customer Tab Button Protect [Header Status : Unregistered]
     * @param handler EZDCommonHandler
     */
    private static void setCustTabBtnProtectForUnregistered(EZDCommonHandler handler) {

        handler.setButtonEnabled(BTN_CONF_EVENT_NM, false);
        handler.setButtonEnabled(BTN_TRK_EVENT_NM, false);
    }

    /**
     * Set Customer Tab Button Protect [Header Status : Saved]
     * @param handler EZDCommonHandler
     */
    private static void setCustTabBtnProtectForSaved(EZDCommonHandler handler) {

        handler.setButtonEnabled(BTN_TRK_EVENT_NM, false);
    }

    /**
     * Set Customer Tab Button Protect [Header Status : Submitted]
     * @param handler EZDCommonHandler
     */
    private static void setCustTabBtnProtectForSubmitted(EZDCommonHandler handler) {

        handler.setButtonEnabled(BTN_SPCL_INSTN_EVENT_NM, false);
        handler.setButtonEnabled(BTN_ADD_CTAC_EVENT_NM, false);
        handler.setButtonEnabled(BTN_DEL_CTAC_EVENT_NM, false);
    }

    /**
     * Set Customer Tab Button Protect [Header Status : Cancelled]
     * @param handler EZDCommonHandler
     */
    private static void setCustTabBtnProtectForCancelled(EZDCommonHandler handler) {

        handler.setButtonEnabled(BTN_SPCL_INSTN_EVENT_NM, false);
        handler.setButtonEnabled(BTN_CONF_EVENT_NM, false);
        handler.setButtonEnabled(BTN_TRK_EVENT_NM, false);
        handler.setButtonEnabled(BTN_ADD_CTAC_EVENT_NM, false);
        handler.setButtonEnabled(BTN_DEL_CTAC_EVENT_NM, false);
    }

    /**
     * Set Delivery / Payment Tab Item Protect [Header Status : Submitted]
     * @param lineMsg NWAL1770_BBMsg
     */
    private static void setDelyPmtTabItemProtectForSubmitted(NWAL1770BMsg scrnMsg) {

        scrnMsg.frtCondDescTxt.setInputProtected(true);
        scrnMsg.frtCondDescTxt_LK.clear();
        scrnMsg.carrSvcLvlDescTxt.setInputProtected(true);
        scrnMsg.carrSvcLvlDescTxt_LK.clear();
        scrnMsg.spclHdlgTpCd.setInputProtected(true);
        scrnMsg.shpgSvcLvlCd.setInputProtected(true);
        scrnMsg.carrAcctNum.setInputProtected(true);
        scrnMsg.rddDt.setInputProtected(true);
        scrnMsg.dsPmtMethCd.setInputProtected(true);
        // Add 2016/08/30 S21_NA#11547
        scrnMsg.pmtTermCashDiscDescTxt.setInputProtected(true);
        scrnMsg.pmtTermCashDiscDescTxt_LK.setInputProtected(true);
        scrnMsg.pmtTermCashDiscDescTxt_LK.clear();
    }

    /**
     * Set Delivery / Payment Tab Item Protect [Header Status : Cancelled]
     * @param lineMsg NWAL1770_BBMsg
     */
    private static void setDelyPmtTabItemProtectForCancelled(NWAL1770BMsg scrnMsg) {

        scrnMsg.frtCondDescTxt.setInputProtected(true);
        scrnMsg.frtCondDescTxt_LK.clear();
        scrnMsg.carrSvcLvlDescTxt.setInputProtected(true);
        scrnMsg.carrSvcLvlDescTxt_LK.clear();
        scrnMsg.spclHdlgTpCd.setInputProtected(true);
        scrnMsg.shpgSvcLvlCd.setInputProtected(true);
        scrnMsg.carrAcctNum.setInputProtected(true);
        scrnMsg.rddDt.setInputProtected(true);
        scrnMsg.dsPmtMethCd.setInputProtected(true);
        // Add 2016/08/30 S21_NA#11547
        scrnMsg.pmtTermCashDiscDescTxt.setInputProtected(true);
        scrnMsg.pmtTermCashDiscDescTxt_LK.setInputProtected(true);
        scrnMsg.pmtTermCashDiscDescTxt_LK.clear();
    }

    /**
     * Set Delivery / Payment Tab Button Protect [Header Status : Unregistered]
     * @param handler EZDCommonHandler
     */
    private static void setDelyPmtTabBtnProtectForUnregistered(EZDCommonHandler handler) {

        handler.setButtonEnabled(BTN_CREDIT_CARD_EVENT_NM, false);
    }

    /**
     * Set Delivery / Payment Tab Button Protect [Header Status : Submitted]
     * @param handler EZDCommonHandler
     */
    private static void setDelyPmtTabBtnProtectForSubmitted(EZDCommonHandler handler) {

        // 2016/10/03 S21_NA#13958 Mod START
        //handler.setButtonEnabled(BTN_CREDIT_CARD_EVENT_NM, false);
        handler.setButtonEnabled(BTN_CREDIT_CARD_EVENT_NM, true);
        // 2016/10/03 S21_NA#13958 Mod End
    }

    /**
     * Set Delivery / Payment Tab Button Protect [Header Status : Cancelled]
     * @param handler EZDCommonHandler
     */
    private static void setDelyPmtTabBtnProtectForCancelled(EZDCommonHandler handler) {

        handler.setButtonEnabled(BTN_CREDIT_CARD_EVENT_NM, false);
    }

    /**
     * Set Comments Tab Item Protect [Header Status : Submitted]
     * @param lineMsg NWAL1770_BBMsg
     */
    private static void setCommentTabItemProtectForSubmitted(NWAL1770BMsg scrnMsg) {

        // 2018/05/11 QC#22139 Add Start
        scrnMsg.quotePrintCmntTxt.setInputProtected(true);
        scrnMsg.ordPrintCmntTxt.setInputProtected(true);
        scrnMsg.shpgCmntPrintCd.setInputProtected(true);
        // 2018/05/11 QC#22139 Add End
        scrnMsg.shpgCmntTxt.setInputProtected(true);
        scrnMsg.splyQuoteCmntTxt.setInputProtected(true);
        scrnMsg.invCmntTxt.setInputProtected(true);
    }

    /**
     * Set Comments Tab Item Protect [Header Status : Cancelled]
     * @param lineMsg NWAL1770_BBMsg
     */
    private static void setCommentTabItemProtectForCancelled(NWAL1770BMsg scrnMsg) {

        scrnMsg.shpgCmntTxt.setInputProtected(true);
        scrnMsg.splyQuoteCmntTxt.setInputProtected(true);
        scrnMsg.invCmntTxt.setInputProtected(true);
    }

    /**
     * Set Item Tab Button Protect [Header Status : Unregistered]
     * @param handler EZDCommonHandler
     */
    private static void setItemTabBtnProtectForUnregistered(EZDCommonHandler handler) {

        handler.setButtonEnabled(BTN_PRFT_EVENT_NM, false);
    }

    /**
     * Set Item Tab Button Protect [Header Status : Submitted]
     * @param handler EZDCommonHandler
     */
    private static void setItemTabBtnProtectForSubmitted(EZDCommonHandler handler) {

        handler.setButtonEnabled(BTN_ADD_ITEM_EVENT_NM, false);
        handler.setButtonEnabled(BTN_CANC_EVENT_NM, false);
        //START 2017/08.01 E.Kameishi [QC#20295,ADD]
        inactiveAddButton(handler);
        //END 2017/08.01 E.Kameishi [QC#20295,ADD]
    }

    /**
     * Set Item Tab Button Protect [Header Status : Cancelled]
     * @param handler EZDCommonHandler
     */
    private static void setItemTabBtnProtectForCancelled(EZDCommonHandler handler) {

        handler.setButtonEnabled(BTN_ADD_ITEM_EVENT_NM, false);
        handler.setButtonEnabled(BTN_CANC_EVENT_NM, false);
        //START 2017/08.01 E.Kameishi [QC#20295,ADD]
        inactiveAddButton(handler);
        //END 2017/08.01 E.Kameishi [QC#20295,ADD]
    }

    /**
     * Set Additional Tab Button Protect [Header Status : Unregistered]
     * @param handler EZDCommonHandler
     */
    private static void setAddlTabBtnProtectForUnregistered(EZDCommonHandler handler) {

        handler.setButtonEnabled(BTN_VIEW_CHNG_LOG_EVENT_NM, false);
    }

    /**
     * Set Additional Tab Item Protect [Header Status : Submitted]
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setAddlTabItemProtectForSubmitted(NWAL1770BMsg scrnMsg) {

        scrnMsg.firstBllgAttrbValTxt.setInputProtected(true);
        scrnMsg.scdBllgAttrbValTxt.setInputProtected(true);
        scrnMsg.thirdBllgAttrbValTxt.setInputProtected(true);
        scrnMsg.frthBllgAttrbValTxt.setInputProtected(true);
        scrnMsg.fifthBllgAttrbValTxt.setInputProtected(true);
        scrnMsg.sixthBllgAttrbValTxt.setInputProtected(true);
        scrnMsg.prcContrNum.setInputProtected(true);
        scrnMsg.prcContrNum_LK.clear();
    }

    /**
     * Set Additional Tab Item Protect [Header Status : Cancelled]
     * @param scrnMsg NWAL1770BMsg
     */
    private static void setAddlTabItemProtectForCancelled(NWAL1770BMsg scrnMsg) {

        scrnMsg.firstBllgAttrbValTxt.setInputProtected(true);
        scrnMsg.scdBllgAttrbValTxt.setInputProtected(true);
        scrnMsg.thirdBllgAttrbValTxt.setInputProtected(true);
        scrnMsg.frthBllgAttrbValTxt.setInputProtected(true);
        scrnMsg.fifthBllgAttrbValTxt.setInputProtected(true);
        scrnMsg.sixthBllgAttrbValTxt.setInputProtected(true);
        scrnMsg.prcContrNum.setInputProtected(true);
        scrnMsg.prcContrNum_LK.clear();
    }

    /**
     * Set Line Item Protect [Line Status : Unregistered]
     * @param lineMsg NWAL1770_BBMsg
     */
    private static void setLineItemProtectForUnregistered(NWAL1770_BBMsg itemLineMsg) {

        itemLineMsg.dealSplyQuoteDtlSlsAmt_B.setInputProtected(true);
    }

    /**
     * Set Line Item Protect [Line Status : Saved]
     * @param lineMsg NWAL1770_BBMsg
     */
    private static void setLineItemProtectForSaved(NWAL1770_BBMsg itemLineMsg) {

        itemLineMsg.dealSplyQuoteDtlSlsAmt_B.setInputProtected(true);
    }

    /**
     * Set Line Item Protect [Line Status : Submitted]
     * @param lineMsg NWAL1770_BBMsg
     */
    private static void setLineItemProtectForSubmitted(NWAL1770_BBMsg itemLineMsg) {

        itemLineMsg.mdseCd_LK.clear();
        itemLineMsg.mdseCd_B.setInputProtected(true);
        itemLineMsg.ordCustUomQty_B.setInputProtected(true);
        itemLineMsg.custUomCd_B.setInputProtected(true);
        itemLineMsg.dealSplyQuoteDtlSlsAmt_B.setInputProtected(true);
        itemLineMsg.prcCatgNm_B.setInputProtected(true);
        itemLineMsg.prcCatgNm_BL.clear();
        // 2018/02/13 QC#21165 Add Start
        itemLineMsg.rddDt_B.setInputProtected(true);
        // 2018/02/13 QC#21165 Add End
        // QC#10347 2017/07/24 Add Start
        itemLineMsg.prcBaseDt_B.setInputProtected(true);
        // QC#10347 2017/07/24 Add End
        // Add Start 2017/10/26 QC#21323
        itemLineMsg.custMdseCd_B.setInputProtected(true);
        // Add End 2017/10/26 QC#21323
        itemLineMsg.supdLockFlg_B.setInputProtected(true);
        itemLineMsg.dsOrdLineCatgCd_B.setInputProtected(true);
        itemLineMsg.ordLineSrcCd_B.setInputProtected(true);
        itemLineMsg.rtlWhNm_B.setInputProtected(true);
        itemLineMsg.rtlWhNm_LK.clear();
        itemLineMsg.rtlSwhNm_B.setInputProtected(true);
        itemLineMsg.rtlSwhNm_LK.clear();
    }

    /**
     * Set Line Item Protect [Line Status : Cancelled]
     * @param lineMsg NWAL1770_BBMsg
     */
    private static void setLineItemProtectForCancelled(NWAL1770_BBMsg itemLineMsg) {

        itemLineMsg.mdseCd_LK.clear();
        itemLineMsg.mdseCd_B.setInputProtected(true);
        itemLineMsg.ordCustUomQty_B.setInputProtected(true);
        itemLineMsg.custUomCd_B.setInputProtected(true);
        itemLineMsg.dealSplyQuoteDtlSlsAmt_B.setInputProtected(true);
        itemLineMsg.prcCatgNm_B.setInputProtected(true);
        itemLineMsg.prcCatgNm_BL.clear();
        // 2018/02/13 QC#21165 Add Start
        itemLineMsg.rddDt_B.setInputProtected(true);
        // 2018/02/13 QC#21165 Add End
        // QC#10347 2017/07/24 Add Start
        itemLineMsg.prcBaseDt_B.setInputProtected(true);
        // QC#10347 2017/07/24 Add End
        itemLineMsg.supdLockFlg_B.setInputProtected(true);
        itemLineMsg.dsOrdLineCatgCd_B.setInputProtected(true);
        itemLineMsg.ordLineSrcCd_B.setInputProtected(true);
        itemLineMsg.rtlWhNm_B.setInputProtected(true);
        itemLineMsg.rtlWhNm_LK.clear();
        itemLineMsg.rtlSwhNm_B.setInputProtected(true);
        itemLineMsg.rtlSwhNm_LK.clear();
    }

    //START 2017/08/01 E.Kameishi [QC#20295,ADD]
    /**
     * Active Add Button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void activeAddButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_ADD_BTN_NM, BTN_CMN_ADD_EVENT_NM, BTN_CMN_ADD_LABEL, 1, null);
    }

    /**
     * Inactive Add Button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void inactiveAddButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_ADD_BTN_NM, BTN_CMN_ADD_EVENT_NM, BTN_CMN_ADD_LABEL, 0, null);
    }
    //END 2017/08/01 E.Kameishi [QC#20295,ADD]


    // QC#16452 add Start
    /**
     * Set Screen Protect By Contact
     * @param scrnMsg NWAL1770BMsg
     */
    public static void setProtectByContact(NWAL1770BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1770_ABMsg ctacMsg = scrnMsg.A.no(i);

            if (ZYPCommonFunc.hasValue(ctacMsg.splyQuoteCtacPsnPk_A)) {
                ctacMsg.ctacPsnTpCd_A.setInputProtected(true);
                ctacMsg.ctacPsnFirstNm_A.setInputProtected(true);
                ctacMsg.ctacPsnFirstNm_LK.clear();
                ctacMsg.ctacPsnLastNm_A.setInputProtected(true);
                ctacMsg.ctacCustRefTpCd_A.setInputProtected(true);

            } else if (ZYPCommonFunc.hasValue(ctacMsg.ctacPsnPk_A)) {
                if (ZYPCommonFunc.hasValue(ctacMsg.ctacCustRefTpCd_AP)) {
                    ctacMsg.ctacPsnTpCd_A.setInputProtected(true);
                    ctacMsg.ctacPsnFirstNm_A.setInputProtected(true);
                    ctacMsg.ctacPsnFirstNm_LK.clear();
                    ctacMsg.ctacPsnLastNm_A.setInputProtected(true);
                    ctacMsg.ctacCustRefTpCd_A.setInputProtected(true);
                }
            }
        }
    }
    // QC#16452 add End
}
