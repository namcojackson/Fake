package business.servlet.NMAL7120.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7120.NMAL7120BMsg;
import business.servlet.NMAL7120.NMAL7120_PBMsgArray;
import business.servlet.NMAL7120.constant.NMAL7120Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 10/05/2015   SRAA            K.Aratani       Update          
 * 02/08/2016   FUJITSU         T.Murai         Update          #3010
 * 02/19/2016   FUJITSU         W.Honda         Update          #1130
 * 07/20/2016   FUJITSU         W.Honda         Update          #9690
 * 08/03/2016   FUJITSU         R.Nakamura      Update          #12174
 * 09/23/2016   Hitachi         Y.Takeno        Update          QC#13083
 * 09/12/2017   Fujitsu         K.Ishizuka      Update          QC#20206(Sol#269)
 *</pre>
 */
public class NMAL7120CommonLogic implements NMAL7120Constant {

	public static void setPage(NMAL7120BMsg scrnMsg, int page) {
		ZYPTableUtil.clear(scrnMsg.A);
		scrnMsg.xxPageShowCurNum_10.setValue(page);
		scrnMsg.xxPageShowFromNum_10.setValue(page);
		scrnMsg.xxPageShowToNum_10.clear();
		scrnMsg.xxPageShowOfNum_10.clear();
	}
	
    public static String setSelectFromStrForPrcContr(String glblCmpyCd) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("SELECT ");
        sb.append("    PC.EZCANCELFLAG ");
        sb.append("   ,PC.GLBL_CMPY_CD ");
        sb.append("   ,PC.PRC_CONTR_NUM ");
        sb.append("   ,PC.PRC_CONTR_NM ");
        sb.append("   ,PC.PRC_CONTR_CUST_BID_NUM ");
        sb.append("   ,PC.PRC_CONTR_SHORT_DESC_TXT ");
        sb.append("   ,PC.EFF_FROM_DT ");
        sb.append("   ,PC.EFF_THRU_DT ");
        sb.append("FROM ");
        sb.append("    PRC_CONTR PC ");
        sb.append("WHERE ");
        sb.append("    PC.EZCANCELFLAG = '0' ");
        sb.append("AND PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForPrcContr(String prcContrNum) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Prc Contr Num";
        whereArray0[1] = "PRC_CONTR_NUM";
        if (ZYPCommonFunc.hasValue(prcContrNum)) {
            whereArray0[2] = prcContrNum;
        }
        whereArray0[3] = FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Prc Contr Nm";
        whereArray1[1] = "PRC_CONTR_NM";
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        
        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Bid Num";
        whereArray2[1] = "PRC_CONTR_CUST_BID_NUM";
        whereArray2[3] = FLG_ON_Y;
        whereList.add(whereArray2);
        
        Object[] whereArray3 = new Object[4];
        whereArray3[0] = "Desc Txt";
        whereArray3[1] = "PRC_CONTR_SHORT_DESC_TXT";
        whereArray3[3] = FLG_ON_Y;
        whereList.add(whereArray3);
        
        Object[] whereArray4 = new Object[4];
        whereArray4[0] = "Eff From Date";
        whereArray4[1] = "EFF_FROM_DT";
        whereArray4[3] = FLG_OFF_N;
        whereList.add(whereArray4);
        
        Object[] whereArray5 = new Object[4];
        whereArray5[0] = "Eff Thru Date";
        whereArray5[1] = "EFF_THRU_DT";
        whereArray5[3] = FLG_OFF_N;
        whereList.add(whereArray5);
        
        return whereList;
    }
    public static List<Object[]> setTblColumnListForPrcContr(NMAL7120BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Prc Contr Num";
        columnArray0[1] = "PRC_CONTR_NUM";
        columnArray0[2] = BigDecimal.valueOf(41);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Prc Contr Nm";
        columnArray1[1] = "PRC_CONTR_NM";
        columnArray1[2] = BigDecimal.valueOf(41);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Bid Num";
        columnArray2[1] = "PRC_CONTR_CUST_BID_NUM";
        columnArray2[2] = BigDecimal.valueOf(10);
        columnArray2[3] = FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Desc Txt";
        columnArray3[1] = "PRC_CONTR_SHORT_DESC_TXT";
        columnArray3[2] = BigDecimal.valueOf(41);
        columnArray3[3] = FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "Eff From Date";
        columnArray4[1] = "EFF_FROM_DT";
        columnArray4[2] = BigDecimal.valueOf(10);
        columnArray4[3] = FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[4];
        columnArray5[0] = "Eff Thru Date";
        columnArray5[1] = "EFF_THRU_DT";
        columnArray5[2] = BigDecimal.valueOf(10);
        columnArray5[3] = FLG_OFF_N;
        columnList.add(columnArray5);

        return columnList;
    }

    public static List<Object[]> setSortListForPrcContr(NMAL7120BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "PRC_CONTR_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        Object[] sortCondArray1 = new Object[2];
        sortCondArray1[0] = "PRC_CONTR_NM";
        sortCondArray1[1] = "";
        sortCondList.add(sortCondArray1);

        
        return sortCondList;
    }	
    public static String setSelectFromStrForPrcCatg(String glblCmpyCd) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("SELECT ");
        sb.append("    PC.EZCANCELFLAG ");
        sb.append("   ,PC.GLBL_CMPY_CD ");
        sb.append("   ,PC.PRC_CATG_CD ");
        sb.append("   ,PC.PRC_CATG_NM ");
        sb.append("   ,PC.PRC_CATG_DESC_TXT ");
        sb.append("   ,PC.PRC_LIST_DPLY_NM ");
        sb.append("   ,PC.EFF_FROM_DT ");
        sb.append("   ,PC.EFF_THRU_DT ");
        sb.append("FROM ");
        sb.append("    PRC_CATG PC ");
        sb.append("   ,PRC_CTX_RELN PCR ");
        sb.append("WHERE ");
        sb.append("    PC.EZCANCELFLAG = '0' ");
        sb.append("AND PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append("AND PC.EZCANCELFLAG = PCR.EZCANCELFLAG ");
        sb.append("AND PC.GLBL_CMPY_CD = PCR.GLBL_CMPY_CD ");
        sb.append("AND PC.PRC_LIST_TP_CD = PCR.PRC_LIST_TP_CD ");
        sb.append("AND PCR.PRC_CTX_TP_CD = '").append(PRC_CTX_TP.CSMP_CREDIT).append("' ");

        return sb.toString();
    }
    public static List<Object[]> setWhereListForPrcCatg(String prcCatgNm) {
        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Prc List Nm";
        whereArray1[1] = "PRC_CATG_NM";
        if (ZYPCommonFunc.hasValue(prcCatgNm)) {
        	whereArray1[2] = prcCatgNm;
        }
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray3 = new Object[4];
        whereArray3[0] = "Prc List Disp Nm";
        whereArray3[1] = "PRC_LIST_DPLY_NM";
        whereArray3[3] = FLG_ON_Y;
        whereList.add(whereArray3);
        
        Object[] whereArray4 = new Object[4];
        whereArray4[0] = "Eff From Date";
        whereArray4[1] = "EFF_FROM_DT";
        whereArray4[3] = FLG_OFF_N;
        whereList.add(whereArray4);
        
        Object[] whereArray5 = new Object[4];
        whereArray5[0] = "Eff Thru Date";
        whereArray5[1] = "EFF_THRU_DT";
        whereArray5[3] = FLG_OFF_N;
        whereList.add(whereArray5);
        
        return whereList;
    }
    public static List<Object[]> setTblColumnListForPrcCatg(NMAL7120BMsg scrnMsg) {
        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Prc List Nm";
        columnArray1[1] = "PRC_CATG_NM";
        columnArray1[2] = BigDecimal.valueOf(35);
        columnArray1[3] = FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Prc List Disp Nm";
        columnArray3[1] = "PRC_LIST_DPLY_NM";
        columnArray3[2] = BigDecimal.valueOf(35);
        columnArray3[3] = FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "Eff From Date";
        columnArray4[1] = "EFF_FROM_DT";
        columnArray4[2] = BigDecimal.valueOf(10);
        columnArray4[3] = FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[4];
        columnArray5[0] = "Eff Thru Date";
        columnArray5[1] = "EFF_THRU_DT";
        columnArray5[2] = BigDecimal.valueOf(10);
        columnArray5[3] = FLG_OFF_N;
        columnList.add(columnArray5);

        return columnList;
    }
    public static List<Object[]> setSortListForPrcCatg(NMAL7120BMsg scrnMsg) {
        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "PRC_CATG_CD";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        Object[] sortCondArray1 = new Object[2];
        sortCondArray1[0] = "PRC_CATG_NM";
        sortCondArray1[1] = "";
        sortCondList.add(sortCondArray1);

        
        return sortCondList;
    }
    
	public static void changeActivation(S21CommonHandler handler, S21UserProfileService profile, NMAL7120BMsg scrnMsg) {
		
        // Table Color Setting
        S21TableColorController tblColor = new S21TableColorController(NMAL7120Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A1", scrnMsg.A);
        tblColor.setAlternateRowsBG("A2", scrnMsg.A);
        controlTableFocusRule(scrnMsg);
        
	    String user = profile.getContextUserInfo().getUserId();
	    boolean authEdit = profile.isFunctionGranted(user, NMAL7120Constant.AUTH_EDIT);
		if (authEdit) {
			if (scrnMsg.A.getValidCount() > 0) {
				for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
					scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
					scrnMsg.A.no(i).prcContrNum_A1.setInputProtected(true); //S21_NA ADD QC#20206(L3#269)
				}
			}
			handler.setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);
			handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 1, null);
			handler.setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);
			handler.setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);
			handler.setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);
			handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 1, null);
			handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);
			handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null);
			handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 1, null);
			handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);
			handler.setButtonEnabled(Search_BTN[1], true);
			handler.setButtonEnabled(Add_BTN[1], true);
			handler.setButtonEnabled(Del_BTN[1], true);
			
		} else {
			scrnMsg.xxChkBox_AL.setInputProtected(true);
			if (scrnMsg.A.getValidCount() > 0) {
				for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
					handler.setButtonEnabled(Line_Cust_BTN[1], i, false);
					handler.setButtonEnabled(Line_Prc_Catg_BTN[1], i, false);
					handler.setButtonEnabled(Line_Prc_Contr_BTN[1], i, false);
					handler.setButtonEnabled(Line_Coa_Br_BTN[1], i, false);
					scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
					scrnMsg.A.no(i).dsAcctNum_A1.setInputProtected(true);
					scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
					scrnMsg.A.no(i).csmpNum_A1.setInputProtected(true);
					scrnMsg.A.no(i).dlrRefNum_A1.setInputProtected(true);
					scrnMsg.A.no(i).prcCatgNm_A1.setInputProtected(true);
					scrnMsg.A.no(i).prcContrNum_A1.setInputProtected(true);
					scrnMsg.A.no(i).origCoaBrCd_A1.setInputProtected(true);
					scrnMsg.A.no(i).rtlDlrCd_A1.setInputProtected(true);
					scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
					scrnMsg.A.no(i).effThruDt_A1.setInputProtected(true);
					scrnMsg.A.no(i).cusaRejDt_A1.setInputProtected(true);
					scrnMsg.A.no(i).erlTrmnDt_A1.setInputProtected(true);
					scrnMsg.A.no(i).rnwCsmpNum_A1.setInputProtected(true);
					scrnMsg.A.no(i).uplftEquipRatio_A1.setInputProtected(true);
					scrnMsg.A.no(i).uplftAsryRatio_A1.setInputProtected(true);
					scrnMsg.A.no(i).csmpNumCmntTxt_A1.setInputProtected(true);
			        // 2016/07/20 CSA-QC#9690 Add Start
                    scrnMsg.A.no(i).custMbrId_A1.setInputProtected(true);
                    // 2016/07/20 CSA-QC#9690 Add end
					scrnMsg.A.no(i).csmpContrActvFlg_A1.setInputProtected(true);
				}
			}
			handler.setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);
			handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 0, null);
			handler.setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);
			handler.setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);
			handler.setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);
			handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null);
			handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);
			handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 0, null);
			handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 0, null);
			handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);
			handler.setButtonEnabled(Add_BTN[1], false);
			handler.setButtonEnabled(Del_BTN[1], false);
		}
	}
	
	public static Object[] toArray_popup(NMAL7120_PBMsgArray p, int size) {
		Object[] param = new Object[size];
		for (int i = 0; i < size; i++) {
			param[i] = p.no(i).xxPopPrm;
		}
		return param;
	}
	
    public static void checkInput_Submit(EZDApplicationContext ctx, NMAL7120BMsg scrnMsg) {
    	
    	// #############
    	// Detail
    	// #############
        scrnMsg.addCheckItem(scrnMsg.A);
        if (scrnMsg.A.getValidCount() != 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            	
            	if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effFromDt_A1)
            			&& ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effThruDt_A1)) {
                	if (ZYPDateUtil.compare(scrnMsg.A.no(i).effFromDt_A1.getValue(), scrnMsg.A.no(i).effThruDt_A1.getValue()) > 0) {
                		//NMAM8173E=0,[@] cannot be set to a future date
                		scrnMsg.A.no(i).effThruDt_A1.setErrorInfo(1, "NMAM8173E", new String[]{"Expiration Date"});
                	}
            	}
                if (!csmpNumFormatCheck(scrnMsg.A.no(i).csmpNum_A1.getValue())) {
                    scrnMsg.A.no(i).csmpNum_A1.setErrorInfo(1, NMAM8075E, new String[] {CHECK_CSMP_FORMAT_TXT});
                }

                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dsAcctNum_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).csmpNum_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dlrRefNum_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prcCatgNm_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prcContrNum_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).origCoaBrCd_A1);
                // Add Start 2016/08/03 QC#12174
                scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlDlrCd_A1);
                // Add End 2016/08/03 QC#12174
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).cusaRejDt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).erlTrmnDt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).rnwCsmpNum_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).uplftEquipRatio_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).uplftAsryRatio_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).csmpNumCmntTxt_A1);
                // 2016/07/20 CSA-QC#9690 Add Start
                scrnMsg.addCheckItem(scrnMsg.A.no(i).custMbrId_A1);
                // 2016/07/20 CSA-QC#9690 Add end
                scrnMsg.addCheckItem(scrnMsg.A.no(i).csmpContrActvFlg_A1);
            }
	    }
        scrnMsg.putErrorScreen();
        
        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith("E")) {
            throw new EZDFieldErrorException();
        }
    	
    }
    
    public static void checkInput_Mass_Update_CSMP(EZDApplicationContext ctx, NMAL7120BMsg scrnMsg) {
    	
    	if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt_MU)
    			&& ZYPCommonFunc.hasValue(scrnMsg.effThruDt_MU)) {
        	if (ZYPDateUtil.compare(scrnMsg.effFromDt_MU.getValue(), scrnMsg.effThruDt_MU.getValue()) > 0) {
        		//NMAM8173E=0,[@] cannot be set to a future date
        		scrnMsg.effThruDt_MU.setErrorInfo(1, "NMAM8173E", new String[]{"Expiration Date"});
        	}
    	}
    	
        if (!csmpNumFormatCheck(scrnMsg.csmpNum_MU.getValue())) {
            scrnMsg.csmpNum_MU.setErrorInfo(1, NMAM8075E, new String[] {CHECK_CSMP_FORMAT_TXT});
        }

        scrnMsg.addCheckItem(scrnMsg.csmpNum_MU);
        scrnMsg.addCheckItem(scrnMsg.dlrRefNum_MU);
        scrnMsg.addCheckItem(scrnMsg.prcContrNum_MU);
        scrnMsg.addCheckItem(scrnMsg.csmpNumCmntTxt_MU);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_MU);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_MU);
        scrnMsg.addCheckItem(scrnMsg.csmpContrActvFlg_MU);
        scrnMsg.addCheckItem(scrnMsg.cusaRejDt_MU);
        scrnMsg.addCheckItem(scrnMsg.uplftEquipRatio_MU);
        scrnMsg.addCheckItem(scrnMsg.uplftAsryRatio_MU);
        scrnMsg.addCheckItem(scrnMsg.rnwCsmpNum_MU);
        scrnMsg.putErrorScreen();
        
        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith("E")) {
            throw new EZDFieldErrorException();
        }
    	
    }
    
    /**
     * @param tblFocusRule ZYPGUITableFocusRule
     */
    public static void controlTableFocusRule(NMAL7120BMsg scrnMsg) {

    	ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule( "NMAL7120Scrn00", "A" );
    	scrnMsg.addGUIAttribute( tblFocusRule );
    	
    	for( int i = 0; i < scrnMsg.A.getValidCount(); i++ ) {
	        ZYPGUIFocusRule fRule = new ZYPGUIFocusRule( "csmpTabIdx_01#" + i );
			if( i > 0 ) {
				fRule.setPrevId( "csmpTabIdx_04#" + (i-1) );
			}
			tblFocusRule.addRule( fRule );
						
			fRule = new ZYPGUIFocusRule( "csmpTabIdx_02#" + i );
			fRule.setNextId( "csmpTabIdx_03#" + i );
			tblFocusRule.addRule( fRule );
						
			fRule = new ZYPGUIFocusRule( "csmpTabIdx_03#" + i );
			fRule.setPrevId( "csmpTabIdx_02#" + i );
			tblFocusRule.addRule( fRule );
						
			fRule = new ZYPGUIFocusRule( "csmpTabIdx_04#" + i );
			if( (i+1) != scrnMsg.A.length() ) {
				fRule.setNextId( "csmpTabIdx_01#" + (i+1) );
			}
			tblFocusRule.addRule( fRule );
	        
    	}
    }

    /**
     * CSMP# Format Check
     * @param checkTarget String
     * @return
     */
    private static boolean csmpNumFormatCheck(String checkTarget) {
        if (!ZYPCommonFunc.hasValue(checkTarget)) {
            return true;
        }
        if (checkTarget.length() != LENGTH_11) {
            return false;
        }
        if (checkTarget.matches(CHECK_CSMP_FORMAT)) {
            return true;
        }
        return false;
    }

// Add Start 2016/09/23 QC#13083

    public static Object[] createArgumentNMAL6760(NMAL7120_PBMsgArray p, int size) {
        Object[] param = new Object[PARAM_SIZE_NMAL6760];

        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm;
        }

        // set empty parameter
        p.no(15).xxPopPrm.clear();
        for (int i = size; i < PARAM_SIZE_NMAL6760; i++) {
            param[i] = p.no(15).xxPopPrm;
        }

        // set display control flag
        p.no(16).xxPopPrm.setValue(ZYPConstant.FLG_ON_Y); // parameter value = "Y"
        param[PARAM_INDEX_NMAL6760_XX_DPLY_CTRL_FLG_HC] = p.no(16).xxPopPrm;
        param[PARAM_INDEX_NMAL6760_XX_DPLY_CTRL_FLG_AC] = p.no(16).xxPopPrm;
        param[PARAM_INDEX_NMAL6760_XX_DPLY_CTRL_FLG_SC] = p.no(16).xxPopPrm;
        param[PARAM_INDEX_NMAL6760_XX_DPLY_CTRL_FLG_CT] = p.no(16).xxPopPrm;

        return param;
    }

// Add End   2016/09/23 QC#13083

}
