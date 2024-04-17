/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_0;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_1;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_2;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.servlet.NWAL1540.constant.NWAL1540Constant;
import business.servlet.NWAL1770.common.NWAL1770CommonLogic;
import business.servlet.NWAL1770.common.NWAL1770CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Fujitsu         T.Yoshida       Create          N/A
 * 2018/07/19   Fujitsu         M.Ishii         Update          QC#26153
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 *</pre>
 */
public class NWAL1770Scrn00_OpenWin_Profitability extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2018/07/19 QC#26153 Mod Start
//        return null;
        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        NWAL1770CMsg bizMsg = new NWAL1770CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // 2018/07/19 QC#26153 Mod End
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        // 2018/07/19 QC#26153 Add Start
        NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        NWAL1770CommonLogic.checkItemLineWarning(scrnMsg);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        NWAL1770CommonLogic.checkItemAllFields(scrnMsg);
        NWAL1770CommonLogicForScrnFields.setEnableByAllRgtnAuth(this, scrnMsg);
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        scrnMsg.setFocusItem(scrnMsg.splyQuoteNum);
        NWAL1770CommonLogicForScrnFields.setProtect(this, scrnMsg);
        // 2018/07/19 QC#26153 Add End

        Object[] params = new Object[IDX_2];
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, NWAL1540Constant.MODE.QUOTE.getValue());
        params[IDX_0] = scrnMsg.xxPopPrm_00;
        params[IDX_1] = scrnMsg.splyQuoteNum;
        setArgForSubScreen(params);
        openMultiModeScreen();

    }
}
