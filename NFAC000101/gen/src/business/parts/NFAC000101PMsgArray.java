// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20091117145036000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFAC000102PMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDPMsgArray;

/**
 * It is NFAC000102 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFAC000101PMsgArray extends EZDPMsgArray {

    // Serial Version UID
    private static final long serialVersionUID = 1L;

    /**
     * get NFAC000102PMsg of the specified element number .
     * @param n Index Number
     * @return  NFAC000102PMsg
     */
    public   NFAC000101PMsg no(int n){
        return ( NFAC000101PMsg)get(n);
    }


    /**
     * The message career type in the array is acquired.
     * @return  NFAC000102PMsg
     */
    public EZDMsg getMyComponent() {
        return new NFAC000101PMsg();
    }
}
