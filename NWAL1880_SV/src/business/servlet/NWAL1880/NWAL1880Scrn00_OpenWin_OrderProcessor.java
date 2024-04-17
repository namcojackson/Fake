/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1880;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NWAL1880.NWAL1880CMsg;
//import business.servlet.NWAL1880.constant.NWAL1880Constant;

import business.servlet.NWAL1880.common.NWAL1880CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_TEAM_ATTRB_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWAL1880Scrn00_OpenWin_OrderProcessor extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1880BMsg scrnMsg = (NWAL1880BMsg) bMsg;
        // 1. check EZDeveloper item attribute values.
        NWAL1880CommonLogic.addCheckItemBizLayerErr(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1880BMsg scrnMsg = (NWAL1880BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        setArgForSubScreen(setPopupParameter(scrnMsg, getGlobalCompanyCode()));
    }
    private static Object[] setPopupParameter(NWAL1880BMsg scrnMsg, String glblCmpyCd) {
        Object[] param = new Object[7];
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(BMsg.R)

        suffixP0 = "";
        scrnNmP1 = "Order Processor Popup";

        tblNmP2.append("SELECT DISTINCT");
        tblNmP2.append("    AP.GLBL_CMPY_CD ");
        tblNmP2.append("    ,AP.EZCANCELFLAG ");
        tblNmP2.append("    ,AP.USR_NM ");
        tblNmP2.append("    ,AP.FIRST_NM ");
        tblNmP2.append("    ,AP.LAST_NM ");
        if (ZYPCommonFunc.hasValue(scrnMsg.xxOrdTeamSrchTxt)) {
            tblNmP2.append("    ,OTH.ORD_TEAM_MSTR_NM ");
        } else {
            tblNmP2.append("    ,NULL AS ORD_TEAM_MSTR_NM ");
        }
        tblNmP2.append("FROM ");
        tblNmP2.append("    AUTH_PSN  AP ");
        tblNmP2.append("    ,ORD_TEAM_MSTR_HDR  OTH ");
        tblNmP2.append("    ,ORD_TEAM_MSTR_DTL  OTD ");
        tblNmP2.append("WHERE ");
        tblNmP2.append("    AP.GLBL_CMPY_CD              = '").append(glblCmpyCd).append("'");
        tblNmP2.append("    AND AP.EZCANCELFLAG          = '0'");
        if (ZYPCommonFunc.hasValue(scrnMsg.xxOrdTeamSrchTxt)) {
            tblNmP2.append("    AND OTH.EZCANCELFLAG         = '0'");
            tblNmP2.append("    AND OTH.EFF_FROM_DT         <= '").append(scrnMsg.slsDt.getValue()).append("'");
            tblNmP2.append("    AND (OTH.EFF_THRU_DT        >= '").append(scrnMsg.slsDt.getValue()).append("' OR OTH.EFF_THRU_DT IS NULL)");
            tblNmP2.append("    AND OTH.GLBL_CMPY_CD         = OTD.GLBL_CMPY_CD");
            tblNmP2.append("    AND OTH.ORD_TEAM_MSTR_HDR_PK            = OTD.ORD_TEAM_MSTR_HDR_PK");
            tblNmP2.append("    AND OTD.EZCANCELFLAG         = '0'");
            tblNmP2.append("    AND OTD.ORD_TEAM_ATTRB_TP_CD = '").append(ORD_TEAM_ATTRB_TP.USER_ID).append("'");
            tblNmP2.append("    AND OTD.ORD_TEAM_ATTRB_VAL_TXT = AP.USR_NM");
        }

        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "User Name";
        whereArray0[1] = "USR_NM";
        whereArray0[2] = scrnMsg.xxPopPrm_P1.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray0);

        if (ZYPCommonFunc.hasValue(scrnMsg.xxOrdTeamSrchTxt)) {
            Object[] columnArray0 = new Object[4];
            columnArray0[0] = "Order Team Name";
            columnArray0[1] = "ORD_TEAM_MSTR_NM";
            columnArray0[2] = BigDecimal.valueOf(15);
            columnArray0[3] = ZYPConstant.FLG_OFF_N;
            columnListP4.add(columnArray0);
        }

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "User Name";
        columnArray1[1] = "USR_NM";
        columnArray1[2] = BigDecimal.valueOf(15);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnListP4.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "First Name";
        columnArray2[1] = "FIRST_NM";
        columnArray2[2] = BigDecimal.valueOf(15);
        columnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnListP4.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Last Name";
        columnArray3[1] = "LAST_NM";
        columnArray3[2] = BigDecimal.valueOf(15);
        columnArray3[3] = ZYPConstant.FLG_OFF_N;
        columnListP4.add(columnArray3);

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "USR_NM";
        sortConditionArray1[1] = "ASC";
        sortConditionListP5.add(sortConditionArray1);

        scrnMsg.Z.clear();

        if (ZYPCommonFunc.hasValue(scrnMsg.xxOrdTeamSrchTxt)) {
            String[] strList0 = scrnMsg.xxOrdTeamSrchTxt.getValue().split(scrnMsg.xxSplCharTxt.getValue(), 0);
            String[] strList1 = scrnMsg.xxCratByUsrSrchTxt.getValue().split(scrnMsg.xxSplCharTxt.getValue(), 0);
            int j = 0;
            for (int i = 0; i < strList0.length; i++) {
                scrnMsg.Z.no(j).xxComnScrColValTxt_0.setValue(strList0[i]);
                for (int k = 0; k < strList1.length; k++) {
                    scrnMsg.Z.no(j).xxComnScrColValTxt_0.setValue(strList0[i]);
                    scrnMsg.Z.no(j).xxComnScrColValTxt_1.setValue(strList1[k]);
                    j++;
                }
                j++;
            }
            scrnMsg.Z.setValidCount(j);
        } else {
            String[] strList0 = scrnMsg.xxCratByUsrSrchTxt.getValue().split(scrnMsg.xxSplCharTxt.getValue(), 0);
            int i = 0;
            for (String strSrchTxt : strList0) {
                scrnMsg.Z.no(i).xxComnScrColValTxt_0.setValue(strSrchTxt);
                i++;
            }
            scrnMsg.Z.setValidCount(i);
        }

        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.Z;

        return param;
    }
}
