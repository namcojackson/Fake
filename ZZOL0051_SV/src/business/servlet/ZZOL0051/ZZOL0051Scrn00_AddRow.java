/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0051;

import parts.common.*;
import parts.servletcommon.*;

//import business.blap.ZZOL0051.ZZOL0051CMsg;
import business.servlet.ZZOL0051.common.ZZOL0051CommonLogic;
import business.servlet.ZZOL0051.constant.ZZOL0051Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0051Scrn00_AddRow extends S21CommonHandler implements ZZOL0051Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZOL0051BMsg scrnMsg = (ZZOL0051BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//ZZOL0051BMsg scrnMsg = (ZZOL0051BMsg) bMsg;

		//ZZOL0051CMsg bizMsg = new ZZOL0051CMsg();
		//bizMsg.setBusinessID("ZZOL0051");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0051BMsg scrnMsg = (ZZOL0051BMsg) bMsg;

        EZDBMsgArray array = null;
        String curttab = scrnMsg.xxDplyTab.getValue();

        if ( TAB_BIZAPP.equals( curttab ) ) {
            array = scrnMsg.B;
        } else {
            array = scrnMsg.P;
        }
        
        int row = array.getValidCount();
        if (row >= array.length()) {
            scrnMsg.setMessageInfo("ZZZM9011E", new String[] { Integer.toString(array.length())  });
            return;
        }

        array.get(row).clear();
        if ( TAB_BIZAPP.equals( curttab ) ) {
            ((ZZOL0051_BBMsgArray) array).no(row).xxNum_B.setValue(row + 1);
            scrnMsg.setFocusItem( ((ZZOL0051_BBMsgArray) array).no(row).upldCsvRstBizAppId_B );
        } else {
            ((ZZOL0051_PBMsgArray) array).no(row).xxNum_P.setValue(row + 1);
            ((ZZOL0051_PBMsgArray) array).no(row).upldCsvRstProcNm_P.setInputProtected( true );
        }
        array.setValidCount(row + 1);
        
        setButtonEnabled(CMN_BTN2[0], array.getValidCount() > 0);
        setButtonEnabled(CMN_BTN7[0], array.getValidCount() > 0);
        ZZOL0051CommonLogic.setTableColor(scrnMsg);
    }

}
