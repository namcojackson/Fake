package business.blap.NYEL8810;

import static business.blap.NYEL8810.constant.NYEL8810Constant.TASK_ID_APPROVAL;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0010E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0012E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0016E;
//import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ZZSM4110E;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLCL0250.constant.NLCL0250Constant;
import business.blap.NYEL8810.common.NYEL8810CommonLogic;
import business.blap.NYEL8810.constant.NYEL8810Constant;
import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002;
import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002Query;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.common.util.S21StopWatch;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.internal.calendar.S21SystemDate;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * NYEL8810CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/10/10   Fujitsu         Q10627          Update          QC#28705
 * 2018/12/18   Fujitsu         Q10627          Update          QC#29682
 * </pre>
*/
public class NYEL8810BL02 extends S21BusinessHandler {

    private static final S21Logger logger = S21LoggerFactory.getLogger(NYEL8810BL02.class);

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);


        
        try {
            String screenAplID = cMsg.getScreenAplID();

            S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess]Start %s", screenAplID));
            S21StopWatch sw = new S21StopWatch();
            sw.start();
            
            if ("NYEL8810_INIT".equals(screenAplID)) {
                doProcess_NYEL8810_INIT((NYEL8810CMsg) cMsg, (NYEL8810SMsg)sMsg);
                ZYPGUITableColumn.getColData((NYEL8810CMsg) cMsg, (NYEL8810SMsg) sMsg);

            } else if ("NYEL8810Scrn00_Search".equals(screenAplID)) {
                if (NYEL8810CommonLogic.isValidParameter((NYEL8810CMsg) cMsg, this.getContextUserInfo().getUserId())){
                    doProcess_NYEL8810Scrn00_Search((NYEL8810CMsg) cMsg, (NYEL8810SMsg)sMsg);
                }
            } else if ("NYEL8810Scrn00_SelectAllApr".equals(screenAplID)) {
                doProcess_NYEL8810Scrn00_SelectAllApr((NYEL8810CMsg) cMsg, (NYEL8810SMsg)sMsg);

            } else if ("NYEL8810Scrn00_SelectAllFyi".equals(screenAplID)) {
                doProcess_NYEL8810Scrn00_SelectAllFyi((NYEL8810CMsg) cMsg, (NYEL8810SMsg)sMsg);

            } else if ("NYEL8810Scrn00_SelectAllRasg".equals(screenAplID)) {
                doProcess_NYEL8810Scrn00_SelectAllRasg((NYEL8810CMsg) cMsg, (NYEL8810SMsg)sMsg);

            } else if ("NYEL8810Scrn00_SelectAllCanc".equals(screenAplID)) {
                doProcess_NYEL8810Scrn00_SelectAllCanc((NYEL8810CMsg) cMsg, (NYEL8810SMsg)sMsg);

            } else if ("NYEL8810Scrn00_Get_AssigneeName".equals(screenAplID)) {
                doProcess_NYEL8810Scrn00_Get_AssigneeName((NYEL8810CMsg) cMsg, (NYEL8810SMsg)sMsg);

            } else if ("NYEL8810Scrn00_Get_UserName".equals(screenAplID)) {
                doProcess_NYEL8810Scrn00_Get_UserName((NYEL8810CMsg) cMsg, (NYEL8810SMsg)sMsg);

            } else if ("NYEL8810Scrn00_Get_FromGroupName".equals(screenAplID)) {
                doProcess_NYEL8810Scrn00_Get_FromGroupName((NYEL8810CMsg) cMsg, (NYEL8810SMsg)sMsg);

            } else if ("NYEL8810Scrn00_Get_ToGroupName".equals(screenAplID)) {
                doProcess_NYEL8810Scrn00_Get_ToGroupName((NYEL8810CMsg) cMsg, (NYEL8810SMsg)sMsg);

            } else if ("NYEL8810_NYEL8820".equals(screenAplID)) {
                doProcess_NYEL8810_NYEL8820((NYEL8810CMsg) cMsg, (NYEL8810SMsg)sMsg);

            } else if ("NYEL8810_NYEL8830".equals(screenAplID)) {
                doProcess_NYEL8810_NYEL8830((NYEL8810CMsg) cMsg, (NYEL8810SMsg)sMsg);

            } else if ("NYEL8810Scrn00_MoveWin_ProcessStatus".equals(screenAplID)) {
                doProcess_MoveWin_ProcessStatus((NYEL8810CMsg) cMsg, (NYEL8810SMsg)sMsg);

            } else if ("NYEL8810Scrn00_MoveWin_Detail".equals(screenAplID)) {
                doProcess_MoveWin_Detail((NYEL8810CMsg) cMsg, (NYEL8810SMsg)sMsg);

            } else if( "NYEL8810Scrn00_PageNext".equals( screenAplID ) ) {
                doProcess_NYEL8810Scrn00_PageNext( (NYEL8810CMsg)cMsg, (NYEL8810SMsg)sMsg );

            } else if( "NYEL8810Scrn00_PagePrev".equals( screenAplID ) ) {
                doProcess_NYEL8810Scrn00_PagePrev( (NYEL8810CMsg)cMsg, (NYEL8810SMsg)sMsg );

            } else if ("NYEL8810_NWAL1130".equals(screenAplID)) {
                doProcess_NYEL8810_NWAL1130((NYEL8810CMsg) cMsg, (NYEL8810SMsg)sMsg);

            } else if ("NYEL8810Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NYEL8810Scrn00_OnChangeSavedSearchOption((NYEL8810CMsg) cMsg, (NYEL8810SMsg) sMsg);

// QC#29682 DEL START 2018/12/18
//            } else if ("NYEL8810Scrn00_OnChangeProcessName".equals(screenAplID)) {
//                doProcess_NYEL8810Scrn00_OnChangeProcessName((NYEL8810CMsg) cMsg, (NYEL8810SMsg) sMsg);
// QC#29682 DEL END   2018/12/18

            } else if ("NYEL8810Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NYEL8810_INIT((NYEL8810CMsg) cMsg, (NYEL8810SMsg)sMsg);
                ZYPGUITableColumn.getColData((NYEL8810CMsg) cMsg, (NYEL8810SMsg) sMsg);

            // QC#21387 ADD START 2018/08/23
            } else if ("NYEL8810Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NYEL8810Scrn00_CMN_Download((NYEL8810CMsg) cMsg, (NYEL8810SMsg)sMsg);

            // QC#21387 ADD END 2018/08/23
            } else if ("NYEL8810Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NYEL8810Scrn00_TBLColumnSort((NYEL8810CMsg) cMsg, (NYEL8810SMsg)sMsg);
            }            
            sw.stop();
            S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess]End %s [%d]", screenAplID, sw.getElapsedMilliSec()));
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NYEL8810Scrn00_Get_AssigneeName(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(//
                bizMsg.xxWfAsgNm //
                , NYEL8810CommonLogic.getAssigneeNmFromS21Psn(bizMsg, bizMsg.xxWfAsgCd.getValue()));

    }

    private void doProcess_NYEL8810Scrn00_Get_FromGroupName(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg){
        if (S21StringUtil.isEmpty(bizMsg.wfUsrGrpNm_F.getValue())){
            // QC#23516 MOD START 2018/05/07
            //bizMsg.wfUsrGrpNm_F.setErrorInfo(1, ZZSM4110E, new String[] {"From" });
            bizMsg.wfUsrGrpNm_F.setErrorInfo(1, NYEM0016E, new String[] {"From" });
            // QC#23516 MOD END 2018/05/07
            return;
        }

        String loginUser = this.getContextUserInfo().getUserId();
        boolean isAdmin = NYXC880002.isAdministrator(loginUser);

        //User Group(From)
        NYEL8810CommonLogic.conv2FromNm(bizMsg, loginUser, isAdmin);
    }

    private void doProcess_NYEL8810Scrn00_Get_ToGroupName(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg){
        if (S21StringUtil.isEmpty(bizMsg.wfUsrGrpNm_T.getValue())){
            // QC#23516 MOD START 2018/05/07
//            bizMsg.wfUsrGrpNm_T.setErrorInfo(1, ZZSM4110E, new String[] {"To" });
            bizMsg.wfUsrGrpNm_T.setErrorInfo(1, NYEM0016E, new String[] {"To" });
            // QC#23516 MOD END 2018/05/07
            return;
        }

        String loginUser = this.getContextUserInfo().getUserId();
        boolean isAdmin = NYXC880002.isAdministrator(loginUser);

        //User Group(From)
        NYEL8810CommonLogic.conv2ToNm(bizMsg, loginUser, isAdmin);
    }

    private void doProcess_NYEL8810Scrn00_Get_UserName(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg) {
        String usrId = bizMsg.usrId.getValue();
        boolean isAdmin = NYXC880002.isAdministrator(this.getContextUserInfo().getUserId());

        if (S21StringUtil.isEmpty(usrId)) {
            usrId = this.getContextUserInfo().getUserId();
        }

        if (isAdmin) {
            ZYPEZDItemValueSetter.setValue(bizMsg.usrNm, NYEL8810CommonLogic.getUserNmFromS21PsnForAdmin(bizMsg, usrId));

        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.usrNm, NYEL8810CommonLogic.getUserNmFromS21Psn(bizMsg, usrId, this.getContextUserInfo().getUserId()));
        }

        // Switch Pull Down
        NYEL8810CommonLogic.initPullDown(bizMsg, usrId);

    }

    private void doProcess_NYEL8810_INIT(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg) {

        //
        // Possible Action
        //

        // Approve
        bizMsg.xxWfAprChkFlg.setValue(ZYPConstant.CHKBOX_ON_Y);

        // Input
        bizMsg.xxWfInpChkFlg.setValue(ZYPConstant.CHKBOX_ON_Y);

        // FYI
        bizMsg.xxWfFyiChkFlg.setValue(ZYPConstant.CHKBOX_ON_Y);

        // Sub Task
        bizMsg.xxWfSubChkFlg.setValue(ZYPConstant.FLG_OFF_N);

        // Rejected
        bizMsg.xxWfVisChkFlg.setValue(ZYPConstant.FLG_OFF_N);

        // Reassign
        bizMsg.xxWfRasgChkFlg.setValue(ZYPConstant.FLG_OFF_N);

        // Confirmed Data
        bizMsg.xxWfConfFlg.setValue(ZYPConstant.FLG_OFF_N);

        bizMsg.procStsCd.setValue(NYEL8810Constant.PROC_STS_ACTIVE);
        ZYPEZDItemValueSetter.setValue(bizMsg.usrId, this.getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(bizMsg.usrNm, NYEL8810CommonLogic.getUserNmFromS21Psn(bizMsg, this.getContextUserInfo().getUserId(), this.getContextUserInfo().getUserId()));
        //bizMsg.xxFromDt_SS.setValue(ZYPDateUtil.addBusinessDay(this.getGlobalCompanyCode(), ZYPDateUtil.getCurrentSystemTime("yyyyMMdd"), NYEL8810Constant.INIT_START_TS_RANGE * -1));

// QC#29682 ADD START 2018/12/18
        ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm, TASK_ID_APPROVAL);
// QC#29682 ADD END   2018/12/18

        NYEL8810CommonLogic.search(bizMsg, sMsg, this.getGlobalCompanyCode(), this.getContextUserInfo().getUserId());

        S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess]Start %s", "initPullDown"));
        S21StopWatch sw = new S21StopWatch();
        sw.start();
        NYEL8810CommonLogic.initPullDown(bizMsg, this.getContextUserInfo().getUserId());
        sw.stop();
        S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess]End %s [%d]", "initPullDown", sw.getElapsedMilliSec()));

        S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess]Start %s", "createSavedSearchOptionsPullDown"));
        sw = new S21StopWatch();
        sw.start();
        NYEL8810CommonLogic.createSavedSearchOptionsPullDown(bizMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        sw.stop();
        S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess]End %s [%d]", "createSavedSearchOptionsPullDown", sw.getElapsedMilliSec()));

        bizMsg.wfCmntTxt.clear();
    }

    private void doProcess_NYEL8810Scrn00_Search(NYEL8810CMsg cMsg, NYEL8810SMsg sMsg) {
        NYEL8810CommonLogic.search(cMsg, sMsg, this.getGlobalCompanyCode(), this.getContextUserInfo().getUserId(), false);
        cMsg.wfCmntTxt.clear();
    }

    private void doProcess_NYEL8810Scrn00_SelectAllApr(NYEL8810CMsg bizMsg, EZDSMsg sMsg) {
        int max = bizMsg.A.getValidCount();

        for (int cnt = 0; cnt < max; cnt++) {
            //if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(cnt).xxWfAprChkVisFlg_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cnt).xxWfAprChkFlg_A, bizMsg.xxWfAprAllChkFlg.getValue());
            //}
        }
    }

    private void doProcess_NYEL8810Scrn00_SelectAllFyi(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg) {
        int max = bizMsg.A.getValidCount();

        for (int cnt = 0; cnt < max; cnt++) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(cnt).xxWfFyiChkVisFlg_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cnt).xxWfFyiChkFlg_A, bizMsg.xxWfFyiAllChkFlg.getValue());
            }
        }
    }

    private void doProcess_NYEL8810Scrn00_SelectAllRasg(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg) {
        int max = bizMsg.A.getValidCount();

        for (int cnt = 0; cnt < max; cnt++) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(cnt).xxWfRasgChkVisFlg_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cnt).xxWfRasgChkFlg_A, bizMsg.xxWfRasgAllChkFlg.getValue());
            }
        }
    }

    private void doProcess_NYEL8810Scrn00_SelectAllCanc(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg) {
        int max = bizMsg.A.getValidCount();

        for (int cnt = 0; cnt < max; cnt++) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(cnt).cancFlg_VF.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cnt).cancFlg_A, bizMsg.cancFlg_AL.getValue());
            }
        }
    }

    private void doProcess_NYEL8810_NYEL8820(NYEL8810CMsg cMsg, NYEL8810SMsg sMsg) {
        NYEL8810CommonLogic.search(cMsg, sMsg, this.getGlobalCompanyCode(), this.getContextUserInfo().getUserId());
    }

    private void doProcess_NYEL8810_NYEL8830(NYEL8810CMsg cMsg, NYEL8810SMsg sMsg) {
        NYEL8810CommonLogic.search(cMsg, sMsg, this.getGlobalCompanyCode(), this.getContextUserInfo().getUserId());
    }

    private void doProcess_NYEL8810_NWAL1130(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg) {
        NYEL8810CommonLogic.initPullDown(bizMsg, bizMsg.usrId.getValue());
    }

    private void doProcess_NYEL8810Scrn00_OnChangeSavedSearchOption(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            NYEL8810CommonLogic.callNszc0330forSearchOption(bizMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
// QC#29682 DEL START 2018/12/18
//            NYEL8810CommonLogic.initTaskNamePullDown(bizMsg, getContextUserInfo().getUserId());
// QC#29682 DEL END   2018/12/18
            }
    }

// QC#29682 DEL START 2018/12/18
//    private void doProcess_NYEL8810Scrn00_OnChangeProcessName(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg) {
//        NYEL8810CommonLogic.initTaskNamePullDown(bizMsg, getContextUserInfo().getUserId());
//    }
// QC#29682 DEL END   2018/12/18

    private void doProcess_MoveWin_ProcessStatus(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg) {
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxWfAprChkFlg_A", ZYPConstant.CHKBOX_ON_Y);
        
        if (selectedRows.isEmpty() || (selectedRows.size()  >= 2)) {
            int max = bizMsg.A.getValidCount();
            for (int i = 0; i < max; i++) {
                bizMsg.A.no(i).xxWfAprChkFlg_A.setErrorInfo(1, NYEM0012E);
            }
            return;
        }
    }

    private void doProcess_MoveWin_Detail(NYEL8810CMsg bizMsg, EZDSMsg sMsg) {
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxWfAprChkFlg_A", ZYPConstant.CHKBOX_ON_Y);

        if (selectedRows.isEmpty()) {
            int max = bizMsg.A.getValidCount();
            for (int i = 0; i < max; i++) {
                bizMsg.A.no(i).xxWfAprChkFlg_A.setErrorInfo(1, NYEM0010E);
            }
            return;
        }
    }

    private void doProcess_NYEL8810Scrn00_PageNext( NYEL8810CMsg cMsg, NYEL8810SMsg sMsg ) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i              = pagenationFrom;
        for( ; i < pagenationFrom + cMsg.A.length(); i++ ) {
            if( i < sMsg.A.getValidCount() ) {
                EZDMsg.copy( sMsg.A.no( i ), null, cMsg.A.no( i - pagenationFrom ), null );
            } else {
                break;
            }
        }
        cMsg.A.setValidCount( i - pagenationFrom );

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue( pagenationFrom + 1 );
        cMsg.xxPageShowToNum.setValue( pagenationFrom + cMsg.A.getValidCount() );

        //All Check Flg
        cMsg.xxWfAprAllChkFlg.setValue(ZYPConstant.FLG_OFF_N);
    }
    
    private void doProcess_NYEL8810Scrn00_PagePrev( NYEL8810CMsg cMsg, NYEL8810SMsg sMsg ) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i              = pagenationFrom;
        for( ; i < pagenationFrom + cMsg.A.length(); i++ ) {
            if( i < sMsg.A.getValidCount() ) {
                EZDMsg.copy( sMsg.A.no( i ), null, cMsg.A.no( i - pagenationFrom ), null );
            } else {
                break;
            }
        }
        cMsg.A.setValidCount( i - pagenationFrom );

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue( pagenationFrom );
        cMsg.xxPageShowToNum.setValue( pagenationFrom + cMsg.A.getValidCount() - 1 );

        //All Check Flg
        cMsg.xxWfAprAllChkFlg.setValue(ZYPConstant.FLG_OFF_N);
    }

    // QC#21387 ADD START 2018/08/23
    private void doProcess_NYEL8810Scrn00_CMN_Download( NYEL8810CMsg cMsg, NYEL8810SMsg sMsg ) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NYEL8810Constant.MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NYXC880002Query.getInstance().getClass());

            // create csv file, parameters
            String ssmId = "";
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm( "Worklist" ), ".csv");
            Map<String, Object> ssMParam = NYEL8810CommonLogic.getParams(cMsg, this.getGlobalCompanyCode(), this.getContextUserInfo().getUserId());
            // QC#28705 ADD START 2018/10/10
            if (ssMParam == null) {
                return;
            }
            // QC#28705 ADD END   2018/10/10
            ssMParam.put("curDt", S21SystemDate.getFWCurrentSystemTime("yyyyMMddHHmm"));
            ssMParam.put("maxRow", NYEL8810Constant.LIMIT_DL_ROWNUM + 1);

            if (ssMParam.containsKey("Administrator") && !ssMParam.containsKey("OtherUser")){
                ssmId = "getWorklistForAdminForCSVDownload";
            } else {
                ssmId = "getWorklistForCSVDownload";
            }

            ps = ssmLLClient.createPreparedStatement(ssmId, ssMParam, execParam);
            rs = ps.executeQuery();
            NYEL8810CommonLogic.writeCsvFile(cMsg, rs);

        } catch (SQLException e) {

            throw new S21AbendException(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }
    // QC#21387 ADD END 2018/08/23
    
    private void doProcess_NYEL8810Scrn00_TBLColumnSort( NYEL8810CMsg cMsg, NYEL8810SMsg sMsg ) {

        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        // execute column sort function
        S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

        // copy(SMsg -> CMsg)
        int i = 0;

        for (; i < sMsg.A.getValidCount(); i++) {

            if (i == cMsg.A.length()) {

                break;
            }

            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
        }

        cMsg.A.setValidCount(i);

        //set page no 
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
    }
}
