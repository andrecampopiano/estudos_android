package dominando.android.expandlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandableListView);
        List<String> listPe = new ArrayList<String>();
        listPe.add("Caruaru");
        listPe.add("Recife");

        List<String> listSp = new ArrayList<String>();
        listSp.add("São Paulo");
        listSp.add("Campinas");

        Map<String, List<String>> dados = new HashMap<String, List<String>>();
        dados.put("PE", listPe);
        dados.put("SP", listSp);

        listView.setAdapter(new MeuExpandableAdapter(dados));
    }
}
