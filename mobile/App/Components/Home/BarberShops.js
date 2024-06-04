import React, { useState, useEffect } from 'react';
import { View, ScrollView, FlatList, TouchableOpacity, Text,StyleSheet } from 'react-native';
import { BarberList } from './BarberList';
import { SubHeading } from './SubHeading';
import { useNavigation } from '@react-navigation/native';
import { API_BASE_URL } from '../../Config/apiConfig';
import { LocationFilter } from '../Location/LocationFilter';
import Colors from '../../../assets/Shared/Colors';
import AsyncStorage from '@react-native-async-storage/async-storage';

export function BarberShops({ customer }) {
    const [barberData, setBarberData] = useState([]);
    const [showAll, setShowAll] = useState(false);
    const [selectedLocation, setSelectedLocation] = useState('');
    const navigation = useNavigation();

    useEffect(() => {
        fetchBarberShops();
    }, [selectedLocation]);

    const fetchBarberShops =async () => {
        let url = `${API_BASE_URL}/api/customer/barberShops`;
        

        if (selectedLocation) {
            url = `${API_BASE_URL}/api/customer/barberShops/location?location=${selectedLocation}`;
        }
        const token = await AsyncStorage.getItem('jwtToken');

        fetch(url,
          {  method: 'GET',
            headers: {
              'Authorization': `Bearer ${token}`
            }}
        )
            .then(response => response.json())
            .then(data => {
                if (data && data.content) {
                    setBarberData(data.content);
                } else {
                    setBarberData([]);  
                }
            })
            .catch(error => {
                console.error('Erreur lors de la récupération des données :', error);
                setBarberData([]);  
            });
    };

    const toggleShowAll = () => {
        setShowAll(!showAll);
    };

    const keyExtractor = (item, index) => {
        return item.id ? item.id.toString() : index.toString();
    };

    return (
        <View style={{ flex: 1, marginTop: 10 , marginBottom:30}}>
          
            <SubHeading subHeadingTitle={'Our barber Shops'} onPressSeeAll={toggleShowAll} showAll={showAll} />
            
            <LocationFilter selectedLocation={selectedLocation} onLocationChange={setSelectedLocation}/>
            {barberData.length > 0 ? (
                <FlatList
                    data={showAll ? barberData : barberData.slice(0, 2)}
                    horizontal={true}
                    showsHorizontalScrollIndicator={false}
                    renderItem={({ item }) => (
                        <TouchableOpacity onPress={() => navigation.navigate('Barber-Shop-detail', { barber: item, customer: customer })}>
                            <BarberList {...item} />
                        </TouchableOpacity>
                    )}
                    keyExtractor={keyExtractor}
                />
            ) : (
                <Text>No barber shops available</Text>
            )}
        </View>
    );

}

