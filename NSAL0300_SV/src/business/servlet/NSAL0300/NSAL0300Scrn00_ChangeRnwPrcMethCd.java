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
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RNW_PRC_METH;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/09   Hitachi         T.Kanasaka      Create          QC#6798
 * 2016/07/28   Hitachi         T.Kanasaka      Update          QC#4806
 *</pre>
 */
public class NSAL0300Scrn00_ChangeRnwPrcMethCd extends S21CommonHandler {

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
        if (!ZYPCommonFunc.hasValue(scrnMsg.rnwPrcMethCd) || RNW_PRC_METH.MODEL_PERCENT.equals(scrnMsg.rnwPrcMethCd.getValue())) {
            scrnMsg.basePrcUpRatio.clear();
            scrnMsg.mtrPrcUpRatio.clear();
        }

        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);

        if (!ZYPCommonFunc.hasValue(scrnMsg.rnwPrcMethCd) || RNW_PRC_METH.MODEL_PERCENT.equals(scrnMsg.rnwPrcMethCd.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.contrUplftTpCd);
        } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.rnwBaseFlg.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.basePrcUpRatio);
        } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.rnwUsgFlg.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.mtrPrcUpRatio);
        } else {
            scrnMsg.setFocusItem(scrnMsg.contrUplftTpCd);
        }
    }
}
