package business.servlet.NMAL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0110.common.NMAL0110CommonLogic;
import business.servlet.NMAL0110.constant.NMAL0110Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/12/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0110Scrn00_InsertRow_RELN_MDSE extends S21CommonHandler implements NMAL0110Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
        if (scrnMsg.D.getValidCount() == scrnMsg.D.length()) {
            //NMAM0050E=0,Details cannot be added because the number will exceed [@].
            scrnMsg.setMessageInfo("NMAM0050E", new String[] { "200" });
            throw new EZDFieldErrorException();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
        
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
        int row = scrnMsg.D.getValidCount();
        int count = scrnMsg.D.getValidCount() + 1;
        scrnMsg.D.setValidCount(count);
        scrnMsg.D.no(row).xxChkBox_D1.clear();
        scrnMsg.D.no(row).mdseItemRelnTpCd_D1.clear();
        scrnMsg.D.no(row).relnMdseCd_D1.clear();
        NMAL0110CommonLogic.changeActivation_Detail(this, getUserProfileService(), scrnMsg);
        // Button Control.
        NMAL0110CommonLogic.rowButtonControl(scrnMsg, this, TABLE_Relation_D);
        // Table Color
        NMAL0110CommonLogic.changeTableColorController(scrnMsg);
        // Focus.
        scrnMsg.setFocusItem(scrnMsg.D.no(row).mdseItemRelnTpCd_D1);
		scrnMsg.xxRsltFlg_H1.clear(); // Manufacture Item Duplication Warning Flag
		scrnMsg.xxRsltFlg_H2.clear(); // Mercury Code and Parts Code does not exists Warning Flag
		scrnMsg.xxRsltFlg_H3.clear(); // "Save as Tmpl" button Disabled Flag

    }

}
