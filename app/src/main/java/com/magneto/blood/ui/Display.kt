package com.magneto.blood.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.*
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.magneto.blood.R
import com.magneto.blood.databinding.ActivityDisplayBinding
import com.magneto.blood.model.Constant
import com.magneto.blood.model.Constant.ACTION_SHOW_TRACKING_FRAGMENT
import com.magneto.blood.model.Constant.NOTIFICATION_CHANNEL_ID
import com.magneto.blood.model.Constant.NOTIFICATION_CHANNEL_NAME
import com.magneto.blood.model.Constant.NOTIFICATION_ID
import com.magneto.blood.ui.data.SearchFragment
import kotlinx.android.synthetic.main.activity_display.*


class Display : AppCompatActivity() {






    //viewBinding for the Activity
    private lateinit var binding: ActivityDisplayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //set navigation color
        run {

            window.navigationBarColor = Color.parseColor("#FFFFFFFF")
        }
        val lottie = binding.gets.ltview
        checkPermissions()
        requestPermissions()

        //initiate landPage
        val beek = BeekFragment()
        loader(beek)


        //cast viewModel
        //val viewModel: DisplayViewModel = ViewModelProvider(this).get(DisplayViewModel::class.java)
//click events
        val supply = view.findViewById<Button>(R.id.supply)
        supply.setOnClickListener {
            val i5 = MapsFragment2()
            loader(i5)

        }

        binding.gets.button2.setOnClickListener {
            pays.visibility = View.VISIBLE
            gets.visibility = View.GONE

        }
        binding.pays.drop.setOnClickListener {

            pays.visibility = View.GONE
        }
        binding.pays.cancelButton.setOnClickListener {
            gets.visibility = View.GONE
            pays.visibility = View.GONE

        }
        binding.pays.supply.setOnClickListener {
            val i5 = MapsFragment()
            loader(i5)
            pays.visibility = View.GONE

        }
        binding.gets.reject.setOnClickListener {
            gets.visibility = View.GONE
        }
        binding.icon1.setOnClickListener {
            val i = BeekFragment()
            loader(i)
        }
        binding.icon2.setOnClickListener {
            val i2 = SearchFragment()
            loader(i2)
        }

        binding.icon4.setOnClickListener {
            val i4 = ProfileFragment()
            loader(i4)
        }
        binding.icon5.setOnClickListener {
            val i5 = MapsFragment()
            loader(i5)
        }
        //end

    }

  /*  private fun startForegroundService() {


        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle("Running App")
            .setContentText("00:00:00")
            .setContentIntent(getMainActivityPendingIntent())

        startForeground(NOTIFICATION_ID, notificationBuilder.build())
    } */

    private fun getMainActivityPendingIntent() = PendingIntent.getActivity(
        this,
        0,
        Intent(this, MainActivity::class.java).also {
            it.action = ACTION_SHOW_TRACKING_FRAGMENT
        },
        FLAG_UPDATE_CURRENT
    )

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }


    fun vibrate() {
        val vibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT > 26) {
            vibe.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibe.cancel()
        }
    }

    fun countdown() {
        val count: CountDownTimer = object : CountDownTimer(7000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                gets.visibility = View.VISIBLE
                vibrate()
            }
        }
        count.start()
    }


    fun loader(fragment: androidx.fragment.app.Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.line, fragment)
        fragmentTransaction.commit()
    }

    private fun showSnackbar(mainTextStringId: Int) {

        Toast.makeText(this, getString(mainTextStringId), Toast.LENGTH_LONG).show()
    }

    /**
     * Return the current state of the permissions needed.
     */
    private fun checkPermissions(): Boolean {
        val permissionState = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        return permissionState == PackageManager.PERMISSION_GRANTED
    }

    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            Constant.REQUEST_PERMISSIONS_REQUEST_CODE
        )
    }

    private fun requestPermissions() {
        val shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.")

            showSnackbar(
                R.string.app_name
            )

        } else {
            Log.i(TAG, "Requesting permission")
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            startLocationPermissionRequest()
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.i(TAG, "onRequestPermissionResult")
        if (requestCode == Constant.REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.size <= 0) {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                Log.i(TAG, "User interaction was cancelled.")
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted.
                // getLastLocation()
                countdown()
            } else {
                // Permission denied.

                // Notify the user via a SnackBar that they have rejected a core permission for the
                // app, which makes the Activity useless. In a real app, core permissions would
                // typically be best requested during a welcome-screen flow.

                // Additionally, it is important to remember that a permission might have been
                // rejected without asking the user for permission (device policy or "Never ask
                // again" prompts). Therefore, a user interface affordance is typically implemented
                // when permissions are denied. Otherwise, your app could appear unresponsive to
                // touches or interactions which have required permissions.
                showSnackbar(
                    R.string.app_name,
                )
            }
        }
    }





}












