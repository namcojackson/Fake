/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import static business.servlet.NSAL0300.constant.NSAL0300Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0300.NSAL0300CMsg;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/16   Hitachi         T.Kanasaka      Create          N/A
 * 2015/11/20   Hitachi         T.Kanasaka      Update          QC976
 * 2015/11/25   Hitachi         A.Kohinata      Update          QC981
 * 2015/11/25   Hitachi         A.Kohinata      Update          QC977
 * 2015/11/27   Hitachi         A.Kohinata      Update          QC1199
 * 2015/11/30   Hitachi         A.Kohinata      Update          QC614
 * 2016/02/24   Hitachi         O.Okuma         Update          QC3029
 * 2016/03/03   Hitachi         K.Kasai         Update          QC3018
 * 2016/03/16   Hitachi         T.Kanasaka      Update          QC5546
 * 2016/05/18   Hitachi         A.Kohinata      Update          QC4212
 *</pre>
 */
public class NSAL0300Scrn00_Go extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CommonLogic.addCheckItem(scrnMsg);
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsContrActCd)) {
            scrnMsg.dsContrActCd.setErrorInfo(1, ZZM9000E, new String[] {"Go Action" });
        }
        scrnMsg.addCheckItem(scrnMsg.dsContrActCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg = new NSAL0300CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg = (NSAL0300CMsg) cMsg;
        NSAL0300CommonLogic.clearPopupParam(scrnMsg);

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
        NSAL0300CommonLogic.addCheckItem(scrnMsg);

        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        String toBizAppId = scrnMsg.bizAppId.getValue();
        this.setResult(toBizAppId);

        if (BIZ_APP_ID_NSAL0380.equals(toBizAppId)) {
            // START 2016/02/24 O.Okuma [QC3029, MOD]
//            Object[] prm = new Object[11];
//            prm[0] = scrnMsg.dsContrPk;
//            prm[1] = scrnMsg.xxPopPrm_01;
//            prm[2] = scrnMsg.xxPopPrm_02;
//            prm[3] = scrnMsg.befEndRnwDaysAot_03;
//            prm[4] = scrnMsg.basePrcUpRatio_04;
//            prm[5] = scrnMsg.mtrPrcUpRatio_05;
//            prm[6] = scrnMsg.xxPopPrm_06;
//            prm[7] = scrnMsg.xxPopPrm_07;
//            prm[8] = scrnMsg.uplftBasePrcUpRatio_08;
//            prm[9] = scrnMsg.uplftMtrPrcUpRatio_09;
//            prm[10] = scrnMsg.dsContrCatgCd;
            Object[] prm = new Object[1];
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).dsContrPk_P1, scrnMsg.dsContrPk);
            scrnMsg.P.setValidCount(1);
            prm[0] = scrnMsg.P;
            // END 2016/02/24 O.Okuma [QC3029, MOD]
            setArgForSubScreen(prm);
        // START 2016/03/16 T.Kanasaka [QC5546, MOD]
        // START 2016/05/19 [QC4212, MOD]
        } else if (BIZ_APP_ID_NSAL0460.equals(toBizAppId) || BIZ_APP_ID_NSAL0600.equals(toBizAppId) || BIZ_APP_ID_NSAL0610.equals(toBizAppId)) {
        // END 2016/05/19 [QC4212, MOD]
            Object[] prm = new Object[1];
            prm[0] = scrnMsg.dsContrPk;
            setArgForSubScreen(prm);
        } else if (BIZ_APP_ID_NSAL0550.equals(toBizAppId)) {
            Object[] prm = new Object[1];
            prm[0] = scrnMsg.dsContrNum;
            setArgForSubScreen(prm);
        // END 2016/03/16 T.Kanasaka [QC5546, MOD]
        // mod start 2016/03/03 CSA Defect#3018
        } else if (BIZ_APP_ID_NSAL0410.equals(toBizAppId)) {
            Object[] prm = new Object[3];
            prm[0] = scrnMsg.dsContrPk;
            prm[2] = scrnMsg.xxModeCd_FU;
            setArgForSubScreen(prm);
        } else if (BIZ_APP_ID_NSAL0440.equals(toBizAppId)) {
            Object[] prm = new Object[3];
            prm[0] = scrnMsg.dsContrPk;
            List<BigDecimal> dsContrDtlPks = new ArrayList<BigDecimal>();
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                    dsContrDtlPks.add(scrnMsg.A.no(i).dsContrDtlPk_A.getValue());
                }
            }
            prm[1] = dsContrDtlPks;
            prm[2] = scrnMsg.xxModeCd_FU;
            setArgForSubScreen(prm);
        // mod end 2016/03/03 CSA Defect#3018
        } else if (BIZ_APP_ID_NSAL0450.equals(toBizAppId)) {
            Object[] prm = new Object[2];
            prm[0] = scrnMsg.dsContrPk;
            prm[1] = scrnMsg.xxModeCd_FU;
            setArgForSubScreen(prm);
        } else if (BIZ_APP_ID_NSAL0710.equals(toBizAppId) || BIZ_APP_ID_NSAL0720.equals(toBizAppId) || BIZ_APP_ID_NSAL0730.equals(toBizAppId) || BIZ_APP_ID_NSAL0740.equals(toBizAppId) || BIZ_APP_ID_NSAL0750.equals(toBizAppId)) {
            Object[] prm = new Object[1];
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).dsContrPk_P1, scrnMsg.dsContrPk);
            scrnMsg.P.setValidCount(1);
            prm[0] = scrnMsg.P;
            setArgForSubScreen(prm);
        // START 2016/05/19 [QC4212, MOD]
        } else if (BIZ_APP_ID_NSAL0390.equals(toBizAppId)) {
            Object[] prm = new Object[3];
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).dsContrPk_P1, scrnMsg.dsContrPk);
            scrnMsg.P.setValidCount(1);
            prm[0] = scrnMsg.P;
            prm[1] = scrnMsg.crCardCustRefNum;
            ZYPEZDItemValueSetter.setValue(scrnMsg.crCardExprYrMth_P, scrnMsg.xxYrDt_CC.getValue() + scrnMsg.xxMthDt_CC.getValue());
            prm[2] = scrnMsg.crCardExprYrMth_P;
            setArgForSubScreen(prm);
        // END 2016/05/19 [QC4212, MOD]
        }
    }
}
