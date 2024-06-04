import { View, Text,FlatList,Image } from 'react-native'
import React, { useState, useEffect } from 'react'
import Colors from '../../../assets/Shared/Colors'
import {Ionicons} from '@expo/vector-icons'
import { API_BASE_URL } from '../../Config/apiConfig';
import AsyncStorage from '@react-native-async-storage/async-storage';

export function BarberCardItem({ barber }) {
  const defaultImage = require('../../../assets/Images/barberImageNew.jpg');
  const [barberImage, setBarberImage] = useState(defaultImage);

  useEffect(() => {
    let url = `${API_BASE_URL}/api/owner/image`;

    const fetchTokenAndImage = async () => {
      try {
        // Récupérer le jeton de AsyncStorage
        const storedToken = await AsyncStorage.getItem('jwtToken');
        
        if (!storedToken) {
          console.error('Token not found');
          setBarberImage(defaultImage);
          return;
        }
        if (barber && barber.images && barber.images.length > 0) {
        
          const imageUrl = `${url}/${barber.images[0].id}`;
          console.log('Image URL:', imageUrl); 

          const response = await fetch(imageUrl,
              {  method: 'GET',
                headers: {
                  'Authorization': `Bearer ${storedToken}`
                }}
          )

          if (response.status === 200) {
           
            setBarberImage({ uri: imageUrl });
          } else {
            console.error('Erreur de requête:', response.status);
            setBarberImage(defaultImage);
          }
        } else {
          setBarberImage(defaultImage);
        }
      } catch (error) {
        console.error('Erreur lors du chargement de l\'image:', error);
        setBarberImage(defaultImage);
      }
    };

    fetchTokenAndImage();
  }, [barber]);
    return (
      <View style={{ borderRadius: 10, marginBottom: 20 }}>
        <Image
        source={barberImage}
        style={{ width: '100%', height: 140, borderTopLeftRadius: 10, borderTopRightRadius: 10 }}
      />
       
        <View
          style={{
            padding: 10,
            backgroundColor: Colors.while,
            borderBottomLeftRadius: 10,
            borderBottomRightRadius: 10,
          }}
        >
          <Text style={{ fontSize: 18 }}>{barber.name}</Text>
          <FlatList
            data={barber.service}
            horizontal={true}
            renderItem={({ item }) => (
              <Text style={{ marginRight: 10, color: Colors.GRAY }}>
                {item.name}
              </Text>
            )}
          />
          <View
            style={{
              borderBottomWidth: 1,
              borderColor: Colors.LIGHT_GRAY,
              margin: 5,
              marginBottom: 16,
            }}
          ></View>
          <View style={{ flexDirection: 'row', alignItems: 'center' }}>
            <Ionicons name="location" size={18} color={Colors.PRIMARY} />
            <Text>{barber.address}</Text>
          </View>
          <View style={{ flexDirection: 'row', alignItems: 'center', marginBottom:10,marginTop:4 }}>
            <Ionicons name="mail" size={20} color={Colors.PRIMARY} />
            <Text>{barber.phone}</Text>
          </View>
        </View>
      </View>
    );
  }
  