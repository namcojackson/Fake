/* <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NWZ.NWZC183001;

import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.MODE_DEL;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.MODE_MOD;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.MODE_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM0012E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM0013E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM0078E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM0081E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM0473E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM0912E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1346E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1347E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1348E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1349E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1350E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1351E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1352E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1353E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1356E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1357E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1358E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1359E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1360E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1361E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1362E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1363E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1364E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1365E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1366E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1367E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1368E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1369E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1370E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1371E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1372E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1373E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.NWZM1776E;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.TIME_SUBSTRING_FROM;
import static com.canon.cusa.s21.api.NWZ.NWZC183001.constant.NWZC183001Constant.TIME_SUBSTRING_TO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.CTAC_TPTMsg;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.DS_CPO_CTAC_PSNTMsg;
import business.db.DS_CPO_CTAC_PSNTMsgArray;
import business.db.DS_CPO_DELY_INFOTMsg;
import business.db.DS_CPO_ISTL_INFOTMsg;
import business.db.DS_DELY_TPTMsg;
import business.db.DS_SITE_SRVYTMsg;
import business.db.DS_SITE_SRVYTMsgArray;
import business.db.LINE_BIZ_TPTMsg;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.db.SVC_ISTL_RULETMsg;
import business.db.TECH_MSTRTMsg;
import business.parts.NWZC183001PMsg;
import business.parts.NWZC183001_cpoCtacPsnInfoListPMsg;
import business.parts.NWZC183001_cpoCtacPsnInfoListPMsgArray;
import business.parts.NWZC183001_cpoDelyInfoListPMsg;
import business.parts.NWZC183001_cpoDelyInfoListPMsgArray;
import business.parts.NWZC183001_cpoInstInfoListPMsg;
import business.parts.NWZC183001_cpoInstInfoListPMsgArray;
import business.parts.NWZC183001_siteSrvyInfoListPMsg;
import business.parts.NWZC183001_siteSrvyInfoListPMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * NWZC1830 D&I / Site Survey / Contact API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/25   Fujitsu         C.Yokoi         Create          N/A
 * 2016/09/28   Fujitsu         Y.Kanefusa      Update          S21_NA#14593
 * 2016/10/06   Fujitsu         Y.Kanefusa      Update          S21_NA#14608
 * 2016/11/18   Fujitsu         W.Honda         Update          S21_NA#14593-02
 * 2016/12/05   Fujitsu         T.Yoshida       Update          S21_NA#15889
 * 2017/06/29   Fujitsu         A.Kosai         Update          QC#19218
 * 2017/06/29   Fujitsu         S.Takami        Update          S21_NA#19515
 * 2017/08/15   Fujitsu         N.Sugiura       Update          S21_NA#16452
 * 2018/01/09   Fujitsu         K.Ishizuka      Update          S21_NA#18460(Sol#087)
 * 2018/07/19   Fujitsu         Mz.Takahashi    Update          S21_NA#26188
 * 2019/01/25   Fujitsu         C.Hara          Update          QC#30005
 * 2019/10/30   Fujitsu         Mz.Takahashi    Update          QC#53993
 * 2019/12/19   Fujitsu         K.Kato          Update          QC#54229
 * 2019/12/26   Fujitsu         S.Kosaka        Update          QC#54725
 *</pre>
 */

public class NWZC183001 extends S21ApiCommonBase {
    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /**
     * Constructor
     */
    public NWZC183001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsg NWZC183001PMsg
     * @param type ONBATCH_TYPE
     */
    public void execute(final NWZC183001PMsg pMsg, final ONBATCH_TYPE type) {
        this.msgMap = new S21ApiMessageMap(pMsg);

        doProcess(pMsg);
        msgMap.flush();
    }

    /**
     * execute
     * @param params List<NWZC183001PMsg>
     * @param type ONBATCH_TYPE
     */
    public void execute(final List<NWZC183001PMsg> params, final ONBATCH_TYPE type) {
        for (NWZC183001PMsg param : params) {
            execute(param, type);
        }
    }

    /**
     * doProcess
     * @param msgMap NWZC183001PMsg
     */
    private void doProcess(NWZC183001PMsg pMsg) {

        // Parameter Check
        if (!checkInputParameter(pMsg)) {
            return;
        }

        setOrdShipInfo(pMsg); //2018/01/09 S21_NA#18460(Sol#087) ADD

        // register DS CPO Delivery Info
        if (!registDsCpoDelyInfo(pMsg)) {
            return;
        }

        // register DS CPO Install Info
        if (!registDsCpoIstlInfo(pMsg)) {
            return;
        }

        // register DS Site Survey
        if (!registDsSiteSrvy(pMsg)) {
            return;
        }

        // register DS CPO Contact Person
        // 2019/12/19 QC#54229 Mod Start
        //registDsCpoCtacPsn(pMsg);
        if (!registDsCpoCtacPsn(pMsg)) {
            return;
        }
        // 2019/12/19 QC#54229 Mod End

        // 2019/12/19 QC#54229 Add Start
        // Auto add inbound SiteSurvey/Contacts
        if (!autoAddSsCp(pMsg)) {
            return;
        }
        // 2019/12/19 QC#54229 Add End

    }

    /**
     * checkInputParameter
     * @param pMsg NWZC183001PMsg
     * @return boolean
     */
    private boolean checkInputParameter(NWZC183001PMsg pMsg) {
        // Global Company Code
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0473E);
        }
        // Order Number
        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
            msgMap.addXxMsgId(NWZM0912E);
        }

        // Contact Person Information List Check
        NWZC183001_cpoCtacPsnInfoListPMsg ctacPsnInfo = null;
        for (int index = 0; index < pMsg.cpoCtacPsnInfoList.getValidCount(); index++) {

            ctacPsnInfo = pMsg.cpoCtacPsnInfoList.no(index);

            checkMode(ctacPsnInfo.xxModeCd.getValue());

            // Contact Type Code
            if (MODE_NEW.equals(ctacPsnInfo.xxModeCd.getValue()) || MODE_MOD.equals(ctacPsnInfo.xxModeCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(ctacPsnInfo.ctacPsnTpCd)) {
                    msgMap.addXxMsgId(NWZM1776E);
                }
            }

            // DS CPO Contact Person PK
            if (MODE_MOD.equals(ctacPsnInfo.xxModeCd.getValue()) || MODE_DEL.equals(ctacPsnInfo.xxModeCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(ctacPsnInfo.dsCpoCtacPsnPk)) {
                    msgMap.addXxMsgId(NWZM1346E);
                }
            }
        }

        // CPO Delivery Information List Check
        NWZC183001_cpoDelyInfoListPMsg cpoDelyInfo = null;
        for (int index = 0; index < pMsg.cpoDelyInfoList.getValidCount(); index++) {

            cpoDelyInfo = pMsg.cpoDelyInfoList.no(index);

            checkMode(cpoDelyInfo.xxModeCd.getValue());

            // DS CPO Delivery Info PK
            if (MODE_MOD.equals(cpoDelyInfo.xxModeCd.getValue()) || MODE_DEL.equals(cpoDelyInfo.xxModeCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(cpoDelyInfo.dsCpoDelyInfoPk)) {
                    msgMap.addXxMsgId(NWZM1349E);
                }
            }
        }

        // CPO Install Information List Check
        NWZC183001_cpoInstInfoListPMsg cpoInstInfo = null;
        for (int index = 0; index < pMsg.cpoInstInfoList.getValidCount(); index++) {

            cpoInstInfo = pMsg.cpoInstInfoList.no(index);

            checkMode(cpoInstInfo.xxModeCd.getValue());

            // DS CPO Install Info PK
            if (MODE_MOD.equals(cpoInstInfo.xxModeCd.getValue()) || MODE_DEL.equals(cpoInstInfo.xxModeCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(cpoInstInfo.dsCpoIstlInfoPk)) {
                    msgMap.addXxMsgId(NWZM1348E);
                }
            }
        }

        // DS Site Survey Information List Check
        NWZC183001_siteSrvyInfoListPMsg siteSrvytInfo = null;
        for (int index = 0; index < pMsg.siteSrvyInfoList.getValidCount(); index++) {

            siteSrvytInfo = pMsg.siteSrvyInfoList.no(index);

            checkMode(siteSrvytInfo.xxModeCd.getValue());

            // DS Site Survey PK
            if (MODE_MOD.equals(siteSrvytInfo.xxModeCd.getValue()) || MODE_DEL.equals(siteSrvytInfo.xxModeCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(siteSrvytInfo.dsSiteSrvyPk)) {
                    msgMap.addXxMsgId(NWZM1347E);
                }
            }
        }
        return !isError(pMsg);
    }

    /**
     * registDsCpoDelyInfo
     * @param pMsg NWZC183001PMsg
     * @return boolean
     */
    private boolean registDsCpoDelyInfo(NWZC183001PMsg pMsg) {
        List<DS_CPO_DELY_INFOTMsg> insTMsgList = new ArrayList<DS_CPO_DELY_INFOTMsg>();
        List<DS_CPO_DELY_INFOTMsg> updTMsgList = new ArrayList<DS_CPO_DELY_INFOTMsg>();
        List<DS_CPO_DELY_INFOTMsg> rmvTMsgList = new ArrayList<DS_CPO_DELY_INFOTMsg>();
        List<CPOTMsg> cpoUpdTMsgList = new ArrayList<CPOTMsg>();
        List<CPO_DTLTMsg> cpoDtlUpdTMsgList = new ArrayList<CPO_DTLTMsg>();
        List<SHPG_PLNTMsg> shpgPlnTMsgList = new ArrayList<SHPG_PLNTMsg>();
        // S21_NA#26188 20180719 Add Start
        List<Integer> hdrIdxList = new ArrayList<Integer>();
        // S21_NA#26188 20180719 Add End

        // CPO Delivery Information List Check
        NWZC183001_cpoDelyInfoListPMsg cpoDelyInfo = null;

        // QC#14608 2016/10/06 Add Start
        NWZC183001_cpoDelyInfoListPMsg hdrInfo = null;
        for (int index = 0; index < pMsg.cpoDelyInfoList.getValidCount(); index++) {
            cpoDelyInfo = pMsg.cpoDelyInfoList.no(index);
            if (!ZYPCommonFunc.hasValue(cpoDelyInfo.dsCpoConfigPk)) {
                hdrInfo = cpoDelyInfo;
                // S21_NA#26188 20180719 Add Start
                hdrIdxList.add(index);
                // S21_NA#26188 20180719 Add End
            }
        }
        Map<String, String> key = new HashMap<String, String>();
        key.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        key.put("ordNum", pMsg.cpoOrdNum.getValue());
        key.put("shipToCustCd", pMsg.cpoOrdNum.getValue());
        // 2019/01/25 QC#30005 Del Start
        // 2017/06/29 QC#19218 Add Start
        // key.put("dropShipFlg", ZYPConstant.FLG_OFF_N);
        // 2017/06/29 QC#19218 Add End
        // 2019/01/25 QC#30005 Del End
        int idx = pMsg.cpoDelyInfoList.getValidCount();

        if (hdrInfo != null) {
            if (!MODE_DEL.equals(hdrInfo.xxModeCd.getValue())) { // S21_NA#15889 Add
                List<Map< ? , ? >> list = (List<Map< ? , ? >>) ssmBatchClient.queryObjectList("queryDelyInfoHeader", key);
                for (Map< ? , ? > map : list) {
                    if (!hasConfigNo(pMsg.cpoDelyInfoList, (BigDecimal) map.get("DS_CPO_CONFIG_PK"))) {
                        cpoDelyInfo = pMsg.cpoDelyInfoList.no(idx++);
                        if (ZYPCommonFunc.hasValue((BigDecimal) map.get("DS_CPO_DELY_INFO_PK"))) {
                            ZYPEZDItemValueSetter.setValue(cpoDelyInfo.xxModeCd, MODE_MOD);
                            ZYPEZDItemValueSetter.setValue(cpoDelyInfo.dsCpoDelyInfoPk, (BigDecimal) map.get("DS_CPO_DELY_INFO_PK"));
                        } else {
                            ZYPEZDItemValueSetter.setValue(cpoDelyInfo.xxModeCd, MODE_NEW);
                        }
                        ZYPEZDItemValueSetter.setValue(cpoDelyInfo.dsCpoConfigPk, (BigDecimal) map.get("DS_CPO_CONFIG_PK"));
                        ZYPEZDItemValueSetter.setValue(cpoDelyInfo.dsDelyTpCd, hdrInfo.dsDelyTpCd);
                        ZYPEZDItemValueSetter.setValue(cpoDelyInfo.rddDt, hdrInfo.rddDt);
                        // 2018/01/09 S21_NA#18460(Sol#087) MOD START
                        //ZYPEZDItemValueSetter.setValue(cpoDelyInfo.opsFromHourMn, hdrInfo.opsFromHourMn);
                        //ZYPEZDItemValueSetter.setValue(cpoDelyInfo.opsToHourMn, hdrInfo.opsToHourMn);
                        if (ZYPCommonFunc.hasValue(hdrInfo.opsFromHourMn) && ZYPCommonFunc.hasValue(hdrInfo.opsToHourMn)) {
                            ZYPEZDItemValueSetter.setValue(cpoDelyInfo.opsFromHourMn, getConvertTime((String) map.get("SHIP_TO_CTRY_CD"), //
                                    (String) map.get("SHIP_TO_POST_CD"), hdrInfo.opsFromHourMn.getValue()));
                            ZYPEZDItemValueSetter.setValue(cpoDelyInfo.opsToHourMn, getConvertTime((String) map.get("SHIP_TO_CTRY_CD"), //
                                    (String) map.get("SHIP_TO_POST_CD"), hdrInfo.opsToHourMn.getValue()));
                        }
                        // 2018/01/09 S21_NA#18460(Sol#087) MOD END
                        ZYPEZDItemValueSetter.setValue(cpoDelyInfo.loadDockAvalFlg, hdrInfo.loadDockAvalFlg);
                        ZYPEZDItemValueSetter.setValue(cpoDelyInfo.stairCrawReqFlg, hdrInfo.stairCrawReqFlg);
                        ZYPEZDItemValueSetter.setValue(cpoDelyInfo.stairCrawNum, hdrInfo.stairCrawNum);
                        ZYPEZDItemValueSetter.setValue(cpoDelyInfo.elevAvalFlg, hdrInfo.elevAvalFlg);
                        ZYPEZDItemValueSetter.setValue(cpoDelyInfo.delyAddlCmntTxt, hdrInfo.delyAddlCmntTxt);
                        ZYPEZDItemValueSetter.setValue(cpoDelyInfo.xxYesNoCd_DI, ZYPConstant.FLG_ON_Y); // 2018/01/09 S21_NA#18460(Sol#087) ADD
                    }
                }
            }
        }
        // S21_NA#26188 20180719 Del Start
//      else {
//          List<Map< ? , ? >> list = (List<Map< ? , ? >>) ssmBatchClient.queryObjectList("queryDelyInfoNoHeader", key);
//          for (Map< ? , ? > map : list) {
//              if (!hasConfigNo(pMsg.cpoDelyInfoList, (BigDecimal) map.get("DS_CPO_CONFIG_PK"))) {
//                  cpoDelyInfo = pMsg.cpoDelyInfoList.no(idx++);
//                  ZYPEZDItemValueSetter.setValue(cpoDelyInfo.xxModeCd, MODE_NEW);
//                  ZYPEZDItemValueSetter.setValue(cpoDelyInfo.dsCpoConfigPk, (BigDecimal) map.get("DS_CPO_CONFIG_PK"));
//                  ZYPEZDItemValueSetter.setValue(cpoDelyInfo.dsDelyTpCd, (String) map.get("DS_DELY_TP_CD"));
//                  ZYPEZDItemValueSetter.setValue(cpoDelyInfo.rddDt, (String) map.get("RDD_DT"));
//                  ZYPEZDItemValueSetter.setValue(cpoDelyInfo.opsFromHourMn, (String) map.get("OPS_FROM_HOUR_MN"));
//                  ZYPEZDItemValueSetter.setValue(cpoDelyInfo.opsToHourMn, (String) map.get("OPS_TO_HOUR_MN"));
//                  ZYPEZDItemValueSetter.setValue(cpoDelyInfo.loadDockAvalFlg, (String) map.get("LOAD_DOCK_AVAL_FLG"));
//                  ZYPEZDItemValueSetter.setValue(cpoDelyInfo.stairCrawReqFlg, (String) map.get("STAIR_CRAW_REQ_FLG"));
//                  ZYPEZDItemValueSetter.setValue(cpoDelyInfo.stairCrawNum, (String) map.get("STAIR_CRAW_NUM"));
//                  ZYPEZDItemValueSetter.setValue(cpoDelyInfo.elevAvalFlg, (String) map.get("ELEV_AVAL_FLG"));
//                  ZYPEZDItemValueSetter.setValue(cpoDelyInfo.delyAddlCmntTxt, (String) map.get("DELY_ADDL_CMNT_TXT"));
//                  ZYPEZDItemValueSetter.setValue(cpoDelyInfo.xxYesNoCd_DI, ZYPConstant.FLG_ON_Y); //     2018/01/09 S21_NA#18460(Sol#087) ADD
//              }
//          }
//      }
        // S21_NA#26188 20180719 Del End

        pMsg.cpoDelyInfoList.setValidCount(idx);
        // QC#14608 2016/10/06 Add End
        // 2019/12/26 QC#54725 Add Start
        Map<BigDecimal, NWZC183001_cpoDelyInfoListPMsg> clobMap = new HashMap<BigDecimal, NWZC183001_cpoDelyInfoListPMsg>();
        // 2019/12/26 QC#54725 Add End

        for (int index = 0; index < pMsg.cpoDelyInfoList.getValidCount(); index++) {

            // S21_NA#26188 20180719 Add Start
            //except header
            if (hdrIdxList.contains(index)) {
                continue;
            }
            // S21_NA#26188 20180719 Add End

            cpoDelyInfo = pMsg.cpoDelyInfoList.no(index);

            // Master Check: DS Delivery Type Code
            if (ZYPCommonFunc.hasValue(cpoDelyInfo.dsDelyTpCd)) {
                DS_DELY_TPTMsg delyTMsg = new DS_DELY_TPTMsg();
                ZYPEZDItemValueSetter.setValue(delyTMsg.dsDelyTpCd, cpoDelyInfo.dsDelyTpCd);
                ZYPEZDItemValueSetter.setValue(delyTMsg.glblCmpyCd, pMsg.glblCmpyCd);

                DS_DELY_TPTMsg tMsgResult = (DS_DELY_TPTMsg) S21CacheTBLAccessor.findByKey(delyTMsg);
                // has no result
                if (tMsgResult == null) {
                    msgMap.addXxMsgId(NWZM1369E);
                    return false;
                }
            }

            // Add Insert
            if (MODE_NEW.equals(cpoDelyInfo.xxModeCd.getValue())) {
                // 2019/12/26 QC#54725 Mod Start
                //addInsDelyTMsg(pMsg, cpoDelyInfo, insTMsgList);
                addInsDelyTMsg(pMsg, cpoDelyInfo, insTMsgList, clobMap);
                // 2019/12/26 QC#54725 Mod End

                // Add Modify
            } else if (MODE_MOD.equals(cpoDelyInfo.xxModeCd.getValue())) {

                DS_CPO_DELY_INFOTMsg updDsCpoDelyInfoTMsg = getUpdDsDelyInfoTMsg(pMsg.glblCmpyCd.getValue(), cpoDelyInfo.dsCpoDelyInfoPk.getValue());
                if (updDsCpoDelyInfoTMsg == null) {
                    msgMap.addXxMsgId(NWZM1350E);
                    return false;
                }

                // if RDD_DT is modify, set to TMsg for update
                if (ZYPCommonFunc.hasValue(cpoDelyInfo.rddDt)
                        && !cpoDelyInfo.rddDt.getValue().equals(updDsCpoDelyInfoTMsg.rddDt.getValue())) {
                    List<OrderDtlInfoBean> orderDtlBeanList = new ArrayList<OrderDtlInfoBean>();

                    // Configuration mode
                    if (ZYPCommonFunc.hasValue(cpoDelyInfo.dsCpoConfigPk)) {
                        orderDtlBeanList = getDsCpoDtlInfo(pMsg, cpoDelyInfo, orderDtlBeanList);

                    // Order header Mode
                    } else {
                        orderDtlBeanList = getCpoDtlInfo(pMsg, orderDtlBeanList);

                        // set RDD_DT to CPOTMsg List
                        cpoUpdTMsgList = updCpoTMsg(pMsg, cpoDelyInfo, cpoUpdTMsgList);
                    }

                    if (hasList(orderDtlBeanList)) {
                        for (OrderDtlInfoBean orderDtl : orderDtlBeanList) {
                            // set RDD_DT to CPO_DTLTMsg List
                            cpoDtlUpdTMsgList = updCpoDtlTMsg(pMsg, cpoDelyInfo, orderDtl, cpoDtlUpdTMsgList);

                            // set RDD_DT to SHPG_PLNTMsg List
                            shpgPlnTMsgList = updShpgPlnTMsg(pMsg, cpoDelyInfo, orderDtl, shpgPlnTMsgList);
                        }
                    }
                }

                // set DS_CPO_DELY_INFOTMsg
                // 2019/12/26 QC#54725 Mod Start
                //updTMsgList = addUpdDelyTMsg(pMsg, cpoDelyInfo, updTMsgList, updDsCpoDelyInfoTMsg);
                updTMsgList = addUpdDelyTMsg(pMsg, cpoDelyInfo, updTMsgList, updDsCpoDelyInfoTMsg, clobMap);
                // 2019/12/26 QC#54725 Mod End

            // Add Delete
            } else {
                if (!addRmvDelyTMsg(pMsg, cpoDelyInfo, rmvTMsgList)) {
                    msgMap.addXxMsgId(NWZM1358E);
                    return false;
                }
            }
        }

        // insert
        if (!insTMsgList.isEmpty()) {
            // 2019/12/26 QC#54725 Mod Start
//            int insCnt = S21FastTBLAccessor.insert(insTMsgList.toArray(new DS_CPO_DELY_INFOTMsg[0]));
//            if (insCnt != insTMsgList.size()) {
//                msgMap.addXxMsgId(NWZM1356E);
//                return false;
//            }
            for (DS_CPO_DELY_INFOTMsg dsCpoDelyInfoTMsg : insTMsgList) {
                EZDTBLAccessor.insert(dsCpoDelyInfoTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsCpoDelyInfoTMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM1356E);
                    return false;
                }
                String schedulingComment = null;
                if (clobMap.get(dsCpoDelyInfoTMsg.dsCpoDelyInfoPk.getValue()) != null) {
                    schedulingComment = ((NWZC183001_cpoDelyInfoListPMsg) clobMap.get(dsCpoDelyInfoTMsg.dsCpoDelyInfoPk.getValue())).xxAttDataCmntTxt.getValue();
                } else {
                    continue;
                }
                if (!new SchedulingCommentsClobAccessor(dsCpoDelyInfoTMsg).updateSql(schedulingComment)) {
                    msgMap.addXxMsgId(NWZM1356E);
                    return false;
                }
            }
            // 2019/12/26 QC#54725 Mod End
        }

        // update
        if (!updTMsgList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updTMsgList.toArray(new DS_CPO_DELY_INFOTMsg[0]));
            if (updCnt != updTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1357E);
                return false;
            }
            // 2019/12/26 QC#54725 Add Start
            for (DS_CPO_DELY_INFOTMsg dsCpoDelyInfoTMsg : updTMsgList) {
                String schedulingComment = null;
                if (clobMap.get(dsCpoDelyInfoTMsg.dsCpoDelyInfoPk.getValue()) != null) {
                    schedulingComment = ((NWZC183001_cpoDelyInfoListPMsg) clobMap.get(dsCpoDelyInfoTMsg.dsCpoDelyInfoPk.getValue())).xxAttDataCmntTxt.getValue();
                } else {
                    continue;
                }
                if (!new SchedulingCommentsClobAccessor(dsCpoDelyInfoTMsg).updateSql(schedulingComment)) {
                    msgMap.addXxMsgId(NWZM1357E);
                    return false;
                }
            }
            // 2019/12/26 QC#54725 Add End
        }

        // remove
        if (!rmvTMsgList.isEmpty()) {
            int rmvCnt = S21FastTBLAccessor.removeLogical(rmvTMsgList.toArray(new DS_CPO_DELY_INFOTMsg[0]));
            if (rmvCnt != rmvTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1358E);
                return false;
            }
        }

        // CPO update
        if (!cpoUpdTMsgList.isEmpty()) {
            int updCpoCnt = S21FastTBLAccessor.update(cpoUpdTMsgList.toArray(new CPOTMsg[0]));
            if (updCpoCnt != cpoUpdTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1368E);
                return false;
            }
        }

        // CPO Detail update
        if (!cpoDtlUpdTMsgList.isEmpty()) {
            int updCpoDtlCnt = S21FastTBLAccessor.update(cpoDtlUpdTMsgList.toArray(new CPO_DTLTMsg[0]));
            if (updCpoDtlCnt != cpoDtlUpdTMsgList.size()) {
                msgMap.addXxMsgId(NWZM0081E);
                return false;
            }
        }

        // Shipping Plan update
        if (!shpgPlnTMsgList.isEmpty()) {
            int updShpgPlnCnt = S21FastTBLAccessor.update(shpgPlnTMsgList.toArray(new SHPG_PLNTMsg[0]));
            if (updShpgPlnCnt != shpgPlnTMsgList.size()) {
                msgMap.addXxMsgId(NWZM0078E);
                return false;
            }
        }
        return true;
    }

    /**
     * registDsCpoIstlInfo
     * @param pMsg NWZC183001PMsg
     * @return boolean
     */
    private boolean registDsCpoIstlInfo(NWZC183001PMsg pMsg) {
        List<DS_CPO_ISTL_INFOTMsg> insTMsgList = new ArrayList<DS_CPO_ISTL_INFOTMsg>();
        List<DS_CPO_ISTL_INFOTMsg> updTMsgList = new ArrayList<DS_CPO_ISTL_INFOTMsg>();
        List<DS_CPO_ISTL_INFOTMsg> rmvTMsgList = new ArrayList<DS_CPO_ISTL_INFOTMsg>();
        // S21_NA#26188 20180719 Add Start
        List<Integer> hdrIdxList = new ArrayList<Integer>();
        // S21_NA#26188 20180719 Add End

        // CPO Install Information List Check
        NWZC183001_cpoInstInfoListPMsg cpoInstlInfo = null;

        // QC#14608 2016/10/06 Add Start
        NWZC183001_cpoInstInfoListPMsg hdrInfo = null;
        for (int index = 0; index < pMsg.cpoInstInfoList.getValidCount(); index++) {
            cpoInstlInfo = pMsg.cpoInstInfoList.no(index);
            if (!ZYPCommonFunc.hasValue(cpoInstlInfo.dsCpoConfigPk)) {
                hdrInfo = cpoInstlInfo;
                // S21_NA#26188 20180719 Add Start
                hdrIdxList.add(index);
                // S21_NA#26188 20180719 Add End
            }
        }
        Map<String, String> key = new HashMap<String, String>();
        key.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        key.put("ordNum", pMsg.cpoOrdNum.getValue());
        // 2019/01/25 QC#30005 Del Start
        // 2017/06/29 QC#19218 Add Start
        // key.put("dropShipFlg", ZYPConstant.FLG_OFF_N);
        // 2017/06/29 QC#19218 Add End
        // 2019/01/25 QC#30005 Del End
        int idx = pMsg.cpoInstInfoList.getValidCount();

        if (hdrInfo != null) {
            if (!MODE_DEL.equals(hdrInfo.xxModeCd.getValue())) { // S21_NA#15889 Add
                List<Map< ? , ? >> list = (List<Map< ? , ? >>) ssmBatchClient.queryObjectList("queryIstlInfoHeader", key);
                for (Map< ? , ? > map : list) {
                    if (!hasConfigNo(pMsg.cpoInstInfoList, (BigDecimal) map.get("DS_CPO_CONFIG_PK"))) {
                        cpoInstlInfo = pMsg.cpoInstInfoList.no(idx++);
                        if (ZYPCommonFunc.hasValue((BigDecimal) map.get("DS_CPO_ISTL_INFO_PK"))) {
                            ZYPEZDItemValueSetter.setValue(cpoInstlInfo.xxModeCd, MODE_MOD);
                            ZYPEZDItemValueSetter.setValue(cpoInstlInfo.dsCpoIstlInfoPk, (BigDecimal) map.get("DS_CPO_ISTL_INFO_PK"));
                        } else {
                            ZYPEZDItemValueSetter.setValue(cpoInstlInfo.xxModeCd, MODE_NEW);
                        }
                        ZYPEZDItemValueSetter.setValue(cpoInstlInfo.dsCpoConfigPk, (BigDecimal) map.get("DS_CPO_CONFIG_PK"));
                        ZYPEZDItemValueSetter.setValue(cpoInstlInfo.istlDivCd, hdrInfo.istlDivCd);
                        ZYPEZDItemValueSetter.setValue(cpoInstlInfo.istlTechCd, hdrInfo.istlTechCd);
                        ZYPEZDItemValueSetter.setValue(cpoInstlInfo.rqstIstlDt, hdrInfo.rqstIstlDt);
                        // 2018/01/09 S21_NA#18460(Sol#087) MOD START
                        //ZYPEZDItemValueSetter.setValue(cpoInstlInfo.rqstIstlTm, hdrInfo.rqstIstlTm);
                        if (ZYPCommonFunc.hasValue(hdrInfo.rqstIstlTm)) {
                            ZYPEZDItemValueSetter.setValue(cpoInstlInfo.rqstIstlTm, getConvertTime((String) map.get("SHIP_TO_CTRY_CD"), //
                                    (String) map.get("SHIP_TO_POST_CD"), hdrInfo.rqstIstlTm.getValue()));
                        }
                        // 2018/01/09 S21_NA#18460(Sol#087) MOD END
                        ZYPEZDItemValueSetter.setValue(cpoInstlInfo.istlAddlCmntTxt, hdrInfo.istlAddlCmntTxt);
                        ZYPEZDItemValueSetter.setValue(cpoInstlInfo.svcIstlRuleNum, hdrInfo.svcIstlRuleNum);
                        ZYPEZDItemValueSetter.setValue(cpoInstlInfo.xxYesNoCd_II, ZYPConstant.FLG_ON_Y); // 2018/01/09 S21_NA#18460(Sol#087) ADD

                        // 2019/10/30 QC#53993 Add Start
                        ZYPEZDItemValueSetter.setValue(cpoInstlInfo.istlBySvcPrvdPtyCd, hdrInfo.istlBySvcPrvdPtyCd);
                        ZYPEZDItemValueSetter.setValue(cpoInstlInfo.svcBySvcPrvdPtyCd, hdrInfo.svcBySvcPrvdPtyCd);
                        // 2019/10/30 QC#53993 Add End
                    }
                }
            }
        }
        // S21_NA#26188 20180719 Del Start
//        else {
//            List<Map< ? , ? >> list = (List<Map< ? , ? >>) ssmBatchClient.queryObjectList("queryIstlInfoNoHeader", key);
//            for (Map< ? , ? > map : list) {
//                if (!hasConfigNo(pMsg.cpoInstInfoList, (BigDecimal) map.get("DS_CPO_CONFIG_PK"))) {
//                    cpoInstlInfo = pMsg.cpoInstInfoList.no(idx++);
//                    ZYPEZDItemValueSetter.setValue(cpoInstlInfo.xxModeCd, MODE_NEW);
//                    ZYPEZDItemValueSetter.setValue(cpoInstlInfo.dsCpoConfigPk, (BigDecimal) map.get("DS_CPO_CONFIG_PK"));
//                    ZYPEZDItemValueSetter.setValue(cpoInstlInfo.istlDivCd, (String) map.get("ISTL_DIV_CD"));
//                    ZYPEZDItemValueSetter.setValue(cpoInstlInfo.istlTechCd, (String) map.get("ISTL_TECH_CD"));
//                    ZYPEZDItemValueSetter.setValue(cpoInstlInfo.rqstIstlDt, (String) map.get("RQST_ISTL_DT"));
//                    ZYPEZDItemValueSetter.setValue(cpoInstlInfo.rqstIstlTm, (String) map.get("RQST_ISTL_TM"));
//                    ZYPEZDItemValueSetter.setValue(cpoInstlInfo.istlAddlCmntTxt, (String) map.get("ISTL_ADDL_CMNT_TXT"));
//                    ZYPEZDItemValueSetter.setValue(cpoInstlInfo.svcIstlRuleNum, (String) map.get("SVC_ISTL_RULE_NUM"));
//                    ZYPEZDItemValueSetter.setValue(cpoInstlInfo.xxYesNoCd_II, ZYPConstant.FLG_ON_Y); // 2018/01/09 S21_NA#18460(Sol#087) ADD
//                }
//            }
//        }
        // S21_NA#26188 20180719 Del End

        pMsg.cpoInstInfoList.setValidCount(idx);
        // QC#14608 2016/10/06 Add End

        for (int index = 0; index < pMsg.cpoInstInfoList.getValidCount(); index++) {

            // S21_NA#26188 20180719 Add Start
            //except header
            if (hdrIdxList.contains(index)) {
                continue;
            }
            // S21_NA#26188 20180719 Add End

            cpoInstlInfo = pMsg.cpoInstInfoList.no(index);

            // Master Check : Install Type Code
            if (ZYPCommonFunc.hasValue(cpoInstlInfo.svcIstlRuleNum)) {
                SVC_ISTL_RULETMsg ruleTMsg = new SVC_ISTL_RULETMsg();
                ZYPEZDItemValueSetter.setValue(ruleTMsg.svcIstlRuleNum, cpoInstlInfo.svcIstlRuleNum);
                ZYPEZDItemValueSetter.setValue(ruleTMsg.glblCmpyCd, pMsg.glblCmpyCd);

                SVC_ISTL_RULETMsg tMsgResult = (SVC_ISTL_RULETMsg) S21CacheTBLAccessor.findByKey(ruleTMsg);
                // has no result
                if (tMsgResult == null) {
                    msgMap.addXxMsgId(NWZM1370E);
                    return false;
                }
            }

            // Master Check : Install Division Code
            if (ZYPCommonFunc.hasValue(cpoInstlInfo.istlDivCd)) {
                LINE_BIZ_TPTMsg lineBizTMsg = new LINE_BIZ_TPTMsg();
                ZYPEZDItemValueSetter.setValue(lineBizTMsg.lineBizTpCd, cpoInstlInfo.istlDivCd);
                ZYPEZDItemValueSetter.setValue(lineBizTMsg.glblCmpyCd, pMsg.glblCmpyCd);

                LINE_BIZ_TPTMsg tMsgResult = (LINE_BIZ_TPTMsg) S21CacheTBLAccessor.findByKey(lineBizTMsg);
                // has no result
                if (tMsgResult == null) {
                    msgMap.addXxMsgId(NWZM1371E);
                    return false;
                }
            }

            // Master Check : Install Technician Code
            if (ZYPCommonFunc.hasValue(cpoInstlInfo.istlTechCd)) {
                TECH_MSTRTMsg techTMsg = new TECH_MSTRTMsg();
                ZYPEZDItemValueSetter.setValue(techTMsg.techTocCd, cpoInstlInfo.istlTechCd);
                ZYPEZDItemValueSetter.setValue(techTMsg.glblCmpyCd, pMsg.glblCmpyCd);

                TECH_MSTRTMsg tMsgResult = (TECH_MSTRTMsg) S21CacheTBLAccessor.findByKey(techTMsg);
                // has no result
                if (tMsgResult == null) {
                    msgMap.addXxMsgId(NWZM1372E);
                    return false;
                }
            }

            // Add Insert
            if (MODE_NEW.equals(cpoInstlInfo.xxModeCd.getValue())) {
                addInsInstlTMsg(pMsg, cpoInstlInfo, insTMsgList);

                // Add Modify
            } else if (MODE_MOD.equals(cpoInstlInfo.xxModeCd.getValue())) {
                if (!addUpdInstlTMsg(pMsg, cpoInstlInfo, updTMsgList)) {
                    msgMap.addXxMsgId(NWZM1351E);
                    return false;
                }

                // Add Delete
            } else {
                if (!addRmvInstlTMsg(pMsg, cpoInstlInfo, rmvTMsgList)) {
                    msgMap.addXxMsgId(NWZM1361E);
                    return false;
                }
            }
        }

        // insert
        if (!insTMsgList.isEmpty()) {
            int insCnt = S21FastTBLAccessor.insert(insTMsgList.toArray(new DS_CPO_ISTL_INFOTMsg[0]));
            if (insCnt != insTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1359E);
                return false;
            }
        }

        // update
        if (!updTMsgList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updTMsgList.toArray(new DS_CPO_ISTL_INFOTMsg[0]));
            if (updCnt != updTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1360E);
                return false;
            }
        }

        // remove
        if (!rmvTMsgList.isEmpty()) {
            int rmvCnt = S21FastTBLAccessor.removeLogical(rmvTMsgList.toArray(new DS_CPO_ISTL_INFOTMsg[0]));
            if (rmvCnt != rmvTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1361E);
                return false;
            }
        }
        // QC#14593 2016/09/28 Mod Start
        // 2017/06/29 S21_NA#19515 Mod Start
//        boolean result = updateCustIstlFlg(pMsg);
        boolean result = updateCustIstlFlg(insTMsgList, updTMsgList);
        // 2017/06/29 S21_NA#19515 Mod End
        if (!result) {
            return false;
        }
        // QC#14593 2016/09/28 Mod End
        return true;
    }
    // QC#14608 2016/10/06 Add Start

    /**
     * hasConfigNo
     * @param list NWZC183001_cpoDelyInfoListPMsgArray
     * @param dsCpoConfigPk BigDecimal
     * @return boolean
     */
    private boolean hasConfigNo(NWZC183001_cpoDelyInfoListPMsgArray list, BigDecimal dsCpoConfigPk) {
        NWZC183001_cpoDelyInfoListPMsg cpoDelylInfo = null;
        for (int index = 0; index < list.getValidCount(); index++) {
            cpoDelylInfo = list.no(index);
            if (ZYPCommonFunc.hasValue(cpoDelylInfo.dsCpoConfigPk) //
                    && dsCpoConfigPk.compareTo(cpoDelylInfo.dsCpoConfigPk.getValue()) == 0) {
                 return true;
            }
        }
        return false;
    }
    /**
     * hasConfigNo
     * @param list NWZC183001_cpoInstInfoListPMsgArray
     * @param dsCpoConfigPk BigDecimal
     * @return boolean
     */
    private boolean hasConfigNo(NWZC183001_cpoInstInfoListPMsgArray list, BigDecimal dsCpoConfigPk) {
        NWZC183001_cpoInstInfoListPMsg cpoInstlInfo = null;
        for (int index = 0; index < list.getValidCount(); index++) {
            cpoInstlInfo = list.no(index);
            if (ZYPCommonFunc.hasValue(cpoInstlInfo.dsCpoConfigPk) //
                    && dsCpoConfigPk.compareTo(cpoInstlInfo.dsCpoConfigPk.getValue()) == 0) {
                 return true;
            }
        }
        return false;
    }
    /**
     * hasConfigNo
     * @param list NWZC183001_cpoInstInfoListPMsgArray
     * @param dsCpoConfigPk BigDecimal
     * @return boolean
     */
    private boolean hasConfigNo(NWZC183001_siteSrvyInfoListPMsgArray list, BigDecimal dsCpoConfigPk) {
        NWZC183001_siteSrvyInfoListPMsg cpoInstlInfo = null;
        for (int index = 0; index < list.getValidCount(); index++) {
            cpoInstlInfo = list.no(index);
            if (ZYPCommonFunc.hasValue(cpoInstlInfo.dsCpoConfigPk) //
                    && dsCpoConfigPk.compareTo(cpoInstlInfo.dsCpoConfigPk.getValue()) == 0) {
                 return true;
            }
        }
        return false;
    }
    /**
     * hasConfigNo
     * @param list NWZC183001_cpoInstInfoListPMsgArray
     * @param dsCpoConfigPk BigDecimal
     * @return boolean
     */
    private boolean hasConfigNo(NWZC183001_cpoCtacPsnInfoListPMsgArray list, BigDecimal dsCpoConfigPk) {
        NWZC183001_cpoCtacPsnInfoListPMsg cpoInstlInfo = null;
        for (int index = 0; index < list.getValidCount(); index++) {
            cpoInstlInfo = list.no(index);
            if (ZYPCommonFunc.hasValue(cpoInstlInfo.dsCpoConfigPk) //
                    && dsCpoConfigPk.compareTo(cpoInstlInfo.dsCpoConfigPk.getValue()) == 0) {
                 return true;
            }
        }
        return false;
    }
    // QC#14608 2016/10/06 Add End

    /**
     * registDsSiteSrvy
     * @param pMsg NWZC183001PMsg
     * @return boolean
     */
    private boolean registDsSiteSrvy(NWZC183001PMsg pMsg) {
        List<DS_SITE_SRVYTMsg> insTMsgList = new ArrayList<DS_SITE_SRVYTMsg>();
        List<DS_SITE_SRVYTMsg> updTMsgList = new ArrayList<DS_SITE_SRVYTMsg>();
        List<DS_SITE_SRVYTMsg> rmvTMsgList = new ArrayList<DS_SITE_SRVYTMsg>();
        // S21_NA#26188 20180719 Add Start
        List<Integer> hdrIdxList = new ArrayList<Integer>();
        // S21_NA#26188 20180719 Add End

        // CPO SiteSurvey Information List Check
        NWZC183001_siteSrvyInfoListPMsg cpoSiteSrvyInfo = null;

        // QC#14608 2016/10/06 Add Start
        NWZC183001_siteSrvyInfoListPMsg hdrInfo = null;
        for (int index = 0; index < pMsg.siteSrvyInfoList.getValidCount(); index++) {
            cpoSiteSrvyInfo = pMsg.siteSrvyInfoList.no(index);
            if (!ZYPCommonFunc.hasValue(cpoSiteSrvyInfo.dsCpoConfigPk)) {
                hdrInfo = cpoSiteSrvyInfo;
                // S21_NA#26188 20180719 Add Start
                hdrIdxList.add(index);
                // S21_NA#26188 20180719 Add End
            }
        }
        Map<String, String> key = new HashMap<String, String>();
        key.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        key.put("ordNum", pMsg.cpoOrdNum.getValue());
        // 2019/01/25 QC#30005 Del Start
        // 2017/06/29 QC#19218 Add Start
        // key.put("dropShipFlg", ZYPConstant.FLG_OFF_N);
        // 2017/06/29 QC#19218 Add End
        // 2019/01/25 QC#30005 Del End
        int idx = pMsg.siteSrvyInfoList.getValidCount();

        if (hdrInfo != null) {
            if (!MODE_DEL.equals(hdrInfo.xxModeCd.getValue())) { // S21_NA#15889 Add
                List<Map< ? , ? >> list = (List<Map< ? , ? >>) ssmBatchClient.queryObjectList("querySiteSrvyInfoHeader", key);
                for (Map< ? , ? > map : list) {
                    if (!hasConfigNo(pMsg.siteSrvyInfoList, (BigDecimal) map.get("DS_CPO_CONFIG_PK"))) {
                        cpoSiteSrvyInfo = pMsg.siteSrvyInfoList.no(idx++);
                        if (ZYPCommonFunc.hasValue((BigDecimal) map.get("DS_SITE_SRVY_PK"))) {
                            ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.xxModeCd, MODE_MOD);
                            ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.dsSiteSrvyPk, (BigDecimal) map.get("DS_SITE_SRVY_PK"));
                        } else {
                            ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.xxModeCd, MODE_NEW);
                        }
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.dsCpoConfigPk, (BigDecimal) map.get("DS_CPO_CONFIG_PK"));
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.cmpyInfoAptBldgNm, hdrInfo.cmpyInfoAptBldgNm);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.cmpyInfoFlNm, hdrInfo.cmpyInfoFlNm);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.cmpyInfoDeptNm, hdrInfo.cmpyInfoDeptNm);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.otsdStepNum, hdrInfo.otsdStepNum);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.insdStepNum, hdrInfo.insdStepNum);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.flgtStairNum, hdrInfo.flgtStairNum);
                        // 2018/01/09 S21_NA#18460(Sol#087) MOD START
                        //ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevAvalFromHourMn, hdrInfo.elevAvalFromHourMn);
                        //ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevAvalToHourMn, hdrInfo.elevAvalToHourMn);
                        if (ZYPCommonFunc.hasValue(hdrInfo.elevAvalFromHourMn) && ZYPCommonFunc.hasValue(hdrInfo.elevAvalToHourMn)) {
                            ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevAvalFromHourMn, getConvertTime((String) map.get("SHIP_TO_CTRY_CD"), //
                                    (String) map.get("SHIP_TO_POST_CD"), hdrInfo.elevAvalFromHourMn.getValue()));
                            ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevAvalToHourMn, getConvertTime((String) map.get("SHIP_TO_CTRY_CD"), //
                                    (String) map.get("SHIP_TO_POST_CD"), hdrInfo.elevAvalToHourMn.getValue()));
                        }
                        // 2018/01/09 S21_NA#18460(Sol#087) MOD END
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevCtacTelNum, hdrInfo.elevCtacTelNum);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevWdt, hdrInfo.elevWdt);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevDepthNum, hdrInfo.elevDepthNum);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevCtacPsnNm, hdrInfo.elevCtacPsnNm);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevCapWt, hdrInfo.elevCapWt);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevDoorHgt, hdrInfo.elevDoorHgt);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevDoorWdt, hdrInfo.elevDoorWdt);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.stairAndLdgWdt, hdrInfo.stairAndLdgWdt);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.crdrWdt, hdrInfo.crdrWdt);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.doorWdt, hdrInfo.doorWdt);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.loadDockHgt, hdrInfo.loadDockHgt);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.bldgEntDoorHgt, hdrInfo.bldgEntDoorHgt);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.bldgEntDoorWdt, hdrInfo.bldgEntDoorWdt);
                        // 2018/01/09 S21_NA#18460(Sol#087) MOD START
                        //ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.loadDockAvalFromHourMn, hdrInfo.loadDockAvalFromHourMn);
                        //ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.loadDockAvalToHourMn, hdrInfo.loadDockAvalToHourMn);
                        if (ZYPCommonFunc.hasValue(hdrInfo.loadDockAvalFromHourMn) && ZYPCommonFunc.hasValue(hdrInfo.loadDockAvalToHourMn)) {
                            ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.loadDockAvalFromHourMn, getConvertTime((String) map.get("SHIP_TO_CTRY_CD"), //
                                    (String) map.get("SHIP_TO_POST_CD"), hdrInfo.loadDockAvalFromHourMn.getValue()));
                            ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.loadDockAvalToHourMn, getConvertTime((String) map.get("SHIP_TO_CTRY_CD"), //
                                    (String) map.get("SHIP_TO_POST_CD"), hdrInfo.loadDockAvalToHourMn.getValue()));
                        }
                        //ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.carrDelyTmHourMn, hdrInfo.carrDelyTmHourMn);
                        if (ZYPCommonFunc.hasValue(hdrInfo.carrDelyTmHourMn)) {
                            ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.carrDelyTmHourMn, getConvertTime((String) map.get("SHIP_TO_CTRY_CD"), //
                                    (String) map.get("SHIP_TO_POST_CD"), hdrInfo.carrDelyTmHourMn.getValue()));
                        }
                        // 2018/01/09 S21_NA#18460(Sol#087) MOD END
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.delyTrnspOptCd, hdrInfo.delyTrnspOptCd);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.siteSrvyAddlCmntTxt, hdrInfo.siteSrvyAddlCmntTxt);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.stairCrawReqFlg, hdrInfo.stairCrawReqFlg);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevAvalFlg, hdrInfo.elevAvalFlg);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevApptReqFlg, hdrInfo.elevApptReqFlg);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevProtReqFlg, hdrInfo.elevProtReqFlg);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.loadDockAvalFlg, hdrInfo.loadDockAvalFlg);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.trctrAndTrailAccsFlg, hdrInfo.trctrAndTrailAccsFlg);
                        ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.xxYesNoCd_SS, ZYPConstant.FLG_ON_Y); // 2018/01/09 S21_NA#18460(Sol#087) ADD
                    }
                }
            }
        }
        // S21_NA#26188 20180719 Del Start
//      else {
//      List<Map< ? , ? >> list = (List<Map< ? , ? >>) ssmBatchClient.queryObjectList("querySiteSrvyInfoNoHeader", key);
//      for (Map< ? , ? > map : list) {
//          if (!hasConfigNo(pMsg.siteSrvyInfoList, (BigDecimal) map.get("DS_CPO_CONFIG_PK"))) {
//              cpoSiteSrvyInfo = pMsg.siteSrvyInfoList.no(idx++);
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.xxModeCd, MODE_NEW);
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.dsCpoConfigPk, (BigDecimal) map.get("DS_CPO_CONFIG_PK"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.cmpyInfoAptBldgNm, (String) map.get("CMPY_INFO_APT_BLDG_NM"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.cmpyInfoFlNm, (String) map.get("CMPY_INFO_FL_NM"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.cmpyInfoDeptNm, (String) map.get("CMPY_INFO_DEPT_NM"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.otsdStepNum, (String) map.get("OTSD_STEP_NUM"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.insdStepNum, (String) map.get("INSD_STEP_NUM"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.flgtStairNum, (String) map.get("FLGT_STAIR_NUM"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevAvalFromHourMn, (String) map.get("ELEV_AVAL_FROM_HOUR_MN"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevAvalToHourMn, (String) map.get("ELEV_AVAL_TO_HOUR_MN"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevCtacTelNum, (String) map.get("ELEV_CTAC_TEL_NUM"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevWdt, (BigDecimal) map.get("ELEV_WDT"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevDepthNum, (BigDecimal) map.get("ELEV_DEPTH_NUM"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevCtacPsnNm, (String) map.get("ELEV_CTAC_PSN_NM"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevCapWt, (BigDecimal) map.get("ELEV_CAP_WT"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevDoorHgt, (BigDecimal) map.get("ELEV_DOOR_HGT"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevDoorWdt, (BigDecimal) map.get("ELEV_DOOR_WDT"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.stairAndLdgWdt, (BigDecimal) map.get("STAIR_AND_LDG_WDT"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.crdrWdt, (BigDecimal) map.get("CRDR_WDT"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.doorWdt, (BigDecimal) map.get("DOOR_WDT"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.loadDockHgt, (BigDecimal) map.get("LOAD_DOCK_HGT"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.bldgEntDoorHgt, (BigDecimal) map.get("BLDG_ENT_DOOR_HGT"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.bldgEntDoorWdt, (BigDecimal) map.get("BLDG_ENT_DOOR_WDT"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.loadDockAvalFromHourMn, (String) map.get("LOAD_DOCK_AVAL_FROM_HOUR_MN"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.loadDockAvalToHourMn, (String) map.get("LOAD_DOCK_AVAL_TO_HOUR_MN"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.carrDelyTmHourMn, (String) map.get("CARR_DELY_TM_HOUR_MN"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.delyTrnspOptCd, (String) map.get("DELY_TRNSP_OPT_CD"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.siteSrvyAddlCmntTxt, (String) map.get("SITE_SRVY_ADDL_CMNT_TXT"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.stairCrawReqFlg, (String) map.get("STAIR_CRAW_REQ_FLG"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevAvalFlg, (String) map.get("ELEV_AVAL_FLG"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevApptReqFlg, (String) map.get("ELEV_APPT_REQ_FLG"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.elevProtReqFlg, (String) map.get("ELEV_PROT_REQ_FLG"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.loadDockAvalFlg, (String) map.get("LOAD_DOCK_AVAL_FLG"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.trctrAndTrailAccsFlg, (String) map.get("TRCTR_AND_TRAIL_ACCS_FLG"));
//              ZYPEZDItemValueSetter.setValue(cpoSiteSrvyInfo.xxYesNoCd_SS, ZYPConstant.FLG_ON_Y); // 2018/01/09 S21_NA#18460(Sol#087) ADD
//          }
//      }
//  }
        // S21_NA#26188 20180719 Del End

        pMsg.siteSrvyInfoList.setValidCount(idx);
        // QC#14608 2016/10/06 Add End

        for (int index = 0; index < pMsg.siteSrvyInfoList.getValidCount(); index++) {

            // S21_NA#26188 20180719 Add Start
            //except header
            if (hdrIdxList.contains(index)) {
                continue;
            }
            // S21_NA#26188 20180719 Add End

            cpoSiteSrvyInfo = pMsg.siteSrvyInfoList.no(index);
            // Add Insert
            if (MODE_NEW.equals(cpoSiteSrvyInfo.xxModeCd.getValue())) {
                addInsSiteSrvyTMsg(pMsg, cpoSiteSrvyInfo, insTMsgList);

                // Add Modify
            } else if (MODE_MOD.equals(cpoSiteSrvyInfo.xxModeCd.getValue())) {
                if (!addUpdSiteSrvyTMsg(pMsg, cpoSiteSrvyInfo, updTMsgList)) {
                    msgMap.addXxMsgId(NWZM1352E);
                    return false;
                }

                // Add Delete
            } else {
                if (!addRmvSiteSrvyTMsg(pMsg, cpoSiteSrvyInfo, rmvTMsgList)) {
                    msgMap.addXxMsgId(NWZM1364E);
                    return false;
                }
            }
        }
        // insert
        if (!insTMsgList.isEmpty()) {
            int insCnt = S21FastTBLAccessor.insert(insTMsgList.toArray(new DS_SITE_SRVYTMsg[0]));
            if (insCnt != insTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1362E);
                return false;
            }
        }

        // update
        if (!updTMsgList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updTMsgList.toArray(new DS_SITE_SRVYTMsg[0]));
            if (updCnt != updTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1363E);
                return false;
            }
        }

        // remove
        if (!rmvTMsgList.isEmpty()) {
            int rmvCnt = S21FastTBLAccessor.removeLogical(rmvTMsgList.toArray(new DS_SITE_SRVYTMsg[0]));
            if (rmvCnt != rmvTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1364E);
                return false;
            }
        }
        return true;
    }

    /**
     * registDsCpoCtacPsn
     * @param pMsg NWZC183001PMsg
     * @return boolean
     */
    private boolean registDsCpoCtacPsn(NWZC183001PMsg pMsg) {
        List<DS_CPO_CTAC_PSNTMsg> insTMsgList = new ArrayList<DS_CPO_CTAC_PSNTMsg>();
        List<DS_CPO_CTAC_PSNTMsg> updTMsgList = new ArrayList<DS_CPO_CTAC_PSNTMsg>();
        List<DS_CPO_CTAC_PSNTMsg> rmvTMsgList = new ArrayList<DS_CPO_CTAC_PSNTMsg>();
        DS_CPO_CONFIGTMsg tMsg = null;
        // S21_NA#26188 20180719 Add Start
        List<Integer> hdrIdxList = new ArrayList<Integer>();
        // S21_NA#26188 20180719 Add End

        // Contact Person Information List Check
        NWZC183001_cpoCtacPsnInfoListPMsg ctacPsnInfo = null;
        List<NWZC183001_cpoCtacPsnInfoListPMsg> hdrInfoList = new ArrayList<NWZC183001_cpoCtacPsnInfoListPMsg>();

        // QC#14608 2016/10/06 Add Start
        for (int index = 0; index < pMsg.cpoCtacPsnInfoList.getValidCount(); index++) {
            ctacPsnInfo = pMsg.cpoCtacPsnInfoList.no(index);
            if (!ZYPCommonFunc.hasValue(ctacPsnInfo.dsCpoConfigPk)) {
                hdrInfoList.add(ctacPsnInfo);
                // S21_NA#26188 20180719 Add Start
                hdrIdxList.add(index);
                // S21_NA#26188 20180719 Add End
            }
        }
        Map<String, String> key = new HashMap<String, String>();
        key.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        key.put("ordNum", pMsg.cpoOrdNum.getValue());
        // 2019/01/25 QC#30005 Del Start
        // 2017/06/29 QC#19218 Add Start
        // key.put("dropShipFlg", ZYPConstant.FLG_OFF_N);
        // 2017/06/29 QC#19218 Add End
        // 2019/01/25 QC#30005 Del End
        int idx = pMsg.cpoCtacPsnInfoList.getValidCount();

        if (hdrInfoList.size() > 0) {
            List<Map<String, Object>> deleteList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("queryCtacPsnInfoForDelete", key);
            // Delete
            for (Map< ? , ? > map : deleteList) {
                if (!hasConfigNo(pMsg.cpoCtacPsnInfoList, (BigDecimal) map.get("DS_CPO_CONFIG_PK"))) {
                    ctacPsnInfo = pMsg.cpoCtacPsnInfoList.no(idx++);
                    ZYPEZDItemValueSetter.setValue(ctacPsnInfo.xxModeCd, MODE_DEL);
                    ZYPEZDItemValueSetter.setValue(ctacPsnInfo.dsCpoCtacPsnPk, (BigDecimal) map.get("DS_CPO_CTAC_PSN_PK"));
                }
            }
            // Create All Data
            // 2017/06/29 QC#19218 Mod Start
//            DS_CPO_CONFIGTMsgArray array = getDsCpoConfig(pMsg);
//            if (array != null) {
//                for (int i = 0; i < array.getValidCount(); i++) {
//                    tMsg = array.no(i);
//                    if (!hasConfigNo(pMsg.cpoCtacPsnInfoList, tMsg.dsCpoConfigPk.getValue())) {
//                        for (NWZC183001_cpoCtacPsnInfoListPMsg hdrInfo : hdrInfoList) {
//                            ctacPsnInfo = pMsg.cpoCtacPsnInfoList.no(idx++);
//                            ZYPEZDItemValueSetter.setValue(ctacPsnInfo.xxModeCd, MODE_NEW);
//                            ZYPEZDItemValueSetter.setValue(ctacPsnInfo.dsCpoConfigPk, tMsg.dsCpoConfigPk);
//                            editCtacPsn(ctacPsnInfo, hdrInfo);
//                        }
//                    }
//                }
//            }
            List<Map<String, Object>> insertList = getDsCpoConfig(pMsg);
            for (Map< ? , ? > map : insertList) {
                if (!hasConfigNo(pMsg.cpoCtacPsnInfoList, (BigDecimal) map.get("DS_CPO_CONFIG_PK"))) {
                    for (NWZC183001_cpoCtacPsnInfoListPMsg hdrInfo : hdrInfoList) {
                        ctacPsnInfo = pMsg.cpoCtacPsnInfoList.no(idx++);
                        ZYPEZDItemValueSetter.setValue(ctacPsnInfo.xxModeCd, MODE_NEW);
                        ZYPEZDItemValueSetter.setValue(ctacPsnInfo.dsCpoConfigPk, (BigDecimal) map.get("DS_CPO_CONFIG_PK"));
                        editCtacPsn(ctacPsnInfo, hdrInfo);
                    }
                }
            }
            // 2017/06/29 QC#19218 Mod End
        }
        // S21_NA#26188 20180719 Del Start
//      else {
//      List<Map<String, Object>> list = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("queryCtacPsnInfoNoHeader", key);
//      for (Map<String, Object> map : list) {
//          if (!hasConfigNo(pMsg.cpoCtacPsnInfoList, (BigDecimal) map.get("DS_CPO_CONFIG_PK"))) {
//              ctacPsnInfo = pMsg.cpoCtacPsnInfoList.no(idx++);
//              ZYPEZDItemValueSetter.setValue(ctacPsnInfo.xxModeCd, MODE_NEW);
//              ZYPEZDItemValueSetter.setValue(ctacPsnInfo.dsCpoConfigPk, (BigDecimal) map.get("DS_CPO_CONFIG_PK"));
//              editCtacPsn(ctacPsnInfo, map);
//          }
//      }
//  }
        // S21_NA#26188 20180719 Del End

        pMsg.cpoCtacPsnInfoList.setValidCount(idx);
        // QC#14608 2016/10/06 Add End

        for (int index = 0; index < pMsg.cpoCtacPsnInfoList.getValidCount(); index++) {

            // S21_NA#26188 20180719 Add Start
            //except header
            if (hdrIdxList.contains(index)) {
                continue;
            }
            // S21_NA#26188 20180719 Add End

            ctacPsnInfo = pMsg.cpoCtacPsnInfoList.no(index);

            // Master Check : Contact Type Code
            if (ZYPCommonFunc.hasValue(ctacPsnInfo.ctacPsnTpCd)) {
                CTAC_TPTMsg ctacTMsg = new CTAC_TPTMsg();
                ZYPEZDItemValueSetter.setValue(ctacTMsg.ctacTpCd, ctacPsnInfo.ctacPsnTpCd);
                ZYPEZDItemValueSetter.setValue(ctacTMsg.glblCmpyCd, pMsg.glblCmpyCd);

                CTAC_TPTMsg tMsgResult = (CTAC_TPTMsg) S21CacheTBLAccessor.findByKey(ctacTMsg);
                // has no result
                if (tMsgResult == null) {
                    msgMap.addXxMsgId(NWZM1373E);
                    return false;
                }
            }

            // Add Insert
            if (MODE_NEW.equals(ctacPsnInfo.xxModeCd.getValue())) {
                addInsCtacPsnTMsg(pMsg, ctacPsnInfo, insTMsgList);

                // Add Modify
            } else if (MODE_MOD.equals(ctacPsnInfo.xxModeCd.getValue())) {
                if (!addUpdCtacPsnTMsg(pMsg, ctacPsnInfo, updTMsgList)) {
                    msgMap.addXxMsgId(NWZM1353E);
                    return false;
                }

                // Add Delete
            } else {
                if (!addRmvCtacPsnTMsg(pMsg, ctacPsnInfo, rmvTMsgList)) {
                    msgMap.addXxMsgId(NWZM1367E);
                    return false;
                }
            }
        }

        // insert
        if (!insTMsgList.isEmpty()) {
            int insCnt = S21FastTBLAccessor.insert(insTMsgList.toArray(new DS_CPO_CTAC_PSNTMsg[0]));
            if (insCnt != insTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1365E);
                return false;
            }
        }

        // update
        if (!updTMsgList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updTMsgList.toArray(new DS_CPO_CTAC_PSNTMsg[0]));
            if (updCnt != updTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1366E);
                return false;
            }
        }

        // remove
        if (!rmvTMsgList.isEmpty()) {
            int rmvCnt = S21FastTBLAccessor.removeLogical(rmvTMsgList.toArray(new DS_CPO_CTAC_PSNTMsg[0]));
            if (rmvCnt != rmvTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1367E);
                return false;
            }
        }
        return true;
    }
    /**
     * editCtacPsn
     * @param editInfo NWZC183001_cpoCtacPsnInfoListPMsg
     * @param hdrInfo NWZC183001_cpoCtacPsnInfoListPMsg
     */
    private void editCtacPsn(NWZC183001_cpoCtacPsnInfoListPMsg editInfo, NWZC183001_cpoCtacPsnInfoListPMsg hdrInfo) {
        ZYPEZDItemValueSetter.setValue(editInfo.ctacPsnPk, hdrInfo.ctacPsnPk);
        ZYPEZDItemValueSetter.setValue(editInfo.ctacPsnTpCd, hdrInfo.ctacPsnTpCd);
        ZYPEZDItemValueSetter.setValue(editInfo.ctacPsnFirstNm, hdrInfo.ctacPsnFirstNm);
        ZYPEZDItemValueSetter.setValue(editInfo.ctacPsnLastNm, hdrInfo.ctacPsnLastNm);
        ZYPEZDItemValueSetter.setValue(editInfo.ctacPsnEmlAddr, hdrInfo.ctacPsnEmlAddr);
        ZYPEZDItemValueSetter.setValue(editInfo.ctacPsnTelNum, hdrInfo.ctacPsnTelNum);
        ZYPEZDItemValueSetter.setValue(editInfo.ctacPsnFaxNum, hdrInfo.ctacPsnFaxNum);
        ZYPEZDItemValueSetter.setValue(editInfo.ctacPsnSortNum, hdrInfo.ctacPsnSortNum);
        ZYPEZDItemValueSetter.setValue(editInfo.ctacPsnExtnNum, hdrInfo.ctacPsnExtnNum);
        ZYPEZDItemValueSetter.setValue(editInfo.ctacCustRefTpCd, hdrInfo.ctacCustRefTpCd);  //S21_NA#16452 Add
    }
    /**
     * editCtacPsn
     * @param editInfo NWZC183001_cpoCtacPsnInfoListPMsg
     * @param map Map<String, Object>
     */
    private void editCtacPsn(NWZC183001_cpoCtacPsnInfoListPMsg editInfo, Map<String, Object> map) {
        ZYPEZDItemValueSetter.setValue(editInfo.ctacPsnPk, (BigDecimal) map.get("CTAC_PSN_PK"));
        ZYPEZDItemValueSetter.setValue(editInfo.ctacPsnTpCd, (String) map.get("CTAC_PSN_TP_CD"));
        ZYPEZDItemValueSetter.setValue(editInfo.ctacPsnFirstNm, (String) map.get("CTAC_PSN_FIRST_NM"));
        ZYPEZDItemValueSetter.setValue(editInfo.ctacPsnLastNm, (String) map.get("CTAC_PSN_LAST_NM"));
        ZYPEZDItemValueSetter.setValue(editInfo.ctacPsnEmlAddr, (String) map.get("CTAC_PSN_EML_ADDR"));
        ZYPEZDItemValueSetter.setValue(editInfo.ctacPsnTelNum, (String) map.get("CTAC_PSN_TEL_NUM"));
        ZYPEZDItemValueSetter.setValue(editInfo.ctacPsnFaxNum, (String) map.get("CTAC_PSN_FAX_NUM"));
        ZYPEZDItemValueSetter.setValue(editInfo.ctacPsnSortNum, (BigDecimal) map.get("CTAC_PSN_SORT_NUM"));
        ZYPEZDItemValueSetter.setValue(editInfo.ctacPsnExtnNum, (String) map.get("CTAC_PSN_EXTN_NUM"));
        ZYPEZDItemValueSetter.setValue(editInfo.ctacCustRefTpCd, (String) map.get("CTAC_CUST_REF_TP_CD"));  //S21_NA#16452 Add
    }

    /**
     * isError
     * @param pMsg NWZC18300101PMsg
     * @return boolean
     */
    private boolean isError(NWZC183001PMsg pMsg) {
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * addInsDelyTMsg
     * @param insTMsg DS_CPO_DELY_INFOTMsg
     * @param pMsg NWZC183001PMsg
     * @param insTMsgList List<DS_CPO_DELY_INFOTMsg>
     * @param clobMap Map<BigDecimal, NWZC183001_cpoDelyInfoListPMsg>
     */
    // 2019/12/26 QC#54725 Mod Start
    //private void addInsDelyTMsg(NWZC183001PMsg pMsg, NWZC183001_cpoDelyInfoListPMsg cpoDelyInfo, List<DS_CPO_DELY_INFOTMsg> insTMsgList) {
    private void addInsDelyTMsg(NWZC183001PMsg pMsg, NWZC183001_cpoDelyInfoListPMsg cpoDelyInfo, List<DS_CPO_DELY_INFOTMsg> insTMsgList, Map<BigDecimal, NWZC183001_cpoDelyInfoListPMsg> clobMap) {
    // 2019/12/26 QC#54725 Mod End
        DS_CPO_DELY_INFOTMsg insTMsg = new DS_CPO_DELY_INFOTMsg();

        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoDelyInfo.dsCpoDelyInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_DELY_INFO_SQ));
        ZYPEZDItemValueSetter.setValue(insTMsg.dsCpoDelyInfoPk, cpoDelyInfo.dsCpoDelyInfoPk);
        ZYPEZDItemValueSetter.setValue(insTMsg.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.dsCpoConfigPk, cpoDelyInfo.dsCpoConfigPk);
        ZYPEZDItemValueSetter.setValue(insTMsg.dsDelyTpCd, cpoDelyInfo.dsDelyTpCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.rddDt, cpoDelyInfo.rddDt);
        // 2018/01/09 S21_NA#18460(Sol#087) MOD START
        //ZYPEZDItemValueSetter.setValue(insTMsg.opsFromHourMn, cpoDelyInfo.opsFromHourMn);
        //ZYPEZDItemValueSetter.setValue(insTMsg.opsToHourMn, cpoDelyInfo.opsToHourMn);
        setTimeForDely(pMsg, cpoDelyInfo, insTMsg);
        // 2018/01/09 S21_NA#18460(Sol#087) MOD END
        ZYPEZDItemValueSetter.setValue(insTMsg.stairCrawNum, cpoDelyInfo.stairCrawNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.delyAddlCmntTxt, cpoDelyInfo.delyAddlCmntTxt);
        // 2019/12/26 QC#54725 Add Start
        ZYPEZDItemValueSetter.setValue(insTMsg.delySchdStsCd, cpoDelyInfo.delySchdStsCd);
        // 2019/12/26 QC#54725 Add Start
        if (ZYPCommonFunc.hasValue(cpoDelyInfo.loadDockAvalFlg)) {
            ZYPEZDItemValueSetter.setValue(insTMsg.loadDockAvalFlg, cpoDelyInfo.loadDockAvalFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(insTMsg.loadDockAvalFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cpoDelyInfo.stairCrawReqFlg)) {
            ZYPEZDItemValueSetter.setValue(insTMsg.stairCrawReqFlg, cpoDelyInfo.stairCrawReqFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(insTMsg.stairCrawReqFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cpoDelyInfo.elevAvalFlg)) {
            ZYPEZDItemValueSetter.setValue(insTMsg.elevAvalFlg, cpoDelyInfo.elevAvalFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(insTMsg.elevAvalFlg, ZYPConstant.FLG_OFF_N);
        }

        insTMsgList.add(insTMsg);

        // 2019/12/26 QC#54725 Add Start
        if (ZYPCommonFunc.hasValue(cpoDelyInfo.xxAttDataCmntTxt)) {
            clobMap.put(cpoDelyInfo.dsCpoDelyInfoPk.getValue(), cpoDelyInfo);
        }
        // 2019/12/26 QC#54725 Add Start
    }

    /**
     * getUpdDsDelyInfoTMsg
     * @param dsCpoDelyInfoPk BigDecimal
     * @param glblCmpyCd String
     * @return DS_CPO_DELY_INFOTMsg
     */
    private DS_CPO_DELY_INFOTMsg getUpdDsDelyInfoTMsg(String glblCmpyCd, BigDecimal dsCpoDelyInfoPk) {

        DS_CPO_DELY_INFOTMsg updTMsgKey = new DS_CPO_DELY_INFOTMsg();

        ZYPEZDItemValueSetter.setValue(updTMsgKey.dsCpoDelyInfoPk, dsCpoDelyInfoPk);
        ZYPEZDItemValueSetter.setValue(updTMsgKey.glblCmpyCd, glblCmpyCd);

        DS_CPO_DELY_INFOTMsg updTMsg = (DS_CPO_DELY_INFOTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(updTMsgKey);
        if (updTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            return null;
        }

        return updTMsg;
    }

    /**
     * addUpdDelyTMsg
     * @param pMsg NWZC183001PMsg
     * @param cpoDelyInfo NWZC183001_cpoDelyInfoListPMsg
     * @param updTMsgList List<DS_CPO_DELY_INFOTMsg>
     * @param updTMsg DS_CPO_DELY_INFOTMsg
     * @param updTMsg DS_CPO_DELY_INFOTMsg
     * @param clobMap Map<BigDecimal, NWZC183001_cpoDelyInfoListPMsg>
     * @return List<DS_CPO_DELY_INFOTMsg>
     */
    //private List<DS_CPO_DELY_INFOTMsg> addUpdDelyTMsg(NWZC183001PMsg pMsg, NWZC183001_cpoDelyInfoListPMsg cpoDelyInfo, List<DS_CPO_DELY_INFOTMsg> updTMsgList, DS_CPO_DELY_INFOTMsg updTMsg) {
    private List<DS_CPO_DELY_INFOTMsg> addUpdDelyTMsg(NWZC183001PMsg pMsg, NWZC183001_cpoDelyInfoListPMsg cpoDelyInfo, List<DS_CPO_DELY_INFOTMsg> updTMsgList, DS_CPO_DELY_INFOTMsg updTMsg, Map<BigDecimal, NWZC183001_cpoDelyInfoListPMsg> clobMap) {
        // add update TMsg
        ZYPEZDItemValueSetter.setValue(updTMsg.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.dsCpoConfigPk, cpoDelyInfo.dsCpoConfigPk);
        ZYPEZDItemValueSetter.setValue(updTMsg.dsDelyTpCd, cpoDelyInfo.dsDelyTpCd);
        ZYPEZDItemValueSetter.setValue(updTMsg.rddDt, cpoDelyInfo.rddDt);
        // 2018/01/09 S21_NA#18460(Sol#087) MOD START
        //ZYPEZDItemValueSetter.setValue(updTMsg.opsFromHourMn, cpoDelyInfo.opsFromHourMn);
        //ZYPEZDItemValueSetter.setValue(updTMsg.opsToHourMn, cpoDelyInfo.opsToHourMn);
        setTimeForDely(pMsg, cpoDelyInfo, updTMsg);
        // 2018/01/09 S21_NA#18460(Sol#087) MOD END
        ZYPEZDItemValueSetter.setValue(updTMsg.stairCrawNum, cpoDelyInfo.stairCrawNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.delyAddlCmntTxt, cpoDelyInfo.delyAddlCmntTxt);
        // 2019/12/26 QC#54725 Add Start
        ZYPEZDItemValueSetter.setValue(updTMsg.delySchdStsCd, cpoDelyInfo.delySchdStsCd);
        // 2019/12/26 QC#54725 Add Start
        if (ZYPCommonFunc.hasValue(cpoDelyInfo.elevAvalFlg)) {
            ZYPEZDItemValueSetter.setValue(updTMsg.elevAvalFlg, cpoDelyInfo.elevAvalFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(updTMsg.elevAvalFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cpoDelyInfo.stairCrawReqFlg)) {
            ZYPEZDItemValueSetter.setValue(updTMsg.stairCrawReqFlg, cpoDelyInfo.stairCrawReqFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(updTMsg.stairCrawReqFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cpoDelyInfo.loadDockAvalFlg)) {
            ZYPEZDItemValueSetter.setValue(updTMsg.loadDockAvalFlg, cpoDelyInfo.loadDockAvalFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(updTMsg.loadDockAvalFlg, ZYPConstant.FLG_OFF_N);
        }
        updTMsgList.add(updTMsg);

        // 2019/12/26 QC#54725 Add Start
        if (ZYPCommonFunc.hasValue(cpoDelyInfo.xxAttDataCmntTxt)
                || !ZYPCommonFunc.hasValue(cpoDelyInfo.xxAttDataCmntTxt)
                && ZYPCommonFunc.hasValue(new SchedulingCommentsClobAccessor(updTMsg).getSql())) {
            clobMap.put(cpoDelyInfo.dsCpoDelyInfoPk.getValue(), cpoDelyInfo);
        }
        // 2019/12/26 QC#54725 Add Start

        return updTMsgList;
    }

    /**
     * updCpoTMsg
     * @param pMsg NWZC183001PMsg
     * @param cpoDelyInfo NWZC183001_cpoDelyInfoListPMsg
     * @param cpoUpdTMsgList List<CPOTMsg>
     * @return boolean
     */
    private  List<CPOTMsg> updCpoTMsg(NWZC183001PMsg pMsg, NWZC183001_cpoDelyInfoListPMsg cpoDelyInfo, List<CPOTMsg> cpoUpdTMsgList) {
        CPOTMsg cpoTMsgKey = new CPOTMsg();

        ZYPEZDItemValueSetter.setValue(cpoTMsgKey.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(cpoTMsgKey.glblCmpyCd, pMsg.glblCmpyCd);

        CPOTMsg updTMsg = (CPOTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(cpoTMsgKey);
        if (updTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            return null;
        }

        // add update TMsg
        ZYPEZDItemValueSetter.setValue(updTMsg.addRddDt, cpoDelyInfo.rddDt);

        cpoUpdTMsgList.add(updTMsg);
        return cpoUpdTMsgList;
    }

    /**
     * updCpoDtlTMsg
     * @param pMsg NWZC183001PMsg
     * @param cpoDelyInfo NWZC183001_cpoDelyInfoListPMsg
     * @param orderDtl OrderDtlInfoBean
     * @param cpoDtlUpdTMsgList List<CPO_DTLTMsg>
     * @return boolean
     */
    private List<CPO_DTLTMsg> updCpoDtlTMsg(NWZC183001PMsg pMsg, NWZC183001_cpoDelyInfoListPMsg cpoDelyInfo, OrderDtlInfoBean orderDtl, List<CPO_DTLTMsg> cpoDtlUpdTMsgList) {
        CPO_DTLTMsg cpoDtlTMsgKey = new CPO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsgKey.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsgKey.cpoOrdNum, orderDtl.getOrdNum());
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsgKey.cpoDtlLineNum, orderDtl.getOrdLineNum());
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsgKey.cpoDtlLineSubNum, orderDtl.getOrdLineSubNum());

        CPO_DTLTMsg updCpoDtlTMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(cpoDtlTMsgKey);

        if (updCpoDtlTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(updCpoDtlTMsg.getReturnCode())) {
            return null;
        }

        ZYPEZDItemValueSetter.setValue(updCpoDtlTMsg.rddDt, cpoDelyInfo.rddDt);
        cpoDtlUpdTMsgList.add(updCpoDtlTMsg);
        return cpoDtlUpdTMsgList;
    }

    /**
     * updShpgPlnTMsg
     * @param pMsg NWZC183001PMsg
     * @param cpoDelyInfo NWZC183001_cpoDelyInfoListPMsg
     * @param orderDtl OrderDtlInfoBean
     * @param shpgPlnTMsgList List<SHPG_PLNTMsg>
     * @return List<SHPG_PLNTMsg> 
     */
    private List<SHPG_PLNTMsg> updShpgPlnTMsg(NWZC183001PMsg pMsg, NWZC183001_cpoDelyInfoListPMsg cpoDelyInfo, OrderDtlInfoBean orderDtl, List<SHPG_PLNTMsg> shpgPlnTMsgList) {
        SHPG_PLNTMsg shpgPlnTMsgKey = new SHPG_PLNTMsg();

        shpgPlnTMsgKey.setSQLID("006");
        shpgPlnTMsgKey.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        shpgPlnTMsgKey.setConditionValue("trxHdrNum01", orderDtl.getOrdNum());
        shpgPlnTMsgKey.setConditionValue("trxLineNum01", orderDtl.getOrdLineNum());
        shpgPlnTMsgKey.setConditionValue("trxLineSubNum01", orderDtl.getOrdLineSubNum());
        shpgPlnTMsgKey.setConditionValue("shpgStsCd01", SHPG_STS.VALIDATED);
        SHPG_PLNTMsgArray shpgPlnTMsgResultAry = (SHPG_PLNTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(shpgPlnTMsgKey);

        if (shpgPlnTMsgResultAry.length() == 0) {
            return shpgPlnTMsgList;
        }

        for (int i = 0; i < shpgPlnTMsgResultAry.length(); i++) {
            ZYPEZDItemValueSetter.setValue(shpgPlnTMsgResultAry.no(i).rddDt, cpoDelyInfo.rddDt);
            shpgPlnTMsgList.add(shpgPlnTMsgResultAry.no(i));
        }

        return shpgPlnTMsgList;
    }

    /**
     * getCpoDtlInfo
     * @param pMsg NWZC183001PMsg
     * @param ordDtlBeanList List<OrderDtlInfoBean>
     * @return List<OrderDtlInfoBean>
     */
    private List<OrderDtlInfoBean> getCpoDtlInfo(final NWZC183001PMsg pMsg, List<OrderDtlInfoBean> ordDtlBeanList) {
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        key.put("ordNum", pMsg.cpoOrdNum.getValue());
        key.put("close", ORD_LINE_STS.CLOSED);
        key.put("cancel", ORD_LINE_STS.CANCELLED);

        List<Map> cpoDtlList = (List<Map>) ssmBatchClient.queryObjectList("queryCpoDtl", key);

        if (hasList(cpoDtlList)) {
            ordDtlBeanList = setOrderDtlList(cpoDtlList, ordDtlBeanList);
        }
        return ordDtlBeanList;
    }

    /**
     * getDsCpoDtlInfo
     * @param pMsg NWZC183001PMsg
     * @param cpoDelyInfo NWZC183001_cpoDelyInfoListPMsg
     * @return List<OrderDtlInfoBean>
     */
    private List<OrderDtlInfoBean> getDsCpoDtlInfo(final NWZC183001PMsg pMsg, NWZC183001_cpoDelyInfoListPMsg cpoDelyInfo, List<OrderDtlInfoBean> ordDtlBeanList) {
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        key.put("ordNum", pMsg.cpoOrdNum.getValue());
        key.put("configPk", cpoDelyInfo.dsCpoConfigPk.getValue());
        key.put("close", ORD_LINE_STS.CLOSED);
        key.put("cancel", ORD_LINE_STS.CANCELLED);

        List<Map> cpoDtlList = (List<Map>) ssmBatchClient.queryObjectList("queryDsCpoDtl", key);

        if (hasList(cpoDtlList)) {
            ordDtlBeanList = setOrderDtlList(cpoDtlList, ordDtlBeanList);
        }
        return ordDtlBeanList;

    }

    /**
     * setOrderDtlList
     * @param cpoDtlList List<Map>
     * @param ordDtlBeanList List<OrderDtlInfoBean>
     * @return List<OrderDtlInfoBean>
     */
    @SuppressWarnings("unchecked")
    private List<OrderDtlInfoBean> setOrderDtlList(List<Map> cpoDtlList, List<OrderDtlInfoBean> ordDtlBeanList) {
        for (Map cpoDtlMap : cpoDtlList) {
            if (!cpoDtlMap.isEmpty()) {
                OrderDtlInfoBean ordDtlBean = new OrderDtlInfoBean();
                ordDtlBean.setOrdNum((String) cpoDtlMap.get("CPO_ORD_NUM"));
                ordDtlBean.setOrdLineNum((String) cpoDtlMap.get("CPO_DTL_LINE_NUM"));
                ordDtlBean.setOrdLineSubNum((String) cpoDtlMap.get("CPO_DTL_LINE_SUB_NUM"));

                ordDtlBeanList.add(ordDtlBean);
            }
        }
        return ordDtlBeanList;
    }

    /**
     * addRmvDelyTMsg
     * @param pMsg NWZC183001PMsg
     * @param cpoDelyInfo NWZC183001_cpoDelyInfoListPMsg
     * @param rmvTMsgList List<DS_CPO_DELY_INFOTMsg>
     * @return boolean
     */
    private boolean addRmvDelyTMsg(NWZC183001PMsg pMsg, NWZC183001_cpoDelyInfoListPMsg cpoDelyInfo, List<DS_CPO_DELY_INFOTMsg> rmvTMsgList) {
        DS_CPO_DELY_INFOTMsg rmvTMsgKey = new DS_CPO_DELY_INFOTMsg();

        ZYPEZDItemValueSetter.setValue(rmvTMsgKey.dsCpoDelyInfoPk, cpoDelyInfo.dsCpoDelyInfoPk);
        ZYPEZDItemValueSetter.setValue(rmvTMsgKey.glblCmpyCd, pMsg.glblCmpyCd);

        DS_CPO_DELY_INFOTMsg rmvTMsg = (DS_CPO_DELY_INFOTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(rmvTMsgKey);
        if (rmvTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(rmvTMsg.getReturnCode())) {
            return false;
        }

        rmvTMsgList.add(rmvTMsg);
        return true;
    }

    /**
     * addInsInstlTMsg
     * @param pMsg NWZC183001PMsg
     * @param cpoIstlInfo NWZC183001_cpoInstInfoListPMsg
     * @param insTMsgList List<DS_CPO_ISTL_INFOTMsg>
     */
    private void addInsInstlTMsg(NWZC183001PMsg pMsg, NWZC183001_cpoInstInfoListPMsg cpoIstlInfo, List<DS_CPO_ISTL_INFOTMsg> insTMsgList) {
        DS_CPO_ISTL_INFOTMsg insTMsg = new DS_CPO_ISTL_INFOTMsg();

        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoIstlInfo.dsCpoIstlInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_ISTL_INFO_SQ));
        ZYPEZDItemValueSetter.setValue(insTMsg.dsCpoIstlInfoPk, cpoIstlInfo.dsCpoIstlInfoPk);
        ZYPEZDItemValueSetter.setValue(insTMsg.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.dsCpoConfigPk, cpoIstlInfo.dsCpoConfigPk);
        ZYPEZDItemValueSetter.setValue(insTMsg.svcIstlRuleNum, cpoIstlInfo.svcIstlRuleNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.istlDivCd, cpoIstlInfo.istlDivCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.istlTechCd, cpoIstlInfo.istlTechCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.rqstIstlDt, cpoIstlInfo.rqstIstlDt);
        // 2018/01/09 S21_NA#18460(Sol#087) MOD START
        //ZYPEZDItemValueSetter.setValue(insTMsg.rqstIstlTm, cpoIstlInfo.rqstIstlTm);
        setTimeForIstl(pMsg, cpoIstlInfo, insTMsg);
        // 2018/01/09 S21_NA#18460(Sol#087) MOD END
        ZYPEZDItemValueSetter.setValue(insTMsg.istlAddlCmntTxt, cpoIstlInfo.istlAddlCmntTxt);

        // 2019/10/30 QC#53993 Add Start
        ZYPEZDItemValueSetter.setValue(insTMsg.istlBySvcPrvdPtyCd, cpoIstlInfo.istlBySvcPrvdPtyCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.svcBySvcPrvdPtyCd, cpoIstlInfo.svcBySvcPrvdPtyCd);
        // 2019/10/30 QC#53993 Add End

        insTMsgList.add(insTMsg);
    }

    /**
     * addUpdInstlTMsg
     * @param pMsg NWZC183001PMsg
     * @param cpoIstlInfo NWZC183001_cpoInstInfoListPMsg
     * @param updTMsgList List<DS_CPO_ISTL_INFOTMsg>
     */
    private boolean addUpdInstlTMsg(NWZC183001PMsg pMsg, NWZC183001_cpoInstInfoListPMsg cpoIstlInfo, List<DS_CPO_ISTL_INFOTMsg> updTMsgList) {
        DS_CPO_ISTL_INFOTMsg updTMsgKey = new DS_CPO_ISTL_INFOTMsg();

        ZYPEZDItemValueSetter.setValue(updTMsgKey.dsCpoIstlInfoPk, cpoIstlInfo.dsCpoIstlInfoPk);
        ZYPEZDItemValueSetter.setValue(updTMsgKey.glblCmpyCd, pMsg.glblCmpyCd);

        DS_CPO_ISTL_INFOTMsg updTMsg = (DS_CPO_ISTL_INFOTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(updTMsgKey);
        if (updTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            return false;
        }

        // add update TMsg
        ZYPEZDItemValueSetter.setValue(updTMsg.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.dsCpoConfigPk, cpoIstlInfo.dsCpoConfigPk);
        ZYPEZDItemValueSetter.setValue(updTMsg.svcIstlRuleNum, cpoIstlInfo.svcIstlRuleNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.istlDivCd, cpoIstlInfo.istlDivCd);
        ZYPEZDItemValueSetter.setValue(updTMsg.istlTechCd, cpoIstlInfo.istlTechCd);
        ZYPEZDItemValueSetter.setValue(updTMsg.rqstIstlDt, cpoIstlInfo.rqstIstlDt);
        // 2018/01/09 S21_NA#18460(Sol#087) MOD START
        //ZYPEZDItemValueSetter.setValue(updTMsg.rqstIstlTm, cpoIstlInfo.rqstIstlTm);
        setTimeForIstl(pMsg, cpoIstlInfo, updTMsg);
        // 2018/01/09 S21_NA#18460(Sol#087) MOD END
        ZYPEZDItemValueSetter.setValue(updTMsg.istlAddlCmntTxt, cpoIstlInfo.istlAddlCmntTxt);

        // 2019/10/30 QC#53993 Add Start
        ZYPEZDItemValueSetter.setValue(updTMsg.istlBySvcPrvdPtyCd, cpoIstlInfo.istlBySvcPrvdPtyCd);
        ZYPEZDItemValueSetter.setValue(updTMsg.svcBySvcPrvdPtyCd, cpoIstlInfo.svcBySvcPrvdPtyCd);
        // 2019/10/30 QC#53993 Add End

        updTMsgList.add(updTMsg);
        return true;
    }

    /**
     * addRmvInstlTMsg
     * @param pMsg NWZC183001PMsg
     * @param cpoIstlInfo NWZC183001_cpoInstInfoListPMsg
     * @param rmvTMsgList List<DS_CPO_ISTL_INFOTMsg>
     */
    private boolean addRmvInstlTMsg(NWZC183001PMsg pMsg, NWZC183001_cpoInstInfoListPMsg cpoIstlInfo, List<DS_CPO_ISTL_INFOTMsg> rmvTMsgList) {
        DS_CPO_ISTL_INFOTMsg rmvTMsgKey = new DS_CPO_ISTL_INFOTMsg();

        ZYPEZDItemValueSetter.setValue(rmvTMsgKey.dsCpoIstlInfoPk, cpoIstlInfo.dsCpoIstlInfoPk);
        ZYPEZDItemValueSetter.setValue(rmvTMsgKey.glblCmpyCd, pMsg.glblCmpyCd);

        DS_CPO_ISTL_INFOTMsg rmvTMsg = (DS_CPO_ISTL_INFOTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(rmvTMsgKey);
        if (rmvTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(rmvTMsg.getReturnCode())) {
            return false;
        }

        rmvTMsgList.add(rmvTMsg);
        return true;
    }

    /**
     * addInsSiteSrvyTMsg
     * @param insTMsg DS_SITE_SRVYTMsg
     * @param pMsg NWZC183001PMsg
     * @param insTMsgList List<DS_SITE_SRVYTMsg>
     */
    private void addInsSiteSrvyTMsg(NWZC183001PMsg pMsg, NWZC183001_siteSrvyInfoListPMsg siteSrvyInfo, List<DS_SITE_SRVYTMsg> insTMsgList) {
        DS_SITE_SRVYTMsg insTMsg = new DS_SITE_SRVYTMsg();

        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(siteSrvyInfo.dsSiteSrvyPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_SITE_SRVY_SQ));
        ZYPEZDItemValueSetter.setValue(insTMsg.dsSiteSrvyPk, siteSrvyInfo.dsSiteSrvyPk);
        ZYPEZDItemValueSetter.setValue(insTMsg.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.dsCpoConfigPk, siteSrvyInfo.dsCpoConfigPk);
        ZYPEZDItemValueSetter.setValue(insTMsg.cmpyInfoAptBldgNm, siteSrvyInfo.cmpyInfoAptBldgNm);
        ZYPEZDItemValueSetter.setValue(insTMsg.cmpyInfoFlNm, siteSrvyInfo.cmpyInfoFlNm);
        ZYPEZDItemValueSetter.setValue(insTMsg.cmpyInfoDeptNm, siteSrvyInfo.cmpyInfoDeptNm);
        ZYPEZDItemValueSetter.setValue(insTMsg.otsdStepNum, siteSrvyInfo.otsdStepNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.insdStepNum, siteSrvyInfo.insdStepNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.flgtStairNum, siteSrvyInfo.flgtStairNum);
        // 2018/01/09 S21_NA#18460(Sol#087) MOD START
        //ZYPEZDItemValueSetter.setValue(insTMsg.elevAvalFromHourMn, siteSrvyInfo.elevAvalFromHourMn);
        //ZYPEZDItemValueSetter.setValue(insTMsg.elevAvalToHourMn, siteSrvyInfo.elevAvalToHourMn);
        ZYPEZDItemValueSetter.setValue(insTMsg.elevCtacTelNum, siteSrvyInfo.elevCtacTelNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.elevWdt, siteSrvyInfo.elevWdt);
        ZYPEZDItemValueSetter.setValue(insTMsg.elevDepthNum, siteSrvyInfo.elevDepthNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.elevCtacPsnNm, siteSrvyInfo.elevCtacPsnNm);
        ZYPEZDItemValueSetter.setValue(insTMsg.elevCapWt, siteSrvyInfo.elevCapWt);
        ZYPEZDItemValueSetter.setValue(insTMsg.elevDoorHgt, siteSrvyInfo.elevDoorHgt);
        ZYPEZDItemValueSetter.setValue(insTMsg.elevDoorWdt, siteSrvyInfo.elevDoorWdt);
        ZYPEZDItemValueSetter.setValue(insTMsg.stairAndLdgWdt, siteSrvyInfo.stairAndLdgWdt);
        ZYPEZDItemValueSetter.setValue(insTMsg.crdrWdt, siteSrvyInfo.crdrWdt);
        ZYPEZDItemValueSetter.setValue(insTMsg.doorWdt, siteSrvyInfo.doorWdt);
        ZYPEZDItemValueSetter.setValue(insTMsg.loadDockHgt, siteSrvyInfo.loadDockHgt);
        ZYPEZDItemValueSetter.setValue(insTMsg.bldgEntDoorHgt, siteSrvyInfo.bldgEntDoorHgt);
        ZYPEZDItemValueSetter.setValue(insTMsg.bldgEntDoorWdt, siteSrvyInfo.bldgEntDoorWdt);
        //ZYPEZDItemValueSetter.setValue(insTMsg.loadDockAvalFromHourMn, siteSrvyInfo.loadDockAvalFromHourMn);
        //ZYPEZDItemValueSetter.setValue(insTMsg.loadDockAvalToHourMn, siteSrvyInfo.loadDockAvalToHourMn);
        //ZYPEZDItemValueSetter.setValue(insTMsg.carrDelyTmHourMn, siteSrvyInfo.carrDelyTmHourMn);
        setTimeForSite(pMsg, siteSrvyInfo, insTMsg);
        // 2018/01/09 S21_NA#18460(Sol#087) MOD END
        ZYPEZDItemValueSetter.setValue(insTMsg.delyTrnspOptCd, siteSrvyInfo.delyTrnspOptCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.siteSrvyAddlCmntTxt, siteSrvyInfo.siteSrvyAddlCmntTxt);
        if (ZYPCommonFunc.hasValue(siteSrvyInfo.stairCrawReqFlg)) {
            ZYPEZDItemValueSetter.setValue(insTMsg.stairCrawReqFlg, siteSrvyInfo.stairCrawReqFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(insTMsg.stairCrawReqFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(siteSrvyInfo.elevAvalFlg)) {
            ZYPEZDItemValueSetter.setValue(insTMsg.elevAvalFlg, siteSrvyInfo.elevAvalFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(insTMsg.elevAvalFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(siteSrvyInfo.elevApptReqFlg)) {
            ZYPEZDItemValueSetter.setValue(insTMsg.elevApptReqFlg, siteSrvyInfo.elevApptReqFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(insTMsg.elevApptReqFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(siteSrvyInfo.elevProtReqFlg)) {
            ZYPEZDItemValueSetter.setValue(insTMsg.elevProtReqFlg, siteSrvyInfo.elevProtReqFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(insTMsg.elevProtReqFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(siteSrvyInfo.loadDockAvalFlg)) {
            ZYPEZDItemValueSetter.setValue(insTMsg.loadDockAvalFlg, siteSrvyInfo.loadDockAvalFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(insTMsg.loadDockAvalFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(siteSrvyInfo.trctrAndTrailAccsFlg)) {
            ZYPEZDItemValueSetter.setValue(insTMsg.trctrAndTrailAccsFlg, siteSrvyInfo.trctrAndTrailAccsFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(insTMsg.trctrAndTrailAccsFlg, ZYPConstant.FLG_OFF_N);
        }

        insTMsgList.add(insTMsg);
    }

    /**
     * addUpdSiteSrvyTMsg
     * @param insTMsg DS_SITE_SRVYTMsg
     * @param pMsg NWZC183001PMsg
     * @param updTMsgList List<DS_SITE_SRVYTMsg>
     * @return boolean
     */
    private boolean addUpdSiteSrvyTMsg(NWZC183001PMsg pMsg, NWZC183001_siteSrvyInfoListPMsg siteSrvyInfo, List<DS_SITE_SRVYTMsg> updTMsgList) {
        DS_SITE_SRVYTMsg updTMsgKey = new DS_SITE_SRVYTMsg();

        ZYPEZDItemValueSetter.setValue(updTMsgKey.dsSiteSrvyPk, siteSrvyInfo.dsSiteSrvyPk);
        ZYPEZDItemValueSetter.setValue(updTMsgKey.glblCmpyCd, pMsg.glblCmpyCd);

        DS_SITE_SRVYTMsg updTMsg = (DS_SITE_SRVYTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(updTMsgKey);
        if (updTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            return false;
        }

        // add update TMsg
        ZYPEZDItemValueSetter.setValue(updTMsg.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.dsCpoConfigPk, siteSrvyInfo.dsCpoConfigPk);
        ZYPEZDItemValueSetter.setValue(updTMsg.cmpyInfoAptBldgNm, siteSrvyInfo.cmpyInfoAptBldgNm);
        ZYPEZDItemValueSetter.setValue(updTMsg.cmpyInfoFlNm, siteSrvyInfo.cmpyInfoFlNm);
        ZYPEZDItemValueSetter.setValue(updTMsg.cmpyInfoDeptNm, siteSrvyInfo.cmpyInfoDeptNm);
        ZYPEZDItemValueSetter.setValue(updTMsg.otsdStepNum, siteSrvyInfo.otsdStepNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.insdStepNum, siteSrvyInfo.insdStepNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.flgtStairNum, siteSrvyInfo.flgtStairNum);
        // 2018/01/09 S21_NA#18460(Sol#087) MOD START
        //ZYPEZDItemValueSetter.setValue(updTMsg.elevAvalFromHourMn, siteSrvyInfo.elevAvalFromHourMn);
        //ZYPEZDItemValueSetter.setValue(updTMsg.elevAvalToHourMn, siteSrvyInfo.elevAvalToHourMn);
        ZYPEZDItemValueSetter.setValue(updTMsg.elevCtacTelNum, siteSrvyInfo.elevCtacTelNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.elevWdt, siteSrvyInfo.elevWdt);
        ZYPEZDItemValueSetter.setValue(updTMsg.elevDepthNum, siteSrvyInfo.elevDepthNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.elevCtacPsnNm, siteSrvyInfo.elevCtacPsnNm);
        ZYPEZDItemValueSetter.setValue(updTMsg.elevCapWt, siteSrvyInfo.elevCapWt);
        ZYPEZDItemValueSetter.setValue(updTMsg.elevDoorHgt, siteSrvyInfo.elevDoorHgt);
        ZYPEZDItemValueSetter.setValue(updTMsg.elevDoorWdt, siteSrvyInfo.elevDoorWdt);
        ZYPEZDItemValueSetter.setValue(updTMsg.stairAndLdgWdt, siteSrvyInfo.stairAndLdgWdt);
        ZYPEZDItemValueSetter.setValue(updTMsg.crdrWdt, siteSrvyInfo.crdrWdt);
        ZYPEZDItemValueSetter.setValue(updTMsg.doorWdt, siteSrvyInfo.doorWdt);
        ZYPEZDItemValueSetter.setValue(updTMsg.loadDockHgt, siteSrvyInfo.loadDockHgt);
        ZYPEZDItemValueSetter.setValue(updTMsg.bldgEntDoorHgt, siteSrvyInfo.bldgEntDoorHgt);
        ZYPEZDItemValueSetter.setValue(updTMsg.bldgEntDoorWdt, siteSrvyInfo.bldgEntDoorWdt);
        //ZYPEZDItemValueSetter.setValue(updTMsg.loadDockAvalFromHourMn, siteSrvyInfo.loadDockAvalFromHourMn);
        //ZYPEZDItemValueSetter.setValue(updTMsg.loadDockAvalToHourMn, siteSrvyInfo.loadDockAvalToHourMn);
        //ZYPEZDItemValueSetter.setValue(updTMsg.carrDelyTmHourMn, siteSrvyInfo.carrDelyTmHourMn);
        setTimeForSite(pMsg, siteSrvyInfo, updTMsg);
        // 2018/01/09 S21_NA#18460(Sol#087) MOD END
        ZYPEZDItemValueSetter.setValue(updTMsg.delyTrnspOptCd, siteSrvyInfo.delyTrnspOptCd);
        ZYPEZDItemValueSetter.setValue(updTMsg.siteSrvyAddlCmntTxt, siteSrvyInfo.siteSrvyAddlCmntTxt);
        if (ZYPCommonFunc.hasValue(siteSrvyInfo.stairCrawReqFlg)) {
            ZYPEZDItemValueSetter.setValue(updTMsg.stairCrawReqFlg, siteSrvyInfo.stairCrawReqFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(updTMsg.stairCrawReqFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(siteSrvyInfo.elevAvalFlg)) {
            ZYPEZDItemValueSetter.setValue(updTMsg.elevAvalFlg, siteSrvyInfo.elevAvalFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(updTMsg.elevAvalFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(siteSrvyInfo.elevApptReqFlg)) {
            ZYPEZDItemValueSetter.setValue(updTMsg.elevApptReqFlg, siteSrvyInfo.elevApptReqFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(updTMsg.elevApptReqFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(siteSrvyInfo.elevProtReqFlg)) {
            ZYPEZDItemValueSetter.setValue(updTMsg.elevProtReqFlg, siteSrvyInfo.elevProtReqFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(updTMsg.elevProtReqFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(siteSrvyInfo.loadDockAvalFlg)) {
            ZYPEZDItemValueSetter.setValue(updTMsg.loadDockAvalFlg, siteSrvyInfo.loadDockAvalFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(updTMsg.loadDockAvalFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(siteSrvyInfo.trctrAndTrailAccsFlg)) {
            ZYPEZDItemValueSetter.setValue(updTMsg.trctrAndTrailAccsFlg, siteSrvyInfo.trctrAndTrailAccsFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(updTMsg.trctrAndTrailAccsFlg, ZYPConstant.FLG_OFF_N);
        }

        updTMsgList.add(updTMsg);
        return true;
    }

    /**
     * addRmvSiteSrvyTMsg
     * @param insTMsg DS_SITE_SRVYTMsg
     * @param pMsg NWZC183001PMsg
     * @param rmvTMsgList List<DS_SITE_SRVYTMsg>
     * @return boolean
     */
    private boolean addRmvSiteSrvyTMsg(NWZC183001PMsg pMsg, NWZC183001_siteSrvyInfoListPMsg siteSrvyInfo, List<DS_SITE_SRVYTMsg> rmvTMsgList) {
        DS_SITE_SRVYTMsg rmvTMsgKey = new DS_SITE_SRVYTMsg();

        ZYPEZDItemValueSetter.setValue(rmvTMsgKey.dsSiteSrvyPk, siteSrvyInfo.dsSiteSrvyPk);
        ZYPEZDItemValueSetter.setValue(rmvTMsgKey.glblCmpyCd, pMsg.glblCmpyCd);

        DS_SITE_SRVYTMsg rmvTMsg = (DS_SITE_SRVYTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(rmvTMsgKey);
        if (rmvTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(rmvTMsg.getReturnCode())) {
            return false;
        }

        rmvTMsgList.add(rmvTMsg);
        return true;
    }

    /**
     * addInsCtacPsnTMsg
     * @param pMsg NWZC183001PMsg
     * @param ctacPsnInfo NWZC183001_cpoCtacPsnInfoListPMsg
     * @param insTMsgList List<DS_CPO_CTAC_PSNTMsg>
     */
    private void addInsCtacPsnTMsg(NWZC183001PMsg pMsg, NWZC183001_cpoCtacPsnInfoListPMsg ctacPsnInfo, List<DS_CPO_CTAC_PSNTMsg> insTMsgList) {

        // S21_NA#15889 Add Start
        if (!ZYPCommonFunc.hasValue(ctacPsnInfo.ctacPsnFirstNm)) {
            return;
        }
        // S21_NA#15889 Add End

        DS_CPO_CTAC_PSNTMsg insTMsg = new DS_CPO_CTAC_PSNTMsg();

        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ctacPsnInfo.dsCpoCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_CTAC_PSN_SQ));
        ZYPEZDItemValueSetter.setValue(insTMsg.dsCpoCtacPsnPk, ctacPsnInfo.dsCpoCtacPsnPk);
        // QC#2275 Add START
        ZYPEZDItemValueSetter.setValue(insTMsg.ctacPsnPk, ctacPsnInfo.ctacPsnPk);
        // QC#2275 Add END
        ZYPEZDItemValueSetter.setValue(insTMsg.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.dsCpoConfigPk, ctacPsnInfo.dsCpoConfigPk);
        ZYPEZDItemValueSetter.setValue(insTMsg.ctacPsnTpCd, ctacPsnInfo.ctacPsnTpCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.ctacPsnFirstNm, ctacPsnInfo.ctacPsnFirstNm);
        ZYPEZDItemValueSetter.setValue(insTMsg.ctacPsnLastNm, ctacPsnInfo.ctacPsnLastNm);
        ZYPEZDItemValueSetter.setValue(insTMsg.ctacPsnEmlAddr, ctacPsnInfo.ctacPsnEmlAddr);
        ZYPEZDItemValueSetter.setValue(insTMsg.ctacPsnTelNum, ctacPsnInfo.ctacPsnTelNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.ctacPsnFaxNum, ctacPsnInfo.ctacPsnFaxNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.ctacPsnSortNum, ctacPsnInfo.ctacPsnSortNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.ctacPsnExtnNum, ctacPsnInfo.ctacPsnExtnNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.ctacPsnOvrdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.ctacCustRefTpCd, ctacPsnInfo.ctacCustRefTpCd); //S21_NA#16452 Add

        insTMsgList.add(insTMsg);
    }

    /**
     * addUpdCtacPsnTMsg
     * @param pMsg NWZC183001PMsg
     * @param ctacPsnInfo NWZC183001_cpoCtacPsnInfoListPMsg
     * @param updTMsgList List<DS_CPO_CTAC_PSNTMsg>
     * @return boolean
     */
    private boolean addUpdCtacPsnTMsg(NWZC183001PMsg pMsg, NWZC183001_cpoCtacPsnInfoListPMsg ctacPsnInfo, List<DS_CPO_CTAC_PSNTMsg> updTMsgList) {
        DS_CPO_CTAC_PSNTMsg updTMsgKey = new DS_CPO_CTAC_PSNTMsg();

        ZYPEZDItemValueSetter.setValue(updTMsgKey.dsCpoCtacPsnPk, ctacPsnInfo.dsCpoCtacPsnPk);
        ZYPEZDItemValueSetter.setValue(updTMsgKey.glblCmpyCd, pMsg.glblCmpyCd);

        DS_CPO_CTAC_PSNTMsg updTMsg = (DS_CPO_CTAC_PSNTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(updTMsgKey);
        if (updTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            return false;
        }

        // add update TMsg
        ZYPEZDItemValueSetter.setValue(updTMsg.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.dsCpoConfigPk, ctacPsnInfo.dsCpoConfigPk);
        // QC#2275 Add START
        ZYPEZDItemValueSetter.setValue(updTMsg.ctacPsnPk, ctacPsnInfo.ctacPsnPk);
        // QC#2275 Add END
        ZYPEZDItemValueSetter.setValue(updTMsg.ctacPsnTpCd, ctacPsnInfo.ctacPsnTpCd);
        ZYPEZDItemValueSetter.setValue(updTMsg.ctacPsnFirstNm, ctacPsnInfo.ctacPsnFirstNm);
        ZYPEZDItemValueSetter.setValue(updTMsg.ctacPsnLastNm, ctacPsnInfo.ctacPsnLastNm);
        ZYPEZDItemValueSetter.setValue(updTMsg.ctacPsnEmlAddr, ctacPsnInfo.ctacPsnEmlAddr);
        ZYPEZDItemValueSetter.setValue(updTMsg.ctacPsnTelNum, ctacPsnInfo.ctacPsnTelNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.ctacPsnFaxNum, ctacPsnInfo.ctacPsnFaxNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.ctacPsnSortNum, ctacPsnInfo.ctacPsnSortNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.ctacPsnExtnNum, ctacPsnInfo.ctacPsnExtnNum);

        updTMsgList.add(updTMsg);
        return true;
    }

    /**
     * addRmvCtacPsnTMsg
     * @param pMsg NWZC183001PMsg
     * @param ctacPsnInfo NWZC183001_cpoCtacPsnInfoListPMsg
     * @param rmvTMsgList List<DS_CPO_CTAC_PSNTMsg>
     * @return boolean
     */
    private boolean addRmvCtacPsnTMsg(NWZC183001PMsg pMsg, NWZC183001_cpoCtacPsnInfoListPMsg ctacPsnInfo, List<DS_CPO_CTAC_PSNTMsg> rmvTMsgList) {
        DS_CPO_CTAC_PSNTMsg rmvTMsgKey = new DS_CPO_CTAC_PSNTMsg();

        ZYPEZDItemValueSetter.setValue(rmvTMsgKey.dsCpoCtacPsnPk, ctacPsnInfo.dsCpoCtacPsnPk);
        ZYPEZDItemValueSetter.setValue(rmvTMsgKey.glblCmpyCd, pMsg.glblCmpyCd);

        DS_CPO_CTAC_PSNTMsg rmvTMsg = (DS_CPO_CTAC_PSNTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(rmvTMsgKey);
        if (rmvTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(rmvTMsg.getReturnCode())) {
            return false;
        }

        rmvTMsgList.add(rmvTMsg);
        return true;
    }

    /**
     * checkMode
     * @param mode String
     */
    private void checkMode(String mode) {
        if (!ZYPCommonFunc.hasValue(mode)) {
            msgMap.addXxMsgId(NWZM0012E);
            return;
        } else if (!MODE_NEW.equals(mode) && !MODE_MOD.equals(mode) && !MODE_DEL.equals(mode)) {
            msgMap.addXxMsgId(NWZM0013E);
            return;
        }
        return;
    }

    /**
     * hasList
     * @param list List
     * @return boolean
     */
    private boolean hasList(List list) {
        if (list == null) {
            return false;
        }

        if (list.size() == 0) {
            return false;
        }
        return true;
    }

    // 2017/06/29 S21_NA#19515 Del Start
//    // QC#14593 2016/09/28 Mod Start
//    /**
//     * updateCustIstlFlg
//     * @param pMsg NWZC183001PMsg
//     * @return boolean
//     */
//    private boolean updateCustIstlFlg(NWZC183001PMsg pMsg) {
//        NWZC183001_cpoInstInfoListPMsg cpoInstlInfo = null;
//        List<Map< ? , ? >> list = null;
//        List<CPO_DTLTMsg> updList = new ArrayList<CPO_DTLTMsg>();
//        for (int index = 0; index < pMsg.cpoInstInfoList.getValidCount(); index++) {
//            cpoInstlInfo = pMsg.cpoInstInfoList.no(index);
//            Map<String, Object> key = new HashMap<String, Object>();
//            key.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//            key.put("ordNum", pMsg.cpoOrdNum.getValue());
//            key.put("dsCpoConfigPk", cpoInstlInfo.dsCpoConfigPk.getValue());
//            key.put("outbound", CONFIG_CATG.OUTBOUND);
//            key.put("close", ORD_LINE_STS.CLOSED);
//            key.put("cancel", ORD_LINE_STS.CANCELLED);
//            list = null;
//            if (ZYPCommonFunc.hasValue(cpoInstlInfo.dsCpoConfigPk)) {
//                list = (List<Map< ? , ? >>) ssmBatchClient.queryObjectList("querySvcIstlFlgByConfig", key);
//            } else {
//                list = (List<Map< ? , ? >>) ssmBatchClient.queryObjectList("querySvcIstlFlgByHeader", key);
//            }
//            for (Map< ? , ? > map : list) {
//                CPO_DTLTMsg dsCpoDtlTMsgKey = new CPO_DTLTMsg();
//                ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsgKey.glblCmpyCd, pMsg.glblCmpyCd.getValue());
//                ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsgKey.cpoOrdNum, pMsg.cpoOrdNum.getValue());
//                ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsgKey.cpoDtlLineNum, (String) map.get("CPO_DTL_LINE_NUM"));
//                ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsgKey.cpoDtlLineSubNum, (String) map.get("CPO_DTL_LINE_SUB_NUM"));
//
//                CPO_DTLTMsg tMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(dsCpoDtlTMsgKey);
//
//                if (tMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
//                    msgMap.addXxMsgId(NWZM2021E);
//                    return false;
//                }
//
//                // QC#14593-02 2016/11/18 Mod Start
////                ZYPEZDItemValueSetter.setValue(tMsg.custIstlFlg, (String) map.get("SVC_ISTL_RULE_FLG"));
//                // QC#14593-02 2016/11/18 Mod End
//                updList.add(tMsg);
//            }
//        }
//        // DS_CPO update
//        if (!updList.isEmpty()) {
//            int cnt = S21FastTBLAccessor.update(updList.toArray(new CPO_DTLTMsg[0]));
//            if (cnt != updList.size()) {
//                msgMap.addXxMsgId(NWZM2022E);
//                return false;
//            }
//        }
//        return true;
//    }
//    // QC#14593 2016/09/28 Mod End
    // 2017/06/29 S21_NA#19515 Del End
    // 2017/06/29 S21_NA#19515 Add Start
    private boolean updateCustIstlFlg(
            List<DS_CPO_ISTL_INFOTMsg> insTMsgList, //
            List<DS_CPO_ISTL_INFOTMsg> updTMsgList) {

        List<DS_CPO_ISTL_INFOTMsg> checkDsCpoIstlInfoTMsgList = new ArrayList<DS_CPO_ISTL_INFOTMsg>(0);

        for (DS_CPO_ISTL_INFOTMsg insTMsg : insTMsgList) {
            if (!finedInList(checkDsCpoIstlInfoTMsgList, insTMsg)) {
                DS_CPO_ISTL_INFOTMsg addTMsg = new DS_CPO_ISTL_INFOTMsg();
                EZDMsg.copy(insTMsg, null, addTMsg, null);
                checkDsCpoIstlInfoTMsgList.add(addTMsg);
            }
        }

        for (DS_CPO_ISTL_INFOTMsg updTMsg : updTMsgList) {
            if (!finedInList(checkDsCpoIstlInfoTMsgList, updTMsg)) {
                DS_CPO_ISTL_INFOTMsg addTMsg = new DS_CPO_ISTL_INFOTMsg();
                EZDMsg.copy(updTMsg, null, addTMsg, null);
                checkDsCpoIstlInfoTMsgList.add(addTMsg);
            }
        }

        for (DS_CPO_ISTL_INFOTMsg checkDsCpoIstlInfoTMsg : checkDsCpoIstlInfoTMsgList) {
            String custIstlFlg = getCustIstlFlg(checkDsCpoIstlInfoTMsg);

            updateCpoDtlCustIstFlg(checkDsCpoIstlInfoTMsg, custIstlFlg);
        }
        return true;
    }

    private boolean finedInList(List<DS_CPO_ISTL_INFOTMsg> checkDsCpoIstlInfoTMsgList, DS_CPO_ISTL_INFOTMsg checkTMsg) {

        boolean findFlg = false;
        for (DS_CPO_ISTL_INFOTMsg checkDsCpoIstlInfoTMsg : checkDsCpoIstlInfoTMsgList) {
            boolean isHdrData = false;
            boolean isConfData = false;
            if (ZYPCommonFunc.hasValue(checkDsCpoIstlInfoTMsg.dsCpoConfigPk)) {
                DS_CPO_CONFIGTMsg dsCpoConfigTMsg = new DS_CPO_CONFIGTMsg();
                ZYPEZDItemValueSetter.setValue(dsCpoConfigTMsg.glblCmpyCd, checkDsCpoIstlInfoTMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsCpoConfigTMsg.dsCpoConfigPk, checkDsCpoIstlInfoTMsg.dsCpoConfigPk);
                dsCpoConfigTMsg = (DS_CPO_CONFIGTMsg) S21FastTBLAccessor.findByKey(dsCpoConfigTMsg);
                if (dsCpoConfigTMsg != null) {
                    if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, dsCpoConfigTMsg.configCatgCd.getValue())) {
                        isHdrData = false;
                        isConfData = true;
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            } else {
                isHdrData = true;
                isConfData = false;
            }
            if (isHdrData && S21StringUtil.isEquals(checkDsCpoIstlInfoTMsg.cpoOrdNum.getValue(), checkTMsg.cpoOrdNum.getValue())) {
                findFlg = true;
                break;
            }
            if (isConfData //
                    && S21StringUtil.isEquals(checkDsCpoIstlInfoTMsg.cpoOrdNum.getValue(), checkTMsg.cpoOrdNum.getValue())) {
                BigDecimal dsCpoConfigPk = checkDsCpoIstlInfoTMsg.dsCpoConfigPk.getValue();
                BigDecimal checkDsCpoConfigPk = checkTMsg.dsCpoConfigPk.getValue();
                if (dsCpoConfigPk != null //
                        && checkDsCpoConfigPk != null //
                        && dsCpoConfigPk.compareTo(checkDsCpoConfigPk) == 0) {
                    findFlg = true;
                    break;
                }
            }
        }
        return findFlg;
    }

    private String getCustIstlFlg(DS_CPO_ISTL_INFOTMsg dsCpoIstlInfoTMsg) {

        SVC_ISTL_RULETMsg svcIstlRuleTMsg = new SVC_ISTL_RULETMsg();
        ZYPEZDItemValueSetter.setValue(svcIstlRuleTMsg.glblCmpyCd, dsCpoIstlInfoTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcIstlRuleTMsg.svcIstlRuleNum, dsCpoIstlInfoTMsg.svcIstlRuleNum);

        svcIstlRuleTMsg = (SVC_ISTL_RULETMsg) S21CacheTBLAccessor.findByKey(svcIstlRuleTMsg);
        if (svcIstlRuleTMsg != null) {
            return svcIstlRuleTMsg.svcCustIstlFlg.getValue();
        } else {
            return ZYPConstant.FLG_OFF_N;
        }
    }

    private void updateCpoDtlCustIstFlg(DS_CPO_ISTL_INFOTMsg checkDsCpoIstlInfoTMsg, String custIstFlg) {

        String glblCmpyCd = checkDsCpoIstlInfoTMsg.glblCmpyCd.getValue();
        String cpoOrdNum = checkDsCpoIstlInfoTMsg.cpoOrdNum.getValue();
        BigDecimal dsCpoConfigPk = checkDsCpoIstlInfoTMsg.dsCpoConfigPk.getValue();

        CPO_DTLTMsg queryKeyTMsg = new CPO_DTLTMsg();
        queryKeyTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        queryKeyTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        queryKeyTMsg.setConditionValue("ordLineStsCd01", ORD_LINE_STS.CANCELLED);
        queryKeyTMsg.setConditionValue("openFlg01", ZYPConstant.FLG_ON_Y);

        if (ZYPCommonFunc.hasValue(dsCpoConfigPk)) {
            queryKeyTMsg.setSQLID("020");
            queryKeyTMsg.setConditionValue("dsCpoConfigPk01", dsCpoConfigPk);
        } else {
            queryKeyTMsg.setSQLID("019");
        }

        CPO_DTLTMsgArray cpoDtlTMsgArray = (CPO_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(queryKeyTMsg);

        if (cpoDtlTMsgArray != null) {
            String[] valKey = new String[1];
            valKey[0] = "custIstlFlg";

            for (int i = 0; i < cpoDtlTMsgArray.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsgArray.no(i).custIstlFlg, custIstFlg);
                S21ApiTBLAccessor.updateSelectionField(cpoDtlTMsgArray.no(i), valKey);
            }
        }
        return;
    }
    // 2017/06/29 S21_NA#19515 Add End
    // QC#14608 2016/10/06 Add Start
    /**
     * getDsCpoConfig
     * @param pMsg NWZC183001PMsg
     * @return DS_CPO_CONFIGTMsgArray
     */
    // 2017/06/29 QC#19218 Mod Start
//    private DS_CPO_CONFIGTMsgArray getDsCpoConfig(NWZC183001PMsg pMsg) {
//        DS_CPO_CONFIGTMsg dsCpoConfCondTMsg = new DS_CPO_CONFIGTMsg();
//        dsCpoConfCondTMsg.setSQLID("001");
//        dsCpoConfCondTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
//        dsCpoConfCondTMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());
//        return (DS_CPO_CONFIGTMsgArray) S21ApiTBLAccessor.findByCondition(dsCpoConfCondTMsg);
//    }
    private List<Map<String, Object>> getDsCpoConfig(NWZC183001PMsg pMsg) {
        Map<String, String> key = new HashMap<String, String>();
        key.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        key.put("ordNum", pMsg.cpoOrdNum.getValue());
        // 2019/01/25 QC#30005 Del Start
        //key.put("dropShipFlg", ZYPConstant.FLG_OFF_N);
        // 2019/01/25 QC#30005 Del End
        return ssmBatchClient.queryObjectList("getDsCpoConfig", key);
    }
    // 2017/06/29 QC#19218 Mod End
    // QC#14608 2016/10/06 Add End

    // 2018/01/10 S21_NA#18460(Sol#087) ADD START
    @SuppressWarnings("unchecked")
    private void setOrdShipInfo(NWZC183001PMsg pMsg) {
        Map<String, String> key = new HashMap<String, String>();
        key.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        key.put("cpoOrdNum", pMsg.cpoOrdNum.getValue());

        Map shipInfoMap = (Map) ssmBatchClient.queryObject("searchWithOrdNum", key);
        ZYPEZDItemValueSetter.setValue(pMsg.ctryCd, (String) shipInfoMap.get("ADD_SHIP_TO_CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.postCd, (String) shipInfoMap.get("ADD_SHIP_TO_POST_CD"));
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getOrdShipInfoWithConf(NWZC183001PMsg pMsg, BigDecimal confPk) {
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        key.put("dsCpoConfigPk", confPk);

        return  (Map) ssmBatchClient.queryObject("searchWithConfPk", key);
    }

    private void setTimeForDely(NWZC183001PMsg pMsg, NWZC183001_cpoDelyInfoListPMsg cpoDelyInfo, DS_CPO_DELY_INFOTMsg tMsg) {
        if (ZYPCommonFunc.hasValue(cpoDelyInfo.xxYesNoCd_DI) && ZYPConstant.FLG_ON_Y.equals(cpoDelyInfo.xxYesNoCd_DI.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.opsFromHourMn, cpoDelyInfo.opsFromHourMn);
            ZYPEZDItemValueSetter.setValue(tMsg.opsToHourMn, cpoDelyInfo.opsToHourMn);
        } else if (!ZYPCommonFunc.hasValue(cpoDelyInfo.dsCpoConfigPk)) {
            ZYPEZDItemValueSetter.setValue(tMsg.opsFromHourMn, getConvertTime(pMsg.ctryCd.getValue(), pMsg.postCd.getValue(), cpoDelyInfo.opsFromHourMn.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.opsToHourMn, getConvertTime(pMsg.ctryCd.getValue(), pMsg.postCd.getValue(), cpoDelyInfo.opsToHourMn.getValue()));
        } else {
            Map<String, Object> map = getOrdShipInfoWithConf(pMsg, cpoDelyInfo.dsCpoConfigPk.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.opsFromHourMn, getConvertTime((String) map.get("SHIP_TO_CTRY_CD"), (String) map.get("SHIP_TO_POST_CD"), cpoDelyInfo.opsFromHourMn.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.opsToHourMn, getConvertTime((String) map.get("SHIP_TO_CTRY_CD"), (String) map.get("SHIP_TO_POST_CD"), cpoDelyInfo.opsToHourMn.getValue()));
        }
    }

    private void setTimeForIstl(NWZC183001PMsg pMsg, NWZC183001_cpoInstInfoListPMsg cpoIstlInfo, DS_CPO_ISTL_INFOTMsg tMsg) {
        if (ZYPCommonFunc.hasValue(cpoIstlInfo.xxYesNoCd_II) && ZYPConstant.FLG_ON_Y.equals(cpoIstlInfo.xxYesNoCd_II.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.rqstIstlTm, cpoIstlInfo.rqstIstlTm);
        } else if (!ZYPCommonFunc.hasValue(cpoIstlInfo.dsCpoConfigPk)) {
            ZYPEZDItemValueSetter.setValue(tMsg.rqstIstlTm, getConvertTime(pMsg.ctryCd.getValue(), pMsg.postCd.getValue(), cpoIstlInfo.rqstIstlTm.getValue()));
        } else {
            Map<String, Object> map = getOrdShipInfoWithConf(pMsg, cpoIstlInfo.dsCpoConfigPk.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.rqstIstlTm, getConvertTime((String) map.get("SHIP_TO_CTRY_CD"), (String) map.get("SHIP_TO_POST_CD"), cpoIstlInfo.rqstIstlTm.getValue()));
        }
    }

    private void setTimeForSite(NWZC183001PMsg pMsg, NWZC183001_siteSrvyInfoListPMsg siteSrvyInfo, DS_SITE_SRVYTMsg tMsg) {
        if (ZYPCommonFunc.hasValue(siteSrvyInfo.xxYesNoCd_SS) && ZYPConstant.FLG_ON_Y.equals(siteSrvyInfo.xxYesNoCd_SS.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.elevAvalFromHourMn, siteSrvyInfo.elevAvalFromHourMn);
            ZYPEZDItemValueSetter.setValue(tMsg.elevAvalToHourMn, siteSrvyInfo.elevAvalToHourMn);
            ZYPEZDItemValueSetter.setValue(tMsg.loadDockAvalFromHourMn, siteSrvyInfo.loadDockAvalFromHourMn);
            ZYPEZDItemValueSetter.setValue(tMsg.loadDockAvalToHourMn, siteSrvyInfo.loadDockAvalToHourMn);
            ZYPEZDItemValueSetter.setValue(tMsg.carrDelyTmHourMn, siteSrvyInfo.carrDelyTmHourMn);
        } else if (!ZYPCommonFunc.hasValue(siteSrvyInfo.dsCpoConfigPk)) {
            ZYPEZDItemValueSetter.setValue(tMsg.elevAvalFromHourMn, getConvertTime(pMsg.ctryCd.getValue(), pMsg.postCd.getValue(), siteSrvyInfo.elevAvalFromHourMn.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.elevAvalToHourMn, getConvertTime(pMsg.ctryCd.getValue(), pMsg.postCd.getValue(), siteSrvyInfo.elevAvalToHourMn.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.loadDockAvalFromHourMn, getConvertTime(pMsg.ctryCd.getValue(), pMsg.postCd.getValue(), siteSrvyInfo.loadDockAvalFromHourMn.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.loadDockAvalToHourMn, getConvertTime(pMsg.ctryCd.getValue(), pMsg.postCd.getValue(), siteSrvyInfo.loadDockAvalToHourMn.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.carrDelyTmHourMn, getConvertTime(pMsg.ctryCd.getValue(), pMsg.postCd.getValue(), siteSrvyInfo.carrDelyTmHourMn.getValue()));
        } else {
            Map<String, Object> map = getOrdShipInfoWithConf(pMsg, siteSrvyInfo.dsCpoConfigPk.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.elevAvalFromHourMn, getConvertTime((String) map.get("SHIP_TO_CTRY_CD"), (String) map.get("SHIP_TO_POST_CD"), siteSrvyInfo.elevAvalFromHourMn.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.elevAvalToHourMn, getConvertTime((String) map.get("SHIP_TO_CTRY_CD"), (String) map.get("SHIP_TO_POST_CD"), siteSrvyInfo.elevAvalToHourMn.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.loadDockAvalFromHourMn, getConvertTime((String) map.get("SHIP_TO_CTRY_CD"), (String) map.get("SHIP_TO_POST_CD"), siteSrvyInfo.loadDockAvalFromHourMn.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.loadDockAvalToHourMn, getConvertTime((String) map.get("SHIP_TO_CTRY_CD"), (String) map.get("SHIP_TO_POST_CD"), siteSrvyInfo.loadDockAvalToHourMn.getValue()));
            ZYPEZDItemValueSetter.setValue(tMsg.carrDelyTmHourMn, getConvertTime((String) map.get("SHIP_TO_CTRY_CD"), (String) map.get("SHIP_TO_POST_CD"), siteSrvyInfo.carrDelyTmHourMn.getValue()));
        }
    }

    private String getConvertTime(String ctryCd, String postCd, String time) {
        if (time == null || "".equals(time)) {
            return null;
        }
        String convertTime = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, //
                ZYPDateUtil.getSalesDate() + time, ctryCd, postCd).getDateTime().substring(TIME_SUBSTRING_FROM, TIME_SUBSTRING_TO);

        return convertTime;
    }
    // 2018/01/10 S21_NA#18460(Sol#087) ADD END

    // 2019/12/19 QC#54229 Add Start
    /**
     * autoAddSsCp
     * @param pMsg NWZC183001PMsg
     * @return boolean
     */
    private boolean autoAddSsCp(NWZC183001PMsg pMsg) {

        List<DS_SITE_SRVYTMsg> insDsSiteSrvyTMsgList = new ArrayList<DS_SITE_SRVYTMsg>();
        List<DS_CPO_CTAC_PSNTMsg> insDsCpoCtacPsnTMsgList = new ArrayList<DS_CPO_CTAC_PSNTMsg>();

        List<BigDecimal> createdList = new ArrayList<BigDecimal>();

        List<Map<String, Object>> inboundConfigList = getInboundConfig(pMsg);

        if (inboundConfigList == null || inboundConfigList.size() <= 0) {
            return true;
        }

        for (int i = 0; i < inboundConfigList.size(); i++) {
            Map<String, Object> inboundConfigMap = inboundConfigList.get(i);
            BigDecimal inboundConfigPk = (BigDecimal) inboundConfigMap.get("DS_CPO_CONFIG_PK");

            if (createdList.contains(inboundConfigPk)) {
                continue;
            } else {
                createdList.add(inboundConfigPk);
            }

            BigDecimal outboundConfigPk = getOutboundConfig(pMsg, inboundConfigPk);

            if (!ZYPCommonFunc.hasValue(outboundConfigPk)) {
                continue;
            }

            if (inboundConfigMap.get("DS_SITE_SRVY_PK") == null || !ZYPCommonFunc.hasValue((BigDecimal) inboundConfigMap.get("DS_SITE_SRVY_PK"))) {
                createAutoAddSiteSurveyList(pMsg, inboundConfigPk, outboundConfigPk, insDsSiteSrvyTMsgList);
            }

            if (inboundConfigMap.get("DS_CPO_CTAC_PSN_PK") == null || !ZYPCommonFunc.hasValue((BigDecimal) inboundConfigMap.get("DS_CPO_CTAC_PSN_PK"))) {
                createAutoAddContactsList(pMsg, inboundConfigPk, outboundConfigPk, insDsCpoCtacPsnTMsgList);
            }
        }

        // insert Site Survey
        if (!insDsSiteSrvyTMsgList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.insert(insDsSiteSrvyTMsgList.toArray(new DS_SITE_SRVYTMsg[0]));
            if (updCnt != insDsSiteSrvyTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1362E);
                return false;
            }
        }

        // insert Contacts
        if (!insDsCpoCtacPsnTMsgList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.insert(insDsCpoCtacPsnTMsgList.toArray(new DS_CPO_CTAC_PSNTMsg[0]));
            if (updCnt != insDsCpoCtacPsnTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1365E);
                return false;
            }
        }

        return true;
    }

    /**
     * createAutoAddSiteSurveyList
     * @param pMsg NWZC183001PMsg
     * @param dsCpoConfigPk BigDecimal
     * @param insDsSiteSrvyTMsgList  List<DS_SITE_SRVYTMsg 
     */
    private void createAutoAddSiteSurveyList(NWZC183001PMsg pMsg, BigDecimal inboundConfigMap, BigDecimal dsCpoConfigPk, List<DS_SITE_SRVYTMsg> insDsSiteSrvyTMsgList) {

        DS_SITE_SRVYTMsgArray dsSiteSrvyArray = getOutboundSiteSurvey(pMsg, dsCpoConfigPk);

        if (dsSiteSrvyArray == null || dsSiteSrvyArray.length() <= 0) {
            return;
        }

        for (int i = 0; i < dsSiteSrvyArray.length(); i++) {
            DS_SITE_SRVYTMsg inTmsg = (DS_SITE_SRVYTMsg) dsSiteSrvyArray.get(i);

            DS_SITE_SRVYTMsg insTmsg = new DS_SITE_SRVYTMsg();

            ZYPEZDItemValueSetter.setValue(insTmsg.glblCmpyCd, inTmsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(insTmsg.dsSiteSrvyPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_SITE_SRVY_SQ));
            ZYPEZDItemValueSetter.setValue(insTmsg.cpoOrdNum, inTmsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(insTmsg.dsCpoConfigPk, inboundConfigMap);
            ZYPEZDItemValueSetter.setValue(insTmsg.cmpyInfoAptBldgNm, inTmsg.cmpyInfoAptBldgNm);
            ZYPEZDItemValueSetter.setValue(insTmsg.cmpyInfoFlNm, inTmsg.cmpyInfoFlNm);
            ZYPEZDItemValueSetter.setValue(insTmsg.cmpyInfoDeptNm, inTmsg.cmpyInfoDeptNm);
            ZYPEZDItemValueSetter.setValue(insTmsg.cmpyInfoCtacNm, inTmsg.cmpyInfoCtacNm);
            ZYPEZDItemValueSetter.setValue(insTmsg.cmpyInfoTelNum, inTmsg.cmpyInfoTelNum);
            ZYPEZDItemValueSetter.setValue(insTmsg.otsdStepNum, inTmsg.otsdStepNum);
            ZYPEZDItemValueSetter.setValue(insTmsg.insdStepNum, inTmsg.insdStepNum);
            ZYPEZDItemValueSetter.setValue(insTmsg.stairCrawReqFlg, inTmsg.stairCrawReqFlg);
            ZYPEZDItemValueSetter.setValue(insTmsg.flgtStairNum, inTmsg.flgtStairNum);
            ZYPEZDItemValueSetter.setValue(insTmsg.elevAvalFlg, inTmsg.elevAvalFlg);
            ZYPEZDItemValueSetter.setValue(insTmsg.elevAvalFromHourMn, inTmsg.elevAvalFromHourMn);
            ZYPEZDItemValueSetter.setValue(insTmsg.elevAvalToHourMn, inTmsg.elevAvalToHourMn);
            ZYPEZDItemValueSetter.setValue(insTmsg.elevApptReqFlg, inTmsg.elevApptReqFlg);
            ZYPEZDItemValueSetter.setValue(insTmsg.elevCtacTelNum, inTmsg.elevCtacTelNum);
            ZYPEZDItemValueSetter.setValue(insTmsg.elevProtReqFlg, inTmsg.elevProtReqFlg);
            ZYPEZDItemValueSetter.setValue(insTmsg.elevWdt, inTmsg.elevWdt);
            ZYPEZDItemValueSetter.setValue(insTmsg.elevDepthNum, inTmsg.elevDepthNum);
            ZYPEZDItemValueSetter.setValue(insTmsg.elevCtacPsnNm, inTmsg.elevCtacPsnNm);
            ZYPEZDItemValueSetter.setValue(insTmsg.elevCapWt, inTmsg.elevCapWt);
            ZYPEZDItemValueSetter.setValue(insTmsg.elevDoorHgt, inTmsg.elevDoorHgt);
            ZYPEZDItemValueSetter.setValue(insTmsg.elevDoorWdt, inTmsg.elevDoorWdt);
            ZYPEZDItemValueSetter.setValue(insTmsg.stairAndLdgWdt, inTmsg.stairAndLdgWdt);
            ZYPEZDItemValueSetter.setValue(insTmsg.crdrWdt, inTmsg.crdrWdt);
            ZYPEZDItemValueSetter.setValue(insTmsg.doorWdt, inTmsg.doorWdt);
            ZYPEZDItemValueSetter.setValue(insTmsg.loadDockAvalFlg, inTmsg.loadDockAvalFlg);
            ZYPEZDItemValueSetter.setValue(insTmsg.loadDockHgt, inTmsg.loadDockHgt);
            ZYPEZDItemValueSetter.setValue(insTmsg.trctrAndTrailAccsFlg, inTmsg.trctrAndTrailAccsFlg);
            ZYPEZDItemValueSetter.setValue(insTmsg.bldgEntDoorHgt, inTmsg.bldgEntDoorHgt);
            ZYPEZDItemValueSetter.setValue(insTmsg.bldgEntDoorWdt, inTmsg.bldgEntDoorWdt);
            ZYPEZDItemValueSetter.setValue(insTmsg.loadDockAvalFromHourMn, inTmsg.loadDockAvalFromHourMn);
            ZYPEZDItemValueSetter.setValue(insTmsg.loadDockAvalToHourMn, inTmsg.loadDockAvalToHourMn);
            ZYPEZDItemValueSetter.setValue(insTmsg.carrDelyTmHourMn, inTmsg.carrDelyTmHourMn);
            ZYPEZDItemValueSetter.setValue(insTmsg.delyTrnspOptCd, inTmsg.delyTrnspOptCd);
            ZYPEZDItemValueSetter.setValue(insTmsg.siteSrvyAddlCmntTxt, inTmsg.siteSrvyAddlCmntTxt);

            insDsSiteSrvyTMsgList.add(insTmsg);

        }
    }

    /**
     * createAutoAddContactsList
     * @param pMsg NWZC183001PMsg
     * @param dsCpoConfigPk BigDecimal
     * @param insDsCpoCtacPsnTMsgList List<DS_CPO_CTAC_PSNTMsg>
     */
    private void createAutoAddContactsList(NWZC183001PMsg pMsg, BigDecimal inboundConfigMap, BigDecimal dsCpoConfigPk, List<DS_CPO_CTAC_PSNTMsg> insDsCpoCtacPsnTMsgList) {

        DS_CPO_CTAC_PSNTMsgArray dsCpoCtacPsnArray = getOutboundContacts(pMsg, dsCpoConfigPk);

        if (dsCpoCtacPsnArray == null || dsCpoCtacPsnArray.length() <= 0) {
            return;
        }

        for (int i = 0; i < dsCpoCtacPsnArray.length(); i++) {
            DS_CPO_CTAC_PSNTMsg inTmsg = (DS_CPO_CTAC_PSNTMsg) dsCpoCtacPsnArray.get(i);

            DS_CPO_CTAC_PSNTMsg insTmsg = new DS_CPO_CTAC_PSNTMsg();

            ZYPEZDItemValueSetter.setValue(insTmsg.glblCmpyCd, inTmsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(insTmsg.dsCpoCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_CTAC_PSN_SQ));
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnPk, inTmsg.ctacPsnPk);
            ZYPEZDItemValueSetter.setValue(insTmsg.cpoOrdNum, inTmsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(insTmsg.dsCpoConfigPk, inboundConfigMap);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnTpCd, inTmsg.ctacPsnTpCd);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnOvrdFlg, inTmsg.ctacPsnOvrdFlg);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnFirstNm, inTmsg.ctacPsnFirstNm);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnMidNm, inTmsg.ctacPsnMidNm);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnLastNm, inTmsg.ctacPsnLastNm);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnEmlAddr, inTmsg.ctacPsnEmlAddr);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnExtnNum, inTmsg.ctacPsnExtnNum);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnTelNum, inTmsg.ctacPsnTelNum);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnFaxNum, inTmsg.ctacPsnFaxNum);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnFirstLineAddr, inTmsg.ctacPsnFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnScdLineAddr, inTmsg.ctacPsnScdLineAddr);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnThirdLineAddr, inTmsg.ctacPsnThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnFrthLineAddr, inTmsg.ctacPsnFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnPostCd, inTmsg.ctacPsnPostCd);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnCtyAddr, inTmsg.ctacPsnCtyAddr);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnStCd, inTmsg.ctacPsnStCd);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnCtryCd, inTmsg.ctacPsnCtryCd);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnSortNum, inTmsg.ctacPsnSortNum);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnCmntTxt, inTmsg.ctacPsnCmntTxt);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacPsnCellPhoNum, inTmsg.ctacPsnCellPhoNum);
            ZYPEZDItemValueSetter.setValue(insTmsg.dsCtacPntPk_01, inTmsg.dsCtacPntPk_01);
            ZYPEZDItemValueSetter.setValue(insTmsg.dsCtacPntPk_02, inTmsg.dsCtacPntPk_02);
            ZYPEZDItemValueSetter.setValue(insTmsg.dsCtacPntPk_03, inTmsg.dsCtacPntPk_03);
            ZYPEZDItemValueSetter.setValue(insTmsg.dsCtacPntPk_04, inTmsg.dsCtacPntPk_04);
            ZYPEZDItemValueSetter.setValue(insTmsg.ctacCustRefTpCd, inTmsg.ctacCustRefTpCd);

            insDsCpoCtacPsnTMsgList.add(insTmsg);

        }
    }

    /**
     * getInboundConfig
     * @param pMsg NWZC183001PMsg
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getInboundConfig(NWZC183001PMsg pMsg) {
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        key.put("cpoOrdNum", pMsg.cpoOrdNum.getValue());
        key.put("configCatgCd", CONFIG_CATG.INBOUND);
        key.put("rtrnLineStsCdList", new String[] {ORD_LINE_STS.CANCELLED});
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getInboundConfig", key);
    }

    /**
     * getOutboundConfig
     * @param pMsg NWZC183001PMsg
     * @param dsCpoConfigPk BigDecimal
     * @return List<Map<String, Object>>
     */
    private BigDecimal getOutboundConfig(NWZC183001PMsg pMsg, BigDecimal dsCpoConfigPk) {
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        key.put("cpoOrdNum", pMsg.cpoOrdNum.getValue());
        key.put("dsCpoConfigPk", dsCpoConfigPk);
        key.put("configCatgCd", CONFIG_CATG.OUTBOUND);
        key.put("ordLineStstCdList", new String[] {ORD_LINE_STS.CANCELLED});

        List<Map<String, Object>> outboundConfigList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getOutboundConfig", key);

        if (outboundConfigList == null || outboundConfigList.size() <= 0) {
            return null;
        } else {
            return (BigDecimal) outboundConfigList.get(0).get("DS_CPO_CONFIG_PK");
        }
    }

    /**
     * getOutboundSiteSurvey
     * @param pMsg NWZC183001PMsg
     * @param dsCpoConfigPk BigDecimal
     * @return DS_SITE_SRVYTMsgArray
     */
    private DS_SITE_SRVYTMsgArray getOutboundSiteSurvey(NWZC183001PMsg pMsg, BigDecimal dsCpoConfigPk) {
        DS_SITE_SRVYTMsg dsSiteSurveyTMsg = new DS_SITE_SRVYTMsg();

        dsSiteSurveyTMsg.setSQLID("006");
        dsSiteSurveyTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        dsSiteSurveyTMsg.setConditionValue("dsCpoConfigPk01", dsCpoConfigPk);
        return (DS_SITE_SRVYTMsgArray) S21ApiTBLAccessor.findByCondition(dsSiteSurveyTMsg);
    }

    /**
     * getOutboundContacts
     * @param pMsg NWZC183001PMsg
     * @param dsCpoConfigPk BigDecimal
     * @return DS_CPO_CTAC_PSNTMsgArray
     */
    private DS_CPO_CTAC_PSNTMsgArray getOutboundContacts(NWZC183001PMsg pMsg, BigDecimal dsCpoConfigPk) {
        DS_CPO_CTAC_PSNTMsg dsCpoCtacPsnTMsg = new DS_CPO_CTAC_PSNTMsg();

        dsCpoCtacPsnTMsg.setSQLID("012");
        dsCpoCtacPsnTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        dsCpoCtacPsnTMsg.setConditionValue("dsCpoConfigPk01", dsCpoConfigPk);
        return (DS_CPO_CTAC_PSNTMsgArray) S21ApiTBLAccessor.findByCondition(dsCpoCtacPsnTMsg);
    }
    // 2019/12/19 QC#54229 Add End
}
