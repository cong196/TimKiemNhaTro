package com.nhatro;


import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;
import com.nhatro.adapter.CardMapViewAdapter;
import com.nhatro.adapter.ShadowTransformer;
import com.nhatro.model.ItemOnMapView;

import java.util.ArrayList;

import static android.content.Context.LOCATION_SERVICE;
import static android.graphics.Typeface.BOLD;
import static android.graphics.Typeface.ITALIC;
import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {

    static View view;
    private MapView mapView;
    private GoogleMap map;
    private LatLng currentSelected;
    private ArrayList<ItemOnMapView> item;

    private Marker selectedMarker;
    private int indexSelected;
    private ArrayList<Marker> lstMarker;

    private CardMapViewAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    //    private CardFragmentPagerAdapter mFragmentCardAdapter;
//    private ShadowTransformer mFragmentCardShadowTransformer;
    private ViewPager mViewPager;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_map, container, false);
            mapView = (MapView) view.findViewById(R.id.mymap);
            mapView.onCreate(savedInstanceState);
            mapView.onResume();
            mapView.getMapAsync(this);
            lstMarker = new ArrayList<>();

            item = new ArrayList<>();

            this.item.add(new ItemOnMapView(1, "Phòng trọ 1", "Địa chỉ 1", 1200000, 10.85064713, 106.77209787));
            this.item.add(new ItemOnMapView(2, "Phòng trọ 2", "Địa chỉ 2", 1100000, 10.84986079, 106.77403353));
            this.item.add(new ItemOnMapView(3, "Phòng trọ 3", "Địa chỉ 3", 550000, 10.84739511, 106.77034281));
            this.item.add(new ItemOnMapView(4, "Phòng trọ 4", "Địa chỉ 4", 450000, 10.84636247, 106.77435539));
            this.item.add(new ItemOnMapView(5, "Phòng trọ 5", "Địa chỉ 5", 2100000, 10.84440583, 106.76908467));
            this.item.add(new ItemOnMapView(6, "Phòng trọ 6", "Địa chỉ 6", 1800000, 10.84484839, 106.77485678));

            mViewPager = (ViewPager) view.findViewById(R.id.viewPager);


            mCardAdapter = new CardMapViewAdapter(this.item);

//            mCardAdapter.addCardItem(new ItemOnMapView(1, "Phòng trọ 1", "Địa chỉ 1", 1555, 10.85064713, 106.77209787));
//            mCardAdapter.addCardItem(new ItemOnMapView(2, "Phòng trọ 2", "Địa chỉ 2", 1556, 10.25064713, 106.37209787));
//            mCardAdapter.addCardItem(new ItemOnMapView(3, "Phòng trọ 3", "Địa chỉ 3", 1557, 10.15064713, 106.87209787));
//            mCardAdapter.addCardItem(new ItemOnMapView(4, "Phòng trọ 4", "Địa chỉ 4", 1558, 10.45064713, 106.57209787));
//            mCardAdapter.addCardItem(new ItemOnMapView(5, "Phòng trọ 5", "Địa chỉ 5", 1559, 10.55064713, 106.17209787));
//            mCardAdapter.addCardItem(new ItemOnMapView(6, "Phòng trọ 6", "Địa chỉ 6", 1550, 10.85064713, 106.97209787));
//            mCardAdapter.addCardItem(new ItemOnMapView(1, "Phòng trọ 1", "Địa chỉ 1", 1555, 10.85064713, 106.77209787));
//            mCardAdapter.addCardItem(new ItemOnMapView(2, "Phòng trọ 2", "Địa chỉ 2", 1556, 10.25064713, 106.37209787));
//            mCardAdapter.addCardItem(new ItemOnMapView(3, "Phòng trọ 3", "Địa chỉ 3", 1557, 10.15064713, 106.87209787));
//            mCardAdapter.addCardItem(new ItemOnMapView(4, "Phòng trọ 4", "Địa chỉ 4", 1558, 10.45064713, 106.57209787));
//            mCardAdapter.addCardItem(new ItemOnMapView(5, "Phòng trọ 5", "Địa chỉ 5", 1559, 10.55064713, 106.17209787));
//            mCardAdapter.addCardItem(new ItemOnMapView(6, "Phòng trọ 6", "Địa chỉ 6", 1550, 10.85064713, 106.97209787));
//            mCardAdapter.addCardItem(new ItemOnMapView(1, "Phòng trọ 1", "Địa chỉ 1", 1555, 10.85064713, 106.77209787));
//            mCardAdapter.addCardItem(new ItemOnMapView(2, "Phòng trọ 2", "Địa chỉ 2", 1556, 10.25064713, 106.37209787));
//            mCardAdapter.addCardItem(new ItemOnMapView(3, "Phòng trọ 3", "Địa chỉ 3", 1557, 10.15064713, 106.87209787));
//            mCardAdapter.addCardItem(new ItemOnMapView(4, "Phòng trọ 4", "Địa chỉ 4", 1558, 10.45064713, 106.57209787));
//            mCardAdapter.addCardItem(new ItemOnMapView(5, "Phòng trọ 5", "Địa chỉ 5", 1559, 10.55064713, 106.17209787));
//            mCardAdapter.addCardItem(new ItemOnMapView(6, "Phòng trọ 6", "Địa chỉ 6", 1550, 10.85064713, 106.97209787));

            mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);

            mCardAdapter.notifyDataSetChanged();

            mViewPager.setAdapter(mCardAdapter);

            mViewPager.setPageTransformer(false, mCardShadowTransformer);
            mViewPager.setOffscreenPageLimit(3);
            mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {

                    Marker tmp = lstMarker.get(indexSelected);
                    IconGenerator icon = new IconGenerator(getContext());
                    icon.setStyle(IconGenerator.STYLE_DEFAULT);
                    tmp.setIcon(BitmapDescriptorFactory.fromBitmap(icon.makeIcon(makeCharSequence(String.valueOf(item.get(indexSelected).getPrice()) + " vnđ"))));


                    indexSelected = position;
                    selectedMarker = lstMarker.get(position);

                    Marker tmp2 = lstMarker.get(indexSelected);
                    IconGenerator icon2 = new IconGenerator(getContext());
                    icon2.setStyle(IconGenerator.STYLE_BLUE);
                    tmp2.setIcon(BitmapDescriptorFactory.fromBitmap(icon2.makeIcon(makeCharSequence(String.valueOf(item.get(indexSelected).getPrice()) + " vnđ"))));


                    CameraPosition temp = new CameraPosition.Builder()
                            .target(new LatLng(mCardAdapter.getItem(position).getLat(), mCardAdapter.getItem(position).getLng()))      // Sets the center of the map to location user
                            .zoom(14)                   // Sets the zoom
                            .build();                   // Creates a CameraPosition from the builder
                    map.animateCamera(CameraUpdateFactory.newCameraPosition(temp));

                }
                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            //Toast.makeText(getContext()),
        }

        Toast.makeText(getContext(),"Load dữ liệu",Toast.LENGTH_SHORT).show();

        return view;
    }

    public void loadData(){
        Toast.makeText(getContext(),"Đang Lọc DL",Toast.LENGTH_SHORT).show();
        this.item.clear();
        map.clear();
        this.item.add(new ItemOnMapView(1, "Phòng trọ 1", "Địa chỉ 1", 1200000, 10.85064713, 106.77209787));
        this.item.add(new ItemOnMapView(2, "Phòng trọ 2", "Địa chỉ 2", 1100000, 10.84986079, 106.77403353));
        mCardAdapter = new CardMapViewAdapter(this.item);
        mViewPager.setAdapter(mCardAdapter);
        //mCardAdapter.notifyDataSetChanged();

        lstMarker.clear();


        for (int i = 0; i < this.item.size(); i++) {
            LatLng temp = new LatLng(item.get(i).getLat(), item.get(i).getLng());

            IconGenerator iconFactory = new IconGenerator(getContext());
            if (i == 0) {
                iconFactory.setStyle(IconGenerator.STYLE_BLUE);
                //scurrentSelected = new LatLng(item.get(i).getLat(),item.get(i).getLng());
            } else {
                iconFactory.setStyle(IconGenerator.STYLE_DEFAULT);
            }


            MarkerOptions markerOptions = new MarkerOptions().
                    icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(makeCharSequence(String.valueOf(item.get(i).getPrice()) + " vnđ")))).
                    position(temp).
                    anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());


            //map.addMarker(markerOptions);

            Marker m = map.addMarker(markerOptions);
            lstMarker.add(m);
            if (i == 0) {
                selectedMarker = lstMarker.get(0);
                indexSelected = 0;
            }
        }

        indexSelected = 0;
        selectedMarker = lstMarker.get(0);


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;

        getLocation();

        for (int i = 0; i < this.item.size(); i++) {
            LatLng temp = new LatLng(item.get(i).getLat(), item.get(i).getLng());

            IconGenerator iconFactory = new IconGenerator(getContext());
            if (i == 0) {
                iconFactory.setStyle(IconGenerator.STYLE_BLUE);
                //scurrentSelected = new LatLng(item.get(i).getLat(),item.get(i).getLng());
            } else {
                iconFactory.setStyle(IconGenerator.STYLE_DEFAULT);
            }


            MarkerOptions markerOptions = new MarkerOptions().
                    icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(makeCharSequence(String.valueOf(item.get(i).getPrice()) + " vnđ")))).
                    position(temp).
                    anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());


            //map.addMarker(markerOptions);

            Marker m = map.addMarker(markerOptions);
            lstMarker.add(m);
            if (i == 0) {
                selectedMarker = lstMarker.get(0);
                indexSelected = 0;
            }
        }
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.equals(selectedMarker)) {
                    return false;
                } else {
                    Marker tmp = lstMarker.get(indexSelected);
                    IconGenerator icon = new IconGenerator(getContext());
                    icon.setStyle(IconGenerator.STYLE_DEFAULT);
                    tmp.setIcon(BitmapDescriptorFactory.fromBitmap(icon.makeIcon(makeCharSequence(String.valueOf(item.get(indexSelected).getPrice()) + " vnđ"))));


                    for (int i = 0; i < lstMarker.size(); i++) {
                        if (lstMarker.get(i).equals(marker)) {
                            indexSelected = i;
                            selectedMarker = marker;

                            Marker tmp2 = lstMarker.get(indexSelected);
                            IconGenerator icon2 = new IconGenerator(getContext());
                            icon2.setStyle(IconGenerator.STYLE_BLUE);
                            tmp2.setIcon(BitmapDescriptorFactory.fromBitmap(icon2.makeIcon(makeCharSequence(String.valueOf(item.get(indexSelected).getPrice()) + " vnđ"))));
                            break;
                        }
                    }

                    mViewPager.setCurrentItem(indexSelected);
                }
                return false;
            }
        });
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(10.85064713, 106.77209787))      // Sets the center of the map to location user
                .zoom(14)                   // Sets the zoom
                .build();                   // Creates a CameraPosition from the builder
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void renderMarker(int position) {
        for (int i = 0; i < this.item.size(); i++) {

            LatLng temp = new LatLng(item.get(i).getLat(), item.get(i).getLng());

            IconGenerator iconFactory = new IconGenerator(getContext());
            if (i == position) {
                iconFactory.setStyle(IconGenerator.STYLE_BLUE);
            } else {
                iconFactory.setStyle(IconGenerator.STYLE_DEFAULT);
            }

            MarkerOptions markerOptions = new MarkerOptions().
                    icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(makeCharSequence(String.valueOf(item.get(i).getPrice()) + " vnđ")))).
                    position(temp).
                    anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            map.addMarker(markerOptions);
        }
    }


    private void getLocation() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location lastLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (lastLocation != null) {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(10.85064713, 106.77209787))      // Sets the center of the map to location user
                    .zoom(14)                   // Sets the zoom
                    .build();                   // Creates a CameraPosition from the builder
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    @Override
    public void onClick(View view) {
//        mViewPager.setAdapter(mCardAdapter);
//        mViewPager.setPageTransformer(false, mCardShadowTransformer);
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    private CharSequence makeCharSequence(String str) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(str);
        ssb.setSpan(new RelativeSizeSpan(0.8f), 0, str.length(), 0);
        ssb.setSpan(new StyleSpan(BOLD), 0, str.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }


}
