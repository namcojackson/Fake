/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC412001;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
  * SOM common component query
  *
  * Date         Company         Name            Create/Update   Defect No
  * ----------------------------------------------------------------------
  * 08/10/2016   Fujitsu         T.ishii         Create          N/A
  * 01/30/2017   SRA             K.Aratani       Update          QC#17120
  * 03/08/2017   SRA             K.Aratani       Update          QC#17768
  * 2017/06/22   Fujitsu         M.Yamada        Update          QC#19451
  * 02/16/2018   SRA             K.Aratani       Update          QC#24206
  * 02/16/2018   SRA             K.Aratani       Update          QC#24240
  * 04/05/2018   SRA             K.Aratani       Update          QC#25324
  * </pre>
 */
public class NWXC412001Query {

    /**
     * Singleton instance.
     */
    private static final NWXC412001Query MY_INSTANCE = new NWXC412001Query();

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /**
     * @return MY_INSTANCE
     */
    protected static NWXC412001Query getInstance() {
        return MY_INSTANCE;
    }

    protected List<Map<String, Object>> getXtrnlIntfcXref(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> result = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getXtrnlIntfcXref", ssmParam);

        return result;
    }

    protected String getPrcCatgCdFromName(Map<String, Object> ssmParam) {

        String result = (String) ssmBatchClient.queryObject("getPrcCatgCdFromName", ssmParam);

        return result;
    }

    protected String getPrcCatgCdFromId(Map<String, Object> ssmParam) {

        String result = (String) ssmBatchClient.queryObject("getPrcCatgCdFromId", ssmParam);

        return result;
    }

    protected Map<String, Object> getShipToInfoByLocNum(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getShipToInfo", ssmParam);

        return result;
    }

    protected Map<String, Object> getShipToInfoFromAddress(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getShipToInfo", ssmParam);

        return result;
    }

    protected Map<String, Object> getBillToInfoFromSomShipToPtyNum(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getBillToInfo", ssmParam);

        return result;
    }

    protected Map<String, Object> getBillToInfoFromAddress(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getBillToInfo", ssmParam);

        return result;
    }

    protected Map<String, Object> getBillToInfoByCode(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getBillToInfo", ssmParam);

        return result;
    }

    protected Map<String, Object> getSoldToInfoFromShipToCustCd(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getSoldToInfoFromShipToCustCd", ssmParam);

        return result;
    }

    protected Map<String, Object> getSoldToInfoFromShipToAcctCd(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getSoldToInfoFromShipToAcctCd", ssmParam);

        return result;
    }

    protected Map<String, Object> getSoldToInfoFromAddress(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getSoldToInfoFromAddress", ssmParam);

        return result;
    }

    protected String getDsLineCatgCd(Map<String, Object> ssmParam) {

        String result = (String) ssmBatchClient.queryObject("getDsLineCatgCd", ssmParam);

        return result;
    }

    protected BigDecimal getSpecCondPrcPkByPrcCondTpCd(Map<String, Object> ssmParam) {

        BigDecimal result = (BigDecimal) ssmBatchClient.queryObject("getSpecCondPrcPkByPrcCondTpCd", ssmParam);

        return result;
    }

    protected Map<String, Object> getMdse(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getMdse", ssmParam);

        return result;
    }

    protected Map<String, Object> getMdseBySerNum(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getSvcMachMstrBySerNum", ssmParam);

        return result;
    }

    protected Map<String, Object> getSvcMachMstrBySerNum(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getSvcMachMstrBySerNum", ssmParam);

        return result;
    }

    protected BigDecimal getSvcMachMstrPkBySerNum(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getSvcMachMstrBySerNum", ssmParam);

        if (result != null && !result.isEmpty()) {

            return (BigDecimal) result.get("SVC_MACH_MSTR_PK");
        }
        return null;
    }

    protected BigDecimal getSvcConfigMstrPkBySerNum(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getSvcMachMstrBySerNum", ssmParam);

        if (result != null && !result.isEmpty()) {

            return (BigDecimal) result.get("SVC_CONFIG_MSTR_PK");
        }
        return null;
    }

    protected BigDecimal getMdlIdByName(Map<String, Object> ssmParam) {

        BigDecimal result = (BigDecimal) ssmBatchClient.queryObject("getMdlIdByName", ssmParam);

        return result;
    }
    
    protected BigDecimal getMdlIdByMdse(Map<String, Object> ssmParam) {

        BigDecimal result = (BigDecimal) ssmBatchClient.queryObject("getMdlIdByMdse", ssmParam);

        return result;
    }
    
    protected Map<String, Object> getDsContrByContrNum(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getDsContrByContrNum", ssmParam);

        return result;
    }

    protected Map<String, Object> getPrcMtrPkgFromName(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getPrcMtrPkgFromName", ssmParam);

        return result;
    }

    protected List<Map<String, Object>> getPrcMtrPkgMtrStru(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> result = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPrcMtrPkgMtrStru", ssmParam);

        return result;
    }

    protected Map<String, Object> getSlsRepTocByResourceId(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getSlsRepTocByResourceId", ssmParam);

        return result;
    }

    protected Map<String, Object> getVendorInfo(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getVendorInfo", ssmParam);

        return result;
    }

    protected Map<String, Object> getCsmpContrInfo(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getCsmpContrInfo", ssmParam);

        return result;
    }

    protected Map<String, Object> getLineBizRoleTpCd(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getLineBizRoleTpCd", ssmParam);

        return result;
    }

    protected Map<String, Object> getPsnCdByPsnNm(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getPsnCdByPsnNm", ssmParam);

        return result;
    }

    protected Map<String, Object> getPsnCdByTocNm(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getPsnCdByTocNm", ssmParam);

        return result;
    }

    protected Map<String, Object> getPsnCdByPsnFullNm(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getPsnCdByPsnFullNm", ssmParam);

        return result;
    }

    protected Map<String, Object> getPsnCdByPsnCd(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getPsnCdByPsnCd", ssmParam);

        return result;
    }
    
    protected Map<String, Object> getDefaultMaintenanceCustomer(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getDefaultMaintenanceCustomer", ssmParam);

        return result;
    }
    
    //QC#17768
    protected List<Map<String, Object>> getConfigMasterDetailList(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> result = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getConfigMasterDetailList", ssmParam);

        return result;
    }
    //QC#25324
    protected List<Map<String, Object>> getConfigMasterDetailListFromConfigID(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> result = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getConfigMasterDetailListFromConfigID", ssmParam);

        return result;
    }
    //QC#15539-14
    protected Map<String, Object> getBillToInfoFromDsAcctNum(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getBillToInfoFromDsAcctNum", ssmParam);

        return result;
    }
    protected Map<String, Object> getAvailableRentalItem(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getAvailableRentalItem", ssmParam);

        return result;
    }

    protected Map<String, Object> getCSANumberByAcctCSMPNum(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getCSANumberByAcctCSMPNum", ssmParam);

        return result;
    }
    //QC#24240
    protected Map<String, Object> getCSANumberByAcctCSMPNumNull(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getCSANumberByAcctCSMPNumNull", ssmParam);

        return result;
    }

    // QC#19451
    public Integer getDecimalPointDigit(Map<String, Object> ssmParam) {
        return (Integer) ssmBatchClient.queryObject("getDecimalPointDigit", ssmParam);
    }

    public List<Map<String, Object>> getMdlIdFromMdlNm(Map<String, Object> ssmParam) {
        return NWXC412001.autoCast( ssmBatchClient.queryObjectList("getMdlIdFromMdlNm", ssmParam));
    }
    //QC#24206
    protected Map<String, Object> getCFSOwner(Map<String, Object> ssmParam) {

        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getCFSOwner", ssmParam);

        return result;
    }
}
