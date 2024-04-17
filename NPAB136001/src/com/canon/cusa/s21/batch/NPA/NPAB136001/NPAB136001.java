/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB136001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_COND_CONSTTMsg;
import business.db.INSRC_RULETMsg;
import business.db.INSRC_RULE_DTLTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_ITEM_STSTMsg;
import business.db.NLBI1410_01TMsg;
import business.db.PRCH_REQTMsg;
import business.db.PRCH_REQ_DTLTMsg;
import business.db.RCV_ASN_VNDTMsg;
import business.db.RTL_WHTMsg;
import business.db.S21_PSNTMsg;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;
import business.db.WH_OWNRTMsg;
import business.parts.NLZC300001PMsg;
import business.parts.NPZC103001PMsg;
import business.parts.NPZC103001_prchReqInfoPMsg;
import business.parts.NPZC113001PMsg;
import business.parts.NPZC129001PMsg;
import business.parts.NPZC136001PMsg;
import business.parts.NWZC206001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC300001.NLZC300001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC113001.NPZC113001;
import com.canon.cusa.s21.api.NPZ.NPZC129001.NPZC129001;
import com.canon.cusa.s21.api.NPZ.NPZC136001.NPZC136001;
import com.canon.cusa.s21.api.NWZ.NWZC206001.NWZC206001;
import com.canon.cusa.s21.batch.NPA.NPAB136001.constant.NPAB136001Constant;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CurrencyConversion;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CurrencyConversionBean;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001GetMdseRelationshipData;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CARR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_CLS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ORD_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NPAB1360 Insourcing Batch
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/28/2015   CITS       Yasushi Nomura        Create          N/A
 * 09/25/2016   CITS       K.Ogino               Update          QC#8195
 * 01/06/2017   CITS       S.Endo                Update          QC#16933
 * 08/29/2017   CITS       S.Endo                Update          Sol#406(QC#19243)
 * 09/19/2017   CITS       S.Endo                Update          Sol#406(QC#19243)
 * 10/10/2017   CITS       T.Tokutomi            Update          QC#21209
 * 10/24/2017   CITS       T.Tokutomi            Update          QC#21657-1
 * 10/30/2017   CITS       S.Katsuma             Update          QC#18512
 * 01/12/2018   CITS       K.Ogino               Update          QC#22405
 * 01/31/2018   CITS       T.Tokutomi            Update          QC#21316
 * 02/21/2018   CITS       T.Hakodate            Update          QC#18418
 * 05/09/2018   CITS       T.Tokutomi            Update          QC#2366
 * 07/09/2018   CITS       K.Ogino               Update          QC#24918
 * 08/20/2018   CITS       T.Tokutomi            Update          QC#26662
 * 08/20/2018   CITS       T.Tokutomi            Update          QC#27655
 * 08/23/2018   CITS       T.Tokutomi            Update          QC#27660
 * 11/08/2018   CITS       K.Ogino               Update          QC#29026
 * 02/18/2019   CITS       K.Ogino               Update          QC#30355
 * 07/29/2019   Fujitsu    S.Ohki                Update          QC#52018
 * 08/10/2019   CITS       K.Ogino               Update          QC#52575/QC#52576
 * 01/03/2020   CITS       K.Ogino               Update          QC#55042
 * 01/25/2020   CITS       K.Ogino               Update          QC#55615
 * 02/04/2020   CITS       K.Ogino               Update          QC#55762
 * 02/10/2020   CITS       K.Ogino               Update          QC#55514
 * 10/29/2020   CITS       K.Ogino               Update          QC#57659
 * 12/09/2020   CITS       K.Ogino               Update          QC#57659-2
 * 12/16/2020   CITS       K.Ogino               Update          QC#57659-3
 * 12/24/2020   CITS       K.Ogino               Update          QC#58170
 * 01/05/2021   CITS       M.Furugoori           Update          QC#58176
 * 03/05/2021   CITS       K.Ogino               Update          QC#58504
 * 05/17/2021   CITS       K.Ogino               Update          QC#58274
 * 05/22/2021   CITS       K.Ogino               Update          QC#58785
 * 02/19/2022   CITS       K.Ogino               Update          QC#59718
 * 03/31/2022   CITS       K.Ogino               Update          QC#59888
 * 02/27/2023   Hitachi    E.Watabe              Update          QC#61134
 * 03/14/2023   CITS       R.Kurahashi           Update          QC#61128
 * 05/09/2023   Hitachi    E.Watabe              Update          QC#61084
 * 05/12/2023   Hitachi    E.Watabe              Update          QC#61083
 * 06/02/2023   Hitachi    S.Dong                Update          QC#55629
 * 09/04/2023   CITS       F.Komaki              Update          QC#61703
 * 09/08/2023   CITS       F.Komaki              Update          QC#61704
 * 09/12/2023   Hitachi    G.Quan                Update          QC#61589
 * 09/19/2023   Hitachi    G.Quan                Update          QC#59207
 * 10/03/2023   CITS       K.Ogino               Update          QC#61703
 * 10/16/2023   Hitachi    T.Kuroda              Update          QC#61740
 * 11/28/2023   CITS       K.Ogino               Update          QC#62434
 *</pre>
 */
public class NPAB136001 extends S21BatchMain {
    /** Global Company Code */
    private String globalCompanyCode = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Sales Date */
    private String salesDate = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** commit count */
    private int commitCount = 0;

    /** total commit count */
    private int totalCommitCount = 0;

    /** error count */
    private int errorCount = 0;

    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    /** data cache for Rule Detail */
    private Map<String, ArrayList<INSRC_RULE_DTLTMsg>> ruleDetailMap = null;

    /** data cache for Supersede Item */
    private Map<String, ArrayList<String>> supersedeItemMap = null;

    /** data cache for Vendor Currency Code */
    private Map<String, String> vendorCurrencyCodeMap = null;

    /** data cache for Refurbish Item */
    private Map<String, Map<String, ArrayList<String>>> refurbishItemMap = null;

    /** data cache for New Item */
    private Map<String, Map<String, ArrayList<String>>> newItemMap = null;

    /** PR Update API PMsg for Source WH */
    private NPZC103001PMsg insrcPrForSrcWhPMsg = new NPZC103001PMsg();

    /** PR Update API PMsg for Insourcing Planning */
    private NPZC103001PMsg insrcPrForInsrcPlnPMsg = new NPZC103001PMsg();

    /** PR Update API PMsg for Choice */
    private NPZC103001PMsg insrcPrForChoicePMsg = new NPZC103001PMsg();

    /** PR Update API PMsg for Specified WH */
    private NPZC103001PMsg insrcPrForSpecWhPMsg = new NPZC103001PMsg();

    /** PR Update API PMsg for Vendor */
    private NPZC103001PMsg insrcPrForVndPMsg = new NPZC103001PMsg();

    /** PR Update API PMsg to create PO Requisition */
    private NPZC103001PMsg insrcPrToCratPrPMsg = new NPZC103001PMsg();

    // QC#52575/QC#52576 Start
    /** PR Update API PMsg for Vendor */
    private NPZC103001PMsg insrcAvalInvPrForVndPMsg = new NPZC103001PMsg();

    /** PR Update API PMsg to create PO Requisition */
    private NPZC103001PMsg insrcPrToCratPrAvalInvPMsg = new NPZC103001PMsg();
    // QC#52575/QC#52576 End
    // 08/29/2017 CITS S.Endo Add Sol#406(QC#19243) START
    /** PR Update API PMsg to create BO WH Transfer */
    private NPZC103001PMsg insrcPrToCratBOWhTransferPMsg = new NPZC103001PMsg();

    /** allocationCompleteFlag:if allocate Complete,then true */
    private List<Integer> allocateCompleteFlagList = new ArrayList<Integer>();

    /** current Index for BO check allocation */
    private Integer currentIndexForBO;

    // 08/29/2017 CITS S.Endo Add Sol#406(QC#19243) END

    /** Error Message Bean List */
    private ArrayList<ErrorMessageBean> errMsgBeanList = new ArrayList<ErrorMessageBean>();

    /** Create Material Parts Codes */
    private String[] materialParts = null;

    /** QC#22405 */
    private boolean isWSErr = false;

    // QC#18418 T.Hakodate ADD START
    /** QC#18418 */
    private ArrayList<String> doesNotExistInItemMaster = new ArrayList<String>();
    // QC#18418 T.Hakodate ADD END

    /** QC#2366 Hazmat enable insourcing rule*/
    private List<String> hazmatInsrcRuleList;

    // QC#27660 Add
    /**
     * LENGTH_PRCH_REQ_LINE_CMNT_TXT
     */
    private static final int LENGTH_PRCH_REQ_LINE_CMNT_TXT = 240;

    // QC#27660 Add
    /**
     * boDetailCmnt
     */
    private String boDetailCmnt = new String();

    // QC#57659 Add start
    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Choice Mail Target Map
     */
    private LinkedHashMap<String, PrInfoBean> choicePrInfoMapList = null;

    /**
     * MNX Courier Job Request List
     */
    private LinkedHashMap<String, PrInfoBean> dbsPrInfoMapList = null;

    /**
     * Kick out from mail address
     */
    private String fromAddrForKickOut = null;

    /**
     * Kick out Begin Day of week
     */
    private int kickOutBeginDayOfWeek = 2;

    /**
     * Kick out Standard Check Time
     */
    private String kickOutBeginTime = null;

    /**
     * Kick out Begin Day of week
     */
    private int kickOutEndDayOfWeek = 7;

    /**
     * Kick out Standard Check Time
     */
    private String kickOutEndTime = null;

    /**
     * Kick out standard to mail address
     */
    private List<String> regulerHoursMailAddrList = null;

    /**
     * Kick out non standard to mail address
     */
    private List<String> nonStandardHoursMailAddrList = null;

    /** */
    private String dbsShipFromCtacNm;
    // QC#57659 Add end

    // QC#57659-3 Add
    /** Rush KickOut Mail Type */
    private String rushKickOutMailType = "";

    // QC#58274
    /** Only PR Type */
    private String[] cusaPartsOnlyPrTypes = null;

    /** Supplier Vendor Code */
    private String[] cusaPartsOnlyVndCds = null;

    // QC#61128 Add Start
    private boolean isTplErr = false;
    // QC#61128 Add End

    // 2023/09/04 QC#61703 START
    /** data cache for MDSE */
    private Map<String, BigDecimal> mdseMap = new HashMap<String, BigDecimal>();
    // 2023/09/04 QC#61703 END

    // START 2023/09/19 G.Quan [QC#59207, ADD]
    /**
     * Kick out from mail address for Standard
     */
    private String fromAddrForKickOutStd = null;
    // END 2023/09/19 G.Quan [QC#59207, ADD]

    @Override
    protected void initRoutine() {
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        globalCompanyCode = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(globalCompanyCode)) {
            throw new S21AbendException(NPAB136001Constant.NPAM1479E);
        }

        salesDate = ZYPDateUtil.getSalesDate(globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            throw new S21AbendException(NPAB136001Constant.NPAM1480E);
        }

        String constValue = ZYPCodeDataUtil.getVarCharConstValue(NPAB136001Constant.VAR_CONST_CREATE_MATERIAL_PARTS, getGlobalCompanyCode());
        if (constValue != null) {
            materialParts = constValue.split(",");
        }

        // QC#2366
        String hazmatInsrcRuleStr = ZYPCodeDataUtil.getVarCharConstValue("NPAB1360_HAZMAT_INSRC_RULE", globalCompanyCode);
        if (hazmatInsrcRuleStr != null) {
            hazmatInsrcRuleList = Arrays.asList(hazmatInsrcRuleStr.split(","));
        } else {
            // Error
            // "@" doesn't exist in the VAR_CHAR_CONST.
            throw new S21AbendException("NMAM8432E", new String[] {"NPAB1360_HAZMAT_INSRC_RULE" });
        }

        // QC#57659 Add start
        // Kickout mail address.
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        constValue = ZYPCodeDataUtil.getVarCharConstValue("NPAB1360_FROM_ADDR_KICK_OUT", globalCompanyCode);
        // START 2023/09/19 G.Quan [QC#59207, MOD]
        //if (constValue != null) {
            //fromAddrForKickOut = constValue;
        //}
        if (constValue != null) {
            fromAddrForKickOut = constValue;
        } else {
            fromAddrForKickOut = NPAB136001Constant.NPAB1360_FROM_ADDR_KICK_OUT;
        }
        // END 2023/09/19 G.Quan [QC#59207, MOD]

        constValue = ZYPCodeDataUtil.getVarCharConstValue("NPAB1360_KICK_OUT_STD_MAIL", globalCompanyCode);
        if (constValue != null) {
            regulerHoursMailAddrList = Arrays.asList(constValue.split(","));
        }
        constValue = ZYPCodeDataUtil.getVarCharConstValue("NPAB1360_KICK_OUT_NON_STD_MAIL", globalCompanyCode);
        if (constValue != null) {
            nonStandardHoursMailAddrList = Arrays.asList(constValue.split(","));
        }
        // Get DBS_SHIP_FROM_CTAC_NM,
        this.dbsShipFromCtacNm = ZYPCodeDataUtil.getVarCharConstValue("DBS_SHIP_FROM_CTAC_NM", globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(dbsShipFromCtacNm)) {
            dbsShipFromCtacNm = "";
        }
        constValue = ZYPCodeDataUtil.getVarCharConstValue("NPAB1360_KICKOUT_B_DAY_OF_WEEK", globalCompanyCode);
        if (constValue != null) {
            kickOutBeginDayOfWeek = Integer.parseInt(constValue);
        }
        constValue = ZYPCodeDataUtil.getVarCharConstValue("NPAB1360_KICKOUT_B_TIME", globalCompanyCode);
        if (constValue != null) {
            kickOutBeginTime = constValue;
        }
        constValue = ZYPCodeDataUtil.getVarCharConstValue("NPAB1360_KICKOUT_E_DAY_OF_WEEK", globalCompanyCode);
        if (constValue != null) {
            kickOutEndDayOfWeek = Integer.parseInt(constValue);
        }
        constValue = ZYPCodeDataUtil.getVarCharConstValue("NPAB1360_KICKOUT_E_TIME", globalCompanyCode);
        if (constValue != null) {
            kickOutEndTime = constValue;
        }
        // QC#57659 Add end
        // QC#57659-3
        constValue = ZYPCodeDataUtil.getVarCharConstValue("NPAB1360_RUSH_KICKOUT_ML_TP", globalCompanyCode);
        if (constValue != null) {
            rushKickOutMailType = constValue;
        }

        // QC#58274 Add
        constValue = ZYPCodeDataUtil.getVarCharConstValue("NPAB1360_CUSA_PRT_ONLY_PR_TYPE", globalCompanyCode);
        if (constValue != null) {
            cusaPartsOnlyPrTypes = constValue.split(",");
        } else {
            cusaPartsOnlyPrTypes = new String[]{"1001"};
        }
 
        constValue = ZYPCodeDataUtil.getVarCharConstValue("NPAB1360_CUSA_PRT_ONLY_VND_CD", globalCompanyCode);
        if (constValue != null) {
            cusaPartsOnlyVndCds = constValue.split(",");
        } else {
            cusaPartsOnlyVndCds = new String[]{"0000900335"};
        }
        // QC#58274 End

        // START 2023/09/19 G.Quan [QC#59207, ADD]
        constValue = ZYPCodeDataUtil.getVarCharConstValue("NPAB1360_FROM_ADDR_KICKOUT_STD", globalCompanyCode);
        if (constValue != null) {
            fromAddrForKickOutStd = constValue;
        } else {
            fromAddrForKickOutStd = NPAB136001Constant.NPAB1360_FROM_ADDR_KICK_OUT;
        }
        // END 2023/09/19 G.Quan [QC#59207, ADD]

    }

    @Override
    protected void mainRoutine() {

        // get Insourcing Target PR Header Number List
        List<String> targetPrNumList = getTargetPrNumList();

        for (int i = 0; i < targetPrNumList.size(); i++) {

            // Initialized PMsg Param
            insrcPrForSrcWhPMsg = new NPZC103001PMsg();
            insrcPrForInsrcPlnPMsg = new NPZC103001PMsg();
            insrcPrForChoicePMsg = new NPZC103001PMsg();
            insrcPrForSpecWhPMsg = new NPZC103001PMsg();
            insrcPrForVndPMsg = new NPZC103001PMsg();
            insrcPrToCratPrPMsg = new NPZC103001PMsg();
            // 08/29/2017 CITS S.Endo Add Sol#406(QC#19243) START
            allocateCompleteFlagList = new ArrayList<Integer>();
            insrcPrToCratBOWhTransferPMsg = new NPZC103001PMsg();
            // 08/29/2017 CITS S.Endo Add Sol#406(QC#19243) END
            // QC#52575/QC#52576
            insrcAvalInvPrForVndPMsg = new NPZC103001PMsg();
            insrcPrToCratPrAvalInvPMsg = new NPZC103001PMsg();
            // QC#57659 Add start
            choicePrInfoMapList = new LinkedHashMap<String, PrInfoBean>();
            dbsPrInfoMapList = new LinkedHashMap<String, PrInfoBean>();
            // QC#57659 Add end

            // get PR Detail
            String prNum = targetPrNumList.get(i);
            ArrayList<PrInfoBean> prList = getPrList(prNum);

            // QC#21316 Line Merge. If merge method is error ,
            // prch_req list is empty.
            prList = duplicateItemMerge(prList);

            if (0 < prList.size()) {

                // init
                commitCount = 0;
                ruleDetailMap = new HashMap<String, ArrayList<INSRC_RULE_DTLTMsg>>();
                supersedeItemMap = new HashMap<String, ArrayList<String>>();
                vendorCurrencyCodeMap = new HashMap<String, String>();
                refurbishItemMap = new HashMap<String, Map<String, ArrayList<String>>>();
                newItemMap = new HashMap<String, Map<String, ArrayList<String>>>();

                ArrayList<INSRC_RULETMsg> ruleList = getInsourcingRule();

                for (PrInfoBean row : prList) {
                    // 09/19/2017 CITS S.Endo Mod Sol#406(QC#19243)
                    // START
                    // ArrayList<INSRC_RULETMsg> ruleSubList =
                    // findInsourcingRule(ruleList,
                    // row.getHeaderInfo().prchReqRecTpCd.getValue()
                    // , row.getHeaderInfo().prchReqTpCd.getValue(),
                    // row.getDetailInfo().procrTpCd.getValue());
                    ArrayList<INSRC_RULETMsg> ruleSubList = findInsourcingRule(ruleList, row.getHeaderInfo().prchReqRecTpCd.getValue(), row.getHeaderInfo().prchReqTpCd.getValue(), row.getDetailInfo().procrTpCd.getValue(),
                            row.getMdseInfo().mdseItemClsTpCd.getValue());
                    // 09/19/2017 CITS S.Endo Mod Sol#406(QC#19243)
                    // END
                    if (ruleSubList.size() == 1) {
                        row.setRuleInfo(ruleSubList.get(0));

                    } else {
                        // sort
                        Collections.sort(ruleSubList, new ComparatorRule());
                        INSRC_RULETMsg ruleTemp = null;

                        for (INSRC_RULETMsg rule : ruleSubList) {
                            if ((ZYPCommonFunc.hasValue(rule.prchReqSrcTpCd)) && (!rule.prchReqSrcTpCd.getValue().equals(row.getHeaderInfo().prchReqSrcTpCd.getValue()))) {
                                continue;
                            }

                            if ((ZYPCommonFunc.hasValue(rule.destLocTpCd)) && (!rule.destLocTpCd.getValue().equals(row.getRtlWhInfo().locTpCd.getValue()))) {
                                continue;
                            }

                            if ((ZYPCommonFunc.hasValue(rule.destRtlWhCatgCd)) && (!rule.destRtlWhCatgCd.getValue().equals(row.getRtlWhInfo().rtlWhCatgCd.getValue()))) {
                                continue;
                            }

                            if ((ZYPCommonFunc.hasValue(rule.techLineBizTpCd)) && (!rule.techLineBizTpCd.getValue().equals(row.getS21PsnInfo().lineBizTpCd.getValue()))) {
                                continue;
                            }

                            if ((ZYPCommonFunc.hasValue(rule.destInvtyLocCd)) && (!rule.destInvtyLocCd.getValue().equals(row.getDetailInfo().destInvtyLocCd.getValue()))) {
                                continue;
                            }

                            ruleTemp = rule;
                            break;
                        }

                        if (ruleTemp == null) {
                            ArrayList<INSRC_RULETMsg> ruleTempList = findInsourcingRule(ruleList, row.getHeaderInfo().prchReqRecTpCd.getValue(), row.getDetailInfo().procrTpCd.getValue());

                            if (0 < ruleTempList.size()) {
                                ruleTemp = ruleTempList.get(0);
                            }
                        }
                        row.setRuleInfo(ruleTemp);
                    }
                }

                // sort PR list.
                Collections.sort(prList, new ComparatorPrDetail());

                boolean dtlProc = false;

                // Insourcing Process and create API Param
                for (int j = 0; j < prList.size(); j++) {

                    PrInfoBean row = prList.get(j);

                    if (row.getRuleInfo() != null) {

                        if (detailProcess(row)) {
                            dtlProc = true;
                        }

                        // QC#22405
                        if (isWSErr) {
                            if (!errMsgBeanList.isEmpty()) {
                                rollback();
                                errorCount++;
                                sendErrMail();
                                commit();
                            }
                            return;
                        }
                        // 09/05/2017 CITS S.Endo Del
                        // Sol#406(QC#19243) START
                        // else {
                        // dtlProc = false;
                        // }
                        // 09/05/2017 CITS S.Endo Del
                        // Sol#406(QC#19243) END
                    }
                }

                if (dtlProc) {
                    List<String> errList;
                    // execute to create Insourced PR
                    if (ZYPCommonFunc.hasValue(insrcPrForSrcWhPMsg.prchReqRecTpCd)) {
                        // 08/31/2017 CITS S.Endo Add
                        // Sol#406(QC#19243) START
                        if (ZYPCommonFunc.hasValue(insrcPrToCratBOWhTransferPMsg.prchReqRecTpCd)) {
                            checkIsBODetail();
                        }

                        // QC#27660 Delete. Initialize line comment.
//                        if (allocateCompleteFlagList.size() > 0) {
//                            for (Integer index : allocateCompleteFlagList) {
//                                
//                                ZYPEZDItemValueSetter.setValue(insrcPrForSrcWhPMsg.prchReqInfo.no(index).prchReqLineCmntTxt, "");
//                            }
//                        }

                        // 08/29/2017 CITS S.Endo Add
                        // Sol#406(QC#19243) END
                        executeNPZC1030(insrcPrForSrcWhPMsg);
                        // QC#55615 Start
//                        errList = S21ApiUtil.getXxMsgIdList(insrcPrForSrcWhPMsg);
                        List<S21ApiMessage> errMsgList = S21ApiUtil.getXxMsgList(insrcPrForSrcWhPMsg);

                        if (errMsgList.size() > 0) {
                            for (S21ApiMessage apiErrMsg : errMsgList) {
                                String xxMsgId = apiErrMsg.getXxMsgid();
                                String[] xxMsgPrm = apiErrMsg.getXxMsgPrmArray();
                                S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));
                                ErrorMessageBean errMsgBean = new ErrorMessageBean();
                                errMsgBean.setPrchReqNum(insrcPrForSrcWhPMsg.prchReqNum.getValue());

                                if (xxMsgPrm != null && xxMsgPrm.length == 1) {
                                    // Hard Allocation error process. 
                                    for (int c = 0; c < insrcPrForSrcWhPMsg.prchReqInfo.getValidCount(); c++) {
                                        if (xxMsgPrm[0].equals(insrcPrForSrcWhPMsg.prchReqInfo.no(c).prchReqLineNum.getValue())) {
                                            errMsgBean.setPrchReqLineNum(insrcPrForSrcWhPMsg.prchReqInfo.no(c).prchReqLineNum.getValue());
                                            errMsgBean.setMdseCd(insrcPrForSrcWhPMsg.prchReqInfo.no(c).mdseCd.getValue());
                                            errMsgBean.setPrchReqQty(insrcPrForSrcWhPMsg.prchReqInfo.no(c).prchReqQty.getValue());
                                        }
                                    }
                                } else {
                                    errMsgBean.setPrchReqLineNum(insrcPrForSrcWhPMsg.prchReqInfo.no(insrcPrForSrcWhPMsg.prchReqInfo.getValidCount() - 1).prchReqLineNum.getValue());
                                    errMsgBean.setMdseCd(insrcPrForSrcWhPMsg.prchReqInfo.no(insrcPrForSrcWhPMsg.prchReqInfo.getValidCount() - 1).mdseCd.getValue());
                                    errMsgBean.setPrchReqQty(insrcPrForSrcWhPMsg.prchReqInfo.no(insrcPrForSrcWhPMsg.prchReqInfo.getValidCount() - 1).prchReqQty.getValue());
                                }
                                errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage(xxMsgId));
                                errMsgBeanList.add(errMsgBean);

                                if (xxMsgId.endsWith("E")) {
                                    rollback();
                                    commitCount--;
                                    errorCount++;
                                }
                            }

                        }
                        commit();
                        commitCount++;

                    }
                    if (ZYPCommonFunc.hasValue(insrcPrForInsrcPlnPMsg.prchReqRecTpCd)) {

                        executeNPZC1030(insrcPrForInsrcPlnPMsg);
                        errList = S21ApiUtil.getXxMsgIdList(insrcPrForInsrcPlnPMsg);
                        if (errList.size() > 0) {
                            for (String xxMsgId : errList) {
                                S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));
                                ErrorMessageBean errMsgBean = new ErrorMessageBean();
                                errMsgBean.setPrchReqNum(insrcPrForInsrcPlnPMsg.prchReqNum.getValue());
                                errMsgBean.setPrchReqLineNum(insrcPrForInsrcPlnPMsg.prchReqInfo.no(insrcPrForInsrcPlnPMsg.prchReqInfo.getValidCount() - 1).prchReqLineNum.getValue());
                                errMsgBean.setMdseCd(insrcPrForInsrcPlnPMsg.prchReqInfo.no(insrcPrForInsrcPlnPMsg.prchReqInfo.getValidCount() - 1).mdseCd.getValue());
                                errMsgBean.setPrchReqQty(insrcPrForInsrcPlnPMsg.prchReqInfo.no(insrcPrForInsrcPlnPMsg.prchReqInfo.getValidCount() - 1).prchReqQty.getValue());
                                errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage(xxMsgId));
                                errMsgBeanList.add(errMsgBean);

                                if (xxMsgId.endsWith("E")) {
                                    rollback();
                                    commitCount--;
                                    errorCount++;
                                }
                            }

                        }
                        commit();
                        commitCount++;
                    }
                    if (ZYPCommonFunc.hasValue(insrcPrForChoicePMsg.prchReqRecTpCd)) {
                        // QC#55042. Mearge Insoucing Choice
                        if (duplicateChoiceItemMerge()) {

                            executeNPZC1030(insrcPrForChoicePMsg);
                            errList = S21ApiUtil.getXxMsgIdList(insrcPrForChoicePMsg);
                            if (errList.size() > 0) {
                                for (String xxMsgId : errList) {
                                    S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));
                                    ErrorMessageBean errMsgBean = new ErrorMessageBean();
                                    errMsgBean.setPrchReqNum(insrcPrForChoicePMsg.prchReqNum.getValue());
                                    errMsgBean.setPrchReqLineNum(insrcPrForChoicePMsg.prchReqInfo.no(insrcPrForChoicePMsg.prchReqInfo.getValidCount() - 1).prchReqLineNum.getValue());
                                    errMsgBean.setMdseCd(insrcPrForChoicePMsg.prchReqInfo.no(insrcPrForChoicePMsg.prchReqInfo.getValidCount() - 1).mdseCd.getValue());
                                    errMsgBean.setPrchReqQty(insrcPrForChoicePMsg.prchReqInfo.no(insrcPrForChoicePMsg.prchReqInfo.getValidCount() - 1).prchReqQty.getValue());
                                    errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage(xxMsgId));
                                    errMsgBeanList.add(errMsgBean);

                                    if (xxMsgId.endsWith("E")) {
                                        rollback();
                                        commitCount--;
                                        errorCount++;
                                    }
                                }
                            }
                        }
                        commit();
                        commitCount++;
                    }
                    if (ZYPCommonFunc.hasValue(insrcPrForSpecWhPMsg.prchReqRecTpCd)) {

                        executeNPZC1030(insrcPrForSpecWhPMsg);
                        errList = S21ApiUtil.getXxMsgIdList(insrcPrForSpecWhPMsg);
                        if (errList.size() > 0) {
                            for (String xxMsgId : errList) {
                                S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));
                                ErrorMessageBean errMsgBean = new ErrorMessageBean();
                                errMsgBean.setPrchReqNum(insrcPrForSpecWhPMsg.prchReqNum.getValue());
                                errMsgBean.setPrchReqLineNum(insrcPrForSpecWhPMsg.prchReqInfo.no(insrcPrForSpecWhPMsg.prchReqInfo.getValidCount() - 1).prchReqLineNum.getValue());
                                errMsgBean.setMdseCd(insrcPrForSpecWhPMsg.prchReqInfo.no(insrcPrForSpecWhPMsg.prchReqInfo.getValidCount() - 1).mdseCd.getValue());
                                errMsgBean.setPrchReqQty(insrcPrForSpecWhPMsg.prchReqInfo.no(insrcPrForSpecWhPMsg.prchReqInfo.getValidCount() - 1).prchReqQty.getValue());
                                errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage(xxMsgId));
                                errMsgBeanList.add(errMsgBean);

                                if (xxMsgId.endsWith("E")) {
                                    rollback();
                                    commitCount--;
                                    errorCount++;
                                }
                            }

                        }
                        commit();
                        commitCount++;
                    }
                    if (ZYPCommonFunc.hasValue(insrcPrForVndPMsg.prchReqRecTpCd)) {

                        insrcPrForVndPMsg = executeNPZC1030(insrcPrForVndPMsg);
                        errList = S21ApiUtil.getXxMsgIdList(insrcPrForVndPMsg);
                        if (errList.size() > 0) {
                            for (String xxMsgId : errList) {
                                S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));
                                ErrorMessageBean errMsgBean = new ErrorMessageBean();
                                errMsgBean.setPrchReqNum(insrcPrForVndPMsg.prchReqNum.getValue());
                                errMsgBean.setPrchReqLineNum(insrcPrForVndPMsg.prchReqInfo.no(insrcPrForVndPMsg.prchReqInfo.getValidCount() - 1).prchReqLineNum.getValue());
                                errMsgBean.setMdseCd(insrcPrForVndPMsg.prchReqInfo.no(insrcPrForVndPMsg.prchReqInfo.getValidCount() - 1).mdseCd.getValue());
                                errMsgBean.setPrchReqQty(insrcPrForVndPMsg.prchReqInfo.no(insrcPrForVndPMsg.prchReqInfo.getValidCount() - 1).prchReqQty.getValue());
                                errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage(xxMsgId));
                                errMsgBeanList.add(errMsgBean);

                                if (xxMsgId.endsWith("E")) {
                                    rollback();
                                    commitCount--;
                                    errorCount++;
                                }
                            }

                        }
                        commit();
                        commitCount++;
                    }
                    if (ZYPCommonFunc.hasValue(insrcPrToCratPrPMsg.prchReqRecTpCd)) {

                        // set Original Line Sub Number and
                        // Transaction Ref Line Sub Number
                        setLineSubNum(insrcPrForVndPMsg, insrcPrToCratPrPMsg);
                        executeNPZC1030(insrcPrToCratPrPMsg);
                        errList = S21ApiUtil.getXxMsgIdList(insrcPrToCratPrPMsg);
                        if (errList.size() > 0) {
                            for (String xxMsgId : errList) {
                                S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));
                                ErrorMessageBean errMsgBean = new ErrorMessageBean();
                                // 2019/07/30 QC#52018 ADD Start
//                                errMsgBean.setPrchReqNum(insrcPrToCratPrPMsg.prchReqNum.getValue());
//                                errMsgBean.setPrchReqLineNum(insrcPrToCratPrPMsg.prchReqInfo.no(insrcPrToCratPrPMsg.prchReqInfo.getValidCount() - 1).prchReqLineNum.getValue());
                                errMsgBean.setPrchReqNum(insrcPrToCratPrPMsg.prchReqInfo.no(insrcPrToCratPrPMsg.prchReqInfo.getValidCount() - 1).trxRefNum.getValue());
                                errMsgBean.setPrchReqLineNum(insrcPrToCratPrPMsg.prchReqInfo.no(insrcPrToCratPrPMsg.prchReqInfo.getValidCount() - 1).trxRefLineNum.getValue());
                                // 2019/07/30 QC#52018 ADD End
                                errMsgBean.setMdseCd(insrcPrToCratPrPMsg.prchReqInfo.no(insrcPrToCratPrPMsg.prchReqInfo.getValidCount() - 1).mdseCd.getValue());
                                errMsgBean.setPrchReqQty(insrcPrToCratPrPMsg.prchReqInfo.no(insrcPrToCratPrPMsg.prchReqInfo.getValidCount() - 1).prchReqQty.getValue());
                                errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage(xxMsgId));
                                errMsgBeanList.add(errMsgBean);

                                if (xxMsgId.endsWith("E")) {
                                    rollback();
                                    commitCount--;
                                    errorCount++;
                                }
                            }
                        }
                        commit();
                        commitCount++;
                    }
                    // QC#52575/QC#52576 Start
                    if (ZYPCommonFunc.hasValue(insrcAvalInvPrForVndPMsg.prchReqRecTpCd)) {

                        insrcAvalInvPrForVndPMsg = executeNPZC1030(insrcAvalInvPrForVndPMsg);
                        errList = S21ApiUtil.getXxMsgIdList(insrcAvalInvPrForVndPMsg);
                        if (errList.size() > 0) {
                            for (String xxMsgId : errList) {
                                S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));
                                ErrorMessageBean errMsgBean = new ErrorMessageBean();
                                errMsgBean.setPrchReqNum(insrcAvalInvPrForVndPMsg.prchReqNum.getValue());
                                errMsgBean.setPrchReqLineNum(insrcAvalInvPrForVndPMsg.prchReqInfo.no(insrcAvalInvPrForVndPMsg.prchReqInfo.getValidCount() - 1).prchReqLineNum.getValue());
                                errMsgBean.setMdseCd(insrcAvalInvPrForVndPMsg.prchReqInfo.no(insrcAvalInvPrForVndPMsg.prchReqInfo.getValidCount() - 1).mdseCd.getValue());
                                errMsgBean.setPrchReqQty(insrcAvalInvPrForVndPMsg.prchReqInfo.no(insrcAvalInvPrForVndPMsg.prchReqInfo.getValidCount() - 1).prchReqQty.getValue());
                                errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage(xxMsgId));
                                errMsgBeanList.add(errMsgBean);

                                if (xxMsgId.endsWith("E")) {
                                    rollback();
                                    commitCount--;
                                    errorCount++;
                                }
                            }

                        }
                        commit();
                        commitCount++;
                    }
                    if (ZYPCommonFunc.hasValue(insrcPrToCratPrAvalInvPMsg.prchReqRecTpCd)) {

                        // set Original Line Sub Number and
                        // Transaction Ref Line Sub Number
                        setLineSubNum(insrcAvalInvPrForVndPMsg, insrcPrToCratPrAvalInvPMsg);
                        executeNPZC1030(insrcPrToCratPrAvalInvPMsg);
                        errList = S21ApiUtil.getXxMsgIdList(insrcPrToCratPrAvalInvPMsg);
                        if (errList.size() > 0) {
                            for (String xxMsgId : errList) {
                                S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));
                                ErrorMessageBean errMsgBean = new ErrorMessageBean();
                                errMsgBean.setPrchReqNum(insrcPrToCratPrAvalInvPMsg.prchReqInfo.no(insrcPrToCratPrAvalInvPMsg.prchReqInfo.getValidCount() - 1).trxRefNum.getValue());
                                errMsgBean.setPrchReqLineNum(insrcPrToCratPrAvalInvPMsg.prchReqInfo.no(insrcPrToCratPrAvalInvPMsg.prchReqInfo.getValidCount() - 1).trxRefLineNum.getValue());
                                errMsgBean.setMdseCd(insrcPrToCratPrAvalInvPMsg.prchReqInfo.no(insrcPrToCratPrAvalInvPMsg.prchReqInfo.getValidCount() - 1).mdseCd.getValue());
                                errMsgBean.setPrchReqQty(insrcPrToCratPrAvalInvPMsg.prchReqInfo.no(insrcPrToCratPrAvalInvPMsg.prchReqInfo.getValidCount() - 1).prchReqQty.getValue());
                                errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage(xxMsgId));
                                errMsgBeanList.add(errMsgBean);

                                if (xxMsgId.endsWith("E")) {
                                    rollback();
                                    commitCount--;
                                    errorCount++;
                                }
                            }
                        }
                        commit();
                        commitCount++;
                    }
                    // QC#52575/QC#52576 End
                    if (ZYPCommonFunc.hasValue(insrcPrToCratBOWhTransferPMsg.prchReqRecTpCd)) {
                        commit();
                        commitCount++;
                    }

                    totalCommitCount += commitCount;
                    commitCount = 0;
                }

                // QC#57659 Add start. 2020/12/24 Mod QC#58170
                // Choice Send Mail
                if (choicePrInfoMapList != null && !choicePrInfoMapList.isEmpty()) {
                    sendMailChoice(choicePrInfoMapList);
                    totalCommitCount++;
                    commit();
                }

                // MNX Courier Job request
                if (dbsPrInfoMapList != null && !dbsPrInfoMapList.isEmpty()) {
                    // O5 Interface Table Insert
                    S21TransactionTableAccessor trxAccess = new S21TransactionTableAccessor();
                    BigDecimal trxId = trxAccess.getNextTransactionId();
                    NLBI1410_01TMsg if01TMsg = createNLBI1410_01_PR(trxId);
                    // QC#57659-2
                    if (if01TMsg != null) {
                        EZDTBLAccessor.insert(if01TMsg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(if01TMsg.getReturnCode())) {
                            S21InfoLogOutput.println("ERR_CD : " + if01TMsg.getReturnCode());
                            S21InfoLogOutput.println(if01TMsg.toString());
                            throw new S21AbendException("NPAM1172E", new String[] {"NLBI1410_01" });
                        }

                        // Create Transaction table.
                        trxAccess.createIntegrationRecordForBatch("NPAB1411", trxId);
                        commit();
                    }
                }
                // QC#57659 Add end. 2020/12/24 Mod QC#58170
            }
        }

        // if error bean is not empty, send email
        if (!errMsgBeanList.isEmpty()) {
            sendErrMail();
        }
    }

    // 09/05/2017 CITS S.Endo Add Sol#406(QC#19243) START
    private void checkIsBODetail() {
        for (int index = 0; index < insrcPrToCratBOWhTransferPMsg.prchReqInfo.getValidCount(); index++) {
            for (int j = 0; j < insrcPrForSrcWhPMsg.prchReqInfo.getValidCount(); j++) {
                if (insrcPrForSrcWhPMsg.prchReqInfo.no(index).invtyOrdLineNum.getValue().equals(insrcPrForSrcWhPMsg.prchReqInfo.no(j).invtyOrdLineNum.getValue())) {
                    // QC#27660 Update.
                    ZYPEZDItemValueSetter.setValue(insrcPrForSrcWhPMsg.prchReqInfo.no(j).prchReqLineCmntTxt, //
                            addFirstLineCmnt(boDetailCmnt, insrcPrForSrcWhPMsg.prchReqInfo.no(j).prchReqLineCmntTxt.getValue()));
                    break;
                }
            }
        }
    }

    // 09/05/2017 CITS S.Endo Add Sol#406(QC#19243) END

    private boolean detailProcess(PrInfoBean prInfo) {

        S21InfoLogOutput.println(String.format("[NPAB1360] To start the process. PR#%s-%s", prInfo.getHeaderInfo().prchReqNum.getValue(), prInfo.getDetailInfo().prchReqLineNum.getValue()));

        ArrayList<INSRC_RULE_DTLTMsg> ruleDetailList = null;

        // QC#57659 Mod start
        if (PRCH_REQ_TP.PREMIUM_RUSH.equals(prInfo.getHeaderInfo().prchReqTpCd.getValue()) //
                && SHPG_SVC_LVL.CUSTOMER_PICK_UP.equals(prInfo.getDetailInfo().shpgSvcLvlCd.getValue())) {

            ruleDetailList = new ArrayList<INSRC_RULE_DTLTMsg>();
            INSRC_RULE_DTLTMsg rule = setSrchOrigItemSrcWhRule();

            WH_OWNRTMsg tMsg = getWhOwnr(prInfo.getDetailInfo().srcRtlWhCd.getValue());

            ZYPEZDItemValueSetter.setValue(rule.prchReqLineTpCd, tMsg.insrcPrchReqLineTpCd);
            ruleDetailList.add(rule);

        } else if (PRCH_REQ_TP.PREMIUM_RUSH.equals(prInfo.getHeaderInfo().prchReqTpCd.getValue()) //
                && SHPG_SVC_LVL.SCHD_DELIVERY.equals(prInfo.getDetailInfo().shpgSvcLvlCd.getValue())) {

            ruleDetailList = new ArrayList<INSRC_RULE_DTLTMsg>();
            INSRC_RULE_DTLTMsg rule = setSrchOrigItemSrcWhRule();
            WH_OWNRTMsg tMsg = getWhOwnr(prInfo.getDetailInfo().srcRtlWhCd.getValue());

            ZYPEZDItemValueSetter.setValue(rule.prchReqLineTpCd, tMsg.insrcPrchReqLineTpCd);
            ruleDetailList.add(rule);

        } else if (ruleDetailMap.containsKey(prInfo.getRuleInfo().insrcRuleDtlNum.getValue())) {
            ruleDetailList = ruleDetailMap.get(prInfo.getRuleInfo().insrcRuleDtlNum.getValue());

        } else {
            ruleDetailList = getInsourcingRuleDetail(prInfo.getRuleInfo().insrcRuleDtlNum.getValue());
            ruleDetailMap.put(prInfo.getRuleInfo().insrcRuleDtlNum.getValue(), ruleDetailList);
        }
        // QC#57659 Mod end

        BigDecimal reqQty = prInfo.getDetailInfo().prchReqBalQty.getValue();
        // START 2023/06/02 S.Dong [QC#55629, ADD]
        BigDecimal origReqQty = prInfo.getDetailInfo().prchReqQty.getValue();
        // END 2023/06/02 S.Dong [QC#55629, ADD]
        BigDecimal allocCount = BigDecimal.ZERO;

        for (INSRC_RULE_DTLTMsg ruleDetail : ruleDetailList) {
            ArrayList<String> mdseCdList = null;

            if (ZYPConstant.FLG_ON_Y.equals(ruleDetail.srchOrigItemFlg.getValue())) {

                mdseCdList = new ArrayList<String>();
                mdseCdList.add(prInfo.getDetailInfo().mdseCd.getValue());

            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDetail.srchSupdItemFlg.getValue())) {

                mdseCdList = getSupersedeItem(prInfo.getDetailInfo().mdseCd.getValue());

            } else if (ZYPCommonFunc.hasValue(ruleDetail.srchMdseItemRelnTpCd.getValue())) {

                if (MDSE_ITEM_RELN_TP.COMPATIBLE.equals(ruleDetail.srchMdseItemRelnTpCd.getValue())) {
                    // QC#59718 Modify
                    // mdseCdList = getRelnMdseCd(prInfo.getDetailInfo().mdseCd.getValue(), ruleDetail.srchMdseItemRelnTpCd.getValue());
                    List<String> mdseList  = NPXC001001GetMdseRelationshipData.getMdseRelationshipData(this.globalCompanyCode, prInfo.getDetailInfo().mdseCd.getValue(), new String[]{ruleDetail.srchMdseItemRelnTpCd.getValue()});
                    // QC#59888 Add
                    if (mdseList != null && !mdseList.isEmpty() && ZYPConstant.FLG_OFF_N.equals(ruleDetail.srchOrigItemFlg.getValue())) {
                        mdseList.remove(prInfo.getDetailInfo().mdseCd.getValue());
                    }
                    mdseCdList = (ArrayList<String>)mdseList;

                } else if (MDSE_ITEM_RELN_TP.REFURBISHED.equals(ruleDetail.srchMdseItemRelnTpCd.getValue())) {

                    if (MDSE_ITEM_RELN_TP.REFURBISHED.equals(prInfo.getMdseInfo().mdseItemClsTpCd.getValue())) {
                        // QC#59718 Modify
                        // mdseCdList = getMdseCd(prInfo.getDetailInfo().mdseCd.getValue(), ruleDetail.srchMdseItemRelnTpCd.getValue());
                        List<String> mdseList  = NPXC001001GetMdseRelationshipData.getMdseRelationshipDataForRefurbished(this.globalCompanyCode, prInfo.getDetailInfo().mdseCd.getValue(), new String[]{ruleDetail.srchMdseItemRelnTpCd.getValue()});
                        // QC#59888 Add
                        if (mdseList != null && !mdseList.isEmpty() && ZYPConstant.FLG_OFF_N.equals(ruleDetail.srchOrigItemFlg.getValue())) {
                            mdseList.remove(prInfo.getDetailInfo().mdseCd.getValue());
                        }
                        mdseCdList = (ArrayList<String>)mdseList;
                    } else {
                        // QC#59718 Modify
                        // mdseCdList = getRelnMdseCd(prInfo.getDetailInfo().mdseCd.getValue(), ruleDetail.srchMdseItemRelnTpCd.getValue());
                        List<String> mdseList  = NPXC001001GetMdseRelationshipData.getMdseRelationshipData(this.globalCompanyCode, prInfo.getDetailInfo().mdseCd.getValue(), new String[]{ruleDetail.srchMdseItemRelnTpCd.getValue()});
                        // QC#59888 Add
                        if (mdseList != null && !mdseList.isEmpty() && ZYPConstant.FLG_OFF_N.equals(ruleDetail.srchOrigItemFlg.getValue())) {
                            mdseList.remove(prInfo.getDetailInfo().mdseCd.getValue());
                        }
                        mdseCdList = (ArrayList<String>)mdseList;

                    }
                } else {
                    // QC#59718 Modify
                    // mdseCdList = getRelnMdseCd(prInfo.getDetailInfo().mdseCd.getValue(), ruleDetail.srchMdseItemRelnTpCd.getValue());
                    List<String> mdseList  = NPXC001001GetMdseRelationshipData.getMdseRelationshipData(this.globalCompanyCode, prInfo.getDetailInfo().mdseCd.getValue(), new String[]{ruleDetail.srchMdseItemRelnTpCd.getValue()});
                    // QC#59888 Add
                    if (mdseList != null && !mdseList.isEmpty() && ZYPConstant.FLG_OFF_N.equals(ruleDetail.srchOrigItemFlg.getValue())) {
                        mdseList.remove(prInfo.getDetailInfo().mdseCd.getValue());
                    }
                    mdseCdList = (ArrayList<String>)mdseList;

                }
            }

            if (mdseCdList != null && mdseCdList.size() > 0) {
                // QC#2366 check hazmat.
                boolean includeHazmat = isHazmat(mdseCdList);

                for (String mdseCd : mdseCdList) {
                    // START 2023/06/02 S.Dong [QC#55629, MOD]
                    //BigDecimal retVal = ruleDetailProcess(prInfo, ruleDetail, mdseCd, reqQty, includeHazmat);
                    BigDecimal retVal = ruleDetailProcess(prInfo, ruleDetail, mdseCd, reqQty, includeHazmat, allocCount);
                    // END 2023/06/02 S.Dong [QC#55629, MOD]
                    
                    // QC#61128 Add Start
                    if (isTplErr) {
                        break;
                    }
                    // QC#61128 Add End

                    if (retVal != null) {
                        allocCount = allocCount.add(retVal);
                        reqQty = reqQty.subtract(retVal);

                        if (reqQty.compareTo(BigDecimal.ZERO) <= 0) {
                            // 08/31/2017 CITS S.Endo Add
                            // Sol#406(QC#19243) START
                            if (currentIndexForBO != null && !ZYPCommonFunc.hasValue(insrcPrToCratBOWhTransferPMsg.prchReqRecTpCd)) {
                                allocateCompleteFlagList.add(currentIndexForBO);
                                currentIndexForBO = null;
                            }
                            // 08/31/2017 CITS S.Endo Add
                            // Sol#406(QC#19243) END
                            // START 2023/06/02 S.Dong [QC#55629, ADD]
                            // update original line
                            if (origReqQty.compareTo(allocCount) < 0) {
                                PRCH_REQ_DTLTMsg prdTMsg = new PRCH_REQ_DTLTMsg();
                                ZYPEZDItemValueSetter.setValue(prdTMsg.glblCmpyCd, this.globalCompanyCode);
                                ZYPEZDItemValueSetter.setValue(prdTMsg.prchReqNum, prInfo.getHeaderInfo().prchReqNum);
                                ZYPEZDItemValueSetter.setValue(prdTMsg.prchReqLineNum, prInfo.getDetailInfo().prchReqLineNum);
                                prdTMsg = getPrntPrchReqDtlTMsg(prdTMsg);
                                if (prdTMsg != null && allocCount.compareTo(prdTMsg.prchReqQty.getValue()) != 0) {
                                    ZYPEZDItemValueSetter.setValue(prdTMsg.prchReqQty, allocCount);
                                    ZYPEZDItemValueSetter.setValue(prdTMsg.prchReqBalQty, allocCount);
                                    EZDTBLAccessor.update(prdTMsg);
                                    if (prdTMsg != null && !EZDTBLAccessor.RTNCD_NORMAL.equals(prdTMsg.getReturnCode())) {
                                        S21InfoLogOutput.println("ERR_CD : " + prdTMsg.getReturnCode());
                                        S21InfoLogOutput.println(prdTMsg.toString());
                                        throw new S21AbendException("NPAM1171E", new String[] {"PRCH_REQ_DTL" });
                                    }
                                }
                            }
                            // END 2023/06/02 S.Dong [QC#55629, ADD]
                            break;
                        }
                    }

                    // QC#22405
                    if (retVal == null && isWSErr) {
                        return false;
                    }
                }
            }
            
            // QC#61128 Add Start
            if (isTplErr) {
                break;
            }
            // QC#61128 Add End

            if (reqQty.compareTo(BigDecimal.ZERO) <= 0) {
                break;
            }
        }
        // QC#57659 Add start
        if (PRCH_REQ_TP.PREMIUM_RUSH.equals(prInfo.getHeaderInfo().prchReqTpCd.getValue()) && BigDecimal.ZERO.compareTo(reqQty) < 0) {
            // Kick out process.
            preRushKickOut(prInfo, reqQty);
            // Next Batch Non target
            PRCH_REQ_DTLTMsg prdTMsg = new PRCH_REQ_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(prdTMsg.glblCmpyCd, this.globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(prdTMsg.prchReqNum, prInfo.getHeaderInfo().prchReqNum);
            ZYPEZDItemValueSetter.setValue(prdTMsg.prchReqLineNum, prInfo.getDetailInfo().prchReqLineNum);
            prdTMsg = getPrntPrchReqDtlTMsg(prdTMsg);
            if (prdTMsg != null) {
                ZYPEZDItemValueSetter.setValue(prdTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.COMPLEATED);
                ZYPEZDItemValueSetter.setValue(prdTMsg.prchReqBalQty, prdTMsg.prchReqQty.getValue().subtract(reqQty));
                EZDTBLAccessor.update(prdTMsg);
                if (prdTMsg != null && !EZDTBLAccessor.RTNCD_NORMAL.equals(prdTMsg.getReturnCode())) {
                    S21InfoLogOutput.println("ERR_CD : " + prdTMsg.getReturnCode());
                    S21InfoLogOutput.println(prdTMsg.toString());
                    throw new S21AbendException("NPAM1172E", new String[] {"PRCH_REQ_DTL" });
                }
            }
            return true;
        }
        // QC#57659 Add end
        
        // QC#61128 Add Start
        if (isTplErr) {
            isTplErr = false;
            return false;
        }
        // QC#61128 Add End

        if (allocCount.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }

        return false;
    }

    // QC#2366 Update method.
    // START 2023/06/02 S.Dong [QC#55629, MOD]
    //private BigDecimal ruleDetailProcess(PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String mdseCd, BigDecimal reqQty, boolean includeHazmat) {
    private BigDecimal ruleDetailProcess(PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String mdseCd, BigDecimal reqQty, boolean includeHazmat, BigDecimal allocCount) {
    // END 2023/06/02 S.Dong [QC#55629, MOD]
        S21InfoLogOutput.println(String.format("[NPAB1360] Exec search process(1). PR#%s-%s, Original MDSE#%s, Search MDSE#%s", prInfo.getHeaderInfo().prchReqNum.getValue(), prInfo.getDetailInfo().prchReqLineNum.getValue(),
                ruleDtl.insrcRuleDtlNm.getValue(), prInfo.getDetailInfo().mdseCd.getValue(), mdseCd));

        if ((reqQty == null) || (reqQty.equals(BigDecimal.ZERO))) {
            return BigDecimal.ZERO;
        }

        BigDecimal ret = null;

        if (includeHazmat) {
            if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.srchSrcWhFlg.getValue()) //
                    && hazmatInsrcRuleList.contains(ruleDtl.prchReqLineTpCd.getValue())) {
                ret = srchStkProcDtlForSrcWh(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);

            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.srchInsrcPlnFlg.getValue())) {
                // Check Hazmat item in srchStkProcDtlForInsrcPln.
                if (!ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) {
                    ret = srchStkProcDtlForInsrcPln(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                }

            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.srchCusaInvtyFlg.getValue()) //
                    && hazmatInsrcRuleList.contains(ruleDtl.prchReqLineTpCd.getValue())) {
                if ((!ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) && (!ZYPConstant.FLG_OFF_N.equals(prInfo.getMdseItemStsInfo().prchAvalFlg.getValue()))) {
                    ret = srchStkProcDtlForCusa(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                }

            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.srchCviInvtyFlg.getValue()) //
                    && hazmatInsrcRuleList.contains(ruleDtl.prchReqLineTpCd.getValue())) {
                if ((!ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) && (!ZYPConstant.FLG_OFF_N.equals(prInfo.getMdseItemStsInfo().prchAvalFlg.getValue()))) {
                    ret = srchStkProcDtlForCvi(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                }

            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.srchWhOwnrInvtyFlg.getValue()) //
                    && hazmatInsrcRuleList.contains(ruleDtl.prchReqLineTpCd.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) {
                    // QC#57659 Mod start
                    // ret = srchStkProcDtlForChoice(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                    ret = srchStkProcDtlForWhOwner(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                    // QC#57659 Mod end
                }

            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.srchSpecLocInvtyFlg.getValue()) //
                    && hazmatInsrcRuleList.contains(ruleDtl.prchReqLineTpCd.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) {
                    ret = srchStkProcDtlForSpecWh(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                }

            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.cratPoFlg.getValue()) //
                    && hazmatInsrcRuleList.contains(ruleDtl.prchReqLineTpCd.getValue())) {
                if (!(((ZYPConstant.FLG_OFF_N.equals(prInfo.getMdseItemStsInfo().prchAvalFlg.getValue())) || ((!(PROCR_TP.SUPPLIER.equals(prInfo.getDetailInfo().procrTpCd.getValue()))) && (ZYPConstant.FLG_ON_Y
                        .equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())))))) {
                    // START 2023/06/02 S.Dong [QC#55629, MOD]
                    //ret = createPo(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                    ret = createPo(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat, allocCount);
                    // END 2023/06/02 S.Dong [QC#55629, MOD]
                // QC#30355
                } else if (PROCR_TP.WAREHOUSE.equals(prInfo.getDetailInfo().procrTpCd.getValue()) //
                        && ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) {
                    return ret;
                } else {
                    // QC#27655 Add Error message.
                    // Since the target MDSE CD is set as "DISCONTINUED"
                    ErrorMessageBean errMsgBean = new ErrorMessageBean();
                    errMsgBean.setPrchReqNum(prInfo.getHeaderInfo().prchReqNum.getValue());
                    errMsgBean.setPrchReqLineNum(prInfo.getDetailInfo().prchReqLineNum.getValue());
                    errMsgBean.setMdseCd(mdseCd);
                    errMsgBean.setPrchReqQty(reqQty);
                    errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage("NPAM1627E"));
                    errMsgBeanList.add(errMsgBean);
                }

                // 08/29/2017 CITS S.Endo Add Sol#406(QC#19243) START
                // }
            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.whTrnsfBoReqFlg.getValue()) //
                    && hazmatInsrcRuleList.contains(ruleDtl.prchReqLineTpCd.getValue())) {
                // QC#58785
                if ((ZYPCommonFunc.hasValue(ruleDtl.boSrcRtlWhCd.getValue())) && (ZYPCommonFunc.hasValue(ruleDtl.boSrcRtlSwhCd.getValue()))) {
                    if (!ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) {
                        ret = createBoWhTransfer(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                    }
                }
                // 08/29/2017 CITS S.Endo Add Sol#406(QC#19243) END
            // QC#57659 Add start
            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.srchStltInvtyFlg.getValue()) //
                    && hazmatInsrcRuleList.contains(ruleDtl.prchReqLineTpCd.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) {
                    ret = srchStkProcDtlForInsrcStlt(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                }
            // QC#57659 Add end
            // QC#61128 Add Start
            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.srchThirdPtyInvtyFlg.getValue()) //
                    && hazmatInsrcRuleList.contains(ruleDtl.prchReqLineTpCd.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) {
                    ret = srchStkProcDtlForThirdPty(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                }
            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.srchOrclInvtyFlg.getValue()) //
                    && hazmatInsrcRuleList.contains(ruleDtl.prchReqLineTpCd.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) {
                    ret = srchStkProcDtlForOrcl(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                }
            // QC#61128 Add End
            } else {
                if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.origLineFrzFlg.getValue())) {
                    // QC#27660. Mod QC#55514
                    freeze(prInfo, ruleDtl);
                }

                if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.boNtfyReqFlg.getValue()) && ZYPConstant.FLG_OFF_N.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) {
                    // START 2023/06/02 S.Dong [QC#55629, MOD]
                    //sendMail(prInfo, reqQty, null, null, null);
                    sendMail(prInfo, reqQty, null, null, null, null);
                    // END 2023/06/02 S.Dong [QC#55629, MOD]
                }
                commit();
            }
        } else {

            if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.srchSrcWhFlg.getValue())) {
                ret = srchStkProcDtlForSrcWh(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);

            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.srchInsrcPlnFlg.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) {
                    ret = srchStkProcDtlForInsrcPln(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                }

            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.srchCusaInvtyFlg.getValue())) {
                if ((!ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) && (!ZYPConstant.FLG_OFF_N.equals(prInfo.getMdseItemStsInfo().prchAvalFlg.getValue()))) {
                    ret = srchStkProcDtlForCusa(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                }

            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.srchCviInvtyFlg.getValue())) {
                if ((!ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) && (!ZYPConstant.FLG_OFF_N.equals(prInfo.getMdseItemStsInfo().prchAvalFlg.getValue()))) {
                    ret = srchStkProcDtlForCvi(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                }

            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.srchWhOwnrInvtyFlg.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) {
                    // QC#57659 Mod start
                    // ret = srchStkProcDtlForChoice(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                    ret = srchStkProcDtlForWhOwner(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                    // QC#57659 Mod end
                }

            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.srchSpecLocInvtyFlg.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) {
                    ret = srchStkProcDtlForSpecWh(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                }

            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.cratPoFlg.getValue())) {
                if (!(((ZYPConstant.FLG_OFF_N.equals(prInfo.getMdseItemStsInfo().prchAvalFlg.getValue())) || ((!(PROCR_TP.SUPPLIER.equals(prInfo.getDetailInfo().procrTpCd.getValue()))) && (ZYPConstant.FLG_ON_Y
                        .equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())))))) {
                    // START 2023/06/02 S.Dong [QC#55629, MOD]
                    //ret = createPo(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                    ret = createPo(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat, allocCount);
                    // END 2023/06/02 S.Dong [QC#55629, MOD]
                // QC#30355
                } else if (PROCR_TP.WAREHOUSE.equals(prInfo.getDetailInfo().procrTpCd.getValue()) //
                        && ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) {
                    return ret;

                } else {
                    // QC#27655 Add Error message.
                    // Since the target MDSE CD is set as "DISCONTINUED"
                    ErrorMessageBean errMsgBean = new ErrorMessageBean();
                    errMsgBean.setPrchReqNum(prInfo.getHeaderInfo().prchReqNum.getValue());
                    errMsgBean.setPrchReqLineNum(prInfo.getDetailInfo().prchReqLineNum.getValue());
                    errMsgBean.setMdseCd(mdseCd);
                    errMsgBean.setPrchReqQty(reqQty);
                    errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage("NPAM1627E"));
                    errMsgBeanList.add(errMsgBean);
                }
                // 08/29/2017 CITS S.Endo Add Sol#406(QC#19243) START
                // }
            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.whTrnsfBoReqFlg.getValue())) {
                // QC#58785
                if ((ZYPCommonFunc.hasValue(ruleDtl.boSrcRtlWhCd.getValue())) && (ZYPCommonFunc.hasValue(ruleDtl.boSrcRtlSwhCd.getValue()))) {
                    if (!ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) {
                        ret = createBoWhTransfer(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                    }
                }
                // 08/29/2017 CITS S.Endo Add Sol#406(QC#19243) END
            // QC#57659 Add start
            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.srchStltInvtyFlg.getValue()) //
                    && hazmatInsrcRuleList.contains(ruleDtl.prchReqLineTpCd.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) {
                    ret = srchStkProcDtlForInsrcStlt(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                }
            // QC#57659 Add end
            // QC#61128 Add Start
            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.srchThirdPtyInvtyFlg.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) {
                    ret = srchStkProcDtlForThirdPty(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                }
            } else if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.srchOrclInvtyFlg.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(prInfo.getDetailInfo().prchReqFrzFlg.getValue())) {
                    ret = srchStkProcDtlForOrcl(prInfo, ruleDtl, mdseCd, reqQty, includeHazmat);
                }
            // QC#61128 Add End
            } else {
                if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.origLineFrzFlg.getValue())) {
                    // QC#27660 update. Mod QC#55514
                    freeze(prInfo, ruleDtl);
                }

                if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.boNtfyReqFlg.getValue())) {
                    // START 2023/06/02 S.Dong [QC#55629, MOD]
                    //sendMail(prInfo, reqQty, null, null, null);
                    sendMail(prInfo, reqQty, null, null, null, null);
                    // END 2023/06/02 S.Dong [QC#55629, MOD]
                }
                commit();
            }
        }

        return ret;
    }

    /**
     * from Source WH (Local)
     * @param prInfo
     * @param ruleDtl
     * @param mdseCd
     * @param reqQty
     * @return
     */
    private BigDecimal srchStkProcDtlForSrcWh(PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String mdseCd, BigDecimal reqQty, boolean includeHazmat) {

        S21InfoLogOutput.println(String.format("[NPAB1360] Exec search process(1). PR#%s-%s, Original MDSE#%s, Search MDSE#%s", prInfo.getHeaderInfo().prchReqNum.getValue(), prInfo.getDetailInfo().prchReqLineNum.getValue(), prInfo
                .getDetailInfo().mdseCd.getValue(), mdseCd));

        // 2023/09/04 QC#61703 START
        RTL_WHTMsg tMsg = getRtlWhTMsg(prInfo.getDetailInfo().srcInvtyLocCd.getValue());
        if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.whCutOffTm.getValue()) && ZYPConstant.FLG_ON_Y.equals(prInfo.getRuleInfo().whCutOffTmApplyFlg.getValue())) {
            // check cut off time.
            Date execDate = new Date();
            SimpleDateFormat sdfDate = new SimpleDateFormat();

            sdfDate.applyPattern(NPAB136001Constant.DATE_TIME_FORMAT);
            SvcTimeZoneInfo timeInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, sdfDate.format(execDate), tMsg.ctryCd.getValue(), tMsg.postCd.getValue());
            String time1 = tMsg.whCutOffTm.getValue().substring(0, NPAB136001Constant.TIME_LEN_HHMM);
            String time2 = timeInfo.getDateTime().substring(NPAB136001Constant.DATE_LEN_YYYYMMDD, NPAB136001Constant.DATE_LEN_YYYYMMDD + NPAB136001Constant.TIME_LEN_HHMM);

            if (time1.compareTo(time2) < 0) {
                return null;
            }
        }
        // 2023/09/04 QC#61703 END
        
        BigDecimal avaQty = getAvalWhInvty(mdseCd, prInfo.getDetailInfo().srcInvtyLocCd.getValue());

        if (avaQty == null) {
            return null;
        }

        BigDecimal prchReqQty = avaQty;

        if (avaQty.compareTo(reqQty) >= 0) {
            prchReqQty = reqQty;
        }
        // 08/29/2017 CITS S.Endo Mod Sol#406(QC#19243) START
        // createNPZC103001PMsgCreate(insrcPrForSrcWhPMsg, prInfo,
        // ruleDtl, ruleDtl.prchReqLineTpCd.getValue(),
        // prInfo.getDetailInfo().srcInvtyLocCd.getValue(), mdseCd,
        // prchReqQty);
        createNPZC103001PMsgCreate(insrcPrForSrcWhPMsg, prInfo, ruleDtl, ruleDtl.prchReqLineTpCd.getValue(), prInfo.getDetailInfo().srcInvtyLocCd.getValue(), mdseCd, prchReqQty, true, includeHazmat);
        // 08/29/2017 CITS S.Endo Mod Sol#406(QC#19243) END

        if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.origLineFrzFlg.getValue())) {
            // QC#27660 update. Mod QC#55514
            freeze(prInfo, ruleDtl);
        }

        if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.boNtfyReqFlg.getValue())) {
            // START 2023/06/02 S.Dong [QC#55629, MOD]
            //sendMail(prInfo, reqQty, null, null, null);
            sendMail(prInfo, reqQty, null, null, null, null);
            // END 2023/06/02 S.Dong [QC#55629, MOD]
        }

        return prchReqQty;
    }

    /**
     * from Insourcing Planning
     * QC#2366 Update method.
     * @param prInfo
     * @param ruleDtl
     * @param mdseCd
     * @param reqQty
     * @param includeHazmat
     * @return
     */
    private BigDecimal srchStkProcDtlForInsrcPln(PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String mdseCd, BigDecimal reqQty, boolean includeHazmat) {

        S21InfoLogOutput.println(String.format("[NPAB1360] Exec search process(2). PR#%s-%s, Original MDSE#%s, Search MDSE#%s", prInfo.getHeaderInfo().prchReqNum.getValue(), prInfo.getDetailInfo().prchReqLineNum.getValue(), prInfo
                .getDetailInfo().mdseCd.getValue(), mdseCd));

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BigDecimal allocCount = BigDecimal.ZERO;

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();

            paramMap.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB136001Constant.DB_PARAM_FROM_RTL_WH_CD, prInfo.getDetailInfo().srcRtlWhCd.getValue());
            paramMap.put(NPAB136001Constant.DB_PARAM_INSRC_ENBL_FLG, ZYPConstant.FLG_ON_Y);
            if (0 < countVarcharConstTable(prInfo.getMdseInfo().mdseItemClsTpCd.getValue())) {
                paramMap.put(NPAB136001Constant.DB_PARAM_MDSE_ITEM_CLS_TP_CD, prInfo.getMdseInfo().mdseItemClsTpCd.getValue());
            } else {
                paramMap.put(NPAB136001Constant.DB_PARAM_MDSE_ITEM_CLS_TP_CD, null);
            }
            paramMap.put(NPAB136001Constant.DB_PARAM_MDSE_CD, mdseCd);
            paramMap.put(NPAB136001Constant.DB_PARAM_MRP_ENBL_FLG, ZYPConstant.FLG_ON_Y);
            paramMap.put(NPAB136001Constant.DB_PARAM_SRC_RTL_SWH_CD, prInfo.getDetailInfo().srcRtlSwhCd.getValue());
            // QC#61128 Add Start
            paramMap.put(NPAB136001Constant.DB_PARAM_PRCH_REQ_TP_CD, prInfo.getHeaderInfo().prchReqTpCd.getValue());
            // QC#61128 Add End
            // 10/03/2023 QC#61703 Add start
            paramMap.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            // 10/03/2023 QC#61703 Add End

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("getInsourcingPlanning", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // QC#2366 Hazmat item check
                if (includeHazmat //
                        && !hazmatInsrcRuleList.contains(resultSet.getString(NPAB136001Constant.INSRC_PRCH_REQ_LINE_TP_CD))) {
                    continue;
                }

                BigDecimal retVal = srchStkProcDtlForInsrcPlnSub(prInfo, ruleDtl, resultSet, mdseCd, reqQty, includeHazmat);

                if (retVal != null) {
                    allocCount = allocCount.add(retVal);
                    reqQty = reqQty.subtract(retVal);

                    if (reqQty.compareTo(BigDecimal.ZERO) <= 0) {
                        break;
                    }
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return allocCount;
    }

    private BigDecimal srchStkProcDtlForInsrcPlnSub(PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, ResultSet rs, String mdseCd, BigDecimal reqQty, boolean includeHazmat) throws SQLException {

        if (ZYPCommonFunc.hasValue(rs.getString(NPAB136001Constant.WH_CUT_OFF_TM)) && ZYPConstant.FLG_ON_Y.equals(prInfo.getRuleInfo().whCutOffTmApplyFlg.getValue())) {
            // check cut off time.
            Date execDate = new Date();
            SimpleDateFormat sdfDate = new SimpleDateFormat();

            sdfDate.applyPattern(NPAB136001Constant.DATE_TIME_FORMAT);
            // QC#61128 Mod Start
            //SvcTimeZoneInfo timeInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, sdfDate.format(execDate), rs.getString(NPAB136001Constant.POST_CD), rs.getString(NPAB136001Constant.CTRY_CD));
            SvcTimeZoneInfo timeInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, sdfDate.format(execDate), rs.getString(NPAB136001Constant.CTRY_CD), rs.getString(NPAB136001Constant.POST_CD));
            // QC#61128 Mod End
            String time1 = rs.getString(NPAB136001Constant.WH_CUT_OFF_TM).substring(0, NPAB136001Constant.TIME_LEN_HHMM);
            String time2 = timeInfo.getDateTime().substring(NPAB136001Constant.DATE_LEN_YYYYMMDD, NPAB136001Constant.DATE_LEN_YYYYMMDD + NPAB136001Constant.TIME_LEN_HHMM);

            if (time1.compareTo(time2) < 0) {
                return null;
            }
        }

        if (ZYPCommonFunc.hasValue(rs.getBigDecimal(NPAB136001Constant.ROP_QTY)) || ZYPCommonFunc.hasValue(rs.getBigDecimal(NPAB136001Constant.MAX_INVTY_QTY))) {
            if (NPAB136001Constant.PLN_ITEM_INSRC_CD_SKIP.equals(rs.getString(NPAB136001Constant.PLN_ITEM_INSRC_CD))) {
                return null;
            }
        }

        BigDecimal avaQty = getAvalWhInvty(mdseCd, rs.getString(NPAB136001Constant.TO_RTL_WH_CD) + prInfo.getDetailInfo().srcRtlSwhCd.getValue());

        if (avaQty == null) {
            return null;
        }

        if (NPAB136001Constant.PLN_ITEM_INSRC_CD_MIN.equals(rs.getString(NPAB136001Constant.PLN_ITEM_INSRC_CD))) {
            if (ZYPCommonFunc.hasValue(rs.getBigDecimal(NPAB136001Constant.ROP_QTY))) {
                avaQty = avaQty.subtract(rs.getBigDecimal(NPAB136001Constant.ROP_QTY));
            }

        } else if (NPAB136001Constant.PLN_ITEM_INSRC_CD_MAX.equals(rs.getString(NPAB136001Constant.PLN_ITEM_INSRC_CD))) {
            if (ZYPCommonFunc.hasValue(rs.getBigDecimal(NPAB136001Constant.MAX_INVTY_QTY))) {
                avaQty = avaQty.subtract(rs.getBigDecimal(NPAB136001Constant.MAX_INVTY_QTY));
            }
        }

        if (avaQty.compareTo(BigDecimal.ZERO) <= 0) {
            return null;
        }

        BigDecimal prchReqQty = null;

        if (avaQty.compareTo(reqQty) >= 0) {
            prchReqQty = reqQty;

        } else {
            prchReqQty = avaQty;
        }

        // get primary SWH from TO_RTL_WH_CD
        String invtyLocCd = getPrtyLocCd(rs.getString(NPAB136001Constant.TO_RTL_WH_CD));

        createNPZC103001PMsgCreate(insrcPrForInsrcPlnPMsg, prInfo, ruleDtl, rs.getString(NPAB136001Constant.INSRC_PRCH_REQ_LINE_TP_CD), invtyLocCd, mdseCd, prchReqQty, includeHazmat);

        if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.origLineFrzFlg.getValue())) {
            // QC#27660 update. Mod QC#55514
            freeze(prInfo, ruleDtl);
        }

        if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.boNtfyReqFlg.getValue())) {
            // START 2023/06/02 S.Dong [QC#55629, MOD]
            //sendMail(prInfo, reqQty, null, null, null);
            sendMail(prInfo, reqQty, null, null, null, null);
            // END 2023/06/02 S.Dong [QC#55629, MOD]
        }

        return prchReqQty;
    }

    /**
     * from CUSA (Insourced PO)
     * @param prInfo
     * @param ruleDtl
     * @param rs
     * @param mdseCd
     * @param reqQty
     * @return
     * @throws SQLException
     */
    private BigDecimal srchStkProcDtlForCusa(PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String mdseCd, BigDecimal reqQty, boolean includeHazmat) {

        S21InfoLogOutput.println(String.format("[NPAB1360] Exec search process(3). PR#%s-%s, Original MDSE#%s, Search MDSE#%s", prInfo.getHeaderInfo().prchReqNum.getValue(), prInfo.getDetailInfo().prchReqLineNum.getValue(), prInfo
                .getDetailInfo().mdseCd.getValue(), mdseCd));

        // QC#18418 T.Hakodate ADD START
        if (doesNotExistInItemMaster.contains(mdseCd)) {

            ErrorMessageBean errMsgBean = new ErrorMessageBean();
            errMsgBean.setPrchReqNum(prInfo.getHeaderInfo().prchReqNum.getValue());
            errMsgBean.setPrchReqLineNum(prInfo.getDetailInfo().prchReqLineNum.getValue());
            errMsgBean.setMdseCd(mdseCd);
            errMsgBean.setPrchReqQty(prInfo.getDetailInfo().prchReqQty.getValue());
            errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage("NLZM2077E"));
            errMsgBeanList.add(errMsgBean);

            return null;
        }
        // START 2023/06/02 S.Dong [QC#55629, ADD]
        // adjust qty, get vndor minimum order qty
        NPZC136001PMsg pMsg = getMinVndQty(prInfo, mdseCd, reqQty);
        if (pMsg != null && reqQty.compareTo(pMsg.prchReqQty.getValue()) < 0) {
            reqQty = pMsg.prchReqQty.getValue();
        }
        // END 2023/06/02 S.Dong [QC#55629, ADD]
        // Mod. QC#29026
        NPZC113001PMsg vendorInfo = getPrimaryVendorInfo(prInfo, mdseCd, false, true);

        if (vendorInfo != null) {

            String vendorSystemType = getVendorSystemType(vendorInfo.vndCd.getValue());
            String searchCusaWs = null;
            String searchCusaParts = null;

            if ((ZYPCommonFunc.hasValue(vendorSystemType)) && (VND_SYS_TP.WHOLE_SALES.equals(vendorSystemType))) {
                searchCusaWs = ZYPConstant.FLG_ON_Y;
                searchCusaParts = ZYPConstant.FLG_OFF_N;
            } else if ((ZYPCommonFunc.hasValue(vendorSystemType)) && (VND_SYS_TP.PARTS.equals(vendorSystemType))) {
                searchCusaWs = ZYPConstant.FLG_OFF_N;
                searchCusaParts = ZYPConstant.FLG_ON_Y;
            } else {
                // Primary vendor is not CUSA WS or CUSA PARTS
                return null;
            }

            // 2023/09/04 QC#61703 START
            BigDecimal trxReqQty = (BigDecimal) mdseMap.get(vendorInfo.vndCd.getValue().concat(mdseCd));

            // 2023/09/08 QC#61704 START
            // NLZC300001PMsg avaInv = getAvalInvtyFromCusaAndCvi(mdseCd, reqQty, ZYPConstant.FLG_OFF_N,
            NLZC300001PMsg avaInv = getAvalInvtyFromCusaAndCvi(vendorInfo.splyItemNum.getValue(), reqQty, ZYPConstant.FLG_OFF_N, 
                    searchCusaWs, searchCusaParts, ZYPConstant.FLG_OFF_N, trxReqQty);
            // 2023/09/08 QC#61704 END
            // 2023/09/04 QC#61703 END

            // NLZC300001PMsg avaInv =
            // getAvalInvtyFromCusaAndCvi(mdseCd, reqQty,
            // ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_ON_Y,
            // ZYPConstant.FLG_ON_Y, ZYPConstant.FLG_OFF_N);

            // QC#18418 T.Hakodate ADD END
            List<String> errList = S21ApiUtil.getXxMsgIdList(avaInv);
            if (errList.size() > 0) {
                for (String xxMsgId : errList) {
                    S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));

                    ErrorMessageBean errMsgBean = new ErrorMessageBean();
                    errMsgBean.setPrchReqNum(prInfo.getHeaderInfo().prchReqNum.getValue());
                    errMsgBean.setPrchReqLineNum(prInfo.getDetailInfo().prchReqLineNum.getValue());
                    errMsgBean.setMdseCd(mdseCd);
                    errMsgBean.setPrchReqQty(prInfo.getDetailInfo().prchReqQty.getValue());
                    errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage(xxMsgId));
                    errMsgBeanList.add(errMsgBean);

                    if (xxMsgId.endsWith("E")) {
                        // QC#22405
                        if (xxMsgId.equals("NLZM2057E")) {
                            isWSErr = true;
                        }

                        // QC#18418 T.Hakodate ADD START
                        // The entered Merchandise Code does not exist
                        // in Master.
                        if (xxMsgId.equals("NLZM2077E")) {
                            doesNotExistInItemMaster.add(mdseCd);
                        }
                        // QC#18418 T.Hakodate ADD END
                        return null;
                    }
                }
            }

            // QC#52575 Start
            if (avaInv.xxDetailList.getValidCount() == 0) {
                return null;
            }

            boolean isCusainvQty = false;
            for (int i = 0; i < avaInv.xxDetailList.getValidCount(); i++) {
                String invFlg = avaInv.xxDetailList.no(i).xxAvalOrdFlg.getValue();
                if (ZYPConstant.FLG_ON_Y.equals(invFlg)) {
                    isCusainvQty = true;
                    break;
                }
            }

            if (!isCusainvQty) {
                return null;
            }

            ZYPEZDItemValueSetter.setValue(prInfo.getHeaderInfo().prchReqApvlStsCd, PRCH_REQ_APVL_STS.APPROVED);
            // QC#52575 End
            // QC#52575/QC#52576
            reqQty = createPoSub(insrcAvalInvPrForVndPMsg, insrcPrToCratPrAvalInvPMsg, prInfo, ruleDtl, mdseCd, reqQty, vendorInfo, includeHazmat, false);

            if (reqQty != null) {
                if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.origLineFrzFlg.getValue())) {
                    // QC#27660 Update. Mod QC#55514
                    freeze(prInfo, ruleDtl);
                }

                if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.boNtfyReqFlg.getValue())) {
                    // START 2023/06/02 S.Dong [QC#55629, MOD]
                    //sendMail(prInfo, reqQty, null, null, null);
                    sendMail(prInfo, reqQty, null, null, null, null);
                    // END 2023/06/02 S.Dong [QC#55629, MOD]
                }
            }

            return reqQty;

        } else {
            // ASL dose not exists.
            return null;
        }
    }

    /**
     * from CVI (Insourced PO)
     * @param prInfo
     * @param ruleDtl
     * @param mdseCd
     * @param reqQty
     * @return
     */
    private BigDecimal srchStkProcDtlForCvi(PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String mdseCd, BigDecimal reqQty, boolean includeHazmat) {

        S21InfoLogOutput.println(String.format("[NPAB1360] Exec search process(4). PR#%s-%s, Original MDSE#%s, Search MDSE#%s", prInfo.getHeaderInfo().prchReqNum.getValue(), prInfo.getDetailInfo().prchReqLineNum.getValue(), prInfo
                .getDetailInfo().mdseCd.getValue(), mdseCd));
        // START 2023/06/02 S.Dong [QC#55629, ADD]
        // adjust qty, get vndor minimum order qty
        NPZC136001PMsg pMsg = getMinVndQty(prInfo, mdseCd, reqQty);
        if (pMsg != null && reqQty.compareTo(pMsg.prchReqQty.getValue()) < 0) {
            reqQty = pMsg.prchReqQty.getValue();
        }
        // END 2023/06/02 S.Dong [QC#55629, ADD]
        // Mod. QC#29026
        NPZC113001PMsg vendorInfo = getPrimaryVendorInfo(prInfo, mdseCd, false, true);
        if (vendorInfo != null) {

            String vendorSystemType = getVendorSystemType(vendorInfo.vndCd.getValue());

            if ((ZYPCommonFunc.hasValue(vendorSystemType)) && (VND_SYS_TP.WHOLE_SALES.equals(vendorSystemType) || VND_SYS_TP.PARTS.equals(vendorSystemType))) {

                // QC#18418 T.Hakodate ADD END
                NLZC300001PMsg avaInv = getAvalInvtyFromCusaAndCvi(mdseCd, reqQty, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_ON_Y, null);

                List<String> errList = S21ApiUtil.getXxMsgIdList(avaInv);
                if (errList.size() > 0) {
                    for (String xxMsgId : errList) {
                        S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));

                        ErrorMessageBean errMsgBean = new ErrorMessageBean();
                        errMsgBean.setPrchReqNum(prInfo.getHeaderInfo().prchReqNum.getValue());
                        errMsgBean.setPrchReqLineNum(prInfo.getDetailInfo().prchReqLineNum.getValue());
                        errMsgBean.setMdseCd(mdseCd);
                        errMsgBean.setPrchReqQty(prInfo.getDetailInfo().prchReqQty.getValue());
                        errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage(xxMsgId));
                        errMsgBeanList.add(errMsgBean);

                        if (xxMsgId.endsWith("E")) {
                            // QC#22405
                            if (xxMsgId.equals("NLZM2057E")) {
                                isWSErr = true;
                            }

                            // QC#18418 T.Hakodate ADD START
                            // The entered Merchandise Code does not
                            // exist
                            // in Master.
                            if (xxMsgId.equals("NLZM2077E")) {
                                doesNotExistInItemMaster.add(mdseCd);
                            }
                            // QC#18418 T.Hakodate ADD END
                            return null;
                        }
                    }
                }

                if ((avaInv.xxDetailList.getValidCount() == 0) || (avaInv.xxDetailList.no(0).xxAvalQty.getValue().compareTo(BigDecimal.ZERO) <= 0)) {
                    return null;
                }

                BigDecimal prchReqQty = avaInv.xxDetailList.no(0).xxAvalQty.getValue();

                if (prchReqQty.compareTo(reqQty) >= 0) {
                    prchReqQty = reqQty;
                }

                // QC#52575
                ZYPEZDItemValueSetter.setValue(prInfo.getHeaderInfo().prchReqApvlStsCd, PRCH_REQ_APVL_STS.APPROVED);
                // QC#52575/QC#52576
                reqQty = createPoSub(insrcAvalInvPrForVndPMsg, insrcPrToCratPrAvalInvPMsg, prInfo, ruleDtl, mdseCd, prchReqQty, vendorInfo, includeHazmat, false);

                if (reqQty != null) {
                    if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.origLineFrzFlg.getValue())) {
                        // QC#27660 Update. Mod QC#55514
                        freeze(prInfo, ruleDtl);
                    }

                    if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.boNtfyReqFlg.getValue())) {
                        // START 2023/06/02 S.Dong [QC#55629, MOD]
                        //sendMail(prInfo, reqQty, null, null, null);
                        sendMail(prInfo, reqQty, null, null, null, null);
                        // END 2023/06/02 S.Dong [QC#55629, MOD]
                    }
                }

                return reqQty;

            } else {

                // Primary vendor is not CUSA WS.
                return null;
            }

        } else {
            // ASL dose not exists.
            return null;
        }
    }

    /**
     * from Wh Owner. QC#57659 Mod
     * @param prInfo
     * @param ruleDtl
     * @param mdseCd
     * @param reqQty
     * @return
     */
    private BigDecimal srchStkProcDtlForWhOwner(PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String mdseCd, BigDecimal reqQty, boolean includeHazmat) {

        S21InfoLogOutput.println(String.format("[NPAB1360] Exec search process(5). PR#%s-%s, Original MDSE#%s, Search MDSE#%s", prInfo.getHeaderInfo().prchReqNum.getValue(), prInfo.getDetailInfo().prchReqLineNum.getValue(), prInfo
                .getDetailInfo().mdseCd.getValue(), mdseCd));

        // QC#57659 Mod start
        // BigDecimal avaQty = getAvalChoiceInvty(mdseCd,
        // ruleDtl.srchWhOwnrCd.getValue());
        BigDecimal avaQty = getAvalWhOwnerInvty(mdseCd, ruleDtl.srchWhOwnrCd.getValue());
        // QC#57659 Mod end

        if ((avaQty == null) || (avaQty.compareTo(BigDecimal.ZERO) <= 0)) {
            return null;
        }

        BigDecimal prchReqQty = avaQty;

        if (avaQty.compareTo(reqQty) >= 0) {
            prchReqQty = reqQty;
        }

        // QC#57659 Add start
        if (WH_OWNR.CHOICE.equals(ruleDtl.srchWhOwnrCd.getValue()) && PRCH_REQ_TP.PREMIUM_RUSH.equals(prInfo.getHeaderInfo().prchReqTpCd.getValue())) {

            if (BigDecimal.ZERO.compareTo(prInfo.getDetailInfo().prchReqQty.getValue()) < 0) {
                prInfo.getDetailInfo().prchReqQty.setValue(BigDecimal.ZERO);
            }

            prInfo.getDetailInfo().prchReqQty.setValue(prInfo.getDetailInfo().prchReqQty.getValue().add(prchReqQty));
            String key = prInfo.getHeaderInfo().prchReqNum.getValue() + prInfo.getDetailInfo().prchReqLineNum.getValue();
            choicePrInfoMapList.put(key, prInfo);

            // update rel qty
            PRCH_REQ_DTLTMsg prdTMsg = new PRCH_REQ_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(prdTMsg.glblCmpyCd, this.globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(prdTMsg.prchReqNum, prInfo.getHeaderInfo().prchReqNum);
            ZYPEZDItemValueSetter.setValue(prdTMsg.prchReqLineNum, prInfo.getDetailInfo().prchReqLineNum);
            prdTMsg = getPrntPrchReqDtlTMsg(prdTMsg);

            if (prdTMsg != null) {

                ZYPEZDItemValueSetter.setValue(prdTMsg.prchReqRelQty, prchReqQty);
                ZYPEZDItemValueSetter.setValue(prdTMsg.prchReqBalQty, prdTMsg.prchReqQty.getValue().subtract(prdTMsg.prchReqRelQty.getValue()));

                if (BigDecimal.ZERO.compareTo(prdTMsg.prchReqBalQty.getValue()) <= 0) {
                    ZYPEZDItemValueSetter.setValue(prdTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.COMPLEATED);
                }

                EZDTBLAccessor.update(prdTMsg);

                if (prdTMsg != null && !EZDTBLAccessor.RTNCD_NORMAL.equals(prdTMsg.getReturnCode())) {
                    S21InfoLogOutput.println("ERR_CD : " + prdTMsg.getReturnCode());
                    S21InfoLogOutput.println(prdTMsg.toString());
                    throw new S21AbendException("NPAM1172E", new String[] {"PRCH_REQ_DTL" });
                }
            }

            return prchReqQty;
        }
        // QC#57659 Add end

        createNPZC103001PMsgCreate(insrcPrForChoicePMsg, prInfo, ruleDtl, ruleDtl.prchReqLineTpCd.getValue(), ruleDtl.whOwnrDummyInvtyLocCd.getValue(), mdseCd, prchReqQty, includeHazmat);

        if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.origLineFrzFlg.getValue())) {
            // QC#27660 Update. Mod QC#55514
            freeze(prInfo, ruleDtl);
        }

        if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.boNtfyReqFlg.getValue())) {
            // START 2023/06/02 S.Dong [QC#55629, MOD]
            //sendMail(prInfo, reqQty, null, null, null);
            sendMail(prInfo, reqQty, null, null, null, null);
            // END 2023/06/02 S.Dong [QC#55629, MOD]
        }

        return prchReqQty;
    }

    /**
     * from Specified WH
     * @param prInfo
     * @param ruleDtl
     * @param mdseCd
     * @param reqQty
     * @return
     */
    private BigDecimal srchStkProcDtlForSpecWh(PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String mdseCd, BigDecimal reqQty, boolean includeHazmat) {

        S21InfoLogOutput.println(String.format("[NPAB1360] Exec search process(6). PR#%s-%s, Original MDSE#%s, Search MDSE#%s", prInfo.getHeaderInfo().prchReqNum.getValue(), prInfo.getDetailInfo().prchReqLineNum.getValue(), prInfo
                .getDetailInfo().mdseCd.getValue(), mdseCd));

        // 2023/09/04 QC#61703 START
        if (!PRCH_REQ_TP.PREMIUM_RUSH.equals(prInfo.getHeaderInfo().prchReqTpCd.getValue()) //
                && ruleDtl.srchSpecInvtyLocCd.getValue().equals(prInfo.getDetailInfo().srcInvtyLocCd.getValue())) {
            return null;
        }
        // 2023/09/04 QC#61703 END

        BigDecimal avaQty = getAvalWhInvty(mdseCd, ruleDtl.srchSpecInvtyLocCd.getValue());

        if (avaQty == null) {
            return null;
        }

        BigDecimal prchReqQty = avaQty;

        if (avaQty.compareTo(reqQty) >= 0) {
            prchReqQty = reqQty;
        }

        // QC#57659 Add start
        RTL_WHTMsg tMsg = getRtlWhTMsg(ruleDtl.srchSpecInvtyLocCd.getValue());
        if (tMsg != null && WH_OWNR.DBS.equals(tMsg.whOwnrCd.getValue()) && PRCH_REQ_TP.PREMIUM_RUSH.equals(prInfo.getHeaderInfo().prchReqTpCd.getValue())) {
            if (BigDecimal.ZERO.compareTo(prInfo.getDetailInfo().prchReqQty.getValue()) < 0) {
                prInfo.getDetailInfo().prchReqQty.setValue(BigDecimal.ZERO);
            }
            prInfo.getDetailInfo().prchReqQty.setValue(prInfo.getDetailInfo().prchReqQty.getValue().add(prchReqQty));
            prInfo.getDetailInfo().srcInvtyLocCd.setValue(ruleDtl.srchSpecInvtyLocCd.getValue());
            String key = prInfo.getHeaderInfo().prchReqNum.getValue() + prInfo.getDetailInfo().prchReqLineNum.getValue();
            dbsPrInfoMapList.put(key, prInfo);
        }
        // QC#57659 Add end

        createNPZC103001PMsgCreate(insrcPrForSpecWhPMsg, prInfo, ruleDtl, ruleDtl.prchReqLineTpCd.getValue(), ruleDtl.srchSpecInvtyLocCd.getValue(), mdseCd, prchReqQty, includeHazmat);

        if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.origLineFrzFlg.getValue())) {
            // QC#27660 Update. Mod QC#55514
            freeze(prInfo, ruleDtl);
        }

        if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.boNtfyReqFlg.getValue())) {
            // START 2023/06/02 S.Dong [QC#55629, MOD]
            //sendMail(prInfo, reqQty, null, null, null);
            sendMail(prInfo, reqQty, null, null, null, null);
            // END 2023/06/02 S.Dong [QC#55629, MOD]
        }

        return prchReqQty;
    }

    /**
     * Create PO to Vendor
     * @param prInfo
     * @param ruleDtl
     * @param mdseCd
     * @param reqQty
     * @return
     */
    // START 2023/06/02 S.Dong [QC#55629, MOD]
    //private BigDecimal createPo(PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String mdseCd, BigDecimal reqQty, boolean includeHazmat) {
    private BigDecimal createPo(PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String mdseCd, BigDecimal reqQty, boolean includeHazmat, BigDecimal allocCount) {
    // END 2023/06/02 S.Dong [QC#55629, MOD]    
        S21InfoLogOutput.println(String.format("[NPAB1360] Exec search process(Create PO). PR#%s-%s, Original MDSE#%s, Search MDSE#%s", prInfo.getHeaderInfo().prchReqNum.getValue(), prInfo.getDetailInfo().prchReqLineNum.getValue(), prInfo
                .getDetailInfo().mdseCd.getValue(), mdseCd));
        // START 2023/06/02 S.Dong [QC#55629, ADD]
        // adjust qty, get vndor minimum order qty
        NPZC136001PMsg pMsg = getMinVndQty(prInfo, mdseCd, reqQty);
        if (pMsg != null && reqQty.compareTo(pMsg.prchReqQty.getValue()) < 0) {
            reqQty = pMsg.prchReqQty.getValue();
        }
        // END 2023/06/02 S.Dong [QC#55629, ADD]
        // QC#18418 T.Hakodate ADD START
        // Mod. QC#29026
        NPZC113001PMsg vendorInfo = getPrimaryVendorInfo(prInfo, mdseCd, true, false);
        // QC#18418 T.Hakodate ADD END

        if (vendorInfo != null) {
            // Mod. QC#29026
            // QC#52575/QC#52576
            reqQty = createPoSub(insrcPrForVndPMsg, insrcPrToCratPrPMsg, prInfo, ruleDtl, vendorInfo.mdseCd.getValue(), reqQty, vendorInfo, includeHazmat, true);

            if (reqQty != null) {
                if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.origLineFrzFlg.getValue())) {
                    // QC#27660 Update. Mod QC#55514
                    freeze(prInfo, ruleDtl);
                }

                if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.boNtfyReqFlg.getValue())) {
                    String prchReqNum = insrcPrToCratPrPMsg.prchReqNum.getValue();
                    String prchReqLineNum = insrcPrToCratPrPMsg.prchReqInfo.no(insrcPrToCratPrPMsg.prchReqInfo.getValidCount() - 1).prchReqLineNum.getValue();
                    String prchReqApvlStsCd = insrcPrToCratPrPMsg.prchReqApvlStsCd.getValue();
                    // START 2023/06/02 S.Dong [QC#55629, ADD]
                    BigDecimal origNum = BigDecimal.ZERO;
                    if (prInfo.getDetailInfo().prchReqQty.getValue().compareTo(pMsg.prchReqQty.getValue()) > 0) {
                        origNum = allocCount.add(reqQty);
                    } else {
                        origNum = pMsg.prchReqQty.getValue();
                    }
                    // END 2023/06/02 S.Dong [QC#55629, ADD]
                    // START 2023/06/02 S.Dong [QC#55629, MOD]
                    // sendMail(prInfo, reqQty, prchReqNum, prchReqLineNum, prchReqApvlStsCd);
                    sendMail(prInfo, reqQty, origNum, prchReqNum, prchReqLineNum, prchReqApvlStsCd);
                    // END 2023/06/02 S.Dong [QC#55629, MOD]
                }
                // QC#57659 Add start
                if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.boNtfyReqFlg.getValue()) && PRCH_REQ_TP.RUSH.equals(prInfo.getHeaderInfo().prchReqTpCd.getValue())) {
                    rushKickOut(prInfo, reqQty);
                }
                // QC#57659 Add end
            }
            // QC#57659 Add start. QC#58504 Mod
            if (PRCH_REQ_TP.PREMIUM_RUSH.equals(prInfo.getHeaderInfo().prchReqTpCd.getValue()) && PROCR_TP.WAREHOUSE.equals(prInfo.getDetailInfo().procrTpCd.getValue())) {
                return null;
            }
            // QC#57659 Add end
            return reqQty;

        } else {

            return null;

        }
    }

    // QC#52575/QC#52576 Mod
    private BigDecimal createPoSub(NPZC103001PMsg vndPMsg, NPZC103001PMsg cratPrPMsg, PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String mdseCd, BigDecimal reqQty, NPZC113001PMsg vendor, boolean includeHazmat, boolean isCreatePo) {

        // QC# 18418 T.hakodate MOD START
        // Get Vendor
        // NPZC113001PMsg vendor = getPrimaryVendor(mdseCd);
        // if (0 < vendor.xxMsgIdList.getValidCount()) {
        // rollback();
        // errorProcess(prInfo,
        // vendor.xxMsgIdList.no(0).xxMsgId.getValue());
        // return null;
        // } else if
        // (ZYPConstant.FLG_ON_Y.equals(vendor.xxErrFlg.getValue())) {
        // rollback();
        // errorProcess(prInfo, NPAB136001Constant.NPZM0272E);
        // return null;
        // }
        // QC# 18418 T.hakodate MOD END

        String currencyCode = null;
        NPXC001001CurrencyConversionBean currency = null;
        NPZC129001PMsg vendorQty = null;

        if (!ZYPConstant.FLG_ON_Y.equals(vendor.xxErrFlg.getValue())) {
            // Get Vendor Currency code
            currencyCode = getVndCcyCd(vendor.vndCd.getValue());

            // Get Vendor Currency
            NPXC001001CurrencyConversion cov = new NPXC001001CurrencyConversion();
            currency = cov.convertCurrency(globalCompanyCode, currencyCode, vendor.unitPrcAmt.getValue(), salesDate, null);

            // Get Vendor Qty
            vendorQty = getVendorPoQty(vendor.vndCd.getValue(), mdseCd, reqQty);
            if (0 < vendorQty.xxMsgIdList.getValidCount()) {
                rollback();
                // Mod. QC#29026
                errorProcess(prInfo, vendorQty.xxMsgIdList.no(0).xxMsgId.getValue(), prInfo.getDetailInfo().mdseCd.getValue());
                return null;
            }
        }

        // Create PO Child Line
        // QC#52575/QC#52576 Start
        createNPZC103001PMsgForCreateForPr(vndPMsg, prInfo, ruleDtl, vendor, mdseCd, reqQty, includeHazmat, isCreatePo);

        if (PRCH_REQ_REC_TP.TECH_REQUEST.equals(prInfo.getHeaderInfo().prchReqRecTpCd.getValue())) {
            createNPZC103001PMsgForCratVndPr(cratPrPMsg, prInfo, ruleDtl, vendor, vendorQty, currency, currencyCode, mdseCd, reqQty, includeHazmat);
        }
        // QC#52575/QC#52576 End

        return reqQty;
    }

    // 08/29/2017 CITS S.Endo Add Sol#406(QC#19243) START
    private BigDecimal createBoWhTransfer(PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String mdseCd, BigDecimal reqQty, boolean includeHazmat) {
        String prchReqLineTpCd = ruleDtl.prchReqLineTpCd.getValue();
        if (!ZYPCommonFunc.hasValue(prchReqLineTpCd)) {
            prchReqLineTpCd = PRCH_REQ_LINE_TP.INSOURCED_DB;
        }
        createNPZC103001PMsgCreate(insrcPrToCratBOWhTransferPMsg, prInfo, ruleDtl, prchReqLineTpCd, ruleDtl.boSrcRtlWhCd.getValue().concat(ruleDtl.boSrcRtlSwhCd.getValue()), mdseCd, reqQty, includeHazmat);
        ZYPEZDItemValueSetter.setValue(prInfo.getDetailInfo().srcInvtyLocCd, ruleDtl.boSrcRtlWhCd.getValue().concat(ruleDtl.boSrcRtlSwhCd.getValue()));
        // QC#27660 Update.
        boDetailCmnt = ruleDtl.frzRefCmntTxt.getValue();
        // QC#55514

        // 2023/09/04 QC#61703 START
        freeze(prInfo, true, ruleDtl, reqQty, includeHazmat);
        // 2023/09/04 QC#61703 END

        if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.boNtfyReqFlg.getValue())) {
            String prchReqNum = insrcPrToCratBOWhTransferPMsg.prchReqNum.getValue();
            String prchReqLineNum = insrcPrToCratBOWhTransferPMsg.prchReqInfo.no(insrcPrToCratBOWhTransferPMsg.prchReqInfo.getValidCount() - 1).prchReqLineNum.getValue();
            String prchReqApvlStsCd = insrcPrToCratBOWhTransferPMsg.prchReqApvlStsCd.getValue();
            // START 2023/06/02 S.Dong [QC#55629, MOD]
            //sendMail(prInfo, reqQty, prchReqNum, prchReqLineNum, prchReqApvlStsCd);
            sendMail(prInfo, reqQty, null, prchReqNum, prchReqLineNum, prchReqApvlStsCd);
            // END 2023/06/02 S.Dong [QC#55629, MOD]
        }
        return reqQty;
    }

    // 08/29/2017 CITS S.Endo Add Sol#406(QC#19243) END
    /**
     * call Primary Vendor from ASL API
     * @param mdseCd
     * @return
     */
    private NPZC113001PMsg getPrimaryVendor(String mdseCd) {

        NPZC113001 api = new NPZC113001();
        NPZC113001PMsg param = new NPZC113001PMsg();

        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(param.slsDt, salesDate);
        ZYPEZDItemValueSetter.setValue(param.mdseCd, mdseCd);

        api.execute(param, ONBATCH_TYPE.BATCH);

        return param;
    }

    /**
     * call Change Vendor UOM API
     * @param vndCd
     * @param mdseCd
     * @param reqQty
     * @return
     */
    private NPZC129001PMsg getVendorPoQty(String vndCd, String mdseCd, BigDecimal reqQty) {

        NPZC129001 api = new NPZC129001();
        NPZC129001PMsg param = new NPZC129001PMsg();

        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(param.slsDt, salesDate);
        ZYPEZDItemValueSetter.setValue(param.vndCd, vndCd);
        ZYPEZDItemValueSetter.setValue(param.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(param.poDispQty, reqQty);
        ZYPEZDItemValueSetter.setValue(param.adjQtyIncrFlg, ZYPConstant.FLG_ON_Y);

        api.execute(param, ONBATCH_TYPE.BATCH);

        return param;
    }

    /**
     * get Vendor Currency Code from Vendor Master
     * @param vndCd
     * @return
     */
    private String getVndCcyCd(String vndCd) {

        if (vendorCurrencyCodeMap.containsKey(vndCd)) {
            return vendorCurrencyCodeMap.get(vndCd);
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String ret = null;

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB136001Constant.DB_PARAM_VND_CD, vndCd);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("getVndCcyCd", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ret = resultSet.getString(NPAB136001Constant.DEAL_CCY_CD);
                vendorCurrencyCodeMap.put(vndCd, ret);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return ret;
    }

    /**
     * call Inventory Reference API (get Available Inventory from CUSA
     * and CVI)
     * @param mdseCd
     * @param reqQty
     * @param flg1 get CSA Inventory Avilability
     * @param flg2 get CUSA WS Inventory Avilability
     * @param flg3 get CUSA Parts Inventory Avilability
     * @param flg4 get CVI Inventory Avilability
     * @return
     */
    private NLZC300001PMsg getAvalInvtyFromCusaAndCvi(String mdseCd, BigDecimal reqQty, 
            String flg1, String flg2, String flg3, String flg4, BigDecimal trxReqQty) {

        NLZC300001 api = new NLZC300001();
        NLZC300001PMsg param = new NLZC300001PMsg();

        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(param.procDt, salesDate);
        ZYPEZDItemValueSetter.setValue(param.xxRqstFlg_01, flg1);
        ZYPEZDItemValueSetter.setValue(param.xxRqstFlg_02, flg2);
        ZYPEZDItemValueSetter.setValue(param.xxRqstFlg_03, flg3);
        ZYPEZDItemValueSetter.setValue(param.xxRqstFlg_04, flg4);
        // QC#22405
        ZYPEZDItemValueSetter.setValue(param.xxRsltFlg_WS, ZYPConstant.FLG_ON_Y);
        // detail
        ZYPEZDItemValueSetter.setValue(param.xxDetailList.no(0).mdseCd, mdseCd);
        // 2023/09/04 QC#61703 START
        if (ZYPCommonFunc.hasValue(trxReqQty)) {
            reqQty = reqQty.add(trxReqQty);
        }
        // 2023/09/04 QC#61703 END
        ZYPEZDItemValueSetter.setValue(param.xxDetailList.no(0).ordQty, reqQty);

        param.xxDetailList.setValidCount(1);

        api.execute(param, ONBATCH_TYPE.BATCH);

        return param;
    }

    private ArrayList<String> getSupersedeItem(String mdseCd) {

        ArrayList<String> ret = null;

        if (supersedeItemMap.containsKey(mdseCd)) {
            ret = supersedeItemMap.get(mdseCd);

        } else {
            NWZC206001 api = new NWZC206001();
            NWZC206001PMsg param = new NWZC206001PMsg();

            ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(param.slsDt, salesDate);
            ZYPEZDItemValueSetter.setValue(param.mdseCd, mdseCd);
            ZYPEZDItemValueSetter.setValue(param.xxModeCd, "1");
            ZYPEZDItemValueSetter.setValue(param.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(param.xxAvalPrchFlg, ZYPConstant.FLG_ON_Y);

            api.execute(param, ONBATCH_TYPE.BATCH);

            List<String> errList = S21ApiUtil.getXxMsgIdList(param);
            if (errList.size() > 0) {
                for (String xxMsgId : errList) {
                    S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));
                    if (xxMsgId.endsWith("E")) {
                        return null;
                    }
                }
            }
            ret = new ArrayList<String>();

            for (int i = 0; i < param.A.getValidCount(); i++) {
                if (!mdseCd.equals(param.A.no(i).mdseCd.getValue())) {
                    ret.add(param.A.no(i).mdseCd.getValue());
                }
            }

            supersedeItemMap.put(mdseCd, ret);
        }

        return ret;
    }

    private ArrayList<String> getMdseCd(String relnMdseCd, String srchMdseItemRelnTpCd) {

        ArrayList<String> ret = null;

        if (newItemMap.containsKey(relnMdseCd)) {
            if (newItemMap.get(relnMdseCd).containsKey(srchMdseItemRelnTpCd)) {
                ret = newItemMap.get(relnMdseCd).get(srchMdseItemRelnTpCd);
            }
        }

        if (ret == null) {
            ret = new ArrayList<String>();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
                HashMap<String, Object> paramMap = new HashMap<String, Object>();

                paramMap.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
                paramMap.put(NPAB136001Constant.DB_PARAM_RELN_MDSE_CD, relnMdseCd);
                paramMap.put(NPAB136001Constant.DB_PARAM_MDSE_ITEM_RELN_TP_CD, srchMdseItemRelnTpCd);

                S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

                execParam.setFetchSize(FETCH_SIZE);
                execParam.setMaxRows(0);
                preparedStatement = ssmLlcClient.createPreparedStatement("getMdseCd", paramMap, execParam);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    ret.add(resultSet.getString(NPAB136001Constant.MDSE_CD));
                }

                if (newItemMap.containsKey(relnMdseCd)) {
                    newItemMap.get(relnMdseCd).put(srchMdseItemRelnTpCd, ret);

                } else {
                    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
                    map.put(srchMdseItemRelnTpCd, ret);
                    newItemMap.put(relnMdseCd, map);
                }

            } catch (SQLException e) {
                sqlExceptionHandler(e);

            } finally {
                S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
            }
        }
        return ret;
    }

    private ArrayList<String> getRelnMdseCd(String mdseCd, String srchMdseItemRelnTpCd) {

        ArrayList<String> ret = null;

        if (refurbishItemMap.containsKey(mdseCd)) {
            if (refurbishItemMap.get(mdseCd).containsKey(srchMdseItemRelnTpCd)) {
                ret = refurbishItemMap.get(mdseCd).get(srchMdseItemRelnTpCd);
            }
        }

        if (ret == null) {
            ret = new ArrayList<String>();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
                HashMap<String, Object> paramMap = new HashMap<String, Object>();

                paramMap.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
                paramMap.put(NPAB136001Constant.DB_PARAM_MDSE_CD, mdseCd);
                paramMap.put(NPAB136001Constant.DB_PARAM_MDSE_ITEM_RELN_TP_CD, srchMdseItemRelnTpCd);

                S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
                execParam.setFetchSize(FETCH_SIZE);
                execParam.setMaxRows(0);
                preparedStatement = ssmLlcClient.createPreparedStatement("getRelnMdseCd", paramMap, execParam);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    ret.add(resultSet.getString(NPAB136001Constant.RELN_MDSE_CD));
                }

                if (refurbishItemMap.containsKey(mdseCd)) {
                    refurbishItemMap.get(mdseCd).put(srchMdseItemRelnTpCd, ret);

                } else {
                    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
                    map.put(srchMdseItemRelnTpCd, ret);
                    refurbishItemMap.put(mdseCd, map);
                }

            } catch (SQLException e) {
                sqlExceptionHandler(e);

            } finally {
                S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
            }
        }
        return ret;
    }

    // Mod. QC#29026
    private void errorProcess(PrInfoBean prInfo, String errMsgCd, String mdseCd) {

        NPZC103001PMsg pMsg = new NPZC103001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_UPDATE);
        ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_ORDER_RELEASE);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, prInfo.getHeaderInfo().prchReqNum);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqStsCd, ZYPCodeDataUtil.getVarCharConstValue(NPAB136001Constant.PRCH_REQ_INSRC_ERR_STS, globalCompanyCode));

        // Detail
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqLineNum, prInfo.getDetailInfo().prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqLineSubNum, prInfo.getDetailInfo().prchReqLineSubNum);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqLineStsCd, ZYPCodeDataUtil.getVarCharConstValue(NPAB136001Constant.PRCH_REQ_LINE_INSRC_ERR_STS, globalCompanyCode));

        // QC#18418 T.Hakodate ADD START
        // ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqRelQty,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqRelQty, prInfo.getDetailInfo().prchReqRelQty);
        // QC#18418 T.Hakodate ADD END
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqRelStsCd, PRCH_REQ_REL_STS.ERROR);
        String msgTxt = S21MessageFunc.clspGetMessage(errMsgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqRelErrMsgTxt, msgTxt);

        pMsg.prchReqInfo.setValidCount(1);

        executeNPZC1030(pMsg);
        List<String> errList;
        errList = S21ApiUtil.getXxMsgIdList(pMsg);

        if (errList.size() > 0) {
            for (String xxMsgId : errList) {
                S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));

                if (xxMsgId.endsWith("E")) {
                    rollback();
                }
            }

        }
        commit();
        errorCount++;

        // set error transaction key info in error bean
        ErrorMessageBean errMsgBean = new ErrorMessageBean();
        errMsgBean.setPrchReqNum(prInfo.getHeaderInfo().prchReqNum.getValue());
        errMsgBean.setPrchReqLineNum(prInfo.getDetailInfo().prchReqLineNum.getValue());
        // Mod. QC#29026
        errMsgBean.setMdseCd(mdseCd);
        errMsgBean.setPrchReqQty(prInfo.getDetailInfo().prchReqQty.getValue());
        errMsgBean.setErrMsg(msgTxt);

        errMsgBeanList.add(errMsgBean);
    }

    // 08/31/2017 CITS S.Endo Add Sol#406(QC#19243) START
    // Mod QC#55514
    private void freeze(PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl) {
        // 2023/09/04 QC#61703 START
        freeze(prInfo, false, ruleDtl, null, false);
        // 2023/09/04 QC#61703 END
    }

    // 08/31/2017 CITS S.Endo Add Sol#406(QC#19243) END

    // 08/31/2017 CITS S.Endo Mod Sol#406(QC#19243) START
    // private void freeze(PrInfoBean prInfo) {
    // QC#27660 Update method.
    // Mod QC#55514
    private void freeze(PrInfoBean prInfo, boolean boFlag, INSRC_RULE_DTLTMsg ruleDtl, BigDecimal reqQty, boolean includeHazmat) {
        // 08/31/2017 CITS S.Endo Mod Sol#406(QC#19243) END
        NPZC103001PMsg pMsg = new NPZC103001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_UPDATE);
        ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_HOLD);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, prInfo.getHeaderInfo().prchReqNum);

        // detail
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqLineNum, prInfo.getDetailInfo().prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqLineSubNum, prInfo.getDetailInfo().prchReqLineSubNum);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqFrzFlg, ZYPConstant.FLG_ON_Y);
        // QC#27660 Update.
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqLineCmntTxt, addFirstLineCmnt(ruleDtl.frzRefCmntTxt.getValue(), prInfo.getDetailInfo().prchReqLineCmntTxt.getValue()));
        // START 2017/10/30 S.Katsuma QC#18512 ADD
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqRelErrMsgTxt, "");
        // END 2017/10/30 S.Katsuma QC#18512 ADD

        // 08/31/2017 CITS S.Endo Add Sol#406(QC#19243) START
        if (boFlag) {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).srcInvtyLocCd, prInfo.getDetailInfo().srcInvtyLocCd);
        }
        // 08/31/2017 CITS S.Endo Add Sol#406(QC#19243) END

        // QC#55514
        String mdseCd = prInfo.getDetailInfo().mdseCd.getValue();

        if (boFlag && this.supersedeItemMap.containsKey(mdseCd) && this.supersedeItemMap.get(mdseCd).size() > 0) {

            String updMdsecd = this.supersedeItemMap.get(mdseCd).get(this.supersedeItemMap.get(mdseCd).size() - 1);
            MDSETMsg updMDSETMsg = new MDSETMsg();
            ZYPEZDItemValueSetter.setValue(updMDSETMsg.glblCmpyCd, this.globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(updMDSETMsg.mdseCd, updMdsecd);

            updMDSETMsg = (MDSETMsg) EZDTBLAccessor.findByKey(updMDSETMsg);

            if (updMDSETMsg != null) {

                String mdseItemClstpCd = updMDSETMsg.mdseItemClsTpCd.getValue();
                // START 2023/09/12 G.Quan [QC#61589, ADD] 
                boolean isMdseItemClsTpFlg = false;

                DS_COND_CONSTTMsg getDS_COND_CONSTTMsg = new DS_COND_CONSTTMsg();
                ZYPEZDItemValueSetter.setValue(getDS_COND_CONSTTMsg.glblCmpyCd, this.globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(getDS_COND_CONSTTMsg.dsCondConstGrpId, NPAB136001Constant.NPAB1360_ITEM_CLS);
                ZYPEZDItemValueSetter.setValue(getDS_COND_CONSTTMsg.dsCondConstCd, ruleDtl.insrcRuleDtlNum.getValue());
                getDS_COND_CONSTTMsg = (DS_COND_CONSTTMsg) EZDTBLAccessor.findByKey(getDS_COND_CONSTTMsg);

                if (getDS_COND_CONSTTMsg != null && mdseItemClstpCd.equals(getDS_COND_CONSTTMsg.dsCondConstValTxt_01.getValue())) {
                    isMdseItemClsTpFlg = true;
                }
                // END 2023/09/12 G.Quan [QC#61589, ADD] 
                if (ZYPCommonFunc.hasValue(mdseItemClstpCd) //
                        && ZYPCommonFunc.hasValue(updMdsecd) //
                        // START 2023/09/07 G.Quan [QC#61589, MOD] 
                        //&& MDSE_ITEM_CLS_TP.OCEOEM.equals(mdseItemClstpCd)) {
                        && (MDSE_ITEM_CLS_TP.OCEOEM.equals(mdseItemClstpCd) || isMdseItemClsTpFlg)) {
                        // END 2023/09/07 G.Quan [QC#61589, MOD] 

                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).mdseCd, updMdsecd);
                    ZYPEZDItemValueSetter.setValue(prInfo.getDetailInfo().mdseCd, updMdsecd);

                } else if (ZYPCommonFunc.hasValue(mdseItemClstpCd) //
                        && ZYPCommonFunc.hasValue(updMdsecd) //
                        // START 2023/09/07 G.Quan [QC#61589, MOD] 
                        //&& !MDSE_ITEM_CLS_TP.OCEOEM.equals(mdseItemClstpCd)) {
                        && !(MDSE_ITEM_CLS_TP.OCEOEM.equals(mdseItemClstpCd) || isMdseItemClsTpFlg)) {
                        // END 2023/09/07 G.Quan [QC#61589, MOD]

                    // 2023/09/04 QC#61703 START
                    if (reqQty != null) {
                        INSRC_RULE_DTLTMsg rule = new INSRC_RULE_DTLTMsg();
                        ZYPEZDItemValueSetter.setValue(rule.insrcRuleDtlNum, ruleDtl.insrcRuleDtlNum);
                        ZYPEZDItemValueSetter.setValue(rule.insrcRuleDtlNm, ruleDtl.insrcRuleDtlNm);
                        ZYPEZDItemValueSetter.setValue(rule.srchSrcWhFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(rule.srchInsrcPlnFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(rule.srchCusaInvtyFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(rule.srchCviInvtyFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(rule.srchWhOwnrInvtyFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(rule.srchWhOwnrCd, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(rule.srchSpecLocInvtyFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(rule.cratPoFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(rule.srchOrigItemFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(rule.srchSupdItemFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(rule.procrTpCd, PROCR_TP.SUPPLIER);
                        if (PRCH_REQ_TP.RUSH.equals(prInfo.getHeaderInfo().prchReqTpCd.getValue())) {
                            ZYPEZDItemValueSetter.setValue(rule.prchReqTpCd, PRCH_REQ_TP.STANDARD_2);
                        } else {
                            ZYPEZDItemValueSetter.setValue(rule.prchReqTpCd, PRCH_REQ_TP.EMERGENCY);
                        }
                        ZYPEZDItemValueSetter.setValue(rule.prchReqLineTpCd, PRCH_REQ_LINE_TP.INSOURCED_PO);
                        ZYPEZDItemValueSetter.setValue(rule.origLineFrzFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(rule.boNtfyReqFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(rule.whTrnsfBoReqFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(rule.srchStltInvtyFlg, ZYPConstant.FLG_OFF_N);
                        // START 2023/06/02 S.Dong [QC#55629, MOD]
                        //createPo(prInfo, rule, updMdsecd, reqQty, includeHazmat);
                        createPo(prInfo, rule, updMdsecd, reqQty, includeHazmat, null);
                        // END 2023/06/02 S.Dong [QC#55629, MOD]
                        return;
                    }
                    // 2023/09/04 QC#61703 END

                    // ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).mdseCd, updMdsecd);
                    // ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqFrzFlg, ZYPConstant.FLG_OFF_N);
                    // ZYPEZDItemValueSetter.setValue(ruleDtl.boNtfyReqFlg, ZYPConstant.FLG_OFF_N);
                    // ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqLineCmntTxt, prInfo.getDetailInfo().prchReqLineCmntTxt.getValue());

                }
            }
        }

        pMsg.prchReqInfo.setValidCount(1);

        executeNPZC1030(pMsg);
        List<String> errList;
        errList = S21ApiUtil.getXxMsgIdList(pMsg);

        if (errList.size() > 0) {
            for (String xxMsgId : errList) {
                S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));

                ErrorMessageBean errMsgBean = new ErrorMessageBean();
                errMsgBean.setPrchReqNum(prInfo.getHeaderInfo().prchReqNum.getValue());
                errMsgBean.setPrchReqLineNum(prInfo.getDetailInfo().prchReqLineNum.getValue());
                errMsgBean.setMdseCd(prInfo.getDetailInfo().mdseCd.getValue());
                errMsgBean.setPrchReqQty(prInfo.getDetailInfo().prchReqQty.getValue());
                errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage(xxMsgId));
                errMsgBeanList.add(errMsgBean);

                if (xxMsgId.endsWith("E")) {
                    rollback();
                }
            }
        }
    }
    // START 2023/06/02 S.Dong [QC#55629, MOD]
    //private void sendMail(PrInfoBean prInfo, BigDecimal balQty, String poReqNum, String poReqLineNum, String poReqApvlStsCd) {
    private void sendMail(PrInfoBean prInfo, BigDecimal balQty, BigDecimal origNum, String poReqNum, String poReqLineNum, String poReqApvlStsCd) {
    // END 2023/06/02 S.Dong [QC#55629, MOD]
        S21Mail mail = new S21Mail(globalCompanyCode);
        // Get mail group
        S21MailGroup groupTo = new S21MailGroup(globalCompanyCode, NPAB136001Constant.MAIL_TO_GROUP_ID);
        // START 2023/09/19 G.Quan [QC#59207, DEL]
        // Get mail group
        //S21MailGroup groupFrom = new S21MailGroup(globalCompanyCode, NPAB136001Constant.MAIL_FROM_GROUP_ID);
        // END 2023/09/19 G.Quan [QC#59207, DEL]

        // Set address
        List<S21MailAddress> toAddrList = groupTo.getMailAddress();
        // START 2023/09/19 G.Quan [QC#59207, MOD]
        //if (toAddrList.isEmpty()) {
        if (!ZYPCommonFunc.hasValue(fromAddrForKickOutStd) || toAddrList.isEmpty()) {
        // END 2023/09/19 G.Quan [QC#59207, MOD]
            // throw new
            // S21AbendException(NPAB136001Constant.NPZM0161E);
            return;
        }
        mail.setToAddressList(toAddrList);
        // START 2023/09/19 G.Quan [QC#59207, DEL]
        //List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
        //mail.setFromAddress(fromAddrList.get(0));
        // END 2023/09/19 G.Quan [QC#59207, DEL]
        // START 2023/09/19 G.Quan [QC#59207, ADD]
        S21MailAddress fromAddr = new S21MailAddress(this.globalCompanyCode, fromAddrForKickOutStd);
        mail.setFromAddress(fromAddr);
        // END 2023/09/19 G.Quan [QC#59207, ADD]

        S21MailTemplate template = new S21MailTemplate(globalCompanyCode, NPAB136001Constant.MAIL_TEMPLATE_ID);
        if (template == null) {
            throw new S21AbendException(NPAB136001Constant.NPZM0161E);
        }
        // Set template parameter
        Date execDate = new Date();
        SimpleDateFormat sdfDate = new SimpleDateFormat();
        sdfDate.applyPattern(NPAB136001Constant.DATE_FORMAT);
        SimpleDateFormat sdfTime = new SimpleDateFormat();
        sdfTime.applyPattern(NPAB136001Constant.TIME_FORMAT);

        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PRCH_REQ_NUM_SUBJECT, prInfo.getHeaderInfo().prchReqNum.getValue());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_TECH_TOC_CD, prInfo.getHeaderInfo().rqstTechTocCd.getValue());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PRCH_REQ_NUM, prInfo.getHeaderInfo().prchReqNum.getValue());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PRCH_REQ_LINE_NUM, prInfo.getDetailInfo().prchReqLineNum.getValue());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_MDSE_CD, prInfo.getDetailInfo().mdseCd.getValue());
        // START 2023/06/02 S.Dong [QC#55629, MOD]
        //template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PRCH_REQ_QTY, prInfo.getDetailInfo().prchReqQty.getValue().toString());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PRCH_REQ_QTY, origNum);
        // END 2023/06/02 S.Dong [QC#55629, MOD]
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PRCH_REQ_BAL_QTY, balQty.toString());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PRCH_REQ_CRAT_DT, prInfo.getHeaderInfo().prchReqCratDt.getValue());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_FSR_NUM, prInfo.getHeaderInfo().fsrNum.getValue());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_SVC_TASK_NUM, prInfo.getHeaderInfo().svcTaskNum.getValue());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_SVC_MACH_SER_NUM, prInfo.getHeaderInfo().svcMachSerNum.getValue());
        if (poReqNum == null) {
            template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PO_REQ_NUM, "");
        } else {
            template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PO_REQ_NUM, poReqNum);
        }
        if (poReqLineNum == null) {
            template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PO_REQ_LINE_NUM, "");
        } else {
            template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PO_REQ_LINE_NUM, poReqLineNum);
        }
        if (poReqApvlStsCd == null) {
            template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PO_REQ_APVL_STS_CD, "");
        } else {
            template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PO_REQ_APVL_STS_CD, poReqApvlStsCd);
        }

        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_TODAY, sdfDate.format(execDate));
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_TIME, sdfTime.format(execDate));

        // Set mail subject
        mail.setSubject(template.getSubject(NPAB136001Constant.ML_LANG), NPAB136001Constant.ML_LANG_CD);
        mail.setMailTemplate(template);

        // Post
        mail.postMail();
    }

    // 08/29/2017 CITS S.Endo Add Sol#406(QC#19243) START
    private NPZC103001PMsg createNPZC103001PMsgCreate(NPZC103001PMsg insrcPrPMsg, PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String prchReqLineTpCd, String srcInvtyLocCd, String mdseCd, BigDecimal prchReqQty, boolean includeHazmat) {
        return createNPZC103001PMsgCreate(insrcPrPMsg, prInfo, ruleDtl, prchReqLineTpCd, srcInvtyLocCd, mdseCd, prchReqQty, false, includeHazmat);
    }

    // 08/29/2017 CITS S.Endo Add Sol#406(QC#19243) END
    // 08/29/2017 CITS S.Endo Mod Sol#406(QC#19243) START
    // private NPZC103001PMsg
    // createNPZC103001PMsgCreate(NPZC103001PMsg insrcPrPMsg,
    // PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String
    // prchReqLineTpCd, String srcInvtyLocCd, String mdseCd,
    // BigDecimal prchReqQty) {
    private NPZC103001PMsg createNPZC103001PMsgCreate(NPZC103001PMsg insrcPrPMsg, PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String prchReqLineTpCd, String srcInvtyLocCd, String mdseCd, BigDecimal prchReqQty, boolean boFlg, boolean includeHazmat) {
        // 08/29/2017 CITS S.Endo Mod Sol#406(QC#19243) END
        int i = insrcPrPMsg.prchReqInfo.getValidCount();

        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.xxModeCd, NPZC103001Constant.MODE_CREATE);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.eventId, NPZC103001Constant.EVENT_INSOURCING);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.procDt, salesDate);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqNum, prInfo.getHeaderInfo().prchReqNum);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqRecTpCd, prInfo.getHeaderInfo().prchReqRecTpCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqTpCd, prInfo.getHeaderInfo().prchReqTpCd);
        // QC#21657-1 Modify.
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.shipToCustCd, prInfo.getHeaderInfo().shipToCustCd);


        // Detail
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).prchReqLineNum, prInfo.getDetailInfo().prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).origPrchReqLineNum, prInfo.getDetailInfo().prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).origPrchReqLineSubNum, prInfo.getDetailInfo().prchReqLineSubNum);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).prchReqLineTpCd, prchReqLineTpCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).procrTpCd, ruleDtl.procrTpCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).destInvtyLocCd, prInfo.getDetailInfo().destInvtyLocCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).srcInvtyLocCd, srcInvtyLocCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).prchReqQty, prchReqQty);
        // 08/29/2017 CITS S.Endo Add Sol#406(QC#19243) START
        if (boFlg) {
            ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).prchReqLineCmntTxt, prInfo.getDetailInfo().prchReqLineCmntTxt);
            currentIndexForBO = new Integer(i);
        }
        // 08/29/2017 CITS S.Endo Add Sol#406(QC#19243) END

        // START 2023/10/16 T.Kuroda [QC#61740, DEL]
//        // QC#2366
//        if (includeHazmat && !isCustomerShip(prInfo.getHeaderInfo().shipToCustCd.getValue())) {
//            String slctShpgSvcLvl = selectShpgSvcLvlOfHazmat(prchReqLineTpCd, mdseCd, srcInvtyLocCd, prInfo.getDetailInfo().shpgSvcLvlCd.getValue());
//
//            ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).shpgSvcLvlCd, slctShpgSvcLvl);
//            if (SHPG_SVC_LVL.GROUND_SERVICE.equals(slctShpgSvcLvl)) {
//                if (ZYPCommonFunc.hasValue(insrcPrPMsg.prchReqInfo.no(i).carrCd)) {
//                    if (!checkShpgSvcCarrReln(insrcPrPMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue(), insrcPrPMsg.prchReqInfo.no(i).carrCd.getValue())) {
//                        insrcPrPMsg.prchReqInfo.no(i).carrCd.clear();
//                    }
//                }
//                // Update ShipTo
//                if (!isCustomerWhShip(prInfo.getDetailInfo().destInvtyLocCd.getValue()) //
//                        && isPreferredCarrierFedexAndSetCarrCd(prInfo.getHeaderInfo().rqstTechTocCd.getValue(), insrcPrPMsg, i)) {
//                    setShipToCustHazmat(insrcPrPMsg);
//                }
//            } else {
//                // QC#2366 Update. 06/29/2018
//                if (isDBSWh(srcInvtyLocCd)) {
//                    ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).shpgSvcLvlCd, SHPG_SVC_LVL.GROUND_SERVICE);
//                    if (!checkShpgSvcCarrReln(insrcPrPMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue(), insrcPrPMsg.prchReqInfo.no(i).carrCd.getValue())) {
//                        insrcPrPMsg.prchReqInfo.no(i).carrCd.clear();
//                    }
//                } else {
//                    String carrCdForWillCall = getCarrCdForWillCall();
//                    ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).carrCd, carrCdForWillCall);
//                }
//            }
//        }
        // END 2023/10/16 T.Kuroda [QC#61740, DEL]

        insrcPrPMsg.prchReqInfo.setValidCount(i + 1);

        return insrcPrPMsg;
    }

    private NPZC103001PMsg createNPZC103001PMsgForCreateForPr(NPZC103001PMsg insrcPrPMsg, PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, NPZC113001PMsg vendor, String mdseCd, BigDecimal prchReqQty, boolean includeHazmat, boolean isCreatePo) {

        int i = insrcPrPMsg.prchReqInfo.getValidCount();

        // 2023/09/04 QC#61703 START
        if (ZYPCommonFunc.hasValue(vendor.vndCd) && !isCreatePo) {
            // 2023/09/08 QC#61704 START
            if (mdseMap.containsKey(vendor.vndCd.getValue().concat(vendor.splyItemNum.getValue()))) {
                BigDecimal existReqQty = (BigDecimal) mdseMap.get(vendor.vndCd.getValue().concat(vendor.splyItemNum.getValue()));
                mdseMap.put(vendor.vndCd.getValue().concat(vendor.splyItemNum.getValue()), existReqQty.add(prchReqQty));
            } else {
                mdseMap.put(vendor.vndCd.getValue().concat(vendor.splyItemNum.getValue()), prchReqQty);
            }
            // 2023/09/08 QC#61704 END
        }
        // 2023/09/04 QC#61703 END

        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.xxModeCd, NPZC103001Constant.MODE_CREATE);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.eventId, NPZC103001Constant.EVENT_INSOURCING);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.procDt, salesDate);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqNum, prInfo.getHeaderInfo().prchReqNum);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqRecTpCd, prInfo.getHeaderInfo().prchReqRecTpCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqTpCd, prInfo.getHeaderInfo().prchReqTpCd);
        // QC#21657-1 Modify
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.shipToCustCd, prInfo.getHeaderInfo().shipToCustCd);

        // Detail
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).prchReqLineNum, prInfo.getDetailInfo().prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).origPrchReqLineNum, prInfo.getDetailInfo().prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).origPrchReqLineSubNum, prInfo.getDetailInfo().prchReqLineSubNum);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).prchReqLineTpCd, ruleDtl.prchReqLineTpCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).procrTpCd, ruleDtl.procrTpCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).destInvtyLocCd, prInfo.getDetailInfo().destInvtyLocCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).prntVndCd, vendor.prntVndCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).vndCd, vendor.vndCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).prchReqQty, prchReqQty);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).prchReqRelStsCd, NPAB136001Constant.PRCH_REQ_REL_STS_8);
        // QC#24918
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).prchReqLineCmntTxt, prInfo.getDetailInfo().prchReqLineCmntTxt);

        // START 2023/10/16 T.Kuroda [QC#61740, DEL]
//        // QC#2366
//        if (includeHazmat && !isCustomerShip(prInfo.getHeaderInfo().shipToCustCd.getValue())) {
//            String slctShpgSvcLvl = selectShpgSvcLvlOfHazmat(ruleDtl.prchReqLineTpCd.getValue(), mdseCd, null, prInfo.getDetailInfo().shpgSvcLvlCd.getValue());
//
//            ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).shpgSvcLvlCd, slctShpgSvcLvl);
//            if (SHPG_SVC_LVL.GROUND_SERVICE.equals(slctShpgSvcLvl)) {
//                if (ZYPCommonFunc.hasValue(insrcPrPMsg.prchReqInfo.no(i).carrCd)) {
//                    if (!checkShpgSvcCarrReln(insrcPrPMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue(), insrcPrPMsg.prchReqInfo.no(i).carrCd.getValue())) {
//                        insrcPrPMsg.prchReqInfo.no(i).carrCd.clear();
//                    }
//                }
//                // Update ShipTo
//                if (!isCustomerWhShip(prInfo.getDetailInfo().destInvtyLocCd.getValue()) //
//                        && isPreferredCarrierFedexAndSetCarrCd(prInfo.getHeaderInfo().rqstTechTocCd.getValue(), insrcPrPMsg, i)) {
//                    setShipToCustHazmat(insrcPrPMsg);
//                }
//            } else {
//                String carrCdForWillCall = getCarrCdForWillCall();
//                ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).carrCd, carrCdForWillCall);
//            }
//        }
        // END 2023/10/16 T.Kuroda [QC#61740, DEL]

        insrcPrPMsg.prchReqInfo.setValidCount(i + 1);

        return insrcPrPMsg;
    }

    private NPZC103001PMsg createNPZC103001PMsgForCratVndPr(NPZC103001PMsg insrcPrPMsg, PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, NPZC113001PMsg vendor, NPZC129001PMsg poQty, NPXC001001CurrencyConversionBean currency,
            String dealCcyCd, String mdseCd, BigDecimal prchReqQty, boolean includeHazmat) {

        int i = insrcPrPMsg.prchReqInfo.getValidCount();

        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.xxModeCd, NPZC103001Constant.MODE_CREATE);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.eventId, NPZC103001Constant.EVENT_SUBMIT);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.procDt, salesDate);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqRecTpCd, PRCH_REQ_REC_TP.PO_REQUISITION);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqTpCd, ruleDtl.prchReqTpCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqCratByPsnCd, ZYPCodeDataUtil.getVarCharConstValue(NPAB136001Constant.PR_CRAT_SYSTEM_USER, globalCompanyCode));
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqRqstByPsnCd, prInfo.getHeaderInfo().prchReqRqstByPsnCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqSrcTpCd, ZYPCodeDataUtil.getVarCharConstValue(NPAB136001Constant.INSC_PRCH_REQ_SRC_TP_CD, globalCompanyCode));
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchGrpCd, prInfo.getMdseInfo().prchGrpCd);
        if (ZYPConstant.FLG_ON_Y.equals(vendor.xxErrFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.ENTERED);
        } else {
            if (ZYPCommonFunc.hasValue(ruleDtl.prchReqApvlStsCd)) {
                ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqApvlStsCd, ruleDtl.prchReqApvlStsCd);
            } else {
                ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqApvlStsCd, prInfo.getHeaderInfo().prchReqApvlStsCd);
            }
        }
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqApvlByPsnCd, prInfo.getHeaderInfo().prchReqApvlByPsnCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.fsrNum, prInfo.getHeaderInfo().fsrNum);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.svcTaskNum, prInfo.getHeaderInfo().svcTaskNum);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.svcMachSerNum, prInfo.getHeaderInfo().svcMachSerNum);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.rqstTechTocCd, prInfo.getHeaderInfo().rqstTechTocCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.shipToCustCd, prInfo.getHeaderInfo().shipToCustCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.shipToLocNm, prInfo.getHeaderInfo().shipToLocNm);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.shipToAddlLocNm, prInfo.getHeaderInfo().shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.shipToFirstLineAddr, prInfo.getHeaderInfo().shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.shipToScdLineAddr, prInfo.getHeaderInfo().shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.shipToThirdLineAddr, prInfo.getHeaderInfo().shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.shipToFrthLineAddr, prInfo.getHeaderInfo().shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.shipToFirstRefCmntTxt, prInfo.getHeaderInfo().shipToFirstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.shipToScdRefCmntTxt, prInfo.getHeaderInfo().shipToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.shipToCtyAddr, prInfo.getHeaderInfo().shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.shipToStCd, prInfo.getHeaderInfo().shipToStCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.shipToProvNm, prInfo.getHeaderInfo().shipToProvNm);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.shipToCntyNm, prInfo.getHeaderInfo().shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.shipToPostCd, prInfo.getHeaderInfo().shipToPostCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.shipToCtryCd, prInfo.getHeaderInfo().shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.ctacPsnNm, prInfo.getHeaderInfo().ctacPsnNm);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqCmntTxt, NPAB136001Constant.PRCH_REQ_CMNT_TXT_MSG + prInfo.getHeaderInfo().prchReqNum.getValue());
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.delyAddlCmntTxt, prInfo.getHeaderInfo().delyAddlCmntTxt);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.poQlfyCd, ZYPCodeDataUtil.getVarCharConstValue(NPAB136001Constant.TECH_INSRC_PO_QLFY_CD, globalCompanyCode));

        // Detail
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).origPrchReqLineNum, prInfo.getDetailInfo().prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).rqstRcvDt, prInfo.getDetailInfo().rqstRcvDt);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).rqstRcvTm, prInfo.getDetailInfo().rqstRcvTm);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).poSchdRelDt, salesDate);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).frtCondCd, prInfo.getDetailInfo().frtCondCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).shpgSvcLvlCd, prInfo.getDetailInfo().shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).carrCd, prInfo.getDetailInfo().carrCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).procrTpCd, ruleDtl.procrTpCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).destInvtyLocCd, prInfo.getDetailInfo().destInvtyLocCd);
        if (!ZYPConstant.FLG_ON_Y.equals(vendor.xxErrFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).prntVndCd, vendor.prntVndCd);
            ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).vndCd, vendor.vndCd);
            ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).aslMdseCd, vendor.splyItemNum);
        }
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).prchReqQty, prchReqQty);
        if (!ZYPConstant.FLG_ON_Y.equals(vendor.xxErrFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).prchReqDispQty, poQty.poDispQty);
            ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).prchReqDsplUomCd, vendor.vndUomCd);
            ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).aslDtlPk, vendor.aslDtlPk);
            ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).aslUnitPrcAmt, vendor.unitPrcAmt);
            ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).entDealNetUnitPrcAmt, vendor.unitPrcAmt);
            ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).entFuncNetUnitPrcAmt, currency.getFuncAmt());
            ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).ccyCd, dealCcyCd);
            ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).exchRate, currency.getExchRate());
        }
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).prchReqRelStsCd, PRCH_REQ_REL_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).trxRefNum, prInfo.getHeaderInfo().prchReqNum);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).trxRefLineNum, prInfo.getDetailInfo().prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).poSchdRelDt, salesDate);

        // QC#8195 ItemNumber Check ASL SulpilerItem mod QC#24918
        if (materialParts != null //
                && ZYPCommonFunc.hasValue(vendor.mdseCd) //
                && Arrays.asList(materialParts).contains(vendor.mdseCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).prchReqLineCmntTxt, prInfo.getDetailInfo().prchReqLineCmntTxt);
        }

        // START 2023/10/16 T.Kuroda [QC#61740, DEL]
//        // QC#2366
//        if (includeHazmat && !isCustomerShip(prInfo.getHeaderInfo().shipToCustCd.getValue())) {
//            String slctShpgSvcLvl = selectShpgSvcLvlOfHazmat(ruleDtl.prchReqLineTpCd.getValue(), mdseCd, null, prInfo.getDetailInfo().shpgSvcLvlCd.getValue());
//
//            ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).shpgSvcLvlCd, slctShpgSvcLvl);
//            if (SHPG_SVC_LVL.GROUND_SERVICE.equals(slctShpgSvcLvl)) {
//                if (ZYPCommonFunc.hasValue(insrcPrPMsg.prchReqInfo.no(i).carrCd)) {
//                    if (!checkShpgSvcCarrReln(insrcPrPMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue(), insrcPrPMsg.prchReqInfo.no(i).carrCd.getValue())) {
//                        insrcPrPMsg.prchReqInfo.no(i).carrCd.clear();
//                    }
//                }
//                // Update ShipTo
//                if (!isCustomerWhShip(prInfo.getDetailInfo().destInvtyLocCd.getValue()) //
//                        && isPreferredCarrierFedexAndSetCarrCd(prInfo.getHeaderInfo().rqstTechTocCd.getValue(), insrcPrPMsg, i)) {
//                    setShipToCustHazmat(insrcPrPMsg);
//                }
//            } else {
//                String carrCdForWillCall = getCarrCdForWillCall();
//                ZYPEZDItemValueSetter.setValue(insrcPrPMsg.prchReqInfo.no(i).carrCd, carrCdForWillCall);
//            }
//        }
        // END 2023/10/16 T.Kuroda [QC#61740, DEL]

        insrcPrPMsg.prchReqInfo.setValidCount(i + 1);

        return insrcPrPMsg;
    }

    private int countVarcharConstTable(String mdseItemClsTpCd) {

        if (!ZYPCommonFunc.hasValue(mdseItemClsTpCd)) {
            return 0;
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int ret = 0;

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();

            paramMap.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB136001Constant.DB_PARAM_VAR_CHAR_CONST_NM, NPAB136001Constant.INSRC_PLN_ITEM_CLS_TP_CD + NPAB136001Constant.SELECT_LIKE_CHAR);
            paramMap.put(NPAB136001Constant.DB_PARAM_VAR_CHAR_CONST_VAL, mdseItemClsTpCd);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("countVarcharConstTable", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ret = resultSet.getInt("CNT");
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
        return ret;
    }

    private PrInfoBean getPRData(ResultSet rs) throws SQLException {

        PrInfoBean ret = new PrInfoBean();

        ret.setHeaderInfo(getHeader(rs));
        ret.setDetailInfo(getDetail(rs));
        ret.setMdseInfo(getMdseInfo(rs));
        ret.setMdseItemStsInfo(getMdseSts(rs));
        ret.setRtlWhInfo(getRtlWh(rs));
        ret.setS21PsnInfo(getS21Psn(rs));

        return ret;
    }

    private PRCH_REQTMsg getHeader(ResultSet rs) throws SQLException {

        PRCH_REQTMsg ret = new PRCH_REQTMsg();

        ZYPEZDItemValueSetter.setValue(ret.prchReqNum, rs.getString(NPAB136001Constant.PRCH_REQ_NUM));
        ZYPEZDItemValueSetter.setValue(ret.prchReqRecTpCd, rs.getString(NPAB136001Constant.PRCH_REQ_REC_TP_CD));
        ZYPEZDItemValueSetter.setValue(ret.prchReqTpCd, rs.getString(NPAB136001Constant.PRCH_REQ_TP_CD));
        ZYPEZDItemValueSetter.setValue(ret.prchReqStsCd, rs.getString(NPAB136001Constant.PRCH_REQ_STS_CD));
        ZYPEZDItemValueSetter.setValue(ret.prchReqCratDt, rs.getString(NPAB136001Constant.PRCH_REQ_CRAT_DT));
        ZYPEZDItemValueSetter.setValue(ret.prchReqCratTm, rs.getString(NPAB136001Constant.PRCH_REQ_CRAT_TM));
        ZYPEZDItemValueSetter.setValue(ret.prchReqCratTz, rs.getString(NPAB136001Constant.PRCH_REQ_CRAT_TZ));
        ZYPEZDItemValueSetter.setValue(ret.prchReqCratDispDtTmTs, rs.getString(NPAB136001Constant.PRCH_REQ_CRAT_DISP_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(ret.prchReqCratByPsnCd, rs.getString(NPAB136001Constant.PRCH_REQ_CRAT_BY_PSN_CD));
        ZYPEZDItemValueSetter.setValue(ret.prchReqCratByNm, rs.getString(NPAB136001Constant.PRCH_REQ_CRAT_BY_NM));
        ZYPEZDItemValueSetter.setValue(ret.prchReqRqstByPsnCd, rs.getString(NPAB136001Constant.PRCH_REQ_RQST_BY_PSN_CD));
        ZYPEZDItemValueSetter.setValue(ret.prchReqSrcTpCd, rs.getString(NPAB136001Constant.PRCH_REQ_SRC_TP_CD));
        ZYPEZDItemValueSetter.setValue(ret.fsrNum, rs.getString(NPAB136001Constant.FSR_NUM));
        ZYPEZDItemValueSetter.setValue(ret.svcTaskNum, rs.getString(NPAB136001Constant.SVC_TASK_NUM));
        ZYPEZDItemValueSetter.setValue(ret.svcMachSerNum, rs.getString(NPAB136001Constant.SVC_MACH_SER_NUM));
        ZYPEZDItemValueSetter.setValue(ret.cpoOrdNum, rs.getString(NPAB136001Constant.CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(ret.custIssPoNum, rs.getString(NPAB136001Constant.CUST_ISS_PO_NUM));
        ZYPEZDItemValueSetter.setValue(ret.custIssPoDt, rs.getString(NPAB136001Constant.CUST_ISS_PO_DT));
        ZYPEZDItemValueSetter.setValue(ret.cpoOrdTpCd, rs.getString(NPAB136001Constant.CPO_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(ret.ctacPsnNm, rs.getString(NPAB136001Constant.CTAC_PSN_NM));
        ZYPEZDItemValueSetter.setValue(ret.adminPsnCd, rs.getString(NPAB136001Constant.ADMIN_PSN_CD));
        ZYPEZDItemValueSetter.setValue(ret.prchReqApvlStsCd, rs.getString(NPAB136001Constant.PRCH_REQ_APVL_STS_CD));
        ZYPEZDItemValueSetter.setValue(ret.prchReqApvlDt, rs.getString(NPAB136001Constant.PRCH_REQ_APVL_DT));
        ZYPEZDItemValueSetter.setValue(ret.prchReqApvlByPsnCd, rs.getString(NPAB136001Constant.PRCH_REQ_APVL_BY_PSN_CD));
        ZYPEZDItemValueSetter.setValue(ret.prchReqApvlByNm, rs.getString(NPAB136001Constant.PRCH_REQ_APVL_BY_NM));
        ZYPEZDItemValueSetter.setValue(ret.rqstTechTocCd, rs.getString(NPAB136001Constant.RQST_TECH_TOC_CD));
        ZYPEZDItemValueSetter.setValue(ret.shipToCustCd, rs.getString(NPAB136001Constant.SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(ret.shipToLocNm, rs.getString(NPAB136001Constant.SHIP_TO_LOC_NM));
        ZYPEZDItemValueSetter.setValue(ret.shipToAddlLocNm, rs.getString(NPAB136001Constant.SHIP_TO_ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(ret.shipToFirstLineAddr, rs.getString(NPAB136001Constant.SHIP_TO_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(ret.shipToScdLineAddr, rs.getString(NPAB136001Constant.SHIP_TO_SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(ret.shipToThirdLineAddr, rs.getString(NPAB136001Constant.SHIP_TO_THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(ret.shipToFrthLineAddr, rs.getString(NPAB136001Constant.SHIP_TO_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(ret.shipToFirstRefCmntTxt, rs.getString(NPAB136001Constant.SHIP_TO_FIRST_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(ret.shipToScdRefCmntTxt, rs.getString(NPAB136001Constant.SHIP_TO_SCD_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(ret.shipToCtyAddr, rs.getString(NPAB136001Constant.SHIP_TO_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(ret.shipToStCd, rs.getString(NPAB136001Constant.SHIP_TO_ST_CD));
        ZYPEZDItemValueSetter.setValue(ret.shipToProvNm, rs.getString(NPAB136001Constant.SHIP_TO_PROV_NM));
        ZYPEZDItemValueSetter.setValue(ret.shipToCntyNm, rs.getString(NPAB136001Constant.SHIP_TO_CNTY_NM));
        ZYPEZDItemValueSetter.setValue(ret.shipToPostCd, rs.getString(NPAB136001Constant.SHIP_TO_POST_CD));
        ZYPEZDItemValueSetter.setValue(ret.shipToCtryCd, rs.getString(NPAB136001Constant.SHIP_TO_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(ret.billToCustCd, rs.getString(NPAB136001Constant.BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(ret.sellToCustCd, rs.getString(NPAB136001Constant.SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(ret.aslShipToCustCd, rs.getString(NPAB136001Constant.ASL_SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(ret.poQlfyCd, rs.getString(NPAB136001Constant.PO_QLFY_CD));
        ZYPEZDItemValueSetter.setValue(ret.vndDropShipFlg, rs.getString(NPAB136001Constant.VND_DROP_SHIP_FLG));
        ZYPEZDItemValueSetter.setValue(ret.prchReqCmntTxt, rs.getString(NPAB136001Constant.PRCH_REQ_CMNT_TXT));
        // QC#21209: Delete spclInstnCmntTxt. spclInstnCmntTxt does
        // not use.
        ZYPEZDItemValueSetter.setValue(ret.delyAddlCmntTxt, rs.getString(NPAB136001Constant.DELY_ADDL_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(ret.rcvAddlCmntTxt, rs.getString(NPAB136001Constant.RCV_ADDL_CMNT_TXT));

        return ret;
    }

    private PRCH_REQ_DTLTMsg getDetail(ResultSet rs) throws SQLException {

        PRCH_REQ_DTLTMsg ret = new PRCH_REQ_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(ret.prchReqLineNum, rs.getString(NPAB136001Constant.PRCH_REQ_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(ret.prchReqLineSubNum, rs.getBigDecimal(NPAB136001Constant.PRCH_REQ_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(ret.prchReqIntfcPk, rs.getBigDecimal(NPAB136001Constant.PRCH_REQ_INTFC_PK));
        ZYPEZDItemValueSetter.setValue(ret.origPrchReqLineNum, rs.getString(NPAB136001Constant.ORIG_PRCH_REQ_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(ret.origPrchReqLineSubNum, rs.getBigDecimal(NPAB136001Constant.ORIG_PRCH_REQ_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(ret.prchReqLineTpCd, rs.getString(NPAB136001Constant.PRCH_REQ_LINE_TP_CD));
        ZYPEZDItemValueSetter.setValue(ret.prchReqLineStsCd, rs.getString(NPAB136001Constant.PRCH_REQ_LINE_STS_CD));
        // QC#57659 Add start
        ZYPEZDItemValueSetter.setValue(ret.prchReqCratDt, rs.getString("DTL_PRCH_REQ_CRAT_DT"));
        ZYPEZDItemValueSetter.setValue(ret.prchReqCratTm, rs.getString("DTL_PRCH_REQ_CRAT_TM"));
        // QC#57659 Add end
        ZYPEZDItemValueSetter.setValue(ret.rqstRcvDt, rs.getString(NPAB136001Constant.RQST_RCV_DT));
        ZYPEZDItemValueSetter.setValue(ret.rqstRcvTm, rs.getString(NPAB136001Constant.RQST_RCV_TM));
        ZYPEZDItemValueSetter.setValue(ret.rqstRcvTz, rs.getString(NPAB136001Constant.RQST_RCV_TZ));
        ZYPEZDItemValueSetter.setValue(ret.rqstRcvDispDtTmTs, rs.getString(NPAB136001Constant.RQST_RCV_DISP_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(ret.poSchdRelDt, rs.getString(NPAB136001Constant.PO_SCHD_REL_DT));
        ZYPEZDItemValueSetter.setValue(ret.prchReqRelStsCd, rs.getString(NPAB136001Constant.PRCH_REQ_REL_STS_CD));
        ZYPEZDItemValueSetter.setValue(ret.prchReqRelDtTmTs, rs.getString(NPAB136001Constant.PRCH_REQ_REL_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(ret.prchReqRelErrMsgTxt, rs.getString(NPAB136001Constant.PRCH_REQ_REL_ERR_MSG_TXT));
        ZYPEZDItemValueSetter.setValue(ret.prchReqFrzFlg, rs.getString(NPAB136001Constant.PRCH_REQ_FRZ_FLG));
        ZYPEZDItemValueSetter.setValue(ret.shpgPlnNum, rs.getString(NPAB136001Constant.SHPG_PLN_NUM));
        ZYPEZDItemValueSetter.setValue(ret.shipFromSoNum, rs.getString(NPAB136001Constant.SHIP_FROM_SO_NUM));
        ZYPEZDItemValueSetter.setValue(ret.procrTpCd, rs.getString(NPAB136001Constant.PROCR_TP_CD));
        ZYPEZDItemValueSetter.setValue(ret.destInvtyLocCd, rs.getString(NPAB136001Constant.DEST_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(ret.destRtlWhCd, rs.getString(NPAB136001Constant.DEST_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(ret.destRtlSwhCd, rs.getString(NPAB136001Constant.DEST_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(ret.srcInvtyLocCd, rs.getString(NPAB136001Constant.SRC_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(ret.srcRtlWhCd, rs.getString(NPAB136001Constant.SRC_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(ret.srcRtlSwhCd, rs.getString(NPAB136001Constant.SRC_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(ret.prntVndCd, rs.getString(NPAB136001Constant.PRNT_VND_CD));
        ZYPEZDItemValueSetter.setValue(ret.vndCd, rs.getString(NPAB136001Constant.VND_CD));
        ZYPEZDItemValueSetter.setValue(ret.vndInvtyLocCd, rs.getString(NPAB136001Constant.VND_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(ret.shpgSvcLvlCd, rs.getString(NPAB136001Constant.SHPG_SVC_LVL_CD));
        ZYPEZDItemValueSetter.setValue(ret.carrCd, rs.getString(NPAB136001Constant.CARR_CD));
        ZYPEZDItemValueSetter.setValue(ret.carrAcctNum, rs.getString(NPAB136001Constant.CARR_ACCT_NUM));
        ZYPEZDItemValueSetter.setValue(ret.configTpCd, rs.getString(NPAB136001Constant.CONFIG_TP_CD));
        ZYPEZDItemValueSetter.setValue(ret.svcConfigMstrPk, rs.getBigDecimal(NPAB136001Constant.SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(ret.mdseCd, rs.getString(NPAB136001Constant.MDSE_CD));
        ZYPEZDItemValueSetter.setValue(ret.poMdseCmpsnTpCd, rs.getString(NPAB136001Constant.PO_MDSE_CMPSN_TP_CD));
        ZYPEZDItemValueSetter.setValue(ret.origRqstMdseCd, rs.getString(NPAB136001Constant.ORIG_RQST_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(ret.aslMdseCd, rs.getString(NPAB136001Constant.ASL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(ret.prchReqQty, rs.getBigDecimal(NPAB136001Constant.PRCH_REQ_QTY));
        ZYPEZDItemValueSetter.setValue(ret.ordQty, rs.getBigDecimal(NPAB136001Constant.ORD_QTY));
        ZYPEZDItemValueSetter.setValue(ret.custUomCd, rs.getString(NPAB136001Constant.CUST_UOM_CD));
        ZYPEZDItemValueSetter.setValue(ret.prchReqDispQty, rs.getBigDecimal(NPAB136001Constant.PRCH_REQ_DISP_QTY));
        ZYPEZDItemValueSetter.setValue(ret.prchReqDsplUomCd, rs.getString(NPAB136001Constant.PRCH_REQ_DSPL_UOM_CD));
        ZYPEZDItemValueSetter.setValue(ret.prchReqRelQty, rs.getBigDecimal(NPAB136001Constant.PRCH_REQ_REL_QTY));
        ZYPEZDItemValueSetter.setValue(ret.prchReqBalQty, rs.getBigDecimal(NPAB136001Constant.PRCH_REQ_BAL_QTY));
        ZYPEZDItemValueSetter.setValue(ret.prchReqInsrcQty, rs.getBigDecimal(NPAB136001Constant.PRCH_REQ_INSRC_QTY));
        ZYPEZDItemValueSetter.setValue(ret.prchReqCancQty, rs.getBigDecimal(NPAB136001Constant.PRCH_REQ_CANC_QTY));
        ZYPEZDItemValueSetter.setValue(ret.ropQty, rs.getBigDecimal(NPAB136001Constant.ROP_QTY));
        ZYPEZDItemValueSetter.setValue(ret.minOrdQty, rs.getBigDecimal(NPAB136001Constant.MIN_ORD_QTY));
        ZYPEZDItemValueSetter.setValue(ret.incrOrdQty, rs.getBigDecimal(NPAB136001Constant.INCR_ORD_QTY));
        ZYPEZDItemValueSetter.setValue(ret.maxInvtyQty, rs.getBigDecimal(NPAB136001Constant.MAX_INVTY_QTY));
        ZYPEZDItemValueSetter.setValue(ret.curInvtyQty, rs.getBigDecimal(NPAB136001Constant.CUR_INVTY_QTY));
        ZYPEZDItemValueSetter.setValue(ret.curInvtyAvalQty, rs.getBigDecimal(NPAB136001Constant.CUR_INVTY_AVAL_QTY));
        ZYPEZDItemValueSetter.setValue(ret.schdInbdQty, rs.getBigDecimal(NPAB136001Constant.SCHD_INBD_QTY));
        ZYPEZDItemValueSetter.setValue(ret.schdOtbdQty, rs.getBigDecimal(NPAB136001Constant.SCHD_OTBD_QTY));
        ZYPEZDItemValueSetter.setValue(ret.insrcFlg, rs.getString(NPAB136001Constant.INSRC_FLG));
        ZYPEZDItemValueSetter.setValue(ret.poCratFlg, rs.getString(NPAB136001Constant.PO_CRAT_FLG));
        ZYPEZDItemValueSetter.setValue(ret.relRqstToPoOrdNum, rs.getString(NPAB136001Constant.REL_RQST_TO_PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(ret.poOrdNum, rs.getString(NPAB136001Constant.PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(ret.poOrdDtlLineNum, rs.getString(NPAB136001Constant.PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(ret.invtyOrdNum, rs.getString(NPAB136001Constant.INVTY_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(ret.invtyOrdLineNum, rs.getString(NPAB136001Constant.INVTY_ORD_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(ret.wrkOrdNum, rs.getString(NPAB136001Constant.WRK_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(ret.wrkOrdDtlLineNum, rs.getString(NPAB136001Constant.WRK_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(ret.trxRefNum, rs.getString(NPAB136001Constant.TRX_REF_NUM));
        ZYPEZDItemValueSetter.setValue(ret.trxRefLineNum, rs.getString(NPAB136001Constant.TRX_REF_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(ret.trxRefLineSubNum, rs.getString(NPAB136001Constant.TRX_REF_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(ret.prchReqLineCmntTxt, rs.getString(NPAB136001Constant.PRCH_REQ_LINE_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(ret.boEmlProcStsCd, rs.getString(NPAB136001Constant.BO_EML_PROC_STS_CD));
        ZYPEZDItemValueSetter.setValue(ret.soNum, rs.getString(NPAB136001Constant.SO_NUM));
        ZYPEZDItemValueSetter.setValue(ret.proNum, rs.getString(NPAB136001Constant.PRO_NUM));
        ZYPEZDItemValueSetter.setValue(ret.shipDtTmTs, rs.getString(NPAB136001Constant.SHIP_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(ret.shipQty, rs.getBigDecimal(NPAB136001Constant.SHIP_QTY));
        ZYPEZDItemValueSetter.setValue(ret.rwsNum, rs.getString(NPAB136001Constant.RWS_NUM));
        ZYPEZDItemValueSetter.setValue(ret.rwsCloDtTmTs, rs.getString(NPAB136001Constant.RWS_CLO_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(ret.rwsPutAwayQty, rs.getBigDecimal(NPAB136001Constant.RWS_PUT_AWAY_QTY));
        ZYPEZDItemValueSetter.setValue(ret.backToTechQty, rs.getBigDecimal(NPAB136001Constant.BACK_TO_TECH_QTY));
        ZYPEZDItemValueSetter.setValue(ret.fromStkStsCd, rs.getString(NPAB136001Constant.FROM_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(ret.toStkStsCd, rs.getString(NPAB136001Constant.TO_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(ret.thisMthFobCostAmt, rs.getBigDecimal(NPAB136001Constant.THIS_MTH_FOB_COST_AMT));
        ZYPEZDItemValueSetter.setValue(ret.aslDtlPk, rs.getBigDecimal(NPAB136001Constant.ASL_DTL_PK));
        ZYPEZDItemValueSetter.setValue(ret.aslUnitPrcAmt, rs.getBigDecimal(NPAB136001Constant.ASL_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(ret.entDealNetUnitPrcAmt, rs.getBigDecimal(NPAB136001Constant.ENT_DEAL_NET_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(ret.entPoDtlDealNetAmt, rs.getBigDecimal(NPAB136001Constant.ENT_PO_DTL_DEAL_NET_AMT));
        ZYPEZDItemValueSetter.setValue(ret.entFuncNetUnitPrcAmt, rs.getBigDecimal(NPAB136001Constant.ENT_FUNC_NET_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(ret.entPoDtlFuncNetAmt, rs.getBigDecimal(NPAB136001Constant.ENT_PO_DTL_FUNC_NET_AMT));
        ZYPEZDItemValueSetter.setValue(ret.ccyCd, rs.getString(NPAB136001Constant.CCY_CD));
        ZYPEZDItemValueSetter.setValue(ret.exchRate, rs.getBigDecimal(NPAB136001Constant.EXCH_RATE));
        ZYPEZDItemValueSetter.setValue(ret.frtChrgToCd, rs.getString(NPAB136001Constant.FRT_CHRG_TO_CD));
        ZYPEZDItemValueSetter.setValue(ret.frtChrgMethCd, rs.getString(NPAB136001Constant.FRT_CHRG_METH_CD));
        ZYPEZDItemValueSetter.setValue(ret.frtCondCd, rs.getString(NPAB136001Constant.FRT_COND_CD));
        ZYPEZDItemValueSetter.setValue(ret.vndPmtTermCd, rs.getString(NPAB136001Constant.VND_PMT_TERM_CD));
        ZYPEZDItemValueSetter.setValue(ret.vndPmtTermDescTxt, rs.getString(NPAB136001Constant.VND_PMT_TERM_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(ret.coaCmpyCd, rs.getString(NPAB136001Constant.COA_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(ret.coaAfflCd, rs.getString(NPAB136001Constant.COA_AFFL_CD));
        ZYPEZDItemValueSetter.setValue(ret.coaBrCd, rs.getString(NPAB136001Constant.COA_BR_CD));
        ZYPEZDItemValueSetter.setValue(ret.coaChCd, rs.getString(NPAB136001Constant.COA_CH_CD));
        ZYPEZDItemValueSetter.setValue(ret.coaCcCd, rs.getString(NPAB136001Constant.COA_CC_CD));
        ZYPEZDItemValueSetter.setValue(ret.coaAcctCd, rs.getString(NPAB136001Constant.COA_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(ret.coaProjCd, rs.getString(NPAB136001Constant.COA_PROJ_CD));
        ZYPEZDItemValueSetter.setValue(ret.coaExtnCd, rs.getString(NPAB136001Constant.COA_EXTN_CD));
        ZYPEZDItemValueSetter.setValue(ret.coaProdCd, rs.getString(NPAB136001Constant.COA_PROD_CD));
        ZYPEZDItemValueSetter.setValue(ret.techRqstNtcMlProcCd, rs.getString(NPAB136001Constant.TECH_RQST_NTC_ML_PROC_CD));
        ZYPEZDItemValueSetter.setValue(ret.techRtnNtcMlProcCd, rs.getString(NPAB136001Constant.TECH_RTN_NTC_ML_PROC_CD));
        ZYPEZDItemValueSetter.setValue(ret.vndRtrnRsnCd, rs.getString(NPAB136001Constant.VND_RTRN_RSN_CD));

        return ret;
    }

    private MDSETMsg getMdseInfo(ResultSet rs) throws SQLException {

        MDSETMsg ret = new MDSETMsg();

        ZYPEZDItemValueSetter.setValue(ret.mdseItemClsTpCd, rs.getString(NPAB136001Constant.MDSE_ITEM_CLS_TP_CD));
        ZYPEZDItemValueSetter.setValue(ret.prchGrpCd, rs.getString(NPAB136001Constant.PRCH_GRP_CD));

        return ret;
    }

    private MDSE_ITEM_STSTMsg getMdseSts(ResultSet rs) throws SQLException {

        MDSE_ITEM_STSTMsg ret = new MDSE_ITEM_STSTMsg();

        ZYPEZDItemValueSetter.setValue(ret.prchAvalFlg, rs.getString(NPAB136001Constant.PRCH_AVAL_FLG));

        return ret;
    }

    private RTL_WHTMsg getRtlWh(ResultSet rs) throws SQLException {

        RTL_WHTMsg ret = new RTL_WHTMsg();

        ZYPEZDItemValueSetter.setValue(ret.locTpCd, rs.getString(NPAB136001Constant.LOC_TP_CD));
        ZYPEZDItemValueSetter.setValue(ret.rtlWhCatgCd, rs.getString(NPAB136001Constant.RTL_WH_CATG_CD));

        return ret;
    }

    private S21_PSNTMsg getS21Psn(ResultSet rs) throws SQLException {

        S21_PSNTMsg ret = new S21_PSNTMsg();

        ZYPEZDItemValueSetter.setValue(ret.lineBizTpCd, rs.getString(NPAB136001Constant.LINE_BIZ_TP_CD));
        // QC#57659 Add start
        ZYPEZDItemValueSetter.setValue(ret.emlAddr, rs.getString("EML_ADDR"));
        // QC#57659 Add end

        return ret;
    }

    private BigDecimal getAvalWhInvty(String mdseCd, String invtyLocCd) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BigDecimal ret = null;

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

            HashMap<String, Object> paramMap = new HashMap<String, Object>();

            paramMap.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB136001Constant.DB_PARAM_MDSE_CD, mdseCd);
            paramMap.put(NPAB136001Constant.DB_PARAM_INVTY_LOC_CD, invtyLocCd);
            paramMap.put(NPAB136001Constant.DB_PARAM_LOC_STS_CD, LOC_STS.DC_STOCK);
            paramMap.put(NPAB136001Constant.DB_PARAM_STK_STS_CD, STK_STS.GOOD);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("getAvalWhInvty", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ret = resultSet.getBigDecimal(NPAB136001Constant.INVTY_AVAL_QTY);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return ret;
    }

    /**
     * QC#57659 Mod
     * @param mdseCd
     * @param whOwnrCd
     * @return
     */
    private BigDecimal getAvalWhOwnerInvty(String mdseCd, String whOwnrCd) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BigDecimal ret = null;

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB136001Constant.DB_PARAM_MDSE_CD, mdseCd);
            paramMap.put(NPAB136001Constant.DB_PARAM_LOC_STS_CD, LOC_STS.DC_STOCK);
            paramMap.put(NPAB136001Constant.DB_PARAM_STK_STS_CD, STK_STS.GOOD);
            paramMap.put(NPAB136001Constant.DB_PARAM_LOC_TP_CD, LOC_TP.WAREHOUSE);
            paramMap.put(NPAB136001Constant.DB_PARAM_WH_OWNR_CD, whOwnrCd);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            // QC#57659 Mod Start
            preparedStatement = ssmLlcClient.createPreparedStatement("getAvalWhOwnerInvty", paramMap, execParam);
            // QC#57659 Mod End
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ret = resultSet.getBigDecimal(NPAB136001Constant.INVTY_AVAL_QTY);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
        return ret;
    }

    /**
     * execute PR Update API
     */
    private NPZC103001PMsg executeNPZC1030(NPZC103001PMsg insrcPrPMsg) {
        NPZC103001 dPZC103001 = new NPZC103001();
        dPZC103001.execute(insrcPrPMsg, ONBATCH_TYPE.BATCH);

        return insrcPrPMsg;
    }

    /**
     * get Insourcing Rule
     * @return
     */
    private ArrayList<INSRC_RULETMsg> getInsourcingRule() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<INSRC_RULETMsg> ret = new ArrayList<INSRC_RULETMsg>();

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("getInsourcingRule", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                INSRC_RULETMsg rule = new INSRC_RULETMsg();
                ZYPEZDItemValueSetter.setValue(rule.insrcRuleNm, resultSet.getString(NPAB136001Constant.INSRC_RULE_NM));
                ZYPEZDItemValueSetter.setValue(rule.insrcRuleDtlNum, resultSet.getString(NPAB136001Constant.INSRC_RULE_DTL_NUM));
                ZYPEZDItemValueSetter.setValue(rule.prchReqRecTpCd, resultSet.getString(NPAB136001Constant.PRCH_REQ_REC_TP_CD));
                ZYPEZDItemValueSetter.setValue(rule.prchReqTpCd, resultSet.getString(NPAB136001Constant.PRCH_REQ_TP_CD));
                ZYPEZDItemValueSetter.setValue(rule.procrTpCd, resultSet.getString(NPAB136001Constant.PROCR_TP_CD));
                ZYPEZDItemValueSetter.setValue(rule.prchReqSrcTpCd, resultSet.getString(NPAB136001Constant.PRCH_REQ_SRC_TP_CD));
                ZYPEZDItemValueSetter.setValue(rule.destLocTpCd, resultSet.getString(NPAB136001Constant.DEST_LOC_TP_CD));
                ZYPEZDItemValueSetter.setValue(rule.destRtlWhCatgCd, resultSet.getString(NPAB136001Constant.DEST_RTL_WH_CATG_CD));
                ZYPEZDItemValueSetter.setValue(rule.techLineBizTpCd, resultSet.getString(NPAB136001Constant.TECH_LINE_BIZ_TP_CD));
                ZYPEZDItemValueSetter.setValue(rule.destInvtyLocCd, resultSet.getString(NPAB136001Constant.DEST_INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(rule.whCutOffTmApplyFlg, resultSet.getString(NPAB136001Constant.WH_CUT_OFF_TM_APPLY_FLG));
                ZYPEZDItemValueSetter.setValue(rule.insrcPrtyNum, resultSet.getString(NPAB136001Constant.INSRC_PRTY_NUM));
                // 08/29/2017 CITS S.Endo Add Sol#406(QC#19243) START
                ZYPEZDItemValueSetter.setValue(rule.mdseItemClsTpCd, resultSet.getString(NPAB136001Constant.MDSE_ITEM_CLS_TP_CD));
                // 08/29/2017 CITS S.Endo Add Sol#406(QC#19243) END
                ret.add(rule);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
        return ret;
    }

    /**
     * get Insourcing Rule Detail
     * @param insrcRuleDtlNum
     * @return
     */
    private ArrayList<INSRC_RULE_DTLTMsg> getInsourcingRuleDetail(String insrcRuleDtlNum) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<INSRC_RULE_DTLTMsg> ret = new ArrayList<INSRC_RULE_DTLTMsg>();

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB136001Constant.DB_PARAM_INSRC_RULE_DTL_NUM, insrcRuleDtlNum);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("getInsourcingRuleDetail", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                INSRC_RULE_DTLTMsg rule = new INSRC_RULE_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(rule.insrcRuleDtlNum, resultSet.getString(NPAB136001Constant.INSRC_RULE_DTL_NUM));
                ZYPEZDItemValueSetter.setValue(rule.insrcRuleDtlNm, resultSet.getString(NPAB136001Constant.INSRC_RULE_DTL_NM));
                ZYPEZDItemValueSetter.setValue(rule.srchSrcWhFlg, resultSet.getString(NPAB136001Constant.SRCH_SRC_WH_FLG));
                ZYPEZDItemValueSetter.setValue(rule.srchInsrcPlnFlg, resultSet.getString(NPAB136001Constant.SRCH_INSRC_PLN_FLG));
                ZYPEZDItemValueSetter.setValue(rule.srchCusaInvtyFlg, resultSet.getString(NPAB136001Constant.SRCH_CUSA_INVTY_FLG));
                ZYPEZDItemValueSetter.setValue(rule.srchCviInvtyFlg, resultSet.getString(NPAB136001Constant.SRCH_CVI_INVTY_FLG));
                ZYPEZDItemValueSetter.setValue(rule.srchWhOwnrInvtyFlg, resultSet.getString(NPAB136001Constant.SRCH_WH_OWNR_INVTY_FLG));
                ZYPEZDItemValueSetter.setValue(rule.srchWhOwnrCd, resultSet.getString(NPAB136001Constant.SRCH_WH_OWNR_CD));
                ZYPEZDItemValueSetter.setValue(rule.whOwnrDummyInvtyLocCd, resultSet.getString(NPAB136001Constant.WH_OWNR_DUMMY_INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(rule.srchSpecLocInvtyFlg, resultSet.getString(NPAB136001Constant.SRCH_SPEC_LOC_INVTY_FLG));
                ZYPEZDItemValueSetter.setValue(rule.srchSpecInvtyLocCd, resultSet.getString(NPAB136001Constant.SRCH_SPEC_INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(rule.cratPoFlg, resultSet.getString(NPAB136001Constant.CRAT_PO_FLG));
                ZYPEZDItemValueSetter.setValue(rule.prchReqApvlStsCd, resultSet.getString(NPAB136001Constant.PRCH_REQ_APVL_STS_CD));
                ZYPEZDItemValueSetter.setValue(rule.prchReqTpCd, resultSet.getString(NPAB136001Constant.PRCH_REQ_TP_CD));
                ZYPEZDItemValueSetter.setValue(rule.srchOrigItemFlg, resultSet.getString(NPAB136001Constant.SRCH_ORIG_ITEM_FLG));
                ZYPEZDItemValueSetter.setValue(rule.srchSupdItemFlg, resultSet.getString(NPAB136001Constant.SRCH_SUPD_ITEM_FLG));
                ZYPEZDItemValueSetter.setValue(rule.srchMdseItemRelnTpCd, resultSet.getString(NPAB136001Constant.SRCH_MDSE_ITEM_RELN_TP_CD));
                ZYPEZDItemValueSetter.setValue(rule.procrTpCd, resultSet.getString(NPAB136001Constant.PROCR_TP_CD));
                ZYPEZDItemValueSetter.setValue(rule.prchReqLineTpCd, resultSet.getString(NPAB136001Constant.PRCH_REQ_LINE_TP_CD));
                ZYPEZDItemValueSetter.setValue(rule.origLineFrzFlg, resultSet.getString(NPAB136001Constant.ORIG_LINE_FRZ_FLG));
                ZYPEZDItemValueSetter.setValue(rule.boNtfyReqFlg, resultSet.getString(NPAB136001Constant.BO_NTFY_REQ_FLG));
                // 08/29/2017 CITS S.Endo Add Sol#406(QC#19243) START
                ZYPEZDItemValueSetter.setValue(rule.whTrnsfBoReqFlg, resultSet.getString(NPAB136001Constant.WH_TRNSF_BO_REQ_FLG));
                ZYPEZDItemValueSetter.setValue(rule.boSrcRtlWhCd, resultSet.getString(NPAB136001Constant.BO_SRC_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(rule.boSrcRtlSwhCd, resultSet.getString(NPAB136001Constant.BO_SRC_RTL_SWH_CD));
                // 08/29/2017 CITS S.Endo Add Sol#406(QC#19243) END
                // QC#27660 Update. Add frzRefCmntTxt
                ZYPEZDItemValueSetter.setValue(rule.frzRefCmntTxt, resultSet.getString(NPAB136001Constant.FRZ_REF_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(rule.srchStltInvtyFlg, resultSet.getString("SRCH_STLT_INVTY_FLG"));
                // QC#61128 Add Start
                ZYPEZDItemValueSetter.setValue(rule.srchThirdPtyInvtyFlg, resultSet.getString("SRCH_THIRD_PTY_INVTY_FLG"));
                ZYPEZDItemValueSetter.setValue(rule.srchOrclInvtyFlg, resultSet.getString("SRCH_ORCL_INVTY_FLG"));
                // QC# 61128 Add End

                ret.add(rule);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
        return ret;
    }

    private ArrayList<INSRC_RULETMsg> findInsourcingRule(ArrayList<INSRC_RULETMsg> list, String prchReqRecTpCd, String prchReqTpCd, String procrTpCd, String mdseItemClsTpCd) {
        ArrayList<INSRC_RULETMsg> ret = new ArrayList<INSRC_RULETMsg>();
        for (INSRC_RULETMsg item : list) {
            // 09/19/2017 CITS S.Endo Mod Sol#406(QC#19243) START
            // if
            // ((prchReqRecTpCd.equals(item.prchReqRecTpCd.getValue()))
            // && (prchReqTpCd.equals(item.prchReqTpCd.getValue())) &&
            // (procrTpCd.equals(item.procrTpCd.getValue()))) {
            if ((prchReqRecTpCd.equals(item.prchReqRecTpCd.getValue())) && (prchReqTpCd.equals(item.prchReqTpCd.getValue())) && (procrTpCd.equals(item.procrTpCd.getValue()))) {
                if ((mdseItemClsTpCd.equals(item.mdseItemClsTpCd.getValue()))) {
                    ret.add(item);
                } else if (!ZYPCommonFunc.hasValue(item.mdseItemClsTpCd.getValue()) && !mdseItemClsTpCd.equals(item.mdseItemClsTpCd.getValue())) {
                    ret.add(item);
                }
            }
            // 09/19/2017 CITS S.Endo Mod Sol#406(QC#19243) END
        }
        return ret;
    }

    private ArrayList<INSRC_RULETMsg> findInsourcingRule(ArrayList<INSRC_RULETMsg> list, String prchReqRecTpCd, String procrTpCd) {
        ArrayList<INSRC_RULETMsg> ret = new ArrayList<INSRC_RULETMsg>();
        for (INSRC_RULETMsg item : list) {
            if ((prchReqRecTpCd.equals(item.prchReqRecTpCd.getValue())) && (!ZYPCommonFunc.hasValue(item.prchReqTpCd)) && (procrTpCd.equals(item.procrTpCd.getValue()))) {
                ret.add(item);
            }
        }
        return ret;
    }

    /**
     * get target PR header Number List
     * @return
     */
    private List<String> getTargetPrNumList() {

        ArrayList<String> prNumList = new ArrayList<String>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // get PR Header Number
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

            HashMap<String, Object> paramMap = new HashMap<String, Object>();

            paramMap.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB136001Constant.DB_PARAM_PRCH_REQ_STS_CD, ZYPCodeDataUtil.getVarCharConstValue(NPAB136001Constant.PRCH_REQ_STS_CD_OPEN, globalCompanyCode));
            paramMap.put(NPAB136001Constant.DB_PARAM_PRCH_REQ_APVL_FLG, ZYPConstant.FLG_ON_Y);
            paramMap.put(NPAB136001Constant.DB_PARAM_PRCH_REQ_REL_STS_CD_NC, PRCH_REQ_REL_STS.IN_COMPLETED);
            paramMap.put(NPAB136001Constant.DB_PARAM_PRCH_REQ_REL_STS_CD_ERR, PRCH_REQ_REL_STS.ERROR);
            // QC#55762
            paramMap.put("open", PRCH_REQ_LINE_STS.OPEN);
            paramMap.put("awaitingShipping", PRCH_REQ_LINE_STS.AWAITING_SHIPPING);
            paramMap.put("partiallyShipped", PRCH_REQ_LINE_STS.PARTIALLY_SHIPPED);
            paramMap.put("partiallyReceived", PRCH_REQ_LINE_STS.PARTIALLY_RECEIVED);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("getTargetPrNumList", paramMap, execParam);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                prNumList.add(resultSet.getString(NPAB136001Constant.PRCH_REQ_NUM));
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
        return prNumList;
    }

    /**
     * get PR details
     * @param prNum
     * @return
     */
    private ArrayList<PrInfoBean> getPrList(String prNum) {

        ArrayList<PrInfoBean> ret = new ArrayList<PrInfoBean>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // get PR Detail Info
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

            HashMap<String, Object> paramMap = new HashMap<String, Object>();

            paramMap.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB136001Constant.DB_PARAM_PRCH_REQ_STS_CD, ZYPCodeDataUtil.getVarCharConstValue(NPAB136001Constant.PRCH_REQ_STS_CD_OPEN, globalCompanyCode));
            paramMap.put(NPAB136001Constant.DB_PARAM_PRCH_REQ_APVL_FLG, ZYPConstant.FLG_ON_Y);
            paramMap.put(NPAB136001Constant.DB_PARAM_PRCH_REQ_REL_STS_CD_NC, PRCH_REQ_REL_STS.IN_COMPLETED);
            paramMap.put(NPAB136001Constant.DB_PARAM_PRCH_REQ_REL_STS_CD_ERR, PRCH_REQ_REL_STS.ERROR);
            paramMap.put(NPAB136001Constant.DB_PARAM_PRCH_REQ_NUM, prNum);
            // QC#55762
            paramMap.put("open", PRCH_REQ_LINE_STS.OPEN);
            paramMap.put("awaitingShipping", PRCH_REQ_LINE_STS.AWAITING_SHIPPING);
            paramMap.put("partiallyShipped", PRCH_REQ_LINE_STS.PARTIALLY_SHIPPED);
            paramMap.put("partiallyReceived", PRCH_REQ_LINE_STS.PARTIALLY_RECEIVED);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("getPRList", paramMap, execParam);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ret.add(getPRData(resultSet));
            }

            totalCommitCount += commitCount;

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
        return ret;
    }

    /**
     * get Primary SWH Code from Sub Warehouse Master
     * @param vndCd
     * @return
     */
    private String getPrtyLocCd(String rtlWhCd) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String ret = null;

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB136001Constant.DB_PARAM_TO_RTL_WH_CD, rtlWhCd);
            paramMap.put(NPAB136001Constant.DB_PARAM_PRTY_LOC_FLG, ZYPConstant.FLG_ON_Y);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("getPrtyLocCd", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ret = resultSet.getString(NPAB136001Constant.INVTY_LOC_CD);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return ret;
    }

    /**
     * SetLineSubNum
     * @param vndPMsg
     * @param cratPrPMsg
     * @return
     */
    private void setLineSubNum(NPZC103001PMsg vndPMsg, NPZC103001PMsg cratPrPMsg) {

        for (int i = 0; i < vndPMsg.prchReqInfo.getValidCount(); i++) {
            String prchReqNum = vndPMsg.prchReqNum.getValue();
            String prchReqLineNum = vndPMsg.prchReqInfo.no(i).prchReqLineNum.getValue();
            BigDecimal prchReqLineSubNum = vndPMsg.prchReqInfo.no(i).prchReqLineSubNum.getValue();

            for (int j = 0; j < cratPrPMsg.prchReqInfo.getValidCount(); j++) {
                // QC#61128 Mod Start
                //if (prchReqNum.equals(cratPrPMsg.prchReqInfo.no(j).trxRefNum.getValue()) && prchReqLineNum.equals(cratPrPMsg.prchReqInfo.no(j).trxRefLineNum.getValue())) {
                if (prchReqNum.equals(cratPrPMsg.prchReqInfo.no(j).trxRefNum.getValue()) && prchReqLineNum.equals(cratPrPMsg.prchReqInfo.no(j).trxRefLineNum.getValue()) && !ZYPCommonFunc.hasValue(cratPrPMsg.prchReqInfo.no(j).origPrchReqLineSubNum)) {
                // QC#61128 Mod End

                    ZYPEZDItemValueSetter.setValue(cratPrPMsg.prchReqInfo.no(j).origPrchReqLineSubNum, prchReqLineSubNum);
                    ZYPEZDItemValueSetter.setValue(cratPrPMsg.prchReqInfo.no(j).trxRefLineSubNum, prchReqLineSubNum.toPlainString());
                    // QC#61128 Add Start
                    break;
                    // QC#61128 Mod End
                }
            }
        }
    }

    @Override
    protected void termRoutine() {
        // Set EndCode and CommitCount
        setTermState(termCd, totalCommitCount, errorCount, totalCommitCount + errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NPAB136001().executeBatch(NPAB136001.class.getSimpleName());
    }

    private void sendErrMail() {
        S21Mail mail = new S21Mail(globalCompanyCode);
        // Get mail group
        S21MailGroup groupTo = new S21MailGroup(globalCompanyCode, NPAB136001Constant.ERR_MAIL_TO_GROUP_ID);
        // Get mail group
        S21MailGroup groupFrom = new S21MailGroup(globalCompanyCode, NPAB136001Constant.MAIL_FROM_GROUP_ID);

        // Set address
        List<S21MailAddress> toAddrList = groupTo.getMailAddress();
        if (toAddrList.isEmpty()) {
            // throw new
            // S21AbendException(NPAB136001Constant.NPZM0161E);
            return;
        }
        mail.setToAddressList(toAddrList);
        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
        mail.setFromAddress(fromAddrList.get(0));

        S21MailTemplate template = new S21MailTemplate(globalCompanyCode, NPAB136001Constant.ERR_MAIL_TEMPLATE_ID);
        if (template == null) {
            throw new S21AbendException(NPAB136001Constant.NPZM0161E);
        }
        // Set template parameter
        Date execDate = new Date();
        SimpleDateFormat sdfTimestamp = new SimpleDateFormat();
        sdfTimestamp.applyPattern(NPAB136001Constant.TIMESTAMP_FORMAT);

        String crlf = System.getProperty("line.separator");

        Map<String, Object> errorSenePrchCash = new HashMap<String, Object>();

        // Message
        StringBuffer msg = new StringBuffer();
        for (ErrorMessageBean bean : errMsgBeanList) {

            String cacheKey = bean.getPrchReqNum() + bean.getPrchReqLineNum() + bean.getErrMsg();

            if (errorSenePrchCash.containsKey(cacheKey)) {
                continue;
            }

            StringBuffer line = new StringBuffer();

            line.append(bean.getPrchReqNum());
            line.append(NPAB136001Constant.REQ_TO_LINE_SPACE);
            line.append(bean.getPrchReqLineNum());
            line.append(NPAB136001Constant.LINE_TO_ITEM_SPACE);
            line.append(bean.getMdseCd());
            line.append(NPAB136001Constant.ITEM_TO_QTY_BASE_SPACE);

            // adjustment space
            if (NPAB136001Constant.MDSE_LENGTH_8 == bean.getMdseCd().length()) {
                line.append(NPAB136001Constant.ITEM_ADJUSTMENT_SPACE);
            }

            String qty = " ";
            if (ZYPCommonFunc.hasValue(bean.getPrchReqQty())) {
                qty = bean.getPrchReqQty().toPlainString();
            }
            int adjSpace = qty.length() - 1;
            if (adjSpace > 0) {
                line.delete(line.length() - adjSpace, line.length());
            }

            line.append(qty);
            line.append(NPAB136001Constant.QTY_TO_ERRMSG_SPACE);
            line.append(bean.getErrMsg());

            // carriage return & line feed
            line.append(crlf);

            // line end.
            msg.append(line);

            errorSenePrchCash.put(cacheKey, cacheKey);

        }

        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_BATCH_ID, NPAB136001Constant.BATCH_ID);
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_MESSAGE, msg.toString());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_ERR_DATE, sdfTimestamp.format(execDate));

        // Set mail subject
        mail.setSubject(template.getSubject(NPAB136001Constant.ML_LANG), NPAB136001Constant.ML_LANG_CD);
        mail.setMailTemplate(template);

        // Post
        mail.postMail();
    }

    /**
     * duplicateItemMerge QC#21316 Add Method.
     * @param prList ArrayList<PrInfoBean>
     * @return merge prchInfoBean List ArrayList<PrInfoBean>
     */
    private ArrayList<PrInfoBean> duplicateItemMerge(ArrayList<PrInfoBean> prList) {

        // Key : Mdse_Cd
        HashMap<String, PrInfoBean> mergeList = new HashMap<String, PrInfoBean>();
        ArrayList<PrInfoBean> cancelLineList = new ArrayList<PrInfoBean>();
        boolean merged = false;

        for (PrInfoBean p : prList) {
            // QC#55042. Mod merge key
            String key = p.getDetailInfo().mdseCd.getValue() + p.getDetailInfo().srcInvtyLocCd.getValue();

            // QC#26662 Update.
            if (isInsourcedLine(p.getDetailInfo())) {
                continue;
            }

            if (mergeList.containsKey(key)) {
                merged = true;
                // Merge.
                PrInfoBean updBean = mergeList.get(key);
                PRCH_REQ_DTLTMsg updLine = updBean.getDetailInfo();
                PRCH_REQ_DTLTMsg currentLine = p.getDetailInfo();

                BigDecimal updOrderQty = updLine.prchReqQty.getValue().add(currentLine.prchReqQty.getValue());

                ZYPEZDItemValueSetter.setValue(updLine.prchReqQty, updOrderQty);
                ZYPEZDItemValueSetter.setValue(updLine.prchReqBalQty, updOrderQty);

                updBean.setDetailInfo(updLine);
                mergeList.put(key, updBean);

                // Cancel.
                String updLineNum = updLine.prchReqLineNum.getValue();
                PRCH_REQ_DTLTMsg cancelLine = p.getDetailInfo();
                // QC#27660 Update.
                ZYPEZDItemValueSetter.setValue(cancelLine.prchReqLineCmntTxt, //
                        addFirstLineCmnt("Merge Line#" + updLineNum, cancelLine.prchReqLineCmntTxt.getValue()));
                p.setDetailInfo(cancelLine);

                cancelLineList.add(p);
            } else {
                mergeList.put(key, p);
            }
        }

        ArrayList<PrInfoBean> mergedList = new ArrayList<PrInfoBean>();
        for (PrInfoBean p : mergeList.values()) {
            mergedList.add(p);
        }

        if (merged) {
            boolean updateError = false;
            // Merge. Call PR Update API. Mod QC#55042
            if (!updateMeargePR(mergedList, false)) {
                // Error
                updateError = true;
            }
            // Line Cancel. Call PR Update API.Mod QC#55042
            if (!updateCancelPR(cancelLineList, false)) {
                // Error
                updateError = true;
            }

            if (updateError) {
                rollback();
                mergedList.clear();
            } else {
                commit();
            }
        }

        return mergedList;
    }

    /**
     * isInsourcedLine
     * QC#26662 Add method.
     * @param line
     * @return true:insourced / false:new Line
     */
    private boolean isInsourcedLine(PRCH_REQ_DTLTMsg line) {

        if (!PRCH_REQ_REL_STS.IN_COMPLETED.equals(line.prchReqRelStsCd.getValue()) //
                && !PRCH_REQ_REL_STS.ERROR.equals(line.prchReqRelStsCd.getValue())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * updateMeargePR QC#21316 Add Method. Mod QC#55042
     * @param mergedList ArrayList<PrInfoBean>
     * @param isChoiceMerge boolean
     * @return true:success
     */
    private boolean updateMeargePR(ArrayList<PrInfoBean> mergedList, boolean isChoiceMerge) {

        NPZC103001 api = new NPZC103001();
        for (PrInfoBean p : mergedList) {
            NPZC103001PMsg param = new NPZC103001PMsg();

            // Header
            ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(param.xxModeCd, NPZC103001Constant.MODE_UPDATE);
            ZYPEZDItemValueSetter.setValue(param.eventId, NPZC103001Constant.EVENT_UPDATE);
            ZYPEZDItemValueSetter.setValue(param.prchReqNum, p.getHeaderInfo().prchReqNum);

            // Detail
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(0).prchReqLineNum, p.getDetailInfo().prchReqLineNum);
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(0).prchReqLineSubNum, p.getDetailInfo().prchReqLineSubNum);
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(0).prchReqQty, p.getDetailInfo().prchReqQty);
            param.prchReqInfo.setValidCount(1);

            api.execute(param, ONBATCH_TYPE.BATCH);

            if (S21ApiUtil.isXxMsgId(param)) {
                List<String> errList = S21ApiUtil.getXxMsgIdList(param);
                if (errList.size() > 0) {
                    for (String xxMsgId : errList) {
                        S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));
                        ErrorMessageBean errMsgBean = new ErrorMessageBean();
                        errMsgBean.setPrchReqNum(param.prchReqNum.getValue());
                        errMsgBean.setPrchReqLineNum(param.prchReqInfo.no(param.prchReqInfo.getValidCount() - 1).prchReqLineNum.getValue());
                        errMsgBean.setMdseCd(param.prchReqInfo.no(param.prchReqInfo.getValidCount() - 1).mdseCd.getValue());
                        errMsgBean.setPrchReqQty(param.prchReqInfo.no(param.prchReqInfo.getValidCount() - 1).prchReqQty.getValue());
                        errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage(xxMsgId));
                        errMsgBeanList.add(errMsgBean);

                        if (xxMsgId.endsWith("E")) {
                            return false;
                        }
                    }
                }
            }
            // QC#55042
            if (isChoiceMerge) {
                PRCH_REQ_DTLTMsg mergePRDtlTMsg = new PRCH_REQ_DTLTMsg();

                ZYPEZDItemValueSetter.setValue(mergePRDtlTMsg.glblCmpyCd, this.globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(mergePRDtlTMsg.prchReqNum, param.prchReqNum);
                ZYPEZDItemValueSetter.setValue(mergePRDtlTMsg.prchReqLineNum, param.prchReqInfo.no(0).prchReqLineNum);
                mergePRDtlTMsg = getPrntPrchReqDtlTMsg(mergePRDtlTMsg);

                if (mergePRDtlTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(mergePRDtlTMsg.prchReqBalQty, mergePRDtlTMsg.prchReqQty.getValue().subtract(mergePRDtlTMsg.prchReqRelQty.getValue()));
                    if (BigDecimal.ZERO.compareTo(mergePRDtlTMsg.prchReqBalQty.getValue()) == 0) {
                        ZYPEZDItemValueSetter.setValue(mergePRDtlTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.COMPLEATED);
                    }
                    EZDTBLAccessor.update(mergePRDtlTMsg);

                    if (mergePRDtlTMsg != null && !EZDTBLAccessor.RTNCD_NORMAL.equals(mergePRDtlTMsg.getReturnCode())) {
                        return false;
                    }
                }
            }

        }

        return true;
    }

    /**
     * updateCancelPR QC#21316 Add Method.Mod QC#55042
     * @param mergedList ArrayList<PrInfoBean>
     * @param isChoiceMerge boolean
     * @return true:success
     */
    private boolean updateCancelPR(ArrayList<PrInfoBean> cancelList, boolean isChoiceMerge) {

        NPZC103001 api = new NPZC103001();
        for (PrInfoBean p : cancelList) {
            NPZC103001PMsg param = new NPZC103001PMsg();

            // Header
            ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(param.xxModeCd, NPZC103001Constant.MODE_UPDATE);
            ZYPEZDItemValueSetter.setValue(param.eventId, NPZC103001Constant.EVENT_UPDATE);
            ZYPEZDItemValueSetter.setValue(param.prchReqNum, p.getHeaderInfo().prchReqNum);

            // Detail
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(0).prchReqLineNum, p.getDetailInfo().prchReqLineNum);
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(0).prchReqLineSubNum, p.getDetailInfo().prchReqLineSubNum);
            if (!isChoiceMerge) {
                ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(0).prchReqLineStsCd, PRCH_REQ_LINE_STS.CANCELLED);
            } else {
                ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(0).prchReqLineStsCd, p.getDetailInfo().prchReqLineStsCd);
                ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(0).prchReqQty, p.getDetailInfo().prchReqQty);
            }
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(0).prchReqLineCmntTxt, p.getDetailInfo().prchReqLineCmntTxt);
            param.prchReqInfo.setValidCount(1);

            api.execute(param, ONBATCH_TYPE.BATCH);

            if (S21ApiUtil.isXxMsgId(param)) {
                List<String> errList = S21ApiUtil.getXxMsgIdList(param);
                if (errList.size() > 0) {
                    for (String xxMsgId : errList) {
                        S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));
                        ErrorMessageBean errMsgBean = new ErrorMessageBean();
                        errMsgBean.setPrchReqNum(param.prchReqNum.getValue());
                        errMsgBean.setPrchReqLineNum(param.prchReqInfo.no(param.prchReqInfo.getValidCount() - 1).prchReqLineNum.getValue());
                        errMsgBean.setMdseCd(param.prchReqInfo.no(param.prchReqInfo.getValidCount() - 1).mdseCd.getValue());
                        errMsgBean.setPrchReqQty(param.prchReqInfo.no(param.prchReqInfo.getValidCount() - 1).prchReqQty.getValue());
                        errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage(xxMsgId));
                        errMsgBeanList.add(errMsgBean);

                        if (xxMsgId.endsWith("E")) {
                            return false;
                        }
                    }
                }
            }
            // QC#55042
            if (isChoiceMerge) {
                PRCH_REQ_DTLTMsg mergePRDtlTMsg = new PRCH_REQ_DTLTMsg();

                ZYPEZDItemValueSetter.setValue(mergePRDtlTMsg.glblCmpyCd, this.globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(mergePRDtlTMsg.prchReqNum, param.prchReqNum);
                ZYPEZDItemValueSetter.setValue(mergePRDtlTMsg.prchReqLineNum, param.prchReqInfo.no(0).prchReqLineNum);
                mergePRDtlTMsg = getPrntPrchReqDtlTMsg(mergePRDtlTMsg);

                if (mergePRDtlTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(mergePRDtlTMsg.prchReqBalQty, mergePRDtlTMsg.prchReqQty.getValue().subtract(mergePRDtlTMsg.prchReqRelQty.getValue()));
                    if (BigDecimal.ZERO.compareTo(mergePRDtlTMsg.prchReqBalQty.getValue()) == 0) {
                        ZYPEZDItemValueSetter.setValue(mergePRDtlTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.COMPLEATED);
                    }
                    EZDTBLAccessor.update(mergePRDtlTMsg);

                    if (mergePRDtlTMsg != null && !EZDTBLAccessor.RTNCD_NORMAL.equals(mergePRDtlTMsg.getReturnCode())) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    // QC#18418 T.Hakodate ADD START
    /**
     * getPrimaryVendorInfo
     * Mod. QC#29026
     * @param prInfo
     * @param mdseCd
     * @param isSupd
     * @param isInvty
     * @return
     */
    private NPZC113001PMsg getPrimaryVendorInfo(PrInfoBean prInfo, String mdseCd, boolean isSupd, boolean isInvty) {

        // QC#24918 Request vendor check
        PRCH_REQ_DTLTMsg prchReqDtlTMsg = prInfo.getDetailInfo();
        PRCH_REQTMsg prchReqTMsg = prInfo.getHeaderInfo();
        NPZC113001PMsg vendor = null;
        if (PRCH_REQ_REC_TP.TECH_REQUEST.equals(prInfo.getHeaderInfo().prchReqRecTpCd.getValue()) //
                && ZYPCommonFunc.hasValue(prchReqDtlTMsg.prntVndCd) //
                && ZYPCommonFunc.hasValue(prchReqDtlTMsg.vndCd)) {

            String bigDealNumber = getBigDealNumber(prchReqTMsg);
            // 1. searches by Big Deal#.
            if (ZYPCommonFunc.hasValue(bigDealNumber)) {
                Map<String, Object> queryParams = new HashMap<String, Object>();
                queryParams.put("glblCmpyCd", globalCompanyCode);
                queryParams.put("salesDate", salesDate);
                queryParams.put("mdseCd", mdseCd);
                queryParams.put("aslQlfyTpCd", "02"); // ASL_QLFY_TP_CD_BIG_DEAL_SPEC
                queryParams.put("aslQlfyRefCd", bigDealNumber);
                vendor = searchAslDtl(queryParams);
            }
            // 2. if the result is empty, searches by Ship To Customer.
            if (vendor == null && ZYPCommonFunc.hasValue(prchReqTMsg.shipToCustCd)) {
                Map<String, Object> queryParams = new HashMap<String, Object>();
                queryParams.put("glblCmpyCd", globalCompanyCode);
                queryParams.put("salesDate", salesDate);
                queryParams.put("mdseCd", mdseCd);
                queryParams.put("aslQlfyTpCd", "01"); // ASL_QLFY_TP_CD_CUST_SPEC
                queryParams.put("aslQlfyRefCd", prchReqTMsg.shipToCustCd.getValue());
                vendor = searchAslDtl(queryParams);
            }
            // 3. if the result is empty, searches by Vendor Code.
            if (vendor == null && ZYPCommonFunc.hasValue(prchReqDtlTMsg.vndCd)) {
                Map<String, Object> queryParams = new HashMap<String, Object>();
                queryParams.put("glblCmpyCd", globalCompanyCode);
                queryParams.put("salesDate", salesDate);
                queryParams.put("mdseCd", mdseCd);
                queryParams.put("vndCd", prchReqDtlTMsg.vndCd.getValue());
                queryParams.put("prntVndCd", prchReqDtlTMsg.prntVndCd.getValue());
                vendor = searchAslDtl(queryParams);
            }
            // 4. no data found asl
            if (vendor == null) {
                vendor = new NPZC113001PMsg();
                ZYPEZDItemValueSetter.setValue(vendor.xxErrFlg, ZYPConstant.FLG_ON_Y);
            }
        }

        if (vendor == null) {
            vendor = getPrimaryVendor(mdseCd);
        }

        if (0 < vendor.xxMsgIdList.getValidCount()) {
            rollback();
            // Mod. QC#29026
            errorProcess(prInfo, vendor.xxMsgIdList.no(0).xxMsgId.getValue(), mdseCd);
            return null;
        } else if (ZYPConstant.FLG_ON_Y.equals(vendor.xxErrFlg.getValue())) {
            // QC#29026
            // Mod. QC#29026
            if (isSupd) {
                NWZC206001PMsg nWZC206001PMsg = new NWZC206001PMsg();
                ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.glblCmpyCd, globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.slsDt, salesDate);
                ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.mdseCd, mdseCd);
                ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxModeCd, NWZC206001.SUPD_LATEST_MODE);
                ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxAvalPrchFlg, ZYPConstant.FLG_ON_Y);

                NWZC206001 nWZC206001 = new NWZC206001();
                nWZC206001.execute(nWZC206001PMsg, ONBATCH_TYPE.BATCH);

                List<String> errList = S21ApiUtil.getXxMsgIdList(nWZC206001PMsg);
                if (errList.size() > 0) {
                    for (String xxMsgId : errList) {
                        S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));
                        if (xxMsgId.endsWith("E")) {
                            return null;
                        }
                    }
                }
                if (ZYPCommonFunc.hasValue(nWZC206001PMsg.A.no(0).mdseCd) && !mdseCd.equals(nWZC206001PMsg.A.no(0).mdseCd.getValue())) {
                    vendor = getPrimaryVendorInfo(prInfo, nWZC206001PMsg.A.no(0).mdseCd.getValue(), false, false);
                }
            }

            if (isSupd && vendor == null) {
                rollback();
                errorProcess(prInfo, NPAB136001Constant.NPZM0272E, mdseCd);
                return null;
            } else if (vendor == null || ZYPConstant.FLG_ON_Y.equals(vendor.xxErrFlg.getValue())) {
                if (isInvty) {
                    return null;
                } else {
                    rollback();
                    errorProcess(prInfo, NPAB136001Constant.NPZM0272E, mdseCd);
                    return null;
                }
            } else {
                return vendor;
            }
        } else {

            if (!isInvty) {
                RCV_ASN_VNDTMsg rcvAsnVndTMsg = new RCV_ASN_VNDTMsg();
                ZYPEZDItemValueSetter.setValue(rcvAsnVndTMsg.glblCmpyCd, globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(rcvAsnVndTMsg.rcvAsnVndCd, vendor.vndCd.getValue());
                rcvAsnVndTMsg = (RCV_ASN_VNDTMsg) S21ApiTBLAccessor.findByKey(rcvAsnVndTMsg);

                if (rcvAsnVndTMsg == null || ZYPConstant.FLG_ON_Y.equals(rcvAsnVndTMsg.itemFlipFlg.getValue())) {

                    NWZC206001PMsg nWZC206001PMsg = new NWZC206001PMsg();
                    ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.glblCmpyCd, globalCompanyCode);
                    ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.slsDt, salesDate);
                    ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.mdseCd, mdseCd);
                    ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxModeCd, NWZC206001.SUPD_LATEST_MODE);
                    ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxAvalPrchFlg, ZYPConstant.FLG_ON_Y);

                    NWZC206001 nWZC206001 = new NWZC206001();
                    nWZC206001.execute(nWZC206001PMsg, ONBATCH_TYPE.BATCH);

                    List<String> errList = S21ApiUtil.getXxMsgIdList(nWZC206001PMsg);
                    if (errList.size() > 0) {
                        for (String xxMsgId : errList) {
                            S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));
                            if (xxMsgId.endsWith("E")) {
                                return null;
                            }
                        }
                    }

                    if (ZYPCommonFunc.hasValue(nWZC206001PMsg.A.no(0).mdseCd) && !mdseCd.equals(nWZC206001PMsg.A.no(0).mdseCd.getValue())) {
                        vendor = getPrimaryVendorInfo(prInfo, nWZC206001PMsg.A.no(0).mdseCd.getValue(), false, isInvty);
                    }

                }
            }
            // Mod. QC#29026 End
            // START 2023/02/27 E.Watabe [QC#61134, MOD]
            // QC#58274 Add
            //if (vendor != null && 
            //      Arrays.asList(this.cusaPartsOnlyPrTypes).contains(prInfo.getHeaderInfo().prchReqTpCd.getValue()) && //
            //      !Arrays.asList(this.cusaPartsOnlyVndCds).contains(vendor.vndCd.getValue())) {
            //  return null;
            //}
            if (vendor != null
                    && Arrays.asList(this.cusaPartsOnlyPrTypes).contains(prInfo.getHeaderInfo().prchReqTpCd.getValue())
                    && !Arrays.asList(this.cusaPartsOnlyVndCds).contains(vendor.vndCd.getValue())
                    && !PRCH_REQ_SRC_TP.TECH_REQUEST_ENTRY.equals(prInfo.getHeaderInfo().prchReqSrcTpCd.getValue())
                    && !PROCR_TP.SUPPLIER.equals(prInfo.getDetailInfo().procrTpCd.getValue())) {
                return null;
            }
            // END 2023/02/27 E.Watabe [QC#61134, MOD]
            return vendor;
        }
    }

    /**
     * getVendorSystemType
     * @param vndCd
     * @return
     */
    private String getVendorSystemType(String vndCd) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String ret = null;

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", globalCompanyCode);
            paramMap.put("vndCd", vndCd);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("getVendorSystemType", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ret = resultSet.getString("VND_SYS_TP_CD");
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return ret;
    }

    // QC#18418 T.Hakodate ADD END

    /**
     * QC#2366 Add method.
     */
    private boolean isHazmat(ArrayList<String> mdseCdList) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean hazmat = false;

        try {

            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

            HashMap<String, Object> paramMap = new HashMap<String, Object>();

            paramMap.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put("mdseCdList", mdseCdList);
            paramMap.put("flgOnY", ZYPConstant.FLG_ON_Y);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("countHazmatItem", paramMap, execParam);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int cnt = resultSet.getInt("CNT");

                if (0 < cnt) {
                    hazmat = true;
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return hazmat;
    }

    /**
     * selectShpgSvcLvlOfHazmat
     * QC#2366 Add method.
     * @param prchReqLineTp String
     * @param mdseCd String
     * @param whCd String
     * @param shpgSvcLvlCd String
     * @return shpgSvcLvlCd String
     */
    private String selectShpgSvcLvlOfHazmat(String prchReqLineTp, String mdseCd, String whCd, String shpgSvcLvlCd) {

        String slctShpgSvcLvl = shpgSvcLvlCd;

        if (PRCH_REQ_LINE_TP.LOCAL.equals(prchReqLineTp)) {
            if (!SHPG_SVC_LVL.CUSTOMER_PICK_UP.equals(slctShpgSvcLvl)) {
                // Ground

                String hazmatItemSSL = getHazmatItemSSL(mdseCd);

                if (hazmatItemSSL == null) {
                    hazmatItemSSL = SHPG_SVC_LVL.CUSTOMER_PICK_UP;
                }

                if (SHPG_SVC_LVL.CUSTOMER_PICK_UP.equals(hazmatItemSSL)) {
                    slctShpgSvcLvl = SHPG_SVC_LVL.CUSTOMER_PICK_UP;
                } else {
                    // Request:Ground and HazmatItem Ground

                    String rtlWhCd = whCd;
                    if (rtlWhCd.length() > NPAB136001Constant.RTL_WH_CD_LENGTH) {
                        rtlWhCd = rtlWhCd.substring(0, NPAB136001Constant.RTL_WH_CD_LENGTH);
                    }

                    if (isCSAWh(rtlWhCd)) {
                        slctShpgSvcLvl = SHPG_SVC_LVL.GROUND_SERVICE;
                    } else {
                        slctShpgSvcLvl = SHPG_SVC_LVL.CUSTOMER_PICK_UP;
                    }
                }
            }

            // START 2023/05/09 E.Watabe [QC#61084,DEL]
            //} else if (PRCH_REQ_LINE_TP.INSOURCED_DB.equals(prchReqLineTp) //
            //        || PRCH_REQ_LINE_TP.INSOURCED_PO.equals(prchReqLineTp)) {
            //  slctShpgSvcLvl = SHPG_SVC_LVL.GROUND_SERVICE;
            // END 2023/05/09 E.Watabe [QC#61084,DEL]
        }

        return slctShpgSvcLvl;
    }

    /**
     * getHazmatItemSSL
     * QC#2366 Add method.
     * @param mdseCd String
     * @return shpgSvcLvlCd String
     */
    private String getHazmatItemSSL(String mdseCd) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String resultShpgSvcLvl = null;

        try {

            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

            HashMap<String, Object> paramMap = new HashMap<String, Object>();

            paramMap.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put("mdseCd", mdseCd);
            paramMap.put("flgOnY", ZYPConstant.FLG_ON_Y);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("getHazmatItemSSL", paramMap, execParam);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Unique
                resultShpgSvcLvl = resultSet.getString("SHPG_SVC_LVL_CD");
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return resultShpgSvcLvl;
    }

    /**
     * isCSAWh
     * QC#2366 Add method.
     * @param rtlWhCd String
     * @return true:csa warehouse
     */
    private boolean isCSAWh(String rtlWhCd) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean csaWh = false;

        try {

            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

            HashMap<String, Object> paramMap = new HashMap<String, Object>();

            paramMap.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put("rtlWhCd", rtlWhCd);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("getWhOwnrCd", paramMap, execParam);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Unique
                String whOwnrCd = resultSet.getString("WH_OWNR_CD");

                if (WH_OWNR.CSA.equals(whOwnrCd)) {
                    csaWh = true;
                } else {
                    csaWh = false;
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return csaWh;
    }

    /**
     * getCarrCdForWillCall
     * QC#2366 Add method.
     * @param pMsg NPZC103001PMsg
     */
    private String getCarrCdForWillCall() {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String slctCarrCd = "";

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.globalCompanyCode);
            queryParam.put("shpgSvcLvlCd", SHPG_SVC_LVL.CUSTOMER_PICK_UP);
            preparedStatement = ssmLlcClient.createPreparedStatement("getCarrCdForWillCall", queryParam);

            resultSet = preparedStatement.executeQuery();

            ArrayList<String> carrCdList = new ArrayList<String>();
            while (resultSet.next()) {
                carrCdList.add(resultSet.getString("CARR_CD"));
            }

            String prfCarrForWillCall = ZYPCodeDataUtil.getVarCharConstValue("NPZA1030_PKUP_PRF_CARR", this.globalCompanyCode);

            if (carrCdList != null && carrCdList.contains(prfCarrForWillCall)) {
                slctCarrCd = prfCarrForWillCall;
            } else if (carrCdList != null) {
                // select first.
                slctCarrCd = carrCdList.get(0);
            } else if (ZYPCommonFunc.hasValue(prfCarrForWillCall)) {
                slctCarrCd = prfCarrForWillCall;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return slctCarrCd;
    }

    /**
     * checkSpgSvcCarrReln
     * QC#2366 Add method.
     * @param shpgSvcLvlCd String
     * @param carrCd String
     * @return true;OK
     */
    private boolean checkShpgSvcCarrReln(String shpgSvcLvlCd, String carrCd) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int cnt = 0;

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.globalCompanyCode);
            queryParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
            queryParam.put("carrCd", carrCd);
            preparedStatement = ssmLlcClient.createPreparedStatement("checkSpgSvcCarrReln", queryParam);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Unique
                cnt = resultSet.getInt("CNT");
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        if (cnt > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * customerShip QC#2366 Add method.
     * @param shipToCustCd String
     * @return true:customer ship
     */
    private boolean isCustomerShip(String shipToCustCd) {

        if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
            return false;
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BigDecimal shipToCustPk = BigDecimal.ZERO;
        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.globalCompanyCode);
            queryParam.put("shipToCustCd", shipToCustCd);
            queryParam.put("locGrpTech", LOC_GRP.TECHNICIAN);
            queryParam.put("rgtnOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);

            preparedStatement = ssmLlcClient.createPreparedStatement("getShipToCustPkForNotTech", queryParam);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Unique
                shipToCustPk = resultSet.getBigDecimal("SHIP_TO_CUST_PK");
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        if (shipToCustPk != null && BigDecimal.ZERO.compareTo(shipToCustPk) < 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * isPreferredCarrierFedexAndSetCarrCd
     * QC#2366 Add method.
     * @param rqstTechTocCd String
     * @param pMsg NPZC103001PMsg
     * @param i int
     * @return true:preferred Carrier Fedex
     */
    private boolean isPreferredCarrierFedexAndSetCarrCd(String rqstTechTocCd, NPZC103001PMsg pMsg, int i) {

        // START 2023/05/12 E.Watabe [QC#61083, ADD]
        if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(i).carrCd)) {
            return true;
        }
        // END 2023/05/12 E.Watabe [QC#61083, ADD]
        
        String prfCarrCd = getPreferredCarrierCodeForFedex(rqstTechTocCd);

        if (prfCarrCd == null || prfCarrCd.isEmpty()) {
            return false;
        } else {
            // Set CarrCd
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(i).carrCd, prfCarrCd);
            return true;
        }
    }

    /**
     * getPreferredCarrierCodeForFedex
     * QC#2366 Add method.
     * @param techTocCd String
     * @return preferred carrier code String
     */
    private String getPreferredCarrierCodeForFedex(String techTocCd) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String prfCarrCd = null;

        try {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.globalCompanyCode);
            queryParam.put("techTocCd", techTocCd);
            queryParam.put("carrTpFedex", CARR_TP.FEDEX);
            queryParam.put("vndTpObCarrier", VND_TP.OUTBOUND_CARRIER);

            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            preparedStatement = ssmLlcClient.createPreparedStatement("getPreferredCarrierFedexForWh", queryParam);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Unique
                prfCarrCd = resultSet.getString("PRF_CARR_CD");
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return prfCarrCd;
    }

    /**
     * setShipToCustHazmat
     * QC#2366 Add method.
     * @param pMsg NPZC103001PMsg
     * @return pMsg NPZC103001PMsg
     */
    private NPZC103001PMsg setShipToCustHazmat(NPZC103001PMsg pMsg) {

        String endOfShipToCustCd = getEndOfShipToCustCdForHazmat();
        String shipToCustCdHazmat = pMsg.rqstTechTocCd.getValue() + endOfShipToCustCd;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            HashMap<String, Object> queryParam = new HashMap<String, Object>();

            queryParam.put("glblCmpyCd", this.globalCompanyCode);
            queryParam.put("shipToCustCd", shipToCustCdHazmat);
            queryParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            preparedStatement = ssmLlcClient.createPreparedStatement("getShipToCustAddress", queryParam);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Unique
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, shipToCustCdHazmat);
                ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, resultSet.getString("LOC_NM"));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, resultSet.getString("FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, resultSet.getString("SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, resultSet.getString("THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToFrthLineAddr, resultSet.getString("FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstRefCmntTxt, resultSet.getString("FIRST_REF_CMNT_TXT"));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToScdRefCmntTxt, resultSet.getString("SCD_REF_CMNT_TXT"));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCtyAddr, resultSet.getString("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCntyNm, resultSet.getString("CNTY_NM"));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToProvNm, resultSet.getString("PROV_NM"));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToStCd, resultSet.getString("ST_CD"));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToPostCd, resultSet.getString("POST_CD"));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCtryCd, resultSet.getString("CTRY_CD"));
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
        return pMsg;
    }

    /**
     * getEndOfShipToCustCdForHazmat QC#2366 Add method.
     * @return end of ship to cust code for hazmat. String
     */
    private String getEndOfShipToCustCdForHazmat() {
        String endOfShipToCustCd = ZYPCodeDataUtil.getVarCharConstValue("END_OF_TECH_HAZMAT_CODE", this.globalCompanyCode);

        if (!ZYPCommonFunc.hasValue(endOfShipToCustCd)) {
            endOfShipToCustCd = "-HAZMAT";
        }

        return endOfShipToCustCd;
    }

    /**
     * isCustomerWHShip
     * QC#2366 Add method.
     * @param destInvtyLocCd
     * @return true:Customer
     */
    private boolean isCustomerWhShip(String destInvtyLocCd) {

        int cnt = 0;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            HashMap<String, Object> queryParam = new HashMap<String, Object>();

            queryParam.put("glblCmpyCd", this.globalCompanyCode);
            queryParam.put("invtyLocCd", destInvtyLocCd);
            queryParam.put("rtlWhCatgCustomer", RTL_WH_CATG.CUSTOMER);

            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            preparedStatement = ssmLlcClient.createPreparedStatement("cntCustomerWh", queryParam);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Unique
                cnt = resultSet.getInt("CNT");
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        if (cnt > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * isDBSWh
     * QC#2366 Update.
     * @param whCd String
     * @return true: DBS WH
     */
    private boolean isDBSWh(String whCd) {

        String rtlWhCd = whCd;
        if (rtlWhCd.length() > NPAB136001Constant.RTL_WH_CD_LENGTH) {
            rtlWhCd = rtlWhCd.substring(0, NPAB136001Constant.RTL_WH_CD_LENGTH);
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean dbsWh = false;

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

            HashMap<String, Object> paramMap = new HashMap<String, Object>();

            paramMap.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put("rtlWhCd", rtlWhCd);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("getWhOwnrCd", paramMap, execParam);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Unique
                String whOwnrCd = resultSet.getString("WH_OWNR_CD");

                if (WH_OWNR.DBS.equals(whOwnrCd)) {
                    dbsWh = true;
                } else {
                    dbsWh = false;
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return dbsWh;
    }

    /**
     * <p>
     * get Big Deal#.
     * </p>
     * @param tMsg PRCH_REQTMsg
     * @return Big Deal Number
     */
    private String getBigDealNumber(PRCH_REQTMsg tMsg) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String bigDealNum = "";

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

            Map<String, Object> queryParams = new HashMap<String, Object>();
            queryParams.put("glblCmpyCd", globalCompanyCode);
            queryParams.put("shipToCustCd", tMsg.shipToCustCd.getValue());

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("getBigDealNumber", queryParams, execParam);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Unique
                bigDealNum = resultSet.getString("BIG_DEAL_NUM");
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return bigDealNum;
    }

    /**
     * <p>
     * searchAslDtl
     * </p>
     * @param param NPZC113001PMsg
     * @return NPZC113001PMsg
     */
    private NPZC113001PMsg searchAslDtl(Map<String, Object> queryParams) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        NPZC113001PMsg vendor = null;

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("searchAslDetails", queryParams, execParam);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                vendor = new NPZC113001PMsg();
                ZYPEZDItemValueSetter.setValue(vendor.aslDtlPk, resultSet.getBigDecimal("ASL_DTL_PK"));
                ZYPEZDItemValueSetter.setValue(vendor.mdseCd, resultSet.getString("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(vendor.vndCd, resultSet.getString("VND_CD"));
                ZYPEZDItemValueSetter.setValue(vendor.prntVndCd, resultSet.getString("PRNT_VND_CD"));
                ZYPEZDItemValueSetter.setValue(vendor.vndUomCd, resultSet.getString("VND_UOM_CD"));
                ZYPEZDItemValueSetter.setValue(vendor.unitPrcAmt, resultSet.getBigDecimal("UNIT_PRC_AMT"));
                if (ZYPCommonFunc.hasValue(resultSet.getBigDecimal("VND_MIN_ORD_QTY"))) {
                    ZYPEZDItemValueSetter.setValue(vendor.vndMinOrdQty, resultSet.getBigDecimal("VND_MIN_ORD_QTY"));
                } else {
                    ZYPEZDItemValueSetter.setValue(vendor.vndMinOrdQty, BigDecimal.ONE);
                }
                if (ZYPCommonFunc.hasValue(resultSet.getBigDecimal("VND_INCR_ORD_QTY"))) {
                    ZYPEZDItemValueSetter.setValue(vendor.vndIncrOrdQty, resultSet.getBigDecimal("VND_INCR_ORD_QTY"));
                } else {
                    ZYPEZDItemValueSetter.setValue(vendor.vndIncrOrdQty, BigDecimal.ONE);
                }
                ZYPEZDItemValueSetter.setValue(vendor.splyItemNum, resultSet.getString("SPLY_ITEM_NUM"));
                ZYPEZDItemValueSetter.setValue(vendor.xxErrFlg, ZYPConstant.FLG_OFF_N);
                if (ZYPCommonFunc.hasValue(resultSet.getBigDecimal("VND_LT_DAYS_NUM"))) {
                    ZYPEZDItemValueSetter.setValue(vendor.vndLtDaysNum, resultSet.getBigDecimal("VND_LT_DAYS_NUM"));
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return vendor;
    }

    /**
     * addFirstLineCmnt
     * QC#27660 Add method.
     * @param newCmnt String
     * @param oldCmnt String
     * @return new setup comment.
     */
    private String addFirstLineCmnt(String newCmnt, String oldCmnt) {

        String cmnt = new String();

        if (oldCmnt == null || oldCmnt.isEmpty()) {
            cmnt = newCmnt;
        } else if (newCmnt == null || newCmnt.isEmpty()) {
            cmnt = oldCmnt;
        } else {

            if (newCmnt.equals(oldCmnt)) {
                cmnt = oldCmnt;
            } else {
                cmnt = newCmnt + "/" + oldCmnt;
            }
        }

        while (LENGTH_PRCH_REQ_LINE_CMNT_TXT < cmnt.length()) {
            // cut
            int lastIndex = cmnt.lastIndexOf("/");
            if (lastIndex < 0) {
                lastIndex = LENGTH_PRCH_REQ_LINE_CMNT_TXT;
            }

            cmnt = cmnt.substring(0, lastIndex);
        }

        return cmnt;
    }

    /**
     * duplicateChoiceItemMerge QC#55042 Add Method.
     * @param NPZC103001PMsg choicePmsg
     * @return boolean
     */
    private boolean duplicateChoiceItemMerge() {

        // Key : Mdse_Cd
        HashMap<String, NPZC103001_prchReqInfoPMsg> mergeList = new HashMap<String, NPZC103001_prchReqInfoPMsg>();
        HashMap<String, PrInfoBean> updBean = new HashMap<String, PrInfoBean>();

        ArrayList<PrInfoBean> cancelLineList = new ArrayList<PrInfoBean>();
        ArrayList<PrInfoBean> mergedList = new ArrayList<PrInfoBean>();

        boolean merged = false;
        int validCnt = this.insrcPrForChoicePMsg.prchReqInfo.getValidCount();
        int shiftCnt = 0;

        for (int i = 0; i < this.insrcPrForChoicePMsg.prchReqInfo.getValidCount(); i++) {

            NPZC103001_prchReqInfoPMsg linePMsg = this.insrcPrForChoicePMsg.prchReqInfo.no(i);

            String key = linePMsg.mdseCd.getValue();

            if (mergeList.containsKey(key)) {

                merged = true;
                validCnt--;

                // Insouced Line Merge.
                NPZC103001_prchReqInfoPMsg updList = mergeList.get(key);
                String updLineNum = updList.prchReqLineNum.getValue();

                BigDecimal choiceQty = linePMsg.prchReqQty.getValue();
                BigDecimal updOrderQty = updList.prchReqQty.getValue().add(linePMsg.prchReqQty.getValue());
                ZYPEZDItemValueSetter.setValue(updList.prchReqQty, updOrderQty);

                // Request Line Merge
                PrInfoBean updPr = null;
                if (updBean.containsKey(key)) {
                    updPr = updBean.get(key);
                    ZYPEZDItemValueSetter.setValue(updPr.getDetailInfo().prchReqQty, updPr.getDetailInfo().prchReqQty.getValue().add(linePMsg.prchReqQty.getValue()));
                } else {
                    updPr = new PrInfoBean();
                    PRCH_REQTMsg mergePRTMsg = new PRCH_REQTMsg();
                    PRCH_REQ_DTLTMsg mergePRDtlTMsg = new PRCH_REQ_DTLTMsg();

                    ZYPEZDItemValueSetter.setValue(mergePRDtlTMsg.glblCmpyCd, this.globalCompanyCode);
                    ZYPEZDItemValueSetter.setValue(mergePRDtlTMsg.prchReqNum, this.insrcPrForChoicePMsg.prchReqNum);
                    ZYPEZDItemValueSetter.setValue(mergePRDtlTMsg.prchReqLineNum, updList.origPrchReqLineNum);
                    mergePRDtlTMsg = getPrntPrchReqDtlTMsg(mergePRDtlTMsg);

                    updPr.setHeaderInfo(mergePRTMsg);
                    updPr.setDetailInfo(mergePRDtlTMsg);

                    // Header
                    ZYPEZDItemValueSetter.setValue(updPr.getHeaderInfo().prchReqNum, this.insrcPrForChoicePMsg.prchReqNum);
                    // Detail
                    ZYPEZDItemValueSetter.setValue(updPr.getDetailInfo().prchReqLineNum, updList.origPrchReqLineNum);
                    ZYPEZDItemValueSetter.setValue(updPr.getDetailInfo().prchReqLineSubNum, updList.origPrchReqLineSubNum);
                    ZYPEZDItemValueSetter.setValue(updPr.getDetailInfo().prchReqQty, updPr.getDetailInfo().prchReqQty.getValue().add(linePMsg.prchReqQty.getValue()));
                }

                mergedList.add(updPr);
                updBean.put(key, updPr);

                // Request Line Cancel.
                PrInfoBean cancelPr = new PrInfoBean();
                PRCH_REQTMsg cancelPRTMsg = new PRCH_REQTMsg();
                PRCH_REQ_DTLTMsg cancelPRDtlTMsg = new PRCH_REQ_DTLTMsg();

                ZYPEZDItemValueSetter.setValue(cancelPRDtlTMsg.glblCmpyCd, this.globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(cancelPRDtlTMsg.prchReqNum, this.insrcPrForChoicePMsg.prchReqNum);
                ZYPEZDItemValueSetter.setValue(cancelPRDtlTMsg.prchReqLineNum, linePMsg.origPrchReqLineNum);
                cancelPRDtlTMsg = getPrntPrchReqDtlTMsg(cancelPRDtlTMsg);

                cancelPr.setHeaderInfo(cancelPRTMsg);
                cancelPr.setDetailInfo(cancelPRDtlTMsg);

                // Header
                ZYPEZDItemValueSetter.setValue(cancelPr.getHeaderInfo().prchReqNum, this.insrcPrForChoicePMsg.prchReqNum);
                // Detail
                ZYPEZDItemValueSetter.setValue(cancelPr.getDetailInfo().prchReqLineNum, linePMsg.origPrchReqLineNum);
                ZYPEZDItemValueSetter.setValue(cancelPr.getDetailInfo().prchReqLineSubNum, linePMsg.origPrchReqLineSubNum);
                ZYPEZDItemValueSetter.setValue(cancelPr.getDetailInfo().prchReqLineCmntTxt, addFirstLineCmnt("Insourced CH Merge Line#" + updLineNum, linePMsg.prchReqLineCmntTxt.getValue()));

                if (cancelPRDtlTMsg != null && ZYPCommonFunc.hasValue(cancelPRDtlTMsg.prchReqQty) && cancelPRDtlTMsg.prchReqQty.getValue().compareTo(choiceQty) == 0) {
                    ZYPEZDItemValueSetter.setValue(cancelPr.getDetailInfo().prchReqLineStsCd, PRCH_REQ_LINE_STS.CANCELLED);
                } else {
                    ZYPEZDItemValueSetter.setValue(cancelPr.getDetailInfo().prchReqQty, cancelPr.getDetailInfo().prchReqQty.getValue().subtract(choiceQty));
                    ZYPEZDItemValueSetter.setValue(cancelPr.getDetailInfo().prchReqLineStsCd, cancelPRDtlTMsg.prchReqLineStsCd);
                }

                cancelLineList.add(cancelPr);
                linePMsg.clear();

            } else {

                EZDMsg.copy(linePMsg, null, this.insrcPrForChoicePMsg.prchReqInfo.no(shiftCnt), null);
                mergeList.put(key, this.insrcPrForChoicePMsg.prchReqInfo.no(shiftCnt));
                shiftCnt++;
            }
        }

        this.insrcPrForChoicePMsg.prchReqInfo.setValidCount(validCnt);

        if (merged) {

            if (!updateMeargePR(mergedList, true)) {
                // Error
                return false;
            }

            // Line Cancel. Call PR Update API.
            if (!updateCancelPR(cancelLineList, true)) {
                // Error
                return false;
            }
        }

        return true;
    }


    /**
     * getPrntPrchReqDtlTMsg
     * @param prchReqDtlTMsg PRCH_REQ_DTLTMsg
     * @return PRCH_REQ_DTLTMsg
     */
    private PRCH_REQ_DTLTMsg getPrntPrchReqDtlTMsg(PRCH_REQ_DTLTMsg prchReqDtlTMsg) {

        PRCH_REQ_DTLTMsg prntPrchReqDtlTMsg = new PRCH_REQ_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(prntPrchReqDtlTMsg.glblCmpyCd, prchReqDtlTMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(prntPrchReqDtlTMsg.prchReqNum, prchReqDtlTMsg.prchReqNum.getValue());
        ZYPEZDItemValueSetter.setValue(prntPrchReqDtlTMsg.prchReqLineNum, prchReqDtlTMsg.prchReqLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(prntPrchReqDtlTMsg.prchReqLineSubNum, BigDecimal.ZERO);

        return (PRCH_REQ_DTLTMsg) EZDTBLAccessor.findByKey(prntPrchReqDtlTMsg);
    }

    // QC#57659 Add start
    /**
     * Premium Rush Kick out process.
     * @param prInfo
     * @param reqQty
     * @return
     */
    private void preRushKickOut(PrInfoBean prInfo, BigDecimal reqQty) {

        String curTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmm");

        HashMap<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.globalCompanyCode);
        ssmParam.put("prchReqNum", prInfo.getHeaderInfo().prchReqNum.getValue());
        ssmParam.put("shipToCustCd", prInfo.getHeaderInfo().rqstTechTocCd.getValue());

        // get PR Customer
        Map<String, Object> shipToInfoMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getPrCustomer", ssmParam);

        String lclDate = "";
        String lclTime = "";
        if (shipToInfoMap != null && !shipToInfoMap.isEmpty()) {
            String postCd = (String) shipToInfoMap.get("POST_CD");
            String ctryCd = (String) shipToInfoMap.get("CTRY_CD");

            postCd = subStrPostCd(postCd);

            String time2 = getTimezoneTime(curTime, postCd, ctryCd, "yyyyMMddHHmm", "yyyyMMddHHmm", NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC);

            lclDate = time2.substring(0, NPAB136001Constant.DATE_LEN_YYYYMMDD);
            lclTime = time2.substring(NPAB136001Constant.DATE_LEN_YYYYMMDD, NPAB136001Constant.DATE_LEN_YYYYMMDD + NPAB136001Constant.TIME_LEN_HHMM);
        } else {
            lclDate = curTime.substring(0, NPAB136001Constant.DATE_LEN_YYYYMMDD);
            lclTime = curTime.substring(NPAB136001Constant.DATE_LEN_YYYYMMDD, NPAB136001Constant.DATE_LEN_YYYYMMDD + NPAB136001Constant.TIME_LEN_HHMM);
        }

        List<String> toMailAddrList = null;
        int dayOfTheWeek = ZYPDateUtil.getDayOfWeek(lclDate);

        // Alert type during regular hours (Monday 6:30am to Saturday 8:00am)
        if (dayOfTheWeek <= kickOutBeginDayOfWeek && kickOutBeginTime.compareTo(lclTime) < 0) {
            if (dayOfTheWeek == kickOutEndDayOfWeek && kickOutEndTime.compareTo(lclTime) < 0) {
                toMailAddrList = nonStandardHoursMailAddrList;
            } else {
                // Alert type during non-standard hours (Saturday
                // 8:01am to Monday 6:30am)
                toMailAddrList = regulerHoursMailAddrList;
            }
        } else {
            // Alert type during non-standard hours
            toMailAddrList = nonStandardHoursMailAddrList;
        }

        if (!ZYPCommonFunc.hasValue(fromAddrForKickOut) || (toMailAddrList == null || toMailAddrList.isEmpty())) {
            return;
        }

        S21Mail mail = new S21Mail(this.globalCompanyCode);

        // Set address
        S21MailAddress fromAddr = new S21MailAddress(this.globalCompanyCode, fromAddrForKickOut);
        List<S21MailAddress> toAddrList = new ArrayList<S21MailAddress>();
        for (String str : toMailAddrList) {
            toAddrList.add(new S21MailAddress(this.globalCompanyCode, str));
        }
        if (toAddrList.isEmpty()) {
            return;
        }
        mail.setFromAddress(fromAddr);
        mail.setToAddressList(toAddrList);

        S21MailTemplate template = new S21MailTemplate(globalCompanyCode, "NPAB1360M004");
        if (template == null) {
            throw new S21AbendException(NPAB136001Constant.NPZM0161E);
        }
        // Set template parameter
        Date execDate = new Date();
        SimpleDateFormat sdfDate = new SimpleDateFormat();
        sdfDate.applyPattern(NPAB136001Constant.DATE_FORMAT);
        SimpleDateFormat sdfTime = new SimpleDateFormat();
        sdfTime.applyPattern(NPAB136001Constant.TIME_FORMAT);

        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PRCH_REQ_NUM_SUBJECT, prInfo.getHeaderInfo().prchReqNum.getValue());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_TECH_TOC_CD, prInfo.getHeaderInfo().rqstTechTocCd.getValue());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PRCH_REQ_NUM, prInfo.getHeaderInfo().prchReqNum.getValue());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PRCH_REQ_LINE_NUM, prInfo.getDetailInfo().prchReqLineNum.getValue());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_MDSE_CD, prInfo.getDetailInfo().mdseCd.getValue());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PRCH_REQ_QTY, prInfo.getDetailInfo().prchReqQty.getValue().toString());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PRCH_REQ_BAL_QTY, reqQty.toString());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PRCH_REQ_CRAT_DT, prInfo.getHeaderInfo().prchReqCratDt.getValue());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_FSR_NUM, prInfo.getHeaderInfo().fsrNum.getValue());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_SVC_TASK_NUM, prInfo.getHeaderInfo().svcTaskNum.getValue());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_SVC_MACH_SER_NUM, prInfo.getHeaderInfo().svcMachSerNum.getValue());

        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_TODAY, sdfDate.format(execDate));
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_TIME, sdfTime.format(execDate));

        // Set mail subject
        mail.setSubject(template.getSubject(NPAB136001Constant.ML_LANG), NPAB136001Constant.ML_LANG_CD);
        mail.setMailTemplate(template);

        // Set mail subject
        mail.setSubject(template.getSubject(NPZC103001Constant.ML_LANG), NPZC103001Constant.ML_LANG_CD);
        mail.setMailTemplate(template);

        // Post
        mail.postMail();

    }

    /**
     * Premium Rush Kick out process.
     * @param prInfo
     * @param reqQty
     * @return
     */
    private void rushKickOut(PrInfoBean prInfo, BigDecimal reqQty) {

        String curTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmm");

        HashMap<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.globalCompanyCode);
        ssmParam.put("prchReqNum", prInfo.getHeaderInfo().prchReqNum.getValue());
        ssmParam.put("shipToCustCd", prInfo.getHeaderInfo().rqstTechTocCd.getValue());

        // get PR Customer
        Map<String, Object> shipToInfoMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getPrCustomer", ssmParam);

        List<String> toMailAddrList = null;

        // Send Mail.
        // Type = 0 : S21_PSN / Type = 1 : RTL_WH
        if (ZYPConstant.FLG_OFF_0.equals(rushKickOutMailType)) {
            toMailAddrList = new ArrayList<String>();
            toMailAddrList.add(prInfo.getS21PsnInfo().emlAddr.getValue());
        } else if (ZYPConstant.FLG_ON_1.equals(rushKickOutMailType)) {
            if (ZYPCommonFunc.hasValue(prInfo.getDetailInfo().destRtlWhCd)) {

                RTL_WHTMsg destRtlWhTMsg = getRtlWhTMsg(prInfo.getDetailInfo().destRtlWhCd.getValue());

                if (destRtlWhTMsg != null && ZYPCommonFunc.hasValue(destRtlWhTMsg.techMblMsgAddr)) {
                    toMailAddrList = new ArrayList<String>();
                    toMailAddrList.add(destRtlWhTMsg.techMblMsgAddr.getValue());
                }
            }
        } else if ("2".equals(rushKickOutMailType)) {
            String lclDate = "";
            String lclTime = "";
            if (shipToInfoMap != null && !shipToInfoMap.isEmpty()) {
                String postCd = (String) shipToInfoMap.get("POST_CD");
                String ctryCd = (String) shipToInfoMap.get("CTRY_CD");

                postCd = subStrPostCd(postCd);

                String time2 = getTimezoneTime(curTime, postCd, ctryCd, "yyyyMMddHHmm", "yyyyMMddHHmm", NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC);

                lclDate = time2.substring(0, NPAB136001Constant.DATE_LEN_YYYYMMDD);
                lclTime = time2.substring(NPAB136001Constant.DATE_LEN_YYYYMMDD, NPAB136001Constant.DATE_LEN_YYYYMMDD + NPAB136001Constant.TIME_LEN_HHMM);
            } else {
                lclDate = curTime.substring(0, NPAB136001Constant.DATE_LEN_YYYYMMDD);
                lclTime = curTime.substring(NPAB136001Constant.DATE_LEN_YYYYMMDD, NPAB136001Constant.DATE_LEN_YYYYMMDD + NPAB136001Constant.TIME_LEN_HHMM);
            }

            int dayOfTheWeek = ZYPDateUtil.getDayOfWeek(lclDate);

            // Alert type during regular hours (Monday 6:30am to Saturday 8:00am)
            if (dayOfTheWeek <= kickOutBeginDayOfWeek && kickOutBeginTime.compareTo(lclTime) < 0) {
                if (dayOfTheWeek == kickOutEndDayOfWeek && kickOutEndTime.compareTo(lclTime) < 0) {
                    toMailAddrList = nonStandardHoursMailAddrList;
                } else {
                    // Alert type during non-standard hours (Saturday
                    // 8:01am to Monday 6:30am)
                    toMailAddrList = regulerHoursMailAddrList;
                }
            } else {
                // Alert type during non-standard hours
                toMailAddrList = nonStandardHoursMailAddrList;
            }
        }

        // START 2023/09/19 G.Quan [QC#59207, MOD]
        //if (toMailAddrList == null) {
        if (!ZYPCommonFunc.hasValue(fromAddrForKickOut) || (toMailAddrList == null || toMailAddrList.isEmpty())) {
        // END 2023/09/19 G.Quan [QC#59207, MOD]
            return;
        }

        S21Mail mail = new S21Mail(this.globalCompanyCode);

        // Set address
        S21MailAddress fromAddr = new S21MailAddress(this.globalCompanyCode, fromAddrForKickOut);
        List<S21MailAddress> toAddrList = new ArrayList<S21MailAddress>();
        for (String str : toMailAddrList) {
            toAddrList.add(new S21MailAddress(this.globalCompanyCode, str));
        }

        mail.setFromAddress(fromAddr);
        mail.setToAddressList(toAddrList);

        S21MailTemplate template = new S21MailTemplate(globalCompanyCode, "NPAB1360M003");
        if (template == null) {
            throw new S21AbendException(NPAB136001Constant.NPZM0161E);
        }

        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PRCH_REQ_NUM_SUBJECT, prInfo.getHeaderInfo().prchReqNum.getValue());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PRCH_REQ_NUM, prInfo.getHeaderInfo().prchReqNum.getValue());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_MDSE_CD, prInfo.getDetailInfo().mdseCd.getValue());
        // 2023/09/08 QC#61704 START
        // template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PRCH_REQ_QTY, prInfo.getDetailInfo().prchReqQty.getValue().toString());
        template.setTemplateParameter(NPAB136001Constant.EMAIL_PARAM_PRCH_REQ_QTY, reqQty);
        // 2023/09/08 QC#61704 END

        // Set mail subject
        mail.setSubject(template.getSubject(NPZC103001Constant.ML_LANG), NPZC103001Constant.ML_LANG_CD);
        mail.setMailTemplate(template);

        // Post
        mail.postMail();

    }

    private static String subStrPostCd(String postCd) {
        if (!ZYPCommonFunc.hasValue(postCd)) {
            return null;
        }
        if (postCd.length() > 5) {
            return postCd.substring(0, 5);
        }
        return postCd;
    }

    private RTL_WHTMsg getRtlWhTMsg(String rtlWhCd) {
        RTL_WHTMsg tMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, rtlWhCd);
        tMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg == null && rtlWhCd.length() > 3) {
            tMsg = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, rtlWhCd.substring(0, 3));
            tMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(tMsg);
        }
        return tMsg;
    }

    /** Tech Info Bean */
    private class TechInfoBean {

        /** tech Mail Address */
        private String techMailAddr;

        /** Tech Cell Phone Number */
        private String techCellPhoNum;

        /** telephone number */
        private String telNum;

        /** Tech Mobile Message Address */
        private String techMblMsgAddr;

        /** Tech Toc Name */
        private String techTocNm;

        protected TechInfoBean(Map<String, Object> techInfoMap) {

            if (techInfoMap != null) {
                this.techMailAddr = getString((String) techInfoMap.get(NPZC103001Constant.COL_EMAIL));
                this.techCellPhoNum = getString((String) techInfoMap.get(NPZC103001Constant.COL_PHO_NUM));
                this.telNum = getString((String) techInfoMap.get(NPZC103001Constant.COL_TEL_NUM));
                this.techMblMsgAddr = getString((String) techInfoMap.get(NPZC103001Constant.COL_TECH_MBL_MSG_ADDR));
                this.techTocNm = getString((String) techInfoMap.get(NPZC103001Constant.COL_TECH_TOC_NM));
            } else {
                this.techMailAddr = "";
                this.techCellPhoNum = "";
                this.telNum = "";
                this.techMblMsgAddr = "";
                this.techTocNm = "";
            }
        }

        protected String getTechMailAddr() {
            return techMailAddr;
        }

        protected String getTechCellPhoNum() {
            return techCellPhoNum;
        }

        protected String getTelNum() {
            return telNum;
        }

        protected String getTechMblMsgAddr() {
            return techMblMsgAddr;
        }

        protected String getTechTocNm() {
            return techTocNm;
        }

        private String getString(String prm) {

            if (prm == null) {
                return "";
            } else {
                return prm;
            }
        }
    }

    private void sendMailChoice(LinkedHashMap<String, PrInfoBean> choicePrList) {

        PrInfoBean prInfo = null;
        List<PRCH_REQ_DTLTMsg> choiceLine = new ArrayList<PRCH_REQ_DTLTMsg>();
        for (Map.Entry<String, PrInfoBean> entry : choicePrList.entrySet()) {
            prInfo = choicePrList.get(entry.getKey());
            choiceLine.add(prInfo.getDetailInfo());
        }

        // get Tech Info
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        String crlf = System.getProperty("line.separator");

        TechInfoBean techInfoBean = getTechInfo(this.globalCompanyCode, prInfo.getHeaderInfo().rqstTechTocCd.getValue());
        String techCellPhoNum = techInfoBean.getTechCellPhoNum();
        String telNum = techInfoBean.getTelNum();
        String techMblMsgAddr = techInfoBean.getTechMblMsgAddr();
        String techTocNm = techInfoBean.getTechTocNm();

        String carrCd = null;
        String shpgSvcLvlCd = null;
        ssmParam.put("whOwnrCd", NPZC103001Constant.CHO);
        carrCd = choiceLine.get(0).carrCd.getValue();
        shpgSvcLvlCd = choiceLine.get(0).shpgSvcLvlCd.getValue();

        String svcLvlnm = getTplSvcLvlNm(this.globalCompanyCode, carrCd, shpgSvcLvlCd);

        S21Mail mail = new S21Mail(this.globalCompanyCode);
        // Get mail group
        S21MailGroup groupTo = new S21MailGroup(this.globalCompanyCode, NPZC103001Constant.MAIL_TO_GROUP_ID);
        // Get mail group
        S21MailGroup groupCc1 = new S21MailGroup(this.globalCompanyCode, NPZC103001Constant.MAIL_CC1_GROUP_ID);
        // Get mail group
        S21MailGroup groupFrom = new S21MailGroup(this.globalCompanyCode, NPZC103001Constant.MAIL_FROM_GROUP_ID);

        // Set address
        List<S21MailAddress> toAddrList = groupTo.getMailAddress();
        if (toAddrList.isEmpty()) {
            return;
        }
        mail.setToAddressList(toAddrList);
        List<S21MailAddress> ccAddrList1 = groupCc1.getMailAddress();
        mail.setCcAddressList(ccAddrList1);
        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
        mail.setFromAddress(fromAddrList.get(0));

        S21MailTemplate template = new S21MailTemplate(this.globalCompanyCode, NPZC103001Constant.MAIL_TEMPLATE_ID_PREMIUM_RUSH);

        // rqstTechTocCd
        template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_TECH_TOC_CD, prInfo.getHeaderInfo().rqstTechTocCd.getValue());

        // rqstTechTocNm
        if (ZYPCommonFunc.hasValue(techTocNm)) {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_TECH_TOC_NM, techTocNm);
        } else {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_TECH_TOC_NM, "");
        }

        // prchReqNum
        template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_PRCH_REQ_NUM, prInfo.getHeaderInfo().prchReqNum.getValue());
        // rqstRcvDt(dtl)
        SimpleDateFormat sdf = new SimpleDateFormat(NPZC103001Constant.RQST_RCV_DT_DATE_FORMAT);
        Date rqstRcvDt = null;
        if (ZYPCommonFunc.hasValue(choiceLine.get(0).rqstRcvDt.getValue())) {
            try {
                rqstRcvDt = sdf.parse(choiceLine.get(0).rqstRcvDt.getValue());
                sdf.applyPattern(NPZC103001Constant.EMAIL_DATE_FORMAT);
            } catch (ParseException e) {
                rqstRcvDt = null;
            }
            if (rqstRcvDt != null) {
                template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_RQST_RCV_DT, sdf.format(rqstRcvDt));
            }

        }
        // shpgSvcLvlCd(dtl)
        template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_SHPG_SVC_LVL_CD, svcLvlnm);
        // shipToLocNm
        template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_SHIP_TO_LOC_NM, prInfo.getHeaderInfo().shipToLocNm.getValue());
        // shipToFirstLineAddr&ScdLineAddr&ThirdLineAddr&FrthLineAddr
        StringBuffer sbShipToAddr = new StringBuffer();

        if (ZYPCommonFunc.hasValue(prInfo.getHeaderInfo().shipToFirstLineAddr.getValue())) {
            sbShipToAddr.append(prInfo.getHeaderInfo().shipToFirstLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(prInfo.getHeaderInfo().shipToScdLineAddr.getValue())) {
            if (sbShipToAddr.length() > 0) {
                sbShipToAddr.append(crlf).append(prInfo.getHeaderInfo().shipToScdLineAddr.getValue());
            } else {
                sbShipToAddr.append(prInfo.getHeaderInfo().shipToScdLineAddr.getValue());
            }
        }
        if (ZYPCommonFunc.hasValue(prInfo.getHeaderInfo().shipToThirdLineAddr.getValue())) {
            if (sbShipToAddr.length() > 0) {
                sbShipToAddr.append(crlf).append(prInfo.getHeaderInfo().shipToThirdLineAddr.getValue());
            } else {
                sbShipToAddr.append(prInfo.getHeaderInfo().shipToThirdLineAddr.getValue());
            }
        }
        if (ZYPCommonFunc.hasValue(prInfo.getHeaderInfo().shipToFrthLineAddr.getValue())) {
            if (sbShipToAddr.length() > 0) {
                sbShipToAddr.append(crlf).append(prInfo.getHeaderInfo().shipToFrthLineAddr.getValue());
            } else {
                sbShipToAddr.append(prInfo.getHeaderInfo().shipToFrthLineAddr.getValue());
            }
        }

        template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_SHIP_TO_FIRST_LINE_ADDR, sbShipToAddr.toString());

        if (ZYPCommonFunc.hasValue(prInfo.getHeaderInfo().prchReqNum)) {
            ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", this.globalCompanyCode);
            ssmParam.put("prchReqNum", prInfo.getHeaderInfo().prchReqNum.getValue());
            if (ZYPCommonFunc.hasValue(prInfo.getHeaderInfo().shipToCustCd)) {
                ssmParam.put("shipToCustCd", prInfo.getHeaderInfo().shipToCustCd.getValue());
            }

            // get PR Customer
            Map<String, Object> shipToInfoMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getPrCustomer", ssmParam);
            if (shipToInfoMap != null) {
                template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_SHIP_TO_LOC_NM, (String) shipToInfoMap.get(NPZC103001Constant.COL_LOC_NM));
                // shipToFirstLineAddr&ScdLineAddr&ThirdLineAddr&FrthLineAddr
                sbShipToAddr = new StringBuffer();

                if (ZYPCommonFunc.hasValue((String) shipToInfoMap.get(NPZC103001Constant.COL_FIRST_LINE_ADDR))) {
                    sbShipToAddr.append((String) shipToInfoMap.get(NPZC103001Constant.COL_FIRST_LINE_ADDR));
                }
                if (ZYPCommonFunc.hasValue((String) shipToInfoMap.get(NPZC103001Constant.COL_SCD_LINE_ADDR))) {
                    if (sbShipToAddr.length() > 0) {
                        sbShipToAddr.append(crlf).append((String) shipToInfoMap.get(NPZC103001Constant.COL_SCD_LINE_ADDR));
                    } else {
                        sbShipToAddr.append((String) shipToInfoMap.get(NPZC103001Constant.COL_SCD_LINE_ADDR));
                    }
                }
                if (ZYPCommonFunc.hasValue((String) shipToInfoMap.get(NPZC103001Constant.COL_THIRD_LINE_ADDR))) {
                    if (sbShipToAddr.length() > 0) {
                        sbShipToAddr.append(crlf).append((String) shipToInfoMap.get(NPZC103001Constant.COL_THIRD_LINE_ADDR));
                    } else {
                        sbShipToAddr.append((String) shipToInfoMap.get(NPZC103001Constant.COL_THIRD_LINE_ADDR));
                    }
                }
                if (ZYPCommonFunc.hasValue((String) shipToInfoMap.get(NPZC103001Constant.COL_FRTH_LINE_ADDR))) {
                    if (sbShipToAddr.length() > 0) {
                        sbShipToAddr.append(crlf).append((String) shipToInfoMap.get(NPZC103001Constant.COL_FRTH_LINE_ADDR));
                    } else {
                        sbShipToAddr.append((String) shipToInfoMap.get(NPZC103001Constant.COL_FRTH_LINE_ADDR));
                    }
                }
                template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_SHIP_TO_FIRST_LINE_ADDR, sbShipToAddr.toString());
                StringBuilder sbForCityStatePost = new StringBuilder();
                if (ZYPCommonFunc.hasValue((String) NPZC103001Constant.COL_CTY_ADDR)) {
                    sbForCityStatePost.append(shipToInfoMap.get((String) NPZC103001Constant.COL_CTY_ADDR));
                }
                if (ZYPCommonFunc.hasValue((String) NPZC103001Constant.COL_ST_CD)) {
                    sbForCityStatePost.append(NPZC103001Constant.SPACE_STRING).append(shipToInfoMap.get((String) NPZC103001Constant.COL_ST_CD));
                }
                if (ZYPCommonFunc.hasValue((String) NPZC103001Constant.COL_POST_CD)) {
                    sbForCityStatePost.append(NPZC103001Constant.SPACE_STRING).append(shipToInfoMap.get((String) NPZC103001Constant.COL_POST_CD));
                }
                // city/state/post
                template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_CITY_STATE_POST, sbForCityStatePost.toString());
            }

        }
        // techCellPhoNum/telNum
        if (ZYPCommonFunc.hasValue(techCellPhoNum)) {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_TECH_CELL_PHO_NUM, techCellPhoNum);
        } else {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_TECH_CELL_PHO_NUM, telNum);
        }
        // emlAddr(dtl)
        template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_EML_ADDR, techMblMsgAddr);

        // message(dtl)
        StringBuilder sbForMessage = new StringBuilder();
        for (int i = 0; i < choiceLine.size(); i++) {
            PRCH_REQ_DTLTMsg dtlTMsg = choiceLine.get(i);
            sbForMessage.append(NPZC103001Constant.EMAIL_PARAM_MATERIAL);
            sbForMessage.append(dtlTMsg.mdseCd.getValue());
            sbForMessage.append(crlf);
            sbForMessage.append(NPZC103001Constant.EMAIL_PARAM_QTY);
            sbForMessage.append(dtlTMsg.prchReqQty.getValueInt());
            sbForMessage.append(crlf);
        }

        template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_MESSAGE, sbForMessage.toString());
        template.setTemplateParameter(NPZC103001Constant.EMAIL_CTAC_PSN_NM, prInfo.getHeaderInfo().ctacPsnNm.getValue());

        // Set mail subject
        mail.setSubject(template.getSubject(NPZC103001Constant.ML_LANG), NPZC103001Constant.ML_LANG_CD);
        mail.setMailTemplate(template);

        // Post
        mail.postMail();

    }

    private TechInfoBean getTechInfo(String glblCmpyCd, String rqstTechTocCd) {

        // get Tech Info
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rqstTechTocCd", rqstTechTocCd);
        Map<String, Object> techInfoMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getTechInfo", ssmParam);

        return new TechInfoBean(techInfoMap);
    }

    private String getTplSvcLvlNm(String glblCmpyCd, String carrCd, String shpgSvcLvlCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("whOwnrCd", NPZC103001Constant.CHO);
        if (ZYPCommonFunc.hasValue(carrCd)) {
            ssmParam.put("carrCd", carrCd);
        }
        if (ZYPCommonFunc.hasValue(shpgSvcLvlCd)) {
            ssmParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
        }

        String shpgSvcLvlNm = (String) this.ssmBatchClient.queryObject("getSvcLvlNm", ssmParam);

        if (!ZYPCommonFunc.hasValue(shpgSvcLvlNm) && ZYPCommonFunc.hasValue(carrCd)) {
            shpgSvcLvlNm = getTplSvcLvlNm(glblCmpyCd, null, shpgSvcLvlCd);
        }
        if (!ZYPCommonFunc.hasValue(shpgSvcLvlNm) && ZYPCommonFunc.hasValue(shpgSvcLvlCd)) {
            shpgSvcLvlNm = getTplSvcLvlNm(glblCmpyCd, null, null);
        }

        return shpgSvcLvlNm;
    }

    private INSRC_RULE_DTLTMsg setSrchOrigItemSrcWhRule() {
        INSRC_RULE_DTLTMsg rule = new INSRC_RULE_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(rule.insrcRuleDtlNum, "PRE_RUSH-WILLCALL");
        ZYPEZDItemValueSetter.setValue(rule.insrcRuleDtlNm, "Tech Request Premium Rush Will Call");
        ZYPEZDItemValueSetter.setValue(rule.srchSrcWhFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(rule.srchInsrcPlnFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rule.srchCusaInvtyFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rule.srchCviInvtyFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rule.srchWhOwnrInvtyFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rule.srchWhOwnrCd, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rule.srchSpecLocInvtyFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rule.cratPoFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rule.srchOrigItemFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(rule.srchSupdItemFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rule.procrTpCd, PROCR_TP.WAREHOUSE);
        ZYPEZDItemValueSetter.setValue(rule.origLineFrzFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rule.boNtfyReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rule.whTrnsfBoReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rule.srchStltInvtyFlg, ZYPConstant.FLG_OFF_N);

        return rule;
    }

    /**
     * for MNX Insourcing Satellite Rule
     * @param prInfo
     * @param ruleDtl
     * @param mdseCd
     * @param reqQty
     * @param includeHazmat
     * @return
     */
    private BigDecimal srchStkProcDtlForInsrcStlt(PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String mdseCd, BigDecimal reqQty, boolean includeHazmat) {

        S21InfoLogOutput.println(String.format("[NPAB1360] Exec search process(Satellite). PR#%s-%s, Original MDSE#%s, Search MDSE#%s", prInfo.getHeaderInfo().prchReqNum.getValue(), prInfo.getDetailInfo().prchReqLineNum.getValue(), prInfo
                .getDetailInfo().mdseCd.getValue(), mdseCd));

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BigDecimal allocCount = BigDecimal.ZERO;

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();

            paramMap.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB136001Constant.DB_PARAM_TO_RTL_WH_CD, prInfo.getDetailInfo().destRtlWhCd.getValue());
            paramMap.put(NPAB136001Constant.DB_PARAM_INSRC_ENBL_FLG, ZYPConstant.FLG_ON_Y);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("getInsourcingSatellite", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                if (includeHazmat //
                        && !hazmatInsrcRuleList.contains(resultSet.getString(NPAB136001Constant.INSRC_PRCH_REQ_LINE_TP_CD))) {
                    continue;
                }

                String postCd = resultSet.getString("POST_CD");
                String ctryCd = resultSet.getString("CTRY_CD");

                postCd = subStrPostCd(postCd);
                String rtlWhCd = resultSet.getString("RTL_WH_CD");

                // Carrier cut off time
                String whCutOffTm = "";
                String carrCd = prInfo.getDetailInfo().carrCd.getValue();
                if (ZYPCommonFunc.hasValue(carrCd)) {
                    VNDTMsgArray vndTMsgArray = getVnd(this.globalCompanyCode, carrCd);
                    if (vndTMsgArray != null && vndTMsgArray.getValidCount() > 0) {
                        VNDTMsg vndTMsg = vndTMsgArray.no(0);
                        if (CARR_TP.FEDEX.equals(vndTMsg.carrTpCd.getValue())) {
                            whCutOffTm = resultSet.getString("FEDEX_CUT_OFF_TM");
                        } else if (CARR_TP.UPS.equals(vndTMsg.carrTpCd.getValue())) {
                            whCutOffTm = resultSet.getString("UPS_CUT_OFF_TM");
                        } else {
                            whCutOffTm = resultSet.getString("WH_CUT_OFF_TM");
                        }
                    }
                } else {
                    whCutOffTm = resultSet.getString("WH_CUT_OFF_TM");
                }

                BigDecimal retVal = srchStkProcDtlForInsrcStltSub(prInfo, ruleDtl, postCd, ctryCd, whCutOffTm, rtlWhCd, mdseCd, reqQty, includeHazmat);

                if (retVal != null) {
                    allocCount = allocCount.add(retVal);
                    reqQty = reqQty.subtract(retVal);

                    if (reqQty.compareTo(BigDecimal.ZERO) <= 0) {
                        break;
                    }
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return allocCount;
    }

    private BigDecimal srchStkProcDtlForInsrcStltSub(PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String postCd, String ctryCd, String whCutOffTm, String rtlWhCd, String mdseCd, BigDecimal reqQty, boolean includeHazmat)
            throws SQLException {

        if (ZYPCommonFunc.hasValue(whCutOffTm)) {
            // check cut off time.
            String currentTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmm"); // Timezone EST(EDT)
            String lclTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmm");
            lclTime = getTimezoneTime(lclTime, postCd, ctryCd, "yyyyMMddHHmm", "yyyyMMddHHmm", NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC);
            if (ZYPCommonFunc.hasValue(lclTime)) {
                lclTime = lclTime.substring(0, NPAB136001Constant.DATE_LEN_YYYYMMDD) + whCutOffTm;
                lclTime = getTimezoneTime(lclTime, postCd, ctryCd, "yyyyMMddHHmm", "yyyyMMddHHmm", NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS);

                if (lclTime.compareTo(currentTime) < 0) {
                    return null;
                }
            }
        } else {
            return null;
        }

        BigDecimal avaQty = getAvalWhInvty(mdseCd, rtlWhCd + prInfo.getDetailInfo().srcRtlSwhCd.getValue());

        if (avaQty == null) {
            return null;
        }

        if (avaQty.compareTo(BigDecimal.ZERO) <= 0) {
            return null;
        }

        BigDecimal prchReqQty = null;

        if (avaQty.compareTo(reqQty) >= 0) {
            prchReqQty = reqQty;

        } else {
            prchReqQty = avaQty;
        }

        // get primary SWH from TO_RTL_WH_CD
        String invtyLocCd = getPrtyLocCd(rtlWhCd);

        createNPZC103001PMsgCreate(insrcPrForInsrcPlnPMsg, prInfo, ruleDtl, ruleDtl.prchReqLineTpCd.getValue(), invtyLocCd, mdseCd, prchReqQty, includeHazmat);

        if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.origLineFrzFlg.getValue())) {
            freeze(prInfo, ruleDtl);
        }

        if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.boNtfyReqFlg.getValue())) {
            // START 2023/06/02 S.Dong [QC#55629, MOD]
            //sendMail(prInfo, reqQty, null, null, null);
            sendMail(prInfo, reqQty, null, null, null, null);
            // END 2023/06/02 S.Dong [QC#55629, MOD]
        }

        return prchReqQty;
    }

    private VNDTMsgArray getVnd(String glblCmpyCd, String vndCd) {
        VNDTMsg tMsg = new VNDTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("vndCd01", vndCd);
        return (VNDTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
    }

    private NLBI1410_01TMsg createNLBI1410_01_PR(BigDecimal trxId) {

        // QC#57659-2
        NLBI1410_01TMsg tMsg = null;
        PrInfoBean prInfo = null;
        for (Map.Entry<String, PrInfoBean> entry : this.dbsPrInfoMapList.entrySet()) {
            prInfo = this.dbsPrInfoMapList.get(entry.getKey());
            break;
        }
        // QC#57659-2
        NPZC103001_prchReqInfoPMsg prInfoMsg = null;
        for (int i = 0; i < insrcPrForSpecWhPMsg.prchReqInfo.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(insrcPrForSpecWhPMsg.prchReqInfo.no(i).soNum)) {
                prInfoMsg = insrcPrForSpecWhPMsg.prchReqInfo.no(i);
                break;
            }
        }
        // QC#57659-2
        if (prInfo != null && prInfoMsg != null) {
            tMsg = new NLBI1410_01TMsg();

            // INTERFACE_ID
            tMsg.interfaceId.setValue("NPAB1411");
            // TRANSACTION_ID
            tMsg.transactionId.setValue(trxId);
            // SEGMENT_ID
            tMsg.segmentId.setValue(1);
            // UNIT_ID
            tMsg.unitId.setValue(1);
            // SEQ_NUMBER
            tMsg.seqNumber.setValue(1);
            // WMS_REC_ID
            ZYPEZDItemValueSetter.setValue(tMsg.wmsRecId, "01");
            // TPL_FROM_PTNR_ID
            ZYPEZDItemValueSetter.setValue(tMsg.tplFromPtnrId, "MNX");
            // TPL_TO_PTNR_ID
            ZYPEZDItemValueSetter.setValue(tMsg.tplToPtnrId, "OCE");

            // REQ_DT_TM_TS_TXT
            if (PRCH_REQ_TP.PREMIUM_RUSH.equals(prInfo.getHeaderInfo().prchReqTpCd.getValue()) && SHPG_SVC_LVL.SCHD_DELIVERY.equals(prInfo.getDetailInfo().shpgSvcLvlCd.getValue())) {

                // Change Timezone
                String rqstRcvDt = prInfo.getDetailInfo().rqstRcvDt.getValue();
                String rqstRcvTm = prInfo.getDetailInfo().rqstRcvTm.getValue() + "00";
                String rqstRcvDtTm = rqstRcvDt + rqstRcvTm + "000";

                ZYPEZDItemValueSetter.setValue(tMsg.reqDtTmTsTxt, rqstRcvDtTm);
            }
            // QC#57659-2
            // ORD_ID_TXT
            ZYPEZDItemValueSetter.setValue(tMsg.ordIdTxt, prInfoMsg.soNum);
            // Get TPL_CARR_CD and TPL_SVC_LVL_CD
            Map<String, Object> tplCarrSvcLvl = null;
            String carrCd = prInfo.getDetailInfo().carrCd.getValue();
            String shpgSvcLvlCd = prInfo.getDetailInfo().shpgSvcLvlCd.getValue();
            tplCarrSvcLvl = getTplCarrSvcLvlWithEffort(this.globalCompanyCode, WH_OWNR.MNX, carrCd, shpgSvcLvlCd);
            // CARR_CD, SHPG_SVC_LVL_CD
            if (tplCarrSvcLvl != null) {
                // CARR_CD
                ZYPEZDItemValueSetter.setValue(tMsg.carrCd, (String) tplCarrSvcLvl.get("TPL_CARR_CD"));
                // SHPG_SVC_LVL_CD
                ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, (String) tplCarrSvcLvl.get("TPL_SVC_LVL_CD"));
                // CARR_CD
                ZYPEZDItemValueSetter.setValue(tMsg.tplCarrNm, (String) tplCarrSvcLvl.get("TPL_CARR_NM"));
                // TPL_SVC_LVL_NM
                ZYPEZDItemValueSetter.setValue(tMsg.tplSvcLvlNm, (String) tplCarrSvcLvl.get("TPL_SVC_LVL_NM"));
                // TPL_SPCL_SVC_NM
                ZYPEZDItemValueSetter.setValue(tMsg.tplSpclSvcNm, (String) tplCarrSvcLvl.get("TPL_SPCL_SVC_NM"));
            }
            // TPL_ACCT_TXT
            ZYPEZDItemValueSetter.setValue(tMsg.tplAcctTxt, "OCE");

            // Ship to Information
            HashMap<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", this.globalCompanyCode);
            ssmParam.put("prchReqNum", prInfo.getHeaderInfo().prchReqNum.getValue());
            if (ZYPCommonFunc.hasValue(prInfo.getHeaderInfo().shipToCustCd)) {
                ssmParam.put("shipToCustCd", prInfo.getHeaderInfo().shipToCustCd.getValue());
                // SHIP_TO_CUST_CD
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, prInfo.getHeaderInfo().shipToCustCd.getValue());
            } else {
                ssmParam.put("shipToCustCd", prInfo.getDetailInfo().destRtlWhCd.getValue());
                // SHIP_TO_CUST_CD
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, prInfo.getDetailInfo().destRtlWhCd.getValue());
            }
            Map<String, Object> shiptoCustInfo = (Map<String, Object>) this.ssmBatchClient.queryObject("getPrCustomer", ssmParam);

            // SHIP_NM_ALL_TXT
            if (ZYPCommonFunc.hasValue(prInfo.getHeaderInfo().shipToLocNm)) {
                ZYPEZDItemValueSetter.setValue(tMsg.shipNmAllTxt, prInfo.getHeaderInfo().shipToLocNm);
            } else if (shiptoCustInfo != null && shiptoCustInfo.get("LOC_NM") != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.shipNmAllTxt, (String) shiptoCustInfo.get("LOC_NM"));
            }

            // SHIP_ADDR_ALL_TXT
            String shipAddrAllText = "";
            if (ZYPCommonFunc.hasValue(prInfo.getHeaderInfo().shipToFirstLineAddr)) {
                shipAddrAllText += prInfo.getHeaderInfo().shipToFirstLineAddr.getValue();

                if (ZYPCommonFunc.hasValue(prInfo.getHeaderInfo().shipToScdLineAddr)) {
                    shipAddrAllText += prInfo.getHeaderInfo().shipToScdLineAddr.getValue();
                }
                if (ZYPCommonFunc.hasValue(prInfo.getHeaderInfo().shipToThirdLineAddr)) {
                    shipAddrAllText += prInfo.getHeaderInfo().shipToThirdLineAddr.getValue();
                }
                if (ZYPCommonFunc.hasValue(prInfo.getHeaderInfo().shipToFrthLineAddr)) {
                    shipAddrAllText += prInfo.getHeaderInfo().shipToFrthLineAddr.getValue();
                }
            } else if (shiptoCustInfo != null && shiptoCustInfo.get("FIRST_LINE_ADDR") != null) {
                shipAddrAllText += (String) shiptoCustInfo.get("FIRST_LINE_ADDR");

                if (shiptoCustInfo.get("SCD_LINE_ADDR") != null) {
                    shipAddrAllText += (String) shiptoCustInfo.get("SCD_LINE_ADDR");
                }
                if (shiptoCustInfo.get("THIRD_LINE_ADDR") != null) {
                    shipAddrAllText += (String) shiptoCustInfo.get("THIRD_LINE_ADDR");
                }
                if (shiptoCustInfo.get("FRTH_LINE_ADDR") != null) {
                    shipAddrAllText += (String) shiptoCustInfo.get("FRTH_LINE_ADDR");
                }
            }
            ZYPEZDItemValueSetter.setValue(tMsg.shipAddrAllTxt, shipAddrAllText);

            // SHIP_CTY_TXT
            if (ZYPCommonFunc.hasValue(prInfo.getHeaderInfo().shipToCtyAddr)) {
                ZYPEZDItemValueSetter.setValue(tMsg.shipCtyTxt, prInfo.getHeaderInfo().shipToCtyAddr);
            } else if (shiptoCustInfo != null && shiptoCustInfo.get("CTY_ADDR") != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.shipCtyTxt, (String) shiptoCustInfo.get("CTY_ADDR"));
            }
            // SHIP_ST_OR_PROV_TXT
            if (ZYPCommonFunc.hasValue(prInfo.getHeaderInfo().shipToStCd)) {
                ZYPEZDItemValueSetter.setValue(tMsg.shipStTxt, prInfo.getHeaderInfo().shipToStCd);
            } else if (shiptoCustInfo != null && shiptoCustInfo.get("ST_CD") != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.shipStTxt, (String) shiptoCustInfo.get("ST_CD"));
            }
            // SHIP_ZIP_OR_POST_CD_TXT
            if (ZYPCommonFunc.hasValue(prInfo.getHeaderInfo().shipToPostCd)) {
                ZYPEZDItemValueSetter.setValue(tMsg.shipPostTxt, prInfo.getHeaderInfo().shipToPostCd);
            } else if (shiptoCustInfo != null && shiptoCustInfo.get("POST_CD") != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.shipPostTxt, (String) shiptoCustInfo.get("POST_CD"));
            }

            // SHIP_CTRY_TXT
            if (ZYPCommonFunc.hasValue(prInfo.getHeaderInfo().shipToCtryCd)) {
                ZYPEZDItemValueSetter.setValue(tMsg.shipCtryTxt, prInfo.getHeaderInfo().shipToCtryCd);
            } else if (shiptoCustInfo != null && shiptoCustInfo.get("CTRY_CD") != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.shipCtryTxt, (String) shiptoCustInfo.get("CTRY_CD"));
            }

            // Get Technician WH info
            RTL_WHTMsg rtlWhTMsg = null;
            if (ZYPCommonFunc.hasValue(prInfo.getDetailInfo().destRtlWhCd)) {
                rtlWhTMsg = getRtlWhTMsg(prInfo.getDetailInfo().destRtlWhCd.getValue());
            }

            // SHIP_CTAC_NM_TXT
            if (ZYPCommonFunc.hasValue(prInfo.getHeaderInfo().ctacPsnNm)) {
                int digit = tMsg.getAttr("shipCtacNmTxt").getDigit();
                String ctacPsnNm = prInfo.getHeaderInfo().ctacPsnNm.getValue();
                if (ctacPsnNm.length() > digit) {
                    ctacPsnNm = ctacPsnNm.substring(0, digit);
                }
             // START 2021/01/05 [QC#58176,MOD]
//                ZYPEZDItemValueSetter.setValue(tMsg.shipCtacNmTxt, prInfo.getHeaderInfo().ctacPsnNm.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.shipCtacNmTxt, ctacPsnNm);
             // END 2021/01/05 [QC#58176,MOD]
            } else if (rtlWhTMsg != null) {
                int digit = tMsg.getAttr("shipCtacNmTxt").getDigit();
                String ctacPsnNm = rtlWhTMsg.rtlWhNm.getValue();
                if (ctacPsnNm.length() > digit) {
                    ctacPsnNm = ctacPsnNm.substring(0, digit);
                }
                ZYPEZDItemValueSetter.setValue(tMsg.shipCtacNmTxt, ctacPsnNm);
            }

            // SHIP_PHO_NUM_TXT
            if (rtlWhTMsg != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.shipPhoNumTxt, rtlWhTMsg.telNum);
            } else if (shiptoCustInfo != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.shipPhoNumTxt, (String) shiptoCustInfo.get("TEL_NUM"));
            }

            // ORD_CMNT_ALL_TXT
            if (ZYPCommonFunc.hasValue(prInfo.getHeaderInfo().delyAddlCmntTxt)) {
                String delyAddlCmntTxt = prInfo.getHeaderInfo().delyAddlCmntTxt.getValue().replaceAll("\r\n", " ");
                ZYPEZDItemValueSetter.setValue(tMsg.ordCmntAllTxt, delyAddlCmntTxt);
            }
            // ORIG_ORD_QTY_TXT
            BigDecimal totQty = BigDecimal.ZERO;
            for (Map.Entry<String, PrInfoBean> entry : this.dbsPrInfoMapList.entrySet()) {
                prInfo = this.dbsPrInfoMapList.get(entry.getKey());
                totQty = totQty.add(prInfo.getDetailInfo().prchReqQty.getValue());
            }
            ZYPEZDItemValueSetter.setValue(tMsg.origOrdQtyTxt, totQty.toPlainString());

            // set from info
            ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", this.globalCompanyCode);
            ssmParam.put("prchReqNum", prInfo.getHeaderInfo().prchReqNum.getValue());
            if (ZYPCommonFunc.hasValue(prInfo.getDetailInfo().srcInvtyLocCd)) {
                ssmParam.put("shipToCustCd", prInfo.getDetailInfo().srcInvtyLocCd.getValue());
            }
            Map<String, Object> shipFromCustInfo = (Map<String, Object>) this.ssmBatchClient.queryObject("getPrCustomer", ssmParam);
            if (shipFromCustInfo != null) {
                // SVC_TP_TXT
                ZYPEZDItemValueSetter.setValue(tMsg.svcTpTxt, "LOC");
                // SHIP_FROM_NM_ALL_TXT
                ZYPEZDItemValueSetter.setValue(tMsg.shipFromNmAllTxt, (String) shipFromCustInfo.get("LOC_NM"));
                // SHIP_FROM_ADDR_ALL_TXT
                String shipFromAddrAllText = "";
                if (ZYPCommonFunc.hasValue((String) shipFromCustInfo.get("FIRST_LINE_ADDR"))) {
                    shipFromAddrAllText += (String) shipFromCustInfo.get("FIRST_LINE_ADDR");

                    if (ZYPCommonFunc.hasValue((String) shipFromCustInfo.get("SCD_LINE_ADDR"))) {
                        shipFromAddrAllText += (String) shipFromCustInfo.get("SCD_LINE_ADDR");
                    }
                    if (ZYPCommonFunc.hasValue((String) shipFromCustInfo.get("THIRD_LINE_ADDR"))) {
                        shipFromAddrAllText += (String) shipFromCustInfo.get("THIRD_LINE_ADDR");
                    }
                    if (ZYPCommonFunc.hasValue((String) shipFromCustInfo.get("FRTH_LINE_ADDR"))) {
                        shipFromAddrAllText += (String) shipFromCustInfo.get("FRTH_LINE_ADDR");
                    }

                }
                ZYPEZDItemValueSetter.setValue(tMsg.shipFromAddrAllTxt, shipFromAddrAllText);
                // SHIP_FROM_CTY_TXT
                ZYPEZDItemValueSetter.setValue(tMsg.shipFromCtyTxt, (String) shipFromCustInfo.get("CTY_ADDR"));
                // SHIP_FROM_ST_TXT
                ZYPEZDItemValueSetter.setValue(tMsg.shipFromStTxt, (String) shipFromCustInfo.get("ST_CD"));
                // SHIP_FROM_POST_TXT
                ZYPEZDItemValueSetter.setValue(tMsg.shipFromPostTxt, (String) shipFromCustInfo.get("POST_CD"));
                // SHIP_FROM_CTRY_TXT
                ZYPEZDItemValueSetter.setValue(tMsg.shipFromCtryTxt, (String) shipFromCustInfo.get("CTRY_CD"));
                // SHIP_FROM_CTAC_NM
                ZYPEZDItemValueSetter.setValue(tMsg.shipFromCtacNm, dbsShipFromCtacNm);
                // SHIP_FROM_PHO_NUM
                ZYPEZDItemValueSetter.setValue(tMsg.shipFromPhoNum, (String) shipFromCustInfo.get("TEL_NUM"));
                // SHIP_FROM_CMNT_ALL_TXT
                ZYPEZDItemValueSetter.setValue(tMsg.shipFromCmntAllTxt, "");
            }
        }

        return tMsg;
    }

    /**
     * getTplCarrSvcLvlWithEffort
     * @param glblCmpyCd String
     * @param whOwnrCd String
     * @param carrCd String
     * @param shpgSvcLvlCd String
     * @return Map<String, Object>
     */
    public Map<String, Object> getTplCarrSvcLvlWithEffort(String glblCmpyCd, String whOwnrCd, String carrCd, String shpgSvcLvlCd) {
        Map<String, Object> tplCarrSvcLvl = null;

        tplCarrSvcLvl = getTplCarrSvcLvl(glblCmpyCd, whOwnrCd, carrCd, shpgSvcLvlCd);
        if (tplCarrSvcLvl == null) {
            tplCarrSvcLvl = getTplCarrSvcLvl(glblCmpyCd, whOwnrCd, null, shpgSvcLvlCd);
        }
        if (tplCarrSvcLvl == null) {
            tplCarrSvcLvl = getTplCarrSvcLvl(glblCmpyCd, whOwnrCd, null, null);
        }

        return tplCarrSvcLvl;
    }

    /**
     * getTplCarrSvcLvl
     * @param whOwnrCd
     * @param carrCd
     * @param shpgSvcLvlCd
     * @return
     */
    private Map<String, Object> getTplCarrSvcLvl(String glblCmpyCd, String whOwnrCd, String carrCd, String shpgSvcLvlCd) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("whOwnrCd", whOwnrCd);
        queryParam.put("carrCd", carrCd);
        queryParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getTplCarrSvcLvl", queryParam, execParam);
    }

    private String getTimezoneTime(String time, String postCd, String ctryCd, String oldFormat, String newFormat, int mode) {

        SvcTimeZoneInfo timeInfo = NSXC001001SvcTimeZone.convertTime(mode, time, ctryCd, postCd);
        String time2 = timeInfo.getDateTime().substring(0, NPAB136001Constant.DATE_LEN_YYYYMMDD + NPAB136001Constant.TIME_LEN_HHMM);
        time2 = ZYPDateUtil.DateFormatter(time2, oldFormat, newFormat);

        return time2;
    }

    private WH_OWNRTMsg getWhOwnr(String srcRtlWhCd) {

        RTL_WHTMsg rtlWhTMsg = getRtlWhTMsg(srcRtlWhCd);
        WH_OWNRTMsg tMsg = new WH_OWNRTMsg();

        if (rtlWhTMsg == null) {
            ZYPEZDItemValueSetter.setValue(tMsg.insrcPrchReqLineTpCd, PRCH_REQ_LINE_TP.LOCAL);
            return tMsg;
        }

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(tMsg.whOwnrCd, rtlWhTMsg.whOwnrCd);
        tMsg = (WH_OWNRTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            tMsg = new WH_OWNRTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.insrcPrchReqLineTpCd, PRCH_REQ_LINE_TP.LOCAL);
            return tMsg;
        }

        return tMsg;
    }
    // QC#57659 Add end
    // QC#61128 Add Start
    /**
     * from Third Party
     * @param prInfo
     * @param ruleDtl
     * @param mdseCd
     * @param reqQty
     * @param includeHazmat
     * @return
     */
    private BigDecimal srchStkProcDtlForThirdPty(PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String mdseCd, BigDecimal reqQty, boolean includeHazmat) {

        S21InfoLogOutput.println(String.format("[NPAB1360] Exec search process(Third Party). PR#%s-%s, Original MDSE#%s, Search MDSE#%s", prInfo.getHeaderInfo().prchReqNum.getValue(), prInfo.getDetailInfo().prchReqLineNum.getValue(),
                prInfo.getDetailInfo().mdseCd.getValue(), mdseCd));

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BigDecimal prchReqQty = BigDecimal.ZERO;
        BigDecimal allocCount = BigDecimal.ZERO;
        String vndCd = null;
        String prntVndCd = null;
        NLZC300001PMsg avaInv = null;

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();

            paramMap.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB136001Constant.DB_PARAM_PRCH_REQ_TP_CD, prInfo.getHeaderInfo().prchReqTpCd.getValue());
            paramMap.put(NPAB136001Constant.DB_PARAM_INSRC_ENBL_FLG, ZYPConstant.FLG_ON_Y);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("getThirdPartyInsourcingPlanning", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                vndCd = resultSet.getString(NPAB136001Constant.VND_CD);
                prntVndCd = getPrntVndCd(vndCd);
                if (ZYPCommonFunc.hasValue(vndCd)) {
                    // 2023/09/08 QC#61704 START
                    Map<String, Object> queryParams = new HashMap<String, Object>();
                    queryParams.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
                    queryParams.put(NPAB136001Constant.DB_PARAM_SALES_DATE, salesDate);
                    queryParams.put(NPAB136001Constant.DB_PARAM_MDSE_CD, mdseCd);
                    queryParams.put(NPAB136001Constant.DB_PARAM_VND_CD, vndCd);
                    queryParams.put(NPAB136001Constant.DB_PARAM_PRNT_VND_CD, prntVndCd);
                    NPZC113001PMsg avalInvtyVndPMsg = searchAslDtl(queryParams);

                    if (avalInvtyVndPMsg == null) {
                        S21InfoLogOutput.println(S21MessageFunc.clspGetMessage("NPAM1591E", new String[] {mdseCd, "ASL" }));
                        return null;
                    } else {
                        // 10/03/2023 QC#61703 Mod start
                        // avaInv = getAvalInvtyFromThirdPty(mdseCd, reqQty, vndCd, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_ON_Y);
                        // avaInv = getAvalInvtyFromThirdPty(avalInvtyVndPMsg.splyItemNum.getValue(), reqQty, vndCd, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_ON_Y);
                        avaInv = getAvalInvtyFromThirdPty(avalInvtyVndPMsg.splyItemNum.getValue(), mdseCd, reqQty, vndCd, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_ON_Y);
                        // 10/03/2023 QC#61703 Mod End
                    }
                    // 2023/09/08 QC#61704 END
                }

                List<String> errList = S21ApiUtil.getXxMsgIdList(avaInv);
                if (errList.size() > 0) {
                    for (String xxMsgId : errList) {
                        S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));

                        ErrorMessageBean errMsgBean = new ErrorMessageBean();
                        errMsgBean.setPrchReqNum(prInfo.getHeaderInfo().prchReqNum.getValue());
                        errMsgBean.setPrchReqLineNum(prInfo.getDetailInfo().prchReqLineNum.getValue());
                        errMsgBean.setMdseCd(mdseCd);
                        errMsgBean.setPrchReqQty(prInfo.getDetailInfo().prchReqQty.getValue());
                        errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage(xxMsgId));
                        errMsgBeanList.add(errMsgBean);

                        if (xxMsgId.endsWith("E")) {
                            if (xxMsgId.equals("NLZM2057E")) {
                                isWSErr = true;
                            }
                            // The entered Merchandise Code does not
                            // exist
                            // in Master.
                            if (xxMsgId.equals("NLZM2077E")) {
                                doesNotExistInItemMaster.add(mdseCd);
                            }
                            return null;
                        }
                    }
                }

                if ((avaInv.xxDetailList.getValidCount() > 0) && (avaInv.xxDetailList.no(0).xxAvalQty.getValue().compareTo(BigDecimal.ZERO) > 0)) {
                    prchReqQty = avaInv.xxDetailList.no(0).xxAvalQty.getValue();
                    if (prchReqQty.compareTo(reqQty) >= 0) {
                        prchReqQty = reqQty;
                    }
                    ZYPEZDItemValueSetter.setValue(prInfo.getHeaderInfo().prchReqApvlStsCd, PRCH_REQ_APVL_STS.APPROVED);
                    BigDecimal retVal  = srchStkProcDtlForThirdPtySub(insrcAvalInvPrForVndPMsg, insrcPrToCratPrAvalInvPMsg, prInfo, ruleDtl, mdseCd, prchReqQty, vndCd, prntVndCd, includeHazmat);

                    if(retVal != null) {
                        allocCount = allocCount.add(retVal);
                        reqQty = reqQty.subtract(retVal);
                        
                        if (reqQty.compareTo(BigDecimal.ZERO) <= 0) {
                            break;
                        }
                    }
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return allocCount;
    }
    
    private BigDecimal srchStkProcDtlForThirdPtySub(NPZC103001PMsg vndPMsg, NPZC103001PMsg cratPrPMsg, PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String mdseCd, BigDecimal reqQty, String vndCd, String prntVndCd, boolean includeHazmat) {
        String currencyCode = null;
        NPXC001001CurrencyConversionBean currency = null;
        NPZC129001PMsg vendorQty = null;

        // NPZC113001PMsg vendor = getPrimaryVendorInfo(prInfo, vndCd, prntVndCd, mdseCd, true, true);
        NPZC113001PMsg vendor =getPrimaryVendor(vndCd, prntVndCd, mdseCd);

        // 2023/09/08 QC#61704 START
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
        queryParams.put(NPAB136001Constant.DB_PARAM_SALES_DATE, salesDate);
        queryParams.put(NPAB136001Constant.DB_PARAM_MDSE_CD, mdseCd);
        queryParams.put(NPAB136001Constant.DB_PARAM_VND_CD, vndCd);
        queryParams.put(NPAB136001Constant.DB_PARAM_PRNT_VND_CD, prntVndCd);
        NPZC113001PMsg avalInvtyVndPMsg = searchAslDtl(queryParams);

        if (avalInvtyVndPMsg == null) {
        // if(!vndCd.equals(vendor.vndCd.getValue()) || !prntVndCd.equals(vendor.prntVndCd.getValue())) {
       
//            String orclVndCd = ZYPCodeDataUtil.getVarCharConstValue(NPAB136001Constant.VAR_CONST_ORCL_VND_CD, getGlobalCompanyCode());
//            if(ZYPCommonFunc.hasValue(orclVndCd) && orclVndCd.equals(vndCd)) {
//                setErrMsg(prInfo.getHeaderInfo().prchReqNum.getValue(), prInfo.getDetailInfo().prchReqLineNum.getValue(), mdseCd, reqQty, NPAB136001Constant.NPAM1659E, new String[] {vndCd});
//                isTplErr = true;
//                errorCount++;
//            }
            // 2023/09/08 QC#61704 END
            return null;
        }

        if (!ZYPConstant.FLG_ON_Y.equals(vendor.xxErrFlg.getValue())) {
            // Get Vendor Currency code
            currencyCode = getVndCcyCd(vendor.vndCd.getValue());

            // Get Vendor Currency
            NPXC001001CurrencyConversion cov = new NPXC001001CurrencyConversion();
            currency = cov.convertCurrency(globalCompanyCode, currencyCode, vendor.unitPrcAmt.getValue(), salesDate, null);

            // Get Vendor Qty
            vendorQty = getVendorPoQty(vendor.vndCd.getValue(), mdseCd, reqQty);
            if (0 < vendorQty.xxMsgIdList.getValidCount()) {
                rollback();
                errorProcess(prInfo, vendorQty.xxMsgIdList.no(0).xxMsgId.getValue(), prInfo.getDetailInfo().mdseCd.getValue());
                return null;
            }
        }

        // Create PO Child Line
        createNPZC103001PMsgForCreateForPr(vndPMsg, prInfo, ruleDtl, vendor, mdseCd, reqQty, includeHazmat, false);

        if (PRCH_REQ_REC_TP.TECH_REQUEST.equals(prInfo.getHeaderInfo().prchReqRecTpCd.getValue())) {
            createNPZC103001PMsgForCratVndPr(cratPrPMsg, prInfo, ruleDtl, vendor, vendorQty, currency, currencyCode, mdseCd, reqQty, includeHazmat);
        }

        if (reqQty != null) {
            if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.origLineFrzFlg.getValue())) {
                freeze(prInfo, ruleDtl);
            }

            if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.boNtfyReqFlg.getValue())) {
                String prchReqNum = insrcPrToCratPrPMsg.prchReqNum.getValue();
                String prchReqLineNum = insrcPrToCratPrPMsg.prchReqInfo.no(insrcPrToCratPrPMsg.prchReqInfo.getValidCount() - 1).prchReqLineNum.getValue();
                String prchReqApvlStsCd = insrcPrToCratPrPMsg.prchReqApvlStsCd.getValue();

                // START 2023/06/02 S.Dong [QC#55629, MOD]
                //sendMail(prInfo, reqQty, prchReqNum, prchReqLineNum, prchReqApvlStsCd);
                sendMail(prInfo, reqQty, null, prchReqNum, prchReqLineNum, prchReqApvlStsCd);
                // END 2023/06/02 S.Dong [QC#55629, MOD]
            }
            
            if (ZYPConstant.FLG_ON_Y.equals(ruleDtl.boNtfyReqFlg.getValue()) && PRCH_REQ_TP.RUSH.equals(prInfo.getHeaderInfo().prchReqTpCd.getValue())) {
                rushKickOut(prInfo, reqQty);
            }
        }
        
        if (PRCH_REQ_TP.PREMIUM_RUSH.equals(prInfo.getHeaderInfo().prchReqTpCd.getValue()) && PROCR_TP.WAREHOUSE.equals(prInfo.getDetailInfo().procrTpCd.getValue())) {
            return null;
        }
        
        return reqQty;
    }
    
    /**
     * call Inventory Reference API (get Available Inventory from. 10/03/2023 QC#61703 Add origMdseCd
     * Third Party)
     * @param mdseCd
     * @param reqQty
     * @param vndCd
     * @param flg1 get CSA Inventory Avilability
     * @param flg2 get CUSA WS Inventory Avilability
     * @param flg3 get CUSA Parts Inventory Avilability
     * @param flg4 get CVI Inventory Avilability
     * @param flg5 get Third Party Inventory Avilability
     * @return
     */
    private NLZC300001PMsg getAvalInvtyFromThirdPty(String mdseCd, String origMdseCd, BigDecimal reqQty, String vndCd, String flg1, String flg2, String flg3, String flg4, String flg5) {

        NLZC300001 api = new NLZC300001();
        NLZC300001PMsg param = new NLZC300001PMsg();

        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(param.procDt, salesDate);
        ZYPEZDItemValueSetter.setValue(param.xxRqstFlg_01, flg1);
        ZYPEZDItemValueSetter.setValue(param.xxRqstFlg_02, flg2);
        ZYPEZDItemValueSetter.setValue(param.xxRqstFlg_03, flg3);
        ZYPEZDItemValueSetter.setValue(param.xxRqstFlg_04, flg4);
        ZYPEZDItemValueSetter.setValue(param.xxRqstFlg_05, flg5);
        ZYPEZDItemValueSetter.setValue(param.xxRsltFlg_WS, ZYPConstant.FLG_ON_Y);
        // detail
        ZYPEZDItemValueSetter.setValue(param.xxDetailList.no(0).mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(param.xxDetailList.no(0).ordQty, reqQty);
        ZYPEZDItemValueSetter.setValue(param.xxDetailList.no(0).vndCd, vndCd);
        // 2023/09/04 QC#61703 START
        // 10/03/2023 QC#61703 Mod Start
//        BigDecimal trxReqQty = (BigDecimal) mdseMap.get(vndCd.concat(mdseCd));
        BigDecimal trxReqQty = getPrPoRequestQty(globalCompanyCode, vndCd, origMdseCd, mdseCd);
        // 10/03/2023 QC#61703 Mod End
        if (ZYPCommonFunc.hasValue(trxReqQty)) {
            ZYPEZDItemValueSetter.setValue(param.xxDetailList.no(0).allocQty, trxReqQty);
        }
        // 2023/09/04 QC#61703 END

        param.xxDetailList.setValidCount(1);

        api.execute(param, ONBATCH_TYPE.BATCH);

        return param;
    }

    /**
     * from Oracle
     * @param prInfo
     * @param ruleDtl
     * @param mdseCd
     * @param reqQty
     * @param includeHazmat
     * @return
     */
    private BigDecimal srchStkProcDtlForOrcl(PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String mdseCd, BigDecimal reqQty, boolean includeHazmat) {

        S21InfoLogOutput.println(String.format("[NPAB1360] Exec search process(Oracle). PR#%s-%s, Original MDSE#%s, Search MDSE#%s", prInfo.getHeaderInfo().prchReqNum.getValue(), prInfo.getDetailInfo().prchReqLineNum.getValue(),
                prInfo.getDetailInfo().mdseCd.getValue(), mdseCd));

        NLZC300001PMsg avaInv = null;
        
        String orclVndCd = ZYPCodeDataUtil.getVarCharConstValue(NPAB136001Constant.VAR_CONST_ORCL_VND_CD, getGlobalCompanyCode());
        String prntVndCd = getPrntVndCd(orclVndCd);
        
        if (ZYPCommonFunc.hasValue(orclVndCd) && ZYPCommonFunc.hasValue(prntVndCd)) {

            // 2023/09/08 QC#61704 START
            Map<String, Object> queryParams = new HashMap<String, Object>();
            queryParams.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            queryParams.put(NPAB136001Constant.DB_PARAM_SALES_DATE, salesDate);
            queryParams.put(NPAB136001Constant.DB_PARAM_MDSE_CD, mdseCd);
            queryParams.put(NPAB136001Constant.DB_PARAM_VND_CD, orclVndCd);
            queryParams.put(NPAB136001Constant.DB_PARAM_PRNT_VND_CD, prntVndCd);
            NPZC113001PMsg avalInvtyVndPMsg = searchAslDtl(queryParams);

            if (avalInvtyVndPMsg == null) {
                S21InfoLogOutput.println(S21MessageFunc.clspGetMessage("NPAM1337W", new String[] {"ASL", orclVndCd, mdseCd }));
                // 2023/11/28 QC#62434 STAT
                avaInv = getAvalInvtyFromOrcl(mdseCd, mdseCd, reqQty, orclVndCd, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_ON_Y);
                List<String> errList = S21ApiUtil.getXxMsgIdList(avaInv);
                for (String xxMsgId : errList) {
                    S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));

                    ErrorMessageBean errMsgBean = new ErrorMessageBean();
                    errMsgBean.setPrchReqNum(prInfo.getHeaderInfo().prchReqNum.getValue());
                    errMsgBean.setPrchReqLineNum(prInfo.getDetailInfo().prchReqLineNum.getValue());
                    errMsgBean.setMdseCd(mdseCd);
                    errMsgBean.setPrchReqQty(prInfo.getDetailInfo().prchReqQty.getValue());
                    errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage(xxMsgId));
                    errMsgBeanList.add(errMsgBean);

                    if (xxMsgId.endsWith("E")) {
                        if (xxMsgId.equals("NLZM2057E")) {
                            isWSErr = true;
                        }
                        // The entered Merchandise Code does not
                        // exist
                        // in Master.
                        if (xxMsgId.equals("NLZM2077E")) {
                            doesNotExistInItemMaster.add(mdseCd);
                        }
                        return null;
                    }
                }

                if ((avaInv.xxDetailList.getValidCount() > 0) && (avaInv.xxDetailList.no(0).xxAvalQty.getValue().compareTo(BigDecimal.ZERO) > 0)) {
                    setErrMsg(prInfo.getHeaderInfo().prchReqNum.getValue(), prInfo.getDetailInfo().prchReqLineNum.getValue(), mdseCd, reqQty, NPAB136001Constant.NPAM1659E, new String[] {orclVndCd });
                    isTplErr = true;
                    errorCount++;
                }
                // 2023/11/28 QC#62434 END
                return null;
            } else {
                // 10/03/2023 QC#61703 Mod start
                // avaInv = getAvalInvtyFromOrcl(mdseCd, reqQty, orclVndCd, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_ON_Y);
                // avaInv = getAvalInvtyFromOrcl(avalInvtyVndPMsg.splyItemNum.getValue(), reqQty, orclVndCd, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_ON_Y);
                avaInv = getAvalInvtyFromOrcl(avalInvtyVndPMsg.splyItemNum.getValue(), mdseCd, reqQty, orclVndCd, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_ON_Y);
                // 10/03/2023 QC#61703 Mod start
            }
            // 2023/09/08 QC#61704 END

            List<String> errList = S21ApiUtil.getXxMsgIdList(avaInv);
            if (errList.size() > 0) {
                for (String xxMsgId : errList) {
                    S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));

                    ErrorMessageBean errMsgBean = new ErrorMessageBean();
                    errMsgBean.setPrchReqNum(prInfo.getHeaderInfo().prchReqNum.getValue());
                    errMsgBean.setPrchReqLineNum(prInfo.getDetailInfo().prchReqLineNum.getValue());
                    errMsgBean.setMdseCd(mdseCd);
                    errMsgBean.setPrchReqQty(prInfo.getDetailInfo().prchReqQty.getValue());
                    errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage(xxMsgId));
                    errMsgBeanList.add(errMsgBean);

                    if (xxMsgId.endsWith("E")) {
                        if (xxMsgId.equals("NLZM2057E")) {
                            isWSErr = true;
                        }
                        // The entered Merchandise Code does not
                        // exist
                        // in Master.
                        if (xxMsgId.equals("NLZM2077E")) {
                            doesNotExistInItemMaster.add(mdseCd);
                        }
                        return null;
                    }
                }
            }

            if ((avaInv.xxDetailList.getValidCount() > 0) && (avaInv.xxDetailList.no(0).xxAvalQty.getValue().compareTo(BigDecimal.ZERO) > 0)) {
                BigDecimal prchReqQty = avaInv.xxDetailList.no(0).xxAvalQty.getValue();
                if (prchReqQty.compareTo(reqQty) >= 0) {
                    prchReqQty = reqQty;
                }
                ZYPEZDItemValueSetter.setValue(prInfo.getHeaderInfo().prchReqApvlStsCd, PRCH_REQ_APVL_STS.APPROVED);
                reqQty = srchStkProcDtlForOrclSub(insrcAvalInvPrForVndPMsg, insrcPrToCratPrAvalInvPMsg, prInfo, ruleDtl, mdseCd, prchReqQty, orclVndCd, prntVndCd, includeHazmat);

                return reqQty;
            }
        }
        
        return null;
    }
    
    private BigDecimal srchStkProcDtlForOrclSub(NPZC103001PMsg vndPMsg, NPZC103001PMsg cratPrPMsg, PrInfoBean prInfo, INSRC_RULE_DTLTMsg ruleDtl, String mdseCd, BigDecimal reqQty, String orclVndCd, String prntVndCd, boolean includeHazmat) {
        return srchStkProcDtlForThirdPtySub(vndPMsg, cratPrPMsg, prInfo, ruleDtl, mdseCd, reqQty, orclVndCd, prntVndCd, includeHazmat);
    }
    
    /**
     * call Inventory Reference API (get Available Inventory from. 10/03/2023 QC#61703 Add origMdseCd
     * Oracle)
     * @param mdseCd
     * @param reqQty
     * @param orclVndCd
     * @param flg1 get CSA Inventory Avilability
     * @param flg2 get CUSA WS Inventory Avilability
     * @param flg3 get CUSA Parts Inventory Avilability
     * @param flg4 get CVI Inventory Avilability
     * @param flg5 get Third Party Inventory Avilability
     * @return
     */
    private NLZC300001PMsg getAvalInvtyFromOrcl(String mdseCd, String origMdseCd, BigDecimal reqQty, String orclVndCd, String flg1, String flg2, String flg3, String flg4, String flg5) {
        return getAvalInvtyFromThirdPty(mdseCd, origMdseCd, reqQty, orclVndCd, flg1, flg2, flg3, flg4, flg5);
    }
    
    /**
     * call Primary Vendor from ASL API
     * @param vndCd
     * @param prntVndCd
     * @param mdseCd
     * @return
     */
    private NPZC113001PMsg getPrimaryVendor(String vndCd, String prntVndCd, String mdseCd) {

        NPZC113001 api = new NPZC113001();
        NPZC113001PMsg param = new NPZC113001PMsg();

        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(param.slsDt, salesDate);
        ZYPEZDItemValueSetter.setValue(param.vndCd, vndCd);
        ZYPEZDItemValueSetter.setValue(param.prntVndCd, prntVndCd);
        ZYPEZDItemValueSetter.setValue(param.mdseCd, mdseCd);

        api.execute(param, ONBATCH_TYPE.BATCH);

        return param;
    }
    
    /**
     * getPrimaryVendorInfo
     * @param prInfo
     * @param vndCd
     * @param prntVndCd
     * @param mdseCd
     * @param isSupd
     * @param isInvty
     * @return
     */
    private NPZC113001PMsg getPrimaryVendorInfo(PrInfoBean prInfo, String vndCd, String prntVndCd, String mdseCd, boolean isSupd, boolean isInvty) {

        NPZC113001PMsg vendor = getPrimaryVendor(vndCd, prntVndCd, mdseCd);

        if (0 < vendor.xxMsgIdList.getValidCount()) {
            rollback();
            errorProcess(prInfo, vendor.xxMsgIdList.no(0).xxMsgId.getValue(), mdseCd);
            return null;
        } else if (ZYPConstant.FLG_ON_Y.equals(vendor.xxErrFlg.getValue())) {
            if (isSupd) {
                NWZC206001PMsg nWZC206001PMsg = new NWZC206001PMsg();
                ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.glblCmpyCd, globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.slsDt, salesDate);
                ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.mdseCd, mdseCd);
                ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxModeCd, NWZC206001.SUPD_LATEST_MODE);
                ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxAvalPrchFlg, ZYPConstant.FLG_ON_Y);

                NWZC206001 nWZC206001 = new NWZC206001();
                nWZC206001.execute(nWZC206001PMsg, ONBATCH_TYPE.BATCH);

                List<String> errList = S21ApiUtil.getXxMsgIdList(nWZC206001PMsg);
                if (errList.size() > 0) {
                    for (String xxMsgId : errList) {
                        S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));
                        if (xxMsgId.endsWith("E")) {
                            return null;
                        }
                    }
                }
                if (ZYPCommonFunc.hasValue(nWZC206001PMsg.A.no(0).mdseCd) && !mdseCd.equals(nWZC206001PMsg.A.no(0).mdseCd.getValue())) {
                    vendor = getPrimaryVendorInfo(prInfo, vndCd, prntVndCd, nWZC206001PMsg.A.no(0).mdseCd.getValue(), false, false);
                }
            }

            if (isSupd && vendor == null) {
                rollback();
                errorProcess(prInfo, NPAB136001Constant.NPZM0272E, mdseCd);
                return null;
            } else if (vendor == null || ZYPConstant.FLG_ON_Y.equals(vendor.xxErrFlg.getValue())) {
                if (isInvty) {
                    return null;
                } else {
                    rollback();
                    errorProcess(prInfo, NPAB136001Constant.NPZM0272E, mdseCd);
                    return null;
                }
            } else {
                return vendor;
            }
        } else {

            if (!isInvty) {
                RCV_ASN_VNDTMsg rcvAsnVndTMsg = new RCV_ASN_VNDTMsg();
                ZYPEZDItemValueSetter.setValue(rcvAsnVndTMsg.glblCmpyCd, globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(rcvAsnVndTMsg.rcvAsnVndCd, vendor.vndCd.getValue());
                rcvAsnVndTMsg = (RCV_ASN_VNDTMsg) S21ApiTBLAccessor.findByKey(rcvAsnVndTMsg);

                if (rcvAsnVndTMsg == null || ZYPConstant.FLG_ON_Y.equals(rcvAsnVndTMsg.itemFlipFlg.getValue())) {

                    NWZC206001PMsg nWZC206001PMsg = new NWZC206001PMsg();
                    ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.glblCmpyCd, globalCompanyCode);
                    ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.slsDt, salesDate);
                    ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.mdseCd, mdseCd);
                    ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxModeCd, NWZC206001.SUPD_LATEST_MODE);
                    ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxAvalPrchFlg, ZYPConstant.FLG_ON_Y);

                    NWZC206001 nWZC206001 = new NWZC206001();
                    nWZC206001.execute(nWZC206001PMsg, ONBATCH_TYPE.BATCH);

                    List<String> errList = S21ApiUtil.getXxMsgIdList(nWZC206001PMsg);
                    if (errList.size() > 0) {
                        for (String xxMsgId : errList) {
                            S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));
                            if (xxMsgId.endsWith("E")) {
                                return null;
                            }
                        }
                    }

                    if (ZYPCommonFunc.hasValue(nWZC206001PMsg.A.no(0).mdseCd) && !mdseCd.equals(nWZC206001PMsg.A.no(0).mdseCd.getValue())) {
                        vendor = getPrimaryVendorInfo(prInfo, vndCd, prntVndCd, nWZC206001PMsg.A.no(0).mdseCd.getValue(), false, isInvty);
                    }

                }
            }

            if (vendor != null && Arrays.asList(this.cusaPartsOnlyPrTypes).contains(prInfo.getHeaderInfo().prchReqTpCd.getValue()) && //
                    !Arrays.asList(this.cusaPartsOnlyVndCds).contains(vendor.vndCd.getValue())) {
                return null;
            }

            return vendor;
        }
    }
    
    private String getPrntVndCd(String vndCd) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String prntVndCd = null;
        
        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

            HashMap<String, Object> paramMap = new HashMap<String, Object>();

            paramMap.put(NPAB136001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB136001Constant.DB_PARAM_VND_CD, vndCd);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("getPrntVndCd", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                prntVndCd = resultSet.getString(NPAB136001Constant.PRNT_VND_CD);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return prntVndCd;
    }
    
    private void setErrMsg(String prchReqNum, String prchReqLineNum, String mdseCd, BigDecimal prchReqQty, String errMsgId, String[] errMsgParam) {
        S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(errMsgId));
        ErrorMessageBean errMsgBean = new ErrorMessageBean();
        errMsgBean.setPrchReqNum(prchReqNum);
        errMsgBean.setPrchReqLineNum(prchReqLineNum);
        errMsgBean.setMdseCd(mdseCd);
        errMsgBean.setPrchReqQty(prchReqQty);
        
        if(errMsgParam != null && errMsgParam.length > 0) {
            errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage(errMsgId, errMsgParam));
        } else {
            errMsgBean.setErrMsg(S21MessageFunc.clspGetMessage(errMsgId));
        }
        
        errMsgBeanList.add(errMsgBean);
    }
    // QC#61128 Add End
    // START 2023/06/02 S.Dong [QC#55629, ADD]
    private NPZC136001PMsg getMinVndQty(PrInfoBean prInfo, String mdseCd, BigDecimal reqQty) {
        NPZC136001PMsg pMsg = getVndMinOrdQty(prInfo, mdseCd, reqQty);
        return pMsg;
    }

    private NPZC136001PMsg getVndMinOrdQty(PrInfoBean prInfo, String mdseCd, BigDecimal reqQty) {

        NPZC136001 api = new NPZC136001();
        NPZC136001PMsg param = new NPZC136001PMsg();

        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(param.slsDt, salesDate);
        ZYPEZDItemValueSetter.setValue(param.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(param.prchReqQty, reqQty);
        ZYPEZDItemValueSetter.setValue(param.prchReqRecTpCd, prInfo.getHeaderInfo().prchReqRecTpCd.getValue());
        if (ZYPCommonFunc.hasValue(prInfo.getHeaderInfo().shipToCustCd)) {
            ZYPEZDItemValueSetter.setValue(param.shipToCustCd, prInfo.getHeaderInfo().shipToCustCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(prInfo.getDetailInfo().prntVndCd)) {
            ZYPEZDItemValueSetter.setValue(param.prntVndCd, prInfo.getDetailInfo().prntVndCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(prInfo.getDetailInfo().vndCd)) {
            ZYPEZDItemValueSetter.setValue(param.vndCd, prInfo.getDetailInfo().vndCd.getValue());
        }
        api.execute(param, ONBATCH_TYPE.BATCH);

        return param;
    }
    // END 2023/06/02 S.Dong [QC#55629, ADD]

    // 10/03/2023 QC#61703 Add Start
    private BigDecimal getPrPoRequestQty(String glblCmpyCd, String vndCd, String mdseCd, String aslMdseCd) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("prchReqRecTpPoReq", PRCH_REQ_REC_TP.PO_REQUISITION);
        queryParam.put("poSrcTpCdInsourcing", PO_ORD_SRC.INSOURCING);
        queryParam.put("vndCd", vndCd);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("aslMdseCd", aslMdseCd);
        List<String> poInsourcingApvlList = new ArrayList<String>();
        poInsourcingApvlList.add(PO_APVL_STS.ENTERED);
        poInsourcingApvlList.add(PO_APVL_STS.AWAITING_APPROVAL);
        poInsourcingApvlList.add(PO_APVL_STS.PRE_APPROVED);
        poInsourcingApvlList.add(PO_APVL_STS.APPROVED);
        queryParam.put("poInsourcingApvlList", poInsourcingApvlList);
        List<String> poLineStsList = new ArrayList<String>();
        poLineStsList.add(PO_LINE_STS.OPEN);
        poLineStsList.add(PO_LINE_STS.OPEN_FOR_RECEIPT);
        queryParam.put("poInsourcingLineStsList", poLineStsList);
        List<String> poNotInsourcingApvlList = new ArrayList<String>();
        poNotInsourcingApvlList.add(PO_APVL_STS.PRE_APPROVED);
        poNotInsourcingApvlList.add(PO_APVL_STS.APPROVED);
        queryParam.put("poNotInsourcingApvlList", poNotInsourcingApvlList);

        return (BigDecimal) this.ssmBatchClient.queryObject("getPrPoRequestQty", queryParam, execParam);
    }
    // 10/03/2023 QC#61703 Add End
}
