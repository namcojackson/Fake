/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1280;

import static business.servlet.NPAL1280.constant.NPAL1280Constant.NPAM0049E;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.NPAM1215E;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.DEST_WH_CD;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.MDSE_CD_D;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.NPAM1329E;
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
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/07   CITS            S.Katsuma       Create          N/A
 *</pre>
 */
public class NPAL1280Scrn00_OpenWin_SupplyDemand extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())
                    && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseCd_A1)) {
                if(PRCH_REQ_LINE_TP.EXPENSE.equals(scrnMsg.A.no(i).prchReqLineTpCd_A1.getValue()) //
                        || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(scrnMsg.A.no(i).prchReqLineTpCd_A1.getValue())){
                    scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NPAM1633E, new String[]{ "expense order" });
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
                } else {
                    scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NPAM1329E, new String[]{ MDSE_CD_D, "" });
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
                }
            }
        }
        scrnMsg.putErrorScreen();

        if (scrnMsg.A.getValidCount() > 0 && !ZYPCommonFunc.hasValue(scrnMsg.destRtlWhCd)) {
            scrnMsg.destRtlWhCd.setErrorInfo(1, NPAM1329E, new String[]{ DEST_WH_CD, "" });
            scrnMsg.addCheckItem(scrnMsg.destRtlWhCd);
            scrnMsg.putErrorScreen();
        }

        List<Integer> selectIdxList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);
        if (selectIdxList.isEmpty()) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NPAM0049E);
            }
        } else if (selectIdxList.size() > 1) {
            for (int idx : selectIdxList) {
                scrnMsg.A.no(idx).xxChkBox_A1.setErrorInfo(1, NPAM1215E);
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
        List<Integer> selectIdxList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);

        if (!selectIdxList.isEmpty()) {
            Object[] params = NPAL1280CommonLogic.setParamForSupplyDemand(scrnMsg, selectIdxList.get(0));
            if (params != null) {
                setArgForSubScreen(params);
                openMultiModeScreen();
            }
        }
    }
}
