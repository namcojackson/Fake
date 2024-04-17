/*
 * <pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1410;

import static business.servlet.NSAL1410.constant.NSAL1410Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

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
public class NSAL1410Scrn00_OpenWin_Branch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1410BMsg scrnMsg = (NSAL1410BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.svcContrBrCd_H);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1410BMsg scrnMsg = (NSAL1410BMsg) bMsg;

        scrnMsg.svcRgNm_P.clear();
        scrnMsg.svcLineBizDescTxt_P.clear();
        setValue(scrnMsg.svcContrBrCd_P, scrnMsg.svcContrBrCd_H);
        setValue(scrnMsg.svcContrBrDescTxt_P, scrnMsg.svcContrBrDescTxt_H);
        scrnMsg.xxGenlFldAreaTxt_P.clear();
        scrnMsg.orgFuncRoleTpNm_P.clear();
        scrnMsg.svcRgPk_P.clear();
        setValue(scrnMsg.psnCd_P, scrnMsg.psnCd_H);
        scrnMsg.svcLineBizCd_P.clear();

        Object[] prm = new Object[NSAL0420_PRM_LENGTH];
        int i = 0;
        prm[i++] = scrnMsg.svcRgNm_P;
        prm[i++] = scrnMsg.svcLineBizDescTxt_P;
        prm[i++] = scrnMsg.svcContrBrCd_P;
        prm[i++] = scrnMsg.svcContrBrDescTxt_P;
        prm[i++] = scrnMsg.xxGenlFldAreaTxt_P;
        prm[i++] = scrnMsg.orgFuncRoleTpNm_P;
        prm[i++] = scrnMsg.svcRgPk_P;
        prm[i++] = scrnMsg.psnCd_P;
        prm[i++] = scrnMsg.svcLineBizCd_P;
        setArgForSubScreen(prm);
    }
}
