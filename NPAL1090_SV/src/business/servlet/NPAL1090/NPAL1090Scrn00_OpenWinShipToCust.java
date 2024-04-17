/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/13   Fujitsu         T.Nishikawa     Create          CSA
 *</pre>
 */
public class NPAL1090Scrn00_OpenWinShipToCust extends S21CommonHandler  {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        //
        // // see: NSBL0190Scrn00_OpenWin_Tech
        //
        // NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
        // NPAL1090CommonLogic.clearPopupParam(scrnMsg);
        //
        // setValue(scrnMsg.xxPopPrm_01, getGlobalCompanyCode());
        // setValue(scrnMsg.xxPopPrm_02,
        // SHIP_TO_CUST_POPUP_WINDOW_TITLE);
        // setValue(scrnMsg.xxPopPrm_03, SHIP_TO_CUST_SEARCH_TABLE);
        //
        // // Where list
        // setValue(scrnMsg.P.no(0).xxComnScrCondLbTxt_H,
        // SHIP_TO_CUST_CODE_DISPLAY_NAME);
        // setValue(scrnMsg.P.no(0).xxComnScrQueryFltrTxt_H,
        // SHIP_TO_CUST_CODE_SQL_NAME);
        // setValue(scrnMsg.P.no(0).xxComnScrCondValTxt_H,
        // scrnMsg.shipToCustCd_H1);
        // setValue(scrnMsg.P.no(0).xxComnScrQueryLikeFlg_H,
        // FLG_ON_Y);
        //
        // setValue(scrnMsg.P.no(1).xxComnScrCondLbTxt_H,
        // SHIP_TO_CUST_NAME_DISPLAY_NAME);
        // setValue(scrnMsg.P.no(1).xxComnScrQueryFltrTxt_H,
        // SHIP_TO_CUST_NAME_SQL_NAME);
        // setValue(scrnMsg.P.no(1).xxComnScrQueryLikeFlg_H,
        // FLG_ON_Y);
        // scrnMsg.P.setValidCount(2);
        //
        // // Column list
        // setValue(scrnMsg.Q.no(0).xxComnScrColLbTxt_C,
        // SHIP_TO_CUST_CODE_DISPLAY_NAME);
        // setValue(scrnMsg.Q.no(0).xxComnScrQueryColNm_C,
        // SHIP_TO_CUST_CODE_SQL_NAME);
        // setValue(scrnMsg.Q.no(0).xxComnScrColLg_C,
        // BigDecimal.valueOf(SHIP_TO_CUST_CODE_LENGTH));
        // setValue(scrnMsg.Q.no(0).xxSelFlg_C, FLG_ON_Y);
        //
        // setValue(scrnMsg.Q.no(1).xxComnScrColLbTxt_C,
        // SHIP_TO_CUST_NAME_DISPLAY_NAME);
        // setValue(scrnMsg.Q.no(1).xxComnScrQueryColNm_C,
        // SHIP_TO_CUST_NAME_SQL_NAME);
        // setValue(scrnMsg.Q.no(1).xxComnScrColLg_C,
        // BigDecimal.valueOf(SHIP_TO_CUST_NAME_LENGTH));
        // setValue(scrnMsg.Q.no(1).xxSelFlg_C, FLG_OFF_N);
        //
        // setValue(scrnMsg.Q.no(2).xxComnScrColLbTxt_C,
        // POSTAL_DISPLAY_NAME);
        // setValue(scrnMsg.Q.no(2).xxComnScrQueryColNm_C,
        // POSTAL_SQL_NAME);
        // setValue(scrnMsg.Q.no(2).xxComnScrColLg_C,
        // BigDecimal.valueOf(POSTAL_LENGTH));
        // setValue(scrnMsg.Q.no(2).xxSelFlg_C, FLG_OFF_N);
        //
        // setValue(scrnMsg.Q.no(3).xxComnScrColLbTxt_C,
        // STATE_DISPLAY_NAME);
        // setValue(scrnMsg.Q.no(3).xxComnScrQueryColNm_C,
        // STATE_SQL_NAME);
        // setValue(scrnMsg.Q.no(3).xxComnScrColLg_C,
        // BigDecimal.valueOf(STATE_LENGTH));
        // setValue(scrnMsg.Q.no(3).xxSelFlg_C, FLG_OFF_N);
        //
        // setValue(scrnMsg.Q.no(4).xxComnScrColLbTxt_C,
        // CITY_DISPLAY_NAME);
        // setValue(scrnMsg.Q.no(4).xxComnScrQueryColNm_C,
        // CITY_SQL_NAME);
        // setValue(scrnMsg.Q.no(4).xxComnScrColLg_C,
        // BigDecimal.valueOf(CITY_LENGTH));
        // setValue(scrnMsg.Q.no(4).xxSelFlg_C, FLG_OFF_N);
        //
        // setValue(scrnMsg.Q.no(5).xxComnScrColLbTxt_C,
        // ADDRESS_DISPLAY_NAME);
        // setValue(scrnMsg.Q.no(5).xxComnScrQueryColNm_C,
        // ADDRESS_SQL_NAME);
        // setValue(scrnMsg.Q.no(5).xxComnScrColLg_C,
        // BigDecimal.valueOf(ADDRESS_LENGTH));
        // setValue(scrnMsg.Q.no(5).xxSelFlg_C, FLG_OFF_N);
        //
        // scrnMsg.Q.setValidCount(6);
        //
        // // Sort list
        // setValue(scrnMsg.R.no(0).xxTblSortColNm_S,
        // SHIP_TO_CUST_CODE_SQL_NAME);
        // setValue(scrnMsg.R.no(0).xxSortOrdByTxt_S, ASCENDING);
        // scrnMsg.R.setValidCount(1);
        //
        // // Result Placeholder
        // scrnMsg.S.setValidCount(0);
        //
        // int cnt = 0;
        // Object[] params = new Object[7];
        // params[cnt++] = "";
        // params[cnt++] = scrnMsg.xxPopPrm_02;
        // params[cnt++] = scrnMsg.xxPopPrm_03;
        // params[cnt++] = scrnMsg.P;
        // params[cnt++] = scrnMsg.Q;
        // params[cnt++] = scrnMsg.R;
        // params[cnt++] = scrnMsg.S;
        //
        // setArgForSubScreen(params);
    }
}
