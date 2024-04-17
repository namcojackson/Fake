package com.canon.cusa.s21.batch.ZZW.ZZWB007001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.common.util.S21NwfDateUtil;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.model.S21NwfWorkItem.ACCESS;
import com.canon.cusa.s21.framework.nwf.core.model.S21NwfWorkItem.STATUS;
import com.canon.cusa.s21.framework.nwf.core.model.S21NwfWorkItem.TYPE;
import com.canon.cusa.s21.framework.nwf.core.model.impl.S21NwfWorkItemImpl;
import com.canon.cusa.s21.framework.nwf.core.process.impl.S21NwfProcessImpl;
import com.canon.cusa.s21.framework.nwf.core.user.S21NwfUserDelegateContainer;
import com.canon.cusa.s21.framework.nwf.core.user.S21NwfUserRole;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.nwf.util.common.impl.S21NwfUtilSystemOnlineContext;

/**
 *<pre>
 * ZZWB007001
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/11   Fujitsu         Q10627          Update          QC#21139
 * 2018/09/19   Fujitsu         Q10627          Update          QC#21139
 *</pre>
 */
public class ZZWB007001 extends S21BatchMain {

    private static final S21Logger logger = S21LoggerFactory.getLogger(ZZWB007001.class);

// 2018/09/19 DEL START QC#21139
//    private static final String ADMINISTRATOR = "Administrator";
// 2018/09/19 DEL END   QC#21139

    private static final int COMMITCOUNT = 1;

    private int normalRecCnt;

// 2018/09/19 DEL START QC#21139
//    private int totalRecCnt;
// 2018/09/19 DEL END   QC#21139

    private Boolean errorFlg;

    private int maxTmsgSize;

// 2018/09/19 ADD START QC#21139
    private int errorRecCnt;

    @Override
    protected void initRoutine() {
// 2018/09/19 ADD START QC#21139
        this.errorRecCnt = 0;
// 2018/09/19 ADD END   QC#21139

        this.normalRecCnt = 0;
// 2018/09/19 DEL START QC#21139
//        this.totalRecCnt = 0;
// 2018/09/19 DEL END   QC#21139

        this.errorFlg = false;
        this.maxTmsgSize = COMMITCOUNT;
    }

    @Override
    protected void mainRoutine() {

        if (logger.isDebugEnabled()) {
            logger.debug("$$ New Workflow Watch Process Start");
        }

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfUtilSystemOnlineContext context;
        try {
            context = (S21NwfUtilSystemOnlineContext)factory.getSystemContex();
        } catch (S21NwfSystemException e1) {
            logger.error("getSystemContex error happened.", e1);
            errorFlg = true;
            return;
        }

        // Now
        String now = S21NwfDateUtil.getSystemTimestamp();

        if (logger.isDebugEnabled()) {
            logger.debug("$$ Check Active Process Start");
        }

        List<BigDecimal> list = null;
        try {
            list = context.getProcessWatchTimer();
        } catch (S21NwfSystemException e) {
            logger.error("GetProcessWatchTimer error happened.", e);
            errorFlg = true;
            return;
        }

        if (list == null || list.size() == 0) {
            logger.error("There is no task to be notified.");
            errorFlg = false;
            return;
        }

        int tmsgSize = list.size();
//        this.totalRecCnt = tmsgSize;

        int tmsgTmpSize;
        int fromIdx = 0;
        while (tmsgSize > 0) {

            if (tmsgSize > maxTmsgSize) {
                tmsgTmpSize = maxTmsgSize;
            }
            else {
                tmsgTmpSize = tmsgSize;
            }

            List<BigDecimal> listTmp =  list.subList(fromIdx, fromIdx + tmsgTmpSize);

            for (BigDecimal wfProcPk : listTmp){
// 2018/09/19 DEL START QC#21139
//                Boolean isCountInc = true;
// 2018/09/19 DEL END   QC#21139
                try{
                    S21NwfProcessImpl procImpl;

                    try {
                        procImpl = (S21NwfProcessImpl)context.getProcess(wfProcPk);
                    } catch (S21NwfSystemException e) {
                        logger.error("Create S21NwfProcess error happened.", e);
                        errorFlg = true;
// 2018/09/19 DEL START QC#21139
//                        isCountInc = false;
// 2018/09/19 DEL END   QC#21139
                        continue;
                    }

                    List<S21NwfWorkItemImpl> tasks = procImpl.getTimeoutTask(now);

                    if (tasks.isEmpty()) {
                        logger.warn(String.format("No timeout task found. WF_PROC_PK:%s", wfProcPk.toPlainString()));
                        continue;
                    }

                    for (S21NwfWorkItemImpl task : tasks) {
                        List<String> allNotifyUsers = new ArrayList<String>();
                        try {
                            // current Approvers
                            List<String> tmpAllNotifyUsers = getCurrentApprovers(task);

                            // current Delegate Users
                            List<String> currentDlgtUsers = S21NwfUserDelegateContainer.getInstance().getDelegateToUsersByFromUsers(
                                                         task.getParentProcess().getProcessName(), tmpAllNotifyUsers);

                            // next Approvers
                            List<String> nextApprovers = getNextApprovers(task);

                            // next Delegate Users
                            List<String> nextDlgtUsers = S21NwfUserDelegateContainer.getInstance().getDelegateToUsersByFromUsers(
                                                         task.getParentProcess().getProcessName(), nextApprovers);

                            tmpAllNotifyUsers.addAll(currentDlgtUsers);
                            tmpAllNotifyUsers.addAll(nextApprovers);
                            tmpAllNotifyUsers.addAll(nextDlgtUsers);

                            allNotifyUsers = new ArrayList<String>(new HashSet<String>(tmpAllNotifyUsers));
                        }
                        catch (S21NwfSystemException e) {
                            logger.error("S21NwfBizException happened.", e);
                            this.errorFlg = true;
                            continue;
                        }

                        Map<String, String> apvlList = new HashMap<String, String>();
// 2018/09/11 DEL START QC#21139
//                        Map<String, String> superiorList = new HashMap<String, String>();
//                        List<S21NwfUserRole> users = task.getAllApprovers();
//                        StringBuilder sb = new StringBuilder();
// 2018/09/11 DEL END   QC#21139

// 2018/09/19 UPD START QC#21139
                        List<String> allGeneralUsers = NYXC880002.getGeneralUsers(allNotifyUsers);
                        for (String notifyUser: allGeneralUsers) {
                           apvlList.put(notifyUser, notifyUser);
                        }

//                        for (String notifyUser : allNotifyUsers) {
//
//                            if (ADMINISTRATOR.equals(notifyUser)) {
//                                continue;
//                            }
//
//                            apvlList.put(notifyUser, notifyUser);
//                        }
// 2018/09/19 UPD END   QC#21139

// 2018/09/11 DEL START QC#21139
//                        List<String> approvers = new ArrayList<String>(apvlList.values());
//
//                        for (String userId : approvers) {
//
//                            if (superiorList.containsKey(userId)) {
//                                continue;
//                            }
//
//                            List<String> superiorIds = getSuperior(userId);
//
//                            for (String superiorId : superiorIds){
//                                if (S21StringUtil.isNotEmpty(superiorId)) {
//                                    Boolean flg = true;
//                                    if (apvlList.containsKey(superiorId)){
//                                        flg = false;
//                                        logger.warn(String.format("Approvers contains Superior Id [User ID=%s Superior ID=%s]", userId, superiorId));
//                                    }
//
//                                    if (flg && !superiorList.containsValue(superiorId)){
//                                        superiorList.put(userId, superiorId);
//                                    }
//                                } else {
//                                    sb.append(String.format("%s ", userId));
//                                }
//                            }
//                        }
// 2018/09/11 DEL END   QC#21139

                        Boolean isCommit = true;

                        try {
                            if (apvlList.isEmpty()) {
// 2018/09/11 DEL START QC#21139
//                                if (sb.length() > 0){
//                                    logger.warn(String.format("No Superior found[User ID=%s]", sb.toString()));
//                                }
// 2018/09/11 DEL END   QC#21139
                                logger.warn(String.format("Escalation skip[WF_WRK_ITEM_PK=%s]", task.getWorkItemUId().toPlainString()));
                                isCommit = false;
// 2018/09/19 DEL START QC#21139
//                                isCountInc = false;
// 2018/09/19 DEL END   QC#21139
                                continue;
                            }

                            task.timeoutAction(now, apvlList);
                            procImpl.save();

                        } catch (S21NwfBizException e) {
                            logger.error("S21NwfBizException happened.", e);
                            isCommit = false;
                            this.errorFlg = true;
// 2018/09/19 ADD START QC#21139
                            this.errorRecCnt += apvlList.size();
// 2018/09/19 ADD END   QC#21139
                            continue;
                        } catch (S21NwfException e) {
                            logger.error("S21NwfException happened.", e);
                            isCommit = false;
                            this.errorFlg = true;
// 2018/09/19 ADD START QC#21139
                            this.errorRecCnt += apvlList.size();
// 2018/09/19 ADD END   QC#21139
                            continue;
                        } finally {
                            if (isCommit) {
                                this.commit();
// 2018/09/19 ADD START QC#21139
                                this.normalRecCnt += apvlList.size();
// 2018/09/19 ADD END   QC#21139
                                 
                            } else {
                                this.rollback();
// 2018/09/19 DEL START QC#21139
//                                isCountInc = false;
// 2018/09/19 DEL END   QC#21139
                            }
                        }
                    }
                } finally{
// 2018/09/19 DEL START QC#21139
//                    if (isCountInc){
//                        this.normalRecCnt++;
//                    }
// 2018/09/19 DEL END   QC#21139
                }
            }

            tmsgSize = tmsgSize - tmsgTmpSize;
            fromIdx = fromIdx + tmsgTmpSize;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("$$ Check Active Process End");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("$$ New Workflow Watch Process End");
            System.out.println("$$ New Workflow Watch Process End");
        }
    }

// 2018/09/11 ADD START QC#21139
    /**
     * getCurrentApprovers
     * @param wi
     * @return
     * @throws S21NwfSystemException
     */
    List<String> getCurrentApprovers(S21NwfWorkItemImpl wi) throws S21NwfSystemException {
        List<String> approvers = new ArrayList<String>();
        if (wi == null) {
            return approvers;
        }

        List<S21NwfUserRole> approverUserRoles = wi.getAllApprovers();
        for (S21NwfUserRole approverUserRole: approverUserRoles) {
            List<ACCESS> accesses = approverUserRole.getAccesses();
            if (accesses.contains(ACCESS.APPROVE_SUB)) {
                continue;
            }
            String user = approverUserRole.getUserRole();
            if (user != null && !user.isEmpty()) {
                approvers.add(user);
            }
        }
        return approvers;
    }

    /**
     * getNextApprovers
     * @param wi
     * @return
     * @throws S21NwfSystemException
     */
    List<String> getNextApprovers(S21NwfWorkItemImpl wi) throws S21NwfSystemException {
        List<S21NwfUserRole> approverUserRoles = new ArrayList<S21NwfUserRole>();
        List<String> approvers = new ArrayList<String>();
        if (wi == null) {
            return approvers;
        }

        while (wi != null && wi.getType().equals(TYPE.TASK) && !wi.getStatus().equals(STATUS.RUN)) {
            wi = wi.next();
        }

        wi = wi.next();
        if (wi != null) {
            approverUserRoles = wi.getAllApprovers();
        }

        for (S21NwfUserRole approverUserRole : approverUserRoles) {
            List<ACCESS> accesses = approverUserRole.getAccesses();
            if (accesses.contains(ACCESS.APPROVE_SUB)) {
                continue;
            }

            String user = approverUserRole.getUserRole();
            if (user != null && !user.isEmpty()) {
                approvers.add(user);
            }
        }
        return approvers;
    }
// 2018/09/11 ADD END   QC#21139

    @Override
    protected void termRoutine() {

        TERM_CD ret = TERM_CD.NORMAL_END;

        if (this.errorFlg) {
            ret = TERM_CD.ABNORMAL_END;
        }
// 2018/09/19 UPD START QC#21139
//        this.setRecordCount(this.normalRecCnt, this.totalRecCnt - this.normalRecCnt, this.totalRecCnt);
      this.setRecordCount(this.normalRecCnt, this.errorRecCnt, this.normalRecCnt + this.errorRecCnt);
// 2018/09/19 UPD END   QC#21139
        this.setTermState(ret);
    }

    public static void main(String[] args) {
        new ZZWB007001().executeBatch(ZZWB007001.class.getSimpleName());
    }

// 2018/09/11 DEL START QC#21139
//    private List<String> getSuperior(String userId) {
//        List<String> ret = new ArrayList<String>();
//
//        S21SsmEZDResult result = ZZWB007001Query.getInstance().getSuperior(userId);
//
//        if (result.isCodeNormal()) {
//            List resultList = (List) result.getResultObject();
//            if (resultList.size() > 0) {
//                Map map = (Map) resultList.get(0);
//                ret.add((String) map.get("HR_PSN_INTFC_SUPV_ID"));
//            }
//        }
//
//        return ret;
//    }
// 2018/09/11 DEL END   QC#21139

}
