package `in`.creativelizard.businessadvisor.utils

import `in`.creativelizard.businessadvisor.R
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import kotlinx.android.synthetic.main.activity_custom_scanner.*

class CustomScannerActivity : AppCompatActivity(), DecoratedBarcodeView.TorchListener {
    private var capture: CaptureManager? = null
    private var mtoolbar: Toolbar?=null
    override fun onTorchOn() {

    }

    override fun onTorchOff() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_scanner)
        initialize(savedInstanceState)
    }

    private fun initialize(savedInstanceState: Bundle?) {
        mtoolbar = toolbar as Toolbar
        mtoolbar?.title = "Scan QR Code"
        setSupportActionBar(mtoolbar)
        capture = CaptureManager(this, zxing_barcode_scanner)
        capture!!.initializeFromIntent(intent, savedInstanceState)
        capture!!.decode()
    }

    override fun onResume() {
        super.onResume()
        capture?.onResume()
    }

    override fun onPause() {
        super.onPause()
        capture?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture?.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        capture?.onSaveInstanceState(outState)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return zxing_barcode_scanner.onKeyDown(keyCode, event)!! || super.onKeyDown(keyCode, event)
    }

}
