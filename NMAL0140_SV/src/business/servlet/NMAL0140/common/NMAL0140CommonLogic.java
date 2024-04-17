package business.servlet.NMAL0140.common;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;

import business.servlet.NMAL0140.NMAL0140BMsg;
import business.servlet.NMAL0140.NMAL0140_PBMsgArray;
import business.servlet.NMAL0140.constant.NMAL0140Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 10/05/2015   SRAA            K.Aratani       Update          
 *</pre>
 */
public class NMAL0140CommonLogic implements NMAL0140Constant {

	public static void setPage(NMAL0140BMsg scrnMsg, int page) {
		ZYPTableUtil.clear(scrnMsg.A);
		scrnMsg.xxPageShowFromNum_10.setValue(page);
		scrnMsg.xxPageShowToNum_10.clear();
		scrnMsg.xxPageShowOfNum_10.clear();
	}

	public static void changeActivation(S21CommonHandler handler, S21UserProfileService profile, NMAL0140BMsg scrnMsg) {
        // Table Color Setting
        S21TableColorController tblColor = new S21TableColorController(NMAL0140Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        //tblColor.setAlternateRowsBG("A2", scrnMsg.A);
	    String user = profile.getContextUserInfo().getUserId();
	    boolean authRead = profile.isFunctionGranted(user, NMAL0140Constant.AUTH_READ);
		if (authRead) {

			handler.setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);
			handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 0, null);
			handler.setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);
			handler.setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);
			handler.setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);
			handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null);
			handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);
			handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null);
			handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 1, null);
			handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);
	        
			handler.setButtonEnabled(SEARCH_BTN[1], true);
			if (scrnMsg.A.getValidCount() > 0) {
				for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
					scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
					scrnMsg.A.no(i).xxDtlCd_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseCstUpdTpNm_A1.setInputProtected(true);
					scrnMsg.A.no(i).xxScrItem54Txt_A1.setInputProtected(true);
					scrnMsg.A.no(i).rqstTotStdCostAmt_A1.setInputProtected(true);
					scrnMsg.A.no(i).rqstTotStdCostAmt_A1.setAppFracDigit(4);
					scrnMsg.A.no(i).rqstTotStdCostAmt_SB.setInputProtected(true);
					scrnMsg.A.no(i).rqstTotStdCostAmt_SB.setAppFracDigit(4);
					scrnMsg.A.no(i).mdseCstUpdEffFromDt_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseCstUpdCratDt_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseCstUpdStsNm_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseCstUpdGrpTxt_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseCstUpdRefTxt_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseCstUpdCratPsnCd_A1.setInputProtected(true);
				}
				handler.setButtonEnabled(VIEW_ITEM_BTN[1], true);
			} else {
				handler.setButtonEnabled(VIEW_ITEM_BTN[1], false);
			}
		} else {
			handler.setButtonEnabled(SEARCH_BTN[1], false);
			handler.setButtonEnabled(VIEW_ITEM_BTN[1], false);
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
		}
	}
	
	
	public static Object[] toArray_popup(NMAL0140_PBMsgArray p, int size) {
		Object[] param = new Object[size];
		for (int i = 0; i < size; i++) {
			param[i] = p.no(i).xxPopPrm;
		}
		return param;
	}

	public static Object[] setParamForNMAL6040(NMAL0140BMsg scrnMsg) {
		ZYPTableUtil.clear(scrnMsg.P);
		
		scrnMsg.P.no(0).xxPopPrm.clear();
		scrnMsg.P.no(2).xxPopPrm.clear();
		scrnMsg.P.no(4).xxPopPrm.clear();
		scrnMsg.P.no(6).xxPopPrm.clear();
		scrnMsg.P.no(8).xxPopPrm.clear();
		scrnMsg.P.no(10).xxPopPrm.clear();
		scrnMsg.P.no(12).xxPopPrm.clear();

		Object[] params = toArray_popup(scrnMsg.P, 28);
		
		return params;
	}
	
    public static String setSelectFromStr(NMAL0140BMsg scrnMsg, String glblCmpyCd) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("SELECT ");
        sb.append("    RS.EZCANCELFLAG ");
        sb.append("   ,RS.GLBL_CMPY_CD ");
        sb.append("   ,RS.RTL_WH_CD ");
        sb.append("   ,W.RTL_WH_NM ");
        sb.append("   ,RS.RTL_SWH_CD ");
        sb.append("   ,RS.RTL_SWH_NM ");
        sb.append("   ,RS.RTL_WH_CATG_CD ");
        sb.append("   ,C.RTL_WH_CATG_NM ");
        sb.append("FROM ");
        sb.append("    RTL_SWH RS ");
        sb.append("   ,RTL_WH W ");
        sb.append("   ,RTL_WH_CATG C ");
        sb.append("WHERE ");
        sb.append("    RS.EZCANCELFLAG = '0' ");
        sb.append("AND RS.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append("AND RS.EZCANCELFLAG = W.EZCANCELFLAG ");
        sb.append("AND RS.GLBL_CMPY_CD = W.GLBL_CMPY_CD ");
        sb.append("AND RS.RTL_WH_CD = W.RTL_WH_CD ");
        sb.append("AND RS.EZCANCELFLAG = C.EZCANCELFLAG (+) ");
        sb.append("AND RS.GLBL_CMPY_CD = C.GLBL_CMPY_CD (+) ");
        sb.append("AND RS.RTL_WH_CATG_CD = C.RTL_WH_CATG_CD (+) ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereList(NMAL0140BMsg scrnMsg) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Warehouse";
        whereArray0[1] = "RTL_WH_CD";
        if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_H1)) {
            whereArray0[2] = scrnMsg.rtlWhCd_H1.getValue();
        }
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Sub Warehouse";
        whereArray1[1] = "RTL_SWH_CD";
        if (ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd_H1)) {
            whereArray1[2] = scrnMsg.rtlSwhCd_H1.getValue();
        }
        whereArray1[3] = FLG_OFF_N;
        whereList.add(whereArray1);

        return whereList;
    }
    public static List<Object[]> setTblColumnList(NMAL0140BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "WH";
        columnArray0[1] = "RTL_WH_CD";
        columnArray0[2] = BigDecimal.valueOf(10);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "WH Name";
        columnArray1[1] = "RTL_WH_NM";
        columnArray1[2] = BigDecimal.valueOf(21);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Sub WH";
        columnArray2[1] = "RTL_SWH_CD";
        columnArray2[2] = BigDecimal.valueOf(10);
        columnArray2[3] = FLG_ON_Y;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Sub WH Name";
        columnArray3[1] = "RTL_SWH_NM";
        columnArray3[2] = BigDecimal.valueOf(21);
        columnArray3[3] = FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "WH Catg";
        columnArray4[1] = "RTL_WH_CATG_CD";
        columnArray4[2] = BigDecimal.valueOf(10);
        columnArray4[3] = FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[4];
        columnArray5[0] = "WH Catg Name";
        columnArray5[1] = "RTL_WH_CATG_NM";
        columnArray5[2] = BigDecimal.valueOf(21);
        columnArray5[3] = FLG_OFF_N;
        columnList.add(columnArray5);

        return columnList;
    }

    public static List<Object[]> setSortList(NMAL0140BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "RTL_WH_CD";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        Object[] sortCondArray1 = new Object[2];
        sortCondArray1[0] = "RTL_SWH_CD";
        sortCondArray1[1] = "";
        sortCondList.add(sortCondArray1);

        return sortCondList;
    }
    /**
     * commonAddCheckItem
     * @param scrnMsg NMAL0140BMsg
     */
    public static void commonAddCheckItem(NMAL0140BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);

        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdGrpTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdRefTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdRefId_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseItemTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaMdseTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaProdCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdEffFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_CC);
    }

}
