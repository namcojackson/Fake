/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8860.common;

import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0003W;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ZZSM4110E;

import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;

import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002;
import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002Query;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WF_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import business.blap.NYEL8860.NYEL8860CMsg;
import business.blap.NYEL8860.NYEL8860SMsg;

/**
 *<pre>
 * NYEL8850CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/14   Fujitsu         Q09079          Create          N/A
 *</pre>
 */

public class NYEL8860CommonLogic {

    public static void initPullDown(NYEL8860CMsg bizMsg){
        ZYPCodeDataUtil.createPulldownList(WF_GRP_TP.class.getSimpleName(), bizMsg.xxGrpFlg_L, bizMsg.xxCondNm_D);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxGrpFlg, WF_GRP_TP.ALL);
        
    }
    
    public static void search(NYEL8860CMsg bizMsg, NYEL8860SMsg sMsg, String usrId, String glblCmpyCd){
        String eventNm = bizMsg.xxScrEventNm.getValue();
        ZYPTableUtil.clear(sMsg.A);

        if (S21StringUtil.isEmpty(eventNm)){

        } else if (eventNm.equals("OpenWin_FromUsrGrp")){
            conv2FromNm(bizMsg, sMsg, usrId, glblCmpyCd);
        } else if (eventNm.equals("OpenWin_ToUsrGrp")){
            conv2ToNm(bizMsg, sMsg, usrId, glblCmpyCd);
        }

        if (sMsg.A.getValidCount() > 0){
            int i = 0;
            for( ; i < sMsg.A.getValidCount(); i++ ) {
                if( i == bizMsg.A.length() ) {
                    break;
                }
                EZDMsg.copy( sMsg.A.no( i ), null, bizMsg.A.no( i ), null );
            }
            bizMsg.A.setValidCount( i );
            bizMsg.xxPageShowFromNum.setValue( 1 );
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        } else {
            bizMsg.A.clear();
            bizMsg.A.setValidCount(0);
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
        }
    }

    private static void conv2FromNm(NYEL8860CMsg bizMsg, NYEL8860SMsg sMsg, String usrId, String glblCmpyCd){
        boolean isAdmin = NYXC880002.isAdministrator(usrId);

        if ("0".equals(bizMsg.xxGrpFlg.getValue())){
            //ALL
            if (isAdmin){
                conv2FromGrpNm(bizMsg, sMsg, "", glblCmpyCd);
            } else{
                conv2FromGrpNm(bizMsg, sMsg, usrId, glblCmpyCd);
            }

            convUser(bizMsg, sMsg, glblCmpyCd);
        } else if ("1".equals(bizMsg.xxGrpFlg.getValue())){
            //GROUP
            if (isAdmin){
                conv2FromGrpNm(bizMsg, sMsg, "", glblCmpyCd);
            } else{
                conv2FromGrpNm(bizMsg, sMsg, usrId, glblCmpyCd);
            }
        } else if ("2".equals(bizMsg.xxGrpFlg.getValue())){
            //USER
            convUser(bizMsg, sMsg, glblCmpyCd);
        }

        if (S21StringUtil.isNotEmpty(bizMsg.wfUsrGrpNm.getValue()) || S21StringUtil.isNotEmpty(bizMsg.xxDtlNm.getValue())){
            if (sMsg.A.getValidCount() <= 0){
                if (S21StringUtil.isNotEmpty(bizMsg.wfUsrGrpNm.getValue())){
                    bizMsg.wfUsrGrpNm.setErrorInfo(1, ZZSM4110E, new String[]{"Id"});
                }

                if (S21StringUtil.isNotEmpty(bizMsg.xxDtlNm.getValue())){
                    bizMsg.xxDtlNm.setErrorInfo(1, ZZSM4110E, new String[]{"Name"});
                }
            }
        }
    }

    /**
     * convUser
     * @param bizMsg
     */
    private static void convUser(NYEL8860CMsg bizMsg, NYEL8860SMsg sMsg, String glblCmpyCd){
        String usrId = bizMsg.wfUsrGrpNm.getValue();
        String usrNm = bizMsg.xxDtlNm.getValue();

        S21SsmEZDResult result = NYXC880002Query.getInstance().getPsnNm(usrId.toUpperCase(), usrNm);

        if (result.isCodeNormal()) {
            toMsg(bizMsg, sMsg, result, WF_GRP_TP.USER, glblCmpyCd);
        }
    }

    private static void toMsg(NYEL8860CMsg bizMsg, NYEL8860SMsg sMsg, S21SsmEZDResult result, String type, String glblCmpyCd){
        List resultList = (List) result.getResultObject();
        int bizMax = resultList.size();
        int lestSize = sMsg.A.length() - sMsg.A.getValidCount();
        int max = bizMax;

        if (lestSize < bizMax) {
            bizMsg.setMessageInfo(NYEM0003W);
            max = lestSize;
        }

        int cnt = sMsg.A.getValidCount();

        for (int index = 0; index < max; index++){
            Map map = (Map) resultList.get(index);

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxCondNm_A, ZYPCodeDataUtil.getName(WF_GRP_TP.class, glblCmpyCd, type));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxGrpFlg_A, type);
            
            if (type.equals(WF_GRP_TP.USER)){
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfUsrGrpNm_A, (String) map.get("USR_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxDtlNm_A, (String) map.get("FULL_PSN_NM"));
            } else {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfUsrGrpNm_A, (String) map.get("WF_USR_GRP_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxDtlNm_A, (String) map.get("WF_USR_GRP_DESC_TXT"));
            }
            cnt++;
        }

        if (lestSize < bizMax) {
            sMsg.A.setValidCount(sMsg.A.length());
        } else {
            sMsg.A.setValidCount(cnt);
        }
    }

    /**
     * conv2FromGrpNm
     * @param usrId
     * @param wfUsrGrpNm
     * @return
     */
    private static void conv2FromGrpNm(NYEL8860CMsg bizMsg, NYEL8860SMsg sMsg, String usrId, String glblCmpyCd){
        String usrGrpNm = bizMsg.wfUsrGrpNm.getValue();
        String dtlNm = bizMsg.xxDtlNm.getValue();

        S21SsmEZDResult result = NYXC880002Query.getInstance().getFromUsrGrpList(usrId, usrGrpNm, dtlNm);

        if (result.isCodeNormal()) {
            toMsg(bizMsg, sMsg, result, WF_GRP_TP.GROUP, glblCmpyCd);
        }
    }

    private static void conv2ToNm(NYEL8860CMsg bizMsg, NYEL8860SMsg sMsg, String usrId, String glblCmpyCd){
        boolean isAdmin = NYXC880002.isAdministrator(usrId);
        
        if ("0".equals(bizMsg.xxGrpFlg.getValue())){
            //ALL
            if (isAdmin){
                conv2ToGrpNm(bizMsg, sMsg, "", glblCmpyCd);
            } else{
                conv2ToGrpNm(bizMsg, sMsg, usrId, glblCmpyCd);
            }

            convUser(bizMsg, sMsg, glblCmpyCd);
        } else if ("1".equals(bizMsg.xxGrpFlg.getValue())){
            //GROUP
            if (isAdmin){
                conv2ToGrpNm(bizMsg, sMsg, "", glblCmpyCd);
            } else{
                conv2ToGrpNm(bizMsg, sMsg, usrId, glblCmpyCd);
            }
        } else if ("2".equals(bizMsg.xxGrpFlg.getValue())){
            //USER
            convUser(bizMsg, sMsg, glblCmpyCd);
        }

        if (S21StringUtil.isNotEmpty(bizMsg.wfUsrGrpNm.getValue()) || S21StringUtil.isNotEmpty(bizMsg.xxDtlNm.getValue())){
            if (sMsg.A.getValidCount() <= 0){
                if (S21StringUtil.isNotEmpty(bizMsg.wfUsrGrpNm.getValue())){
                    bizMsg.wfUsrGrpNm.setErrorInfo(1, ZZSM4110E, new String[]{"Id"});
                }

                if (S21StringUtil.isNotEmpty(bizMsg.xxDtlNm.getValue())){
                    bizMsg.xxDtlNm.setErrorInfo(1, ZZSM4110E, new String[]{"Name"});
                }
            }
        }
    }

    /**
     * conv2ToGrpNm
     * @param bizMsg
     * @param usrId
     */
    private static void conv2ToGrpNm(NYEL8860CMsg bizMsg, NYEL8860SMsg sMsg, String usrId, String glblCmpyCd){
        String usrGrpNm = bizMsg.wfUsrGrpNm.getValue();
        String dtlNm = bizMsg.xxDtlNm.getValue();

        S21SsmEZDResult result = NYXC880002Query.getInstance().getToUsrGrpList(usrId, usrGrpNm, dtlNm);

        if (result.isCodeNormal()) {
            toMsg(bizMsg, sMsg, result, WF_GRP_TP.GROUP, glblCmpyCd);
        }
    }
}
