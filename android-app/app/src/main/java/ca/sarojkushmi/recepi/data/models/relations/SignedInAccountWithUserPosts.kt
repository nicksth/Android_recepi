package ca.sarojkushmi.recepi.data.models.relations

import androidx.room.Embedded
import androidx.room.Relation
import ca.sarojkushmi.recepi.data.models.roomModels.UserModel
import ca.sarojkushmi.recepi.data.models.roomModels.UserPost

data class SignedInAccountWithUserPosts(
    @Embedded val userModel: UserModel,
    @Relation(
        parentColumn = "user_email",
        entityColumn = "email"
    )
    val userPosts: List<UserPost>
)