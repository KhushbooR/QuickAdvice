package com.demo.quickadvice.ui.uiutils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.demo.quickadvice.ui.StringConstants
import com.demo.quickadvice.ui.activities.ChatActivity
import com.demo.quickadvice.ui.activities.ExpertsListActivity
import com.demo.quickadvice.ui.activities.LoginActivity
import com.demo.quickadvice.ui.activities.SignUpActivity
import com.demo.quickadvice.ui.models.ExpertData

object HelperClass {

    fun moveToLoginScreen(context: Context) {
        val intent = Intent (context, LoginActivity::class.java)
        context.startActivity(intent)
    }

    fun moveToSignUpScreen(context: Context) {
        val intent = Intent (context, SignUpActivity::class.java)
        context.startActivity(intent)
    }

    fun moveToExpertScreen(context: Context) {
        val intent = Intent (context, ExpertsListActivity::class.java)
        context.startActivity(intent)
    }

    fun moveToChatScreen(context: Context, expertData: ExpertData?) {
        val intent = Intent (context, ChatActivity::class.java)
        intent.putExtra(StringConstants.KEY_EXTRA_EXPERT_INFO_FOR_CHAT, expertData)
        context.startActivity(intent)
    }
}