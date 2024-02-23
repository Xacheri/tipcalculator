package smith.inclass.tipcalculator;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    Button mChangeTipButton;
    Button mCalculateTipButton;
    EditText mSubtotalEditText;
    TextView mTotalText;
    TextView mTipText;
    TipCalculatorModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChangeTipButton = findViewById(R.id.change_tip_button);
        mCalculateTipButton = findViewById(R.id.calculate_tip_button);
        mSubtotalEditText = findViewById(R.id.subtotal_edit_text);
        mTotalText = findViewById(R.id.total);
        mTipText = findViewById(R.id.tip);
        model = new TipCalculatorModel();
        // listener to set model value on unfocus
        mSubtotalEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    setSubtotal();
                    setTotal();
                }
            }
        });


        mCalculateTipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTotal();
            }
        });

        mChangeTipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchTipChange();
            }
        });

        setTotal();
    }

    // set the subtotal text in the view
    private void setSubtotal()
    {
        String subtotalText = mSubtotalEditText.getText().toString();
        try {
            double subtotalDbl = Double.parseDouble(subtotalText);
            model.setSubtotal(subtotalDbl);
        } catch (NumberFormatException e)
        {
            Toast.makeText(this, "You must put a number value in that field", Toast.LENGTH_SHORT).show();
        }
    }

    // update the tip text in the view
    private void setTip()
    {
        double tip = model.getTip();
        String tipText = NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                .format(tip);
        mTipText.setText(tipText);
    }

    // update the total text in the view
    private void setTotal()
    {
        setSubtotal();
        setTip();
        String totalText = NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                .format(model.getTotal());
        mTotalText.setText(totalText);
    }

    // launch tip change activity
    private void launchTipChange()
    {
        Intent toTipChange = new Intent(this, TipChange.class);
        mTipChangeLauncher.launch(toTipChange); // man that naming made this look like english
    }

    private final ActivityResultLauncher<Intent> mTipChangeLauncher = registerForActivityResult(
    // starts the activity
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            // override the callback executed when the other activity ends
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK)
                {
                    Intent fromTipChange = result.getData();
                    if (fromTipChange != null)
                    {
                        Bundle pkg = fromTipChange.getExtras();
                        double newPercent = pkg.getDouble("percent");
                        model.setTipPercent(newPercent);
                        setTip();
                        setSubtotal();
                        setTotal();
                    }
                }
            }
    });
}