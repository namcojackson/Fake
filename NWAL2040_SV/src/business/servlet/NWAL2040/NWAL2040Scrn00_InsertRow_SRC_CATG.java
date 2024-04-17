package business.servlet.NWAL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2040.NWAL2040CMsg;
import business.servlet.NWAL2040.common.NWAL2040CommonLogic;
import business.servlet.NWAL2040.constant.NWAL2040Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/12/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NWAL2040Scrn00_InsertRow_SRC_CATG extends S21CommonHandler implements NWAL2040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;
//        // 100 records check
//        if (scrnMsg.B.getValidCount() >= scrnMsg.B.length()) {
//        	//NMAM0050E=0,Details cannot be added because the number will exceed [@].
//            scrnMsg.setMessageInfo("NMAM0050E", new String[] { "100" });
//        }
//        // Mandatory check
//        String select = scrnMsg.pkgUomCd_H1.getValue();
//        if (!ZYPCommonFunc.hasValue(scrnMsg.pkgUomCd_H1)) {
//        	//NMAM0041E=0,Please specify [@].
//            scrnMsg.pkgUomCd_H1.setErrorInfo(1, "NMAM0041E", new String[] { "Select Packaging Type" });
//        }
//        // Duplicate check
//        for (int i = 0; i < scrnMsg.K.getValidCount(); i++) {
//            if (select.equals(scrnMsg.K.no(i).pkgUomCd_K1.getValue())) {
//            	//NMAM0010E=0,[@] is already registered.
//                scrnMsg.pkgUomCd_H1.setErrorInfo(1, "NMAM0010E", new String[] { "Select Packaging Type" });
//            }
//        }
//        scrnMsg.addCheckItem(scrnMsg.pkgUomCd_H1);
        scrnMsg.putErrorScreen();
        
        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith(ERROR_CODE_E)) {
            throw new EZDFieldErrorException();
        }

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
        NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;
        NWAL2040CMsg bizMsg = new NWAL2040CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;
        NWAL2040CMsg bizMsg = (NWAL2040CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        scrnMsg.B.no(scrnMsg.B.getValidCount()-1).xxChkBox_B1.setInputProtected(false);
        scrnMsg.B.no(scrnMsg.B.getValidCount()-1).dsOrdCatgCd_B1.setInputProtected(false);
        scrnMsg.B.no(scrnMsg.B.getValidCount()-1).dsOrdTpCd_B1.setInputProtected(false);
        scrnMsg.B.no(scrnMsg.B.getValidCount()-1).firstBizCtxAttrbTxt_B1.setInputProtected(false);
        scrnMsg.B.no(scrnMsg.B.getValidCount()-1).effFromDt_B1.setInputProtected(false);
        scrnMsg.B.no(scrnMsg.B.getValidCount()-1).effThruDt_B1.setInputProtected(false);
        scrnMsg.setFocusItem(scrnMsg.B.no(scrnMsg.B.getValidCount()-1).dsOrdCatgCd_B1);
        
        // Set detail properties
		NWAL2040CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
	}

}
