/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7270;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.common.NMX.NMXC105001.NMXC105001PriceMasterUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7270Scrn00_OpenWin_PrcRuleCondVal_Cmn
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7270Scrn00_OpenWin_PrcRuleCondVal_Cmn extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7270BMsg scrnMsg = (NMAL7270BMsg) bMsg;

        List<EZDBStringItem> prmList = new ArrayList<EZDBStringItem>();
        scrnMsg.P.clear();
        for (int i = 0; i < scrnMsg.P.length(); i++) {
            prmList.add(scrnMsg.P.no(i).xxPopPrm);
        }

        int idx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, ctx.getEventName());

        setArgForSubScreen(NMXC105001PriceMasterUtil.getRuleAttrbPopParamFor6050(scrnMsg.A.no(idx).prcRuleAttrbCd_A1.getValue(),
                scrnMsg.A.no(idx).prcRuleCondFromTxt_A1, scrnMsg.A.no(idx).xxRecNmTxt_A1, prmList));

    }
}
