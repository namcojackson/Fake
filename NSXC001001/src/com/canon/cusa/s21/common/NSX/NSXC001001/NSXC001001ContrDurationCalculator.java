/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE_UOM;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

import business.db.BLLG_CYCLE_UOMTMsg;

/**
 * <pre>
 * Contract Duration Calculator
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/23   Hitachi         K.Yamada        Update          CSA QC#1824
 *</pre>
 */
public class NSXC001001ContrDurationCalculator {

    /** parameter */
    private ContrDurationInfo param;

    /** ssm client */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC001001ContrDurationCalculator.class);

    /**
     * Constructor
     * @param param ContrDurationInfo
     */
    public NSXC001001ContrDurationCalculator(ContrDurationInfo param) {
        this.param = param;
    }

    /**
     * Calculate contract end date by start date, duration and cycle UOM.
     */
    public void calcEndDt() {

        if (!canCalcEndDt()) {
            return;
        }

        BLLG_CYCLE_UOMTMsg cycleUom = getCycleUom(this.param.getGlblCmpyCd(), this.param.getCycleUomCd());
        if (cycleUom == null) {
            return;
        }

        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date startDt;
        try {
            startDt = df.parse(this.param.getContrEffFromDt());
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        String endDate = null;
        if (FLG_ON_Y.equals(cycleUom.bllgCycleDlyFlg.getValue())) {

            BigDecimal daysAot = cycleUom.bllgCycleDaysAot.getValue();
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDt);
            cal.add(Calendar.DATE, this.param.getContrDurnNum().multiply(daysAot).intValue() - 1);
            endDate = df.format(cal.getTime());

        } else if (FLG_ON_Y.equals(cycleUom.bllgCycleMlyFlg.getValue())) {
            BigDecimal mthAot = cycleUom.bllgCycleMthAot.getValue();
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDt);
            cal.add(Calendar.MONTH, this.param.getContrDurnNum().multiply(mthAot).intValue());
            cal.add(Calendar.DATE, -1);
            endDate = df.format(cal.getTime());
        }
        this.param.setContrEffThruDt(endDate);
    }

    /**
     * Calculate duration and cycle UOM by start date and end date.
     */
    public void calcDuration() {

        if (!canCalcDuration()) {
            return;
        }

        List<BLLG_CYCLE_UOMTMsg> cycleUomList = getCycleUomExceptDays(this.param.getGlblCmpyCd());
        if (cycleUomList == null || cycleUomList.isEmpty()) {
            return;
        }

        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date startDt;
        try {
            startDt = df.parse(this.param.getContrEffFromDt());
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        String paramEndDate = this.param.getContrEffThruDt();
        for (BLLG_CYCLE_UOMTMsg cycleUom : cycleUomList) {
            Calendar cal = Calendar.getInstance();
            String calcEndDate = "";
            BigDecimal durnCnt = BigDecimal.ZERO;
            BigDecimal daysAot = cycleUom.bllgCycleDaysAot.getValue();
            BigDecimal mthAot = cycleUom.bllgCycleMthAot.getValue();

            while (paramEndDate.compareTo(calcEndDate) > 0) {
                cal.setTime(startDt);
                durnCnt = durnCnt.add(BigDecimal.ONE);

                if (FLG_ON_Y.equals(cycleUom.bllgCycleDlyFlg.getValue())) {
                    cal.add(Calendar.DATE, durnCnt.multiply(daysAot).intValue() - 1);
                } else if (FLG_ON_Y.equals(cycleUom.bllgCycleMlyFlg.getValue())) {
                    cal.add(Calendar.MONTH, durnCnt.multiply(mthAot).intValue());
                    cal.add(Calendar.DATE, -1);
                }
                calcEndDate = df.format(cal.getTime());
            }

            if (paramEndDate.compareTo(calcEndDate) < 0) {
                continue;
            } else if (paramEndDate.compareTo(calcEndDate) == 0) {
                this.param.setContrDurnNum(durnCnt);
                this.param.setCycleUomCd(cycleUom.bllgCycleUomCd.getValue());
                break;
            }
        }

        if (!hasValue(this.param.getContrDurnNum())) {
            int duration = ZYPDateUtil.getDiffDays(this.param.getContrEffThruDt(), this.param.getContrEffFromDt()) + 1;
            this.param.setContrDurnNum(BigDecimal.valueOf(duration));
            this.param.setCycleUomCd(BLLG_CYCLE_UOM.DAYS);
        }
    }

    private boolean canCalcEndDt() {
        if (this.param == null) {
            return false;
        }
        if (!hasValue(this.param.getGlblCmpyCd())) {
            return false;
        }

        if (hasValue(this.param.getContrEffFromDt()) && hasValue(this.param.getContrDurnNum())
                && hasValue(this.param.getCycleUomCd())) {
            return true;
        }
        return false;
    }

    private boolean canCalcDuration() {
        if (this.param == null) {
            return false;
        }
        if (!hasValue(this.param.getGlblCmpyCd())) {
            return false;
        }

        if (hasValue(this.param.getContrEffFromDt()) && hasValue(this.param.getContrEffThruDt())) {
            return true;
        }
        return false;
    }

    private BLLG_CYCLE_UOMTMsg getCycleUom(String glblCmpyCd, String bllgCycleUomCd) {
        return (BLLG_CYCLE_UOMTMsg) ZYPCodeDataUtil.findByCode(BLLG_CYCLE_UOM.class, glblCmpyCd, bllgCycleUomCd);
    }

    @SuppressWarnings("unchecked")
    private List<BLLG_CYCLE_UOMTMsg> getCycleUomExceptDays(String glblCmpyCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("days", BLLG_CYCLE_UOM.DAYS);

        return (List<BLLG_CYCLE_UOMTMsg>) SSM_CLIENT.queryObjectList("getCycleUomExceptDays", queryParam);
    }

}
