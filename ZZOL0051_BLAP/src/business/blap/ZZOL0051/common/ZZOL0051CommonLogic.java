package business.blap.ZZOL0051.common;

import parts.common.EZDFMsg;
import business.blap.ZZOL0051.constant.ZZOL0051Constant;

public class ZZOL0051CommonLogic implements ZZOL0051Constant{

    /**
     * make instance of the EZDFMsg for the cause in file ID
     * @param fMsgName File ID
     * @return EZDFMsg
     */
    public static EZDFMsg getEZDFMsg(String fMsgName) {
        
        try {
            return (EZDFMsg) Class.forName( EZDFMSG_FILE.replace(REPLACE_STR, fMsgName) ).newInstance();
        } catch (ClassNotFoundException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get Class name
     * @param pkgNm   Packege name
     * @param msgName EZDMsg name
     * @return
     */
    public static String getEZDClsNm(String pkgNm, String msgName) {

        try {
            Class cls = Class.forName( pkgNm.replace(REPLACE_STR, msgName) );
            return cls.getName();
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
    
}
