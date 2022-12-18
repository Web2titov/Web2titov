package easy.flex_chill.bordcord.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.sax.RootElement
import easy.flex_chill.bordcord.R
import easy.flex_chill.bordcord.databinding.ActivityEditAdsBinding

class EdidAdsAct : AppCompatActivity() {
    private lateinit var rootElement: ActivityEditAdsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityEditAdsBinding.inflate(layoutInflater)
        setContentView(rootElement.root)
    }
}