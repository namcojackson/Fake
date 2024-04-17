/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2040;

import static business.servlet.NLAL2040.constant.NLAL2040Constant.BIZ_APP_ID;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.BTN_OPEN_WIN_MDSE_DETAIL;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.CHK_BOX_A1;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.NLBM1279E;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.SCRN_ID;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2040.NLAL2040CMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NLAL2040 MODELS-CLICKS Inventory Valuation Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/18/2016   CITS            T.Kikuhara      Create          N/A
 * 12/27/2016   CITS            T.Kikuhara      Update          QC#13056-2
 *</pre>
 */
public class NLAL2040Scrn00_DeleteLine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2040BMsg scrnMsg = (NLAL2040BMsg) bMsg;
        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.A, CHK_BOX_A1, ZYPConstant.CHKBOX_ON_Y);
        if (checkList.isEmpty()) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effThruDt_A1)
                    || ZYPDateUtil.compare(scrnMsg.A.no(i).effThruDt_A1.getValue(), ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue())) >= 0) {
                    scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NLBM1279E, new String[] {});
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
                }
            }
            scrnMsg.putErrorScreen();
        }
        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2040BMsg scrnMsg = (NLAL2040BMsg) bMsg;
        NLAL2040CMsg bizMsg = new NLAL2040CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2040BMsg scrnMsg = (NLAL2040BMsg) bMsg;
        NLAL2040CMsg bizMsg  = (NLAL2040CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdlId_A1)) {
                scrnMsg.A.no(i).t_MdlNm_A1.setInputProtected(true);
                setButtonEnabled(BTN_OPEN_WIN_MDSE_DETAIL, i, false);
            } else {
                scrnMsg.A.no(i).t_MdlNm_A1.setInputProtected(false);
                setButtonEnabled(BTN_OPEN_WIN_MDSE_DETAIL, i, true);
            }
        }

        scrnMsg.setFocusItem(scrnMsg.t_MdlNm);

    }
}
