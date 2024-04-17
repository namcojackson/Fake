/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC103001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.common.EZDMsg;
import parts.common.EZDPMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AUTH_PSNTMsg;
import business.db.AUTH_PSNTMsgArray;
import business.db.DS_INVTY_LOC_VTMsg;
import business.db.DS_INVTY_LOC_VTMsgArray;
import business.db.MDSETMsg;
import business.db.PRCH_REQTMsg;
import business.db.PRCH_REQ_APVL_STSTMsg;
import business.db.PRCH_REQ_BIZ_PROC_LOGTMsg;
import business.db.PRCH_REQ_DTLTMsg;
import business.db.PRCH_REQ_DTLTMsgArray;
import business.db.PRCH_REQ_INTFCTMsg;
import business.db.PRCH_REQ_LINE_TPTMsg;
import business.db.PRCH_REQ_LOG_EVENTTMsg;
import business.db.PRCH_REQ_LOG_MODETMsg;
import business.db.PRCH_REQ_REC_TPTMsg;
import business.db.PRCH_REQ_SERTMsg;
import business.db.PRCH_REQ_TPTMsg;
import business.db.RCV_ASN_VNDTMsg;
import business.db.RTL_WHTMsg;
import business.db.S21_PSNTMsg;
import business.db.S21_PSN_VTMsg;
import business.db.S21_PSN_VTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.TECH_APVL_MINTMsg;
import business.parts.NLZC301001PMsg;
import business.parts.NPZC103001PMsg;
import business.parts.NPZC103001_prchReqInfoPMsg;
import business.parts.NPZC108001PMsg;
import business.parts.NPZC113001PMsg;
import business.parts.NPZC128001PMsg;
import business.parts.NPZC130001PMsg;
import business.parts.NSZC043001PMsg;
import business.parts.NWZC206001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC301001.NLZC301001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC108001.NPZC108001;
import com.canon.cusa.s21.api.NPZ.NPZC113001.NPZC113001;
import com.canon.cusa.s21.api.NPZ.NPZC128001.NPZC128001;
import com.canon.cusa.s21.api.NPZ.NPZC130001.NPZC130001;
import com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC206001.NWZC206001;
import com.canon.cusa.s21.api.NWZ.NWZC206001.constant.NWZC206001Constant;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CurrencyConversion;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CurrencyConversionBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetRspTime;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CARR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_QLFY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRT_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ASG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeData;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeException;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * PR Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2014/06/03   Hitachi         T.Kanasaka      Update          CSA-279_288
 * 2015/03/18   Fujitsu         T.Nishikawa     Update          CSA
 * 2015/10/27   CITS            H.Sugawara      Update          CSA
 * 2016/03/29   CSAI            K.Lee           Update          QC#6135
 * 2016/03/29   CSAI            K.Lee           Update          QC#5961
 * 2016/03/29   CSAI            K.Lee           Update          QC#6259
 * 2016/04/11   CITS            K.Ogino         Update          QC#6815
 * 2016/04/15   CITS            K.Ogino         Update          QC#6953
 * 2016/03/29   CSAI            K.Lee           Update          QC#7474
 * 07/14/2016   CSAI            Y.Imazu         Update          QC#6663
 * 2016/11/07   CITS            Y.IWASAKI       Update          QC#15052
 * 2016/11/21   CITS            K.Ogino         Update          QC#16064
 * 2016/11/21   CITS            K.Ogino         Update          QC#16036
 * 2016/11/22   CITS            K.Ogino         Update          QC#8295
 * 2016/11/24   CITS            Y.IWASAKI       Update          QC#16052
 * 2016/12/06   CITS            K.Ogino         Update          QC#16231
 * 2016/12/19   CITS            K.Ogino         Update          QC#15825
 * 2016/12/19   CITS            K.Ogino         Update          QC#6562
 * 2017/01/16   CITS            T.Tokutomi      Update          QC#17049
 * 2017/02/15   CITS            T.Wada          Update          QC#17347
 * 2017/02/21   CITS            Y.IWASAKI       Update          QC#17487
 * 2017/02/24   CITS            R.Shimamoto     Update          QC#17049
 * 2017/02/27   CITS            M.Naito         Update          QC#14640
 * 2017/03/03   CITS            R.Shimamoto     Update          QC#17395
 * 2017/05/25   CITS            R.Shimamoto     Update          QC#18698
 * 2017/08/08   CITS            H.Naoi          Update          Sol#097(QC#18398)
 * 2017/08/31   Hitachi         T.Tomita        Update          QC#18573
 * 2017/09/01   CITS            R.Shimamoto     Update          QC#20439
 * 2017/08/31   CITS            S.Endo          Update          Sol#406(QC#19243)
 * 2017/09/04   CITS            K.Kameoka       Update          Sol#369(QC#19243)
 * 2017/09/15   CITS            T.Tokutomi      Update          QC#20379
 * 2017/10/06   CITS            T.Tokutomi      Update          QC#21209
 * 2017/10/11   CITS            K.Ogino         Update          QC#21771
 * 2017/10/16   CITS            T.Tokutomi      Update          QC#21657
 * 2017/10/24   CITS            T.Tokutomi      Update          QC#21657-1
 * 2017/10/25   CITS            K.Ogino         Update          QC#22058
 * 2017/10/24   CITS            T.Tokutomi      Update          QC#21911
 * 2017/11/13   CITS            M.Naito         Update          QC#22501
 * 2017/11/21   CITS            K.Ogino         Update          QC#22212
 * 2017/12/21   CITS            K.Ogino         Update          QC#22400
 * 2017/12/25   CITS            T.Wada          Update          QC#22747
 * 2018/01/11   CITS            K.Ogino         Update          QC#21922
 * 2018/02/07   CITS            K.Ogino         Update          QC#18922
 * 2018/02/11   CITS            T.Tokutomi      Update          QC#21316
 * 03/14/2018   CITS            T.Wada          Update          Sol#118(QC#12110)
 * 03/26/2018   CITS            S.Katsuma       Update          QC#22519
 * 04/03/2018   CITS            K.Ogino         Update          QC#18738
 * 05/22/2018   CITS            K.Ogino         Update          QC#25886
 * 05/22/2018   CITS            T.Tokutomi      Update          QC#2366
 * 05/28/2018   CITS            S.Katsuma       Update          QC#25893
 * 06/12/2018   CITS            K.Ogino         Update          QC#26616
 * 07/26/2018   CITS            T.Tokutomi      Update          QC#27433
 * 07/31/2018   CITS            K.Ogino         Update          QC#27521
 * 07/31/2018   CITS            Y.Iwasaki       Update          QC#27459
 * 08/20/2018   CITS            T.Tokutomi      Update          QC#26662
 * 08/24/2018   CITS            T.Tokutomi      Update          QC#27575
 * 10/02/2018   CITS            M.Naito         Update          QC#28479
 * 10/09/2018   CITS            M.Naito         Update          QC#28479
 * 10/09/2018   CITS            K.Ogino         Update          QC#28675
 * 10/15/2018   CITS            T.Wada          Update          QC#28778
 * 10/26/2018   CITS            K.Ogino         Update          QC#28958
 * 11/12/2018   CITS            T.Hakodate      Update          QC#28939
 * 11/28/2018   Fujitsu         S.Takami        Update          QC#28735
 * 12/04/2018   CITS            M.Naito         Update          QC#29320
 * 12/07/2018   CITS            T.Tokutomi      Update          QC#25900
 * 12/10/2018   CITS            M.Naito         Update          QC#29320-1
 * 12/11/2018   CITS            K.Ogino         Update          QC#29263
 * 12/17/2018   CITS            M.Naito         Update          QC#29599
 * 2019/01/10   Fujitsu         S.Takami        Update          QC#29877
 * 2019/01/10   Fujitsu         T.Ogura         Update          QC#29774
 * 2019/02/08   CITS            K.Ogino         Update          QC#30102,QC#30135
 * 2019/02/21   CITS            K.Ogino         Update          QC#30444
 * 02/22/2019   CITS            K.Ogino         Update          QC#30460
 * 02/26/2019   CITS            K.Ogino         Update          QC#30516
 * 03/01/2019   CITS            K.Ogino         Update          QC#30589
 * 03/25/2019   CITS            K.Ogino         Update          QC#30768
 * 04/01/2019   CITS            K.Ogino         Update          QC#30817
 * 05/20/2019   CITS            K.Ogino         Update          QC#50072
 * 05/29/2019   CITS            T.Tokutomi      Update          QC#50581
 * 05/30/2019   CITS            M.Naito         Update          QC#50597
 * 06/17/2019   CITS            K.Ogino         Update          QC#50850
 * 06/24/2019   CITS            K.Ogino         Update          QC#50904
 * 07/10/2019   CITS            K.Ogino         Update          QC#51148
 * 07/11/2019   CITS            M.Naito         Update          QC#51148
 * 07/24/2019   CITS            M.Naito         Update          QC#51917
 * 07/26/2019   CITS            K.Ogino         Update          QC#51553
 * 2019/07/31   Hitachi         K.Kitachi       Update          QC#52257
 * 08/02/2019   CITS            M.Naito         Update          QC#52287
 * 08/16/2019   CITS            R.Shimamoto     Update          QC#52756
 * 08/19/2019   CITS            K.Ogino         Update          QC#53081
 * 08/22/2019   CITS            M.Naito         Update          QC#52582
 * 08/23/2019   CITS            M.Naito         Update          QC#52585
 * 09/02/2019   CITS            T.Wada          Update          QC#52509
 * 09/02/2019   CITS            M.Naito         Update          QC#52585
 * 09/12/2019   CITS            T.Wada          Update          QC#53301
 * 09/17/2019   CITS            T.Wada          Update          QC#53303
 * 09/23/2019   CITS            R.Shimamoto     Update          QC#53271
 * 12/02/2019   CITS            K.Ogino         Update          QC#54672,QC#54552
 * 12/20/2019   CITS            M.Naito         Update          QC#54908
 * 12/23/2019   CITS            M.Naito         Update          QC#54729
 * 01/25/2019   CITS            K.Ogino         Update          QC#55615
 * 01/29/2020   CITS            K.Ogino         Update          QC#55615
 * 02/04/2020   CITS            K.Ogino         Update          QC#55602-1
 * 02/10/2020   CITS            K.Ogino         Update          QC#55514
 * 02/13/2020   CITS            Y.Iwasaki       Update          QC#55778
 * 02/14/2020   CITS            K.Ogino         Update          QC#55710
 * 03/05/2020   Fujitsu         R.Nakamura      Update          QC#56103
 * 04/08/2020   CITS            M.Furugoori     Update          QC#56406
 * 04/28/2020   CITS            K.Ogino         Update          QC#56317
 * 05/08/2020   CITS            K.Fukumura      Update          QC#56543
 * 05/23/2020   CITS            K.Ogino         Update          QC#56867
 * 07/30/2020   CITS            K.Ogino         Update          QC#57365
 * 09/09/2020   CITS            K.Ogino         Update          QC#57659
 * 12/01/2020   CITS            T.Wada          Update          QC#58023
 * 01/26/2021   CITS            L.Mandanas      Update          QC#56947
 * 02/09/2021   CITS            J.Evangelista   Update          QC#57869
 * 04/28/2021   CITS            M.Furugoori     Update          QC#58645
 * 05/21/2021   CITS            J.Evangelista   Update          QC#58782
 * 2022/04/11   Hitachi         K.Kitachi       Update          QC#59899
 * 2022/10/06   CITS            R.Azucena       Update          QC#60664
 * 2022/12/12   Hitachi         K.Kitachi       Update          QC#60911
 * 2023/01/24   Hitachi         T.Kuroda        Update          QC#60908
 * 2023/02/06   CITS            A.Cullano       Update          QC#60993
 * 2023/02/08   Hitachi         T.Kuroda        Update          QC#60966
 * 2023/03/31   Hitachi         E.Watabe        Update          QC#61210
 * 2023/04/26   CITS            A.Cullano       Update          QC#61410
 * 2023/05/09   Hitachi         E.Watabe        Update          QC#61084
 * 2023/05/18   Hitachi         T.Kuroda        Update          QC#61211
 * 2023/06/13   Hitachi         T.Kuroda        Update          QC#61363
 * 2023/08/29   Hitachi         T.Kuroda        Update          QC#61740
 * </pre>
 */
public class NPZC103001 extends S21ApiCommonBase {

    /**
     * Global Company Code
     */
    private String glblCmpyCd = null;

    /**
     * Mode
     */
    private String mode = null;

    /**
     * Event
     */
    private String event = null;

    /**
     * S21ApiMessageMap
     */
    private S21ApiMessageMap msgMap = null;

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Current Purchase Requisition Number
     */
    private String currPrchReqNum = null;

    /**
     * This value will be taken from DB, only once per activation.
     */
    private BigDecimal currentMaxPrchReqLineNum = null;

    /**
     * This value will be taken from DB, only once per activation.
     */
    private Map<String, BigDecimal> currentMaxPrchReqLineSubNum = new HashMap<String, BigDecimal>();

    /**
     * Parts Request Line Number (This value will changes during loop)
     */
    private String prchReqLineNum = null;

    /**
     * Tech Notification Required
     */
    private boolean isNotificationRequired = false;

    /**
     * Online Batch Type Code
     */
    private ONBATCH_TYPE onBatTpCd = null;

    /**
     * Additional Header Map Key: column Value: value
     */
    private Map<String, Object> addlHdrMap = null;

    /**
     * Additional Detail List Key: column Value: value
     */
    private List<Map<String, Object>> addlDtlLst = null;

    /**
     * Config line exists flag
     */
    private boolean isConfigLineExists = false;

    /**
     * isForceApprove
     */
    private boolean isNextDayForceFlg = false;

    // QC#17395 Start.
    private String[] whOwnrCdList = null;
    // QC#17395 End.

    // QC#22400 Start
    private String preRqstRcvDt = null;

    private String preRqstRcvTm = null;
    // QC#22400 End

    // QC#27521
    private String techPoQlfyCd = null;
    
    // START 2023/03/31 E.Watabe [QC#61210, ADD]
    private String cusaPrntVndCd = null;
    // END 2023/03/31 E.Watabe [QC#61210, ADD]

    // sol#118(QC#12110)
    /**
     * isToolItem
     */
    private boolean isToolItem = false;

    // QC#28778 Add
    private boolean isSendMailRushCho = false;

    // QC#25900 ADD
    private final static int REGULAR_ITEM = 0;
    
    // QC#25900 ADD
    private final static int TOOL_ITEM = 1;
    
    // QC#25900 ADD
    private final static int HAZMAT_ITEM = 2;
    
    // QC#25900 ADD
    private final static int HAZMAT_TOOL_ITEM = 3;

    // QC#51553
    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    // START 2023/05/18 T.Kuroda [QC#61211, ADD]
    /**
     * isApvlMinAmtFlg
     */
    private boolean isApvlMinAmtFlg = false;
    // END   2023/05/18 T.Kuroda [QC#61211, ADD]

    /**
     * Constructor
     */
    public NPZC103001() {
        super();
    }

    /**
     * PR Update API
     * @param param NPZC103001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NPZC103001PMsg param, final ONBATCH_TYPE onBatchType) {

        this.msgMap = new S21ApiMessageMap(param);
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.onBatTpCd = onBatchType;
        this.addlHdrMap = new HashMap<String, Object>();
        this.addlDtlLst = new ArrayList<Map<String, Object>>();

        for (int idx = 0; idx < param.prchReqInfo.getValidCount(); idx++) {
            addlDtlLst.add(new HashMap<String, Object>());
        }

        NPZC103001PMsg pMsg = (NPZC103001PMsg) this.msgMap.getPmsg();

        // QC#25900 init_check
        this.mode = pMsg.xxModeCd.getValue();
        this.event = pMsg.eventId.getValue();
        this.glblCmpyCd = pMsg.glblCmpyCd.getValue();

        checkParam(pMsg);

        if (this.msgMap.getMsgMgr().isXxMsgId()) {
            // START 2019/01/10 T.Ogura [QC#29774,ADD]
            this.msgMap.flush();
            // END   2019/01/10 T.Ogura [QC#29774,ADD]
            return;
        }

        // QC#25900 Split Prch Req(Hazmat&Tool, Hazmat, Tool, Regular)
        ArrayList<NPZC103001PMsg> pMsgList = splitPrchReq(pMsg);

        for (NPZC103001PMsg p : pMsgList) {
            doProcess(p);
        }

        // Set split message.
        if (pMsgList.size() > 1) {
            updatePrchReqSplitCmnt(pMsgList);
        }

        // Update return parameter.
        // List sequence : Regular, Tool, Hazmat, Hazmat&Tool.
        // QC#50581
        if (!pMsgList.isEmpty()) {
            EZDPMsg.copy(pMsgList.get(0), null, pMsg, null);
        }

        this.msgMap.flush();
    }

    /**
     * splitPrchReq
     * QC#25900 Add method.
     * @param msg NPZC103001PMsg
     * @return ArrayList<NPZC103001PMsg>
     */
    private ArrayList<NPZC103001PMsg> splitPrchReq(NPZC103001PMsg pMsg) {

        ArrayList<NPZC103001PMsg> pMsgList = new ArrayList<NPZC103001PMsg>();

        if (isSplitPR(pMsg)) {

            NPZC103001PMsg regularMsg = new NPZC103001PMsg();
            NPZC103001PMsg toolMsg = new NPZC103001PMsg();
            NPZC103001PMsg hazmatMsg = new NPZC103001PMsg();
            NPZC103001PMsg hazmatToolMsg = new NPZC103001PMsg();

            // Copy
            EZDPMsg.copy(pMsg, null, regularMsg, null);
            EZDPMsg.copy(pMsg, null, toolMsg, null);
            EZDPMsg.copy(pMsg, null, hazmatMsg, null);
            EZDPMsg.copy(pMsg, null, hazmatToolMsg, null);

            // Clear Detail & Serial
            ZYPTableUtil.clear(regularMsg.prchReqInfo);
            ZYPTableUtil.clear(toolMsg.prchReqInfo);
            ZYPTableUtil.clear(hazmatMsg.prchReqInfo);
            ZYPTableUtil.clear(hazmatToolMsg.prchReqInfo);

            ZYPTableUtil.clear(regularMsg.serNumInfo);
            ZYPTableUtil.clear(toolMsg.serNumInfo);
            ZYPTableUtil.clear(hazmatMsg.serNumInfo);
            ZYPTableUtil.clear(hazmatToolMsg.serNumInfo);

            boolean isCustomerShip = isCustomerShip(pMsg.shipToCustCd.getValue());
            // ShpgSvcLvlCd is unique.
            boolean isWillCall = isWillCallRequest(pMsg.prchReqInfo.no(0).shpgSvcLvlCd.getValue());
            boolean isPrfCarrFdx = isPreferredCarrierFedex(pMsg.rqstTechTocCd.getValue());
            // START 2023/08/29 T.Kuroda [QC#61740, MOD]
            // START 2019/08/23 M.Naito [QC#52585,MOD]
//            boolean isGroundHazmatOnly = isGroundHazmat(pMsg);
//            boolean isGroundHazmatOnly = isShpgSvlHazmatGroundOnly(pMsg);
            boolean isHazmatHasGround = isShpgSvlHazmatHasGround(pMsg);
            // END 2019/08/23 M.Naito [QC#52585,MOD]
            // END 2023/08/29 T.Kuroda [QC#61740, MOD]

            boolean isIncludeHazmatForMerged = false;
            boolean isIncludeHazmatForMergedTool = false;
            boolean isCustomerWhShip = isCustomerWhShip(pMsg.prchReqInfo.no(0).destInvtyLocCd.getValue());

            // Split
            for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
                switch (getItemType(pMsg.prchReqInfo.no(i).mdseCd.getValue())) {
                    case HAZMAT_TOOL_ITEM:
                        // START 2023/08/29 T.Kuroda [QC#61740, MOD]
//                        if (isCustomerShip || isWillCall || (isGroundHazmatOnly && !isPrfCarrFdx) || isCustomerWhShip) {
                        if (isCustomerShip || isWillCall || (isHazmatHasGround && !isPrfCarrFdx) || isCustomerWhShip) {
                        // END 2023/08/29 T.Kuroda [QC#61740, MOD]
                            copyPMsgForSplitPR(pMsg, i, toolMsg, toolMsg.prchReqInfo.getValidCount(), toolMsg.serNumInfo.getValidCount());
                            isIncludeHazmatForMergedTool = true;
                        } else {
                            copyPMsgForSplitPR(pMsg, i, hazmatToolMsg, hazmatToolMsg.prchReqInfo.getValidCount(), hazmatToolMsg.serNumInfo.getValidCount());
                        }
                        break;
                    case HAZMAT_ITEM:
                        // START 2023/08/29 T.Kuroda [QC#61740, MOD]
//                        if (isCustomerShip || isWillCall || (isGroundHazmatOnly && !isPrfCarrFdx) || isCustomerWhShip) {
                        if (isCustomerShip || isWillCall || (isHazmatHasGround && !isPrfCarrFdx) || isCustomerWhShip) {
                        // END 2023/08/29 T.Kuroda [QC#61740, MOD]
                            copyPMsgForSplitPR(pMsg, i, regularMsg, regularMsg.prchReqInfo.getValidCount(), regularMsg.serNumInfo.getValidCount());
                            isIncludeHazmatForMerged = true;
                        } else {
                            copyPMsgForSplitPR(pMsg, i, hazmatMsg, hazmatMsg.prchReqInfo.getValidCount(), hazmatMsg.serNumInfo.getValidCount());
                        }
                        break;
                    case TOOL_ITEM:
                        copyPMsgForSplitPR(pMsg, i, toolMsg, toolMsg.prchReqInfo.getValidCount(), toolMsg.serNumInfo.getValidCount());
                        break;
                    case REGULAR_ITEM:
                        copyPMsgForSplitPR(pMsg, i, regularMsg, regularMsg.prchReqInfo.getValidCount(), regularMsg.serNumInfo.getValidCount());
                        break;
                    default:
                        break;
                }
            }

            // Modification common item
            String carrCdForWillCall = getCarrCdForWillCall();

            // Modify Hazmat Tool request.
            hazmatToolMsg = modificationHazmatRequest(hazmatToolMsg, isPrfCarrFdx, carrCdForWillCall);
            // START 2023/08/29 T.Kuroda [QC#61740, ADD]
            hazmatToolMsg = setPrchReqTpForTool(hazmatToolMsg);
            // END 2023/08/29 T.Kuroda [QC#61740, ADD]
            if (S21ApiUtil.isXxMsgId(hazmatToolMsg)) {
                // Error.
                EZDMsg.copy(hazmatToolMsg.xxMsgIdList, null, pMsg.xxMsgIdList, null);
                return pMsgList; // Empty List.
            }

            // Modify Hazmat request.
            hazmatMsg = modificationHazmatRequest(hazmatMsg, isPrfCarrFdx, carrCdForWillCall);
            if (S21ApiUtil.isXxMsgId(hazmatMsg)) {
                // Error.
                EZDMsg.copy(hazmatMsg.xxMsgIdList, null, pMsg.xxMsgIdList, null);
                return pMsgList; // Empty List.
            }

            // Modify Tool Request
            // START 2023/08/29 T.Kuroda [QC#61740, MOD]
//            toolMsg = modificationRegularRequest(toolMsg, isIncludeHazmatForMergedTool, isWillCall, isGroundHazmatOnly, isCustomerWhShip, isPrfCarrFdx, carrCdForWillCall);
            toolMsg = modificationRegularRequest(toolMsg, isIncludeHazmatForMergedTool, isWillCall, isHazmatHasGround, isCustomerWhShip, isPrfCarrFdx, carrCdForWillCall);
            // END 2023/08/29 T.Kuroda [QC#61740, MOD]
            // START 2019/12/23 M.Naito [QC#54729,ADD]
            toolMsg = setPrchReqTpForTool(toolMsg);
            // END 2019/12/23 M.Naito [QC#54729,ADD]
            if (S21ApiUtil.isXxMsgId(toolMsg)) {
                // Error.
                EZDMsg.copy(toolMsg.xxMsgIdList, null, pMsg.xxMsgIdList, null);
                return pMsgList; // Empty List.
            }

            // Modify Regular Request
            // START 2023/08/29 T.Kuroda [QC#61740, MOD]
//            regularMsg = modificationRegularRequest(regularMsg, isIncludeHazmatForMerged, isWillCall, isGroundHazmatOnly, isCustomerWhShip, isPrfCarrFdx, carrCdForWillCall);
            regularMsg = modificationRegularRequest(regularMsg, isIncludeHazmatForMerged, isWillCall, isHazmatHasGround, isCustomerWhShip, isPrfCarrFdx, carrCdForWillCall);
            // END 2023/08/29 T.Kuroda [QC#61740, MOD]
            if (S21ApiUtil.isXxMsgId(regularMsg)) {
                // Error.
                EZDMsg.copy(regularMsg.xxMsgIdList, null, pMsg.xxMsgIdList, null);
                return pMsgList; // Empty List.
            }

            // Set Request List
            if (regularMsg.prchReqInfo.getValidCount() > 0) {
                pMsgList.add(regularMsg);
            }
            if (toolMsg.prchReqInfo.getValidCount() > 0) {
                pMsgList.add(toolMsg);
            }
            if (hazmatMsg.prchReqInfo.getValidCount() > 0) {
                pMsgList.add(hazmatMsg);
            }
            if (hazmatToolMsg.prchReqInfo.getValidCount() > 0) {
                pMsgList.add(hazmatToolMsg);
            }

        } else {
            pMsgList.add(pMsg);
        }

        return pMsgList;
    }

    /**
     * getItemType
     * QC#25900 Add method.
     * @param mdseCd
     * @return item type (int)
     */
    private int getItemType(String mdseCd) {

        Map mdseSftyInfo = getHazmatItemInfo(mdseCd);

        if (mdseSftyInfo != null) {
            if (isToolItem(mdseCd)) {
                return HAZMAT_TOOL_ITEM;
            } else {
                return HAZMAT_ITEM;
            }
        } else {
            if (isToolItem(mdseCd)) {
                return TOOL_ITEM;
            } else {
                return REGULAR_ITEM;
            }
        }
    }
    
    /**
     * isToolItem
     * QC#25900 Add method.
     * @param mdseCd
     * @return true (Yes) / false(No)
     */
    private boolean isToolItem(String mdseCd) {

        MDSETMsg tMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
        tMsg = (MDSETMsg) EZDTBLAccessor.findByKey(tMsg);
        if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.prtItemTpCd)) {
            if (PRT_ITEM_TP.TOOL.equals(tMsg.prtItemTpCd.getValue())) {
                return true;
            }
        }

        return false;
    }
 
    /**
     * isShpgSvlHazmatRequest
     * QC#25900 Add method.
     * @param pMsg NPZC103001PMsg
     * @return true(ground only) / false(no)
     */
    private boolean isShpgSvlHazmatGroundOnly(NPZC103001PMsg pMsg) {
        boolean isShpgSvcLvlGround = true;

        for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
            Map mdseSftyInfo = getHazmatItemInfo(pMsg.prchReqInfo.no(i).mdseCd.getValue());

            // START 2019/08/23 M.Naito [QC#52585,MOD]
            if (mdseSftyInfo != null && !SHPG_SVC_LVL.GROUND_SERVICE.equals((String) mdseSftyInfo.get("SHPG_SVC_LVL_CD"))) {
                isShpgSvcLvlGround = false;
                break;
            }
            // END 2019/08/23 M.Naito [QC#52585,MOD]
        }

        return isShpgSvcLvlGround;
    }

    /**
     * isShipDBSWhOnly
     * QC#25900 Add method.
     * @param pMsg NPZC103001PMsg
     * @return true(DBS only) / false(no)
     */
    private boolean isShipDBSWhOnly(NPZC103001PMsg pMsg){
        boolean isDBSWhOnly = true;
        
        for(int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++){
            // DBS-WH check
            if (!isDBSwh(pMsg.prchReqInfo.no(i).srcInvtyLocCd.getValue())) {
                isDBSWhOnly = false;
            }
        }

        return isDBSWhOnly;
    }

// START 2023/08/29 T.Kuroda [QC#61740, ADD]
    /**
     * isShpgSvlHazmatHasGround
     * @param pMsg NPZC103001PMsg
     * @return true(has ground) / false(no)
     */
    private boolean isShpgSvlHazmatHasGround(NPZC103001PMsg pMsg) {
        boolean isHasGround = false;

        for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
            Map mdseSftyInfo = getHazmatItemInfo(pMsg.prchReqInfo.no(i).mdseCd.getValue());

            if (mdseSftyInfo != null && SHPG_SVC_LVL.GROUND_SERVICE.equals((String) mdseSftyInfo.get("SHPG_SVC_LVL_CD"))) {
                isHasGround = true;
                break;
            }
        }

        return isHasGround;
    }
// END 2023/08/29 T.Kuroda [QC#61740, ADD]

    /**
     * modificationHazmatRequest
     * QC#25900 Add method.
     * @param hazmatMsg
     * @param isPrfCarrFdx
     * @param carrCdForWillCall
     * @return modified hazmat request
     */
    private NPZC103001PMsg modificationHazmatRequest(NPZC103001PMsg hazmatMsg, boolean isPrfCarrFdx, String carrCdForWillCall) {

        if (hazmatMsg.prchReqInfo.getValidCount() > 0) {
            // START 2023/08/29 T.Kuroda [QC#61740, DEL]
//            boolean isShpgSvcLvlGround = isShpgSvlHazmatGroundOnly(hazmatMsg);
            // END 2023/08/29 T.Kuroda [QC#61740, DEL]
            // START 2023/05/09 E.Watabe [QC#61084,DEL]
//            boolean isDBSWhOnly = isShipDBSWhOnly(hazmatMsg);
            // END 2023/05/09 E.Watabe [QC#61084,DEL]
            // START 2023/08/29 T.Kuroda [QC#61740, MOD]
            boolean isHazmatHasGround = isShpgSvlHazmatHasGround(hazmatMsg);
            // END 2023/08/29 T.Kuroda [QC#61740, MOD]

            // Update Hazmat Tool PR
            // START 2023/08/29 T.Kuroda [QC#61740, MOD]
//            if (PRCH_REQ_TP.RUSH.equals(hazmatMsg.prchReqTpCd.getValue()) //
//                    || PRCH_REQ_TP.PREMIUM_RUSH.equals(hazmatMsg.prchReqTpCd.getValue())) {
            if ((PRCH_REQ_TP.RUSH.equals(hazmatMsg.prchReqTpCd.getValue()) //
                    || PRCH_REQ_TP.PREMIUM_RUSH.equals(hazmatMsg.prchReqTpCd.getValue())) //
                    && isHazmatHasGround) {
            // END 2023/08/29 T.Kuroda [QC#61740, MOD]
                ZYPEZDItemValueSetter.setValue(hazmatMsg.prchReqTpCd, PRCH_REQ_TP.STANDARD);
            }

            // START 2023/08/29 T.Kuroda [QC#61740, MOD]
//            if (isPrfCarrFdx && hazmatMsg.prchReqInfo.getValidCount() > 0) {
            if (isPrfCarrFdx && hazmatMsg.prchReqInfo.getValidCount() > 0
                    && isHazmatHasGround) {
            // END 2023/08/29 T.Kuroda [QC#61740, MOD]
                hazmatMsg = setShipToCustHazmat(hazmatMsg);

            }

            for (int i = 0; i < hazmatMsg.prchReqInfo.getValidCount(); i++) {
            // START 2023/05/09 E.Watabe [QC#61084,MOD]
//                if (isShpgSvcLvlGround || isDBSWhOnly) {
//                    ZYPEZDItemValueSetter.setValue(hazmatMsg.prchReqInfo.no(i).shpgSvcLvlCd, SHPG_SVC_LVL.GROUND_SERVICE);
//                    if (ZYPCommonFunc.hasValue(hazmatMsg.prchReqInfo.no(i).carrCd)) {
//                        if (!checkShpgSvcCarrReln(hazmatMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue(), hazmatMsg.prchReqInfo.no(i).carrCd.getValue())) {
//                            hazmatMsg.prchReqInfo.no(i).carrCd.clear();
//                        }
//                    }
//                } else {
//                    ZYPEZDItemValueSetter.setValue(hazmatMsg.prchReqInfo.no(i).shpgSvcLvlCd, SHPG_SVC_LVL.CUSTOMER_PICK_UP);
//                    ZYPEZDItemValueSetter.setValue(hazmatMsg.prchReqInfo.no(i).carrCd, carrCdForWillCall);
//                }
                // START 2023/08/29 T.Kuroda [QC#61740, MOD]
//                if (!isShpgSvcLvlGround) {
                if (!isHazmatHasGround) {
                // END 2023/08/29 T.Kuroda [QC#61740, MOD]
                    if (ZYPCommonFunc.hasValue(hazmatMsg.prchReqInfo.no(i).carrCd)) {
                        if (!checkShpgSvcCarrReln(hazmatMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue(), hazmatMsg.prchReqInfo.no(i).carrCd.getValue())) {
                            hazmatMsg.prchReqInfo.no(i).carrCd.clear();
                        }
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(hazmatMsg.prchReqInfo.no(i).shpgSvcLvlCd, SHPG_SVC_LVL.GROUND_SERVICE);

                    if (ZYPCommonFunc.hasValue(hazmatMsg.prchReqInfo.no(i).carrCd)) {
                        if (!checkShpgSvcCarrReln(hazmatMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue(), hazmatMsg.prchReqInfo.no(i).carrCd.getValue())) {
                            hazmatMsg.prchReqInfo.no(i).carrCd.clear();
                        }
                    }
                }
            // END 2023/05/09 E.Watabe [QC#61084,MOD]
            }
        }

        return hazmatMsg;
    }
    
    /**
     * modificationRegularRequest
     * @param regularMsg
     * @param isIncludeHazmatForMerged
     * @param isWillCall
     * @param isHazmatHasGround
     * @param isCustomerWhShip
     * @param isPrfCarrFdx
     * @param carrCdForWillCall
     * @return modified regular request
     */
    // START 2023/08/29 T.Kuroda [QC#61740, MOD]
//    private NPZC103001PMsg modificationRegularRequest(NPZC103001PMsg regularMsg, final boolean isIncludeHazmatForMerged, final boolean isWillCall, final boolean isGroundHazmatOnly, final boolean isCustomerWhShip, final boolean isPrfCarrFdx, final String carrCdForWillCall) {
    private NPZC103001PMsg modificationRegularRequest(NPZC103001PMsg regularMsg, final boolean isIncludeHazmatForMerged, final boolean isWillCall, final boolean isHazmatHasGround, final boolean isCustomerWhShip, final boolean isPrfCarrFdx, final String carrCdForWillCall) {
    // END 2023/08/29 T.Kuroda [QC#61740, MOD]

        // START 2019/08/23 M.Naito [QC#52585,ADD]
        // set ShpgSvcLvl & Carrier for hazmat item
        if (isIncludeHazmatForMerged && !isWillCall) {
            // START 2023/08/29 T.Kuroda [QC#61740, MOD]
//            boolean isShpgSvcLvlGround = isShpgSvlHazmatGroundOnly(regularMsg);
            boolean isShpgSvlHazmatHasGround = isShpgSvlHazmatHasGround(regularMsg);
            // END 2023/08/29 T.Kuroda [QC#61740, MOD]
            // START 2023/05/09 E.Watabe [QC#61084,DEL]
//            boolean isDBSWhOnly = isShipDBSWhOnly(regularMsg);
//            boolean oneTimeWrnMsg = true;
            // END 2023/05/09 E.Watabe [QC#61084,DEL]

            for (int i = 0; i < regularMsg.prchReqInfo.getValidCount(); i++) {
            // START 2023/05/09 E.Watabe [QC#61084,MOD]
//                if (isShpgSvcLvlGround || isDBSWhOnly) {
//                    ZYPEZDItemValueSetter.setValue(regularMsg.prchReqInfo.no(i).shpgSvcLvlCd, SHPG_SVC_LVL.GROUND_SERVICE);
//                    if (ZYPCommonFunc.hasValue(regularMsg.prchReqInfo.no(i).carrCd)) {
//                        if (!checkShpgSvcCarrReln(regularMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue(), regularMsg.prchReqInfo.no(i).carrCd.getValue())) {
//                            regularMsg.prchReqInfo.no(i).carrCd.clear();
//                        }
//                    }
//                } else {
//                    ZYPEZDItemValueSetter.setValue(regularMsg.prchReqInfo.no(i).shpgSvcLvlCd, SHPG_SVC_LVL.CUSTOMER_PICK_UP);
//                    ZYPEZDItemValueSetter.setValue(regularMsg.prchReqInfo.no(i).carrCd, carrCdForWillCall);
//                }
                // START 2023/08/29 T.Kuroda [QC#61740, MOD]
//                if (!isShpgSvcLvlGround) {
                if (!isShpgSvlHazmatHasGround) {
                // END 2023/08/29 T.Kuroda [QC#61740, MOD]
                    if (ZYPCommonFunc.hasValue(regularMsg.prchReqInfo.no(i).carrCd)) {
                        if (!checkShpgSvcCarrReln(regularMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue(), regularMsg.prchReqInfo.no(i).carrCd.getValue())) {
                            regularMsg.prchReqInfo.no(i).carrCd.clear();
                        }
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(regularMsg.prchReqInfo.no(i).shpgSvcLvlCd, SHPG_SVC_LVL.GROUND_SERVICE);

                    if (ZYPCommonFunc.hasValue(regularMsg.prchReqInfo.no(i).carrCd)) {
                        if (!checkShpgSvcCarrReln(regularMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue(), regularMsg.prchReqInfo.no(i).carrCd.getValue())) {
                            regularMsg.prchReqInfo.no(i).carrCd.clear();
                        }
                    }
                }
            // END 2023/05/09 E.Watabe [QC#61084,MOD]
            }

            // START 2023/08/29 T.Kuroda [QC#61740, ADD]
            if (isShpgSvlHazmatHasGround) {
                if (PRCH_REQ_TP.RUSH.equals(regularMsg.prchReqTpCd.getValue()) //
                        || PRCH_REQ_TP.PREMIUM_RUSH.equals(regularMsg.prchReqTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(regularMsg.prchReqTpCd, PRCH_REQ_TP.STANDARD);
                }
            }
            // END 2023/08/29 T.Kuroda [QC#61740, ADD]

        }
        // END 2019/08/23 M.Naito [QC#52585,ADD]

        // Non hazmat ShpgSvcLvl & Carrier Check
        for (int i = 0; i < regularMsg.prchReqInfo.getValidCount(); i++) {
            // QC#50904
            if (ZYPCommonFunc.hasValue(regularMsg.prchReqInfo.no(i).carrCd) // 
                    && !ZYPCommonFunc.hasValue(regularMsg.mrpPlnNm)) {

                if (!checkShpgSvcCarrReln(regularMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue(), regularMsg.prchReqInfo.no(i).carrCd.getValue())) {
                    regularMsg.prchReqInfo.no(i).carrCd.clear();
                }
            }

            if (SHPG_SVC_LVL.CUSTOMER_PICK_UP.equals(regularMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(regularMsg.prchReqInfo.no(i).carrCd, carrCdForWillCall);
            }
        }

        // Non Hazmat will Call or GroundOnly. check Fedex Hazmat.
        // START 2023/08/29 T.Kuroda [QC#61740, MOD]
//        if (isIncludeHazmatForMerged //
//                && (isWillCall || isGroundHazmatOnly)) {
        if (isIncludeHazmatForMerged //
                && (isWillCall || isHazmatHasGround)) {
        // END 2023/08/29 T.Kuroda [QC#61740, MOD]
            if (isPrfCarrFdx //
                    && !isCustomerWhShip && regularMsg.prchReqInfo.getValidCount() > 0) {
                regularMsg = setShipToCustHazmat(regularMsg);
            }
        }

        return regularMsg;
    }

    // START 2019/12/23 M.Naito [QC#54729,ADD]
    /**
     * setPrchReqTpForTool
     * @param pMsg NPZC103001PMsg
     * @return NPZC103001PMsg
     */
    private NPZC103001PMsg setPrchReqTpForTool(NPZC103001PMsg pMsg) {
        if (pMsg.prchReqInfo.getValidCount() > 0) {
            // Update Tool PR
            if (PRCH_REQ_TP.RUSH.equals(pMsg.prchReqTpCd.getValue()) //
                    || PRCH_REQ_TP.PREMIUM_RUSH.equals(pMsg.prchReqTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqTpCd, PRCH_REQ_TP.STANDARD);
            }
        }
        return pMsg;
    }
    // END 2019/12/23 M.Naito [QC#54729,ADD]

    /**
     * updatePrchReqSplitCmnt
     * QC#25900 Add method.
     * @param msgList
     */
    private void updatePrchReqSplitCmnt(ArrayList<NPZC103001PMsg> msgList) {

        // Target PRCH_REQ_NUM List
        LinkedList<String> numList = new LinkedList<String>();

        for (NPZC103001PMsg msg : msgList) {
            numList.add(msg.prchReqNum.getValue());
        }

        for (NPZC103001PMsg msg : msgList) {
            PRCH_REQTMsg tMsg = new PRCH_REQTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqNum, msg.prchReqNum);
            tMsg = (PRCH_REQTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);

            if (tMsg != null) {
                // Create split comment.
                LinkedList<String> cloneNumList = (LinkedList<String>) numList.clone();

                cloneNumList.remove(tMsg.prchReqNum.getValue());

                StringBuffer splitCmnt = new StringBuffer();
                splitCmnt.append("Splitted TR ");

                for (int i = 0; i < cloneNumList.size(); i++) {
                    if (i > 0) {
                        splitCmnt.append(", ");
                    }
                    splitCmnt.append("#");
                    splitCmnt.append(cloneNumList.get(i));
                }

                ZYPEZDItemValueSetter.setValue(tMsg.prchReqCmntTxt, splitCmnt.toString());

                S21ApiTBLAccessor.update(tMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    // Update Error.
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0100E);
                }
            }
        }

        // QC#50850
        for (int i = 0; i < msgList.size(); i++) {

            NPZC103001PMsg para = msgList.get(i);

            String procStsCd = "";
            String errMsgCd = "";
            String errMsgTxt = "";
            String prchReqNum = "";

            if (S21ApiUtil.isXxMsgId(para)) {
                procStsCd = PROC_STS.ERROR;
                errMsgCd = para.xxMsgIdList.no(0).xxMsgId.getValue();
                errMsgTxt = S21MessageFunc.clspGetMessage(para.xxMsgIdList.no(0).xxMsgId.getValue());

            } else {
                procStsCd = PROC_STS.COMPLEATED;
                if (ZYPCommonFunc.hasValue(para.prchReqNum)) {
                    prchReqNum = para.prchReqNum.getValue();
                }
            }

            // select PrInterface for update
            for (int c = 0; c < para.prchReqInfo.getValidCount(); c++) {
                if (ZYPCommonFunc.hasValue(para.prchReqInfo.no(c).prchReqIntfcPk)) {
                    PRCH_REQ_INTFCTMsg prchReqIntfcTMsg = new PRCH_REQ_INTFCTMsg();
                    ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.prchReqIntfcPk, para.prchReqInfo.no(c).prchReqIntfcPk);

                    prchReqIntfcTMsg = (PRCH_REQ_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdate(prchReqIntfcTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prchReqIntfcTMsg.getReturnCode())) {
                        return;
                    }

                    // update PrInterface
                    ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.procStsCd, procStsCd);
                    ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.prchReqNum, prchReqNum);
                    ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.prchReqLineNum, para.prchReqInfo.no(c).prchReqLineNum);
                    ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.prchReqLineSubNum, para.prchReqInfo.no(c).prchReqLineSubNum);
                    ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.procErrMsgCd, errMsgCd);
                    String intfcErrMsgTxt = errMsgTxt;
                    if (ZYPCommonFunc.hasValue(intfcErrMsgTxt) && intfcErrMsgTxt.length() > 400) {
                        intfcErrMsgTxt = intfcErrMsgTxt.substring(0, 400);
                    }
                    ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.intfcErrMsgTxt, intfcErrMsgTxt);
                    EZDTBLAccessor.update(prchReqIntfcTMsg);
                }
            }
        }
    }

    /**
     * Main Process.
     * @param pMsg NPZC103001PMsg
     */
    private void doProcess(NPZC103001PMsg pMsg) {

        // QC#25900 move checkParam. doProcess => execute.

        // QC#25900 Init.
        this.isToolItem = false;

        // QC#17395 Start.
        String whOwnrCd = ZYPCodeDataUtil.getVarCharConstValue(NPZC103001Constant.HAZMAT_WH_OWNR_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(whOwnrCd)) {
            this.whOwnrCdList = null;
        } else {
            this.whOwnrCdList = whOwnrCd.split(",");
        }
        // QC#17395 End.
        // QC#27521
        this.techPoQlfyCd = ZYPCodeDataUtil.getVarCharConstValue("TECH_INSRC_PO_QLFY_CD", glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(techPoQlfyCd)) {
            this.techPoQlfyCd = "PC";
        }
        
        // START 2023/03/31 E.Watabe [QC#61210, ADD]
        this.cusaPrntVndCd = ZYPCodeDataUtil.getVarCharConstValue(NPZC103001Constant.CUSA_VAR_CHAR_CONST_NM, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cusaPrntVndCd)) {
            this.cusaPrntVndCd = "614271";
        }
        // END 2023/03/31 E.Watabe [QC#61210, ADD]

        if (!NPZC103001Constant.EVENT_UPDATE.equals(this.event)) {
            masterValidation(pMsg);
        }

        // QC#28778 Add Start
        String constRushChoSendMail = ZYPCodeDataUtil.getVarCharConstValue("RUSH_CHO_SEND_MAIL_FLG", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(constRushChoSendMail) && ZYPConstant.FLG_ON_Y.equals(constRushChoSendMail)) {
            this.isSendMailRushCho = true;
        } else {
            this.isSendMailRushCho = false;
        }
        // QC#28778 Add End

        if (this.msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // QC#25900 Restore hazmat process.
        if (isCreateMode()) {
            insertPurchaseRequisition(pMsg);

        } else if (isUpdateMode()) {
            updatePurchaseRequisition(pMsg);

        } else if (isCancelMode()) {
            updatePurchaseRequisition(pMsg);

        } else if (isTpShipConfMode()) {
            tpShipConf(pMsg);

        } else if (isCloseMode()) {
            updatePurchaseRequisitionHederClose(pMsg);
        }

        if (this.msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // QC#25900 Restore hazmat process.
        commonProcess(pMsg);

    }

    /**
     * Common Process.
     * @param pMsg NPZC103001PMsg
     */
    private void commonProcess(NPZC103001PMsg pMsg) {

        // QC#8295
        if (isCancelMode() || isCloseMode()) {
            return;
        } else if (PRCH_REQ_APVL_STS.SUBMIT_FOR_APPROVAL.equals(pMsg.prchReqApvlStsCd.getValue()) && isTechRequest(pMsg)) {
            setTechApprovalNotificationRequired(pMsg);
            // Sol#369(QC#19243) K.Kameoka Update Start
            // Check PR to send e-Mail for Choice
            checkSendMail(pMsg);
            // Sol#369(QC#19243) K.Kameoka Update End
            // START 2021/04/28 [QC#58645,MOD]
//        } else if (isPreApprovedWhOwnr(pMsg)) {
        } else if (isPreApprovedWhOwnr(pMsg) || PRCH_REQ_APVL_STS.PRE_APPROVED.equals(pMsg.prchReqApvlStsCd.getValue())) {
            // END 2021/04/28 [QC#58645,MOD]
            // QC#17487
            // Set Pre-Approved if target WH is included in specified WH owners.
            updatePoReqToPreApproved(pMsg);
        } else if (isSavedStatus(pMsg)) {
            return;
        }
        // START 2019/07/24 M.Naito [QC#51917,ADD]
        // check existing PO
        if (NPZC103001Constant.EVENT_ASN.equals(this.event)) {
            return;
        }
        // END 2019/07/24 M.Naito [QC#51917,ADD]
        if (isApprovedStatus(pMsg)) {
            // QC#21922
            if (isTechRequest(pMsg) && PRCH_REQ_TP.RUSH.equals(pMsg.prchReqTpCd.getValue())) {
                for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
                    // QC#28778 Mod Start
                    //if (PRCH_REQ_LINE_TP.INSOURCED_CH.equals(pMsg.prchReqInfo.no(i).prchReqLineTpCd.getValue())) {
                    // QC#30516
                    if (!isTpShipConfMode() && PRCH_REQ_LINE_TP.INSOURCED_CH.equals(pMsg.prchReqInfo.no(i).prchReqLineTpCd.getValue()) && isSendMailRushCho) {
                    // QC#28778 Mod End
                        PRCH_REQTMsg prTMsg = selectPRCH_REQTMsg(pMsg.prchReqNum.getValue());

                        PRCH_REQ_DTLTMsg paramTMsg = new PRCH_REQ_DTLTMsg();
                        ZYPEZDItemValueSetter.setValue(paramTMsg.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(paramTMsg.prchReqNum, pMsg.prchReqNum);
                        ZYPEZDItemValueSetter.setValue(paramTMsg.prchReqLineNum, pMsg.prchReqInfo.no(i).prchReqLineNum);
                        ZYPEZDItemValueSetter.setValue(paramTMsg.prchReqLineSubNum, BigDecimal.ZERO);
                        PRCH_REQ_DTLTMsg prdTMsg = (PRCH_REQ_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(paramTMsg);

                        if (prdTMsg == null) {
                            continue;
                        }

                        sendMailRushCho(pMsg, prTMsg, prdTMsg);
                        break;
                    }
                }
            }

            // QC#27433 Update.
            createWarehouseTransferOrder(pMsg);

        } else {
            // START 2019/07/24 M.Naito [QC#51917,ADD]
            for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(i).soNum) || ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(i).rwsNum)) {
                    return;
                }
            }
            // END 2019/07/24 M.Naito [QC#51917,ADD]
            poRequisitionApprovalWorkflow(pMsg);
            // Sol#369(QC#19243) K.Kameoka Update Start
            // Check PR to send e-Mail for Choice
            checkSendMail(pMsg);
            // Sol#369(QC#19243) K.Kameoka Update End
        }

    }

    /**
     * Input Parameter Check
     * @param pMsg NPZC103001PMsg
     */
    private void checkParam(NPZC103001PMsg pMsg) {

        // XX_MODE_CD
        if (!ZYPCommonFunc.hasValue(this.mode)) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0093E);
            return;
        }
        // GLBL_CMPY_CD
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0001E);
            return;
        }
        // EVENT_ID QC#8295
        if ((!isTpShipConfMode() && !isCloseMode()) && !ZYPCommonFunc.hasValue(this.event)) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0094E);
            return;
        }

        // Check by Mode
        if (isCreateMode()) {
            checkParamForCreate(pMsg);
        } else if (isUpdateMode()) {
            checkParamForUpdate(pMsg);
        } else if (isCancelMode()) {
            checkParamForCancel(pMsg);
        } else if (isTpShipConfMode()) {
            checkParamForTpShipConf(pMsg);
        } else if (isCloseMode()) {
            // QC#8295
            checkParamForClose(pMsg);
        }

    }

    /**
     * Parameter Check for Create Mode
     * @param pMsg NPZC103001PMsg
     */
    private void checkParamForCreate(NPZC103001PMsg pMsg) {
        // PROC_DT
        if (!ZYPCommonFunc.hasValue(pMsg.procDt)) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0059E);
            return;
        }

        // Check Header

        // PRCH_REQ_REC_TP_CD
        if (!ZYPCommonFunc.hasValue(pMsg.prchReqRecTpCd)) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0166E);
            return;
        }
        // PRCH_REQ_TP_CD
        if (!ZYPCommonFunc.hasValue(pMsg.prchReqTpCd)) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0096E);
            return;
        }

        // Check by Event
        if (NPZC103001Constant.EVENT_SUBMIT.equals(this.event)) {
            // PRCH_REQ_CRAT_BY_PSN_CD
            if (!ZYPCommonFunc.hasValue(pMsg.prchReqCratByPsnCd)) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0182E);
                return;
            }
            // PRCH_REQ_SRC_TP_CD
            if (!ZYPCommonFunc.hasValue(pMsg.prchReqSrcTpCd)) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0076E);
                return;
            }
            // FSR_NUM and SVC_TASK_NUM
            if (!checkServiceTask(pMsg)) {
                return;
            }
            // SHIP_TO_CUST Address
            if (!checkShipToCustAddress(pMsg)) {
                return;
            }

            // RQST_TECH_TOC_CD
            if (isTechRequest(pMsg)) {
                if (!ZYPCommonFunc.hasValue(pMsg.rqstTechTocCd)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0184E);
                    return;
                }
            }
        } else if (NPZC103001Constant.EVENT_INSOURCING.equals(this.event)) {
            // PRCH_REQ_NUM
            if (!ZYPCommonFunc.hasValue(pMsg.prchReqNum)) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0060E);
                return;
            }
        }

        // Check Detail
        if (pMsg.prchReqInfo.getValidCount() < 1) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0095E);
            return;
        }

        for (int idx = 0; idx < pMsg.prchReqInfo.getValidCount(); idx++) {
            NPZC103001_prchReqInfoPMsg info = pMsg.prchReqInfo.no(idx);

            if (ZYPCommonFunc.hasValue(info.configTpCd)) {
                isConfigLineExists = true;
            }

            // MDSE_CD
            if (!ZYPCommonFunc.hasValue(info.configTpCd) && !ZYPCommonFunc.hasValue(info.mdseCd)) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0020E);
                return;
            }
            // PRCH_REQ_QTY
            if (!ZYPCommonFunc.hasValue(info.prchReqQty)) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0064E);
                return;
            }

            // Check by Event
            if (NPZC103001Constant.EVENT_SUBMIT.equals(this.event)) {
                // DEST_INVTY_LOC_CD
                if (!PRCH_REQ_REC_TP.INVENTORY_REQUEST.equals(pMsg.prchReqRecTpCd.getValue()) && !ZYPCommonFunc.hasValue(info.destInvtyLocCd)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0185E);
                    return;
                }
            } else if (NPZC103001Constant.EVENT_INSOURCING.equals(this.event)) {
                // PRCH_REQ_LINE_NUM
                if (!ZYPCommonFunc.hasValue(info.prchReqLineNum)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0061E);
                    return;
                }
                // PRCH_REQ_LINE_TYPE_CD
                if (!ZYPCommonFunc.hasValue(info.prchReqLineTpCd)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0172E);
                    return;
                }
                // PROCR_TP_CD
                if (!ZYPCommonFunc.hasValue(info.procrTpCd)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0062E);
                    return;
                }
            }
        }
    }

    /**
     * Parameter Check for Update Mode
     * @param pMsg NPZC103001PMsg
     */
    private void checkParamForUpdate(NPZC103001PMsg pMsg) {
        // PROC_DT
        if ((NPZC103001Constant.EVENT_SUBMIT.equals(this.event) || NPZC103001Constant.EVENT_APPROVAL.equals(this.event)) && !ZYPCommonFunc.hasValue(pMsg.procDt)) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0059E);
            return;
        }

        // Check Header

        // PRCH_REQ_NUM
        if (!ZYPCommonFunc.hasValue(pMsg.prchReqNum)) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0060E);
            return;
        }

        // Check by Event

        if (NPZC103001Constant.EVENT_SUBMIT.equals(this.event)) {
            // FSR_NUM and SVC_TASK_NUM
            if (!checkServiceTask(pMsg)) {
                return;
            }
            // SHIP_TO_CUST Address
            if (!checkShipToCustAddress(pMsg)) {
                return;
            }
        } else if (NPZC103001Constant.EVENT_APPROVAL.equals(this.event)) {
            // PRCH_REQ_APVL_STS_CD
            if (!ZYPCommonFunc.hasValue(pMsg.prchReqApvlStsCd)) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0186E);
                return;
            }
        }

        // Check Detail
        if (NPZC103001Constant.EVENT_APPROVAL.equals(this.event)) {
            // "Approval" does not update "prchReqInfo"
            return;
        } else if (!NPZC103001Constant.EVENT_SUBMIT.equals(this.event) && pMsg.prchReqInfo.getValidCount() == 0) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0095E);
            return;
        }

        for (int idx = 0; idx < pMsg.prchReqInfo.getValidCount(); idx++) {
            NPZC103001_prchReqInfoPMsg info = pMsg.prchReqInfo.no(idx);

            // Key Check (Exclude Submit Event)
            if (!NPZC103001Constant.EVENT_SUBMIT.equals(this.event)) {
                // PRCH_REQ_LINE_NUM
                if (!ZYPCommonFunc.hasValue(info.prchReqLineNum)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0061E);
                    return;
                }
                // PRCH_REQ_LINE_SUB_NUM
                if (!ZYPCommonFunc.hasValue(info.prchReqLineSubNum)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0171E);
                    return;
                }
            }

            // Check by Event

            if (NPZC103001Constant.EVENT_SUBMIT.equals(this.event)) {
                // PROCR_TP_CD
                if (!ZYPCommonFunc.hasValue(info.procrTpCd)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0062E);
                    return;
                }
                // PRCH_REQ_QTY
                if (!ZYPCommonFunc.hasValue(info.prchReqQty)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0064E);
                    return;
                }
            } else if (NPZC103001Constant.EVENT_HOLD.equals(this.event)) {
                // PRCH_REQ_FRZ_FLG
                if (!ZYPCommonFunc.hasValue(info.prchReqFrzFlg)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0170E);
                    return;
                }
            } else if (NPZC103001Constant.EVENT_ORDER_RELEASE.equals(this.event)) {
                // PRCH_REQ_LINE_STS_CD
                if (!ZYPCommonFunc.hasValue(info.prchReqLineStsCd)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0187E);
                    return;
                }
                // PRCH_REQ_REL_QTY
                if (!ZYPCommonFunc.hasValue(info.prchReqRelQty)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0174E);
                    return;
                }
                // PRCH_REQ_REL_STS_CD
                if (!ZYPCommonFunc.hasValue(info.prchReqRelStsCd)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0188E);
                    return;
                }
            } else if (NPZC103001Constant.EVENT_SHIPPED.equals(this.event)) {
                // SO_NUM
                if (!ZYPCommonFunc.hasValue(info.soNum)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0028E);
                    return;
                }
            } else if (NPZC103001Constant.EVENT_RECEIVED.equals(this.event)) {
                // RWS_NUM
                if (!ZYPCommonFunc.hasValue(info.rwsNum)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0189E);
                    return;
                }
            } else if (NPZC103001Constant.EVENT_UPDATE.equals(this.event)) {
                // QC#21316 RWS_NUM mandatory check delete.
                // RWS_NUM
//                if (!ZYPCommonFunc.hasValue(info.rwsNum)) {
//                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0189E);
//                    return;
//                }
            } else if (NPZC103001Constant.EVENT_ASN.equals(this.event)) {
                // PRCH_REQ_LINE_NUM
                if (!ZYPCommonFunc.hasValue(info.prchReqLineNum)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0061E);
                    return;
                }
            }
        }
    }

    /**
     * Parameter Check for Cancel Mode
     * @param pMsg NPZC103001PMsg
     */
    private void checkParamForCancel(NPZC103001PMsg pMsg) {
        // PRCH_REQ_NUM
        if (!ZYPCommonFunc.hasValue(pMsg.prchReqNum)) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0060E);
            return;
        }

        // Check Detail

        if (NPZC103001Constant.EVENT_SHIPPED.equals(this.event)) {
            if (pMsg.prchReqInfo.getValidCount() == 0) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0095E);
                return;
            }
            for (int idx = 0; idx < pMsg.prchReqInfo.getValidCount(); idx++) {
                NPZC103001_prchReqInfoPMsg info = pMsg.prchReqInfo.no(idx);
                // PRCH_REQ_LINE_NUM
                if (!ZYPCommonFunc.hasValue(info.prchReqLineNum)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0061E);
                    return;
                }
                // PRCH_REQ_LINE_SUB_NUM
                if (!ZYPCommonFunc.hasValue(info.prchReqLineSubNum)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0171E);
                    return;
                }
                // SHIP_QTY
                if (!ZYPCommonFunc.hasValue(info.shipQty)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0195E);
                    return;
                }
            }
        }
    }

    /**
     * Parameter Check for Cancel Mode
     * @param pMsg NPZC103001PMsg
     */
    private void checkParamForTpShipConf(NPZC103001PMsg pMsg) {
        // RQST_TECH_TOC_CD
        if (isTechRequest(pMsg)) {
            if (!ZYPCommonFunc.hasValue(pMsg.rqstTechTocCd)) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0184E);
                return;
            }
        }

        // Check Detail
        if (pMsg.prchReqInfo.getValidCount() < 1) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0095E);
            return;
        }

        for (int idx = 0; idx < pMsg.prchReqInfo.getValidCount(); idx++) {
            NPZC103001_prchReqInfoPMsg info = pMsg.prchReqInfo.no(idx);
            // SRC_INVTY_LOC_CD
            if (!ZYPCommonFunc.hasValue(info.srcInvtyLocCd)) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0173E);
                return;
            }
            // MDSE_CD
            if (!ZYPCommonFunc.hasValue(info.mdseCd)) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0020E);
                return;
            }
            // PRCH_REQ_QTY
            if (!ZYPCommonFunc.hasValue(info.prchReqQty)) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0064E);
                return;
            }
            // TRX_REF_NUM
            if (!ZYPCommonFunc.hasValue(info.trxRefNum)) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0064E);
                return;
            }
        }
    }

    /**
     * Parameter Check for Close Mode
     * @param pMsg NPZC103001PMsg
     */
    private void checkParamForClose(NPZC103001PMsg pMsg) {
        // PRCH_REQ_NUM
        if (!ZYPCommonFunc.hasValue(pMsg.prchReqNum)) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0060E);
            return;
        }

        // PROC_DT
        if (!ZYPCommonFunc.hasValue(pMsg.procDt)) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0059E);
            return;
        }

    }

    /**
     * check FSR_NUM and SVC_TASK_NUM. PR Type "Premium Rush" or
     * "Rush" then FSR_NUM or SVC_TASK_NUM is mandatory.
     * @param pMsg NPZC103001PMsg
     * @return true: Normal, false: Error
     */
    private boolean checkServiceTask(NPZC103001PMsg pMsg) {

        // START 2019/08/22 M.Naito [QC#52582,ADD]
        Map<String, String> prchReqTpData = getPrchReqTpData(pMsg);
        if (prchReqTpData == null || !ZYPCommonFunc.hasValue(prchReqTpData.get(NPZC103001Constant.COL_SCE_ORD_TP_CD)) || !SCE_ORD_TP.TECH_REQUEST.equals(prchReqTpData.get(NPZC103001Constant.COL_SCE_ORD_TP_CD))) {
            return true;
        }
        // END 2019/08/22 M.Naito [QC#52582,ADD]
        if (ZYPCommonFunc.hasValue(pMsg.fsrNum) || ZYPCommonFunc.hasValue(pMsg.svcTaskNum) || ZYPCommonFunc.hasValue(pMsg.svcMachSerNum)) {

            // QC#55778 Skip FSR check if TR is already saved.
            if (ZYPCommonFunc.hasValue(pMsg.prchReqNum.getValue())) {
                return true;
            }

            Map<String, Object> svcTaskMap = getSvcTask(pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNum.getValue(), pMsg.fsrNum.getValue(), pMsg.svcMachSerNum.getValue());
            if (svcTaskMap == null) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0263E);
                return false;
            }
            String svcTaskNum = (String) svcTaskMap.get("SVC_TASK_NUM");
            String fsrNum = (String) svcTaskMap.get("FSR_NUM");
            String svcMachSerNum = (String) svcTaskMap.get("SER_NUM");
            ZYPEZDItemValueSetter.setValue(pMsg.svcTaskNum, svcTaskNum);
            ZYPEZDItemValueSetter.setValue(pMsg.fsrNum, fsrNum);
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachSerNum, svcMachSerNum);

        } else {

            // Premium Rush or Rush
            // QC:7389
            Map<String, Object> svcTaskCheckControlMap = getSvcTaskCheckControl(pMsg.glblCmpyCd.getValue(), pMsg.prchReqTpCd.getValue());
            if (svcTaskCheckControlMap != null) {
                String dsCondConstValTxt_07 = (String) svcTaskCheckControlMap.get(NPZC103001Constant.DB_COLUMN_DS_COND_CONST_VAL_TXT_07);
                if (ZYPConstant.FLG_ON_Y.equals(dsCondConstValTxt_07)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0183E);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * (Conditional Mandatory) Partial input is not allowed for
     * address (more accurately, address mandatory fields. other
     * fields are optional).
     * @param pMsg
     * @return true: Normal, false: Error
     */
    private boolean checkShipToCustAddress(NPZC103001PMsg pMsg) {
        boolean hasAddressInput = ZYPCommonFunc.hasValue(pMsg.shipToFirstLineAddr) //
                || ZYPCommonFunc.hasValue(pMsg.shipToCtyAddr) // 
                || ZYPCommonFunc.hasValue(pMsg.shipToStCd) //
                || ZYPCommonFunc.hasValue(pMsg.shipToPostCd);
        boolean isNormal = true;

        if (hasAddressInput || CTRY.UNITED_STATES_OF_AMERICA.equals(pMsg.shipToCtryCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(pMsg.shipToCustCd)) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0167E);
                isNormal = false;
            }
        }

        if (hasAddressInput && !ZYPCommonFunc.hasValue(pMsg.shipToLocNm)) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0103E);
            isNormal = false;
        }

        if (hasAddressInput && !ZYPCommonFunc.hasValue(pMsg.shipToFirstLineAddr)) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0104E);
            isNormal = false;
        }

        if (hasAddressInput && !ZYPCommonFunc.hasValue(pMsg.shipToCtyAddr)) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0105E);
            isNormal = false;
        }

        if (hasAddressInput && CTRY.UNITED_STATES_OF_AMERICA.equals(pMsg.shipToCtryCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(pMsg.shipToStCd)) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0106E);
                isNormal = false;
            }
        }

        if (hasAddressInput && !ZYPCommonFunc.hasValue(pMsg.shipToPostCd)) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0107E);
            isNormal = false;
        }
        return isNormal;
    }

    /**
     * is Create Mode
     * @return true: Create Mode, false: Other Mode
     */
    private boolean isCreateMode() {
        return NPZC103001Constant.MODE_CREATE.equals(this.mode);
    }

    /**
     * is Update Mode
     * @return true: Update Mode, false: Other Mode
     */
    private boolean isUpdateMode() {
        return NPZC103001Constant.MODE_UPDATE.equals(this.mode);
    }

    /**
     * is Cancel Mode
     * @return true: Cancel Mode, false: Other Mode
     */
    private boolean isCancelMode() {
        return NPZC103001Constant.MODE_CANCEL.equals(this.mode);
    }

    /**
     * is TP Ship Conf Mode
     * @return true: TP Ship Conf Mode, false: Other Mode
     */
    private boolean isTpShipConfMode() {
        return NPZC103001Constant.MODE_TP_SHIP_CONF.equals(this.mode);
    }

    /**
     * is Close Mode
     * @return true: Close Mode, false: Other Mode
     */
    private boolean isCloseMode() {
        return NPZC103001Constant.MODE_CLOSE.equals(this.mode);
    }

    private boolean isTechRequest(NPZC103001PMsg pMsg) {
        if (PRCH_REQ_REC_TP.TECH_REQUEST.equals(pMsg.prchReqRecTpCd.getValue()) || PRCH_REQ_REC_TP.TECH_RETURN.equals(pMsg.prchReqRecTpCd.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * Master Validation.
     * @param pMsg NPZC103001PMsg
     */
    private void masterValidation(NPZC103001PMsg pMsg) {

        for (int idx = 0; idx < pMsg.prchReqInfo.getValidCount(); idx++) {
            NPZC103001_prchReqInfoPMsg info = pMsg.prchReqInfo.no(idx);
            // DEST_INVTY_LOC_CD
            if (ZYPCommonFunc.hasValue(info.destInvtyLocCd) && !existInvtyLocCd(info.destInvtyLocCd.getValue())) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0047E);
                return;
            }
            // MDSE_CD
            if (ZYPCommonFunc.hasValue(info.mdseCd) && !existMdseCd(info.mdseCd.getValue())) {
                String newMdseCd = null;

                if (PRCH_REQ_REC_TP.TECH_REQUEST.equals(pMsg.prchReqRecTpCd.getValue())) {
                    // Tech Request
                    newMdseCd = getSupersedeItem(pMsg, info.mdseCd.getValue(), info.vndCd.getValue());
                }

                if (newMdseCd == null) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0045E);
                    return;
                } else {
                    // Set Original Request Merchandise Code
                    Map<String, Object> keyVal = addlDtlLst.get(idx);
                    keyVal.put(NPZC103001Constant.COL_ORIG_RQST_MDSE_CD, info.mdseCd.getValue());
                    addlDtlLst.set(idx, keyVal);
                    // Change New Merchandise Code
                    info.mdseCd.setValue(newMdseCd);
                }
            }
            // SRC_INVTY_LOC_CD
            if (ZYPCommonFunc.hasValue(info.procrTpCd) && PROCR_TP.SUPPLIER.equals(info.procrTpCd.getValue())) {
                // SUPPLIER -> VND_CD
                if (ZYPCommonFunc.hasValue(info.srcInvtyLocCd) && !existVndCd(info.srcInvtyLocCd.getValue())) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0043E);
                    return;
                }
            } else {
                if (ZYPCommonFunc.hasValue(info.srcInvtyLocCd) && !existInvtyLocCd(info.srcInvtyLocCd.getValue())) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0098E);
                    return;
                }
            }
        }
    }

    /**
     * Exists Inventory Location Code
     * @param invtyLocCd Inventory Location Code
     * @return true: exists, false: no exists
     */
    private boolean existInvtyLocCd(String invtyLocCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invtyLocCd", invtyLocCd);
        queryParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        List<Map> resultList = (List<Map>) ssmBatchClient.queryObjectList("existInvtyLocCd", queryParam);

        if (resultList == null || resultList.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Exists Merchandise Code
     * @param mdseCd Merchandise Code
     * @return true: exists, false: no exists
     */
    private boolean existMdseCd(String mdseCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("rgtnStsCds", new String[] {RGTN_STS.READY_FOR_ORDER_TAKING, RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING });

        List<Map> resultList = (List<Map>) ssmBatchClient.queryObjectList("existMdseCd", queryParam);

        if (resultList == null || resultList.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Exists Vendor Code
     * @param vndCd Vendor Code
     * @return true: exists, false: no exists
     */
    private boolean existVndCd(String vndCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("vndCd", vndCd);
        queryParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        List<Map> resultList = (List<Map>) ssmBatchClient.queryObjectList("existVndCd", queryParam);

        if (resultList == null || resultList.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Insert PRCH_REQ and PRCH_REQ_DTL
     * @param pMsg
     */
    private void insertPurchaseRequisition(NPZC103001PMsg pMsg) {

        // Header
        PRCH_REQTMsg prchReqTMsg = null;
        if (NPZC103001Constant.EVENT_SUBMIT.equals(this.event)) {
            prchReqTMsg = getHdrTMsgForInsert(pMsg);

            if (prchReqTMsg == null) {
                return;
            }
            S21FastTBLAccessor.insert(prchReqTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prchReqTMsg.getReturnCode())) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0099E);
                return;
            }
        }

        // Detail
        List<PRCH_REQ_DTLTMsg> prchReqDtlTMsg = null;

        if (NPZC103001Constant.EVENT_INSOURCING.equals(this.event)) {

            prchReqDtlTMsg = new ArrayList<PRCH_REQ_DTLTMsg>();

            // Get Header for Create History
            prchReqTMsg = selectPRCH_REQTMsg(pMsg.prchReqNum.getValue());
            setupPMsgforUpdate(pMsg, prchReqTMsg);

            PRCH_REQ_DTLTMsg insrcOrigLineTMsg = null;

            String prePrchReqLineNum = null;
            for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {

                String prchReqLineNum = pMsg.prchReqInfo.no(i).prchReqLineNum.getValue();

                // Original Line Update
                insrcOrigLineTMsg = getDtlInsrcOrigLine(pMsg, i);

                if (insrcOrigLineTMsg == null) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0291E);
                    return;
                }

                S21FastTBLAccessor.update(insrcOrigLineTMsg);
                if (insrcOrigLineTMsg != null && !EZDTBLAccessor.RTNCD_NORMAL.equals(insrcOrigLineTMsg.getReturnCode())) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0291E);
                    return;
                }

                // create TMsg for Insourced PR Line
                if (prchReqLineNum.equals(prePrchReqLineNum)) {
                    prchReqDtlTMsg.add(getDtlTMsgForInsrc(pMsg, i, insrcOrigLineTMsg, true));
                } else {
                    prchReqDtlTMsg.add(getDtlTMsgForInsrc(pMsg, i, insrcOrigLineTMsg, false));
                }

                prePrchReqLineNum = prchReqLineNum;
            }

            // Original Line Update History
            List<PRCH_REQ_DTLTMsg> origLineList = new ArrayList<PRCH_REQ_DTLTMsg>();
            origLineList.add(insrcOrigLineTMsg);
            createBizProcLog(prchReqTMsg, origLineList);

        } else {
            // create TMsg for Insourced PR Line
            prchReqDtlTMsg = getDtlTMsgListForInsert(pMsg);
        }

        int lineNum = S21FastTBLAccessor.insert(prchReqDtlTMsg.toArray(new PRCH_REQ_DTLTMsg[prchReqDtlTMsg.size()]));

        if (lineNum != prchReqDtlTMsg.size()) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0289E);
            return;
        }

        if (pMsg.serNumInfo.getValidCount() > 0) {
            // Serial
            List<PRCH_REQ_SERTMsg> prchReqSerTMsg = new ArrayList<PRCH_REQ_SERTMsg>();
            for (int i = 0; i < pMsg.serNumInfo.getValidCount(); i++) {
                prchReqSerTMsg.add(getSerTMsgForInsert(pMsg, i));
            }
            lineNum = S21FastTBLAccessor.insert(prchReqSerTMsg.toArray(new PRCH_REQ_SERTMsg[prchReqSerTMsg.size()]));
            if (lineNum != prchReqSerTMsg.size()) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0290E);
                return;
            }
        }

        // History
        createBizProcLog(prchReqTMsg, prchReqDtlTMsg);
    }

    /**
     * get PRCH_REQ TMsg for Insert
     * @param pMsg
     * @return PRCH_REQTMsg
     */
    private PRCH_REQTMsg getHdrTMsgForInsert(NPZC103001PMsg pMsg) {

        PRCH_REQTMsg prchReqTMsg = new PRCH_REQTMsg();

        this.currPrchReqNum = ZYPOracleSeqAccessor.getNumberString(NPZC103001Constant.PRCH_REQ_NUM_SQ, NPZC103001Constant.PRCH_REQ_NUM_SQ_DIGIT);
        pMsg.prchReqNum.setValue(this.currPrchReqNum);
        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        // PRCH_REQ_NUM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqNum, pMsg.prchReqNum);
        // PRCH_REQ_REC_TP_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqRecTpCd, pMsg.prchReqRecTpCd);
        // PRCH_REQ_TP_CD
        getPrchReqTpCd(pMsg);
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqTpCd, pMsg.prchReqTpCd);
        // SHIP_TO_CUST_CD
        // QC#26616
        if ((
                PRCH_REQ_SRC_TP.INSOURCING.equals(pMsg.prchReqSrcTpCd.getValue()) //
                || PRCH_REQ_SRC_TP.WH_PLANNING.equals(pMsg.prchReqSrcTpCd.getValue()) //
                || PRCH_REQ_SRC_TP.TECH_PLANNING.equals(pMsg.prchReqSrcTpCd.getValue()) //
                || PRCH_REQ_SRC_TP.TECH_REQUEST_ENTRY.equals(pMsg.prchReqSrcTpCd.getValue()) //
                || PRCH_REQ_SRC_TP.PHONE.equals(pMsg.prchReqSrcTpCd.getValue()) //
             ) 
             && ZYPCommonFunc.hasValue(pMsg.shipToCustCd)) {
            getShipToCustAddress(pMsg);

            // QC#2366 Update
            ZYPEZDItemValueSetter.setValue(pMsg.poQlfyCd, PO_QLFY.TECH_REQUEST);

        } else if (ZYPCommonFunc.hasValue(pMsg.shipToCustCd)) {
            getShipToCustAddress(pMsg);
        }

        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToCustCd, pMsg.shipToCustCd);
        // SHIP_TO_LOC_NM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToLocNm, pMsg.shipToLocNm);
        // SHIP_TO_ADDL_LOC_NM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToAddlLocNm, pMsg.shipToAddlLocNm);
        // SHIP_TO_FIRST_LINE_ADDR
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToFirstLineAddr, pMsg.shipToFirstLineAddr);
        // SHIP_TO_SCD_LINE_ADDR
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToScdLineAddr, pMsg.shipToScdLineAddr);
        // SHIP_TO_THIRD_LINE_ADDR
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToThirdLineAddr, pMsg.shipToThirdLineAddr);
        // SHIP_TO_FRTH_LINE_ADDR
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToFrthLineAddr, pMsg.shipToFrthLineAddr);
        // SHIP_TO_FIRST_REF_CMNT_TXT
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToFirstRefCmntTxt, pMsg.shipToFirstRefCmntTxt);
        // SHIP_TO_SCD_REF_CMNT_TXT
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToScdRefCmntTxt, pMsg.shipToScdRefCmntTxt);
        // SHIP_TO_CTY_ADDR
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToCtyAddr, pMsg.shipToCtyAddr);
        // SHIP_TO_ST_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToStCd, pMsg.shipToStCd);
        // SHIP_TO_PROV_NM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToProvNm, pMsg.shipToProvNm);
        // SHIP_TO_CNTY_NM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToCntyNm, pMsg.shipToCntyNm);
        // SHIP_TO_POST_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToPostCd, pMsg.shipToPostCd);
        // SHIP_TO_CTRY_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToCtryCd, pMsg.shipToCtryCd);
        // BILL_TO_CUST_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.billToCustCd, pMsg.billToCustCd);
        // SELL_TO_CUST_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.sellToCustCd, pMsg.sellToCustCd);
        // PRCH_REQ_CRAT_DT
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCratDt, pMsg.procDt);
        // PRCH_REQ_CRAT_TM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCratTm, ZYPDateUtil.getCurrentSystemTime(NPZC103001Constant.TIME_PATTERN));
        // PRCH_REQ_CRAT_TZ
        getPrchReqCratTz(pMsg);
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCratTz, (String) addlHdrMap.get(NPZC103001Constant.COL_PRCH_REQ_CRAT_TZ));
        // PRCH_REQ_CRAT_DISP_DT_TM_TS
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCratDispDtTmTs, (String) addlHdrMap.get(NPZC103001Constant.COL_PRCH_REQ_CRAT_DISP_DT_TM_TS));
        // PRCH_REQ_CRAT_BY_PSN_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCratByPsnCd, pMsg.prchReqCratByPsnCd);
        // PRCH_REQ_CRAT_BY_NM
        String cratByNm = getFullPersonName(pMsg.prchReqCratByPsnCd.getValue());
        if (ZYPCommonFunc.hasValue(cratByNm)) {
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCratByNm, cratByNm);
        } else {
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCratByNm, pMsg.prchReqCratByPsnCd);
        }
        // PRCH_REQ_RQST_BY_PSN_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqRqstByPsnCd, pMsg.prchReqRqstByPsnCd);
        // RQST_TECH_TOC_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.rqstTechTocCd, pMsg.rqstTechTocCd);
        // PRCH_REQ_STS_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqStsCd, PRCH_REQ_STS.OPEN);
        // PRCH_REQ_APVL_STS_CD
        if (!ZYPCommonFunc.hasValue(pMsg.prchReqApvlStsCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.SUBMIT_FOR_APPROVAL);
        }
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlStsCd, pMsg.prchReqApvlStsCd);
        // PRCH_REQ_APVL_DT
        if ((PRCH_REQ_APVL_STS.PRE_APPROVED.equals(pMsg.prchReqApvlStsCd.getValue())) || (PRCH_REQ_APVL_STS.APPROVED.equals(pMsg.prchReqApvlStsCd.getValue()))) {
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlDt, pMsg.procDt);
            // PRCH_REQ_APVL_BY_PSN_CD
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlByPsnCd, pMsg.prchReqApvlByPsnCd);
            if (ZYPCommonFunc.hasValue(pMsg.prchReqApvlByPsnCd)) {
                // PRCH_REQ_APVL_BY_NM
                ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlByNm, getFullPersonName(pMsg.prchReqApvlByPsnCd.getValue()));
            }
        }
        // PRCH_REQ_SRC_TP_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqSrcTpCd, pMsg.prchReqSrcTpCd);
        // TRX_REF_NUM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.trxRefNum, pMsg.prchReqInfo.no(0).trxRefNum);
        // FSR_NUM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.fsrNum, pMsg.fsrNum);
        // SVC_TASK_NUM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.svcTaskNum, pMsg.svcTaskNum);
        // SVC_MACH_SER_NUM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.svcMachSerNum, pMsg.svcMachSerNum);
        // CPO_ORD_NUM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.cpoOrdNum, pMsg.cpoOrdNum);
        // CUST_ISS_PO_NUM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.custIssPoNum, pMsg.custIssPoNum);
        // CUST_ISS_PO_DT
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.custIssPoDt, pMsg.custIssPoDt);
        // CPO_ORD_TP_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.cpoOrdTpCd, pMsg.cpoOrdTpCd);
        // CTAC_PSN_NM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.ctacPsnNm, pMsg.ctacPsnNm);
        // ADMIN_PSN_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.adminPsnCd, pMsg.adminPsnCd);
        // PO_QLFY_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.poQlfyCd, pMsg.poQlfyCd);
        // VND_DROP_SHIP_FLG
        if (ZYPCommonFunc.hasValue(pMsg.poQlfyCd)) {
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.vndDropShipFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.vndDropShipFlg, ZYPConstant.FLG_OFF_N);
        }
        this.addlHdrMap.put(NPZC103001Constant.COL_VND_DROP_SHIP_FLG, prchReqTMsg.vndDropShipFlg.getValue());
        // PRCH_GRP_CD
        if (!ZYPCommonFunc.hasValue(pMsg.prchGrpCd)) {
            String prchGrpCd = getPrchGrpCd(pMsg);
            if (prchGrpCd == null) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0235E);
                return null;
            }
            ZYPEZDItemValueSetter.setValue(pMsg.prchGrpCd, prchGrpCd);
        }
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchGrpCd, pMsg.prchGrpCd);
        // LINE_BIZ_TP_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.lineBizTpCd, pMsg.lineBizTpCd);
        // PRCH_REQ_CMNT_TXT
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCmntTxt, pMsg.prchReqCmntTxt);
        // DELY_ADDL_CMNT_TXT
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.delyAddlCmntTxt, pMsg.delyAddlCmntTxt);
        // RCV_ADDL_CMNT_TXT
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.rcvAddlCmntTxt, pMsg.rcvAddlCmntTxt);
        //08/08/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        // MRP_PLN_NM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.mrpPlnNm, pMsg.mrpPlnNm);
        //08/08/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        // START 2023/02/15 T.Kuroda [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.rqstShipDt, pMsg.rqstShipDt);
        // END   2023/02/15 T.Kuroda [QC#60966, ADD]

        // Sol#118(QC#12110) Start
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.techExpRqstFlg, ZYPConstant.FLG_OFF_N);
        for (int n = 0; n < pMsg.prchReqInfo.getValidCount(); ++n) {
            NPZC103001_prchReqInfoPMsg dtlPMsg = pMsg.prchReqInfo.no(n);
            String mdseCd = dtlPMsg.mdseCd.getValue();

            MDSETMsg tMsg = new MDSETMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
            tMsg = (MDSETMsg) EZDTBLAccessor.findByKey(tMsg);
            if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.prtItemTpCd)) {
                if (PRT_ITEM_TP.TOOL.equals(tMsg.prtItemTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(prchReqTMsg.techExpRqstFlg, ZYPConstant.FLG_ON_Y);
                    isToolItem = true;
                    break;
                }
            }
        }
        // Sol#118(QC#12110) End

        return prchReqTMsg;
    }

    /**
     * get PRCH_REQ TMsg for TP Ship Conf Original Header
     * @param prchReqTMsg PRCH_REQTMsg
     * @param pMsg NPZC103001PMsg
     * @param origPrNum String
     * @return PRCH_REQTMsg
     */
    private PRCH_REQTMsg getHdrTMsgForTpShipConf(PRCH_REQTMsg prchReqTMsg, NPZC103001PMsg pMsg, String origPrNum) {

        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        // PRCH_REQ_NUM
        this.currPrchReqNum = ZYPOracleSeqAccessor.getNumberString(NPZC103001Constant.PRCH_REQ_NUM_SQ, NPZC103001Constant.PRCH_REQ_NUM_SQ_DIGIT);
        pMsg.prchReqNum.setValue(this.currPrchReqNum);
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqNum, pMsg.prchReqNum);
        // PRCH_REQ_REC_TP_CD
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqRecTpCd, PRCH_REQ_REC_TP.TECH_REQUEST);
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqRecTpCd, pMsg.prchReqRecTpCd);
        // QC#57659
        // PRCH_REQ_TP_CD
        if (!ZYPCommonFunc.hasValue(pMsg.prchReqTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqTpCd, PRCH_REQ_TP.PREMIUM_RUSH);
        }
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqTpCd, pMsg.prchReqTpCd);
        // PRCH_REQ_CRAT_DT
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCratDt, pMsg.procDt);
        // PRCH_REQ_CRAT_TM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCratTm, ZYPDateUtil.getCurrentSystemTime(NPZC103001Constant.TIME_PATTERN));
        // PRCH_REQ_CRAT_BY_PSN_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCratByPsnCd, ZYPCodeDataUtil.getVarCharConstValue(NPZC103001Constant.PR_CRAT_SYSTEM_USER, pMsg.glblCmpyCd.getValue()));
        // PRCH_REQ_CRAT_BY_NM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCratByNm, ZYPCodeDataUtil.getVarCharConstValue(NPZC103001Constant.PR_CRAT_SYSTEM_USER, pMsg.glblCmpyCd.getValue()));
        // PRCH_REQ_RQST_BY_PSN_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqRqstByPsnCd, pMsg.rqstTechTocCd);
        // RQST_TECH_TOC_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.rqstTechTocCd, pMsg.rqstTechTocCd);
        // PRCH_REQ_STS_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqStsCd, PRCH_REQ_STS.OPEN);
        // PRCH_REQ_APVL_STS_CD
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.PRE_APPROVED);
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlStsCd, pMsg.prchReqApvlStsCd);
        // PRCH_REQ_APVL_DT
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlDt, pMsg.procDt);
        // PRCH_REQ_APVL_BY_PSN_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlByPsnCd, pMsg.rqstTechTocCd);
        // PRCH_REQ_APVL_BY_NM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlByNm, getFullPersonName(pMsg.rqstTechTocCd.getValue()));

        // QC#57659
        // PRCH_REQ_SRC_TP_CD
        if (!ZYPCommonFunc.hasValue(pMsg.prchReqSrcTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqSrcTpCd, PRCH_REQ_SRC_TP.CHOICE_SHIP_CONFIRMATION);
        }
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqSrcTpCd, pMsg.prchReqSrcTpCd);
        // TRX_REF_NUM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.trxRefNum, origPrNum);
        // PO_QLFY_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.poQlfyCd, PO_QLFY.TECH_REQUEST);
        // VND_DROP_SHIP_FLG
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.vndDropShipFlg, ZYPConstant.FLG_ON_Y);

        // SHIP_TO_LOC_NM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToLocNm, pMsg.shipToLocNm);
        // SHIP_TO_FIRST_LINE_ADDR
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToFirstLineAddr, pMsg.shipToFirstLineAddr);
        // SHIP_TO_SCD_LINE_ADDR
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToScdLineAddr, pMsg.shipToScdLineAddr);
        // SHIP_TO_THIRD_LINE_ADDR
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToThirdLineAddr, pMsg.shipToThirdLineAddr);
        // SHIP_TO_FRTH_LINE_ADDR
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToFrthLineAddr, pMsg.shipToFrthLineAddr);
        // SHIP_TO_CTY_ADDR
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToCtyAddr, pMsg.shipToCtyAddr);
        // SHIP_TO_ST_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToStCd, pMsg.shipToStCd);
        // SHIP_TO_POST_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToPostCd, pMsg.shipToPostCd);
        // SHIP_TO_CTRY_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToCtryCd, pMsg.shipToCtryCd);
        // CTAC_PSN_NM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.ctacPsnNm, pMsg.ctacPsnNm);

        // Sol#118(QC#12110) Start
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.techExpRqstFlg, ZYPConstant.FLG_OFF_N);
        for (int n = 0; n < pMsg.prchReqInfo.getValidCount(); ++n) {
            NPZC103001_prchReqInfoPMsg dtlPMsg = pMsg.prchReqInfo.no(n);
            String mdseCd = dtlPMsg.mdseCd.getValue();

            MDSETMsg tMsg = new MDSETMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
            tMsg = (MDSETMsg) EZDTBLAccessor.findByKey(tMsg);
            if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.prtItemTpCd)) {
                if (PRT_ITEM_TP.TOOL.equals(tMsg.prtItemTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(prchReqTMsg.techExpRqstFlg, ZYPConstant.FLG_ON_Y);
                    isToolItem = true;
                    break;
                }
            }
        }
        // Sol#118(QC#12110) End

        return prchReqTMsg;
    }

    /**
     * getDtlInsrcOrigLine
     * @param pMsg NPZC103001PMsg
     * @param idx int
     * @return PRCH_REQ_DTLTMsg
     */
    private PRCH_REQ_DTLTMsg getDtlInsrcOrigLine(NPZC103001PMsg pMsg, int idx) {

        PRCH_REQ_DTLTMsg paramTMsg = new PRCH_REQ_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(paramTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(paramTMsg.prchReqNum, pMsg.prchReqNum);
        ZYPEZDItemValueSetter.setValue(paramTMsg.prchReqLineNum, pMsg.prchReqInfo.no(idx).prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(paramTMsg.prchReqLineSubNum, BigDecimal.ZERO);
        PRCH_REQ_DTLTMsg updTMsg = (PRCH_REQ_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(paramTMsg);

        if (updTMsg == null) {
            return null;
        }
        BigDecimal relQty = updTMsg.prchReqRelQty.getValue();
        if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(idx).prchReqQty)) {
            // PRCH_REQ_REL_QTY
            // Before RelQty + Param PrchReqQty
            relQty = relQty.add(pMsg.prchReqInfo.no(idx).prchReqQty.getValue());
            ZYPEZDItemValueSetter.setValue(updTMsg.prchReqRelQty, relQty);

            // PRCH_REQ_BAL_QTY
            // Before PrchReqQty - Param PrchReqQty
            ZYPEZDItemValueSetter.setValue(updTMsg.prchReqBalQty, updTMsg.prchReqBalQty.getValue().subtract(pMsg.prchReqInfo.no(idx).prchReqQty.getValue()));
        }
        if (isInsourced(pMsg, idx)) {
            // Insourced Flag = Y
            // PRCH_REQ_INSRC_QTY
            BigDecimal insrcQty = updTMsg.prchReqInsrcQty.getValue();
            if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(idx).prchReqQty)) {
                insrcQty = insrcQty.add(pMsg.prchReqInfo.no(idx).prchReqQty.getValue());
            }
            ZYPEZDItemValueSetter.setValue(updTMsg.prchReqInsrcQty, insrcQty);
            // INSRC_FLG
            ZYPEZDItemValueSetter.setValue(updTMsg.insrcFlg, ZYPConstant.FLG_ON_Y);
        }
        // PRCH_REQ_REL_STS_CD
        if (BigDecimal.ZERO.compareTo(updTMsg.prchReqBalQty.getValue()) == 0) {
            ZYPEZDItemValueSetter.setValue(updTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.COMPLEATED);
        } else {
            ZYPEZDItemValueSetter.setValue(updTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.IN_COMPLETED);
        }
        // PO_CRAT_FLG
        if (PROCR_TP.SUPPLIER.equals(pMsg.prchReqInfo.no(idx).procrTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(updTMsg.poCratFlg, ZYPConstant.FLG_ON_Y);
        }
        //08/31/2017 CITS S.Endo Add Sol#406(QC#19243) START
        // 08/23/2018 QC#27660 Update. 
        ZYPEZDItemValueSetter.setValue(updTMsg.prchReqLineCmntTxt, //
                addFirstLineCmnt(pMsg.prchReqInfo.no(idx).prchReqLineCmntTxt.getValue(), updTMsg.prchReqLineCmntTxt.getValue()));
        ZYPEZDItemValueSetter.setValue(updTMsg.prchReqRelErrMsgTxt, pMsg.prchReqInfo.no(idx).prchReqLineCmntTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(idx).prchReqLineCmntTxt, "");
        //08/31/2017 CITS S.Endo Add Sol#406(QC#19243) END

        return updTMsg;
    }

    /**
     * get PRCH_REQ_DTL TMsg for Insert(Insource Child Line)
     * @param pMsg NPZC103001PMsg
     * @param idx int
     * @param origLineTMsg PRCH_REQ_DTLTMsg
     * @param isSubLineAdd boolean
     * @return PRCH_REQ_DTLTMsg
     */
    private PRCH_REQ_DTLTMsg getDtlTMsgForInsrc(NPZC103001PMsg pMsg, int idx, PRCH_REQ_DTLTMsg origLineTMsg, boolean isSubLineAdd) {

        PRCH_REQ_DTLTMsg prchReqDtlTMsg = new PRCH_REQ_DTLTMsg();
        NPZC103001_prchReqInfoPMsg dtlInfo = pMsg.prchReqInfo.no(idx);
        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        // PRCH_REQ_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqNum, origLineTMsg.prchReqNum);
        // PRCH_REQ_LINE_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineNum, origLineTMsg.prchReqLineNum);
        // output parameter
        ZYPEZDItemValueSetter.setValue(dtlInfo.prchReqLineNum, prchReqDtlTMsg.prchReqLineNum);
        // PRCH_REQ_LINE_SUB_NUM
        // QC#17005 CITS T.Tokutomi START
        BigDecimal subNum = BigDecimal.ZERO;

        // QC#17248 CITS K.Ogino START
        if (isSubLineAdd) {
            subNum = BigDecimal.ONE//
                    .add(getMaxPrchReqLineSubNum(pMsg.prchReqNum.getValue(), dtlInfo.prchReqLineNum.getValue())) //
                    .add(BigDecimal.valueOf(idx));
        } else {
            subNum = BigDecimal.ONE//
                    .add(getMaxPrchReqLineSubNum(pMsg.prchReqNum.getValue(), dtlInfo.prchReqLineNum.getValue()));
        }

        // QC#17248 CITS K.Ogino END
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineSubNum, subNum);
        // QC#17005 CITS T.Tokutomi END
        // output parameter
        ZYPEZDItemValueSetter.setValue(dtlInfo.prchReqLineSubNum, prchReqDtlTMsg.prchReqLineSubNum);
        // ORIG_PRCH_REQ_LINE_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.origPrchReqLineNum, dtlInfo.origPrchReqLineNum);
        // ORIG_PRCH_REQ_LINE_SUB_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.origPrchReqLineSubNum, dtlInfo.origPrchReqLineSubNum);
        // PRCH_REQ_LINE_TP_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineTpCd, dtlInfo.prchReqLineTpCd);
        // PRCH_REQ_CRAT_DT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCratDt, pMsg.procDt);
        // PRCH_REQ_CRAT_TM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCratTm, ZYPDateUtil.getCurrentSystemTime(NPZC103001Constant.TIME_PATTERN));
        // PRCH_REQ_LINE_STS_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, origLineTMsg.prchReqLineStsCd);
        // PRCH_REQ_REL_STS_CD
        if (!ZYPCommonFunc.hasValue(dtlInfo.prchReqRelStsCd)) {
            ZYPEZDItemValueSetter.setValue(dtlInfo.prchReqRelStsCd, PRCH_REQ_REL_STS.IN_COMPLETED);
        }
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelStsCd, dtlInfo.prchReqRelStsCd);
        // PROCR_TP_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.procrTpCd, dtlInfo.procrTpCd);
        // SRC_INVTY_LOC_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcInvtyLocCd, dtlInfo.srcInvtyLocCd);

        if (ZYPCommonFunc.hasValue(dtlInfo.srcInvtyLocCd)) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcInvtyLocCd, dtlInfo.srcInvtyLocCd);
            // get SRC_RTL_WH_CD/SRC_RTL_SWH_CD
            Map<String, String> locDat = getWhAndSwhByInvtyLocCd(dtlInfo.srcInvtyLocCd.getValue());

            if (locDat != null) {
                // SRC_RTL_WH_CD
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcRtlWhCd, locDat.get(NPZC103001Constant.RTL_WH_CD));
                // SRC_RTL_SWH_CD
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcRtlSwhCd, locDat.get(NPZC103001Constant.RTL_SWH_CD));
            }
        }
        // PRNT_VND_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prntVndCd, dtlInfo.prntVndCd);
        // VND_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.vndCd, dtlInfo.vndCd);
        // VND_INVTY_LOC_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.vndInvtyLocCd, dtlInfo.vndInvtyLocCd);
        // DEST_INVTY_LOC_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.destInvtyLocCd, origLineTMsg.destInvtyLocCd);
        // DEST_RTL_WH_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.destRtlWhCd, origLineTMsg.destRtlWhCd);
        // DEST_RTL_SWH_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.destRtlSwhCd, origLineTMsg.destRtlSwhCd);
        // RQST_RCV_DT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rqstRcvDt, origLineTMsg.rqstRcvDt);
        // RQST_RCV_DT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rqstRcvDt, origLineTMsg.rqstRcvDt);
        // RQST_RCV_TM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rqstRcvTm, origLineTMsg.rqstRcvTm);
        // START 2023/02/08 T.Kuroda [QC#60966, ADD]
        // RQST_SHIP_DT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rqstShipDt, origLineTMsg.rqstShipDt);
        // END   2023/02/08 T.Kuroda [QC#60966, ADD]
        // CONFIG_TP_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.configTpCd, origLineTMsg.configTpCd);
        // SVC_CONFIG_MSTR_PK
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.svcConfigMstrPk, origLineTMsg.svcConfigMstrPk);
        // MDSE_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.mdseCd, dtlInfo.mdseCd);
        // PO_MDSE_CMPSN_TP_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.poMdseCmpsnTpCd, origLineTMsg.poMdseCmpsnTpCd);
        // ORIG_RQST_MDSE_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.origRqstMdseCd, origLineTMsg.origRqstMdseCd);
        // ASL_MDSE_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.aslMdseCd, origLineTMsg.aslMdseCd);
        // PRCH_REQ_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqQty, ZYPCommonFunc.getBigDecimal(dtlInfo.prchReqQty, "0"));
        // PRCH_REQ_DISP_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqDispQty, ZYPCommonFunc.getBigDecimal(dtlInfo.prchReqQty, "0"));
        // PRCH_REQ_DSPL_UOM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqDsplUomCd, origLineTMsg.prchReqDsplUomCd);
        // PRCH_REQ_REL_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelQty, BigDecimal.ZERO);
        // QC#17049 2017/01/16 CITS T.Tokutomi START
        // PRCH_REQ_BAL_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqBalQty, BigDecimal.ZERO);
        // QC#17049 2017/01/16 CITS T.Tokutomi END
        // PRCH_REQ_INSRC_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqInsrcQty, BigDecimal.ZERO);
        // PRCH_REQ_CANC_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCancQty, BigDecimal.ZERO);
        // ORD_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.ordQty, BigDecimal.ZERO);
        // ROP_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.ropQty, BigDecimal.ZERO);
        // MAX_INVTY_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.maxInvtyQty, BigDecimal.ZERO);
        // MIN_ORD_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.minOrdQty, BigDecimal.ZERO);
        // INCR_ORD_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.incrOrdQty, BigDecimal.ZERO);
        // CUR_INVTY_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.curInvtyQty, BigDecimal.ZERO);
        // CUR_INVTY_AVAL_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.curInvtyAvalQty, BigDecimal.ZERO);
        // SCHD_INBD_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.schdInbdQty, BigDecimal.ZERO);
        // SCHD_OTBD_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.schdOtbdQty, BigDecimal.ZERO);
        // PRO_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.proNum, dtlInfo.proNum);
        // SHIP_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shipQty, BigDecimal.ZERO);
        // RWS_PUT_AWAY_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rwsPutAwayQty, BigDecimal.ZERO);
        // BACK_TO_TECH_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.backToTechQty, BigDecimal.ZERO);
        // THIS_MTH_FOB_COST_AMT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.thisMthFobCostAmt, BigDecimal.ZERO);
        // ASL_UNIT_PRC_AMT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.aslUnitPrcAmt, BigDecimal.ZERO);
        // ENT_DEAL_NET_UNIT_PRC_AMT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entDealNetUnitPrcAmt, BigDecimal.ZERO);
        // ENT_PO_DTL_DEAL_NET_AMT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entPoDtlDealNetAmt, BigDecimal.ZERO);
        // ENT_FUNC_NET_UNIT_PRC_AMT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entFuncNetUnitPrcAmt, BigDecimal.ZERO);
        // ENT_PO_DTL_FUNC_NET_AMT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entPoDtlFuncNetAmt, BigDecimal.ZERO);

        // FROM_STK_STS_CD
        if (!ZYPCommonFunc.hasValue(origLineTMsg.fromStkStsCd)) {
            getFromSSFromPrchReqLineTp(dtlInfo.prchReqLineTpCd.getValue(), dtlInfo);
        } else {
            ZYPEZDItemValueSetter.setValue(dtlInfo.fromStkStsCd, origLineTMsg.fromStkStsCd);
        }
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.fromStkStsCd, dtlInfo.fromStkStsCd);
        // TO_STK_STS_CD
        if (!ZYPCommonFunc.hasValue(origLineTMsg.toStkStsCd)) {
            getToSSFromPrchReqLineTp(dtlInfo.prchReqLineTpCd.getValue(), dtlInfo);
        } else {
            ZYPEZDItemValueSetter.setValue(dtlInfo.toStkStsCd, origLineTMsg.toStkStsCd);
        }
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.toStkStsCd, dtlInfo.toStkStsCd);

        // FRT_COND_CD
        if (ZYPCommonFunc.hasValue(dtlInfo.frtCondCd)) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.frtCondCd, dtlInfo.frtCondCd);
        } else {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.frtCondCd, origLineTMsg.frtCondCd);
        }
        // SHPG_SVC_LVL_CD
        if (!ZYPCommonFunc.hasValue(dtlInfo.shpgSvcLvlCd)) {
            ZYPEZDItemValueSetter.setValue(dtlInfo.shpgSvcLvlCd, origLineTMsg.shpgSvcLvlCd);
        }
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shpgSvcLvlCd, dtlInfo.shpgSvcLvlCd);
        // CARR_CD
        if (ZYPCommonFunc.hasValue(dtlInfo.carrCd)) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.carrCd, dtlInfo.carrCd);
        } else {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.carrCd, origLineTMsg.carrCd);
        }

        // QC#2366 Add.
        if(! checkShpgSvcCarrReln(prchReqDtlTMsg.shpgSvcLvlCd.getValue(), prchReqDtlTMsg.carrCd.getValue())){
            prchReqDtlTMsg.carrCd.clear();
        }

        // PRCH_REQ_LINE_CMNT_TXT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineCmntTxt, dtlInfo.prchReqLineCmntTxt);
        // COA_CMPY_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaCmpyCd, origLineTMsg.coaCmpyCd);
        // COA_AFFL_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaAfflCd, origLineTMsg.coaAfflCd);
        // COA_BR_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaBrCd, origLineTMsg.coaBrCd);
        // COA_CH_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaChCd, origLineTMsg.coaChCd);
        // COA_CC_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaCcCd, origLineTMsg.coaCcCd);
        // COA_ACCT_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaAcctCd, origLineTMsg.coaAcctCd);
        // COA_PROD_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaProdCd, origLineTMsg.coaProdCd);
        // COA_PROJ_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaProjCd, origLineTMsg.coaProjCd);
        // COA_EXTN_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaExtnCd, origLineTMsg.coaExtnCd);
        // BO_EML_PROC_STS_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.boEmlProcStsCd, dtlInfo.boEmlProcStsCd);
        // PRCH_REQ_FRZ_FLG
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqFrzFlg, ZYPConstant.FLG_OFF_N);
        // INSRC_FLG
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.insrcFlg, ZYPConstant.FLG_OFF_N);
        // PO_CRAT_FLG
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.poCratFlg, ZYPConstant.FLG_OFF_N);

        return prchReqDtlTMsg;
    }

    /**
     * get PRCH_REQ_DTL TMsg List for Insert
     * @param pMsg NPZC103001PMsg
     * @return List<PRCH_REQ_DTLTMsg>
     */
    private List<PRCH_REQ_DTLTMsg> getDtlTMsgListForInsert(NPZC103001PMsg pMsg) {

        List<PRCH_REQ_DTLTMsg> prchReqDtlList = new ArrayList<PRCH_REQ_DTLTMsg>();
        for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
            prchReqDtlList.add(getDtlTMsgForInsert(pMsg, i));
        }
        return prchReqDtlList;
    }

    /**
     * get PRCH_REQ_DTL TMsg List for Insert
     * @param pMsg NPZC103001PMsg
     * @param idx int
     * @return PRCH_REQ_DTLTMsg
     */
    private PRCH_REQ_DTLTMsg getDtlTMsgForInsert(NPZC103001PMsg pMsg, int idx) {

        PRCH_REQ_DTLTMsg prchReqDtlTMsg = new PRCH_REQ_DTLTMsg();
        NPZC103001_prchReqInfoPMsg dtlInfo = pMsg.prchReqInfo.no(idx);
        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        // PRCH_REQ_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqNum, pMsg.prchReqNum);
        // PRCH_REQ_LINE_NUM
        if (ZYPCommonFunc.hasValue(dtlInfo.prchReqLineNum)) {
            prchReqDtlTMsg.prchReqLineNum.setValue(dtlInfo.prchReqLineNum.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineNum, getNextPrchReqLineNum(pMsg));
            // Out Parameter
            dtlInfo.prchReqLineNum.setValue(prchReqDtlTMsg.prchReqLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(dtlInfo.prchReqLineNum, prchReqDtlTMsg.prchReqLineNum.getValue());
        }
        this.prchReqLineNum = prchReqDtlTMsg.prchReqLineNum.getValue();
        // PRCH_REQ_LINE_SUB_NUM
        if (!ZYPCommonFunc.hasValue(dtlInfo.prchReqLineSubNum)) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineSubNum, BigDecimal.ZERO);
        } else {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineSubNum, dtlInfo.prchReqLineSubNum);
        }
        // Out Parameter
        ZYPEZDItemValueSetter.setValue(dtlInfo.prchReqLineSubNum, prchReqDtlTMsg.prchReqLineSubNum);

        // PRCH_REQ_INTFC_PK
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqIntfcPk, dtlInfo.prchReqIntfcPk);
        // ORIG_PRCH_REQ_LINE_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.origPrchReqLineNum, dtlInfo.origPrchReqLineNum);
        // ORIG_PRCH_REQ_LINE_SUB_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.origPrchReqLineSubNum, dtlInfo.origPrchReqLineSubNum);
        // PRCH_REQ_LINE_TP_CD
        if (!ZYPCommonFunc.hasValue(dtlInfo.prchReqLineTpCd)) {
            getPrchReqLineTpCd(pMsg, dtlInfo);
        }
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineTpCd, dtlInfo.prchReqLineTpCd);
        // PRCH_REQ_CRAT_DT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCratDt, pMsg.procDt);
        // PRCH_REQ_CRAT_TM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCratTm, ZYPDateUtil.getCurrentSystemTime(NPZC103001Constant.TIME_PATTERN));
        // PRCH_REQ_LINE_STS_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.OPEN);
        // TRX_REF_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.trxRefNum, dtlInfo.trxRefNum);
        // TRX_REF_LINE_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.trxRefLineNum, dtlInfo.trxRefLineNum);
        // TRX_REF_LINE_SUB_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.trxRefLineSubNum, dtlInfo.trxRefLineSubNum);
        // SHPG_PLN_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shpgPlnNum, dtlInfo.shpgPlnNum);
        // SHIP_FROM_SO_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shipFromSoNum, dtlInfo.shipFromSoNum);
        // PRCH_REQ_REL_STS_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.IN_COMPLETED);
        // REL_RQST_TO_PO_ORD_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.relRqstToPoOrdNum, dtlInfo.relRqstToPoOrdNum);
        // PO_ORD_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.poOrdNum, dtlInfo.poOrdNum);
        // PO_ORD_DTL_LINE_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.poOrdDtlLineNum, dtlInfo.poOrdDtlLineNum);
        // PO_SCHD_REL_DT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.poSchdRelDt, dtlInfo.poSchdRelDt);

        // Procurement Source
        Map<String, String> procurementSource = null;
        if (!ZYPCommonFunc.hasValue(dtlInfo.procrTpCd) || !ZYPCommonFunc.hasValue(dtlInfo.srcInvtyLocCd)) {
            procurementSource = getProcurementSource(pMsg, dtlInfo);
        }
        // PROCR_TP
        if (!ZYPCommonFunc.hasValue(dtlInfo.procrTpCd) && procurementSource != null) {
            ZYPEZDItemValueSetter.setValue(dtlInfo.procrTpCd, procurementSource.get(NPZC103001Constant.COL_PROCR_TP_CD));
        }
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.procrTpCd, dtlInfo.procrTpCd);
        // SRC_INVTY_LOC_CD
        if (ZYPCommonFunc.hasValue(dtlInfo.srcInvtyLocCd)) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcInvtyLocCd, dtlInfo.srcInvtyLocCd);
            // get SRC_RTL_WH_CD/SRC_RTL_SWH_CD
            Map<String, String> locDat = getWhAndSwhByInvtyLocCd(dtlInfo.srcInvtyLocCd.getValue());

            if (locDat != null) {
                // SRC_RTL_WH_CD
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcRtlWhCd, locDat.get(NPZC103001Constant.RTL_WH_CD));
                // SRC_RTL_SWH_CD
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcRtlSwhCd, locDat.get(NPZC103001Constant.RTL_SWH_CD));
            } else {
                // SRC_RTL_WH_CD
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcRtlWhCd, dtlInfo.srcInvtyLocCd);
                // SRC_RTL_SWH_CD
                prchReqDtlTMsg.srcRtlSwhCd.clear();
            }
        } else if (procurementSource != null) {
            ZYPEZDItemValueSetter.setValue(dtlInfo.srcInvtyLocCd, procurementSource.get(NPZC103001Constant.COL_SRC_LOC_CD));
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcInvtyLocCd, dtlInfo.srcInvtyLocCd);
            // SRC_RTL_WH_CD
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcRtlWhCd, procurementSource.get(NPZC103001Constant.COL_SRC_RTL_WH_CD));
            // SRC_RTL_SWH_CD
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcRtlSwhCd, procurementSource.get(NPZC103001Constant.COL_SRC_RTL_SWH_CD));
        }
        // ORIG_RQST_MDSE_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.origRqstMdseCd, dtlInfo.mdseCd);
        // Supersede

        // QC#22747
        boolean isSupersede = false;

        // QC#28939 add start
        boolean isTextItem = false;

        if (PRCH_REQ_REC_TP.PO_REQUISITION.equals(pMsg.prchReqRecTpCd.getValue())) {

            // QC#27521
            String reqMdseCd = dtlInfo.mdseCd.getValue();

            // QC#28939 add start
            // check text
            isTextItem = textItemCheck(reqMdseCd);

            if (isTextItem) {

                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.mdseDescShortTxt, dtlInfo.mdseDescShortTxt);
                // QC#30135
            } else if (!PRCH_REQ_SRC_TP.SALES_ORDER.equals(pMsg.prchReqSrcTpCd.getValue())) {

                String supdMdseCd = getSupersedeItem(pMsg, dtlInfo.mdseCd.getValue(), dtlInfo.vndCd.getValue());

                if (ZYPCommonFunc.hasValue(supdMdseCd)) {

                    // MDSE_CD
                    ZYPEZDItemValueSetter.setValue(dtlInfo.mdseCd, supdMdseCd);

                    // QC#22747
                    isSupersede = true;

                    // Update Shipping Plan Mdse
                    // ********************************************************************************

                    if (ZYPCommonFunc.hasValue(dtlInfo.shpgPlnNum.getValue())) {

                        updShpgPlnMdse(dtlInfo.shpgPlnNum.getValue(), supdMdseCd);
                    }

                    // QC#27521
                    if (ZYPCommonFunc.hasValue(reqMdseCd) //
                            && ZYPCommonFunc.hasValue(supdMdseCd) //
                            && !reqMdseCd.equals(supdMdseCd)) {

                        dtlInfo.prntVndCd.clear();
                        dtlInfo.vndCd.clear();
                    }
                }
                // QC#53271 Add Start
                if (ZYPCommonFunc.hasValue(dtlInfo.mdseDescShortTxt) // 
                        && (PRCH_REQ_LINE_TP.EXPENSE.equals(dtlInfo.prchReqLineTpCd.getValue()) || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(dtlInfo.prchReqLineTpCd.getValue()))) {

                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.mdseDescShortTxt, dtlInfo.mdseDescShortTxt);
                }
                // QC#53271 Add End
            }

            // QC#28939 add end
        }

        // MDSE_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.mdseCd, dtlInfo.mdseCd);
        // PO_MDSE_CMPSN_TP_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.poMdseCmpsnTpCd, dtlInfo.poMdseCmpsnTpCd);

        // Supplier
        Map<String, Object> primaryVendor = null;
        if (ZYPCommonFunc.hasValue(dtlInfo.procrTpCd) && PROCR_TP.SUPPLIER.equals(dtlInfo.procrTpCd.getValue()) && (!ZYPCommonFunc.hasValue(dtlInfo.prntVndCd) || !ZYPCommonFunc.hasValue(dtlInfo.vndCd))) {
            // get Primary Vendor API
            primaryVendor = getPrimaryVendor(pMsg, dtlInfo);
            // Supplier
            if (!ZYPCommonFunc.hasValue(dtlInfo.prntVndCd)) {
                ZYPEZDItemValueSetter.setValue(dtlInfo.prntVndCd, (String) primaryVendor.get(NPZC103001Constant.COL_PRNT_VND_CD));
            }
            // Supplier Site
            if (!ZYPCommonFunc.hasValue(dtlInfo.vndCd)) {
                ZYPEZDItemValueSetter.setValue(dtlInfo.vndCd, (String) primaryVendor.get(NPZC103001Constant.COL_VND_CD));
            }
            if (!ZYPCommonFunc.hasValue(dtlInfo.prntVndCd) || !ZYPCommonFunc.hasValue(dtlInfo.vndCd)) {
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.ENTERED);
                updateApprovalStatus(pMsg);
            }
        }
        // ASL_MDSE_CD
        // QC#22747
        if (isSupersede) {
            // QC#53271 Add Start
            if (ZYPCommonFunc.hasValue(dtlInfo.aslMdseCd) // 
                    && (PRCH_REQ_LINE_TP.EXPENSE.equals(dtlInfo.prchReqLineTpCd.getValue()) || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(dtlInfo.prchReqLineTpCd.getValue()))) {

                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.aslMdseCd, dtlInfo.aslMdseCd);
            // QC#53271 Add End
            } else {
                Map<String, Object> aslInfo = getAslInfo(pMsg.glblCmpyCd.getValue(), dtlInfo.vndCd.getValue(), dtlInfo.mdseCd.getValue()) ;
                if (aslInfo != null) {
                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.aslMdseCd, (String) aslInfo.get("SPLY_ITEM_NUM"));
                }
            }

        } else {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.aslMdseCd, dtlInfo.aslMdseCd);
        }
        // PRNT_VND_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.vndCd, dtlInfo.vndCd);
        // VND_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prntVndCd, dtlInfo.prntVndCd);

        if (PRCH_REQ_REC_TP.PO_REQUISITION.equals(pMsg.prchReqRecTpCd.getValue())) {
            // ASL_DTL_PK
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.aslDtlPk, dtlInfo.aslDtlPk);
            // ASL_UNIT_PRC_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.aslUnitPrcAmt, ZYPCommonFunc.getBigDecimal(dtlInfo.aslUnitPrcAmt, "0"));
            // ENT_DEAL_NET_UNIT_PRC_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entDealNetUnitPrcAmt, ZYPCommonFunc.getBigDecimal(dtlInfo.entDealNetUnitPrcAmt, "0"));
            // ENT_PO_DTL_DEAL_NET_AMT
            BigDecimal dealAmt = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(dtlInfo.entDealNetUnitPrcAmt)) {
                dealAmt = dtlInfo.entDealNetUnitPrcAmt.getValue().multiply(ZYPCommonFunc.getBigDecimal(dtlInfo.prchReqDispQty, "0"));
            }
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entPoDtlDealNetAmt, dealAmt);
            // THIS_MTH_FOB_COST_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.thisMthFobCostAmt, ZYPCommonFunc.getBigDecimal(dtlInfo.entFuncNetUnitPrcAmt, "0"));
            // ENT_FUNC_NET_UNIT_PRC_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entFuncNetUnitPrcAmt, ZYPCommonFunc.getBigDecimal(dtlInfo.entFuncNetUnitPrcAmt, "0"));
            // ENT_PO_DTL_FUNC_NET_AMT
            if (BigDecimal.ZERO.compareTo(dealAmt) == 0) {
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entPoDtlFuncNetAmt, BigDecimal.ZERO);
            } else {
                NPXC001001CurrencyConversion currCon = new NPXC001001CurrencyConversion();
                NPXC001001CurrencyConversionBean currConBean = currCon.convertCurrency(this.glblCmpyCd, dtlInfo.ccyCd.getValue(), dealAmt, pMsg.procDt.getValue(), null);
                if (NFCConst.CST_RTN_CD_ERR.equals(currConBean.getRtrnCd())) {
                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entPoDtlFuncNetAmt, BigDecimal.ZERO);
                } else {
                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entPoDtlFuncNetAmt, currConBean.getFuncAmt());
                }
            }
            // CCY_CD
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.ccyCd, dtlInfo.ccyCd);
            // EXCH_RATE
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.exchRate, dtlInfo.exchRate);
        // START 2018/05/25 S.Katsuma [QC#25893,ADD]
        } else if (PRCH_REQ_REC_TP.INVENTORY_REQUEST.equals(pMsg.prchReqRecTpCd.getValue())
                && PRCH_REQ_TP.VENDOR_RETURN.equals(pMsg.prchReqTpCd.getValue())) {
            // THIS_MTH_FOB_COST_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.thisMthFobCostAmt, BigDecimal.ZERO);
            // ASL_UNIT_PRC_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.aslUnitPrcAmt, BigDecimal.ZERO);
            // ENT_DEAL_NET_UNIT_PRC_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entDealNetUnitPrcAmt, ZYPCommonFunc.getBigDecimal(dtlInfo.entDealNetUnitPrcAmt, "0"));
            // ENT_PO_DTL_DEAL_NET_AMT
            BigDecimal dealAmt = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(dtlInfo.entDealNetUnitPrcAmt)) {
                dealAmt = dtlInfo.entDealNetUnitPrcAmt.getValue().multiply(ZYPCommonFunc.getBigDecimal(dtlInfo.prchReqDispQty, "0"));
            }
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entPoDtlDealNetAmt, dealAmt);
            // ENT_FUNC_NET_UNIT_PRC_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entFuncNetUnitPrcAmt, BigDecimal.ZERO);
            // ENT_PO_DTL_FUNC_NET_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entPoDtlFuncNetAmt, BigDecimal.ZERO);
        // END 2018/05/25 S.Katsuma [QC#25893,ADD]
        } else {
            // THIS_MTH_FOB_COST_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.thisMthFobCostAmt, BigDecimal.ZERO);
            // ASL_UNIT_PRC_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.aslUnitPrcAmt, BigDecimal.ZERO);
            // ENT_DEAL_NET_UNIT_PRC_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entDealNetUnitPrcAmt, BigDecimal.ZERO);
            // ENT_PO_DTL_DEAL_NET_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entPoDtlDealNetAmt, BigDecimal.ZERO);
            // ENT_FUNC_NET_UNIT_PRC_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entFuncNetUnitPrcAmt, BigDecimal.ZERO);
            // ENT_PO_DTL_FUNC_NET_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entPoDtlFuncNetAmt, BigDecimal.ZERO);
        }
        // VND_INVTY_LOC_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.vndInvtyLocCd, dtlInfo.vndInvtyLocCd);
        // VND_RTRN_RSN_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.vndRtrnRsnCd, dtlInfo.vndRtrnRsnCd);
        // DEST_INVTY_LOC_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.destInvtyLocCd, dtlInfo.destInvtyLocCd);
        Map<String, String> locDat = getWhAndSwhByInvtyLocCd(dtlInfo.destInvtyLocCd.getValue());
        if (locDat != null) {
            // DEST_RTL_WH_CD
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.destRtlWhCd, locDat.get(NPZC103001Constant.RTL_WH_CD));
            // DEST_RTL_SWH_CD
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.destRtlSwhCd, locDat.get(NPZC103001Constant.RTL_SWH_CD));
        } else {
            // DEST_RTL_WH_CD
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.destRtlWhCd, dtlInfo.destInvtyLocCd);
            // DEST_RTL_SWH_CD
            prchReqDtlTMsg.destRtlSwhCd.clear();
        }
        // RQST_RCV_DT
        if (!ZYPCommonFunc.hasValue(dtlInfo.rqstRcvDt)) {
            // Calculate RDD for PR API
            ZYPEZDItemValueSetter.setValue(dtlInfo.rqstRcvDt, calcRddForPr(pMsg, idx));
        }
        if (ZYPCommonFunc.hasValue(dtlInfo.rqstRcvTm)) {
            // QC#55710
            if (PRCH_REQ_TP.TECH_RETURN.equals(pMsg.prchReqTpCd.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(pMsg.prchReqTpCd.getValue())) {
                setRqstRcvDtTm(pMsg, idx, prchReqDtlTMsg.srcRtlWhCd.getValue());
            } else {
                setRqstRcvDtTm(pMsg, idx, prchReqDtlTMsg.destRtlWhCd.getValue());
            }
        }
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rqstRcvDt, dtlInfo.rqstRcvDt);
        // RQST_RCV_TM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rqstRcvTm, dtlInfo.rqstRcvTm);
        // START 2023/02/14 T.Kuroda [QC#60966, ADD]
        // RQST_SHIP_DT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rqstShipDt, dtlInfo.rqstShipDt);
        // END   2023/02/14 T.Kuroda [QC#60966, ADD]
        // CONFIG_TP_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.configTpCd, dtlInfo.configTpCd);
        // SVC_CONFIG_MSTR_PK
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.svcConfigMstrPk, dtlInfo.svcConfigMstrPk);
        // PRCH_REQ_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqQty, ZYPCommonFunc.getBigDecimal(dtlInfo.prchReqQty, "0"));
        // ORD_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.ordQty, ZYPCommonFunc.getBigDecimal(dtlInfo.ordQty, "0"));
        // CUST_UOM_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.custUomCd, dtlInfo.custUomCd);
        // PRCH_REQ_DISP_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqDispQty, ZYPCommonFunc.getBigDecimal(dtlInfo.prchReqDispQty, "0"));
        // PRCH_REQ_DSPL_UOM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqDsplUomCd, dtlInfo.prchReqDsplUomCd);
        // PRCH_REQ_REL_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelQty, ZYPCommonFunc.getBigDecimal(dtlInfo.prchReqRelQty, "0"));
        // PRCH_REQ_BAL_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqBalQty, ZYPCommonFunc.getBigDecimal(dtlInfo.prchReqQty, "0"));
        // PRCH_REQ_INSRC_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqInsrcQty, BigDecimal.ZERO);
        // PRCH_REQ_CANC_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCancQty, BigDecimal.ZERO);
        // ROP_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.ropQty, ZYPCommonFunc.getBigDecimal(dtlInfo.ropQty, "0"));
        // MAX_INVTY_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.maxInvtyQty, ZYPCommonFunc.getBigDecimal(dtlInfo.maxInvtyQty, "0"));
        // MIN_ORD_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.minOrdQty, ZYPCommonFunc.getBigDecimal(dtlInfo.minOrdQty, "0"));
        // INCR_ORD_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.incrOrdQty, ZYPCommonFunc.getBigDecimal(dtlInfo.incrOrdQty, "0"));
        // CUR_INVTY_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.curInvtyQty, ZYPCommonFunc.getBigDecimal(dtlInfo.curInvtyQty, "0"));
        // CUR_INVTY_AVAL_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.curInvtyAvalQty, ZYPCommonFunc.getBigDecimal(dtlInfo.curInvtyAvalQty, "0"));
        // SCHD_INBD_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.schdInbdQty, ZYPCommonFunc.getBigDecimal(dtlInfo.schdInbdQty, "0"));
        // SCHD_OTBD_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.schdOtbdQty, ZYPCommonFunc.getBigDecimal(dtlInfo.schdOtbdQty, "0"));
        // SHIP_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shipQty, BigDecimal.ZERO);
        // RWS_PUT_AWAY_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rwsPutAwayQty, BigDecimal.ZERO);
        // BACK_TO_TECH_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.backToTechQty, BigDecimal.ZERO);

        // PRO_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.proNum, dtlInfo.proNum);
        // FROM_STK_STS_CD
        if (!ZYPCommonFunc.hasValue(dtlInfo.fromStkStsCd)) {
            getFromSSFromPrchReqLineTp(dtlInfo.prchReqLineTpCd.getValue(), dtlInfo);
        }
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.fromStkStsCd, dtlInfo.fromStkStsCd);
        // TO_STK_STS_CD
        if (!ZYPCommonFunc.hasValue(dtlInfo.toStkStsCd)) {
            getToSSFromPrchReqLineTp(dtlInfo.prchReqLineTpCd.getValue(), dtlInfo);
        }
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.toStkStsCd, dtlInfo.toStkStsCd);
        // FRT_CHRG_TO_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.frtChrgToCd, dtlInfo.frtChrgToCd);
        // FRT_CHRG_METH_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.frtChrgMethCd, dtlInfo.frtChrgMethCd);
        // FRT_COND_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.frtCondCd, dtlInfo.frtCondCd);
        // SHPG_SVC_LVL_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shpgSvcLvlCd, dtlInfo.shpgSvcLvlCd);
        // CARR_CD
        // QC#28675
        if (!ZYPCommonFunc.hasValue(dtlInfo.carrCd) && !PRCH_REQ_SRC_TP.SALES_ORDER.equals(pMsg.prchReqSrcTpCd.getValue())) {
            String carrCd = null;
            // START 2018/12/17 M.Naito [QC#29599,MOD]
//            if (ZYPCommonFunc.hasValue(pMsg.shipToCustCd)) {
//                ZYPEZDItemValueSetter.setValue(dtlInfo.carrCd, getCarrCdByCustomer(pMsg.shipToCustCd.getValue()));
//            } else {
//                ZYPEZDItemValueSetter.setValue(dtlInfo.carrCd, getCarrCdByWh(prchReqDtlTMsg.srcRtlWhCd.getValue()));
//            }
            if (!ZYPCommonFunc.hasValue(pMsg.shipToCustCd)) {
                ZYPEZDItemValueSetter.setValue(dtlInfo.carrCd, getCarrCdByWh(prchReqDtlTMsg.srcRtlWhCd.getValue()));
            }
            // END 2018/12/17 M.Naito [QC#29599,MOD]
        }
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.carrCd, dtlInfo.carrCd);
        // START 2021/05/21 J.Evangelista [QC#58782,ADD]
        // Set carrCd to null when combination with shpgSvcLvlCd does not exist in SHPG_SVC_LVL_CARR_RELN table
        if (ZYPCommonFunc.hasValue(prchReqDtlTMsg.shpgSvcLvlCd) && ZYPCommonFunc.hasValue(prchReqDtlTMsg.carrCd)
                && !checkShpgSvcCarrReln(prchReqDtlTMsg.shpgSvcLvlCd.getValue(), prchReqDtlTMsg.carrCd.getValue())) {
            prchReqDtlTMsg.carrCd.clear();
        }
        // END 2021/05/21 J.Evangelista [QC#58782,ADD]
        // CARR_ACCT_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.carrAcctNum, dtlInfo.carrAcctNum);
        // Vendor Payment
        Map<String, String> vndPmt = getVendorPayment(dtlInfo);
        if (vndPmt != null) {
            // VND_PMT_TERM_CD
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.vndPmtTermCd, vndPmt.get(NPZC103001Constant.VND_PMT_TERM_CD));
            // VND_PMT_TERM_DESC_TXT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.vndPmtTermDescTxt, vndPmt.get(NPZC103001Constant.VND_PMT_TERM_DESC_TXT));
        }
        // PRCH_REQ_LINE_CMNT_TXT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineCmntTxt, dtlInfo.prchReqLineCmntTxt);
        // COA_CMPY_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaCmpyCd, dtlInfo.coaCmpyCd);
        // COA_AFFL_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaAfflCd, dtlInfo.coaAfflCd);
        // COA_BR_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaBrCd, dtlInfo.coaBrCd);
        // COA_CH_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaChCd, dtlInfo.coaChCd);
        // COA_CC_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaCcCd, dtlInfo.coaCcCd);
        // COA_ACCT_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaAcctCd, dtlInfo.coaAcctCd);
        // COA_PROD_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaProdCd, dtlInfo.coaProdCd);
        // COA_PROJ_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaProjCd, dtlInfo.coaProjCd);
        // COA_EXTN_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaExtnCd, dtlInfo.coaExtnCd);
        // TECH_RQST_NTC_ML_PROC_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.techRqstNtcMlProcCd, NPZC103001Constant.TECH_NTC_ML_PROC_EXCLUDED);
        // TECH_RTN_NTC_ML_PROC_CD
        if (PRCH_REQ_REC_TP.TECH_RETURN.equals(pMsg.prchReqRecTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.techRtnNtcMlProcCd, ZYPConstant.FLG_OFF_0);
        } else {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.techRtnNtcMlProcCd, NPZC103001Constant.TECH_NTC_ML_PROC_EXCLUDED);
        }
        // PRCH_REQ_FRZ_FLG
        // QC#26662 Update
        if(ZYPCommonFunc.hasValue(dtlInfo.prchReqFrzFlg)){
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqFrzFlg, dtlInfo.prchReqFrzFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqFrzFlg, ZYPConstant.FLG_OFF_N);
        }

        // INSRC_FLG
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.insrcFlg, ZYPConstant.FLG_OFF_N);
        // PO_CRAT_FLG
        if (ZYPCommonFunc.hasValue(dtlInfo.procrTpCd) && PROCR_TP.SUPPLIER.equals(dtlInfo.procrTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.poCratFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.poCratFlg, ZYPConstant.FLG_OFF_N);
        }
        // QC#21209
        // SPCL_INSTN_CMNT_TXT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.spclInstnCmntTxt, dtlInfo.spclInstnCmntTxt);

        return prchReqDtlTMsg;
    }

    /**
     * getDtlTMsgForTpUpdate
     * @param origDtlTMsg PRCH_REQ_DTLTMsg
     * @param pMsg NPZC103001PMsg
     * @param idx int
     * @return PRCH_REQ_DTLTMsg
     */
    private PRCH_REQ_DTLTMsg getDtlTMsgForTpUpdate(PRCH_REQ_DTLTMsg origDtlTMsg, NPZC103001PMsg pMsg, int idx) {

        PRCH_REQ_DTLTMsg prchReqDtlTMsg = new PRCH_REQ_DTLTMsg();
        NPZC103001_prchReqInfoPMsg dtlInfo = pMsg.prchReqInfo.no(idx);
        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        // PRCH_REQ_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqNum, pMsg.prchReqNum);
        // Current PR Number
        this.currPrchReqNum = pMsg.prchReqNum.getValue();
        // PRCH_REQ_LINE_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineNum, origDtlTMsg.prchReqLineNum);
        // Out Parameter
        dtlInfo.prchReqLineNum.setValue(prchReqDtlTMsg.prchReqLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(dtlInfo.prchReqLineNum, prchReqDtlTMsg.prchReqLineNum.getValue());
        this.prchReqLineNum = prchReqDtlTMsg.prchReqLineNum.getValue();
        // PRCH_REQ_LINE_SUB_NUM
        BigDecimal maxSubNum = BigDecimal.ONE.add(getMaxPrchReqLineSubNum(pMsg.prchReqNum.getValue(), dtlInfo.prchReqLineNum.getValue()));
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineSubNum, maxSubNum);
        // Out Parameter
        ZYPEZDItemValueSetter.setValue(dtlInfo.prchReqLineSubNum, prchReqDtlTMsg.prchReqLineSubNum);
        // ORIG_PRCH_REQ_LINE_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.origPrchReqLineNum, origDtlTMsg.prchReqLineNum);
        // ORIG_PRCH_REQ_LINE_SUB_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.origPrchReqLineSubNum, origDtlTMsg.prchReqLineSubNum);
        // PRCH_REQ_LINE_TP_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineTpCd, dtlInfo.prchReqLineTpCd);
        // PRCH_REQ_CRAT_DT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCratDt, pMsg.procDt);
        // PRCH_REQ_CRAT_TM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCratTm, ZYPDateUtil.getCurrentSystemTime(NPZC103001Constant.TIME_PATTERN));
        // PRCH_REQ_LINE_STS_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.AWAITING_SHIPPING);
        // TRX_REF_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.trxRefNum, dtlInfo.trxRefNum);
        // TRX_REF_LINE_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.trxRefLineNum, origDtlTMsg.trxRefLineNum);
        // TRX_REF_LINE_SUB_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.trxRefLineSubNum, origDtlTMsg.trxRefLineSubNum);
        // PRCH_REQ_REL_STS
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.IN_COMPLETED);
        // PROCR_TP_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.procrTpCd, PROCR_TP.WAREHOUSE);
        if (ZYPCommonFunc.hasValue(dtlInfo.srcInvtyLocCd)) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcInvtyLocCd, dtlInfo.srcInvtyLocCd);
            // get SRC_RTL_WH_CD/SRC_RTL_SWH_CD
            Map<String, String> locDat = getWhAndSwhByInvtyLocCd(dtlInfo.srcInvtyLocCd.getValue());

            if (locDat != null) {
                // SRC_RTL_WH_CD
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcRtlWhCd, locDat.get(NPZC103001Constant.RTL_WH_CD));
                // SRC_RTL_SWH_CD
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcRtlSwhCd, locDat.get(NPZC103001Constant.RTL_SWH_CD));
            }
        }
        // DEST_INVTY_LOC_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.destInvtyLocCd, origDtlTMsg.destInvtyLocCd);
        // DEST_RTL_WH_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.destRtlWhCd, origDtlTMsg.destRtlWhCd);
        // DEST_RTL_SWH_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.destRtlSwhCd, origDtlTMsg.destRtlSwhCd);
        // QC#20379 09/14/2017 Update Start.
        if (ZYPCommonFunc.hasValue(dtlInfo.rqstRcvDt)) {
            // RQST_RCV_DT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rqstRcvDt, dtlInfo.rqstRcvDt);
        }
        if (ZYPCommonFunc.hasValue(dtlInfo.rqstRcvTm)) {
            // RQST_RCV_TM
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rqstRcvTm, dtlInfo.rqstRcvTm);
        }
        if (ZYPCommonFunc.hasValue(dtlInfo.proNum)) {
            // PRO_NUM
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.proNum, dtlInfo.proNum);
        }
        // QC#20379 09/14/2017 Update End.
        // START 2023/02/08 T.Kuroda [QC#60966, ADD]
        if (ZYPCommonFunc.hasValue(dtlInfo.rqstShipDt)) {
            // RQST_SHIP_DT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rqstShipDt, dtlInfo.rqstShipDt);
        }
        // END   2023/02/08 T.Kuroda [QC#60966, ADD]
        // MDSE_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.mdseCd, dtlInfo.mdseCd);
        // ORIG_RQST_MDSE_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.origRqstMdseCd, origDtlTMsg.mdseCd);
        // PRCH_REQ_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqQty, dtlInfo.prchReqQty);
        // ORD_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.ordQty, BigDecimal.ZERO);
        // PRCH_REQ_DISP_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqDispQty, dtlInfo.prchReqQty);
        // PRCH_REQ_REL_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelQty, BigDecimal.ZERO);
        // PRCH_REQ_BAL_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqBalQty, dtlInfo.prchReqQty);
        // PRCH_REQ_INSRC_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqInsrcQty, BigDecimal.ZERO);
        // PRCH_REQ_CANC_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCancQty, BigDecimal.ZERO);
        // ROP_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.ropQty, BigDecimal.ZERO);
        // MAX_INVTY_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.maxInvtyQty, BigDecimal.ZERO);
        // MIN_ORD_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.minOrdQty, BigDecimal.ZERO);
        // INCR_ORD_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.incrOrdQty, BigDecimal.ZERO);
        // CUR_INVTY_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.curInvtyQty, BigDecimal.ZERO);
        // CUR_INVTY_AVAL_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.curInvtyAvalQty, BigDecimal.ZERO);
        // SCHD_INBD_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.schdInbdQty, BigDecimal.ZERO);
        // SCHD_OTBD_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.schdOtbdQty, BigDecimal.ZERO);
        // SHIP_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shipQty, BigDecimal.ZERO);
        // RWS_PUT_AWAY_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rwsPutAwayQty, BigDecimal.ZERO);
        // BACK_TO_TECH_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.backToTechQty, BigDecimal.ZERO);
        // FROM_STK_STS_CD
        if (!ZYPCommonFunc.hasValue(dtlInfo.fromStkStsCd)) {
            getFromSSFromPrchReqLineTp(dtlInfo.prchReqLineTpCd.getValue(), dtlInfo);
        }
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.fromStkStsCd, dtlInfo.fromStkStsCd);
        // TO_STK_STS_CD
        if (!ZYPCommonFunc.hasValue(dtlInfo.toStkStsCd)) {
            getToSSFromPrchReqLineTp(dtlInfo.prchReqLineTpCd.getValue(), dtlInfo);
        }
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.toStkStsCd, dtlInfo.toStkStsCd);
        // THIS_MTH_FOB_COST_AMT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.thisMthFobCostAmt, BigDecimal.ZERO);
        // ASL_UNIT_PRC_AMT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.aslUnitPrcAmt, BigDecimal.ZERO);
        // ENT_DEAL_NET_UNIT_PRC_AMT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entDealNetUnitPrcAmt, BigDecimal.ZERO);
        // ENT_PO_DTL_DEAL_NET_AMT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entPoDtlDealNetAmt, BigDecimal.ZERO);
        // ENT_FUNC_NET_UNIT_PRC_AMT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entFuncNetUnitPrcAmt, BigDecimal.ZERO);
        // ENT_PO_DTL_FUNC_NET_AMT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entPoDtlFuncNetAmt, BigDecimal.ZERO);
        // FRT_COND_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.frtCondCd, dtlInfo.frtCondCd);
        // SHPG_SVC_LVL_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shpgSvcLvlCd, dtlInfo.shpgSvcLvlCd);
        // CARR_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.carrCd, dtlInfo.carrCd);
        // PRCH_REQ_FRZ_FLG
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqFrzFlg, ZYPConstant.FLG_OFF_N);
        // INSRC_FLG
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.insrcFlg, ZYPConstant.FLG_OFF_N);
        // PO_CRAT_FLG
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.poCratFlg, ZYPConstant.FLG_OFF_N);

        return prchReqDtlTMsg;
    }

    /**
     * getDtlTMsgForTpInsert
     * @param pMsg NPZC103001PMsg
     * @param idx int
     * @return PRCH_REQ_DTLTMsg
     */
    private PRCH_REQ_DTLTMsg getDtlTMsgForTpInsert(NPZC103001PMsg pMsg, int idx) {

        PRCH_REQ_DTLTMsg prchReqDtlTMsg = new PRCH_REQ_DTLTMsg();
        NPZC103001_prchReqInfoPMsg dtlInfo = pMsg.prchReqInfo.no(idx);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqNum, pMsg.prchReqNum);

        if (ZYPCommonFunc.hasValue(dtlInfo.prchReqLineNum)) {

            prchReqDtlTMsg.prchReqLineNum.setValue(dtlInfo.prchReqLineNum.getValue());

        } else {

            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineNum, getNextPrchReqLineNum(pMsg));
            dtlInfo.prchReqLineNum.setValue(prchReqDtlTMsg.prchReqLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(dtlInfo.prchReqLineNum, prchReqDtlTMsg.prchReqLineNum.getValue());
        }

        this.prchReqLineNum = prchReqDtlTMsg.prchReqLineNum.getValue();

        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineSubNum, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(dtlInfo.prchReqLineSubNum, prchReqDtlTMsg.prchReqLineSubNum);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineTpCd, dtlInfo.prchReqLineTpCd);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCratDt, pMsg.procDt);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCratTm, ZYPDateUtil.getCurrentSystemTime(NPZC103001Constant.TIME_PATTERN));
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.AWAITING_SHIPPING);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.trxRefNum, dtlInfo.trxRefNum);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.procrTpCd, PROCR_TP.WAREHOUSE);

        if (ZYPCommonFunc.hasValue(dtlInfo.srcInvtyLocCd)) {

            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcInvtyLocCd, dtlInfo.srcInvtyLocCd);
            Map<String, String> locDat = getWhAndSwhByInvtyLocCd(dtlInfo.srcInvtyLocCd.getValue());

            if (locDat != null) {

                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcRtlWhCd, locDat.get(NPZC103001Constant.RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcRtlSwhCd, locDat.get(NPZC103001Constant.RTL_SWH_CD));
            }
        }

        Map<String, String> locDat = getWhAndSwhByTechTocCd(pMsg);

        if (locDat != null) {

            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.destInvtyLocCd, locDat.get(NPZC103001Constant.INVTY_LOC_CD));
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.destRtlWhCd, locDat.get(NPZC103001Constant.RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.destRtlSwhCd, locDat.get(NPZC103001Constant.RTL_SWH_CD));
        }

        // QC#20379 09/14/2017 Update Start.
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rqstRcvDt, dtlInfo.rqstRcvDt);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rqstRcvTm, dtlInfo.rqstRcvTm);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.proNum, dtlInfo.proNum);
        // QC#20379 09/14/2017 Update End.
        // START 2023/02/08 T.Kuroda [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rqstShipDt, dtlInfo.rqstShipDt);
        // END   2023/02/08 T.Kuroda [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.mdseCd, dtlInfo.mdseCd);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.origRqstMdseCd, dtlInfo.mdseCd);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqQty, dtlInfo.prchReqQty);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.ordQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqDispQty, dtlInfo.prchReqQty);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqBalQty, dtlInfo.prchReqQty);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqInsrcQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCancQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.ropQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.maxInvtyQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.minOrdQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.incrOrdQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.curInvtyQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.curInvtyAvalQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.schdInbdQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.schdOtbdQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shipQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rwsPutAwayQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.backToTechQty, BigDecimal.ZERO);

        if (!ZYPCommonFunc.hasValue(dtlInfo.fromStkStsCd)) {

            getFromSSFromPrchReqLineTp(dtlInfo.prchReqLineTpCd.getValue(), dtlInfo);
        }

        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.fromStkStsCd, dtlInfo.fromStkStsCd);

        if (!ZYPCommonFunc.hasValue(dtlInfo.toStkStsCd)) {

            getToSSFromPrchReqLineTp(dtlInfo.prchReqLineTpCd.getValue(), dtlInfo);
        }

        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.toStkStsCd, dtlInfo.toStkStsCd);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.thisMthFobCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.aslUnitPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entDealNetUnitPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entPoDtlDealNetAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entFuncNetUnitPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entPoDtlFuncNetAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.frtCondCd, dtlInfo.frtCondCd);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shpgSvcLvlCd, dtlInfo.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.carrCd, dtlInfo.carrCd);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqFrzFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.insrcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.poCratFlg, ZYPConstant.FLG_OFF_N);

        return prchReqDtlTMsg;
    }

    /**
     * getSerTMsgForInsert
     * @param pMsg NPZC103001PMsg
     * @param idx int
     * @return PRCH_REQ_SERTMsg
     */
    private PRCH_REQ_SERTMsg getSerTMsgForInsert(NPZC103001PMsg pMsg, int idx) {

        PRCH_REQ_SERTMsg prchReqSerTMsg = new PRCH_REQ_SERTMsg();
        ZYPEZDItemValueSetter.setValue(prchReqSerTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        String prchReqSerPk = ZYPOracleSeqAccessor.getNumberString(NPZC103001Constant.PRCH_REQ_SER_SQ, NPZC103001Constant.PRCH_REQ_SER_SQ_DIGIT);
        ZYPEZDItemValueSetter.setValue(prchReqSerTMsg.prchReqSerPk, new BigDecimal(prchReqSerPk));
        ZYPEZDItemValueSetter.setValue(prchReqSerTMsg.prchReqNum, pMsg.prchReqNum);

        Map<String, Object> numInfo = getSerLineNumLineSubNum(pMsg, idx);

        if (numInfo != null) {

            ZYPEZDItemValueSetter.setValue(prchReqSerTMsg.prchReqLineNum, (String) numInfo.get("prchReqLineNum"));
            ZYPEZDItemValueSetter.setValue(prchReqSerTMsg.prchReqLineSubNum, (BigDecimal) numInfo.get("prchReqLineSubNum"));
        }

        ZYPEZDItemValueSetter.setValue(prchReqSerTMsg.serNum, pMsg.serNumInfo.no(idx).serNum);

        return prchReqSerTMsg;
    }

    /**
     * getSerTMsgForDelete
     * @param pMsg NPZC103001PMsg
     * @param i int
     * @return PRCH_REQ_SERTMsg
     */
    private PRCH_REQ_SERTMsg getSerTMsgForDelete(NPZC103001PMsg pMsg, int i) {

        PRCH_REQ_SERTMsg prchReqSerTMsg = new PRCH_REQ_SERTMsg();
        ZYPEZDItemValueSetter.setValue(prchReqSerTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prchReqSerTMsg.prchReqSerPk, pMsg.serNumInfo.no(i).prchReqSerPk);
        return prchReqSerTMsg;
    }

    /**
     * updatePurchaseRequisition
     * @param pMsg NPZC103001PMsg
     */
    private void updatePurchaseRequisition(NPZC103001PMsg pMsg) {

        PRCH_REQTMsg prchReqTMsg = null;
        this.currPrchReqNum = pMsg.prchReqNum.getValue();

        // Set Key Value to PMsg
        setupPMsgforUpdate(pMsg);

        // QC#30460 Add. Check Hazmat request.
        if (isTechReq(pMsg)) {
            pMsg = checkHazmatRequestAndUpdate(pMsg);
        }

        // Detail
        PRCH_REQ_DTLTMsg prchReqDtlTMsg = null;
        List<PRCH_REQ_DTLTMsg> prDtlTMsgList = new ArrayList<PRCH_REQ_DTLTMsg>();
        boolean isInsert = false;
        String updateMode = null;

        if (NPZC103001Constant.MODE_CANCEL.equals(this.mode)) {

            updateMode = NPZC103001Constant.ALL;
        }

        List<String> procPrchReqLineList = new ArrayList<String>();

        // QC55514
        boolean isHoldMdseCd = false;
        for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {

            if (NPZC103001Constant.MODE_CANCEL.equals(this.mode)) {

                if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(i).prchReqLineNum)) {

                    prchReqDtlTMsg = getDtlTMsgForUpdateCancel(pMsg, i);
                    updateMode = NPZC103001Constant.LINE;
                }

            } else if (NPZC103001Constant.EVENT_SUBMIT.equals(this.event)) {

                PRCH_REQ_DTLTMsg updDtlTMsg = null;

                if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(i).prchReqLineNum) && ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(i).prchReqLineSubNum)) {

                    updDtlTMsg = selectPRCH_REQ_DTLTMsgForUpdate(pMsg, pMsg.prchReqInfo.no(i));

                    if (updDtlTMsg == null) {

                        isInsert = true;
                    }

                } else {

                    isInsert = true;
                }

                if (isInsert) {

                    prchReqDtlTMsg = getDtlTMsgForInsert(pMsg, i);

                } else {

                    prchReqDtlTMsg = getDtlTMsgForUpdateSubmit(pMsg, i, updDtlTMsg);
                }

            } else if (NPZC103001Constant.EVENT_HOLD.equals(this.event)) {
                // QC#55514
                if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(i).mdseCd)) {
                    isHoldMdseCd = true;
                }
                prchReqDtlTMsg = getDtlTMsgForUpdateHold(pMsg, i);

            } else if (NPZC103001Constant.EVENT_ORDER_RELEASE.equals(this.event)) {

                prchReqDtlTMsg = getDtlTMsgForUpdateOrderRelease(pMsg, i);

            } else if (NPZC103001Constant.EVENT_SHIPPED.equals(this.event)) {

                prchReqDtlTMsg = getDtlTMsgForUpdateShipped(pMsg, i);

            } else if (NPZC103001Constant.EVENT_RECEIVED.equals(this.event)) {

                prchReqDtlTMsg = getDtlTMsgForUpdateReceived(pMsg, i);

            } else if (NPZC103001Constant.EVENT_UPDATE.equals(this.event)) {

                prchReqDtlTMsg = getDtlTMsgForUpdateUpdate(pMsg, i);

            // QC#17049 Add.
            } else if (NPZC103001Constant.EVENT_ASN.equals(this.event)) {

                prchReqDtlTMsg = getDtlTMsgForUpdateAsn(pMsg, i);

            } else {

                break;
            }

            if (isInsert) {

                S21FastTBLAccessor.insert(prchReqDtlTMsg);

            } else {

                S21FastTBLAccessor.update(prchReqDtlTMsg);
            }

            if (prchReqDtlTMsg != null && !EZDTBLAccessor.RTNCD_NORMAL.equals(prchReqDtlTMsg.getReturnCode())) {

                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0291E);
                return;

            } else {

                prDtlTMsgList.add(prchReqDtlTMsg);
            }

            if (NPZC103001Constant.EVENT_SHIPPED.equals(this.event) || (NPZC103001Constant.EVENT_RECEIVED.equals(this.event))
                    || (NPZC103001Constant.EVENT_ASN.equals(this.event))) {

                // PRCH_REQ_LINE_SUB_NUM = 0 (Not exist parent line)
                if (BigDecimal.ZERO.compareTo(prchReqDtlTMsg.prchReqLineSubNum.getValue()) == 0) {

                    continue;
                }
                // START 2019/12/20 M.Naito [QC#54908,ADD]
                if (NPZC103001Constant.EVENT_ASN.equals(this.event) && !isTechRequestLine(prchReqDtlTMsg.glblCmpyCd.getValue(), prchReqDtlTMsg.prchReqLineTpCd.getValue())) {

                    continue;
                }
                // END 2019/12/20 M.Naito [QC#54908,ADD]

                PRCH_REQ_DTLTMsg prntPrchReqDtlTMsg = getPrntPrchReqDtlTMsg(prchReqDtlTMsg);

                if (prntPrchReqDtlTMsg != null) {

                    String prchReqLineStsCd = getUpdprchReqLineSts(prntPrchReqDtlTMsg);

                    if (!prntPrchReqDtlTMsg.prchReqLineStsCd.getValue().equals(prchReqLineStsCd)) {

                        ZYPEZDItemValueSetter.setValue(prntPrchReqDtlTMsg.prchReqLineStsCd, prchReqLineStsCd);
                        S21ApiTBLAccessor.update(prntPrchReqDtlTMsg);

                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prntPrchReqDtlTMsg.getReturnCode())) {

                            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0291E);
                            return;
                        }
                    }

                    if (!procPrchReqLineList.contains(prchReqDtlTMsg.prchReqLineNum.getValue())) {

                        procPrchReqLineList.add(prchReqDtlTMsg.prchReqLineNum.getValue());
                        prDtlTMsgList.add(prntPrchReqDtlTMsg);
                    }
                }
            }
        }

        if (updateMode != null) {

            // QC#16231
            if (NPZC103001Constant.MODE_CANCEL.equals(this.mode) //
                    && NPZC103001Constant.EVENT_SHIPPED.equals(this.event)) {

                prDtlTMsgList = getDtlTMsgListForUpdateShipCancel(pMsg);

            } else {

                prDtlTMsgList = getDtlTMsgListForUpdateCancel(pMsg, updateMode);

            }

            int ret = S21FastTBLAccessor.update(prDtlTMsgList.toArray(new PRCH_REQ_DTLTMsg[prDtlTMsgList.size()]));

            if (ret != prDtlTMsgList.size()) {

                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0291E);
                return;
            }

        } else if (NPZC103001Constant.EVENT_APPROVAL.equals(this.event)) {

            prDtlTMsgList = getDtlTMsgListForUpdateApproval(pMsg);
            int ret = S21FastTBLAccessor.update(prDtlTMsgList.toArray(new PRCH_REQ_DTLTMsg[prDtlTMsgList.size()]));

            if (ret != prDtlTMsgList.size()) {

                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0291E);
                return;
            }
        }

        // Config
        List<PRCH_REQ_DTLTMsg> configDtlTMsgList = getConfigPrDetail(pMsg.prchReqNum.getValue());

        for (int i = 0; i < configDtlTMsgList.size(); i++) {

            PRCH_REQ_DTLTMsg configPrchReqDtlTMsg = configDtlTMsgList.get(i);

            // check cancel lines to update cancel status for config
            // line
            boolean isAllCancelled = true;

            List<String> prchReqLineStsCdList = new ArrayList<String>();
            prchReqLineStsCdList.add(PRCH_REQ_LINE_STS.CANCELLED);

            BigDecimal cancelResult = getConfigDtlNoMatchLineStsCd(configPrchReqDtlTMsg, prchReqLineStsCdList);

            if (BigDecimal.ZERO.compareTo(cancelResult) == 0) {

                isAllCancelled = true;

            } else {

                isAllCancelled = false;
            }

            if (isAllCancelled) {

                ZYPEZDItemValueSetter.setValue(configPrchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.CANCELLED);

                // check close and cancel lines to update close status
                // for config line
            } else {

                boolean isAllClosed = true;

                prchReqLineStsCdList.add(PRCH_REQ_LINE_STS.CANCELLED);
                prchReqLineStsCdList.add(PRCH_REQ_LINE_STS.CLOSED);
                prchReqLineStsCdList.add(getClosePRLineStatus(pMsg));

                BigDecimal closeResult = getConfigDtlNoMatchLineStsCd(configPrchReqDtlTMsg, prchReqLineStsCdList);

                if (BigDecimal.ZERO.compareTo(closeResult) == 0) {

                    isAllClosed = true;

                } else {

                    isAllClosed = false;
                }

                if (isAllClosed) {

                    ZYPEZDItemValueSetter.setValue(configPrchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.CLOSED);
                }
            }

            // update config line status
            if (configPrchReqDtlTMsg.prchReqLineStsCd.getValue().equals(PRCH_REQ_LINE_STS.CLOSED) || configPrchReqDtlTMsg.prchReqLineStsCd.getValue().equals(PRCH_REQ_LINE_STS.CANCELLED)) {

                EZDTBLAccessor.updateSelectionField(configPrchReqDtlTMsg, new String[] {"prchReqLineStsCd" });

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(configPrchReqDtlTMsg.getReturnCode())) {

                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0291E);
                    return;
                }
            }
        }

        // Header
        // Get PRCH_REQ TMsg by Event ("Hold" is not update the
        // Header)
        if (NPZC103001Constant.MODE_CANCEL.equals(this.mode)) {

            prchReqTMsg = getHdrTMsgForUpdateCancel(pMsg, updateMode);

            // START 2023/01/24 T.Kuroda [QC#60908, ADD]
            // update prchReqApvl
            if (PRCH_REQ_APVL_STS.REJECTED.equals(pMsg.prchReqApvlStsCd.getValue())) {
                prchReqTMsg = getHdrTMsgForUpdateReject(prchReqTMsg, pMsg);
            }
            // END 2023/01/24 T.Kuroda [QC#60908, ADD]

        } else if (NPZC103001Constant.EVENT_SUBMIT.equals(this.event)) {

            prchReqTMsg = getHdrTMsgForUpdateSubmit(pMsg);
            // QC#53301 Add Start
            if (prchReqTMsg == null) {
                return;
            }
            // QC#53301 Add End

        } else if (NPZC103001Constant.EVENT_APPROVAL.equals(this.event)) {

            prchReqTMsg = getHdrTMsgForUpdateApproval(pMsg);

        } else if (NPZC103001Constant.EVENT_HOLD.equals(this.event)) {

            // QC#55514
            if (isHoldMdseCd) {
                prchReqTMsg = getHdrTMsgForUpdateUpdate(pMsg);
                // Create History
                createBizProcLog(prchReqTMsg, prDtlTMsgList);
            }
            return;

        } else if (NPZC103001Constant.EVENT_ORDER_RELEASE.equals(this.event)) {

            prchReqTMsg = getHdrTMsgForUpdateOrderRelease(pMsg);

        } else if (NPZC103001Constant.EVENT_SHIPPED.equals(this.event)) {

            prchReqTMsg = getHdrTMsgForUpdateShipped(pMsg);

        } else if (NPZC103001Constant.EVENT_RECEIVED.equals(this.event)) {

            prchReqTMsg = getHdrTMsgForUpdateReceived(pMsg);

        } else if (NPZC103001Constant.EVENT_UPDATE.equals(this.event)) {

            prchReqTMsg = getHdrTMsgForUpdateUpdate(pMsg);

        // QC#17049 Add.
        } else if (NPZC103001Constant.EVENT_ASN.equals(this.event)) {

            prchReqTMsg = getHdrTMsgForUpdateUpdate(pMsg);
        }

        S21FastTBLAccessor.update(prchReqTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prchReqTMsg.getReturnCode())) {

            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0100E);
            return;
        }

        // Create History
        createBizProcLog(prchReqTMsg, prDtlTMsgList);

        // Release Pending Parts
        if (PRCH_REQ_STS.CLOSED.equals(prchReqTMsg.prchReqStsCd.getValue()) && PRCH_REQ_REC_TP.TECH_REQUEST.equals(prchReqTMsg.prchReqRecTpCd.getValue()) && ZYPCommonFunc.hasValue(prchReqTMsg.fsrNum)) {

            HashMap<String, String> param = new HashMap<String, String>();
            param.put("glblCmpyCd", prchReqTMsg.glblCmpyCd.getValue());
            param.put("prchReqNum", prchReqTMsg.prchReqNum.getValue());
            param.put("svcTaskStsPendPrt", SVC_TASK_STS.PENDING_PARTS);
            // Add Start 2017/08/29 QC#18573
            param.put("svcTaskStsTBO", SVC_TASK_STS.TBO);
            // Add End 2017/08/29 QC#18573

            List<Map<String, Object>> fsrTaskMapList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getFsrTaskList", param);

            if (fsrTaskMapList != null && !fsrTaskMapList.isEmpty()) {
                // Add Start 2017/08/29 QC#18573
                String erlStartTs;
                // Add End 2017/08/29 QC#18573
                for (Map<String, Object> fsrTaskMap : fsrTaskMapList) {
                    // Add Start 2017/08/29 QC#18573
                    erlStartTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
                    erlStartTs = NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(
                            pMsg.glblCmpyCd.getValue()
                          , BigDecimal.ZERO
                          , (BigDecimal) fsrTaskMap.get("SVC_MACH_MSTR_PK")
                          , erlStartTs.substring(0, 8)
                          , erlStartTs.substring(8, 14)
                          , false
                          // START 2019/07/31 K.Kitachi [QC#52257, ADD]
                          , (String) fsrTaskMap.get("DS_SVC_CALL_TP_CD")
                          // END 2019/07/31 K.Kitachi [QC#52257, ADD]
                          // START 2022/04/11 K.Kitachi [QC#59899, ADD]
                          , (String) fsrTaskMap.get("SHIP_TO_UPD_CUST_CD")
                          // END 2022/04/11 K.Kitachi [QC#59899, ADD]
                    );
                    // Add End 2017/08/29 QC#18573

                    // Call FSR Update API
                    NSZC043001PMsg fsrUpdPMsg = new NSZC043001PMsg();
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.glblCmpyCd, prchReqTMsg.glblCmpyCd.getValue());
                    // START 2021/01/26 L.Mandanas [QC#56947, MOD]
                    //    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.xxModeCd, NSZC043001Constant.MODE_UPDATE_FSR);
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.xxModeCd,
                            NSZC043001Constant.MODE_UPDATE_FSR_PR);
                    // END 2021/01/26 L.Mandanas [QC#56947, MOD]
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.fsrNum, (String) fsrTaskMap.get("FSR_NUM"));

                    // QC#58023 Mod Start
                    //ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.userId, (String) fsrTaskMap.get("RQST_TECH_TOC_CD"));
                    String fsrVistTechCd = (String) fsrTaskMap.get("TECH_CD");
                    String rqstTechTocCd = (String) fsrTaskMap.get("RQST_TECH_TOC_CD");
                    if (ZYPCommonFunc.hasValue(fsrVistTechCd) && ZYPCommonFunc.hasValue(rqstTechTocCd) && !fsrVistTechCd.equals(rqstTechTocCd)) {
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.userId, fsrVistTechCd);
                    } else {
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.userId, rqstTechTocCd);
                    }
                    // QC#58023 Mod End

                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).svcTaskNum, (String) fsrTaskMap.get("SVC_TASK_NUM"));

                    // QC#58023 Mod Start
                    // ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).techCd, (String) fsrTaskMap.get("RQST_TECH_TOC_CD"));
                    String svcAsgTechCd = (String) fsrTaskMap.get("SVC_ASG_TECH_CD");
                    if (ZYPCommonFunc.hasValue(svcAsgTechCd) && ZYPCommonFunc.hasValue(rqstTechTocCd) && !fsrVistTechCd.equals(rqstTechTocCd)) {
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).techCd, fsrVistTechCd);
                    } else {
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).techCd, rqstTechTocCd);
                    }
                    // QC#58023 Mod End
                    // START 2022/12/12 K.Kitachi [QC#60911, ADD]
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).svcAsgTpCd, SVC_ASG_TP.REQUIRED);
                    // END 2022/12/12 K.Kitachi [QC#60911, ADD]

                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
                    // START 2019/05/30 M.Naito [QC#50597,MOD]
//                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).xxOrigFollUpTaskFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).xxOrigFollUpTaskFlg, ZYPConstant.FLG_OFF_N);
                    // Add Start 2017/08/29 QC#18573
//                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcTaskStsCd, SVC_TASK_STS.TBO);
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).ovrdFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).erlStartTs, erlStartTs);
                    // Add End 2017/08/29 QC#18573
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcMachMstrPk, (BigDecimal) fsrTaskMap.get("SVC_MACH_MSTR_PK"));
                    // END 2019/05/30 M.Naito [QC#50597,MOD]

                    fsrUpdPMsg.taskList.setValidCount(1);

                    NSZC043001 api = new NSZC043001();
                    api.execute(fsrUpdPMsg, onBatTpCd);

                    for (String xxMsgId : S21ApiUtil.getXxMsgIdList(fsrUpdPMsg)) {

                        this.msgMap.addXxMsgId(xxMsgId);
                    }
                }
            }
        }

        // Serial
        if (pMsg.serNumInfo.getValidCount() < 1) {

            return;
        }

        List<PRCH_REQ_SERTMsg> deleteSerList = new ArrayList<PRCH_REQ_SERTMsg>();
        List<PRCH_REQ_SERTMsg> prchReqSerTMsg = new ArrayList<PRCH_REQ_SERTMsg>();

        for (int i = 0; i < pMsg.serNumInfo.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(pMsg.serNumInfo.no(i).prchReqSerPk)) {

                // Delete
                deleteSerList.add(getSerTMsgForDelete(pMsg, i));
                prchReqSerTMsg.add(getSerTMsgForInsert(pMsg, i));

            } else if (!existSerial(pMsg, i)) {

                // Insert
                prchReqSerTMsg.add(getSerTMsgForInsert(pMsg, i));
            }
        }

        int lineNum = S21FastTBLAccessor.insert(prchReqSerTMsg.toArray(new PRCH_REQ_SERTMsg[prchReqSerTMsg.size()]));

        if (lineNum != prchReqSerTMsg.size()) {

            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0290E);
            return;
        }

        // Delete Serial Number
        if (deleteSerList.size() > 0) {

            S21FastTBLAccessor.removeLogical(deleteSerList.toArray(new PRCH_REQ_SERTMsg[deleteSerList.size()]));
        }
    }

    /**
     * setupPMsgforUpdate
     * @param pMsg NPZC103001PMsg
     */
    private void setupPMsgforUpdate(NPZC103001PMsg pMsg) {

        PRCH_REQTMsg tMsg = selectPRCH_REQTMsg(pMsg.prchReqNum.getValue());
        setupPMsgforUpdate(pMsg, tMsg);
    }

    /**
     * setupPMsgforUpdate
     * @param pMsg NPZC103001PMsg
     * @param tMsg PRCH_REQTMsg
     */
    private void setupPMsgforUpdate(NPZC103001PMsg pMsg, PRCH_REQTMsg tMsg) {

        // PRCH_REQ_TP_CD
        if (!ZYPCommonFunc.hasValue(pMsg.prchReqTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqTpCd, tMsg.prchReqTpCd);
        }
        // PRCH_REQ_REC_TP_CD
        if (!ZYPCommonFunc.hasValue(pMsg.prchReqRecTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqRecTpCd, tMsg.prchReqRecTpCd);
        }
        // PRCH_REQ_SRC_TP_CD
        if (!ZYPCommonFunc.hasValue(pMsg.prchReqSrcTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqSrcTpCd, tMsg.prchReqSrcTpCd);
        }
        // PRCH_REQ_APVL_STS_CD
        if (!ZYPCommonFunc.hasValue(pMsg.prchReqApvlStsCd) && !NPZC103001Constant.EVENT_SUBMIT.equals(this.event)) {
            // expect update/submit
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, tMsg.prchReqApvlStsCd);
        }
    }

    /**
     * getHdrTMsgForUpdateCancel
     * @param pMsg NPZC103001PMsg
     * @param cancelMode String
     * @return PRCH_REQTMsg
     */
    private PRCH_REQTMsg getHdrTMsgForUpdateCancel(NPZC103001PMsg pMsg, String cancelMode) {

        PRCH_REQTMsg prchReqTMsg = selectPRCH_REQTMsgForUpdate(pMsg);
        // PRCH_REQ_STS_CD
        if (isAllDtlCancel(pMsg)) {
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqStsCd, PRCH_REQ_STS.CANCELLED);
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCloDtTmTs, getCurrentDtTmTs());
        } else if (isClosePRLineStatus(pMsg)) {
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqStsCd, PRCH_REQ_STS.CLOSED);
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCloDtTmTs, getCurrentDtTmTs());
        }
        return prchReqTMsg;
    }

// START 2023/01/24 T.Kuroda [QC#60908, ADD]
    /**
     * getHdrTMsgForUpdateReject
     * @param prchReqTMsg PRCH_REQTMsg
     * @param pMsg NPZC103001PMsg
     * @return PRCH_REQTMsg
     */
    private PRCH_REQTMsg getHdrTMsgForUpdateReject(PRCH_REQTMsg prchReqTMsg, NPZC103001PMsg pMsg) {

        // PRCH_REQ_APVL_STS_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlStsCd, pMsg.prchReqApvlStsCd);
        // PRCH_REQ_APVL_DT
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlDt, pMsg.prchReqApvlDt);
        // PRCH_REQ_APVL_BY_PSN_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlByPsnCd, pMsg.prchReqApvlByPsnCd);
        // PRCH_REQ_APVL_BY_NM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlByNm, getFullPersonName(pMsg.prchReqApvlByPsnCd.getValue()));

        return prchReqTMsg;
    }
// END 2023/01/24 T.Kuroda [QC#60908, ADD]

    /**
     * getHdrTMsgForUpdateSubmit
     * @param pMsg NPZC103001PMsg
     * @return PRCH_REQTMsg
     */
    private PRCH_REQTMsg getHdrTMsgForUpdateSubmit(NPZC103001PMsg pMsg) {

        PRCH_REQTMsg prchReqTMsg = selectPRCH_REQTMsgForUpdate(pMsg);
        // SHIP_TO_CUST_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToCustCd, pMsg.shipToCustCd);

        // QC#21657
        if (ZYPCommonFunc.hasValue(pMsg.shipToCustCd)) {
            getShipToCustAddress(pMsg);
        }

        // SHIP_TO_LOC_NM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToLocNm, pMsg.shipToLocNm);
        // SHIP_TO_ADDL_LOC_NM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToAddlLocNm, pMsg.shipToAddlLocNm);
        // SHIP_TO_FIRST_LINE_ADDR
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToFirstLineAddr, pMsg.shipToFirstLineAddr);
        // SHIP_TO_SCD_LINE_ADDR
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToScdLineAddr, pMsg.shipToScdLineAddr);
        // SHIP_TO_THIRD_LINE_ADDR
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToThirdLineAddr, pMsg.shipToThirdLineAddr);
        // SHIP_TO_FRTH_LINE_ADDR
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToFrthLineAddr, pMsg.shipToFrthLineAddr);
        // SHIP_TO_FIRST_REF_CMNT_TXT
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToFirstRefCmntTxt, pMsg.shipToFirstRefCmntTxt);
        // SHIP_TO_SCD_REF_CMNT_TXT
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToScdRefCmntTxt, pMsg.shipToScdRefCmntTxt);
        // SHIP_TO_CTY_ADDR
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToCtyAddr, pMsg.shipToCtyAddr);
        // SHIP_TO_ST_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToStCd, pMsg.shipToStCd);
        // SHIP_TO_PROV_NM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToProvNm, pMsg.shipToProvNm);
        // SHIP_TO_CNTY_NM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToCntyNm, pMsg.shipToCntyNm);
        // SHIP_TO_POST_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToPostCd, pMsg.shipToPostCd);
        // SHIP_TO_CTRY_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.shipToCtryCd, pMsg.shipToCtryCd);

        if (ZYPCommonFunc.hasValue(pMsg.shipToCustCd) && (!ZYPCommonFunc.hasValue(pMsg.billToCustCd) || !ZYPCommonFunc.hasValue(pMsg.sellToCustCd))) {
            Map<String, String> billToSellTo = getBillToSellToByShipTo(pMsg);
            // BILL_TO_CUST_CD => SHIP_TO_CUST.BILL_TO_CUST_CD is always NULL.
            /*
            if (!ZYPCommonFunc.hasValue(pMsg.billToCustCd)) {
                ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, billToSellTo.get(NPZC103001Constant.BILL_TO_CUST_CD));
            }
            */
            // SELL_TO_CUST_CD
            if (!ZYPCommonFunc.hasValue(pMsg.sellToCustCd)) {
                ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, billToSellTo.get(NPZC103001Constant.SELL_TO_CUST_CD));
            }
        }
        // BILL_TO_CUST_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.billToCustCd, pMsg.billToCustCd);
        // SELL_TO_CUST_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.sellToCustCd, pMsg.sellToCustCd);

        // PRCH_REQ_RQST_BY_PSN_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqRqstByPsnCd, pMsg.prchReqRqstByPsnCd);

        // QC#17075 START
        // QC#20439 Mod START
//        if (PRCH_REQ_SRC_TP.WH_PLANNING.equals(prchReqTMsg.prchReqSrcTpCd.getValue())) {
//            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCratByPsnCd, pMsg.prchReqCratByPsnCd);
//        }
        if (ZYPCommonFunc.hasValue(pMsg.prchReqCratByPsnCd)) {
        	ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCratByPsnCd, pMsg.prchReqCratByPsnCd);

        	String cratByNm = getFullPersonName(pMsg.prchReqCratByPsnCd.getValue());
            if (ZYPCommonFunc.hasValue(cratByNm)) {
            	ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCratByNm, cratByNm);
            } else {
            	ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCratByNm, pMsg.prchReqCratByPsnCd);
            }
        }

        // QC#20439 Mod END
        // QC#17075 END

        // PRCH_REQ_APVL_STS_CD
        if (!ZYPCommonFunc.hasValue(pMsg.prchReqApvlStsCd)) {
            pMsg.prchReqApvlStsCd.setValue(PRCH_REQ_APVL_STS.SUBMIT_FOR_APPROVAL);
        }
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlStsCd, pMsg.prchReqApvlStsCd);
        // PRCH_REQ_APVL_DT
        if (PRCH_REQ_APVL_STS.PRE_APPROVED.equals(pMsg.prchReqApvlStsCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlDt, pMsg.procDt);
        }
        // FSR_NUM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.fsrNum, pMsg.fsrNum);
        // SVC_TASK_NUM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.svcTaskNum, pMsg.svcTaskNum);
        // SVC_MACH_SER_NUM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.svcMachSerNum, pMsg.svcMachSerNum);
        // CTAC_PSN_NM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.ctacPsnNm, pMsg.ctacPsnNm);
        // LINE_BIZ_TP_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.lineBizTpCd, pMsg.lineBizTpCd);
        // PRCH_REQ_CMNT_TXT
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCmntTxt, pMsg.prchReqCmntTxt);
        // DELY_ADDL_CMNT_TXT
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.delyAddlCmntTxt, pMsg.delyAddlCmntTxt);
        // RCV_ADDL_CMNT_TXT
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.rcvAddlCmntTxt, pMsg.rcvAddlCmntTxt);

        // sol#118(QC#12110) Start
        if (ZYPConstant.FLG_ON_Y.equals(prchReqTMsg.techExpRqstFlg.getValue())) {
            isToolItem = true;
        }
        // sol#118(QC#12110) End
        // QC#30444
        // PRCH_GRP_CD
        if (!ZYPCommonFunc.hasValue(pMsg.prchGrpCd)) {
            String prchGrpCd = getPrchGrpCd(pMsg);
            if (prchGrpCd == null) {
                //QC#53301 Mod Start
                if (!ZYPCommonFunc.hasValue(prchReqTMsg.prchGrpCd)) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0235E);
                    return null;
                } else {
                    prchGrpCd = prchReqTMsg.prchGrpCd.getValue();
                }
                //QC#53301 Mod End
            }
            ZYPEZDItemValueSetter.setValue(pMsg.prchGrpCd, prchGrpCd);
        }
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchGrpCd, pMsg.prchGrpCd);
        // START 2023/02/15 T.Kuroda [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.rqstShipDt, pMsg.rqstShipDt);
        // END   2023/02/15 T.Kuroda [QC#60966, ADD]

        return prchReqTMsg;
    }

    /**
     * getHdrTMsgForUpdateApproval
     * @param pMsg NPZC103001PMsg
     * @return PRCH_REQTMsg
     */
    private PRCH_REQTMsg getHdrTMsgForUpdateApproval(NPZC103001PMsg pMsg) {

        PRCH_REQTMsg prchReqTMsg = selectPRCH_REQTMsgForUpdate(pMsg);
        // PRCH_REQ_APVL_STS_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlStsCd, pMsg.prchReqApvlStsCd);
        // PRCH_REQ_APVL_DT
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlDt, pMsg.prchReqApvlDt);
        // PRCH_REQ_APVL_BY_PSN_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlByPsnCd, pMsg.prchReqApvlByPsnCd);
        // PRCH_REQ_APVL_BY_NM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlByNm, getFullPersonName(pMsg.prchReqApvlByPsnCd.getValue()));
        return prchReqTMsg;
    }

    /**
     * getHdrTMsgForUpdateOrderRelease
     * @param pMsg NPZC103001PMsg
     * @return PRCH_REQTMsg
     */
    private PRCH_REQTMsg getHdrTMsgForUpdateOrderRelease(NPZC103001PMsg pMsg) {

        PRCH_REQTMsg prchReqTMsg = selectPRCH_REQTMsgForUpdate(pMsg);
        // PRCH_REQ_STS_CD
        if (ZYPCommonFunc.hasValue(pMsg.prchReqStsCd)) {
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqStsCd, pMsg.prchReqStsCd);
            if (PRCH_REQ_STS.CLOSED.equals(pMsg.prchReqStsCd.getValue()) || PRCH_REQ_STS.CANCELLED.equals(pMsg.prchReqStsCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCloDtTmTs, getCurrentDtTmTs());
            }
            return prchReqTMsg;
        } else {
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("prchReqNum", pMsg.prchReqNum.getValue());

            List<String> lineStsList = (List<String>) this.ssmBatchClient.queryObjectList("getLineStatus", queryParam);

            if (lineStsList == null || lineStsList.size() == 0) {
                return null;
            }
            boolean isAllCancelled = true;
            for (String lineStsCd : lineStsList) {
                if (!lineStsCd.equals(PRCH_REQ_LINE_STS.CANCELLED)) {
                    isAllCancelled = false;
                    break;
                }
            }
            if (isAllCancelled) {
                ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqStsCd, PRCH_REQ_STS.CANCELLED);
                ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCloDtTmTs, getCurrentDtTmTs());
                return prchReqTMsg;
            }

            boolean isAllClosed = true;
            for (String lineStsCd : lineStsList) {
                if (!lineStsCd.equals(PRCH_REQ_LINE_STS.CLOSED) && !lineStsCd.equals(PRCH_REQ_LINE_STS.CANCELLED)) {
                    isAllClosed = false;
                    break;
                }
            }
            if (isAllClosed) {
                ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqStsCd, PRCH_REQ_STS.CLOSED);
                ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCloDtTmTs, getCurrentDtTmTs());
                return prchReqTMsg;
            }
        }
        return prchReqTMsg;
    }

    /**
     * getHdrTMsgForUpdateShipped
     * @param pMsg NPZC103001PMsg
     * @return PRCH_REQTMsg
     */
    private PRCH_REQTMsg getHdrTMsgForUpdateShipped(NPZC103001PMsg pMsg) {

        PRCH_REQTMsg prchReqTMsg = selectPRCH_REQTMsgForUpdate(pMsg);
        // PRCH_REQ_STS_CD
        if (isAllDtlCancel(pMsg)) {
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqStsCd, PRCH_REQ_STS.CANCELLED);
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCloDtTmTs, getCurrentDtTmTs());
        } else if (isClosePRLineStatus(pMsg)) {
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqStsCd, PRCH_REQ_STS.CLOSED);
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCloDtTmTs, getCurrentDtTmTs());
        }
        return prchReqTMsg;
    }

    /**
     * getHdrTMsgForUpdateReceived
     * @param pMsg NPZC103001PMsg
     * @return PRCH_REQTMsg
     */
    private PRCH_REQTMsg getHdrTMsgForUpdateReceived(NPZC103001PMsg pMsg) {

        PRCH_REQTMsg prchReqTMsg = selectPRCH_REQTMsgForUpdate(pMsg);
        // QC#16036. QC#55710
        if (PRCH_REQ_TP.RUSH.equals(prchReqTMsg.prchReqTpCd.getValue()) //
                || PRCH_REQ_TP.STANDARD.equals(prchReqTMsg.prchReqTpCd.getValue()) //
                || PRCH_REQ_TP.MIN_MAX.equals(prchReqTMsg.prchReqTpCd.getValue()) //
                || PRCH_REQ_TP.TECH_RETURN.equals(prchReqTMsg.prchReqTpCd.getValue()) //
                || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(pMsg.prchReqTpCd.getValue()) //
                || PRCH_REQ_TP.PREMIUM_RUSH.equals(prchReqTMsg.prchReqTpCd.getValue())) {

            // Get summary qty
            // START 2022/10/07 R.Azucena [QC#60664, DEL]
            // BigDecimal sumPrchReqQty = getTechReqSumQty(glblCmpyCd, pMsg.prchReqNum.getValue(), false);
            // BigDecimal sumRwsPutAwayQty = getTechReqSumQty(glblCmpyCd, pMsg.prchReqNum.getValue(), true);
            // END 2022/10/07 R.Azucena [QC#60664, DEL]

            // START 2019/07/24 M.Naito [QC#51917,MOD]
//            if (sumRwsPutAwayQty != null && sumPrchReqQty.compareTo(sumRwsPutAwayQty) == 0) {
            // START 2022/10/07 R.Azucena [QC#60664, MOD]
            // if (sumRwsPutAwayQty != null && sumPrchReqQty.compareTo(sumRwsPutAwayQty) == 0 && isClosePRLineStatus(pMsg)) {
            if (isClosePRLineStatus(pMsg)) {
            // END 2022/10/07 R.Azucena [QC#60664, MOD]
            // END 2019/07/24 M.Naito [QC#51917,MOD]
                ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqStsCd, PRCH_REQ_STS.CLOSED);
                ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCloDtTmTs, getCurrentDtTmTs());
            } else if (isAllDtlCancel(pMsg)) {
                ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqStsCd, PRCH_REQ_STS.CANCELLED);
                ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCloDtTmTs, getCurrentDtTmTs());
            }

        } else {
            // PRCH_REQ_STS_CD
            if (isAllDtlCancel(pMsg)) {
                ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqStsCd, PRCH_REQ_STS.CANCELLED);
                ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCloDtTmTs, getCurrentDtTmTs());
            } else if (isClosePRLineStatus(pMsg)) {
                ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqStsCd, PRCH_REQ_STS.CLOSED);
                ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCloDtTmTs, getCurrentDtTmTs());
            }
        }

        return prchReqTMsg;
    }

    /**
     * getHdrTMsgForUpdateUpdate
     * @param pMsg NPZC103001PMsg
     * @return PRCH_REQTMsg
     */
    private PRCH_REQTMsg getHdrTMsgForUpdateUpdate(NPZC103001PMsg pMsg) {

        PRCH_REQTMsg prchReqTMsg = selectPRCH_REQTMsgForUpdate(pMsg);
        if (PRCH_REQ_REC_TP.INVENTORY_REQUEST.equals(pMsg.prchReqRecTpCd.getValue())) {
            // PRCH_REQ_STS_CD
            if (isAllDtlCancel(pMsg)) {
                ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqStsCd, PRCH_REQ_STS.CANCELLED);
                ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCloDtTmTs, getCurrentDtTmTs());
            } else if (isClosePRLineStatus(pMsg)) {
                ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqStsCd, PRCH_REQ_STS.CLOSED);
                ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCloDtTmTs, getCurrentDtTmTs());
            }
        }
        return prchReqTMsg;
    }

    /**
     * getDtlTMsgForUpdateSubmit
     * @param pMsg NPZC103001PMsg
     * @param idx int
     * @param prchReqDtlTMsg PRCH_REQ_DTLTMsg
     * @return PRCH_REQ_DTLTMsg
     */
    private PRCH_REQ_DTLTMsg getDtlTMsgForUpdateSubmit(NPZC103001PMsg pMsg, int idx, PRCH_REQ_DTLTMsg prchReqDtlTMsg) {

        NPZC103001_prchReqInfoPMsg dtlInfo = pMsg.prchReqInfo.no(idx);

        // ORIG_PRCH_REQ_LINE_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.origPrchReqLineNum, dtlInfo.origPrchReqLineNum);
        // ORIG_PRCH_REQ_LINE_SUB_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.origPrchReqLineSubNum, dtlInfo.origPrchReqLineSubNum);
        // PRCH_REQ_LINE_TP_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineTpCd, dtlInfo.prchReqLineTpCd);
        // PRCH_REQ_REL_STS_CD
        // QC#52756 Start
        if (ZYPCommonFunc.hasValue(prchReqDtlTMsg.prchReqRelStsCd)) {
        	if (PRCH_REQ_REL_STS.COMPLEATED.equals(prchReqDtlTMsg.prchReqRelStsCd.getValue())) {
        		ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.COMPLEATED);
        	} else {
        		ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.IN_COMPLETED);
        	}
        } else {
        	ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.IN_COMPLETED);
        }
        // QC#52756 End
        // REL_RQST_TO_PO_ORD_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.relRqstToPoOrdNum, dtlInfo.relRqstToPoOrdNum);
        // PO_SCHD_REL_DT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.poSchdRelDt, dtlInfo.poSchdRelDt);
        // PROCR_TP
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.procrTpCd, dtlInfo.procrTpCd);
        // SRC_INVTY_LOC_CD
        if (ZYPCommonFunc.hasValue(dtlInfo.srcInvtyLocCd)) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcInvtyLocCd, dtlInfo.srcInvtyLocCd);
            // get SRC_RTL_WH_CD/SRC_RTL_SWH_CD
            Map<String, String> locDat = getWhAndSwhByInvtyLocCd(dtlInfo.srcInvtyLocCd.getValue());

            if (locDat != null) {
                // SRC_RTL_WH_CD
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcRtlWhCd, locDat.get(NPZC103001Constant.RTL_WH_CD));
                // SRC_RTL_SWH_CD
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcRtlSwhCd, locDat.get(NPZC103001Constant.RTL_SWH_CD));
            } else {
                // SRC_RTL_WH_CD
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcRtlWhCd, dtlInfo.srcInvtyLocCd);
                // SRC_RTL_SWH_CD
                prchReqDtlTMsg.srcRtlSwhCd.clear();
            }
        }
        // PRNT_VND_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prntVndCd, dtlInfo.prntVndCd);
        // VND_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.vndCd, dtlInfo.vndCd);
        // VND_INVTY_LOC_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.vndInvtyLocCd, dtlInfo.vndInvtyLocCd);
        // VND_RTRN_RSN_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.vndRtrnRsnCd, dtlInfo.vndRtrnRsnCd);
        // DEST_INVTY_LOC_CD
        if (ZYPCommonFunc.hasValue(dtlInfo.destInvtyLocCd) && !dtlInfo.destInvtyLocCd.getValue().equals(prchReqDtlTMsg.destInvtyLocCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.destInvtyLocCd, dtlInfo.destInvtyLocCd);
            Map<String, String> locDat = getWhAndSwhByInvtyLocCd(dtlInfo.destInvtyLocCd.getValue());

            if (locDat != null) {
                // DEST_RTL_WH_CD
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.destRtlWhCd, locDat.get(NPZC103001Constant.RTL_WH_CD));
                // DEST_RTL_SWH_CD
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.destRtlSwhCd, locDat.get(NPZC103001Constant.RTL_SWH_CD));
            } else {
                // DEST_RTL_WH_CD
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.destRtlWhCd, dtlInfo.destInvtyLocCd);
                // DEST_RTL_SWH_CD
                prchReqDtlTMsg.destRtlSwhCd.clear();
            }
        }
        // RQST_RCV_DT
        if (!ZYPCommonFunc.hasValue(dtlInfo.rqstRcvDt)) {
            // Calculate RDD for PR API
            ZYPEZDItemValueSetter.setValue(dtlInfo.rqstRcvDt, calcRddForPr(pMsg, idx));
        }
        if (ZYPCommonFunc.hasValue(dtlInfo.rqstRcvTm)) {
            // QC#55710
            if (PRCH_REQ_TP.TECH_RETURN.equals(pMsg.prchReqTpCd.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(pMsg.prchReqTpCd.getValue())) {
                setRqstRcvDtTm(pMsg, idx, prchReqDtlTMsg.srcRtlWhCd.getValue());
            } else {
                setRqstRcvDtTm(pMsg, idx, prchReqDtlTMsg.destRtlWhCd.getValue());
            }
        }
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rqstRcvDt, dtlInfo.rqstRcvDt);
        // RQST_RCV_TM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rqstRcvTm, dtlInfo.rqstRcvTm);
        // START 2023/02/14 T.Kuroda [QC#60966, ADD]
        // RQST_SHIP_DT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rqstShipDt, dtlInfo.rqstShipDt);
        // END   2023/02/14 T.Kuroda [QC#60966, ADD]
        // CONFIG_TP_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.configTpCd, dtlInfo.configTpCd);
        // SVC_CONFIG_MSTR_PK
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.svcConfigMstrPk, dtlInfo.svcConfigMstrPk);
        // MDSE_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.mdseCd, dtlInfo.mdseCd);
        // PO_MDSE_CMPSN_TP_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.poMdseCmpsnTpCd, dtlInfo.poMdseCmpsnTpCd);
        // ASL_MDSE_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.aslMdseCd, dtlInfo.aslMdseCd);
        // PRCH_REQ_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqQty, ZYPCommonFunc.getBigDecimal(dtlInfo.prchReqQty, "0"));
        // PRCH_REQ_DISP_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqDispQty, ZYPCommonFunc.getBigDecimal(dtlInfo.prchReqDispQty, "0"));
        // PRCH_REQ_DSPL_UOM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqDsplUomCd, dtlInfo.prchReqDsplUomCd);
        // QC#55615
        // PRCH_REQ_REL_QTY
        if (isUpdateMode() //
                && ZYPCommonFunc.hasValue(pMsg.prchReqRecTpCd) //
                && PRCH_REQ_REC_TP.TECH_REQUEST.equals(pMsg.prchReqRecTpCd.getValue()) //
                && ZYPCommonFunc.hasValue(prchReqDtlTMsg.prchReqRelQty) // 
                && BigDecimal.ZERO.compareTo(prchReqDtlTMsg.prchReqRelQty.getValue()) < 0) {

            BigDecimal relQty = ZYPCommonFunc.getBigDecimal(dtlInfo.prchReqQty, "0").subtract(ZYPCommonFunc.getBigDecimal(prchReqDtlTMsg.prchReqBalQty, "0"));
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelQty, relQty);
            ZYPEZDItemValueSetter.setValue(dtlInfo.prchReqRelQty, relQty);

        } else {

            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelQty, ZYPCommonFunc.getBigDecimal(dtlInfo.prchReqRelQty, "0"));

        }
        // PRCH_REQ_BAL_QTYa
        BigDecimal balQty = ZYPCommonFunc.getBigDecimal(dtlInfo.prchReqQty, "0").subtract(ZYPCommonFunc.getBigDecimal(dtlInfo.prchReqRelQty, "0"));   
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqBalQty, balQty);
        // PRO_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.proNum, dtlInfo.proNum);
        // FROM_STK_STS_CD
        if (!ZYPCommonFunc.hasValue(dtlInfo.fromStkStsCd)) {
            getFromSSFromPrchReqLineTp(dtlInfo.prchReqLineTpCd.getValue(), dtlInfo);
        }
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.fromStkStsCd, dtlInfo.fromStkStsCd);
        // TO_STK_STS_CD
        if (!ZYPCommonFunc.hasValue(dtlInfo.toStkStsCd)) {
            getToSSFromPrchReqLineTp(dtlInfo.prchReqLineTpCd.getValue(), dtlInfo);
        }
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.toStkStsCd, dtlInfo.toStkStsCd);

        if (PRCH_REQ_REC_TP.PO_REQUISITION.equals(pMsg.prchReqRecTpCd.getValue())) {

            // QC#28939 add start
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.mdseDescShortTxt, dtlInfo.mdseDescShortTxt);
            // QC#28939 add end

            // ASL_DTL_PK
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.aslDtlPk, dtlInfo.aslDtlPk);
            // ASL_UNIT_PRC_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.aslUnitPrcAmt, ZYPCommonFunc.getBigDecimal(dtlInfo.aslUnitPrcAmt, "0"));
            // ENT_DEAL_NET_UNIT_PRC_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entDealNetUnitPrcAmt, ZYPCommonFunc.getBigDecimal(dtlInfo.entDealNetUnitPrcAmt, "0"));
            // ENT_PO_DTL_DEAL_NET_AMT
            BigDecimal dealAmt = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(dtlInfo.entDealNetUnitPrcAmt)) {
                dealAmt = dtlInfo.entDealNetUnitPrcAmt.getValue().multiply(ZYPCommonFunc.getBigDecimal(dtlInfo.prchReqDispQty, "0"));
            }
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entPoDtlDealNetAmt, dealAmt);
            // THIS_MTH_FOB_COST_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.thisMthFobCostAmt, ZYPCommonFunc.getBigDecimal(dtlInfo.entFuncNetUnitPrcAmt, "0"));
            // ENT_FUNC_NET_UNIT_PRC_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entFuncNetUnitPrcAmt, ZYPCommonFunc.getBigDecimal(dtlInfo.entFuncNetUnitPrcAmt, "0"));
            // ENT_PO_DTL_FUNC_NET_AMT
            if (BigDecimal.ZERO.compareTo(dealAmt) == 0) {
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entPoDtlFuncNetAmt, BigDecimal.ZERO);
            } else {
                NPXC001001CurrencyConversion currCon = new NPXC001001CurrencyConversion();
                NPXC001001CurrencyConversionBean currConBean = currCon.convertCurrency(this.glblCmpyCd, dtlInfo.ccyCd.getValue(), dealAmt, pMsg.procDt.getValue(), null);
                if (NFCConst.CST_RTN_CD_ERR.equals(currConBean.getRtrnCd())) {
                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entPoDtlFuncNetAmt, BigDecimal.ZERO);
                } else {
                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entPoDtlFuncNetAmt, currConBean.getFuncAmt());
                }
            }
            // CCY_CD
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.ccyCd, dtlInfo.ccyCd);
            // EXCH_RATE
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.exchRate, dtlInfo.exchRate);
        // START 2018/05/25 S.Katsuma [QC#25893,ADD]
        } else if (PRCH_REQ_REC_TP.INVENTORY_REQUEST.equals(pMsg.prchReqRecTpCd.getValue())
            && PRCH_REQ_TP.VENDOR_RETURN.equals(pMsg.prchReqTpCd.getValue())) {
            // ENT_DEAL_NET_UNIT_PRC_AMT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entDealNetUnitPrcAmt, ZYPCommonFunc.getBigDecimal(dtlInfo.entDealNetUnitPrcAmt, "0"));
            // ENT_PO_DTL_DEAL_NET_AMT
            BigDecimal dealAmt = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(dtlInfo.entDealNetUnitPrcAmt)) {
                dealAmt = dtlInfo.entDealNetUnitPrcAmt.getValue().multiply(ZYPCommonFunc.getBigDecimal(dtlInfo.prchReqDispQty, "0"));
            }
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.entPoDtlDealNetAmt, dealAmt);
        }
        // END 2018/05/25 S.Katsuma [QC#25893,ADD]
        // FRT_CHRG_TO_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.frtChrgToCd, dtlInfo.frtChrgToCd);
        // FRT_CHRG_METH_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.frtChrgMethCd, dtlInfo.frtChrgMethCd);
        // FRT_COND_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.frtCondCd, dtlInfo.frtCondCd);
        // SHPG_SVC_LVL_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shpgSvcLvlCd, dtlInfo.shpgSvcLvlCd);
        // CARR_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.carrCd, dtlInfo.carrCd);
        // CARR_ACCT_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.carrAcctNum, dtlInfo.carrAcctNum);
        // Vendor Payment
        Map<String, String> vndPmt = getVendorPayment(dtlInfo);
        if (vndPmt != null) {
            // VND_PMT_TERM_CD
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.vndPmtTermCd, vndPmt.get(NPZC103001Constant.VND_PMT_TERM_CD));
            // VND_PMT_TERM_DESC_TXT
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.vndPmtTermDescTxt, vndPmt.get(NPZC103001Constant.VND_PMT_TERM_DESC_TXT));
        }
        // PRCH_REQ_LINE_CMNT_TXT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineCmntTxt, dtlInfo.prchReqLineCmntTxt);
        // COA_CMPY_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaCmpyCd, dtlInfo.coaCmpyCd);
        // COA_AFFL_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaAfflCd, dtlInfo.coaAfflCd);
        // COA_BR_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaBrCd, dtlInfo.coaBrCd);
        // COA_CH_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaChCd, dtlInfo.coaChCd);
        // COA_CC_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaCcCd, dtlInfo.coaCcCd);
        // COA_ACCT_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaAcctCd, dtlInfo.coaAcctCd);
        // COA_PROD_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaProdCd, dtlInfo.coaProdCd);
        // COA_PROJ_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaProjCd, dtlInfo.coaProjCd);
        // COA_EXTN_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.coaExtnCd, dtlInfo.coaExtnCd);
        // TECH_RQST_NTC_ML_PROC_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.techRqstNtcMlProcCd, NPZC103001Constant.TECH_NTC_ML_PROC_EXCLUDED);
        // QC#21209
        // SPCL_INSTN_CMNT_TXT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.spclInstnCmntTxt, dtlInfo.spclInstnCmntTxt);

        return prchReqDtlTMsg;
    }

    /**
     * getDtlTMsgForUpdateHold
     * @param pMsg NPZC103001PMsg
     * @param idx int
     * @return PRCH_REQ_DTLTMsg
     */
    private PRCH_REQ_DTLTMsg getDtlTMsgForUpdateHold(NPZC103001PMsg pMsg, int idx) {

        NPZC103001_prchReqInfoPMsg dtlInfo = pMsg.prchReqInfo.no(idx);
        PRCH_REQ_DTLTMsg prchReqDtlTMsg = selectPRCH_REQ_DTLTMsgForUpdate(pMsg, dtlInfo);
        // PRCH_REQ_LINE_CMNT_TXT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineCmntTxt, dtlInfo.prchReqLineCmntTxt);
        // PRCH_REQ_FRZ_FLG
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqFrzFlg, dtlInfo.prchReqFrzFlg);
        //08/31/2017 CITS S.Endo Add Sol#406(QC#19243) START
        if (ZYPCommonFunc.hasValue(dtlInfo.srcInvtyLocCd)) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcInvtyLocCd, dtlInfo.srcInvtyLocCd);
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineCmntTxt, dtlInfo.prchReqLineCmntTxt);
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelErrMsgTxt, dtlInfo.prchReqLineCmntTxt);
            //divide scrInvtyLocCd to WH/SWH Cd
            if (dtlInfo.srcInvtyLocCd.getValue().length() == NPZC103001Constant.LOC_CD_LENGTH) {
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcRtlWhCd, dtlInfo.srcInvtyLocCd.getValue().substring(0, NPZC103001Constant.WH_CD_LENGTH));
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.srcRtlSwhCd, dtlInfo.srcInvtyLocCd.getValue().substring(NPZC103001Constant.WH_CD_LENGTH));
            }
        }
        //08/31/2017 CITS S.Endo Add Sol#406(QC#19243) END
        // QC#55514
        if (ZYPCommonFunc.hasValue(dtlInfo.mdseCd)) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.mdseCd, dtlInfo.mdseCd);
        }
        return prchReqDtlTMsg;
    }

    /**
     * getDtlTMsgForUpdateOrderRelease
     * @param pMsg NPZC103001PMsg
     * @param idx int
     * @return PRCH_REQ_DTLTMsg
     */
    private PRCH_REQ_DTLTMsg getDtlTMsgForUpdateOrderRelease(NPZC103001PMsg pMsg, int idx) {

        NPZC103001_prchReqInfoPMsg dtlInfo = pMsg.prchReqInfo.no(idx);
        PRCH_REQ_DTLTMsg prchReqDtlTMsg = selectPRCH_REQ_DTLTMsgForUpdate(pMsg, dtlInfo);
        // PRCH_REQ_REL_STS_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelStsCd, dtlInfo.prchReqRelStsCd);
        // PRCH_REQ_LINE_STS_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, dtlInfo.prchReqLineStsCd);
        // PRCH_REQ_REL_DT_TM_TS
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelDtTmTs, ZYPDateUtil.getCurrentSystemTime(NPZC103001Constant.DTTMTS_PATTERN));
        // PRCH_REQ_REL_ERR_MSG_TXT
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelErrMsgTxt, dtlInfo.prchReqRelErrMsgTxt);
        // PO_ORD_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.poOrdNum, dtlInfo.poOrdNum);
        // PO_ORD_DTL_LINE_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.poOrdDtlLineNum, dtlInfo.poOrdDtlLineNum);
        // INVTY_ORD_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.invtyOrdNum, dtlInfo.invtyOrdNum);
        // INVTY_ORD_LINE_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.invtyOrdLineNum, dtlInfo.invtyOrdLineNum);
        // WRK_ORD_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.wrkOrdNum, dtlInfo.wrkOrdNum);
        // WRK_ORD_DTL_LINE_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.wrkOrdDtlLineNum, dtlInfo.wrkOrdDtlLineNum);
        // PRCH_REQ_REL_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelQty, ZYPCommonFunc.getBigDecimal(dtlInfo.prchReqRelQty, "0"));
        // PRCH_REQ_BAL_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqBalQty, prchReqDtlTMsg.prchReqQty.getValue().subtract(dtlInfo.prchReqRelQty.getValue()));
        // SO_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.soNum, dtlInfo.soNum);
        return prchReqDtlTMsg;
    }

    /**
     * getDtlTMsgForUpdateShipped
     * @param pMsg NPZC103001PMsg
     * @param idx int
     * @return PRCH_REQ_DTLTMsg
     */
    private PRCH_REQ_DTLTMsg getDtlTMsgForUpdateShipped(NPZC103001PMsg pMsg, int idx) {

        NPZC103001_prchReqInfoPMsg dtlInfo = pMsg.prchReqInfo.no(idx);
        PRCH_REQ_DTLTMsg prchReqDtlTMsg = selectPRCH_REQ_DTLTMsgForUpdate(pMsg, dtlInfo);
        // SO_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.soNum, dtlInfo.soNum);
        // RWS_NUM
        // QC#57365 Add
        if (ZYPCommonFunc.hasValue(dtlInfo.rwsNum)) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rwsNum, dtlInfo.rwsNum);
        }
        // PRO_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.proNum, dtlInfo.proNum);
        // FRT_COND_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.frtCondCd, dtlInfo.frtCondCd);
        // SHPG_SVC_LVL_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shpgSvcLvlCd, dtlInfo.shpgSvcLvlCd);
        // CARR_CD
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.carrCd, dtlInfo.carrCd);
        // SHIP_DT_TM_TS
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shipDtTmTs, dtlInfo.shipDtTmTs);
        // SHIP_QTY
        if (!ZYPCommonFunc.hasValue(dtlInfo.shipQty)) {
            dtlInfo.shipQty.setValue(BigDecimal.ZERO);
        }
        // QC#14640
        // QC#57365 Mod
        BigDecimal totShipQty = culcShipQty(prchReqDtlTMsg.prchReqNum.getValue(), prchReqDtlTMsg.prchReqLineNum.getValue(), prchReqDtlTMsg.prchReqLineSubNum.getValue());
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shipQty, totShipQty);
//        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shipQty, dtlInfo.shipQty.getValue());
        // PRCH_REQ_LINE_STS_CD
        // QC#56317
        if (prchReqDtlTMsg.prchReqQty.getValue().compareTo(prchReqDtlTMsg.shipQty.getValue()) <= 0) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.SHIPPED);
        } else {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.PARTIALLY_SHIPPED);
        }
        return prchReqDtlTMsg;
    }

    /**
     * getDtlTMsgForUpdateReceived
     * @param pMsg NPZC103001PMsg
     * @param idx int
     * @return PRCH_REQ_DTLTMsg
     */
    private PRCH_REQ_DTLTMsg getDtlTMsgForUpdateReceived(NPZC103001PMsg pMsg, int idx) {

        NPZC103001_prchReqInfoPMsg dtlInfo = pMsg.prchReqInfo.no(idx);
        PRCH_REQ_DTLTMsg prchReqDtlTMsg = selectPRCH_REQ_DTLTMsgForUpdate(pMsg, dtlInfo);
        // RWS_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rwsNum, dtlInfo.rwsNum);
        // RWS_CLOSE_DT_TM_TS
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rwsCloDtTmTs, dtlInfo.rwsCloDtTmTs);
        // RWS_PUT_AWAY_QTY
        if (!ZYPCommonFunc.hasValue(dtlInfo.rwsPutAwayQty)) {
            dtlInfo.rwsPutAwayQty.setValue(BigDecimal.ZERO);
        }
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rwsPutAwayQty, prchReqDtlTMsg.rwsPutAwayQty.getValue().add(dtlInfo.rwsPutAwayQty.getValue()));
        // QC#17049 Add.
        BigDecimal oldShipQty = prchReqDtlTMsg.shipQty.getValue();
        if (oldShipQty.compareTo(BigDecimal.ZERO) == 0) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shipQty, prchReqDtlTMsg.rwsPutAwayQty);
        } else if (oldShipQty.compareTo(prchReqDtlTMsg.rwsPutAwayQty.getValue()) < 0) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shipQty, prchReqDtlTMsg.rwsPutAwayQty);
            // QC#25886
//        } else if (BigDecimal.ZERO.compareTo(dtlInfo.rwsPutAwayQty.getValue()) != 0) {
//            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shipQty, dtlInfo.rwsPutAwayQty.getValue());
        }

        // BACK_TO_TECH_QTY
        if (!ZYPCommonFunc.hasValue(dtlInfo.backToTechQty)) {
            dtlInfo.backToTechQty.setValue(BigDecimal.ZERO);
        }
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.backToTechQty, prchReqDtlTMsg.backToTechQty.getValue().add(dtlInfo.backToTechQty.getValue()));
        // PRCH_REQ_LINE_STS_CD
        // 2017/11/13 M.Naito Mod QC#22501 START
        // Close RWS
        if (BigDecimal.ZERO.compareTo(dtlInfo.rwsPutAwayQty.getValue()) == 0) {
            if (BigDecimal.ZERO.compareTo(prchReqDtlTMsg.rwsPutAwayQty.getValue()) == 0) {
                // START 2018/12/04 M.Naito [QC#29320,MOD]
//              ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.CANCELLED);
                if (BigDecimal.ZERO.compareTo(dtlInfo.backToTechQty.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.CLOSED);
                // START 2018/12/10 M.Naito [QC#29320-1,MOD]
                } else {
                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.RECEIVED);
                }
                // END 2018/12/10 M.Naito [QC#29320-1,MOD]
                // END 2018/12/04 M.Naito [QC#29320,MOD]
            } else {
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.RECEIVED);
            }

            BigDecimal cancelQty = prchReqDtlTMsg.shipQty.getValue().subtract(prchReqDtlTMsg.rwsPutAwayQty.getValue());
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCancQty, prchReqDtlTMsg.prchReqCancQty.getValue().add(cancelQty));
            return prchReqDtlTMsg;
        }

        BigDecimal rcvQty = prchReqDtlTMsg.rwsPutAwayQty.getValue().add(prchReqDtlTMsg.backToTechQty.getValue());
        // QC#56317
        if (prchReqDtlTMsg.prchReqQty.getValue().subtract(prchReqDtlTMsg.prchReqCancQty.getValue()).compareTo(rcvQty) <= 0) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.RECEIVED);
        } else {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.PARTIALLY_RECEIVED);
        }
        // 2017/11/13 M.Naito Mod QC#22501 END
        return prchReqDtlTMsg;
    }

    /**
     * getDtlTMsgForUpdateUpdate
     * @param pMsg NPZC103001PMsg
     * @param idx int
     * @return PRCH_REQ_DTLTMsg
     */
    private PRCH_REQ_DTLTMsg getDtlTMsgForUpdateUpdate(NPZC103001PMsg pMsg, int idx) {

        NPZC103001_prchReqInfoPMsg dtlInfo = pMsg.prchReqInfo.no(idx);
        PRCH_REQ_DTLTMsg prchReqDtlTMsg = selectPRCH_REQ_DTLTMsgForUpdate(pMsg, dtlInfo);
        // RWS_NUM
        // QC#57365 Add
        if (ZYPCommonFunc.hasValue(dtlInfo.rwsNum)) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rwsNum, dtlInfo.rwsNum);
        }
        // QC#21316 Add.
        if (ZYPCommonFunc.hasValue(dtlInfo.prchReqQty)) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqQty, dtlInfo.prchReqQty);
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqBalQty, dtlInfo.prchReqQty);
        }
        if (ZYPCommonFunc.hasValue(dtlInfo.prchReqLineStsCd)) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, dtlInfo.prchReqLineStsCd);
        }
        if (ZYPCommonFunc.hasValue(dtlInfo.prchReqLineCmntTxt)) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineCmntTxt, dtlInfo.prchReqLineCmntTxt);
        }

        return prchReqDtlTMsg;
    }

    /** QC#17049 Add.
     * getDtlTMsgForUpdateAsn
     * @param pMsg NPZC103001PMsg
     * @param idx int
     * @return PRCH_REQ_DTLTMsg
     */
    private PRCH_REQ_DTLTMsg getDtlTMsgForUpdateAsn(NPZC103001PMsg pMsg, int idx) {

        NPZC103001_prchReqInfoPMsg dtlInfo = pMsg.prchReqInfo.no(idx);
        PRCH_REQ_DTLTMsg prchReqDtlTMsg = selectPRCH_REQ_DTLTMsgForUpdate(pMsg, dtlInfo);
        // RWS_NUM
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.rwsNum, dtlInfo.rwsNum);
        // SHIP_QTY
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.shipQty, prchReqDtlTMsg.shipQty.getValue().add(dtlInfo.shipQty.getValue()));

        // START 2019/12/20 M.Naito [QC#54908,ADD]
        if (isTechRequestLine(pMsg.glblCmpyCd.getValue(), prchReqDtlTMsg.prchReqLineTpCd.getValue())) {
            if (prchReqDtlTMsg.prchReqQty.getValue().compareTo(prchReqDtlTMsg.shipQty.getValue()) <= 0) {
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.SHIPPED);
            // QC#56317
            } else if (BigDecimal.ZERO.compareTo(prchReqDtlTMsg.shipQty.getValue()) < 0) {
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.PARTIALLY_SHIPPED);
            }
        }
        // END 2019/12/20 M.Naito [QC#54908,ADD]
        return prchReqDtlTMsg;
    }

    /**
     * getDtlTMsgForUpdateCancel
     * @param pMsg NPZC103001PMsg
     * @param idx int
     * @return PRCH_REQ_DTLTMsg
     */
    private PRCH_REQ_DTLTMsg getDtlTMsgForUpdateCancel(NPZC103001PMsg pMsg, int idx) {

        // Cancel - Shipped
        NPZC103001_prchReqInfoPMsg dtlInfo = pMsg.prchReqInfo.no(idx);
        PRCH_REQ_DTLTMsg prchReqDtlTMsg = selectPRCH_REQ_DTLTMsgForUpdate(pMsg, dtlInfo);

        // QC#54672 Start
        if (PRCH_REQ_LINE_STS.RECEIVED.equals(prchReqDtlTMsg.prchReqLineStsCd.getValue())) {
            return prchReqDtlTMsg;
        }
        // PRCH_REQ_CANC_QTY
        // QC#14640
        if (!ZYPCommonFunc.hasValue(dtlInfo.rwsPutAwayQty)) {
            ZYPEZDItemValueSetter.setValue(dtlInfo.rwsPutAwayQty, BigDecimal.ZERO);
        }
        if (ZYPCommonFunc.hasValue(dtlInfo.shipQty)) {
            if (prchReqDtlTMsg.prchReqQty.getValue().compareTo(dtlInfo.shipQty.getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCancQty, prchReqDtlTMsg.prchReqCancQty.getValue().add(dtlInfo.shipQty.getValue().subtract(dtlInfo.rwsPutAwayQty.getValue())));
            } else {
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCancQty, prchReqDtlTMsg.prchReqQty.getValue().subtract(dtlInfo.rwsPutAwayQty.getValue()));
            }
        // QC#54672 End
        } else {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCancQty, prchReqDtlTMsg.prchReqCancQty.getValue().add(prchReqDtlTMsg.shipQty.getValue()));
        }
        // PRCH_REQ_LINE_STS_CD
        if (prchReqDtlTMsg.prchReqCancQty.getValue().compareTo(prchReqDtlTMsg.prchReqQty.getValue()) == 0) {
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.CANCELLED);
        }
        // QC#55615
        if (ZYPCommonFunc.hasValue(prchReqDtlTMsg.prchReqBalQty) //
                && ZYPCommonFunc.hasValue(prchReqDtlTMsg.prchReqCancQty)
                && BigDecimal.ZERO.compareTo(prchReqDtlTMsg.prchReqBalQty.getValue()) < 0) {

            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqBalQty, prchReqDtlTMsg.prchReqBalQty.getValue().subtract(prchReqDtlTMsg.prchReqCancQty.getValue()));

        }
        
        return prchReqDtlTMsg;
    }

    /**
     * getDtlTMsgListForUpdateCancel
     * @param pMsg NPZC103001PMsg
     * @param updateMode String
     * @return List<PRCH_REQ_DTLTMsg>
     */
    private List<PRCH_REQ_DTLTMsg> getDtlTMsgListForUpdateCancel(NPZC103001PMsg pMsg, String updateMode) {

        // QC#56867
        List<PRCH_REQ_DTLTMsg> prchReqDtlTMsgList = new ArrayList<PRCH_REQ_DTLTMsg>();
        if (PRCH_REQ_REC_TP.INVENTORY_REQUEST.equals(pMsg.prchReqRecTpCd.getValue())) {
            for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
                PRCH_REQ_DTLTMsg prLineTMsg = selectPRCH_REQ_DTLTMsgForUpdate(pMsg, pMsg.prchReqInfo.no(i));
                prchReqDtlTMsgList.add(prLineTMsg);
            }   
        } else {
            prchReqDtlTMsgList = selectPRCH_REQ_DTLTMsgForUpdateAll(pMsg, updateMode);
        }
        
        for (PRCH_REQ_DTLTMsg prchReqDtlTMsg : prchReqDtlTMsgList) {
            if (PRCH_REQ_LINE_STS.CANCELLED.equals(prchReqDtlTMsg.prchReqLineStsCd.getValue())) {
                continue;
            }

            // QC#54552 Start
            if (PRCH_REQ_LINE_STS.PARTIALLY_RECEIVED.equals(prchReqDtlTMsg.prchReqLineStsCd.getValue()) || PRCH_REQ_LINE_STS.RECEIVED.equals(prchReqDtlTMsg.prchReqLineStsCd.getValue())) {
                // PRCH_REQ_LINE_STS_CD
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.RECEIVED);
            } else if (PRCH_REQ_LINE_STS.PARTIALLY_SHIPPED.equals(prchReqDtlTMsg.prchReqLineStsCd.getValue()) || PRCH_REQ_LINE_STS.SHIPPED.equals(prchReqDtlTMsg.prchReqLineStsCd.getValue())) {
                // PRCH_REQ_LINE_STS_CD
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.SHIPPED);
            } else {
            	// QC#56543 2020/05/08 Start
                //// PRCH_REQ_LINE_STS_CD
                //ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.CANCELLED);
                //// PRCH_REQ_CANC_QTY
                //ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCancQty, prchReqDtlTMsg.prchReqBalQty);
                //// PRCH_REQ_BAL_QTY
                //ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqBalQty, BigDecimal.ZERO);
                //// PRCH_REQ_REL_STS
                //ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.NO_NEED_TO_RELEASE);
                if(PRCH_REQ_TP.KITTING.equals(pMsg.prchReqTpCd.getValue())) {
                    BigDecimal prchReqQty = prchReqDtlTMsg.prchReqQty.getValue();
                    BigDecimal shipQty = prchReqDtlTMsg.shipQty.getValue();
                    BigDecimal rwsPutAwayQty = prchReqDtlTMsg.rwsPutAwayQty.getValue();
                    BigDecimal prchReqCancQty = prchReqDtlTMsg.prchReqCancQty.getValue();
                    if (BigDecimal.ZERO.compareTo(rwsPutAwayQty) < 0) {
                        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.RECEIVED);
                    } else if (BigDecimal.ZERO.compareTo(shipQty) < 0) {
                        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.SHIPPED);    
                    } else if (prchReqQty.compareTo(prchReqCancQty) == 0) {
                        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.CANCELLED);
                        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqBalQty, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.NO_NEED_TO_RELEASE);
                    } else if (PRCH_REQ_LINE_STS.OPEN.equals(prchReqDtlTMsg.prchReqLineStsCd.getValue())){
                        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.CANCELLED);
                        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCancQty, prchReqQty);
                        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqBalQty, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.NO_NEED_TO_RELEASE);
                    }
                }
                else {
                    // PRCH_REQ_LINE_STS_CD
                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.CANCELLED);
                    // PRCH_REQ_CANC_QTY
                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCancQty, prchReqDtlTMsg.prchReqBalQty);
                    // PRCH_REQ_BAL_QTY
                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqBalQty, BigDecimal.ZERO);
                    // PRCH_REQ_REL_STS
                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.NO_NEED_TO_RELEASE);
                }
                // QC#56543 2020/05/08 End
                

            }
            // QC#54552 End
        }
        return prchReqDtlTMsgList;
    }

    /**
     * getDtlTMsgListForUpdateShipCancel QC#16231
     * @param pMsg NPZC103001PMsg
     * @return List<PRCH_REQ_DTLTMsg>
     */
    private List<PRCH_REQ_DTLTMsg> getDtlTMsgListForUpdateShipCancel(NPZC103001PMsg pMsg) {

        List<PRCH_REQ_DTLTMsg> prdList = new ArrayList<PRCH_REQ_DTLTMsg>();
        for (int idx = 0; idx < pMsg.prchReqInfo.getValidCount(); idx++) {

            NPZC103001_prchReqInfoPMsg prDtlPmsg = pMsg.prchReqInfo.no(idx);
            PRCH_REQ_DTLTMsg info = selectPRCH_REQ_DTLTMsgForUpdate(pMsg.prchReqNum.getValue(), prDtlPmsg.prchReqLineNum.getValue(), prDtlPmsg.prchReqLineSubNum.getValue(), null);

            // PRCH_REQ_CANC_QTY
            ZYPEZDItemValueSetter.setValue(info.prchReqCancQty, prDtlPmsg.shipQty);

            if (info.prchReqQty.getValue().compareTo(info.prchReqCancQty.getValue()) == 0) {

                // PRCH_REQ_LINE_STS_CD
                ZYPEZDItemValueSetter.setValue(info.prchReqLineStsCd, PRCH_REQ_LINE_STS.CANCELLED);

            // 2017/11/13 M.Naito Mod QC#22501 START
            } else if (info.prchReqQty.getValue().compareTo(info.shipQty.getValue().add(info.prchReqCancQty.getValue())) == 0) {
                // QC#54672
                if (BigDecimal.ZERO.compareTo(info.prchReqCancQty.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(info.prchReqLineStsCd, PRCH_REQ_LINE_STS.SHIPPED);
                } else {
                    ZYPEZDItemValueSetter.setValue(info.prchReqLineStsCd, PRCH_REQ_LINE_STS.PARTIALLY_SHIPPED);
                }

            }
            // 2017/11/13 M.Naito Mod QC#22501 END

            // PRCH_REQ_REL_STS
            // QC#56867
            if (PRCH_REQ_LINE_STS.CANCELLED.equals(info.prchReqLineStsCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(info.prchReqRelStsCd, PRCH_REQ_REL_STS.NO_NEED_TO_RELEASE);
            }
            prdList.add(info);
        }
        return prdList;
    }

    /**
     * getDtlTMsgListForUpdateRws QC#6582
     * @param pMsg NPZC103001PMsg
     * @return List<PRCH_REQ_DTLTMsg>
     */
    private List<PRCH_REQ_DTLTMsg> getDtlTMsgListForUpdateRws(NPZC103001PMsg pMsg) {

        List<PRCH_REQ_DTLTMsg> prdList = new ArrayList<PRCH_REQ_DTLTMsg>();
        for (int idx = 0; idx < pMsg.prchReqInfo.getValidCount(); idx++) {

            NPZC103001_prchReqInfoPMsg prDtlPmsg = pMsg.prchReqInfo.no(idx);
            PRCH_REQ_DTLTMsg info = selectPRCH_REQ_DTLTMsgForUpdate(pMsg.prchReqNum.getValue(), prDtlPmsg.prchReqLineNum.getValue(), prDtlPmsg.prchReqLineSubNum.getValue(), null);

            // RWS_NUM
            ZYPEZDItemValueSetter.setValue(info.rwsNum, prDtlPmsg.rwsNum);
            prdList.add(info);
        }

        return prdList;
    }

    /**
     * getDtlTMsgListForUpdateApproval
     * @param pMsg NPZC103001PMsg
     * @return List<PRCH_REQ_DTLTMsg>
     */
    private List<PRCH_REQ_DTLTMsg> getDtlTMsgListForUpdateApproval(NPZC103001PMsg pMsg) {

        List<PRCH_REQ_DTLTMsg> prchReqDtlTMsgList = selectPRCH_REQ_DTLTMsgForUpdateAll(pMsg, NPZC103001Constant.ALL);
        for (PRCH_REQ_DTLTMsg prchReqDtlTMsg : prchReqDtlTMsgList) {

            if (isApprovedStatus(pMsg)) {
                // PO_SCHD_REL_DT
                if (ZYPCommonFunc.hasValue(prchReqDtlTMsg.poSchdRelDt)) {
                    if (ZYPDateUtil.compare(pMsg.procDt.getValue(), prchReqDtlTMsg.poSchdRelDt.getValue()) == 1) {
                        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.poSchdRelDt, pMsg.procDt);
                    }
                // START 2018/10/02 M.Naito [QC#28479,MOD]
//                } else if (ZYPConstant.FLG_ON_Y.equals(getSwLicReqFlg(prchReqDtlTMsg.mdseCd.getValue()))) {
                // QC#29263
                } else if (PO_MDSE_CMPSN_TP.CHILD.equals(prchReqDtlTMsg.poMdseCmpsnTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.poSchdRelDt, pMsg.procDt);
                // START 2023/06/13 T.Kuroda [QC#61363, DEL]
//                } else if (chkSwLicItem(prchReqDtlTMsg.mdseCd.getValue())) {
//                // END 2018/10/02 M.Naito [QC#28479,MOD]
//                    prchReqDtlTMsg.poSchdRelDt.clear();
                // END 2023/06/13 T.Kuroda [QC#61363, DEL]
                } else {
                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.poSchdRelDt, pMsg.procDt);
                }
                // START 2017/05/25 QC#18698 Mod.
                // PRCH_REQ_REL_STS
                if (!PRCH_REQ_LINE_STS.CANCELLED.equals(prchReqDtlTMsg.prchReqLineStsCd.getValue())) {
                    // QC#26662 Update 2018/08/23
                    if (isTechRequest(pMsg) //
                            && !(PRCH_REQ_REL_STS.COMPLEATED.equals(prchReqDtlTMsg.prchReqRelStsCd.getValue()) //
                            || PRCH_REQ_REL_STS.NO_NEED_TO_RELEASE.equals(prchReqDtlTMsg.prchReqRelStsCd.getValue()))) {
                        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.IN_COMPLETED);
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.NO_NEED_TO_RELEASE);
                }

                // END 2017/05/25 QC#18698 Mod.
            // Del Start 2020/03/05 QC#56103
//            } else {
                // PRCH_REQ_REL_STS
//                prchReqDtlTMsg.prchReqRelStsCd.clear();
            // Del End 2020/03/05 QC#56103
            }
        }
        return prchReqDtlTMsgList;
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

        return (PRCH_REQ_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(prntPrchReqDtlTMsg);
    }

    /**
     * getUpdprchReqLineSts
     * @param prntPrchReqDtlTMsg PRCH_REQ_DTLTMsg
     * @return String
     */
    private String getUpdprchReqLineSts(PRCH_REQ_DTLTMsg prntPrchReqDtlTMsg) {

        // Get all child lines
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", prntPrchReqDtlTMsg.glblCmpyCd.getValue());
        queryParam.put("prchReqNum", prntPrchReqDtlTMsg.prchReqNum.getValue());
        queryParam.put("prchReqLineNum", prntPrchReqDtlTMsg.prchReqLineNum.getValue());

        List<Map<String, Object>> prchReqDtlMapList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getPrchReqDtlChild", queryParam);

        // QC#14640
        boolean allCloseFlg = true;
        boolean allCancelFlg = true;
        boolean shippedFlg = false;
        boolean receivedFlg = false;
        // QC#54672 Start
        // Qty check
        BigDecimal totShipQty = BigDecimal.ZERO;
        BigDecimal totRcvQty = BigDecimal.ZERO;
        BigDecimal totChildQty = BigDecimal.ZERO;
        // START 2023/04/26 A.Cullano [QC#61410, ADD]
        BigDecimal totCancQty = BigDecimal.ZERO;
        // END 2023/04/26 A.Cullano [QC#61410, ADD]
        BigDecimal prntPrchReqQty = BigDecimal.ZERO;
        String cloPrchReqLineStsCd = PRCH_REQ_LINE_STS.CLOSED;
        boolean isReturnPrnt = false;
        boolean isMissingLine = false;

        // START 2019/12/20 M.Naito [QC#54908,MOD]
        // Check child lines if all close or all cancel
        for (Map<String, Object> prchReqDtlMap : prchReqDtlMapList) {

            String prchReqLineStsCd = (String) prchReqDtlMap.get("PRCH_REQ_LINE_STS_CD");
            cloPrchReqLineStsCd = (String) prchReqDtlMap.get("CLO_PRCH_REQ_LINE_STS_CD");
            // Qty Check
            totShipQty = totShipQty.add((BigDecimal) prchReqDtlMap.get("SHIP_QTY"));
            totRcvQty = totRcvQty.add((BigDecimal) prchReqDtlMap.get("RWS_PUT_AWAY_QTY"));
            totChildQty = totChildQty.add((BigDecimal) prchReqDtlMap.get("PRCH_REQ_QTY"));
            // START 2023/04/26 A.Cullano [QC#61410, ADD]
            totCancQty = totCancQty.add((BigDecimal) prchReqDtlMap.get("PRCH_REQ_CANC_QTY"));
            // END 2023/04/26 A.Cullano [QC#61410, ADD]
            prntPrchReqQty = (BigDecimal) prchReqDtlMap.get("PRNT_PRCH_REQ_QTY");
            String lineTpCd = (String) prchReqDtlMap.get("PRCH_REQ_LINE_TP_CD");

            if (PRCH_REQ_LINE_TP.LOCAL_RETURN.equals(prntPrchReqDtlTMsg.prchReqLineTpCd.getValue()) //
                    || PRCH_REQ_LINE_TP.DEFECTIVE_RETURN.equals(prntPrchReqDtlTMsg.prchReqLineTpCd.getValue()) //
                    || PRCH_REQ_LINE_TP.USED_LOCAL_RETURN.equals(prntPrchReqDtlTMsg.prchReqLineTpCd.getValue())) {

                if (!isReturnPrnt) {
                    totRcvQty = totRcvQty.add(prntPrchReqDtlTMsg.rwsPutAwayQty.getValue().add(prntPrchReqDtlTMsg.prchReqCancQty.getValue()));
                    isReturnPrnt = true;
                }
            }

            if (NPZC103001Constant.EVENT_SHIPPED.equals(this.event) && PRCH_REQ_LINE_TP.MISSING_PARTS.equals(lineTpCd) && !isMissingLine) {
                totShipQty = totShipQty.add(prntPrchReqDtlTMsg.shipQty.getValue());
                isMissingLine = true;
            }
            if (NPZC103001Constant.EVENT_RECEIVED.equals(this.event) && PRCH_REQ_LINE_TP.MISSING_PARTS.equals(lineTpCd) && !isMissingLine) {
                totRcvQty = totRcvQty.add(prntPrchReqDtlTMsg.rwsPutAwayQty.getValue());
                isMissingLine = true;
            }

            // Shipped event
//            if (NPZC103001Constant.EVENT_SHIPPED.equals(this.event)) {
            if (NPZC103001Constant.EVENT_SHIPPED.equals(this.event) || NPZC103001Constant.EVENT_ASN.equals(this.event)) {
                cloPrchReqLineStsCd = PRCH_REQ_LINE_STS.SHIPPED;
            }

            // check all close
            if (!PRCH_REQ_LINE_STS.CANCELLED.equals(prchReqLineStsCd) && !cloPrchReqLineStsCd.equals(prchReqLineStsCd)) {
                allCloseFlg = false;
            }

            if (!PRCH_REQ_LINE_STS.CANCELLED.equals(prchReqLineStsCd)) {

                allCancelFlg = false;

                if (PRCH_REQ_LINE_STS.PARTIALLY_SHIPPED.equals(prchReqLineStsCd) || PRCH_REQ_LINE_STS.SHIPPED.equals(prchReqLineStsCd)) {

                    shippedFlg = true;

                } else if (PRCH_REQ_LINE_STS.PARTIALLY_RECEIVED.equals(prchReqLineStsCd) || PRCH_REQ_LINE_STS.RECEIVED.equals(prchReqLineStsCd)) {

                    receivedFlg = true;
                }
            }
        }

        // QC#54672 Qty Check
//        if (NPZC103001Constant.EVENT_SHIPPED.equals(this.event) && prntPrchReqQty.compareTo(totShipQty) <= 0) {
        if ((NPZC103001Constant.EVENT_SHIPPED.equals(this.event) || NPZC103001Constant.EVENT_ASN.equals(this.event)) && prntPrchReqQty.compareTo(totShipQty) <= 0) {
            cloPrchReqLineStsCd = PRCH_REQ_LINE_STS.SHIPPED;
            allCloseFlg = true;
//        } else if (NPZC103001Constant.EVENT_SHIPPED.equals(this.event) && prntPrchReqQty.compareTo(totShipQty) != 0) {
        } else if ((NPZC103001Constant.EVENT_SHIPPED.equals(this.event) || NPZC103001Constant.EVENT_ASN.equals(this.event)) && prntPrchReqQty.compareTo(totShipQty) != 0) {
            // START 2023/04/26 A.Cullano [QC#61410, MOD]
            // allCloseFlg = false;
            BigDecimal totalQtyCheck = totShipQty.add(totCancQty);
            if (prntPrchReqQty.compareTo(totalQtyCheck) == 0) {
                cloPrchReqLineStsCd = PRCH_REQ_LINE_STS.RECEIVED;
                allCloseFlg = true;
            } else {
                allCloseFlg = false;
            }
            // END 2023/04/26 A.Cullano [QC#61410, MOD]
        }  else if (NPZC103001Constant.EVENT_RECEIVED.equals(this.event) && prntPrchReqQty.compareTo(totRcvQty) <= 0) {
            cloPrchReqLineStsCd = PRCH_REQ_LINE_STS.RECEIVED;
            allCloseFlg = true;
        } else if (NPZC103001Constant.EVENT_RECEIVED.equals(this.event) && prntPrchReqQty.compareTo(totRcvQty) != 0) {
            allCloseFlg = false;
        }

        if (allCancelFlg) {

            return PRCH_REQ_LINE_STS.CANCELLED;

        } else if (allCloseFlg) {

            return cloPrchReqLineStsCd;
        }

//        if (NPZC103001Constant.EVENT_SHIPPED.equals(this.event) && shippedFlg) {
        if ((NPZC103001Constant.EVENT_SHIPPED.equals(this.event) || NPZC103001Constant.EVENT_ASN.equals(this.event)) && shippedFlg) {
            return PRCH_REQ_LINE_STS.PARTIALLY_SHIPPED;
        } else if (NPZC103001Constant.EVENT_RECEIVED.equals(this.event) && receivedFlg) {
            return PRCH_REQ_LINE_STS.PARTIALLY_RECEIVED;
        } else {
            return prntPrchReqDtlTMsg.prchReqLineStsCd.getValue();
        }
        // END 2019/12/20 M.Naito [QC#54908,MOD]
    }

    /**
     * SELECT PRCH_REQ FOR UPDATE
     * @param pMsg NPZC103001PMsg
     * @return selected PRCH_REQTMsg
     */
    private PRCH_REQTMsg selectPRCH_REQTMsgForUpdate(NPZC103001PMsg pMsg) {

        return selectPRCH_REQTMsgForUpdate(pMsg.prchReqNum.getValue());
    }

    /**
     * SELECT PRCH_REQ
     * @param prchReqNum String
     * @return selected PRCH_REQTMsg
     */
    private PRCH_REQTMsg selectPRCH_REQTMsg(String prchReqNum) {

        PRCH_REQTMsg inMsg = new PRCH_REQTMsg();
        inMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        inMsg.prchReqNum.setValue(prchReqNum);

        PRCH_REQTMsg tMsg = (PRCH_REQTMsg) EZDTBLAccessor.findByKey(inMsg);
        return tMsg;
    }

    /**
     * SELECT PRCH_REQ FOR UPDATE
     * @param prchReqNum String
     * @return selected PRCH_REQTMsg
     */
    private PRCH_REQTMsg selectPRCH_REQTMsgForUpdate(String prchReqNum) {

        PRCH_REQTMsg inMsg = new PRCH_REQTMsg();
        inMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        inMsg.prchReqNum.setValue(prchReqNum);

        PRCH_REQTMsg tMsg = (PRCH_REQTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
        return tMsg;
    }

    /**
     * Select an open PRCH_REQ_DTLTMsg for update.
     * @param pMsg NPZC103001PMsg
     * @param msg NPZC103001_prchReqInfoPMsg
     * @return PRCH_REQ_DTLTMsg
     */
    private PRCH_REQ_DTLTMsg selectPRCH_REQ_DTLTMsgForUpdate(NPZC103001PMsg pMsg, NPZC103001_prchReqInfoPMsg msg) {

        return selectPRCH_REQ_DTLTMsgForUpdate(pMsg.prchReqNum.getValue(), msg.prchReqLineNum.getValue(), msg.prchReqLineSubNum.getValue(), null);
    }

    /**
     * Select an open PRCH_REQ_DTLTMsg for update.
     * @param prchReqNum String
     * @param prLineNum String
     * @param prLineSubNum BigDecimal
     * @param prchReqLineStsCdExcludeList List<String>
     * @return PRCH_REQ_DTLTMsg
     */
    private PRCH_REQ_DTLTMsg selectPRCH_REQ_DTLTMsgForUpdate(String prchReqNum, String prLineNum, BigDecimal prLineSubNum, List<String> prchReqLineStsCdExcludeList) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("updFlg", "updFlg");
        params.put("noWaitFlg", "noWaitFlg");
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("prchReqNum", prchReqNum);
        params.put("prchReqLineNum", prLineNum);
        params.put("prchReqLineSubNum", prLineSubNum);

        if (prchReqLineStsCdExcludeList != null && !prchReqLineStsCdExcludeList.isEmpty()) {
            params.put("prchReqLineStsCdExcludeList", prchReqLineStsCdExcludeList.toArray(new String[prchReqLineStsCdExcludeList.size()]));
        }

        List<PRCH_REQ_DTLTMsg> tMsgs = (List<PRCH_REQ_DTLTMsg>) this.ssmBatchClient.queryObjectList("getPrchReqDtl", params);
        if (tMsgs != null && !tMsgs.isEmpty()) {
            return tMsgs.get(0);
        }
        return null;
    }

    /**
     * selectPRCH_REQ_DTLTMsg
     * @param pMsg NPZC103001PMsg
     * @return List<PRCH_REQ_DTLTMsg>
     */
    private List<PRCH_REQ_DTLTMsg> selectPRCH_REQ_DTLTMsg(NPZC103001PMsg pMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("updFlg", "updFlg");
        queryParam.put("noWaitFlg", "noWaitFlg");
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        queryParam.put("prchReqNum", pMsg.prchReqNum.getValue());
        List<String> prchLineNum = new ArrayList<String>();
        for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
            if (!prchLineNum.contains(pMsg.prchReqInfo.no(i).prchReqLineNum.getValue())) {
                prchLineNum.add(pMsg.prchReqInfo.no(i).prchReqLineNum.getValue());
            }
        }
        if (!prchLineNum.isEmpty()) {
            queryParam.put("prchReqLineNums", prchLineNum.toArray(new String[prchLineNum.size()]));
        }

        return (List<PRCH_REQ_DTLTMsg>) this.ssmBatchClient.queryObjectList("getPrchReqDtl", queryParam);
    }

    /**
     * selectPRCH_REQ_DTLTMsgForUpdateOrig
     * @param prNum String
     * @param mdseCd String
     * @param prchReqTp String
     * @param prchReqQty BigDecimal
     * @return List<PRCH_REQ_DTLTMsg>
     */
    private List<PRCH_REQ_DTLTMsg> selectPRCH_REQ_DTLTMsgForUpdateOrig(String prNum, String mdseCd, String prchReqTp, BigDecimal prchReqQty) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("noWaitFlg", "noWaitFlg");
        queryParam.put("updFlg", "updFlg");
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("prchReqNum", prNum);
        // QC#21771
        if (ZYPCommonFunc.hasValue(prchReqQty)) {
            queryParam.put("prchReqQty", prchReqQty);
        }
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("prchReqLineStsCdExcludeList", new String[] {PRCH_REQ_LINE_STS.CLOSED, PRCH_REQ_LINE_STS.CANCELLED });
        queryParam.put("tpShipConf", ZYPConstant.FLG_ON_Y);

        // QC#21771
        if (!PRCH_REQ_TP.PREMIUM_RUSH.equals(prchReqTp)) {
            String prLineTp = ZYPCodeDataUtil.getVarCharConstValue(NPZC103001Constant.TP_SHIP_CONF_CANC_LINE_TP, this.glblCmpyCd);
            if (ZYPCommonFunc.hasValue(prLineTp)) {
                queryParam.put("prchReqLineTpCdList", prLineTp.split(NPZC103001Constant.DELIMITER));
            } else {
                queryParam.put("prchReqLineTpCdList", new String[] {PRCH_REQ_LINE_TP.INSOURCED_CH });
            }
        }

        if (PRCH_REQ_TP.PREMIUM_RUSH.equals(prchReqTp)) {
            String prLineTp = ZYPCodeDataUtil.getVarCharConstValue("P_RUSH_SHIP_CONF_CANC_LINE_TP", this.glblCmpyCd);
            if (ZYPCommonFunc.hasValue(prLineTp)) {
                queryParam.put("prchReqLineTpCdList", prLineTp.split(NPZC103001Constant.DELIMITER));
            } else {
                queryParam.put("prchReqLineTpCdList", new String[] {PRCH_REQ_LINE_TP.INSOURCED_ST });
            }
        }

        return (List<PRCH_REQ_DTLTMsg>) this.ssmBatchClient.queryObjectList("getPrchReqDtl", queryParam);
    }

    //Sol#369(QC#19243) K.Kameoka Update Start
    /**
     * selectPRCH_REQ_DTLTMsgForUpdateOrig
     * @param prNum String
     * @param mdseCd String
     * @return List<PRCH_REQ_DTLTMsg>
     */
    private List<PRCH_REQ_DTLTMsg> selectPRCH_REQ_DTLTMsgForUpdatePrnt(String prNum, String mdseCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("noWaitFlg", "noWaitFlg");
        queryParam.put("updFlg", "updFlg");
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("prchReqNum", prNum);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("prchReqLineStsCdExcludeList", new String[] {PRCH_REQ_LINE_STS.CLOSED, PRCH_REQ_LINE_STS.CANCELLED });

        return (List<PRCH_REQ_DTLTMsg>) this.ssmBatchClient.queryObjectList("getPrchReqDtlPrnt", queryParam);
    }
    //Sol#369(QC#19243) K.Kameoka Update End
    
    /**
     * selectPRCH_REQ_DTLTMsgForUpdateAll
     * @param pMsg NPZC103001PMsg
     * @param updateMode String
     * @return List<PRCH_REQ_DTLTMsg>
     */
    private List<PRCH_REQ_DTLTMsg> selectPRCH_REQ_DTLTMsgForUpdateAll(NPZC103001PMsg pMsg, String updateMode) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("updFlg", "updFlg");
        queryParam.put("noWaitFlg", "noWaitFlg");
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        queryParam.put("prchReqNum", pMsg.prchReqNum.getValue());
        if (NPZC103001Constant.LINE.equals(updateMode)) {
            List<String> prchLineNum = new ArrayList<String>();
            for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
                if (!prchLineNum.contains(pMsg.prchReqInfo.no(i).prchReqLineNum.getValue())) {
                    prchLineNum.add(pMsg.prchReqInfo.no(i).prchReqLineNum.getValue());
                }
            }
            if (!prchLineNum.isEmpty()) {
                queryParam.put("prchReqLineNums", prchLineNum.toArray(new String[prchLineNum.size()]));
            }
        }

        return (List<PRCH_REQ_DTLTMsg>) this.ssmBatchClient.queryObjectList("getPrchReqDtl", queryParam);
    }

    /**
     * SELECT PRCH_REQ_SER
     * @param prchReqNum String
     * @param prLineNum String
     * @param prLineSubNum BigDecimal
     * @return selected PRCH_REQ_SERTMsg
     */
    private List<PRCH_REQ_SERTMsg> selectPRCH_REQ_SERTMsg(String prchReqNum, String prLineNum, BigDecimal prLineSubNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("prchReqNum", prchReqNum);
        params.put("prchReqLineNum", prLineNum);
        params.put("prchReqLineSubNum", prLineSubNum);

        List<PRCH_REQ_SERTMsg> tMsgs = (List<PRCH_REQ_SERTMsg>) this.ssmBatchClient.queryObjectList("getPrchReqSer", params);
        if (tMsgs != null && !tMsgs.isEmpty()) {
            return tMsgs;
        }
        return null;
    }

    /**
     * Get Config PRCH_REQ_DTLTMsg
     * @param prchReqNum String
     * @return List<PRCH_REQ_DTLTMsg>
     */
    private List<PRCH_REQ_DTLTMsg> getConfigPrDetail(String prchReqNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("prchReqNum", prchReqNum);

        return (List<PRCH_REQ_DTLTMsg>) this.ssmBatchClient.queryObjectList("getConfigPrchReqDtl", params);
    }

    /**
     * check Config item status PRCH_REQ_DTLTMsg
     * @param prchReqNum String
     * @param prchReqLineNum String
     * @return String
     */
    private String checkConfigItemPrDtl(String prchReqNum, String prchReqLineNum) {

        String configItemStsCd = "";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("prchReqNum", prchReqNum);
        params.put("prchReqLineNum", prchReqLineNum);

        List<PRCH_REQ_DTLTMsg> configItemStslList = (List<PRCH_REQ_DTLTMsg>) this.ssmBatchClient.queryObjectList("getConfigPrchReqDtl", params);

        String preConfigItemStsCd = "";
        int validCount = 0;

        for (int i = 0; i < configItemStslList.size(); i++) {
            configItemStsCd = configItemStslList.get(i).prchReqLineStsCd.getValue();
            if (ZYPCommonFunc.hasValue(configItemStsCd) && configItemStsCd.equals(preConfigItemStsCd) || !ZYPCommonFunc.hasValue(preConfigItemStsCd)) {
                validCount++;
            } else {
                validCount = 0;
            }
            preConfigItemStsCd = configItemStsCd;
        }

        if (validCount != configItemStslList.size()) {
            configItemStsCd = "";
        }

        return configItemStsCd;
    }

    /**
     * tpShipConf
     * @param pMsg NPZC103001PMsg
     */
    private void tpShipConf(NPZC103001PMsg pMsg) {

        List<String> prNumList = new ArrayList<String>();
        String rqstFromShpgConfFlg = ZYPConstant.FLG_OFF_N;
        // ProcDt
        String procDt = null;
        if (ZYPCommonFunc.hasValue(pMsg.procDt)) {
            procDt = pMsg.procDt.getValue();
        } else {
            procDt = ZYPDateUtil.getCurrentSystemTime(NPZC103001Constant.DATE_PATTERN);
        }

        for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
            NPZC103001_prchReqInfoPMsg dtlInfo = pMsg.prchReqInfo.no(i);
            // Get Original PR Number
            if (!prNumList.contains(dtlInfo.trxRefNum.getValue())) {
                prNumList.add(dtlInfo.trxRefNum.getValue());
            }
        }
        if (prNumList.isEmpty()) {
            return;
        }
        for (String prNum : prNumList) {
            // PR Header
            PRCH_REQTMsg prHdrTMsg = null;
            // Original Lines
            List<PRCH_REQ_DTLTMsg> origDtlList = new ArrayList<PRCH_REQ_DTLTMsg>();
            // Insert Lines
            List<PRCH_REQ_DTLTMsg> newDtlList = new ArrayList<PRCH_REQ_DTLTMsg>();
            if (BigDecimal.ZERO.compareTo(getTpShipConfOrig(prNum)) == 0) {
                // Insert Original Header
                PRCH_REQTMsg prchReqTMsg = new PRCH_REQTMsg();
                prHdrTMsg = getHdrTMsgForTpShipConf(prchReqTMsg, pMsg, prNum);
                S21ApiTBLAccessor.insert(prHdrTMsg);
                if (prHdrTMsg != null && !EZDTBLAccessor.RTNCD_NORMAL.equals(prHdrTMsg.getReturnCode())) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0099E);
                    return;
                }
                rqstFromShpgConfFlg = ZYPConstant.FLG_ON_Y;
                // Insert Line
                for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
                    NPZC103001_prchReqInfoPMsg dtlInfo = pMsg.prchReqInfo.no(i);
                    if (!prNum.equals(dtlInfo.trxRefNum.getValue())) {
                        continue;
                    }
                    // WH_OWNR_CD
                    Map<String, String> whOwnrMap = getWhOwnrCd(dtlInfo.srcInvtyLocCd.getValue(), procDt);
                    String whOwnr = null;
                    if (whOwnrMap != null) {
                        whOwnr = whOwnrMap.get(NPZC103001Constant.WH_OWNR_CD);
                    }
                    // PRCH_REQ_LINE_TP
                    String prLineTpCd = null;
                    Map<String, String> prLineTpMap = getPrchReqLineTpCd(rqstFromShpgConfFlg, whOwnr);
                    if (prLineTpMap != null) {
                        prLineTpCd = prLineTpMap.get(NPZC103001Constant.PRCH_REQ_LINE_TP_CD);
                    }
                    ZYPEZDItemValueSetter.setValue(dtlInfo.prchReqLineTpCd, prLineTpCd);
                    PRCH_REQ_DTLTMsg newDtl = getDtlTMsgForTpInsert(pMsg, i);
                    S21ApiTBLAccessor.insert(newDtl);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newDtl.getReturnCode())) {
                        this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0289E);
                        return;
                    }
                    newDtlList.add(newDtl);
                }
            } else {
                // Update Original Header
                prHdrTMsg = selectPRCH_REQTMsgForUpdate(prNum);
                // QC#30817
                if (ZYPConstant.FLG_OFF_N.equals(prHdrTMsg.vndDropShipFlg.getValue())) {
                    // SHIP_TO_LOC_NM
                    ZYPEZDItemValueSetter.setValue(prHdrTMsg.shipToLocNm, pMsg.shipToLocNm);
                    // SHIP_TO_FIRST_LINE_ADDR
                    ZYPEZDItemValueSetter.setValue(prHdrTMsg.shipToFirstLineAddr, pMsg.shipToFirstLineAddr);
                    // SHIP_TO_SCD_LINE_ADDR
                    ZYPEZDItemValueSetter.setValue(prHdrTMsg.shipToScdLineAddr, pMsg.shipToScdLineAddr);
                    // SHIP_TO_THIRD_LINE_ADDR
                    ZYPEZDItemValueSetter.setValue(prHdrTMsg.shipToThirdLineAddr, pMsg.shipToThirdLineAddr);
                    // SHIP_TO_FRTH_LINE_ADDR
                    ZYPEZDItemValueSetter.setValue(prHdrTMsg.shipToFrthLineAddr, pMsg.shipToFrthLineAddr);
                    // SHIP_TO_CTY_ADDR
                    ZYPEZDItemValueSetter.setValue(prHdrTMsg.shipToCtyAddr, pMsg.shipToCtyAddr);
                    // SHIP_TO_ST_CD
                    ZYPEZDItemValueSetter.setValue(prHdrTMsg.shipToStCd, pMsg.shipToStCd);
                    // SHIP_TO_POST_CD
                    ZYPEZDItemValueSetter.setValue(prHdrTMsg.shipToPostCd, pMsg.shipToPostCd);
                    // SHIP_TO_CTRY_CD
                    ZYPEZDItemValueSetter.setValue(prHdrTMsg.shipToCtryCd, pMsg.shipToCtryCd);
                    // CTAC_PSN_NM
                    ZYPEZDItemValueSetter.setValue(prHdrTMsg.ctacPsnNm, pMsg.ctacPsnNm);
                    
                }
                // VND_DROP_SHIP_FLG
                ZYPEZDItemValueSetter.setValue(prHdrTMsg.vndDropShipFlg, ZYPConstant.FLG_ON_Y);
                S21ApiTBLAccessor.update(prHdrTMsg);
                if (prHdrTMsg != null && !EZDTBLAccessor.RTNCD_NORMAL.equals(prHdrTMsg.getReturnCode())) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0100E);
                    return;
                }
                setupPMsgforUpdate(pMsg, prHdrTMsg);
                // Insert New Line
                for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
                    NPZC103001_prchReqInfoPMsg dtlInfo = pMsg.prchReqInfo.no(i);
                    // QC#21771
                    // Original Lines
                    // QC#51148 Start
                    String origMdseCd = dtlInfo.mdseCd.getValue();
                    String mdseCd = dtlInfo.mdseCd.getValue();
                    List<PRCH_REQ_DTLTMsg> cancDtlList = selectPRCH_REQ_DTLTMsgForUpdateOrig(prNum, mdseCd, prHdrTMsg.prchReqTpCd.getValue(), dtlInfo.prchReqQty.getValue());

                    // Choice SUPD_TO_MDSE_CD / PR SUPD_FROM_MDSE_CD
                    if (cancDtlList == null || cancDtlList.isEmpty()) {
                        // check original item and insoucing choice line
                        cancDtlList = selectPRCH_REQ_DTLTMsgForUpdateOrig(prNum, mdseCd, prHdrTMsg.prchReqTpCd.getValue(), null);
                        if (cancDtlList == null || cancDtlList.isEmpty()) {
                            int cnt = 0;
                            // check supd from item
                            while (true) {
                                if (cnt == 10) {
                                    break;
                                }
                                cnt++;
                                List<Map<String, Object>> supdRelnFromIncgFlgNResultList = getSupdInfoFromIncgFlgN(mdseCd);
                                if (supdRelnFromIncgFlgNResultList.size() >= 1) {
                                    mdseCd = (String) supdRelnFromIncgFlgNResultList.get(0).get("SUPD_FROM_MDSE_CD");
                                    if (!ZYPCommonFunc.hasValue(mdseCd)) {
                                        break;
                                    }
                                    cancDtlList = selectPRCH_REQ_DTLTMsgForUpdateOrig(prNum, mdseCd, prHdrTMsg.prchReqTpCd.getValue(), null);
                                    if (cancDtlList == null || cancDtlList.isEmpty()) {
                                        continue;
                                    } else {
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                    }

                    // QC#51553 Start
                    // Choice SUPD_FROM_MDSE_CD / PR SUPD_TO_MDSE_CD
                    if (cancDtlList == null || cancDtlList.isEmpty()) {
                        NWZC206001 api = new NWZC206001();
                        NWZC206001PMsg param = new NWZC206001PMsg();

                        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(param.slsDt, pMsg.procDt);
                        ZYPEZDItemValueSetter.setValue(param.mdseCd, origMdseCd);
                        ZYPEZDItemValueSetter.setValue(param.xxModeCd, NWZC206001Constant.SUPD_LIST_MODE);
                        ZYPEZDItemValueSetter.setValue(param.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(param.xxAvalPrchFlg, ZYPConstant.FLG_ON_Y);

                        api.execute(param, ONBATCH_TYPE.BATCH);

                        List<String> errList = S21ApiUtil.getXxMsgIdList(param);
                        if (errList.size() == 0) {
                            for (int c = 0; c < param.A.getValidCount(); c++) {
                                if (!origMdseCd.equals(param.A.no(c).mdseCd.getValue())) {
                                    mdseCd = param.A.no(c).mdseCd.getValue();
                                    cancDtlList = selectPRCH_REQ_DTLTMsgForUpdateOrig(prNum, mdseCd, prHdrTMsg.prchReqTpCd.getValue(), null);
                                    if (cancDtlList == null || cancDtlList.isEmpty()) {
                                        continue;
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    // Choice MDSE_CD / PR Compatible MDSE_CD
                    if (cancDtlList == null || cancDtlList.isEmpty()) {
                        ArrayList<String> mdseCdList = getRelnMdseCd(origMdseCd, MDSE_ITEM_RELN_TP.COMPATIBLE, false);
                        for (String tmpMdseCd : mdseCdList) {
                            mdseCd = tmpMdseCd;
                            cancDtlList = selectPRCH_REQ_DTLTMsgForUpdateOrig(prNum, mdseCd, prHdrTMsg.prchReqTpCd.getValue(), null);
                            if (cancDtlList == null || cancDtlList.isEmpty()) {
                                continue;
                            } else {
                                break;
                            }
                        }
                    }

                    // Choice MDSE_CD / PR Refurb MDSE_CD
                    if (cancDtlList == null || cancDtlList.isEmpty()) {
                        ArrayList<String> mdseCdList = getMdseCd(origMdseCd, MDSE_ITEM_RELN_TP.REFURBISHED, false);
                        for (String tmpMdseCd : mdseCdList) {
                            mdseCd = tmpMdseCd;
                            cancDtlList = selectPRCH_REQ_DTLTMsgForUpdateOrig(prNum, mdseCd, prHdrTMsg.prchReqTpCd.getValue(), null);
                            if (cancDtlList == null || cancDtlList.isEmpty()) {
                                continue;
                            } else {
                                break;
                            }
                        }
                    }

                    // Choice Compatible MDSE_CD / PR MDSE_CD
                    if (cancDtlList == null || cancDtlList.isEmpty()) {
                        ArrayList<String> mdseCdList = getRelnMdseCd(origMdseCd, MDSE_ITEM_RELN_TP.COMPATIBLE, true);
                        for (String tmpMdseCd : mdseCdList) {
                            mdseCd = tmpMdseCd;
                            cancDtlList = selectPRCH_REQ_DTLTMsgForUpdateOrig(prNum, mdseCd, prHdrTMsg.prchReqTpCd.getValue(), null);
                            if (cancDtlList == null || cancDtlList.isEmpty()) {
                                continue;
                            } else {
                                break;
                            }
                        }
                    }

                    // Choice Refurb MDSE_CD / PR MDSE_CD
                    if (cancDtlList == null || cancDtlList.isEmpty()) {
                        ArrayList<String> mdseCdList = getMdseCd(origMdseCd, MDSE_ITEM_RELN_TP.REFURBISHED, true);
                        for (String tmpMdseCd : mdseCdList) {
                            mdseCd = tmpMdseCd;
                            cancDtlList = selectPRCH_REQ_DTLTMsgForUpdateOrig(prNum, mdseCd, prHdrTMsg.prchReqTpCd.getValue(), null);
                            if (cancDtlList == null || cancDtlList.isEmpty()) {
                                continue;
                            } else {
                                break;
                            }
                        }
                    }
                    // QC#51553 End

                    PRCH_REQ_DTLTMsg origDtl = null;

                    // Cancel Original Line
                    for (PRCH_REQ_DTLTMsg cancDtl : cancDtlList) {
                        // QC#22058 Start
                        if (!PRCH_REQ_TP.PREMIUM_RUSH.equals(prHdrTMsg.prchReqTpCd.getValue())) {
                            // / PRCH_REQ_LINE_STS_CD
                            ZYPEZDItemValueSetter.setValue(cancDtl.prchReqLineStsCd, PRCH_REQ_LINE_STS.CANCELLED);
                            // PRCH_REQ_CANC_QTY
                            ZYPEZDItemValueSetter.setValue(cancDtl.prchReqCancQty, cancDtl.prchReqBalQty);
                            // PRCH_REQ_BAL_QTY
                            ZYPEZDItemValueSetter.setValue(cancDtl.prchReqBalQty, BigDecimal.ZERO);

                            S21ApiTBLAccessor.update(cancDtl);

                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cancDtl.getReturnCode())) {
                                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0291E);
                                return;
                            }

                        } else if (PRCH_REQ_TP.PREMIUM_RUSH.equals(prHdrTMsg.prchReqTpCd.getValue()) && ZYPCommonFunc.hasValue(pMsg.prchReqSrcTpCd) && PRCH_REQ_SRC_TP.MNX_SHIP_CONFIRMATION.equals(pMsg.prchReqSrcTpCd.getValue())) {
                            // / PRCH_REQ_LINE_STS_CD
                            ZYPEZDItemValueSetter.setValue(cancDtl.prchReqLineStsCd, PRCH_REQ_LINE_STS.CANCELLED);
                            // PRCH_REQ_CANC_QTY
                            ZYPEZDItemValueSetter.setValue(cancDtl.prchReqCancQty, cancDtl.prchReqBalQty);
                            // PRCH_REQ_BAL_QTY
                            ZYPEZDItemValueSetter.setValue(cancDtl.prchReqBalQty, BigDecimal.ZERO);

                            S21ApiTBLAccessor.update(cancDtl);

                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cancDtl.getReturnCode())) {
                                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0291E);
                                return;
                            }

                        }
                        // QC#22058 End

                        origDtlList.add(cancDtl);
                        if (origDtl == null) {
                            origDtl = cancDtl;
                        }
                    }
                    //Sol#369(QC#19243) Kameoka Start
                    if(origDtl == null){
                        List<PRCH_REQ_DTLTMsg> prchDtlList = selectPRCH_REQ_DTLTMsgForUpdatePrnt(prNum, origMdseCd);
                        // Choice SUPD_TO_MDSE_CD / PR SUPD_FROM_MDSE_CD
                        if (prchDtlList == null || prchDtlList.isEmpty()) {
                            // check supd from item
                            int cnt = 0;
                            while (true) {
                                if (cnt == 10) {
                                    break;
                                }
                                cnt++;
                                List<Map<String, Object>> supdRelnFromIncgFlgNResultList = getSupdInfoFromIncgFlgN(origMdseCd);
                                if (supdRelnFromIncgFlgNResultList.size() >= 1) {
                                    mdseCd = (String) supdRelnFromIncgFlgNResultList.get(0).get("SUPD_FROM_MDSE_CD");
                                    if (!ZYPCommonFunc.hasValue(mdseCd)) {
                                        break;
                                    }
                                    prchDtlList = selectPRCH_REQ_DTLTMsgForUpdatePrnt(prNum, mdseCd);
                                    if (prchDtlList == null || prchDtlList.isEmpty()) {
                                        continue;
                                    } else {
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                        }

                        // QC#51553 Start
                        // Choice SUPD_FROM_MDSE_CD / PR SUPD_TO_MDSE_CD
                        if (prchDtlList == null || prchDtlList.isEmpty()) {
                            NWZC206001 api = new NWZC206001();
                            NWZC206001PMsg param = new NWZC206001PMsg();

                            ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
                            ZYPEZDItemValueSetter.setValue(param.slsDt, pMsg.procDt);
                            ZYPEZDItemValueSetter.setValue(param.mdseCd, origMdseCd);
                            ZYPEZDItemValueSetter.setValue(param.xxModeCd, NWZC206001Constant.SUPD_LIST_MODE);
                            ZYPEZDItemValueSetter.setValue(param.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);
                            ZYPEZDItemValueSetter.setValue(param.xxAvalPrchFlg, ZYPConstant.FLG_ON_Y);

                            api.execute(param, ONBATCH_TYPE.BATCH);

                            List<String> errList = S21ApiUtil.getXxMsgIdList(param);
                            if (errList.size() == 0) {
                                for (int c = 0; c < param.A.getValidCount(); c++) {
                                    if (!origMdseCd.equals(param.A.no(c).mdseCd.getValue())) {
                                        mdseCd = param.A.no(c).mdseCd.getValue();
                                        prchDtlList = selectPRCH_REQ_DTLTMsgForUpdatePrnt(prNum, mdseCd);
                                        if (prchDtlList == null || prchDtlList.isEmpty()) {
                                            continue;
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                        }

                        // Choice MDSE_CD / PR Compatible MDSE_CD
                        if (prchDtlList == null || prchDtlList.isEmpty()) {
                            ArrayList<String> mdseCdList = getRelnMdseCd(origMdseCd, MDSE_ITEM_RELN_TP.COMPATIBLE, false);
                            for (String tmpMdseCd : mdseCdList) {
                                mdseCd = tmpMdseCd;
                                prchDtlList = selectPRCH_REQ_DTLTMsgForUpdatePrnt(prNum, mdseCd);
                                if (prchDtlList == null || prchDtlList.isEmpty()) {
                                    continue;
                                } else {
                                    break;
                                }
                            }
                        }

                        // Choice MDSE_CD / PR Refurb MDSE_CD
                        if (prchDtlList == null || prchDtlList.isEmpty()) {
                            ArrayList<String> mdseCdList = getMdseCd(origMdseCd, MDSE_ITEM_RELN_TP.REFURBISHED, false);
                            for (String tmpMdseCd : mdseCdList) {
                                mdseCd = tmpMdseCd;
                                prchDtlList = selectPRCH_REQ_DTLTMsgForUpdatePrnt(prNum, mdseCd);
                                if (prchDtlList == null || prchDtlList.isEmpty()) {
                                    continue;
                                } else {
                                    break;
                                }
                            }
                        }

                        // Choice Compatible MDSE_CD / PR MDSE_CD
                        if (prchDtlList == null || prchDtlList.isEmpty()) {
                            ArrayList<String> mdseCdList = getRelnMdseCd(origMdseCd, MDSE_ITEM_RELN_TP.COMPATIBLE, true);
                            for (String tmpMdseCd : mdseCdList) {
                                mdseCd = tmpMdseCd;
                                prchDtlList = selectPRCH_REQ_DTLTMsgForUpdatePrnt(prNum, mdseCd);
                                if (prchDtlList == null || prchDtlList.isEmpty()) {
                                    continue;
                                } else {
                                    break;
                                }
                            }
                        }

                        // Choice Refurb MDSE_CD / PR MDSE_CD
                        if (prchDtlList == null || prchDtlList.isEmpty()) {
                            ArrayList<String> mdseCdList = getMdseCd(origMdseCd, MDSE_ITEM_RELN_TP.REFURBISHED, true);
                            for (String tmpMdseCd : mdseCdList) {
                                mdseCd = tmpMdseCd;
                                prchDtlList = selectPRCH_REQ_DTLTMsgForUpdatePrnt(prNum, mdseCd);
                                if (prchDtlList == null || prchDtlList.isEmpty()) {
                                    continue;
                                } else {
                                    break;
                                }
                            }
                        }
                        // QC#51553 End

                        for (PRCH_REQ_DTLTMsg prchDtl : prchDtlList) {
                            origDtlList.add(prchDtl);
                            if (origDtl == null) {
                                origDtl = prchDtl;
                            }
                        }
                    }
                    //Sol#369(QC#19243) Kameoka End

                    // WH_OWNR_CD
                    Map<String, String> whOwnrMap = getWhOwnrCd(dtlInfo.srcInvtyLocCd.getValue(), procDt);
                    String whOwnr = null;
                    if (whOwnrMap != null) {
                        whOwnr = whOwnrMap.get(NPZC103001Constant.WH_OWNR_CD);
                    }
                    // PRCH_REQ_LINE_TP
                    String prLineTpCd = null;
                    // QC#28958
                    if (PRCH_REQ_TP.PREMIUM_RUSH.equals(prHdrTMsg.prchReqTpCd.getValue())) {
                        Map<String, String> prLineTpMap = getPrchReqLineTpCd(rqstFromShpgConfFlg, whOwnr);
                        if (prLineTpMap != null) {
                            prLineTpCd = prLineTpMap.get(NPZC103001Constant.PRCH_REQ_LINE_TP_CD);
                        }
                    } else {
                        if (origDtl == null) {
                            Map<String, String> prLineTpMap = getPrchReqLineTpCd(rqstFromShpgConfFlg, whOwnr);
                            if (prLineTpMap != null) {
                                prLineTpCd = prLineTpMap.get(NPZC103001Constant.PRCH_REQ_LINE_TP_CD);
                            }    
                        } else {
                            prLineTpCd = origDtl.prchReqLineTpCd.getValue();
                            if (!ZYPCommonFunc.hasValue(prLineTpCd)) {
                                prLineTpCd = PRCH_REQ_LINE_TP.INSOURCED_CH;
                            }
                        }
                    }

                    if(origDtl == null) {
                        // QC#52509 Mod Start
                        //this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0311E);
                        this.msgMap.addXxMsgIdWithPrm(NPZC103001Constant.NPZM0311E, new String[] {prNum,origMdseCd});
                        // QC#52509 Mod End
                        return;
                    }
                    // QC#51148 End
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, prNum);
                    ZYPEZDItemValueSetter.setValue(dtlInfo.prchReqLineTpCd, prLineTpCd);
                    PRCH_REQ_DTLTMsg newDtl = getDtlTMsgForTpUpdate(origDtl, pMsg, i);
                    S21ApiTBLAccessor.insert(newDtl);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newDtl.getReturnCode())) {
                        this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0289E);
                        return;
                    }
                    // QC#55602-1
                    if (PRCH_REQ_LINE_TP.SAMEDAY_CH.equals(newDtl.prchReqLineTpCd.getValue())) {
                        BigDecimal balQty = origDtl.prchReqBalQty.getValue();
                        if (ZYPCommonFunc.hasValue(balQty)) {
                            balQty = balQty.subtract(newDtl.prchReqQty.getValue());
                            if (BigDecimal.ZERO.compareTo(balQty) > 0) {
                                balQty = BigDecimal.ZERO;
                            }
                            ZYPEZDItemValueSetter.setValue(origDtl.prchReqBalQty, balQty);
                            S21ApiTBLAccessor.update(origDtl);
                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newDtl.getReturnCode())) {
                                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0289E);
                                return;
                            }
                        }
                    }
                    newDtlList.add(newDtl);
                }
            }
            // History
            if (origDtlList != null && !origDtlList.isEmpty()) {
                createBizProcLog(prHdrTMsg, origDtlList);
            }
            if (newDtlList != null && !newDtlList.isEmpty()) {
                createBizProcLog(prHdrTMsg, newDtlList);
            }
        }
    }

    /**
     * getBillToSellToByShipTo
     * @param pMsg NPZC103001PMsg
     * @return Map<String, String>
     */
    private Map<String, String> getBillToSellToByShipTo(NPZC103001PMsg pMsg) {

        SHIP_TO_CUSTTMsg shipToCust = new SHIP_TO_CUSTTMsg();
        shipToCust.setConditionValue("shipToCustCd01", pMsg.shipToCustCd.getValue());
        shipToCust.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        shipToCust.setSQLID("004");
        Map<String, String> billToSellTo = new HashMap<String, String>();

        SHIP_TO_CUSTTMsgArray shipToCustAry = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipToCust);
        if (shipToCustAry != null && shipToCustAry.getValidCount() > 0) {
            billToSellTo.put(NPZC103001Constant.SELL_TO_CUST_CD, shipToCustAry.no(0).sellToCustCd.getValue());
        }

        return billToSellTo;
    }

    /**
     * getPrchGrpCd. Mod QC#30589
     * @param pMsg NPZC103001PMsg
     * @return String
     */
    private String getPrchGrpCd(NPZC103001PMsg pMsg) {

        for (int idx = 0; idx < pMsg.prchReqInfo.getValidCount(); idx++) {
            if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(idx).mdseCd)) {
                MDSETMsg tMsg = new MDSETMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, pMsg.prchReqInfo.no(idx).mdseCd);

                MDSETMsg outMsg = (MDSETMsg) EZDTBLAccessor.findByKey(tMsg);
                if (outMsg != null) {
                    return outMsg.prchGrpCd.getValue();
                }

            }
        }

        return null;
    }

    /**
     * getShipToCustAddress
     * @param pMsg NPZC103001PMsg
     */
    private void getShipToCustAddress(NPZC103001PMsg pMsg) {

        if (ZYPCommonFunc.hasValue(pMsg.poQlfyCd) && isShipToCustAllNull(pMsg)) {
            Map<String, String> shipToCustMap = null;

            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            queryParam.put("shipToCustCd", pMsg.shipToCustCd.getValue());
            queryParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            shipToCustMap = (Map<String, String>) this.ssmBatchClient.queryObject("getShipTo", queryParam);

            if (shipToCustMap == null || shipToCustMap.isEmpty()) {
                return;
            }
            ZYPEZDItemValueSetter.setValue(pMsg.shipToAddlLocNm, shipToCustMap.get(NPZC103001Constant.ADDL_LOC_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCntyNm, shipToCustMap.get(NPZC103001Constant.CNTY_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCtryCd, shipToCustMap.get(NPZC103001Constant.CTRY_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCtyAddr, shipToCustMap.get(NPZC103001Constant.CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, shipToCustMap.get(NPZC103001Constant.FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, shipToCustMap.get(NPZC103001Constant.SCD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, shipToCustMap.get(NPZC103001Constant.THIRD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFrthLineAddr, shipToCustMap.get(NPZC103001Constant.FRTH_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstRefCmntTxt, shipToCustMap.get(NPZC103001Constant.FIRST_REF_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdRefCmntTxt, shipToCustMap.get(NPZC103001Constant.SCD_REF_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, shipToCustMap.get(NPZC103001Constant.LOC_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToPostCd, shipToCustMap.get(NPZC103001Constant.POST_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToProvNm, shipToCustMap.get(NPZC103001Constant.PROV_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToStCd, shipToCustMap.get(NPZC103001Constant.ST_CD));
        }
        return;
    }

    /**
     * isShipToCustAllNull
     * @param pMsg NPZC103001PMsg
     * @return boolean
     */
    private boolean isShipToCustAllNull(NPZC103001PMsg pMsg) {

        if (ZYPCommonFunc.hasValue(pMsg.shipToAddlLocNm)) {
            return false;
        } else if (ZYPCommonFunc.hasValue(pMsg.shipToCntyNm)) {
            return false;
        } else if (ZYPCommonFunc.hasValue(pMsg.shipToCtryCd)) {
            return false;
        } else if (ZYPCommonFunc.hasValue(pMsg.shipToCtyAddr)) {
            return false;
        } else if (ZYPCommonFunc.hasValue(pMsg.shipToFirstLineAddr)) {
            return false;
        } else if (ZYPCommonFunc.hasValue(pMsg.shipToScdLineAddr)) {
            return false;
        } else if (ZYPCommonFunc.hasValue(pMsg.shipToThirdLineAddr)) {
            return false;
        } else if (ZYPCommonFunc.hasValue(pMsg.shipToFrthLineAddr)) {
            return false;
        } else if (ZYPCommonFunc.hasValue(pMsg.shipToFirstRefCmntTxt)) {
            return false;
        } else if (ZYPCommonFunc.hasValue(pMsg.shipToScdRefCmntTxt)) {
            return false;
        } else if (ZYPCommonFunc.hasValue(pMsg.shipToLocNm)) {
            return false;
        } else if (ZYPCommonFunc.hasValue(pMsg.shipToPostCd)) {
            return false;
        } else if (ZYPCommonFunc.hasValue(pMsg.shipToProvNm)) {
            return false;
        } else if (ZYPCommonFunc.hasValue(pMsg.shipToStCd)) {
            return false;
        }
        return true;
    }

    /**
     * Get PRCH_REQ_TP_CD If null in PMsg, Get by PRCH_REQ_REC_TP_CD
     * @param pMsg NPZC103001PMsg
     */
    private void getPrchReqTpCd(NPZC103001PMsg pMsg) {

        if (!ZYPCommonFunc.hasValue(pMsg.prchReqTpCd) && ZYPCommonFunc.hasValue(pMsg.prchReqRecTpCd)) {
            PRCH_REQ_REC_TPTMsg tMsg = new PRCH_REQ_REC_TPTMsg();

            ZYPEZDItemValueSetter.setValue(tMsg.prchReqRecTpCd, pMsg.prchReqRecTpCd);
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            tMsg = (PRCH_REQ_REC_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);

            if (tMsg != null) {
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqTpCd, tMsg.defPrchReqTpCd);
            }
        }
        return;
    }

    /**
     * Get PRCH_REQ_CRAT_TZ
     * @param pMsg NPZC103001PMsg
     */
    private void getPrchReqCratTz(NPZC103001PMsg pMsg) {

        if (isTechRequest(pMsg)) {
            // get Local Time
            String rtlWhCd = null;
            if (pMsg.prchReqInfo.getValidCount() > 0) {
                Map<String, String> locDat = null;
                // QC#55710
                if (PRCH_REQ_TP.TECH_RETURN.equals(pMsg.prchReqTpCd.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(pMsg.prchReqTpCd.getValue())) {
                    locDat = getWhAndSwhByInvtyLocCd(pMsg.prchReqInfo.no(0).srcInvtyLocCd.getValue());
                } else {
                    locDat = getWhAndSwhByInvtyLocCd(pMsg.prchReqInfo.no(0).destInvtyLocCd.getValue());
                }

                if (locDat != null) {
                    rtlWhCd = locDat.get(NPZC103001Constant.RTL_WH_CD);
                }
            }

            if (rtlWhCd == null) {
                rtlWhCd = pMsg.rqstTechTocCd.getValue();
            }

            String lclTZId = getTechTZId(rtlWhCd);
            ZYPLocalTimeData timeData = null;
            if (lclTZId != null) {
                timeData = ZYPLocalTimeUtil.convertTimeSys2Lcl(lclTZId, ZYPDateUtil.getCurrentSystemTime(NPZC103001Constant.TIMESTAMP_PATTERN));
            }

            if (timeData == null) {
                addlHdrMap.put(NPZC103001Constant.COL_PRCH_REQ_CRAT_TZ, null);
                addlHdrMap.put(NPZC103001Constant.COL_PRCH_REQ_CRAT_DISP_DT_TM_TS, ZYPDateUtil.getCurrentSystemTime(NPZC103001Constant.DTTMTS_PATTERN));
            } else {
                addlHdrMap.put(NPZC103001Constant.COL_PRCH_REQ_CRAT_TZ, timeData.getTZDspName());
                String prchReqCratDispDtTmTs = timeData.getTime();
                addlHdrMap.put(NPZC103001Constant.COL_PRCH_REQ_CRAT_DISP_DT_TM_TS, prchReqCratDispDtTmTs.substring(0, NPZC103001Constant.DT_TM_TS_LENGTH));
            }
        }
        return;
    }

    /**
     * Get getTechTZId
     * @param techTocCd String
     * @return String
     */
    private String getTechTZId(String rtlWhCd) {

        // QC#15825
        String postCd = null;
        String ctryCd = null;
        Map<String, Object> locInfo =  getTechLocation(glblCmpyCd, rtlWhCd);
        if (locInfo != null) {
            postCd = (String) locInfo.get("POST_CD");
            ctryCd = (String) locInfo.get("CTRY_CD");
        }

        String lclTZId = null;
        if (ZYPCommonFunc.hasValue(ctryCd) && ZYPCommonFunc.hasValue(postCd)) {
            try {
                // QC#18922
                postCd = subStrPostCd(postCd);
                lclTZId = ZYPLocalTimeUtil.getTZId(ctryCd, postCd);
            } catch (ZYPLocalTimeException e) {
                lclTZId = null;
            }
        } else {
            S21InfoLogOutput.println("Address information of the technician is not registered. TECH_TOC_CD = " + rtlWhCd);
        }

        return lclTZId;
    }

    /**
     * Set RQST_RCV_DT by Tech Timezone
     * @param pMsg NPZC103001PMsg
     * @param idx index
     * @param rtlWhCd String
     */
    private void setRqstRcvDtTm(NPZC103001PMsg pMsg, int idx, String rtlWhCd) {

        if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(idx).rqstRcvDt) && isTechRequest(pMsg)) {

            // get Local Time
            if (rtlWhCd == null) {
                rtlWhCd = pMsg.rqstTechTocCd.getValue();
            }

            String lclTZId = getTechTZId(rtlWhCd);
            ZYPLocalTimeData timeData = null;
            String tm = null;
            if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(idx).rqstRcvTm)) {
                tm = pMsg.prchReqInfo.no(idx).rqstRcvTm.getValue() + "00";
            } else {
                tm = "000000";
            }
            if (lclTZId != null) {
                timeData = ZYPLocalTimeUtil.convertTimeLcl2Sys(lclTZId, pMsg.prchReqInfo.no(idx).rqstRcvDt.getValue() + tm, NPZC103001Constant.DTTMTS_PATTERN);
                // QC#22400 Start
                if (!ZYPCommonFunc.hasValue(preRqstRcvDt)) {
                    preRqstRcvDt = pMsg.prchReqInfo.no(idx).rqstRcvDt.getValue();
                }
                if (!ZYPCommonFunc.hasValue(preRqstRcvTm)) {
                    preRqstRcvTm = pMsg.prchReqInfo.no(idx).rqstRcvTm.getValue();
                }
                // QC#22400 End
            }
            if (timeData != null) {
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(idx).rqstRcvDt, timeData.getTime().substring(0, NPZC103001Constant.DT_LENGTH));
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(idx).rqstRcvTm, timeData.getTime().substring(NPZC103001Constant.DT_LENGTH, NPZC103001Constant.DT_LENGTH + NPZC103001Constant.TM_LENGTH));
            }
        }
        return;
    }

    /**
     * Get calcRddForPr
     * @param pMsg NPZC103001PMsg
     * @param idx index
     * @return String
     */
    private String calcRddForPr(NPZC103001PMsg pMsg, int idx) {

        String rqsrRcvDt = null;

        NPZC128001PMsg rddPMsg = new NPZC128001PMsg();
        ZYPEZDItemValueSetter.setValue(rddPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rddPMsg.mdseCd, pMsg.prchReqInfo.no(idx).mdseCd);
        // START 2018/03/26 S.Katsuma [QC#22519,ADD]
        ZYPEZDItemValueSetter.setValue(rddPMsg.prchReqTpCd, pMsg.prchReqTpCd);
        // END 2018/03/26 S.Katsuma [QC#22519,ADD]
        ZYPEZDItemValueSetter.setValue(rddPMsg.prchReqSrcTpCd, pMsg.prchReqSrcTpCd);
        ZYPEZDItemValueSetter.setValue(rddPMsg.procDt, pMsg.procDt);
        NPZC128001 calcRdd = new NPZC128001();
        calcRdd.execute(rddPMsg, this.onBatTpCd);
        if (rddPMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < rddPMsg.xxMsgIdList.getValidCount(); i++) {
                this.msgMap.addXxMsgId(rddPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
        } else if (ZYPCommonFunc.hasValue(rddPMsg.rddDt)) {
            rqsrRcvDt = rddPMsg.rddDt.getValue();
        }
        return rqsrRcvDt;
    }

    /**
     * Get FULL_PSN_NAME
     * @param pMsg String
     * @return String
     */
    private String getFullPersonName(String psnCd) {

        S21_PSN_VTMsg s21PsnV = new S21_PSN_VTMsg();
        s21PsnV.setConditionValue("psnCd01", psnCd);
        s21PsnV.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        s21PsnV.setSQLID("001");

        S21_PSN_VTMsgArray s21PsnVAry = (S21_PSN_VTMsgArray) EZDTBLAccessor.findByCondition(s21PsnV);
        // QC#30768
        if (s21PsnVAry != null && s21PsnVAry.getValidCount() > 0) {
            return s21PsnVAry.no(0).fullPsnNm.getValue();
        } else {
            AUTH_PSNTMsg inMsg = new AUTH_PSNTMsg();
            inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            inMsg.setConditionValue("usrNm01", psnCd);
            inMsg.setMaxCount(1);
            inMsg.setSQLID("053");
            AUTH_PSNTMsgArray outMsg = (AUTH_PSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
            if (outMsg.length() != 0) {
                return ZYPCommonFunc.concatString(outMsg.no(0).firstNm.getValue(), " ", outMsg.no(0).lastNm.getValue());
            }
        }

        return null;
    }

    /**
     * getNextPrchReqLineNum
     * @param pMsg NPZC103001PMsg
     * @return String PRCH_REQ_LINE_NUM nextval
     */
    private String getNextPrchReqLineNum(NPZC103001PMsg pMsg) {

        if (currentMaxPrchReqLineNum == null) {
            currentMaxPrchReqLineNum = getCurrentMaxPrchReqLineNum(pMsg);
        }

        currentMaxPrchReqLineNum = currentMaxPrchReqLineNum.add(BigDecimal.ONE);

        DecimalFormat df = new DecimalFormat("000");
        return df.format(currentMaxPrchReqLineNum);
    }

    /**
     * getCurrentMaxPrchReqLineNum
     * @param pMsg NPZC103001PMsg
     * @return BigDecimal current max PRCH_REQ_LINE_NUM value
     */
    private BigDecimal getCurrentMaxPrchReqLineNum(NPZC103001PMsg pMsg) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        params.put("prchReqNum", this.currPrchReqNum);

        return (BigDecimal) this.ssmBatchClient.queryObject("getCurrentMaxPrchReqLineNum", params);
    }

    /**
     * getNextPrchReqLineSubNum
     * @param pMsg NPZC103001PMsg
     * @return BigDecimal PRCH_REQ_LINE_SUB_NUM nextval
     */
    private BigDecimal getNextPrchReqLineSubNum(NPZC103001PMsg pMsg) {

        BigDecimal next = BigDecimal.ZERO;
        if (!currentMaxPrchReqLineSubNum.containsKey(this.prchReqLineNum)) {
            BigDecimal currSubNum = getCurrentMaxPrchReqLineSubNum(pMsg);
            if (currSubNum != null) {
                next = currSubNum.add(BigDecimal.ONE);
            }
        } else {
            next = next.add(BigDecimal.ONE);
        }

        currentMaxPrchReqLineSubNum.put(this.prchReqLineNum, next);

        return next;
    }

    /**
     * getCurrentMaxPrchReqLineSubNum
     * @param pMsg NPZC103001PMsg
     * @return BigDecimal current max PRCH_REQ_LINE_SUB_NUM value
     */
    private BigDecimal getCurrentMaxPrchReqLineSubNum(NPZC103001PMsg pMsg) {

        return getMaxPrchReqLineSubNum(this.currPrchReqNum, this.prchReqLineNum);
    }

    /**
     * getMaxPrchReqLineSubNum
     * @param glblCmpyCd String
     * @param prLineNum String
     * @return BigDecimal current max PRCH_REQ_LINE_SUB_NUM value
     */
    private BigDecimal getMaxPrchReqLineSubNum(String prNum, String prLineNum) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("prchReqNum", prNum);
        params.put("prchReqLineNum", prLineNum);

        return (BigDecimal) this.ssmBatchClient.queryObject("getCurrentMaxPrchReqLineSubNum", params);
    }

    /**
     * Get PRCH_REQ_LINE_TP_CD by PRCH_REQ_REC_TP (Default
     * PRCH_REQ_LINE_TP)
     * @param pMsg NPZC103001PMsg
     * @param info NPZC103001_prchReqInfoPMsg
     */
    private void getPrchReqLineTpCd(NPZC103001PMsg pMsg, NPZC103001_prchReqInfoPMsg info) {

        PRCH_REQ_TPTMsg tMsg = new PRCH_REQ_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.prchReqTpCd, pMsg.prchReqTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        tMsg = (PRCH_REQ_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(info.prchReqLineTpCd, tMsg.defPrchReqLineTpCd);
        }
        return;
    }

    /**
     * Get Default FROM_STK_STS_CD FROM PRCH_REQ_LINE_TP
     * @param prchReqLineTpCd String
     * @param info NPZC103001_prchReqInfoPMsg
     */
    private void getFromSSFromPrchReqLineTp(String prchReqLineTpCd, NPZC103001_prchReqInfoPMsg info) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("prchReqLineTpCd", prchReqLineTpCd);

        String fromStkStsCd = (String) this.ssmBatchClient.queryObject("getFromSSFromPrchReqLineTp", queryParam);

        if (ZYPCommonFunc.hasValue(fromStkStsCd)) {
            ZYPEZDItemValueSetter.setValue(info.fromStkStsCd, fromStkStsCd);
        }

        return;
    }

    /**
     * Get Default TO_STK_STS_CD FROM PRCH_REQ_LINE_TP
     * @param prchReqLineTpCd String
     * @param info NPZC103001_prchReqInfoPMsg
     */
    private void getToSSFromPrchReqLineTp(String prchReqLineTpCd, NPZC103001_prchReqInfoPMsg info) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("prchReqLineTpCd", prchReqLineTpCd);

        String toStkStsCd = (String) this.ssmBatchClient.queryObject("getToSSFromPrchReqLineTp", queryParam);

        if (ZYPCommonFunc.hasValue(toStkStsCd)) {
            ZYPEZDItemValueSetter.setValue(info.toStkStsCd, toStkStsCd);
        }

        return;
    }

    /**
     * Get Procurement Type(PROCR_TP_CD), FROM_INVTY_LOC_CD
     * @param pMsg NPZC103001PMsg
     * @param msg NPZC103001_prchReqInfoPMsg
     * @return Map<String, String>
     */
    private Map<String, String> getProcurementSource(NPZC103001PMsg pMsg, NPZC103001_prchReqInfoPMsg msg) {

        NPZC108001 api = new NPZC108001();
        NPZC108001PMsg param = new NPZC108001PMsg();

        param.glblCmpyCd.setValue(this.glblCmpyCd);
        param.slsDt.setValue(pMsg.procDt.getValue());
        param.mdseCd.setValue(msg.mdseCd.getValue());
        param.invtyLocCd.setValue(msg.destInvtyLocCd.getValue());

        api.execute(param, this.onBatTpCd);

        if (param.xxMsgIdList.getValidCount() == 0) {
            Map<String, String> map = new HashMap<String, String>();
            map.put(NPZC103001Constant.COL_PROCR_TP_CD, param.procrTpCd.getValue());
            map.put(NPZC103001Constant.COL_SRC_LOC_CD, param.srcLocCd.getValue());
            map.put(NPZC103001Constant.COL_SRC_RTL_WH_CD, param.srcRtlWhCd.getValue());
            map.put(NPZC103001Constant.COL_SRC_RTL_SWH_CD, param.srcRtlSwhCd.getValue());
            return map;
        } else {
            return Collections.EMPTY_MAP;
        }
    }

    // START 2018/10/02 M.Naito [QC#28479,MOD]
//    /**
//     * getSwLicReqFlg
//     * @param mdseCd String
//     * @return String
//     */
//    private String getSwLicReqFlg(String mdseCd) {
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", this.glblCmpyCd);
//        queryParam.put("mdseCd", mdseCd);
//        return (String) this.ssmBatchClient.queryObject("getSwLicReqFlg", queryParam);
//    }

// START 2023/06/13 T.Kuroda [QC#61363, DEL]
//    /**
//     * chkSwLicItem
//     * @param mdseCd String
//     * @return boolean
//     */
//    private boolean chkSwLicItem(String mdseCd) {
//
//        // START 2018/10/09 M.Naito [QC#28479,MOD]
//        if (!ZYPCommonFunc.hasValue(mdseCd)) {
//            return false;
//        }
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", this.glblCmpyCd);
//        queryParam.put("mdseCd", mdseCd);
//        Map<String, String> swLicItemInfoMap = (Map<String, String>) this.ssmBatchClient.queryObject("getSwLicReqFlg", queryParam);
//
//        if (swLicItemInfoMap != null) {
//            if (ZYPConstant.FLG_ON_Y.equals(swLicItemInfoMap.get("INTNT_CONN_SW_CTRL_FLG")) || ZYPConstant.FLG_ON_Y.equals(swLicItemInfoMap.get("SW_LIC_REQ_FLG"))) {
//                return true;
//            }
//        }
//        // END 2018/10/09 M.Naito [QC#28479,MOD]
//        return false;
//    }
//    // END 2018/10/02 M.Naito [QC#28479,MOD]
// END 2023/06/13 T.Kuroda [QC#61363, DEL]

    /**
     * getWhAndSwhByInvtyLocCd
     * @param invtyLocCd String
     * @return Map<String, String>
     */
    private Map<String, String> getWhAndSwhByInvtyLocCd(String invtyLocCd) {

        DS_INVTY_LOC_VTMsg dsInvtyLocV = new DS_INVTY_LOC_VTMsg();
        dsInvtyLocV.setSQLID("002");
        dsInvtyLocV.setConditionValue("invtyLocCd01", invtyLocCd);
        dsInvtyLocV.setConditionValue("glblCmpyCd01", this.glblCmpyCd);

        DS_INVTY_LOC_VTMsgArray dsInvtyLocVAry = (DS_INVTY_LOC_VTMsgArray) EZDTBLAccessor.findByCondition(dsInvtyLocV);

        Map<String, String> whSwh = null;
        if (dsInvtyLocVAry != null && dsInvtyLocVAry.getValidCount() > 0) {
            whSwh = new HashMap<String, String>();
            whSwh.put(NPZC103001Constant.RTL_WH_CD, dsInvtyLocVAry.no(0).rtlWhCd.getValue());
            whSwh.put(NPZC103001Constant.RTL_SWH_CD, dsInvtyLocVAry.no(0).rtlSwhCd.getValue());
        }

        return whSwh;
    }

    /**
     * getWhAndSwhByTechTocCd
     * @param pMsg NPZC103001PMsg
     * @return Map<String, String>
     */
    private Map<String, String> getWhAndSwhByTechTocCd(NPZC103001PMsg pMsg) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("techTocCd", pMsg.rqstTechTocCd.getValue());
        queryParam.put("whCatgTechCar", RTL_WH_CATG.TECH_CAR_STOCK);
        String procDt = null;
        if (ZYPCommonFunc.hasValue(pMsg.procDt)) {
            procDt = pMsg.procDt.getValue();
        } else {
            procDt = ZYPDateUtil.getCurrentSystemTime(NPZC103001Constant.DATE_PATTERN);
        }
        queryParam.put("procDt", procDt);
        queryParam.put("prtyLocFlgY", ZYPConstant.FLG_ON_Y);
        return (Map<String, String>) this.ssmBatchClient.queryObject("getWhAndSwhByTechTocCd", queryParam);
    }

    // START 2018/12/17 M.Naito [QC#29599,DEL]
//    /**
//     * getCarrCdByCustomer
//     * @param shipToCustCd String
//     * @return String
//     */
//    private String getCarrCdByCustomer(String shipToCustCd) {
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", this.glblCmpyCd);
//        queryParam.put("shipToCustCd", shipToCustCd);
//        queryParam.put("defAcctCarrFlg", ZYPConstant.FLG_ON_Y);
//        return (String) this.ssmBatchClient.queryObject("getCarrCdByCustomer", queryParam);
//    }
    // END 2018/12/17 M.Naito [QC#29599,DEL]

    /**
     * getCarrCdByWh
     * @param srcRtlWhCd String
     * @return String
     */
    private String getCarrCdByWh(String srcRtlWhCd) {

        RTL_WHTMsg tMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, srcRtlWhCd);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        tMsg = (RTL_WHTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return tMsg.prfCarrCd.getValue();
        }
        return null;
    }

    /**
     * getWhOwnrCd
     * @param invtyLocCd String
     * @param procDt String
     * @return Map<String, String>
     */
    private Map<String, String> getWhOwnrCd(String invtyLocCd, String procDt) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invtyLocCd", invtyLocCd);
        queryParam.put("procDt", procDt);
        return (Map<String, String>) this.ssmBatchClient.queryObject("getWhOwnrCd", queryParam);
    }

    /**
     * getPrchReqLineTpCd
     * @param rqstFromShpgConfFlg String
     * @param whOwnrCd String
     * @return Map<String, String>
     */
    private Map<String, String> getPrchReqLineTpCd(String rqstFromShpgConfFlg, String whOwnrCd) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("rqstFromShpgConfFlg", rqstFromShpgConfFlg);
        queryParam.put("whOwnrCd", whOwnrCd);
        return (Map<String, String>) this.ssmBatchClient.queryObject("getPrchReqLineTpCd", queryParam);
    }

    /**
     * getVendorPayment
     * @param dtlInfo NPZC103001_prchReqInfoPMsg
     * @return Map<String, String>
     */
    private Map<String, String> getVendorPayment(NPZC103001_prchReqInfoPMsg dtlInfo) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("vndCd", dtlInfo.vndCd.getValue());
        return (Map<String, String>) this.ssmBatchClient.queryObject("getVendorPayment", queryParam);
    }

    /**
     * isApprovedStatus
     * @param pMsg NPZC103001PMsg
     * @return boolean
     */
    private boolean isApprovedStatus(NPZC103001PMsg pMsg) {

        PRCH_REQ_APVL_STSTMsg tMsg = new PRCH_REQ_APVL_STSTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.prchReqApvlStsCd, pMsg.prchReqApvlStsCd);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        tMsg = (PRCH_REQ_APVL_STSTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return ZYPConstant.FLG_ON_Y.equals(tMsg.prchReqApvlFlg.getValue());
        }
        return false;
    }

    /**
     * isSavedStatus
     * @param pMsg NPZC103001PMsg
     * @return boolean
     */
    private boolean isSavedStatus(NPZC103001PMsg pMsg) {

        PRCH_REQ_APVL_STSTMsg tMsg = new PRCH_REQ_APVL_STSTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.prchReqApvlStsCd, pMsg.prchReqApvlStsCd);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        tMsg = (PRCH_REQ_APVL_STSTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return ZYPConstant.FLG_ON_Y.equals(tMsg.prchReqSavedFlg.getValue());
        }
        return false;
    }

    /**
     * existSerial
     * @param pMsg NPZC103001PMsg
     * @param idx int
     * @return boolean
     */
    private boolean existSerial(NPZC103001PMsg pMsg, int idx) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("prchReqNum", pMsg.prchReqNum.getValue());
        queryParam.put("serNum", pMsg.serNumInfo.no(idx).serNum.getValue());
        queryParam.put("rownum", "1");
        // PRCH_REQ_LINE_NUM/PRCH_REQ_LINE_SUB_NUM
        Map<String, Object> numInfo = getSerLineNumLineSubNum(pMsg, idx);
        if (numInfo == null) {
            return false;
        }
        queryParam.put("prchReqLineNum", numInfo.get("prchReqLineNum"));
        queryParam.put("prchReqLineSubNum", numInfo.get("prchReqLineSubNum"));

        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("existSerial", queryParam);
        if (result != null && !result.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * getSerLineNumLineSubNum
     * @param pMsg NPZC103001PMsg
     * @param idx int
     * @return Map<String, Object>
     */
    private Map<String, Object> getSerLineNumLineSubNum(NPZC103001PMsg pMsg, int idx) {

        Map<String, Object> numInfo = new HashMap<String, Object>();
        if (ZYPCommonFunc.hasValue(pMsg.serNumInfo.no(idx).prchReqLineNum) && ZYPCommonFunc.hasValue(pMsg.serNumInfo.no(idx).prchReqLineSubNum)) {
            numInfo.put("prchReqLineNum", pMsg.serNumInfo.no(idx).prchReqLineNum.getValue());
            numInfo.put("prchReqLineSubNum", pMsg.serNumInfo.no(idx).prchReqLineSubNum.getValue());
        } else if (ZYPCommonFunc.hasValue(pMsg.serNumInfo.no(idx).xxDtlLineNum)) {
            for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
                if (pMsg.serNumInfo.no(idx).xxDtlLineNum.getValue().equals(pMsg.prchReqInfo.no(i).xxDtlLineNum.getValue())) {
                    numInfo.put("prchReqLineNum", pMsg.prchReqInfo.no(i).prchReqLineNum.getValue());
                    numInfo.put("prchReqLineSubNum", pMsg.prchReqInfo.no(i).prchReqLineSubNum.getValue());
                }
            }
        } else {
            return null;
        }
        return numInfo;
    }

    /**
     * isAllDtlCancel
     * @param pMsg NPZC103001PMsg
     * @return boolean
     */
    private boolean isAllDtlCancel(NPZC103001PMsg pMsg) {

        String prchReqLineNum = "";

        return isAllDtlCancel(pMsg, prchReqLineNum);
    }

    /**
     * isClosePRLineStatus
     * @param pMsg NPZC103001PMsg
     * @return boolean
     */
    private boolean isClosePRLineStatus(NPZC103001PMsg pMsg) {

        String prchReqLineNum = "";

        // START 2022/10/07 R.Azucena [QC#60664, MOD]
        // return isClosePRLineStatus(pMsg, prchReqLineNum);
        return isClosePRLineStatus(pMsg, prchReqLineNum) && isAllRwsStatusClosed(pMsg);
        // END 2022/10/07 R.Azucena [QC#60664, MOD]
    }

    /**
     * isAllDtlCancel
     * @param pMsg NPZC103001PMsg
     * @return boolean
     */
    private boolean isAllDtlCancel(NPZC103001PMsg pMsg, String prchReqLineNum) {

        List<String> prchReqLineStsCdList = new ArrayList<String>();

        prchReqLineStsCdList.add(PRCH_REQ_LINE_STS.CANCELLED);

        BigDecimal result = getDtlNoMatchLineStsCd(pMsg, prchReqLineStsCdList, prchReqLineNum);

        if (BigDecimal.ZERO.compareTo(result) == 0) {
            return true;
        }

        return false;
    }

    /**
     * isClosePRLineStatus
     * @param pMsg NPZC103001PMsg
     * @return boolean
     */
    private boolean isClosePRLineStatus(NPZC103001PMsg pMsg, String prchReqLineNum) {

        List<String> prchReqLineStsCdList = new ArrayList<String>();
        // 2017/11/13 M.Naito Del QC#22501 START
// QC#17347
//        prchReqLineStsCdList.add(PRCH_REQ_LINE_STS.PARTIALLY_RECEIVED);
//        prchReqLineStsCdList.add(PRCH_REQ_LINE_STS.PARTIALLY_SHIPPED);
        // 2017/11/13 M.Naito Del QC#22501 END
        prchReqLineStsCdList.add(PRCH_REQ_LINE_STS.CANCELLED);
        prchReqLineStsCdList.add(PRCH_REQ_LINE_STS.CLOSED);
        prchReqLineStsCdList.add(getClosePRLineStatus(pMsg));

        BigDecimal result = getDtlNoMatchLineStsCd(pMsg, prchReqLineStsCdList, prchReqLineNum);

        if (BigDecimal.ZERO.compareTo(result) == 0) {
            return true;
        }

        return false;
    }

    /**
     * getClosePRLineStatus
     * @param pMsg NPZC103001PMsg
     * @return String
     */
    private String getClosePRLineStatus(NPZC103001PMsg pMsg) {

        PRCH_REQ_TPTMsg tMsg = new PRCH_REQ_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.prchReqTpCd, pMsg.prchReqTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        tMsg = (PRCH_REQ_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return tMsg.cloPrchReqLineStsCd.getValue();
        }
        return null;
    }

    /**
     * getDtlNoMatchLineStsCd
     * @param pMsg NPZC103001PMsg
     * @param prchReqLineStsCdList List<String>
     * @return BigDecimal
     */
    private BigDecimal getDtlNoMatchLineStsCd(NPZC103001PMsg pMsg, List<String> prchReqLineStsCdList, String prchReqLineNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("prchReqNum", pMsg.prchReqNum.getValue());
        if (ZYPCommonFunc.hasValue(prchReqLineNum)) {
            queryParam.put("prchReqLineNum", prchReqLineNum);
            queryParam.put("prchReqLineSubNum", BigDecimal.ZERO);
        }
        queryParam.put("prchReqLineStsCdList", prchReqLineStsCdList.toArray(new String[prchReqLineStsCdList.size()]));
        return (BigDecimal) this.ssmBatchClient.queryObject("getDtlNoMatchLineStsCd", queryParam);
    }

    /**
     * getConfigDtlNoMatchLineStsCd
     * @param configPrchReqDtlTMsg PRCH_REQ_DTLTMsg
     * @param prchReqLineStsCdList List<String>
     * @return BigDecimal
     */
    private BigDecimal getConfigDtlNoMatchLineStsCd(PRCH_REQ_DTLTMsg configPrchReqDtlTMsg, List<String> prchReqLineStsCdList) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("prchReqNum", configPrchReqDtlTMsg.prchReqNum.getValue());
        queryParam.put("prchReqLineNum", configPrchReqDtlTMsg.prchReqLineNum.getValue());
        queryParam.put("prchReqLineSubNum", configPrchReqDtlTMsg.prchReqLineSubNum.getValue());
        queryParam.put("prchReqLineStsCdList", prchReqLineStsCdList.toArray(new String[prchReqLineStsCdList.size()]));
        return (BigDecimal) this.ssmBatchClient.queryObject("getConfigDtlNoMatchLineStsCd", queryParam);
    }

    /**
     * getTpShipConfOrig
     * @param prchReqNum String
     * @return BigDecimal
     */
    private BigDecimal getTpShipConfOrig(String prchReqNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("prchReqNum", prchReqNum);
        queryParam.put("prchReqRecTpCd", PRCH_REQ_REC_TP.TECH_REQUEST);
        queryParam.put("prchReqStsCd", PRCH_REQ_STS.OPEN);
        return (BigDecimal) this.ssmBatchClient.queryObject("getTpShipConfOrig", queryParam);
    }

    /**
     * getPrchReqCratDispDtTmTs
     * @param pMsg NPZC103001PMsg
     * @return String
     */
    private String getPrchReqCratDispDtTmTs(NPZC103001PMsg pMsg) {

        PRCH_REQTMsg inMsg = new PRCH_REQTMsg();
        inMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
        inMsg.prchReqNum.setValue(pMsg.prchReqNum.getValue());

        PRCH_REQTMsg tMsg = (PRCH_REQTMsg) S21CacheTBLAccessor.findByKey(inMsg);
        if (tMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return tMsg.prchReqCratDispDtTmTs.getValue();
        }
        return null;
    }

    /**
     * getPrimaryVendor
     * @param pMsg NPZC103001PMsg
     * @param dtlInfo NPZC103001_prchReqInfoPMsg
     * @return Map<String, Object>
     */
    private Map<String, Object> getPrimaryVendor(NPZC103001PMsg pMsg, NPZC103001_prchReqInfoPMsg dtlInfo) {

        NPZC113001 api = new NPZC113001();
        NPZC113001PMsg param = new NPZC113001PMsg();
        param.glblCmpyCd.setValue(this.glblCmpyCd);
        param.slsDt.setValue(pMsg.procDt.getValue());
        param.shipToCustCd.setValue(pMsg.shipToCustCd.getValue());
        param.mdseCd.setValue(dtlInfo.mdseCd.getValue());

        api.execute(param, this.onBatTpCd);

        // QC#18738
        if (ZYPCommonFunc.hasValue(pMsg.mrpPlnNm) && param.xxMsgIdList.getValidCount() == 0 && ZYPConstant.FLG_ON_Y.equals(param.xxErrFlg.getValue())) {
            return Collections.emptyMap();
        } else if (param.xxMsgIdList.getValidCount() == 0) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(NPZC103001Constant.COL_VND_CD, param.vndCd.getValue());
            map.put(NPZC103001Constant.COL_PRNT_VND_CD, param.prntVndCd.getValue());
            map.put(NPZC103001Constant.COL_ASL_DTL_PK, param.aslDtlPk.getValue());
            map.put(NPZC103001Constant.COL_VND_UOM_CD, param.vndUomCd.getValue());
            map.put(NPZC103001Constant.COL_UNIT_PRC_AMT, param.unitPrcAmt.getValue());
            return map;
        } else {
            return Collections.emptyMap();
        }
    }

    /**
     * Get Supersede New Item
     * @param pMsg NPZC103001PMsg
     * @param mdseCd Target Mdse
     * @return String
     */
    private String getSupersedeItem(NPZC103001PMsg pMsg, String mdseCd, String vndCd) {

        // QC#23025
        RCV_ASN_VNDTMsg rcvAsnVndTMsg = new RCV_ASN_VNDTMsg();
        ZYPEZDItemValueSetter.setValue(rcvAsnVndTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rcvAsnVndTMsg.rcvAsnVndCd, vndCd);
        rcvAsnVndTMsg = (RCV_ASN_VNDTMsg) S21ApiTBLAccessor.findByKey(rcvAsnVndTMsg);

        if (rcvAsnVndTMsg == null || ZYPConstant.FLG_ON_Y.equals(rcvAsnVndTMsg.itemFlipFlg.getValue())) {
            NWZC206001PMsg nWZC206001PMsg = new NWZC206001PMsg();
            ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.slsDt, pMsg.procDt);
            ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.mdseCd, mdseCd);
            ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxModeCd, NWZC206001.SUPD_LATEST_MODE);
            ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(nWZC206001PMsg.xxAvalPrchFlg, ZYPConstant.FLG_ON_Y);

            NWZC206001 nWZC206001 = new NWZC206001();
            nWZC206001.execute(nWZC206001PMsg, this.onBatTpCd);

            if (nWZC206001PMsg.xxMsgIdList.getValidCount() > 0) {
                for (int i = 0; i < nWZC206001PMsg.xxMsgIdList.getValidCount(); i++) {
                    this.msgMap.addXxMsgId(nWZC206001PMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                }
                return null;
            }
            // START 2023/03/31 E.Watabe [QC#61210, ADD]
            if((getCountCusaSupplier(vndCd) > 0) 
                    && (!checkCusaPrntVndCd(pMsg, nWZC206001PMsg))){
                return mdseCd;
            }
            // END 2023/03/31 E.Watabe [QC#61210, ADD]
            return nWZC206001PMsg.A.no(0).mdseCd.getValue();
        }

        return mdseCd;
    
    }
    
    // START 2023/03/31 E.Watabe [QC#61210, ADD]
    /**
     * checkPrntVndCd
     * @param NPZC103001PMsg pMsg, NWZC206001PMsg nWZC206001PMsg
     * @return boolean
     */
    private boolean checkCusaPrntVndCd(NPZC103001PMsg pMsg, NWZC206001PMsg nWZC206001PMsg) {
        String parentVendorCode = null;
        
        parentVendorCode = getParentVendorCode(pMsg.procDt.getValue(),nWZC206001PMsg.A.no(0).mdseCd.getValue());
        if(parentVendorCode == null){
            return false;
        }
        
        return parentVendorCode.equals(cusaPrntVndCd);
    }
    

    /**
     * getCountCusaSupplier
     * @param vndCd String
     * @return int
     */
    private int getCountCusaSupplier(String vndCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("prntVndCd", cusaPrntVndCd);
        queryParam.put("vndCd", vndCd);
        return (Integer) this.ssmBatchClient.queryObject("getCountCusaSupplier", queryParam);
    }
    
      /**
      * getParentVendorCode
      * @param mdseCd String
      * @return String
      */
     private String getParentVendorCode(String procDt,String mdseCd) {

         Map<String, String> queryParam = new HashMap<String, String>();
         queryParam.put("glblCmpyCd", this.glblCmpyCd);
         queryParam.put("mdseCd", mdseCd);
         queryParam.put("procDt", procDt);
         return (String) this.ssmBatchClient.queryObject("getParentVendorCode", queryParam);
     }
     // END 2023/03/31 E.Watabe [QC#61210, ADD]
    
    /**
     * setTechApprovalNotificationRequired
     * @param pMsg NPZC103001PMsg
     */
    private void setTechApprovalNotificationRequired(NPZC103001PMsg pMsg) {

        // Modify 2018/03/23 QC#12110 Tool item.
        if(isToolItem){
            pMsg.prchReqApvlStsCd.setValue(PRCH_REQ_APVL_STS.AWAITING_APPROVAL);
        // QC#55710
        } else if (PRCH_REQ_TP.MIN_MAX.equals(pMsg.prchReqTpCd.getValue()) || PRCH_REQ_TP.TECH_RETURN.equals(pMsg.prchReqTpCd.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(pMsg.prchReqTpCd.getValue())) {
            pMsg.prchReqApvlStsCd.setValue(PRCH_REQ_APVL_STS.PRE_APPROVED);
            isNotificationRequired = false;
        } else if (PRCH_REQ_TP.PREMIUM_RUSH.equals(pMsg.prchReqTpCd.getValue())) {
            pMsg.prchReqApvlStsCd.setValue(PRCH_REQ_APVL_STS.PRE_APPROVED);
            isNotificationRequired = true;
            // START 2023/05/18 T.Kuroda [QC#61211, ADD]
            isApvlMinAmtFlg = isApvlMinAmt(pMsg);
            if (isApvlMinAmtFlg) {
                pMsg.prchReqApvlStsCd.setValue(PRCH_REQ_APVL_STS.AWAITING_APPROVAL);
            }
            // END   2023/05/18 T.Kuroda [QC#61211, ADD]
        } else if (PRCH_REQ_TP.RUSH.equals(pMsg.prchReqTpCd.getValue())) {
            // QC#53081 Start
            String prchReqCratDispDtTmTs = null;
            if (addlHdrMap.get(NPZC103001Constant.COL_PRCH_REQ_CRAT_DISP_DT_TM_TS) == null) {
                PRCH_REQTMsg tMsg = selectPRCH_REQTMsg(pMsg.prchReqNum.getValue());
                if (tMsg == null) {
                    getPrchReqCratTz(pMsg);
                    prchReqCratDispDtTmTs = ((String) addlHdrMap.get(NPZC103001Constant.COL_PRCH_REQ_CRAT_DISP_DT_TM_TS));
                } else {
                    prchReqCratDispDtTmTs = getPrchReqUptimeTz(pMsg, tMsg);
                }
            } else {
                prchReqCratDispDtTmTs = ((String) addlHdrMap.get(NPZC103001Constant.COL_PRCH_REQ_CRAT_DISP_DT_TM_TS));
            }
            // QC#53081 End
            // START 2019/08/02 M.Naito [QC#52287,ADD]
            String preApprovedStartTime = ZYPCodeDataUtil.getVarCharConstValue("RUSH_PRE_APPROVED_START_TIME", this.glblCmpyCd);
            String preApprovedEndTime = ZYPCodeDataUtil.getVarCharConstValue("RUSH_PRE_APPROVED_END_TIME", this.glblCmpyCd);
            String prchReqCratDispTm = prchReqCratDispDtTmTs.substring(NPZC103001Constant.DT_LENGTH, NPZC103001Constant.DT_LENGTH + NPZC103001Constant.TM_LENGTH);
//            if (EZDCommonFunc.cmpTime(NPZC103001Constant.PREMIUM_RUSH_JUDGE_TIME, prchReqCratDispDtTmTs.substring(NPZC103001Constant.DT_LENGTH, NPZC103001Constant.DT_LENGTH + NPZC103001Constant.TM_LENGTH)) == 1) {

            // QC#53303 Mod Start
            String prchReqCratDispDt = prchReqCratDispDtTmTs.substring(0, NPZC103001Constant.DT_LENGTH);
            if (EZDCommonFunc.cmpTime(preApprovedStartTime, prchReqCratDispTm) == 1 && EZDCommonFunc.cmpTime(prchReqCratDispTm, preApprovedEndTime) == 1 && ZYPDateUtil.isBusinessDay(glblCmpyCd, prchReqCratDispDt)) {
//            if (EZDCommonFunc.cmpTime(preApprovedStartTime, prchReqCratDispTm) == 1 && EZDCommonFunc.cmpTime(prchReqCratDispTm, preApprovedEndTime) == 1) {
            // QC#53303 Mod End

            // END 2019/08/02 M.Naito [QC#52287,ADD]
                // Before
                pMsg.prchReqApvlStsCd.setValue(PRCH_REQ_APVL_STS.AWAITING_APPROVAL);
                if (isAwatingApproval(pMsg, prchReqCratDispDtTmTs)) {
                    // Next Day and Before 8am
                    isNextDayForceFlg = true;
                }
                isNotificationRequired = false;
            } else {
                // After
                pMsg.prchReqApvlStsCd.setValue(PRCH_REQ_APVL_STS.PRE_APPROVED);
                isNotificationRequired = true;
            }
        } else if (PRCH_REQ_TP.STANDARD.equals(pMsg.prchReqTpCd.getValue())) {
            if (existsMRPInfo(pMsg)) {
                isNotificationRequired = true;
            } else {
                isNotificationRequired = false;
            }
        }

        // START 2021/02/09 J.Evangelista [QC#57869,ADD]
        // Overwrite isNotificationRequired flag if prchReqInfo is empty.
        if (pMsg.prchReqInfo.getValidCount() <= 0) {
            isNotificationRequired = false;
        }
        // END 2021/02/09 J.Evangelista [QC#57869,ADD]

        // update PRCH_REQ_APVL_STS_CD
        updateApprovalStatus(pMsg);

        if (isNotificationRequired) {
            // update TECH_RQST_NTC_ML_PROC_CD
            updateTechNtcProcCd(pMsg);
        }
    }

    /**
     * updateApprovalStatus
     * @param pMsg NPZC103001PMsg
     */
    private void updateApprovalStatus(NPZC103001PMsg pMsg) {

        PRCH_REQTMsg prchReqTMsg = selectPRCH_REQTMsgForUpdate(pMsg);
        // PRCH_REQ_APVL_STS_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlStsCd, pMsg.prchReqApvlStsCd);
        boolean isApproved = false;
        if ((PRCH_REQ_APVL_STS.PRE_APPROVED.equals(pMsg.prchReqApvlStsCd.getValue())) || (PRCH_REQ_APVL_STS.APPROVED.equals(pMsg.prchReqApvlStsCd.getValue()))) {
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlDt, pMsg.procDt);
            // PRCH_REQ_APVL_BY_PSN_CD
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlByPsnCd, pMsg.rqstTechTocCd);
            // PRCH_REQ_APVL_BY_NM
            ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlByNm, getFullPersonName(pMsg.rqstTechTocCd.getValue()));
            isApproved = true;
        }
        S21FastTBLAccessor.update(prchReqTMsg);
        if (prchReqTMsg != null && !EZDTBLAccessor.RTNCD_NORMAL.equals(prchReqTMsg.getReturnCode())) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0100E);
            return;
        }
        if (isApproved) {
            // Create History
            List<PRCH_REQ_DTLTMsg> prchReqDtlList = selectPRCH_REQ_DTLTMsg(pMsg);
            createBizProcLog(prchReqTMsg, prchReqDtlList, true);
        }
    }

    /**
     * updateTechNtcProcCd
     * @param pMsg NPZC103001PMsg
     */
    private void updateTechNtcProcCd(NPZC103001PMsg pMsg) {

        List<PRCH_REQ_DTLTMsg> prchReqDtlTMsgList = new ArrayList<PRCH_REQ_DTLTMsg>();
        for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
            NPZC103001_prchReqInfoPMsg dtlInfo = pMsg.prchReqInfo.no(i);
            PRCH_REQ_DTLTMsg prchReqDtlTMsg = selectPRCH_REQ_DTLTMsgForUpdate(pMsg, dtlInfo);
            // TECH_RQST_NTC_ML_PROC_CD
            ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.techRqstNtcMlProcCd, ZYPConstant.FLG_OFF_0);
            prchReqDtlTMsgList.add(prchReqDtlTMsg);
        }
        int lineNum = S21FastTBLAccessor.update(prchReqDtlTMsgList.toArray(new PRCH_REQ_DTLTMsg[prchReqDtlTMsgList.size()]));
        if (lineNum != prchReqDtlTMsgList.size()) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0291E);
            return;
        }
    }

    /**
     * poRequisitionApprovalWorkflow
     * @param pMsg NPZC103001PMsg
     */
    private void poRequisitionApprovalWorkflow(NPZC103001PMsg pMsg) {

        // PO Request Approval to WF API
        NPZC130001PMsg wfPMsg = new NPZC130001PMsg();
        ZYPEZDItemValueSetter.setValue(wfPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wfPMsg.slsDt, pMsg.procDt);
        if (isTechRequest(pMsg)) {
            // Mode
            // sol#118(QC#12110) Mod Start
            // if (PRCH_REQ_TP.RUSH.equals(pMsg.prchReqTpCd.getValue())) {
            if (PRCH_REQ_TP.RUSH.equals(pMsg.prchReqTpCd.getValue()) || isToolItem) {
                //if (isNextDayForceFlg) {
                if (isNextDayForceFlg || isToolItem) {
                    ZYPEZDItemValueSetter.setValue(wfPMsg.xxModeCd, NPZC130001Constant.WFMD_TECH_REQ_RUSH);
                } else {
                    ZYPEZDItemValueSetter.setValue(wfPMsg.xxModeCd, NPZC130001Constant.WFMD_TECH_REQ);
                }
            }
            // START 2023/05/18 T.Kuroda [QC#61211, ADD]
            else if (isApvlMinAmtFlg) {
                ZYPEZDItemValueSetter.setValue(wfPMsg.xxModeCd, NPZC130001Constant.WFMD_TECH_REQ_RUSH);
            }
            // END   2023/05/18 T.Kuroda [QC#61211, ADD]
            else {
                ZYPEZDItemValueSetter.setValue(wfPMsg.xxModeCd, NPZC130001Constant.WFMD_TECH_REQ);
            }
            // sol#118(QC#12110) Mod End

            // Approval History Source Type Code
            ZYPEZDItemValueSetter.setValue(wfPMsg.apvlHistSrcTpCd, APVL_HIST_SRC_TP.TECH_REQUEST);
        } else if (PRCH_REQ_REC_TP.PO_REQUISITION.equals(pMsg.prchReqRecTpCd.getValue())) {
            // Mode
            ZYPEZDItemValueSetter.setValue(wfPMsg.xxModeCd, NPZC130001Constant.WFMD_PO_REQ);
            // Approval History Source Type Code
            ZYPEZDItemValueSetter.setValue(wfPMsg.apvlHistSrcTpCd, APVL_HIST_SRC_TP.PO_REQUISITION);
        } else if (PRCH_REQ_REC_TP.INVENTORY_REQUEST.equals(pMsg.prchReqRecTpCd.getValue())) {
            // Mode
            ZYPEZDItemValueSetter.setValue(wfPMsg.xxModeCd, NPZC130001Constant.WFMD_INVTY_REQ);
            // Approval History Source Type Code
            ZYPEZDItemValueSetter.setValue(wfPMsg.apvlHistSrcTpCd, APVL_HIST_SRC_TP.INVENTORY_REQUEST);
        }
        // Process Type
        if (NPZC103001Constant.MODE_CREATE.equals(this.mode)) {
            ZYPEZDItemValueSetter.setValue(wfPMsg.xxProcTpCd, NPZC103001Constant.NEW_ORDER);
        } else {
            ZYPEZDItemValueSetter.setValue(wfPMsg.xxProcTpCd, NPZC103001Constant.UPDATE_ORDER);
        }
        // Transaction Reference Number
        ZYPEZDItemValueSetter.setValue(wfPMsg.trxRefNum, pMsg.prchReqNum);

        // PR Status
        PRCH_REQTMsg updMsg = new PRCH_REQTMsg();
        ZYPEZDItemValueSetter.setValue(updMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(updMsg.prchReqNum, pMsg.prchReqNum);
        ZYPEZDItemValueSetter.setValue(updMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.AWAITING_APPROVAL);
        EZDTBLAccessor.updateSelectionField(updMsg, new String[] {"prchReqApvlStsCd" });
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0100E);
        }

        // Call to WF API
        NPZC130001 wfApi = new NPZC130001();
        wfApi.execute(wfPMsg, this.onBatTpCd);

        if (S21ApiUtil.isXxMsgId(wfPMsg)) {
            // Copy Error Message to NPZC1030 PMsg
            for (int i = 0; i < wfPMsg.xxMsgIdList.getValidCount(); i++) {
                this.msgMap.addXxMsgId(wfPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
        }

        // QC#27575 DeleteHazmat Prch_req update & call workflow.
        // QC#2366 Add. Hazmat Prch_req update & call workflow.

        return;
    }

    /**
     * isAwatingApproval
     * @param pMsg NPZC103001PMsg
     * @param prchReqCratDispDtTmTs String
     * @return boolean
     */
    private boolean isAwatingApproval(NPZC103001PMsg pMsg, String prchReqCratDispDtTmTs) {

        if (prchReqCratDispDtTmTs == null || prchReqCratDispDtTmTs.length() < NPZC103001Constant.DT_LENGTH) {
            return false;
        }
        String cratDt = prchReqCratDispDtTmTs.substring(0, NPZC103001Constant.DT_LENGTH);
        // QC#22400
        if (EZDCommonFunc.cmpDate(EZDCommonFunc.addDay(cratDt, 1), preRqstRcvDt) == 0 && EZDCommonFunc.cmpTime(NPZC103001Constant.AWATING_APPROVAL_JUDGE_TIME, preRqstRcvTm) > -1) {
            return true;
        }
        return false;
    }

    /**
     * existsMRPInfo
     * @param pMsg NPZC103001PMsg
     * @return boolean
     */
    private boolean existsMRPInfo(NPZC103001PMsg pMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invtyLocCd", pMsg.prchReqInfo.no(0).destInvtyLocCd.getValue());
        List<String> mdseList = new ArrayList<String>();
        for (int idx = 0; idx < pMsg.prchReqInfo.getValidCount(); idx++) {
            mdseList.add(pMsg.prchReqInfo.no(idx).mdseCd.getValue());
        }
        BigDecimal cnt = (BigDecimal) this.ssmBatchClient.queryObject("getMRPInfo", queryParam);
        if (BigDecimal.ZERO.compareTo(cnt) < 0) {
            return true;
        }
        return false;
    }

    /**
     * createWarehouseTransferOrder
     * @param pMsg NPZC103001PMsg
     */
    private void createWarehouseTransferOrder(NPZC103001PMsg nPZC103001PMsg) {

        // get PRCH_REQ TMsg
        PRCH_REQTMsg hdrTmsg = selectPRCH_REQTMsgForUpdate(nPZC103001PMsg.prchReqNum.getValue());

        // QC#17395 Start.
        // PRCH_REQ_DTL List for Non-hazmat
        List<PRCH_REQ_DTLTMsg> nonHazmatPrchReqDtlTMsgList = new ArrayList<PRCH_REQ_DTLTMsg>();
        // PRCH_REQ_DTL List for hazmat
        List<PRCH_REQ_DTLTMsg> hazmatPrchReqDtlTMsgList = new ArrayList<PRCH_REQ_DTLTMsg>();
        

        // NLZC301001 Create Warehouse Transfer API PMsgList for Non hazmat.
        List<NLZC301001PMsg> nonHazMatNLZC301001PMsgList = getNLZC301001PMsg(nPZC103001PMsg, hdrTmsg, nonHazmatPrchReqDtlTMsgList, false);

        // NLZC301001 Create Warehouse Transfer API PMsgList for HazMat.
        List<NLZC301001PMsg> hazMatNLZC301001PMsgList = getNLZC301001PMsg(nPZC103001PMsg, hdrTmsg, hazmatPrchReqDtlTMsgList, true);

        if (nonHazMatNLZC301001PMsgList.isEmpty() && hazMatNLZC301001PMsgList.isEmpty()) {
            return;
        }

        NLZC301001 nLZC301001 = new NLZC301001();

        // Call NLZC3010 Warehouse Transfer API for Non-hazmat.
        if (!nonHazMatNLZC301001PMsgList.isEmpty()) {

            nLZC301001.execute(nonHazMatNLZC301001PMsgList, this.onBatTpCd);

            // Error Message
            for (int i = 0; i < nonHazMatNLZC301001PMsgList.size(); i++) {
                NLZC301001PMsg nLZC301001PMsg = nonHazMatNLZC301001PMsgList.get(i);
                for (int idx = 0; idx < nLZC301001PMsg.xxMsgIdList.getValidCount(); idx++) {
                    // QC#55615
                    if (ZYPCommonFunc.hasValue(nLZC301001PMsg.prchReqLineNum)) {
                        this.msgMap.addXxMsgIdWithPrm(nLZC301001PMsg.xxMsgIdList.no(idx).xxMsgId.getValue(), new String[]{nLZC301001PMsg.prchReqLineNum.getValue()});
                    } else {
                        this.msgMap.addXxMsgId(nLZC301001PMsg.xxMsgIdList.no(idx).xxMsgId.getValue());
                    }
                }
            }

            if (nPZC103001PMsg.xxMsgIdList.getValidCount() > 0) {
                return;
            }

            // Update Purchase Requisition
            boolean hasError = false;
            for (NLZC301001PMsg nLZC301001PMsg : nonHazMatNLZC301001PMsgList) {
                setDtlTMsgForUpdateAfterWHTrans(nPZC103001PMsg, nLZC301001PMsg, nonHazmatPrchReqDtlTMsgList);
            }
            // Update Header
            setHdrTMsgForUpdateAfterWHTrans(nPZC103001PMsg, hasError, hdrTmsg);
            S21FastTBLAccessor.update(hdrTmsg);
            if (hdrTmsg != null && !EZDTBLAccessor.RTNCD_NORMAL.equals(hdrTmsg.getReturnCode())) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0100E);
                return;
            }

            // Update Detail
            int ret = S21FastTBLAccessor.update(nonHazmatPrchReqDtlTMsgList.toArray(new PRCH_REQ_DTLTMsg[nonHazmatPrchReqDtlTMsgList.size()]));
            if (ret != nonHazmatPrchReqDtlTMsgList.size()) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0291E);
                return;
            }
        }

        // Call NLZC3010 Warehouse Transfer API for hazmat.
        if (!hazMatNLZC301001PMsgList.isEmpty()) {

            nLZC301001.execute(hazMatNLZC301001PMsgList, this.onBatTpCd);

            // Error Message
            for (int i = 0; i < hazMatNLZC301001PMsgList.size(); i++) {
                NLZC301001PMsg nLZC301001PMsg = hazMatNLZC301001PMsgList.get(i);
                for (int idx = 0; idx < nLZC301001PMsg.xxMsgIdList.getValidCount(); idx++) {
                    // QC#55615
                    if (ZYPCommonFunc.hasValue(nLZC301001PMsg.prchReqLineNum)) {
                        this.msgMap.addXxMsgIdWithPrm(nLZC301001PMsg.xxMsgIdList.no(idx).xxMsgId.getValue(), new String[]{nLZC301001PMsg.prchReqLineNum.getValue()});
                    } else {
                        this.msgMap.addXxMsgId(nLZC301001PMsg.xxMsgIdList.no(idx).xxMsgId.getValue());
                    }
                }
            }

            if (nPZC103001PMsg.xxMsgIdList.getValidCount() > 0) {
                return;
            }

            // Update Purchase Requisition
            boolean hasError = false;
            for (NLZC301001PMsg nLZC301001PMsg : hazMatNLZC301001PMsgList) {
                setDtlTMsgForUpdateAfterWHTrans(nPZC103001PMsg, nLZC301001PMsg, hazmatPrchReqDtlTMsgList);
            }
            // Update Header
            setHdrTMsgForUpdateAfterWHTrans(nPZC103001PMsg, hasError, hdrTmsg);
            S21FastTBLAccessor.update(hdrTmsg);
            if (hdrTmsg != null && !EZDTBLAccessor.RTNCD_NORMAL.equals(hdrTmsg.getReturnCode())) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0100E);
                return;
            }

            // Update Detail
            int ret = S21FastTBLAccessor.update(hazmatPrchReqDtlTMsgList.toArray(new PRCH_REQ_DTLTMsg[hazmatPrchReqDtlTMsgList.size()]));
            if (ret != hazmatPrchReqDtlTMsgList.size()) {
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0291E);
                return;
            }
        }


        // QC#17395 End.
    }

    /**
     * setHdrTMsgForUpdateAfterWHTrans
     * @param nPZC103001PMsg NPZC103001PMsg
     * @param hasError boolean
     * @param tMsg PRCH_REQTMsg
     */
    private void setHdrTMsgForUpdateAfterWHTrans(NPZC103001PMsg nPZC103001PMsg, boolean hasError, PRCH_REQTMsg tMsg) {

        if (hasError) {

            ZYPEZDItemValueSetter.setValue(tMsg.prchReqStsCd, PRCH_REQ_STS.RELEASE_ERROR);

        } else {

            ZYPEZDItemValueSetter.setValue(tMsg.prchReqStsCd, PRCH_REQ_STS.OPEN);
        }

        return;
    }

    /**
     * setDtlTMsgForUpdateAfterWHTrans
     * @param nPZC103001PMsg NPZC103001PMsg
     * @param nLZC301001PMsg NLZC301001PMsg
     * @param prchReqDtlTMsgList List<PRCH_REQ_DTLTMsg>
     */
    private void setDtlTMsgForUpdateAfterWHTrans(NPZC103001PMsg nPZC103001PMsg, NLZC301001PMsg nLZC301001PMsg, List<PRCH_REQ_DTLTMsg> prchReqDtlTMsgList) {

        for (PRCH_REQ_DTLTMsg tMsg : prchReqDtlTMsgList) {
            if (!tMsg.prchReqNum.getValue().equals(nLZC301001PMsg.prchReqNum.getValue()) || !tMsg.prchReqLineNum.getValue().equals(nLZC301001PMsg.prchReqLineNum.getValue())
                    || !tMsg.prchReqLineSubNum.getValue().equals(nLZC301001PMsg.prchReqLineSubNum.getValue())) {
                // Different Dtl
                continue;
            }
            // PRCH_REQ_LINE_STS_CD
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.AWAITING_SHIPPING);
            // PO_SCHD_REL_DT
            ZYPEZDItemValueSetter.setValue(tMsg.poSchdRelDt, nPZC103001PMsg.procDt);
            // PRCH_REQ_REL_STS
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.COMPLEATED);
            // PRCH_REQ_REL_DT_TM_TS
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqRelDtTmTs, ZYPDateUtil.getCurrentSystemTime(NPZC103001Constant.DTTMTS_PATTERN));
            // INVTY_ORD_NUM
            ZYPEZDItemValueSetter.setValue(tMsg.invtyOrdNum, nLZC301001PMsg.invtyOrdNum);
            // INVTY_ORD_LINE_NUM
            ZYPEZDItemValueSetter.setValue(tMsg.invtyOrdLineNum, nLZC301001PMsg.invtyOrdLineNum);
            // PRCH_REQ_REL_QTY
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqRelQty, tMsg.prchReqQty);
            // QC#17049 2017/01/16 CITS T.Tokutomi START
            // PRCH_REQ_BAL_QTY
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqBalQty, BigDecimal.ZERO);
            // QC#17049 2017/01/16 CITS T.Tokutomi END
            // SO_NUM
            ZYPEZDItemValueSetter.setValue(tMsg.soNum, nLZC301001PMsg.soNum);
            // Output Parameter
            for (int i = 0; i < nPZC103001PMsg.prchReqInfo.getValidCount(); i++) {
                NPZC103001_prchReqInfoPMsg info = nPZC103001PMsg.prchReqInfo.no(i);
                if (nPZC103001PMsg.prchReqNum.getValue().equals(nLZC301001PMsg.trxRefNum.getValue()) && info.prchReqLineNum.getValue().equals(nLZC301001PMsg.trxRefLineNum.getValue())
                        && info.prchReqLineSubNum.getValue().toString().equals(nLZC301001PMsg.trxRefLineSubNum.getValue())) {
                    ZYPEZDItemValueSetter.setValue(info.soNum, nLZC301001PMsg.soNum);
                    break;
                }
            }
        }
        return;
    }

    /**
     * getNonHazMatNLZC301001PMsg
     * @param nPZC103001PMsg NPZC103001PMsg
     * @param hdrTmsg PRCH_REQTMsg
     * @param prchReqDtlTMsgList List<PRCH_REQ_DTLTMsg>
     * @param checkHazMatFlg boolean
     * @return List<NLZC301001PMsg>
     */
    private List<NLZC301001PMsg> getNLZC301001PMsg(NPZC103001PMsg nPZC103001PMsg, PRCH_REQTMsg hdrTmsg, List<PRCH_REQ_DTLTMsg> prchReqDtlTMsgList, boolean checkHazMatFlg) {

        List<NLZC301001PMsg> nLZC301001PMsgList = new ArrayList<NLZC301001PMsg>();
        for (int idx = 0; idx < nPZC103001PMsg.prchReqInfo.getValidCount(); idx++) {
            PRCH_REQ_DTLTMsg info = selectPRCH_REQ_DTLTMsgForUpdate(nPZC103001PMsg.prchReqNum.getValue(), nPZC103001PMsg.prchReqInfo.no(idx).prchReqLineNum.getValue(), nPZC103001PMsg.prchReqInfo.no(idx).prchReqLineSubNum.getValue(), null);

            if (ZYPCommonFunc.hasValue(info.srcInvtyLocCd)) {
                RTL_WHTMsg whTMsg = getRtlWh(info.srcInvtyLocCd.getValue());
                if (whTMsg != null && RTL_WH_CATG.VIRTUAL.equals(whTMsg.rtlWhCatgCd.getValue())) {
                    continue;
                }
            }

            if (!isAllocationRequest(info.prchReqLineTpCd.getValue(), info.prchReqRelDtTmTs.getValue(), info.prchReqRelStsCd.getValue())) {
                continue;
            }

            // QC#17395 Start.
            // Check hazmat
            if (!checkHazMatFlg) {
                if (checkHazmat(nPZC103001PMsg, idx)) {
                    continue;
                }
            } else {
                if (!checkHazmat(nPZC103001PMsg, idx)) {
                    continue;
                }
            }
            // QC#17395 End.

            NLZC301001PMsg nLZC301001PMsg = new NLZC301001PMsg();
            // NPZC103001_prchReqInfoPMsg info =
            // nPZC103001PMsg.prchReqInfo.no(idx);

            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.glblCmpyCd, nPZC103001PMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.xxInvtyOrdCratFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.slsDt, nPZC103001PMsg.procDt);

            Map<String, String> srcWhSwh = getWhAndSwhByInvtyLocCd(info.srcInvtyLocCd.getValue());
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.fromInvtyLocCd, info.srcInvtyLocCd);
            if (srcWhSwh != null) {
                ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.fromLocCd, srcWhSwh.get(NPZC103001Constant.RTL_WH_CD));
            }

            Map<String, String> destWhSwh = getWhAndSwhByInvtyLocCd(info.destInvtyLocCd.getValue());
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.toInvtyLocCd, info.destInvtyLocCd);
            if (destWhSwh != null) {
                ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.toLocCd, destWhSwh.get(NPZC103001Constant.RTL_WH_CD));
            }
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.locStsCd, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.delyDt, info.rqstRcvDt);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.shpgSvcLvlCd, info.shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.mdseCd, info.mdseCd);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.ordQty, info.prchReqQty);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.stkStsCd, info.fromStkStsCd);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.carrCd, info.carrCd);
            if (ZYPCommonFunc.hasValue(hdrTmsg.delyAddlCmntTxt)) {
                String delyAddlCmntTxt = hdrTmsg.delyAddlCmntTxt.getValue();
                if (delyAddlCmntTxt.length() <= NPZC103001Constant.INVTY_ORD_CMNT_LENGTH) {
                    // under 64
                    ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.firstInvtyOrdCmntTxt, delyAddlCmntTxt);
                } else if (delyAddlCmntTxt.length() <= NPZC103001Constant.INVTY_ORD_CMNT_LENGTH * NPZC103001Constant.SECOND_CMNT_LENGTH) {
                    ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.firstInvtyOrdCmntTxt, delyAddlCmntTxt.substring(0, NPZC103001Constant.INVTY_ORD_CMNT_LENGTH));
                    ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.scdInvtyOrdCmntTxt, delyAddlCmntTxt.substring(NPZC103001Constant.INVTY_ORD_CMNT_LENGTH));
                } else if (delyAddlCmntTxt.length() <= NPZC103001Constant.INVTY_ORD_CMNT_LENGTH * NPZC103001Constant.THIRD_CMNT_LENGTH) {
                    ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.firstInvtyOrdCmntTxt, delyAddlCmntTxt.substring(0, NPZC103001Constant.INVTY_ORD_CMNT_LENGTH));
                    ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.scdInvtyOrdCmntTxt, delyAddlCmntTxt.substring(NPZC103001Constant.INVTY_ORD_CMNT_LENGTH, NPZC103001Constant.INVTY_ORD_CMNT_LENGTH * NPZC103001Constant.SECOND_CMNT_LENGTH));
                    ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.thirdInvtyOrdCmntTxt, delyAddlCmntTxt.substring(NPZC103001Constant.INVTY_ORD_CMNT_LENGTH * NPZC103001Constant.SECOND_CMNT_LENGTH));
                } else {
                    // delyAddlCmntTxt Length 240
                    ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.firstInvtyOrdCmntTxt, delyAddlCmntTxt.substring(0, NPZC103001Constant.INVTY_ORD_CMNT_LENGTH));
                    ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.scdInvtyOrdCmntTxt, delyAddlCmntTxt.substring(NPZC103001Constant.INVTY_ORD_CMNT_LENGTH, NPZC103001Constant.INVTY_ORD_CMNT_LENGTH * NPZC103001Constant.SECOND_CMNT_LENGTH));
                    ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.thirdInvtyOrdCmntTxt, delyAddlCmntTxt.substring(NPZC103001Constant.INVTY_ORD_CMNT_LENGTH * NPZC103001Constant.SECOND_CMNT_LENGTH, NPZC103001Constant.INVTY_ORD_CMNT_LENGTH
                            * NPZC103001Constant.THIRD_CMNT_LENGTH));
                    ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.frthInvtyOrdCmntTxt, delyAddlCmntTxt.substring(NPZC103001Constant.INVTY_ORD_CMNT_LENGTH * NPZC103001Constant.THIRD_CMNT_LENGTH));
                }
            }
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.prchReqNum, hdrTmsg.prchReqNum);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.prchReqLineNum, info.prchReqLineNum);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.prchReqLineSubNum, info.prchReqLineSubNum);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.prchReqRecTpCd, hdrTmsg.prchReqRecTpCd);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.fsrNum, hdrTmsg.fsrNum);
            // QC#21657 modify
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.dropShipFlg, hdrTmsg.vndDropShipFlg);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.shipToCustCd, hdrTmsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.shipToLocNm, hdrTmsg.shipToLocNm);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.shipToAddlLocNm, hdrTmsg.shipToAddlLocNm);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.shipToFirstLineAddr, hdrTmsg.shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.shipToScdLineAddr, hdrTmsg.shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.shipToThirdLineAddr, hdrTmsg.shipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.shipToFrthLineAddr, hdrTmsg.shipToFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.shipToFirstRefCmntTxt, hdrTmsg.shipToFirstRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.shipToScdRefCmntTxt, hdrTmsg.shipToScdRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.shipToCtyAddr, hdrTmsg.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.shipToStCd, hdrTmsg.shipToStCd);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.shipToProvNm, hdrTmsg.shipToProvNm);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.shipToCntyNm, hdrTmsg.shipToCntyNm);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.shipToPostCd, hdrTmsg.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.shipToCtryCd, hdrTmsg.shipToCtryCd);

            Map<String, String> prchReqTpData = getPrchReqTpData(nPZC103001PMsg);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.sceOrdTpCd, prchReqTpData.get(NPZC103001Constant.COL_SCE_ORD_TP_CD));
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.trxSrcTpCd, prchReqTpData.get(NPZC103001Constant.COL_TRX_SRC_TP_CD));
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.invtyOrdTpCd, INVTY_ORD_TP.DC_TRANSFER);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.trxRefSrcTpCd, hdrTmsg.prchReqSrcTpCd);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.trxRefNum, hdrTmsg.prchReqNum);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.trxRefLineNum, info.prchReqLineNum);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.trxRefLineSubNum, info.prchReqLineSubNum.getValue().toString());
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.invtyOrdLineCostAmt, info.thisMthFobCostAmt);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.coaCmpyCd, info.coaCmpyCd);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.coaBrCd, info.coaBrCd);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.coaAcctCd, info.coaAcctCd);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.coaProdCd, info.coaProdCd);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.coaChCd, info.coaChCd);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.coaCcCd, info.coaCcCd);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.coaAfflCd, info.coaAfflCd);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.coaExtnCd, info.coaExtnCd);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.coaProjCd, info.coaProjCd);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.svcConfigMstrPk, info.svcConfigMstrPk);
            ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.rmvConfigFlg, ZYPConstant.FLG_OFF_N);
            List<PRCH_REQ_SERTMsg> serList = selectPRCH_REQ_SERTMsg(info.prchReqNum.getValue(), info.prchReqLineNum.getValue(), info.prchReqLineSubNum.getValue());
            if (serList != null) {
                int cnt = 0;
                for (PRCH_REQ_SERTMsg serInfo : serList) {
                    ZYPEZDItemValueSetter.setValue(nLZC301001PMsg.SerialInfo.no(cnt).serNum, serInfo.serNum);
                    cnt++;
                }
            }
            nLZC301001PMsgList.add(nLZC301001PMsg);
            prchReqDtlTMsgList.add(info);
        }
        return nLZC301001PMsgList;
    }

    /**
     * isAllocationRequest
     * @param prchReqLineTpCd String
     * @param prchReqRelDtTmTs String
     * @param prchReqRelStsCd String
     * @return boolean
     */
    private boolean isAllocationRequest(String prchReqLineTpCd, String prchReqRelDtTmTs, String prchReqRelStsCd) {

        PRCH_REQ_LINE_TPTMsg inMsg = new PRCH_REQ_LINE_TPTMsg();
        inMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        inMsg.prchReqLineTpCd.setValue(prchReqLineTpCd);
        PRCH_REQ_LINE_TPTMsg tMsg = (PRCH_REQ_LINE_TPTMsg) S21CacheTBLAccessor.findByKey(inMsg);

        if (tMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {

            // QC#28958
            if (PRCH_REQ_LINE_TP.INSOURCED_CH.equals(tMsg.prchReqLineTpCd.getValue()) && isTpShipConfMode()) {
                return true;
            }
            if (ZYPConstant.FLG_ON_Y.equals(tMsg.allocReqFlg.getValue()) && !ZYPCommonFunc.hasValue(prchReqRelDtTmTs) && PRCH_REQ_REL_STS.IN_COMPLETED.equals(prchReqRelStsCd)) {

                return true;
            }
        }

        return false;
    }

    /**
     * isInsourced
     * @param pMsg NPZC103001PMsg
     * @param idx int
     * @return boolean
     */
    private boolean isInsourced(NPZC103001PMsg pMsg, int idx) {

        PRCH_REQ_LINE_TPTMsg inMsg = new PRCH_REQ_LINE_TPTMsg();
        inMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
        inMsg.prchReqLineTpCd.setValue(pMsg.prchReqInfo.no(idx).prchReqLineTpCd.getValue());
        PRCH_REQ_LINE_TPTMsg tMsg = (PRCH_REQ_LINE_TPTMsg) S21CacheTBLAccessor.findByKey(inMsg);

        if (tMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {

            if (ZYPConstant.FLG_ON_Y.equals(tMsg.insrcFlg.getValue())) {

                return true;
            }
        }

        return false;
    }

    // START 2019/12/20 M.Naito [QC#54908,ADD]
    /**
     * isTechRequestLine
     * @param glblCmpyCd String
     * @param prchReqLineTpCd String
     * @return boolean
     */
    private boolean isTechRequestLine(String glblCmpyCd, String prchReqLineTpCd) {

        PRCH_REQ_LINE_TPTMsg inMsg = new PRCH_REQ_LINE_TPTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.prchReqLineTpCd.setValue(prchReqLineTpCd);
        PRCH_REQ_LINE_TPTMsg tMsg = (PRCH_REQ_LINE_TPTMsg) S21CacheTBLAccessor.findByKey(inMsg);

        if (tMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            if (PRCH_REQ_REC_TP.TECH_REQUEST.equals(tMsg.prchReqRecTpCd.getValue())) {
                return true;
            }
        }
        return false;
    }
    // END 2019/12/20 M.Naito [QC#54908,ADD]

    /**
     * getPrchReqTpData
     * @param pMsg NPZC103001PMsg
     * @return Map<String, String>
     */
    private Map<String, String> getPrchReqTpData(NPZC103001PMsg pMsg) {

        Map<String, String> result = new HashMap<String, String>();
        PRCH_REQ_TPTMsg inMsg = new PRCH_REQ_TPTMsg();
        inMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
        inMsg.prchReqTpCd.setValue(pMsg.prchReqTpCd.getValue());
        PRCH_REQ_TPTMsg tMsg = (PRCH_REQ_TPTMsg) S21CacheTBLAccessor.findByKey(inMsg);

        if (tMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {

            result.put(NPZC103001Constant.COL_SCE_ORD_TP_CD, tMsg.sceOrdTpCd.getValue());
            result.put(NPZC103001Constant.COL_TRX_SRC_TP_CD, tMsg.trxSrcTpCd.getValue());
        }

        return result;
    }

    /**
     * createBizProcLog
     * @param prchReqTMsg PRCH_REQTMsg
     * @param prchReqDtlTMsgList List<PRCH_REQ_DTLTMsg>
     */
    private void createBizProcLog(PRCH_REQTMsg prchReqTMsg, List<PRCH_REQ_DTLTMsg> prchReqDtlTMsgList) {
        createBizProcLog(prchReqTMsg, prchReqDtlTMsgList, false);
    }

    /**
     * createBizProcLog
     * @param prchReqTMsg PRCH_REQTMsg
     * @param prchReqDtlTMsgList List<PRCH_REQ_DTLTMsg>
     * @param isForceApprove boolean
     */
    private void createBizProcLog(PRCH_REQTMsg prchReqTMsg, List<PRCH_REQ_DTLTMsg> prchReqDtlTMsgList, boolean isForceApprove) {

        if (prchReqTMsg == null || prchReqDtlTMsgList == null || prchReqDtlTMsgList.isEmpty()) {

            return;
        }

        for (int i = 0; i < prchReqDtlTMsgList.size(); i++) {

            PRCH_REQ_BIZ_PROC_LOGTMsg prBizProcLogTMsg = new PRCH_REQ_BIZ_PROC_LOGTMsg();
            PRCH_REQ_DTLTMsg prDtlTMsg = prchReqDtlTMsgList.get(i);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.glblCmpyCd, prchReqTMsg.glblCmpyCd);
            String prBizProcLogPk = ZYPOracleSeqAccessor.getNumberString(NPZC103001Constant.PRCH_REQ_BIZ_PROC_LOG_SQ, NPZC103001Constant.PRCH_REQ_BIZ_PROC_LOG_SQ_DIGIT);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqBizProcLogPk, new BigDecimal(prBizProcLogPk));
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqNum, prchReqTMsg.prchReqNum);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqLineNum, prDtlTMsg.prchReqLineNum);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqLineSubNum, prDtlTMsg.prchReqLineSubNum);
            if (isForceApprove) {
                ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqLogEventTxt, getEventTxt(NPZC103001Constant.EVENT_APPROVAL));
            } else {
                ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqLogEventTxt, getEventTxt(this.event));
            }
            // START 2023/02/06 A.Cullano [QC#60993, MOD]
            // ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqLogModeTxt, getModeTxt());
            if (PRCH_REQ_SRC_TP.MNX_SHIP_CONFIRMATION.equals(prchReqTMsg.prchReqSrcTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqLogModeTxt, NPZC103001Constant.MNX);
            } else {
                ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqLogModeTxt, getModeTxt());
            }
            // END 2023/02/06 A.Cullano [QC#60993, MOD]

            if (ZYPCommonFunc.hasValue(prchReqTMsg.ezInUserID)) {

                String ezInUser = prchReqTMsg.ezInUserID.getValue();

                if (ezInUser.length() > NPZC103001Constant.PSN_CD_LENGTH) {

                    ezInUser = ezInUser.substring(0, NPZC103001Constant.PSN_CD_LENGTH);
                }

                ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqUpdByPsnCd, ezInUser);
            }

            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqStsCd, prchReqTMsg.prchReqStsCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqLineStsCd, prDtlTMsg.prchReqLineStsCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqApvlStsCd, prchReqTMsg.prchReqApvlStsCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqApvlDt, prchReqTMsg.prchReqApvlDt);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqApvlByPsnCd, prchReqTMsg.prchReqApvlByPsnCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqApvlByNm, prchReqTMsg.prchReqApvlByNm);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqRelStsCd, prDtlTMsg.prchReqRelStsCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqRelDtTmTs, prDtlTMsg.prchReqRelDtTmTs);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqRelErrMsgTxt, prDtlTMsg.prchReqRelErrMsgTxt);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.poOrdNum, prDtlTMsg.poOrdNum);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.poOrdDtlLineNum, prDtlTMsg.poOrdDtlLineNum);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.invtyOrdNum, prDtlTMsg.invtyOrdNum);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.invtyOrdLineNum, prDtlTMsg.invtyOrdLineNum);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.wrkOrdNum, prDtlTMsg.wrkOrdNum);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.wrkOrdDtlLineNum, prDtlTMsg.wrkOrdDtlLineNum);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.procrTpCd, prDtlTMsg.procrTpCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.srcInvtyLocCd, prDtlTMsg.srcInvtyLocCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.srcRtlWhCd, prDtlTMsg.srcRtlWhCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.srcRtlSwhCd, prDtlTMsg.srcRtlSwhCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prntVndCd, prDtlTMsg.prntVndCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.vndCd, prDtlTMsg.vndCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.rqstRcvDt, prDtlTMsg.rqstRcvDt);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.rqstRcvTm, prDtlTMsg.rqstRcvTm);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.mdseCd, prDtlTMsg.mdseCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.origRqstMdseCd, prDtlTMsg.origRqstMdseCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.aslMdseCd, prDtlTMsg.aslMdseCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqQty, prDtlTMsg.prchReqQty);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqDispQty, prDtlTMsg.prchReqDispQty);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqDsplUomCd, prDtlTMsg.prchReqDsplUomCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqRelQty, prDtlTMsg.prchReqRelQty);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqBalQty, prDtlTMsg.prchReqBalQty);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqInsrcQty, prDtlTMsg.prchReqInsrcQty);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqCancQty, prDtlTMsg.prchReqCancQty);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.soNum, prDtlTMsg.soNum);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.proNum, prDtlTMsg.proNum);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.shipDtTmTs, prDtlTMsg.shipDtTmTs);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.shipQty, prDtlTMsg.shipQty);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.rwsNum, prDtlTMsg.rwsNum);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.rwsCloDtTmTs, prDtlTMsg.rwsCloDtTmTs);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.rwsPutAwayQty, prDtlTMsg.rwsPutAwayQty);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.backToTechQty, prDtlTMsg.backToTechQty);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.thisMthFobCostAmt, prDtlTMsg.thisMthFobCostAmt);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.aslDtlPk, prDtlTMsg.aslDtlPk);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.aslUnitPrcAmt, prDtlTMsg.aslUnitPrcAmt);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.entDealNetUnitPrcAmt, prDtlTMsg.entDealNetUnitPrcAmt);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.entPoDtlDealNetAmt, prDtlTMsg.entPoDtlDealNetAmt);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.entFuncNetUnitPrcAmt, prDtlTMsg.entFuncNetUnitPrcAmt);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.entPoDtlFuncNetAmt, prDtlTMsg.entPoDtlFuncNetAmt);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.ccyCd, prDtlTMsg.ccyCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.exchRate, prDtlTMsg.exchRate);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.lineBizTpCd, prchReqTMsg.lineBizTpCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.frtCondCd, prDtlTMsg.frtCondCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.shpgSvcLvlCd, prDtlTMsg.shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.carrCd, prDtlTMsg.carrCd);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqLineCmntTxt, prDtlTMsg.prchReqLineCmntTxt);
            ZYPEZDItemValueSetter.setValue(prBizProcLogTMsg.prchReqFrzFlg, prDtlTMsg.prchReqFrzFlg);

            S21ApiTBLAccessor.insert(prBizProcLogTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prBizProcLogTMsg.getReturnCode())) {

                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0233E);
                return;
            }
        }

        return;
    }

    /**
     * getModeTxt
     * @return String
     */
    private String getModeTxt() {

        PRCH_REQ_LOG_MODETMsg paramTMsg = new PRCH_REQ_LOG_MODETMsg();
        ZYPEZDItemValueSetter.setValue(paramTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(paramTMsg.prchReqLogModeCd, this.mode);
        PRCH_REQ_LOG_MODETMsg modeTMsg = (PRCH_REQ_LOG_MODETMsg) EZDTBLAccessor.findByKey(paramTMsg);

        if (modeTMsg != null) {

            return modeTMsg.prchReqLogModeDescTxt.getValue();
        }

        return null;
    }

    /**
     * getEventTxt
     * @return String
     */
    private String getEventTxt(String eventCd) {

        PRCH_REQ_LOG_EVENTTMsg paramTMsg = new PRCH_REQ_LOG_EVENTTMsg();
        ZYPEZDItemValueSetter.setValue(paramTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(paramTMsg.prchReqLogEventCd, eventCd);
        PRCH_REQ_LOG_EVENTTMsg eventTMsg = (PRCH_REQ_LOG_EVENTTMsg) EZDTBLAccessor.findByKey(paramTMsg);

        if (eventTMsg != null) {

            return eventTMsg.prchReqLogEventDescTxt.getValue();
        }

        return null;
    }

    /**
     * Get Service Task
     * @param glblCmpyCd String
     * @param svcTaskNum String
     * @param fsrNum String
     * @param svcMachSerNum String
     * @return Map<String, Object>
     */
    private Map<String, Object> getSvcTask(String glblCmpyCd, String svcTaskNum, String fsrNum, String svcMachSerNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("svcTaskNum", svcTaskNum);
        queryParam.put("fsrNum", fsrNum);
        queryParam.put("svcMachSerNum", svcMachSerNum);
        queryParam.put("svcTaskStsList", new String[] {SVC_TASK_STS.CLOSED, SVC_TASK_STS.CANCELLED });

        Map<String, Object> svcTaskMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getSvcTask", queryParam);
        return svcTaskMap;
    }

    /**
     * Get Service Task Check Control
     * @param glblCmpyCd String
     * @param prchReqTpCd String
     * @return Map<String, Object>
     */
    private Map<String, Object> getSvcTaskCheckControl(String glblCmpyCd, String prchReqTpCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put(NPZC103001Constant.DB_PARAM_DS_COND_CONST_GRP_ID, NPZC103001Constant.DS_COND_CONST_GRP_ID);
        queryParam.put("prchReqTpCd", prchReqTpCd);

        Map<String, Object> svcTaskCheckControlMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getSvcTaskCheckControl", queryParam);
        return svcTaskCheckControlMap;
    }

    /**
     * updShpgPlnMdse
     * @param shpgPlnNum
     * @param mdseCd
     */
    private void updShpgPlnMdse(String shpgPlnNum, String mdseCd) {

        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        shpgPlnTMsg.glblCmpyCd.setValue(glblCmpyCd);
        shpgPlnTMsg.shpgPlnNum.setValue(shpgPlnNum);

        shpgPlnTMsg = (SHPG_PLNTMsg) S21ApiTBLAccessor.findByKeyForUpdate(shpgPlnTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgPlnTMsg.getReturnCode())) {
            return;
        }

        if (!mdseCd.equals(shpgPlnTMsg.mdseCd.getValue())) {
            shpgPlnTMsg.mdseCd.setValue(mdseCd);
            S21ApiTBLAccessor.update(shpgPlnTMsg);
        }
    }

    /**
     * getTechReqSumQty
     * @param glblCmpyCd String
     * @param prNum String
     * @param isRwsPutAwayQty boolean
     * @return BigDeicmal isRwsPutAwayQty=true:SUM(RWQ_PUT_AWAY_QTY),isRwsPutAwayQty=false:SUM(PRCH_REQ_QTY)
     */
    /* START 2022/10/07 R.Azucena [QC#60664, DEL]
    private BigDecimal getTechReqSumQty(String glblCmpyCd, String prNum, boolean isRwsPutAwayQty) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("prchReqNum", prNum);
        queryParam.put("prchReqLineStsCdExcludeList", new String[] {PRCH_REQ_LINE_STS.CANCELLED });
        if (!isRwsPutAwayQty) {
            queryParam.put("prchReqLineTpCdList", new String[] {PRCH_REQ_LINE_TP.MISSING_PARTS });
        }

        Map<String, BigDecimal> qtyMap = (Map<String, BigDecimal>) this.ssmBatchClient.queryObject("getTechReqSumQty", queryParam);

        if (isRwsPutAwayQty) {
            return qtyMap.get(NPZC103001Constant.SUM_RWS_PUT_AWAY_QTY);
        } else {
            return qtyMap.get(NPZC103001Constant.SUM_PRCH_REQ_QTY);
        }
    } */
    // END 2022/10/07 R.Azucena [QC#60664, DEL]

    /**
     * QC#8295
     * updatePurchaseRequisitionHederClose
     * @param pMsg NPZC103001PMsg
     */
    private void updatePurchaseRequisitionHederClose(NPZC103001PMsg pMsg) {

        PRCH_REQTMsg prchReqTMsg = selectPRCH_REQTMsgForUpdate(pMsg.prchReqNum.getValue());
        List<PRCH_REQ_DTLTMsg> prchReqDtlTMsgList = selectPRCH_REQ_DTLTMsgForUpdateAll(pMsg, NPZC103001Constant.ALL);
        prchReqTMsg.prchReqStsCd.setValue(PRCH_REQ_STS.CLOSED);
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqCloDtTmTs, getCurrentDtTmTs());

        // QC#56543 2020/05/08 Start
        setupPMsgforUpdate(pMsg, prchReqTMsg);
        // QC#56543 2020/05/08 End
        S21FastTBLAccessor.update(prchReqTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prchReqTMsg.getReturnCode())) {

            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0100E);
            return;
        }
        // QC#54552
        for (PRCH_REQ_DTLTMsg prchReqDtlTMsg : prchReqDtlTMsgList) {

            if (PRCH_REQ_LINE_STS.PARTIALLY_RECEIVED.equals(prchReqDtlTMsg.prchReqLineStsCd.getValue()) || PRCH_REQ_LINE_STS.RECEIVED.equals(prchReqDtlTMsg.prchReqLineStsCd.getValue())) {
                // PRCH_REQ_LINE_STS_CD
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.RECEIVED);
            } else if (PRCH_REQ_LINE_STS.PARTIALLY_SHIPPED.equals(prchReqDtlTMsg.prchReqLineStsCd.getValue()) || PRCH_REQ_LINE_STS.SHIPPED.equals(prchReqDtlTMsg.prchReqLineStsCd.getValue())) {
                // PRCH_REQ_LINE_STS_CD
                ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.SHIPPED);
            } else {
                // Cancel
                BigDecimal prchReqQty = prchReqDtlTMsg.prchReqQty.getValue();
                BigDecimal shipQty = prchReqDtlTMsg.shipQty.getValue();
                BigDecimal rwsPutAwayQty = prchReqDtlTMsg.rwsPutAwayQty.getValue();
                BigDecimal prchReqCancQty = prchReqDtlTMsg.prchReqCancQty.getValue();

                if (BigDecimal.ZERO.compareTo(rwsPutAwayQty) < 0) {
                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.RECEIVED);
                } else if (BigDecimal.ZERO.compareTo(shipQty) < 0) {
                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.SHIPPED);    
                } else if (prchReqQty.compareTo(prchReqCancQty) == 0) {
                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.CANCELLED);
                    // QC#56543 2020/05/08 Start
                    if(PRCH_REQ_TP.KITTING.equals(pMsg.prchReqTpCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqBalQty, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.NO_NEED_TO_RELEASE);
                    }
                    // QC#56543 2020/05/08 End
                } else if (PRCH_REQ_LINE_STS.OPEN.equals(prchReqDtlTMsg.prchReqLineStsCd.getValue())){
                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineStsCd, PRCH_REQ_LINE_STS.CANCELLED);
                    ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqCancQty, prchReqQty);
                    // QC#56543 2020/05/08 Start
                    if(PRCH_REQ_TP.KITTING.equals(pMsg.prchReqTpCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqBalQty, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.NO_NEED_TO_RELEASE);
                    }                    
                    // QC#56543 2020/05/08 End
                }
            }
        }

        int ret = S21FastTBLAccessor.update(prchReqDtlTMsgList.toArray(new PRCH_REQ_DTLTMsg[prchReqDtlTMsgList.size()]));

        if (ret != prchReqDtlTMsgList.size()) {

            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0291E);
            return;
        }

        // Create History
        createBizProcLog(prchReqTMsg, prchReqDtlTMsgList);
    }

    /**
     * getCurrentDtTmTs
     * @return String
     */
    private String getCurrentDtTmTs() {
        return ZYPDateUtil.getSalesDate() + ZYPDateUtil.getCurrentSystemTime(NPZC103001Constant.TIME_PATTERN);
    }

    /**
     * getTechLocation
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return Map<String, Object>
     */
    private static Map<String, Object> getTechLocation(String glblCmpyCd, String rtlWhCd) {

        if (!ZYPCommonFunc.hasValue(rtlWhCd)) {
            return null;
        }

        RTL_WHTMsg tMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, rtlWhCd);
        tMsg = (RTL_WHTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.ctryCd) && ZYPCommonFunc.hasValue(tMsg.postCd)) {
            Map<String, Object> rslt = new HashMap<String, Object>();
            rslt.put("POST_CD", tMsg.postCd.getValue());
            rslt.put("CTRY_CD", tMsg.ctryCd.getValue());
            return rslt;
        }

        return null;
    }

    /**
     * <pre>
     * isPreApprovedWhOwnr
     * Set Approval Status to Pre-Approved if target WH is included in specified WH owners.
     * Condition:
     * + REC_TP is "PO Req"
     * + APVL_STS is "Submit for approval"
     * + Target WH's owner is in "PR_PREAPVL_WH_OWNR_CD"
     * </pre>
     * @param pMsg NPZC103001PMsg
     * @return boolean
     */
    private boolean isPreApprovedWhOwnr(NPZC103001PMsg pMsg) {
        if (!PRCH_REQ_REC_TP.PO_REQUISITION.equals(pMsg.prchReqRecTpCd.getValue())) {
            return false;
        }
        if (!PRCH_REQ_APVL_STS.SUBMIT_FOR_APPROVAL.equals(pMsg.prchReqApvlStsCd.getValue())) {
            return false;
        }

        String prPreapvlWhOwnrCd = ZYPCodeDataUtil.getVarCharConstValue(NPZC103001Constant.PR_PREAPVL_WH_OWNR_CD, pMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(prPreapvlWhOwnrCd)) {
            return false;
        }

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String prchReqNum = pMsg.prchReqNum.getValue();
        String[] whOwnrCdArray = prPreapvlWhOwnrCd.split(",");
        for (int n = 0; n < whOwnrCdArray.length; ++n) {
            if (ZYPCommonFunc.hasValue(whOwnrCdArray[n])) {
                whOwnrCdArray[n] = whOwnrCdArray[n].trim();
            }
        }
        int count = countDestRtlWhInWhOwnr(glblCmpyCd, prchReqNum, whOwnrCdArray);
        return (count > 0);
    }

    /**
     * countDestRtlWhInWhOwnr
     * @param glblCmpyCd String
     * @param prchReqNum String
     * @param whOwnrCdArray String[]
     * @return int
     */
    private int countDestRtlWhInWhOwnr(String glblCmpyCd, String prchReqNum, String[] whOwnrCdArray) {
        HashMap<String, Object> bindParams = new HashMap<String, Object>();
        bindParams.put("glblCmpyCd", glblCmpyCd);
        bindParams.put("prchReqNum", prchReqNum);
        bindParams.put("whOwnrCdList", whOwnrCdArray);

        return (Integer) this.ssmBatchClient.queryObject("countDestRtlWhInWhOwnr", bindParams);
    }

    /**
     * updatePoReqToPreApproved
     * @param pMsg NPZC103001PMsg
     */
    private void updatePoReqToPreApproved(NPZC103001PMsg pMsg) {

        ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.PRE_APPROVED);

        PRCH_REQTMsg prchReqTMsg = selectPRCH_REQTMsgForUpdate(pMsg);
        // PRCH_REQ_APVL_STS_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlStsCd, pMsg.prchReqApvlStsCd);
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlDt, pMsg.procDt);
        // PRCH_REQ_APVL_BY_PSN_CD
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlByPsnCd, pMsg.prchReqRqstByPsnCd);
        // PRCH_REQ_APVL_BY_NM
        ZYPEZDItemValueSetter.setValue(prchReqTMsg.prchReqApvlByNm, getFullPersonName(pMsg.prchReqRqstByPsnCd.getValue()));

        S21FastTBLAccessor.update(prchReqTMsg);
        if (prchReqTMsg != null && !EZDTBLAccessor.RTNCD_NORMAL.equals(prchReqTMsg.getReturnCode())) {
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0100E);
            return;
        }

        List<PRCH_REQ_DTLTMsg> prDtlTMsgList = getDtlTMsgListForUpdateApproval(pMsg);
        int ret = S21FastTBLAccessor.update(prDtlTMsgList.toArray(new PRCH_REQ_DTLTMsg[prDtlTMsgList.size()]));

        if (ret != prDtlTMsgList.size()) {

            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0291E);
            return;
        }

        // Create History
        List<PRCH_REQ_DTLTMsg> prchReqDtlList = selectPRCH_REQ_DTLTMsg(pMsg);
        createBizProcLog(prchReqTMsg, prchReqDtlList, true);
    }

    /**
     * checkHazmat
     * @param pMsg NPZC103001PMsg
     * @param index int
     * @return true:Hazmat, false:NonHazmat boolean
     */
    private boolean checkHazmat(NPZC103001PMsg pMsg, int index) {

        if (this.whOwnrCdList == null) {
            // Non-hazmat.
            return false;
        }

        Map<String, Object> checkHazmatMap = null;
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        queryParam.put("prchReqNum", pMsg.prchReqNum.getValue());
        queryParam.put("prchReqLineNum", pMsg.prchReqInfo.no(index).prchReqLineNum.getValue());
        queryParam.put("prchReqLineSubNum", pMsg.prchReqInfo.no(index).prchReqLineSubNum.getValue());
        queryParam.put("hazMatFlgY", ZYPConstant.FLG_ON_Y);
        queryParam.put("whOwnrCdList", this.whOwnrCdList);
        checkHazmatMap = (Map<String, Object>) this.ssmBatchClient.queryObject("checkHazmat", queryParam);

        if (checkHazmatMap == null || checkHazmatMap.isEmpty()) {
            // Non-hazmat.
            return false;
        }
        if (ZYPConstant.FLG_ON_Y.equals(checkHazmatMap.get(NPZC103001Constant.HAZ_MAT_FLG))) {
            // hasmat.
            return true;
        }

        return false;
    }


    // START 2017/09/05 K.Kameoka [Sol#369(QC#19243),Update]
    /**
     * checkSendMail
     * @param pMsg NPZC103001PMsg
     */
    private void checkSendMail(NPZC103001PMsg pMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlWhCd", pMsg.rqstTechTocCd.getValue());
        ssmParam.put("prchReqTpCd", pMsg.prchReqTpCd.getValue());
        if (pMsg.prchReqInfo.no(0) != null && ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(0).shpgSvcLvlCd)) {
            ssmParam.put("shpgSvcLvlCds", new String[] {pMsg.prchReqInfo.no(0).shpgSvcLvlCd.getValue(), NPZC103001Constant.ASTERISK });
        } else {
            ssmParam.put("shpgSvcLvlCds", new String[] {NPZC103001Constant.ASTERISK });
        }
        String checkSendMail = (String) this.ssmBatchClient.queryObject("getInsrcCtrlInfo", ssmParam);

        // QC#57659 Add Start
        if (PRCH_REQ_TP.PREMIUM_RUSH.equals(pMsg.prchReqTpCd.getValue())) {
            checkSendMail = ZYPConstant.FLG_OFF_N;
        }
        // QC#57659 Add End
        // QC#30102
        List<NPZC103001_prchReqInfoPMsg> choiceLine = new ArrayList<NPZC103001_prchReqInfoPMsg>();
        if (ZYPConstant.FLG_ON_Y.equals(checkSendMail)) {
            // Source WH Check.( Choice WH
            for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
                
                if (!ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(i).procrTpCd)) {
                    continue;
                }
                String procrTpCd = pMsg.prchReqInfo.no(i).procrTpCd.getValue();
                if (!PROCR_TP.WAREHOUSE.equals(procrTpCd) || !ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(i).srcInvtyLocCd)) {
                    continue;
                }
                // WH_OWNR_CD
                Map<String, String> whOwnrMap = getWhOwnrCd(pMsg.prchReqInfo.no(i).srcInvtyLocCd.getValue(), pMsg.procDt.getValue());
                String whOwnr = null;
                if (whOwnrMap != null) {
                    whOwnr = whOwnrMap.get(NPZC103001Constant.WH_OWNR_CD);
                    if (WH_OWNR.CHOICE.equals(whOwnr)) {
                        choiceLine.add(pMsg.prchReqInfo.no(i));
                    }
                } else {
                    continue;
                }
            }
        }

        if (choiceLine.isEmpty()) {
            checkSendMail = ZYPConstant.FLG_OFF_N;
        }

        if (ZYPConstant.FLG_ON_Y.equals(checkSendMail)) {
            // update PRCH_REQ_DTL set PRCH_REQ_REL_STS_CD = 1

            PRCH_REQTMsg headerTMsg = new PRCH_REQTMsg();
            ZYPEZDItemValueSetter.setValue(headerTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(headerTMsg.prchReqNum, pMsg.prchReqNum);

            headerTMsg = (PRCH_REQTMsg) EZDTBLAccessor.findByKey(headerTMsg);
            if (headerTMsg == null) {
                return;
            }

            if (!(PRCH_REQ_APVL_STS.APPROVED.equals(headerTMsg.prchReqApvlStsCd.getValue()) || PRCH_REQ_APVL_STS.PRE_APPROVED.equals(headerTMsg.prchReqApvlStsCd.getValue()))) {
                return;
            }

            for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
                PRCH_REQ_DTLTMsg paramTMsg = new PRCH_REQ_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(paramTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(paramTMsg.prchReqNum, pMsg.prchReqNum);
                ZYPEZDItemValueSetter.setValue(paramTMsg.prchReqLineNum, pMsg.prchReqInfo.no(i).prchReqLineNum);
                ZYPEZDItemValueSetter.setValue(paramTMsg.prchReqLineSubNum, pMsg.prchReqInfo.no(i).prchReqLineSubNum);

                PRCH_REQ_DTLTMsg updTMsg = (PRCH_REQ_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(paramTMsg);
                if (updTMsg == null) {
                    return;
                }
                if (PRCH_REQ_REL_STS.IN_COMPLETED.equals(updTMsg.prchReqRelStsCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(updTMsg.prchReqRelStsCd, PRCH_REQ_REL_STS.COMPLEATED);
                }

                S21FastTBLAccessor.update(updTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
                    this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0291E);
                    return;
                }
            }

            sendMail(pMsg, choiceLine, headerTMsg);
        }

    }

    /**
     * sendMail
     * @param pMsg NPZC103001PMsg
     * @param choiceLine List<NPZC103001_prchReqInfoPMsg>
     * @param headerTMsg PRCH_REQTMsg
     */
    private void sendMail(NPZC103001PMsg pMsg, List<NPZC103001_prchReqInfoPMsg> choiceLine, PRCH_REQTMsg headerTMsg) {
        //get Tech Info
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        String crlf = System.getProperty("line.separator");

        // 2019/01/10 QC#29877 Del Start
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("rtlWhCd", pMsg.rqstTechTocCd.getValue());
//
//        Map<String, Object> techInfoMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getTechInfo", ssmParam);
//        String techMailAddr = (String) techInfoMap.get(NPZC103001Constant.COL_EMAIL);
//        String techCellPhoNum = (String) techInfoMap.get(NPZC103001Constant.COL_PHO_NUM);
//        String telNum = (String) techInfoMap.get(NPZC103001Constant.COL_TEL_NUM);
//        // QC#22212
//        String techMblMsgAddr = (String) techInfoMap.get(NPZC103001Constant.COL_TECH_MBL_MSG_ADDR);
//        // 2018/11/28 QC#28735 Add Start
//        String techTocNm = (String) techInfoMap.get(NPZC103001Constant.COL_TECH_TOC_NM);
//        // 2018/11/28 QC#28735 Add End
        // 2019/01/10 QC#29877 Del End
        // 2019/01/10 QC#29877 Add Start
        TechInfoBean techInfoBean = getTechInfo(glblCmpyCd, pMsg.rqstTechTocCd.getValue());
        String techMailAddr = techInfoBean.getTechMailAddr();
        String techCellPhoNum = techInfoBean.getTechCellPhoNum();
        String telNum = techInfoBean.getTelNum();
        String techMblMsgAddr = techInfoBean.getTechMblMsgAddr();
        String techTocNm = techInfoBean.getTechTocNm();
        // 2019/01/10 QC#29877 Add End

        // QC#21911 Modify start.
        String carrCd = null;
        String shpgSvcLvlCd = null;
        ssmParam.put("whOwnrCd", NPZC103001Constant.CHO);
        if (pMsg.prchReqInfo.no(0) != null) {
            carrCd = pMsg.prchReqInfo.no(0).carrCd.getValue();
            shpgSvcLvlCd = pMsg.prchReqInfo.no(0).shpgSvcLvlCd.getValue();
        }
        String svcLvlnm = getTplSvcLvlNm(glblCmpyCd, carrCd, shpgSvcLvlCd);
        // QC#21911 Modify end.

        S21Mail mail = new S21Mail(glblCmpyCd);
        // Get mail group
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, NPZC103001Constant.MAIL_TO_GROUP_ID);
        // Get mail group
        S21MailGroup groupCc1 = new S21MailGroup(glblCmpyCd, NPZC103001Constant.MAIL_CC1_GROUP_ID);
        // Get mail group
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, NPZC103001Constant.MAIL_FROM_GROUP_ID);

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

        // QC#22210
        S21MailTemplate template = null;
        if (PRCH_REQ_TP.PREMIUM_RUSH.equals(pMsg.prchReqTpCd.getValue())) {
            template = new S21MailTemplate(glblCmpyCd, NPZC103001Constant.MAIL_TEMPLATE_ID_PREMIUM_RUSH);
        } else {
            template = new S21MailTemplate(glblCmpyCd, NPZC103001Constant.MAIL_TEMPLATE_ID);
        }
        if (template == null) {
            throw new S21AbendException(NPZC103001Constant.NPZM0161E);
        }

        //rqstTechTocCd
        template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_TECH_TOC_CD, pMsg.rqstTechTocCd.getValue());

        // 2018/11/28 QC#28735 Add Start
        // rqstTechTocNm
        if (ZYPCommonFunc.hasValue(techTocNm)) {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_TECH_TOC_NM, techTocNm);
        } else {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_TECH_TOC_NM, "");
        }
        // 2018/11/28 QC#28735 Add End

        //prchReqNum
        template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_PRCH_REQ_NUM, pMsg.prchReqNum.getValue());
        //rqstRcvDt(dtl)
        SimpleDateFormat sdf = new SimpleDateFormat(NPZC103001Constant.RQST_RCV_DT_DATE_FORMAT);
        Date rqstRcvDt = null;
        if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(0).rqstRcvDt.getValue())) {
            try {
                rqstRcvDt = sdf.parse(pMsg.prchReqInfo.no(0).rqstRcvDt.getValue());
                sdf.applyPattern(NPZC103001Constant.EMAIL_DATE_FORMAT);
            } catch (ParseException e) {
                rqstRcvDt = null;
            }
            if (rqstRcvDt != null) {
                template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_RQST_RCV_DT, sdf.format(rqstRcvDt));
            }
        }
        // START 2023/02/08 T.Kuroda [QC#60966, ADD]
        //rqstShipDt(dtl)
        SimpleDateFormat sdfShipDt = new SimpleDateFormat(NPZC103001Constant.RQST_SHIP_DT_DATE_FORMAT);
        Date rqstShipDt = null;
        if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(0).rqstShipDt.getValue())) {
            try {
                rqstShipDt = sdfShipDt.parse(pMsg.prchReqInfo.no(0).rqstShipDt.getValue());
                sdfShipDt.applyPattern(NPZC103001Constant.EMAIL_DATE_FORMAT);
            } catch (ParseException e) {
                rqstShipDt = null;
            }
            if (rqstShipDt != null) {
                template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_RQST_SHIP_DT, sdfShipDt.format(rqstShipDt));
            }
        }
        // END   2023/02/08 T.Kuroda [QC#60966, ADD]
        //shpgSvcLvlCd(dtl)
        template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_SHPG_SVC_LVL_CD, svcLvlnm);
        //shipToLocNm
        template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_SHIP_TO_LOC_NM, pMsg.shipToLocNm.getValue());
        //shipToFirstLineAddr&ScdLineAddr&ThirdLineAddr&FrthLineAddr
        StringBuffer sbShipToAddr = new StringBuffer();

        if (ZYPCommonFunc.hasValue(pMsg.shipToFirstLineAddr.getValue())) {
            sbShipToAddr.append(pMsg.shipToFirstLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(pMsg.shipToScdLineAddr.getValue())) {
            if (sbShipToAddr.length() > 0) {
                sbShipToAddr.append(crlf).append(pMsg.shipToScdLineAddr.getValue());
            } else {
                sbShipToAddr.append(pMsg.shipToScdLineAddr.getValue());
            }
        }
        if (ZYPCommonFunc.hasValue(pMsg.shipToThirdLineAddr.getValue())) {
            if (sbShipToAddr.length() > 0) {
                sbShipToAddr.append(crlf).append(pMsg.shipToThirdLineAddr.getValue());
            } else {
                sbShipToAddr.append(pMsg.shipToThirdLineAddr.getValue());
            }
        }
        if (ZYPCommonFunc.hasValue(pMsg.shipToFrthLineAddr.getValue())) {
            if (sbShipToAddr.length() > 0) {
                sbShipToAddr.append(crlf).append(pMsg.shipToFrthLineAddr.getValue());
            } else {
                sbShipToAddr.append(pMsg.shipToFrthLineAddr.getValue());
            }
        }

        template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_SHIP_TO_FIRST_LINE_ADDR, sbShipToAddr.toString());

        if (ZYPCommonFunc.hasValue(pMsg.prchReqNum)) {
            ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("prchReqNum", pMsg.prchReqNum.getValue());
            // QC#21657-1 Modify
            if(ZYPCommonFunc.hasValue(pMsg.shipToCustCd)){
                ssmParam.put("shipToCustCd", pMsg.shipToCustCd.getValue());
            }

            // get PR Customer
            Map<String, Object> shipToInfoMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getPrCustomer", ssmParam);
            if (shipToInfoMap != null) {
                template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_SHIP_TO_LOC_NM, (String) shipToInfoMap.get(NPZC103001Constant.COL_LOC_NM));
                //shipToFirstLineAddr&ScdLineAddr&ThirdLineAddr&FrthLineAddr
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
                //city/state/post
                template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_CITY_STATE_POST, sbForCityStatePost.toString());
            }

        }
        // techCellPhoNum/telNum
        if (ZYPCommonFunc.hasValue(techCellPhoNum)) {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_TECH_CELL_PHO_NUM, techCellPhoNum);
        } else {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_TECH_CELL_PHO_NUM, telNum);
        }
        //emlAddr(dtl)
        // QC#22212
        if (PRCH_REQ_TP.PREMIUM_RUSH.equals(pMsg.prchReqTpCd.getValue()) || PRCH_REQ_TP.RUSH.equals(pMsg.prchReqTpCd.getValue())) {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_EML_ADDR, techMblMsgAddr);
        } else {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_EML_ADDR, techMailAddr);
        }
        //message(dtl) 
        StringBuilder sbForMessage = new StringBuilder();
        for (int i = 0; i < choiceLine.size(); i++) {
            NPZC103001_prchReqInfoPMsg apMsg = choiceLine.get(i);
            sbForMessage.append(NPZC103001Constant.EMAIL_PARAM_MATERIAL);
            sbForMessage.append(apMsg.mdseCd.getValue());
            sbForMessage.append(crlf);
            sbForMessage.append(NPZC103001Constant.EMAIL_PARAM_QTY);
            sbForMessage.append(apMsg.prchReqQty.getValueInt());
            sbForMessage.append(crlf);
        }

        template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_MESSAGE, sbForMessage.toString());

        // QC#50072
        if (ZYPCommonFunc.hasValue(pMsg.ctacPsnNm)) {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_CTAC_PSN_NM, pMsg.ctacPsnNm.getValue());
        } else if (ZYPCommonFunc.hasValue(headerTMsg.ctacPsnNm)) {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_CTAC_PSN_NM, headerTMsg.ctacPsnNm.getValue());
        }

        // Set mail subject
        mail.setSubject(template.getSubject(NPZC103001Constant.ML_LANG), NPZC103001Constant.ML_LANG_CD);
        mail.setMailTemplate(template);

        // Post
        mail.postMail();

    }
    // END 2017/09/05 K.Kameoka [Sol#369(QC#19243),ADD]

    // QC#21911 Add method.
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
    /**
     * 
     * @param glblCmpyCd
     * @param vndCd
     * @param mdseCd
     * @param prInterFaceMap
     * @return
     */
    private Map<String, Object> getAslInfo(String glblCmpyCd, String vndCd, String mdseCd) {

        boolean isError = false;

        // Get ASL Info
        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("GLBL_CMPY_CD", glblCmpyCd);
        queryParam.put("SLS_DT", ZYPDateUtil.getSalesDate(glblCmpyCd));
        queryParam.put("VND_CD", vndCd);
        queryParam.put("MDSE_CD", mdseCd);
        queryParam.put("MIN_START_DATE", NPZC103001Constant.MIN_START_DATE_VAL);
        queryParam.put("MAX_START_DATE", NPZC103001Constant.MAX_START_DATE_VAL);

        Map<String, Object> aslInfoMap = (Map<String, Object>) ssmBatchClient.queryObject("getAslInfoByVnd", queryParam);
        
        return aslInfoMap ;

    }

    /**
     * sendMailRushCho
     * @param pMsg NPZC103001PMsg
     * @param prTMsg PRCH_REQTMsg
     * @param prdTMsg PRCH_REQ_DTLTMsg
     */
    private void sendMailRushCho(NPZC103001PMsg pMsg, PRCH_REQTMsg prTMsg, PRCH_REQ_DTLTMsg prdTMsg) {
        //get Tech Info
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        String crlf = System.getProperty("line.separator");

        // 2019/01/10 QC#29877 Del Start
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("rtlWhCd", prTMsg.rqstTechTocCd.getValue());
//
//        Map<String, Object> techInfoMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getTechInfo", ssmParam);
//        String techMailAddr = (String) techInfoMap.get(NPZC103001Constant.COL_EMAIL);
//        String techCellPhoNum = (String) techInfoMap.get(NPZC103001Constant.COL_PHO_NUM);
//        String telNum = (String) techInfoMap.get(NPZC103001Constant.COL_TEL_NUM);
//        String techMblMsgAddr = (String) techInfoMap.get(NPZC103001Constant.COL_TECH_MBL_MSG_ADDR);
//        // 2018/11/28 QC#28735 Add Start
//        String techTocNm = (String) techInfoMap.get(NPZC103001Constant.COL_TECH_TOC_NM);
//        // 2018/11/28 QC#28735 Add End
        // 2019/01/10 QC#29877 Del End
        // 2019/01/10 QC#29877 Add Start
        TechInfoBean techInfoBean = getTechInfo(glblCmpyCd, prTMsg.rqstTechTocCd.getValue());
        String techMailAddr = techInfoBean.getTechMailAddr();
        String techCellPhoNum = techInfoBean.getTechCellPhoNum();
        String telNum = techInfoBean.getTelNum();
        String techMblMsgAddr = techInfoBean.getTechMblMsgAddr();
        String techTocNm = techInfoBean.getTechTocNm();
        // 2019/01/10 QC#29877 Add End

        ssmParam.put("whOwnrCd", NPZC103001Constant.CHO);
        String carrCd = prdTMsg.carrCd.getValue();
        String shpgSvcLvlCd = prdTMsg.shpgSvcLvlCd.getValue();
        String svcLvlnm = getTplSvcLvlNm(glblCmpyCd, carrCd, shpgSvcLvlCd);

        S21Mail mail = new S21Mail(glblCmpyCd);
        // Get mail group
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, NPZC103001Constant.MAIL_TO_GROUP_ID);
        // Get mail group
        S21MailGroup groupCc1 = new S21MailGroup(glblCmpyCd, NPZC103001Constant.MAIL_CC1_GROUP_ID);
        // Get mail group
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, NPZC103001Constant.MAIL_FROM_GROUP_ID);

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

        S21MailTemplate template = null;
        if (PRCH_REQ_TP.PREMIUM_RUSH.equals(prTMsg.prchReqTpCd.getValue())) {
            template = new S21MailTemplate(glblCmpyCd, NPZC103001Constant.MAIL_TEMPLATE_ID_PREMIUM_RUSH);
        } else {
            template = new S21MailTemplate(glblCmpyCd, NPZC103001Constant.MAIL_TEMPLATE_ID);
        }
        if (template == null) {
            throw new S21AbendException(NPZC103001Constant.NPZM0161E);
        }

        //rqstTechTocCd
        template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_TECH_TOC_CD, prTMsg.rqstTechTocCd.getValue());
        // 2018/11/28 QC#28735 Add Start
        // rqstTechTocNm
        if (ZYPCommonFunc.hasValue(techTocNm)) {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_TECH_TOC_NM, techTocNm);
        } else {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_TECH_TOC_NM, "");
        }
        // 2018/11/28 QC#28735 Add End
        //prchReqNum
        template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_PRCH_REQ_NUM, prTMsg.prchReqNum.getValue());
        //rqstRcvDt(dtl)
        SimpleDateFormat sdf = new SimpleDateFormat(NPZC103001Constant.RQST_RCV_DT_DATE_FORMAT);
        Date rqstRcvDt = null;
        if (ZYPCommonFunc.hasValue(prdTMsg.rqstRcvDt.getValue())) {
            try {
                rqstRcvDt = sdf.parse(prdTMsg.rqstRcvDt.getValue());
                sdf.applyPattern(NPZC103001Constant.EMAIL_DATE_FORMAT);
            } catch (ParseException e) {
                rqstRcvDt = null;
            }
            if (rqstRcvDt != null) {
                template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_RQST_RCV_DT, sdf.format(rqstRcvDt));
            }
        }
        // START 2023/02/08 T.Kuroda [QC#60966, ADD]
        //rqstShipDt(dtl)
        SimpleDateFormat sdfShipDt = new SimpleDateFormat(NPZC103001Constant.RQST_SHIP_DT_DATE_FORMAT);
        Date rqstShipDt = null;
        if (ZYPCommonFunc.hasValue(prdTMsg.rqstShipDt.getValue())) {
            try {
                rqstShipDt = sdfShipDt.parse(prdTMsg.rqstShipDt.getValue());
                sdfShipDt.applyPattern(NPZC103001Constant.EMAIL_DATE_FORMAT);
            } catch (ParseException e) {
                rqstShipDt = null;
            }
            if (rqstShipDt != null) {
                template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_RQST_SHIP_DT, sdfShipDt.format(rqstShipDt));
            }
        }
        // END   2023/02/08 T.Kuroda [QC#60966, ADD]
        //shpgSvcLvlCd(dtl)
        template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_SHPG_SVC_LVL_CD, svcLvlnm);
        //shipToLocNm
        template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_SHIP_TO_LOC_NM, prTMsg.shipToLocNm.getValue());
        //shipToFirstLineAddr&ScdLineAddr&ThirdLineAddr&FrthLineAddr
        StringBuffer sbShipToAddr = new StringBuffer();

        if (ZYPCommonFunc.hasValue(prTMsg.shipToFirstLineAddr.getValue())) {
            sbShipToAddr.append(prTMsg.shipToFirstLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(prTMsg.shipToScdLineAddr.getValue())) {
            if (sbShipToAddr.length() > 0) {
                sbShipToAddr.append(crlf).append(prTMsg.shipToScdLineAddr.getValue());
            } else {
                sbShipToAddr.append(prTMsg.shipToScdLineAddr.getValue());
            }
        }
        if (ZYPCommonFunc.hasValue(prTMsg.shipToThirdLineAddr.getValue())) {
            if (sbShipToAddr.length() > 0) {
                sbShipToAddr.append(crlf).append(prTMsg.shipToThirdLineAddr.getValue());
            } else {
                sbShipToAddr.append(prTMsg.shipToThirdLineAddr.getValue());
            }
        }
        if (ZYPCommonFunc.hasValue(prTMsg.shipToFrthLineAddr.getValue())) {
            if (sbShipToAddr.length() > 0) {
                sbShipToAddr.append(crlf).append(prTMsg.shipToFrthLineAddr.getValue());
            } else {
                sbShipToAddr.append(prTMsg.shipToFrthLineAddr.getValue());
            }
        }

        template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_SHIP_TO_FIRST_LINE_ADDR, sbShipToAddr.toString());

        if (ZYPCommonFunc.hasValue(prTMsg.prchReqNum)) {
            ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("prchReqNum", prTMsg.prchReqNum.getValue());
            // QC#21657-1 Modify
            if(ZYPCommonFunc.hasValue(prTMsg.shipToCustCd)){
                ssmParam.put("shipToCustCd", prTMsg.shipToCustCd.getValue());
            }

            // get PR Customer
            Map<String, Object> shipToInfoMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getPrCustomer", ssmParam);
            if (shipToInfoMap != null) {
                template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_SHIP_TO_LOC_NM, (String) shipToInfoMap.get(NPZC103001Constant.COL_LOC_NM));
                //shipToFirstLineAddr&ScdLineAddr&ThirdLineAddr&FrthLineAddr
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
                //city/state/post
                template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_CITY_STATE_POST, sbForCityStatePost.toString());
            }

        }
        // techCellPhoNum/telNum
        if (ZYPCommonFunc.hasValue(techCellPhoNum)) {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_TECH_CELL_PHO_NUM, techCellPhoNum);
        } else {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_TECH_CELL_PHO_NUM, telNum);
        }
        //emlAddr(dtl)
        // QC#22212
        if (PRCH_REQ_TP.PREMIUM_RUSH.equals(prTMsg.prchReqTpCd.getValue()) || PRCH_REQ_TP.RUSH.equals(prTMsg.prchReqTpCd.getValue())) {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_EML_ADDR, techMblMsgAddr);
        } else {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_EML_ADDR, techMailAddr);
        }
        // message(dtl)
        List<PRCH_REQ_DTLTMsg> prDtlList = selectPRCH_REQ_DTLTMsg(pMsg);
        StringBuilder sbForMessage = new StringBuilder();
        for (int i = 0; i < prDtlList.size(); i++) {
            PRCH_REQ_DTLTMsg prchReqDtlTMsg = prDtlList.get(i);

            if (PRCH_REQ_LINE_TP.INSOURCED_CH.equals(prchReqDtlTMsg.prchReqLineTpCd.getValue())) {
                sbForMessage.append(NPZC103001Constant.EMAIL_PARAM_MATERIAL);
                sbForMessage.append(prchReqDtlTMsg.mdseCd.getValue());
                sbForMessage.append(crlf);
                sbForMessage.append(NPZC103001Constant.EMAIL_PARAM_QTY);
                sbForMessage.append(prchReqDtlTMsg.prchReqQty.getValueInt());
                sbForMessage.append(crlf);
            }
        }

        template.setTemplateParameter(NPZC103001Constant.EMAIL_PARAM_MESSAGE, sbForMessage.toString());

        // QC#50072
        if (ZYPCommonFunc.hasValue(pMsg.ctacPsnNm)) {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_CTAC_PSN_NM, pMsg.ctacPsnNm.getValue());
        } else if (ZYPCommonFunc.hasValue(prTMsg.ctacPsnNm)) {
            template.setTemplateParameter(NPZC103001Constant.EMAIL_CTAC_PSN_NM, prTMsg.ctacPsnNm.getValue());
        }

        // Set mail subject
        mail.setSubject(template.getSubject(NPZC103001Constant.ML_LANG), NPZC103001Constant.ML_LANG_CD);
        mail.setMailTemplate(template);

        // Post
        mail.postMail();

    }

    /**
     * QC#18922
     * subStrPostCd
     * @param postCd String
     * @return String
     */
    public static String subStrPostCd(String postCd) {
        if (!ZYPCommonFunc.hasValue(postCd)) {
            return null;
        }
        if (postCd.length() > 5) {
            return postCd.substring(0, 5);
        }
        return postCd;
    }

    /**
     * isSplitPR
     * QC#2366 Add method.
     * @param pMsg NPZC103001PMsg
     * @return true/Split Request
     */
    private boolean isSplitPR(NPZC103001PMsg pMsg) {

        // QC#25900 Add check.
        if (!isCreateMode()){
            // Not Create.
            return false;
        }

        if (NPZC103001Constant.EVENT_INSOURCING.equals(pMsg.eventId.getValue())) {
            return false;
        }

        if (PRCH_REQ_SRC_TP.TECH_REQUEST_ENTRY.equals(pMsg.prchReqSrcTpCd.getValue()) //
                || PRCH_REQ_SRC_TP.PHONE.equals(pMsg.prchReqSrcTpCd.getValue()) //
                || PRCH_REQ_SRC_TP.TECH_PLANNING.equals(pMsg.prchReqSrcTpCd.getValue())) {
            // QC#55710
            if (PRCH_REQ_TP.TECH_RETURN.equals(pMsg.prchReqTpCd.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(pMsg.prchReqTpCd.getValue())) {
                return false;
            } else {
                return true;
            }
        }

        return false;
    }

    /**
     * isTechReq
     * QC#30460 Add method.
     * @param pMsg NPZC103001PMsg
     * @return true/Tech Request
     */
    private boolean isTechReq(NPZC103001PMsg pMsg) {

        if (NPZC103001Constant.EVENT_INSOURCING.equals(pMsg.eventId.getValue())) {
            return false;
        }

        if (PRCH_REQ_SRC_TP.TECH_REQUEST_ENTRY.equals(pMsg.prchReqSrcTpCd.getValue()) //
                || PRCH_REQ_SRC_TP.PHONE.equals(pMsg.prchReqSrcTpCd.getValue()) //
                || PRCH_REQ_SRC_TP.TECH_PLANNING.equals(pMsg.prchReqSrcTpCd.getValue())) {
            // QC#55710
            if (PRCH_REQ_TP.TECH_RETURN.equals(pMsg.prchReqTpCd.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(pMsg.prchReqTpCd.getValue())) {
                return false;
            } else {
                return true;
            }
        }

        return false;
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

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("shipToCustCd", shipToCustCd);
        queryParam.put("locGrpTech", LOC_GRP.TECHNICIAN);
        queryParam.put("rgtnOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);

        BigDecimal shipToCustPk = (BigDecimal) ssmBatchClient.queryObject("getShipToCustPkForTech", queryParam);

        if (shipToCustPk == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * getHazmatItemInfo
     * QC#2366 Add method.
     * @param mdseCd String
     * @return Map mdseSftyInfo
     */
    private boolean isPreferredCarrierFedex(String techTocCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("techTocCd", techTocCd);
        queryParam.put("carrTpFedex", CARR_TP.FEDEX);
        queryParam.put("vndTpObCarrier", VND_TP.OUTBOUND_CARRIER);

        String prfCarrCd = (String) ssmBatchClient.queryObject("getPreferredCarrierFedexForWh", queryParam);

        if (prfCarrCd == null || prfCarrCd.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * isWillCallRequest
     * QC#2366 Add method.
     * @param shpgSvcLvlCd
     * @return
     */
    private boolean isWillCallRequest(String shpgSvcLvlCd) {
        return SHPG_SVC_LVL.CUSTOMER_PICK_UP.equals(shpgSvcLvlCd);
    }

    /**
     * isGroundHazmat
     * QC#2366 Add method.
     * @param pMsg NPZC103001PMsg
     * @param isPrfCarrFdx boolean
     * @return true;ground only
     */
    private boolean isGroundHazmat(NPZC103001PMsg pMsg) {

        for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
            if (!SHPG_SVC_LVL.GROUND_SERVICE.equals(pMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue())) {
                return false;
            }

            Map mdseSftyInfo = getHazmatItemInfo(pMsg.prchReqInfo.no(i).mdseCd.getValue());

            if (mdseSftyInfo != null //
                    && !SHPG_SVC_LVL.GROUND_SERVICE.equals((String) mdseSftyInfo.get("SHPG_SVC_LVL_CD"))) {
                return false;
            }
        }

        return true;
    }

    /**
     * getHazmatItemInfo
     * QC#2366 Add method.
     * @param mdseCd String
     * @return Map mdseSftyInfo
     */
    private Map getHazmatItemInfo(String mdseCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("flgOnY", ZYPConstant.FLG_ON_Y);

        return (Map) ssmBatchClient.queryObject("getHazmatItemInfo", queryParam);
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

        HashMap<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("shipToCustCd", shipToCustCdHazmat);
        queryParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        Map shipAddress = (Map) ssmBatchClient.queryObject("getShipToCustAddress", queryParam);

        if (shipAddress != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, shipToCustCdHazmat);
            ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, (String) shipAddress.get("LOC_NM"));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, (String) shipAddress.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, (String) shipAddress.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, (String) shipAddress.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFrthLineAddr, (String) shipAddress.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstRefCmntTxt, (String) shipAddress.get("FIRST_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdRefCmntTxt, (String) shipAddress.get("SCD_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCtyAddr, (String) shipAddress.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCntyNm, (String) shipAddress.get("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToProvNm, (String) shipAddress.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToStCd, (String) shipAddress.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToPostCd, (String) shipAddress.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCtryCd, (String) shipAddress.get("CTRY_CD"));
        } else {
            // Error: Shipping address for hazard material was not found.
            this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0303E);

            pMsg.xxMsgIdList.no(pMsg.xxMsgIdList.getValidCount()).xxMsgId.setValue(NPZC103001Constant.NPZM0303E);
            pMsg.xxMsgIdList.setValidCount(pMsg.xxMsgIdList.getValidCount() + 1);
        }

        return pMsg;
    }

    /**
     * copyPMsgForSplitPR
     * @param pMsg original NPZC103001PMsg
     * @param i oricinal int
     * @param targetPMsg NPZC103001PMsg
     * @param targetIndex int 
     * @param targetSerIndex Serial int 
     * @return updatePMsg NPZC103001PMsg
     */
    private NPZC103001PMsg copyPMsgForSplitPR(NPZC103001PMsg pMsg, int i, NPZC103001PMsg targetPMsg, int targetIndex, int targetSerIndex) {
        EZDPMsg.copy(pMsg.prchReqInfo.no(i), null, targetPMsg.prchReqInfo.no(targetIndex), null);

        ZYPEZDItemValueSetter.setValue(targetPMsg.prchReqInfo.no(targetIndex).prchReqLineNum, ZYPCommonFunc.leftPad(String.valueOf(targetIndex + 1), 3, String.valueOf(0)));
        ZYPEZDItemValueSetter.setValue(targetPMsg.prchReqInfo.no(targetIndex).prchReqLineSubNum, BigDecimal.ZERO);

        if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(i).xxDtlLineNum)) {
            for (int j = 0; j < pMsg.serNumInfo.getValidCount(); j++) {

                if (pMsg.prchReqInfo.no(i).xxDtlLineNum.getValue().equals(pMsg.serNumInfo.no(j).xxDtlLineNum.getValue())) {
                    EZDPMsg.copy(pMsg.serNumInfo.no(i), null, targetPMsg.serNumInfo.no(targetSerIndex), null);

                    ZYPEZDItemValueSetter.setValue(targetPMsg.serNumInfo.no(targetSerIndex).prchReqLineNum, targetPMsg.prchReqInfo.no(targetIndex).prchReqLineNum);
                    ZYPEZDItemValueSetter.setValue(targetPMsg.serNumInfo.no(targetSerIndex).prchReqLineSubNum, targetPMsg.prchReqInfo.no(targetIndex).prchReqLineSubNum);

                    targetSerIndex++;
                }
            }
        }

        targetPMsg.prchReqInfo.setValidCount(targetIndex + 1);
        targetPMsg.serNumInfo.setValidCount(targetSerIndex);

        return targetPMsg;
    }

    /**
     * getEndOfShipToCustCdForHazmat QC#2366 Add method.
     * @return end of ship to cust code for hazmat. String
     */
    private String getEndOfShipToCustCdForHazmat() {
        String endOfShipToCustCd = ZYPCodeDataUtil.getVarCharConstValue("END_OF_TECH_HAZMAT_CODE", this.glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(endOfShipToCustCd)) {
            endOfShipToCustCd = "-HAZMAT";
        }

        return endOfShipToCustCd;
    }

    /**
     * checkHazmatRequestAndUpdate
     * QC#2366 Add method.
     * @param pMsg NPZC103001PMsg
     * @return pMsg
     */
    private NPZC103001PMsg checkHazmatRequestAndUpdate(NPZC103001PMsg pMsg) {

        // Check mdse hazmat.
        boolean isHazmatOrder = false;
        boolean isDBSWhOnly = true;
        for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {

            Map m = getHazmatItemInfo(pMsg.prchReqInfo.no(i).mdseCd.getValue());

            if (m != null) {
                isHazmatOrder = true;
            }

            if (!isDBSwh(pMsg.prchReqInfo.no(i).srcInvtyLocCd.getValue())) {
                isDBSWhOnly = false;
            }
        }

        if (!isHazmatOrder) {
            // Not hazmat order.Not update.
            return pMsg;
        }

        String endOfHazmatCode = getEndOfShipToCustCdForHazmat();
        boolean isPrfCarrFdx = isPreferredCarrierFedex(pMsg.rqstTechTocCd.getValue());
        boolean isCustomerWhShip = isCustomerWhShip(pMsg.prchReqInfo.no(0).destInvtyLocCd.getValue());
        // START 2023/08/29 T.Kuroda [QC#61740, ADD]
        boolean isHazmatHasGround = isShpgSvlHazmatHasGround(pMsg);
        // END 2023/08/29 T.Kuroda [QC#61740, ADD]

        // check ship_to_cust_cd
        // START 2023/08/29 T.Kuroda [QC#61740, MOD]
//        if (isPrfCarrFdx //
//                && ! isCustomerWhShip) {
        if (isPrfCarrFdx //
                && !isCustomerWhShip && isHazmatHasGround) {
        // END 2023/08/29 T.Kuroda [QC#61740, MOD]
            String shipToCustCd = pMsg.shipToCustCd.getValue();
            if (shipToCustCd == null //
                    || !shipToCustCd.endsWith(endOfHazmatCode)) {
                // Warning. & Update shipToCustCd

                // START 04/08/2020 [QC#56406,MOD]
//                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0304W);
                this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0313E);
                // END   04/08/2020 [QC#56406,MOD]

                String shipToCustCdHazmat = pMsg.rqstTechTocCd.getValue() + endOfHazmatCode;
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, shipToCustCdHazmat);
            }
        }

        // START 2019/08/23 M.Naito [QC#52585,MOD]
//        if (!isCustomerWhShip) {
            // check shpg_svc_lvl_cd
//            boolean isGroundHazmatOnly = isGroundHazmat(pMsg);
            // START 2023/08/29 T.Kuroda [QC#61740, DEL]
//            boolean isGroundHazmatOnly = isShpgSvlHazmatGroundOnly(pMsg);
            // END 2023/08/29 T.Kuroda [QC#61740, DEL]
            // START 2023/05/09 E.Watabe [QC#61084,DEL]
//            boolean oneTimeWrnMsg = true;
//            String carrCdForWillCall = getCarrCdForWillCall();
            // END 2023/05/09 E.Watabe [QC#61084,DEL]
            for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
            // START 2023/05/09 E.Watabe [QC#61084,MOD]
//                if (!SHPG_SVC_LVL.GROUND_SERVICE.equals(pMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue()) && !SHPG_SVC_LVL.CUSTOMER_PICK_UP.equals(pMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue())) {
//                    // Warning. & Update shpgSvcLvlCd
//                    if (oneTimeWrnMsg) {
//                        this.msgMap.addXxMsgId(NPZC103001Constant.NPZM0305W);
//                        oneTimeWrnMsg = false;
//                    }
//
//                    // START 2019/08/23 M.Naito [QC#52585,MOD]
////                    if (isGroundazmatOnly) {
//                    if (isDBSWhOnly || isGroundHazmatOnly) {
//                    // END 2019/08/23 M.Naito [QC#52585,MOD]
//                        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(i).shpgSvcLvlCd, SHPG_SVC_LVL.GROUND_SERVICE);
//
//                        if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(i).carrCd)) {
//                            if (!checkShpgSvcCarrReln(pMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue(), pMsg.prchReqInfo.no(i).carrCd.getValue())) {
//                                pMsg.prchReqInfo.no(i).carrCd.clear();
//                            }
//                        }
//                    } else {
//                        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(i).shpgSvcLvlCd, SHPG_SVC_LVL.CUSTOMER_PICK_UP);
//                        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(i).carrCd, carrCdForWillCall);
//                    }
//                } else {
//                    // START 2019/08/23 M.Naito [QC#52585,MOD]
//                    if (!SHPG_SVC_LVL.CUSTOMER_PICK_UP.equals(pMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue()) && (isDBSWhOnly || isGroundHazmatOnly)) {
//                    // END 2019/08/23 M.Naito [QC#52585,MOD]
//                        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(i).shpgSvcLvlCd, SHPG_SVC_LVL.GROUND_SERVICE);
//
//                        if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(i).carrCd)) {
//                            if (!checkShpgSvcCarrReln(pMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue(), pMsg.prchReqInfo.no(i).carrCd.getValue())) {
//                                pMsg.prchReqInfo.no(i).carrCd.clear();
//                            }
//                        }
//                    } else {
//                        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(i).shpgSvcLvlCd, SHPG_SVC_LVL.CUSTOMER_PICK_UP);
//
//                        if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(i).carrCd)) {
//                            if (!checkShpgSvcCarrReln(pMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue(), pMsg.prchReqInfo.no(i).carrCd.getValue())) {
//                                pMsg.prchReqInfo.no(i).carrCd.clear();
//                            }
//                        // START 2019/08/23 M.Naito [QC#52585,MOD]
//                        } else {
//                            ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(i).carrCd, carrCdForWillCall);
//                        }
//                        // END 2019/08/23 M.Naito [QC#52585,MOD]
//                    }
//                }

                // START 2023/08/29 T.Kuroda [QC#61740, MOD]
//                if (!isGroundHazmatOnly) {
                if (!isHazmatHasGround) {
                // END 2023/08/29 T.Kuroda [QC#61740, MOD]
                    if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(i).carrCd)) {
                        if (!checkShpgSvcCarrReln(pMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue(), pMsg.prchReqInfo.no(i).carrCd.getValue())) {
                            pMsg.prchReqInfo.no(i).carrCd.clear();
                        }
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(i).shpgSvcLvlCd, SHPG_SVC_LVL.GROUND_SERVICE);

                    if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(i).carrCd)) {
                        if (!checkShpgSvcCarrReln(pMsg.prchReqInfo.no(i).shpgSvcLvlCd.getValue(), pMsg.prchReqInfo.no(i).carrCd.getValue())) {
                            pMsg.prchReqInfo.no(i).carrCd.clear();
                        }
                    }
                }
           }
           // END 2023/05/09 E.Watabe [QC#61084,MOD]
//        }
        // END 2019/08/23 M.Naito [QC#52585,MOD]

        return pMsg;
    }

    /**
     * getCarrCdForWillCall QC#2366 Add method.
     * @param pMsg NPZC103001PMsg
     */
    private String getCarrCdForWillCall() {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("shpgSvcLvlCd", SHPG_SVC_LVL.CUSTOMER_PICK_UP);
        List<String> carrCdList = (List<String>) this.ssmBatchClient.queryObjectList("getCarrCdForWillCall", queryParam);

        String prfCarrForWillCall = ZYPCodeDataUtil.getVarCharConstValue("NPZA1030_PKUP_PRF_CARR", this.glblCmpyCd);

        String slctCarrCd = "";

        if (carrCdList != null && carrCdList.contains(prfCarrForWillCall)) {
            slctCarrCd = prfCarrForWillCall;
        } else if (carrCdList != null) {
            // select first.
            slctCarrCd = carrCdList.get(0);
        } else if (ZYPCommonFunc.hasValue(prfCarrForWillCall)) {
            slctCarrCd = prfCarrForWillCall;
        }

        return slctCarrCd;
    }

    /**
     * checkShpgSvcCarrReln
     * QC#2366 Add method.
     * @param shpgSvcLvlCd
     * @param carrCd
     * @return true:OK
     */
    private boolean checkShpgSvcCarrReln(String shpgSvcLvlCd, String carrCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
        queryParam.put("carrCd", carrCd);
        Integer cnt = (Integer) this.ssmBatchClient.queryObject("checkSpgSvcCarrReln", queryParam);

        if (cnt.intValue() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * isCustomerWHShip
     * QC#2366 Add method.
     * @param destInvtyLocCd
     * @return true:Customer
     */
    private boolean isCustomerWhShip(String destInvtyLocCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invtyLocCd", destInvtyLocCd);
        queryParam.put("rtlWhCatgCustomer", RTL_WH_CATG.CUSTOMER);

        Integer cnt = (Integer) this.ssmBatchClient.queryObject("cntCustomerWh", queryParam);

        if (cnt.intValue() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    // QC#28939 add start
    /**
     * isTextItem
     * @param mdseCd
     * @return
     */
    private boolean textItemCheck(String mdseCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("mdseItemTpCd", MDSE_ITEM_TP.TEXT_ITEM);

        Integer cnt = (Integer) this.ssmBatchClient.queryObject("textItemCheck", queryParam);

        if (cnt.intValue() > 0) {
            return true;
        } else {
            return false;
        }
    }
    // QC#28939 add end
    
    

    /**
     * isDBSwh
     * QC#2366 Add method.
     * @param rtlWhCd
     * @return true/ DBS Wh
     */
    private boolean isDBSwh(String srcInvtyLocCd) {

        if (!ZYPCommonFunc.hasValue(srcInvtyLocCd)) {
            return false;
        }

        Map<String, String> m = getWhOwnrCd(srcInvtyLocCd, ZYPDateUtil.getSalesDate(glblCmpyCd));

        if (m == null) {
            return false;
        }

        String whOwnrCd = m.get("WH_OWNR_CD");

        if (WH_OWNR.DBS.equals(whOwnrCd)) {
            return true;
        } else {
            return false;
        }
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

        while (NPZC103001Constant.LENGTH_PRCH_REQ_LINE_CMNT_TXT < cmnt.length()) {
            // cut
            int lastIndex = cmnt.lastIndexOf("/");
            if (lastIndex < 0) {
                lastIndex = NPZC103001Constant.LENGTH_PRCH_REQ_LINE_CMNT_TXT;
            }

            cmnt = cmnt.substring(0, lastIndex);
        }

        return cmnt;
    }

    // 2019/01/10 QC#29877 Add Start
    private TechInfoBean getTechInfo(String glblCmpyCd, String rqstTechTocCd) {

        //get Tech Info
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rqstTechTocCd", rqstTechTocCd);
        Map<String, Object> techInfoMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getTechInfo", ssmParam);

        return new TechInfoBean(techInfoMap);
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
    // 2019/01/10 QC#29877 Add End

    public List<Map<String, Object>> getSupdInfoFromIncgFlgN(String supdToMdseCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("supdToMdseCd", supdToMdseCd);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSupdInfoFromIncgFlgN", ssmParam);
    }

    // 2019/01/10 QC#29877 Add End

    // QC#51553 Start
    public NWZC206001PMsg getSupdList(NPZC103001PMsg pMsg, String origMdseCd) {

        NWZC206001 api = new NWZC206001();
        NWZC206001PMsg param = new NWZC206001PMsg();

        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.slsDt, pMsg.procDt);
        ZYPEZDItemValueSetter.setValue(param.mdseCd, origMdseCd);
        ZYPEZDItemValueSetter.setValue(param.xxModeCd, "1");
        ZYPEZDItemValueSetter.setValue(param.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(param.xxAvalPrchFlg, ZYPConstant.FLG_ON_Y);

        api.execute(param, ONBATCH_TYPE.BATCH);

        return param;
    }

    private ArrayList<String> getMdseCd(String relnMdseCd, String srchMdseItemRelnTpCd, boolean isChoice) {

        ArrayList<String> ret = null;

        if (ret == null) {
            ret = new ArrayList<String>();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
                HashMap<String, Object> paramMap = new HashMap<String, Object>();

                paramMap.put("glblCmpyCd", this.glblCmpyCd);
                paramMap.put("relnMdseCd", relnMdseCd);
                paramMap.put("srchMdseItemRelnTpCd", srchMdseItemRelnTpCd);
                if(isChoice) {
                    paramMap.put("isChoice", ZYPConstant.FLG_ON_Y);
                }

                S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

                execParam.setFetchSize(FETCH_SIZE);
                execParam.setMaxRows(0);
                preparedStatement = ssmLlcClient.createPreparedStatement("getMdseCd", paramMap, execParam);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    ret.add(resultSet.getString("MDSE_CD"));
                }

            } catch (SQLException e) {
                sqlExceptionHandler(e);

            } finally {
                S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
            }
        }
        return ret;
    }

    private ArrayList<String> getRelnMdseCd(String mdseCd, String srchMdseItemRelnTpCd, boolean isChoice) {

        ArrayList<String> ret = null;

        if (ret == null) {
            ret = new ArrayList<String>();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
                HashMap<String, Object> paramMap = new HashMap<String, Object>();

                paramMap.put("glblCmpyCd", this.glblCmpyCd);
                paramMap.put("mdseCd", mdseCd);
                paramMap.put("srchMdseItemRelnTpCd", srchMdseItemRelnTpCd);
                if(isChoice) {
                    paramMap.put("isChoice", ZYPConstant.FLG_ON_Y);
                }

                S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
                execParam.setFetchSize(FETCH_SIZE);
                execParam.setMaxRows(0);
                preparedStatement = ssmLlcClient.createPreparedStatement("getRelnMdseCd", paramMap, execParam);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    ret.add(resultSet.getString("RELN_MDSE_CD"));
                }

            } catch (SQLException e) {
                sqlExceptionHandler(e);

            } finally {
                S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
            }
        }
        return ret;
    }
    // QC#51553 End

    /**
     * Get EZUPTIME Timezone time. Add QC#53081
     * @param pMsg NPZC103001PMsg
     * @param tMsg PRCH_REQTMsg
     * @return String
     */
    private String getPrchReqUptimeTz(NPZC103001PMsg pMsg, PRCH_REQTMsg tMsg) {

        if (isTechRequest(pMsg)) {
            // get Local Time
            String rtlWhCd = null;
            if (pMsg.prchReqInfo.getValidCount() > 0) {
                Map<String, String> locDat = null;
                // QC#55710
                if (PRCH_REQ_TP.TECH_RETURN.equals(pMsg.prchReqTpCd.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(pMsg.prchReqTpCd.getValue())) {
                    locDat = getWhAndSwhByInvtyLocCd(pMsg.prchReqInfo.no(0).srcInvtyLocCd.getValue());
                } else {
                    locDat = getWhAndSwhByInvtyLocCd(pMsg.prchReqInfo.no(0).destInvtyLocCd.getValue());
                }

                if (locDat != null) {
                    rtlWhCd = locDat.get(NPZC103001Constant.RTL_WH_CD);
                }
            }

            if (rtlWhCd == null) {
                rtlWhCd = pMsg.rqstTechTocCd.getValue();
            }

            String lclTZId = getTechTZId(rtlWhCd);
            ZYPLocalTimeData timeData = null;
            if (lclTZId != null) {
                timeData = ZYPLocalTimeUtil.convertTimeSys2Lcl(lclTZId, tMsg.ezUpTime.getValue());
            }

            if (timeData == null) {
                return tMsg.prchReqCratDispDtTmTs.getValue();
            } else {
                String prchReqCratDispDtTmTs = timeData.getTime().substring(0, NPZC103001Constant.DT_TM_TS_LENGTH);
                return prchReqCratDispDtTmTs;
            }

        } else {
            return tMsg.prchReqCratDispDtTmTs.getValue();
        }
    }

    /**
     * Add QC#57365 Methods
     * Get Total Ship Qty
     * @param prchReqNum String
     * @param prchReqLineNum String
     * @param prchReqLineSubNum String
     * @return
     */
    private BigDecimal culcShipQty(String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("prchReqNum", prchReqNum);
        ssmParam.put("prchReqLineNum", prchReqLineNum);
        ssmParam.put("prchReqLineSubNum", prchReqLineSubNum);

        BigDecimal totShipQty = (BigDecimal) ssmBatchClient.queryObject("sumShipQty", ssmParam);

        if (totShipQty == null) {
            return BigDecimal.ZERO;
        }

        return totShipQty;
    }

    /**
     * get Retail Warehouse
     * @param srcRtlWhCd String
     * @return String
     */
    private RTL_WHTMsg getRtlWh(String srcRtlWhCd) {

        RTL_WHTMsg tMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, srcRtlWhCd);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        tMsg = (RTL_WHTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return tMsg;
        }
        return null;
    }

    // START 2022/10/07 R.Azucena [QC#60664, ADD]
    /**
     * isAllRwsStatusClosed
     * @param pMsg NPZC103001PMsg
     * @return boolean
     */
    private boolean isAllRwsStatusClosed(NPZC103001PMsg pMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("prchReqNum", pMsg.prchReqNum.getValue());
        queryParam.put("prchReqLineStsCd", PRCH_REQ_LINE_STS.CANCELLED);
        queryParam.put("rwsStsCd", RWS_STS.RECEIVED);
        BigDecimal result = (BigDecimal) this.ssmBatchClient.queryObject("getActiveRwsNum", queryParam);

        if (BigDecimal.ZERO.equals(result)) {
            return true;
        }

        return false;
    }
    // END 2022/10/07 R.Azucena [QC#60664, ADD]

    // START 2023/05/18 T.Kuroda [QC#61211, ADD]
    /**
     * isApvlMinAmt
     * @param pMsg NPZC103001PMsg
     * @return boolean
     */
    private boolean isApvlMinAmt(NPZC103001PMsg pMsg) {
        // Standard Time
        String prchReqCratDispDtTmTs = null;
        if (addlHdrMap.get(NPZC103001Constant.COL_PRCH_REQ_CRAT_DISP_DT_TM_TS) == null) {
            PRCH_REQTMsg prTMsg = selectPRCH_REQTMsg(pMsg.prchReqNum.getValue());
            if (prTMsg == null) {
                getPrchReqCratTz(pMsg);
                prchReqCratDispDtTmTs = ((String) addlHdrMap.get(NPZC103001Constant.COL_PRCH_REQ_CRAT_DISP_DT_TM_TS));
            } else {
                prchReqCratDispDtTmTs = getPrchReqUptimeTz(pMsg, prTMsg);
            }
        } else {
            prchReqCratDispDtTmTs = ((String) addlHdrMap.get(NPZC103001Constant.COL_PRCH_REQ_CRAT_DISP_DT_TM_TS));
        }

        if (!isStandardTime(prchReqCratDispDtTmTs)) {
            return false;
        }

        // Check Will Call Request
        if (isWillCallRequest(pMsg.prchReqInfo.no(0).shpgSvcLvlCd.getValue())) {
            return false;
        }

        // Line Biz TP
        TECH_APVL_MINTMsg tamTMsg = new TECH_APVL_MINTMsg();
        ZYPEZDItemValueSetter.setValue(tamTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tamTMsg.lineBizTpCd, getTechLineBizType(pMsg));
        tamTMsg = (TECH_APVL_MINTMsg) S21CacheTBLAccessor.findByKey(tamTMsg);
        if (tamTMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(tamTMsg.getReturnCode())) {
            return false;
        }
        if (BigDecimal.ZERO.compareTo(tamTMsg.techApvlMinAmt.getValue()) > 0) {
            return false;
        }

        // Less than premium rush apvl limit amt
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(i).prchReqLineSubNum)
                    && BigDecimal.ZERO.compareTo(pMsg.prchReqInfo.no(i).prchReqLineSubNum.getValue()) != 0) {
                continue;
            }
            if (PRCH_REQ_LINE_STS.CANCELLED.equals(pMsg.prchReqInfo.no(i).prchReqLineStsCd.getValue())) {
                continue;
            }

            NLXC001001GetInventoryItemCostBean param = new NLXC001001GetInventoryItemCostBean();
            param.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
            param.setInvtyLocCd(pMsg.prchReqInfo.no(i).destInvtyLocCd.getValue());
            param.setMdseCd(pMsg.prchReqInfo.no(i).mdseCd.getValue());
            if (ZYPCommonFunc.hasValue(pMsg.prchReqInfo.no(i).prchReqQty)) {
                param.setQty(pMsg.prchReqInfo.no(i).prchReqQty.getValue());
            } else {
                param.setQty(BigDecimal.ONE);
            }

            param = NLXC001001GetInventoryItemCost.getInventoryItemCost(param);
            if (param.getTotPrcAmt() != null) {
                total = total.add(param.getTotPrcAmt());
            }
        }

        if (total.compareTo(tamTMsg.techApvlMinAmt.getValue()) > 0) {
            return false;
        }

        return true;
    }

    /**
     * isStandardTime
     * @param String prchReqCratDispDtTmTs
     * @return boolean
     */
    private boolean isStandardTime(String prchReqCratDispDtTmTs) {
        String prchReqCratDispTm = prchReqCratDispDtTmTs.substring(NPZC103001Constant.DT_LENGTH, NPZC103001Constant.DT_LENGTH + NPZC103001Constant.TM_LENGTH);
        String prchReqCratDispDt = prchReqCratDispDtTmTs.substring(0, NPZC103001Constant.DT_LENGTH);
        String preApprovedStartTime = ZYPCodeDataUtil.getVarCharConstValue("RUSH_PRE_APPROVED_START_TIME", this.glblCmpyCd);
        String preApprovedEndTime = ZYPCodeDataUtil.getVarCharConstValue("RUSH_PRE_APPROVED_END_TIME", this.glblCmpyCd);

        if (EZDCommonFunc.cmpTime(preApprovedStartTime, prchReqCratDispTm) == 1
                && EZDCommonFunc.cmpTime(prchReqCratDispTm, preApprovedEndTime) == 1
                && ZYPDateUtil.isBusinessDay(glblCmpyCd, prchReqCratDispDt)) {
            return true;
        }

        return false;
    }

    /**
     * getTechLineBizType
     * @param pMsg NPZC103001PMsg
     * @return String
     */
    private String getTechLineBizType(NPZC103001PMsg pMsg) {
        // Create condition param
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        queryParam.put("psnCd", pMsg.rqstTechTocCd.getValue());
        queryParam.put("effFromDt", ZYPDateUtil.getSalesDate());
        queryParam.put("effThruDt", ZYPDateUtil.getSalesDate());

        return (String) this.ssmBatchClient.queryObject("getTechLineBizType", queryParam);
    }
    // END   2023/05/18 T.Kuroda [QC#61211, ADD]
}
