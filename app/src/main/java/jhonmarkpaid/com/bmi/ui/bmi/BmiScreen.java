package jhonmarkpaid.com.bmi.ui.bmi;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.bumptech.glide.Glide;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.shawnlin.numberpicker.NumberPicker;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;
import jhonmarkpaid.com.bmi.R;
import jhonmarkpaid.com.bmi.base.BaseController;

public class BmiScreen extends BaseController {

    @BindView(R.id.txt_name)
    EditText txtName;

    @BindView(R.id.picker_weight)
    NumberPicker weightPicker;

    @BindView(R.id.picker_height)
    NumberPicker heightPicker;

    @BindView(R.id.picker_gender)
    NumberPicker genderPicker;

    @BindView(R.id.img_bar)
    ImageView imgBar;

    private InterstitialAd mInterstitialAd;

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.bmi_screen, container, false);
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);

        setGenderPicker();

        initIntertitialAd();

        // sets drawable using glide
        Glide.with(getApplicationContext()).load(R.drawable.gradient_bg_main).into(imgBar);

    }

    @OnClick(R.id.btn_calculate)
    public void onBtnCalculateClick(){

        if(validate()){
            getRouter().pushController(RouterTransaction.with(new BmiDetailsScreen(
                    calculateBmi(
                            weightPicker.getValue(),
                            heightPicker.getValue()),
                            txtName.getText().toString()))
                    .tag("transaction_history_screen")
                    .pushChangeHandler(new HorizontalChangeHandler())
                    .popChangeHandler(new HorizontalChangeHandler()));

                //show ads
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }

    }

    private void setGenderPicker(){

        String[] data = {"Male", "Female"};
        genderPicker.setMinValue(1);
        genderPicker.setMaxValue(data.length);
        genderPicker.setDisplayedValues(data);

    }

    private String calculateBmi(double weight, double height){
        return String.valueOf(new DecimalFormat("##.##").format(weight /  Math.pow(height * 0.01, 2)));
    }



    private Boolean validate(){
        if(txtName.getText().toString().length() < 1){
            Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true ;
    }


    private void initIntertitialAd(){

        MobileAds.initialize(getApplicationContext(), String.valueOf(R.string.admob_init_id));

        mInterstitialAd = new InterstitialAd(getApplicationContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

}
