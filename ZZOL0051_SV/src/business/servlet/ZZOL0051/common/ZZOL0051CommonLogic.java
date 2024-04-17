package business.servlet.ZZOL0051.common;

import java.util.Vector;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import business.blap.ZZOL0051.ZZOL0051CMsg;
import business.servlet.ZZOL0051.ZZOL0051BMsg;
import business.servlet.ZZOL0051.constant.ZZOL0051Constant;

public class ZZOL0051CommonLogic implements ZZOL0051Constant {

    /**
     * 画面の「一覧表：A」の行の背景色を、行単位に反転させます。
     * 
     * @param scrnMsg   ZZOL0050BMsg
     */
    public static void setTableColor( ZZOL0051BMsg scrnMsg ) {
        
        S21TableColorController tblColor = new S21TableColorController( screenId, scrnMsg );
        String curttab = scrnMsg.xxDplyTab.getValue();
        if ( TAB_HEADER.equals( curttab ) ) {
            tblColor.setAlternateRowsBG( "A_LeftTBL",  scrnMsg.H );
            tblColor.setAlternateRowsBG( "A_RightTBL", scrnMsg.H );
        } else if (  TAB_BIZAPP.equals( curttab )  ) {
            tblColor.setAlternateRowsBG( "A_LeftTBL",  scrnMsg.B );
            tblColor.setAlternateRowsBG( "A_RightTBL", scrnMsg.B );
        } else if (  TAB_PROCID.equals( curttab )  ) {
            tblColor.setAlternateRowsBG( "A_LeftTBL",  scrnMsg.P );
            tblColor.setAlternateRowsBG( "A_RightTBL", scrnMsg.P );
        }        
    }

    private static void setButtonConfirmMsg( S21CommonHandler handler, String[] btnDef, boolean msgFlg ) {

        if ( msgFlg ) {
            handler.setButtonConfirmMsg(btnDef[1], "ZZM8102I", new String[] { btnDef[2] }, 0);
        } else {
            handler.setButtonConfirmMsg(btnDef[1], null, null, 0);
        }
    }

    /**
     * set screen00 button status
     * @param handler S21CommonHandler
     */
    public static void setButtonScrn00( S21CommonHandler handler ) {

        handler.setButtonProperties( CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null );
        handler.setButtonProperties( CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 1, null );
        handler.setButtonProperties( CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null );
        handler.setButtonProperties( CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null );
        handler.setButtonProperties( CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null );
        handler.setButtonProperties( CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null );
        handler.setButtonProperties( CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null );
        handler.setButtonProperties( CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 0, null );
        handler.setButtonProperties( CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 0, null );
        handler.setButtonProperties( CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null );
        
        setButtonConfirmMsg(handler, CMN_BTN7, true);
        setButtonConfirmMsg(handler, CMN_BTN10, true);
    }
    
    /**
     * set chip message
     * 
     * @param scrnMsg   ZZOL0051BMsg
     */
    public static void setNameForMessage( ZZOL0051BMsg scrnMsg ) {

        scrnMsg.glblCmpyCd.setNameForMessage(       "Global Company CD");
        scrnMsg.upldCsvId.setNameForMessage(        "Upload CSV ID");
        scrnMsg.upldCsvNm.setNameForMessage(        "Upload CSV Name");
        scrnMsg.upldCsvFileId.setNameForMessage(    "File ID");
        scrnMsg.upldCsvTempTblId.setNameForMessage( "Table ID");
        scrnMsg.ezReqBusinessID.setNameForMessage(  "Request Job Net ID");
    }

    /**
     * set screen00 init
     * @param handler S21CommonHandler
     * @param scrnMsg ZZOL0051BMsg
     * @param bizMsg ZZOL0051CMsg
     */
    public static void setScreenInit( S21CommonHandler handler, ZZOL0051BMsg scrnMsg, ZZOL0051CMsg bizMsg ) {

        // required item
        scrnMsg.glblCmpyCd.setIndispensable( true );
        scrnMsg.upldCsvId.setIndispensable( true );
        scrnMsg.upldCsvNm.setIndispensable( true );
        scrnMsg.upldCsvFileId.setIndispensable( true );
        scrnMsg.upldCsvTempTblId.setIndispensable( true );
        scrnMsg.ezReqBusinessID.setIndispensable( true );

        
        // glblCmpyCd & upldCsvId item protected
        scrnMsg.glblCmpyCd.setInputProtected( true );
        if ( scrnMsg.upldCsvId.isClear() ) {
            scrnMsg.setFocusItem( scrnMsg.upldCsvId );
        } else {
            scrnMsg.upldCsvId.setInputProtected( true );
            scrnMsg.setFocusItem( scrnMsg.upldCsvNm );
        }
        
        // CSV Header table item protected
        for ( int idx = 0; idx < scrnMsg.H.getValidCount(); idx++ ) {
            scrnMsg.H.no(idx).upldCsvHdrNm_H.setInputProtected( true );
        }

        // Process ID table item protected
        for ( int idx = 0; idx < scrnMsg.P.getValidCount(); idx++ ) {
            scrnMsg.P.no(idx).upldCsvRstProcNm_P.setInputProtected( true );
        }

        // Display TAB = Detail
        if ( scrnMsg.xxDplyTab.getValue().length() == 0 ) {
            scrnMsg.xxDplyTab.setValue( TAB_HEADER );
        }
        // button control
        if ( TAB_HEADER.equals(scrnMsg.xxDplyTab.getValue()) ) {
            handler.setButtonEnabled(CMN_SEARCH, true);
        } else {
            handler.setButtonEnabled(CMN_SEARCH, false);
        }
        
        handler.setButtonEnabled(CMN_BTN2[0], scrnMsg.H.getValidCount() > 0);
        ZZOL0051CommonLogic.setTableColor(scrnMsg);
    }

    /**
     * set addCheckItem
     * @param scrnMsg ZZOL0051BMsg
     */
    public static void setAddCheckItem( ZZOL0051BMsg scrnMsg ) {
        
        String val = null;
        String tab = null;
        Vector<String> vc = new Vector<String>();

        // required check
        scrnMsg.addCheckItem( scrnMsg.glblCmpyCd );
        scrnMsg.addCheckItem( scrnMsg.upldCsvId );
        scrnMsg.addCheckItem( scrnMsg.upldCsvNm );
        scrnMsg.addCheckItem( scrnMsg.upldCsvFileId );
        scrnMsg.addCheckItem( scrnMsg.upldCsvTempTblId );
        scrnMsg.addCheckItem( scrnMsg.ezReqBusinessID );

        scrnMsg.H.setCheckParam(new String[] { "upldCsvHdrDefNm_H" }, 3);
        scrnMsg.addCheckItem( scrnMsg.B );

        scrnMsg.B.setCheckParam(new String[] { "upldCsvRstBizAppId_B" }, 3);
        scrnMsg.addCheckItem( scrnMsg.B );

        scrnMsg.P.setCheckParam(new String[] { "upldCsvRstProcNm_P" }, 3);
        scrnMsg.addCheckItem( scrnMsg.P );

        if ( scrnMsg.H.getValidCount() == 0 ) {
            scrnMsg.setMessageInfo("ZZZM9025E", new String[] {"CSV Header"});
        }

        // blank & duplication check for Application ID
        vc.clear();
        for ( int idx = 0; idx < scrnMsg.B.getValidCount(); idx++ ) {
            val = scrnMsg.B.no(idx).upldCsvRstBizAppId_B.getValue();
            
            // blank check
            if ( val.length() == 0 ) {
                scrnMsg.B.no(idx).upldCsvRstBizAppId_B.setErrorInfo(1, "ZZZM9007E", new String[]{"Business Application ID"});
                if ( tab == null ) {
                    tab = TAB_BIZAPP;
                }
                continue;
            }
            
            // duplication check
            if ( !vc.contains(val) ) {
                vc.add(val);
            } else {
                scrnMsg.B.no(idx).upldCsvRstBizAppId_B.setErrorInfo( 1, "ZYPM0119E", new String[] { "Business Application ID" } );                
                if ( tab == null ) {
                    tab = TAB_BIZAPP;
                }
            }

        }
        
        // blank & duplication check for Process ID
        vc.clear();
        for ( int idx = 0; idx < scrnMsg.P.getValidCount(); idx++ ) {
            val = scrnMsg.P.no(idx).upldCsvRstProcNm_P.getValue();

            // blank check
            if ( val.length() == 0 ) {
                scrnMsg.P.no(idx).upldCsvRstProcNm_P.setErrorInfo(1, "ZZZM9007E", new String[]{"Business Process ID"});
                if ( tab == null ) {
                    tab = TAB_PROCID;
                }
                continue;
            }

            // duplication check
            if ( !vc.contains(val) ) {
                vc.add(val);
            } else {
                scrnMsg.P.no(idx).upldCsvRstProcNm_P.setErrorInfo( 1, "ZYPM0119E", new String[] { "Business Process ID" } );                
                if ( tab == null ) {
                    tab = TAB_PROCID;
                }
            }
        }

        // TAB position setting
        if ( tab != null ) {

            String curttab = scrnMsg.xxDplyTab.getValue();
            if ( !curttab.equals(tab) ) {
                scrnMsg.xxDplyTab.setValue(tab);
            }
        }
    }

}
