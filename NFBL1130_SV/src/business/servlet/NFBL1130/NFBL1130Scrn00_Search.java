/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFBL1130.NFBL1130CMsg;
import business.servlet.NFBL1130.common.NFBL1130CommonLogic;
import business.servlet.NFBL1130.constant.NFBL1130Constant;
import business.servlet.NFBL1130.NFBL1130BMsg;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CUSA            Y.Aikawa        Create          N/A
 * 2016/09/29   Hitachi         K.Kojima        Update          QC#14178
 * 2016/09/30   Hitachi         K.Kojima        Update          QC#14179
 * 2022/03/28   Hitachi         A.Kohinata      Update          QC#57935(56588)
 *</pre>
 */
public class NFBL1130Scrn00_Search extends S21CommonHandler implements NFBL1130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;

        // Mandatory Check
//        // Mandatory.
//        if (!ZYPCommonFunc.hasValue(scrnMsg.delyOrdNum_H)) {
//            scrnMsg.delyOrdNum_H.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.delyOrdNum_H.getNameForMessage() });
//        }
        
        
        // TODO now
        scrnMsg.addCheckItem(scrnMsg.poNum);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.delyOrdNum);
        scrnMsg.addCheckItem(scrnMsg.invRcvQty);
        scrnMsg.addCheckItem(scrnMsg.apVndInvNum);
        // START 2016/09/30 K.Kojima [QC#14179,MOD]
        // scrnMsg.addCheckItem(scrnMsg.apVndCd);
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        // END 2016/09/30 K.Kojima [QC#14179,MOD]
        scrnMsg.addCheckItem(scrnMsg.prntVndNm);
        scrnMsg.addCheckItem(scrnMsg.invQty);
        scrnMsg.addCheckItem(scrnMsg.acrlCoaAcctCd_S);
        // START 2016/09/29 K.Kojima [QC#14178,ADD]
        scrnMsg.addCheckItem(scrnMsg.rwsNum);
        // END 2016/09/29 K.Kojima [QC#14178,ADD]
        scrnMsg.addCheckItem(scrnMsg.invDt_FR);
        scrnMsg.addCheckItem(scrnMsg.invDt_TO);
        scrnMsg.addCheckItem(scrnMsg.stkInDt_FR);
        scrnMsg.addCheckItem(scrnMsg.stkInDt_TO);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;

        NFBL1130CMsg bizMsg = new NFBL1130CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;
        NFBL1130CMsg bizMsg  = (NFBL1130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        /** Initialize button control */ 
        NFBL1130CommonLogic.initControl(this, scrnMsg);
        /** Set alternate rows background color */
        NFBL1130CommonLogic.setAlternateRowsBGCommon(scrnMsg);
        /** Set focus when opening screen */
        scrnMsg.setFocusItem(scrnMsg.poNum);
        // add start 2022/03/28 QC#57935(56588)
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // add end 2022/03/28 QC#57935(56588)
    }
}
