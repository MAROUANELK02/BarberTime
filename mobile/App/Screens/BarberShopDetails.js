import React, { useState, useEffect } from 'react';
import { View, Text, Image, TouchableOpacity, FlatList, Dimensions } from 'react-native';
import { BarberInfo } from '../Components/BarberDetail/BarberInfo';
import Colors from '../../assets/Shared/Colors';
import { PageHeader } from '../Components/Shared/PageHeader';
import { useNavigation, useRoute } from '@react-navigation/native';
import { API_BASE_URL } from '../Config/apiConfig';
import AsyncStorage from '@react-native-async-storage/async-storage';
const windowWidth = Dimensions.get('window').width;

export function BarberShopDetails() {
    const route = useRoute();
    const barber = route.params.barber;
    const navigation = useNavigation();
    const customer = route.params.customer;
    const [images, setImages] = useState([]);

    useEffect(() => {
        const fetchImages = async () => {
            try {
                const token = await AsyncStorage.getItem('jwtToken');
                const response = await fetch(`${API_BASE_URL}/api/owner/barberShop/${barber.idBarberShop}/images`,{
                    headers: {
                      'Authorization': `Bearer ${token}`,
                    },});
                if (response.ok) {
                    const data = await response.json();
                    setImages(data);
                } else {
                    console.error('Failed to fetch images:', response.status);
                }
            } catch (error) {
                console.error('Error fetching images:', error);
            }
        };

        fetchImages();
    }, [barber.id]);

    const renderImageItem = ({ item }) => (
        <Image
            source={{ uri: `data:image/jpeg;base64,${item}` }} 
            style={{ width: windowWidth, height: 260 }}
            resizeMode='cover'
        />
    );

    const renderDefaultImage = () => (
        <Image
            source={require('../../assets/Images/image.png')}
            style={{ width: windowWidth, height: 260 }}
            resizeMode='cover'
        />
    );

    return barber && (
        <View style={{ flex: 1, backgroundColor: Colors.while }}>
            <View style={{ position: 'absolute', zIndex: 10, margin: 15, marginTop: 30 }}>
                <PageHeader title={''} />
            </View>

            {images.length > 0 ? (
                <FlatList
                    horizontal
                    data={images}
                    renderItem={renderImageItem}
                    keyExtractor={(item, index) => index.toString()}
                    pagingEnabled
                />
            ) : (
                renderDefaultImage()
            )}

            <View
                style={{
                    marginTop: -30,
                    backgroundColor: Colors.while,
                    borderTopLeftRadius: 20,
                    borderTopRightRadius: 20,
                    padding: 20
                }}
            >
                <BarberInfo barber={barber} customer={customer} />
            </View>

            <TouchableOpacity
                onPress={() => navigation.navigate('book-appointment', { barber: barber, customer: customer })}
                style={{
                    padding: 13,
                    backgroundColor: Colors.PRIMARY,
                    margin: 10,
                    borderRadius: 99,
                    left: 0,
                    right: 0,
                    marginBottom: 10,
                    zIndex: 20
                }}
            >
                <Text style={{ color: Colors.while, textAlign: 'center', fontSize: 17 }}>Book Appointment</Text>
            </TouchableOpacity>
        </View>
    );
}
