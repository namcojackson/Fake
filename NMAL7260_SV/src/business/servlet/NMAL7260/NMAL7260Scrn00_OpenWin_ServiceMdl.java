/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.common.NMX.NMXC105001.NMXC105001PriceMasterUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7260Scrn00_OpenWin_ServiceMdl
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/30   Fujitsu         R.Nakamura      Create          N/A
 * 2017/08/24   Fujitsu         S.Ohki          Update          QC#20729
 *</pre>
 */
public class NMAL7260Scrn00_OpenWin_ServiceMdl extends S21CommonHandler {

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

        int idx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, ctx.getEventName());

        // 2017/08/24 QC#20729 Add Start
//        // Clear Params
//        ZYPTableUtil.clear(scrnMsg.P);
//
//        // Set Params
//        Object[] params = new Object[1];
//        params[0] = scrnMsg.P.no(0).xxPopPrm;
//
//        setArgForSubScreen(params);
//        openMultiModeScreen();

        scrnMsg.R.clear();

        setArgForSubScreen(NMXC105001PriceMasterUtil.getRuleAttrbPopParamFor1130(PRC_RULE_ATTRB.SERVICE_MODEL,
                scrnMsg.B.no(idx).prcRuleCondFromTxt_40, scrnMsg.B.no(idx).mdlDescTxt_40, getGlobalCompanyCode(), scrnMsg.R));
        // 2017/08/24 QC#20729 Add End
    }
}
