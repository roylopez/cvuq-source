package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.investigadores;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Investigador;


public class TabDetalleDeInvestigadorFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 2;
    private Investigador investigador;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        investigador = new Investigador();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(getArguments() != null){
            investigador = getArguments().getParcelable("investigador");
        } else {
            Log.v("nulo","nulo");
        }

        View x = inflater.inflate(R.layout.fragment_tool_bar, null);
        tabLayout = (TabLayout) x.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager(), investigador));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        TextView txtTitulo = (TextView) x.findViewById(R.id.txtTitulo_toolbar);
        txtTitulo.setText(investigador.getNombre());

        return x;

    }

    class MyAdapter extends FragmentPagerAdapter {
        private Investigador investigador;

        public MyAdapter(FragmentManager fm, Investigador investigador) {
            super(fm);
            this.investigador = investigador;
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position) {
            Fragment f = null;
            switch (position) {
                case 0:
                    f = DetalleDeInvestigadorFragment.newInstance(investigador);

                    break;
                case 1:
                    f = InfoPersonalFragment.newInstance(investigador);
                    break;
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
                    return getResources().getString(R.string.texto_datos_personales);
                case 1:
                    return getResources().getString(R.string.texto_informacion_adicional);
            }
            return null;
        }
    }

    public Investigador getInvestigador() {
        return investigador;
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }
}
