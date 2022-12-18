package easy.flex_chill.bordcord.dialoghelper

import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import easy.flex_chill.bordcord.MainActivity
import easy.flex_chill.bordcord.R
import easy.flex_chill.bordcord.accounthelper.AccountHelper
import easy.flex_chill.bordcord.databinding.SingDialogBinding

class DialogHelper(act:MainActivity) {
    private val act = act
    private val accHelper = AccountHelper(act)

    fun createSingDialog(index: Int) {

        val builder = AlertDialog.Builder(act)
        val rootDialogElement = SingDialogBinding.inflate(act.layoutInflater)
        val view = rootDialogElement.root
        builder.setView(view)
        setDialogState(index, rootDialogElement)

        val dialog = builder.create()
        rootDialogElement.btSingUpIn.setOnClickListener {
            setOnClickSingUpIn(index, rootDialogElement, dialog)
        }
        rootDialogElement.btForgetP.setOnClickListener {
            setOnClickResetPassword(rootDialogElement, dialog)
        }
        dialog.show()
    }

    private fun setOnClickResetPassword(rootDialogElement: SingDialogBinding, dialog: AlertDialog?) {
        if (rootDialogElement.edSingEmail.text.isNotEmpty()) {
            act.mAuth.sendPasswordResetEmail(rootDialogElement.edSingEmail.text.toString()).addOnCompleteListener {
                task ->
                if (task.isSuccessful){
                    Toast.makeText(act,R.string.email_hint_password, Toast.LENGTH_LONG).show()
                }
            }
        dialog?.dismiss()
        } else {
           // rootDialogElement.tvSingD.text = act.resources.getString(R.string.password_hint_vvod)
            rootDialogElement.tvSingD.visibility = View.VISIBLE
        }
    }

    private fun setOnClickSingUpIn(index: Int, rootDialogElement: SingDialogBinding, dialog: AlertDialog?) {

        dialog?.dismiss()
        if (index == DialogConst.SING_IN_STATE) {
            accHelper.singUpWithEmail(rootDialogElement.edSingEmail.text.toString(),
                rootDialogElement.edSingPassword.text.toString())
        } else {
            accHelper.singInWithEmail(rootDialogElement.edSingEmail.text.toString(),
                rootDialogElement.edSingPassword.text.toString())

        }
    }

    private fun setDialogState(index: Int, rootDialogElement: SingDialogBinding) {
        if (index == DialogConst.SING_IN_STATE){
            rootDialogElement.tvSingTitle.text = act.resources.getString(R.string.ac_sing_up)
            rootDialogElement.btSingUpIn.text = act.resources.getString(R.string.sing_up_action)
        } else {
            rootDialogElement.tvSingTitle.text = act.resources.getString(R.string.ac_sing_in)
            rootDialogElement.btSingUpIn.text = act.resources.getString(R.string.sing_in_action)
            rootDialogElement.btForgetP.visibility = View.VISIBLE
        }
    }
}