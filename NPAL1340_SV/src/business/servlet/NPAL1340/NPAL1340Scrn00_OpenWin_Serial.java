/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1340;

import static business.servlet.NPAL1340.constant.NPAL1340Constant.MSG_PARAM_RELEASE_QTY;
import static business.servlet.NPAL1340.constant.NPAL1340Constant.ZZM9000E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1340.common.NPAL1340CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * NPAL1340 Drop Ship Release
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/29   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NPAL1340Scrn00_OpenWin_Serial extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1340BMsg scrnMsg = (NPAL1340BMsg) bMsg;
        int btnNum = getButtonSelectNumber();
        if(!ZYPCommonFunc.hasValue(scrnMsg.A.no(btnNum).poRcvQty_A1)){
            scrnMsg.A.no(btnNum).poRcvQty_A1.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_RELEASE_QTY });
            scrnMsg.addCheckItem(scrnMsg.A.no(btnNum).poRcvQty_A1);
            scrnMsg.putErrorScreen();
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1340BMsg scrnMsg = (NPAL1340BMsg) bMsg;

        int btnNum = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(btnNum));

        List<Object> pParam = new ArrayList<Object>();

        // 1) VND_CD
        pParam.add(scrnMsg.A.no(btnNum).vndCd_A1);
        // 2) VND_NM
        pParam.add(scrnMsg.A.no(btnNum).vndNm_A1);
        // 3) CUST_ISS_PO_NUM
        pParam.add(scrnMsg.custIssPoNum_H2);
        // 4) CUST_ISS_PO_DT
        pParam.add(scrnMsg.custIssPoDt_H2);
        // 5) CPO_ORD_NUM
        pParam.add(scrnMsg.cpoOrdNum_H2);
        // 6) BILL_TO_CUST_CD
        pParam.add(scrnMsg.billToCustCd_H2);
        // 7) MDSE_CD
        pParam.add(scrnMsg.A.no(btnNum).mdseCd_A1);
        // 8) MDSE_NM
        pParam.add(scrnMsg.A.no(btnNum).mdseDescShortTxt_A1);
        // 9) PO_QTY
        pParam.add(scrnMsg.A.no(btnNum).poQty_A1);
        // 10) INV_QTY
        pParam.add(scrnMsg.A.no(btnNum).poRcvQty_A1);
        // 11) CPO_DTL_LINE_NUM
        pParam.add(scrnMsg.A.no(btnNum).cpoDtlLineNum_A1);
        // 12) CPO_DTL_LINE_SUB_NUM
        pParam.add(scrnMsg.A.no(btnNum).cpoDtlLineSubNum_A1);
        // 13) SHPG_PLN_NUM
        pParam.add(scrnMsg.A.no(btnNum).shpgPlnNum_A1);
        // 14) PO_ORD_NUM
        pParam.add(scrnMsg.poOrdNum_H2);
        // 15) PO_ORD_DTL_LINE_NUM
        pParam.add(scrnMsg.A.no(btnNum).poOrdDtlLineNum_A1);
        // 16) PO_RCV_NUM
        pParam.add(scrnMsg.A.no(btnNum).poRcvNum_A1);
        // 17) PO_RCV_LINE_NUM
        pParam.add(scrnMsg.A.no(btnNum).poRcvLineNum_A1);
        // 18) RowNum
        ArrayList<EZDBBigDecimalItem> rowArray = NPAL1340CommonLogic.getArrayRowNum(scrnMsg, btnNum);
        pParam.add(rowArray);
        // 19) Serial Number
        ArrayList<EZDBStringItem> serNumArray = NPAL1340CommonLogic.getArraySerialNum(scrnMsg, btnNum);
        pParam.add(serNumArray);
        // 20) Edit flag
        ArrayList<EZDBStringItem> flgArray = NPAL1340CommonLogic.getArrayProcFlg(scrnMsg, btnNum);
        pParam.add(flgArray);
        // 21) Processing Number
        scrnMsg.N.no(btnNum).xxRowNum.setValue(0);
        pParam.add(scrnMsg.N.no(btnNum).xxRowNum);
        // 22) New Entry Flag
        pParam.add(ZYPConstant.FLG_ON_Y);

        // 23) Supplier Name
        pParam.add(scrnMsg.prntVndNm_H2);
        
        // 24) PO Qualifier Code
        pParam.add(scrnMsg.poQlfyCd_H2);

        // 25) PO Source Name
        pParam.add(scrnMsg.poOrdSrcNm_H2);

        setArgForSubScreen(pParam.toArray());

    }
}
