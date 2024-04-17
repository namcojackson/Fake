/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/24   CUSA            Y.Aikawa        Create          N/A
 * 2016/09/13   Hitachi         K.Kojima        Update          QC#12725
 *</pre>
 */
public class NFBL1120Scrn00_OpenWin_ApMaintInvApvlLimit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/09/13 K.Kojima [QC#12725,DEL]
        // NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;
        //
        // scrnMsg.addCheckItem(scrnMsg.apvrUsrId);
        //
        // scrnMsg.putErrorScreen();
        // END 2016/09/13 K.Kojima [QC#12725,DEL]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2016/09/13 K.Kojima [QC#12725,DEL]
        // NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;
        // scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        //
        // Object[] params = new Object[7];
        // params[0] = "";
        // params[1] = "Approver Search";
        //
        // StringBuilder sb = new StringBuilder();
        // sb.append("SELECT ");
        // sb.append("  AMIAL.EZCANCELFLAG                        AS EZCANCELFLAG ");
        // sb.append(", AMIAL.GLBL_CMPY_CD                        AS GLBL_CMPY_CD ");
        // sb.append(", SUBSTR(AMIAL.APVR_USR_ID,0,16)            AS APVR_USR_ID ");
        // sb.append(", SUBSTR(AMIAL.APVR_USR_NM,0,30)            AS APVR_USR_NM ");
        // sb.append("FROM ");
        // sb.append("  ( ");
        // sb.append("    SELECT ");
        // sb.append("      EZCANCELFLAG                          AS EZCANCELFLAG ");
        // sb.append("    , GLBL_CMPY_CD                          AS GLBL_CMPY_CD ");
        // sb.append("    , USR_NM                                AS APVR_USR_ID ");
        // sb.append("    , MAX(LAST_NM) || ', ' || MAX(FIRST_NM) AS APVR_USR_NM ");
        // sb.append("    FROM ");
        // sb.append("      AP_MAINT_INV_APVL_LIMIT ");
        // sb.append("    WHERE ");
        // sb.append("        EZCANCELFLAG   = '0' ");
        // sb.append("    AND GLBL_CMPY_CD   = '").append(getGlobalCompanyCode()).append("' ");
        // sb.append("    GROUP BY ");
        // sb.append("      EZCANCELFLAG ");
        // sb.append("    , GLBL_CMPY_CD ");
        // sb.append("    , USR_NM ");
        // sb.append("  ) AMIAL ");
        // sb.append("WHERE ");
        // sb.append("    AMIAL.EZCANCELFLAG   = '0' ");
        // sb.append("AND AMIAL.GLBL_CMPY_CD   = '").append(getGlobalCompanyCode()).append("' ");
        //
        // params[2] = sb.toString();
        //
        // List<Object[]> whereList = new ArrayList<Object[]>();
        //
        // Object[] whereArray0 = new Object[4];
        // whereArray0[0] = "Approver ID";
        // whereArray0[1] = "APVR_USR_ID";
        // whereArray0[2] = scrnMsg.apvrUsrId.getValue();
        // whereArray0[3] = "Y";
        // whereList.add(whereArray0);
        //
        // Object[] whereArray1 = new Object[4];
        // whereArray1[0] = "Approver Name";
        // whereArray1[1] = "APVR_USR_NM";
        // whereArray1[2] = scrnMsg.apvrUsrNm.getValue();
        // whereArray1[3] = "Y";
        // whereList.add(whereArray1);
        //
        // params[3] = whereList;
        //
        // List<Object[]> columnList = new ArrayList<Object[]>();
        //
        // Object[] columnArray0 = new Object[4];
        // columnArray0[0] = "Approver ID";
        // columnArray0[1] = "APVR_USR_ID";
        // columnArray0[2] = BigDecimal.valueOf(30);
        // columnArray0[3] = "Y";
        // columnList.add(columnArray0);
        //
        // Object[] columnArray1 = new Object[4];
        // columnArray1[0] = "Approver Name";
        // columnArray1[1] = "APVR_USR_NM";
        // columnArray1[2] = BigDecimal.valueOf(62);
        // columnArray1[3] = "N";
        // columnList.add(columnArray1);
        //
        // params[4] = columnList;
        //
        // List<Object[]> sortConditionList = new
        // ArrayList<Object[]>();
        //
        // Object[] sortConditionArray0 = new Object[2];
        // sortConditionArray0[0] = "APVR_USR_ID";
        // sortConditionArray0[1] = "ASC";
        // sortConditionList.add(sortConditionArray0);
        //
        // params[5] = sortConditionList;
        //
        // ZYPTableUtil.clear(scrnMsg.Z);
        // params[6] = scrnMsg.Z;
        //
        // setArgForSubScreen(params);
        // END 2016/09/13 K.Kojima [QC#12725,DEL]
    }
}
