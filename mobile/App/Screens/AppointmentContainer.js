import React, { useState, useCallback } from 'react';
import { View, Text, ActivityIndicator } from 'react-native';
import { useFocusEffect } from '@react-navigation/native';
import { Appointment } from '../Components/Appointment/Appointment';
import { API_BASE_URL } from '../Config/apiConfig';
import AsyncStorage from '@react-native-async-storage/async-storage';

export function AppointmentContainer({ customer }) {
  const [appointments, setAppointments] = useState([]);
  const [loading, setLoading] = useState(true);

  const fetchData = async () => {
    try {
      const token = await AsyncStorage.getItem('jwtToken');
      const response = await fetch(`${API_BASE_URL}/api/customer/appointments/${customer.idUser}`, {
        headers: {
          'Authorization': `Bearer ${token}`,
        },
      });
      if (!response.ok) {
        throw new Error('Erreur lors de la récupération des données');
      }
      const data = await response.json();
      setAppointments(data.content);
    } catch (error) {
      console.error('Erreur lors de la récupération des données :', error);
    } finally {
      setLoading(false);
    }
  };

  useFocusEffect(
    useCallback(() => {
      setLoading(true);
      fetchData();
    }, [customer.idUser])
  );

  return (
    <View style={{ flex: 1 }}>
      {loading ? (
        <ActivityIndicator size="large" color="#0000ff" />
      ) : appointments.length === 0 ? (
        <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
          <Text>Vous n'avez pas de rendez-vous.</Text>
        </View>
      ) : (
        <Appointment data={appointments} />
      )}
    </View>
  );
}
