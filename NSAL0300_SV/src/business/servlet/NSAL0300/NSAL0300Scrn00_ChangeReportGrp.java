/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0300.NSAL0300CMsg;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;
import business.servlet.NSAL0300.constant.NSAL0300Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/27   Hitachi         Y.Takeno        Create          QC#17278
 * 2018/06/18   Hitachi         K.Kim           Update          QC#25195
 *</pre>
 */
public class NSAL0300Scrn00_ChangeReportGrp extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.dsContrRptGrpDescTxt)) {
            scrnMsg.addCheckItem(scrnMsg.dsContrRptGrpDescTxt);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;

        NSAL0300CMsg bizMsg = new NSAL0300CMsg();
        bizMsg.setBusinessID(NSAL0300Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0300Constant.EZD_FUNC_CD_INQ);
        // START 2018/06/18 K.Kim [QC#25195, ADD]
        scrnMsg.dsContrRptGrpNum.clear();
        // END 2018/06/18 K.Kim [QC#25195, ADD]
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg  = (NSAL0300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        this.setResult("no");
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSetFlg.getValue())) {
            this.setResult("yes");
            setArgForSubScreen(NSAL0300CommonLogic.setReportGrpCommonPopUpParam(scrnMsg));
            return;
        }

        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
        scrnMsg.addCheckItem(scrnMsg.dsContrRptGrpDescTxt);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        scrnMsg.setFocusItem(scrnMsg.dsContrRptGrpDescTxt);
    }
}
