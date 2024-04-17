/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import static business.blap.NSAL0300.constant.NSAL0300Constant.BIZ_ID;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0300.common.NSAL0300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/02/20   Hitachi         R.Takau         Create          QC#55645
 * 2023/12/07   Hitachi         Y.Ogura         Update          QC#62173
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_BankAccountSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CommonLogic.clearPopupParam(scrnMsg);
        scrnMsg.xxScrEventNm.setValue("OpenWin_BankAccountSearch");

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Bank Account Search";
        params[2] = getSelectSQL(scrnMsg);
        params[3] = getWhereList(scrnMsg);    
        params[4] = getColumnList(scrnMsg);

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "DS_BANK_ACCT_NM";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);
        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.R);
        params[6] = scrnMsg.R;

        setArgForSubScreen(params);
    }

    private String getSelectSQL(NSAL0300BMsg scrnMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BIZ_ID);
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("    X.GLBL_CMPY_CD ");
        sb.append("   ,X.EZCANCELFLAG ");
        sb.append("   ,X.DS_BANK_ACCT_NM ");
        sb.append("   ,X.DS_BANK_ACCT_NUM ");
        sb.append("   ,X.BANK_RTE_NUM ");
        sb.append("   ,X.MASK_BANK_ACCT_NUM ");
        sb.append("   ,X.DS_CUST_BANK_ACCT_RELN_PK ");
        sb.append("FROM ");
        sb.append("    ( ");
        sb.append("    SELECT ");
        sb.append("        A.GLBL_CMPY_CD ");
        sb.append("       ,A.EZCANCELFLAG ");
        sb.append("       ,A.DS_BANK_ACCT_NM ");
        sb.append("       ,A.DS_BANK_ACCT_NUM ");
        sb.append("       ,A.BANK_RTE_NUM ");
        sb.append("       ,CASE WHEN LENGTH(A.DS_BANK_ACCT_NUM) <= 4 ");
        sb.append("          THEN REGEXP_REPLACE(A.DS_BANK_ACCT_NUM,'.','*') ");
        sb.append("          ELSE REGEXP_REPLACE(SUBSTR(A.DS_BANK_ACCT_NUM, 1, LENGTH(A.DS_BANK_ACCT_NUM) - 4),'.','*') ");
        sb.append("                 || SUBSTR(A.DS_BANK_ACCT_NUM, LENGTH(A.DS_BANK_ACCT_NUM) - 4 + 1) ");
        sb.append("        END AS MASK_BANK_ACCT_NUM ");
        sb.append("       ,B.DS_CUST_BANK_ACCT_RELN_PK ");
        sb.append("       ,ROW_NUMBER() OVER (PARTITION BY A.DS_BANK_ACCT_NM, A.DS_BANK_ACCT_NUM, A.DS_BANK_MICR_NUM, C.SELL_TO_CUST_CD ");
        sb.append("                               ORDER BY A.EFF_FROM_DT DESC, B.DS_CUST_BANK_ACCT_RELN_PK DESC) AS LVL ");
        sb.append("    FROM ");
        sb.append("        DS_BANK_ACCT A ");
        sb.append("       ,DS_CUST_BANK_ACCT_RELN B ");
        sb.append("       ,SELL_TO_CUST C ");
        sb.append("    WHERE");
        sb.append("            A.GLBL_CMPY_CD                  = 'ADB' ");
        sb.append("        AND A.EFF_FROM_DT                  <= '"+ slsDt + "'");
        sb.append("        AND NVL(A.EFF_THRU_DT, '"+ slsDt + "') >= '"+ slsDt + "'");
        sb.append("        AND A.EZCANCELFLAG                  = '0' ");
        sb.append("        AND A.GLBL_CMPY_CD                  = B.GLBL_CMPY_CD ");
        sb.append("        AND A.DS_BANK_ACCT_PK               = B.DS_BANK_ACCT_PK ");
        sb.append("        AND B.EZCANCELFLAG                  = '0' ");
        sb.append("        AND B.GLBL_CMPY_CD                  = C.GLBL_CMPY_CD ");
        sb.append("        AND B.DS_ACCT_CUST_PK               = C.SELL_TO_CUST_PK ");
        // START 2023/11/16 Y.Ogura [QC#62010, MOD]
//        sb.append("        AND C.SELL_TO_CUST_CD               = '" + scrnMsg.dsAcctNum.getValue() +"'");
        sb.append("        AND C.SELL_TO_CUST_CD               = '" + scrnMsg.sellToCustCd.getValue() +"'");
        // END 2023/11/16 Y.Ogura [QC#62010, MOD]
        sb.append("        AND C.EZCANCELFLAG                  = '0' ");
        sb.append("    ) X ");
        sb.append("WHERE X.LVL = 1 ");

        String sql = sb.toString();

        return sql;
    }

    private  List<Object[]> getWhereList(NSAL0300BMsg scrnMsg){
        
        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Bank Name";
        whereArray0[1] = "DS_BANK_ACCT_NM";
        whereArray0[2] = null;
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Routing Number";
        whereArray2[1] = "BANK_RTE_NUM";
        whereArray2[2] = scrnMsg.bankRteNum.getValue();
        whereArray2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[4];
        whereArray3[0] = "Account Number";
        whereArray3[1] = "DS_BANK_ACCT_NUM";
        whereArray3[2] = scrnMsg.dsBankAcctNum.getValue();
        whereArray3[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);

        return whereList;
    }
    
    private  List<Object[]> getColumnList(NSAL0300BMsg scrnMsg){
        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Bank Name";
        columnArray0[1] = "DS_BANK_ACCT_NM";
        columnArray0[2] = BigDecimal.valueOf(30);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Routing Number";
        columnArray2[1] = "BANK_RTE_NUM";
        columnArray2[2] = BigDecimal.valueOf(25);
        columnArray2[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Account Number";
        columnArray3[1] = "MASK_BANK_ACCT_NUM";
        columnArray3[2] = BigDecimal.valueOf(25);
        columnArray3[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "PK";
        columnArray4[1] = "DS_CUST_BANK_ACCT_RELN_PK";
        columnArray4[2] = BigDecimal.valueOf(0);
        columnArray4[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        return columnList;
    }
}
