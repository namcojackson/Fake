package business.servlet.NWAL2040;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2040.NWAL2040CMsg;
import business.servlet.NWAL2040.constant.NWAL2040Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/12/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NWAL2040Scrn00_DeleteRow_MAP_TMPL_QLFY extends S21CommonHandler implements NWAL2040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
        if (selectedRows.isEmpty()) {
            //NMAM8054E=0,Please select item(s).
            scrnMsg.setMessageInfo("NMAM8054E");
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
//        NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;
//        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
//        ZYPTableUtil.deleteRows(scrnMsg.A, selectedRows);
        // Button Control.
//        NWAL2040CommonLogic.rowButtonControl(scrnMsg, this, "A");
//        // Table Color
//        NWAL2040CommonLogic.changeTableColorController(scrnMsg);

    }

}
