/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0170;

import static business.servlet.NMAL0170.constant.NMAL0170Constant.BIZ_ID;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.NMAM8054E;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.NMAM8098E;
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
 * NMAL0170Scrn00_View
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Fujitsu         T.Arima         Create          N/A
 * 2015/12/14   Fujitsu         T.Arima         Update          QC#1953
 * 2016/01/04   Fujitsu         M.Nakamura      Update          QC#2528
 *</pre>
 */
public class NMAL0170Scrn00_View extends S21CommonHandler {

    @Override
    /**
     * Check Input Event
     * - Not checked record, Show Message : Please select item(s)
     * - Check multiple record, Cannot select multiple.
     */
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A1", CHKBOX_ON_Y);
        if (selectedRows.size() == 0) {
            /* Please select item(s) */
            scrnMsg.setMessageInfo(NMAM8054E);
            throw new EZDFieldErrorException();
        } else if (selectedRows.size() != 1) {
            /* Cannot select multiple */
            scrnMsg.setMessageInfo(NMAM8098E);
            throw new EZDFieldErrorException();
        }

        // DEL START 2016/01/04 QC#2528
//        // ADD START 2015/12/14
//        NMAL0170_ABMsg lineMsg = scrnMsg.A.no(selectedRows.get(0));
//        if (!ZYPCommonFunc.hasValue(lineMsg.mdseItemTpCd_A1)) {
//            lineMsg.mdseItemTpCd_A1.setErrorInfo(1, NMAM0041E, new String[] {TBL_LBL_ITEM_TYPE});
//        }
//        if (!ZYPCommonFunc.hasValue(lineMsg.mdseItemClsTpCd_A1)) {
//            lineMsg.mdseItemClsTpCd_A1.setErrorInfo(1, NMAM0041E, new String[] {TBL_LBL_ITEM_CLASSIFICATION});
//        }
//        // ADD END 2015/12/14
        // DEL END   2016/01/04 QC#2528

        NMAL0170CommonLogic.scrnCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }
    }

    @Override

    /**
     * Set Request Date Event
     * -Insert or Update SUPD_RELN_STAGE
     */
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;
        NMAL0170CMsg bizMsg = new NMAL0170CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    /**
     * Do Process Event
     * - Have Error Message, 
     * - Set Supd Reln Stage Pk.
     */
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;
        NMAL0170CMsg bizMsg = (NMAL0170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL0170CommonLogic.scrnCheckItem(scrnMsg);
        NMAL0170CommonLogic.setNameForMessage(scrnMsg);
        scrnMsg.putErrorScreen();

        /* has error message, show this. */
        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        /* Set Supd Reln Stage PK */
        Object[] params = new Object[1];
        params[0] = scrnMsg.supdRelnStagePk_P;
        setArgForSubScreen(params);
    }
}
