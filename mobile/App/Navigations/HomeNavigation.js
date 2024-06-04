import { View, Text } from 'react-native'
import React from 'react'
import {createStackNavigator }from '@react-navigation/stack'
import { Home } from '../Screens/Home';
import { BarberServiceList } from '../Screens/BarberServiceList';
import { BarberShopDetails } from '../Screens/BarberShopDetails';
import {BookAppointment} from '../Screens/BookAppointment';

const Stack=createStackNavigator();
export default function Homenavigation() {
return (
<Stack.Navigator >
<Stack.Screen name='Home' component={Home} />
<Stack.Screen name='barbers-by-service-list'
component={BarberServiceList}/>

<Stack.Screen name='Barber-Shop-detail' component={BarberShopDetails}/>

<Stack.Screen name='book-appointment' component={BookAppointment}/>
</Stack.Navigator>
)
}