package com.canon.cusa.s21.common.NWX.NWXC220001;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.BILL_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

public class NWXC220001QueryForCustomer {

    /**
     * Singleton instance.
     */

    private static final NWXC220001QueryForCustomer MY_INSTANCE = new NWXC220001QueryForCustomer();

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /**
     * @return MY_INSTANCE
     */
    protected static NWXC220001QueryForCustomer getInstance() {
        return MY_INSTANCE;
    }

    public List< ? > getBillToCustWithDsAcctFromCd(BILL_TO_CUSTTMsg inBillTMsg, String slsDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", inBillTMsg.glblCmpyCd.getValue());
        ssmParam.put("billToCustCd", inBillTMsg.billToCustCd.getValue());
        ssmParam.put("rgtnStsCd", inBillTMsg.rgtnStsCd.getValue());
        ssmParam.put("slsDt", slsDt);

        return (List< ? >) ssmBatchClient.queryObjectList("getBillToCustWithDsAcctFromCd", ssmParam);
    }

    public List< ? > getShipToCustWithDsAcctFromCd(SHIP_TO_CUSTTMsg inShipTMsg, String slsDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", inShipTMsg.glblCmpyCd.getValue());
        ssmParam.put("shipToCustCd", inShipTMsg.shipToCustCd.getValue());
        ssmParam.put("rgtnStsCd", inShipTMsg.rgtnStsCd.getValue());
        ssmParam.put("slsDt", slsDt);

        return (List< ? >) ssmBatchClient.queryObjectList("getShipToCustWithDsAcctFromCd", ssmParam);
    }

}
