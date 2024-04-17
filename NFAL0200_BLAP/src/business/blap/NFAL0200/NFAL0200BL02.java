package business.blap.NFAL0200;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFAL0200.NFAL0200CMsg;
import business.blap.NFAL0200.common.NFAL0200CommonLogic;

import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import business.blap.NFAL0200.constant.NFAL0200Constant;
import business.blap.NFAL0200.constant.NFAL0200Constant.CoaSegmentTabs;
import business.file.NFAL0200F00FMsg;
import business.file.NFAL0200F10FMsg;
import business.file.NFAL0200F20FMsg;
import business.file.NFAL0200F30FMsg;
import business.parts.NSZC033001PMsg;

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
public class NFAL0200BL02 extends S21BusinessHandler implements ZYPConstant{

    /**
     * Method name: doProcess
     * <dd>The method explanation: Call each process by screen id.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // NFAL0200Scrn00_PageJump
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NFAL0200_INIT".equals(screenAplID)) {
                doProcess_NFAL0200_INIT(cMsg, sMsg);
            } else if ("NFAL0200Scrn00_SearchBtn".equals(screenAplID)) {
                doProcess_NFAL0200_SearchBtn(cMsg, sMsg);                
            } else if ("NFAL0200Scrn00_OnChange_RadioBtn".equals(screenAplID)) {
                doProcess_NFAL0200_OnChange_RadioBtn(cMsg, sMsg);
            } else if ("NFAL0200Scrn00_TAB_Ch".equals(screenAplID)) {
                doProcess_NFAL0200_TAB_Ch(cMsg, sMsg);
            } else if ("NFAL0200Scrn00_TAB_Br".equals(screenAplID)) {
                doProcess_NFAL0200_TAB_Br(cMsg, sMsg);
            } else if ("NFAL0200Scrn00_TAB_Cc".equals(screenAplID)) {
                doProcess_NFAL0200_TAB_Cc(cMsg, sMsg);
            } else if ("NFAL0200Scrn00_TAB_Acct".equals(screenAplID)) {
                doProcess_NFAL0200_TAB_Acct(cMsg, sMsg);
            } else if ("NFAL0200Scrn00_TAB_Proj".equals(screenAplID)) {
                doProcess_NFAL0200_TAB_Proj(cMsg, sMsg);
            } else if ("NFAL0200Scrn00_TAB_Prod".equals(screenAplID)) {
                doProcess_NFAL0200_TAB_Prod(cMsg, sMsg);
            } else if ("NFAL0200Scrn00_TAB_Affl".equals(screenAplID)) {
                doProcess_NFAL0200_TAB_Affl(cMsg, sMsg);
            } else if ("NFAL0200Scrn00_TAB_Extn".equals(screenAplID)) {
                doProcess_NFAL0200_TAB_Extn(cMsg, sMsg);
            } else if ("NFAL0200Scrn00_DownloadCombBtn".equals(screenAplID)) {
                doProcess_NFAL0200Scrn00_DownloadCombBtn(cMsg, sMsg);
            } else if ("NFAL0200Scrn00_DownloadCoaBtn".equals(screenAplID)) {
                doProcess_NFAL0200Scrn00_DownloadCoaBtn(cMsg, sMsg);
            // START 2016/10/21 J.Kim [QC#13514,ADD]
            } else if ("NFAL0200Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NFAL0200Scrn00_OnChangeSavedSearchOption(cMsg, sMsg);
            // END 2016/10/21 J.Kim [QC#13514,ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
    
    /**
     * Method name: doProcess_NFAL0200_INIT
     * <dd>The method explanation: Initializing.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0200_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0200_INIT================================START", this);

        NFAL0200CMsg bizMsg = (NFAL0200CMsg) cMsg;
        NFAL0200SMsg glblMsg = (NFAL0200SMsg) sMsg;
        
        setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        
        try {
            // set dropdown list values
            ZYPCodeDataUtil.createPulldownList("COA_BR_RG", bizMsg.coaBrRgCd_LC, bizMsg.coaBrRgDescTxt_LN);
            ZYPCodeDataUtil.createPulldownList("COA_BR_ZN", bizMsg.coaBrZnCd_LC, bizMsg.coaBrZnDescTxt_LN);
            ZYPCodeDataUtil.createPulldownList("COA_BR_PRINT", bizMsg.coaBrPrintCd_LC, bizMsg.coaBrPrintDescTxt_LN);
            ZYPCodeDataUtil.createPulldownList("COA_BR_TP", bizMsg.coaBrTpCd_LC, bizMsg.coaBrTpDescTxt_LN);
            // START 2016/11/02 J.Kim [QC#13443,DEL]
            // ZYPCodeDataUtil.createPulldownList("COA_BR_CONTR", bizMsg.coaBrContrCd_LC, bizMsg.coaBrContrDescTxt_LN);
            // END 2016/11/02 J.Kim [QC#13443,DEl] 
            ZYPCodeDataUtil.createPulldownList("COA_BR_PRTR", bizMsg.coaBrPrtrCd_LC, bizMsg.coaBrPrtrDescTxt_LN);
            // START 2016/11/02 J.Kim [QC#13443,DEL]
            // ZYPCodeDataUtil.createPulldownList("COA_BR_GEO", bizMsg.coaBrGeoCd_LC, bizMsg.coaBrGeoDescTxt_LN);
            // END 2016/11/02 J.Kim [QC#13443,DEL]
        } catch (S21AbendException ex) {
            // any of code table doesn't have data
        }
        
        // default checked
        setValue(bizMsg.xxChkBox, FLG_ON_Y);
        NFAL0200CommonLogic.createPulldownListSaveOpt(bizMsg, getContextUserInfo().getUserId(), bizMsg.glblCmpyCd.getValue());
        
        EZDDebugOutput.println(5, "doProcess_NFAL0200_INIT================================END", this);
    }
    
    private void doProcess_NFAL0200_SearchBtn(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0200_SearchBtn================================START", this);

        NFAL0200CMsg bizMsg = (NFAL0200CMsg) cMsg;
        NFAL0200SMsg glblMsg = (NFAL0200SMsg) sMsg;
        
        NFAL0200CommonLogic.search(bizMsg, glblMsg, false);
                
        EZDDebugOutput.println(5, "doProcess_NFAL0200_SearchBtn================================END", this);
    }
    
    private void doProcess_NFAL0200_OnChange_RadioBtn(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0200_OnChange_RadioBtn================================START", this);

        NFAL0200CMsg bizMsg = (NFAL0200CMsg) cMsg;
        NFAL0200SMsg glblMsg = (NFAL0200SMsg) sMsg;
        
        NFAL0200CommonLogic.clearLowerTab(bizMsg, glblMsg);
        
        CoaSegmentTabs selTab = NFAL0200CommonLogic.getSelectedTab(bizMsg);
        
     // set lower tab with selected combination
        switch (selTab) {
            case CH:
                NFAL0200CommonLogic.getCOACh(bizMsg, glblMsg, true);
                break;
            case BR:
                NFAL0200CommonLogic.getCOABr(bizMsg, glblMsg, true);
                break;
            case CC:
                NFAL0200CommonLogic.getCOACc(bizMsg, glblMsg, true);
                break;
            case ACCT:
                NFAL0200CommonLogic.getCOAAcct(bizMsg, glblMsg, true);
                break;
            case PROJ:
                NFAL0200CommonLogic.getCOAProj(bizMsg, glblMsg, true);
                break;
            case PROD:
                NFAL0200CommonLogic.getCOAProd(bizMsg, glblMsg, true);
                break;
            case AFFL:
                NFAL0200CommonLogic.getCOAAffl(bizMsg, glblMsg, true);
                break;
            case EXTN:
                NFAL0200CommonLogic.getCOAExtn(bizMsg, glblMsg, true);
                break;
            default:
        }
        
        
        EZDDebugOutput.println(5, "doProcess_NFAL0200_OnChange_RadioBtn================================END", this);
    }
    
    private void doProcess_NFAL0200_TAB_Ch(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0200_TAB_Ch================================START", this);
        
        NFAL0200CMsg bizMsg = (NFAL0200CMsg) cMsg;
        NFAL0200SMsg glblMsg = (NFAL0200SMsg) sMsg;
        
        if (!hasValue(bizMsg.B.no(0).coaChCd_B)) {
            
            if (FLG_ON_Y.equals(bizMsg.xxChkBox.getValue())) {
                // if there is record in combination
                if (bizMsg.A.getValidCount() > 0) {
                    NFAL0200CommonLogic.getCOACh(bizMsg, glblMsg, true);
                }
            } else {
                
                    // search
                    NFAL0200CommonLogic.getCOACh(bizMsg, glblMsg, false);
            }
        }
        setValue(bizMsg.xxDplyTab, NFAL0200Constant.CH_TAB);
        
        EZDDebugOutput.println(5, "doProcess_NFAL0200_TAB_Ch================================END", this);
    }
    
    private void doProcess_NFAL0200_TAB_Br(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0200_TAB_Br================================START", this);
        
        NFAL0200CMsg bizMsg = (NFAL0200CMsg) cMsg;
        NFAL0200SMsg glblMsg = (NFAL0200SMsg) sMsg;
        
        if (!hasValue(bizMsg.C.no(0).coaBrCd_C)) {
        
            if (FLG_ON_Y.equals(bizMsg.xxChkBox.getValue())) {
                // if there is record in combination
                if (bizMsg.A.getValidCount() > 0) {
                    NFAL0200CommonLogic.getCOABr(bizMsg, glblMsg, true);
                }
            } else {
                // search
                NFAL0200CommonLogic.getCOABr(bizMsg, glblMsg, false);
            }
        }
        setValue(bizMsg.xxDplyTab, NFAL0200Constant.BR_TAB);
        
        EZDDebugOutput.println(5, "doProcess_NFAL0200_TAB_Br================================END", this);
    }
    
    private void doProcess_NFAL0200_TAB_Cc(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0200_TAB_Cc================================START", this);
        
        NFAL0200CMsg bizMsg = (NFAL0200CMsg) cMsg;
        NFAL0200SMsg glblMsg = (NFAL0200SMsg) sMsg;
        
        if (!hasValue(bizMsg.D.no(0).coaCcCd_D)) {
        
            if (FLG_ON_Y.equals(bizMsg.xxChkBox.getValue())) {
                // if there is record in combination
                if (bizMsg.A.getValidCount() > 0) {
                    NFAL0200CommonLogic.getCOACc(bizMsg, glblMsg, true);
                }
            } else {
                // search
                NFAL0200CommonLogic.getCOACc(bizMsg, glblMsg, false);
            }
        }
        setValue(bizMsg.xxDplyTab, NFAL0200Constant.CC_TAB);
        
        EZDDebugOutput.println(5, "doProcess_NFAL0200_TAB_Cc================================END", this);
    }
    
    private void doProcess_NFAL0200_TAB_Acct(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0200_TAB_Acct================================START", this);
        
        NFAL0200CMsg bizMsg = (NFAL0200CMsg) cMsg;
        NFAL0200SMsg glblMsg = (NFAL0200SMsg) sMsg;
        
        if (!hasValue(bizMsg.E.no(0).coaAcctCd_E)) {
        
            if (FLG_ON_Y.equals(bizMsg.xxChkBox.getValue())) {
                // if there is record in combination
                if (bizMsg.A.getValidCount() > 0) {
                    NFAL0200CommonLogic.getCOAAcct(bizMsg, glblMsg, true);
                }
            } else {
                    // search
                    NFAL0200CommonLogic.getCOAAcct(bizMsg, glblMsg, false);
            }
        }
        setValue(bizMsg.xxDplyTab, NFAL0200Constant.ACCT_TAB);
        
        EZDDebugOutput.println(5, "doProcess_NFAL0200_TAB_Acct================================END", this);
    }
    
    private void doProcess_NFAL0200_TAB_Proj(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0200_TAB_Proj================================START", this);
        
        NFAL0200CMsg bizMsg = (NFAL0200CMsg) cMsg;
        NFAL0200SMsg glblMsg = (NFAL0200SMsg) sMsg;
        
        if (!hasValue(bizMsg.F.no(0).coaProjCd_F)) {
        
            if (FLG_ON_Y.equals(bizMsg.xxChkBox.getValue())) {
                // if there is record in combination
                if (bizMsg.A.getValidCount() > 0) {
                    NFAL0200CommonLogic.getCOAProj(bizMsg, glblMsg, true);
                }
            } else {
                    // search
                    NFAL0200CommonLogic.getCOAProj(bizMsg, glblMsg, false);
            }
        }
        
        setValue(bizMsg.xxDplyTab, NFAL0200Constant.PROJ_TAB);
        
        EZDDebugOutput.println(5, "doProcess_NFAL0200_TAB_Proj================================END", this);
    }
    
    private void doProcess_NFAL0200_TAB_Prod(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0200_TAB_Prod================================START", this);
        
        NFAL0200CMsg bizMsg = (NFAL0200CMsg) cMsg;
        NFAL0200SMsg glblMsg = (NFAL0200SMsg) sMsg;
        
        if (!hasValue(bizMsg.G.no(0).coaProdCd_G)) {
        
            if (FLG_ON_Y.equals(bizMsg.xxChkBox.getValue())) {
                // if there is record in combination
                if (bizMsg.A.getValidCount() > 0) {
                    NFAL0200CommonLogic.getCOAProd(bizMsg, glblMsg, true);
                }
            } else {
                    // search
                    NFAL0200CommonLogic.getCOAProd(bizMsg, glblMsg, false);
            }
        }
        
        setValue(bizMsg.xxDplyTab, NFAL0200Constant.PROD_TAB);
        
        EZDDebugOutput.println(5, "doProcess_NFAL0200_TAB_Prod================================END", this);
    }
    
    private void doProcess_NFAL0200_TAB_Affl(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0200_TAB_Affl================================START", this);
        
        NFAL0200CMsg bizMsg = (NFAL0200CMsg) cMsg;
        NFAL0200SMsg glblMsg = (NFAL0200SMsg) sMsg;
        
        if (!hasValue(bizMsg.H.no(0).coaAfflCd_H)) {
        
            if (FLG_ON_Y.equals(bizMsg.xxChkBox.getValue())) {
                // if there is record in combination
                if (bizMsg.A.getValidCount() > 0) {
                    NFAL0200CommonLogic.getCOAAffl(bizMsg, glblMsg, true);
                }
            } else {
                    // search
                    NFAL0200CommonLogic.getCOAAffl(bizMsg, glblMsg, false);
            }
        }
        
        setValue(bizMsg.xxDplyTab, NFAL0200Constant.AFFL_TAB);
        
        EZDDebugOutput.println(5, "doProcess_NFAL0200_TAB_Affl================================END", this);
    }
    
    private void doProcess_NFAL0200_TAB_Extn(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0200_TAB_Extn================================START", this);
        
        NFAL0200CMsg bizMsg = (NFAL0200CMsg) cMsg;
        NFAL0200SMsg glblMsg = (NFAL0200SMsg) sMsg;
        
        if (!hasValue(bizMsg.I.no(0).coaExtnCd_I)) {
        
            if (FLG_ON_Y.equals(bizMsg.xxChkBox.getValue())) {
                // if there is record in combination
                if (bizMsg.A.getValidCount() > 0) {
                    NFAL0200CommonLogic.getCOAExtn(bizMsg, glblMsg, true);
                }
            } else {
                // search
                NFAL0200CommonLogic.getCOAExtn(bizMsg, glblMsg, false);
            }
        }
        
        setValue(bizMsg.xxDplyTab, NFAL0200Constant.EXTN_TAB);
        
        EZDDebugOutput.println(5, "doProcess_NFAL0200_TAB_Extn================================END", this);
    }
    
    private void doProcess_NFAL0200Scrn00_DownloadCombBtn(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0200Scrn00_DownloadCombBtn================================START", this);

        NFAL0200CMsg bizMsg = (NFAL0200CMsg) cMsg;
        NFAL0200SMsg glblMsg = (NFAL0200SMsg) sMsg;
        
        bizMsg.xxFileData.setTempFilePath( 
                null, 
                ZYPCSVOutFile.createCSVOutFileNm( "COA_Combination" ), 
                ".csv" 
            );

        NFAL0200F00FMsg fMsg       = new NFAL0200F00FMsg();
        ZYPCSVOutFile   csvOutFile = new ZYPCSVOutFile( bizMsg.xxFileData.getTempFilePath(), fMsg );

        csvOutFile.writeHeader( 
                new String[]{ 
                        "COA Code Combination", 
                        "Account Description", 
                        "Type", 
                        "From Date",
                        "To Date",
                        "Enabled", 
                        "Posting",
                        "Budgeting",
                    } 
                );

        for( int i = 0 ; i < bizMsg.A.getValidCount(); i++ ) {
            EZDMsg.copy( bizMsg.A.no( i ), null, fMsg, null );
            
            // format date
            setValue(fMsg.xxDtTxt_S, NFAL0200CommonLogic.chngDateFormat(bizMsg.A.no(i).coaStartActvDt_A.getValue()));
            setValue(fMsg.xxDtTxt_E, NFAL0200CommonLogic.chngDateFormat(bizMsg.A.no(i).coaEndActvDt_A.getValue()));
            
            csvOutFile.write();
        }

        csvOutFile.close();
                
        EZDDebugOutput.println(5, "doProcess_NFAL0200Scrn00_DownloadCombBtn================================END", this);
    }
    
    private void doProcess_NFAL0200Scrn00_DownloadCoaBtn(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0200Scrn00_DownloadCoaBtn================================START", this);

        NFAL0200CMsg bizMsg = (NFAL0200CMsg) cMsg;
        NFAL0200SMsg glblMsg = (NFAL0200SMsg) sMsg;
        
        String fileNm = NFAL0200CommonLogic.getFileName(bizMsg);
        
        bizMsg.xxFileData.setTempFilePath( 
                null, 
                ZYPCSVOutFile.createCSVOutFileNm( "COA_".concat(fileNm) ), 
                ".csv" 
            );

        if (NFAL0200CommonLogic.getSelectedTab(bizMsg) == CoaSegmentTabs.BR) {
            
            // set name of the selected code in drop down lists.
            NFAL0200CommonLogic.setCodeValue(bizMsg);
            
            NFAL0200F20FMsg fMsg       = new NFAL0200F20FMsg();
            ZYPCSVOutFile   csvOutFile = new ZYPCSVOutFile( bizMsg.xxFileData.getTempFilePath(), fMsg );

            csvOutFile.writeHeader( 
                    new String[]{ 
                            "COA Branch Code", 
                            "Description", 
                            "From Date",
                            "To Date",
                            "Parent",
                            "Enabled",
                            "Posting",
                            "Budgeting",
                            "Region", 
                            "Zone", 
                            "PrintBranch",
                            "Branch Type",
                            // START 2016/11/02 J.Kim [QC#13443,ADD]
                            // "Contact Code",
                            // END 2016/11/02 J.Kim [QC#13443,ADD] 
                            "Contract Branch",
                            "Printer Code",
                            "GEO Code",
                        } 
                    );
    
            for( int i = 0 ; i < bizMsg.C.getValidCount(); i++ ) {
                EZDMsg.copy( bizMsg.C.no( i ), null, fMsg, null );
                
                // format date
                setValue(fMsg.xxDtTxt_S, NFAL0200CommonLogic.chngDateFormat(bizMsg.C.no(i).coaStartActvDt_C.getValue()));
                setValue(fMsg.xxDtTxt_E, NFAL0200CommonLogic.chngDateFormat(bizMsg.C.no(i).coaEndActvDt_C.getValue()));
                
                csvOutFile.write();
            }
            
            csvOutFile.close();
        } else if (NFAL0200CommonLogic.getSelectedTab(bizMsg) == CoaSegmentTabs.ACCT) {
            NFAL0200F30FMsg fMsg       = new NFAL0200F30FMsg();
            ZYPCSVOutFile   csvOutFile = new ZYPCSVOutFile( bizMsg.xxFileData.getTempFilePath(), fMsg );
    
            csvOutFile.writeHeader( 
                    new String[]{ 
                            "COA Branch Code", 
                            "Description", 
                            "From Date",
                            "To Date",
                            "Parent",
                            "Enabled",
                            "Posting",
                            "Budgeting",
                            "Accrual",
                        } 
                    );
            
            for( int i = 0 ; i < bizMsg.E.getValidCount(); i++ ) {
                EZDMsg.copy( bizMsg.E.no( i ), null, fMsg, null );
                
             // format date
                setValue(fMsg.xxDtTxt_S, NFAL0200CommonLogic.chngDateFormat(bizMsg.E.no(i).coaStartActvDt_E.getValue()));
                setValue(fMsg.xxDtTxt_E, NFAL0200CommonLogic.chngDateFormat(bizMsg.E.no(i).coaEndActvDt_E.getValue()));
                
                csvOutFile.write();
            }
            
            csvOutFile.close();
            
        } else {
            NFAL0200F10FMsg fMsg       = new NFAL0200F10FMsg();
            ZYPCSVOutFile   csvOutFile = new ZYPCSVOutFile( bizMsg.xxFileData.getTempFilePath(), fMsg );
            
            CoaSegmentTabs selTab = NFAL0200CommonLogic.getSelectedTab(bizMsg);
            
            switch (selTab) {
                case CH:
                    csvOutFile.writeHeader( 
                            new String[]{ 
                                    "COA Channel Code", 
                                    "Description",
                                    "From Date",
                                    "To Date",
                                    "Parent",
                                    "Enabled",
                                    "Posting",
                                    "Budgeting",
                                } 
                            );
            
                    for( int i = 0 ; i < bizMsg.B.getValidCount(); i++ ) {
                        setValue(fMsg.coaAcctCd, bizMsg.B.no(i).coaChCd_B.getValue());
                        setValue(fMsg.coaCcDescTxt, bizMsg.B.no(i).coaChDescTxt_B.getValue());
                        setValue(fMsg.xxChkBox, bizMsg.B.no(i).xxChkBox_B.getValue());
                        setValue(fMsg.coaEnblFlg, bizMsg.B.no(i).coaEnblFlg_B.getValue());
                        setValue(fMsg.coaPstgAllwFlg, bizMsg.B.no(i).coaPstgAllwFlg_B.getValue());
                        setValue(fMsg.coaBdgAllwFlg, bizMsg.B.no(i).coaBdgAllwFlg_B.getValue());
                        // START 2016/08/25 J.Kim [QC#13515,ADD]
                        fMsg.addExclusionItem("coaLineBizTpCd");
                        // END 2016/08/25 J.Kim [QC#13515,ADD]
                        // format date
                        setValue(fMsg.xxDtTxt_S, NFAL0200CommonLogic.chngDateFormat(bizMsg.B.no(i).coaStartActvDt_B.getValue()));
                        setValue(fMsg.xxDtTxt_E, NFAL0200CommonLogic.chngDateFormat(bizMsg.B.no(i).coaEndActvDt_B.getValue()));
                        
                        csvOutFile.write();
                    }
                    break;
                    
                case CC:

                    csvOutFile.writeHeader( 
                            new String[]{ 
                                    "COA Cost Center Code", 
                                    "Description", 
                                    "From Date",
                                    "To Date",
                                    "Parent",
                                    "Enabled",
                                    "Posting",
                                    "Budgeting",
                                    // START 2016/08/25 J.Kim [QC#13515,ADD]
                                    "Business Entity"
                                    // END 2016/08/25 J.Kim [QC#13515,ADD]
                                }
                            );
            
                    for( int i = 0 ; i < bizMsg.D.getValidCount(); i++ ) {
                        setValue(fMsg.coaAcctCd, bizMsg.D.no(i).coaCcCd_D.getValue());
                        setValue(fMsg.coaCcDescTxt, bizMsg.D.no(i).coaCcDescTxt_D.getValue());
                        setValue(fMsg.xxChkBox, bizMsg.D.no(i).xxChkBox_D.getValue());
                        setValue(fMsg.coaEnblFlg, bizMsg.D.no(i).coaEnblFlg_D.getValue());
                        setValue(fMsg.coaPstgAllwFlg, bizMsg.D.no(i).coaPstgAllwFlg_D.getValue());
                        setValue(fMsg.coaBdgAllwFlg, bizMsg.D.no(i).coaBdgAllwFlg_D.getValue());
                        // START 2016/08/25 J.Kim [QC#13515,ADD]
                        setValue(fMsg.coaLineBizTpCd, bizMsg.D.no(i).coaLineBizTpCd_D.getValue());
                        // END 2016/08/25 J.Kim [QC#13515,ADD]
                        // format date
                        setValue(fMsg.xxDtTxt_S, NFAL0200CommonLogic.chngDateFormat(bizMsg.D.no(i).coaStartActvDt_D.getValue()));
                        setValue(fMsg.xxDtTxt_E, NFAL0200CommonLogic.chngDateFormat(bizMsg.D.no(i).coaEndActvDt_D.getValue()));
                        
                        csvOutFile.write();
                    }
                    
                    break;
                            
                case PROJ:
                    
                    csvOutFile.writeHeader( 
                            new String[]{ 
                                    "COA Merchandise Type Code", 
                                    "Description", 
                                    "From Date",
                                    "To Date",
                                    "Parent",
                                    "Enabled",
                                    "Posting",
                                    "Budgeting",
                                } 
                            );
            
                    for( int i = 0 ; i < bizMsg.F.getValidCount(); i++ ) {
                        setValue(fMsg.coaAcctCd, bizMsg.F.no(i).coaProjCd_F.getValue());
                        setValue(fMsg.coaCcDescTxt, bizMsg.F.no(i).coaProjDescTxt_F.getValue());
                        setValue(fMsg.xxChkBox, bizMsg.F.no(i).xxChkBox_F.getValue());
                        setValue(fMsg.coaEnblFlg, bizMsg.F.no(i).coaEnblFlg_F.getValue());
                        setValue(fMsg.coaPstgAllwFlg, bizMsg.F.no(i).coaPstgAllwFlg_F.getValue());
                        setValue(fMsg.coaBdgAllwFlg, bizMsg.F.no(i).coaBdgAllwFlg_F.getValue());
                        // START 2016/08/25 J.Kim [QC#13515,ADD]
                        fMsg.addExclusionItem("coaLineBizTpCd");
                        // END 2016/08/25 J.Kim [QC#13515,ADD]
                        // format date
                        setValue(fMsg.xxDtTxt_S, NFAL0200CommonLogic.chngDateFormat(bizMsg.F.no(i).coaStartActvDt_F.getValue()));
                        setValue(fMsg.xxDtTxt_E, NFAL0200CommonLogic.chngDateFormat(bizMsg.F.no(i).coaEndActvDt_F.getValue()));
                        
                        csvOutFile.write();
                    }
                    
                    break;
                    
                case PROD:
                    
                    csvOutFile.writeHeader( 
                            new String[]{ 
                                    "COA Product Code", 
                                    "Description",
                                    "From Date",
                                    "To Date",
                                    "Parent",
                                    "Enabled",
                                    "Posting",
                                    "Budgeting",
                                } 
                            );
            
                    for( int i = 0 ; i < bizMsg.G.getValidCount(); i++ ) {
                        setValue(fMsg.coaAcctCd, bizMsg.G.no(i).coaProdCd_G.getValue());
                        setValue(fMsg.coaCcDescTxt, bizMsg.G.no(i).coaProdDescTxt_G.getValue());
                        setValue(fMsg.xxChkBox, bizMsg.G.no(i).xxChkBox_G.getValue());
                        setValue(fMsg.coaEnblFlg, bizMsg.G.no(i).coaEnblFlg_G.getValue());
                        setValue(fMsg.coaPstgAllwFlg, bizMsg.G.no(i).coaPstgAllwFlg_G.getValue());
                        setValue(fMsg.coaBdgAllwFlg, bizMsg.G.no(i).coaBdgAllwFlg_G.getValue());
                        // START 2016/08/25 J.Kim [QC#13515,ADD]
                        fMsg.addExclusionItem("coaLineBizTpCd");
                        // END 2016/08/25 J.Kim [QC#13515,ADD]
                        // format date
                        setValue(fMsg.xxDtTxt_S, NFAL0200CommonLogic.chngDateFormat(bizMsg.G.no(i).coaStartActvDt_G.getValue()));
                        setValue(fMsg.xxDtTxt_E, NFAL0200CommonLogic.chngDateFormat(bizMsg.G.no(i).coaEndActvDt_G.getValue()));
                        
                        csvOutFile.write();
                    }
                    
                    break;
                case AFFL:
                    
                    csvOutFile.writeHeader( 
                            new String[]{
                                    // START 2016/11/25 J.Kim [QC#16240,MOD]
                                    // "COA Affiliate Code",
                                    "COA Intercompany Code",
                                    // END 2016/11/25 J.Kim [QC#16240,MOD]
                                    "Description", 
                                    "From Date",
                                    "To Date",
                                    "Parent",
                                    "Enabled",
                                    "Posting",
                                    "Budgeting",
                                } 
                            );
            
                    for( int i = 0 ; i < bizMsg.H.getValidCount(); i++ ) {
                        setValue(fMsg.coaAcctCd, bizMsg.H.no(i).coaAfflCd_H.getValue());
                        setValue(fMsg.coaCcDescTxt, bizMsg.H.no(i).coaAfflDescTxt_H.getValue());
                        setValue(fMsg.xxChkBox, bizMsg.H.no(i).xxChkBox_H.getValue());
                        setValue(fMsg.coaEnblFlg, bizMsg.H.no(i).coaEnblFlg_H.getValue());
                        setValue(fMsg.coaPstgAllwFlg, bizMsg.H.no(i).coaPstgAllwFlg_H.getValue());
                        setValue(fMsg.coaBdgAllwFlg, bizMsg.H.no(i).coaBdgAllwFlg_H.getValue());
                        // START 2016/08/25 J.Kim [QC#13515,ADD]
                        fMsg.addExclusionItem("coaLineBizTpCd");
                        // END 2016/08/25 J.Kim [QC#13515,ADD]
                        // format date
                        setValue(fMsg.xxDtTxt_S, NFAL0200CommonLogic.chngDateFormat(bizMsg.H.no(i).coaStartActvDt_H.getValue()));
                        setValue(fMsg.xxDtTxt_E, NFAL0200CommonLogic.chngDateFormat(bizMsg.H.no(i).coaEndActvDt_H.getValue()));
                        
                        csvOutFile.write();
                    }
                    break;
                    
                case EXTN:
                    
                    csvOutFile.writeHeader( 
                            new String[]{ 
                                    "COA Business Unit Code", 
                                    "Description", 
                                    "From Date",
                                    "To Date",
                                    "Parent",
                                    "Enabled",
                                    "Posting",
                                    "Budgeting",
                                } 
                            );
            
                    for( int i = 0 ; i < bizMsg.I.getValidCount(); i++ ) {
                        setValue(fMsg.coaAcctCd, bizMsg.I.no(i).coaExtnCd_I.getValue());
                        setValue(fMsg.coaCcDescTxt, bizMsg.I.no(i).coaExtnDescTxt_I.getValue());
                        setValue(fMsg.xxChkBox, bizMsg.I.no(i).xxChkBox_I.getValue());
                        setValue(fMsg.coaEnblFlg, bizMsg.I.no(i).coaEnblFlg_I.getValue());
                        setValue(fMsg.coaPstgAllwFlg, bizMsg.I.no(i).coaPstgAllwFlg_I.getValue());
                        setValue(fMsg.coaBdgAllwFlg, bizMsg.I.no(i).coaBdgAllwFlg_I.getValue());
                        // START 2016/08/25 J.Kim [QC#13515,ADD]
                        fMsg.addExclusionItem("coaLineBizTpCd");
                        // END 2016/08/25 J.Kim [QC#13515,ADD]
                        // format date
                        setValue(fMsg.xxDtTxt_S, NFAL0200CommonLogic.chngDateFormat(bizMsg.I.no(i).coaStartActvDt_I.getValue()));
                        setValue(fMsg.xxDtTxt_E, NFAL0200CommonLogic.chngDateFormat(bizMsg.I.no(i).coaEndActvDt_I.getValue()));
                        
                        csvOutFile.write();
                    }
                    break;
                    
                default:
                    break;
            }
            
            csvOutFile.close();
        }
        

        
                
        EZDDebugOutput.println(5, "doProcess_NFAL0200Scrn00_DownloadCoaBtn================================END", this);
    }

    // START 2016/10/21 J.Kim [QC#13514,ADD]
    private void doProcess_NFAL0200Scrn00_OnChangeSavedSearchOption(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFAL0200CMsg bizMsg = (NFAL0200CMsg) cMsg;

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NFAL0200Constant.BIZ_ID);

        if (!NFAL0200CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.coaCmpyCd, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaCcCd, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctCd, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaChCd, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaAfflCd, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaProjCd, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox, pMsg.srchOptTxt_10);

    }
    // END 2016/10/21 J.Kim [QC#13514,ADD]
}
