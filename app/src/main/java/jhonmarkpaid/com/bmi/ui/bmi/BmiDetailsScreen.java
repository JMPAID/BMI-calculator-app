package jhonmarkpaid.com.bmi.ui.bmi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;
import jhonmarkpaid.com.bmi.R;
import jhonmarkpaid.com.bmi.base.BaseController;
import jhonmarkpaid.com.bmi.util.BundleBuilder;

public class BmiDetailsScreen extends BaseController {

    @BindView(R.id.txt_bmi)
    TextView txtBmi;

    @BindView(R.id.txt_name)
    TextView txtName;

    @BindView(R.id.adView)
    AdView mAdView;

    @BindView(R.id.img_bmi_bg)
    ImageView imgBmiBg;

    @BindView(R.id.img_bar)
    ImageView imgBar;

    public BmiDetailsScreen(Bundle args){
        super(args);

    }

    public BmiDetailsScreen(String bmi, String name){
        this(new BundleBuilder(new Bundle())
                .putString("bmi", bmi)
                .putString("name", name)
                .build());

    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.bmi_details_screen, container, false);
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);

        initAdmob();

        // sets drawable using glide
        Glide.with(getApplicationContext()).load(R.drawable.gradient_bg_bmi_details).into(imgBmiBg);
        Glide.with(getApplicationContext()).load(R.drawable.gradient_bg_main).into(imgBar);

        txtBmi.setText(getArgs().getString("bmi"));
        txtName.setText(String.valueOf("HELLO " + getArgs().getString("name").toUpperCase() + ", YOU ARE " + bmiRange(getArgs().getString("bmi"))));

    }

    @OnClick(R.id.btn_back)
    public void onBtnBackClick(){
        getRouter().popCurrentController();
    }

    @Override
    public boolean handleBack() {
        getRouter().popCurrentController();
        return true;
    }

    private String bmiRange(String bmi){
        double bmiValue = Double.parseDouble(bmi);

        if (bmiValue < 18.50) {
            return "UNDERWEWEIGHT";
        }

        if(bmiValue == 18.50 || bmiValue <= 24.90){
            return "NORMAL  WEIGHT";
        }

        if(bmiValue == 25 || bmiValue <= 29.90){
            return "OVERWEIGHT";
        }

        if(bmiValue == 30 || bmiValue <= 35){
            return "OBESITY";
        }

        if(bmiValue > 35){
            return "SEVERE OBESITY";
        }

        return null;
    }


    private void initAdmob(){
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

}
