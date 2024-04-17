/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0410;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0410.constant.NSAL0410Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CRAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Hitachi         T.Tomita        Create          QC#1319
 * 2019/01/29   Hitachi         Y.Takeno        Update          QC#29949
 * 2019/02/01   Hitachi         K.Kitachi       Update          QC#29949
 * 2019/02/15   Hitachi         K.Kitachi       Update          QC#29949
 *</pre>
 */
public class NSAL0410Scrn00_OnChange_SerNum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2019/01/29 [QC#29949, DEL]
        // NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;
        // 
        // for Delete Event
        // scrnMsg.xxPgFlg_DE.clear();
        // END   2019/01/29 [QC#29949, DEL]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // START 2019/01/29 [QC#29949, DEL]
        // NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;
        //
        // NSAL0410CMsg bizMsg = new NSAL0410CMsg();
        // bizMsg.setBusinessID(BIZ_ID);
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);
        // 
        // // Select Row Number
        // ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum_H, new BigDecimal(getButtonSelectNumber()));
        // return bizMsg;
        // END   2019/01/29 [QC#29949, DEL]

        // START 2019/01/29 [QC#29949, ADD]
        return null;
        // END   2019/01/29 [QC#29949, ADD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0410BMsg scrnMsg = (NSAL0410BMsg) bMsg;

        // START 2019/01/29 [QC#29949, DEL]
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
        //
        // NSAL0410CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID(), getGlobalCompanyCode(), false);
        //
        // set Focus
        // scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).dsContrDtlPk_A);
        // scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).xxScrItem34Txt_A);
        //
        // has error
        // if (scrnMsg.getMessageCode().endsWith("E")) {
        //     throw new EZDFieldErrorException();
        // }
        // END   2019/01/29 [QC#29949, DEL]

        // START 2019/01/29 [QC#29949, ADD]
        int rowNum = getButtonSelectNumber();
        NSAL0410_ABMsg aScrnMsg = scrnMsg.A.no(rowNum);
        Object[] params = getParamNWAL1130ForSerNumList(scrnMsg, aScrnMsg, this.getGlobalCompanyCode());
        setArgForSubScreen(params);
        // END   2019/01/29 [QC#29949, ADD]
    }

    protected Object[] getParamNWAL1130ForSerNumList(NSAL0410BMsg scrnMsg, NSAL0410_ABMsg aScrnMsg, String glblCmpyCd) {
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Serial# Search";

        // START 2019/02/01 K.Kitachi [QC#29949, MOD]
        StringBuilder sb = new StringBuilder();
        // START 2019/02/15 K.Kitachi [QC#29949, MOD]
        sb.append(" SELECT");
        sb.append("  C.GLBL_CMPY_CD");
        sb.append(" ,C.EZCANCELFLAG");
        sb.append(" ,C.SER_NUM");
        sb.append(" ,A.SVC_MACH_MSTR_PK");
        sb.append(" ,D.MDSE_DESC_SHORT_TXT");
        sb.append(" ,A.CONTR_EFF_FROM_DT");
        sb.append(" ,NVL(A.CONTR_CLO_DT, A.CONTR_EFF_THRU_DT) CONTR_EFF_THRU_DT");
        sb.append(" ,TO_CHAR(TO_DATE(A.CONTR_EFF_FROM_DT, '#dateFormat#'), '#dateFormatDisp#') DISP_CONTR_EFF_FROM_DT");
        sb.append(" ,TO_CHAR(TO_DATE(NVL(A.CONTR_CLO_DT, A.CONTR_EFF_THRU_DT), '#dateFormat#'), '#dateFormatDisp#') DISP_CONTR_EFF_THRU_DT");
        sb.append(" ,A.DS_CONTR_DTL_PK");
        sb.append(" ,3 LV");
        sb.append(" ,NVL(F.SER_NUM, C.SER_NUM) SORT_SER_NUM");
        sb.append(" ,NVL(F.MDSE_CD, C.MDSE_CD) SORT_MDSE_CD");
        sb.append(" ,G.SVC_PGM_TP_SORT_NUM");
        sb.append(" ,NVL(A.DS_CONTR_CRAT_TP_CD, '#dsContrCratTpCdIsOther#') DS_CONTR_CRAT_TP_CD");
        sb.append(" ,CASE");
        sb.append("     WHEN A.DS_CONTR_DTL_TP_CD = '#dsContrDtlTpCdIsAcc#'");
        sb.append("     THEN A.PRNT_DS_CONTR_DTL_PK");
        sb.append("     ELSE A.DS_CONTR_DTL_PK");
        sb.append("  END PRNT_DS_CONTR_DTL_PK");
        sb.append(" ,H.DS_CONTR_DTL_TP_SORT_NUM");
        sb.append(" FROM");
        sb.append("  DS_CONTR_DTL    A");
        sb.append(" ,DS_CONTR        B");
        sb.append(" ,SVC_MACH_MSTR   C");
        sb.append(" ,MDSE            D");
        sb.append(" ,DS_CONTR_DTL    E");
        sb.append(" ,SVC_MACH_MSTR   F");
        sb.append(" ,SVC_PGM_TP      G");
        sb.append(" ,DS_CONTR_DTL_TP H");
        sb.append(" WHERE");
        sb.append("     A.GLBL_CMPY_CD         = '#glblCmpyCd#'");
        sb.append(" AND A.DS_CONTR_PK          = #dsContrPk#");
        sb.append(" AND A.EZCANCELFLAG         = '0'");
        sb.append(" AND A.GLBL_CMPY_CD         = B.GLBL_CMPY_CD");
        sb.append(" AND A.DS_CONTR_PK          = B.DS_CONTR_PK");
        sb.append(" AND B.DS_CONTR_CATG_CD     IN ('#dsContrCatgCdIsReg#', '#dsContrCatgCdIsAgg#')");
        sb.append(" AND B.EZCANCELFLAG         = '0'");
        sb.append(" AND A.GLBL_CMPY_CD         = C.GLBL_CMPY_CD");
        sb.append(" AND A.SVC_MACH_MSTR_PK     = C.SVC_MACH_MSTR_PK");
        sb.append(" AND C.EZCANCELFLAG         = '0'");
        sb.append(" AND A.GLBL_CMPY_CD         = D.GLBL_CMPY_CD");
        sb.append(" AND A.SVC_PGM_MDSE_CD      = D.MDSE_CD");
        sb.append(" AND D.EZCANCELFLAG         = '0'");
        sb.append(" AND A.GLBL_CMPY_CD         = E.GLBL_CMPY_CD (+)");
        sb.append(" AND A.PRNT_DS_CONTR_DTL_PK = E.DS_CONTR_DTL_PK (+)");
        sb.append(" AND E.EZCANCELFLAG (+)     = '0'");
        sb.append(" AND E.GLBL_CMPY_CD         = F.GLBL_CMPY_CD (+)");
        sb.append(" AND E.SVC_MACH_MSTR_PK     = F.SVC_MACH_MSTR_PK (+)");
        sb.append(" AND F.EZCANCELFLAG (+)     = '0'");
        sb.append(" AND D.GLBL_CMPY_CD         = G.GLBL_CMPY_CD (+)");
        sb.append(" AND D.SVC_PGM_TP_CD        = G.SVC_PGM_TP_CD (+)");
        sb.append(" AND G.EZCANCELFLAG (+)     = '0'");
        sb.append(" AND A.GLBL_CMPY_CD         = H.GLBL_CMPY_CD");
        sb.append(" AND A.DS_CONTR_DTL_TP_CD   = H.DS_CONTR_DTL_TP_CD");
        sb.append(" AND H.EZCANCELFLAG         = '0'");
        sb.append(" UNION ALL");
        sb.append(" SELECT");
        sb.append("  C.GLBL_CMPY_CD");
        sb.append(" ,C.EZCANCELFLAG");
        sb.append(" ,C.DS_CONTR_CATG_ABBR_NM || '_' || B.DS_CONTR_NUM SER_NUM");
        sb.append(" ,NULL AS SVC_MACH_MSTR_PK");
        sb.append(" ,D.MDSE_DESC_SHORT_TXT");
        sb.append(" ,A.CONTR_EFF_FROM_DT");
        sb.append(" ,NVL(A.CONTR_CLO_DT, A.CONTR_EFF_THRU_DT) CONTR_EFF_THRU_DT");
        sb.append(" ,TO_CHAR(TO_DATE(A.CONTR_EFF_FROM_DT, '#dateFormat#'), '#dateFormatDisp#') DISP_CONTR_EFF_FROM_DT");
        sb.append(" ,TO_CHAR(TO_DATE(NVL(A.CONTR_CLO_DT, A.CONTR_EFF_THRU_DT), '#dateFormat#'), '#dateFormatDisp#') DISP_CONTR_EFF_THRU_DT");
        sb.append(" ,A.DS_CONTR_DTL_PK");
        sb.append(" ,2 LV");
        sb.append(" ,NULL SORT_SER_NUM");
        sb.append(" ,NULL SORT_MDSE_CD");
        sb.append(" ,0 SVC_PGM_TP_SORT_NUM");
        sb.append(" ,NULL DS_CONTR_CRAT_TP_CD");
        sb.append(" ,0 PRNT_DS_CONTR_DTL_PK");
        sb.append(" ,0 DS_CONTR_DTL_TP_SORT_NUM");
        sb.append(" FROM");
        sb.append("  DS_CONTR_DTL  A");
        sb.append(" ,DS_CONTR      B");
        sb.append(" ,DS_CONTR_CATG C");
        sb.append(" ,MDSE          D");
        sb.append(" WHERE");
        sb.append("     A.GLBL_CMPY_CD     = '#glblCmpyCd#'");
        sb.append(" AND A.DS_CONTR_PK      = #dsContrPk#");
        sb.append(" AND A.EZCANCELFLAG     = '0'");
        sb.append(" AND A.GLBL_CMPY_CD     = B.GLBL_CMPY_CD");
        sb.append(" AND A.DS_CONTR_PK      = B.DS_CONTR_PK");
        sb.append(" AND B.DS_CONTR_CATG_CD = '#dsContrCatgCdIsFlt#'");
        sb.append(" AND B.EZCANCELFLAG     = '0'");
        sb.append(" AND B.GLBL_CMPY_CD     = C.GLBL_CMPY_CD");
        sb.append(" AND B.DS_CONTR_CATG_CD = C.DS_CONTR_CATG_CD");
        sb.append(" AND C.EZCANCELFLAG     = '0'");
        sb.append(" AND A.GLBL_CMPY_CD     = D.GLBL_CMPY_CD");
        sb.append(" AND A.SVC_PGM_MDSE_CD  = D.MDSE_CD");
        sb.append(" AND D.EZCANCELFLAG     = '0'");
        sb.append(" UNION ALL");
        sb.append(" SELECT");
        sb.append("  A.GLBL_CMPY_CD");
        sb.append(" ,A.EZCANCELFLAG");
        sb.append(" ,B.DS_CONTR_CATG_ABBR_NM || '_' || A.DS_CONTR_NUM SER_NUM");
        sb.append(" ,NULL SVC_MACH_MSTR_PK");
        sb.append(" ,NULL MDSE_DESC_SHORT_TXT");
        sb.append(" ,A.CONTR_VRSN_EFF_FROM_DT CONTR_EFF_FROM_DT");
        sb.append(" ,A.CONTR_VRSN_EFF_THRU_DT CONTR_EFF_THRU_DT");
        sb.append(" ,TO_CHAR(TO_DATE(A.CONTR_VRSN_EFF_FROM_DT, '#dateFormat#'), '#dateFormatDisp#') DISP_CONTR_EFF_FROM_DT");
        sb.append(" ,TO_CHAR(TO_DATE(A.CONTR_VRSN_EFF_THRU_DT, '#dateFormat#'), '#dateFormatDisp#') DISP_CONTR_EFF_THRU_DT");
        sb.append(" ,NULL DS_CONTR_DTL_PK");
        sb.append(" ,1 LV");
        sb.append(" ,NULL SORT_SER_NUM");
        sb.append(" ,NULL SORT_MDSE_CD");
        sb.append(" ,0 SVC_PGM_TP_SORT_NUM");
        sb.append(" ,NULL DS_CONTR_CRAT_TP_CD");
        sb.append(" ,0 PRNT_DS_CONTR_DTL_PK");
        sb.append(" ,0 DS_CONTR_DTL_TP_SORT_NUM");
        sb.append(" FROM");
        sb.append("  DS_CONTR      A");
        sb.append(" ,DS_CONTR_CATG B");
        sb.append(" WHERE");
        sb.append("     A.GLBL_CMPY_CD     = '#glblCmpyCd#'");
        sb.append(" AND A.DS_CONTR_PK      = #dsContrPk#");
        sb.append(" AND A.DS_CONTR_CATG_CD IN ('#dsContrCatgCdIsReg#', '#dsContrCatgCdIsAgg#')");
        sb.append(" AND A.EZCANCELFLAG     = '0'");
        sb.append(" AND A.GLBL_CMPY_CD     = B.GLBL_CMPY_CD");
        sb.append(" AND A.DS_CONTR_CATG_CD = B.DS_CONTR_CATG_CD");
        sb.append(" AND B.EZCANCELFLAG     = '0'");
        // END 2019/02/15 K.Kitachi [QC#29949, MOD]

        String sql = sb.toString();
        sql = sql.replaceAll("#glblCmpyCd#", glblCmpyCd);
        sql = sql.replaceAll("#dsContrPk#", String.valueOf(aScrnMsg.dsContrPk_A.getValue()));
        sql = sql.replaceAll("#dsContrCatgCdIsReg#", DS_CONTR_CATG.REGULAR);
        sql = sql.replaceAll("#dsContrCatgCdIsFlt#", DS_CONTR_CATG.FLEET);
        sql = sql.replaceAll("#dsContrCatgCdIsAgg#", DS_CONTR_CATG.AGGREGATE);
        sql = sql.replaceAll("#dsContrDtlTpCdIsAcc#", DS_CONTR_DTL_TP.ACCESSORIES);
        sql = sql.replaceAll("#dsContrCratTpCdIsOther#", DS_CONTR_CRAT_TP.OTHER);
        // START 2019/02/15 K.Kitachi [QC#29949, ADD]
        sql = sql.replaceAll("#dateFormat#", ZYPDateUtil.TYPE_YYYYMMDD);
        sql = sql.replaceAll("#dateFormatDisp#", NSAL0410Constant.DATE_FORMAT_DISP);
        // END 2019/02/15 K.Kitachi [QC#29949, ADD]

        params[2] = sql;
        // END 2019/02/01 K.Kitachi [QC#29949, MOD]

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Serial# ";
        whereArray1[1] = "SER_NUM";
        whereArray1[2] = aScrnMsg.xxScrItem34Txt_A.getValue();
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Machine Master";
        whereArray2[1] = "SVC_MACH_MSTR_PK";
        // START 2019/02/01 K.Kitachi [QC#29949, MOD]
        if (ZYPCommonFunc.hasValue(aScrnMsg.svcMachMstrPk_A)) {
            whereArray2[2] = String.valueOf(aScrnMsg.svcMachMstrPk_A.getValue());
        }
        // END 2019/02/01 K.Kitachi [QC#29949, MOD]
        whereArray2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[4];
        whereArray3[0] = "Service Program";
        whereArray3[1] = "MDSE_DESC_SHORT_TXT";
        whereArray3[2] = aScrnMsg.mdseDescShortTxt_A.getValue();
        // START 2019/02/01 K.Kitachi [QC#29949, MOD]
        whereArray3[3] = ZYPConstant.FLG_OFF_N;
        // END 2019/02/01 K.Kitachi [QC#29949, MOD]
        whereList.add(whereArray3);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Serial#";
        columnArray1[1] = "SER_NUM";
        columnArray1[2] = BigDecimal.valueOf(10);
        columnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Machine Master";
        columnArray2[1] = "SVC_MACH_MSTR_PK";
        columnArray2[2] = BigDecimal.valueOf(10);
        columnArray2[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Service Program";
        columnArray3[1] = "MDSE_DESC_SHORT_TXT";
        // START 2019/02/01 K.Kitachi [QC#29949, MOD]
        columnArray3[2] = BigDecimal.valueOf(57);
        // END 2019/02/01 K.Kitachi [QC#29949, MOD]
        columnArray3[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "Start";
        // START 2019/02/15 K.Kitachi [QC#29949, MOD]
        columnArray4[1] = "DISP_CONTR_EFF_FROM_DT";
        columnArray4[2] = BigDecimal.valueOf(7);
        // END 2019/02/15 K.Kitachi [QC#29949, MOD]
        columnArray4[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[4];
        columnArray5[0] = "End";
        // START 2019/02/15 K.Kitachi [QC#29949, MOD]
        columnArray5[1] = "DISP_CONTR_EFF_THRU_DT";
        columnArray5[2] = BigDecimal.valueOf(7);
        // END 2019/02/15 K.Kitachi [QC#29949, MOD]
        columnArray5[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray5);

        Object[] columnArray6 = new Object[4];
        columnArray6[0] = "DS_CONTR_DTL_PK";
        columnArray6[1] = "DS_CONTR_DTL_PK";
        columnArray6[2] = BigDecimal.valueOf(0);
        columnArray6[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray6);

        Object[] columnArray7 = new Object[4];
        // START 2019/02/01 K.Kitachi [QC#29949, MOD]
        columnArray7[0] = "LV";
        columnArray7[1] = "LV";
        columnArray7[2] = BigDecimal.valueOf(0);
        columnArray7[3] = ZYPConstant.FLG_OFF_N;
        // END 2019/02/01 K.Kitachi [QC#29949, MOD]
        columnList.add(columnArray7);

        // START 2019/02/15 K.Kitachi [QC#29949, ADD]
        Object[] columnArray8 = new Object[4];
        columnArray8[0] = "CONTR_EFF_FROM_DT";
        columnArray8[1] = "CONTR_EFF_FROM_DT";
        columnArray8[2] = BigDecimal.valueOf(0);
        columnArray8[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray8);

        Object[] columnArray9 = new Object[4];
        columnArray9[0] = "CONTR_EFF_THRU_DT";
        columnArray9[1] = "CONTR_EFF_THRU_DT";
        columnArray9[2] = BigDecimal.valueOf(0);
        columnArray9[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray9);
        // END 2019/02/15 K.Kitachi [QC#29949, ADD]

        params[4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        // START 2019/02/01 K.Kitachi [QC#29949, MOD]
        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "LV";
        sortConditionArray1[1] = "ASC";
        sortList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[2];
        sortConditionArray2[0] = "SORT_SER_NUM";
        sortConditionArray2[1] = "ASC";
        sortList.add(sortConditionArray2);

        Object[] sortConditionArray3 = new Object[2];
        sortConditionArray3[0] = "SORT_MDSE_CD";
        sortConditionArray3[1] = "ASC";
        sortList.add(sortConditionArray3);

        Object[] sortConditionArray4 = new Object[2];
        sortConditionArray4[0] = "SVC_PGM_TP_SORT_NUM";
        sortConditionArray4[1] = "ASC";
        sortList.add(sortConditionArray4);

        Object[] sortConditionArray5 = new Object[2];
        sortConditionArray5[0] = "DS_CONTR_CRAT_TP_CD";
        sortConditionArray5[1] = "ASC";
        sortList.add(sortConditionArray5);

        Object[] sortConditionArray6 = new Object[2];
        sortConditionArray6[0] = "PRNT_DS_CONTR_DTL_PK";
        sortConditionArray6[1] = "ASC";
        sortList.add(sortConditionArray6);

        Object[] sortConditionArray7 = new Object[2];
        sortConditionArray7[0] = "DS_CONTR_DTL_TP_SORT_NUM";
        sortConditionArray7[1] = "ASC";
        sortList.add(sortConditionArray7);

        Object[] sortConditionArray8 = new Object[2];
        sortConditionArray8[0] = "DS_CONTR_DTL_PK";
        sortConditionArray8[1] = "ASC";
        sortList.add(sortConditionArray8);
        // END 2019/02/01 K.Kitachi [QC#29949, MOD]
        params[5] = sortList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;
        return params;
    }
}
