/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7290;

import static business.servlet.NMAL7290.constant.NMAL7290Constant.BIZ_ID;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.CHKBOX_B;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.FUNC_CD_20;
import static business.servlet.NMAL7290.constant.NMAL7290Constant.NMAM8054E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7290.NMAL7290CMsg;
import business.servlet.NMAL7290.common.NMAL7290CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7290Scrn00_Del_PrcRulePrcd
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   Fujitsu         C.Tanaka        Create          N/A
 *</pre>
 */
public class NMAL7290Scrn00_Del_PrcRulePrcd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7290BMsg scrnMsg = (NMAL7290BMsg) bMsg;

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, CHKBOX_B, ZYPConstant.CHKBOX_ON_Y);
        if (checkList.size() == 0) {
            scrnMsg.setMessageInfo(NMAM8054E);
            throw new EZDFieldErrorException();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7290BMsg scrnMsg = (NMAL7290BMsg) bMsg;

        NMAL7290CMsg bizMsg = new NMAL7290CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7290BMsg scrnMsg = (NMAL7290BMsg) bMsg;
        NMAL7290CMsg bizMsg = (NMAL7290CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7290CommonLogic.ctrlCmnBtnProp(this, getUserProfileService(), scrnMsg);
    }
}
