/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL1130.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFBL1130.NFBL1130CMsg;
import business.blap.NFBL1130.NFBL1130SMsg;
import business.blap.NFBL1130.NFBL1130Query;
import business.blap.NFBL1130.constant.NFBL1130Constant;
import business.db.CM_ACRL_WRITE_OFFTMsg;
import business.db.SAVE_SRCH_OPTTMsg;
import business.db.SAVE_SRCH_OPTTMsgArray;
import business.file.NFBL1130F00FMsg;
import business.parts.NFZC102001PMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * AP Accrual Write-off Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/09/28   Hitachi         K.Kasai         Update          QC#14130
 * 2016/09/29   Hitachi         K.Kojima        Update          QC#14178
 * 2016/09/30   Hitachi         K.Kojima        Update          QC#14179
 * 2016/10/06   Hitachi         K.Kojima        Update          QC#11613
 * 2017/01/12   Fujitsu         T.Murai         Update          QC#16928
 * 2019/01/30   Fujitsu         H.Ikeda         Update          QC#30057
 * 2019/02/26   Fujitsu         S.Ohki          Update          QC#30505
 * 2019/03/12   Hitachi         Y.Takeno        Update          QC#30729
 * 2022/03/28   Hitachi         A.Kohinata      Update          QC#57935(56588)
 * 2022/04/05   Hitachi         R.Onozuka       Update          QC#57935
 * 2022/08/05   Hitachi         A.Kohinata      Update          QC#59245
 * </pre>
 */
public class NFBL1130CommonLogic implements NFBL1130Constant {

    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();

    /**
     * Method name: createAcrlCoaAcctCdPulldownList
     * <dd>The method explanation: Create pulldown list.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @SuppressWarnings("unchecked")
    public static void createAcrlCoaAcctCdPulldownList(EZDCMsg cMsg) {

        NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;

        S21SsmEZDResult ssmResult = NFBL1130Query.getInstance().getAcrlCoaAcctCdPulldownValue();

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                
                // START 2017/01/12 [QC#16928,MOD]
                // ZYPEZDItemValueSetter.setValue(bizMsg.acrlCoaAcctCd_C.no(i), (String) map.get(ACRL_COA_ACCT_CD));
                ZYPEZDItemValueSetter.setValue(bizMsg.acrlCoaAcctCd_C.no(i), (String) map.get(COA_ACCT_CD));
                // END   2017/01/12 [QC#16928,MOD]
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyByItemNm_D.no(i), (String) map.get(XX_DPLY_BY_ITEM_NM));
            }
        }

    }

    /**
     * Method name: getAcrlWrtOffRsnPulldownValue
     * <dd>The method explanation: Get pulldown list value.
     * <dd>Remarks:
     * @return resultList List 
     */
    @SuppressWarnings("unchecked")
    public static List getAcrlWrtOffRsnPulldownValue() {

        S21SsmEZDResult ssmResult = NFBL1130Query.getInstance().getAcrlWrtOffRsnPulldownValue();

        List resultList = new ArrayList();
        if (ssmResult.isCodeNormal()) {
            resultList = (List) ssmResult.getResultObject();
        }
        return resultList;

    }

    /**
     * Method name: createAcrlWrtOffRsnPulldownList
     * <dd>The method explanation: Create pulldown list.
     * <dd>Remarks:
     * @param listAcrlWrtOffRsn List
     * @param sMsg Global area information
     * @param index int
     */
    @SuppressWarnings("unchecked")
    public static void createAcrlWrtOffRsnPulldownList(List listAcrlWrtOffRsn, EZDSMsg sMsg, int index) {

        NFBL1130SMsg globalMsg = (NFBL1130SMsg) sMsg;

        for (int i = 0; i < listAcrlWrtOffRsn.size(); i++) {
            Map map = (Map) listAcrlWrtOffRsn.get(i);
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(index).acrlWrtOffRsnCd_C.no(i), (String) map.get(ACRL_WRT_OFF_RSN_CD));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(index).acrlWrtOffRsnNm_D.no(i), (String) map.get(ACRL_WRT_OFF_RSN_NM));
        }

    }
    
    /**
     * Method name: setLocNm
     * <dd>The method explanation: Set Supplier Name.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    @SuppressWarnings("unchecked")
    public static void setPrntVndNm(EZDCMsg cMsg) {

        NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;

        // START 2016/09/30 K.Kojima [QC#14179,MOD]
        // S21SsmEZDResult ssmResult =
        // NFBL1130Query.getInstance().getPrntVndNm(bizMsg.apVndCd.getValue());
        S21SsmEZDResult ssmResult = NFBL1130Query.getInstance().getPrntVndNm(bizMsg.prntVndCd.getValue());
        // END 2016/09/30 K.Kojima [QC#14179,MOD]

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            if (resultList.size() > 0) {
                Map map = (Map) resultList.get(0);
                ZYPEZDItemValueSetter.setValue(bizMsg.prntVndNm, (String)map.get(PRNT_VND_NM));
            }
        } else {
            bizMsg.prntVndNm.clear();
        }
    }

    /**
     * Method name: searchRecord
     * <dd>The method explanation: Get record for LINES tab.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @SuppressWarnings("unchecked")
    public static boolean searchRecord(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "searchRecord================================START", null);

        NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;
        NFBL1130SMsg globalMsg = (NFBL1130SMsg) sMsg;

        // LINES tab
        globalMsg.A.setValidCount(0);
        globalMsg.A.clear();
        bizMsg.A.setValidCount(0);
        bizMsg.A.clear();

        S21SsmEZDResult ssmResult = NFBL1130Query.getInstance().searchRecord(bizMsg, globalMsg);

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();

            if (resultList.size() > globalMsg.A.length()) {
                bizMsg.A.setValidCount(bizMsg.A.length());
                globalMsg.A.setValidCount(globalMsg.A.length());
                bizMsg.xxPageShowFromNum_A.setValue(1);
                bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.length());
                bizMsg.xxPageShowOfNum_A.setValue(globalMsg.A.getValidCount());

                bizMsg.setMessageInfo(NFBM0001W, new String[] {Long.toString(globalMsg.A.length()), Long.toString(resultList.size()) });

            } else {
                globalMsg.A.setValidCount(resultList.size());
                bizMsg.xxPageShowFromNum_A.setValue(1);
                bizMsg.xxPageShowOfNum_A.setValue(resultList.size());

                if (bizMsg.A.length() < globalMsg.A.getValidCount()) {
                    bizMsg.A.setValidCount(bizMsg.A.length());
                    bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.length());
                } else {
                    bizMsg.A.setValidCount(resultList.size());
                    bizMsg.xxPageShowToNum_A.setValue(resultList.size());
                }
                // The search ended normally
                bizMsg.setMessageInfo(ZZM8002I);

            }

            List listAcrlWrtOffRsn = new ArrayList();
            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (i == 0) {
                    listAcrlWrtOffRsn = getAcrlWrtOffRsnPulldownValue();
                }
                Map map = (Map) resultList.get(i);
                // ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTime_A1, (String) map.get(EZUPTIME));
                // ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTimeZone_A1, (String) map.get(EZUPTIMEZONE));
                // ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).cmAcrlWriteOffPk_A1, (BigDecimal) map.get(CM_ACRL_WRITE_OFF_PK));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).poNum_A1, (String) map.get(PO_NUM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).delyOrdNum_A1, (String) map.get(DELY_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).stkInDt_A1, (String) map.get(STK_IN_DT));
                // START 2016/09/29 K.Kojima [QC#14178,ADD]
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).rwsNum_A1, (String) map.get(RWS_NUM));
                // END 2016/09/29 K.Kojima [QC#14178,ADD]
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apVndCd_A1, (String) map.get(AP_VND_CD)); // Supplier#
                // START 2016/09/30 K.Kojima [QC#14179,ADD]
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).prntVndCd_A1, (String) map.get(PRNT_VND_CD)); // Supplier Number
                // END 2016/09/30 K.Kojima [QC#14179,ADD]
                // START 2016/09/30 K.Kojima [QC#14179,MOD]
                // ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).prntVndNm_A1,
                // (String) map.get(PRNT_VND_NM)); // Supplier Name
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).dplyVndNm_A1, (String) map.get(DPLY_VND_NM)); // Supplier Name
                // END 2016/09/30 K.Kojima [QC#14179,MOD]
                // START 2018/11/13 J.Kim [QC#23037,MOL]
                // ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apVndInvSqNum_A1, (String) map.get(AP_VND_INV_SQ_NUM)); // Line
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).poOrdDtlLineNum_A1, (String) map.get(PO_ORD_DTL_LINE_NUM)); // Line
                // END 2018/11/13 J.Kim [QC#23037,MOL]
                // START 2018/11/08 J.Kim [QC#23037,DEL]
                // ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).vndUomCd_A1, (String) map.get(UOM_CD)); // UoM
                // END 2016/10/05 J.Kim [QC#23037,DEL]
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invRcvQty_A1, (BigDecimal) map.get(INV_RCV_QTY)); // Receipt Qty
                // START 2019/02/26 S.Ohki [QC#30505,MOD]
//                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).thisMthFobCostAmt_A1, (BigDecimal) map.get(THIS_MTH_FOB_COST_AMT)); // Unit Price
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).thisMthFobCostAmt_A1, (BigDecimal) map.get(ENT_DEAL_NET_UNIT_PRC_AMT_DR)); // Unit Price
                // END 2019/02/26 S.Ohki [QC#30505,MOD]
                // START 2018/11/08 J.Kim [QC#23037,MOL]
                // ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).acInvJrnlCostAmt_A1, (BigDecimal) map.get(AC_INV_JRNL_COST_AMT));
                // START 2019/02/26 S.Ohki [QC#30505,MOD]
//                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).acInvJrnlCostAmt_A1, (BigDecimal) map.get(AC_INV_JRNL_COST_DR_AMT)); // Receipt Amt
                // mod start 2022/08/05 QC#59245
                //ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).acInvJrnlCostAmt_A1, (BigDecimal) map.get(RECEIPT_AMT)); // Receipt Amt
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).acInvJrnlCostAmt_A1, (BigDecimal) map.get(RCV_TOT_AMT)); // Receipt Amt
                // mod end 2022/08/05 QC#59245
                // END 2019/02/26 S.Ohki [QC#30505,MOD]
                // END 2018/11/08 J.Kim [QC#23037,MOL]
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaAcctCd_A1, (String) map.get(COA_ACCT_CD)); // Account Accrual
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).mdseCd_A1, (String) map.get(MDSE_CD)); // Item
                // START 2016/10/06 K.Kojima [QC#11613,MOD]
                // ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).mdseNm_A1,
                // (String) map.get(MDSE_NM)); // Description
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).mdseDescShortTxt_A1, (String) map.get(MDSE_DESC_SHORT_TXT)); // Description
                // END 2016/10/06 K.Kojima [QC#11613,MOD]
                // add start 2022/03/28 QC#57935(56588)
                if (!map.get(MDSE_CD).equals(map.get(INV_MDSE_CD))) {
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).mdseCd_A2, (String) map.get(INV_MDSE_CD)); // Item
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).mdseDescShortTxt_A2, (String) map.get(INV_MDSE_DESC_SHORT_TXT)); // Description
                }
                // add end 2022/03/28 QC#57935(56588)
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apVndInvNum_A1, (String) map.get(AP_VND_INV_NUM)); // Payable Invoice
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invDt_A1, (String) map.get(INV_DT)); // Invoice Date
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invQty_A1, (BigDecimal) map.get(INV_QTY)); // Invoice Qty
                // START 2016/09/28 [QC#14130, ADD]
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).acInvJrnlCostAmt_A2, (BigDecimal) map.get(ENT_DEAL_NET_UNIT_PRC_AMT)); // Invoice Price
                // END 2016/09/28 [QC#14130, ADD]
                // START 2018/11/08 J.Kim [QC#23037,MOL]
                // ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).acInvJrnlCostAmt_A3, (BigDecimal) map.get(AC_INV_JRNL_COST_AMT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).acInvJrnlCostAmt_A3, (BigDecimal) map.get(AC_INV_JRNL_COST_CR_AMT)); // Invoice Amt
                // START 2018/11/08 J.Kim [QC#23037,MOL]
                // START 2022/04/05 R.Onozuka [QC57935, ADD]
                // del start 2022/08/05 QC#59245
                //BigDecimal woInvPrice = (BigDecimal) map.get(ENT_DEAL_NET_UNIT_PRC_AMT);
                //BigDecimal invPrice = (BigDecimal) map.get(INV_UNIT_PRC_AMT);
                //if (woInvPrice.compareTo(invPrice) != 0) {
                //    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).acInvJrnlCostAmt_A2, (BigDecimal) map.get(INV_UNIT_PRC_AMT)); // Invoice Unit Price
                //    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).acInvJrnlCostAmt_A3, (BigDecimal) map.get(AC_OC_TOT_INV_AMT)); // Invoice Amt
                //}
                // del end 2022/08/05 QC#59245
                // END 2022/04/05 R.Onozuka [QC57935, ADD]
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).wrtOffFlg_A1, (String) map.get(WRT_OFF_FLG)); // Write off
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).wrtOffFlg_BF, (String) map.get(WRT_OFF_FLG)); // Write off(Before Change)
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).wrtOffDt_A1, (String) map.get(WRT_OFF_DT)); // Write off Date
                // add start 2022/03/28 QC#57935(56588)
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).wrtOffDt_BF, (String) map.get(WRT_OFF_DT)); // Write off Date(Before Change)
                // add end 2022/03/28 QC#57935(56588)
                // Create [Write off Reason] pulldown.
                NFBL1130CommonLogic.createAcrlWrtOffRsnPulldownList(listAcrlWrtOffRsn, globalMsg, i);
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).acrlWrtOffRsnCd_S, (String) map.get(ACRL_WRT_OFF_RSN_CD)); // Write off Reason
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).acrlWrtOffCmntTxt_A1, (String) map.get(ACRL_WRT_OFF_CMNT_TXT)); // Remarks

                // For exclusive control start
                // START 2019/01/30 H.Ikeda [QC#30057, MOD]
//                List<Map> listCmAcrlWriteOffPk = getCmAcrlWriteOffPkList(globalMsg.A.no(i).poNum_A1.getValue(), globalMsg.A.no(i).apVndCd_A1.getValue(), globalMsg.A.no(i).mdseCd_A1.getValue());
//                globalMsg.B.setValidCount(listCmAcrlWriteOffPk.size());
//                for (int j = 0; j < globalMsg.B.getValidCount(); j++) {
//                    Map mapCmAcrlWriteOffPk = (Map) listCmAcrlWriteOffPk.get(j);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.B.no(j).cmAcrlWriteOffPk_B1, (BigDecimal) mapCmAcrlWriteOffPk.get(CM_ACRL_WRITE_OFF_PK));
//                    ZYPEZDItemValueSetter.setValue(globalMsg.B.no(j).ezUpTime_B1, (String) mapCmAcrlWriteOffPk.get(EZUPTIME));
//                    ZYPEZDItemValueSetter.setValue(globalMsg.B.no(j).ezUpTimeZone_B1, (String) mapCmAcrlWriteOffPk.get(EZUPTIMEZONE));
//                }
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTime_A1, (String) map.get(EZUPTIME));          // EZUPTIME
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTimeZone_A1, (String) map.get(EZUPTIMEZONE));  // EZUPTIMEZONE
                // END   2019/01/30 H.Ikeda [QC#30057, MOD]
                // START 2022/04/05 R.Onozuka [QC357935, ADD]
                if(ZYPCommonFunc.hasValue((String) map.get(XX_CMNT_TXT))){
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxCmntTxt_A1, (String) map.get(XX_CMNT_TXT));                               // 9segment
                } else {
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxCmntTxt_A1, getDefaultAccount(GLBL_CMPY_CD, (String) map.get(MDSE_CD), bizMsg));  // 9segment
                }
                // del start 2022/08/05 QC#59245
                //ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).acInvJrnlCostAmt_AP, (BigDecimal) map.get(INV_UNIT_PRC_AMT));  // Invoice Unit Price
                // del end 2022/08/05 QC#59245
                // END 2022/04/05 R.Onozuka [QC357935, ADD]
                // For exclusive control end
            }
            // Copy SMsg info to CMsg.
            copySMsgToCMsgS(bizMsg, globalMsg);
        } else {
            // Not found
            bizMsg.A.setValidCount(0);
            bizMsg.xxPageShowFromNum_A.setValue(0);
            bizMsg.xxPageShowToNum_A.setValue(0);
            bizMsg.xxPageShowOfNum_A.setValue(0);
            // START 2021/04/06 K.Suzuki [QC#57040, ADD]
            if ("NFBL1130Scrn00_CMN_Submit".equals(cMsg.getScreenAplID())) {
                // The search ended normally
                bizMsg.setMessageInfo(ZZM8002I);
            } else {
                // END   2021/04/06 K.Suzuki [QC#57040, ADD]
                // Record is not found.
                bizMsg.setMessageInfo(NFBM0069E);
                // START 2021/04/06 K.Suzuki [QC#57040, ADD]
            }
            // END   2021/04/06 K.Suzuki [QC#57040, ADD]
            return false;
        }

        // Keep search condition
        ZYPEZDItemValueSetter.setValue(bizMsg.poNum_BF, bizMsg.poNum.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.delyOrdNum_BF, bizMsg.delyOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd_BF, bizMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.invRcvQty_BF, bizMsg.invRcvQty.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.apVndInvNum_BF, bizMsg.apVndInvNum.getValue());
        // START 2016/09/30 K.Kojima [QC#14179,MOD]
        // ZYPEZDItemValueSetter.setValue(bizMsg.apVndCd_BF,
        // bizMsg.apVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.prntVndCd_BF, bizMsg.prntVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.prntVndNm_BF, bizMsg.prntVndNm.getValue());
        // END 2016/09/30 K.Kojima [QC#14179,MOD]
        ZYPEZDItemValueSetter.setValue(bizMsg.invQty_BF, bizMsg.invQty.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.acrlCoaAcctCd_BF, bizMsg.acrlCoaAcctCd_S.getValue());
        // START 2016/09/29 K.Kojima [QC#14178,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsNum_BF, bizMsg.rwsNum.getValue());
        // END 2016/09/29 K.Kojima [QC#14178,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.invDt_BF, bizMsg.invDt_FR.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.invDt_BT, bizMsg.invDt_TO.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.stkInDt_BF, bizMsg.stkInDt_FR.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.stkInDt_BT, bizMsg.stkInDt_TO.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_BW, bizMsg.xxChkBox_WO.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_BP, bizMsg.xxChkBox_PM.getValue());
        // add start 2022/08/05 QC#59245
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_BC, bizMsg.xxChkBox_CV.getValue());
        // add end 2022/08/05 QC#59245

        EZDDebugOutput.println(5, "searchRecord================================END", null);
        return true;
    }

    /**
     * Method name: csvDownLoad
     * <dd>The method explanation: CSV Download Process 
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void csvDownLoad(EZDCMsg cMsg, EZDSMsg sMsg) {
    	
        NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;
        NFBL1130SMsg globalMsg = (NFBL1130SMsg) sMsg;

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV_EXT);

        NFBL1130F00FMsg f00Msg = new NFBL1130F00FMsg();

        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), f00Msg);

        csvOutFile.writeHeader(CSV_HEADER);

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            /* Copy a record into FMsg, and write it by copy. */
            EZDMsg.copy(globalMsg.A.no(i), null, f00Msg, null);
            csvOutFile.write();
        }
        csvOutFile.close();

    }

    /**
     * Method name: clearHeader
     * <dd>The method explanation: Clear values on HEADER tab.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void clearHeader(EZDCMsg cMsg) {

        NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;

        bizMsg.poNum.clear();
        bizMsg.mdseCd.clear();
        bizMsg.delyOrdNum.clear();
        bizMsg.invRcvQty.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_WO, ZYPConstant.FLG_OFF_N);
        bizMsg.apVndInvNum.clear();
        // START 2016/09/30 K.Kojima [QC#14179,MOD]
        // bizMsg.apVndCd.clear();
        bizMsg.prntVndCd.clear();
        // END 2016/09/30 K.Kojima [QC#14179,MOD]
        bizMsg.prntVndNm.clear();
        bizMsg.invQty.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_PM, ZYPConstant.FLG_OFF_N);
        bizMsg.acrlCoaAcctCd_S.clear();
        // START 2016/09/29 K.Kojima [QC#14178,ADD]
        bizMsg.rwsNum.clear();
        // END 2016/09/29 K.Kojima [QC#14178,ADD]
        // add start 2022/08/05 QC#59245
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_CV, ZYPConstant.FLG_OFF_N);
        // add end 2022/08/05 QC#59245
        bizMsg.invDt_FR.clear();
        bizMsg.invDt_TO.clear();
        bizMsg.stkInDt_FR.clear();
        bizMsg.stkInDt_TO.clear();
        // START 2016/10/05 J.Kim [QC#5521,ADD]
        bizMsg.srchOptPk_S.clear();
        bizMsg.srchOptNm_S.clear();
        // END 2016/10/05 J.Kim [QC#5521,ADD]
    }
    
    /**
     * Method name: clearLines
     * <dd>The method explanation: Clear values on LINES tab.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void clearLines(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;
        NFBL1130SMsg globalMsg = (NFBL1130SMsg) sMsg;

        // LINES Tab
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        globalMsg.A.clear();
        globalMsg.A.setValidCount(0);
    }
    
    /**
     * Method name: copySMsgToCMsgS
     * <dd>The method explanation: Copy SMsg info to CMsg.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void copySMsgToCMsgS(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "copySMsgToCMsgS================================START", null);
        NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;
        NFBL1130SMsg globalMsg = (NFBL1130SMsg) sMsg;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
        }
        EZDDebugOutput.println(5, "copySMsgToCMsgS================================END", null);
    }

    /**
     * Method name: moveToFirstErrorPage
     * <dd>The method explanation: Copy SMsg info to CMsg.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param firstErrIndex int
     */
    public static void moveToFirstErrorPage(EZDCMsg cMsg, EZDSMsg sMsg, int firstErrIndex) {
        EZDDebugOutput.println(5, "moveToFirstErrorPage================================START", null);
        NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;
        NFBL1130SMsg globalMsg = (NFBL1130SMsg) sMsg;

        // Specify the page of first error.
        int remainder = (firstErrIndex + 1) / bizMsg.A.length();
        int quotient = (firstErrIndex + 1) % bizMsg.A.length();
        int firstErrPageNum = remainder;
        if (quotient > 0) {
            firstErrPageNum = firstErrPageNum + 1;
        }

        bizMsg.A.clear();
        int xxPageShowFromNum = (firstErrPageNum - 1) * bizMsg.A.length() + 1;
        int xxPageShowToNum =  xxPageShowFromNum - 1;
        int i = xxPageShowFromNum - 1;
        for (; i < xxPageShowFromNum - 1 + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                // copy data from SMsg onto CMsg
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - (xxPageShowFromNum - 1)), null);
                xxPageShowToNum++;
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - (xxPageShowFromNum - 1));
        bizMsg.xxPageShowFromNum_A.setValue(xxPageShowFromNum);
        bizMsg.xxPageShowToNum_A.setValue(xxPageShowToNum);

        EZDDebugOutput.println(5, "moveToFirstErrorPage================================END", null);
    }

    /**
     * Method name: saveDetailInfo
     * <dd>The method explanation: Save CMsg status to SMsg.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global Message
     */
    public static void saveDetailInfo(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFBL1130CMsg bizMsg = (NFBL1130CMsg) cMsg;
        NFBL1130SMsg globalMsg = (NFBL1130SMsg) sMsg;

        // Save current page values(CMsg) to SMsg.
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).wrtOffFlg_A1)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).wrtOffFlg_A1, ZYPConstant.FLG_OFF_N);
            }
            EZDMsg.copy(bizMsg.A.no(i), null, globalMsg.A.no(i + bizMsg.xxPageShowFromNum_A.getValueInt() - 1), null);
        }

    }
    
    /**
     * Method name: getCmAcrlWriteOffPkList
     * <dd>The method explanation: Get CM_ACRL_WRITE_OFF_PK list.
     * <dd>Remarks:
     * @param poNum String
     * @param poOrdDtlLineNum String
     * @param wrtOffFlg String
     * @param wrtOffDt String
     * @return resultList List 
     */
    @SuppressWarnings("unchecked")
    // START 2019/03/12 [QC#30729, MOD]
    // public static List getCmAcrlWriteOffPkList(String poNum, String apVndCd, String mdseCd) {
    // mod start 2022/03/28 QC#57935(56588)
    //public static List getCmAcrlWriteOffPkList(String poNum, String apVndCd, String mdseCd, String poOrdDtlLineNum) {
    public static List getCmAcrlWriteOffPkList(String poNum, String poOrdDtlLineNum, String wrtOffFlg, String wrtOffDt) {
    // mod end 2022/03/28 QC#57935(56588)

        // S21SsmEZDResult ssmResult = NFBL1130Query.getInstance().getCmAcrlWriteOffPkList(poNum, apVndCd, mdseCd);
        // mod start 2022/03/28 QC#57935(56588)
        //S21SsmEZDResult ssmResult = NFBL1130Query.getInstance().getCmAcrlWriteOffPkList(poNum, apVndCd, mdseCd, poOrdDtlLineNum);
        S21SsmEZDResult ssmResult = NFBL1130Query.getInstance().getCmAcrlWriteOffPkList(poNum, poOrdDtlLineNum, wrtOffFlg, wrtOffDt);
        // mod end 2022/03/28 QC#57935(56588)

        List resultList = new ArrayList();
        if (ssmResult.isCodeNormal()) {
            resultList = (List) ssmResult.getResultObject();
        }
        return resultList;

    }
    // END   2019/03/12 [QC#30729, MOD]

    // START 2019/01/30 H.Ikeda [QC#30057, ADD]
    /**
     * Method name: getExclusiveData
     * <dd>The method explanation: Get Exclusive Data.
     * <dd>Remarks:
     * @param poNum String
     * @param poOrdDtlLineNum String
     * @param wrtOffFlg String
     * @param wrtOffDt String
     * @return resultList List 
     */
    @SuppressWarnings("unchecked")
    // START 2019/03/12 [QC#30729, MOD]
    // public static List getExclusiveData(String poNum, String apVndCd, String mdseCd) {
    // mod start 2022/03/28 QC#57935(56588)
    //public static List getExclusiveData(String poNum, String apVndCd, String mdseCd, String poOrdDtlLineNum) {
    public static List getExclusiveData(String poNum, String poOrdDtlLineNum, String wrtOffFlg, String wrtOffDt) {
    // mod end 2022/03/28 QC#57935(56588)

        // S21SsmEZDResult ssmResult = NFBL1130Query.getInstance().getExclusiveData(poNum, apVndCd, mdseCd);
        // mod start 2022/03/28 QC#57935(56588)
        //S21SsmEZDResult ssmResult = NFBL1130Query.getInstance().getExclusiveData(poNum, apVndCd, mdseCd, poOrdDtlLineNum);
        S21SsmEZDResult ssmResult = NFBL1130Query.getInstance().getExclusiveData(poNum, poOrdDtlLineNum, wrtOffFlg, wrtOffDt);
        // mod end 2022/03/28 QC#57935(56588)

        List resultList = new ArrayList();
        if (ssmResult.isCodeNormal()) {
            resultList = (List) ssmResult.getResultObject();
        }
        return resultList;

    }
    // END   2019/03/12 [QC#30729, MOD]
    // END   2019/01/30 H.Ikeda [QC#30057, ADD]

    // START 2016/10/05 J.Kim [QC#5521,ADD]
    /**
     * Call NSZC0330 for Search Option
     * @param bizMsg NFBL1130CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    public static boolean callSrchOptApi(NFBL1130CMsg bizMsg, NSZC033001PMsg pMsg) {

        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int i = 0; i < pMsg.xxMsgIdList.length(); i++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(i).xxMsgId)) {
                    String msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        bizMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Pagination <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagination(NFBL1130CMsg cMsg, NFBL1130SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum_A.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_A.setValue(pageFrom + cMsg.A.getValidCount());

    }

    /**
     * Create Save Option PullDown list
     * @param bizMsg NFBL2060CMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void createPulldownListSaveOpt(NFBL1130CMsg bizMsg, String userId, String glblCmpyCd) {

        bizMsg.srchOptPk_H.clear();
        bizMsg.srchOptNm_H.clear();

        SAVE_SRCH_OPTTMsg saveSrchOptTMsg = new SAVE_SRCH_OPTTMsg();
        saveSrchOptTMsg.setSQLID("001");
        saveSrchOptTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        saveSrchOptTMsg.setConditionValue("srchOptAplId01", BIZ_ID);
        saveSrchOptTMsg.setConditionValue("srchOptUsrId01", userId);

        SAVE_SRCH_OPTTMsgArray saveSrchOptTMsgArray = (SAVE_SRCH_OPTTMsgArray) EZDTBLAccessor.findByCondition(saveSrchOptTMsg);
        for (int i = 0; i < saveSrchOptTMsgArray.length(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_H.no(i), saveSrchOptTMsgArray.no(i).srchOptPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptNm_H.no(i), saveSrchOptTMsgArray.no(i).srchOptNm);
        }
    }

    /**
     * isExistSaveSearchName
     * @param bizMsg NFBL1130CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NFBL1130CMsg bizMsg) {

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_H.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param bizMsg NFBL1130CMsg
     * @param pMsg NSZC033001PMsg
     */
    public static void setSelectSaveSearchName(NFBL1130CMsg bizMsg, NSZC033001PMsg pMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_H.no(i));
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_H.no(i));
            }
        }
        return;
    }

    /**
     * isSameSaveSearchName
     * @param bizMsg NFBL1130CMsg
     * @return boolean
     */
    public static boolean isSameSaveSearchName(NFBL1130CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return false;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_H.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    // START 2016/10/05 J.Kim [QC#5521,ADD]
    // START 2022/04/05 R.Onozuka [QC#57935, ADD]
    /**
     * Check 9 Segment
     * @param bizMsg NFBL1130CMsg
     * @param idx Integer
     * @param coa String[]
     * @return boolean
     */
    public static boolean check9Seg(NFBL1130SMsg globalMsg, int idx, String[] coa) {
        CM_ACRL_WRITE_OFFTMsg cmAcrlWriteOffTMsg = new CM_ACRL_WRITE_OFFTMsg();
        int coaCmpyCdDigit = cmAcrlWriteOffTMsg.getAttr("coaCmpyCd").getDigit();
        int coaBrCdDigit = cmAcrlWriteOffTMsg.getAttr("coaBrCd").getDigit();
        int coaCcCdDigit = cmAcrlWriteOffTMsg.getAttr("coaCcCd").getDigit();
        int coaAcctCdDigit = cmAcrlWriteOffTMsg.getAttr("coaAcctCd").getDigit();
        int coaProdCdDigit = cmAcrlWriteOffTMsg.getAttr("coaProdCd").getDigit();
        int coaChCdDigit = cmAcrlWriteOffTMsg.getAttr("coaChCd").getDigit();
        int coaAfflCdDigit = cmAcrlWriteOffTMsg.getAttr("coaAfflCd").getDigit();
        int coaProjCdDigit = cmAcrlWriteOffTMsg.getAttr("coaProjCd").getDigit();
        int coaExtnCdDigit = cmAcrlWriteOffTMsg.getAttr("coaExtnCd").getDigit();
        int coaIdx = 0;

        if (coa.length != 9) {
            String errMsg = "9 segments";
            errMsg = errMsg.concat("(");
            errMsg = errMsg.concat(String.valueOf(coa.length));
            errMsg = errMsg.concat(")");
            globalMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {errMsg });
            return false;
        }
        if (coa[coaIdx++].length() > coaCmpyCdDigit) {
            globalMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {"Company" });
            return false;
        }
        if (coa[coaIdx++].length() > coaBrCdDigit) {
            globalMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {"Branch" });
            return false;
        }
        if (coa[coaIdx++].length() > coaCcCdDigit) {
            globalMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {"Cost Center" });
            return false;
        }
        if (coa[coaIdx++].length() > coaAcctCdDigit) {
            globalMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {"Account" });
            return false;
        }
        if (coa[coaIdx++].length() > coaProdCdDigit) {
            globalMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {"Product" });
            return false;
        }
        if (coa[coaIdx++].length() > coaChCdDigit) {
            globalMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {"Channel" });
            return false;
        }
        if (coa[coaIdx++].length() > coaAfflCdDigit) {
            globalMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {"Intercompany" });
            return false;
        }
        if (coa[coaIdx++].length() > coaProjCdDigit) {
            globalMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {"Merchandise" });
            return false;
        }
        if (coa[coaIdx++].length() > coaExtnCdDigit) {
            globalMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {"Business Unit" });
            return false;
        }
        return true;
    }
    /**
     * glCodeCombinationCheck
     * @param bizMsg NFBL1130CMsg
     * @param idx Integer
     * @param coa String[]
     * @return boolean
     */
    public static boolean checkGlCodeCombination(NFBL1130SMsg globalMsg, int idx, String[] coa) {
        if (!NFBL1130CommonLogic.check9Seg(globalMsg, idx, coa)) {
            return false;
        }

        NFZC102001 api = new NFZC102001();
        NFZC102001PMsg apiMsg = new NFZC102001PMsg();

        ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxMstChkFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxArcsApiChkFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxGlCmbnChkFlg, ZYPConstant.FLG_ON_Y);

        int coaIdx = 0;
        ZYPEZDItemValueSetter.setValue(apiMsg.coaCmpyCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaBrCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaCcCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaAcctCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaProdCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaChCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaAfflCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaProjCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaExtnCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.resrcObjNm, BIZ_ID);

        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
        if (apiMsg.xxMsgIdList.getValidCount() > 0) {
            String msgId;
            String msgTxt;
            msgId = apiMsg.xxMsgIdList.no(0).xxMsgId.getValue();
            msgTxt = apiMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue();
            globalMsg.A.no(idx).xxCmntTxt_A1.clearErrorInfo();
            globalMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, msgId, new String[] {msgTxt });
            return false;
        } else {
            globalMsg.A.no(idx).xxCmntTxt_A1.clearErrorInfo();
        }
        return true;
    }
    /**
     * getDefaultAccount
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return String
     */
    public static String getDefaultAccount(String glblCmpyCd, String mdseCd, NFBL1130CMsg bizMsg) {

        String defCoa = ZYPCodeDataUtil.getVarCharConstValue(DEF_ACRL_WRT_OFF_COA, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(defCoa)) {
            bizMsg.setMessageInfo(ZZZM9006E, new String[] {"Default COA" });
            return null;
        }

        String[] defaultCoaList = defCoa.split(",");
        StringBuffer defAcct = new StringBuffer();

        Map<String, String> defaultCoaMap = NFBL1130Query.getInstance().getDefaultCoaList(glblCmpyCd, mdseCd);
        if (defaultCoaMap == null) {
            return "";
        }

        defAcct.append(getDefaultCoa(defaultCoaList[0], defaultCoaMap, CMPY_CD));
        defAcct.append(STR_COMMA);
        defAcct.append(getDefaultCoa(defaultCoaList[1], defaultCoaMap, BR_CD));
        defAcct.append(STR_COMMA);
        defAcct.append(getDefaultCoa(defaultCoaList[2], defaultCoaMap, CC_CD));
        defAcct.append(STR_COMMA);
        defAcct.append(getDefaultCoa(defaultCoaList[3], defaultCoaMap, ACCT_CD));
        defAcct.append(STR_COMMA);
        defAcct.append(getDefaultCoa(defaultCoaList[4], defaultCoaMap, PROD_CD));
        defAcct.append(STR_COMMA);
        defAcct.append(getDefaultCoa(defaultCoaList[5], defaultCoaMap, CH_CD));
        defAcct.append(STR_COMMA);
        defAcct.append(getDefaultCoa(defaultCoaList[6], defaultCoaMap, AFFL_CD));
        defAcct.append(STR_COMMA);
        defAcct.append(getDefaultCoa(defaultCoaList[7], defaultCoaMap, PROJ_CD));
        defAcct.append(STR_COMMA);
        defAcct.append(getDefaultCoa(defaultCoaList[8], defaultCoaMap, EXTN_CD));

        return defAcct.toString();
    }

    private static String getDefaultCoa(String defaultCoa, Map<String, String> defaultCoaMap, String coa) {

        String defCoa = "";
        if (defaultCoa.startsWith("@", 0)) {
            defCoa = defaultCoaMap.get(defaultCoa.substring(1) + coa);
            if (!ZYPCommonFunc.hasValue(defCoa)) {
                defCoa = "";
            }
        } else {
            defCoa = defaultCoa;
        }
        return defCoa;
    }
    // END 2022/04/05 R.Onozuka [QC#57935, ADD]
}
