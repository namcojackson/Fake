/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1280;

import static business.servlet.NPAL1280.constant.NPAL1280Constant.NPAM0049E;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.NPAM1215E;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.NPAM1633E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1280.common.NPAL1280CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1280 PO Requisition Entry
 * Function Name : OpenWin_InvInq
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS            K.Ogino          Create          N/A
 * 03/23/2016   CITS            K.Ogino          Update          QC#5964
 * 04/04/2016   CITS            K.Ogino          Update          QC#5964
 * 11/10/2016   CITS            K.Ogino          Update          QC#14890
 *</pre>
 */
public class NPAL1280Scrn00_OpenWin_InvInq extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;

        List<Integer> selectIdxList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);
        if (selectIdxList.isEmpty()) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NPAM0049E);
            }
        } else if (selectIdxList.size() > 1) {
            for (int idx : selectIdxList) {
                scrnMsg.A.no(idx).xxChkBox_A1.setErrorInfo(1, NPAM1215E);
            }
        } else {
            for (int idx : selectIdxList) {
                if ((PRCH_REQ_LINE_TP.EXPENSE.equals(scrnMsg.A.no(idx).prchReqLineTpCd_A1.getValue()) //
                        || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(scrnMsg.A.no(idx).prchReqLineTpCd_A1.getValue()))//
                        && !ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).mdseCd_A1)) {
                    scrnMsg.A.no(idx).xxChkBox_A1.setErrorInfo(1, NPAM1633E, new String[] {"expense order" });
                }
            }
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        // QC#14890
        List<Integer> selectIdxList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);

        if (!selectIdxList.isEmpty()) {
            Object[] params = NPAL1280CommonLogic.setParamForOnhandInvInq(scrnMsg, selectIdxList.get(0));
            if (params != null) {
                setArgForSubScreen(params);
                openMultiModeScreen();
            }
        }
    }
}
