package com.demo.quickadvice.ui.adapters

import com.demo.quickadvice.R
import com.demo.quickadvice.databinding.ItemExpertBinding
import com.demo.quickadvice.ui.listeners.ExpertItemListener
import com.demo.quickadvice.ui.models.ExpertData


class ExpertListAdapter(
    private val list: List<ExpertData>,
    private val expertItemListener: ExpertItemListener
) : BaseAdapter<ItemExpertBinding, ExpertData>(list) {

    override val layoutId: Int = R.layout.item_expert

    override fun bind(binding: ItemExpertBinding, item: ExpertData) {
        binding.apply {
            expertData = item
            expertClickListener = expertItemListener
            executePendingBindings()
        }
    }
}