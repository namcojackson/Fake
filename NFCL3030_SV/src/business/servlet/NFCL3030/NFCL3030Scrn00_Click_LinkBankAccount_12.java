/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3030;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3030.NFCL3030CMsg;
//import business.servlet.NFCL3030.constant.NFCL3030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BANK_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/16   Fujitsu         T.Tanaka        Create          N/A
 *</pre>
 */
public class NFCL3030Scrn00_Click_LinkBankAccount_12 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;

        //NFCL3030CMsg bizMsg = new NFCL3030CMsg();
        //bizMsg.setBusinessID("NFCL3030");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue("Click_LinkBankAccount_12");

        scrnMsg.dsBankAcctTpCd.setValue(DS_BANK_ACCT_TP.INTERNAL);
       
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Bank Account Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    A.GLBL_CMPY_CD");
        sb.append("   ,A.EZCANCELFLAG");
        sb.append("   ,A.DS_BANK_ACCT_NM ");
        sb.append("   ,B.DS_BANK_BR_NM ");
        sb.append("   ,B.BANK_RTE_NUM ");
        sb.append("   ,A.DS_BANK_ACCT_NUM ");
        sb.append("   ,A.DS_BANK_ACCT_PK ");
        sb.append("FROM");
        sb.append("    DS_BANK_ACCT A ");
        sb.append("   ,DS_BANK_BR   B ");
        sb.append("WHERE");
        sb.append("        A.GLBL_CMPY_CD                  = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND A.EFF_FROM_DT                  <= '").append(scrnMsg.procDt.getValue()).append("'");
        sb.append("    AND NVL(A.EFF_THRU_DT, '99991231') >= '").append(scrnMsg.procDt.getValue()).append("'");
        sb.append("    AND A.DS_BANK_ACCT_TP_CD            = '").append(scrnMsg.dsBankAcctTpCd.getValue()).append("'");
        sb.append("    AND A.GLBL_CMPY_CD  = B.GLBL_CMPY_CD");
        sb.append("    AND A.DS_BANK_BR_PK = B.DS_BANK_BR_PK");
        sb.append("    AND A.EZCANCELFLAG                  = '0'");
        sb.append("    AND B.EZCANCELFLAG                  = '0'");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Bank Name";
        whereArray0[1] = "DS_BANK_ACCT_NM";
        whereArray0[2] = scrnMsg.dsBankAcctNm_H1.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Branch Name";
        whereArray1[1] = "DS_BANK_BR_NM";
        whereArray1[2] = scrnMsg.dsBankBrNm_H1.getValue();
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Routing Number";
        whereArray2[1] = "BANK_RTE_NUM";
        whereArray2[2] = scrnMsg.bankRteNum_H1.getValue();
        whereArray2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[4];
        whereArray3[0] = "Account Number";
        whereArray3[1] = "DS_BANK_ACCT_NUM";
        whereArray3[2] = scrnMsg.dsBankAcctNum_H1.getValue();
        whereArray3[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Bank Name";
        columnArray0[1] = "DS_BANK_ACCT_NM";
        columnArray0[2] = BigDecimal.valueOf(30);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Branch Name";
        columnArray1[1] = "DS_BANK_BR_NM";
        columnArray1[2] = BigDecimal.valueOf(30);
        columnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Routing Number";
        columnArray2[1] = "BANK_RTE_NUM";
        columnArray2[2] = BigDecimal.valueOf(10);
        columnArray2[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Account Number";
        columnArray3[1] = "DS_BANK_ACCT_NUM";
        columnArray3[2] = BigDecimal.valueOf(14);
        columnArray3[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "PK";
        columnArray4[1] = "DS_BANK_ACCT_PK";
        columnArray4[2] = BigDecimal.valueOf(28);
        columnArray4[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        params[4] = columnList;


        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "DS_BANK_ACCT_NM";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[6] = scrnMsg.P;

        setArgForSubScreen(params);

    }
}
