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
 * 2017/09/12   Fujitsu         T.Ogura         Create          QC#19805
 *</pre>
 */
public class NWAL2040Scrn00_InsertRow_MAP_TMPL_QLFY_RMA extends S21CommonHandler implements NWAL2040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;
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
        
        scrnMsg.E.no(scrnMsg.E.getValidCount()-1).xxChkBox_E1.setInputProtected(false);
        scrnMsg.E.no(scrnMsg.E.getValidCount()-1).defWhMapTmplCd_E1.setInputProtected(false);
        scrnMsg.E.no(scrnMsg.E.getValidCount()-1).mdseItemClsTpCd_E1.setInputProtected(false);
        scrnMsg.E.no(scrnMsg.E.getValidCount()-1).thirdPtyItemFlg_E1.setInputProtected(false);
        scrnMsg.E.no(scrnMsg.E.getValidCount()-1).fromPostCd_E1.setInputProtected(false);
        scrnMsg.E.no(scrnMsg.E.getValidCount()-1).toPostCd_E1.setInputProtected(false);
        scrnMsg.E.no(scrnMsg.E.getValidCount()-1).onHndChkFlg_E1.setInputProtected(false);
        scrnMsg.E.no(scrnMsg.E.getValidCount()-1).thirdPtyDspTpCd_E1.setInputProtected(false);
        scrnMsg.E.no(scrnMsg.E.getValidCount()-1).dropRtrnVndCd_E1.setInputProtected(false);
        scrnMsg.E.no(scrnMsg.E.getValidCount()-1).rmaDefWhCd_E1.setInputProtected(false);
        scrnMsg.setFocusItem(scrnMsg.E.no(scrnMsg.E.getValidCount()-1).defWhMapTmplCd_E1);
        
        // Set detail properties
		NWAL2040CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
	}

}
