package net.sakashin.linelogin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.linecorp.linesdk.LineApiResponseCode
import com.linecorp.linesdk.auth.LineLoginApi
import com.linecorp.linesdk.auth.LineLoginResult

class MainActivity : AppCompatActivity() {

    val CHANNEL_ID = ""
    val CHANNEL_SECRET = ""
    val ACCESS_TOKEN = ""

    var mLoginButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mLoginButton = findViewById(R.id.btn_line_login)
        mLoginButton?.setOnClickListener{
            val intent = LineLoginApi.getLoginIntent(baseContext, CHANNEL_ID)
            startActivityForResult(intent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100) {
            val result = LineLoginApi.getLoginResultFromIntent(data)
            when(result.responseCode) {
                LineApiResponseCode.SUCCESS -> {
                    val accessToken = result.lineCredential?.accessToken!!.accessToken
                    val transitionIntent :Intent = Intent(this, PostLoginActivity::class.java)
                    transitionIntent.putExtra("access_token", accessToken)
                    transitionIntent.putExtra("line_profile", result.lineProfile)
                    transitionIntent.putExtra("line_credential", result.lineCredential)
                    startActivity(transitionIntent)
                }
                LineApiResponseCode.CANCEL-> {
                    Log.e(MainActivity::class.java.name, "LINE Login canceld by user")
                }
                else -> {
                    Log.e(MainActivity::class.java.name, "LINE Login failed [" + result.errorData + "]")
                }
            }
        }
    }
}
