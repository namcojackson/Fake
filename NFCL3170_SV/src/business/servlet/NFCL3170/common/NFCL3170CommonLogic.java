package business.servlet.NFCL3170.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

import business.servlet.NFCL3170.NFCL3170BMsg;
import business.servlet.NFCL3170.constant.NFCL3170Constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/07   Fujitsu         T.Tanaka        Create          N/A
 * 2016/02/01   Fujitsu         T.Tanaka        Update          Def#2625
 * 2016/05/27   Fujitsu         S.Fujita        Update          QC#8534
 * 2016/12/16   Fujitsu         T.Murai         Update          QC#16533
 * 2023/05/24   Hitachi         R.Takau         Update          QC#55645
 *</pre>
 */
public class NFCL3170CommonLogic implements NFCL3170Constant {
    
    public static void initialize_New(EZDCommonHandler scrnAppli, NFCL3170BMsg scrnMsg) {
        
        scrnMsg.setInputProtected(false);
        
        scrnAppli.setButtonProperties("Click_SetCustomerName", "Click_SetCustomerName", ">>", 0, null);
        scrnAppli.setButtonProperties("Add", "Add", "Add", 0, null);
        scrnAppli.setButtonProperties("Delete", "Delete", "Delete", 0, null);
        scrnAppli.setButtonProperties("New", "New", "New", 0, null);

        scrnAppli.setButtonProperties("btn1" , ""           , "Save"    , 0, null);
        scrnAppli.setButtonProperties("btn2" , "CMN_Submit" , "Submit"  , 1, null);
        scrnAppli.setButtonProperties("btn3" , ""           , "Apply"   , 0, null);
        scrnAppli.setButtonProperties("btn4" , ""           , "Approve" , 0, null);
        scrnAppli.setButtonProperties("btn5" , ""           , "Reject"  , 0, null);
        scrnAppli.setButtonProperties("btn6" , ""           , "Download", 0, null);
        scrnAppli.setButtonProperties("btn7" , ""           , "Delete"  , 0, null);
        scrnAppli.setButtonProperties("btn8" , "CMN_Clear"  , "Clear"   , 0, null);
        scrnAppli.setButtonProperties("btn9" , "CMN_Reset"  , "Reset"   , 1, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return" , "Return"  , 1, null);
        
        if(scrnMsg.xxBizAppId.getValue().equals(BUSINESS_ID)) {
            scrnAppli.setButtonProperties("btn8" , "CMN_Clear"  , "Clear"   , 1, null);
            scrnAppli.setButtonProperties("btn9" , "CMN_Reset"  , "Reset"   , 0, null);
        } else {
            scrnAppli.setButtonProperties("btn8" , "CMN_Clear"  , "Clear"   , 1, null);
            // START 2016/05/27 S.Fujita [QC#8534,MOD]
//            scrnAppli.setButtonProperties("btn9" , "CMN_Reset"  , "Reset"   , 1, null);
            scrnAppli.setButtonProperties("btn9" , "CMN_Reset"  , "Reset"   , 0, null);
            // END   2016/05/27 S.Fujita [QC#8534,MOD]
        }
        
        //-----------------------------------------
        // Set Mandatory
        //-----------------------------------------
        // START 2023/06/15 R.Takau [QC#55645, DEL]
        // scrnMsg.dsBankAcctNm_H1.setIndispensable(true);
        // scrnMsg.dsBankBrNm_H1.setIndispensable(true);
        // START 2023/06/15 R.Takau [QC#55645, DEL]
        // START 2023/05/24 R.Takau [QC#55645, ADD]
        scrnMsg.bankRteNum_H1.setIndispensable(true);
        // END 2023/05/24 R.Takau [QC#55645, ADD]
        scrnMsg.dsBankAcctNum_H1.setIndispensable(true);
        scrnMsg.effFromDt_H1.setIndispensable(true);

        // START 2016/05/27 S.Fujita [QC#8534,ADD]
        // START 2016/12/13 T.Murai [QC#16533,MOD]
//        scrnMsg.firstLineAddr_H1.setIndispensable(true);
//        scrnMsg.ctyAddr_H1.setIndispensable(true);
//        scrnMsg.cntyNm_H1.setIndispensable(true);
//        scrnMsg.stCd_H1.setIndispensable(true);
//        scrnMsg.postCd_H1.setIndispensable(true);

        // External not Mandatory
        scrnMsg.firstLineAddr_H1.setIndispensable(false);
        scrnMsg.ctyAddr_H1.setIndispensable(false);
        scrnMsg.cntyNm_H1.setIndispensable(false);
        scrnMsg.stCd_H1.setIndispensable(false);
        scrnMsg.postCd_H1.setIndispensable(false);
        // END   2016/12/13 T.Murai [QC#16533,MOD]
        // END   2016/05/27 S.Fujita [QC#8534,ADD]

        // START 2023/06/15 R.Takau [QC#55645, DEL]
        //scrnMsg.dsBankAcctNm_H1.setIndispensable(true);
        // START 2023/06/15 R.Takau [QC#55645, DEL]

        //-----------------------------------------
        // Set Protect
        //-----------------------------------------
        scrnMsg.dsAcctNum_H1.setInputProtected(true);
        scrnMsg.dsAcctNm_H1.setInputProtected(true);
        scrnMsg.locNum_H1.setInputProtected(true);

        //-----------------------------------------
        // Initial
        //-----------------------------------------
        scrnMsg.xxRadioBtn.setValue(BigDecimal.ONE);
        
        NFCL3170CommonLogic.setCustomerProtect(scrnAppli, scrnMsg);
        
    }

    public static void initialize_ReadOnly(EZDCommonHandler scrnAppli, NFCL3170BMsg scrnMsg) {
        
        scrnMsg.setInputProtected(false);
        
        scrnAppli.setButtonProperties("Click_SetCustomerName", "Click_SetCustomerName", ">>", 0, null);
        scrnAppli.setButtonProperties("Add", "Add", "Add", 0, null);
        scrnAppli.setButtonProperties("Delete", "Delete", "Delete", 0, null);
        scrnAppli.setButtonProperties("New", "New", "New", 0, null);

        scrnAppli.setButtonProperties("btn1" , ""           , "Save"    , 0, null);
        scrnAppli.setButtonProperties("btn2" , "CMN_Submit" , "Submit"  , 0, null);
        scrnAppli.setButtonProperties("btn3" , ""           , "Apply"   , 0, null);
        scrnAppli.setButtonProperties("btn4" , ""           , "Approve" , 0, null);
        scrnAppli.setButtonProperties("btn5" , ""           , "Reject"  , 0, null);
        scrnAppli.setButtonProperties("btn6" , ""           , "Download", 0, null);
        scrnAppli.setButtonProperties("btn7" , ""           , "Delete"  , 0, null);
        scrnAppli.setButtonProperties("btn8" , "CMN_Clear"  , "Clear"   , 0, null);
        scrnAppli.setButtonProperties("btn9" , "CMN_Reset"  , "Reset"   , 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return" , "Return"  , 1, null);
        
        //-----------------------------------------
        // Set Mandatory
        //-----------------------------------------

        //-----------------------------------------
        // Set Protect
        //-----------------------------------------
        scrnMsg.dsBankAcctNm_H1.setInputProtected(true);
        scrnMsg.dsBankBrNm_H1.setInputProtected(true);
        scrnMsg.bankRteNum_H1.setInputProtected(true);
        scrnMsg.dsBankAcctNum_H1.setInputProtected(true);
        scrnMsg.xxRadioBtn.setInputProtected(true);
        scrnMsg.effFromDt_H1.setInputProtected(true);
        scrnMsg.effThruDt_H1.setInputProtected(true);
        
        scrnMsg.firstLineAddr_H1.setInputProtected(true);
        scrnMsg.scdLineAddr_H1.setInputProtected(true);
        scrnMsg.ctyAddr_H1.setInputProtected(true);
        scrnMsg.cntyNm_H1.setInputProtected(true);
        scrnMsg.stCd_H1.setInputProtected(true);
        scrnMsg.postCd_H1.setInputProtected(true);

        scrnMsg.dsAcctNum_H1.setInputProtected(true);
        scrnMsg.dsAcctNm_H1.setInputProtected(true);
        scrnMsg.locNum_H1.setInputProtected(true);

    }

    /**
     * 
     * @param scrnAppli
     * @param scrnMsg
     */
    public static void initialize_Update(EZDCommonHandler scrnAppli, NFCL3170BMsg scrnMsg) {
        
        scrnMsg.setInputProtected(false);
        
        scrnAppli.setButtonProperties("Click_SetCustomerName", "Click_SetCustomerName", ">>", 0, null);
        scrnAppli.setButtonProperties("Add", "Add", "Add", 0, null);
        scrnAppli.setButtonProperties("Delete", "Delete", "Delete", 0, null);
        scrnAppli.setButtonProperties("New", "New", "New", 0, null);

        scrnAppli.setButtonProperties("btn1" , ""           , "Save"    , 0, null);
        scrnAppli.setButtonProperties("btn2" , "CMN_Submit" , "Submit"  , 1, null);
        scrnAppli.setButtonProperties("btn3" , ""           , "Apply"   , 0, null);
        scrnAppli.setButtonProperties("btn4" , ""           , "Approve" , 0, null);
        scrnAppli.setButtonProperties("btn5" , ""           , "Reject"  , 0, null);
        scrnAppli.setButtonProperties("btn6" , ""           , "Download", 0, null);
        scrnAppli.setButtonProperties("btn7" , ""           , "Delete"  , 0, null);
        scrnAppli.setButtonProperties("btn8" , "CMN_Clear"  , "Clear"   , 1, null);
        scrnAppli.setButtonProperties("btn9" , "CMN_Reset"  , "Reset"   , 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return" , "Return"  , 1, null);
        
        if(scrnMsg.xxBizAppId.getValue().equals(BUSINESS_ID)) {
            scrnAppli.setButtonProperties("btn8" , "CMN_Clear"  , "Clear"   , 1, null);
            scrnAppli.setButtonProperties("btn9" , "CMN_Reset"  , "Reset"   , 0, null);
        } else {
            // START 2016/05/27 S.Fujita [QC#8534,MOD]
//            scrnAppli.setButtonProperties("btn8" , "CMN_Clear"  , "Clear"   , 1, null);
            scrnAppli.setButtonProperties("btn8" , "CMN_Clear"  , "Clear"   , 0, null);
            // END   2016/05/27 S.Fujita [QC#8534,MOD]
            scrnAppli.setButtonProperties("btn9" , "CMN_Reset"  , "Reset"   , 1, null);
        }

        //-----------------------------------------
        // Set Mandatory
        //-----------------------------------------
        // START 2023/06/15 R.Takau [QC#55645, DEL]
        // scrnMsg.dsBankAcctNm_H1.setIndispensable(true);
        // scrnMsg.dsBankBrNm_H1.setIndispensable(true);
        // START 2023/06/15 R.Takau [QC#55645, DEL]
        scrnMsg.dsBankAcctNum_H1.setIndispensable(true);
        // START 2023/05/24 R.Takau [QC#55645, ADD]
        scrnMsg.bankRteNum_H1.setIndispensable(true);
        // END 2023/05/24 R.Takau [QC#55645, ADD]

        // START 2016/05/27 S.Fujita [QC#8534,ADD]
        // START 2016/12/13 T.Murai [QC#16533,MOD]
//        scrnMsg.firstLineAddr_H1.setIndispensable(true);
//        scrnMsg.ctyAddr_H1.setIndispensable(true);
//        scrnMsg.cntyNm_H1.setIndispensable(true);
//        scrnMsg.stCd_H1.setIndispensable(true);
//        scrnMsg.postCd_H1.setIndispensable(true);
        if (BigDecimal.ONE.compareTo(scrnMsg.xxRadioBtn.getValue()) != 0) {
            // Internal Mandatory
            scrnMsg.firstLineAddr_H1.setIndispensable(true);
            scrnMsg.ctyAddr_H1.setIndispensable(true);
            scrnMsg.cntyNm_H1.setIndispensable(true);
            scrnMsg.stCd_H1.setIndispensable(true);
            scrnMsg.postCd_H1.setIndispensable(true);
        } else {
            scrnMsg.xxRadioBtn.setValue(BigDecimal.ONE);
            // External not Mandatory
            scrnMsg.firstLineAddr_H1.setIndispensable(false);
            scrnMsg.ctyAddr_H1.setIndispensable(false);
            scrnMsg.cntyNm_H1.setIndispensable(false);
            scrnMsg.stCd_H1.setIndispensable(false);
            scrnMsg.postCd_H1.setIndispensable(false);
        }
        // END 2016/12/13 T.Murai [QC#16533,MOD]
        // END 2016/05/27 S.Fujita [QC#8534,ADD]
        //-----------------------------------------
        // Set Protect
        //-----------------------------------------
        scrnMsg.dsAcctNum_H1.setInputProtected(true);
        scrnMsg.dsAcctNm_H1.setInputProtected(true);
        scrnMsg.locNum_H1.setInputProtected(true);

        if(ZYPCommonFunc.hasValue(scrnMsg.dsBankAcctPk_H1.getValue())){
            scrnMsg.effFromDt_H1.setInputProtected(true);
        } else {
            scrnMsg.effFromDt_H1.setInputProtected(false);
        }
        
        if(BigDecimal.ONE.compareTo(scrnMsg.xxRadioBtn.getValue()) == 0) {
            scrnMsg.dsAcctNm_L1.setInputProtected(false);
            scrnMsg.dsAcctNum_L1.setInputProtected(false);
            scrnMsg.locNum_L1.setInputProtected(false);

        } else {
            scrnMsg.dsAcctNm_L1.setInputProtected(true);
            scrnMsg.dsAcctNum_L1.setInputProtected(true);
            scrnMsg.locNum_L1.setInputProtected(true);
        }
        
        NFCL3170CommonLogic.setCustomerProtect(scrnAppli, scrnMsg);

    }


    /**
     * 
     * @param scrnMsg
     */
    public static void clearBranch(NFCL3170BMsg scrnMsg) {
        scrnMsg.bankRteNum_H1.clear();
        scrnMsg.dsBankBrNm_H1.clear();
        scrnMsg.firstLineAddr_H1.clear();
        scrnMsg.scdLineAddr_H1.clear();
        scrnMsg.ctyAddr_H1.clear();
        scrnMsg.cntyNm_H1.clear();
        scrnMsg.stCd_H1.clear();
        scrnMsg.postCd_H1.clear();
    }

    /**
     * 
     * @param scrnMsg
     */
    public static void setBranch(NFCL3170BMsg scrnMsg) {
        scrnMsg.dsBankBrNm_H1.clear();
        scrnMsg.firstLineAddr_H1.clear();
        scrnMsg.scdLineAddr_H1.clear();
        scrnMsg.ctyAddr_H1.clear();
        scrnMsg.cntyNm_H1.clear();
        scrnMsg.stCd_H1.clear();
        scrnMsg.postCd_H1.clear();
    }

    /**
     * 
     * @param scrnAppli
     * @param scrnMsg
     */
    public static void setProtect_NewBranch(EZDCommonHandler scrnAppli, NFCL3170BMsg scrnMsg) {
        
        if(scrnMsg.xxModeCd.getValue().equals(NEW_BUTTON_OFF)) {
            scrnMsg.bankRteNum_H1.clear();
            scrnMsg.bankRteNum_H1.setIndispensable(false);
            scrnMsg.bankRteNum_H1.setInputProtected(true);
            
            scrnMsg.dsBankBrNm_H1.setInputProtected(false);
            scrnMsg.firstLineAddr_H1.setInputProtected(false);
            scrnMsg.scdLineAddr_H1.setInputProtected(false);
            scrnMsg.ctyAddr_H1.setInputProtected(false);
            scrnMsg.cntyNm_H1.setInputProtected(false);
            scrnMsg.stCd_H1.setInputProtected(false);
            scrnMsg.postCd_H1.setInputProtected(false);

            // START 2023/06/15 R.Takau [QC#55645, DEL]
            // scrnMsg.dsBankBrNm_H1.setIndispensable(true);
            // END 2023/06/15 R.Takau [QC#55645, DEL]

            scrnMsg.firstLineAddr_H1.setIndispensable(true);
            scrnMsg.ctyAddr_H1.setIndispensable(true);
            scrnMsg.stCd_H1.setIndispensable(true);
            scrnMsg.postCd_H1.setIndispensable(true);
            
            scrnAppli.setButtonProperties("New", "New", "Reset", 1, null);
            scrnMsg.xxModeCd.setValue(NEW_BUTTON_ON);
        } else {
            scrnMsg.bankRteNum_H1.setIndispensable(true);
            scrnMsg.bankRteNum_H1.setInputProtected(false);
            
            scrnMsg.dsBankBrNm_H1.setInputProtected(true);
            scrnMsg.firstLineAddr_H1.setInputProtected(true);
            scrnMsg.scdLineAddr_H1.setInputProtected(true);
            scrnMsg.ctyAddr_H1.setInputProtected(true);
            scrnMsg.cntyNm_H1.setInputProtected(true);
            scrnMsg.stCd_H1.setInputProtected(true);
            scrnMsg.postCd_H1.setInputProtected(true);

            scrnMsg.dsBankBrNm_H1.setIndispensable(false);
            scrnMsg.firstLineAddr_H1.setIndispensable(false);
            scrnMsg.ctyAddr_H1.setIndispensable(false);
            scrnMsg.stCd_H1.setIndispensable(false);
            scrnMsg.postCd_H1.setIndispensable(false);
            
            scrnAppli.setButtonProperties("New", "New", "New", 1, null);
            scrnMsg.xxModeCd.setValue(NEW_BUTTON_OFF);
        }

        scrnMsg.dsBankAcctNum_H1.setInputProtected(true);
        scrnMsg.dsBankAcctNm_H1.setInputProtected(true);
        scrnMsg.dsBankMicrNum_H1.setInputProtected(true);
        scrnMsg.xxRadioBtn.setInputProtected(true);
        scrnMsg.effFromDt_H1.setInputProtected(true);
        scrnMsg.effThruDt_H1.setInputProtected(true);
        
        // DS_CUST_BANK_ACCT_RELN
        scrnMsg.dsAcctNum_H1.setInputProtected(true);
        scrnMsg.dsAcctNm_H1.setInputProtected(true);

    }

    /**
     * 
     * @param scrnMsg
     */
    public static void clearCustomer(NFCL3170BMsg scrnMsg) {
        
    }
    
    /**
     * 
     * @param scrnMsg
     */
    public static void checkSubmit_BankBranch(NFCL3170BMsg scrnMsg) {
        
        scrnMsg.addCheckItem(scrnMsg.dsBankBrNm_H1);
        scrnMsg.addCheckItem(scrnMsg.firstLineAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.stCd_H1);
        scrnMsg.addCheckItem(scrnMsg.postCd_H1);
    }
    

    /**
     * 
     * @param scrnMsg
     */
    public static void chkecItem_Submit(NFCL3170BMsg scrnMsg) {
        
        scrnMsg.addCheckItem(scrnMsg.dsBankAcctNm_H1);
        scrnMsg.addCheckItem(scrnMsg.dsBankBrNm_H1);
        scrnMsg.addCheckItem(scrnMsg.dsBankAcctNum_H1);
        // START 2023/05/24 R.Takau [QC#55645, ADD]
        scrnMsg.addCheckItem(scrnMsg.bankRteNum_H1);
        // END 2023/05/24 R.Takau [QC#55645, ADD]
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
//        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
//        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H1);
//        scrnMsg.addCheckItem(scrnMsg.locNum_H1);
    }

    /**
     * 
     * @param scrnAppli
     * @param scrnMsg
     */
    public static void setCustomerProtect(EZDCommonHandler scrnAppli, NFCL3170BMsg scrnMsg) {
        
        // Radio Button Protect
        if(BigDecimal.ONE.compareTo(scrnMsg.xxRadioBtn.getValue()) == 0) {
            boolean isExists = false;
            for(int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if(ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dsCustBankAcctRelnPk_A1.getValue())) {
                    isExists = true;
                    break;
                }
            }
            if(isExists) {
                scrnMsg.xxRadioBtn.setInputProtected(true);
            }
        }
        // internal Data Clear
        if(BigDecimal.ONE.compareTo(scrnMsg.xxRadioBtn.getValue()) != 0) {
            scrnMsg.A.clear();
            scrnMsg.A.setValidCount(0);
        }
        
        // External
        if(BigDecimal.ONE.compareTo(scrnMsg.xxRadioBtn.getValue()) == 0) {
            // Protect
            scrnMsg.dsAcctNum_H1.setInputProtected(true);
            scrnMsg.dsAcctNm_H1.setInputProtected(true);
            scrnMsg.locNum_H1.setInputProtected(true);

            scrnMsg.dsAcctNum_L1.setInputProtected(false);
            scrnMsg.dsAcctNm_L1.setInputProtected(false);
            scrnMsg.locNum_L1.setInputProtected(false);
            
            scrnAppli.setButtonProperties("Add", "Add", "Add", 1, null);
            if(scrnMsg.A.getValidCount() > 0) {
                scrnAppli.setButtonProperties("Delete", "Delete", "Delete", 1, null);
            } else {
                scrnAppli.setButtonProperties("Delete", "Delete", "Delete", 0, null);
            }
            
            for(int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).dsAcctNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).locNum_A1.setInputProtected(true);
            }

        } else {
            // Protect
            scrnMsg.dsAcctNum_H1.setInputProtected(true);
            scrnMsg.dsAcctNm_H1.setInputProtected(true);
            scrnMsg.locNum_H1.setInputProtected(true);
            
            scrnMsg.dsAcctNum_H1.clear();
            scrnMsg.dsAcctNm_H1.clear();
            scrnMsg.locNum_H1.clear();
            
            scrnMsg.dsAcctNum_L1.setInputProtected(true);
            scrnMsg.dsAcctNm_L1.setInputProtected(true);
            scrnMsg.locNum_L1.setInputProtected(true);

            scrnAppli.setButtonProperties("Add", "Add", "Add", 0, null);
            scrnAppli.setButtonProperties("Delete", "Delete", "Delete", 0, null);
            
            for(int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).locNum_A1.setInputProtected(true);
            }

        }
    }
    // START 2016/05/27 S.Fujita [QC#8534,ADD]
    /**
     * addressLookupPopupParameter
     * @param scrnMsg
     * @param glblCmpyCd
     * @param eventName
     */
    public static Object[] addressLookupPopupParameter(NFCL3170BMsg scrnMsg, String glblCmpyCd, String eventName) {
        scrnMsg.xxScrEventNm.setValue(eventName);

        Object[] params = new Object[7];

        StringBuilder baseSql = new StringBuilder();
        baseSql.append("SELECT ");
        baseSql.append("    P.GLBL_CMPY_CD ");
        baseSql.append("  , P.EZCANCELFLAG ");
        baseSql.append("  , P.CTY_ADDR ");
        baseSql.append("  , P.ST_CD ");
        baseSql.append("  , P.POST_CD ");
        baseSql.append("  , C.CNTY_NM ");
        baseSql.append("FROM ");
        baseSql.append("    POST P ");
        baseSql.append("  , CNTY_POST_RELN R ");
        baseSql.append("  , CNTY C ");
        baseSql.append("WHERE ");
        baseSql.append("    P.GLBL_CMPY_CD = '" + glblCmpyCd + "' ");
        baseSql.append("AND P.EZCANCELFLAG = '0' ");
        baseSql.append("AND R.POST_PK(+) = P.POST_PK ");
        baseSql.append("AND R.GLBL_CMPY_CD(+) = P.GLBL_CMPY_CD ");
        baseSql.append("AND R.EZCANCELFLAG(+) = '0' ");
        baseSql.append("AND C.GLBL_CMPY_CD(+) = R.GLBL_CMPY_CD ");
        baseSql.append("AND C.EZCANCELFLAG(+) = '0' ");
        baseSql.append("AND C.CNTY_PK(+) = R.CNTY_PK ");

        List<Object[]> whereList = new ArrayList<Object[]>();
        addWhereCondition(whereList, "City", "CTY_ADDR", scrnMsg.ctyAddr_H1.getValue(), "Y");
        addWhereCondition(whereList, "State", "ST_CD", scrnMsg.stCd_H1.getValue(), "Y");
        addWhereCondition(whereList, "Postal Code", "POST_CD", scrnMsg.postCd_H1.getValue(), "Y");
        addWhereCondition(whereList, "County", "CNTY_NM", scrnMsg.cntyNm_H1.getValue(), "Y");

        List<Object[]> columnList = new ArrayList<Object[]>();
        addDisplayColumn(columnList, "City", "CTY_ADDR", BigDecimal.valueOf(25), "Y");
        addDisplayColumn(columnList, "State", "ST_CD", BigDecimal.valueOf(5), "Y");
        addDisplayColumn(columnList, "Postal Code", "POST_CD", BigDecimal.valueOf(10), "Y");
        addDisplayColumn(columnList, "County", "CNTY_NM", BigDecimal.valueOf(30), "Y");

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        addSortCondition(sortConditionList, "CTY_ADDR", "ASC");
        addSortCondition(sortConditionList, "ST_CD", "ASC");
        addSortCondition(sortConditionList, "POST_CD", "ASC");
        addSortCondition(sortConditionList, "CNTY_NM", "ASC");

        scrnMsg.L.clear();

        params[0] = "";
        params[1] = "Address Lookup Popup";
        params[2] = baseSql.toString();
        params[3] = whereList;
        params[4] = columnList;
        params[5] = sortConditionList;
        params[6] = scrnMsg.L;
        
        return params;
    }

    /**
     * addWhereCondition
     * @param whereList
     * @param labelName
     * @param dbColumnName
     * @param initValue
     * @param likeConditionFlag
     */
    private static void addWhereCondition(List<Object[]> whereList, String labelName, String dbColumnName, String initValue, String likeConditionFlag){
        Object[] whereArray= new Object[4];
        whereArray[0] = labelName;
        whereArray[1] = dbColumnName;
        whereArray[2] = initValue;
        whereArray[3] = likeConditionFlag;
        whereList.add(whereArray);
    }

    /**
     * addDisplayColumn
     * @param columnList
     * @param labelName
     * @param dbColumnName
     * @param displaySize
     * @param linkFlag
     */
    private static void addDisplayColumn(List<Object[]> columnList, String labelName, String dbColumnName, BigDecimal displaySize, String linkFlag){
        Object[] columnArray = new Object[4];
        columnArray[0] = labelName;
        columnArray[1] = dbColumnName;
        columnArray[2] = displaySize;
        columnArray[3] = linkFlag;
        columnList.add(columnArray);
    }

    /**
     * addSortCondition
     * @param sortConditionList
     * @param dbColumnName
     * @param orderBy
     */
    private static void addSortCondition(List<Object[]> sortConditionList, String dbColumnName, String orderBy){
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = dbColumnName;
        sortConditionArray[1] = orderBy;
        sortConditionList.add(sortConditionArray);
    }
    // END   2016/05/27 S.Fujita [QC#8534,ADD]
    
    // START 2016/12/13 T.Murai [QC#16533,ADD]
    public static void setAddressMandatory(NFCL3170BMsg scrnMsg) {

        if (BigDecimal.ONE.compareTo(scrnMsg.xxRadioBtn.getValue()) == 0) {
            // External not Mandatory
            scrnMsg.firstLineAddr_H1.setIndispensable(false);
            scrnMsg.ctyAddr_H1.setIndispensable(false);
            scrnMsg.cntyNm_H1.setIndispensable(false);
            scrnMsg.stCd_H1.setIndispensable(false);
            scrnMsg.postCd_H1.setIndispensable(false);
        } else {
            // Internal Mandatory
            scrnMsg.firstLineAddr_H1.setIndispensable(true);
            scrnMsg.ctyAddr_H1.setIndispensable(true);
            scrnMsg.cntyNm_H1.setIndispensable(true);
            scrnMsg.stCd_H1.setIndispensable(true);
            scrnMsg.postCd_H1.setIndispensable(true);
        }
    }
    // END   2016/12/13 T.Murai [QC#16533,ADD]

}
