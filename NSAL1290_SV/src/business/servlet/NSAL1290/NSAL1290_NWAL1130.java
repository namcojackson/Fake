/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1290;

import static business.servlet.NSAL1290.constant.NSAL1290Constant.BTN_NM_OPEN_WIN_BLLG_COUNTER;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.BTN_NM_OPEN_WIN_REG_COUNTER;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.NWAL1130_EVENT_NM_CLOSE;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.NWAL1130_SQL_NM_MTR_IDX_CD;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.NWAL1130_SQL_NM_MTR_LB_CD;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.NWAL1130_SQL_NM_MTR_LB_DESC_TXT;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.NWAL1130_SQL_NM_MTR_LB_NM;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.SCRN_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL1290.common.NSAL1290CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/01   Hitachi         M.Gotou         Create          N/A
 *</pre>
 */
public class NSAL1290_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1290BMsg scrnMsg = (NSAL1290BMsg) bMsg;

        if (!NWAL1130_EVENT_NM_CLOSE.equals(getLastGuard())) {
            if (BTN_NM_OPEN_WIN_REG_COUNTER.equals(scrnMsg.xxScrEventNm.getValue())) {
                return setRequestData_OpenWinRegularCounter(ctx, scrnMsg);

            } else if (BTN_NM_OPEN_WIN_BLLG_COUNTER.equals(scrnMsg.xxScrEventNm.getValue())) {
                return setRequestData_OpenWinBillingCounter(ctx, scrnMsg);
            }
        }

        return null;
    }

    protected EZDCMsg setRequestData_OpenWinRegularCounter(EZDApplicationContext ctx, NSAL1290BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.P.length(); i++) {
            if (NWAL1130_SQL_NM_MTR_LB_CD.equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                scrnMsg.mdlMtrLbCd_H.clear();
            }
            if (NWAL1130_SQL_NM_MTR_LB_NM.equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    setValue(scrnMsg.mtrLbNm_H, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.mtrLbNm_H.clear();
                }
            }
            if (NWAL1130_SQL_NM_MTR_IDX_CD.equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                scrnMsg.mtrIdxCd_H.clear();
            }
        }

        return null;
    }

    protected EZDCMsg setRequestData_OpenWinBillingCounter(EZDApplicationContext ctx, NSAL1290BMsg scrnMsg) {

        int selIndex = getButtonSelectNumber();

        for (int i = 0; i < scrnMsg.P.length(); i++) {
            if (NWAL1130_SQL_NM_MTR_LB_CD.equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                scrnMsg.A.no(selIndex).bllgMtrLbCd_BC.clear();
            }
            if (NWAL1130_SQL_NM_MTR_LB_NM.equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    setValue(scrnMsg.A.no(selIndex).mtrLbNm_BC, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.A.no(selIndex).mtrLbNm_BC.clear();
                }
            } else if (NWAL1130_SQL_NM_MTR_LB_DESC_TXT.equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    setValue(scrnMsg.A.no(selIndex).mtrLbDescTxt_BC, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.A.no(selIndex).mtrLbDescTxt_BC.clear();
                }
            }
        }

        return null;
   }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1290BMsg scrnMsg = (NSAL1290BMsg) bMsg;
        if (BTN_NM_OPEN_WIN_REG_COUNTER.equals(scrnMsg.xxScrEventNm.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.mtrLbNm_H);
            if (!NWAL1130_EVENT_NM_CLOSE.equals(getLastGuard())) {
                ZYPTableUtil.clear(scrnMsg.A);
            }
        } else if (BTN_NM_OPEN_WIN_BLLG_COUNTER.equals(scrnMsg.xxScrEventNm.getValue())) {
            int selIndex = getButtonSelectNumber();
            scrnMsg.setFocusItem(scrnMsg.A.no(selIndex).mtrLbNm_BC);
        }
        NSAL1290CommonLogic.initialControlScreen(this, scrnMsg);
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
