package smith.inclass.tipcalculator;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class TipChange extends AppCompatActivity {

    RadioGroup mTipButtonGroup;
    Button mChangeTipButton;

    TipChangeModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_change);
        mTipButtonGroup = findViewById(R.id.buttonGroup);
        mChangeTipButton = findViewById(R.id.change_tip_percent_button);
        // init model with lowest tip value
        model = new TipChangeModel();

        mTipButtonGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                changeTipPercent();
            }
        });

        mChangeTipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMainActivity();
            }
        });
    }

    private void changeTipPercent()
    {
        int selectedId = mTipButtonGroup.getCheckedRadioButtonId();
        RadioButton selected = findViewById(selectedId);
        String selectedValue = selected.getText().toString();
        double newPercent = TipChangeModel.percentize(selectedValue);
        model.setTipPercent(newPercent);
    }
    protected void launchMainActivity()
    {
        Intent toMain = new Intent(this, MainActivity.class);
        Bundle pkg = new Bundle();
        pkg.putDouble("percent", model.getTipPercent());
        toMain.putExtras(pkg);
        setResult(RESULT_OK, toMain);
        finish();
    }
}