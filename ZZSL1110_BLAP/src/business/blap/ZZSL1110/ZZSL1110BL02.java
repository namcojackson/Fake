/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Q02046
 * Company: Fujitsu Limited
 * Date: Jul 8, 2009
 */
package business.blap.ZZSL1110;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * The Class ZZSL1110BL02.
 */
public class ZZSL1110BL02 extends S21BusinessHandler {
    
    /**
     * The Constant INIT_MIN_ROW_NUM.
     */
    private static final int INIT_MIN_ROW_NUM = 0;
    
    /**
     * The Constant INIT_MAX_ROW_NUM.
     */
    private static final int INIT_MAX_ROW_NUM = 200;
    
    /**
     * The Constant INIT_CUR_PAGE.
     */
    private static final int INIT_CUR_PAGE = 0;
    
	/**
	 * 
	 * @param cMsg
	 * @param sMsg
	 * @see parts.ejbcommon.EZDBusinessHandler#doProcess(parts.common.EZDCMsg, parts.common.EZDSMsg)
	 */
	@Override
	protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
		ZZSL1110CMsg bizMsg = (ZZSL1110CMsg)cMsg;
        String screenAplID = bizMsg.getScreenAplID();
		try {
	        super.preDoProcess(cMsg, sMsg);
            if("ZZSL1110_INIT".equals(screenAplID)) {
                this.setInitialValues(cMsg, sMsg);
            } else if("ZZSL1110Scrn00_Next".equals(screenAplID)) {
                doProcess_ZZSL1110Scrn00_Next(cMsg, sMsg);
            } else if("ZZSL1110Scrn00_Prev".equals(screenAplID)) {
                doProcess_ZZSL1110Scrn00_Prev(cMsg, sMsg);
            } else if("ZZSL1110Scrn00_Refresh".equals(screenAplID)) {
                this.setInitialValues(cMsg, sMsg);
            }
            this.getCompanyListOnCriteria(cMsg);
            
		} finally {
			super.postDoProcess(cMsg, sMsg);
		}
	}
    
    /**
     * Do process_ zzs l1110 scrn00_ refresh.
     * @param cMsg the c msg
     * @param sMsg the s msg
     */
    private void getCompanyListOnCriteria(EZDCMsg cMsg) {
        ZZSL1110CMsg bizMsg = (ZZSL1110CMsg) cMsg;
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        
        int hitCount = ZZSL1110Query.getInstance().getCompanyListOnCriteria(bizMsg);
        if (hitCount == 0) {
            bizMsg.A.setValidCount(0);
//            bizMsg.setMessageInfo("ZZSM0001E", new String[] {"Record is not found" });
        } else if (hitCount >= 200) {
            bizMsg.setMessageInfo("ZZZM9002W", new String[] {"200"});
        }
        return;
    }
    
    /**
     * Sets the initial values.
     * @param cMsg the c msg
     * @param sMsg the s msg
     */
    private void setInitialValues(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZSL1110CMsg bizMsg = (ZZSL1110CMsg)cMsg;
        bizMsg.xxCurPg.setValue(INIT_CUR_PAGE);
        bizMsg.xxRowNum_MN.setValue(INIT_MIN_ROW_NUM);
        bizMsg.xxRowNum_MX.setValue(INIT_MAX_ROW_NUM);
    }
    
    
    /**
     * Do process_ zzs l1110 scrn00_ next.
     * @param cMsg the c msg
     * @param sMsg the s msg
     */
    private void doProcess_ZZSL1110Scrn00_Next(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZSL1110CMsg bizMsg = (ZZSL1110CMsg)cMsg;
        bizMsg.xxCurPg.setValue( bizMsg.xxCurPg.getValueInt() + 1);
        this.setRowNumValues(bizMsg, sMsg);
    }
    
    
    /**
     * Do process_ zzs l1110 scrn00_ prev.
     * @param cMsg the c msg
     * @param sMsg the s msg
     */
    private void doProcess_ZZSL1110Scrn00_Prev(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZSL1110CMsg bizMsg = (ZZSL1110CMsg)cMsg;
        bizMsg.xxCurPg.setValue( bizMsg.xxCurPg.getValueInt() - 1);
        this.setRowNumValues(bizMsg, sMsg);
    }
    
    /**
     * Sets the row num values.
     * @param cMsg the c msg
     * @param sMsg the s msg
     */
    private void setRowNumValues(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZSL1110CMsg bizMsg = (ZZSL1110CMsg)cMsg;
        
        int curPage =  bizMsg.xxCurPg.getValueInt();
        int fetchRecords = 200;
        int latestMinRow = 0;
        int latestMaxRow = 200;
        if (curPage != 0) {
            latestMinRow = (curPage * fetchRecords) + 1;
            latestMaxRow = ( curPage + 1 )* fetchRecords;
        }
        bizMsg.xxRowNum_MN.setValue(latestMinRow);
        bizMsg.xxRowNum_MX.setValue(latestMaxRow);
    }
    
}
