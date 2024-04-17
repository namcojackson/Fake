/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1530;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1530Scrn00_OpenWin_Ser
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Fujitsu         S.Ohki          Create          N/A
 * 2016/02/17   Fujitsu         M.suzuki        Update          S21_NA#3555
 * 2016/02/23   Fujitsu         M.suzuki        Update          S21_NA#1975
 *</pre>
 */
public class NWAL1530Scrn00_OpenWin_Ser extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
 // 2016/02/17 S21_NA#3555 Mod Start --------------
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1530BMsg scrnMsg = (NWAL1530BMsg) bMsg;

        scrnMsg.P.no(0).xxPopPrm_P.clear();
        scrnMsg.P.no(1).xxPopPrm_P.clear();

        NWAL1530_BBMsg bbMsg = scrnMsg.B.no(getButtonSelectNumber());
        // Set Params
        Object[] params = new Object[7];
        params[0] = scrnMsg.P.no(0).xxPopPrm_P.getValue();
        params[1] = "Show Serial Popup";
        params[2] = getSql(bbMsg);

        // Set Where List
        List<Object[]> whereList = new ArrayList<Object[]>();
        params[3] = whereList;

        // Set Column List
        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "SO Number";
        columnArray0[1] = "SO_NUM";
        columnArray0[2] = BigDecimal.valueOf(30);
        columnArray0[3] = FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Serail Number";
        columnArray1[1] = "SER_NUM";
        columnArray1[2] = BigDecimal.valueOf(30);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        params[4] = columnList;

        // Set Sort Condition List
        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "SER_NUM";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        params[5] = sortConditionList;
        setArgForSubScreen(params);
    }
    private String getSql(NWAL1530_BBMsg bbMsg) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT");
        sb.append("    SS.GLBL_CMPY_CD");
        sb.append("   ,SS.EZCANCELFLAG");
        sb.append("   ,SS.SO_NUM");
        sb.append("   ,SS.SER_NUM ");
        sb.append("FROM");

        if (Integer.parseInt(SHPG_STS.SHIPPED) > Integer.parseInt(bbMsg.shpgStsCd_B.getValue())) {
            sb.append("    SO_SER SS");
        } else {
            sb.append("    SHIP_SER_NUM SS");
        }
        sb.append(" WHERE");
        sb.append("        SS.GLBL_CMPY_CD        = '").append(getGlobalCompanyCode()).append("'");
        sb.append("    AND SS.SO_NUM              = '").append(bbMsg.soNum_B.getValue()).append("'");
        sb.append("    AND SS.SO_SLP_NUM          = '").append(bbMsg.soSlpNum_B.getValue()).append("'"); //2016/02/19 S21_NA#3555 Add
        sb.append("    AND SS.EZCANCELFLAG        = '0'");

        return sb.toString();

    }
// 2016/02/17 S21_NA#3555 MOD END --------------
}
