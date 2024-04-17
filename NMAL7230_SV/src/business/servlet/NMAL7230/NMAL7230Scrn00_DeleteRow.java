/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7230;

import static business.servlet.NMAL7230.constant.NMAL7230Constant.NMAM8054E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7230.common.NMAL7230CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7230Scrn00_DeleteRow
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7230Scrn00_DeleteRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;

        List<Integer> delIdxList = ZYPTableUtil.getSelectedRows(scrnMsg.A, NMAL7230Bean.xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);

        if (delIdxList.size() == 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NMAM8054E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            }
            scrnMsg.putErrorScreen();
        }

        // Detail
        NMAL7230CommonLogic.clearMandantoryCheckDetail(scrnMsg);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL7230_ABMsg scrnLineMsg = scrnMsg.A.no(i);

            if (ZYPConstant.FLG_ON_Y.equals(scrnLineMsg.xxChkBox_A1.getValue())) {
                continue;
            }

            scrnMsg.addCheckItem(scrnLineMsg.dsAcctNm_A1);
            scrnMsg.addCheckItem(scrnLineMsg.prcGrpNm_A1);
            scrnMsg.addCheckItem(scrnLineMsg.shipToStCd_A1);
            scrnMsg.addCheckItem(scrnLineMsg.shipToCtryCd_A1);
            scrnMsg.addCheckItem(scrnLineMsg.shipToFromPostCd_A1);
            scrnMsg.addCheckItem(scrnLineMsg.shipToThruPostCd_A1);
            scrnMsg.addCheckItem(scrnLineMsg.effFromDt_A1);
            scrnMsg.addCheckItem(scrnLineMsg.effThruDt_A1);
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;

        List<Integer> delIdx = ZYPTableUtil.getSelectedRows(scrnMsg.A, NMAL7230Bean.xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);
        ZYPTableUtil.deleteRows(scrnMsg.A, delIdx);

        NMAL7230CommonLogic.controlScreen(this, scrnMsg);

        if (0 < scrnMsg.A.getValidCount()) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).lineBizTpDescTxt_A1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.lineBizTpCd);
        }
    }
}
