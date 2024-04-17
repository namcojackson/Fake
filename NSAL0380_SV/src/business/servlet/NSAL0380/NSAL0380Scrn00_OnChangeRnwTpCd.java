/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0380;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0380.NSAL0380CMsg;
import business.servlet.NSAL0380.common.NSAL0380CommonLogic;
import business.servlet.NSAL0380.constant.NSAL0380Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/25   Hitachi         O.Okuma         Create          QC3024
 * 2016/03/04   Hitachi         K.Kasai         Update          QC3024,QC3029
 * 2016/04/15   Hitachi         T.Tomita        Update          QC#4085
 * 2016/07/15   Hitachi         A.Kohinata      Update          QC#8608
 * 2016/07/26   Hitachi         A.Kohinata      Update          QC#12199
 * 2017/09/29   Hitachi         K.Kojima        Update          QC#18378
 *</pre>
 */
public class NSAL0380Scrn00_OnChangeRnwTpCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0380BMsg scrnMsg = (NSAL0380BMsg) bMsg;

        // event line
        final int eventLine = getButtonSelectNumber();
        scrnMsg.xxNum_EV.setValue(eventLine);

        // mod start 2016/07/15 CSA Defect#8608
        NSAL0380CMsg bizMsg = new NSAL0380CMsg();
        bizMsg.setBusinessID(NSAL0380Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NSAL0380Constant.FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // mod end 2016/07/15 CSA Defect#8608
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0380BMsg scrnMsg = (NSAL0380BMsg) bMsg;
        // add start 2016/07/15 CSA Defect#8608
        NSAL0380CMsg bizMsg = (NSAL0380CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // add end 2016/07/15 CSA Defect#8608

        int currentCnt = scrnMsg.xxNum_EV.getValueInt();
        NSAL0380_ABMsg abMsg = scrnMsg.A.no(currentCnt);
        List<NSAL0380_ABMsg> lowerList = null;

        if (DS_CONTR_CATG.AGGREGATE.equals(abMsg.dsContrCatgCd_A.getValue())) {
            if (NSAL0380Constant.MACH_LVL_NUM_1.equals(scrnMsg.A.no(currentCnt).xxDplyByCtrlAncrLvlCd_A.getValue())) {
                lowerList = NSAL0380CommonLogic.getLowerLinesForContract(scrnMsg, currentCnt);
            } else if (NSAL0380Constant.MACH_LVL_NUM_2.equals(scrnMsg.A.no(currentCnt).xxDplyByCtrlAncrLvlCd_A.getValue())) {
                lowerList = NSAL0380CommonLogic.getLowerLinesForAgg(scrnMsg, currentCnt);
            }
        } else if (DS_CONTR_CATG.FLEET.equals(abMsg.dsContrCatgCd_A.getValue())) {
            lowerList = NSAL0380CommonLogic.getLowerLinesForContract(scrnMsg, currentCnt);
        } else {
            if (NSAL0380Constant.MACH_LVL_NUM_1.equals(scrnMsg.A.no(currentCnt).xxDplyByCtrlAncrLvlCd_A.getValue())) {
                lowerList = NSAL0380CommonLogic.getLowerLinesForContract(scrnMsg, currentCnt);
            } else if (NSAL0380Constant.MACH_LVL_NUM_2.equals(scrnMsg.A.no(currentCnt).xxDplyByCtrlAncrLvlCd_A.getValue())) {
                lowerList = NSAL0380CommonLogic.getLowerLinesForSer(scrnMsg, currentCnt);
            }
        }
        setContrAutoRnwTpCd(lowerList, abMsg);
        // mod start 2016/07/15 CSA Defect#8608
        NSAL0380CommonLogic.controlDtl(this, scrnMsg);
        if (!ZYPConstant.FLG_ON_Y.equals(abMsg.rnwBaseFlg_A.getValue()) && !ZYPConstant.FLG_ON_Y.equals(abMsg.rnwUsgFlg_A.getValue())) {
            scrnMsg.setFocusItem(abMsg.contrAutoRnwTpCd_A);
        } else {
            scrnMsg.setFocusItem(abMsg.rnwPrcMethCd_A);
        }
        // mod end 2016/07/15 CSA Defect#8608
    }

    private void setContrAutoRnwTpCd(List<NSAL0380_ABMsg> list, NSAL0380_ABMsg abMsg) {

        // add start 2016/07/26 CSA Defect#12199
        if (list == null || list.isEmpty()) {
            return;
        }
        // add end 2016/07/26 CSA Defect#12199

        for (int i = 0; i < list.size(); i++) {
            NSAL0380_ABMsg targetABMsg = list.get(i);
            setValue(targetABMsg.contrAutoRnwTpCd_A, abMsg.contrAutoRnwTpCd_A);
            // add start 2016/07/15 CSA Defect#8608
            setValue(targetABMsg.rnwBaseFlg_A, abMsg.rnwBaseFlg_A);
            setValue(targetABMsg.rnwUsgFlg_A, abMsg.rnwUsgFlg_A);
            // add end 2016/07/15 CSA Defect#8608
            // START 2017/09/29 K.Kojima [QC#18378,ADD]
            setValue(targetABMsg.autoRnwFlg_A, abMsg.autoRnwFlg_A);
            // END 2017/09/29 K.Kojima [QC#18378,ADD]
        }
    }
}
