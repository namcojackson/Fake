/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB416001;

import static com.canon.cusa.s21.batch.NWA.NWAB416001.constant.NWAB416001Constant.*;
import static com.canon.cusa.s21.batch.NWA.NWAB416001.constant.NWAB416001Constant.ZZMM0001E;
import static com.canon.cusa.s21.batch.NWA.NWAB416001.constant.NWAB416001Constant.ZZMM0015E;
import static com.canon.cusa.s21.batch.NWA.NWAB416001.constant.NWAB416001Constant.ZZZM9025E;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import parts.dbcommon.EZDTBLAccessor;
import business.db.INTFC_TS_CTRLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;

/**
 * <pre>
 * Update Process Timestamp for EOPS Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/08   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 * 
 * </pre>
 */
public class NWAB416001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Interface Id */
    private String intfcId = null;

    /** System TimeStamp */
    private String sysTime = null;

    /** Initial System TimeStamp */
    private String initSysTime = null;

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Normal Count */
    private int normalAllCount;

    /** Error Count */
    private int errorAllCount;

    /** Key String From */
    private String strFrom = null;

    /** Key String To */
    private String strTo = null;

    /** Aot Minus */
    private BigDecimal aotMins = BigDecimal.ZERO;

    /** Aot Days */
    private BigDecimal aotDays = BigDecimal.ZERO;

    /**
     * A main method for batch application start.
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB416001().executeBatch(NWAB416001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }
        // Interface Id
        this.intfcId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(this.intfcId)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Interface ID" });
        }

        this.aotDays = ZYPCodeDataUtil.getNumConstValue("EOPS_IF_INIT_SETUP_AOT_DAYS", this.glblCmpyCd);
        if (this.aotDays != null) {
            this.initSysTime = ZYPDateUtil.addDays(ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT), this.aotDays.intValue() * -1);
        } else {
            this.initSysTime = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);
        }
        this.sysTime = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);
        this.termCd = TERM_CD.NORMAL_END;
        this.normalAllCount = 0;
        this.errorAllCount = 0;
        this.strFrom = ZYPCodeDataUtil.getVarCharConstValue("EOPS_IF_KEY_STRING_FROM", this.glblCmpyCd);
        if (this.strFrom == null) {
            this.strFrom = "FROM_TS";
        }
        this.strTo = ZYPCodeDataUtil.getVarCharConstValue("EOPS_IF_KEY_STRING_TO", this.glblCmpyCd);
        if (this.strTo == null) {
            this.strTo = "TO_TS";
        }
        this.aotMins = ZYPCodeDataUtil.getNumConstValue("EOPS_IF_AOT_MINS", this.glblCmpyCd);
        if (this.aotMins == null) {
            this.aotMins = BigDecimal.ZERO;
        }

    }

    @Override
    protected void mainRoutine() {

        INTFC_TS_CTRLTMsg to = new INTFC_TS_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(to.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(to.ctrlIntfcId, this.intfcId);
        ZYPEZDItemValueSetter.setValue(to.ctrlIntfcKeyTxt, this.strTo);
        to = (INTFC_TS_CTRLTMsg) S21FastTBLAccessor.findByKey(to);
        String toTimestamp = "";
        if (to == null) {
            toTimestamp = this.initSysTime;
            to = new INTFC_TS_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(to.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(to.ctrlIntfcId, this.intfcId);
            ZYPEZDItemValueSetter.setValue(to.ctrlIntfcKeyTxt, this.strTo);
            ZYPEZDItemValueSetter.setValue(to.ctrlIntfcValTxt, this.sysTime);
            S21FastTBLAccessor.insert(to);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(to.getReturnCode())) {
                //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                throw new S21AbendException(ZZMM0001E, new String[] {"INTFC_TS_CTRL", "CTRL_INTFC_ID,CTRL_INTFC_KEY_TXT", this.intfcId + "," + this.strTo });
            }
        } else {
            //Current Timestamp  --> TO Timestamp
            toTimestamp = to.ctrlIntfcValTxt.getValue();
            ZYPEZDItemValueSetter.setValue(to.ctrlIntfcValTxt, this.sysTime);
            S21FastTBLAccessor.update(to);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(to.getReturnCode())) {
                //ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
                throw new S21AbendException(ZZMM0015E, new String[] {"INTFC_TS_CTRL", "CTRL_INTFC_ID,CTRL_INTFC_KEY_TXT", this.intfcId + "," + this.strFrom });
            }
        }

        INTFC_TS_CTRLTMsg from = new INTFC_TS_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(from.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(from.ctrlIntfcId, this.intfcId);
        ZYPEZDItemValueSetter.setValue(from.ctrlIntfcKeyTxt, this.strFrom);
        from = (INTFC_TS_CTRLTMsg) S21FastTBLAccessor.findByKey(from);
        if (from == null) {
            from = new INTFC_TS_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(from.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(from.ctrlIntfcId, this.intfcId);
            ZYPEZDItemValueSetter.setValue(from.ctrlIntfcKeyTxt, this.strFrom);
            ZYPEZDItemValueSetter.setValue(from.ctrlIntfcValTxt, addMinutes(toTimestamp, this.aotMins));
            S21FastTBLAccessor.insert(from);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(from.getReturnCode())) {
                //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                throw new S21AbendException(ZZMM0001E, new String[] {"INTFC_TS_CTRL", "CTRL_INTFC_ID,CTRL_INTFC_KEY_TXT", this.intfcId + "," + this.strFrom });
            }
        } else {
            //TO Timestamp  -->  FROM Timestamp
            ZYPEZDItemValueSetter.setValue(from.ctrlIntfcValTxt, addMinutes(toTimestamp, this.aotMins));
            S21FastTBLAccessor.update(from);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(from.getReturnCode())) {
                //ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
                throw new S21AbendException(ZZMM0015E, new String[] {"INTFC_TS_CTRL", "CTRL_INTFC_ID,CTRL_INTFC_KEY_TXT", this.intfcId + "," + this.strFrom });
            }
        }

        commit();

    }

    @Override
    protected void termRoutine() {
        if (this.errorAllCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.normalAllCount, this.errorAllCount);
    }

    private String addMinutes(String timestamp, BigDecimal aot) {

        if (timestamp == null) {
            timestamp = this.sysTime;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(S21SessionHelper.EZD_TIME_FORMAT, Locale.US);
        TimeZone zone = TimeZone.getDefault();
        dateFormat.setTimeZone(zone);

        int year = Integer.parseInt(timestamp.substring(0, LEN_YYYY));
        int month = Integer.parseInt(timestamp.substring(LEN_YYYY, LEN_YYYYMM));
        int day = Integer.parseInt(timestamp.substring(LEN_YYYYMM, LEN_YYYYMMDD));
        int hour = Integer.parseInt(timestamp.substring(LEN_YYYYMMDD, LEN_YYYYMMDDHH));
        int minute = Integer.parseInt(timestamp.substring(LEN_YYYYMMDDHH, LEN_YYYYMMDDHHMI));
        int second = Integer.parseInt(timestamp.substring(LEN_YYYYMMDDHHMI, LEN_YYYYMMDDHHMISS));
        int millSecond = Integer.parseInt(timestamp.substring(LEN_YYYYMMDDHHMISS, LEN_YYYYMMDDHHMISSFFF));

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(zone);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, millSecond);
        if (aot != null && BigDecimal.ZERO.compareTo(aot) != 0) {
            cal.add(Calendar.MINUTE, aot.intValue() * -1);
        }
        Date date = cal.getTime();
        return dateFormat.format(date);
    }

}
