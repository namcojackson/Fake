/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZIL0020;

import java.io.File;
import java.util.Arrays;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.ZZIL0020.ZZIL0020CMsg;
import business.servlet.ZZIL0020.common.ZZIL0020CommonLogic;
import business.servlet.ZZIL0020.constant.ZZIL0020Constant;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZIL0020Scrn00_Upload extends S21CommonHandler implements ZZIL0020Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		ZZIL0020BMsg scrnMsg = (ZZIL0020BMsg) bMsg;
        scrnMsg.xxUpldFileNm.clear();

        // listbox input check
        scrnMsg.addCheckItem( scrnMsg.xxTblNm );
        if ( scrnMsg.xxTblNm.getValue().length() == 0 ) {
            scrnMsg.xxTblNm.setErrorInfo(1, "ZZM9000E", new String[] { "Table Name" });
        }
        
        // upload file input check
        scrnMsg.addCheckItem( scrnMsg.xxFileData );
        if (!scrnMsg.xxFileData.isUploaded()) {
            scrnMsg.xxFileData.setErrorInfo(1, "ZZM9000E", new String[] { "Upload Request" });
        }
        
        scrnMsg.putErrorScreen();

        // file extension check
        //[MOD] FWDEF273 - C.Kim EXCEL Convert
        String[] authorizeExtensions = { "txt", "csv", "xlsx" };
        //[MOD] FWDEF273 - C.Kim EXCEL Convert
        if( !ZYPUploadFileValidator.isAuthorizedExtension( scrnMsg.xxFileData, Arrays.asList( authorizeExtensions ) ) ) {
            scrnMsg.addCheckItem( scrnMsg.xxFileData );
            scrnMsg.putErrorScreen();
        }
        
        // file size check
        if( !ZYPUploadFileValidator.isAllowedFileSize( scrnMsg.xxFileData, 1 ) ) {
            scrnMsg.addCheckItem( scrnMsg.xxFileData );
            scrnMsg.putErrorScreen();
        }

        // upload file name
        String filePath = scrnMsg.xxFileData.getValue().getFileName();
        int lastIndex = filePath.lastIndexOf(File.separator);
        String fineNm = filePath.substring(lastIndex + 1);
        
        int len = scrnMsg.getSchema().getAttr("xxUpldFileNm").getDigit();
        if ( fineNm.length() > len) {
            fineNm = fineNm.substring(0, len);
        }
        scrnMsg.xxUpldFileNm.setValue( fineNm );

        // upload
        scrnMsg.xxFileData.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData, getContextUserInfo().getUserId()), "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData));
    }

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZIL0020BMsg scrnMsg = (ZZIL0020BMsg) bMsg;

		ZZIL0020CMsg bizMsg = new ZZIL0020CMsg();
		bizMsg.setBusinessID("ZZIL0020");
		bizMsg.setFunctionCode("40");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZIL0020BMsg scrnMsg = (ZZIL0020BMsg) bMsg;
		ZZIL0020CMsg bizMsg  = (ZZIL0020CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZZIL0020CommonLogic.setTableColor( scrnMsg );

	}

}
