package net.sakashin.linelogin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.linecorp.linesdk.LineProfile
import kotlinx.android.synthetic.main.activity_post_login.*

class PostLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_login)

        txt_access_token.text = intent.getStringExtra("access_token")
        val lineProfile = intent.getParcelableExtra<LineProfile>("line_profile")
        txt_display_name.text = lineProfile.displayName;
        txt_status_message.text = lineProfile.statusMessage;
        txt_user_id.text = lineProfile.userId
        txt_picture_url.text = lineProfile.pictureUrl.toString()
        txt_credential.text = intent.getStringExtra("line_credential")
    }
}
