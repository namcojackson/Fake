/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7080;

import static business.servlet.NMAL7080.constant.NMAL7080Constant.BIZ_ID;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.NMAM8054E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7080.NMAL7080CMsg;
import business.servlet.NMAL7080.common.NMAL7080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Supply Agreement Plan Set Up
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7080Scrn00_OnClick_MassUpdate extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7080BMsg scrnMsg = (NMAL7080BMsg) bMsg;

        NMAL7080CommonLogic.clearMandantoryCheckHeader(scrnMsg);
        NMAL7080CommonLogic.clearMandantoryCheckDetail(scrnMsg);
        List<Integer> delIdxList = ZYPTableUtil.getSelectedRows(scrnMsg.A, NMAL7080Bean.xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
        if (delIdxList.size() == 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NMAM8054E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            }
        }
        NMAL7080CommonLogic.headerAddCheckItem(scrnMsg);
        NMAL7080CommonLogic.detailAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7080BMsg scrnMsg = (NMAL7080BMsg) bMsg;

        NMAL7080CMsg bizMsg = new NMAL7080CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7080BMsg scrnMsg = (NMAL7080BMsg) bMsg;
        NMAL7080CMsg bizMsg = (NMAL7080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }
}
