/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.SCREEN_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.TAB_COMMENTS;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.TAB_ITEMS;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1770.common.NWAL1770CommonLogic;
import business.servlet.NWAL1770.common.NWAL1770CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Fujitsu         T.Yoshida       Create          N/A
 * 2017/08/01   Hitachi         E.Kameishi      Update          QC#20295
 * 2018/05/11   Fujitsu         T.Aoi           Update          QC#22139
 *</pre>
 */
public class NWAL1770Scrn00_TAB_Comments extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        String dplyTab = scrnMsg.xxDplyTab.getValue();

        if (TAB_COMMENTS.equals(dplyTab) || (TAB_ITEMS.equals(dplyTab) && !ZYPCommonFunc.hasValue(scrnMsg.B.no(0).mdseCd_B))) {
            return;
        }

        NWAL1770CommonLogic.checkItemAllFields(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_COMMENTS);
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        // 2018/05/11 QC#22139 Mod Start
        //scrnMsg.setFocusItem(scrnMsg.shpgCmntTxt);
        scrnMsg.setFocusItem(scrnMsg.quotePrintCmntTxt);
        // 2018/05/11 QC#22139 Mod End

        NWAL1770CommonLogicForScrnFields.setProtect(this, scrnMsg);
        //START 2017/08.01 E.Kameishi [QC#20295,ADD]
        NWAL1770CommonLogicForScrnFields.inactiveAddButton(this);
        //END 2017/08.01 E.Kameishi [QC#20295,ADD]
    }
}
