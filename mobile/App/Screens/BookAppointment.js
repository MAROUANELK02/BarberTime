import { View, Text,ScrollView } from 'react-native'
import React from 'react'
import {AppointmentBarberInfo}  from '../Components/BookAppointment/AppointmentBarberInfo';
import { useRoute } from '@react-navigation/native';
import { ActionButton } from '../Components/BarberDetail/ActionButton';
import { HorizontalLine } from '../Components/Shared/HorizontalLine';
import { BookingSection } from '../Components/BookAppointment/BookingSection';

export function BookAppointment() {
    const param=useRoute().params;
  return (
    <View style={{padding:20,marginTop:15}}>
    <AppointmentBarberInfo barber={param.barber} />
    <ActionButton barber={param.barber}/>
    <HorizontalLine/>
    <BookingSection barber={param.barber} customer={param.customer}/>
    </View>

  )
}