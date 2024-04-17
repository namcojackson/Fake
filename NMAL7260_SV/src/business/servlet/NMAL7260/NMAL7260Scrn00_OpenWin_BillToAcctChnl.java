/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.common.NMX.NMXC105001.NMXC105001PriceMasterUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7260Scrn00_OpenWin_BillToAcctChnl
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/30   Fujitsu         R.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7260Scrn00_OpenWin_BillToAcctChnl extends S21CommonHandler {

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

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;

        List<EZDBStringItem> prmList = new ArrayList<EZDBStringItem>();
        for (int i = 0; i < scrnMsg.P.length(); i++) {
            prmList.add(scrnMsg.P.no(i).xxPopPrm);
        }

        int idx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, ctx.getEventName());

        setArgForSubScreen(NMXC105001PriceMasterUtil.getRuleAttrbPopParamFor6050(PRC_RULE_ATTRB.BILL_TO_ACCT_CHANNEL, //
                scrnMsg.B.no(idx).prcRuleCondFromTxt_17, scrnMsg.B.no(idx).coaChDescTxt_17, prmList));
    }
}
