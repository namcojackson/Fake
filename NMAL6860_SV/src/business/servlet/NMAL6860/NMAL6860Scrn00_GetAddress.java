/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860;

import static business.servlet.NMAL6860.constant.NMAL6860Constant.BIZ_ID;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.ZZZM9025E;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.ZZM9037E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6860.NMAL6860CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/09   CITS            S.Endo          Create          QC#10839
 * 2018/02/15   CITS            T.Gotoda        Update          QC#22054
 *</pre>
 */
public class NMAL6860Scrn00_GetAddress extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // QC#22054 MOD START
        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;
        int idx = this.getButtonSelectNumber();

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).ctryCd_A)) {
            scrnMsg.addCheckItem(scrnMsg.A.no(idx).ctryCd_A);
            scrnMsg.A.no(idx).ctryCd_A.setErrorInfo(1, ZZZM9025E, new String[] {scrnMsg.A.no(idx).ctryCd_A.getNameForMessage()});
            scrnMsg.setMessageInfo(ZZM9037E);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).postCd_A)) {
            scrnMsg.addCheckItem(scrnMsg.A.no(idx).postCd_A);
            scrnMsg.A.no(idx).postCd_A.setErrorInfo(1, ZZZM9025E, new String[] {scrnMsg.A.no(idx).postCd_A.getNameForMessage()});
            scrnMsg.setMessageInfo(ZZM9037E);
        }

        scrnMsg.putErrorScreen();
        // QC#22054 MOD END
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;

        NMAL6860CMsg bizMsg = new NMAL6860CMsg();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_AD, new BigDecimal(this.getButtonSelectNumber()));
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;
        NMAL6860CMsg bizMsg  = (NMAL6860CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int idx = scrnMsg.xxNum_AD.getValueInt();
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).postCd_A);
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).ctyAddr_A);
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).stCd_A);
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).cntyNm_A);
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.A.no(idx).postCd_A);
    }
}
