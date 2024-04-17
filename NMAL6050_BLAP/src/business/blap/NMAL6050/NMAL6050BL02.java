package business.blap.NMAL6050;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class NMAL6050BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6050_INIT".equals(screenAplID)) {
                doProcess_NMAL6050_INIT(cMsg, sMsg);

            } else if ("NMAL6050Scrn00_Search_ConditionCode".equals(screenAplID)) {
                doProcess_NMAL6050Scrn00_Search_ConditionCode(cMsg, sMsg);

            } else if ("NMAL6050Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL6050Scrn00_PageNext(cMsg, sMsg);

            } else if ("NMAL6050Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL6050Scrn00_PagePrev(cMsg, sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NMAL6050_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {

        // delete 03/16/2010 S.Sugino
        // When the parameter is set. 20091007 T.Tanaka
//        NMAL6050CMsg bizMsg = (NMAL6050CMsg) cMsg;
//        if(ZYPCommonFunc.hasValue(bizMsg.xxCondCd) || ZYPCommonFunc.hasValue(bizMsg.xxCondNm)) {
            search(cMsg, sMsg);
//        }
    }

    /**
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NMAL6050Scrn00_Search_ConditionCode(EZDCMsg cMsg, EZDSMsg sMsg) {

        search(cMsg, sMsg);

    }

    private void search(EZDCMsg cMsg, EZDSMsg sMsg) {

        NMAL6050CMsg bizMsg = (NMAL6050CMsg) cMsg;
        NMAL6050SMsg asMsg = (NMAL6050SMsg) sMsg;

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        asMsg.A.clear();
        asMsg.A.setValidCount(0);

        S21SsmEZDResult ssmResult = NMAL6050Query.getInstance().getNMAL6050( bizMsg, asMsg );
        
        if( ssmResult.isCodeNormal() ) {

    		int queryResCnt = ssmResult.getQueryResultCount();
    		if( queryResCnt > asMsg.A.length() ) {
                //NZZM0001W=0,There are too many search results, there is data that cannot be displayed.
    			bizMsg.setMessageInfo( "NZZM0001W" );
    			queryResCnt = asMsg.A.length();
    		}
    		
            //Copy from SMsg to cMsg
            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(asMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);
            // Set page number
            bizMsg.xxPageShowFromNum_10.setValue(1);
            bizMsg.xxPageShowToNum_10.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum_10.setValue(queryResCnt);
            //ZZM8100I=0,Process ended normally.
            bizMsg.setMessageInfo("ZZM8100I");
        
        } else {
            bizMsg.xxPageShowFromNum_10.clear();
            bizMsg.xxPageShowToNum_10.clear();
            bizMsg.xxPageShowOfNum_10.clear();
            //NZZM0000E=0,No search results found.
        	bizMsg.setMessageInfo("NZZM0000E");
        	return;
        }

    }
    /**
     * <pre>
     * Page Next Event
     * </pre>
     * @param cMsg Business Message
     * @param sMsg Business Message
     */
    private void doProcess_NMAL6050Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {
    	
        NMAL6050CMsg bizMsg = (NMAL6050CMsg) cMsg;
        NMAL6050SMsg asMsg = (NMAL6050SMsg) sMsg;
        
        // copy data from SMsg onto CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum_10.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < asMsg.A.getValidCount()) {
                EZDMsg.copy(asMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to paging items
        bizMsg.xxPageShowFromNum_10.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum_10.setValue(pagenationFrom + bizMsg.A.getValidCount());
    }

    /**
     * <pre>
     * Page Preview Event
     * </pre>
     * @param cMsg Business Message
     * @param sMsg Business Message
     */
    private void doProcess_NMAL6050Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
    	
        NMAL6050CMsg bizMsg = (NMAL6050CMsg) cMsg;
        NMAL6050SMsg asMsg = (NMAL6050SMsg) sMsg;
        
        // copy data from SMsg to CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum_10.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < asMsg.A.getValidCount()) {
                EZDMsg.copy(asMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to paging items
        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum_10.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum_10.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);
    }

}
