/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1410;

import static business.servlet.NPAL1410.constant.NPAL1410Constant.EVENT_NAME_LOC_POPUP;

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
 * <pre>
 * Business ID : NPAL1410 Reman Workbench
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 4/05/2016   CITS       Yasushi Nomura       Create          N/A
 *</pre>
 */
public class NPAL1410Scrn00_OpenWin_Locator extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1410BMsg scrnMsg = (NPAL1410BMsg) bMsg;
        scrnMsg.eventNm.setValue(EVENT_NAME_LOC_POPUP);

        ZYPTableUtil.clear(scrnMsg.R);
        Object[] params = new Object[7];

        params[0] = "";
        params[1] = "Locator Search Popup";
        params[2] = "WH_LOCTR";

        // Where list
        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Warehosue Code";
        whereArray1[1] = "RTL_WH_CD";
        whereArray1[2] = scrnMsg.rmnfRtlWhCd_H1.getValue();
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Locator";
        whereArray2[1] = "WH_LOCTR_CD";
        whereArray2[2] = scrnMsg.whLoctrCd_H1.getValue();
        whereArray2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        params[3] = whereList;

        // Column list
        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Warehosue Code";
        columnArray1[1] = "RTL_WH_CD";
        columnArray1[2] = BigDecimal.valueOf(20);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Locator";
        columnArray2[1] = "WH_LOCTR_CD";
        columnArray2[2] = BigDecimal.valueOf(20);
        columnArray2[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray2);

        params[4] = columnList;

        // Sort list
        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "RTL_WH_CD";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[2];
        sortConditionArray2[0] = "WH_LOCTR_CD";
        sortConditionArray2[1] = "ASC";
        sortConditionList.add(sortConditionArray2);

        params[5] = sortConditionList;
        params[6] = scrnMsg.R;

        setArgForSubScreen(params);
    }
}
