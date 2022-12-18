package easy.flex_chill.bordcord

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import easy.flex_chill.bordcord.act.EdidAdsAct
import easy.flex_chill.bordcord.databinding.ActivityMainBinding
import easy.flex_chill.bordcord.dialoghelper.DialogConst
import easy.flex_chill.bordcord.dialoghelper.DialogHelper

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var tvAccount: TextView
    private lateinit var rootElement: ActivityMainBinding
    private val dialogHelper = DialogHelper(this)
    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityMainBinding.inflate(layoutInflater)
        val view = rootElement.root
        setContentView(view)
        init()
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId == R.id.id_pc){
//            val i = Intent(this, EdidAdsAct::class.java)
//            startActivity(i)
//        }   // 18.35(7)
//        return super.onOptionsItemSelected(item)
//    }

    private fun init(){
        val toggle = ActionBarDrawerToggle(this,rootElement.drawerlayout, rootElement.mainContent.toolbar, R.string.open, R.string.close)
        rootElement.drawerlayout.addDrawerListener(toggle)
        toggle.syncState()
        rootElement.navView.setNavigationItemSelectedListener (this)
        tvAccount = rootElement.navView.getHeaderView(0).findViewById(R.id.tvAccountEmail)
    }

    override fun onStart() {
        super.onStart()
        uiUpdate(mAuth.currentUser)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {

            R.id.id_my_ads -> {
                Toast.makeText(this,"Preset id my ass", Toast.LENGTH_LONG).show()
            } R.id.id_car -> {
            Toast.makeText(this,"Preset id my car", Toast.LENGTH_LONG).show()
        } R.id.id_smartphone -> {
            Toast.makeText(this,"Preset id my smart", Toast.LENGTH_LONG).show()
        } R.id.id_pc -> {
            Toast.makeText(this,"Preset id my pc", Toast.LENGTH_LONG).show()
        } R.id.id_dm -> {
            Toast.makeText(this,"Preset id my dm", Toast.LENGTH_LONG).show()
        } R.id.id_sing_in -> {
            dialogHelper.createSingDialog(DialogConst.SING_UP_STATE)
        } R.id.id_sing_out -> {
            uiUpdate(null)
            mAuth.signOut()
        } R.id.id_sing_up -> {
            dialogHelper.createSingDialog(DialogConst.SING_IN_STATE)
        }

        }

        rootElement.drawerlayout.closeDrawer(GravityCompat.START)
return true
    }
    fun uiUpdate(user: FirebaseUser?) {
        tvAccount.text = if (user == null) {
            resources.getString(R.string.not_reg)
        } else {
            user.email
        }
    }

}