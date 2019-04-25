package to_kanzaki.camera2_app

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import permissions.dispatcher.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import to_kanzaki.camera2_app.view.Camera2View

/**
 * permission_dispatcher
 * -> onRequestPermissionsResult, checkPermissionWithPermissionCheck は一度ビルドしないと以上先に作成されない
 * アノテーション付与のメソッドはパブリックでないと移譲先にメソッドが作られない
 */
@RuntimePermissions
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        checkPermissionWithPermissionCheck()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @NeedsPermission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun checkPermission(){
        toast("granted")
        startActivity<Camera2View>()
    }

    @OnPermissionDenied(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun onPermissionDenied() { longToast("permission denied") }

    @OnShowRationale(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun showRationaleForPermission(request: PermissionRequest) {
        //一回拒否された後
        request.proceed()
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun onPermissionNeverAskAgain() {
        //二度と表示しないを選択した後
        longToast("go SETTING and grant permissions")
    }
}
