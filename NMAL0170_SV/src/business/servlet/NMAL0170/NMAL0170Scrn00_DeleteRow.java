/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0170;

import static business.servlet.NMAL0170.constant.NMAL0170Constant.BIZ_ID;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.EVENT_DELETE_ROW;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.NMAM8054E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.CHKBOX_ON_Y;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0170.NMAL0170CMsg;
import business.servlet.NMAL0170.common.NMAL0170CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0170Scrn00_DeleteRow
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Fujitsu         T.Arima          Create          N/A
 * 2015/12/11   Fujitsu         T.Arima          Create          QC#1882
 * 2017/02/08   Fujitsu         K.Ishizuka       Update          QC#17259
 *</pre>
 */
public class NMAL0170Scrn00_DeleteRow extends S21CommonHandler {

    @Override
    /**
     * Check Input Event
     * - If not check, Show Message : NMAL8054E
     */
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A1", CHKBOX_ON_Y);
        if (selectedRows.size() == 0) {
            scrnMsg.setMessageInfo(NMAM8054E);
            throw new EZDFieldErrorException();
        }

        //NMAL0170CommonLogic.clearRowsBG(scrnMsg, scrnMsg.A, "A"); // DEL S21_NA #17259

    }

    @Override
    /**
     * Set Request Date Event
     * -  do nothing.
     */
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // UPDATE START 2015/12/11 
        // QC1882 Logical Remove
        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;

        NMAL0170CMsg bizMsg = new NMAL0170CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        // MOD start S21_NA #17259
        //bizMsg.setFunctionCode("20");
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        // MOD end S21_NA #17259
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // UPDATE END 2015/12/11
    }

    @Override
    /**
     * Do Process Event
     * - Selected row delete.
     */
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;
        NMAL0170CMsg bizMsg = (NMAL0170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // DELETE START 2015/12/11
        //List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A1", CHKBOX_ON_Y);
        //ZYPTableUtil.deleteRows(scrnMsg.A, selectedRows);
        // DELETE END 2015/12/11
        NMAL0170CommonLogic.setCmnBtnProp(this, scrnMsg, EVENT_DELETE_ROW);

        //NMAL0170CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A"); // DEL S21_NA #17259
        NMAL0170CommonLogic.setScrnLineProtected(scrnMsg);
    }
}
