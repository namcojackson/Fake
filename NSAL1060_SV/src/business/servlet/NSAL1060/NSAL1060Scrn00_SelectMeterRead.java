/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1060;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static business.servlet.NSAL1060.constant.NSAL1060Constant.*;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Meter Reading Popup.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   Fujitsu         M.Hara          Create          N/A
 * 11/27/2015   Hitachi         T.Iwamoto       Update          QC#1235
 * 10/27/2016   Hitachi         K.Kishimoto     Update          QC#15511
 * 01/04/2017   Hitachi         K.Ochiai        Update          QC#16584
 * 2019/11/05   Hitachi         K.Kitachi       Update          QC#54164
 * </pre>
 */
public class NSAL1060Scrn00_SelectMeterRead extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1060BMsg scrnMsg = (NSAL1060BMsg) bMsg;

        int selBtnNum = getButtonSelectNumber();
        // START 2019/11/05 K.Kitachi [QC#54164, ADD]
        BigDecimal selDsContrDtlPk = scrnMsg.A.no(selBtnNum).dsContrDtlPk_A1.getValue();
        BigDecimal prmDsContrDtlPk = scrnMsg.dsContrDtlPk.getValue();
        if (hasValue(selDsContrDtlPk) && hasValue(prmDsContrDtlPk) && selDsContrDtlPk.compareTo(prmDsContrDtlPk) != 0) {
            scrnMsg.setMessageInfo(NSAM0752E, new String[] {scrnMsg.A.no(selBtnNum).dsContrNum_A1.getValue() });
            throw new EZDFieldErrorException();
        }
        // END 2019/11/05 K.Kitachi [QC#54164, ADD]

        int mtrReadGrpSq = scrnMsg.A.no(selBtnNum).svcPhysMtrReadGrpSq_A1.getValueInt();
        int compGrpSq = 0;
        int cnt = 0;
        for (int i = selBtnNum; i < scrnMsg.A.getValidCount(); i++) {
            compGrpSq = scrnMsg.A.no(i).svcPhysMtrReadGrpSq_A1.getValueInt();
            if (mtrReadGrpSq != compGrpSq) {
                break;
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(cnt).mtrReadDt_P, scrnMsg.A.no(selBtnNum).mtrReadDt_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(cnt).svcPhysMtrPk_P, scrnMsg.A.no(i).svcPhysMtrPk_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(cnt).mtrLbCd_P, scrnMsg.A.no(i).mtrLbCd_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(cnt).readMtrCnt_P, scrnMsg.A.no(i).readMtrCnt_A1);
            // Add Start 10/127/2016 <QC#15511>
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(cnt).svcPhysMtrReadGrpSq_P, scrnMsg.A.no(i).svcPhysMtrReadGrpSq_A1);
            // Add End   10/127/2016 <QC#15511>
            // START 01/04/2017 K.Ochiai [QC#16584, ADD]
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(cnt).dsMtrReadTpGrpCd_P, scrnMsg.A.no(i).dsMtrReadTpGrpCd_A1);
            // END 01/04/2017 K.Ochiai [QC#16584, ADD]
            cnt++;
        }
        scrnMsg.Q.setValidCount(cnt);

        Object[] params = (Object[]) getArgForSubScreen();
        EZDBMsgArray param2 = (EZDBMsgArray) params[2];
        EZDMsg.copy(scrnMsg.Q, null, param2, null);

    }
}
