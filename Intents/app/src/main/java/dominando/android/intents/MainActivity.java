package dominando.android.intents;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.provider.AlarmClock;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.jar.Manifest;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.acoes));
        setListAdapter(adapter);
    }

    private void discar(){
        Uri uri = Uri.parse("tel:996008196");
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        dispararIntent(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            discar();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Uri uri = null;
        Intent intent = null;
        switch (position){
            //Abrir uma URL
            case 0:
                uri = Uri.parse("http://www.nglauber.com.br");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                dispararIntent(intent);
                break;
            //Realizar uma chamada
            case 1:
                if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE},0);
                }else {
                    discar();
                }
                /*uri = Uri.parse("tel:996008196");
                intent = new Intent(Intent.ACTION_DIAL, uri);
                dispararIntent(intent);
                */
                break;
            //Pesquisa uma posição no mapa
            case 2:
                uri = Uri.parse("geo:0,0?q=Rua+Amelia,Recife");
                intent = new Intent(Intent.ACTION_VIEW,uri);
                dispararIntent(intent);
                break;
            //Executa uma musica do SDCard
            case 3:
                uri = Uri.parse("file:///mnt/sdcard/musica.mp3");
                intent = new Intent().setAction(Intent.ACTION_VIEW).setDataAndType(uri,"audio/mp3");
                dispararIntent(intent);
                break;
            //Abrindo o Editor de SMS
            case 4:
                uri = Uri.parse("sms:12345");
                intent = new Intent(Intent.ACTION_VIEW, uri).putExtra("sms_body" , "Corpo do SMS");
                dispararIntent(intent);
                break;
            //Compartilhar
            case 5:
                intent = new Intent().setAction(Intent.ACTION_SEND).putExtra(Intent.EXTRA_TEXT,"Compartilhando via Internet.").setType("text/plain");
                dispararIntent(intent);
                break;
            //Alarme
            case 6:
                ArrayList<Integer> dias = new ArrayList<Integer>();
                dias.add(Calendar.MONDAY);
                dias.add(Calendar.WEDNESDAY);
                dias.add(Calendar.FRIDAY);
                intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, "Estudar Android")
                        .putExtra(AlarmClock.EXTRA_HOUR,19)
                        .putExtra(AlarmClock.EXTRA_MINUTES,0)
                        .putExtra(AlarmClock.EXTRA_SKIP_UI,true)
                        .putExtra(AlarmClock.EXTRA_DAYS, dias);
                dispararIntent(intent);
                break;
            //Buscar na web
            case 7:
                intent = new Intent(Intent.ACTION_SEARCH)
                        .putExtra(SearchManager.QUERY, "Novatec");
                dispararIntent(intent);
                break;
            //Configurações do aparelho
            case 8:
                intent = new Intent(Settings.ACTION_SETTINGS);
                dispararIntent(intent);
                break;
            //Ação customizada 1
            case 9:
                intent = new Intent("dominando.android.ACAO_CUSTOMIZADA");
                dispararIntent(intent);
                break;
            //Ação customizada 2
            case 10:
                uri = Uri.parse("produto://Notebook/Slim");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                dispararIntent(intent);
                break;
            default:
                finish();

        }
    }

    private void  dispararIntent(Intent intent){
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Toast.makeText(this, R.string.erro_intent, Toast.LENGTH_SHORT).show();
        }

    }
}