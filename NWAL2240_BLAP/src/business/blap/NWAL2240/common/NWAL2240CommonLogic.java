/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2240.common;

import static business.blap.NWAL2240.constant.NWAL2240Constant.AM_CD;
import static business.blap.NWAL2240.constant.NWAL2240Constant.AM_PM_PULLDOWN_CD;
import static business.blap.NWAL2240.constant.NWAL2240Constant.AM_PM_PULLDOWN_NM;
import static business.blap.NWAL2240.constant.NWAL2240Constant.TIME_OF_24_HOURS_CHANGE;
import static business.blap.NWAL2240.constant.NWAL2240Constant.DEFAULT_TIME;
import static business.blap.NWAL2240.constant.NWAL2240Constant.TIME_START_POS;
import static business.blap.NWAL2240.constant.NWAL2240Constant.TIME_END_POS;

import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NWAL2240.NWAL2240CMsg;
import business.blap.NWAL2240.NWAL2240Query;
import business.blap.NWAL2240.NWAL2240SMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NWAL2240CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Fujitsu         S.Ohki          Create          N/A
 * 2018/01/09   Fujitsu         N.Sugiura       Update          QC#18460
 * 2019/11/09   Fujitsu         Mz.Takahashi    Update          QC#53993 
 *</pre>
 */
public class NWAL2240CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * Set Service Install Rule
     * @param bizMsg NWAL2240CMsg
     * @param glblCmpyCd Global Company Code
     */
    public static void setSvcIstlRulePulldown(NWAL2240CMsg bizMsg, String glblCmpyCd) {
        S21SsmEZDResult result = NWAL2240Query.getInstance().getSvcIstlRuleList(bizMsg, glblCmpyCd);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            for (Map<String, Object> map : list) {
                if (map != null && map.get("SVC_ISTL_RULE_NUM") != null) {
                    bizMsg.svcIstlRuleNum_L0.no(i).setValue((String) map.get("SVC_ISTL_RULE_NUM"));
                    bizMsg.svcIstlRuleNm_L0.no(i).setValue((String) map.get("SVC_ISTL_RULE_NM"));
                    i++;
                }
            }
        }
    }

    /**
     * Set AmPm Pulldown List
     * @param bizMsg NWAL2240CMsg
     */
    public static void setAmPmPulldownList(NWAL2240CMsg bizMsg) {
        for (int idx = 0; idx < AM_PM_PULLDOWN_CD.length; idx++) {
            bizMsg.xxTpCd.no(idx).setValue(AM_PM_PULLDOWN_CD[idx]);
            bizMsg.xxTpNm.no(idx).setValue(AM_PM_PULLDOWN_NM[idx]);
        }
    }

    /**
     * From CMsg to SMsg
     * @param cMsg NWAL2240CMsg
     * @param sMsg NWAL2240SMsg
     */
    public static void copyCMsgToSMsg(NWAL2240CMsg cMsg, NWAL2240SMsg sMsg) {
        int idx = 0;
        for (; idx < cMsg.C.length(); idx++) {
            if (idx < cMsg.C.getValidCount()) {
                EZDMsg.copy(cMsg.C.get(idx), null, sMsg.C.get(idx), null);
            } else {
                break;
            }
        }
        sMsg.C.setValidCount(idx);
    }

    /**
     * From SMsg to CMsg
     * @param cMsg NWAL2240CMsg
     * @param sMsg NWAL2240SMsg
     */
    public static void copySMsgToCMsg(NWAL2240CMsg cMsg, NWAL2240SMsg sMsg) {
        int idx = 0;
        for (; idx < cMsg.C.length(); idx++) {
            if (idx < sMsg.C.getValidCount()) {
                EZDMsg.copy(sMsg.C.no(idx), null, cMsg.C.no(idx), null);
            } else {
                break;
            }
        }
        cMsg.C.setValidCount(idx);
    }

    /**
     * Change time in 24-hour notation
     * @param time String
     * @param apCode String
     * @return changeTime String
     */
    public static String changeTime24h(String time, String apCode) {
        if (!ZYPCommonFunc.hasValue(time)) {
            return time;
        }

        if (AM_CD.equals(apCode)) {
            return time;
        }

        return String.valueOf(Integer.parseInt(time.substring(0, 2)) + TIME_OF_24_HOURS_CHANGE) + time.substring(2);
    }

    // 2018/01/09 QC#18460 Add Start
    /**
     * getTimeZone
     * @param cMsg NWAL2240CMsg
     * @return tmZoneCd String
     */
    public static String getTimeZone(NWAL2240CMsg cMsg) {

        String tmZoneCd = "";
        String time = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()) + DEFAULT_TIME;

        SvcTimeZoneInfo svcTimeZoneInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, time, cMsg.ctryCd_H0.getValue(), cMsg.postCd_H0.getValue());

        if (svcTimeZoneInfo != null) {
            tmZoneCd = svcTimeZoneInfo.getTimeZone();
        }

        return tmZoneCd;
    }

    /**
     * getChangeTime
     * @param cMsg NWAL2240CMsg
     * @param time String
     * @return time String
     */
    public static String getChangeTime(NWAL2240CMsg cMsg, String time) {

        if (ZYPCommonFunc.hasValue(time)) {
            time = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()) + time;

            SvcTimeZoneInfo svcTimeZoneInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, time, cMsg.ctryCd_H0.getValue(), cMsg.postCd_H0.getValue());

            if (svcTimeZoneInfo != null) {
                time = svcTimeZoneInfo.getDateTime();
            }

            if (time.length() > TIME_END_POS) {
                time = S21StringUtil.subStringByLength(time, TIME_START_POS, TIME_END_POS);
            }
        }

        return time;
    }
    // 2018/01/09 QC#18460 Add End

    // 2019/11/09 QC#53993 Add Start
    /**
     * setCtacPsnTpPulldown
     * @param bizMsg NWAL2240CMsg
     * @param toBeIstlOrSvcPrvdByFlg true:toBeIstlByFlg='Y' false:svcPrvdByFlg='Y'
     * @param glblCmpyCd Global Company Code
     */
    public static void setSvcPrvdPtyPulldown(NWAL2240CMsg bizMsg, boolean toBeIstlOrSvcPrvdByFlg, String glblCmpyCd) {
        S21SsmEZDResult result = NWAL2240Query.getInstance().getSvcPrvdPty(bizMsg, toBeIstlOrSvcPrvdByFlg, glblCmpyCd);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            for (Map<String, Object> map : list) {
                if (toBeIstlOrSvcPrvdByFlg) {
                    bizMsg.istlBySvcPrvdPtyCd_L0.no(i).setValue((String) map.get("SVC_PRVD_PTY_CD"));
                    bizMsg.svcPrvdPtyDescTxt_L0.no(i).setValue((String) map.get("SVC_PRVD_PTY_DESC_TXT"));
                } else {
                    bizMsg.svcBySvcPrvdPtyCd_L0.no(i).setValue((String) map.get("SVC_PRVD_PTY_CD"));
                    bizMsg.svcPrvdPtyDescTxt_L1.no(i).setValue((String) map.get("SVC_PRVD_PTY_DESC_TXT"));
                }

                i++;
            }
        }
    }
    // 2019/11/09 QC#53993 Add End
}
