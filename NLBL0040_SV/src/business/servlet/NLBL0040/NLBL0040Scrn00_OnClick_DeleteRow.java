package business.servlet.NLBL0040;

import java.util.List;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.NLBL0040.NLBL0040CMsg;
import business.servlet.NLBL0040.common.NLBL0040CommonLogic;
import business.servlet.NLBL0040.constant.NLBL0040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * When Event[OnClick_DeleteRow] in BusinessID NLBL0040 is generated, this class processes it. 
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/08/07   Fujitsu         D.Fukaya        Create          N/A
 * </pre>
 */
public class NLBL0040Scrn00_OnClick_DeleteRow extends S21CommonHandler implements NLBL0040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		NLBL0040BMsg scrnMsg = (NLBL0040BMsg) bMsg;
		
		scrnMsg.xxConfMsgAlrdyDplyFlg_G1.setValue(ZYPConstant.FLG_OFF_N);
		scrnMsg.xxConfMsgAlrdyDplyFlg_G2.setValue(ZYPConstant.FLG_OFF_N);
		
        List<Integer> chkYList = ZYPTableUtil.getSelectedRows(scrnMsg.B, HTML_NAME_VALUE_CHKBOX, ZYPConstant.FLG_ON_Y);

        if (chkYList.isEmpty()) {

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                EZDBItem checkBox = scrnMsg.B.no(i).xxChkBox_B1;

                checkBox.setErrorInfo(1, NLBM0017E);
                scrnMsg.addCheckItem(checkBox);
            }
        }
        
        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		NLBL0040BMsg scrnMsg = (NLBL0040BMsg) bMsg;

        NLBL0040CMsg bizMsg = new NLBL0040CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NLBL0040BMsg scrnMsg = (NLBL0040BMsg) bMsg;
        NLBL0040CMsg bizMsg = (NLBL0040CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		
        NLBL0040CommonLogic.cntrlDispScrnItem(this, scrnMsg);
        NLBL0040CommonLogic.setFocusForTrnspLTTbl(scrnMsg);
	}

}
