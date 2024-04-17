/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0210;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/30   Hitachi         T.Aoyagi        Create          QC2049
 * 2016/05/18   Hitachi         Y.Takeno        Update          NA#CSA
 *</pre>
 */
public class NSBL0210Scrn00_OpenWin_SearchIntgMdse extends S21CommonHandler  {

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

//        NSBL0210BMsg scrnMsg = (NSBL0210BMsg) bMsg;
//
//        String itngMdseCd = scrnMsg.A.no(getButtonSelectNumber()).intgMdseCd_A.getValue();
//
//        List<Object> whereList = new ArrayList<Object>();
//        Object[] whereObj1 = {POPUP_MDSE_CD, POPUP_MDSE_CD_COL, itngMdseCd, FLG_ON_Y };
//        Object[] whereObj2 = {POPUP_MDSE_NM, POPUP_MDSE_NM_COL, null, FLG_ON_Y };
//        Object[] whereObj3 = {POPUP_COA_PROD_CD, POPUP_COA_PROD_CD_COL, null, FLG_ON_Y };
//        whereList.add(whereObj1);
//        whereList.add(whereObj2);
//        whereList.add(whereObj3);
//
//        List<Object> colList = new ArrayList<Object>();
//        Object[] colObj1 = {POPUP_MDSE_CD, POPUP_MDSE_CD_COL, new BigDecimal(POPUP_MDSE_CD_LEN), FLG_ON_Y };
//        Object[] colObj2 = {POPUP_MDSE_NM, POPUP_MDSE_NM_COL, new BigDecimal(POPUP_MDSE_NM_LEN), FLG_OFF_N };
//        Object[] colObj3 = {POPUP_COA_PROD_CD, POPUP_COA_PROD_CD_COL, new BigDecimal(POPUP_COA_PROD_CD_LEN), FLG_OFF_N };
//        colList.add(colObj1);
//        colList.add(colObj2);
//        colList.add(colObj3);
//
//        List<Object> sortList = new ArrayList<Object>();
//        Object[] sortObj1 = {POPUP_MDSE_CD_COL, POPUP_MDSE_ASC };
//        sortList.add(sortObj1);
//
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, POPUP_INTG_MDSE_TITLE);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, POPUP_MDSE_SQL_SELECT);
//
//        Object[] params = new Object[PARAM_7];
//
//        // 0 : Lv1 : Suffix
//        params[PARAM_0] = POPUP_INTG_MDSE_SUFFIX;
//        // 1 : Lv1 : Window Title
//        params[PARAM_1] = scrnMsg.xxPopPrm_P1;
//        // 2 : Lv1 : Select Table Name
//        params[PARAM_2] = scrnMsg.xxPopPrm_P2;
//        // 3 : Lv1 : Where List
//        params[PARAM_3] = whereList;
//        // 4 : Lv1 : Column List
//        params[PARAM_4] = colList;
//        // 5 : Lv1 : Sort Condition List
//        params[PARAM_5] = sortList;
//        // 6 : Output
//        params[PARAM_6] = scrnMsg.B;
//
//        setArgForSubScreen(params);
    }
}
