import React from 'react';
import { View, Text, FlatList, Image } from 'react-native';
import Colors from '../../../assets/Shared/Colors';
import { TouchableOpacity } from 'react-native';
import { useNavigation } from '@react-navigation/native';

export function Services({customer}) {
    const navigation = useNavigation();

    const serviceList = [
        { id: 1, name: 'COUPE', iconUrl: 'https://cdn-icons-png.flaticon.com/512/4428/4428089.png' },
        { id: 2, name: 'RASAGE', iconUrl: 'https://cdn-icons-png.flaticon.com/512/4428/4428089.png' },
        { id: 3, name: 'COLORATION', iconUrl: 'https://cdn-icons-png.flaticon.com/512/4428/4428089.png' },
        { id: 4, name: 'COIFFURE', iconUrl: 'https://cdn-icons-png.flaticon.com/512/4428/4428089.png' },
        { id: 5, name: 'MANUCURE', iconUrl: 'https://cdn-icons-png.flaticon.com/512/4428/4428089.png' },
        { id: 6, name: 'PEDICURE', iconUrl: 'https://cdn-icons-png.flaticon.com/512/4428/4428089.png' },
        { id: 7, name: 'MASSAGE', iconUrl: 'https://cdn-icons-png.flaticon.com/512/4428/4428089.png' },
        { id: 8, name: 'EPILATION', iconUrl: 'https://cdn-icons-png.flaticon.com/512/4428/4428089.png' },
        { id: 9, name: 'MAQUILLAGE', iconUrl: 'https://cdn-icons-png.flaticon.com/512/4428/4428089.png' },
        { id: 10, name: 'SHAMPOING', iconUrl: 'https://cdn-icons-png.flaticon.com/512/4428/4428089.png' },
    ];

    return (
        <View style={{ marginTop: 10 }}>
            <Text style={{ fontSize: 20, marginBottom: 5 }}>Services</Text>
            
            <FlatList
                data={serviceList}
                horizontal
                pagingEnabled
                showsHorizontalScrollIndicator={false}
                renderItem={({ item }) => (
                    <TouchableOpacity
                        onPress={() => navigation.navigate('barbers-by-service-list', {
                            serviceName: item.name,
                            customer: customer
                        })}
                        style={{ alignItems: 'center', marginHorizontal: 6 }}>
                        <View style={{
                            backgroundColor: Colors.SECONDARY,
                            padding: 15,
                            borderRadius: 99,
                        }}>
                            <Image
                                source={{ uri: item.iconUrl }}
                                style={{ width: 30, height: 30 }}
                            />
                        </View>
                        <Text>{item.name}</Text>
                    </TouchableOpacity>
                )}
                keyExtractor={(item) => item.id.toString()}
            />
        </View>
    );
}
