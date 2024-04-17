/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import static business.servlet.NSAL0300.constant.NSAL0300Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.ArrayList;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Hitachi         T.Kanasaka      Create          N/A
 * 2015/12/11   Hitachi         T.Kanasaka      Update          QC1757
 * 2016/03/02   Hitachi         T.Tomita        Update          QC#3048
 * 2017/02/15   Hitachi         K.Kishimoto     Update          QC#17306
 * 2018/06/18   Hitachi         K.Kitachi       Update          QC#18591
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_BillToContact extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.altPayerCustCd)) {
            scrnMsg.altPayerCustCd.setErrorInfo(1, ZZM9000E, new String[] {"Bill To Location" });
        }
        scrnMsg.addCheckItem(scrnMsg.altPayerCustCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
//        NSAL0300CMsg bizMsg = new NSAL0300CMsg();
//        bizMsg.setBusinessID(NSAL0300Constant.BIZ_ID);
//        bizMsg.setFunctionCode(NSAL0300Constant.EZD_FUNC_CD_INQ);
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//        return bizMsg;
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2016/03/02 T.Tomita [QC#3048, MOD]
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
//        NSAL0300CMsg bizMsg = (NSAL0300CMsg) cMsg;
        NSAL0300CommonLogic.clearPopupParam(scrnMsg);
        scrnMsg.xxScrEventNm.setValue("OpenWin_BillToContact");

        Object[] params = new Object[26];
        // START 2018/06/18 K.Kitachi [QC#18591, ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_06, scrnMsg.ctacPsnFirstNm_CP);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_07, scrnMsg.ctacPsnLastNm_CP);
        // END 2018/06/18 K.Kitachi [QC#18591, ADD]
        scrnMsg.xxPopPrm_11.setValue(ZYPConstant.FLG_OFF_N);
        scrnMsg.xxPopPrm_12.setValue(ZYPConstant.FLG_OFF_N);
        scrnMsg.xxPopPrm_13.setValue(ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, bizMsg.locNum_PP);
        // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, CTAC_TP.BILL_TO_CONTACT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, CTAC_TP.BILL_TO_CONTACT_CONTRACTS);
        // END 2018/06/18 K.Kitachi [QC#18591, MOD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_20, scrnMsg.altPayerCustCd);
        // Add Start 02/15/2017 <QC#17306>
        setValue(scrnMsg.xxPopPrm_24, ZYPConstant.FLG_ON_Y);
        // Add End   02/15/2017 <QC#17306>
        // START 2018/06/18 K.Kitachi [QC#18591, DEL]
//        NSAL0300CommonLogic.setContactName(scrnMsg.xxPsnNm_CP, scrnMsg.xxPopPrm_06, scrnMsg.xxPopPrm_07);
        // END 2018/06/18 K.Kitachi [QC#18591, DEL]
        params[0] = scrnMsg.xxPopPrm_00;
        params[1] = scrnMsg.xxPopPrm_01;
        params[2] = scrnMsg.xxPopPrm_02;
        params[3] = scrnMsg.xxPopPrm_03;
        params[4] = scrnMsg.xxPopPrm_04;
        params[5] = scrnMsg.xxPopPrm_05;
        params[6] = scrnMsg.xxPopPrm_06;
        params[7] = scrnMsg.xxPopPrm_07;
        params[8] = scrnMsg.xxPopPrm_08;
        params[9] = scrnMsg.xxPopPrm_09;
        params[10] = scrnMsg.xxPopPrm_10;
        params[11] = scrnMsg.xxPopPrm_11;
        params[12] = scrnMsg.xxPopPrm_12;
        params[13] = scrnMsg.xxPopPrm_13;
        params[14] = scrnMsg.xxPopPrm_14;
        params[15] = scrnMsg.ctacPsnPk_15;
        params[16] = scrnMsg.ctacPsnPk_16;
        params[17] = scrnMsg.ctacPsnPk_17;
        params[18] = new ArrayList<Object>();
        params[19] = scrnMsg.ctacPsnPk_CP;
        params[20] = scrnMsg.xxPopPrm_20;
        // Add Start 02/15/2017 <QC#17306>
        params[21] = null;
        params[22] = null;
        params[23] = null;
        params[24] = null;
        params[25] = scrnMsg.xxPopPrm_24;
        // Add End   02/15/2017 <QC#17306>
        setArgForSubScreen(params);
        // END 2016/03/02 T.Tomita [QC#3048, MOD]
    }
}
