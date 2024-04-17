/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0200;

import static business.servlet.NMAL0200.constant.NMAL0200Constant.ASC;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.COL_DISP_NAME1;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.COL_DISP_NAME2;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.COL_NAME1;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.COL_NAME2;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.IDX_0;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.IDX_1;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.IDX_10;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.IDX_2;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.IDX_3;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.IDX_4;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.IDX_5;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.IDX_6;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.IDX_7;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.IDX_81;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.WHERE_DISP_NAME1;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.WHERE_DISP_NAME2;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.WINDOW_NAME;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NMAL0200 Product Categorization Maintenance
 * Function Name : OpenWin_Relation
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2016   CITS            K.Ogino         Create          N/A
 * 07/11/2017   CITS            S.Katsuma       Update          QC#19843
 *</pre>
 */
public class NMAL0200Scrn00_OpenWin_Relation extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL0200BMsg scrnMsg = (NMAL0200BMsg) bMsg;

        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());

        Object[] params = createNWAL1130Params(scrnMsg, getGlobalCompanyCode());
        setArgForSubScreen(params);
    }

    /**
     * Create OpenWin_Relation Parameter
     * @param scrnMsg NMAL0200BMsg
     * @return Object[]
     */
    private static Object[] createNWAL1130Params(NMAL0200BMsg scrnMsg, String glblCmpyCd) {

        // Parameter Clear
        ZYPTableUtil.clear(scrnMsg.R);

        // Parameter Set
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = WINDOW_NAME;

        // START 2017/07/11 S.Katsuma [QC#19843]
        // params[IDX_2] = SELECT_TABLE_NAME;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("  PC.EZCANCELFLAG        AS EZCANCELFLAG ");
        sb.append(", PC.GLBL_CMPY_CD        AS GLBL_CMPY_CD ");
        sb.append(", PC.PROD_CTRL_CD        AS PROD_CTRL_CD ");
        sb.append(", PC.PROD_CTRL_NM        AS PROD_CTRL_NM ");
        sb.append("FROM ");
        sb.append("  PROD_CTRL PC ");
        sb.append("WHERE ");
        sb.append("    PC.EZCANCELFLAG            = '0' ");
        sb.append("AND PC.GLBL_CMPY_CD            = '").append(glblCmpyCd).append("' ");
        sb.append("AND PC.MDSE_STRU_ELMNT_TP_CD   = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2).append("' ");
        params[IDX_2] = sb.toString();
        // END 2017/07/11 S.Katsuma [QC#19843]

        List<Object> whereList = new ArrayList<Object>(IDX_2);
        Object[] whereObj1 = {WHERE_DISP_NAME1, COL_NAME1, scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).scdProdHrchCd_A1.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {WHERE_DISP_NAME2, COL_NAME2, null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj1);
        whereList.add(whereObj2);
        params[IDX_3] = whereList;

        List<Object> colList = new ArrayList<Object>(IDX_2);
        Object[] colObj1 = {COL_DISP_NAME1, COL_NAME1, BigDecimal.valueOf(IDX_10), ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {COL_DISP_NAME2, COL_NAME2, BigDecimal.valueOf(IDX_81), ZYPConstant.FLG_OFF_N };
        colList.add(colObj1);
        colList.add(colObj2);
        params[IDX_4] = colList;

        List<Object> sortList = new ArrayList<Object>(IDX_1);
        Object[] sortObj1 = {COL_NAME2, ASC };

        sortList.add(sortObj1);

        params[IDX_5] = sortList;
        params[IDX_6] = scrnMsg.R;

        return params;
    }

}
