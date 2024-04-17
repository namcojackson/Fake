/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0040;


import java.text.SimpleDateFormat;
import java.util.Locale;

import parts.common.*;
import parts.servletcommon.*;
import parts.servletcommon.gui.*;
//import business.blap.ZZZL0040.ZZZL0040CMsg;

import business.blap.ZZZL0040.ZZZL0040CMsg;
import business.servlet.ZZZL0040.common.ZZZL0040CommonLogic;
import business.servlet.ZZZL0040.constant.ZZZL0040Constant;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0040Scrn00_CMN_Download extends S21CommonHandler implements ZZZL0040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZZL0040BMsg scrnMsg = (ZZZL0040BMsg) bMsg;
        ZZZL0040CommonLogic.checkItem(scrnMsg);
        ZZZL0040CommonLogic.correlativeTimeCheck(scrnMsg);
        scrnMsg.putErrorScreen(); 
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
        ZZZL0040BMsg scrnMsg = (ZZZL0040BMsg) bMsg;

        ZZZL0040CMsg bizMsg = new ZZZL0040CMsg();
        bizMsg.setBusinessID("ZZZL0040");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZZL0040BMsg scrnMsg = (ZZZL0040BMsg) bMsg;
		ZZZL0040CMsg bizMsg  = (ZZZL0040CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        if(bizMsg.getMessageKind() == "" || bizMsg.getMessageKind() == null) {
            // Building file name
            StringBuilder fileName = new StringBuilder();
            fileName.append("Report");
            fileName.append("(");
            fileName.append(bizMsg.bizStartTs.getValue().substring(0, 12));
            fileName.append("-");
            fileName.append(bizMsg.bizEndTs.getValue().substring(0, 12));
            fileName.append(")");
            fileName.append(".csv");

            // execute csv download
            String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
            executeDownload(tempFilePath, true, fileName.toString());
        }
	}
}
