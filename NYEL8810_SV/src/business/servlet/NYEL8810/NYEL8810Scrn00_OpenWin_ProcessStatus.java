/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8810;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

// import com.canon.cusa.s21.framework.mail.S21Mail;
// import com.canon.cusa.s21.framework.mail.S21MailAddress;
import business.blap.NYEL8810.NYEL8810CMsg;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.model.S21NwfWorkItem;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.user.S21NwfUserRole;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizConditionValue;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizHistWorkItem;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizProcess;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizWorkItem;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8810Scrn00_OpenWin_ProcessStatus
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/14   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8810Scrn00_OpenWin_ProcessStatus extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;
        NYEL8810CMsg bizMsg = (NYEL8810CMsg) cMsg;

        // TODO [Template]
        // Clear Params
        // scrnMsg.XXX.clear();
        //
        // Set Value
        // scrnMsg.XXX.setValue(scrnMsg.YYY.getValue());
        //
        // Set Params
        // Object[] params = new Object[n]; //FIXME n:number of
        // parameters
        // params[0] = scrnMsg.xxxCd;
        // params[1] = scrnMsg.P.no(1).xxPopPrm;
        //
        // setArgForSubScreen(params);

        // which button was selected from spread
        // int selectedIndex = getButtonSelectNumber();
        /*
         * int selectedIndex = 0; Object[] params = new Object[2];
         * params[0] = scrnMsg.A.no(selectedIndex).wfProcNm_A;
         * scrnMsg.
         * A.no(selectedIndex).wfBizAttrbTxt_A1.setValue("TESTDOC");
         * params[1] = scrnMsg.A.no(selectedIndex).wfBizAttrbTxt_A1;
         * setArgForSubScreen(params);
         */

        /*
        int selectedIndex = 0; Object[] params = new Object[2];
        scrnMsg.A.no(selectedIndex).wfProcNm_A.setValue("NWAL1540");
        params[0] = scrnMsg.A.no(selectedIndex).wfProcNm_A;
        scrnMsg.A.no(selectedIndex).wfBizAttrbTxt_A1.setValue("T-Test888888");
        params[1] = scrnMsg.A.no(selectedIndex).wfBizAttrbTxt_A1;
        setArgForSubScreen(params);
        */

        Object[] params = new Object[3];
        
        //params[0] ="NWWP0008Test1";
        
        //params[0] ="0";
        //params[1] ="TEST_WF554";
        //params[1] ="TEST_WF6";
        
        //params[1] ="VP001275";
        //params[1] ="REASGTESTTest4";
        //
        //params[1] ="";
        //params[2] ="101520";
        //params[2] ="ProcessGroupB";
        //params[2] ="000263";
        
        //setArgForSubScreen(params);
        //setJumpTransition("NYEL8850");

        
        
        /*
         * S21NwfContextFactory factory = new
         * S21NwfUtilContextFactory(); try { S21NwfContext context =
         * factory.getContex(); //S21NwfProcess proc =
         * context.getProcessForBiz(new BigDecimal(777));
         * //List<S21NwfProcess> procs =
         * context.getProcessForBiz("NWAL1540", "777");
         * List<S21NwfProcess> procs =
         * context.getProcessForBiz("NWAL1540", "T-Test10");
         * //System.out.println(proc.getProcessId());
         * for(S21NwfProcess proc: procs){ S21NwfUtilBizProcess p =
         * (S21NwfUtilBizProcess)proc; System.out.println(
         * p.toString()); } //context.ge } catch
         * (S21NwfSystemException e) { // TODO 自動生成された catch ブロック
         * e.printStackTrace(); }
         */
        
        
    
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();

        try {
            S21NwfContext context = factory.getContex();

            // Processインスタンス取得
            List<S21NwfProcess> procs = context.getProcessForBiz("NSWP0002", "M17450246660320161130");

            for (S21NwfProcess proc : procs) {
                S21NwfUtilBizProcess p = (S21NwfUtilBizProcess) proc;

                // Processに紐付くTask一覧取得
                List<S21NwfUtilBizWorkItem> tasks = p.getTasks();

                for (S21NwfUtilBizWorkItem wi : tasks) {
                    // 承認タスクを検索する。
                    if (wi.isApprovable()) {

                        // 承認者
                        List<S21NwfUserRole> users = wi.getToUsers();

                        for (S21NwfUserRole user : users) {
                            user.getUserRole(); // <-QXXXXXのIDを戻す為それを変換してください。
                            
                            String hoge = user.getGroup();
                            if (S21StringUtil.isEmpty(hoge)){
                                //本来の承認者
                            } else {
                                //上長
                            }
                        }

                        for(String group : wi.getGroups()){
                            //グループ名称
                            //group;
                        }
                        
                        //condition
                        S21NwfUtilBizConditionValue condition = wi.getCondition();
                        
                        if (condition != null){
                            //COND_NUM_1
                            condition.getCondNum1();
                        }
                        
                        // Approval Date
                        wi.getEndTimestamp();

                        // Comments
                        wi.getDescription();

                        // Actual Approver
                        wi.getActOpUser();
                    }
                }
            }
        } catch (S21NwfSystemException e) {
            e.printStackTrace();
            bizMsg.setMessageInfo(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
        }

        /*
         * String globalCompanyCode = "NWFT"; String address =
         * "miztakahashi_consultant@cusa.canon.com"; // メール部品の利用方法
         * S21Mail mail = new S21Mail(globalCompanyCode); //
         * 送信元（FROM）の設定（メールグループを利用） //S21MailGroup groupFrom = new
         * S21MailGroup(globalCompanyCode, "FROM0004");
         * //List<S21MailAddress> addrFromList =
         * groupFrom.getMailAddress(); S21MailAddress addr = new
         * S21MailAddress(globalCompanyCode, address);
         * mail.setFromAddress(addr); mail.setToAddress(addr);
         * mail.sendMail();
         */
        
        /*
        String globalCompanyCode = "ADB";
        //String globalCompanyCode = "NWFT";
        String address = "miztakahashi_consultant@cusa.canon.com"; // メール部品の利用方法
        S21Mail mail = new S21Mail(globalCompanyCode);
        mail.setText("testmail");
        //送信元（FROM）の設定（メールグループを利用）
        //S21MailGroup groupFrom = new S21MailGroup(globalCompanyCode, "FROM0004");
        //List<S21MailAddress> addrFromList =groupFrom.getMailAddress();
        S21MailAddress addr = new S21MailAddress(globalCompanyCode, address);
        mail.setFromAddress(addr); mail.setToAddress(addr);
        mail.sendMail();
        */
    }
}
