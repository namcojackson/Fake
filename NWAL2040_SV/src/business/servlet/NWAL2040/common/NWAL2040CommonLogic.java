package business.servlet.NWAL2040.common;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import business.servlet.NWAL2040.NWAL2040BMsg;
import business.servlet.NWAL2040.NWAL2040_PBMsgArray;
import business.servlet.NWAL2040.constant.NWAL2040Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 2016/03/23   Hitachi         K.Kojima        Update          S21 NA Unit Test #56  Add Upload Function
 * 2017/09/12   Fujitsu         T.Ogura         Update          QC#19805
 * 2022/10/14   Hitachi         B.Amarsanaa     Update          QC#60609
 *</pre>
 */
public class NWAL2040CommonLogic implements NWAL2040Constant {

//	public static void setPage(NWAL2040BMsg scrnMsg, int page) {
//		ZYPTableUtil.clear(scrnMsg.A);
//		scrnMsg.xxPageShowFromNum_10.setValue(page);
//		scrnMsg.xxPageShowToNum_10.clear();
//		scrnMsg.xxPageShowOfNum_10.clear();
//	}

	public static void setInitialTab(S21CommonHandler handler, S21UserProfileService profile, NWAL2040BMsg scrnMsg) {
		
	    //String user = profile.getContextUserInfo().getUserId();
	    //boolean authSrcCatg = profile.isFunctionGranted(user, NWAL2040Constant.AUTH_TAB_SRC_CATG);
	    //boolean authMapTmplQlfy = profile.isFunctionGranted(user, NWAL2040Constant.AUTH_TAB_MAP_TMPL_QLFY);
	    boolean authSrcCatg = true;
        boolean authMapTmplQlfy = true;
        // 2017/09/12 QC#19805 Add Start
        boolean authMapTmplQlfyRMA = true;
        // 2017/09/12 QC#19805 Add End
	    if (authSrcCatg) {
		    scrnMsg.xxTabProt_SC.setValue("Y");
	    } else {
		    scrnMsg.xxTabProt_SC.setValue("N");
	    }
	    if (authMapTmplQlfy) {
		    scrnMsg.xxTabProt_MT.setValue("Y");
	    } else {
		    scrnMsg.xxTabProt_MT.setValue("N");
	    }
        // 2017/09/12 QC#19805 Add Start
        if (authMapTmplQlfyRMA) {
            scrnMsg.xxTabProt_MR.setValue("Y");
        } else {
            scrnMsg.xxTabProt_MR.setValue("N");
        }
        // 2017/09/12 QC#19805 Add End

	    if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab_H1)&& authSrcCatg) {
		    scrnMsg.xxDplyTab_H1.setValue(TAB_SRC_CATG);
	    } else if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab_H1)&& !authSrcCatg && authMapTmplQlfy) {
		    scrnMsg.xxDplyTab_H1.setValue(TAB_MAP_TMPL_QLFY);
	    // 2017/09/12 QC#19805 Add Start
        } else if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab_H1)&& !authSrcCatg && !authMapTmplQlfy && authMapTmplQlfyRMA) {
            scrnMsg.xxDplyTab_H1.setValue(TAB_MAP_TMPL_QLFY_RMA);
	    // 2017/09/12 QC#19805 Add End
        // 2017/09/12 QC#19805 Mod Start
//	    } else if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab_H1)&& !authSrcCatg && !authMapTmplQlfy) {
        } else if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab_H1)&& !authSrcCatg && !authMapTmplQlfy && !authMapTmplQlfyRMA) {
	    // 2017/09/12 QC#19805 Mod End
		    scrnMsg.xxDplyTab_H1.setValue("");
	    }
	}
	
	public static void changeActivation(S21CommonHandler handler, S21UserProfileService profile, NWAL2040BMsg scrnMsg) {
		
	    String user = profile.getContextUserInfo().getUserId();
	    boolean authEdit = profile.isFunctionGranted(user, NWAL2040Constant.AUTH_EDIT);
	    setInitialTab(handler, profile, scrnMsg);
        controlTableFocusRule(scrnMsg);
	    //boolean debug = true;
		if (authEdit) {
		//if (debug) {
			handler.setButtonEnabled(SEARCH_BTN[1], true);
			handler.setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);
			handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 1, null);
			handler.setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);
			handler.setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);
			handler.setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);
            // START 2016/03/24 K.Kojima [UT#56,DEL]
            // handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1],
            // CMN_BTN6[2], 0, null);
            // END 2016/03/24 K.Kojima [UT#56,DEL]
			handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);
			handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 0, null);
			handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 1, null);
			handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);
	        
			if (ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab_H1) && "SrcCatg".equals(scrnMsg.xxDplyTab_H1.getValue())) {
                // START 2016/03/24 K.Kojima [UT#56,ADD]
                handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null);
                // END 2016/03/24 K.Kojima [UT#56,ADD]
		        // Table Color Setting
		        S21TableColorController tblColor = new S21TableColorController(NWAL2040Constant.SCREEN_ID, scrnMsg);
		        tblColor.setAlternateRowsBG("B1", scrnMsg.B);
		        tblColor.clearRowsBG("A1", scrnMsg.A);
		        tblColor.clearRowsBG("A2", scrnMsg.A);
                // 2017/09/12 QC#19805 Add Start
                tblColor.clearRowsBG("E1", scrnMsg.E);
                // 2017/09/12 QC#19805 Add End
				if (scrnMsg.B.getValidCount() > 0) {
					for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
					}
				}
			} else if (ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab_H1) && "MapTmpQlfy".equals(scrnMsg.xxDplyTab_H1.getValue())) {
                // START 2016/03/24 K.Kojima [UT#56,ADD]
                handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 1, null);
                // END 2016/03/24 K.Kojima [UT#56,ADD]
		        // Table Color Setting
		        S21TableColorController tblColor = new S21TableColorController(NWAL2040Constant.SCREEN_ID, scrnMsg);
		        tblColor.setAlternateRowsBG("A1", scrnMsg.A);
		        tblColor.setAlternateRowsBG("A2", scrnMsg.A);
                tblColor.clearRowsBG("B1", scrnMsg.B);
                // 2017/09/12 QC#19805 Add Start
                tblColor.clearRowsBG("E1", scrnMsg.E);
                // 2017/09/12 QC#19805 Add End
				if (scrnMsg.A.getValidCount() > 0) {
					for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
					}
				}
			// 2017/09/12 QC#19805 Add Start
            } else if (ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab_H1) && TAB_MAP_TMPL_QLFY_RMA.equals(scrnMsg.xxDplyTab_H1.getValue())) {
                handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 1, null);
                // Table Color Setting
                S21TableColorController tblColor = new S21TableColorController(NWAL2040Constant.SCREEN_ID, scrnMsg);
                tblColor.setAlternateRowsBG("E1", scrnMsg.E);
                tblColor.clearRowsBG("B1", scrnMsg.B);
                tblColor.clearRowsBG("A1", scrnMsg.A);
                tblColor.clearRowsBG("A2", scrnMsg.A);
                if (scrnMsg.E.getValidCount() > 0) {
                    for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
                    }
                }
			// 2017/09/12 QC#19805 Add End
			}
		} else {
			handler.setButtonEnabled(SEARCH_BTN[1], false);
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
			
			if (ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab_H1) && "SrcCatg".equals(scrnMsg.xxDplyTab_H1.getValue())) {
				handler.setButtonEnabled(INS_SRC_CATG_BTN[1], false);
				handler.setButtonEnabled(DEL_SRC_CATG_BTN[1], false);
		        // Table Color Setting
		        S21TableColorController tblColor = new S21TableColorController(NWAL2040Constant.SCREEN_ID, scrnMsg);
		        tblColor.setAlternateRowsBG("B1", scrnMsg.B);
		        tblColor.clearRowsBG("A1", scrnMsg.A);
		        tblColor.clearRowsBG("A2", scrnMsg.A);
		        // 2017/09/12 QC#19805 Add Start
                tblColor.clearRowsBG("E1", scrnMsg.E);
		        // 2017/09/12 QC#19805 Add End
				if (scrnMsg.B.getValidCount() > 0) {
					for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
						scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(true);
						scrnMsg.B.no(i).dsOrdCatgCd_B1.setInputProtected(true);
						scrnMsg.B.no(i).dsOrdTpCd_B1.setInputProtected(true);
						scrnMsg.B.no(i).firstBizCtxAttrbTxt_B1.setInputProtected(true);
						scrnMsg.B.no(i).effFromDt_B1.setInputProtected(true);
						scrnMsg.B.no(i).effThruDt_B1.setInputProtected(true);
					}
				}
			} else if (ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab_H1) && "MapTmpQlfy".equals(scrnMsg.xxDplyTab_H1.getValue())) {
				handler.setButtonEnabled(INS_MAP_TMPL_BTN[1], false);
				handler.setButtonEnabled(DEL_MAP_TMPL_BTN[1], false);
                // START 2016/03/24 K.Kojima [UT#56,DEL]
                // handler.setButtonEnabled(FILTER_RESULT_BTN[1],
                // false);
                // END 2016/03/24 K.Kojima [UT#56,DEL]
                // START 2016/03/24 K.Kojima [UT#56,ADD]
                handler.setButtonEnabled(MASS_UPLOAD_BTN[1], false);
                // END 2016/03/24 K.Kojima [UT#56,ADD]
				handler.setButtonEnabled(OTBD_PRIM_ON_HND_CHK_WH_BTN[1], false);
				handler.setButtonEnabled(OTBD_PRIM_ON_HND_CHK_SWH_BTN[1], false);
				handler.setButtonEnabled(OTBD_SCD_ON_HND_CHK_WH_BTN[1], false);
				handler.setButtonEnabled(OTBD_SCD_ON_HND_CHK_SWH_BTN[1], false);
				handler.setButtonEnabled(OTBD_DEF_WH_BTN[1], false);
				handler.setButtonEnabled(OTBD_DEF_SWH_BTN[1], false);
				handler.setButtonEnabled(RMA_DEF_WH_BTN[1], false);
				handler.setButtonEnabled(RMA_DEF_SWH_BTN[1], false);
                // START 2016/03/24 K.Kojima [UT#56,DEL]
                // scrnMsg.defWhMapTmplCd_H1.setInputProtected(true);
                // END 2016/03/24 K.Kojima [UT#56,DEL]
		        // Table Color Setting
		        S21TableColorController tblColor = new S21TableColorController(NWAL2040Constant.SCREEN_ID, scrnMsg);
		        tblColor.setAlternateRowsBG("A1", scrnMsg.A);
		        tblColor.setAlternateRowsBG("A2", scrnMsg.A);
		        tblColor.clearRowsBG("B1", scrnMsg.B);
		        // 2017/09/12 QC#19805 Add Start
                tblColor.clearRowsBG("E1", scrnMsg.E);
		        // 2017/09/12 QC#19805 Add End
				if (scrnMsg.A.getValidCount() > 0) {
					for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
						scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
						scrnMsg.A.no(i).defWhMapTmplCd_A1.setInputProtected(true);
						scrnMsg.A.no(i).mdseItemClsTpCd_A1.setInputProtected(true);
						scrnMsg.A.no(i).fromPostCd_A1.setInputProtected(true);
						scrnMsg.A.no(i).toPostCd_A1.setInputProtected(true);
						scrnMsg.A.no(i).onHndChkFlg_A1.setInputProtected(true);
						scrnMsg.A.no(i).otbdPrimOnHndChkWhCd_A1.setInputProtected(true);
						scrnMsg.A.no(i).rtlSwhNm_01.setInputProtected(true);
						scrnMsg.A.no(i).otbdPrimOnHndLinSrcCd_A1.setInputProtected(true);
						scrnMsg.A.no(i).otbdScdOnHndChkWhCd_A1.setInputProtected(true);
						scrnMsg.A.no(i).rtlSwhNm_02.setInputProtected(true);
						scrnMsg.A.no(i).otbdScdOnHndLineSrcCd_A1.setInputProtected(true);
						scrnMsg.A.no(i).otbdDefWhCd_A1.setInputProtected(true);
						scrnMsg.A.no(i).rtlSwhNm_03.setInputProtected(true);
						scrnMsg.A.no(i).otbdDefLineSrcCd_A1.setInputProtected(true);
						// 2017/09/12 QC#19805 Del Start
//						scrnMsg.A.no(i).rmaDefWhCd_A1.setInputProtected(true);
//						scrnMsg.A.no(i).rtlSwhNm_04.setInputProtected(true);
//						scrnMsg.A.no(i).rmaDefLineSrcCd_A1.setInputProtected(true);
						// 2017/09/12 QC#19805 Del End
						scrnMsg.A.no(i).thirdPtyItemFlg_A1.setInputProtected(true);
					}
				}
			// 2017/09/12 QC#19805 Add Start
            } else if (ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab_H1) && TAB_MAP_TMPL_QLFY_RMA.equals(scrnMsg.xxDplyTab_H1.getValue())) {
                handler.setButtonEnabled(INS_MAP_TMPL_RMA_BTN[1], false);
                handler.setButtonEnabled(DEL_MAP_TMPL_RMA_BTN[1], false);
                handler.setButtonEnabled(MASS_UPLOAD_BTN[1], false);
                handler.setButtonEnabled(RMA_DEF_WH_BTN[1], false);
                // Table Color Setting
                S21TableColorController tblColor = new S21TableColorController(NWAL2040Constant.SCREEN_ID, scrnMsg);
                tblColor.setAlternateRowsBG("E1", scrnMsg.E);
                tblColor.clearRowsBG("B1", scrnMsg.B);
                tblColor.clearRowsBG("A1", scrnMsg.A);
                tblColor.clearRowsBG("A2", scrnMsg.A);
                if (scrnMsg.E.getValidCount() > 0) {
                    for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
                        scrnMsg.E.no(i).xxChkBox_E1.setInputProtected(true);
                        scrnMsg.E.no(i).defWhMapTmplCd_E1.setInputProtected(true);
                        scrnMsg.E.no(i).mdseItemClsTpCd_E1.setInputProtected(true);
                        scrnMsg.E.no(i).thirdPtyItemFlg_E1.setInputProtected(true);
                        scrnMsg.E.no(i).fromPostCd_E1.setInputProtected(true);
                        scrnMsg.E.no(i).toPostCd_E1.setInputProtected(true);
                        scrnMsg.E.no(i).onHndChkFlg_E1.setInputProtected(true);
                        scrnMsg.E.no(i).thirdPtyDspTpCd_E1.setInputProtected(true);
                        scrnMsg.E.no(i).dropRtrnVndCd_E1.setInputProtected(true);
                        scrnMsg.E.no(i).rmaDefWhCd_E1.setInputProtected(true);
                    }
                }
			// 2017/09/12 QC#19805 Add End
			}
		}
	}
	
	
	public static Object[] toArray_popup(NWAL2040_PBMsgArray p, int size) {
		Object[] param = new Object[size];
		for (int i = 0; i < size; i++) {
			param[i] = p.no(i).xxPopPrm;
		}
		return param;
	}

	public static Object[] setParamForNMAL6040(NWAL2040BMsg scrnMsg) {
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
	
    public static String setSelectFromStrForRtlWh(NWAL2040BMsg scrnMsg, String glblCmpyCd) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("SELECT ");
        sb.append("    distinct W.PHYS_WH_CD ");
        sb.append("    ,W.GLBL_CMPY_CD ");
        sb.append("    ,W.EZCANCELFLAG ");
        sb.append("FROM ");
        sb.append("    RTL_WH W ");
        sb.append("WHERE ");
        sb.append("    W.EZCANCELFLAG = '0' ");
        sb.append("AND W.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append("AND '").append(ZYPDateUtil.getBatProcDate()).append("' BETWEEN W.EFF_FROM_DT AND NVL(W.EFF_THRU_DT ,'99991231')");
        sb.append("AND W.LOC_TP_CD = '").append(LOC_TP.WAREHOUSE).append("' ");
        sb.append("AND W.PHYS_WH_CD is not null ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForRtlWh(NWAL2040BMsg scrnMsg, String physWhCd) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        //2022/10/14 QC#60609 Mod Start
        //whereArray0[0] = "Physical Warehouse";
        whereArray0[0] = "Warehouse";
        //2022/10/14 QC#60609 Mod End
        whereArray0[1] = "PHYS_WH_CD";
        if (ZYPCommonFunc.hasValue(physWhCd)) {
            whereArray0[2] = physWhCd;
        }
        //2022/10/14 QC#60609 Mod Start
        //whereArray0[3] = FLG_OFF_N;
        whereArray0[3] = FLG_ON_Y;
        //2022/10/14 QC#60609 Mod End
        whereList.add(whereArray0);

        return whereList;
    }
    public static List<Object[]> setTblColumnListForRtlWh(NWAL2040BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        //2022/10/14 QC#60609 Mod Start
        //columnArray0[0] = "Physical Warehouse Code";
        columnArray0[0] = "Warehouse Code";
        //2022/10/14 QC#60609 Mod End
        columnArray0[1] = "PHYS_WH_CD";
        columnArray0[2] = BigDecimal.valueOf(90);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        return columnList;
    }

    public static List<Object[]> setSortListForRtlWh(NWAL2040BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "PHYS_WH_CD";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }
    
    public static String setSelectFromStrForRtlSwh(NWAL2040BMsg scrnMsg, String glblCmpyCd) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("SELECT ");
        sb.append("    distinct S.EZCANCELFLAG ");
        sb.append("   ,S.GLBL_CMPY_CD ");
        sb.append("   ,S.RTL_SWH_CD ");
        sb.append("   ,S.RTL_SWH_NM ");
        sb.append("FROM ");
        sb.append("    SWH S ");
        sb.append("WHERE ");
        sb.append("    S.EZCANCELFLAG = '0' ");
        sb.append("AND S.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForRtlSwh(NWAL2040BMsg scrnMsg, String rtlSwhCd, String rtlSwhNm) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Sub Warehouse";
        whereArray0[1] = "RTL_SWH_CD";
        if (ZYPCommonFunc.hasValue(rtlSwhCd)) {
            whereArray0[2] = rtlSwhCd;
        }
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Sub Warehouse Name";
        whereArray1[1] = "RTL_SWH_NM";
        if (ZYPCommonFunc.hasValue(rtlSwhNm)) {
            whereArray1[2] = rtlSwhNm;
        }
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        return whereList;
    }
    public static List<Object[]> setTblColumnListForRtlSwh(NWAL2040BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "SWH";
        columnArray0[1] = "RTL_SWH_CD";
        columnArray0[2] = BigDecimal.valueOf(31);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "SWH Name";
        columnArray1[1] = "RTL_SWH_NM";
        columnArray1[2] = BigDecimal.valueOf(63);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        return columnList;
    }

    public static List<Object[]> setSortListForRtlSwh(NWAL2040BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "RTL_SWH_CD";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }
    /**
     * @param tblFocusRule ZYPGUITableFocusRule
     */
    public static void controlTableFocusRule(NWAL2040BMsg scrnMsg) {

    	ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule( "NWAL2040Scrn00", "A" );
    	scrnMsg.addGUIAttribute( tblFocusRule );
    	
    	for( int i = 0; i < scrnMsg.A.getValidCount(); i++ ) {
	        ZYPGUIFocusRule fRule = new ZYPGUIFocusRule( "defWhTabIdx_01#" + i );
			if( i > 0 ) {
				fRule.setPrevId( "defWhTabIdx_04#" + (i-1) );
			}
			tblFocusRule.addRule( fRule );
						
			fRule = new ZYPGUIFocusRule( "defWhTabIdx_02#" + i );
			fRule.setNextId( "defWhTabIdx_03#" + i );
			tblFocusRule.addRule( fRule );
						
			fRule = new ZYPGUIFocusRule( "defWhTabIdx_03#" + i );
			fRule.setPrevId( "defWhTabIdx_02#" + i );
			tblFocusRule.addRule( fRule );
						
			fRule = new ZYPGUIFocusRule( "defWhTabIdx_04#" + i );
			if( (i+1) != scrnMsg.A.length() ) {
				fRule.setNextId( "defWhTabIdx_01#" + (i+1) );
			}
			tblFocusRule.addRule( fRule );
	        
    	}
    }
}
