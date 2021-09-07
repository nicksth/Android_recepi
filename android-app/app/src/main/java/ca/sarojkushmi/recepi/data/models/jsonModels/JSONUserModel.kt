package ca.sarojkushmi.recepi.data.models.jsonModels

import com.google.gson.annotations.SerializedName

data class JSONUserModel(
    @SerializedName("email")
    var email: String,
    @SerializedName("username")
    var username: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("profilePicBase64")
    var profilePicBase64: String,
    @SerializedName("bio")
    var bio : String,
    @SerializedName("firebaseUid")
    var firebaseUid : String
)
