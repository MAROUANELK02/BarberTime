import React from 'react';
import { View, Text, FlatList, Image, Dimensions } from 'react-native';

export function Slider() {
  const sliderList = [
    {
      id: 1,
      name: 'Slider 1',
      imageUrl: 'https://assets-global.website-files.com/644a9d9ce529ef8812f82a28/647fb85c69e95444243ef9bd_Henley%27s%20Gentlemen%27s%20Grooming%20-%20Barbershop%20and%20Mens%20Grooming.webp',
    },
    {
      id: 2,
      name: 'Slider 2',
      imageUrl: 'https://as1.ftcdn.net/v2/jpg/02/05/49/82/1000_F_205498258_AfQmtyR5kO5llwKd6fWRRxcc4xRUbQcb.jpg',
    },
  ];

  return (
    <View style={{ marginTop: 10 }}>
      <FlatList
        data={sliderList}
        horizontal={true}
        showsHorizontalScrollIndicator={false}
        renderItem={({ item }) => (
          <Image
            source={{ uri: item.imageUrl }}
            style={{
              width: Dimensions.get('screen').width * 0.9,
              height: 170,
              borderRadius: 10,
            }}
            onError={() => console.log('Error loading image')}
          />
        )}
      />
 
    </View>
  );
}
