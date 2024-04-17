package com.canon.cusa.s21.api.NLZ.NLZC901001;

import java.math.BigDecimal;
import java.util.List;

import business.parts.NLZC400001PMsg;
import business.parts.NLZC400001_pktStsUpdateInfoPMsg;
import business.parts.NLZC901001PMsg;
import business.parts.NLZC901001_xxShipInfoListPMsg;
import business.parts.NLZC901001_xxShipInfoListPMsgArray;

import com.canon.cusa.s21.api.NLZ.NLZC400001.NLZC400001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiUtil;

public class NLZC901001 extends S21ApiCommonBase {

    /** Program ID */
    private static final String PROGRAM_ID = NLZC901001.class.getSimpleName();
    

    /** timestamp format (time part) */
    public static final String FORMAT_TIMESTAMP_TIME = "HHmmss";
    
    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NLZC901001() {
        super();
    }

    /**
     * <pre>
     * Inventory Allocation API (List)
     * </pre>
     * @param params      Input parameter list.
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(final List<NLZC901001PMsg> params, final ONBATCH_TYPE onBatchType) {
    	
    	
        for (NLZC901001PMsg param : params) {
            execute(param, onBatchType);
        }

    }
    
    
    public void execute(final NLZC901001PMsg param, final ONBATCH_TYPE onBatchType) {

        // Checking Input value
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        // main
        doProcess(msgMap);
        // end
        msgMap.flush();
    }
    
    
    protected void doProcess(S21ApiMessageMap msgMap) {
    	
    	System.out.println("###### WMSLOG NLZC901001 doProcess START ######");

    	// SystemDate
        String tempSysDate = ZYPDateUtil.getSalesDate().concat(ZYPDateUtil.getCurrentSystemTime(FORMAT_TIMESTAMP_TIME));
        String sysDate = tempSysDate.substring(0,8);

        // param
        NLZC901001PMsg param = (NLZC901001PMsg) msgMap.getPmsg();
        
        String glblCmpyCd = param.glblCmpyCd.getValue();
        String shipNum = param.prtShipNum.getValue();
        
        NLZC901001_xxShipInfoListPMsgArray shipInfoListPMsgArray = param.xxShipInfoList;
        
        // shipListの件数取得
        int paramCnt = shipInfoListPMsgArray.getValidCount();
        
        System.out.println("shipNum  : " + shipNum);
        System.out.println("paramCnt  : " + paramCnt);
        
        // Set param for PKT Line Update API
        NLZC400001PMsg pktLineUpdPMsg = new NLZC400001PMsg();
        
        int pktCnt = 0;
        
        for(int i = 0; i < paramCnt; i++) {
        	
        	NLZC901001_xxShipInfoListPMsg shipInfoList = shipInfoListPMsgArray.no(i);
        
        	// 値をPMsgから取得する
            String soNum =  shipInfoList.soNum.getValue();
            String soSlpNum =  shipInfoList.soSlpNum.getValue();
            String mdseCd = shipInfoList.mdseCd.getValue();
            
            String binLocCd = shipInfoList.binLocCd.getValue();
            
            String rtlWhCd = shipInfoList.rtlWhCd.getValue();
            String invtyOwnrCd = shipInfoList.invtyOwnrCd.getValue();
            String rtlSwhCd = shipInfoList.rtlSwhCd.getValue();
            
            String fromStkStsCd = shipInfoList.fromStkStsCd.getValue();
            BigDecimal shpgQty = shipInfoList.shpgQty.getValue();
            BigDecimal totShpgWt = shipInfoList.totShpgWt.getValue();
            
            // PMsgに値を設定する
            ZYPEZDItemValueSetter.setValue(pktLineUpdPMsg.glblCmpyCd, glblCmpyCd);
            
            // TODO （仮の値）　設定すべき値は？
            ZYPEZDItemValueSetter.setValue(pktLineUpdPMsg.slsDt,sysDate);
            
            NLZC400001_pktStsUpdateInfoPMsg stsUpdateInfo = pktLineUpdPMsg.pktStsUpdateInfo.no(i);
            
            ZYPEZDItemValueSetter.setValue(stsUpdateInfo.soNum, soNum);
            ZYPEZDItemValueSetter.setValue(stsUpdateInfo.soSlpNum, soSlpNum);
            ZYPEZDItemValueSetter.setValue(stsUpdateInfo.dsSoLineStsCd, DS_SO_LINE_STS.PICK_CONFIRMED);
            ZYPEZDItemValueSetter.setValue(stsUpdateInfo.pktStsTs, ZYPDateUtil.getSalesDate().concat(ZYPDateUtil.getCurrentSystemTime(FORMAT_TIMESTAMP_TIME)));
            ZYPEZDItemValueSetter.setValue(stsUpdateInfo.pktStsQty, shpgQty);

            
           	System.out.println("################# WMSLOG START - shipment status update  ################");
           	System.out.println("Cnt : " + i);
           	
           	System.out.println("Shipping Order # : " + stsUpdateInfo.soNum.getValue());
           	System.out.println("Line Number : " + stsUpdateInfo.soSlpNum.getValue());
            
           	System.out.println("STATUS CODE : " + stsUpdateInfo.dsSoLineStsCd.getValue());
        	System.out.println("SALES DATE : "    +stsUpdateInfo.pktStsTs.getValue());
        	System.out.println("QUANTITY : " + stsUpdateInfo.pktStsQty.getValue());

           	System.out.println("################# WMSLOG  END #######################################");
           	
           	pktCnt++;
        
        }
	

	    // Call PKT Line Update API
	    if (pktCnt > 0) {
	
	        NLZC400001 pktApi = new NLZC400001();
	        // リストに件数を追加
	        pktLineUpdPMsg.pktStsUpdateInfo.setValidCount(pktCnt);
	
	        pktApi.execute(pktLineUpdPMsg, ONBATCH_TYPE.ONLINE);
	        
	        if (!chkApiResult(S21ApiUtil.getXxMsgIdList(pktLineUpdPMsg))) {
	        	
	            return;
	        }
	    }
	    
    	System.out.println("###### WMSLOG NLZC901001 doProcess END ######");

	    // SO Serialの更新は対象外とする(今回の対象データでSerial Noがある場合は、機能追加する)
//	    
//        // Set param for SO Serial Update API
//        NLZC401001PMsg soSerUpMsg = new NLZC401001PMsg();
//
//        int soSerUpCnt = 0;
//
//        // PMsgに値を設定する
//        ZYPEZDItemValueSetter.setValue(soSerUpMsg.glblCmpyCd, glblCmpyCd);
//        // TODO （仮の値）　
//        ZYPEZDItemValueSetter.setValue(soSerUpMsg.slsDt, sysDate);
//        
//        
//        // API Msg Setup
//        for(int i = 0; i < paramCnt; i++) {
//
//        	NLZC901001_xxShipInfoListPMsg shipInfoList = shipInfoListPMsgArray.no(i);
//        
//        	// 値をPMsgから取得する
//            String soNum =  shipInfoList.soNum.getValue();
//            String mdseCd = shipInfoList.mdseCd.getValue();
//            
//            String rtlWhCd = shipInfoList.rtlWhCd.getValue();
//            String invtyOwnrCd = shipInfoList.invtyOwnrCd.getValue();
//            String rtlSwhCd = shipInfoList.rtlSwhCd.getValue();
//            
//            String fromStkStsCd = shipInfoList.fromStkStsCd.getValue();
//            BigDecimal shpgQty = shipInfoList.shpgQty.getValue();
//            BigDecimal totShpgWt = shipInfoList.totShpgWt.getValue();
//            
//            // 行番号を設定する
//            int rowNum = i + 1;
//            String LineNumber = "";
//            if (rowNum < 10){
//            	LineNumber = "00" + Integer.toString(rowNum);
//            } else if(paramCnt < 100) {
//            	LineNumber = "0" + Integer.toString(rowNum);
//            }
//            
////            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).xxProcTpCd, NLZC401001Constant.MODE_PICKUP_SERIAL);
////            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).mdseCd, mdseCd);
////            
////            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).serNum, sMsgBLine.serNum_B1);
////            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).soNum, soNum);
////            
////            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).soSlpNum, LineNumber);
////            
////            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxHdrNum, sMsgBLine.trxHdrNum_HT);
////            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxLineNum, LineNumber);
////            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxLineSubNum, "");
////            ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxSrcTpCd, sMsgBLine.trxSrcTpCd_HD);
////            soSerUpCnt++;
//        }
//        
////        if (soSerUpCnt > 0) {
////
////            // Call SO Serial Update API
////            NLZC401001 soSerUpApi = new NLZC401001();
////            soSerUpMsg.serInfo.setValidCount(soSerUpCnt);
////
////            soSerUpApi.execute(soSerUpMsg, ONBATCH_TYPE.ONLINE);
////
////            if (S21ApiUtil.isXxMsgId(soSerUpMsg)) {
////
////                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(soSerUpMsg);
////
////                for (S21ApiMessage msg : msgList) {
////
////                    String msgId = msg.getXxMsgid();
////                    String[] msgPrms = msg.getXxMsgPrmArray();
////
////                    if (ZYPCommonFunc.hasValue(msgId)) {
////
////                            return;
////
////                    }
////                }
////            }
////        }

    }
    
    public static boolean chkApiResult(List<String> apiMsgList) {

        if (!apiMsgList.isEmpty()) {

            for (String msgId : apiMsgList) {

                if (ZYPCommonFunc.hasValue(msgId)) {

//                    cMsg.setMessageInfo(msgId);

                    if (msgId.endsWith("E")) {

                        return false;
                    }
                }
            }
        }

        return true;
    }
	
}
