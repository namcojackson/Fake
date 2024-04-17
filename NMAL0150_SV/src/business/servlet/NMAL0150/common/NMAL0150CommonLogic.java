package business.servlet.NMAL0150.common;

import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0150.NMAL0150BMsg;
import business.servlet.NMAL0150.NMAL0150_PBMsgArray;
import business.servlet.NMAL0150.constant.NMAL0150Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 10/05/2015   SRAA            K.Aratani       Update          
 * 2022/05/30   Hitachi         A.Kohinata      Update          QC#55970
 *</pre>
 */
public class NMAL0150CommonLogic implements NMAL0150Constant {

	public static void changeActivation(S21CommonHandler handler, S21UserProfileService profile, NMAL0150BMsg scrnMsg) {
        // Table Color Setting
        S21TableColorController tblColor = new S21TableColorController(NMAL0150Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseCstUpdHistHdrPk_H1)) {
    		scrnMsg.mdseCstUpdTpCd_H1.setInputProtected(true);
    		scrnMsg.mdseCstUpdGrpTxt_H1.setInputProtected(true);
    		scrnMsg.mdseCstUpdDescTxt_H1.setInputProtected(true);
        }
		scrnMsg.mdseCstUpdRefTxt_H1.setInputProtected(true);
		scrnMsg.mdseCstUpdRefId_H1.setInputProtected(true);
		scrnMsg.mdseCstUpdCratPsnCd_H1.setInputProtected(true);
		scrnMsg.xxFullNm_H1.setInputProtected(true);
		scrnMsg.mdseCstUpdCratDt_H1.setInputProtected(true);
        
	    String user = profile.getContextUserInfo().getUserId();
	    boolean authEdit = profile.isFunctionGranted(user, NMAL0150Constant.AUTH_EDIT);
		if (authEdit && !ZYPCommonFunc.hasValue(scrnMsg.mdseCstUpdHistHdrPk_H1)) {
			if (scrnMsg.A.getValidCount() > 0) {
				for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
					if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseCd_AB)) {
						scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
						handler.setButtonEnabled(Line_Item_BTN[1], i, false);
					}
					scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseItemTpNm_A1.setInputProtected(true);
					scrnMsg.A.no(i).xxScrItem54Txt_A1.setInputProtected(true);
					scrnMsg.A.no(i).coaProdCd_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseCstUpdStsNm_A1.setInputProtected(true);
					scrnMsg.A.no(i).rqstTotStdCostAmt_A1.setAppFracDigit(2);
				}
			}
			handler.setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);
			handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 1, null);
			handler.setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);
			handler.setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);
			handler.setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);
			handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null);
			handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);
			handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null);
			handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 1, null);
			handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);
			handler.setButtonEnabled(Add_BTN[1], true);
			handler.setButtonEnabled(Del_BTN[1], true);
			handler.setButtonEnabled(Upload_BTN[1], true);
		} else {
			scrnMsg.mdseCstUpdTpCd_H1.setInputProtected(true);
			scrnMsg.mdseCstUpdGrpTxt_H1.setInputProtected(true);
			scrnMsg.mdseCstUpdDescTxt_H1.setInputProtected(true);
			scrnMsg.mdseCstUpdRefTxt_H1.setInputProtected(true);
			scrnMsg.mdseCstUpdRefId_H1.setInputProtected(true);
			scrnMsg.xxFileData_H1.setInputProtected(true);
			scrnMsg.xxChkBox_AL.setInputProtected(true);
			if (scrnMsg.A.getValidCount() > 0) {
				for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
					handler.setButtonEnabled(Line_Item_BTN[1], i, false);
					scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseItemTpNm_A1.setInputProtected(true);
					scrnMsg.A.no(i).xxScrItem54Txt_A1.setInputProtected(true);
					scrnMsg.A.no(i).coaProdCd_A1.setInputProtected(true);
					scrnMsg.A.no(i).rqstTotStdCostAmt_A1.setInputProtected(true);
					scrnMsg.A.no(i).pkgUomCd_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseCstUpdEffFromDt_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseCstUpdStsNm_A1.setInputProtected(true);
					scrnMsg.A.no(i).rqstTotStdCostAmt_A1.setAppFracDigit(2);
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
			handler.setButtonEnabled(Upload_BTN[1], false);
		}
	}
	
	public static Object[] toArray_popup(NMAL0150_PBMsgArray p, int size) {
		Object[] param = new Object[size];
		for (int i = 0; i < size; i++) {
			param[i] = p.no(i).xxPopPrm;
		}
		return param;
	}

	public static Object[] setParamForNMAL6040(NMAL0150BMsg scrnMsg) {
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
	
    // del start 2022/05/30 QC#55970
//    public static void checkInput_Submit(EZDApplicationContext ctx, NMAL0150BMsg scrnMsg) {
//    	
//        // Input Check
//    	// #############
//    	// Header
//    	// #############
//        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdTpCd_H1);
//        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdGrpTxt_H1);
//        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdDescTxt_H1);
//        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdRefTxt_H1);
//        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdRefId_H1);
//        scrnMsg.addCheckItem(scrnMsg.xxFullNm_H1);
//        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdCratPsnCd_H1);
//        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdCratDt_H1);
//        
//    	// #############
//    	// Detail
//    	// #############
//        scrnMsg.addCheckItem(scrnMsg.A);
//        if (scrnMsg.A.getValidCount() != 0) {
//        	List<String> dupChkList = new ArrayList<String>();        	
//            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//                scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
//                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseCd_A1) 
//                		&& !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).rqstTotStdCostAmt_A1) 
//                		&& !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).pkgUomCd_A1) 
//                		&& !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseCstUpdEffFromDt_A1)) {
//                    continue;
//                }
//                scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
//                scrnMsg.addCheckItem(scrnMsg.A.no(i).rqstTotStdCostAmt_A1);
//                scrnMsg.addCheckItem(scrnMsg.A.no(i).pkgUomCd_A1);
//                scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCstUpdEffFromDt_A1);
//                
//                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseCstUpdEffFromDt_A1)) {
//                	validateDate(scrnMsg.A.no(i).mdseCstUpdEffFromDt_A1, true, true, scrnMsg, "DATE");
//                }
//                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseCd_A1)) {
//                	if (dupChkList.contains(scrnMsg.A.no(i).mdseCd_A1.getValue())) {
//                		// NMAM0072E=0, @  is duplicated.
//                		scrnMsg.A.no(i).mdseCd_A1.setErrorInfo(1, "NMAM0072E", new String[]{"Item Number"});
//                        scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
//                	} else {
//                    	dupChkList.add(scrnMsg.A.no(i).mdseCd_A1.getValue());
//                	}
//                }
//            }
//        } else {
//        	//NMAM8190E=0,Detail requires at least one line.  Please enter.
//    		scrnMsg.setMessageInfo("NMAM8190E");
//	    }
//        scrnMsg.putErrorScreen();
//        
//        String msgCode = scrnMsg.getMessageCode();
//        if (msgCode.endsWith("E")) {
//            throw new EZDFieldErrorException();
//        }
//    	
//    }
//    
//    public static void validateDate(EZDBDateItem dt, Boolean required, Boolean isFutureToDtAllowed, EZDBMsg scrn, String name) {
//        String now = ZYPDateUtil.getCurrentSystemTime("yyyyMMdd");
//        if (required && dt.isClear()) {
//        	//ZZM9000E=0,[@] field is mandatory.
//            dt.setErrorInfo(1, "ZZM9000E", new String[] { name });
//        }
//        if (!isFutureToDtAllowed && !dt.isClear() && dt.getValue().compareTo(now) > 0) {
//        	//NMAM8173E=0,[@] cannot be set to a future date
//            dt.setErrorInfo(1, "NMAM8173E", new String[] { name });
//        }
//        scrn.addCheckItem(dt);
//        scrn.putErrorScreen();
//    }
    // del end 2022/05/30 QC#55970
    
	public static void addCheckItem_UPLOAD(NMAL0150BMsg scrnMsg) {
		scrnMsg.addCheckItem(scrnMsg.xxFileData_H1);
		scrnMsg.addCheckItem(scrnMsg.A);
	}

    // add start 2022/05/30 QC#55970
    public static void addCheckItemHeader(EZDApplicationContext ctx, NMAL0150BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdGrpTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdDescTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdRefTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdRefId_H1);
        scrnMsg.addCheckItem(scrnMsg.xxFullNm_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdCratPsnCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdCratDt_H1);
    }

    public static void addCheckItemDetail(EZDApplicationContext ctx, NMAL0150BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseCd_A1) && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).rqstTotStdCostAmt_A1) && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseCstUpdEffFromDt_A1)) {
                continue;
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rqstTotStdCostAmt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCstUpdEffFromDt_A1);
        }
    }
    // add end 2022/05/30 QC#55970
}
