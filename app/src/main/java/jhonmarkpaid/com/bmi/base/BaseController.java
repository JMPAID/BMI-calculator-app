package jhonmarkpaid.com.bmi.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;


public abstract class BaseController extends RefWatchingController {

    protected BaseController() { }

    protected BaseController(Bundle args) {
        super(args);
    }


    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
    }

}
