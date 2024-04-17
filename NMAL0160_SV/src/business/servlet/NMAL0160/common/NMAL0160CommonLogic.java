package business.servlet.NMAL0160.common;

import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0160.NMAL0160BMsg;
import business.servlet.NMAL0160.NMAL0160_PBMsgArray;
import business.servlet.NMAL0160.constant.NMAL0160Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 10/05/2015   SRAA            K.Aratani       Update          
 * 11/22/2016   Fujitsu         N.Sugiura       Update          S21_NA#16026
 * 2018/02/13   Hitachi         J.Kim           Update          QC#23765
 *</pre>
 */
public class NMAL0160CommonLogic implements NMAL0160Constant {

	public static void setPage(NMAL0160BMsg scrnMsg, int page) {
		ZYPTableUtil.clear(scrnMsg.A);
		scrnMsg.xxPageShowFromNum_10.setValue(page);
		scrnMsg.xxPageShowToNum_10.clear();
		scrnMsg.xxPageShowOfNum_10.clear();
	}

	public static void changeActivation(S21CommonHandler handler, S21UserProfileService profile, NMAL0160BMsg scrnMsg) {
        // Table Color Setting
        S21TableColorController tblColor = new S21TableColorController(NMAL0160Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A1", scrnMsg.A);
        tblColor.setAlternateRowsBG("A2", scrnMsg.A);
		scrnMsg.xxPageShowTotNum_H1.setInputProtected(true);
		scrnMsg.invtyQty_H1.setInputProtected(true);
		scrnMsg.xxEstScTotCostAmt_H1.setInputProtected(true);

		scrnMsg.xxPageShowTotNum_H1.setAppFracDigit(2);
		scrnMsg.invtyQty_H1.setAppFracDigit(0);
		scrnMsg.xxEstScTotCostAmt_H1.setAppFracDigit(2);

		scrnMsg.mdseCstUpdStsCd_H1.setInputProtected(true);
		handler.setButtonEnabled(APPLY_BTN[1], false);
		
	    String user = profile.getContextUserInfo().getUserId();
	    boolean authEdit = profile.isFunctionGranted(user, NMAL0160Constant.AUTH_EDIT);
		if (authEdit) {
			if (scrnMsg.A.getValidCount() > 0) {
				for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
					
					scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseCstUpdTpNm_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseCstUpdGrpTxt_A1.setInputProtected(true);
					scrnMsg.A.no(i).coaMdseTpCd_A1.setInputProtected(true);
					scrnMsg.A.no(i).coaProdCd_A1.setInputProtected(true);
					scrnMsg.A.no(i).thisMthTotStdCostAmt_A1.setInputProtected(true);
					scrnMsg.A.no(i).rqstTotStdCostAmt_A1.setInputProtected(true);
					scrnMsg.A.no(i).invtyQty_A1.setInputProtected(true);
					scrnMsg.A.no(i).xxEstScTotCostAmt_A1.setInputProtected(true);
					// scrnMsg.A.no(i).mdseCstUpdEffFromDt_A1.setInputProtected(true); // 11/22/2016 S21_NA#16026 Del
					scrnMsg.A.no(i).mdseCstUpdCratDt_A1.setInputProtected(true);
					// START 2018/02/13 J.Kim [QC#23765,MOD]
					// scrnMsg.A.no(i).thisMthTotStdCostAmt_A1.setAppFracDigit(4);
					// scrnMsg.A.no(i).rqstTotStdCostAmt_A1.setAppFracDigit(4);
	                scrnMsg.A.no(i).thisMthTotStdCostAmt_A1.setAppFracDigit(2);
	                scrnMsg.A.no(i).rqstTotStdCostAmt_A1.setAppFracDigit(2);
					// END 2018/02/13 J.Kim [QC#23765,MOD]
					scrnMsg.A.no(i).invtyQty_A1.setAppFracDigit(0);
					// START 2018/02/13 J.Kim [QC#23765,MOD]
					// scrnMsg.A.no(i).xxEstScTotCostAmt_A1.setAppFracDigit(4);
					scrnMsg.A.no(i).xxEstScTotCostAmt_A1.setAppFracDigit(2);
					// END 2018/02/13 J.Kim [QC#23765,MOD]
					
				}
				scrnMsg.mdseCstUpdStsCd_H1.setInputProtected(false);
				handler.setButtonEnabled(APPLY_BTN[1], true);
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
			handler.setButtonEnabled(SEARCH_BTN[1], true);
		} else {
			if (scrnMsg.A.getValidCount() > 0) {
				for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
					scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseCstUpdTpNm_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseCstUpdGrpTxt_A1.setInputProtected(true);
					scrnMsg.A.no(i).coaMdseTpCd_A1.setInputProtected(true);
					scrnMsg.A.no(i).coaProdCd_A1.setInputProtected(true);
					scrnMsg.A.no(i).thisMthTotStdCostAmt_A1.setInputProtected(true);
					scrnMsg.A.no(i).rqstTotStdCostAmt_A1.setInputProtected(true);
					scrnMsg.A.no(i).invtyQty_A1.setInputProtected(true);
					scrnMsg.A.no(i).xxEstScTotCostAmt_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseCstUpdStsCd_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseCstUpdEffFromDt_A1.setInputProtected(true);
					scrnMsg.A.no(i).mdseCstUpdCratDt_A1.setInputProtected(true);
					// START 2018/02/13 J.Kim [QC#23765,MOD]
					// scrnMsg.A.no(i).thisMthTotStdCostAmt_A1.setAppFracDigit(4);
					// scrnMsg.A.no(i).rqstTotStdCostAmt_A1.setAppFracDigit(4);
					// scrnMsg.A.no(i).invtyQty_A1.setAppFracDigit(0);
					// scrnMsg.A.no(i).xxEstScTotCostAmt_A1.setAppFracDigit(4);
                    scrnMsg.A.no(i).thisMthTotStdCostAmt_A1.setAppFracDigit(2);
                    scrnMsg.A.no(i).rqstTotStdCostAmt_A1.setAppFracDigit(2);
                    scrnMsg.A.no(i).invtyQty_A1.setAppFracDigit(0);
                    scrnMsg.A.no(i).xxEstScTotCostAmt_A1.setAppFracDigit(2);
                    // END 2018/02/13 J.Kim [QC#23765,MOD]
				}
			}
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
			handler.setButtonEnabled(SEARCH_BTN[1], false);
		}
	}
	
	public static Object[] toArray_popup(NMAL0160_PBMsgArray p, int size) {
		Object[] param = new Object[size];
		for (int i = 0; i < size; i++) {
			param[i] = p.no(i).xxPopPrm;
		}
		return param;
	}

	public static Object[] setParamForNMAL6040(NMAL0160BMsg scrnMsg) {
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
	
    public static void checkInput_Submit(EZDApplicationContext ctx, NMAL0160BMsg scrnMsg) {
    	
        // Input Check
    	// #############
    	// Detail
    	// #############
        scrnMsg.addCheckItem(scrnMsg.A);
        if (scrnMsg.A.getValidCount() != 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCstUpdStsCd_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCstUpdEffFromDt_A1);
            }
        } else {
        	//NMAM8190E=0,Detail requires at least one line.  Please enter.
    		scrnMsg.setMessageInfo("NMAM8190E");
	    }
        scrnMsg.putErrorScreen();
        
        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith(ERROR_CODE_E)) {
            throw new EZDFieldErrorException();
        }
    	
    }
    
    public static void validateDate(EZDBDateItem dt, Boolean required, Boolean isFutureToDtAllowed, EZDBMsg scrn, String name) {
        String now = ZYPDateUtil.getCurrentSystemTime("yyyyMMdd");
        if (required && dt.isClear()) {
        	//ZZM9000E=0,[@] field is mandatory.
            dt.setErrorInfo(1, "ZZM9000E", new String[] { name });
        }
        if (!isFutureToDtAllowed && !dt.isClear() && dt.getValue().compareTo(now) > 0) {
        	//NMAM8173E=0,[@] cannot be set to a future date
            dt.setErrorInfo(1, "NMAM8173E", new String[] { name });
        }
        scrn.addCheckItem(dt);
        scrn.putErrorScreen();
    }
    
}
