/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1230;

import static business.servlet.NSAL1230.constant.NSAL1230Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1230.NSAL1230CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/11   Hitachi         Y.Takeno        Create          N/A
 * 2018/04/10   CITS            T.Wada          Update          QC#23378(Sol#320)
 *</pre>
 */
public class NSAL1230Scrn00_CMN_Submit extends S21CommonHandler {

    // QC#23378(Sol#320) Add Start
    private void checkInput4BC(NSAL1230BMsg scrnMsg) {
        int entryMode = 9; 
        for(int i=0; i<scrnMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).coaAfflAcctNm_A1)) {
                scrnMsg.A.no(i).coaAfflAcctNm_A1.setErrorInfo(1, ZZM9000E, new String[] {"Segment" });
                scrnMsg.addCheckItem(scrnMsg.A.no(i).coaAfflAcctNm_A1);
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcAllocRate_A1) && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcAllocAmt_A1)) {
                scrnMsg.A.no(i).prcAllocRate_A1.setErrorInfo(1, NMAM0207E, new String[] {"Percent" ,"Price"});
                scrnMsg.A.no(i).prcAllocAmt_A1.setErrorInfo(1, NMAM0207E, new String[] {"Percent" ,"Price" });
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prcAllocRate_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prcAllocAmt_A1);
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcAllocRate_A1)) {
                if(entryMode == MODE_PRICE) {
                    scrnMsg.A.no(i).prcAllocRate_A1.setErrorInfo(1, NSAM0714E, new String[] {"Percent" ,"Price"});
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).prcAllocRate_A1);
                } else {
                    entryMode = MODE_PERCENT;
                }
            } else if(ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcAllocAmt_A1)) {
                if(entryMode == MODE_PERCENT) {
                    scrnMsg.A.no(i).prcAllocAmt_A1.setErrorInfo(1, NSAM0714E, new String[] {"Price" ,"Percent" });
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).prcAllocAmt_A1);
                } else {
                    entryMode = MODE_PRICE;
                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcAllocRate_A1) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcAllocAmt_A1)) {
                scrnMsg.A.no(i).prcAllocRate_A1.setErrorInfo(1, NSAM0714E, new String[] {"Percent" ,"Price"});
                scrnMsg.A.no(i).prcAllocAmt_A1.setErrorInfo(1, NSAM0714E, new String[] {"Price" ,"Percent" });
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prcAllocRate_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prcAllocAmt_A1);
            }
        }
	}
    // QC#23378(Sol#320) Add End

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1230BMsg scrnMsg = (NSAL1230BMsg) bMsg;

        // QC#23378(Sol#320) Mod Start
        scrnMsg.A.setCheckParam(new String[] {NSAL1230Bean.coaAfflAcctNm_A1}, 1);

        if (ZYPCommonFunc.hasValue(scrnMsg.svcInvChrgTpCd) && SVC_INV_CHRG_TP.BASE_CHARGE.equals(scrnMsg.svcInvChrgTpCd.getValue())) {
            checkInput4BC(scrnMsg);
        } else {
            scrnMsg.A.setCheckParam(new String[] {NSAL1230Bean.coaAfflAcctNm_A1, NSAL1230Bean.prcAllocRate_A1 }, 1);
            for(int i=0; i<scrnMsg.A.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcAllocRate_A1)) {
                    scrnMsg.A.no(i).prcAllocRate_A1.setErrorInfo(1, ZZM9000E, new String[] {"Percent"});
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).prcAllocRate_A1);
                }
            }
        }
        // QC#23378(Sol#320) Mod End

        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1230BMsg scrnMsg = (NSAL1230BMsg) bMsg;

        NSAL1230CMsg bizMsg = new NSAL1230CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUCNTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1230BMsg scrnMsg = (NSAL1230BMsg) bMsg;
        NSAL1230CMsg bizMsg = (NSAL1230CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        if ("W".equals(bizMsg.getMessageKind())) {
            return;
        }
    }
}
