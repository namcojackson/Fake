/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3130;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFCL3130.NFCL3130CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NFCL3130Scrn00_Click_rcptSrc extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3130BMsg scrnMsg = (NFCL3130BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3130BMsg scrnMsg = (NFCL3130BMsg) bMsg;
        Object[] params = setPopupParameter(scrnMsg);
        setArgForSubScreen(params);
    }
    

    private Object[] setPopupParameter(NFCL3130BMsg scrnMsg) {

        scrnMsg.xxScrEventNm.setValue("Click_rcptScr");
        scrnMsg.P.clear();
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Credit Receipt Source Popup";

        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT");
        sb.append("        INLINE1.EZCANCELFLAG");
        sb.append("       ,INLINE1.GLBL_CMPY_CD");
        sb.append("       ,INLINE1.AR_RCPT_SRC_CD");
        sb.append("       ,INLINE1.AR_RCPT_SRC_NM");
        sb.append("       ,INLINE1.AR_RCPT_SRC_SORT_NUM");
        sb.append("       ,INLINE1.AR_RCPT_SRC_DESC_TXT");
        sb.append(" FROM  AR_RCPT_SRC INLINE1");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Receipt Source Code";
        whereArray0[1] = "AR_RCPT_SRC_CD";
        whereArray0[2] = "";
        whereArray0[3] = "Y";
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Receipt Source Name";
        whereArray1[1] = "AR_RCPT_SRC_NM";
        whereArray1[2] = "";
        whereArray1[3] = "Y";
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Sort Number";
        whereArray2[1] = "AR_RCPT_SRC_SORT_NUM";
        whereArray2[2] = "";
        whereArray2[3] = "Y";
        whereList.add(whereArray2);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Receipt Source Code";
        columnArray0[1] = "AR_RCPT_SRC_CD";
        columnArray0[2] = BigDecimal.valueOf(10);
        columnArray0[3] = "Y";
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Receipt Source Name";
        columnArray1[1] = "AR_RCPT_SRC_NM";
        columnArray1[2] = BigDecimal.valueOf(30);
        columnArray1[3] = "N";
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Sort Number";
        columnArray2[1] = "AR_RCPT_SRC_SORT_NUM";
        columnArray2[2] = BigDecimal.valueOf(10);
        columnArray2[3] = "N";
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Description";
        columnArray3[1] = "AR_RCPT_SRC_DESC_TXT";
        columnArray3[2] = BigDecimal.valueOf(50);
        columnArray3[3] = "N";
        columnList.add(columnArray3);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "AR_RCPT_SRC_SORT_NUM";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);
        params[5] = sortConditionList;

        params[6] = scrnMsg.Q;

        return params;
    }
}
