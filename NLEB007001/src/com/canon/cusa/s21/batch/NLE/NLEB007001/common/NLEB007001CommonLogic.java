/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLE.NLEB007001.common;

import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.*;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.ASSET_ENTRY_FROM_AP;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.DISPOASAL;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.INIT_COST_CALUC;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PENDING_ASSET_ENTRY;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.REVENUE_RECOGNITION;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.SHIP_FROM_ASSET_WH;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NLZC305001PMsg;

import com.canon.cusa.s21.batch.NLE.NLEB007001.DSAssetStagingInfoBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_MODE;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Hitachi         J.Kim           Create          N/A
 * 2016/04/12   Hitachi         J.Kim           Update          QC#6869
 * 2016/04/14   Hitachi         J.Kim           Update          QC#7038
 * 2016/04/26   Hitachi         T.Tsuchida      Update          QC#7605
 * 2016/06/01   Hitachi         T.Tsuchida      Update          QC#9229
 * 2016/06/06   Hitachi         T.Tsuchida      Update          QC#9451
 * 2016/07/01   Hitachi         J.Kim           Update          QC#11310
 * 2016/07/08   Hitachi         J.Kim           Update          QC#11627
 * 2016/07/12   Hitachi         J.Kim           Update          QC#11691
 * 2017/11/17   Hitachi         J.Kim           Update          QC#17088
 * 2018/02/08   Hitachi         J.Kim           Update          QC#24024
 * 2018/07/24   Hitachi         J.Kim           Update          QC#24950
 *</pre>
 */
public class NLEB007001CommonLogic implements ZYPConstant {

    /**
     * DSAssetStagingInfoBean
     * @param rs ResultSet
     * @return DSAssetStagingInfoBean
     * @throws SQLException exception
     */
    public static DSAssetStagingInfoBean setDsAssetStagingInfoBean(ResultSet rs) throws SQLException {

        DSAssetStagingInfoBean bean = new DSAssetStagingInfoBean();
        bean.setRowNumber(rs.getBigDecimal("ROW_NUMBER"));
        bean.setGlblCmpyCd(rs.getString("GLBL_CMPY_CD"));
        bean.setDsAssetStgngPk(rs.getBigDecimal("DS_ASSET_STGNG_PK"));
        bean.setFromSvcConfigMstrPk(rs.getBigDecimal("FROM_SVC_CONFIG_MSTR_PK"));
        bean.setServcConfigMstrPk(rs.getBigDecimal("SERVC_CONFIG_MSTR_PK"));
        bean.setProcModeCd(rs.getString("PROC_MODE_CD"));
        bean.setMdseCd(rs.getString("MDSE_CD"));
        bean.setSerNum(rs.getString("SER_NUM"));
        bean.setSvcMachMstrPk(rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        bean.setBaseCmptFlg(rs.getString("BASE_CMPT_FLG"));
        bean.setAssetTpCd(rs.getString("ASSET_TP_CD"));
        bean.setShpgPlnNum(rs.getString("SHPG_PLN_NUM"));
        bean.setInvtyTrxPk(rs.getBigDecimal("INVTY_TRX_PK"));
        bean.setCpoOrdNum(rs.getString("CPO_ORD_NUM"));
        bean.setCpoDtlLineNum(rs.getString("CPO_DTL_LINE_NUM"));
        bean.setCpoDtlLineSubNum(rs.getString("CPO_DTL_LINE_SUB_NUM"));
        bean.setDsOrdPosnNum(rs.getString("DS_ORD_POSN_NUM"));
        bean.setShipToCustAcctCd(rs.getString("SHIP_TO_CUST_ACCT_CD"));
        bean.setShipToCustCd(rs.getString("SHIP_TO_CUST_CD"));
        bean.setSellToCustCd(rs.getString("SELL_TO_CUST_CD"));
        bean.setSoldToCustLocCd(rs.getString("SOLD_TO_CUST_LOC_CD"));
        bean.setInvNum(rs.getString("INV_NUM"));
        if (ZYPCommonFunc.hasValue(rs.getString("INV_DT"))) {
            bean.setInvDt(rs.getString("INV_DT"));
        } else {
            bean.setInvDt(ZYPDateUtil.getSalesDate());
        }
        bean.setRtnWhCd(rs.getString("RTN_WH_CD"));
        bean.setSlsRepTocCd(rs.getString("SLS_REP_TOC_CD"));
        bean.setStdCostAmt(rs.getBigDecimal("STD_COST_AMT"));
        bean.setSvcConfigMstrPk(rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
        bean.setEzintime(rs.getString("EZINTIME"));
        bean.setDsAssetStGngStsCd(rs.getString("DS_ASSET_STGNG_STS_CD"));
        bean.setDsAssetMstrPk(rs.getBigDecimal("DS_ASSET_MSTR_PK"));
        bean.setCmt(rs.getString("CMT"));
        return bean;
    }

    /**
     * editDSAssetStagingInfoList
     * @param infoListBean List<DSAssetStagingInfoBean>
     * @return Map<Integer, List<DSAssetStagingInfoBean>>
     */
    public static Map<Integer, List<DSAssetStagingInfoBean>> editDSAssetStagingInfoList(List<DSAssetStagingInfoBean> infoListBean) {

        List<DSAssetStagingInfoBean> dsAssetStagingInfo = new ArrayList<DSAssetStagingInfoBean>();
        Map<Integer, List<DSAssetStagingInfoBean>> dSAssetStagingInfoMapList = new HashMap<Integer, List<DSAssetStagingInfoBean>>();

        int index = 0;
        String tmpCmt = "";
        for (int count = 0; count < infoListBean.size(); count++) {

            DSAssetStagingInfoBean bean = infoListBean.get(count);
            String cmt = bean.getCmt();
            if ((!tmpCmt.isEmpty() && !tmpCmt.equals(cmt))) {
                dSAssetStagingInfoMapList.put(index, dsAssetStagingInfo);
                dsAssetStagingInfo = new ArrayList<DSAssetStagingInfoBean>();
                index++;
            }

            dsAssetStagingInfo.add(bean);
            if (count == (infoListBean.size() - 1)) {
                dSAssetStagingInfoMapList.put(index, dsAssetStagingInfo);
                dsAssetStagingInfo = new ArrayList<DSAssetStagingInfoBean>();
            }

            if (count != (infoListBean.size() - 1)) {
                tmpCmt = bean.getCmt();
            }
        }
        return dSAssetStagingInfoMapList;
    }

    /**
     * Service Exchange Mode6
     * @param bean DSAssetStagingInfoBean
     * @return NLZC305001PMsg
     */
    public static NLZC305001PMsg setNLZC305001PMsgServiceExchangeMode6(DSAssetStagingInfoBean bean) {
        NLZC305001PMsg pMsg = new NLZC305001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt,ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, SHIP_FROM_ASSET_WH);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetMstrPk, bean.getDsAssetMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetStgngPk, bean.getDsAssetStgngPk());
        pMsg.updDtlList.setValidCount(1);
        return pMsg;
    }

    // START 2017/12/04 J.Kim [QC#18127,ADD]
    /**
     * Service Exchange Mode7
     * @param bean DSAssetStagingInfoBean
     * @return NLZC305001PMsg
     */
    public static NLZC305001PMsg setNLZC305001PMsgServiceExchangeMode7(DSAssetStagingInfoBean bean) {
        NLZC305001PMsg pMsg = new NLZC305001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, UPDATE_SERVICE_EXCHANGE);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetMstrPk, bean.getDsAssetMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).prntDsAssetMstrPk, bean.getPrntDsAssetMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetStgngPk, bean.getDsAssetStgngPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).svcMachMstrPk, bean.getSmmSvcMachMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).mdseCd, bean.getSmmMdseCd());
        // START 2018/07/23 J.Kim [QC#24950,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, PROC_MODE.SERVICE_EXCHANGE);
        // END 2018/07/23 J.Kim [QC#24950,ADD]
        pMsg.updDtlList.setValidCount(1);
        return pMsg;
    }
    // END 2017/12/04 J.Kim [QC#18127,ADD]

    /**
     * Rental Ship Mode A
     * @param bean DSAssetStagingInfoBean
     * @return NLZC305001PMsg
     */
    public static NLZC305001PMsg setNLZC305001PMsgRentalShipModeA(DSAssetStagingInfoBean bean) {
        NLZC305001PMsg pMsg = new NLZC305001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bean.getInvDt());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, PENDING_ASSET_ENTRY);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetStgngPk, bean.getDsAssetStgngPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).prntDsAssetMstrPk, bean.getPrntDsAssetMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetTpCd, ASSET_TP.RENTAL_ASSET);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).svcMachMstrPk, bean.getSmmSvcMachMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).ordAdjAmt, bean.getCpoDtlFuncNetAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).csmpPrcAmt, bean.getFuncCsmpCrAmt());
        // START 2018/07/23 J.Kim [QC#24950,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, PROC_MODE.RENTAL);
        // END 2018/07/23 J.Kim [QC#24950,ADD]
        pMsg.updDtlList.setValidCount(1);
        return pMsg;
    }

    /**
     * Rental Ship Mode H
     * @param bean DSAssetStagingInfoBean
     * @return NLZC305001PMsg
     */
    public static NLZC305001PMsg setNLZC305001PMsgRentalShipModeH(DSAssetStagingInfoBean bean, String procMode) {
        NLZC305001PMsg pMsg = new NLZC305001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bean.getInvDt());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, INIT_COST_CALUC);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).prntDsAssetMstrPk, bean.getPrntDsAssetMstrPk());
        // START 2018/07/23 J.Kim [QC#24950,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, procMode);
        // END 2018/07/23 J.Kim [QC#24950,ADD]
        pMsg.updDtlList.setValidCount(1);
        return pMsg;
    }

    /**
     * EMSD Shipping Mode A
     * @param bean DSAssetStagingInfoBean
     * @return NLZC305001PMsg
     */
    public static NLZC305001PMsg setNLZC305001PMsgEMSDShippingModeA(DSAssetStagingInfoBean bean) {
        NLZC305001PMsg pMsg = new NLZC305001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bean.getInvDt());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, PENDING_ASSET_ENTRY);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetStgngPk, bean.getDsAssetStgngPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).prntDsAssetMstrPk, bean.getPrntDsAssetMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetTpCd, ASSET_TP.EMSD_ASSET);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).svcMachMstrPk, bean.getSmmSvcMachMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).ordAdjAmt, bean.getCpoDtlFuncNetAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).csmpPrcAmt, bean.getFuncCsmpCrAmt());
        // START 2018/07/23 J.Kim [QC#24950,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, PROC_MODE.EMSD);
        // END 2018/07/23 J.Kim [QC#24950,ADD]
        pMsg.updDtlList.setValidCount(1);
        return pMsg;
    }

    /**
     * EMSD Shipping Mode6
     * @param bean DSAssetStagingInfoBean
     * @return NLZC305001PMsg
     */
    public static NLZC305001PMsg setNLZC305001PMsgEMSDShippingMode6(DSAssetStagingInfoBean bean) {
        NLZC305001PMsg pMsg = new NLZC305001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, SHIP_FROM_ASSET_WH);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetMstrPk, bean.getDsAssetMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetStgngPk, bean.getDsAssetStgngPk());
        // START 2018/07/23 J.Kim [QC#24950,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, PROC_MODE.EMSD);
        // END 2018/07/23 J.Kim [QC#24950,ADD]
        pMsg.updDtlList.setValidCount(1);
        return pMsg;
    }

    /**
     * Rental Term Of Conversion
     * @param bean DSAssetStagingInfoBean
     * @return NLZC305001PMsg
     */
    public static NLZC305001PMsg setNLZC305001PMsgRentalTermofConversion(DSAssetStagingInfoBean bean) {
        NLZC305001PMsg pMsg = new NLZC305001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, REVENUE_RECOGNITION);
        // START 2018/02/08 J.Kim [QC#24024,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetStgngPk, bean.getDsAssetStgngPk());
        // END 2018/02/08 J.Kim [QC#24024,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetMstrPk, bean.getDsAssetMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).slsRepTocCd, bean.getSlsRepTocCd());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).billToCustCd, bean.getBillToCustCd());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).invNum, bean.getInvNum());
        // START 2018/07/23 J.Kim [QC#24950,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, PROC_MODE.RENTAL_TO_SALES);
        // END 2018/07/23 J.Kim [QC#24950,ADD]
        pMsg.updDtlList.setValidCount(1);
        return pMsg;
    }

    /**
     * Asset WH Return Mode 2
     * @param bean DSAssetStagingInfoBean
     * @return NLZC305001PMsg
     */
    public static NLZC305001PMsg setNLZC305001PMsgRentalShipModeReturn(DSAssetStagingInfoBean bean) {
        NLZC305001PMsg pMsg = new NLZC305001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bean.getInvDt());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, MODE_RETURN);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetMstrPk, bean.getDsAssetMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetStgngPk, bean.getDsAssetStgngPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).prntDsAssetMstrPk, bean.getPrntDsAssetMstrPk());
        // START 2017/11/17 J.Kim [QC#17088,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).sellToCustCd, bean.getSellToCustCd());
        // END 2017/11/17 J.Kim [QC#17088,ADD]
        // START 2018/07/23 J.Kim [QC#24950,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, PROC_MODE.RETURN_OR_RETIRE);
        // END 2018/07/23 J.Kim [QC#24950,ADD]
        pMsg.updDtlList.setValidCount(1);
        return pMsg;
    }

    /**
     * Disposal
     * @param bean DSAssetStagingInfoBean
     * @return NLZC305001PMsg
     */
    public static NLZC305001PMsg setNLZC305001PMsgDisposalModeBase(DSAssetStagingInfoBean bean) {
        NLZC305001PMsg pMsg = new NLZC305001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, DISPOASAL);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetStgngPk, bean.getDsAssetStgngPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).prntDsAssetMstrPk, bean.getDsAssetMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).slsRepTocCd, bean.getSlsRepTocCd());
        // START 2017/11/17 J.Kim [QC#17088,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).sellToCustCd, bean.getSellToCustCd());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).billToCustCd, bean.getBillToCustCd());
        // END 2017/11/17 J.Kim [QC#17088,ADD]
        // START 2018/07/23 J.Kim [QC#24950,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, PROC_MODE.DISPOSAL);
        // END 2018/07/23 J.Kim [QC#24950,ADD]
        pMsg.updDtlList.setValidCount(1);
        return pMsg;
    }

    /**
     * Disposal
     * @param bean DSAssetStagingInfoBean
     * @return NLZC305001PMsg
     */
    public static NLZC305001PMsg setNLZC305001PMsgDisposalModeNonBase(DSAssetStagingInfoBean bean) {
        NLZC305001PMsg pMsg = new NLZC305001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, DISPOASAL);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetStgngPk, bean.getDsAssetStgngPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetMstrPk, bean.getDsAssetMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).slsRepTocCd, bean.getSlsRepTocCd());
        // START 2017/11/17 J.Kim [QC#17088,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).sellToCustCd, bean.getSellToCustCd());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).billToCustCd, bean.getBillToCustCd());
        // END 2017/11/17 J.Kim [QC#17088,ADD]
        // START 2018/07/23 J.Kim [QC#24950,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, PROC_MODE.DISPOSAL);
        // END 2018/07/23 J.Kim [QC#24950,ADD]
        pMsg.updDtlList.setValidCount(1);
        return pMsg;
    }

    /**
     * Update
     * @param bean DSAssetStagingInfoBean
     * @return NLZC305001PMsg
     */
    public static NLZC305001PMsg setNLZC305001PMsgModeUpdate(DSAssetStagingInfoBean bean, String procMode) {
        NLZC305001PMsg pMsg = new NLZC305001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, UPDATE);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetMstrPk, bean.getDsAssetMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).prntDsAssetMstrPk, bean.getPrntDsAssetMstrPk());
        // START 2018/07/23 J.Kim [QC#24950,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, procMode);
        // END 2018/07/23 J.Kim [QC#24950,ADD]
        pMsg.updDtlList.setValidCount(1);
        return pMsg;
    }

    /**
     * Update Before Activate
     * @param bean DSAssetStagingInfoBean
     * @return NLZC305001PMsg
     */
    public static NLZC305001PMsg setNLZC305001PMsgModeC(DSAssetStagingInfoBean bean, String procMode) {
        NLZC305001PMsg pMsg = new NLZC305001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, MODE_UPDATE_BEFORE_ACTIVATE);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).prntDsAssetMstrPk, bean.getPrntDsAssetMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetMstrPk, bean.getDsAssetMstrPk());
        // START 2018/07/23 J.Kim [QC#24950,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, procMode);
        // END 2018/07/23 J.Kim [QC#24950,ADD]
        pMsg.updDtlList.setValidCount(1);
        return pMsg;
    }
    
    /**
     * Ap Invoice Mode D
     * @param bean DSAssetStagingInfoBean
     * @return NLZC305001PMsg
     */
    public static NLZC305001PMsg setNLZC305001PMsgAPInvoiceModeD(DSAssetStagingInfoBean bean) {
        NLZC305001PMsg pMsg = new NLZC305001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, ASSET_ENTRY_FROM_AP);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetStgngPk, bean.getDsAssetStgngPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).mdseCd, bean.getMdseCd());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetTpCd, ASSET_TP.EMSD_ASSET);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).slsRepTocCd, bean.getSlsRepTocCd());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).curValAmt, bean.getStdCostAmt());
        // START 2018/07/23 J.Kim [QC#24950,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, PROC_MODE.AP_INVOICE);
        // END 2018/07/23 J.Kim [QC#24950,ADD]
        pMsg.updDtlList.setValidCount(1);
        return pMsg;
    }

    /**
     * Service Exchange Shipping Mode A
     * @param bean DSAssetStagingInfoBean
     * @param assetTpCd String
     * @return NLZC305001PMsg
     */
    public static NLZC305001PMsg setNLZC305001PMsgSvcExModeA(DSAssetStagingInfoBean bean, String assetTpCd) {
        NLZC305001PMsg pMsg = new NLZC305001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, PENDING_ASSET_ENTRY);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetStgngPk, bean.getDsAssetStgngPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).prntDsAssetMstrPk, bean.getPrntDsAssetMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetTpCd, assetTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).svcMachMstrPk, bean.getSvcMachMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).ordAdjAmt, bean.getCpoDtlFuncNetAmt());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).csmpPrcAmt, bean.getFuncCsmpCrAmt());
        pMsg.updDtlList.setValidCount(1);
        return pMsg;
    }

    /**
     * Disposal
     * @param bean DSAssetStagingInfoBean
     * @return NLZC305001PMsg
     */
    public static NLZC305001PMsg setNLZC305001PMsgDisposal(DSAssetStagingInfoBean bean) {
        NLZC305001PMsg pMsg = new NLZC305001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, DISPOASAL);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetMstrPk, bean.getDsAssetMstrPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetStgngPk, bean.getDsAssetStgngPk());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).slsRepTocCd, bean.getSlsRepTocCd());
        // START 2018/07/23 J.Kim [QC#24950,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, PROC_MODE.DISPOSAL);
        // END 2018/07/23 J.Kim [QC#24950,ADD]
        pMsg.updDtlList.setValidCount(1);
        return pMsg;
    }
}
