package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.grupos;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.adapter.AdaptadorDeInvestigador;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Investigador;

public class InvestigadoresFragment extends Fragment implements AdaptadorDeInvestigador.OnClickAdaptadorDeInvestigador{


    AdaptadorDeInvestigador adaptador;
    RecyclerView listadoIntegrantes;
    RecyclerView lider;
    Investigador lider_investigador;
    ArrayList<Investigador> investigadors;

    public InvestigadoresFragment() {
        // Required empty public constructor
    }

    public static InvestigadoresFragment newInstance(ArrayList<Investigador> investigadors, Investigador lider_investigador) {
        InvestigadoresFragment invs = new InvestigadoresFragment();
        invs.setInvestigadors(investigadors);
        invs.setLider_investigador(lider_investigador);
        Bundle bundle = new Bundle();
        invs.setArguments(bundle);
        invs.setRetainInstance(true);
        return invs;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_investigadores, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listadoIntegrantes= (RecyclerView)getView().findViewById(R.id.listaIntegrantes);
        adaptador = new AdaptadorDeInvestigador(investigadors,this);
        //listadoIntegrantes.setNestedScrollingEnabled(false);

        listadoIntegrantes.setAdapter(adaptador);
        listadoIntegrantes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        listadoIntegrantes= (RecyclerView)getView().findViewById(R.id.lider_lista);
        ArrayList<Investigador> lider_list = new ArrayList<>();
        lider_list.add(lider_investigador);
        adaptador = new AdaptadorDeInvestigador(lider_list,this);
        listadoIntegrantes.setAdapter(adaptador);
        listadoIntegrantes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

    }

    public ArrayList getInvestigadors() {
        return investigadors;
    }

    public void setInvestigadors(ArrayList investigadors) {
        this.investigadors = investigadors;
    }


    public Investigador getLider_investigador() {
        return lider_investigador;
    }

    public void setLider_investigador(Investigador lider_investigador) {
        this.lider_investigador = lider_investigador;
    }

    @Override
    public void onClickPosition(int pos) {
        Log.d("investigadors","bu");
    }
}
