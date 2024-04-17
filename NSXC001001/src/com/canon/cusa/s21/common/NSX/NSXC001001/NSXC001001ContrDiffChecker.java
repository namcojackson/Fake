package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

import parts.common.EZDItemAttribute;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsg;
import business.db.CONTR_XS_COPYTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_ADDL_CHRGTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BR_ALLOCTMsg;
import business.db.DS_CONTR_CR_CARD_POTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTL_TPTMsg;
import business.db.DS_CONTR_RNW_ESCLTMsg;
import business.db.DS_CONTR_SEG_ALLOCTMsg;
import business.db.SVC_TERM_CONDTMsg;

/**
 * <pre>
 * Contract Difference checker
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/13/2015   Hitachi         K.Yamada        Create          N/A
 * 12/14/2015   Hitachi         T.Tsuchida      Update          QC#1655
 * 03/31/2016   Hitachi         Y.Tsuchimoto    Update          QC#6339
 * 04/09/2018   Hitachi         U.Kim           Update          QC#23378(Sol320)
 *</pre>
 */
public class NSXC001001ContrDiffChecker {

    private static final String[] DS_CONTR_TARGET_ITEM = new String[]{
        "contrVrsnEffFromDt"
        , "contrVrsnEffThruDt"
        , "ccyCd"
        , "leaseCmpyCd"
        , "dsAcctNum"
        , "tocCd"
        , "baseChrgToLeaseCmpyFlg"
        , "usgChrgToLeaseCmpyFlg"
        , "prcAllocByMachQtyFlg"
    };

    private static final String[] DS_CONTR_DTL_BASE_TARGET_ITEM = new String[]{
        "dsContrDtlTpCd"
        , "svcMachMstrPk"
        , "prntDsContrDtlPk"
        , "contrCloDay"
        , "baseBllgCycleCd"
        , "baseBllgTmgCd"
        , "contrBllgDay"
        , "basePrcDealAmt"
        , "contrEffFromDt"
        , "contrEffThruDt"
        , "contrCloDt"
        , "baseBillToCustCd"
        , "trmnTotAmt"
        , "trmnOvrdTotAmt"
        , "supprCrFlg"
        , "basePrcTermDealAmtRate"
        , "svcPgmMdseCd"
    };

    private static final String[] DS_CONTR_DTL_USG_TARGET_ITEM = new String[]{
        "dsContrDtlTpCd"
        , "svcMachMstrPk"
        , "prntDsContrDtlPk"
        , "mtrBllgTmgCd"
        , "contrEffFromDt"
        , "contrEffThruDt"
        , "mtrCloDay"
        , "mtrBllgDay"
        , "trmnTotAmt"
        , "trmnOvrdTotAmt"
        , "supprCrFlg"
    };

    private static final String[] CONTR_PHYS_BLLG_MTR_RELN_TARGET_ITEM = new String[]{
        "contrPhysBllgMtrRelnPk"
        , "dsContrDtlPk"
        , "machMstrPk"
        , "contrMtrMultRate"
        , "bllgMtrLbCd"
        , "dsContrBllgMtrPk"
        , "svcPhysMtrPk"
        , "bllblFlg"
        , "bllgMtrLvlNum"
    };

    private static final String[] DS_CONTR_BLLG_MTR_TARGET_ITEM = new String[]{
        "dsContrBllgMtrPk"
        , "dsContrDtlPk"
        , "bllgMtrLbCd"
        , "bllgMtrBillToCustCd"
        , "bllgMtrBllgCycleCd"
        , "xsChrgTpCd"
        , "bllgMinCnt"
        , "bllgMinAmtRate"
        , "bllgRollOverRatio"
        , "bllgFreeCopyCnt"
        , "rollOverCnt"
        , "intgMdseCd"
    };

    private static final String[] CONTR_XS_COPY_TARGET_ITEM = new String[]{
        "glblCmpyCd"
        , "contrXsCopyPk"
        , "dsContrBllgMtrPk"
        , "xsMtrCopyQty"
        , "xsMtrAmtRate"
        , "xsMtrFirstFlg"
    };

    private static final String[] DS_CONTR_ADDL_CHRG_TARGET_ITEM = new String[]{
        "dsContrPk"
        , "dsContrDtlPk"
        , "addlChrgTpCd"
        , "svcBillByTpCd"
        , "addlChrgInvTpCd"
        , "ccyCd"
        , "addlChrgFlatDealPrcAmt"
        , "addlChrgAplcPct"
        , "printDtlFlg"
        , "bllgCycleCd"
        , "effFromDt"
        , "effThruDt"
        , "trmnDt"
        , "addlChrgInvdFlg"
    };

    private static final String[] DS_CONTR_BR_ALLOC_TARGET_ITEM = new String[]{
        "dsContrPk"
        , "dsContrDtlPk"
        , "svcInvChrgTpCd"
        , "coaBrCd"
        , "prcAllocRate"
    };
    
    private static final String[] DS_CONTR_SEG_ALLOC_TARGET_ITEM = new String[]{
        "dsContrPk"
        , "dsContrDtlPk"
        , "svcInvChrgTpCd"
        , "coaCmpyCd"
        , "coaAfflCd"
        , "coaBrCd"
        , "coaChCd"
        , "coaCcCd"
        , "coaAcctCd"
        , "coaProdCd"
        , "coaProjCd"
        , "coaExtnCd"
        , "prcAllocRate"
        // START 2018/04/09 U.Kim [QC#23378(Sol320), ADD]
        , "prcAllocAmt"
        // END 2018/04/09 U.Kim [QC#23378(Sol320), ADD]
    };

    public static boolean isDifferenceDsContr(DS_CONTRTMsg updTMsg) {

        DS_CONTRTMsg orgTMsg = new DS_CONTRTMsg();
        setValue(orgTMsg.glblCmpyCd, updTMsg.glblCmpyCd);
        setValue(orgTMsg.dsContrPk, updTMsg.dsContrPk);

        orgTMsg = (DS_CONTRTMsg) EZDTBLAccessor.findByKey(orgTMsg);
        if (orgTMsg == null) {
            return true; //this data has been deleted.
        }

        boolean isDifference = false;
        for (String targetItem : DS_CONTR_TARGET_ITEM) {
            if (checkDifference(updTMsg, orgTMsg, targetItem)) {
                isDifference = true;
                break;
            }
        }
        return isDifference;
    }

    public static List<String> getDsContrDiffColumns(DS_CONTRTMsg updTMsg) {

        DS_CONTRTMsg orgTMsg = new DS_CONTRTMsg();
        setValue(orgTMsg.glblCmpyCd, updTMsg.glblCmpyCd);
        setValue(orgTMsg.dsContrPk, updTMsg.dsContrPk);

        orgTMsg = (DS_CONTRTMsg) EZDTBLAccessor.findByKey(orgTMsg);
        if (orgTMsg == null) {
            return null;
        }

        return createDiffColumnList(updTMsg, orgTMsg);
    }

    public static boolean isDifferenceDsContrDtlBase(DS_CONTR_DTLTMsg updTMsg) {

        DS_CONTR_DTLTMsg orgTMsg = new DS_CONTR_DTLTMsg();
        setValue(orgTMsg.glblCmpyCd, updTMsg.glblCmpyCd);
        setValue(orgTMsg.dsContrDtlPk, updTMsg.dsContrDtlPk);

        orgTMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(orgTMsg);
        if (orgTMsg == null) {
            return true; //this data has been deleted.
        }

        boolean isDifference = false;
        for (String targetItem : DS_CONTR_DTL_BASE_TARGET_ITEM) {
            if (checkDifference(updTMsg, orgTMsg, targetItem)) {
                isDifference = true;
                break;
            }
        }
        return isDifference;
    }

    public static boolean isDifferenceDsContrDtlUsg(DS_CONTR_DTLTMsg updTMsg) {

        DS_CONTR_DTLTMsg orgTMsg = new DS_CONTR_DTLTMsg();
        setValue(orgTMsg.glblCmpyCd, updTMsg.glblCmpyCd);
        setValue(orgTMsg.dsContrDtlPk, updTMsg.dsContrDtlPk);

        orgTMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(orgTMsg);
        if (orgTMsg == null) {
            return true; //this data has been deleted.
        }

        boolean isDifference = false;
        for (String targetItem : DS_CONTR_DTL_USG_TARGET_ITEM) {
            if (checkDifference(updTMsg, orgTMsg, targetItem)) {
                isDifference = true;
                break;
            }
        }
        return isDifference;
    }

    public static List<String> getDsContrDtlDiffColumns(DS_CONTR_DTLTMsg updTMsg) {

        DS_CONTR_DTLTMsg orgTMsg = new DS_CONTR_DTLTMsg();
        setValue(orgTMsg.glblCmpyCd, updTMsg.glblCmpyCd);
        setValue(orgTMsg.dsContrDtlPk, updTMsg.dsContrDtlPk);

        orgTMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(orgTMsg);
        if (orgTMsg == null) {
            return null;
        }

        return createDiffColumnList(updTMsg, orgTMsg);
    }

    public static boolean isDifferenceContrPhysBllgMtrReln(CONTR_PHYS_BLLG_MTR_RELNTMsg updTMsg) {

        CONTR_PHYS_BLLG_MTR_RELNTMsg orgTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        setValue(orgTMsg.glblCmpyCd, updTMsg.glblCmpyCd);
        setValue(orgTMsg.contrPhysBllgMtrRelnPk, updTMsg.contrPhysBllgMtrRelnPk);

        orgTMsg = (CONTR_PHYS_BLLG_MTR_RELNTMsg) EZDTBLAccessor.findByKey(orgTMsg);
        if (orgTMsg == null) {
            return true; //this data has been deleted.
        }

        boolean isDifference = false;
        for (String targetItem : CONTR_PHYS_BLLG_MTR_RELN_TARGET_ITEM) {
            if (checkDifference(updTMsg, orgTMsg, targetItem)) {
                isDifference = true;
                break;
            }
        }
        return isDifference;
    }

    public static List<String> getContrPhysRelnDiffColumns(CONTR_PHYS_BLLG_MTR_RELNTMsg updTMsg) {

        CONTR_PHYS_BLLG_MTR_RELNTMsg orgTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        setValue(orgTMsg.glblCmpyCd, updTMsg.glblCmpyCd);
        setValue(orgTMsg.contrPhysBllgMtrRelnPk, updTMsg.contrPhysBllgMtrRelnPk);

        orgTMsg = (CONTR_PHYS_BLLG_MTR_RELNTMsg) EZDTBLAccessor.findByKey(orgTMsg);
        if (orgTMsg == null) {
            return null;
        }

        return createDiffColumnList(updTMsg, orgTMsg);
    }

    public static boolean isDifferenceDsContrBllgMtr(DS_CONTR_BLLG_MTRTMsg updTMsg) {

        DS_CONTR_BLLG_MTRTMsg orgTMsg = new DS_CONTR_BLLG_MTRTMsg();
        setValue(orgTMsg.glblCmpyCd, updTMsg.glblCmpyCd);
        setValue(orgTMsg.dsContrBllgMtrPk, updTMsg.dsContrBllgMtrPk);

        orgTMsg = (DS_CONTR_BLLG_MTRTMsg) EZDTBLAccessor.findByKey(orgTMsg);
        if (orgTMsg == null) {
            return true; //this data has been deleted.
        }

        boolean isDifference = false;
        for (String targetItem : DS_CONTR_BLLG_MTR_TARGET_ITEM) {
            if (checkDifference(updTMsg, orgTMsg, targetItem)) {
                isDifference = true;
                break;
            }
        }
        return isDifference;
    }

    public static List<String> getContrBllgMtrDiffColumns(DS_CONTR_BLLG_MTRTMsg updTMsg) {

        DS_CONTR_BLLG_MTRTMsg orgTMsg = new DS_CONTR_BLLG_MTRTMsg();
        setValue(orgTMsg.glblCmpyCd, updTMsg.glblCmpyCd);
        setValue(orgTMsg.dsContrBllgMtrPk, updTMsg.dsContrBllgMtrPk);

        orgTMsg = (DS_CONTR_BLLG_MTRTMsg) EZDTBLAccessor.findByKey(orgTMsg);
        if (orgTMsg == null) {
            return null;
        }

        return createDiffColumnList(updTMsg, orgTMsg);
    }

    public static boolean isDifferencContrXsCopy(CONTR_XS_COPYTMsg updTMsg) {

        CONTR_XS_COPYTMsg orgTMsg = new CONTR_XS_COPYTMsg();
        setValue(orgTMsg.glblCmpyCd, updTMsg.glblCmpyCd);
        setValue(orgTMsg.contrXsCopyPk, updTMsg.contrXsCopyPk);

        orgTMsg = (CONTR_XS_COPYTMsg) EZDTBLAccessor.findByKey(orgTMsg);
        if (orgTMsg == null) {
            return true; //this data has been deleted.
        }

        boolean isDifference = false;
        for (String targetItem : CONTR_XS_COPY_TARGET_ITEM) {
            if (checkDifference(updTMsg, orgTMsg, targetItem)) {
                isDifference = true;
                break;
            }
        }
        return isDifference;
    }

    public static List<String> getXsCopyDiffColumns(CONTR_XS_COPYTMsg updTMsg) {

        CONTR_XS_COPYTMsg orgTMsg = new CONTR_XS_COPYTMsg();
        setValue(orgTMsg.glblCmpyCd, updTMsg.glblCmpyCd);
        setValue(orgTMsg.contrXsCopyPk, updTMsg.contrXsCopyPk);

        orgTMsg = (CONTR_XS_COPYTMsg) EZDTBLAccessor.findByKey(orgTMsg);
        if (orgTMsg == null) {
            return null;
        }

        return createDiffColumnList(updTMsg, orgTMsg);
    }

    public static boolean isDifferenceDsContrAddlChrg(DS_CONTR_ADDL_CHRGTMsg updTMsg) {

        DS_CONTR_ADDL_CHRGTMsg orgTMsg = new DS_CONTR_ADDL_CHRGTMsg();
        setValue(orgTMsg.glblCmpyCd, updTMsg.glblCmpyCd);
        setValue(orgTMsg.dsContrAddlChrgPk, updTMsg.dsContrAddlChrgPk);

        orgTMsg = (DS_CONTR_ADDL_CHRGTMsg) EZDTBLAccessor.findByKey(orgTMsg);
        if (orgTMsg == null) {
            return true; //this data has been deleted.
        }

        boolean isDifference = false;
        for (String targetItem : DS_CONTR_ADDL_CHRG_TARGET_ITEM) {
            if (checkDifference(updTMsg, orgTMsg, targetItem)) {
                isDifference = true;
                break;
            }
        }
        return isDifference;
    }

    public static boolean isDifferenceDsContrBrAlloc(DS_CONTR_BR_ALLOCTMsg updTMsg) {

        DS_CONTR_BR_ALLOCTMsg orgTMsg = new DS_CONTR_BR_ALLOCTMsg();
        setValue(orgTMsg.glblCmpyCd, updTMsg.glblCmpyCd);
        setValue(orgTMsg.dsContrBrAllocPk, updTMsg.dsContrBrAllocPk);

        orgTMsg = (DS_CONTR_BR_ALLOCTMsg) EZDTBLAccessor.findByKey(orgTMsg);
        if (orgTMsg == null) {
            return true; //this data has been deleted.
        }

        boolean isDifference = false;
        for (String targetItem : DS_CONTR_BR_ALLOC_TARGET_ITEM) {
            if (checkDifference(updTMsg, orgTMsg, targetItem)) {
                isDifference = true;
                break;
            }
        }
        return isDifference;
    }

    public static boolean isDifferenceDsContrSegAlloc(DS_CONTR_SEG_ALLOCTMsg updTMsg) {

        DS_CONTR_SEG_ALLOCTMsg orgTMsg = new DS_CONTR_SEG_ALLOCTMsg();
        setValue(orgTMsg.glblCmpyCd, updTMsg.glblCmpyCd);
        setValue(orgTMsg.dsContrSegAllocPk, updTMsg.dsContrSegAllocPk);

        orgTMsg = (DS_CONTR_SEG_ALLOCTMsg) EZDTBLAccessor.findByKey(orgTMsg);
        if (orgTMsg == null) {
            return true; //this data has been deleted.
        }

        boolean isDifference = false;
        for (String targetItem : DS_CONTR_SEG_ALLOC_TARGET_ITEM) {
            if (checkDifference(updTMsg, orgTMsg, targetItem)) {
                isDifference = true;
                break;
            }
        }
        return isDifference;
    }

    public static List<String> getContrCrCardDiffColumns(DS_CONTR_CR_CARD_POTMsg updTMsg) {

        DS_CONTR_CR_CARD_POTMsg orgTMsg = new DS_CONTR_CR_CARD_POTMsg();
        setValue(orgTMsg.glblCmpyCd, updTMsg.glblCmpyCd);
        setValue(orgTMsg.dsContrCrCardPoPk, updTMsg.dsContrCrCardPoPk);

        orgTMsg = (DS_CONTR_CR_CARD_POTMsg) EZDTBLAccessor.findByKey(orgTMsg);
        if (orgTMsg == null) {
            return null;
        }

        return createDiffColumnList(updTMsg, orgTMsg);
    }

    public static List<String> getContrAddlChrgDiffColumns(DS_CONTR_ADDL_CHRGTMsg updTMsg) {

        DS_CONTR_ADDL_CHRGTMsg orgTMsg = new DS_CONTR_ADDL_CHRGTMsg();
        setValue(orgTMsg.glblCmpyCd, updTMsg.glblCmpyCd);
        setValue(orgTMsg.dsContrAddlChrgPk, updTMsg.dsContrAddlChrgPk);

        orgTMsg = (DS_CONTR_ADDL_CHRGTMsg) EZDTBLAccessor.findByKey(orgTMsg);
        if (orgTMsg == null) {
            return null;
        }

        return createDiffColumnList(updTMsg, orgTMsg);
    }

    public static List<String> getContrRnwEsclDiffColumns(DS_CONTR_RNW_ESCLTMsg updTMsg) {

        DS_CONTR_RNW_ESCLTMsg orgTMsg = new DS_CONTR_RNW_ESCLTMsg();
        setValue(orgTMsg.glblCmpyCd, updTMsg.glblCmpyCd);
        setValue(orgTMsg.dsContrRnwEsclPk, updTMsg.dsContrRnwEsclPk);

        orgTMsg = (DS_CONTR_RNW_ESCLTMsg) EZDTBLAccessor.findByKey(orgTMsg);
        if (orgTMsg == null) {
            return null;
        }

        return createDiffColumnList(updTMsg, orgTMsg);
    }

    public static List<String> getSvcTermCondDiffColumns(SVC_TERM_CONDTMsg updTMsg) {

        SVC_TERM_CONDTMsg orgTMsg = new SVC_TERM_CONDTMsg();
        setValue(orgTMsg.glblCmpyCd, updTMsg.glblCmpyCd);
        setValue(orgTMsg.svcTermCondPk, updTMsg.svcTermCondPk);

        orgTMsg = (SVC_TERM_CONDTMsg) EZDTBLAccessor.findByKey(orgTMsg);
        if (orgTMsg == null) {
            return null;
        }

        return createDiffColumnList(updTMsg, orgTMsg);
    }

    private static List<String> createDiffColumnList(EZDTMsg updTMsg, EZDTMsg orgTMsg) {

        List<String> diffColumnList = new ArrayList<String>();
        @SuppressWarnings("unchecked")
        List<String>[] columnLists = updTMsg.getSelectColumnList();
        List<String> columnList = columnLists[0];
        for (String targetItem : columnList) {
            if (checkDifference(updTMsg, orgTMsg, targetItem)) {
                diffColumnList.add(targetItem);
            }
        }
        return diffColumnList;

    }

    private static boolean checkDifference(EZDTMsg updTMsg, EZDTMsg orgTMsg, String targetItem) {
        Object updData = null;
        Object orgData = null;

        if (updTMsg.getAttr(targetItem) == null) {
            return false;
        }
        int type = updTMsg.getAttr(targetItem).getType();
        if (type == EZDItemAttribute.TYPE_SEISU_SYOSU) {
            updData = updTMsg.getValueBigDecimal(targetItem, 1);
            orgData = orgTMsg.getValueBigDecimal(targetItem, 1);
        } else {
            updData = updTMsg.getValueString(targetItem, 1);
            orgData = orgTMsg.getValueString(targetItem, 1);
        }

        if (isDiffValue(updData, orgData)) {
            return true;
        }
        return false;
    }

    private static boolean isDiffValue(Object val1, Object val2) {
        if (val1 == null && val2 == null) {
            return false;
        }
        if (val1 == null || val2 == null) {
            return true;
        }
        if (val1 instanceof String) {
            if (val1.equals(val2)) {
                return false;
            }
        }
        if (val1 instanceof BigDecimal) {
            if (((BigDecimal) val1).compareTo((BigDecimal) val2) == 0) {
                return false;
            }
        }
        return true;
    }

    public static List<BigDecimal> getDiffContrListForBase(NSXC001001ContrDiffCheckerBean bean) {
        List<BigDecimal> baseDsContrDtlPkList = new ArrayList<BigDecimal>();

        //contract header
        if (isDifferenceDsContr(bean.getDsContr())) {
            addAllDetailForBase(bean, baseDsContrDtlPkList);
            return baseDsContrDtlPkList;
        }

        //additional charge (header lv)
        boolean hasDiffHdrLvAddlChrg = false;
        for (DS_CONTR_ADDL_CHRGTMsg addlChrg : bean.getDsContrAddlChrgList()) {
            if (hasValue(addlChrg.dsContrDtlPk)) {
                continue;
            }
            if (isDifferenceDsContrAddlChrg(addlChrg)) {
                addAllDetailForBase(bean, baseDsContrDtlPkList);
                hasDiffHdrLvAddlChrg = true;
                break;
            }
        }
        if (hasDiffHdrLvAddlChrg) {
            return baseDsContrDtlPkList;
        }

        //branch allocation (header lv)
        boolean hasDiffHdrLvBrAlloc = false;
        for (DS_CONTR_BR_ALLOCTMsg brAlloc : bean.getDsContrBrAllocList()) {
            if (hasValue(brAlloc.dsContrDtlPk)) {
                continue;
            }
            if (isDifferenceDsContrBrAlloc(brAlloc)) {
                addAllDetailForBase(bean, baseDsContrDtlPkList);
                hasDiffHdrLvBrAlloc = true;
                break;
            }
        }
        if (hasDiffHdrLvBrAlloc) {
            return baseDsContrDtlPkList;
        }

        //segment allocation (header lv)
        boolean hasDiffHdrLvSeAlloc = false;
        for (DS_CONTR_SEG_ALLOCTMsg segAlloc : bean.getDsContrSegAllocList()) {
            if (hasValue(segAlloc.dsContrDtlPk)) {
                continue;
            }
            if (isDifferenceDsContrSegAlloc(segAlloc)) {
                addAllDetailForBase(bean, baseDsContrDtlPkList);
                hasDiffHdrLvSeAlloc = true;
                break;
            }
        }
        if (hasDiffHdrLvSeAlloc) {
            return baseDsContrDtlPkList;
        }

        //contract detail
        for (DS_CONTR_DTLTMsg contrDtl : bean.getDsContrDtlList()) {
            if (!isBaseChrg(contrDtl)) {
                continue;
            }
            if (isDifferenceDsContrDtlBase(contrDtl)) {
                baseDsContrDtlPkList.add(contrDtl.dsContrDtlPk.getValue());
                continue;
            }
        }

        //branch allocation
        for (DS_CONTR_BR_ALLOCTMsg brAlloc : bean.getDsContrBrAllocList()) {
            if (!hasValue(brAlloc.dsContrDtlPk)) {
                continue;
            }
            if (!isBaseChrg(brAlloc.glblCmpyCd.getValue(), brAlloc.dsContrDtlPk.getValue())) {
                continue;
            }
            if (isDifferenceDsContrBrAlloc(brAlloc)) {
                baseDsContrDtlPkList.add(brAlloc.dsContrDtlPk.getValue());
                continue;
            }
        }

        //segment allocation
        for (DS_CONTR_SEG_ALLOCTMsg segAlloc : bean.getDsContrSegAllocList()) {
            if (!hasValue(segAlloc.dsContrDtlPk)) {
                continue;
            }
            if (!isBaseChrg(segAlloc.glblCmpyCd.getValue(), segAlloc.dsContrDtlPk.getValue())) {
                continue;
            }
            if (isDifferenceDsContrSegAlloc(segAlloc)) {
                baseDsContrDtlPkList.add(segAlloc.dsContrDtlPk.getValue());
                continue;
            }
        }

        return baseDsContrDtlPkList;
    }

    public static List<BigDecimal> getDiffContrListForUsg(NSXC001001ContrDiffCheckerBean bean) {
        List<BigDecimal> usgDsContrBllgMtrPkList = new ArrayList<BigDecimal>();

        //contract header
        if (isDifferenceDsContr(bean.getDsContr())) {
            addAllBllgMtrForUsg(bean, usgDsContrBllgMtrPkList);
            return usgDsContrBllgMtrPkList;
        }

        //additional charge (header lv)
        boolean hasDiffHdrLvAddlChrg = false;
        for (DS_CONTR_ADDL_CHRGTMsg addlChrg : bean.getDsContrAddlChrgList()) {
            if (hasValue(addlChrg.dsContrDtlPk)) {
                continue;
            }
            if (isDifferenceDsContrAddlChrg(addlChrg)) {
                addAllBllgMtrForUsg(bean, usgDsContrBllgMtrPkList);
                hasDiffHdrLvAddlChrg = true;
                break;
            }
        }
        if (hasDiffHdrLvAddlChrg) {
            return usgDsContrBllgMtrPkList;
        }

        //branch allocation (header lv)
        boolean hasDiffHdrLvBrAlloc = false;
        for (DS_CONTR_BR_ALLOCTMsg brAlloc : bean.getDsContrBrAllocList()) {
            if (hasValue(brAlloc.dsContrDtlPk)) {
                continue;
            }
            if (isDifferenceDsContrBrAlloc(brAlloc)) {
                addAllBllgMtrForUsg(bean, usgDsContrBllgMtrPkList);
                hasDiffHdrLvBrAlloc = true;
                break;
            }
        }
        if (hasDiffHdrLvBrAlloc) {
            return usgDsContrBllgMtrPkList;
        }

        //segment allocation (header lv)
        boolean hasDiffHdrLvSeAlloc = false;
        for (DS_CONTR_SEG_ALLOCTMsg segAlloc : bean.getDsContrSegAllocList()) {
            if (hasValue(segAlloc.dsContrDtlPk)) {
                continue;
            }
            if (isDifferenceDsContrSegAlloc(segAlloc)) {
                addAllBllgMtrForUsg(bean, usgDsContrBllgMtrPkList);
                hasDiffHdrLvSeAlloc = true;
                break;
            }
        }
        if (hasDiffHdrLvSeAlloc) {
            return usgDsContrBllgMtrPkList;
        }

        //contract detail
        for (DS_CONTR_DTLTMsg contrDtl : bean.getDsContrDtlList()) {
            if (!isUsgChrg(contrDtl)) {
                continue;
            }
            if (isDifferenceDsContrDtlUsg(contrDtl)) {
                addBllgMtrForUsgByContrDtlPk(bean, usgDsContrBllgMtrPkList, contrDtl.dsContrDtlPk.getValue());
                continue;
            }
        }

        //contract billing meter
        for (DS_CONTR_BLLG_MTRTMsg bllgMtr : bean.getDsContrBllgMtrList()) {
            if (isDifferenceDsContrBllgMtr(bllgMtr)) {
                usgDsContrBllgMtrPkList.add(bllgMtr.dsContrBllgMtrPk.getValue());
                continue;
            }
        }

        //physical billing meter relation
        for (CONTR_PHYS_BLLG_MTR_RELNTMsg reln : bean.getContrPhysBllgMtrRelnList()) {
            if (isDifferenceContrPhysBllgMtrReln(reln)) {
                addBllgMtrForUsgByContrDtlPk(bean, usgDsContrBllgMtrPkList, reln.dsContrDtlPk.getValue());
                continue;
            }
        }

        //excess copy
        for (CONTR_XS_COPYTMsg xsCopy : bean.getContrXsCopyList()) {
            if (isDifferencContrXsCopy(xsCopy)) {
                usgDsContrBllgMtrPkList.add(xsCopy.dsContrBllgMtrPk.getValue());
                continue;
            }
        }

        //branch allocation
        for (DS_CONTR_BR_ALLOCTMsg brAlloc : bean.getDsContrBrAllocList()) {
            if (!hasValue(brAlloc.dsContrDtlPk)) {
                continue;
            }
            if (!isUsgChrg(brAlloc.glblCmpyCd.getValue(), brAlloc.dsContrDtlPk.getValue())) {
                continue;
            }
            if (isDifferenceDsContrBrAlloc(brAlloc)) {
                addBllgMtrForUsgByContrDtlPk(bean, usgDsContrBllgMtrPkList, brAlloc.dsContrDtlPk.getValue());
                continue;
            }
        }

        //segment allocation
        for (DS_CONTR_SEG_ALLOCTMsg segAlloc : bean.getDsContrSegAllocList()) {
            if (!hasValue(segAlloc.dsContrDtlPk)) {
                continue;
            }
            if (!isUsgChrg(segAlloc.glblCmpyCd.getValue(), segAlloc.dsContrDtlPk.getValue())) {
                continue;
            }
            if (isDifferenceDsContrSegAlloc(segAlloc)) {
                addBllgMtrForUsgByContrDtlPk(bean, usgDsContrBllgMtrPkList, segAlloc.dsContrDtlPk.getValue());
                continue;
            }
        }

        return usgDsContrBllgMtrPkList;
    }

    public static void addAllDetailForBase(NSXC001001ContrDiffCheckerBean bean, List<BigDecimal> baseDsContrDtlPkList) {
        for (DS_CONTR_DTLTMsg contrDtl : bean.getDsContrDtlList()) {
            if (isBaseChrg(contrDtl)) {
                baseDsContrDtlPkList.add(contrDtl.dsContrDtlPk.getValue());
            }
        }
    }

    public static void addAllBllgMtrForUsg(NSXC001001ContrDiffCheckerBean bean, List<BigDecimal> usgDsContrBllgMtrPkList) {
        for (DS_CONTR_BLLG_MTRTMsg bllgMtr : bean.getDsContrBllgMtrList()) {
            if (isUsgChrg(bllgMtr.glblCmpyCd.getValue(), bllgMtr.dsContrDtlPk.getValue())) {
                usgDsContrBllgMtrPkList.add(bllgMtr.dsContrBllgMtrPk.getValue());
            }
        }
    }

    public static void addBllgMtrForUsgByContrDtlPk(NSXC001001ContrDiffCheckerBean bean, List<BigDecimal> usgDsContrBllgMtrPkList, BigDecimal dsContrDtlPk) {
        for (DS_CONTR_BLLG_MTRTMsg bllgMtr : bean.getDsContrBllgMtrList()) {
            if (dsContrDtlPk.compareTo(bllgMtr.dsContrDtlPk.getValue()) == 0) {
                usgDsContrBllgMtrPkList.add(bllgMtr.dsContrBllgMtrPk.getValue());
            }
        }
    }

    private static boolean isBaseChrg(DS_CONTR_DTLTMsg contrDtl) {
        DS_CONTR_DTL_TPTMsg dtlTp = new DS_CONTR_DTL_TPTMsg();
        setValue(dtlTp.glblCmpyCd, contrDtl.glblCmpyCd);
        setValue(dtlTp.dsContrDtlTpCd, contrDtl.dsContrDtlTpCd);
        dtlTp = (DS_CONTR_DTL_TPTMsg) S21CodeTableAccessor.findByKey(dtlTp);
        return FLG_ON_Y.equals(dtlTp.baseChrgFlg.getValue());
    }

    private static boolean isBaseChrg(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg contrDtl = new DS_CONTR_DTLTMsg();
        setValue(contrDtl.glblCmpyCd, glblCmpyCd);
        setValue(contrDtl.dsContrDtlPk, dsContrDtlPk);
        contrDtl = (DS_CONTR_DTLTMsg) S21CodeTableAccessor.findByKey(contrDtl);
        return isBaseChrg(contrDtl);
    }

    private static boolean isUsgChrg(DS_CONTR_DTLTMsg contrDtl) {
        DS_CONTR_DTL_TPTMsg dtlTp = new DS_CONTR_DTL_TPTMsg();
        setValue(dtlTp.glblCmpyCd, contrDtl.glblCmpyCd);
        setValue(dtlTp.dsContrDtlTpCd, contrDtl.dsContrDtlTpCd);
        dtlTp = (DS_CONTR_DTL_TPTMsg) S21CodeTableAccessor.findByKey(dtlTp);
        return FLG_ON_Y.equals(dtlTp.usgChrgFlg.getValue());
    }

    private static boolean isUsgChrg(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg contrDtl = new DS_CONTR_DTLTMsg();
        setValue(contrDtl.glblCmpyCd, glblCmpyCd);
        setValue(contrDtl.dsContrDtlPk, dsContrDtlPk);
        contrDtl = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(contrDtl);
        return isUsgChrg(contrDtl);
    }

}
