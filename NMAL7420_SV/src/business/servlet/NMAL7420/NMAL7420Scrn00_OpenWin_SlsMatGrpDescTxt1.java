/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7420;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL7420.NMAL7420CMsg;
//import business.servlet.NMAL7420.constant.NMAL7420Constant;

import business.servlet.NMAL7420.common.NMAL7420CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/24   Fujitsu         M.Ohno          Update          QC#22569
 *</pre>
 */
public class NMAL7420Scrn00_OpenWin_SlsMatGrpDescTxt1 extends S21CommonHandler {

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

        NMAL7420BMsg scrnMsg = (NMAL7420BMsg) bMsg;
        setArgForSubScreen(NMAL7420CommonLogic.getPopParamFor1130(scrnMsg, scrnMsg.prcRuleCondFromTxt_59, //
                scrnMsg.slsMatGrpDescTxt_59, ctx.getEventName(), PRC_RULE_ATTRB.MATERIAL_GROUP_1, getGlobalCompanyCode()));

    }
}
