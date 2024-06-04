import React, { useEffect, useState } from 'react';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { Home } from '../Screens/Home';
import { Profile } from '../Screens/Profile';
import { AppointmentContainer } from './AppointmentContainer';
import Ionicons from 'react-native-vector-icons/Ionicons';
import { API_BASE_URL } from '../Config/apiConfig';
import { useCustomer } from '../Contexts/CustomerContext';
import Colors from '../../assets/Shared/Colors';
import AsyncStorage from '@react-native-async-storage/async-storage';

const HomeName = 'Home';
const AppointmentName = 'Appointment';
const ProfileName = 'Profile';

const Tab = createBottomTabNavigator();

export function MainScreen() {
  const { customer, setCustomer } = useCustomer();

  useEffect(() => {
    fetchCustomer();
  }, []);

  const fetchCustomer = async () => {
    try {
      const token = await AsyncStorage.getItem('jwtToken');
      if (!token) {
        console.error('Token is not available');
        return;
      }

      const response = await fetch(`${API_BASE_URL}/api/customer/${customer.id}`, {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });

      const data = await response.json();

      if (response.ok) {
        setCustomer(data);
      } else {
        console.error('Error fetching customer:', data.message);
      }
    } catch (error) {
      console.error('Error fetching customer:', error);
    }
  };


  console.log('Customer in MainScreen render:', customer , customer.token);

  return (
      <Tab.Navigator
          initialRouteName={HomeName}
          screenOptions={({ route }) => ({
            tabBarIcon: ({ focused, color, size }) => {
              let iconName;
              let rn = route.name;

              if (rn === HomeName) {
                iconName = focused ? 'home' : 'home-outline';
              } else if (rn === AppointmentName) {
                iconName = focused ? 'calendar' : 'calendar-outline';
              } else if (rn === ProfileName) {
                iconName = focused ? 'person-circle' : 'person-circle-outline';
              }
              return <Ionicons name={iconName} size={size} color={color} />;
            },
            tabBarActiveTintColor: Colors.PRIMARY,
            tabBarInactiveTintColor: 'grey',
            tabBarLabelStyle: { paddingBottom: 10, fontSize: 10 },
            tabBarStyle: { padding: 10, height: 59 },
          })}
      >
        <Tab.Screen name={HomeName}>
          {(props) => <Home {...props} customer={customer} />}
        </Tab.Screen>

        <Tab.Screen name={AppointmentName}>
          {(props) => <AppointmentContainer {...props} customer={customer} />}
        </Tab.Screen>
        <Tab.Screen name={ProfileName}>
          {(props) => <Profile {...props} customer={customer} />}
        </Tab.Screen>
      </Tab.Navigator>
  );
}
