import React, { useState, useEffect } from 'react';
import { View, Text, Image } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import Colors from '../../../assets/Shared/Colors';
import { API_BASE_URL } from '../../Config/apiConfig';

export function BarberList( barber ) {
  const defaultImage = require('../../../assets/Images/image.png');
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

        // Vérifier si barber et barber.images sont définis
        if (barber && barber.images && barber.images.length > 0) {
          // Corriger l'URL de l'image
          const imageUrl = `${url}/${barber.images[0].id}`;
          console.log('Image URL:', imageUrl); // Vérifiez l'URL

          // Faire une requête fetch pour vérifier l'accessibilité de l'image
          const response = await fetch(imageUrl,
              {  method: 'GET',
                headers: {
                  'Authorization': `Bearer ${storedToken}`
                }}
          )

          if (response.status === 200) {
            // Si la requête est réussie, mettre à jour barberImage avec l'URL
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
    <View style={{
      width: 200,
      borderWidth: 1,
      borderRadius: 10,
      marginRight: 10
    }}>
      <Image
        source={barberImage}
        style={{ width: '100%', height: 110, borderTopLeftRadius: 10, borderTopRightRadius: 10 }}
      />
      <View style={{ padding: 7 }}>
        <Text style={{ fontSize: 16 }}>{barber.name}</Text>
        <Text style={{ color: Colors.GRAY }}>{barber.address}</Text>
      </View>
    </View>
  );
}