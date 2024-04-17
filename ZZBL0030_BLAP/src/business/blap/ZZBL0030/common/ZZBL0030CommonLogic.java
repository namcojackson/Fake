/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   Fujitsu         Yamaguchi       Create          N/A
 *</Pre>
 */
package business.blap.ZZBL0030.common;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSMsgArray;
import business.blap.ZZBL0030.ZZBL0030CMsg;
import business.blap.ZZBL0030.ZZBL0030Query;
import business.blap.ZZBL0030.ZZBL0030SMsg;
import business.blap.ZZBL0030.ZZBL0030_ASMsg;
import business.blap.ZZBL0030.constant.ZZBL0030Constant;


public class ZZBL0030CommonLogic implements ZZBL0030Constant {

    /**
     * Method name: getAot10
     * <dd>The method explanation: get search result exsits
     * @param sMsg
     * @param cMsg
     */
    public static void getAot10(EZDSMsg sMsg, EZDCMsg cMsg) {
        
        ZZBL0030CMsg bizMsg = (ZZBL0030CMsg) cMsg;  
        ZZBL0030SMsg sesMsg = (ZZBL0030SMsg) sMsg;

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        sesMsg.A.clear();
        sesMsg.A.setValidCount(0);
        
        int hitCount = ZZBL0030Query.getInstance().exec(cMsg, sMsg);
        
        int rowCounter = copySMsgToCMsg(sMsg, cMsg, 0);
        bizMsg.xxPageShowFromNum.setValue( 1 );
        bizMsg.xxPageShowToNum.setValue(rowCounter );
        bizMsg.xxPageShowOfNum.setValue( hitCount );
    }

    /**
     * Copy C Msg to S Msg
     * @param cMsg
     * @param sMsg
     */
     public static void copyCMsgtoSMsg(EZDCMsg cMsg, EZDSMsg sMsg) {
            
        ZZBL0030CMsg bizMsg = (ZZBL0030CMsg) cMsg;  
        ZZBL0030SMsg sesMsg = (ZZBL0030SMsg) sMsg;
            
        for (int i=0; i < bizMsg.A.getValidCount(); i++) {
                
                // Those fields are PK keys. 
            String ezReqBusinessName = bizMsg.A.no(i).ezReqBusinessName_A.getValue();
            String ezReqInputDeptCode = bizMsg.A.no(i).ezReqInputDeptCode_A.getValue();
            String ezReqInputUserID = bizMsg.A.no(i).ezReqInputUserID_A.getValue();
            String ezReqInputDate = bizMsg.A.no(i).ezReqInputDate_A.getValue();
            String ezReqInputTime = bizMsg.A.no(i).ezReqInputTime_A.getValue();
            String ezReqUserKeyItem = bizMsg.A.no(i).ezReqUserKeyItem_A.getValue(); 
            String ezReqJobStatus = bizMsg.A.no(i).ezReqJobStatus_A.getValue();
            
            // Search the specific record.
            for (int j=0; j < sesMsg.A.getValidCount(); j++) {
                ZZBL0030_ASMsg currentRow = sesMsg.A.no(j);
                if (currentRow.ezReqBusinessName_A.getValue().equals(ezReqBusinessName) &&
                    currentRow.ezReqInputDeptCode_A.getValue().equals(ezReqInputDeptCode) &&
                    currentRow.ezReqInputUserID_A.getValue().equals(ezReqInputUserID) &&
                    currentRow.ezReqInputDate_A.getValue().equals(ezReqInputDate) &&
                    currentRow.ezReqInputTime_A.getValue().equals(ezReqInputTime) &&
                    currentRow.ezReqUserKeyItem_A.getValue().equals(ezReqUserKeyItem) &&
                    !currentRow.ezReqJobStatus_A.getValue().equals(ezReqJobStatus)) {
                    
                        currentRow.ezReqJobStatus_A.setValue(ezReqJobStatus);
                        currentRow.actvFlg_A.setValue("Y");  // Dirty Flag
                        break;
                }
            }
        }
    }

       /**
        * 
        * @param sMsg
        * @param cMsg
        * @param offset
        */
       public static int copySMsgToCMsg(EZDSMsg sMsg, EZDCMsg cMsg,  int offset) {
        
            ZZBL0030CMsg bizMsg = (ZZBL0030CMsg) cMsg;  
            ZZBL0030SMsg sesMsg = (ZZBL0030SMsg) sMsg;

           int rowCounter = copyPartialArray(sesMsg.A, bizMsg.A, offset, bizMsg.A.length());

           for (int i=0; i < (rowCounter < bizMsg.A.getValidCount()? rowCounter : bizMsg.A.getValidCount()); i++) {
               bizMsg.A.no(i).xxRowNum_A.setValue(++offset);
            
               bizMsg.A.no(i).ezReqJobStatus_AD.no(0).setValue("0");
               bizMsg.A.no(i).xxBatProcTrmNm_AD.no(0).setValue("Not Started");
               bizMsg.A.no(i).ezReqJobStatus_AD.no(1).setValue("1");
               bizMsg.A.no(i).xxBatProcTrmNm_AD.no(1).setValue("In Process");
               bizMsg.A.no(i).ezReqJobStatus_AD.no(2).setValue("2");
               bizMsg.A.no(i).xxBatProcTrmNm_AD.no(2).setValue("Normal End");
               bizMsg.A.no(i).ezReqJobStatus_AD.no(3).setValue("3");
               bizMsg.A.no(i).xxBatProcTrmNm_AD.no(3).setValue("Abnormal End");
               bizMsg.A.no(i).ezReqJobStatus_AD.no(4).setValue("4");
               bizMsg.A.no(i).xxBatProcTrmNm_AD.no(4).setValue("Warning End");

           }
        
           return rowCounter;
       }
       
       /**
        * Copy partial array. It should be in common library.
        * @param sMsg the S msg
        * @param cMsg the C msg
        * @param pageNum the page number
        * @param pageSize the page size
        * @return the int
        */
       private static int copyPartialArray(EZDSMsgArray sMsg, EZDCMsgArray cMsg, int offset, int pageSize) {

           cMsg.clear();

           int nextPageOffset = offset + pageSize;
           int lastRow = nextPageOffset > sMsg.getValidCount() ? sMsg.getValidCount() : nextPageOffset;

           int rowCounter = 0;
           for (int i = offset; i < lastRow; i++) {
               EZDMsg.copy(sMsg.get(i), null, cMsg.get(rowCounter++), null);
           }
           cMsg.setValidCount(rowCounter);

           return rowCounter;
       }
}
