import React, { useState } from 'react';
import { View } from 'react-native';
import { Picker } from '@react-native-picker/picker';
import Colors from '../../../assets/Shared/Colors';

export function LocationFilter({ selectedLocation, onLocationChange }) {
    return (
        <View style={{ marginTop:-10 }}>
            <Picker
                selectedValue={selectedLocation}
                onValueChange={(itemValue) => onLocationChange(itemValue)}
            >
                <Picker.Item label="All Locations" value="" />
                <Picker.Item label="Ain Chock" value="Ain_Chock" />
                <Picker.Item label="Ain Sebaa" value="Ain_Sebaa" />
                <Picker.Item label="Al Azhar Panorama" value="Al_Azhar_Panorama" />
                <Picker.Item label="Al Fida" value="Al_Fida" />
                <Picker.Item label="Annasi" value="Annasi" />
                <Picker.Item label="Beausejour" value="Beausejour" />
                <Picker.Item label="Belvedere" value="Belvedere" />
                <Picker.Item label="Bourgogne" value="Bourgogne" />
                <Picker.Item label="Hay Hassani" value="Hay_Hassani" />
                <Picker.Item label="Hay Mohammadi" value="Hay_Mohammadi" />
                <Picker.Item label="Lissasfa" value="Lissasfa" />
                <Picker.Item label="Maarif" value="Maarif" />
                <Picker.Item label="Oulfa" value="Oulfa" />
                <Picker.Item label="Quartier des Hopitaux" value="Quartier_des_Hopitaux" />
                <Picker.Item label="Sidi Bernoussi" value="Sidi_Bernoussi" />
                <Picker.Item label="Sidi Maarouf" value="Sidi_Maarouf" />
                <Picker.Item label="Sidi Moumen" value="Sidi_Moumen" />
                <Picker.Item label="Sidi Othmane" value="Sidi_Othmane" />
                <Picker.Item label="Val Fleuri" value="Val_Fleuri" />
            </Picker>
        </View>
    );
}
