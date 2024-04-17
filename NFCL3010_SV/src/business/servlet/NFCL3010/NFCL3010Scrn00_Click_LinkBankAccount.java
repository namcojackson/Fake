/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3010;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3010.NFCL3010CMsg;
//import business.servlet.NFCL3010.constant.NFCL3010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/11   Fujitsu         T.Tanaka        Create          N/A
 *</pre>
 */
public class NFCL3010Scrn00_Click_LinkBankAccount extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;

        //NFCL3010CMsg bizMsg = new NFCL3010CMsg();
        //bizMsg.setBusinessID("NFCL3010");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;

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
        whereArray0[0] = "Bank Account Name";
        whereArray0[1] = "DS_BANK_ACCT_NM";
        whereArray0[2] = scrnMsg.dsBankAcctNm_H.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray0[0] = "Bank Branch Name";
        whereArray0[1] = "DS_BANK_BR_NM";
        whereArray0[2] = scrnMsg.dsBankBrNm_H.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray0[0] = "Routing Number";
        whereArray0[1] = "BANK_RTE_NUM";
        whereArray0[2] = scrnMsg.bankRteNum_H.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[4];
        whereArray0[0] = "Bank Account Number";
        whereArray0[1] = "DS_BANK_ACCT_NUM";
        whereArray0[2] = scrnMsg.dsBankAcctNum_H.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);

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
