package com.example.android.thijari.fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.thijari.R;
import com.example.android.thijari.rest.model.BranchInformation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import static android.app.Activity.RESULT_OK;


public class MarkerGoogleMapFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ResultCallback, android.location.LocationListener {


    protected interface OnUpdateLocationListener {
        void refreshLocation(Location location);
    }

    private GoogleMap mGoogleMap;
    private MapView mapView;
    private LocationManager locationmanager;
    //    private onLocationChangeListener locationListener;
    public static final int REQUEST_CHECK_LOCATION = 101;
    private final int PERMISSIONS_REQUEST_LOCATION = 100;
    private final int MIN_TIME_BW_UPDATES = 5000;
    private final int MIN_DISTANCE_CHANGE_FOR_UPDATES = 500;

    //    private Location lastLocation;
    private OnUpdateLocationListener updateLocationListener;

    private final double defaultLatitude = 3.158335;//Tabung Haji HQ Latitude
    private final double defaultLongitude = 101.719840;//Tabung Haji HQ Longitude
    private final String defaultTitle = "Lembaga Tabung Haji";//Tabung Haji HQ
    private final String defaultAddress = "Jalan Tun Razak, Kuala Lumpur, 50450 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur, Malaysia";//Tabung Haji HQ Address

    protected GoogleApiClient mGoogleApiClient;
    protected LocationRequest locationRequest;

    public MarkerGoogleMapFragment() {
    }

    public void setUpdateLocationListener(OnUpdateLocationListener listener) {
        this.updateLocationListener = listener;
    }

    private boolean checkLocationPermission() {
        boolean result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS_REQUEST_LOCATION);
                result = false;
            } else
                result = true;
        } else
            result = true;
        return result;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationmanager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        mGoogleApiClient.connect();

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (updateLocationListener != null) {
//            lastLocation = location;
            updateLocationListener.refreshLocation(location);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    private void getLastLocation() {


        if (checkLocationPermission()) {
            locationmanager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    MIN_TIME_BW_UPDATES,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

//            lastLocation = locationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            if (lastLocation == null)
//                lastLocation = locationmanager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_maker_google_map, container, false);
        mapView = (MapView) v.findViewById(R.id.googleMapId);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        return v;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        if (checkLocationPermission())
            mGoogleMap.setMyLocationEnabled(true);
        refreshMap(null);
    }

    private void refreshMap(Location lastLocation) {
        CameraUpdate cameraUpdate;
        if (lastLocation == null) {
            mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(defaultLatitude, defaultLongitude)).title(defaultTitle).snippet(defaultAddress));
            cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(defaultLatitude, defaultLongitude), 15);
            mGoogleMap.animateCamera(cameraUpdate);
        } else {
            cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude()), 15);
        }
        if (cameraUpdate != null)
            mGoogleMap.animateCamera(cameraUpdate);
    }


    public void addMarker(BranchInformation location) {
        LatLng branchLocation = getLocationFromAddress(location.getAddress());
        Location mLocation = new Location("");
        mLocation.setLatitude(Double.parseDouble(location.getLatitude()));
        mLocation.setLongitude(Double.parseDouble(location.getLongitude()));


        MarkerOptions options = new MarkerOptions();
        options.position(branchLocation);
        options.title(location.getName());
        options.snippet(location.getAddress());
        mGoogleMap.addMarker(options);
        refreshMap(mLocation);
    }

    //TODO: comment the branch Listing
//    public void addMarker(BranchList list) {
//        for (BranchLocation location : list.getLocation()) {
//            MarkerOptions options = new MarkerOptions();
//            options.position(new LatLng(location.getLatitude(), location.getLongitude()));
//            options.title(location.getTitle());
//            options.snippet(location.getAddress());
//            mGoogleMap.addMarker(options);
//            refreshMap();
//        }
//    }


    private LatLng getLocationFromAddress(String strAddress) {
        Geocoder coder = new Geocoder(getActivity());
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }


            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return p1;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_LOCATION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Location lastLocation = null;
            if (checkLocationPermission()) {
                lastLocation = locationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (lastLocation == null)
                    lastLocation = locationmanager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                if (lastLocation != null && updateLocationListener != null)
                    updateLocationListener.refreshLocation(lastLocation);
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);
        PendingResult result =
                LocationServices.SettingsApi.checkLocationSettings(
                        mGoogleApiClient,
                        builder.build()
                );
        result.setResultCallback(this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("MarketGoogleMap", "Google Location ConnectionSuspended =" + i);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("MarketGoogleMap", "Google Location ConnectionFailed =" + connectionResult.getErrorMessage());
    }


    @Override
    public void onResult(@NonNull Result result) {
        final Status status = result.getStatus();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:
                getLastLocation();
                break;
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                // Location settings are not satisfied. But could be fixed by showing the user
                // a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    status.startResolutionForResult(getActivity(), REQUEST_CHECK_LOCATION);
                } catch (IntentSender.SendIntentException e) {
                    // Ignore the error.
                    Log.e("MarketGoogleMap", "Google API ResolutionForResult error =" + e.getMessage());
                }
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHECK_LOCATION) {

            if (resultCode == RESULT_OK) {
                getLastLocation();
            } else {
                Toast.makeText(getActivity(), R.string.noGPSEnableMsg, Toast.LENGTH_SHORT).show();
            }

        }
    }
}