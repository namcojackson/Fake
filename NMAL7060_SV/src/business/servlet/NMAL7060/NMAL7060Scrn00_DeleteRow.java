/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7060;

import static business.servlet.NMAL7060.constant.NMAL7060Constant.BIZ_ID;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.NLAM0023E;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.ZZM9037E;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7060.NMAL7060CMsg;
import business.servlet.NMAL7060.common.NMAL7060CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7060Scrn00_DeleteRow
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Fujitsu         W.Honda         Create          N/A
 * 2018/08/20   Fujitsu         W.Honda         Update          QC#24307
 *</pre>
 */
public class NMAL7060Scrn00_DeleteRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn.getValue())) {
            scrnMsg.xxRadioBtn.setErrorInfo(1, NLAM0023E);
            scrnMsg.setMessageInfo(ZZM9037E);
            scrnMsg.addCheckItem(scrnMsg.xxRadioBtn);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;
        NMAL7060CMsg bizMsg = new NMAL7060CMsg();

        // QC#24307 2018/08/20 Del start
//        int index = scrnMsg.xxRadioBtn.getValueInt();
//        Integer indexInteger = Integer.valueOf(index);
//        List<Integer> selectedRows = new ArrayList<Integer>();
//        selectedRows.add(indexInteger);
//        ZYPTableUtil.deleteRows(scrnMsg.A, selectedRows);
        // QC#24307 2018/08/20 Del end

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;
        NMAL7060CMsg bizMsg  = (NMAL7060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7060CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
    }
}
