/*
 * <pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1410;

import static business.servlet.NSAL1410.common.NSAL1410CommonLogic.*;
import static business.servlet.NSAL1410.constant.NSAL1410Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Contract Branch Rep Update
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/06/07   Hitachi         A.Kohinata      Create          QC#60080
 *</pre>
 */
public class NSAL1410Scrn00_OpenWin_Rep extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1410BMsg scrnMsg = (NSAL1410BMsg) bMsg;
        int index = getButtonSelectNumber();
        if (index >= 0) {
            scrnMsg.addCheckItem(scrnMsg.A.no(index).psnCd_A2);
        } else {
            scrnMsg.addCheckItem(scrnMsg.psnCd_H);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1410BMsg scrnMsg = (NSAL1410BMsg) bMsg;

        scrnMsg.psnCd_P.clear();
        scrnMsg.xxPsnNm_P.clear();
        scrnMsg.svcLineBizCd_P.clear();
        scrnMsg.svcContrBrCd_P.clear();
        scrnMsg.svcContrBrDescTxt_P.clear();
        ZYPTableUtil.clear(scrnMsg.R);
        int index = getButtonSelectNumber();
        if (index >= 0) {
            setValue(scrnMsg.psnCd_P, scrnMsg.A.no(index).psnCd_A2);
            setValue(scrnMsg.xxPsnNm_P, scrnMsg.A.no(index).xxPsnNm_A2);
            setValue(scrnMsg.svcContrBrCd_P, scrnMsg.A.no(index).svcContrBrCd_A);
        } else {
            setValue(scrnMsg.psnCd_P, scrnMsg.psnCd_H);
            setValue(scrnMsg.xxPsnNm_P, scrnMsg.xxPsnNm_H);
        }

        // 0 : Lv1 : Suffix (String: Output Item Suffix)
        // 1 : Lv1 : Window Title (String: Popup Title)
        // 2 : Lv1 : Select Table Name (String: Search SQL)
        // 3 : Lv2 : Where List (List: Search condition columns)
        // 4 : Lv2 : Column List (List: Search result columns)
        // 5 : Lv2 : Sort Condition (List: Sort columns)
        // 6 : Lv2 : Output
        Object[] prm = new Object[NWAL1130_PRM_LENGTH];
        int i = 0;
        prm[i++] = "";
        prm[i++] = "Rep  Search Popup";
        prm[i++] = getSelectSQL(scrnMsg, getGlobalCompanyCode(), ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        prm[i++] = getSearchConditionSetting(scrnMsg);
        prm[i++] = getDisplayColumnSetting(scrnMsg);
        prm[i++] = getSortSetting(scrnMsg);
        prm[i++] = scrnMsg.R;
        setArgForSubScreen(prm);
    }
}
