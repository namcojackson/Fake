/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFBL2060.NFBL2060CMsg;
import business.servlet.NFBL2060.common.NFBL2060CommonLogic;
import business.servlet.NFBL2060.constant.NFBL2060Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/21   Hitachi         T.Tsuchida      Update          QC#12116
 * 2016/07/26   Hitachi         T.Tsuchida      Update          QC#12239
 * 2016/08/05   Fujitsu         C.Tanaka        Update          QC#12951
 * 2016/08/30   Fujitsu         C.Tanaka        Update          QC#5521
 * 2016/09/30   Hitachi         T.Tsuchida      Update          QC#14498
 * 2016/10/03   Hitachi         T.Tsuchida      Update          QC#13414
 * 2016/10/24   Hitachi         Y.Tsuchimoto    Update          QC#15493
 * 2018/02/21   Fujitsu         T.Murai         Update          QC#23494
 * 2018/03/23   Hitachi         Y.Takeno        Update          QC#22350
 * 2018/05/28   CITS            K.Ogino         Update          QC#25902
 * 2024/02/05   Hitachi         S.Ikariya       Update          QC#63451
 * 2024/03/27   Hitachi         Y.Ogura         Update          QC#63778
 *</pre>
 */
public class NFBL2060Scrn00_OnClick_Search extends S21CommonHandler implements NFBL2060Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;

        // Charge Account From
        if (ZYPCommonFunc.hasValue(scrnMsg.xxCmntTxt_FR)) {
            String str = scrnMsg.xxCmntTxt_FR.getValue();
            String[] strSplit = str.split("\\.");
            if (strSplit.length != 9) {
                // Format Error
                scrnMsg.xxCmntTxt_FR.setErrorInfo(1, ZZSM2014E);
            }
        }
        // Charge Account To
        if (ZYPCommonFunc.hasValue(scrnMsg.xxCmntTxt_TO)) {
            String str = scrnMsg.xxCmntTxt_TO.getValue();
            String[] strSplit = str.split("\\.");
            if (strSplit.length != 9) {
                // Format Error
                scrnMsg.xxCmntTxt_TO.setErrorInfo(1, ZZSM2014E);
            }
        }
        
      // START 2024/03/27 Y.Ogura [QC#63778,ADD]
      if(ZYPCommonFunc.hasValue(scrnMsg.acInvTotCostAmt_FR)&&(ZYPCommonFunc.hasValue(scrnMsg.acInvTotCostAmt_TO))){
          // Invoice Amount From-To Compare
          if(scrnMsg.acInvTotCostAmt_FR.getValue().compareTo(scrnMsg.acInvTotCostAmt_TO.getValue()) > 0){
              // Compare Error
              String errMsg = "Range";
              scrnMsg.acInvTotCostAmt_FR.setErrorInfo(1, NFBM0041E, new String[] {errMsg });
              scrnMsg.acInvTotCostAmt_TO.setErrorInfo(1, NFBM0041E, new String[] {errMsg });
          }
      }
      // END 2024/03/27 Y.Ogura [QC#63778,ADD]
        
        // Vendor(Header)
        scrnMsg.addCheckItem(scrnMsg.dplyVndNm);
        scrnMsg.addCheckItem(scrnMsg.apVndCd);
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        // START 2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.addCheckItem(scrnMsg.poNum);
        // END   2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.addCheckItem(scrnMsg.delyOrdNum);
        scrnMsg.addCheckItem(scrnMsg.poDt_FR);
        scrnMsg.addCheckItem(scrnMsg.poDt_TO);
        // START 2016/07/26 T.Tsuchida [QC#12239,MOD]
        // scrnMsg.addCheckItem(scrnMsg.vndPmtTermCd);
        scrnMsg.addCheckItem(scrnMsg.vndPmtTermDescTxt);
        // END 2016/07/26 T.Tsuchida [QC#12239,MOD]

        // Invoice(Header)
        scrnMsg.addCheckItem(scrnMsg.apVndInvNum);
        scrnMsg.addCheckItem(scrnMsg.acctInvStsCd_S);
        scrnMsg.addCheckItem(scrnMsg.invDt_FR);
        scrnMsg.addCheckItem(scrnMsg.invDt_TO);
        // START 2024/02/05 S.Ikariya [QC#63451, ADD]
        scrnMsg.addCheckItem(scrnMsg.acInvTotCostAmt_FR);
        scrnMsg.addCheckItem(scrnMsg.acInvTotCostAmt_TO);
        // END 2024/02/05 S.Ikariya [QC#63451, ADD]
        scrnMsg.addCheckItem(scrnMsg.xxCmntTxt_FR);
        scrnMsg.addCheckItem(scrnMsg.xxCmntTxt_TO);
        // START 2016/07/21 T.Tsuchida [QC#12116,MOD]
        //scrnMsg.addCheckItem(scrnMsg.vndPmtMethCd);
        scrnMsg.addCheckItem(scrnMsg.vndPmtMethDescTxt);
        // END 2016/07/21 T.Tsuchida [QC#12116,MOD]
        scrnMsg.addCheckItem(scrnMsg.apInvCatgCd_S);
        scrnMsg.addCheckItem(scrnMsg.apPmtChkNum);
        scrnMsg.addCheckItem(scrnMsg.pmtDt_FR);
        scrnMsg.addCheckItem(scrnMsg.pmtDt_TO);
        // START 2018/03/23 [QC#22350, ADD]
        scrnMsg.addCheckItem(scrnMsg.dispPoDtlLineNum);
        // END   2018/03/23 [QC#22350, ADD]
        // QC#25902
        scrnMsg.addCheckItem(scrnMsg.vndRtrnNum);
        scrnMsg.addCheckItem(scrnMsg.vndRtrnSubmtDt_FR);
        scrnMsg.addCheckItem(scrnMsg.vndRtrnSubmtDt_TO);
        // QC#25902 End
        // START 2018/10/18 J.Kim [QC#28816,ADD]
        scrnMsg.addCheckItem(scrnMsg.invAuthDt_FR);
        scrnMsg.addCheckItem(scrnMsg.invAuthDt_TO);
        // END 2018/10/18 J.Kim [QC#28816,ADD]

        // Invoice With(Header)
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_01);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_02);
        // START 2024/02/05 S.Ikariya [QC#63451, ADD]
        scrnMsg.putErrorScreen();
        // END 2024/02/05 S.Ikariya [QC#63451, ADD]

		// Add START 2018/02/21 S21_NA#23494
        // START 2018/03/30 [QC#22350, MOD] Mod QC#25902
        if (!ZYPCommonFunc.hasValue(scrnMsg.dplyVndNm) && !ZYPCommonFunc.hasValue(scrnMsg.apVndCd) && !ZYPCommonFunc.hasValue(scrnMsg.apVndInvNum) //
                && !ZYPCommonFunc.hasValue(scrnMsg.acctInvStsCd_S) && !ZYPCommonFunc.hasValue(scrnMsg.prntVndCd) && !ZYPCommonFunc.hasValue(scrnMsg.poNum) //
                && !ZYPCommonFunc.hasValue(scrnMsg.delyOrdNum) && !ZYPCommonFunc.hasValue(scrnMsg.invDt_FR) && !ZYPCommonFunc.hasValue(scrnMsg.invDt_TO) //
                // START 2024/02/05 S.Ikariya [QC#63451, ADD]
                && !ZYPCommonFunc.hasValue(scrnMsg.acInvTotCostAmt_FR) && !ZYPCommonFunc.hasValue(scrnMsg.acInvTotCostAmt_TO) //
                // END 2024/02/05 S.Ikariya [QC#63451, ADD]
                && !ZYPCommonFunc.hasValue(scrnMsg.poDt_FR) && !ZYPCommonFunc.hasValue(scrnMsg.poDt_TO) && !ZYPCommonFunc.hasValue(scrnMsg.vndPmtTermDescTxt) //
                && !ZYPCommonFunc.hasValue(scrnMsg.xxCmntTxt_FR) && !ZYPCommonFunc.hasValue(scrnMsg.xxCmntTxt_TO) && !ZYPCommonFunc.hasValue(scrnMsg.vndPmtMethDescTxt) //
                && !ZYPCommonFunc.hasValue(scrnMsg.apInvCatgCd_S) && !ZYPCommonFunc.hasValue(scrnMsg.apPmtChkNum) && !ZYPCommonFunc.hasValue(scrnMsg.pmtDt_FR) && !ZYPCommonFunc.hasValue(scrnMsg.pmtDt_TO) //
                && !ZYPCommonFunc.hasValue(scrnMsg.dispPoDtlLineNum) //
                && !ZYPCommonFunc.hasValue(scrnMsg.invAuthDt_FR) && !ZYPCommonFunc.hasValue(scrnMsg.invAuthDt_TO) //
                && !ZYPCommonFunc.hasValue(scrnMsg.vndRtrnNum) && !ZYPCommonFunc.hasValue(scrnMsg.vndRtrnSubmtDt_FR) && !ZYPCommonFunc.hasValue(scrnMsg.vndRtrnSubmtDt_TO)
                ) {
            scrnMsg.setMessageInfo(NFBM0212E);
            throw new EZDFieldErrorException();
        }
        // END   2018/03/30 [QC#22350, MOD] Mod QC#25902
        // Add END 2018/02/21 S21_NA#23494
        // START 2024/02/05 S.Ikariya [QC#63451, DEL]
//        scrnMsg.putErrorScreen();
        // END 2024/02/05 S.Ikariya [QC#63451, DEL]

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;

        NFBL2060CMsg bizMsg = new NFBL2060CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;
        NFBL2060CMsg bizMsg  = (NFBL2060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        /** Initialize button control */ 
        NFBL2060CommonLogic.initControl(this, scrnMsg);
        /** Initialize tab position */
        // QC#5521 DEL Start
        // scrnMsg.xxDplyTab.setValue(TAB_DETAILED);
        // QC#5521 DEL End
        /** Set focus when opening screen */
        scrnMsg.setFocusItem(scrnMsg.dplyVndNm);

        // QC#5521 MOD Start
        // QC#12951 ADD Start
        if (TAB_DETAILED.equals(scrnMsg.xxDplyTab.getValue())) {
            NFBL2060CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.D, null);
        } else {
            NFBL2060CommonLogic.setRowsBGWithClear(scrnMsg, null, scrnMsg.S);
        }
        // QC#12951 ADD End
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.S.no(0).getBaseContents());
        // QC#5521 MOD End
    }
}
