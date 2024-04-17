/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 * 2020/03/03   Fujitsu         K.takahama      Update          QC#56127
 *</pre>
 */
package business.servlet.ZZML0030.common;

import parts.common.EZDGUIAttribute;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import business.servlet.ZZML0030.ZZML0030BMsg;
import business.servlet.ZZML0030.constant.ZZML0030Constant;

public class ZZML0030CommonLogic implements ZZML0030Constant  {

    /**
     * 画面の「一覧表：A」の行の背景色を、行単位に反転させます。
     * 
     * @param scrnMsg   ZZML0030BMsg
     */
    public static void setTableColor( ZZML0030BMsg scrnMsg ) {
        
        S21TableColorController tblColor = new S21TableColorController( screenId, scrnMsg );
        tblColor.setAlternateRowsBG( "A_LeftTBL",  scrnMsg.A );
        tblColor.setAlternateRowsBG( "A_RightTBL", scrnMsg.A );
    }

    /**
     * set chip message
     * 
     * @param scrnMsg   ZZML0030BMsg
     */
    public static void setNameForMessage( ZZML0030BMsg scrnMsg ) {

        scrnMsg.glblCmpyCd.setNameForMessage(    "Global Company CD");
        scrnMsg.mlSendStsCd_H.setNameForMessage( "Status");
        scrnMsg.mlSubjTxt_H.setNameForMessage(   "Subject");
        scrnMsg.mlUsrAddr_H.setNameForMessage(   "Mail Address From");
        // 03/03/2020 Mod QC#56127 Start
        scrnMsg.mlAddrTpCd_T.setNameForMessage(  "Mail Address Type");
        scrnMsg.mlUsrAddr_T.setNameForMessage(   "Mail Address To");
        // 03/03/2020 Mod QC#56127 End
        scrnMsg.xxFromDt_H.setNameForMessage(    "Status Update Date(From)");
        scrnMsg.xxToDt_H.setNameForMessage(      "Status Update Date(To)");
        scrnMsg.xxHrs_F.setNameForMessage(       "Status Update Time(From)");
        scrnMsg.xxHrs_T.setNameForMessage(       "Status Update Time(To)");
    }

    /**
     * set Background Color
     * 
     * @param scrnMsg   ZZML0030BMsg
     * @param itemNm    String
     */
    public static void setBackgroundColor( ZZML0030BMsg scrnMsg, String itemNm ) {
        
        EZDGUIAttribute att = new EZDGUIAttribute("ZZML0030Scrn01", itemNm);
        att.setStyleAttribute("background-color", "FBFBFB");
        scrnMsg.addGUIAttribute(att);
    }

}
