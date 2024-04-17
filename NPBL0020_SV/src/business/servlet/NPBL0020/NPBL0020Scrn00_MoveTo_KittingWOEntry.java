/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_0;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_1;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_2;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_3;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_4;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_5;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_6;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_7;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_8;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_9;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_10;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.NPAM0049E;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.NPAM1239E;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.NPBM0013I;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.PARM_DS_WO_TYPE_1;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Open Kitting WO Entry(NPAL1360)
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/17/2016   CITS            Makoto Okigami  Create          N/A
 * 03/27/2018   CITS            S.Katsuma       Update          Qc#22519
 *</pre>
 */
public class NPBL0020Scrn00_MoveTo_KittingWOEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        int count = 0;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                count++;
            }
        }

        if (count == 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NPAM0049E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            }
            scrnMsg.putErrorScreen();
        } else if (count > 1) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                    scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NPAM1239E);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
                }
            }
            scrnMsg.putErrorScreen();
            return;
        }

        // QC#17439 Add.
        List<Integer> selNumList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
        if (PRCH_REQ_LINE_STS.CLOSED.equals(scrnMsg.A.no(selNumList.get(0)).prchReqLineStsCd_A1.getValue())
                || PRCH_REQ_LINE_STS.CANCELLED.equals(scrnMsg.A.no(selNumList.get(0)).prchReqLineStsCd_A1.getValue())) {
            scrnMsg.setMessageInfo(NPBM0013I);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        int selectCheckIndex = -1;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                selectCheckIndex = i;
                break;
            }
        }

        // START 2018/03/27 S.Katsuma [QC#22519,MOD]
        // QC#9436
//        Object[] params = new Object[IDX_9];
        Object[] params = new Object[IDX_10];
        // END 2018/03/27 S.Katsuma [QC#22519,MOD]
        scrnMsg.P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, PARM_DS_WO_TYPE_1);

        params[IDX_0] = scrnMsg.P.no(0).xxPopPrm;
        params[IDX_1] = scrnMsg.A.no(selectCheckIndex).mdseCd_A1;
        params[IDX_2] = scrnMsg.A.no(selectCheckIndex).prchReqDispQty_A1;
        params[IDX_3] = scrnMsg.A.no(selectCheckIndex).destRtlWhCd_A1;
        params[IDX_4] = scrnMsg.A.no(selectCheckIndex).destRtlSwhCd_A1;
        params[IDX_5] = scrnMsg.prchReqNum;
        params[IDX_6] = scrnMsg.A.no(selectCheckIndex).prchReqLineNum_A1;
        params[IDX_7] = scrnMsg.A.no(selectCheckIndex).prchReqLineSubNum_A1;
        // QC#9436
        params[IDX_8] = scrnMsg.A.no(selectCheckIndex).xxLogDtlTxt_A1;
        // START 2018/03/27 S.Katsuma [QC#22519,ADD]
        params[IDX_9] = scrnMsg.rqstRcvDt;
        // END 2018/03/27 S.Katsuma [QC#22519,ADD]

        setArgForSubScreen(params);

    }
}
