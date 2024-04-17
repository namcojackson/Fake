/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7190;

import static business.servlet.NMAL7190.constant.NMAL7190Constant.BIZ_ID;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7190.NMAL7190CMsg;
import business.servlet.NMAL7190.common.NMAL7190CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7190Scrn00_DeleteRow
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         W.Honda         Create          N/A
 * 2018/12/18   Fujitsu         T.Noguchi       Update          QC#29661
 *</pre>
 */
public class NMAL7190Scrn00_DeleteRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;

        // 2018/12/18 QC#29661 Del Start
        //List<Integer> delIdxList = ZYPTableUtil.getSelectedRows(scrnMsg.A, NMAL7190Bean.xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);
        //
        //if (delIdxList.size() == 0) {
        //    for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
        //        scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NMAM8054E);
        //        scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
        //    }
        //    scrnMsg.putErrorScreen();
        //}
        // 2018/12/18 QC#29661 Del End

        NMAL7190CommonLogic.clearMandantoryCheckHeader(scrnMsg);
        NMAL7190CommonLogic.addCheckItemForHeader(scrnMsg);
        // Detail
        NMAL7190CommonLogic.clearMandantoryCheckDetail(scrnMsg);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL7190_ABMsg scrnLineMsg = scrnMsg.A.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnLineMsg.xxChkBox_A1.getValue())) {
                continue;
            }

            scrnMsg.addCheckItem(scrnLineMsg.prcGrpTrgtTpCd_A1);
            scrnMsg.addCheckItem(scrnLineMsg.prcGrpTrgtAttrbCd_A1);
            scrnMsg.addCheckItem(scrnLineMsg.prcGrpOpCd_A1);
            scrnMsg.addCheckItem(scrnLineMsg.prcGrpFromTxt_A1);
            scrnMsg.addCheckItem(scrnLineMsg.prcGrpThruTxt_A1);
            scrnMsg.addCheckItem(scrnLineMsg.effFromDt_A1);
            scrnMsg.addCheckItem(scrnLineMsg.effThruDt_A1);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;

        NMAL7190CMsg bizMsg = new NMAL7190CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;
        NMAL7190CMsg bizMsg  = (NMAL7190CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 2018/12/18 QC#29661 Add Start
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
        }
        scrnMsg.putErrorScreen();
        // 2018/12/18 QC#29661 Add End

        NMAL7190CommonLogic.controlScreen(this, scrnMsg);

        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.prcGrpNm);
        }
    }
}
