/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB038001;

import static com.canon.cusa.s21.batch.NWC.NWCB038001.constant.NWCB038001Constant.MSG_ID.ZZMM0001E;
import static com.canon.cusa.s21.batch.NWC.NWCB038001.constant.NWCB038001Constant.MSG_ID.ZZMM0015E;
import static com.canon.cusa.s21.batch.NWC.NWCB038001.constant.NWCB038001Constant.MSG_ID.ZZZM9025E;
import static com.canon.cusa.s21.batch.NWC.NWCB038001.constant.NWCB038001Constant.NUM_CONST_KEY.NWCB0380_AOT_MINS;
import static com.canon.cusa.s21.batch.NWC.NWCB038001.constant.NWCB038001Constant.NUM_CONST_KEY.NWCB0380_INIT_SETUP_AOT_DAYS;
import static com.canon.cusa.s21.batch.NWC.NWCB038001.constant.NWCB038001Constant.NUM_CONST_KEY.NWCB0380_KEY_STRING_FROM;
import static com.canon.cusa.s21.batch.NWC.NWCB038001.constant.NWCB038001Constant.NUM_CONST_KEY.NWCB0380_KEY_STRING_TO;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
 * Update Process Timestamp for EMSD Tool Interface.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/18   Fujitsu         M.Yamada        Create          QC#18131
 * </pre>
 */
public class NWCB038001 extends S21BatchMain {

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

    /** Aot Mins */
    private BigDecimal aot = BigDecimal.ZERO;

    /** Aot Days */
    private BigDecimal aotDays = BigDecimal.ZERO;

    @Override
    protected void initRoutine() {
        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E.name(), new String[] {"Global Company Code" });
        }
        // Interface Id
        this.intfcId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(this.intfcId)) {
            throw new S21AbendException(ZZZM9025E.name(), new String[] {"Interface ID" });
        }

        this.aotDays = ZYPCodeDataUtil.getNumConstValue(NWCB0380_INIT_SETUP_AOT_DAYS.name(), this.glblCmpyCd);
        if (this.aotDays != null) {
            this.initSysTime = ZYPDateUtil.addDays(ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT), this.aotDays.intValue() * -1);
        } else {
            this.initSysTime = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);
        }
        this.sysTime = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);
        this.termCd = TERM_CD.NORMAL_END;
        this.normalAllCount = 0;
        this.errorAllCount = 0;
        this.strFrom = ZYPCodeDataUtil.getVarCharConstValue(NWCB0380_KEY_STRING_FROM.name(), this.glblCmpyCd);
        if (this.strFrom == null) {
            this.strFrom = "FROM_TS";
        }
        this.strTo = ZYPCodeDataUtil.getVarCharConstValue(NWCB0380_KEY_STRING_TO.name(), this.glblCmpyCd);
        if (this.strTo == null) {
            this.strTo = "TO_TS";
        }
        this.aot = ZYPCodeDataUtil.getNumConstValue(NWCB0380_AOT_MINS.name(), this.glblCmpyCd);
        if (this.aot == null) {
            this.aot = BigDecimal.ZERO;
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
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(to.getReturnCode())) {
                //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                throw new S21AbendException(ZZMM0001E.name(), new String[] {"INTFC_TS_CTRL", "CTRL_INTFC_ID,CTRL_INTFC_KEY_TXT", this.intfcId + "," + this.strTo });
            }
        } else {
            //Current Timestamp  --> TO Timestamp
            toTimestamp = to.ctrlIntfcValTxt.getValue();
            ZYPEZDItemValueSetter.setValue(to.ctrlIntfcValTxt, this.sysTime);
            S21FastTBLAccessor.update(to);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(to.getReturnCode())) {
                //ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
                throw new S21AbendException(ZZMM0015E.name(), new String[] {"INTFC_TS_CTRL", "CTRL_INTFC_ID,CTRL_INTFC_KEY_TXT", this.intfcId + "," + this.strFrom });
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
            ZYPEZDItemValueSetter.setValue(from.ctrlIntfcValTxt, addMinutes(toTimestamp, this.aot));
            S21FastTBLAccessor.insert(from);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(from.getReturnCode())) {
                //ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                throw new S21AbendException(ZZMM0001E.name(), new String[] {"INTFC_TS_CTRL", "CTRL_INTFC_ID,CTRL_INTFC_KEY_TXT", this.intfcId + "," + this.strFrom });
            }
        } else {
            //TO Timestamp  -->  FROM Timestamp
            ZYPEZDItemValueSetter.setValue(from.ctrlIntfcValTxt, addMinutes(toTimestamp, this.aot));
            S21FastTBLAccessor.update(from);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(from.getReturnCode())) {
                //ZZMM0015E=0,Data update failure.  [ TableName = @ , key = @, value = @ ]
                throw new S21AbendException(ZZMM0015E.name(), new String[] {"INTFC_TS_CTRL", "CTRL_INTFC_ID,CTRL_INTFC_KEY_TXT", this.intfcId + "," + this.strFrom });
            }
        }

        commit();
    }

    private String addMinutes(String timestamp, BigDecimal mAot) {

        if (timestamp == null) {
            timestamp = this.sysTime;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(S21SessionHelper.EZD_TIME_FORMAT, Locale.US);
        TimeZone zone = TimeZone.getDefault();
        dateFormat.setTimeZone(zone);

        int year = Integer.parseInt(timestamp.substring(0, "YYYY".length()));
        int month = Integer.parseInt(timestamp.substring("YYYY".length(), "YYYYMM".length()));
        int day = Integer.parseInt(timestamp.substring("YYYYMM".length(), "YYYYMMDD".length()));
        int hour = Integer.parseInt(timestamp.substring("YYYYMMDD".length(), "YYYYMMDDhr".length()));
        int minute = Integer.parseInt(timestamp.substring("YYYYMMDDhr".length(), "YYYYMMDDhrmi".length()));
        int second = Integer.parseInt(timestamp.substring("YYYYMMDDhrmi".length(), "YYYYMMDDhrmiss".length()));
        int millSecond = Integer.parseInt(timestamp.substring("YYYYMMDDhrmiss".length(), "YYYYMMDDhrmissSSS".length()));

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(zone);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, millSecond);
        if (mAot != null && BigDecimal.ZERO.compareTo(mAot) != 0) {
            cal.add(Calendar.MINUTE, mAot.intValue() * -1);
        }
        Date date = cal.getTime();
        return dateFormat.format(date);
    }

    @Override
    protected void termRoutine() {
        if (this.errorAllCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.normalAllCount, this.errorAllCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWCB038001().executeBatch(NWCB038001.class.getSimpleName());
    }

}
