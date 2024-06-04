import { View, Text, ActivityIndicator } from 'react-native';
import React from 'react';
import { useRoute } from '@react-navigation/native';
import { PageHeader } from '../Components/Shared/PageHeader';
import { BarberListBig } from '../Components/BarberScreen/BarberListBig';
import Colors from '../../assets/Shared/Colors';

export function BarberServiceList() {
    const route = useRoute();
    const { serviceName , customer} = route.params;
    

    return (
        <View style={{ paddingTop: 35, paddingLeft: 10 }}>
            <PageHeader title={serviceName} />
            <BarberListBig service={serviceName} customer={customer}/>
        </View>
    );
}
