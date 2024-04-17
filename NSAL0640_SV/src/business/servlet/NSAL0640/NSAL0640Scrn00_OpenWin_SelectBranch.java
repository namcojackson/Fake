/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0640;

import static business.servlet.NSAL0640.constant.NSAL0640Constant.BUSINESS_ID;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.NSAL0420_PRM_LENGTH;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.SELECT_BRANCH_DTL;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.SELECT_BRANCH_HEAD;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0640.NSAL0640CMsg;
import business.servlet.NSAL0640.common.NSAL0640CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Update Contract Branch/Representative
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/28   Hitachi         M.Gotou         Update          QC#4702,4703,4915
 * 2016/04/01   Hitachi         M.Gotou         Update          QC#4916
 *</pre>
 */
public class NSAL0640Scrn00_OpenWin_SelectBranch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // mod start 2016/04/01 CSA Defect#4916
        NSAL0640BMsg scrnMsg = (NSAL0640BMsg) bMsg;
        int index = getButtonSelectNumber();
        if (index >= 0) {
            //scrnMsg.addCheckItem(scrnMsg.A.no(index).tocNm_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).xxGenlFldAreaTxt_A2);
        } else {
            //scrnMsg.addCheckItem(scrnMsg.tocCd_H);
            //scrnMsg.addCheckItem(scrnMsg.tocNm_H);
            scrnMsg.addCheckItem(scrnMsg.xxDplyByCdNmCnctTxt_H);
        }
        // mod end 2016/04/01 CSA Defect#4916
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0640BMsg scrnMsg = (NSAL0640BMsg) bMsg;

        NSAL0640CMsg bizMsg = new NSAL0640CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        this.getLastGuard();
        int index = getButtonSelectNumber();
        if (index >= 0) {
            scrnMsg.xxScrEventNm.setValue(SELECT_BRANCH_DTL);
            scrnMsg.xxRowNum.setValue(index);
        } else {
            scrnMsg.xxScrEventNm.setValue(SELECT_BRANCH_HEAD);
            scrnMsg.xxRowNum.setValue(index);
        }
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0640BMsg scrnMsg = (NSAL0640BMsg) bMsg;
        NSAL0640CommonLogic.clearPopupParam(scrnMsg);

        int index = getButtonSelectNumber();

        if (index >= 0) {
            scrnMsg.xxScrEventNm.setValue(SELECT_BRANCH_DTL);
            scrnMsg.svcRgNm_P.clear();
            scrnMsg.svcLineBizDescTxt_P.clear();
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcContrBrCd_P, scrnMsg.A.no(index).svcContrBrCd_A2);
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcContrBrDescTxt_P, scrnMsg.A.no(index).svcContrBrDescTxt_A2);
            scrnMsg.xxGenlFldAreaTxt_P.clear();
            scrnMsg.orgFuncRoleTpNm_P.clear();
            scrnMsg.svcRgPk_P.clear();
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnCd_P, scrnMsg.A.no(index).psnCd_A2);
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
        } else {
            scrnMsg.xxScrEventNm.setValue(SELECT_BRANCH_HEAD);
            scrnMsg.svcRgNm_P.clear();
            scrnMsg.svcLineBizDescTxt_P.clear();
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcContrBrCd_P, scrnMsg.svcContrBrCd_H);
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcContrBrDescTxt_P, scrnMsg.svcContrBrDescTxt_H);
            scrnMsg.xxGenlFldAreaTxt_P.clear();
            scrnMsg.orgFuncRoleTpNm_P.clear();
            scrnMsg.svcRgPk_P.clear();
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnCd_P, scrnMsg.psnCd_H);
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
}
