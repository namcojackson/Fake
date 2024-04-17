/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   Fujitsu         Yamaguchi       Create          N/A
 *</Pre>
 */
package business.blap.ZZBL0020.common;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.ZZBL0020.ZZBL0020CMsg;
import business.blap.ZZBL0020.ZZBL0020Query;
import business.blap.ZZBL0020.ZZBL0020SMsg;
import business.blap.ZZBL0020.ZZBL0020_ASMsg;
import business.blap.ZZBL0020.constant.ZZBL0020Constant;


public class ZZBL0020CommonLogic implements ZZBL0020Constant {

    /**
     * Method name: getAom10
     * <dd>The method explanation: get search result exsits
     * @param sMsg
     * @param cMsg
     */
    public static void getAom10(EZDSMsg sMsg, EZDCMsg cMsg) {
        
        ZZBL0020CMsg bizMsg = (ZZBL0020CMsg) cMsg;  
        ZZBL0020SMsg sesMsg = (ZZBL0020SMsg) sMsg;

        sesMsg.A.clear();
        sesMsg.A.setValidCount(0);
        try {

            // Retrieve records from DB
            int hitCount = ZZBL0020Query.getInstance().exec(cMsg, sMsg);
            
            // Convert a code to its name on S Msg
            if (hitCount > 0 ) {
                for(int i=0; i < hitCount; i++) {
                    convertCodeToName(cMsg, sesMsg.A.no(i));
                }
            }
            
            // Copy records on S Msg to C Msg at most one page's records ( 40 ) 
            int rowCounter = ZZBL0020CommonLogic.copySMsgToCMsg(sMsg, cMsg, 0);

            bizMsg.xxPageShowFromNum.setValue( 1 );
            bizMsg.xxPageShowToNum.setValue(rowCounter );
            bizMsg.xxPageShowOfNum.setValue( hitCount );
                
        } catch (Exception e) {
               EZDDebugOutput.println(1,e.getMessage(), null);
        }
    }
    
    /**
     * Copy S Msg records w/o deletions to B Msg.
     * @param sMsg
     * @param cMsg
     * @param offset 
    */
    public static int copySMsgToCMsg(EZDSMsg sMsg, EZDCMsg cMsg, int offsetTop) {
            
        ZZBL0020CMsg bizMsg = (ZZBL0020CMsg) cMsg;  
        ZZBL0020SMsg sesMsg = (ZZBL0020SMsg) sMsg;

        // Clear C Msg array.
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        
        int pageSize = bizMsg.A.length();
        
        // Find out real offset number. Skip all deleted rows before offset
        int offset = 0;
        for(int i=0; i < sesMsg.A.getValidCount(); i++) {
            if (!sesMsg.A.no(i).actvFlg_A.getValue().equals(DELETED)) {
                if(offset == offsetTop) { 
                    offset = i;
                    break;
                } 
                offset++;
            }
        }

        // Copy all non-deleted records from offset to Biz Meg.
        // offset variable is an index of S Msg. ( b/w offset and (offset + 40 or last record of S Msg )) 
        // rowCounter variable is an actual index of C Msg. ( b/w 0 and 40 )
        int rowCounter = 0;
        while (rowCounter < pageSize && offset < sesMsg.A.getValidCount()) {
            if (!sesMsg.A.no(offset).actvFlg_A.getValue().equals(DELETED)) {
                EZDMsg.copy(sesMsg.A.get(offset), null, bizMsg.A.get(rowCounter), null);
                bizMsg.A.no(rowCounter).xxRowNum_A.setValue(rowCounter+offsetTop+1);
                rowCounter++;
            }
            offset++;
        }
        
        bizMsg.A.setValidCount(rowCounter);
        return rowCounter;
    }
        
        
    /**
     *  Set a deletion mark on S MSG from B Msg
     * @param sMsg
     * @param cMsg
     * @param offset
     */
     public static void copyCMsgToSMsg(EZDCMsg cMsg, EZDSMsg sMsg) {
                
        ZZBL0020CMsg bizMsg = (ZZBL0020CMsg) cMsg;  
        ZZBL0020SMsg sesMsg = (ZZBL0020SMsg) sMsg;
        
        for(int i=0; i < bizMsg.A.getValidCount() ; i++ ){
            String ezReqBusinessID = bizMsg.A.no(i).ezReqBusinessID_A.getValue();  // This is a PK.
            // Search this record inside S Msg. 
            for(int j=0; j < sesMsg.A.getValidCount(); j++) {
                ZZBL0020_ASMsg currentRow = sesMsg.A.no(j);
                if (currentRow.ezReqBusinessID_A.getValue().equals(ezReqBusinessID)) {
                    if (bizMsg.A.no(i).xxYesNoCd_A.getValue().equals(DELETED)) {
                         currentRow.xxYesNoCd_A.setValue(bizMsg.A.no(i).xxYesNoCd_A.getValue());
                    } else {
                        currentRow.xxYesNoCd_A.setValue(bizMsg.A.no(i).xxYesNoCd_A.getValue());
                    }
                    break;
                }
            }
        }
     }
         
    /**
     * This function counts a number of valid records on screen.
     * It means that it skips to count the deleted reows.
     * @param sMsg
     * @return
     */
    public static int getCurrentRowNumber(EZDSMsg sMsg) {
    
        ZZBL0020SMsg sesMsg = (ZZBL0020SMsg) sMsg;
        
        int hitCount =0;
        for (int i=0; i < sesMsg.A.getValidCount(); i++) {
            if (!sesMsg.A.no(i).actvFlg_A.getValue().equals(DELETED)) {
                hitCount++;
            }
        }
        
        return hitCount;
    }
    /**
     * Convert Job Stop Flag -> "Yes", "No"
     *         Job Control Flag ->  "Abort", "Continue"
     * @param cMsg
     * @param sMsgRec
     */
    private static void convertCodeToName(EZDCMsg cMsg, ZZBL0020_ASMsg sMsgRec) {
        
        ZZBL0020CMsg bizMsg = (ZZBL0020CMsg) cMsg;  
        
        for(int j=0; j < bizMsg.ezReqJobStopFlag_P2.length(); j++) {
            if (sMsgRec.ezReqJobStopFlag_A.getValue().equals(bizMsg.ezReqJobStopFlag_P2.no(j).getValue())) {
                sMsgRec.xxJobBlockFlgNm_A1.setValue(bizMsg.xxJobBlockFlgNm_P2.no(j).getValue());
                break;
            }
        }
        
        for(int j=0; j < bizMsg.ezReqJobErrorCtlFlag_P3.length(); j++) {
            if (sMsgRec.ezReqJobErrorCtlFlag_A.getValue().equals(bizMsg.ezReqJobErrorCtlFlag_P3.no(j).getValue())) {
                sMsgRec.xxJobErrCtrlFlgNm_A2.setValue(bizMsg.xxJobErrCtrlFlgNm_P3.no(j).getValue());
                break;
            }
        }
    }
}
