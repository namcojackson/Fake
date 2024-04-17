/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2040.NFBL2040CMsg;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;
import business.servlet.NFBL2040.constant.NFBL2040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/07/07   Hitachi         Y.Tsuchimoto    Update          QC#11331
 * 2016/07/22   Hitachi         Y.Tsuchimoto    Update          QC#12008
 * 2016/07/25   Hitachi         Y.Tsuchimoto    Update          QC#11999
 * 2016/07/27   Hitachi         T.Tsuchida      Update          QC#12002
 * 2016/07/29   Hitachi         K.Kojima        Update          QC#12665
 * 2016/08/01   Hitachi         T.Tsuchida      Update          QC#12009
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#13157
 * 2016/09/28   Hitachi         T.Tsuchida      Update          QC#13960
 * 2016/10/04   Hitachi         T.Tsuchida      Update          QC#13414
 * 2016/10/07   Hitachi         T.Tsuchida      Update          QC#13960
 * 2016/10/26   Hitachi         Y.Tsuchimoto    Update          QC#15493
 * 2017/01/12   Hitachi         E.Kameishi      Update          QC#16950
 * 2018/02/26   Hitachi         Y.Takeno        Update          QC#23505
 * 2018/03/06   Hitachi         Y.Takeno        Update          QC#24079
 * 2018/05/25   CITS            K.Ogino         Update          QC#25902,QC#25190,QC#25141
 * 2020/02/17   Fujitsu         H.Ikeda         Update          QC#53413
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 *</pre>
 */
public class NFBL2040_NWAL1130 extends S21CommonHandler implements NFBL2040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if (CMN_CLOSE.equals(getLastGuard())) {
            return null;
        }

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        // START 2018/02/28 [QC#24079, DEL]
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        // START 2018/03/06 [QC#23505, MOD]
        // if (OPENWIN_SUPPLIER.equals(scrEventNm) || ONCLICK_TERM_LINK.equals(scrEventNm)) {
        // // START 2017/01/12 E.Kameishi [QC#16950,ADD]
        //     if (ZYPCommonFunc.hasValue(scrnMsg.termNetDueDt)) {
        //         return null;
        //     }
        // // END 2017/01/12 E.Kameishi [QC#16950,ADD]
        // }
        // // END 2018/02/28 [QC#23505, MOD]
        // END 2018/03/06 [QC#24079, DEL]

        if (OPENWIN_SUPPLIER.equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndCd, scrnMsg.Z.no(NFBL2040Constant.IDX_0).xxComnScrColValTxt);
            String dplyVndNm = scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue();
            if (ZYPCommonFunc.hasValue(dplyVndNm)
                    && ZYPCommonFunc.hasValue(scrnMsg.Z.no(IDX_3).xxComnScrColValTxt.getValue())) {
                dplyVndNm = scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue() + DIV_DPLY_VND_NM + scrnMsg.Z.no(IDX_3).xxComnScrColValTxt.getValue();
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.dplyVndNm, dplyVndNm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.apVndCd, scrnMsg.Z.no(NFBL2040Constant.IDX_2).xxComnScrColValTxt);

            NFBL2040CMsg bizMsg = new NFBL2040CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode(FUNC_CD_20);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        } else if (OPENWIN_PURCHASE_ORDER_1.equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.poNum, scrnMsg.Z.no(NFBL2040Constant.IDX_0).xxComnScrColValTxt);
        } else if (OPENWIN_PURCHASE_ORDER_2.equals(scrEventNm)) {
            // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
            ZYPEZDItemValueSetter.setValue(scrnMsg.poNum_HT, scrnMsg.Z.no(NFBL2040Constant.IDX_0).xxComnScrColValTxt);
            // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]

            // START 2018/02/26 [QC#23505, ADD]
            if (NFBL2040CommonLogic.isCorrectableInvoice(scrnMsg) && NFBL2040CommonLogic.isCorrectedInvoice(scrnMsg) 
                    && NFBL2040CommonLogic.isUnsubmittedCorrection(scrnMsg)) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.poNum, scrnMsg.Z.no(NFBL2040Constant.IDX_0).xxComnScrColValTxt);
                scrnMsg.delyOrdNum_H.clear();
                scrnMsg.rwsNum_H.clear();

                NFBL2040CMsg bizMsg = new NFBL2040CMsg();
                bizMsg.setBusinessID(BIZ_ID);
                bizMsg.setFunctionCode(FUNC_CD_20);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
                return bizMsg;
            }
            // END   2018/02/26 [QC#23505, ADD]
        // START 2020/02/17 H.Ikeda [QC#53413, ADD]
        } else if (OPENWIN_PURCHASE_ORDER_3.equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.poNum_L, scrnMsg.Z.no(NFBL2040Constant.IDX_0).xxComnScrColValTxt);
        } else if (OPENWIN_RECEIPT_3.equals(scrEventNm)) {
            // mod start 2022/02/15 QC#57090
            //ZYPEZDItemValueSetter.setValue(scrnMsg.poNum_L, scrnMsg.Z.no(NFBL2040Constant.IDX_0).xxComnScrColValTxt);
            //ZYPEZDItemValueSetter.setValue(scrnMsg.delyOrdNum_L, scrnMsg.Z.no(NFBL2040Constant.IDX_1).xxComnScrColValTxt);
            //ZYPEZDItemValueSetter.setValue(scrnMsg.rwsNum_L, scrnMsg.Z.no(NFBL2040Constant.IDX_2).xxComnScrColValTxt);
            if ("PO".equals(scrnMsg.Z.no(NFBL2040Constant.IDX_2).xxComnScrColValTxt.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.poNum_L, scrnMsg.Z.no(NFBL2040Constant.IDX_0).xxComnScrColValTxt);
                scrnMsg.vndRtrnNum_L.clear();
            } else if ("Vendor Return".equals(scrnMsg.Z.no(NFBL2040Constant.IDX_2).xxComnScrColValTxt.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndRtrnNum_L, scrnMsg.Z.no(NFBL2040Constant.IDX_0).xxComnScrColValTxt);
                scrnMsg.poNum_L.clear();
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.poNum_L, scrnMsg.Z.no(NFBL2040Constant.IDX_0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rwsNum_L, scrnMsg.Z.no(NFBL2040Constant.IDX_2).xxComnScrColValTxt);
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.delyOrdNum_L, scrnMsg.Z.no(NFBL2040Constant.IDX_1).xxComnScrColValTxt);
           // mod end 2022/02/15 QC#57090
        // END   2020/02/17 H.Ikeda [QC#53413, ADD]
        } else if (OPENWIN_RECEIPT_1.equals(scrEventNm)) {
            // START QC#25902,QC#25190,QC#25141
            if ("PO".equals(scrnMsg.Z.no(NFBL2040Constant.IDX_2).xxComnScrColValTxt.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.poNum, scrnMsg.Z.no(NFBL2040Constant.IDX_0).xxComnScrColValTxt);
                scrnMsg.vndRtrnNum.clear();
            } else if ("Vendor Return".equals(scrnMsg.Z.no(NFBL2040Constant.IDX_2).xxComnScrColValTxt.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndRtrnNum, scrnMsg.Z.no(NFBL2040Constant.IDX_0).xxComnScrColValTxt);
                scrnMsg.poNum.clear();
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.poNum, scrnMsg.Z.no(NFBL2040Constant.IDX_0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rwsNum_H, scrnMsg.Z.no(NFBL2040Constant.IDX_2).xxComnScrColValTxt);
            }
            // END QC#25902,QC#25190,QC#25141
            ZYPEZDItemValueSetter.setValue(scrnMsg.delyOrdNum_H, scrnMsg.Z.no(NFBL2040Constant.IDX_1).xxComnScrColValTxt);
        } else if (OPENWIN_RECEIPT_2.equals(scrEventNm)) {
            // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
            ZYPEZDItemValueSetter.setValue(scrnMsg.poNum_HT, scrnMsg.Z.no(NFBL2040Constant.IDX_0).xxComnScrColValTxt);
            // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
            ZYPEZDItemValueSetter.setValue(scrnMsg.delyOrdNum_DH, scrnMsg.Z.no(NFBL2040Constant.IDX_1).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rwsNum_DH, scrnMsg.Z.no(NFBL2040Constant.IDX_2).xxComnScrColValTxt);
        } else if (ONCLICK_TERM_LINK.equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.vndPmtTermDescTxt, scrnMsg.Z.no(NFBL2040Constant.IDX_1).xxComnScrColValTxt);

            // START 2016/10/07 T.Tsuchida [QC#13960,ADD]
            NFBL2040CMsg bizMsg = new NFBL2040CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode(FUNC_CD_20);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
            // END 2016/10/07 T.Tsuchida [QC#13960,ADD]
        // START QC#25902,QC#25190,QC#25141
        } else if (BTN_NORMAL_CORRECTION.equals(scrEventNm)) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.poNum_HT, scrnMsg.Z.no(NFBL2040Constant.IDX_0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.poNum, scrnMsg.Z.no(NFBL2040Constant.IDX_0).xxComnScrColValTxt);
            scrnMsg.delyOrdNum_H.clear();
            scrnMsg.rwsNum_H.clear();

            NFBL2040CMsg bizMsg = new NFBL2040CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode(FUNC_CD_20);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageTblNm, TABLE_A);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        } else if (OPENWIN_VND_RTRN_1.equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.vndRtrnNum, scrnMsg.Z.no(NFBL2040Constant.IDX_0).xxComnScrColValTxt);
        } else if (OPENWIN_VND_RTRN_2.equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.vndRtrnNum_H, scrnMsg.Z.no(NFBL2040Constant.IDX_0).xxComnScrColValTxt);
        // add start 2022/02/15 QC#57090
        } else if (OPENWIN_VND_RTRN_3.equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.vndRtrnNum_L, scrnMsg.Z.no(NFBL2040Constant.IDX_0).xxComnScrColValTxt);
        // add end 2022/02/15 QC#57090
        }
        // END QC#25902,QC#25190,QC#25141
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // START QC#25902,QC#25190,QC#25141
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if (CMN_CLOSE.equals(getLastGuard())) {
            // START 2020/02/17 H.Ikeda [QC#53413, ADD]
            if (OPENWIN_PURCHASE_ORDER_3.equals(scrEventNm) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_CR.getValue())) {
                return;
            }
            // START 2020/02/17 H.Ikeda [QC#53413, ADD]
            scrnMsg.xxBtnFlg_CR.setValue(ZYPConstant.FLG_OFF_N);
            scrnMsg.xxChkBox_CR.setValue(ZYPConstant.FLG_OFF_N);
            return;
        }
        // END QC#25902,QC#25190,QC#25141

        if (OPENWIN_SUPPLIER.equals(scrEventNm)) {
            NFBL2040CMsg bizMsg  = (NFBL2040CMsg) cMsg;

            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            scrnMsg.addCheckItem(scrnMsg.vndPmtMethDescTxt);
            scrnMsg.putErrorScreen();
            scrnMsg.setFocusItem(scrnMsg.apVndCd);
        } else if (OPENWIN_PURCHASE_ORDER_1.equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.poNum);
        } else if (OPENWIN_PURCHASE_ORDER_2.equals(scrEventNm)) {
            // START 2018/02/26 [QC#23505, ADD]
            if (NFBL2040CommonLogic.isCorrectableInvoice(scrnMsg) && NFBL2040CommonLogic.isCorrectedInvoice(scrnMsg) 
                    && NFBL2040CommonLogic.isUnsubmittedCorrection(scrnMsg)) {
                NFBL2040CMsg bizMsg  = (NFBL2040CMsg) cMsg;

                EZDMsg.copy(bizMsg, null, scrnMsg, null);
                scrnMsg.addCheckItem(scrnMsg.poNum_HT);
                scrnMsg.putErrorScreen();

                NFBL2040CommonLogic.initControl(this, scrnMsg);
                NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);
                NFBL2040CommonLogic.setFocusRule(scrnMsg);
                NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
            }
            // END   2018/02/26 [QC#23505, ADD]
            // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
            scrnMsg.setFocusItem(scrnMsg.poNum_HT);
            // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        // START 2020/02/17 H.Ikeda [QC#15493, ADD]
        } else if (OPENWIN_PURCHASE_ORDER_3.equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.poNum_L);
        // END   2020/02/17 H.Ikeda [QC#15493, ADD]
        } else if (OPENWIN_RECEIPT_1.equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.delyOrdNum_H);
        } else if (OPENWIN_RECEIPT_2.equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.delyOrdNum_HD);
        // START 2020/02/17 H.Ikeda [QC#15493, ADD]
        } else if (OPENWIN_RECEIPT_3.equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.delyOrdNum_L);
        // END   2020/02/17 H.Ikeda [QC#15493, ADD]
        } else if (ONCLICK_TERM_LINK.equals(scrEventNm)) {
            // START 2016/10/07 T.Tsuchida [QC#13960,ADD]
            NFBL2040CMsg bizMsg  = (NFBL2040CMsg) cMsg;

            // START 2018/03/06 [QC#24079, MOD]
            // START 2017/01/12 E.Kameishi [QC#16950,ADD]
            // if (!ZYPCommonFunc.hasValue(scrnMsg.termNetDueDt)) {
            //     EZDMsg.copy(bizMsg, null, scrnMsg, null);
            // }
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            // END 2017/01/12 E.Kameishi [QC#16950,ADD]
            // END 2016/10/07 T.Tsuchida [QC#13960,ADD]
            // END 2018/03/06 [QC#24079, MOD]
            scrnMsg.setFocusItem(scrnMsg.vndPmtTermDescTxt);
        // START QC#25902,QC#25190,QC#25141
        } else if (BTN_NORMAL_CORRECTION.equals(scrEventNm)) {
            NFBL2040CMsg bizMsg  = (NFBL2040CMsg) cMsg;

            if (MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            }

            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            scrnMsg.addCheckItem(scrnMsg.poNum);
            scrnMsg.putErrorScreen();

            /** Initialize button control */ 
            NFBL2040CommonLogic.initControl(this, scrnMsg);

            /** Set alternate rows background color */
            NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);
            NFBL2040CommonLogic.clearRowsBG_H(scrnMsg);

            /** Initialize tab position */
            NFBL2040CommonLogic.initTabPosition(scrnMsg);

            /** Set focus when opening screen */
            scrnMsg.setFocusItem(scrnMsg.poNum_HT);

            NFBL2040CommonLogic.setFocusRule(scrnMsg);
            NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
        } else if (OPENWIN_VND_RTRN_1.equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.vndRtrnNum);
        } else if (OPENWIN_VND_RTRN_2.equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.vndRtrnNum_H);
        // END QC#25902,QC#25190,QC#25141
        // add start 2022/02/15 QC#57090
        } else if (OPENWIN_VND_RTRN_3.equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.vndRtrnNum_L);
        // add end 2022/02/15 QC#57090
        }

        // START 2016/07/29 K.Kojima [QC#12665,ADD]
        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
        // END 2016/07/29 K.Kojima [QC#12665,ADD]
    }
}
