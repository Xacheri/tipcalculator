package smith.inclass.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    Button mChangeTipButton;
    Button mCalculateTipButton;
    EditText mSubtotalEditText;
    TextView mTotalText;
    TipCalculatorModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChangeTipButton = findViewById(R.id.change_tip_button);
        mCalculateTipButton = findViewById(R.id.calculate_tip_button);
        mSubtotalEditText = findViewById(R.id.subtotal_edit_text);
        mTotalText = findViewById(R.id.total);
        model = new TipCalculatorModel();

        // listener to set model value on unfocus
        mSubtotalEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    setSubtotal();
                }
            }
        });

        mCalculateTipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTotalText.setText(Double.toString(model.getTotal()));
            }
        });


    }

    private void setSubtotal()
    {
        String subtotalText = mSubtotalEditText.getText().toString();
        try {
            double subtotalDbl = Double.parseDouble(subtotalText);
            model.subtotal = subtotalDbl;
        } catch (NumberFormatException e)
        {
            Toast.makeText(this, "You must put a number value in that field", Toast.LENGTH_SHORT).show();
        }
    }
}