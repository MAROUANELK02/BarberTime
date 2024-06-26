import { View, Text, TouchableOpacity } from 'react-native'
import React from 'react'
import {Ionicons} from '@expo/vector-icons'
import { useNavigation } from '@react-navigation/native'



export function PageHeader({title,backButton=true}) {
    const navigation=useNavigation();
  return (
    <View style={{display:'flex',flexDirection:'row',
    gap:5,alignItems:'center'}}>
      {backButton? <TouchableOpacity onPress={()=>navigation.goBack()}>
        <Ionicons name="arrow-back-circle-outline"
        size={37} color="black" />
        </TouchableOpacity>:null} 
      <Text style={{fontSize:25}}>{title}</Text>
    </View>
  )
}