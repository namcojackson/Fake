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
public class NMAL0110Scrn00_InsertRow_CUST_REF extends S21CommonHandler implements NMAL0110Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
        if (scrnMsg.E.getValidCount() == scrnMsg.E.length()) {
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

        int row = scrnMsg.E.getValidCount();
        int count = scrnMsg.E.getValidCount() + 1;
        scrnMsg.E.setValidCount(count);
        scrnMsg.E.no(row).xxChkBox_E1.clear();
        scrnMsg.E.no(row).dsAcctNum_E1.clear();
        scrnMsg.E.no(row).dsAcctNm_E1.clear();
        scrnMsg.E.no(row).custMdseCd_E1.clear();
        scrnMsg.E.no(row).custMdseNm_E1.clear();
        scrnMsg.E.no(row).effFromDt_E1.clear();
        scrnMsg.E.no(row).xxChkBox_EN.clear();
        NMAL0110CommonLogic.changeActivation_Detail(this, getUserProfileService(), scrnMsg);
        // Button Control.
        NMAL0110CommonLogic.rowButtonControl(scrnMsg, this, TABLE_CustRef_E);
        // Table Color
        NMAL0110CommonLogic.changeTableColorController(scrnMsg);

        // Focus.
        scrnMsg.setFocusItem(scrnMsg.E.no(row).dsAcctNum_E1);
		scrnMsg.xxRsltFlg_H1.clear(); // Manufacture Item Duplication Warning Flag
		scrnMsg.xxRsltFlg_H2.clear(); // Mercury Code and Parts Code does not exists Warning Flag
		scrnMsg.xxRsltFlg_H3.clear(); // "Save as Tmpl" button Disabled Flag

    }

}
