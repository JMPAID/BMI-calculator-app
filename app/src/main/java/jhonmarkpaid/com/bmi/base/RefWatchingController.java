package jhonmarkpaid.com.bmi.base;

import android.os.Bundle;

import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeType;

import androidx.annotation.NonNull;


public abstract class RefWatchingController extends ButterKnifeController {

    protected RefWatchingController() { }
    protected RefWatchingController(Bundle args) {
        super(args);
    }

    private boolean hasExited;

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onChangeEnded(@NonNull ControllerChangeHandler changeHandler, @NonNull ControllerChangeType changeType) {
        super.onChangeEnded(changeHandler, changeType);

        hasExited = !changeType.isEnter;
    
    }
}
