/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0120.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.HashSet;
import java.util.Set;

import parts.common.EZDBMsg;
import parts.common.EZDGUIAttribute;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFAL0120.NFAL0120BMsg;
import business.servlet.NFAL0120.constant.NFAL0120Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/26   Hitachi         Y.Tsuchimoto    Update          QC#12127
 * 2016/09/08   Fujitsu         S.Fujita        Update          QC#4105
 * 2016/12/01   Fujitsu         T.Murai         Update          QC#16158
 * 2017/09/29   CITS            Y.Fujii         Update          QC#19408
 * 2017/12/01   Hitachi         Y.Takeno        Update          QC#12525
 * 2018/06/22   CITS            S.Katsuma       Update          QC#24025
 *</pre>
 */
public class NFAL0120CommonLogic implements NFAL0120Constant {
    
    /**
     * Initialize Link
     * If the field is null, make it invisible.
     * @param scrnMsg NFAL0120BMsg
     * @param handler EZ Common Handler
     */
    public static void initLink(EZDCommonHandler handler, NFAL0120BMsg scrnMsg) {
        /*
        //----- start add 2016/01/21
        scrnMsg.clearAllGUIAttribute("NFAL0120Scrn00");
        //---- end 2016/01/21
        
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).ajeInvNum_A)) {
                setItemVisibility(scrnMsg, "ajeInvNum_A#" + i, true);
            } else {
                setItemVisibility(scrnMsg, "ajeInvNum_A#" + i, false);
            }
            
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).invtyTrxPk_A)) {
                setItemVisibility(scrnMsg, "invtyTrxPk_A#" + i, true);
            } else {
                setItemVisibility(scrnMsg, "invtyTrxPk_A#" + i, false);
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).ajeItemCd_A)) {
                setItemVisibility(scrnMsg, "ajeItemCd_A#" + i, true);
            } else {
                setItemVisibility(scrnMsg, "ajeItemCd_A#" + i, false);
            }
            
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poOrdNum_A)) {
                setItemVisibility(scrnMsg, "poOrdNum_A#" + i, true);
            } else {
                setItemVisibility(scrnMsg, "poOrdNum_A#" + i, false);
            }
            
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).rcptNum_A)) {
                setItemVisibility(scrnMsg, "rcptNum_A#" + i, true);
            } else {
                setItemVisibility(scrnMsg, "rcptNum_A#" + i, false);
            }
            
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prntDsAssetMstrPk_A)) {
                setItemVisibility(scrnMsg, "prntDsAssetMstrPk_A#" + i, true);
            } else {
                setItemVisibility(scrnMsg, "prntDsAssetMstrPk_A#" + i, false);
            }
            
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).manJrnlEntryHdrPk_A)) {
                setItemVisibility(scrnMsg, "manJrnlEntryHdrPk_A#" + i, true);
            } else {
                setItemVisibility(scrnMsg, "manJrnlEntryHdrPk_A#" + i, false);
            }
        }
        */
        
    }
    
    /**
     * Set Item Visibility
     * @param scrnMsg NFCL3010BMsg
     * @param itemNm String
     * @param visibility boolean
     */
    public static void setItemVisibility(NFAL0120BMsg scrnMsg, String itemNm, boolean visibility) {
        EZDGUIAttribute itemVisibility = new EZDGUIAttribute("NFAL0120Scrn00", itemNm);
        itemVisibility.setVisibility(visibility);
        scrnMsg.addGUIAttribute(itemVisibility);
    }
    
    /**
     * Method name: initButton
     * <dd>The method explanation: Init Button Control.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     * @param handler EZ Common Handler
     */
    public static void initButton(EZDBMsg bMsg, EZDCommonHandler handler) {

        bMsg.clearAllGUIAttribute("NFAL0120Scrn00");
        
        handler.setButtonProperties(BTN_CMN_BLANK1[0], BTN_CMN_BLANK1[1], BTN_CMN_BLANK1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK3[0], BTN_CMN_BLANK3[1], BTN_CMN_BLANK3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK4[0], BTN_CMN_BLANK4[1], BTN_CMN_BLANK4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK5[0], BTN_CMN_BLANK5[1], BTN_CMN_BLANK5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 1, null);  // CSA mod. Add download button
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);  // CSA mod. remove reset button
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        

    }

    /**
     * Method name: initFocusItem
     * <dd>The method explanation: Init button confirm message.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void initFocusItem(EZDBMsg bMsg) {

        NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;

        scrnMsg.setFocusItem(scrnMsg.glDt_FR);
    }

    /**
     * Method name: setNameForMessage
     * <dd>The method explanation: Set Name For Message.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void setNameForMessage(EZDBMsg bMsg) {

        NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;

        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        scrnMsg.glDt_FR.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "GL Date From"));
        scrnMsg.glDt_TO.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "GL Date To"));

        scrnMsg.sysSrcCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Syste Source Name"));
        //scrnMsg.trxCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Transaction Name"));
        //scrnMsg.trxRsnCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Transaction Reason Name"));

        //---- start CSA add
        // START 2017/12/01 [QC#12525, MOD]
        // scrnMsg.jrnlCatgNm.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Journal Category"));
        // scrnMsg.coaAcctCd_FM.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "COA Account From"));
        // scrnMsg.coaAcctCd_TO.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "COA Account To"));
        scrnMsg.jrnlCatgCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Journal Category"));
        scrnMsg.coaCmpyCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Company Code"));
        scrnMsg.coaBrCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Branch"));
        scrnMsg.coaCcCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Cost Center"));
        scrnMsg.coaAcctCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Natural Account"));
        scrnMsg.coaProdCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Product Code"));
        scrnMsg.coaChCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Sales Channel"));
        scrnMsg.coaAfflCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Intercompany Code"));
        scrnMsg.coaProjCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Merchandise Type"));
        scrnMsg.coaExtnCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Business Unit"));
        scrnMsg.ajeTrxTpCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Transaction Type"));
        scrnMsg.xxQueryFltrTxt_AT.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Transaction Type"));
        // END   2017/12/01 [QC#12525, MOD]
        scrnMsg.jrnlDealDrAmt_FM.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Amount Low"));
        scrnMsg.jrnlDealDrAmt_TO.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Amount High"));
        scrnMsg.glPerNm.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "GL Period"));
        scrnMsg.xxQueryFltrTxt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Reference Search"));
        //---- end
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName()); //ADD 2016/12/01 [QC#16158]

        /* QC#8454 CSA modification
        scrnMsg.ajeId.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "AJE ID"));
        scrnMsg.coaBrCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Branch Code"));
        scrnMsg.coaCcCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Cost Center"));
        scrnMsg.coaAcctCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Account Code"));
        scrnMsg.drCoaProdCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Product Code"));

        scrnMsg.coaChCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Channel Code"));
        scrnMsg.coaAfflCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Affiliate Code"));
        scrnMsg.coaProjCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Project Code"));

        scrnMsg.billToCustCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Customer Code"));
        scrnMsg.vndCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Vendor Code"));
        scrnMsg.ajeInvNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Invoice Number"));
        scrnMsg.prmoPk.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Promotion"));
        scrnMsg.ajeItemCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Item Code"));

        scrnMsg.tocCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "TOC Code"));
        scrnMsg.coaProdCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "Product Code"));
        scnMsg.soNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID, "SO Number"));
        */
    }

    /**
     * Method name: clearAll
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     */
    public void clearAll(NFAL0120BMsg scrnMsg) {

        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);

        scrnMsg.glDt_FR.clear();
        scrnMsg.glDt_TO.clear();

        scrnMsg.sysSrcCd_3.clear();
        
        //---- start add for CSA
        // START 2017/12/01 [QC#12525, MOD]
        // scrnMsg.coaAcctCd_FM.clear();
        // scrnMsg.coaAcctCd_TO.clear();
        // scrnMsg.jrnlCatgNm.clear();
        scrnMsg.jrnlCatgCd.clear();
        scrnMsg.jrnlCatgNm.clear();
        scrnMsg.coaBrCd.clear();
        scrnMsg.coaCcCd.clear();
        scrnMsg.coaAcctCd.clear();
        scrnMsg.coaProdCd.clear();
        scrnMsg.coaChCd.clear();
        scrnMsg.coaAfflCd.clear();
        scrnMsg.coaProjCd.clear();
        scrnMsg.coaExtnCd.clear();
        scrnMsg.ajeTrxTpCd_3.clear();
        scrnMsg.xxQueryFltrTxt_AT.clear();
        // END   2017/12/01 [QC#12525, MOD]
        scrnMsg.jrnlDealDrAmt_FM.clear();
        scrnMsg.jrnlDealDrAmt_TO.clear();
        scrnMsg.glPerNm.clear();
        //---- end
        
        //scrnMsg.trxCd_3.clear();

        //scrnMsg.trxRsnCd_1.clear();
        //scrnMsg.trxRsnCd_2.clear();
        //scrnMsg.trxRsnCd_3.clear();

        //scrnMsg.sysSrcNm.clear();
        //scrnMsg.trxNm.clear();
        //scrnMsg.trxRsnNm.clear();

        /*
        scrnMsg.ajeId.clear();
        scrnMsg.coaBrCd_3.clear();
        scrnMsg.coaCcCd.clear();
        scrnMsg.coaAcctCd.clear();
        scrnMsg.drCoaProdCd.clear();
        scrnMsg.coaChCd_3.clear();
        scrnMsg.coaAfflCd.clear();
        scrnMsg.coaProjCd.clear();
        scrnMsg.billToCustCd.clear();
        scrnMsg.vndCd.clear();
        scrnMsg.ajeInvNum.clear();
        scrnMsg.prmoPk.clear();
        scrnMsg.ajeItemCd.clear();
        scrnMsg.tocCd.clear();
        scrnMsg.coaProdCd.clear();
        scrnMsg.soNum.clear();
        */

        scrnMsg.xxTotPrcAmt_DR.clear();
        scrnMsg.xxTotPrcAmt_CR.clear();
        
        scrnMsg.xxQueryFltrTxt.clear();
        
        scrnMsg.clearAllGUIAttribute("NFAL0120Scrn00");
        
    }

    /**
     * Method name: setInputProtectedForListInputFiled
     * <dd>The method explanation: Set input protected
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     */
    public static void setInputProtectedForListInputFiled(NFAL0120BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).jrnlSrcNm_A.setInputProtected(true);
            scrnMsg.A.no(i).jrnlCatgNm_A.setInputProtected(true);

            scrnMsg.A.no(i).jrnlFuncDrAmt_A.setInputProtected(true);
            scrnMsg.A.no(i).jrnlFuncCrAmt_A.setInputProtected(true);

            // START 2018/06/22 S.Katsuma [QC#24025,ADD]
            scrnMsg.A.no(i).ajeItemDescTxt_A.setInputProtected(true);
            // END 2018/06/22 S.Katsuma [QC#24025,ADD]

            // START 2016/07/26 Y.Tsuchimoto [QC#12127,ADD]
            scrnMsg.A.no(i).serNum_A.setInputProtected(true);
            // END   2016/07/26 Y.Tsuchimoto [QC#12127,ADD]

            // START 2016/09/08 S.Fujita [QC#4105,ADD]
            scrnMsg.A.no(i).psnNum_A.setInputProtected(false);
            // END   2016/09/08 S.Fujita [QC#4105,ADD]

            scrnMsg.A.no(i).xxQueryFltrTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxQueryFltrTxt_A2.setInputProtected(true);
            scrnMsg.A.no(i).xxQueryFltrTxt_A3.setInputProtected(true);
            scrnMsg.A.no(i).xxQueryFltrTxt_A4.setInputProtected(true);
            scrnMsg.A.no(i).xxQueryFltrTxt_A5.setInputProtected(true);
        }
    }

    /**
     * Method name: protectParmanentFields
     * <dd>Enable or Disable Button Property
     * @param scrnMsg NFAL0070BMsg
     */
    public static void protectParmanentFields(NFAL0120BMsg scrnMsg) {

        //scrnMsg.sysSrcNm.setInputProtected(true);
        //scrnMsg.trxNm.setInputProtected(true);
        //scrnMsg.trxRsnNm.setInputProtected(true);
        // START 2017/12/01 [QC#12525, ADD]
        scrnMsg.coaCmpyCd.setInputProtected(true);
        scrnMsg.jrnlCatgNm.setInputProtected(true);
        // END   2017/12/01 [QC#12525, ADD]
    }

    /**
     * Method name: changeTableColorByRow
     * <dd>The method explanation: change Table Coloer by Row.
     * <dd>Remarks:
     * @param scrnMsg NFAL0120BMsg
     */
    public static void changeTableColorByRow(NFAL0120BMsg scrnMsg) {
        // set alternate rows back-ground color
        int tempCnt = scrnMsg.A.getValidCount();
        scrnMsg.A.setValidCount(tempCnt + 1);

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        scrnMsg.A.setValidCount(tempCnt);
    }
    
    
    public boolean chkGlDtRange(NFAL0120BMsg scrnMsg) {
        String st = scrnMsg.glDt_FR.getValue();
        
        if (scrnMsg.glDt_TO.isClear()) {
            return true;
        }
        
        String to = scrnMsg.glDt_TO.getValue();
        
        if (st == null) {
            return false;
        }
        
        String stMonth = st.substring(0,6);
        String endMonth = to.substring(0,6);
        
        if (stMonth != null && !stMonth.equals(endMonth)) {
            return false;
        }
        
        return true;
    }
    
    //---- start add 2016/05/31
    public static void chkGlDtFromTo(NFAL0120BMsg scrnMsg) {
       if (hasValue(scrnMsg.glDt_FR) && hasValue(scrnMsg.glDt_TO)) {
           if (scrnMsg.glDt_FR.getValue().compareTo(scrnMsg.glDt_TO.getValue()) > 0) {
               scrnMsg.glDt_TO.setErrorInfo(1, "NFCM0023E");
               scrnMsg.addCheckItem(scrnMsg.glDt_TO);
           }
       }
    }

    // START 2017/12/01 [QC#12525, DEL]
    // public static void chkCoaAcctFromTo(NFAL0120BMsg scrnMsg) {
    //     if (hasValue(scrnMsg.coaAcctCd_FM) && hasValue(scrnMsg.coaAcctCd_TO)) {
    //         if (scrnMsg.coaAcctCd_FM.getValue().compareTo(scrnMsg.coaAcctCd_TO.getValue()) > 0) {
    //             scrnMsg.coaAcctCd_TO.setErrorInfo(1, "NFCM0023E");
    //             scrnMsg.addCheckItem(scrnMsg.coaAcctCd_TO);
    //         }
    //     }
    // }
    // END   2017/12/01 [QC#12525, DEL]

    public static void chkAmountFromTo(NFAL0120BMsg scrnMsg) {
        if (hasValue(scrnMsg.jrnlDealDrAmt_FM) && hasValue(scrnMsg.jrnlDealDrAmt_TO)) {
            if (scrnMsg.jrnlDealDrAmt_FM.getValue().compareTo(scrnMsg.jrnlDealDrAmt_TO.getValue()) > 0) {
                scrnMsg.jrnlDealDrAmt_TO.setErrorInfo(1, "NFCM0023E");
                scrnMsg.addCheckItem(scrnMsg.jrnlDealDrAmt_TO);
            }
        }
     }
    
    public static void chkPerNm(NFAL0120BMsg scrnMsg) {
        if (!hasValue(scrnMsg.glPerNm)) {
            return;
        }
        
        if (scrnMsg.glPerNm.getValue().length() != 6) {
            scrnMsg.glPerNm.setErrorInfo(1, "NFAM0043E", new String[] {"Period Name"} );
            scrnMsg.addCheckItem(scrnMsg.glPerNm);
            return;
        }
        
        if (!"-".equals(scrnMsg.glPerNm.getValue().substring(3,4))) {
            scrnMsg.glPerNm.setErrorInfo(1, "NFAM0043E", new String[] {"Period Name"} );
            scrnMsg.addCheckItem(scrnMsg.glPerNm);
            return;
        }
        
        String monthNm = scrnMsg.glPerNm.getValue().substring(0,3);
        
        Set<String> monthSet = new HashSet<String>();
        
        monthSet.add("JAN");
        monthSet.add("FEB");
        monthSet.add("MAR");
        monthSet.add("APR");
        monthSet.add("MAY");
        monthSet.add("JUN");
        monthSet.add("JUL");
        monthSet.add("AUG");
        monthSet.add("SEP");
        monthSet.add("OCT");
        monthSet.add("NOV");
        monthSet.add("DEC");
     
        if (!monthSet.contains(monthNm)) {
            scrnMsg.glPerNm.setErrorInfo(1, "NFAM0043E", new String[] {"Period Name"} );
            scrnMsg.addCheckItem(scrnMsg.glPerNm);
            return;
        }
        
        String year = scrnMsg.glPerNm.getValue().substring(4,6);
        
        try
        {
            Integer.parseInt(year);
        } catch (NumberFormatException ex) {
            scrnMsg.glPerNm.setErrorInfo(1, "NFAM0043E", new String[] {"Period Name"} );
            scrnMsg.addCheckItem(scrnMsg.glPerNm);
            return;
        }
        
    }
    
    public static boolean chkReqdFields(NFAL0120BMsg scrnMsg) {
        
        // START 2017/12/01 [QC#12525, ADD]
        if (hasValue(scrnMsg.ajeTrxTpCd_3) && hasValue(scrnMsg.xxQueryFltrTxt_AT) && !"".equals(scrnMsg.xxQueryFltrTxt_AT.getValue())) {
            return true;
        }
        // END   2017/12/01 [QC#12525, ADD]
        if (hasValue(scrnMsg.glDt_FR) || hasValue(scrnMsg.glPerNm)) {
            // OK
            return true;
        }
        // else error
        return false;
    }
    
    //---- end

    // START 2017/12/01 [QC#12525, ADD]
    public static Object[] setParamForCoaAcctPopup(NFAL0120BMsg scrnMsg) {
        scrnMsg.xxTblNm_P.clear();
        scrnMsg.xxTblCdColNm_P.clear();
        scrnMsg.xxTblNmColNm_P.clear();
        scrnMsg.xxTblSortColNm_P.clear();
        scrnMsg.xxScrNm_P.clear();
        scrnMsg.xxHdrCdLbNm_P.clear();
        scrnMsg.xxHdrNmLbNm_P.clear();
        scrnMsg.xxDtlCdLbNm_P.clear();
        scrnMsg.xxDtlNmLbNm_P.clear();
        scrnMsg.xxCondNm_P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, NFAL0120Constant.TBL_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, NFAL0120Constant.TBL_CD_COL_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, NFAL0120Constant.TBL_NM_COL_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, NFAL0120Constant.TBL_SORT_COL_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, NFAL0120Constant.SCR_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, NFAL0120Constant.HDR_CD_LB_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, NFAL0120Constant.HDR_NM_LB_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, NFAL0120Constant.DTL_CD_LB_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, NFAL0120Constant.DTL_NM_LB_NM_FOR_ACCT);

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxTblNm_P;
        params[1] = scrnMsg.xxTblCdColNm_P;
        params[2] = scrnMsg.xxTblNmColNm_P;
        params[3] = scrnMsg.xxTblSortColNm_P;
        params[4] = scrnMsg.xxScrNm_P;
        params[5] = scrnMsg.xxHdrCdLbNm_P;
        params[6] = scrnMsg.xxHdrNmLbNm_P;
        params[7] = scrnMsg.xxDtlCdLbNm_P;
        params[8] = scrnMsg.xxDtlNmLbNm_P;
        params[9] = scrnMsg.coaAcctCd;
        params[10] = scrnMsg.xxCondNm_P;

        return params;
    }

    public static Object[] setParamForCoaAfflPopup(NFAL0120BMsg scrnMsg) {
        scrnMsg.xxTblNm_P.clear();
        scrnMsg.xxTblCdColNm_P.clear();
        scrnMsg.xxTblNmColNm_P.clear();
        scrnMsg.xxTblSortColNm_P.clear();
        scrnMsg.xxScrNm_P.clear();
        scrnMsg.xxHdrCdLbNm_P.clear();
        scrnMsg.xxHdrNmLbNm_P.clear();
        scrnMsg.xxDtlCdLbNm_P.clear();
        scrnMsg.xxDtlNmLbNm_P.clear();
        scrnMsg.xxCondNm_P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, NFAL0120Constant.TBL_NM_FOR_AFFL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, NFAL0120Constant.TBL_CD_COL_NM_FOR_AFFL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, NFAL0120Constant.TBL_NM_COL_NM_FOR_AFFL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, NFAL0120Constant.TBL_SORT_COL_NM_FOR_AFFL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, NFAL0120Constant.SCR_NM_FOR_AFFL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, NFAL0120Constant.HDR_CD_LB_NM_FOR_AFFL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, NFAL0120Constant.HDR_NM_LB_NM_FOR_AFFL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, NFAL0120Constant.DTL_CD_LB_NM_FOR_AFFL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, NFAL0120Constant.DTL_NM_LB_NM_FOR_AFFL);

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxTblNm_P;
        params[1] = scrnMsg.xxTblCdColNm_P;
        params[2] = scrnMsg.xxTblNmColNm_P;
        params[3] = scrnMsg.xxTblSortColNm_P;
        params[4] = scrnMsg.xxScrNm_P;
        params[5] = scrnMsg.xxHdrCdLbNm_P;
        params[6] = scrnMsg.xxHdrNmLbNm_P;
        params[7] = scrnMsg.xxDtlCdLbNm_P;
        params[8] = scrnMsg.xxDtlNmLbNm_P;
        params[9] = scrnMsg.coaAfflCd;
        params[10] = scrnMsg.xxCondNm_P;

        return params;
    }

    public static Object[] setParamForCoaBrPopup(NFAL0120BMsg scrnMsg) {
        scrnMsg.xxTblNm_P.clear();
        scrnMsg.xxTblCdColNm_P.clear();
        scrnMsg.xxTblNmColNm_P.clear();
        scrnMsg.xxTblSortColNm_P.clear();
        scrnMsg.xxScrNm_P.clear();
        scrnMsg.xxHdrCdLbNm_P.clear();
        scrnMsg.xxHdrNmLbNm_P.clear();
        scrnMsg.xxDtlCdLbNm_P.clear();
        scrnMsg.xxDtlNmLbNm_P.clear();
        scrnMsg.xxCondNm_P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, NFAL0120Constant.TBL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, NFAL0120Constant.TBL_CD_COL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, NFAL0120Constant.TBL_NM_COL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, NFAL0120Constant.TBL_SORT_COL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, NFAL0120Constant.SCR_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, NFAL0120Constant.HDR_CD_LB_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, NFAL0120Constant.HDR_NM_LB_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, NFAL0120Constant.DTL_CD_LB_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, NFAL0120Constant.DTL_NM_LB_NM_FOR_BR);

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxTblNm_P;
        params[1] = scrnMsg.xxTblCdColNm_P;
        params[2] = scrnMsg.xxTblNmColNm_P;
        params[3] = scrnMsg.xxTblSortColNm_P;
        params[4] = scrnMsg.xxScrNm_P;
        params[5] = scrnMsg.xxHdrCdLbNm_P;
        params[6] = scrnMsg.xxHdrNmLbNm_P;
        params[7] = scrnMsg.xxDtlCdLbNm_P;
        params[8] = scrnMsg.xxDtlNmLbNm_P;
        params[9] = scrnMsg.coaBrCd;
        params[10] = scrnMsg.xxCondNm_P;

        return params;
    }

    public static Object[] setParamForCoaCcPopup(NFAL0120BMsg scrnMsg) {
        scrnMsg.xxTblNm_P.clear();
        scrnMsg.xxTblCdColNm_P.clear();
        scrnMsg.xxTblNmColNm_P.clear();
        scrnMsg.xxTblSortColNm_P.clear();
        scrnMsg.xxScrNm_P.clear();
        scrnMsg.xxHdrCdLbNm_P.clear();
        scrnMsg.xxHdrNmLbNm_P.clear();
        scrnMsg.xxDtlCdLbNm_P.clear();
        scrnMsg.xxDtlNmLbNm_P.clear();
        scrnMsg.xxCondNm_P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, NFAL0120Constant.TBL_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, NFAL0120Constant.TBL_CD_COL_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, NFAL0120Constant.TBL_NM_COL_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, NFAL0120Constant.TBL_SORT_COL_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, NFAL0120Constant.SCR_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, NFAL0120Constant.HDR_CD_LB_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, NFAL0120Constant.HDR_NM_LB_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, NFAL0120Constant.DTL_CD_LB_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, NFAL0120Constant.DTL_NM_LB_NM_FOR_CC);

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxTblNm_P;
        params[1] = scrnMsg.xxTblCdColNm_P;
        params[2] = scrnMsg.xxTblNmColNm_P;
        params[3] = scrnMsg.xxTblSortColNm_P;
        params[4] = scrnMsg.xxScrNm_P;
        params[5] = scrnMsg.xxHdrCdLbNm_P;
        params[6] = scrnMsg.xxHdrNmLbNm_P;
        params[7] = scrnMsg.xxDtlCdLbNm_P;
        params[8] = scrnMsg.xxDtlNmLbNm_P;
        params[9] = scrnMsg.coaCcCd;
        params[10] = scrnMsg.xxCondNm_P;

        return params;
    }

    public static Object[] setParamForCoaChPopup(NFAL0120BMsg scrnMsg) {
        scrnMsg.xxTblNm_P.clear();
        scrnMsg.xxTblCdColNm_P.clear();
        scrnMsg.xxTblNmColNm_P.clear();
        scrnMsg.xxTblSortColNm_P.clear();
        scrnMsg.xxScrNm_P.clear();
        scrnMsg.xxHdrCdLbNm_P.clear();
        scrnMsg.xxHdrNmLbNm_P.clear();
        scrnMsg.xxDtlCdLbNm_P.clear();
        scrnMsg.xxDtlNmLbNm_P.clear();
        scrnMsg.xxCondNm_P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, NFAL0120Constant.TBL_NM_FOR_CH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, NFAL0120Constant.TBL_CD_COL_NM_FOR_CH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, NFAL0120Constant.TBL_NM_COL_NM_FOR_CH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, NFAL0120Constant.TBL_SORT_COL_NM_FOR_CH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, NFAL0120Constant.SCR_NM_FOR_CH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, NFAL0120Constant.HDR_CD_LB_NM_FOR_CH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, NFAL0120Constant.HDR_NM_LB_NM_FOR_CH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, NFAL0120Constant.DTL_CD_LB_NM_FOR_CH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, NFAL0120Constant.DTL_NM_LB_NM_FOR_CH);

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxTblNm_P;
        params[1] = scrnMsg.xxTblCdColNm_P;
        params[2] = scrnMsg.xxTblNmColNm_P;
        params[3] = scrnMsg.xxTblSortColNm_P;
        params[4] = scrnMsg.xxScrNm_P;
        params[5] = scrnMsg.xxHdrCdLbNm_P;
        params[6] = scrnMsg.xxHdrNmLbNm_P;
        params[7] = scrnMsg.xxDtlCdLbNm_P;
        params[8] = scrnMsg.xxDtlNmLbNm_P;
        params[9] = scrnMsg.coaChCd;
        params[10] = scrnMsg.xxCondNm_P;

        return params;
    }

    public static Object[] setParamForCoaExtnPopup(NFAL0120BMsg scrnMsg) {
        scrnMsg.xxTblNm_P.clear();
        scrnMsg.xxTblCdColNm_P.clear();
        scrnMsg.xxTblNmColNm_P.clear();
        scrnMsg.xxTblSortColNm_P.clear();
        scrnMsg.xxScrNm_P.clear();
        scrnMsg.xxHdrCdLbNm_P.clear();
        scrnMsg.xxHdrNmLbNm_P.clear();
        scrnMsg.xxDtlCdLbNm_P.clear();
        scrnMsg.xxDtlNmLbNm_P.clear();
        scrnMsg.xxCondNm_P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, NFAL0120Constant.TBL_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, NFAL0120Constant.TBL_CD_COL_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, NFAL0120Constant.TBL_NM_COL_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, NFAL0120Constant.TBL_SORT_COL_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, NFAL0120Constant.SCR_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, NFAL0120Constant.HDR_CD_LB_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, NFAL0120Constant.HDR_NM_LB_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, NFAL0120Constant.DTL_CD_LB_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, NFAL0120Constant.DTL_NM_LB_NM_FOR_EXTN);

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxTblNm_P;
        params[1] = scrnMsg.xxTblCdColNm_P;
        params[2] = scrnMsg.xxTblNmColNm_P;
        params[3] = scrnMsg.xxTblSortColNm_P;
        params[4] = scrnMsg.xxScrNm_P;
        params[5] = scrnMsg.xxHdrCdLbNm_P;
        params[6] = scrnMsg.xxHdrNmLbNm_P;
        params[7] = scrnMsg.xxDtlCdLbNm_P;
        params[8] = scrnMsg.xxDtlNmLbNm_P;
        params[9] = scrnMsg.coaExtnCd;
        params[10] = scrnMsg.xxCondNm_P;

        return params;
    }

    public static Object[] setParamForCoaProdPopup(NFAL0120BMsg scrnMsg) {
        scrnMsg.xxTblNm_P.clear();
        scrnMsg.xxTblCdColNm_P.clear();
        scrnMsg.xxTblNmColNm_P.clear();
        scrnMsg.xxTblSortColNm_P.clear();
        scrnMsg.xxScrNm_P.clear();
        scrnMsg.xxHdrCdLbNm_P.clear();
        scrnMsg.xxHdrNmLbNm_P.clear();
        scrnMsg.xxDtlCdLbNm_P.clear();
        scrnMsg.xxDtlNmLbNm_P.clear();
        scrnMsg.xxCondNm_P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, NFAL0120Constant.TBL_NM_FOR_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, NFAL0120Constant.TBL_CD_COL_NM_FOR_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, NFAL0120Constant.TBL_NM_COL_NM_FOR_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, NFAL0120Constant.TBL_SORT_COL_NM_FOR_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, NFAL0120Constant.SCR_NM_FOR_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, NFAL0120Constant.HDR_CD_LB_NM_FOR_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, NFAL0120Constant.HDR_NM_LB_NM_FOR_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, NFAL0120Constant.DTL_CD_LB_NM_FOR_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, NFAL0120Constant.DTL_NM_LB_NM_FOR_PROD);

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxTblNm_P;
        params[1] = scrnMsg.xxTblCdColNm_P;
        params[2] = scrnMsg.xxTblNmColNm_P;
        params[3] = scrnMsg.xxTblSortColNm_P;
        params[4] = scrnMsg.xxScrNm_P;
        params[5] = scrnMsg.xxHdrCdLbNm_P;
        params[6] = scrnMsg.xxHdrNmLbNm_P;
        params[7] = scrnMsg.xxDtlCdLbNm_P;
        params[8] = scrnMsg.xxDtlNmLbNm_P;
        params[9] = scrnMsg.coaProdCd;
        params[10] = scrnMsg.xxCondNm_P;

        return params;
    }

    public static Object[] setParamForCoaProjPopup(NFAL0120BMsg scrnMsg) {
        scrnMsg.xxTblNm_P.clear();
        scrnMsg.xxTblCdColNm_P.clear();
        scrnMsg.xxTblNmColNm_P.clear();
        scrnMsg.xxTblSortColNm_P.clear();
        scrnMsg.xxScrNm_P.clear();
        scrnMsg.xxHdrCdLbNm_P.clear();
        scrnMsg.xxHdrNmLbNm_P.clear();
        scrnMsg.xxDtlCdLbNm_P.clear();
        scrnMsg.xxDtlNmLbNm_P.clear();
        scrnMsg.xxCondNm_P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, NFAL0120Constant.TBL_NM_FOR_PROJ);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, NFAL0120Constant.TBL_CD_COL_NM_FOR_PROJ);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, NFAL0120Constant.TBL_NM_COL_NM_FOR_PROJ);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, NFAL0120Constant.TBL_SORT_COL_NM_FOR_PROJ);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, NFAL0120Constant.SCR_NM_FOR_PROJ);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, NFAL0120Constant.HDR_CD_LB_NM_FOR_PROJ);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, NFAL0120Constant.HDR_NM_LB_NM_FOR_PROJ);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, NFAL0120Constant.DTL_CD_LB_NM_FOR_PROJ);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, NFAL0120Constant.DTL_NM_LB_NM_FOR_PROJ);

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxTblNm_P;
        params[1] = scrnMsg.xxTblCdColNm_P;
        params[2] = scrnMsg.xxTblNmColNm_P;
        params[3] = scrnMsg.xxTblSortColNm_P;
        params[4] = scrnMsg.xxScrNm_P;
        params[5] = scrnMsg.xxHdrCdLbNm_P;
        params[6] = scrnMsg.xxHdrNmLbNm_P;
        params[7] = scrnMsg.xxDtlCdLbNm_P;
        params[8] = scrnMsg.xxDtlNmLbNm_P;
        params[9] = scrnMsg.coaProjCd;
        params[10] = scrnMsg.xxCondNm_P;

        return params;
    }

    public static Object[] setParamForJrnlCatgPopup(NFAL0120BMsg scrnMsg) {
        scrnMsg.xxTblNm_P.clear();
        scrnMsg.xxTblCdColNm_P.clear();
        scrnMsg.xxTblNmColNm_P.clear();
        scrnMsg.xxTblSortColNm_P.clear();
        scrnMsg.xxScrNm_P.clear();
        scrnMsg.xxHdrCdLbNm_P.clear();
        scrnMsg.xxHdrNmLbNm_P.clear();
        scrnMsg.xxDtlCdLbNm_P.clear();
        scrnMsg.xxDtlNmLbNm_P.clear();
        scrnMsg.xxCondNm_P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, NFAL0120Constant.TBL_NM_FOR_JRNL_CATG);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, NFAL0120Constant.TBL_CD_COL_NM_FOR_JRNL_CATG);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, NFAL0120Constant.TBL_NM_COL_NM_FOR_JRNL_CATG);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, NFAL0120Constant.TBL_SORT_COL_NM_FOR_JRNL_CATG);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, NFAL0120Constant.SCR_NM_FOR_JRNL_CATG);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, NFAL0120Constant.HDR_CD_LB_NM_FOR_JRNL_CATG);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, NFAL0120Constant.HDR_NM_LB_NM_FOR_JRNL_CATG);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, NFAL0120Constant.DTL_CD_LB_NM_FOR_JRNL_CATG);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, NFAL0120Constant.DTL_NM_LB_NM_FOR_JRNL_CATG);

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxTblNm_P;
        params[1] = scrnMsg.xxTblCdColNm_P;
        params[2] = scrnMsg.xxTblNmColNm_P;
        params[3] = scrnMsg.xxTblSortColNm_P;
        params[4] = scrnMsg.xxScrNm_P;
        params[5] = scrnMsg.xxHdrCdLbNm_P;
        params[6] = scrnMsg.xxHdrNmLbNm_P;
        params[7] = scrnMsg.xxDtlCdLbNm_P;
        params[8] = scrnMsg.xxDtlNmLbNm_P;
        params[9] = scrnMsg.jrnlCatgCd;
        params[10] = scrnMsg.jrnlCatgNm;

        return params;
    }
    // END   2017/12/01 [QC#12525, ADD]
}
