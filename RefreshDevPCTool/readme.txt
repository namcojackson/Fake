--------------------------------------------------------------------------------
  Refresh Development PC Tool
--------------------------------------------------------------------------------
                                                            2016/12  Framework

  <利用方法>
    詳細な手順はeRoomを参照ください。
    http://eroom.cusa.canon.com/eRoom/S21/S21Program2/0_6b938
    (1) refreshByEAR.bat(refreshByEAR_FW.bat)を実行します。
    (2) 立ちあがったコマンドプロンプトに"BUILD SUCCESSFUL"が表示されるまで待ちま
        す。
        -- 以下は初回のみ実施してください --
    (3) クラスパスに、以下の３つのJarを追加してください。
        Interstage Studio＞ウィンドウ（W)＞設定（P)...を開いて、Java
         > ビルドパス > ユーザライブラリのEZD_LIBに以下を追加してください。
        C:\Interstage\J2EE\var\deployment\ijserver\{IJServer名}\Shared\lib\S21Application.jar
        C:\Interstage\J2EE\var\deployment\ijserver\{IJServer名}\Shared\lib\ezdMsg.jar
        C:\Interstage\J2EE\var\deployment\ijserver\{IJServer名}\Shared\lib\S21CodeTableConstants.jar
    (4) 以下のファイルを上書きコピーしてください。
        コピー元
          http://eroom.cusa.canon.com/eRoom/S21/S21Program2/0_6b97e
          messages.properties
        コピー先
          C:\S21_EZDeveloper\workspace\dvlpPC\99ezf\parts\tools\EZDDevelopAssistant\etc
    (5) 以下のファイルを編集してください。
        C:\Interstage\J2EE\var\deployment\ijserver\{IJServer名}\Shared\classes\EZDSystem.properties
        ezd.dispatch_file_dirの値を、”../classes”から、”dispatch”に変更して
        ください。


  <注意>
    ・ツールは以下のパスに格納されている必要があります。
      C:\S21_EZDeveloper\workspace\RefreshDevPCTool
    ・ツール実行中に以下のメッセージが出力される場合がありますが、無視してくださ
      い。IJServer停止コマンド実行時に、既に停止状態である場合に出力されます。
        isstopwu: エラー: is31703: 指定されたワークユニットは定義されていないか
        、起動されていません: WU={IJServer名}


  <コピー資材>
    refreshByEAR_FW.batではrefreshByEAR.batより多くの資材を取り込みます。
    各実行バッチで開発者環境にデプロイされる資材はfilelistを参照してください。

   ------------------------------------------------------------------------------
   ・refreshByEAR.bat
      TMsg、フレームワーク資材、メッセージプロパティは、AP1等サーバー上に
      デプロイされている資産と同期せずに、ローカルPC上にあるものを使います。
      → 開発中のTMsgや、フレームワークなど、サーバー上にデプロイされているもの
         より、ローカルPC上の新しい資産を使いたいときはこちらを使用してください。


   ・refreshByEAR_FW.bat（新規追加ツール）
      TMsg、フレームワーク資材、メッセージプロパティも、AP1等サーバー上に
      デプロイされている資産と同期します。
      → TMsgや、フレームワークなどの資産をサーバにデプロイされているバージョン
         と合わせたいときはこちらを使用してください。 
   ------------------------------------------------------------------------------

