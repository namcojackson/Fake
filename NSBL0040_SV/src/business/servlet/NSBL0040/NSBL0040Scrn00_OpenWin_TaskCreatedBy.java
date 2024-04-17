/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/01   SRA             Otsuji          Create          N/A
 *</pre>
 */
public class NSBL0040Scrn00_OpenWin_TaskCreatedBy extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0040BMsg scrnMsg = (NSBL0040BMsg) bMsg;

        // Open NMAL6050
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, "AUTH_PSN");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, "USR_NM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, "HR_TTL_NM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, "USR_NM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, "User Pop Up");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, "User ID");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, "User Name");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, "User ID");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, "User Name");
        scrnMsg.xxCondCd_P1.clear();
        scrnMsg.xxCondNm_P1.clear();

        Object[] param = new Object[11];

        int i = 0;
        param[i++] = scrnMsg.xxTblNm_P1;
        param[i++] = scrnMsg.xxTblCdColNm_P1;
        param[i++] = scrnMsg.xxTblNmColNm_P1;
        param[i++] = scrnMsg.xxTblSortColNm_P1;
        param[i++] = scrnMsg.xxScrNm_P1;
        param[i++] = scrnMsg.xxHdrCdLbNm_P1;
        param[i++] = scrnMsg.xxHdrNmLbNm_P1;
        param[i++] = scrnMsg.xxDtlCdLbNm_P1;
        param[i++] = scrnMsg.xxDtlNmLbNm_P1;
        param[i++] = scrnMsg.ezInUserID;
        param[i++] = scrnMsg.xxCondNm_P1;

        setArgForSubScreen(param);
    }
}
