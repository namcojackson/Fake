package business.parts;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import parts.common.EZDTMsg;
import business.db.AJE_INV_ACCT_DISTTMsg;
import business.db.AJE_INV_ACCT_DIST_ERRTMsg;
import business.db.JRNL_ENTRYTMsg;
import business.parts.NFACommonJrnlEntryConstant.CoaSegment;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_FM_IND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_GENL_IND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INTFC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_LINE_IND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_DEF_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DFRD_ACCTG_RULE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_INVTY_IND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_SPL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ALLOC_IND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * This batch journalizes front transactions of LOAN DEPRECIATION.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/14/2009   CSA             H.Naoi          Create          N/A
 * 05/13/2013   CSAI            K.Uramori       MOD             Modification for DS.
 * 04/16/2014   Fujitsu         T.Tozuka        Mod             CSA AJE#84
 * 10/15/2015   CSAI            K.Uramori       Mod             CSA Modification
 * 01/15/2016   CSAI            K.Uramori       Mod             Add function
 * 01/26/2016   CSAI            K.Uramori       Mod             RTL_WH_ACCT -> COA_PROJ_ACCT & Default COA Values
 * 11/28/2016   CSAI            K.Uramori       Mod             QC#16075 If COA values can not be retrieved set default value and notify IT operation.
 * 11/29/2016   Fujitsu         S.Fujita        Mod             QC#16075 Add Mail Send Process
 * 11/20/2017   CITS            K.Ogino         Mod             QC#22290
 * 03/14/2018   Hitachi         E.Kameishi      Mod             QC#23689
 * 04/23/2018   Hitachi         E.Kameishi      Mod             QC#23378
 * 08/28/2018   Hitachi         E.Kameishi      Mod             QC#27945
 * 01/07/2018   Hitachi         E.Kameishi      Mod             QC#29734
 * 05/21/2018   Hitachi         E.Kameishi      Mod             QC#50407
 * 10/01/2019   Fujitsu         T.Murai         Mod             QC#53639
 * 11/14/2019   Hitachi         H.Umeda         Mod             QC#54357
 * </pre>
 */
public class NFACommonJrnlEntry implements NFACommonJrnlEntryConstant, ZYPConstant {

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;

    // START 2018/03/12 E.Kameishi [QC#23689,ADD]
    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;
    // END   2018/03/12 E.Kameishi [QC#23689,ADD]

    /** Map of System Source */
    private static List<Map> sysSrcMap;

    private DefCoaValues defCoa = null;
    
    private String outOfTrtryBr = null;
    
    private String emailSntFlg = null;

    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NFACommonJrnlEntry() {
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        // START 2018/03/12 E.Kameishi [QC#23689,ADD]
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // END   2018/03/12 E.Kameishi [QC#23689,ADD]
    }

    /**
     * <pre>
     *  Get AJE Interface Key List that is not journalized from AJE Interface Control.
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @param ajeIntfcTpCd AJE Interface Type Code
     * @return List<Map> ssmResForGetAjeIntfcCtrlKeyNotJrnlized
     */
    @SuppressWarnings("unchecked")
    public List<Map> getAjeIntfcKeyListNotJrnlized(String glblCmpyCd, String ajeIntfcTpCd) {

        // ** Get All Transaction Pattern in AJE Loan Interface. **
        Map<String, String> queryParamToGetAjeIntfcKeyListNotJrnlized = new HashMap<String, String>();
        queryParamToGetAjeIntfcKeyListNotJrnlized.put("glblCmpyCd", glblCmpyCd);
        queryParamToGetAjeIntfcKeyListNotJrnlized.put("ajeIntfcTpCd", ajeIntfcTpCd);
        queryParamToGetAjeIntfcKeyListNotJrnlized.put("jrnlCpltFlg", FLG_OFF_N);

        List<Map> ssmResForGetAjeIntfcCtrlKeyNotJrnlized = (List<Map>) ssmBatchClient.queryObjectList("getAjeIntfcCtrlListNotJrnlized", queryParamToGetAjeIntfcKeyListNotJrnlized);

        return ssmResForGetAjeIntfcCtrlKeyNotJrnlized;
    }

    /**
     * <pre>
     *  Get Target AJE Patterns.
     * </pre>
     * @param ssmResForGetIntfcTrxPtrn AJE Interface Transaction
     * Pattern.
     * @param glblCmpyCd Global Company Code
     * @return List<Map> ssmResForGetAjePtrn
     */
    @SuppressWarnings("unchecked")
    public List<Map> getAjePtrn(List<Map> ssmResForGetIntfcTrxPtrn, String glblCmpyCd, String ajeIntfcTpCd) {

        // ** Get Target AJE Patterns. **
        Map<String, Object> queryParamToGetAjePtrn = new HashMap<String, Object>();
        queryParamToGetAjePtrn.put("glblCmpyCd", glblCmpyCd);
        queryParamToGetAjePtrn.put("ajeLinkFlg", FLG_ON_Y);
        queryParamToGetAjePtrn.put("drTpCd", DR_CR_TP_CD_VALUE_D);
        queryParamToGetAjePtrn.put("crTpCd", DR_CR_TP_CD_VALUE_C);
        queryParamToGetAjePtrn.put("ajeIntfcTpCd", ajeIntfcTpCd);
        queryParamToGetAjePtrn.put("jrnlSrcNmLen", JRNL_SRC_NM_LEN);

        // Add AJE Id List from AJE Interface Table.
        Set<String> ajeIdList = new HashSet<String>();
        for (Map<String, String> rsGetIntfcTrxPtrn : ssmResForGetIntfcTrxPtrn) {
            String ajeId = generateAjeId(rsGetIntfcTrxPtrn.get(SYS_SRC_CD), rsGetIntfcTrxPtrn.get(TRX_CD), rsGetIntfcTrxPtrn.get(TRX_RSN_CD));

            ajeIdList.add(ajeId);

            // add aje_id with sys_src_cd = 'ZZZ', too
            ajeId = generateAjeId(SYS_SRC_CD_DEFAULT, rsGetIntfcTrxPtrn.get(TRX_CD), rsGetIntfcTrxPtrn.get(TRX_RSN_CD));

            ajeIdList.add(ajeId);
        }
        queryParamToGetAjePtrn.put("ajeIdList", ajeIdList);

        List<Map> ssmResForGetAjePtrn = (List<Map>) ssmBatchClient.queryObjectList("getAjePtrn", queryParamToGetAjePtrn);

        sysSrcMap = getEssMapNotReqSysSrc(glblCmpyCd);

        return ssmResForGetAjePtrn;
    }

    /**
     * <pre>
     *  Get Target AJE Patterns with its qty.
     * </pre>
     * @param trxPtrns AJE Interface Transaction Pattern.
     * @param glblCmpyCd Global Company Code
     * @return List<Map> ssmResForGetAjePtrn
     */
    @SuppressWarnings("unchecked")
    public List<Map> getCountedAjeIdByIndType(List<Map> trxPtrns, String glblCmpyCd) {

        // ** Get Target AJE Patterns. **
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", glblCmpyCd);
        map.put("ajeLinkFlg", FLG_ON_Y);
        // Add AJE Id List from AJE Interface Table.
        List<String> ajeIdList = new ArrayList<String>();
        for (Map<String, String> trxPtn : trxPtrns) {
            String ajeId = generateAjeId(trxPtn.get(SYS_SRC_CD), trxPtn.get(TRX_CD), trxPtn.get(TRX_RSN_CD));
            ajeIdList.add(ajeId);
        }
        map.put("ajeIdList", ajeIdList);

        List<Map> ssmResForGetAjePtrn = (List<Map>) ssmBatchClient.queryObjectList("getCountedAjeIdByIndType", map);

        return ssmResForGetAjePtrn;
    }

    /**
     * <pre>
     *  Gererate AJE ID by concatenating SYS_SRC_CD, TRX_CD and TRX_RSN_CD.
     * </pre>
     * @param sysSrcCd System Source Code
     * @param trxCd Transaction Code
     * @param trxRsnCd Transaction Reason Code
     * @return String ajeId
     */
    public String generateAjeId(String sysSrcCd, String trxCd, String trxRsnCd) {

        // TODO Need this check logic?
        // if (sysSrcCd.length()==0 || sysSrcCd == null ||
        // trxCd.length()==0 || trxCd == null ||
        // trxRsnCd.length()==0 || trxRsnCd == null){
        // return null;
        // } else {
        String ajeId = sysSrcCd + "-" + trxCd + "-" + trxRsnCd;
        return ajeId;
        // }
    }

    /**
     * <pre>
     *  Get Currency Information by Global Company Code.
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @return Map<String, String> ssmResForGetCcy
     */
    @SuppressWarnings("unchecked")
    public Map getCcy(String glblCmpyCd) {

        // ** Get Get Currency Information by Global Company Code. **
        Map<String, String> queryParamToGetCcy = new HashMap<String, String>();
        queryParamToGetCcy.put("glblCmpyCd", glblCmpyCd);

        Map ssmResForGetCcy = (Map) ssmBatchClient.queryObject("getCcy", queryParamToGetCcy);

        return ssmResForGetCcy;
    }

    /**
     * <pre>
     *  Get Standard Currency Code from global company master
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @return String
     */
    @SuppressWarnings("unchecked")
    public String getStdCcy(String glblCmpyCd) {

        Map ccy = getCcy(glblCmpyCd);

        return checkNull(ccy.get("FUNC_CCY_CD").toString());
    }

    /**
     * <pre>
     *  Get System Source Code list for which ESS mapping should not apply
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @return Map<String, String> ssmResForGetCcy
     */
    @SuppressWarnings("unchecked")
    public List<Map> getEssMapNotReqSysSrc(String glblCmpyCd) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("flgN", ZYPConstant.FLG_OFF_N);

        List<Map> ssmRes = (List<Map>) ssmBatchClient.queryObjectList("getEssMapSysSrc", param);

        return ssmRes;
    }

    /**
     * <pre>
     *  Check COA Segment Value for '@ITEM'.
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @return boolean
     */
    public boolean checkCoaSegLkupTpCdForItem(String ajePtrnCoaCd) {
        if (ajePtrnCoaCd == null) {
            return false;
        }

        if (ajePtrnCoaCd.equals(COA_SEG_LKUP_TP_ITEM_VAL)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     *  Check COA Segment Value for '@TOC'.
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @return boolean
     */
    public boolean checkCoaSegLkupTpCdForToc(String ajePtrnCoaCd) {

        if (ajePtrnCoaCd == null) {
            return false;
        }

        if (ajePtrnCoaCd.equals(COA_SEG_LKUP_TP_TOC_VAL)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     *  Check COA Segment Value for '@CUST'.
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @return boolean
     */
    public boolean checkCoaSegLkupTpCdForCust(String ajePtrnCoaCd) {

        if (ajePtrnCoaCd == null) {
            return false;
        }

        if (ajePtrnCoaCd.equals(COA_SEG_LKUP_TP_CUST_VAL)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     *  Check COA Segment Value for '@VND'.
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @return boolean
     */
    public boolean checkCoaSegLkupTpCdForVnd(String ajePtrnCoaCd) {
        if (ajePtrnCoaCd == null) {
            return false;
        }

        if (ajePtrnCoaCd.equals(COA_SEG_LKUP_TP_VND_VAL)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     *  Check COA Segment Value for '@LINK'.
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @return boolean
     */
    public boolean checkCoaSegLkupTpCdForLink(String ajePtrnCoaCd) {

        if (ajePtrnCoaCd == null) {
            return false;
        }

        if (ajePtrnCoaCd.equals(COA_SEG_LKUP_TP_LINK_VAL)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     *  Check COA Segment Value for '@BANK-LC'.
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @return boolean
     */
    public boolean checkCoaSegLkupTpCdForBankLc(String ajePtrnCoaCd) {

        if (ajePtrnCoaCd == null) {
            return false;
        }

        if (ajePtrnCoaCd.equals(COA_SEG_LKUP_TP_BANK_LC_VAL)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     *  Check COA Segment Value for '@BANK-RM'.
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @return boolean
     */
    public boolean checkCoaSegLkupTpCdForBankRm(String ajePtrnCoaCd) {

        if (ajePtrnCoaCd == null) {
            return false;
        }

        if (ajePtrnCoaCd.equals(COA_SEG_LKUP_TP_BANK_RM_VAL)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     *  Check COA Segment Value for '@BANK-DA'.
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @return boolean
     */
    public boolean checkCoaSegLkupTpCdForBankDa(String ajePtrnCoaCd) {

        if (ajePtrnCoaCd == null) {
            return false;
        }

        if (ajePtrnCoaCd.equals(COA_SEG_LKUP_TP_BANK_DA_VAL)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     *  Check COA Segment Value for '@AFFL'.
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @return boolean
     */
    public boolean checkCoaSegLkupTpCdForAffl(String ajePtrnCoaCd) {

        if (ajePtrnCoaCd == null) {
            return false;
        }

        if (ajePtrnCoaCd.equals(COA_SEG_LKUP_TP_AFFL_VAL)) {
            return true;
        }
        return false;
    }

    // START 2014/04/16 T.Tozuka [CSA AJE#84 Add]
    /**
     * <pre>
     *  Check COA Segment Value for '@ST'.
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @return boolean
     */
    private boolean checkCoaSegLkupTpCdForSt(String ajePtrnCoaCd) {

        if (ajePtrnCoaCd == null) {
            return false;
        }

        if (ajePtrnCoaCd.equals(COA_SEG_LKUP_TP_ST_VAL)) {
            return true;
        }
        return false;
    }

    // END 2014/04/16 T.Tozuka [CSA AJE#84 Add]

    /**
     * <pre>
     *  Check COA Segment Value for '#PR'.
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @return boolean
     */
    public boolean checkCoaSegLkupTpCdForPr(String ajePtrnCoaCd) {

        if (ajePtrnCoaCd == null) {
            return false;
        }

        if (ajePtrnCoaCd.equals(COA_SEG_LKUP_TP_PR_VAL)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     *  Check COA Segment Value for '#PRBR'.
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @return boolean
     */
    public boolean checkCoaSegLkupTpCdForPrBr(String ajePtrnCoaCd) {

        if (ajePtrnCoaCd == null) {
            return false;
        }

        if (ajePtrnCoaCd.equals(COA_SEG_LKUP_TP_PRBR_VAL)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     *  Check COA Segment Value for '#WH'.
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @return boolean
     */
    public boolean checkCoaSegLkupTpCdForWh(String ajePtrnCoaCd) {

        if (ajePtrnCoaCd == null) {
            return false;
        }

        if (ajePtrnCoaCd.equals(COA_SEG_LKUP_TP_WH_VAL)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     *  Check COA Segment Value for '@MC'.
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @return boolean
     */
    public boolean checkCoaSegLkupTpCdForMC(String ajePtrnCoaCd) {

        if (ajePtrnCoaCd == null) {
            return false;
        }

        if (ajePtrnCoaCd.equals(COA_SEG_LKUP_TP_MC_VAL)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@Link' is permitted for COA Branch Code in AJE Pattern.
     *  11/03/2009 added 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForBrOnlyLinkPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForPr || resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForWh || resutlOfCheckCoaSegLkupTpCdForToc) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '#PR' is permitted for COA Branch Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForBrOnlyPrPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForPr) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink || resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForWh || resutlOfCheckCoaSegLkupTpCdForToc) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '#PR' is permitted for COA Branch Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForBrOnlyPrBrPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForPrBr) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink || resutlOfCheckCoaSegLkupTpCdForPr || resutlOfCheckCoaSegLkupTpCdForWh || resutlOfCheckCoaSegLkupTpCdForToc) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@TOC' is permitted for COA Branch Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForBrOnlyTocPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink || resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForPr || resutlOfCheckCoaSegLkupTpCdForWh) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '#WH' is permitted for COA Branch Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForBrOnlyWhPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForWh) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink || resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForPr || resutlOfCheckCoaSegLkupTpCdForToc) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '#PR' is permitted for COA Cost Center Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForCcOnlyPrPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForPr) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForWh || resutlOfCheckCoaSegLkupTpCdForToc || resutlOfCheckCoaSegLkupTpCdForLink) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '#WH' is permitted for COA Cost Center Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForCcOnlyWHPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForWh) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForPr || resutlOfCheckCoaSegLkupTpCdForToc || resutlOfCheckCoaSegLkupTpCdForLink) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '#PRBR' is permitted for COA Cost Center Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForCcOnlyPrBrPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForPrBr) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForPr || resutlOfCheckCoaSegLkupTpCdForWh || resutlOfCheckCoaSegLkupTpCdForToc || resutlOfCheckCoaSegLkupTpCdForLink) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@TOC' is permitted for COA Cost Center Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForCcOnlyTocPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForPr || resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForWh || resutlOfCheckCoaSegLkupTpCdForLink) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@TOC' or '@Link' is permitted for COA Cost Center Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCdOnOrg COA Segment in in Organization Master
     * @param ajeIntfcSegCdOnLink COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForCcTocLinkPermit(String ajePtrnCoaCd, String ajeIntfcSegCdOnOrg, String ajeIntfcSegCdOnLink) {

        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return ajeIntfcSegCdOnOrg;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return ajeIntfcSegCdOnLink;
        } else if (resutlOfCheckCoaSegLkupTpCdForPr || resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForWh) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@LINK' and '@BANK' are NOT permitted for COA Account Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @return Valid Segment Value changed 10.29.2009
     */
    public String getValidCoaSegValueForAcctLinkBankNotPermit(String ajePtrnCoaCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForBankLc = checkCoaSegLkupTpCdForBankLc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForBankRm = checkCoaSegLkupTpCdForBankRm(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForBankDa = checkCoaSegLkupTpCdForBankDa(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForLink || resutlOfCheckCoaSegLkupTpCdForBankLc || resutlOfCheckCoaSegLkupTpCdForBankRm || resutlOfCheckCoaSegLkupTpCdForBankDa) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@LINK' is permitted for COA Account Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForAcctOnlyLinkPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForBankLc = checkCoaSegLkupTpCdForBankLc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForBankRm = checkCoaSegLkupTpCdForBankRm(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForBankDa = checkCoaSegLkupTpCdForBankDa(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForMC = checkCoaSegLkupTpCdForMC(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForBankLc || resutlOfCheckCoaSegLkupTpCdForBankRm || resutlOfCheckCoaSegLkupTpCdForBankDa || resutlOfCheckCoaSegLkupTpCdForMC) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@LINK' or '@AFFL' is permitted for COA Account Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCdAtLink COA Segment in AJE Interface * @param
     * ajeIntfcSegCdAtAffl COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForAcctLinkAfflPermit(String ajePtrnCoaCd, String ajeIntfcSegCdAtLink, String ajeIntfcSegCdAtAffl) {

        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForBankLc = checkCoaSegLkupTpCdForBankLc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForBankRm = checkCoaSegLkupTpCdForBankRm(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForBankDa = checkCoaSegLkupTpCdForBankDa(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForAffl = checkCoaSegLkupTpCdForAffl(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForMC = checkCoaSegLkupTpCdForMC(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return ajeIntfcSegCdAtLink;
        } else if (resutlOfCheckCoaSegLkupTpCdForAffl) {
            return ajeIntfcSegCdAtAffl;
        } else if (resutlOfCheckCoaSegLkupTpCdForBankLc || resutlOfCheckCoaSegLkupTpCdForBankRm || resutlOfCheckCoaSegLkupTpCdForBankDa || resutlOfCheckCoaSegLkupTpCdForMC) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    // START 2014/04/16 T.Tozuka [CSA AJE#84 Add]
    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@LINK' or '@AFFL' or '@ST' is permitted for COA Account Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCdAtLink COA Segment in AJE Interface
     * @param ajeIntfcSegCdAtAffl COA Segment in AJE Interface
     * @param ajeIntfcSegCdAtSt COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForAcctLinkAfflStPermit(String ajePtrnCoaCd, String ajeIntfcSegCdAtLink, String ajeIntfcSegCdAtAffl, String ajeIntfcSegCdAtSt) {

        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForBankLc = checkCoaSegLkupTpCdForBankLc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForBankRm = checkCoaSegLkupTpCdForBankRm(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForBankDa = checkCoaSegLkupTpCdForBankDa(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForAffl = checkCoaSegLkupTpCdForAffl(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForMC = checkCoaSegLkupTpCdForMC(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForSt = checkCoaSegLkupTpCdForSt(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return ajeIntfcSegCdAtLink;
        } else if (resutlOfCheckCoaSegLkupTpCdForAffl) {
            return ajeIntfcSegCdAtAffl;
        } else if (resutlOfCheckCoaSegLkupTpCdForSt) {
            return ajeIntfcSegCdAtSt;
        } else if (resutlOfCheckCoaSegLkupTpCdForBankLc || resutlOfCheckCoaSegLkupTpCdForBankRm || resutlOfCheckCoaSegLkupTpCdForBankDa || resutlOfCheckCoaSegLkupTpCdForMC) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    // END 2014/04/16 T.Tozuka [CSA AJE#84 Add]

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@LINK' or '@MC' is permitted for COA Account Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCdAtLink COA Segment in AJE Interface * @param
     * ajeIntfcSegCdAtMc COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForAcctLinkMCPermit(String ajePtrnCoaCd, String ajeIntfcSegCdAtLink, String ajeIntfcSegCdAtMc) {

        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForAffl = checkCoaSegLkupTpCdForAffl(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForMC = checkCoaSegLkupTpCdForMC(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return ajeIntfcSegCdAtLink;
        } else if (resutlOfCheckCoaSegLkupTpCdForMC) {
            return ajeIntfcSegCdAtMc;
        } else if (resutlOfCheckCoaSegLkupTpCdForAffl) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when only '@MC' is permitted for COA Account Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCdAtMc COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForAcctMCPermit(String ajePtrnCoaCd, String ajeIntfcSegCdAtMc) {

        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForAffl = checkCoaSegLkupTpCdForAffl(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForMC = checkCoaSegLkupTpCdForMC(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForMC) {
            return ajeIntfcSegCdAtMc;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink || resutlOfCheckCoaSegLkupTpCdForAffl) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@LINK' is permitted for COA Product Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForProdOnlyLinkPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForItem = checkCoaSegLkupTpCdForItem(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForItem || resutlOfCheckCoaSegLkupTpCdForToc) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@ITEM' is permitted for COA Product Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForProdOnlyItemPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForItem = checkCoaSegLkupTpCdForItem(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForItem) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink || resutlOfCheckCoaSegLkupTpCdForToc) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@ITEM' or '@TOC' is permitted for COA Product Code in AJE Pattern. 
     * </pre>
     * 
     * The function name was wrong, so modified. The name used to be
     * getValidCoaSegValueForProdItemNotPermit
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCdOnMdse COA Segment in Merchandise Master
     * @param ajeIntfcSegCdOnOrg COA Segment in Organization Master
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForProdLinkNotPermit(String ajePtrnCoaCd, String ajeIntfcSegCdOnMdse, String ajeIntfcSegCdOnOrg) {

        boolean resutlOfCheckCoaSegLkupTpCdForItem = checkCoaSegLkupTpCdForItem(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForItem) {
            return ajeIntfcSegCdOnMdse;
        } else if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return ajeIntfcSegCdOnOrg;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@LINK' or '@TOC' is permitted for COA Product Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCdOnLink COA Segment in AJE Interface
     * @param ajeIntfcSegCdOnOrg COA Segment of Toc in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForProdLinkOrgPermit(String ajePtrnCoaCd, String ajeIntfcSegCdOnLink, String ajeIntfcSegCdOnOrg) {

        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForItem = checkCoaSegLkupTpCdForItem(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return ajeIntfcSegCdOnLink;
        } else if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return ajeIntfcSegCdOnOrg;
        } else if (resutlOfCheckCoaSegLkupTpCdForItem) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '#PR' is permitted for COA Channel Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForChOnlyPrPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForPr) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForWh || resutlOfCheckCoaSegLkupTpCdForCust || resutlOfCheckCoaSegLkupTpCdForLink) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '#WH' is permitted for COA Channel Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForChOnlyWHPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForWh) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForPr || resutlOfCheckCoaSegLkupTpCdForCust || resutlOfCheckCoaSegLkupTpCdForLink) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '#PRBR' is permitted for COA Channel Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForChOnlyPrBrPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForPrBr) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForPr || resutlOfCheckCoaSegLkupTpCdForWh || resutlOfCheckCoaSegLkupTpCdForCust || resutlOfCheckCoaSegLkupTpCdForLink) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@CUST' is permitted for COA Channel Code in AJE Pattern. 
     *  changed 20100111: CH_CD can't be set from BILL_TO_CUST, so this function changed from getValidCoaSegValueForChOnlyCustPermit to getValidCoaSegValueForChOnlyTocPermit
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForChOnlyTocPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForPr || resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForWh || resutlOfCheckCoaSegLkupTpCdForCust || resutlOfCheckCoaSegLkupTpCdForLink) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when only value of aje pattern is permitted for COA Channel Code. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForChOnlyPtrnPermit(String ajePtrnCoaCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc || resutlOfCheckCoaSegLkupTpCdForPr || resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForWh || resutlOfCheckCoaSegLkupTpCdForCust || resutlOfCheckCoaSegLkupTpCdForLink) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@CUST' is permitted for COA Affiliate Code in AJE Pattern. (added 100509)
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForAffOnlyCustPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForVnd = checkCoaSegLkupTpCdForVnd(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForCust) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForVnd) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@CUST' and '@VND' are permitted for COA Affiliate Code in AJE Pattern. (added 100813)
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForAfflCustVndPermit(String ajePtrnCoaCd, String ajeCustSegCd, String ajeVndSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForVnd = checkCoaSegLkupTpCdForVnd(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForCust) {
            return ajeCustSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForVnd) {
            return ajeVndSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@CUST' or '@AFFL is permitted for COA Affiliate Code in AJE Pattern. (added 100509)
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @param ajeIntfcSegCdAtAffl COA Segment '@AFFL
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForAffCustAfflPermit(String ajePtrnCoaCd, String ajeIntfcSegCd, String ajeIntfcSegCdAtAffl) {

        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForVnd = checkCoaSegLkupTpCdForVnd(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForAffl = checkCoaSegLkupTpCdForAffl(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForCust) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForAffl) {
            return ajeIntfcSegCdAtAffl;
        } else if (resutlOfCheckCoaSegLkupTpCdForVnd) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@CUST', '@AFFL' or '@Link' is permitted for COA Affiliate Code in AJE Pattern. (added 101013)
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @param ajeIntfcSegCdAtAffl COA Segment '@AFFL'
     * @param ajeIntfcSegCdAtLink COA Segment '@Link'
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForAffCustAfflLinkPermit(String ajePtrnCoaCd, String ajeIntfcSegCd, String ajeIntfcSegCdAtAffl, String ajeIntfcSegCdAtLink) {

        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForVnd = checkCoaSegLkupTpCdForVnd(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForAffl = checkCoaSegLkupTpCdForAffl(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForCust) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForAffl) {
            return ajeIntfcSegCdAtAffl;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return ajeIntfcSegCdAtLink;
        } else if (resutlOfCheckCoaSegLkupTpCdForVnd) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@CUST' or '@VND' is NOT permitted for COA Affiliate Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForAfflCustVndNotPermit(String ajePtrnCoaCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForVnd = checkCoaSegLkupTpCdForVnd(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForCust || resutlOfCheckCoaSegLkupTpCdForVnd) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when only '@VND' is permitted for COA Affiliate Code in AJE Pattern. 
     *  2009/10/27 added
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForAfflOnlyVndPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForVnd = checkCoaSegLkupTpCdForVnd(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForVnd) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForCust) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@VND' or '@LINK' is permitted for COA Affiliate Code in AJE Pattern. 
     *  2009/10/27 added
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface * @param
     * ajeIntfcSegCdAtLink COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForAfflVndLinkPermit(String ajePtrnCoaCd, String ajeIntfcSegCd, String ajeIntfcSegCdAtLink) {

        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForVnd = checkCoaSegLkupTpCdForVnd(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForVnd) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return ajeIntfcSegCdAtLink;
        } else if (resutlOfCheckCoaSegLkupTpCdForCust) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when only '@LINK' is permitted for COA Affiliate Code in AJE Pattern. 
     *  2009/10/27 added
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCdAtLink COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForAfflOnlyLinkPermit(String ajePtrnCoaCd, String ajeIntfcSegCdAtLink) {

        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForVnd = checkCoaSegLkupTpCdForVnd(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForVnd) {
            return INVALID_MSG_FOR_COA_SEG;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return ajeIntfcSegCdAtLink;
        } else if (resutlOfCheckCoaSegLkupTpCdForCust) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@LINK' is permitted for COA Project Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForProjLinkPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return ajeIntfcSegCd;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@LINK' is NOT permitted for COA Project Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForProjLinkNotPermit(String ajePtrnCoaCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@TOC' or '#WH' is permitted for COA Branch Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param intfcSegCdByOrg COA Segment in AJE Interface from
     * S21_ORG
     * @param intfcSegCdByCsw COA Segment in AJE Interface from
     * COA_SEG_WH_PTRN
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForBrTocWhPermit(String ajePtrnCoaCd, String intfcSegCdByOrg, String intfcSegCdByCsw) {

        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return intfcSegCdByOrg;
        } else if (resutlOfCheckCoaSegLkupTpCdForWh) {
            return intfcSegCdByCsw;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink || resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForPr) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@TOC', '#WH' or '@LINK' is permitted for COA Branch Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param intfcSegCdByOrg COA Segment in AJE Interface from
     * S21_ORG
     * @param intfcSegCdByCsw COA Segment in AJE Interface from
     * COA_SEG_WH_PTRN
     * @param intfcSegCdByLink COA Segment in AJE Interface from INTFC
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForBrTocWhLinkPermit(String ajePtrnCoaCd, String intfcSegCdByOrg, String intfcSegCdByCsw, String intfcSegCdByLink) {

        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return intfcSegCdByOrg;
        } else if (resutlOfCheckCoaSegLkupTpCdForWh) {
            return intfcSegCdByCsw;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return intfcSegCdByLink;
        } else if (resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForPr) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@TOC' or '#PR' is permitted for COA Branch Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param intfcSegCdByOrg COA Segment in AJE Interface from
     * S21_ORG
     * @param intfcSegCdByCsp COA Segment in AJE Interface from
     * COA_SEG_PROD_PTRN
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForBrTocPrPermit(String ajePtrnCoaCd, String intfcSegCdByOrg, String intfcSegCdByCsp) {

        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return intfcSegCdByOrg;
        } else if (resutlOfCheckCoaSegLkupTpCdForPr) {
            return intfcSegCdByCsp;
        } else if (resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForWh || resutlOfCheckCoaSegLkupTpCdForLink) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@TOC' or '@LINK' is permitted for COA Branch Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param intfcSegCdByOrg COA Segment in AJE Interface from
     * S21_ORG
     * @param intfcSegCdByLink COA Segment in AJE Interface from INTFC
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForBrTocLinkPermit(String ajePtrnCoaCd, String intfcSegCdByOrg, String intfcSegCdByLink) {

        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return intfcSegCdByOrg;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return intfcSegCdByLink;
        } else if (resutlOfCheckCoaSegLkupTpCdForWh || resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForPr) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when only value of aje pattern is permitted for COA Branch Code. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForBrOnlyPtrnPermit(String ajePtrnCoaCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc || resutlOfCheckCoaSegLkupTpCdForLink || resutlOfCheckCoaSegLkupTpCdForWh || resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForPr) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@TOC' or '#WH' is permitted for COA Cost Center Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param intfcSegCdByOrg COA Segment in AJE Interface from
     * S21_ORG
     * @param intfcSegCdByCsw COA Segment in AJE Interface from
     * COA_SEG_WH_PTRN
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForCcTocWhPermit(String ajePtrnCoaCd, String intfcSegCdByOrg, String intfcSegCdByCsw) {

        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return intfcSegCdByOrg;
        } else if (resutlOfCheckCoaSegLkupTpCdForWh) {
            return intfcSegCdByCsw;
        } else if (resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForPr || resutlOfCheckCoaSegLkupTpCdForLink) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@TOC' or '#WH' is permitted for COA Cost Center Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param intfcSegCdByOrg COA Segment in AJE Interface from
     * S21_ORG
     * @param intfcSegCdByCsw COA Segment in AJE Interface from
     * COA_SEG_WH_PTRN
     * @param intfcSegCdByCspb COA Segment in AJE Interface from
     * COA_SEG_PROD_BR_PTRN
     * @param intfcSegCdByLink COA Segment in AJE Interface from INTFC
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForCcTocWhPrbrLinkPermit(String ajePtrnCoaCd, String intfcSegCdByOrg, String intfcSegCdByCsw, String intfcSegCdByCspb, String intfcSegCdByLink) {

        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return intfcSegCdByOrg;
        } else if (resutlOfCheckCoaSegLkupTpCdForWh) {
            return intfcSegCdByCsw;
        } else if (resutlOfCheckCoaSegLkupTpCdForPrBr) {
            return intfcSegCdByCspb;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return intfcSegCdByLink;
        } else if (resutlOfCheckCoaSegLkupTpCdForPr) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@TOC' or '#WH' is permitted for COA Cost Center Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param intfcSegCdByOrg COA Segment in AJE Interface from
     * S21_ORG
     * @param intfcSegCdByCsw COA Segment in AJE Interface from
     * COA_SEG_WH_PTRN
     * @param intfcSegCdByCsp COA Segment in AJE Interface from
     * COA_SEG_PROD_PTRN
     * @param intfcSegCdByLink COA Segment in AJE Interface from INTFC
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForCcTocWhPrLinkPermit(String ajePtrnCoaCd, String intfcSegCdByOrg, String intfcSegCdByCsw, String intfcSegCdByCsp, String intfcSegCdByLink) {

        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return intfcSegCdByOrg;
        } else if (resutlOfCheckCoaSegLkupTpCdForWh) {
            return intfcSegCdByCsw;
        } else if (resutlOfCheckCoaSegLkupTpCdForPrBr) {
            return INVALID_MSG_FOR_COA_SEG;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return intfcSegCdByLink;
        } else if (resutlOfCheckCoaSegLkupTpCdForPr) {
            return intfcSegCdByCsp;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@TOC' or '#WH' is permitted for COA Cost Center Code in AJE Pattern.
     *  If Cost Center which is retrieved from TOC and the one from PRBR don't match, return error. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param intfcSegCdByOrg COA Segment in AJE Interface from
     * S21_ORG
     * @param intfcSegCdByCsw COA Segment in AJE Interface from
     * COA_SEG_WH_PTRN
     * @param intfcSegCdByCspb COA Segment in AJE Interface from
     * COA_SEG_PROD_BR_PTRN
     * @param IntfcChCdByCspb COA Channel Code in AJE Interface from
     * COA_SEG_PROD_BR_PTRN
     * @param ajePtrnChCd COA Channel Code in AJE Pattern
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForCcTocWhPermitForInvty(String ajePtrnCoaCd, String intfcSegCdByOrg, String intfcSegCdByCsw, String intfcSegCdByCspb, String IntfcChCdByCspb, String ajePtrnChCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        boolean isPRBR = checkCoaSegLkupTpCdForPrBr(ajePtrnChCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            // if value of coa channel code is '#PRBR', check if the
            // values of cost center from Toc and the one from PRBR
            // match
            if (isPRBR) {
                if (intfcSegCdByOrg == null && intfcSegCdByCspb == null) {
                    return null;
                } else if (intfcSegCdByOrg == null || (IntfcChCdByCspb != null && intfcSegCdByCspb == null)) {
                    return INVALID_MSG_FOR_COA_CC;
                } else if (IntfcChCdByCspb == null) {
                    return intfcSegCdByOrg; // if ch code is null,
                                            // then ch code error will
                                            // be raised later.
                } else if (!intfcSegCdByOrg.equals(intfcSegCdByCspb)) {
                    return INVALID_MSG_FOR_COA_CC;
                }
            }

            return intfcSegCdByOrg;

        } else if (resutlOfCheckCoaSegLkupTpCdForWh) {
            return intfcSegCdByCsw;
        } else if (resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForPr || resutlOfCheckCoaSegLkupTpCdForLink) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@TOC' or '#PR' is permitted for COA Cost Center Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param intfcSegCdByOrg COA Segment in AJE Interface from
     * S21_ORG
     * @param intfcSegCdByCsp COA Segment in AJE Interface from
     * COA_SEG_PROD_PTRN
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForCcTocPrPermit(String ajePtrnCoaCd, String intfcSegCdByOrg, String intfcSegCdByCsp) {

        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return intfcSegCdByOrg;
        } else if (resutlOfCheckCoaSegLkupTpCdForPr) {
            return intfcSegCdByCsp;
        } else if (resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForWh || resutlOfCheckCoaSegLkupTpCdForLink) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@TOC' or '#PR' is permitted for COA Channel Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param intfcSegCdByOrg COA Segment in AJE Interface from
     * S21_ORG
     * @param intfcSegCdByCsp COA Segment in AJE Interface from
     * COA_SEG_PROD_PTRN
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForChTocPrPermit(String ajePtrnCoaCd, String intfcSegCdByOrg, String intfcSegCdByCsp) {

        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return intfcSegCdByOrg;
        } else if (resutlOfCheckCoaSegLkupTpCdForPr) {
            return intfcSegCdByCsp;
        } else if (resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForWh || resutlOfCheckCoaSegLkupTpCdForLink) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@CUST' or '#WH' is permitted for COA Channel Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param intfcSegCdByBtc COA Segment in AJE Interface from
     * BILL_TO_CUST
     * @param intfcSegCdByCsw COA Segment in AJE Interface from
     * COA_SEG_WH_PTRN
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForChCustWhPermit(String ajePtrnCoaCd, String intfcSegCdByBtc, String intfcSegCdByCsw) {

        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForCust) {
            return intfcSegCdByBtc;
        } else if (resutlOfCheckCoaSegLkupTpCdForWh) {
            return intfcSegCdByCsw;
        } else if (resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForPr || resutlOfCheckCoaSegLkupTpCdForLink) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@TOC' or '@LINK' is permitted for COA Channel Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param intfcSegCdByOrg COA Segment in AJE Interface from
     * S21_ORG
     * @param intfcSegCdByLink COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForChTocLinkPermit(String ajePtrnCoaCd, String intfcSegCdByOrg, String intfcSegCdByLink) {

        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return intfcSegCdByOrg;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return intfcSegCdByLink;
        } else if (resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForPr || resutlOfCheckCoaSegLkupTpCdForCust || resutlOfCheckCoaSegLkupTpCdForWh) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@TOC', '@CUST' or '@LINK' is permitted for COA Channel Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param intfcSegCdByOrg COA Segment in AJE Interface from
     * S21_ORG
     * @param intfcSegCdByLink COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForChTocLinkCustPermit(String ajePtrnCoaCd, String intfcSegCdByOrg, String intfcSegCdByLink, String intfcSegCdByCust) {

        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return intfcSegCdByOrg;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return intfcSegCdByLink;
        } else if (resutlOfCheckCoaSegLkupTpCdForCust) {
            return intfcSegCdByCust;
        } else if (resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForPr || resutlOfCheckCoaSegLkupTpCdForWh) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@CUST' or '#WH' is permitted for COA Channel Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param intfcSegCdByBtc COA Segment in AJE Interface from
     * BILL_TO_CUST
     * @param intfcSegCdByCsw COA Segment in AJE Interface from
     * COA_SEG_WH_PTRN
     * @param intfcSegCdByCspb Segment in AJE Interface from
     * COA_SEG_PROD_BR_PTRN
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForChCustWhPrBrPermit(String ajePtrnCoaCd, String intfcSegCdByBtc, String intfcSegCdByCsw, String intfcSegCdByCspb) {

        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForCust) {
            return intfcSegCdByBtc;
        } else if (resutlOfCheckCoaSegLkupTpCdForWh) {
            return intfcSegCdByCsw;
        } else if (resutlOfCheckCoaSegLkupTpCdForPrBr) {
            return intfcSegCdByCspb;
        } else if (resutlOfCheckCoaSegLkupTpCdForPr || resutlOfCheckCoaSegLkupTpCdForLink) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@TOC', '#WH', '#PRBR', or '@LINK' is permitted for COA Channel Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param intfcSegCdByOrg COA Segment in AJE Interface from
     * S21_ORG
     * @param intfcSegCdByCsw COA Segment in AJE Interface from
     * COA_SEG_WH_PTRN
     * @param intfcSegCdByCspb Segment in AJE Interface from
     * COA_SEG_PROD_BR_PTRN
     * @param intfcSegCdByLink Segment in AJE Interface from INTFC
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForChTocWhPrBrLinkPermit(String ajePtrnCoaCd, String intfcSegCdByOrg, String intfcSegCdByCsw, String intfcSegCdByCspb, String intfcSegCdByLink) {

        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return intfcSegCdByOrg;
        } else if (resutlOfCheckCoaSegLkupTpCdForWh) {
            return intfcSegCdByCsw;
        } else if (resutlOfCheckCoaSegLkupTpCdForPrBr) {
            return intfcSegCdByCspb;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return intfcSegCdByLink;
        } else if (resutlOfCheckCoaSegLkupTpCdForPr || resutlOfCheckCoaSegLkupTpCdForCust) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@TOC', '#WH', '#PRBR', or '@LINK' is permitted for COA Channel Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param intfcSegCdByOrg COA Segment in AJE Interface from
     * S21_ORG
     * @param intfcSegCdByCsw COA Segment in AJE Interface from
     * COA_SEG_WH_PTRN
     * @param intfcSegCdByCsp Segment in AJE Interface from
     * COA_SEG_PROD_PTRN
     * @param intfcSegCdByLink Segment in AJE Interface from INTFC
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForChTocWhPrLinkPermit(String ajePtrnCoaCd, String intfcSegCdByOrg, String intfcSegCdByCsw, String intfcSegCdByCsp, String intfcSegCdByLink) {

        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return intfcSegCdByOrg;
        } else if (resutlOfCheckCoaSegLkupTpCdForWh) {
            return intfcSegCdByCsw;
        } else if (resutlOfCheckCoaSegLkupTpCdForPr) {
            return intfcSegCdByCsp;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return intfcSegCdByLink;
        } else if (resutlOfCheckCoaSegLkupTpCdForPrBr || resutlOfCheckCoaSegLkupTpCdForCust) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@TOC', '#WH', '#PRBR', '@CUST or '@LINK' is permitted for COA Channel Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param intfcSegCdByOrg COA Segment in AJE Interface from
     * S21_ORG
     * @param intfcSegCdByCsw COA Segment in AJE Interface from
     * COA_SEG_WH_PTRN
     * @param intfcSegCdByCsp Segment in AJE Interface from
     * COA_SEG_PROD_PTRN
     * @param intfcSegCdByLink Segment in AJE Interface from INTFC
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForChAllPermit(String ajePtrnCoaCd, String intfcSegCdByOrg, String intfcSegCdByCsw, String intfcSegCdByCsp, String intfcSegCdByLink, String intfcSegCdByCust) {

        boolean resutlOfCheckCoaSegLkupTpCdForCust = checkCoaSegLkupTpCdForCust(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPr = checkCoaSegLkupTpCdForPr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForPrBr = checkCoaSegLkupTpCdForPrBr(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForWh = checkCoaSegLkupTpCdForWh(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return intfcSegCdByOrg;
        } else if (resutlOfCheckCoaSegLkupTpCdForWh) {
            return intfcSegCdByCsw;
        } else if (resutlOfCheckCoaSegLkupTpCdForPr) {
            return intfcSegCdByCsp;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return intfcSegCdByLink;
        } else if (resutlOfCheckCoaSegLkupTpCdForCust) {
            return intfcSegCdByCust;
        } else if (resutlOfCheckCoaSegLkupTpCdForPrBr) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@ITEM' or '@LINK' is permitted for COA Product Code in AJE Pattern.
     *  
     *   changed 20100111: PROD_CD can't be set from Toc, so this function changed from getValidCoaSegValueForProdItemTocPermit to getValidCoaSegValueForProdItemLinkPermit
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCdOnMdse COA Segment in Merchandise Master
     * @param ajeIntfcSegCdOnLink COA Segment in Organization Master
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForProdItemLinkPermit(String ajePtrnCoaCd, String ajeIntfcSegCdOnMdse, String ajeIntfcSegCdOnLink) {

        boolean resutlOfCheckCoaSegLkupTpCdForItem = checkCoaSegLkupTpCdForItem(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForItem) {
            return ajeIntfcSegCdOnMdse;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return ajeIntfcSegCdOnLink;
        } else if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@ITEM', '@LINK' or '@TOC' is permitted for COA Product Code in AJE Pattern.
     *  
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCdOnMdse COA Segment in Merchandise Master
     * @param ajeIntfcSegCdOnLink COA Segment in Interface Table
     * @param ajeIntfcSegCdOnToc COA Segment in Organization Master
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForProdItemLinkTocPermit(String ajePtrnCoaCd, String ajeIntfcSegCdOnMdse, String ajeIntfcSegCdOnLink, String ajeIntfcSegCdOnToc) {

        boolean resutlOfCheckCoaSegLkupTpCdForItem = checkCoaSegLkupTpCdForItem(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForItem) {
            return ajeIntfcSegCdOnMdse;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink) {
            return ajeIntfcSegCdOnLink;
        } else if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return ajeIntfcSegCdOnToc;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@LINK' is permitted for COA Account Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcAcctCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    // this is duplicated and deleted. 10.29.2009
    // public String getValidCoaSegValueForAcctLinkPermit(String
    // ajePtrnCoaCd, String ajeIntfcAcctCd) {
    //
    // boolean resutlOfCheckCoaSegLkupTpCdForLink =
    // checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);
    //
    // if (resutlOfCheckCoaSegLkupTpCdForLink) {
    // return ajeIntfcAcctCd;
    // } else {
    // return ajePtrnCoaCd;
    // }
    // }
    /**
     * <pre>
     *  Get Valid COA Segment Value in AJE Pattern is valid or not.
     *  This process is used when '@TOC' is permitted for COA Product Code in AJE Pattern. 
     * </pre>
     * @param ajePtrnCoaCd COA Segment in AJE Pattern
     * @param ajeIntfcSegCd COA Segment in AJE Interface
     * @return Valid Segment Value
     */
    public String getValidCoaSegValueForProdOnlyTocPermit(String ajePtrnCoaCd, String ajeIntfcSegCd) {

        boolean resutlOfCheckCoaSegLkupTpCdForItem = checkCoaSegLkupTpCdForItem(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForLink = checkCoaSegLkupTpCdForLink(ajePtrnCoaCd);
        boolean resutlOfCheckCoaSegLkupTpCdForToc = checkCoaSegLkupTpCdForToc(ajePtrnCoaCd);

        if (resutlOfCheckCoaSegLkupTpCdForToc) {
            return ajeIntfcSegCd;
        } else if (resutlOfCheckCoaSegLkupTpCdForLink || resutlOfCheckCoaSegLkupTpCdForItem) {
            return INVALID_MSG_FOR_COA_SEG;
        } else {
            return ajePtrnCoaCd;
        }
    }

    /**
     * <pre>
     * Refine AJE Pattern List by AJE Id and Indicator Type.
     * </pre>
     * @param ajeId AJE ID of current interface table's record
     * @param rsIntfcData ResultSet of each interface table
     * @param ajePtrnListFrmIntfc AJE pattern list which is retrieved
     * from interface table, which means it includes patterns that
     * might not match the current row's AJE ID.
     * @return Valid Segment Value
     * @throws SQLException SQL Exception
     */
    public List<NFAC000101PMsg> getAJEPtrnByAjeIdAndIndTp(String ajeId, ResultSet rsIntfcData, List<Map> ajePtrnListFrmIntfc) throws SQLException {
        List<NFAC000101PMsg> emptyList = new ArrayList<NFAC000101PMsg>();

        // get AJE pattern which matches current record's AJE ID
        List<NFAC000101PMsg> ajePtrnList = getAjePtrnListByAjeId(ajePtrnListFrmIntfc, ajeId, false);

        List<NFAC000101PMsg> ajePtrnListByInd = getAJEPtrnByIndTp(rsIntfcData, ajePtrnList, ajePtrnListFrmIntfc);

        if (ajePtrnListByInd == null) {

            return emptyList;
        }

        // if no pattern found, then try it again with default
        // SYS_SRC_CD
        if (ajePtrnListByInd.size() == 0) {
            ajePtrnList.clear();
            // get pattern of default aje_id
            ajePtrnList = getAjePtrnListByAjeId(ajePtrnListFrmIntfc, ajeId, true);
            ajePtrnListByInd = getAJEPtrnByIndTp(rsIntfcData, ajePtrnList, ajePtrnListFrmIntfc);

            if (ajePtrnListByInd == null) {
                return emptyList;
            }
        }

        return ajePtrnListByInd;
    }

    private List<NFAC000101PMsg> getAJEPtrnByIndTp(ResultSet rsIntfcData, List<NFAC000101PMsg> ajePtrnList, List<Map> ajePtrnListFrmIntfc) throws SQLException {
        List<NFAC000101PMsg> ajePtrnListByInd = new ArrayList<NFAC000101PMsg>();

        // copy list of ajePtrnList to ajePtrnListByInd
        for (NFAC000101PMsg ajePtrnItem : ajePtrnList) {
            ajePtrnListByInd.add(ajePtrnItem);
        }

        String indColumnNm = "";
        boolean isExit = false;

        for (int i = 1; i <= AJE_PTRN_IND_TP_CNT; i++) {
            if (isExit) {
                break;
            }

            for (NFAC000101PMsg ajePtrnItem : ajePtrnList) {
                // form column name of indicator
                switch (i) {
                    case AJE_PTRN_IND_TP_CNT_1:
                        indColumnNm = ajePtrnItem.ajeIntfcColTxt_01.getValue();
                        break;
                    case AJE_PTRN_IND_TP_CNT_2:
                        indColumnNm = ajePtrnItem.ajeIntfcColTxt_02.getValue();
                        break;
                    case AJE_PTRN_IND_TP_CNT_3:
                        indColumnNm = ajePtrnItem.ajeIntfcColTxt_03.getValue();
                        break;
                    default:
                        indColumnNm = "";
                        break;
                }
    
                // if indicator is not set, exit
                if (indColumnNm.equals("") || indColumnNm == null) {
                    isExit = true;
                    break;
                }
    
                boolean isMatched = false;
                // check if value of interface table matches the
                // one
                // of pattern table
                try {
                        
                    switch (i) {
                        case AJE_PTRN_IND_TP_CNT_1:
                            if (ajePtrnItem.ajePtrnActlCd_01.getValue().equals(rsIntfcData.getString(indColumnNm))) {
                                isMatched = true;
                            }
                            break;
                        case AJE_PTRN_IND_TP_CNT_2:
                            if (ajePtrnItem.ajePtrnActlCd_02.getValue().equals(rsIntfcData.getString(indColumnNm))) {
                                isMatched = true;
                            }
                            break;
                        case AJE_PTRN_IND_TP_CNT_3:
                            if (ajePtrnItem.ajePtrnActlCd_03.getValue().equals(rsIntfcData.getString(indColumnNm))) {
                                isMatched = true;
                            }
                            break;
                        default:
                            break;
                    }
                    
                } catch (java.sql.SQLException ex) {
                    //---- start mod 2016/03/22 QC#5849 this modification is required according to the modification of removing AJE_INTFC_TP as a key from AJE_PTRN_IND_TP.
                    // cannot retrieve the designated field from result set
                    //return null;
                    // delete this pattern from the list
                    ajePtrnListByInd.remove(ajePtrnItem);
                    // go to next
                    continue;
                    //---- end 2016/03/22
                }
                
                // if the value is not matched
                if (!isMatched) {
                    // delete this pattern from the list
                    ajePtrnListByInd.remove(ajePtrnItem);
                }
            }// for pattern

        } // for number of indicator type
        
        return ajePtrnListByInd;
    }

    /**
     * <pre>
     *  Get AJE Pattern List by AJE Id.
     *  (Moved from indivisual program to make it common.)
     * </pre>
     * @param ajePtrnList AJE Pattern List
     * @param ajeId AJE ID
     * @return List<NFAC000101PMsg> ajeIdList
     */
    private List<NFAC000101PMsg> getAjePtrnListByAjeId(List<Map> ajePtrnList, String ajeId, boolean isForDefault) {

        List<NFAC000101PMsg> ajeIdList = new ArrayList<NFAC000101PMsg>();

        boolean isExist = false;

        if (!isForDefault) {
            for (Map<String, String> rsGetAjePtrnByAjeIdAndReasons : ajePtrnList) {
                String ajeIdInAjePtrn = rsGetAjePtrnByAjeIdAndReasons.get(AJE_ID);

                if (ajeId.equals(ajeIdInAjePtrn)) {

                    // flag on
                    isExist = true;

                    NFAC000101PMsg ajePtrn = new NFAC000101PMsg();

                    ajePtrn = setAJEPatternValue(rsGetAjePtrnByAjeIdAndReasons, ajeId, rsGetAjePtrnByAjeIdAndReasons.get(JRNL_SRC_CD), rsGetAjePtrnByAjeIdAndReasons.get(JRNL_SRC_NM));

                    ajeIdList.add(ajePtrn);
                }
            }
        }

        // if no aje pattern matched by the AJE_ID of the transaction
        if (!isExist) {
            // Copy AJE Pattern List
            List<Map> ajePtrnListBackUp = new ArrayList<Map>();

            // copy list of ajePtrnList to ajePtrnListByInd
            for (Map<String, String> ajePtrnItem : ajePtrnList) {
                ajePtrnListBackUp.add(ajePtrnItem);
            }

            // Find default aje pattern
            String[] ajeIdSep = ajeId.split("-");
            // make default aje_id by aje_id of transaction
            String ajeIdTrx = SYS_SRC_CD_DEFAULT + "-" + ajeIdSep[1] + "-" + ajeIdSep[2];
            String orgSysSrc = ajeIdSep[0];

            for (Map<String, String> rsGetAjePtrnByAjeIdAndReasons : ajePtrnList) {
                String ajeIdInAjePtrn = rsGetAjePtrnByAjeIdAndReasons.get(AJE_ID);

                if (ajeIdTrx.equals(ajeIdInAjePtrn)) {

                    NFAC000101PMsg ajePtrn = new NFAC000101PMsg();

                    ajePtrn = setAJEPatternValue(rsGetAjePtrnByAjeIdAndReasons, ajeId, orgSysSrc, getJrnlSrcNm(orgSysSrc, ajePtrnListBackUp));

                    ajeIdList.add(ajePtrn);
                }
            }

        }

        return ajeIdList;
    }

    /**
     * <pre>
     *  Get journal source name by original system source code.
     * </pre>
     * @param sysSrcCd original system source code
     * @param ajePtrnList resultset of AJE Pattern List
     * @return String journal source name (system source name of
     * original system source code)
     */
    private String getJrnlSrcNm(String sysSrcCd, List<Map> ajePtrnList) {

        for (Map<String, String> ajePtrn : ajePtrnList) {

            if (sysSrcCd.equals(ajePtrn.get(JRNL_SRC_CD))) {
                return ajePtrn.get(JRNL_SRC_NM);
            }

        }

        return "";
    }

    private NFAC000101PMsg setAJEPatternValue(Map<String, String> rsGetAjePtrnByAjeIdAndReasons, String orgAjeId, String jrnlSrcCd, String jrnlSrcNm) {
        NFAC000101PMsg ajePtrn = new NFAC000101PMsg();

        ajePtrn.glblCmpyCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(GLBL_CMPY_CD)));
        ajePtrn.ajeId.setValue(orgAjeId);
        ajePtrn.sysSrcCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(SYS_SRC_CD)));
        ajePtrn.sysSrcNm.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(SYS_SRC_NM)));
        ajePtrn.trxCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(TRX_CD)));
        ajePtrn.trxNm.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(TRX_NM)));
        ajePtrn.trxRsnCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(TRX_RSN_CD)));
        ajePtrn.trxRsnNm.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(TRX_RSN_NM)));
        ajePtrn.ajePtrnIndTpCd_01.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(AJE_PTRN_IND_TP_CD_01)));
        ajePtrn.ajePtrnIndTpNm_01.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(AJE_PTRN_IND_TP_NM_01)));
        ajePtrn.ajePtrnActlCd_01.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(AJE_PTRN_ACTL_CD_01)));
        ajePtrn.ajePtrnActlNm_01.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(AJE_PTRN_ACTL_NM_01)));
        ajePtrn.ajePtrnIndTpCd_02.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(AJE_PTRN_IND_TP_CD_02)));
        ajePtrn.ajePtrnIndTpNm_02.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(AJE_PTRN_IND_TP_NM_02)));
        ajePtrn.ajePtrnActlCd_02.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(AJE_PTRN_ACTL_CD_02)));
        ajePtrn.ajePtrnActlNm_02.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(AJE_PTRN_ACTL_NM_02)));
        ajePtrn.ajePtrnIndTpCd_03.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(AJE_PTRN_IND_TP_CD_03)));
        ajePtrn.ajePtrnIndTpNm_03.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(AJE_PTRN_IND_TP_NM_03)));
        ajePtrn.ajePtrnActlCd_03.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(AJE_PTRN_ACTL_CD_03)));
        ajePtrn.ajePtrnActlNm_03.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(AJE_PTRN_ACTL_NM_03)));
        ajePtrn.jrnlSrcCd.setValue(checkNull(jrnlSrcCd));
        ajePtrn.jrnlSrcNm.setValue(checkNull(jrnlSrcNm));
        ajePtrn.jrnlCatgCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(JRNL_CATG_CD)));
        ajePtrn.jrnlCatgNm.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(JRNL_CATG_NM)));
        // ajePtrn.drCrTpCd.setValue(rsGetAjePtrnByAjeIdAndReasons.get(DR_CR_TP_CD));
        ajePtrn.ajeLinkFlg.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(AJE_LINK_FLG)));
        ajePtrn.ajeLineIdxNum.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(AJE_LINE_IDX_NUM)));

        // for DR
        ajePtrn.drAjeLineIdxDescTxt.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("DR_" + AJE_LINE_IDX_DESC_TXT)));
        ajePtrn.drAjeCoaCmpyCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("DR_" + AJE_COA_CMPY_CD)));
        ajePtrn.drAjeCoaBrCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("DR_" + AJE_COA_BR_CD)));
        ajePtrn.drAjeCoaCcCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("DR_" + AJE_COA_CC_CD)));
        ajePtrn.drAjeCoaAcctCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("DR_" + AJE_COA_ACCT_CD)));
        ajePtrn.drAjeCoaProdCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("DR_" + AJE_COA_PROD_CD)));
        ajePtrn.drAjeCoaChCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("DR_" + AJE_COA_CH_CD)));
        ajePtrn.drAjeCoaAfflCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("DR_" + AJE_COA_AFFL_CD)));
        ajePtrn.drAjeCoaProjCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("DR_" + AJE_COA_PROJ_CD)));
        ajePtrn.drAjeCoaExtnCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("DR_" + AJE_COA_EXTN_CD)));

        // for CR
        ajePtrn.crAjeLineIdxDescTxt.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("CR_" + AJE_LINE_IDX_DESC_TXT)));
        ajePtrn.crAjeCoaCmpyCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("CR_" + AJE_COA_CMPY_CD)));
        ajePtrn.crAjeCoaBrCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("CR_" + AJE_COA_BR_CD)));
        ajePtrn.crAjeCoaCcCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("CR_" + AJE_COA_CC_CD)));
        ajePtrn.crAjeCoaAcctCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("CR_" + AJE_COA_ACCT_CD)));
        ajePtrn.crAjeCoaProdCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("CR_" + AJE_COA_PROD_CD)));
        ajePtrn.crAjeCoaChCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("CR_" + AJE_COA_CH_CD)));
        ajePtrn.crAjeCoaAfflCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("CR_" + AJE_COA_AFFL_CD)));
        ajePtrn.crAjeCoaProjCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("CR_" + AJE_COA_PROJ_CD)));
        ajePtrn.crAjeCoaExtnCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get("CR_" + AJE_COA_EXTN_CD)));

        ajePtrn.ajeIntfcColTxt_01.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(AJE_INTFC_COL_TXT_01)));
        ajePtrn.ajeIntfcColTxt_02.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(AJE_INTFC_COL_TXT_02)));
        ajePtrn.ajeIntfcColTxt_03.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(AJE_INTFC_COL_TXT_03)));
        ajePtrn.ajeLineIndTpCd.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(AJE_LINE_IND_TP_CD)));

        ajePtrn.ajeLineIndTpNm.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(AJE_LINE_IND_TP_NM)));
        
        //---- start add 2016/02/01
        ajePtrn.jrnlAmtFlipFlg.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(JRNL_AMT_FLIP_FLG)));
        ajePtrn.jrnlQtyFlipFlg.setValue(checkNull(rsGetAjePtrnByAjeIdAndReasons.get(JRNL_QTY_FLIP_FLG)));
        //---- end 2016/02/01

        return ajePtrn;
    }

    /**
     * <pre>
     *  Set value to Journal Entry by the value in AJE Pattern
     *  Changed for CSA
     * </pre>
     * @param jrnlEntry TMsg of Journal Entry
     * @param coa COA segment enum class
     * @param crOrDr string of "Cr" or "Dr"
     * @param rsAjePtrnList pMsg of AJE Pattern
     * @param rs result set
     * @param mtList List of COA_PROJ_ACCT
     * @return boolean
     */
    public boolean setValueToJrnlEntryByAjePtrn(JRNL_ENTRYTMsg jrnlEntry, CoaSegment coa, String crOrDr, NFAC000101PMsg rsAjePtrnList, ResultSet rs, Map<String, List<MdseTpAcct>> mtMap) throws SQLException {
        String fieldNm = getCoAFieldNm(coa);
        String ajePtrnFieldNm = crOrDr + "Aje" + fieldNm;

        // Get value of AJE pattern
        String ptrnVal = rsAjePtrnList.getValueString(ajePtrnFieldNm, -1);

        String val;

        // if the value has prefix '@' then, get the designated value
        // from result set.
        if (ptrnVal.startsWith("@", 0)) {

            val = getPtrnValFromRs(crOrDr, ptrnVal, coa, rs, mtMap);
            
            if (val == null) {

                /* remove 2016/01/27 since inventory account of WH has been discontinued.
                // in case pattern is @ITEM-INV then retry with
                // @WH-INV
                if (COA_SEG_LKUP_TP_ITEM_INV.equals(ptrnVal)) {
                    val = getPtrnValFromRs(crOrDr, COA_SEG_LKUP_TP_WH_INV, coa, rs, whList);

                    if (val == null) {
                        // failure
                        return false;
                    }
                } */

                    //----start 2016/11/28 QC#16075
                    if ("@IB".equals(ptrnVal) && coa == CoaSegment.BR) {
                        // get COA Branch for Out Of Territory
                        if (outOfTrtryBr == null) {
                            outOfTrtryBr = getOutOfTrtryBr(rsAjePtrnList.glblCmpyCd.getValue());
                        }
                        val = outOfTrtryBr;
                    }
                    
                    // failure
                    //return false;
                    //---- end 2016/11/28 QC#16075
            }
            
            // if the retrieved value has prefix '@' then, retrieve the value from result set again.
            if (val != null && val.startsWith("@", 0)) {
                ptrnVal = val;
                val = getPtrnValFromRs(crOrDr, val, coa, rs, mtMap);

                if (val == null) {
                    
                    //----start 2016/11/28 QC#16075
                    if ("@IB".equals(ptrnVal) && coa == CoaSegment.BR) {
                        // get COA Branch for Out Of Territory
                        if (outOfTrtryBr == null) {
                            outOfTrtryBr = getOutOfTrtryBr(rsAjePtrnList.glblCmpyCd.getValue());
                        }
                        val = outOfTrtryBr;
                    }
                    // failure
                    //return false;
                  //---- end 2016/11/28 QC#16075
                }
            }

            // add 2016/01/26 retrieve default values from Varchar Const
        } else if (DEF_COA_VAL.equals(ptrnVal)) {

            val = getDefaultCoa(rsAjePtrnList.glblCmpyCd.getValue(), coa);

        } else {
            // Use fixed value in AJE pattern
            val = ptrnVal;
        }

        // if (fieldNm.equals("AjeLineIdxDescTxt")) {
        // ajePtrnFieldNm = crOrDr + fieldNm;
        // }

        //----start 2016/11/28 QC#16075
        if (val == null || "".equals(val)) {
            // set default value
            val = getDefaultCoa(rsAjePtrnList.glblCmpyCd.getValue(), coa);
            
            // set AJE_INTFC_CMNT_TXT
            String msg = "";
            if (hasValue(jrnlEntry.ajeIntfcCmntTxt.getValue())) {
                msg = jrnlEntry.ajeIntfcCmntTxt.getValue() + " |" + coa.toString();
            } else {
                msg = "**Default Value is Set: " + coa.toString();
            }
            if (msg.length() > 240) {
                msg = msg.substring(0, 240);
            }
            jrnlEntry.setDBValue("ajeIntfcCmntTxt", msg);
            // START 2019/05/21 E.Kameishi [QC#50407,DEL]
            // send notification email to IT Operation
            //if (emailSntFlg == null) {
            //    emailSntFlg = "Y";  // Email to be sent only once per batch.
                // START 2016/11/29 S.Fujita [QC#16075,ADD]
            //    eMailSend(rsAjePtrnList.glblCmpyCd.getValue());
                // END   2016/11/29 S.Fujita [QC#16075,ADD]
            //}
            // END 2019/05/21 E.Kameishi [QC#50407,DEL]
        }
        
        //---- end 2016/11/28 QC#16075
        
        // set the value to T message of JRNL_ENTRY
        jrnlEntry.setDBValue(crOrDr + fieldNm, checkNull(val));

        return true;

    }

    private String getDefaultCoa(String glblCmpyCd, CoaSegment coa) {
        String val = null;
        
        if (defCoa == null) {
            getDefCoaValues(glblCmpyCd);
        }

        switch (coa) {
            case CMPY:
                val = defCoa.getDefCoaCmpy();
                break;
            case BR:
                val = defCoa.getDefCoaBr();
                break;
            case CC:
                val = defCoa.getDefCoaCc();
                break;
            case ACCT:
                val = defCoa.getDefCoaAcct();
                break;
            case PROD:
                val = defCoa.getDefCoaProd();
                break;
            case CH:
                val = defCoa.getDefCoaCh();
                break;
            case AFFL:
                val = defCoa.getDefCoaAffl();
                break;
            case PROJ:
                val = defCoa.getDefCoaProj();
                break;
            case EXTN:
                val = defCoa.getDefCoaExtn();
                break;
            default:
                val = null;
                break;
        }
        
        return val;
    }
    
    /**
     * <pre>
     *  Get values of COA segment based on AJE pattern
     * </pre>
     * @param crOrDr string of "Cr" or "Dr"
     * @param ptrn AJE Pattern String
     * @param coa COA segment enum class
     * @param rs result set
     * @param whList List of RTL_WH_ACCT
     * @return String retrieved value
     */
    /* remove 2016/01/27
    private String getPtrnValFromRs(String crOrDr, String ptrn, CoaSegment coa, ResultSet rs, List<RtlWhAcct> whList) throws SQLException {
        String val = null;
        String fieldNmOfAt = "";
        String rtlWhCd = "";

        // If the pattern is "@WH-***" or "@ORIG-WH", retrieve value
        // from RTL_WH_ACCT
        if (ptrn.indexOf("@WH") != -1 || ptrn.indexOf("@ORIG-WH") != -1) {
            if (whList == null) { // if list of RTL_WH_ACCT is not
                                  // set, error.
                return null;
            }
            try {
                if (ptrn.indexOf("@WH") != -1) {
                    rtlWhCd = rs.getString("RTL_WH_CD");
                } else {
                    rtlWhCd = rs.getString("ORIG_RTL_WH_CD");
                }
                val = getAtWhValue(whList, ptrn, rtlWhCd, coa);
            } catch (SQLException sqlEx) {
                return null; // If RTL_WH_CD is not selected, error.
            }
        } else {

            fieldNmOfAt = ptrn.substring(1) + "_" + coa.toString() + "_CD"; // this
                                                                            // is
                                                                            // a
                                                                            // field
                                                                            // name
                                                                            // to
                                                                            // be
                                                                            // retrieved
                                                                            // and
                                                                            // which
                                                                            // is
                                                                            // pre-determined
                                                                            // by
                                                                            // rule.

            try {
                val = rs.getString(fieldNmOfAt);
            } catch (SQLException sqlEx) {

                try {
                    // retry the field name with "DR" or "CR"
                    fieldNmOfAt = crOrDr.toUpperCase() + "_" + ptrn.substring(1) + "_" + coa.toString() + "_CD";
                    val = rs.getString(fieldNmOfAt);
                } catch (SQLException ex) {
                    return null;
                }
            }
        }

        return val;
    }
    */

    private String getPtrnValFromRs(String crOrDr, String ptrn, CoaSegment coa, ResultSet rs, Map<String, List<MdseTpAcct>> mtMap) throws SQLException {
        String val = null;
        String fieldNmOfAt = "";
        String mtCd = "";

        // If the pattern is "@MT-***" from COA_PROJ_ACCT
        if (ptrn.indexOf("@MT") != -1 || ptrn.indexOf("@MMT") != -1) {
            if (mtMap == null) { // if list of COA_PROJ_ACCT is not set, error.
                return null;
            }
            try {
// START 2019/11/14 H.Umeda [QC#54357,MOD]
//              if (ptrn.indexOf("@MT") != -1) {
                if (ptrn.indexOf("@MT-ITEM") != -1) {
                    mtCd = rs.getString("LINE-ITEM_PROJ_CD");
                } else if (ptrn.indexOf("@MT") != -1) {
// END   2019/11/14 H.Umeda [QC#54357,MOD]
                    mtCd = rs.getString("ITEM_PROJ_CD");
                } else {
                    mtCd = rs.getString("MACH_PROJ_CD");
                }
                
                if (mtCd == null) {
                    return null;  // Merchandise Type Code is null
                }
                
                val = getAtMtValue(mtMap, ptrn, mtCd);
            } catch (SQLException sqlEx) {
                return null; // If COA_PROJ_CD is not in result set, error.
            }
        } else {
         // this is a field name to be retrieved and which is pre-determined by rule.
            fieldNmOfAt = ptrn.substring(1) + "_" + coa.toString() + "_CD"; 

            try {
                val = rs.getString(fieldNmOfAt);
            } catch (SQLException sqlEx) {

                try {
                    // retry the field name with "DR" or "CR"
                    fieldNmOfAt = crOrDr.toUpperCase() + "_" + ptrn.substring(1) + "_" + coa.toString() + "_CD";
                    val = rs.getString(fieldNmOfAt);
                } catch (SQLException ex) {
                    return null;
                }
            }
        }

        return val;
    }
    
    /**
     * <pre>
     *  Get values of COA segment of @WH.
     * </pre>
     * @param whList List of RTL_WH_ACCT
     * @param ptrn AJE Pattern String
     * @param rtlWhCd Retail WH Code
     * @param coa COA segment enum class
     * @return String retrieved value
     */
    private String getAtWhValue(List<RtlWhAcct> whList, String ptrn, String rtlWhCd, CoaSegment coa) {
        RtlWhAcct rtlWh = null;
        String ptrnString = "";

        // getting pattern string excludes '@WH-' or '@ORIG-WH-'.
        if (ptrn.indexOf("@WH") != -1) {
            ptrnString = ptrn.substring("@WH-".length(), ptrn.length());
        } else {
            ptrnString = ptrn.substring("@ORIG-WH-".length(), ptrn.length());
        }

        for (RtlWhAcct row : whList) {
            // search for designated RTL_WH_ACCT. (Type Name should
            // match with second part of AJE pattern string.)
            // ex. In case of @WH-INV, type name should be "INV".
            // With this rule, AJE can retrieve COA values even if
            // type is added.
            if (row.getRtlWhCd().equals(rtlWhCd) && row.getRtlWhAcctTpNm().equals(ptrnString)) {
                rtlWh = row;
                break;
            }
        }

        if (rtlWh == null) {
            return null;
        } else {
            switch (coa) {
                case BR:
                    return rtlWh.getCoaBrCd();
                case ACCT:
                    return rtlWh.getCoaAcctCd();
                default:
                    return null;
            }
        }
    }

    /**
     * <pre>
     *  Get values of COA segment of @MT.
     * </pre>
     * @param mtList List of COA_PROJ_ACCT
     * @param ptrn AJE Pattern String
     * @param mtCd MDSE Type Code
     * @return String retrieved value
     */
    private String getAtMtValue(Map<String, List<MdseTpAcct>> mtMap, String ptrn, String mtCd) {
        MdseTpAcct mtAcct = null;
        String ptrnString = "";

        // getting pattern string excludes '@MT-' or '@MMT'.
        if (ptrn.indexOf("@MT") != -1) {
            ptrnString = ptrn.substring("@MT-".length(), ptrn.length());
        } else {
            ptrnString = ptrn.substring("@MMT-".length(), ptrn.length());
        }
        
        // get list of COA_PROJ_ACCT for this merchandise type
        List<MdseTpAcct> mtList = mtMap.get(mtCd);
        
        for (MdseTpAcct row : mtList) {
            // search for designated COA_PROJ_ACCT. (Type Name should match with second part of AJE pattern string.) 
            //ex. In case of @MT-INV, type name should be "INV".
            // With this rule, AJE can retrieve COA values even if type is added.
            if (row.getCoaProjAcctTpNm().equals(ptrnString)) {
                mtAcct = row;
                break;
            }
        }

        if (mtAcct == null) {
            return null;
        } else {
            return mtAcct.getCoaAcctCd();
        }
    }
    
    /**
     * <pre>
     *  Get COA Field Name
     * </pre>
     * @param coa COA segment enum class
     * @return String field name of COA
     */
    public String getCoAFieldNm(CoaSegment coa) {
        switch (coa) {
            case CMPY:
                return "CoaCmpyCd";
            case BR:
                return "CoaBrCd";
            case CC:
                return "CoaCcCd";
            case ACCT:
                return "CoaAcctCd";
            case PROD:
                return "CoaProdCd";
            case CH:
                return "CoaChCd";
            case AFFL:
                return "CoaAfflCd";
            case PROJ:
                return "CoaProjCd";
            case EXTN:
                return "CoaExtnCd";
            default:
                return "";
        }

    }

    /**
     * <pre>
     *  Get COA text for error message
     * </pre>
     * @param coa COA segment enum class
     * @return String COA text
     */
    public String getCoAMessage(CoaSegment coa) {
        switch (coa) {
            case CMPY:
                return COA_ERROR_CMPY;
            case BR:
                return COA_ERROR_BR;
            case CC:
                return COA_ERROR_CC;
            case ACCT:
                return COA_ERROR_ACCT;
            case PROD:
                return COA_ERROR_PROD;
            case CH:
                return COA_ERROR_CH;
            case AFFL:
                return COA_ERROR_AFFL;
            case PROJ:
                return COA_ERROR_PROJ;
            case EXTN:
                return COA_ERROR_EXTN;
            default:
                return "";
        }

    }

    /**
     * <pre>
     *  Set value to Journal Entry by the value in parameter
     *  
     * </pre>
     * @param jrnlEntry TMsg of Journal Entry
     * @param fieldNm field name to be set
     * @param crOrDr string of "Cr" or "Dr"
     * @param val value to be set
     */
    public void setValueToJrnlEntryByPrm(JRNL_ENTRYTMsg jrnlEntry, String fieldNm, String crOrDr, Object val) {

        jrnlEntry.setDBValue(crOrDr + fieldNm, val);
    }

    /**
     * <pre>
     *  Get value of AJE Pattern
     *  
     * </pre>
     * @param rsAjePtrnList PMsg of AJE Pattern
     * @param fieldNm field name to be gotten
     * @param crOrDr string of "Cr" or "Dr"
     * @return String the value of AJE Pattern
     */
    public String getValueOfAjePtrn(NFAC000101PMsg rsAjePtrnList, String fieldNm, String crOrDr) {

        return rsAjePtrnList.getValueString(crOrDr + fieldNm, -1);

    }

    private boolean isNotEssMapping(COAValuesByCases val) {
        for (Map<String, String> sysSrcEach : sysSrcMap) {
            // if (val.sysSrcCd.equals(sysSrcEach.get("SYS_SRC_CD")))
            // {
            if (sysSrcEach.get("SYS_SRC_CD").equals(val.sysSrcCd)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     *  Get Value Of COA Code by patch tables
     *  
     *  For COA_BR_CD, COA_CC_CD, properties whose name start by 'valByAffl' should be set null or "".
     * </pre>
     * @param val COAValuesByCases
     * @return String value of COA Channel to be set
     */
    public String getValueOfCoaByCases(COAValuesByCases val) {

        if (isNotEssMapping(val)) {
            return val.valOriginal; // exception
        }

        // if AJE Pattern is @TOC then
        if (checkCoaSegLkupTpCdForToc(val.ajePtrnCoaCd)) {

            // --------- by affiliate
            // 1. PROD=@Item, AFFL=@Cust
            if (checkCoaSegLkupTpCdForItem(val.ajePtrnCoaProdCd) && checkCoaSegLkupTpCdForCust(val.ajePtrnCoaAfflCd)) {
                if (val.valByAfflAtItemAtCust != null && !val.valByAfflAtItemAtCust.equals("")) {
                    return val.valByAfflAtItemAtCust;
                }
            }
            // 2. PROD=@Link, AFFL='000'
            if (checkCoaSegLkupTpCdForLink(val.ajePtrnCoaProdCd) && val.ajePtrnCoaAfflCd.equals(COA_AFFL_CD_VAL_000)) {
                if (val.valByAfflAtLinkAtPtrn != null && !val.valByAfflAtLinkAtPtrn.equals("")) {
                    return val.valByAfflAtLinkAtPtrn;
                }
            }
            // 3. PROD=@Item, AFFL='000'
            if (checkCoaSegLkupTpCdForItem(val.ajePtrnCoaProdCd) && val.ajePtrnCoaAfflCd.equals(COA_AFFL_CD_VAL_000)) {
                if (val.valByAfflAtItemAtPtrn != null && !val.valByAfflAtItemAtPtrn.equals("")) {
                    return val.valByAfflAtItemAtPtrn;
                }
            }
            // 4. PROD='ZZ', AFFL=@Cust
            if (val.ajePtrnCoaProdCd.equals(COA_PROD_CD_VAL_ZZ) && checkCoaSegLkupTpCdForCust(val.ajePtrnCoaAfflCd)) {
                if (val.valByAfflAtPtrnAtCust != null && !val.valByAfflAtPtrnAtCust.equals("")) {
                    return val.valByAfflAtPtrnAtCust;
                }
            }
            // 5. PROD=@Link, AFFL=@Cust
            if (checkCoaSegLkupTpCdForLink(val.ajePtrnCoaProdCd) && checkCoaSegLkupTpCdForCust(val.ajePtrnCoaAfflCd)) {
                if (val.valByAfflAtLinkAtCust != null && !val.valByAfflAtLinkAtCust.equals("")) {
                    return val.valByAfflAtLinkAtCust;
                }
            }
            // 6. PROD=@Toc, AFFL=@Cust
            if (checkCoaSegLkupTpCdForToc(val.ajePtrnCoaProdCd) && checkCoaSegLkupTpCdForCust(val.ajePtrnCoaAfflCd)) {
                if (val.valByAfflAtTocAtCust != null && !val.valByAfflAtTocAtCust.equals("")) {
                    return val.valByAfflAtTocAtCust;
                }
            }
            // 6. PROD=@Toc, AFFL='000'
            if (checkCoaSegLkupTpCdForToc(val.ajePtrnCoaProdCd) && COA_AFFL_CD_VAL_000.equals(val.ajePtrnCoaAfflCd)) {
                if (val.valByAfflAtTocAtPtrn != null && !val.valByAfflAtTocAtPtrn.equals("")) {
                    return val.valByAfflAtTocAtPtrn;
                }
            }
            // 7. PROD=@Link, AFFL=@Link
            if (checkCoaSegLkupTpCdForLink(val.ajePtrnCoaProdCd) && checkCoaSegLkupTpCdForLink(val.ajePtrnCoaAfflCd)) {
                if (val.valByAfflAtLinkAtLink != null && !val.valByAfflAtLinkAtLink.equals("")) {
                    return val.valByAfflAtLinkAtLink;
                }
            }

            // --------- by product
            // 1. @Item
            if (checkCoaSegLkupTpCdForItem(val.ajePtrnCoaProdCd)) {
                if (val.valByDeptProdAtItem != null && !val.valByDeptProdAtItem.equals("")) {
                    return val.valByDeptProdAtItem;
                }
            }
            // 2. @Link
            if (checkCoaSegLkupTpCdForLink(val.ajePtrnCoaProdCd)) {
                if (val.valByDeptProdAtLink != null && !val.valByDeptProdAtLink.equals("")) {
                    return val.valByDeptProdAtLink;
                }
            }
            // 3. 'ZZ'
            if (val.ajePtrnCoaProdCd.equals(COA_PROD_CD_VAL_ZZ)) {
                if (val.valByDeptProdAtPtrn != null && !val.valByDeptProdAtPtrn.equals("")) {
                    return val.valByDeptProdAtPtrn;
                }
            }
            // 4. @Toc
            if (checkCoaSegLkupTpCdForToc(val.ajePtrnCoaProdCd)) {
                if (val.valByDeptProdAtToc != null && !val.valByDeptProdAtToc.equals("")) {
                    return val.valByDeptProdAtToc;
                }
            }

        }
        return val.valOriginal;
    }

    /**
     * <pre>
     *  Change Size of Array
     *  
     * </pre>
     * @param arrayRec Array of TMsg
     * @param newsize size
     * @return EZDTMsg[] changed array
     */
    public EZDTMsg[] changeArraySize(EZDTMsg[] arrayRec, int newsize) {

        EZDTMsg[] copyMsgs = arrayRec.clone();
        arrayRec = new EZDTMsg[newsize];
        // copy data
        for (int i = 0; i < newsize; i++) {
            arrayRec[i] = copyMsgs[i];
        }

        return arrayRec;
    }

    /**
     * COAValuesByCases
     * 
     * <pre>
     * 
     * </pre>
     */
    public class COAValuesByCases {
        private String ajePtrnCoaCd;

        private String ajePtrnCoaAfflCd;

        private String ajePtrnCoaProdCd;

        private String valOriginal;

        private String valByDeptProdAtItem;

        private String valByDeptProdAtLink;

        private String valByDeptProdAtPtrn;

        private String valByDeptProdAtToc;

        private String valByAfflAtItemAtCust;

        private String valByAfflAtLinkAtPtrn;

        private String valByAfflAtItemAtPtrn;

        private String valByAfflAtPtrnAtCust;

        private String valByAfflAtLinkAtCust;

        private String valByAfflAtTocAtCust;

        private String valByAfflAtTocAtPtrn;

        private String valByAfflAtLinkAtLink;

        private String sysSrcCd;

        public COAValuesByCases() {
            clear();
        }

        public void clear() {
            this.ajePtrnCoaCd = "";
            this.ajePtrnCoaAfflCd = "";
            this.ajePtrnCoaProdCd = "";

            this.valOriginal = "";

            this.valByDeptProdAtItem = "";
            this.valByDeptProdAtLink = "";
            this.valByDeptProdAtPtrn = "";
            this.valByDeptProdAtToc = "";

            this.valByAfflAtItemAtCust = "";
            this.valByAfflAtItemAtPtrn = "";
            this.valByAfflAtLinkAtCust = "";
            this.valByAfflAtLinkAtPtrn = "";
            this.valByAfflAtPtrnAtCust = "";
            this.valByAfflAtTocAtCust = "";
            this.valByAfflAtTocAtPtrn = "";
            this.valByAfflAtLinkAtLink = "";

            // this.sysSrcCd = ""; it should be same in the record
        }

        public String getAjePtrnCoaCd() {
            return ajePtrnCoaCd;
        }

        public void setAjePtrnCoaCd(String ajePtrnCoaCd) {

            this.ajePtrnCoaCd = ajePtrnCoaCd;
        }

        public String getAjePtrnCoaAfflCd() {
            return ajePtrnCoaAfflCd;
        }

        public void setAjePtrnCoaAfflCd(String ajePtrnCoaAfflCd) {

            this.ajePtrnCoaAfflCd = ajePtrnCoaAfflCd;
        }

        public String getAjePtrnCoaProdCd() {
            return ajePtrnCoaProdCd;
        }

        public void setAjePtrnCoaProdCd(String ajePtrnCoaProdCd) {
            this.ajePtrnCoaProdCd = ajePtrnCoaProdCd;
        }

        public String getValOriginal() {
            return valOriginal;
        }

        public void setValOriginal(String valOriginal) {

            this.valOriginal = valOriginal;
        }

        public String getValByAfflAtItemAtCust() {
            return valByAfflAtItemAtCust;
        }

        public void setValByAfflAtItemAtCust(String valByAfflAtItemAtCust) {
            this.valByAfflAtItemAtCust = valByAfflAtItemAtCust;
        }

        public String getValByAfflAtItemAtPtrn() {
            return valByAfflAtItemAtPtrn;
        }

        public void setValByAfflAtItemAtPtrn(String valByAfflAtItemAtPtrn) {
            this.valByAfflAtItemAtPtrn = valByAfflAtItemAtPtrn;
        }

        public String getValByAfflAtLinkAtCust() {
            return valByAfflAtLinkAtCust;
        }

        public void setValByAfflAtLinkAtCust(String valByAfflAtLinkAtCust) {
            this.valByAfflAtLinkAtCust = valByAfflAtLinkAtCust;
        }

        public String getValByAfflAtLinkAtPtrn() {
            return valByAfflAtLinkAtPtrn;
        }

        public void setValByAfflAtLinkAtPtrn(String valByAfflAtLinkAtPtrn) {
            this.valByAfflAtLinkAtPtrn = valByAfflAtLinkAtPtrn;
        }

        public String getValByAfflAtPtrnAtCust() {
            return valByAfflAtPtrnAtCust;
        }

        public void setValByAfflAtPtrnAtCust(String valByAfflAtPtrnAtCust) {
            this.valByAfflAtPtrnAtCust = valByAfflAtPtrnAtCust;
        }

        public String getValByAfflAtTocAtCust() {
            return valByAfflAtTocAtCust;
        }

        public void setValByAfflAtTocAtCust(String valByAfflAtTocAtCust) {
            this.valByAfflAtTocAtCust = valByAfflAtTocAtCust;
        }

        public String getValByAfflAtTocAtPtrn() {
            return valByAfflAtTocAtPtrn;
        }

        public void setValByAfflAtTocAtPtrn(String valByAfflAtTocAtPtrn) {
            this.valByAfflAtTocAtPtrn = valByAfflAtTocAtPtrn;
        }

        public String getValByDeptProdAtItem() {
            return valByDeptProdAtItem;
        }

        public void setValByDeptProdAtItem(String valByDeptProdAtItem) {
            this.valByDeptProdAtItem = valByDeptProdAtItem;
        }

        public String getValByDeptProdAtLink() {
            return valByDeptProdAtLink;
        }

        public void setValByDeptProdAtLink(String valByDeptProdAtLink) {
            this.valByDeptProdAtLink = valByDeptProdAtLink;
        }

        public String getValByDeptProdAtPtrn() {
            return valByDeptProdAtPtrn;
        }

        public void setValByDeptProdAtPtrn(String valByDeptProdAtPtrn) {
            this.valByDeptProdAtPtrn = valByDeptProdAtPtrn;
        }

        public String getValByDeptProdAtToc() {
            return valByDeptProdAtToc;
        }

        public void setValByDeptProdAtToc(String valByDeptProdAtToc) {
            this.valByDeptProdAtToc = valByDeptProdAtToc;
        }

        public String getValByAfflAtLinkAtLink() {
            return valByAfflAtLinkAtLink;
        }

        public void setValByAfflAtLinkAtLink(String valByAfflAtLinkAtLink) {
            this.valByAfflAtLinkAtLink = valByAfflAtLinkAtLink;
        }

        public String getSysSrcCd() {
            return sysSrcCd;
        }

        public void setSysSrcCd(String sysSrcCd) {

            this.sysSrcCd = checkNull(sysSrcCd);
        }
    }

    /**
     * checkNull
     * 
     * <pre>
     *  If the value is null, then return ""
     *  
     * </pre>
     * @param val String
     * @return String
     */
    public String checkNull(String val) {
        if (ZYPCommonFunc.hasValue(val)) {
            return val;
        } else {
            return BLANK;
        }
    }

    /**
     * checkNull
     * 
     * <pre>
     *  If the value is null, then return 0
     *  This function should be used only if null value can be converted to 0.
     *  If null and 0 should be treated as different value, this function should not be used.
     * </pre>
     * @param val BigDecimal
     * @return BigDecimal
     */
    public BigDecimal checkNull(BigDecimal val) {
        if (ZYPCommonFunc.hasValue(val)) {
            return val;
        } else {
            return new BigDecimal(0);
        }
    }

    /**
     * <pre>
     *  Remove records per a certain amount of records.
     *  @param rmvRec TMsg to be removed
     *  @throws JrnlCommonException JrnlCommonException
     * </pre>
     */
    public void removeJrnlEntryByPartialVal(List<JRNL_ENTRYTMsg> rmvList) throws JrnlCommonException {

        for (int i = 0; i < rmvList.size(); i++) {
            int retCnt = S21FastTBLAccessor.removeByPartialValue(rmvList.get(i), new String[] {"glblCmpyCd", "ajeIntfcTpCd", "ajeIntfcPk" });

            if (retCnt == -1) {
                throw new JrnlCommonException(ZZBM0074E);
            }
        }
    }

    /**
     * chngAJEPtrnByInvtyCtrlFlg
     * 
     * <pre>
     *  In case non ship sales of ross or parts, change AJE pattern of coa_prod_cd to '@TOC
     * @param crOrDr Cr or Dr
     * @param jrnlEntry TMsg of Journal Entry that is being generated
     * @param rsAjeIntfcNotJrnlized result set of interface table    
     * @throws SQLException SQL Exception
     * </pre>
     */
    /*
     * public void chngAJEPtrnByInvtyCtrlFlg(String crOrDr, String
     * invtyValFlg, String coaProdCd, NFAC000101PMsg rsAjePtrnList)
     * throws SQLException{ // check AJE_ID if
     * (isContained(rsAjePtrnList.ajeId.getValue(),
     * NO_SHIP_SALES_AJE_ID)) { //check Line indicator type // In Case
     * aje_pattern = @ITEM and sales or expense line if
     * ((AJE_LINE_IND_TP_CD_SALES
     * .equals(rsAjePtrnList.ajeLineIndTpCd.getValue()) &&
     * COA_SEG_LKUP_TP_ITEM_VAL
     * .equals(getValueOfAjePtrn(rsAjePtrnList, "AjeCoaProdCd",
     * crOrDr))) ||
     * (AJE_LINE_IND_TP_CD_EXPENSE.equals(rsAjePtrnList.ajeLineIndTpCd
     * .getValue()) &&
     * COA_SEG_LKUP_TP_ITEM_VAL.equals(getValueOfAjePtrn
     * (rsAjePtrnList, "AjeCoaProdCd", crOrDr)))) { // check
     * INVTY_VAL_FLG and COA_PROD_CD retrieved from MDSE master if
     * (FLG_OFF_N.equals(checkNull(invtyValFlg)) &&
     * COA_PROD_CD_VAL_ZZ.equals(checkNull(coaProdCd))) { // Change
     * aje pattern value of COA_PROD_CD to @TOC if
     * ("cr".equals(crOrDr)) { // deleted. COA_PROD_CD can be
     * retrieved by @Link
     * //rsAjePtrnList.crAjeCoaProdCd.setValue(COA_SEG_LKUP_TP_TOC_VAL
     * ); } else { // deleted. COA_PROD_CD can be retrieved by @Link
     * //
     * rsAjePtrnList.drAjeCoaProdCd.setValue(COA_SEG_LKUP_TP_TOC_VAL);
     * } } } } }
     */

    private boolean isContained(String val, String[] vals) {

        for (String str : vals) {

            if (str.equals(val)) {
                return true;
            }

        }

        return false;
    }

    /**
     * setValue for String
     * @param tmsg TMsg
     * @param columnNm column name
     * @param val value to be set </pre>
     */
    public void setFieldValue(EZDTMsg tmsg, String columnNm, String val) {

        tmsg.setDBValue(columnNm, checkNull(val));

    }

    /**
     * setValue for BigDecimal
     * @param tmsg TMsg
     * @param columnNm column name
     * @param val value to be set </pre>
     */
    public void setFieldValue(EZDTMsg tmsg, String columnNm, BigDecimal val) {

        tmsg.setDBValue(columnNm, checkNull(val));

    }

    private class AjeIdAndSysSrc {
        private String ajeId;

        private String sysSrc;

        /**
         * Method name: setAjeId <dd>Remarks:
         * @param val String
         */
        public void setAjeId(String val) {
            this.ajeId = val;
        }

        /**
         * Method name: getAjeId <dd>Remarks:
         * @return val String
         */
        public String getAjeId() {
            return this.ajeId;
        }

        /**
         * Method name: setSysSrc <dd>Remarks:
         * @param val String
         */
        public void setSysSrc(String val) {
            this.sysSrc = val;
        }

        /**
         * Method name: getSysSrc <dd>Remarks:
         * @return val String
         */
        public String getSysSrc() {
            return this.sysSrc;
        }
    }

    /**
     * <pre>
     *  JrnlCommonException
     *  Not like S21AbendException it doesn't terminate the program. By passing message ID,
     *  getMessage() returns its description.
     * </pre>
     */
    public class JrnlCommonException extends java.lang.Exception {

        /**
         * 
         */
        private static final long serialVersionUID = -4646261466521510100L;

        /**
         * Message ID
         */
        private String msg;

        /**
         * [ ]It is a default constructor.
         * <p>
         * [Detailed explanation]
         * 
         * <pre>
         * </pre>
         */
        public JrnlCommonException() {
            super();
        }

        /**
         * [ ]It is a default constructor.
         * <p>
         * [Detailed explanation]
         * 
         * <pre>
         * </pre>
         * @param msg [I] Detailed message
         */
        public JrnlCommonException(String msg) {
            super(msg);
            this.msg = msg;
        }

        /**
         * [ ]The exception originally thrown out is buried and
         * JrnlCommonException is composed.
         * <p>
         * [Detailed explanation]
         * 
         * <pre>
         * </pre>
         * @param ex [I] Exception originally thrown out
         */
        public JrnlCommonException(Exception ex) {
            super(ex);
        }

        /**
         * [ ]JrnlCommonException that buries the stern and the
         * exception thrown out with a specific, detailed message is
         * composed.
         * <p>
         * [Detailed explanation]
         * 
         * <pre>
         * </pre>
         * @param msg [I] Detailed message
         * @param ex [I] Exception originally thrown out
         */
        public JrnlCommonException(String msg, Exception ex) {

            super(msg, ex);
        }

        /**
         * Message Text of passed Message ID
         * <p>
         * @return String message text
         */
        public String getMessage() {
            return S21MessageFunc.clspGetMessage(msg);
        }

    }

    /**
     * <pre>
     * getCoaSegLkup
     * 
     * <pre>
     */
    /*
     * @SuppressWarnings("unchecked") public Map getCoaSegLkup(String
     * glblCmpyCd, String coaSegLkupTpCd) { Map<String, String>
     * queryParam = new HashMap<String, String>();
     * queryParam.put("glblCmpyCd", glblCmpyCd);
     * queryParam.put("coaSegLkupTpCd", coaSegLkupTpCd); Map ssmRes =
     * (Map) ssmBatchClient.queryObject("getCoaSegLkup", queryParam);
     * return ssmRes; }
     */
    /**
     * @param glblCmpyCd String
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Map> getCoaSegLkupAll(String glblCmpyCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);

        List<Map> ssmRes = (List<Map>) ssmBatchClient.queryObjectList("getCoaSegLkupAll", queryParam);

        return ssmRes;
    }

    @SuppressWarnings("unchecked")
    public String getDistData(String glblCmpyCd, List<Map> coaSegLkupList, String coaSegLkupCd, String coaAttrNm, ResultSet srcRec) throws SQLException {

        String resultCode = null;

        if (coaSegLkupCd.equals(COA_SEG_LKUP_TP_IB_VAL)) {
            resultCode = getDistData_OTHER(glblCmpyCd, coaSegLkupList, coaSegLkupCd, coaAttrNm, srcRec);
        } else if (coaSegLkupCd.equals(COA_SEG_LKUP_TP_MACHINE_VAL)) {
            resultCode = getDistData_OTHER(glblCmpyCd, coaSegLkupList, coaSegLkupCd, coaAttrNm, srcRec);
        } else if (coaSegLkupCd.equals(COA_SEG_LKUP_TP_ITEM_VAL)) {
            resultCode = getDistData_OTHER(glblCmpyCd, coaSegLkupList, coaSegLkupCd, coaAttrNm, srcRec);
        } else {
            resultCode = getDistData_NORMAL(glblCmpyCd, coaSegLkupList, coaSegLkupCd, coaAttrNm, srcRec);
        }
        return resultCode;
    }

    @SuppressWarnings("unchecked")
    public String getDistData_NORMAL(String glblCmpyCd, List<Map> coaSegLkupList, String coaSegLkupCd, String coaAttrNm, ResultSet srcRec) throws SQLException {

        String resultCode = null;

        //---- start  add logic for default value 2016/01/26 
        if ("#".equals(coaSegLkupCd.substring(0,1))) {
            if (defCoa == null) {
                getDefCoaValues(glblCmpyCd);
            }

           if (COA_ATTRB_NM_01.equals(coaAttrNm)) {
               return defCoa.getDefCoaCmpy();
           } else if (COA_ATTRB_NM_02.equals(coaAttrNm)) {
               return defCoa.getDefCoaBr();
           } else if (COA_ATTRB_NM_03.equals(coaAttrNm)) {
               return defCoa.getDefCoaCc();
           } else if (COA_ATTRB_NM_04.equals(coaAttrNm)) {
               return defCoa.getDefCoaAcct();
           } else if (COA_ATTRB_NM_05.equals(coaAttrNm)) {
               return defCoa.getDefCoaProd();
           } else if (COA_ATTRB_NM_06.equals(coaAttrNm)) {
               return defCoa.getDefCoaCh();
           } else if (COA_ATTRB_NM_07.equals(coaAttrNm)) {
               return defCoa.getDefCoaAffl();
           } else if (COA_ATTRB_NM_08.equals(coaAttrNm)) {
               return defCoa.getDefCoaProj();
           } else if (COA_ATTRB_NM_09.equals(coaAttrNm)) {
               return defCoa.getDefCoaExtn();
           }
        }
        //---- end 2016/01/26
        
        try {
            for (Map<String, String> coaSegLkup : coaSegLkupList) {
                if (coaSegLkup.get(COA_SEG_LKUP_TP_CD).toString().equals(coaSegLkupCd)) {
                    String distCoaAttrNm = null;
                    String srcTblNm = (String) coaSegLkup.get(SRC_TBL_NM);
                    String srcAttrNm_01 = (String) coaSegLkup.get(SRC_ATTRB_NM_01);
                    String srcAttrNm_02 = (String) coaSegLkup.get(SRC_ATTRB_NM_02);

                    if (coaAttrNm.equals(COA_ATTRB_NM_01)) {
                        if (ZYPCommonFunc.hasValue((String) coaSegLkup.get(COA_ATTRB_NM_01))) {
                            distCoaAttrNm = (String) coaSegLkup.get(COA_ATTRB_NM_01);
                        }
                    } else if (coaAttrNm.equals(COA_ATTRB_NM_02)) {
                        if (ZYPCommonFunc.hasValue((String) coaSegLkup.get(COA_ATTRB_NM_02))) {
                            distCoaAttrNm = (String) coaSegLkup.get(COA_ATTRB_NM_02);
                        }
                    } else if (coaAttrNm.equals(COA_ATTRB_NM_03)) {
                        if (ZYPCommonFunc.hasValue((String) coaSegLkup.get(COA_ATTRB_NM_03))) {
                            distCoaAttrNm = (String) coaSegLkup.get(COA_ATTRB_NM_03);
                        }
                    } else if (coaAttrNm.equals(COA_ATTRB_NM_04)) {
                        if (ZYPCommonFunc.hasValue((String) coaSegLkup.get(COA_ATTRB_NM_04))) {
                            distCoaAttrNm = (String) coaSegLkup.get(COA_ATTRB_NM_04);
                        }
                    } else if (coaAttrNm.equals(COA_ATTRB_NM_05)) {
                        if (ZYPCommonFunc.hasValue((String) coaSegLkup.get(COA_ATTRB_NM_05))) {
                            distCoaAttrNm = (String) coaSegLkup.get(COA_ATTRB_NM_05);
                        }
                    } else if (coaAttrNm.equals(COA_ATTRB_NM_06)) {
                        if (ZYPCommonFunc.hasValue((String) coaSegLkup.get(COA_ATTRB_NM_06))) {
                            distCoaAttrNm = (String) coaSegLkup.get(COA_ATTRB_NM_06);
                        }
                    } else if (coaAttrNm.equals(COA_ATTRB_NM_07)) {
                        if (ZYPCommonFunc.hasValue((String) coaSegLkup.get(COA_ATTRB_NM_07))) {
                            distCoaAttrNm = (String) coaSegLkup.get(COA_ATTRB_NM_07);
                        }
                    } else if (coaAttrNm.equals(COA_ATTRB_NM_08)) {
                        if (ZYPCommonFunc.hasValue((String) coaSegLkup.get(COA_ATTRB_NM_08))) {
                            distCoaAttrNm = (String) coaSegLkup.get(COA_ATTRB_NM_08);
                        }
                    } else if (coaAttrNm.equals(COA_ATTRB_NM_09)) {
                        if (ZYPCommonFunc.hasValue((String) coaSegLkup.get(COA_ATTRB_NM_09))) {
                            distCoaAttrNm = (String) coaSegLkup.get(COA_ATTRB_NM_09);
                        }
                    }
                    if (!ZYPCommonFunc.hasValue(distCoaAttrNm)) {
                        break;
                    }

                    if (!ZYPCommonFunc.hasValue(srcAttrNm_02)) {
                        break;
                    }

                    String srcAttrCd = srcRec.getString(srcAttrNm_02);
                    if (srcAttrCd == null) {
                        break;
                    }

                    Map<String, String> queryParam = new HashMap<String, String>();
                    queryParam.put("glblCmpyCd", glblCmpyCd);
                    queryParam.put("srcTblNm", srcTblNm);
                    queryParam.put("srcAttrNm", srcAttrNm_01);
                    queryParam.put("srcAttrCd", srcAttrCd);
                    queryParam.put("distAttrNm", distCoaAttrNm);
                    Map ssmRes = (Map) ssmBatchClient.queryObject("getDistData", queryParam);
                    // when no data found, ssmRes is null
                    //if (ssmRes.get("RESULT_CD") == null) {
                    if (ssmRes == null) {
                        break;
                    }
                    resultCode = (String) ssmRes.get("RESULT_CD");
                    break;
                }
            }

        } catch (java.sql.SQLException ex) {
            // If the attribute name in the lookup table cannot be
            // retrieved from the result set.
            return null;
        }

        return resultCode;
    }

    @SuppressWarnings("unchecked")
    public String getDistData_OTHER(String glblCmpyCd, List<Map> coaSegLkupList, String coaSegLkupCd, String coaAttrNm, ResultSet srcRec) throws SQLException {

        String resultCode = null;

        for (Map<String, String> coaSegLkup : coaSegLkupList) {
            if (coaSegLkup.get("COA_SEG_LKUP_TP_CD").toString().equals(coaSegLkupCd)) {
                String distCoaAttrNm = null;
                String srcTblNm = (String) coaSegLkup.get(SRC_TBL_NM);
                String srcAttrNm_01 = (String) coaSegLkup.get(SRC_ATTRB_NM_01);
                String srcAttrNm_02 = (String) coaSegLkup.get(SRC_ATTRB_NM_02);

                if (coaAttrNm.equals(COA_ATTRB_NM_01)) {
                    if (ZYPCommonFunc.hasValue((String) coaSegLkup.get(COA_ATTRB_NM_01))) {
                        distCoaAttrNm = (String) coaSegLkup.get(COA_ATTRB_NM_01);
                    }
                } else if (coaAttrNm.equals(COA_ATTRB_NM_02)) {
                    if (ZYPCommonFunc.hasValue((String) coaSegLkup.get(COA_ATTRB_NM_02))) {
                        distCoaAttrNm = (String) coaSegLkup.get(COA_ATTRB_NM_02);
                    }
                } else if (coaAttrNm.equals(COA_ATTRB_NM_03)) {
                    if (ZYPCommonFunc.hasValue((String) coaSegLkup.get(COA_ATTRB_NM_03))) {
                        distCoaAttrNm = (String) coaSegLkup.get(COA_ATTRB_NM_03);
                    }
                } else if (coaAttrNm.equals(COA_ATTRB_NM_04)) {
                    if (ZYPCommonFunc.hasValue((String) coaSegLkup.get(COA_ATTRB_NM_04))) {
                        distCoaAttrNm = (String) coaSegLkup.get(COA_ATTRB_NM_04);
                    }
                } else if (coaAttrNm.equals(COA_ATTRB_NM_05)) {
                    if (ZYPCommonFunc.hasValue((String) coaSegLkup.get(COA_ATTRB_NM_05))) {
                        distCoaAttrNm = (String) coaSegLkup.get(COA_ATTRB_NM_05);
                    }
                } else if (coaAttrNm.equals(COA_ATTRB_NM_06)) {
                    if (ZYPCommonFunc.hasValue((String) coaSegLkup.get(COA_ATTRB_NM_06))) {
                        distCoaAttrNm = (String) coaSegLkup.get(COA_ATTRB_NM_06);
                    }
                } else if (coaAttrNm.equals(COA_ATTRB_NM_07)) {
                    if (ZYPCommonFunc.hasValue((String) coaSegLkup.get(COA_ATTRB_NM_07))) {
                        distCoaAttrNm = (String) coaSegLkup.get(COA_ATTRB_NM_07);
                    }
                } else if (coaAttrNm.equals(COA_ATTRB_NM_08)) {
                    if (ZYPCommonFunc.hasValue((String) coaSegLkup.get(COA_ATTRB_NM_08))) {
                        distCoaAttrNm = (String) coaSegLkup.get(COA_ATTRB_NM_08);
                    }
                } else if (coaAttrNm.equals(COA_ATTRB_NM_09)) {
                    if (ZYPCommonFunc.hasValue((String) coaSegLkup.get(COA_ATTRB_NM_09))) {
                        distCoaAttrNm = (String) coaSegLkup.get(COA_ATTRB_NM_09);
                    }
                }
                if (!ZYPCommonFunc.hasValue(distCoaAttrNm)) {
                    break;
                }

                if (!ZYPCommonFunc.hasValue(srcAttrNm_01)) {
                    break;
                }

                if (!ZYPCommonFunc.hasValue(srcAttrNm_02)) {
                    break;
                }

                String srcAttrCd1 = srcRec.getString(srcAttrNm_01);
                if (srcAttrCd1 == null) {
                    break;
                }

                String srcAttrCd2 = srcRec.getString(srcAttrNm_02);
                if (srcAttrCd2 == null) {
                    break;
                }

                Map<String, String> queryParam = new HashMap<String, String>();
                queryParam.put("glblCmpyCd", glblCmpyCd);
                queryParam.put("srcAttrCd1", srcAttrCd1);
                queryParam.put("srcAttrCd2", srcAttrCd2);
                // QC#22290 Start
                queryParam.put("flgY", ZYPConstant.FLG_ON_Y);
                // QC#22290 End

                if (coaSegLkupCd.equals(COA_SEG_LKUP_TP_IB_VAL)) {
                    /*
                     * Map ssmRes = (Map)
                     * ssmBatchClient.queryObject("getDistData_IB",
                     * queryParam);
                     * if(ssmRes.get(distCoaAttrNm)==null) { break; }
                     * resultCode = (String)ssmRes.get(distCoaAttrNm);
                     */
                    // @IB will be handled by service team. AJE will
                    // create journal entry from the values in
                    // DS_INV_SLS_CR
                    resultCode = null;
                    break;
                } else if (coaSegLkupCd.equals(COA_SEG_LKUP_TP_MACHINE_VAL)) {
                    Map ssmRes = (Map) ssmBatchClient.queryObject("getDistData_MACHINE", queryParam);
                    // when no data found, ssmRes is null
                    //if (ssmRes.get(distCoaAttrNm) == null) {
                    if (ssmRes == null) {
                        break;
                    }
                    resultCode = (String) ssmRes.get(distCoaAttrNm);
                } else if(coaSegLkupCd.equals(COA_SEG_LKUP_TP_ITEM_VAL)) { 
                    Map ssmRes = (Map) ssmBatchClient.queryObject("getDistData_ITEM",queryParam);
                    // when no data found, ssmRes is null
                    //if(ssmRes.get(distCoaAttrNm) == null) {
                    if (ssmRes == null) {
                        break; 
                    } 
                    resultCode = (String)ssmRes.get(distCoaAttrNm); 
                }

                break;
            }
        }
        return resultCode;
    }

    /**
     * @param glblCmpyCd
     * @param sysSrcCd
     * @param trxCd
     * @param trxRsnCd
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Map> getAjePtrn2(String glblCmpyCd, String sysSrcCd, String trxCd, String trxRsnCd, String drCrTpCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        // pattern indicator to be refined dynamically
        /*
         * queryParam.put("ajePtrnIndTpCd_01",
         * AJE_PTRN_IND_TP_CD_INVOICE_INVENTORY);
         * queryParam.put("ajePtrnActlCd_01",
         * AJE_PTRN_ACTRL_CD_INVOICE);
         */
        queryParam.put("ajeLinkFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("drCrTpCd", drCrTpCd);
        queryParam.put("jrnlSrcNmLen", JRNL_SRC_NM_LEN);
        queryParam.put("ajeIntfcTpCd", AJE_INTFC_TP.OM);

        Set<String> ajeIdList = new HashSet<String>();
        String ajeId = generateAjeId(sysSrcCd, trxCd, trxRsnCd);
        ajeIdList.add(ajeId);
        ajeId = generateAjeId(SYS_SRC_CD_DEFAULT, trxCd, trxRsnCd);
        ajeIdList.add(ajeId);
        queryParam.put("ajeIdList", ajeIdList);

        List<Map> ssmRes = (List<Map>) ssmBatchClient.queryObjectList("getAjePtrn2", queryParam);

        return ssmRes;
    }

    /**
     * <pre>
     *  Get Retail WH Account
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @return List<RtlWhAcct> List of Retail WH Account
     */
    @SuppressWarnings("unchecked")
    public List<RtlWhAcct> getRtlWhAcct(String glblCmpyCd) {

        // ** Get Target AJE Patterns. **
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);

        List<RtlWhAcct> ssmRes = (List<RtlWhAcct>) ssmBatchClient.queryObjectList("getRtlWhAcct", queryParam);

        return ssmRes;
    }

    /**
     * getJrnlEntryRefTxt
     * @param tmsg
     * @return String
     */
    public String getJrnlEntryRefTxt(JRNL_ENTRYTMsg tmsg) {
        String cnctStr = "";

        List<String> cnctStrList = new ArrayList<String>();

        cnctStrList.add(tmsg.ajeItemCd.getValue());
        cnctStrList.add(tmsg.tocCd.getValue());
        cnctStrList.add(tmsg.billToCustCd.getValue());
        cnctStrList.add(tmsg.sellToCustCd.getValue());
        cnctStrList.add(tmsg.shipToCustCd.getValue());
        cnctStrList.add(tmsg.vndCd.getValue());
        cnctStrList.add(tmsg.ajeInvNum.getValue());
        cnctStrList.add(tmsg.rcptChkNum.getValue());
        cnctStrList.add(tmsg.cpoOrdNum.getValue());
        cnctStrList.add(tmsg.soNum.getValue());
        cnctStrList.add(tmsg.rcptNum.getValue());
        cnctStrList.add(tmsg.dsAcctNum.getValue());
        cnctStrList.add(tmsg.arTrxNum.getValue());
        cnctStrList.add(tmsg.serNum.getValue());
        cnctStrList.add(nullVal(tmsg.invtyTrxPk.getValue(), "").toString());
        cnctStrList.add(nullVal(tmsg.svcMachMstrPk.getValue(), "").toString());
        cnctStrList.add(nullVal(tmsg.svcConfigMstrPk.getValue(), "").toString());
        cnctStrList.add(tmsg.jrnlEntryFirstRefTxt.getValue());
        cnctStrList.add(tmsg.jrnlEntryScdRefTxt.getValue());
        cnctStrList.add(tmsg.jrnlEntryThirdRefTxt.getValue());
        cnctStrList.add(tmsg.jrnlEntryFrthRefTxt.getValue());
        cnctStrList.add(tmsg.jrnlEntryFifthRefTxt.getValue());
        
        //---- start 2016/06/03 add new fields
        cnctStrList.add(nullVal(tmsg.manJrnlEntryHdrPk.getValue(),"").toString());
        cnctStrList.add(nullVal(tmsg.prntDsAssetMstrPk.getValue(),"").toString());
        cnctStrList.add(tmsg.poOrdNum.getValue());
        cnctStrList.add(tmsg.ajeId.getValue());
        cnctStrList.add(tmsg.trxRsnNm.getValue());
        cnctStrList.add(tmsg.sysSrcCd.getValue());
        cnctStrList.add(tmsg.trxCd.getValue());
        cnctStrList.add(tmsg.trxRsnCd.getValue());
        cnctStrList.add(tmsg.jrnlSrcNm.getValue());
        cnctStrList.add(tmsg.jrnlCatgNm.getValue());
        
        String drCoaStr = tmsg.drCoaCmpyCd.getValue().concat(COA_DIV).concat(tmsg.drCoaBrCd.getValue()).concat(COA_DIV).concat(tmsg.drCoaCcCd.getValue())
                          .concat(COA_DIV).concat(tmsg.drCoaAcctCd.getValue()).concat(COA_DIV).concat(tmsg.drCoaProdCd.getValue()).concat(COA_DIV)
                          .concat(tmsg.drCoaChCd.getValue()).concat(COA_DIV).concat(tmsg.drCoaAfflCd.getValue()).concat(COA_DIV).concat(tmsg.drCoaProjCd.getValue())
                          .concat(COA_DIV).concat(tmsg.drCoaExtnCd.getValue());

        cnctStrList.add(drCoaStr);
        
        String crCoaStr = tmsg.crCoaCmpyCd.getValue().concat(COA_DIV).concat(tmsg.crCoaBrCd.getValue()).concat(COA_DIV).concat(tmsg.crCoaCcCd.getValue())
                        .concat(COA_DIV).concat(tmsg.crCoaAcctCd.getValue()).concat(COA_DIV).concat(tmsg.crCoaProdCd.getValue()).concat(COA_DIV)
                        .concat(tmsg.crCoaChCd.getValue()).concat(COA_DIV).concat(tmsg.crCoaAfflCd.getValue()).concat(COA_DIV).concat(tmsg.crCoaProjCd.getValue())
                        .concat(COA_DIV).concat(tmsg.crCoaExtnCd.getValue());
        
        cnctStrList.add(crCoaStr);
        
        //---- end 2016/06/03

        for (String rec : cnctStrList) {

            cnctStr = cnctStr + nullVal(rec, "") + "^";

        }

        // length check
        if (cnctStr.length() > REF_TXT_LEN) {
            // cut the exceeded string
            cnctStr = cnctStr.substring(0, REF_TXT_LEN);
        }
        
        return cnctStr;
    }

    private String nullVal(String val, String rpls) {

        if (hasValue(val)) {
            return val;
        }

        return rpls;
    }

    private BigDecimal nullVal(BigDecimal val, BigDecimal rpls) {

        if (hasValue(val)) {
            return val;
        }

        return rpls;
    }

    private String nullVal(BigDecimal val, String rpls) {

        if (hasValue(val)) {
            return val.toString();
        }

        return rpls;
    }

    //---- start add 2016/01/26
    private class DefCoaValues {
        String defCoaCmpy;

        String defCoaBr;

        String defCoaCc;

        String defCoaAcct;

        String defCoaProd;

        String defCoaCh;

        String defCoaAffl;

        String defCoaProj;

        String defCoaExtn;

        public String getDefCoaCmpy() {
            return defCoaCmpy;
        }

        public void setDefCoaCmpy(String defCoaCmpy) {
            this.defCoaCmpy = defCoaCmpy;
        }

        public String getDefCoaBr() {
            return defCoaBr;
        }

        public void setDefCoaBr(String defCoaBr) {
            this.defCoaBr = defCoaBr;
        }

        public String getDefCoaCc() {
            return defCoaCc;
        }

        public void setDefCoaCc(String defCoaCc) {
            this.defCoaCc = defCoaCc;
        }

        public String getDefCoaAcct() {
            return defCoaAcct;
        }

        public void setDefCoaAcct(String defCoaAcct) {
            this.defCoaAcct = defCoaAcct;
        }

        public String getDefCoaProd() {
            return defCoaProd;
        }

        public void setDefCoaProd(String defCoaProd) {
            this.defCoaProd = defCoaProd;
        }

        public String getDefCoaCh() {
            return defCoaCh;
        }

        public void setDefCoaCh(String defCoaCh) {
            this.defCoaCh = defCoaCh;
        }

        public String getDefCoaAffl() {
            return defCoaAffl;
        }

        public void setDefCoaAffl(String defCoaAffl) {
            this.defCoaAffl = defCoaAffl;
        }

        public String getDefCoaProj() {
            return defCoaProj;
        }

        public void setDefCoaProj(String defCoaProj) {
            this.defCoaProj = defCoaProj;
        }

        public String getDefCoaExtn() {
            return defCoaExtn;
        }

        public void setDefCoaExtn(String defCoaExtn) {
            this.defCoaExtn = defCoaExtn;
        }

    }

    private String getOutOfTrtryBr (String glblCmpyCd) {
        return ZYPCodeDataUtil.getVarCharConstValue(OUT_OF_TERRITORY_BR, glblCmpyCd);
    }
    
    private void getDefCoaValues(String glblCmpyCd) {

        String defVals = ZYPCodeDataUtil.getVarCharConstValue(AJE_COA_DEF_VALUES, glblCmpyCd);

        StringTokenizer st = new StringTokenizer(defVals, ",");

        defCoa = new DefCoaValues();

        int cnt = 0;
        String val;
        while (st.hasMoreTokens()) {
            val = st.nextToken();
            cnt++;
            switch (cnt) {
                case 1:
                    defCoa.setDefCoaCmpy(val);
                    break;
                case 2:
                    defCoa.setDefCoaBr(val);
                    break;
                case 3:
                    defCoa.setDefCoaCc(val);
                    break;
                case 4:
                    defCoa.setDefCoaAcct(val);
                    break;
                case 5:
                    defCoa.setDefCoaProd(val);
                    break;
                case 6:
                    defCoa.setDefCoaCh(val);
                    break;
                case 7:
                    defCoa.setDefCoaAffl(val);
                    break;
                case 8:
                    defCoa.setDefCoaProj(val);
                    break;
                case 9:
                    defCoa.setDefCoaExtn(val);
                    break;
                default:
                    break;
            }
        }

    }
    /**
     * <pre>
     *  Get Merchandise Type Account
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @return List<MdseTypeAccount> List of MDSE Type Account
     */
    @SuppressWarnings("unchecked")
    public Map<String, List<MdseTpAcct>> getMdseTpAcct(String glblCmpyCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);

        List<MdseTpAcct> ssmRes = (List<MdseTpAcct>) ssmBatchClient.queryObjectList("getMdseTpAcct", queryParam);

        Map<String, List<MdseTpAcct>> mtAcctMap = new HashMap<String, List<MdseTpAcct>> (); 
        
        // create map per merchandise type
        for (MdseTpAcct rec: ssmRes) {
        
            List<MdseTpAcct> list = mtAcctMap.get(rec.getCoaProjCd());
            
            if (list == null) {
                list = new ArrayList<MdseTpAcct> ();
                mtAcctMap.put(rec.getCoaProjCd(), list);
            }
            list.add(rec);
            
        }
        
        return mtAcctMap;
        
    }
  //---- end add 2016/01/26
    
    //---- start added 2016/02/19 for deferred
    /**
     * @param glblCmpyCd
     * @param sysSrcCd
     * @param trxCd
     * @param trxRsnCd
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Map> getAjePtrnForDfrd(List<Map> trxList, String glblCmpyCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        // pattern indicator to be refined dynamically
        /*
         * queryParam.put("ajePtrnIndTpCd_01",
         * AJE_PTRN_IND_TP_CD_INVOICE_INVENTORY);
         * queryParam.put("ajePtrnActlCd_01",
         * AJE_PTRN_ACTRL_CD_INVOICE);
         */
        queryParam.put("ajeLinkFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("drCrTpCd", "C");  // credit side only
        queryParam.put("jrnlSrcNmLen", JRNL_SRC_NM_LEN);
        queryParam.put("ajeIntfcTpCd", AJE_INTFC_TP.OM);
        queryParam.put("ajeLineIndTpDfrd", AJE_LINE_IND_TP.DFRD_REV_99);  // deferred revenue only
        
        
        // Add AJE Id List from AJE Interface Table.
        Set<String> ajeIdList = new HashSet<String>();
        for (Map<String, String> rsGetIntfcTrxPtrn : trxList) {
            String ajeId = generateAjeId(rsGetIntfcTrxPtrn.get(SYS_SRC_CD), rsGetIntfcTrxPtrn.get(TRX_CD), rsGetIntfcTrxPtrn.get(TRX_RSN_CD));

            ajeIdList.add(ajeId);

            // add aje_id with sys_src_cd = 'ZZZ', too
            ajeId = generateAjeId(SYS_SRC_CD_DEFAULT, rsGetIntfcTrxPtrn.get(TRX_CD), rsGetIntfcTrxPtrn.get(TRX_RSN_CD));

            ajeIdList.add(ajeId);
        }
        queryParam.put("ajeIdList", ajeIdList);

        List<Map> ssmRes = (List<Map>) ssmBatchClient.queryObjectList("getAjePtrn2", queryParam);
        return ssmRes;
    }
    //---- end 2016/02/19
    // START 2019/05/21 E.Kameishi [QC#50407,DEL]
    // START 2016/11/29 S.Fujita [QC#16075,ADD]
    /**
     * Send eMail
     * @param glblCmpyCd
     */
//    private void eMailSend(String glblCmpyCd) {
//        //=========================================
//        // Mail Template
//        // =========================================
//        S21MailTemplate s21MailTemplate = new S21MailTemplate(glblCmpyCd, MAIL_NFAB0020M001);
//        if (!ZYPCommonFunc.hasValue(s21MailTemplate.getSubject()) || !ZYPCommonFunc.hasValue(s21MailTemplate.getBody())) {
//            throw new S21AbendException(NFAM0183E, new String[] {MAIL_NFAB0020M001});
//        }
//        //=========================================
//        // Mail From Address
//        //=========================================
//        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GRP_ID_FROM);
//        groupFrom.setMailKey1(MAIL_KEY_1_FROM);
//        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
//        if (addrFromList.size() <= 0) {
//            throw new S21AbendException(NFAM0182E, new String[] {MAIL_TYPE_FROM, MAIL_GRP_ID_FROM, MAIL_KEY_1_FROM});
//        }
//        //=========================================
//        // Mail To Address
//        //=========================================
//        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GRP_ID_TO);
//        List<S21MailAddress> addrToList = groupTo.getMailAddress();
//        if (addrFromList.size() <= 0) {
//            throw new S21AbendException(NFAM0182E, new String[] {MAIL_TYPE_TO, MAIL_GRP_ID_TO, null});
//        }
//        //=========================================
//        //  Send mail
//        //=========================================
//        S21Mail mail = new S21Mail(glblCmpyCd);
//        mail.setMailTemplate(s21MailTemplate);
//        mail.setFromAddress(addrFromList.get(0));
//        mail.setToAddressList(addrToList);
//        String mailEvent = mail.postMail();
//        if (!ZYPCommonFunc.hasValue(mailEvent)) {
//            return;
//        }
//    }
    // END   2016/11/29 S.Fujita [QC#16075,ADD]
    // END 2019/05/21 E.Kameishi [QC#50407,DEL]
    // SART 2018/03/12 E.Kameishi [QC#23689,ADD]
    /**
     * <pre>
     *  Get Invoice
     * </pre>
     * @param String glblCmpyCd
     * @param String invNum
     * @param String procDt
     * @return PreparedStatement ps
     */
    public PreparedStatement getInvoice(String glblCmpyCd ,String invNum, String procDt, PreparedStatement ps, String divideNum) throws JrnlCommonException {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("invoice", INV_INVTY_IND_TP.INVOICE);
        queryParam.put("flgY", ZYPConstant.FLG_ON_Y);
        queryParam.put("flgN", ZYPConstant.FLG_OFF_N);
        queryParam.put("nfc", SYS_SRC.S21_ACCOUNTING_AR);
        queryParam.put("procYrMth", ZYPDateUtil.DateFormatter(procDt, "yyyyMMdd", "yyyyMM") + "%");
        queryParam.put("dfrd", DFRD_ACCTG_RULE.DEFERRED);
        queryParam.put("immediate", DFRD_ACCTG_RULE.IMMEDIATE);
        queryParam.put("dspt", SVC_INV_SRC_TP.DISPATCH);
        queryParam.put("csmp", CM_DEF_ACCT.CSMP_COA);
        queryParam.put("lineBizTp_PPS", LINE_BIZ_TP.PPS);
        queryParam.put("lineBizTp_LFS", LINE_BIZ_TP.LFS);
        queryParam.put("dev", INV_LINE_SPL_TP.DEVIATION);

        String newcore = ZYPCodeDataUtil.getVarCharConstValue("AJE_NEW_CORE_CD", glblCmpyCd);
        String nonNewCoreBr = ZYPCodeDataUtil.getVarCharConstValue("AJE_DEF_COA_BR_NONCORE", glblCmpyCd);
        String nonNewCoreCc = ZYPCodeDataUtil.getVarCharConstValue("AJE_DEF_COA_CC_NONCORE", glblCmpyCd);
        String stAcctCd = ZYPCodeDataUtil.getVarCharConstValue("AJE_COA_DEF_TAX_ACCT", glblCmpyCd);
        BigDecimal multiCalcCnt = ZYPCodeDataUtil.getNumConstValue("MULTI_INV_CALC_CNT", glblCmpyCd);
        // START 2019/01/07 E.Kameishi [QC#29734,ADD]
        String varRental = ZYPCodeDataUtil.getVarCharConstValue("AJE_RENTAL_SVC_ALLOC_TP_CD", glblCmpyCd);
        List<String> varRentalCdList = new ArrayList<String>();

        if (varRental != null) {
            String[] varRentalList = varRental.split(",");
            varRentalCdList = Arrays.asList(varRentalList);
        }
        // END 2019/01/07 E.Kameishi [QC#29734,ADD]
        
        queryParam.put("newcore", newcore);
        queryParam.put("defBrNewcore", nonNewCoreBr);
        queryParam.put("defCcNewcore", nonNewCoreCc);
        queryParam.put("bsd", DS_CONTR_CLS.BSD);
        queryParam.put("fm", AJE_FM_IND_TP.FM);
        queryParam.put("nonFm", AJE_FM_IND_TP.NON_FM);
        queryParam.put("stAcctCd", stAcctCd);
        queryParam.put("dsContrCatgFLEET", DS_CONTR_CATG.FLEET);
        queryParam.put("svcAllocTpOPTIMA", SVC_ALLOC_TP.OPTIMA_SUPPLY_INCLUSIVE);
        queryParam.put("divideNum", divideNum);
        queryParam.put("multiCalcCnt", multiCalcCnt);
        // START 2018/08/28 E.Kameishi [QC#27945, ADD]
        queryParam.put("configInd", AJE_GENL_IND_TP.CONFIG_INDICATOR);
        queryParam.put("none", AJE_GENL_IND_TP.NONE);
        // END 2018/08/28 E.Kameishi [QC#27945, ADD]
        // START 2019/01/07 E.Kameishi [QC#29734,ADD]
        queryParam.put("varRental", varRentalCdList);
        queryParam.put("re", SVC_ALLOC_IND_TP.RENTAL);
        queryParam.put("nonRe", SVC_ALLOC_IND_TP.NON_RENTAL);
        // END 2019/01/07 E.Kameishi [QC#29734,ADD]
        // START 2019/10/01 T.Murai [QC#53639 ADD]
        queryParam.put("coaMdseTpAA", COA_PROJ.FREIGHT_RECOVERY);
        // END 2019/10/01 T.Murai [QC#53639 ADD]

        try {
            return ssmLLClient.createPreparedStatement("getInvoice", queryParam);
        } catch (SQLException ex) {
            throw new JrnlCommonException(NFAM0032E);
        }
    }
    // END  2018/03/12 E.Kameishi [QC#23689,ADD]
    // START 2018/04/12 E.Kameishi [QC#23378,ADD]
    /**
     * <pre>
     *  getSegInfoForSvc
     * </pre>
     * @param String glblCmpyCd
     * @param BigDecimal dsContrPk
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getSegInfoForSvc(String glblCmpyCd , BigDecimal dsContrPk) {
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String, Object>> segInfoList = new ArrayList<Map<String, Object>>();
        
        try {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            String newcore = ZYPCodeDataUtil.getVarCharConstValue("AJE_NEW_CORE_CD", glblCmpyCd);
            String nonNewCoreBr = ZYPCodeDataUtil.getVarCharConstValue("AJE_DEF_COA_BR_NONCORE", glblCmpyCd);
            String nonNewCoreCc = ZYPCodeDataUtil.getVarCharConstValue("AJE_DEF_COA_CC_NONCORE", glblCmpyCd);
            // START 2019/01/07 E.Kameishi [QC#29734,ADD]
            String varRental = ZYPCodeDataUtil.getVarCharConstValue("AJE_RENTAL_SVC_ALLOC_TP_CD", glblCmpyCd);
            List<String> varRentalCdList = new ArrayList<String>();

            if (varRental != null) {
                String[] varRentalList = varRental.split(",");
                varRentalCdList = Arrays.asList(varRentalList);
            }
            // END 2019/01/07 E.Kameishi [QC#29734,ADD]

            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("dsContrPk", dsContrPk);
            queryParam.put("newcore", newcore);
            queryParam.put("defBrNewcore", nonNewCoreBr);
            queryParam.put("defCcNewcore", nonNewCoreCc);
            queryParam.put("dsContrCatgFLEET", DS_CONTR_CATG.FLEET);
            queryParam.put("svcAllocTpOPTIMA", SVC_ALLOC_TP.OPTIMA_SUPPLY_INCLUSIVE);
            queryParam.put("invoice", INV_INVTY_IND_TP.INVOICE);
            queryParam.put("immediate", DFRD_ACCTG_RULE.IMMEDIATE);
            // START 2019/01/07 E.Kameishi [QC#29734,ADD]
            queryParam.put("varRental", varRentalCdList);
            queryParam.put("re", SVC_ALLOC_IND_TP.RENTAL);
            queryParam.put("nonRe", SVC_ALLOC_IND_TP.NON_RENTAL);
            // END 2019/01/07 E.Kameishi [QC#29734,ADD]
            ps = (PreparedStatement) ssmLLClient.createPreparedStatement("getSegInfoForSvc", queryParam);
            rs = ps.executeQuery();

            while (rs.next()) {
                // When TRX_RSN_CD is S1
                Map<String, Object> segInfoMapS1 = new HashMap<String, Object>();
                segInfoMapS1.put(SEG_DS_CONTR_PK, rs.getBigDecimal(SEG_DS_CONTR_PK));
                segInfoMapS1.put(SEG_DS_CONTR_DTL_PK, rs.getBigDecimal(SEG_DS_CONTR_DTL_PK));
                segInfoMapS1.put(TRX_RSN_CD, TRX_RSN.SVC_SVC_REV_BASE);
                // indicator
                String ajeIdS1 = generateAjeId(SYS_SRC.S21_SERVICE, TRX.SALES, TRX_RSN.SVC_SVC_REV_BASE);

                // Get AJE Pattern List for Debit
                List<Map> resAjePtrnListS1 = (List<Map>) getAjePtrn2(glblCmpyCd, SYS_SRC.S21_SERVICE, TRX.SALES, TRX_RSN.SVC_SVC_REV_BASE, SEG_CR_CD);

                // refine AJE Pattern by pattern indicator codes
                List<NFAC000101PMsg> ajePtrnListByAjeIdAndIndTpS1 = getAJEPtrnByAjeIdAndIndTp(ajeIdS1, rs, resAjePtrnListS1);

                // if pattern could not be obtained go to next
                // record. (No need to create one more error
                // record.)
                for (NFAC000101PMsg resAjePtrn : ajePtrnListByAjeIdAndIndTpS1) {
                    get9SegmentsValues(glblCmpyCd, rs, resAjePtrn, SEG_CR_CD, segInfoMapS1);
                }
                segInfoList.add(segInfoMapS1);

                // When TRX_RSN_CD is S2
                Map<String, Object> segInfoMapS2 = new HashMap<String, Object>();
                segInfoMapS2.put(SEG_DS_CONTR_PK, rs.getBigDecimal(SEG_DS_CONTR_PK));
                segInfoMapS2.put(SEG_DS_CONTR_DTL_PK, rs.getBigDecimal(SEG_DS_CONTR_DTL_PK));
                segInfoMapS2.put(TRX_RSN_CD, TRX_RSN.SVC_SUP_REV_BASE);
                // indicator
                String ajeIdS2 = generateAjeId(SYS_SRC.S21_SERVICE, TRX.SALES, TRX_RSN.SVC_SUP_REV_BASE);

                // Get AJE Pattern List for Debit
                List<Map> resAjePtrnListS2 = (List<Map>) getAjePtrn2(glblCmpyCd, SYS_SRC.S21_SERVICE, TRX.SALES, TRX_RSN.SVC_SUP_REV_BASE, SEG_CR_CD);

                // refine AJE Pattern by pattern indicator codes
                List<NFAC000101PMsg> ajePtrnListByAjeIdAndIndTpS2 = getAJEPtrnByAjeIdAndIndTp(ajeIdS2, rs, resAjePtrnListS2);

                // if pattern could not be obtained go to next
                // record. (No need to create one more error
                // record.)
                for (NFAC000101PMsg resAjePtrn : ajePtrnListByAjeIdAndIndTpS2) {
                    get9SegmentsValues(glblCmpyCd, rs, resAjePtrn, SEG_CR_CD, segInfoMapS2);
                }
                segInfoList.add(segInfoMapS2);

                // When TRX_RSN_CD is S3
                Map<String, Object> segInfoMapS3 = new HashMap<String, Object>();
                segInfoMapS3.put(SEG_DS_CONTR_PK, rs.getBigDecimal(SEG_DS_CONTR_PK));
                segInfoMapS3.put(SEG_DS_CONTR_DTL_PK, rs.getBigDecimal(SEG_DS_CONTR_DTL_PK));
                segInfoMapS3.put(TRX_RSN_CD, TRX_RSN.SVC_EQUIP_REV_BASE);
                // indicator
                String ajeIdS3 = generateAjeId(SYS_SRC.S21_SERVICE, TRX.SALES, TRX_RSN.SVC_EQUIP_REV_BASE);

                // Get AJE Pattern List for Debit
                List<Map> resAjePtrnListS3 = (List<Map>) getAjePtrn2(glblCmpyCd, SYS_SRC.S21_SERVICE, TRX.SALES, TRX_RSN.SVC_EQUIP_REV_BASE, SEG_CR_CD);

                // refine AJE Pattern by pattern indicator codes
                List<NFAC000101PMsg> ajePtrnListByAjeIdAndIndTpS3 = getAJEPtrnByAjeIdAndIndTp(ajeIdS3, rs, resAjePtrnListS3);

                // if pattern could not be obtained go to next
                // record. (No need to create one more error
                // record.)
                for (NFAC000101PMsg resAjePtrn : ajePtrnListByAjeIdAndIndTpS3) {
                    get9SegmentsValues(glblCmpyCd, rs, resAjePtrn, SEG_CR_CD, segInfoMapS3);
                }
                segInfoList.add(segInfoMapS3);
            }
        } catch (SQLException ex) {
                
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        return segInfoList;
    }
    
    /**
     * Set 9 Segments Values
     * @param param
     * @param jrnlEntry
     * @param rs
     * @param resAjePtrn
     * @param drCrTpCd
     * @return boolean
     * @throws SQLException
     */
    private void get9SegmentsValues(String glblCmpyCd, ResultSet rs, NFAC000101PMsg resAjePtrn, String drCrTpCd, Map<String, Object> segInfoMap )
            throws SQLException {

        if (!AJE_LINE_IND_TP.REV_18.equals(resAjePtrn.ajeLineIndTpCd.getValue())) {
            return;
        }
        
        String resultCd;
        String coaCmpyCd;
        String coaBrCd;
        String coaCcCd;
        String coaAcctCd;
        String coaProdCd;
        String coaChCd;
        String coaAfflCd;
        String coaProjCd;
        String coaExtnCd;

        if (SEG_DR_CD.equals(drCrTpCd)) {
            coaCmpyCd = resAjePtrn.drAjeCoaCmpyCd.getValue();
            coaBrCd = resAjePtrn.drAjeCoaBrCd.getValue();
            coaCcCd = resAjePtrn.drAjeCoaCcCd.getValue();
            coaAcctCd = resAjePtrn.drAjeCoaAcctCd.getValue();
            coaProdCd = resAjePtrn.drAjeCoaProdCd.getValue();
            coaChCd = resAjePtrn.drAjeCoaChCd.getValue();
            coaAfflCd = resAjePtrn.drAjeCoaAfflCd.getValue();
            coaProjCd = resAjePtrn.drAjeCoaProjCd.getValue();
            coaExtnCd = resAjePtrn.drAjeCoaExtnCd.getValue();
        } else {
            coaCmpyCd = resAjePtrn.crAjeCoaCmpyCd.getValue();
            coaBrCd = resAjePtrn.crAjeCoaBrCd.getValue();
            coaCcCd = resAjePtrn.crAjeCoaCcCd.getValue();
            coaAcctCd = resAjePtrn.crAjeCoaAcctCd.getValue();
            coaProdCd = resAjePtrn.crAjeCoaProdCd.getValue();
            coaChCd = resAjePtrn.crAjeCoaChCd.getValue();
            coaAfflCd = resAjePtrn.crAjeCoaAfflCd.getValue();
            coaProjCd = resAjePtrn.crAjeCoaProjCd.getValue();
            coaExtnCd = resAjePtrn.crAjeCoaExtnCd.getValue();
        }

        boolean defaultSetFlg = false;
        JRNL_ENTRYTMsg tMsg = new JRNL_ENTRYTMsg();
        String crOrDr = "cr";
        if (SEG_DR_CD.equals(drCrTpCd)) {
            crOrDr = "dr";
        }
        for (int i = 0; i < 9; i++) {
            switch (i) {
                case 0:// Company COA_CMPY
                    // {
                    if (coaCmpyCd.substring(0, 1).equals("@") || coaCmpyCd.substring(0, 1).equals("#")) {
                        if (setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.CMPY, crOrDr, resAjePtrn, rs, null)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaCmpyCd");

                            segInfoMap.put(SEG_AJE_INTFC_CMNT_TXT, tMsg.ajeIntfcCmntTxt.getValue());
                            
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaCmpyCd;
                    }
                    segInfoMap.put(SEG_COA_CMPY_CD, resultCd);

                    break;

                case 1:// Branch COA_BR
                    if (coaBrCd.substring(0, 1).equals("@") || coaBrCd.substring(0, 1).equals("#")) {
                        if (setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.BR, crOrDr, resAjePtrn, rs, null)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaBrCd");

                            segInfoMap.put(SEG_AJE_INTFC_CMNT_TXT, tMsg.ajeIntfcCmntTxt.getValue());
                            
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaBrCd;
                    }

                    segInfoMap.put(SEG_COA_BR_CD, resultCd);

                    break;

                case 2:// Cost Center COA_CC
                    if (coaCcCd.substring(0, 1).equals("@") || coaCcCd.substring(0, 1).equals("#")) {
                        if (setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.CC, crOrDr, resAjePtrn, rs, null)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaCcCd");

                            segInfoMap.put(SEG_AJE_INTFC_CMNT_TXT, tMsg.ajeIntfcCmntTxt.getValue());
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaCcCd;
                    }

                    segInfoMap.put(SEG_COA_CC_CD, resultCd);
                    break;

                case 3:// Account COA_ACCT
                    if (coaAcctCd.substring(0, 1).equals("@") || coaAcctCd.substring(0, 1).equals("#")) {
                        if (setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.ACCT, crOrDr, resAjePtrn, rs, null)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaAcctCd");

                            segInfoMap.put(SEG_AJE_INTFC_CMNT_TXT, tMsg.ajeIntfcCmntTxt.getValue());
                            
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaAcctCd;
                    }

                    segInfoMap.put(SEG_COA_ACCT_CD, resultCd);
                    if (SEG_COA_ACCT_CD_ITEM_REV.equals(coaAcctCd)) {
                        defaultSetFlg = checkBsPlTp(glblCmpyCd, resultCd);
                    }
                    break;

                case 4:// Product COA_PROD
                    if (coaProdCd.substring(0, 1).equals("@") || coaProdCd.substring(0, 1).equals("#")) {
                        if (setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.PROD, crOrDr, resAjePtrn, rs, null)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaProdCd");

                            segInfoMap.put(SEG_AJE_INTFC_CMNT_TXT, tMsg.ajeIntfcCmntTxt.getValue());
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaProdCd;
                    }

                    segInfoMap.put(SEG_COA_PROD_CD, resultCd);
                    break;

                case 5:// Channel COA_CH
                    if (coaChCd.substring(0, 1).equals("@") || coaChCd.substring(0, 1).equals("#")) {
                        if (setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.CH, crOrDr, resAjePtrn, rs, null)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaChCd");

                            segInfoMap.put(SEG_AJE_INTFC_CMNT_TXT, tMsg.ajeIntfcCmntTxt.getValue());
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaChCd;
                    }

                    segInfoMap.put(SEG_COA_CH_CD, resultCd);
                    break;

                case 6:// Affiliate COA_AFFL
                    if (coaAfflCd.substring(0, 1).equals("@") || coaAfflCd.substring(0, 1).equals("#")) {
                        if (setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.AFFL, crOrDr, resAjePtrn, rs, null)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaAfflCd");

                            segInfoMap.put(SEG_AJE_INTFC_CMNT_TXT, tMsg.ajeIntfcCmntTxt.getValue());
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaAfflCd;
                    }

                    segInfoMap.put(SEG_COA_AFFL_CD, resultCd);
                    break;

                case 7:// Merchandise Type COA_PROJ
                    if (coaProjCd.substring(0, 1).equals("@") || coaProjCd.substring(0, 1).equals("#")) {
                        if (setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.PROJ, crOrDr, resAjePtrn, rs, null)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaProjCd");

                            segInfoMap.put(SEG_AJE_INTFC_CMNT_TXT, tMsg.ajeIntfcCmntTxt.getValue());
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaProjCd;
                    }

                    segInfoMap.put(SEG_COA_PROJ_CD, resultCd);
                    break;

                case 8:// Business Unit COA_EXTN
                    if (coaExtnCd.substring(0, 1).equals("@") || coaExtnCd.substring(0, 1).equals("#")) {
                        if (setValueToJrnlEntryByAjePtrn(tMsg, CoaSegment.EXTN, crOrDr, resAjePtrn, rs, null)) {
                            resultCd = (String) tMsg.getDBValue(crOrDr + "CoaExtnCd");

                            segInfoMap.put(SEG_AJE_INTFC_CMNT_TXT, tMsg.ajeIntfcCmntTxt.getValue());
                        } else {
                            resultCd = null;
                        }
                    } else {
                        resultCd = coaExtnCd;
                    }

                    segInfoMap.put(SEG_COA_EXTN_CD, resultCd);
                    break;

                default:
                    break;
            }
            if (defaultSetFlg) {
                break;
            }
        }

        String ajeCoaDefValuesCsv = ZYPCodeDataUtil.getVarCharConstValue("AJE_COA_DEF_VALUES", glblCmpyCd);
        String[] ajeCoaDefValues = ajeCoaDefValuesCsv.split(",");
        
        if (defaultSetFlg) {
            segInfoMap.put(SEG_COA_BR_CD, ajeCoaDefValues[1]);
            segInfoMap.put(SEG_COA_CC_CD, ajeCoaDefValues[2]);
            segInfoMap.put(SEG_COA_ACCT_CD, ajeCoaDefValues[3]);
            segInfoMap.put(SEG_COA_PROD_CD, ajeCoaDefValues[4]);
            segInfoMap.put(SEG_COA_CH_CD, ajeCoaDefValues[5]);
            segInfoMap.put(SEG_COA_AFFL_CD, ajeCoaDefValues[6]);
            segInfoMap.put(SEG_COA_PROJ_CD, ajeCoaDefValues[7]);
            segInfoMap.put(SEG_COA_EXTN_CD, ajeCoaDefValues[8]);
        }
    }

    private boolean checkBsPlTp(String glblCmpyCd, String coaAcctCd) throws SQLException {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("coaAcctCd", coaAcctCd);

        String bsPlTpCd = (String) ssmBatchClient.queryObject("getBsPlTp", queryParam);

        if (SEG_BS_PL_TP_CD_BS.equals(bsPlTpCd)) {
            return true;
        }
        return false;
    }
    // END  2018/04/12 E.Kameishi [QC#23378,ADD]
}
