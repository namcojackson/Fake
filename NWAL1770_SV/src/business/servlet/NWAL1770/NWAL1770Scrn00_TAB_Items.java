/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.SCREEN_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.TAB_ITEMS;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.servlet.NWAL1770.common.NWAL1770CommonLogic;
import business.servlet.NWAL1770.common.NWAL1770CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Fujitsu         T.Yoshida       Create          N/A
 * 2017/08/01   Hitachi         E.Kameishi      Update          QC#20295
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 *</pre>
 */
public class NWAL1770Scrn00_TAB_Items extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        String dplyTab = scrnMsg.xxDplyTab.getValue();

        if (TAB_ITEMS.equals(dplyTab)) {
            return;
        }

        NWAL1770CommonLogic.checkItemAllFields(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        NWAL1770CMsg bizMsg = new NWAL1770CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_ITEMS);
        NWAL1770CommonLogic.checkItemLineWarning(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, dplyTab);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        NWAL1770CommonLogic.checkItemAllFields(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_ITEMS);
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.setFocusItem(scrnMsg.B.no(0).mdseCd_B);

        NWAL1770CommonLogic.setTblBackColorForItem(scrnMsg);
        //START 2017/08.01 E.Kameishi [QC#20295,ADD]
        NWAL1770CommonLogicForScrnFields.activeAddButton(this);
        //END 2017/08.01 E.Kameishi [QC#20295,ADD]
        NWAL1770CommonLogicForScrnFields.setProtect(this, scrnMsg);
    }
}
