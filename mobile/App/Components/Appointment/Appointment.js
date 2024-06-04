import React, { useState, useEffect } from 'react';
import { View, Text, TouchableOpacity, FlatList, StyleSheet, Alert } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { API_BASE_URL } from '../../Config/apiConfig';
import AsyncStorage from "@react-native-async-storage/async-storage";

export function Appointment({ data }) {
  const [appointments, setAppointments] = useState(data);

  const handleEditAppointment = (id) => {
    console.log("Edit appointment with ID:", id);
  };

  const handleDeleteAppointment = async (id) => {
    Alert.alert(
        "Annuler le rendez-vous",
        "Êtes-vous sûr de vouloir annuler ce rendez-vous ?",
        [
          {
            text: "Non",
            style: "cancel"
          },
          {
            text: "Oui",
            onPress: async () => {
              try {
                const token = await AsyncStorage.getItem('jwtToken');
                const response = await fetch(`${API_BASE_URL}/api/customer/appointment/${id}/cancel`, {
                  method: 'PATCH',
                  headers: {
                    'Authorization': `Bearer ${token}`,
                  },
                });

                if (!response.ok) {
                  throw new Error('Erreur lors de l\'annulation du rendez-vous');
                }

                console.log("Appointment with ID:", id, "has been cancelled");
                setAppointments(appointments.filter(appointment => appointment.idAppointment !== id));
              } catch (error) {
                console.error('Erreur lors de l\'annulation du rendez-vous :', error);
              }
            }
          }
        ]
    );
  };

  const renderItem = ({ item }) => (
    <View style={styles.appointmentContainer}>
      <View style={styles.appointmentHeader}>
        <Text style={styles.title}>{item.date}</Text>
        <Ionicons name="calendar" size={24} color="#333" />
      </View>
      <View style={styles.appointmentInfo}>
        <Ionicons name="time" size={20} color="#333" />
        <Text>{item.time}</Text>
      </View>
      <View style={styles.appointmentInfo}>
        <Ionicons name="storefront" size={20} color="#333" />
        <Text>{item.barberShopResDTO.name}</Text>
      </View>
      <View style={styles.appointmentInfo}>
        <Ionicons name="person" size={20} color="#333" />
        <Text>{`${item.barberShopResDTO.ownerDTO.firstName} ${item.barberShopResDTO.ownerDTO.lastName}`}</Text>
      </View>
      <View style={styles.appointmentInfo}>
        <Ionicons name="location" size={20} color="#333" />
        <Text>{item.barberShopResDTO.address}, {item.barberShopResDTO.neighborhood} </Text>
      </View>
      <View style={styles.appointmentInfo}>
        <Ionicons name="call" size={20} color="#333" />
        <Text>{item.barberShopResDTO.phone}</Text>
      </View>
      <View style={styles.appointmentInfo}>
        <Ionicons name="mail" size={20} color="#333" />
        <Text>{item.barberShopResDTO.ownerDTO.email}</Text>
      </View>
      <View style={styles.appointmentInfo}>
        <Ionicons name="checkmark-done" size={20} color={item.status === 'Confirmed' ? 'green' : 'red'} />
        <Text>{item.status}</Text>
      </View>
      <View style={styles.buttonContainer}>
        <TouchableOpacity style={[styles.button, styles.editButton]} onPress={() => handleEditAppointment(item.idAppointment)}>
          <Ionicons name="pencil" size={20} color="#fff" />
          <Text style={styles.buttonText}>Modifier</Text>
        </TouchableOpacity>
        <TouchableOpacity style={[styles.button, styles.deleteButton]} onPress={() => handleDeleteAppointment(item.idAppointment)}>
          <Ionicons name="trash" size={20} color="#fff" />
          <Text style={styles.buttonText}>Annuler</Text>
        </TouchableOpacity>
      </View>
    </View>
  );

  return (
    <FlatList
      data={appointments}
      renderItem={renderItem}
      keyExtractor={(item) => item.idAppointment.toString()}
    />
  );
}

const styles = StyleSheet.create({
  appointmentContainer: {
    paddingVertical: 10,
    paddingHorizontal: 20,
    borderBottomWidth: 1,
    borderBottomColor: '#ccc',
  },
  appointmentHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 10,
  },
  appointmentInfo: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 5,
  },
  title: {
    fontWeight: 'bold',
    fontSize: 18,
  },
  buttonContainer: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    marginTop: 10,
  },
  button: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: 10,
    paddingVertical: 5,
    borderRadius: 5,
  },
  editButton: {
    backgroundColor: 'blue',
  },
  deleteButton: {
    marginLeft: 5,
    backgroundColor: 'red',
  },
  buttonText: {
    color: '#fff',
    marginLeft: 5,
  },
});
