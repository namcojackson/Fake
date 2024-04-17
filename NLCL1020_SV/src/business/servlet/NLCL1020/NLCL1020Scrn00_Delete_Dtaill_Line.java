/**
 * <Pre>Copyright(c)2019 Canon USA Inc. All rights reserved.</Pre>
*/
package business.servlet.NLCL1020;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL1020.common.NLCL1020CommonLogic;
import business.servlet.NLCL1020.constant.NLCL1020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/26/2019   Fujitsu         T.Ogura         Create          QC#30124
 *</pre>
 */
public class NLCL1020Scrn00_Delete_Dtaill_Line extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_AD", ZYPConstant.CHKBOX_ON_Y);

        if (selectedRows.isEmpty()) {
            scrnMsg.setMessageInfo(NLCL1020Constant.NLCM0233E);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;

        int beforeCnt = scrnMsg.A.getValidCount();
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_AD", ZYPConstant.CHKBOX_ON_Y);
        int afterCnt = ZYPTableUtil.deleteRows(scrnMsg.A, selectedRows);

        scrnMsg.A.setValidCount(beforeCnt - afterCnt);

        // set Line number
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).invtyOrdLineNum_A1.setValue(String.valueOf(i + 1));
        }

        NLCL1020CommonLogic.initialize(this, scrnMsg);
        NLCL1020CommonLogic.setScrnItemValue_NLCL1020Scrn00_Add_Dtaill_Line(scrnMsg);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
        }
    }

}
