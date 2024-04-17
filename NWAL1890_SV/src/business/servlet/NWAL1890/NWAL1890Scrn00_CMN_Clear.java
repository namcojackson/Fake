/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1890;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/08   Fujitsu         T.Aoi           Create          N/A
 *</pre>
 */
public class NWAL1890Scrn00_CMN_Clear extends S21CommonHandler {

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

        NWAL1890BMsg scrnMsg = (NWAL1890BMsg) bMsg;

        // Config Level //
        scrnMsg.dsOrdPosnNum.clear();
        scrnMsg.xxConfigIdSrchTxt.clear();
        scrnMsg.xxMdlSrchTxt.clear();
        scrnMsg.xxBillToAcctNmSrchTxt.clear();
        scrnMsg.xxBillToAcctCdSrchTxt.clear();
        scrnMsg.xxBillToLocCdSrchTxt.clear();
        scrnMsg.xxShipToAcctNmSrchTxt.clear();
        scrnMsg.xxShipToAcctCdSrchTxt.clear();
        scrnMsg.xxShipToLocCdSrchTxt.clear();
        scrnMsg.xxSoldToAcctNmSrchTxt.clear();
        scrnMsg.xxSoldToAcctCdSrchTxt.clear();
        scrnMsg.xxSoldToLocCdSrchTxt.clear();
        scrnMsg.xxShowInclLineFlg.clear();

        // Line Level //
        scrnMsg.xxLineNum.clear();
        scrnMsg.xxLineStsSrchTxt.clear();
        scrnMsg.xxOrdItemSrchTxt.clear();
        scrnMsg.xxRtlWhSrchTxt.clear();
        scrnMsg.xxRtlSwhSrchTxt.clear();
        scrnMsg.xxCpoSrcTpSrchTxt.clear();
        scrnMsg.xxOrdSrcRefNumSrchTxt.clear();
        scrnMsg.xxSbstItemSrchTxt.clear();
        scrnMsg.xxSerNumSrchTxt.clear();
        scrnMsg.xxShowInclCloLineFlg.clear();
        scrnMsg.xxShowInclCancLineFlg.clear();

        // RMA Line Level //
        scrnMsg.xxLineNum_R.clear();
        scrnMsg.xxLineStsSrchTxt_R.clear();
        scrnMsg.xxOrdItemSrchTxt_R.clear();
        scrnMsg.xxRtrnRsnSrchTxt_R.clear();
        scrnMsg.xxRtlWhSrchTxt_R.clear();
        scrnMsg.xxRtlSwhSrchTxt_R.clear();
        scrnMsg.xxOrdSrcRefNumSrchTxt_R.clear();
        scrnMsg.xxSerNumSrchTxt_R.clear();
        scrnMsg.xxShowInclCloLineFlg_R.clear();
        scrnMsg.xxShowInclCancLineFlg_R.clear();
    }
}
