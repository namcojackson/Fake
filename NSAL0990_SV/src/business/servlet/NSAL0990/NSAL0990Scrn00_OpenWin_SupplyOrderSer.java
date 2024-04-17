/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0990.NSAL0990CMsg;
import business.servlet.NSAL0990.common.NSAL0990CommonLogic;
import business.servlet.NSAL0990.constant.NSAL0990Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         K.Kasai         Create          N/A
 * 2016/02/23   Hitachi         T.Tsuchida      Update          QC#4117
 * 2016/03/11   Hitachi         A.Kohinata      Update          QC#5211
 * 2018/07/12   Hitachi         K.Kim           Update          QC#27009
 * 2018/07/19   Hitachi         K.Kitachi       Update          QC#26978
 * 2019/01/21   Hitachi         A.Kohinata      Update          QC#27304
 * 2019/07/31   Hitachi         T.Kanasaka      Update          QC#51976
 *</pre>
 */
public class NSAL0990Scrn00_OpenWin_SupplyOrderSer extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        NSAL0990CommonLogic.addCheckItem(scrnMsg);
        // add start 2019/01/21 QC#27304
        if (!ZYPCommonFunc.hasValue(scrnMsg.billToLocNum)) {
            scrnMsg.addCheckItem(scrnMsg.billToLocNum);
            scrnMsg.billToLocNum.setErrorInfo(1, NSAL0990Constant.ZZZM9007E, new String []{"Bill To"});
        }
        // add end 2019/01/21 QC#27304
        if (!ZYPCommonFunc.hasValue(scrnMsg.curLocNum)) {
            scrnMsg.addCheckItem(scrnMsg.curLocNum);
            scrnMsg.curLocNum.setErrorInfo(1, NSAL0990Constant.ZZZM9007E, new String []{"Ship To"});
        }
        // START 2019/07/31 T.Kanasaka [QC#51976, MOD]
//        if (!ZYPCommonFunc.hasValue(scrnMsg.svcByLineBizTpCd)) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.svcByLineBizTpCd) && !ZYPCommonFunc.hasValue(scrnMsg.sldByLineBizTpCd)) {
            scrnMsg.addCheckItem(scrnMsg.svcByLineBizTpCd);
            scrnMsg.addCheckItem(scrnMsg.sldByLineBizTpCd);
            scrnMsg.svcByLineBizTpCd.setErrorInfo(1, NSAL0990Constant.ZZZM9007E, new String []{"Line Of Business Type"});
            scrnMsg.sldByLineBizTpCd.setErrorInfo(1, NSAL0990Constant.ZZZM9007E, new String []{"Line Of Business Type"});
        }
        // END 2019/07/31 T.Kanasaka [QC#51976, MOD]
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;

        NSAL0990CMsg bizMsg = new NSAL0990CMsg();
        bizMsg.setBusinessID(NSAL0990Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        NSAL0990CMsg bizMsg  = (NSAL0990CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0990CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        NSAL0990CommonLogic.clearPopUpParam(scrnMsg);

        Object[] params = new Object[NSAL0990Constant.PRM_NSAL1020];
        int i = 0;
        setValue(scrnMsg.xxPopPrm_P0, scrnMsg.ownrAcctNum);
        params[i++] = scrnMsg.xxPopPrm_P0;
        setValue(scrnMsg.xxPopPrm_P1, scrnMsg.curLocNum);
        params[i++] = scrnMsg.xxPopPrm_P1;
        // START 2019/07/31 T.Kanasaka [QC#51976, MOD]
//        setValue(scrnMsg.xxPopPrm_P2, scrnMsg.svcByLineBizTpCd);
        if (ZYPCommonFunc.hasValue(scrnMsg.sldByLineBizTpCd)) {
            setValue(scrnMsg.xxPopPrm_P2, scrnMsg.sldByLineBizTpCd);
        } else {
            setValue(scrnMsg.xxPopPrm_P2, scrnMsg.svcByLineBizTpCd);
        }
        // END 2019/07/31 T.Kanasaka [QC#51976, MOD]
        params[i++] = scrnMsg.xxPopPrm_P2;
        // START 2018/07/12 K.Kim [QC#27009, MOD]
        setValue(scrnMsg.xxPopPrm_P3, scrnMsg.billToAcctNum);
        params[i++] = scrnMsg.xxPopPrm_P3;
        // END 2018/07/12 K.Kim [QC#27009, MOD]
        int cnt = 0;
        for (int j = 0; j < scrnMsg.E.getValidCount(); j++) {
            // START 2018/07/19 K.Kitachi [QC#26978, MOD]
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(j).serNum_PD, scrnMsg.E.no(j).serNum_E);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(j).svcMachMstrPk_PD, scrnMsg.E.no(j).svcMachMstrPk_E);
            // END 2018/07/19 K.Kitachi [QC#26978, MOD]
            cnt++;
        }
        scrnMsg.P.setValidCount(cnt);
        params[i++] = scrnMsg.P;
        // START 2016/02/23 T.Tsuchida [QC#4117, MOD]
        params[i++] = scrnMsg.svcMachMstrPk_P;
        params[i++] = scrnMsg.dsContrDtlPk_P;
        // END 2016/02/23 T.Tsuchida [QC#4117, MOD]
        setArgForSubScreen(params);

    }
}
