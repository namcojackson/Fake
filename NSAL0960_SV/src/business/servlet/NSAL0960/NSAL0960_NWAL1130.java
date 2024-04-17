/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0960;

import static business.servlet.NSAL0960.constant.NSAL0960Constant.BUSINESS_ID;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.FUNCTION_SEARCH;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.PARAMETER_ARGS_DS_CONTR_VLD_CATG_DESC_TXT;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.PARAMETER_ARGS_DS_CONTR_VLD_DESC_TXT;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.PARAMETER_ARGS_DS_CONTR_VLD_LIST_PK;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.PARAMETER_ARGS_DS_CONTR_VLD_NM;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.PARAMETER_ARGS_DS_CONTR_VLD_PK;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.SCRN_ID;
import static business.servlet.NSAL0960.constant.NSAL0960Constant.SELECT_POPUP_SEARCH;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0960.NSAL0960CMsg;
import business.db.DS_CONTR_VLDTMsg;
import business.servlet.NSAL0960.common.NSAL0960CommonLogic;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/21   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/08/05   Hitachi         T.Mizuki        Update          QC#9508
 *</pre>
 */
public class NSAL0960_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0960BMsg scrnMsg = (NSAL0960BMsg) bMsg;

        NSAL0960CMsg bizMsg = new NSAL0960CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0960BMsg scrnMsg = (NSAL0960BMsg) bMsg;
        NSAL0960CMsg bizMsg  = (NSAL0960CMsg) cMsg;
        getArgForSubScreen();

        if (!"CMN_Close".equals(getLastGuard())) {
            if (SELECT_POPUP_SEARCH.equals(scrnMsg.xxPopPrm_SE.getValue())) {
                // mod start 2016/08/05 CSA QC#9508
                String dsContrVldListPk = scrnMsg.X.no(PARAMETER_ARGS_DS_CONTR_VLD_LIST_PK).xxComnScrColValTxt.getValue();
                setValue(bizMsg.dsContrVldListPk_H, new BigDecimal(dsContrVldListPk));
                setValue(bizMsg.dsContrVldListNm_H, scrnMsg.X.no(0).xxComnScrColValTxt.getValue());
                setValue(bizMsg.dsContrVldListDescTxt_H, scrnMsg.X.no(1).xxComnScrColValTxt.getValue());
                if (hasValue(scrnMsg.X.no(2).xxComnScrColValTxt)) {
                    setValue(bizMsg.effFromDt_H, ZYPDateUtil.DateFormatter(scrnMsg.X.no(2).xxComnScrColValTxt.getValue(), "mm/dd/yyyy", "yyyymmdd"));
                }
                if (hasValue(scrnMsg.X.no(3).xxComnScrColValTxt)) {
                    setValue(bizMsg.effToDt_H, ZYPDateUtil.DateFormatter(scrnMsg.X.no(3).xxComnScrColValTxt.getValue(), "mm/dd/yyyy", "yyyymmdd"));
                }
                // mod end 2016/08/05 CSA QC#9508
            } else {
                int selectedRow = getButtonSelectNumber();
                setValue(bizMsg.A.no(selectedRow).glblCmpyCd_A, getGlobalCompanyCode());
                String dsContrVldPk = scrnMsg.X.no(PARAMETER_ARGS_DS_CONTR_VLD_PK).xxComnScrColValTxt.getValue();
                setValue(bizMsg.A.no(selectedRow).dsContrVldPk_A, new BigDecimal(dsContrVldPk));
                setValue(bizMsg.A.no(selectedRow).dsContrVldCatgDescTxt_A, scrnMsg.X.no(PARAMETER_ARGS_DS_CONTR_VLD_CATG_DESC_TXT).xxComnScrColValTxt.getValue());
                setValue(bizMsg.A.no(selectedRow).dsContrVldNm_A, scrnMsg.X.no(PARAMETER_ARGS_DS_CONTR_VLD_NM).xxComnScrColValTxt.getValue());
                setValue(bizMsg.A.no(selectedRow).dsContrVldDescTxt_A, scrnMsg.X.no(PARAMETER_ARGS_DS_CONTR_VLD_DESC_TXT).xxComnScrColValTxt.getValue());
                DS_CONTR_VLDTMsg tMsg = getDsContrVld(new BigDecimal(dsContrVldPk));
                if (tMsg != null) {
                    setValue(bizMsg.A.no(selectedRow).effFromDt_A, tMsg.effFromDt);
                    setValue(bizMsg.A.no(selectedRow).effToDt_A, tMsg.effToDt);
                }
            }
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0960CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.putErrorScreen();
        if (SELECT_POPUP_SEARCH.equals(scrnMsg.xxPopPrm_SE.getValue())) {
            if (scrnMsg.A.getValidCount() > 0) {
                scrnMsg.setFocusItem(scrnMsg.A.no(0).vldSortNum_A);
                setValue(scrnMsg.xxRadioBtn_A, BigDecimal.ZERO);
            } else {
                scrnMsg.setFocusItem(scrnMsg.dsContrVldListNm_H);
            }
        } else {
            if (scrnMsg.A.getValidCount() > 0) {
                int selectedRow = getButtonSelectNumber();
                scrnMsg.setFocusItem(scrnMsg.A.no(selectedRow).vldSortNum_A);
            }
        }
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }

    private DS_CONTR_VLDTMsg getDsContrVld(BigDecimal dsContrVldPk) {
        DS_CONTR_VLDTMsg prmTMsg = new DS_CONTR_VLDTMsg();
        setValue(prmTMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(prmTMsg.dsContrVldPk, dsContrVldPk);
        return (DS_CONTR_VLDTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }
}
