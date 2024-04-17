/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0390;

import static business.servlet.NSAL0390.constant.NSAL0390Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/24   Fujitsu         T.Yoshida       Create          N/A
 * 2016/03/10   Hitachi         M.Gotou         Update          QC#4423
 * 2016/05/18   Hitachi         A.Kohinata      Update          QC#4212
 * 2016/06/09   Hitachi         T.Kanasaka      Update          QC#9708
 * 2016/08/29   Hitachi         A.Kohinata      Update          QC#11243
 *</pre>
 */
public class NSAL0390Scrn00_Open_CC extends S21CommonHandler {

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

        // mod start 2016/03/14 CSA Defect#4423
        NSAL0390BMsg scrnMsg = (NSAL0390BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue("Open_CC");
        final int eventLine = getButtonSelectNumber();
        scrnMsg.xxNum_EV.setValue(eventLine);

        int index = getButtonSelectNumber();
        NSAL0390_ABMsg abMsg = scrnMsg.A.no(index);
        String machLvlNum = abMsg.dsContrMachLvlNum_A0.getValue();
        String dtlNm = abMsg.xxDtlNm_A0.getValue();

        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.dsContrDtlPk_P.clear();
        scrnMsg.dsCrCardPk.clear();

        Object[] params = new Object[PARAM_LENGTH_NWAL2010];

        // mod start 2016/08/29 CSA Defect#11243
        // START 2016/06/09 T.Kanasaka [QC#9708, MOD]
//        params[0] = abMsg.altPayerCustCd_A0;
//        params[0] = abMsg.dsAcctNum_A0;
        params[0] = abMsg.sellToCustCd_A0;
        // END 2016/06/09 T.Kanasaka [QC#9708, MOD]
        if (MACH_LVL_NUM_1.equals(machLvlNum)) {
            scrnMsg.xxPopPrm_02.setValue(CR_CARD_TRX_TP.CONTRACT_HEADER);
        } else if (MACH_LVL_NUM_2.equals(machLvlNum)) {
            scrnMsg.xxPopPrm_02.setValue(CR_CARD_TRX_TP.CONTRACT_DETAIL);
        } else if (MACH_LVL_NUM_3.equals(machLvlNum) && TRD_TP_CONTR_DTL.equals(dtlNm)) {
            scrnMsg.xxPopPrm_02.setValue(CR_CARD_TRX_TP.CONTRACT_BASE);
        } else {
            scrnMsg.xxPopPrm_02.setValue(CR_CARD_TRX_TP.CPONTRACT_METER);
        }
        // mod end 2016/08/29 CSA Defect#11243
        params[1] = scrnMsg.xxPopPrm_02;
        params[2] = scrnMsg.xxPopPrm_03;
        params[3] = scrnMsg.xxPopPrm_04;
        params[4] = scrnMsg.xxPopPrm_05;
        params[5] = scrnMsg.xxPopPrm_06;
        params[6] = scrnMsg.xxPopPrm_07;
        params[7] = abMsg.dsContrPk_A0;
        params[8] = abMsg.dsContrDtlPk_A0;
        params[9] = abMsg.dsContrBllgMtrPk_A0;
        params[10] = scrnMsg.dsContrDtlPk_P;
        params[11] = scrnMsg.dsContrDtlPk_P;
        params[12] = scrnMsg.dsCrCardPk;

        setArgForSubScreen(params);
        // mod end 2016/03/14 CSA Defect#4423
    }
}
