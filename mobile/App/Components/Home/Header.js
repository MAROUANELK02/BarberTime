import { View, Text,Image } from 'react-native'
import React from 'react'
import pic from '../../../assets/Images/pic.png'
import {Ionicons} from '@expo/vector-icons'
export function Header({customer}) {
  return (
    <View style={{display:'flex',flexDirection:'row',alignItems:'center',justifyContent:'space-between'}}>
        <View style={{
            display:'flex',
            flexDirection:'row',
            gap:7,
            alignItems:'center'
        }}> 
            <Image source={pic}
            style={{width:45,height:45,borderRadius:99}}
            />
<View>
    <Text>Hello,👋</Text>

    {customer ? (
                    <Text style={{fontSize:18, fontWeight:'bold'}}>
                        {customer.firstName} {customer.lastName}
                    </Text>
                ) : (
                    <Text>Loading...</Text>
                )}
    </View>
    </View>
    
    </View>
  )
}