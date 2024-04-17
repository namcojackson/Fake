/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3070;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL3070.common.NLBL3070CommonLogic;
import business.blap.NLBL3070.constant.NLBL3070Constant;
import business.db.CPOTMsg;
import business.db.DS_SO_LINE_STSTMsg;
import business.db.SCHD_COORD_STSTMsg;
import business.file.NLBL3070F01FMsg;

import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BACK_ORD_IMPCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SVC_CALL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_COORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2015   Fujitsu         Y.Taoka         Create          N/A
 * 02/26/2016   CSAI            Y.Imazu         Update          QC#2046, 2201
 * 05/03/2016   CSAI            Y.Imazu         Update          QC#5125
 * 05/03/2016   CSAI            Y.Imazu         Update          QC#7334
 * 07/19/2016   CSAI            Y.Imazu         Update          QC#10232
 * 08/31/2016   CSAI            Y.Imazu         Update          QC#9845
 * 10/17/2016   CITS            K.Ogino         Update          QC#10406
 * 11/21/2016   CSAI            Y.Imazu         Update          QC#15969
 * 12/13/2016   CITS            T.Kikuhara      Update          QC#15622
 * 10/16/2016   CITS            S.Katsuma       Update          Sol#454
 * 01/09/2018   CITS            M.Naito         Update          QC#18889
 * 02/14/2018   CITS            T.Tokutomi      Update          QC#22613
 * 03/22/2018   CITS            S.Katsuma       Update          QC#24697
 * 07/02/2018   CITS            T.Tokutomi      Update          QC#23726
 * 08/24/2018   CITS            K.Ogino         Update          QC#27833
 * 10/03/2018   CITS            M.Naito         Update          QC#28539
 * 10/10/2018   CITS            M.Naito         Update          QC#28738
 * 10/25/2018   CITS            M.Naito         Update          QC#28867
 * 04/23/2019   Hitachi         K.Kitachi       Update          QC#31245
 * 05/31/2021   CITS            A.Marte         Update          QC#58786
 * 04/04/2022   CITS            R.Azucena       Update          QC#59802
 * 08/08/2022   CITS            R.Azucena       Update          QC#60416
 * 12/09/2022   Hitachi         T.Kuroda        Update          QC#60810
 *</pre>
 */
public final class NLBL3070Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLBL3070Query MYINSTANCE = new NLBL3070Query();

    /**
     * Constructor.
     */
    private NLBL3070Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL3070Query
     */
    public static NLBL3070Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * getOtbdCarrRsnList
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOtbdCarrRsnList(String glblCmpyCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("otbdDelyFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("getOtbdCarrRsnList", params);
    }

    /**
     * getInitSchdCoordStsList
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInitSchdCoordStsList(String glblCmpyCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getInitSchdCoordStsList", params);
    }

    /**
     * getSchdCoordStsList
     * @param glblCmpyCd String
     * @param sMsgALine NLBL3070_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSchdCoordStsList(String glblCmpyCd, NLBL3070_ASMsg sMsgALine) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("schdCoordStsCd", sMsgALine.schdCoordStsCd_A1.getValue());
        params.put("manUpdPrtyNum", sMsgALine.manUpdPrtyNum_A1.getValue());
        params.put("flgY", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("getSchdCoordStsList", params);
    }

    /**
     * getSavedSearchOptionList
     * @param glblCmpyCd String
     * @param usrId user id
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String glblCmpyCd, String usrId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("srchOptAplId", NLBL3070Constant.BUSINESS_ID);
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }

    /**
     * searchSchd
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchSchd(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        createSearchCondInit(ssmParam, cMsg.glblCmpyCd.getValue());
        ssmParam.put("dsShpgOrdDtlConn", chkDsShpgOrdDtlConn(cMsg));
        // START 2018/10/25 M.Naito [QC#28867,MOD]
//        ssmParam.put("cMsg", cMsg);
        ssmParam.put("rownum", sMsg.A.length());

        S21InfoLogOutput.println("### Set Parameters (SQLID: SearchSchd) ###################################");
        S21InfoLogOutput.println(ssmParam.toString());
        Map<String, String> logParams = NLBL3070CommonLogic.setParamsForLog(cMsg);
        S21InfoLogOutput.println(logParams.toString());

        ssmParam.put("cMsg", cMsg);
        // END 2018/10/25 M.Naito [QC#28867,MOD]
        return getSsmEZDClient().queryEZDMsgArray("SearchSchd", ssmParam, sMsg.A);
    }

    /**
     * reSearchSchd
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult reSearchSchd(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        createSearchCondInit(ssmParam, cMsg.glblCmpyCd.getValue());
        ssmParam.put("soNumList", createSoList(sMsg, false, true));
        ssmParam.put("rownum", sMsg.A.length());

        // START 2018/10/25 M.Naito [QC#28867,ADD]
        S21InfoLogOutput.println("### Set Parameters (SQLID: SearchSchd) ###################################");
        S21InfoLogOutput.println(ssmParam.toString());
        Map<String, String> logParams = NLBL3070CommonLogic.setParamsForLog(cMsg);
        S21InfoLogOutput.println(logParams.toString());
        // END 2018/10/25 M.Naito [QC#28867,ADD]
        return getSsmEZDClient().queryEZDMsgArray("SearchSchd", ssmParam, sMsg.A);
    }

    /**
     * searchSchdTab
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchSchdTab(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        createSearchCondInit(ssmParam, cMsg.glblCmpyCd.getValue());
        ssmParam.put("soNumList", createSoList(sMsg, true, false));
        ssmParam.put("rownum", sMsg.A.length());

        // START 2018/10/25 M.Naito [QC#28867,ADD]
        S21InfoLogOutput.println("### Set Parameters (SQLID: SearchSchd) ###################################");
        S21InfoLogOutput.println(ssmParam.toString());
        Map<String, String> logParams = NLBL3070CommonLogic.setParamsForLog(cMsg);
        S21InfoLogOutput.println(logParams.toString());
        // END 2018/10/25 M.Naito [QC#28867,ADD]
        return getSsmEZDClient().queryEZDMsgArray("SearchSchd", ssmParam, sMsg.A);
    }

    /**
     * getSsmParamDownLoadScheduling
     * @param cMsg NLBL3070CMsg
     * @param rownum int
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParamDownLoadScheduling(NLBL3070CMsg cMsg, int rownum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        createSearchCondInit(ssmParam, cMsg.glblCmpyCd.getValue());
        ssmParam.put("dsShpgOrdDtlConn", chkDsShpgOrdDtlConn(cMsg));
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("rownum", rownum);

        return ssmParam;
    }

    /**
     * searchDely
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchDely(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        createSearchCondInit(ssmParam, cMsg.glblCmpyCd.getValue());
        createSearchCondInitDelv(cMsg, ssmParam);
        // START 2018/10/25 M.Naito [QC#28867,MOD]
//        ssmParam.put("cMsg", cMsg);
        ssmParam.put("rownum", sMsg.B.length());
        // START 2018/03/22 S.Katsuma [QC#24697,MOD]
        // QC#22163 Add.
//        ssmParam.put("dsSoLineStsAS", DS_SO_LINE_STS.AWAITING_SHIPPING);
        ssmParam.put("dsSoLineStsAS", DS_SO_LINE_STS.ORDER_RELEASED);
        // END 2018/03/22 S.Katsuma [QC#24697,MOD]

        S21InfoLogOutput.println("### Set Parameters (SQLID: SearchDely) ###################################");
        S21InfoLogOutput.println(ssmParam.toString());
        Map<String, String> logParams = NLBL3070CommonLogic.setParamsForLog(cMsg);
        S21InfoLogOutput.println(logParams.toString());
        ssmParam.put("cMsg", cMsg);
        // END 2018/10/25 M.Naito [QC#28867,MOD]
        return getSsmEZDClient().queryEZDMsgArray("SearchDely", ssmParam, sMsg.B);
    }

    /**
     * searchDelyTab
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchDelyTab(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        createSearchCondInit(ssmParam, cMsg.glblCmpyCd.getValue());
        createSearchCondInitDelv(cMsg, ssmParam);
        ssmParam.put("boSrchFlg", ZYPConstant.FLG_OFF_N);
        // START 2018/10/25 M.Naito [QC#28867,MOD]
//        ssmParam.put("cMsg", cMsg);
        ssmParam.put("soNumList", createSoList(sMsg, true, true));
        ssmParam.put("rownum", sMsg.B.length());
        // START 2018/03/22 S.Katsuma [QC#24697,MOD]
        // QC#22163 Add.
//        ssmParam.put("dsSoLineStsAS", DS_SO_LINE_STS.AWAITING_SHIPPING);
        ssmParam.put("dsSoLineStsAS", DS_SO_LINE_STS.ORDER_RELEASED);
        // END 2018/03/22 S.Katsuma [QC#24697,MOD]

        S21InfoLogOutput.println("### Set Parameters (SQLID: SearchDely) ###################################");
        S21InfoLogOutput.println(ssmParam.toString());
        Map<String, String> logParams = NLBL3070CommonLogic.setParamsForLog(cMsg);
        S21InfoLogOutput.println(logParams.toString());
        ssmParam.put("cMsg", cMsg);
        // END 2018/10/25 M.Naito [QC#28867,MOD]
        return getSsmEZDClient().queryEZDMsgArray("SearchDely", ssmParam, sMsg.B);
    }

    /**
     * reSearchDely
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult reSearchDely(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        createSearchCondInit(ssmParam, cMsg.glblCmpyCd.getValue());
        createSearchCondInitDelv(cMsg, ssmParam);
        ssmParam.put("trxHrdNumList", createTrxHdrList(sMsg));
        ssmParam.put("rownum", sMsg.B.length());

        // SoNum
        ArrayList<String> soNumList = new ArrayList<String>();
        ArrayList<String> soNumSlpNumList = new ArrayList<String>();

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            soNumList.add(sMsg.B.no(i).soNum_B1.getValue());
            soNumSlpNumList.add(sMsg.B.no(i).soNum_B1.getValue() + sMsg.B.no(i).soSlpNum_B1.getValue());
        }

        ssmParam.put("soNumList", soNumList);
        ssmParam.put("soNumSlpNumList", soNumSlpNumList);
        // START 2018/03/22 S.Katsuma [QC#24697,MOD]
        // QC#22163 Add.
//        ssmParam.put("dsSoLineStsAS", DS_SO_LINE_STS.AWAITING_SHIPPING);
        ssmParam.put("dsSoLineStsAS", DS_SO_LINE_STS.ORDER_RELEASED);
        // END 2018/03/22 S.Katsuma [QC#24697,MOD]

        // START 2018/10/25 M.Naito [QC#28867,ADD]
        S21InfoLogOutput.println("### Set Parameters (SQLID: SearchDely) ###################################");
        S21InfoLogOutput.println(ssmParam.toString());
        Map<String, String> logParams = NLBL3070CommonLogic.setParamsForLog(cMsg);
        S21InfoLogOutput.println(logParams.toString());
        // END 2018/10/25 M.Naito [QC#28867,ADD]
        return getSsmEZDClient().queryEZDMsgArray("SearchDely", ssmParam, sMsg.B);
    }

    /**
     * getSsmParamDownLoadDeliveries
     * @param cMsg NLBL3070CMsg
     * @param rownum int
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParamDownLoadDeliveries(NLBL3070CMsg cMsg, int rownum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        createSearchCondInit(ssmParam, cMsg.glblCmpyCd.getValue());
        createSearchCondInitDelv(cMsg, ssmParam);
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("rownum", rownum);
        // START 2018/03/22 S.Katsuma [QC#24697,MOD]
        // QC#22163 Add.
//        ssmParam.put("dsSoLineStsAS", DS_SO_LINE_STS.AWAITING_SHIPPING);
        ssmParam.put("dsSoLineStsAS", DS_SO_LINE_STS.ORDER_RELEASED);
        // END 2018/03/22 S.Katsuma [QC#24697,MOD]

        return ssmParam;
    }

    /**
     * chkDsShpgOrdDtlConn
     * @param cMsg NLBL3070CMsg
     * @return String
     */
    private static String chkDsShpgOrdDtlConn(NLBL3070CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.dsSoLineStsCd)) {

            return ZYPConstant.FLG_ON_Y;

        } else if (ZYPCommonFunc.hasValue(cMsg.rtlSwhCd)) {

            return ZYPConstant.FLG_ON_Y;

        } else if (ZYPCommonFunc.hasValue(cMsg.actlDelyDt_FR)) {

            return ZYPConstant.FLG_ON_Y;

        } else if (ZYPCommonFunc.hasValue(cMsg.actlDelyDt_TO)) {

            return ZYPConstant.FLG_ON_Y;
        }

        return ZYPConstant.FLG_OFF_N;
    }

    /**
     * createSoList
     * @param sMsg NLBL3070SMsg
     * @param isChkOnly boolean
     * @param tabSchd boolean
     * @return ArrayList<String>
     */
    private static ArrayList<String> createSoList(NLBL3070SMsg sMsg, boolean isChkOnly, boolean tabSchd) {

        ArrayList<String> soNumList = new ArrayList<String>();

        if (tabSchd) {

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                if (isChkOnly) {

                    if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {

                        soNumList.add(sMsg.A.no(i).soNum_A1.getValue());
                    }

                } else {

                    soNumList.add(sMsg.A.no(i).soNum_A1.getValue());
                }
            }

        } else {

            for (int i = 0; i < sMsg.B.getValidCount(); i++) {

                if (isChkOnly) {

                    if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_B1.getValue())) {

                        soNumList.add(sMsg.B.no(i).soNum_B1.getValue());
                    }

                } else {

                    soNumList.add(sMsg.B.no(i).soNum_B1.getValue());
                }
            }
        }

        return soNumList;
    }

    /**
     * createTrxHdrList
     * @param sMsg NLBL3070SMsg
     * @return ArrayList<String>
     */
    private static ArrayList<String> createTrxHdrList(NLBL3070SMsg sMsg) {

        ArrayList<String> trxHdrNumList = new ArrayList<String>();

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            if (!trxHdrNumList.contains(sMsg.B.no(i).trxHdrNum_B1.getValue())) {

                trxHdrNumList.add(sMsg.B.no(i).trxHdrNum_B1.getValue());
            }
        }

        return trxHdrNumList;
    }

    /**
     * getCarrCdList
     * @param glblCmpyCd String
     * @param carrNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCarrCdList(String glblCmpyCd, String carrNm) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("carrNm", carrNm);

        return getSsmEZDClient().queryObjectList("getCarrCdList", ssmParam);
    }

    /**
     * getAcctCarrCnt
     * @param cMsg NLBL3070CMsg
     * @param dsAcctNum String
     * @param carrCd String
     * @param carrAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAcctCarrCnt(NLBL3070CMsg cMsg, String dsAcctNum, String carrCd, String carrAcctNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("slsDt", cMsg.slsDt.getValue());
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("carrCd", carrCd);
        ssmParam.put("carrAcctNum", carrAcctNum);

        return getSsmEZDClient().queryObject("getAcctCarrCnt", ssmParam);
    }

    /**
     * getCarrSvcLvlCdList
     * @param cMsg NLBL3070CMsg
     * @param shpgSvcLvlCd String
     * @param carrCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCarrSvcLvlCdList(NLBL3070CMsg cMsg, String shpgSvcLvlCd, String carrCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
        ssmParam.put("carrCd", carrCd);

        return getSsmEZDClient().queryObjectList("getCarrSvcLvlCdList", ssmParam);
    }

    /**
     * getShipToCustNm
     * @param cMsg NLBL3070CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToCustNm(NLBL3070CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("shipToCustCd", cMsg.shipToCustCd.getValue());

        return getSsmEZDClient().queryObject("GetShipToCustNm", ssmParam);
    }

    /**
     * getSoCancelAvalFlg
     * @param sMsgBLine NLBL3070_BSMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSoCancelAvalFlg(NLBL3070_BSMsg sMsgBLine, String glblCmpyCd) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlWhCd", sMsgBLine.rtlWhCd_B1.getValue());
        ssmParam.put("sceOrdTpCd", sMsgBLine.sceOrdTpCd_B1.getValue());
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);

        return getSsmEZDClient().queryObject("getSoCancelAvalFlg", ssmParam);
    }

    /**
     * getSerNumList
     * @param cMsgBLine NLBL3070_BCMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSerNumList(NLBL3070_BCMsg cMsgBLine, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("soNum", cMsgBLine.soNum_B1.getValue());
        ssmParam.put("soSlpNum", cMsgBLine.soSlpNum_B1.getValue());
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);

        if (ZYPCommonFunc.hasValue(cMsgBLine.proNum_B1)) {

            ssmParam.put("bolNum", cMsgBLine.proNum_B1.getValue());
        }

        String queryId = "GetSerNumListNotShip";

        if (ZYPCommonFunc.hasValue(cMsgBLine.proNum_B1) && ZYPConstant.FLG_ON_Y.equals(cMsgBLine.shipFlg_B1.getValue())) {

            queryId = "GetSerNumListShipped";
        }

        return getSsmEZDClient().queryObjectList(queryId, ssmParam);
    }

    /**
     * getSerNumListGlobal
     * @param glblCmpyCd String
     * @param sMsgBLine NLBL3070_BSMsg
     * @param fMsg NLBL3070F01FMsg
     * @param shipFlg String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSerNumListGlobal(String glblCmpyCd, NLBL3070_BSMsg sMsgBLine, NLBL3070F01FMsg fMsg, String shipFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);

        String bolNum = null;

        if (sMsgBLine != null) {

            ssmParam.put("soNum", sMsgBLine.soNum_B1.getValue());
            ssmParam.put("soSlpNum", sMsgBLine.soSlpNum_B1.getValue());
            bolNum = sMsgBLine.proNum_B1.getValue();

        } else {

            ssmParam.put("soNum", fMsg.soNum.getValue());
            ssmParam.put("soSlpNum", fMsg.soSlpNum.getValue());
            bolNum = fMsg.proNum.getValue();
        }

        if (!ZYPCommonFunc.hasValue(bolNum)) {

            bolNum = "*";
        }

        ssmParam.put("bolNum", bolNum);

        String queryId = "GetSerNumListNotShip";

        if (ZYPConstant.FLG_ON_Y.equals(shipFlg)) {

            queryId = "GetSerNumListShipped";
        }

        return getSsmEZDClient().queryObjectList(queryId, ssmParam);
    }

    /**
     * getPermissionWhList
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPermissionWhList(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPermissionWhList", ssmParam);
    }

    /**
     * createSearchCondInit
     * @param ssmParam Map<String, Object>
     * @param glblCmpyCd String
     */
    private static void createSearchCondInit(Map<String, Object> ssmParam, String glblCmpyCd) {

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("locTpCust", LOC_TP.CUSTOMER);
        ssmParam.put("soCustDataTpShipTo", SO_CUST_DATA_TP.SHIP_TO);
        ssmParam.put("dsSvcCallTpInstl", DS_SVC_CALL_TP.INSTALL);
        ssmParam.put("dsSvcCallTpAccInstl", DS_SVC_CALL_TP.ACC_INSTALL);
        ssmParam.put("svcTaskStsCancel", SVC_TASK_STS.CANCELLED);
        // START 2017/10/16 S.Katsuma [Sol#454,ADD]
//        ssmParam.put("ctacPsnTpShipTo", CTAC_TP.SHIP_TO_CONTACT);
        ssmParam.put("ctacPsnTpDelivIns", CTAC_TP.DELIVERY_INSTALL);
        ssmParam.put("ctacPsnTpOrdCtac", CTAC_TP.ORDER_CONTACT);
        // END 2017/10/16 S.Katsuma [Sol#454,ADD]
        ssmParam.put("shpgStsCdValid", SHPG_STS.VALIDATED);
        ssmParam.put("boImpctTpCrtcl", BACK_ORD_IMPCT_TP.CRITICAL);
        ssmParam.put("boImpctTpEsstl", BACK_ORD_IMPCT_TP.ESSENTIAL);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);

        // START 2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]
        String svcIstlRuleNumList = ZYPCodeDataUtil.getVarCharConstValue("SVC_ISTL_RULE_TYPE_NUM", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(svcIstlRuleNumList)) {
            ssmParam.put("svcIstlRuleNumList", svcIstlRuleNumList.split(","));
        }
        // START 2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]
        // QC#27833
        ssmParam.put("ordCatgCtxTp", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);

        // START 2021/05/31 A.Marte [QC#58786, ADD]
        // List for allowed PRCH_REQ types for search(Disposal & Vendor Return)
        ArrayList<String> includeSceOrdTpList = new ArrayList<String>();
        includeSceOrdTpList.add(SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC);
        includeSceOrdTpList.add(SCE_ORD_TP.DISPOSAL);
        ssmParam.put("includeSceOrdTpList", includeSceOrdTpList);
        // END 2021/05/31 A.Marte [QC#58786, ADD]
    }

    /**
     * createSearchCondInitDelv
     * @param cMsg NLBL3070CMsg
     * @param ssmParam Map<String, Object>
     */
    private static void createSearchCondInitDelv(NLBL3070CMsg cMsg, Map<String, Object> ssmParam) {

        ssmParam.put("dsSoLineStsPickConf", DS_SO_LINE_STS.PICK_CONFIRMED);
        ssmParam.put("dsSoLineStsInAssemblyShop", DS_SO_LINE_STS.IN_ASSEMBLY_SHOP);
        ssmParam.put("dsSoLineStsTechAssigned", DS_SO_LINE_STS.TECH_ASSIGNED);
        ssmParam.put("dsSoLineStsAwaitingShpg", DS_SO_LINE_STS.AWAITING_SHIPPING);
        ssmParam.put("dsCondConstShipCtrl", "NLZC2110_SHIP_CTRL");
        ssmParam.put("soProcSts", SO_PROC_STS.SHIP);

        // For Back Order
        ssmParam.put("dsSoLineStsBoDesc", getDsSoLineStsDescTxt(cMsg.glblCmpyCd.getValue(), DS_SO_LINE_STS.BACKORDERED));
        ssmParam.put("schdCoordStsBoDesc", getSchdCoordStsDescTxt(cMsg.glblCmpyCd.getValue(), SCHD_COORD_STS.AWAITING_BACKORDER_FULFILLMENT));
        ssmParam.put("dsOrdTpList", getDsOrdTpCdListForDt(cMsg.glblCmpyCd.getValue()));
        ssmParam.put("sceOrdTpRs", SCE_ORD_TP.DIRECT_SALES);
        ssmParam.put("sceOrdTpDt", SCE_ORD_TP.DC_TRANSFER);
        ssmParam.put("boSrchFlg", chkBoSrchCond(cMsg));
    }

    /**
     * getDsSoLineStsDescTxt
     * @param glblCmpyCd String
     * @param dsSoLineStsCd String
     * @return String
     */
    private static String getDsSoLineStsDescTxt(String glblCmpyCd, String dsSoLineStsCd) {

        DS_SO_LINE_STSTMsg dsSoLineStsTMsg = new DS_SO_LINE_STSTMsg();
        ZYPEZDItemValueSetter.setValue(dsSoLineStsTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsSoLineStsTMsg.dsSoLineStsCd, dsSoLineStsCd);
        dsSoLineStsTMsg = (DS_SO_LINE_STSTMsg) EZDTBLAccessor.findByKey(dsSoLineStsTMsg);

        if (dsSoLineStsTMsg == null) {

            return null;
        }

        return dsSoLineStsTMsg.dsSoLineStsDescTxt.getValue();
    }

    /**
     * getSchdCoordStsDescTxt
     * @param glblCmpyCd String
     * @param schdCoordStsCd String
     * @return String
     */
    private static String getSchdCoordStsDescTxt(String glblCmpyCd, String schdCoordStsCd) {

        SCHD_COORD_STSTMsg schdCoordStsTMsg = new SCHD_COORD_STSTMsg();
        ZYPEZDItemValueSetter.setValue(schdCoordStsTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(schdCoordStsTMsg.schdCoordStsCd, schdCoordStsCd);
        schdCoordStsTMsg = (SCHD_COORD_STSTMsg) EZDTBLAccessor.findByKey(schdCoordStsTMsg);

        if (schdCoordStsTMsg == null) {

            return null;
        }

        return schdCoordStsTMsg.schdCoordStsDescTxt.getValue();
    }

    /**
     * getDsOrdCatgCdForDt
     * @param glblCmpyCd String
     * @return String
     */
    private static String[] getDsOrdTpCdListForDt(String glblCmpyCd) {

        String dsOrdTpList = ZYPCodeDataUtil.getVarCharConstValue(NLBL3070Constant.VAR_CONST_CPO_DS_ORD_TP_FOR_WH_TRNSF, glblCmpyCd);
        return dsOrdTpList.split(",");
    }

    /**
     * chkBoSrchCond
     * @param cMsg NLBL3070CMsg
     * @return String
     */
    private static String chkBoSrchCond(NLBL3070CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.soNum)) {

            return ZYPConstant.FLG_OFF_N;

        } else if (ZYPCommonFunc.hasValue(cMsg.bolNum)) {

            return ZYPConstant.FLG_OFF_N;

        } else if (ZYPCommonFunc.hasValue(cMsg.schdCoordPsnCd)) {

            return ZYPConstant.FLG_OFF_N;

        } else if (ZYPCommonFunc.hasValue(cMsg.schdCarrPickUpDt_FR)) {

            return ZYPConstant.FLG_OFF_N;

        } else if (ZYPCommonFunc.hasValue(cMsg.schdCarrPickUpDt_TO)) {

            return ZYPConstant.FLG_OFF_N;

        } else if (ZYPCommonFunc.hasValue(cMsg.schdDelyDt_FR)) {

            return ZYPConstant.FLG_OFF_N;

        } else if (ZYPCommonFunc.hasValue(cMsg.schdDelyDt_TO)) {

            return ZYPConstant.FLG_OFF_N;

        } else if (ZYPCommonFunc.hasValue(cMsg.actlDelyDt_FR)) {

            return ZYPConstant.FLG_OFF_N;

        } else if (ZYPCommonFunc.hasValue(cMsg.actlDelyDt_TO)) {

            return ZYPConstant.FLG_OFF_N;

        } else if (ZYPCommonFunc.hasValue(cMsg.dsSoLineStsCd) && !DS_SO_LINE_STS.BACKORDERED.equals(cMsg.dsSoLineStsCd.getValue())) {

            return ZYPConstant.FLG_OFF_N;

        } else if (ZYPCommonFunc.hasValue(cMsg.schdCoordStsCd) && !SCHD_COORD_STS.AWAITING_BACKORDER_FULFILLMENT.equals(cMsg.schdCoordStsCd.getValue())) {

            return ZYPConstant.FLG_OFF_N;

        } else if (ZYPCommonFunc.hasValue(cMsg.wmsDropFlg_Y) && !ZYPCommonFunc.hasValue(cMsg.wmsDropFlg_N)) {

            return ZYPConstant.FLG_OFF_N;

        } else if (ZYPCommonFunc.hasValue(cMsg.delyReqFlg_Y) && !ZYPCommonFunc.hasValue(cMsg.delyReqFlg_N)) {

            return ZYPConstant.FLG_OFF_N;
        }

        return ZYPConstant.FLG_ON_Y;
    }

    // START 2018/01/09 M.Naito [QC#18889,ADD]
    /**
     * chkDeliveryScheduleMail
     * @param cMsg NLBL3070CMsg
     * @param soNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkDeliveryScheduleMail(NLBL3070CMsg cMsg, String soNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("soNum", soNum);
        ssmParam.put("dsSvcCallTp", DS_SVC_CALL_TP.INSTALL);
        ssmParam.put("scheduled", SVC_TASK_STS.SCHEDULED);
        ssmParam.put("assigned", SVC_TASK_STS.ASSIGNED);
        // START 2019/04/23 K.Kitachi [QC#31245, ADD]
        ssmParam.put("tbo", SVC_TASK_STS.TBO);
        // END 2019/04/23 K.Kitachi [QC#31245, ADD]
        String outTrtySvcBrCd = ZYPCodeDataUtil.getVarCharConstValue(NLBL3070Constant.VAR_CONST_OUT_TRTY_SVC_BR_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put("outTrtySvcBrCd", outTrtySvcBrCd);

        return getSsmEZDClient().queryObject("chkDeliveryScheduleMail", ssmParam);
    }

    // START 2018/10/03 M.Naito [QC#28539,ADD]
    /**
     * getSvcTaskInfoFromSoNum
     * @param cMsg NLBL3070CMsg
     * @param soNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcTaskInfoFromSoNum(NLBL3070CMsg cMsg, String soNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("soNum", soNum);

        return getSsmEZDClient().queryObject("getSvcTaskInfoFromSoNum", ssmParam);
    }

    /**
     * chkChngTechMeetTheTruck
     * @param cMsg NLBL3070CMsg
     * @param soNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkChngTechMeetTheTruck(NLBL3070CMsg cMsg, String soNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("soNum", soNum);
        ssmParam.put("dsSvcCallTp", DS_SVC_CALL_TP.INSTALL);
        ssmParam.put("scheduled", SVC_TASK_STS.SCHEDULED);
        ssmParam.put("assigned", SVC_TASK_STS.ASSIGNED);
        // START 2019/04/23 K.Kitachi [QC#31245, ADD]
        ssmParam.put("tbo", SVC_TASK_STS.TBO);
        ssmParam.put("inRoute", SVC_TASK_STS.IN_ROUTE);
        // END 2019/04/23 K.Kitachi [QC#31245, ADD]
        String outTrtySvcBrCd = ZYPCodeDataUtil.getVarCharConstValue(NLBL3070Constant.VAR_CONST_OUT_TRTY_SVC_BR_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put("outTrtySvcBrCd", outTrtySvcBrCd);

        return getSsmEZDClient().queryObject("chkChngTechMeetTheTruck", ssmParam);
    }
    // END 2018/10/03 M.Naito [QC#28539,ADD]

    /**
     * getSvcBrMngrEmlAddr
     * @param cMsg NLBL3070CMsg
     * @param soNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcBrMngrEmlAddr(NLBL3070CMsg cMsg, String soNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("soNum", soNum);
        ssmParam.put("orgFuncRoleTp", NLBL3070Constant.ORG_FUNC_ROLE_TP_BR_SVC_MGR);
        ssmParam.put("gnrnTpCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpFuture", GNRN_TP.FUTURE);
        ssmParam.put("gnrnTpDelete", GNRN_TP.DELETE);
        // START 2018/10/10 M.Naito [QC#28738,ADD]
        ssmParam.put("service", BIZ_AREA_ORG.SERVICE);
        ssmParam.put("slsDt", cMsg.slsDt.getValue());
        // END 2018/10/10 M.Naito [QC#28738,ADD]
        return getSsmEZDClient().queryObject("getSvcBrMngrEmlAddr", ssmParam);
    }

    /**
     * getDeliveryScheduleMailInfo
     * @param cMsg NLBL3070CMsg
     * @param soNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDeliveryScheduleMailInfo(NLBL3070CMsg cMsg, String soNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("soNum", soNum);
        return getSsmEZDClient().queryObjectList("getDeliveryScheduleMailInfo", ssmParam);
    }
    // END 2018/01/09 M.Naito [QC#18889,ADD]

    /**
     * getCPOTmsg
     * QC#23726 Add Method.
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return CPOTMsg
     */
    public CPOTMsg getCPOTmsg(String glblCmpyCd, String cpoOrdNum) {

        CPOTMsg param = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.cpoOrdNum, cpoOrdNum);

        return (CPOTMsg) EZDTBLAccessor.findByKey(param);
    }

    // START 2019/04/23 K.Kitachi [QC#31245, ADD]
    /**
     * getEmlAddrFromTaskSvcBrMgr
     * @param cMsg NLBL3070CMsg
     * @param soNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getEmlAddrFromTaskSvcBrMgr(NLBL3070CMsg cMsg, String soNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("soNum", soNum);
        return getSsmEZDClient().queryObject("getEmlAddrFromTaskSvcBrMgr", ssmParam);
    }
    // END 2019/04/23 K.Kitachi [QC#31245, ADD]

    // START 2021/05/31 A.Marte [QC#58786, ADD]
    /**
     * getInvtyTrxHdrNumFromSO
     * @param cMsg NLBL3070CMsg
     * @param soNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvtyTrxHdrNumFromSO(String glblCmpyCd, String soNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("soNum", soNum);
        return getSsmEZDClient().queryObject("getInvtyTrxHdrNumFromSO", ssmParam);
    }
    // END 2021/05/31 A.Marte [QC#58786, ADD]

    // START 2022/04/04 R.Azucena [QC#59802, ADD]
    // START 2022/08/08 R.Azucena [QC#60416, DEL]
    /**
     * getAvailSingleQty
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    // public S21SsmEZDResult getAvailSingleQty(Map<String, Object> ssmParam) {
    //     return getSsmEZDClient().queryObject("getAvailSingleQty", ssmParam);
    // }
    // END 2022/08/08 R.Azucena [QC#60416, DEL]
    // END 2022/04/04 R.Azucena [QC#59802, ADD]

    // START 2022/12/09 T.Kuroda [QC#60810, ADD]
    /**
     * getCountWMSWarehouseList
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCountWMSWarehouseList(String glblCmpyCd, String rtlWhCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlWhCd", rtlWhCd);
        return getSsmEZDClient().queryObject("getCountWMSWarehouseList", ssmParam);
    }
    // END 2022/12/09 T.Kuroda [QC#60810, ADD]
}
