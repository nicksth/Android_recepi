package ca.sarojkushmi.recepi.ui.recyclerviewAdapters

import ca.sarojkushmi.recepi.data.models.roomModels.UserModel

interface OnItemClickListener {
    fun onItemClick(account : UserModel)
}