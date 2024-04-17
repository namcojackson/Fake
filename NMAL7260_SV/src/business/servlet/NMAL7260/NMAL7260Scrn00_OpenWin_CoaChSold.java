/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL7260.NMAL7260CMsg;
//import business.servlet.NMAL7260.constant.NMAL7260Constant;

import com.canon.cusa.s21.common.NMX.NMXC105001.NMXC105001PriceMasterUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 *NMAL7260Scrn00_OpenWin_CoaChSold
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/14   Fujitsu         Y.Kanefusa      Create          QC#6173
 *</pre>
 */
public class NMAL7260Scrn00_OpenWin_CoaChSold extends S21CommonHandler {

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

        setArgForSubScreen(NMXC105001PriceMasterUtil.getRuleAttrbPopParamFor6050(PRC_RULE_ATTRB.SOLD_TO_ACCT_CHANNEL,
                scrnMsg.B.no(idx).prcRuleCondFromTxt_55, scrnMsg.B.no(idx).coaChDescTxt_55, prmList));
    }
}
