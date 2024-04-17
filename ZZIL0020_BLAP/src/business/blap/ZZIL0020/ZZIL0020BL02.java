/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/27/2009   Fujitsu         T.Kawamura      Create          N/A
 *</pre>
 */
package business.blap.ZZIL0020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDFMsg;
import parts.common.EZDItemAttribute;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSMsg;
import parts.common.EZDSchemaInfo;
import business.blap.ZZIL0020.constant.ZZIL0020Constant;
import business.file.ZZIL0020F00FMsg;
import business.file.ZZIL0020F01FMsg;
import business.file.ZZIL0020F02FMsg;
import business.file.ZZIL0020F03FMsg;
import business.file.ZZIL0020F04FMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZIL0020BL02 extends S21BusinessHandler implements ZZIL0020Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("ZZIL0020_INIT".equals(screenAplID)) {
                doProcess_ZZIL0020_INIT((ZZIL0020CMsg) cMsg);

            } else if ("ZZIL0020Scrn00_Download".equals(screenAplID)) {
                doProcess_ZZIL0020Scrn00_Download((ZZIL0020CMsg) cMsg, (ZZIL0020SMsg) sMsg);

            } else if ("ZZIL0020Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZIL0020Scrn00_PageNext((ZZIL0020CMsg) cMsg, (ZZIL0020SMsg) sMsg);

            } else if ("ZZIL0020Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZIL0020Scrn00_PagePrev((ZZIL0020CMsg) cMsg, (ZZIL0020SMsg) sMsg);

            } else if ("ZZIL0020Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_ZZIL0020Scrn00_CMN_Clear((ZZIL0020CMsg) cMsg, (ZZIL0020SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * initialization processing
     * @param cMsg EZDCMsg
     */
    private void doProcess_ZZIL0020_INIT(ZZIL0020CMsg cMsg) {

        TBL[] tbl = TBL.values();
        for (int i = 0; i < cMsg.xxTblNm_C.length(); i++) {
            cMsg.xxTblNm_C.no(i).setValue( tbl[i].toString() );
            cMsg.xxTblNm_D.no(i).setValue( tbl[i].toString() );
        }
    }

    /**
     * download processing
     * @param cMsg EZDCMsg
     * @param cMsg EZDSMsg
     */
    private void doProcess_ZZIL0020Scrn00_Download(ZZIL0020CMsg cMsg, ZZIL0020SMsg sMsg) {

        EZDFMsg fMsg = null;
        EZDMsgArray array = null;
        
        // EZDFMsg instance generation, get EZDTMsgArray instance
        String tblNm = cMsg.xxTblNm.getValue();
        TBL selTbl = TBL.valueOf( tblNm );
        
        switch( selTbl ) {
            case INTERFACE_SETTING :
                array     = sMsg.A;
                fMsg      = new ZZIL0020F00FMsg();
                break;
            case INTERFACE_SENDER :
                array     = sMsg.B;
                fMsg      = new ZZIL0020F01FMsg();
                break;
            case INTERFACE_RECEIVER :
                array     = sMsg.C;
                fMsg      = new ZZIL0020F02FMsg();
                break;
            case INTERFACE_MQCONFIG :
                array     = sMsg.D;
                fMsg      = new ZZIL0020F03FMsg();
                break;
            case INTERFACE_MQCONFIG_JNDI :
                array     = sMsg.E;
                fMsg      = new ZZIL0020F04FMsg();
                break;
        }
        
        // get csv data
        S21SsmEZDResult ssmResult = ZZIL0020Query.getInstance().getCsvData(cMsg, array);
        if ( !ssmResult.isCodeNormal() && !ssmResult.isCodeNotFound() ) {
            cMsg.setMessageInfo("ZZM9501E");
            return;
        }
        
        // set full path storing a CSV file
        cMsg.xxFileData.setTempFilePath( 
                null, 
                ZYPCSVOutFile.createCSVOutFileNm( cMsg.xxTblNm.getValue() ), 
                ".csv" 
            );

        // ZYPCSVOutFile create
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile( cMsg.xxFileData.getTempFilePath(), fMsg );

        // write in a header line at a CSV file
        csvOutFile.writeHeader( setCsvHeader(cMsg, fMsg) );
        
        // write in the data of one record at a CSV file
        String val = null;
        for( int i = 0 ; i < array.getValidCount(); i++ ) {
            EZDMsg.copy( array.get( i ), null, fMsg, null );
            
            // password decode
            if (selTbl == TBL.INTERFACE_RECEIVER) {
                val = ((ZZIL0020F02FMsg)fMsg).ezPassword_C.getValue();
                val = Integutil.decodeString(val);
                ((ZZIL0020F02FMsg)fMsg).ezPassword_C.setValue(val);
            } else if (selTbl == TBL.INTERFACE_RECEIVER) {
                val = ((ZZIL0020F03FMsg)fMsg).queueConnectPassword_D.getValue();
                val = Integutil.decodeString(val);
                ((ZZIL0020F03FMsg)fMsg).queueConnectPassword_D.setValue(val);
            }
            
            csvOutFile.write();
        }

        // close a file stream
        csvOutFile.close();

        array.clear();
        array.setValidCount( 0 );
    }
    
    /**
     * next page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0020Scrn00_PageNext(ZZIL0020CMsg cMsg, ZZIL0020SMsg sMsg) {

        // get start row number of page
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        //int prevPagenationFrom = pagenationFrom - cMsg.A.length();

        // set next page data
        setPagenation(cMsg, sMsg, pagenationFrom);
    }
    
    /**
     * previous page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0020Scrn00_PagePrev(ZZIL0020CMsg cMsg, ZZIL0020SMsg sMsg) {

        // get start row number of page
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        //int nextPagenationFrom = pagenationFrom + cMsg.A.length();

        // set prev page data
        setPagenation(cMsg, sMsg, pagenationFrom);
    }

    /**
     * clear processing
     * @param cMsg EZDCMsg
     * @param cMsg EZDSMsg
     */
    private void doProcess_ZZIL0020Scrn00_CMN_Clear(ZZIL0020CMsg cMsg, ZZIL0020SMsg sMsg) {

        cMsg.clear();
        cMsg.X.clear();
        cMsg.X.setValidCount(0);
        
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.B.clear();
        sMsg.B.setValidCount(0);
        sMsg.C.clear();
        sMsg.C.setValidCount(0);
        sMsg.D.clear();
        sMsg.D.setValidCount(0);
        sMsg.E.clear();
        sMsg.E.setValidCount(0);
        sMsg.X.clear();
        sMsg.X.setValidCount(0);
        
        doProcess_ZZIL0020_INIT(cMsg);
    }

    /**
     * csv header setting
     * @param cMsg EZDCMsg
     * @param fMsg EZDFMsg
     */
    private String[] setCsvHeader(ZZIL0020CMsg cMsg, EZDFMsg fMsg) {
        
        if ( TBL.valueOf( cMsg.xxTblNm.getValue() ) == TBL.INTERFACE_RECEIVER ) {
            return HEAD_INTERFACE_RECEIVER;
        }
        
        List<String> hdList = Collections.synchronizedList(new ArrayList<String>());
        
        // get schema infomation
        EZDSchemaInfo schema = fMsg.getSchema();
        Iterator it = schema.keysIterator();

        // get DB column name
        while (it.hasNext()) {
            String itemName = (String) it.next();
            EZDItemAttribute itemAtt = schema.getAttr(itemName);
            hdList.add(itemAtt.getMessage());
        }
        
        return hdList.toArray(new String[0]);
    }
    
    /**
     * set pagenation
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @param pagenationFrom  Page row number
     */
    private void setPagenation(ZZIL0020CMsg cMsg, ZZIL0020SMsg sMsg, int pagenationFrom) {

        // copy data from SMsg onto CMsg
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.X.length(); i++) {
            if (i >= sMsg.X.getValidCount()) {
                break;
            }
            EZDMsg.copy(sMsg.X.no(i), null, cMsg.X.no(i - pagenationFrom), null);
        }
        cMsg.X.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.X.getValidCount());
    }

}
