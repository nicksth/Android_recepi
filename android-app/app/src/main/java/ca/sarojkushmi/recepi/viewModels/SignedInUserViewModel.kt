package ca.sarojkushmi.recepi.viewModels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ca.sarojkushmi.recepi.data.db.DAOs.UserAccountDAO
import ca.sarojkushmi.recepi.data.db.roomDbs.CurrentLoggedInUserCache
import ca.sarojkushmi.recepi.data.models.roomModels.UserModel
import ca.sarojkushmi.recepi.data.models.roomModels.UserPost
import ca.sarojkushmi.recepi.data.models.relations.SignedInAccountWithUserPosts
import ca.sarojkushmi.recepi.repositories.SignedInUserAccountRepository

class SignedInUserViewModel(application: Application) : AndroidViewModel(application) {

    private val userAccountDao : UserAccountDAO = CurrentLoggedInUserCache.getInstance(application).userAccountDAO()
    private val repository = SignedInUserAccountRepository(userAccountDao)
    private var livedataUser : LiveData<UserModel> = repository.getCurrentLoggedInUser()
    private var livedataPostList : LiveData<List<SignedInAccountWithUserPosts>> = repository.getPosts()

    fun getCurrentLoggedInUser(): LiveData<UserModel> {
        return livedataUser
    }

    suspend fun updateUser(context: Context, account: UserModel, idToken: String) : Boolean {
        return repository.updateUser(context, account, idToken)
    }

    suspend fun logInUser(context: Context, idToken: String) : Boolean {
        return repository.logInUser(context, idToken)
    }

    suspend fun checkIfUsernameIsAvailable(username: String, email: String) :Int {
        return repository.checkIfUsernameIsAvailable(username, email)
    }

    suspend fun registerUser(context: Context, account: UserModel, idToken: String) : Boolean {
        return repository.registerUser(context, account, idToken)
    }

    private fun deleteAccountFromCache(context : Context) {
        deleteAllPosts()
        repository.deleteAccountFromCache(context)
    }

    fun logOutUser(context: Context) {
        deleteAccountFromCache(context)
    }

    suspend fun deleteAccount(context: Context, idToken: String) : Boolean {
        deleteAccountFromCache(context)
        return repository.deleteAccountFromServer(idToken)
    }

    suspend fun addPost(context: Context, post : UserPost, idToken: String, email: String) : Boolean {
        return repository.addPost(context, post, idToken, email)
    }

    fun getPosts(): LiveData<List<SignedInAccountWithUserPosts>> {
        return livedataPostList
    }

    suspend fun getPostsForUserSearch(context : Context, idToken : String, email : String) : ArrayList<UserPost> {
        return repository.getPostsForUserSearch(context, idToken, email)
    }

    suspend fun searchForAccounts(context: Context, searchQuery : String) : ArrayList<UserModel> {
        return repository.searchForAccounts(context, searchQuery)
    }

    suspend fun deletePost(post: UserPost, idToken: String) : Boolean {
        return repository.deletePost(post, idToken)
    }

    private fun deleteAllPosts() {
        repository.deleteAllPosts()
    }
}