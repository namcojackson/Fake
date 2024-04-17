/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0100;

import static business.servlet.NWCL0100.constant.NWCL0100Constant.POPUP_PRM_COLUMN_WIDTH_CD;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.POPUP_PRM_COLUMN_WIDTH_NM;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.POPUP_PRM_DISP_NAME_CD;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.POPUP_PRM_DISP_NAME_NM;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.POPUP_PRM_SORT_KEY_ASC;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.POPUP_PRM_SQL_NAME_NM;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.POPUP_PRM_SQL_NAME_NUM;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.POPUP_PRM_SUFFIX;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.POPUP_PRM_TABLE_NAME;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.POPUP_PRM_WIN_TITLE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWCL0100Scrn00_OpenWin_MultiBillToAcct
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/23   Fujitsu         S.Ohki          Create          N/A
 * 2016/07/11   Fujitsu         N.Sugiura       Update          QC#8182
 *</pre>
 */
public class NWCL0100Scrn00_OpenWin_MultiBillToAcct extends S21CommonHandler {

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

        NWCL0100BMsg scrnMsg = (NWCL0100BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.P);

        Object[] params = new Object[7];
        params[0] = POPUP_PRM_SUFFIX;
        params[1] = POPUP_PRM_WIN_TITLE;
        params[2] = POPUP_PRM_TABLE_NAME;
        params[3] = getSearchConditionSetting();
        params[4] = getDisplayColumnSetting();
        params[5] = getSortSetting();
        // 2016/07/11 QC#8182 Add Start
        String[] mdlNmList = scrnMsg.xxBillToAcctCdSrchTxt_H0.getValue().split(",", 0);
        for (int i = 0; i < mdlNmList.length; i++) {
            scrnMsg.P.no(i).xxComnScrColValTxt_1.setValue(mdlNmList[i]);
        }
        scrnMsg.P.setValidCount(mdlNmList.length);
        // 2016/07/11 QC#8182 Add End
        params[6] = scrnMsg.P;

        setArgForSubScreen(params);
    }

    private List<Object> getSearchConditionSetting() {

        List<Object> whereList = new ArrayList<Object>();
        Object[] whereObj1 = {POPUP_PRM_DISP_NAME_NM, POPUP_PRM_SQL_NAME_NM, null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj1);

        Object[] whereObj2 = {POPUP_PRM_DISP_NAME_CD, POPUP_PRM_SQL_NAME_NUM, null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj2);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting() {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {POPUP_PRM_DISP_NAME_NM, POPUP_PRM_SQL_NAME_NM, new BigDecimal(POPUP_PRM_COLUMN_WIDTH_NM), ZYPConstant.FLG_OFF_N };
        colList.add(colObj1);

        Object[] colObj2 = {POPUP_PRM_DISP_NAME_CD, POPUP_PRM_SQL_NAME_NUM, new BigDecimal(POPUP_PRM_COLUMN_WIDTH_CD), ZYPConstant.FLG_OFF_N };
        colList.add(colObj2);

        return colList;
    }

    private List<Object> getSortSetting() {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {POPUP_PRM_SQL_NAME_NM, POPUP_PRM_SORT_KEY_ASC };

        sortList.add(sortObj1);

        return sortList;
    }
}
