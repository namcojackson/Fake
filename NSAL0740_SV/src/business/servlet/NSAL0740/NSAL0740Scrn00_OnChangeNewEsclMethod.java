/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0740;

import static business.servlet.NSAL0740.common.NSAL0740CommonLogic.controlScreenFields;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.UPLFT_PRC_METH;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/14   Hitachi         A.Kohinata      Create          QC#8608
 *</pre>
 */
public class NSAL0740Scrn00_OnChangeNewEsclMethod extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.uplftPrcMethCd_H3) || UPLFT_PRC_METH.MODEL_PERCENT.equals(scrnMsg.uplftPrcMethCd_H3.getValue())) {
            scrnMsg.uplftBasePrcUpRatio_H1.clear();
            scrnMsg.uplftMtrPrcUpRatio_H1.clear();
        }

        controlScreenFields(this, scrnMsg);

        if (!ZYPCommonFunc.hasValue(scrnMsg.uplftPrcMethCd_H3) || UPLFT_PRC_METH.MODEL_PERCENT.equals(scrnMsg.uplftPrcMethCd_H3.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.svcMemoRsnCd_H3);
        } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.uplftBaseFlg_H1.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.uplftBasePrcUpRatio_H1);
        } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.uplftUsgFlg_H1.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.uplftMtrPrcUpRatio_H1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.svcMemoRsnCd_H3);
        }
    }
}
