package com.demo.quickadvice.ui.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.demo.quickadvice.R
import com.demo.quickadvice.databinding.ActivityChatBinding
import com.demo.quickadvice.ui.StringConstants
import com.demo.quickadvice.ui.models.ExpertData

class ChatActivity : BaseActivity() {

    private lateinit var mBinding: ActivityChatBinding

    override fun getActivityName(): String = "ChatActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_chat)

        val expertInfo = intent.extras?.get(StringConstants.KEY_EXTRA_EXPERT_INFO_FOR_CHAT) as ExpertData

        mBinding.chatToolbar.toolbarUsername.text = expertInfo.name
        mBinding.chatToolbar.backIcon.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}