package business.blap.ZZML0030;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZML0030.constant.ZZML0030Constant;
import business.db.ML_STSTMsg;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.mail.MailDefinition;

public class ZZML0030BL06 extends S21BusinessHandler implements ZZML0030Constant  {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("ZZML0030Scrn00_Send".equals(screenAplID)) {
                doProcess_ZZML0030Scrn00_Send((ZZML0030CMsg) cMsg, (ZZML0030SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * send processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0030Scrn00_Send(ZZML0030CMsg cMsg, ZZML0030SMsg sMsg) {

        // get row number
        int rownum = cMsg.xxRowNum.getValueInt();
        
        // check status
        String mlSendSts = sMsg.A.no( rownum ).xxScrItem7Txt_A.getValue();
        if ( ML_STS_SENT.equals( mlSendSts ) ) {

            // check the state of the send button
            String rstFlg = cMsg.A.no( rownum ).xxRsltFlg_A.getValue();            
            if ( rstFlg.length() == 0 ) {
                cMsg.setMessageInfo( "ZZZM9022E" );
                cMsg.A.no( rownum ).xxRsltFlg_A.setValue( PROC_WARN );
                return;
            }
        }

        // data index of EZDSMsg
        int index = rownum + cMsg.xxPageShowFromNum.getValueInt() - 1;

        // key parameter
        BigDecimal mlStsPk = sMsg.A.no( index ).mlStsPk_A.getValue();
        
        // set search parameter
        ML_STSTMsg tMsg = new ML_STSTMsg();
        tMsg.glblCmpyCd.setValue( sMsg.glblCmpyCd.getValue() );
        tMsg.mlStsPk.setValue( mlStsPk );
        // get search result
        ML_STSTMsg mlStsTMsg = (ML_STSTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait( tMsg );
        if (mlStsTMsg == null) {
            cMsg.setMessageInfo( "ZZZM9004E" );
            return;
        }

        // check the update date and time and update time zone
        String upDate = sMsg.A.no( index ).ezUpTime_A.getValue();
        String upZone = sMsg.A.no( index ).ezUpTimeZone_A.getValue();
        if ( !upDate.equals( mlStsTMsg.ezUpTime.getValue() ) || !upZone.equals( mlStsTMsg.ezUpTimeZone.getValue() ) ) {
            cMsg.setMessageInfo( "ZZZM9004E" );
            return;
        }
        
        // status change
        mlStsTMsg.mlSendStsCd.setValue( MailDefinition.ML_STS_WAIT );
        
        // update
        EZDTBLAccessor.update( mlStsTMsg );
        if ( !EZDTBLAccessor.RTNCD_NORMAL.equals( mlStsTMsg.getReturnCode() ) ) {
            cMsg.setMessageInfo( "ZZZM9013E", new String[] { mlStsTMsg.getReturnCode() } );
        }

        // error check
        if ( cMsg.getMessageInfo() == null ) {
            cMsg.setMessageInfo( "ZZZM9003I", new String[] { "send" } );
        }
        // clear the state of the send button
        cMsg.A.no( rownum ).xxRsltFlg_A.clear();

    }

}
