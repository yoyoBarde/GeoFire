package com.example.j.myapplication

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GooglePlayServicesUtil
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    override fun onConnectionFailed(p0: ConnectionResult) {
    }

    override fun onConnected(p0: Bundle?) {
    }

    override fun onConnectionSuspended(p0: Int) {
    }

    private lateinit var mMap: GoogleMap
    val MY_PERMISSION_REQUEST_CODE =7192
    val PLAY_SERVICES_RESOLUTION_REQUEST=300193
    lateinit var mLocationRequest: LocationRequest
    lateinit var mGoogleClient: GoogleApiClient
    lateinit var mLastLocation: Location
    var TAG="YOYOBARDE"
    var UPDATE_INTERVAL:Long=5000 //5secs
    var FASTEST_INTERVAL:Long=3000
    var DISPLACEMENT:Long=10
    var PRIORITY_HIGH_ACCURACY = LocationRequest.PRIORITY_HIGH_ACCURACY


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }
    fun setUpLocation() {
        val MPLIST = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, MPLIST, MY_PERMISSION_REQUEST_CODE)

        } else {
            if (checkPlayServices()) {
                buildGoogleAPIClient()
                createLocationRequest()
                displayLocation()
            }

        }
    }

    private fun displayLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleClient)
        if(mLastLocation!=null) {
            var latitude = mLastLocation.latitude
            var longtitude = mLastLocation.longitude
            Log.d(TAG,"Your Location was change")

        }
        else
            Log.d(TAG,"Cannot get your location")
    }

    fun createLocationRequest() {
      var mLocationRequest= mLocationRequest
       mLocationRequest.setInterval(UPDATE_INTERVAL)
       mLocationRequest.setFastestInterval(FASTEST_INTERVAL)
       mLocationRequest.setPriority(PRIORITY_HIGH_ACCURACY)
mLocationRequest.setSmallestDisplacement(DISPLACEMENT.toFloat())
    }

    fun buildGoogleAPIClient(){
      var mGoogleApiClient =GoogleApiClient.Builder(this)
              .addConnectionCallbacks(this)
              .addOnConnectionFailedListener(this)
              .addApi(LocationServices.API)
              .build()
                mGoogleApiClient.connect()
   }

    fun checkPlayServices(): Boolean{
        var resultCode=GooglePlayServicesUtil.isGooglePlayServicesAvailable(this)
       if (resultCode!= ConnectionResult.SUCCESS) {
           if(GooglePlayServicesUtil.isUserRecoverableError(resultCode))
           GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show()

           else{
               Toast.makeText(this, "This device is not supported", Toast.LENGTH_SHORT).show()
               finish()
           }
       return false
       }
       return true

   }









    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
    
