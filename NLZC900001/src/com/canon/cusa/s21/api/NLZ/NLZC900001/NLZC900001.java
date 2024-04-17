package com.canon.cusa.s21.api.NLZ.NLZC900001;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NLZC900001PMsg;
import business.parts.NLZC900001_xxShipInfoListPMsg;
import business.parts.NLZC900001_xxShipInfoListPMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

public class NLZC900001 extends S21ApiCommonBase {



    /** Program ID */
    private static final String PROGRAM_ID = NLZC900001.class.getSimpleName();
	

    
    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NLZC900001() {
        super();

    }

    /**
     * <pre>
     * Inventory Allocation API (List)
     * </pre>
     * @param params      Input parameter list.
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(final List<NLZC900001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NLZC900001PMsg param : params) {
            execute(param, onBatchType);
        }
    }
    
    /**
     * <pre>
     * Inventory Allocation API
     * </pre>
     * @param param       Input parameters.
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(final NLZC900001PMsg param, final ONBATCH_TYPE onBatchType) {

        // Checking Input value
    	S21ApiMessageMap msgMap  = new S21ApiMessageMap(param);

        // main
        doProcess(msgMap);
        // end
        msgMap.flush();
    }
    
    /**
     * Main process
     * @param msgMap S21ApiMessageMap
     */
    protected void doProcess(S21ApiMessageMap msgMap) {

        NLZC900001PMsg param = (NLZC900001PMsg) msgMap.getPmsg();
        
        String glblCmpyCd = param.glblCmpyCd.getValue();
        String shipNum = param.prtShipNum.getValue();
        NLZC900001_xxShipInfoListPMsgArray shipInfoListPMsgArray = param.xxShipInfoList;
        
        // Shipment Noをもとにshipping Informationを検索する
        HashMap<String, String> searchShipInfoParam = new HashMap<String, String>();
        searchShipInfoParam.put("glblCmpyCd", glblCmpyCd);
        searchShipInfoParam.put("shipNum", shipNum);
        
        // Shipment Informationを検索
        S21SsmEZDResult searchShipInfoResult = NLZC9000Query.getInstance().searchShipInfo(searchShipInfoParam);
        
        // 検索結果を判定する
        if (searchShipInfoResult.isCodeNormal()) {
      	  
      	  List<Map<String, Object>> shipInfoList = (List<Map<String, Object>>) searchShipInfoResult.getResultObject();
      	  
      	  if (shipInfoList.size() < 1) {
      		  System.out.println("No result for searchShipInfoResult");
      	  }
      	  
      	  int valCnt = 0;
      	  for (int i = 0; i < shipInfoList.size(); i++) {
      		
           	System.out.println("################# WMSLOG START - shipment info search ################");
           	System.out.println("Shipment #             : " + shipNum);
           	System.out.println("Shipping Order#        : " + (String) shipInfoList.get(i).get("SO_NUM"));
           	System.out.println("Shipping Orde Slip No  : " +  (String) shipInfoList.get(i).get("SO_SLP_NUM"));
            
           	System.out.println("BIN LOCATION           : " + (String) shipInfoList.get(i).get("BIN_LOC_CD"));
        	System.out.println("ITEM CODE              : " + (String) shipInfoList.get(i).get("MDSE_CD"));
        	
        	System.out.println("WH CD                  : " + (String) shipInfoList.get(i).get("RTL_WH_CD"));
        	System.out.println("INVTY_OWNR_CD          : " + (String) shipInfoList.get(i).get("INVTY_OWNR_CD"));
        	System.out.println("SUB WH CD              : " + (String) shipInfoList.get(i).get("RTL_SWH_CD"));

        	System.out.println("STOCK STATUS           : " +  (String) shipInfoList.get(i).get("FROM_STK_STS_CD"));
        	System.out.println("QUANTITY               : " + (BigDecimal) shipInfoList.get(i).get("SHPG_QTY"));
        	
        	System.out.println("WEIGHT                 : " +  (BigDecimal) shipInfoList.get(i).get("TOT_SHPG_WT"));
        	
          	System.out.println("SHIP TO CUST CODE      : " + (String)shipInfoList.get(i).get("SHIP_TO_CUST_CD"));
        	System.out.println("LOCATION               : " + (String)shipInfoList.get(i).get("LOC_NM"));
           	System.out.println("################# WMSLOG  END #######################################");

           	
           	NLZC900001_xxShipInfoListPMsg xxShipInfoList = shipInfoListPMsgArray.no(i);
           	
           	ZYPEZDItemValueSetter.setValue(xxShipInfoList.soNum,    (String) shipInfoList.get(i).get("SO_NUM"));
           	ZYPEZDItemValueSetter.setValue(xxShipInfoList.soSlpNum,    (String) shipInfoList.get(i).get("SO_SLP_NUM"));
           	
           	ZYPEZDItemValueSetter.setValue(xxShipInfoList.binLocCd,    (String) shipInfoList.get(i).get("BIN_LOC_CD"));
           	ZYPEZDItemValueSetter.setValue(xxShipInfoList.mdseCd,   (String) shipInfoList.get(i).get("MDSE_CD"));
           	
           	ZYPEZDItemValueSetter.setValue(xxShipInfoList.rtlWhCd,   (String) shipInfoList.get(i).get("RTL_WH_CD"));
           	ZYPEZDItemValueSetter.setValue(xxShipInfoList.invtyOwnrCd,   (String) shipInfoList.get(i).get("INVTY_OWNR_CD"));
           	ZYPEZDItemValueSetter.setValue(xxShipInfoList.rtlSwhCd,   (String) shipInfoList.get(i).get("RTL_SWH_CD"));
           	
           	ZYPEZDItemValueSetter.setValue(xxShipInfoList.fromStkStsCd,   (String) shipInfoList.get(i).get("FROM_STK_STS_CD"));
           	ZYPEZDItemValueSetter.setValue(xxShipInfoList.shpgQty,   (BigDecimal) shipInfoList.get(i).get("SHPG_QTY"));
           	ZYPEZDItemValueSetter.setValue(xxShipInfoList.totShpgWt,   (BigDecimal) shipInfoList.get(i).get("TOT_SHPG_WT"));
           	
           	valCnt++;
      	  }
      	  // リストに件数を追加
      	  shipInfoListPMsgArray.setValidCount(valCnt);
        }
        
        // リストの件数が0件の場合、shipmentが存在するか判定する
        if (shipInfoListPMsgArray.getValidCount() < 1) {
            
            // Shipmentを検索
            S21SsmEZDResult searchShipmentResult = NLZC9000Query.getInstance().searchShipment(searchShipInfoParam);
            
            // 検索結果を判定する
            if (searchShipmentResult.isCodeNormal()) {
                
                List<Map<String, Object>> soNumList = (List<Map<String, Object>>) searchShipmentResult.getResultObject();
                
                if (soNumList.size() < 1 ) {
                    System.out.println("No Result for searchShipmentResult");
                }
                
                int valCnt = 0;
                for (int i = 0; i < soNumList.size(); i++) {
                    System.out.println("################# WMSLOG START - shipment info search ################");
                    System.out.println("Shipment #             : " + shipNum);
                    System.out.println("Shipping Order#        : " + (String) soNumList.get(i).get("SO_NUM"));

                    System.out.println("################# WMSLOG  END #######################################");
                    
                    NLZC900001_xxShipInfoListPMsg xxSoNumList = shipInfoListPMsgArray.no(i);
                    ZYPEZDItemValueSetter.setValue(xxSoNumList.soNum,    (String) soNumList.get(i).get("SO_NUM"));

                    valCnt++;
                }
                // リストに件数を追加
                shipInfoListPMsgArray.setValidCount(valCnt);
                
            }
        }
        
    }
}

