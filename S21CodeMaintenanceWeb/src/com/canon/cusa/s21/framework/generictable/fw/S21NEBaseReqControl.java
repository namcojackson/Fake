package com.canon.cusa.s21.framework.generictable.fw;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import parts.common.EZDAbendException;
import parts.common.EZDLog;
import parts.common.EZDMessageInfo;
import parts.common.EZDValidatorException;
import tools.ezdcommon.EZDMessageFunc;

/**
 * コードメンテナンス用プレゼンテーションコントロール。<br>
 * プレゼンテーション層の一般的な処理を行うベースクラス。<br>
 * 特別な処理を行わない場合は、本クラスを継承したうえでcontrolメソッド、
 * validateErrorメソッドに個別の処理を記述する。<br>
 * @author Administrator
 */
public abstract class S21NEBaseReqControl implements S21NEReqControl {

    /** メインJSP */
    private static final String MAIN_JSP = "./codemaintenance/GenericmaintenanceMain.jsp";

    /**
     * HTTPリクエストに対する処理を行う。<br>
     * @param req リクエスト
     * @param resp レスポンス
     * @param bean リクエスト情報コンテナ
     * @throws ServletException exception
     * @throws IOException exception
     */
    public void doProc(HttpServletRequest req, HttpServletResponse resp, S21NEContainerDataBean bean) throws ServletException, IOException {

        try {
            // 入力チェックを行う
            validate(bean);

            // 画面個別制御処理
            control(bean);

            // 個別にメッセージが設定されていない場合は、共通正常系メッセージを設定する
            if (bean.getMsgInfo() == null) {
                // エラーメッセージ作成
                EZDMessageInfo msgInfo = new EZDMessageInfo("ZZM8100I");

                // コンテナに設定
                bean.setMsgInfo(msgInfo);
            }
            // 次画面遷移
            succeedFwd(req, resp, bean);

        } catch (S21NEValidatorException e) {

            // 入力チェックエラー

            // 入力チェックエラーではログ出力しない

            // 例外情報を設定
            bean.setExp(e);

            // エラー制御処理
            error(bean);

            // JSPに例外を引き渡さない
            bean.setExp(null);

            // 次画面遷移
            failedFwd(req, resp, bean);

        } catch (EZDAbendException e) {

            // ログ出力
            EZDLog.println(EZDLog.LEVEL_FW, EZDMessageFunc.clspGetLogMessage("LGM000901"));
            EZDLog.printStackTrace(EZDLog.LEVEL_MSG_DUMP, e);

            // 例外情報を設定
            bean.setExp(e);

            // エラー種別をチェック
            if (e.getCause() != null
                    && (e.getCause() instanceof S21NEValidatorException
                        || e.getCause() instanceof EZDValidatorException)) {
                // エラー制御処理
                error(bean);

                // JSPに例外を引き渡さない
                bean.setExp(null);

            } else {
                // システムエラー制御処理
                sysError(bean);
            }

            // 次画面遷移
            failedFwd(req, resp, bean);

        } catch (Exception e) {

            // ログ出力
            EZDLog.println(EZDLog.LEVEL_FW, EZDMessageFunc.clspGetLogMessage("LGM000901"));
            EZDLog.printStackTrace(EZDLog.LEVEL_MSG_DUMP, e);

            // 例外情報を設定
            bean.setExp(e);

            // システムエラー制御処理
            sysError(bean);

            // 次画面遷移
            failedFwd(req, resp, bean);
        }
    }

    /**
     * HTTPリクエストに対する処理を行う。<br>
     * @param bean リクエスト情報コンテナ
     */
    protected abstract void control(S21NEContainerDataBean bean);

    /**
     * 入力チェックエラー時の処理を行う。<br>
     * @param bean リクエスト情報コンテナ
     */
    protected abstract void error(S21NEContainerDataBean bean);

    /**
     * 入力情報のチェックを行い、問題がなければリクエストをBeanに設定する。<br>
     * 実際のチェックはS21NEValidator実装クラスにて行う。<br>
     * 入力情報チェックで問題が発生した場合は、Exceptionにて通知を行う。<br>
     * @param req HttpServletRequest
     * @param bean リクエスト情報コンテナ
     */
    protected void validate(S21NEContainerDataBean bean) {

        // コントロールクラスのインスタンス生成
        S21NEValidator validator = S21NEClassFactory.getInstance().createValidator(bean.getScreenID(), bean.getReqID());

        // 入力チェック処理を行う
        validator.valid(bean);
    }

    /**
     * 業務処理成功後のforward処理。<br>
     * 個別に特殊な処理を行う場合は、本メソッドをオーバライドすることで
     * RequestDispatcher#forward処理以外を行う。<br>
     * @param req リクエスト
     * @param resp レスポンス
     * @param bean リクエスト情報コンテナ
     * @throws ServletException
     * @throws IOException
     */
    protected void succeedFwd(HttpServletRequest req, HttpServletResponse resp, S21NEContainerDataBean bean) throws ServletException, IOException {
        // RequestDispatcher#forward処理を行う
        forward(req, resp, bean);
    }

    /**
     * 業務処理失敗後のforward処理。<br>
     * 個別に特殊な処理を行う場合は、本メソッドをオーバライドすることで
     * RequestDispatcher#forward処理以外を行う。<br>
     * @param req リクエスト
     * @param resp レスポンス
     * @param bean リクエスト情報コンテナ
     * @throws ServletException
     * @throws IOException
     */
    protected void failedFwd(HttpServletRequest req, HttpServletResponse resp, S21NEContainerDataBean bean) throws ServletException, IOException {
        // RequestDispatcher#forward処理を行う
        forward(req, resp, bean);
    }

    /**
     * 次画面遷移。<br>
     * 標準的な処理として指定のJSPへ遷移する。<br>
     * ファイルのダウンロードなどに対応する場合は、本メソッドをオーバライドする。<br>
     * @param req リクエスト
     * @param resp レスポンス
     * @param bean リクエスト情報コンテナ
     * @throws ServletException
     * @throws IOException
     */
    private void forward(HttpServletRequest req, HttpServletResponse resp, S21NEContainerDataBean bean) throws ServletException, IOException {
        // 遷移先画面取得
        String target = S21NEConfig.getInstance().getNextScreen(bean.getScreenID(), bean.getReqID(), bean.getStatus());

        // 遷移先画面を設定する
        bean.setTransition(target);

        // サーバ日付/時刻
        // 暫定処理
        // ここでやるのがいいのか? とりあえずテスト用に適当
        bean.setServerTime(getNowTime());
        bean.setServerDate(getNowDate());

        // beanをAttributeに設定
        req.setAttribute(S21NERequestDef.ATTR_VALUE, bean);

        // 画面遷移
        // forwardする先は固定
        RequestDispatcher dispatch = req.getRequestDispatcher(MAIN_JSP);
        dispatch.forward(req, resp);
    }

    /**
     * 業務処理を起動する。<br>
     * 実際の業務処理はS21NEBusiness実装クラスにて行う。<br>
     * @param bean リクエスト情報コンテナ
     */
    protected void fireBiz(S21NEContainerDataBean bean) {

        // 業務処理クラスのインスタンス生成
        S21NEBusiness biz = S21NEClassFactory.getInstance().createBiz(bean.getScreenID(), bean.getReqID());

        // 業務処理を行う
        biz.doProc(bean);
    }

    /**
     * サーバの現在日付を取得する。<br>
     * @return サーバ日付
     */
    private String getNowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(new Date());
    }

    /**
     * サーバの現在時刻を取得する。<br>
     * @return サーバ時刻
     */
    private String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * システムエラー処理。<br>
     * @param bean S21NEContainerDataBean
     */
    protected abstract void sysError(S21NEContainerDataBean bean);
}
