package business.servlet.NWAL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2040.NWAL2040CMsg;
import business.servlet.NWAL2040.NWAL2040BMsg;
import business.servlet.NWAL2040.common.NWAL2040CommonLogic;
import business.servlet.NWAL2040.constant.NWAL2040Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 2017/09/12   Fujitsu         T.Ogura         Update          QC#19805
 *</pre>
 */
public class NWAL2040Scrn00_PagePrev extends S21CommonHandler implements NWAL2040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;
        // 2017/09/12 QC#19805 Add Start
        if (TAB_MAP_TMPL_QLFY.equals(scrnMsg.xxDplyTab_H1.getValue())) {
        // 2017/09/12 QC#19805 Add End
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                //Mandatory Check
                scrnMsg.addCheckItem(scrnMsg.A.no(i).defWhMapTmplCd_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseItemClsTpCd_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).fromPostCd_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).toPostCd_A1);
            }
        // 2017/09/12 QC#19805 Add Start
        } else if (TAB_MAP_TMPL_QLFY_RMA.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
                //Mandatory Check
                scrnMsg.addCheckItem(scrnMsg.E.no(i).defWhMapTmplCd_E1);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).mdseItemClsTpCd_E1);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).fromPostCd_E1);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).toPostCd_E1);
            }
        }
        // 2017/09/12 QC#19805 Add End
	    scrnMsg.putErrorScreen();
	    String msgCode = scrnMsg.getMessageCode();
	    if (msgCode.endsWith(ERROR_CODE_E)) {
	        throw new EZDFieldErrorException();
	    }
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;
        //scrnMsg.xxPageShowFromNum_10.setValue(scrnMsg.xxPageShowFromNum_10.getValueInt() - scrnMsg.A.length()-1);
        //scrnMsg.xxPageShowToNum_10.clear();
		NWAL2040CMsg bizMsg = new NWAL2040CMsg();
		bizMsg.setBusinessID(BUSINESS_ID);
		bizMsg.setFunctionCode(FUNC_CD_SRCH);
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
 		return bizMsg;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;
		NWAL2040CMsg bizMsg  = (NWAL2040CMsg) cMsg;
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		NWAL2040CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
//		scrnMsg.setFocusItem(scrnMsg.mdseCstUpdTpCd_H1);
		
	}

}
