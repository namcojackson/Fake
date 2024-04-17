/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0060;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZML0060.ZZML0060CMsg;
import business.servlet.ZZML0060.constant.ZZML0060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * @author q02673
 */
public class ZZML0060Scrn01_CMN_Reset extends S21CommonHandler implements ZZML0060Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZML0060BMsg scrnMsg = (ZZML0060BMsg) bMsg;
        // Edit
        if (scrnMsg.xxScrEventNm.getValue().equals("Edit")) {
            ZZML0060CMsg bizMsg = new ZZML0060CMsg();
            bizMsg.setBusinessID("ZZML0060");
            bizMsg.setFunctionCode("20");

            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;

        }
        // Add
        return null;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        ZZML0060BMsg scrnMsg = (ZZML0060BMsg) bMsg;
        // Edit
        if (scrnMsg.xxScrEventNm.getValue().equals("Edit")) {
            ZZML0060CMsg bizMsg  = (ZZML0060CMsg) cMsg;
            
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
    
            scrnMsg.setFocusItem(scrnMsg.mlGrpNm_01);
        // Add
        } else {
            scrnMsg.glblCmpyCd_01.setValue(scrnMsg.glblCmpyCd_S.getValue());
            scrnMsg.mlGrpId_01.clear();
            scrnMsg.mlGrpNm_01.clear();
            scrnMsg.mlKeyFirstNm_01.clear();
            scrnMsg.mlKeyScdNm_01.clear();
            scrnMsg.mlKeyThirdNm_01.clear();
            scrnMsg.mlGrpDescTxt_01.clear();
            scrnMsg.setFocusItem(scrnMsg.mlGrpId_01);
        }
	}

}
