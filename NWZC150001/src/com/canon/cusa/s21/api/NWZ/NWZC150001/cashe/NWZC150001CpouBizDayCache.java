/**
 * <pre>
 * Business Day Cache
 *
 * The common feature to which order information cooperating
 *  is updated is offered from two or more Order Source.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/04   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cashe;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByCondition;
import parts.common.EZDTMsg;
import business.db.CAL_RELNTMsg;
import business.db.CAL_RELNTMsgArray;

import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalDataCacheBase;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * Business Day Cache.
 * @author K.Tajima
 */
public class NWZC150001CpouBizDayCache extends NWZC150001CpouLocalDataCacheBase {

    private final S21LRUMap<String, CAL_RELNTMsg> calRelnCache = new S21LRUMap<String, CAL_RELNTMsg>();

    private final S21LRUMap<String, Boolean> isBizDayCache = new S21LRUMap<String, Boolean>();

    private final S21LRUMap<String, String> bizDayCache = new S21LRUMap<String, String>();

    // 2017/05/11 S21_NA#Review structure Lv.2 Add Start
    private final S21LRUMap<String, EZDTMsg> tMsgCache = new S21LRUMap<String, EZDTMsg>();
    // 2017/05/11 S21_NA#Review structure Lv.2 Add End

    public NWZC150001CpouBizDayCache() {
    }

    public CAL_RELNTMsg getCalRelnTMsg(String glblCmpyCd, String calSubTpCd, String calMultCd) {

        final CAL_RELNTMsg calRelnTMsg = new CAL_RELNTMsg();
        setValue(calRelnTMsg.glblCmpyCd, glblCmpyCd);
        setValue(calRelnTMsg.calSubTpCd, calSubTpCd);
        setValue(calRelnTMsg.calMultCd, calMultCd);

        return (CAL_RELNTMsg) NWZC150001CpouLocalCache.findByKeyWithCache(calRelnTMsg);
    }

    public CAL_RELNTMsg getCalRelnTMsg(String glblCmpyCd, String calSubTpCd) {

        final StringBuilder cacheKeySb = new StringBuilder();
        cacheKeySb.append(glblCmpyCd).append(",");
        cacheKeySb.append(calSubTpCd);

        final String cacheKey = cacheKeySb.toString();

        CAL_RELNTMsg calRelnTMsg = calRelnCache.get(cacheKey);

        if (calRelnTMsg == null) {

            calRelnTMsg = new CAL_RELNTMsg();
            calRelnTMsg.setSQLID("001");
            calRelnTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            calRelnTMsg.setConditionValue("calSubTpCd01", calSubTpCd);
            calRelnTMsg.setMaxCount(1);

            final CAL_RELNTMsgArray calRelnArray = (CAL_RELNTMsgArray) findByCondition(calRelnTMsg);

            if (calRelnArray.getValidCount() > 0) {
                calRelnTMsg = calRelnArray.no(0);
            }

            calRelnCache.put(cacheKey, calRelnTMsg);
        }

        return calRelnCache.get(cacheKey);
    }

    public boolean isBizDay(String glblCmpyCd, String calTpCd, String yyyyMMdd) {

        final StringBuilder cacheKeySb = new StringBuilder();
        cacheKeySb.append(glblCmpyCd).append(",");
        cacheKeySb.append(calTpCd).append(",");
        cacheKeySb.append(yyyyMMdd);

        final String cacheKey = cacheKeySb.toString();

        Boolean isBizDay = isBizDayCache.get(cacheKey);

        if (isBizDay == null) {
            try {
                isBizDay = ZYPDateUtil.isBusinessDayEx(glblCmpyCd, calTpCd, yyyyMMdd);
            } catch (S21AbendException e) {
                isBizDay = false;
            }

            isBizDayCache.put(cacheKey, isBizDay);
        }

        return isBizDayCache.get(cacheKey);
    }

    public String getBizDay(String glblCmpyCd, String calTpCd, String yyyyMMdd) {

        final StringBuilder cacheKeySb = new StringBuilder();
        cacheKeySb.append(glblCmpyCd).append(",");
        cacheKeySb.append(calTpCd).append(",");
        cacheKeySb.append(yyyyMMdd);

        final String cacheKey = cacheKeySb.toString();

        String bizDay = bizDayCache.get(cacheKey);

        if (bizDay == null) {
            try {
                bizDay = ZYPDateUtil.addBusinessDayEx(glblCmpyCd, calTpCd, yyyyMMdd, -1);
            } catch (S21AbendException e) {
                bizDay = yyyyMMdd;
            }

            bizDayCache.put(cacheKey, bizDay);
        }

        return bizDayCache.get(cacheKey);
    }

    // 2017/05/11 S21_NA#Review structure Lv.2 Add Start
    public CAL_RELNTMsg getTMsgByKey(String glblCmpyCd, String calSubTpCd, String calMultCd) {

        final StringBuilder sb = new StringBuilder();
        sb.append(glblCmpyCd).append(",");
        sb.append(calSubTpCd).append(",");
        sb.append(calMultCd);

        final String cacheKey = sb.toString();

        EZDTMsg resTMsg = tMsgCache.get(cacheKey);

        if (resTMsg == null) {

            // find by key
            final CAL_RELNTMsg reqTMsg = new CAL_RELNTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
            reqTMsg.calSubTpCd.setValue(calSubTpCd);
            reqTMsg.calMultCd.setValue(calMultCd);

            resTMsg = super.findByKey(reqTMsg);
            if (resTMsg != null) {
                tMsgCache.put(cacheKey, resTMsg);
            }
        }

        return (CAL_RELNTMsg) resTMsg;
    }
    // 2017/05/11 S21_NA#Review structure Lv.2 Add End

}
