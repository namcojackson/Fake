/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0620;

import static business.servlet.NSAL0620.constant.NSAL0620Constant.BIZ_ID;
import static business.servlet.NSAL0620.constant.NSAL0620Constant.SCREEN_ID;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0620.NSAL0620CMsg;
import business.servlet.NSAL0620.common.NSAL0620CommonLogic;
import business.servlet.NSAL0620.constant.NSAL0620Constant.FUNC;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         T.Tsuchida      Create          N/A
 * 2016/04/25   Hitachi         M.Gotou         Update          QC#4326
 * 2016/07/01   Hitachi         T.Aoyagi        Update          QC#11261
 * 2018/06/15   Hitachi         K.Kim           Update          QC#26255
 * 2019/08/30   Hitachi         T.Aoyagi        Update          QC#53005
 * 2022/10/13   Hitachi         M.Komatsu       Update          QC#60078,60537
 *</pre>
 */
public class NSAL0620_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;
        NSAL0620CMsg bizMsg = new NSAL0620CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());

        // START 2018/06/15 K.Kim [QC#26255, ADD]
        scrnMsg.xxRadioBtn_H2.setValue(BigDecimal.valueOf(4));
        // END 2018/06/15 K.Kim [QC#26255, ADD]

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;
        NSAL0620CMsg bizMsg = (NSAL0620CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NSAL0620CommonLogic.initialControlScreen(this, scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;
        scrnMsg.dsContrNum_H.setNameForMessage("Contract Number");
        scrnMsg.dsContrCratDt_H1.setNameForMessage("Contract From Date");
        scrnMsg.dsContrCratDt_H2.setNameForMessage("Contract Thru Date");
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        scrnMsg.xxComnScrColValTxt_H1.setNameForMessage("Contract Type");
        scrnMsg.xxComnScrColValTxt_H2.setNameForMessage("Contract Status");
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]
        // mod start 2016/04/25 CSA Defect#4326
        scrnMsg.dsContrRptGrpNum_H.setNameForMessage("Contract Report Group Number");
        // mod end 2016/04/25 CSA Defect#4326
        scrnMsg.svcContrRefCmntTxt_H.setNameForMessage("Commnet");
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        scrnMsg.xxComnScrColValTxt_H3.setNameForMessage("Contract Category");
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]
        scrnMsg.dsAcctNm_H.setNameForMessage("Account Name");
        scrnMsg.dsAcctNum_H.setNameForMessage("Account Number");
        scrnMsg.xxGenlFldAreaTxt_H1.setNameForMessage("Genl1");
        scrnMsg.billToCustCd_H1.setNameForMessage("Bill To Customer Code");
        // add start 2016/07/01 CSA Defect#11261
        scrnMsg.mdseDescShortTxt_H.setNameForMessage("Service Program");
        // add end 2016/07/01 CSA Defect#11261
        scrnMsg.dsAcctNm_H3.setNameForMessage("Master Account Code");
        scrnMsg.serNum_H.setNameForMessage("Serial #");
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        scrnMsg.xxComnScrColValTxt_H4.setNameForMessage("Machine Status");
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]
        // START 2022/10/13 M.Komatsu [QC#60537,ADD]
        scrnMsg.mdseCd_H.setNameForMessage("Item #");
        // END 2022/10/13 M.Komatsu [QC#60537,ADD]
        scrnMsg.t_MdlNm_H.setNameForMessage("Model Name");
        // START 2019/08/30 [QC#53005,ADD]
        scrnMsg.svcConfigMstrPk_H.setNameForMessage("Config #");
        // END 2019/08/30 [QC#53005,ADD]
        // mod start 2016/04/25 CSA Defect#4326
        scrnMsg.dsAcctNm_H2.setNameForMessage("Located At Customer Name");
        // mod end 2016/04/25 CSA Defect#4326
        scrnMsg.xxGenlFldAreaTxt_H2.setNameForMessage("Genl2");
        scrnMsg.locNum_H.setNameForMessage("Location Number");
        scrnMsg.xxFromDt_H.setNameForMessage("From Date");
        scrnMsg.xxThruDt_H.setNameForMessage("Thru Date");
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        scrnMsg.xxComnScrColValTxt_H5.setNameForMessage("Base Frequency");
        scrnMsg.xxComnScrColValTxt_H6.setNameForMessage("Overage Frequency");
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]
        scrnMsg.srchOptNm_S.setNameForMessage("Search Option Name");
    }
}
