/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLE.NLEB007001;

import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.AP_INVOICE;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.ASSET_ADJUSTOMENT_OR_DISPOSAL;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.ASSET_ENTRY_FROM_AP;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.ASSET_STGNG_STS;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.CONFIGURATION_CHANGE;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.DISPOASAL;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.DS_ASSET_MSTR;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.DS_ASSET_STGNG;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.DS_RTL_WH_V;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.EMSD_SHIPMENT;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.INIT_COST_CALUC;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.INV;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.MDSE;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.MODE_RETURN;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.MODE_UPDATE_BEFORE_ACTIVATE;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.NLEM0001E;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.NLEM0022E;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.NLEM0024E;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.NLEM0026E;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.NLEM0027E;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.NLEM0028E;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.NLEM0029E;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.NLEM0030E;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PENDING_ASSET_ENTRY;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PROC_MODE_01;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PROC_MODE_02;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PROC_MODE_11;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PROC_MODE_21;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PROC_MODE_31;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PROC_MODE_41;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PROC_MODE_51;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PROC_MODE_61;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PROGRAM_ID;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.RENTAL_SHIPMENT;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.RENTAL_TO_SALES;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.RETURN;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.REVENUE_RECOGNITION;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.SERVICE_EXCHANGE_SHIPMENT;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.SHIP_FROM_ASSET_WH;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.SHPG_PLN;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.SIZE_MAX;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.SVC_MACH_MSTR;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.UPDATE;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.UPDATE_SERVICE_EXCHANGE;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.DS_ASSET_MSTRTMsg;
import business.db.DS_ASSET_MSTRTMsgArray;
import business.db.DS_ASSET_STGNGTMsg;
import business.db.DS_RTL_WH_VTMsgArray;
import business.db.INVTMsg;
import business.db.MDSETMsg;
import business.db.SHPG_PLNTMsg;
import business.parts.NLZC305001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC305001.NLZC305001;
import com.canon.cusa.s21.batch.NLE.NLEB007001.common.NLEB007001CommonLogic;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ASSET_STGNG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_MODE;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * NLEB007001
 * Asset Creation Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Hitachi         J.Kim           Create          N/A
 * 2016/04/07   Hitachi         J.Kim           Update          QC#6644
 * 2016/04/11   Hitachi         J.Kim           Update          QC#6485
 * 2016/04/12   Hitachi         J.Kim           Update          QC#6869
 * 2016/04/12   Hitachi         J.Kim           Update          QC#5982
 * 2016/04/18   Hitachi         T.Tsuchida      Update          QC#7168
 * 2016/04/26   Hitachi         T.Tsuchida      Update          QC#7605
 * 2016/04/27   Hitachi         J.Kim           Update          QC#7556
 * 2016/05/09   Hitachi         T.Tsuchida      Update          QC#7743
 * 2016/05/11   Hitachi         T.Tsuchida      Update          QC#8118
 * 2016/05/12   Hitachi         T.Tsuchida      Update          QC#7537
 * 2016/05/12   Hitachi         T.Tsuchida      Update          QC#7773
 * 2016/06/01   Hitachi         T.Tsuchida      Update          QC#9229
 * 2016/06/06   Hitachi         T.Tsuchida      Update          QC#9451
 * 2016/06/08   Hitachi         T.Tsuchida      Update          QC#9542
 * 2016/06/23   Hitachi         T.Tsuchida      Update          QC#10694
 * 2016/06/27   Hitachi         T.Tsuchida      Update          QC#10896
 * 2016/07/01   Hitachi         J.Kim           Update          QC#11310
 * 2016/07/05   Hitachi         T.Tomita        Update          QC#10692
 * 2016/07/08   Hitachi         J.Kim           Update          QC#11627
 * 2016/07/12   Hitachi         J.Kim           Update          QC#11691
 * 2016/07/14   Hitachi         J.Kim           Update          QC#11910
 * 2016/07/15   Hitachi         J.Kim           Update          QC#11312
 * 2016/09/26   Hitachi         J.Kim           Update          QC#13372
 * 2017/01/31   Hitachi         E.Kameishi      Update          QC#17271
 * 2017/03/13   Hitachi         E.Kameishi      Update          QC#17980
 * 2017/11/17   Hitachi         J.Kim           Update          QC#17088
 * 2017/12/04   Hitachi         J.Kim           Update          QC#18127
 * 2018/02/19   Hitachi         J.Kim           Update          QC#23431
 * 2018/07/24   Hitachi         J.Kim           Update          QC#24950
 * 2018/07/30   Hitachi         J.Kim           Update          QC#26885
 * 2023/05/16   CITS            Y.Mochida       Update          QC#58740
 * </pre>
 */
public class NLEB007001 extends S21BatchMain {

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Date */
    private String batchDt = null;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCont = 0;

    /** message list */
    private List<String> msgList = new ArrayList<String>();

    /** API Error message list */
    private List<String> apiErrMsgList = new ArrayList<String>();

    /** API Error message Map */
    private Map<String, String[]> apiErrMsgMap = new HashMap<String, String[]>();

    /** Create Data Info */
    private Map<Integer, List<DSAssetStagingInfoBean>> createDSAssetStagingInfoMapList = new HashMap<Integer, List<DSAssetStagingInfoBean>>();

    /** DS Asset Staging Info Bean */
    private Map<String, List<DSAssetStagingInfoBean>> dSAssetStagingInfoSingleList = new HashMap<String, List<DSAssetStagingInfoBean>>();

    /** AssetStaging Data Info */
    private Map<Integer, List<DSAssetStagingInfoBean>> dSAssetStagingInfoMapList = new HashMap<Integer, List<DSAssetStagingInfoBean>>();

    /** DS Asset Staging Info Bean Mode 01,02,11,41 */
    private List<DSAssetStagingInfoBean> dSAssetStagingInfoMutiList = new ArrayList<DSAssetStagingInfoBean>();

    /** DS Asset Staging Info Bean */
    private List<DSAssetStagingInfoBean> dSAssetStagingInfoList = new ArrayList<DSAssetStagingInfoBean>();

    /** create Data List */
    private List<DSAssetStagingInfoBean> mailInfoBeanList = new ArrayList<DSAssetStagingInfoBean>();

    /** Save Bean Info */
    private DSAssetStagingInfoBean saveBeanInfo = new DSAssetStagingInfoBean();

    /** DS_ASSET_MSTR_PK */
    private List<BigDecimal> seDsAssetMstrPkList = new ArrayList<BigDecimal>();

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE batchType = null;

    @Override
    protected void initRoutine() {

        // "Global Company Code" Mandatory
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NLEM0001E, new String[] {"Global Company Code" });
        }

        // "Batch Date"
        this.batchDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd, PROGRAM_ID);
        if (!ZYPCommonFunc.hasValue(this.batchDt)) {
            throw new S21AbendException(NLEM0001E, new String[] {"Batch Date" });
        }

        this.batchType = ONBATCH_TYPE.BATCH;
    }

    @Override
    protected void mainRoutine() {

        // START 2018/07/30 J.Kim [QC#26885]
        //// ACCT Calendar
        //String acctYrMth = NLEB007001Query.getInstance().getAcctMthCtrl(this.glblCmpyCd);
        //
        //if (!ZYPCommonFunc.hasValue(acctYrMth)) {
        //    throw new S21AbendException(NLEM0001E, new String[] {"ACCT Calendar" });
        //}
        //
        //String batchYrMth = this.batchDt.substring(0, 6);
        //if (!batchYrMth.equals(acctYrMth)) {
        //    // START 2017/03/13 E.Kameishi [QC#17980,ADD]
        //    setInfoLogOutput(NLEM0048I, null);
        //    // END 2017/03/13 E.Kameishi [QC#17980,ADD]
        //    return;
        //}
        // END 2018/07/30 J.Kim [QC#26885]

        // Get Target Data List
        searchDsAssetStagingInfoBean();

        // Create Asset Creation List
        createDSAssetStagingInfoBeanList();

        doProcess();
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NLEB007001().executeBatch(NLEB007001.class.getSimpleName());
    }

    @Override
    protected void termRoutine() {

        if (this.errorCont > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCont, this.normalCount + this.errorCont);
    }

    /**
     * Search Target Data
     */
    private void searchDsAssetStagingInfoBean() {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            // Query parameter
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            String assetSts = ZYPCodeDataUtil.getVarCharConstValue(ASSET_STGNG_STS, this.glblCmpyCd);
            List<String> assetStsList = new ArrayList<String>();
            if (assetSts != null) {
                String[] asset = assetSts.split(",");
                assetStsList = Arrays.asList(asset);
            }
            queryParam.put("dsAssetStgngStsCd", assetStsList);
            queryParam.put("configCatgCdIsOutbound", CONFIG_CATG.OUTBOUND);
            queryParam.put("limitCount", SIZE_MAX);

            // Execute parameter
            S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
            execPrm.setFetchSize(SIZE_MAX);
            execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
            execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            ps = NLEB007001Query.getInstance().getDsAssetStagingInfo(queryParam, execPrm);
            rs = ps.executeQuery();

            while (rs.next()) {

                DSAssetStagingInfoBean bean = NLEB007001CommonLogic.setDsAssetStagingInfoBean(rs);

                if (PROC_MODE_01.equals(bean.getProcModeCd()) || PROC_MODE_02.equals(bean.getProcModeCd()) || PROC_MODE_11.equals(bean.getProcModeCd()) || PROC_MODE_41.equals(bean.getProcModeCd())) {

                    this.dSAssetStagingInfoMutiList.add(bean);

                } else {

                    this.dSAssetStagingInfoList.add(bean);

                }
            }

            this.dSAssetStagingInfoMapList.putAll(NLEB007001CommonLogic.editDSAssetStagingInfoList(this.dSAssetStagingInfoMutiList));

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(ps, rs);

        }
    }

    /**
     * Crate Target Data
     */
    private void createDSAssetStagingInfoBeanList() {

        for (int index = 0; index < this.dSAssetStagingInfoMapList.size(); index++) {

            boolean executionFlg = false;
            List<DSAssetStagingInfoBean> beanInfoList = (ArrayList<DSAssetStagingInfoBean>) this.dSAssetStagingInfoMapList.get(index);
            List<DSAssetStagingInfoBean> createBeanList = new ArrayList<DSAssetStagingInfoBean>();

            for (DSAssetStagingInfoBean beanInfo : beanInfoList) {

                String mode = beanInfo.getProcModeCd();

                if (PROC_MODE_01.equals(mode)) {
                    // Mode 01:Rental Shipment
                    List<DSAssetStagingInfoBean> tmpBeanList = createRentalShipData(beanInfo);

                    if (tmpBeanList == null || tmpBeanList.size() == 0) {
                        continue;
                    }

                    createBeanList.addAll(tmpBeanList);
                } else if (PROC_MODE_02.equals(mode)) {
                    // Mode 02:EMSD Ship
                    List<DSAssetStagingInfoBean> tmpBeanList = createEMSDShipData(beanInfo);

                    if (tmpBeanList == null) {
                        continue;
                    }

                    createBeanList.addAll(tmpBeanList);
                } else if (PROC_MODE_11.equals(mode)) {
                    // Mode 11:Rental Term of Conv,Early Buy out
                    // Only One Process
                    if (!executionFlg) {

                        List<DSAssetStagingInfoBean> tmpBeanList = createRentalTermOfConversion(beanInfo);

                        if (tmpBeanList == null) {
                            continue;
                        }

                        createBeanList.addAll(tmpBeanList);
                        executionFlg = true;
                    }
                } else if (PROC_MODE_41.equals(mode)) {
                    // Mode 41:Servie Exchange
                    List<DSAssetStagingInfoBean> tmpBeanList = createServiceExchange(beanInfo);

                    if (tmpBeanList == null) {
                        continue;
                    }

                    createBeanList.addAll(tmpBeanList);
                }
            }

            this.createDSAssetStagingInfoMapList.put(index, createBeanList);
        }

        List<DSAssetStagingInfoBean> procMode21List = new ArrayList<DSAssetStagingInfoBean>();
        List<DSAssetStagingInfoBean> procMode31List = new ArrayList<DSAssetStagingInfoBean>();
        List<DSAssetStagingInfoBean> procMode51List = new ArrayList<DSAssetStagingInfoBean>();
        List<DSAssetStagingInfoBean> procMode61List = new ArrayList<DSAssetStagingInfoBean>();

        for (DSAssetStagingInfoBean beanInfo : dSAssetStagingInfoList) {

            String mode = beanInfo.getProcModeCd();

            List<DSAssetStagingInfoBean> procModeList = new ArrayList<DSAssetStagingInfoBean>();

            if (PROC_MODE_21.equals(mode)) {
                // Mode 21:Return
                DSAssetStagingInfoBean tmpBean = createReturnWH(beanInfo);

                if (tmpBean == null) {
                    continue;
                }

                procMode21List.add(tmpBean);
                procModeList.addAll(procMode21List);
            } else if (PROC_MODE_31.equals(mode)) {
                // Mode 31:Configuration Change
                DSAssetStagingInfoBean tmpBean = createConfigurationChange(beanInfo);

                if (tmpBean == null) {
                    continue;
                }

                procMode31List.add(tmpBean);
                procModeList.addAll(procMode31List);
            } else if (PROC_MODE_51.equals(mode)) {
                // Mode 51:AP Invoice
                procMode51List.add(beanInfo);
                procModeList.addAll(procMode51List);
            } else if (PROC_MODE_61.equals(mode)) {
                // Mode 61:Asset Adjustment or Disposal
                DSAssetStagingInfoBean tmpBean = createAssetAdjustmentOrDisposal(beanInfo);

                if (tmpBean == null) {
                    continue;
                }

                procMode61List.add(tmpBean);
                procModeList.addAll(procMode61List);
            }

            this.dSAssetStagingInfoSingleList.put(mode, procModeList);
        }
    }

    private void doProcess() {

        this.apiErrMsgList.clear();

        for (int index = 0; index < this.createDSAssetStagingInfoMapList.size(); index++) {

            List<DSAssetStagingInfoBean> createBeanList = (ArrayList<DSAssetStagingInfoBean>) this.createDSAssetStagingInfoMapList.get(index);

            String procMode = null;
            int errProcessCount = 0;
            this.seDsAssetMstrPkList.clear();
            this.saveBeanInfo = new DSAssetStagingInfoBean();
            int beanListSize = createBeanList.size();
            boolean baseCmptFlg = checkBaseCmptFlg(createBeanList);

            for (int i = 0; i < createBeanList.size(); i++) {

                DSAssetStagingInfoBean createBean = createBeanList.get(i);
                procMode = createBean.getProcModeCd();

                if (PROC_MODE_01.equals(procMode)) {
                    // Mode 01:Rental Shipment
                    executionRentalShipment(createBean, baseCmptFlg, i, beanListSize);
                } else if (PROC_MODE_02.equals(procMode)) {
                    // Mode 02:EMSD Ship
                    executionEMSDShip(createBean, baseCmptFlg, i, beanListSize);
                } else if (PROC_MODE_11.equals(procMode)) {
                    // Mode 11:Rental Term of Conv,Early Buy out
                    executionRentalTermOfConvEarlyBuyout(createBean, i, beanListSize);
                } else if (PROC_MODE_41.equals(procMode)) {
                    // Mode 41:Servie Exchange
                    executionServiceExchange(createBean, baseCmptFlg);
                }

                if (this.apiErrMsgList.size() > 0) {

                    errProcessCount++;
                    this.errorCont += errProcessCount;
                    this.mailInfoBeanList.clear();
                    this.apiErrMsgList.clear();
                    break;
                }

                this.mailInfoBeanList.add(createBean);
            }

            if (this.mailInfoBeanList.size() > 0) {
                // Update DS AssetStaging
                updateDSAssetStaging(dSAssetStagingInfoMapList.get(index));
                // Send Mail
                sendMail(this.mailInfoBeanList, procMode);
            }

            this.mailInfoBeanList.clear();

        }

        this.mailInfoBeanList.clear();
        String[] procMode = {PROC_MODE_21, PROC_MODE_31, PROC_MODE_51, PROC_MODE_61 };

        for (String mode : procMode) {

            List<DSAssetStagingInfoBean> dSAssetStagingInfo = this.dSAssetStagingInfoSingleList.get(mode);
            this.apiErrMsgList.clear();

            if (PROC_MODE_21.equals(mode) && dSAssetStagingInfo != null) {

                for (DSAssetStagingInfoBean createBean : dSAssetStagingInfo) {
                    // Mode 21:Return
                    executionReturn(createBean);

                    if (this.apiErrMsgList.size() == 0) {
                        updateDSAssetStaging(createBean);
                        this.mailInfoBeanList.add(createBean);
                    } else {
                        this.errorCont++;
                        this.apiErrMsgList.clear();
                    }
                }
            } else if (PROC_MODE_31.equals(mode) && dSAssetStagingInfo != null) {
                // Mode 31:Configuration Change
                for (DSAssetStagingInfoBean createBean : dSAssetStagingInfo) {

                    executionConfigurationChange(createBean);

                    if (this.apiErrMsgList.size() == 0) {
                        updateDSAssetStaging(createBean);
                        this.mailInfoBeanList.add(createBean);
                    } else {
                        this.errorCont++;
                        this.apiErrMsgList.clear();
                    }
                }
            } else if (PROC_MODE_51.equals(mode) && dSAssetStagingInfo != null) {

                for (DSAssetStagingInfoBean createBean : dSAssetStagingInfo) {
                    // Mode 51:AP Invoice
                    executionAPInvoice(createBean);

                    if (this.apiErrMsgList.size() == 0) {
                        updateDSAssetStaging(createBean);
                        this.mailInfoBeanList.add(createBean);
                    } else {
                        this.errorCont++;
                        this.apiErrMsgList.clear();
                    }
                }
            } else if (PROC_MODE_61.equals(mode) && dSAssetStagingInfo != null) {
                // Mode 61:Asset Adjustment or Disposal
                for (DSAssetStagingInfoBean createBean : dSAssetStagingInfo) {

                    executionAssetAdjustmentOrDisposal(createBean);

                    if (this.apiErrMsgList.size() == 0) {
                        updateDSAssetStaging(createBean);
                        this.mailInfoBeanList.add(createBean);
                    } else {
                        this.errorCont++;
                        this.apiErrMsgList.clear();
                    }
                }
            }

            if (this.mailInfoBeanList.size() > 0) {
                sendMail(this.mailInfoBeanList, mode);
                this.mailInfoBeanList.clear();
            }
        }
    }

    private void sendMail(List<DSAssetStagingInfoBean> bean, String mode) {
        // Send Mail
        DSAssetStagingSendMail mail = new DSAssetStagingSendMail(this.glblCmpyCd, mode);
        mail.sendMail(this.mailInfoBeanList, this.msgList);
    }

    private boolean checkBaseCmptFlg(List<DSAssetStagingInfoBean> beanList) {

        for (DSAssetStagingInfoBean bean : beanList) {
            if (ZYPConstant.FLG_ON_Y.equals(bean.getBaseCmptFlg())) {
                return true;
            }
        }
        return false;
    }

    private List<DSAssetStagingInfoBean> createRentalShipData(DSAssetStagingInfoBean bean) {

        List<DSAssetStagingInfoBean> insertBeanList = new ArrayList<DSAssetStagingInfoBean>();

        // SHPG_PLN
        SHPG_PLNTMsg outTMsg = NLEB007001Query.getInstance().getShippingPlanTMsg(this.glblCmpyCd, bean.getShpgPlnNum());
        if (outTMsg == null) {
            setInfoLogOutput(NLEM0022E, new String[] {RENTAL_SHIPMENT, "SHPG_PLN" });
            return null;
        }

        bean.setShpgPlnSlsRepTocCd(outTMsg.slsRepTocCd.getValue());

        // SVC_MACH_MSTR
        List<Map<String, Object>> svcMachMsrtInfo = NLEB007001Query.getInstance().getSvcMachMstrInfo(this.glblCmpyCd, outTMsg.soNum.getValue(), outTMsg.soSlpNum.getValue());

        if (svcMachMsrtInfo == null || svcMachMsrtInfo.size() == 0) {
            setInfoLogOutput(NLEM0022E, new String[] {RENTAL_SHIPMENT, SVC_MACH_MSTR });
            return null;
        }

        // MDSE
        for (int index = 0; index < svcMachMsrtInfo.size(); index++) {
            DSAssetStagingInfoBean createBean = bean.clone();
            Map<String, Object> svcMachMsrtMap = svcMachMsrtInfo.get(index);
            MDSETMsg mdseTMsg = NLEB007001Query.getMdseTMsg(this.glblCmpyCd, (String) svcMachMsrtMap.get("MDSE_CD"));

            if (mdseTMsg == null) {
                setInfoLogOutput(NLEM0022E, new String[] {RENTAL_SHIPMENT, MDSE });
                return null;
            }

            if (!ZYPConstant.FLG_ON_Y.equals(mdseTMsg.instlBaseCtrlFlg.getValue())) {
                continue;
            }

            createBean.setSmmSvcMachMstrPk((BigDecimal) svcMachMsrtMap.get("SVC_MACH_MSTR_PK"));
            createBean.setSmmMdseCd((String) svcMachMsrtMap.get("MDSE_CD"));
            createBean.setSerNum((String) svcMachMsrtMap.get("SER_NUM"));
            insertBeanList.add(createBean);
        }

        return insertBeanList;
    }

    private List<DSAssetStagingInfoBean> createEMSDShipData(DSAssetStagingInfoBean bean) {

        List<DSAssetStagingInfoBean> insertBeanList = new ArrayList<DSAssetStagingInfoBean>();
        SHPG_PLNTMsg outTMsg = NLEB007001Query.getInstance().getShippingPlanTMsg(this.glblCmpyCd, bean.getShpgPlnNum());
        if (outTMsg == null) {
            setInfoLogOutput(NLEM0022E, new String[] {EMSD_SHIPMENT, "SHPG_PLN" });
            return null;
        }

        DS_RTL_WH_VTMsgArray outVTMsg = NLEB007001Query.getInstance().getDsRtlWhVTMsg(this.glblCmpyCd, outTMsg.invtyLocCd.getValue());
        if (outVTMsg.getValidCount() > 0) {
            bean.setInvtyAcctCd(outVTMsg.no(0).invtyAcctCd.getValue());
        }

        // SVC_MACH_MSTR
        List<Map<String, Object>> svcMachMsrtInfo = NLEB007001Query.getInstance().getSvcMachMstrInfo(this.glblCmpyCd, outTMsg.soNum.getValue(), outTMsg.soSlpNum.getValue());
        if (svcMachMsrtInfo == null || svcMachMsrtInfo.size() == 0) {
            setInfoLogOutput(NLEM0022E, new String[] {EMSD_SHIPMENT, "SVC_MACH_MSTR" });
            return null;
        }

        // MDSE
        for (Map<String, Object> svcMachMsrtMap : svcMachMsrtInfo) {
            DSAssetStagingInfoBean createBean = bean.clone();
            MDSETMsg mdseTMsg = NLEB007001Query.getMdseTMsg(this.glblCmpyCd, (String) svcMachMsrtMap.get("MDSE_CD"));
            if (mdseTMsg == null) {
                setInfoLogOutput(NLEM0022E, new String[] {EMSD_SHIPMENT, MDSE });
                return null;
            }
            if (!ZYPConstant.FLG_ON_Y.equals(mdseTMsg.instlBaseCtrlFlg.getValue())) {
                continue;
            }
            createBean.setSmmSvcMachMstrPk((BigDecimal) svcMachMsrtMap.get("SVC_MACH_MSTR_PK"));
            createBean.setSmmMdseCd((String) svcMachMsrtMap.get("MDSE_CD"));
            createBean.setSerNum((String) svcMachMsrtMap.get("SER_NUM"));
            insertBeanList.add(createBean);
        }
        return insertBeanList;
    }

    private List<DSAssetStagingInfoBean> createRentalTermOfConversion(DSAssetStagingInfoBean bean) {

        List<DSAssetStagingInfoBean> insertBeanList = new ArrayList<DSAssetStagingInfoBean>();

        // 1-0-1
        List<Map<String, Object>> saAssetMstrInfoList = NLEB007001Query.getInstance().getDsAssetMstrPkRentalTermOfConv(bean);
        if (saAssetMstrInfoList == null || saAssetMstrInfoList.size() == 0) {
        	// START 2023/05/16 Y.Mochida [QC#58740,ADD]
        	updateDSAssetStaging(bean);
        	// END 2023/05/16 Y.Mochida [QC#58740,ADD]
            infoLogOutput(NLEM0022E, new String[] {RENTAL_TO_SALES, DS_ASSET_MSTR });
            return null;
        }

        // 1-0-1-1 
        Map<String, Object> dsAssetMstrPostInfo = NLEB007001Query.getInstance().getDsAssetMstrPkPostData(bean);

        if (dsAssetMstrPostInfo != null) {
            infoLogOutput(NLEM0028E, new String[] {RENTAL_TO_SALES });
            return null;
        }

        // 1-0-1-2
        List<Map<String, Object>> dsAssetMstrPostActiveList = NLEB007001Query.getInstance().getDsAssetMstrPkPostActive(bean);
        for (Map<String, Object> dsAssetMstrPostActive : dsAssetMstrPostActiveList) {
            DSAssetStagingInfoBean createBean = bean.clone();
            createBean.setDsAssetMstrPk((BigDecimal) dsAssetMstrPostActive.get("DS_ASSET_MSTR_PK"));
            createBean.setSvcMachMstrPk((BigDecimal) dsAssetMstrPostActive.get("SVC_MACH_MSTR_PK"));
            createBean.setMdseCd((String) dsAssetMstrPostActive.get("MDSE_CD"));
            insertBeanList.add(createBean);
        }
        if (dsAssetMstrPostActiveList == null || dsAssetMstrPostActiveList.size() == 0) {
            insertBeanList.add(bean);
        }
        return insertBeanList;
    }

    private DSAssetStagingInfoBean createReturnWH(DSAssetStagingInfoBean bean) {

        // 1-1. Get Retrun WH
        DS_RTL_WH_VTMsgArray outTMsg = NLEB007001Query.getInstance().getDsRtlWhVTMsg(bean);
        if (outTMsg == null || outTMsg.getValidCount() == 0) {
            infoLogOutput(NLEM0022E, new String[] {RETURN, DS_RTL_WH_V });
            return null;
        }

        // 1-1. Base Component
        Map<String, Object> dsAssetMstrPkMap = NLEB007001Query.getInstance().getDsAssetMstrPkReturn(bean);
        if (dsAssetMstrPkMap == null) {
        	// START 2023/05/16 Y.Mochida [QC#58740,ADD]
        	updateDSAssetStaging(bean);
        	// END 2023/05/16 Y.Mochida [QC#58740,ADD]
            infoLogOutput(NLEM0022E, new String[] {RETURN, "DS_ASSET_MSTR" });
            return null;
        }

        bean.setInvtyAcctCd(outTMsg.no(0).invtyAcctCd.getValue());
        bean.setRtlWhNm(outTMsg.no(0).rtlWhNm.getValue());
        bean.setDsAssetMstrPk((BigDecimal) dsAssetMstrPkMap.get("DS_ASSET_MSTR_PK"));
        bean.setPrntDsAssetMstrPk((BigDecimal) dsAssetMstrPkMap.get("PRNT_DS_ASSET_MSTR_PK"));
        bean.setAssetTpCd((String) dsAssetMstrPkMap.get("ASSET_TP_CD"));
        bean.setAssetStsCd((String) dsAssetMstrPkMap.get("ASSET_STS_CD"));

        String dsAssetStsCd = (String) dsAssetMstrPkMap.get("ASSET_STS_CD");
        if (ASSET_STS.RETIRE.equals(dsAssetStsCd)) {
        	// START 2023/05/16 Y.Mochida [QC#58740,ADD]
        	updateDSAssetStaging(bean);
        	// END 2023/05/16 Y.Mochida [QC#58740,ADD]
            // Skip
            return null;
        }

        if (!ASSET_STS.ACTIVATE.equals(dsAssetStsCd) && !ASSET_STS.MERGED.equals(dsAssetStsCd) && !ASSET_STS.RETIRE.equals(dsAssetStsCd)) {
            infoLogOutput(NLEM0029E, new String[] {RETURN, dsAssetStsCd });
            return null;
        }

        Map<String, Object> svcExchForReturnMap = NLEB007001Query.getInstance().getSvcExchForReturn(bean);
        if (svcExchForReturnMap == null) {
            infoLogOutput(NLEM0022E, new String[] {RETURN, DS_ASSET_STGNG });
            return null;
        }

        return bean;
    }

    private DSAssetStagingInfoBean createConfigurationChange(DSAssetStagingInfoBean bean) {

        Map<String, Object> dsAssetMstrInfo = NLEB007001Query.getInstance().getDsAssetMstrConfigurationChange(bean);
        if (dsAssetMstrInfo == null) {
            infoLogOutput(NLEM0022E, new String[] {CONFIGURATION_CHANGE, DS_ASSET_MSTR });
            return null;
        }

        String status = (String) dsAssetMstrInfo.get("ASSET_STS_CD");
        String baseCmptFlg = (String) dsAssetMstrInfo.get("BASE_CMPT_FLG");

        if (ASSET_STS.RETIRE.equals(status) || ASSET_STS.DELETE.equals(status) || ASSET_STS.CAPITALIZED.equals(status)) {
            infoLogOutput(NLEM0027E, new String[] {CONFIGURATION_CHANGE, status });
            return null;
        }

        bean.setBaseCmptFlg(baseCmptFlg);
        bean.setDsAssetMstrPk((BigDecimal) dsAssetMstrInfo.get("DS_ASSET_MSTR_PK"));
        bean.setSvcConfigMstrPk((BigDecimal) dsAssetMstrInfo.get("SVC_CONFIG_MSTR_PK"));
        bean.setPrntDsAssetMstrPk((BigDecimal) dsAssetMstrInfo.get("PRNT_DS_ASSET_MSTR_PK"));
        bean.setAssetPostFlg((String) dsAssetMstrInfo.get("ASSET_POST_FLG"));
        bean.setActvAssetFlg((String) dsAssetMstrInfo.get("ACTV_ASSET_FLG"));

        return bean;
    }

    private List<DSAssetStagingInfoBean> createServiceExchange(DSAssetStagingInfoBean bean) {

        List<DSAssetStagingInfoBean> insertBeanList = new ArrayList<DSAssetStagingInfoBean>();
        SHPG_PLNTMsg outTMsg = NLEB007001Query.getInstance().getShippingPlanTMsg(this.glblCmpyCd, bean.getShpgPlnNum());

        if (outTMsg == null) {
            setInfoLogOutput(NLEM0022E, new String[] {SERVICE_EXCHANGE_SHIPMENT, SHPG_PLN });
            return null;
        }

        DS_RTL_WH_VTMsgArray outVTMsg = NLEB007001Query.getInstance().getDsRtlWhVTMsg(this.glblCmpyCd, outTMsg.invtyLocCd.getValue());

        if (outVTMsg.getValidCount() > 0) {
            bean.setInvtyAcctCd(outVTMsg.no(0).invtyAcctCd.getValue());
            bean.setRtlWhNm(outVTMsg.no(0).rtlWhNm.getValue());
        }

        // SVC_MACH_MSTR
        List<Map<String, Object>> svcMachMsrtInfo = NLEB007001Query.getInstance().getSvcMachMstrInfo(this.glblCmpyCd, outTMsg.soNum.getValue(), outTMsg.soSlpNum.getValue());

        if (svcMachMsrtInfo == null || svcMachMsrtInfo.size() == 0) {
            setInfoLogOutput(NLEM0022E, new String[] {SERVICE_EXCHANGE_SHIPMENT, SVC_MACH_MSTR });
            return null;
        }

        // MDSE
        for (Map<String, Object> svcMachMsrtMap : svcMachMsrtInfo) {
            MDSETMsg mdseTMsg = NLEB007001Query.getMdseTMsg(this.glblCmpyCd, (String) svcMachMsrtMap.get("MDSE_CD"));

            if (mdseTMsg == null) {
                setInfoLogOutput(NLEM0022E, new String[] {SERVICE_EXCHANGE_SHIPMENT, MDSE });
                return null;
            }

            if (!ZYPConstant.FLG_ON_Y.equals(mdseTMsg.instlBaseCtrlFlg.getValue())) {
                continue;
            }

            DSAssetStagingInfoBean createBean = bean.clone();
            createBean.setSmmSvcMachMstrPk((BigDecimal) svcMachMsrtMap.get("SVC_MACH_MSTR_PK"));
            createBean.setSmmMdseCd((String) svcMachMsrtMap.get("MDSE_CD"));
            createBean.setSerNum((String) svcMachMsrtMap.get("SER_NUM"));
            insertBeanList.add(createBean);
        }
        return insertBeanList;
    }

    private DSAssetStagingInfoBean createAssetAdjustmentOrDisposal(DSAssetStagingInfoBean bean) {

        DS_RTL_WH_VTMsgArray inTMsg = NLEB007001Query.getInstance().getDsRtlWhVTMsg(bean);

        if (inTMsg == null || inTMsg.getValidCount() == 0) {
        	// START 2023/05/16 Y.Mochida [QC#58740,ADD]
        	updateDSAssetStaging(bean);
        	// END 2023/05/16 Y.Mochida [QC#58740,ADD]
            infoLogOutput(NLEM0022E, new String[] {ASSET_ADJUSTOMENT_OR_DISPOSAL, DS_RTL_WH_V });
            return null;
        }

        if (!INVTY_ACCT.ASSET.equals(inTMsg.no(0).invtyAcctCd.getValue())) {
        	// START 2023/05/16 Y.Mochida [QC#58740,ADD]
        	updateDSAssetStaging(bean);
        	// END 2023/05/16 Y.Mochida [QC#58740,ADD]
            infoLogOutput(NLEM0026E, new String[] {ASSET_ADJUSTOMENT_OR_DISPOSAL, inTMsg.no(0).invtyAcctCd.getValue() });
            return null;
        }

        Map<String, Object> assetDisposal = NLEB007001Query.getInstance().getDsAssetMstrParentAssetAdjOrDisposal(bean);

        if (assetDisposal == null) {
            infoLogOutput(NLEM0030E, new String[] {ASSET_ADJUSTOMENT_OR_DISPOSAL });
            return null;
        }

        bean.setDsAssetMstrPk((BigDecimal) assetDisposal.get("DS_ASSET_MSTR_PK"));
        bean.setPrntDsAssetMstrPk((BigDecimal) assetDisposal.get("PRNT_DS_ASSET_MSTR_PK"));
        bean.setBaseCmptFlg((String) assetDisposal.get("BASE_CMPT_FLG"));

        return bean;
    }

    /**
     * Mode 01 : Rental Ship
     * @param createBean DSAssetStagingInfoBean
     * @param baseCmptFlg boolean
     * @param index int
     * @param listSize int
     */
    private void executionRentalShipment(DSAssetStagingInfoBean createBean, boolean baseCmptFlg, int index, int listSize) {

        createAssetData(createBean);

        if (ZYPConstant.FLG_OFF_N.equals(createBean.getBaseCmptFlg())) {
            createBean.setPrntDsAssetMstrPk(this.saveBeanInfo.getPrntDsAssetMstrPk());
        } else {
            createBean.setPrntDsAssetMstrPk(null);
        }

        if (!baseCmptFlg) {
            // Base Component Nothing
            Map<String, Object> dsAssetMstrPkInfo = NLEB007001Query.getInstance().getConfigMainMachineAssetMstr(glblCmpyCd, createBean.getServcConfigMstrPk());

            if (dsAssetMstrPkInfo == null) {
                infoLogOutput(NLEM0022E, new String[] {RENTAL_SHIPMENT, DS_ASSET_MSTR });
                return;
            }

            createBean.setPrntDsAssetMstrPk((BigDecimal) dsAssetMstrPkInfo.get("PRNT_DS_ASSET_MSTR_PK"));

            if (ZYPConstant.FLG_ON_Y.equals((String) dsAssetMstrPkInfo.get("ASSET_POST_FLG"))) {
                createBean.setPrntDsAssetMstrPk(null);
            }

            this.saveBeanInfo.setDsAssetMstrPk((BigDecimal) dsAssetMstrPkInfo.get("DS_ASSET_MSTR_PK"));
            this.saveBeanInfo.setSmmMdseCd((String) dsAssetMstrPkInfo.get("MDSE_CD"));
            this.saveBeanInfo.setSmmSvcMachMstrPk((BigDecimal) dsAssetMstrPkInfo.get("SVC_MACH_MSTR_PK"));
            this.saveBeanInfo.setPrntDsAssetMstrPk((BigDecimal) dsAssetMstrPkInfo.get("PRNT_DS_ASSET_MSTR_PK"));

        }

        // NLZC3050 Asset Update API Mode A(Pending Asset Entry)
        callApiAssetUpdate(createBean, NLEB007001CommonLogic.setNLZC305001PMsgRentalShipModeA(createBean));

        if (this.apiErrMsgList.size() > 0) {
            infoLogOutput(NLEM0024E, new String[] {RENTAL_SHIPMENT, PENDING_ASSET_ENTRY });
            return;
        }

        if (ZYPConstant.FLG_ON_Y.equals(createBean.getBaseCmptFlg())) {
            this.saveBeanInfo.setDsAssetMstrPk(createBean.getSeApiRsDsAssetMstrPk());
            this.saveBeanInfo.setSmmMdseCd(createBean.getSmmMdseCd());
            this.saveBeanInfo.setSmmSerNum(createBean.getSmmSerNum());
                this.saveBeanInfo.setSmmSvcMachMstrPk(createBean.getSmmSvcMachMstrPk());
                this.saveBeanInfo.setPrntDsAssetMstrPk(createBean.getSeApiRsDsAssetMstrPk());
            }

        if (index == (listSize - 1)) {
            if (createBean.getPrntDsAssetMstrPk() == null) {
                this.saveBeanInfo.setDsAssetMstrPk(createBean.getSeApiRsDsAssetMstrPk());
                createBean.setSeApiRsDsAssetMstrPk(createBean.getSeApiRsDsAssetMstrPk());
                createBean.setDsAssetMstrPk(createBean.getSeApiRsDsAssetMstrPk());
                createBean.setPrntDsAssetMstrPk(createBean.getSeApiRsDsAssetMstrPk());
            } else {
                createBean.setSeApiRsDsAssetMstrPk(this.saveBeanInfo.getDsAssetMstrPk());
                createBean.setDsAssetMstrPk(this.saveBeanInfo.getPrntDsAssetMstrPk());
            }

            // NLZC3050 Asset Update API Mode H(Initial Cost
            // Calculation)
            callApiAssetUpdate(createBean, NLEB007001CommonLogic.setNLZC305001PMsgRentalShipModeH(createBean, PROC_MODE.RENTAL));

            if (this.apiErrMsgList.size() > 0) {
                infoLogOutput(NLEM0024E, new String[] {RENTAL_SHIPMENT, INIT_COST_CALUC });
                return;
            }
        }
    }

    private void executionEMSDShip(DSAssetStagingInfoBean createBean, boolean baseCompornent, int index, int listSize) {

        if (!INVTY_ACCT.ASSET.equals(createBean.getInvtyAcctCd())) {
            createAssetData(createBean);
        }

        if (ZYPConstant.FLG_OFF_N.equals(createBean.getBaseCmptFlg())) {
            createBean.setPrntDsAssetMstrPk(this.saveBeanInfo.getPrntDsAssetMstrPk());
        } else {
            createBean.setPrntDsAssetMstrPk(null);
        }

        if (!baseCompornent) {
            // Base Component Nothing
            Map<String, Object> dsAssetMstrPkInfo = NLEB007001Query.getInstance().getConfigMainMachineAssetMstr(glblCmpyCd, createBean.getServcConfigMstrPk());

            if (dsAssetMstrPkInfo == null) {
                infoLogOutput(NLEM0022E, new String[] {EMSD_SHIPMENT, DS_ASSET_MSTR });
                return;
            }

            createBean.setPrntDsAssetMstrPk((BigDecimal) dsAssetMstrPkInfo.get("PRNT_DS_ASSET_MSTR_PK"));

            if (ZYPConstant.FLG_ON_Y.equals((String) dsAssetMstrPkInfo.get("ASSET_POST_FLG"))) {
                createBean.setPrntDsAssetMstrPk(null);
            }

            createBean.setAssetStsCd((String) dsAssetMstrPkInfo.get("ASSET_STS_CD"));

            this.saveBeanInfo.setDsAssetMstrPk((BigDecimal) dsAssetMstrPkInfo.get("DS_ASSET_MSTR_PK"));
            this.saveBeanInfo.setSmmMdseCd((String) dsAssetMstrPkInfo.get("MDSE_CD"));
            this.saveBeanInfo.setSmmSvcMachMstrPk((BigDecimal) dsAssetMstrPkInfo.get("SVC_MACH_MSTR_PK"));
            this.saveBeanInfo.setPrntDsAssetMstrPk((BigDecimal) dsAssetMstrPkInfo.get("PRNT_DS_ASSET_MSTR_PK"));
        }

        // Asset Wh
        if (INVTY_ACCT.ASSET.equals(createBean.getInvtyAcctCd())) {

            DS_ASSET_MSTRTMsgArray outTMsg = NLEB007001Query.getInstance().getDsAssetMstr(this.glblCmpyCd, createBean.getSmmSvcMachMstrPk());
            if (outTMsg == null || outTMsg.getValidCount() == 0) {
                infoLogOutput(NLEM0022E, new String[] {EMSD_SHIPMENT, DS_ASSET_MSTR });
                return;
            }

            createBean.setDsAssetMstrPk(outTMsg.no(0).dsAssetMstrPk.getValue());

            // NLZC3050 Asset Update API (Mode6:Ship from Asset WH)
            callApiAssetUpdate(createBean, NLEB007001CommonLogic.setNLZC305001PMsgEMSDShippingMode6(createBean));

            if (this.apiErrMsgList.size() > 0) {
                infoLogOutput(NLEM0024E, new String[] {EMSD_SHIPMENT, SHIP_FROM_ASSET_WH });
                return;
            }

            this.saveBeanInfo.setDsAssetMstrPk(createBean.getDsAssetMstrPk());

        } else {

            // NLZC3050 Asset Update API (ModeA:Pending Asset Entry)
            callApiAssetUpdate(createBean, NLEB007001CommonLogic.setNLZC305001PMsgEMSDShippingModeA(createBean));

            if (this.apiErrMsgList.size() > 0) {
                infoLogOutput(NLEM0024E, new String[] {EMSD_SHIPMENT, PENDING_ASSET_ENTRY });
                return;
            }

            if (ZYPConstant.FLG_ON_Y.equals(createBean.getBaseCmptFlg())) {
                createBean.setPrntDsAssetMstrPk(createBean.getSeApiRsDsAssetMstrPk());
                this.saveBeanInfo.setDsAssetMstrPk(createBean.getSeApiRsDsAssetMstrPk());
                this.saveBeanInfo.setSmmMdseCd(createBean.getSmmMdseCd());
                this.saveBeanInfo.setSmmSerNum(createBean.getSmmSerNum());
                this.saveBeanInfo.setSmmSvcMachMstrPk(createBean.getSmmSvcMachMstrPk());
                this.saveBeanInfo.setPrntDsAssetMstrPk(createBean.getSeApiRsDsAssetMstrPk());
            }
        }

        if ((index == (listSize - 1) || createBean.getPrntDsAssetMstrPk() == null) && !INVTY_ACCT.ASSET.equals(createBean.getInvtyAcctCd())) {
            if (createBean.getPrntDsAssetMstrPk() == null) {
                this.saveBeanInfo.setDsAssetMstrPk(createBean.getSeApiRsDsAssetMstrPk());
                createBean.setDsAssetMstrPk(createBean.getSeApiRsDsAssetMstrPk());
                createBean.setPrntDsAssetMstrPk(createBean.getSeApiRsDsAssetMstrPk());
            } else {
                createBean.setDsAssetMstrPk(this.saveBeanInfo.getDsAssetMstrPk());
                createBean.setDsAssetMstrPk(this.saveBeanInfo.getPrntDsAssetMstrPk());
            }

            // NLZC3050 Asset Update API Mode H(Initial Cost
            // Calculation)
            callApiAssetUpdate(createBean, NLEB007001CommonLogic.setNLZC305001PMsgRentalShipModeH(createBean, PROC_MODE.EMSD));

            if (this.apiErrMsgList.size() > 0) {
                infoLogOutput(NLEM0024E, new String[] {EMSD_SHIPMENT, INIT_COST_CALUC });
                return;
            }
        }
    }

    private void executionRentalTermOfConvEarlyBuyout(DSAssetStagingInfoBean createBean, int index, int listSize) {

        if (!checkRentalTermOfConvEarlyBuyout(createBean)) {
            return;
        }
    }

    private void executionReturn(DSAssetStagingInfoBean createBean) {

        // START 2017/11/17 J.Kim [QC#17088,ADD]
        CPOTMsg cpoTMsg = NLEB007001Query.getInstance().getOriginalCpoTMsg(createBean);
        if (cpoTMsg == null) {
            return;
        }
        createBean.setSellToCustCd(cpoTMsg.sellToCustCd.getValue());
        createBean.setBillToCustCd(cpoTMsg.billToCustCd.getValue());

        Map<String, Object> dsAssetMstrPkReturnMap = NLEB007001Query.getInstance().getDsAssetMstrPkReturn(createBean);
        if (dsAssetMstrPkReturnMap == null) {
            return;
        }

        BigDecimal dsAssetMstrPk = (BigDecimal) dsAssetMstrPkReturnMap.get("DS_ASSET_MSTR_PK");
        BigDecimal prntDsAssetMstrPk = (BigDecimal) dsAssetMstrPkReturnMap.get("PRNT_DS_ASSET_MSTR_PK");
        // END 2017/11/17 J.Kim [QC#17088,ADD]

        if (INVTY_ACCT.ASSET.equals(createBean.getInvtyAcctCd())) {

            // Check Return Type
            // START 2017/11/17 J.Kim [QC#17088,MOD]
            // String returnTp = NLEB007001Query.getInstance().getReturnType(createBean);
            // if (ZYPCommonFunc.hasValue(returnTp)) {
            // createBean.setPrntDsAssetMstrPk(null);
            // } else {
            // createBean.setPrntDsAssetMstrPk(createBean.getDsAssetMstrPk());
            // }
            if (dsAssetMstrPk.compareTo(prntDsAssetMstrPk) != 0) {
                createBean.setPrntDsAssetMstrPk(null);
            }
            // END 2017/11/17 J.Kim [QC#17088,MOD]

            // NLZC3050 Asset Update API (Mode 2(Return))
            callApiAssetUpdate(createBean, NLEB007001CommonLogic.setNLZC305001PMsgRentalShipModeReturn(createBean));

            if (this.apiErrMsgList.size() > 0) {
                infoLogOutput(NLEM0024E, new String[] {RETURN, MODE_RETURN });
                return;
            }

        } else {
            // Return to Non Asset WH
            if (dsAssetMstrPk.compareTo(prntDsAssetMstrPk) == 0) {
                // Base Component
                // NLZC3050 Asset Update API (Mode 3:Disposal)
                callApiAssetUpdate(createBean, NLEB007001CommonLogic.setNLZC305001PMsgDisposalModeBase(createBean));
                if (this.apiErrMsgList.size() > 0) {
                    infoLogOutput(NLEM0024E, new String[] {RETURN, DISPOASAL });
                    return;
                }

            } else {
                DS_ASSET_MSTRTMsg assetMstrTMsg = NLEB007001Query.getDsAssetMstrPk(glblCmpyCd, dsAssetMstrPk);
                if (assetMstrTMsg == null) {
                    return;
                }
                createBean.setAssetStsCd(assetMstrTMsg.assetStsCd.getValue());
                // Non Base Component
                if (ASSET_STS.ACTIVATE.equals(createBean.getAssetStsCd()) || ASSET_STS.MERGED.equals(createBean.getAssetStsCd())) {
                    // NLZC3050 Asset Update API (Mode 3:Disposal)
                    callApiAssetUpdate(createBean, NLEB007001CommonLogic.setNLZC305001PMsgDisposalModeNonBase(createBean));
                    if (this.apiErrMsgList.size() > 0) {
                        infoLogOutput(NLEM0024E, new String[] {RETURN, DISPOASAL });
                        return;
                    }
                }
            }
        }
    }

    private void executionConfigurationChange(DSAssetStagingInfoBean createBean) {

        if (createBean.getServcConfigMstrPk() != null) {
            // Parent Asset Master
            Map<String, Object> dsAssetMstrToSvcConfigMstrPk = NLEB007001Query.getInstance().getDsAssetMstrParentCc(createBean);
            if (dsAssetMstrToSvcConfigMstrPk == null) {
                infoLogOutput(NLEM0022E, new String[] {CONFIGURATION_CHANGE, DS_ASSET_MSTR });
                return;
            }

            createBean.setPrntDsAssetMstrPk((BigDecimal) dsAssetMstrToSvcConfigMstrPk.get("PRNT_DS_ASSET_MSTR_PK"));
            createBean.setNewPrntDsAssetMstrPk((BigDecimal) dsAssetMstrToSvcConfigMstrPk.get("PRNT_DS_ASSET_MSTR_PK"));
        }

        if (createBean.getDsAssetMstrPk().compareTo(createBean.getPrntDsAssetMstrPk()) == 0) {

            if (createBean.getServcConfigMstrPk() == null) {

                List<Map<String, Object>> dsAssetMstrConfigPkList = NLEB007001Query.getInstance().getAssetGroupingCancelList(createBean);

                for (Map<String, Object> dsAssetMstrConfigPk : dsAssetMstrConfigPkList) {
                    createBean.setDsAssetMstrPk((BigDecimal) dsAssetMstrConfigPk.get("DS_ASSET_MSTR_PK"));
                    createBean.setPrntDsAssetMstrPk(createBean.getDsAssetMstrPk());

                    if (ZYPConstant.FLG_ON_Y.equals(dsAssetMstrConfigPk.get("ASSET_POST_FLG")) && ZYPConstant.FLG_ON_Y.equals(dsAssetMstrConfigPk.get("ACTV_ASSET_FLG"))) {
                        // Mode4(Update)
                        callApiAssetUpdate(createBean, NLEB007001CommonLogic.setNLZC305001PMsgModeUpdate(createBean, PROC_MODE.CONFIGURATION_CHANGE));

                        if (this.apiErrMsgList.size() > 0) {
                            infoLogOutput(NLEM0024E, new String[] {CONFIGURATION_CHANGE, UPDATE });
                            return;
                        }
                    } else if (ZYPConstant.FLG_OFF_N.equals(dsAssetMstrConfigPk.get("ASSET_POST_FLG")) && ZYPConstant.FLG_OFF_N.equals(dsAssetMstrConfigPk.get("ACTV_ASSET_FLG"))) {
                        // ModeC(Before Activate)
                        callApiAssetUpdate(createBean, NLEB007001CommonLogic.setNLZC305001PMsgModeC(createBean, PROC_MODE.CONFIGURATION_CHANGE));

                        if (this.apiErrMsgList.size() > 0) {
                            infoLogOutput(NLEM0024E, new String[] {CONFIGURATION_CHANGE, MODE_UPDATE_BEFORE_ACTIVATE });
                            return;
                        }
                    } else {
                        continue;
                    }
                }

            } else {

                List<Map<String, Object>> dsAssetMstrConfigInfoList = NLEB007001Query.getInstance().getDsAssetMstrConfigInfoList(createBean);

                for (Map<String, Object> dsAssetMstrConfigInfo : dsAssetMstrConfigInfoList) {

                    createBean.setDsAssetMstrPk((BigDecimal) dsAssetMstrConfigInfo.get("DS_ASSET_MSTR_PK"));

                    if (ZYPConstant.FLG_ON_Y.equals(dsAssetMstrConfigInfo.get("ASSET_POST_FLG")) && ZYPConstant.FLG_ON_Y.equals(dsAssetMstrConfigInfo.get("ACTV_ASSET_FLG"))) {
                        // Mode4(Update)
                        callApiAssetUpdate(createBean, NLEB007001CommonLogic.setNLZC305001PMsgModeUpdate(createBean, PROC_MODE.CONFIGURATION_CHANGE));

                        if (this.apiErrMsgList.size() > 0) {
                            infoLogOutput(NLEM0024E, new String[] {CONFIGURATION_CHANGE, UPDATE });
                            return;
                        }
                    } else if (ZYPConstant.FLG_OFF_N.equals(dsAssetMstrConfigInfo.get("ASSET_POST_FLG")) && ZYPConstant.FLG_OFF_N.equals(dsAssetMstrConfigInfo.get("ACTV_ASSET_FLG"))) {
                        // ModeC(Before Activate)
                        callApiAssetUpdate(createBean, NLEB007001CommonLogic.setNLZC305001PMsgModeC(createBean, PROC_MODE.CONFIGURATION_CHANGE));

                        if (this.apiErrMsgList.size() > 0) {
                            infoLogOutput(NLEM0024E, new String[] {CONFIGURATION_CHANGE, MODE_UPDATE_BEFORE_ACTIVATE });
                            return;
                        }
                    } else {
                        continue;
                    }
                }
            }

        } else {

            if (createBean.getServcConfigMstrPk() != null) {
                createBean.setPrntDsAssetMstrPk(createBean.getPrntDsAssetMstrPk());
            } else {
                createBean.setPrntDsAssetMstrPk(createBean.getDsAssetMstrPk());
            }

            if (ZYPConstant.FLG_ON_Y.equals(createBean.getAssetPostFlg()) && ZYPConstant.FLG_ON_Y.equals(createBean.getActvAssetFlg())) {

                // Mode4(Update)
                callApiAssetUpdate(createBean, NLEB007001CommonLogic.setNLZC305001PMsgModeUpdate(createBean, PROC_MODE.CONFIGURATION_CHANGE));

                if (this.apiErrMsgList.size() > 0) {
                    infoLogOutput(NLEM0024E, new String[] {CONFIGURATION_CHANGE, UPDATE });
                    return;
                }
            } else if (ZYPConstant.FLG_OFF_N.equals(createBean.getAssetPostFlg()) && ZYPConstant.FLG_OFF_N.equals(createBean.getActvAssetFlg())) {

                // ModeC(Before Activate)
                callApiAssetUpdate(createBean, NLEB007001CommonLogic.setNLZC305001PMsgModeC(createBean, PROC_MODE.CONFIGURATION_CHANGE));

                if (this.apiErrMsgList.size() > 0) {
                    infoLogOutput(NLEM0024E, new String[] {CONFIGURATION_CHANGE, MODE_UPDATE_BEFORE_ACTIVATE });
                    return;
                }
            } else {
                return;
            }
        }
    }

    private void executionServiceExchange(DSAssetStagingInfoBean createBean, boolean baseCmptFlg) {

        // START 2018/02/19 J.Kim [QC#23431,ADD]
        Map<String, Object> assetMasterInfo = NLEB007001Query.getInstance().getTargetAssetMasterInfo(createBean);
        if (assetMasterInfo == null) {
            infoLogOutput(NLEM0022E, new String[] {SERVICE_EXCHANGE_SHIPMENT, DS_ASSET_MSTR });
            return;
        }

        createBean.setDsAssetMstrPk((BigDecimal) assetMasterInfo.get("DS_ASSET_MSTR_PK"));
        createBean.setPrntDsAssetMstrPk((BigDecimal) assetMasterInfo.get("PRNT_DS_ASSET_MSTR_PK"));

        // NLZC3050 Asset Update API (Mode 7:Update by Service Exchange)
        callApiAssetUpdate(createBean, NLEB007001CommonLogic.setNLZC305001PMsgServiceExchangeMode7(createBean));
        if (this.apiErrMsgList.size() > 0) {
            infoLogOutput(NLEM0024E, new String[] {SERVICE_EXCHANGE_SHIPMENT, UPDATE_SERVICE_EXCHANGE });
            return;
        }
        // END 2018/02/19 J.Kim [QC#23431,ADD]

    }

    private void executionAPInvoice(DSAssetStagingInfoBean createBean) {

        // NLZC3050 Asset Update API (Mode D)
        callApiAssetUpdate(createBean, NLEB007001CommonLogic.setNLZC305001PMsgAPInvoiceModeD(createBean));

        if (this.apiErrMsgList.size() > 0) {
            infoLogOutput(NLEM0024E, new String[] {AP_INVOICE, ASSET_ENTRY_FROM_AP });
            return;
        }
    }

    private void executionAssetAdjustmentOrDisposal(DSAssetStagingInfoBean createBean) {

        // Disposal Asset Master PK
        BigDecimal dsAssetMasterPK = createBean.getDsAssetMstrPk();
        BigDecimal prntDsAssetMasterPK = createBean.getPrntDsAssetMstrPk();

        // Check Parent Asset Master
        if (dsAssetMasterPK.compareTo(prntDsAssetMasterPK) == 0) {
            // Parent Asset
            // 1-2-1. Get Asset Grouping Cancel List
            List<Map<String, Object>> dsAssetMstrGrpCancelList = NLEB007001Query.getInstance().getAssetGroupingCancelList(createBean);

            for (Map<String, Object> dsAssetMstrGrpCancelMap : dsAssetMstrGrpCancelList) {

                // set DS Asset Master PK
                createBean.setDsAssetMstrPk((BigDecimal) dsAssetMstrGrpCancelMap.get("DS_ASSET_MSTR_PK"));
                createBean.setPrntDsAssetMstrPk(createBean.getDsAssetMstrPk());

                // Call NLZC3050 Asset Update API(Mode4 or ModeC)
                if (ZYPConstant.FLG_ON_Y.equals(dsAssetMstrGrpCancelMap.get("ASSET_POST_FLG")) && ZYPConstant.FLG_ON_Y.equals(dsAssetMstrGrpCancelMap.get("ACTV_ASSET_FLG"))) {
                    // Mode4(Update)
                    callApiAssetUpdate(createBean, NLEB007001CommonLogic.setNLZC305001PMsgModeUpdate(createBean, PROC_MODE.DISPOSAL));

                    if (this.apiErrMsgList.size() > 0) {
                        infoLogOutput(NLEM0024E, new String[] {ASSET_ADJUSTOMENT_OR_DISPOSAL, UPDATE });
                        return;
                    }
                } else if (ZYPConstant.FLG_OFF_N.equals(dsAssetMstrGrpCancelMap.get("ASSET_POST_FLG")) && ZYPConstant.FLG_OFF_N.equals(dsAssetMstrGrpCancelMap.get("ACTV_ASSET_FLG"))) {
                    // ModeC(Before Activate)
                    callApiAssetUpdate(createBean, NLEB007001CommonLogic.setNLZC305001PMsgModeC(createBean, PROC_MODE.DISPOSAL));

                    if (this.apiErrMsgList.size() > 0) {
                        infoLogOutput(NLEM0024E, new String[] {ASSET_ADJUSTOMENT_OR_DISPOSAL, MODE_UPDATE_BEFORE_ACTIVATE });
                        return;
                    }
                } else {
                    continue;
                }
            }
        }

        // Set Disposal Asset Master PK
        createBean.setDsAssetMstrPk(dsAssetMasterPK);
        createBean.setPrntDsAssetMstrPk(prntDsAssetMasterPK);

        // NLZC3050 Asset Update API (Mode 3:Disposal)
        callApiAssetUpdate(createBean, NLEB007001CommonLogic.setNLZC305001PMsgDisposal(createBean));

        if (this.apiErrMsgList.size() > 0) {
            infoLogOutput(NLEM0024E, new String[] {ASSET_ADJUSTOMENT_OR_DISPOSAL, DISPOASAL });
            return;
        }
    }

    private boolean checkRentalTermOfConvEarlyBuyout(DSAssetStagingInfoBean bean) {

        // 1-1-1 Get Bill to Customer Code
        INVTMsg outTMsg = NLEB007001Query.getInstance().getINVTMsg(bean.getGlblCmpyCd(), bean.getInvNum());
        if (outTMsg == null) {
            infoLogOutput(NLEM0022E, new String[] {RENTAL_TO_SALES, INV });
            return false;
        }

        // 1-1-2 Get DS Asset master
        Map<String, Object> dsAssetMstPkPostData = NLEB007001Query.getInstance().getDsAssetMstrPkPost(bean);
        if (dsAssetMstPkPostData == null) {
            infoLogOutput(NLEM0022E, new String[] {RENTAL_TO_SALES, DS_ASSET_MSTR });
            return false;
        }

        bean.setBillToCustCd(outTMsg.billToCustCd.getValue());
        bean.setDsAssetMstrPk((BigDecimal) dsAssetMstPkPostData.get("DS_ASSET_MSTR_PK"));
        bean.setPrntDsAssetMstrPk((BigDecimal) dsAssetMstPkPostData.get("DS_ASSET_MSTR_PK"));
        bean.setAssetStsCd((String) dsAssetMstPkPostData.get("ASSET_STS_CD"));
        bean.setBaseCmptFlg((String) dsAssetMstPkPostData.get("BASE_CMPT_FLG"));

        if (!ASSET_STS.ACTIVATE.equals(bean.getAssetStsCd()) && !ASSET_STS.MERGED.equals(bean.getAssetStsCd())) {
            infoLogOutput(NLEM0027E, new String[] {RENTAL_TO_SALES, bean.getAssetStsCd() });
            return false;
        }

        // 1-1-4. NLZC3050 Asset Update API (Mode 1:Revenue)
        callApiAssetUpdate(bean, NLEB007001CommonLogic.setNLZC305001PMsgRentalTermofConversion(bean));

        if (this.apiErrMsgList.size() > 0) {
            infoLogOutput(NLEM0024E, new String[] {RENTAL_TO_SALES, REVENUE_RECOGNITION });
            return false;
        }

        if (ZYPConstant.FLG_ON_Y.equals(bean.getBaseCmptFlg())) {
            this.seDsAssetMstrPkList.add(bean.getDsAssetMstrPk());
        }

        return true;
    }

    /**
     * Create Asset Data(Mode:01(Rental Ship) and Mode:02(EMSD Ship))
     * @param bean
     */
    private void createAssetData(DSAssetStagingInfoBean bean) {
        // CSMP Cash
        bean.setFuncCsmpCrAmt(NLEB007001Query.getInstance().getFuncCsmpCrAmt(bean));

        if (ZYPConstant.FLG_ON_Y.equals(bean.getBaseCmptFlg())) {
            // Adjustment Order
            bean.setCpoDtlFuncNetAmt(NLEB007001Query.getInstance().getCpoDtlFuncNetAmt(bean));
        }
    }

    private void callApiAssetUpdate(DSAssetStagingInfoBean bean, NLZC305001PMsg pMsg) {
        new NLZC305001().execute(pMsg, this.batchType);
        setApiErrMsgList(pMsg);
        bean.setSeApiRsDsAssetMstrPk(pMsg.updDtlList.no(0).dsAssetMstrPk.getValue());
    }

    private void infoLogOutput(String msgId, String[] params) {
        setInfoLogOutput(msgId, params);
        rollback();
    }

    private void setInfoLogOutput(String msgId, String[] params) {
        S21InfoLogOutput.println(msgId, params);
        this.msgList.add(S21MessageFunc.clspGetMessage(msgId, params));
        this.apiErrMsgList.add(msgId);
        if (this.apiErrMsgMap.size() > 0) {
            String apiMsgId = this.apiErrMsgList.get(0);
            S21InfoLogOutput.println(apiMsgId, this.apiErrMsgMap.get(apiMsgId));
            this.msgList.add(S21MessageFunc.clspGetMessage(apiMsgId, this.apiErrMsgMap.get(apiMsgId)));
            this.apiErrMsgMap.clear();
        }
    }

    private void updateDSAssetStaging(List<DSAssetStagingInfoBean> beanList) {

        List<DS_ASSET_STGNGTMsg> inTMsgList = new ArrayList<DS_ASSET_STGNGTMsg>();
        for (DSAssetStagingInfoBean bean : beanList) {
            DS_ASSET_STGNGTMsg inTMsg = new DS_ASSET_STGNGTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(inTMsg.dsAssetStgngPk, bean.getDsAssetStgngPk());
            DS_ASSET_STGNGTMsg outTMsg = (DS_ASSET_STGNGTMsg) S21FastTBLAccessor.findByKeyForUpdate(inTMsg);
            if (outTMsg != null) {
                ZYPEZDItemValueSetter.setValue(outTMsg.dsAssetStgngStsCd, DS_ASSET_STGNG_STS.COMPLETED);
                inTMsgList.add(outTMsg);
            }
        }

        DS_ASSET_STGNGTMsg[] inTMsgArray = new DS_ASSET_STGNGTMsg[beanList.size()];
        int updateCont = S21FastTBLAccessor.update(inTMsgList.toArray(inTMsgArray));

        if (updateCont != inTMsgArray.length) {
            this.errorCont += (inTMsgArray.length - updateCont);
            rollback();
        } else {
            this.normalCount += updateCont;
            commit();
        }
    }

    private void updateDSAssetStaging(DSAssetStagingInfoBean bean) {

        DS_ASSET_STGNGTMsg inTMsg = new DS_ASSET_STGNGTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(inTMsg.dsAssetStgngPk, bean.getDsAssetStgngPk());
        DS_ASSET_STGNGTMsg outTMsg = (DS_ASSET_STGNGTMsg) S21FastTBLAccessor.findByKeyForUpdate(inTMsg);
        if (outTMsg != null) {
            ZYPEZDItemValueSetter.setValue(outTMsg.dsAssetStgngStsCd, DS_ASSET_STGNG_STS.COMPLETED);
            EZDTBLAccessor.update(outTMsg);
        }

        if (outTMsg == null || outTMsg.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
            this.errorCont++;
            rollback();
        } else {
            this.normalCount++;
            commit();
        }
    }

    private boolean setApiErrMsgList(EZDPMsg apiPMsg) {

        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<S21ApiMessage> xxMsgIdList = S21ApiUtil.getXxMsgList(apiPMsg);
            S21ApiMessage msg = xxMsgIdList.get(0);
            this.apiErrMsgList.add(msg.getXxMsgid());
            this.apiErrMsgMap.put(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            return false;
        }
        return true;
    }
}
