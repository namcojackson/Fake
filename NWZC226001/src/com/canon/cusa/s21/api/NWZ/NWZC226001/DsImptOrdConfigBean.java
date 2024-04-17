/**
 * <Pre>Copyright (c) 2016-2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC226001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.common.S21StringUtil;

import parts.common.EZDMsg;
import parts.common.EZDTStringItem;

import business.db.DS_CPO_CONFIGTMsg;
import business.db.DS_IMPT_ORD_CONFIGTMsg;
import business.db.DS_IMPT_ORD_CTAC_PSNTMsg;
import business.db.DS_IMPT_ORD_DELY_INFOTMsg;
import business.db.DS_IMPT_ORD_ISTL_INFOTMsg;
import business.db.DS_IMPT_ORD_SITE_SRVYTMsg;
import business.db.DS_IMPT_ORD_SLS_CRTMsg;

/** <pre>
 * DsImptOrdConfigBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/17/2016   Fujitsu         M.Hara          Create          N/A
 * 2017/09/08   Fujitsu         S.Iidaka        Update          S21_NA#20992
 * 2018/01/23   Fujitsu         T.Aoi           Update          S21_NA#18798(Sol#173)
 * </pre>
 */
public class DsImptOrdConfigBean extends DS_IMPT_ORD_CONFIGTMsg implements IImportBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // *************************************************************************
    // Extention Field
    // *************************************************************************
    public final ImptHdrBean imptHdrBean;
    public final List<DsImptLineBean> dsImptOrdLineList;
    public final List<DsImptRtnLineBean> dsImptRtnLineList;
    /** Sales Credit(Config Level) */
    public final List<DS_IMPT_ORD_SLS_CRTMsg> dsImptSlsCrList;

    /** Delivery Data(Config Level) */
    public final List<DS_IMPT_ORD_DELY_INFOTMsg> dsImptDelyList;

    /** Site Survey Data(Config Level) */
    public final List<DS_IMPT_ORD_SITE_SRVYTMsg> dsImptSiteSrvyList;

    /** Install Data(Config Level) */
    public final List<DS_IMPT_ORD_ISTL_INFOTMsg> dsImptInstList;

    /** Contact Person Data(Config Level) */
    public final  List<DS_IMPT_ORD_CTAC_PSNTMsg> dsImptCtacPsnList;

    public final List<DsImptOrdErrBean> dsImptOrdErrList;

    public final DS_CPO_CONFIGTMsg origDsCpoConfTMsg;

    public String slsRepTocCd;
    public String coaBrCd;
    public String coaExtnCd;
    public boolean isOrigConfig = false;

    // 2018/01/23 QC#18798 Add Start
    public String origDsOrdPosnNum;
    // 2018/01/23 QC#18798 Add End

    public DsImptOrdConfigBean(ImptHdrBean hedBean, DS_IMPT_ORD_CONFIGTMsg baseTMsg) {
        this(hedBean, baseTMsg, false, -1, null);
    }

    public DsImptOrdConfigBean(ImptHdrBean hedBean, DS_IMPT_ORD_CONFIGTMsg baseTMsg, DS_CPO_CONFIGTMsg origConfTMsg) {
        this(hedBean, baseTMsg, false, -1, origConfTMsg);
    }

    public DsImptOrdConfigBean(ImptHdrBean hdrBean, DS_IMPT_ORD_CONFIGTMsg baseTMsg, boolean child, int eleNo, DS_CPO_CONFIGTMsg origConfTMsg) {
        super(child, eleNo);

        EZDMsg.copy(baseTMsg, null, this, null);
        imptHdrBean = hdrBean;
        dsImptOrdLineList = new ArrayList<DsImptLineBean>();
        dsImptRtnLineList = new ArrayList<DsImptRtnLineBean>();
        dsImptSlsCrList = new ArrayList<DS_IMPT_ORD_SLS_CRTMsg>();
        dsImptDelyList = new ArrayList<DS_IMPT_ORD_DELY_INFOTMsg>();
        dsImptSiteSrvyList = new ArrayList<DS_IMPT_ORD_SITE_SRVYTMsg>();
        dsImptInstList = new ArrayList<DS_IMPT_ORD_ISTL_INFOTMsg>();
        dsImptCtacPsnList = new ArrayList<DS_IMPT_ORD_CTAC_PSNTMsg>();

        dsImptOrdErrList = new ArrayList<DsImptOrdErrBean>();
        origDsCpoConfTMsg = origConfTMsg;
    }

    @Override
    public List<DsImptOrdErrBean> getDsImptOrdErrList() {
        return dsImptOrdErrList;
    }

    @Override
    public boolean hasError(boolean doSearchChild) {
        if (this.dsImptOrdErrList.size() > 0) {
            return true;
        }

        if (doSearchChild) {
            for (DsImptLineBean lineBean : dsImptOrdLineList) {
                if (lineBean.hasError(true)) {
                    return true;
                }
            }

            for (DsImptRtnLineBean rtnBean : dsImptRtnLineList) {
                if (rtnBean.hasError(true)) {
                    return true;
                }
            }

        }

        return false;
    }

    @Override
    public DsImptOrdConfigBean getDsImptOrdConfigBean() {
        return this;
    }

    @Override
    public boolean isSameErrorExists(DsImptOrdErrBean dsImptOrdErrBean) {

        if (ZYPCommonFunc.hasValue(dsImptOrdErrBean.dsImptOrdConfigPk)) {

            for (DsImptOrdErrBean dsImptOrdErr : this.dsImptOrdErrList) {

                if (dsImptOrdErrBean.dsImptOrdConfigPk.getValue().compareTo(dsImptOrdErr.dsImptOrdConfigPk.getValue()) != 0) {

                    continue;
                }

                if (S21StringUtil.isEquals(dsImptOrdErrBean.dsImptOrdErrTxt.getValue(), dsImptOrdErr.dsImptOrdErrTxt.getValue())) {

                    return true;
                }
            }
        }
        return false;
    }

    public boolean isOutbound() {
        return (CONFIG_CATG.OUTBOUND.equals(this.configCatgCd.getValue()));
    }

    public boolean isUpdateData() {
        return this.origDsCpoConfTMsg != null;
    }

    public boolean isExistValidatedSts() {
        return this.isUpdateData() && imptHdrBean.isExistValidatedSts();
    }

    public ExpendMdseBean getBaseComponetMdse() {
        for (DsImptLineBean lineBean : this.dsImptOrdLineList) {
            for (ExpendMdseBean mdseBean : lineBean.mdseAllList) {
                if (ZYPConstant.FLG_ON_Y.equals(mdseBean.getInstlBaseCtrlFlg())) {
                    return mdseBean;
                }
            }
        }

        return null;
    }

    public boolean hasBaseComponent() {
        return (getBaseComponetMdse() != null);
    }


    // 2017/09/08 S21_NA#20992 Mod Start
    public boolean hasMdse(String mdseCd, boolean check8Len, String dsOrdLineCatgCd) {
        String checkMdse, expndMdse, expndDsOrdLineCatgCd;

        // 2017/09/08 S21_NA#20992 Mod End

        if (check8Len && ZYPCommonFunc.hasValue(mdseCd) && mdseCd.length() > 8) {
            checkMdse = mdseCd.substring(0, 8);
        } else {
            checkMdse = mdseCd;
        }
        for (DsImptLineBean lineBean : this.dsImptOrdLineList) {
            for (ExpendMdseBean mdseBean : lineBean.mdseAllList) {
                expndMdse = mdseBean.getChildMdseCd();

                // 2017/09/08 S21_NA#20992 Mod Start
                expndDsOrdLineCatgCd = lineBean.dsOrdLineCatgCd.getValue();
                if (check8Len && ZYPCommonFunc.hasValue(expndMdse) && expndMdse.length() > 8) {
                    expndMdse = expndMdse.substring(0, 8);
                }
                if (expndMdse.equals(checkMdse) && S21StringUtil.isEquals(expndDsOrdLineCatgCd, dsOrdLineCatgCd)) {
                    return true;
                }
                // 2017/09/08 S21_NA#20992 Mod Start
            }
        }

        return false;
    }

   public boolean hasShipToCustInfo() {

       List<EZDTStringItem> shipToCustItemList = Arrays.asList(
               super.shipToLocNm
               , super.shipToAddlLocNm
               , super.shipToFirstLineAddr
               , super.shipToScdLineAddr
               , super.shipToThirdLineAddr
               , super.shipToFrthLineAddr
               , super.shipToFirstRefCmntTxt
               , super.shipToScdRefCmntTxt
               , super.shipToCtyAddr
               , super.shipToStCd
               , super.shipToProvNm
               , super.shipToCntyNm
               , super.shipToPostCd
               , super.shipToCtryCd
               );

       for (EZDTStringItem item : shipToCustItemList) {
           if (ZYPCommonFunc.hasValue(item)) {
               return true;
           }
       }

       return false;
   }

   public List<DsImptRtnLineBean> findRtnBeanListByMdseCdAndSerNum(String mdseCd, String serNum) {
       List<DsImptRtnLineBean> retList = new ArrayList<DsImptRtnLineBean>();

       if (!this.isOutbound()) {
           for (DsImptRtnLineBean rtnBean : this.dsImptRtnLineList) {
               if (S21StringUtil.isEquals(mdseCd, rtnBean.mdseCd.getValue()) && S21StringUtil.isEquals(serNum, rtnBean.serNum.getValue())) {
                   retList.add(rtnBean);
               }
           }
       }

       return retList;
   }

}
