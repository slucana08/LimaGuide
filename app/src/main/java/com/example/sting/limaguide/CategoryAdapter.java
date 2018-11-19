package com.example.sting.limaguide;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * {@link CategoryAdapter} provides the means to initialize a fragment with the right content to
 * be displayed in one of the place category tabs.
 */

public class CategoryAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private ArrayList<boolean []> isExpandedCheck;
    private CategoryFragment fragmentA;
    private CategoryFragment fragmentB;
    private CategoryFragment fragmentC;
    private CategoryFragment fragmentD;

    /**
     * Constructor for the ViewPager adapter that returns the correct fragment according to the
     * selected position
     * @param fm is the FragmentManager
     * @param context is the correct Context
     * @param isExpandedCheck is an array storing the state of all the views in the different
     *                        fragments, could be null when the app is first launched
     */

    public CategoryAdapter(FragmentManager fm, Context context,
                           @Nullable ArrayList<boolean[]> isExpandedCheck) {
        super(fm);
        this.context = context;

        // Create data to populate landmarks' fragments
        ArrayList<Place> landmarks = new ArrayList<>();
        landmarks.add(new Place(context,context.getResources().getString(R.string.landmark_circuito_title),
                R.drawable.landmarks_circuito_magico,
                context.getResources().getString(R.string.landmark_circuito_description),
                context.getResources().getString(R.string.landmark_circuito_location),
                context.getResources().getString(R.string.landmark_circuito_hours),
                -12.071069, -77.033628,
                context.getResources().getString(R.string.landmark_circuito_url)));
        landmarks.add(new Place(context,context.getResources().getString(R.string.landmark_plaza_armas_title),
                R.drawable.landmark_plaza_de_armas,
                context.getResources().getString(R.string.landmark_plaza_armas_description),
                context.getResources().getString(R.string.landmark_plaza_armas_location),
                context.getResources().getString(R.string.landmark_plaza_armas_hours),
                -12.045605, -77.030613,
                context.getResources().getString(R.string.landmark_plaza_armas_url)));
        landmarks.add(new Place(context,context.getResources().getString(R.string.landmark_miraflores_title),
                R.drawable.landmark_miraflores,
                context.getResources().getString(R.string.landmark_miraflores_description),
                context.getResources().getString(R.string.landmark_miraflores_location),
                context.getResources().getString(R.string.landmark_miraflores_hours),
                -12.120897, -77.029470,
                context.getResources().getString(R.string.landmark_miraflores_url)));
        landmarks.add(new Place(context,context.getResources().getString(R.string.landmark_barranco_title),
                R.drawable.landmark_barranco,
                context.getResources().getString(R.string.landmark_barranco_description),
                context.getResources().getString(R.string.landmark_barranco_location),
                context.getResources(). getString(R.string.landmark_barranco_hours),
                -12.149404, -77.021188,
                context.getResources().getString(R.string.landmark_barranco_url)));
        landmarks.add(new Place(context,context.getResources().getString(R.string.landmark_cerro_cristobal_title),
                R.drawable.landmark_cerro_cristobal,
                context.getResources().getString(R.string.landmark_cerro_cristobal_description),
                context.getResources().getString(R.string.landmark_cerro_cristobal_location),
                context.getResources(). getString(R.string.landmark_cerro_cristobal_hours),
                -12.035218, -77.017642,
                context.getResources().getString(R.string.landmark_cerro_cristobal_url)));
        landmarks.add(new Place(context,context.getResources().getString(R.string.landmark_plaza_martin_title),
                R.drawable.landmark_plaza_martin,
                context.getResources().getString(R.string.landmark_plaza_martin_description),
                context.getResources().getString(R.string.landmark_plaza_martin_location),
                context.getResources(). getString(R.string.landmark_plaza_martin_hours),
                -12.051664, -77.034648,
                context.getResources().getString(R.string.landmark_plaza_martin_url)));

        // Create data to populate restaurants' fragments
        ArrayList<Place> restaurants = new ArrayList<>();
        restaurants.add(new Place(context.getResources().getString(R.string.restaurant_central_title),
                3,R.drawable.restaurant_central,
                context.getResources().getString(R.string.restaurant_central_description),
                context.getResources().getString(R.string.restaurant_central_location),
                context.getResources().getString(R.string.restaurant_central_phone),
                context.getResources(). getString(R.string.restaurant_central_hours),
                -12.152630, -77.022543,
                context.getResources().getString(R.string.restaurant_central_url)));
        restaurants.add(new Place(context.getResources().getString(R.string.restaurant_maido_title),
                3,R.drawable.restaurant_maido,
                context.getResources().getString(R.string.restaurant_maido_description),
                context.getResources().getString(R.string.restaurant_maido_location),
                context.getResources().getString(R.string.restaurant_maido_phone),
                context.getResources(). getString(R.string.restaurant_maido_hours),
                -12.125483, -77.030551,
                context.getResources().getString(R.string.restaurant_maido_url)));
        restaurants.add(new Place(context.getResources().getString(R.string.restaurant_malabar_title),
                3,R.drawable.restaurant_malabar,
                context.getResources().getString(R.string.restaurant_malabar_description),
                context.getResources().getString(R.string.restaurant_malabar_location),
                context.getResources().getString(R.string.restaurant_malabar_phone),
                context.getResources(). getString(R.string.restaurant_malabar_hours),
                -12.094312, -77.034796,
                context.getResources().getString(R.string.restaurant_malabar_url)));
        restaurants.add(new Place(context.getResources().getString(R.string.restaurant_lamar_title),
                2,R.drawable.restaurant_lamar,
                context.getResources().getString(R.string.restaurant_lamar_description),
                context.getResources().getString(R.string.restaurant_lamar_location),
                context.getResources().getString(R.string.restaurant_lamar_phone),
                context.getResources(). getString(R.string.restaurant_lamar_hours),
                -12.113318, -77.045400,
                context.getResources().getString(R.string.restaurant_lamar_url)));
        restaurants.add(new Place(context.getResources().getString(R.string.restaurant_picanteria_title),
                2,R.drawable.restaurant_picanteria,
                context.getResources().getString(R.string.restaurant_picanteria_description),
                context.getResources().getString(R.string.restaurant_picanteria_location),
                context.getResources().getString(R.string.restaurant_picanteria_phone),
                context.getResources(). getString(R.string.restaurant_picanteria_hours),
                -12.116770, -77.023789,
                context.getResources().getString(R.string.restaurant_picanteria_url)));
        restaurants.add(new Place(context.getResources().getString(R.string.restaurant_titi_title),
                2,R.drawable.restaurant_titi,
                context.getResources().getString(R.string.restaurant_titi_description),
                context.getResources().getString(R.string.restaurant_titi_location),
                context.getResources().getString(R.string.restaurant_titi_phone),
                context.getResources(). getString(R.string.restaurant_titi_hours),
                -12.090089, -77.015694,
                context.getResources().getString(R.string.restaurant_titi_url)));
        restaurants.add(new Place(context.getResources().getString(R.string.restaurant_chinito_title),
                1,R.drawable.restaurant_chinito,
                context.getResources().getString(R.string.restaurant_chinito_description),
                context.getResources().getString(R.string.restaurant_chinito_location),
                context.getResources().getString(R.string.restaurant_chinito_phone),
                context.getResources(). getString(R.string.restaurant_chinito_hours),
                -12.049208, -77.040779,
                context.getResources().getString(R.string.restaurant_chinito_url)));
        restaurants.add(new Place(context.getResources().getString(R.string.restaurant_lucha_title),
                1,R.drawable.restaurant_lucha,
                context.getResources().getString(R.string.restaurant_lucha_description),
                context.getResources().getString(R.string.restaurant_lucha_location),
                context.getResources().getString(R.string.restaurant_lucha_phone),
                context.getResources(). getString(R.string.restaurant_lucha_hours),
                -12.120746, -77.030276,
                context.getResources().getString(R.string.restaurant_lucha_url)));
        restaurants.add(new Place(context.getResources().getString(R.string.restaurant_toke_title),
                1,R.drawable.restaurant_toke,
                context.getResources().getString(R.string.restaurant_toke_description),
                context.getResources().getString(R.string.restaurant_toke_location),
                context.getResources().getString(R.string.restaurant_toke_phone),
                context.getResources(). getString(R.string.restaurant_toke_hours),
                -12.113487, -77.022635,
                context.getResources().getString(R.string.restaurant_toke_url)));

        // Create data to populate restaurants' fragments
        ArrayList<Place> museums = new ArrayList<>();
        museums.add(new Place(context.getResources().getString(R.string.museum_larco_title),
                0,R.drawable.museum_larco,
                context.getResources().getString(R.string.museum_larco_description),
                context.getResources().getString(R.string.museum_larco_location),
                context.getResources().getString(R.string.museum_larco_phone),
                context.getResources(). getString(R.string.museum_larco_hours),
                -12.072484, -77.070839,
                context.getResources().getString(R.string.museum_larco_url)));
        museums.add(new Place(context.getResources().getString(R.string.museum_pisco_title),
                0,R.drawable.museum_pisco,
                context.getResources().getString(R.string.museum_pisco_description),
                context.getResources().getString(R.string.museum_pisco_location),
                context.getResources().getString(R.string.museum_pisco_phone),
                context.getResources(). getString(R.string.museum_pisco_hours),
                -12.045693, -77.029470,
                context.getResources().getString(R.string.museum_pisco_url)));
        museums.add(new Place(context.getResources().getString(R.string.museum_mali_title),
                0,R.drawable.museum_mali,
                context.getResources().getString(R.string.museum_mali_description),
                context.getResources().getString(R.string.museum_mali_location),
                context.getResources().getString(R.string.museum_mali_phone),
                context.getResources(). getString(R.string.museum_mali_hours),
                -12.060318, -77.037012,
                context.getResources().getString(R.string.museum_mali_url)));
        museums.add(new Place(context.getResources().getString(R.string.museum_catacombs_title),
                0,R.drawable.museum_catacombs,
                context.getResources().getString(R.string.museum_catacombs_description),
                context.getResources().getString(R.string.museum_catacombs_location),
                context.getResources().getString(R.string.museum_catacombs_phone),
                context.getResources(). getString(R.string.museum_catacombs_hours),
                -12.045324, -77.027024,
                context.getResources().getString(R.string.museum_catacombs_url)));
        museums.add(new Place(context.getResources().getString(R.string.museum_presbitero_title),
                0,R.drawable.museum_presbitero,
                context.getResources().getString(R.string.museum_presbitero_description),
                context.getResources().getString(R.string.museum_presbitero_location),
                context.getResources().getString(R.string.museum_presbitero_phone),
                context.getResources(). getString(R.string.museum_presbitero_hours),
                -12.041508, -77.007280,
                context.getResources().getString(R.string.museum_presbitero_url)));
        museums.add(new Place(context.getResources().getString(R.string.museum_history_title),
                0,R.drawable.museum_history,
                context.getResources().getString(R.string.museum_history_description),
                context.getResources().getString(R.string.museum_history_location),
                context.getResources().getString(R.string.museum_history_phone),
                context.getResources(). getString(R.string.museum_history_hours),
                -12.077211, -77.062151,
                context.getResources().getString(R.string.museum_history_url)));

        // Create data to populate bars' fragments
        ArrayList<Place> bars = new ArrayList<>();
        bars.add(new Place(context.getResources().getString(R.string.bar_ayahuasca_title),
                2,R.drawable.bar_ayahuasca,
                context.getResources().getString(R.string.bar_ayahuasca_description),
                context.getResources().getString(R.string.bar_ayahuasca_location),
                context.getResources().getString(R.string.bar_ayahuasca_phone),
                context.getResources().getString(R.string.bar_ayahuasca_hours),
                -12.147091, -77.022169,
                context.getResources().getString(R.string.bar_ayahuasca_url)));
        bars.add(new Place(context.getResources().getString(R.string.bar_piselli_title),
                1,R.drawable.bar_piselli,
                context.getResources().getString(R.string.bar_piselli_description),
                context.getResources().getString(R.string.bar_piselli_location),
                context.getResources().getString(R.string.bar_piselli_phone),
                context.getResources().getString(R.string.bar_piselli_hours),
                -12.151070, -77.021778,
                context.getResources().getString(R.string.bar_piselli_url)));
        bars.add(new Place(context.getResources().getString(R.string.bar_express_title),
                3,R.drawable.bar_express,
                context.getResources().getString(R.string.bar_express_description),
                context.getResources().getString(R.string.bar_express_location),
                context.getResources().getString(R.string.bar_express_phone),
                context.getResources().getString(R.string.bar_express_hours),
                -12.123824, -77.027518,
                context.getResources().getString(R.string.bar_express_url)));
        bars.add(new Place(context.getResources().getString(R.string.bar_company_title),
                2,R.drawable.bar_company,
                context.getResources().getString(R.string.bar_company_description),
                context.getResources().getString(R.string.bar_company_location),
                context.getResources().getString(R.string.bar_company_phone),
                context.getResources().getString(R.string.bar_company_hours),
                -12.148647, -77.021010,
                context.getResources().getString(R.string.bar_company_url)));
        bars.add(new Place(context.getResources().getString(R.string.bar_bolivar_title),
                3,R.drawable.bar_bolivar,
                context.getResources().getString(R.string.bar_bolivar_description),
                context.getResources().getString(R.string.bar_bolivar_location),
                context.getResources().getString(R.string.bar_bolivar_phone),
                context.getResources().getString(R.string.bar_bolivar_hours),
                -12.050898, -77.035251,
                context.getResources().getString(R.string.bar_bolivar_url)));

        // Verify state of views
        if (isExpandedCheck == null) {
            // First time run, views need to be initialized
            fragmentA = CategoryFragment.newInstance(landmarks, null);
            fragmentB = CategoryFragment.newInstance(restaurants, null);
            fragmentC = CategoryFragment.newInstance(museums, null);
            fragmentD = CategoryFragment.newInstance(bars, null);
        } else {
            // Views have already been initialized, create fragments with those states
            this.isExpandedCheck = isExpandedCheck;
            fragmentA = CategoryFragment.newInstance(landmarks, isExpandedCheck.get(0));
            fragmentB = CategoryFragment.newInstance(restaurants, isExpandedCheck.get(1));
            fragmentC = CategoryFragment.newInstance(museums, isExpandedCheck.get(2));
            fragmentD = CategoryFragment.newInstance(bars, isExpandedCheck.get(3));
        }
    }

    @Override
    public Fragment getItem(int position) {
        // Return the correct fragment instance according to data passed as an initializing parameter
        switch (position){
            case 0: return fragmentA;
            case 1: return fragmentB;
            case 2: return fragmentC;
            default: return fragmentD;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //Array of Strings to be used as tab titles
        String[] tabNames = {context.getResources().getString(R.string.landmarks),
                context.getResources().getString(R.string.restaurants),
                context.getResources().getString(R.string.museums),
                context.getResources().getString(R.string.bars)};
        // Generate title based on item position
        return tabNames[position];
    }

    // Method that returns the state of the views
    public ArrayList<boolean[]> getIsExpandedCheck() {
        // Initialize and clear the values stored to be replaced with new values captured from
        // fragments' states
        isExpandedCheck = new ArrayList<>();
        isExpandedCheck.clear();
        isExpandedCheck.add(fragmentA.getIsExpanded());
        isExpandedCheck.add(fragmentB.getIsExpanded());
        isExpandedCheck.add(fragmentC.getIsExpanded());
        isExpandedCheck.add(fragmentD.getIsExpanded());
        return isExpandedCheck;
    }
}
