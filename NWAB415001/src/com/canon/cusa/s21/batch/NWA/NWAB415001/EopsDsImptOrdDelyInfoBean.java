/*
 * <Pre>Copyright (c) 2017-2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB415001;

import java.math.BigDecimal;

import business.db.DS_IMPT_ORD_DELY_INFOTMsg;

import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.CUTOFF_LEN;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.FLG;
import static com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.TIME_START_POS;
import static com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.TIME_END_POS;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_DELY_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * EOPS Interface to S21 Import Data Batch
 * EopsDsImptOrdDelyInfoBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 * 2018/01/22   Fujitsu         T.Aoi           Update          QC#18460
 *</pre>
 */
public class EopsDsImptOrdDelyInfoBean extends DS_IMPT_ORD_DELY_INFOTMsg implements IEopsDsImptOrd {

    /**  */
    private static final long serialVersionUID = 1L;

    /**  */
    public final NWAB415001Constant.DELY_INFO_TYPE delyInfoType;

    /**  */
    public final EopsDsImptOrdBean dsImptOrdBean;

    /**  */
    public final EopsDsImptOrdConfigBean dsImptOrdConfigBean;

    /**
     * EopsDsImptOrdDelyInfoBean
     * @param delyInfoType          NWAB415001Constant.DELY_INFO_TYPE
     * @param dsImptOrdConfigBean   EopsDsImptOrdConfigBean
     */
    public EopsDsImptOrdDelyInfoBean(NWAB415001Constant.DELY_INFO_TYPE delyInfoType, EopsDsImptOrdConfigBean dsImptOrdConfigBean) {
        super();

        this.delyInfoType = delyInfoType;
        this.dsImptOrdBean = dsImptOrdConfigBean.dsImptOrdBean;
        this.dsImptOrdConfigBean = dsImptOrdConfigBean;
        dsImptOrdConfigBean.dsImptOrdDelyInfoBean = this;
    }

    /**
     * EopsDsImptOrdDelyInfoBean
     * @param delyInfoType  NWAB415001Constant.DELY_INFO_TYPE
     * @param dsImptOrdBean EopsDsImptOrdBean
     */
    public EopsDsImptOrdDelyInfoBean(NWAB415001Constant.DELY_INFO_TYPE delyInfoType, EopsDsImptOrdBean dsImptOrdBean) {
        super();

        this.delyInfoType = delyInfoType;
        this.dsImptOrdBean = dsImptOrdBean;
        this.dsImptOrdConfigBean = null;
        dsImptOrdBean.dsImptOrdDelyInfoBean = this;
    }

    /**
     * doImptMapping
     * @param glblCmpyCd    glblCmpyCd
     * @param salesDate     salesDate
     * @return boolean
     */
    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        String rddDt = null;
        String opsHourMn = null;
        String opsFromHourMn = null;
        String opsToHourMn = null;
        String loadDockAvalFlg = null;
        String elevAvalFlg = null;

        //        String firstNm = null;
        String instnTxt = null;
        //        BigDecimal stepCnt = null;
        if (NWAB415001Constant.DELY_INFO_TYPE.HEADER_DELY_INFO.equals(this.delyInfoType)) {

            ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdDelyInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_DELY_INFO_SQ));
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdBean.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(this.dsDelyTpCd, DS_DELY_TP.INSTALLATION);

            rddDt = NWXC412001.getValidDate(this.dsImptOrdBean.nwai415001.erlstDelyTs.getValue());
            opsHourMn = this.dsImptOrdBean.nwai415001.hrsOpTxt.getValue();
            String[] opsHourMnFromTo = NWXC412001.divideTimeFromTo(opsHourMn);
            if (ZYPCommonFunc.hasValue(opsHourMnFromTo[0])) {
                opsFromHourMn = opsHourMnFromTo[0];
            }
            if (ZYPCommonFunc.hasValue(opsHourMnFromTo[1])) {
                opsToHourMn = opsHourMnFromTo[1];
            }
            if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.nwai415001.elevIndSomTxt) //
                    && FLG.YES.name().equals(this.dsImptOrdBean.nwai415001.elevIndSomTxt.getValue())) {
                elevAvalFlg = ZYPConstant.FLG_ON_Y;
            } else {
                elevAvalFlg = ZYPConstant.FLG_OFF_N;
            }
            if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.nwai415001.loadDockIndSomTxt) //
                    && FLG.YES.name().equals(this.dsImptOrdBean.nwai415001.loadDockIndSomTxt.getValue())) {
                loadDockAvalFlg = ZYPConstant.FLG_ON_Y;
            } else {
                loadDockAvalFlg = ZYPConstant.FLG_OFF_N;
            }
            //            firstNm = this.dsImptOrdBean.nwai4150_01.delyCtacFirstNm.getValue();
            instnTxt = this.dsImptOrdBean.nwai415001.delyInstnTxt.getValue();
            //            stepCnt = this.dsImptOrdBean.nwai4150_01.somStepCnt.getValue();

            ZYPEZDItemValueSetter.setValue(this.rddDt, rddDt);
            // 2018/01/10 QC#18460 Mod Start
            //ZYPEZDItemValueSetter.setValue(this.opsFromHourMn, opsFromHourMn);
            //ZYPEZDItemValueSetter.setValue(this.opsToHourMn, opsToHourMn);
            if (opsFromHourMn != null) {
                ZYPEZDItemValueSetter.setValue(this.opsFromHourMn, getChangeTime(opsFromHourMn, this.dsImptOrdConfigBean.shipToCtryCd.getValue(), this.dsImptOrdConfigBean.shipToPostCd.getValue(), this.dsImptOrdBean.slsDt));
            }
            if (opsToHourMn != null) {
                ZYPEZDItemValueSetter.setValue(this.opsToHourMn, getChangeTime(opsToHourMn, this.dsImptOrdConfigBean.shipToCtryCd.getValue(), this.dsImptOrdConfigBean.shipToPostCd.getValue(), this.dsImptOrdBean.slsDt));
            }
            // 2018/01/10 QC#18460 Mod End
            ZYPEZDItemValueSetter.setValue(this.loadDockAvalFlg, loadDockAvalFlg);
            //            if (ZYPCommonFunc.hasValue(stepCnt) && BigDecimal.ZERO.compareTo(stepCnt) < 0) {
            //                ZYPEZDItemValueSetter.setValue(this.stairCrawReqFlg, ZYPConstant.FLG_ON_Y);
            //                ZYPEZDItemValueSetter.setValue(this.stairCrawNum, stepCnt.toString());
            //            } else {
            ZYPEZDItemValueSetter.setValue(this.stairCrawReqFlg, ZYPConstant.FLG_OFF_N);
            this.stairCrawNum.clear();
            //            }
            ZYPEZDItemValueSetter.setValue(this.elevAvalFlg, elevAvalFlg);
            if (ZYPCommonFunc.hasValue(instnTxt)) {
                ZYPEZDItemValueSetter.setValue(this.delyAddlCmntTxt, instnTxt);
            } else {
                this.delyAddlCmntTxt.clear();
            }

        } else {
            ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdDelyInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_DELY_INFO_SQ));
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdConfigBean.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, this.dsImptOrdConfigBean.dsImptOrdConfigPk);
            ZYPEZDItemValueSetter.setValue(this.dsDelyTpCd, DS_DELY_TP.INSTALLATION);

            if (NWAB415001Constant.CONFIG_TYPE.REGULAR_SHIP_CONFIG.equals(dsImptOrdConfigBean.configType)) {

                rddDt = NWXC412001.getValidDate(dsImptOrdConfigBean.nwai415012.erlstDelyTs.getValue());
                if (!ZYPCommonFunc.hasValue(rddDt)) {
                    rddDt = NWXC412001.getValidDate(this.dsImptOrdBean.nwai415001.erlstDelyTs.getValue());
                }
                opsHourMn = dsImptOrdConfigBean.nwai415012.hrsOpTxt.getValue();
                if (!ZYPCommonFunc.hasValue(opsHourMn)) {
                    opsHourMn = this.dsImptOrdBean.nwai415001.hrsOpTxt.getValue();
                }
                String[] opsHourMnFromTo = NWXC412001.divideTimeFromTo(opsHourMn);
                if (ZYPCommonFunc.hasValue(opsHourMnFromTo[0])) {
                    opsFromHourMn = opsHourMnFromTo[0];
                }
                if (ZYPCommonFunc.hasValue(opsHourMnFromTo[1])) {
                    opsToHourMn = opsHourMnFromTo[1];
                }
                BigDecimal somElevCnt = dsImptOrdConfigBean.nwai415012.somElevCnt.getValue();
                if (somElevCnt == null) {
                    if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.nwai415001.elevIndSomTxt.getValue()) //
                            && FLG.YES.name().equals(this.dsImptOrdBean.nwai415001.elevIndSomTxt.getValue())) {
                        elevAvalFlg = ZYPConstant.FLG_ON_Y;
                    } else {
                        elevAvalFlg = ZYPConstant.FLG_OFF_N;
                    }
                } else {
                    if (ZYPCommonFunc.hasValue(dsImptOrdConfigBean.nwai415012.somElevCnt) //
                            && BigDecimal.ONE.compareTo(dsImptOrdConfigBean.nwai415012.somElevCnt.getValue()) == 0) {
                        elevAvalFlg = ZYPConstant.FLG_ON_Y;
                    } else {
                        elevAvalFlg = ZYPConstant.FLG_OFF_N;
                    }
                }
                BigDecimal loadDockCnt = dsImptOrdConfigBean.nwai415012.loadDockCnt.getValue();
                if (loadDockCnt == null) {
                    if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.nwai415001.loadDockIndSomTxt) //
                            && FLG.YES.name().equals(this.dsImptOrdBean.nwai415001.loadDockIndSomTxt.getValue())) {
                        loadDockAvalFlg = ZYPConstant.FLG_ON_Y;
                    } else {
                        loadDockAvalFlg = ZYPConstant.FLG_OFF_N;
                    }
                } else {
                    if (ZYPCommonFunc.hasValue(dsImptOrdConfigBean.nwai415012.loadDockCnt) //
                            && BigDecimal.ONE.compareTo(dsImptOrdConfigBean.nwai415012.loadDockCnt.getValue()) == 0) {
                        loadDockAvalFlg = ZYPConstant.FLG_ON_Y;
                    } else {
                        loadDockAvalFlg = ZYPConstant.FLG_OFF_N;
                    }
                }
                instnTxt = dsImptOrdConfigBean.nwai415012.eopsInstnTxt.getValue();
                //                stepCnt = dsImptOrdConfigBean.nwai4150_12.somStepCnt.getValue();
                //                if (stepCnt == null || BigDecimal.ZERO.compareTo(stepCnt) == 0) {
                //                    stepCnt = this.dsImptOrdBean.nwai4150_01.somStepCnt.getValue();
                //                }
            } else if (NWAB415001Constant.CONFIG_TYPE.OCE_PROMOTION_RMA.equals(dsImptOrdConfigBean.configType)) {

                String pickUpDtOthDescTxt = dsImptOrdConfigBean.nwai4150_14List.get(0).pickUpDtOthDescTxt.getValue();
                if (ZYPCommonFunc.hasValue(pickUpDtOthDescTxt)) {
                    if (pickUpDtOthDescTxt.length() > CUTOFF_LEN.YYYYMMDD.getLen()) {
                        pickUpDtOthDescTxt = pickUpDtOthDescTxt.substring(0, CUTOFF_LEN.YYYYMMDD.getLen());
                    }
                }
                rddDt = NWXC412001.getValidDate(pickUpDtOthDescTxt);
                if (NWAB415001Constant.INVALID_DATE.equals(rddDt)) {
                    rddDt = null;
                }
                //                opsHourMn = dsImptOrdConfigBean.dsImptOrdBean.nwai4150_01.hrsOpTxt.getValue();
                loadDockAvalFlg = ZYPConstant.FLG_OFF_N;
                elevAvalFlg = ZYPConstant.FLG_OFF_N;

            } else if (NWAB415001Constant.CONFIG_TYPE.EOPS_RMA.equals(dsImptOrdConfigBean.configType)) {

                String rtrnPickUpTs = dsImptOrdConfigBean.nwai4150_24List.get(0).rtrnPickUpTs.getValue();
                if (ZYPCommonFunc.hasValue(rtrnPickUpTs)) {
                    if (rtrnPickUpTs.length() > CUTOFF_LEN.YYYYMMDD.getLen()) {
                        rtrnPickUpTs = rtrnPickUpTs.substring(0, CUTOFF_LEN.YYYYMMDD.getLen());
                    }
                }
                rddDt = NWXC412001.getValidDate(rtrnPickUpTs);
                if (NWAB415001Constant.INVALID_DATE.equals(rddDt)) {
                    rddDt = null;
                }
                loadDockAvalFlg = ZYPConstant.FLG_OFF_N;
                elevAvalFlg = ZYPConstant.FLG_OFF_N;

            } else {
                rddDt = NWXC412001.getValidDate(dsImptOrdConfigBean.getPickUpDtOthDescTxt());
                opsHourMn = dsImptOrdConfigBean.nwai415005.hrsOpTxt.getValue();
                String[] opsHourMnFromTo = NWXC412001.divideTimeFromTo(opsHourMn);
                if (ZYPCommonFunc.hasValue(opsHourMnFromTo[0])) {
                    opsFromHourMn = opsHourMnFromTo[0];
                }
                if (ZYPCommonFunc.hasValue(opsHourMnFromTo[1])) {
                    opsToHourMn = opsHourMnFromTo[1];
                }
                if (ZYPCommonFunc.hasValue(dsImptOrdConfigBean.nwai415005.loadDockCnt) && BigDecimal.ONE.compareTo(dsImptOrdConfigBean.nwai415005.loadDockCnt.getValue()) == 0) {
                    loadDockAvalFlg = ZYPConstant.FLG_ON_Y;
                } else if (BigDecimal.ZERO.compareTo(dsImptOrdConfigBean.nwai415005.loadDockCnt.getValue()) == 0) {
                    loadDockAvalFlg = ZYPConstant.FLG_OFF_N;
                }
                if (ZYPCommonFunc.hasValue(dsImptOrdConfigBean.nwai415005.somElevCnt) //
                        && BigDecimal.ONE.compareTo(dsImptOrdConfigBean.nwai415005.somElevCnt.getValue()) == 0) {
                    elevAvalFlg = ZYPConstant.FLG_ON_Y;
                } else if (BigDecimal.ZERO.compareTo(dsImptOrdConfigBean.nwai415005.somElevCnt.getValue()) == 0) {
                    elevAvalFlg = ZYPConstant.FLG_OFF_N;
                }
                //                firstNm = dsImptOrdConfigBean.nwai4150_05.primEopsCtacFirstNm.getValue();
                //                stepCnt = dsImptOrdConfigBean.nwai4150_05.somStepCnt.getValue();

            }

            ZYPEZDItemValueSetter.setValue(this.rddDt, rddDt);
            // 2018/01/10 QC#18460 Mod Start
            //ZYPEZDItemValueSetter.setValue(this.opsFromHourMn, opsFromHourMn);
            //ZYPEZDItemValueSetter.setValue(this.opsToHourMn, opsToHourMn);
            if (opsToHourMn != null) {
                ZYPEZDItemValueSetter.setValue(this.opsFromHourMn, getChangeTime(opsFromHourMn, this.dsImptOrdConfigBean.shipToCtryCd.getValue(), this.dsImptOrdConfigBean.shipToPostCd.getValue(), this.dsImptOrdBean.slsDt));
            }
            if (opsToHourMn != null) {
                ZYPEZDItemValueSetter.setValue(this.opsToHourMn, getChangeTime(opsToHourMn, this.dsImptOrdConfigBean.shipToCtryCd.getValue(), this.dsImptOrdConfigBean.shipToPostCd.getValue(), this.dsImptOrdBean.slsDt));
            }
            // 2018/01/10 QC#18460 Mod End
            ZYPEZDItemValueSetter.setValue(this.loadDockAvalFlg, loadDockAvalFlg);
            ZYPEZDItemValueSetter.setValue(this.stairCrawReqFlg, ZYPConstant.FLG_OFF_N);
            this.stairCrawNum.clear();
            //            if (ZYPCommonFunc.hasValue(stepCnt) && BigDecimal.ZERO.compareTo(stepCnt) < 0) {
            //                ZYPEZDItemValueSetter.setValue(this.stairCrawReqFlg, ZYPConstant.FLG_ON_Y);
            //                ZYPEZDItemValueSetter.setValue(this.stairCrawNum, stepCnt.toString());
            //            } else {
            //                ZYPEZDItemValueSetter.setValue(this.stairCrawReqFlg, ZYPConstant.FLG_OFF_N);
            //                this.stairCrawNum.clear();
            //            }
            ZYPEZDItemValueSetter.setValue(this.elevAvalFlg, elevAvalFlg);
            if (ZYPCommonFunc.hasValue(instnTxt)) {
                ZYPEZDItemValueSetter.setValue(this.delyAddlCmntTxt, S21StringUtil.subStringByLength(instnTxt, 0, CUTOFF_LEN.DELY_ADDL_CMNT_TXT.getLen()));
            } else {
                this.delyAddlCmntTxt.clear();
            }
        }

        return true;
    }

    // 2018/01/22 QC#18460 Add Start
    /**
     * getChangeTime
     * @param cMsg NWAL2240CMsg
     * @param time String
     * @return time String
     */
    public static String getChangeTime(String time, String ctryCd, String postCd, String slsDt) {

        if (ZYPCommonFunc.hasValue(time)) {
            time = slsDt + time;

            SvcTimeZoneInfo svcTimeZoneInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, time, ctryCd, postCd);

            if (svcTimeZoneInfo != null) {
                time = S21StringUtil.subStringByLength(svcTimeZoneInfo.getDateTime(), TIME_START_POS, TIME_END_POS);
            }

        }

        return time;
    }
    // 2018/01/22 QC#18460 Add End
}
