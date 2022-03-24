package fr.isen.leclech.androiderestaurant.Registration

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import fr.isen.leclech.androiderestaurant.Network.NetworkConstants
import fr.isen.leclech.androiderestaurant.Network.RegisterResult
import fr.isen.leclech.androiderestaurant.Network.User
import fr.isen.leclech.androiderestaurant.R
import fr.isen.leclech.androiderestaurant.databinding.ActivityBasketBinding.bind
import fr.isen.leclech.androiderestaurant.databinding.ActivityUserBinding
import org.json.JSONObject
import java.security.MessageDigest
import java.util.Locale

interface UserActivityFragmentInteraction {
    fun showLogin()
    fun showRegister()
    fun makeRequest(email: String?, password: String?, lastname: String?, firstname: String?, isfromLogin: Boolean)
}

class UserActivity : AppCompatActivity(), UserActivityFragmentInteraction {
    lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = RegisterFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit()
    }

    private fun hash(string: String): String {
        val bytes = this.toString().toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }

    fun verifyInformations(
        email: String?,
        password: String?,
        lastname: String?,
        firstname: String?,
        fromLogin: Boolean
    ): Boolean {
        var verified: Boolean =  (email?.isNotEmpty() == true && password?.count() ?: 0 >= 6)

        if(!fromLogin) {
            verified = verified && (firstname?.isNotEmpty() == true && lastname?.isNotEmpty() == true)
        }
        return  verified
    }

    override fun showLogin() {
        val fragment = LoginFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }

    override fun showRegister() {
        val fragment = RegisterFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }

    private fun launchRequest(
        email: String?,
        password: String?,
        lastname: String?,
        firstname: String?,
        fromLogin: Boolean
    ) {
        val queue = Volley.newRequestQueue(this)
        var path = NetworkConstants.PATH_REGISTER
        var hashpassword = hash(password.toString())
        if(fromLogin) {
            path = NetworkConstants.PATH_LOGIN
        }
        val url = NetworkConstants.BASE_URL + path

        val jsonData = JSONObject()
        jsonData.put(NetworkConstants.ID_SHOP, "1")
        jsonData.put(NetworkConstants.EMAIL, email)
        jsonData.put(NetworkConstants.PASSWORD, hashpassword)
        if(!fromLogin) {
            jsonData.put(NetworkConstants.FIRSTNAME, firstname)
            jsonData.put(NetworkConstants.LASTNAME, lastname)
        }

        var request = JsonObjectRequest(
            Request.Method.POST,
            url,
            jsonData,
            { response ->
                val userResult = GsonBuilder().create().fromJson(response.toString(), RegisterResult::class.java)
                if(userResult.data != null) {
                    saveUser(userResult.data)
                } else {
                    Snackbar.make(binding.root, R.string.loginerror, Snackbar.LENGTH_LONG).show()
                }
            },
            { error ->
                error.message?.let {

                } ?: run {


                }
            }
        )

        queue.add(request)

    }

    fun saveUser(user: User) {
        val sharedPreferences = getSharedPreferences(USER_PREFERENCES_NAME, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(ID_USER, user.id)
        editor.apply()

        setResult(RESULT_FIRST_USER)
        finish()
    }

    override fun makeRequest(
        email: String?,
        password: String?,
        lastname: String?,
        firstname: String?,
        isfromLogin: Boolean
    ) {
        if(verifyInformations(email, password, lastname, firstname, isfromLogin)) {
            launchRequest(email, password, lastname, firstname, isfromLogin)
        } else {
            Snackbar.make(binding.root, R.string.registrationerror, Snackbar.LENGTH_LONG).show()
        }
    }

    companion object {
        const val REQUEST_CODE = 111
        const val ID_USER = "ID_USER"
        const val USER_PREFERENCES_NAME = "USER_PREFERENCES_NAME"
    }
}
