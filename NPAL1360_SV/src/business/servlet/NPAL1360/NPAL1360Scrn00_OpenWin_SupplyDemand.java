/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1360;

import static business.servlet.NPAL1360.constant.NPAL1360Constant.NPAM0049E;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.NPAM1215E;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.NPAM1329E;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.DISPLAY_WH;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1360.common.NPAL1360CommonLogic;

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
public class NPAL1360Scrn00_OpenWin_SupplyDemand extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)) {
            scrnMsg.rtlWhCd.setErrorInfo(1, NPAM1329E, new String[]{ DISPLAY_WH, "" });
            scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
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

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;
        List<Integer> selectIdxList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);

        if (!selectIdxList.isEmpty()) {
            Object[] params = NPAL1360CommonLogic.setParamForSupplyDemand(scrnMsg, selectIdxList.get(0));
            if (params != null) {
                setArgForSubScreen(params);
            }
        }

    }
}
