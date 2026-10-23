
package com.example.regobr;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.regobr.R;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername, etFullName, etCountry, etEmail, etPhone, etPassword;
    private RadioGroup rgGender;
    private CheckBox cbTerms;
    private Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Odmik od sistemskih vrstic (status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        etUsername = findViewById(R.id.etUsername);
        etFullName = findViewById(R.id.etFullName);
        etCountry = findViewById(R.id.etCountry);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        rgGender = findViewById(R.id.rgGender);
        cbTerms = findViewById(R.id.cbTerms);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        btnCreateAccount.setOnClickListener(v -> showSummaryDialog());
    }

    private void showSummaryDialog() {
        // Besedila iz vnosnih polj
        String username = etUsername.getText().toString();
        String fullName = etFullName.getText().toString();
        String country = etCountry.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String password = etPassword.getText().toString();

        // Izbrani spol (-1 pomeni, da ni izbran noben RadioButton)
        String gender;
        int checkedId = rgGender.getCheckedRadioButtonId();
        if (checkedId == -1) {
            gender = "Not selected";
        } else {
            RadioButton selected = findViewById(checkedId);
            gender = selected.getText().toString();
        }

        // Stanje potrditvenega polja
        String terms = cbTerms.isChecked() ? "Yes" : "No";

        String message =
                "Username: " + username + "\n" +
                        "Full name: " + fullName + "\n" +
                        "Country: " + country + "\n" +
                        "Email: " + email + "\n" +
                        "Phone number: " + phone + "\n" +
                        "Password: " + password + "\n" +
                        "Gender: " + gender + "\n" +
                        "Terms Accepted: " + terms;

        new AlertDialog.Builder(this)
                .setTitle("Registration Details")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}
