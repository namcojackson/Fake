/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0170;

import static business.servlet.NMAL0170.constant.NMAL0170Constant.POPUP_CODE_WIDTH;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.POPUP_DISP_ITEM_NUMBER;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.POPUP_DISP_SUPD;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.POPUP_DISPLAY_NAME;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.POPUP_NAME_WIDTH;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.POPUP_SORT_KEY_ASC;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.POPUP_SQL_ITEM_NUMBER;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.POPUP_SQL_SUPD;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.POPUP_SQL_ITEM_NM;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.POPUP_SQL_SUPD_NM;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.POPUP_WINDOW_TITLE;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.POPUP_PRM_NUM;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.POPUP_TBL_SUFFIX;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0170Scrn00_OpenWin_LinkItemNum
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/07   Fujitsu         T.Arima         Create          N/A
 * 2015/12/11   Fujitsu         T.Arima         Update          QC#1882
 *</pre>
 */
public class NMAL0170Scrn00_OpenWin_LinkItemNum extends S21CommonHandler {

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

        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;

        List<Object> params = new ArrayList<Object>(POPUP_PRM_NUM);

        params.add(POPUP_TBL_SUFFIX);
        params.add(POPUP_WINDOW_TITLE);
        params.add(getSelectSQL(scrnMsg.supdToMdseCd.getValue()));
        params.add(getSearchConditionSetting(scrnMsg));
        params.add(getDisplayColumnSetting(scrnMsg));
        params.add(getSortSetting(scrnMsg));
        params.add(scrnMsg.T);

        setArgForSubScreen(params.toArray());
    }

    private List<Object> getSearchConditionSetting(NMAL0170BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();

        //UPDATE START 12/11/2015
        Object[] whereObj1 = {POPUP_DISP_ITEM_NUMBER, POPUP_SQL_ITEM_NUMBER, null, FLG_ON_Y };
        Object[] whereObj2 = {POPUP_DISPLAY_NAME, POPUP_SQL_ITEM_NM, null, FLG_ON_Y };
        Object[] whereObj3 = {POPUP_DISP_SUPD, POPUP_SQL_SUPD, null, FLG_ON_Y };
        Object[] whereObj4 = {POPUP_DISPLAY_NAME, POPUP_SQL_SUPD_NM, null, FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);
        whereList.add(whereObj3);
        whereList.add(whereObj4);
        //UPDATE END 12/11/2015
        return whereList;
    }

    private List<Object> getDisplayColumnSetting(NMAL0170BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();

        //UPDATE START 12/11/2015
        Object[] colObj1 = {POPUP_DISP_ITEM_NUMBER, POPUP_SQL_ITEM_NUMBER, POPUP_CODE_WIDTH, FLG_ON_Y };
        Object[] colObj2 = {POPUP_DISPLAY_NAME, POPUP_SQL_ITEM_NM, POPUP_NAME_WIDTH, FLG_OFF_N };
        Object[] colObj3 = {POPUP_DISP_SUPD, POPUP_SQL_SUPD, POPUP_CODE_WIDTH, FLG_ON_Y };
        Object[] colObj4 = {POPUP_DISPLAY_NAME, POPUP_SQL_SUPD_NM, POPUP_NAME_WIDTH, FLG_OFF_N };
        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);
        colList.add(colObj4);
        // UPDATE END 12/11/2015

        return colList;
    }

    private List<Object> getSortSetting(NMAL0170BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();

        // UPDATE START 12/11/2015
        Object[] sortObj1 = {POPUP_SQL_ITEM_NUMBER, POPUP_SORT_KEY_ASC };
        // UPDATE END 12/11/2015

        sortList.add(sortObj1);

        return sortList;
    }

    private String getSelectSQL(String itemNumber) {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ");
        sb.append("      SU.GLBL_CMPY_CD ");
        sb.append("    , SU.EZCANCELFLAG ");
        sb.append("    , SU.SUPD_TO_MDSE_CD ");
        sb.append("    , DT.MDSE_DESC_SHORT_TXT AS DT_MDSE_TXT");
        sb.append("    , SU.SUPD_FROM_MDSE_CD ");
        sb.append("    , DF.MDSE_DESC_SHORT_TXT AS DF_MDSE_TXT");
        sb.append(" FROM ");
        sb.append("      SUPD_RELN_STAGE SU ");
        sb.append("    , MDSE            DT ");
        sb.append("    , MDSE            DF ");
        sb.append(" WHERE  ");
        sb.append("       SU.EZCANCELFLAG = '0' ");
        sb.append("   AND SU.GLBL_CMPY_CD = '#glblCmpyCd#' ");
        sb.append("   AND SU.SUBMT_FLG = 'N' ");
        sb.append("   AND DT.EZCANCELFLAG = SU.EZCANCELFLAG ");
        sb.append("   AND DT.GLBL_CMPY_CD = SU.GLBL_CMPY_CD ");
        sb.append("   AND DT.MDSE_CD      = SU.SUPD_TO_MDSE_CD  ");
        sb.append("   AND DF.EZCANCELFLAG = SU.EZCANCELFLAG ");
        sb.append("   AND DF.GLBL_CMPY_CD = SU.GLBL_CMPY_CD ");
        sb.append("   AND DF.MDSE_CD      = SU.SUPD_FROM_MDSE_CD  ");
        String sql = sb.toString();
        sql = sql.replaceAll("#glblCmpyCd#", getGlobalCompanyCode());
        sql = sql.replaceAll("#itemNumber#", itemNumber);
        return sql;
    }
}
