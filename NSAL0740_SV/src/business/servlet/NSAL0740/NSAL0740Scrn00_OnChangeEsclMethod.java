/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0740;

import static business.servlet.NSAL0740.common.NSAL0740CommonLogic.controlScreenFields;
import static business.servlet.NSAL0740.common.NSAL0740CommonLogic.getLowerLinesForContract;
import static business.servlet.NSAL0740.common.NSAL0740CommonLogic.getLowerLinesForSer;
import static business.servlet.NSAL0740.constant.NSAL0740Constant.LINE_LEVEL_BASE_OVERAGE;
import static business.servlet.NSAL0740.constant.NSAL0740Constant.LINE_LEVEL_CONTRACT;
import static business.servlet.NSAL0740.constant.NSAL0740Constant.LINE_LEVEL_CONTRACT_DETAIL;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.UPLFT_PRC_METH;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/25   Hitachi         M.Gotou         Create          N/A
 * 2016/07/13   Hitachi         A.Kohinata      Update          QC#8608
 * 2017/11/08   Hitachi         M.Kidokoro      Update          QC#21928
 * 2018/11/16   Hitachi         K.Kitachi       Update          QC#28638
 *</pre>
 */
public class NSAL0740Scrn00_OnChangeEsclMethod extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;

        // event line
        final int eventLine = getButtonSelectNumber();
        scrnMsg.xxNum_EV.setValue(eventLine);

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;
        int currentCnt = scrnMsg.xxNum_EV.getValueInt();
        NSAL0740_ABMsg abMsg = scrnMsg.A.no(currentCnt);
        // add start 2016/07/13 CSA Defect#8608
        clearUplftDetail(abMsg);
        // add end 2016/07/13 CSA Defect#8608

        List<NSAL0740_ABMsg> lowerList = null;

        if (DS_CONTR_CATG.FLEET.equals(abMsg.dsContrCatgCd_D1.getValue())) {
            lowerList = getLowerLinesForContract(scrnMsg, currentCnt);
        } else {
            if (LINE_LEVEL_CONTRACT.equals(scrnMsg.A.no(currentCnt).dsContrMachLvlNum_D1.getValue())) {
                lowerList = getLowerLinesForContract(scrnMsg, currentCnt);
            } else if (LINE_LEVEL_CONTRACT_DETAIL.equals(scrnMsg.A.no(currentCnt).dsContrMachLvlNum_D1.getValue())) {
                lowerList = getLowerLinesForSer(scrnMsg, currentCnt);
            }
        }
        // mod start 2016/07/13 CSA Defect#8608
        if (lowerList != null && lowerList.size() > 0) {
            setContrAutoEsclMethod(lowerList, abMsg);
        }

        controlScreenFields(this, scrnMsg);
        if (ZYPCommonFunc.hasValue(abMsg.uplftPrcMethCd_D3)) {
            scrnMsg.setFocusItem(abMsg.uplftBefEndRnwDaysAot_D1);
        } else {
            scrnMsg.setFocusItem(abMsg.uplftPrcMethCd_D3);
        }
        // mod end 2016/07/13 CSA Defect#8608
    }

    private void setContrAutoEsclMethod(List<NSAL0740_ABMsg> list, NSAL0740_ABMsg abMsg) {

        for (int i = 0; i < list.size(); i++) {
            NSAL0740_ABMsg targetABMsg = list.get(i);
            // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
            if (LINE_LEVEL_CONTRACT_DETAIL.equals(targetABMsg.dsContrMachLvlNum_D1.getValue())) {
                if (targetABMsg.xxChkBox_S1.isInputProtected()) {
                    continue;
                }
            } else if (LINE_LEVEL_BASE_OVERAGE.equals(targetABMsg.dsContrMachLvlNum_D1.getValue())) {
                if (targetABMsg.xxChkBox_S2.isInputProtected()) {
                    continue;
                }
            }
            // END 2017/11/08 M.Kidokoro [QC#21928, ADD]
            setValue(targetABMsg.uplftPrcMethCd_D3, abMsg.uplftPrcMethCd_D3);
            // add start 2016/07/13 CSA Defect#8608
            clearUplftDetail(targetABMsg);
            // add end 2016/07/13 CSA Defect#8608
        }
    }

    // add start 2016/07/13 CSA Defect#8608
    private void clearUplftDetail(NSAL0740_ABMsg abMsg) {
        if (!ZYPCommonFunc.hasValue(abMsg.uplftPrcMethCd_D3) || UPLFT_PRC_METH.MODEL_PERCENT.equals(abMsg.uplftPrcMethCd_D3.getValue())) {
            abMsg.uplftBasePrcUpRatio_D1.clear();
            abMsg.uplftMtrPrcUpRatio_D1.clear();
        }
        // START 2018/11/16 K.Kitachi [QC#28638, ADD]
        if (!ZYPCommonFunc.hasValue(abMsg.uplftPrcMethCd_D3)) {
            abMsg.maxPrcUpRatio_D1.clear();
            abMsg.fixTermInMthAot_D1.clear();
            abMsg.uplftFixedDt_D1.clear();
            abMsg.uplftPcyDt_D1.clear();
        }
        // END 2018/11/16 K.Kitachi [QC#28638, ADD]
    }
    // add end 2016/07/13 CSA Defect#8608
}
