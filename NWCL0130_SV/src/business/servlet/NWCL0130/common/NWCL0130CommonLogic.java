package business.servlet.NWCL0130.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWCL0130.NWCL0130BMsg;
import business.servlet.NWCL0130.NWCL0130_PBMsgArray;
import business.servlet.NWCL0130.constant.NWCL0130Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONSL_RGNR_ACT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PROC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/04/2016   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NWCL0130CommonLogic implements NWCL0130Constant {

	public static void setPage(NWCL0130BMsg scrnMsg, int page) {
		ZYPTableUtil.clear(scrnMsg.A);
		scrnMsg.xxPageShowCurNum_10.setValue(page);
		scrnMsg.xxPageShowFromNum_10.setValue(page);
		scrnMsg.xxPageShowToNum_10.clear();
		scrnMsg.xxPageShowOfNum_10.clear();
	}
	
    public static String setSelectFromStrForConslBill(String glblCmpyCd) {

        String tble = ZYPCodeDataUtil.getVarCharConstValue("CANON_E479_INV_SRCH_TBL", glblCmpyCd);
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("SELECT ");
        sb.append("    CB.EZCANCELFLAG ");
        sb.append("   ,CB.GLBL_CMPY_CD ");
        sb.append("   ,TO_CHAR(CB.CONSL_BILL_PK) CONSL_BILL_NUM ");
        sb.append("   ,CB.BILL_TO_CUST_CD ");
        sb.append("   ,CB.BILL_TO_CUST_ACCT_CD ");
        sb.append("   ,CB.BILL_TO_CUST_ACCT_NM ");
        sb.append("   ,CB.CTOFF_DT ");
        sb.append("   ,CB.PMT_TERM_CASH_DISC_DESC_TXT ");
        sb.append("   ,CB.CONSL_TOT_DEAL_NET_AMT ");
        sb.append("FROM ");
        sb.append("    CONSL_BILL CB ");
        sb.append("WHERE ");
        sb.append("    CB.EZCANCELFLAG = '0' ");
        sb.append("AND CB.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append("AND EXISTS (SELECT '1' FROM INV_PRT_CTRL IPC WHERE  ");
        sb.append("IPC.GLBL_CMPY_CD = CB.GLBL_CMPY_CD AND IPC.EZCANCELFLAG = '0' ");
        sb.append("AND IPC.CONSL_BILL_NUM = TO_CHAR(CB.CONSL_BILL_PK) ");
        sb.append("AND IPC.INV_PROC_TP_CD IN ('").append(INV_PROC_TP.SPECIAL_BILLING).append("','").append(INV_PROC_TP.MANUAL_BILLING).append("')");
        sb.append("AND IPC.INV_PRT_CTRL_REC_CD = 'BILL' ");
        sb.append("AND IPC.INV_SPCL_BILL_PROC_STS_CD = '2' ");
        sb.append(") ");
        if (ZYPCommonFunc.hasValue(tble)) {
            sb.append("AND EXISTS (SELECT '1' FROM canon_e479_excel_detail ceed,  ");
            sb.append(" canon_e479_excel_control ceec, canon_E479_inv_Srch_Tbl srch ");
            sb.append(" WHERE ceed.ref_id = ceec.ref_id ");
            sb.append(" AND ceed.sequence_id = ceec.sequence_id ");
            sb.append(" AND ceed.rectype = 'DETAIL' ");
            sb.append(" AND TO_CHAR(ceec.urn) = srch.urn_number ");// Mod QC#19317
            sb.append(" AND ( NVL (srch.REVIEW_REQUIRED, 'N') = 'N' OR ( NVL (srch.REVIEW_REQUIRED, 'N') = 'Y' ");
            sb.append(" AND NVL (srch.PENDING_ACTION, 'Y') in ('R', 'S')))");
            sb.append(" AND ceed.BILL_NUMBER = TO_CHAR(CB.CONSL_BILL_PK) ");
            sb.append(") ");
        }

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForConslBill(String conslBillNum) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Bill#";
        whereArray0[1] = "CONSL_BILL_NUM";
        if (ZYPCommonFunc.hasValue(conslBillNum)) {
            whereArray0[2] = conslBillNum;
        }
        whereArray0[3] = FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Bill To";
        whereArray1[1] = "BILL_TO_CUST_CD";
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        
        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Bill To Account#";
        whereArray2[1] = "BILL_TO_CUST_ACCT_CD";
        whereArray2[3] = FLG_ON_Y;
        whereList.add(whereArray2);
        
        Object[] whereArray3 = new Object[4];
        whereArray3[0] = "Customer Name";
        whereArray3[1] = "BILL_TO_CUST_ACCT_NM";
        whereArray3[3] = FLG_ON_Y;
        whereList.add(whereArray3);
        
        Object[] whereArray4 = new Object[4];
        whereArray4[0] = "Cut Off Date";
        whereArray4[1] = "CTOFF_DT";
        whereArray4[3] = FLG_OFF_N;
        whereList.add(whereArray4);
        
        Object[] whereArray5 = new Object[4];
        whereArray5[0] = "Payment Term";
        whereArray5[1] = "PMT_TERM_CASH_DISC_DESC_TXT";
        whereArray5[3] = FLG_ON_Y;
        whereList.add(whereArray5);
        
        Object[] whereArray6 = new Object[4];
        whereArray6[0] = "Amount";
        whereArray6[1] = "CONSL_TOT_DEAL_NET_AMT";
        whereArray6[3] = FLG_OFF_N;
        whereList.add(whereArray6);
        
        return whereList;
    }
    public static List<Object[]> setTblColumnListForConslBill(NWCL0130BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Bill#";
        columnArray0[1] = "CONSL_BILL_NUM";
        columnArray0[2] = BigDecimal.valueOf(11);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Bill To";
        columnArray1[1] = "BILL_TO_CUST_CD";
        columnArray1[2] = BigDecimal.valueOf(11);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Bill To Account#";
        columnArray2[1] = "BILL_TO_CUST_ACCT_CD";
        columnArray2[2] = BigDecimal.valueOf(11);
        columnArray2[3] = FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Customer Name";
        columnArray3[1] = "BILL_TO_CUST_ACCT_NM";
        columnArray3[2] = BigDecimal.valueOf(25);
        columnArray3[3] = FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "Cut Off Date";
        columnArray4[1] = "CTOFF_DT";
        columnArray4[2] = BigDecimal.valueOf(11);
        columnArray4[3] = FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[4];
        columnArray5[0] = "Payment Term";
        columnArray5[1] = "PMT_TERM_CASH_DISC_DESC_TXT";
        columnArray5[2] = BigDecimal.valueOf(12);
        columnArray5[3] = FLG_OFF_N;
        columnList.add(columnArray5);

        Object[] columnArray6 = new Object[4];
        columnArray6[0] = "Amount";
        columnArray6[1] = "CONSL_TOT_DEAL_NET_AMT";
        columnArray6[2] = BigDecimal.valueOf(11);
        columnArray6[3] = FLG_OFF_N;
        columnList.add(columnArray6);

        return columnList;
    }

    public static List<Object[]> setSortListForConslBill(NWCL0130BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "CONSL_BILL_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }	
    public static String setSelectFromStrForInvNum(String glblCmpyCd, NWCL0130BMsg scrnMsg) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("SELECT ");
        sb.append("    I.EZCANCELFLAG ");
        sb.append("   ,I.GLBL_CMPY_CD ");
        sb.append("   ,I.INV_NUM ");
        sb.append("   ,I.BILL_TO_CUST_CD ");
        sb.append("   ,I.CUST_CARE_TKT_NUM ");
        sb.append("   ,I.BILL_TO_CUST_ACCT_CD ");
        sb.append("   ,I.BILL_TO_CUST_ACCT_NM ");
        sb.append("   ,I.INV_DT ");
        sb.append("   ,IT.INV_TP_NM ");
        sb.append("   ,I.PMT_TERM_CASH_DISC_DESC_TXT ");
        sb.append("   ,I.INV_TOT_DEAL_NET_AMT ");
        sb.append("FROM ");
        sb.append("    INV I ");
        sb.append("   ,INV_TP IT ");
        sb.append("WHERE ");
        sb.append("    I.EZCANCELFLAG = '0' ");
        sb.append("AND I.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append("AND I.GLBL_CMPY_CD = IT.GLBL_CMPY_CD ");
        sb.append("AND I.INV_TP_CD = IT.INV_TP_CD ");
        sb.append("AND IT.EZCANCELFLAG = '0' ");
        sb.append("AND EXISTS (SELECT '1' FROM INV_PRT_CTRL IPC WHERE ");
        sb.append("IPC.GLBL_CMPY_CD = I.GLBL_CMPY_CD ");
        if (!CONSL_RGNR_ACT_TP.ADD.equals(scrnMsg.conslRgnrActTpCd_H1.getValue())) {
            sb.append("AND IPC.INV_SPCL_BILL_PROC_STS_CD = '2' ");
        }
        sb.append("AND IPC.INV_NUM = I.INV_NUM ");
        sb.append("AND IPC.EZCANCELFLAG = '0' ");
        sb.append("AND IPC.INV_PROC_TP_CD IN ('").append(INV_PROC_TP.SPECIAL_BILLING).append("','").append(INV_PROC_TP.MANUAL_BILLING).append("')");
        if (ZYPCommonFunc.hasValue(scrnMsg.conslBillNum_H1) 
                && CONSL_RGNR_ACT_TP.ADD.equals(scrnMsg.conslRgnrActTpCd_H1.getValue())) {
            sb.append(" AND (IPC.CONSL_BILL_NUM != '").append(scrnMsg.conslBillNum_H1.getValue()).append("' OR IPC.CONSL_BILL_NUM IS NULL ) ");
            sb.append(" AND IPC.BILL_TO_CUST_CD in (select BILL_TO_CUST_CD from CONSL_BILL a where a.CONSL_BILL_PK = ").append(scrnMsg.conslBillNum_H1.getValue()).append(") ");
        } else if (ZYPCommonFunc.hasValue(scrnMsg.conslBillNum_H1) 
                && (CONSL_RGNR_ACT_TP.DROP.equals(scrnMsg.conslRgnrActTpCd_H1.getValue())
                || CONSL_RGNR_ACT_TP.RETRANSMIT.equals(scrnMsg.conslRgnrActTpCd_H1.getValue()))) {
            sb.append(" AND IPC.CONSL_BILL_NUM = '").append(scrnMsg.conslBillNum_H1.getValue()).append("' ");
            sb.append(" AND IPC.BILL_TO_CUST_CD in (select BILL_TO_CUST_CD from CONSL_BILL a where a.CONSL_BILL_PK = ").append(scrnMsg.conslBillNum_H1.getValue()).append(") ");
        }
        sb.append(") ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForInvNum(String invNum) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Inv#";
        whereArray0[1] = "INV_NUM";
        if (ZYPCommonFunc.hasValue(invNum)) {
            whereArray0[2] = invNum;
        }
        whereArray0[3] = FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Bill To";
        whereArray1[1] = "BILL_TO_CUST_CD";
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Cust Ticket#";
        whereArray2[1] = "CUST_CARE_TKT_NUM";
        whereArray2[3] = FLG_ON_Y;
        whereList.add(whereArray2);

        
        Object[] whereArray3 = new Object[4];
        whereArray3[0] = "Bill To Account#";
        whereArray3[1] = "BILL_TO_CUST_ACCT_CD";
        whereArray3[3] = FLG_ON_Y;
        whereList.add(whereArray3);
        
        Object[] whereArray4 = new Object[4];
        whereArray4[0] = "Customer Name";
        whereArray4[1] = "BILL_TO_CUST_ACCT_NM";
        whereArray4[3] = FLG_ON_Y;
        whereList.add(whereArray4);
        
        Object[] whereArray5 = new Object[4];
        whereArray5[0] = "Invoice Date";
        whereArray5[1] = "INV_DT";
        whereArray5[3] = FLG_OFF_N;
        whereList.add(whereArray5);
        
        Object[] whereArray6 = new Object[4];
        whereArray6[0] = "Invoice Type";
        whereArray6[1] = "INV_TP_NM";
        whereArray6[3] = FLG_ON_Y;
        whereList.add(whereArray6);
        
        Object[] whereArray7 = new Object[4];
        whereArray7[0] = "Payment Term";
        whereArray7[1] = "PMT_TERM_CASH_DISC_DESC_TXT";
        whereArray7[3] = FLG_ON_Y;
        whereList.add(whereArray7);
        
        Object[] whereArray8 = new Object[4];
        whereArray8[0] = "Amount";
        whereArray8[1] = "INV_TOT_DEAL_NET_AMT";
        whereArray8[3] = FLG_OFF_N;
        whereList.add(whereArray8);
        
        return whereList;
    }
    public static List<Object[]> setTblColumnListForInvNum(NWCL0130BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Inv#";
        columnArray0[1] = "INV_NUM";
        columnArray0[2] = BigDecimal.valueOf(6);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Bill To";
        columnArray1[1] = "BILL_TO_CUST_CD";
        columnArray1[2] = BigDecimal.valueOf(6);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Cust Ticket#";
        columnArray2[1] = "CUST_CARE_TKT_NUM";
        columnArray2[2] = BigDecimal.valueOf(9);
        columnArray2[3] = FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Bill To Account#";
        columnArray3[1] = "BILL_TO_CUST_ACCT_CD";
        columnArray3[2] = BigDecimal.valueOf(10);
        columnArray3[3] = FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "Customer Name";
        columnArray4[1] = "BILL_TO_CUST_ACCT_NM";
        columnArray4[2] = BigDecimal.valueOf(24);
        columnArray4[3] = FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[4];
        columnArray5[0] = "Invoice Date";
        columnArray5[1] = "INV_DT";
        columnArray5[2] = BigDecimal.valueOf(8);
        columnArray5[3] = FLG_OFF_N;
        columnList.add(columnArray5);

        Object[] columnArray6 = new Object[4];
        columnArray6[0] = "Invoice Type";
        columnArray6[1] = "INV_TP_NM";
        columnArray6[2] = BigDecimal.valueOf(8);
        columnArray6[3] = FLG_OFF_N;
        columnList.add(columnArray6);

        Object[] columnArray7 = new Object[4];
        columnArray7[0] = "Payment Term";
        columnArray7[1] = "PMT_TERM_CASH_DISC_DESC_TXT";
        columnArray7[2] = BigDecimal.valueOf(12);
        columnArray7[3] = FLG_OFF_N;
        columnList.add(columnArray7);

        Object[] columnArray8 = new Object[4];
        columnArray8[0] = "Amount";
        columnArray8[1] = "INV_TOT_DEAL_NET_AMT";
        columnArray8[2] = BigDecimal.valueOf(9);
        columnArray8[3] = FLG_OFF_N;
        columnList.add(columnArray8);

        return columnList;
    }

    public static List<Object[]> setSortListForInvNum(NWCL0130BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "INV_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }	
    
	public static void changeActivation(S21CommonHandler handler, S21UserProfileService profile, NWCL0130BMsg scrnMsg) {
		
        // Table Color Setting
        S21TableColorController tblColor = new S21TableColorController(NWCL0130Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A1", scrnMsg.A);
        controlTableFocusRule(scrnMsg);
        
	    String user = profile.getContextUserInfo().getUserId();
	    boolean authEdit = profile.isFunctionGranted(user, NWCL0130Constant.AUTH_EDIT);
		if (authEdit) {
			if (scrnMsg.A.getValidCount() > 0) {
				for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
					scrnMsg.A.no(i).billToCustCd_A1.setInputProtected(true);
					scrnMsg.A.no(i).billToDsAcctNum_A1.setInputProtected(true);
					scrnMsg.A.no(i).billToDsAcctNm_A1.setInputProtected(true);
					scrnMsg.A.no(i).conslBillNum_A1.setInputProtected(true);
					scrnMsg.A.no(i).invNum_A1.setInputProtected(true);
					scrnMsg.A.no(i).custCareTktNum_A1.setInputProtected(true);
					scrnMsg.A.no(i).xxUrnNum_A1.setInputProtected(true);
					scrnMsg.A.no(i).invDt_A1.setInputProtected(true);
					scrnMsg.A.no(i).invTotDealNetAmt_A1.setInputProtected(true);
					scrnMsg.A.no(i).invTotDealNetAmt_A1.setAppFracDigit(2);
					scrnMsg.A.no(i).conslRgnrActTpNm_A1.setInputProtected(true);
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
					scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
					scrnMsg.A.no(i).billToCustCd_A1.setInputProtected(true);
					scrnMsg.A.no(i).billToDsAcctNum_A1.setInputProtected(true);
					scrnMsg.A.no(i).billToDsAcctNm_A1.setInputProtected(true);
					scrnMsg.A.no(i).conslBillNum_A1.setInputProtected(true);
					scrnMsg.A.no(i).invNum_A1.setInputProtected(true);
					scrnMsg.A.no(i).custCareTktNum_A1.setInputProtected(true);
					scrnMsg.A.no(i).xxUrnNum_A1.setInputProtected(true);
					scrnMsg.A.no(i).invDt_A1.setInputProtected(true);
					scrnMsg.A.no(i).invTotDealNetAmt_A1.setInputProtected(true);
					scrnMsg.A.no(i).invTotDealNetAmt_A1.setAppFracDigit(2);
					scrnMsg.A.no(i).conslRgnrActTpNm_A1.setInputProtected(true);
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
			handler.setButtonEnabled(Drop_BTN[1], false);
			handler.setButtonEnabled(Retransmit_BTN[1], false);
			handler.setButtonEnabled(Del_BTN[1], false);
		}
	}
	
	public static Object[] toArray_popup(NWCL0130_PBMsgArray p, int size) {
		Object[] param = new Object[size];
		for (int i = 0; i < size; i++) {
			param[i] = p.no(i).xxPopPrm;
		}
		return param;
	}
	
    public static void checkInput_AddDropRetDel(EZDApplicationContext ctx, NWCL0130BMsg scrnMsg) {
    	
        scrnMsg.addCheckItem(scrnMsg.conslBillNum_H1);
        scrnMsg.addCheckItem(scrnMsg.invNum_H1);
    	// #############
    	// Detail
    	// #############
        scrnMsg.addCheckItem(scrnMsg.A);
        if (scrnMsg.A.getValidCount() != 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            	scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            }
	    }
        scrnMsg.putErrorScreen();
        
        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith("E")) {
            throw new EZDFieldErrorException();
        }
    	
    }

    public static void checkInput_Submit(EZDApplicationContext ctx, NWCL0130BMsg scrnMsg, boolean flg) {
    	
    	if (flg) {
	        scrnMsg.addCheckItem(scrnMsg.conslBillNum_H1);
	        scrnMsg.addCheckItem(scrnMsg.invNum_H1);
    	}
    	// #############
    	// Detail
    	// #############
        scrnMsg.addCheckItem(scrnMsg.A);
        if (scrnMsg.A.getValidCount() != 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            	scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            }
	    }
        scrnMsg.putErrorScreen();
        
        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith("E")) {
            throw new EZDFieldErrorException();
        }
    	
    }
    
    /**
     * @param tblFocusRule ZYPGUITableFocusRule
     */
    public static void controlTableFocusRule(NWCL0130BMsg scrnMsg) {

    	ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule( "NWCL0130Scrn00", "A" );
    	scrnMsg.addGUIAttribute( tblFocusRule );
    }

}
