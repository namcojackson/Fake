/**
 * <pre>
 * Search Contact Person.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/12/2015   Canon           S.Ohki          Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.api.NWX.NWXC021001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NWXC021001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

public class NWXC021001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /** "Bill To Customer CD" is required. */
    public static final String NWZM0617E = "NWZM0617E";

    /** "Ship To Customer CD" is required. */
    public static final String NWZM0619E = "NWZM0619E";

    /** The parameter's "Global Company Code" is not set. */
    public static final String NWZM0789E = "NWZM0789E";

    /** MAX_ROW_NUM */
    public static final int MAX_ROW_NUM = 200;

    /**
     * Initialize
     */
    public NWXC021001() {
        super();

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Execute
     * 
     * <pre>
     * BillToCustCd, ShipToCustCd, and use the Global Campay Cd, to get the Contact Person of the Location level.
     * If you can not get at the Location level, perform acquisition in Account level.
     * </pre>
     * @param param NWXC021001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWXC021001PMsg param, final ONBATCH_TYPE onBatchType) {
        this.msgMap = new S21ApiMessageMap(param);
        doProcess(param, onBatchType);
        msgMap.flush();
    }

    /**
     * Do Process
     * @param param NWXC021001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    private void doProcess(final NWXC021001PMsg param, final ONBATCH_TYPE onBatchType) {

        inputParameterCheck(param);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        String glblCmpyCd = param.glblCmpyCd.getValue();
        String billToCustCd = param.billToCustCd.getValue();
        String shipToCustCd = param.shipToCustCd.getValue();

        List<Map> resListBt = getLocationContactPersonByBillTo(glblCmpyCd, billToCustCd);

        if (resListBt.size() == 0) {
            resListBt = getAccountContactPersonByBillTo(glblCmpyCd, billToCustCd);
        }

        List<Map> resListSt = getLocationContactPersonByShipTo(glblCmpyCd, shipToCustCd);

        if (resListSt.size() == 0) {
            resListSt = getAccountContactPersonByShipTo(glblCmpyCd, shipToCustCd);
        }

        for (int i = 0; i < resListBt.size(); i++) {

            Map<String, BigDecimal> btMap = resListBt.get(i);

            for (int j = 0; j < resListSt.size(); j++) {

                Map<String, BigDecimal> stMap = resListSt.get(j);

                // if (btMap.get("CTAC_PSN_FIRST_NM").equals(stMap.get("CTAC_PSN_FIRST_NM")) && btMap.get("CTAC_PSN_LAST_NM").equals(stMap.get("CTAC_PSN_LAST_NM"))
                // && btMap.get("PHONE").equals(stMap.get("PHONE")) && btMap.get("EXT").equals(stMap.get("EXT"))
                // &&btMap.get("EMAIL").equals(stMap.get("EMAIL")) && btMap.get("FAX").equals(stMap.get("FAX"))) {
                if ((btMap.get("CTAC_PSN_PK")).compareTo(stMap.get("CTAC_PSN_PK")) == 0) {
                    resListSt.remove(j);
                }
            }
        }

        List retList = new ArrayList();
        retList.addAll(resListBt);
        retList.addAll(resListSt);

        int i = 0;
        for (; i < retList.size(); i++) {
            Map map = (HashMap) retList.get(i);

            ZYPEZDItemValueSetter.setValue(param.A.no(i).ctacPsnPk, (BigDecimal) map.get("CTAC_PSN_PK"));
            ZYPEZDItemValueSetter.setValue(param.A.no(i).ctacPsnFirstNm, (String) map.get("CTAC_PSN_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(param.A.no(i).ctacPsnLastNm, (String) map.get("CTAC_PSN_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(param.A.no(i).ctacTpCd, (String) map.get("CTAC_TP_CD"));
            ZYPEZDItemValueSetter.setValue(param.A.no(i).dsCtacPntValTxt_PH, (String) map.get("PHONE"));
            ZYPEZDItemValueSetter.setValue(param.A.no(i).dsCtacPsnExtnNum, (String) map.get("EXT"));
            ZYPEZDItemValueSetter.setValue(param.A.no(i).dsCtacPntValTxt_ML, (String) map.get("EMAIL"));
            ZYPEZDItemValueSetter.setValue(param.A.no(i).dsCtacPntValTxt_FX, (String) map.get("FAX"));

            if (i == 199) {
                break;
            }
        }
        param.A.setValidCount(i);
    }

    private void inputParameterCheck(final NWXC021001PMsg param) {

        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0789E);
        }

        if (!ZYPCommonFunc.hasValue(param.billToCustCd)) {
            msgMap.addXxMsgId(NWZM0617E);
        }

        if (!ZYPCommonFunc.hasValue(param.shipToCustCd)) {
            msgMap.addXxMsgId(NWZM0619E);
        }
    }

    /**
     * Get Location Contact Person By Bill To
     * @param glblCmpyCd
     * @param billToCustCd
     * @return List
     */
    private List<Map> getLocationContactPersonByBillTo(String glblCmpyCd, String billToCustCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("billToCustCd", billToCustCd);
        queryParam.put("ctacPntTpCd_P", DS_CTAC_PNT_TP.PHONE_WORK);
        queryParam.put("ctacPntTpCd_E", DS_CTAC_PNT_TP.EMAIL_WORK);
        queryParam.put("ctacPntTpCd_F", DS_CTAC_PNT_TP.FAX_WORK);
        queryParam.put("rowNum", MAX_ROW_NUM);
        queryParam.put("flgOnY", ZYPConstant.FLG_ON_Y);

        return (List<Map>) ssmBatchClient.queryObjectList("getLocationContactPersonByBillTo", queryParam);
    }

    /**
     * Get Account Contact Person By Bill To
     * @param glblCmpyCd
     * @param billToCustCd
     * @return List
     */
    private List<Map> getAccountContactPersonByBillTo(String glblCmpyCd, String billToCustCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("billToCustCd", billToCustCd);
        queryParam.put("ctacPntTpCd_P", DS_CTAC_PNT_TP.PHONE_WORK);
        queryParam.put("ctacPntTpCd_E", DS_CTAC_PNT_TP.EMAIL_WORK);
        queryParam.put("ctacPntTpCd_F", DS_CTAC_PNT_TP.FAX_WORK);
        queryParam.put("rowNum", MAX_ROW_NUM);
        queryParam.put("flgOnY", ZYPConstant.FLG_ON_Y);

        return (List<Map>) ssmBatchClient.queryObjectList("getAccountContactPersonByBillTo", queryParam);
    }

    /**
     * Get Location Contact Person By Ship To
     * @param glblCmpyCd
     * @param shipToCustCd
     * @return List
     */
    private List<Map> getLocationContactPersonByShipTo(String glblCmpyCd, String shipToCustCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("shipToCustCd", shipToCustCd);
        queryParam.put("ctacPntTpCd_P", DS_CTAC_PNT_TP.PHONE_WORK);
        queryParam.put("ctacPntTpCd_E", DS_CTAC_PNT_TP.EMAIL_WORK);
        queryParam.put("ctacPntTpCd_F", DS_CTAC_PNT_TP.FAX_WORK);
        queryParam.put("rowNum", MAX_ROW_NUM);
        queryParam.put("flgOnY", ZYPConstant.FLG_ON_Y);

        return (List<Map>) ssmBatchClient.queryObjectList("getLocationContactPersonByShipTo", queryParam);
    }

    /**
     * Get Account Contact Person By Ship To
     * @param glblCmpyCd
     * @param shipToCustCd
     * @return List
     */
    private List<Map> getAccountContactPersonByShipTo(String glblCmpyCd, String shipToCustCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("shipToCustCd", shipToCustCd);
        queryParam.put("ctacPntTpCd_P", DS_CTAC_PNT_TP.PHONE_WORK);
        queryParam.put("ctacPntTpCd_E", DS_CTAC_PNT_TP.EMAIL_WORK);
        queryParam.put("ctacPntTpCd_F", DS_CTAC_PNT_TP.FAX_WORK);
        queryParam.put("rowNum", MAX_ROW_NUM);
        queryParam.put("flgOnY", ZYPConstant.FLG_ON_Y);

        return (List<Map>) ssmBatchClient.queryObjectList("getAccountContactPersonByShipTo", queryParam);
    }
}
