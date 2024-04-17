/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7240;

import static business.servlet.NMAL7240.constant.NMAL7240Constant.*;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7240.common.NMAL7240CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7240Scrn00_DeleteRow
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7240Scrn00_DeleteRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7240BMsg scrnMsg = (NMAL7240BMsg) bMsg;

        List<Integer> delIdxList = ZYPTableUtil.getSelectedRows(scrnMsg.A, NMAL7240Bean.xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);

        if (delIdxList.size() == 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NMAM8054E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            }
            scrnMsg.putErrorScreen();
        }

        NMAL7240CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL7240CommonLogic.clearMandantoryCheckDetail(scrnMsg);
        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                continue;
            }

            scrnMsg.addCheckItem(scrnMsg.A.no(i).lineBizTpDescTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).frtZoneNum_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).shpgSvcLvlDescTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).fromSclQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).qtyUnitTpCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).shpgFrtRate_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).frtRateCcyCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).frtRatePerNum_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).perUnitTpCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7240BMsg scrnMsg = (NMAL7240BMsg) bMsg;

        List<Integer> delIdx = ZYPTableUtil.getSelectedRows(scrnMsg.A, NMAL7240Bean.xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);
        ZYPTableUtil.deleteRows(scrnMsg.A, delIdx);

        NMAL7240CommonLogic.controlScreen(this, scrnMsg);

        if (0 < scrnMsg.A.getValidCount()) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).lineBizTpDescTxt_A1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.lineBizTpCd);
        }
    }
}
