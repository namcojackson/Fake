package com.canon.cusa.s21.framework.generictable.fw;

import java.sql.SQLException;

import com.canon.cusa.s21.framework.log.S21SyslogInfoContext;
import com.canon.cusa.s21.framework.log.S21SyslogInfoContextHolder;

import parts.common.EZDAbendException;
import parts.common.EZDAccountInfoWriter;
import parts.common.EZDBizPerformanceCounter;
import parts.common.EZDCMsg;
import parts.common.EZDLog;
import parts.common.EZDMsgCommons;
import parts.common.EZDStringUtil;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDDBCICarrier;
import tools.ezdcommon.EZDMessageFunc;

/**
 * コードメンテナンス用ビジネス層ベースクラス。<br>
 * トランザクション制御を行う。<br>
 * @author Administrator
 */
public abstract class S21NEBusiness {

    /** LOGLEVEL_1 */
    private static final boolean LOGLEVEL_1 = EZDLog.getLogLevel() >= 1;

    /** LOGLEVEL_2 */
    private static final boolean LOGLEVEL_2 = EZDLog.getLogLevel() >= 2;

    /** LOGLEVEL_3 */
    private static final boolean LOGLEVEL_3 = EZDLog.getLogLevel() >= 3;

    // [ADD] 2011.02.10 T.Tsuji Performance counter START
    private EZDBizPerformanceCounter bizProcCounter;
    // [ADD] 2011.02.10 T.Tsuji Performance counter END
    
    /**
     * リクエストに対する処理を行う。<br>
     * メソッド内でトランザクション制御、コミット、ロールバックの制御処理を行う。<br>
     * @param dataBean S21NEContainerDataBean
     */
    public void doProc(S21NEContainerDataBean dataBean) {

        // ダミーリクエストの設定
        dataBean.setCmsg(new S21NEDummyCMsg(dataBean));

        // トランザクション開始処理
        startTran(dataBean);
        bizProcCounter = new EZDBizPerformanceCounter();
        S21SyslogInfoContext context = (S21SyslogInfoContext) S21SyslogInfoContextHolder.getInstance().getContext();
        bizProcCounter.setJvmName(context.getJvnName());
        bizProcCounter.setHostName(context.getHostName());
        bizProcCounter.setOperationDate(getCurrentTime());
        bizProcCounter.setBizStartTime(getCurrentTime());
        bizProcCounter.setBlapName("ZZXL0030", 0);;
        

        try {
            // 継承クラスの業務処理を起動
            bizProcCounter.setBlapStarTime(getCurrentTime(), 0);

            execute(dataBean);

            bizProcCounter.setBlapEndime(getCurrentTime(), 0);
            // 業務処理での例外
            try {
                // トランザクション終了処理
                endTran(dataBean);
                bizProcCounter.setBizEndTime(getCurrentTime());
                bizProcCounter.requestQueue(new S21NEDummyCMsg(dataBean));
                
            } catch (SQLException ex) {
                throw new S21NEException(ex);
            }

        }catch(S21NEValidatorException e) {
            try {
                // トランザクション終了処理
                rollbackTran(dataBean);
                bizProcCounter.setBizEndTime(getCurrentTime());
                bizProcCounter.requestQueue(new S21NEDummyCMsg(dataBean));
            } catch (SQLException ex) {
                throw new S21NEException(ex);
            }
            throw e;
            
        } catch (EZDAbendException e) {

            // ログ出力
            EZDLog.println(0, EZDMessageFunc.clspGetLogMessage("LGM000901"));
            EZDLog.printStackTrace(e);

            // 業務処理での例外
            try {
                // トランザクション終了処理
                rollbackTran(dataBean);
            } catch (SQLException ex) {
                throw new S21NEException(ex);
            }

            // Exceptionの再throw
            throw e;

        } catch (Exception e) {

            // ログ出力
            EZDLog.println(0, EZDMessageFunc.clspGetLogMessage("LGM000901"));
            EZDLog.printStackTrace(e);

            // 業務処理での例外
            try {
                // トランザクション終了処理
                rollbackTran(dataBean);
            } catch (SQLException ex) {
                throw new S21NEException(ex);
            }

            // Exceptionの再throw
            throw new S21NEException(e);
        }
    }

    /**
     * トランザクション開始処理を行う。<br>
     * @param req
     */
    protected void startTran(S21NEContainerDataBean req) {

        // ThreadLocal初期化
        // EZDDBCICarrier.initOnline("dummy", "20080506152827156", "EDT", "ZZZ");
        EZDDBCICarrier.initOnline(req.getQid(), req.getReqTime(), req.getReqTimeZone(), req.getCompanyCode());
        EZDDBCICarrier.setProgID(req.getEzBizID());

        // コネクションマネージャ作成
        EZDConnectionMgr.getInstance();

        // 課金情報処理
//        EZDAccountInfoWriter accountInfo = initProcess(req.getCmsg(), req.getScreenID());
//        req.setAccountInfo(accountInfo);
    }

    /**
     * トランザクション終了処理(正常)を行う。<br>
     * コネクションマネージャを使用して現在のスレッドに紐付くConnectionを
     * 取得してcommitを行う。
     * @param req
     * @throws SQLException
     */
    protected void endTran(S21NEContainerDataBean req) throws SQLException {

        // 課金情報処理
//        closeAccInfo(req);

        // コミット
        EZDConnectionMgr.getInstance().getConnection().commit();

        // 継承クラスでのコミット後処理を起動
        commitProc(req);
    }

    /**
     * トランザクション終了処理(異常)を行う。<br>
     * コネクションマネージャを使用して現在のスレッドに紐付くConnectionを
     * 取得してrollbackを行う。
     * @param req
     */
    protected void rollbackTran(S21NEContainerDataBean req) throws SQLException {

        // ロールバック
        EZDConnectionMgr.getInstance().getConnection().rollback();

        // 課金情報処理
        // 未実装

        // 継承クラスでのロールバック後処理を起動
        rollbackProc(req);
    }

    /**
     * 課金情報初期化。<br>
     * EZDDispatcher#initProcess相当処理。<br>
     * @param inoutMsg ダミーのリクエスト情報
     * @param screen 画面ID
     * @return 課金情報
     */
    private EZDAccountInfoWriter initProcess(EZDCMsg inoutMsg, String screen) {

        EZDAccountInfoWriter accInfo = new EZDAccountInfoWriter();
        accInfo.setCommStartTime(getCurrentTime());

        if (LOGLEVEL_1) {
            EZDLog.println(1, EZDMessageFunc.clspGetLogMessage("LGM003901"));
            EZDLog.println(1, EZDMessageFunc.clspGetLogMessage("LGM004001")
                                    + inoutMsg.getExecMode());
            EZDLog.println(1, EZDMessageFunc.clspGetLogMessage("LGM004101")
                                    + inoutMsg.getTermIPAddr());
            EZDLog.println(1, EZDMessageFunc.clspGetLogMessage("LGM004201")
                                    + inoutMsg.getTermComputerName());
            EZDLog.println(1, EZDMessageFunc.clspGetLogMessage("LGM004301")
                                    + inoutMsg.getBusinessID());
            EZDLog.println(1, EZDMessageFunc.clspGetLogMessage("LGM004401")
                                    + inoutMsg.getFunctionCode());
        }

        inoutMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);

        String execKbn = inoutMsg.getExecMode();
        String onLineDate = inoutMsg.getOnlOperationDate();
        if ("0".equals(execKbn) || onLineDate == null || (onLineDate.trim().length() == 0)) {
            inoutMsg.setOnlOperationDate(getCurrentTime().substring(0, 8));
        }

        // EZDではEZDDBCICarrier作成を行っているが、別の処理で行っているため
        // ここでは行わない

        if (LOGLEVEL_3) {
            EZDLog.println(3, EZDMessageFunc.clspGetLogMessage("LGM004501"));
            EZDLog.println(3, inoutMsg);
        }

        // 課金情報書き込みフラグ
        accInfo.setNecessaryWrite(S21NEConfig.getInstance().isWriteAccount(screen));

        return accInfo;
    }

    private void closeAccInfo(S21NEContainerDataBean req) {

        EZDAccountInfoWriter accountInfo = req.getAccountInfo();

        accountInfo.setCommEndTime(getCurrentTime());

        if (accountInfo.isNecessaryWrite()) {
            accountInfo.write(req.getCmsg(), getItemErrKind(req.getCmsg()));
        }
    }

    private String getCurrentTime() {
        return EZDStringUtil.getCurrentDate();
    }

    private String getItemErrKind(EZDCMsg inoutMsg) {
//        String messageKind = inoutMsg.getMessageKind();
//        String itemErrKind = EZDBusinessTransInfoMgr.getInfo().getItemErrKind();
//        if ("E".equals(messageKind) || "E".equals(itemErrKind)) {
//            return EZDBusinessTransInfo.EZ_ITEMERRKIND_E;
//        }
//        if ("W".equals(messageKind) || "W".equals(itemErrKind)) {
//            return EZDBusinessTransInfo.EZ_ITEMERRKIND_W;
//        }
//        return itemErrKind;

        // 暫定処理
        return "W";
    }

    /**
     * コミット後処理を行う。<br>
     * トランザクションコミット後に業務側に手何らかの処理を行う場合は、
     * 本メソッドをオーバライドする。<br>
     * @param req S21NEContainerDataBean
     */
    protected void commitProc(S21NEContainerDataBean req) {
        // NOP
    }

    /**
     * コミット後処理を行う。<br>
     * トランザクションコミット後に業務側に手何らかの処理を行う場合は、
     * 本メソッドをオーバライドする。<br>
     * @param req S21NEContainerDataBean
     */
    protected void rollbackProc(S21NEContainerDataBean req) {
        // NOP
    }

    /**
     * 業務処理を行う。<br>
     * 本メソッドに個別の業務処理を実装する。<br>
     * @param req S21NEContainerDataBean
     */
    protected abstract void execute(S21NEContainerDataBean req);
}
