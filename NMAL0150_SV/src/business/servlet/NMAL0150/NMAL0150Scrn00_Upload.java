package business.servlet.NMAL0150;


import java.util.Arrays;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0150.NMAL0150CMsg;
import business.servlet.NMAL0150.common.NMAL0150CommonLogic;
import business.servlet.NMAL0150.constant.NMAL0150Constant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 11/21/2016   Fujitsu         R.Nakamura      Update          QC#16082
 *</pre>
 */
public class NMAL0150Scrn00_Upload extends S21CommonHandler implements NMAL0150Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		NMAL0150BMsg scrnMsg = (NMAL0150BMsg) bMsg;

		if( scrnMsg.xxFileData_H1.isUploaded() ) {
			//ZYEM0096E=0,The file with other than the file extension "csv" "txt" can't be uploaded.
		    // Mod Start 2016/11/21 QC#16082
//			if( !ZYPUploadFileValidator.isAuthorizedExtension( scrnMsg.xxFileData_H1, Arrays.asList( new String[]{ "csv", "txt" } ) ) ) {
		    if( !ZYPUploadFileValidator.isAuthorizedExtension( scrnMsg.xxFileData_H1, Arrays.asList( new String[]{ "txt", "csv", "xlsx", "xls" } ) ) ) {
			// Mod End 2016/11/21 QC#16082
				scrnMsg.xxFileData_H1.setErrorInfo(1, "ZYEM0096E");
			}
		} else {
			//ZZM9000E=0,[@] field is mandatory.
			scrnMsg.xxFileData_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Upload File"});
		}
		scrnMsg.addCheckItem( scrnMsg.xxFileData_H1 );
		scrnMsg.putErrorScreen();

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL0150BMsg scrnMsg = (NMAL0150BMsg) bMsg;

		if( scrnMsg.xxFileData_H1.isUploaded() ) {
			scrnMsg.xxFileData_H1.makeTempFile( 
				null,
				ZYPFileNameUtil.createUniqueFileNm( scrnMsg.xxFileData_H1,
						getUserProfileService().getContextUserInfo().getUserId() ),
				"." + ZYPFileNameUtil.getFileEx( scrnMsg.xxFileData_H1 )
			);
		}

		NMAL0150CMsg bizMsg = new NMAL0150CMsg();
		bizMsg.setBusinessID(BUSINESS_ID);
		bizMsg.setFunctionCode(FUNC_CD_UPD);
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0150BMsg scrnMsg = (NMAL0150BMsg) bMsg;
		NMAL0150CMsg bizMsg  = (NMAL0150CMsg) cMsg;
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		NMAL0150CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
		scrnMsg.putErrorScreen();
		scrnMsg.setFocusItem(scrnMsg.xxFileData_H1);

	}

}
