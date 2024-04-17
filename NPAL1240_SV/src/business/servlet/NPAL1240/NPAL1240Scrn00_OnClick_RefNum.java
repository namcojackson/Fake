/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1240;

import static business.servlet.NPAL1240.constant.NPAL1240Constant.ASL_QLFY_TP_CUSTOMER_SPECIFIC;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID   : NPAL1240 Qualifier Setup
 * Function Name : The business process for OnClick_Ref#.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/25   CITS            M.Ouchi         Create          N/A
 * </pre>
 */
public class NPAL1240Scrn00_OnClick_RefNum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1240BMsg scrnMsg = (NPAL1240BMsg) bMsg;

        // creates the parameters.
        Object[] params = createPopupParameter(scrnMsg);

        setArgForSubScreen(params);
    }

    /**
     * <p>
     * Creates the parameters for NWAL1130
     * </p>
     * @param scrnMsg BMsg
     * @return the parameters for NWAL1130
     */
    private Object[] createPopupParameter(NPAL1240BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.P);

        int selectRowIndex = getButtonSelectNumber();

        NPAL1240_ABMsg selectRowMsg = scrnMsg.A.no(selectRowIndex);

        Object[] params = new Object[7];

        if (ASL_QLFY_TP_CUSTOMER_SPECIFIC.equals(selectRowMsg.aslQlfyTpCd_A.getValue())) {

            params[0] = "";
            params[1] = "Customer# Popup";

            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT");
            sb.append("     SC.GLBL_CMPY_CD");
            sb.append("   , SC.EZCANCELFLAG");
            sb.append("   , DA.SELL_TO_CUST_CD");
            sb.append("   , DA.DS_ACCT_NM");
            sb.append("   , SC.SHIP_TO_CUST_CD");
            sb.append("   , SC.LOC_NM");
            sb.append(" FROM");
            sb.append("     SHIP_TO_CUST     SC");
            sb.append("   , SELL_TO_CUST     DA");
            sb.append(" WHERE");
            sb.append("     SC.GLBL_CMPY_CD    = DA.GLBL_CMPY_CD(+)");
            sb.append(" AND SC.SELL_TO_CUST_CD = DA.SELL_TO_CUST_CD(+)");
            sb.append(" AND SC.RGTN_STS_CD     = 'P20'");
            sb.append(" AND DA.RGTN_STS_CD     = 'P20'");
            sb.append(" AND SC.EZCANCELFLAG    = '0'");
            sb.append(" AND DA.EZCANCELFLAG    = '0'");
            params[2] = sb.toString();

            List<Object[]> whereList = new ArrayList<Object[]>(2);
            Object[] whereArray0 = new Object[4];
            whereArray0[0] = "Customer#";
            whereArray0[1] = "SHIP_TO_CUST_CD";
            whereArray0[2] = selectRowMsg.aslQlfyRefCd_A.getValue();
            whereArray0[3] = "Y";
            whereList.add(whereArray0);

            Object[] whereArray1 = new Object[4];
            whereArray1[0] = "Location Name";
            whereArray1[1] = "LOC_NM";
            whereArray1[2] = "";
            whereArray1[3] = "Y";
            whereList.add(whereArray1);

            params[3] = whereList;

            List<Object[]> columnList = new ArrayList<Object[]>();
            Object[] columnArray0 = new Object[4];
            columnArray0[0] = "Account Customer Code";
            columnArray0[1] = "SELL_TO_CUST_CD";
            columnArray0[2] = BigDecimal.valueOf(20);
            columnArray0[3] = "N";
            columnList.add(columnArray0);

            Object[] columnArray1 = new Object[4];
            columnArray1[0] = "Customer Name";
            columnArray1[1] = "DS_ACCT_NM";
            columnArray1[2] = BigDecimal.valueOf(30);
            columnArray1[3] = "N";
            columnList.add(columnArray1);
            
            Object[] columnArray2 = new Object[4];
            columnArray2[0] = "Customer#";
            columnArray2[1] = "SHIP_TO_CUST_CD";
            columnArray2[2] = BigDecimal.valueOf(10);
            columnArray2[3] = "Y";
            columnList.add(columnArray2);

            Object[] columnArray3 = new Object[4];
            columnArray3[0] = "Location Name";
            columnArray3[1] = "LOC_NM";
            columnArray3[2] = BigDecimal.valueOf(30);
            columnArray3[3] = "N";
            columnList.add(columnArray3);

            params[4] = columnList;

            List<Object[]> sortConditionList = new ArrayList<Object[]>();

            Object[] sortConditionArray0 = new Object[2];
            sortConditionArray0[0] = "SHIP_TO_CUST_CD";
            sortConditionArray0[1] = "ASC";
            sortConditionList.add(sortConditionArray0);
            params[5] = sortConditionList;

            params[6] = scrnMsg.P;

        } else {

            params[0] = "";
            params[1] = "Big Deal Customer Search Popup";

            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT");
            sb.append("     SC.GLBL_CMPY_CD");
            sb.append("   , SC.EZCANCELFLAG");
            sb.append("   , DA.SELL_TO_CUST_CD");
            sb.append("   , DA.DS_ACCT_NM");
            sb.append("   , SC.SHIP_TO_CUST_CD");
            sb.append("   , SC.LOC_NM");
            sb.append("   , SC.BIG_DEAL_NUM");
            sb.append(" FROM");
            sb.append("     SHIP_TO_CUST     SC");
            sb.append("   , SELL_TO_CUST     DA");
            sb.append(" WHERE");
            sb.append("     SC.GLBL_CMPY_CD    = DA.GLBL_CMPY_CD(+)");
            sb.append(" AND SC.SELL_TO_CUST_CD = DA.SELL_TO_CUST_CD(+)");
            sb.append(" AND SC.RGTN_STS_CD     = 'P20'");
            sb.append(" AND DA.RGTN_STS_CD     = 'P20'");
            sb.append(" AND SC.EZCANCELFLAG    = '0'");
            sb.append(" AND DA.EZCANCELFLAG    = '0'");

            params[2] = sb.toString();

            List<Object[]> whereList = new ArrayList<Object[]>(2);
            Object[] whereArray0 = new Object[4];
            whereArray0[0] = "Big Deal Customer#";
            whereArray0[1] = "BIG_DEAL_NUM";
            whereArray0[2] = selectRowMsg.aslQlfyRefCd_A.getValue();
            whereArray0[3] = "N";
            whereList.add(whereArray0);

            Object[] whereArray1 = new Object[4];
            whereArray1[0] = "Location Name";
            whereArray1[1] = "LOC_NM";
            whereArray1[2] = "";
            whereArray1[3] = "Y";
            whereList.add(whereArray1);

            params[3] = whereList;

            List<Object[]> columnList = new ArrayList<Object[]>();
            Object[] columnArray0 = new Object[4];
            columnArray0[0] = "Account Customer Code";
            columnArray0[1] = "SELL_TO_CUST_CD";
            columnArray0[2] = BigDecimal.valueOf(20);
            columnArray0[3] = "N";
            columnList.add(columnArray0);

            Object[] columnArray1 = new Object[4];
            columnArray1[0] = "Customer Name";
            columnArray1[1] = "DS_ACCT_NM";
            columnArray1[2] = BigDecimal.valueOf(20);
            columnArray1[3] = "N";
            columnList.add(columnArray1);

            Object[] columnArray2 = new Object[4];
            columnArray2[0] = "Ship to Customer Code";
            columnArray2[1] = "SHIP_TO_CUST_CD";
            columnArray2[2] = BigDecimal.valueOf(20);
            columnArray2[3] = "N";
            columnList.add(columnArray2);

            Object[] columnArray3 = new Object[4];
            columnArray3[0] = "Location Name";
            columnArray3[1] = "LOC_NM";
            columnArray3[2] = BigDecimal.valueOf(15);
            columnArray3[3] = "N";
            columnList.add(columnArray3);

            Object[] columnArray4 = new Object[4];
            columnArray4[0] = "Big Deal Customer#";
            columnArray4[1] = "BIG_DEAL_NUM";
            columnArray4[2] = BigDecimal.valueOf(15);
            columnArray4[3] = "Y";
            columnList.add(columnArray4);

            params[4] = columnList;

            List<Object[]> sortConditionList = new ArrayList<Object[]>();

            Object[] sortConditionArray0 = new Object[2];
            sortConditionArray0[0] = "SELL_TO_CUST_CD";
            sortConditionArray0[1] = "ASC";
            sortConditionList.add(sortConditionArray0);
            params[5] = sortConditionList;

            params[6] = scrnMsg.P;
        }
        return params;
    }
}
