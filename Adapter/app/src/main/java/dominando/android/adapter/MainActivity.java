package dominando.android.adapter;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    List<Carro> carros;
    CarrosAdapter adapter;
    ListView listView;

    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);

        listView.setEmptyView(findViewById(android.R.id.empty));

        carros = new ArrayList<Carro>();
        carros.add(new Carro("Celta", 2010, 1, true, false));
        carros.add(new Carro("Uno", 2012, 2, true, true));
        carros.add(new Carro("Fiesta", 2012, 3, false, true));
        carros.add(new Carro("Palio", 2015, 2, true, true));
        carros.add(new Carro("Prisma", 2016, 3, true, true));
        carros.add(new Carro("Passat", 2013, 0, true, true));
        carros.add(new Carro("Saveiro", 2014, 0, true, true));
        carros.add(new Carro("Ka", 2014, 3, true, true));
        carros.add(new Carro("Fusion", 2012, 3, true, false));
        carros.add(new Carro("Cruise", 2016, 1, true, true));
        carros.add(new Carro("Doblo", 2014, 2, true, true));
        carros.add(new Carro("Siena", 2014, 2, true, true));

        adapter = new CarrosAdapter(this, carros);


        final int PADDING = 8;

        TextView txtHeader = new TextView(this);

        txtHeader.setBackgroundColor(Color.GRAY);
        txtHeader.setTextColor(Color.WHITE);
        txtHeader.setText(R.string.texto_cabecalho);
        txtHeader.setPadding(PADDING, PADDING, 0, PADDING);

        listView.addHeaderView(txtHeader);

        final TextView txtFooter = new TextView(this);
        txtFooter.setText(getResources().getQuantityString(
                R.plurals.texto_rodape, adapter.getCount(), adapter.getCount()
        ));
        txtFooter.setBackgroundColor(Color.LTGRAY);
        txtFooter.setGravity(Gravity.RIGHT);
        txtFooter.setPadding(0, PADDING, PADDING, PADDING);
        listView.addFooterView(txtFooter);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Carro carro = (Carro) parent.getItemAtPosition(position);
                if (carro != null) {
                    Toast.makeText(MainActivity.this, carro.modelo + "-" + carro.ano, Toast.LENGTH_SHORT).show();
                    carros.remove(carro);
                    adapter.notifyDataSetChanged();
                    txtFooter.setText(getResources().getQuantityString(
                            R.plurals.texto_rodape,
                            adapter.getCount(),
                            adapter.getCount()));
                }
            }

        });
    }

}
