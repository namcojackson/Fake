/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0380;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0380.common.NSAL0380CommonLogic;
import business.servlet.NSAL0380.constant.NSAL0380Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/25   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/15   Hitachi         O.Okuma         Update          QC3029
 * 2016/03/03   Hitachi         K.Yamada        Update          CSA QC#5008
 * 2016/03/04   Hitachi         K.Kasai         Update          QC3024,QC3029
 * 2016/04/15   Hitachi         T.Tomita        Update          QC#4085
 * 2016/07/15   Hitachi         A.Kohinata      Update          QC#8608
 *</pre>
 */
public class NSAL0380Scrn00_OnChangeRnwlMethod extends S21CommonHandler {

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
        setRnwPrcMethCd(lowerList, abMsg);
        // add start 2016/07/15 CSA Defect#8608
        NSAL0380CommonLogic.controlDtl(this, scrnMsg);
        // add end 2016/07/15 CSA Defect#8608
        scrnMsg.setFocusItem(abMsg.befEndRnwDaysAot_A);
    }

    private void setRnwPrcMethCd(List<NSAL0380_ABMsg> list, NSAL0380_ABMsg abMsg) {

        // del start 2016/07/15 CSA Defect#8608
        //NSAL0380CommonLogic.controlFieldForChangeMethodPulldown(abMsg);
        // del end 2016/07/15 CSA Defect#8608

        // add start 2016/03/03 CSA Defect#5008
        if (list == null || list.isEmpty()) {
            return;
        }
        // add end 2016/03/03 CSA Defect#5008
        for (int i = 0; i < list.size(); i++) {
            NSAL0380_ABMsg targetABMsg = list.get(i);
            if (!targetABMsg.rnwPrcMethCd_A.isInputProtected()) {
                setValue(targetABMsg.rnwPrcMethCd_A, abMsg.rnwPrcMethCd_A);
                // del start 2016/07/15 CSA Defect#8608
                //NSAL0380CommonLogic.controlFieldForChangeMethodPulldown(targetABMsg);
                // del end 2016/07/15 CSA Defect#8608
            }
        }
    }
}
