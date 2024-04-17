/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2200.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import business.blap.NWAL2200.NWAL2200CMsg;
import business.blap.NWAL2200.NWAL2200Query;
import business.blap.NWAL2200.NWAL2200SMsg;
import business.blap.NWAL2200.NWAL2200_ACMsg;
import business.blap.NWAL2200.NWAL2200_ASMsg;
import business.blap.NWAL2200.NWAL2200_BCMsg;
import business.blap.NWAL2200.NWAL2200_BCMsgArray;
import business.blap.NWAL2200.NWAL2200_JSMsg;
import business.blap.NWAL2200.constant.NWAL2200Constant;
import business.db.DS_IMPT_ORD_DELY_INFOTMsg;
import business.db.DS_IMPT_ORD_ERRTMsg;

import com.canon.cusa.s21.common.NWX.NWXC245001.NWXC245001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHIP_TO_ADDR_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * NWAL2200CommonLogicForCUSARetail
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/24   Fujitsu         T.Ishii         Create          S21_NA#8651
 * 2016/06/23   Fujitsu         T.Ishii         Update          S21_NA#10699
 * 2017/02/10   Fujitsu         M.Ohno          Update          QC#17302
 * 2017/12/05   Fujitsu         T.Murai         Update          S21_NA#22624
 *</pre>
 */
public class NWAL2200CommonLogicForCUSARetail {

    /**
     * deriveData
     * @param bizMsg NWAL2200CMsg
     * @return true/false
     */
    public static boolean deriveData(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg, List<DS_IMPT_ORD_ERRTMsg> errorList) {

        if (bizMsg.B.getValidCount() == 0) {
            // to validation process.
            return true;
        }

        // generate CPO order number by order source reference number.
        String cpoOrdNum = generateCpoOrdNumByOrdSrcRefNum(bizMsg.ordSrcRefNum.getValue());
        if (cpoOrdNum == null) {
            // to validation process.
            return true;
        }

        // generate RCPO detail sequence by first order source line
        // reference number in this order.
        BigDecimal rcpoDtlSQ = null;
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            rcpoDtlSQ = generateRcpoDtlSQByOrdSrcRefLineNum(bizMsg.B.no(i).ordSrcRefLineNum_LL.getValue());
            if (rcpoDtlSQ != null) {
                break;
            }
        }
        if (rcpoDtlSQ == null) {
            // to validation process.
            return true;
        }

        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String salesDate = bizMsg.slsDt.getValue();

        // get CSA order header
        Map<String, Object> csaOrdHdr = getCsaOrdHdr(GLBL_CMPY.CANON_USA_INC, cpoOrdNum);
        if (csaOrdHdr == null) {
            // to validation process.
            return true;
        }

        // get CSA order item (first item)
        List<Map<String, Object>> csaOrdItemFirst = getCsaOrdItem(GLBL_CMPY.CANON_USA_INC, cpoOrdNum, null, null, rcpoDtlSQ);
        if (csaOrdItemFirst == null || csaOrdItemFirst.size() == 0) {
            // to validation process.
            return true;
        }

        // CSA order header information
        String rtlDivCd = (String) csaOrdHdr.get("RTL_DIV_CD");

        // CSA retail order position information

        // CSA order item information
        String shipToAddrTpCd = (String) csaOrdItemFirst.get(0).get("SHIP_TO_ADDR_TP_CD");
        String rtlDlrCd = (String) csaOrdItemFirst.get(0).get("SVC_DLR_CD");
        String instlCd = (String) csaOrdItemFirst.get(0).get("INSTL_CD");
        String instlSubLocCd = (String) csaOrdItemFirst.get(0).get("ISTL_SUB_LOC_CD");

        // dealer information
        Map<String, Object> dsRtlDlr = NWXC245001.getDsRtlDlrInfo(glblCmpyCd, rtlDlrCd, rtlDivCd, salesDate);
        if (dsRtlDlr == null) {
            // return false;
        }
        // order category business context
        Map<String, Object> ordCatgBizCtx = null;
        if (S21StringUtil.isEquals(shipToAddrTpCd, "2")) {
            ordCatgBizCtx = NWXC245001.getOrdCatgBizCtx(glblCmpyCd, rtlDivCd);
            if (ordCatgBizCtx == null) {
                // return false;
            }
        }

        // X reference account
        Map<String, Object> dsXrefAcct = NWXC245001.getDsXrefAcct(glblCmpyCd, instlCd, instlSubLocCd);
        if (dsXrefAcct == null) {
            errorList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), null, null, null, NWAL2200Constant.NWAM0929E, null)); // 2017/02/09 S21_NA#17302 Add
            // return false;
        }

        // retail intangible item map
        Map<String, Object> dsRtlIntgItemMap = NWXC245001.getDsRtlIntgItemMap(glblCmpyCd, rtlDivCd, salesDate);
        if (dsRtlIntgItemMap == null) {
            // return false;
        }

        // service configuration master (for no main machine order)
        BigDecimal svcConfigMstrPk = NWXC245001.getSvcConfigMstrPk(glblCmpyCd, cpoOrdNum, instlCd, instlSubLocCd);
        if (svcConfigMstrPk == null) {
            // return false;
        }

        String delyAddlCmntTxt = NWXC245001.getDelyAddlCmntTxt(glblCmpyCd, instlCd, instlSubLocCd);
        if (delyAddlCmntTxt == null) {
            // return false;
        }

        if (S21StringUtil.isEquals(shipToAddrTpCd, "2")) {

            // drop ship(Install Location)
            if (ordCatgBizCtx != null) {

                if (S21StringUtil.isEquals(bizMsg.dsOrdCatgCd.getValue(), glblMsg.dsOrdCatgCd.getValue())) {
                    // category
                    ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, (String) ordCatgBizCtx.get("DS_ORD_CATG_CD"));
                }

                if (S21StringUtil.isEquals(bizMsg.dsOrdTpCd.getValue(), glblMsg.dsOrdTpCd.getValue())) {
                    // reason
                    ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, (String) ordCatgBizCtx.get("DS_ORD_TP_CD"));
                }
            }

        } else {

            if (dsRtlDlr != null) {
                if (S21StringUtil.isEquals(bizMsg.dsOrdCatgCd.getValue(), glblMsg.dsOrdCatgCd.getValue())) {
                    // category
                    ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, (String) dsRtlDlr.get("DS_ORD_CATG_CD"));
                }
                if (S21StringUtil.isEquals(bizMsg.dsOrdTpCd.getValue(), glblMsg.dsOrdTpCd.getValue())) {
                    // reason
                    ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, (String) dsRtlDlr.get("DS_ORD_TP_CD"));
                }
            }
        }

        if (dsRtlDlr != null) {

            if (S21StringUtil.isEquals(bizMsg.billToCustCd.getValue(), glblMsg.billToCustCd.getValue())) {
                // bill to
                ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd, (String) dsRtlDlr.get("BILL_TO_CUST_CD"));
            }
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                NWAL2200_ACMsg config = bizMsg.A.no(i);
                NWAL2200_ASMsg configOrg = glblMsg.A.no(i);
                if (S21StringUtil.isEquals(config.billToCustLocCd_LC.getValue(), configOrg.billToCustLocCd_LC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(config.billToCustLocCd_LC, (String) dsRtlDlr.get("BILL_TO_CUST_CD"));
                }
            }
        }

        if (dsXrefAcct != null) {

            // ship to
            if (S21StringUtil.isEquals(bizMsg.shipToCustAcctCd.getValue(), glblMsg.shipToCustAcctCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, (String) dsXrefAcct.get("SELL_TO_CUST_CD"));
            }
            if (S21StringUtil.isEquals(bizMsg.shipToCustCd.getValue(), glblMsg.shipToCustCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, (String) dsXrefAcct.get("SHIP_TO_CUST_CD"));
            }
            if (S21StringUtil.isEquals(bizMsg.shipToLocNm.getValue(), glblMsg.shipToLocNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm, (String) dsXrefAcct.get("LOC_NM"));
            }
            if (S21StringUtil.isEquals(bizMsg.shipToAddlLocNm.getValue(), glblMsg.shipToAddlLocNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToAddlLocNm, (String) dsXrefAcct.get("ADDL_LOC_NM"));
            }
            if (S21StringUtil.isEquals(bizMsg.shipToFirstLineAddr.getValue(), glblMsg.shipToFirstLineAddr.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr, (String) dsXrefAcct.get("FIRST_LINE_ADDR"));
            }
            if (S21StringUtil.isEquals(bizMsg.shipToScdLineAddr.getValue(), glblMsg.shipToScdLineAddr.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr, (String) dsXrefAcct.get("SCD_LINE_ADDR"));
            }
            if (S21StringUtil.isEquals(bizMsg.shipToThirdLineAddr.getValue(), glblMsg.shipToThirdLineAddr.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToThirdLineAddr, (String) dsXrefAcct.get("THIRD_LINE_ADDR"));
            }
            if (S21StringUtil.isEquals(bizMsg.shipToFrthLineAddr.getValue(), glblMsg.shipToFrthLineAddr.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToFrthLineAddr, (String) dsXrefAcct.get("FRTH_LINE_ADDR"));
            }
            if (S21StringUtil.isEquals(bizMsg.shipToCtyAddr.getValue(), glblMsg.shipToCtyAddr.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr, (String) dsXrefAcct.get("CTY_ADDR"));
            }
            if (S21StringUtil.isEquals(bizMsg.shipToStCd.getValue(), glblMsg.shipToStCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd, (String) dsXrefAcct.get("ST_CD"));
            }
            if (S21StringUtil.isEquals(bizMsg.shipToProvNm.getValue(), glblMsg.shipToProvNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToProvNm, (String) dsXrefAcct.get("PROV_NM"));
            }
            if (S21StringUtil.isEquals(bizMsg.shipToCntyNm.getValue(), glblMsg.shipToCntyNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm, (String) dsXrefAcct.get("CNTY_NM"));
            }
            if (S21StringUtil.isEquals(bizMsg.shipToPostCd.getValue(), glblMsg.shipToPostCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd, (String) dsXrefAcct.get("POST_CD"));
            }
            if (S21StringUtil.isEquals(bizMsg.shipToCtryCd.getValue(), glblMsg.shipToCtryCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd, (String) dsXrefAcct.get("CTRY_CD"));
            }
            if (S21StringUtil.isEquals(bizMsg.shipTo01RefCmntTxt.getValue(), glblMsg.shipTo01RefCmntTxt.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipTo01RefCmntTxt, (String) dsXrefAcct.get("FIRST_REF_CMNT_TXT"));
            }
            if (S21StringUtil.isEquals(bizMsg.shipTo02RefCmntTxt.getValue(), glblMsg.shipTo02RefCmntTxt.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipTo02RefCmntTxt, (String) dsXrefAcct.get("SCD_REF_CMNT_TXT"));
            }

            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

                NWAL2200_ACMsg config = bizMsg.A.no(i);
                NWAL2200_ASMsg configOrg = glblMsg.A.no(i);
                if (S21StringUtil.isEquals(config.shipToCustAcctCd_LC.getValue(), configOrg.shipToCustAcctCd_LC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(config.shipToCustAcctCd_LC, (String) dsXrefAcct.get("SELL_TO_CUST_CD"));
                }
                if (S21StringUtil.isEquals(config.shipToCustLocCd_LC.getValue(), configOrg.shipToCustLocCd_LC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(config.shipToCustLocCd_LC, (String) dsXrefAcct.get("SHIP_TO_CUST_CD"));
                }
                if (S21StringUtil.isEquals(config.shipToLocNm_LC.getValue(), configOrg.shipToLocNm_LC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(config.shipToLocNm_LC, (String) dsXrefAcct.get("LOC_NM"));
                }
                if (S21StringUtil.isEquals(config.shipToAddlLocNm_LC.getValue(), configOrg.shipToAddlLocNm_LC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(config.shipToAddlLocNm_LC, (String) dsXrefAcct.get("ADDL_LOC_NM"));
                }
                if (S21StringUtil.isEquals(config.shipToFirstLineAddr_LC.getValue(), configOrg.shipToFirstLineAddr_LC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(config.shipToFirstLineAddr_LC, (String) dsXrefAcct.get("FIRST_LINE_ADDR"));
                }
                if (S21StringUtil.isEquals(config.shipToScdLineAddr_LC.getValue(), configOrg.shipToScdLineAddr_LC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(config.shipToScdLineAddr_LC, (String) dsXrefAcct.get("SCD_LINE_ADDR"));
                }
                if (S21StringUtil.isEquals(config.shipToThirdLineAddr_LC.getValue(), configOrg.shipToThirdLineAddr_LC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(config.shipToThirdLineAddr_LC, (String) dsXrefAcct.get("THIRD_LINE_ADDR"));
                }
                if (S21StringUtil.isEquals(config.shipToFrthLineAddr_LC.getValue(), configOrg.shipToFrthLineAddr_LC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(config.shipToFrthLineAddr_LC, (String) dsXrefAcct.get("FRTH_LINE_ADDR"));
                }
                if (S21StringUtil.isEquals(config.shipToCtyAddr_LC.getValue(), configOrg.shipToCtyAddr_LC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(config.shipToCtyAddr_LC, (String) dsXrefAcct.get("CTY_ADDR"));
                }
                if (S21StringUtil.isEquals(config.shipToStCd_LC.getValue(), configOrg.shipToStCd_LC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(config.shipToStCd_LC, (String) dsXrefAcct.get("ST_CD"));
                }
                if (S21StringUtil.isEquals(config.shipToProvNm_LC.getValue(), configOrg.shipToProvNm_LC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(config.shipToProvNm_LC, (String) dsXrefAcct.get("PROV_NM"));
                }
                if (S21StringUtil.isEquals(config.shipToCntyNm_LC.getValue(), configOrg.shipToCntyNm_LC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(config.shipToCntyNm_LC, (String) dsXrefAcct.get("CNTY_NM"));
                }
                if (S21StringUtil.isEquals(config.shipToPostCd_LC.getValue(), configOrg.shipToPostCd_LC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(config.shipToPostCd_LC, (String) dsXrefAcct.get("POST_CD"));
                }
                if (S21StringUtil.isEquals(config.shipToCtryCd_LC.getValue(), configOrg.shipToCtryCd_LC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(config.shipToCtryCd_LC, (String) dsXrefAcct.get("CTRY_CD"));
                }
                if (S21StringUtil.isEquals(config.shipToFirstRefCmntTxt_LC.getValue(), configOrg.shipToFirstRefCmntTxt_LC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(config.shipToFirstRefCmntTxt_LC, (String) dsXrefAcct.get("FIRST_REF_CMNT_TXT"));
                }
                if (S21StringUtil.isEquals(config.shipToScdRefCmntTxt_LC.getValue(), configOrg.shipToScdRefCmntTxt_LC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(config.shipToScdRefCmntTxt_LC, (String) dsXrefAcct.get("SCD_REF_CMNT_TXT"));
                }
            }
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {

                NWAL2200_BCMsg line = bizMsg.B.no(i);
                NWAL2200_JSMsg lineOrg = glblMsg.J.no(i);
                if (S21StringUtil.isEquals(line.shipToCustCd_LL.getValue(), lineOrg.shipToCustCd_LL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(line.shipToCustCd_LL, (String) dsXrefAcct.get("SHIP_TO_CUST_CD"));
                }

                if (S21StringUtil.isEquals(line.shipToLocNm_LL.getValue(), lineOrg.shipToLocNm_LL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(line.shipToLocNm_LL, (String) dsXrefAcct.get("LOC_NM"));
                }
                if (S21StringUtil.isEquals(line.shipToAddlLocNm_LL.getValue(), lineOrg.shipToAddlLocNm_LL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(line.shipToAddlLocNm_LL, (String) dsXrefAcct.get("ADDL_LOC_NM"));
                }
                if (S21StringUtil.isEquals(line.shipToFirstLineAddr_LL.getValue(), lineOrg.shipToFirstLineAddr_LL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(line.shipToFirstLineAddr_LL, (String) dsXrefAcct.get("FIRST_LINE_ADDR"));
                }
                if (S21StringUtil.isEquals(line.shipToScdLineAddr_LL.getValue(), lineOrg.shipToScdLineAddr_LL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(line.shipToScdLineAddr_LL, (String) dsXrefAcct.get("SCD_LINE_ADDR"));
                }
                if (S21StringUtil.isEquals(line.shipToThirdLineAddr_LL.getValue(), lineOrg.shipToThirdLineAddr_LL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(line.shipToThirdLineAddr_LL, (String) dsXrefAcct.get("THIRD_LINE_ADDR"));
                }
                if (S21StringUtil.isEquals(line.shipToFrthLineAddr_LL.getValue(), lineOrg.shipToFrthLineAddr_LL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(line.shipToFrthLineAddr_LL, (String) dsXrefAcct.get("FRTH_LINE_ADDR"));
                }
                if (S21StringUtil.isEquals(line.shipToCtyAddr_LL.getValue(), lineOrg.shipToCtyAddr_LL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(line.shipToCtyAddr_LL, (String) dsXrefAcct.get("CTY_ADDR"));
                }
                if (S21StringUtil.isEquals(line.shipToStCd_LL.getValue(), lineOrg.shipToStCd_LL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(line.shipToStCd_LL, (String) dsXrefAcct.get("ST_CD"));
                }
                if (S21StringUtil.isEquals(line.shipToProvNm_LL.getValue(), lineOrg.shipToProvNm_LL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(line.shipToProvNm_LL, (String) dsXrefAcct.get("PROV_NM"));
                }
                if (S21StringUtil.isEquals(line.shipToCntyNm_LL.getValue(), lineOrg.shipToCntyNm_LL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(line.shipToCntyNm_LL, (String) dsXrefAcct.get("CNTY_NM"));
                }
                if (S21StringUtil.isEquals(line.shipToPostCd_LL.getValue(), lineOrg.shipToPostCd_LL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(line.shipToPostCd_LL, (String) dsXrefAcct.get("POST_CD"));
                }
                if (S21StringUtil.isEquals(line.shipToCtryCd_LL.getValue(), lineOrg.shipToCtryCd_LL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(line.shipToCtryCd_LL, (String) dsXrefAcct.get("CTRY_CD"));
                }
                if (S21StringUtil.isEquals(line.shipToFirstRefCmntTxt_LL.getValue(), lineOrg.shipToFirstRefCmntTxt_LL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(line.shipToFirstRefCmntTxt_LL, (String) dsXrefAcct.get("FIRST_REF_CMNT_TXT"));
                }
                if (S21StringUtil.isEquals(line.shipToScdRefCmntTxt_LL.getValue(), lineOrg.shipToScdRefCmntTxt_LL.getValue())) {
                    ZYPEZDItemValueSetter.setValue(line.shipToScdRefCmntTxt_LL, (String) dsXrefAcct.get("SCD_REF_CMNT_TXT"));
                }
            }
        }

        if (dsRtlIntgItemMap != null) {
            // negotiate deal amount

            BigDecimal fndgFeeMultRate = (BigDecimal) dsRtlIntgItemMap.get("FNDG_FEE_MULT_RATE");
            if (fndgFeeMultRate == null) {
                fndgFeeMultRate = BigDecimal.ZERO;
            }
            // get finding dealer complete amount (by install
            // location)
            BigDecimal fndgDlrCompAmt = getFndgDlrCompAmt(GLBL_CMPY.CANON_USA_INC, cpoOrdNum, instlCd, instlSubLocCd, null);
            if (fndgDlrCompAmt == null) {
                fndgDlrCompAmt = BigDecimal.ZERO;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.negoDealAmt, fndgFeeMultRate.multiply(fndgDlrCompAmt));
        }

        // delivery address comment
        if (!updateDelyAddlCmntTxt(glblCmpyCd, bizMsg.dsImptOrdPk.getValue(), delyAddlCmntTxt)) {
            return false;
        }

        // retail warehouse
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL2200_BCMsg line = bizMsg.B.no(i);
            NWAL2200_JSMsg lineOrg = glblMsg.J.no(i);
            if (S21StringUtil.isEquals(line.rtlWhCd_LL.getValue(), lineOrg.rtlWhCd_LL.getValue())) {
                if (dsRtlIntgItemMap != null) {
                    if (S21StringUtil.isEquals(line.mdseCd_LL.getValue(), (String) dsRtlIntgItemMap.get("FNDG_MDSE_CD"))) {
                        // skip finding charge
                        continue;
                    }
                    if (S21StringUtil.isEquals(line.mdseCd_LL.getValue(), (String) dsRtlIntgItemMap.get("ISTL_MDSE_CD"))) {
                        // skip install charge
                        continue;
                    }
                }
                // 2017/12/05 S21_NA#22624 Mod Start
//                if (S21StringUtil.isEquals(shipToAddrTpCd, "2")) {
//
//                    // drop ship(Install Location)
//                    ZYPEZDItemValueSetter.setValue(line.rtlWhCd_LL, ZYPCodeDataUtil.getVarCharConstValue("DROP_SHIP_RTL_WH_CD", glblCmpyCd));
//
//                } else {
//
//                    if (dsRtlDlr != null) {
//                        ZYPEZDItemValueSetter.setValue(line.rtlWhCd_LL, (String) dsRtlDlr.get("WH_CD"));
//                    }
//                 }
                if (S21StringUtil.isEquals(shipToAddrTpCd, SHIP_TO_ADDR_TP.INSTALL_LOCATION)//
                        || S21StringUtil.isEquals(shipToAddrTpCd, SHIP_TO_ADDR_TP.OTHER)) {

                    // drop ship(Install Location)
                    ZYPEZDItemValueSetter.setValue(line.rtlWhCd_LL, ZYPCodeDataUtil.getVarCharConstValue("DROP_SHIP_RTL_WH_CD", glblCmpyCd));
                    ZYPEZDItemValueSetter.setValue(line.ordLineSrcCd_LL, ORD_LINE_SRC.CUSA_DROP_SHIP);

                } else {

                    if (dsRtlDlr != null) {
                        ZYPEZDItemValueSetter.setValue(line.rtlWhCd_LL, (String) dsRtlDlr.get("WH_CD"));
                        ZYPEZDItemValueSetter.setValue(line.ordLineSrcCd_LL, ORD_LINE_SRC.INTERNAL);
                    }
                }
                // 2017/12/05 S21_NA#22624 Mod End
            }
        }

        // service configuration id
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL2200_ACMsg config = bizMsg.A.no(i);
            NWAL2200_ASMsg configOrg = glblMsg.A.no(i);
            if (isEqualsForBigDecimal(config.svcConfigMstrPk_LC.getValue(), configOrg.svcConfigMstrPk_LC.getValue())) {
                if (!isExistsMainMachine(bizMsg.B, cpoOrdNum, config.dsOrdPosnNum_LC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(config.svcConfigMstrPk_LC, svcConfigMstrPk);
                }
            }
        }
        return true;
    }

    private static boolean isEqualsForBigDecimal(BigDecimal value1, BigDecimal value2) {

        if (value1 == null) {

            return value2 == null;
        } else if (value2 == null) {

            return value1 == null;
        } else {

            return value1.compareTo(value2) == 0;
        }
    }

    private static Map<String, Object> getCsaOrdHdr(String glblCmpyCd, String cpoOrdNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getCsaOrdHdr(ssmParam);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmResult.getResultObject();

        return result;
    }

    private static List<Map<String, Object>> getCsaOrdItem(String glblCmpyCd, String cpoOrdNum, String instlCd, String instlSubLocCd, BigDecimal rcpoDtlSQ) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("instlCd", instlCd);
        ssmParam.put("instlSubLocCd", instlSubLocCd);
        ssmParam.put("rcpoDtlSQ", rcpoDtlSQ);

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getCsaOrdItem(ssmParam);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> result = (List<Map<String, Object>>) ssmResult.getResultObject();

        return result;
    }

    private static BigDecimal getFndgDlrCompAmt(String glblCmpyCd, String cpoOrdNum, String instlCd, String instlSubLocCd, BigDecimal rcpoDtlSQ) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("instlCd", instlCd);
        ssmParam.put("instlSubLocCd", instlSubLocCd);
        ssmParam.put("rcpoDtlSQ", rcpoDtlSQ);

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getFndgDlrCompAmt(ssmParam);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        BigDecimal result = (BigDecimal) ssmResult.getResultObject();

        return result;
    }

    private static boolean updateDelyAddlCmntTxt(String glblCmpyCd, BigDecimal dsImptOrdPk, String delyAddlCmntTxt) {

        List<Map<String, Object>> dsImptOrdDelyInfoList = getDsImptOrdDelyInfo(glblCmpyCd, dsImptOrdPk);
        if (dsImptOrdDelyInfoList == null) {
            return true;
        }

        for (Map<String, Object> dsImptOrdDelyInfo : dsImptOrdDelyInfoList) {
            BigDecimal dsImptOrdDelyInfoPk = (BigDecimal) dsImptOrdDelyInfo.get("DS_IMPT_ORD_DELY_INFO_PK");
            DS_IMPT_ORD_DELY_INFOTMsg updateDsImptOrdDelyInfo = new DS_IMPT_ORD_DELY_INFOTMsg();
            ZYPEZDItemValueSetter.setValue(updateDsImptOrdDelyInfo.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(updateDsImptOrdDelyInfo.dsImptOrdDelyInfoPk, dsImptOrdDelyInfoPk);
            updateDsImptOrdDelyInfo = (DS_IMPT_ORD_DELY_INFOTMsg) S21FastTBLAccessor.findByKeyForUpdate(updateDsImptOrdDelyInfo);
            if (updateDsImptOrdDelyInfo == null) {
                continue;
            }
            if (!S21StringUtil.isEquals(updateDsImptOrdDelyInfo.delyAddlCmntTxt.getValue(), delyAddlCmntTxt)) {
                if (delyAddlCmntTxt != null) {
                    ZYPEZDItemValueSetter.setValue(updateDsImptOrdDelyInfo.delyAddlCmntTxt, S21StringUtil.subStringByLength(delyAddlCmntTxt, 0, 180));
                } else {
                    updateDsImptOrdDelyInfo.delyAddlCmntTxt.clear();
                }
            }
        }
        return true;
    }

    private static List<Map<String, Object>> getDsImptOrdDelyInfo(String glblCmpyCd, BigDecimal dsImptOrdPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsImptOrdPk", dsImptOrdPk);

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getDsImptOrdDelyInfo(ssmParam);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> result = (List<Map<String, Object>>) ssmResult.getResultObject();

        return result;
    }

    private static boolean isExistsMainMachine(NWAL2200_BCMsgArray lineList, String cpoOrdNum, String dsOrdPosnNum) {

        for (int i = 0; i < lineList.getValidCount(); i++) {
            NWAL2200_BCMsg line = lineList.no(i);

            if (!S21StringUtil.isEquals(line.dsOrdPosnNum_LL.getValue(), dsOrdPosnNum)) {
                continue;
            }
            BigDecimal rcpoDtlSQ = generateRcpoDtlSQByOrdSrcRefLineNum(line.ordSrcRefLineNum_LL.getValue());
            if (rcpoDtlSQ == null) {
                continue;
            }

            // get CSA order item
            List<Map<String, Object>> csaOrdItem = getCsaOrdItem(GLBL_CMPY.CANON_USA_INC, cpoOrdNum, null, null, rcpoDtlSQ);
            if (csaOrdItem == null || csaOrdItem.size() == 0) {
                continue;
            }
            String machMdlTpCd = (String) csaOrdItem.get(0).get("MACH_MDL_TP_CD");
            if (S21StringUtil.isEquals(machMdlTpCd, MDL_TP.MACHINE_MODEL)) {
                return true;
            }
        }

        return false;
    }

    private static String generateCpoOrdNumByOrdSrcRefNum(String ordSrcRefNum) {

        if (S21StringUtil.isEmpty(ordSrcRefNum)) {
            return null;
        }

        // ordSrcRefNum = cpoOrdNum + "-" + instlCd + "-" " istlSubLoc
        String[] divideOrdSrcRefNum = ordSrcRefNum.split("-");
        if (divideOrdSrcRefNum == null || divideOrdSrcRefNum.length < 3) {
            return null;
        }

        return divideOrdSrcRefNum[0];
    }

    private static BigDecimal generateRcpoDtlSQByOrdSrcRefLineNum(String ordSrcRefLineNum) {

        if (S21StringUtil.isEmpty(ordSrcRefLineNum)) {
            return null;
        }

        // ordSrcRefLineNum = rcpoDtlSQ + "-" + unique sequence
        String[] divideOrdSrcRefLineNum = ordSrcRefLineNum.split("-");
        if (divideOrdSrcRefLineNum == null || divideOrdSrcRefLineNum.length < 2) {
            return null;
        }

        // need to be numeric
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(divideOrdSrcRefLineNum[0]);
        if (!matcher.find()) {
            return null;
        }
        return BigDecimal.valueOf(Integer.valueOf(divideOrdSrcRefLineNum[0]));
    }

    private static DS_IMPT_ORD_ERRTMsg createErr(NWAL2200CMsg bizMsg, BigDecimal dsImptOrdPk, BigDecimal dsImptOrdConfigPk, BigDecimal dsImptOrdDtlPk, BigDecimal dsImptOrdRtrnDtlPk, String messageId, String[] paramArray) {

        DS_IMPT_ORD_ERRTMsg dsImptOrdErr = new DS_IMPT_ORD_ERRTMsg();
        ZYPEZDItemValueSetter.setValue(dsImptOrdErr.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdPk, dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdConfigPk, dsImptOrdConfigPk);
        ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdDtlPk, dsImptOrdDtlPk);
        ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdRtrnDtlPk, dsImptOrdRtrnDtlPk);
        ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdErrMsgId, messageId);
        ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdErrTxt, S21MessageFunc.clspGetMessage(messageId, paramArray));

        return dsImptOrdErr;
    }
}
