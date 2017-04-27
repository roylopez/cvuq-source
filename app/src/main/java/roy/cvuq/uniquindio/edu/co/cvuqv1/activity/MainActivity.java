package roy.cvuq.uniquindio.edu.co.cvuqv1.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.AddGroupFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.AddInvestigatorFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.HomeFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.SearchDialogFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.SearchFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.grupos.ListaDeGruposFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.grupos.TabFragmentDetalleGrupo;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.investigadores.ListaInvestigadoresFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.investigadores.TabDetalleDeInvestigadorFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Grupo;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Investigador;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener,
        HomeFragment.OnOptionHomeListener,
        ListaInvestigadoresFragment.OnInvestigadorSeleccionadoListener,
        ListaDeGruposFragment.OnGrupoSeleccionadoListener,
        SearchDialogFragment.OnSearchListener{

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private Fragment actualFragment;
    private int IdContextName;
    private boolean isRestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View view = navigationView.getHeaderView(0);
        view.setOnClickListener(this);

        isRestore = false;

        if (savedInstanceState != null) {
            isRestore = true;
            actualFragment = getSupportFragmentManager().getFragment(savedInstanceState, "actualFragment");
            drawFragmentWithContextName(actualFragment, savedInstanceState.getInt("contextName"), actualFragment.getTag());
        } else {
            drawFragmentWithContextName(new HomeFragment(), R.string.texto_inicio, getResources().getString(R.string.tag_fragment_home));
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        isRestore = false;
        int option = item.getItemId();

        switch (option) {
            case R.id.nav_investigadores:
                drawFragmentWithContextName(new ListaInvestigadoresFragment(), R.string.texto_titulo_lista_investigadores, getResources().getString(R.string.tag_fragment_listainvestigadores));
                break;

            case R.id.nav_buscar:
                SearchDialogFragment dialogo = new SearchDialogFragment();
                dialogo.setStyle(dialogo.STYLE_NORMAL, R.style.DialogoTitulo);
                dialogo.show(getSupportFragmentManager(), MainActivity.class.getName());
                break;

            case R.id.nav_actualizar:
                break;

            case R.id.nav_grupos:
                drawFragmentWithContextName(new ListaDeGruposFragment(), R.string.texto_grupos, getResources().getString(R.string.tag_fragment_listagrupos));
                break;

            case R.id.nav_idioma_espaniol:
                break;

            case R.id.nav_idioma_ingles:
                break;

            default:
                break;

        }

        drawerLayout.closeDrawers();
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.app_navigation_drawer_header) {
            drawFragmentWithContextName(new HomeFragment(), R.string.texto_inicio, getResources().getString(R.string.tag_fragment_home));
            drawerLayout.closeDrawers();
        }
    }

    @Override
    public void onOptionHomeSelected(int optionId) {
        isRestore = false;
        switch (optionId) {
            case R.id.option_groups:
                drawFragmentWithContextName(new ListaDeGruposFragment(), R.string.texto_grupos, getResources().getString(R.string.tag_fragment_listagrupos));
                break;

            case R.id.option_add_group:
                drawFragmentWithContextName(new AddGroupFragment(), R.string.texto_nuevo_grupo, getResources().getString(R.string.tag_fragment_addgroup));
                break;

            case R.id.option_investigators:
                drawFragmentWithContextName(new ListaInvestigadoresFragment(), R.string.texto_titulo_lista_investigadores, getResources().getString(R.string.tag_fragment_listainvestigadores));
                break;

            case R.id.option_add_investigator:
                drawFragmentWithContextName(new AddInvestigatorFragment(), R.string.texto_nuevo_investigador, getResources().getString(R.string.tag_fragment_addinvestigator));
                break;

            default:
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("contextName", IdContextName);
        getSupportFragmentManager().putFragment(outState, "actualFragment", actualFragment);
    }

    @Override
    public void onInvestigadorSeleccionado(Investigador investigador) {
        TabDetalleDeInvestigadorFragment fragment = new TabDetalleDeInvestigadorFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("investigador", investigador);
        fragment.setArguments(bundle);
        drawFragmentWithContextName(fragment, R.string.texto_titulo_lista_investigadores, getResources().getString(R.string.tag_fragment_detalleinvestigador));
    }

    @Override
    public void onGrupoSeleccionado(Grupo grupo) {
        TabFragmentDetalleGrupo fragment = new TabFragmentDetalleGrupo();
        Bundle bundle = new Bundle();
        bundle.putParcelable("grupo", grupo);
        fragment.setArguments(bundle);
        drawFragmentWithContextName(fragment, R.string.texto_grupos, getResources().getString(R.string.tag_fragment_detallegrupo));
    }


    @Override
    public void onSearchListener(ArrayList<Grupo> grupos, ArrayList<Investigador> investigadores) {
        SearchFragment searchFragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("grupos",grupos);
        bundle.putParcelableArrayList("investigadores",investigadores);
        searchFragment.setArguments(bundle);
        drawFragmentWithContextName(searchFragment,R.string.texto_titulo_search,getResources().getString(R.string.tag_fragment_search));

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            isRestore = true;
            super.onBackPressed();
            Fragment fragmentAfterBackPress = getCurrentFragment();

            if (fragmentAfterBackPress instanceof AddInvestigatorFragment) {
                drawFragmentWithContextName(new AddInvestigatorFragment(), R.string.texto_nuevo_investigador, fragmentAfterBackPress.getTag());
            } else if (fragmentAfterBackPress instanceof ListaDeGruposFragment) {
                drawFragmentWithContextName(new ListaDeGruposFragment(), R.string.texto_grupos, fragmentAfterBackPress.getTag());
            } else if (fragmentAfterBackPress instanceof HomeFragment) {
                drawFragmentWithContextName(new HomeFragment(), R.string.texto_inicio, fragmentAfterBackPress.getTag());
            } else if (fragmentAfterBackPress instanceof ListaInvestigadoresFragment) {
                drawFragmentWithContextName(new ListaInvestigadoresFragment(), R.string.texto_lista_investigadores, fragmentAfterBackPress.getTag());
            } else if (fragmentAfterBackPress instanceof TabFragmentDetalleGrupo) {
                drawFragmentWithContextName(fragmentAfterBackPress, R.string.texto_grupos, fragmentAfterBackPress.getTag());
            } else if (fragmentAfterBackPress instanceof TabDetalleDeInvestigadorFragment) {
                drawFragmentWithContextName(fragmentAfterBackPress, R.string.texto_lista_investigadores, fragmentAfterBackPress.getTag());
            } else if (fragmentAfterBackPress instanceof AddGroupFragment) {
                drawFragmentWithContextName(new AddGroupFragment(), R.string.texto_nuevo_grupo, fragmentAfterBackPress.getTag());
            } else if(fragmentAfterBackPress instanceof SearchFragment){
                drawFragmentWithContextName(fragmentAfterBackPress, R.string.texto_titulo_search, fragmentAfterBackPress.getTag());
            }

            //condicionar con instanceof para saber que fragmento redibujar
            //drawFragmentWithContextName(fragmentAfterBackPress, R.string.texto_inicio, fragmentAfterBackPress.getTag());

            //actualFragment = fragmentAfterBackPress;
        }
    }

    private Fragment getCurrentFragment() {
        if (actualFragment instanceof HomeFragment) {
            try {
                FragmentManager fragmentManager = getSupportFragmentManager();
                String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
                Fragment currentFragment = fragmentManager.findFragmentByTag(fragmentTag);
                return currentFragment;
            } catch (ArrayIndexOutOfBoundsException e) {
                finish();
                return null;
            }
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
            Fragment currentFragment = fragmentManager.findFragmentByTag(fragmentTag);
            return currentFragment;
        }
    }

    private boolean navigateToSame(Fragment fragment) {
        boolean flag = false;
        if (actualFragment instanceof HomeFragment && fragment instanceof HomeFragment) {
            flag = true;
        } else if (actualFragment instanceof ListaDeGruposFragment && fragment instanceof ListaDeGruposFragment) {
            flag = true;
        } else if (actualFragment instanceof ListaInvestigadoresFragment && fragment instanceof ListaInvestigadoresFragment) {
            flag = true;
        } else if (actualFragment instanceof AddGroupFragment && fragment instanceof AddGroupFragment) {
            flag = true;
        } else if (actualFragment instanceof AddInvestigatorFragment && fragment instanceof AddInvestigatorFragment) {
            flag = true;
        }
        return flag;
    }

    private void drawFragmentWithContextName(Fragment fragment, int contextName, String tag) {
        /*
        Evaluar si existen los contendores para las vistas de tablet
         */


        if (isRestore) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content, fragment, tag)
                    .commit();
        } else {
            if (navigateToSame(fragment)) {

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_content, fragment, tag)
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_content, fragment, tag)
                        .addToBackStack(tag)
                        .commit();
            }
        }

        actualFragment = fragment;
        IdContextName = contextName;

        getSupportActionBar().setTitle(contextName);
    }

}
