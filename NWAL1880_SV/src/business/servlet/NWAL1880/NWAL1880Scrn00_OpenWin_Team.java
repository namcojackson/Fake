/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1880;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NWAL1880.common.NWAL1880CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/10   Fujitsu         K.Sato          Create          N/A
 *</pre>
 */
public class NWAL1880Scrn00_OpenWin_Team extends S21CommonHandler {

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
        scrnNmP1 = "Order Team Popup";

        tblNmP2.append("SELECT");
        tblNmP2.append("    OZN.GLBL_CMPY_CD ");
        tblNmP2.append("    ,OZN.EZCANCELFLAG ");
        tblNmP2.append("    ,OZN.ORD_ZN_DESC_TXT ");
        tblNmP2.append("    ,OTH.ORD_TEAM_MSTR_NM ");
        tblNmP2.append("FROM ");
        tblNmP2.append("    ORD_TEAM_MSTR_HDR  OTH ");
        tblNmP2.append("    ,ORD_ZN            OZN ");
        tblNmP2.append("WHERE ");
        tblNmP2.append("    OTH.GLBL_CMPY_CD           = '").append(glblCmpyCd).append("'");
        tblNmP2.append("    AND OTH.EZCANCELFLAG       = '0'");
        tblNmP2.append("    AND OTH.EFF_FROM_DT       <= '").append(scrnMsg.slsDt.getValue()).append("'");
        tblNmP2.append("    AND (OTH.EFF_THRU_DT      >= '").append(scrnMsg.slsDt.getValue()).append("' OR OTH.EFF_THRU_DT IS NULL)");
        tblNmP2.append("    AND OTH.GLBL_CMPY_CD       = OZN.GLBL_CMPY_CD");
        tblNmP2.append("    AND OTH.ORD_ZN_CD          = OZN.ORD_ZN_CD");
        tblNmP2.append("    AND OZN.EZCANCELFLAG       = '0'");

        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Order Zone Name";
        whereArray0[1] = "ORD_ZN_DESC_TXT";
        whereArray0[2] = scrnMsg.xxPopPrm_P1.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Order Team Name";
        whereArray1[1] = "ORD_TEAM_MSTR_NM";
        whereArray1[2] = scrnMsg.xxPopPrm_P1.getValue();
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray1);

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Order Zone Name";
        columnArray0[1] = "ORD_ZN_DESC_TXT";
        columnArray0[2] = BigDecimal.valueOf(25);
        columnArray0[3] = "Y";
        columnListP4.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Order Team Name";
        columnArray1[1] = "ORD_TEAM_MSTR_NM";
        columnArray1[2] = BigDecimal.valueOf(25);
        columnArray1[3] = "N";
        columnListP4.add(columnArray1);

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "ORD_TEAM_MSTR_NM";
        sortConditionArray1[1] = "ASC";
        sortConditionListP5.add(sortConditionArray1);

        scrnMsg.Z.clear();

//        String[] strList = scrnMsg.xxOrdTeamSrchTxt.getValue().split(scrnMsg.xxSplCharTxt.getValue(), 0);
//        for (int i = 0; i < strList.length; i++) {
//            scrnMsg.Z.no(i).xxComnScrColValTxt_1.setValue(strList[i]);
//        }
//        scrnMsg.Z.setValidCount(strList.length);

        String[] strList0 = scrnMsg.xxOrdZnSrchTxt.getValue().split(scrnMsg.xxSplCharTxt.getValue(), 0);
        String[] strList1 = scrnMsg.xxOrdTeamSrchTxt.getValue().split(scrnMsg.xxSplCharTxt.getValue(), 0);
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
