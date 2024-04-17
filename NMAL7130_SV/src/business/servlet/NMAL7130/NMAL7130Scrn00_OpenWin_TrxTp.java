/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7130;

import static business.servlet.NMAL7130.constant.NMAL7130Constant.BIZ_ID;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.CMN_PRM_COLUMN_NUM;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.CMN_PRM_WHERE_NUM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL7130.NMAL7130CMsg;
import business.servlet.NMAL7130.common.NMAL7130CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7130Scrn00_OpenWin_TrxTp
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/22   Fujitsu         W.Honda         Update          CSA-QC#3636
 *</pre>
 */
public class NMAL7130Scrn00_OpenWin_TrxTp extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // START 2016/02/22 W.Honda [S21 CSA QC#3636 ADD]
        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;

        NMAL7130CommonLogic.addCheckScreenItem(scrnMsg);

        scrnMsg.putErrorScreen();
        // END 2016/02/22 W.Honda [S21 CSA QC#3636 ADD]

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;

        int idx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));

        NMAL7130CMsg bizMsg = new NMAL7130CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;
        NMAL7130CMsg bizMsg = (NMAL7130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(getButtonSelectNumber()));

        setArgForSubScreen(setPopupParameter(scrnMsg, getGlobalCompanyCode(), getButtonSelectNumber()));
    }

    private static Object[] setPopupParameter(NMAL7130BMsg scrnMsg, String glblCmpyCd, int eventRow) {
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
        scrnNmP1 = "Order Category Popup";

        tblNmP2.append(" SELECT");
        tblNmP2.append(" DOC.GLBL_CMPY_CD");
        tblNmP2.append(",DOC.EZCANCELFLAG");
        tblNmP2.append(",DOC.DS_ORD_CATG_SORT_NUM");
        tblNmP2.append(",DOC.DS_ORD_CATG_CD");
        tblNmP2.append(",DOC.DS_ORD_CATG_NM");
        tblNmP2.append(" FROM DS_ORD_CATG DOC");
        tblNmP2.append(" WHERE");
        tblNmP2.append(" DOC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        tblNmP2.append(" AND DOC.EZCANCELFLAG = '").append("0").append("'");

        Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
        whereArray0[0] = "Order Category Code";
        whereArray0[1] = "DS_ORD_CATG_CD";
        whereArray0[2] = scrnMsg.xxPopPrm_P1.getValue();
        whereArray0[3] = "Y";
        whereListP3.add(whereArray0);

        Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
        whereArray1[0] = "Order Category Name";
        whereArray1[1] = "DS_ORD_CATG_NM";
        whereArray1[2] = scrnMsg.xxPopPrm_P1.getValue();
        whereArray1[3] = "Y";
        whereListP3.add(whereArray1);

        Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray0[0] = "Order Category Code";
        columnArray0[1] = "DS_ORD_CATG_CD";
        columnArray0[2] = BigDecimal.valueOf(25);
        columnArray0[3] = "Y";
        columnListP4.add(columnArray0);

        Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray1[0] = "Order Category Name";
        columnArray1[1] = "DS_ORD_CATG_NM";
        columnArray1[2] = BigDecimal.valueOf(25);
        columnArray1[3] = "N";
        columnListP4.add(columnArray1);

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "DS_ORD_CATG_SORT_NUM";
        sortConditionArray1[1] = "ASC";
        sortConditionListP5.add(sortConditionArray1);

        scrnMsg.R.clear();

        String[] trxTpList = scrnMsg.D.no(eventRow).xxRecNmTxt_D1.getValue().split(scrnMsg.addCharTxt.getValue(), 0);
        String[] trxTpListBk = scrnMsg.D.no(eventRow).xxRecNmTxt_BK.getValue().split(scrnMsg.addCharTxt.getValue(), 0);
        if (trxTpList.length == trxTpListBk.length) {
            for (int i = 0; i < trxTpList.length; i++) {
                scrnMsg.R.no(i).xxComnScrColValTxt_0.setValue(trxTpListBk[i]);
                scrnMsg.R.no(i).xxComnScrColValTxt_1.setValue(trxTpList[i]);
            }
        }
        scrnMsg.R.setValidCount(trxTpList.length);

        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }
}
