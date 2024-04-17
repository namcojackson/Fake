/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0380;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0380.common.NSAL0380CommonLogic;
import business.servlet.NSAL0380.constant.NSAL0380Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RNW_PRC_METH;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/25   Hitachi         O.Okuma         Create          QC3024
 * 2016/03/04   Hitachi         K.Kasai         Update          QC3024,QC3029
 * 2016/04/15   Hitachi         T.Tomita        Update          QC#4085
 * 2016/07/15   Hitachi         A.Kohinata      Update          QC#8608
 * 2018/12/20   Hitachi         K.Kim           Update          QC#29636
 *</pre>
 */
public class NSAL0380Scrn00_OnChangeMaxPrcUpRatio extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0380BMsg scrnMsg = (NSAL0380BMsg) bMsg;

        // event line
        final int eventLine = getButtonSelectNumber();
        scrnMsg.xxNum_EV.setValue(eventLine);

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0380BMsg scrnMsg = (NSAL0380BMsg) bMsg;
        int currentCnt = scrnMsg.xxNum_EV.getValueInt();
        NSAL0380_ABMsg abMsg = scrnMsg.A.no(currentCnt);
        List<NSAL0380_ABMsg> lowerList = null;

        if (DS_CONTR_CATG.AGGREGATE.equals(abMsg.dsContrCatgCd_A.getValue())) {
            if (NSAL0380Constant.MACH_LVL_NUM_1.equals(scrnMsg.A.no(currentCnt).xxDplyByCtrlAncrLvlCd_A.getValue())) {
                lowerList = NSAL0380CommonLogic.getLowerLines(scrnMsg, currentCnt);
            } else if (NSAL0380Constant.MACH_LVL_NUM_2.equals(scrnMsg.A.no(currentCnt).xxDplyByCtrlAncrLvlCd_A.getValue())) {
                lowerList = NSAL0380CommonLogic.getLowerLinesForAgg(scrnMsg, currentCnt);
            }
        } else if (DS_CONTR_CATG.FLEET.equals(abMsg.dsContrCatgCd_A.getValue())) {
            lowerList = NSAL0380CommonLogic.getLowerLinesForContract(scrnMsg, currentCnt);
        } else {
            if (NSAL0380Constant.MACH_LVL_NUM_1.equals(scrnMsg.A.no(currentCnt).xxDplyByCtrlAncrLvlCd_A.getValue())
                    // START 2018/12/20 [QC#29636, ADD]
                    || NSAL0380Constant.MACH_LVL_NUM_2.equals(scrnMsg.A.no(currentCnt).xxDplyByCtrlAncrLvlCd_A.getValue())) {
                    // END 2018/12/20 [QC#29636, ADD]
                lowerList = NSAL0380CommonLogic.getLowerLines(scrnMsg, currentCnt);
            } else {
                // mod start 2016/07/15 CSA Defect#8608
                if (!ZYPCommonFunc.hasValue(abMsg.rnwPrcMethCd_A) || RNW_PRC_METH.MODEL_PERCENT.equals(abMsg.rnwPrcMethCd_A.getValue())) {
                    scrnMsg.setFocusItem(abMsg.maxPrcUpRatio_A);
                } else if (ZYPConstant.FLG_ON_Y.equals(abMsg.rnwBaseFlg_A.getValue())) {
                    scrnMsg.setFocusItem(abMsg.basePrcUpRatio_A);
                } else if (ZYPConstant.FLG_ON_Y.equals(abMsg.rnwUsgFlg_A.getValue())) {
                    scrnMsg.setFocusItem(abMsg.mtrPrcUpRatio_A);
                } else {
                    scrnMsg.setFocusItem(abMsg.maxPrcUpRatio_A);
                }
                // mod end 2016/07/15 CSA Defect#8608
                return;
            }
        }
        setMaxPrcUpRatio(lowerList, abMsg);
        // mod start 2016/07/15 CSA Defect#8608
        if (!ZYPCommonFunc.hasValue(abMsg.rnwPrcMethCd_A) || RNW_PRC_METH.MODEL_PERCENT.equals(abMsg.rnwPrcMethCd_A.getValue())) {
            scrnMsg.setFocusItem(abMsg.maxPrcUpRatio_A);
        } else if (ZYPConstant.FLG_ON_Y.equals(abMsg.rnwBaseFlg_A.getValue())) {
            scrnMsg.setFocusItem(abMsg.basePrcUpRatio_A);
        } else if (ZYPConstant.FLG_ON_Y.equals(abMsg.rnwUsgFlg_A.getValue())) {
            scrnMsg.setFocusItem(abMsg.mtrPrcUpRatio_A);
        } else {
            scrnMsg.setFocusItem(abMsg.maxPrcUpRatio_A);
        }
        // mod end 2016/07/15 CSA Defect#8608
    }

    private void setMaxPrcUpRatio(List<NSAL0380_ABMsg> list, NSAL0380_ABMsg abMsg) {

        for (int i = 0; i < list.size(); i++) {
            NSAL0380_ABMsg targetABMsg = list.get(i);
            if (!targetABMsg.maxPrcUpRatio_A.isInputProtected()) {
                setValue(targetABMsg.maxPrcUpRatio_A, abMsg.maxPrcUpRatio_A);
            }
        }
    }
}
