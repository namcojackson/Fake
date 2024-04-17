/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC601001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDPStringItem;
import parts.common.EZDTDateItem;
import parts.common.EZDTMsg;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDSeqTable;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ACCT_LOCTMsg;
import business.db.ACCT_LOCTMsgArray;
import business.db.LOC_USGTMsg;
import business.db.PTY_LOC_WRKTMsg;
import business.db.PTY_LOC_WRKTMsgArray;
import business.db.RTL_SWHTMsg;
import business.db.RTL_WHTMsg;
import business.db.RTL_WH_LOC_ROLE_PTRNTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.TECH_LOCTMsg;
import business.db.VND_SHIP_TO_XREFTMsg;
import business.db.WHTMsg;
import business.parts.NMZC601001PMsg;
import business.parts.NMZC601001_swhInfoListPMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TECH_LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_XREF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   CITS            M.Ito           Create          N/A
 * 02/04/2016   CSAI            D.Fukaya        Update          QC#2380
 * 02/16/2016   CSAI            D.Fukaya        Update          QC#2368
 * 02/18/2016   CSAI            D.Fukaya        Update          QC#3436
 * 02/29/2016   CSAI            D.Fukaya        Update          QC#1596/2363/2365
 * 04/25/2016   CSAI            D.Fukaya        Update          QC#6406
 * 05/03/2016   CSAI            D.Fukaya        Update          QC#7454/7731
 * 06/14/2016   CSAI            D.Fukaya        Update          QC#9017
 * 03/03/2017   CSAI            T.Wada          Update          QC#17362
 * 11/21/2017   CITS            K.Ogino         Update          QC#22212
 * 12/25/2017   CITS            K.Ogino         Update          QC#21320
 * 05/08/2018   CITS            T.Tokutomi      Update          QC#2366
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 *</pre>
 */
public class NMZC601001 extends S21ApiCommonBase {

    // -------------------------------------------------
    // Mode
    // -------------------------------------------------

    /** Mode: Create */
    public static final String MODE_CREATE = "1";

    /** Mode: Update */
    public static final String MODE_UPDATE = "2";

    /** Mode: Delete */
    public static final String MODE_DELETE = "3";

    /** Mode for NMZC6100 Customer Information Get API: 04:Default Billto Shipto */
    public static final String MODE_NMZC6100 = "04";

    // -------------------------------------------------
    // Message Code
    // -------------------------------------------------
    /**
     * NMZM0009E: Input Parameter Global Company Code is mandatory
     * field.
     */
    public static final String NMZM0009E = "NMZM0009E";

    /** NMZM0029E: Input Parameter Mode is mandatory field. */
    public static final String NMZM0029E = "NMZM0029E";

    /**
     * NMZM0030E: Input Parameter Retail Warehouse Code is mandatory
     * field.
     */
    public static final String NMZM0030E = "NMZM0030E";

    /**
     * NMZM0031E: Input Parameter Retail Warehouse Name is mandatory
     * field.
     */
    public static final String NMZM0031E = "NMZM0031E";

    /**
     * NMZM0032E: Input Parameter Retail Warehouse Category Code is
     * mandatory field.
     */
    public static final String NMZM0032E = "NMZM0032E";

    /**
     * NMZM0033E: Input Parameter Location Type Code is mandatory
     * field.
     */
    public static final String NMZM0033E = "NMZM0033E";

    /**
     * NMZM0034E: Input Parameter Inventory Owner Code is mandatory
     * field.
     */
    public static final String NMZM0034E = "NMZM0034E";

    /**
     * NMZM0035E: Input Parameter First Line Address is mandatory
     * field.
     */
    public static final String NMZM0035E = "NMZM0035E";

    /** NMZM0036E: Input Parameter City Address is mandatory field. */
    public static final String NMZM0036E = "NMZM0036E";

    /** NMZM0037E: Input Parameter State Code is mandatory field. */
    public static final String NMZM0037E = "NMZM0037E";

    /** NMZM0038E: Input Parameter Post Code is mandatory field. */
    public static final String NMZM0038E = "NMZM0038E";

    /** NMZM0039E: Input Parameter Country Code is mandatory field. */
    public static final String NMZM0039E = "NMZM0039E";

    /**
     * NMZM0040E: Input Parameter Effective From Date is mandatory
     * field.
     */
    public static final String NMZM0040E = "NMZM0040E";

    /**
     * NMZM0041E: Input Parameter Retail Sub Warehouse Code is
     * mandatory field.
     */
    public static final String NMZM0041E = "NMZM0041E";

    /**
     * NMZM0042E: Input Parameter Retail Sub Warehouse Name is
     * mandatory field.
     */
    public static final String NMZM0042E = "NMZM0042E";

    /**
     * NMZM0043E: Input Parameter Inventory Location Code is mandatory
     * field.
     */
    public static final String NMZM0043E = "NMZM0043E";

    /**
     * NMZM0044E: Input Parameter Effective From Date is mandatory
     * field.
     */
    public static final String NMZM0044E = "NMZM0044E";

    /**
     * NMZM0045E: Input Parameter Material Requirements Planning
     * Enabled Flag is mandatory field.
     */
    public static final String NMZM0045E = "NMZM0045E";

    /**
     * NMZM0068E: Input Parameter Inventory Account Code is mandatory
     * field.
     */
    public static final String NMZM0068E = "NMZM0068E";

    /**
     * NMZM0069E: Input Parameter Ship To Customer Location Name is
     * mandatory field.
     */
    public static final String NMZM0069E = "NMZM0069E";

    /**
     * NMZM0069E: Input Parameter Branch Code is mandatory field.
     */
    public static final String NMZM0168E = "NMZM0168E";

    /** NMZM0046E: Failed to register to RTL_WH. */
    public static final String NMZM0046E = "NMZM0046E";

    /** NMZM0047E: Failed to register to RTL_SWH. */
    public static final String NMZM0047E = "NMZM0047E";

    /** NMZM0048E: Failed to register to PTY_LOC_WRK. */
    public static final String NMZM0048E = "NMZM0048E";

    /** NMZM0050E: Failed to register to SHIP_TO_CUST. */
    public static final String NMZM0050E = "NMZM0050E";

    /** NMZM0093E: Failed to register to ACCT_LOC. */
    public static final String NMZM0093E = "NMZM0093E";

    /** NMZM0051E: Failed to register to LOC_USG. */
    public static final String NMZM0051E = "NMZM0051E";

    /** NMZM0052E: Failed to register to WH. */
    public static final String NMZM0052E = "NMZM0052E";

    /** NMZM0053E: Failed to register to TECH_LOC. */
    public static final String NMZM0053E = "NMZM0053E";

    /** NMZM0182E: Failed to register to VND_SHIP_TO_XREF. */
    public static final String NMZM0182E = "NMZM0182E";

    /** NMZM0067E: Failed to register to Direct Sales Ship To Customer. */
    public static final String NMZM0067E = "NMZM0067E";

    /** NMZM0169E: Failed to remove SHIP_TO_CUST table. Please contact System Administrator. */
    public static final String NMZM0169E = "NMZM0169E";

    /** NMZM0171E: Failed to remove PTY_LOC_WRK table. Please contact System Administrator. */
    public static final String NMZM0171E = "NMZM0171E";

    /** NMZM0173E: Failed to remove ACCT_LOC table. Please contact System Administrator. */
    public static final String NMZM0173E = "NMZM0173E";

    /** NMZM0174E: Failed to remove LOC_USG table. Please contact System Administrator. */
    public static final String NMZM0174E = "NMZM0174E";

    /** NMZM0175E: Failed to remove WH table. Please contact System Administrator. */
    public static final String NMZM0175E = "NMZM0175E";

    /** NMZM0176E: Failed to remove TECH_LOC table. Please contact System Administrator. */
    public static final String NMZM0176E = "NMZM0176E";

    /** NMZM0177E: Failed to remove RTL_SWH table. Please contact System Administrator. */
    public static final String NMZM0177E = "NMZM0177E";

    /** NMZM0183E: Failed to remove VND_SHIP_TO_XREF table. Please contact System Administrator. */
    public static final String NMZM0183E = "NMZM0183E";

    /** NMZM0186E: Please enter Return To Code or select check box "Return To Address Is the same with Ship To Address". */
    public static final String NMZM0186E = "NMZM0186E";

    // QC#21320
    /** NMZM0362E: No Retail Warehouse Location Role Pattern data found. Need to setup Retail Warehouse Location Role Pattern. */
    public static final String NMZM0362E = "NMZM0362E";

    // -------------------------------------------------
    // DB Constants
    // -------------------------------------------------

    /** Sequence Name for PTY_LOC_PK */
    private static final String PTY_LOC_PK_SQ = "PTY_LOC_WRK_SQ";

    /** Sequence Name for SHIP_TO_CUST_PK */
    private static final String SHIP_TO_CUST_PK_SQ = "SHIP_TO_CUST_SQ";

    /** Sequence Name for ACCT_LOC_PK */
    private static final String ACCT_LOC_PK_SQ = "ACCT_LOC_SQ";

    /** Sequence Name for LOC_USG_PK */
    private static final String LOC_USG_PK_SQ = "LOC_USG_SQ";

    /** Sequence Name for WH_PK */
    private static final String WH_PK_SQ = "WH_SQ";

    /** Sequence Name for TECH_LOC_PK */
    private static final String TECH_LOC_PK_SQ = "TECH_LOC_SQ";

    /** Sequence Name for VND_SHIP_TO_XREF_SQ_PK */
    private static final String VND_SHIP_TO_XREF_PK_SQ = "VND_SHIP_TO_XREF_SQ";

    /** Max Date */
    private static final String MAX_DATE = "99991231";

    /** TMsg normal return code */
    private static final String RETURN_CODE_NORMAL = "0000";

    /** Flag: Y */
    private static final String Y = ZYPConstant.FLG_ON_Y;

    /** Flag: N */
    private static final String N = ZYPConstant.FLG_OFF_N;

    /** Conjunction string for location name */
    private static final String LOC_NM_JOINT = " ";

    // =================================================
    // Var Char Const Key
    // =================================================
    /** Var Char Const Key :  NMZC601001_SUFFIX_FOR_RTRN_TO*/
    public static final String NMZC601001_SUFFIX_FOR_RTRN_TO = "NMZC601001_SUFFIX_FOR_RTRN_TO";

    /** NMZC601001_LINE_BIZ_TP : Value for DS_PTY_LOC_WRK.LINE_BIZ_TP_CD */
    private static final String NMZC601001_LINE_BIZ_TP_CD = "NMZC601001_LINE_BIZ_TP_CD";

    // =================================================
    // Constant Value
    // =================================================
    /** Asterisk */
    public static final String CONST_VALUE_ASTERISK = "*";

    /** Default suffix for Return-To*/
    public static final String DEFAULT_SUFFIX_FOR_RTRN_TO = "RT";

    // -------------------------------------------------
    // Instance Fields
    // -------------------------------------------------
    /** Mode */
    private String mode = null;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** S21User */
    private S21UserInfo userInfo = null;

    /** CMPY_PK */
    private BigDecimal cmpyPk = null;

    /** LOC_TP_CD */
    private String locTpCd = null;

    /** LOC_GRP_CD */
    private String locGrpCd = null;

    /** LOC_ROLE_TP_CD */
    private String locRoleTpCd = null;

    /** Suffix For Return-To Code */
    private String suffixForRtrnToCd = null;

    /** LINE_BIZ_TP_CD for RTL_WH : ALL */
    private String lineBizTpCd = null;

    /**
     * Constructor
     */
    public NMZC601001() {
        super();
    }

    /**
     * Retail Warehouse Setup Update API
     * @param param pMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NMZC601001PMsg param, final ONBATCH_TYPE onBatchType) {
        this.msgMap = new S21ApiMessageMap(param);
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.userInfo = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo();
        this.mode = param.xxModeCd.getValue();
        setSuffixForRtrnTo(param);
        this.lineBizTpCd = getLineBizTpCd(param.glblCmpyCd.getValue());

        NMZC601001PMsg pMsg = (NMZC601001PMsg) this.msgMap.getPmsg();
        doProcess(pMsg);
        this.msgMap.flush();
    }

    /**
     * @param pMsg
     */
    private void doProcess(NMZC601001PMsg pMsg) {

        checkParam(pMsg);

        if (hasError()) {
            return;
        }

        executeQuery(pMsg);
    }

    // -----------------------------------------------------------
    // Util
    // -----------------------------------------------------------

    private boolean hasError() {
        return this.msgMap.getMsgMgr().isXxMsgId();
    }

    private void addError(String xxMsgId) {
        this.msgMap.addXxMsgId(xxMsgId);
    }

    private boolean isCreateMode() {
        return MODE_CREATE.equals(this.mode);
    }

    private boolean isUpdateMode() {
        return MODE_UPDATE.equals(this.mode);
    }

    // -----------------------------------------------------------
    // Validation
    // -----------------------------------------------------------

    /**
     * @param pMsg
     */
    private void checkParam(NMZC601001PMsg pMsg) {
        checkCommon(pMsg);

        if (hasError()) {
            return;
        }

        if (isCreateMode()) {
            checkCreate(pMsg);

        } else if (isUpdateMode()) {
            checkUpdate(pMsg);
        }
    }

    /**
     * @param pMsg
     */
    private void checkCommon(NMZC601001PMsg pMsg) {
        if (!hasValue(pMsg.xxModeCd)) {
            addError(NMZM0029E);
        }
        if (!hasValue(pMsg.glblCmpyCd)) {
            addError(NMZM0009E);
        }
    }

    /**
     * @param pMsg
     */
    private void checkCreate(NMZC601001PMsg pMsg) {
        if (!hasValue(pMsg.rtlWhCd)) {
            addError(NMZM0030E);
        }
        if (!hasValue(pMsg.rtlWhNm)) {
            addError(NMZM0031E);
        }
        if (!hasValue(pMsg.rtlWhCatgCd)) {
            addError(NMZM0032E);
        }
        if (!hasValue(pMsg.locTpCd)) {
            addError(NMZM0033E);
        }
        if (!hasValue(pMsg.invtyAcctCd)) {
            addError(NMZM0068E);
        }
        if (!hasValue(pMsg.invtyOwnrCd)) {
            addError(NMZM0034E);
        }
        if (!hasValue(pMsg.firstLineAddr)) {
            addError(NMZM0035E);
        }
        if (!hasValue(pMsg.ctyAddr)) {
            addError(NMZM0036E);
        }
        if (!hasValue(pMsg.stCd)) {
            addError(NMZM0037E);
        }
        if (!hasValue(pMsg.postCd)) {
            addError(NMZM0038E);
        }
        if (!hasValue(pMsg.ctryCd)) {
            addError(NMZM0039E);
        }
        if (!hasValue(pMsg.effFromDt)) {
            addError(NMZM0040E);
        }
        if (!hasValue(pMsg.coaBrCd)) {
            addError(NMZM0168E);
        }
        for (int i = 0; i < pMsg.swhInfoList.getValidCount(); i++) {
            checkCreate(pMsg.swhInfoList.no(i));
        }

        if (!ZYPConstant.FLG_ON_Y.equals(pMsg.xxChkBox.getValue()) && !ZYPCommonFunc.hasValue(pMsg.rtrnToCustCd)) {
            if (cntShipToCustForRtrnTo(pMsg) > 0) {
                addError(NMZM0186E);
            }
        }
    }

    /**
     * @param swhInfoPMsg
     */
    private void checkCreate(NMZC601001_swhInfoListPMsg swhInfoPMsg) {
        if (!hasValue(swhInfoPMsg.xxModeCd)) {
            addError(NMZM0029E);
        }
        if (!hasValue(swhInfoPMsg.rtlSwhCd)) {
            addError(NMZM0041E);
        }
        if (!hasValue(swhInfoPMsg.rtlSwhNm)) {
            addError(NMZM0042E);
        }
        if (!hasValue(swhInfoPMsg.effFromDt)) {
            addError(NMZM0044E);
        }
        if (!hasValue(swhInfoPMsg.mrpEnblFlg)) {
            addError(NMZM0045E);
        }
    }

    /**
     * @param pMsg
     */
    private void checkUpdate(NMZC601001PMsg pMsg) {
        // same as create
        checkCreate(pMsg);
    }

    // -----------------------------------------------------------
    // Master CRUD
    // -----------------------------------------------------------

    /**
     * @param pMsg
     */
    private void executeQuery(NMZC601001PMsg pMsg) {

        if (isCreateMode()) {
            executeCreate(pMsg);

        } else if (isUpdateMode()) {
            executeUpdate(pMsg);
        }
    }

    // ----------------------------
    // Create
    // ----------------------------

    /**
     * @param pMsg
     */
    private void executeCreate(NMZC601001PMsg pMsg) {
        preparePTYInfo(pMsg);

        insertRTL_WH(pMsg);

        if (hasError()) {
            return;
        }

        insertRTL_SWHAndOthers(pMsg);
    }

    /**
     * @param pMsg
     */
    private void insertRTL_WH(NMZC601001PMsg pMsg) {
        RTL_WHTMsg tMsg = createRTL_WH(pMsg);

        EZDTBLAccessor.insert(tMsg);

        if (!isCodeNormal(tMsg)) {
            addError(NMZM0046E);
        }
    }

    /**
     * @param pMsg
     * @return
     */
    private RTL_WHTMsg createRTL_WH(NMZC601001PMsg pMsg) {
        RTL_WHTMsg tMsg = new RTL_WHTMsg();
        EZDMsg.copy(pMsg, null, tMsg, null);

        setValueIfNull(tMsg.autoSoDropFlg, N);
        if (WH_SYS_TP.WMS.equals(tMsg.whSysTpCd.getValue())) {
            setValueIfNull(tMsg.soPrinReqFlg, Y);
            setValueIfNull(tMsg.rwsPrinReqFlg, Y);
        } else {
            setValueIfNull(tMsg.soPrinReqFlg, N);
            setValueIfNull(tMsg.rwsPrinReqFlg, N);
        }
        setValueIfNull(tMsg.effThruDt, MAX_DATE);

        if (PROCR_TP.SUPPLIER.equals(pMsg.defSrcProcrTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.defSrcRtlWhCd, pMsg.prntVndCd_SD);
            ZYPEZDItemValueSetter.setValue(tMsg.defSrcRtlSwhCd, pMsg.vndCd_SD);
        }

        if (PROCR_TP.SUPPLIER.equals(pMsg.emerSrcProcrTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.emerSrcRtlWhCd, pMsg.prntVndCd_SE);
            ZYPEZDItemValueSetter.setValue(tMsg.emerSrcRtlSwhCd, pMsg.vndCd_SE);
        }

        if (PROCR_TP.SUPPLIER.equals(pMsg.defRtrnToProcrTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.defRtrnToRtlWhCd, pMsg.prntVndCd_SR);
            ZYPEZDItemValueSetter.setValue(tMsg.defRtrnToRtlSwhCd, pMsg.vndCd_SR);
        }

        // Set Ship-To Code
        if (ZYPCommonFunc.hasValue(pMsg.shipToCustCd)) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, pMsg.shipToCustCd);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, pMsg.rtlWhCd);
        }

        // Set Return-To Code
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxChkBox.getValue())) {
            if (ZYPCommonFunc.hasValue(pMsg.shipToCustCd)) {
                ZYPEZDItemValueSetter.setValue(tMsg.rtrnToCustCd, pMsg.shipToCustCd);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.rtrnToCustCd, pMsg.rtlWhCd);
            }
        } else {
            if (ZYPCommonFunc.hasValue(pMsg.rtrnToCustCd)) {
                ZYPEZDItemValueSetter.setValue(tMsg.rtrnToCustCd, pMsg.rtrnToCustCd);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.rtrnToCustCd, S21StringUtil.concatStrings(pMsg.rtlWhCd.getValue(), this.suffixForRtrnToCd));
            }
        }
        // QC#17362
        ZYPEZDItemValueSetter.setValue(tMsg.prntVndCd, pMsg.prntVndCd);
        ZYPEZDItemValueSetter.setValue(tMsg.vndCd, pMsg.vndCd);
        // QC#22212
        ZYPEZDItemValueSetter.setValue(tMsg.techMblMsgAddr, pMsg.techMblMsgAddr);
        
        return tMsg;
    }

    /**
     * @param pMsg
     */
    private void insertRTL_SWHAndOthers(NMZC601001PMsg pMsg) {
        int cnt = pMsg.swhInfoList.getValidCount();

        if (hasError()) {
            return;
        }

        List<RTL_SWHTMsg> swhList = new ArrayList<RTL_SWHTMsg>(cnt);
        List<PTY_LOC_WRKTMsg> ptyLocWrkList = new ArrayList<PTY_LOC_WRKTMsg>(cnt);
        List<SHIP_TO_CUSTTMsg> shipToCustList = new ArrayList<SHIP_TO_CUSTTMsg>(cnt);
        List<ACCT_LOCTMsg> acctLocList = new ArrayList<ACCT_LOCTMsg>(cnt);
        List<LOC_USGTMsg> locUsgList = new ArrayList<LOC_USGTMsg>();
        List<WHTMsg> whList = new ArrayList<WHTMsg>(cnt);
        List<TECH_LOCTMsg> techLocList = new ArrayList<TECH_LOCTMsg>(cnt);
        List<VND_SHIP_TO_XREFTMsg> vndShipToXrefList = new ArrayList<VND_SHIP_TO_XREFTMsg>(cnt);

        // create all tMsgs of WH Level
        NMZC601001_swhInfoListPMsg swhInfoPMsg = new NMZC601001_swhInfoListPMsg();
        createRTL_SWHAndOthersForRTLWHLevelRequire(pMsg,
                swhInfoPMsg,
                swhList,
                ptyLocWrkList,
                shipToCustList,
                acctLocList,
                locUsgList,
                whList,
                techLocList,
                vndShipToXrefList,
                cnt);

        // Create Return-To related information
        if (!ZYPConstant.FLG_ON_Y.equals(pMsg.xxChkBox.getValue()) && !ZYPCommonFunc.hasValue(pMsg.rtrnToCustCd)) {
            createRtrnToRelatedInfo(pMsg, ptyLocWrkList, shipToCustList, acctLocList);
        }

        // create all tMsgs of SWH Level
        for (int i = 0; i < cnt; i++) {
            swhInfoPMsg = pMsg.swhInfoList.no(i);
            createRTL_SWHAndOthers(pMsg, swhInfoPMsg,
                    swhList,
                    ptyLocWrkList,
                    shipToCustList,
                    acctLocList,
                    locUsgList,
                    whList,
                    techLocList,
                    cnt);
        }

        batchInsert(swhList, ptyLocWrkList, shipToCustList, acctLocList, locUsgList, whList, techLocList, vndShipToXrefList);
    }

    /**
     * @param swhList
     * @param ptyLocWrkList
     * @param ptyList
     * @param shipToCustList
     * @param acctLocList
     * @param locUsgList
     * @param whList
     * @param techLocList
     * @param vndShipToXrefList
     */
    private void batchInsert(List<RTL_SWHTMsg> swhList, List<PTY_LOC_WRKTMsg> ptyLocWrkList, List<SHIP_TO_CUSTTMsg> shipToCustList, List<ACCT_LOCTMsg> acctLocList, List<LOC_USGTMsg> locUsgList,
            List<WHTMsg> whList, List<TECH_LOCTMsg> techLocList, List<VND_SHIP_TO_XREFTMsg> vndShipToXrefList) {
        int result;

        if (swhList != null && swhList.size() != 0) {
            result = EZDFastTBLAccessor.insert(swhList.toArray(new RTL_SWHTMsg[swhList.size()]));
            if (result != swhList.size()) {
                addError(NMZM0047E);
                return;
            }
        }

        if (ptyLocWrkList != null && ptyLocWrkList.size() != 0) {
            result = EZDFastTBLAccessor.insert(ptyLocWrkList.toArray(new PTY_LOC_WRKTMsg[ptyLocWrkList.size()]));
            if (result != ptyLocWrkList.size()) {
                addError(NMZM0048E);
                return;
            }
        }

        if (shipToCustList != null && shipToCustList.size() != 0) {
            result = EZDFastTBLAccessor.insert(shipToCustList.toArray(new SHIP_TO_CUSTTMsg[shipToCustList.size()]));
            if (result != shipToCustList.size()) {
                addError(NMZM0050E);
                return;
            }
        }

        if (acctLocList != null && acctLocList.size() != 0) {
            result = EZDFastTBLAccessor.insert(acctLocList.toArray(new ACCT_LOCTMsg[acctLocList.size()]));
            if (result != acctLocList.size()) {
                addError(NMZM0093E);
                return;
            }
        }

        if (locUsgList != null && locUsgList.size() > 0) {
            result = EZDFastTBLAccessor.insert(locUsgList.toArray(new LOC_USGTMsg[locUsgList.size()]));
            if (result != locUsgList.size()) {
                addError(NMZM0051E);
                return;
            }
        }

        if (isLocTpWarehouse()) {
            if (whList != null && whList.size() > 0) {
                result = EZDFastTBLAccessor.insert(whList.toArray(new WHTMsg[whList.size()]));
                if (result != whList.size()) {
                    addError(NMZM0052E);
                    return;
                }
            }

        } else if (isLocTpTech()) {
            if (techLocList != null && techLocList.size() > 0) {
                result = EZDFastTBLAccessor.insert(techLocList.toArray(new TECH_LOCTMsg[techLocList.size()]));
                if (result != techLocList.size()) {
                    addError(NMZM0053E);
                    return;
                }
            }
        }

        if (vndShipToXrefList.size() > 0) {
            result = EZDFastTBLAccessor.insert(vndShipToXrefList.toArray(new VND_SHIP_TO_XREFTMsg[vndShipToXrefList.size()]));
            if (result != vndShipToXrefList.size()) {
                addError(NMZM0182E);
                return;
            }
        }
    }

    /**
     * Creates TMsgs and sets them to given lists. (Caller should do
     * batch insert or update)
     * @param pMsg
     * @param swhInfoPMsg
     * @param swhList
     * @param ptyLocWrkList
     * @param shipToCustList
     * @param acctLocList
     * @param locUsgList
     * @param whList
     * @param techLocList
     */
    private void createRTL_SWHAndOthers(NMZC601001PMsg pMsg, NMZC601001_swhInfoListPMsg swhInfoPMsg, List<RTL_SWHTMsg> swhList, List<PTY_LOC_WRKTMsg> ptyLocWrkList,
            List<SHIP_TO_CUSTTMsg> shipToCustList, List<ACCT_LOCTMsg> acctLocList, List<LOC_USGTMsg> locUsgList, List<WHTMsg> whList, List<TECH_LOCTMsg> techLocList, int swhListCnt) {
        BigDecimal ptyLocPk = nextval(PTY_LOC_PK_SQ);
        BigDecimal shipToCustPk = nextval(SHIP_TO_CUST_PK_SQ);
        ZYPEZDItemValueSetter.setValue(swhInfoPMsg.invtyLocCd, pMsg.rtlWhCd.getValue().concat(swhInfoPMsg.rtlSwhCd.getValue()));

        swhList.add(createRTL_SWH(pMsg, swhInfoPMsg));

        if (!Y.equals(swhInfoPMsg.rtlSwhDsblFlg.getValue())) {
            ptyLocWrkList.add(createPTY_LOC_WRK(pMsg, swhInfoPMsg, ptyLocPk, swhListCnt));
            shipToCustList.add(createSHIP_TO_CUST(pMsg, swhInfoPMsg, ptyLocPk, shipToCustPk, swhListCnt));
            acctLocList.add(createACCT_LOC(pMsg, swhInfoPMsg, ptyLocPk, swhListCnt));
            createLOC_USGForDefault(pMsg, ptyLocPk, locUsgList);

            if (isLocTpWarehouse() && locUsgList.size() != 0) {
                whList.add(createWH(pMsg, swhInfoPMsg, ptyLocPk, swhListCnt));

            } else if (isLocTpTech() && locUsgList.size() != 0) {
                techLocList.add(createTECH_LOC(pMsg, swhInfoPMsg, ptyLocPk, swhListCnt));
            }
        }
    }

    /**
     * Creates TMsgs and sets them to given lists. (Caller should do
     * batch insert or update)
     * @param pMsg
     * @param swhInfoPMsg
     * @param swhList list to set RTL_SWH
     * @param ptyLocWrkList list to set PTY_LOC_WRK
     * @param shipToCustList list to set SHIP_TO_CUST
     * @param acctLocList
     * @param locUsgList
     * @param whList
     * @param techLocList
     * @param vndShipToXrefList
     * @param swhListCnt
     */
    private void createRTL_SWHAndOthersForRTLWHLevelRequire(NMZC601001PMsg pMsg, NMZC601001_swhInfoListPMsg swhInfoPMsg, List<RTL_SWHTMsg> swhList, List<PTY_LOC_WRKTMsg> ptyLocWrkList, List<SHIP_TO_CUSTTMsg> shipToCustList,
            List<ACCT_LOCTMsg> acctLocList, List<LOC_USGTMsg> locUsgList, List<WHTMsg> whList, List<TECH_LOCTMsg> techLocList, List<VND_SHIP_TO_XREFTMsg> vndShipToXrefList, Integer swhListCnt) {
        BigDecimal ptyLocPk = nextval(PTY_LOC_PK_SQ);
        BigDecimal shipToCustPk = nextval(SHIP_TO_CUST_PK_SQ);
        ZYPEZDItemValueSetter.setValue(swhInfoPMsg.invtyLocCd, pMsg.rtlWhCd.getValue().concat(swhInfoPMsg.rtlSwhCd.getValue()));

        ptyLocWrkList.add(createPTY_LOC_WRK(pMsg, swhInfoPMsg, ptyLocPk, 0));

        if (ptyLocWrkList.size() != 0) {
            shipToCustList.add(createSHIP_TO_CUST(pMsg, swhInfoPMsg, ptyLocPk, shipToCustPk, 0));
            acctLocList.add(createACCT_LOC(pMsg, swhInfoPMsg, ptyLocPk, 0));
        }

        // QC#2366: Create Ship to Cust of Tech-Hazmat
        if (isLocTpTech()) {

            String endOfCode = ZYPCodeDataUtil.getVarCharConstValue("END_OF_TECH_HAZMAT_CODE", pMsg.glblCmpyCd.getValue());

            if (!ZYPCommonFunc.hasValue(endOfCode)) {
                endOfCode = "-HAZMAT";
            }

            BigDecimal hazmatShipToPk = nextval(SHIP_TO_CUST_PK_SQ);
            BigDecimal hazmatptyLocPk = nextval(PTY_LOC_PK_SQ);
            ptyLocWrkList.add(createPTY_LOC_WRK_Hazmat(pMsg, swhInfoPMsg, hazmatptyLocPk, endOfCode));
            shipToCustList.add(createSHIP_TO_CUST_For_TechHazmat(pMsg, swhInfoPMsg, hazmatptyLocPk, hazmatShipToPk, endOfCode));
            acctLocList.add(createACCT_LOC_Hazmat(pMsg, swhInfoPMsg, hazmatptyLocPk, endOfCode));
        }

        if (swhListCnt == 0) {
            createLOC_USGForDefault(pMsg, ptyLocPk, locUsgList);

            if (isLocTpWarehouse() && locUsgList.size() != 0) {
                whList.add(createWH(pMsg, swhInfoPMsg, ptyLocPk, 0));

            } else if (isLocTpTech() && locUsgList.size() != 0) {
                techLocList.add(createTECH_LOC(pMsg, swhInfoPMsg, ptyLocPk, 0));
            }
        }

        createVND_SHIP_TO_XREF(pMsg, vndShipToXrefList);
    }

    /**
     * Creates TMsgs and sets them to given lists. (Caller should do
     * batch insert or update)
     * @param pMsg
     * @param swhInfoPMsg
     * @param swhList list to set RTL_SWH
     * @param ptyLocWrkList list to set PTY_LOC_WRK
     * @param shipToCustList list to set SHIP_TO_CUST
     */
    private void createRtrnToRelatedInfo(NMZC601001PMsg pMsg, List<PTY_LOC_WRKTMsg> ptyLocWrkList, List<SHIP_TO_CUSTTMsg> shipToCustList, List<ACCT_LOCTMsg> acctLocList) {

        BigDecimal ptyLocPk = nextval(PTY_LOC_PK_SQ);
        BigDecimal shipToCustPk = nextval(SHIP_TO_CUST_PK_SQ);

        ptyLocWrkList.add(createPTY_LOC_WRK_ForReturnTo(pMsg, ptyLocPk));
        shipToCustList.add(createSHIP_TO_CUST_ForReturnTo(pMsg, ptyLocPk, shipToCustPk));
        acctLocList.add(createACCT_LOC_ForReturnTo(pMsg, ptyLocPk));
    }

    /**
     * @param pMsg
     * @param swhInfoPMsg
     * @return
     */
    private RTL_SWHTMsg createRTL_SWH(NMZC601001PMsg pMsg, NMZC601001_swhInfoListPMsg swhInfoPMsg) {
        RTL_SWHTMsg tMsg = new RTL_SWHTMsg();
        EZDMsg.copy(pMsg, null, tMsg, null);
        EZDMsg.copy(swhInfoPMsg, null, tMsg, null);

        setValueIfNull(tMsg.prtyLocFlg, N);
        setValueIfNull(tMsg.rtlSwhDsblFlg, N);
        setValueIfNull(tMsg.effThruDt, MAX_DATE);

        if (PROCR_TP.SUPPLIER.equals(swhInfoPMsg.defSrcProcrTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.defSrcRtlWhCd, swhInfoPMsg.prntVndCd_AS);
            ZYPEZDItemValueSetter.setValue(tMsg.defSrcRtlSwhCd, swhInfoPMsg.vndCd_AS);
        }
        if (PROCR_TP.SUPPLIER.equals(swhInfoPMsg.defRtrnToProcrTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.defRtrnToRtlWhCd, swhInfoPMsg.prntVndCd_AR);
            ZYPEZDItemValueSetter.setValue(tMsg.defRtrnToRtlSwhCd, swhInfoPMsg.vndCd_AR);
        }

        return tMsg;
    }

    /**
     * @param pMsg
     * @param swhInfoPMsg
     * @param ptyLocPk
     * @return
     */
    private PTY_LOC_WRKTMsg createPTY_LOC_WRK(NMZC601001PMsg pMsg, NMZC601001_swhInfoListPMsg swhInfoPMsg, BigDecimal ptyLocPk, Integer swhListCnt) {
        PTY_LOC_WRKTMsg tMsg = new PTY_LOC_WRKTMsg();
        EZDMsg.copy(pMsg, null, tMsg, null);
        EZDMsg.copy(swhInfoPMsg, null, tMsg, null);

        setValue(tMsg.ptyLocPk, ptyLocPk);
        setValue(tMsg.cmpyPk, this.cmpyPk);
        if (swhListCnt == 0) {
            setValue(tMsg.locNum, pMsg.rtlWhCd);
            setValue(tMsg.locNm, pMsg.rtlWhNm);
        } else {
            setValue(tMsg.locNum, swhInfoPMsg.invtyLocCd);
            setValue(tMsg.locNm, getLocNmByJoint(pMsg, swhInfoPMsg));
        }
        tMsg.glnNum.clear();
        tMsg.dbaNm.clear();
        tMsg.dunsNum.clear();
        setValue(tMsg.apvlStsCd, APVL_STS.APPROVED);
        if (swhListCnt == 0) {
            setValue(tMsg.effFromDt, pMsg.effFromDt);
            setValue(tMsg.effThruDt, pMsg.effThruDt);
            setValueIfNull(tMsg.effThruDt, MAX_DATE);
        } else {
            setValue(tMsg.effFromDt, swhInfoPMsg.effFromDt);
            setValue(tMsg.effThruDt, swhInfoPMsg.effThruDt);
            setValueIfNull(tMsg.effThruDt, MAX_DATE);
        }
        setValue(tMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
        tMsg.dplStsCd.clear();
        tMsg.dplRsnTxt.clear();
        tMsg.dplRspDtTmTs.clear();
        tMsg.dplModByLoginUsrId.clear();
        tMsg.dplModDtTmTs.clear();
        setValue(tMsg.embgoFlg, N);

        tMsg.tmZoneCd.clear();
        tMsg.custTierCd.clear();
        tMsg.indyTpCd.clear();
        tMsg.lgcyCustClsCd.clear();
        setValue(tMsg.dsInsdCtyLimitFlg, N);
        tMsg.prfTechCd.clear();
        tMsg.reqTechCd.clear();
        // QC#15204
        setValue(tMsg.lineBizTpCd, this.lineBizTpCd);
        tMsg.dsUltDunsNum.clear();
        tMsg.dsPrntDunsNum.clear();
        tMsg.dsLocEmpNum.clear();
        tMsg.dsLocRevAmt.clear();
        tMsg.dsLastUpdDunsDt.clear();
        tMsg.hqDunsNum.clear();
        tMsg.dsCustSicCd.clear();
        tMsg.dsCustSicDescTxt.clear();
        setValue(tMsg.dsLocNm, pMsg.dsLocNm_S1);

        return tMsg;
    }

    /**
     * @param pMsg
     * @param swhInfoPMsg
     * @param ptyLocPk
     * @return
     */
    private SHIP_TO_CUSTTMsg createSHIP_TO_CUST(NMZC601001PMsg pMsg, NMZC601001_swhInfoListPMsg swhInfoPMsg, BigDecimal ptyLocPk, BigDecimal shipToCustPk, Integer swhListCnt) {
        SHIP_TO_CUSTTMsg tMsg = new SHIP_TO_CUSTTMsg();

        EZDMsg.copy(pMsg, null, tMsg, null);
        EZDMsg.copy(swhInfoPMsg, null, tMsg, null);

        setValue(tMsg.shipToCustPk, shipToCustPk);
        if (swhListCnt == 0) {
            setValue(tMsg.locNum, pMsg.rtlWhCd);
        } else {
            setValue(tMsg.locNum, swhInfoPMsg.invtyLocCd);

        }
        setValue(tMsg.locNm, pMsg.rtlWhNm);
        tMsg.glnNum.clear();
        tMsg.dbaNm.clear();
        tMsg.dunsNum.clear();
        setValue(tMsg.rgtnStsCd, getRgtnSts(swhInfoPMsg));
        setValue(tMsg.cnsgnFlg, N);
        setValue(tMsg.ptyLocPk, ptyLocPk);
        setValue(tMsg.locRoleTpCd, this.locRoleTpCd);
        setValue(tMsg.locGrpCd, this.locGrpCd);
        if (swhListCnt == 0) {
            setValue(tMsg.effFromDt, pMsg.effFromDt);
            setValue(tMsg.effThruDt, pMsg.effThruDt);
            setValueIfNull(tMsg.effThruDt, MAX_DATE);
        } else {
            setValue(tMsg.effFromDt, swhInfoPMsg.effFromDt);
            setValue(tMsg.effThruDt, swhInfoPMsg.effThruDt);
            setValueIfNull(tMsg.effThruDt, MAX_DATE);
        }
        setValue(tMsg.cmpyPk, this.cmpyPk);
        tMsg.prinCustPk.clear();
        setValue(tMsg.sellToCustCd, pMsg.dsAcctNum_S1);
        if (swhListCnt == 0) {
            setValue(tMsg.shipToCustCd, pMsg.rtlWhCd);
        } else {
            setValue(tMsg.shipToCustCd, swhInfoPMsg.invtyLocCd);
        }
        tMsg.geoCd.clear();
        tMsg.dplStsCd.clear();
        tMsg.dplRsnTxt.clear();
        tMsg.dplRspDtTmTs.clear();
        tMsg.dplModByLoginUsrId.clear();
        tMsg.dplModDtTmTs.clear();
        tMsg.imptInvCnsgnCd.clear();
        setValue(tMsg.oemFlg, N);
        setValue(tMsg.embgoFlg, N);

        setValue(tMsg.dsInsdCtyLimitFlg, N);
        tMsg.dsCustTaxCd.clear();
        tMsg.relnBillToCustCd.clear();
        setValue(tMsg.primUsgFlg, N);
        tMsg.dsTaxPrntTpCd.clear();
        tMsg.dsCustTaxCalcCd.clear();
        tMsg.dsTaxGrpExemCd.clear();
        setValue(tMsg.dsTaxExemFlg, N);
        tMsg.dsExemExprDt.clear();
        tMsg.coaCcCd.clear();
        tMsg.lineBizTpCd.clear();
        tMsg.indyTpCd.clear();
        tMsg.coaAfflCd.clear();
        tMsg.coaChCd.clear();
        tMsg.coaCmpyCd.clear();
        tMsg.coaBrCd.clear();
        tMsg.coaAcctCd.clear();
        tMsg.coaProdCd.clear();
        tMsg.coaProjCd.clear();
        tMsg.coaExtnCd.clear();
        tMsg.bigDealNum.clear();
        // 2023/11/06 QC#61924 Add Start
        setValue(tMsg.deacNewTrxFlg, N);
        // 2023/11/06 QC#61924 Add End

        return tMsg;
    }

    /**
     * @param pMsg
     * @param swhInfoPMsg
     * @param ptyLocPk
     * @return
     */
    private ACCT_LOCTMsg createACCT_LOC(NMZC601001PMsg pMsg, NMZC601001_swhInfoListPMsg swhInfoPMsg, BigDecimal ptyLocPk, Integer swhListCnt) {
        ACCT_LOCTMsg tMsg = new ACCT_LOCTMsg();
        EZDMsg.copy(pMsg, null, tMsg, null);
        EZDMsg.copy(swhInfoPMsg, null, tMsg, null);

        setValue(tMsg.acctLocPk, nextval(ACCT_LOC_PK_SQ));
        setValue(tMsg.dsAcctNum, pMsg.dsAcctNum_S1);
        if (swhListCnt == 0) {
            setValue(tMsg.locNum, pMsg.rtlWhCd);
        } else {
            setValue(tMsg.locNum, swhInfoPMsg.invtyLocCd);
        }
        setValue(tMsg.ptyLocPk, ptyLocPk);
        if (swhListCnt == 0) {
            setValue(tMsg.effFromDt, pMsg.effFromDt);
            setValue(tMsg.effThruDt, pMsg.effThruDt);
            setValueIfNull(tMsg.effThruDt, MAX_DATE);
        } else {
            setValue(tMsg.effFromDt, swhInfoPMsg.effFromDt);
            setValue(tMsg.effThruDt, swhInfoPMsg.effThruDt);
            setValueIfNull(tMsg.effThruDt, MAX_DATE);
        }
        setValue(tMsg.rgtnStsCd, getRgtnSts(swhInfoPMsg));

        return tMsg;
    }

    /**
     * @param pMsg NMZC601001PMsg
     * @param vndShipToXrefList 
     */
    private void createVND_SHIP_TO_XREF(NMZC601001PMsg pMsg, List<VND_SHIP_TO_XREFTMsg> vndShipToXrefList) {
 
        if (ZYPCommonFunc.hasValue(pMsg.vndLocCd_S1)) {
            VND_SHIP_TO_XREFTMsg tMsg = new VND_SHIP_TO_XREFTMsg();
            setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(tMsg.vndShipToXrefPk, nextval(VND_SHIP_TO_XREF_PK_SQ));
            tMsg.invtyLocCd.clear();
            setValue(tMsg.rtlWhCd, pMsg.rtlWhCd);
            tMsg.rtlSwhCd.clear();
            setValue(tMsg.prchGrpCd, CONST_VALUE_ASTERISK);
            tMsg.vndCd.clear();
            setValue(tMsg.vndXrefTpCd, VND_XREF_TP.CSA_TO_CUSA);
            setValue(tMsg.vndLocCd, pMsg.vndLocCd_S1);
            tMsg.vndShipToCustCd.clear();
            tMsg.vndShipToCustNm.clear();
            setValue(tMsg.effFromDt, pMsg.effFromDt);
            setValue(tMsg.effThruDt, pMsg.effThruDt);
            setValueIfNull(tMsg.effThruDt, MAX_DATE);
            vndShipToXrefList.add(tMsg);
        }
        if (ZYPCommonFunc.hasValue(pMsg.vndLocCd_R1)) {
            VND_SHIP_TO_XREFTMsg tMsg = new VND_SHIP_TO_XREFTMsg();
            setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(tMsg.vndShipToXrefPk, nextval(VND_SHIP_TO_XREF_PK_SQ));
            tMsg.invtyLocCd.clear();
            setValue(tMsg.rtlWhCd, pMsg.rtlWhCd);
            tMsg.rtlSwhCd.clear();
            setValue(tMsg.prchGrpCd, CONST_VALUE_ASTERISK);
            tMsg.vndCd.clear();
            setValue(tMsg.vndXrefTpCd, VND_XREF_TP.CSA_TO_CUSA_RETURN_TO);
            setValue(tMsg.vndLocCd, pMsg.vndLocCd_R1);
            tMsg.vndShipToCustCd.clear();
            tMsg.vndShipToCustNm.clear();
            setValue(tMsg.effFromDt, pMsg.effFromDt);
            setValue(tMsg.effThruDt, pMsg.effThruDt);
            setValueIfNull(tMsg.effThruDt, MAX_DATE);
            vndShipToXrefList.add(tMsg);
        }
    }

    /**
     * @param pMsg
     * @param ptyLocPk
     * @return
     */
    private PTY_LOC_WRKTMsg createPTY_LOC_WRK_ForReturnTo(NMZC601001PMsg pMsg, BigDecimal ptyLocPk) {
        PTY_LOC_WRKTMsg tMsg = new PTY_LOC_WRKTMsg();

        setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(tMsg.ptyLocPk, ptyLocPk);
        setValue(tMsg.cmpyPk, this.cmpyPk);
        setValue(tMsg.firstLineAddr, pMsg.rtrnToFirstLineAddr);
        setValue(tMsg.scdLineAddr, pMsg.rtrnToScdLineAddr);
        setValue(tMsg.thirdLineAddr, pMsg.rtrnToThirdLineAddr);
        setValue(tMsg.frthLineAddr, pMsg.rtrnToFrthLineAddr);
        setValue(tMsg.ctyAddr, pMsg.rtrnToCtyAddr);
        setValue(tMsg.cntyPk, pMsg.rtrnToCntyPk);
        setValue(tMsg.provNm, pMsg.rtrnToProvNm);
        setValue(tMsg.stCd, pMsg.rtrnToStCd);
        setValue(tMsg.postCd, pMsg.rtrnToPostCd);
        setValue(tMsg.ctryCd, pMsg.rtrnToCtryCd);
        setValue(tMsg.locNum, S21StringUtil.concatStrings(pMsg.rtlWhCd.getValue(), this.suffixForRtrnToCd));
        setValue(tMsg.locNm, pMsg.dsLocNm_R1);
        setValue(tMsg.addlLocNm, pMsg.rtrnToAddlLocNm);
        tMsg.glnNum.clear();
        setValue(tMsg.firstRefCmntTxt, pMsg.firstRefCmntTxt);
        setValue(tMsg.scdRefCmntTxt, pMsg.scdRefCmntTxt);
        tMsg.dbaNm.clear();
        tMsg.dunsNum.clear();
        setValue(tMsg.telNum, pMsg.telNum);
        setValue(tMsg.faxNum, pMsg.faxNum);
        setValue(tMsg.apvlStsCd, APVL_STS.APPROVED);
        tMsg.geoCd.clear();
        setValue(tMsg.effFromDt, pMsg.effFromDt);
        setValue(tMsg.effThruDt, pMsg.effThruDt);
        setValueIfNull(tMsg.effThruDt, MAX_DATE);
        setValue(tMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
        tMsg.dplStsCd.clear();
        tMsg.dplRsnTxt.clear();
        tMsg.dplRspDtTmTs.clear();
        tMsg.dplModByLoginUsrId.clear();
        tMsg.dplModDtTmTs.clear();
        setValue(tMsg.embgoFlg, N);

        tMsg.tmZoneCd.clear();
        tMsg.custTierCd.clear();
        tMsg.indyTpCd.clear();
        tMsg.lgcyCustClsCd.clear();
        setValue(tMsg.dsInsdCtyLimitFlg, N);
        tMsg.prfTechCd.clear();
        tMsg.reqTechCd.clear();
        // QC#15204
        setValue(tMsg.lineBizTpCd, this.lineBizTpCd);
        tMsg.dsUltDunsNum.clear();
        tMsg.dsPrntDunsNum.clear();
        tMsg.dsLocEmpNum.clear();
        tMsg.dsLocRevAmt.clear();
        tMsg.dsLastUpdDunsDt.clear();
        tMsg.hqDunsNum.clear();
        tMsg.dsCustSicCd.clear();
        tMsg.dsCustSicDescTxt.clear();
        tMsg.dsAcctNm.clear();
        setValue(tMsg.dsLocNm, pMsg.dsLocNm_R1);

        return tMsg;
    }

    /**
     * @param pMsg
     * @param ptyLocPk
     * @param shipToCustPk
     * @return
     */
    private SHIP_TO_CUSTTMsg createSHIP_TO_CUST_ForReturnTo(NMZC601001PMsg pMsg, BigDecimal ptyLocPk, BigDecimal shipToCustPk) {
        SHIP_TO_CUSTTMsg tMsg = new SHIP_TO_CUSTTMsg();

        setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(tMsg.shipToCustPk, shipToCustPk);
        setValue(tMsg.firstLineAddr, pMsg.rtrnToFirstLineAddr);
        setValue(tMsg.scdLineAddr, pMsg.rtrnToScdLineAddr);
        setValue(tMsg.thirdLineAddr, pMsg.rtrnToThirdLineAddr);
        setValue(tMsg.frthLineAddr, pMsg.rtrnToFrthLineAddr);
        setValue(tMsg.ctyAddr, pMsg.rtrnToCtyAddr);
        setValue(tMsg.cntyPk, pMsg.rtrnToCntyPk);
        setValue(tMsg.provNm, pMsg.rtrnToProvNm);
        setValue(tMsg.stCd, pMsg.rtrnToStCd);
        setValue(tMsg.postCd, pMsg.rtrnToPostCd);
        setValue(tMsg.ctryCd, pMsg.rtrnToCtryCd);
        setValue(tMsg.locNum, S21StringUtil.concatStrings(pMsg.rtlWhCd.getValue(), this.suffixForRtrnToCd));
        setValue(tMsg.locNm, pMsg.dsLocNm_R1);
        setValue(tMsg.addlLocNm, pMsg.rtrnToAddlLocNm);
        tMsg.glnNum.clear();
        setValue(tMsg.firstRefCmntTxt, pMsg.firstRefCmntTxt);
        setValue(tMsg.scdRefCmntTxt, pMsg.scdRefCmntTxt);
        tMsg.dbaNm.clear();
        tMsg.dunsNum.clear();
        setValue(tMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
        setValue(tMsg.cnsgnFlg, N);
        setValue(tMsg.ptyLocPk, ptyLocPk);
        setValue(tMsg.locRoleTpCd, this.locRoleTpCd);
        setValue(tMsg.locGrpCd, this.locGrpCd);
        setValue(tMsg.effFromDt, pMsg.effFromDt);
        setValue(tMsg.effThruDt, pMsg.effThruDt);
        setValueIfNull(tMsg.effThruDt, MAX_DATE);
        setValue(tMsg.cmpyPk, this.cmpyPk);
        tMsg.prinCustPk.clear();
        setValue(tMsg.faxNum, pMsg.faxNum);
        setValue(tMsg.telNum, pMsg.telNum);
        setValue(tMsg.sellToCustCd, pMsg.dsAcctNum_R1);
        setValue(tMsg.shipToCustCd, S21StringUtil.concatStrings(pMsg.rtlWhCd.getValue(), this.suffixForRtrnToCd));
        tMsg.geoCd.clear();
        tMsg.dplStsCd.clear();
        tMsg.dplRsnTxt.clear();
        tMsg.dplRspDtTmTs.clear();
        tMsg.dplModByLoginUsrId.clear();
        tMsg.dplModDtTmTs.clear();
        tMsg.imptInvCnsgnCd.clear();
        setValue(tMsg.oemFlg, N);
        setValue(tMsg.embgoFlg, N);

        setValue(tMsg.dsInsdCtyLimitFlg, N);
        tMsg.dsCustTaxCd.clear();
        tMsg.relnBillToCustCd.clear();
        setValue(tMsg.primUsgFlg, N);
        tMsg.dsTaxPrntTpCd.clear();
        tMsg.dsCustTaxCalcCd.clear();
        tMsg.dsTaxGrpExemCd.clear();
        setValue(tMsg.dsTaxExemFlg, N);
        tMsg.dsExemExprDt.clear();
        tMsg.coaCcCd.clear();
        tMsg.lineBizTpCd.clear();
        tMsg.indyTpCd.clear();
        tMsg.coaAfflCd.clear();
        tMsg.coaChCd.clear();
        tMsg.coaCmpyCd.clear();
        tMsg.coaBrCd.clear();
        tMsg.coaAcctCd.clear();
        tMsg.coaProdCd.clear();
        tMsg.coaProjCd.clear();
        tMsg.coaExtnCd.clear();
        tMsg.bigDealNum.clear();
        tMsg.dsAcctNm.clear();
        setValue(tMsg.dsLocNm, pMsg.dsLocNm_R1);
        // 2023/11/06 QC#61924 Add Start
        setValue(tMsg.deacNewTrxFlg, N);
        // 2023/11/06 QC#61924 Add End

        return tMsg;
    }

    /**
     * @param pMsg
     * @param ptyLocPk
     * @return
     */
    private ACCT_LOCTMsg createACCT_LOC_ForReturnTo(NMZC601001PMsg pMsg, BigDecimal ptyLocPk) {
        ACCT_LOCTMsg tMsg = new ACCT_LOCTMsg();

        setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(tMsg.acctLocPk, nextval(ACCT_LOC_PK_SQ));
        setValue(tMsg.dsAcctNum, pMsg.dsAcctNum_R1);
        setValue(tMsg.locNum, S21StringUtil.concatStrings(pMsg.rtlWhCd.getValue(), this.suffixForRtrnToCd));
        setValue(tMsg.ptyLocPk, ptyLocPk);
        setValue(tMsg.effFromDt, pMsg.effFromDt);
        setValue(tMsg.effThruDt, pMsg.effThruDt);
        setValueIfNull(tMsg.effThruDt, MAX_DATE);
        setValue(tMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);

        return tMsg;
    }

    /**
     * @param pMsg
     * @param ptyLocPk
     * @return
     */
    private void createLOC_USGForDefault(NMZC601001PMsg pMsg, BigDecimal ptyLocPk, List<LOC_USGTMsg> locUsgList) {
        RTL_WH_LOC_ROLE_PTRNTMsg inMsg = new RTL_WH_LOC_ROLE_PTRNTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.rtlWhCatgCd, pMsg.rtlWhCatgCd);
        setValue(inMsg.invtyOwnrCd, pMsg.invtyOwnrCd);
        setValue(inMsg.invtyAcctCd, pMsg.invtyAcctCd);

        inMsg = (RTL_WH_LOC_ROLE_PTRNTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (inMsg != null) {
            if (hasValue(inMsg.locRoleTpCd_01)) {
                LOC_USGTMsg tMsg = new LOC_USGTMsg();
                setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
                setValue(tMsg.locUsgPk, nextval(LOC_USG_PK_SQ));
                setValue(tMsg.ptyLocPk, ptyLocPk);
                setValue(tMsg.locRoleTpCd, inMsg.locRoleTpCd_01);
                setValue(tMsg.locGrpCd, this.locGrpCd);

                locUsgList.add(tMsg);
            }
            if (hasValue(inMsg.locRoleTpCd_02)) {
                LOC_USGTMsg tMsg = new LOC_USGTMsg();
                setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
                setValue(tMsg.locUsgPk, nextval(LOC_USG_PK_SQ));
                setValue(tMsg.ptyLocPk, ptyLocPk);
                setValue(tMsg.locRoleTpCd, inMsg.locRoleTpCd_02);
                setValue(tMsg.locGrpCd, this.locGrpCd);
                locUsgList.add(tMsg);
                
            }
            if (hasValue(inMsg.locRoleTpCd_03)) {
                LOC_USGTMsg tMsg = new LOC_USGTMsg();
                setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
                setValue(tMsg.locUsgPk, nextval(LOC_USG_PK_SQ));
                setValue(tMsg.ptyLocPk, ptyLocPk);
                setValue(tMsg.locRoleTpCd, inMsg.locRoleTpCd_03);
                setValue(tMsg.locGrpCd, this.locGrpCd);

                locUsgList.add(tMsg);
            }
        } else {
            // QC#21320
            addError(NMZM0362E);
        }
    }

    /**
     * @param pMsg
     * @param swhInfoPMsg
     * @param ptyLocPk
     * @return
     */
    private WHTMsg createWH(NMZC601001PMsg pMsg, NMZC601001_swhInfoListPMsg swhInfoPMsg, BigDecimal ptyLocPk, Integer swhListCnt) {
        WHTMsg tMsg = new WHTMsg();
        EZDMsg.copy(pMsg, null, tMsg, null);
        if (swhInfoPMsg != null) {
            EZDMsg.copy(swhInfoPMsg, null, tMsg, null);
        }

        setValue(tMsg.whPk, nextval(WH_PK_SQ));
        if (swhListCnt == 0) {
            setValue(tMsg.whCd, pMsg.rtlWhCd);
            setValue(tMsg.locNum, pMsg.rtlWhCd);
            setValue(tMsg.locNm, pMsg.rtlWhNm);
        } else {
            setValue(tMsg.whCd, swhInfoPMsg.invtyLocCd);
            setValue(tMsg.locNum, swhInfoPMsg.invtyLocCd);
            setValue(tMsg.locNm, getLocNmByJoint(pMsg, swhInfoPMsg));
        }
        tMsg.glnNum.clear();
        tMsg.dbaNm.clear();
        tMsg.dunsNum.clear();
        setValue(tMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
        setValue(tMsg.ptyLocPk, ptyLocPk);
        setValue(tMsg.locRoleTpCd, this.locRoleTpCd);
        setValue(tMsg.locGrpCd, this.locGrpCd);
        if (swhListCnt == 0) {
            setValue(tMsg.effFromDt, pMsg.effFromDt);
            setValue(tMsg.effThruDt, pMsg.effThruDt);
            setValueIfNull(tMsg.effThruDt, MAX_DATE);
        } else {
            setValue(tMsg.effFromDt, swhInfoPMsg.effFromDt);
            setValue(tMsg.effThruDt, swhInfoPMsg.effThruDt);
            setValueIfNull(tMsg.effThruDt, MAX_DATE);
        }
        if (WH_SYS_TP.WMS.equals(tMsg.whSysTpCd.getValue())) {
            setValueIfNull(tMsg.soPrinReqFlg, Y);
            setValueIfNull(tMsg.rwsPrinReqFlg, Y);
        } else {
            setValueIfNull(tMsg.soPrinReqFlg, N);
            setValueIfNull(tMsg.rwsPrinReqFlg, N);
        }
        setValue(tMsg.cmpyPk, this.cmpyPk);

        return tMsg;
    }

    /**
     * @param pMsg
     * @param swhInfoPMsg
     * @param ptyLocPk
     * @return
     */
    private TECH_LOCTMsg createTECH_LOC(NMZC601001PMsg pMsg, NMZC601001_swhInfoListPMsg swhInfoPMsg, BigDecimal ptyLocPk, Integer swhListCnt) {
        TECH_LOCTMsg tMsg = new TECH_LOCTMsg();
        EZDMsg.copy(pMsg, null, tMsg, null);
        if (swhInfoPMsg != null) {
            EZDMsg.copy(swhInfoPMsg, null, tMsg, null);
        }

        setValue(tMsg.techLocPk, nextval(TECH_LOC_PK_SQ));
        if (swhListCnt == 0) {
            setValue(tMsg.techLocCd, pMsg.rtlWhCd);
        } else {
            setValue(tMsg.techLocCd, swhInfoPMsg.invtyLocCd);
        }
        setValue(tMsg.locTpCd, this.locTpCd);
        setValue(tMsg.locRoleTpCd, this.locRoleTpCd);
        setValue(tMsg.techLocTpCd, TECH_LOC_TP.TECHNICIAN);
        setValue(tMsg.locGrpCd, this.locGrpCd);
        if (swhListCnt == 0) {
            setValue(tMsg.locNm, pMsg.rtlWhNm);
        } else {
            setValue(tMsg.locNm, getLocNmByJoint(pMsg, swhInfoPMsg));
        }
        setValue(tMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
        setValue(tMsg.ptyLocPk, ptyLocPk);
        if (swhListCnt == 0) {
            setValue(tMsg.effFromDt, pMsg.effFromDt);
            setValue(tMsg.effThruDt, pMsg.effThruDt);
            setValueIfNull(tMsg.effThruDt, MAX_DATE);
        } else {
            setValue(tMsg.effFromDt, swhInfoPMsg.effFromDt);
            setValue(tMsg.effThruDt, swhInfoPMsg.effThruDt);
            setValueIfNull(tMsg.effThruDt, MAX_DATE);
        }
        setValue(tMsg.allwAutoRcptFlg, N);
        setValue(tMsg.allwAutoExpFlg, Y);
        setValue(tMsg.trkEmerOrdInfoFlg, Y);
        setValue(tMsg.rplshMonFlg, N);
        setValue(tMsg.rplshTueFlg, N);
        setValue(tMsg.rplshWedFlg, N);
        setValue(tMsg.rplshThuFlg, N);
        setValue(tMsg.rplshFriFlg, N);
        setValue(tMsg.blockRplshFlg, N);
        tMsg.blockRplshDt.clear();
        tMsg.blockRplshUpdUsrId.clear();
        tMsg.blockRplshUpdDt.clear();
        if (isY(swhInfoPMsg.rtlSwhDsblFlg)) {
            String salesDate = ZYPDateUtil.getSalesDate();
            setValue(tMsg.locTrmnDt, salesDate);
            setValue(tMsg.trmnFlgUpdUsrId, this.userInfo.getUserId());
            setValue(tMsg.trmnFlgUpdUsrDt, salesDate);
        } else {
            tMsg.locTrmnDt.clear();
            tMsg.trmnFlgUpdUsrId.clear();
            tMsg.trmnFlgUpdUsrDt.clear();
        }
        setValueNvl(tMsg.prtyLocFlg, swhInfoPMsg.prtyLocFlg, N);

        return tMsg;
    }

    /**
     * @param pMsg
     */
    private void executeUpdate(NMZC601001PMsg pMsg) {
        preparePTYInfo(pMsg);
        deleteRTL_WHAndOthers(pMsg);
        if (hasError()) {
            return;
        }

        updateAndInsertRTL_WHAndOthers(pMsg);
        if (hasError()) {
            return;
        }
        updateRTL_WH(pMsg);
    }

    /**
     * @param pMsg
     */
    private void updateRTL_WH(NMZC601001PMsg pMsg) {
        RTL_WHTMsg tMsg = selectRTL_WH(pMsg);
        if (tMsg == null) {
            addError(NMZM0046E);
            return;
        }

        EZDMsg.copy(pMsg, null, tMsg, null);

        setValueIfNull(tMsg.autoSoDropFlg, N);
        if (WH_SYS_TP.WMS.equals(tMsg.whSysTpCd.getValue())) {
            setValueIfNull(tMsg.soPrinReqFlg, Y);
            setValueIfNull(tMsg.rwsPrinReqFlg, Y);
        } else {
            setValueIfNull(tMsg.soPrinReqFlg, N);
            setValueIfNull(tMsg.rwsPrinReqFlg, N);
        }
        setValueIfNull(tMsg.effThruDt, MAX_DATE);

        if (PROCR_TP.SUPPLIER.equals(pMsg.defSrcProcrTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.defSrcRtlWhCd, pMsg.prntVndCd_SD);
            ZYPEZDItemValueSetter.setValue(tMsg.defSrcRtlSwhCd, pMsg.vndCd_SD);
        }

        if (PROCR_TP.SUPPLIER.equals(pMsg.emerSrcProcrTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.emerSrcRtlWhCd, pMsg.prntVndCd_SE);
            ZYPEZDItemValueSetter.setValue(tMsg.emerSrcRtlSwhCd, pMsg.vndCd_SE);
        }

        if (PROCR_TP.SUPPLIER.equals(pMsg.defRtrnToProcrTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.defRtrnToRtlWhCd, pMsg.prntVndCd_SR);
            ZYPEZDItemValueSetter.setValue(tMsg.defRtrnToRtlSwhCd, pMsg.vndCd_SR);
        }

        // Set Ship-To Code
        if (ZYPCommonFunc.hasValue(pMsg.shipToCustCd)) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, pMsg.shipToCustCd);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, pMsg.rtlWhCd);
        }

        // Set Return-To Code
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxChkBox.getValue())) {
            if (ZYPCommonFunc.hasValue(pMsg.shipToCustCd)) {
                ZYPEZDItemValueSetter.setValue(tMsg.rtrnToCustCd, pMsg.shipToCustCd);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.rtrnToCustCd, pMsg.rtlWhCd);
            }
        } else {
            if (ZYPCommonFunc.hasValue(pMsg.rtrnToCustCd)) {
                ZYPEZDItemValueSetter.setValue(tMsg.rtrnToCustCd, pMsg.rtrnToCustCd);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.rtrnToCustCd, S21StringUtil.concatStrings(pMsg.rtlWhCd.getValue(), this.suffixForRtrnToCd));
            }
        }

        // QC#17362
        ZYPEZDItemValueSetter.setValue(tMsg.prntVndCd, pMsg.prntVndCd);
        ZYPEZDItemValueSetter.setValue(tMsg.vndCd, pMsg.vndCd);
        // QC#22212
        ZYPEZDItemValueSetter.setValue(tMsg.techMblMsgAddr, pMsg.techMblMsgAddr);

        EZDTBLAccessor.update(tMsg);
        if (!isCodeNormal(tMsg)) {
            addError(NMZM0046E);
            return;
        }
    }

    /**
     * @param pMsg
     */
    private void updateAndInsertRTL_WHAndOthers(NMZC601001PMsg pMsg) {
        int cnt = pMsg.swhInfoList.getValidCount();

        List<PTY_LOC_WRKTMsg> ptyLocWrkUpdateList = new ArrayList<PTY_LOC_WRKTMsg>();
        List<SHIP_TO_CUSTTMsg> shipToCustUpdateList = new ArrayList<SHIP_TO_CUSTTMsg>();
        List<ACCT_LOCTMsg> acctLocUpdateList = new ArrayList<ACCT_LOCTMsg>();
        List<LOC_USGTMsg> locUsgCreateList = new ArrayList<LOC_USGTMsg>();
        List<WHTMsg> whCreateList = new ArrayList<WHTMsg>();
        List<TECH_LOCTMsg> techLocCreateList = new ArrayList<TECH_LOCTMsg>();
        List<VND_SHIP_TO_XREFTMsg> vndShipToXrefCreateList = new ArrayList<VND_SHIP_TO_XREFTMsg>();
        List<ACCT_LOCTMsg> acctLocCreateList = new ArrayList<ACCT_LOCTMsg>();
        List<RTL_SWHTMsg> swhCreateList = new ArrayList<RTL_SWHTMsg>();
        List<PTY_LOC_WRKTMsg> ptyLocWrkCreateList = new ArrayList<PTY_LOC_WRKTMsg>();
        List<SHIP_TO_CUSTTMsg> shipToCustCreateList = new ArrayList<SHIP_TO_CUSTTMsg>();

        /*******************************************/
        /******** RTL_WH Level (Update) ************/
        /*******************************************/
        // Update
        List<PTY_LOC_WRKTMsg> ptyLocWrkTmpList = createPTY_LOC_WRKForUpdate(pMsg);
        if (ptyLocWrkTmpList != null && !ptyLocWrkTmpList.isEmpty()) {
            ptyLocWrkUpdateList.addAll(ptyLocWrkTmpList);
        } else {
            addError(NMZM0048E);
            return;
        }

        List<SHIP_TO_CUSTTMsg> shipToCustTmpList = createSHIP_TO_CUSTForUpdate(pMsg);
        if (shipToCustTmpList != null && !shipToCustTmpList.isEmpty()) {
            shipToCustUpdateList.addAll(shipToCustTmpList);
        }

        List<ACCT_LOCTMsg> acctLocTmpList = createACCT_LOCForUpdate(pMsg);
        if (acctLocTmpList != null && !acctLocTmpList.isEmpty()) {
            acctLocUpdateList.addAll(acctLocTmpList);
        }

        /**********************************************/
        /******** Retail WH Level (Insert) ************/
        /**********************************************/
        PTY_LOC_WRKTMsg ptyLocWrkTMsg = ptyLocWrkUpdateList.get(0);
        BigDecimal ptyLocPk = ptyLocWrkTMsg.ptyLocPk.getValue();

        if (cnt == 0) {
            createLOC_USGForDefault(pMsg, ptyLocPk, locUsgCreateList);

            if (locUsgCreateList.size() != 0) {
                if (isLocTpWarehouse()) {
                    whCreateList.add(createWH(pMsg, null, ptyLocPk, 0));

                } else if (isLocTpTech()) {
                    techLocCreateList.add(createTECH_LOC(pMsg, null, ptyLocPk, 0));
                }
            }
        }

        createVND_SHIP_TO_XREF(pMsg, vndShipToXrefCreateList);

        if (!ZYPConstant.FLG_ON_Y.equals(pMsg.xxChkBox.getValue()) && !ZYPCommonFunc.hasValue(pMsg.rtrnToCustCd)) {
            createRtrnToRelatedInfo(pMsg, ptyLocWrkCreateList, shipToCustCreateList, acctLocCreateList);
        }

        /*************************************************/
        /******** Retail Sub-WH Level (Insert) ***********/
        /*************************************************/
        // create all tMsgs of SWH Level
        NMZC601001_swhInfoListPMsg swhInfoPMsg;
        for (int i = 0; i < cnt; i++) {
            swhInfoPMsg = pMsg.swhInfoList.no(i);
            createRTL_SWHAndOthers(pMsg,
                    swhInfoPMsg,
                    swhCreateList,
                    ptyLocWrkCreateList,
                    shipToCustCreateList,
                    acctLocCreateList,
                    locUsgCreateList,
                    whCreateList,
                    techLocCreateList,
                    cnt);
        }

        batchUpdate(ptyLocWrkUpdateList, shipToCustUpdateList, acctLocUpdateList);
        batchInsert(swhCreateList, ptyLocWrkCreateList, shipToCustCreateList, acctLocCreateList, locUsgCreateList, whCreateList, techLocCreateList, vndShipToXrefCreateList);
    }

    /**
     * @param pMsg
     */
    private void deleteRTL_WHAndOthers(NMZC601001PMsg pMsg) {

        /**********************************/
        /******** WH Level ****************/
        /**********************************/
        // Delete VND_SHIP_TO_XREF
        List<Map> vndShipToXrefMapList = getVndShipToXrefMapList(pMsg);
        if (!deleteVndShipToXref(pMsg, vndShipToXrefMapList)) {
            return;
        }

        /**********************************/
        /******** SWH Level ***************/
        /**********************************/
        List<Map> rtlSwhMapList = getRtlSwhMapList(pMsg);
        if (rtlSwhMapList!= null && rtlSwhMapList.size() > 0) {

            // Delete RTL_SWH
            if (!deleteRtlSwh(pMsg, rtlSwhMapList)) {
                return;
            }
            // Delete SHIP_TO_CUST
            List<Map> shipToCustMapListForSwhLevel = getShipToCustMapListForSwhLevel(pMsg, rtlSwhMapList);
            if (!deleteShipToCust(pMsg, shipToCustMapListForSwhLevel)) {
                return;
            }
            // Delete ACCT_LOC
            List<Map> acctLocMapListForSwhLevel = getAcctLocMapListForSwhLevel(pMsg, rtlSwhMapList);
            if (!deleteAcctLoc(pMsg, acctLocMapListForSwhLevel)) {
                return;
            }
            // Delete PTY_LOC_WRK
            if (!deletePtyLocWrk(pMsg, shipToCustMapListForSwhLevel)) {
                return;
            }
        }

        /**********************************/
        /******** WH and SWH Level ********/
        /**********************************/
        // Delete WH and LOC_USG
        List<Map> whMapList = getWhMapList(pMsg, rtlSwhMapList);
        if (whMapList != null && whMapList.size() > 0) {
            if (!deleteWhAndLocUsg(pMsg, whMapList)) {
                return;
            }
        }

        // Delete TECH_LOC and LOC_USG
        List<Map> techLocMapList = getTechLocMapList(pMsg, rtlSwhMapList);
        if (techLocMapList != null && techLocMapList.size() > 0) {
            if (!deleteTechLocAndLocUsg(pMsg, techLocMapList)) {
                return;
            }
        }
    }

    /**
     * @param swhList
     * @param ptyLocWrkList
     * @param shipToCustList
     * @param acctLocList
     * @param whList
     * @param techLocList
     */
    private void batchUpdate(List<PTY_LOC_WRKTMsg> ptyLocWrkList, List<SHIP_TO_CUSTTMsg> shipToCustList, List<ACCT_LOCTMsg> acctLocList) {
        int result;

        result = EZDFastTBLAccessor.update(ptyLocWrkList.toArray(new PTY_LOC_WRKTMsg[ptyLocWrkList.size()]));
        if (result != ptyLocWrkList.size()) {
            addError(NMZM0048E);
            return;
        }

        result = EZDFastTBLAccessor.update(shipToCustList.toArray(new SHIP_TO_CUSTTMsg[shipToCustList.size()]));
        if (result != shipToCustList.size()) {
            addError(NMZM0050E);
            return;
        }

        result = EZDFastTBLAccessor.update(acctLocList.toArray(new ACCT_LOCTMsg[acctLocList.size()]));
        if (result != acctLocList.size()) {
            addError(NMZM0093E);
            return;
        }
    }

    /**
     * @param pMsg
     * @param swhInfoPMsg
     * @return
     */
    private List<PTY_LOC_WRKTMsg> createPTY_LOC_WRKForUpdate(NMZC601001PMsg pMsg) {
        PTY_LOC_WRKTMsgArray arr = selectPtyLocWrkForUpdate(pMsg);
        int cnt = arr.getValidCount();
        if (cnt == 0) {
            return null;
        }

        List<PTY_LOC_WRKTMsg> list = new ArrayList<PTY_LOC_WRKTMsg>(cnt);

        for (int i = 0; i < cnt; i++) {
            PTY_LOC_WRKTMsg tMsg = arr.no(i);

            setValue(tMsg.firstLineAddr, pMsg.firstLineAddr);
            setValue(tMsg.scdLineAddr, pMsg.scdLineAddr);
            setValue(tMsg.thirdLineAddr, pMsg.thirdLineAddr);
            setValue(tMsg.frthLineAddr, pMsg.frthLineAddr);
            setValue(tMsg.ctyAddr, pMsg.ctyAddr);
            setValue(tMsg.cntyPk, pMsg.cntyPk);
            setValue(tMsg.provNm, pMsg.provNm);
            setValue(tMsg.stCd, pMsg.stCd);
            setValue(tMsg.postCd, pMsg.postCd);
            setValue(tMsg.ctryCd, pMsg.ctryCd);
            setValue(tMsg.locNm, pMsg.rtlWhNm);
            setValue(tMsg.addlLocNm, pMsg.addlLocNm);
            setValue(tMsg.firstRefCmntTxt, pMsg.firstRefCmntTxt);
            setValue(tMsg.scdRefCmntTxt, pMsg.scdRefCmntTxt);
            setValue(tMsg.telNum, pMsg.telNum);
            setValue(tMsg.faxNum, pMsg.faxNum);
            setValue(tMsg.apvlStsCd, APVL_STS.APPROVED);
            setValue(tMsg.geoCd, pMsg.geoCd);
            setValue(tMsg.effFromDt, pMsg.effFromDt);
            if (ZYPCommonFunc.hasValue(pMsg.effThruDt)) {
                setValue(tMsg.effThruDt, pMsg.effThruDt);
            } else {
                setValue(tMsg.effThruDt, MAX_DATE);
            }
            setValue(tMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
            setValue(tMsg.dsLocNm, pMsg.dsLocNm_S1);
            list.add(tMsg);
        }

        return list;
    }

    /**
     * @param pMsg
     * @param swhInfoPMsg
     * @return
     */
    private List<SHIP_TO_CUSTTMsg> createSHIP_TO_CUSTForUpdate(NMZC601001PMsg pMsg) {
        SHIP_TO_CUSTTMsgArray arr = selectShipToCustForUpdate(pMsg);
        int cnt = arr.getValidCount();
        if (cnt == 0) {
            addError(NMZM0050E);
            return Collections.emptyList();
        }

        List<SHIP_TO_CUSTTMsg> list = new ArrayList<SHIP_TO_CUSTTMsg>(cnt);

        for (int i = 0; i < cnt; i++) {
            SHIP_TO_CUSTTMsg tMsg = arr.no(i);

            setValue(tMsg.firstLineAddr, pMsg.firstLineAddr);
            setValue(tMsg.scdLineAddr, pMsg.scdLineAddr);
            setValue(tMsg.thirdLineAddr, pMsg.thirdLineAddr);
            setValue(tMsg.frthLineAddr, pMsg.frthLineAddr);
            setValue(tMsg.ctyAddr, pMsg.ctyAddr);
            setValue(tMsg.cntyPk, pMsg.cntyPk);
            setValue(tMsg.provNm, pMsg.provNm);
            setValue(tMsg.stCd, pMsg.stCd);
            setValue(tMsg.postCd, pMsg.postCd);
            setValue(tMsg.ctryCd, pMsg.ctryCd);
            setValue(tMsg.locNm, pMsg.rtlWhNm);
            setValue(tMsg.addlLocNm, pMsg.addlLocNm);
            setValue(tMsg.firstRefCmntTxt, pMsg.firstRefCmntTxt);
            setValue(tMsg.scdRefCmntTxt, pMsg.scdRefCmntTxt);
            setValue(tMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
            setValue(tMsg.locRoleTpCd, this.locRoleTpCd);
            setValue(tMsg.locGrpCd, this.locGrpCd);
            setValue(tMsg.effFromDt, pMsg.effFromDt);
            if (ZYPCommonFunc.hasValue(pMsg.effThruDt)) {
                setValue(tMsg.effThruDt, pMsg.effThruDt);
            } else {
                setValue(tMsg.effThruDt, MAX_DATE);
            }
            setValue(tMsg.faxNum, pMsg.faxNum);
            setValue(tMsg.telNum, pMsg.telNum);

            list.add(tMsg);
        }

        return list;
    }

    /**
     * @param pMsg
     * @return
     */
    private List<ACCT_LOCTMsg> createACCT_LOCForUpdate(NMZC601001PMsg pMsg) {
        ACCT_LOCTMsgArray arr = selectAcctLocForUpdate(pMsg);
        int cnt = arr.getValidCount();
        if (cnt == 0) {
            addError(NMZM0093E);
            return Collections.emptyList();
        }

        List<ACCT_LOCTMsg> list = new ArrayList<ACCT_LOCTMsg>(cnt);

        for (int i = 0; i < cnt; i++) {
            ACCT_LOCTMsg tMsg = arr.no(i);

            setValue(tMsg.effFromDt, pMsg.effFromDt);
            if (ZYPCommonFunc.hasValue(pMsg.effThruDt)) {
                setValue(tMsg.effThruDt, pMsg.effThruDt);
            } else {
                setValue(tMsg.effThruDt, MAX_DATE);
            }
            setValue(tMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);

            list.add(tMsg);
        }

        return list;
    }

    /**
     * Get PTY information and set them to instance fields.
     * @param pMsg
     */
    private void preparePTYInfo(NMZC601001PMsg pMsg) {
        this.cmpyPk = getCmpyPk(pMsg);
        Map<String, String> locGroup = getLocGroup(pMsg);
        this.locTpCd = locGroup.get("LOC_TP_CD");
        this.locGrpCd = locGroup.get("LOC_GRP_CD");
        this.locRoleTpCd = locGroup.get("LOC_ROLE_TP_CD");
    }

    /**
     * @param pMsg
     * @return CMPY_PK
     */
    private BigDecimal getCmpyPk(NMZC601001PMsg pMsg) {
        Map<String, Object> param = createSsmParam(pMsg);
        param.put("CUSA_PRIN", LOC_ROLE_TP.CANON_U_S_A_PRINCIPAL);

        return (BigDecimal) this.ssmBatchClient.queryObject("getCmpyPk", param);
    }

    /**
     * @param pMsg
     * @return LOC_TP_CD, LOC_GRP_CD and ROLE_TP_CD
     */
    private Map<String, String> getLocGroup(NMZC601001PMsg pMsg) {
        return (Map) this.ssmBatchClient.queryObject("getLocGroup", createSsmParam(pMsg));
    }

    /**
     * @param pMsg
     * @return Map with putting PMsg as "msg"
     */
    private Map<String, Object> createSsmParam(NMZC601001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("msg", pMsg);
        return param;
    }

    /**
     * @param pMsg
     * @return selected RTL_WH
     */
    private RTL_WHTMsg selectRTL_WH(NMZC601001PMsg pMsg) {
        RTL_WHTMsg inMsg = new RTL_WHTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.rtlWhCd, pMsg.rtlWhCd);

        return (RTL_WHTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * @param pMsg
     * @return selected PTY_LOC_WRK
     */
    private PTY_LOC_WRKTMsgArray selectPtyLocWrkForUpdate(NMZC601001PMsg pMsg) {
        PTY_LOC_WRKTMsg inMsg = new PTY_LOC_WRKTMsg();
        inMsg.setSQLID("012");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("locNum01", pMsg.rtlWhCd.getValue());

        return (PTY_LOC_WRKTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);
    }

    /**
     * @param pMsg
     * @return selected list of SHIP_TO_CUST
     */
    private SHIP_TO_CUSTTMsgArray selectShipToCustForUpdate(NMZC601001PMsg pMsg) {
        SHIP_TO_CUSTTMsg inMsg = new SHIP_TO_CUSTTMsg();
        inMsg.setSQLID("048");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("locNum01", pMsg.rtlWhCd.getValue());

        return (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);
    }

    /**
     * @param pMsg
     * @param swhInfoPMsg
     * @return selected list of ACCT_LOC
     */
    private ACCT_LOCTMsgArray selectAcctLocForUpdate(NMZC601001PMsg pMsg) {
        ACCT_LOCTMsg inMsg = new ACCT_LOCTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("locNum01", pMsg.rtlWhCd.getValue());

        return (ACCT_LOCTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);
    }

    // ----------------------------
    // Util
    // ----------------------------

    /**
     * Set given value to given item only when given item has not
     * value.
     * @param item
     * @param value
     */
    private static void setValueIfNull(EZDTStringItem item, String value) {
        if (!hasValue(item)) {
            setValue(item, value);
        }
    }

    /**
     * Set given value to given item only when given item has not
     * value.
     * @param item
     * @param value
     */
    private static void setValueIfNull(EZDTDateItem item, String value) {
        if (!hasValue(item)) {
            setValue(item, value);
        }
    }

    /**
     * Set src value to dst if src has value, otherwise set value to
     * dst.
     * @param dst
     * @param src
     * @param value
     */
    private static void setValueNvl(EZDTStringItem dst, EZDPStringItem src, String value) {
        if (hasValue(src)) {
            setValue(dst, src);
        } else {
            setValue(dst, value);
        }
    }

    /**
     * @param tMsg
     * @return true if return code of given TMsg is normal.
     */
    private boolean isCodeNormal(EZDTMsg tMsg) {
        return RETURN_CODE_NORMAL.equals(tMsg.getReturnCode());
    }

    /**
     * @param pMsg
     * @param swhInfoPMsg
     * @return RTL_WH_NM + RTL_SWH_NM
     */
    private String getLocNmByJoint(NMZC601001PMsg pMsg, NMZC601001_swhInfoListPMsg swhInfoPMsg) {
        return pMsg.rtlWhNm.getValue() + LOC_NM_JOINT + swhInfoPMsg.rtlSwhNm.getValue();
    }

    /**
     * @param item
     * @return true if flag is Y.
     */
    private static boolean isY(EZDPStringItem item) {
        return Y.equals(item.getValue());
    }

    /**
     * @param sqName
     * @return next BigDecimal value of given sequence.
     */
    private static BigDecimal nextval(String sqName) {
        return BigDecimal.valueOf(EZDSeqTable.getNumberInt(sqName));
    }

    /**
     * @param swhInfoPMsg
     * @return appropriate RGTN_STS_CD for given RTL_SWH_DSBL_FLG
     */
    private static String getRgtnSts(NMZC601001_swhInfoListPMsg swhInfoPMsg) {
        EZDPStringItem flgItem = swhInfoPMsg.rtlSwhDsblFlg;

        if (isY(flgItem)) {
            return RGTN_STS.TERMINATED;

        } else {
            return RGTN_STS.READY_FOR_ORDER_TAKING;
        }
    }

    /**
     * @return true if LOC_TP_CD means WAREHOUSE.
     */
    private boolean isLocTpWarehouse() {
        return LOC_TP.WAREHOUSE.equals(this.locTpCd);
    }

    /**
     * @return true if LOC_TP_CD means TECHNICIAN.
     */
    private boolean isLocTpTech() {
        return LOC_TP.TECHNICIAN.equals(this.locTpCd);
    }

    /**
     * @param pMsg
     * @param rtlSwhMapList
     * @return List<Map>
     */
    private List<Map> getShipToCustMapListForSwhLevel(NMZC601001PMsg pMsg, List<Map> rtlSwhMapList) {

        List<String> locNumList = new ArrayList<String>();
        if (rtlSwhMapList != null) {
            for (int i = 0; i < rtlSwhMapList.size(); i++) {
                locNumList.add((String)rtlSwhMapList.get(i).get("INVTY_LOC_CD"));
            }
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd);
        queryParam.put("locNumList", locNumList);

        return (List<Map>) ssmBatchClient.queryObjectList("getShipToCustMapList", queryParam);
    }

    /**
     * @param pMsg
     * @param rtlSwhMapList
     * @return List<Map>
     */
    private List<Map> getAcctLocMapListForSwhLevel(NMZC601001PMsg pMsg, List<Map> rtlSwhMapList) {

        List<String> locNumList = new ArrayList<String>();
        if (rtlSwhMapList != null) {
            for (int i = 0; i < rtlSwhMapList.size(); i++) {
                locNumList.add((String)rtlSwhMapList.get(i).get("INVTY_LOC_CD"));
            }
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd);
        queryParam.put("locNumList", locNumList);

        return (List<Map>) ssmBatchClient.queryObjectList("getAcctLocMapList", queryParam);
    }

    /**
     * @param pMsg
     * @param rtlSwhMapList
     * @return List<Map>
     */
    private List<Map> getWhMapList(NMZC601001PMsg pMsg, List<Map> rtlSwhMapList) {

        List<String> whCdList = new ArrayList<String>();
        whCdList.add(pMsg.rtlWhCd.getValue());
        if (rtlSwhMapList != null) {
            for (int i = 0; i < rtlSwhMapList.size(); i++) {
                whCdList.add((String)rtlSwhMapList.get(i).get("INVTY_LOC_CD"));
            }
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd);
        queryParam.put("whCdList", whCdList);

        return (List<Map>) ssmBatchClient.queryObjectList("getWhMapList", queryParam);
    }

    /**
     * @param pMsg
     * @param rtlSwhMapList
     * @return List<Map>
     */
    private List<Map> getTechLocMapList(NMZC601001PMsg pMsg, List<Map> rtlSwhMapList) {

        List<String> techLocCdList = new ArrayList<String>();
        techLocCdList.add(pMsg.rtlWhCd.getValue());
        if (rtlSwhMapList != null) {
            for (int i = 0; i < rtlSwhMapList.size(); i++) {
                techLocCdList.add((String)rtlSwhMapList.get(i).get("INVTY_LOC_CD"));
            }
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd);
        queryParam.put("techLocCdList", techLocCdList);

        return (List<Map>) ssmBatchClient.queryObjectList("getTechLocMapList", queryParam);
    }

    /**
     * @param pMsg
     * @param rtlSwhMapList
     * @return List<Map>
     */
    private List<Map> getLocUsgMapList(NMZC601001PMsg pMsg, List<BigDecimal> ptyLocPkList) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd);
        queryParam.put("ptyLocPkList", ptyLocPkList);

        return (List<Map>) ssmBatchClient.queryObjectList("getLocUsgMapList", queryParam);
    }

    /**
     * @param pMsg
     * @return List<Map>
     */
    private List<Map> getRtlSwhMapList(NMZC601001PMsg pMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd);
        queryParam.put("rtlWhCd", pMsg.rtlWhCd);

        return (List<Map>) ssmBatchClient.queryObjectList("getRtlSwhMapList", queryParam);
    }

    /**
     * @param pMsg
     * @return List<Map>
     */
    private List<Map> getVndShipToXrefMapList(NMZC601001PMsg pMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd);
        queryParam.put("rtlWhCd", pMsg.rtlWhCd);

        return (List<Map>) ssmBatchClient.queryObjectList("getVndShipToXrefMapList", queryParam);
    }

    /**
     * @param pMsg
     * @param shipToCustMapList
     * @return boolean (true : No Error / false : Error)
     */
    private boolean deleteShipToCust(NMZC601001PMsg pMsg, List<Map> shipToCustMapList) {

        boolean noErrorFlag = true;

        if (shipToCustMapList == null || shipToCustMapList.size() == 0) {
            return noErrorFlag;
        }

        List<SHIP_TO_CUSTTMsg> shipToCustTMsglist = new ArrayList<SHIP_TO_CUSTTMsg>();
        for (int i = 0; i < shipToCustMapList.size(); i++) {
            SHIP_TO_CUSTTMsg tMsg = new SHIP_TO_CUSTTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.shipToCustPk, (BigDecimal)shipToCustMapList.get(i).get("SHIP_TO_CUST_PK"));
            shipToCustTMsglist.add(tMsg);
        }

        int result;
        result = EZDFastTBLAccessor.removePhysical(shipToCustTMsglist.toArray(new SHIP_TO_CUSTTMsg[shipToCustTMsglist.size()]));
        if (result != shipToCustTMsglist.size()) {
            addError(NMZM0169E);
            noErrorFlag = false;
        }

        return noErrorFlag;
    }

    /**
     * @param pMsg
     * @param shipToCustMapList
     * @return boolean (true : No Error / false : Error)
     */
    private boolean deletePtyLocWrk(NMZC601001PMsg pMsg, List<Map> shipToCustMapList) {

        boolean noErrorFlag = true;

        if (shipToCustMapList == null || shipToCustMapList.size() == 0) {
            return noErrorFlag;
        }

        List<PTY_LOC_WRKTMsg> ptyLocWrkTMsglist = new ArrayList<PTY_LOC_WRKTMsg>();
        for (int i = 0; i < shipToCustMapList.size(); i++) {
            PTY_LOC_WRKTMsg tMsg = new PTY_LOC_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.ptyLocPk, (BigDecimal)shipToCustMapList.get(i).get("PTY_LOC_PK"));
            ptyLocWrkTMsglist.add(tMsg);
        }

        int result;
        result = EZDFastTBLAccessor.removePhysical(ptyLocWrkTMsglist.toArray(new PTY_LOC_WRKTMsg[ptyLocWrkTMsglist.size()]));
        if (result != ptyLocWrkTMsglist.size()) {
            addError(NMZM0171E);
            noErrorFlag = false;
        }

        return noErrorFlag;
    }

    /**
     * @param pMsg
     * @param acctLocMapList
     * @return boolean (true : No Error / false : Error)
     */
    private boolean deleteAcctLoc(NMZC601001PMsg pMsg, List<Map> acctLocMapList) {

        boolean noErrorFlag = true;

        if (acctLocMapList == null || acctLocMapList.size() == 0) {
            return noErrorFlag;
        }

        List<ACCT_LOCTMsg> acctLocTMsglist = new ArrayList<ACCT_LOCTMsg>();
        for (int i = 0; i < acctLocMapList.size(); i++) {
            ACCT_LOCTMsg tMsg = new ACCT_LOCTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.acctLocPk, (BigDecimal)acctLocMapList.get(i).get("ACCT_LOC_PK"));
            acctLocTMsglist.add(tMsg);
        }

        int result;
        result = EZDFastTBLAccessor.removePhysical(acctLocTMsglist.toArray(new ACCT_LOCTMsg[acctLocTMsglist.size()]));
        if (result != acctLocTMsglist.size()) {
            addError(NMZM0173E);
            noErrorFlag = false;
        }

        return noErrorFlag;
    }

    /**
     * @param pMsg
     * @param whMapList
     * @return boolean (true : No Error / false : Error)
     */
    private boolean deleteWhAndLocUsg(NMZC601001PMsg pMsg, List<Map> whMapList) {

        boolean noErrorFlag = true;

        if (whMapList == null || whMapList.size() == 0) {
            return noErrorFlag;
        }

        // Delete WH
        List<WHTMsg> whTMsglist = new ArrayList<WHTMsg>();
        for (int i = 0; i < whMapList.size(); i++) {
            WHTMsg tMsg = new WHTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.whPk, (BigDecimal)whMapList.get(i).get("WH_PK"));
            whTMsglist.add(tMsg);
        }
        int result = EZDFastTBLAccessor.removePhysical(whTMsglist.toArray(new WHTMsg[whTMsglist.size()]));
        if (result != whTMsglist.size()) {
            addError(NMZM0175E);
            noErrorFlag = false;
        }

        // Delete LOC_USG
        List<BigDecimal> ptyLocPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < whMapList.size(); i++) {
            ptyLocPkList.add((BigDecimal)whMapList.get(i).get("PTY_LOC_PK"));
        }
        List<Map> locUsgMapList = getLocUsgMapList(pMsg, ptyLocPkList);

        if (locUsgMapList == null || locUsgMapList.size() == 0) {
            return noErrorFlag;
        }

        List<LOC_USGTMsg> locUsgTMsglist = new ArrayList<LOC_USGTMsg>();
        for (int i = 0; i < locUsgMapList.size(); i++) {
            LOC_USGTMsg tMsg = new LOC_USGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.locUsgPk, (BigDecimal)locUsgMapList.get(i).get("LOC_USG_PK"));
            locUsgTMsglist.add(tMsg);
        }
        result = EZDFastTBLAccessor.removePhysical(locUsgTMsglist.toArray(new LOC_USGTMsg[locUsgTMsglist.size()]));
        if (result != locUsgMapList.size()) {
            addError(NMZM0174E);
            noErrorFlag = false;
        }

        return noErrorFlag;
    }

    /**
     * @param pMsg
     * @param techLocMapList
     * @return boolean (true : No Error / false : Error)
     */
    private boolean deleteTechLocAndLocUsg(NMZC601001PMsg pMsg, List<Map> techLocMapList) {

        boolean noErrorFlag = true;

        if (techLocMapList == null || techLocMapList.size() == 0) {
            return noErrorFlag;
        }

        // Delete TECH_LOC
        List<TECH_LOCTMsg> techLoclist = new ArrayList<TECH_LOCTMsg>();
        for (int i = 0; i < techLocMapList.size(); i++) {
            TECH_LOCTMsg tMsg = new TECH_LOCTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.techLocPk, (BigDecimal)techLocMapList.get(i).get("TECH_LOC_PK"));
            techLoclist.add(tMsg);
        }
        int result = EZDFastTBLAccessor.removePhysical(techLoclist.toArray(new TECH_LOCTMsg[techLoclist.size()]));
        if (result != techLoclist.size()) {
            addError(NMZM0176E);
            noErrorFlag = false;
        }

        List<BigDecimal> ptyLocPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < techLocMapList.size(); i++) {
            ptyLocPkList.add((BigDecimal)techLocMapList.get(i).get("PTY_LOC_PK"));
        }
        List<Map> locUsgMapList = getLocUsgMapList(pMsg, ptyLocPkList);

        if (locUsgMapList == null || locUsgMapList.size() == 0) {
            return noErrorFlag;
        }

        List<LOC_USGTMsg> locUsgTMsglist = new ArrayList<LOC_USGTMsg>();
        for (int i = 0; i < locUsgMapList.size(); i++) {
            LOC_USGTMsg tMsg = new LOC_USGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.locUsgPk, (BigDecimal)locUsgMapList.get(i).get("LOC_USG_PK"));
            locUsgTMsglist.add(tMsg);
        }
        result = EZDFastTBLAccessor.removePhysical(locUsgTMsglist.toArray(new LOC_USGTMsg[locUsgTMsglist.size()]));
        if (result != locUsgMapList.size()) {
            addError(NMZM0174E);
            noErrorFlag = false;
        }

        return noErrorFlag;
    }

    /**
     * @param pMsg
     * @param vndShipToXrefMapList
     * @return boolean (true : No Error / false : Error)
     */
    private boolean deleteVndShipToXref(NMZC601001PMsg pMsg, List<Map> vndShipToXrefMapList) {

        boolean noErrorFlag = true;

        if (vndShipToXrefMapList == null || vndShipToXrefMapList.size() == 0) {
            return noErrorFlag;
        }

        List<VND_SHIP_TO_XREFTMsg> vndShipToXreflist = new ArrayList<VND_SHIP_TO_XREFTMsg>();
        for (int i = 0; i < vndShipToXrefMapList.size(); i++) {
            VND_SHIP_TO_XREFTMsg tMsg = new VND_SHIP_TO_XREFTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.vndShipToXrefPk, (BigDecimal)vndShipToXrefMapList.get(i).get("VND_SHIP_TO_XREF_PK"));
            vndShipToXreflist.add(tMsg);
        }

        int result;
        result = EZDFastTBLAccessor.removePhysical(vndShipToXreflist.toArray(new VND_SHIP_TO_XREFTMsg[vndShipToXreflist.size()]));
        if (result != vndShipToXreflist.size()) {
            addError(NMZM0183E);
            noErrorFlag = false;
        }

        return noErrorFlag;
    }

    /**
     * @param pMsg
     * @param rtlSwhList
     * @return boolean (true : No Error / false : Error)
     */
    private boolean deleteRtlSwh(NMZC601001PMsg pMsg, List<Map> rtlSwhList) {

        boolean noErrorFlag = true;

        if (rtlSwhList == null || rtlSwhList.size() == 0) {
            return noErrorFlag;
        }

        List<RTL_SWHTMsg> rtlSwhlist = new ArrayList<RTL_SWHTMsg>();
        for (int i = 0; i < rtlSwhList.size(); i++) {
            RTL_SWHTMsg tMsg = new RTL_SWHTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, (String)rtlSwhList.get(i).get("RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, (String)rtlSwhList.get(i).get("RTL_SWH_CD"));
            rtlSwhlist.add(tMsg);
        }

        int result;
        result = EZDFastTBLAccessor.removePhysical(rtlSwhlist.toArray(new RTL_SWHTMsg[rtlSwhlist.size()]));
        if (result != rtlSwhlist.size()) {
            addError(NMZM0177E);
            noErrorFlag = false;
        }

        return noErrorFlag;
    }

    /**
     * <pre>
     * Set Suffix for Return-To Code
     * </pre>
     * @param pMsg NMZC601001PMsg 
     */
    public void setSuffixForRtrnTo(NMZC601001PMsg pMsg) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("varCharConstNm", NMZC601001_SUFFIX_FOR_RTRN_TO);
        this.suffixForRtrnToCd = (String) this.ssmBatchClient.queryObject("getSuffixForRtrnTo", param);
        if (!ZYPCommonFunc.hasValue(this.suffixForRtrnToCd)) {
            this.suffixForRtrnToCd = DEFAULT_SUFFIX_FOR_RTRN_TO;
        }
    }

    private String getLineBizTpCd(String glblCmpyCd) {
        String lineBizTpCd = ZYPCodeDataUtil.getVarCharConstValue(NMZC601001_LINE_BIZ_TP_CD, glblCmpyCd);
        if(!ZYPCommonFunc.hasValue(lineBizTpCd)) {
            lineBizTpCd = LINE_BIZ_TP.ALL;
        }
        return lineBizTpCd;
    }

    /**
     * @param pMsg NMZC601001PMsg
     * @return int
     */
    public int cntShipToCustForRtrnTo(NMZC601001PMsg pMsg) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("rtrnToCustCd", S21StringUtil.concatStrings(pMsg.rtlWhCd.getValue(), this.suffixForRtrnToCd));

        int cnt = (Integer) ssmBatchClient.queryObject("cntShipToCustForRtrnTo", param);
        return cnt;
    }


    /**
     * QC#2366 Add method.
     * 
     * @param pMsg
     * @param swhInfoPMsg
     * @param ptyLocPk
     * @param shipToCustPk
     * @return
     */
    private SHIP_TO_CUSTTMsg createSHIP_TO_CUST_For_TechHazmat(NMZC601001PMsg pMsg, NMZC601001_swhInfoListPMsg swhInfoPMsg, BigDecimal ptyLocPk, BigDecimal shipToCustPk, String endOfCode) {
        SHIP_TO_CUSTTMsg tMsg = new SHIP_TO_CUSTTMsg();

        EZDMsg.copy(pMsg, null, tMsg, null);
        EZDMsg.copy(swhInfoPMsg, null, tMsg, null);

        String shipToCustCd = pMsg.rtlWhCd.getValue() + endOfCode;

        setValue(tMsg.shipToCustPk, shipToCustPk);
        setValue(tMsg.locNum, shipToCustCd);

        setValue(tMsg.locNm, pMsg.rtlWhNm);
        tMsg.glnNum.clear();
        tMsg.dbaNm.clear();
        tMsg.dunsNum.clear();
        setValue(tMsg.rgtnStsCd, getRgtnSts(swhInfoPMsg));
        setValue(tMsg.cnsgnFlg, N);
        setValue(tMsg.ptyLocPk, ptyLocPk);
        setValue(tMsg.locRoleTpCd, LOC_ROLE_TP.SHIP_TO);
        setValue(tMsg.locGrpCd, this.locGrpCd);
        setValue(tMsg.effFromDt, pMsg.effFromDt);
        setValue(tMsg.effThruDt, pMsg.effThruDt);
        setValueIfNull(tMsg.effThruDt, MAX_DATE);
        setValue(tMsg.cmpyPk, this.cmpyPk);
        tMsg.prinCustPk.clear();
        setValue(tMsg.sellToCustCd, pMsg.dsAcctNum_S1);

        setValue(tMsg.shipToCustCd, shipToCustCd);
        tMsg.geoCd.clear();
        tMsg.dplStsCd.clear();
        tMsg.dplRsnTxt.clear();
        tMsg.dplRspDtTmTs.clear();
        tMsg.dplModByLoginUsrId.clear();
        tMsg.dplModDtTmTs.clear();
        tMsg.imptInvCnsgnCd.clear();
        setValue(tMsg.oemFlg, N);
        setValue(tMsg.embgoFlg, N);

        setValue(tMsg.dsInsdCtyLimitFlg, N);
        tMsg.dsCustTaxCd.clear();
        tMsg.relnBillToCustCd.clear();
        setValue(tMsg.primUsgFlg, N);
        tMsg.dsTaxPrntTpCd.clear();
        tMsg.dsCustTaxCalcCd.clear();
        tMsg.dsTaxGrpExemCd.clear();
        setValue(tMsg.dsTaxExemFlg, N);
        tMsg.dsExemExprDt.clear();
        tMsg.coaCcCd.clear();
        tMsg.lineBizTpCd.clear();
        tMsg.indyTpCd.clear();
        tMsg.coaAfflCd.clear();
        tMsg.coaChCd.clear();
        tMsg.coaCmpyCd.clear();
        tMsg.coaBrCd.clear();
        tMsg.coaAcctCd.clear();
        tMsg.coaProdCd.clear();
        tMsg.coaProjCd.clear();
        tMsg.coaExtnCd.clear();
        tMsg.bigDealNum.clear();
        // 2023/11/06 QC#61924 Add Start
        setValue(tMsg.deacNewTrxFlg, N);
        // 2023/11/06 QC#61924 Add End
        return tMsg;
    }

    /**
     * @param pMsg
     * @param swhInfoPMsg
     * @param ptyLocPk
     * @param endOfCode
     * @return
     */
    private PTY_LOC_WRKTMsg createPTY_LOC_WRK_Hazmat(NMZC601001PMsg pMsg, NMZC601001_swhInfoListPMsg swhInfoPMsg, BigDecimal ptyLocPk, String endOfCode) {
        PTY_LOC_WRKTMsg tMsg = new PTY_LOC_WRKTMsg();
        EZDMsg.copy(pMsg, null, tMsg, null);
        EZDMsg.copy(swhInfoPMsg, null, tMsg, null);

        setValue(tMsg.ptyLocPk, ptyLocPk);
        setValue(tMsg.cmpyPk, this.cmpyPk);

        setValue(tMsg.locNum, pMsg.rtlWhCd.getValue() + endOfCode);
        setValue(tMsg.locNm, pMsg.rtlWhNm);

        tMsg.glnNum.clear();
        tMsg.dbaNm.clear();
        tMsg.dunsNum.clear();
        setValue(tMsg.apvlStsCd, APVL_STS.APPROVED);
        setValue(tMsg.effFromDt, pMsg.effFromDt);
        setValue(tMsg.effThruDt, pMsg.effThruDt);
        setValueIfNull(tMsg.effThruDt, MAX_DATE);
        setValue(tMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
        tMsg.dplStsCd.clear();
        tMsg.dplRsnTxt.clear();
        tMsg.dplRspDtTmTs.clear();
        tMsg.dplModByLoginUsrId.clear();
        tMsg.dplModDtTmTs.clear();
        setValue(tMsg.embgoFlg, N);

        tMsg.tmZoneCd.clear();
        tMsg.custTierCd.clear();
        tMsg.indyTpCd.clear();
        tMsg.lgcyCustClsCd.clear();
        setValue(tMsg.dsInsdCtyLimitFlg, N);
        tMsg.prfTechCd.clear();
        tMsg.reqTechCd.clear();
        // QC#15204
        setValue(tMsg.lineBizTpCd, this.lineBizTpCd);
        tMsg.dsUltDunsNum.clear();
        tMsg.dsPrntDunsNum.clear();
        tMsg.dsLocEmpNum.clear();
        tMsg.dsLocRevAmt.clear();
        tMsg.dsLastUpdDunsDt.clear();
        tMsg.hqDunsNum.clear();
        tMsg.dsCustSicCd.clear();
        tMsg.dsCustSicDescTxt.clear();
        setValue(tMsg.dsLocNm, pMsg.dsLocNm_S1);

        return tMsg;
    }
    
    /**
     * @param pMsg
     * @param swhInfoPMsg
     * @param ptyLocPk
     * @param endOfCode
     * @return
     */
    private ACCT_LOCTMsg createACCT_LOC_Hazmat(NMZC601001PMsg pMsg, NMZC601001_swhInfoListPMsg swhInfoPMsg, BigDecimal ptyLocPk, String endOfCode) {
        ACCT_LOCTMsg tMsg = new ACCT_LOCTMsg();
        EZDMsg.copy(pMsg, null, tMsg, null);
        EZDMsg.copy(swhInfoPMsg, null, tMsg, null);

        setValue(tMsg.acctLocPk, nextval(ACCT_LOC_PK_SQ));
        setValue(tMsg.dsAcctNum, pMsg.dsAcctNum_S1);
        setValue(tMsg.locNum, pMsg.rtlWhCd.getValue() + endOfCode);
        setValue(tMsg.ptyLocPk, ptyLocPk);
        setValue(tMsg.effFromDt, pMsg.effFromDt);
        setValue(tMsg.effThruDt, pMsg.effThruDt);
        setValueIfNull(tMsg.effThruDt, MAX_DATE);
        setValue(tMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);

        return tMsg;
    }
}
