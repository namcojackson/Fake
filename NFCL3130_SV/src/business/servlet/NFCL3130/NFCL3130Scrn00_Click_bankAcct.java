/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3130;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3130.NFCL3130CMsg;
//import business.servlet.NFCL3130.constant.NFCL3130Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NFCL3130Scrn00_Click_bankAcct extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3130BMsg scrnMsg = (NFCL3130BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3130BMsg scrnMsg = (NFCL3130BMsg) bMsg;

        //NFCL3130CMsg bizMsg = new NFCL3130CMsg();
        //bizMsg.setBusinessID("NFCL3130");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

         NFCL3130BMsg scrnMsg = (NFCL3130BMsg) bMsg;
         Object[] params = setPopupParameter(scrnMsg);
         setArgForSubScreen(params);

    }
    

    private Object[] setPopupParameter(NFCL3130BMsg scrnMsg) {

        scrnMsg.xxScrEventNm.setValue("Click_bankAcct");
        scrnMsg.Q.clear();
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Owner Search Popup";

        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT");
        sb.append("        INLINE1.EZCANCELFLAG");
        sb.append("       ,INLINE1.GLBL_CMPY_CD");
        sb.append("       ,INLINE1.DS_BANK_ACCT_PK");
        sb.append("       ,INLINE1.DS_BANK_ACCT_NUM");
        sb.append("       ,INLINE1.DS_BANK_ACCT_NM");
        sb.append("   FROM DS_BANK_ACCT INLINE1");
        sb.append("  WHERE INLINE1.DS_BANK_ACCT_TP_CD = '02'");
        sb.append("    AND '" + ZYPDateUtil.getSalesDate() + "' BETWEEN INLINE1.EFF_FROM_DT AND DECODE(INLINE1.EFF_THRU_DT, NULL, '99991231')");
        sb.append("    AND (INLINE1.AR_RCPT_SRC_CD IS NULL OR AR_RCPT_SRC_CD <> '" + scrnMsg.arRcptSrcCd.getValue() + "')");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Bank Account Number";
        whereArray0[1] = "DS_BANK_ACCT_NUM";
        whereArray0[2] = "";
        whereArray0[3] = "Y";
        whereList.add(whereArray0);
        
        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Bank Account Name";
        whereArray1[1] = "DS_BANK_ACCT_NM";
        whereArray1[2] = "";
        whereArray1[3] = "Y";
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Bank Account Number";
        columnArray0[1] = "DS_BANK_ACCT_NUM";
        columnArray0[2] = BigDecimal.valueOf(14);
        columnArray0[3] = "Y";
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Bank Account Name";
        columnArray1[1] = "DS_BANK_ACCT_NM";
        columnArray1[2] = BigDecimal.valueOf(80);
        columnArray1[3] = "N";
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Bank Account Pk";
        columnArray2[1] = "DS_BANK_ACCT_PK";
        columnArray2[2] = BigDecimal.valueOf(28);
        columnArray2[3] = "N";
        columnList.add(columnArray2);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "DS_BANK_ACCT_NUM";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);
        params[5] = sortConditionList;

        params[6] = scrnMsg.Q;

        return params;
    }
}
