package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Grupo;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Investigador;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchDialogFragment extends DialogFragment implements View.OnClickListener {

    private ArrayList<Grupo> resultadoGrupos;
    private ArrayList<Investigador> resultadoInvestigadores;
    private Button buscar;
    private EditText txtGrupo;
    private EditText txtInvestigador;
    private EditText txtLineas;

    ArrayList<Grupo> grupos;
    List<Investigador> investigadores;

    private OnSearchListener listener;

    public SearchDialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.fragment_search_dialog, null);
        buscar = (Button) view.findViewById(R.id.button_search);
        buscar.setOnClickListener(this);
        txtGrupo = (EditText) view.findViewById(R.id.grupo_search);
        txtLineas = (EditText) view.findViewById(R.id.lineas_search);
        txtInvestigador = (EditText) view.findViewById(R.id.investigador_search);
        resultadoGrupos = new ArrayList<>();
        resultadoInvestigadores = new ArrayList<>();

        crearGrupos();

        builder.setView(view);
        return builder.create();
    }

    public interface OnSearchListener{
        void onSearchListener(ArrayList<Grupo> grupos, ArrayList<Investigador> investigadores);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity){
            activity = (Activity)context;
            try {
                listener = (OnSearchListener)activity;
            }catch (ClassCastException e){
                throw new ClassCastException(activity.toString() + "Implemente la interfaz OnSearchListener");
            }
        }
    }

    private void crearGrupos() {
        grupos = new ArrayList();

        List<String> lineasG1 = new ArrayList<>(), lineasG2 = new ArrayList<>(), lineasG3 = new ArrayList<>(), lineasG4 = new ArrayList<String>();
        lineasG1.add("Bases de datos");
        lineasG1.add("Redes de computadoras");
        lineasG1.add("Mineria");

        investigadores = new ArrayList<Investigador>();
        Investigador i1 = new Investigador();
        Investigador i2 = new Investigador();
        Investigador i3 = new Investigador();
        Investigador i4 = new Investigador();
        Investigador i5 = new Investigador();

        i1.setNombre("Pepito");
        i1.setApellido("Perez");
        i1.setLink("www.cvlac.com/pepito");
        i1.setCategoria("Categoria 2");
        i1.setLineasInvestigacion((ArrayList) lineasG1);

        i2.setNombre("Pepita");
        i2.setApellido("Lopez");
        i2.setLineasInvestigacion((ArrayList) lineasG1);

        i3.setNombre("Lupita");
        i3.setApellido("Luna");
        i3.setLineasInvestigacion((ArrayList) lineasG1);

        i4.setNombre("Marcus");
        i4.setApellido("Cornelious");
        i4.setLineasInvestigacion((ArrayList) lineasG1);

        i5.setNombre("Serena");
        i5.setApellido("Williams");
        i5.setLineasInvestigacion((ArrayList) lineasG1);

        investigadores.add(i1);
        investigadores.add(i2);
        investigadores.add(i3);
        investigadores.add(i4);
        investigadores.add(i5);

        lineasG2.add("Bases de datos");
        lineasG2.add("Mineria");
        lineasG2.add("Gestion del conocimiento");

        lineasG4.add("Redes de computadoras");
        lineasG4.add("Ingenieria de software");
        lineasG4.add("Mineria");


        Investigador lider = new Investigador();
        lider.setNombre("Manu");
        lider.setApellido("Lalu");
        lider.setLineasInvestigacion((ArrayList) lineasG2);


        Grupo g1 = new Grupo();
        g1.setCategoria("Categoria C");
        g1.setNombre("Grupo de Investigación en Redes, Información y Distribuciones GRID");
        //g1.setNombre("GEOIDE-G62");
        g1.setEmail("grid@micorreo.com");
        g1.setSigla("GRID");
        g1.setFoto(12300);
        g1.setLider(lider);
        g1.setInvestigadores(investigadores);
        g1.setLink("www.cvlac.com/grid");
        g1.setLineasInvestigacion(lineasG4);


        Grupo g2 = new Grupo();
        g2.setNombre("GEOIDE-G62");
        g2.setSigla("GEOIDE-G62");
        g2.setCategoria("Categoria D");
        g2.setLink("www.cvlac.com/geoide");
        g2.setLineasInvestigacion(lineasG2);
        g2.setLider(lider);
        g2.setInvestigadores(investigadores);

        Grupo g3 = new Grupo();
        g3.setNombre("SINFOCI");
        g3.setSigla("SINFOCI");
        g3.setCategoria("Categoria D");
        g3.setLink("www.cvlac.com/SINFOCI");
        g3.setLineasInvestigacion(lineasG2);
        g3.setLider(lider);
        g3.setInvestigadores(investigadores);

        Grupo g4 = new Grupo();
        g4.setNombre("CIDERA");
        g4.setSigla("CIDERA");
        g4.setCategoria("Categoria D");
        g4.setLink("www.cvlac.com/CIDERA");
        g4.setLineasInvestigacion(lineasG2);
        g4.setLider(lider);
        g4.setInvestigadores(investigadores);


        grupos.add(g1);
        grupos.add(g2);
        grupos.add(g3);
        grupos.add(g4);

    }

    private void buscarEnListas(String grupo, String investigador, String linea) {

        if (grupo.equals("") && investigador.equals("") && linea.equals("")){
            Log.v("TAG","Ingrese un termino de búsqueda");
        }else if (investigador.equals("") && !grupo.equals("")){
            for (int i = 0; i < grupos.size(); i++) {
                if (grupos.get(i).getSigla().contains(grupo) || grupos.get(i).getLineasInvestigacion().contains(linea)) {
                    resultadoGrupos.add(grupos.get(i));
                }
            }
        }else if (grupo.equals("") && !investigador.equals("")){
            for (int i = 0; i < investigadores.size(); i++) {
                if (investigadores.get(i).getNombre().contains(investigador) ||
                        investigadores.get(i).getLineasInvestigacion().contains(linea)) {
                    resultadoInvestigadores.add(investigadores.get(i));
                }
            }
        }else{
            for (int i = 0; i < investigadores.size(); i++) {
                if (investigadores.get(i).getNombre().contains(investigador) ||
                        investigadores.get(i).getLineasInvestigacion().contains(linea)) {
                    resultadoInvestigadores.add(investigadores.get(i));
                }
            }
            for (int i = 0; i < grupos.size(); i++) {
                if (grupos.get(i).getSigla().contains(grupo) || grupos.get(i).getLineasInvestigacion().contains(linea)) {
                    resultadoGrupos.add(grupos.get(i));
                }
            }
        }

    }

    @Override
    public void onClick(View v) {
        buscarEnListas(txtGrupo.getText().toString(), txtInvestigador.getText().toString(), txtLineas.getText().toString());
        listener.onSearchListener(resultadoGrupos,resultadoInvestigadores);
        Log.v("Grupos", resultadoGrupos.size() + "");
        Log.v("Investigadores", resultadoInvestigadores.size() + "");
        getDialog().dismiss();
    }
}