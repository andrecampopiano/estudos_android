package dominando.android.edittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    EditText mEdtNome;
    EditText mEdtEmail;
    EditText mEdtSenha;
    EditText mEdtCep;
    EditText mEdtCpf;
    EditText mEdtCnpj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdtNome = (EditText) findViewById(R.id.edtNome);
        mEdtEmail = (EditText) findViewById(R.id.edtEmail);
        mEdtSenha = (EditText) findViewById(R.id.edtSenha);
        mEdtSenha.setOnEditorActionListener(this);
        mEdtCep = (EditText) findViewById(R.id.edtCep);
        mEdtCpf = (EditText) findViewById(R.id.edtCpf);
        mEdtCnpj = (EditText) findViewById(R.id.edtCnpj);

        mEdtCnpj.addTextChangedListener(new TextWatcher() {
            boolean isUpdating;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }

                boolean hasMask = s.toString().indexOf('.') > -1 || s.toString().indexOf('/') > -1 || s.toString().indexOf('-') > -1;

                String str = s.toString().replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "");

                if(count > before){
                    if(str.length()>12){
                        str = str.substring(0,2) + "." + str.substring(2,5) + "." + str.substring(5,8) + "/" + str.substring(8,12) + "-" + str.substring(12);
                    }else if (str.length() > 8){
                        str = str.substring(0,2) + "." + str.substring(2,5) + "." + str.substring(5,8) + "/" + str.substring(8);
                    }else if (str.length() > 5){
                        str = str.substring(0,2) + "." + str.substring(2,5) + "." + str.substring(5);
                    }else if (str.length() > 2){
                        str = str.substring(0,2) + "." + str.substring(2);
                    }

                    isUpdating = true;
                    mEdtCnpj.setText(str);

                    mEdtCnpj.setSelection(mEdtCnpj.getText().length());

                }else {
                    isUpdating = true;
                    mEdtCnpj.setText(str);
                    mEdtCnpj.setSelection(Math.max(0, Math.min(hasMask ? start - before : start,str.length())));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mEdtCpf.addTextChangedListener(new TextWatcher() {
            boolean isUpdating;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }
                boolean hasMask = s.toString().indexOf('.') > -1 || s.toString().indexOf('-') > -1;

                String str = s.toString().replaceAll("[.]", "").replaceAll("[-]", "");
                if (count > before) {
                    if (str.length() > 9) {
                        str = str.substring(0, 3) + "." + str.substring(3, 6) + "." + str.substring(6, 9) + "-" + str.substring(9);
                    } else if (str.length() > 6) {
                        str = str.substring(0, 3) + "." + str.substring(3, 6) + "." + str.substring(6);
                    } else if (str.length() > 3) {
                        str = str.substring(0, 3) + "." + str.substring(3);
                    }
                    isUpdating = true;
                    mEdtCpf.setText(str);
                    mEdtCpf.setSelection(mEdtCpf.getText().length());
                } else {
                    isUpdating = true;
                    mEdtCpf.setText(str);
                    mEdtCpf.setSelection(Math.max(0, Math.min(hasMask ? start - before : start, str.length())));
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mEdtCep.addTextChangedListener(new TextWatcher() {
            boolean isUpdating;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Quando o texto é alterado o onTextChange é chamado
                //Essa flag evita a chamada infinita desse método
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }
                //Ao apagar o texto, a máscara pe removida,
                //então o posicionamento do cursor precisa
                //saber se o texto atual tinha ou não, máscara
                boolean hasMask = s.toString().indexOf('.') > -1 || s.toString().indexOf('-') > -1;

                //Remove o '.' e '-' da String
                String str = s.toString().replaceAll("[.]", "").replaceAll("[-]", "");

                //os parametros before e after dizem o tamanho
                //anterior e atual da String digitada, se after > before é
                //porque está digitando, caso contrário, está apagando

                if (count > before) {
                    // Se tem mais de 6 caracteres (sem máscara)
                    //coloca o '.' e o '-'
                    if (str.length() > 5) {
                        str = str.substring(0, 2) + '.' +
                                str.substring(2, 5) + '-' +
                                str.substring(5);
                        // Se tem mais de 2, coloca só o ponto
                    } else if (str.length() > 2) {
                        str = str.substring(0, 2) + '.' +
                                str.substring(2);
                    }
                    //Seta a flag para evitar chamada infinita
                    isUpdating = true;
                    //Seta o novo texto
                    mEdtCep.setText(str);

                    //Seta a posição do cursor
                    mEdtCep.setSelection(mEdtCep.getText().length());
                } else {
                    isUpdating = true;
                    mEdtCep.setText(str);
                    // Se estiver apagando posiciona o cursor
                    // no local correto. Isso trata a deleção dos
                    // caracterea da máscara
                    mEdtCep.setSelection(Math.max(0, Math.min(hasMask ? start - before : start, str.length())));

                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (v == mEdtSenha && EditorInfo.IME_ACTION_DONE == actionId) {
            String nome = mEdtNome.getText().toString();
            String email = mEdtEmail.getText().toString();
            String senha = mEdtSenha.getText().toString();
            String cpf = mEdtCpf.getText().toString();
            boolean ok = true;

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                mEdtEmail.setError(getString(R.string.msg_erro_email));
                ok = false;
            }
            if (cpf.length() != 14) {
                mEdtCpf.setError(getString(R.string.msg_erro_cpf));
                ok = false;
            }
            if (!senha.equals("123")) {
                mEdtSenha.setError(getString(R.string.msg_erro_senha));
                ok = false;
            }

            if (ok) {
                Toast.makeText(this, getString(R.string.msg_sucesso, nome, email), Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return false;
    }
}
