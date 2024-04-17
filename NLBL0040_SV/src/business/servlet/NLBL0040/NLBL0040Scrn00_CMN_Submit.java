package business.servlet.NLBL0040;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.NLBL0040.NLBL0040CMsg;
import business.servlet.NLBL0040.common.NLBL0040CommonLogic;
import business.servlet.NLBL0040.constant.NLBL0040Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * When Event[CMN_Submit] in BusinessID NLBL0040 is generated, this class processes it. 
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/07/2009   Fujitsu         D.Fukaya        Create          N/A
 * 09/09/2010   CSAI            D.Fukaya        Update          360
 * 
 * </pre>
 */
public class NLBL0040Scrn00_CMN_Submit extends S21CommonHandler implements NLBL0040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		NLBL0040BMsg scrnMsg = (NLBL0040BMsg) bMsg;
		
		scrnMsg.xxConfMsgAlrdyDplyFlg_G1.setValue(ZYPConstant.FLG_OFF_N);
		scrnMsg.xxConfMsgAlrdyDplyFlg_G2.setValue(ZYPConstant.FLG_OFF_N);
		
		scrnMsg.addCheckItem(scrnMsg.effFromDt_L1);
		scrnMsg.addCheckItem(scrnMsg.effThruDt_L1);
		
		NLBL0040CommonLogic.checkAttributeOfLeftTable(scrnMsg);
		NLBL0040CommonLogic.checkAttributeOfRightTable(scrnMsg);
		scrnMsg.putErrorScreen();
		
		if (!ZYPCommonFunc.hasValue(scrnMsg.effFromDt_G1)) {
			
			if (!ZYPDateUtil.getSalesDate().equals(scrnMsg.effFromDt_L1.getValue())) {
				
				scrnMsg.effFromDt_L1.setErrorInfo(1, NLBM0087E, new String[]{ZYPDateUtil.convertFormat(ZYPDateUtil.getSalesDate(),
		    			ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, DATE_SEPARATOR)});
			}
			
			if (!DATE_MAX_VALUE.equals(scrnMsg.effThruDt_L1.getValue())) {
				
				scrnMsg.effThruDt_L1.setErrorInfo(1, NLBM0088E);
			}
			
			scrnMsg.addCheckItem(scrnMsg.effFromDt_L1);
			scrnMsg.addCheckItem(scrnMsg.effThruDt_L1);
		}
		scrnMsg.putErrorScreen();

        // 09/09/2010 D.Fukaya delete start
		//if (ZYPDateUtil.compare(scrnMsg.effFromDt_L1.getValue(), ZYPDateUtil.getSalesDate()) == -1) {
        //	
        //	scrnMsg.effFromDt_L1.setErrorInfo(1, NLBM0082E, new String[]{ZYPDateUtil.convertFormat(ZYPDateUtil.getSalesDate(),
        //			ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, DATE_SEPARATOR)});
        //	scrnMsg.addCheckItem(scrnMsg.effFromDt_L1);
        //}
        //scrnMsg.putErrorScreen();
        //
		//if (ZYPDateUtil.compare(scrnMsg.effThruDt_L1.getValue(), ZYPDateUtil.getSalesDate()) == -1) {
        //	
        //	scrnMsg.effThruDt_L1.setErrorInfo(1, NLBM0083E, new String[]{ZYPDateUtil.convertFormat(ZYPDateUtil.getSalesDate(),
        //			ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, DATE_SEPARATOR)});
        //	scrnMsg.addCheckItem(scrnMsg.effThruDt_L1);
        //}
        //scrnMsg.putErrorScreen();
		// 09/09/2010 D.Fukaya delete end

		if (ZYPDateUtil.compare(scrnMsg.effFromDt_L1.getValue(), scrnMsg.effThruDt_L1.getValue()) == 1) {
			
			scrnMsg.effFromDt_L1.setErrorInfo(1, NLBM0020E);
			scrnMsg.effThruDt_L1.setErrorInfo(1, NLBM0020E);
			
			scrnMsg.addCheckItem(scrnMsg.effFromDt_L1);
			scrnMsg.addCheckItem(scrnMsg.effThruDt_L1);
		}
		scrnMsg.putErrorScreen();		
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		NLBL0040BMsg scrnMsg = (NLBL0040BMsg) bMsg;
 		
        NLBL0040CMsg bizMsg = new NLBL0040CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NLBL0040BMsg scrnMsg = (NLBL0040BMsg) bMsg;
		NLBL0040CMsg bizMsg  = (NLBL0040CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL0040CommonLogic.cntrlDispScrnItem(this, scrnMsg);

        NLBL0040CommonLogic.cntrlDispAreaLeadTimeTable(scrnMsg);
        
		// input value error check
		for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
			
			scrnMsg.addCheckItem(scrnMsg.A.no(i).delyLeadAot_A1);
		}
		
		for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
			
			scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_B1);
			scrnMsg.addCheckItem(scrnMsg.B.no(i).fromZipCd_B1);
			scrnMsg.addCheckItem(scrnMsg.B.no(i).toZipCd_B1);
			scrnMsg.addCheckItem(scrnMsg.B.no(i).trnspLtAot_B1);
		}

		scrnMsg.putErrorScreen();
		
		scrnMsg.setFocusItem(scrnMsg.whCd_H2);
	}

}
