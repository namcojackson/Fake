/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */

package business.blap.NLCL0280;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NLCL0280.constant.NLCL0280Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/25   CITS            T.Tokutomi      Create          N/A
 * 05/25/2016   CSAI            Y.Imazu         Update          QC#6864
 * 07/21/2016   CSAI            Y.Imazu         Update          QC#10315
 * 12/20/2017   CITS            K.Ogino         Update          QC#22066
 * 04/21/2023   CITS            DuyLe           Update          QC#61403
 *</pre>
 */
public class NLCL0280Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLCL0280Query MY_INSTANCE = new NLCL0280Query();

    /**
     * Constructor.
     */
    private NLCL0280Query() {
        super();

    }

    /**
     * Singleton instance getter.
     * @return MY_INSTANCE NLCL0280Query
     */
    public static NLCL0280Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getTrxCodeList
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrxCodeList(String glblCmpyCd) {

        HashMap<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invtyTrxFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("getTrxCodeList", ssmParam);
    }

    /**
     * selectTrxCodeList
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult selectTrxCodeList(String glblCmpyCd, String trxTpCd) {

        HashMap<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("trxTpCd", trxTpCd);
        ssmParam.put("dsCondConstGrpId", NLCL0280Constant.NLCL0280_INVTY_TRX);
        ssmParam.put("invtyTrxFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("selectTrxCodeList", ssmParam);
    }

    /**
     * selectTrxRsnCodeListNoTrxTp
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult selectTrxRsnCodeListNoTrxTp(String glblCmpyCd, String trxCd) {

        HashMap<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("trxCd", trxCd);
        ssmParam.put("invtyTrxFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("selectTrxRsnCodeListNoTrxTp", ssmParam);
    }

    /**
     * selectTrxRsnCodeListHasTrxTp
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult selectTrxRsnCodeListHasTrxTp(String glblCmpyCd, String trxCd, String trxTpCd) {

        HashMap<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("trxCd", trxCd);
        ssmParam.put("trxTpCd", trxTpCd);
        ssmParam.put("dsCondConstGrpId", NLCL0280Constant.NLCL0280_INVTY_TRX);
        ssmParam.put("invtyTrxFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("selectTrxRsnCodeListHasTrxTp", ssmParam);
    }

    /**
     * selectTrxRsnCodeList
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult selectTrxRsnCodeList(String glblCmpyCd, String trxTpCd) {

        HashMap<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("trxTpCd", trxTpCd);
        ssmParam.put("dsCondConstGrpId", NLCL0280Constant.NLCL0280_INVTY_TRX);
        ssmParam.put("invtyTrxFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("selectTrxRsnCodeList", ssmParam);
    }

    /**
     * getTrxTypeList
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrxTypeList(String glblCmpyCd) {

        HashMap<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);

        return getSsmEZDClient().queryObjectList("getTrxTypeList", ssmParam);
    }

    /**
     * getLocStsList
     * @param String glblCmpyCd
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocStsList(String glblCmpyCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsCondConstGrpId", NLCL0280Constant.LOC_STS_COND_CONST_GRP_ID);

        return getSsmEZDClient().queryObjectList("getLocStsList", params);
    }

    /**
     * getVndNm
     * @param glblCmpyCd String
     * @param vndCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getVndNm(String glblCmpyCd, String vndCd) {

        HashMap<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("vndCd", vndCd);

        return getSsmEZDClient().queryObject("getVndNm", ssmParam);
    }

    /**
     * getShipToLocationInfo
     * @param glblCmpyCd String
     * @param shipToLocCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToLocationInfo(String glblCmpyCd, String shipToLocCd) {

        HashMap<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("shipToLocCd", shipToLocCd);

        return getSsmEZDClient().queryObjectList("getShipToLocationInfo", ssmParam);
    }

    /**
     * getInvtyTrx
     * @param glblCmpyCd String
     * @param cMsg NLCL0280CMsg
     * @param ordTakeMdse boolean
     * @param shipFromLocTpCd String
     * @param shipToLocTpCd String
     * @param rowNum
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvtyTrx(String glblCmpyCd, NLCL0280CMsg cMsg, boolean ordTakeMdse, String shipFromLocTpCd, String shipToLocTpCd, int rowNum) {

        HashMap<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsCondConstGrpId", NLCL0280Constant.NLCL0280_INVTY_TRX);
        ssmParam.put("trxDtFrom", cMsg.trxDt_H1.getValue());
        ssmParam.put("trxDtTo", cMsg.trxDt_H2.getValue());
        ssmParam.put("trxId", cMsg.invtyTrxPk_H1.getValue());
        ssmParam.put("trxTpCd", cMsg.invtyTrxTpCd_PS.getValue());
        ssmParam.put("trxCd", cMsg.trxCd_PS.getValue());
        ssmParam.put("trxRsnCd", cMsg.trxRsnCd_PS.getValue());
        ssmParam.put("srcDocNum", cMsg.invtyTrxSlpNum_H1.getValue());
        ssmParam.put("rwsNum", cMsg.rwsNum_H1.getValue());
        ssmParam.put("soNum", cMsg.soNum_H1.getValue());
        ssmParam.put("mdseDescShortTxt", cMsg.mdseDescShortTxt_H1.getValue());
        ssmParam.put("mdseItemTpCd", cMsg.mdseItemTpCd_PS.getValue());
        ssmParam.put("coaMdseTpCd", cMsg.coaProjCd_PS.getValue());
        ssmParam.put("prodCd", cMsg.coaProdCd_H1.getValue());
        ssmParam.put("xrefItemTpCd", cMsg.mdseItemRelnTpCd_PS.getValue());
        ssmParam.put("xrefItemCd", cMsg.relnMdseCd_H1.getValue());
        ssmParam.put("serNum", cMsg.serNum_H1.getValue());
        ssmParam.put("splyItemNum", cMsg.splyItemNum_H1.getValue());
        ssmParam.put("mnfItemCd", cMsg.mnfItemCd_H1.getValue());
        ssmParam.put("rtlWhCd", cMsg.rtlWhCd_H1.getValue());
        ssmParam.put("rtlSwhCd", cMsg.rtlSwhCd_H1.getValue());
        ssmParam.put("vndCd", cMsg.vndCd_H1.getValue());
        ssmParam.put("shipFromLocCd", cMsg.shipFromLocCustCd_H1.getValue());
        ssmParam.put("shipToLocCd", cMsg.shipToLocCustCd_H1.getValue());
        // QC#22066
        ssmParam.put("invtyLocCdCR", "CR");

        if (ordTakeMdse) {

            ssmParam.put("ordTakeMdseCd", cMsg.mdseCd_H1.getValue() + "%");

        } else {

            ssmParam.put("mdseCd", cMsg.mdseCd_H1.getValue());
        }

        ArrayList<String> locStsList = new ArrayList<String>();

        for (int i = 0; i < cMsg.L.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.L.no(i).xxChkBox_LS.getValue())) {

                locStsList.add(cMsg.L.no(i).locStsCd_LS.getValue());
            }
        }

        if (!locStsList.isEmpty()) {

            ssmParam.put("locStsList", locStsList);
        }

        if (ZYPCommonFunc.hasValue(shipFromLocTpCd) 
                && (LOC_TP.CUSTOMER.equals(shipFromLocTpCd) || LOC_TP.VENDOR.equals(shipFromLocTpCd))) {

            ssmParam.put("shipFromLocTpFlg", ZYPConstant.FLG_ON_Y);

        } else if (ZYPCommonFunc.hasValue(shipFromLocTpCd) 
                && (LOC_TP.WAREHOUSE.equals(shipFromLocTpCd) || LOC_TP.TECHNICIAN.equals(shipFromLocTpCd))) {

            ssmParam.put("shipFromLocTpFlg", ZYPConstant.FLG_OFF_N);

        }

        if (ZYPCommonFunc.hasValue(shipToLocTpCd) 
                && (LOC_TP.CUSTOMER.equals(shipToLocTpCd) || LOC_TP.VENDOR.equals(shipToLocTpCd))) {

            ssmParam.put("shipToLocTpFlg", ZYPConstant.FLG_ON_Y);

        } else if (ZYPCommonFunc.hasValue(shipToLocTpCd) 
                && (LOC_TP.WAREHOUSE.equals(shipToLocTpCd) || LOC_TP.TECHNICIAN.equals(shipToLocTpCd))) {

            ssmParam.put("shipToLocTpFlg", ZYPConstant.FLG_OFF_N);

        }
        // Updated by DuyLe - 04/21/2023 - QC#61403 - START
        ssmParam.put("rowNum", rowNum);
        // Updated by DuyLe - 04/21/2023 - QC#61403 - END
        return getSsmEZDClient().queryObjectList("getInvtyTrx", ssmParam);
    }

    /**
     * getRcvSerList
     * @param glblCmpyCd String
     * @param rcvSerInvtyTrxPkList List<BigDecimal>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRcvSerList(String glblCmpyCd, List<BigDecimal> rcvSerInvtyTrxPkList) {

        HashMap<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rcvSerInvtyTrxPkList", rcvSerInvtyTrxPkList);

        return getSsmEZDClient().queryObjectList("getRcvSerList", ssmParam);
    }
}
