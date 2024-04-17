package business.servlet.NFAL0200.common;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFAL0200.NFAL0200BMsg;
import business.servlet.NFAL0200.constant.NFAL0200Constant;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

public class NFAL0200CommonLogic implements ZYPConstant{

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg   NFDL0010BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFAL0200BMsg scrnMsg) {

        scrnMsg.setInputProtected(false);

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);

        scrnMsg.coaCmpyCd.setInputProtected(true);   // Display Only
        
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxComnScrCondValTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).xxRecNmTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).xxRptTpTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).coaGlCmbnEnblFlg_A.setInputProtected(true);
            scrnMsg.A.no(i).coaGlCmbnPstgAllwFlg_A.setInputProtected(true);
            scrnMsg.A.no(i).coaGlCmbnBdgAllwFlg_A.setInputProtected(true);
        }
        
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).coaChDescTxt_B.setInputProtected(true);
            scrnMsg.B.no(i).xxChkBox_B.setInputProtected(true);
            scrnMsg.B.no(i).coaEnblFlg_B.setInputProtected(true);
            scrnMsg.B.no(i).coaPstgAllwFlg_B.setInputProtected(true);
            scrnMsg.B.no(i).coaBdgAllwFlg_B.setInputProtected(true);
            scrnMsg.B.no(i).coaStartActvDt_B.setInputProtected(true);
            scrnMsg.B.no(i).coaEndActvDt_B.setInputProtected(true);
        }
        
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).coaBrDescTxt_C.setInputProtected(true);
            scrnMsg.C.no(i).xxChkBox_C.setInputProtected(true);
            scrnMsg.C.no(i).coaEnblFlg_C.setInputProtected(true);
            scrnMsg.C.no(i).coaPstgAllwFlg_C.setInputProtected(true);
            scrnMsg.C.no(i).coaBdgAllwFlg_C.setInputProtected(true);
            scrnMsg.C.no(i).coaStartActvDt_C.setInputProtected(true);
            scrnMsg.C.no(i).coaEndActvDt_C.setInputProtected(true);
        }
        
        for (int i = 0; i < scrnMsg.D.length(); i++) {
            scrnMsg.D.no(i).coaCcDescTxt_D.setInputProtected(true);
            scrnMsg.D.no(i).xxChkBox_D.setInputProtected(true);
            scrnMsg.D.no(i).coaEnblFlg_D.setInputProtected(true);
            scrnMsg.D.no(i).coaPstgAllwFlg_D.setInputProtected(true);
            scrnMsg.D.no(i).coaBdgAllwFlg_D.setInputProtected(true);
            scrnMsg.D.no(i).coaStartActvDt_D.setInputProtected(true);
            scrnMsg.D.no(i).coaEndActvDt_D.setInputProtected(true);
        }
        
        for (int i = 0; i < scrnMsg.E.length(); i++) {
            scrnMsg.E.no(i).coaAcctDescTxt_E.setInputProtected(true);
            scrnMsg.E.no(i).xxChkBox_E.setInputProtected(true);
            scrnMsg.E.no(i).coaEnblFlg_E.setInputProtected(true);
            scrnMsg.E.no(i).coaPstgAllwFlg_E.setInputProtected(true);
            scrnMsg.E.no(i).coaBdgAllwFlg_E.setInputProtected(true);
            scrnMsg.E.no(i).coaStartActvDt_E.setInputProtected(true);
            scrnMsg.E.no(i).coaEndActvDt_E.setInputProtected(true);
        }
        
        for (int i = 0; i < scrnMsg.F.length(); i++) {
            scrnMsg.F.no(i).coaProjDescTxt_F.setInputProtected(true);
            scrnMsg.F.no(i).xxChkBox_F.setInputProtected(true);
            scrnMsg.F.no(i).coaEnblFlg_F.setInputProtected(true);
            scrnMsg.F.no(i).coaPstgAllwFlg_F.setInputProtected(true);
            scrnMsg.F.no(i).coaBdgAllwFlg_F.setInputProtected(true);
            scrnMsg.F.no(i).coaStartActvDt_F.setInputProtected(true);
            scrnMsg.F.no(i).coaEndActvDt_F.setInputProtected(true);
        }
        
        for (int i = 0; i < scrnMsg.G.length(); i++) {
            scrnMsg.G.no(i).coaProdDescTxt_G.setInputProtected(true);
            scrnMsg.G.no(i).xxChkBox_G.setInputProtected(true);
            scrnMsg.G.no(i).coaEnblFlg_G.setInputProtected(true);
            scrnMsg.G.no(i).coaPstgAllwFlg_G.setInputProtected(true);
            scrnMsg.G.no(i).coaBdgAllwFlg_G.setInputProtected(true);
            scrnMsg.G.no(i).coaStartActvDt_G.setInputProtected(true);
            scrnMsg.G.no(i).coaEndActvDt_G.setInputProtected(true);
        }
        
        for (int i = 0; i < scrnMsg.H.length(); i++) {
            scrnMsg.H.no(i).coaAfflDescTxt_H.setInputProtected(true);
            scrnMsg.H.no(i).xxChkBox_H.setInputProtected(true);
            scrnMsg.H.no(i).coaEnblFlg_H.setInputProtected(true);
            scrnMsg.H.no(i).coaPstgAllwFlg_H.setInputProtected(true);
            scrnMsg.H.no(i).coaBdgAllwFlg_H.setInputProtected(true);
            scrnMsg.H.no(i).coaStartActvDt_H.setInputProtected(true);
            scrnMsg.H.no(i).coaEndActvDt_H.setInputProtected(true);
        }
        
        for (int i = 0; i < scrnMsg.I.length(); i++) {
            scrnMsg.I.no(i).coaExtnDescTxt_I.setInputProtected(true);
            scrnMsg.I.no(i).xxChkBox_I.setInputProtected(true);
            scrnMsg.I.no(i).coaEnblFlg_I.setInputProtected(true);
            scrnMsg.I.no(i).coaPstgAllwFlg_I.setInputProtected(true);
            scrnMsg.I.no(i).coaBdgAllwFlg_I.setInputProtected(true);
            scrnMsg.I.no(i).coaStartActvDt_I.setInputProtected(true);
            scrnMsg.I.no(i).coaEndActvDt_I.setInputProtected(true);
        }
        // all tabs to be protected
        //setTabProtected(scrnMsg);
        
        // COA BR tab to be selected
        scrnMsg.xxDplyTab.setValue(NFAL0200Constant.BR_TAB);
        submitBtnControl(scrnAppli, true);
        
        setDownloadBtnControll(scrnAppli, scrnMsg);
    }
    
    public static boolean inputCheckForSearch(NFAL0200BMsg scrnMsg) {
        if (hasValue(scrnMsg.coaBrCd) || hasValue(scrnMsg.coaCcCd) || hasValue(scrnMsg.coaAcctCd) || hasValue(scrnMsg.coaProjCd)
                || hasValue(scrnMsg.coaChCd) || hasValue(scrnMsg.coaAfflCd) || hasValue(scrnMsg.coaProdCd) || hasValue(scrnMsg.coaExtnCd)) {
            // ok
            return true;
        } else {
            
            // error
            return false;
        }
    }
        
    //----- start 2016/06/29 remove this function since hierarchy botton to be activated always
    /*public static void setTabProtected(NFAL0200BMsg scrnMsg) {
        
        scrnMsg.clearAllGUIAttribute(NFAL0200Constant.SCRN_ID);
        
        if (hasValue(scrnMsg.coaChCd)) {
            scrnMsg.xxTabProt_B.setInputProtected(false);
        } else {
            scrnMsg.xxTabProt_B.setInputProtected(true);
        }
        
        if (hasValue(scrnMsg.coaBrCd)) {
            scrnMsg.xxTabProt_C.setInputProtected(false);
        } else {
            scrnMsg.xxTabProt_C.setInputProtected(true);
        }
        
        if (hasValue(scrnMsg.coaCcCd)) {
            scrnMsg.xxTabProt_D.setInputProtected(false);
        } else {
            scrnMsg.xxTabProt_D.setInputProtected(true);
        }
        
        if (hasValue(scrnMsg.coaAcctCd)) {
            scrnMsg.xxTabProt_E.setInputProtected(false);
        } else {
            scrnMsg.xxTabProt_E.setInputProtected(true);
        }
        
        if (hasValue(scrnMsg.coaProjCd)) {
            scrnMsg.xxTabProt_F.setInputProtected(false);
        } else {
            scrnMsg.xxTabProt_F.setInputProtected(true);
        }
        
        if (hasValue(scrnMsg.coaProdCd)) {
            scrnMsg.xxTabProt_G.setInputProtected(false);
        } else {
            scrnMsg.xxTabProt_G.setInputProtected(true);
        }
        
        if (hasValue(scrnMsg.coaAfflCd)) {
            scrnMsg.xxTabProt_H.setInputProtected(false);
        } else {
            scrnMsg.xxTabProt_H.setInputProtected(true);
        }
        
        if (hasValue(scrnMsg.coaExtnCd)) {
            scrnMsg.xxTabProt_I.setInputProtected(false);
        } else {
            scrnMsg.xxTabProt_I.setInputProtected(true);
        }
        
    }
    */
    
    public static void setTabUnprotected (NFAL0200BMsg scrnMsg) {
        
        scrnMsg.clearAllGUIAttribute(NFAL0200Constant.SCRN_ID);
        
        scrnMsg.xxTabProt_B.setInputProtected(false);   
        scrnMsg.xxTabProt_C.setInputProtected(false);
        scrnMsg.xxTabProt_D.setInputProtected(false);
        scrnMsg.xxTabProt_E.setInputProtected(false);
        scrnMsg.xxTabProt_F.setInputProtected(false);
        scrnMsg.xxTabProt_G.setInputProtected(false);
        scrnMsg.xxTabProt_H.setInputProtected(false);
        scrnMsg.xxTabProt_I.setInputProtected(false);
        
    }
    
    public static void submitBtnControl(EZDCommonHandler scrnAppli, boolean isEnable) {
        if (isEnable) {
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
        } else {
            scrnAppli.setButtonProperties("btn2", "", "Submit", 0, null);
        }
    }
    
    public static void clearAll(NFAL0200BMsg scrnMsg) {
        
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        
        scrnMsg.B.clear();
        scrnMsg.B.setValidCount(0);
        
        scrnMsg.C.clear();
        scrnMsg.C.setValidCount(0);
        
        scrnMsg.D.clear();
        scrnMsg.D.setValidCount(0);
        
        scrnMsg.E.clear();
        scrnMsg.E.setValidCount(0);
        
        scrnMsg.F.clear();
        scrnMsg.F.setValidCount(0);
        
        scrnMsg.G.clear();
        scrnMsg.G.setValidCount(0);
        
        scrnMsg.H.clear();
        scrnMsg.H.setValidCount(0);
        
        scrnMsg.I.clear();
        scrnMsg.I.setValidCount(0);
     
        scrnMsg.coaChCd.clear();
        scrnMsg.coaBrCd.clear();
        scrnMsg.coaCcCd.clear();
        scrnMsg.coaAcctCd.clear();
        scrnMsg.coaProjCd.clear();
        scrnMsg.coaProdCd.clear();
        scrnMsg.coaAfflCd.clear();
        scrnMsg.coaExtnCd.clear();
        
        scrnMsg.xxChkBox.setValue(FLG_ON_Y);
        // START 2016/10/21 J.Kim [QC#13514,ADD]
        scrnMsg.srchOptPk_S.clear();
        scrnMsg.srchOptNm_S.clear();
        // END 2016/10/21 J.Kim [QC#13514,ADD]
    }
    
    public static void setDownloadBtnControll(EZDCommonHandler scrnAppli, NFAL0200BMsg scrnMsg) {
        
        if (scrnMsg.A.getValidCount() > 0) {
            scrnAppli.setButtonProperties("DownloadCombBtn", "DownloadCombBtn", "Comb Download ", 1, null);
        } else {
            scrnAppli.setButtonProperties("DownloadCombBtn", "", "Comb Download ", 0, null);
        }
        
        if (scrnMsg.xxDplyTab.getValue().equals(NFAL0200Constant.CH_TAB)) {
            
            if (scrnMsg.B.getValidCount() > 0) {
                scrnAppli.setButtonProperties("DownloadCoaBtn", "DownloadCoaBtn", "COA Download ", 1, null);
            } else {
                scrnAppli.setButtonProperties("DownloadCoaBtn", "", "COA Download ", 0, null);
            }
            
        } else if (scrnMsg.xxDplyTab.getValue().equals(NFAL0200Constant.BR_TAB)) {
            
            if (scrnMsg.C.getValidCount() > 0) {
                scrnAppli.setButtonProperties("DownloadCoaBtn", "DownloadCoaBtn", "COA Download ", 1, null);
            } else {
                scrnAppli.setButtonProperties("DownloadCoaBtn", "", "COA Download ", 0, null);
            }
            
        } else if (scrnMsg.xxDplyTab.getValue().equals(NFAL0200Constant.CC_TAB)) {
            
            if (scrnMsg.D.getValidCount() > 0) {
                scrnAppli.setButtonProperties("DownloadCoaBtn", "DownloadCoaBtn", "COA Download ", 1, null);
            } else {
                scrnAppli.setButtonProperties("DownloadCoaBtn", "", "COA Download ", 0, null);
            }
            
        } else if (scrnMsg.xxDplyTab.getValue().equals(NFAL0200Constant.ACCT_TAB)) {
            
            if (scrnMsg.E.getValidCount() > 0) {
                scrnAppli.setButtonProperties("DownloadCoaBtn", "DownloadCoaBtn", "COA Download ", 1, null);
            } else {
                scrnAppli.setButtonProperties("DownloadCoaBtn", "", "COA Download ", 0, null);
            }
            
        } else if (scrnMsg.xxDplyTab.getValue().equals(NFAL0200Constant.PROJ_TAB)) {
            
            if (scrnMsg.F.getValidCount() > 0) {
                scrnAppli.setButtonProperties("DownloadCoaBtn", "DownloadCoaBtn", "COA Download ", 1, null);
            } else {
                scrnAppli.setButtonProperties("DownloadCoaBtn", "", "COA Download ", 0, null);
            }
            
        } else if (scrnMsg.xxDplyTab.getValue().equals(NFAL0200Constant.PROD_TAB)) {
            
            if (scrnMsg.G.getValidCount() > 0) {
                scrnAppli.setButtonProperties("DownloadCoaBtn", "DownloadCoaBtn", "COA Download ", 1, null);
            } else {
                scrnAppli.setButtonProperties("DownloadCoaBtn", "", "COA Download ", 0, null);
            }
            
        } else if (scrnMsg.xxDplyTab.getValue().equals(NFAL0200Constant.AFFL_TAB)) {
            
            if (scrnMsg.H.getValidCount() > 0) {
                scrnAppli.setButtonProperties("DownloadCoaBtn", "DownloadCoaBtn", "COA Download ", 1, null);
            } else {
                scrnAppli.setButtonProperties("DownloadCoaBtn", "", "COA Download ", 0, null);
            }
            
        } else if (scrnMsg.xxDplyTab.getValue().equals(NFAL0200Constant.EXTN_TAB)) {
            
            if (scrnMsg.I.getValidCount() > 0) {
                scrnAppli.setButtonProperties("DownloadCoaBtn", "DownloadCoaBtn", "COA Download ", 1, null);
            } else {
                scrnAppli.setButtonProperties("DownloadCoaBtn", "", "COA Download ", 0, null);
            }
        }
        
    }
    
    public static boolean isNeedSearch(NFAL0200BMsg scrnMsg, String selTab) {
        
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox.getValue())) {
            if (scrnMsg.A.getValidCount() > 0) {
                return true;
            } else {
                // If no result in the combination list, clear the lists in tabs.
                if (NFAL0200Constant.BR_TAB.equals(selTab)) {
                    scrnMsg.C.clear();
                    scrnMsg.C.setValidCount(0);
                } else if (NFAL0200Constant.CC_TAB.equals(selTab)) {
                    scrnMsg.D.clear();
                    scrnMsg.D.setValidCount(0);
                }  else if (NFAL0200Constant.ACCT_TAB.equals(selTab)) {
                    scrnMsg.E.clear();
                    scrnMsg.E.setValidCount(0);
                } else if (NFAL0200Constant.PROD_TAB.equals(selTab)) {
                    scrnMsg.G.clear();
                    scrnMsg.G.setValidCount(0);
                } else if (NFAL0200Constant.CH_TAB.equals(selTab)) {
                    scrnMsg.B.clear();
                    scrnMsg.B.setValidCount(0);
                } else if (NFAL0200Constant.AFFL_TAB.equals(selTab)) {
                    scrnMsg.H.clear();
                    scrnMsg.H.setValidCount(0);
                } else if (NFAL0200Constant.PROJ_TAB.equals(selTab)) {
                    scrnMsg.F.clear();
                    scrnMsg.F.setValidCount(0);
                } else if (NFAL0200Constant.EXTN_TAB.equals(selTab)) {
                    scrnMsg.I.clear();
                    scrnMsg.I.setValidCount(0);
                }
            }
        } else {
            if (NFAL0200Constant.BR_TAB.equals(selTab)) {
                
                if (hasValue(scrnMsg.coaBrCd.getValue())) {
                    return true;
                } else {
                    scrnMsg.C.clear();
                    scrnMsg.C.setValidCount(0);
                }
                
            } else if (NFAL0200Constant.CC_TAB.equals(selTab)) {
                
                if (hasValue(scrnMsg.coaCcCd.getValue())) {
                    return true;
                } else {
                    scrnMsg.D.clear();
                    scrnMsg.D.setValidCount(0);
                }
            }  else if (NFAL0200Constant.ACCT_TAB.equals(selTab)) {
                
                if (hasValue(scrnMsg.coaAcctCd.getValue())) {
                    return true;
                } else {
                    scrnMsg.E.clear();
                    scrnMsg.E.setValidCount(0);
                }
            } else if (NFAL0200Constant.PROD_TAB.equals(selTab)) {
                
                if (hasValue(scrnMsg.coaProdCd.getValue())) {
                    return true;
                } else {
                    scrnMsg.G.clear();
                    scrnMsg.G.setValidCount(0);
                }
            } else if (NFAL0200Constant.CH_TAB.equals(selTab)) {
                
                if (hasValue(scrnMsg.coaChCd.getValue())) {
                    return true;
                } else {
                    scrnMsg.B.clear();
                    scrnMsg.B.setValidCount(0);
                }
            } else if (NFAL0200Constant.AFFL_TAB.equals(selTab)) {
                
                if (hasValue(scrnMsg.coaAfflCd.getValue())) {
                    return true;
                } else {
                    scrnMsg.H.clear();
                    scrnMsg.H.setValidCount(0);
                }
            } else if (NFAL0200Constant.PROJ_TAB.equals(selTab)) {
                
                if (hasValue(scrnMsg.coaProjCd.getValue())) {
                    return true;
                } else {
                    scrnMsg.F.clear();
                    scrnMsg.F.setValidCount(0);
                }
            } else if (NFAL0200Constant.EXTN_TAB.equals(selTab)) {
                
                if (hasValue(scrnMsg.coaExtnCd.getValue())) {
                    return true;
                } else {
                    scrnMsg.I.clear();
                    scrnMsg.I.setValidCount(0);
                }
            }
        }
        
        // set tab
        setValue(scrnMsg.xxDplyTab, selTab);
        
        return false;
        
    }

    // START 2016/11/02 J.Kim [QC#13443,ADD]
    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NFAL0200BMsg
     * @param int index
     * @return Object[]
     */
    public static Object[] setParamForBrPopup(NFAL0200BMsg scrnMsg, int index) {

        scrnMsg.xxTblNm_P.clear();
        scrnMsg.xxTblCdColNm_P.clear();
        scrnMsg.xxTblNmColNm_P.clear();
        scrnMsg.xxTblSortColNm_P.clear();
        scrnMsg.xxScrNm_P.clear();
        scrnMsg.xxHdrCdLbNm_P.clear();
        scrnMsg.xxHdrNmLbNm_P.clear();
        scrnMsg.xxDtlCdLbNm_P.clear();
        scrnMsg.xxDtlNmLbNm_P.clear();
        scrnMsg.coaBrNm_P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, NFAL0200Constant.TBL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, NFAL0200Constant.TBL_CD_COL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, NFAL0200Constant.TBL_NM_COL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, NFAL0200Constant.TBL_SORT_COL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, NFAL0200Constant.SCR_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, NFAL0200Constant.HDR_CD_LB_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, NFAL0200Constant.HDR_NM_LB_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, NFAL0200Constant.DTL_CD_LB_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, NFAL0200Constant.DTL_NM_LB_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.contrCoaBrCd_P, scrnMsg.C.no(index).contrCoaBrCd_C);

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
        params[9] = scrnMsg.contrCoaBrCd_P;
        params[10] = scrnMsg.coaBrNm_P;

        return params;
    }
    // END 2016/11/02 J.Kim [QC#13443,ADD]

    // START 2017/11/30 [QC#12525, ADD]
    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NFAL0200BMsg
     * @return Object[]
     */
    public static Object[] setParamForCoaAcctPopup(NFAL0200BMsg scrnMsg) {

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

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, NFAL0200Constant.TBL_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, NFAL0200Constant.TBL_CD_COL_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, NFAL0200Constant.TBL_NM_COL_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, NFAL0200Constant.TBL_SORT_COL_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, NFAL0200Constant.SCR_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, NFAL0200Constant.HDR_CD_LB_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, NFAL0200Constant.HDR_NM_LB_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, NFAL0200Constant.DTL_CD_LB_NM_FOR_ACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, NFAL0200Constant.DTL_NM_LB_NM_FOR_ACCT);

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

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NFAL0200BMsg
     * @return Object[]
     */
    public static Object[] setParamForCoaAfflPopup(NFAL0200BMsg scrnMsg) {

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

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, NFAL0200Constant.TBL_NM_FOR_AFFL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, NFAL0200Constant.TBL_CD_COL_NM_FOR_AFFL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, NFAL0200Constant.TBL_NM_COL_NM_FOR_AFFL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, NFAL0200Constant.TBL_SORT_COL_NM_FOR_AFFL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, NFAL0200Constant.SCR_NM_FOR_AFFL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, NFAL0200Constant.HDR_CD_LB_NM_FOR_AFFL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, NFAL0200Constant.HDR_NM_LB_NM_FOR_AFFL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, NFAL0200Constant.DTL_CD_LB_NM_FOR_AFFL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, NFAL0200Constant.DTL_NM_LB_NM_FOR_AFFL);

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
    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NFAL0200BMsg
     * @return Object[]
     */
    public static Object[] setParamForCoaBrPopup(NFAL0200BMsg scrnMsg) {

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

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, NFAL0200Constant.TBL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, NFAL0200Constant.TBL_CD_COL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, NFAL0200Constant.TBL_NM_COL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, NFAL0200Constant.TBL_SORT_COL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, NFAL0200Constant.SCR_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, NFAL0200Constant.HDR_CD_LB_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, NFAL0200Constant.HDR_NM_LB_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, NFAL0200Constant.DTL_CD_LB_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, NFAL0200Constant.DTL_NM_LB_NM_FOR_BR);

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

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NFAL0200BMsg
     * @return Object[]
     */
    public static Object[] setParamForCoaCcPopup(NFAL0200BMsg scrnMsg) {

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

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, NFAL0200Constant.TBL_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, NFAL0200Constant.TBL_CD_COL_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, NFAL0200Constant.TBL_NM_COL_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, NFAL0200Constant.TBL_SORT_COL_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, NFAL0200Constant.SCR_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, NFAL0200Constant.HDR_CD_LB_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, NFAL0200Constant.HDR_NM_LB_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, NFAL0200Constant.DTL_CD_LB_NM_FOR_CC);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, NFAL0200Constant.DTL_NM_LB_NM_FOR_CC);

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

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NFAL0200BMsg
     * @return Object[]
     */
    public static Object[] setParamForCoaChPopup(NFAL0200BMsg scrnMsg) {

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

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, NFAL0200Constant.TBL_NM_FOR_CH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, NFAL0200Constant.TBL_CD_COL_NM_FOR_CH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, NFAL0200Constant.TBL_NM_COL_NM_FOR_CH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, NFAL0200Constant.TBL_SORT_COL_NM_FOR_CH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, NFAL0200Constant.SCR_NM_FOR_CH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, NFAL0200Constant.HDR_CD_LB_NM_FOR_CH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, NFAL0200Constant.HDR_NM_LB_NM_FOR_CH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, NFAL0200Constant.DTL_CD_LB_NM_FOR_CH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, NFAL0200Constant.DTL_NM_LB_NM_FOR_CH);

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

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NFAL0200BMsg
     * @return Object[]
     */
    public static Object[] setParamForCoaExtnPopup(NFAL0200BMsg scrnMsg) {

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

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, NFAL0200Constant.TBL_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, NFAL0200Constant.TBL_CD_COL_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, NFAL0200Constant.TBL_NM_COL_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, NFAL0200Constant.TBL_SORT_COL_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, NFAL0200Constant.SCR_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, NFAL0200Constant.HDR_CD_LB_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, NFAL0200Constant.HDR_NM_LB_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, NFAL0200Constant.DTL_CD_LB_NM_FOR_EXTN);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, NFAL0200Constant.DTL_NM_LB_NM_FOR_EXTN);

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

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NFAL0200BMsg
     * @return Object[]
     */
    public static Object[] setParamForCoaProdPopup(NFAL0200BMsg scrnMsg) {

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

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, NFAL0200Constant.TBL_NM_FOR_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, NFAL0200Constant.TBL_CD_COL_NM_FOR_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, NFAL0200Constant.TBL_NM_COL_NM_FOR_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, NFAL0200Constant.TBL_SORT_COL_NM_FOR_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, NFAL0200Constant.SCR_NM_FOR_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, NFAL0200Constant.HDR_CD_LB_NM_FOR_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, NFAL0200Constant.HDR_NM_LB_NM_FOR_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, NFAL0200Constant.DTL_CD_LB_NM_FOR_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, NFAL0200Constant.DTL_NM_LB_NM_FOR_PROD);

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

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NFAL0200BMsg
     * @return Object[]
     */
    public static Object[] setParamForCoaProjPopup(NFAL0200BMsg scrnMsg) {

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

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, NFAL0200Constant.TBL_NM_FOR_PROJ);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, NFAL0200Constant.TBL_CD_COL_NM_FOR_PROJ);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, NFAL0200Constant.TBL_NM_COL_NM_FOR_PROJ);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, NFAL0200Constant.TBL_SORT_COL_NM_FOR_PROJ);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, NFAL0200Constant.SCR_NM_FOR_PROJ);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, NFAL0200Constant.HDR_CD_LB_NM_FOR_PROJ);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, NFAL0200Constant.HDR_NM_LB_NM_FOR_PROJ);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, NFAL0200Constant.DTL_CD_LB_NM_FOR_PROJ);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, NFAL0200Constant.DTL_NM_LB_NM_FOR_PROJ);

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
    // END   2017/11/30 [QC#17089, ADD]
}
