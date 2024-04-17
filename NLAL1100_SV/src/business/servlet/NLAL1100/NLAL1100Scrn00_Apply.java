/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL1100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL1100.NLAL1100CMsg;
import business.servlet.NLAL1100.common.NLAL1100CommonLogic;
import business.servlet.NLAL1100.constant.NLAL1100Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/28   CITS            R.Shimamoto     Update          QC#18669
 * 2019/05/09   Fujitsu         T.Ogura         Update          QC#50027
 *</pre>
 */
public class NLAL1100Scrn00_Apply extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    	NLAL1100BMsg scrnMsg = (NLAL1100BMsg)  bMsg;

    	scrnMsg.addCheckItem(scrnMsg.rtrnTrkNtfyGrpCd_G);
    	scrnMsg.addCheckItem(scrnMsg.carrCd_G);
    	scrnMsg.addCheckItem(scrnMsg.schdPickUpDt_G);
        // START 2019/05/09 T.Ogura [QC#50027,ADD]
        scrnMsg.addCheckItem(scrnMsg.rcvTsDplyTxt_G);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDtTxt_G);
        // END   2019/05/09 T.Ogura [QC#50027,ADD]
    	scrnMsg.addCheckItem(scrnMsg.inspCpltDt_G);
    	scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_G);
    	scrnMsg.addCheckItem(scrnMsg.actlPickUpDt_G);
    	scrnMsg.addCheckItem(scrnMsg.carrRsnCd_G);
    	scrnMsg.addCheckItem(scrnMsg.rtrnTrkStsCd_G);
    	scrnMsg.putErrorScreen();

    	if (NLAL1100CommonLogic.isApplyAllBlank(scrnMsg)) {
    		// All blank
    		scrnMsg.rtrnTrkNtfyGrpCd_G.setErrorInfo(1, NLAL1100Constant.NMAM8119E);
    		scrnMsg.carrCd_G.setErrorInfo(1, NLAL1100Constant.NMAM8119E);
    		scrnMsg.schdPickUpDt_G.setErrorInfo(1, NLAL1100Constant.NMAM8119E);
            // START 2019/05/09 T.Ogura [QC#50027,ADD]
            scrnMsg.rcvTsDplyTxt_G.setErrorInfo(1, NLAL1100Constant.NMAM8119E);
            scrnMsg.rqstRcvDtTxt_G.setErrorInfo(1, NLAL1100Constant.NMAM8119E);
            // END   2019/05/09 T.Ogura [QC#50027,ADD]
    		scrnMsg.inspCpltDt_G.setErrorInfo(1, NLAL1100Constant.NMAM8119E);
    		scrnMsg.shpgSvcLvlCd_G.setErrorInfo(1, NLAL1100Constant.NMAM8119E);
    		scrnMsg.actlPickUpDt_G.setErrorInfo(1, NLAL1100Constant.NMAM8119E);
    		scrnMsg.carrRsnCd_G.setErrorInfo(1, NLAL1100Constant.NMAM8119E);
    		scrnMsg.rtrnTrkStsCd_G.setErrorInfo(1, NLAL1100Constant.NMAM8119E);

    		scrnMsg.addCheckItem(scrnMsg.rtrnTrkNtfyGrpCd_G);
        	scrnMsg.addCheckItem(scrnMsg.carrCd_G);
        	scrnMsg.addCheckItem(scrnMsg.schdPickUpDt_G);
            // START 2019/05/09 T.Ogura [QC#50027,ADD]
            scrnMsg.addCheckItem(scrnMsg.rcvTsDplyTxt_G);
            scrnMsg.addCheckItem(scrnMsg.rqstRcvDtTxt_G);
            // END   2019/05/09 T.Ogura [QC#50027,ADD]
        	scrnMsg.addCheckItem(scrnMsg.inspCpltDt_G);
        	scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_G);
        	scrnMsg.addCheckItem(scrnMsg.actlPickUpDt_G);
        	scrnMsg.addCheckItem(scrnMsg.carrRsnCd_G);
        	scrnMsg.addCheckItem(scrnMsg.rtrnTrkStsCd_G);

        	scrnMsg.putErrorScreen();
    	}

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

    	NLAL1100BMsg scrnMsg = (NLAL1100BMsg) bMsg;
        NLAL1100CMsg bizMsg = new NLAL1100CMsg();
        bizMsg.setBusinessID(NLAL1100Constant.BIZ_APP_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

    	NLAL1100BMsg scrnMsg = (NLAL1100BMsg) bMsg;
    	NLAL1100CMsg bizMsg = (NLAL1100CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLAL1100CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        scrnMsg.addCheckItem(scrnMsg.rtrnTrkNtfyGrpCd_G);
    	scrnMsg.addCheckItem(scrnMsg.carrCd_G);
    	scrnMsg.addCheckItem(scrnMsg.schdPickUpDt_G);
        // START 2019/05/09 T.Ogura [QC#50027,ADD]
        scrnMsg.addCheckItem(scrnMsg.rcvTsDplyTxt_G);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDtTxt_G);
        // END   2019/05/09 T.Ogura [QC#50027,ADD]
    	scrnMsg.addCheckItem(scrnMsg.inspCpltDt_G);
    	scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_G);
    	scrnMsg.addCheckItem(scrnMsg.actlPickUpDt_G);
    	scrnMsg.addCheckItem(scrnMsg.carrRsnCd_G);
    	scrnMsg.addCheckItem(scrnMsg.rtrnTrkStsCd_G);

    	scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {

            throw new EZDFieldErrorException();
        }

    }
}
