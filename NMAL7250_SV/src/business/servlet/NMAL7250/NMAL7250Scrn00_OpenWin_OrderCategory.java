/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7250;

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
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/15   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7250Scrn00_OpenWin_OrderCategory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7250BMsg scrnMsg = (NMAL7250BMsg) bMsg;

        List<EZDBStringItem> prmList = new ArrayList<EZDBStringItem>();
        for (int i = 0; i < scrnMsg.P.length(); i++) {
            prmList.add(scrnMsg.P.no(i).xxPopPrm);
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, ctx.getEventName());

        scrnMsg.P.no(29).xxPopPrm.clear();
        setArgForSubScreen(NMXC105001PriceMasterUtil.getRuleAttrbPopParamFor6050(PRC_RULE_ATTRB.ORDER_CATEGORY, scrnMsg.dsOrdCatgCd, scrnMsg.P.no(29).xxPopPrm, prmList));
    }
}
