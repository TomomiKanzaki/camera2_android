# camera2_android
### 使う時メモ
- textureViewからbitmap作成してbase64に変換
- オートフォーカス効いてないっぽい+無駄なコードあるので修正必要
### permission_dispatcher
- permissionはpermission_dispatcher使用
- permission_dispatcherのアノテーション使っているメソッドはパブリックじゃないとダメっぽい
- 移譲先(permission_dispatcher)でメソッドが作成されるのは一度ビルドしてからなため、〇〇WithPermissionCheck()はビルド後までコメントアウト等必要
