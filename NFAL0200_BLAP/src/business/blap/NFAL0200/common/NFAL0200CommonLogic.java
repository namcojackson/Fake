package business.blap.NFAL0200.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFAL0200.NFAL0200CMsg;
import business.blap.NFAL0200.NFAL0200Query;
import business.blap.NFAL0200.NFAL0200SMsg;
import business.blap.NFAL0200.constant.NFAL0200Constant;
import business.blap.NFAL0200.constant.NFAL0200Constant.CoaSegmentTabs;
import business.db.SAVE_SRCH_OPTTMsg;
import business.db.SAVE_SRCH_OPTTMsgArray;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/25   CSAI            K.Uramori       Create          N/A
 * 2016/08/26   Hitachi         J.Kim           Update          QC#13515
 * 2016/10/21   Hitachi         J.Kim           Update          QC#13514
 * 2016/11/02   Hitachi         J.Kim           Update          QC#13443
 * 2016/11/25   Hitachi         J.Kim           Update          QC#16240
 *</pre>
 */
public class NFAL0200CommonLogic implements ZYPConstant{

    
    public static void getCOACmbn(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg) {
        
        S21SsmEZDResult ssmResult = NFAL0200Query.getInstance().getCOACmbn(cMsg, sMsg);
        
         // search result exists
        if( ssmResult.isCodeNormal() ) {
            
            // max rows exceed warning
            int queryResCnt = ssmResult.getQueryResultCount();
            if( queryResCnt > cMsg.A.length() ) {
                cMsg.setMessageInfo( "NFAM0157I", new String[] {String.valueOf(cMsg.A.length()), String.valueOf(cMsg.A.length())});
                queryResCnt = cMsg.A.length();
            }

            // SMsg -> CMsg
            int i = 0;
            
            // initial value. Set to first line.
            setValue(cMsg.xxRadioBtn, BigDecimal.ZERO);
            
            for( ; i < sMsg.A.getValidCount(); i++ ) {
                if( i == cMsg.A.length() ) {
                    break;
                }
                EZDMsg.copy( sMsg.A.no( i ), null, cMsg.A.no( i ), null );
                
            }
            cMsg.A.setValidCount( i );

            
        // no search result
        } else {
            cMsg.A.clear();
            cMsg.A.setValidCount(0);
            cMsg.setMessageInfo( "ZZPM0037W" );
        }       

    }
    
    public static void getCOACh(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg, boolean isCmbn) {
        String coaChCd;
        
        if (isCmbn) { 
            // get selected combination's COA_CH_CD
            String cmbnVal = cMsg.A.no(cMsg.xxRadioBtn.getValueInt()).xxComnScrCondValTxt_A.getValue();
            coaChCd = getSegValue(cmbnVal, 6);
        } else {
            
            // get entered search criteria
            coaChCd = cMsg.coaChCd.getValue();
            
            if (!hasValue(coaChCd)) {
                return;
            }
        }
        
        S21SsmEZDResult ssmResult = NFAL0200Query.getInstance().getCOACh(cMsg, sMsg, coaChCd);
        
         // search result exists
        if( ssmResult.isCodeNormal() ) {
            
            // max rows exceed warning
            int queryResCnt = ssmResult.getQueryResultCount();
            if( queryResCnt > cMsg.B.length() ) {
                cMsg.setMessageInfo( "NFAM0157I" , new String[] {String.valueOf(cMsg.B.length()), String.valueOf(cMsg.B.length())});
                queryResCnt = cMsg.B.length();
            }

            // SMsg -> CMsg
            int i = 0;
            
            for( ; i < sMsg.B.getValidCount(); i++ ) {
                if( i == cMsg.B.length() ) {
                    break;
                }
                EZDMsg.copy( sMsg.B.no( i ), null, cMsg.B.no( i ), null );
                
            }
            cMsg.B.setValidCount( i );

            
        // no search result
        } else {
            cMsg.B.clear();
            cMsg.B.setValidCount(0);
            cMsg.setMessageInfo( "ZZPM0037W" );
        }       

    }
    
    public static void getCOABr(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg, boolean isCmbn) {
        
        String coaBrCd;
        
        if (isCmbn) { 
            // get selected combination's COA_BR_CD
            String cmbnVal = cMsg.A.no(cMsg.xxRadioBtn.getValueInt()).xxComnScrCondValTxt_A.getValue();
            coaBrCd = getSegValue(cmbnVal, 2);
        } else {
            
            // get entered search criteria
            coaBrCd = cMsg.coaBrCd.getValue();
            
            if (!hasValue(coaBrCd)) {
                return;
            }
        }
        
        S21SsmEZDResult ssmResult = NFAL0200Query.getInstance().getCOABr(cMsg, sMsg, coaBrCd);
        
         // search result exists
        if( ssmResult.isCodeNormal() ) {
            
            // max rows exceed warning
            int queryResCnt = ssmResult.getQueryResultCount();
            if( queryResCnt > cMsg.C.length() ) {
                cMsg.setMessageInfo( "NFAM0157I" , new String[] {String.valueOf(cMsg.C.length()), String.valueOf(cMsg.C.length())});
                queryResCnt = cMsg.C.length();
            }

            // SMsg -> CMsg
            int i = 0;
            
            for( ; i < sMsg.C.getValidCount(); i++ ) {
                if( i == cMsg.C.length() ) {
                    break;
                }
                EZDMsg.copy( sMsg.C.no( i ), null, cMsg.C.no( i ), null );
                
            }
            cMsg.C.setValidCount( i );

            
        // no search result
        } else {
            cMsg.C.clear();
            cMsg.C.setValidCount(0);
            cMsg.setMessageInfo( "ZZPM0037W" );
        }       

    }
    
    public static void getCOACc(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg, boolean isCmbn) {
        
        String coaCcCd;
        
        if (isCmbn) { 
            // get selected combination's COA_CC_CD
            String cmbnVal = cMsg.A.no(cMsg.xxRadioBtn.getValueInt()).xxComnScrCondValTxt_A.getValue();
            coaCcCd = getSegValue(cmbnVal, 3);
        } else {
            
            // get entered search criteria
            coaCcCd = cMsg.coaCcCd.getValue();
            
            if (!hasValue(coaCcCd)) {
                return;
            }
        }

        // START 2016/08/25 J.Kim [QC#13515,ADD]
        // Editable
        ZYPCodeDataUtil.createPulldownList(COA_LINE_BIZ_TP.class, cMsg.coaLineBizTpCd_C, cMsg.coaLineBizTpDescTxt_D);
        // END 2016/08/25 J.Kim [QC#13515,ADD]

        S21SsmEZDResult ssmResult = NFAL0200Query.getInstance().getCOACc(cMsg, sMsg, coaCcCd);
        
         // search result exists
        if( ssmResult.isCodeNormal() ) {
            
            // max rows exceed warning
            int queryResCnt = ssmResult.getQueryResultCount();
            if( queryResCnt > cMsg.D.length() ) {
                cMsg.setMessageInfo( "NFAM0157I" , new String[] {String.valueOf(cMsg.D.length()), String.valueOf(cMsg.D.length())});
                queryResCnt = cMsg.D.length();
            }

            // SMsg -> CMsg
            int i = 0;
            
            for( ; i < sMsg.D.getValidCount(); i++ ) {
                if( i == cMsg.D.length() ) {
                    break;
                }
                EZDMsg.copy( sMsg.D.no( i ), null, cMsg.D.no( i ), null );
                
            }
            cMsg.D.setValidCount( i );

            
        // no search result
        } else {
            cMsg.D.clear();
            cMsg.D.setValidCount(0);
            cMsg.setMessageInfo( "ZZPM0037W" );
        }       

    }
    
    public static void getCOAAcct(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg, boolean isCmbn) {
        
        String coaAcctCd;
        
        if (isCmbn) { 
            // get selected combination's COA_ACCT_CD
            String cmbnVal = cMsg.A.no(cMsg.xxRadioBtn.getValueInt()).xxComnScrCondValTxt_A.getValue();
            coaAcctCd = getSegValue(cmbnVal, 4);
        } else {
            
            // get entered search criteria
            coaAcctCd = cMsg.coaAcctCd.getValue();
            
            if (!hasValue(coaAcctCd)) {
                return;
            }
        }
        
        S21SsmEZDResult ssmResult = NFAL0200Query.getInstance().getCOAAcct(cMsg, sMsg, coaAcctCd);
        
         // search result exists
        if( ssmResult.isCodeNormal() ) {
            
            // max rows exceed warning
            int queryResCnt = ssmResult.getQueryResultCount();
            if( queryResCnt > cMsg.E.length() ) {
                cMsg.setMessageInfo( "NFAM0157I" , new String[] {String.valueOf(cMsg.E.length()), String.valueOf(cMsg.E.length())});
                queryResCnt = cMsg.E.length();
            }

            // SMsg -> CMsg
            int i = 0;
            
            for( ; i < sMsg.E.getValidCount(); i++ ) {
                if( i == cMsg.E.length() ) {
                    break;
                }
                EZDMsg.copy( sMsg.E.no( i ), null, cMsg.E.no( i ), null );
                
            }
            cMsg.E.setValidCount( i );

            
        // no search result
        } else {
            cMsg.E.clear();
            cMsg.E.setValidCount(0);
            cMsg.setMessageInfo( "ZZPM0037W" );
        }       

    }
    
    public static void getCOAProj(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg, boolean isCmbn) {
        
        String coaProjCd;
        
        if (isCmbn) { 
            // get selected combination's COA_PROJ_CD
            String cmbnVal = cMsg.A.no(cMsg.xxRadioBtn.getValueInt()).xxComnScrCondValTxt_A.getValue();
            coaProjCd = getSegValue(cmbnVal, 8);
            
            if (coaProjCd.length() == 4) {
                // get last 2 digits
                coaProjCd = coaProjCd.substring(2,4);
            }
        } else {
            
            // get entered search criteria
            coaProjCd = cMsg.coaProjCd.getValue();
            
            if (!hasValue(coaProjCd)) {
                return;
            }
        }
        
        S21SsmEZDResult ssmResult = NFAL0200Query.getInstance().getCOAProj(cMsg, sMsg, coaProjCd);
        
         // search result exists
        if( ssmResult.isCodeNormal() ) {
            
            // max rows exceed warning
            int queryResCnt = ssmResult.getQueryResultCount();
            if( queryResCnt > cMsg.F.length() ) {
                cMsg.setMessageInfo( "NFAM0157I" , new String[] {String.valueOf(cMsg.F.length()), String.valueOf(cMsg.F.length())});
                queryResCnt = cMsg.F.length();
            }

            // SMsg -> CMsg
            int i = 0;
            
            for( ; i < sMsg.F.getValidCount(); i++ ) {
                if( i == cMsg.F.length() ) {
                    break;
                }
                EZDMsg.copy( sMsg.F.no( i ), null, cMsg.F.no( i ), null );
                
            }
            cMsg.F.setValidCount( i );

            
        // no search result
        } else {
            cMsg.F.clear();
            cMsg.F.setValidCount(0);
            cMsg.setMessageInfo( "ZZPM0037W" );
        }       

    }

    public static void getCOAProd(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg, boolean isCmbn) {
        
        String coaProdCd;
        
        if (isCmbn) { 
            // get selected combination's COA_PROD_CD
            String cmbnVal = cMsg.A.no(cMsg.xxRadioBtn.getValueInt()).xxComnScrCondValTxt_A.getValue();
            coaProdCd = getSegValue(cmbnVal, 5);
        } else {
            
            // get entered search criteria
            coaProdCd = cMsg.coaProdCd.getValue();
            
            if (!hasValue(coaProdCd)) {
                return;
            }
        }
        
        S21SsmEZDResult ssmResult = NFAL0200Query.getInstance().getCOAProd(cMsg, sMsg, coaProdCd);
        
         // search result exists
        if( ssmResult.isCodeNormal() ) {
            
            // max rows exceed warning
            int queryResCnt = ssmResult.getQueryResultCount();
            if( queryResCnt > cMsg.G.length() ) {
                cMsg.setMessageInfo( "NFAM0157I" , new String[] {String.valueOf(cMsg.G.length()), String.valueOf(cMsg.G.length())});
                queryResCnt = cMsg.G.length();
            }

            // SMsg -> CMsg
            int i = 0;
            
            for( ; i < sMsg.G.getValidCount(); i++ ) {
                if( i == cMsg.G.length() ) {
                    break;
                }
                EZDMsg.copy( sMsg.G.no( i ), null, cMsg.G.no( i ), null );
                
            }
            cMsg.G.setValidCount( i );

            
        // no search result
        } else {
            cMsg.G.clear();
            cMsg.G.setValidCount(0);
            cMsg.setMessageInfo( "ZZPM0037W" );
        }       

    }
    
    public static void getCOAAffl(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg, boolean isCmbn) {
        
        String coaAfflCd;
        
        if (isCmbn) { 
            // get selected combination's COA_AFFL_CD
            String cmbnVal = cMsg.A.no(cMsg.xxRadioBtn.getValueInt()).xxComnScrCondValTxt_A.getValue();
            coaAfflCd = getSegValue(cmbnVal, 7);
        } else {
            
            // get entered search criteria
            coaAfflCd = cMsg.coaAfflCd.getValue();
            
            if (!hasValue(coaAfflCd)) {
                return;
            }
        }
        
        S21SsmEZDResult ssmResult = NFAL0200Query.getInstance().getCOAAffl(cMsg, sMsg, coaAfflCd);
        
         // search result exists
        if( ssmResult.isCodeNormal() ) {
            
            // max rows exceed warning
            int queryResCnt = ssmResult.getQueryResultCount();
            if( queryResCnt > cMsg.H.length() ) {
                cMsg.setMessageInfo( "NFAM0157I" , new String[] {String.valueOf(cMsg.H.length()), String.valueOf(cMsg.H.length())});
                queryResCnt = cMsg.H.length();
            }

            // SMsg -> CMsg
            int i = 0;
            
            for( ; i < sMsg.H.getValidCount(); i++ ) {
                if( i == cMsg.H.length() ) {
                    break;
                }
                EZDMsg.copy( sMsg.H.no( i ), null, cMsg.H.no( i ), null );
                
            }
            cMsg.H.setValidCount( i );

            
        // no search result
        } else {
            cMsg.H.clear();
            cMsg.H.setValidCount(0);
            cMsg.setMessageInfo( "ZZPM0037W" );
        }       

    }
    
    public static void getCOAExtn(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg, boolean isCmbn) {
        
        String coaExtnCd;
        
        if (isCmbn) { 
            // get selected combination's COA_EXTN_CD
            String cmbnVal = cMsg.A.no(cMsg.xxRadioBtn.getValueInt()).xxComnScrCondValTxt_A.getValue();
            coaExtnCd = getSegValue(cmbnVal, 9);
        } else {
            
            // get entered search criteria
            coaExtnCd = cMsg.coaExtnCd.getValue();
            
            if (!hasValue(coaExtnCd)) {
                return;
            }
        }
        
        S21SsmEZDResult ssmResult = NFAL0200Query.getInstance().getCOAExtn(cMsg, sMsg, coaExtnCd);
        
         // search result exists
        if( ssmResult.isCodeNormal() ) {
            
            // max rows exceed warning
            int queryResCnt = ssmResult.getQueryResultCount();
            if( queryResCnt > sMsg.I.length() ) {
                cMsg.setMessageInfo( "NFAM0157I" , new String[] {String.valueOf(sMsg.I.length()), String.valueOf(sMsg.I.length())});
                queryResCnt = sMsg.I.length();
            }

            // SMsg -> CMsg
            int i = 0;
            
            for( ; i < sMsg.I.getValidCount(); i++ ) {
                if( i == cMsg.I.length() ) {
                    break;
                }
                EZDMsg.copy( sMsg.I.no( i ), null, cMsg.I.no( i ), null );
                
            }
            cMsg.I.setValidCount( i );

            
        // no search result
        } else {
            cMsg.I.clear();
            cMsg.I.setValidCount(0);
            cMsg.setMessageInfo( "ZZPM0037W" );
        }       

    }
    
    public static void search(NFAL0200CMsg bizMsg, NFAL0200SMsg glblMsg, boolean isReset) {
        
     // reset all
        NFAL0200CommonLogic.clearAllTab(bizMsg, glblMsg);
        
        if (FLG_ON_Y.equals(bizMsg.xxChkBox.getValue())) {
            getCOACmbn(bizMsg, glblMsg);
            
            if (bizMsg.A.getValidCount() > 0) {
                // retrieve data for lower tab
                getCOABr(bizMsg, glblMsg, true);
            }
            
            if (!isReset) {
                // branch tab to be displayed.
                setValue(bizMsg.xxDplyTab, NFAL0200Constant.BR_TAB);
            }
            
        } else {
            
            CoaSegmentTabs selTab = NFAL0200CommonLogic.getToBeShowedTab(bizMsg);
            
            // set lower tab with search criteria
            switch (selTab) {
                case CH:
                    getCOACh(bizMsg, glblMsg, false);
                    setValue(bizMsg.xxDplyTab, NFAL0200Constant.CH_TAB);
                    break;
                case BR:
                    getCOABr(bizMsg, glblMsg, false);
                    setValue(bizMsg.xxDplyTab, NFAL0200Constant.BR_TAB);
                    break;
                case CC:
                    getCOACc(bizMsg, glblMsg, false);
                    setValue(bizMsg.xxDplyTab, NFAL0200Constant.CC_TAB);
                    break;
                case ACCT:
                    getCOAAcct(bizMsg, glblMsg, false);
                    setValue(bizMsg.xxDplyTab, NFAL0200Constant.ACCT_TAB);
                    break;
                case PROJ:
                    getCOAProj(bizMsg, glblMsg, false);
                    setValue(bizMsg.xxDplyTab, NFAL0200Constant.PROJ_TAB);
                    break;
                case PROD:
                    getCOAProd(bizMsg, glblMsg, false);
                    setValue(bizMsg.xxDplyTab, NFAL0200Constant.PROD_TAB);
                    break;
                case AFFL:
                    getCOAAffl(bizMsg, glblMsg, false);
                    setValue(bizMsg.xxDplyTab, NFAL0200Constant.AFFL_TAB);
                    break;
                case EXTN:
                    getCOAExtn(bizMsg, glblMsg, false);
                    setValue(bizMsg.xxDplyTab, NFAL0200Constant.EXTN_TAB);
                    break;
                default:
            }
        }
    }

    private static String getSegValue(String cmbnVal, int type) {
        
        StringTokenizer st = new StringTokenizer(cmbnVal, ".");
        
        List<String> listCmbn = new ArrayList<String> ();
        
        while (st.hasMoreTokens()) {
            listCmbn.add(st.nextToken());
        }
        
        return listCmbn.get(type-1);
    }
    
    public static CoaSegmentTabs getSelectedTab(NFAL0200CMsg cMsg) {
        
        if (cMsg.xxDplyTab.getValue().equals(NFAL0200Constant.CH_TAB)) {
            return CoaSegmentTabs.CH;
        } else if (cMsg.xxDplyTab.getValue().equals(NFAL0200Constant.BR_TAB)) {
            return CoaSegmentTabs.BR;
        } else if (cMsg.xxDplyTab.getValue().equals(NFAL0200Constant.CC_TAB)) {
            return CoaSegmentTabs.CC;
        } else if (cMsg.xxDplyTab.getValue().equals(NFAL0200Constant.ACCT_TAB)) {
            return CoaSegmentTabs.ACCT;
        } else if (cMsg.xxDplyTab.getValue().equals(NFAL0200Constant.PROJ_TAB)) {
            return CoaSegmentTabs.PROJ;
        } else if (cMsg.xxDplyTab.getValue().equals(NFAL0200Constant.PROD_TAB)) {
            return CoaSegmentTabs.PROD;
        } else if (cMsg.xxDplyTab.getValue().equals(NFAL0200Constant.AFFL_TAB)) {
            return CoaSegmentTabs.AFFL;
        } else if (cMsg.xxDplyTab.getValue().equals(NFAL0200Constant.EXTN_TAB)) {
            return CoaSegmentTabs.EXTN;
        }
        
        return CoaSegmentTabs.CH;
    }
    
    public static String getFileName(NFAL0200CMsg cMsg) {
        
        if (cMsg.xxDplyTab.getValue().equals(NFAL0200Constant.CH_TAB)) {
            return "Channel";
        } else if (cMsg.xxDplyTab.getValue().equals(NFAL0200Constant.BR_TAB)) {
            return "Branch";
        } else if (cMsg.xxDplyTab.getValue().equals(NFAL0200Constant.CC_TAB)) {
            return "Cost_Center";
        } else if (cMsg.xxDplyTab.getValue().equals(NFAL0200Constant.ACCT_TAB)) {
            return "Account";
        } else if (cMsg.xxDplyTab.getValue().equals(NFAL0200Constant.PROJ_TAB)) {
            return "MT";
        } else if (cMsg.xxDplyTab.getValue().equals(NFAL0200Constant.PROD_TAB)) {
            return "Prod";
        } else if (cMsg.xxDplyTab.getValue().equals(NFAL0200Constant.AFFL_TAB)) {
            // START 2016/11/25 J.Kim [QC#16240,MOD]
            // return "Affiliate";
            return "Intercompany";
            // END 2016/11/25 J.Kim [QC#16240,MOD]
        } else if (cMsg.xxDplyTab.getValue().equals(NFAL0200Constant.EXTN_TAB)) {
            return "Business_Unit";
        }
        
        return "";
    }
    
    public static void clearAllTab(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg) {
        
        cMsg.A.clear();
        cMsg.B.clear();
        cMsg.C.clear();
        cMsg.D.clear();
        cMsg.E.clear();
        cMsg.F.clear();
        cMsg.G.clear();
        cMsg.H.clear();
        cMsg.I.clear();
        
        sMsg.A.clear();
        sMsg.B.clear();
        sMsg.C.clear();
        sMsg.D.clear();
        sMsg.E.clear();
        sMsg.F.clear();
        sMsg.G.clear();
        sMsg.H.clear();
        sMsg.I.clear();
        
        cMsg.A.setValidCount(0);
        cMsg.B.setValidCount(0);
        cMsg.C.setValidCount(0);
        cMsg.D.setValidCount(0);
        cMsg.E.setValidCount(0);
        cMsg.F.setValidCount(0);
        cMsg.G.setValidCount(0);
        cMsg.H.setValidCount(0);
        cMsg.I.setValidCount(0);
        
        sMsg.A.setValidCount(0);
        sMsg.B.setValidCount(0);
        sMsg.C.setValidCount(0);
        sMsg.D.setValidCount(0);
        sMsg.E.setValidCount(0);
        sMsg.F.setValidCount(0);
        sMsg.G.setValidCount(0);
        sMsg.H.setValidCount(0);
        sMsg.I.setValidCount(0);
    }
    
    public static void clearLowerTab(NFAL0200CMsg cMsg, NFAL0200SMsg sMsg) {
        cMsg.B.clear();
        cMsg.C.clear();
        cMsg.D.clear();
        cMsg.E.clear();
        cMsg.F.clear();
        cMsg.G.clear();
        cMsg.H.clear();
        cMsg.I.clear();
        
        sMsg.B.clear();
        sMsg.C.clear();
        sMsg.D.clear();
        sMsg.E.clear();
        sMsg.F.clear();
        sMsg.G.clear();
        sMsg.H.clear();
        sMsg.I.clear();

        cMsg.B.setValidCount(0);
        cMsg.C.setValidCount(0);
        cMsg.D.setValidCount(0);
        cMsg.E.setValidCount(0);
        cMsg.F.setValidCount(0);
        cMsg.G.setValidCount(0);
        cMsg.H.setValidCount(0);
        cMsg.I.setValidCount(0);
        
        sMsg.B.setValidCount(0);
        sMsg.C.setValidCount(0);
        sMsg.D.setValidCount(0);
        sMsg.E.setValidCount(0);
        sMsg.F.setValidCount(0);
        sMsg.G.setValidCount(0);
        sMsg.H.setValidCount(0);
        sMsg.I.setValidCount(0);
    }

    public static CoaSegmentTabs getToBeShowedTab(NFAL0200CMsg cMsg) {
        
        if (hasValue(cMsg.coaChCd)) {
            return CoaSegmentTabs.CH;
        } else if (hasValue(cMsg.coaBrCd)) {
            return CoaSegmentTabs.BR;
        } else if (hasValue(cMsg.coaCcCd)) {
            return CoaSegmentTabs.CC;
        } else if (hasValue(cMsg.coaAcctCd)) {
            return CoaSegmentTabs.ACCT;
        } else if (hasValue(cMsg.coaProjCd)) {
            return CoaSegmentTabs.PROJ;
        } else if (hasValue(cMsg.coaProdCd)) {
            return CoaSegmentTabs.PROD;
        } else if (hasValue(cMsg.coaAfflCd)) {
            return CoaSegmentTabs.AFFL;
        } else if (hasValue(cMsg.coaExtnCd)) {
            return CoaSegmentTabs.EXTN;
        }
        
        return CoaSegmentTabs.CH;
    }
    
    public static boolean compStr(String var1, String var2) {
        
        if (var1 == null || var1.equals("")) {
            if (var2 == null || var2.equals("")) {
                return true;
            }
        }
        
        if (var1.equals(var2)) {
            return true;
        }
        
        return false;
        
    }
    
    public static boolean compStrForFlg(String var1, String var2) {
        
        if (var1 == null || var1.equals("") || ZYPConstant.FLG_OFF_N.equals(var1)) {
            if (var2 == null || var2.equals("") || ZYPConstant.FLG_OFF_N.equals(var2)) {
                return true;
            }
        }
        
        if (var1.equals(var2)) {
            return true;
        }
        
        return false;
        
    }
    
    public static String getFlgValue(String val) {
        
        if (val == null || val.equals("")) {
            return ZYPConstant.FLG_OFF_N;
        }
        
        return val;
    }
    
    public static void setCodeValue(NFAL0200CMsg bizMsg) {
        
        for (int i=0; i < bizMsg.C.getValidCount(); i++) {
            setCodeValueOfRg(bizMsg, i);
            setCodeValueOfZn(bizMsg, i);
            setCodeValueOfPrint(bizMsg, i);
            setCodeValueOfTp(bizMsg, i);
            setCodeValueOfContr(bizMsg, i);
            setCodeValueOfPrtr(bizMsg, i);
            setCodeValueOfGeo(bizMsg, i);
        }
    }
    
    private static void setCodeValueOfRg(NFAL0200CMsg bizMsg, int idx) {
        
        String val = bizMsg.C.no(idx).coaBrRgCd_C.getValue();
        
        if (!hasValue(val)) {
            return;
        }
        
        for(int i = 0; i < bizMsg.coaBrRgCd_LC.length(); i++) {
            if (!hasValue(bizMsg.coaBrRgCd_LC.no(i).getValue())) {
                break;
            }
            
            if (val.equals(bizMsg.coaBrRgCd_LC.no(i).getValue())) {
                bizMsg.C.no(idx).coaBrRgDescTxt_C.setValue((bizMsg.coaBrRgDescTxt_LN.no(i).getValue()));
            }
        }
    
    }
    
    private static void setCodeValueOfZn(NFAL0200CMsg bizMsg, int idx) {
        
        String val = bizMsg.C.no(idx).coaBrZnCd_C.getValue();
        
        if (!hasValue(val)) {
            return;
        }
        
        for(int i = 0; i < bizMsg.coaBrZnCd_LC.length(); i++) {
            if (!hasValue(bizMsg.coaBrZnCd_LC.no(i).getValue())) {
                break;
            }
            
            if (val.equals(bizMsg.coaBrZnCd_LC.no(i).getValue())) {
                bizMsg.C.no(idx).coaBrZnDescTxt_C.setValue((bizMsg.coaBrZnDescTxt_LN.no(i).getValue()));
            }
        }
    
    }
    
    private static void setCodeValueOfPrint(NFAL0200CMsg bizMsg, int idx) {
        
        String val = bizMsg.C.no(idx).coaBrPrintCd_C.getValue();
        
        if (!hasValue(val)) {
            return;
        }
        
        for(int i = 0; i < bizMsg.coaBrPrintCd_LC.length(); i++) {
            if (!hasValue(bizMsg.coaBrPrintCd_LC.no(i).getValue())) {
                break;
            }
            
            if (val.equals(bizMsg.coaBrPrintCd_LC.no(i).getValue())) {
                bizMsg.C.no(idx).coaBrPrintDescTxt_C.setValue((bizMsg.coaBrPrintDescTxt_LN.no(i).getValue()));
            }
        }
    
    }
    
    private static void setCodeValueOfTp(NFAL0200CMsg bizMsg, int idx) {
        
        String val = bizMsg.C.no(idx).coaBrTpCd_C.getValue();
        
        if (!hasValue(val)) {
            return;
        }
        
        for(int i = 0; i < bizMsg.coaBrTpCd_LC.length(); i++) {
            if (!hasValue(bizMsg.coaBrTpCd_LC.no(i).getValue())) {
                break;
            }
            
            if (val.equals(bizMsg.coaBrTpCd_LC.no(i).getValue())) {
                bizMsg.C.no(idx).coaBrTpDescTxt_C.setValue((bizMsg.coaBrTpDescTxt_LN.no(i).getValue()));
            }
        }
    
    }
    
    private static void setCodeValueOfContr(NFAL0200CMsg bizMsg, int idx) {

        // START 2016/11/02 J.Kim [QC#13443,ADD]
        // String val = bizMsg.C.no(idx).coaBrContrCd_C.getValue();
        String val = bizMsg.C.no(idx).contrCoaBrCd_C.getValue();
        // END 2016/11/02 J.Kim [QC#13443,ADD]
        
        if (!hasValue(val)) {
            return;
        }
        // START 2016/11/02 J.Kim [QC#13443,ADD]
        //for(int i = 0; i < bizMsg.coaBrContrCd_LC.length(); i++) {
        //    if (!hasValue(bizMsg.coaBrContrCd_LC.no(i).getValue())) {
        //        break;
        //    }
        //    
        //    if (val.equals(bizMsg.coaBrContrCd_LC.no(i).getValue())) {
        //        bizMsg.C.no(idx).coaBrContrDescTxt_C.setValue((bizMsg.coaBrContrDescTxt_LN.no(i).getValue()));
        //    }
        //}
        // END 2016/11/02 J.Kim [QC#13443,ADD]
    
    }
    
    private static void setCodeValueOfPrtr(NFAL0200CMsg bizMsg, int idx) {
        
        String val = bizMsg.C.no(idx).coaBrPrtrCd_C.getValue();
        
        if (!hasValue(val)) {
            return;
        }
        
        for(int i = 0; i < bizMsg.coaBrPrtrCd_LC.length(); i++) {
            if (!hasValue(bizMsg.coaBrPrtrCd_LC.no(i).getValue())) {
                break;
            }
            
            if (val.equals(bizMsg.coaBrPrtrCd_LC.no(i).getValue())) {
                bizMsg.C.no(idx).coaBrPrtrDescTxt_C.setValue((bizMsg.coaBrPrtrDescTxt_LN.no(i).getValue()));
            }
        }
    
    }
    
    private static void setCodeValueOfGeo(NFAL0200CMsg bizMsg, int idx) {

        // START 2016/11/02 J.Kim [QC#13443,ADD]
        // String val = bizMsg.C.no(idx).coaBrGeoCd_C.getValue();
        String val = bizMsg.C.no(idx).coaBrGeoTxt_C.getValue();
        // END 2016/11/02 J.Kim [QC#13443,ADD] 
        
        if (!hasValue(val)) {
            return;
        }
        // START 2016/11/02 J.Kim [QC#13443,ADD]
        //for(int i = 0; i < bizMsg.coaBrGeoCd_LC.length(); i++) {
        //    if (!hasValue(bizMsg.coaBrGeoCd_LC.no(i).getValue())) {
        //        break;
        //    }
        //    
        //    if (val.equals(bizMsg.coaBrGeoCd_LC.no(i).getValue())) {
        //        bizMsg.C.no(idx).coaBrGeoDescTxt_C.setValue((bizMsg.coaBrGeoDescTxt_LN.no(i).getValue()));
        //    }
        //}
        // END 2016/11/02 J.Kim [QC#13443,ADD]
    
    }

    /**
     * chngDateFormat
     * @param date String
     * @return String
     */
    public static String chngDateFormat(String date) {
        if (hasValue(date)) {
            date = ZYPDateUtil.formatEzd8ToDisp(date);
        }
        return date;
    }

    // START 2016/10/21 J.Kim [QC#13514,ADD]
    /**
     * Call NSZC0330 for Search Option
     * @param bizMsg NFAL0200CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    public static boolean callSrchOptApi(NFAL0200CMsg bizMsg, NSZC033001PMsg pMsg) {

        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int i = 0; i < pMsg.xxMsgIdList.length(); i++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(i).xxMsgId)) {
                    String msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        bizMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Create Save Option PullDown list
     * @param bizMsg NFBL2060CMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void createPulldownListSaveOpt(NFAL0200CMsg bizMsg, String userId, String glblCmpyCd) {

        bizMsg.srchOptPk_H.clear();
        bizMsg.srchOptNm_H.clear();

        SAVE_SRCH_OPTTMsg saveSrchOptTMsg = new SAVE_SRCH_OPTTMsg();
        saveSrchOptTMsg.setSQLID("001");
        saveSrchOptTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        saveSrchOptTMsg.setConditionValue("srchOptAplId01", NFAL0200Constant.BIZ_ID);
        saveSrchOptTMsg.setConditionValue("srchOptUsrId01", userId);

        SAVE_SRCH_OPTTMsgArray saveSrchOptTMsgArray = (SAVE_SRCH_OPTTMsgArray) EZDTBLAccessor.findByCondition(saveSrchOptTMsg);
        for (int i = 0; i < saveSrchOptTMsgArray.length(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_H.no(i), saveSrchOptTMsgArray.no(i).srchOptPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptNm_H.no(i), saveSrchOptTMsgArray.no(i).srchOptNm);
        }
    }

    /**
     * isExistSaveSearchName
     * @param bizMsg NFAL0200CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NFAL0200CMsg bizMsg) {

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_H.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param bizMsg NFAL0200CMsg
     * @param pMsg NSZC033001PMsg
     */
    public static void setSelectSaveSearchName(NFAL0200CMsg bizMsg, NSZC033001PMsg pMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_H.no(i));
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_H.no(i));
            }
        }
        return;
    }

    /**
     * isSameSaveSearchName
     * @param bizMsg NFAL0200CMsg
     * @return boolean
     */
    public static boolean isSameSaveSearchName(NFAL0200CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return false;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_H.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    // END 2016/10/21 J.Kim [QC#13514,ADD]
}
