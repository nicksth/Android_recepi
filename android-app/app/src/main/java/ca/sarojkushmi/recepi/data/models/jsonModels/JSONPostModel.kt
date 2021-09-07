package ca.sarojkushmi.recepi.data.models.jsonModels

import com.google.gson.annotations.SerializedName

data class JSONPostModel(
    @SerializedName("id")
    var id: Long,
    @SerializedName("caption")
    var caption: String,
    @SerializedName("postPicBase64")
    var postPicBase64: String,
    @SerializedName("firebaseUid")
    var firebaseUid: String,
    @SerializedName("email")
    var email: String
)
