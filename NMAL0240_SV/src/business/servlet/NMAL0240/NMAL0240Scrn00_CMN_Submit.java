/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0240;

import static business.servlet.NMAL0240.constant.NMAL0240Constant.BIZ_ID;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BOM_ITEM;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.COMPONENT;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.FUNC_CD_40;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.NMAM0185E;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.NMAM0803E;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.NMAM0833E;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.NZZM0002I;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.QUANTITY;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.START_DATE;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0240.NMAL0240CMsg;
import business.servlet.NMAL0240.common.NMAL0240CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0240Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Fujitsu         C.Tanaka        Create          CSA
 * 2016/02/18   SRAA            Y.Chen          Update          QC#2645
 * 2018/03/14   CITS            K.Ogino         Update          QC#22324
 *</pre>
 */
public class NMAL0240Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL0240BMsg scrnMsg = (NMAL0240BMsg) bMsg;

        if (scrnMsg.A.getValidCount() == 0 && !ZYPCommonFunc.hasValue(scrnMsg.cmpsnRevnNum_A)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {"Component" });
            throw new EZDFieldErrorException();
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseCd_A1)) {
                scrnMsg.A.no(i).mdseCd_A1.setErrorInfo(1, ZZM9000E, new String[] {COMPONENT });
            }
            if (scrnMsg.mdseCd.getValue().equals(scrnMsg.A.no(i).mdseCd_A1.getValue())) {
                scrnMsg.A.no(i).mdseCd_A1.setErrorInfo(1, NMAM0833E, new String[] {"Mdse Code", BOM_ITEM });
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).childMdseQty_A1)) {
                scrnMsg.A.no(i).childMdseQty_A1.setErrorInfo(1, ZZM9000E, new String[] {QUANTITY });
            }
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxChkBox) || ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxChkBox.getValue())) {
        	
	        String salesDt = ZYPDateUtil.getSalesDate();
	        String fromDt = scrnMsg.effFromDt_A.getValue();
	        String thruDt = scrnMsg.effThruDt_A.getValue();
	        if (fromDt.isEmpty()) {
	            scrnMsg.effFromDt_A.setErrorInfo(1, ZZM9000E, new String[] {START_DATE });
	        }
	        if (thruDt.isEmpty()) {
	            thruDt = "99991231";
	        }
	
	        if (0 < ZYPDateUtil.compare(salesDt, fromDt)) {
	            scrnMsg.effFromDt_A.setErrorInfo(1, NMAM0185E);
	        }
	        if (0 <= ZYPDateUtil.compare(fromDt, thruDt)) {
	            scrnMsg.effFromDt_A.setErrorInfo(1, NMAM0803E);
	            scrnMsg.effThruDt_A.setErrorInfo(1, NMAM0803E);
	        }
        }

        NMAL0240CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0240BMsg scrnMsg = (NMAL0240BMsg) bMsg;

        NMAL0240CMsg bizMsg = new NMAL0240CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_40);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0240BMsg scrnMsg = (NMAL0240BMsg) bMsg;
        NMAL0240CMsg bizMsg = (NMAL0240CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }

        NMAL0240CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }

        NMAL0240CommonLogic.controlFields(this, scrnMsg, false);
        NMAL0240CommonLogic.controlRevisionFields(this, scrnMsg);
        // QC#2645
        NMAL0240CommonLogic.controlTableRowColor(scrnMsg);
        // QC#22324
        ZYPTableUtil.clear(scrnMsg.R);
    }
}
