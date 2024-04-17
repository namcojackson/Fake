/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.NPAM0049E;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.NPAM1239E;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.NPAM1329E;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.DISPLAY_NM_MDSE_CD;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.DISPLAY_NM_SRC_RTL_WH_CD_LINE;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPBL0020.common.NPBL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/07   CITS            S.Katsuma       Create          N/A
 *</pre>
 */
public class NPBL0020Scrn00_MoveTo_SupplyDemand extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        if (scrnMsg.A.getValidCount() == 0) {
            scrnMsg.setMessageInfo(NPAM0049E);
            throw new EZDFieldErrorException();
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())
                    && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseCd_A1)) {
                scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NPAM1329E, new String[]{ DISPLAY_NM_MDSE_CD, "" });
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            }
        }
        scrnMsg.putErrorScreen();

        if (!ZYPCommonFunc.hasValue(scrnMsg.srcRtlWhCd)) {
            scrnMsg.srcRtlWhCd.setErrorInfo(1, NPAM1329E, new String[]{ DISPLAY_NM_SRC_RTL_WH_CD_LINE, "" });
            scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd);
            scrnMsg.putErrorScreen();
        }

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
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        List<Integer> selectIdxList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);

        if (!selectIdxList.isEmpty()) {
            Object[] params = NPBL0020CommonLogic.setParamForSupplyDemand(scrnMsg, selectIdxList.get(0));
            setArgForSubScreen(params);
        }
    }
}
