/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.servlet.NWAL1770.common.NWAL1770CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Fujitsu         T.Yoshida       Create          N/A
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 *</pre>
 */
public class NWAL1770Scrn00_OnBlur_ChangeWH extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIndex);

        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).rtlWhNm_B)) {
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).rtlWhNm_B);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        scrnMsg.B.no(selectIndex).rtlWhCd_B.clear();
        scrnMsg.B.no(selectIndex).rtlSwhCd_B.clear();
        scrnMsg.B.no(selectIndex).rtlSwhNm_B.clear();
        if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).rtlWhNm_B)) {
            return null;
        }

        NWAL1770CMsg bizMsg = new NWAL1770CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        int selectIndex = scrnMsg.xxCellIdx.getValueInt();
        if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).rtlWhNm_B)) {
            // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            NWAL1770CommonLogic.checkItemLineWarningOnlyBMsg(scrnMsg);
            // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).rtlSwhNm_B);
            return;
        }

        NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        NWAL1770CommonLogic.checkItemLineWarning(scrnMsg);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]

        scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).rtlWhNm_B);
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).rtlSwhNm_B);
    }
}
