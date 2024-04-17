/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0030;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/06/14   Hitachi         T.Tsuchida      Update          QC#9829
 *</pre>
 */
public class NFDL0030Scrn00_OpenWin_CreditCardEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0030BMsg scrnMsg = (NFDL0030BMsg) bMsg;

        // START 2016/06/14 T.Tsuchida [QC#9829,MOD]
//        Object[] prm = new Object[13];
//
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, scrnMsg.dsAcctNum_H);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, "COLLECTION");
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, scrnMsg.dsAcctNum_H);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, getContextUserInfo().getUserId());
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_05, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
//
//        prm[0] = scrnMsg.xxPopPrm_01;
//        prm[1] = scrnMsg.xxPopPrm_02;
//        prm[2] = scrnMsg.xxPopPrm_03;
//        prm[3] = scrnMsg.xxPopPrm_04;
//        prm[4] = scrnMsg.xxPopPrm_05;
//        prm[5] = scrnMsg.xxPopPrm_06;
//        prm[6] = scrnMsg.xxPopPrm_07;
//        prm[7] = scrnMsg.xxPopPrm_08;
//        prm[8] = scrnMsg.xxPopPrm_09;
//        prm[9] = scrnMsg.xxPopPrm_10;
//        prm[10] = scrnMsg.xxPopPrm_11;
//        prm[11] = scrnMsg.xxPopPrm_12;
//        prm[12] = scrnMsg.xxPopPrm_13;

        List<Object> parameters = new ArrayList<Object>();

        ZYPEZDItemValueSetter.setValue(scrnMsg.sellToCustCd_O, scrnMsg.dsAcctNum_H);
        ZYPEZDItemValueSetter.setValue(scrnMsg.crCardTrxTpCd_O, CR_CARD_TRX_TP.COLLECTION);
        ZYPEZDItemValueSetter.setValue(scrnMsg.firstTrxInfoTxt_O, scrnMsg.dsAcctNum_H);
        ZYPEZDItemValueSetter.setValue(scrnMsg.scdTrxInfoTxt_O, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(scrnMsg.thirdTrxInfoTxt_O, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        scrnMsg.frthTrxInfoTxt_O.clear();
        scrnMsg.fifthTrxInfoTxt_O.clear();
        scrnMsg.firstTrxInfoNum_O.clear();
        scrnMsg.scdTrxInfoNum_O.clear();
        scrnMsg.thirdTrxInfoNum_O.clear();
        scrnMsg.frthTrxInfoNum_O.clear();
        scrnMsg.fifthTrxInfoNum_O.clear();
        scrnMsg.dsCrCardPk_O.clear();
//        scrnMsg.dsCrCardPk_O.setValue(scrnMsg.dsCrCardPk.getValue());

        parameters.add(scrnMsg.sellToCustCd_O);
        parameters.add(scrnMsg.crCardTrxTpCd_O);
        parameters.add(scrnMsg.firstTrxInfoTxt_O);
        parameters.add(scrnMsg.scdTrxInfoTxt_O);
        parameters.add(scrnMsg.thirdTrxInfoTxt_O);
        parameters.add(scrnMsg.frthTrxInfoTxt_O);
        parameters.add(scrnMsg.fifthTrxInfoTxt_O);
        parameters.add(scrnMsg.firstTrxInfoNum_O);
        parameters.add(scrnMsg.scdTrxInfoNum_O);
        parameters.add(scrnMsg.thirdTrxInfoNum_O);
        parameters.add(scrnMsg.frthTrxInfoNum_O);
        parameters.add(scrnMsg.fifthTrxInfoNum_O);
        parameters.add(scrnMsg.dsCrCardPk_O);

        setArgForSubScreen(parameters.toArray(new Object[0]));
        // END 2016/06/14 T.Tsuchida [QC#9829,MOD]
    }
}
