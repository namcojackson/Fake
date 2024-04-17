/*
 * Copyright (c) 2008 Canon USA Inc. All rights reserved.
 * Original Author: ${user}
 * Company: Fujitsu Limited
 * Date: ${date}
 */

package business.servlet.ZZSL0000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * @author $Author: Arti Kumari $ Arti Kumari
 * @version $Revision: 1.3 $ $Date: 2008/12/23 16:27:35 $
 */
public class ZZSL0000_XXXL0000 extends S21CommonHandler {

    /**
     * checkInput method checks the request data.
     * @param ctx Application Context
     * @param bMsg Input from the screen value of the object
     * @return void
     */
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZSL0000BMsg scrnMsg = (ZZSL0000BMsg)bMsg;

    }

    /**
     * setRequestData is used to generate and control parts of the
     * conversation to call the server for a specific information.
     * @param ctx Application context
     * @param bMsg Input from the screen value of the object
     * @return EZDCMsg EZDCMsg class object
     */
    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZSL0000BMsg scrnMsg = (ZZSL0000BMsg)bMsg;

        // ZZSL0000CMsg bizMsg = new ZZSL0000CMsg();
        // bizMsg.setBusinessID( "ZZSL0000" );
        // bizMsg.setFunctionCode( "10" );
        // EZDMsg.copy( scrnMsg, null, bizMsg, null );

        // return bizMsg;

        return null;
    }

    /**
     * doProcess method process the requests.
     * @param ctx Application Context
     * @param bMsg Input from the screen value of the object
     * @param cMsg Business Info Message
     * @return void
     */
    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // ZZSL0000BMsg scrnMsg = (ZZSL0000BMsg)bMsg;
        // ZZSL0000CMsg bizMsg = (ZZSL0000CMsg)cMsg;

        // EZDMsg.copy( bizMsg, null, scrnMsg, null );

    }

}
