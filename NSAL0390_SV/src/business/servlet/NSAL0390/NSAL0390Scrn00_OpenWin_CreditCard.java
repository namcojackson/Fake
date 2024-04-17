/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0390;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
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
 * 2016/05/18   Hitachi         A.Kohinata      Create          QC#4212
 * 2016/06/09   Hitachi         T.Kanasaka      Update          QC#9708
 * 2016/08/29   Hitachi         A.Kohinata      Update          QC#11243
 *</pre>
 */
public class NSAL0390Scrn00_OpenWin_CreditCard extends S21CommonHandler {

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

        NSAL0390BMsg scrnMsg = (NSAL0390BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue("OpenWin_CreditCard");

        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.dsContrPk_P.clear();
        scrnMsg.dsContrDtlPk_P.clear();
        scrnMsg.dsCrCardPk.clear();

        if (scrnMsg.A.getValidCount() > 0) {
            NSAL0390_ABMsg abMsg = scrnMsg.A.no(0);
            // mod start 2016/08/29 CSA Defect#11243
            // START 2016/06/09 T.Kanasaka [QC#9708, MOD]
//            setValue(scrnMsg.xxPopPrm_01, abMsg.altPayerCustCd_A0);
//            setValue(scrnMsg.xxPopPrm_01, abMsg.dsAcctNum_A0);
            setValue(scrnMsg.xxPopPrm_01, abMsg.sellToCustCd_A0);
            // END 2016/06/09 T.Kanasaka [QC#9708, MOD]
            // mod end 2016/08/29 CSA Defect#11243
            setValue(scrnMsg.dsContrPk_P, abMsg.dsContrPk_A0);
        }

        Object[] params = new Object[PARAM_LENGTH_NWAL2010];

        params[0] = scrnMsg.xxPopPrm_01;
        scrnMsg.xxPopPrm_02.setValue(CR_CARD_TRX_TP.CONTRACT_HEADER);
        params[1] = scrnMsg.xxPopPrm_02;
        params[2] = scrnMsg.xxPopPrm_03;
        params[3] = scrnMsg.xxPopPrm_04;
        params[4] = scrnMsg.xxPopPrm_05;
        params[5] = scrnMsg.xxPopPrm_06;
        params[6] = scrnMsg.xxPopPrm_07;
        params[7] = scrnMsg.dsContrPk_P;
        params[8] = scrnMsg.dsContrDtlPk_P;
        params[9] = scrnMsg.dsContrDtlPk_P;
        params[10] = scrnMsg.dsContrDtlPk_P;
        params[11] = scrnMsg.dsContrDtlPk_P;
        params[12] = scrnMsg.dsCrCardPk;

        setArgForSubScreen(params);
    }
}
