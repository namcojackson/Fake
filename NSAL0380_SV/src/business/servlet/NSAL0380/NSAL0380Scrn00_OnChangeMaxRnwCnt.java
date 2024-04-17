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
 *</pre>
 */
public class NSAL0380Scrn00_OnChangeMaxRnwCnt extends S21CommonHandler {

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
            if (NSAL0380Constant.MACH_LVL_NUM_1.equals(scrnMsg.A.no(currentCnt).xxDplyByCtrlAncrLvlCd_A.getValue())) {
                lowerList = NSAL0380CommonLogic.getLowerLines(scrnMsg, currentCnt);
            } else {
                scrnMsg.setFocusItem(abMsg.maxPrcUpRatio_A);
                return;
            }
        }
        setMaxContrRnwCnt(lowerList, abMsg);
        scrnMsg.setFocusItem(abMsg.maxPrcUpRatio_A);
    }

    private void setMaxContrRnwCnt(List<NSAL0380_ABMsg> list, NSAL0380_ABMsg abMsg) {

        for (int i = 0; i < list.size(); i++) {
            NSAL0380_ABMsg targetABMsg = list.get(i);
            if (!targetABMsg.maxContrRnwCnt_A.isInputProtected()) {
                setValue(targetABMsg.maxContrRnwCnt_A, abMsg.maxContrRnwCnt_A);
            }
        }
    }
}
