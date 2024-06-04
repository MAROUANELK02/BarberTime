import { View, Text, Dimensions, StyleSheet, Image, TouchableOpacity } from 'react-native';
import React from 'react';
import { useNavigation } from '@react-navigation/native';
import img from '../../assets/Images/loginimg.jpg';
import Colors from '../../assets/Shared/Colors';

export function Login() {
  const navigation = useNavigation();
  return (
    <View style={{ alignItems: 'center' }}>
      <Image
        source={img}
        style={styles.appImage}
      />
      <View style={styles.contentContainer}>
        <Text style={styles.heading}>Your Favorite Barber</Text>
        <Text style={styles.heading}>Appointment Booking Application</Text>
        <Text style={styles.description}>Book Appointments and Manage good haircut</Text>
        <TouchableOpacity
          onPress={() => navigation.navigate('SignIn')}
          style={styles.loginButton}>
          <Text style={styles.loginButtonText}>Login</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  appImage: {
    width: Dimensions.get('screen').width, 
    height: Dimensions.get('screen').height * 0.4, 
    resizeMode: 'contain',
    marginBottom: -50, 
  },
  contentContainer: {
    backgroundColor: Colors.while,
    padding: 25,
    alignItems: 'center',
    elevation: 2,
    width: '90%', 
  },
  heading: {
    fontSize: 28,
    fontWeight: 'bold',
    textAlign: 'center',
    marginVertical: 5, 
  },
  description: {
    textAlign: 'center',
    marginTop: 20,
  },
  loginButton: {
    padding: 16,
    backgroundColor: Colors.PRIMARY,
    borderRadius: 60,
    alignItems: 'center',
    marginTop: 20,
    width: Dimensions.get('screen').width * 0.8,
  },
  loginButtonText: {
    fontSize: 17,
    color: Colors.while,
  },
});
