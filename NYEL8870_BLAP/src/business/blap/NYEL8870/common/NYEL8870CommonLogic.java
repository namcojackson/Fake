/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8870.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import parts.common.EZDMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WF_NTFY_EML_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WF_NTFY_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import business.blap.NYEL8870.NYEL8870CMsg;
import business.blap.NYEL8870.NYEL8870Query;
import business.blap.NYEL8870.NYEL8870SMsg;
import business.blap.NYEL8870.constant.NYEL8870Constant;
import business.db.WF_NTFY_TPTMsg;
import business.db.WF_NTFY_TPTMsgArray;
import business.db.WF_USR_PRFTMsg;

/**
 *<pre>
 * NYEL8870CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/30   Fujitsu         Q10627          Create          N/A
 *</pre>
 */
public class NYEL8870CommonLogic {
    /**
     * initProcNamePullDown
     * @param bizMsg Business Message
     * @param glblCmpyCd Global Company Code
     * @param wfBizAppId WF Biz App ID
     * @return proc names
     */
    public static List<Map> getProcNames(NYEL8870CMsg bizMsg, String glblCmpyCd, String wfBizAppId) {
        List<Map> mapList = new ArrayList<Map>();

        int maxPulldownLen = bizMsg.wfProcNm_L.length() - 1; // 1 is for ALL

        Map<String, Object> params = new HashMap<String, Object>();
        if (wfBizAppId != null && !wfBizAppId.isEmpty()) {
            params.put("wfBizAppId", wfBizAppId);
        }
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rowNum", maxPulldownLen);
        S21SsmEZDResult ssmResult = NYEL8870Query.getInstance().getProcNames(params);
        if (!ssmResult.isCodeNormal()) {
            return mapList;
        }

        List<Map> resultList = (List<Map>) ssmResult.getResultObject();

        for (Map resultMap : resultList) {
            Map map = new HashMap();
            map.put("WF_PROC_NM", (String) resultMap.get("WF_PROC_NM"));
            map.put("WF_DESC_TXT", (String) resultMap.get("WF_DESC_TXT"));
            mapList.add(map);
        }

        return mapList;
    }

    /**
     * initProcNamePullDown
     * @param bizMsg Business Message
     * @param glblCmpyCd Global Company Code
     */
    public static void initProcNamePullDown(NYEL8870CMsg bizMsg, String glblCmpyCd) {

        bizMsg.wfProcNm_L.clear();
        bizMsg.wfDescTxt_LD.clear();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rowNum", bizMsg.wfProcNm_L.length() - 1);
        S21SsmEZDResult ssmResult = NYEL8870Query.getInstance().getProcNames(params);
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        List resultList = (List) ssmResult.getResultObject();

        for (int i = 0; i < resultList.size(); i++) {
            Map resultMap = (Map) resultList.get(i);

            String wfProcName = (String) resultMap.get("WF_PROC_NM");
            String wfDescTxt = (String) resultMap.get("WF_DESC_TXT");

            ZYPEZDItemValueSetter.setValue(bizMsg.wfProcNm_L.no(i), wfProcName);
            ZYPEZDItemValueSetter.setValue(bizMsg.wfDescTxt_LD.no(i), wfDescTxt);
        }
    }

    /**
     * initNtfyTypePullDown
     * @param bizMsg Business Message
     */
    public static void initNtfyTypePullDown(NYEL8870CMsg bizMsg) {
        ZYPCodeDataUtil.createPulldownList(WF_NTFY_TP.class, bizMsg.wfNtfyTpCd_L, bizMsg.wfNtfyTpDescTxt_LD);
    }

    /**
     * initNtfyEmailTypePullDown
     * @param bizMsg Business Message
     */
    public static void initNtfyEmailTypePullDown(NYEL8870CMsg bizMsg) {
        ZYPCodeDataUtil.createPulldownList(WF_NTFY_EML_TP.class, bizMsg.wfNtfyEmlTpCd_L, bizMsg.wfNtfyEmlTpDescTxt_LD);
    }


    /**
     * search
     * @param bizMsg Business Message
     * @param sMsg Server Message
     * @param usrId user Id
     * @param glblCmpyCd Global company code
     */
    public static void search(NYEL8870CMsg bizMsg, NYEL8870SMsg sMsg, String usrId, String glblCmpyCd) {
        sMsg.A.clear();

        //Get User Preference
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);

        params.put("default", NYEL8870Constant.FOR_DEFAULT);
        params.put("wfUsrId", usrId);

        String searchBizAppId = bizMsg.wfProcNm_V.getValue();
        if (!searchBizAppId.isEmpty()) {
            params.put("wfBizAppId", searchBizAppId);
        }

        String searchNtfyTpCd = bizMsg.wfNtfyTpCd_V.getValue();
        if (!searchNtfyTpCd.isEmpty()) {
            params.put("wfNtfyTpCd", searchNtfyTpCd);
        }

        params.put("wfUsrId", usrId);
        params.put("rowNum", sMsg.A.length() + 1);
        S21SsmEZDResult ssmResult = NYEL8870Query.getInstance().getUserPrefs(params);
//        if (!ssmResult.isCodeNormal()) {
//            return;
//        }

        String searchWfNtfyEmlTpCd = bizMsg.wfNtfyEmlTpCd_V.getValue();

        List<Map> resultList = (List<Map>) ssmResult.getResultObject();

        // Get WF_BIZ_APP_ID & WF_DESC_TXT List
        List<Map> wfProcDfnInfoList = getProcNames(bizMsg, glblCmpyCd, searchBizAppId);

        // Get WF_NTFY_TP_CD List
        List<String> tpCdList = new ArrayList<String>();
        if (searchNtfyTpCd.isEmpty()) {
            WF_NTFY_TPTMsgArray ntfyTpTMsgArray = (WF_NTFY_TPTMsgArray) ZYPCodeDataUtil.findAll("WF_NTFY_TP");
            for (int i = 0; i < ntfyTpTMsgArray.length(); i++) {
                WF_NTFY_TPTMsg ntfyTpTMsg = (WF_NTFY_TPTMsg) ntfyTpTMsgArray.get(i);
                if (ntfyTpTMsg.glblCmpyCd.getValue().equals(glblCmpyCd)) {
                    tpCdList.add(ntfyTpTMsg.wfNtfyTpCd.getValue());
                }
            }
        } else {
            tpCdList.add(searchNtfyTpCd);
        }

        // e.g.
        // WF_BIZ_APP_ID, WF_NTFY_TP_CD, WF_USR_ID, WF_NTFY_EML_TP_CD
        // NWWP0002       01             DEFAULT    01
        // NWWP0002       02             DEFAULT    02
        // NWWP0002       01             Q0XXXX     02

        String ezUpTime;
        String ezUpTimeZone;
        String wfBizAppId;
        String wfDescTxt;
        String wfNtfyTpCd;
        String wfUsrId;
        String wfNtfyEmlTpCd;
        String defaultWfNtfyEmlTpCd;

        String wfNtfyEmlTpCdForNotSet = WF_NTFY_EML_TP.HTML_MAIL;

        boolean specifiedUserIdFlag;
        int i = 0;
        for (Map wfProcDfnInfo : wfProcDfnInfoList) {

            if (i >= sMsg.A.length()) {
                bizMsg.setMessageInfo("NZZM0007E");
                break;
            }

            //WF_PROC_NM = WF_BIZ_APP_ID
            String procName = (String) wfProcDfnInfo.get("WF_PROC_NM");
            wfDescTxt = (String) wfProcDfnInfo.get("WF_DESC_TXT");

            for (String tpCd : tpCdList) {
                if (i >= sMsg.A.length()) {
                    break;
                }

                specifiedUserIdFlag = false;
                defaultWfNtfyEmlTpCd = null;
                wfNtfyEmlTpCd = null;
                ezUpTime = null;
                ezUpTimeZone = null;
                for (Map resultMap : resultList) {
                    ezUpTime = (String) resultMap.get("EZUPTIMED");
                    ezUpTimeZone = (String) resultMap.get("EZUPTIMEZONE");

                    wfBizAppId = (String) resultMap.get("WF_BIZ_APP_ID");
                    wfNtfyTpCd = (String) resultMap.get("WF_NTFY_TP_CD");

                    wfUsrId = (String) resultMap.get("WF_USR_ID");
                    wfNtfyEmlTpCd = (String) resultMap.get("WF_NTFY_EML_TP_CD");

                    // Get wfNtfyEmlTpCd value for DEFAULT & Q0XXXX user
                    if (wfBizAppId.equals(procName) && wfNtfyTpCd.equals(tpCd)) {
                        if (NYEL8870Constant.FOR_DEFAULT.equals(wfUsrId)) {
                            defaultWfNtfyEmlTpCd = wfNtfyEmlTpCd;
                            continue;
                        } else {
                            defaultWfNtfyEmlTpCd = null;
                            specifiedUserIdFlag = true;
                            break;
                        }
                    }
                }

                if (!specifiedUserIdFlag) {
                    if (defaultWfNtfyEmlTpCd != null) {
                        wfNtfyEmlTpCd = defaultWfNtfyEmlTpCd;
                    } else {
                        wfNtfyEmlTpCd = wfNtfyEmlTpCdForNotSet;
                    }
                }

                // Set to SMSG
                if (searchWfNtfyEmlTpCd.isEmpty() || searchWfNtfyEmlTpCd.equals(wfNtfyEmlTpCd)) {
                    if (ezUpTime != null) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezUpTime_A, ezUpTime);
                    }
                    if (ezUpTimeZone != null) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezUpTimeZone_A, ezUpTimeZone);
                    }

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).wfBizAppId_A, procName);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).wfDescTxt_A, wfDescTxt);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).wfNtfyTpCd_A, tpCd);

                    WF_NTFY_TPTMsg ntfyTpTMsg = (WF_NTFY_TPTMsg) ZYPCodeDataUtil.findByCode("WF_NTFY_TP", glblCmpyCd, tpCd);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).wfNtfyTpDescTxt_A, ntfyTpTMsg.wfNtfyTpDescTxt.getValue());
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).wfUsrId_A, usrId);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).wfNtfyEmlTpCd_A, wfNtfyEmlTpCd);
                    i++;
                }
            }
        }

        sMsg.A.setValidCount(i);
        bizMsg.A.setValidCount(i);
        EZDMsg.copy(sMsg.A, null, bizMsg.A, null);

        return;
    }

    /**
     * submit
     * @param bizMsg bizMsg
     * @param sMsg sMsg
     * @param usrId usrId
     * @param glblCmpyCd glblCmpyCd
     */
    public static void submit(NYEL8870CMsg bizMsg, NYEL8870SMsg sMsg, String usrId, String glblCmpyCd) {

        List<WF_USR_PRFTMsg> tmsgListForInsert = new ArrayList<WF_USR_PRFTMsg>();
        List<WF_USR_PRFTMsg> tmsgListForUpate = new ArrayList<WF_USR_PRFTMsg>();


        //Check updated data from screen
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            WF_USR_PRFTMsg inWfUsrPrfTMsg = new WF_USR_PRFTMsg();
            WF_USR_PRFTMsg outWfUsrPrfTMsg;

            if (!bizMsg.A.no(i).wfNtfyEmlTpCd_A.getValue().equals(sMsg.A.no(i).wfNtfyEmlTpCd_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(inWfUsrPrfTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inWfUsrPrfTMsg.wfBizAppId, bizMsg.A.no(i).wfBizAppId_A.getValue());
                ZYPEZDItemValueSetter.setValue(inWfUsrPrfTMsg.wfNtfyTpCd, bizMsg.A.no(i).wfNtfyTpCd_A.getValue());
                ZYPEZDItemValueSetter.setValue(inWfUsrPrfTMsg.wfUsrId, bizMsg.A.no(i).wfUsrId_A.getValue());

                outWfUsrPrfTMsg = (WF_USR_PRFTMsg) S21FastTBLAccessor.findByKeyForUpdate(inWfUsrPrfTMsg);
                if (outWfUsrPrfTMsg == null) {
                    // If target data is not exist, insert the data
                    outWfUsrPrfTMsg = new WF_USR_PRFTMsg();
                    ZYPEZDItemValueSetter.setValue(outWfUsrPrfTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(outWfUsrPrfTMsg.wfBizAppId, bizMsg.A.no(i).wfBizAppId_A.getValue());
                    ZYPEZDItemValueSetter.setValue(outWfUsrPrfTMsg.wfNtfyTpCd, bizMsg.A.no(i).wfNtfyTpCd_A.getValue());
                    ZYPEZDItemValueSetter.setValue(outWfUsrPrfTMsg.wfUsrId, bizMsg.A.no(i).wfUsrId_A.getValue());
                    ZYPEZDItemValueSetter.setValue(outWfUsrPrfTMsg.wfNtfyEmlTpCd, bizMsg.A.no(i).wfNtfyEmlTpCd_A.getValue());
                    tmsgListForInsert.add(outWfUsrPrfTMsg);
                } else {
                    //No check
//                  String ezUpTime = bizMsg.A.no(i).ezUpTime_A.getValue();
//                  String ezUpTimeZone = bizMsg.A.no(i).ezUpTimeZone_A.getValue();
//                  String bfEzUpTime = outWfUsrPrfTMsg.ezUpTime.getValue();
//                  String bfEzUpTimeZone = outWfUsrPrfTMsg.ezUpTimeZone.getValue();
//                  if (!ZYPDateUtil.isSameTimeStamp(bfEzUpTime, bfEzUpTimeZone, ezUpTime, ezUpTimeZone)) {
//                      bizMsg.setMessageInfo("ZZPM0025E");
//                      return;
//                  }

                    ZYPEZDItemValueSetter.setValue(outWfUsrPrfTMsg.wfNtfyEmlTpCd, bizMsg.A.no(i).wfNtfyEmlTpCd_A.getValue());
                    tmsgListForUpate.add(outWfUsrPrfTMsg);
                }
            }
        }

        int insertCount = tmsgListForInsert.size();
        if (insertCount > 0) {
            WF_USR_PRFTMsg[] tmsgs = tmsgListForInsert.toArray(new WF_USR_PRFTMsg[insertCount]);
            int cnt = S21FastTBLAccessor.insert(tmsgs);
            if (cnt != insertCount) {
                return;
            }
        }

        int updateCount = tmsgListForUpate.size();
        if (updateCount > 0) {
            WF_USR_PRFTMsg[] tmsgs = tmsgListForUpate.toArray(new WF_USR_PRFTMsg[updateCount]);
            int cnt = S21FastTBLAccessor.update(tmsgs);
            if (cnt != updateCount) {
                return;
            }
        }
        NYEL8870CommonLogic.search(bizMsg, sMsg, usrId, glblCmpyCd);
    }

}
