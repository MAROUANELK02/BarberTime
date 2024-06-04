import React from 'react'
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs'
import { Home } from '../Screens/Home'
import { Profile } from '../Screens/Profile'
import { Appointment } from '../Screens/AppointmentContainer'

const Tab= createBottomTabNavigator()

export function TabNavigation() {
  return (
    <Tab.Navigator>
 
 
      <Tab.Screen name='Profile' component={Profile} />
      <Tab.Screen name='Appointment' component={Appointment} />
    </Tab.Navigator>
  )
}