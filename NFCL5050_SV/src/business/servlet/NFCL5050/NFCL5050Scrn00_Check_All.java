/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2009   Fujitsu         FAP)D.Kato      Create          DefID 2024
 * 07/13/2010   Fujitsu         I.Kondo         Update          DefID 7731 No.198
 * 08/06/2010   Fujitsu         I.Kondo         Update          Merge.
 * 2018/07/20   Fujitsu         Y.Matsui        Update          QC#26985
 *</pre>
 */
package business.servlet.NFCL5050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL5050.NFCL5050CMsg;
import business.servlet.NFCL5050.constant.NFCL5050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NFCL5050Scrn00_Check_All.
 */
public class NFCL5050Scrn00_Check_All extends S21CommonHandler implements NFCL5050Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2018/07/20 Y.Matsui [QC#26985,MOD]
        NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;
        NFCL5050CMsg bizMsg = new NFCL5050CMsg();
        bizMsg.setBusinessID("NFCL5050");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // END   2018/07/20 Y.Matsui [QC#26985,MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2018/07/20 Y.Matsui [QC#26985,MOD]
        NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;
        NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;

        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }
        
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // END   2018/07/20 Y.Matsui [QC#26985,MOD]
    }
}
