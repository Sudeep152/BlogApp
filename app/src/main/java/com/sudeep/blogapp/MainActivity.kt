package com.sudeep.blogapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sudeep.blogapp.ButtomNavigation.Feed_Fragment
import com.sudeep.blogapp.ButtomNavigation.MessageFragment
import com.sudeep.blogapp.ButtomNavigation.NotificationFragment
import com.sudeep.blogapp.ButtomNavigation.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val feedFragment=Feed_Fragment()
        val messageFragment=MessageFragment()
        val notificationFragment=NotificationFragment()
        val profileFragment=ProfileFragment()

        setCurrentFragment(feedFragment)

        tabs.setOnNavigationItemSelectedListener {
            when(it.itemId ){
                R.id.feed_view -> setCurrentFragment(feedFragment)
                R.id.message_view -> setCurrentFragment(messageFragment)
                R.id.notification_view ->setCurrentFragment(notificationFragment)
                R.id.profile_view -> setCurrentFragment(profileFragment)

            }
            true

        }


    }
    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {

            replace(R.id.frame,fragment)
                .commit()
        }
}