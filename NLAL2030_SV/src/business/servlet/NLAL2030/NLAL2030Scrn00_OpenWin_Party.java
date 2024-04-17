/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2030;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2030.NLAL2030CMsg;
import business.servlet.NLAL2030.constant.NLAL2030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLAL2030Scrn00_OpenWin_Party
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NLAL2030Scrn00_OpenWin_Party extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;

        NLAL2030CMsg bizMsg = new NLAL2030CMsg();
        bizMsg.setBusinessID(NLAL2030Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;
        NLAL2030CMsg bizMsg = (NLAL2030CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Party Popup";
        params[2] = scrnMsg.xxPopSqlTxt.getValue();
        params[3] = getSearchConditionSetting(scrnMsg);
        params[4] = getDisplayColumnSetting(scrnMsg);
        params[5] = getSortSetting(scrnMsg);
        params[6] = scrnMsg.P;

        setArgForSubScreen(params);
    }

    private List<Object> getSearchConditionSetting(NLAL2030BMsg scrnMsg) {

        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {"Party Code", "RTL_WH_CD", scrnMsg.fromLocCd.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {"Party Name", "RTL_WH_NM", null, ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting(NLAL2030BMsg scrnMsg) {

        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {"Party Code", "RTL_WH_CD", new BigDecimal("15"), ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {"Party Name", "RTL_WH_NM", new BigDecimal("25"), ZYPConstant.FLG_OFF_N };
        Object[] colObj3 = {"Location Type", "LOC_TP_CD", new BigDecimal("10"), ZYPConstant.FLG_OFF_N };
        Object[] colObj4 = {"Location Name", "LOC_TP_NM", new BigDecimal("10"), ZYPConstant.FLG_OFF_N };
        Object[] colObj5 = {"Address", "LINE_ADDR", new BigDecimal("30"), ZYPConstant.FLG_OFF_N };
        Object[] colObj6 = {"City", "CTY_ADDR", new BigDecimal("10"), ZYPConstant.FLG_OFF_N };
        Object[] colObj7 = {"State", "ST_CD", new BigDecimal("5"), ZYPConstant.FLG_OFF_N };
        Object[] colObj8 = {"Post", "POST_CD", new BigDecimal("5"), ZYPConstant.FLG_OFF_N };

        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);
        colList.add(colObj4);
        colList.add(colObj5);
        colList.add(colObj6);
        colList.add(colObj7);
        colList.add(colObj8);

        return colList;
    }

    private List<Object> getSortSetting(NLAL2030BMsg scrnMsg) {

        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {"RTL_WH_CD", "ASC"};
        sortList.add(sortObj1);

        return sortList;
    }
}
