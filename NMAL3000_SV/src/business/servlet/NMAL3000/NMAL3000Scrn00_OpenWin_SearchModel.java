/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3000;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.POP_WIN_TITLE;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.POP_TABLE_NM;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.POP_DISP_NM_MDL_CD;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.POP_SQL_COL_MDL_CD;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.POP_DISP_NM_MDL_NM;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.POP_SQL_COL_MDL_NM;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.POP_SORT_COL;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.POP_SORT_CONDITION;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.PARAM_SIZE_4;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.PARAM_SIZE_7;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.PARAM_SIZE_2;



/**
 *<pre>
 * NMAL3000Scrn00_OpenWin_SearchModel
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL3000Scrn00_OpenWin_SearchModel extends S21CommonHandler {

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

        NMAL3000BMsg scrnMsg = (NMAL3000BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.P);
        int index = getButtonSelectNumber();

        Object[] params = new Object[PARAM_SIZE_7];
        params[0] = "";
        params[1] = POP_WIN_TITLE;
        params[2] = POP_TABLE_NM;
        params[3] = getSearchConditionSetting(scrnMsg, index);
        params[4] = getDisplayColumnSetting();
        params[5] = getSortSetting();
        params[6] = scrnMsg.P;
        setArgForSubScreen(params);

    }

    private List<Object> getSearchConditionSetting(NMAL3000BMsg scrnMsg, int index) {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = new Object[PARAM_SIZE_4];
        whereObj1[0] = POP_DISP_NM_MDL_CD;
        whereObj1[1] = POP_SQL_COL_MDL_CD;
        whereObj1[2] = null;
        whereObj1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj1);
        Object[] whereObj2 = new Object[PARAM_SIZE_4];
        whereObj2[0] = POP_DISP_NM_MDL_NM;
        whereObj2[1] = POP_SQL_COL_MDL_NM;
        whereObj2[2] = null;
        whereObj2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj2);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting() {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = new Object[PARAM_SIZE_4];
        colObj1[0] = POP_DISP_NM_MDL_CD;
        colObj1[1] = POP_SQL_COL_MDL_CD;
        colObj1[2] = new BigDecimal("22");
        colObj1[3] = ZYPConstant.FLG_ON_Y;
        colList.add(colObj1);
        Object[] colObj2 = new Object[PARAM_SIZE_4];
        colObj2[0] = POP_DISP_NM_MDL_NM;
        colObj2[1] = POP_SQL_COL_MDL_NM;
        colObj2[2] = new BigDecimal("90");
        colObj2[3] = ZYPConstant.FLG_OFF_N;
        colList.add(colObj2);

        return colList;
    }

    private List<Object> getSortSetting() {
        List<Object> sortList = new ArrayList<Object>();
        Object[] sortObj1 = new Object[PARAM_SIZE_2];
        sortObj1[0] = POP_SORT_COL;
        sortObj1[1] = POP_SORT_CONDITION;
        sortList.add(sortObj1);
        return sortList;
    }
}
