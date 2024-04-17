/**
 * <pre>
 * ATP Calculation API
 * ATP(Available time promiss)
 * 
 * Date         Company     Name            Create/Update   Defect No
 * ------------------------------------------------------------------
 * 07/01/2009   Fujitsu     K.Suda          Create          N/A
 * 09/23/2009   Fujitsu     K.Kimura        Update          N/A
 * 10/06/2009   Fujitsu     K.Kimura        Update          419
 * 10/20/2009   Fujitsu     K.Kimura        Update          811,854  
 * 10/29/2009   Fujitsu     K.Kimura        Update          1231  
 * 11/10/2009   Fujitsu     K.Kimura        Update          1621  
 * 11/11/2009   Fujitsu     K.Kimura        Update          1776  
 * 11/16/2009   Fujitsu     K.Kimura        Update          1847  
 * 11/18/2009   Fujitsu     K.Kimura        Update          Add request  
 * 11/23/2009   Fujitsu     K.Kimura        Update          Tuning  
 * 03/05/2010   Fujitsu     R.Watanabe      Update          4371  
 * 04/08/2010   Fujitsu     A.Suda          Update          5090 
 * 05/01/2010   Fujitsu     K.Tajima        Update          4359 (performance Tuning)
 * 05/12/2010   Fujitsu     A.Suda          Update          5359
 * 06/01/2010   Fujitsu     K.Tajima        Update          6745
 * 08/16/2010   Fujitsu     K.Tajima        Update          delete a lot of debug logs.
 * 01/04/2011   Fujitsu     A.Suda          Update          1056 (performance Tuning)
 * 01/24/2011    Fujitsu     S.Takami        Update          1141(All2)
 * 03/03/2011   CSAI        M.Takahashi     Update          1544(PROD)
 * 
 * 2013/04/03   Fujitsu     S.Takami        Update          Phase 2.0 S21 WDS R-OM005-001
 * 2016/03/16   Fujitsu     M.Nakamura      Update          CSA: ReqID P628
 * </pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC108001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKey;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKeyForUpdateAPI;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static parts.dbcommon.EZDTBLAccessor.RTNCD_NORMAL;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDMsg;
import parts.common.EZDTMsg;
import business.db.ATP_BAT_WRKTMsg;
import business.db.ATP_HISTTMsg;
import business.db.ATP_INFOTMsg;
import business.db.CAL_RELNTMsg;
import business.db.CAL_RELNTMsgArray;
import business.db.CMPSNTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DIST_PLN_DTLTMsg;
import business.db.DIST_PLN_TM_FRAMETMsg;
import business.db.HARD_ALLOCTMsg;
import business.db.HLD_PROC_DFNTMsg;
import business.db.INVTY_LOC_VTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_WH_CONDTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PO_DTLTMsg;
import business.db.RCV_ASN_VNDTMsg;
import business.db.SHPG_PLNTMsg;
import business.db.SOFT_ALLOCTMsg;
import business.db.SPLY_INFOTMsg;
import business.parts.NWZC002001PMsg;
import business.parts.NWZC108001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC002001.NWZC002001;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001OrderTakeMdseTBLAccessor;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BACK_ORD_IMPCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_SUB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HARD_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SOFT_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

public class NWZC108001 extends S21ApiCommonBase {

    public static final String NWZM0188E = "NWZM0188E";
    public static final String NWZM0301E = "NWZM0301E";
    public static final String NWZM0022E = "NWZM0022E";
    public static final String NWZM0612E = "NWZM0612E";
    public static final String NWZM0613E = "NWZM0613E";
    public static final String NWZM0643E = "NWZM0643E";
    public static final String NWZM0614E = "NWZM0614E";
    public static final String NWZM0733E = "NWZM0733E";
    public static final String NWZM0734E = "NWZM0734E";
    public static final String NWZM0735E = "NWZM0735E";
    public static final String NWZM0736E = "NWZM0736E";
    public static final String NWZM0737E = "NWZM0737E";
    public static final String NWZM0738E = "NWZM0738E";
    public static final String NWZM0739E = "NWZM0739E";
    public static final String NWZM0740E = "NWZM0740E";
    public static final String NWZM0741E = "NWZM0741E";
    public static final String NWZM0742E = "NWZM0742E";
    public static final String NWZM0743E = "NWZM0743E";
    public static final String NWZM0648E = "NWZM0648E";
    public static final String NWZM0816E = "NWZM0816E";
    public static final String ZZSM4104E = "ZZSM4104E";
    /** 2013/04/03 Phase 2.0 S21 WDS R-OM005-001 ADD Can't get Config Lead Time*/
    public static final String NWZM1246E = "NWZM1246E";

    /** The entered "Serial Number" dose not exist. */
    public static final String NWZM1930E = "NWZM1930E";
    /** The entered "Serial Number" is duplicated. */
    public static final String NWZM1931E = "NWZM1931E";
    private static final String LABEL_BLANK = "";
    private static final String LABEL_STOCK = "STOCK";
    private static final String LABEL_DOMES = "DOMES";
    private static final String LABEL_TBD   = "99991231";

    private static final String Y = ZYPConstant.FLG_ON_Y;
    private static final String N = ZYPConstant.FLG_OFF_N;

    @SuppressWarnings("unchecked")
    private static final List<String>[] atpInfoKeyColumnListArray    = new ATP_INFOTMsg().getKeyColumnList();
    @SuppressWarnings("unchecked")
    private static final List<String>[] atpInfoSelectColumnListArray = new ATP_INFOTMsg().getSelectColumnList();

    private ONBATCH_TYPE      onBatchType;
    private S21ApiMessageMap  apiMsgMap;
    private S21SsmBatchClient ssmClient;
    private InstanceVariable  insVal;
    private Cache             cache;

    /*
     * Default Configulation Lead Time Date Var_CHAR_CONST_NM
     */
    private static final String DEFAULT_CONFIG_LT_DAY_NUM = "DEFAULT_CONFIG_LT_DAY_NUM";

    /** ssm */
    private S21SsmBatchClient ssmClientAtp;

    /** Constructor */
    public NWZC108001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmClientAtp = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * ATP Calculation API.
     * @param param NWZC108001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC108001PMsg param, final ONBATCH_TYPE onBatchType) {
        final String methodNm = "execute";
        debugMethodStart(getClass(), methodNm);

        try {

            // --------------------------------------------------
            // Initialization.
            // --------------------------------------------------
            this.apiMsgMap   = new S21ApiMessageMap(param);
            this.onBatchType = onBatchType;
            this.insVal      = new InstanceVariable(param);
            this.cache       = new Cache();

            // --------------------------------------------------
            // do main process.
            // --------------------------------------------------
            doProcess(param);
            this.apiMsgMap.flush();

        } finally {
            debugMethodEnd(getClass(), methodNm);
        }
    }

    protected void doProcess(NWZC108001PMsg pMsg) {
        final String methodNm = "doProcess";
        debugMethodStart(getClass(), methodNm);

        try {

            // --------------------------------------------------
            // validate Request Parameters.
            // --------------------------------------------------
            if (!validate(pMsg)) {
                return;
            }

            // --------------------------------------------------
            // process by [ATP_BAT_WRK].
            // --------------------------------------------------
            boolean isNormalEnd = (Boolean) ssmClient.queryObject("getAtpBatWrk", pMsg, new AtpCalculator());

            if (isNormalEnd) {
                if (!isEmpty(this.cache.atpBatWrkList)) {

                    debug(getClass(), "+++<< cache.shpgPlnBean >>+++++++++++++++++++++++++++++++++++++++++++++++");
                    debug(getClass(), " + size() = ", this.cache.shpgPlnBean.size());
                    debug(getClass(), "+++<< cache.shpgPlnBean >>+++++++++++++++++++++++++++++++++++++++++++++++");

                    if (!isEmpty(this.cache.shpgPlnBean.keySet())) {

                        // --------------------------------------------------
                        // For distribution type.
                        // If there are remainder soft allocation qty, make TBD ATP.
                        // --------------------------------------------------
                        processDistNotSoftAllocTBD();

                        // --------------------------------------------------
                        // adjust 'PSD_DT/PDD_DT' by 'SHIP_CPLT_CD'.
                        // --------------------------------------------------
                        adjustPsdPddByShipCplt();

                        // --------------------------------------------------
                        // adjust 'PSD_DT/PDD_DT' by Set Mdse.
                        // --------------------------------------------------
                        adjustPsdPddBySetMdse();

                        // --------------------------------------------------
                        // adjust 'PSD_DT/PDD_DT' by Config.
                        // --------------------------------------------------
                        adjustPsdPddByConfig();

                        // --------------------------------------------------
                        // 1. insert [ATP_INFO] & [ATP_HIST].
                        // 2. update 'PSD_DT/PDD_DT' in [SHPG_PLN].
                        // --------------------------------------------------
                        isNormalEnd = insertAtpInfoAndHist();
                        if (!isNormalEnd) {
                            // Update failed because this record has been recently updated by another user.
                            addMsgId(ZZSM4104E);
                            return;
                        }
                    }

                    debug(getClass(), "+++<< Distribution Remainder >>+++++++++++++++++++++++++++++++++++++++++++++++");
                    debug(getClass(), " + size() = ", this.insVal.atpInfoListByDistRemainder);
                    debug(getClass(), "+++<< Distribution Remainder >>+++++++++++++++++++++++++++++++++++++++++++++++");

                    // --------------------------------------------------
                    // insert [ATP_INFO]. (Distribution Remainder)
                    // --------------------------------------------------
                    if (!isEmpty(this.insVal.atpInfoListByDistRemainder)) {
                        isNormalEnd = insertAtpInfoDistRemainder();
                        if (!isNormalEnd) {
                            // Update failed because this record has been recently updated by another user.
                            addMsgId(ZZSM4104E);
                            return;
                        }
                    }
                }
            }

        } finally {
            debugMethodEnd(getClass(), methodNm);
        }
    }

    private void addMsgId(String msgId) {
        this.apiMsgMap.addXxMsgId(msgId);
    }

    private void adjustPsdPdd(List<ATP_INFOTMsg> atpInfoTMsgList) {

        if (!isEmpty(atpInfoTMsgList)) {

            final List<ATP_INFOTMsg> listForAdjust = new ArrayList<ATP_INFOTMsg>(atpInfoTMsgList);
            Collections.sort(listForAdjust, new Comparator<ATP_INFOTMsg>() {
                public int compare(ATP_INFOTMsg atpInfo1, ATP_INFOTMsg atpInfo2) {
                    return -(atpInfo1.psdDt.getValue().compareTo(atpInfo2.psdDt.getValue()));
                }
            });

            final ATP_INFOTMsg atpInfo = listForAdjust.get(0);
            final String psdDt = atpInfo.psdDt.getValue();
            final String pddDt = atpInfo.pddDt.getValue();

            for (ATP_INFOTMsg atpInfoTMsg : atpInfoTMsgList) {
                atpInfoTMsg.psdDt.setValue(psdDt);
                atpInfoTMsg.pddDt.setValue(pddDt);
            }
        }
    }

    /**
     * Process for Set Item Adjust.
     */
    private void adjustPsdPddBySetMdse() {
//        final String methodNm = "adjustPsdPddBySetMdse";
//        debugMethodStart(getClass(), methodNm);
//
//        try {

            // ------------------------------------------------------------
            // grouping by 'TRX_SRC_TP_CD/TRX_HDR_NUM/TRX_LINE_NUM'.
            // ------------------------------------------------------------
            final Map<String, SHPG_PLNTMsg> cmpntShpgPlnMap = new HashMap<String, SHPG_PLNTMsg>();

            for (ShpgPlnBean shpgBean : this.cache.shpgPlnBean.values()) {

                final SHPG_PLNTMsg shpgPlnTMsg = shpgBean.toTMsg();

                // 'TRX_SRC_TP_CD/TRX_HDR_NUM/TRX_LINE_NUM'
                final String trxSrcTpCd = shpgPlnTMsg.trxSrcTpCd.getValue();
                final String trxHdrNum  = shpgPlnTMsg.trxHdrNum.getValue();
                final String trxLineNum = shpgPlnTMsg.trxLineNum.getValue();
                final String setMdseCd  = shpgPlnTMsg.setMdseCd.getValue();

                // if not set component, not regist.
                if (!hasValue(setMdseCd)) {
                    continue;
                }

                final String groupingKey = trxSrcTpCd + "," + trxHdrNum + "," + trxLineNum;
                if (!cmpntShpgPlnMap.containsKey(groupingKey)) {
                    cmpntShpgPlnMap.put(groupingKey, shpgPlnTMsg);
                }
            }

            for (Entry<String, SHPG_PLNTMsg> entry : cmpntShpgPlnMap.entrySet()) {

                final SHPG_PLNTMsg cmpntShpgPln = entry.getValue();
                final String trxSrcTpCd    = cmpntShpgPln.trxSrcTpCd.getValue();
                final String trxHdrNum     = cmpntShpgPln.trxHdrNum.getValue();
                final String trxLineNum    = cmpntShpgPln.trxLineNum.getValue();
                final String trxLineSubNum = "000";
                final String setMdseCd     = cmpntShpgPln.setMdseCd.getValue();
                final String setShpgPlnNum = cmpntShpgPln.setShpgPlnNum.getValue();

                // get cmpsn for set.
                final List<Map<String, ? >> cmpsnMapList = getOrdCmpsnList(trxHdrNum, trxLineNum, trxLineSubNum);
                if (isEmpty(cmpsnMapList)) {
                    continue;
                }

                // get parent shipping plan .
                final SHPG_PLNTMsg parentShpgPln = getShpgPlnBySetShpgPlnNum(setShpgPlnNum);
                if (parentShpgPln == null) {
                    continue;
                }

                // get order qty
                int ordQty;
                if (TRX_SRC_TP.WHOLE_SALES.equals(trxSrcTpCd)) {
                    CPO_DTLTMsg cpoDtlTMsg = getCpoDtl(trxHdrNum, trxLineNum, trxLineSubNum);
                    if (cpoDtlTMsg != null) {
                        ordQty = cpoDtlTMsg.ordQty.getValueInt();
                    } else {
                        continue;
                    }
//                } else if (TRX_SRC_TP.RETAIL.equals(trxSrcTpCd)) {
//                    ROSS_ORDTMsg rossOrdTMsg = getRossOrd(trxHdrNum, trxLineNum, trxLineSubNum);
//                    if (rossOrdTMsg != null) {
//                        ordQty = rossOrdTMsg.ordQty.getValueInt();
//                    } else {
//                        continue;
//                    }
                } else {
                    continue;
                }

                // get atp from memory by ord_num, line.
                List<ATP_INFOTMsg> atpInfoListByOrder = getAtpInfoList(this.insVal.atpInfoList, trxSrcTpCd, trxHdrNum, trxLineNum);
                if (isEmpty(atpInfoListByOrder)) {
                    continue;
                } else {
                    // It need sort for 1) TBD 2) ValidDate 3) Blank(H/A)
                    atpInfoListByOrder = sortAtpForSetOldest(atpInfoListByOrder);
                }

                // --------------------------------------------------
                // new [ATP_INFO].
                // --------------------------------------------------
                final List<ATP_INFOTMsg> newAtpInfoList = new ArrayList<ATP_INFOTMsg>();

                for (; !isEmpty(atpInfoListByOrder);) {

                    final ATP_INFOTMsg atpInfo = atpInfoListByOrder.get(0);
                    String mdseCd = atpInfo.mdseCd.getValue();

                    // Calc for maximam qty for target component.
                    long powQty = 1;
                    long compQty = 1;
                    for (Map<String, ? > cmpsnMap : cmpsnMapList) {
                        String cmpMdseCd = (String) cmpsnMap.get("MDSE_CD");
                        String searchCmpMdseCd = substring(mdseCd, 8);
                        if (cmpMdseCd.equals(mdseCd) || cmpMdseCd.equals(searchCmpMdseCd)) {
                            BigInteger atpQty        = BigInteger.valueOf(atpInfo.allocQty.getValue().longValue());
                            BigInteger curCmpMdseQty = ((BigDecimal) cmpsnMap.get("CHILD_MDSE_QTY")).toBigInteger();
                            BigInteger divans        = atpQty.divide(curCmpMdseQty);
                            powQty  = divans.longValue();
                            compQty = ((BigDecimal) cmpsnMap.get("CHILD_MDSE_QTY")).longValue();
                            break;
                        }
                    }

                    // --------------------------------------------------
                    // new [ATP_INFO] by Order.
                    // --------------------------------------------------
                    final List<ATP_INFOTMsg> newAtpListByOrder = new ArrayList<ATP_INFOTMsg>();

                    // if atp qty > component qty, make new atp record using target atp.
                    // if not, make new atp record using same merchandise atp.
                    if (powQty == 0) {
                        final List<ATP_INFOTMsg> atpListByMdse = getAtpInfoListByMdseCd(atpInfoListByOrder, mdseCd);
                        long maxNextQty = compQty;
                        for (int lastAtpIndex = 0; maxNextQty > 0 && lastAtpIndex < atpListByMdse.size(); lastAtpIndex++) {
                            ATP_INFOTMsg nowAtp = atpListByMdse.get(lastAtpIndex);
                            ATP_INFOTMsg newAtp = new ATP_INFOTMsg();
                            EZDMsg.copy(nowAtp, null, newAtp, null);
                            if (nowAtp.allocQty.getValueInt() < maxNextQty) {
                                maxNextQty -= nowAtp.allocQty.getValueInt();
                                nowAtp.allocQty.setValue(ZERO);
                            } else {
                                nowAtp.allocQty.setValue((int) (nowAtp.allocQty.getValueInt() - maxNextQty));
                                newAtp.allocQty.setValue((int) maxNextQty);
                                maxNextQty = 0;
                            }
                            newAtpListByOrder.add(newAtp);
                        }
                        powQty = 1;
                    } else {
                        ATP_INFOTMsg newAtp = new ATP_INFOTMsg();
                        EZDMsg.copy(atpInfo, null, newAtp, null);
                        long moveQty = (compQty * powQty);
                        long lastQty = atpInfo.allocQty.getValueInt() - moveQty;
                        newAtp.allocQty.setValue(BigDecimal.valueOf(moveQty));
                        atpInfo.allocQty.setValue(BigDecimal.valueOf(lastQty));
                        newAtpListByOrder.add(newAtp);
                    }

                    // Make new atp for other merchandise.
                    long nextCompQty = 1;
                    for (Map<String, ? > cmpsnMap : cmpsnMapList) {
                        String cmpMdseCd = (String) cmpsnMap.get("MDSE_CD");
                        String searchCmpMdseCd = substring(mdseCd, 8);
                        if (cmpMdseCd.equals(mdseCd) || cmpMdseCd.equals(searchCmpMdseCd)) {
                            continue;
                        }
                        nextCompQty = ((BigDecimal) cmpsnMap.get("CHILD_MDSE_QTY")).longValue();

                        final List<ATP_INFOTMsg> atpListByMdse = getAtpInfoListByMdseCd(atpInfoListByOrder, cmpMdseCd);
                        long maxNextQty = nextCompQty * powQty;
                        for (int lastAtpIndex = 0; maxNextQty > 0 && lastAtpIndex < atpListByMdse.size(); lastAtpIndex++) {
                            ATP_INFOTMsg nowAtp = atpListByMdse.get(lastAtpIndex);
                            ATP_INFOTMsg newAtp = new ATP_INFOTMsg();
                            EZDMsg.copy(nowAtp, null, newAtp, null);
                            if (nowAtp.allocQty.getValueInt() < maxNextQty) {
                                maxNextQty -= nowAtp.allocQty.getValueInt();
                                nowAtp.allocQty.setValue(ZERO);
                            } else {
                                nowAtp.allocQty.setValue((int) (nowAtp.allocQty.getValueInt() - maxNextQty));
                                newAtp.allocQty.setValue((int) maxNextQty);
                                maxNextQty = 0;
                            }
                            newAtpListByOrder.add(newAtp);
                        }
                    }

                    // Adjust psd/pdd
                    adjustPsdPdd(newAtpListByOrder);

                    // Get newest eta date
                    ATP_INFOTMsg etaLastAtp = null;
                    String eta = "";
                    for (int i = 0; i < newAtpListByOrder.size(); i++) {
                        ATP_INFOTMsg newAtp = newAtpListByOrder.get(i);
                        if (i == 0) {
                            eta = newAtp.whEtaDt.getValue();
                            etaLastAtp = newAtp;
                            continue;
                        }
                        if (ZYPDateUtil.compare(eta, newAtp.whEtaDt.getValue()) == -1) {
                            eta = newAtp.whEtaDt.getValue();
                            etaLastAtp = newAtp;
                        }
                    }
                    if (etaLastAtp == null) {
                        break;
                    }

                    // Make parent atp(for set).
                    final ATP_INFOTMsg newParentAtp = new ATP_INFOTMsg();
                    EZDMsg.copy(etaLastAtp, null, newParentAtp, null);

                    newParentAtp.mdseCd.setValue(setMdseCd);
                    newParentAtp.shpgPlnNum.setValue(parentShpgPln.shpgPlnNum.getValue());
                    newParentAtp.shpgStsCd.setValue(parentShpgPln.shpgStsCd.getValue());
                    newParentAtp.invtyLocCd.setValue(parentShpgPln.invtyLocCd.getValue());
                    newParentAtp.cpoQty.setValue(ordQty);
                    newParentAtp.allocQty.setValue(BigDecimal.valueOf(1 * powQty));
                    newParentAtp.trxLineSubNum.setValue("000");
                    newParentAtp.distPk.clear();
                    newParentAtp.distTmFrameNum.clear();
                    newParentAtp.inbdVisEventCd.clear();
                    newParentAtp.imptInvNum.clear();
                    newParentAtp.vndInvDoNum.clear();
                    newParentAtp.imptCntnrNum.clear();

                    // Add Atp List
                    newAtpInfoList.add(newParentAtp);
                    newAtpInfoList.addAll(newAtpListByOrder);
                    // Clear Src Atp.
                    removeZeroAllocQtyAtpInfo(atpInfoListByOrder);
                }

                removeZeroAllocQtyAtpInfo(this.insVal.atpInfoList);
                this.insVal.atpInfoList.addAll(newAtpInfoList);
            }

//        } finally {
//            debugMethodEnd(getClass(), methodNm);
//        }
    }

    private void adjustPsdPddByConfig() {
        //  shpgPlnMap<CPO#, Map<Config#, List<SHPG_PLNTMsg>>>
        final Map<String, Map<String, List<SHPG_PLNTMsg>>> shpgPlnMap = new HashMap<String, Map<String, List<SHPG_PLNTMsg>>>();
        final Map<String, List<Map<String, String>>> ordListMap = new HashMap<String, List<Map<String, String>>>();
        final Set<String> shpgPlnNumSet = new HashSet<String>();

        String configNum = "";

        List<Map<String, String>> retList;
        for (ShpgPlnBean shpgPlnBean : this.cache.shpgPlnBean.values()) {

            final SHPG_PLNTMsg shpgPlnTMsg = shpgPlnBean.toTMsg();

            // 'TRX_SRC_TP_CD/TRX_HDR_NUM'
            final String trxSrcTpCd = shpgPlnTMsg.trxSrcTpCd.getValue();
            final String trxHdrNum  = shpgPlnTMsg.trxHdrNum.getValue();
            final String groupingKey = trxHdrNum;

            final String shpgPlnNumCache = shpgPlnTMsg.shpgPlnNum.getValue();


            if (ordListMap.containsKey(groupingKey)) {
                retList = ordListMap.get(groupingKey);
            } else {
                Map<String, String> ssmParam = new HashMap<String, String>();
                ssmParam.put("glblCmpyCd",  getGlblCmpyCd());
                ssmParam.put("trxSrcTpCd",  trxSrcTpCd);
                ssmParam.put("trxHdrNum",   trxHdrNum);
                retList = (List<Map<String, String>>) ssmClient.queryObjectList("getOrdInfo", ssmParam);
                if (retList.isEmpty()) {
                    continue;
                }
                ordListMap.put(groupingKey, retList);
            }

            for (Map<String, String> rsltMap : retList) {
                if (shpgPlnNumCache.equals(rsltMap.get("SHPG_PLN_NUM"))) {
                    configNum = rsltMap.get("DS_ORD_POSN_NUM");
                    break;
                }
            }

            Map<String, List<SHPG_PLNTMsg>> cpoInfoMap;
            List<SHPG_PLNTMsg> shpgPlnList;
            if (shpgPlnMap.containsKey(groupingKey)) {
                cpoInfoMap = shpgPlnMap.get(groupingKey);
                if (cpoInfoMap.containsKey(configNum)) {
                    shpgPlnList = cpoInfoMap.get(configNum);
                } else {
                    shpgPlnList = new ArrayList<SHPG_PLNTMsg>();
                }
            } else {
                cpoInfoMap = new HashMap<String, List<SHPG_PLNTMsg>>();
                shpgPlnList = new ArrayList<SHPG_PLNTMsg>();
            }
            shpgPlnList.add(shpgPlnTMsg);
            cpoInfoMap.put(configNum, shpgPlnList);
            shpgPlnMap.put(groupingKey, cpoInfoMap);

            // SHPG_PLN_NUM
            shpgPlnNumSet.add(shpgPlnTMsg.shpgPlnNum.getValue());
        }

        // Map<SHPG_PLN_NUM, List<ATP_INFOTMsg>>
        final Map<String, List<ATP_INFOTMsg>> atpInfoMap = new HashMap<String, List<ATP_INFOTMsg>>();
        for (ATP_INFOTMsg atpInfo : this.insVal.atpInfoList) {
            final String shpgPlnNum = atpInfo.shpgPlnNum.getValue();
            if  (shpgPlnNumSet.contains(shpgPlnNum)) {
                if (!atpInfoMap.containsKey(shpgPlnNum)) {
                    atpInfoMap.put(shpgPlnNum, new ArrayList<ATP_INFOTMsg>());
                }
                atpInfoMap.get(shpgPlnNum).add(atpInfo);
            }
        }
        shpgPlnNumSet.clear();

        // ------------------------------------------------------------
        // adjust 'PSD_DT/PDD_ST'.
        // ------------------------------------------------------------
        for (Entry<String, Map<String, List<SHPG_PLNTMsg>>> entryCpo : shpgPlnMap.entrySet()) {
            String cpoOrdNum = entryCpo.getKey();
            Map<String, List<SHPG_PLNTMsg>> configInfo = entryCpo.getValue();
            for (Entry<String, List<SHPG_PLNTMsg>> entryConfig : configInfo.entrySet()) {

                // ATP_INFO
                final List<ATP_INFOTMsg> atpInfoList = new ArrayList<ATP_INFOTMsg>();
                for (SHPG_PLNTMsg shpgPlnTMsg : entryConfig.getValue()) {
                    final String shpgPlnNum = shpgPlnTMsg.shpgPlnNum.getValue();
                    if (atpInfoMap.containsKey(shpgPlnNum)) {
                        atpInfoList.addAll(atpInfoMap.get(shpgPlnNum));
                        atpInfoMap.remove(shpgPlnNum);
                    }
                }
                // adjust psd pdd for same ship complete code.
                adjustPsdPddByOrdConfig(atpInfoList, ordListMap.get(cpoOrdNum));
            }
        }
    }

    private void adjustPsdPddByOrdConfig(List<ATP_INFOTMsg> atpInfoTMsgList, List<Map<String, String>> ordConfigList) {
        if (!isEmpty(atpInfoTMsgList)) {
            boolean isExec = false;
            String shpgPlnNum;
            String psdDt = "";
            String pddDt = "";

            for (Map<String, String> rlstMap : ordConfigList) {
                if (BACK_ORD_IMPCT_TP.CRITICAL.equals(rlstMap.get("BACK_ORD_IMPCT_TP_CD"))
                        || BACK_ORD_IMPCT_TP.ESSENTIAL.equals(rlstMap.get("BACK_ORD_IMPCT_TP_CD"))) {
                    isExec = true;
                    shpgPlnNum = rlstMap.get("SHPG_PLN_NUM");
                    for (ATP_INFOTMsg atpInfoTMsg : atpInfoTMsgList) {
                        if (shpgPlnNum.equals(atpInfoTMsg.shpgPlnNum.getValue())) {
                            if (ZYPCommonFunc.hasValue(pddDt)) {
                                if (ZYPDateUtil.compare(pddDt, atpInfoTMsg.pddDt.getValue()) < 0) {
                                    psdDt = atpInfoTMsg.psdDt.getValue();
                                    pddDt = atpInfoTMsg.pddDt.getValue();
                                }
                            } else {
                                psdDt = atpInfoTMsg.psdDt.getValue();
                                pddDt = atpInfoTMsg.pddDt.getValue();
                            }
                            break;
                        }
                    }
                }
            }
            if (!isExec) {
                return;
            }

            for (ATP_INFOTMsg atpInfoTMsg : atpInfoTMsgList) {
                if (ZYPDateUtil.compare(pddDt, atpInfoTMsg.pddDt.getValue()) > 0) {
                    atpInfoTMsg.psdDt.setValue(psdDt);
                    atpInfoTMsg.pddDt.setValue(pddDt);
                }
            }
        }
    }
    // 2016/03/16 CSA: ReqID P628 End.

    private void adjustPsdPddByShipCplt() {
//        final String methodNm = "adjustPsdPddByShipCplt";
//        debugMethodStart(getClass(), methodNm);
//
//        try {

            // ------------------------------------------------------------
            // grouping by 'TRX_SRC_TP_CD/TRX_HDR_NUM/SHIP_CPLT_CD'.
            // ------------------------------------------------------------
            final Map<String, List<SHPG_PLNTMsg>> shpgPlnMap = new HashMap<String, List<SHPG_PLNTMsg>>();

            final Set<String> shpgPlnNumSet = new HashSet<String>();

            for (ShpgPlnBean shpgPlnBean : this.cache.shpgPlnBean.values()) {

                final SHPG_PLNTMsg shpgPlnTMsg = shpgPlnBean.toTMsg();

                // 'TRX_SRC_TP_CD/TRX_HDR_NUM/SHIP_CPLT_CD'
                final String trxSrcTpCd = shpgPlnTMsg.trxSrcTpCd.getValue();
                final String trxHdrNum  = shpgPlnTMsg.trxHdrNum.getValue();
                final String shipCpltCd = shpgPlnTMsg.shipCpltCd.getValue();

                if (!hasValue(shipCpltCd)) {
                    continue;
                }

                final String groupingKey = trxSrcTpCd + "," + trxHdrNum + "," + shipCpltCd;

                // SHPG_PLN
                if (!shpgPlnMap.containsKey(groupingKey)) {
                    shpgPlnMap.put(groupingKey, new ArrayList<SHPG_PLNTMsg>());
                }
                shpgPlnMap.get(groupingKey).add(shpgPlnTMsg);

                // SHPG_PLN_NUM
                shpgPlnNumSet.add(shpgPlnTMsg.shpgPlnNum.getValue());
            }

            // Map<SHPG_PLN_NUM, List<ATP_INFOTMsg>>
            final Map<String, List<ATP_INFOTMsg>> atpInfoMap = new HashMap<String, List<ATP_INFOTMsg>>();
            for (ATP_INFOTMsg atpInfo : this.insVal.atpInfoList) {
                final String shpgPlnNum = atpInfo.shpgPlnNum.getValue();
                if  (shpgPlnNumSet.contains(shpgPlnNum)) {
                    if (!atpInfoMap.containsKey(shpgPlnNum)) {
                        atpInfoMap.put(shpgPlnNum, new ArrayList<ATP_INFOTMsg>());
                    }
                    atpInfoMap.get(shpgPlnNum).add(atpInfo);
                }
            }
            shpgPlnNumSet.clear();

            // ------------------------------------------------------------
            // adjust 'PSD_DT/PDD_ST'.
            // ------------------------------------------------------------
            for (Entry<String, List<SHPG_PLNTMsg>> entry : shpgPlnMap.entrySet()) {

                // ATP_INFO
                final List<ATP_INFOTMsg> atpInfoList = new ArrayList<ATP_INFOTMsg>();
                for (SHPG_PLNTMsg shpgPlnTMsg : entry.getValue()) {
                    final String shpgPlnNum = shpgPlnTMsg.shpgPlnNum.getValue();
                    if (atpInfoMap.containsKey(shpgPlnNum)) {
                        atpInfoList.addAll(atpInfoMap.get(shpgPlnNum));
                        atpInfoMap.remove(shpgPlnNum);
                    }
                }

                // adjust psd pdd for same ship complete code.
                adjustPsdPdd(atpInfoList);
            }

//        } finally {
//            debugMethodEnd(getClass(), methodNm);
//        }
    }

    /**
     * Create atp memory for TBD. (allocQty is shipping plan ord qty).
     * @param shpgPlnTMsg
     * @param mdseCd
     * @param ordQty
     */
    private List<ATP_INFOTMsg> createAtpInfoTMsgForTBD(SHPG_PLNTMsg shpgPlnTMsg, String mdseCd, int ordQty) {
        return createAtpInfoTMsgForTBD(shpgPlnTMsg, mdseCd, ordQty, shpgPlnTMsg.ordQty.getValueInt());
    }

    /**
     * Create atp memory for TBD.
     * @param shpgPlnTMsg
     * @param mdseCd
     * @param ordQty
     * @param allocQty
     */
    private List<ATP_INFOTMsg> createAtpInfoTMsgForTBD(SHPG_PLNTMsg shpgPlnTMsg, String mdseCd, int ordQty, int allocQty) {

        final ATP_INFOTMsg atpInfoTMsg = new ATP_INFOTMsg();

        atpInfoTMsg.atpInfoPk.clear();
        atpInfoTMsg.atpCalcTs.clear();
        atpInfoTMsg.trxSrcTpCd.setValue(shpgPlnTMsg.trxSrcTpCd.getValue());
        atpInfoTMsg.trxHdrNum.setValue(shpgPlnTMsg.trxHdrNum.getValue());
        atpInfoTMsg.trxLineNum.setValue(shpgPlnTMsg.trxLineNum.getValue());
        atpInfoTMsg.trxLineSubNum.setValue(shpgPlnTMsg.trxLineSubNum.getValue());
        atpInfoTMsg.shpgPlnNum.setValue(shpgPlnTMsg.shpgPlnNum.getValue());
        atpInfoTMsg.shpgStsCd.setValue(shpgPlnTMsg.shpgStsCd.getValue());
        atpInfoTMsg.mdseCd.setValue(mdseCd);
        atpInfoTMsg.invtyLocCd.setValue(shpgPlnTMsg.invtyLocCd.getValue());
        atpInfoTMsg.allocQty.setValue(allocQty);
        atpInfoTMsg.cpoQty.setValue(ordQty);
        atpInfoTMsg.whEtaDt.setValue(LABEL_TBD);
        atpInfoTMsg.psdDt.setValue(LABEL_TBD);
        atpInfoTMsg.pddDt.setValue(LABEL_TBD);
        atpInfoTMsg.distPk.clear();
        atpInfoTMsg.distTmFrameNum.clear(); // mean blank.
        atpInfoTMsg.inbdVisEventCd.clear(); // mean blank.
        atpInfoTMsg.imptInvNum.clear(); // mean blank.
        atpInfoTMsg.vndInvDoNum.clear(); // mean blank.
        atpInfoTMsg.imptCntnrNum.clear(); // mean blank.

        return new ArrayList<ATP_INFOTMsg>(asList(atpInfoTMsg));
    }

    /**
     * Create atp memory for Vendor drop.
     * @param shpgPlnTMsg
     * @param mdseCd
     * @param ordQty
     * @return
     */
    private List<ATP_INFOTMsg> createAtpInfoTMsgForVendorDrop(SHPG_PLNTMsg shpgPlnTMsg, String mdseCd, int ordQty) {

        // [PO_DTL]
        List<PO_DTLTMsg> poDtlTMsgList = getPoDtlList(shpgPlnTMsg.shpgPlnNum.getValue());
        if (isEmpty(poDtlTMsgList)) {
            return emptyList();
        }
        PO_DTLTMsg poDtlTMsg = poDtlTMsgList.get(0);

        final ATP_INFOTMsg atpInfoTMsg = new ATP_INFOTMsg();

        atpInfoTMsg.atpInfoPk.clear();
        atpInfoTMsg.atpCalcTs.clear();
        atpInfoTMsg.trxSrcTpCd.setValue(shpgPlnTMsg.trxSrcTpCd.getValue());
        atpInfoTMsg.trxHdrNum.setValue(shpgPlnTMsg.trxHdrNum.getValue());
        atpInfoTMsg.trxLineNum.setValue(shpgPlnTMsg.trxLineNum.getValue());
        atpInfoTMsg.trxLineSubNum.setValue(shpgPlnTMsg.trxLineSubNum.getValue());
        atpInfoTMsg.shpgPlnNum.setValue(shpgPlnTMsg.shpgPlnNum.getValue());
        atpInfoTMsg.shpgStsCd.setValue(shpgPlnTMsg.shpgStsCd.getValue());
        atpInfoTMsg.mdseCd.setValue(mdseCd);
        atpInfoTMsg.invtyLocCd.setValue(shpgPlnTMsg.invtyLocCd.getValue());
        atpInfoTMsg.cpoQty.setValue(ordQty);
        atpInfoTMsg.allocQty.setValue(ordQty);
        atpInfoTMsg.whEtaDt.setValue(poDtlTMsg.etaDt.getValue());
        atpInfoTMsg.psdDt.clear(); // LABEL_BLANK
        atpInfoTMsg.pddDt.clear(); // LABEL_BLANK
        atpInfoTMsg.distPk.clear();
        atpInfoTMsg.distTmFrameNum.setValue(LABEL_BLANK);
        atpInfoTMsg.inbdVisEventCd.setValue(LABEL_BLANK);
        atpInfoTMsg.imptInvNum.setValue(LABEL_BLANK);
        atpInfoTMsg.vndInvDoNum.setValue(LABEL_DOMES);
        atpInfoTMsg.imptCntnrNum.setValue(LABEL_DOMES);

        return new ArrayList<ATP_INFOTMsg>(asList(atpInfoTMsg));
    }

    /**
     * Create atp memory for CUSA Vendor drop.
     * @param shpgPlnTMsg
     * @param mdseCd
     * @param ordQty
     * @return
     */
    private List<ATP_INFOTMsg> createAtpInfoTMsgForCusaVendorDrop(SHPG_PLNTMsg shpgPlnTMsg, String mdseCd, int ordQty) {

        // SPLY_INFO (Stock In, Stock Out)
        List<PO_DTLTMsg> poDtlTMsgList = getPoDtlListByTrxNum(shpgPlnTMsg);
        if (isEmpty(poDtlTMsgList)) {
            return emptyList();
        }
        PO_DTLTMsg poDtlTMsg = poDtlTMsgList.get(0);

        String keyVndInvDoNum = poDtlTMsg.poOrdNum.getValue() + poDtlTMsg.poOrdDtlLineNum.getValue();
        List<Map> resultList = getSplyInfoListForCusa(keyVndInvDoNum);
        if (isEmpty(resultList)) {
            return emptyList();
        }
        List<ATP_INFOTMsg> atpInfoTMsgList = new ArrayList<ATP_INFOTMsg>(0);
        for (Map cusaPoInfo : resultList) {
            BigDecimal avalQty = (BigDecimal) cusaPoInfo.get("AVAL_QTY");
            String etdDt = (String) cusaPoInfo.get("ETD_DT");
            String etaDt = (String) cusaPoInfo.get("ETA_DT");
            String imptInvNum = (String) cusaPoInfo.get("IMPT_INV_NUM");
            String vndInvDoNum = (String) cusaPoInfo.get("VND_INV_DO_NUM");
            String imptCntnrNum = (String) cusaPoInfo.get("IMPT_CNTNR_NUM");
            String inbdVisEventCd = (String) cusaPoInfo.get("INBD_VIS_EVENT_CD");

            final ATP_INFOTMsg atpInfoTMsg = new ATP_INFOTMsg();

            atpInfoTMsg.atpInfoPk.clear();
            ZYPEZDItemValueSetter.setValue(atpInfoTMsg.atpCalcTs, insVal.procDt);
            ZYPEZDItemValueSetter.setValue(atpInfoTMsg.trxSrcTpCd, shpgPlnTMsg.trxSrcTpCd);
            ZYPEZDItemValueSetter.setValue(atpInfoTMsg.trxHdrNum, shpgPlnTMsg.trxHdrNum);
            ZYPEZDItemValueSetter.setValue(atpInfoTMsg.trxLineNum, shpgPlnTMsg.trxLineNum);
            ZYPEZDItemValueSetter.setValue(atpInfoTMsg.trxLineSubNum, shpgPlnTMsg.trxLineSubNum);
            ZYPEZDItemValueSetter.setValue(atpInfoTMsg.shpgPlnNum, shpgPlnTMsg.shpgPlnNum);
            ZYPEZDItemValueSetter.setValue(atpInfoTMsg.shpgStsCd, shpgPlnTMsg.shpgStsCd);
            ZYPEZDItemValueSetter.setValue(atpInfoTMsg.mdseCd, mdseCd);
            ZYPEZDItemValueSetter.setValue(atpInfoTMsg.invtyLocCd, shpgPlnTMsg.invtyLocCd);
            ZYPEZDItemValueSetter.setValue(atpInfoTMsg.cpoQty, avalQty);
            ZYPEZDItemValueSetter.setValue(atpInfoTMsg.allocQty, avalQty);
            if (ZYPCommonFunc.hasValue(etdDt)) {
                ZYPEZDItemValueSetter.setValue(atpInfoTMsg.whEtaDt, etdDt);
                ZYPEZDItemValueSetter.setValue(atpInfoTMsg.psdDt, etdDt);
                ZYPEZDItemValueSetter.setValue(atpInfoTMsg.pddDt, etaDt);
            } else {
                ZYPEZDItemValueSetter.setValue(atpInfoTMsg.whEtaDt, etaDt);
                ZYPEZDItemValueSetter.setValue(atpInfoTMsg.psdDt, LABEL_TBD);
                ZYPEZDItemValueSetter.setValue(atpInfoTMsg.pddDt, LABEL_TBD);
            }
            atpInfoTMsg.distPk.clear();
            ZYPEZDItemValueSetter.setValue(atpInfoTMsg.distTmFrameNum, LABEL_BLANK);
            ZYPEZDItemValueSetter.setValue(atpInfoTMsg.inbdVisEventCd, inbdVisEventCd);
            ZYPEZDItemValueSetter.setValue(atpInfoTMsg.imptInvNum, imptInvNum);
            ZYPEZDItemValueSetter.setValue(atpInfoTMsg.vndInvDoNum, vndInvDoNum);
            ZYPEZDItemValueSetter.setValue(atpInfoTMsg.imptCntnrNum, imptCntnrNum);

            atpInfoTMsgList.add(atpInfoTMsg);
        }
        return atpInfoTMsgList;
    }

    /**
     * Get atp info from memory by shpgPlnNum.
     * @param atpInfoTMsgList
     * @param shpgPlnNum
     * @return
     */
    private List<ATP_INFOTMsg> getAtpInfoList(List<ATP_INFOTMsg> atpInfoTMsgList, String shpgPlnNum) {

        if (isEmpty(atpInfoTMsgList)) {
            return emptyList();
        }

        final List<ATP_INFOTMsg> retList = new ArrayList<ATP_INFOTMsg>();

        for (ATP_INFOTMsg atpInfoTMsg : atpInfoTMsgList) {
            if (atpInfoTMsg.shpgPlnNum.getValue().equals(shpgPlnNum)) {
                retList.add(atpInfoTMsg);
            }
        }

        return retList;
    }

    /**
     * Get atp info from memory by trxSrcTpCd,trxHdrNum,trxLineNum.
     * @param atpInfoTMsgList
     * @param trxSrcTpCd
     * @param trxHdrNum
     * @param trxLineNum
     * @return
     */
    private List<ATP_INFOTMsg> getAtpInfoList(List<ATP_INFOTMsg> atpInfoTMsgList, String trxSrcTpCd, String trxHdrNum, String trxLineNum) {
        return getAtpInfoList(atpInfoTMsgList, trxSrcTpCd, trxHdrNum, trxLineNum, null);
    }

    /**
     * Get atp info from memory by
     * trxSrcTpCdtrxHdrNum,trxLineNum,trxLineSubNum.
     * @param atpInfoTMsgList
     * @param trxSrcTpCd
     * @param trxHdrNum
     * @param trxLineNum
     * @param trxLineSubNum
     * @return
     */
    private List<ATP_INFOTMsg> getAtpInfoList(List<ATP_INFOTMsg> atpInfoTMsgList, String trxSrcTpCd, String trxHdrNum, String trxLineNum, String trxLineSubNum) {

        if (isEmpty(atpInfoTMsgList)) {
            return emptyList();
        }

        final List<ATP_INFOTMsg> retList = new ArrayList<ATP_INFOTMsg>();

        for (ATP_INFOTMsg atpInfoTMsg : atpInfoTMsgList) {

            final boolean isEqualsSrcTpCd    = atpInfoTMsg.trxSrcTpCd.getValue().equals(trxSrcTpCd);
            final boolean isEqualsHdrNum     = atpInfoTMsg.trxHdrNum.getValue().equals(trxHdrNum);
            final boolean isEqualsLineNum    = atpInfoTMsg.trxLineNum.getValue().equals(trxLineNum);
            final boolean isEqualsLineSubNum = atpInfoTMsg.trxLineSubNum.getValue().equals(trxLineSubNum);

            if (hasValue(trxLineSubNum)) {
                if (isEqualsSrcTpCd && isEqualsHdrNum && isEqualsLineNum && isEqualsLineSubNum) {
                    retList.add(atpInfoTMsg);
                }

            } else if (hasValue(trxLineNum)) {
                if (isEqualsSrcTpCd && isEqualsHdrNum && isEqualsLineNum) {
                    retList.add(atpInfoTMsg);
                }

            } else {
                if (isEqualsSrcTpCd && isEqualsHdrNum) {
                    retList.add(atpInfoTMsg);
                }
            }
        }

        return retList;
    }

    @SuppressWarnings("unchecked")
    private List<ATP_INFOTMsg> getAtpInfoList(String trxSrcTpCd, String trxHdrNum, String trxLineNum, String trxLineSubNum, BigDecimal distPk, String distTmFrameNum, String shpgPlnNum) {

        final ATP_INFOTMsg atpInfoTMsg = new ATP_INFOTMsg();

        // GLBL_CMPY_CD
        atpInfoTMsg.glblCmpyCd.setValue(getGlblCmpyCd());
        // TRX_SRC_TP_CD
        if (hasValue(trxSrcTpCd)) {
            atpInfoTMsg.trxSrcTpCd.setValue(trxSrcTpCd);
        }
        // TRX_HDR_NUM
        if (hasValue(trxHdrNum)) {
            atpInfoTMsg.trxHdrNum.setValue(trxHdrNum);
        }
        // TRX_LINE_NUM
        if (hasValue(trxLineNum)) {
            atpInfoTMsg.trxLineNum.setValue(trxLineNum);
        }
        // TRX_LINE_SUB_NUM
        if (hasValue(trxLineSubNum)) {
            atpInfoTMsg.trxLineSubNum.setValue(trxLineSubNum);
        }
        // DIST_PK
        if (hasValue(distPk)) {
            atpInfoTMsg.distPk.setValue(distPk);
        }
        // DIST_TM_FRAME_NUM
        if (hasValue(distTmFrameNum)) {
            atpInfoTMsg.distTmFrameNum.setValue(distTmFrameNum);
        }
        // Add for DefectID 1847
        // SHPG_PLN_NUM
        if (hasValue(shpgPlnNum)) {
            atpInfoTMsg.shpgPlnNum.setValue(shpgPlnNum);
        }

        return ssmClient.queryObjectList("getAtpInfoByOrderNumDataRs", atpInfoTMsg);
    }

    /**
     * Get atp info from memory by mdseCd.
     * @param atpInfoTMsgList
     * @param mdseCd
     * @return
     */
    private List<ATP_INFOTMsg> getAtpInfoListByMdseCd(List<ATP_INFOTMsg> atpInfoTMsgList, String mdseCd) {

        if (isEmpty(atpInfoTMsgList)) {
            return emptyList();
        }

        final List<ATP_INFOTMsg> retList = new ArrayList<ATP_INFOTMsg>();

        for (ATP_INFOTMsg atpInfoTMsg : atpInfoTMsgList) {
            if (atpInfoTMsg.mdseCd.getValue().startsWith(mdseCd)) {
                retList.add(atpInfoTMsg);
            }
        }

        return retList;
    }

    @SuppressWarnings("unchecked")
    private List<ATP_INFOTMsg> getAtpInfoListDistRemainder(BigDecimal distPk, String distTmFrameNum) {

        final ATP_INFOTMsg atpInfoTMsg = new ATP_INFOTMsg();

        atpInfoTMsg.glblCmpyCd.setValue(getGlblCmpyCd());
        // DIST_PK
        if (hasValue(distPk)) {
            atpInfoTMsg.distPk.setValue(distPk);
        }
        // DIST_TM_FRAME_NUM
        if (hasValue(distTmFrameNum)) {
            atpInfoTMsg.distTmFrameNum.setValue(distTmFrameNum);
        }

        return ssmClient.queryObjectList("getAtpInfoListDistRemainder", atpInfoTMsg);
    }

    /**
     * Get nearly ATP_INFOTMsg. checdate > procDate and nealy date.
     * @param atpInfoTMsgList
     * @return
     */
    private ATP_INFOTMsg getAtpInfoPsdOldestDay(List<ATP_INFOTMsg> atpInfoTMsgList) {

        ATP_INFOTMsg oldestAtpInfoTMsg = null;
        Pattern ptn = Pattern.compile("\\d{8}");

        for (ATP_INFOTMsg atpInfoTMsg : atpInfoTMsgList) {
            String psdDt = atpInfoTMsg.psdDt.getValue();
            if (!hasValue(psdDt)) {
                continue;
            }
            Matcher mc = ptn.matcher(psdDt);
            if (!mc.matches()) {
                continue;
            }
            if (oldestAtpInfoTMsg == null) {
                oldestAtpInfoTMsg = atpInfoTMsg;
            } else {
                if (ZYPDateUtil.compare(oldestAtpInfoTMsg.psdDt.getValue(), psdDt) == 1) {
                    oldestAtpInfoTMsg = atpInfoTMsg;
                }
            }
        }

        return oldestAtpInfoTMsg;
    }

    private String getBiggestDate(List<String> dateStrList) {

        if (isEmpty(dateStrList)) {
            return "";
        }

        String biggestDateStr = "";
        for (String dateStr : dateStrList) {

            if (!hasValue(dateStr)) {
                continue;
            }

            if (!ZYPDateUtil.checkDate(dateStr)) {
                continue;
            }

            if (biggestDateStr.length() == 0) {
                biggestDateStr = dateStr;
                continue;
            }

            if (ZYPDateUtil.compare(biggestDateStr, dateStr) == -1) {
                biggestDateStr = dateStr;
            }
        }

        return biggestDateStr;
    }

    @SuppressWarnings("unchecked")
    private List<CMPSNTMsg> getCmpsnList(String prntMdseCd) {

        final String cacheKey = createCacheKey(getGlblCmpyCd(), prntMdseCd);

        List<CMPSNTMsg> retList = this.cache.cmpsn.get(cacheKey);

        if (retList == null) {
            CMPSNTMsg ssmParam = new CMPSNTMsg();
            ssmParam.glblCmpyCd.setValue(getGlblCmpyCd());
            ssmParam.prntMdseCd.setValue(prntMdseCd);
            retList = ssmClient.queryObjectList("getCmpsnList", ssmParam);
            if (retList != null) {
                this.cache.cmpsn.put(cacheKey, retList);
            }
        }

        return retList;
    }

    private List<Map<String, ? >> getOrdCmpsnList(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        final String cacheKey = createCacheKey(getGlblCmpyCd(), cpoOrdNum, cpoDtlLineNum);
        List<Map<String, ? >> retList = this.cache.ordCmpsn.get(cacheKey);
        if (retList == null) {
            Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd",       getGlblCmpyCd());
            ssmParam.put("cpoOrdNum",        cpoOrdNum);
            ssmParam.put("cpoDtlLineNum",    cpoDtlLineNum);
            ssmParam.put("cpoDtlLineSubNum", cpoDtlLineSubNum);
            retList = (List<Map<String, ? >>) ssmClient.queryObjectList("getOrdCmpsnList", ssmParam);
            if (retList != null) {
                this.cache.ordCmpsn.put(cacheKey, retList);
            }
        }
        return retList;
    }

    private CPO_DTLTMsg getCpoDtl(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        cpoDtlTMsg.glblCmpyCd.setValue(getGlblCmpyCd());
        cpoDtlTMsg.cpoOrdNum.setValue(cpoOrdNum);
        cpoDtlTMsg.cpoDtlLineNum.setValue(cpoDtlLineNum);
        cpoDtlTMsg.cpoDtlLineSubNum.setValue(cpoDtlLineSubNum);

        return (CPO_DTLTMsg) findByKey(cpoDtlTMsg);
    }

    private String getGlblCmpyCd() {
        return this.insVal.glblCmpyCd;
    }

    private INVTY_LOC_VTMsg getInvtyLocV(String invtyLocCd) {

        final String cacheKey = createCacheKey(getGlblCmpyCd(), invtyLocCd);

        INVTY_LOC_VTMsg invtyLocVTMsg = this.cache.invtyLocV.get(cacheKey);

        if (invtyLocVTMsg == null) {
            invtyLocVTMsg = new INVTY_LOC_VTMsg();
            invtyLocVTMsg.glblCmpyCd.setValue(getGlblCmpyCd());
            invtyLocVTMsg.invtyLocCd.setValue(invtyLocCd);
            invtyLocVTMsg = (INVTY_LOC_VTMsg) ssmClient.queryObject("getInvtyLocV", invtyLocVTMsg);
            if (invtyLocVTMsg != null) {
                this.cache.invtyLocV.put(cacheKey, invtyLocVTMsg);
            }
        }

        return invtyLocVTMsg;
    }

    private MDSETMsg getMdse(String mdseCd) {
        return NWXMdseTMsgThreadLocalCache.getInstance().get(getGlblCmpyCd(), mdseCd);
    }

    @SuppressWarnings("unchecked")
    private List<PO_DTLTMsg> getPoDtlList(String shpgPlnNum) {

        PO_DTLTMsg poDtlTMsg = new PO_DTLTMsg();
        poDtlTMsg.glblCmpyCd.setValue(getGlblCmpyCd());
        poDtlTMsg.shpgPlnNum.setValue(shpgPlnNum);

        return ssmClient.queryObjectList("getPoDtlList", poDtlTMsg);
    }

//    private ROSS_ORDTMsg getRossOrd(String rossOrdNum, String rossDtlLineNum, String rossDtlLineSubNum) {
//
//        ROSS_ORDTMsg rossOrdTMsg = new ROSS_ORDTMsg();
//        rossOrdTMsg.glblCmpyCd.setValue(getGlblCmpyCd());
//        rossOrdTMsg.rossOrdNum.setValue(rossOrdNum);
//        rossOrdTMsg.rossDtlLineNum.setValue(rossDtlLineNum);
//        rossOrdTMsg.rossDtlLineSubNum.setValue(rossDtlLineSubNum);
//
//        return (ROSS_ORDTMsg) findByKey(rossOrdTMsg);
//    }

    private SHPG_PLNTMsg getShpgPln(String shpgPlnNum) {

        ShpgPlnBean shpgPlnBean = this.cache.shpgPlnBean.get(shpgPlnNum);

        if (shpgPlnBean == null) {
            SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
            shpgPlnTMsg.glblCmpyCd.setValue(getGlblCmpyCd());
            shpgPlnTMsg.shpgPlnNum.setValue(shpgPlnNum);
            shpgPlnTMsg = (SHPG_PLNTMsg) findByKey(shpgPlnTMsg);
            if (shpgPlnTMsg != null) {
                shpgPlnBean = new ShpgPlnBean(shpgPlnTMsg);
                this.cache.shpgPlnBean.put(shpgPlnNum, shpgPlnBean);
            }
        }

        SHPG_PLNTMsg shpgPlnTMsg = null;
        if (shpgPlnBean != null) {
            shpgPlnTMsg = shpgPlnBean.toTMsg();
        }
        return shpgPlnTMsg;
    }

    private SHPG_PLNTMsg getShpgPlnBySetShpgPlnNum(String setShpgPlnNum) {

        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        shpgPlnTMsg.glblCmpyCd.setValue(getGlblCmpyCd());
        shpgPlnTMsg.shpgPlnNum.setValue(setShpgPlnNum);

        return (SHPG_PLNTMsg) findByKey(shpgPlnTMsg);
    }

    private SOFT_ALLOCTMsg getSoftAlloc(BigDecimal softAllocPk) {

        SOFT_ALLOCTMsg softAllocTMsg = new SOFT_ALLOCTMsg();
        softAllocTMsg.glblCmpyCd.setValue(getGlblCmpyCd());
        softAllocTMsg.softAllocPk.setValue(softAllocPk);

        return (SOFT_ALLOCTMsg) findByKey(softAllocTMsg);
    }

    @SuppressWarnings("unchecked")
    private List<SPLY_INFOTMsg> getSplyInfoList(String mdseCd, String invtyLocCd, String stkStsCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", getGlblCmpyCd());
        queryParam.put("mdseCd", mdseCd);
        if (ZYPCommonFunc.hasValue(invtyLocCd)) {
            queryParam.put("invtyLocCd", invtyLocCd);
        }
        queryParam.put("stkStsCd", stkStsCd);
        queryParam.put("avalQty", ONE); // WithoutZero.

        List<String> whSysTpCdList = new ArrayList<String>();
        whSysTpCdList.add(WH_SYS_TP.WMS);
        whSysTpCdList.add(WH_SYS_TP.S21_SYSTEM);
        queryParam.put("whSysTpCd", whSysTpCdList);
        return ssmClient.queryObjectList("getSplyInfoList", queryParam);
    }

    private boolean insertAtpHist(List<ATP_HISTTMsg> atpHistTMsgList) {

        if (!isEmpty(atpHistTMsgList)) {
            for (ATP_HISTTMsg atpHistTMsg : atpHistTMsgList) {

                atpHistTMsg.glblCmpyCd.setValue(getGlblCmpyCd());
                atpHistTMsg.atpHistPk.setValue(getNextAtpHistPk());

                // ***** insert
                S21ApiTBLAccessor.insert(atpHistTMsg);

                String rtnCd = atpHistTMsg.getReturnCode();
                if (!RTNCD_NORMAL.equals(rtnCd)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean insertAtpInfo(List<ATP_INFOTMsg> atpInfoTMsgList) {

        if (!isEmpty(atpInfoTMsgList)) {
            for (ATP_INFOTMsg atpInfoTMsg : atpInfoTMsgList) {

                atpInfoTMsg.glblCmpyCd.setValue(getGlblCmpyCd());
                atpInfoTMsg.atpCalcTs.setValue(this.insVal.runSystemTime);
                atpInfoTMsg.atpInfoPk.setValue(getNextAtpInfoPk());

                // ***** insert
                S21ApiTBLAccessor.insert(atpInfoTMsg);

                String rtnCd = atpInfoTMsg.getReturnCode();
                if (!RTNCD_NORMAL.equals(rtnCd)) {
                    return false;
                }
            }
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    private boolean insertAtpInfoAndHist() {
        final String methodNm = "insertAtpInfoAndHist";
         debugMethodStart(getClass(), methodNm);

        try {

            // --------------------------------------------------
            // grouping by 'SHPG_PLN_NUM'
            // --------------------------------------------------
            final Map<String, List<ATP_INFOTMsg>> atpInfoMap = new HashMap<String, List<ATP_INFOTMsg>>();

            for (ATP_INFOTMsg atpInfoTMsg : this.insVal.atpInfoList) {

                // 'SHPG_PLN_NUM'
                final String shpgPlnNum = atpInfoTMsg.shpgPlnNum.getValue();

                final String groupingKey = shpgPlnNum;

                if (!atpInfoMap.containsKey(groupingKey)) {
                    atpInfoMap.put(groupingKey, new ArrayList<ATP_INFOTMsg>());
                }
                atpInfoMap.get(groupingKey).add(atpInfoTMsg);
            }


            // --------------------------------------------------
            // update [Database]
            // --------------------------------------------------
            for (Entry<String, List<ATP_INFOTMsg>> entry : atpInfoMap.entrySet()) {

                final String shpgPlnNum = entry.getKey();
                final List<ATP_INFOTMsg> atpInfoList = entry.getValue();

                // is changed value?
                final ATP_INFOTMsg atpInfo = atpInfoList.get(0);
                atpInfo.glblCmpyCd.setValue(getGlblCmpyCd());
                final List<ATP_INFOTMsg> atpInfoListDB = ssmClient.queryObjectList("getAtpInfoListByShpgPlnNum", atpInfo);

                final boolean isDiffAtpInfo = isDiffAtpInfo(atpInfoList, atpInfoListDB);

                if (isDiffAtpInfo) {

                    // --------------------------------------------------
                    // delete & insert. [ATP_INFO] & [ATP_HIST]
                    // --------------------------------------------------
                    // ATP_HIST
                    List<ATP_HISTTMsg> atpHistTMsgList = new ArrayList<ATP_HISTTMsg>();
                    for (ATP_INFOTMsg atpInfoDB : atpInfoListDB) {
                        ATP_HISTTMsg atpHistTMsg = new ATP_HISTTMsg();
                        EZDMsg.copy(atpInfoDB, null, atpHistTMsg, null);
                        atpHistTMsgList.add(atpHistTMsg);
                    }

                    boolean isNormalEnd = false;

                    // ***** insert
                    if (insertAtpHist(atpHistTMsgList)) {
                        // ***** remove
                        if (removeAtpInfo(atpInfoListDB)) {
                            // ***** insert
                            if (insertAtpInfo(atpInfoList)) {
                                isNormalEnd = true;
                            }
                        }
                    }

                    // --------------------------------------------------
                    // update 'PSD_DT/PDD_DT' in [SHPG_PLN].
                    // --------------------------------------------------
                    Boolean isVndCusa = isVndCusa(getShpgPln(shpgPlnNum).invtyLocCd.getValue()); // 2013/04/03 Phase 2.0 S21 WDS R-OM005-001 ADD
                    if (isNormalEnd) {
                        if (isVndCusa) {
                            isNormalEnd = updateShpgPlnPsdPdd(shpgPlnNum, getAtpInfoPsdLastDay(atpInfoList));
                        } else {
                            isNormalEnd = updateShpgPlnPsdPdd(shpgPlnNum, getAtpInfoPsdOldestDay(atpInfoList));
                        }
                    }

                    if (!isNormalEnd) {
                        return false;
                    }
                }
            }

            return true;

        } finally {
            debugMethodEnd(getClass(), methodNm);
        }
    }

    private boolean insertAtpInfoDistRemainder() {

        // --------------------------------------------------
        // grouping by 'DIST_PK/DIST_TM_FRAME_NUM'.
        // --------------------------------------------------
        final Map<String, List<ATP_INFOTMsg>> atpInfoMap = new HashMap<String, List<ATP_INFOTMsg>>();

        for (ATP_INFOTMsg atpInfoTMsg : this.insVal.atpInfoListByDistRemainder) {

            // 'DIST_PK/DIST_TM_FRAME_NUM'
            final BigDecimal distPk         = atpInfoTMsg.distPk.getValue();
            final String     distTmFrameNum = atpInfoTMsg.distTmFrameNum.getValue();

            final String groupingKey = String.valueOf(distPk) + "," + distTmFrameNum;

            if (!atpInfoMap.containsKey(groupingKey)) {
                atpInfoMap.put(groupingKey, new ArrayList<ATP_INFOTMsg>());
            }
            atpInfoMap.get(groupingKey).add(atpInfoTMsg);
        }

        // --------------------------------------------------
        // delete & insert
        // --------------------------------------------------
        for (Entry<String, List<ATP_INFOTMsg>> entry : atpInfoMap.entrySet()) {

            final List<ATP_INFOTMsg> atpInfoList = entry.getValue();

            // 'DIST_PK/DIST_TM_FRAME_NUM'
            final ATP_INFOTMsg atpInfoTMsg = atpInfoList.get(0);
            final BigDecimal distPk         = atpInfoTMsg.distPk.getValue();
            final String     distTmFrameNum = atpInfoTMsg.distTmFrameNum.getValue();

            // ***** remove
            final List<ATP_INFOTMsg> atpInfoListDB = getAtpInfoListDistRemainder(distPk, distTmFrameNum);
            if (removeAtpInfo(atpInfoListDB)) {
                // ***** insert
                if (insertAtpInfo(atpInfoList)) {
                    continue;
                }
            }

            // error case.
            return false;
        }

        return true;
    }

    private boolean isDiffAtpInfo(List<ATP_INFOTMsg> atpInfoList1, List<ATP_INFOTMsg> atpInfoList2) {

        if (atpInfoList1.size() != atpInfoList2.size()) {
            return true;
        }

        for (ATP_INFOTMsg atpInfo1 : atpInfoList1) {
            for (ATP_INFOTMsg atpInfo2 : atpInfoList2) {
                for (String fieldNm : atpInfoSelectColumnListArray[0]) {
                    if (!atpInfoKeyColumnListArray[0].contains(fieldNm)) {
                        if (!fieldNm.startsWith("ez")) {
                            if (!"atpCalcTs".equals(fieldNm)) {
                                Object value1 = atpInfo1.getValueData(fieldNm, -1);
                                Object value2 = atpInfo2.getValueData(fieldNm, -1);
                                if (value1 != null && value2 != null) {
                                    if (!value1.equals(value2)) {
//                                        debug(getClass(), "*** isDiffAtpInfo(). isDiff!! fieldNm=[",fieldNm,"], value1=[",value1,"], value2=[",value2,"]");
                                        return true;
                                    }
                                } else {
                                    if (value1 == null && value2 == null) {
                                        continue;
                                    } else {
//                                        debug(getClass(), "*** isDiffAtpInfo(). isDiff!! fieldNm=[",fieldNm,"], value1=[",value1,"], value2=[",value2,"]");
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean isIntangible(String invtyLocCd, String lineSubNum) {

        if (hasValue(invtyLocCd)) {
            return false;
        }

        if ("000".equals(lineSubNum)) {
            return false;
        }

        return true;
    }

    /**
     * Create TBD ATP for distribution & not soft allocated qty.
     */
    private void processDistNotSoftAllocTBD() {

        // --------------------------------------------------
        // create Map<SHPG_PLN_NUM, Set<SOFT_ALLOC_PK>>.
        // --------------------------------------------------
        final Map<String, Set<BigDecimal>> softAllocPkMap = new HashMap<String, Set<BigDecimal>>();

        for (ATP_BAT_WRKTMsg atpBatWrk : this.cache.atpBatWrkList) {

            final String     shpgPlnNum  = atpBatWrk.shpgPlnNum.getValue();
            final BigDecimal softAllocPk = atpBatWrk.softAllocPk.getValue();

            Set<BigDecimal> softAllocPkSet;
            if (softAllocPkMap.containsKey(shpgPlnNum)) {
                softAllocPkSet = softAllocPkMap.get(shpgPlnNum);
            } else {
                softAllocPkSet = new HashSet<BigDecimal>();
                softAllocPkMap.put(shpgPlnNum, softAllocPkSet);
            }

            if (hasValue(softAllocPk)) {
                softAllocPkSet.add(softAllocPk);
            }
        }

        // --------------------------------------------------
        // create TBD [ATP_INFO].
        // --------------------------------------------------
        final List<ATP_INFOTMsg> tbdAtpInfoList = new ArrayList<ATP_INFOTMsg>();

        for (ShpgPlnBean shpgPlnBean : this.cache.shpgPlnBean.values()) {

            final SHPG_PLNTMsg shpgPlnTMsg = shpgPlnBean.toTMsg();

            final String shpgStsCd     = shpgPlnTMsg.shpgStsCd.getValue();
            final String trxSrcTpCd    = shpgPlnTMsg.trxSrcTpCd.getValue();
            final String trxHdrNum     = shpgPlnTMsg.trxHdrNum.getValue();
            final String trxLineNum    = shpgPlnTMsg.trxLineNum.getValue();
            final String trxLineSubNum = shpgPlnTMsg.trxLineSubNum.getValue();
            final String invtyLocCd    = shpgPlnTMsg.invtyLocCd.getValue();
            final String shpgPlnNum    = shpgPlnTMsg.shpgPlnNum.getValue();

            if (!SHPG_STS.VALIDATED.equals(shpgStsCd)) {
                continue;
            }

            // get 'ORD_QTY'
            int ordQty;
            String distItemFlg;
            if (TRX_SRC_TP.WHOLE_SALES.equals(trxSrcTpCd)) {
                CPO_DTLTMsg cpoDtlTMsg = getCpoDtl(trxHdrNum, trxLineNum, trxLineSubNum);
                if (cpoDtlTMsg != null) {
                    ordQty      = cpoDtlTMsg.ordQty.getValueInt();
                    distItemFlg = "";
                } else {
                    continue;
                }
//            } else if (TRX_SRC_TP.RETAIL.equals(trxSrcTpCd)) {
//                ROSS_ORDTMsg rossOrdTMsg = getRossOrd(trxHdrNum, trxLineNum, trxLineSubNum);
//                if (rossOrdTMsg != null) {
//                    ordQty      = rossOrdTMsg.ordQty.getValueInt();
//                    distItemFlg = rossOrdTMsg.distItemFlg.getValue();
//                } else {
//                    continue;
//                }
            } else {
                continue;
            }

            if (!Y.equals(distItemFlg)) {
                continue;
            }

            // If target item is INTANGEBLE, Ignore..
            if (isIntangible(invtyLocCd, trxLineSubNum)) {
                continue;
            }

            // get 'INVTY_LOC_TP_CD'
            INVTY_LOC_VTMsg invtyLocVTMsg = getInvtyLocV(invtyLocCd);
            if (invtyLocVTMsg == null) {
                continue;
            }
            if (!LOC_TP.WAREHOUSE.equals(invtyLocVTMsg.invtyLocTpCd.getValue())) {
                continue;
            }

            // --------------------------------------------------
            // create TBD [ATP_INFO].
            // --------------------------------------------------
            // make s/a list for one shipping plan.
            final Set<BigDecimal> softAllocPkSet = softAllocPkMap.get(shpgPlnNum);

            // calc total s/a qty.
            if (isEmpty(softAllocPkSet)) {
                // mdse is dist and softallocpk is zero, all qty TBD.
                List<ATP_INFOTMsg> atpInfoTMsgList = createAtpInfoTMsgForTBD(shpgPlnTMsg, shpgPlnTMsg.mdseCd.getValue(), ordQty, ordQty);
                tbdAtpInfoList.addAll(atpInfoTMsgList);
//                    return retList;
                continue;
            }

            int softAllocQtyTotal = 0;
            for (BigDecimal softAllocPk : softAllocPkSet) {
                SOFT_ALLOCTMsg softAllocTMsg = getSoftAlloc(softAllocPk);
                if (softAllocTMsg == null) {
                    continue;
                }
                if (hasValue(softAllocTMsg.softAllocQty)) {
                    softAllocQtyTotal += softAllocTMsg.softAllocQty.getValueInt();
                }
            }

            // If there are reminder qty, make tbd record.
            int shpgPlnOrdQty = shpgPlnTMsg.ordQty.getValueInt();
            int last = shpgPlnOrdQty - softAllocQtyTotal;
            if (last > 0) {
                List<ATP_INFOTMsg> atpInfoTMsgList = createAtpInfoTMsgForTBD(shpgPlnTMsg, shpgPlnTMsg.mdseCd.getValue(), ordQty, last);
                tbdAtpInfoList.addAll(atpInfoTMsgList);
            }
        }

        this.insVal.atpInfoList.addAll(tbdAtpInfoList);
    }

    @SuppressWarnings("unchecked")
    private boolean remove(final List tMsgList) {

        if (isEmpty(tMsgList)) {
//            debug(getClass(), "tMsgList.size() == 0.");

        } else {
            final EZDTMsg[] tMsgArray = (EZDTMsg[]) tMsgList.toArray(new EZDTMsg[0]);
            // ***** remove
            final int resRecordCnt = S21FastTBLAccessor.removePhysical(tMsgArray);
//            debug(getClass(), "S21FastTBLAccessor.removePhysical(", tMsgArray[0].getTableName(), "). Result record count = [", resRecordCnt, "].");
            if (tMsgArray.length != resRecordCnt) {
//                debug(getClass(), "Error. S21FastTBLAccessor.removePhysical(" + tMsgArray[0].getTableName() + "). EZDTMsg[].length = [" + tMsgArray.length + "], but result record count = [" + resRecordCnt + "].");
                return false;
            }
        }

        return true;
    }

    private boolean removeAtpInfo(ATP_INFOTMsg atpInfoTMsg) {
        return removeAtpInfo(new ArrayList<ATP_INFOTMsg>(asList(atpInfoTMsg)));
    }

    private boolean removeAtpInfo(List<ATP_INFOTMsg> atpInfoTMsgList) {

        for (ATP_INFOTMsg atpInfoTMsg : atpInfoTMsgList) {
            if (!hasValue(atpInfoTMsg.glblCmpyCd)) {
                atpInfoTMsg.glblCmpyCd.setValue(getGlblCmpyCd());
            }
        }

        // ***** remove
        return remove(atpInfoTMsgList);
    }

    private void removeZeroAllocQtyAtpInfo(List<ATP_INFOTMsg> atpInfoTMsgList) {

        Iterator<ATP_INFOTMsg> it = atpInfoTMsgList.iterator();
        while (it.hasNext()) {
            ATP_INFOTMsg atpInfoTMsg = it.next();
            if (atpInfoTMsg.allocQty.getValueInt() == 0) {
                it.remove();
            }
        }
    }

    /**
     * Sort atp for set oldest.
     * @param atpInfoTMsgList
     * @return
     */
    private List<ATP_INFOTMsg> sortAtpForSetOldest(List<ATP_INFOTMsg> atpInfoTMsgList) {

        // Sort ATP_INFO by ETA. order is 1) TBD, 2) valid oldest, 3) Blank(H/Auj.
        if (isEmpty(atpInfoTMsgList)) {
            return atpInfoTMsgList;
        }

        ATP_INFOTMsg[] atpInfoTMsgArray = atpInfoTMsgList.toArray(new ATP_INFOTMsg[0]);
        Arrays.sort(atpInfoTMsgArray, new Comparator<ATP_INFOTMsg>() {
            public int compare(ATP_INFOTMsg atp1, ATP_INFOTMsg atp2) {
                if (!hasValue(atp1.whEtaDt)) {
                    return 1;
                }
                if (!hasValue(atp2.whEtaDt)) {
                    return -1;
                }
                return (atp2.whEtaDt.getValue().compareTo(atp1.whEtaDt.getValue()));
            }
        });

        return new ArrayList<ATP_INFOTMsg>(asList(atpInfoTMsgArray));
    }

    private void updateAtpInfo(ATP_INFOTMsg reqAtpInfoTMsg) {

        ATP_INFOTMsg atpInfoTMsg = new ATP_INFOTMsg();
        atpInfoTMsg.glblCmpyCd.setValue(getGlblCmpyCd());
        atpInfoTMsg.atpInfoPk.setValue(reqAtpInfoTMsg.atpInfoPk.getValue());
        // ***** findByKeyForUpdate
        atpInfoTMsg = (ATP_INFOTMsg) findByKeyForUpdateAPI(atpInfoTMsg);
        if (atpInfoTMsg != null) {
            atpInfoTMsg.allocQty.setValue(reqAtpInfoTMsg.allocQty.getValue());
            // ***** update
            S21ApiTBLAccessor.update(atpInfoTMsg);
            if (!RTNCD_NORMAL.equals(atpInfoTMsg.getReturnCode())) {
                addMsgId(NWZM0742E);
            }
        } else {
            addMsgId(NWZM0742E);
        }
    }

    private boolean updateShpgPlnPsdPdd(String shpgPlnNum, ATP_INFOTMsg atpInfoTMsg) {

        boolean isNormalEnd = true;

        if (atpInfoTMsg == null) {
            return isNormalEnd;
        }

        SHPG_PLNTMsg shpgPlnTMsgCache = getShpgPln(shpgPlnNum);

        if (shpgPlnTMsgCache != null) {

            String rteStsCd = shpgPlnTMsgCache.rteStsCd.getValue();
            String oldPsdDt = shpgPlnTMsgCache.psdDt.getValue();
            String oldPddDt = shpgPlnTMsgCache.pddDt.getValue();
            String newPsdDt = "";
            String newPddDt = "";

            //if routing status is ROUTED, not update shipping plan.

            if (!RTE_STS.ROUTED.equals(rteStsCd)) {
                // NEW PSD_DT
                newPsdDt = atpInfoTMsg.psdDt.getValue();
                // NEW PDD_DT
                newPddDt = atpInfoTMsg.pddDt.getValue();

                // OLD PSD_DT
                if (!hasValue(oldPsdDt)) {
                    oldPsdDt = "";
                }
                // OLD PDD_DT
                if (!hasValue(oldPddDt)) {
                    oldPddDt = "";
                }

                //if old Psd and new Psd are same, and old Pdd and new Pdd are same , not update shipping plan.
                if (oldPsdDt.equals(newPsdDt) && oldPddDt.equals(newPddDt)) {
                    //debug(getClass(), " ### update !!!!!!!!!!!!old Psd and new Psd are same, and old Pdd and new Pdd are same!!!!!!!!!!!!!!");
                    return isNormalEnd;

                } else {

                    // ***** findByKeyForUpdate
                    SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                    shpgPlnTMsg.glblCmpyCd.setValue(getGlblCmpyCd());
                    shpgPlnTMsg.shpgPlnNum.setValue(shpgPlnNum);
                    shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg);
                    if (shpgPlnTMsg == null) {
                        return true;
                    }
                    rteStsCd = shpgPlnTMsg.rteStsCd.getValue();

                    if (!RTE_STS.ROUTED.equals(rteStsCd)) {

                        if (LABEL_TBD.equals(newPsdDt)) {
                            shpgPlnTMsg.psdDt.clear();
                        } else {
                            shpgPlnTMsg.psdDt.setValue(newPsdDt);
                        }

                        // PDD_DT
                        if (LABEL_TBD.equals(newPddDt)) {
                            shpgPlnTMsg.pddDt.clear();
                        } else {
                            shpgPlnTMsg.pddDt.setValue(newPddDt);
                        }

                        //***** update
                        S21ApiTBLAccessor.update(shpgPlnTMsg);

                        final String rtnCd = shpgPlnTMsg.getReturnCode();
                        isNormalEnd = RTNCD_NORMAL.equals(rtnCd);

                        debug(getClass(), " ### update 'SHPG_PLN'. SHPG_PLN_NUM=[", shpgPlnNum, "]. PSD_DT:[", oldPsdDt, "] => [", shpgPlnTMsg.psdDt.getValue(), "], PDD_DT:[", oldPddDt, "] => [", shpgPlnTMsg.pddDt.getValue(), "]. ReturnCode=[", rtnCd, "].");
                    }
                }
            }
        }

        if (!isNormalEnd) {
            addMsgId(NWZM0613E);
        }

        return isNormalEnd;
    }

    private boolean validate(NWZC108001PMsg pMsg) {

        // GLBL_CMPY_CD
        if (!hasValue(pMsg.glblCmpyCd)) {
            addMsgId(NWZM0188E);
            return false;
        }

        // PROC_DT
        if (!hasValue(pMsg.procDt)) {
            addMsgId(NWZM0301E);
            return false;
        }

        return true;
    }

    private static String createCacheKey(String... strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str).append(",");
        }
        return sb.toString();
    }

    private static void debug(Class caller, final Object... debugInfos) {
//        if(EZDDebugOutput.isDebug()){
            final StringBuilder sb = new StringBuilder(128);
            sb.append("<<").append(caller.getName()).append(">> ");
            for (final Object debugInfo : debugInfos) {
                sb.append(debugInfo);
            }
//            EZDDebugOutput.println(1, sb.toString(), null);
            S21InfoLogOutput.println(sb.toString());
//        }
    }

    private static void debugMethodEnd(Class caller, final String methodNm) {
        debug(caller, new StringBuilder().append("[E N D]").append("******************** method : [").append(methodNm).append("]").toString());
    }

    private static void debugMethodStart(Class caller, final String methodNm) {
        debug(caller, new StringBuilder().append("[START]").append("******************** method : [").append(methodNm).append("]").toString());
    }

    private static BigDecimal getNextAtpHistPk() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.ATP_HIST_SQ);
    }

    private static BigDecimal getNextAtpInfoPk() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.ATP_INFO_SQ);
    }

    private static boolean isEmpty(Collection< ? > collection) {
        return collection == null || collection.isEmpty();
    }

    private static String substring(String str, int endIndex) {
        String retStr = str;
        if (retStr != null) {
            if (retStr.length() > endIndex) {
                retStr = retStr.substring(0, endIndex);
            }
        }
        return retStr;
    }

    /** AtpCalculator */
    private class AtpCalculator extends S21SsmBooleanResultSetHandlerSupport implements SHPG_STS {

        private static final int ALLOC_TP__NONE        = 0;
        private static final int ALLOC_TP__DIST        = 1;
        private static final int ALLOC_TP__HARD_AUTO   = 2; // Hard Allocation : Auto
        private static final int ALLOC_TP__HARD_MANUAL = 3; // Hard Allocation : Manual
        private static final int ALLOC_TP__SOFT_MANUAL = 4; // Soft Allocation : Manual
        private static final int SHOW_ETA_FLG_N        = 5; // SHOW_ETA_FLG =N

        /** CPO_DTL.DIST_ITEM_FLG */
        private String cpoDtlDistItemFlg;
        /** CPO_DTL.EXPD_SHIP_DT */
        private String cpoDtlExpdShipDt;

        /** ROSS_ORD.DIST_ITEM_FLG */
        private String rossOrdDistItemFlg;
        /** ROSS_ORD.EXPD_SHIP_DT */
        private String rossOrdExpdShipDt;

        /** SOFT_ALLOC.SOFT_ALLOC_QTY */
        private int softAllocQty;
        /** SOFT_ALLOC.DIST_TM_FRAME_NUM */
        private String distTmFrameNum;
        /** SOFT_ALLOC.DIST_PLN_NUM */
        private String distPlnNum;

        private boolean isHardAllocNotPermit = false;
        private boolean isShippingNotPermit = false;

        private int allocationTp = ALLOC_TP__HARD_AUTO;

        private final List[] atpBatWrkColumnListArray;

        private AtpCalculator() {
            this.atpBatWrkColumnListArray = new ATP_BAT_WRKTMsg().getSelectColumnList();
        }

        @Override
        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            final String methodNm = "doProcessQueryResult";
            debugMethodStart(getClass(), methodNm);

            try {

                // records.
                rs.last();
                int records = rs.getRow();
                rs.first();
                debug(getClass(), "records = ", records);

                if (records > 0) {

                    do {

                        // [ATP_BAT_WRK]
                        final ATP_BAT_WRKTMsg atpBatWrkTMsg = toAtpBatWrkTMsg(rs);

                        if (!validate(atpBatWrkTMsg)) {
                            return false;
                        } else {
                            cache.atpBatWrkList.add(atpBatWrkTMsg);
                        }

                        // Check Calculation Distribution Remainder or Calculation ATP
                        if (isOnlyCalcDistRemainder(atpBatWrkTMsg)) {
                            final List<ATP_INFOTMsg> atpInfoList = calcDistRemainder(atpBatWrkTMsg.distPk.getValue(), atpBatWrkTMsg.distTmFrameNum.getValue());
                            insVal.atpInfoListByDistRemainder.addAll(atpInfoList);
                        } else {
                            final List<ATP_INFOTMsg> atpInfoList = calcATP(atpBatWrkTMsg);
                            if (hasError()) {
                                return false;
                            }
                            insVal.atpInfoList.addAll(atpInfoList);
                        }

                    } while (rs.next());
                }

                return true;

            } finally {
                debugMethodEnd(getClass(), methodNm);
            }
        }

        private List<ATP_INFOTMsg> calcATP(ATP_BAT_WRKTMsg atpBatWrkTMsg) {

            // select and keep in memory shipping plan info.
            SHPG_PLNTMsg shpgPlnTMsg = getShpgPln(atpBatWrkTMsg.shpgPlnNum.getValue());
            if (shpgPlnTMsg == null) {
                return emptyList();
            }

            final String trxSrcTpCd = shpgPlnTMsg.trxSrcTpCd.getValue();
            if (!TRX_SRC_TP.WHOLE_SALES.equals(trxSrcTpCd) && !TRX_SRC_TP.RETAIL.equals(trxSrcTpCd)) {
                return emptyList();
            }

            // If target item is INTANGEBLE, Ignore..
            if (isIntangible(shpgPlnTMsg.invtyLocCd.getValue(), shpgPlnTMsg.trxLineSubNum.getValue())) {
                return emptyList();
            }
            // Check SVC_MACH_MSTR.
            CPO_DTLTMsg dsCpoDtlTMsg = getCpoDtlInfoByShpgPlnInfo(shpgPlnTMsg);
            String serNum = null;
            if (dsCpoDtlTMsg != null) {
                serNum = dsCpoDtlTMsg.serNum.getValue();
            } else {
                return emptyList();
            }

            // Check SVC_MACH_MSTR.
            boolean isSvcMachMstrErr = false;
            if (ZYPCommonFunc.hasValue(serNum)) {
                Map<String, Object> svcMachMstrInfo;
                svcMachMstrInfo = getSvcMachMstr(shpgPlnTMsg.glblCmpyCd.getValue(), serNum, shpgPlnTMsg.mdseCd.getValue(), shpgPlnTMsg.invtyLocCd.getValue());
                if (svcMachMstrInfo != null) {
                    if (checkSerNumForOrderDetail(svcMachMstrInfo, shpgPlnTMsg)) {
                        isSvcMachMstrErr = true;
                    }
                } else {
                    isSvcMachMstrErr = true;
                }

            }

            if (ONBATCH_TYPE.ONLINE.equals(onBatchType)) {

                // reset distributin reminder for distribution type(available softallocpk & online mode).
                if (hasValue(atpBatWrkTMsg.softAllocPk)) {
                    processResetDistributinReminder(atpBatWrkTMsg);

                // calucurate supply info for not distribution type(unavailable softallocpk & online mode).
                } else {
                    calcSplyInfoAvalQty(atpBatWrkTMsg);
                }
            }

            // --------------------------------------------------
            // ETA calculation
            // --------------------------------------------------
            final List<ATP_INFOTMsg> atpInfoList = calcETA(atpBatWrkTMsg, isSvcMachMstrErr);

            // --------------------------------------------------
            // PSD/PDD calculation
            // --------------------------------------------------
            if (!isEmpty(atpInfoList)) {
                calcPsdPdd(atpBatWrkTMsg, atpInfoList, isSvcMachMstrErr);
            }

            return atpInfoList;
        }

        private boolean checkSerNumForOrderDetail(Map<String, Object> svcMachMstrInfo, SHPG_PLNTMsg shpgPlnTMsg) {
            String svcMachUsgStsCd = (String) svcMachMstrInfo.get("SVC_MACH_USG_STS_CD");
            String svcMachMstrStsCd = (String) svcMachMstrInfo.get("SVC_MACH_MSTR_STS_CD");
            if ((SVC_MACH_USG_STS.IN_INVENTORY.equals(svcMachUsgStsCd) && SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd))
                    || (SVC_MACH_USG_STS.RETURNED.equals(svcMachUsgStsCd) && SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd))) {
                if (ZYPConstant.FLG_ON_Y.equals((String) svcMachMstrInfo.get("ALLC_FLG"))) {
                    return false;
                }

                if (shpgPlnTMsg.trxLineNum.getValue().equals((String) svcMachMstrInfo.get("CPO_DTL_LINE_NUM"))
                        && shpgPlnTMsg.trxLineSubNum.getValue().equals((String) svcMachMstrInfo.get("CPO_DTL_LINE_SUB_NUM"))
                        && shpgPlnTMsg.trxHdrNum.getValue().equals((String) svcMachMstrInfo.get("CPO_ORD_NUM"))) {
                    return false;
                }
            }
            return true;
        }

        /**
         * Calclate the new remainder of the distribution.
         */
        private List<ATP_INFOTMsg> calcDistRemainder(BigDecimal distPk, String distTmFrameNum) {

            // --------------------------------------------------
            // [DIST_PLN_DTL]
            // --------------------------------------------------
            DIST_PLN_DTLTMsg distPlnDtlTMsg = getDistPlnDtl(distPk, distTmFrameNum);
            if (distPlnDtlTMsg == null) {
                return emptyList();
            }

            // --------------------------------------------------
            // [MDSE]
            // --------------------------------------------------
            String mdseCd;
            if (hasValue(distPlnDtlTMsg.mdseCd)) {
                mdseCd = distPlnDtlTMsg.mdseCd.getValue();
            } else {
                mdseCd = distPlnDtlTMsg.ordTakeMdseCd.getValue();
            }

            MDSETMsg mdseTMsg = getMdse(mdseCd);
            if (mdseTMsg == null) {
                return emptyList();
            }

            final List<DistributionRemainderBean> distBeanList = new ArrayList<DistributionRemainderBean>();

            // MDSE_TP = 'SET'
            if (MDSE_TP.SET.equals(mdseTMsg.mdseTpCd.getValue())) {
                List<CMPSNTMsg> cmpsnTMsgList = getCmpsnList(mdseCd);
                if (isEmpty(cmpsnTMsgList)) {
                    return emptyList();
                }
                for (CMPSNTMsg cmpsnTMsg : cmpsnTMsgList) {
                    final DistributionRemainderBean distBean = new DistributionRemainderBean();
                    distBean.setMdseCd(cmpsnTMsg.childMdseCd.getValue());
                    distBean.setOrdTakeMdseCd(cmpsnTMsg.childOrdTakeMdseCd.getValue());
                    distBean.setInvtyLocCd(distPlnDtlTMsg.invtyLocCd.getValue());
                    distBean.setChildMdseQty(cmpsnTMsg.childMdseQty.getValue());
                    distBeanList.add(distBean);
                }
            // MDSE_TP != 'SET'
            } else {
                final DistributionRemainderBean distBean = new DistributionRemainderBean();
                if (mdseTMsg.mdseCd.getValue().equals(distPlnDtlTMsg.mdseCd.getValue())) {
                    distBean.setMdseCd(mdseCd);
                } else {
                    distBean.setOrdTakeMdseCd(distPlnDtlTMsg.ordTakeMdseCd.getValue());
                }
                distBean.setInvtyLocCd(distPlnDtlTMsg.invtyLocCd.getValue());
                distBean.setChildMdseQty(ONE);
                distBeanList.add(distBean);
            }

            // --------------------------------------------------
            // create [ATP_INFO] by distribution remainder.
            // --------------------------------------------------
            final List<ATP_INFOTMsg> retList = new ArrayList<ATP_INFOTMsg>();
            for (DistributionRemainderBean distBean : distBeanList) {
                retList.addAll(createAtpInfoTMsgList(distPlnDtlTMsg, distBean));
            }
            return retList;
        }

        private List<ATP_INFOTMsg> calcETA(ATP_BAT_WRKTMsg atpBatWrkTMsg, boolean isSvcMachMstrErr) {

            // [SHPG_PLN]
            SHPG_PLNTMsg shpgPlnTMsg = getShpgPln(atpBatWrkTMsg.shpgPlnNum.getValue());
            if (shpgPlnTMsg == null) {
                return emptyList();
            }

            String trxSrcTpCd    = shpgPlnTMsg.trxSrcTpCd.getValue();
            String trxHdrNum     = shpgPlnTMsg.trxHdrNum.getValue();
            String trxLineNum    = shpgPlnTMsg.trxLineNum.getValue();
            String trxLineSubNum = shpgPlnTMsg.trxLineSubNum.getValue();
            String shpgStsCd     = shpgPlnTMsg.shpgStsCd.getValue();
            String invtyLocCd    = shpgPlnTMsg.invtyLocCd.getValue();
            String stkStsCd      = shpgPlnTMsg.stkStsCd.getValue();
            String mdseCd        = shpgPlnTMsg.mdseCd.getValue();

            // Get order info and order qty
            int ordQty;
            String distItemFlg;

            // TRX_SRC_TP = 'WHOLE_SALES'
            if (TRX_SRC_TP.WHOLE_SALES.equals(trxSrcTpCd)) {
                CPO_DTLTMsg cpoDtlTMsg = getCpoDtl(trxHdrNum, trxLineNum, trxLineSubNum);
                if (cpoDtlTMsg == null) {
                    return emptyList();
                }
                ordQty      = cpoDtlTMsg.ordQty.getValueInt();
                distItemFlg = "";

                this.cpoDtlDistItemFlg = distItemFlg;
                this.cpoDtlExpdShipDt  = cpoDtlTMsg.expdShipDt.getValue();

//            // TRX_SRC_TP = 'RETAIL'
//            } else if (TRX_SRC_TP.RETAIL.equals(trxSrcTpCd)) {
//                ROSS_ORDTMsg rossOrdTMsg = getRossOrd(trxHdrNum, trxLineNum, trxLineSubNum);
//                if (rossOrdTMsg == null) {
//                    return emptyList();
//                }
//                ordQty      = rossOrdTMsg.ordQty.getValueInt();
//                distItemFlg = rossOrdTMsg.distItemFlg.getValue();
//
//                this.rossOrdDistItemFlg = distItemFlg;
//                this.rossOrdExpdShipDt  = rossOrdTMsg.expdShipDt.getValue();

            } else {
                return emptyList();
            }

            // Get soft allocation info.
            int softAllocQty = 0;
            BigDecimal distPk = null;
            String distTmFrameNum = "";
            if (hasValue(atpBatWrkTMsg.softAllocPk)) {
                SOFT_ALLOCTMsg softAllocTMsg = getSoftAlloc(atpBatWrkTMsg.softAllocPk.getValue());
                if (softAllocTMsg == null) {
                    return emptyList();
                }
                softAllocQty   = softAllocTMsg.softAllocQty.getValueInt();
                distPk         = softAllocTMsg.distPk.getValue();
                distTmFrameNum = softAllocTMsg.distTmFrameNum.getValue();

                this.softAllocQty   = softAllocQty;
                this.distPlnNum     = softAllocTMsg.distPlnNum.getValue();
                this.distTmFrameNum = distTmFrameNum;
            }

            // Get Inventory location type code
            INVTY_LOC_VTMsg invtyLocVTMsg = getInvtyLocV(invtyLocCd);
            if (invtyLocVTMsg == null) {
                return emptyList();
            }
            String invtyLocTpCd = invtyLocVTMsg.invtyLocTpCd.getValue();

            // Get Hard Allocation
            getExistHold(shpgPlnTMsg);

            List<ATP_INFOTMsg> retList = null;

            if (isSvcMachMstrErr) {
                retList = createAtpInfoTMsgForTBD(shpgPlnTMsg, mdseCd, ordQty);
                return retList;
            }

            // LOC_TP = 'VENDOR'
            if (LOC_TP.VENDOR.equals(invtyLocTpCd)) {

                boolean isVndCusa = isVndCusa(invtyLocCd);

                // Warehouse Vendor,ShippingStatus Validated -> TBD
                if (VALIDATED.equals(shpgStsCd)) {
                    retList = createAtpInfoTMsgForTBD(shpgPlnTMsg, mdseCd, ordQty);

                // Warehouse Vendor,ShippingStatus P/O Printed -> VendorDrop
                } else if (P_OR_O_PRINTED.equals(shpgStsCd) && !isVndCusa) {
                    retList = createAtpInfoTMsgForVendorDrop(shpgPlnTMsg, mdseCd, ordQty);
                } else if (P_OR_O_PRINTED.equals(shpgStsCd) && isVndCusa) {
                    retList = createAtpInfoTMsgForCusaVendorDrop(shpgPlnTMsg, mdseCd, ordQty);
                }
                // 2013/04/03 Phase 2.0 S21 WDS R-OM005-001 ADD End

            // LOC_TP != 'VENDOR'
            } else {

                // ETA requires MDSE.SHOW_ETA_FLG and allocationTp Check. 
                int allocationTpShowETA = getAllocationTpShowETA(distItemFlg, mdseCd, invtyLocCd, stkStsCd);

                // [calcPsdPdd] Method requires [this.allocationTp]... Therefore do not erase this line. 
                this.allocationTp = getAllocationTp(distItemFlg, mdseCd, invtyLocCd, stkStsCd);
                // --------------------------------------------------
                // 'VALIDATED'
                // --------------------------------------------------
                if (VALIDATED.equals(shpgStsCd)) {

                    switch (allocationTpShowETA) {

                        case ALLOC_TP__DIST:
                            if (softAllocQty > 0) {
                                if (this.isHardAllocNotPermit) {
                                    retList = createAtpInfoTMsgForTBD(shpgPlnTMsg, mdseCd, ordQty, softAllocQty);
                                } else {
                                    retList = createAtpInfoTMsgForETACalc(shpgPlnTMsg, mdseCd, ordQty, softAllocQty, distPk, distTmFrameNum);
                                }
                            }
                            break;

                        case ALLOC_TP__HARD_AUTO:
                            if (this.isHardAllocNotPermit) {
                                retList = createAtpInfoTMsgForTBD(shpgPlnTMsg, mdseCd, ordQty);
                            } else {
                                retList = createAtpInfoTMsgForETACalc(shpgPlnTMsg, mdseCd, ordQty, softAllocQty, distPk, distTmFrameNum);
                            }
                            break;

                        case SHOW_ETA_FLG_N:
                        case ALLOC_TP__SOFT_MANUAL:
                        case ALLOC_TP__HARD_MANUAL:
                            retList = createAtpInfoTMsgForTBD(shpgPlnTMsg, mdseCd, ordQty);
                            break;

                        default:
                            break;
                    }

                // --------------------------------------------------
                // 'HARD_ALLOCATED', 'S_OR_O_PRINTED'
                // --------------------------------------------------
                } else if (HARD_ALLOCATED.equals(shpgStsCd) || S_OR_O_PRINTED.equals(shpgStsCd)) {

                    List<ATP_INFOTMsg> atpListSame = getAtpInfoList(insVal.atpInfoList, atpBatWrkTMsg.shpgPlnNum.getValue());
                    if (!isEmpty(atpListSame)) {
                        return emptyList();
                    }
                    retList = createAtpInfoTMsgForBlank(shpgPlnTMsg, mdseCd, ordQty);
                }
            }

            if (retList == null) {
                retList = emptyList();
            }
            return retList;
        }

        private void calcPsdPdd(ATP_BAT_WRKTMsg atpBatWrkTMsg, List<ATP_INFOTMsg> atpInfoTMsgList, boolean isSvcMachMstrErr) {

            if (isEmpty(atpInfoTMsgList)) {
                return;
            }

            // [SHPG_PLN]
            SHPG_PLNTMsg shpgPlnTMsg = getShpgPln(atpBatWrkTMsg.shpgPlnNum.getValue());
            if (shpgPlnTMsg == null) {
                return;
            }

            String trxSrcTpCd    = shpgPlnTMsg.trxSrcTpCd.getValue();
            String trxHdrNum     = shpgPlnTMsg.trxHdrNum.getValue();
            String trxLineNum    = shpgPlnTMsg.trxLineNum.getValue();
            String trxLineSubNum = shpgPlnTMsg.trxLineSubNum.getValue();
            String shpgStsCd     = shpgPlnTMsg.shpgStsCd.getValue();
            String rteStsCd      = shpgPlnTMsg.rteStsCd.getValue();
            String invtyLocCd    = shpgPlnTMsg.invtyLocCd.getValue();

            // Get Inventory location type code
            INVTY_LOC_VTMsg invtyLocVTMsg = getInvtyLocV(invtyLocCd);
            if (invtyLocVTMsg == null) {
                return;
            }
            String invtyLocTpCd = invtyLocVTMsg.invtyLocTpCd.getValue();

            // Get ESD
            String esd;
            if (TRX_SRC_TP.WHOLE_SALES.equals(trxSrcTpCd)) {
                if (!hasValue(this.cpoDtlExpdShipDt) || !hasValue(this.cpoDtlDistItemFlg)) {
                    CPO_DTLTMsg cpoDtlTMsg = getCpoDtl(trxHdrNum, trxLineNum, trxLineSubNum);
                    if (cpoDtlTMsg != null) {
                        esd = cpoDtlTMsg.expdShipDt.getValue();
                    } else {
                        return;
                    }
                } else {
                    esd = this.cpoDtlExpdShipDt;
                }

//            } else if (TRX_SRC_TP.RETAIL.equals(trxSrcTpCd)) {
//                if (!hasValue(this.rossOrdExpdShipDt) || !hasValue(this.rossOrdDistItemFlg)) {
//                    ROSS_ORDTMsg rossOrdTMsg = getRossOrd(trxHdrNum, trxLineNum, trxLineSubNum);
//                    if (rossOrdTMsg != null) {
//                        esd = rossOrdTMsg.expdShipDt.getValue();
//                    } else {
//                        return;
//                    }
//                } else {
//                    esd = this.rossOrdExpdShipDt;
//                }

            } else {
                return;
            }

            // Get soft allocation info.
            int softAllocQty = 0;
            String distTmFrameNum       = "";
            String distPlnNum           = "";
            String distTmFrameStartDate = "";

            if (hasValue(atpBatWrkTMsg.softAllocPk)) {

                if (!hasValue(this.distTmFrameNum) || !hasValue(this.distPlnNum)) {
                    SOFT_ALLOCTMsg softAllocTMsg = getSoftAlloc(atpBatWrkTMsg.softAllocPk.getValue());
                    if (softAllocTMsg == null) {
                        return;
                    }
                    softAllocQty   = softAllocTMsg.softAllocQty.getValueInt();
                    distTmFrameNum = softAllocTMsg.distTmFrameNum.getValue();
                    distPlnNum     = softAllocTMsg.distPlnNum.getValue();
                } else {
                    softAllocQty   = this.softAllocQty;
                    distTmFrameNum = this.distTmFrameNum;
                    distPlnNum     = this.distPlnNum;
                }

                DIST_PLN_TM_FRAMETMsg distPlnTmFrameTMsg = getDistPlnTmFrame(distPlnNum, distTmFrameNum);
                if (distPlnTmFrameTMsg == null) {
                    return;
                } else {
                    distTmFrameStartDate = distPlnTmFrameTMsg.tmFrameFromDt.getValue();
                }
            }

            // Calculation for all created atp.
            for (ATP_INFOTMsg atpInfoTMsg : atpInfoTMsgList) {

                if (hasValue(atpInfoTMsg.psdDt)) {
                    // already calculated.
                    continue;
                }

                if (isSvcMachMstrErr) {
                    atpInfoTMsg.psdDt.setValue(LABEL_TBD);
                    atpInfoTMsg.pddDt.setValue(LABEL_TBD);
                    continue;
                }

                final List<String> dateList = new ArrayList<String>();

                if (LOC_TP.VENDOR.equals(invtyLocTpCd)) {

                    boolean isVndCusa = isVndCusa(invtyLocCd);
                    if (P_OR_O_PRINTED.equals(shpgStsCd) && !isVndCusa) {

                        List<PO_DTLTMsg> poDtlTMsgList = getPoDtlList(shpgPlnTMsg.shpgPlnNum.getValue());
                        if (isEmpty(poDtlTMsgList)) {
                            continue;
                        }

                        // PDD is not need ajust!! -> Set TBD!
                        PO_DTLTMsg poDtlTMsg = poDtlTMsgList.get(0);
                        atpInfoTMsg.psdDt.setValue(poDtlTMsg.etaDt.getValue());
                        atpInfoTMsg.pddDt.setValue(LABEL_TBD);
                    }

                } else {
                    final boolean isConfigurationItem = isConfigurationItem(shpgPlnTMsg);
                    BigDecimal configLtDayNum = BigDecimal.ZERO;
                    if (isConfigurationItem) {
                        configLtDayNum = getConfigLtDay(shpgPlnTMsg.mdseCd.getValue());
                        if (null == configLtDayNum) {
                            atpInfoTMsgList.clear();
                            addMsgId(NWZM1246E);
                            return;
                        }
                    }

                    if (VALIDATED.equals(shpgStsCd)) {

                        switch (this.allocationTp) {

                            case ALLOC_TP__DIST:
                                if (softAllocQty > 0) {
                                    if (this.isShippingNotPermit) {
                                        atpInfoTMsg.psdDt.setValue(LABEL_TBD);
                                        atpInfoTMsg.pddDt.setValue(LABEL_TBD);

                                    } else {
                                        String etaDt = atpInfoTMsg.whEtaDt.getValue();
                                        etaDt = getNearestBizDate(atpInfoTMsg.invtyLocCd.getValue(), etaDt, configLtDayNum);
                                        dateList.add(etaDt);
                                        dateList.add(esd);
                                        dateList.add(distTmFrameStartDate);

                                        PsdPdd psdpdd = callLeadTimeCalculationAPI(shpgPlnTMsg, atpInfoTMsg, getBiggestDate(dateList));
                                        if (psdpdd == null) {
                                            continue;
                                        }
                                        atpInfoTMsg.psdDt.setValue(psdpdd.psd);
                                        atpInfoTMsg.pddDt.setValue(psdpdd.pdd);
                                    }
                                }
                                break;

                            case ALLOC_TP__HARD_AUTO:
                                if (this.isShippingNotPermit) {
                                    atpInfoTMsg.psdDt.setValue(LABEL_TBD);
                                    atpInfoTMsg.pddDt.setValue(LABEL_TBD);

                                } else {
                                    String etaDt = atpInfoTMsg.whEtaDt.getValue();
                                    etaDt = getNearestBizDate(atpInfoTMsg.invtyLocCd.getValue(), etaDt, configLtDayNum);
                                    dateList.add(etaDt);
                                    dateList.add(esd);

                                    PsdPdd psdPdd = callLeadTimeCalculationAPI(shpgPlnTMsg, atpInfoTMsg, getBiggestDate(dateList));
                                    if (psdPdd == null) {
                                        continue;
                                    }
                                    atpInfoTMsg.psdDt.setValue(psdPdd.psd);
                                    atpInfoTMsg.pddDt.setValue(psdPdd.pdd);
                                }
                                break;

                            default:
                               break;
                        }

                    } else if (HARD_ALLOCATED.equals(shpgStsCd) && RTE_STS.UN_ROUTED.equals(rteStsCd)) {

                        if (this.isShippingNotPermit) {
                            atpInfoTMsg.psdDt.setValue(LABEL_TBD);
                            atpInfoTMsg.pddDt.setValue(LABEL_TBD);

                        } else {
                            dateList.add(esd);
                            String procDt = insVal.procDt;
                            procDt = getNearestBizDate(atpInfoTMsg.invtyLocCd.getValue(), procDt, configLtDayNum);
                            dateList.add(procDt);

                            PsdPdd psdPdd = callLeadTimeCalculationAPI(shpgPlnTMsg, atpInfoTMsg, getBiggestDate(dateList));
                            if (psdPdd == null) {
                                continue;
                            }
                            atpInfoTMsg.psdDt.setValue(psdPdd.psd);
                            atpInfoTMsg.pddDt.setValue(psdPdd.pdd);
                        }

                    } else if ((HARD_ALLOCATED.equals(shpgStsCd) && RTE_STS.ROUTED.equals(rteStsCd)) || (S_OR_O_PRINTED.equals(shpgStsCd))) {

                        atpInfoTMsg.psdDt.setValue(shpgPlnTMsg.psdDt.getValue());
                        atpInfoTMsg.pddDt.setValue(shpgPlnTMsg.pddDt.getValue());
                    }
                }

                if (!LABEL_TBD.equals(atpInfoTMsg.pddDt.getValue())) {
                    if (Y.equals(shpgPlnTMsg.exptFlg.getValue())) {
                        atpInfoTMsg.pddDt.setValue(LABEL_TBD);
                    }
                }
            }
        }

        /**
         * Calucurate Supply AVAL_QTY.
         * @param pMsg
         */
        private void calcSplyInfoAvalQty(ATP_BAT_WRKTMsg atpBatWrkTMsg) {

            List<HARD_ALLOCTMsg> hardAllocTMsgList = getHardAllocList(atpBatWrkTMsg.shpgPlnNum.getValue());
            if (isEmpty(hardAllocTMsgList)) {
                return;
            }

            for (HARD_ALLOCTMsg hardAllocTMsg : hardAllocTMsgList) {

                List<SPLY_INFOTMsg> splyInfoTMsgList = getSplyInfoList(hardAllocTMsg.mdseCd.getValue(), hardAllocTMsg.invtyLocCd.getValue(), hardAllocTMsg.stkStsCd.getValue());
                if (isEmpty(splyInfoTMsgList)) {
                    continue;
                }
                SPLY_INFOTMsg splyInfoTMsg = splyInfoTMsgList.get(0);

                splyInfoTMsg.avalQty.setValue(splyInfoTMsg.avalQty.getValueInt() - hardAllocTMsgList.get(0).hardAllocQty.getValueInt());

                // ***** update
                update(splyInfoTMsg);
            }
        }

        private PsdPdd callLeadTimeCalculationAPI(SHPG_PLNTMsg shpgPlnTMsg, ATP_INFOTMsg atpInfoTMsg, String rsd) {

            PsdPdd psdPdd = null;

            if (!hasValue(rsd)) {
                return psdPdd;
            }

            if (!ZYPDateUtil.checkDate(rsd)) {
                return psdPdd;
            }

            if (LABEL_TBD.equals(rsd)) {
                return psdPdd;
            }

            NWZC002001PMsg apiPMsg = new NWZC002001PMsg();
            apiPMsg.glblCmpyCd.setValue(getGlblCmpyCd());
            apiPMsg.mdseCd.setValue(atpInfoTMsg.mdseCd.getValue());
            apiPMsg.ordQty.setValue(atpInfoTMsg.cpoQty.getValue());
            apiPMsg.shpgSvcLvlCd.setValue(shpgPlnTMsg.reqShpgSvcLvlCd.getValue());
            apiPMsg.frtChrgMethCd.setValue(shpgPlnTMsg.reqFrtChrgMethCd.getValue());
            apiPMsg.frtChrgToCd.setValue(shpgPlnTMsg.reqFrtChrgToCd.getValue());
            apiPMsg.xxRwsdDt.setValue("");
            apiPMsg.xxRsdDt.setValue(rsd);
            apiPMsg.xxRddDt.setValue("");
            apiPMsg.invtyLocCd.setValue(atpInfoTMsg.invtyLocCd.getValue());
            apiPMsg.shipToPostCd.setValue(shpgPlnTMsg.shipToPostCd.getValue());
            apiPMsg.shipToCustCd.setValue(shpgPlnTMsg.shipToCustCd.getValue());
            apiPMsg.sellToCustCd.setValue(shpgPlnTMsg.sellToCustCd.getValue());
            apiPMsg.shipToStCd.setValue(shpgPlnTMsg.shipToStCd.getValue());
            apiPMsg.uomCd.setValue(PKG_UOM.EACH);
            apiPMsg.slsDt.setValue(insVal.procDt);

            new NWZC002001().execute(apiPMsg, onBatchType);

            if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
                psdPdd = new PsdPdd(LABEL_TBD, LABEL_TBD);
            } else {
                psdPdd = new PsdPdd(apiPMsg.xxPsdDt.getValue(), apiPMsg.xxPddDt.getValue());
            }

            return psdPdd;
        }

        private ATP_INFOTMsg createAtpInfoTMsg(DIST_PLN_DTLTMsg distPlnDtlTMsg, SPLY_INFOTMsg splyInfoTMsg, int ordQty, int avalQty) {

            final ATP_INFOTMsg atpInfoTMsg = new ATP_INFOTMsg();

            atpInfoTMsg.atpInfoPk.clear();
            atpInfoTMsg.atpCalcTs.clear();
            atpInfoTMsg.trxSrcTpCd.clear();
            atpInfoTMsg.trxHdrNum.clear();
            atpInfoTMsg.trxLineNum.clear();
            atpInfoTMsg.trxLineSubNum.clear();
            atpInfoTMsg.shpgPlnNum.clear();
            atpInfoTMsg.shpgStsCd.clear();
            atpInfoTMsg.mdseCd.setValue(splyInfoTMsg.mdseCd.getValue());
            atpInfoTMsg.invtyLocCd.setValue(splyInfoTMsg.invtyLocCd.getValue());
            atpInfoTMsg.allocQty.setValue(avalQty);
            atpInfoTMsg.cpoQty.setValue(ordQty);
            atpInfoTMsg.whEtaDt.setValue(splyInfoTMsg.etaOrEtdDt.getValue());
            atpInfoTMsg.psdDt.clear();
            atpInfoTMsg.pddDt.clear();
            atpInfoTMsg.distPk.setValue(distPlnDtlTMsg.distPk.getValue());
            atpInfoTMsg.distTmFrameNum.setValue(distPlnDtlTMsg.distTmFrameNum.getValue());
            atpInfoTMsg.inbdVisEventCd.setValue(splyInfoTMsg.inbdVisEventCd.getValue());
            atpInfoTMsg.imptInvNum.setValue(splyInfoTMsg.imptInvNum.getValue());
            atpInfoTMsg.vndInvDoNum.setValue(splyInfoTMsg.vndInvDoNum.getValue());
            atpInfoTMsg.imptCntnrNum.setValue(splyInfoTMsg.imptCntnrNum.getValue());

            return atpInfoTMsg;
        }

        private List<ATP_INFOTMsg> createAtpInfoTMsgForBlank(SHPG_PLNTMsg shpgPlnTMsg, String mdseCd, int ordQty) {

            final ATP_INFOTMsg atpInfoTMsg = new ATP_INFOTMsg();

            atpInfoTMsg.atpInfoPk.clear();
            atpInfoTMsg.atpCalcTs.clear();
            atpInfoTMsg.trxSrcTpCd.setValue(shpgPlnTMsg.trxSrcTpCd.getValue());
            atpInfoTMsg.trxHdrNum.setValue(shpgPlnTMsg.trxHdrNum.getValue());
            atpInfoTMsg.trxLineNum.setValue(shpgPlnTMsg.trxLineNum.getValue());
            atpInfoTMsg.trxLineSubNum.setValue(shpgPlnTMsg.trxLineSubNum.getValue());
            atpInfoTMsg.shpgPlnNum.setValue(shpgPlnTMsg.shpgPlnNum.getValue());
            atpInfoTMsg.shpgStsCd.setValue(shpgPlnTMsg.shpgStsCd.getValue());
            atpInfoTMsg.mdseCd.setValue(mdseCd);
            atpInfoTMsg.invtyLocCd.setValue(shpgPlnTMsg.invtyLocCd.getValue());
            atpInfoTMsg.cpoQty.setValue(ordQty);
            atpInfoTMsg.allocQty.setValue(shpgPlnTMsg.ordQty.getValue());
            atpInfoTMsg.whEtaDt.clear(); // LABEL_BLANK
            atpInfoTMsg.psdDt.clear(); // LABEL_BLANK
            atpInfoTMsg.pddDt.clear(); // LABEL_BLANK
            atpInfoTMsg.distPk.clear();
            atpInfoTMsg.distTmFrameNum.clear(); // LABEL_BLANK
            atpInfoTMsg.inbdVisEventCd.clear(); // LABEL_BLANK
            atpInfoTMsg.imptInvNum.setValue(LABEL_STOCK);
            atpInfoTMsg.vndInvDoNum.setValue(LABEL_STOCK);
            atpInfoTMsg.imptCntnrNum.setValue(LABEL_STOCK);

            return new ArrayList<ATP_INFOTMsg>(asList(atpInfoTMsg));
        }

        /**
         * Create atp memory for ETA Calculation.
         * @param shpgPlnTMsg
         * @param mdseCd
         * @param ordQty
         * @param invtyLocCd
         * @param softAllocQty
         * @param distPk
         * @param distTmFrameNum
         */
        private List<ATP_INFOTMsg> createAtpInfoTMsgForETACalc(SHPG_PLNTMsg shpgPlnTMsg, String mdseCd, int ordQty, int softAllocQty, BigDecimal distPk, String distTmFrameNum) {

            List<ATP_INFOTMsg> retList = new ArrayList<ATP_INFOTMsg>();

            // [SPLY_INFO]
            List<SPLY_INFOTMsg> splyInfoTMsgList = getSplyInfoList(mdseCd + "%", shpgPlnTMsg.invtyLocCd.getValue(), shpgPlnTMsg.stkStsCd.getValue());
            if (isEmpty(splyInfoTMsgList)) {
                splyInfoTMsgList = new ArrayList<SPLY_INFOTMsg>();
            }

            int allocQty = 0;
            if (softAllocQty > 0) {
                allocQty = softAllocQty;
            } else {
                allocQty = shpgPlnTMsg.ordQty.getValueInt();
            }

            for (int i = 0; allocQty > 0 && i < splyInfoTMsgList.size(); i++) {

                SPLY_INFOTMsg splyInfoTMsg = splyInfoTMsgList.get(i);
                if (splyInfoTMsg.avalQty.getValueInt() == 0) {
                    continue;
                }

                int curAllocQty = 0;
                if (allocQty > splyInfoTMsg.avalQty.getValueInt()) {
                    curAllocQty = splyInfoTMsg.avalQty.getValueInt();
                } else {
                    curAllocQty = allocQty;
                }

                final ATP_INFOTMsg atpInfoTMsg = new ATP_INFOTMsg();
                atpInfoTMsg.atpInfoPk.clear();
                atpInfoTMsg.atpCalcTs.clear();
                atpInfoTMsg.trxSrcTpCd.setValue(shpgPlnTMsg.trxSrcTpCd.getValue());
                atpInfoTMsg.trxHdrNum.setValue(shpgPlnTMsg.trxHdrNum.getValue());
                atpInfoTMsg.trxLineNum.setValue(shpgPlnTMsg.trxLineNum.getValue());
                atpInfoTMsg.trxLineSubNum.setValue(shpgPlnTMsg.trxLineSubNum.getValue());
                atpInfoTMsg.shpgPlnNum.setValue(shpgPlnTMsg.shpgPlnNum.getValue());
                atpInfoTMsg.shpgStsCd.setValue(shpgPlnTMsg.shpgStsCd.getValue());
                atpInfoTMsg.mdseCd.setValue(splyInfoTMsg.mdseCd.getValue());
                atpInfoTMsg.invtyLocCd.setValue(shpgPlnTMsg.invtyLocCd.getValue());
                atpInfoTMsg.allocQty.setValue(curAllocQty);
                atpInfoTMsg.cpoQty.setValue(ordQty);
                atpInfoTMsg.whEtaDt.setValue(splyInfoTMsg.etaOrEtdDt.getValue());
                atpInfoTMsg.psdDt.clear();
                atpInfoTMsg.pddDt.clear();
                if (distTmFrameNum != null && distTmFrameNum.length() > 0) {
                    atpInfoTMsg.distPk.setValue(distPk);
                    atpInfoTMsg.distTmFrameNum.setValue(distTmFrameNum);
                } else {
                    atpInfoTMsg.distPk.clear();
                    atpInfoTMsg.distTmFrameNum.clear(); // LABEL_BLANK
                }
                atpInfoTMsg.inbdVisEventCd.setValue(splyInfoTMsg.inbdVisEventCd.getValue());
                atpInfoTMsg.imptInvNum.setValue(splyInfoTMsg.imptInvNum.getValue());
                atpInfoTMsg.vndInvDoNum.setValue(splyInfoTMsg.vndInvDoNum.getValue());
                atpInfoTMsg.imptCntnrNum.setValue(splyInfoTMsg.imptCntnrNum.getValue());
                retList.add(atpInfoTMsg);

                splyInfoTMsg.avalQty.setValue(splyInfoTMsg.avalQty.getValueInt() - curAllocQty);

                // ***** update
                update(splyInfoTMsg);

                allocQty -= curAllocQty;
            }

            // Not dist type and last alloc qty > 0, create tbd.
            // DistType is make tbd after created eta all.
            if (allocQty > 0) {
                List<ATP_INFOTMsg> atpInfoTMsgList = createAtpInfoTMsgForTBD(shpgPlnTMsg, mdseCd, ordQty, allocQty);
                retList.addAll(atpInfoTMsgList);
            }

            return retList;
        }

        /**
         * Calclate the new remainder of the distribution for one mdse.
         */
        private List<ATP_INFOTMsg> createAtpInfoTMsgList(DIST_PLN_DTLTMsg distPlnDtlTMsg, DistributionRemainderBean distBean) {

            // --------------------------------------------------
            // [SPLY_INFO]
            // --------------------------------------------------
            String mdseCd = distBean.mdseCd;
            if (hasValue(distBean.ordTakeMdseCd)) {
                mdseCd = distBean.ordTakeMdseCd + "%";
            }

            List<SPLY_INFOTMsg> splyInfoTMsgList = getSplyInfoList(mdseCd, distBean.invtyLocCd, STK_STS.GOOD);
            if (isEmpty(splyInfoTMsgList)) {
                return emptyList();
            }

            // --------------------------------------------------
            // update [SPLY_INFO] & create [ATP_INFO] TMsg.
            // --------------------------------------------------
            final List<ATP_INFOTMsg> retList = new ArrayList<ATP_INFOTMsg>();

            int remindQty = distPlnDtlTMsg.distPlnAvalQty.getValueInt() + distPlnDtlTMsg.distPlnSaveQty.getValueInt();
            int lastQty = remindQty * distBean.childMdseQty.intValue();

            for (int i = 0; lastQty > 0 && i < splyInfoTMsgList.size(); i++) {

                SPLY_INFOTMsg splyInfoTMsg = splyInfoTMsgList.get(i);

                final int avalQty = splyInfoTMsg.avalQty.getValueInt();

                final int curRemQty;
                if (avalQty > lastQty) {
                    curRemQty = lastQty;
                    splyInfoTMsg.avalQty.setValue(avalQty - lastQty);
                    lastQty = 0;
                } else {
                    curRemQty = avalQty;
                    lastQty -= avalQty;
                    splyInfoTMsg.avalQty.setValue(ZERO);
                }

                // ***** update
                update(splyInfoTMsg);

                retList.add(createAtpInfoTMsg(distPlnDtlTMsg, splyInfoTMsg, remindQty, curRemQty));
            }

            return retList;
        }

        private int getAllocationTp(String distFlg, String mdseCd, String whCd, String stkStsCd) {

            int retCd = ALLOC_TP__NONE;

            if (Y.equals(distFlg)) {
                if (STK_STS.GOOD.equals(stkStsCd)) {
                    retCd = ALLOC_TP__DIST;
                } else {
                    retCd = ALLOC_TP__HARD_MANUAL;
                }

            } else {

                String defaultMdseCd = NWXC001001OrderTakeMdseTBLAccessor.findMdseCd(getGlblCmpyCd(), mdseCd);
                MDSETMsg mdse = getMdse(defaultMdseCd);
                if (mdse != null) {

                    // Soft
                    if (SOFT_ALLOC_TP.MANUAL.equals(mdse.invtySoftAllocTpCd.getValue())) {
                        retCd = ALLOC_TP__SOFT_MANUAL;
                    // Hard
                    } else {
                        if (HARD_ALLOC_TP.MANUAL.equals(mdse.invtyHardAllocTpCd.getValue())) {
                            retCd = ALLOC_TP__HARD_MANUAL;
                        } else {
                            retCd = ALLOC_TP__HARD_AUTO;
//                            final MDSE_WH_CONDTMsg mdseWH = getMdseWhCond(defaultMdseCd, whCd);
//                            if (mdseWH != null) {
//                                if (HARD_ALLOC_TP.MANUAL.equals(mdseWH.invtyHardAllocTpCd.getValue())) {
//                                    retCd = ALLOC_TP__HARD_MANUAL;
//                                }
//                            }
                        }
                    }
                }
            }

            return retCd;
        }

        private int getAllocationTpShowETA(String distFlg, String mdseCd, String whCd, String stkStsCd) {

            int retCd = ALLOC_TP__NONE;

            String defaultMdseCd = NWXC001001OrderTakeMdseTBLAccessor.findMdseCd(getGlblCmpyCd(), mdseCd);
            MDSETMsg mdse = getMdse(defaultMdseCd);

            if (mdse != null) {

                if (N.equals(mdse.showEtaFlg.getValue())) {
                    retCd = SHOW_ETA_FLG_N;
                    return retCd;
                }

                // Soft
                if (SOFT_ALLOC_TP.MANUAL.equals(mdse.invtySoftAllocTpCd.getValue())) {
                    retCd = ALLOC_TP__SOFT_MANUAL;
                    return retCd;
                }

                // Hard
                if (HARD_ALLOC_TP.MANUAL.equals(mdse.invtyHardAllocTpCd.getValue())) {
                    retCd = ALLOC_TP__HARD_MANUAL;
                    return retCd;
                }
            }

            // ShowETAFlg = Y and AllocationTp is not MANUAL
            retCd = getAllocationTp(distFlg, mdseCd, whCd, stkStsCd);
            return retCd;

        }

        private DIST_PLN_DTLTMsg getDistPlnDtl(BigDecimal distPk, String distTmFrameNum) {

            DIST_PLN_DTLTMsg distPlnDtlTMsg = new DIST_PLN_DTLTMsg();
            distPlnDtlTMsg.glblCmpyCd.setValue(getGlblCmpyCd());
            distPlnDtlTMsg.distPk.setValue(distPk);
            distPlnDtlTMsg.distTmFrameNum.setValue(distTmFrameNum);

            return (DIST_PLN_DTLTMsg) ssmClient.queryObject("getDistPlnDtl", distPlnDtlTMsg);
        }

        private DIST_PLN_TM_FRAMETMsg getDistPlnTmFrame(String distPlnNum, String distTmFrameNum) {

            DIST_PLN_TM_FRAMETMsg distPlnDtlTMsg = new DIST_PLN_TM_FRAMETMsg();
            distPlnDtlTMsg.glblCmpyCd.setValue(getGlblCmpyCd());
            distPlnDtlTMsg.distPlnNum.setValue(distPlnNum);
            distPlnDtlTMsg.distTmFrameNum.setValue(distTmFrameNum);

            return (DIST_PLN_TM_FRAMETMsg) findByKey(distPlnDtlTMsg);
        }

        @SuppressWarnings("unchecked")
        private void getExistHold(SHPG_PLNTMsg shpgPlnTMsg) {
            this.isHardAllocNotPermit = false;
            this.isShippingNotPermit = false;

            Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd",       getGlblCmpyCd());
            ssmParam.put("cpoOrdNum",        shpgPlnTMsg.trxHdrNum.getValue());
            ssmParam.put("cpoDtlLineNum",    shpgPlnTMsg.trxLineNum.getValue());
            ssmParam.put("cpoDtlLineSubNum", shpgPlnTMsg.trxLineSubNum.getValue());
            ssmParam.put("shpgPlnNum",       shpgPlnTMsg.shpgPlnNum.getValue());

            List<HLD_PROC_DFNTMsg> hldProcDfnTMsgList = ssmClient.queryObjectList("getExistHoldRs", ssmParam);

            for (HLD_PROC_DFNTMsg hldProcDfnTMsg : hldProcDfnTMsgList) {
                if (Y.equals(hldProcDfnTMsg.relPntHardAllocFlg.getValue())) {
                    this.isHardAllocNotPermit = true;
                    break;
                }
                if (Y.equals(hldProcDfnTMsg.relPntSoCratFlg.getValue())) {
                    this.isShippingNotPermit = true;
                    break;
                }
            }
        }

        @SuppressWarnings("unchecked")
        private List<HARD_ALLOCTMsg> getHardAllocList(String shpgPlnNum) {

            HARD_ALLOCTMsg queryParam = new HARD_ALLOCTMsg();
            queryParam.glblCmpyCd.setValue(getGlblCmpyCd());
            queryParam.shpgPlnNum.setValue(shpgPlnNum);

            return ssmClient.queryObjectList("getHardAllocList", queryParam);
        }

//        private MDSE_WH_CONDTMsg getMdseWhCond(String mdseCd, String whCd) {
//
//            MDSE_WH_CONDTMsg mdseWhCondTMsg = new MDSE_WH_CONDTMsg();
//            mdseWhCondTMsg.glblCmpyCd.setValue(getGlblCmpyCd());
//            mdseWhCondTMsg.mdseCd.setValue(mdseCd);
//            mdseWhCondTMsg.whCd.setValue(whCd);
//
//            return (MDSE_WH_CONDTMsg) findByKey(mdseWhCondTMsg);
//        }

        @SuppressWarnings("unchecked")
        private List<SPLY_INFOTMsg> getSplyInfoListForResetDistributionReminder(String mdseCd, String invtyLocCd, String inbdVisEventCd, String imptInvNum, String imptCntnrNum, String vndInvDoNum, String etaOrEtdDt) {

            SPLY_INFOTMsg splyInfoTMsg = new SPLY_INFOTMsg();
            splyInfoTMsg.glblCmpyCd.setValue(getGlblCmpyCd());
            splyInfoTMsg.mdseCd.setValue(mdseCd);
            if (hasValue(invtyLocCd)) {
                splyInfoTMsg.invtyLocCd.setValue(invtyLocCd);
            }
            splyInfoTMsg.inbdVisEventCd.setValue(inbdVisEventCd);
            splyInfoTMsg.imptInvNum.setValue(imptInvNum);
            splyInfoTMsg.imptCntnrNum.setValue(imptCntnrNum);
            splyInfoTMsg.vndInvDoNum.setValue(vndInvDoNum);
            splyInfoTMsg.etaOrEtdDt.setValue(etaOrEtdDt);

            return ssmClient.queryObjectList("getSplyInfoListForResetDistributionReminder", splyInfoTMsg);
        }

        private boolean isOnlyCalcDistRemainder(ATP_BAT_WRKTMsg atpBatWrkTMsg) {
            return hasValue(atpBatWrkTMsg.distPk) && hasValue(atpBatWrkTMsg.distTmFrameNum);
        }

        private void processResetDistributinReminder(ATP_BAT_WRKTMsg atpBatWrkTMsg) {

            // [SOFT_ALLOC]
            SOFT_ALLOCTMsg softAllocTMsg = getSoftAlloc(atpBatWrkTMsg.softAllocPk.getValue());
            if (softAllocTMsg == null) {
                return;
            }

            BigDecimal distPk     = softAllocTMsg.distPk.getValue();
            String distTmFrameNum = softAllocTMsg.distTmFrameNum.getValue();
            int softAllocQty      = softAllocTMsg.softAllocQty.getValueInt();

            List<ATP_INFOTMsg> reminderlist = getAtpInfoList(null, null, null, null, distPk, distTmFrameNum, null);
            if (isEmpty(reminderlist)) {
                return;
            }

            int remToSplyQty = softAllocQty;
            for (int i = 0; i < reminderlist.size() && remToSplyQty > 0; i++) {

                ATP_INFOTMsg atpInfoTMsg = reminderlist.get(i);

                // [SPLY_INFO]
                List<SPLY_INFOTMsg> splyInfoTMsgList = getSplyInfoListForResetDistributionReminder(atpInfoTMsg.mdseCd.getValue(), atpInfoTMsg.invtyLocCd.getValue(), atpInfoTMsg.inbdVisEventCd.getValue(), atpInfoTMsg.imptInvNum
                        .getValue(), atpInfoTMsg.imptCntnrNum.getValue(), atpInfoTMsg.vndInvDoNum.getValue(), atpInfoTMsg.whEtaDt.getValue());
                if (isEmpty(splyInfoTMsgList)) {
                    continue;
                }
                SPLY_INFOTMsg splyInfoTMsg = splyInfoTMsgList.get(0);

                int curRemToSplyQty = splyInfoTMsg.rcvPlnQty.getValueInt() - splyInfoTMsg.avalQty.getValueInt();
                if (curRemToSplyQty > remToSplyQty) {
                    curRemToSplyQty = remToSplyQty;
                }
                int retQty = 0;

                // If cur qty > atp allocqty, delete target atp. otherwise calc new atp allocqty (atp allocqty - cur qty) and update atp.
                if (curRemToSplyQty >= atpInfoTMsg.allocQty.getValueInt()) {
                    // ***** remove
                    removeAtpInfo(atpInfoTMsg);
                    splyInfoTMsg.avalQty.setValue(splyInfoTMsg.avalQty.getValueInt() + atpInfoTMsg.allocQty.getValueInt());
                    retQty = atpInfoTMsg.allocQty.getValueInt();
                } else {
                    atpInfoTMsg.allocQty.setValue(atpInfoTMsg.allocQty.getValueInt() - curRemToSplyQty);
                    // ***** update
                    updateAtpInfo(atpInfoTMsg);
                    splyInfoTMsg.avalQty.setValue(splyInfoTMsg.avalQty.getValueInt() + curRemToSplyQty);
                    retQty = curRemToSplyQty;
                }

                // ***** update
                update(splyInfoTMsg);
                remToSplyQty -= retQty;
            }
        }

        private ATP_BAT_WRKTMsg toAtpBatWrkTMsg(ResultSet rs) throws SQLException {

            final ATP_BAT_WRKTMsg atpBatWrkTMsg = new ATP_BAT_WRKTMsg();

            for (int i = 0; i < atpBatWrkColumnListArray[0].size(); i++) {

                String itemNm = (String) atpBatWrkColumnListArray[0].get(i);
                String dbColNm = (String) atpBatWrkColumnListArray[1].get(i);

                if (!itemNm.startsWith("ez")) {
                    Object value = rs.getObject(dbColNm);
                    if (value instanceof String) {
                        atpBatWrkTMsg.setValue(itemNm, -1, (String) value);
                    } else if (value instanceof BigDecimal) {
                        atpBatWrkTMsg.setValue(itemNm, -1, (BigDecimal) value);
                    } else {
                        continue;
                    }
                }
            }

            return atpBatWrkTMsg;
        }

        private void update(SPLY_INFOTMsg reqSplyInfoTMsg) {

            SPLY_INFOTMsg splyInfoTMsg = new SPLY_INFOTMsg();
            splyInfoTMsg.glblCmpyCd.setValue(getGlblCmpyCd());
            splyInfoTMsg.splyInfoPk.setValue(reqSplyInfoTMsg.splyInfoPk.getValue());
            // ***** findByKeyForUpdate
            splyInfoTMsg = (SPLY_INFOTMsg) findByKeyForUpdateAPI(splyInfoTMsg);
            if (splyInfoTMsg != null) {
                splyInfoTMsg.avalQty.setValue(reqSplyInfoTMsg.avalQty.getValue());
                // ***** update
                S21ApiTBLAccessor.update(splyInfoTMsg);
                if (!RTNCD_NORMAL.equals(splyInfoTMsg.getReturnCode())) {
                    addMsgId(NWZM0741E);
                }
            } else {
                addMsgId(NWZM0741E);
            }
        }

        private boolean validate(ATP_BAT_WRKTMsg atpBatWrkTMsg) {

            // SHPG_PLN_NUM
            if (!hasValue(atpBatWrkTMsg.shpgPlnNum)) {
                // DIST_PK
                if (!hasValue(atpBatWrkTMsg.distPk)) {
                    addMsgId(NWZM0022E);
                    return false;
                // DIST_TM_FRAME_NUM
                } else if (!hasValue(atpBatWrkTMsg.distTmFrameNum)) {
                    addMsgId(NWZM0612E);
                    return false;
                }
            }

            return true;
        }

        private Boolean isConfigurationItem(SHPG_PLNTMsg shpgPlnTMsg) {
            boolean rslt = Boolean.FALSE;
            CPO_DTLTMsg cpoDtlTMsg = getCpoDtlInfoByShpgPlnInfo(shpgPlnTMsg);
            if (null != cpoDtlTMsg) {
//                    && ZYPCommonFunc.hasValue(cpoDtlTMsg.configItemFlg)
//                    && Y.equals(cpoDtlTMsg.configItemFlg.getValue())) {
                rslt = Boolean.TRUE;
            }
            return rslt;
        }
        private BigDecimal getConfigLtDay(String mdseCd) {
            BigDecimal configLtDayNum = null;
            MDSETMsg dsMdseInfoTMsg = getMdseInfo(mdseCd);

            ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
            int ordTakeMdseCdDigit = ordTakeMdseTMsg.getAttr("ordTakeMdseCd").getDigit();
            if (null == dsMdseInfoTMsg && mdseCd.length() <= ordTakeMdseCdDigit) {
                String defaultMdseCd = NWXC001001OrderTakeMdseTBLAccessor.findMdseCd(getGlblCmpyCd(), mdseCd);
                dsMdseInfoTMsg = getMdseInfo(defaultMdseCd);
            }
            if (null == dsMdseInfoTMsg || (null != dsMdseInfoTMsg && !ZYPCommonFunc.hasValue(dsMdseInfoTMsg.configLtDayNum))) {
                configLtDayNum = ZYPCodeDataUtil.getNumConstValue(DEFAULT_CONFIG_LT_DAY_NUM, getGlblCmpyCd());
            } else {
                configLtDayNum = dsMdseInfoTMsg.configLtDayNum.getValue();
            }
            return configLtDayNum;
        }
        private CPO_DTLTMsg getCpoDtlInfoByShpgPlnInfo(SHPG_PLNTMsg shpgPlnTMsg) {
            CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, shpgPlnTMsg.trxHdrNum.getValue());
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, shpgPlnTMsg.trxLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, shpgPlnTMsg.trxLineSubNum.getValue());

            return (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(cpoDtlTMsg);
        }
        private MDSETMsg getMdseInfo(String mdseCd) {
            MDSETMsg mdseInfoTMsgKey = new MDSETMsg();
            ZYPEZDItemValueSetter.setValue(mdseInfoTMsgKey.glblCmpyCd, getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(mdseInfoTMsgKey.mdseCd, mdseCd);

            return (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseInfoTMsgKey);
        }
        private String getNearestBizDate(String invtyLocCd, String orgDt, BigDecimal addDates) {
            String calendarCode = getBizCalTpCd(getGlblCmpyCd(), invtyLocCd);
            String rslt = ZYPDateUtil.addBusinessDayEx(getGlblCmpyCd(), calendarCode, orgDt, addDates.intValue());
            return rslt;
        }
        private String getBizCalTpCd(String glblCmpyCd, String invtyLocCd) {

            // WAREHOUSE_CALENDAR
            CAL_RELNTMsg calReln = new CAL_RELNTMsg();
            ZYPEZDItemValueSetter.setValue(calReln.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(calReln.calSubTpCd, CAL_SUB_TP.WAREHOUSE_CALENDAR);
            ZYPEZDItemValueSetter.setValue(calReln.calMultCd,  invtyLocCd);
            calReln = (CAL_RELNTMsg) findByKey(calReln);

            final String calTpCd;

            if (calReln != null) {
                calTpCd = calReln.calTpCd.getValue();

            } else {

                // COMPANY_CALENDAR
                calReln = new CAL_RELNTMsg();
                calReln.setSQLID("001");
                calReln.setConditionValue("glblCmpyCd01", glblCmpyCd);
                calReln.setConditionValue("calSubTpCd01", CAL_SUB_TP.COMPANY_CALENDAR);

                CAL_RELNTMsgArray calRelnArray = (CAL_RELNTMsgArray) S21ApiTBLAccessor.findByCondition(calReln);

                if (null == calRelnArray
                        || calRelnArray.getValidCount() != 1) {
                    calTpCd = glblCmpyCd;
                } else {
                    calTpCd = calRelnArray.no(0).calTpCd.getValue();
                }
            }

            return calTpCd;
        }

        private Map<String, Object> getSvcMachMstr(String glblCmpyCd, String serNum, String mdseCd, String invtyLocCd) {

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", glblCmpyCd);
            param.put("mdseCd", mdseCd);
            param.put("serNum", serNum);
            param.put("invtyLocCd", invtyLocCd);

            List<Map<String, Object>> svcMachMstrList = (List<Map<String, Object>>) ssmClientAtp.queryObjectList("getSvcMachMstrStatus", param);

            if (svcMachMstrList.isEmpty()) {
                String key = NWZM1930E + " <Key: MdseCd=" + mdseCd + ", SerNum=" + serNum + ", InvtyLocCd=" + invtyLocCd + ">";
                S21InfoLogOutput.printlnv(key);
                return null;
            }
            if (svcMachMstrList.size() > 1) {
                String key = NWZM1931E + " <Key: MdseCd=" + mdseCd + ", SerNum=" + serNum + ", InvtyLocCd=" + invtyLocCd + ">";
                S21InfoLogOutput.printlnv(key);
                return null;
            }
            return (Map<String, Object>) svcMachMstrList.get(0);
        }

        private final class DistributionRemainderBean {

            private String     mdseCd;
            private String     ordTakeMdseCd;
            private String     invtyLocCd;
            private BigDecimal childMdseQty;

            private void setChildMdseQty(BigDecimal childMdseQty) {
                this.childMdseQty = childMdseQty;
            }

            private void setInvtyLocCd(String invtyLocCd) {
                this.invtyLocCd = invtyLocCd;
            }

            private void setMdseCd(String mdseCd) {
                this.mdseCd = mdseCd;
            }

            private void setOrdTakeMdseCd(String ordTakeMdseCd) {
                this.ordTakeMdseCd = ordTakeMdseCd;
            }
        }

        private final class PsdPdd {

            private String psd;
            private String pdd;

            private PsdPdd(String psd, String pdd) {
                this.psd = psd;
                this.pdd = pdd;
            }
        }
    }

    private static final class Cache {

        private List<ATP_BAT_WRKTMsg>              atpBatWrkList = new ArrayList<ATP_BAT_WRKTMsg>();
        private Map<String, ShpgPlnBean>           shpgPlnBean   = new HashMap<String, ShpgPlnBean>();
        private S21LRUMap<String, List<CMPSNTMsg>> cmpsn         = new S21LRUMap<String, List<CMPSNTMsg>>(200);
        private S21LRUMap<String, List<Map<String, ? >>> ordCmpsn = new S21LRUMap<String, List<Map<String, ? >>>(200);
        private S21LRUMap<String, INVTY_LOC_VTMsg> invtyLocV     = new S21LRUMap<String, INVTY_LOC_VTMsg>(200);
    }

    /**
     * inner class to management instance variables.
     */
    private static final class InstanceVariable {

        private String glblCmpyCd;
        private String procDt;
        private String runSystemTime;

        private List<ATP_INFOTMsg> atpInfoList                = new ArrayList<ATP_INFOTMsg>();
        private List<ATP_INFOTMsg> atpInfoListByDistRemainder = new ArrayList<ATP_INFOTMsg>();

        private InstanceVariable(NWZC108001PMsg pMsg) {
            this.glblCmpyCd    = pMsg.glblCmpyCd.getValue();
            this.procDt        = pMsg.procDt.getValue();
            this.runSystemTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
        }
    }

    /** ShpgPlnBean */
    private static final class ShpgPlnBean {

        @SuppressWarnings("unchecked")
        private static final List<String> shpgPlnTMsgFieldNmList = new SHPG_PLNTMsg().getSelectColumnList()[0];

        private Map<String, Object> shpgPlnData = new HashMap<String, Object>();

        private ShpgPlnBean(SHPG_PLNTMsg shpgPlnTMsg) {
            for (String fieldNm : shpgPlnTMsgFieldNmList) {
                shpgPlnData.put(fieldNm, shpgPlnTMsg.getValueData(fieldNm, -1));
            }
        }

        private SHPG_PLNTMsg toTMsg() {
            final SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
            for (Entry<String, Object> entry : shpgPlnData.entrySet()) {
                String fieldNm = entry.getKey();
                Object value   = entry.getValue();
                if (value instanceof String) {
                    shpgPlnTMsg.setValue(fieldNm, -1, (String) value);
                } else if (value instanceof BigDecimal) {
                    shpgPlnTMsg.setValue(fieldNm, -1, (BigDecimal) value);
                } else {
                    // nothing to do.
                }
            }
            return shpgPlnTMsg;
        }
    }
    private List<PO_DTLTMsg> getPoDtlListByTrxNum(SHPG_PLNTMsg shpgPlnTMsg) {

        PO_DTLTMsg poDtlTMsg = new PO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(poDtlTMsg.glblCmpyCd, getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(poDtlTMsg.cpoOrdNum, shpgPlnTMsg.trxHdrNum.getValue());
        ZYPEZDItemValueSetter.setValue(poDtlTMsg.cpoDtlLineNum, shpgPlnTMsg.trxLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(poDtlTMsg.cpoDtlLineSubNum, shpgPlnTMsg.trxLineSubNum.getValue());

        return ssmClient.queryObjectList("getPoDtlListByTrxNum", poDtlTMsg);
    }

    private List<Map> getSplyInfoListForCusa(String vndInvDoNum) {
        Map<String, String> queryKey = new HashMap<String, String>();
        queryKey.put("glblCmpyCd", getGlblCmpyCd());
        queryKey.put("vndInvDoNum", vndInvDoNum + "%");
        queryKey.put("inbdVisDataTpCdStkIn", INBD_VIS_DATA_TP.STOCK_IN_DC);
        queryKey.put("inbdVisDataTpCdStkOut", INBD_VIS_DATA_TP.STOCK_OUT);

        return (List<Map>) ssmClient.queryObjectList("getSplyInfoListForCusa", queryKey);
    }

    private Boolean hasError() {
        Boolean rslt = Boolean.FALSE;
        NWZC108001PMsg pMsg = (NWZC108001PMsg) this.apiMsgMap.getPmsg();
        for (int n = 0; n < pMsg.xxMsgIdList.getValidCount(); n++) {
            String msgId = pMsg.xxMsgIdList.no(n).xxMsgId.getValue();
            if (msgId.endsWith("E")) {
                rslt = Boolean.TRUE;
                break;
            }
        }
        return rslt;
    }

    private RCV_ASN_VNDTMsg getRcvAsnVndInfo(String invtyLocCd) {
        RCV_ASN_VNDTMsg rcvAsnVndTMsg = new RCV_ASN_VNDTMsg();
        ZYPEZDItemValueSetter.setValue(rcvAsnVndTMsg.glblCmpyCd, getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(rcvAsnVndTMsg.rcvAsnVndCd, invtyLocCd);
        return (RCV_ASN_VNDTMsg) S21CacheTBLAccessor.findByKey(rcvAsnVndTMsg);
    }

    private Boolean isVndCusa(String invtyLocCd) {
        RCV_ASN_VNDTMsg rcvAsnVndTMsg = getRcvAsnVndInfo(invtyLocCd);
        return rcvAsnVndTMsg == null ? Boolean.FALSE : Boolean.TRUE;
    }

    /**
     * Get nearly ATP_INFOTMsg. checdate > procDate and nealy date.
     * @param atpInfoTMsgList
     * @return
     */
    private ATP_INFOTMsg getAtpInfoPsdLastDay(List<ATP_INFOTMsg> atpInfoTMsgList) {

        Pattern ptn = Pattern.compile("\\d{8}");

        Iterator<ATP_INFOTMsg> atpInfoTMsgIterator = atpInfoTMsgList.iterator();
        ATP_INFOTMsg lastAtpInfoTMsg = atpInfoTMsgIterator.next();

        while (atpInfoTMsgIterator.hasNext()) {
            ATP_INFOTMsg atpInfoTMsg = atpInfoTMsgIterator.next();
            String psdDt = atpInfoTMsg.psdDt.getValue();
            if (!hasValue(psdDt)) {
                continue;
            }
            Matcher mc = ptn.matcher(psdDt);
            if (!mc.matches()) {
                continue;
            }
            if (ZYPDateUtil.compare(lastAtpInfoTMsg.psdDt.getValue(), psdDt) < 0) {
                lastAtpInfoTMsg = atpInfoTMsg;
            }
        }

        return lastAtpInfoTMsg;
    }
}
