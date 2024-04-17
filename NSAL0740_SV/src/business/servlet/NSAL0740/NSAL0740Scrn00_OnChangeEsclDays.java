/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0740;

import static business.servlet.NSAL0740.common.NSAL0740CommonLogic.getLowerLinesForContract;
import static business.servlet.NSAL0740.common.NSAL0740CommonLogic.getLowerLinesForSer;
import static business.servlet.NSAL0740.constant.NSAL0740Constant.LINE_LEVEL_CONTRACT;
import static business.servlet.NSAL0740.constant.NSAL0740Constant.LINE_LEVEL_CONTRACT_DETAIL;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.UPLFT_PRC_METH;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/25   Hitachi         M.Gotou         Create          N/A
 * 2016/07/13   Hitachi         A.Kohinata      Update          QC#8608
 * 2018/12/03   Hitachi         T.Tomita        Update          QC#28638
 *</pre>
 */
public class NSAL0740Scrn00_OnChangeEsclDays extends S21CommonHandler {

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
            setContrAutoEsclDays(lowerList, abMsg);
        }

        if (UPLFT_PRC_METH.MODEL_PERCENT.equals(abMsg.uplftPrcMethCd_D3.getValue())) {
            // Mod Start 2018/12/03 QC#28638
            // scrnMsg.setFocusItem(abMsg.uplftBefEndRnwDaysAot_D1);
            scrnMsg.setFocusItem(abMsg.maxPrcUpRatio_D1);
            // Mod End 2018/12/03 QC#28638
        } else if (ZYPConstant.FLG_ON_Y.equals(abMsg.uplftBaseFlg_D1.getValue())) {
            // Mod Start 2018/12/03 QC#28638
            // scrnMsg.setFocusItem(abMsg.uplftBasePrcUpRatio_D1);
            scrnMsg.setFocusItem(abMsg.maxPrcUpRatio_D1);
            // Mod End 2018/12/03 QC#28638
        } else if (ZYPConstant.FLG_ON_Y.equals(abMsg.uplftUsgFlg_D1.getValue())) {
            // Mod Start 2018/12/03 QC#28638
            // scrnMsg.setFocusItem(abMsg.uplftMtrPrcUpRatio_D1);
            scrnMsg.setFocusItem(abMsg.maxPrcUpRatio_D1);
            // Mod End 2018/12/03 QC#28638
        } else {
            // Mod Start 2018/12/03 QC#28638
            // scrnMsg.setFocusItem(abMsg.uplftBefEndRnwDaysAot_D1);
            scrnMsg.setFocusItem(abMsg.maxPrcUpRatio_D1);
            // Mod End 2018/12/03 QC#28638
        }
        // mod end 2016/07/13 CSA Defect#8608
    }

    private void setContrAutoEsclDays(List<NSAL0740_ABMsg> list, NSAL0740_ABMsg abMsg) {

        for (int i = 0; i < list.size(); i++) {
            NSAL0740_ABMsg targetABMsg = list.get(i);
            if (!targetABMsg.uplftBefEndRnwDaysAot_D1.isInputProtected()) {
                setValue(targetABMsg.uplftBefEndRnwDaysAot_D1, abMsg.uplftBefEndRnwDaysAot_D1);
            }
        }
    }
}
