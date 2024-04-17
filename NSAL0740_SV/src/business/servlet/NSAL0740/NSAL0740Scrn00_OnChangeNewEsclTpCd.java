/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0740;

import static business.servlet.NSAL0740.common.NSAL0740CommonLogic.controlScreenFields;
import static business.servlet.NSAL0740.constant.NSAL0740Constant.BUSINESS_ID;
import static business.servlet.NSAL0740.constant.NSAL0740Constant.FUCNTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0740.NSAL0740CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/14   Hitachi         A.Kohinata      Create          QC#8608
 * 2017/08/22   CITS            T.Kikuhara      Update          QC#18799(L3)
 *</pre>
 */
public class NSAL0740Scrn00_OnChangeNewEsclTpCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;

        NSAL0740CMsg bizMsg = new NSAL0740CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUCNTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;
        NSAL0740CMsg bizMsg = (NSAL0740CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.uplftBaseFlg_H1.getValue()) && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.uplftUsgFlg_H1.getValue())) {
            scrnMsg.uplftPrcMethCd_H3.clear();
            // QC#18799 ADD START
            scrnMsg.befEndUplftDaysAot_H1.clear();
            // QC#18799 ADD END
        }
        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.uplftBaseFlg_H1.getValue())) {
            scrnMsg.uplftBasePrcUpRatio_H1.clear();
        }
        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.uplftUsgFlg_H1.getValue())) {
            scrnMsg.uplftMtrPrcUpRatio_H1.clear();
        }

        controlScreenFields(this, scrnMsg);

        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.uplftBaseFlg_H1.getValue()) && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.uplftUsgFlg_H1.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.contrUplftTpCd_H3);
        } else {
            scrnMsg.setFocusItem(scrnMsg.uplftPrcMethCd_H3);
        }
    }
}
