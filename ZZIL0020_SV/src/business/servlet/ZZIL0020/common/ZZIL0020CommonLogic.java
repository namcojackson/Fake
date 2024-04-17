/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.servlet.ZZIL0020.common;

import parts.common.EZDGUIAttribute;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import business.servlet.ZZIL0020.ZZIL0020BMsg;
import business.servlet.ZZIL0020.constant.ZZIL0020Constant;

public class ZZIL0020CommonLogic implements ZZIL0020Constant {

    /**
     * reverse a background color to a line unit.
     * @param scrnMsg   ZZIL0020BMsg
     */
    public static void setTableColor( ZZIL0020BMsg scrnMsg ) {
        
        S21TableColorController tblColor = new S21TableColorController( screenId, scrnMsg );
        tblColor.setAlternateRowsBG( "A_TBL",  scrnMsg.X );
    }

    /**
     * set Background Color
     * 
     * @param scrnMsg   ZZIL0020BMsg
     * @param itemNm    String
     */
    public static void setBackgroundColor( ZZIL0020BMsg scrnMsg, String itemNm ) {
        
        EZDGUIAttribute att = new EZDGUIAttribute(screenId, itemNm);
        att.setStyleAttribute("background-color", "EEEEFF");
        scrnMsg.addGUIAttribute(att);
    }

}
