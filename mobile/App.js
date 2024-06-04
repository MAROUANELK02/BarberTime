import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import {Login} from './App/Screens/Login'
import {MainScreen} from './App/Screens/MainScreen'
import {Profile} from './App/Screens/Profile'
import { Appointment } from './App/Screens/AppointmentContainer';
import { TabNavigation } from './App/Navigations/TabNavigation';
import { Header } from './App/Components/Home/Header';
import { BarberServiceList } from './App/Screens/BarberServiceList';
import { BarberShopDetails } from './App/Screens/BarberShopDetails';
import { BookAppointment } from './App/Screens/BookAppointment';
import { SignIn } from './App/Screens/SignIn';
import { SignUp } from './App/Screens/SignUp';
import { CustomerProvider } from './App/Contexts/CustomerContext';
import { EmailVerification } from './App/Components/Verification/EmailVerification';
import { CodeVerification } from './App/Components/Verification/CodeVerification';
const Stack = createStackNavigator();

export default function App() {
  return (
    <CustomerProvider>
    <NavigationContainer>
      <Stack.Navigator>
 
        <Stack.Screen name="Login" component={Login} />
        <Stack.Screen name="SignIn" component={SignIn} />
        <Stack.Screen name="EmailVerification" component={EmailVerification} />
        <Stack.Screen name="CodeVerification" component={CodeVerification} />
        <Stack.Screen name="SignUp" component={SignUp} />
        <Stack.Screen name="MainScreen" component={MainScreen } options={{ headerShown: false }} />
       <Stack.Screen name="barbers-by-service-list" component={BarberServiceList} options={{ headerShown: false }}/>
       <Stack.Screen name="Barber-Shop-detail" component={BarberShopDetails} options={{ headerShown: false }}/>
       <Stack.Screen name="book-appointment" component={BookAppointment} options={{ headerShown: false }}/>
     

      </Stack.Navigator>
    </NavigationContainer>
    </CustomerProvider>
  );
}
