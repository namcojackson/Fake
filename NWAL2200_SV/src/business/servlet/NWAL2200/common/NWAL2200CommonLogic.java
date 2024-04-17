/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200.common;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.COMMA;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.SPACE;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_RMA;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_DISP_HRCH_ACCT_CD_BILL;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_DISP_HRCH_ACCT_CD_SHIP;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_STATUS_CD_ACTIVE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBItem;
import parts.common.EZDBStringItem;
import business.servlet.NWAL2200.NWAL2200BMsg;
import business.servlet.NWAL2200.NWAL2200_BBMsg;
import business.servlet.NWAL2200.constant.NWAL2200Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NWAL2200CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2016/08/04   Fujitsu         R.Nakamura      Update          QC#9078
 * 2016/08/31   Fujitsu         T.Murai         Update          QC#14020
 * 2017/09/15   Fujitsu         R.Nakamura      Update          QC#21118
 * 2018/07/25   Fujitsu         Mz.Takahashi    Update          S21_NA#14307
 * 2018/10/18   Fujitsu         K.Ishizuka      Update          QC#28712
 *</pre>
 */
public class NWAL2200CommonLogic {

    /**
     * compare to big decimal
     * @param source source value
     * @param target target value
     * @return result (0, > 0 , < 0)
     */
    public static int compareBigDecimal(BigDecimal source, BigDecimal target) {

        if (source == null) {
            if (target == null) {
                return 0;
            } else {
                return -1;
            }
        } else {
            if (target == null) {
                return 1;
            } else {
                return source.compareTo(target);
            }
        }
    }

    /**
     * setInitRequestData
     * @param scrnMsg NWAL2200BMsg
     * @param params initial parameters
     */
    public static void setInitRequestData(NWAL2200BMsg scrnMsg, Object[] params) {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        scrnMsg.clear();
        ZYPTableUtil.clear(scrnMsg.A);
        ZYPTableUtil.clear(scrnMsg.B);
        ZYPTableUtil.clear(scrnMsg.C);
        ZYPTableUtil.clear(scrnMsg.D);
        ZYPTableUtil.clear(scrnMsg.E);
        ZYPTableUtil.clear(scrnMsg.F);
        ZYPTableUtil.clear(scrnMsg.G);
        ZYPTableUtil.clear(scrnMsg.H);
        // ZYPTableUtil.clear(scrnMsg.I);
        ZYPTableUtil.clear(scrnMsg.N);
        ZYPTableUtil.clear(scrnMsg.O);
        ZYPTableUtil.clear(scrnMsg.P);
        ZYPTableUtil.clear(scrnMsg.Q);
        ZYPTableUtil.clear(scrnMsg.Z);
        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, userProfSvc.getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(scrnMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, NWAL2200Constant.TAB_HEADER);
        if (params != null && params.length > 0) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.ordSrcRefNum, (EZDBStringItem) params[0]);
            if (params.length > 1) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsImptOrdPk, (EZDBBigDecimalItem) params[1]);
            }
        }
    }

    /**
     * Get Param NWAL1760
     * @param scrnMsg NWAL2200BMsg
     * @param prcCtxTp Price Ctx Type
     * @return Param NWAL1760
     */
    public static Object[] getParamNWAL1760(NWAL2200BMsg scrnMsg, String prcCtxTp) {

        ZYPTableUtil.clear(scrnMsg.P);

        int selectIdx = scrnMsg.xxCellIdx.getValueInt();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, prcCtxTp);
        List<EZDBItem> paramList = new ArrayList<EZDBItem>();

        if (selectIdx < 0) {
            if (ZYPCommonFunc.hasValue(scrnMsg.xxCratDt)) {
                paramList.add(scrnMsg.xxCratDt);
            } else {
                paramList.add(scrnMsg.slsDt);
            }
            paramList.add(scrnMsg.P.no(0).xxPopPrm);
            paramList.add(scrnMsg.dsOrdCatgCd);
            paramList.add(scrnMsg.dsOrdTpCd);
            paramList.add(scrnMsg.sellToCustCd);
            paramList.add(scrnMsg.csmpContrNum);
            paramList.add(scrnMsg.dlrRefNum);
            paramList.add(scrnMsg.prcContrNum);
            paramList.add(scrnMsg.coaBrCd);
        } else {
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIdx).prcBaseDt_LL)) {
                    paramList.add(scrnMsg.B.no(selectIdx).prcBaseDt_LL);
                } else {
                    paramList.add(scrnMsg.slsDt);
                }
                paramList.add(scrnMsg.P.no(0).xxPopPrm);
                paramList.add(scrnMsg.dsOrdCatgCd);
                paramList.add(scrnMsg.dsOrdTpCd);
                paramList.add(scrnMsg.sellToCustCd);
                paramList.add(scrnMsg.B.no(selectIdx).csmpContrNum_LL);
                paramList.add(scrnMsg.B.no(selectIdx).dlrRefNum_LL);
                paramList.add(scrnMsg.prcContrNum);
                paramList.add(scrnMsg.coaBrCd);
            } else {
                if (ZYPCommonFunc.hasValue(scrnMsg.D.no(selectIdx).prcBaseDt_RL)) {
                    paramList.add(scrnMsg.D.no(selectIdx).prcBaseDt_RL);
                } else {
                    paramList.add(scrnMsg.slsDt);
                }
                paramList.add(scrnMsg.P.no(0).xxPopPrm);
                paramList.add(scrnMsg.dsOrdCatgCd);
                paramList.add(scrnMsg.dsOrdTpCd);
                paramList.add(scrnMsg.sellToCustCd);
                paramList.add(scrnMsg.D.no(selectIdx).csmpContrNum_RL);
                paramList.add(scrnMsg.D.no(selectIdx).dlrRefNum_RL);
                paramList.add(scrnMsg.prcContrNum);
                paramList.add(scrnMsg.coaBrCd);
            }
        }
        paramList.add(scrnMsg.P.no(1).xxPopPrm);
        paramList.add(scrnMsg.P.no(2).xxPopPrm);
        paramList.add(scrnMsg.P.no(3).xxPopPrm);
        paramList.add(scrnMsg.P.no(4).xxPopPrm);

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NWAL1130 For Order Category
     * @param scrnMsg NWAL2200BMsg
     * @return Param NWAL1130 For Order Category
     */
    public static Object[] getParamNWAL1130ForOrdCatg(NWAL2200BMsg scrnMsg) {

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Order Category Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    DOC.GLBL_CMPY_CD         AS GLBL_CMPY_CD");
        sb.append("   ,DOC.EZCANCELFLAG         AS EZCANCELFLAG");
        sb.append("   ,DOC.DS_ORD_CATG_CD       AS DS_ORD_CATG_CD");
        sb.append("   ,DOC.DS_ORD_CATG_DESC_TXT AS DS_ORD_CATG_DESC_TXT");
        sb.append("   ,DOC.DS_ORD_CATG_SORT_NUM AS DS_ORD_CATG_SORT_NUM ");
        sb.append("FROM");
        sb.append("    DS_ORD_CATG DOC ");
        sb.append("WHERE");
        sb.append("    DOC.DS_ORD_CATG_CD IN (");
        sb.append("        SELECT");
        sb.append("            DOT.DS_ORD_CATG_CD AS DS_ORD_CATG_CD");
        sb.append("        FROM");
        sb.append("            AVAL_DS_ORD_TP_APP_ID AD");
        sb.append("           ,DS_ORD_TP             DOT");
        sb.append("        WHERE");
        sb.append("            AD.GLBL_CMPY_CD      = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("            AND AD.BIZ_APP_ID    = '").append(BIZ_ID).append("' ");
        sb.append("            AND AD.EZCANCELFLAG  = '0'");
        sb.append("            AND AD.GLBL_CMPY_CD  = DOT.GLBL_CMPY_CD");
        sb.append("            AND AD.DS_ORD_TP_CD  = DOT.DS_ORD_TP_CD");
        sb.append("            AND DOT.EZCANCELFLAG = '0'");
        sb.append("        GROUP BY");
        sb.append("            DOT.DS_ORD_CATG_CD )");
        sb.append("    AND DOC.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND DOC.EZCANCELFLAG = '0'");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray = new Object[4];
        whereArray[0] = "Order Category Name";
        whereArray[1] = "DS_ORD_CATG_DESC_TXT";
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgDescTxt)) {// S21_NA#8393
            whereArray[2] = "%";
        } else {
            whereArray[2] = scrnMsg.dsOrdCatgDescTxt.getValue();
        }
        whereArray[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Order Category Code";
        columnArray0[1] = "DS_ORD_CATG_CD";
        columnArray0[2] = BigDecimal.valueOf(13);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Order Category Name";
        columnArray1[1] = "DS_ORD_CATG_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "DS_ORD_CATG_SORT_NUM";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NMAL6770 For Bill To
     * @param scrnMsg NWAL2200BMsg
     * @return Param NMAL6770 For Bill To
     */
    public static Object[] getParamNMAL6760ForBillTo(NWAL2200BMsg scrnMsg) {
        // 2018/06/21 QC#14307 Add Start
        String funcCatgId = scrnMsg.P.no(2).xxPopPrm.getValue();
        String trxRuleTp = scrnMsg.P.no(3).xxPopPrm.getValue();
        // 2018/06/21 QC#14307 Add End
 
        scrnMsg.firstLineAddr.clear();
        scrnMsg.scdLineAddr.clear();
        scrnMsg.thirdLineAddr.clear();
        scrnMsg.frthLineAddr.clear();
        scrnMsg.ctyAddr.clear();
        scrnMsg.stCd.clear();
        scrnMsg.postCd.clear();
        ZYPTableUtil.clear(scrnMsg.P);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        int selectIdx = scrnMsg.xxCellIdx.getValueInt();
        // Mod Start 2017/09/15 QC#21118
//        if (selectIdx == -1) {
            //  Mod Start 2016/08/04 QC#9078
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, NMAL6760Constant.STATUS_CD_ACTIVE);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, NMAL6760Constant.DISP_HRCH_ACCT_CD_BILL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, NMAL6760_STATUS_CD_ACTIVE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, NMAL6760_DISP_HRCH_ACCT_CD_BILL);
            // Mod End 2016/08/04 QC#9078

        // 2018/06/21 QC#14307 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, funcCatgId);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, trxRuleTp);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, scrnMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(10).xxPopPrm, ZYPConstant.FLG_ON_Y);
        // 2018/06/21 QC#14307 Add End

        String billToAcctNum = "";
        
        // Mod Start 2017/09/15 QC#21118
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            billToAcctNum = scrnMsg.A.no(selectIdx).billToCustAcctCd_LC.getValue();
            paramList.add(scrnMsg.A.no(selectIdx).billToCustAcctCd_LC);
            paramList.add(scrnMsg.P.no(0).xxPopPrm);
        } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
            billToAcctNum = scrnMsg.C.no(selectIdx).billToCustAcctCd_RC.getValue();
            paramList.add(scrnMsg.C.no(selectIdx).billToCustAcctCd_RC);
            paramList.add(scrnMsg.P.no(0).xxPopPrm);
        } else {
            billToAcctNum = scrnMsg.billToCustAcctCd.getValue();
            paramList.add(scrnMsg.billToCustAcctCd);
            paramList.add(scrnMsg.billToCustAcctNm);
        }

        if (ZYPCommonFunc.hasValue(billToAcctNum)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, ZYPConstant.FLG_ON_Y);
        }

        // Mod End 2017/09/15 QC#21118
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.firstLineAddr);
        paramList.add(scrnMsg.ctyAddr);
        paramList.add(scrnMsg.stCd);
        paramList.add(scrnMsg.postCd);
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(1).xxPopPrm); // Active Only
        paramList.add(scrnMsg.P.no(2).xxPopPrm); // Bill To's Only
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        // Mod Start 2017/09/15 QC#21118
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            paramList.add(scrnMsg.A.no(selectIdx).billToCustLocCd_LC);
        }else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
            paramList.add(scrnMsg.C.no(selectIdx).billToCustLocCd_RC);
        } else {
            paramList.add(scrnMsg.billToCustCd);
        }
        // Mod End 2017/09/15 QC#21118
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.scdLineAddr);
        paramList.add(scrnMsg.thirdLineAddr);
        paramList.add(scrnMsg.frthLineAddr);
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(8).xxPopPrm); // Display Hierarkey Active Flag
        paramList.add(scrnMsg.P.no(9).xxPopPrm); // Account Number Active Flag
        paramList.add(scrnMsg.P.no(10).xxPopPrm); // Status Active Flag

        // 2018/06/21 QC#14307 Add Start
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [27]
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [28]
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [29]
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [30]
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [31]
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [32]
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [33]
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [34]
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [35]
        paramList.add(scrnMsg.P.no(5).xxPopPrm); // [36] Display Control Customer Special Instruction
        paramList.add(scrnMsg.P.no(4).xxPopPrm); // [37] Transaction Type
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [38] Business Area
        paramList.add(scrnMsg.P.no(6).xxPopPrm); // [39] Function ID
        paramList.add(scrnMsg.P.no(3).xxPopPrm); // [40] Function Category ID
        paramList.add(scrnMsg.P.no(7).xxPopPrm); // [41] Line of Business Code
        // 2018/06/21 QC#14307 Add End

//        } else {
//
//            //  Mod Start 2016/08/04 QC#9078
////            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, NMAL6760Constant.STATUS_CD_ACTIVE);
////            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, NMAL6760Constant.DISP_HRCH_ACCT_CD_BILL);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, NMAL6760_STATUS_CD_ACTIVE);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, NMAL6760_DISP_HRCH_ACCT_CD_BILL);
//            // Mod End 2016/08/04 QC#9078
//
//            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
//                paramList.add(scrnMsg.A.no(selectIdx).billToCustAcctCd_LC);
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//            } else {
//                paramList.add(scrnMsg.C.no(selectIdx).billToCustAcctCd_RC);
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//            }
//            paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//            paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//            paramList.add(scrnMsg.P.no(0).xxPopPrm);
//            paramList.add(scrnMsg.P.no(0).xxPopPrm);
//            paramList.add(scrnMsg.P.no(0).xxPopPrm);
//            paramList.add(scrnMsg.P.no(0).xxPopPrm);
//            paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//            paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//            paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//            paramList.add(scrnMsg.P.no(1).xxPopPrm); // Active Only
//            paramList.add(scrnMsg.P.no(2).xxPopPrm); // Bill To's Only
//            paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//            paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
//                paramList.add(scrnMsg.A.no(selectIdx).billToCustLocCd_LC);
//            } else {
//                paramList.add(scrnMsg.C.no(selectIdx).billToCustLocCd_RC);
//            }
//            paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//            paramList.add(scrnMsg.P.no(0).xxPopPrm);
//            paramList.add(scrnMsg.P.no(0).xxPopPrm);
//            paramList.add(scrnMsg.P.no(0).xxPopPrm);
//            paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//            paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//            paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//            paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//        }
        // Mod End 2017/09/15 QC#21118

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NMAL6770 For Ship To
     * @param scrnMsg NWAL2200BMsg
     * @return Param NMAL6770 For Ship To
     */
    public static Object[] getParamNMAL6760ForShipTo(NWAL2200BMsg scrnMsg) {

        // 2018/06/21 QC#14307 Add Start
        String funcCatgId = scrnMsg.P.no(2).xxPopPrm.getValue();
        String trxRuleTp = scrnMsg.P.no(3).xxPopPrm.getValue();
        // 2018/06/21 QC#14307 Add End

        scrnMsg.firstRefCmntTxt.clear();
        scrnMsg.scdRefCmntTxt.clear();
        scrnMsg.firstLineAddr.clear();
        scrnMsg.scdLineAddr.clear();
        scrnMsg.thirdLineAddr.clear();
        scrnMsg.frthLineAddr.clear();
        scrnMsg.ctyAddr.clear();
        scrnMsg.cntyNm.clear();
        scrnMsg.provNm.clear();
        scrnMsg.stCd.clear();
        scrnMsg.postCd.clear();
        scrnMsg.ctryCd.clear();
        ZYPTableUtil.clear(scrnMsg.P);

        // Mod Start 2016/08/04 QC#9078
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, NMAL6760Constant.STATUS_CD_ACTIVE);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, NMAL6760Constant.DISP_HRCH_ACCT_CD_SHIP);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, NMAL6760_STATUS_CD_ACTIVE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, NMAL6760_DISP_HRCH_ACCT_CD_SHIP);
        // Mod End 2016/08/04 QC#9078

        // 2018/06/21 QC#14307 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, funcCatgId);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, trxRuleTp);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, scrnMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(10).xxPopPrm, ZYPConstant.FLG_ON_Y);
        // 2018/06/21 QC#14307 Add End
        
        String shipToAcctNum = "";
        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        int selectIdx = scrnMsg.xxCellIdx.getValueInt();
        // Mod Start 2017/09/15 QC#21118
//        if (selectIdx == -1) {
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            shipToAcctNum = scrnMsg.A.no(selectIdx).shipToCustAcctCd_LC.getValue();
            paramList.add(scrnMsg.A.no(selectIdx).shipToCustAcctCd_LC);
            paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
            shipToAcctNum = scrnMsg.C.no(selectIdx).shipToCustAcctCd_RC.getValue();
            paramList.add(scrnMsg.C.no(selectIdx).shipToCustAcctCd_RC);
            paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        } else {
            shipToAcctNum = scrnMsg.shipToCustAcctCd.getValue();
            paramList.add(scrnMsg.shipToCustAcctCd);
            paramList.add(scrnMsg.shipToCustAcctNm);
        }

        // 2018/06/21 QC#14307 Add Start
        if (ZYPCommonFunc.hasValue(shipToAcctNum)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, ZYPConstant.FLG_ON_Y);
        }
        // 2018/06/21 QC#14307 Add End

        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.firstLineAddr);
        paramList.add(scrnMsg.ctyAddr);
        paramList.add(scrnMsg.stCd);
        paramList.add(scrnMsg.postCd);
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(1).xxPopPrm); // Active Only
        paramList.add(scrnMsg.P.no(2).xxPopPrm); // Ship To's Only
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            paramList.add(scrnMsg.A.no(selectIdx).shipToCustLocCd_LC);
        } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
            paramList.add(scrnMsg.C.no(selectIdx).shipToCustLocCd_RC);
        } else {
            paramList.add(scrnMsg.shipToCustCd);
        }
        paramList.add(scrnMsg.scdLineAddr);
        paramList.add(scrnMsg.thirdLineAddr);
        paramList.add(scrnMsg.frthLineAddr);
        paramList.add(scrnMsg.cntyNm);
        paramList.add(scrnMsg.provNm);
        paramList.add(scrnMsg.ctryCd); // S21_NA#11429
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        //paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used

        // 2018/06/21 QC#14307 Add Start
        paramList.add(scrnMsg.P.no(8).xxPopPrm); // Display Hierarkey Active Flag
        paramList.add(scrnMsg.P.no(9).xxPopPrm); // Account Number Active Flag
        paramList.add(scrnMsg.P.no(10).xxPopPrm); // Status Active Flag
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.firstRefCmntTxt); // First Ref Comment
        paramList.add(scrnMsg.scdRefCmntTxt); // Second Ref Comment
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [33]
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [34]
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [35]
        paramList.add(scrnMsg.P.no(5).xxPopPrm); // [36] Display Control Customer Special Instruction
        paramList.add(scrnMsg.P.no(4).xxPopPrm); // [37] Transaction Type
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [38] Business Area
        paramList.add(scrnMsg.P.no(6).xxPopPrm); // [39] Function ID
        paramList.add(scrnMsg.P.no(3).xxPopPrm); // [40] Function Category ID
        paramList.add(scrnMsg.P.no(7).xxPopPrm); // [41] Line of Business Code
        // 2018/06/21 QC#14307 Add End

        // 2018/07/10 S21_NA#26661,26713 Add End

//        } else {
//            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.A.no(selectIdx).shipToFirstLineAddr_LC);
//                paramList.add(scrnMsg.A.no(selectIdx).shipToCtyAddr_LC);
//                paramList.add(scrnMsg.A.no(selectIdx).shipToStCd_LC);
//                paramList.add(scrnMsg.A.no(selectIdx).shipToPostCd_LC);
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.P.no(1).xxPopPrm); // Active
//                // Only
//                paramList.add(scrnMsg.P.no(2).xxPopPrm); // Ship To's
//                // Only
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.A.no(selectIdx).shipToCustLocCd_LC);
//                paramList.add(scrnMsg.A.no(selectIdx).shipToScdLineAddr_LC);
//                paramList.add(scrnMsg.A.no(selectIdx).shipToThirdLineAddr_LC);
//                paramList.add(scrnMsg.A.no(selectIdx).shipToFrthLineAddr_LC);
//                paramList.add(scrnMsg.A.no(selectIdx).shipToCntyNm_LC);
//                paramList.add(scrnMsg.A.no(selectIdx).shipToProvNm_LC);
//                paramList.add(scrnMsg.A.no(selectIdx).shipToCtryCd_LC); // S21_NA#11429
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//            } else {
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.C.no(selectIdx).shipToFirstLineAddr_RC);
//                paramList.add(scrnMsg.C.no(selectIdx).shipToCtyAddr_RC);
//                paramList.add(scrnMsg.C.no(selectIdx).shipToStCd_RC);
//                paramList.add(scrnMsg.C.no(selectIdx).shipToPostCd_RC);
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.P.no(1).xxPopPrm); // Active
//                // Only
//                paramList.add(scrnMsg.P.no(2).xxPopPrm); // Ship To's
//                // Only
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.C.no(selectIdx).shipToCustLocCd_RC);
//                paramList.add(scrnMsg.C.no(selectIdx).shipToScdLineAddr_RC);
//                paramList.add(scrnMsg.C.no(selectIdx).shipToThirdLineAddr_RC);
//                paramList.add(scrnMsg.C.no(selectIdx).shipToFrthLineAddr_RC);
//                paramList.add(scrnMsg.C.no(selectIdx).shipToCntyNm_RC);
//                paramList.add(scrnMsg.C.no(selectIdx).shipToProvNm_RC);
//                paramList.add(scrnMsg.C.no(selectIdx).shipToCtryCd_RC); // S21_NA#11429
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//                paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
//            }
//        }

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NMAL6770 For Sold To
     * @param scrnMsg NWAL2200BMsg
     * @return Param NMAL6770 For Sold To
     */
    public static Object[] getParamNMAL6760ForSoldTo(NWAL2200BMsg scrnMsg) {

        // 2018/06/21 QC#14307 Add Start
        String funcCatgId = scrnMsg.P.no(2).xxPopPrm.getValue();
        String trxRuleTp = scrnMsg.P.no(3).xxPopPrm.getValue();
        // 2018/06/21 QC#14307 Add End

        scrnMsg.firstLineAddr.clear();
        scrnMsg.scdLineAddr.clear();
        scrnMsg.thirdLineAddr.clear();
        scrnMsg.frthLineAddr.clear();
        scrnMsg.ctyAddr.clear();
        scrnMsg.stCd.clear();
        scrnMsg.postCd.clear();
        ZYPTableUtil.clear(scrnMsg.P);

        // 2018/06/21 QC#14307 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, funcCatgId);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, trxRuleTp);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, scrnMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, ZYPConstant.FLG_OFF_N);
        if (ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, ZYPConstant.FLG_ON_Y);
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(10).xxPopPrm, ZYPConstant.FLG_ON_Y);
        // 2018/06/21 QC#14307 Add End

        // Mod Start 2016/08/04 QC#9078
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, NMAL6760Constant.STATUS_CD_ACTIVE);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, NMAL6760Constant.DISP_HRCH_ACCT_CD_BILL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, NMAL6760_STATUS_CD_ACTIVE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, NMAL6760_DISP_HRCH_ACCT_CD_BILL);
        // Mod End 2016/08/04 QC#9078

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.sellToCustCd);
        paramList.add(scrnMsg.soldToCustAcctNm);
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.firstLineAddr);
        paramList.add(scrnMsg.ctyAddr);
        paramList.add(scrnMsg.stCd);
        paramList.add(scrnMsg.postCd);
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(1).xxPopPrm); // Active Only
        paramList.add(scrnMsg.P.no(2).xxPopPrm); // Bill To's Only
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.soldToCustLocCd);
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.scdLineAddr);
        paramList.add(scrnMsg.thirdLineAddr);
        paramList.add(scrnMsg.frthLineAddr);
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // no used

        // 2018/06/21 QC#14307 Add Start
        paramList.add(scrnMsg.P.no(8).xxPopPrm); // Display Hierarkey Active Flag
        paramList.add(scrnMsg.P.no(9).xxPopPrm); // Account Number Active Flag
        paramList.add(scrnMsg.P.no(10).xxPopPrm); // Status Active Flag
        
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [27]
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [28]
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [29]
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [30]
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [31]
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [32]
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [33]
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [34]
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [35]
        paramList.add(scrnMsg.P.no(5).xxPopPrm); // [36] Display Control Customer Special Instruction
        paramList.add(scrnMsg.P.no(4).xxPopPrm); // [37] Transaction Type
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // [38] Business Area
        paramList.add(scrnMsg.P.no(6).xxPopPrm); // [39] Function ID
        paramList.add(scrnMsg.P.no(3).xxPopPrm); // [40] Function Category ID
        paramList.add(scrnMsg.P.no(7).xxPopPrm); // [41] Line of Business Code
        // 2018/07/10 S21_NA#26661,26713 Add End

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NWAL1130 For Warehouse
     * @param scrnMsg NWAL2200BMsg
     * @return Param NWAL1130 For Warehouse
     */
    public static Object[] getParamNWAL1130ForWh(NWAL2200BMsg scrnMsg) {

        int selectIndex = scrnMsg.xxCellIdx.getValueInt();
        String rtlWhNm = null;
        //String rtlSubWhNm = null; // Del 2016/08/31 QC#14020

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            rtlWhNm = scrnMsg.B.no(selectIndex).rtlWhNm_LL.getValue();
            // rtlSubWhNm = scrnMsg.B.no(selectIndex).rtlSwhNm_LL.getValue(); // Del 2016/08/31 QC#14020
        } else {
            rtlWhNm = scrnMsg.D.no(selectIndex).rtlWhNm_RL.getValue();
            // rtlSubWhNm = scrnMsg.D.no(selectIndex).rtlSwhNm_RL.getValue(); // Del 2016/08/31 QC#14020
        }

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Ship From Warehouse Search";
        params[2] = getSlctTblNmForWh(scrnMsg);

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Warehouse Name";
        whereArray0[1] = "UPPER(RTL_WH_NM)";
        // Mod Start 2016/08/31 QC#14020
        // if (S21StringUtil.isEmpty(rtlWhNm) && S21StringUtil.isEmpty(rtlSubWhNm)) {
        if (S21StringUtil.isEmpty(rtlWhNm)) {
            // Mod End 2016/08/31 QC#14020
            whereArray0[2] = "%";
        } else {
            whereArray0[2] = rtlWhNm;
        }
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        // Del Start 2016/08/31 QC#14020
        // Object[] whereArray1 = new Object[4];
        // whereArray1[0] = "Sub Warehouse Name";
        // whereArray1[1] = "UPPER(RTL_SWH_NM)";
        // whereArray1[2] = rtlSubWhNm;
        // whereArray1[3] = ZYPConstant.FLG_ON_Y;
        // whereList.add(whereArray1);
        // Del End 2016/08/31 QC#14020

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Warehouse Code";
        columnArray0[1] = "RTL_WH_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Warehouse Name";
        columnArray1[1] = "RTL_WH_NM";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        // Del Start 2016/08/31 QC#14020
        // Object[] columnArray2 = new Object[4];
        // columnArray2[0] = "Sub Warehouse Code";
        // columnArray2[1] = "RTL_SWH_CD";
        // columnArray2[2] = BigDecimal.valueOf(20);
        // columnArray2[3] = ZYPConstant.FLG_ON_Y;
        // columnList.add(columnArray2);
        //
        // Object[] columnArray3 = new Object[4];
        // columnArray3[0] = "Sub Warehouse Name";
        // columnArray3[1] = "RTL_SWH_NM";
        // columnArray3[2] = BigDecimal.valueOf(30);
        // columnArray3[3] = ZYPConstant.FLG_OFF_N;
        // columnList.add(columnArray3);
        // Del End 2016/08/31 QC#14020

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "RTL_WH_CD";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        // Del Start 2016/08/31 QC#14020
        // Object[] sortConditionArray1 = new Object[2];
        // sortConditionArray1[0] = "RTL_SWH_CD";
        // sortConditionArray1[1] = "ASC";
        // sortConditionList.add(sortConditionArray1);
        // Del End 2016/08/31 QC#14020

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Select Table Name For Warehouse
     * @param scrnMsg NWAL2200BMsg
     * @return Select Table Name For Warehouse
     */
    private static String getSlctTblNmForWh(NWAL2200BMsg scrnMsg) {

        String glblCmpyCd = scrnMsg.glblCmpyCd.getValue();
        String slsDt = scrnMsg.slsDt.getValue();
        String dsOrdTpCd = scrnMsg.dsOrdTpCd.getValue();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    WH.GLBL_CMPY_CD");
        sb.append("   ,WH.EZCANCELFLAG");
        sb.append("   ,WH.RTL_WH_CD");
        sb.append("   ,WH.RTL_WH_NM ");
        // Del Start 2016/08/31 QC#14020
        // sb.append("   ,WH.RTL_SWH_CD");
        // sb.append("   ,WH.RTL_SWH_NM ");
        // Del End 2016/08/31 QC#14020
        sb.append("FROM");

        if (ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            sb.append("    DS_INVTY_LOC_V WH ");
            sb.append("WHERE ");
            sb.append("        WH.GLBL_CMPY_CD                 = '").append(glblCmpyCd).append("'");
            sb.append("    AND WH.EZCANCELFLAG                 = '0'");
            sb.append("    AND WH.RGTN_STS_CD                  = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
            sb.append("    AND WH.EFF_FROM_DT                  <= '").append(slsDt).append("'");
            sb.append("    AND NVL(WH.EFF_THRU_DT, '99991231') >= '").append(slsDt).append("'");
            sb.append("    AND EXISTS(");
            // 2018/10/18 S21_NA#28712 Mod Start
            // sb.append("        SELECT");
            sb.append("        SELECT /*+ USE_NL(LRR LU) INDEX(LU LOC_USG_I3) */");
            // 2018/10/18 S21_NA#28712 Mod End
            sb.append("            *");
            sb.append("        FROM    ");
            sb.append("             DS_ORD_TP_LOC_ROLE_RELN    LRR   ");
            sb.append("            ,LOC_USG                    LU");
            sb.append("        WHERE");
            sb.append("                LRR.DS_ORD_TP_CD        = '").append(dsOrdTpCd).append("'");
            sb.append("            AND LRR.EZCANCELFLAG        = '0'");
            sb.append("            AND LRR.GLBL_CMPY_CD        = LU.GLBL_CMPY_CD");
            sb.append("            AND LRR.LOC_ROLE_TP_CD      = LU.LOC_ROLE_TP_CD");
            sb.append("            AND LU.EZCANCELFLAG         = '0'");
            sb.append("            AND LU.GLBL_CMPY_CD         = WH.GLBL_CMPY_CD");
            sb.append("            AND LU.PTY_LOC_PK           = WH.PTY_LOC_PK");
            sb.append("    )");
        } else {
            sb.append("    DS_INVTY_LOC_V                       WH ");
            sb.append("WHERE");
            sb.append("        WH.GLBL_CMPY_CD                  = '").append(glblCmpyCd).append("'");
            sb.append("    AND WH.RGTN_STS_CD                   = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
            sb.append("    AND WH.EFF_FROM_DT                   <= '").append(slsDt).append("'");
            sb.append("    AND NVL(WH.EFF_THRU_DT, '99991231')  >= '").append(slsDt).append("'");
            sb.append("    AND WH.EZCANCELFLAG                  = '0'");
        }

        return sb.toString();
    }

    // Add Start 2016/08/31 QC#14020
    /**
     * Get Param NWAL1130 For Sub Warehouse
     * @param scrnMsg NWAL2200BMsg
     * @return Param NWAL1130 For Sub Warehouse
     */
    public static Object[] getParamNWAL1130ForSubWh(NWAL2200BMsg scrnMsg) {

        NWAL2200_BBMsg itemLineMsg = scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt());
        String rtlWhCd = itemLineMsg.rtlWhCd_LL.getValue();
        String rtlSubWhNm = itemLineMsg.rtlSwhNm_LL.getValue();

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Sub Warehouse Search";
        params[2] = getSlctTblNmForSubWh(scrnMsg, rtlWhCd);

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray = new Object[4];
        whereArray[0] = "Sub Warehouse Name";
        whereArray[1] = "UPPER(RTL_SWH_NM)";

        if (ZYPCommonFunc.hasValue(rtlSubWhNm)) {
            whereArray[2] = rtlSubWhNm;
        } else {
            whereArray[2] = "%";
        }
        whereArray[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Sub Warehouse Code";
        columnArray0[1] = "RTL_SWH_CD";
        columnArray0[2] = BigDecimal.valueOf(30);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Sub Warehouse Name";
        columnArray1[1] = "RTL_SWH_NM";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "RTL_SWH_CD";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Select Table Name For Sub Werehouse
     * @param scrnMsg NWAL2200BMsg
     * @param rtlWhCd Retail Werehouse Code
     * @return Select Table Name For Sub Werehouse
     */
    private static String getSlctTblNmForSubWh(NWAL2200BMsg scrnMsg, String rtlWhCd) {

        String glblCmpyCd = scrnMsg.glblCmpyCd.getValue();
        String slsDt = scrnMsg.slsDt.getValue();
        String dsOrdTpCd = scrnMsg.dsOrdTpCd.getValue();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    WH.GLBL_CMPY_CD");
        sb.append("   ,WH.EZCANCELFLAG");
        sb.append("   ,WH.RTL_SWH_CD");
        sb.append("   ,WH.RTL_SWH_NM ");
        sb.append("FROM");

        if (ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            sb.append("    DS_INVTY_LOC_V WH ");
            sb.append("WHERE ");
            sb.append("        WH.GLBL_CMPY_CD                 = '").append(glblCmpyCd).append("'");
            sb.append("    AND WH.EZCANCELFLAG                 = '0'");
            sb.append("    AND WH.RGTN_STS_CD                  = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
            sb.append("    AND WH.EFF_FROM_DT                  <= '").append(slsDt).append("'");
            sb.append("    AND NVL(WH.EFF_THRU_DT, '99991231') >= '").append(slsDt).append("'");
            sb.append("    AND EXISTS(");
            // 2018/10/18 S21_NA#28712 Mod Start
            // sb.append("        SELECT");
            sb.append("        SELECT /*+ USE_NL(LRR LU) INDEX(LU LOC_USG_I3) */");
            // 2018/10/18 S21_NA#28712 Mod End
            sb.append("            *");
            sb.append("        FROM    ");
            sb.append("             DS_ORD_TP_LOC_ROLE_RELN    LRR   ");
            sb.append("            ,LOC_USG                    LU");
            sb.append("        WHERE");
            sb.append("                LRR.DS_ORD_TP_CD        = '").append(dsOrdTpCd).append("'");
            sb.append("            AND LRR.EZCANCELFLAG        = '0'");
            sb.append("            AND LRR.GLBL_CMPY_CD        = LU.GLBL_CMPY_CD");
            sb.append("            AND LRR.LOC_ROLE_TP_CD      = LU.LOC_ROLE_TP_CD");
            sb.append("            AND LU.EZCANCELFLAG         = '0'");
            sb.append("            AND LU.GLBL_CMPY_CD         = WH.GLBL_CMPY_CD");
            sb.append("            AND LU.PTY_LOC_PK           = WH.PTY_LOC_PK");
            sb.append("    )");
        } else {
            sb.append("    DS_INVTY_LOC_V                       WH ");
            sb.append("WHERE");
            sb.append("        WH.GLBL_CMPY_CD                  = '").append(glblCmpyCd).append("'");
            sb.append("    AND WH.RGTN_STS_CD                   = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
            sb.append("    AND WH.EFF_FROM_DT                   <= '").append(slsDt).append("'");
            sb.append("    AND NVL(WH.EFF_THRU_DT, '99991231')  >= '").append(slsDt).append("'");
            sb.append("    AND WH.EZCANCELFLAG                  = '0'");
        }

        if (ZYPCommonFunc.hasValue(rtlWhCd)) {
            sb.append("    AND WH.RTL_WH_CD                     = '").append(rtlWhCd).append("'");
        }

        return sb.toString();
    }
    // Add End 2016/08/31 QC#14020
    /**
     * Combine Customer Address
     * @param scrnMsg NWAL2200BMsg
     * @return Customer Address
     */
    public static String cmbnAddr(NWAL2200BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.shipToFirstLineAddr)) {
            return null;
        }

        StringBuilder addr = new StringBuilder(scrnMsg.shipToFirstLineAddr.getValue());

        if (ZYPCommonFunc.hasValue(scrnMsg.shipToScdLineAddr)) {
            addr.append(SPACE);
            addr.append(scrnMsg.shipToScdLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToThirdLineAddr)) {
            addr.append(SPACE);
            addr.append(scrnMsg.shipToThirdLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToFrthLineAddr)) {
            addr.append(SPACE);
            addr.append(scrnMsg.shipToFrthLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToCtyAddr)) {
            addr.append(SPACE);
            addr.append(scrnMsg.shipToCtyAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToStCd)) {
            addr.append(COMMA);
            addr.append(scrnMsg.shipToStCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToPostCd)) {
            addr.append(SPACE);
            addr.append(scrnMsg.shipToPostCd.getValue());
        }

        return addr.toString();
    }
    // QC#13688 2017/02/24 Add Start
    /**
     * Get Param NMAL6050 For Freight Term
     * @param scrnMsg NWAL2200BMsg
     * @return Param NMAL6050
     */
    public static Object[] getParamNMAL6050ForFrtTerm(NWAL2200BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.P);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();

        // [0]:TBL_NM
        scrnMsg.P.no(0).xxPopPrm.setValue("FRT_COND");
        paramList.add(scrnMsg.P.no(0).xxPopPrm);

        // [1]:TBL_CD_COL_NM
        scrnMsg.P.no(1).xxPopPrm.setValue("FRT_COND_CD");
        paramList.add(scrnMsg.P.no(1).xxPopPrm);

        // [2]:TBL_NM_COL_NM
        scrnMsg.P.no(2).xxPopPrm.setValue("FRT_COND_DESC_TXT");
        paramList.add(scrnMsg.P.no(2).xxPopPrm);

        // [3]:TBL_SORT_COL_NM
        scrnMsg.P.no(3).xxPopPrm.setValue("FRT_COND_SORT_NUM");
        paramList.add(scrnMsg.P.no(3).xxPopPrm);

        // [4]:SCR_NM
        scrnMsg.P.no(4).xxPopPrm.setValue("Freight Terms Search");
        paramList.add(scrnMsg.P.no(4).xxPopPrm);

        // [5]:HDR_CD_LB_NM
        scrnMsg.P.no(5).xxPopPrm.setValue("Freight Terms Code");
        paramList.add(scrnMsg.P.no(5).xxPopPrm);

        // [6]:HDR_NM_LB_NM
        scrnMsg.P.no(6).xxPopPrm.setValue("Freight Terms Name");
        paramList.add(scrnMsg.P.no(6).xxPopPrm);

        // [7]:DTL_CD_LB_NM
        scrnMsg.P.no(7).xxPopPrm.setValue("Freight Terms Code");
        paramList.add(scrnMsg.P.no(7).xxPopPrm);

        // [8]:DTL_NM_LB_NM
        scrnMsg.P.no(8).xxPopPrm.setValue("Freight Terms Name");
        paramList.add(scrnMsg.P.no(8).xxPopPrm);

        // [9]:COND_CD
        scrnMsg.P.no(9).xxPopPrm.clear();
        // 2018/12/21 QC#29315 Add Start
        if (!ZYPCommonFunc.hasValue(scrnMsg.frtCondDescTxt)) {
            scrnMsg.frtCondCd.clear();
        }
        paramList.add(scrnMsg.frtCondCd);

        // [10]:COND_NM
        paramList.add(scrnMsg.frtCondDescTxt);

        return paramList.toArray(new EZDBItem[0]);
    }
    /**
     * Get Param NWAL1130 For Carrier Service Level
     * @param scrnMsg NWAL2200BMsg
     * @return Param NWAL1130 Carrier Service Level
     */
    public static Object[] getParamNWAL1130ForCarrSvcLvl(NWAL2200BMsg scrnMsg) {

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Carrier Service Level Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("    CSL.EZCANCELFLAG ");
        sb.append("   ,CSL.GLBL_CMPY_CD ");
        sb.append("   ,CSL.CARR_SVC_LVL_CD ");
        sb.append("   ,CSL.CARR_SVC_LVL_DESC_TXT ");
        sb.append("   ,CSL.CARR_SVC_LVL_SORT_NUM ");
        sb.append("FROM  ");
        sb.append("    FRT_COND_SVC_LVL_RELN RELN ");
        sb.append("   ,DS_ORD_TP_PROC_DFN OTD ");
        sb.append("   ,CARR_SVC_LVL CSL ");
        sb.append("WHERE ");
        sb.append("    RELN.GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append("AND RELN.EZCANCELFLAG    = '0' ");
        sb.append("AND OTD.GLBL_CMPY_CD     = RELN.GLBL_CMPY_CD ");
        sb.append("AND OTD.EZCANCELFLAG     = '0' ");
        sb.append("AND OTD.DS_ORD_TP_CD     = '").append(scrnMsg.dsOrdTpCd.getValue()).append("' ");
        sb.append("AND OTD.LINE_BIZ_TP_CD   = RELN.LINE_BIZ_TP_CD ");
        sb.append("AND RELN.FRT_COND_CD     = '").append(scrnMsg.frtCondCd.getValue()).append("'");
        sb.append("AND RELN.SHPG_SVC_LVL_CD = '").append(scrnMsg.shpgSvcLvlCd.getValue()).append("'");
        sb.append("AND CSL.GLBL_CMPY_CD     = RELN.GLBL_CMPY_CD ");
        sb.append("AND CSL.EZCANCELFLAG     = '0' ");
        sb.append("AND CSL.CARR_SVC_LVL_CD  = RELN.CARR_SVC_LVL_CD ");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray = new Object[4];
        whereArray[0] = "Carr Svc Lvl Nm";
        whereArray[1] = "CARR_SVC_LVL_DESC_TXT";
        if (!ZYPCommonFunc.hasValue(scrnMsg.carrSvcLvlDescTxt)) { //S21_NA#8393
            whereArray[2] = "%";
        } else {
            whereArray[2] = scrnMsg.carrSvcLvlDescTxt.getValue();
        }
        whereArray[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Carr Svc Lvl Cd";
        columnArray0[1] = "CARR_SVC_LVL_CD";
        columnArray0[2] = BigDecimal.valueOf(13);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Carr Svc Lvl Nm";
        columnArray1[1] = "CARR_SVC_LVL_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "CARR_SVC_LVL_SORT_NUM";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        return params;
    }
    // QC#13688 2017/02/24 Add End
    
    // S21_NA#14307 2018/07/25 Add Start
    public static Serializable getArgForSubScreen(NWAL2200BMsg scrnMsg, String glblCmpyCd) {

        List<Object> parmeters = new ArrayList<Object>();

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int idx = scrnMsg.xxCellIdx.getValueInt();

        // [0] : Global Company Code
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, glblCmpyCd);
        parmeters.add(scrnMsg.P.no(0).xxPopPrm);

        // [1] : Function ID
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, BIZ_ID);
        parmeters.add(scrnMsg.P.no(1).xxPopPrm);

        // [2] : Function Category ID
        // 2018/07/10 S21_NA#26661,26713 Del Start
        // ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, "");
        // 2018/07/10 S21_NA#26661,26713 Del End
        parmeters.add(scrnMsg.P.no(2).xxPopPrm);

        // [3] : Transaction Type
        parmeters.add(scrnMsg.P.no(3).xxPopPrm);

        // [4] : Business Area
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, "");
        parmeters.add(scrnMsg.P.no(4).xxPopPrm);

        // [5] : Customer Special Instruction Line Suffix
        parmeters.add("QL");

        // [6] : Customer Special Instruction Line
        int index = 0;

        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).dsAcctNum_QL, scrnMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).billToCustCd_QL, "");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index++).shipToCustCd_QL, "");

        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).dsAcctNum_QL, "");

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).billToCustCd_QL, scrnMsg.A.no(idx).billToCustLocCd_LC);
        } else if (TAB_RMA.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).billToCustCd_QL, scrnMsg.C.no(idx).billToCustLocCd_RC);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).billToCustCd_QL, scrnMsg.billToCustCd);
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index++).shipToCustCd_QL, "");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).dsAcctNum_QL, "");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).billToCustCd_QL, "");

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).shipToCustCd_QL, scrnMsg.A.no(idx).shipToCustLocCd_LC);
        } else if (TAB_RMA.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).shipToCustCd_QL, scrnMsg.C.no(idx).shipToCustLocCd_RC);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index++).shipToCustCd_QL, scrnMsg.shipToCustCd);
        }

        scrnMsg.Q.setValidCount(index);
        parmeters.add(scrnMsg.Q);

        // 2018/07/10 S21_NA#26661,26713 Add Start
        // [7] : Line of Business Code
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, scrnMsg.lineBizTpCd);
        parmeters.add(scrnMsg.P.no(5).xxPopPrm);
        // 2018/07/10 S21_NA#26661,26713 Add End

        return parmeters.toArray(new Object[0]);
    }
    // S21_NA#14307 2018/07/25 Add End
}
