package keshav.fairbid.test.fairbid20;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fyber.FairBid;
import com.fyber.fairbid.ads.Banner;
import com.fyber.fairbid.ads.Interstitial;
import com.fyber.fairbid.ads.Rewarded;
import com.fyber.fairbid.ads.banner.BannerError;
import com.fyber.fairbid.ads.banner.BannerListener;
import com.fyber.fairbid.ads.banner.BannerOptions;
import com.fyber.fairbid.ads.banner.SupportedCreativeSizes;
import com.fyber.fairbid.ads.interstitial.InterstitialListener;
import com.fyber.fairbid.ads.rewarded.RewardedListener;


public class MainActivity extends AppCompatActivity {

    Button ReqRV, ShowRV;
    Button ReqIS, ShowIS;
    Button StopRV, StopIS, TestSuite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FairBid.start("109570", this);

        ReqRV = findViewById(R.id.ReqRV);
        ShowRV = findViewById(R.id.ShowRV);
        ReqIS = findViewById(R.id.ReqIS);
        ShowIS = findViewById(R.id.ShowIS);
        StopRV = findViewById(R.id.StopReqRV);
        StopIS = findViewById(R.id.StopReqIS);
        TestSuite = findViewById(R.id.testSuite);

        ExecuteUI();
        bannerAds();
        enableLogging();

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);


    }

    public void enableLogging() {
        FairBid.configureForAppId("109570")
                .enableLogs() // enables logs for debugging
                .start(this);
    }

    private void bannerAds() {
        BannerOptions bannerOptions = new BannerOptions()
                .withNetworkSize(SupportedCreativeSizes.ADMOB_LEADERBOARD)
                .withNetworkSize(SupportedCreativeSizes.FACEBOOK_BANNER_HEIGHT_90);
        Banner.display("Banner", bannerOptions, MainActivity.this);

        Banner.setBannerListener(new BannerListener() {
            @Override
            public void onError(String placement, BannerError error) {
                // Called when an error arises when displaying the banner from placement 'placement'
            }

            @Override
            public void onLoad(String placement) {
                // Called when the banner from placement 'placement' is successfully loaded
            }

            @Override
            public void onShow(String placement) {
                // Called when the banner from placement 'placement' is shown
                //destroys the banner for a specific placement
                //Banner.destroy("Banner");
            }

            @Override
            public void onClick(String placement) {
                // Called when the banner from placement 'placement' is clicked
            }
        });

    }

    public void ExecuteUI() {

        ShowRV.setEnabled(false);
        ShowIS.setEnabled(false);
        ReqRV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestRV();
            }
        });

        ShowRV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRV();
            }
        });

        ReqIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestIS();
            }
        });

        ShowIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIS();
            }
        });

        StopRV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rewarded.stopRequesting("RV");
            }
        });

        StopIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Interstitial.stopRequesting("Int");
            }
        });

        TestSuite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FairBid.showTestSuite(MainActivity.this);
            }
        });
    }

    public void requestIS() {
        Interstitial.request("Int");
        Interstitial.setInterstitialListener(new InterstitialListener() {
            @Override
            public void onShow(@NonNull String placement) {
                // Called when the interstitial from placement 'placement_name' shows up
            }

            @Override
            public void onClick(@NonNull String placement) {
                // Called when the interstitial from placement 'placement_name' is clicked
                ShowIS.setEnabled(false);

            }

            @Override
            public void onHide(@NonNull String placement) {
                // Called when the interstitial from placement 'placement_name' hides
                ShowIS.setEnabled(false);
            }

            @Override
            public void onShowFailure(@NonNull String placement) {
                // Called when an error arises when showing the interstitial from placement 'placement_name'
            }

            @Override
            public void onAvailable(@NonNull String placement) {
                // Called when a interstitial from placement 'placement_name' becomes available
                ShowIS.setEnabled(true);
            }

            @Override
            public void onUnavailable(@NonNull String placement) {
                // Called when a interstitial from placement 'placement_name' becomes unavailable
            }

            @Override
            public void onAudioStart(@NonNull String s) {

            }

            @Override
            public void onAudioFinish(@NonNull String s) {

            }
        });
    }

    private void requestRV() {
        Rewarded.request("RV");
        Rewarded.setRewardedListener(new RewardedListener() {
            @Override
            public void onShow(@NonNull String placement) {
                // Called when the rewarded ad from placement 'placement' shows up
            }

            @Override
            public void onClick(@NonNull String placement) {
                // Called when the rewarded ad from placement 'placement' is clicked
            }

            @Override
            public void onHide(@NonNull String placement) {
                // Called when the rewarded ad from placement 'placement' hides
            }

            @Override
            public void onShowFailure(@NonNull String placement) {
                // Called when an error arises when showing the rewarded ad from placement 'placement'
            }

            @Override
            public void onAvailable(@NonNull String placement) {
                // Called when a cached rewarded ad from placement 'placement' becomes available
//                showRV();
                ShowRV.setEnabled(true);
            }

            @Override
            public void onUnavailable(@NonNull String placement) {
                // Called when a cached rewarded ad from placement 'placement' becomes unavailable
            }

            @Override
            public void onAudioStart(@NonNull String placement) {
                // Called when a rewarded ad from placement 'placement' starts playing audio
            }

            @Override
            public void onAudioFinish(@NonNull String placement) {
                // Called when a rewarded ad from placement 'placement' stops playing audio
            }

            @Override
            public void onCompletion(@NonNull String placement, boolean userRewarded) {
                // Called when a rewarded ad from placement 'placement' finishes playing
                ShowRV.setEnabled(false);
            }
        });

    }


    public void showRV() {
        if (Rewarded.isAvailable("RV")) {
            Rewarded.show("RV", MainActivity.this);
        } else {
            Toast.makeText(MainActivity.this, "RV Ad is not available", Toast.LENGTH_LONG).show();
        }
    }

    public void showIS() {
        if (Interstitial.isAvailable("Int")) {
            Interstitial.show("Int", MainActivity.this);
        } else {
            Toast.makeText(MainActivity.this, "IS Ad is not available", Toast.LENGTH_LONG).show();
        }
    }

}
