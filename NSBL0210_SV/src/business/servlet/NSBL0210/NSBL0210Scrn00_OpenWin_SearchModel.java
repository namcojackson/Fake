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
 * 2013/06/28   SRA             E.Inada         Create          N/A
 * 2016/05/18   Hitachi         Y.Takeno        Update          NA#CSA
 *</pre>
 */
public class NSBL0210Scrn00_OpenWin_SearchModel extends S21CommonHandler  {

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
//        int selectIdx = getButtonSelectNumber();
//        String mdlGrpNm = null;
//        String mdlNm = null;
//
//        if (selectIdx < 0) {
//            mdlGrpNm = scrnMsg.mdlGrpNm.getValue();
//            mdlNm = scrnMsg.mdlNm.getValue();
//        } else {
//            mdlGrpNm = scrnMsg.A.no(selectIdx).mdlGrpNm_A.getValue();
//            mdlNm = scrnMsg.A.no(selectIdx).mdlNm_A.getValue();
//        }
//
//        List<Object> whereList = new ArrayList<Object>();
//        Object[] whereObj1 = {POPUP_MDL_GRP_NM, POPUP_MDL_GRP_NM_COL, mdlGrpNm, ZYPConstant.FLG_ON_Y };
//        Object[] whereObj2 = {POPUP_MDL_NM, POPUP_MDL_NM_COL, mdlNm, ZYPConstant.FLG_ON_Y };
//        whereList.add(whereObj1);
//        whereList.add(whereObj2);
//
//        List<Object> colList = new ArrayList<Object>();
//        Object[] colObj1 = {POPUP_MDL_GRP_NM, POPUP_MDL_GRP_NM_COL, new BigDecimal(POPUP_MDL_GRP_NM_LEN), ZYPConstant.FLG_ON_Y };
//        Object[] colObj2 = {POPUP_MDL_NM, POPUP_MDL_NM_COL, new BigDecimal(POPUP_MDL_NM_LEN), ZYPConstant.FLG_ON_Y };
//        colList.add(colObj1);
//        colList.add(colObj2);
//
//        List<Object> sortList = new ArrayList<Object>();
//        Object[] sortObj1 = {POPUP_MDL_ID_COL, POPUP_MDL_DESC };
//        sortList.add(sortObj1);
//
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, POPUP_MDL_TITLE);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, POPUP_SQL_SELECT + POPUP_SQL_FROM + POPUP_SQL_WHERE1 + POPUP_SQL_WHERE2 + POPUP_SQL_WHERE3);
//
//        Object[] params = new Object[PARAM_7];
//
//        // 0 : Lv1 : Suffix
//        params[PARAM_0] = POPUP_MDL_SUFFIX;
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
