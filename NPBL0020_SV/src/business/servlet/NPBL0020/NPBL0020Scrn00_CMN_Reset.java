/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.SCRN_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.TAB_DETAIL;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;
import business.servlet.NPBL0020.common.NPBL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Reset
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/22/2016   CITS            Makoto Okigami  Create          N/A
 * 04/05/2016   CITS            K.Ogino         Update          N/A
 *</pre>
 */
public class NPBL0020Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Do nothing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        EZDBStringItem str = scrnMsg.xxComnColOrdTxt;
        String prchReqNum = null;
        String prchReqTpCdSL = PRCH_REQ_TP.SUBCONTRACT;
        String prchReqSrcTpCd = PRCH_REQ_SRC_TP.PARTS_REFURB_AUTO;
        String trxRefNum = null;
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        String mrpPlnNm = null;
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        String poOrdNum = null;
        String poOrdDtlLineNum = null;

        if (ZYPCommonFunc.hasValue(scrnMsg.prchReqNum_IP)) {
            prchReqNum = scrnMsg.prchReqNum_IP.getValue();
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.trxRefNum)) {
            trxRefNum = scrnMsg.trxRefNum.getValue();
        }
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        if (ZYPCommonFunc.hasValue(scrnMsg.mrpPlnNm)) {
            mrpPlnNm = scrnMsg.mrpPlnNm.getValue();
        }
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        if (ZYPCommonFunc.hasValue(scrnMsg.poOrdNum)) {
            poOrdNum = scrnMsg.poOrdNum.getValue();
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.poOrdDtlLineNum)) {
            poOrdDtlLineNum = scrnMsg.poOrdDtlLineNum.getValue();
        }

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.clear();

        NPBL0020CMsg bizMsg = new NPBL0020CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxComnColOrdTxt, str);

        ZYPEZDItemValueSetter.setValue(bizMsg.prchReqNum_IP, prchReqNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.prchReqTpCd_SL, prchReqTpCdSL);
        ZYPEZDItemValueSetter.setValue(bizMsg.prchReqSrcTpCd, prchReqSrcTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.trxRefNum, trxRefNum);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        ZYPEZDItemValueSetter.setValue(bizMsg.mrpPlnNm, mrpPlnNm);
        //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        ZYPEZDItemValueSetter.setValue(bizMsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.poOrdDtlLineNum, poOrdDtlLineNum);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        NPBL0020CMsg bizMsg = (NPBL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String prchReqTpCd_SL = scrnMsg.prchReqTpCd_SL.getValue();

        if (ZYPCommonFunc.hasValue(scrnMsg.prchReqNum)) {
            NPBL0020CommonLogic.setCtrlScrnItemDispAfterSearch(this, scrnMsg);
        } else if (ZYPCommonFunc.hasValue(scrnMsg.poOrdNum) && ZYPCommonFunc.hasValue(prchReqTpCd_SL) && PRCH_REQ_TP.SUBCONTRACT.equals(prchReqTpCd_SL)) {
            scrnMsg.prchReqTpCd_SL.setValue(prchReqTpCd_SL);
            NPBL0020CommonLogic.setCtrlScrnItemDispInit(this, scrnMsg);
        } else {
            scrnMsg.prchReqTpCd_SL.clear();
            NPBL0020CommonLogic.setCtrlScrnItemDispInit(this, scrnMsg);
        }

        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);

        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.prchReqNum);

    }
}
