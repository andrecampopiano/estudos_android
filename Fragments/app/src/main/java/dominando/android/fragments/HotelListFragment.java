package dominando.android.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 10/03/16.
 */
public class HotelListFragment extends ListFragment {

    List<Hotel> mHoteis;
    ArrayAdapter<Hotel> mAdapter;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mHoteis = carregarHoteis();
        mAdapter = new ArrayAdapter<Hotel>(getActivity(), android.R.layout.simple_list_item_1, mHoteis);

        setListAdapter(mAdapter);
    }

    private List<Hotel> carregarHoteis() {
        List<Hotel> hotels = new ArrayList<Hotel>();
        hotels.add(new Hotel("New Beach Hotel", "Av Boa Viagem", 4.5f));
        hotels.add(new Hotel("Recife Hotel", "Av Boa Viagem", 4.0f));
        hotels.add(new Hotel("Canario Hotel", "Rua dos Navegantes", 3.0f));
        hotels.add(new Hotel("Byanca Beach Hotel", "Rua Mamanguape", 4.0f));
        hotels.add(new Hotel("Grand Hotel Dor", "Av Bernardo", 3.5f));
        hotels.add(new Hotel("Hotel Cool", "Av Conselheiro Aguiar", 4.0f));
        hotels.add(new Hotel("Hotel Ininito", "Rua Ribeiro de Brito", 5.0f));
        hotels.add(new Hotel("Hotel Tulipa", "Av. Boa Viagem", 5.0f));

        return hotels;

    }
}
