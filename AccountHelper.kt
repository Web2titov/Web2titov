package easy.flex_chill.bordcord.accounthelper

import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Api
import com.google.firebase.auth.FirebaseUser
import easy.flex_chill.bordcord.MainActivity
import easy.flex_chill.bordcord.R

class AccountHelper(act:MainActivity) {
    private val act = act

    fun singUpWithEmail(email:String, password:String){
        if (email.isNotEmpty() && password.isNotEmpty()){
            act.mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {task->

                if (task.isSuccessful) {
                    sendEmailVerification(task.result?.user!!)
                    act.uiUpdate(task.result?.user)
                } else {

                    Toast.makeText(act, act.resources.getString(R.string.sing_up_error), Toast.LENGTH_LONG).show()

                }
            }
        }
    }

        fun singInWithEmail(email:String, password:String){
            if (email.isNotEmpty() && password.isNotEmpty()){
                act.mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {task->

                    if (task.isSuccessful) {
                        act.uiUpdate(task.result?.user)
                    } else {

                        Toast.makeText(act, act.resources.getString(R.string.sing_in_error), Toast.LENGTH_LONG).show()

                    }
                }
            }
        }

    private fun sendEmailVerification(user:FirebaseUser) {
        user.sendEmailVerification().addOnCompleteListener { task->
            if (task.isSuccessful) {
                Toast.makeText(act, act.resources.getString(R.string.sendVeryficationDone), Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(act, act.resources.getString(R.string.sendVerification), Toast.LENGTH_LONG).show()

            }
        }
    }
}