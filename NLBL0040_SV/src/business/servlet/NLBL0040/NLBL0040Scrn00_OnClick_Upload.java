package business.servlet.NLBL0040;


import parts.common.*;
import parts.servletcommon.*;

import business.servlet.NLBL0040.constant.NLBL0040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * When Event[OnClick_Upload] in BusinessID NLBL0040 is generated, 
 * this class processes it. 
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/08/07   Fujitsu         D.Fukaya        Create          N/A
 * </pre>
 */
public class NLBL0040Scrn00_OnClick_Upload extends S21CommonHandler implements NLBL0040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
		
		NLBL0040BMsg scrnMsg = (NLBL0040BMsg) bMsg;
		scrnMsg.xxConfMsgAlrdyDplyFlg_G1.setValue(ZYPConstant.FLG_OFF_N);
		scrnMsg.xxConfMsgAlrdyDplyFlg_G2.setValue(ZYPConstant.FLG_OFF_N);
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 		// There is no processing.
 		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NLBL0040BMsg scrnMsg = (NLBL0040BMsg) bMsg;
		
        ZYPEZDItemValueSetter.setValue(scrnMsg.upldCsvId, Upload_CSV_ID);
        
        Object[] params = new Object[1];
        params[0] = scrnMsg.upldCsvId;

        // set parameter for ZYPL0210
        super.setArgForSubScreen(params);
	}
}
