/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0170;

import static business.servlet.NFDL0170.constant.NFDL0170Constant.BIZ_ID;
import static business.servlet.NFDL0170.constant.NFDL0170Constant.FUNC_CD_SRCH;
import static business.servlet.NFDL0170.constant.NFDL0170Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0170.NFDL0170CMsg;
import business.servlet.NFDL0170.common.NFDL0170CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Customer Account Search Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/07   Hitachi         T.Tsuchida      Create          QC#19574
 * 2018/10/18   Fujitsu         T.Noguchi       Update          QC#28434
 * 2024/02/23   Hitachi         S.Ikariya       Update          QC#63452
 *</pre>
 */
public class NFDL0170Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0170BMsg scrnMsg = (NFDL0170BMsg) bMsg;

        NFDL0170CommonLogic.addCheckItemHeader(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0170BMsg scrnMsg = (NFDL0170BMsg) bMsg;

        // 2018/10/18 QC#28434 Add Start
        NFDL0170CommonLogic.setToSearchCondition(scrnMsg);
        // 2018/10/18 QC#28434 Add End

        NFDL0170CMsg bizMsg = new NFDL0170CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0170BMsg scrnMsg = (NFDL0170BMsg) bMsg;
        NFDL0170CMsg bizMsg  = (NFDL0170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFDL0170CommonLogic.controlScreen(this, scrnMsg);
        NFDL0170CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A);
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // START 2024/02/23 S.Ikariya [QC#63452, ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.invNum_FR.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.invNum_TO.getValue())
                && scrnMsg.invNum_FR.getValue().equals(scrnMsg.invNum_TO.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.invNum_SR, scrnMsg.invNum_FR);
        }
        // END 2024/02/23 S.Ikariya [QC#63452, ADD]
    }
}
