package business.blap.NMAL7010.common;

import static business.blap.NMAL7010.common.NMAL7010CommonLogic.toHighValDate;
import static business.blap.NMAL7010.common.NMAL7010CommonLogic.toStr;
import static business.blap.NMAL7010.constant.NMAL7010Constant.COMMA;
import static business.blap.NMAL7010.constant.NMAL7010Constant.MODE_MODIFY;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM0043E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM0072E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM0163E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM0834E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM0847E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8191E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8197E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8215E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8217E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8218E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8219E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8220E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8224E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8226E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8232E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8248E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8249E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8364E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8510E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_EQUIP;
import static business.blap.NMAL7010.constant.NMAL7010Constant.ZZM9000E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCDateItem;
import parts.common.EZDCSVInFile;
import parts.common.EZDCStringItem;
import parts.common.EZDFMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSDateItem;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7010.NMAL7010CMsg;
import business.blap.NMAL7010.NMAL7010Query;
import business.blap.NMAL7010.NMAL7010SMsg;
import business.blap.NMAL7010.NMAL7010_ACMsg;
import business.blap.NMAL7010.NMAL7010_ASMsg;
import business.blap.NMAL7010.NMAL7010_BCMsg;
import business.blap.NMAL7010.NMAL7010_BSMsg;
import business.blap.NMAL7010.NMAL7010_CCMsg;
import business.blap.NMAL7010.NMAL7010_CSMsg;
import business.blap.NMAL7010.NMAL7010_DCMsg;
import business.blap.NMAL7010.NMAL7010_DSMsg;
import business.blap.NMAL7010.NMAL7010_ECMsg;
import business.blap.NMAL7010.NMAL7010_ESMsg;
import business.blap.NMAL7010.NMAL7010_FCMsg;
import business.blap.NMAL7010.NMAL7010_FSMsg;
import business.blap.NMAL7010.NMAL7010_GCMsg;
import business.blap.NMAL7010.NMAL7010_GSMsg;
import business.blap.NMAL7010.NMAL7010_HCMsg;
import business.blap.NMAL7010.NMAL7010_HSMsg;
import business.blap.NMAL7010.NMAL7010_ISMsg;
import business.db.MDL_NMTMsg;
import business.db.MDL_NMTMsgArray;
import business.db.PRC_FMLATMsg;
import business.db.PRC_LIST_QTY_DISCTMsg;
import business.db.PROD_CTRLTMsg;
import business.file.NMAL7010F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_BR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_CH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CUST_AUDC_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_FMLA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RATE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_TRX_AUDC_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NMAL7010CheckLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         M.Nakamura      Create          N/A
 * 2015/12/22   Fujitsu         N.Sugiura       Update          CSA-QC#1125
 * 2016/02/02   SRAA            Y.Chen          Update          QC#2175
 * 2016/02/25   Fujitsu         H.Ikeda         Update          QC#2928
 * 2016/04/15   SRA             E.Inada         Update          QC#6933
 * 2016/07/06   Fujitsu         H.Ikeda         Update          QC#10909
 * 2016/07/15   Fujitsu         W.Honda         Update          QC#11933
 * 2016/07/19   Fujitsu         W.Honda         Update          QC#11933
 * 2016/07/21   Fujitsu         W.Honda         Update          QC#12364
 * 2016/09/16   Hitachi         Y.Takeno        Update          QC#14579
 * 2016/09/21   Hitachi         T.Mizuki        Update          QC#13252
 * 2016/09/28   Hitachi         T.Mizuki        Update          QC#13263
 * 2016/09/29   Hitachi         T.Mizuki        Update          QC#13270
 * 2016/12/07   Fujitsu         N.Aoyama        Update          QC#16312
 * 2017/09/27   Fujitsu         M.Ohno          Update          QC#20977
 * 2018/11/17   Fujitsu         N.Sugiura       Update          QC#29147
 * 2019/11/26   Fujitsu         S.Kosaka        Update          QC#54214
 *</pre>
 */
public class NMAL7010CheckLogic {

    /**
     * checkCommon.
     * @param bizMsg NMAL7010CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkCommon(NMAL7010CMsg bizMsg, String glblCmpyCd) {

        boolean isSuccess = true;
        // Header Check.
        //// Business
        if (isExistPrcListNm(bizMsg)) {
            bizMsg.prcCatgNm_H1.setErrorInfo(1, NMAM0834E, new String[] {"Entered name", "Price List Master"});
            isSuccess = false;
        }

        if (!isExistPrcContr(bizMsg)) {
            bizMsg.prcContrNum_H1.setErrorInfo(1, NMAM0163E, new String[] {bizMsg.prcContrNum_H1.getValue(), "Price Contract Master"});
            isSuccess = false;
        }

        //// Sub Price List
        if (!checkSubPrcList(bizMsg)) {
            isSuccess = false;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.effThruDt_H1)) {
            if (ZYPDateUtil.compare(bizMsg.effFromDt_H1.getValue(), bizMsg.effThruDt_H1.getValue()) > 0) {
                bizMsg.effFromDt_H1.setErrorInfo(1, NMAM0043E, new String[] {"Effective Date From", "Effective Date To" });
                isSuccess = false;
            }
        }

        if (!checkTrxDrv(bizMsg, glblCmpyCd)) {
            isSuccess = false;
        }

        if (!isSuccess) {
            // for error handling
            NMAL7010CommonLogic.openHeaderTab(bizMsg);
        }

        //// Customer Audience
        if (!checkCustAudc(bizMsg, glblCmpyCd)) {
            isSuccess = false;
            // for error handling
            NMAL7010CommonLogic.openCustAudcTab(bizMsg);
        }

        //// Transaction Audience
        if (!checkTrxAudc(bizMsg, glblCmpyCd)) {
            isSuccess = false;
            // for error handling
            NMAL7010CommonLogic.openTrxAudcTab(bizMsg);
        }

        return isSuccess;
    }

    /**
     * checkPrcListEquip.
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @return boolean
     */
    public static boolean checkPrcListEquip(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, String glblCmpyCd, String slsDt) {

        boolean isSuccess = true;
        boolean firstErrFlg = true;
        List<BigDecimal> keyListRec = new ArrayList<BigDecimal>(glblMsg.A.getValidCount());
//        List<String> keyListProd = new ArrayList<String>(glblMsg.A.getValidCount());

        HashMap<String, ArrayList<String[]>> chkPriceMap = new HashMap<String, ArrayList<String[]>>();

        if (bizMsg.A.getValidCount() <= 0) {
            return isSuccess;
        }

        // 2016/07/15 CSA-QC#11933 Add Start
        List<BigDecimal> equipPkList = new ArrayList<BigDecimal>();
        // 2016/07/15 CSA-QC#11933 Add end
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            // Mandatory
            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcQlfyTpCd_PA)) {
                glblMsg.A.no(i).prcQlfyTpCd_PA.setErrorInfo(1, ZZM9000E, new String[] {"Qualifer Type"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcQlfyValTxt_PA)) {
                glblMsg.A.no(i).prcQlfyValTxt_PA.setErrorInfo(1, ZZM9000E, new String[] {"Qualifer Value"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).pkgUomCd_PA)) {
                glblMsg.A.no(i).pkgUomCd_PA.setErrorInfo(1, ZZM9000E, new String[] {"UOM"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).effFromDt_PA)) {
                glblMsg.A.no(i).effFromDt_PA.setErrorInfo(1, ZZM9000E, new String[] {"Effective Date From"});
                isSuccess = false;
            }

            // Business
            // QC#16312 2016/12/07 UPD START
            // if(ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcQlfyTpCd_PA)
            // &&
            // ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcQlfyValTxt_PA))
            // {
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcQlfyTpCd_PA) && ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcQlfyValTxt_PA) && ZYPCommonFunc.hasValue(glblMsg.A.no(i).xxModeCd_A1)) {
                // QC#16312 2016/12/07 UPD E N D
                if (!checkPrcQlfyVal(glblMsg.A.no(i).prcQlfyTpCd_PA, glblMsg.A.no(i).prcQlfyValTxt_PA, glblMsg.A.no(i).mdseDescShortTxt_PA, glblCmpyCd)) {
                    isSuccess = false;
                }
            }

            if ((ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcFmlaPk_PA) && ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcListEquipPrcAmt_PA))
                    || !ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcFmlaPk_PA) && !ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcListEquipPrcAmt_PA)) {
                glblMsg.A.no(i).prcFmlaPk_PA.setErrorInfo(1, NMAM8217E);
                glblMsg.A.no(i).prcListEquipPrcAmt_PA.setErrorInfo(1, NMAM8217E);
                isSuccess = false;
            } else {
                if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcFmlaPk_PA)) {
                    if (!checkFormula(glblMsg.A.no(i).prcFmlaPk_PA, glblMsg.A.no(i).prcQlfyValTxt_PA, glblMsg.A.no(i).prcQlfyTpCd_PA.getValue(), glblCmpyCd)) {
                        isSuccess = false;
                    } else {
                        if (!NMAL7010CommonLogic.fmlaApiCall(glblMsg.A.no(i), glblCmpyCd, slsDt, bizMsg.stdCcyCd.getValue())) {
                            isSuccess = false;
                        }
                    }
                } else {
                    glblMsg.A.no(i).prcFmlaNm_PA.clear();
                    glblMsg.A.no(i).xxCalcTotPrcAmt_PA.clear();
                }

                // UOM
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).pkgUomCd_A2, glblMsg.A.no(i).pkgUomCd_PA.getValue()); // QC#7958
                if (PRC_QLFY_TP.ITEM_CODE.equals(glblMsg.A.no(i).prcQlfyTpCd_PA.getValue())) {
                    // QC#7958
                    if (!checkPrcByUom(glblMsg.A.no(i))) {
                        isSuccess = false;
                    }
//                    if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).xxCalcTotPrcAmt_PA)) {
//                        if (!checkPrcByUom(glblMsg.A.no(i).prcQlfyValTxt_PA, glblMsg.A.no(i).pkgUomCd_PA.getValue(), glblMsg.A.no(i).xxCalcTotPrcAmt_PA.getValue())) {
//                            glblMsg.A.no(i).prcQlfyValTxt_PA.setErrorInfo(1, NMAM8224E);
//                            glblMsg.A.no(i).pkgUomCd_PA.setErrorInfo(1, NMAM8224E);
//                            isSuccess = false;
//                        }
//                    } else {
//                        if (!checkPrcByUom(glblMsg.A.no(i).prcQlfyValTxt_PA, glblMsg.A.no(i).pkgUomCd_PA.getValue(), glblMsg.A.no(i).prcListEquipPrcAmt_PA.getValue())) {
//                            glblMsg.A.no(i).prcQlfyValTxt_PA.setErrorInfo(1, NMAM8232E);
//                            glblMsg.A.no(i).pkgUomCd_PA.setErrorInfo(1, NMAM8232E);
//                            isSuccess = false;
//                        }
//                    }
                }
            }

            // Price Amount
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).minPrcAmt_PA) && ZYPCommonFunc.hasValue(glblMsg.A.no(i).maxPrcAmt_PA)) {
                if (glblMsg.A.no(i).minPrcAmt_PA.getValue().compareTo(glblMsg.A.no(i).maxPrcAmt_PA.getValue()) > 0) {
                    glblMsg.A.no(i).minPrcAmt_PA.setErrorInfo(1, NMAM0043E, new String[] {"Min Price", "Max Price" });
                    isSuccess = false;
                }
            }

            // Lease Payment Amount
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).minLeasePmtAmt_PA) && ZYPCommonFunc.hasValue(glblMsg.A.no(i).maxLeasePmtAmt_PA)) {
                if (glblMsg.A.no(i).minLeasePmtAmt_PA.getValue().compareTo(glblMsg.A.no(i).maxLeasePmtAmt_PA.getValue()) > 0) {
                    glblMsg.A.no(i).minLeasePmtAmt_PA.setErrorInfo(1, NMAM0043E, new String[] {"Min Price", "Max Price" });
                    isSuccess = false;
                }
            }

            // Effective Date
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).effFromDt_PA)) {
                if (ZYPDateUtil.compare(glblMsg.A.no(i).effFromDt_PA.getValue(), toHighValDate(glblMsg.A.no(i).effThruDt_PA.getValue())) > 0) {
                    glblMsg.A.no(i).effFromDt_PA.setErrorInfo(1, NMAM0043E, new String[] {"Effective Date From", "Effective Date To" });
                    isSuccess = false;
                }
            }

            // For check of specification is unclear, temporarily comment.
//            // Product Duplicate Check.
//            if (!glblMsg.A.no(i).prcQlfyTpCd_PA.isError()) {
//                String prodInfo = getProdCtrlInfo(glblMsg.A.no(i).prcQlfyTpCd_PA.getValue(), glblMsg.A.no(i).prcQlfyValTxt_PA.getValue());
//                if (!ZYPCommonFunc.hasValue(prodInfo)) {
//                    glblMsg.A.no(i).prcQlfyValTxt_PA.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.A.no(i).prcQlfyValTxt_PA.getValue(), "Merchandise All View"});
//                    isSuccess = false;
//                } else {
//                    if (isContainsProdCtrl(keyListProd, prodInfo)) {
//                        glblMsg.A.no(i).prcQlfyValTxt_PA.setErrorInfo(1, NMAM0072E, new String[] {glblMsg.A.no(i).prcQlfyValTxt_PA.getValue(), "Product Hierarchy"});
//                        isSuccess = false;
//                    } else {
//                        keyListProd.add(prodInfo);
//                    }
//                }
//            }

            // Record Duplicate Check.
            // 2015/12/22 CSA-QC#1125 Mod Start
            if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(i).delFlg_PA.getValue())) {

                String keyInfo = setKeyInfoPrcListEquip(glblMsg.A.no(i));

                if (chkPriceMap.containsKey(keyInfo)) {
                    if (!recordDuplicateCheck(chkPriceMap, keyInfo, glblMsg.A.no(i).effFromDt_PA.getValue(), glblMsg.A.no(i).effThruDt_PA.getValue())) {
                        glblMsg.A.no(i).xxChkBox_PA.setErrorInfo(1, NMAM0072E, new String[] {"Price List Equipment"});
                        isSuccess = false;
                    }
                }

                addChkPriceMap(chkPriceMap, keyInfo, glblMsg.A.no(i).effFromDt_PA.getValue(), glblMsg.A.no(i).effThruDt_PA.getValue());
                if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcListEquipPk_PA)) {
                    keyListRec.add(glblMsg.A.no(i).prcListEquipPk_PA.getValue());
                }
            }
            // 2015/12/22 CSA-QC#1125 Mod End

            // Term combination check // QC#1223
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcTermAot_PA) || ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcTermUomCd_PA) || ZYPCommonFunc.hasValue(glblMsg.A.no(i).mlyPmtAmt_PA)) {
                if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcTermAot_PA)) {
                    glblMsg.A.no(i).prcTermAot_PA.setErrorInfo(1, NMAM8364E);
                    isSuccess = false;
                }
                if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcTermUomCd_PA)) {
                    glblMsg.A.no(i).prcTermUomCd_PA.setErrorInfo(1, NMAM8364E);
                    isSuccess = false;
                }
                if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).mlyPmtAmt_PA)) {
                    glblMsg.A.no(i).mlyPmtAmt_PA.setErrorInfo(1, NMAM8364E);
                    isSuccess = false;
                }
            }

            // for error handling
            if (!isSuccess && firstErrFlg) {
                NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
                firstErrFlg = false;
            }

            // 2016/07/15 CSA-QC#11933 Add Start
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcListEquipPk_PA)) {
                equipPkList.add(glblMsg.A.no(i).prcListEquipPk_PA.getValue());
            }
            // 2016/07/15 CSA-QC#11933 Add end
            // mod start 2016/09/29 CSA QC#13270
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcListEquipPrcAmt_PA)) {
                if (glblMsg.A.no(i).prcListEquipPrcAmt_PA.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.A.no(i).prcListEquipPrcAmt_PA.setErrorInfo(1, NMAM0847E, new String[] {"Price" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).minPrcAmt_PA)) {
                if (glblMsg.A.no(i).minPrcAmt_PA.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.A.no(i).minPrcAmt_PA.setErrorInfo(1, NMAM0847E, new String[] {"Min Price" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).maxPrcAmt_PA)) {
                if (glblMsg.A.no(i).maxPrcAmt_PA.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.A.no(i).maxPrcAmt_PA.setErrorInfo(1, NMAM0847E, new String[] {"Max Price" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).mlyPmtAmt_PA)) {
                if (glblMsg.A.no(i).mlyPmtAmt_PA.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.A.no(i).mlyPmtAmt_PA.setErrorInfo(1, NMAM0847E, new String[] {"Monthly Payment Amount" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).minLeasePmtAmt_PA)) {
                if (glblMsg.A.no(i).minLeasePmtAmt_PA.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.A.no(i).minLeasePmtAmt_PA.setErrorInfo(1, NMAM0847E, new String[] {"Lease Payment Min" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).maxLeasePmtAmt_PA)) {
                if (glblMsg.A.no(i).maxLeasePmtAmt_PA.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.A.no(i).maxLeasePmtAmt_PA.setErrorInfo(1, NMAM0847E, new String[] {"Lease Payment Max" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).quotRevAmt_PA)) {
                if (glblMsg.A.no(i).quotRevAmt_PA.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.A.no(i).quotRevAmt_PA.setErrorInfo(1, NMAM0847E, new String[] {"Quota Rev" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).custBidQty_PA)) {
                if (glblMsg.A.no(i).custBidQty_PA.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.A.no(i).custBidQty_PA.setErrorInfo(1, NMAM0847E, new String[] {"Bid Qty" });
                    isSuccess = false;
                }
            }
            // mod end 2016/09/29 CSA QC#13270
        }
        // 2015/12/22 CSA-QC#1125 Add Start
        chkPriceMap.clear();
        // 2016/07/15 CSA-QC#11933 Mod Start
//        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
//            S21SsmEZDResult result = NMAL7010Query.getInstance().getPrcListEquipForDuplicateCheck(bizMsg);
//
//            StringBuilder keyInfo = new StringBuilder();
//            if (!result.isCodeNotFound()) {
//                List<PRC_LIST_EQUIPTMsg> prcListEquip = (List) result.getResultObject();
//                for (int i = 0; i < prcListEquip.size(); i++) {
//                    if (!keyListRec.contains(prcListEquip.get(i).prcListEquipPk.getValue())) {
//                        keyInfo.setLength(0);
//                        if (ZYPCommonFunc.hasValue(prcListEquip.get(i).prcListEquipConfigNum)) {
//                            keyInfo = keyInfo.append(toStr(prcListEquip.get(i).prcListEquipConfigNum.getValue())).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListEquip.get(i).prcListEquipConfigNm)) {
//                            keyInfo = keyInfo.append(prcListEquip.get(i).prcListEquipConfigNm.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListEquip.get(i).prcQlfyTpCd)) {
//                            keyInfo = keyInfo.append(prcListEquip.get(i).prcQlfyTpCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListEquip.get(i).prcQlfyValTxt)) {
//                            keyInfo = keyInfo.append(prcListEquip.get(i).prcQlfyValTxt.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListEquip.get(i).pkgUomCd)) {
//                            keyInfo = keyInfo.append(prcListEquip.get(i).pkgUomCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListEquip.get(i).prcTermUomCd)) {
//                            keyInfo = keyInfo.append(prcListEquip.get(i).prcTermUomCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListEquip.get(i).prcTermAot)) {
//                            keyInfo = keyInfo.append(toStr(prcListEquip.get(i).prcTermAot.getValue()));
//                        }
//
//                        addChkPriceMap(chkPriceMap, keyInfo.toString(), prcListEquip.get(i).effFromDt.getValue(), prcListEquip.get(i).effThruDt.getValue());
//
//                    }
//                }
//            }
//        }
//        if (chkPriceMap.size() > 0) {
//            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
//                if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(i).delFlg_PA.getValue())) {
//
//                    String keyInfo = setKeyInfoPrcListEquip(glblMsg.A.no(i));
//
//                    if (chkPriceMap.containsKey(keyInfo)) {
//                        if (!recordDuplicateCheck(chkPriceMap, keyInfo, glblMsg.A.no(i).effFromDt_PA.getValue(), glblMsg.A.no(i).effThruDt_PA.getValue())) {
//                            glblMsg.A.no(i).xxChkBox_PA.setErrorInfo(1, NMAM0072E, new String[] {"Price List Equipment"});
//                            isSuccess = false;
//
//                            // for error handling
//                            if (firstErrFlg) {
//                                NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
//                                firstErrFlg = false;
//                            }
//                        }
//                    }
//                }
//            }
//        }
        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
            for (int i = 0; i < glblMsg.A.getValidCount(); i++){
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(i).delFlg_PA.getValue())
                        || !MODE_MODIFY.equals(glblMsg.A.no(i).xxModeCd_A1.getValue())) {
                    continue;
                }

                S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcListEquipForDuplicateCheck(glblMsg.A.no(i), bizMsg.prcCatgCd_BK.getValue());

                if (!ssmResult.isCodeNormal()) {
                    continue;
                }

                // SSM Result
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                for (Map<String, Object> resultMap : resultList) {
                    if (!equipPkList.contains(resultMap.get("PRC_LIST_EQUIP_PK"))) {
                        glblMsg.A.no(i).xxChkBox_PA.setErrorInfo(1, NMAM0072E, new String[] {"Price List Equipment"});
                        isSuccess = false;

                        // for error handling
                        if (firstErrFlg) {
                            NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
                            firstErrFlg = false;
                        }
                    }
                }
            }
        }
        // 2016/07/15 CSA-QC#11933 Mod Start
        // 2015/12/22 CSA-QC#1125 Add End

        return isSuccess;
    }

    // TODO For check of specification is unclear, temporarily comment.
//    private static String getProdCtrlInfo(String prcQlfyTpCd, String prcQlfyVal) {
//
//        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getProdCtrl(prcQlfyTpCd, prcQlfyVal);
//        if (ssmResult.isCodeNotFound()) {
//            return null;
//        }
//        return (String) ssmResult.getResultObject();
//    }

    // TODO For check of specification is unclear, temporarily comment.
//    private static boolean isContainsProdCtrl(List<String> keyList, String key) {
//        if (keyList.contains(key)) {
//            return true;
//        } else {
//            for (String listVal : keyList) {
//                if (listVal.contains(key)
//                        || key.contains(listVal)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    private static boolean checkPrcQlfyVal(EZDSStringItem prcQlfyTpCd, EZDSStringItem prcQlfyVal, EZDSStringItem prcQlfyNm, String glblCmpyCd) {

        boolean isSuccess = true;
        String rtrnVal = "";

        if (PRC_QLFY_TP.ITEM_CODE.equals(prcQlfyTpCd.getValue())) {
            rtrnVal = NMAL7010CommonLogic.getMdseNm(glblCmpyCd, prcQlfyVal);
            if (!ZYPCommonFunc.hasValue(rtrnVal)) {
                isSuccess = false;
            }
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_1.equals(prcQlfyTpCd.getValue())
                || PRC_QLFY_TP.PRODUCT_HIERARCHY_2.equals(prcQlfyTpCd.getValue())
                || PRC_QLFY_TP.PRODUCT_HIERARCHY_3.equals(prcQlfyTpCd.getValue())
                || PRC_QLFY_TP.PRODUCT_HIERARCHY_4.equals(prcQlfyTpCd.getValue())
                || PRC_QLFY_TP.PRODUCT_HIERARCHY_5.equals(prcQlfyTpCd.getValue())) {
            
            if (PRC_QLFY_TP.PRODUCT_HIERARCHY_1.equals(prcQlfyTpCd.getValue())) {
                PROD_CTRLTMsg inTMsg = new PROD_CTRLTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.prodCtrlCd, prcQlfyVal.getValue());
                PROD_CTRLTMsg outTMsg = (PROD_CTRLTMsg) EZDTBLAccessor.findByKey(inTMsg);
                if (outTMsg == null) {
                    rtrnVal = null;
                    prcQlfyVal.setErrorInfo(1, NMAM0163E, new String[] {prcQlfyVal.getValue(), "Product Hierarchy 1"});
                    isSuccess = false;
                } else if(!S21StringUtil.isEquals(MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP, outTMsg.mdseStruElmntTpCd.getValue())) {
                    rtrnVal = null;
                    prcQlfyVal.setErrorInfo(1, NMAM0163E, new String[] {prcQlfyVal.getValue(), "Product Hierarchy 1"});
                    isSuccess = false;
                } else {
                    rtrnVal = outTMsg.prodCtrlDescTxt.getValue();
                }
            } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_2.equals(prcQlfyTpCd.getValue())) {
                PROD_CTRLTMsg inTMsg = new PROD_CTRLTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.prodCtrlCd, prcQlfyVal.getValue());
                PROD_CTRLTMsg outTMsg = (PROD_CTRLTMsg) EZDTBLAccessor.findByKey(inTMsg);
                if (outTMsg == null) {
                    rtrnVal = null;
                    prcQlfyVal.setErrorInfo(1, NMAM0163E, new String[] {prcQlfyVal.getValue(), "Product Hierarchy 2"});
                    isSuccess = false;
                } else if(!S21StringUtil.isEquals(MDSE_STRU_ELMNT_TP.PRODUCT_LINE, outTMsg.mdseStruElmntTpCd.getValue())) {
                    rtrnVal = null;
                    prcQlfyVal.setErrorInfo(1, NMAM0163E, new String[] {prcQlfyVal.getValue(), "Product Hierarchy 2"});
                    isSuccess = false;
                } else {
                    rtrnVal = outTMsg.prodCtrlDescTxt.getValue();
                }
            } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_3.equals(prcQlfyTpCd.getValue())) {
                PROD_CTRLTMsg inTMsg = new PROD_CTRLTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.prodCtrlCd, prcQlfyVal.getValue());
                PROD_CTRLTMsg outTMsg = (PROD_CTRLTMsg) EZDTBLAccessor.findByKey(inTMsg);
                if (outTMsg == null) {
                    rtrnVal = null;
                    prcQlfyVal.setErrorInfo(1, NMAM0163E, new String[] {prcQlfyVal.getValue(), "Product Hierarchy 3"});
                    isSuccess = false;
                } else if(!S21StringUtil.isEquals(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2, outTMsg.mdseStruElmntTpCd.getValue())) {
                    rtrnVal = null;
                    prcQlfyVal.setErrorInfo(1, NMAM0163E, new String[] {prcQlfyVal.getValue(), "Product Hierarchy 3"});
                    isSuccess = false;
                } else {
                    rtrnVal = outTMsg.prodCtrlDescTxt.getValue();
                }
            } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_4.equals(prcQlfyTpCd.getValue())) {
                PROD_CTRLTMsg inTMsg = new PROD_CTRLTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.prodCtrlCd, prcQlfyVal.getValue());
                PROD_CTRLTMsg outTMsg = (PROD_CTRLTMsg) EZDTBLAccessor.findByKey(inTMsg);
                if (outTMsg == null) {
                    rtrnVal = null;
                    prcQlfyVal.setErrorInfo(1, NMAM0163E, new String[] {prcQlfyVal.getValue(), "Product Hierarchy 4"});
                    isSuccess = false;
                } else if(!S21StringUtil.isEquals(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3, outTMsg.mdseStruElmntTpCd.getValue())) {
                    rtrnVal = null;
                    prcQlfyVal.setErrorInfo(1, NMAM0163E, new String[] {prcQlfyVal.getValue(), "Product Hierarchy 4"});
                    isSuccess = false;
                } else {
                    rtrnVal = outTMsg.prodCtrlDescTxt.getValue();
                }
            } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_5.equals(prcQlfyTpCd.getValue())) {
                PROD_CTRLTMsg inTMsg = new PROD_CTRLTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.prodCtrlCd, prcQlfyVal.getValue());
                PROD_CTRLTMsg outTMsg = (PROD_CTRLTMsg) EZDTBLAccessor.findByKey(inTMsg);
                if (outTMsg == null) {
                    rtrnVal = null;
                    prcQlfyVal.setErrorInfo(1, NMAM0163E, new String[] {prcQlfyVal.getValue(), "Product Hierarchy 5"});
                    isSuccess = false;
                } else if(!S21StringUtil.isEquals(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4, outTMsg.mdseStruElmntTpCd.getValue())) {
                    rtrnVal = null;
                    prcQlfyVal.setErrorInfo(1, NMAM0163E, new String[] {prcQlfyVal.getValue(), "Product Hierarchy 5"});
                    isSuccess = false;
                } else {
                    rtrnVal = outTMsg.prodCtrlDescTxt.getValue();
                }
            }
            

        } else if (PRC_QLFY_TP.MERCHANDISE_TYPE.equals(prcQlfyTpCd.getValue())) {
            rtrnVal = ZYPCodeDataUtil.getName(COA_PROJ.class, glblCmpyCd, prcQlfyVal.getValue());
            if (!ZYPCommonFunc.hasValue(rtrnVal)) {
                prcQlfyVal.setErrorInfo(1, NMAM0163E, new String[] {prcQlfyVal.getValue(), "Merchandise Item Type"});
                isSuccess = false;
            }
        }

        if (isSuccess) {
            ZYPEZDItemValueSetter.setValue(prcQlfyNm, rtrnVal);
        }

        return isSuccess;
    }

    private static boolean checkFormula(EZDSBigDecimalItem prcFmlaPk, EZDSStringItem prcQlfyVal, String prcQlfyTpCd, String glblCmpyCd) {

        boolean isSuccess = true;

        PRC_FMLATMsg inTMsg = new PRC_FMLATMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.prcFmlaPk, prcFmlaPk.getValue());
        PRC_FMLATMsg outTMsg = (PRC_FMLATMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            prcFmlaPk.setErrorInfo(1, NMAM0163E, new String[] {prcFmlaPk.getValue().toString(), "Price Formula"});
            isSuccess = false;
        } else {
            if (PRC_QLFY_TP.ITEM_CODE.equals(prcQlfyTpCd)) {
                if (!PRC_FMLA_TP.PRICE_LIST.equals(outTMsg.prcFmlaTpCd.getValue())
                        && !PRC_FMLA_TP.STANDARD_COST.equals(outTMsg.prcFmlaTpCd.getValue())) {
                    prcFmlaPk.setErrorInfo(1, NMAM8218E);
                    isSuccess = false;
                } else {
                    if (PRC_FMLA_TP.PRICE_LIST.equals(outTMsg.prcFmlaTpCd.getValue())) {
                        if (ZYPCommonFunc.hasValue(outTMsg.prcCatgCd)) {
                            S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcListEquip(outTMsg.prcCatgCd.getValue(), prcQlfyVal.getValue());
                            if (ssmResult.isCodeNotFound()) {
                                prcFmlaPk.setErrorInfo(1, NMAM0163E, new String[] {prcFmlaPk.getValue().toString(), "Price List Equipment"});
                                isSuccess = false;
                            } else {
                                Map< ? , ? > rsltMap = (Map< ? , ? >) ssmResult.getResultObject();
                                if (!ZYPCommonFunc.hasValue(toStr((BigDecimal) rsltMap.get("PRC_LIST_EQUIP_PRC_AMT")))
                                        || ZYPCommonFunc.hasValue(toStr((BigDecimal) rsltMap.get("PRC_FMLA_PK")))) {
                                    prcFmlaPk.setErrorInfo(1, NMAM8220E);
                                    isSuccess = false;
                                }
                            }
                        } else {
                            prcFmlaPk.setErrorInfo(1, NMAM0163E, new String[] {prcFmlaPk.getValue().toString(), "Price List Equipment"});
                            isSuccess = false;
                        }
                    }
                }
            } else {
                if (!PRC_FMLA_TP.PRICE_LIST.equals(outTMsg.prcFmlaTpCd.getValue())) {
                    prcFmlaPk.setErrorInfo(1, NMAM8219E);
                    isSuccess = false;
                }
            }
        }

        return isSuccess;
    }

    // private static boolean checkPrcByUom(EZDSStringItem itemCd, String uomCd, BigDecimal prcAmt) {
    private static boolean checkPrcByUom(NMAL7010_ASMsg asMsg) {
        BigDecimal prcAmt = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(asMsg.xxCalcTotPrcAmt_PA)) {
            prcAmt = asMsg.xxCalcTotPrcAmt_PA.getValue();
        } else {
            prcAmt = asMsg.prcListEquipPrcAmt_PA.getValue();
        }

        if (!ZYPCommonFunc.hasValue(prcAmt)) {
            return false;
        }
        
        // QC#16312 2016/12/07 ADD START
        if (ZYPCommonFunc.hasValue(asMsg.pkgUomCd_A2) && !ZYPCommonFunc.hasValue(asMsg.xxModeCd_A1)){
            return true;
        }
        // QC#16312 2016/12/07 ADD E N D

        // S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getInEachQty(itemCd.getValue(), uomCd);
        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getUomInfo(asMsg.prcQlfyValTxt_PA.getValue(), asMsg.pkgUomCd_PA.getValue());
        if (ssmResult.isCodeNotFound()) {
            asMsg.prcQlfyValTxt_PA.setErrorInfo(1, NMAM8510E);
            asMsg.pkgUomCd_PA.setErrorInfo(1, NMAM8510E);
            return false;
        }
        Map< ? , ? > rsltMap = (Map< ? , ? >) ssmResult.getResultObject();

        BigDecimal amtScale4 = prcAmt.setScale(4, BigDecimal.ROUND_HALF_UP);
        String pkgUomCd = (String) rsltMap.get("PKG_UOM_CD");
        // 2016/07/06 QC#10909 Mod Start
        //BigDecimal divideVal = (amtScale4.divide((BigDecimal) rsltMap.get("IN_EACH_QTY"))).setScale(4, BigDecimal.ROUND_HALF_UP);
        BigDecimal divideVal = (amtScale4.divide((BigDecimal) rsltMap.get("IN_EACH_QTY"), 4, BigDecimal.ROUND_HALF_UP));
        // 2016/07/06 QC#10909 Mod End
        BigDecimal divideValScale2 = divideVal.setScale(2, BigDecimal.ROUND_HALF_UP);

        if (divideVal.compareTo(divideValScale2) != 0) {
            if (ZYPCommonFunc.hasValue(asMsg.xxCalcTotPrcAmt_PA)) {
                asMsg.prcQlfyValTxt_PA.setErrorInfo(1, NMAM8224E);
                asMsg.pkgUomCd_PA.setErrorInfo(1, NMAM8224E);
            } else {
                asMsg.prcQlfyValTxt_PA.setErrorInfo(1, NMAM8232E);
                asMsg.pkgUomCd_PA.setErrorInfo(1, NMAM8232E);
            }
            return false;
        }
        // replace UOM
        ZYPEZDItemValueSetter.setValue(asMsg.pkgUomCd_A2, pkgUomCd);

        return true;
    }

    /**
     * checkPrcListSvc.
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkPrcListSvc(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, String glblCmpyCd) {

        boolean isSuccess = true;
        boolean firstErrFlg = true;
        List<BigDecimal> keyListRec = new ArrayList<BigDecimal>(glblMsg.B.getValidCount());
        HashMap<String, ArrayList<String[]>> chkPriceMap = new HashMap<String, ArrayList<String[]>>();

        if (bizMsg.B.getValidCount() <= 0) {
            return isSuccess;
        }

        // 2016/07/19 CSA-QC#11933 Add Start
        List<BigDecimal> svcPkList = new ArrayList<BigDecimal>();
        // 2016/07/19 CSA-QC#11933 Add end
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            // Mandatory
            // QC#6933 modify
            if (PRC_RATE_TP.CPC.equals(glblMsg.B.no(i).prcRateTpCd_PB.getValue()) || PRC_RATE_TP.ANNUAL.equals(glblMsg.B.no(i).prcRateTpCd_PB.getValue())) {
                if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).mdlNm_PB)) {
                    glblMsg.B.no(i).mdlNm_PB.setErrorInfo(1, ZZM9000E, new String[] {"Service Model Name" });
                    isSuccess = false;
                }
            }

            if (PRC_RATE_TP.CPC.equals(glblMsg.B.no(i).prcRateTpCd_PB.getValue()) || PRC_RATE_TP.ANNUAL.equals(glblMsg.B.no(i).prcRateTpCd_PB.getValue())) {
                if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcMtrPkgNm_PB)) {
                    glblMsg.B.no(i).prcMtrPkgNm_PB.setErrorInfo(1, ZZM9000E, new String[] {"Meter Package Name" });
                    isSuccess = false;
                }
            }

            // QC#6933 add BaseOnly
            if (PRC_RATE_TP.BASE_ONLY.equals(glblMsg.B.no(i).prcRateTpCd_PB.getValue()) ) {
                if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcListMdseCd_PB)) {
                    glblMsg.B.no(i).prcListMdseCd_PB.setErrorInfo(1, ZZM9000E, new String[] {"Item#"});
                    isSuccess = false;
                }
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcSvcPlnTpCd_PB)) {
                glblMsg.B.no(i).prcSvcPlnTpCd_PB.setErrorInfo(1, ZZM9000E, new String[] {"Plan Type"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcSvcContrTpCd_PB)) {
                glblMsg.B.no(i).prcSvcContrTpCd_PB.setErrorInfo(1, ZZM9000E, new String[] {"Contract Type"});
                isSuccess = false;
            }

            // QC#4466 add Annual check // QC#6933 modify
            // if (!PRC_RATE_TP.ANNUAL.equals(glblMsg.B.no(i).prcRateTpCd_PB.getValue())) {
            if (PRC_RATE_TP.CPC.equals(glblMsg.B.no(i).prcRateTpCd_PB.getValue())) {
                if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).minCopyVolCnt_PB)) {
                    glblMsg.B.no(i).minCopyVolCnt_PB.setErrorInfo(1, ZZM9000E, new String[] {"Min Vol"});
                    isSuccess = false;
                }

                if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).maxCopyVolCnt_PB)) {
                    glblMsg.B.no(i).maxCopyVolCnt_PB.setErrorInfo(1, ZZM9000E, new String[] {"Max Vol"});
                    isSuccess = false;
                }
            }

            if (PRC_RATE_TP.CPC.equals(glblMsg.B.no(i).prcRateTpCd_PB.getValue())) {
                // 2018/11/17 QC#29147 Mod Start
                // if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcListBandCd_PB)) {
                //     glblMsg.B.no(i).prcListBandCd_PB.setErrorInfo(1, ZZM9000E, new String[] {"Band" });
                //     isSuccess = false;
                // }
                if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcListBandDescTxt_PB)) {
                    glblMsg.B.no(i).prcListBandDescTxt_PB.setErrorInfo(1, ZZM9000E, new String[] {"Band" });
                    isSuccess = false;
                }
                // 2018/11/17 QC#29147 Mod End
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).baseAmt_PB)) {
                glblMsg.B.no(i).baseAmt_PB.setErrorInfo(1, ZZM9000E, new String[] {"Base Amount"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).termFromMthAot_PB)) {
                glblMsg.B.no(i).termFromMthAot_PB.setErrorInfo(1, ZZM9000E, new String[] {"Term From (MTH)"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).termThruMthAot_PB)) {
                glblMsg.B.no(i).termThruMthAot_PB.setErrorInfo(1, ZZM9000E, new String[] {"Term To (MTH)"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).effFromDt_PB)) {
                glblMsg.B.no(i).effFromDt_PB.setErrorInfo(1, ZZM9000E, new String[] {"Effective Date From"});
                isSuccess = false;
            }

            if (PRC_RATE_TP.CPC.equals(glblMsg.B.no(i).prcRateTpCd_PB.getValue())) {
                if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).mtrLbNm_PB)) {
                    glblMsg.B.no(i).mtrLbNm_PB.setErrorInfo(1, ZZM9000E, new String[] {"Meter Type"});
                    isSuccess = false;
                }

//                if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).mtrLbCd_PB)) {
//                    glblMsg.B.no(i).mtrLbCd_PB.setErrorInfo(1, ZZM9000E, new String[] {"Meter Type"});
//                    isSuccess = false;
//                }

                if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).cpcAmtRate_PB)) {
                    glblMsg.B.no(i).cpcAmtRate_PB.setErrorInfo(1, ZZM9000E, new String[] {"Cost Per Copy (OVERAGE)"});
                    isSuccess = false;
                }

                if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).mdseCd_PB)) {
                    glblMsg.B.no(i).mdseCd_PB.setErrorInfo(1, ZZM9000E, new String[] {"Service Item"});
                    isSuccess = false;
                }
            }

            //Meter Label exists check
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).mtrLbNm_PB)) {
                String mtrLbNm = getMtrLb(glblMsg.B.no(i).mtrLbNm_PB.getValue());
                if (!ZYPCommonFunc.hasValue(mtrLbNm)) {
                    glblMsg.B.no(i).mtrLbNm_PB.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.B.no(i).mtrLbNm_PB.getValue().toString(), "Meter Label"});
                    isSuccess = false;
                }
            }

            // Service Model
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).mdlNm_PB)) {
                MDL_NMTMsg mdlInTMsg = new MDL_NMTMsg();
                mdlInTMsg.setSQLID("001");
                mdlInTMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
                mdlInTMsg.setConditionValue("t_MdlNm01", glblMsg.B.no(i).mdlNm_PB.getValue());
                MDL_NMTMsgArray rsltTMsg = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlInTMsg);
                if (rsltTMsg == null || rsltTMsg.length() == 0) {
                    glblMsg.B.no(i).mdlNm_PB.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.B.no(i).mdlNm_PB.getValue().toString(), "Model Name"});
                    glblMsg.B.no(i).mdlId_PB.clear();
                    isSuccess = false;
                } else {
                    ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).mdlId_PB, rsltTMsg.no(0).t_MdlId);
                }
            }

            // Meter Package Name
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcMtrPkgNm_PB)) {
                BigDecimal mtrPkgPk =  getBigDecimalAnyItem("PRC_MTR_PKG", glblMsg.B.no(i).prcMtrPkgNm_PB.getValue());
                if (!ZYPCommonFunc.hasValue(mtrPkgPk)) {
                    glblMsg.B.no(i).prcMtrPkgNm_PB.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.B.no(i).prcMtrPkgNm_PB.getValue().toString(), "Price Meter Package"});
                    isSuccess = false;
                }
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).prcMtrPkgPk_PB, mtrPkgPk);
            } else {
                glblMsg.B.no(i).prcMtrPkgPk_PB.clear();
            }

            //Meter Type
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).mtrLbNm_PB)) {
                String mtrLbCd = getBllgMtrLb(glblMsg.B.no(i).mtrLbNm_PB.getValue());
                if (!ZYPCommonFunc.hasValue(mtrLbCd)) {
                    glblMsg.B.no(i).mtrLbNm_PB.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.B.no(i).mtrLbNm_PB.getValue().toString(), "Meter Type Master"});
                    isSuccess = false;
                }
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).mtrLbCd_PB, mtrLbCd);
            } else {
                glblMsg.B.no(i).mtrLbCd_PB.clear();
            }
            // 2018/11/17 QC#29147 Add Start
            // Price List Band
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcListBandDescTxt_PB)) {
                String prcListBandCd = getPrcListBand(glblMsg.B.no(i).prcListBandDescTxt_PB.getValue());
                if (!ZYPCommonFunc.hasValue(prcListBandCd)) {
                    glblMsg.B.no(i).prcListBandDescTxt_PB.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.B.no(i).prcListBandDescTxt_PB.getValue().toString(), "Price List Band Master"});
                    isSuccess = false;
                }
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).prcListBandCd_PB, prcListBandCd);
            } else {
                glblMsg.B.no(i).prcListBandCd_PB.clear();
            }
            // 2018/11/17 QC#29147 Add End

            // Item# // Add QC#6933
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcListMdseCd_PB)) {
                String rtrnVal = NMAL7010CommonLogic.getMdseNm(glblCmpyCd, glblMsg.B.no(i).prcListMdseCd_PB);
                if (!ZYPCommonFunc.hasValue(rtrnVal)) {
                    isSuccess = false;
                }
            }

            // Service Item
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).mdseCd_PB)) {
                String rtrnVal = NMAL7010CommonLogic.getMdseNm(glblCmpyCd, glblMsg.B.no(i).mdseCd_PB, true);
                if (!ZYPCommonFunc.hasValue(rtrnVal)) {
                    isSuccess = false;
                }
            }

            // Volume
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).minCopyVolCnt_PB) && ZYPCommonFunc.hasValue(glblMsg.B.no(i).maxCopyVolCnt_PB)) {
                if (glblMsg.B.no(i).minCopyVolCnt_PB.getValue().compareTo(glblMsg.B.no(i).maxCopyVolCnt_PB.getValue()) > 0) {
                    glblMsg.B.no(i).minCopyVolCnt_PB.setErrorInfo(1, NMAM0043E, new String[] {"Min Vol", "Max Vol" });
                    isSuccess = false;
                }
            }

            // Base
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).minBaseAmt_PB) && ZYPCommonFunc.hasValue(glblMsg.B.no(i).maxBaseAmt_PB)) {
                if (glblMsg.B.no(i).minBaseAmt_PB.getValue().compareTo(glblMsg.B.no(i).maxBaseAmt_PB.getValue()) > 0) {
                    glblMsg.B.no(i).minBaseAmt_PB.setErrorInfo(1, NMAM0043E, new String[] {"Min Base", "Max Base" });
                    isSuccess = false;
                }
            }

            // CPC
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).minCpcAmtRate_PB) && ZYPCommonFunc.hasValue(glblMsg.B.no(i).maxCpcAmtRate_PB)) {
                if (glblMsg.B.no(i).minCpcAmtRate_PB.getValue().compareTo(glblMsg.B.no(i).maxCpcAmtRate_PB.getValue()) > 0) {
                    glblMsg.B.no(i).minCpcAmtRate_PB.setErrorInfo(1, NMAM0043E, new String[] {"Min CPC", "Max CPC" });
                    isSuccess = false;
                }
            }

            // Term From
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).termFromMthAot_PB) && ZYPCommonFunc.hasValue(glblMsg.B.no(i).termThruMthAot_PB)) {
                if (glblMsg.B.no(i).termFromMthAot_PB.getValue().compareTo(glblMsg.B.no(i).termThruMthAot_PB.getValue()) > 0) {
                    glblMsg.B.no(i).termFromMthAot_PB.setErrorInfo(1, NMAM0043E, new String[] {"Term From", "Term To" });
                    isSuccess = false;
                }
            }

            // Price Meter Package Billing Meter
            if (PRC_RATE_TP.CPC.equals(glblMsg.B.no(i).prcRateTpCd_PB.getValue()) 
                    && ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcMtrPkgPk_PB) 
                    && ZYPCommonFunc.hasValue(glblMsg.B.no(i).mtrLbCd_PB) 
                    && ZYPCommonFunc.hasValue(glblMsg.B.no(i).mdlId_PB)) {
                BigDecimal prcMtrPkgBllgMtPk = getPrcMtrPkgBllgMtr(glblMsg.B.no(i).prcMtrPkgPk_PB.getValue(), glblMsg.B.no(i).mtrLbCd_PB.getValue(), glblMsg.B.no(i).mdlId_PB.getValue());
                if (!ZYPCommonFunc.hasValue(prcMtrPkgBllgMtPk)) {
                    glblMsg.B.no(i).prcMtrPkgNm_PB.setErrorInfo(1, NMAM0163E, new String[] {"combined value", "Price Meter Package Billing Meter" });
                    glblMsg.B.no(i).mtrLbNm_PB.setErrorInfo(1, NMAM0163E, new String[] {"combined value", "Price Meter Package Billing Meter" });
                    glblMsg.B.no(i).mdlNm_PB.setErrorInfo(1, NMAM0163E, new String[] {"combined value", "Price Meter Package Billing Meter" });
                    isSuccess = false;
                } else {
                    ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).prcMtrPkgBllgMtrPk_PB, prcMtrPkgBllgMtPk);
                }
            }

            // Effective Date
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).effFromDt_PB)) {
                if (ZYPDateUtil.compare(glblMsg.B.no(i).effFromDt_PB.getValue(), toHighValDate(glblMsg.B.no(i).effThruDt_PB.getValue())) > 0) {
                    glblMsg.B.no(i).effFromDt_PB.setErrorInfo(1, NMAM0043E, new String[] {"Effective Date From", "Effective Date To" });
                    isSuccess = false;
                }
            }

            // Record Duplicate Check.
            // 2015/12/22 CSA-QC#1125 Mod Start
            if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.B.no(i).delFlg_PB.getValue())) {

                String keyInfo = setKeyInfoPrcListSvc(glblMsg.B.no(i));

                if (chkPriceMap.containsKey(keyInfo)) {
                    if (!recordDuplicateCheck(chkPriceMap, keyInfo, glblMsg.B.no(i).effFromDt_PB.getValue(), glblMsg.B.no(i).effThruDt_PB.getValue())) {
                        glblMsg.B.no(i).xxChkBox_PB.setErrorInfo(1, NMAM0072E, new String[] {"Price List Service"});
                        isSuccess = false;
                    }
                }

                addChkPriceMap(chkPriceMap, keyInfo, glblMsg.B.no(i).effFromDt_PB.getValue(), glblMsg.B.no(i).effThruDt_PB.getValue());
                if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcListSvcPk_PB)) {
                    keyListRec.add(glblMsg.B.no(i).prcListSvcPk_PB.getValue());
                }
            }
            // 2015/12/22 CSA-QC#1125 Mod End

            // for error handling
            if (!isSuccess && firstErrFlg) {
                NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
                firstErrFlg = false;
            }

            // 2016/07/19 CSA-QC#11933 Add Start
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcListSvcPk_PB)) {
                svcPkList.add(glblMsg.B.no(i).prcListSvcPk_PB.getValue());
            }
            // 2016/07/19 CSA-QC#11933 Add end

            // mod start 2016/09/29 CSA QC#13270
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).baseAmt_PB)) {
                if (glblMsg.B.no(i).baseAmt_PB.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.B.no(i).baseAmt_PB.setErrorInfo(1, NMAM0847E, new String[] {"Base Amount" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).minBaseAmt_PB)) {
                if (glblMsg.B.no(i).minBaseAmt_PB.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.B.no(i).minBaseAmt_PB.setErrorInfo(1, NMAM0847E, new String[] {"Min Base" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).maxBaseAmt_PB)) {
                if (glblMsg.B.no(i).maxBaseAmt_PB.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.B.no(i).maxBaseAmt_PB.setErrorInfo(1, NMAM0847E, new String[] {"Max Base" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).minCopyVolCnt_PB)) {
                if (glblMsg.B.no(i).minCopyVolCnt_PB.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.B.no(i).minCopyVolCnt_PB.setErrorInfo(1, NMAM0847E, new String[] {"Min Vol" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).maxCopyVolCnt_PB)) {
                if (glblMsg.B.no(i).maxCopyVolCnt_PB.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.B.no(i).maxCopyVolCnt_PB.setErrorInfo(1, NMAM0847E, new String[] {"Max Vol" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).cpcAmtRate_PB)) {
                if (glblMsg.B.no(i).cpcAmtRate_PB.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.B.no(i).cpcAmtRate_PB.setErrorInfo(1, NMAM0847E, new String[] {"Cost Per Copy (OVERAGE)" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).minCpcAmtRate_PB)) {
                if (glblMsg.B.no(i).minCpcAmtRate_PB.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.B.no(i).minCpcAmtRate_PB.setErrorInfo(1, NMAM0847E, new String[] {"Min CPC" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).maxCpcAmtRate_PB)) {
                if (glblMsg.B.no(i).maxCpcAmtRate_PB.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.B.no(i).maxCpcAmtRate_PB.setErrorInfo(1, NMAM0847E, new String[] {"Max CPC" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).quotRevAmt_PB)) {
                if (glblMsg.B.no(i).quotRevAmt_PB.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.B.no(i).quotRevAmt_PB.setErrorInfo(1, NMAM0847E, new String[] {"Quota Rev" });
                    isSuccess = false;
                }
            }
            // mod end 2016/09/29 CSA QC#13270
        }
        // 2015/12/22 CSA-QC#1125 Add Start
        chkPriceMap.clear();
        // 2016/07/19 CSA-QC#11933 Add Start
//        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
//            S21SsmEZDResult result = NMAL7010Query.getInstance().getPrcListSvcForDuplicateCheck(bizMsg);
//
//            StringBuilder keyInfo = new StringBuilder();
//            if (!result.isCodeNotFound()) {
//                List<PRC_LIST_SVCTMsg> prcListSvc = (List) result.getResultObject();
//                for (int i = 0; i < prcListSvc.size(); i++) {
//
//                    if (!keyListRec.contains(prcListSvc.get(i).prcListSvcPk.getValue())) {
//                        keyInfo.setLength(0);
//                        if (ZYPCommonFunc.hasValue(prcListSvc.get(i).mdlId)) {
//                            keyInfo = keyInfo.append(prcListSvc.get(i).mdlId.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSvc.get(i).prcMtrPkgPk)) {
//                            keyInfo = keyInfo.append(toStr(prcListSvc.get(i).prcMtrPkgPk.getValue())).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSvc.get(i).prcListMdseCd)) {
//                            // QC#6933 add
//                            keyInfo = keyInfo.append(prcListSvc.get(i).prcListMdseCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSvc.get(i).prcSvcPlnTpCd)) {
//                            keyInfo = keyInfo.append(prcListSvc.get(i).prcSvcPlnTpCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSvc.get(i).prcSvcContrTpCd)) {
//                            keyInfo = keyInfo.append(prcListSvc.get(i).prcSvcContrTpCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSvc.get(i).bllgMtrLbCd)) {
//                            keyInfo = keyInfo.append(prcListSvc.get(i).bllgMtrLbCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSvc.get(i).prcListBandCd)) {
//                            keyInfo = keyInfo.append(prcListSvc.get(i).prcListBandCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSvc.get(i).termFromMthAot)) {
//                            keyInfo = keyInfo.append(toStr(prcListSvc.get(i).termFromMthAot.getValue())).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSvc.get(i).termThruMthAot)) {
//                            keyInfo = keyInfo.append(toStr(prcListSvc.get(i).termThruMthAot.getValue()));
//                        }
//
//                        addChkPriceMap(chkPriceMap, keyInfo.toString(), prcListSvc.get(i).effFromDt.getValue(), prcListSvc.get(i).effThruDt.getValue());
//
//                    }
//                }
//            }
//        }
//        if (chkPriceMap.size() > 0) {
//            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
//                if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.B.no(i).delFlg_PB.getValue())) {
//
//                    String keyInfo = setKeyInfoPrcListSvc(glblMsg.B.no(i));
//
//                    if (chkPriceMap.containsKey(keyInfo)) {
//                        if (!recordDuplicateCheck(chkPriceMap, keyInfo, glblMsg.B.no(i).effFromDt_PB.getValue(), glblMsg.B.no(i).effThruDt_PB.getValue())) {
//                            glblMsg.B.no(i).xxChkBox_PB.setErrorInfo(1, NMAM0072E, new String[] {"Price List Service"});
//                            isSuccess = false;
//
//                            // for error handling
//                            if (firstErrFlg) {
//                                NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
//                                firstErrFlg = false;
//                            }
//                        }
//                    }
//                }
//            }
//        }
        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
            for (int i = 0; i < glblMsg.B.getValidCount(); i++){
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.B.no(i).delFlg_PB.getValue())
                        || !MODE_MODIFY.equals(glblMsg.B.no(i).xxModeCd_B1.getValue())) {
                    continue;
                }

                S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcListSvcForDuplicateCheck(glblMsg.B.no(i), bizMsg.prcCatgCd_BK.getValue());

                if (!ssmResult.isCodeNormal()) {
                    continue;
                }

                // SSM Result
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                for (Map<String, Object> resultMap : resultList) {
                    if (!svcPkList.contains(resultMap.get("PRC_LIST_XXXX_PK"))) {
                        glblMsg.B.no(i).xxChkBox_PB.setErrorInfo(1, NMAM0072E, new String[] {"Price List Service"});
                        isSuccess = false;

                        // for error handling
                        if (firstErrFlg) {
                            NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
                            firstErrFlg = false;
                        }
                    }
                }
            }
        }
        // 2016/07/19 CSA-QC#11933 Mod end
        // 2015/12/22 CSA-QC#1125 Add End
        return isSuccess;
    }

    /**
     * checkPrcListSvcTier.
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkPrcListSvcTier(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, String glblCmpyCd) {

        boolean isSuccess = true;
        boolean firstErrFlg = true;
        List<BigDecimal> keyListRec = new ArrayList<BigDecimal>(glblMsg.C.getValidCount());
        HashMap<String, ArrayList<String[]>> chkPriceMap = new HashMap<String, ArrayList<String[]>>();

        if (bizMsg.C.getValidCount() <= 0) {
            return isSuccess;
        }

        // 2016/07/19 CSA-QC#11933 Add Start
        List<BigDecimal> svcTierPkList = new ArrayList<BigDecimal>();
        // 2016/07/19 CSA-QC#11933 Add end
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            // Mandatory
            if (!ZYPCommonFunc.hasValue(glblMsg.C.no(i).prcMtrPkgNm_PC)) {
                glblMsg.C.no(i).prcMtrPkgNm_PC.setErrorInfo(1, ZZM9000E, new String[] {"Meter Package Name"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.C.no(i).minCopyVolCnt_PC)) {
                glblMsg.C.no(i).minCopyVolCnt_PC.setErrorInfo(1, ZZM9000E, new String[] {"Min Vol"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.C.no(i).maxCopyVolCnt_PC)) {
                glblMsg.C.no(i).maxCopyVolCnt_PC.setErrorInfo(1, ZZM9000E, new String[] {"Max Vol"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.C.no(i).mdseCd_PC)) {
                glblMsg.C.no(i).mdseCd_PC.setErrorInfo(1, ZZM9000E, new String[] {"Service Item"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.C.no(i).effFromDt_PC)) {
                glblMsg.C.no(i).effFromDt_PC.setErrorInfo(1, ZZM9000E, new String[] {"Effective Date From"});
                isSuccess = false;
            }

            // Service Model
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).mdlNm_PC)) {
                MDL_NMTMsg mdlInTMsg = new MDL_NMTMsg();
                mdlInTMsg.setSQLID("001");
                mdlInTMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
                mdlInTMsg.setConditionValue("t_MdlNm01", glblMsg.C.no(i).mdlNm_PC.getValue());
                MDL_NMTMsgArray rsltTMsg = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlInTMsg);
                if (rsltTMsg == null || rsltTMsg.length() == 0) {
                    glblMsg.C.no(i).mdlNm_PC.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.C.no(i).mdlNm_PC.getValue().toString(), "Model Name"});
                    isSuccess = false;
                // 2016/07/19 CSA-QC#12364 Mod Start
//                }
//                ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).mdlId_PC, rsltTMsg.no(0).t_MdlId);
                } else {
                    ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).mdlId_PC, rsltTMsg.no(0).t_MdlId);
                }
                // 2016/07/19 CSA-QC#12364 Mod end
            }

            // Meter Package Name
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).prcMtrPkgNm_PC)) {
                BigDecimal mtrPkgPk =  getBigDecimalAnyItem("PRC_MTR_PKG", glblMsg.C.no(i).prcMtrPkgNm_PC.getValue());
                if (!ZYPCommonFunc.hasValue(mtrPkgPk)) {
                    glblMsg.C.no(i).prcMtrPkgNm_PC.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.C.no(i).prcMtrPkgNm_PC.getValue().toString(), "Price Meter Package"});
                    isSuccess = false;
                }
                ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).prcMtrPkgPk_PC, mtrPkgPk);
            }

            //Meter Type
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).mtrLbNm_PC)) {
                String mtrLbCd = getBllgMtrLb(glblMsg.C.no(i).mtrLbNm_PC.getValue());
                if (!ZYPCommonFunc.hasValue(mtrLbCd)) {
                    glblMsg.C.no(i).mtrLbNm_PC.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.C.no(i).mtrLbNm_PC.getValue().toString(), "Meter Type Master"});
                    isSuccess = false;
                }
                ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).mtrLbCd_PC, mtrLbCd);
            } else {
                glblMsg.C.no(i).mtrLbCd_PC.clear();
            }
            // 2018/11/17 QC#29147 Add Start
            // Price List Band
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).prcListBandDescTxt_PC)) {
                String prcListBandCd = getPrcListBand(glblMsg.C.no(i).prcListBandDescTxt_PC.getValue());
                if (!ZYPCommonFunc.hasValue(prcListBandCd)) {
                    glblMsg.C.no(i).prcListBandDescTxt_PC.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.C.no(i).prcListBandDescTxt_PC.getValue().toString(), "Price List Band Master"});
                    isSuccess = false;
                }
                ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).prcListBandCd_PC, prcListBandCd);
            } else {
                glblMsg.C.no(i).prcListBandCd_PC.clear();
            }
            // 2018/11/17 QC#29147 Add End
            // Service Item
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).mdseCd_PC)) {
                String rtrnVal = NMAL7010CommonLogic.getMdseNm(glblCmpyCd, glblMsg.C.no(i).mdseCd_PC, true);
                if (!ZYPCommonFunc.hasValue(rtrnVal)) {
                    isSuccess = false;
                }
            }

            // Volume
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).minCopyVolCnt_PC) && ZYPCommonFunc.hasValue(glblMsg.C.no(i).maxCopyVolCnt_PC)) {
                if (glblMsg.C.no(i).minCopyVolCnt_PC.getValue().compareTo(glblMsg.C.no(i).maxCopyVolCnt_PC.getValue()) > 0) {
                    glblMsg.C.no(i).minCopyVolCnt_PC.setErrorInfo(1, NMAM0043E, new String[] {"Min Vol", "Max Vol" });
                    isSuccess = false;
                }
            }

            // Base Amount
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).minBaseAmt_PC) && ZYPCommonFunc.hasValue(glblMsg.C.no(i).maxBaseAmt_PC)) {
                if (glblMsg.C.no(i).minBaseAmt_PC.getValue().compareTo(glblMsg.C.no(i).maxBaseAmt_PC.getValue()) > 0) {
                    glblMsg.C.no(i).minBaseAmt_PC.setErrorInfo(1, NMAM0043E, new String[] {"Min Base", "Max Base" });
                    isSuccess = false;
                }
            }

            // CPC
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).minCpcAmtRate_PC) && ZYPCommonFunc.hasValue(glblMsg.C.no(i).maxCpcAmtRate_PC)) {
                if (glblMsg.C.no(i).minCpcAmtRate_PC.getValue().compareTo(glblMsg.C.no(i).maxCpcAmtRate_PC.getValue()) > 0) {
                    glblMsg.C.no(i).minCpcAmtRate_PC.setErrorInfo(1, NMAM0043E, new String[] {"Min CPC", "Max CPC" });
                    isSuccess = false;
                }
            }

            // Term From
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).termFromMthAot_PC) && ZYPCommonFunc.hasValue(glblMsg.C.no(i).termThruMthAot_PC)) {
                if (glblMsg.C.no(i).termFromMthAot_PC.getValue().compareTo(glblMsg.C.no(i).termThruMthAot_PC.getValue()) > 0) {
                    glblMsg.C.no(i).termFromMthAot_PC.setErrorInfo(1, NMAM0043E, new String[] {"Min CPC", "Max CPC" });
                    isSuccess = false;
                }
            }

            //Meter Label exists check
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).mtrLbNm_PC)) {
                String mtrLbNm = getMtrLb(glblMsg.C.no(i).mtrLbNm_PC.getValue());
                if (!ZYPCommonFunc.hasValue(mtrLbNm)) {
                    glblMsg.C.no(i).mtrLbNm_PC.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.C.no(i).mtrLbNm_PC.getValue().toString(), "Meter Label"});
                    isSuccess = false;
                }
            }

            
            // Price Meter Package Billing Meter
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).prcMtrPkgPk_PC) && ZYPCommonFunc.hasValue(glblMsg.C.no(i).mtrLbCd_PC) && ZYPCommonFunc.hasValue(glblMsg.C.no(i).mdlId_PC)) {
                BigDecimal prcMtrPkgBllgMtPk = getPrcMtrPkgBllgMtr(glblMsg.C.no(i).prcMtrPkgPk_PC.getValue(), glblMsg.C.no(i).mtrLbCd_PC.getValue(), glblMsg.C.no(i).mdlId_PC.getValue());
                if (!ZYPCommonFunc.hasValue(prcMtrPkgBllgMtPk)) {
                    glblMsg.C.no(i).prcMtrPkgNm_PC.setErrorInfo(1, NMAM0163E, new String[] {"combined value", "Price Meter Package Billing Meter" });
                    glblMsg.C.no(i).mtrLbNm_PC.setErrorInfo(1, NMAM0163E, new String[] {"combined value", "Price Meter Package Billing Meter" });
                    glblMsg.C.no(i).mdlNm_PC.setErrorInfo(1, NMAM0163E, new String[] {"combined value", "Price Meter Package Billing Meter" });
                    isSuccess = false;
                } else {
                    ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).prcMtrPkgBllgMtrPk_PB, prcMtrPkgBllgMtPk);
                }
            }

            // Effective Date
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).effFromDt_PC)) {
                if (ZYPDateUtil.compare(glblMsg.C.no(i).effFromDt_PC.getValue(), toHighValDate(glblMsg.C.no(i).effThruDt_PC.getValue())) > 0) {
                    glblMsg.C.no(i).effFromDt_PC.setErrorInfo(1, NMAM0043E, new String[] {"Effective Date From", "Effective Date To" });
                    isSuccess = false;
                }
            }

            // Record Duplicate Check.
            // 2015/12/22 CSA-QC#1125 Mod Start
            if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.C.no(i).delFlg_PC.getValue())) {

                String keyInfo = setKeyInfoPrcListSvcTier(glblMsg.C.no(i));

                if (chkPriceMap.containsKey(keyInfo)) {
                    if (!recordDuplicateCheck(chkPriceMap, keyInfo, glblMsg.C.no(i).effFromDt_PC.getValue(), glblMsg.C.no(i).effThruDt_PC.getValue())) {
                        glblMsg.C.no(i).xxChkBox_PC.setErrorInfo(1, NMAM0072E, new String[] {"Price List Service Tiers"});
                        isSuccess = false;
                    }
                }

                addChkPriceMap(chkPriceMap, keyInfo, glblMsg.C.no(i).effFromDt_PC.getValue(), glblMsg.C.no(i).effThruDt_PC.getValue());
                if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).prcListSvcTierPk_PC)) {
                    keyListRec.add(glblMsg.C.no(i).prcListSvcTierPk_PC.getValue());
                }
            }
            // 2015/12/22 CSA-QC#1125 Mod End

            // for error handling
            if (!isSuccess && firstErrFlg) {
                NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
                firstErrFlg = false;
            }

            // 2016/07/19 CSA-QC#11933 Add Start
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).prcListSvcTierPk_PC)) {
                svcTierPkList.add(glblMsg.C.no(i).prcListSvcTierPk_PC.getValue());
            }
            // 2016/07/19 CSA-QC#11933 Add end
            // mod start 2016/09/29 CSA QC#13270
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).avgCopyVolCnt_PC)) {
                if (glblMsg.C.no(i).avgCopyVolCnt_PC.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.C.no(i).avgCopyVolCnt_PC.setErrorInfo(1, NMAM0847E, new String[] {"Avg Copy Volume" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).minCopyVolCnt_PC)) {
                if (glblMsg.C.no(i).minCopyVolCnt_PC.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.C.no(i).minCopyVolCnt_PC.setErrorInfo(1, NMAM0847E, new String[] {"Min Vol" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).maxCopyVolCnt_PC)) {
                if (glblMsg.C.no(i).maxCopyVolCnt_PC.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.C.no(i).maxCopyVolCnt_PC.setErrorInfo(1, NMAM0847E, new String[] {"Max Vol" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).baseAmt_PC)) {
                if (glblMsg.C.no(i).baseAmt_PC.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.C.no(i).baseAmt_PC.setErrorInfo(1, NMAM0847E, new String[] {"Base Amount" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).minBaseAmt_PC)) {
                if (glblMsg.C.no(i).minBaseAmt_PC.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.C.no(i).minBaseAmt_PC.setErrorInfo(1, NMAM0847E, new String[] {"Min Base" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).maxBaseAmt_PC)) {
                if (glblMsg.C.no(i).maxBaseAmt_PC.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.C.no(i).maxBaseAmt_PC.setErrorInfo(1, NMAM0847E, new String[] {"Max Base" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).cpcAmtRate_PC)) {
                if (glblMsg.C.no(i).cpcAmtRate_PC.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.C.no(i).cpcAmtRate_PC.setErrorInfo(1, NMAM0847E, new String[] {"Cost Per Copy (OVERAGE)" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).minCpcAmtRate_PC)) {
                if (glblMsg.C.no(i).minCpcAmtRate_PC.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.C.no(i).minCpcAmtRate_PC.setErrorInfo(1, NMAM0847E, new String[] {"Min CPC" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).maxCpcAmtRate_PC)) {
                if (glblMsg.C.no(i).maxCpcAmtRate_PC.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.C.no(i).maxCpcAmtRate_PC.setErrorInfo(1, NMAM0847E, new String[] {"Max CPC" });
                    isSuccess = false;
                }
            }
            // mod end 2016/09/29 CSA QC#13270
        }
     // 2015/12/22 CSA-QC#1125 Add Start
        chkPriceMap.clear();
        // 2016/07/19 CSA-QC#11933 Mod Start
//        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
//            S21SsmEZDResult result = NMAL7010Query.getInstance().getPrcListSvcTierForDuplicateCheck(bizMsg);
//
//            StringBuilder keyInfo = new StringBuilder();
//            if (!result.isCodeNotFound()) {
//                List<PRC_LIST_SVC_TIERTMsg> prcListSvcTier = (List) result.getResultObject();
//                for (int i = 0; i < prcListSvcTier.size(); i++) {
//
//                    if (!keyListRec.contains(prcListSvcTier.get(i).prcListSvcTierPk.getValue())) {
//                        keyInfo.setLength(0);
//                        if (ZYPCommonFunc.hasValue(prcListSvcTier.get(i).mdlId)) {
//                            keyInfo = keyInfo.append(toStr(prcListSvcTier.get(i).mdlId.getValue())).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSvcTier.get(i).prcSvcTierTpCd)) {
//                            keyInfo = keyInfo.append(prcListSvcTier.get(i).prcSvcTierTpCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSvcTier.get(i).prcMtrPkgPk)) {
//                            keyInfo = keyInfo.append(toStr(prcListSvcTier.get(i).prcMtrPkgPk.getValue())).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSvcTier.get(i).prcSvcPlnTpCd)) {
//                            keyInfo = keyInfo.append(prcListSvcTier.get(i).prcSvcPlnTpCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSvcTier.get(i).prcSvcContrTpCd)) {
//                            keyInfo = keyInfo.append(prcListSvcTier.get(i).prcSvcContrTpCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSvcTier.get(i).bllgMtrLbCd)) {
//                            keyInfo = keyInfo.append(prcListSvcTier.get(i).bllgMtrLbCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSvcTier.get(i).prcListBandCd)) {
//                            keyInfo = keyInfo.append(prcListSvcTier.get(i).prcListBandCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSvcTier.get(i).termFromMthAot)) {
//                            keyInfo = keyInfo.append(toStr(prcListSvcTier.get(i).termFromMthAot.getValue())).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSvcTier.get(i).termThruMthAot)) {
//                            keyInfo = keyInfo.append(toStr(prcListSvcTier.get(i).termThruMthAot.getValue()));
//                        }
//
//                        addChkPriceMap(chkPriceMap, keyInfo.toString(), prcListSvcTier.get(i).effFromDt.getValue(), prcListSvcTier.get(i).effThruDt.getValue());
//
//                    }
//                }
//            }
//        }
//        if (chkPriceMap.size() > 0) {
//            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
//                if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.C.no(i).delFlg_PC.getValue())) {
//
//                    String keyInfo = setKeyInfoPrcListSvcTier(glblMsg.C.no(i));
//
//                    if (chkPriceMap.containsKey(keyInfo)) {
//                        if (!recordDuplicateCheck(chkPriceMap, keyInfo, glblMsg.C.no(i).effFromDt_PC.getValue(), glblMsg.C.no(i).effThruDt_PC.getValue())) {
//                            glblMsg.C.no(i).xxChkBox_PC.setErrorInfo(1, NMAM0072E, new String[] {"Price List Service Tiers"});
//                            isSuccess = false;
//
//                            // for error handling
//                            if (firstErrFlg) {
//                                NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
//                                firstErrFlg = false;
//                            }
//                        }
//                    }
//                }
//            }
//        }
        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
            for (int i = 0; i < glblMsg.C.getValidCount(); i++){
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.C.no(i).delFlg_PC.getValue())
                        || !MODE_MODIFY.equals(glblMsg.C.no(i).xxModeCd_C1.getValue())) {
                    continue;
                }

                S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcListSvcTierForDuplicateCheck(glblMsg.C.no(i), bizMsg.prcCatgCd_BK.getValue());

                if (!ssmResult.isCodeNormal()) {
                    continue;
                }

                // SSM Result
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                for (Map<String, Object> resultMap : resultList) {
                    if (!svcTierPkList.contains(resultMap.get("PRC_LIST_SVC_TIER_PK"))) {
                        glblMsg.C.no(i).xxChkBox_PC.setErrorInfo(1, NMAM0072E, new String[] {"Price List Service Tiers"});
                        isSuccess = false;

                        // for error handling
                        if (firstErrFlg) {
                            NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
                            firstErrFlg = false;
                        }
                    }
                }
            }
        }
        // 2016/07/19 CSA-QC#11933 Mod end
        // 2015/12/22 CSA-QC#1125 Add End
        return isSuccess;
    }

    /**
     * checkPrcListSvcSpecChrg.
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkPrcListSvcSpecChrg(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, String glblCmpyCd) {

        boolean isSuccess = true;
        boolean firstErrFlg = true;
        List<BigDecimal> keyListRec = new ArrayList<BigDecimal>(glblMsg.C.getValidCount());
        HashMap<String, ArrayList<String[]>> chkPriceMap = new HashMap<String, ArrayList<String[]>>();

        if (bizMsg.D.getValidCount() <= 0) {
            return isSuccess;
        }

        // 2016/07/19 CSA-QC#11933 Add Start
        List<BigDecimal> svcSpecChrgPkList = new ArrayList<BigDecimal>();
        // 2016/07/19 CSA-QC#11933 Add end
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {

            // Mandatory
            if (!ZYPCommonFunc.hasValue(glblMsg.D.no(i).mdseCd_PD)) {
                glblMsg.D.no(i).mdseCd_PD.setErrorInfo(1, ZZM9000E, new String[] {"Item Code"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.D.no(i).addlChrgPrcAmt_PD)) {
                glblMsg.D.no(i).addlChrgPrcAmt_PD.setErrorInfo(1, ZZM9000E, new String[] {"Price"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.D.no(i).effFromDt_PD)) {
                glblMsg.D.no(i).effFromDt_PD.setErrorInfo(1, ZZM9000E, new String[] {"Effective Date From"});
                isSuccess = false;
            }

            // Service Model
            if (ZYPCommonFunc.hasValue(glblMsg.D.no(i).mdlNm_PD)) {
                MDL_NMTMsg mdlInTMsg = new MDL_NMTMsg();
                mdlInTMsg.setSQLID("001");
                mdlInTMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
                mdlInTMsg.setConditionValue("t_MdlNm01", glblMsg.D.no(i).mdlNm_PD.getValue());
                MDL_NMTMsgArray rsltTMsg = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlInTMsg);
                if (rsltTMsg == null || rsltTMsg.length() == 0) {
                    glblMsg.D.no(i).mdlNm_PD.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.D.no(i).mdlNm_PD.getValue().toString(), "Model Name"});
                    isSuccess = false;
                }
            }

            // Service Item
            if (ZYPCommonFunc.hasValue(glblMsg.D.no(i).mdseCd_PD)) {
                String rtrnVal = NMAL7010CommonLogic.getMdseNm(glblCmpyCd, glblMsg.D.no(i).mdseCd_PD, false);
                if (!ZYPCommonFunc.hasValue(rtrnVal)) {
                    isSuccess = false;
                }
            }

            // Effective Date
            if (ZYPCommonFunc.hasValue(glblMsg.D.no(i).effFromDt_PD)) {
                if (ZYPDateUtil.compare(glblMsg.D.no(i).effFromDt_PD.getValue(), toHighValDate(glblMsg.D.no(i).effThruDt_PD.getValue())) > 0) {
                    glblMsg.D.no(i).effFromDt_PD.setErrorInfo(1, NMAM0043E, new String[] {"Effective Date From", "Effective Date To" });
                    isSuccess = false;
                }
            }

            // Record Duplicate Check.
            // 2015/12/22 CSA-QC#1125 Mod Start
            if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.D.no(i).delFlg_PD.getValue())) {

                String keyInfo = setKeyInfoPrcListAddlChrg(glblMsg.D.no(i));

                if (chkPriceMap.containsKey(keyInfo)) {
                    if (!recordDuplicateCheck(chkPriceMap, keyInfo, glblMsg.D.no(i).effFromDt_PD.getValue(), glblMsg.D.no(i).effThruDt_PD.getValue())) {
                        glblMsg.D.no(i).xxChkBox_PD.setErrorInfo(1, NMAM0072E, new String[] {"Price List Service Special Charges"});
                        isSuccess = false;
                    }
                }

                addChkPriceMap(chkPriceMap, keyInfo, glblMsg.D.no(i).effFromDt_PD.getValue(), glblMsg.D.no(i).effThruDt_PD.getValue());
                if (ZYPCommonFunc.hasValue(glblMsg.D.no(i).prcListAddlChrgPk_PD)) {
                    keyListRec.add(glblMsg.D.no(i).prcListAddlChrgPk_PD.getValue());
                }
            }
            // 2015/12/22 CSA-QC#1125 Mod End

            // for error handling
            if (!isSuccess && firstErrFlg) {
                NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
                firstErrFlg = false;
            }

            // 2016/07/19 CSA-QC#11933 Add Start
            if (ZYPCommonFunc.hasValue(glblMsg.D.no(i).prcListAddlChrgPk_PD)) {
                svcSpecChrgPkList.add(glblMsg.D.no(i).prcListAddlChrgPk_PD.getValue());
            }
            // 2016/07/19 CSA-QC#11933 Add end
            // mod start 2016/09/29 CSA QC#13270
            if (ZYPCommonFunc.hasValue(glblMsg.D.no(i).addlChrgPrcAmt_PD)) {
                if (glblMsg.D.no(i).addlChrgPrcAmt_PD.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.D.no(i).addlChrgPrcAmt_PD.setErrorInfo(1, NMAM0847E, new String[] {"Price" });
                    isSuccess = false;
                }
            }
            // mod end 2016/09/29 CSA QC#13270
        }
        // 2015/12/22 CSA-QC#1125 Add Start
        chkPriceMap.clear();
        // 2016/07/19 CSA-QC#11933 Mod Start
//        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
//            S21SsmEZDResult result = NMAL7010Query.getInstance().getPrcListAddlChrgForDuplicateCheck(bizMsg);
//
//            StringBuilder keyInfo = new StringBuilder();
//            if (!result.isCodeNotFound()) {
//                List<PRC_LIST_ADDL_CHRGTMsg> prcListAddlChrg = (List) result.getResultObject();
//                for (int i = 0; i < prcListAddlChrg.size(); i++) {
//                    if (!keyListRec.contains(prcListAddlChrg.get(i).prcListAddlChrgPk.getValue())) {
//                        keyInfo.setLength(0);
//                        if (ZYPCommonFunc.hasValue(prcListAddlChrg.get(i).mdseCd)) {
//                            keyInfo = keyInfo.append(prcListAddlChrg.get(i).mdseCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListAddlChrg.get(i).mktMdlSegCd)) {
//                            keyInfo = keyInfo.append(prcListAddlChrg.get(i).mktMdlSegCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListAddlChrg.get(i).mdlId)) {
//                            keyInfo = keyInfo.append(toStr(prcListAddlChrg.get(i).mdlId.getValue())).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListAddlChrg.get(i).mdlNm)) {
//                            keyInfo = keyInfo.append(prcListAddlChrg.get(i).mdlNm.getValue()).append(COMMA);
//                        }
//
//                        addChkPriceMap(chkPriceMap, keyInfo.toString(), prcListAddlChrg.get(i).effFromDt.getValue(), prcListAddlChrg.get(i).effThruDt.getValue());
//
//                    }
//                }
//            }
//        }
//        if (chkPriceMap.size() > 0) {
//            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
//                if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.D.no(i).delFlg_PD.getValue())) {
//
//                    String keyInfo = setKeyInfoPrcListAddlChrg(glblMsg.D.no(i));
//
//                    if (chkPriceMap.containsKey(keyInfo)) {
//                        if (!recordDuplicateCheck(chkPriceMap, keyInfo, glblMsg.D.no(i).effFromDt_PD.getValue(), glblMsg.D.no(i).effThruDt_PD.getValue())) {
//                            glblMsg.D.no(i).xxChkBox_PD.setErrorInfo(1, NMAM0072E, new String[] {"Price List Service Special Charges"});
//                            isSuccess = false;
//
//                            // for error handling
//                            if (firstErrFlg) {
//                                NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
//                                firstErrFlg = false;
//                            }
//                        }
//                    }
//                }
//            }
//        }

        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
            for (int i = 0; i < glblMsg.D.getValidCount(); i++){
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.D.no(i).delFlg_PD.getValue())
                        || !MODE_MODIFY.equals(glblMsg.D.no(i).xxModeCd_D1.getValue())) {
                    continue;
                }

                S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcListAddlChrgForDuplicateCheck(glblMsg.D.no(i), bizMsg.prcCatgCd_BK.getValue());

                if (!ssmResult.isCodeNormal()) {
                    continue;
                }

                // SSM Result
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                for (Map<String, Object> resultMap : resultList) {
                    if (!svcSpecChrgPkList.contains(resultMap.get("PRC_LIST_ADDL_CHRG_PK"))) {
                        glblMsg.D.no(i).xxChkBox_PD.setErrorInfo(1, NMAM0072E, new String[] {"Price List Service Special Charges"});
                        isSuccess = false;

                        // for error handling
                        if (firstErrFlg) {
                            NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
                            firstErrFlg = false;
                        }
                    }
                }
            }
        }
        // 2016/07/19 CSA-QC#11933 Mod end
        // 2015/12/22 CSA-QC#1125 Add End

        return isSuccess;
    }

    /**
     * checkPrcListSplyPgm.
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkPrcListSplyPgm(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, String glblCmpyCd) {

        boolean isSuccess = true;
        boolean firstErrFlg = true;
        List<BigDecimal> keyListRec = new ArrayList<BigDecimal>(glblMsg.E.getValidCount());
        HashMap<String, ArrayList<String[]>> chkPriceMap = new HashMap<String, ArrayList<String[]>>();

        if (bizMsg.E.getValidCount() <= 0) {
            return isSuccess;
        }

        // 2016/07/19 CSA-QC#11933 Add Start
        List<BigDecimal> splyPgmPkList = new ArrayList<BigDecimal>();
        // 2016/07/19 CSA-QC#11933 Add end
        for (int i = 0; i < glblMsg.E.getValidCount(); i++) {
            // Mandatory
            if (!ZYPCommonFunc.hasValue(glblMsg.E.no(i).prcMtrPkgNm_PE)) {
                glblMsg.E.no(i).prcMtrPkgNm_PE.setErrorInfo(1, ZZM9000E, new String[] {"Meter Package Name"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.E.no(i).minCopyVolCnt_PE)) {
                glblMsg.E.no(i).minCopyVolCnt_PE.setErrorInfo(1, ZZM9000E, new String[] {"Min Vol"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.E.no(i).maxCopyVolCnt_PE)) {
                glblMsg.E.no(i).maxCopyVolCnt_PE.setErrorInfo(1, ZZM9000E, new String[] {"Max Vol"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.E.no(i).cpcAmtRate_PE)) {
                glblMsg.E.no(i).cpcAmtRate_PE.setErrorInfo(1, ZZM9000E, new String[] {"Cost Per Copy(Overage)"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.E.no(i).termFromMthAot_PE)) {
                glblMsg.E.no(i).termFromMthAot_PE.setErrorInfo(1, ZZM9000E, new String[] {"Term From (MTH)"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.E.no(i).termThruMthAot_PE)) {
                glblMsg.E.no(i).termThruMthAot_PE.setErrorInfo(1, ZZM9000E, new String[] {"Term To (MTH)"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.E.no(i).mdseCd_PE)) {
                glblMsg.E.no(i).mdseCd_PE.setErrorInfo(1, ZZM9000E, new String[] {"Service Item"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.E.no(i).prcSvcZoneCd_PE)) {
                glblMsg.E.no(i).prcSvcZoneCd_PE.setErrorInfo(1, ZZM9000E, new String[] {"Service Zone(s) Included"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.E.no(i).effFromDt_PE)) {
                glblMsg.E.no(i).effFromDt_PE.setErrorInfo(1, ZZM9000E, new String[] {"Effective Date From"});
                isSuccess = false;
            }

            // Service Model
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).mdlNm_PE)) {
                MDL_NMTMsg mdlInTMsg = new MDL_NMTMsg();
                mdlInTMsg.setSQLID("001");
                mdlInTMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
                mdlInTMsg.setConditionValue("t_MdlNm01", glblMsg.E.no(i).mdlNm_PE.getValue());
                MDL_NMTMsgArray rsltTMsg = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlInTMsg);
                if (rsltTMsg == null || rsltTMsg.length() == 0) {
                    glblMsg.E.no(i).mdlNm_PE.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.E.no(i).mdlNm_PE.getValue().toString(), "Model Name"});
                    glblMsg.E.no(i).mdlId_PE.clear();
                    isSuccess = false;
                } else {
                    ZYPEZDItemValueSetter.setValue(glblMsg.E.no(i).mdlId_PE, rsltTMsg.no(0).t_MdlId);
                }
            }

            // Meter Package Name
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).prcMtrPkgNm_PE)) {
                if (!isExistTable("PRC_MTR_PKG", glblMsg.E.no(i).prcMtrPkgNm_PE.getValue())) {
                    glblMsg.E.no(i).prcMtrPkgNm_PE.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.E.no(i).prcMtrPkgNm_PE.getValue().toString(), "Price Meter Package"});
                    isSuccess = false;
                }
            }

            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).prcMtrPkgNm_PE)) {
                BigDecimal mtrPkgPk =  getBigDecimalAnyItem("PRC_MTR_PKG", glblMsg.E.no(i).prcMtrPkgNm_PE.getValue());
                if (!ZYPCommonFunc.hasValue(mtrPkgPk)) {
                    glblMsg.E.no(i).prcMtrPkgNm_PE.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.E.no(i).prcMtrPkgNm_PE.getValue().toString(), "Price Meter Package"});
                    glblMsg.E.no(i).prcMtrPkgPk_PE.clear();
                    isSuccess = false;
                } else {
                    ZYPEZDItemValueSetter.setValue(glblMsg.E.no(i).prcMtrPkgPk_PE, mtrPkgPk);
                }
            }

            //Meter Type
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).mtrLbNm_PE)) {
                String mtrLbCd = getBllgMtrLb(glblMsg.E.no(i).mtrLbNm_PE.getValue());
                if (!ZYPCommonFunc.hasValue(mtrLbCd)) {
                    glblMsg.E.no(i).mtrLbNm_PE.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.E.no(i).mtrLbNm_PE.getValue().toString(), "Meter Type Master"});
                    isSuccess = false;
                }
                ZYPEZDItemValueSetter.setValue(glblMsg.E.no(i).mtrLbCd_PE, mtrLbCd);
            } else {
                glblMsg.E.no(i).mtrLbCd_PE.clear();
            }
            // 2018/11/17 QC#29147 Add Start
            // Price List Band
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).prcListBandDescTxt_PE)) {
                String prcListBandCd = getPrcListBand(glblMsg.E.no(i).prcListBandDescTxt_PE.getValue());
                if (!ZYPCommonFunc.hasValue(prcListBandCd)) {
                    glblMsg.E.no(i).prcListBandDescTxt_PE.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.E.no(i).prcListBandDescTxt_PE.getValue().toString(), "Price List Band Master"});
                    isSuccess = false;
                }
                ZYPEZDItemValueSetter.setValue(glblMsg.E.no(i).prcListBandCd_PE, prcListBandCd);
            } else {
                glblMsg.E.no(i).prcListBandCd_PE.clear();
            }
            // 2018/11/17 QC#29147 Add End
            // Service Item
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).mdseCd_PE)) {
                String rtrnVal = NMAL7010CommonLogic.getMdseNm(glblCmpyCd, glblMsg.E.no(i).mdseCd_PE, true);
                if (!ZYPCommonFunc.hasValue(rtrnVal)) {
                    isSuccess = false;
                }
            }

            // Volume
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).minCopyVolCnt_PE) && ZYPCommonFunc.hasValue(glblMsg.E.no(i).maxCopyVolCnt_PE)) {
                if (glblMsg.E.no(i).minCopyVolCnt_PE.getValue().compareTo(glblMsg.E.no(i).maxCopyVolCnt_PE.getValue()) > 0) {
                    glblMsg.E.no(i).minCopyVolCnt_PE.setErrorInfo(1, NMAM0043E, new String[] {"Min Vol", "Max Vol" });
                    isSuccess = false;
                }
            }

            // CPC
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).minCpcAmtRate_PE) && ZYPCommonFunc.hasValue(glblMsg.E.no(i).maxCpcAmtRate_PE)) {
                if (glblMsg.E.no(i).minCpcAmtRate_PE.getValue().compareTo(glblMsg.E.no(i).maxCpcAmtRate_PE.getValue()) > 0) {
                    glblMsg.E.no(i).minCpcAmtRate_PE.setErrorInfo(1, NMAM0043E, new String[] {"Min CPC", "Max CPC" });
                    isSuccess = false;
                }
            }

            // Term From
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).termFromMthAot_PE) && ZYPCommonFunc.hasValue(glblMsg.E.no(i).termThruMthAot_PE)) {
                if (glblMsg.E.no(i).termFromMthAot_PE.getValue().compareTo(glblMsg.E.no(i).termThruMthAot_PE.getValue()) > 0) {
                    glblMsg.E.no(i).termFromMthAot_PE.setErrorInfo(1, NMAM0043E, new String[] {"Min CPC", "Max CPC" });
                    isSuccess = false;
                }
            }

            //Meter Label exists check
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).mtrLbNm_PE)) {
                String mtrLbNm = getMtrLb(glblMsg.E.no(i).mtrLbNm_PE.getValue());
                if (!ZYPCommonFunc.hasValue(mtrLbNm)) {
                    glblMsg.E.no(i).mtrLbNm_PE.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.E.no(i).mtrLbNm_PE.getValue().toString(), "Meter Label"});
                    isSuccess = false;
                }
            }

            
            // Price Meter Package Billing Meter
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).prcMtrPkgPk_PE) && ZYPCommonFunc.hasValue(glblMsg.E.no(i).mtrLbCd_PE) && ZYPCommonFunc.hasValue(glblMsg.E.no(i).mdlId_PE)) {
                BigDecimal prcMtrPkgBllgMtPk = getPrcMtrPkgBllgMtr(glblMsg.E.no(i).prcMtrPkgPk_PE.getValue(), glblMsg.E.no(i).mtrLbCd_PE.getValue(), glblMsg.E.no(i).mdlId_PE.getValue());
                if (!ZYPCommonFunc.hasValue(prcMtrPkgBllgMtPk)) {
                    glblMsg.E.no(i).prcMtrPkgNm_PE.setErrorInfo(1, NMAM0163E, new String[] {"combined value", "Price Meter Package Billing Meter" });
                    glblMsg.E.no(i).mtrLbNm_PE.setErrorInfo(1, NMAM0163E, new String[] {"combined value", "Price Meter Package Billing Meter" });
                    glblMsg.E.no(i).mdlNm_PE.setErrorInfo(1, NMAM0163E, new String[] {"combined value", "Price Meter Package Billing Meter" });
                    isSuccess = false;
                } else {
                    ZYPEZDItemValueSetter.setValue(glblMsg.E.no(i).prcMtrPkgBllgMtrPk_PE, prcMtrPkgBllgMtPk);
                }
            }

            // Supply Plan
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).splyAgmtPlnPk_PE)) {
                // mod start 2016/09/21 QC#13252
                if (!NMAL7010Query.getInstance().getSplyAgmtPln(glblMsg.E.no(i).splyAgmtPlnPk_PE.getValue())) {
                    glblMsg.E.no(i).splyAgmtPlnPk_PE.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.E.no(i).splyAgmtPlnPk_PE.getValue().toString(), "Supply Plan"});
                    // mod end 2016/09/21 QC#13252
                    isSuccess = false;
                }
            }

            // Effective Date
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).effFromDt_PE)) {
                if (ZYPDateUtil.compare(glblMsg.E.no(i).effFromDt_PE.getValue(), toHighValDate(glblMsg.E.no(i).effThruDt_PE.getValue())) > 0) {
                    glblMsg.E.no(i).effFromDt_PE.setErrorInfo(1, NMAM0043E, new String[] {"Effective Date From", "Effective Date To" });
                    isSuccess = false;
                }
            }

            // Record Duplicate Check.
            // 2015/12/22 CSA-QC#1125 Mod Start
            if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.E.no(i).delFlg_PE.getValue())) {

                String keyInfo = setKeyInfoPrcListSplyPgm(glblMsg.E.no(i));

                if (chkPriceMap.containsKey(keyInfo)) {
                    if (!recordDuplicateCheck(chkPriceMap, keyInfo, glblMsg.E.no(i).effFromDt_PE.getValue(), glblMsg.E.no(i).effThruDt_PE.getValue())) {
                        glblMsg.E.no(i).xxChkBox_PE.setErrorInfo(1, NMAM0072E, new String[] {"Price List Supply Program"});
                        isSuccess = false;
                    }
                }

                addChkPriceMap(chkPriceMap, keyInfo, glblMsg.E.no(i).effFromDt_PE.getValue(), glblMsg.E.no(i).effThruDt_PE.getValue());
                if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).prcListSplyPgmPk_PE)) {
                    keyListRec.add(glblMsg.E.no(i).prcListSplyPgmPk_PE.getValue());
                }
            }
            // 2015/12/22 CSA-QC#1125 Mod End

            // for error handling
            if (!isSuccess && firstErrFlg) {
                NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
                firstErrFlg = false;
            }

            // 2016/07/19 CSA-QC#11933 Add Start
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).prcListSplyPgmPk_PE)) {
                splyPgmPkList.add(glblMsg.E.no(i).prcListSplyPgmPk_PE.getValue());
            }
            // 2016/07/19 CSA-QC#11933 Add end
            // 2019/11/26 QC#54214 Mod Start
            // mod start 2016/09/28 CSA QC#13263
            //if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).totBaseAmt_PE) || ZYPCommonFunc.hasValue(glblMsg.E.no(i).splyBaseAmt_PE)) {
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).totBaseAmt_PE) && ZYPCommonFunc.hasValue(glblMsg.E.no(i).splyBaseAmt_PE)) {
            // 2019/11/26 QC#54214 Mod End
                if (glblMsg.E.no(i).splyBaseAmt_PE.getValue().compareTo(glblMsg.E.no(i).totBaseAmt_PE.getValue()) > 0) {
                    glblMsg.E.no(i).splyBaseAmt_PE.setErrorInfo(1, NMAM0043E, new String[] {"Supply Base Amount", "Total Base Amount" });
                    isSuccess = false;
                }
            }
            // mod end 2016/09/28 CSA QC#13263
            // mod start 2016/09/29 CSA QC#13270
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).minCopyVolCnt_PE)) {
                if (glblMsg.E.no(i).minCopyVolCnt_PE.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.E.no(i).minCopyVolCnt_PE.setErrorInfo(1, NMAM0847E, new String[] {"Min Vol" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).maxCopyVolCnt_PE)) {
                if (glblMsg.E.no(i).maxCopyVolCnt_PE.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.E.no(i).maxCopyVolCnt_PE.setErrorInfo(1, NMAM0847E, new String[] {"Max Vol" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).totBaseAmt_PE)) {
                if (glblMsg.E.no(i).totBaseAmt_PE.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.E.no(i).totBaseAmt_PE.setErrorInfo(1, NMAM0847E, new String[] {"Total Base Amount" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).splyBaseAmt_PE)) {
                if (glblMsg.E.no(i).splyBaseAmt_PE.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.E.no(i).splyBaseAmt_PE.setErrorInfo(1, NMAM0847E, new String[] {"Supply Base Amount" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).cpcAmtRate_PE)) {
                if (glblMsg.E.no(i).cpcAmtRate_PE.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.E.no(i).cpcAmtRate_PE.setErrorInfo(1, NMAM0847E, new String[] {"Cost Per Copy (Overage)" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).minCpcAmtRate_PE)) {
                if (glblMsg.E.no(i).minCpcAmtRate_PE.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.E.no(i).minCpcAmtRate_PE.setErrorInfo(1, NMAM0847E, new String[] {"Min CPC" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.E.no(i).maxCpcAmtRate_PE)) {
                if (glblMsg.E.no(i).maxCpcAmtRate_PE.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.E.no(i).maxCpcAmtRate_PE.setErrorInfo(1, NMAM0847E, new String[] {"Max CPC" });
                    isSuccess = false;
                }
            }
            // mod end 2016/09/29 CSA QC#13270
        }
        // 2015/12/22 CSA-QC#1125 Add Start
        chkPriceMap.clear();
        // 2016/07/19 CSA-QC#11933 Mod Start
//        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
//            S21SsmEZDResult result = NMAL7010Query.getInstance().getPrcListSplyPgmForDuplicateCheck(bizMsg);
//
//            StringBuilder keyInfo = new StringBuilder();
//            if (!result.isCodeNotFound()) {
//                List<PRC_LIST_SPLY_PGMTMsg> prcListSplyPgm = (List) result.getResultObject();
//                for (int i = 0; i < prcListSplyPgm.size(); i++) {
//                    if (!keyListRec.contains(prcListSplyPgm.get(i).prcListSplyPgmPk.getValue())) {
//                        keyInfo.setLength(0);
//                        if (ZYPCommonFunc.hasValue(prcListSplyPgm.get(i).mdlId)) {
//                            keyInfo = keyInfo.append(toStr(prcListSplyPgm.get(i).mdlId.getValue())).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSplyPgm.get(i).prcMtrPkgPk)) {
//                            keyInfo = keyInfo.append(toStr(prcListSplyPgm.get(i).prcMtrPkgPk.getValue())).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSplyPgm.get(i).prcSvcPlnTpCd)) {
//                            keyInfo = keyInfo.append(prcListSplyPgm.get(i).prcSvcPlnTpCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSplyPgm.get(i).prcSvcContrTpCd)) {
//                            keyInfo = keyInfo.append(prcListSplyPgm.get(i).prcSvcContrTpCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSplyPgm.get(i).bllgMtrLbCd)) {
//                            keyInfo = keyInfo.append(prcListSplyPgm.get(i).bllgMtrLbCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSplyPgm.get(i).prcListBandCd)) {
//                            keyInfo = keyInfo.append(prcListSplyPgm.get(i).prcListBandCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSplyPgm.get(i).termFromMthAot)) {
//                            keyInfo = keyInfo.append(toStr(prcListSplyPgm.get(i).termFromMthAot.getValue())).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListSplyPgm.get(i).termThruMthAot)) {
//                            keyInfo = keyInfo.append(toStr(prcListSplyPgm.get(i).termThruMthAot.getValue()));
//                        }
//
//                        addChkPriceMap(chkPriceMap, keyInfo.toString(), prcListSplyPgm.get(i).effFromDt.getValue(), prcListSplyPgm.get(i).effThruDt.getValue());
//
//                    }
//                }
//            }
//        }
//        if (chkPriceMap.size() > 0) {
//            for (int i = 0; i < glblMsg.E.getValidCount(); i++) {
//                if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.E.no(i).delFlg_PE.getValue())) {
//
//                    String keyInfo = setKeyInfoPrcListSplyPgm(glblMsg.E.no(i));
//
//                    if (chkPriceMap.containsKey(keyInfo)) {
//                        if (!recordDuplicateCheck(chkPriceMap, keyInfo, glblMsg.E.no(i).effFromDt_PE.getValue(), glblMsg.E.no(i).effThruDt_PE.getValue())) {
//                            glblMsg.E.no(i).xxChkBox_PE.setErrorInfo(1, NMAM0072E, new String[] {"Price List Supply Program"});
//                            isSuccess = false;
//
//                            // for error handling
//                            if (firstErrFlg) {
//                                NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
//                                firstErrFlg = false;
//                            }
//                        }
//                    }
//                }
//            }
//        }

        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
            for (int i = 0; i < glblMsg.E.getValidCount(); i++){
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.E.no(i).delFlg_PE.getValue())
                        || !MODE_MODIFY.equals(glblMsg.E.no(i).xxModeCd_E1.getValue())) {
                    continue;
                }

                S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcListSplyPgmForDuplicateCheck(glblMsg.E.no(i), bizMsg.prcCatgCd_BK.getValue());

                if (!ssmResult.isCodeNormal()) {
                    continue;
                }

                // SSM Result
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                for (Map<String, Object> resultMap : resultList) {
                    if (!splyPgmPkList.contains(resultMap.get("PRC_LIST_SPLY_PGM_PK"))) {
                        glblMsg.E.no(i).xxChkBox_PE.setErrorInfo(1, NMAM0072E, new String[] {"Price List Supply Program"});
                        isSuccess = false;

                        // for error handling
                        if (firstErrFlg) {
                            NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
                            firstErrFlg = false;
                        }
                    }
                }
            }
        }
        // 2016/07/19 CSA-QC#11933 Mod end
        // 2015/12/22 CSA-QC#1125 Add End

        return isSuccess;
    }

    /**
     * checkPrcListLeaseRate.
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkPrcListLeaseRate(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, String glblCmpyCd) {

        boolean isSuccess = true;
        boolean firstErrFlg = true;
        List<BigDecimal> keyListRec = new ArrayList<BigDecimal>(glblMsg.F.getValidCount());
        HashMap<String, ArrayList<String[]>> chkPriceMap = new HashMap<String, ArrayList<String[]>>();

        if (bizMsg.F.getValidCount() <= 0) {
            return isSuccess;
        }

        // 2016/07/19 CSA-QC#11933 Add Start
        List<BigDecimal> leaseRatePkList = new ArrayList<BigDecimal>();
        // 2016/07/19 CSA-QC#11933 Add end

        for (int i = 0; i < glblMsg.F.getValidCount(); i++) {
            // Mandatory
            if (!ZYPCommonFunc.hasValue(glblMsg.F.no(i).leaseRate_PF)) {
                glblMsg.F.no(i).leaseRate_PF.setErrorInfo(1, ZZM9000E, new String[] {"Lease Rate"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.F.no(i).effFromDt_PF)) {
                glblMsg.F.no(i).effFromDt_PF.setErrorInfo(1, ZZM9000E, new String[] {"Effective Date From"});
                isSuccess = false;
            }

            // Service Model
            if (ZYPCommonFunc.hasValue(glblMsg.F.no(i).mdlNm_PF)) {
                MDL_NMTMsg mdlInTMsg = new MDL_NMTMsg();
                mdlInTMsg.setSQLID("001");
                mdlInTMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
                mdlInTMsg.setConditionValue("t_MdlNm01", glblMsg.F.no(i).mdlNm_PF.getValue());
                MDL_NMTMsgArray rsltTMsg = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlInTMsg);
                if (rsltTMsg == null || rsltTMsg.length() == 0) {
                    glblMsg.F.no(i).mdlNm_PF.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.F.no(i).mdlNm_PF.getValue().toString(), "Model Name"});
                    isSuccess = false;
                }
            }

            // Lease Account#
            if (ZYPCommonFunc.hasValue(glblMsg.F.no(i).dsAcctNm_PF)) {
                if (!isExistTable("SELL_TO_CUST", glblMsg.F.no(i).dsAcctNm_PF.getValue())) {
                    glblMsg.F.no(i).dsAcctNm_PF.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.F.no(i).dsAcctNm_PF.getValue().toString(), "Account Customer"});
                    isSuccess = false;
                }
            }

            // Total Financed
            if (ZYPCommonFunc.hasValue(glblMsg.F.no(i).minTotFinAmt_PF) && ZYPCommonFunc.hasValue(glblMsg.F.no(i).maxTotFinAmt_PF)) {
                if (glblMsg.F.no(i).minTotFinAmt_PF.getValue().compareTo(glblMsg.F.no(i).maxTotFinAmt_PF.getValue()) > 0) {
                    glblMsg.F.no(i).minTotFinAmt_PF.setErrorInfo(1, NMAM0043E, new String[] {"Total Financed Min", "Total Financed Max" });
                    isSuccess = false;
                }
            }

            // Term From
            if (ZYPCommonFunc.hasValue(glblMsg.F.no(i).termFromMthAot_PF) && ZYPCommonFunc.hasValue(glblMsg.F.no(i).termThruMthAot_PF)) {
                if (glblMsg.F.no(i).termFromMthAot_PF.getValue().compareTo(glblMsg.F.no(i).termThruMthAot_PF.getValue()) > 0) {
                    glblMsg.F.no(i).termFromMthAot_PF.setErrorInfo(1, NMAM0043E, new String[] {"Qualifiying Term From", "Qualifiying Term To" });
                    isSuccess = false;
                }
            }

            // Effective Date
            if (ZYPCommonFunc.hasValue(glblMsg.F.no(i).effFromDt_PF)) {
                if (ZYPDateUtil.compare(glblMsg.F.no(i).effFromDt_PF.getValue(), toHighValDate(glblMsg.F.no(i).effThruDt_PF.getValue())) > 0) {
                    glblMsg.F.no(i).effFromDt_PF.setErrorInfo(1, NMAM0043E, new String[] {"Effective Date From", "Effective Date To" });
                    isSuccess = false;
                }
            }

            // Record Duplicate Check.
            // 2015/12/22 CSA-QC#1125 Mod Start
            if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.F.no(i).delFlg_PF.getValue())) {

                String keyInfo = setKeyInfoPrcListLeaseRate(glblMsg.F.no(i));

                if (chkPriceMap.containsKey(keyInfo)) {
                    if (!recordDuplicateCheck(chkPriceMap, keyInfo, glblMsg.F.no(i).effFromDt_PF.getValue(), glblMsg.F.no(i).effThruDt_PF.getValue())) {
                        glblMsg.F.no(i).xxChkBox_PF.setErrorInfo(1, NMAM0072E, new String[] {"Price List Lease Rates"});
                        isSuccess = false;
                    }
                }

                addChkPriceMap(chkPriceMap, keyInfo, glblMsg.F.no(i).effFromDt_PF.getValue(), glblMsg.F.no(i).effThruDt_PF.getValue());
                if (ZYPCommonFunc.hasValue(glblMsg.F.no(i).prcListLeaseRatePk_PF)) {
                    keyListRec.add(glblMsg.F.no(i).prcListLeaseRatePk_PF.getValue());
                }
            }
            // 2015/12/22 CSA-QC#1125 Mod End

            // for error handling
            if (!isSuccess && firstErrFlg) {
                NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
                firstErrFlg = false;
            }

            // 2016/07/19 CSA-QC#11933 Add Start
            if (ZYPCommonFunc.hasValue(glblMsg.F.no(i).prcListLeaseRatePk_PF)) {
                leaseRatePkList.add(glblMsg.F.no(i).prcListLeaseRatePk_PF.getValue());
            }
            // 2016/07/19 CSA-QC#11933 Add end
            // mod start 2016/09/29 CSA QC#13270
            if (ZYPCommonFunc.hasValue(glblMsg.F.no(i).minTotFinAmt_PF)) {
                if (glblMsg.F.no(i).minTotFinAmt_PF.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.F.no(i).minTotFinAmt_PF.setErrorInfo(1, NMAM0847E, new String[] {"Total Financed Min" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.F.no(i).maxTotFinAmt_PF)) {
                if (glblMsg.F.no(i).maxTotFinAmt_PF.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.F.no(i).maxTotFinAmt_PF.setErrorInfo(1, NMAM0847E, new String[] {"Total Financed Max" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.F.no(i).leaseRate_PF)) {
                if (glblMsg.F.no(i).leaseRate_PF.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.F.no(i).leaseRate_PF.setErrorInfo(1, NMAM0847E, new String[] {"Lease Rate" });
                    isSuccess = false;
                }
            }
            // mod end 2016/09/29 CSA QC#13270
        }
        // 2015/12/22 CSA-QC#1125 Add Start
        chkPriceMap.clear();
        // 2015/12/22 CSA-QC#1125 Mod End
//        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
//            S21SsmEZDResult result = NMAL7010Query.getInstance().getPrcListLeaseRateForDuplicateCheck(bizMsg);
//
//            StringBuilder keyInfo = new StringBuilder();
//            if (!result.isCodeNotFound()) {
//                List<PRC_LIST_LEASE_RATETMsg> prcListLeaseRate = (List) result.getResultObject();
//                for (int i = 0; i < prcListLeaseRate.size(); i++) {
//                    if (!keyListRec.contains(prcListLeaseRate.get(i).prcListLeaseRatePk.getValue())) {
//                        keyInfo.setLength(0);
//                        if (ZYPCommonFunc.hasValue(prcListLeaseRate.get(i).prcLeaseCmpyAbbrNm)) {
//                            keyInfo = keyInfo.append(prcListLeaseRate.get(i).prcLeaseCmpyAbbrNm.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListLeaseRate.get(i).dsAcctNm)) {
//                            keyInfo = keyInfo.append(prcListLeaseRate.get(i).dsAcctNm.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListLeaseRate.get(i).prcPgmTpCd)) {
//                            keyInfo = keyInfo.append(prcListLeaseRate.get(i).prcPgmTpCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListLeaseRate.get(i).prcEquipTpCd)) {
//                            keyInfo = keyInfo.append(prcListLeaseRate.get(i).prcEquipTpCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListLeaseRate.get(i).mdlId)) {
//                            keyInfo = keyInfo.append(toStr(prcListLeaseRate.get(i).mdlId.getValue())).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListLeaseRate.get(i).termFromMthAot)) {
//                            keyInfo = keyInfo.append(toStr(prcListLeaseRate.get(i).termFromMthAot.getValue())).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListLeaseRate.get(i).termThruMthAot)) {
//                            keyInfo = keyInfo.append(toStr(prcListLeaseRate.get(i).termThruMthAot.getValue()));
//                        }
//
//                        addChkPriceMap(chkPriceMap, keyInfo.toString(), prcListLeaseRate.get(i).effFromDt.getValue(), prcListLeaseRate.get(i).effThruDt.getValue());
//
//                    }
//                }
//            }
//        }
//        if (chkPriceMap.size() > 0) {
//            for (int i = 0; i < glblMsg.F.getValidCount(); i++) {
//                if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.F.no(i).delFlg_PF.getValue())) {
//
//                    String keyInfo = setKeyInfoPrcListLeaseRate(glblMsg.F.no(i));
//
//                    if (chkPriceMap.containsKey(keyInfo)) {
//                        if (!recordDuplicateCheck(chkPriceMap, keyInfo, glblMsg.F.no(i).effFromDt_PF.getValue(), glblMsg.F.no(i).effThruDt_PF.getValue())) {
//                            glblMsg.F.no(i).xxChkBox_PF.setErrorInfo(1, NMAM0072E, new String[] {"Price List Lease Rates"});
//                            isSuccess = false;
//
//                            // for error handling
//                            if (firstErrFlg) {
//                                NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
//                                firstErrFlg = false;
//                            }
//                        }
//                    }
//                }
//            }
//        }

        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
            for (int i = 0; i < glblMsg.F.getValidCount(); i++){
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.F.no(i).delFlg_PF.getValue())
                        || !MODE_MODIFY.equals(glblMsg.F.no(i).xxModeCd_F1.getValue())) {
                    continue;
                }

                S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcListLeaseRateForDuplicateCheck(glblMsg.F.no(i), bizMsg.prcCatgCd_BK.getValue());

                if (!ssmResult.isCodeNormal()) {
                    continue;
                }

                // SSM Result
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                for (Map<String, Object> resultMap : resultList) {
                    if (!leaseRatePkList.contains(resultMap.get("PRC_LIST_LEASE_RATE_PK"))) {
                        glblMsg.F.no(i).xxChkBox_PF.setErrorInfo(1, NMAM0072E, new String[] {"Price List Lease Rates"});
                        isSuccess = false;

                        // for error handling
                        if (firstErrFlg) {
                            NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
                            firstErrFlg = false;
                        }
                    }
                }
            }
        }
        // 2016/07/19 CSA-QC#11933 Mod end
        // 2015/12/22 CSA-QC#1125 Add End

        return isSuccess;
    }

    /**
     * checkPrcListLeaseRtrnFee.
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkPrcListLeaseRtrnFee(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, String glblCmpyCd) {
        boolean isSuccess = true;
        boolean firstErrFlg = true;
        List<BigDecimal> keyListRec = new ArrayList<BigDecimal>(glblMsg.G.getValidCount());
        HashMap<String, ArrayList<String[]>> chkPriceMap = new HashMap<String, ArrayList<String[]>>();

        if (bizMsg.G.getValidCount() <= 0) {
            return isSuccess;
        }

        // 2016/07/19 CSA-QC#11933 Add Start
        List<BigDecimal> leaseRtrnFeePkList = new ArrayList<BigDecimal>();
        // 2016/07/19 CSA-QC#11933 Add end
        for (int i = 0; i < glblMsg.G.getValidCount(); i++) {
            // Mandatory
            if (!ZYPCommonFunc.hasValue(glblMsg.G.no(i).rtrnFeeAmt_PG)) {
                glblMsg.G.no(i).rtrnFeeAmt_PG.setErrorInfo(1, ZZM9000E, new String[] {"Return Fee"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.G.no(i).effFromDt_PG)) {
                glblMsg.G.no(i).effFromDt_PG.setErrorInfo(1, ZZM9000E, new String[] {"Effective Date From"});
                isSuccess = false;
            }

            // Effective Date
            if (ZYPCommonFunc.hasValue(glblMsg.G.no(i).effFromDt_PG)) {
                if (ZYPDateUtil.compare(glblMsg.G.no(i).effFromDt_PG.getValue(), toHighValDate(glblMsg.G.no(i).effThruDt_PG.getValue())) > 0) {
                    glblMsg.G.no(i).effFromDt_PG.setErrorInfo(1, NMAM0043E, new String[] {"Effective Date From", "Effective Date To" });
                    isSuccess = false;
                }
            }

            // Record Duplicate Check.
            // 2015/12/22 CSA-QC#1125 Mod Start
            if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.G.no(i).delFlg_PG.getValue())) {

                String keyInfo = setKeyInfoPrcListLeaseRtrn(glblMsg.G.no(i));

                if (chkPriceMap.containsKey(keyInfo)) {
                    if (!recordDuplicateCheck(chkPriceMap, keyInfo, glblMsg.G.no(i).effFromDt_PG.getValue(), glblMsg.G.no(i).effThruDt_PG.getValue())) {
                        glblMsg.G.no(i).xxChkBox_PG.setErrorInfo(1, NMAM0072E, new String[] {"Price List Lease Return"});
                        isSuccess = false;
                    }
                }

                addChkPriceMap(chkPriceMap, keyInfo, glblMsg.G.no(i).effFromDt_PG.getValue(), glblMsg.G.no(i).effThruDt_PG.getValue());
                if (ZYPCommonFunc.hasValue(glblMsg.G.no(i).prcListLeaseRtrnPk_PG)) {
                    keyListRec.add(glblMsg.G.no(i).prcListLeaseRtrnPk_PG.getValue());
                }
            }
            // 2015/12/22 CSA-QC#1125 Mod End

            // for error handling
            if (!isSuccess && firstErrFlg) {
                NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
                firstErrFlg = false;
            }

            // 2016/07/19 CSA-QC#11933 Add Start
            if (ZYPCommonFunc.hasValue(glblMsg.G.no(i).prcListLeaseRtrnPk_PG)) {
                leaseRtrnFeePkList.add(glblMsg.G.no(i).prcListLeaseRtrnPk_PG.getValue());
            }
            // 2016/07/19 CSA-QC#11933 Add end
            // mod start 2016/09/29 CSA QC#13270
            if (ZYPCommonFunc.hasValue(glblMsg.G.no(i).dstMileAmt_PG)) {
                if (glblMsg.G.no(i).dstMileAmt_PG.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.G.no(i).dstMileAmt_PG.setErrorInfo(1, NMAM0847E, new String[] {"Distance (Miles)" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.G.no(i).rtrnFeeAmt_PG)) {
                if (glblMsg.G.no(i).rtrnFeeAmt_PG.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.G.no(i).rtrnFeeAmt_PG.setErrorInfo(1, NMAM0847E, new String[] {"Return Fee" });
                    isSuccess = false;
                }
            }
            // mod end 2016/09/29 CSA QC#13270
        }
        // 2015/12/22 CSA-QC#1125 Add Start
        chkPriceMap.clear();
        // 2016/07/19 CSA-QC#11933 Add Start
//        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
//            S21SsmEZDResult result = NMAL7010Query.getInstance().getPrcListLeaseRtrnForDuplicateCheck(bizMsg);
//
//            StringBuilder keyInfo = new StringBuilder();
//            if (!result.isCodeNotFound()) {
//                List<PRC_LIST_LEASE_RTRNTMsg> prcListLeaseRtrn = (List) result.getResultObject();
//                for (int i = 0; i < prcListLeaseRtrn.size(); i++) {
//                    if (!keyListRec.contains(prcListLeaseRtrn.get(i).prcListLeaseRtrnPk.getValue())) {
//                        keyInfo.setLength(0);
//                        if (ZYPCommonFunc.hasValue(prcListLeaseRtrn.get(i).prcLeaseCmpyAbbrNm)) {
//                            keyInfo = keyInfo.append(prcListLeaseRtrn.get(i).prcLeaseCmpyAbbrNm.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListLeaseRtrn.get(i).svcSegCd)) {
//                            keyInfo = keyInfo.append(prcListLeaseRtrn.get(i).svcSegCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListLeaseRtrn.get(i).prcInOutRgCd)) {
//                            keyInfo = keyInfo.append(prcListLeaseRtrn.get(i).prcInOutRgCd.getValue()).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListLeaseRtrn.get(i).dstMileAmt)) {
//                            keyInfo = keyInfo.append(toStr(prcListLeaseRtrn.get(i).dstMileAmt.getValue()));
//                        }
//
//                        addChkPriceMap(chkPriceMap, keyInfo.toString(), prcListLeaseRtrn.get(i).effFromDt.getValue(), prcListLeaseRtrn.get(i).effThruDt.getValue());
//
//                    }
//                }
//            }
//        }
//        if (chkPriceMap.size() > 0) {
//            for (int i = 0; i < glblMsg.G.getValidCount(); i++) {
//                if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.G.no(i).delFlg_PG.getValue())) {
//
//                    String keyInfo = setKeyInfoPrcListLeaseRtrn(glblMsg.G.no(i));
//
//                    if (chkPriceMap.containsKey(keyInfo)) {
//                        if (!recordDuplicateCheck(chkPriceMap, keyInfo, glblMsg.G.no(i).effFromDt_PG.getValue(), glblMsg.G.no(i).effThruDt_PG.getValue())) {
//                            glblMsg.G.no(i).xxChkBox_PG.setErrorInfo(1, NMAM0072E, new String[] {"Price List Lease Return"});
//                            isSuccess = false;
//
//                            // for error handling
//                            if (firstErrFlg) {
//                                NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
//                                firstErrFlg = false;
//                            }
//                        }
//                    }
//                }
//            }
//        }

        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
            for (int i = 0; i < glblMsg.G.getValidCount(); i++){
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.G.no(i).delFlg_PG.getValue())
                        || !MODE_MODIFY.equals(glblMsg.G.no(i).xxModeCd_G1.getValue())) {
                    continue;
                }

                S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcListLeaseRtrnForDuplicateCheck(glblMsg.G.no(i), bizMsg.prcCatgCd_BK.getValue());

                if (!ssmResult.isCodeNormal()) {
                    continue;
                }

                // SSM Result
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                for (Map<String, Object> resultMap : resultList) {
                    if (!leaseRtrnFeePkList.contains(resultMap.get("PRC_LIST_LEASE_RTRN_PK"))) {
                        glblMsg.G.no(i).xxChkBox_PG.setErrorInfo(1, NMAM0072E, new String[] {"Price List Lease Return"});
                        isSuccess = false;

                        // for error handling
                        if (firstErrFlg) {
                            NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
                            firstErrFlg = false;
                        }
                    }
                }
            }
        }
        // 2016/07/19 CSA-QC#11933 Mod end
        // 2015/12/22 CSA-QC#1125 Add End

        return isSuccess;
    }

    /**
     * checkPrcListTradeIn.
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     * @param glblCmpyCd String
     * @return boolean
     */ 
    public static boolean checkPrcListTradeIn(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, String glblCmpyCd) {

        boolean isSuccess = true;
        boolean firstErrFlg = true;
        List<BigDecimal> keyListRec = new ArrayList<BigDecimal>(glblMsg.H.getValidCount());
        HashMap<String, ArrayList<String[]>> chkPriceMap = new HashMap<String, ArrayList<String[]>>();


        if (bizMsg.H.getValidCount() <= 0) {
            return isSuccess;
        }

        // 2016/07/19 CSA-QC#11933 Add Start
        List<BigDecimal> tradeInPkList = new ArrayList<BigDecimal>();
        // 2016/07/19 CSA-QC#11933 Add end
        for (int i = 0; i < glblMsg.H.getValidCount(); i++) {
            // Mandatory
            if (!ZYPCommonFunc.hasValue(glblMsg.H.no(i).tiAmt_PH)) {
                glblMsg.H.no(i).tiAmt_PH.setErrorInfo(1, ZZM9000E, new String[] {"Trade In Value"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.H.no(i).effFromDt_PH)) {
                glblMsg.H.no(i).effFromDt_PH.setErrorInfo(1, ZZM9000E, new String[] {"Effective Date From"});
                isSuccess = false;
            }

            // Service Model
            if (ZYPCommonFunc.hasValue(glblMsg.H.no(i).mdlNm_PH)) {
                MDL_NMTMsg mdlInTMsg = new MDL_NMTMsg();
                mdlInTMsg.setSQLID("001");
                mdlInTMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
                mdlInTMsg.setConditionValue("t_MdlNm01", glblMsg.H.no(i).mdlNm_PH.getValue());
                MDL_NMTMsgArray rsltTMsg = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlInTMsg);
                if (rsltTMsg == null || rsltTMsg.length() == 0) {
                    glblMsg.H.no(i).mdlNm_PH.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.H.no(i).mdlNm_PH.getValue().toString(), "Model Name"});
                    isSuccess = false;
                // 2017/09/27 S21_NA#20977 add start
                } else {
                    ZYPEZDItemValueSetter.setValue(glblMsg.H.no(i).mdlId_PH, rsltTMsg.no(0).t_MdlId);
                }
                // 2017/09/27 S21_NA#20977 add end
            }

            // Meter
            if (ZYPCommonFunc.hasValue(glblMsg.H.no(i).fromMtrCopyVolCnt_PH) && ZYPCommonFunc.hasValue(glblMsg.H.no(i).thruMtrCopyVolCnt_PH)) {
                if (glblMsg.H.no(i).fromMtrCopyVolCnt_PH.getValue().compareTo(glblMsg.H.no(i).thruMtrCopyVolCnt_PH.getValue()) > 0) {
                    glblMsg.H.no(i).fromMtrCopyVolCnt_PH.setErrorInfo(1, NMAM0043E, new String[] {"Meter From", "Meter To" });
                    isSuccess = false;
                }
            }

            // Effective Date
            if (ZYPCommonFunc.hasValue(glblMsg.H.no(i).effFromDt_PH)) {
                if (ZYPDateUtil.compare(glblMsg.H.no(i).effFromDt_PH.getValue(), toHighValDate(glblMsg.H.no(i).effThruDt_PH.getValue())) > 0) {
                    glblMsg.H.no(i).effFromDt_PH.setErrorInfo(1, NMAM0043E, new String[] {"Effective Date From", "Effective Date To" });
                    isSuccess = false;
                }
            }

            // Record Duplicate Check.
            // 2015/12/22 CSA-QC#1125 Mod Start
            if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.H.no(i).delFlg_PH.getValue())) {

                String keyInfo = setKeyInfoPrcListTiVal(glblMsg.H.no(i));

                if (chkPriceMap.containsKey(keyInfo)) {
                    if (!recordDuplicateCheck(chkPriceMap, keyInfo, glblMsg.H.no(i).effFromDt_PH.getValue(), glblMsg.H.no(i).effThruDt_PH.getValue())) {
                        glblMsg.H.no(i).xxChkBox_PH.setErrorInfo(1, NMAM0072E, new String[] {"Price List Trade Ins"});
                        isSuccess = false;
                    }
                }

                addChkPriceMap(chkPriceMap, keyInfo, glblMsg.H.no(i).effFromDt_PH.getValue(), glblMsg.H.no(i).effThruDt_PH.getValue());
                if (ZYPCommonFunc.hasValue(glblMsg.H.no(i).prcListTiValPk_PH)) {
                    keyListRec.add(glblMsg.H.no(i).prcListTiValPk_PH.getValue());
                }
            }
            // 2015/12/22 CSA-QC#1125 Mod End

            // for error handling
            if (!isSuccess && firstErrFlg) {
                NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
                firstErrFlg = false;
            }

            // 2016/07/19 CSA-QC#11933 Add Start
            if (ZYPCommonFunc.hasValue(glblMsg.H.no(i).prcListTiValPk_PH)) {
                tradeInPkList.add(glblMsg.H.no(i).prcListTiValPk_PH.getValue());
            }
            // 2016/07/19 CSA-QC#11933 Add end
            // mod start 2016/09/29 CSA QC#13270
            if (ZYPCommonFunc.hasValue(glblMsg.H.no(i).tiAmt_PH)) {
                if (glblMsg.H.no(i).tiAmt_PH.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.H.no(i).tiAmt_PH.setErrorInfo(1, NMAM0847E, new String[] {"Trade In Value" });
                    isSuccess = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.H.no(i).fromMtrCopyVolCnt_PH)) {
                if (glblMsg.H.no(i).fromMtrCopyVolCnt_PH.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.H.no(i).fromMtrCopyVolCnt_PH.setErrorInfo(1, NMAM0847E, new String[] {"Meter From" });
                    isSuccess = false;
                }
            }if (ZYPCommonFunc.hasValue(glblMsg.H.no(i).thruMtrCopyVolCnt_PH)) {
                if (glblMsg.H.no(i).thruMtrCopyVolCnt_PH.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    glblMsg.H.no(i).thruMtrCopyVolCnt_PH.setErrorInfo(1, NMAM0847E, new String[] {"Meter To" });
                    isSuccess = false;
                }
            }
            // mod end 2016/09/29 CSA QC#13270
        }
        // 2015/12/22 CSA-QC#1125 Add Start
        chkPriceMap.clear();
        // 2016/07/19 CSA-QC#11933 Mod Start
//        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
//            S21SsmEZDResult result = NMAL7010Query.getInstance().getPrcListTiValForDuplicateCheck(bizMsg);
//
//            StringBuilder keyInfo = new StringBuilder();
//            if (!result.isCodeNotFound()) {
//                List<PRC_LIST_TI_VALTMsg> prcListTiVal = (List) result.getResultObject();
//                for (int i = 0; i < prcListTiVal.size(); i++) {
//                    if (!keyListRec.contains(prcListTiVal.get(i).prcListTiValPk.getValue())) {
//                        keyInfo.setLength(0);
//                        if (ZYPCommonFunc.hasValue(prcListTiVal.get(i).mdlId)) {
//                            keyInfo = keyInfo.append(toStr(prcListTiVal.get(i).mdlId.getValue())).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListTiVal.get(i).fromMtrCopyVolCnt)) {
//                            keyInfo = keyInfo.append(toStr(prcListTiVal.get(i).fromMtrCopyVolCnt.getValue())).append(COMMA);
//                        }
//                        if (ZYPCommonFunc.hasValue(prcListTiVal.get(i).thruMtrCopyVolCnt)) {
//                            keyInfo = keyInfo.append(toStr(prcListTiVal.get(i).thruMtrCopyVolCnt.getValue()));
//                        }
//
//                        addChkPriceMap(chkPriceMap, keyInfo.toString(), prcListTiVal.get(i).effFromDt.getValue(), prcListTiVal.get(i).effThruDt.getValue());
//
//                    }
//                }
//            }
//        }
//        if (chkPriceMap.size() > 0) {
//            for (int i = 0; i < glblMsg.H.getValidCount(); i++) {
//                if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.H.no(i).delFlg_PH.getValue())) {
//
//                    String keyInfo = setKeyInfoPrcListTiVal(glblMsg.H.no(i));
//
//                    if (chkPriceMap.containsKey(keyInfo)) {
//                        if (!recordDuplicateCheck(chkPriceMap, keyInfo, glblMsg.H.no(i).effFromDt_PH.getValue(), glblMsg.H.no(i).effThruDt_PH.getValue())) {
//                            glblMsg.H.no(i).xxChkBox_PH.setErrorInfo(1, NMAM0072E, new String[] {"Price List Trade Ins"});
//                            isSuccess = false;
//
//                            // for error handling
//                            if (firstErrFlg) {
//                                NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
//                                firstErrFlg = false;
//                            }
//                        }
//                    }
//                }
//            }
//        }

        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
            for (int i = 0; i < glblMsg.H.getValidCount(); i++){
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.H.no(i).delFlg_PH.getValue())
                        || !MODE_MODIFY.equals(glblMsg.H.no(i).xxModeCd_H1.getValue())) {
                    continue;
                }

                S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcListTiValForDuplicateCheck(glblMsg.H.no(i), bizMsg.prcCatgCd_BK.getValue());

                if (!ssmResult.isCodeNormal()) {
                    continue;
                }

                // SSM Result
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                for (Map<String, Object> resultMap : resultList) {
                    if (!tradeInPkList.contains(resultMap.get("PRC_LIST_TI_VAL_PK"))) {
                        glblMsg.H.no(i).xxChkBox_PH.setErrorInfo(1, NMAM0072E, new String[] {"Price List Trade Ins"});
                        isSuccess = false;

                        // for error handling
                        if (firstErrFlg) {
                            NMAL7010CommonLogic.setErrorPage(bizMsg, glblMsg, i);
                            firstErrFlg = false;
                        }
                    }
                }
            }
        }
        // 2016/07/19 CSA-QC#11933 Mod end
        // 2015/12/22 CSA-QC#1125 Add End

        return isSuccess;
    }

    /**
     * checkPrcListQtyDisc.
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkPrcListQtyDisc(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, String glblCmpyCd) {

        boolean isSuccess = true;
        List<BigDecimal> keyListRec = new ArrayList<BigDecimal>(glblMsg.I.getValidCount());
        HashMap<String, ArrayList<String[]>> chkPriceMap = new HashMap<String, ArrayList<String[]>>();

        if (bizMsg.I.getValidCount() <= 0) {
            return isSuccess;
        }

        // 2016/07/19 CSA-QC#11933 Add Start
        // don't add check, because this logic is can't do.
        // 2016/07/19 CSA-QC#11933 Add end
        for (int i = 0; i < glblMsg.I.getValidCount(); i++) {

            // Mandatory
            if (!ZYPCommonFunc.hasValue(glblMsg.I.no(i).prcQlfyTpCd_PI)) {
                glblMsg.I.no(i).prcQlfyTpCd_PI.setErrorInfo(1, ZZM9000E, new String[] {"Qualifer Type"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.I.no(i).prcQlfyValTxt_PI)) {
                glblMsg.I.no(i).prcQlfyValTxt_PI.setErrorInfo(1, ZZM9000E, new String[] {"Value"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.I.no(i).qtyDiscPrcAmt_PI)) {
                glblMsg.I.no(i).qtyDiscPrcAmt_PI.setErrorInfo(1, ZZM9000E, new String[] {"Price"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.I.no(i).pkgUomCd_PI)) {
                glblMsg.I.no(i).pkgUomCd_PI.setErrorInfo(1, ZZM9000E, new String[] {"UOM"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.I.no(i).effFromDt_PI)) {
                glblMsg.I.no(i).effFromDt_PI.setErrorInfo(1, ZZM9000E, new String[] {"Effective Date From"});
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(glblMsg.I.no(i).prcQlfyTpCd_PI)
                    && ZYPCommonFunc.hasValue(glblMsg.I.no(i).prcQlfyValTxt_PI)) {
                if (!checkPrcQlfyVal(glblMsg.I.no(i).prcQlfyTpCd_PI, glblMsg.I.no(i).prcQlfyValTxt_PI, glblMsg.I.no(i).prodCtrlNm_PI, glblCmpyCd)) {
                    isSuccess = false;
                }
            }

//            if (PRC_QLFY_TP.ITEM_CODE.equals(glblMsg.I.no(i).prcQlfyTpCd_PI.getValue())) {
//                if (ZYPCommonFunc.hasValue(glblMsg.I.no(i).qtyDiscPrcAmt_PI)) {
//                    if (!checkPrcByUom(glblMsg.I.no(i).prcQlfyValTxt_PI, glblMsg.I.no(i).pkgUomCd_PI.getValue(), glblMsg.I.no(i).qtyDiscPrcAmt_PI.getValue())) {
//                        glblMsg.I.no(i).prcQlfyValTxt_PI.setErrorInfo(1, NMAM8232E);
//                        glblMsg.I.no(i).pkgUomCd_PI.setErrorInfo(1, NMAM8232E);
//                        isSuccess = false;
//                    }
//                }
//            }

            // Effective Date
            if (ZYPCommonFunc.hasValue(glblMsg.I.no(i).effFromDt_PI)) {
                if (ZYPDateUtil.compare(glblMsg.I.no(i).effFromDt_PI.getValue(), toHighValDate(glblMsg.I.no(i).effThruDt_PI.getValue())) > 0) {
                    glblMsg.I.no(i).effFromDt_PI.setErrorInfo(1, NMAM0043E, new String[] {"Effective Date From", "Effective Date To" });
                    isSuccess = false;
                }
            }

            // Record Duplicate Check.
            // 2015/12/22 CSA-QC#1125 Mod Start
            if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.I.no(i).delFlg_PI.getValue())) {

                String keyInfo = setKeyInfoPrcListQtyDisc(glblMsg.I.no(i));

                if (chkPriceMap.containsKey(keyInfo)) {
                    if (!recordDuplicateCheck(chkPriceMap, keyInfo, glblMsg.I.no(i).effFromDt_PI.getValue(), glblMsg.I.no(i).effThruDt_PI.getValue())) {
                        glblMsg.I.no(i).xxChkBox_PI.setErrorInfo(1, NMAM0072E, new String[] {"Price List Qty Discount"});
                        isSuccess = false;
                    }
                }

                addChkPriceMap(chkPriceMap, keyInfo, glblMsg.I.no(i).effFromDt_PI.getValue(), glblMsg.I.no(i).effThruDt_PI.getValue());
                if (ZYPCommonFunc.hasValue(glblMsg.I.no(i).prcListQtyDiscPk_PI)) {
                    keyListRec.add(glblMsg.I.no(i).prcListQtyDiscPk_PI.getValue());
                }
            }
            // 2015/12/22 CSA-QC#1125 Mod End

            // 2016/07/19 CSA-QC#11933 Add Start
            // don't add check, because this logic is can't do.
            // 2016/07/19 CSA-QC#11933 Add end
        }
        // 2015/12/22 CSA-QC#1125 Add Start
        chkPriceMap.clear();

        // 2016/07/19 CSA-QC#11933 Add Start
        // don't Modify check, because this logic is can't do.
        // 2016/07/19 CSA-QC#11933 Add end
        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
            S21SsmEZDResult result = NMAL7010Query.getInstance().getPrcListQtyDiscForDuplicateCheck(bizMsg);

            StringBuilder keyInfo = new StringBuilder();
            if (!result.isCodeNotFound()) {
                List<PRC_LIST_QTY_DISCTMsg> prcListQtyDisc = (List) result.getResultObject();
                for (int i = 0; i < prcListQtyDisc.size(); i++) {
                    if (!keyListRec.contains(prcListQtyDisc.get(i).prcListQtyDiscPk.getValue())) {
                        keyInfo.setLength(0);
                        if (ZYPCommonFunc.hasValue(prcListQtyDisc.get(i).prcQlfyTpCd)) {
                            keyInfo = keyInfo.append(prcListQtyDisc.get(i).prcQlfyTpCd.getValue()).append(COMMA);
                        }
                        if (ZYPCommonFunc.hasValue(prcListQtyDisc.get(i).prcQlfyValTxt)) {
                            keyInfo = keyInfo.append(prcListQtyDisc.get(i).prcQlfyValTxt.getValue()).append(COMMA);
                        }
                        if (ZYPCommonFunc.hasValue(prcListQtyDisc.get(i).pkgUomCd)) {
                            keyInfo = keyInfo.append(prcListQtyDisc.get(i).pkgUomCd.getValue());
                        }

                        addChkPriceMap(chkPriceMap, keyInfo.toString(), prcListQtyDisc.get(i).effFromDt.getValue(), prcListQtyDisc.get(i).effThruDt.getValue());

                    }
                }
            }
        }
        if (chkPriceMap.size() > 0) {
            for (int i = 0; i < glblMsg.I.getValidCount(); i++) {
                if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.I.no(i).delFlg_PI.getValue())) {

                    String keyInfo = setKeyInfoPrcListQtyDisc(glblMsg.I.no(i));

                    if (chkPriceMap.containsKey(keyInfo)) {
                        if (!recordDuplicateCheck(chkPriceMap, keyInfo, glblMsg.I.no(i).effFromDt_PI.getValue(), glblMsg.I.no(i).effThruDt_PI.getValue())) {
                            glblMsg.I.no(i).xxChkBox_PI.setErrorInfo(1, NMAM0072E, new String[] {"Price List Qty Discount"});
                            isSuccess = false;
                        }
                    }
                }
            }
        }
        // 2015/12/22 CSA-QC#1125 Add End

        return isSuccess;
    }

    /**
     * checkPrcListQtyDiscDtl.
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkPrcListQtyDiscDtl(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, String glblCmpyCd) {

        boolean isSuccess = true;
        List<String> keyListRec = new ArrayList<String>(glblMsg.J.getValidCount());
        StringBuilder keyInfo = new StringBuilder();

        // 2016/07/19 CSA-QC#11933 Add Start
        // don't add check, because this logic is can't do.
        // 2016/07/19 CSA-QC#11933 Add end
        for (int i = 0; i < glblMsg.J.getValidCount(); i++) {
            // Mandatory
            if (!ZYPCommonFunc.hasValue(glblMsg.J.no(i).qtyDiscDtlQty_PJ)) {
                glblMsg.J.no(i).qtyDiscDtlQty_PJ.setErrorInfo(1, ZZM9000E, new String[] {"Qty"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.J.no(i).pkgUomCd_PJ)) {
                glblMsg.J.no(i).pkgUomCd_PJ.setErrorInfo(1, ZZM9000E, new String[] {"UOM"});
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.J.no(i).prcBreakAmt_PJ)) {
                glblMsg.J.no(i).prcBreakAmt_PJ.setErrorInfo(1, ZZM9000E, new String[] {"Price Break Amount"});
                isSuccess = false;
            }

//            if (ZYPCommonFunc.hasValue(glblMsg.J.no(i).prcBreakAmt_PJ)) {
//                NMAL7010_ISMsg isMsg = NMAL7010CommonLogic.getQtyDiscSMsg(glblMsg.I, glblMsg.J.no(i));
//                if (!checkPrcByUom(isMsg.prcQlfyValTxt_PI, glblMsg.J.no(i).pkgUomCd_PJ.getValue(), glblMsg.J.no(i).prcBreakAmt_PJ.getValue())) {
//                    glblMsg.J.no(i).pkgUomCd_PJ.setErrorInfo(1, NMAM8232E);
//                    isSuccess = false;
//                }
//            }

            // Record Duplicate Check.
            keyInfo.setLength(0);
            keyInfo = keyInfo.append(toStr(glblMsg.J.no(i).prcListQtyDiscPk_PJ.getValue())).append(COMMA);
            keyInfo = keyInfo.append(toStr(glblMsg.J.no(i).xxDtlCnt_PJ.getValue())).append(COMMA);
            keyInfo = keyInfo.append(toStr(glblMsg.J.no(i).qtyDiscDtlQty_PJ.getValue())).append(COMMA);
            keyInfo = keyInfo.append(glblMsg.J.no(i).pkgUomCd_PJ.getValue()).append(COMMA);
            if (keyListRec.contains(keyInfo.toString())) {
                glblMsg.J.no(i).xxChkBox_PJ.setErrorInfo(1, NMAM0072E, new String[] {"Price List Qty Discount Detail"});
                isSuccess = false;
            } else {
                keyListRec.add(keyInfo.toString());
            }

            // 2016/07/19 CSA-QC#11933 Add Start
            // don't add check, because this logic is can't do.
            // 2016/07/19 CSA-QC#11933 Add end
        }

        return isSuccess;
    }

    private static boolean checkCustAudc(NMAL7010CMsg bizMsg, String glblCmpyCd) {

        boolean isSuccess = true;
        if (bizMsg.X.getValidCount() <= 0) {
            return isSuccess;
        }

        String val1 = "";
        String val2 = "";
        String val3 = "";
        for (int i = 0; i < bizMsg.X.getValidCount(); i++) {
            // Business
            // Value 1
            if (PRC_CUST_AUDC_CATG.PUBLIC.equals(bizMsg.X.no(i).prcCustAudcCatgCd_X1.getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.X.no(i).prcCustAudcCatgCd_X2)
                        || ZYPCommonFunc.hasValue(bizMsg.X.no(i).xxScrItem30Txt_X2)
                        || ZYPCommonFunc.hasValue(bizMsg.X.no(i).prcCustAudcCatgCd_X3)
                        || ZYPCommonFunc.hasValue(bizMsg.X.no(i).xxScrItem30Txt_X3)) {
                    bizMsg.X.no(i).prcCustAudcCatgCd_X2.setErrorInfo(1, NMAM8215E);
                    bizMsg.X.no(i).xxScrItem30Txt_X2.setErrorInfo(1, NMAM8215E);
                    bizMsg.X.no(i).prcCustAudcCatgCd_X3.setErrorInfo(1, NMAM8215E);
                    bizMsg.X.no(i).xxScrItem30Txt_X3.setErrorInfo(1, NMAM8215E);
                    isSuccess = false;
                }
            } else {
                val1 = getNameMstCustAudc(bizMsg.X.no(i).prcCustAudcCatgCd_X1.getValue(), bizMsg.X.no(i).xxScrItem30Txt_X1, glblCmpyCd);
                if (val1 == null) {
                    isSuccess = false;
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.X.no(i).xxRecNmTxt_X1, val1);
                }
            }
            // Value 2
            if (ZYPCommonFunc.hasValue(bizMsg.X.no(i).prcCustAudcCatgCd_X2)) {
                val2 = getNameMstCustAudc(bizMsg.X.no(i).prcCustAudcCatgCd_X2.getValue(), bizMsg.X.no(i).xxScrItem30Txt_X2, glblCmpyCd);
                if (val2 == null) {
                    isSuccess = false;
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.X.no(i).xxRecNmTxt_X2, val2);
                }
            }

            // Value 3
            if (ZYPCommonFunc.hasValue(bizMsg.X.no(i).prcCustAudcCatgCd_X3)) {
                val3 = getNameMstCustAudc(bizMsg.X.no(i).prcCustAudcCatgCd_X3.getValue(), bizMsg.X.no(i).xxScrItem30Txt_X3, glblCmpyCd);
                if (val3 == null) {
                    isSuccess = false;
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.X.no(i).xxRecNmTxt_X3, val3);
                }
            }

            // Effective Date
            if (ZYPCommonFunc.hasValue(bizMsg.X.no(i).effThruDt_CX)) {
                if (ZYPDateUtil.compare(bizMsg.X.no(i).effFromDt_CX.getValue(), toHighValDate(bizMsg.X.no(i).effThruDt_CX.getValue())) > 0) {
                    bizMsg.X.no(i).effFromDt_CX.setErrorInfo(1, NMAM0043E, new String[] {"Effective Date From", "Effective Date To" });
                    isSuccess = false;
                }
            }
        }

        return isSuccess;
    }

    private static boolean checkTrxAudc(NMAL7010CMsg bizMsg, String glblCmpyCd) {

        boolean isSuccess = true;

        if (bizMsg.Y.getValidCount() <= 0) {
            return isSuccess;
        }

        String val1 = "";
        String val2 = "";
        for (int i = 0; i < bizMsg.Y.getValidCount(); i++) {
            // Value 1
            if (ZYPCommonFunc.hasValue(bizMsg.Y.no(i).prcTrxAudcCatgCd_Y1)) {
                val1 = getNameMstTrxAudc(bizMsg.Y.no(i).prcTrxAudcCatgCd_Y1.getValue(), bizMsg.Y.no(i).xxScrItem30Txt_Y1, glblCmpyCd, true);
                if (val1 == null) {
                    isSuccess = false;
                } else {
                    // if (PRC_TRX_AUDC_CATG.TRANSACTION_PRICE_GROUP.equals(bizMsg.Y.no(i).prcTrxAudcCatgCd_Y1.getValue())) {
                    //     ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(i).xxScrItem30Txt_Y1, val1);
                    // }
                    ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(i).xxRecNmTxt_Y1, val1);
                }
            }

            if (ZYPCommonFunc.hasValue(bizMsg.Y.no(i).prcTrxAudcCatgCd_Y2)) {
                val2 = getNameMstTrxAudc(bizMsg.Y.no(i).prcTrxAudcCatgCd_Y2.getValue(), bizMsg.Y.no(i).xxScrItem30Txt_Y2, glblCmpyCd, true);
                if (val2 == null) {
                    isSuccess = false;
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(i).xxRecNmTxt_Y2, val2);
                }
            }

            // Effective Date
            if (ZYPCommonFunc.hasValue(bizMsg.Y.no(i).effThruDt_TY)) {
                if (ZYPDateUtil.compare(bizMsg.Y.no(i).effFromDt_TY.getValue(), bizMsg.Y.no(i).effThruDt_TY.getValue()) > 0) {
                    bizMsg.Y.no(i).effFromDt_TY.setErrorInfo(1, NMAM0043E, new String[] {"Effective Date From", "Effective Date To" });
                    isSuccess = false;
                }
            }
        }

        return isSuccess;
    }

    private static boolean checkTrxDrv(NMAL7010CMsg bizMsg, String glblCmpyCd) {

        boolean isSuccess = true;

        bizMsg.leaseRatePrcCatgCd_CA.clear();

        if (!ZYPCommonFunc.hasValue(bizMsg.prcCatgNm_CA)) {
            return isSuccess;
        }

        // --- Equipment ---
        // get and check prcCatgNm
        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {

            String leaseRatePrcCatgCd = NMAL7010CommonLogic.getPrcCatgCd(bizMsg.prcCatgNm_CA.getValue(), PRC_LIST_STRU_TP.LEASE_RATES);

            if (!ZYPCommonFunc.hasValue(leaseRatePrcCatgCd)) {
                bizMsg.prcCatgNm_CA.setErrorInfo(1, NMAM0163E, new String[] {bizMsg.prcCatgNm_CA.getValue(), "Price Category" });
                isSuccess = false;
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.leaseRatePrcCatgCd_CA, leaseRatePrcCatgCd);
            }
        }

        return isSuccess;
    }

    private static boolean checkSubPrcList(NMAL7010CMsg bizMsg) {
        // QC#2175
        boolean isSuccess = true;

        if (bizMsg.W.getValidCount() <= 0) {
            return isSuccess;
        }

        for (int i = 0; i < bizMsg.W.getValidCount(); i++) {
            boolean isLineSuccess = true;

            // Effective Date
            if (ZYPCommonFunc.hasValue(bizMsg.W.no(i).effThruDt_SW)) {
                if (ZYPDateUtil.compare(bizMsg.W.no(i).effFromDt_SW.getValue(), bizMsg.W.no(i).effThruDt_SW.getValue()) > 0) {
                    bizMsg.W.no(i).effFromDt_SW.setErrorInfo(1, NMAM0043E, new String[] {"Effective Date From", "Effective Date To" });
                    isLineSuccess = false;
                }
            }

            // Price List Structure Type
            S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcListStruTp(bizMsg, bizMsg.W.no(i).subPrcCatgCd_SW.getValue());
            if (ssmResult.isCodeNotFound()) {
                bizMsg.W.no(i).prcCatgNm_SW.setErrorInfo(1, NMAM0163E, new String[] {bizMsg.W.no(i).subPrcCatgCd_SW.getValue(), "Price List" });
                isLineSuccess = false;
            } else {
                String rsltPrcListStruTp = (String) ssmResult.getResultObject();
                if (!bizMsg.prcListStruTpCd_H1.getValue().equals(rsltPrcListStruTp)) {
                    bizMsg.W.no(i).prcCatgNm_SW.setErrorInfo(1, NMAM8248E, new String[] {bizMsg.W.no(i).subPrcCatgCd_SW.getValue() });
                    isLineSuccess = false;
                }
            }

            // Cycle Reference
            if (isLineSuccess) {
                if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
                    // 2016/09/16 QC#14579 Add Start
                    if (bizMsg.prcCatgCd_BK.getValue().equals(bizMsg.W.no(i).subPrcCatgCd_SW.getValue())) {
                        bizMsg.W.no(i).prcCatgNm_SW.setErrorInfo(1, NMAM8249E);
                        isLineSuccess = false;
                    }
                    // 2016/09/16 QC#14579 Add End

                    ssmResult = NMAL7010Query.getInstance().searchSubPrcListTree(bizMsg, new String[] {bizMsg.W.no(i).subPrcCatgCd_SW.getValue() });
                    if (ssmResult.isCodeNormal()) {
                        bizMsg.W.no(i).prcCatgNm_SW.setErrorInfo(1, NMAM8249E);
                        isLineSuccess = false;
                    }
                }
            }

            if (!isLineSuccess) {
                isSuccess = false;
            }
        }

        if (!isSuccess) {
            return false;
        }

        // Duplicate (Sub Price Category Code & Period, Priority
        // Number)
        for (int i = 0; i < bizMsg.W.getValidCount(); i++) {
            for (int j = i + 1; j < bizMsg.W.getValidCount(); j++) {

                String preSubPrcCatgCd = bizMsg.W.no(i).subPrcCatgCd_SW.getValue();
                String preEffFromDt = bizMsg.W.no(i).effFromDt_SW.getValue();
                String preEffThruDt = bizMsg.W.no(i).effThruDt_SW.getValue();
                BigDecimal preSubPrcPrtyNum = bizMsg.W.no(i).subPrcPrtyNum_SW.getValue();

                String nextSubPrcCatgCd = bizMsg.W.no(j).subPrcCatgCd_SW.getValue();
                String nextEffFromDt = bizMsg.W.no(j).effFromDt_SW.getValue();
                String nextEffThruDt = bizMsg.W.no(j).effThruDt_SW.getValue();
                BigDecimal nextSubPrcPrtyNum = bizMsg.W.no(j).subPrcPrtyNum_SW.getValue();

                if (preSubPrcCatgCd.equals(nextSubPrcCatgCd)) {
                    if (isPeriodOverlap(preEffFromDt, preEffThruDt, nextEffFromDt, nextEffThruDt)) {
                        bizMsg.W.no(i).effFromDt_SW.setErrorInfo(1, NMAM0072E, new String[] {"Effective Date" });
                        bizMsg.W.no(i).effThruDt_SW.setErrorInfo(1, NMAM0072E, new String[] {"Effective Date" });

                        bizMsg.W.no(j).effFromDt_SW.setErrorInfo(1, NMAM0072E, new String[] {"Effective Date" });
                        bizMsg.W.no(j).effThruDt_SW.setErrorInfo(1, NMAM0072E, new String[] {"Effective Date" });

                        isSuccess = false;
                    }
                }
                if (preSubPrcPrtyNum.compareTo(nextSubPrcPrtyNum) == 0) {
                    bizMsg.W.no(i).subPrcPrtyNum_SW.setErrorInfo(1, NMAM0072E, new String[] {"Sub Price Sequence Number" });
                    bizMsg.W.no(j).subPrcPrtyNum_SW.setErrorInfo(1, NMAM0072E, new String[] {"Sub Price Sequence Number" });
                    isSuccess = false;
                }
            }
        }

        return isSuccess;
    }

    private static boolean isPeriodOverlap(String preEffFromDt, String preEffThruDt, String nextEffFromDt, String nextEffThruDt) {
        preEffThruDt = toHighValDate(preEffThruDt);
        nextEffThruDt = toHighValDate(nextEffThruDt);

        if (isPeriodOverlap(preEffFromDt, nextEffFromDt, nextEffThruDt)) {
            return true;
        }
        if (isPeriodOverlap(preEffThruDt, nextEffFromDt, nextEffThruDt)) {
            return true;
        }
        if (isPeriodOverlap(nextEffFromDt, preEffFromDt, preEffThruDt)) {
            return true;
        }
        if (isPeriodOverlap(nextEffThruDt, preEffFromDt, preEffThruDt)) {
            return true;
        }
        return false;
    }

    private static boolean isPeriodOverlap(String trgtDt, String fromDt, String thruDt) {
        if ((trgtDt.compareTo(fromDt) >= 0) && (trgtDt.compareTo(thruDt) <= 0)) {
            return true;
        }
        return false;
    }

    private static boolean isExistPrcContr(NMAL7010CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.prcContrNum_H1)) {
            bizMsg.prcContrNm_H1.clear();
            return true;
        }

        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcContr(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrNm_H1, (String) ssmResult.getResultObject());

        return true;
    }

    private static boolean isExistPrcListNm(NMAL7010CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcList(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            return false;
        }

        return true;
    }

    private static boolean isExistTable(String tblNm, String whereVal) {

        String colNm = "";
        String whereCol = "";
        if ("SELL_TO_CUST".equals(tblNm)) {
            colNm = "DS_ACCT_NM";
            whereCol = "DS_ACCT_NM";
        } else if ("PRC_MTR_PKG".equals(tblNm)) {
            colNm = "PRC_MTR_PKG_NM";
            whereCol = "PRC_MTR_PKG_NM";
        }

        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getAnyColmn(colNm, tblNm, whereCol, whereVal);
        if (ssmResult.isCodeNotFound()) {
            return false;
        }

        return true;
    }

    private static boolean isExistTable(String tblNm, BigDecimal whereVal) {

        String colNm = "";
        String whereCol = "";
        if ("SPLY_AGMT_PLN".equals(tblNm)) {
            colNm = "SPLY_AGMT_PLN_NM";
            whereCol = "SPLY_AGMT_PLN_PK";
        }

        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getAnyColmn(colNm, tblNm, whereCol, whereVal);
        if (ssmResult.isCodeNotFound()) {
            return false;
        }

        return true;
    }

    private static BigDecimal getBigDecimalAnyItem(String tblNm, String whereVal) {

        String colNm = "";
        String whereCol = "";
        if ("PRC_MTR_PKG".equals(tblNm)) {
            colNm = "PRC_MTR_PKG_PK";
            whereCol = "PRC_MTR_PKG_NM";
        }

        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getAnyColmn(colNm, tblNm, whereCol, whereVal);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        return new BigDecimal((String) ssmResult.getResultObject());
    }

    private static String getNameMstCustAudc(String prcCustAudcCd, EZDCStringItem valueFiled, String glblCmpyCd) {

        String rtrnVal = "";

        if (PRC_CUST_AUDC_CATG.PUBLIC_LOB.equals(prcCustAudcCd)) {
            rtrnVal = ZYPCodeDataUtil.getName(LINE_BIZ_TP.class, glblCmpyCd, valueFiled.getValue());
            if (rtrnVal == null) {
                valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "Line Business Type"});
                return null;
            }

        } else if (PRC_CUST_AUDC_CATG.ACCT_NUM.equals(prcCustAudcCd)) {
            S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getAnyColmn("DS_ACCT_NM", "SELL_TO_CUST", "SELL_TO_CUST_CD", valueFiled.getValue());
            if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
                valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "Account Number"});
                return null;
            }
            rtrnVal =  (String) ssmResult.getResultObject();

        } else if (PRC_CUST_AUDC_CATG.ACCT_CHANNEL.equals(prcCustAudcCd)) {
            rtrnVal = ZYPCodeDataUtil.getName(COA_CH.class, glblCmpyCd, valueFiled.getValue());
            if (rtrnVal == null) {
                valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "COA Channel"});
                return null;
            }
        } else if (PRC_CUST_AUDC_CATG.ACCT_CUSTGROUP.equals(prcCustAudcCd)) {
            // 2016/2/25 QC#2928 Mod Start
            //rtrnVal = ZYPCodeDataUtil.getName(DS_ACCT_GRP.class, glblCmpyCd, valueFiled.getValue());
            //if (rtrnVal == null) {
            //    valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "Account Group"});
            //    return null;
            //}
            S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getAnyColmn("DS_ACCT_GRP_NM", "DS_ACCT_GRP", "DS_ACCT_GRP_CD", valueFiled.getValue());
            if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
                valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "Account Group"});
                return null;
            }
            rtrnVal =  (String) ssmResult.getResultObject();
            // 2016/2/25 QC#2928 Mod End

        } else if (PRC_CUST_AUDC_CATG.BRANCH.equals(prcCustAudcCd)) {
            rtrnVal = ZYPCodeDataUtil.getName(COA_BR.class, glblCmpyCd, valueFiled.getValue());
            if (rtrnVal == null) {
                valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "COA Branch"});
                return null;
            }

        // CSMP Number check unnecessary.
        } else if (PRC_CUST_AUDC_CATG.CUST_PRICE_GROUP.equals(prcCustAudcCd)) {
            if (!ZYPCommonFunc.hasValue(valueFiled)
                    || !ZYPCommonFunc.isNumberCheck(valueFiled.getValue())) {
                valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "Price Group"});
                return null;
            }
            S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcGrp(valueFiled.getValue(), PRC_GRP_TP.CUSTOMER_PRICING_GROUP, true);
            if (ssmResult.isCodeNotFound()) {
                valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "Price Group"});
                return null;
            }
            rtrnVal = (String) ((Map< ? , ? >) ssmResult.getResultObject()).get("PRC_GRP_NM");
       }
        return rtrnVal;
    }

    private static String getNameMstTrxAudc(String prcTrxAudcCd, EZDCStringItem valueFiled, String glblCmpyCd, boolean isPk) {
        String rtrnVal = "";
        if (PRC_TRX_AUDC_CATG.TRANSACTION_PRICE_GROUP.equals(prcTrxAudcCd)) {
            if (!ZYPCommonFunc.hasValue(valueFiled)
                    || (isPk && !ZYPCommonFunc.isNumberCheck(valueFiled.getValue()))) {
                valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "Price Group"});
                return null;
            }
            S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcGrp(valueFiled.getValue(), PRC_GRP_TP.ORDER_CATEGORY_OR_REASON, isPk);
            if (ssmResult.isCodeNotFound()) {
                valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "Price Group"});
                return null;
            }
            rtrnVal = ((String) ((Map< ? , ? >) ssmResult.getResultObject()).get("PRC_GRP_NM")).toString();
        } else if (PRC_TRX_AUDC_CATG.ORDER_CATEGORY.equals(prcTrxAudcCd)) {
            rtrnVal = ZYPCodeDataUtil.getName(DS_ORD_CATG.class, glblCmpyCd, valueFiled.getValue());
            if (rtrnVal == null) {
                valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "DS Order Category"});
                return null;
            }
        } else if (PRC_TRX_AUDC_CATG.ORDER_REASON.equals(prcTrxAudcCd)) {
            rtrnVal = ZYPCodeDataUtil.getName(DS_ORD_TP.class, glblCmpyCd, valueFiled.getValue());
            if (rtrnVal == null) {
                valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "DS Order Type"});
                return null;
            }
        }
        return rtrnVal;
    }

    private static BigDecimal getPrcMtrPkgBllgMtr(BigDecimal prcMtrPkgPk, String mtrLbCd, BigDecimal mdlId) {
        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcMtrPkgBllgMtr(prcMtrPkgPk, mtrLbCd, mdlId);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }

        return (BigDecimal) ssmResult.getResultObject();
    }
    
    private static String getBllgMtrLb(String bllgMtrLbNm) {
        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getBllgMtrLb(bllgMtrLbNm);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }

        return (String) ssmResult.getResultObject();
    }
    // 2018/11/17 QC#29147 Add Start
    private static String getPrcListBand(String prcListBandDescTxt) {
        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcListBand(prcListBandDescTxt);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }

        return (String) ssmResult.getResultObject();
    }
    // 2018/11/17 QC#29147 Add End


    /**
     * checkPrcFmlaRelation.
     * @param aSMsg NMAL7010_ASMsg
     * @return boolean
     */
    public static boolean checkPrcFmlaRelation(NMAL7010_ASMsg aSMsg) {
        if (!ZYPCommonFunc.hasValue(aSMsg.prcFmlaPk_PA) || !ZYPCommonFunc.hasValue(aSMsg.prcQlfyValTxt_PA)) {
            return true;
        }

        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcFmlaRelation(aSMsg.prcFmlaPk_PA.getValue(), aSMsg.prcQlfyValTxt_PA.getValue());
        if (ssmResult.isCodeNotFound()) {
            return true;
        }
        aSMsg.prcFmlaPk_PA.setErrorInfo(1, NMAM8226E);
        return false;
    }

    /**
     * readHeaderCsvFile.
     * @param mappedFile EZDCSVInFile
     * @param bizMsg NMAL7010CMsg
     * @return boolean
     */
    public static boolean readHeaderCsvFile(EZDCSVInFile mappedFile, NMAL7010CMsg bizMsg) {
        int header = mappedFile.read();
        if (header == 1) {
            bizMsg.setMessageInfo(NMAM8191E, new String[] {"Header Record"});
            return false;
        }
        return true;
    }

    /**
     * validateCustAudc.
     * @param bizMsg NMAL7010CMsg
     * @param status int
     * @param totCount int
     * @param fMsg NMAL7010F00FMsg
     * @param uploadCount int
     * @param length int
     * @return boolean
     */
    public static boolean validateCustAudc(NMAL7010CMsg bizMsg, int status, int totCount, NMAL7010F00FMsg fMsg, int uploadCount, int length) {
        if (totCount > length) {
            bizMsg.setMessageInfo(NMAM8197E);
            return true;
        }

        if (status == 1000) {
            bizMsg.setMessageInfo(NMAM8191E, new String[]{"Line=" + String.valueOf(uploadCount)});
            return true;
        }
        int errCol1 = status - 1000;
        int errCol2 = status - 2000;
        // Account Number
        if (errCol1 == 1 || errCol2 == 1) {
            bizMsg.setMessageInfo(NMAM8191E, new String[]{"Account Number(Line=" + String.valueOf(uploadCount) + ")"});
            return true;
        // Account Name
        } else if (errCol1 == 2 || errCol2 == 2) {
            bizMsg.setMessageInfo(NMAM8191E, new String[]{"Account Name(Line=" + String.valueOf(uploadCount) + ")"});
            return true;
        }

        return false;
    }

    /**
     * validatePrcList.
     * @param bizMsg NMAL7010CMsg
     * @param status int
     * @param totCount int
     * @param fMsg EZDFMsg
     * @param uploadCount int
     * @param length int
     * @param colStrList String[]
     * @return boolean
     */
    public static boolean validatePrcList(NMAL7010CMsg bizMsg, int status, int totCount, EZDFMsg fMsg, int uploadCount, int length, String[] colStrList) {

        if (totCount >= length) {
            bizMsg.setMessageInfo(NMAM8197E);
            return true;
        }

        if (status == 1000) {
            bizMsg.setMessageInfo(NMAM8191E, new String[]{"Line=" + String.valueOf(uploadCount)});
            return true;
        }
        int errCol1 = status - 1000;
        int errCol2 = status - 2000;

        for (int colIdx = 1; colIdx < colStrList.length; colIdx++) {
            if (errCol1 == colIdx || errCol2 == colIdx) {
                bizMsg.setMessageInfo(NMAM8191E, new String[]{colStrList[colIdx - 1] + "(Line=" + String.valueOf(uploadCount) + ")"});
                return true;
            }
        }
        return false;
    }
//    /**
//     * recordDuplicateCheck.
//     * @param idx int
//     * @param chkPriceMap HashMap<Integer,String[]>
//     * @param chkPriceMapTable HashMap<Integer,String[]>
//     * @return boolean
//     */
//    public static boolean duplicateCheck(int idx, HashMap<Integer, String[]> chkPriceMap, HashMap<Integer, String[]> chkPriceMapTable) {
//
//        String keyInfo = chkPriceMap.get(idx)[0];
//        String effFromDt = chkPriceMap.get(idx)[1];
//        String effThruDt = chkPriceMap.get(idx)[2];
//        String delFlg = chkPriceMap.get(idx)[3];
//        // Duplicate Check for displayed data.
//        for (int i = 0; i < chkPriceMap.size(); i++) {
//            if (i != idx && !recordDuplicateCheck(keyInfo, effFromDt, effThruDt, chkPriceMap.get(i))) {
//                return false;
//            }
//        }
//        // Duplicate Check for Database data.
//        for (int i = 0; i < chkPriceMapTable.size(); i++) {
//            if (!recordDuplicateCheck(keyInfo, effFromDt, effThruDt, chkPriceMapTable.get(i))) {
//                return false;
//            }
//        }
//        return true;
//    }
    // 2015/12/22 CSA-QC#1125 Add Start
    /**
     * recordDuplicateCheck
     * @param chkPriceMap HashMap<String, ArrayList<String[]>>
     * @param keyInfo String 
     * @param effFromDt String
     * @param effThruDt String
     * @return boolean
     */
    public static boolean recordDuplicateCheck(HashMap<String, ArrayList<String[]>> chkPriceMap, String keyInfo, String effFromDt, String effThruDt) {

        effThruDt = toHighValDate(effThruDt);
        ArrayList<String[]> list = chkPriceMap.get(keyInfo);
        for (int i = 0; i < list.size(); i++) {
            String tgtEffFromDt = list.get(i)[0];
            String tgtEffThruDt = list.get(i)[1];
            if (ZYPDateUtil.compare(effFromDt, tgtEffThruDt) <= 0
                    && ZYPDateUtil.compare(tgtEffFromDt, effThruDt) <= 0) {
                return false;
            }
        }
        return true;
    }
    /**
     * 
     * @param chkPriceMap HashMap<String, ArrayList<String[]>>
     * @param keyInfo String 
     * @param effFromDt String
     * @param effThruDt String
     */
    public static void addChkPriceMap(HashMap<String, ArrayList<String[]>> chkPriceMap, String keyInfo, String effFromDt, String effThruDt) {
        String[] list = {effFromDt, toHighValDate(effThruDt)};
        ArrayList<String[]> valueList = chkPriceMap.get(keyInfo);
        if (valueList == null) {
            valueList = new ArrayList<String[]>();
        }
        valueList.add(list);
        chkPriceMap.put(keyInfo, valueList);

    }
    /**
     * setKeyInfoPrcListEquip
     * @param asMsg NMAL7010_ASMsg
     * @return String
     */
    public static String setKeyInfoPrcListEquip(NMAL7010_ASMsg asMsg) {
        StringBuilder keyInfo = new StringBuilder();
        if (ZYPCommonFunc.hasValue(asMsg.prcListEquipConfigNum_PA)) {
            keyInfo = keyInfo.append(toStr(asMsg.prcListEquipConfigNum_PA.getValue())).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(asMsg.prcListEquipConfigNm_PA)) {
            keyInfo = keyInfo.append(asMsg.prcListEquipConfigNm_PA.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(asMsg.prcQlfyTpCd_PA)) {
            keyInfo = keyInfo.append(asMsg.prcQlfyTpCd_PA.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(asMsg.prcQlfyValTxt_PA)) {
            keyInfo = keyInfo.append(asMsg.prcQlfyValTxt_PA.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(asMsg.pkgUomCd_PA)) {
            keyInfo = keyInfo.append(asMsg.pkgUomCd_PA.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(asMsg.prcTermUomCd_PA)) {
            keyInfo = keyInfo.append(asMsg.prcTermUomCd_PA.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(asMsg.prcTermAot_PA)) {
            keyInfo = keyInfo.append(toStr(asMsg.prcTermAot_PA.getValue()));
        }
        return keyInfo.toString();
    }
    /**
     * setKeyInfoPrcListSvc
     * @param bsMsg NMAL7010_BSMsg
     * @return String
     */
    public static String setKeyInfoPrcListSvc(NMAL7010_BSMsg bsMsg) {
        StringBuilder keyInfo = new StringBuilder();
        if (ZYPCommonFunc.hasValue(bsMsg.mdlId_PB)) {
            keyInfo = keyInfo.append(toStr(bsMsg.mdlId_PB.getValue())).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(bsMsg.prcMtrPkgPk_PB)) {
            keyInfo = keyInfo.append(toStr(bsMsg.prcMtrPkgPk_PB.getValue())).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(bsMsg.prcListMdseCd_PB)) {
            // QC#6933 Add
            keyInfo = keyInfo.append(bsMsg.prcListMdseCd_PB.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(bsMsg.prcSvcPlnTpCd_PB)) {
            keyInfo = keyInfo.append(bsMsg.prcSvcPlnTpCd_PB.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(bsMsg.prcSvcContrTpCd_PB)) {
            keyInfo = keyInfo.append(bsMsg.prcSvcContrTpCd_PB.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(bsMsg.mtrLbCd_PB)) {
            keyInfo = keyInfo.append(bsMsg.mtrLbCd_PB.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(bsMsg.prcListBandCd_PB)) {
            keyInfo = keyInfo.append(bsMsg.prcListBandCd_PB.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(bsMsg.termFromMthAot_PB)) {
            keyInfo = keyInfo.append(toStr(bsMsg.termFromMthAot_PB.getValue())).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(bsMsg.termThruMthAot_PB)) {
            keyInfo = keyInfo.append(toStr(bsMsg.termThruMthAot_PB.getValue()));
        }

        return keyInfo.toString();
    }
    /**
     * setKeyInfoPrcListSvcTier
     * @param csMsg NMAL7010_CSMsg
     * @return String
     */
    public static String setKeyInfoPrcListSvcTier(NMAL7010_CSMsg csMsg) {
        StringBuilder keyInfo = new StringBuilder();
        if (ZYPCommonFunc.hasValue(csMsg.mdlId_PC)) {
            keyInfo = keyInfo.append(toStr(csMsg.mdlId_PC.getValue())).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(csMsg.prcSvcTierTpCd_PC)) {
            keyInfo = keyInfo.append(csMsg.prcSvcTierTpCd_PC.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(csMsg.prcMtrPkgPk_PC)) {
            keyInfo = keyInfo.append(toStr(csMsg.prcMtrPkgPk_PC.getValue())).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(csMsg.prcSvcPlnTpCd_PC)) {
            keyInfo = keyInfo.append(csMsg.prcSvcPlnTpCd_PC.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(csMsg.prcSvcContrTpCd_PC)) {
            keyInfo = keyInfo.append(csMsg.prcSvcContrTpCd_PC.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(csMsg.mtrLbCd_PC)) {
            keyInfo = keyInfo.append(csMsg.mtrLbCd_PC.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(csMsg.prcListBandCd_PC)) {
            keyInfo = keyInfo.append(csMsg.prcListBandCd_PC.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(csMsg.termFromMthAot_PC)) {
            keyInfo = keyInfo.append(toStr(csMsg.termFromMthAot_PC.getValue())).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(csMsg.termThruMthAot_PC)) {
            keyInfo = keyInfo.append(toStr(csMsg.termThruMthAot_PC.getValue()));
        }

        return keyInfo.toString();
    }
    /**
     * setKeyInfoPrcListSplyPgm
     * @param esMsg NMAL7010_ESMsg
     * @return String
     */
    public static String setKeyInfoPrcListSplyPgm(NMAL7010_ESMsg esMsg) {

        StringBuilder keyInfo = new StringBuilder();
        if (ZYPCommonFunc.hasValue(esMsg.mdlId_PE)) {
            keyInfo = keyInfo.append(toStr(esMsg.mdlId_PE.getValue())).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(esMsg.prcMtrPkgPk_PE)) {
            keyInfo = keyInfo.append(toStr(esMsg.prcMtrPkgPk_PE.getValue())).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(esMsg.prcSvcPlnTpCd_PE)) {
            keyInfo = keyInfo.append(esMsg.prcSvcPlnTpCd_PE.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(esMsg.prcSvcContrTpCd_PE)) {
            keyInfo = keyInfo.append(esMsg.prcSvcContrTpCd_PE.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(esMsg.mtrLbCd_PE)) {
            keyInfo = keyInfo.append(esMsg.mtrLbCd_PE.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(esMsg.prcListBandCd_PE)) {
            keyInfo = keyInfo.append(esMsg.prcListBandCd_PE.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(esMsg.termFromMthAot_PE)) {
            keyInfo = keyInfo.append(toStr(esMsg.termFromMthAot_PE.getValue())).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(esMsg.termThruMthAot_PE)) {
            keyInfo = keyInfo.append(toStr(esMsg.termThruMthAot_PE.getValue()));
        }

        return keyInfo.toString();
    }
    /**
     * setKeyInfoPrcListAddlChrg
     * @param dsMsg NMAL7010_DSMsg
     * @return String
     */
    public static String setKeyInfoPrcListAddlChrg(NMAL7010_DSMsg dsMsg) {

        StringBuilder keyInfo = new StringBuilder();
        if (ZYPCommonFunc.hasValue(dsMsg.mdseCd_PD)) {
            keyInfo = keyInfo.append(dsMsg.mdseCd_PD.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(dsMsg.mktMdlSegCd_PD)) {
            keyInfo = keyInfo.append(dsMsg.mktMdlSegCd_PD.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(dsMsg.mdlId_PD)) {
            keyInfo = keyInfo.append(toStr(dsMsg.mdlId_PD.getValue())).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(dsMsg.mdlNm_PD)) {
            keyInfo = keyInfo.append(dsMsg.mdlNm_PD.getValue()).append(COMMA);
        }

        return keyInfo.toString();
    }
    /**
     * setKeyInfoPrcListLeaseRate
     * @param fsMsg NMAL7010_FSMsg
     * @return String
     */
    public static String setKeyInfoPrcListLeaseRate(NMAL7010_FSMsg fsMsg) {

        StringBuilder keyInfo = new StringBuilder();
        if (ZYPCommonFunc.hasValue(fsMsg.prcLeaseCmpyAbbrNm_PF)) {
            keyInfo = keyInfo.append(fsMsg.prcLeaseCmpyAbbrNm_PF.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(fsMsg.dsAcctNm_PF)) {
            keyInfo = keyInfo.append(fsMsg.dsAcctNm_PF.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(fsMsg.prcPgmTpCd_PF)) {
            keyInfo = keyInfo.append(fsMsg.prcPgmTpCd_PF.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(fsMsg.prcEquipTpCd_PF)) {
            keyInfo = keyInfo.append(fsMsg.prcEquipTpCd_PF.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(fsMsg.mdlId_PF)) {
            keyInfo = keyInfo.append(toStr(fsMsg.mdlId_PF.getValue())).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(fsMsg.termFromMthAot_PF)) {
            keyInfo = keyInfo.append(toStr(fsMsg.termFromMthAot_PF.getValue())).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(fsMsg.termThruMthAot_PF)) {
            keyInfo = keyInfo.append(toStr(fsMsg.termThruMthAot_PF.getValue()));
        }

        return keyInfo.toString();
    }
    /**
     * setKeyInfoPrcListLeaseRtrn
     * @param gsMsg NMAL7010_GSMsg
     * @return String
     */
    public static String setKeyInfoPrcListLeaseRtrn(NMAL7010_GSMsg gsMsg) {

        StringBuilder keyInfo = new StringBuilder();
        if (ZYPCommonFunc.hasValue(gsMsg.prcLeaseCmpyAbbrNm_PG)) {
            keyInfo = keyInfo.append(gsMsg.prcLeaseCmpyAbbrNm_PG.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(gsMsg.svcSegCd_PG)) {
            keyInfo = keyInfo.append(gsMsg.svcSegCd_PG.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(gsMsg.prcInOutRgCd_PG)) {
            keyInfo = keyInfo.append(gsMsg.prcInOutRgCd_PG.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(gsMsg.dstMileAmt_PG)) {
            keyInfo = keyInfo.append(toStr(gsMsg.dstMileAmt_PG.getValue()));
        }

        return keyInfo.toString();
    }
    /**
     * setKeyInfoPrcListTiVal
     * @param hsMsg NMAL7010_HSMsg
     * @return String
     */
    public static String setKeyInfoPrcListTiVal(NMAL7010_HSMsg hsMsg) {

        StringBuilder keyInfo = new StringBuilder();
        if (ZYPCommonFunc.hasValue(hsMsg.mdlId_PH)) {
            keyInfo = keyInfo.append(toStr(hsMsg.mdlId_PH.getValue())).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(hsMsg.fromMtrCopyVolCnt_PH)) {
            keyInfo = keyInfo.append(toStr(hsMsg.fromMtrCopyVolCnt_PH.getValue())).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(hsMsg.thruMtrCopyVolCnt_PH)) {
            keyInfo = keyInfo.append(toStr(hsMsg.thruMtrCopyVolCnt_PH.getValue()));
        }

        return keyInfo.toString();
    }
    /**
     * setKeyInfoPrcListQtyDisc
     * @param isMsg NMAL7010_ISMsg
     * @return String
     */
    public static String setKeyInfoPrcListQtyDisc(NMAL7010_ISMsg isMsg) {

        StringBuilder keyInfo = new StringBuilder();
        if (ZYPCommonFunc.hasValue(isMsg.prcQlfyTpCd_PI)) {
            keyInfo = keyInfo.append(isMsg.prcQlfyTpCd_PI.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(isMsg.prcQlfyValTxt_PI)) {
            keyInfo = keyInfo.append(isMsg.prcQlfyValTxt_PI.getValue()).append(COMMA);
        }
        if (ZYPCommonFunc.hasValue(isMsg.pkgUomCd_PI)) {
            keyInfo = keyInfo.append(isMsg.pkgUomCd_PI.getValue());
        }

        return keyInfo.toString();
    }
    // 2015/12/22 CSA-QC#1125 Add End

    private static String getMtrLb(String mtrLbNm) {
        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getMtrLb(mtrLbNm);
        //getMtrLb
        if (ssmResult.isCodeNotFound()) {
            return null;
        }

        return (String) ssmResult.getResultObject();
    }

    /* ------ Compare methods ----- */
    public static boolean isChangeForEquip(NMAL7010_ACMsg acMsg, NMAL7010_ASMsg asMsg) {
        if (!isEqual(acMsg.prcListEquipConfigNum_PA, asMsg.prcListEquipConfigNum_PA)) {
            return true;
        }
        if (!isEqual(acMsg.prcListEquipConfigNm_PA, asMsg.prcListEquipConfigNm_PA)) {
            return true;
        }
        if (!isEqual(acMsg.prcQlfyTpCd_PA, asMsg.prcQlfyTpCd_PA)) {
            return true;
        }
        if (!isEqual(acMsg.prcQlfyValTxt_PA, asMsg.prcQlfyValTxt_PA)) {
            return true;
        }
        if (!isEqual(acMsg.pkgUomCd_PA, asMsg.pkgUomCd_PA)) {
            return true;
        }
        if (!isEqual(acMsg.prcListEquipPrcAmt_PA, asMsg.prcListEquipPrcAmt_PA)) {
            return true;
        }
        if (!isEqual(acMsg.minPrcAmt_PA, asMsg.minPrcAmt_PA)) {
            return true;
        }
        if (!isEqual(acMsg.maxPrcAmt_PA, asMsg.maxPrcAmt_PA)) {
            return true;
        }
        if (!isEqual(acMsg.prcTermAot_PA, asMsg.prcTermAot_PA)) {
            return true;
        }
        if (!isEqual(acMsg.prcTermUomCd_PA, asMsg.prcTermUomCd_PA)) {
            return true;
        }
        if (!isEqual(acMsg.custBidQty_PA, asMsg.custBidQty_PA)) {
            return true;
        }
        if (!isEqual(acMsg.mlyPmtAmt_PA, asMsg.mlyPmtAmt_PA)) {
            return true;
        }
        if (!isEqual(acMsg.minLeasePmtAmt_PA, asMsg.minLeasePmtAmt_PA)) {
            return true;
        }
        if (!isEqual(acMsg.maxLeasePmtAmt_PA, asMsg.maxLeasePmtAmt_PA)) {
            return true;
        }
        if (!isEqual(acMsg.prcFmlaPk_PA, asMsg.prcFmlaPk_PA)) {
            return true;
        }
        if (!isEqualForFlg(acMsg.openMktFlg_PA, asMsg.openMktFlg_PA)) {
            return true;
        }
        if (!isEqual(acMsg.quotRevAmt_PA, asMsg.quotRevAmt_PA)) {
            return true;
        }
        if (!isEqual(acMsg.compCd_PA, asMsg.compCd_PA)) {
            return true;
        }
        if (!isEqual(acMsg.effFromDt_PA, asMsg.effFromDt_PA)) {
            return true;
        }
        if (!isEqual(acMsg.effThruDt_PA, asMsg.effThruDt_PA)) {
            return true;
        }

        return false;
    }

    public static boolean isChangeForSvc(NMAL7010_BCMsg bcMsg, NMAL7010_BSMsg bsMsg) {
        if (!isEqual(bcMsg.prcRateTpCd_PB, bsMsg.prcRateTpCd_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.mdlNm_PB, bsMsg.mdlNm_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.prcMtrPkgNm_PB, bsMsg.prcMtrPkgNm_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.prcListMdseCd_PB, bsMsg.prcListMdseCd_PB)) {
            // QC#6933 Add
            return true;
        }
        if (!isEqual(bcMsg.prcSvcPlnTpCd_PB, bsMsg.prcSvcPlnTpCd_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.prcSvcContrTpCd_PB, bsMsg.prcSvcContrTpCd_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.mtrLbNm_PB, bsMsg.mtrLbNm_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.minCopyVolCnt_PB, bsMsg.minCopyVolCnt_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.maxCopyVolCnt_PB, bsMsg.maxCopyVolCnt_PB)) {
            return true;
        }
        // 2018/11/17 QC#29147 Mod Start
        if (!isEqual(bcMsg.prcListBandDescTxt_PB, bsMsg.prcListBandDescTxt_PB)) {
            return true;
        }
        // 2018/11/17 QC#29147 Mod End
        if (!isEqual(bcMsg.baseAmt_PB, bsMsg.baseAmt_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.minBaseAmt_PB, bsMsg.minBaseAmt_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.maxBaseAmt_PB, bsMsg.maxBaseAmt_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.cpcAmtRate_PB, bsMsg.cpcAmtRate_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.minCpcAmtRate_PB, bsMsg.minCpcAmtRate_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.maxCpcAmtRate_PB, bsMsg.maxCpcAmtRate_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.termFromMthAot_PB, bsMsg.termFromMthAot_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.termThruMthAot_PB, bsMsg.termThruMthAot_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.prcSvcZoneCd_PB, bsMsg.prcSvcZoneCd_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.mdseCd_PB, bsMsg.mdseCd_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.quotRevAmt_PB, bsMsg.quotRevAmt_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.compCd_PB, bsMsg.compCd_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.effFromDt_PB, bsMsg.effFromDt_PB)) {
            return true;
        }
        if (!isEqual(bcMsg.effThruDt_PB, bsMsg.effThruDt_PB)) {
            return true;
        }
        return false;
    }

    public static boolean isChangeForSvcTier(NMAL7010_CCMsg ccMsg, NMAL7010_CSMsg csMsg) {
        if (!isEqual(ccMsg.mdlNm_PC, csMsg.mdlNm_PC)) {
            return true;
        }
        if (!isEqual(ccMsg.prcSvcTierTpCd_PC, csMsg.prcSvcTierTpCd_PC)) {
            return true;
        }
        if (!isEqual(ccMsg.prcMtrPkgNm_PC, csMsg.prcMtrPkgNm_PC)) {
            return true;
        }
        if (!isEqual(ccMsg.prcSvcPlnTpCd_PC, csMsg.prcSvcPlnTpCd_PC)) {
            return true;
        }
        if (!isEqual(ccMsg.prcSvcContrTpCd_PC, csMsg.prcSvcContrTpCd_PC)) {
            return true;
        }
        if (!isEqual(ccMsg.mtrLbNm_PC, csMsg.mtrLbNm_PC)) {
            return true;
        }
        if (!isEqual(ccMsg.avgCopyVolCnt_PC, csMsg.avgCopyVolCnt_PC)) {
            return true;
        }
        if (!isEqual(ccMsg.minCopyVolCnt_PC, csMsg.minCopyVolCnt_PC)) {
            return true;
        }
        if (!isEqual(ccMsg.maxCopyVolCnt_PC, csMsg.maxCopyVolCnt_PC)) {
            return true;
        }
        // 2018/11/17 QC#29147 Mod Start
        // if (!isEqual(ccMsg.prcListBandCd_PC, csMsg.prcListBandCd_PC)) {
        //     return true;
        // }
        if (!isEqual(ccMsg.prcListBandDescTxt_PC, csMsg.prcListBandDescTxt_PC)) {
            return true;
        }
        // 2018/11/17 QC#29147 Mod End
        if (!isEqual(ccMsg.baseAmt_PC, csMsg.baseAmt_PC)) {
            return true;
        }
        if (!isEqual(ccMsg.minBaseAmt_PC, csMsg.minBaseAmt_PC)) {
            return true;
        }
        if (!isEqual(ccMsg.maxBaseAmt_PC, csMsg.maxBaseAmt_PC)) {
            return true;
        }
        if (!isEqual(ccMsg.cpcAmtRate_PC, csMsg.cpcAmtRate_PC)) {
            return true;
        }
        if (!isEqual(ccMsg.minCpcAmtRate_PC, csMsg.minCpcAmtRate_PC)) {
            return true;
        }
        if (!isEqual(ccMsg.maxCpcAmtRate_PC, csMsg.maxCpcAmtRate_PC)) {
            return true;
        }
        if (!isEqual(ccMsg.termFromMthAot_PC, csMsg.termFromMthAot_PC)) {
            return true;
        }
        if (!isEqual(ccMsg.termThruMthAot_PC, csMsg.termThruMthAot_PC)) {
            return true;
        }
        if (!isEqual(ccMsg.mdseCd_PC, csMsg.mdseCd_PC)) {
            return true;
        }
        if (!isEqual(ccMsg.effFromDt_PC, csMsg.effFromDt_PC)) {
            return true;
        }
        if (!isEqual(ccMsg.effThruDt_PC, csMsg.effThruDt_PC)) {
            return true;
        }
        return false;
    }

    public static boolean isChangeForSvcSpecChrg(NMAL7010_DCMsg dcMsg, NMAL7010_DSMsg dsMsg) {
        if (!isEqual(dcMsg.mdseCd_PD, dsMsg.mdseCd_PD)) {
            return true;
        }
        if (!isEqual(dcMsg.prcAddlChrgTpCd_PD, dsMsg.prcAddlChrgTpCd_PD)) {
            return true;
        }
        if (!isEqual(dcMsg.mktMdlSegCd_PD, dsMsg.mktMdlSegCd_PD)) {
            return true;
        }
        if (!isEqual(dcMsg.mdlNm_PD, dsMsg.mdlNm_PD)) {
            return true;
        }
        if (!isEqual(dcMsg.addlChrgPrcAmt_PD, dsMsg.addlChrgPrcAmt_PD)) {
            return true;
        }
        if (!isEqual(dcMsg.effFromDt_PD, dsMsg.effFromDt_PD)) {
            return true;
        }
        if (!isEqual(dcMsg.effThruDt_PD, dsMsg.effThruDt_PD)) {
            return true;
        }
        return false;
    }

    public static boolean isChangeForSplyPgm(NMAL7010_ECMsg ecMsg, NMAL7010_ESMsg esMsg) {
        if (!isEqual(ecMsg.mdlNm_PE, esMsg.mdlNm_PE)) {
            return true;
        }
        if (!isEqual(ecMsg.prcMtrPkgNm_PE, esMsg.prcMtrPkgNm_PE)) {
            return true;
        }
        if (!isEqual(ecMsg.prcSvcPlnTpCd_PE, esMsg.prcSvcPlnTpCd_PE)) {
            return true;
        }
        if (!isEqual(ecMsg.prcSvcContrTpCd_PE, esMsg.prcSvcContrTpCd_PE)) {
            return true;
        }
        if (!isEqual(ecMsg.mtrLbNm_PE, esMsg.mtrLbNm_PE)) {
            return true;
        }
        if (!isEqual(ecMsg.minCopyVolCnt_PE, esMsg.minCopyVolCnt_PE)) {
            return true;
        }
        if (!isEqual(ecMsg.maxCopyVolCnt_PE, esMsg.maxCopyVolCnt_PE)) {
            return true;
        }
        // 2018/11/17 QC#29147 Mod Start
        // if (!isEqual(ecMsg.prcListBandCd_PE, esMsg.prcListBandCd_PE)) {
        //     return true;
        // }
        if (!isEqual(ecMsg.prcListBandDescTxt_PE, esMsg.prcListBandDescTxt_PE)) {
            return true;
        }
        // 2018/11/17 QC#29147 Mod End
        if (!isEqual(ecMsg.totBaseAmt_PE, esMsg.totBaseAmt_PE)) {
            return true;
        }
        if (!isEqual(ecMsg.splyBaseAmt_PE, esMsg.splyBaseAmt_PE)) {
            return true;
        }
        if (!isEqual(ecMsg.cpcAmtRate_PE, esMsg.cpcAmtRate_PE)) {
            return true;
        }
        if (!isEqual(ecMsg.minCpcAmtRate_PE, esMsg.minCpcAmtRate_PE)) {
            return true;
        }
        if (!isEqual(ecMsg.maxCpcAmtRate_PE, esMsg.maxCpcAmtRate_PE)) {
            return true;
        }
        if (!isEqual(ecMsg.termFromMthAot_PE, esMsg.termFromMthAot_PE)) {
            return true;
        }
        if (!isEqual(ecMsg.termThruMthAot_PE, esMsg.termThruMthAot_PE)) {
            return true;
        }
        if (!isEqual(ecMsg.mdseCd_PE, esMsg.mdseCd_PE)) {
            return true;
        }
        if (!isEqual(ecMsg.prcSvcZoneCd_PE, esMsg.prcSvcZoneCd_PE)) {
            return true;
        }
        if (!isEqual(ecMsg.splyAgmtPlnPk_PE, esMsg.splyAgmtPlnPk_PE)) {
            return true;
        }
        if (!isEqual(ecMsg.effFromDt_PE, esMsg.effFromDt_PE)) {
            return true;
        }
        if (!isEqual(ecMsg.effThruDt_PE, esMsg.effThruDt_PE)) {
            return true;
        }
        return false;
    }

    public static boolean isChangeForLeaseRate(NMAL7010_FCMsg fcMsg, NMAL7010_FSMsg fsMsg) {
        if (!isEqual(fcMsg.prcLeaseCmpyAbbrNm_PF, fsMsg.prcLeaseCmpyAbbrNm_PF)) {
            return true;
        }
        if (!isEqual(fcMsg.dsAcctNum_PF, fsMsg.dsAcctNum_PF)) {
            return true;
        }
        if (!isEqual(fcMsg.prcPgmTpCd_PF, fsMsg.prcPgmTpCd_PF)) {
            return true;
        }
        if (!isEqual(fcMsg.prcEquipTpCd_PF, fsMsg.prcEquipTpCd_PF)) {
            return true;
        }
        if (!isEqual(fcMsg.mdlNm_PF, fsMsg.mdlNm_PF)) {
            return true;
        }
        if (!isEqual(fcMsg.minTotFinAmt_PF, fsMsg.minTotFinAmt_PF)) {
            return true;
        }
        if (!isEqual(fcMsg.maxTotFinAmt_PF, fsMsg.maxTotFinAmt_PF)) {
            return true;
        }
        if (!isEqual(fcMsg.termFromMthAot_PF, fsMsg.termFromMthAot_PF)) {
            return true;
        }
        if (!isEqual(fcMsg.termThruMthAot_PF, fsMsg.termThruMthAot_PF)) {
            return true;
        }
        if (!isEqual(fcMsg.leaseRate_PF, fsMsg.leaseRate_PF)) {
            return true;
        }
        if (!isEqual(fcMsg.effFromDt_PF, fsMsg.effFromDt_PF)) {
            return true;
        }
        if (!isEqual(fcMsg.effThruDt_PF, fsMsg.effThruDt_PF)) {
            return true;
        }
        return false;
    }

    public static boolean isChangeForLeaseRtrnFee(NMAL7010_GCMsg gcMsg, NMAL7010_GSMsg gsMsg) {
        if (!isEqual(gcMsg.prcLeaseCmpyAbbrNm_PG, gsMsg.prcLeaseCmpyAbbrNm_PG)) {
            return true;
        }
        if (!isEqual(gcMsg.svcSegCd_PG, gsMsg.svcSegCd_PG)) {
            return true;
        }
        if (!isEqual(gcMsg.prcInOutRgCd_PG, gsMsg.prcInOutRgCd_PG)) {
            return true;
        }
        if (!isEqual(gcMsg.dstMileAmt_PG, gsMsg.dstMileAmt_PG)) {
            return true;
        }
        if (!isEqual(gcMsg.rtrnFeeAmt_PG, gsMsg.rtrnFeeAmt_PG)) {
            return true;
        }
        if (!isEqual(gcMsg.effFromDt_PG, gsMsg.effFromDt_PG)) {
            return true;
        }
        if (!isEqual(gcMsg.effThruDt_PG, gsMsg.effThruDt_PG)) {
            return true;
        }
        return false;
    }

    public static boolean isChangeForTradeIn(NMAL7010_HCMsg hcMsg, NMAL7010_HSMsg hsMsg) {
        if (!isEqual(hcMsg.mdlNm_PH, hsMsg.mdlNm_PH)) {
            return true;
        }
        if (!isEqual(hcMsg.tiAmt_PH, hsMsg.tiAmt_PH)) {
            return true;
        }
        if (!isEqualForFlg(hcMsg.mtrRngReqFlg_PH, hsMsg.mtrRngReqFlg_PH)) {
            return true;
        }
        if (!isEqual(hcMsg.fromMtrCopyVolCnt_PH, hsMsg.fromMtrCopyVolCnt_PH)) {
            return true;
        }
        if (!isEqual(hcMsg.thruMtrCopyVolCnt_PH, hsMsg.thruMtrCopyVolCnt_PH)) {
            return true;
        }
        if (!isEqual(hcMsg.effFromDt_PH, hsMsg.effFromDt_PH)) {
            return true;
        }
        if (!isEqual(hcMsg.effThruDt_PH, hsMsg.effThruDt_PH)) {
            return true;
        }
        return false;
    }

    private static boolean isEqual(EZDCStringItem item1, EZDSStringItem item2) {
        boolean item1HasVal = ZYPCommonFunc.hasValue(item1);
        boolean item2HasVal = ZYPCommonFunc.hasValue(item2);
        if (!item1HasVal && !item2HasVal) {
            return true;
        }
        if (item1HasVal && item2HasVal && item1.getValue().equals(item2.getValue())) {
            return true;
        }
        return false;
    }

    private static boolean isEqual(EZDCBigDecimalItem item1, EZDSBigDecimalItem item2) {
        boolean item1HasVal = ZYPCommonFunc.hasValue(item1);
        boolean item2HasVal = ZYPCommonFunc.hasValue(item2);
        if (!item1HasVal && !item2HasVal) {
            return true;
        }
        if (item1HasVal && item2HasVal && item1.getValue().compareTo(item2.getValue()) == 0) {
            return true;
        }
        return false;
    }

    private static boolean isEqual(EZDCDateItem item1, EZDSDateItem item2) {
        boolean item1HasVal = ZYPCommonFunc.hasValue(item1);
        boolean item2HasVal = ZYPCommonFunc.hasValue(item2);
        if (!item1HasVal && !item2HasVal) {
            return true;
        }
        if (item1HasVal && item2HasVal && item1.getValue().compareTo(item2.getValue()) == 0) {
            return true;
        }
        return false;
    }

    private static boolean isEqualForFlg(EZDCStringItem item1, EZDSStringItem item2) {
        String flg1 = ZYPCommonFunc.hasValue(item1) ? item1.getValue() : ZYPConstant.FLG_OFF_N;
        String flg2 = ZYPCommonFunc.hasValue(item2) ? item2.getValue() : ZYPConstant.FLG_OFF_N;

        return flg1.equals(flg2);
    }
    /* ------------------------------- */
}
