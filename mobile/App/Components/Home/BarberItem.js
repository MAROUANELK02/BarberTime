import { View, Text } from 'react-native'
import React from 'react'



export function BarberItem() {
  return (
    <View style={{width:200,
        borderWidth:1,
        borderColor:Colors.LIGHT_GRAY,
        borderRadius:10,
        marginRight:10}}>
        <Image source={{uri:barber.attributes.image}}
        style={{width: '100%', height: 110, borderTopLeftRadius:10, borderTopRightRadius:10}}
        />
        <View style={{padding:7}}>
        <Text style={{
        fontSize:16}}>{hospital.attributes.Name}</Text>
        <Text style={{color:Colors.GRAY}}>{hospital.attributes.Address}</Text>
        </View>
        </View>
  )
}