package com.zb.library;

import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.list.FlowQueryList;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;


public class DefaultAddressProvider implements AddressProvider {
    public DefaultAddressProvider(Context context) {
        FlowManager.init(new FlowConfig.Builder(context.getApplicationContext()).build());
    }

    @Override
    public void provideProvinces(final AddressReceiver<Province> addressReceiver) {
        final FlowQueryList<Province> provinceQueryList = SQLite.select()
                .from(Province.class)
                .flowQueryList();
        addressReceiver.send(new ArrayList<>(provinceQueryList));
    }

    @Override
    public void provideCitiesWith(int provinceId, final AddressReceiver<City> addressReceiver) {
        final FlowQueryList<City> cityQueryList = SQLite.select()
                .from(City.class)
                .flowQueryList();
        addressReceiver.send(new ArrayList<>(cityQueryList));
    }

    @Override
    public void provideCountiesWith(int cityId, final AddressReceiver<County> addressReceiver) {
        final FlowQueryList<County> countyQueryList = SQLite.select()
                .from(County.class)
                .flowQueryList();
        addressReceiver.send(new ArrayList<>(countyQueryList));
    }

    @Override
    public void provideStreetsWith(int countyId, final AddressReceiver<Street> addressReceiver) {
        final FlowQueryList<Street> streetQueryList = SQLite.select()
                .from(Street.class)
                .flowQueryList();
        addressReceiver.send(new ArrayList<>(streetQueryList));
    }
}
