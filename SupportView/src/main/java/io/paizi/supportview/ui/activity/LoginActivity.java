package io.paizi.supportview.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.paizi.supportview.R;
import io.paizi.supportview.app.BaseActivity;

/**
 * Created by pai on 2017/1/25.
 * 演示TextInputLayout的使用
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.text_input_layout)
    TextInputLayout textInputLayout;

    @BindView(R.id.view_account_edit)
    EditText viewAccountEdit;

    @BindView(R.id.view_pwd_edit)
    EditText viewPwdEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        textInputLayout.setCounterMaxLength(10);
        textInputLayout.setCounterEnabled(true);
        String errorMsg = getResources().getString(R.string.accunt_error);
        textInputLayout.getEditText().addTextChangedListener(new MyTextWatcher(textInputLayout, errorMsg));
    }

    class MyTextWatcher implements TextWatcher {
        private TextInputLayout mTextInputLayout;
        private String mErrorMsg;

        public MyTextWatcher(TextInputLayout textInputLayout, String errorMsg) {
            mTextInputLayout = textInputLayout;
            mErrorMsg = errorMsg;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length() > mTextInputLayout.getCounterMaxLength()){
                textInputLayout.setErrorEnabled(true);
                textInputLayout.setError(mErrorMsg);
            }else{
                textInputLayout.setErrorEnabled(false);
            }
        }
    }
}
