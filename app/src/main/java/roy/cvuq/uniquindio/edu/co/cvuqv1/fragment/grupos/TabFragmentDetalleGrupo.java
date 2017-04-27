package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.grupos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Grupo;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Investigador;

public class TabFragmentDetalleGrupo extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3;
    private Grupo grupo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        grupo = getArguments().getParcelable("grupo");
        View x = inflater.inflate(R.layout.fragment_tool_bar, null);
        tabLayout = (TabLayout) x.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);


        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager(), grupo));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        TextView txtNombreGrupo = (TextView)
                x.findViewById(R.id.txtTitulo_toolbar);
        txtNombreGrupo.setText(grupo.getSigla());

        return x;

    }

    class MyAdapter extends FragmentPagerAdapter {
        private Grupo grupo;

        public MyAdapter(FragmentManager fm, Grupo grupo) {
            super(fm);
            this.grupo = grupo;
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position) {
            Fragment f = null;
            switch (position) {
                case 0:
                    f = DetalleDeGrupoFragment.newInstance(grupo);
                    return f;
                case 1:
                    f = LineasInvestigacionFragment.newInstance((ArrayList) grupo.getLineasInvestigacion());
                    return f;
                case 2:
                    f = InvestigadoresFragment.newInstance((ArrayList<Investigador>) grupo.getInvestigadores(),
                            grupo.getLider());
                    return f;
            }
            return f;
        }

        @Override
        public int getCount() {
            return int_items;
        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Descripción";
                case 1:
                    return "Líneas de investigación";
                case 2:
                    return "Investigadores";
            }
            return null;
        }
    }

    public Grupo getGrupo(){
        return this.grupo;
    }

}
