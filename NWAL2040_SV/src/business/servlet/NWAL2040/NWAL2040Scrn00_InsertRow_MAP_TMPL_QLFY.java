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
 * 2017/09/12   Fujitsu         T.Ogura         Update          QC#19805
 *</pre>
 */
public class NWAL2040Scrn00_InsertRow_MAP_TMPL_QLFY extends S21CommonHandler implements NWAL2040Constant {

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
        
        scrnMsg.A.no(scrnMsg.A.getValidCount()-1).xxChkBox_A1.setInputProtected(false);
        scrnMsg.A.no(scrnMsg.A.getValidCount()-1).defWhMapTmplCd_A1.setInputProtected(false);
        scrnMsg.A.no(scrnMsg.A.getValidCount()-1).mdseItemClsTpCd_A1.setInputProtected(false);
        scrnMsg.A.no(scrnMsg.A.getValidCount()-1).fromPostCd_A1.setInputProtected(false);
        scrnMsg.A.no(scrnMsg.A.getValidCount()-1).toPostCd_A1.setInputProtected(false);
        scrnMsg.A.no(scrnMsg.A.getValidCount()-1).onHndChkFlg_A1.setInputProtected(false);
        scrnMsg.A.no(scrnMsg.A.getValidCount()-1).otbdPrimOnHndChkWhCd_A1.setInputProtected(false);
        scrnMsg.A.no(scrnMsg.A.getValidCount()-1).rtlSwhNm_01.setInputProtected(false);
        scrnMsg.A.no(scrnMsg.A.getValidCount()-1).otbdPrimOnHndLinSrcCd_A1.setInputProtected(false);
        scrnMsg.A.no(scrnMsg.A.getValidCount()-1).otbdScdOnHndChkWhCd_A1.setInputProtected(false);
        scrnMsg.A.no(scrnMsg.A.getValidCount()-1).rtlSwhNm_02.setInputProtected(false);
        scrnMsg.A.no(scrnMsg.A.getValidCount()-1).otbdScdOnHndLineSrcCd_A1.setInputProtected(false);
        scrnMsg.A.no(scrnMsg.A.getValidCount()-1).otbdDefWhCd_A1.setInputProtected(false);
        scrnMsg.A.no(scrnMsg.A.getValidCount()-1).rtlSwhNm_03.setInputProtected(false);
        scrnMsg.A.no(scrnMsg.A.getValidCount()-1).otbdDefLineSrcCd_A1.setInputProtected(false);
        // 2017/09/12 QC#19805 Del Start
//        scrnMsg.A.no(scrnMsg.A.getValidCount()-1).rmaDefWhCd_A1.setInputProtected(false);
//        scrnMsg.A.no(scrnMsg.A.getValidCount()-1).rtlSwhNm_04.setInputProtected(false);
//        scrnMsg.A.no(scrnMsg.A.getValidCount()-1).rmaDefLineSrcCd_A1.setInputProtected(false);
        // 2017/09/12 QC#19805 Del End
        scrnMsg.A.no(scrnMsg.A.getValidCount()-1).thirdPtyItemFlg_A1.setInputProtected(false);
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount()-1).defWhMapTmplCd_A1);
        
        // Set detail properties
		NWAL2040CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
	}

}
