/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.UPLFT_PRC_METH;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/09   Hitachi         T.Kanasaka      Create          QC#6798
 *</pre>
 */
public class NSAL0300Scrn00_ChangeUplftPrcMethCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.uplftPrcMethCd) || UPLFT_PRC_METH.MODEL_PERCENT.equals(scrnMsg.uplftPrcMethCd.getValue())) {
            scrnMsg.uplftBasePrcUpRatio.clear();
            scrnMsg.uplftMtrPrcUpRatio.clear();
        }

        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);

        if (!ZYPCommonFunc.hasValue(scrnMsg.uplftPrcMethCd) || UPLFT_PRC_METH.MODEL_PERCENT.equals(scrnMsg.uplftPrcMethCd.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.serNum);
        } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.uplftBaseFlg.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.uplftBasePrcUpRatio);
        } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.uplftUsgFlg.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.uplftMtrPrcUpRatio);
        } else {
            scrnMsg.setFocusItem(scrnMsg.serNum);
        }
    }
}
