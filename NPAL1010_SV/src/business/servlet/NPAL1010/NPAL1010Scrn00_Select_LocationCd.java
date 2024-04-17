/**
 * Copyright(c)2011 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1010;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1010.common.NPAL1010CommonLogic;
import business.servlet.NPAL1010.constant.NPAL1010Constant.PARAMS_INDEX;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * NPAL1010 Location Info Pop Up
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/06/26   Fujitsu         S.Noguchi       Create          N/A
 * 2012/09/13   Fujitsu         Y.Kamide        Update          N/A
 * 2013/05/14   Fujitsu         H.Mizutani      Update          N/A
 * 2015/04/28   CITS            H.Sugawara      Update          N/A
 *</pre>
 */
public class NPAL1010Scrn00_Select_LocationCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1010BMsg scrnMsg = (NPAL1010BMsg) bMsg;

        NPAL1010CommonLogic.addCheckItemSearchCondition(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1010BMsg scrnMsg = (NPAL1010BMsg) bMsg;

        int index = getButtonSelectNumber();

        Object[] arg = (Object[]) getArgForSubScreen();

        if (arg instanceof Object[]) {
            // WDS R-OM025 Inventory Transaction Mizutani Start
            Object[] params = (Object[]) arg;
            EZDBStringItem param1;
            EZDBStringItem param2;
            EZDBStringItem param6;
            EZDBStringItem param7;
            EZDBStringItem param8;
            EZDBStringItem param9;
            EZDBStringItem param10;
            EZDBStringItem param12; // 10/28/2015 add
            if (params.length > PARAMS_INDEX.LOCATION_CODE.getIndex()) {
                param1 = (EZDBStringItem) params[PARAMS_INDEX.LOCATION_CODE.getIndex()];
                ZYPEZDItemValueSetter.setValue(param1, scrnMsg.A.no(index).invtyLocCd_A);
            }
            if (params.length > PARAMS_INDEX.LOCATION_NAME.getIndex()) {
                param2 = (EZDBStringItem) params[PARAMS_INDEX.LOCATION_NAME.getIndex()];
                ZYPEZDItemValueSetter.setValue(param2, scrnMsg.A.no(index).invtyLocNm_A);
            }
            if (params.length > PARAMS_INDEX.LOC_ROLE_TP_CD.getIndex()) {
                param6 = (EZDBStringItem) params[PARAMS_INDEX.LOC_ROLE_TP_CD.getIndex()];
                ZYPEZDItemValueSetter.setValue(param6, scrnMsg.A.no(index).locRoleTpCd_A);
            }
            // EZDBStringItem param3 = (EZDBStringItem) params[2];
            // EZDBStringItem param4 = (EZDBStringItem) params[3];
            // EZDBStringItem param5 = (EZDBStringItem) params[4];
            // START 2015/04/28 H.Sugawara E850 Warehouse Setup
            if (params.length > PARAMS_INDEX.RETAIL_WH_CODE.getIndex()) {
                param7 = (EZDBStringItem) params[PARAMS_INDEX.RETAIL_WH_CODE.getIndex()];
                // WH_CD
                ZYPEZDItemValueSetter.setValue(param7, scrnMsg.A.no(index).rtlWhCd_A);
            }
            if (params.length > PARAMS_INDEX.RETAIL_WH_NAME.getIndex()) {
                param8 = (EZDBStringItem) params[PARAMS_INDEX.RETAIL_WH_NAME.getIndex()];
                // WH_NM
                ZYPEZDItemValueSetter.setValue(param8, scrnMsg.A.no(index).rtlWhNm_A);
            }
            if (params.length > PARAMS_INDEX.RETAIL_SWH_CODE.getIndex()) {
                param9 = (EZDBStringItem) params[PARAMS_INDEX.RETAIL_SWH_CODE.getIndex()];
                // SWH_CD
                ZYPEZDItemValueSetter.setValue(param9, scrnMsg.A.no(index).rtlSwhCd_A);
            }
            if (params.length > PARAMS_INDEX.RETAIL_SWH_NAME.getIndex()) {
                param10 = (EZDBStringItem) params[PARAMS_INDEX.RETAIL_SWH_NAME.getIndex()];
                // SWH_NM
                ZYPEZDItemValueSetter.setValue(param10, scrnMsg.A.no(index).rtlSwhNm_A);
            }
            // 10/28/2015 add start
            if (params.length > PARAMS_INDEX.INVTY_ACCT_CD.getIndex()) {
                param12 = (EZDBStringItem) params[PARAMS_INDEX.INVTY_ACCT_CD.getIndex()];
                // INVTY_ACCT_CD
                ZYPEZDItemValueSetter.setValue(param12, scrnMsg.A.no(index).invtyAcctCd_A);
            }
            // 10/28/2015 add end

            // END 2015/04/28 H.Sugawara E850 Warehouse Setup

            // START 2015/04/28 H.Sugawara E850 Warehouse Setup
            // END 2015/04/28 H.Sugawara E850 Warehouse Setup
            // ZYPEZDItemValueSetter.setValue(param1,
            // scrnMsg.invtyLocCd);
            // ZYPEZDItemValueSetter.setValue(param2,
            // scrnMsg.invtyLocNm);
            // ZYPEZDItemValueSetter.setValue(param3,
            // scrnMsg.dsWhSrcTpCd);
            // ZYPEZDItemValueSetter.setValue(param4,
            // scrnMsg.A.no(index).invtyLocCd_A);
            // ZYPEZDItemValueSetter.setValue(param5,
            // scrnMsg.A.no(index).invtyLocNm_A);
            // WDS R-OM025 Inventory Transaction Mizutani End
        }
    }
}
