import { View, Text, FlatList, TouchableOpacity, Linking } from 'react-native'
import React from 'react'
import { Ionicons } from '@expo/vector-icons'
import Colors from '../../../assets/Shared/Colors'

export function ActionButton({ barber }) {
  const actionButtonList = [
    {
      id: 1,
      name: 'Website',
      icon: 'earth',
     
    },
    {
      id: 2,
      name: 'Email',
      icon: 'chatbubble-ellipses',
      onPress: () => openUrl(`mailto:${barber.ownerDTO.email}`)
    },
    {
      id: 3,
      name: 'Phone',
      icon: 'call-sharp',
      onPress: () => openUrl(`tel:${barber.phone}`)
    },
    {
      id: 4,
      name: 'Direction',
      icon: 'map',
      onPress: () => openUrl(`https://maps.google.com/?q=${barber.address}`)
    },
    {
      id: 5,
      name: 'Share',
      icon: 'share',

    },
  ]

  const openUrl = (url) => {
    Linking.openURL(url);
  }


  return (
    <View style={{ marginTop: 15 }}>
      <FlatList
        data={actionButtonList}
        columnWrapperStyle={{
          flex: 1,
          justifyContent: 'space-between'
        }}
        numColumns={5}
        renderItem={({ item }) => (
          <TouchableOpacity style={{ alignItems: 'center' }} onPress={item.onPress}>
            <View style={{
              backgroundColor: Colors.SECONDARY,
              padding: 13,
              borderRadius: 99,
              alignItems: 'center'
            }}>
              <Ionicons name={item.icon} size={25} color={Colors.PRIMARY} />
            </View>
            <Text style={{ marginTop: 5 }}>{item.name}</Text>
          </TouchableOpacity>
        )}
      />
    </View>
  )
}
