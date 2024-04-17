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
 * 2013/09/05   SRA             E.Inada         Update          QC#2135
 *</pre>
 */
public class NSBL0040Scrn00_OpenWin_Tech extends S21CommonHandler {

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
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, "TECH_MSTR");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, "TECH_TOC_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, "TECH_NM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, "TECH_TOC_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, "Technician Pop Up");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, "Technician Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, "Technician Name");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, "Technician Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, "Technician Name");
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

        int index = getButtonSelectNumber();
        if (index >= 0) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxCondCd_P1, scrnMsg.A.no(index).techCd_A1);
            param[i++] = scrnMsg.xxCondCd_P1;
        } else {
            param[i++] = scrnMsg.techCd;
        }

        param[i++] = scrnMsg.xxCondNm_P1;

        setArgForSubScreen(param);
    }
}
