package smith.inclass.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

public class TipChange extends AppCompatActivity {

    RadioGroup mTipButtonGroup;
    Button mChangeTipButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_change);
        mTipButtonGroup = findViewById(R.id.buttonGroup);


    }

}