import { View, Text, ScrollView, TouchableOpacity, ActivityIndicator } from 'react-native';
import React, { useState, useEffect } from 'react';
import { BarberCardItem } from '../Shared/BarberCardItem';
import { useNavigation } from '@react-navigation/native';
import Colors from '../../../assets/Shared/Colors';
import { API_BASE_URL } from '../../Config/apiConfig';
import AsyncStorage from '@react-native-async-storage/async-storage';

export function BarberListBig({ service,customer }) {
    const [barberList, setBarberList] = useState([]);
    const [loading, setLoading] = useState(true);
    const navigation = useNavigation();

    useEffect(() => {
        const fetchBarberShops = async () => {
            try {
                const token = await AsyncStorage.getItem('jwtToken');
                const response = await fetch(`${API_BASE_URL}/api/customer/barberShops/service?service=${service}`, {
                    headers: {
                        'Authorization': `Bearer ${token}`,
                    },
                });
                console.log(service);
                const data = await response.json();
                console.log(data);
                setBarberList(data);
            } catch (error) {
                console.error(error);
            } finally {
                setLoading(false);
            }
        };

        fetchBarberShops();
    }, [service]);

    if (loading) {
        return <ActivityIndicator size="large" color={Colors.PRIMARY} />;
    }

    return (
        <ScrollView>
            <View style={{ marginTop: 15, marginRight:10 ,marginBottom:10 }}>
                {barberList.length > 0 ? (
                    barberList.map((barber) => (
                        <TouchableOpacity key={barber.idBarberShop} onPress={() => navigation.navigate('Barber-Shop-detail', { barber , customer: customer })}>
                            <BarberCardItem key={barber.idBarberShop} barber={barber} />
                        </TouchableOpacity>
                    ))
                ) : (
                    <View style={{ alignItems: 'center', justifyContent: 'center', marginTop: 20 }}>
                        <Text style={{ color: Colors.GRAY, fontSize: 16 }}>No barber shops available for this service.</Text>
                    </View>
                )}
            </View>
        </ScrollView>
    );
}
