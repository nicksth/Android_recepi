package ca.sarojkushmi.recepi.data.db.DAOs

import androidx.lifecycle.LiveData
import androidx.room.*
import ca.sarojkushmi.recepi.data.models.roomModels.UserModel
import ca.sarojkushmi.recepi.data.models.roomModels.UserPost
import ca.sarojkushmi.recepi.data.models.relations.SignedInAccountWithUserPosts

@Dao
interface UserAccountDAO {

    @Query("SELECT * FROM signedInAccount LIMIT 1")
    fun retrieveCurrentLoggedInUser() : LiveData<UserModel>

    @Insert
    fun insertUser(currentUser: UserModel)

    @Query("DELETE FROM signedInAccount")
    fun deleteAccount()

    @Update
    fun updateUser(currentUser: UserModel)

    @Insert
    fun insertPost(post: UserPost)

    @Transaction
    @Query("SELECT * FROM signedInAccount")
    fun getUserModelWithUserPosts() : LiveData<List<SignedInAccountWithUserPosts>>

    @Query("SELECT * FROM signedInAccount")
    fun getUserModelWithUserPostsNonLiveData() : List<SignedInAccountWithUserPosts>

    @Delete
    fun deletePost(post: UserPost)

    @Query("DELETE FROM userPost")
    fun deleteAllPosts()
}